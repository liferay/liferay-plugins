/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.mail.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.search.AndTerm;
import javax.mail.search.BodyTerm;
import javax.mail.search.FromStringTerm;
import javax.mail.search.OrTerm;
import javax.mail.search.RecipientStringTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import com.liferay.util.JSONUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.mail.model.MailAccount;
import com.liferay.util.mail.JavaMailUtil;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPSSLStore;
import com.sun.mail.imap.IMAPStore;

/**
 * Used as a fascade to the JavaMail API.  All JavaMail objects are wrappered in
 * model classes and returned out of this utility fascade.
 *
 * <a href="MailBoxManager.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 *
 */
public class MailBoxManager {

	private MailAccount _defaultMailAccount;
    private Session _session = null;
    private Store _store = null;
	private User _user;

	private String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    public MailBoxManager(User user, int accountId) {

    	// get mail settings for account number for user

    	_user = user;

    	_defaultMailAccount = new MailAccount(user, accountId);
    }

    public void deleteMessage(Folder folder, Message message) {

        try {
        	if (!folder.isOpen()) {
        		folder.open(Folder.READ_WRITE);
        	}

			message.setFlag(Flags.Flag.DELETED, true);

			folder.close(true);
		}
		catch (MessagingException ex) {
		    System.out.println(ex.getMessage());
        }
	}

    public void deleteMessagesByUid(String folderName, String messageUidsCsv)
    	throws MessagingException {

    	Folder folder = getFolder(folderName);

    	deleteMessagesByUid(folder, messageUidsCsv);
    }

    public void deleteMessagesByUid(Folder folder, String messageUidsCsv) {
        try {
        	int[] messageUids = GetterUtil.getIntegerValues(
        		messageUidsCsv.split("\\s*,\\s*"));

        	if (!folder.isOpen()) {
        		folder.open(Folder.READ_WRITE);
        	}

        	for (int i = 0; i < messageUids.length; i++) {
        		try {
            		Message message = getMessageByUid(folder, messageUids[i]);

        			message.setFlag(Flags.Flag.DELETED, true);
        		}
        		catch (MessagingException me) {
        			_log.error(me);
        		}
        	}

			folder.close(true);
		}
		catch (MessagingException ex) {
		    System.out.println(ex.getMessage());
        }
	}

    /*
     * Create folder if it does not exist
     */
	public void createFolder(String folderName) throws Exception {

		Folder newFolder = _getStore().getFolder(folderName);

		if (!newFolder.exists()) {
			newFolder.create(Folder.HOLDS_MESSAGES);
		}
	}

    /**
     *
     *
     * @param addresses
     * @return
     */
	public String getAddressesAsString(Address[] addresses) {
        StringBuffer sb = new StringBuffer();

		if (addresses == null) {
			return StringPool.BLANK;
		}

        try {
			for (int i = 0; i < addresses.length; i++) {

				if (i != 0) {
					sb.append(",");
				}

				sb.append(((InternetAddress)addresses[i]).getAddress());
			}
        }
        catch(Exception e) {
            e.printStackTrace();

            return null;
        }

		return sb.toString();
	}

	public Part getAttachment(
			String folderName, int messageUid, String contentPath) 
		throws MessagingException { 
	
		Message message = getMessageByUid(folderName, messageUid);
		
		return _getMessagePart(message, contentPath);
	}
	
	/**
	 * Gets a list of folders (and subfolders) from store
	 *
	 * @return
	 * @throws MessagingException
	 */
	public List getFolders() throws MessagingException {
		Store store = _getStore();
		IMAPFolder rootFolder = (IMAPFolder)store.getDefaultFolder();

		List allFolders = new ArrayList();
		
		_getFolders(allFolders, rootFolder.list());
		
		return allFolders;
	}

	/**
	 * Gets the specified folder (not opened)
	 *
	 * @param folderName
	 * @return
	 * @throws Exception
	 */
    public Folder getFolder(String folderName) throws MessagingException {
        // Open the Folder
        Folder folder = _getStore().getDefaultFolder();

        folder = folder.getFolder(folderName);

        if (folder == null) {
            System.out.println("Invalid folder: " + folderName);
        }

        return folder;
	}

