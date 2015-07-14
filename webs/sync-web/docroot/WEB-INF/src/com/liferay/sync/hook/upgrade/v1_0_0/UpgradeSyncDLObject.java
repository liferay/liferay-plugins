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

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portlet.documentlibrary.NoSuchFileException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileEntryConstants;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;
import com.liferay.sync.model.SyncConstants;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.service.SyncDLObjectLocalServiceUtil;
import com.liferay.sync.util.SyncUtil;

import java.util.List;

/**
 * @author Dennis Ju
 */
public class UpgradeSyncDLObject extends UpgradeProcess {

	protected void addSyncDLObject(SyncDLObject syncDLObject)
		throws PortalException {

		SyncDLObjectLocalServiceUtil.addSyncDLObject(
			syncDLObject.getCompanyId(), syncDLObject.getUserId(),
			syncDLObject.getUserName(), syncDLObject.getModifiedTime(),
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
	}

	@Override
	protected void doUpgrade() throws Exception {
		try {
			runSQL("drop index IX_69ADEDD1 on SyncDLObject");
		}
		catch (Exception e) {
		}

		updateSyncDLObjects();
	}

	protected void incrementCount() {
		_count++;

		if (_log.isDebugEnabled()) {
			if ((_count % 1000) == 0) {
				_log.debug(
					"Processed " + _count + "/" + _totalCount + " folders " +
						"and files");
			}
		}
	}

	protected void populateAllSyncDLObjects(long groupId)
		throws PortalException {

		if (_log.isDebugEnabled()) {
			_log.debug("Processing group " + groupId);
		}

		_count = 0;

		ActionableDynamicQuery dlFileEntryActionableDynamicQuery =
			DLFileEntryLocalServiceUtil.getActionableDynamicQuery();

		dlFileEntryActionableDynamicQuery.setGroupId(groupId);

		dlFileEntryActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod() {

				@Override
				public void performAction(Object object)
					throws PortalException {

					incrementCount();

					DLFileEntry dlFileEntry = (DLFileEntry)object;

					if (dlFileEntry.getStatus() !=
							WorkflowConstants.STATUS_APPROVED) {

						return;
					}

					try {
						SyncDLObject fileEntrySyncDLObject =
							SyncUtil.toSyncDLObject(
								dlFileEntry, SyncConstants.EVENT_ADD, true);

						String type = fileEntrySyncDLObject.getType();

						if (type.equals(
								SyncConstants.TYPE_PRIVATE_WORKING_COPY)) {

							SyncDLObject approvedSyncDLObject =
								SyncUtil.toSyncDLObject(
									dlFileEntry, SyncConstants.EVENT_ADD, true,
									true);

							addSyncDLObject(approvedSyncDLObject);
						}

						addSyncDLObject(fileEntrySyncDLObject);
					}
					catch (NoSuchFileException nsfe) {
						if (_log.isWarnEnabled()) {
							_log.warn(
								"File missing for file entry " +
									dlFileEntry.getFileEntryId());
						}
					}
				}

			});

		ActionableDynamicQuery dlFolderActionableDynamicQuery =
			DLFolderLocalServiceUtil.getActionableDynamicQuery();

			dlFolderActionableDynamicQuery.setAddCriteriaMethod(
				new ActionableDynamicQuery.AddCriteriaMethod() {

					@Override
					public void addCriteria(DynamicQuery dynamicQuery) {
						Property mountPointProperty =
							PropertyFactoryUtil.forName("mountPoint");

						dynamicQuery.add(mountPointProperty.eq(false));

						Property statusProperty = PropertyFactoryUtil.forName(
							"status");

						dynamicQuery.add(
							statusProperty.eq(
								WorkflowConstants.STATUS_APPROVED));
					}

				});
			dlFolderActionableDynamicQuery.setGroupId(groupId);
			dlFolderActionableDynamicQuery.setPerformActionMethod(
				new ActionableDynamicQuery.PerformActionMethod() {

					@Override
					public void performAction(Object object)
						throws PortalException {

						incrementCount();

						DLFolder dlFolder = (DLFolder)object;

						if (!SyncUtil.isSupportedFolder(dlFolder)) {
							return;
						}

						addSyncDLObject(
							SyncUtil.toSyncDLObject(
								dlFolder, SyncConstants.EVENT_ADD));
					}

				});

		_totalCount =
			dlFileEntryActionableDynamicQuery.performCount() +
				dlFolderActionableDynamicQuery.performCount();

		dlFileEntryActionableDynamicQuery.performActions();

		dlFolderActionableDynamicQuery.performActions();

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Processed " + _count + " files and folders for group " +
					groupId);
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

			populateAllSyncDLObjects(group.getGroupId());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(UpgradeSyncDLObject.class);

	private long _count = 0;
	private long _totalCount = 0;

}