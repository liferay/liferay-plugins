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

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.util.portlet.PortletProps;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="MessageIndexer.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 *
 */
public class MessageIndexer {

	public static void reIndex(String[] ids) throws SystemException {
		if (SearchEngineUtil.isIndexReadOnly()) {
			return;
		}

		long companyId = GetterUtil.getLong(ids[0]);

		String rootPath = PortletProps.get("disk.root.dir") + "/";

		String[] userIds = FileUtil.listDirs(rootPath);

		try {
			for (String userIdString : userIds) {
				long userId = 0;
				long groupId = 0;

				try {
					userId = GetterUtil.getLong(userIdString);

					User user = UserLocalServiceUtil.getUserById(userId);

					groupId = user.getGroup().getGroupId();
				}
				catch (Exception e) {
					_log.error(e, e);

					continue;
				}

				String userPath = rootPath + userId + "/";

				if (!FileUtil.exists(userPath)) {
					continue;
				}

				String[] emailAddresses = FileUtil.listDirs(userPath + "/");

				for (String emailAddress : emailAddresses) {
					_reIndexAccount(
						companyId, groupId, userId, userPath, emailAddress);
				}
			}
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	private static void _reIndexAccount(
			long companyId, long groupId, long userId, String userPath,
			String emailAddress)
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
					companyId, groupId, userId, emailAddress, folderName,
					folderPath, GetterUtil.getLong(messageUid));
			}
		}
	}

	private static void _reIndexMessage(
			long companyId, long groupId, long userId, String emailAddress,
			String folderName, String folderPath, long messageUid)
		throws Exception {

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
			companyId, groupId, userId, emailAddress, folderName, messageUid,
			subject, content);

		SearchEngineUtil.addDocument(companyId, doc);
	}

	private static Log _log = LogFactory.getLog(MessageIndexer.class);

}