	public static String getJsonEmailAccounts(long userId)
    	throws MessagingException {

    	JSONObject jsonObj = new JSONObject();
		JSONArray jsonArray = new JSONArray();

    	// Get user's email accounts

    	// Stuff them into a JSON array

		// Loop through all accounts

		// MUST CORREPSOND WITH DATA IN MailAccount.java

		// Sample Account 1

		JSONObject jsonAccount1 = new JSONObject();

		JSONUtil.put(jsonAccount1, "emailAccount", "liferay.mail.1@gmail.com");
		JSONUtil.put(jsonAccount1, "accountId", "0");

		jsonArray.put(jsonAccount1);

		// Sample Account 2

		JSONObject jsonAccount2 = new JSONObject();

		JSONUtil.put(jsonAccount2, "emailAccount", "liferay.mail.2@gmail.com");
		JSONUtil.put(jsonAccount2, "accountId", "1");

		jsonArray.put(jsonAccount2);

		// Sample Account 3

		JSONObject jsonAccount3 = new JSONObject();

		JSONUtil.put(jsonAccount3, "emailAccount", "liferay.mail.3@gmail.com");
		JSONUtil.put(jsonAccount3, "accountId", "2");

		jsonArray.put(jsonAccount3);

		// Add to array

		JSONUtil.put(jsonObj, "accounts", jsonArray);

		return jsonObj.toString();
	}

    public JSONObject getJsonFolder(IMAPFolder folder)
    	throws MessagingException {

    	JSONObject jsonFolderObj = new JSONObject();

		Date startTimer = new Date();
    	
    	if (folder.getType() != Folder.HOLDS_FOLDERS) {
    		folder.open(Folder.READ_ONLY);

    		JSONUtil.put(jsonFolderObj, "newMessages", folder.getUnreadMessageCount());
    		JSONUtil.put(jsonFolderObj, "name", folder.getFullName());
    	
    		Message[] messages = folder.getMessages();
    		
    		JSONUtil.put(jsonFolderObj, "messageCount", messages.length);

    		/*
    		JSONArray jsonUidList = new JSONArray();

    		if (messages.length > 0) {
				for (int i = 0; i < messages.length; i++) {
					jsonUidList.put(i, folder.getUID(messages[i]));
				}
    		}

    		jsonFolderObj.put("uidList", jsonUidList);
    		*/
    		
    		folder.close(false);

    		Date endTimer = new Date();

    		int elapsedSeconds = 
    			((endTimer.getMinutes() * 60) + endTimer.getSeconds()) - 
    			((startTimer.getMinutes() * 60) + startTimer.getSeconds());
    		
    		System.out.println("get jsonfolder[" + folder.getFullName() + "] with messages[" + messages.length + "] took " + elapsedSeconds + " seconds");
    		//System.out.println("get jsonfolder[" + folder.getFullName() + "] took " + elapsedSeconds + " seconds");
    		
    		return jsonFolderObj;
    	}

    	return null;
    }

    public String getJsonFolders() throws MessagingException {

    	// Get folder listing

		List folders = getFolders();

		// Create json object

		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		// Loop through all folders

		for (int i = 0; i < folders.size() ; i++) {
			IMAPFolder folder = (IMAPFolder)folders.get(i);

			if (folder.getType() != Folder.HOLDS_FOLDERS) {
				jsonArray.put(getJsonFolder(folder));
			}
		}

		JSONUtil.put(jsonObj, "folderCount", folders.size());
		JSONUtil.put(jsonObj, "folders", jsonArray);

		return jsonObj.toString();
    }

