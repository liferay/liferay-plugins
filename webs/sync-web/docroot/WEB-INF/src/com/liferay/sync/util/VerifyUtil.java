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

package com.liferay.sync.util;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;
import com.liferay.sync.model.SyncConstants;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.service.SyncDLObjectLocalServiceUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Dennis Ju
 */
public class VerifyUtil {

	public static void verify() throws Exception {
		VerifyUtil verifyUtil = new VerifyUtil();

		verifyUtil.doVerify();
	}

	protected void addSyncDLObject(SyncDLObject syncDLObject)
		throws PortalException {

		String event = syncDLObject.getEvent();

		if (event.equals(SyncConstants.EVENT_DELETE) ||
			event.equals(SyncConstants.EVENT_TRASH)) {

			SyncDLObjectLocalServiceUtil.addSyncDLObject(
				0, syncDLObject.getUserId(), syncDLObject.getUserName(),
				syncDLObject.getModifiedTime(), 0, 0, StringPool.BLANK,
				StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
				StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
				StringPool.BLANK, 0, 0, StringPool.BLANK, event, null, 0,
				StringPool.BLANK, syncDLObject.getType(),
				syncDLObject.getTypePK(), StringPool.BLANK);
		}
		else {
			SyncDLObjectLocalServiceUtil.addSyncDLObject(
				syncDLObject.getCompanyId(), syncDLObject.getUserId(),
				syncDLObject.getUserName(), syncDLObject.getModifiedTime(),
				syncDLObject.getRepositoryId(),
				syncDLObject.getParentFolderId(), syncDLObject.getTreePath(),
				syncDLObject.getName(), syncDLObject.getExtension(),
				syncDLObject.getMimeType(), syncDLObject.getDescription(),
				syncDLObject.getChangeLog(), syncDLObject.getExtraSettings(),
				syncDLObject.getVersion(), syncDLObject.getVersionId(),
				syncDLObject.getSize(), syncDLObject.getChecksum(),
				syncDLObject.getEvent(), syncDLObject.getLockExpirationDate(),
				syncDLObject.getLockUserId(), syncDLObject.getLockUserName(),
				syncDLObject.getType(), syncDLObject.getTypePK(),
				syncDLObject.getTypeUuid());
		}
	}

	protected void doVerify() throws Exception {
		List<Group> groups = GroupLocalServiceUtil.getGroups(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Group group : groups) {
			if (group.isStaged()) {
				continue;
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Verifying group " + group.getGroupId());
			}

			verifyDLFileEntriesAndFolders(group.getGroupId());
			verifySyncDLObjects(group.getGroupId());
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Verification completed");
		}
	}

	protected void logCount(long count, long totalCount, String name) {
		if (_log.isDebugEnabled()) {
			if ((count % 1000) == 0) {
				_log.debug("Verified " + count + "/" + totalCount + " " + name);
			}
		}
	}

