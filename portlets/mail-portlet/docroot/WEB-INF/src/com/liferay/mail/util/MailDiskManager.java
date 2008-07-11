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

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.util.portlet.PortletProps;

import com.sun.mail.imap.IMAPFolder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.net.URLDecoder;
import java.net.URLEncoder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * <a href="MailDiskManager.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 *
 */
public class MailDiskManager {

	public static String getAccountFilePath(User user, String emailAddress) {
		return getAccountPath(user, emailAddress) + "/account.json";
	}

	public static String getAccountLockPath(User user, String emailAddress) {
		return MailDiskManager.getAccountPath(user, emailAddress) + "/.lock";
	}

	public static String getAccountPath(User user, String emailAddress) {
		String pathName = getUserPath(user) + "/" + emailAddress;

		FileUtil.mkdirs(pathName);

		return pathName;
	}

	public static String getFolderFilePath(
		User user, String emailAddress, String folderName) {

		return getFolderPath(user, emailAddress, folderName) + "/folder.json";
	}

	public static String getFolderPath(
		User user, String emailAddress, String folderName) {

		try {
			String escapedFolderName = StringUtil.replace(
				URLEncoder.encode(folderName, "UTF-8"), '*', "%2A");

			String pathName =
				getAccountPath(user, emailAddress) + "/" + escapedFolderName;

			FileUtil.mkdirs(pathName);

			return pathName;
		}
		catch (UnsupportedEncodingException uee) {
			_log.error(uee, uee);
		}

		return StringPool.BLANK;
	}

