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

import com.liferay.mail.model.MailAccount;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.util.JSONUtil;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPSSLStore;
import com.sun.mail.imap.IMAPStore;

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
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
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
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * <a href="MailBoxManager.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 *
 */
public class MailBoxManager {

	public static String getJSONAccounts(User user) throws MessagingException {
    	JSONObject jsonObj = new JSONObject();

		// Accounts

		JSONArray jsonArray = new JSONArray();

		JSONUtil.put(jsonObj, "accounts", jsonArray);

    	// Account 1

		JSONObject account1 = new JSONObject();

		JSONUtil.put(account1, "emailAddress", "liferay.mail.1@gmail.com");
		JSONUtil.put(account1, "accountId", "0");

		jsonArray.put(account1);

		// Account 2

		JSONObject account2 = new JSONObject();

		JSONUtil.put(account2, "emailAddress", "liferay.mail.2@gmail.com");
		JSONUtil.put(account2, "accountId", "1");

		jsonArray.put(account2);

		// Account 3

		JSONObject account3 = new JSONObject();

		JSONUtil.put(account3, "emailAddress", "liferay.mail.3@gmail.com");
		JSONUtil.put(account3, "accountId", "2");

		jsonArray.put(account3);

		// Accounts

		return jsonObj.toString();
	}

    public MailBoxManager(User user, int accountId) {
    	_user = user;
    	_mailAccount = new MailAccount(user, accountId);
    }

	public void createFolder(String folderName) throws Exception {
		Folder newFolder = getStore().getFolder(folderName);

		if (!newFolder.exists()) {
			newFolder.create(Folder.HOLDS_MESSAGES);
		}
	}

    public void deleteMessage(Folder folder, Message message) {
        try {
        	if (!folder.isOpen()) {
        		folder.open(Folder.READ_WRITE);
        	}

			message.setFlag(Flags.Flag.DELETED, true);

			folder.close(true);
		}
		catch (MessagingException me) {
			_log.error(me, me);
        }
	}

    public void deleteMessagesByUids(String folderName, String messageUids)
    	throws MessagingException {

    	Folder folder = getFolder(folderName);

    	deleteMessagesByUids(folder, messageUids);
    }

    public void deleteMessagesByUids(Folder folder, String messageUids) {
        try {
        	int[] messageUidsArray = GetterUtil.getIntegerValues(
        		messageUids.split("\\s*,\\s*"));

        	if (!folder.isOpen()) {
        		folder.open(Folder.READ_WRITE);
        	}

        	for (int messageUid : messageUidsArray) {
        		try {
            		Message message = getMessageByUid(folder, messageUid);

        			message.setFlag(Flags.Flag.DELETED, true);
        		}
        		catch (MessagingException me) {
        			_log.error(me, me);
        		}
        	}

			folder.close(true);
		}
		catch (MessagingException me) {
			_log.error(me, me);
        }
	}

	public String getAddresses(Address[] addresses) {
        StringBuilder sb = new StringBuilder();

		if (addresses == null) {
			return StringPool.BLANK;
		}

        try {
			for (int i = 0; i < addresses.length; i++) {
				InternetAddress address = (InternetAddress)addresses[i];

				if (i != 0) {
					sb.append(StringPool.COMMA);
				}

				sb.append(address.getAddress());
			}
        }
        catch (Exception e) {
            _log.error(e, e);

            return null;
        }

		return sb.toString();
	}

	public Part getAttachment(
			String folderName, int messageUid, String contentPath)
		throws MessagingException {

		Message message = getMessageByUid(folderName, messageUid);

		return getMessagePart(message, contentPath);
	}

    public Folder getFolder(String folderName) throws MessagingException {
		Folder folder = getStore().getDefaultFolder();

        folder = folder.getFolder(folderName);

        if (folder == null) {
			_log.error("Invalid folder " + folderName);
        }

        return folder;
	}