	protected void verifyDLFileEntriesAndFolders(long groupId)
		throws Exception {

		_dlFoldersAndFileEntriesCount = 0;

		ActionableDynamicQuery dlFolderActionableDynamicQuery =
			DLFolderLocalServiceUtil.getActionableDynamicQuery();

		dlFolderActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					Property mountPointProperty = PropertyFactoryUtil.forName(
						"mountPoint");

					dynamicQuery.add(mountPointProperty.eq(false));

					Property statusProperty = PropertyFactoryUtil.forName(
						"status");

					int[] workflowConstants = new int[] {
						WorkflowConstants.STATUS_APPROVED,
						WorkflowConstants.STATUS_IN_TRASH
					};

					dynamicQuery.add(statusProperty.in(workflowConstants));
				}

			});
		dlFolderActionableDynamicQuery.setGroupId(groupId);
		dlFolderActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<DLFolder>() {

				@Override
				public void performAction(DLFolder dlFolder)
					throws PortalException {

					_dlFoldersAndFileEntriesCount++;

					logCount(
						_dlFoldersAndFileEntriesCount,
						_dlFoldersAndFileEntriesTotalCount,
						"DL folders and DL file entries");

					if (!SyncUtil.isSupportedFolder(dlFolder)) {
						return;
					}

					try {
						SyncDLObject syncDLObject =
							SyncDLObjectLocalServiceUtil.fetchSyncDLObject(
								SyncConstants.TYPE_FOLDER,
								dlFolder.getFolderId());

						Date modifiedDate = dlFolder.getModifiedDate();

						if ((syncDLObject != null) &&
							(syncDLObject.getModifiedTime() >=
								modifiedDate.getTime())) {

							return;
						}

						if (dlFolder.getStatus() ==
								WorkflowConstants.STATUS_APPROVED) {

							addSyncDLObject(
								SyncUtil.toSyncDLObject(
									dlFolder, SyncConstants.EVENT_ADD));
						}
						else {
							addSyncDLObject(
								SyncUtil.toSyncDLObject(
									dlFolder, SyncConstants.EVENT_TRASH));
						}
					}
					catch (Exception e) {
						_log.error(e, e);
					}
				}

			});

		ActionableDynamicQuery dlFileEntryActionableDynamicQuery =
			DLFileEntryLocalServiceUtil.getActionableDynamicQuery();

		dlFileEntryActionableDynamicQuery.setGroupId(groupId);
		dlFileEntryActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<DLFileEntry>() {

				@Override
				public void performAction(DLFileEntry dlFileEntry)
					throws PortalException {

					_dlFoldersAndFileEntriesCount++;

					logCount(
						_dlFoldersAndFileEntriesCount,
						_dlFoldersAndFileEntriesTotalCount,
						"DL folders and DL file entries");

					if ((dlFileEntry.getStatus() !=
							WorkflowConstants.STATUS_APPROVED) &&
						(dlFileEntry.getStatus() !=
							WorkflowConstants.STATUS_IN_TRASH)) {

						return;
					}

					try {
						SyncDLObject fileEntrySyncDLObject =
							SyncDLObjectLocalServiceUtil.fetchSyncDLObject(
								SyncConstants.TYPE_FILE,
								dlFileEntry.getFileEntryId());

						Date modifiedDate = dlFileEntry.getModifiedDate();

						if ((fileEntrySyncDLObject != null) &&
							(fileEntrySyncDLObject.getModifiedTime() >=
								modifiedDate.getTime())) {

							return;
						}

						String event = null;

						if (dlFileEntry.getStatus() ==
								WorkflowConstants.STATUS_APPROVED) {

							event = SyncConstants.EVENT_ADD;
						}
						else {
							event = SyncConstants.EVENT_TRASH;
						}

						if (dlFileEntry.isCheckedOut()) {
							SyncDLObject approvedFileEntrySyncDLObject =
								SyncUtil.toSyncDLObject(
									dlFileEntry, event, true, true);

							addSyncDLObject(approvedFileEntrySyncDLObject);
						}

						fileEntrySyncDLObject = SyncUtil.toSyncDLObject(
							dlFileEntry, event, true);

						addSyncDLObject(fileEntrySyncDLObject);
					}
					catch (Exception e) {
						_log.error(e, e);
					}
				}

			});

		_dlFoldersAndFileEntriesTotalCount =
			dlFolderActionableDynamicQuery.performCount() +
				dlFileEntryActionableDynamicQuery.performCount();

		dlFolderActionableDynamicQuery.performActions();

		dlFileEntryActionableDynamicQuery.performActions();

		logCount(
			_dlFoldersAndFileEntriesCount, _dlFoldersAndFileEntriesTotalCount,
			"DL folders and DL file entries");
	}

	protected void verifySyncDLObjects(final long groupId) throws Exception {
		_syncDLObjectsCount = 0;

		ActionableDynamicQuery syncDLObjectActionableDynamicQuery =
			SyncDLObjectLocalServiceUtil.getActionableDynamicQuery();

		syncDLObjectActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					Property eventProperty = PropertyFactoryUtil.forName(
						"event");

					dynamicQuery.add(
						eventProperty.ne(SyncConstants.EVENT_DELETE));

					Property repositoryIdProperty = PropertyFactoryUtil.forName(
						"repositoryId");

					dynamicQuery.add(repositoryIdProperty.eq(groupId));
				}

			});
		syncDLObjectActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<SyncDLObject>() {

				@Override
				public void performAction(SyncDLObject syncDLObject)
					throws PortalException {

					_syncDLObjectsCount++;

					logCount(
						_syncDLObjectsCount, _syncDLObjectsTotalCount,
						"Sync DL objects");

					String type = syncDLObject.getType();

					if (type.equals(SyncConstants.TYPE_FILE)) {
						DLFileEntry dlFileEntry =
							DLFileEntryLocalServiceUtil.fetchDLFileEntry(
								syncDLObject.getTypePK());

						if (dlFileEntry == null) {
							syncDLObject.setEvent(SyncConstants.EVENT_DELETE);
							syncDLObject.setModifiedTime(
								System.currentTimeMillis());

							addSyncDLObject(syncDLObject);
						}
					}
					else if (type.equals(SyncConstants.TYPE_FOLDER)) {
						DLFolder dlFolder =
							DLFolderLocalServiceUtil.fetchDLFolder(
								syncDLObject.getTypePK());

						if (dlFolder == null) {
							syncDLObject.setEvent(SyncConstants.EVENT_DELETE);
							syncDLObject.setModifiedTime(
								System.currentTimeMillis());

							addSyncDLObject(syncDLObject);
						}
					}
					else if (type.equals(
								SyncConstants.TYPE_PRIVATE_WORKING_COPY)) {

						DLFileEntry dlFileEntry =
							DLFileEntryLocalServiceUtil.fetchDLFileEntry(
								syncDLObject.getTypePK());

						if ((dlFileEntry == null) ||
							!DLFileEntryLocalServiceUtil.isFileEntryCheckedOut(
								syncDLObject.getTypePK())) {

							SyncDLObjectLocalServiceUtil.deleteSyncDLObject(
								syncDLObject);
						}
					}
				}

			});

		_syncDLObjectsTotalCount =
			syncDLObjectActionableDynamicQuery.performCount();

		syncDLObjectActionableDynamicQuery.performActions();

		logCount(
			_syncDLObjectsTotalCount, _syncDLObjectsTotalCount,
			"Sync DL objects");
	}

	private static Log _log = LogFactoryUtil.getLog(VerifyUtil.class);

	private long _dlFoldersAndFileEntriesCount;
	private long _dlFoldersAndFileEntriesTotalCount;
	private long _syncDLObjectsCount;
	private long _syncDLObjectsTotalCount;

}