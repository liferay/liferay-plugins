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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.deploy.DeployManagerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Repository;
import com.liferay.portal.model.User;
import com.liferay.portal.security.ac.AccessControlled;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.comparator.GroupNameComparator;
import com.liferay.portlet.documentlibrary.DuplicateFileException;
import com.liferay.portlet.documentlibrary.DuplicateFolderNameException;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.NoSuchFolderException;
import com.liferay.portlet.documentlibrary.model.DLFileEntryConstants;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.model.DLSyncEvent;
import com.liferay.portlet.documentlibrary.service.DLSyncEventLocalServiceUtil;
import com.liferay.portlet.trash.util.TrashUtil;
import com.liferay.sync.SyncDLObjectChecksumException;
import com.liferay.sync.model.SyncConstants;
import com.liferay.sync.model.SyncContext;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.model.SyncDLObjectUpdate;
import com.liferay.sync.service.base.SyncDLObjectServiceBaseImpl;
import com.liferay.sync.util.PortletPropsKeys;
import com.liferay.sync.util.PortletPropsValues;
import com.liferay.sync.util.SyncUtil;
import com.liferay.util.portlet.PortletProps;

import java.io.File;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.portlet.PortletPreferences;

/**
 * @author Michael Young
 * @author Dennis Ju
 */
public class SyncDLObjectServiceImpl extends SyncDLObjectServiceBaseImpl {

