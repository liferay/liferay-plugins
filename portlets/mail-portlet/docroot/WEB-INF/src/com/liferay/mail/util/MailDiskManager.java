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

	public static final String MAIL_ROOT_DIR = "d:/mailbox";

	public static String getAccountFilePath(
		User user, MailAccount mailAccount) {

		return getAccountPath(user, mailAccount) + "/account.json";
	}

	public static String getAccountPath(User user, MailAccount mailAccount) {
		String pathName =
			MAIL_ROOT_DIR + "/" + user.getUserId() + "/" +
				mailAccount.getEmailAddress();

		FileUtil.mkdirs(pathName);

		return pathName;
	}

	public static String getFolderFilePath(
		User user, MailAccount mailAccount, String folderName) {

		return getFolderPath(user, mailAccount, folderName) + "/folder.json";
	}

	public static String getFolderPath(
		User user, MailAccount mailAccount, String folderName) {

		try {
			String escapedFolderName = StringUtil.replace(
				URLEncoder.encode(folderName, "UTF-8"), '*', "%2A");

			String pathName =
				getAccountPath(user, mailAccount) + "/" + escapedFolderName;

			FileUtil.mkdirs(pathName);

			return pathName;
		}
		catch (UnsupportedEncodingException uee) {
			_log.error(uee, uee);
		}

		return StringPool.BLANK;
	}

	public static JSONObject getJSONAccount(
		User user, MailAccount mailAccount) {

		String accountFilePath = getAccountFilePath(user, mailAccount);

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

	public static JSONObject getJSONFolder(
		User user, MailAccount mailAccount, String folderName) {

		String folderFilePath = getFolderFilePath(
			user, mailAccount, folderName);

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

	public static JSONObject getJSONFolders(
		User user, MailAccount mailAccount) {

		String accountPath = getAccountPath(user, mailAccount);

		String[] folderNames = FileUtil.listDirs(accountPath);

		JSONObject jsonObj = new JSONObject();

		JSONArray jsonArray = new JSONArray();

		JSONUtil.put(jsonObj, "folders", jsonArray);

		for (String folderName : folderNames) {
			try {
				String decodedFolderName = URLDecoder.decode(
					folderName, "UTF-8");

				JSONObject jsonFolder = getJSONFolder(
					user, mailAccount, decodedFolderName);

				// Skip if folder exists but file does not

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
		User user, MailAccount mailAccount, String folderName,
		String messageUid) {

		String messageFilePath = getMessageFilePath(
			user, mailAccount, folderName, messageUid);

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
		User user, MailAccount mailAccount, String folderName,
		long messageUid, int offset) {

		String folderPath = getFolderPath(user, mailAccount, folderName);

		String[] messageUids = FileUtil.listDirs(folderPath);

		for (int i = 0; i < messageUids.length; i++) {
			long tempMessageUid = GetterUtil.getLong(messageUids[i]);

			if (messageUid == tempMessageUid) {
				return getJSONMessageByUid(
					user, mailAccount, folderName, messageUids[i + offset]);
			}
		}

		return null;
	}

	public static JSONObject getJSONMessagesByPage(
		User user, MailAccount mailAccount, String folderName,
		int pageNumber, int messagesPerPage) {

		String folderPath = getFolderPath(user, mailAccount, folderName);
		String folderFilePath = getFolderFilePath(
			user, mailAccount, folderName);

		try {
			JSONObject jsonFolderObj = new JSONObject(
				FileUtil.read(folderFilePath));

			String[] messageUids = FileUtil.listDirs(folderPath);

			int messageCount = jsonFolderObj.getInt("messageCount");
			int messageUidCount = messageUids.length - 1;

			int begin = (messageUidCount + 1) - (messagesPerPage * pageNumber);
			int end = messageUidCount - (messagesPerPage * (pageNumber - 1));

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
						user, mailAccount, folderName, messageUids[i]));
			}

			return jsonObj;
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
		User user, MailAccount mailAccount, String folderName, int pageNumber,
		int messagesPerPage, String searchString) {

		String folderPath = getFolderPath(user, mailAccount, folderName);
		String folderFilePath = getFolderFilePath(
			user, mailAccount, folderName);

		try {
			JSONObject jsonFolderObj = new JSONObject(
				FileUtil.read(folderFilePath));

			String[] messageUids = FileUtil.listDirs(folderPath);

			List matchingMessageUids = new ArrayList();

			for (String messageUid : messageUids) {
				String messageFilePath = getMessageFilePath(
					user, mailAccount, folderName, messageUid);

				String jsonString = FileUtil.read(messageFilePath);

				if (jsonString.indexOf(searchString) != -1) {
					matchingMessageUids.add(messageUid);
				}
			}

			int messageCount = matchingMessageUids.size();

			int begin = (messageCount + 1) - (messagesPerPage * pageNumber);
			int end = messageCount - (messagesPerPage * (pageNumber - 1)) - 1;

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
						user, mailAccount, folderName,
						matchingMessageUids.get(i).toString()));
			}

			return jsonObj;
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}
		catch (JSONException jsone) {
			_log.error(jsone, jsone);
		}

		return null;
	}

	public static String getMessageFilePath(
		User user, MailAccount mailAccount, String folderName,
		String messageUid) {

		return getMessagePath(user, mailAccount, folderName, messageUid) +
			"/message.json";
	}

	public static String getMessagePath(
		User user, MailAccount mailAccount, String folderName,
		String messageUid) {

		String pathName =
			getFolderPath(user, mailAccount, folderName) + "/" + messageUid;

		FileUtil.mkdirs(pathName);

		return pathName;
	}

	public static long getNewestStoredMessageUID(
		User user, MailAccount mailAccount, String folderName) {

		String folderPath = getFolderPath(user, mailAccount, folderName);

		String[] messageUids = FileUtil.listDirs(folderPath);

		if (messageUids.length == 0) {
			return -1;
		}

		return GetterUtil.getLong(messageUids[messageUids.length - 1]);
	}

	public static long getOldestStoredMessageUID(
		User user, MailAccount mailAccount, String folderName) {

		String folderPath = getFolderPath(user, mailAccount, folderName);

		String[] messageUids = FileUtil.listDirs(folderPath);

		return GetterUtil.getLong(messageUids[0]);
	}

	private static Log _log = LogFactory.getLog(MailBoxManager.class);

}