    public JSONObject getJsonMessage(
    		IMAPFolder folder, Message message, boolean isSummary)
    	throws MessagingException {

		Date startTimer = new Date();

		String read = "read";

		if (!message.isSet(Flags.Flag.SEEN)) {
			read = "unread";
		}

		SimpleDateFormat format = new SimpleDateFormat("MMM dd yyyy HH:mm");
		String date = format.format(message.getSentDate());

		// create json object

		JSONObject jsonObj = new JSONObject();

		JSONUtil.put(jsonObj, "date", date);
		JSONUtil.put(jsonObj, "from", getAddressesAsString(message.getFrom()));
		JSONUtil.put(jsonObj, "hasAttachment", false);
		JSONUtil.put(jsonObj, "isHtml", "undetermined");
		JSONUtil.put(jsonObj, "msgNum", String.valueOf(message.getMessageNumber()));
		JSONUtil.put(jsonObj, "read", read);
		JSONUtil.put(jsonObj, "subject", message.getSubject());
		JSONUtil.put(jsonObj, "uid", folder.getUID(message));

		if (isSummary) {
			//JSONUtil.put(jsonObj, "bodyPreview", _getContentPreview("", message));
			
			JSONUtil.put(jsonObj, "bodyPreview", StringPool.BLANK);
		}
		else {
			JSONUtil.put(jsonObj, "bcc",
				getAddressesAsString(message.getRecipients(RecipientType.BCC)));
			JSONUtil.put(jsonObj, "body",
				_getContent("", "", message, false));
			JSONUtil.put(jsonObj, "cc",
				getAddressesAsString(message.getRecipients(RecipientType.CC)));
			JSONUtil.put(jsonObj, "to",
				getAddressesAsString(message.getRecipients(RecipientType.TO)));
		}

		Date endTimer = new Date();

		int elapsedSeconds = 
			((endTimer.getMinutes() * 60) + endTimer.getSeconds()) - 
			((startTimer.getMinutes() * 60) + startTimer.getSeconds());

		if (isSummary) {
			System.out.println("get jsonmessage[" + folder.getUID(message) + "] SUMMARY took " + elapsedSeconds + " seconds");
		}
		else {
			System.out.println("get jsonmessage[" + folder.getUID(message) + "] took " + elapsedSeconds + " seconds");
		}

		return jsonObj;
    }

    public String getJsonMessageByNum(String folderName, int messageNum)
    	throws MessagingException {

    	IMAPFolder folder = (IMAPFolder)openFolder(folderName);

		Message message = folder.getMessage(messageNum);

		return getJsonMessage(folder, message, false).toString();
    }

    public String getJsonMessageByUid(String folderName, int messageUid)
    	throws MessagingException {

    	IMAPFolder folder = (IMAPFolder)openFolder(folderName);

		Message message = folder.getMessageByUID(messageUid);

		return getJsonMessage(folder, message, false).toString();
    }

    public String getJsonMessages(
    		IMAPFolder folder, String messageNumsToGet, 
    		String messageUidsToExclude)
    	throws MessagingException {
    	
    	int[] msgNums = GetterUtil.getIntegerValues(
			messageNumsToGet.split("\\s*,\\s*"));

    	int[] msgUids = GetterUtil.getIntegerValues(
			messageUidsToExclude.split("\\s*,\\s*"));

		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		if (msgNums.length != 0) {
    		for (int i = 0; i < msgNums.length; i++) {
    			Message message = folder.getMessage(msgNums[i]);
    			long msgUid = folder.getUID(message);
    			
    			// Skip message if it is in the exclude list
    			
    			if (msgUids.length != 0) {
    				for (int j = 0; j < msgUids.length; j++) {
    					if (msgUid == msgUids[j]) {

							try {
								jsonArray.put((int)msgUid, "");
							}
							catch (JSONException jsone) {
								if (_log.isWarnEnabled()) {
									_log.warn(jsone, jsone);
								}
							}
    						
    						continue;
    					}
    				}
    			}
    			
				try {
					jsonArray.put((int)msgUid, getJsonMessage(folder, message, true));
				}
				catch (JSONException jsone) {
					if (_log.isWarnEnabled()) {
						_log.warn(jsone, jsone);
					}
				}
    		}
    	}

		JSONUtil.put(jsonObj, "messages", jsonArray);

		return jsonObj.toString();
    }
    
