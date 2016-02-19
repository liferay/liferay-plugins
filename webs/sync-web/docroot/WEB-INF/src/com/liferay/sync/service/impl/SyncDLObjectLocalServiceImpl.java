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

package com.liferay.sync.service.impl;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.model.SyncDLObjectConstants;
import com.liferay.sync.service.base.SyncDLObjectLocalServiceBaseImpl;
import com.liferay.sync.util.PortletPropsValues;

import java.util.Date;
import java.util.List;

/**
 * @author Michael Young
 * @author Dennis Ju
 */
public class SyncDLObjectLocalServiceImpl
	extends SyncDLObjectLocalServiceBaseImpl {

	@Override
	public SyncDLObject addSyncDLObject(
			long companyId, long userId, String userName, long modifiedTime,
			long repositoryId, long parentFolderId, String treePath,
			String name, String extension, String mimeType, String description,
			String changeLog, String extraSettings, String version,
			long versionId, long size, String checksum, String event,
			Date lockExpirationDate, long lockUserId, String lockUserName,
			String type, long typePK, String typeUuid)
		throws PortalException {

		if (!isDefaultRepository(parentFolderId)) {
			return null;
		}

		SyncDLObject syncDLObject = syncDLObjectPersistence.fetchByT_T(
			type, typePK);

		if (syncDLObject == null) {
			long syncDLObjectId = counterLocalService.increment();

			syncDLObject = syncDLObjectPersistence.create(syncDLObjectId);

			syncDLObject.setCompanyId(companyId);
			syncDLObject.setCreateTime(modifiedTime);
			syncDLObject.setRepositoryId(repositoryId);
			syncDLObject.setType(type);
			syncDLObject.setTypePK(typePK);
			syncDLObject.setTypeUuid(typeUuid);

			if (type.equals(SyncDLObjectConstants.TYPE_PRIVATE_WORKING_COPY)) {
				SyncDLObject approvedSyncDLObject =
					syncDLObjectPersistence.fetchByT_T(
						SyncDLObjectConstants.TYPE_FILE, typePK);

				approvedSyncDLObject.setModifiedTime(modifiedTime);
				approvedSyncDLObject.setLockExpirationDate(lockExpirationDate);
				approvedSyncDLObject.setLockUserId(lockUserId);
				approvedSyncDLObject.setLockUserName(lockUserName);

				syncDLObjectPersistence.update(approvedSyncDLObject);
			}
		}
		else if (syncDLObject.getModifiedTime() >= modifiedTime) {
			return null;
		}
		else if (type.equals(SyncDLObjectConstants.TYPE_FILE)) {
			SyncDLObject pwcSyncDLObject = syncDLObjectPersistence.fetchByT_T(
				SyncDLObjectConstants.TYPE_PRIVATE_WORKING_COPY, typePK);

			if (pwcSyncDLObject != null) {
				DLFileEntry dlFileEntry =
					dlFileEntryLocalService.fetchDLFileEntry(typePK);

				if ((dlFileEntry == null) || !dlFileEntry.isCheckedOut()) {
					syncDLObjectPersistence.remove(pwcSyncDLObject);
				}
			}
		}
		else if (type.equals(SyncDLObjectConstants.TYPE_PRIVATE_WORKING_COPY)) {
			if (event.equals(SyncDLObjectConstants.EVENT_RESTORE) ||
				event.equals(SyncDLObjectConstants.EVENT_TRASH)) {

				SyncDLObject approvedSyncDLObject =
					syncDLObjectPersistence.fetchByT_T(
						SyncDLObjectConstants.TYPE_FILE, typePK);

				approvedSyncDLObject.setEvent(event);

				syncDLObjectPersistence.update(approvedSyncDLObject);
			}
		}

		syncDLObject.setUserId(userId);
		syncDLObject.setUserName(userName);
		syncDLObject.setModifiedTime(modifiedTime);
		syncDLObject.setParentFolderId(parentFolderId);
		syncDLObject.setTreePath(treePath);
		syncDLObject.setName(name);
		syncDLObject.setExtension(extension);
		syncDLObject.setMimeType(mimeType);
		syncDLObject.setDescription(description);
		syncDLObject.setChangeLog(changeLog);
		syncDLObject.setVersion(version);
		syncDLObject.setVersionId(versionId);
		syncDLObject.setSize(size);
		syncDLObject.setChecksum(checksum);
		syncDLObject.setEvent(event);
		syncDLObject.setLockExpirationDate(lockExpirationDate);
		syncDLObject.setLockUserId(lockUserId);
		syncDLObject.setLockUserName(lockUserName);

		syncDLObject = syncDLObjectPersistence.update(syncDLObject);

		if (type.equals(SyncDLObjectConstants.TYPE_FILE) &&
			ArrayUtil.contains(
				PortletPropsValues.SYNC_MAC_PACKAGE_METADATA_FILE_NAMES,
				syncDLObject.getName())) {

			SyncDLObject parentFolderSyncDLObject =
				syncDLObjectPersistence.fetchByT_T(
					SyncDLObjectConstants.TYPE_FOLDER,
					syncDLObject.getParentFolderId());

			String parentFolderExtension = FileUtil.getExtension(
				parentFolderSyncDLObject.getName());

			if (ArrayUtil.contains(
					PortletPropsValues.SYNC_MAC_PACKAGE_FOLDER_EXTENSIONS,
					parentFolderExtension)) {

				JSONObject extraSettingsJSONObject =
					JSONFactoryUtil.createJSONObject(
						parentFolderSyncDLObject.getExtraSettings());

				extraSettingsJSONObject.put("macPackage", true);

				parentFolderSyncDLObject.setExtraSettings(
					extraSettingsJSONObject.toString());

				syncDLObjectPersistence.update(parentFolderSyncDLObject);
			}
		}

		if (type.equals(SyncDLObjectConstants.TYPE_FOLDER)) {
			if (Validator.isNull(treePath)) {
				return syncDLObject;
			}

			if (event.equals(SyncDLObjectConstants.EVENT_MOVE)) {
				moveSyncDLObjects(syncDLObject);
			}
			else if (event.equals(SyncDLObjectConstants.EVENT_RESTORE)) {
				restoreSyncDLObjects(syncDLObject);
			}
			else if (event.equals(SyncDLObjectConstants.EVENT_TRASH)) {
				trashSyncDLObjects(syncDLObject);
			}
		}
		else if (event.equals(SyncDLObjectConstants.EVENT_DELETE)) {
			try {
				syncDLFileVersionDiffLocalService.deleteSyncDLFileVersionDiffs(
					typePK);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		return syncDLObject;
	}

	@Override
	public void deleteSyncDLObjects(String version, String type) {
		syncDLObjectPersistence.removeByV_T(version, type);
	}

	@Override
	public SyncDLObject fetchSyncDLObject(String type, long typePK) {
		return syncDLObjectPersistence.fetchByT_T(type, typePK);
	}

	@Override
	public long getLatestModifiedTime() {
		DynamicQuery dynamicQuery = dynamicQuery();

		Projection projection = ProjectionFactoryUtil.max("modifiedTime");

		dynamicQuery.setProjection(projection);

		List<Long> modifiedTimes = syncDLObjectPersistence.findWithDynamicQuery(
			dynamicQuery);

		if (modifiedTimes.isEmpty() || (modifiedTimes.get(0) == 0)) {
			return System.currentTimeMillis();
		}

		return modifiedTimes.get(0);
	}

	@Override
	public List<SyncDLObject> getSyncDLObjects(
		long repositoryId, long parentFolderId) {

		return syncDLObjectPersistence.findByR_P(repositoryId, parentFolderId);
	}

	@Override
	public void moveSyncDLObjects(final SyncDLObject parentSyncDLObject)
		throws PortalException {

		final String searchTreePath = StringUtil.quote(
			String.valueOf(parentSyncDLObject.getTypePK()), StringPool.SLASH);

		ActionableDynamicQuery actionableDynamicQuery =
			getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					dynamicQuery.add(
						RestrictionsFactoryUtil.like(
							"treePath",
							StringUtil.quote(
								searchTreePath, StringPool.PERCENT)));
				}

			});
		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<SyncDLObject>() {

				@Override
				public void performAction(SyncDLObject syncDLObject)
					throws PortalException {

					syncDLObject.setUserId(parentSyncDLObject.getUserId());
					syncDLObject.setUserName(parentSyncDLObject.getUserName());

					String treePath = syncDLObject.getTreePath();

					String oldParentTreePath = treePath.substring(
						0,
						treePath.indexOf(searchTreePath) +
							searchTreePath.length());

					treePath = StringUtil.replaceFirst(
						treePath, oldParentTreePath,
						parentSyncDLObject.getTreePath());

					syncDLObject.setTreePath(treePath);

					syncDLObjectPersistence.update(syncDLObject);
				}

			});

		actionableDynamicQuery.performActions();
	}

	@Override
	public void restoreSyncDLObjects(final SyncDLObject parentSyncDLObject)
		throws PortalException {

		ActionableDynamicQuery actionableDynamicQuery =
			getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					dynamicQuery.add(
						RestrictionsFactoryUtil.eq(
							"event", SyncDLObjectConstants.EVENT_TRASH));

					String treePath = parentSyncDLObject.getTreePath();

					dynamicQuery.add(
						RestrictionsFactoryUtil.like(
							"treePath", treePath + StringPool.PERCENT));
				}

			});
		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<SyncDLObject>() {

				@Override
				public void performAction(SyncDLObject syncDLObject)
					throws PortalException {

					syncDLObject.setUserId(parentSyncDLObject.getUserId());
					syncDLObject.setUserName(parentSyncDLObject.getUserName());
					syncDLObject.setModifiedTime(
						parentSyncDLObject.getModifiedTime());
					syncDLObject.setEvent(SyncDLObjectConstants.EVENT_RESTORE);

					syncDLObjectPersistence.update(syncDLObject);
				}

			});

		actionableDynamicQuery.performActions();
	}

	@Override
	public void trashSyncDLObjects(final SyncDLObject parentSyncDLObject)
		throws PortalException {

		ActionableDynamicQuery actionableDynamicQuery =
			getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					dynamicQuery.add(
						RestrictionsFactoryUtil.ne(
							"event", SyncDLObjectConstants.EVENT_TRASH));

					String treePath = parentSyncDLObject.getTreePath();

					dynamicQuery.add(
						RestrictionsFactoryUtil.like(
							"treePath", treePath + StringPool.PERCENT));
				}

			});
		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<SyncDLObject>() {

				@Override
				public void performAction(SyncDLObject syncDLObject)
					throws PortalException {

					syncDLObject.setUserId(parentSyncDLObject.getUserId());
					syncDLObject.setUserName(parentSyncDLObject.getUserName());
					syncDLObject.setEvent(SyncDLObjectConstants.EVENT_TRASH);

					syncDLObjectPersistence.update(syncDLObject);
				}

			});

		actionableDynamicQuery.performActions();
	}

	protected boolean isDefaultRepository(long folderId)
		throws PortalException {

		if (folderId == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return true;
		}

		Folder folder = dlAppLocalService.getFolder(folderId);

		return folder.isDefaultRepository();
	}

	private static Log _log = LogFactoryUtil.getLog(
		SyncDLObjectLocalServiceImpl.class);

}