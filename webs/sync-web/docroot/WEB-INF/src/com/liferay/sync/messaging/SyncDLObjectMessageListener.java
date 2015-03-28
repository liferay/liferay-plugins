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

package com.liferay.sync.messaging;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.NoSuchFolderException;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;
import com.liferay.sync.model.SyncConstants;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.service.SyncDLObjectLocalServiceUtil;
import com.liferay.sync.util.SyncUtil;

import java.util.List;

/**
 * @author Dennis Ju
 */
public class SyncDLObjectMessageListener extends BaseMessageListener {

	protected void addSyncDLObject(
			long modifiedTime, String event, String type, long typePK)
		throws Exception {

		if (event.equals(SyncConstants.EVENT_DELETE)) {
			long userId = 0;
			String userName = StringPool.BLANK;

			PermissionChecker permissionChecker =
				PermissionThreadLocal.getPermissionChecker();

			if (permissionChecker != null) {
				User user = permissionChecker.getUser();

				userId = user.getUserId();
				userName = user.getFullName();
			}

			SyncDLObjectLocalServiceUtil.addSyncDLObject(
				0, userId, userName, modifiedTime, 0, 0, StringPool.BLANK,
				StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
				StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, 0, 0,
				StringPool.BLANK, event, null, 0, StringPool.BLANK, type,
				typePK, StringPool.BLANK);

			return;
		}

		SyncDLObject syncDLObject = null;

		if (type.equals(SyncConstants.TYPE_FILE)) {
			FileEntry fileEntry = null;

			try {
				fileEntry = DLAppLocalServiceUtil.getFileEntry(typePK);
			}
			catch (NoSuchFileEntryException nsfee) {
				return;
			}

			syncDLObject = SyncUtil.toSyncDLObject(fileEntry, event, true);
		}
		else {
			Folder folder = null;

			try {
				folder = DLAppLocalServiceUtil.getFolder(typePK);
			}
			catch (NoSuchFolderException nsfe) {
				return;
			}

			if (!SyncUtil.isSupportedFolder(folder)) {
				return;
			}

			syncDLObject = SyncUtil.toSyncDLObject(folder, event);
		}

		SyncDLObjectLocalServiceUtil.addSyncDLObject(
			syncDLObject.getCompanyId(), syncDLObject.getUserId(),
			syncDLObject.getUserName(), modifiedTime,
			syncDLObject.getRepositoryId(), syncDLObject.getParentFolderId(),
			syncDLObject.getName(), syncDLObject.getExtension(),
			syncDLObject.getMimeType(), syncDLObject.getDescription(),
			syncDLObject.getChangeLog(), syncDLObject.getExtraSettings(),
			syncDLObject.getVersion(), syncDLObject.getVersionId(),
			syncDLObject.getSize(), syncDLObject.getChecksum(),
			syncDLObject.getEvent(), syncDLObject.getLockExpirationDate(),
			syncDLObject.getLockUserId(), syncDLObject.getLockUserName(),
			syncDLObject.getType(), syncDLObject.getTypePK(),
			syncDLObject.getTypeUuid());

		if (event.equals(SyncConstants.EVENT_RESTORE) &&
			type.equals(SyncConstants.TYPE_FOLDER)) {

			List<Object> foldersAndFileEntriesAndFileShortcuts =
				DLAppServiceUtil.getFoldersAndFileEntriesAndFileShortcuts(
					syncDLObject.getRepositoryId(), syncDLObject.getTypePK(),
					WorkflowConstants.STATUS_ANY, false, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			for (Object folderAndFileEntryAndFileShortcut :
					foldersAndFileEntriesAndFileShortcuts) {

				if (folderAndFileEntryAndFileShortcut instanceof FileEntry) {
					FileEntry fileEntry =
						(FileEntry)folderAndFileEntryAndFileShortcut;

					addSyncDLObject(
						modifiedTime, SyncConstants.EVENT_RESTORE,
						SyncConstants.TYPE_FILE, fileEntry.getFileEntryId());
				}
				else if (folderAndFileEntryAndFileShortcut instanceof Folder) {
					Folder folder = (Folder)folderAndFileEntryAndFileShortcut;

					if (!SyncUtil.isSupportedFolder(folder)) {
						continue;
					}

					addSyncDLObject(
						modifiedTime, SyncConstants.EVENT_RESTORE,
						SyncConstants.TYPE_FOLDER, folder.getFolderId());
				}
			}
		}
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		String event = message.getString("event");
		long modifiedTime = message.getLong("modifiedTime");
		String type = message.getString("type");
		long typePK = message.getLong("typePK");

		addSyncDLObject(modifiedTime, event, type, typePK);
	}

}