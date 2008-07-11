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
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.util.portlet.PortletProps;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPSSLStore;
import com.sun.mail.imap.IMAPStore;

import java.io.File;
import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

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

/**
 *
 * <a href="MailBoxManager.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 *
 */
public class MailBoxManager {

	public static void removeAccountLock(User user, String emailAddress) {
		String filePath = MailDiskManager.getAccountLockPath(
			user, emailAddress);

		FileUtil.delete(filePath);
	}

	public static void writeAccountLock(User user, String emailAddress) {
		try {
			JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

			String filePath = MailDiskManager.getAccountLockPath(
				user, emailAddress);

			DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

			jsonObj.put("locked", true);
			jsonObj.put("dateLocked", df.format(new Date()));

			FileUtil.write(filePath, jsonObj.toString());
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}
	}

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

	public Folder createFolder(String folderName) throws MessagingException {
		Folder newFolder = getStore(true).getFolder(folderName);

		if (!newFolder.exists()) {
			newFolder.create(Folder.HOLDS_MESSAGES);
		}

		return newFolder;
	}

	public Message createMessage(
			String from, String to, String cc, String bcc, String subject,
			String body, File[] files)
		throws MessagingException {

		// Create new message

		Message message = new MimeMessage(getSession());

		// Fill message fields

		message.setFrom(new InternetAddress(from));

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

		message.setSentDate(new Date());
		message.setSubject(subject);

		MimeMultipart multipart = new MimeMultipart();

		// Add message body to multipart

		BodyPart messageBodyPart = new MimeBodyPart();

		messageBodyPart.setContent(body, ContentTypes.TEXT_HTML);

		multipart.addBodyPart(messageBodyPart);

		// Add message attachments to multipart

		if (Validator.isNotNull(files)) {
			for (File file : files) {
				String fileName = file.getName();
				DataSource dataSource = new FileDataSource(file);

				BodyPart messageAttachmentPart = new MimeBodyPart();

				messageAttachmentPart.setDataHandler(
					new DataHandler(dataSource));
				messageAttachmentPart.setFileName(fileName);

				multipart.addBodyPart(messageAttachmentPart);
			}
		}

		message.setContent(multipart);

		return message;
	}

	public Message createTestMessage() throws MessagingException {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy HH:mm");

		Message message = new MimeMessage(getSession());

		message.setFrom(new InternetAddress(_mailAccount.getEmailAddress()));
		message.setRecipient(
			Message.RecipientType.TO,
			new InternetAddress(_mailAccount.getEmailAddress()));
		message.setSubject(
			"[" + sdf.format(new Date()) + "] testing outgoing email");
		message.setContent("testing outgoing email", "text/plain");

		return message;
	}

	public JSONObject deleteAccountFromDisk() {
		String filePath = MailDiskManager.getAccountFilePath(
			_user, _mailAccount.getEmailAddress());

		FileUtil.delete(filePath);

		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		jsonObj.put("success", true);

		return jsonObj;
	}

	public void deleteMessage(Folder folder, long messageUid, boolean expunge) {
		try {

			// Delete from server

			Message message = getMessageByUid(folder, messageUid);

			if (Validator.isNotNull(message)) {
				message.setFlag(Flags.Flag.DELETED, true);

				if (expunge) {
					folder.close(true);
				}
			}
		}
		catch (MessagingException me) {
			_log.error(me, me);
		}

		// Delete from local disk

		String messagePath = MailDiskManager.getMessagePath(
			_user, _mailAccount.getEmailAddress(), folder.getFullName(),
			messageUid);

		FileUtil.deltree(messagePath);
	}

	public JSONObject deleteMessagesByUids(
		String folderName, String messageUids) {

		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		long[] messageUidsArray = GetterUtil.getLongValues(
			messageUids.split("\\s*,\\s*"));

		if (Validator.isNull(messageUidsArray)) {
			jsonObj.put("success", false);

			return jsonObj;
		}

		try {
			Folder folder = openFolder(folderName);

			for (long messageUid : messageUidsArray) {
				deleteMessage(folder, messageUid, false);
			}

			folder.close(true);

			jsonObj.put("success", true);
		}
		catch (MessagingException me) {
			_log.error(me, me);

			jsonObj.put("success", false);
		}

		return jsonObj;
	}

	public Part getAttachment(
			String folderName, int messageUid, String contentPath)
		throws MessagingException {

		Message message = getMessageByUid(folderName, messageUid);

		return getMessagePart(message, contentPath);
	}