    public String getJsonMessages(
    		IMAPFolder folder, int pageNum, int messagesPerPage, 
    		String messageUidsToExclude)
    	throws MessagingException {

		int totalMessages = folder.getMessageCount();
		int lastIndexInclusive =
			totalMessages - ((pageNum - 1) * messagesPerPage);
		int firstIndexInclusive = lastIndexInclusive - messagesPerPage + 1;

		if (firstIndexInclusive < 1) {
			firstIndexInclusive = 1;
		}

		double totalPages = Math.ceil((double)totalMessages / messagesPerPage);
		
    	int[] messageUids = GetterUtil.getIntegerValues(
			messageUidsToExclude.split("\\s*,\\s*"));
		
		// Create Json object

		JSONObject jsonObj = new JSONObject();

		JSONArray jsonArray = new JSONArray();

		JSONUtil.put(jsonObj, "totalPages", (int)totalPages);
		JSONUtil.put(jsonObj, "totalMessages", totalMessages);
		JSONUtil.put(jsonObj, "begin", firstIndexInclusive);
		JSONUtil.put(jsonObj, "end", lastIndexInclusive);
		JSONUtil.put(jsonObj, "pageNum", pageNum);
		JSONUtil.put(jsonObj, "messagesPerPage", messagesPerPage);

		Message[] messages = folder.getMessages(
			firstIndexInclusive, lastIndexInclusive);

		// Convert all messages into Json Objects

		for (int i = messages.length - 1; i >= 0 ; i--) {
			Message message = messages[i];
			long messageUid = folder.getUID(message);
			
			// Skip message if it is in the exclude list
			
//			if (messageUids.length != 0) {
//				for (int j = 0; j < messageUids.length; j++) {
//					if (messageUid == messageUids[j])  {
//						continue;
//					}
//				}
//			}
			
			// Otherwise, add to list
			
			jsonArray.put(getJsonMessage(folder, message, true));
		}

		JSONUtil.put(jsonObj, "messages", jsonArray);

		return jsonObj.toString();
    }

    public String getJsonMessagesBySearch(
    		IMAPFolder folder, int pageNum, int messagesPerPage,
    		String searchString)
    	throws Exception {

    	SearchTerm st = _getSearchTerm(searchString);

    	// TODO: cache search results for better performance

		Message[] messages = folder.search(st);

		int totalMessages = messages.length;
		int firstIndexInclusive = (pageNum - 1) * messagesPerPage;
		int lastIndexInclusive = firstIndexInclusive + messagesPerPage - 1;

		if (lastIndexInclusive >= totalMessages) {
			lastIndexInclusive = totalMessages - 1;
		}

		double totalPages = Math.ceil((double)totalMessages / messagesPerPage);

		// Create Json object

		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		JSONUtil.put(jsonObj, "totalPages", totalPages);
		JSONUtil.put(jsonObj, "totalMessages", totalMessages);
		JSONUtil.put(jsonObj, "begin", firstIndexInclusive);
		JSONUtil.put(jsonObj, "end", lastIndexInclusive);
		JSONUtil.put(jsonObj, "pageNum", pageNum);
		JSONUtil.put(jsonObj, "messagesPerPage", messagesPerPage);

		// Convert all messages into Json Objects

		for (int i = firstIndexInclusive; i <= lastIndexInclusive; i++) {	
			Message message = messages[i];

			jsonArray.put(getJsonMessage(folder, message, true));
		}

		JSONUtil.put(jsonObj, "messages", jsonArray);

		return jsonObj.toString();
	}

    public Message getMessageByUid(String folderName, int messageUid)
		throws MessagingException {

		IMAPFolder folder = (IMAPFolder)openFolder(folderName);

		return folder.getMessageByUID(messageUid);
	}

	public Message getMessageByUid(Folder folder, int messageUid)
		throws MessagingException {

		return ((IMAPFolder)folder).getMessageByUID(messageUid);
	}

    public void markMessagesAsReadByUid(
    		String folderName, String messageUidsCsv, boolean isRead)
    	throws MessagingException {

		IMAPFolder folder = (IMAPFolder)openFolder(folderName);

    	markMessagesSeenByUid(folder, messageUidsCsv, isRead);
    }

