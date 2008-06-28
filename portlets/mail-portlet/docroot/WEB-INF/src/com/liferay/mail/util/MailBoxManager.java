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
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.util.JSONUtil;
import com.liferay.util.portlet.PortletProps;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPSSLStore;
import com.sun.mail.imap.IMAPStore;

import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

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
import org.json.JSONObject;

/**
 *
 * <a href="MailBoxManager.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 *
 */
public class MailBoxManager {

	public MailBoxManager(User user, String emailAddress) {
		_user = user;
		_mailAccount = new MailAccount(user, emailAddress);
	}

	public MailBoxManager(
		User user, String emailAddress, boolean initialized,
		String mailInHostName, String mailInPort, String mailOutHostName,
		String mailOutPort, boolean mailSecure, String password,
		String username) {

		_user = user;
		_mailAccount = new MailAccount(
			user, emailAddress, initialized, mailInHostName, mailInPort,
			mailOutHostName, mailOutPort, mailSecure, password, username);
	}

	public void createFolder(String folderName) throws Exception {
		Folder newFolder = getStore().getFolder(folderName);

		if (!newFolder.exists()) {
			newFolder.create(Folder.HOLDS_MESSAGES);
		}
	}

	public JSONObject deleteAccountFromDisk() {
		String filePath = MailDiskManager.getAccountFilePath(
			_user, _mailAccount.getEmailAddress());

		FileUtil.delete(filePath);

		JSONObject jsonObj = new JSONObject();

		JSONUtil.put(jsonObj, "success", true);

		return jsonObj;
	}

