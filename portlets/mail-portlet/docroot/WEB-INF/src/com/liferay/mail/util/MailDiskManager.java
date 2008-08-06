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
import com.liferay.portal.SystemException;
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
public class MailDiskManager {

	public static JSONObject createAccount(User user, MailAccount mailAccount) {
		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		try {
			String filePath = _getAccountFilePath(
				user, mailAccount.getEmailAddress());

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

	public static void createMessage(
		User user, String emailAddress, String folderName, long messageUid,
		JSONObject message) {

		if (!isAccountExists(user, emailAddress)) {
			return;
		}

		String filePath =
			getMessageFilePath(user, emailAddress, folderName, messageUid);

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
				user.getCompanyId(), user.getUserId(), emailAddress,
				_encodeFolderName(folderName), messageUid, subject, body);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public static void deleteAccount(User user, String emailAddress) {
		String accountPath = _getAccountPath(user, emailAddress);

		FileUtil.deltree(accountPath);

		try {
			Indexer.deleteMessages(
				user.getCompanyId(), user.getUserId(), emailAddress);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public static void deleteMessage(
		User user, String emailAddress, String folderName, long messageUid) {

		String messagePath =
			getMessagePath(user, emailAddress, folderName, messageUid);

		FileUtil.deltree(messagePath);

		try {
			Indexer.deleteMessage(
				user.getCompanyId(), user.getUserId(), emailAddress, folderName,
				messageUid);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public static JSONObject getJSONAccount(User user, String emailAddress) {
		String accountFilePath = _getAccountFilePath(user, emailAddress);

		if (Validator.isNull(user) || Validator.isNull(emailAddress)) {
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

	public static JSONObject getJSONAccounts(User user)
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
			JSONObject jsonAccount = getJSONAccount(user, emailAddress);

			// Skip if the account exists but the file does not

			if (Validator.isNull(jsonAccount)) {
				continue;
			}

			jsonArray.put(jsonAccount);
		}

		return jsonObj;
	}

	public static JSONObject getJSONFolder(
		User user, String emailAddress, String folderName) {

		if (Validator.isNull(user) || Validator.isNull(emailAddress) ||
			Validator.isNull(folderName)) {

			return null;
		}

		String folderFilePath = _getFolderFilePath(
			user, emailAddress, folderName);

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

	public static JSONObject getJSONDraftsFolder(
		User user, String emailAddress) {

		if (Validator.isNull(user) || Validator.isNull(emailAddress)) {
			return null;
		}

		JSONObject jsonObj = getJSONFolders(user, emailAddress);

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

	public static JSONObject getJSONFolders(User user, String emailAddress) {
		if (Validator.isNull(user) || Validator.isNull(emailAddress) ||
			user.isDefaultUser()) {

			return null;
		}

		String accountPath = _getAccountPath(user, emailAddress);

		String[] folderNames = FileUtil.listDirs(accountPath);

		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		jsonObj.put("folders", jsonArray);

		for (String folderName : folderNames) {
			try {
				String decodedFolderName = URLDecoder.decode(
					folderName, "UTF-8");

				JSONObject jsonFolder = getJSONFolder(
					user, emailAddress, decodedFolderName);

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

	public static JSONObject getJSONMessageByUid(
		User user, String emailAddress, String folderName, long messageUid) {

		if (Validator.isNull(user) || Validator.isNull(emailAddress) ||
			Validator.isNull(folderName)) {

			return null;
		}

		String messageFilePath = getMessageFilePath(
			user, emailAddress, folderName, messageUid);

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

	public static JSONObject getJSONMessageRelativeToUid(
		User user, String emailAddress, String folderName, long messageUid,
		int offset, String keywords) {

		if (Validator.isNull(user) || Validator.isNull(emailAddress) ||
			Validator.isNull(folderName)) {

			return null;
		}

		long[] messageUids = null;

		if (keywords.equals(StringPool.BLANK)) {
			messageUids = getMessageUidsByFolder(
				user, emailAddress, folderName);
		}
		else {
			messageUids = search(user, emailAddress, folderName, keywords);
		}

		for (int i = 0; i < messageUids.length; i++) {
			if (messageUid == messageUids[i]) {
				return getJSONMessageByUid(
					user, emailAddress, folderName, messageUids[i + offset]);
			}
		}

		return null;
	}

	public static JSONObject getJSONMessagesByPage(
		User user, String emailAddress, String folderName, int pageNumber,
		int messagesPerPage) {

		if (Validator.isNull(user) || Validator.isNull(emailAddress) ||
			Validator.isNull(folderName)) {

			return null;
		}

		try {

			// Actual message count

			String folderFilePath = _getFolderFilePath(
				user, emailAddress, folderName);

			JSONObject jsonFolderObj = JSONFactoryUtil.createJSONObject(
				FileUtil.read(folderFilePath));

			int messageCount = jsonFolderObj.getInt("messageCount");

			// Disk message count

			long[] messageUids = getMessageUidsByFolder(
				user, emailAddress, folderName);

			int messagesOnDiskCount = messageUids.length;

			return getJSONPaginatedMessages(
				user, emailAddress, folderName, messageUids, pageNumber,
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

	public static JSONObject getJSONMessagesBySearch(
		User user, String emailAddress, String folderName, int pageNumber,
		int messagesPerPage, String keywords) {

		if (Validator.isNull(user) || Validator.isNull(emailAddress) ||
			Validator.isNull(folderName)) {

			return null;
		}

		long[] messageUids = search(user, emailAddress, folderName, keywords);

		int messageCount = messageUids.length;

		return getJSONPaginatedMessages(
			user, emailAddress, folderName, messageUids, pageNumber,
			messagesPerPage, messageCount, messageCount);
	}

	public static String getMessageFilePath(
		User user, String emailAddress, String folderName, long messageUid) {

		return
			getMessagePath(user, emailAddress, folderName, messageUid) +
				"/message.json";
	}

	public static String getMessagePath(
		User user, String emailAddress, String folderName, long messageUid) {

		String pathName =
			_getFolderPath(user, emailAddress, folderName) + "/" + messageUid;

		FileUtil.mkdirs(pathName);

		return pathName;
	}

	public static long[] getMessageUidsByFolder(
		User user, String emailAddress, String folderName) {

		String folderPath = _getFolderPath(user, emailAddress, folderName);

		long[] messageUids = GetterUtil.getLongValues(
			FileUtil.listDirs(folderPath));

		Arrays.sort(messageUids);

		return messageUids;
	}

	public static JSONObject getJSONPaginatedMessages(
		User user, String emailAddress, String folderName,
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
			JSONObject message =
				getJSONMessageByUid(
					user, emailAddress, folderName, messageUidsOnDisk[i]);

			if (message != null) {
				jsonArray.put(message);
			}
		}

		return jsonObj;
	}

	public static long[] search(
		User user, String emailAddress, String folderName, String keywords) {

		long companyId = user.getCompanyId();
		long userId = user.getUserId();

		try {
			String escapedFolderName = _encodeFolderName(folderName);

			BooleanQuery contextQuery = BooleanQueryFactoryUtil.create();

			contextQuery.addRequiredTerm(Field.COMPANY_ID, companyId);
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

				fullQuery.add(searchQuery, BooleanClauseOccur.SHOULD);
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Query string: " + fullQuery.toString());
			}

			Hits hits = SearchEngineUtil.search(
				companyId, fullQuery.toString(), SearchEngineUtil.ALL_POS,
				SearchEngineUtil.ALL_POS);

			if (_log.isDebugEnabled()) {
				_log.debug("Hit count: " + hits.getLength());
			}

			Set<Long> messageUidsSet = new HashSet();

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

	public static boolean isAccountExists(User user, String emailAddress) {
		if (Validator.isNull(getJSONAccount(user, emailAddress))) {
			return false;
		}
		else {
			return true;
		}
	}

	public static void reIndex(String[] ids) throws SystemException {
		if (SearchEngineUtil.isIndexReadOnly()) {
			return;
		}

		long companyId = GetterUtil.getLong(ids[0]);

		String rootPath = PortletProps.get("disk.root.dir") + "/";

		String[] userIds = FileUtil.listDirs(rootPath);

		try {
			for (String userId : userIds) {
				String userPath = rootPath + userId + "/";

				if (!FileUtil.exists(userPath)) {
					continue;
				}

				String[] emailAddresses = FileUtil.listDirs(userPath + "/");

				for (String emailAddress : emailAddresses) {
					_reIndexAccount(
						companyId, GetterUtil.getLong(userId), userPath,
						emailAddress);
				}
			}
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public static void updateFolder(
		User user, String emailAddress, JSONObject folder) {

		if (!isAccountExists(user, emailAddress)) {
			return;
		}

		try {
			String fullName = folder.getString("fullName");
			double messageCount = folder.getInt("messageCount") * 1.0;
			int downloadedMessages = getMessageUidsByFolder(
				user, emailAddress, fullName).length;

			int percentageDownloaded =
				(int)((downloadedMessages / messageCount) * 100);

			String filePath = _getFolderFilePath(user, emailAddress, fullName);

			folder.put("percentageDownloaded", percentageDownloaded);
			folder.put("initialized", true);
			folder.put("lastUpdated", new Date());

			FileUtil.write(filePath, folder.toString());
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}
	}

	public static void updateMessage(
		User user, String emailAddress, String folderName, long messageUid,
		String flag, boolean value) {

		if (!isAccountExists(user, emailAddress)) {
			return;
		}

		JSONObject jsonMessage = getJSONMessageByUid(
			user, emailAddress, folderName, messageUid);

		JSONObject jsonFlags = jsonMessage.getJSONObject("flags");

		jsonFlags.put(flag, value);

		String filePath = getMessageFilePath(
			user, emailAddress, folderName, messageUid);

		try {
			FileUtil.write(filePath, jsonMessage.toString());
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}
	}

	private static String _encodeFolderName(String folderName)
		throws UnsupportedEncodingException {

		return StringUtil.replace(
			URLEncoder.encode(folderName, "UTF-8"), "*", "%2A");
	}

	private static String _getAccountFilePath(User user, String emailAddress) {
		return _getAccountPath(user, emailAddress) + "/account.json";
	}

	private static String _getAccountPath(User user, String emailAddress) {
		String pathName = _getUserPath(user) + "/" + emailAddress;

		FileUtil.mkdirs(pathName);

		return pathName;
	}

	private static String _getFolderFilePath(
		User user, String emailAddress, String folderName) {

		return _getFolderPath(user, emailAddress, folderName) + "/folder.json";
	}

	private static String _getFolderPath(
		User user, String emailAddress, String folderName) {

		try {
			String escapedFolderName = _encodeFolderName(folderName);

			String pathName =
				_getAccountPath(user, emailAddress) + "/" + escapedFolderName;

			FileUtil.mkdirs(pathName);

			return pathName;
		}
		catch (UnsupportedEncodingException uee) {
			_log.error(uee, uee);
		}

		return StringPool.BLANK;
	}

	private static String _getUserPath(User user) {
		String pathName =
			PortletProps.get("disk.root.dir") + "/" + user.getUserId();

		FileUtil.mkdirs(pathName);

		return pathName;
	}

	private static void _reIndexAccount(
			long companyId, long userId, String userPath, String emailAddress)
		throws Exception {

		String accountPath = userPath + emailAddress + "/";

		if (!FileUtil.exists(accountPath)) {
			return;
		}

		Indexer.deleteMessages(companyId, userId, emailAddress);

		String[] folders = FileUtil.listDirs(accountPath + "/");

		for (String folderName : folders) {
			String folderPath = accountPath + folderName + "/";

			if (!FileUtil.exists(folderPath)) {
				continue;
			}

			String[] messageUids = FileUtil.listDirs(folderPath + "/");

			for (String messageUid : messageUids) {
				_reIndexMessage(
					companyId, userId, emailAddress, folderName, folderPath,
					GetterUtil.getLong(messageUid));
			}
		}
	}

	private static void _reIndexMessage(
		long companyId, long userId, String emailAddress, String folderName,
		String folderPath, long messageUid) throws Exception {

		String messagePath = folderPath + messageUid + "/";

		if (!FileUtil.exists(messagePath)) {
			return;
		}

		String filePath = messagePath + "/message.json";

		if (!FileUtil.exists(filePath)) {
			return;
		}

		JSONObject jsonObj = JSONFactoryUtil.createJSONObject(
			FileUtil.read(filePath));

		String subject = jsonObj.getString("subject");
		String content = jsonObj.getString("body");

		Document doc = Indexer.getMessageDocument(
			companyId, userId, emailAddress, folderName, messageUid, subject,
			content);

		SearchEngineUtil.addDocument(companyId, doc);
	}

	private static Log _log = LogFactory.getLog(MailDiskManager.class);

}