    public void markMessagesSeenByUid(
    		Folder folder, String messageUidsCsv, boolean isRead)
    	throws MessagingException {

    	try {
        	int[] messageUids = GetterUtil.getIntegerValues(
        		messageUidsCsv.split("\\s*,\\s*"));

        	if (!folder.isOpen()) {
        		folder.open(Folder.READ_WRITE);
        	}

        	for (int i = 0; i < messageUids.length; i++) {
        		try {
            		Message message = getMessageByUid(folder, messageUids[i]);

        			message.setFlag(Flags.Flag.SEEN, isRead);
        		}
        		catch (MessagingException me) {
        			_log.error(me);
        		}
        	}

			folder.close(true);
		}
		catch (MessagingException ex) {
		    System.out.println(ex.getMessage());
        }
	}

    /**
     * Gets the specified folder (opened)
     *
     * @param folderName
     * @return
     * @throws Exception
     */
    public Folder openFolder(String folderName) throws MessagingException {

        // Open the Folder
        Folder folder = _getStore().getDefaultFolder();

        folder = folder.getFolder(folderName);

        if (folder == null) {
            return null;
        }

        // try to open read/write and if that fails try read-only
        try {
            folder.open(Folder.READ_WRITE);
        }
        catch (MessagingException ex) {
            folder.open(Folder.READ_ONLY);
        }

        return folder;
    }

    public void sendForward(
    		Message msg, int fromAccountId, String recipientsTo,
			String recipientsCc, String recipientsBcc, String subject,
    		String content, Multipart mp)
    	throws MessagingException {

		// Create the message to forward

		Message forward = new MimeMessage(_getSession());

		// Create multi-part to combine the parts

		if (mp == null) {
			mp = new MimeMultipart();
		}

		// Create and fill part for the forwarded content

		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setDataHandler(msg.getDataHandler());

		// Add part to multi part

		mp.addBodyPart(messageBodyPart);

    	_send(forward, fromAccountId, recipientsTo, recipientsCc, recipientsBcc,
    		subject, content, mp);
    }

    public void sendNew(
    		int fromAccountId, String recipientsTo, String recipientsCc,
    		String recipientsBcc, String subject, String content, Multipart mp)
    	throws MessagingException {

    	// Instantiate a message

		Message msg = new MimeMessage(_getSession());

    	_send(msg, fromAccountId, recipientsTo, recipientsCc, recipientsBcc,
    		subject, content, mp);
    }

    public void sendReply(
    		Message msg, int fromAccountId, String recipientsTo,
			String recipientsCc, String recipientsBcc, String subject,
    		String content, Multipart mp)
    	throws MessagingException {

    	MimeMessage reply = (MimeMessage)msg.reply(false);

    	_send(reply, fromAccountId, recipientsTo, recipientsCc, recipientsBcc,
    		subject, content, mp);
    }

	private String _getContent(
			String messageContent, String contentPath, Part messagePart, 
			boolean isPreview)
		throws MessagingException {

		try {
			String contentType = messagePart.getContentType().toLowerCase();
			
			if (messagePart.getContent() instanceof Multipart) {
				Multipart multipart = (Multipart)messagePart.getContent();

				// If "multipart/alternative", only get selective parts

				if (contentType.startsWith(
						ContentTypes.MULTIPART_ALTERNATIVE)) {

					for (int i = 0; i < multipart.getCount(); i++) {
						Part curPart = multipart.getBodyPart(i);

						String partContentType =
							curPart.getContentType().toLowerCase();

						// Get text part if getting preview

						if (isPreview && partContentType.startsWith(
								ContentTypes.TEXT_PLAIN)) {

							// Only get content preview if there is none

							if (messageContent.equals(StringPool.BLANK)) {
								messageContent = _getContent(
									messageContent, "", curPart, isPreview);
							}

							break;
						}

						// Get html part if getting full body

						if (partContentType.startsWith(
								ContentTypes.TEXT_HTML)) {

							messageContent = _getContent(
								messageContent, "", curPart, isPreview);

							break;
						}
					}
				}
				else {

					// Otherwise, get all parts

					for (int i = 0; i < multipart.getCount(); i++) {
						Part curPart = multipart.getBodyPart(i);

						messageContent = _getContent(
							messageContent, contentPath + StringPool.PERIOD + i,
							curPart, isPreview);
					}
				}
			}
			else if (Validator.isNull(messagePart.getFileName())) {

				// Plain Text, HTML or Forwarded Message

				if (contentType.startsWith(ContentTypes.TEXT_PLAIN)) {
					messageContent +=
						((String)messagePart.getContent()).trim() + "\n\n";
				}
				else if (contentType.startsWith(ContentTypes.TEXT_HTML)) {
					if (isPreview) {
						messageContent +=
							_stripHtml((String)messagePart.getContent()) +
							"<HR/>";
					}
					else {
						messageContent
							+= ((String)messagePart.getContent()) + "<HR/>";
					}
				}
				else if (contentType.startsWith(ContentTypes.MESSAGE_RFC822)) {
					messageContent +=
						_getContent(
							messageContent, "", messagePart, isPreview);

					/*
					MailContent subContent = new MailContent();

					mailMessage = _getContent(
						(Part)messagePart.getContent(), mailMessage, subContent,
						contentPath + StringPool.PERIOD + 0);

					content.appendSubContent(subContent);
					*/
				}
				else {
					// Unknown Content Type: ignored
				}
			}
			else {
				// Attachment (ignore)

				contentPath = contentPath + ".attachment";
				
				messageContent += "<HR><a href=\"" + contentPath + "\">" + 
					messagePart.getFileName() + "</a>";

				//mailMessage.appendRemoteAttachment(
				//	_getRemoteAttachment(
				//		messagePart, contentPath + StringPool.PERIOD + -1));
			}

		}
		catch (IOException ioe) {
			_log.error(ioe.getMessage());

			//throw new MessagingException(ioe);
		}

		return messageContent;
	}