	@Override
	public SyncDLObject addFileEntry(
			long repositoryId, long folderId, String sourceFileName,
			String mimeType, String title, String description, String changeLog,
			File file, String checksum, ServiceContext serviceContext)
		throws PortalException {

		try {
			validateChecksum(file, checksum);

			FileEntry fileEntry = dlAppService.addFileEntry(
				repositoryId, folderId, sourceFileName, mimeType, title,
				description, changeLog, file, serviceContext);

			return toSyncDLObject(fileEntry, SyncConstants.EVENT_ADD);
		}
		catch (PortalException pe) {
			if (pe instanceof DuplicateFileException) {
				if (GetterUtil.getBoolean(
						serviceContext.getAttribute("overwrite"))) {

					FileEntry fileEntry = dlAppService.getFileEntry(
						repositoryId, folderId, title);

					fileEntry = dlAppService.updateFileEntry(
						fileEntry.getFileEntryId(), sourceFileName, mimeType,
						title, description, changeLog, false, file,
						serviceContext);

					return toSyncDLObject(
						fileEntry, SyncConstants.EVENT_UPDATE);
				}
			}

			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
	}

	@Override
	public SyncDLObject addFolder(
			long repositoryId, long parentFolderId, String name,
			String description, ServiceContext serviceContext)
		throws PortalException {

		try {
			Folder folder = dlAppService.addFolder(
				repositoryId, parentFolderId, name, description,
				serviceContext);

			return toSyncDLObject(folder, SyncConstants.EVENT_ADD);
		}
		catch (PortalException pe) {
			if (pe instanceof DuplicateFolderNameException) {
				if (GetterUtil.getBoolean(
						serviceContext.getAttribute("overwrite"))) {

					Folder folder = dlAppService.getFolder(
						repositoryId, parentFolderId, name);

					folder = dlAppService.updateFolder(
						folder.getFolderId(), name, description,
						serviceContext);

					return toSyncDLObject(folder, SyncConstants.EVENT_UPDATE);
				}
			}

			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
	}

	@Override
	public SyncDLObject cancelCheckOut(long fileEntryId)
		throws PortalException {

		try {
			dlAppService.cancelCheckOut(fileEntryId);

			FileEntry fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

			return toSyncDLObject(
				fileEntry, SyncConstants.EVENT_CANCEL_CHECK_OUT);
		}
		catch (PortalException pe) {
			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
	}

	@Override
	public SyncDLObject checkInFileEntry(
			long fileEntryId, boolean majorVersion, String changeLog,
			ServiceContext serviceContext)
		throws PortalException {

		try {
			dlAppService.checkInFileEntry(
				fileEntryId, majorVersion, changeLog, serviceContext);

			FileEntry fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

			return toSyncDLObject(fileEntry, SyncConstants.EVENT_CHECK_IN);
		}
		catch (PortalException pe) {
			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
	}

	@Override
	public SyncDLObject checkOutFileEntry(
			long fileEntryId, ServiceContext serviceContext)
		throws PortalException {

		try {
			dlAppService.checkOutFileEntry(fileEntryId, serviceContext);

			FileEntry fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

			return toSyncDLObject(fileEntry, SyncConstants.EVENT_CHECK_OUT);
		}
		catch (PortalException pe) {
			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
	}

	@Override
	public SyncDLObject checkOutFileEntry(
			long fileEntryId, String owner, long expirationTime,
			ServiceContext serviceContext)
		throws PortalException {

		try {
			FileEntry fileEntry = dlAppService.checkOutFileEntry(
				fileEntryId, owner, expirationTime, serviceContext);

			return toSyncDLObject(fileEntry, SyncConstants.EVENT_CHECK_OUT);
		}
		catch (PortalException pe) {
			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
	}

	@Override
	public List<SyncDLObject> getAllFolderSyncDLObjects(
			long companyId, long repositoryId)
		throws PortalException {

		try {
			repositoryService.checkRepository(repositoryId);

			return syncDLObjectFinder.filterFindByC_R(companyId, repositoryId);
		}
		catch (PortalException pe) {
			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public SyncDLObjectUpdate getAllSyncDLObjects(
			long repositoryId, long folderId)
		throws PortalException {

		try {
			long lastAccessTime = System.currentTimeMillis();

			long companyId = 0;

			Repository repository = repositoryLocalService.fetchRepository(
				repositoryId);

			if (repository != null) {
				companyId = repository.getCompanyId();
			}
			else {
				Group group = groupLocalService.getGroup(repositoryId);

				companyId = group.getCompanyId();
			}

			List<SyncDLObject> syncDLObjects =
				syncDLObjectPersistence.findByC_M_R(companyId, 0, repositoryId);

			return new SyncDLObjectUpdate(syncDLObjects, lastAccessTime);
		}
		catch (PortalException pe) {
			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
	}

	@Override
	public SyncDLObject getFileEntrySyncDLObject(
			long groupId, long folderId, String title)
		throws PortalException {

		try {
			FileEntry fileEntry = dlAppService.getFileEntry(
				groupId, folderId, title);

			return toSyncDLObject(fileEntry, SyncConstants.EVENT_GET);
		}
		catch (PortalException pe) {
			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
	}

	@Override
	public List<SyncDLObject> getFileEntrySyncDLObjects(
			long repositoryId, long folderId)
		throws PortalException {

		try {
			List<FileEntry> fileEntries = dlAppService.getFileEntries(
				repositoryId, folderId);

			List<SyncDLObject> syncDLObjects = new ArrayList<SyncDLObject>(
				fileEntries.size());

			for (FileEntry fileEntry : fileEntries) {
				SyncDLObject syncDLObject = toSyncDLObject(
					fileEntry, SyncConstants.EVENT_GET);

				syncDLObjects.add(syncDLObject);
			}

			return syncDLObjects;
		}
		catch (PortalException pe) {
			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
	}

	@Override
	public SyncDLObject getFolderSyncDLObject(long folderId)
		throws PortalException {

		try {
			Folder folder = dlAppService.getFolder(folderId);

			if (!SyncUtil.isSupportedFolder(folder)) {
				return null;
			}

			return toSyncDLObject(folder, SyncConstants.EVENT_GET);
		}
		catch (PortalException pe) {
			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
	}

	@Override
	public SyncDLObject getFolderSyncDLObject(
			long repositoryId, long parentFolderId, String name)
		throws PortalException {

		try {
			Folder folder = dlAppService.getFolder(
				repositoryId, parentFolderId, name);

			if (!SyncUtil.isSupportedFolder(folder)) {
				return null;
			}

			return toSyncDLObject(folder, SyncConstants.EVENT_GET);
		}
		catch (PortalException pe) {
			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
	}

	@Override
	public List<SyncDLObject> getFolderSyncDLObjects(
			long repositoryId, long parentFolderId)
		throws PortalException {

		try {
			List<Folder> folders = dlAppService.getFolders(
				repositoryId, parentFolderId);

			List<SyncDLObject> syncDLObjects = new ArrayList<SyncDLObject>(
				folders.size());

			for (Folder folder : folders) {
				if (!SyncUtil.isSupportedFolder(folder)) {
					continue;
				}

				SyncDLObject syncDLObject = toSyncDLObject(
					folder, SyncConstants.EVENT_GET);

				syncDLObjects.add(syncDLObject);
			}

			return syncDLObjects;
		}
		catch (PortalException pe) {
			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
	}

	@Override
	public Group getGroup(long groupId) throws PortalException {
		try {
			return groupService.getGroup(groupId);
		}
		catch (PortalException pe) {
			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
	}

	@Override
	public long getLatestModifiedTime() {
		return syncDLObjectLocalService.getLatestModifiedTime();
	}

	@Override
	public PortletPreferences getPortletPreferences() throws PortalException {
		User user = getUser();

		PortletPreferences portletPreferences = PrefsPropsUtil.getPreferences(
			user.getCompanyId());

		Properties properties = PortletProps.getProperties();

		for (Map.Entry<Object, Object> entry : properties.entrySet()) {
			String key = String.valueOf(entry.getKey());
			String value = String.valueOf(entry.getValue());

			if (portletPreferences.getValue(key, null) != null) {
				continue;
			}

			try {
				portletPreferences.setValue(key, value);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		return portletPreferences;
	}

	@AccessControlled(guestAccessEnabled = true)
	@Override
	public SyncContext getSyncContext(String uuid) throws PortalException {
		try {
			User user = getGuestOrUser();

			SyncContext syncContext = new SyncContext();

			PluginPackage syncWebPluginPackage =
				DeployManagerUtil.getInstalledPluginPackage("sync-web");

			syncContext.setPluginVersion(syncWebPluginPackage.getVersion());

			if (!user.isDefaultUser()) {
				syncContext.setPortalBuildNumber(ReleaseInfo.getBuildNumber());

				PluginPackage soPortletPluginPackage =
					DeployManagerUtil.getInstalledPluginPackage("so-portlet");

				syncContext.setPortletPreferencesMap(
					getPortletPreferencesMap());

				if (soPortletPluginPackage != null) {
					syncContext.setSocialOfficeInstalled(true);
				}
				else {
					syncContext.setSocialOfficeInstalled(false);
				}

				syncContext.setUser(user);
				syncContext.setUserSitesGroups(getUserSitesGroups());
			}

			return syncContext;
		}
		catch (PortalException pe) {
			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
	}

	@Override
	public SyncDLObjectUpdate getSyncDLObjectUpdate(
			long companyId, long repositoryId, long lastAccessTime)
		throws PortalException {

		try {
			repositoryService.checkRepository(repositoryId);

			List<SyncDLObject> syncDLObjects =
				syncDLObjectFinder.filterFindByC_M_R_P(
					companyId, lastAccessTime, repositoryId, -1);

			for (SyncDLObject syncDLObject : syncDLObjects) {
				if (syncDLObject.getModifiedTime() > lastAccessTime) {
					lastAccessTime = syncDLObject.getModifiedTime();
				}
			}

			return new SyncDLObjectUpdate(syncDLObjects, lastAccessTime);
		}
		catch (PortalException pe) {
			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
	}

	@Override
	public SyncDLObjectUpdate getSyncDLObjectUpdate(
			long companyId, long repositoryId, long parentFolderId,
			long lastAccessTime)
		throws PortalException {

		try {
			repositoryService.checkRepository(repositoryId);

			List<SyncDLObject> syncDLObjects = new ArrayList<SyncDLObject>();

			if (parentFolderId > 0) {
				PermissionChecker permissionChecker = getPermissionChecker();

				if (permissionChecker.hasPermission(
						repositoryId, DLFolderConstants.getClassName(),
						parentFolderId, ActionKeys.VIEW)) {

					SyncDLObject syncDLObject =
						syncDLObjectPersistence.fetchByT_T(
							SyncConstants.TYPE_FOLDER, parentFolderId);

					if (syncDLObject != null) {
						syncDLObjects.add(syncDLObject);
					}
				}
			}

			syncDLObjects = getSyncDLObjects(
				syncDLObjects, companyId, repositoryId, parentFolderId,
				lastAccessTime);

			return new SyncDLObjectUpdate(syncDLObjects, lastAccessTime);
		}
		catch (PortalException pe) {
			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
	}

	@Override
	public List<Group> getUserSitesGroups() throws PortalException {
		try {
			User user = getUser();

			List<Group> groups = new ArrayList<Group>();

			LinkedHashMap<String, Object> groupParams =
				new LinkedHashMap<String, Object>();

			groupParams.put("active", true);
			groupParams.put("usersGroups", user.getUserId());

			List<Group> userSiteGroups = groupLocalService.search(
				user.getCompanyId(), null, groupParams, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

			for (Group userSiteGroup : userSiteGroups) {
				if (isSyncEnabled(userSiteGroup)) {
					if (userSiteGroup.isGuest()) {
						userSiteGroup.setName(
							userSiteGroup.getDescriptiveName());
					}

					groups.add(userSiteGroup);
				}
			}

			List<Organization> organizations =
				organizationLocalService.getOrganizations(
					user.getUserId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null);

			for (Organization organization : organizations) {
				Group userOrganizationGroup = organization.getGroup();

				if (isSyncEnabled(userOrganizationGroup)) {
					groups.add(userOrganizationGroup);
				}

				if (!GetterUtil.getBoolean(
						PropsUtil.get(
							PropsKeys.ORGANIZATIONS_MEMBERSHIP_STRICT))) {

					for (Organization ancestorOrganization :
							organization.getAncestors()) {

						Group userAncestorOrganizationGroup =
							ancestorOrganization.getGroup();

						if (isSyncEnabled(userAncestorOrganizationGroup)) {
							groups.add(userAncestorOrganizationGroup);
						}
					}
				}
			}

			groups.add(user.getGroup());

			Collections.sort(groups, new GroupNameComparator());

			return ListUtil.unique(groups);
		}

		catch (PortalException pe) {
			throw new PortalException(pe.getClass().getName(), pe);
		}
	}

	@Override
	public SyncDLObject moveFileEntry(
			long fileEntryId, long newFolderId, ServiceContext serviceContext)
		throws PortalException {

		try {
			FileEntry fileEntry = dlAppService.moveFileEntry(
				fileEntryId, newFolderId, serviceContext);

			return toSyncDLObject(fileEntry, SyncConstants.EVENT_MOVE);
		}
		catch (PortalException pe) {
			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
	}

	@Override
	public SyncDLObject moveFileEntryToTrash(long fileEntryId)
		throws PortalException {

		try {
			if (TrashUtil.isInTrash(
					DLFileEntryConstants.getClassName(), fileEntryId)) {

				return null;
			}

			FileEntry fileEntry = dlAppService.moveFileEntryToTrash(
				fileEntryId);

			return toSyncDLObject(fileEntry, SyncConstants.EVENT_TRASH);
		}
		catch (NoSuchFileEntryException nsfee) {
			return null;
		}
		catch (PortalException pe) {
			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
	}

	@Override
	public SyncDLObject moveFolder(
			long folderId, long parentFolderId, ServiceContext serviceContext)
		throws PortalException {

		try {
			Folder folder = dlAppService.moveFolder(
				folderId, parentFolderId, serviceContext);

			return toSyncDLObject(folder, SyncConstants.EVENT_MOVE);
		}
		catch (PortalException pe) {
			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
	}

	@Override
	public SyncDLObject moveFolderToTrash(long folderId)
		throws PortalException {

		try {
			if (TrashUtil.isInTrash(
					DLFolderConstants.getClassName(), folderId)) {

				return null;
			}

			Folder folder = dlAppService.moveFolderToTrash(folderId);

			return toSyncDLObject(folder, SyncConstants.EVENT_TRASH);
		}
		catch (NoSuchFolderException nsfe) {
			return null;
		}
		catch (PortalException pe) {
			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
	}

	@Override
	public SyncDLObject patchFileEntry(
			long fileEntryId, String sourceVersion, String sourceFileName,
			String mimeType, String title, String description, String changeLog,
			boolean majorVersion, File deltaFile, String checksum,
			ServiceContext serviceContext)
		throws PortalException {

		File patchedFile = null;

		try {
			File sourceFile = dlFileEntryLocalService.getFile(
				getUserId(), fileEntryId, sourceVersion, false);

			patchedFile = FileUtil.createTempFile();

			SyncUtil.patchFile(sourceFile, deltaFile, patchedFile);

			SyncDLObject syncDLObject = updateFileEntry(
				fileEntryId, sourceFileName, mimeType, title, description,
				changeLog, majorVersion, patchedFile, checksum, serviceContext);

			if (PortletPropsValues.SYNC_FILE_DIFF_CACHE_ENABLED) {
				DLFileVersion sourceDLFileVersion =
					dlFileVersionLocalService.getFileVersion(
						fileEntryId, sourceVersion);
				DLFileVersion targetDLFileVersion =
					dlFileVersionLocalService.getFileVersion(
						fileEntryId, syncDLObject.getVersion());

				syncDLFileVersionDiffLocalService.addSyncDLFileVersionDiff(
					fileEntryId, sourceDLFileVersion.getFileVersionId(),
					targetDLFileVersion.getFileVersionId(), deltaFile);
			}

			return syncDLObject;
		}
		catch (PortalException pe) {
			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
		finally {
			FileUtil.delete(patchedFile);
		}
	}

	@Override
	public SyncDLObject restoreFileEntryFromTrash(long fileEntryId)
		throws PortalException {

		try {
			dlAppService.restoreFileEntryFromTrash(fileEntryId);

			FileEntry fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

			return toSyncDLObject(fileEntry, SyncConstants.EVENT_RESTORE);
		}
		catch (PortalException pe) {
			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
	}

	@Override
	public SyncDLObject restoreFolderFromTrash(long folderId)
		throws PortalException {

		try {
			dlAppService.restoreFolderFromTrash(folderId);

			Folder folder = dlAppLocalService.getFolder(folderId);

			return toSyncDLObject(folder, SyncConstants.EVENT_RESTORE);
		}
		catch (PortalException pe) {
			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
	}

	@Override
	public SyncDLObject updateFileEntry(
			long fileEntryId, String sourceFileName, String mimeType,
			String title, String description, String changeLog,
			boolean majorVersion, File file, String checksum,
			ServiceContext serviceContext)
		throws PortalException {

		try {
			if (file != null) {
				validateChecksum(file, checksum);
			}

			FileEntry fileEntry = dlAppService.updateFileEntry(
				fileEntryId, sourceFileName, mimeType, title, description,
				changeLog, majorVersion, file, serviceContext);

			return toSyncDLObject(fileEntry, SyncConstants.EVENT_UPDATE);
		}
		catch (PortalException pe) {
			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
	}

	@Override
	public SyncDLObject updateFolder(
			long folderId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException {

		try {
			Folder folder = dlAppService.updateFolder(
				folderId, name, description, serviceContext);

			return toSyncDLObject(folder, SyncConstants.EVENT_UPDATE);
		}
		catch (PortalException pe) {
			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
	}

	protected SyncDLObject checkModifiedTime(
			SyncDLObject syncDLObject, long typePk)
		throws PortalException {

		DynamicQuery dynamicQuery = DLSyncEventLocalServiceUtil.dynamicQuery();

		dynamicQuery.add(RestrictionsFactoryUtil.eq("typePK", typePk));

		List<DLSyncEvent> dlSyncEvents =
			DLSyncEventLocalServiceUtil.dynamicQuery(dynamicQuery);

		if (dlSyncEvents.isEmpty()) {
			return syncDLObject;
		}

		DLSyncEvent dlSyncEvent = dlSyncEvents.get(0);

		syncDLObject.setModifiedTime(dlSyncEvent.getModifiedTime());

		return syncDLObject;
	}

	protected Map<String, String> getPortletPreferencesMap()
		throws PortalException {

		Map<String, String> portletPreferencesMap =
			new HashMap<String, String>();

		User user = getUser();

		int maxConnections = PrefsPropsUtil.getInteger(
			user.getCompanyId(), PortletPropsKeys.SYNC_CLIENT_MAX_CONNECTIONS,
			PortletPropsValues.SYNC_CLIENT_MAX_CONNECTIONS);

		portletPreferencesMap.put(
			PortletPropsKeys.SYNC_CLIENT_MAX_CONNECTIONS,
			String.valueOf(maxConnections));

		int pollInterval = PrefsPropsUtil.getInteger(
			user.getCompanyId(), PortletPropsKeys.SYNC_CLIENT_POLL_INTERVAL,
			PortletPropsValues.SYNC_CLIENT_POLL_INTERVAL);

		portletPreferencesMap.put(
			PortletPropsKeys.SYNC_CLIENT_POLL_INTERVAL,
			String.valueOf(pollInterval));

		return portletPreferencesMap;
	}

	protected List<SyncDLObject> getSyncDLObjects(
			List<SyncDLObject> syncDLObjects, long companyId, long repositoryId,
			long parentFolderId, long lastAccessTime)
		throws PortalException {

		List<SyncDLObject> curSyncDLObjects =
			syncDLObjectFinder.filterFindByC_M_R_P(
				companyId, lastAccessTime, repositoryId, parentFolderId);

		syncDLObjects.addAll(curSyncDLObjects);

		for (SyncDLObject curSyncDLObject : curSyncDLObjects) {
			String type = curSyncDLObject.getType();

			if (type.equals("file")) {
				continue;
			}

			getSyncDLObjects(
				syncDLObjects, companyId, repositoryId,
				curSyncDLObject.getTypePK(), lastAccessTime);
		}

		return syncDLObjects;
	}

	protected boolean isSyncEnabled(Group group) {
		return GetterUtil.getBoolean(
			group.getTypeSettingsProperty("syncEnabled"), true);
	}

	protected SyncDLObject toSyncDLObject(FileEntry fileEntry, String event)
		throws PortalException {

		SyncDLObject syncDLObject = SyncUtil.toSyncDLObject(fileEntry, event);

		return checkModifiedTime(syncDLObject, fileEntry.getFileEntryId());
	}

	protected SyncDLObject toSyncDLObject(Folder folder, String event)
		throws PortalException {

		SyncDLObject syncDLObject = SyncUtil.toSyncDLObject(folder, event);

		return checkModifiedTime(syncDLObject, folder.getFolderId());
	}

	protected void validateChecksum(File file, String checksum)
		throws PortalException {

		if (Validator.isNull(checksum)) {
			return;
		}

		String fileChecksum = SyncUtil.getChecksum(file);

		if (Validator.isNull(fileChecksum) || fileChecksum.equals(checksum)) {
			return;
		}

		StringBundler sb = new StringBundler(4);

		sb.append("Expected checksum ");
		sb.append(checksum);
		sb.append(" does not match actual checksum ");
		sb.append(fileChecksum);

		throw new SyncDLObjectChecksumException(sb.toString());
	}

	private static Log _log = LogFactoryUtil.getLog(
		SyncDLObjectServiceImpl.class);

}