	public static JSONObject getJSONAccount(User user, String emailAddress) {
		String accountFilePath = getAccountFilePath(user, emailAddress);

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

		String userPath = getUserPath(user);

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

		String folderFilePath = getFolderFilePath(
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
		if (Validator.isNull(user) || Validator.isNull(emailAddress)) {

			return null;
		}

		String accountPath = getAccountPath(user, emailAddress);

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
		int offset, String searchString) {

		if (Validator.isNull(user) || Validator.isNull(emailAddress) ||
			Validator.isNull(folderName)) {

			return null;
		}

		searchString = searchString.trim();

		long[] messageUids = null;

		if (searchString.equals(StringPool.BLANK)) {
			messageUids = _getMessageUidsByFolder(
				user, emailAddress, folderName);
		}
		else {
			messageUids = _getMessageUidsBySearch(
				user, emailAddress, folderName, searchString);
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

			String folderFilePath = getFolderFilePath(
				user, emailAddress, folderName);

			JSONObject jsonFolderObj = JSONFactoryUtil.createJSONObject(
				FileUtil.read(folderFilePath));

			int messageCount = jsonFolderObj.getInt("messageCount");

			// Disk message count

			long[] messageUids = _getMessageUidsByFolder(
				user, emailAddress, folderName);

			int messagesOnDiskCount = messageUids.length;

			return _getJSONPaginatedMessages(
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
		int messagesPerPage, String searchString) {

		if (Validator.isNull(user) || Validator.isNull(emailAddress) ||
			Validator.isNull(folderName)) {

			return null;
		}

		searchString = searchString.trim();

		long[] messageUids = _getMessageUidsBySearch(
			user, emailAddress, folderName, searchString);

		int messageCount = messageUids.length;

		return _getJSONPaginatedMessages(
			user, emailAddress, folderName, messageUids, pageNumber,
			messagesPerPage, messageCount, messageCount);
	}

	public static String getMessageFilePath(
		User user, String emailAddress, String folderName, long messageUid) {

		return getMessagePath(user, emailAddress, folderName, messageUid) +
			"/message.json";
	}

	public static String getMessagePath(
		User user, String emailAddress, String folderName, long messageUid) {

		String pathName =
			getFolderPath(user, emailAddress, folderName) + "/" + messageUid;

		FileUtil.mkdirs(pathName);

		return pathName;
	}

	public static Message getNewestStoredMessage(
			User user, String emailAddress, String folderName)
		throws MessagingException {

		String folderPath = getFolderPath(user, emailAddress, folderName);

		String[] messageUids = FileUtil.listDirs(folderPath);

		if (messageUids.length == 0) {
			return null;
		}

		// Verify message exists, if not delete message from disk and get next

		MailBoxManager mailBoxManager = new MailBoxManager(
			user, emailAddress);

		Folder folder = mailBoxManager.getFolder(folderName);

		for (int i = messageUids.length - 1; i >= 0; i--) {
			long messageUid = GetterUtil.getLong(messageUids[i]);

			if (messageUid == -1) {
				break;
			}

			folder = mailBoxManager.openFolder(folder);

			Message message = ((IMAPFolder)folder).getMessageByUID(messageUid);

			if (Validator.isNull(message)) {
				mailBoxManager.deleteMessage(folder, messageUid, false);
			}
			else {
				return message;
			}
		}

		return null;
	}

	public static long getOldestStoredMessageUID(
		User user, String emailAddress, String folderName) {

		String folderPath = getFolderPath(user, emailAddress, folderName);

		String[] messageUids = FileUtil.listDirs(folderPath);

		return GetterUtil.getLong(messageUids[0]);
	}

	public static String getUserPath(User user) {
		String pathName =
			PortletProps.get("disk.root.dir") + "/" + user.getUserId();

		FileUtil.mkdirs(pathName);

		return pathName;
	}

	public static boolean isAccountLocked(User user, String emailAddress) {
		boolean locked = false;

		try {
			String filePath = MailDiskManager.getAccountLockPath(
				user, emailAddress);

			if (!FileUtil.exists(filePath)) {
				return false;
			}

			JSONObject jsonObj = JSONFactoryUtil.createJSONObject(
				FileUtil.read(filePath));

			if (GetterUtil.getBoolean(jsonObj.getString("locked"))) {
				DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

				Date dateLocked = GetterUtil.getDate(
					jsonObj.getString("dateLocked"), df);

				Date now = new Date();

				// Lock expires in 5 minutes

				long nowTime = now.getTime();
				long expireTime = dateLocked.getTime() + 300000;

				if (nowTime < expireTime) {
					return true;
				}
			}
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}
		catch (JSONException jsone) {
			_log.error(jsone, jsone);
		}

		return locked;
	}

	private static JSONObject _getJSONPaginatedMessages(
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
			jsonArray.put(
				getJSONMessageByUid(
					user, emailAddress, folderName, messageUidsOnDisk[i]));
		}

		return jsonObj;
	}

	private static long[] _getMessageUidsByFolder(
		User user, String emailAddress, String folderName) {

		String folderPath = getFolderPath(user, emailAddress, folderName);

		long[] messageUids = GetterUtil.getLongValues(
			FileUtil.listDirs(folderPath));

		Arrays.sort(messageUids);

		return messageUids;
	}

	private static long[] _getMessageUidsBySearch(
		User user, String emailAddress, String folderName,
		String searchString) {

		List<String> matchingMessageUids = new ArrayList<String>();

		try {
			long[] allMessageUidsInFolder = _getMessageUidsByFolder(
				user, emailAddress, folderName);

			for (long messageUid : allMessageUidsInFolder) {
				String messageFilePath = getMessageFilePath(
					user, emailAddress, folderName, messageUid);

				String jsonString = FileUtil.read(messageFilePath);

				if (jsonString.indexOf(searchString) != -1) {
					matchingMessageUids.add(String.valueOf(messageUid));
				}
			}
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}

		long[] messageUids = new long[matchingMessageUids.size()];

		for (int i = 0; i < matchingMessageUids.size(); i++) {
			messageUids[i] = GetterUtil.getLong(matchingMessageUids.get(i));
		}

		return messageUids;
	}

	private static Log _log = LogFactory.getLog(MailBoxManager.class);

}