    private String _getContentPreview(String messageContent, Part messagePart)
		throws MessagingException {

		String fullContent = _getContent(messageContent, "", 
			messagePart, true);

		if (fullContent.length() > 80) {
			fullContent = fullContent.substring(0, 79) + "... ";
		}

		return fullContent;
	}

	/**
	 * Manually set the incoming connection settings
	 *
	 */
	private void _getIncomingStore(MailAccount mailAccount) {
		try {
			Properties props = new Properties();

			if (mailAccount.isMailSecure()) {
				props.setProperty("mail.imap.socketFactory.class", SSL_FACTORY);
			}

			props.setProperty("mail.imap.socketFactory.fallback", "false");
			props.setProperty("mail.imap.port", mailAccount.getMailInPort());
			props.setProperty(
				"mail.imap.socketFactory.port", mailAccount.getMailInPort());

			URLName url = new URLName(
				"imap", mailAccount.getMailInHostName(),
				Integer.parseInt(mailAccount.getMailInPort()), "",
				mailAccount.getUsername(), mailAccount.getPassword());

			Session session = Session.getInstance(props, null);

			Store store;

			if (mailAccount.isMailSecure()) {
				store = new IMAPSSLStore(session, url);
			}
			else {
				store = new IMAPStore(session, url);
			}

			store.connect();
			_setStore(store);
		}
		catch (MessagingException ex) {
			if (_log.isErrorEnabled()) {
				_log.error(ex.getMessage());
			}
        }
	}

	private Part _getMessagePart(Part part, String contentPath)
		throws MessagingException {
	
		int index = GetterUtil.getInteger(
			StringUtil.split(contentPath.substring(1), StringPool.PERIOD)[0]);
	
		try {
			if (part.getContent() instanceof Multipart) {
				String prefix = String.valueOf(index) + StringPool.PERIOD;
		
				Multipart multipart = (Multipart)part.getContent();
		
				for (int i = 0; i < multipart.getCount(); i++) {
					if (index == i) {
						return _getMessagePart(
							multipart.getBodyPart(i),
							contentPath.substring(prefix.length()));
					}
				}
			}
		
			return part;
		}
		catch (IOException ioe) {
			_log.error(ioe.getMessage());
			
			return null;
		}
	}
	
	/**
	 * Manually set the outgoing connection settings
	 *
	 */
	private Session _getOutgoingSession(MailAccount mailAccount) {

		// Properties

		Properties props = new Properties();
		props.put("mail.smtp.host", mailAccount.getMailOutHostName());
		props.put("mail.smtp.port", mailAccount.getMailOutPort());

		if (mailAccount.isMailSecure()) {
			props.put("mail.smtp.auth", "true");
			props.put(
				"mail.smtp.socketFactory.port", mailAccount.getMailOutPort());
			props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
			props.put("mail.smtp.socketFactory.fallback", "false");
		}

		props.put("mail.debug", "true");

		// Session

		Session session = Session.getDefaultInstance(props, null);

		session.setDebug(true);

		return session;
	}