	public JSONObject deleteMessagesByUids(
			String folderName, String messageUids)
		throws MessagingException {

		Folder folder = getFolder(folderName);

		try {
			long[] messageUidsArray = GetterUtil.getLongValues(
				messageUids.split("\\s*,\\s*"));

			if (!folder.isOpen()) {
				folder.open(Folder.READ_WRITE);
			}

			for (long messageUid : messageUidsArray) {
				try {

					// Delete from server

					Message message = getMessageByUid(folder, messageUid);

					message.setFlag(Flags.Flag.DELETED, true);

					// Delete from local disk

					String messagePath = MailDiskManager.getMessagePath(
						_user, _mailAccount.getEmailAddress(), folderName,
						String.valueOf(messageUid));

					FileUtil.deltree(messagePath);
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

		JSONObject jsonObj = new JSONObject();

		JSONUtil.put(jsonObj, "success", true);

		return jsonObj;
	}

	public Part getAttachment(
			String folderName, int messageUid, String contentPath)
		throws MessagingException {

		Message message = getMessageByUid(folderName, messageUid);

		return getMessagePart(message, contentPath);
	}

	public JSONObject markMessagesAsRead(
			String folderName, String messageUids, boolean read)
		throws MessagingException {

		IMAPFolder folder = (IMAPFolder)openFolder(folderName);

		long[] messageUidsArray = GetterUtil.getLongValues(
			messageUids.split("\\s*,\\s*"));

		if (!folder.isOpen()) {
			folder.open(Folder.READ_WRITE);
		}

		for (long messageUid : messageUidsArray) {
			try {

				// Update message on server

				Message message = getMessageByUid(folder, messageUid);

				message.setFlag(Flags.Flag.SEEN, read);

				// Update message on local disk

				updateJSONMessageField(
					_user, _mailAccount, folderName, String.valueOf(messageUid),
					"read", String.valueOf(read));
			}
			catch (IOException ioe) {
				_log.error(ioe, ioe);
			}
			catch (MessagingException me) {
				_log.error(me, me);
			}
		}

		folder.close(true);

		JSONObject jsonObj = new JSONObject();

		JSONUtil.put(jsonObj, "success", true);

		return jsonObj;
	}

	public JSONObject sendMessage(
			String messageType, String folderName, long messageUid,
			String fromEmailAddress, String to, String cc, String bcc,
			String subject, Multipart multipart)
		throws MessagingException {

		JSONObject jsonObj = new JSONObject();

		Message message = null;

		if (messageType.equalsIgnoreCase("new")) {

			// Instantiate a message

			message = new MimeMessage(getSession());

			send(message, fromEmailAddress, to, cc, bcc, subject, multipart);

			JSONUtil.put(jsonObj, "success", true);
		}
		else {
			Message oldMessage = getMessageByUid(folderName, messageUid);

			if (messageType.equalsIgnoreCase("forward")) {

				// Create the message to forward

				message = new MimeMessage(getSession());

				// Create multipart to combine the parts

				if (multipart == null) {
					multipart = new MimeMultipart();
				}

				// Create and fill part for the forwarded content

				BodyPart messageBodyPart = new MimeBodyPart();

				messageBodyPart.setDataHandler(message.getDataHandler());

				// Add part to multipart

				multipart.addBodyPart(messageBodyPart);

				send(
					oldMessage, fromEmailAddress, to, cc, bcc, subject,
					multipart);

				JSONUtil.put(jsonObj, "success", true);
			}
			else if (messageType.equalsIgnoreCase("reply")) {
				message = (MimeMessage)oldMessage.reply(false);

				send(
					message, fromEmailAddress, to, cc, bcc, subject, multipart);

				JSONUtil.put(jsonObj, "success", true);
			}
			else {
				JSONUtil.put(jsonObj, "success", false);
			}
		}

		return jsonObj;
	}

	public JSONObject storeAccountToDisk() {
		try {
			JSONObject jsonObj = new JSONObject();

			String filePath = MailDiskManager.getAccountFilePath(
				_user, _mailAccount.getEmailAddress());

			JSONUtil.put(
				jsonObj, "emailAddress", _mailAccount.getEmailAddress());
			JSONUtil.put(jsonObj, "initialized", _mailAccount.isInitialized());
			JSONUtil.put(
				jsonObj, "mailInHostName", _mailAccount.getMailInHostName());
			JSONUtil.put(jsonObj, "mailInPort", _mailAccount.getMailInPort());
			JSONUtil.put(
				jsonObj, "mailOutHostName", _mailAccount.getMailOutHostName());
			JSONUtil.put(jsonObj, "mailOutPort", _mailAccount.getMailOutPort());
			JSONUtil.put(jsonObj, "mailSecure", _mailAccount.isMailSecure());
			JSONUtil.put(jsonObj, "password", _mailAccount.getPassword());
			JSONUtil.put(jsonObj, "username", _mailAccount.getUsername());

			FileUtil.write(filePath, jsonObj.toString());
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			return null;
		}

		JSONObject jsonObj = new JSONObject();

		JSONUtil.put(jsonObj, "success", true);

		return jsonObj;
	}

	public JSONObject syncronizeAccount() throws MessagingException {
		List<Folder> folders = getFolders();

		for (Folder folder : folders) {
			syncronizeFolder(folder);
		}

		JSONObject jsonObj = new JSONObject();

		JSONUtil.put(jsonObj, "success", true);

		return jsonObj;
	}

	public void syncronizeFolder(Folder folder) throws MessagingException {

		// Check if folder has been initialized

		JSONObject jsonObj = MailDiskManager.getJSONFolder(
			_user, _mailAccount.getEmailAddress(), folder.getFullName());

		long latestMessageUid = MailDiskManager.getNewestStoredMessageUID(
			_user, _mailAccount.getEmailAddress(), folder.getFullName());

		boolean initialized = false;

		if ((jsonObj != null) && jsonObj.optBoolean("initialized")) {
			initialized = true;
		}

		folder = openFolder(folder);

		int messageCount = folder.getMessageCount();

		Message[] messages = null;

		if (!initialized || (latestMessageUid == -1)) {
			if (messageCount < _messagesToPrefetch) {
				messages = folder.getMessages(1, messageCount);
			}
			else {
				messages = folder.getMessages(
					(messageCount - _messagesToPrefetch + 1), messageCount);
			}

			storeMessagesToDisk(folder, messages);

			// Write new JSON folder

			storeFolderToDisk(folder, true, new Date());
		}
		else {

			// Get new messages since last update

			Message message = ((IMAPFolder)folder).getMessageByUID(
				latestMessageUid);

			int messageNumber = message.getMessageNumber();

			messages = folder.getMessages(messageNumber, messageCount);

			storeMessagesToDisk(folder, messages);
			storeFolderToDisk(folder, true, new Date());
		}
	}

	protected String getAddresses(Address[] addresses) {
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

	protected void getBody(
		StringBuilder sb, String contentPath, Part messagePart,
		List<Object[]> attachments) {

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
							attachments)) {

						break;
					}
				}
			}
			else if (Validator.isNull(messagePart.getFileName())) {

				// Plain text, HTML or forwarded message

				if (contentType.startsWith(ContentTypes.TEXT_PLAIN)) {
					sb.append(StringPool.NEW_LINE + StringPool.NEW_LINE);
					sb.append(messagePart.getContent());
				}
				else if (contentType.startsWith(ContentTypes.TEXT_HTML)) {
					sb.append("<hr />");
					sb.append(
						stripUnsafeCss(messagePart.getContent().toString()));
				}
				else if (contentType.startsWith(ContentTypes.MESSAGE_RFC822)) {
					//getBody(
					//	sb, contentPath + StringPool.PERIOD + 0, messagePart,
					//	attachments);
				}
			}
			else {

				// Attachment

				Object[] attachment = new Object[] {
					contentPath + StringPool.PERIOD + -1,
					messagePart.getFileName()
				};

				attachments.add(attachment);
			}
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}
		catch (MessagingException me) {
			sb.append("Error retrieving message content");

			_log.error(me, me);
		}
	}

	protected boolean getBodyMulitipart(
			String contentType, Part curPart, String contentPath,
			StringBuilder sb, List<Object[]> attachments)
		throws MessagingException {

		if (contentType.startsWith(ContentTypes.MULTIPART_ALTERNATIVE)) {
			String partContentType = curPart.getContentType().toLowerCase();

			if (partContentType.startsWith(ContentTypes.TEXT_HTML)) {
				getBody(sb, StringPool.BLANK, curPart, attachments);

				return true;
			}
		}
		else {
			getBody(sb, contentPath, curPart, attachments);
		}

		return false;
	}

	protected String getBodyPreview(String messageBody)
		throws MessagingException {

		messageBody = stripHtml(messageBody);
		messageBody = StringUtil.shorten(messageBody, 150);

		return messageBody;
	}

	protected Folder getFolder(String folderName) throws MessagingException {
		Folder folder = getStore().getDefaultFolder();

		folder = folder.getFolder(folderName);

		if (folder == null) {
			_log.error("Invalid folder " + folderName);
		}

		return folder;
	}

	protected List<Folder> getFolders() throws MessagingException {
		Store store = getStore();

		IMAPFolder rootFolder = (IMAPFolder)store.getDefaultFolder();

		List<Folder> allFolders = new ArrayList<Folder>();

		getFolders(allFolders, rootFolder.list());

		return allFolders;
	}

	protected void getFolders(List<Folder> allFolders, Folder[] folders) {
		for (Folder folder : folders) {
			try {
				int folderType = folder.getType();

				if ((folderType & IMAPFolder.HOLDS_MESSAGES) != 0) {
					allFolders.add(folder);
				}

				if ((folderType & IMAPFolder.HOLDS_FOLDERS) != 0) {
					getFolders(allFolders, folder.list());
				}
			}
			catch (MessagingException me) {
				_log.error("Skipping IMAP folder: " + me.getMessage());
			}
		}
	}

	protected void getIncomingStore(MailAccount mailAccount) {
		try {
			Properties props = new Properties();

			URLName url = new URLName(
				"imap", mailAccount.getMailInHostName(),
				mailAccount.getMailInPort(), StringPool.BLANK,
				mailAccount.getUsername(), mailAccount.getPassword());

			props.setProperty(
				"mail.imap.port", String.valueOf(mailAccount.getMailInPort()));

			if (mailAccount.isMailSecure()) {
				props.setProperty(
					"mail.imap.socketFactory.port",
					String.valueOf(mailAccount.getMailInPort()));
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

	protected JSONObject getJSONFolder(Folder folder)
		throws MessagingException {

		JSONObject jsonObj = new JSONObject();

		if (folder.getType() != Folder.HOLDS_FOLDERS) {
			openFolder(folder);

			JSONUtil.put(jsonObj, "messageCount", folder.getMessageCount());
			JSONUtil.put(jsonObj, "name", folder.getFullName());

			return jsonObj;
		}

		return null;
	}

	protected JSONObject getJSONMessage(Folder folder, Message message)
		throws MessagingException {

		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy HH:mm");

		String date = StringPool.BLANK;

		if (Validator.isNotNull(message.getSentDate())) {
			date = sdf.format(message.getSentDate());
		}

		StringBuilder sb = new StringBuilder();

		List<Object[]> attachments = new ArrayList<Object[]>();

		getBody(sb, StringPool.BLANK, message, attachments);

		JSONObject jsonObj = new JSONObject();

		JSONUtil.put(jsonObj, "attachments", getJSONAttachments(attachments));
		JSONUtil.put(jsonObj, "body", sb.toString());
		JSONUtil.put(jsonObj, "bodyPreview", getBodyPreview(sb.toString()));
		JSONUtil.put(jsonObj, "date", date);
		JSONUtil.put(jsonObj, "from", getAddresses(message.getFrom()));
		JSONUtil.put(jsonObj, "html", false);
		JSONUtil.put(jsonObj, "messageNumber", message.getMessageNumber());
		JSONUtil.put(jsonObj, "read", message.isSet(Flags.Flag.SEEN));
		JSONUtil.put(jsonObj, "subject", message.getSubject());
		JSONUtil.put(jsonObj, "uid", ((IMAPFolder)folder).getUID(message));

		JSONUtil.put(
			jsonObj, "to",
			getAddresses(message.getRecipients(RecipientType.TO)));
		JSONUtil.put(
			jsonObj, "cc",
			getAddresses(message.getRecipients(RecipientType.CC)));
		JSONUtil.put(
			jsonObj, "bcc",
			getAddresses(message.getRecipients(RecipientType.BCC)));

		return jsonObj;
	}

	protected Message getMessageByUid(String folderName, long messageUid)
		throws MessagingException {

		IMAPFolder folder = (IMAPFolder)openFolder(folderName);

		return getMessageByUid(folder, messageUid);
	}

	protected Message getMessageByUid(Folder folder, long messageUid)
		throws MessagingException {

		return ((IMAPFolder)folder).getMessageByUID(messageUid);
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

		props.put("mail.debug", "false");

		Session session = Session.getDefaultInstance(props, null);

		session.setDebug(false);

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

	protected Folder openFolder(String folderName) throws MessagingException {
		Folder folder = getStore().getDefaultFolder();

		folder = folder.getFolder(folderName);

		return openFolder(folder);
	}

	protected Folder openFolder(Folder folder) throws MessagingException {
		if (folder == null) {
			return null;
		}

		if (folder.isOpen()) {
			return folder;
		}

		try {
			folder.open(Folder.READ_WRITE);
		}
		catch (MessagingException me1) {
			try {
				folder.open(Folder.READ_ONLY);
			}
			catch (MessagingException me2) {
				_log.error(
					"Unable to open folder " + folder.getFullName(), me2);
			}
		}

		return folder;
	}

	protected void send(
			Message message, String fromEmailAddress, String to, String cc,
			String bcc, String subject, Multipart multipart)
		throws MessagingException {

		MailAccount fromMailAccount = new MailAccount(_user, fromEmailAddress);

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
		message.setContent(multipart);

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

	protected JSONObject storeFolderToDisk(
		Folder folder, boolean initialized, Date date) {

		try {
			JSONObject jsonObj = getJSONFolder(folder);

			String filePath = MailDiskManager.getFolderFilePath(
				_user, _mailAccount.getEmailAddress(), folder.getFullName());

	   		JSONUtil.put(jsonObj, "initialized", initialized);
			JSONUtil.put(jsonObj, "lastUpdated", date);

			FileUtil.write(filePath, jsonObj.toString());

			return jsonObj;
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}
		catch (MessagingException me) {
			_log.error(me, me);
		}

		return null;
	}

	protected void storeMessagesToDisk(Folder folder, Message[] messages) {
		for (Message message : messages) {
			storeMessageToDisk(folder, message);
		}
	}

	protected void storeMessageToDisk(Folder folder, Message message) {
		try {
			IMAPFolder imapFolder = (IMAPFolder)folder;

			String jsonMessage = getJSONMessage(imapFolder, message).toString();
			String messageUid = String.valueOf(imapFolder.getUID(message));

			String filePath = MailDiskManager.getMessageFilePath(
				_user, _mailAccount.getEmailAddress(), folder.getFullName(),
				messageUid);

			FileUtil.write(filePath, jsonMessage);
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}
		catch (MessagingException me) {
			_log.error(me, me);
		}
	}

	protected String stripHtml(String html) {
		html = html.replaceAll("<[^>]+>", StringPool.BLANK);
		html = html.replaceAll("[\r\n]+", StringPool.BLANK);

		return html;
	}

	protected String stripUnsafeCss(String html) {

		// Remove external stylesheets

		html = html.replaceAll("<link [^>]+>", StringPool.BLANK);

		// Remove style blocks

		Pattern pattern = Pattern.compile("<style.*?</style>", Pattern.DOTALL);

		return pattern.matcher(html).replaceAll(StringPool.BLANK);
	}

	protected void updateJSONMessageField(
			User user, MailAccount mailAccount, String folderName,
			String messageUid, String field, String value)
		throws IOException {

		JSONObject jsonObj = MailDiskManager.getJSONMessageByUid(
			_user, _mailAccount.getEmailAddress(), folderName,
			String.valueOf(messageUid));

		JSONUtil.put(jsonObj, field, value);

		String filePath = MailDiskManager.getMessageFilePath(
			_user, _mailAccount.getEmailAddress(), folderName, messageUid);

		FileUtil.write(filePath, jsonObj.toString());
	}

	public static final String _SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

	private static Log _log = LogFactory.getLog(MailBoxManager.class);

	private User _user;
	private MailAccount _mailAccount;
	private Session _session = null;
	private Store _store = null;
	private int _messagesToPrefetch = GetterUtil.getInteger(
		PortletProps.get("messages.to.prefetch"));

}