	public List getFolders() throws MessagingException {
		Store store = getStore();

		IMAPFolder rootFolder = (IMAPFolder)store.getDefaultFolder();

		List allFolders = new ArrayList();

		getFolders(allFolders, rootFolder.list());

		return allFolders;
	}

    public JSONObject getJSONFolder(IMAPFolder folder)
		throws MessagingException {

    	JSONObject jsonObj = new JSONObject();

    	if (folder.getType() != Folder.HOLDS_FOLDERS) {
    		folder.open(Folder.READ_ONLY);

    		JSONUtil.put(jsonObj, "name", folder.getFullName());

    		folder.close(false);

    		return jsonObj;
    	}

    	return null;
    }

    public String getJSONFolders() throws MessagingException {
		JSONObject jsonObj = new JSONObject();

		JSONArray jsonArray = new JSONArray();

		JSONUtil.put(jsonObj, "folders", jsonArray);

		List<IMAPFolder> folders = getFolders();

		for (IMAPFolder folder : folders) {
			if (folder.getType() != Folder.HOLDS_FOLDERS) {
				jsonArray.put(getJSONFolder(folder));
			}
		}

		JSONUtil.put(jsonObj, "folderCount", folders.size());

		return jsonObj.toString();
    }

    public JSONObject getJSONMessage(
    		IMAPFolder folder, Message message, boolean preview)
    	throws MessagingException {

		String read = "read";

		if (!message.isSet(Flags.Flag.SEEN)) {
			read = "unread";
		}

		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy HH:mm");

		JSONObject jsonObj = new JSONObject();

		JSONUtil.put(jsonObj, "uid", folder.getUID(message));
		JSONUtil.put(jsonObj, "messageNumber", message.getMessageNumber());
		JSONUtil.put(jsonObj, "date", sdf.format(message.getSentDate()));
		JSONUtil.put(jsonObj, "from", getAddresses(message.getFrom()));
		JSONUtil.put(jsonObj, "subject", message.getSubject());
		JSONUtil.put(jsonObj, "html", false);
		JSONUtil.put(jsonObj, "read", read);

		if (preview) {
			JSONUtil.put(jsonObj, "bodyPreview", StringPool.BLANK);
		}
		else {
			StringBuilder sb = new StringBuilder();

			List<Object[]> attachments = new ArrayList<Object[]>();

			getBody(sb, StringPool.BLANK, message, attachments, false);

			JSONUtil.put(
				jsonObj, "to",
				getAddresses(message.getRecipients(RecipientType.TO)));
			JSONUtil.put(
				jsonObj, "cc",
				getAddresses(message.getRecipients(RecipientType.CC)));
			JSONUtil.put(
				jsonObj, "bcc",
				getAddresses(message.getRecipients(RecipientType.BCC)));
			JSONUtil.put(jsonObj, "body", sb.toString());
			JSONUtil.put(
				jsonObj, "attachments", getJSONAttachments(attachments));
		}

		return jsonObj;
    }

    public String getJSONMessageByNumber(String folderName, int messageNumber)
    	throws MessagingException {

    	IMAPFolder folder = (IMAPFolder)openFolder(folderName);

		Message message = folder.getMessage(messageNumber);

		return getJSONMessage(folder, message, false).toString();
    }

    public String getJSONMessageByUid(String folderName, int messageUid)
    	throws MessagingException {

    	IMAPFolder folder = (IMAPFolder)openFolder(folderName);

		Message message = folder.getMessageByUID(messageUid);

		return getJSONMessage(folder, message, false).toString();
    }