	/**
	 * Returns a SearchTerm which requires that all terms in searchString
	 * must appear in the email
	 */
	private SearchTerm _getSearchTerm(String searchString) {

		String searchStrings[] = searchString.split("\\s");

		SearchTerm[] allOrTerms = new OrTerm[searchStrings.length];

		for (int i = 0; i < searchStrings.length; i++) {
			String tempSearchString = searchStrings[i];

			SearchTerm[] allEmailPartsTerm = {
				new FromStringTerm(tempSearchString),
				new RecipientStringTerm(
					Message.RecipientType.TO, tempSearchString),
				new RecipientStringTerm(
					Message.RecipientType.CC, tempSearchString),
				new RecipientStringTerm(
					Message.RecipientType.BCC, tempSearchString),
				new BodyTerm(tempSearchString),
				new SubjectTerm(tempSearchString)
			};

			allOrTerms[i] = new OrTerm(allEmailPartsTerm);
		}

		return new AndTerm(allOrTerms);
	}

    private Session _getSession() {
		if (_session == null) {
			_session = _getOutgoingSession(_defaultMailAccount);
		}

		return _session;
	}

    private Store _getStore() {
		if (_store == null) {
			_getIncomingStore(_defaultMailAccount);
		}

		return _store;
	}

	private static void _getFolders(List list, Folder[] folders) {
		for (int i = 0; i < folders.length; i++) {
			Folder folder = folders[i];

			try {
				int folderType = folder.getType();

				if ((folderType & IMAPFolder.HOLDS_MESSAGES) != 0) {
					list.add(folder);
				}

				if ((folderType & IMAPFolder.HOLDS_FOLDERS) != 0) {
					_getFolders(list, folder.list());
				}
			}
			catch (MessagingException me) {
				_log.error("Skipping IMAP folder because " + me.getMessage());
			}
		}
	}

	private void _send(
			Message msg, int fromAccountId, String recipientsTo,
			String recipientsCc, String recipientsBcc, String subject,
			String content, Multipart mp)
		throws MessagingException {

		MailAccount fromMailAccount = new MailAccount(_user, fromAccountId);

		//Set message attributes

		msg.setFrom(new InternetAddress(fromMailAccount.getEmailAddress()));

		if (!recipientsTo.trim().equalsIgnoreCase(StringPool.BLANK)) {
			msg.setRecipients(
				Message.RecipientType.TO, InternetAddress.parse(
					recipientsTo, false));
		}

		if (!recipientsCc.trim().equalsIgnoreCase(StringPool.BLANK)) {
			msg.setRecipients(
				Message.RecipientType.CC, InternetAddress.parse(
					recipientsCc, false));
		}

		if (!recipientsBcc.trim().equalsIgnoreCase(StringPool.BLANK)) {
			msg.setRecipients(
				Message.RecipientType.BCC, InternetAddress.parse(
					recipientsBcc, false));
		}

		msg.setSubject(subject);
		msg.setSentDate(new Date());

		if (mp != null) {
			// Add attachment
			msg.setContent(mp);
		}
		else {
			// Set message content
			msg.setText(content);
		}

		// Resets the header to reflect changes

		msg.saveChanges();

		//Send the message

		Transport t = _getOutgoingSession(fromMailAccount).getTransport("smtp");
		try {
			t.connect(
				fromMailAccount.getUsername(), fromMailAccount.getPassword());
			t.sendMessage(msg, msg.getAllRecipients());
		}
		finally {
			t.close();
		}
	}

	private void _setStore(Store store) {
		this._store = store;
	}

	private static String _stripHtml(String html) {
		html = html.replaceAll( "<[^>]+>", StringPool.BLANK);
		html = html.replaceAll( "[\r\n]+", StringPool.BLANK);

		return html;
	}

	private static Log _log = LogFactory.getLog(MailBoxManager.class);

}