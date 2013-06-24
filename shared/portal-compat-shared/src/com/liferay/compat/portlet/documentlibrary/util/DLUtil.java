/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.compat.portlet.documentlibrary.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class DLUtil {

	public static final String MANUAL_CHECK_IN_REQUIRED =
		"manualCheckInRequired";

	public static final String MANUAL_CHECK_IN_REQUIRED_PATH =
		StringPool.SLASH + MANUAL_CHECK_IN_REQUIRED;

	public static final String WEBDAV_CHECK_IN_MODE = "webDAVCheckInMode";

	public static String getWebDavURL(
			ThemeDisplay themeDisplay, Folder folder, FileEntry fileEntry,
			boolean manualCheckInRequired)
		throws PortalException, SystemException {

		StringBuilder sb = new StringBuilder();

		if (folder != null) {
			Folder curFolder = folder;

			while (true) {
				sb.insert(0, HttpUtil.encodeURL(curFolder.getName(), true));
				sb.insert(0, StringPool.SLASH);

				if (curFolder.getParentFolderId() ==
						DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

					break;
				}
				else {
					curFolder = DLAppLocalServiceUtil.getFolder(
						curFolder.getParentFolderId());
				}
			}
		}

		if (fileEntry != null) {
			sb.append(StringPool.SLASH);
			sb.append(HttpUtil.encodeURL(fileEntry.getTitle(), true));
		}

		Group group = themeDisplay.getScopeGroup();

		StringBundler webDavURL = new StringBundler(7);

		webDavURL.append(themeDisplay.getPortalURL());
		webDavURL.append(themeDisplay.getPathContext());
		webDavURL.append("/api/secure/webdav");

		if (manualCheckInRequired) {
			webDavURL.append(MANUAL_CHECK_IN_REQUIRED_PATH);
		}

		webDavURL.append(group.getFriendlyURL());
		webDavURL.append("/document_library");
		webDavURL.append(sb.toString());

		return webDavURL.toString();
	}

}