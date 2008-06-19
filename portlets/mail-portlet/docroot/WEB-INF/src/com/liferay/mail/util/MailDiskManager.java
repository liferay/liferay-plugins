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

import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.util.JSONUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.net.URLDecoder;
import java.net.URLEncoder;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

		try {
			if (FileUtil.exists(accountFilePath)) {
				return new JSONObject(FileUtil.read(accountFilePath));
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

		String userPath = getUserPath(user);

		String[] emailAddresses = FileUtil.listDirs(userPath);

		JSONObject jsonObj = new JSONObject();

		// Accounts

		JSONArray jsonArray = new JSONArray();

		JSONUtil.put(jsonObj, "accounts", jsonArray);

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

		String folderFilePath = getFolderFilePath(
			user, emailAddress, folderName);

		try {
			if (FileUtil.exists(folderFilePath)) {
				return new JSONObject(FileUtil.read(folderFilePath));
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

	public static JSONObject getJSONFolders(User user, String emailAddress) {
		String accountPath = getAccountPath(user, emailAddress);

		String[] folderNames = FileUtil.listDirs(accountPath);

		JSONObject jsonObj = new JSONObject();

		JSONArray jsonArray = new JSONArray();

		JSONUtil.put(jsonObj, "folders", jsonArray);

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

				JSONUtil.put(jsonFolder, "name", decodedFolderName);

				jsonArray.put(jsonFolder);
			}
			catch (UnsupportedEncodingException uee) {
				_log.error(uee, uee);
			}
		}

		return jsonObj;
	}

	public static JSONObject getJSONMessageByUid(
		User user, String emailAddress, String folderName,
		String messageUid) {

		String messageFilePath = getMessageFilePath(
			user, emailAddress, folderName, messageUid);

		try {
			if (FileUtil.exists(messageFilePath)) {
				String jsonString = FileUtil.read(messageFilePath);

				JSONObject jsonObj = new JSONObject(jsonString);

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
		User user, String emailAddress, String folderName,
		long messageUid, int offset, String searchString) {

		searchString = searchString.trim();

		String[] messageUids = null;

		if (searchString.equals(StringPool.BLANK)) {
			messageUids = _getMessageUidsByFolder(
				user, emailAddress, folderName);
		}
		else {
			messageUids = _getMessageUidsBySearch(
				user, emailAddress, folderName, searchString);
		}

		for (int i = 0; i < messageUids.length; i++) {
			long tempMessageUid = GetterUtil.getLong(messageUids[i]);

			if (messageUid == tempMessageUid) {
				return getJSONMessageByUid(
					user, emailAddress, folderName, messageUids[i + offset]);
			}
		}

		return null;
	}

	public static JSONObject getJSONMessagesByPage(
		User user, String emailAddress, String folderName,
		int pageNumber, int messagesPerPage) {

		try {

			// Actual message count

			String folderFilePath = getFolderFilePath(
				user, emailAddress, folderName);

			JSONObject jsonFolderObj = new JSONObject(
				FileUtil.read(folderFilePath));

			int messageCount = jsonFolderObj.getInt("messageCount");

			// Disk message count

			String[] messageUids = _getMessageUidsByFolder(
				user, emailAddress, folderName);

			int messagesOnDiskCount = messageUids.length - 1;

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

		searchString = searchString.trim();

		String[] messageUids = _getMessageUidsBySearch(
			user, emailAddress, folderName, searchString);

		int messageCount = messageUids.length;

		return _getJSONPaginatedMessages(
			user, emailAddress, folderName, messageUids, pageNumber,
			messagesPerPage, messageCount, messageCount);
	}

	public static String getMessageFilePath(
		User user, String emailAddress, String folderName,
		String messageUid) {

		return getMessagePath(user, emailAddress, folderName, messageUid) +
			"/message.json";
	}

	public static String getMessagePath(
		User user, String emailAddress, String folderName,
		String messageUid) {

		String pathName =
			getFolderPath(user, emailAddress, folderName) + "/" + messageUid;

		FileUtil.mkdirs(pathName);

		return pathName;
	}

	public static long getNewestStoredMessageUID(
		User user, String emailAddress, String folderName) {

		String folderPath = getFolderPath(user, emailAddress, folderName);

		String[] messageUids = FileUtil.listDirs(folderPath);

		if (messageUids.length == 0) {
			return -1;
		}

		return GetterUtil.getLong(messageUids[messageUids.length - 1]);
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

	private static JSONObject _getJSONPaginatedMessages(
		User user, String emailAddress, String folderName,
		String[] messageUidsOnDisk, int pageNumber, int messagesPerPage,
		int messageCount, int messagesOnDiskCount) {

		int begin = (messagesOnDiskCount + 1) - (messagesPerPage * pageNumber) -
			1;
		int end = messagesOnDiskCount - (messagesPerPage * (pageNumber - 1)) -
			1;

		if (begin < 0) {
			begin = 0;
		}

		double pageCount = Math.ceil(
			(double)messageCount / messagesPerPage);

		JSONObject jsonObj = new JSONObject();

		JSONArray jsonArray = new JSONArray();

		JSONUtil.put(jsonObj, "messages", jsonArray);
		JSONUtil.put(jsonObj, "messageCount", messageCount);
		JSONUtil.put(jsonObj, "messagesPerPage", messagesPerPage);
		JSONUtil.put(jsonObj, "pageCount", (int)pageCount);
		JSONUtil.put(jsonObj, "pageNumber", pageNumber);

		for (int i = begin; i <= end; i++) {
			jsonArray.put(
				getJSONMessageByUid(
					user, emailAddress, folderName, messageUidsOnDisk[i]));
		}

		return jsonObj;
	}

	private static String[] _getMessageUidsByFolder(
		User user, String emailAddress, String folderName) {

		String folderPath = getFolderPath(user, emailAddress, folderName);

		return FileUtil.listDirs(folderPath);
	}

	private static String[] _getMessageUidsBySearch(
		User user, String emailAddress, String folderName,
		String searchString) {

		String folderPath = getFolderPath(user, emailAddress, folderName);

		List<String> matchingMessageUids = new ArrayList<String>();

		try {
			String[] allMessageUidsInFolder = FileUtil.listDirs(folderPath);

			for (String messageUid : allMessageUidsInFolder) {
				String messageFilePath = getMessageFilePath(
					user, emailAddress, folderName, messageUid);

				String jsonString = FileUtil.read(messageFilePath);

				if (jsonString.indexOf(searchString) != -1) {
					matchingMessageUids.add(messageUid);
				}
			}
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}

		return matchingMessageUids.toArray(new String[0]);
	}

	private static Log _log = LogFactory.getLog(MailBoxManager.class);

}