    public String getJSONMessages(
    		IMAPFolder folder, String messageNumbersToInclude,
    		String messageUidsToExclude)
    	throws MessagingException {

    	int[] messageNumbersArray = GetterUtil.getIntegerValues(
			messageNumbersToInclude.split("\\s*,\\s*"));
		int[] messageUidsArray = GetterUtil.getIntegerValues(
			messageUidsToExclude.split("\\s*,\\s*"));

		JSONObject jsonObj = new JSONObject();

		JSONArray jsonArray = new JSONArray();

		JSONUtil.put(jsonObj, "messages", jsonArray);

		for (int messageNumber : messageNumbersArray) {
			Message message = folder.getMessage(messageNumber);

			long messageUid = folder.getUID(message);

			// Skip message if it is in the exclude list

			try {
				for (int curMessageUid : messageUidsArray) {
					if (curMessageUid == messageUid) {
						jsonArray.put((int)messageUid, StringPool.BLANK);
						continue;
					}
				}

				jsonArray.put(
					(int)messageUid, getJSONMessage(folder, message, true));
			}
			catch (JSONException jsone) {
				if (_log.isWarnEnabled()) {
					_log.warn(jsone, jsone);
				}
			}
		}

		return jsonObj.toString();
    }

    public String getJSONMessages(
    		IMAPFolder folder, int pageNumber, int messagesPerPage,
    		String messageUidsToExclude)
    	throws MessagingException {

		int messageCount = folder.getMessageCount();
		int end = messageCount - ((pageNumber - 1) * messagesPerPage);
		int begin = end - messagesPerPage + 1;

		if (begin < 1) {
			begin = 1;
		}

		double pageCount = Math.ceil((double)messageCount / messagesPerPage);

    	int[] messageUids = GetterUtil.getIntegerValues(
			messageUidsToExclude.split("\\s*,\\s*"));

		// Create JSON object

		JSONObject jsonObj = new JSONObject();

		JSONArray jsonArray = new JSONArray();

		JSONUtil.put(jsonObj, "messages", jsonArray);

		JSONUtil.put(jsonObj, "pageCount", (int)pageCount);
		JSONUtil.put(jsonObj, "messageCount", messageCount);
		JSONUtil.put(jsonObj, "begin", begin);
		JSONUtil.put(jsonObj, "end", end);
		JSONUtil.put(jsonObj, "pageNumber", pageNumber);
		JSONUtil.put(jsonObj, "messagesPerPage", messagesPerPage);

		Message[] messages = folder.getMessages(begin, end);

		// Convert all messages into JSON objects

		for (Message message : messages) {
			long messageUid = folder.getUID(message);

			// Otherwise, add to list

			jsonArray.put(getJSONMessage(folder, message, true));
		}

		return jsonObj.toString();
    }

    public String getJSONMessagesBySearch(
    		IMAPFolder folder, int pageNumber, int messagesPerPage,
    		String searchString, String messageUidsToExclude)
    	throws Exception {

    	SearchTerm st = getSearchTerm(searchString);

		Message[] messages = folder.search(st);

		int messageCount = messages.length;
		int begin = (pageNumber - 1) * messagesPerPage;
		int end = begin + messagesPerPage - 1;

		if (end >= messageCount) {
			end = messageCount - 1;
		}

		double pageCount = Math.ceil((double)messageCount / messagesPerPage);

		// Create JSON object

		JSONObject jsonObj = new JSONObject();

		JSONArray jsonArray = new JSONArray();

		JSONUtil.put(jsonObj, "messages", jsonArray);

		JSONUtil.put(jsonObj, "pageCount", pageCount);
		JSONUtil.put(jsonObj, "messageCount", messageCount);
		JSONUtil.put(jsonObj, "begin", begin);
		JSONUtil.put(jsonObj, "end", end);
		JSONUtil.put(jsonObj, "pageNumber", pageNumber);
		JSONUtil.put(jsonObj, "messagesPerPage", messagesPerPage);

		// Convert all messages into JSON objects

		for (int i = begin; i <= end; i++) {
			Message message = messages[i];

			jsonArray.put(getJSONMessage(folder, message, true));
		}

		return jsonObj.toString();
	}

    public Message getMessageByUid(String folderName, int messageUid)
		throws MessagingException {

		IMAPFolder folder = (IMAPFolder)openFolder(folderName);

		return getMessageByUid(folder, messageUid);
	}