	public JSONObject flagMessages(
			String folderName, String messageUids, String flag, boolean value) {

		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		long[] messageUidsArray = GetterUtil.getLongValues(
			messageUids.split("\\s*,\\s*"));

		if (Validator.isNull(messageUidsArray)) {
			jsonObj.put("success", false);

			return jsonObj;
		}

		try {
			Folder folder = getFolder(folderName);

			for (long messageUid : messageUidsArray) {
				try {

					// Update message on server

					Message message = getMessageByUid(folder, messageUid);

					if (flag.equals("answered")) {
						message.setFlag(Flags.Flag.ANSWERED, value);
					}
					else if (flag.equals("flagged")) {
						message.setFlag(Flags.Flag.FLAGGED, value);
					}
					else if (flag.equals("seen")) {
						message.setFlag(Flags.Flag.SEEN, value);
					}

					// Update message on local disk

					updateJSONMessageFlag(
						_user, _mailAccount, folderName, messageUid, flag,
						value);
				}
				catch (MessagingException me) {
					_log.error(me, me);
				}
			}

			folder.close(true);

			jsonObj.put("success", true);
		}
		catch (MessagingException me) {
			_log.error(me, me);

			jsonObj.put("success", false);
		}

		return jsonObj;
	}

	public JSONObject saveMessage(Message message, String oldDraftMessageUid)
		throws MessagingException {

		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		Folder draftsFolder = getDraftsFolder();

		// Append message to folder and get messageUid

		long newDraftMessageUid = ((IMAPFolder)draftsFolder).getUIDNext();

		draftsFolder.appendMessages(new Message[]{message});

		storeMessageToDisk(getMessageByUid(draftsFolder, newDraftMessageUid));

		// Remove old draft message

		if (Validator.isNotNull(oldDraftMessageUid)) {
			deleteMessage(
				draftsFolder, GetterUtil.getLong(oldDraftMessageUid), true);
		}

		jsonObj.put("success", true);
		jsonObj.put("draftMessageUid", newDraftMessageUid);

		return jsonObj;
	}

	public JSONObject sendMessage(
		MailAccount fromMailAccount, Message message) {

		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		Transport transport = null;

		try {

			// Send message

			Session session = getOutgoingSession(fromMailAccount);

			if (fromMailAccount.isMailSecure()) {
				transport = session.getTransport("smtps");
			}
			else {
				transport = session.getTransport("smtp");
			}

			transport.connect(
				fromMailAccount.getMailOutHostName(),
				fromMailAccount.getUsername(), fromMailAccount.getPassword());

			transport.sendMessage(message, message.getAllRecipients());

			jsonObj.put("success", true);
		}
		catch (MessagingException me) {
			_log.error(me, me);

			jsonObj.put("success", false);
		}

		return jsonObj;
	}

	public JSONObject sendUpdateMessage() {
		JSONObject mailRequestJSON = JSONFactoryUtil.createJSONObject();

		mailRequestJSON.put("emailAddress", _mailAccount.getEmailAddress());
		mailRequestJSON.put("userId", _user.getUserId());

		MessageBusUtil.sendMessage(
			DestinationNames.MAIL_SYNCHRONIZER, mailRequestJSON.toString());

		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		jsonObj.put("success", true);

		return jsonObj;
	}

	public JSONObject storeAccountToDisk() {
		try {
			JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

			String filePath = MailDiskManager.getAccountFilePath(
				_user, _mailAccount.getEmailAddress());

			jsonObj.put("emailAddress", _mailAccount.getEmailAddress());
			jsonObj.put("initialized", _mailAccount.isInitialized());
			jsonObj.put("mailInHostName", _mailAccount.getMailInHostName());
			jsonObj.put("mailInPort", _mailAccount.getMailInPort());
			jsonObj.put("mailOutHostName", _mailAccount.getMailOutHostName());
			jsonObj.put("mailOutPort", _mailAccount.getMailOutPort());
			jsonObj.put("mailSecure", _mailAccount.isMailSecure());
			jsonObj.put("password", _mailAccount.getPassword());
			jsonObj.put("username", _mailAccount.getUsername());

			FileUtil.write(filePath, jsonObj.toString());
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			return null;
		}

		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		jsonObj.put("success", true);

		return jsonObj;
	}

