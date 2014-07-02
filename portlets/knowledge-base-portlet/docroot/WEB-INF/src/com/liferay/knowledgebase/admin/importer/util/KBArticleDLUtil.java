/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.knowledgebase.admin.importer.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.NoSuchFolderException;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;

import java.io.InputStream;

import java.util.Map;

/**
 * @author James Hinkey
 * @author Sergio González
 */
public class KBArticleDLUtil {

	public static FileEntry addFile(
			Folder folder, String fileName, InputStream inputStream,
			Map<String, FileEntry> fileEntriesMap,
			ServiceContext serviceContext)
		throws PortalException {

		String mimeType = MimeTypesUtil.getContentType(fileName);

		FileEntry fileEntry = DLAppServiceUtil.addFileEntry(
			folder.getRepositoryId(), folder.getFolderId(), fileName, mimeType,
			fileName, StringPool.BLANK, StringPool.BLANK, inputStream, 0,
			serviceContext);

		fileEntriesMap.put(fileName, fileEntry);

		return fileEntry;
	}

	public static Folder addFolder(
			long groupId, String name, ServiceContext serviceContext)
		throws PortalException {

		return DLAppServiceUtil.addFolder(
			groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, name,
			StringPool.BLANK, serviceContext);
	}

	public static void deleteFolder(long groupId, String name)
		throws PortalException {

		try {
			Folder folder = DLAppServiceUtil.getFolder(
				groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, name);

			DLAppServiceUtil.deleteFolder(folder.getFolderId());
		}
		catch (NoSuchFolderException nsfe) {
		}
	}

}