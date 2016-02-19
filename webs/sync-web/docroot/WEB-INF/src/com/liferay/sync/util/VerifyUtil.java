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
import com.liferay.portal.kernel.exception.SystemException;
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
import com.liferay.portlet.documentlibrary.service.persistence.DLFileEntryActionableDynamicQuery;
import com.liferay.portlet.documentlibrary.service.persistence.DLFolderActionableDynamicQuery;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.model.SyncDLObjectConstants;
import com.liferay.sync.service.SyncDLObjectLocalServiceUtil;
import com.liferay.sync.service.persistence.SyncDLObjectActionableDynamicQuery;

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
			new DLFolderActionableDynamicQuery() {

				@Override
				protected void addCriteria(DynamicQuery dynamicQuery) {
					Property hiddenProperty = PropertyFactoryUtil.forName(
						"hidden");

					dynamicQuery.add(hiddenProperty.eq(false));

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

				@Override
				protected void performAction(Object object)
					throws PortalException, SystemException {

					_dlFoldersAndFileEntriesCount++;

					logCount(
						_dlFoldersAndFileEntriesCount,
						_dlFoldersAndFileEntriesTotalCount,
						"DL folders and DL file entries");

					DLFolder dlFolder = (DLFolder)object;

					try {
						if (dlFolder.getStatus() ==
								WorkflowConstants.STATUS_APPROVED) {

							SyncUtil.addSyncDLObject(
								SyncUtil.toSyncDLObject(
									dlFolder, 0, StringPool.BLANK,
									SyncDLObjectConstants.EVENT_ADD));
						}
						else {
							SyncUtil.addSyncDLObject(
								SyncUtil.toSyncDLObject(
									dlFolder, 0, StringPool.BLANK,
									SyncDLObjectConstants.EVENT_TRASH));
						}
					}
					catch (Exception e) {
						_log.error(e, e);
					}
				}

			};

		dlFolderActionableDynamicQuery.setGroupId(groupId);

		ActionableDynamicQuery dlFileEntryActionableDynamicQuery =
			new DLFileEntryActionableDynamicQuery() {

				@Override
				protected void performAction(Object object)
					throws PortalException, SystemException {

					_dlFoldersAndFileEntriesCount++;

					logCount(
						_dlFoldersAndFileEntriesCount,
						_dlFoldersAndFileEntriesTotalCount,
						"DL folders and DL file entries");

					DLFileEntry dlFileEntry = (DLFileEntry)object;

					if ((dlFileEntry.getStatus() !=
							WorkflowConstants.STATUS_APPROVED) &&
						!dlFileEntry.isInTrash()) {

						return;
					}

					try {
						SyncDLObject syncDLObject =
							SyncDLObjectLocalServiceUtil.fetchSyncDLObject(
								SyncDLObjectConstants.TYPE_FILE,
								dlFileEntry.getFileEntryId());

						if (syncDLObject != null) {
							Date modifiedDate = dlFileEntry.getModifiedDate();

							if (syncDLObject.getModifiedTime() >=
									modifiedDate.getTime()) {

								return;
							}
						}

						String event = null;

						if (dlFileEntry.getStatus() ==
								WorkflowConstants.STATUS_APPROVED) {

							event = SyncDLObjectConstants.EVENT_ADD;
						}
						else {
							event = SyncDLObjectConstants.EVENT_TRASH;
						}

						if (dlFileEntry.isCheckedOut()) {
							SyncDLObject approvedFileEntrySyncDLObject =
								SyncUtil.toSyncDLObject(
									dlFileEntry, event,
									!dlFileEntry.isInTrash(), true);

							SyncUtil.addSyncDLObject(
								approvedFileEntrySyncDLObject);
						}

						SyncUtil.addSyncDLObject(
							SyncUtil.toSyncDLObject(
								dlFileEntry, event, !dlFileEntry.isInTrash()));
					}
					catch (Exception e) {
						_log.error(e, e);
					}
				}

			};

		dlFileEntryActionableDynamicQuery.setGroupId(groupId);

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

		ActionableDynamicQuery actionableDynamicQuery =
			new SyncDLObjectActionableDynamicQuery() {

				@Override
				protected void addCriteria(DynamicQuery dynamicQuery) {
					Property eventProperty = PropertyFactoryUtil.forName(
						"event");

					dynamicQuery.add(
						eventProperty.ne(SyncDLObjectConstants.EVENT_DELETE));

					Property repositoryIdProperty = PropertyFactoryUtil.forName(
						"repositoryId");

					dynamicQuery.add(repositoryIdProperty.eq(groupId));
				}

				@Override
				protected void performAction(Object object)
					throws PortalException, SystemException {

					_syncDLObjectsCount++;

					logCount(
						_syncDLObjectsCount, _syncDLObjectsTotalCount,
						"Sync DL objects");

					SyncDLObject syncDLObject = (SyncDLObject)object;

					String type = syncDLObject.getType();

					if (type.equals(SyncDLObjectConstants.TYPE_FILE)) {
						DLFileEntry dlFileEntry =
							DLFileEntryLocalServiceUtil.fetchDLFileEntry(
								syncDLObject.getTypePK());

						if (dlFileEntry == null) {
							syncDLObject.setEvent(
								SyncDLObjectConstants.EVENT_DELETE);
							syncDLObject.setModifiedTime(
								System.currentTimeMillis());

							SyncUtil.addSyncDLObject(syncDLObject);
						}
					}
					else if (type.equals(SyncDLObjectConstants.TYPE_FOLDER)) {
						DLFolder dlFolder =
							DLFolderLocalServiceUtil.fetchDLFolder(
								syncDLObject.getTypePK());

						if (dlFolder == null) {
							syncDLObject.setEvent(
								SyncDLObjectConstants.EVENT_DELETE);
							syncDLObject.setModifiedTime(
								System.currentTimeMillis());

							SyncUtil.addSyncDLObject(syncDLObject);
						}
					}
					else if (type.equals(
								SyncDLObjectConstants.
									TYPE_PRIVATE_WORKING_COPY)) {

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

			};

		_syncDLObjectsTotalCount = actionableDynamicQuery.performCount();

		actionableDynamicQuery.performActions();

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