	public JSONObject synchronizeAccount() throws MessagingException {
		List<Folder> folders = getFolders();

		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		if (Validator.isNotNull(folders)) {
			for (Folder folder : folders) {
				try {
					synchronizeFolder(folder);
				}
				catch (MessagingException me) {
					_log.error(me, me);
				}
			}

			jsonObj.put("success", true);
		}
		else {
			jsonObj.put("success", false);
		}

		return jsonObj;
	}

	public void synchronizeFolder(Folder folder) throws MessagingException {

		// Check if folder has been initialized

		JSONObject jsonObj = MailDiskManager.getJSONFolder(
			_user, _mailAccount.getEmailAddress(), folder.getFullName());

		Message latestMessage = MailDiskManager.getNewestStoredMessage(
			_user, _mailAccount.getEmailAddress(), folder.getFullName());

		boolean initialized = false;

		if ((jsonObj != null) && jsonObj.getBoolean("initialized")) {
			initialized = true;
		}

		folder = openFolder(folder);

		if (Validator.isNull(folder)) {
			return;
		}

		int messageCount = folder.getMessageCount();

		Message[] messages = null;

		if (!initialized || (Validator.isNull(latestMessage))) {
			if (messageCount < _messagesToPrefetch) {
				messages = folder.getMessages(1, messageCount);
			}
			else {
				messages = folder.getMessages(
					(messageCount - _messagesToPrefetch + 1), messageCount);
			}

			if (Validator.isNotNull(messages)) {
				storeMessagesToDisk(messages);
			}

			// Write new JSON folder

			storeFolderToDisk(folder, true, new Date());
		}
		else {

			// Get new messages since last update

			int messageNumber = latestMessage.getMessageNumber();

			messages = folder.getMessages(messageNumber, messageCount);

			storeMessagesToDisk(messages);
			storeFolderToDisk(folder, true, new Date());
		}
	}

	public JSONObject testAccount() {
		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		// Test incoming

		Folder folder = null;

		try {
			folder = getStore(false).getDefaultFolder();

			if (Validator.isNotNull(folder) &&
				Validator.isNotNull(folder.list())) {

				jsonObj.put("incoming", true);
			}
			else {
				jsonObj.put("incoming", false);
			}
		}
		catch (MessagingException me) {
			jsonObj.put("incoming", false);
		}

		// Test outgoing

		try {
			JSONObject outgoing = sendMessage(
				_mailAccount, createTestMessage());

			jsonObj.put("outgoing", outgoing.getBoolean("success"));
		}
		catch (MessagingException me) {
			jsonObj.put("outgoing", false);
		}

		return jsonObj;
	}