	public Message getMessageByUid(Folder folder, int messageUid)
		throws MessagingException {

		return ((IMAPFolder)folder).getMessageByUID(messageUid);
	}

    public List getMessageUids(String folderName, String messageNumbers)
    	throws MessagingException {

		IMAPFolder folder = (IMAPFolder)openFolder(folderName);

		return getMessageUids(folder, messageNumbers);
	}

    public List getMessageUids(IMAPFolder folder, String messageNumbers)
    	throws MessagingException {

    	int[] messageNumbersArray = GetterUtil.getIntegerValues(
			messageNumbers.split("\\s*,\\s*"));

		List messageUids = new ArrayList();

		for (int messageNumber : messageNumbersArray) {
			Message message = folder.getMessage(messageNumber);

			messageUids.add(folder.getUID(message));
		}

		return messageUids;
    }

    public void markMessagesAsRead(
			String folderName, String messageUids, boolean read)
    	throws MessagingException {

		IMAPFolder folder = (IMAPFolder)openFolder(folderName);

    	markMessagesAsRead(folder, messageUids, read);
    }

    public void markMessagesAsRead(
    		Folder folder, String messageUids, boolean read)
    	throws MessagingException {

    	try {
        	int[] messageUidsArray = GetterUtil.getIntegerValues(
        		messageUids.split("\\s*,\\s*"));

        	if (!folder.isOpen()) {
        		folder.open(Folder.READ_WRITE);
        	}

        	for (int messageUid : messageUidsArray) {
        		try {
            		Message message = getMessageByUid(folder, messageUid);

        			message.setFlag(Flags.Flag.SEEN, read);
        		}
        		catch (MessagingException me) {
        			_log.error(me, me);
        		}
        	}

			folder.close(true);
		}
		catch (MessagingException me) {
			_log.error(me, me);
        }
	}

    public Folder openFolder(String folderName) throws MessagingException {
		Folder folder = getStore().getDefaultFolder();

        folder = folder.getFolder(folderName);

        if (folder == null) {
            return null;
        }

		try {
            folder.open(Folder.READ_WRITE);
        }
        catch (MessagingException me) {
            folder.open(Folder.READ_ONLY);
        }

        return folder;
    }

    public void sendForward(
    		Message message, int fromAccountId, String to, String cc,
			String bcc, String subject, String content, Multipart multipart)
    	throws MessagingException {

		// Create the message to forward

		Message forward = new MimeMessage(getSession());

		// Create multipart to combine the parts

		if (multipart == null) {
			multipart = new MimeMultipart();
		}

		// Create and fill part for the forwarded content

		BodyPart messageBodyPart = new MimeBodyPart();

		messageBodyPart.setDataHandler(message.getDataHandler());

		// Add part to multipart

		multipart.addBodyPart(messageBodyPart);

    	send(forward, fromAccountId, to, cc, bcc, subject, content, multipart);
    }

    public void sendNew(
    		int fromAccountId, String to, String cc, String bcc, String subject,
			String content, Multipart multipart)
    	throws MessagingException {

    	// Instantiate a message

		Message message = new MimeMessage(getSession());

    	send(message, fromAccountId, to, cc, bcc, subject, content, multipart);
    }

    public void sendReply(
    		Message message, int fromAccountId, String to, String cc,
			String bcc, String subject, String content, Multipart multipart)
    	throws MessagingException {

    	MimeMessage reply = (MimeMessage)message.reply(false);

    	send(reply, fromAccountId, to, cc, bcc, subject, content, multipart);
    }

