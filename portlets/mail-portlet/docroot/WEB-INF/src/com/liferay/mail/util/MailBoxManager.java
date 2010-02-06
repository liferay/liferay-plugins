/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.util.portlet.PortletProps;

import com.sun.mail.imap.IMAPFolder;

import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Arrays;
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

/**
 * <a href="MailBoxManager.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 *
 */
public class MailBoxManager {

	static JSONObject _successJSON;
	static JSONObject _failureJSON;

	static {
		_successJSON = JSONFactoryUtil.createJSONObject();

		_successJSON.put("success", true);

		_failureJSON = JSONFactoryUtil.createJSONObject();

		_failureJSON.put("success", false);
	}

	public MailBoxManager(User user, String emailAddress) {
		_user = user;
		_mailAccount = new MailAccount(user, emailAddress);
	}

	public MailBoxManager(
		User user, String emailAddress, boolean initialized,
		String mailInHostName, String mailInPort, boolean mailInSecure,
		String mailOutHostName, String mailOutPort, boolean mailOutSecure,
		String password, String username) {

		_user = user;
		_mailAccount = new MailAccount(
			user, emailAddress, initialized, mailInHostName, mailInPort,
			mailInSecure, mailOutHostName, mailOutPort, mailOutSecure, password,
			username);
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

		messageBodyPart.setContent(body, ContentTypes.TEXT_HTML_UTF8);

		multipart.addBodyPart(messageBodyPart);

		// Add message attachments to multipart

		if (Validator.isNotNull(files)) {
			for (File file : files) {
				if (!file.exists()) {
					continue;
				}

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

	public JSONObject deleteAccount() {
		MailDiskManager.deleteAccount(_user, _mailAccount.getEmailAddress());

		return _successJSON;
	}

	public void deleteMessage(Folder folder, long messageUid, boolean expunge) {

		// Delete from server

		try {
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

		MailDiskManager.deleteMessage(
			_user, _mailAccount.getEmailAddress(), folder.getFullName(),
			messageUid);
	}

	public JSONObject deleteMessagesByUids(
		String folderName, String messageUids) {

		long[] messageUidsArray = GetterUtil.getLongValues(
			messageUids.split("\\s*,\\s*"));

		if (Validator.isNull(messageUidsArray)) {
			return _failureJSON;
		}

		try {
			Folder folder = openFolder(folderName);

			for (long messageUid : messageUidsArray) {
				deleteMessage(folder, messageUid, false);
			}

			folder.close(true);

			return _successJSON;
		}
		catch (MessagingException me) {
			_log.error(me, me);

			return _failureJSON;
		}
	}

	public Part getAttachment(
			String folderName, int messageUid, String contentPath)
		throws MessagingException {

		Message message = getMessageByUid(folderName, messageUid);

		return getMessagePart(message, contentPath);
	}

	public JSONObject flagMessages(
		String folderName, String messageUids, String flag, boolean value) {

		// Cancel if account no longer exists

		if (!isAccountExists()) {
			return null;
		}

		long[] messageUidsArray = GetterUtil.getLongValues(
			messageUids.split("\\s*,\\s*"));

		if (Validator.isNull(messageUidsArray)) {
			return _failureJSON;
		}

		try {
			Folder folder = getFolder(folderName);

			for (long messageUid : messageUidsArray) {
				try {

					// Cancel if account no longer exists

					if (!isAccountExists()) {
						return null;
					}

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

			return _successJSON;
		}
		catch (MessagingException me) {
			_log.error(me, me);

			return _failureJSON;
		}
	}

	public JSONObject saveMessage(Message message, String oldDraftMessageUid)
		throws MessagingException {

		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		Folder draftsFolder = getDraftsFolder();

		// Append message to folder and get messageUid

		long newDraftMessageUid = ((IMAPFolder)draftsFolder).getUIDNext();

		draftsFolder.appendMessages(new Message[]{message});

		storeMessage(
			_user, _mailAccount,
			getMessageByUid(draftsFolder, newDraftMessageUid));

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

		try {
			Transport transport = getTransport(fromMailAccount);

			transport.sendMessage(message, message.getAllRecipients());

			transport.close();

			return _successJSON;
		}
		catch (MessagingException me) {
			_log.error(me, me);

			return _failureJSON;
		}
	}

	public JSONObject sendUpdateMessage() {
		JSONObject mailRequestJSON = JSONFactoryUtil.createJSONObject();

		mailRequestJSON.put("emailAddress", _mailAccount.getEmailAddress());
		mailRequestJSON.put("userId", _user.getUserId());

		MessageBusUtil.sendMessage(
			DestinationNames.MAIL_SYNCHRONIZER, mailRequestJSON);

		return _successJSON;
	}

	public JSONObject storeAccount() {
		return MailDiskManager.createAccount(_user, _mailAccount);
	}

	public JSONObject synchronizeAccount() throws MessagingException {

		// Cancel if account no longer exists

		if (!isAccountExists()) {
			return null;
		}

		List<Folder> folders = getFolders();

		if (Validator.isNotNull(folders)) {
			for (Folder folder : folders) {

				// Cancel if account no longer exists

				if (!isAccountExists()) {
					return null;
				}

				try {
					synchronizeFolder(folder);
				}
				catch (MessagingException me) {
					_log.error(me, me);
				}
			}

			_mailAccount.setInitialized(true);

			MailDiskManager.createAccount(_user, _mailAccount);

			return _successJSON;
		}

		return _failureJSON;
	}

	public void synchronizeFolder(Folder folder) throws MessagingException {

		// Cancel if account no longer exists

		if (!isAccountExists()) {
			return;
		}

		// Check if folder has been initialized

		JSONObject jsonObj = MailDiskManager.getJSONFolder(
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

		if (!initialized) {

			// Get just enough messages to initialize folder

			if (messageCount < _MESSAGES_TO_PREFETCH) {
				messages = folder.getMessages(1, messageCount);
			}
			else {
				messages = folder.getMessages(
					(messageCount - _MESSAGES_TO_PREFETCH + 1), messageCount);
			}

			if (Validator.isNotNull(messages)) {
				storeMessages(messages);
			}

			storeFolder(folder);
		}
		else {

			// Get all new messages since last update

			Message newestStoredMessage = getNewestOrOldestStoredMessage(
				folder, true);

			if (Validator.isNotNull(newestStoredMessage)) {
				int messageNumber = newestStoredMessage.getMessageNumber();

				if (messageNumber != messageCount) {
					messages = folder.getMessages(messageNumber, messageCount);

					storeMessages(messages);

					storeFolder(folder);
				}
			}

			// Get some older messages since last update

			Message oldestStoredMessage = getNewestOrOldestStoredMessage(
				folder, false);

			if (Validator.isNotNull(oldestStoredMessage)) {
				int messageNumber = oldestStoredMessage.getMessageNumber();

				if (messageCount != 1) {
					if ((messageNumber - _MESSAGES_TO_PREFETCH) < 0) {
						messages = folder.getMessages(1, messageNumber);
					}
					else {
						messages = folder.getMessages(
							(messageNumber - _MESSAGES_TO_PREFETCH),
							messageNumber);
					}

					storeMessages(messages);

					storeFolder(folder);
				}
			}
		}
	}

	public JSONObject testAccount() {
		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		// Test incoming

		try {
			Store store = getStore(false);

			Folder folder = store.getDefaultFolder();

			if (Validator.isNotNull(folder) &&
				Validator.isNotNull(folder.list())) {

				jsonObj.put("incoming", true);
			}
			else {
				jsonObj.put("incoming", false);
			}

			store.close();
		}
		catch (MessagingException me) {
			jsonObj.put("incoming", false);
		}

		// Test outgoing

		try {
			Transport transport = getTransport(_mailAccount);

			if (transport.isConnected()) {
				jsonObj.put("outgoing", true);
			}
			else {
				jsonObj.put("outgoing", false);
			}

			transport.close();
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
			sb.append(address.getAddress());
			sb.append(StringPool.COMMA);
			sb.append(StringPool.SPACE);
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

					String messageText = messagePart.getContent().toString();

					sb.append(messageText.replaceAll("\r\n", "<br />"));
				}
				else if (contentType.startsWith(ContentTypes.TEXT_HTML)) {
					if (sb.length() > 0) {
						sb.append("<hr />");
					}

					try {
						sb.append(
							stripUnsafeHtmlAndCss(
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

		openFolder(message.getFolder());

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

	protected Message getNewestOrOldestStoredMessage(
		Folder folder, boolean newest) {

		long[] messageUids = MailDiskManager.getMessageUidsByFolder(
			_user, _mailAccount.getEmailAddress(), folder.getFullName());

		if (Validator.isNull(folder)) {
			return null;
		}

		if (messageUids.length == 0) {
			return null;
		}

		Arrays.sort(messageUids);

		// If message no longer exists, delete message from disk and get next

		if (newest) {
			for (int i = messageUids.length - 1; i >= 0; i--) {
				try {
					return getMessageByUid(folder, messageUids[i]);
				}
				catch (MessagingException me) {
					deleteMessage(folder, messageUids[i], false);
				}
			}
		}
		else {
			for (int i = 0; i < messageUids.length; i++) {
				try {
					return getMessageByUid(folder, messageUids[i]);
				}
				catch (MessagingException me) {
					deleteMessage(folder, messageUids[i], false);
				}
			}

		}

		return null;
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
			_session = getSession(_mailAccount);
		}

		return _session;
	}

	protected Session getSession(MailAccount mailAccount) {
		boolean debug = GetterUtil.getBoolean(
			PortletProps.get("javamail.debug"));

		Properties props = new Properties();

		props.put("mail.imap.host", mailAccount.getMailInHostName());
		props.put("mail.imap.port", mailAccount.getMailInPort());

		props.put("mail.imaps.auth", "true");
		props.put("mail.imaps.host", mailAccount.getMailInHostName());
		props.put("mail.imaps.port", mailAccount.getMailInPort());
		props.put("mail.imaps.socketFactory.port", mailAccount.getMailInPort());
		props.put("mail.imaps.socketFactory.class", _SSL_FACTORY);
		props.put("mail.imaps.socketFactory.fallback", "false");

		props.put("mail.smtp.host", mailAccount.getMailOutHostName());
		props.put("mail.smtp.port", mailAccount.getMailOutPort());

		props.put("mail.smtps.auth", "true");
		props.put("mail.smtps.host", mailAccount.getMailOutHostName());
		props.put("mail.smtps.port", mailAccount.getMailOutPort());
		props.put(
			"mail.smtps.socketFactory.port", mailAccount.getMailOutPort());
		props.put("mail.smtps.socketFactory.class", _SSL_FACTORY);
		props.put("mail.smtps.socketFactory.fallback", "false");

		props.put("mail.debug", Boolean.toString(debug));

		Session session = Session.getInstance(props);

		session.setDebug(debug);

		return session;
	}

	protected Store getStore(boolean useOldStores) throws MessagingException {
		Store store = null;

		String storeKey =
			_mailAccount.getUser().getUserId() + "_STORE_" +
				_mailAccount.getEmailAddress();

		if (useOldStores) {
			store = (Store)_allStores.get(storeKey);

			if (Validator.isNotNull(store) && !store.isConnected()) {
				store.close();

				store = null;
			}
		}

		if (Validator.isNull(store)) {
			Session session = getSession();

			if (_mailAccount.isMailInSecure()) {
				store = session.getStore("imaps");
			}
			else {
				store = session.getStore("imap");
			}

			store.addConnectionListener(new ConnectionListener(storeKey));

			try {
				store.connect(
					_mailAccount.getMailInHostName(),
					_mailAccount.getMailInPort(), _mailAccount.getUsername(),
					_mailAccount.getPassword());
			}
			catch (MessagingException me) {
				_log.error("Failed on connecting to " + storeKey);

				throw me;
			}

			if (useOldStores) {
				_allStores.put(storeKey, store);
			}
		}

		return store;
	}

	protected Transport getTransport(MailAccount mailAccount)
		throws MessagingException {

		String transportKey =
			_mailAccount.getUser().getUserId() + "_TRANSPORT_" +
				_mailAccount.getEmailAddress();

		Transport transport = null;

		Session session = getSession(mailAccount);

		if (mailAccount.isMailOutSecure()) {
			transport = session.getTransport("smtps");
		}
		else {
			transport = session.getTransport("smtp");
		}

		transport.addConnectionListener(new ConnectionListener(transportKey));

		transport.connect(
			mailAccount.getMailOutHostName(), mailAccount.getMailOutPort(),
			mailAccount.getUsername(), mailAccount.getPassword());

		return transport;
	}

	protected boolean isAccountExists() {
		String emailAddress = _mailAccount.getEmailAddress();

		return MailDiskManager.isAccountExists(_user, emailAddress);
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

	protected void storeFolder(Folder folder) throws MessagingException {
		JSONObject jsonFolder = getJSONFolder(folder);

		MailDiskManager.updateFolder(
			_user, _mailAccount.getEmailAddress(), jsonFolder);
	}

	protected void storeMessages(Message[] messages) {
		if (!isAccountExists()) {
			return;
		}

		for (Message message : messages) {
			try {
				storeMessage(_user, _mailAccount, message);
			}
			catch (MessagingException me) {
				_log.error(me, me);
			}
		}
	}

	protected void storeMessage(
			User user, MailAccount mailAccount, Message message)
		throws MessagingException {

		IMAPFolder folder = (IMAPFolder)openFolder(message.getFolder());

		String folderName = folder.getFullName();
		long messageUid = folder.getUID(message);
		JSONObject jsonMessage = getJSONMessage(message);

		MailDiskManager.createMessage(
			user, mailAccount.getEmailAddress(), folderName, messageUid,
			jsonMessage);
	}

	protected String stripHtml(String html) {
		html = html.replaceAll("<[^>]+>", StringPool.BLANK);
		html = html.replaceAll("[\r\n]+", StringPool.BLANK);

		return html;
	}

	protected String stripUnsafeHtmlAndCss(String html) {

		// Remove lines (external stylesheets, html, body)

		html = html.replaceAll("<link [^>]+>", StringPool.BLANK);
		html = html.replaceAll("<html [^>]+>", StringPool.BLANK);
		html = html.replaceAll("<body [^>]+>", StringPool.BLANK);

		// Remove blocks (style, head)

		Pattern patternStyle = Pattern.compile(
			"<style.*?</style>", Pattern.DOTALL);
		Pattern patternHead = Pattern.compile(
			"<head.*?</head>", Pattern.DOTALL);

		html = patternStyle.matcher(html).replaceAll(StringPool.BLANK);
		html = patternHead.matcher(html).replaceAll(StringPool.BLANK);

		return html;
	}

	protected void updateJSONMessageFlag(
		User user, MailAccount mailAccount, String folderName, long messageUid,
		String flag, boolean value) {

		MailDiskManager.updateMessage(
			user, mailAccount.getEmailAddress(), folderName, messageUid, flag,
			value);
	}

	private static final int _MESSAGES_TO_PREFETCH = GetterUtil.getInteger(
		PortletProps.get("messages.to.prefetch"));

	private static final String _SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

	private static final Log _log = LogFactoryUtil.getLog(MailBoxManager.class);

	private static ConcurrentHashMap<String, Store> _allStores =
		new ConcurrentHashMap<String, Store>();

	private User _user;
	private MailAccount _mailAccount;
	private Session _session;

}