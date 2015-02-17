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

package com.liferay.sync.hook.upgrade.v1_0_0;

import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portlet.documentlibrary.NoSuchFileException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileEntryConstants;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;
import com.liferay.sync.model.SyncConstants;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.service.SyncDLObjectLocalServiceUtil;
import com.liferay.sync.util.SyncUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dennis Ju
 */
public class UpgradeSyncDLObject extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try {
			runSQL("drop index IX_69ADEDD1 on SyncDLObject");
		}
		catch (Exception e) {
		}

		updateSyncDLObjects();
	}

	protected void populateAllSyncDLObjects(
			long groupId, long folderId, List<SyncDLObject> syncDLObjects)
		throws PortalException {

		List<Object> foldersAndFileEntriesAndFileShortcuts =
			DLFolderLocalServiceUtil.getFoldersAndFileEntriesAndFileShortcuts(
				groupId, folderId, null, false,
				new QueryDefinition(WorkflowConstants.STATUS_APPROVED));

		for (Object foldersAndFileEntriesAndFileShortcut :
				foldersAndFileEntriesAndFileShortcuts) {

			if (foldersAndFileEntriesAndFileShortcut instanceof DLFileEntry) {
				DLFileEntry dlFileEntry =
					(DLFileEntry)foldersAndFileEntriesAndFileShortcut;

				String event = StringPool.BLANK;

				if (dlFileEntry.isInTrash()) {
					event = SyncConstants.EVENT_TRASH;
				}
				else {
					event = SyncConstants.EVENT_ADD;
				}

				try {
					SyncDLObject fileEntrySyncDLObject =
						SyncUtil.toSyncDLObject(dlFileEntry, event, true);

					syncDLObjects.add(fileEntrySyncDLObject);

					String type = fileEntrySyncDLObject.getType();

					if (type.equals(SyncConstants.TYPE_PRIVATE_WORKING_COPY)) {
						SyncDLObject approvedSyncDLObject =
							SyncUtil.toSyncDLObject(dlFileEntry, event, true);

						syncDLObjects.add(approvedSyncDLObject);
					}
				}
				catch (NoSuchFileException nsfe) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"File missing for file entry " +
								dlFileEntry.getFileEntryId());
					}
				}
			}
			else if (foldersAndFileEntriesAndFileShortcut instanceof DLFolder) {
				DLFolder dlFolder =
					(DLFolder)foldersAndFileEntriesAndFileShortcut;

				if (!SyncUtil.isSupportedFolder(dlFolder)) {
					continue;
				}

				String event = StringPool.BLANK;

				if (dlFolder.isInTrash()) {
					event = SyncConstants.EVENT_TRASH;
				}
				else {
					event = SyncConstants.EVENT_ADD;
				}

				syncDLObjects.add(SyncUtil.toSyncDLObject(dlFolder, event));

				populateAllSyncDLObjects(
					groupId, dlFolder.getFolderId(), syncDLObjects);
			}
		}
	}

	protected void updateSyncDLObjects() throws Exception {
		SyncDLObjectLocalServiceUtil.deleteSyncDLObjects(
			DLFileEntryConstants.PRIVATE_WORKING_COPY_VERSION,
			SyncConstants.TYPE_FILE);

		List<Group> groups = GroupLocalServiceUtil.getGroups(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Group group : groups) {
			if (group.isStaged()) {
				continue;
			}

			List<SyncDLObject> syncDLObjects = new ArrayList<>();

			populateAllSyncDLObjects(
				group.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
				syncDLObjects);

			for (SyncDLObject syncDLObject : syncDLObjects) {
				SyncDLObjectLocalServiceUtil.addSyncDLObject(
					syncDLObject.getCompanyId(), syncDLObject.getModifiedTime(),
					syncDLObject.getRepositoryId(),
					syncDLObject.getParentFolderId(), syncDLObject.getName(),
					syncDLObject.getExtension(), syncDLObject.getMimeType(),
					syncDLObject.getDescription(), syncDLObject.getChangeLog(),
					syncDLObject.getExtraSettings(), syncDLObject.getVersion(),
					syncDLObject.getVersionId(), syncDLObject.getSize(),
					syncDLObject.getChecksum(), syncDLObject.getEvent(),
					syncDLObject.getLockExpirationDate(),
					syncDLObject.getLockUserId(),
					syncDLObject.getLockUserName(), syncDLObject.getType(),
					syncDLObject.getTypePK(), syncDLObject.getTypeUuid());
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(UpgradeSyncDLObject.class);

}