	protected void getBody(
			StringBuilder sb, String contentPath, Part messagePart,
			List<Object[]> attachments, boolean preview)
		throws MessagingException {

		try {
			String contentType = messagePart.getContentType().toLowerCase();

			if (messagePart.getContent() instanceof Multipart) {

				// Multipart

				Multipart multipart = (Multipart)messagePart.getContent();

				for (int i = 0; i < multipart.getCount(); i++) {
					Part curPart = multipart.getBodyPart(i);

					if (getBodyMulitipart(
							contentType, curPart,
							contentPath + StringPool.PERIOD + i, sb,
							attachments, preview)) {

						break;
					}
				}
			}
			else if (Validator.isNull(messagePart.getFileName())) {

				// Plain text, HTML or forwarded message

				if (contentType.startsWith(ContentTypes.TEXT_PLAIN)) {
					sb.append(messagePart.getContent());
					sb.append("\n\n");
				}
				else if (contentType.startsWith(ContentTypes.TEXT_HTML)) {
					if (preview) {
						sb.append(stripHtml((String)messagePart.getContent()));
					}
					else {
						sb.append((String)messagePart.getContent());
						sb.append("<hr />");
					}
				}
				else if (contentType.startsWith(ContentTypes.MESSAGE_RFC822)) {
					getBody(
						sb, StringPool.BLANK, messagePart, attachments,
						preview);
				}
			}
			else {

				// Attachment

				if (!preview) {
					attachments.add(
						new Object[] {contentPath, messagePart.getFileName()});
				}
			}
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}
	}

	protected boolean getBodyMulitipart(
			String contentType, Part curPart, String contentPath,
			StringBuilder sb, List<Object[]> attachments, boolean preview)
		throws MessagingException {

		if (contentType.startsWith(ContentTypes.MULTIPART_ALTERNATIVE)) {
			String partContentType = curPart.getContentType().toLowerCase();

			if (preview &&
				partContentType.startsWith(ContentTypes.TEXT_PLAIN)) {

				getBody(sb, StringPool.BLANK, curPart, attachments, preview);

				return true;
			}

			if (partContentType.startsWith(ContentTypes.TEXT_HTML)) {
				getBody(sb, StringPool.BLANK, curPart, attachments, preview);

				return true;
			}
		}
		else {
			getBody(sb, contentPath, curPart, attachments, preview);
		}

		return false;
	}

	protected String getBodyPreview(String messageContent, Part messagePart)
		throws MessagingException {

		StringBuilder sb = new StringBuilder();

		getBody(sb, StringPool.BLANK, messagePart, null, true);

		if (sb.length() < 80) {
			return sb.toString();
		}
		else {
			return sb.substring(0, 80).concat("...");
		}
	}

    protected void getFolders(List list, Folder[] folders) {
		for (Folder folder : folders) {
			try {
				int folderType = folder.getType();

				if ((folderType & IMAPFolder.HOLDS_MESSAGES) != 0) {
					list.add(folder);
				}

				if ((folderType & IMAPFolder.HOLDS_FOLDERS) != 0) {
					getFolders(list, folder.list());
				}
			}
			catch (MessagingException me) {
				_log.error("Skipping IMAP folder: " + me.getMessage());
			}
		}
	}

	protected JSONArray getJSONAttachments(List<Object[]> attachments)
		throws MessagingException {

		JSONArray jsonArray = new JSONArray();

		for (Object[] attachment : attachments) {
			JSONArray tempJsonArray = new JSONArray();

			tempJsonArray.put(attachment[0]);
			tempJsonArray.put(attachment[1]);

			jsonArray.put(tempJsonArray);
		}

		return jsonArray;
	}

	protected void getIncomingStore(MailAccount mailAccount) {
		try {
			Properties props = new Properties();

			URLName url = new URLName(
				"imap", mailAccount.getMailInHostName(),
				GetterUtil.getInteger(mailAccount.getMailInPort()),
				StringPool.BLANK, mailAccount.getUsername(),
				mailAccount.getPassword());

			props.setProperty("mail.imap.port", mailAccount.getMailInPort());

			if (mailAccount.isMailSecure()) {
				props.setProperty(
					"mail.imap.socketFactory.port",
					mailAccount.getMailInPort());
				props.setProperty(
					"mail.imap.socketFactory.class", _SSL_FACTORY);
				props.setProperty("mail.imap.socketFactory.fallback", "false");
			}

			Session session = Session.getInstance(props, null);

			Store store = null;

			if (mailAccount.isMailSecure()) {
				store = new IMAPSSLStore(session, url);
			}
			else {
				store = new IMAPStore(session, url);
			}

			store.connect();

			setStore(store);
		}
		catch (MessagingException me) {
			if (_log.isErrorEnabled()) {
				_log.error(me, me);
			}
        }
	}

