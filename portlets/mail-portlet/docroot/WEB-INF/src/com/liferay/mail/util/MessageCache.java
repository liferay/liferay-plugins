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
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.util.portlet.PortletProps;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.net.URLDecoder;
import java.net.URLEncoder;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="MailDiskManager.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 *
 */
public class MessageCache {

	public MessageCache(User user) {
		if (Validator.isNull(user)) {
			throw new NullPointerException();
		}

		_user = user;
	}

	public JSONObject createAccount(MailAccount mailAccount) {
		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		try {
			String filePath = _getAccountFilePath(
				mailAccount.getEmailAddress());

			jsonObj.put("emailAddress", mailAccount.getEmailAddress());
			jsonObj.put("initialized", mailAccount.isInitialized());
			jsonObj.put("mailInHostName", mailAccount.getMailInHostName());
			jsonObj.put("mailInPort", mailAccount.getMailInPort());
			jsonObj.put("mailInSecure", mailAccount.isMailInSecure());
			jsonObj.put("mailOutHostName", mailAccount.getMailOutHostName());
			jsonObj.put("mailOutPort", mailAccount.getMailOutPort());
			jsonObj.put("mailOutSecure", mailAccount.isMailOutSecure());
			jsonObj.put("password", mailAccount.getPassword());
			jsonObj.put("username", mailAccount.getUsername());

			FileUtil.write(filePath, jsonObj.toString());
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			return null;
		}

		return jsonObj;
	}