	protected String getAddresses(Address[] addresses) {
		StringBuilder sb = new StringBuilder();

		if (Validator.isNull(addresses)) {
			return StringPool.BLANK;
		}

		for (InternetAddress address : (InternetAddress[])addresses) {
			sb.append(address.getAddress() + StringPool.COMMA + StringPool.SPACE);
		}

		return sb.substring(0, sb.length() - 2);
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
					if (sb.length() > 0) {
						sb.append(StringPool.NEW_LINE + StringPool.NEW_LINE);
					}

					sb.append(messagePart.getContent());
				}
				else if (contentType.startsWith(ContentTypes.TEXT_HTML)) {
					if (sb.length() > 0) {
						sb.append("<hr />");
					}

					try {
						sb.append(
							stripUnsafeCss(
								messagePart.getContent().toString()));
					}
					catch (IOException ioe) {
						// ignore
					}
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
		catch (Exception e) {
			sb.append("Error retrieving message content");

			_log.error(e, e);
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

	protected Folder getDraftsFolder() throws MessagingException {
		Folder draftsFolder = null;

		JSONObject jsonDraftFolder = MailDiskManager.getJSONDraftsFolder(
			_user, _mailAccount.getEmailAddress());

		if (Validator.isNull(jsonDraftFolder)) {
			List<Folder> folders = getFolders();

			for (Folder tempFolder : folders) {
				if (tempFolder.getName().equalsIgnoreCase("drafts")) {
					draftsFolder = tempFolder;
				}
			}

			if (Validator.isNull(draftsFolder)) {
				draftsFolder = createFolder("Drafts");
			}
		}
		else {
			draftsFolder = getFolder(jsonDraftFolder.getString("fullName"));
		}

		return openFolder(draftsFolder);
	}

	protected Folder getFolder(String folderName) throws MessagingException {
		Folder folder = getStore(true).getDefaultFolder();

		return folder.getFolder(folderName);
	}

	protected List<Folder> getFolders() throws MessagingException {
		Store store = getStore(true);

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

	protected JSONArray getJSONAttachments(List<Object[]> attachments) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (Object[] attachment : attachments) {
			JSONArray tempJsonArray = JSONFactoryUtil.createJSONArray();

			tempJsonArray.put(attachment[0].toString());
			tempJsonArray.put(attachment[1].toString());

			jsonArray.put(tempJsonArray);
		}

		return jsonArray;
	}

	protected JSONObject getJSONFolder(Folder folder)
		throws MessagingException {

		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		folder = openFolder(folder);

		jsonObj.put("fullName", folder.getFullName());
		jsonObj.put("name", folder.getName());

		if (folder.getType() != Folder.HOLDS_FOLDERS) {
			jsonObj.put("messageCount", folder.getMessageCount());
		}

		return jsonObj;
	}

	protected JSONObject getJSONMessage(Message message)
		throws MessagingException {

		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy HH:mm");

		String date = StringPool.BLANK;

		if (Validator.isNotNull(message.getSentDate())) {
			date = sdf.format(message.getSentDate());
		}

		StringBuilder sb = new StringBuilder();

		List<Object[]> attachments = new ArrayList<Object[]>();

		boolean seen = false;

		if (message.isSet(Flags.Flag.SEEN)) {
			seen = true;
		}

		getBody(sb, StringPool.BLANK, message, attachments);

		if (!seen) {
			message.setFlag(Flags.Flag.SEEN, false);
		}

		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
		JSONObject jsonFlags = JSONFactoryUtil.createJSONObject();

		jsonObj.put("attachments", getJSONAttachments(attachments));
		jsonObj.put("body", sb.toString());
		jsonObj.put("bodyPreview", getBodyPreview(sb.toString()));
		jsonObj.put("date", date);
		jsonObj.put("flags", jsonFlags);
		jsonObj.put("from", getAddresses(message.getFrom()));
		jsonObj.put("html", false);
		jsonObj.put("messageNumber", message.getMessageNumber());
		jsonObj.put("subject", message.getSubject());
		jsonObj.put("uid", getMessageUid(message));

		jsonFlags.put("answered", message.isSet(Flags.Flag.ANSWERED));
		jsonFlags.put("deleted", message.isSet(Flags.Flag.DELETED));
		jsonFlags.put("draft", message.isSet(Flags.Flag.DRAFT));
		jsonFlags.put("flagged", message.isSet(Flags.Flag.FLAGGED));
		jsonFlags.put("recent", message.isSet(Flags.Flag.RECENT));
		jsonFlags.put("seen", seen);
		jsonFlags.put("user", message.isSet(Flags.Flag.USER));

		jsonObj.put(
			"to", getAddresses(message.getRecipients(RecipientType.TO)));
		jsonObj.put(
			"cc", getAddresses(message.getRecipients(RecipientType.CC)));
		jsonObj.put(
			"bcc", getAddresses(message.getRecipients(RecipientType.BCC)));

		return jsonObj;
	}

	protected Message getMessageByUid(String folderName, long messageUid)
		throws MessagingException {

		return getMessageByUid(getFolder(folderName), messageUid);
	}

	protected Message getMessageByUid(Folder folder, long messageUid)
		throws MessagingException {

		folder = openFolder(folder);

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

	protected long getMessageUid(Message message) throws MessagingException {
		IMAPFolder folder = (IMAPFolder)openFolder(message.getFolder());

		return folder.getUID(message);
	}

	protected Session getOutgoingSession(MailAccount mailAccount) {
		Properties props = new Properties();

		if (mailAccount.isMailSecure()) {
			props.put("mail.smtps.auth", "true");
			props.put("mail.smtp.host", mailAccount.getMailOutHostName());
			props.put("mail.smtps.port", mailAccount.getMailOutPort());
			props.put(
				"mail.smtps.socketFactory.port", mailAccount.getMailOutPort());
			props.put("mail.smtps.socketFactory.class", _SSL_FACTORY);
			props.put("mail.smtps.socketFactory.fallback", "false");
		}
		else {
			props.put("mail.smtp.host", mailAccount.getMailOutHostName());
			props.put("mail.smtp.port", mailAccount.getMailOutPort());
		}

		props.put("mail.debug", "false");

		Session session = Session.getInstance(props, null);

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
		if (Validator.isNull(_session)) {
			_session = getOutgoingSession(_mailAccount);
		}

		return _session;
	}

	protected Store getStore(boolean useOldStores) throws MessagingException {
		Store store = null;

		String key = _user.getUserId() + "." + _mailAccount.getEmailAddress();

		if (useOldStores) {
			if (Validator.isNull(_allStores)) {
				_allStores = new ConcurrentHashMap<String, Store>();
			}

			store = (Store)_allStores.get(key);
		}

		if (Validator.isNotNull(store) && !store.isConnected()) {
			store.close();

			store = null;
		}

		if (Validator.isNull(store)) {

			// Create new store connection

			Properties props = new Properties();

			URLName url = new URLName(
				"imap", _mailAccount.getMailInHostName(),
				_mailAccount.getMailInPort(), StringPool.BLANK,
				_mailAccount.getUsername(), _mailAccount.getPassword());

			props.setProperty(
				"mail.imap.port", String.valueOf(_mailAccount.getMailInPort()));

			if (_mailAccount.isMailSecure()) {
				props.setProperty(
					"mail.imap.socketFactory.port",
					String.valueOf(_mailAccount.getMailInPort()));
				props.setProperty(
					"mail.imap.socketFactory.class", _SSL_FACTORY);
				props.setProperty("mail.imap.socketFactory.fallback", "false");
			}

			Session session = Session.getInstance(props, null);

			if (_mailAccount.isMailSecure()) {
				store = new IMAPSSLStore(session, url);
			}
			else {
				store = new IMAPStore(session, url);
			}

			store.connect();

			if (useOldStores) {
				_allStores.put(key, store);
			}
		}

		return store;
	}

	protected Folder openFolder(String folderName) throws MessagingException {
		return openFolder(getFolder(folderName));
	}

	protected Folder openFolder(Folder folder) throws MessagingException {
		if (folder.isOpen()) {
			return folder;
		}

		try {
			folder.open(Folder.READ_WRITE);
		}
		catch (MessagingException me1) {
			Store store = folder.getStore();

			if (!store.isConnected()) {
				store = getStore(true);
			}

			folder = store.getFolder(folder.getFullName());

			folder.open(Folder.READ_WRITE);
		}

		return folder;
	}

	protected JSONObject storeFolderToDisk(
			Folder folder, boolean initialized, Date date)
		throws MessagingException {

		try {
			JSONObject jsonObj = getJSONFolder(folder);

			String filePath = MailDiskManager.getFolderFilePath(
				_user, _mailAccount.getEmailAddress(), folder.getFullName());

	   		jsonObj.put("initialized", initialized);
			jsonObj.put("lastUpdated", date);

			FileUtil.write(filePath, jsonObj.toString());

			return jsonObj;
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			return null;
		}
	}

	protected void storeMessagesToDisk(Message[] messages) {
		for (Message message : messages) {
			try {
				storeMessageToDisk(message);
			}
			catch (MessagingException me) {
				_log.error(me, me);
			}
		}
	}

	protected void storeMessageToDisk(Message message)
		throws MessagingException  {

		try {
			IMAPFolder folder = (IMAPFolder)openFolder(message.getFolder());

			String jsonMessage = getJSONMessage(message).toString();

			String filePath = MailDiskManager.getMessageFilePath(
				_user, _mailAccount.getEmailAddress(), folder.getFullName(),
				folder.getUID(message));

			FileUtil.write(filePath, jsonMessage);
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
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

	protected void updateJSONMessageFlag(
		User user, MailAccount mailAccount, String folderName, long messageUid,
		String flag, boolean value) {

		JSONObject jsonMessage = MailDiskManager.getJSONMessageByUid(
			_user, _mailAccount.getEmailAddress(), folderName, messageUid);

		JSONObject jsonFlags = jsonMessage.getJSONObject("flags");

		jsonFlags.put(flag, value);

		String filePath = MailDiskManager.getMessageFilePath(
			_user, _mailAccount.getEmailAddress(), folderName, messageUid);

		try {
			FileUtil.write(filePath, jsonMessage.toString());
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}
	}

	public static final String _SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

	private static ConcurrentHashMap<String, Store> _allStores = null;

	private static Log _log = LogFactory.getLog(MailBoxManager.class);

	private User _user;
	private MailAccount _mailAccount;
	private Session _session = null;
	private int _messagesToPrefetch = GetterUtil.getInteger(
		PortletProps.get("messages.to.prefetch"));

}