	protected Part getMessagePart(Part part, String contentPath)
		throws MessagingException {

		int index = GetterUtil.getInteger(
			StringUtil.split(contentPath.substring(1), StringPool.PERIOD)[0]);

		try {
			if (part.getContent() instanceof Multipart) {
				String prefix = String.valueOf(index) + StringPool.PERIOD;

				Multipart multipart = (Multipart)part.getContent();

				for (int i = 0; i < multipart.getCount(); i++) {
					if (index == i) {
						return getMessagePart(
							multipart.getBodyPart(i),
							contentPath.substring(prefix.length()));
					}
				}
			}

			return part;
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			return null;
		}
	}

	protected Session getOutgoingSession(MailAccount mailAccount) {
		Properties props = new Properties();

		props.put("mail.smtp.host", mailAccount.getMailOutHostName());
		props.put("mail.smtp.port", mailAccount.getMailOutPort());

		if (mailAccount.isMailSecure()) {
			props.put(
				"mail.smtp.socketFactory.port", mailAccount.getMailOutPort());
			props.put("mail.smtp.socketFactory.class", _SSL_FACTORY);
			props.put("mail.smtp.socketFactory.fallback", "false");
			props.put("mail.smtp.auth", "true");
		}

		props.put("mail.debug", "true");

		Session session = Session.getDefaultInstance(props, null);

		session.setDebug(true);

		return session;
	}

	protected SearchTerm getSearchTerm(String searchString) {
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

	protected Session getSession() {
		if (_session == null) {
			_session = getOutgoingSession(_mailAccount);
		}

		return _session;
	}

    protected Store getStore() {
		if (_store == null) {
			getIncomingStore(_mailAccount);
		}

		return _store;
	}

	protected void send(
			Message message, int fromAccountId, String to, String cc,
			String bcc, String subject, String content, Multipart multipart)
		throws MessagingException {

		MailAccount fromMailAccount = new MailAccount(_user, fromAccountId);

		message.setSentDate(new Date());
		message.setFrom(new InternetAddress(fromMailAccount.getEmailAddress()));

		if (Validator.isNotNull(to)) {
			message.setRecipients(
				Message.RecipientType.TO, InternetAddress.parse(to, false));
		}

		if (Validator.isNotNull(cc)) {
			message.setRecipients(
				Message.RecipientType.CC, InternetAddress.parse(cc, false));
		}

		if (Validator.isNotNull(bcc)) {
			message.setRecipients(
				Message.RecipientType.BCC, InternetAddress.parse(bcc, false));
		}

		message.setSubject(subject);

		if (multipart != null) {
			message.setContent(multipart);
		}
		else {
			message.setText(content);
		}

		message.saveChanges();

		Session session = getOutgoingSession(fromMailAccount);

		Transport transport = session.getTransport("smtp");

		try {
			transport.connect(
				fromMailAccount.getUsername(), fromMailAccount.getPassword());

			transport.sendMessage(message, message.getAllRecipients());
		}
		finally {
			transport.close();
		}
	}

	protected void setStore(Store store) {
		_store = store;
	}

	protected String stripHtml(String html) {
		html = html.replaceAll( "<[^>]+>", StringPool.BLANK);
		html = html.replaceAll( "[\r\n]+", StringPool.BLANK);

		return html;
	}

	public static final String _SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

	private static Log _log = LogFactory.getLog(MailBoxManager.class);

	private User _user;
	private MailAccount _mailAccount;
    private Session _session = null;
    private Store _store = null;

}