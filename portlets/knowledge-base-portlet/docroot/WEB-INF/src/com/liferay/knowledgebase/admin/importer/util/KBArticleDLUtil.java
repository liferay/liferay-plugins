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

import com.liferay.knowledgebase.admin.importer.KBArticleImporterContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.DuplicateFolderNameException;
import com.liferay.portlet.documentlibrary.NoSuchFolderException;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;

import java.io.File;

import java.rmi.RemoteException;

/**
 * @author James Hinkey
 */
public class KBArticleDLUtil {

	public static FileEntry addFile(
			Folder folder, File file, KBArticleImporterContext importerContext)
		throws PortalException, SystemException {

		String name = file.getName();
		String fileName = file.getName();

		if (Validator.isNull(name)) {
			name = fileName;
		}

		ServiceContext serviceContext = importerContext.getServiceContext();

		FileEntry fileEntry = DLAppServiceUtil.addFileEntry(
			folder.getRepositoryId(), folder.getFolderId(), name, fileName,
			name, StringPool.BLANK, StringPool.BLANK, file, serviceContext);

		importerContext.addFileEntry(name, fileEntry);

		return fileEntry;
	}

	public static Folder addFolder(
			String name, KBArticleImporterContext importerContext)
		throws PortalException, SystemException {

		ServiceContext serviceContext = importerContext.getServiceContext();

		Folder folder = null;
		try {
			folder = DLAppServiceUtil.addFolder(
				serviceContext.getScopeGroupId(),
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, name,
				StringPool.BLANK, serviceContext);
		}
		catch (DuplicateFolderNameException dfne) {
			throw new DuplicateFolderNameException();
		}

		return folder;
	}

	public static void deleteFolder(String name, ServiceContext serviceContext)
		throws PortalException, RemoteException, SystemException {

		try {
			Folder imageFolder = DLAppServiceUtil.getFolder(
				serviceContext.getScopeGroupId(),
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, name);

			DLAppServiceUtil.deleteFolder(imageFolder.getFolderId());
		}
		catch (NoSuchFolderException nsfe) {
		}
	}

}