	public void createMessage(
		String emailAddress, String folderName, long messageUid,
		JSONObject message) {

		if (!isAccountExists(emailAddress)) {
			return;
		}

		User user = getUser();

		String filePath =
			getMessageFilePath(emailAddress, folderName, messageUid);

		try {
			FileUtil.write(filePath, message.toString());
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}

		String subject = message.getString("subject");
		String body = message.getString("body");

		try {
			Indexer.addMessage(
				user.getCompanyId(), user.getGroup().getGroupId(),
				user.getUserId(), emailAddress, _encodeFolderName(folderName),
				messageUid, subject, body);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public void deleteAccount(String emailAddress) {
		String accountPath = _getAccountPath(emailAddress);

		FileUtil.deltree(accountPath);

		User user = getUser();

		try {
			Indexer.deleteMessages(
				user.getCompanyId(), user.getUserId(), emailAddress);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public void deleteMessage(
		String emailAddress, String folderName, long messageUid) {

		String messagePath =
			getMessagePath(emailAddress, folderName, messageUid);

		FileUtil.deltree(messagePath);

		try {
			User user = getUser();

			Indexer.deleteMessage(
				user.getCompanyId(), user.getUserId(), emailAddress, folderName,
				messageUid);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public JSONObject getJSONAccount(String emailAddress) {
		String accountFilePath = _getAccountFilePath(emailAddress);

		if (Validator.isNull(emailAddress)) {
			return null;
		}

		try {
			if (FileUtil.exists(accountFilePath)) {
				return JSONFactoryUtil.createJSONObject(
					FileUtil.read(accountFilePath));
			}
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}
		catch (JSONException jsone) {
			_log.error(jsone, jsone);
		}

		return null;
	}

	public JSONObject getJSONAccounts(User user)
		throws MessagingException {

		if (Validator.isNull(user)) {
			return null;
		}

		String userPath = _getUserPath(user);

		String[] emailAddresses = FileUtil.listDirs(userPath);

		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		// Accounts

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		jsonObj.put("accounts", jsonArray);

		for (String emailAddress : emailAddresses) {
			JSONObject jsonAccount = getJSONAccount(emailAddress);

			// Skip if the account exists but the file does not

			if (Validator.isNull(jsonAccount)) {
				continue;
			}

			jsonArray.put(jsonAccount);
		}

		return jsonObj;
	}

	public JSONObject getJSONDraftsFolder(
		String emailAddress) {

		if (Validator.isNull(emailAddress)) {
			return null;
		}

		JSONObject jsonObj = getJSONFolders(emailAddress);

		JSONArray jsonArray = jsonObj.getJSONArray("folders");

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonFolder = jsonArray.getJSONObject(i);

			String folderName = jsonFolder.getString("name").toLowerCase();

			if (folderName.indexOf("drafts") != -1) {
				return jsonFolder;
			}
		}

		return null;
	}

	public JSONObject getJSONFolder(
		String emailAddress, String folderName) {

		if (Validator.isNull(emailAddress) || Validator.isNull(folderName)) {
			return null;
		}

		String folderFilePath = _getFolderFilePath(emailAddress, folderName);

		try {
			if (FileUtil.exists(folderFilePath)) {
				return JSONFactoryUtil.createJSONObject(
					FileUtil.read(folderFilePath));
			}
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}
		catch (JSONException jsone) {
			_log.error(jsone, jsone);
		}

		return null;
	}

	public JSONObject getJSONFolders(String emailAddress) {
		if (Validator.isNull(emailAddress) || getUser().isDefaultUser()) {
			return null;
		}

		String accountPath = _getAccountPath(emailAddress);

		String[] folderNames = FileUtil.listDirs(accountPath);

		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		jsonObj.put("folders", jsonArray);

		for (String folderName : folderNames) {
			try {
				String decodedFolderName = URLDecoder.decode(
					folderName, "UTF-8");

				JSONObject jsonFolder = getJSONFolder(
					emailAddress, decodedFolderName);

				// Skip if the folder exists but the file does not

				if (Validator.isNull(jsonFolder)) {
					continue;
				}

				jsonFolder.put("name", decodedFolderName);

				jsonArray.put(jsonFolder);
			}
			catch (UnsupportedEncodingException uee) {
				_log.error(uee, uee);
			}
		}

		return jsonObj;
	}

	public JSONObject getJSONMessageByUid(
		String emailAddress, String folderName, long messageUid) {

		if (Validator.isNull(emailAddress) || Validator.isNull(folderName)) {
			return null;
		}

		String messageFilePath = getMessageFilePath(
			emailAddress, folderName, messageUid);

		try {
			if (FileUtil.exists(messageFilePath)) {
				String jsonString = FileUtil.read(messageFilePath);

				JSONObject jsonObj = JSONFactoryUtil.createJSONObject(
					jsonString);

				return jsonObj;
			}
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}
		catch (JSONException jsone) {
			_log.error(jsone, jsone);
		}

		return null;
	}

	public JSONObject getJSONMessageRelativeToUid(
		String emailAddress, String folderName, long messageUid,
		int offset, String keywords) {

		if (Validator.isNull(emailAddress) || Validator.isNull(folderName)) {
			return null;
		}

		long[] messageUids = null;

		if (keywords.equals(StringPool.BLANK)) {
			messageUids = getMessageUidsByFolder(emailAddress, folderName);
		}
		else {
			messageUids = search(emailAddress, folderName, keywords);
		}

		for (int i = 0; i < messageUids.length; i++) {
			if (messageUid == messageUids[i]) {
				return getJSONMessageByUid(
					emailAddress, folderName, messageUids[i + offset]);
			}
		}

		return null;
	}

	public JSONObject getJSONMessagesByPage(
		String emailAddress, String folderName, int pageNumber,
		int messagesPerPage) {

		if (Validator.isNull(emailAddress) || Validator.isNull(folderName)) {
			return null;
		}

		try {

			// Actual message count

			String folderFilePath = _getFolderFilePath(
				emailAddress, folderName);

			JSONObject jsonFolderObj = JSONFactoryUtil.createJSONObject(
				FileUtil.read(folderFilePath));

			int messageCount = jsonFolderObj.getInt("messageCount");

			// Disk message count

			long[] messageUids = getMessageUidsByFolder(
				emailAddress, folderName);

			int messagesOnDiskCount = messageUids.length;

			return getJSONPaginatedMessages(
				emailAddress, folderName, messageUids, pageNumber,
				messagesPerPage, messageCount, messagesOnDiskCount);
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}
		catch (JSONException jsone) {
			_log.error(jsone, jsone);
		}

		return null;
	}

	public JSONObject getJSONMessagesBySearch(
		String emailAddress, String folderName, int pageNumber,
		int messagesPerPage, String keywords) {

		if (Validator.isNull(emailAddress) || Validator.isNull(folderName)) {
			return null;
		}

		long[] messageUids = search(emailAddress, folderName, keywords);

		int messageCount = messageUids.length;

		return getJSONPaginatedMessages(
			emailAddress, folderName, messageUids, pageNumber, messagesPerPage,
			messageCount, messageCount);
	}

	public JSONObject getJSONPaginatedMessages(
		String emailAddress, String folderName,
		long[] messageUidsOnDisk, int pageNumber, int messagesPerPage,
		int messageCount, int messagesOnDiskCount) {

		int begin =
			(messagesOnDiskCount + 1) - (messagesPerPage * pageNumber) - 1;
		int end =
			messagesOnDiskCount - (messagesPerPage * (pageNumber - 1)) - 1;

		if (begin < 0) {
			begin = 0;
		}

		double pageCount = Math.ceil((double)messageCount / messagesPerPage);

		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		jsonObj.put("messages", jsonArray);
		jsonObj.put("messageCount", messageCount);
		jsonObj.put("messagesPerPage", messagesPerPage);
		jsonObj.put("pageCount", (int)pageCount);
		jsonObj.put("pageNumber", pageNumber);

		for (int i = begin; i <= end; i++) {
			JSONObject message = getJSONMessageByUid(
				emailAddress, folderName, messageUidsOnDisk[i]);

			if (message != null) {
				jsonArray.put(message);
			}
		}

		return jsonObj;
	}

	public String getMessageFilePath(
		String emailAddress, String folderName, long messageUid) {

		return
			getMessagePath(emailAddress, folderName, messageUid) +
				"/message.json";
	}

	public String getMessagePath(
		String emailAddress, String folderName, long messageUid) {

		String pathName =
			_getFolderPath(emailAddress, folderName) + "/" + messageUid;

		FileUtil.mkdirs(pathName);

		return pathName;
	}

	public long[] getMessageUidsByFolder(
		String emailAddress, String folderName) {

		String folderPath = _getFolderPath(emailAddress, folderName);

		long[] messageUids = GetterUtil.getLongValues(
			FileUtil.listDirs(folderPath));

		Arrays.sort(messageUids);

		return messageUids;
	}

	public User getUser() {
		return _user;
	}

	public boolean isAccountExists(String emailAddress) {
		if (Validator.isNull(getJSONAccount(emailAddress))) {
			return false;
		}
		else {
			return true;
		}
	}

	public long[] search(
		String emailAddress, String folderName, String keywords) {

		User user = getUser();

		long companyId = user.getCompanyId();
		long groupId = user.getGroup().getGroupId();
		long userId = user.getUserId();

		try {
			String escapedFolderName = _encodeFolderName(folderName);

			BooleanQuery contextQuery = BooleanQueryFactoryUtil.create();

			contextQuery.addRequiredTerm(Field.COMPANY_ID, companyId);
			contextQuery.addRequiredTerm(Field.GROUP_ID, groupId);
			contextQuery.addRequiredTerm(Field.PORTLET_ID, Indexer.PORTLET_ID);
			contextQuery.addRequiredTerm(Field.USER_ID, userId);
			contextQuery.addRequiredTerm(Indexer.EMAIL_ADDRESS, emailAddress);

			contextQuery.addRequiredTerm(
				Indexer.FOLDER_NAME, escapedFolderName);

			BooleanQuery fullQuery = BooleanQueryFactoryUtil.create();

			fullQuery.add(contextQuery, BooleanClauseOccur.MUST);

			if (Validator.isNotNull(keywords)) {
				keywords = keywords.trim();

				BooleanQuery searchQuery = BooleanQueryFactoryUtil.create();

				searchQuery.addTerm(Field.TITLE, keywords);
				searchQuery.addTerm(Field.CONTENT, keywords);

				fullQuery.add(searchQuery, BooleanClauseOccur.MUST);
			}

			Hits hits = SearchEngineUtil.search(
				companyId, fullQuery, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			Set<Long> messageUidsSet = new HashSet<Long>();

			for (int i = 0; i < hits.getLength(); i++) {
				Document doc = hits.getDocs()[i];

				String messageUid = doc.get(Field.ENTRY_CLASS_PK);

				messageUidsSet.add(GetterUtil.getLong(messageUid));
			}

			long[] messageUids = new long[messageUidsSet.size()];
			Long[] messageUidsArray = messageUidsSet.toArray(new Long[0]);

			for (int i = 0; i < messageUids.length; i++) {
				messageUids[i] = messageUidsArray[i].longValue();
			}

			Arrays.sort(messageUids);

			return messageUids;
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return new long[0];
	}

	public void updateFolder(
		String emailAddress, JSONObject folder) {

		if (!isAccountExists(emailAddress)) {
			return;
		}

		try {
			String fullName = folder.getString("fullName");
			double messageCount = folder.getInt("messageCount") * 1.0;
			int downloadedMessages = getMessageUidsByFolder(
				emailAddress, fullName).length;

			int percentageDownloaded =
				(int)((downloadedMessages / messageCount) * 100);

			String filePath = _getFolderFilePath( emailAddress, fullName);

			folder.put("percentageDownloaded", percentageDownloaded);
			folder.put("initialized", true);
			folder.put("lastUpdated", new Date());

			FileUtil.write(filePath, folder.toString());
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}
	}

	public void updateMessage(
		String emailAddress, String folderName, long messageUid,
		String flag, boolean value) {

		if (!isAccountExists(emailAddress)) {
			return;
		}

		JSONObject jsonMessage = getJSONMessageByUid(
			emailAddress, folderName, messageUid);

		JSONObject jsonFlags = jsonMessage.getJSONObject("flags");

		jsonFlags.put(flag, value);

		String filePath = getMessageFilePath(
			emailAddress, folderName, messageUid);

		try {
			FileUtil.write(filePath, jsonMessage.toString());
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}
	}

	private String _encodeFolderName(String folderName)
		throws UnsupportedEncodingException {

		return StringUtil.replace(
			URLEncoder.encode(folderName, "UTF-8"), "*", "%2A");
	}

	private String _getAccountFilePath(String emailAddress) {
		return _getAccountPath(emailAddress) + "/account.json";
	}

	private String _getAccountPath(String emailAddress) {
		String pathName = _getUserPath(getUser()) + "/" + emailAddress;

		FileUtil.mkdirs(pathName);

		return pathName;
	}

	private String _getFolderFilePath(
		String emailAddress, String folderName) {

		return _getFolderPath(emailAddress, folderName) + "/folder.json";
	}

	private String _getFolderPath(
		String emailAddress, String folderName) {

		try {
			String escapedFolderName = _encodeFolderName(folderName);

			String pathName =
				_getAccountPath(emailAddress) + "/" + escapedFolderName;

			FileUtil.mkdirs(pathName);

			return pathName;
		}
		catch (UnsupportedEncodingException uee) {
			_log.error(uee, uee);
		}

		return StringPool.BLANK;
	}

	private String _getUserPath(User user) {
		String pathName =
			PortletProps.get("disk.root.dir") + "/" + user.getUserId();

		FileUtil.mkdirs(pathName);

		return pathName;
	}

	private User _user = null;

	private static Log _log = LogFactory.getLog(MessageCache.class);

}