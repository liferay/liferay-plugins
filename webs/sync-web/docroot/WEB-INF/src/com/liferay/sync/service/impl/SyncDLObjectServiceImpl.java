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
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.zip.ZipReader;
import com.liferay.portal.kernel.zip.ZipReaderFactoryUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Repository;
import com.liferay.portal.model.User;
import com.liferay.portal.security.ac.AccessControlled;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
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
import com.liferay.sync.model.SyncConstants;
import com.liferay.sync.model.SyncContext;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.model.SyncDLObjectUpdate;
import com.liferay.sync.service.base.SyncDLObjectServiceBaseImpl;
import com.liferay.sync.util.JSONWebServiceActionParametersMap;
import com.liferay.sync.util.PortletPropsKeys;
import com.liferay.sync.util.PortletPropsValues;
import com.liferay.sync.util.SyncUtil;

import java.io.File;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jodd.bean.BeanUtil;

import jodd.util.NameValue;

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
			Group group = groupLocalService.getGroup(repositoryId);

			SyncUtil.checkSyncEnabled(group.getGroupId());

			if (serviceContext.getGroupPermissions() == null) {
				SyncUtil.setFilePermissions(group, false, serviceContext);
			}

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
			Group group = groupLocalService.getGroup(repositoryId);

			SyncUtil.checkSyncEnabled(group.getGroupId());

			if (serviceContext.getGroupPermissions() == null) {
				SyncUtil.setFilePermissions(group, true, serviceContext);
			}

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
			FileEntry fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

			SyncUtil.checkSyncEnabled(fileEntry.getGroupId());

			dlAppService.cancelCheckOut(fileEntryId);

			fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

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
			FileEntry fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

			SyncUtil.checkSyncEnabled(fileEntry.getGroupId());

			dlAppService.checkInFileEntry(
				fileEntryId, majorVersion, changeLog, serviceContext);

			fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

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
			FileEntry fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

			SyncUtil.checkSyncEnabled(fileEntry.getGroupId());

			dlAppService.checkOutFileEntry(fileEntryId, serviceContext);

			fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

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
			FileEntry fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

			SyncUtil.checkSyncEnabled(fileEntry.getGroupId());

			fileEntry = dlAppService.checkOutFileEntry(
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
			SyncUtil.checkSyncEnabled(repositoryId);

			repositoryService.checkRepository(repositoryId);

			List<SyncDLObject> syncDLObjects =
				syncDLObjectFinder.filterFindByC_R(companyId, repositoryId);

			if (!InlineSQLHelperUtil.isEnabled(repositoryId)) {
				return checkSyncDLObjects(syncDLObjects);
			}

			return syncDLObjects;
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
			SyncUtil.checkSyncEnabled(repositoryId);

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
			SyncUtil.checkSyncEnabled(groupId);

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
			SyncUtil.checkSyncEnabled(repositoryId);

			List<FileEntry> fileEntries = dlAppService.getFileEntries(
				repositoryId, folderId);

			List<SyncDLObject> syncDLObjects = new ArrayList<>(
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
			Folder folder = dlAppLocalService.getFolder(folderId);

			SyncUtil.checkSyncEnabled(folder.getGroupId());

			folder = dlAppService.getFolder(folderId);

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
			SyncUtil.checkSyncEnabled(repositoryId);

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
			SyncUtil.checkSyncEnabled(repositoryId);

			List<Folder> folders = dlAppService.getFolders(
				repositoryId, parentFolderId);

			List<SyncDLObject> syncDLObjects = new ArrayList<>(folders.size());

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
			SyncUtil.checkSyncEnabled(groupId);

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

	@AccessControlled(guestAccessEnabled = true)
	@Override
	public SyncContext getSyncContext() throws PortalException {
		try {
			User user = getGuestOrUser();

			SyncContext syncContext = new SyncContext();

			String authType = PrefsPropsUtil.getString(
				CompanyThreadLocal.getCompanyId(),
				PropsKeys.COMPANY_SECURITY_AUTH_TYPE,
				PropsUtil.get(PropsKeys.COMPANY_SECURITY_AUTH_TYPE));

			syncContext.setAuthType(authType);

			boolean oAuthEnabled = PrefsPropsUtil.getBoolean(
				user.getCompanyId(), PortletPropsKeys.SYNC_OAUTH_ENABLED,
				PortletPropsValues.SYNC_OAUTH_ENABLED);

			if (oAuthEnabled) {
				String oAuthConsumerKey = PrefsPropsUtil.getString(
					user.getCompanyId(),
					PortletPropsKeys.SYNC_OAUTH_CONSUMER_KEY);

				syncContext.setOAuthConsumerKey(oAuthConsumerKey);

				String oAuthConsumerSecret = PrefsPropsUtil.getString(
					user.getCompanyId(),
					PortletPropsKeys.SYNC_OAUTH_CONSUMER_SECRET);

				syncContext.setOAuthConsumerSecret(oAuthConsumerSecret);
			}

			syncContext.setOAuthEnabled(oAuthEnabled);

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

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #getSyncContext()}
	 */
	@Deprecated
	@Override
	public SyncContext getSyncContext(String uuid) throws PortalException {
		return getSyncContext();
	}

	@Override
	public SyncDLObjectUpdate getSyncDLObjectUpdate(
			long companyId, long repositoryId, long lastAccessTime)
		throws PortalException {

		try {
			SyncUtil.checkSyncEnabled(repositoryId);

			repositoryService.checkRepository(repositoryId);

			List<SyncDLObject> syncDLObjects =
				syncDLObjectFinder.filterFindByC_M_R_P(
					companyId, lastAccessTime, repositoryId, -1);

			if (!InlineSQLHelperUtil.isEnabled(repositoryId)) {
				syncDLObjects = checkSyncDLObjects(syncDLObjects);
			}

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
			SyncUtil.checkSyncEnabled(repositoryId);

			repositoryService.checkRepository(repositoryId);

			List<SyncDLObject> syncDLObjects = new ArrayList<>();

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

			List<Group> groups = new ArrayList<>();

			LinkedHashMap<String, Object> groupParams = new LinkedHashMap<>();

			groupParams.put("active", true);
			groupParams.put("usersGroups", user.getUserId());

			List<Group> userSiteGroups = groupLocalService.search(
				user.getCompanyId(), null, groupParams, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

			for (Group userSiteGroup : userSiteGroups) {
				if (SyncUtil.isSyncEnabled(userSiteGroup)) {
					userSiteGroup.setName(userSiteGroup.getDescriptiveName());

					groups.add(userSiteGroup);
				}
			}

			List<Organization> organizations =
				organizationLocalService.getOrganizations(
					user.getUserId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null);

			for (Organization organization : organizations) {
				Group userOrganizationGroup = organization.getGroup();

				if (SyncUtil.isSyncEnabled(userOrganizationGroup)) {
					groups.add(userOrganizationGroup);
				}

				if (!GetterUtil.getBoolean(
						PropsUtil.get(
							PropsKeys.ORGANIZATIONS_MEMBERSHIP_STRICT))) {

					for (Organization ancestorOrganization :
							organization.getAncestors()) {

						Group userAncestorOrganizationGroup =
							ancestorOrganization.getGroup();

						if (SyncUtil.isSyncEnabled(
								userAncestorOrganizationGroup)) {

							groups.add(userAncestorOrganizationGroup);
						}
					}
				}
			}

			if (PrefsPropsUtil.getBoolean(
					user.getCompanyId(),
					PortletPropsKeys.SYNC_ALLOW_USER_PERSONAL_SITES,
					PortletPropsValues.SYNC_ALLOW_USER_PERSONAL_SITES)) {

				groups.add(user.getGroup());
			}

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
			FileEntry fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

			SyncUtil.checkSyncEnabled(fileEntry.getGroupId());

			fileEntry = dlAppService.moveFileEntry(
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
			FileEntry fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

			SyncUtil.checkSyncEnabled(fileEntry.getGroupId());

			if (TrashUtil.isInTrash(
					DLFileEntryConstants.getClassName(), fileEntryId)) {

				return null;
			}

			fileEntry = dlAppService.moveFileEntryToTrash(fileEntryId);

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
			Folder folder = dlAppLocalService.getFolder(folderId);

			SyncUtil.checkSyncEnabled(folder.getGroupId());

			folder = dlAppService.moveFolder(
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
			Folder folder = dlAppLocalService.getFolder(folderId);

			SyncUtil.checkSyncEnabled(folder.getGroupId());

			if (TrashUtil.isInTrash(
					DLFolderConstants.getClassName(), folderId)) {

				return null;
			}

			folder = dlAppService.moveFolderToTrash(folderId);

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
			long fileEntryId, long sourceVersionId, String sourceFileName,
			String mimeType, String title, String description, String changeLog,
			boolean majorVersion, File deltaFile, String checksum,
			ServiceContext serviceContext)
		throws PortalException {

		File patchedFile = null;

		try {
			FileEntry fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

			SyncUtil.checkSyncEnabled(fileEntry.getGroupId());

			DLFileVersion dlFileVersion =
				dlFileVersionLocalService.getDLFileVersion(sourceVersionId);

			File sourceFile = dlFileEntryLocalService.getFile(
				fileEntryId, dlFileVersion.getVersion(), false);

			patchedFile = FileUtil.createTempFile();

			SyncUtil.patchFile(sourceFile, deltaFile, patchedFile);

			SyncDLObject syncDLObject = updateFileEntry(
				fileEntryId, sourceFileName, mimeType, title, description,
				changeLog, majorVersion, patchedFile, checksum, serviceContext);

			if (PortletPropsValues.SYNC_FILE_DIFF_CACHE_ENABLED &&
				(sourceVersionId != syncDLObject.getVersionId())) {

				DLFileVersion targetDLFileVersion =
					dlFileVersionLocalService.getFileVersion(
						syncDLObject.getVersionId());

				syncDLFileVersionDiffLocalService.addSyncDLFileVersionDiff(
					fileEntryId, sourceVersionId,
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
			FileEntry fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

			SyncUtil.checkSyncEnabled(fileEntry.getGroupId());

			dlAppService.restoreFileEntryFromTrash(fileEntryId);

			fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

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
			Folder folder = dlAppLocalService.getFolder(folderId);

			SyncUtil.checkSyncEnabled(folder.getGroupId());

			dlAppService.restoreFolderFromTrash(folderId);

			folder = dlAppLocalService.getFolder(folderId);

			return toSyncDLObject(folder, SyncConstants.EVENT_RESTORE);
		}
		catch (PortalException pe) {
			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
	}

	@Override
	@Transactional(enabled = false)
	public Map<String, Object> updateFileEntries(File zipFile)
		throws PortalException {

		Map<String, Object> responseMap = new HashMap<>();

		ZipReader zipReader = null;

		try {
			zipReader = ZipReaderFactoryUtil.getZipReader(zipFile);

			String manifest = zipReader.getEntryAsString("/manifest.json");

			JSONArray jsonArray = JSONFactoryUtil.createJSONArray(manifest);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				JSONWebServiceActionParametersMap
					jsonWebServiceActionParametersMap =
						JSONFactoryUtil.looseDeserialize(
							jsonObject.toString(),
							JSONWebServiceActionParametersMap.class);

				String zipFileId = MapUtil.getString(
					jsonWebServiceActionParametersMap, "zipFileId");

				try {
					responseMap.put(
						zipFileId,
						updateFileEntries(
							zipReader, zipFileId,
							jsonWebServiceActionParametersMap));
				}
				catch (Exception e) {
					String message = e.getMessage();

					if (!message.startsWith(StringPool.QUOTE) &&
						!message.endsWith(StringPool.QUOTE)) {

						message = StringUtil.quote(message, StringPool.QUOTE);
					}

					String json = "{\"exception\": " + message + "}";

					responseMap.put(zipFileId, json);
				}
			}
		}
		finally {
			if (zipReader != null) {
				zipReader.close();
			}
		}

		return responseMap;
	}

	@Override
	public SyncDLObject updateFileEntry(
			long fileEntryId, String sourceFileName, String mimeType,
			String title, String description, String changeLog,
			boolean majorVersion, File file, String checksum,
			ServiceContext serviceContext)
		throws PortalException {

		try {
			FileEntry fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

			SyncUtil.checkSyncEnabled(fileEntry.getGroupId());

			fileEntry = dlAppService.updateFileEntry(
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
			Folder folder = dlAppLocalService.getFolder(folderId);

			SyncUtil.checkSyncEnabled(folder.getGroupId());

			folder = dlAppService.updateFolder(
				folderId, name, description, serviceContext);

			return toSyncDLObject(folder, SyncConstants.EVENT_UPDATE);
		}
		catch (PortalException pe) {
			throw new PortalException(SyncUtil.buildExceptionMessage(pe), pe);
		}
	}

	protected SyncDLObject checkModifiedTime(
		SyncDLObject syncDLObject, long typePk) {

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

	protected List<SyncDLObject> checkSyncDLObjects(
		List<SyncDLObject> syncDLObjects) {

		Iterator<SyncDLObject> iterator = syncDLObjects.iterator();

		while (iterator.hasNext()) {
			SyncDLObject syncDLObject = iterator.next();

			String type = syncDLObject.getType();

			try {
				if (type.equals(SyncConstants.TYPE_FILE) ||
					type.equals(SyncConstants.TYPE_PRIVATE_WORKING_COPY)) {

					dlAppService.getFileEntry(syncDLObject.getTypePK());
				}
				else {
					dlAppService.getFolder(syncDLObject.getTypePK());
				}
			}
			catch (Exception e) {
				iterator.remove();
			}
		}

		return syncDLObjects;
	}

	protected Map<String, String> getPortletPreferencesMap()
		throws PortalException {

		Map<String, String> portletPreferencesMap = new HashMap<>();

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

		if (!InlineSQLHelperUtil.isEnabled(repositoryId)) {
			curSyncDLObjects = checkSyncDLObjects(curSyncDLObjects);
		}

		syncDLObjects.addAll(curSyncDLObjects);

		for (SyncDLObject curSyncDLObject : curSyncDLObjects) {
			String type = curSyncDLObject.getType();

			if (!type.equals(SyncConstants.TYPE_FOLDER)) {
				continue;
			}

			getSyncDLObjects(
				syncDLObjects, companyId, repositoryId,
				curSyncDLObject.getTypePK(), lastAccessTime);
		}

		return syncDLObjects;
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

	protected SyncDLObject updateFileEntries(
			ZipReader zipReader, String zipFileId,
			JSONWebServiceActionParametersMap jsonWebServiceActionParametersMap)
		throws Exception {

		ServiceContext serviceContext = new ServiceContext();

		List<NameValue<String, Object>> innerParameters =
			jsonWebServiceActionParametersMap.getInnerParameters(
				"serviceContext");

		if (innerParameters != null) {
			for (NameValue<String, Object> innerParameter : innerParameters) {
				try {
					BeanUtil.setProperty(
						serviceContext, innerParameter.getName(),
						innerParameter.getValue());
				}
				catch (Exception e) {
					if (_log.isDebugEnabled()) {
						_log.debug(e.getMessage(), e);
					}
				}
			}
		}

		String urlPath = MapUtil.getString(
			jsonWebServiceActionParametersMap, "urlPath");

		if (urlPath.endsWith("/add-file-entry")) {
			long repositoryId = MapUtil.getLong(
				jsonWebServiceActionParametersMap, "repositoryId");
			long folderId = MapUtil.getLong(
				jsonWebServiceActionParametersMap, "folderId");
			String sourceFileName = MapUtil.getString(
				jsonWebServiceActionParametersMap, "sourceFileName");
			String mimeType = MapUtil.getString(
				jsonWebServiceActionParametersMap, "mimeType");
			String title = MapUtil.getString(
				jsonWebServiceActionParametersMap, "title");
			String description = MapUtil.getString(
				jsonWebServiceActionParametersMap, "description");
			String changeLog = MapUtil.getString(
				jsonWebServiceActionParametersMap, "changeLog");

			InputStream inputStream = zipReader.getEntryAsInputStream(
				zipFileId);

			File tempFile = null;

			try {
				tempFile = FileUtil.createTempFile(inputStream);

				String checksum = MapUtil.getString(
					jsonWebServiceActionParametersMap, "checksum");

				return addFileEntry(
					repositoryId, folderId, sourceFileName, mimeType, title,
					description, changeLog, tempFile, checksum, serviceContext);
			}
			finally {
				FileUtil.delete(tempFile);
			}
		}
		else if (urlPath.endsWith("/add-folder")) {
			long repositoryId = MapUtil.getLong(
				jsonWebServiceActionParametersMap, "repositoryId");
			long parentFolderId = MapUtil.getLong(
				jsonWebServiceActionParametersMap, "parentFolderId");
			String name = MapUtil.getString(
				jsonWebServiceActionParametersMap, "name");
			String description = MapUtil.getString(
				jsonWebServiceActionParametersMap, "description");

			return addFolder(
				repositoryId, parentFolderId, name, description,
				serviceContext);
		}
		else if (urlPath.endsWith("/move-file-entry")) {
			long fileEntryId = MapUtil.getLong(
				jsonWebServiceActionParametersMap, "fileEntryId");
			long newFolderId = MapUtil.getLong(
				jsonWebServiceActionParametersMap, "newFolderId");

			return moveFileEntry(fileEntryId, newFolderId, serviceContext);
		}
		else if (urlPath.endsWith("/move-file-entry-to-trash")) {
			long fileEntryId = MapUtil.getLong(
				jsonWebServiceActionParametersMap, "fileEntryId");

			return moveFileEntryToTrash(fileEntryId);
		}
		else if (urlPath.endsWith("/move-folder")) {
			long folderId = MapUtil.getLong(
				jsonWebServiceActionParametersMap, "folderId");
			long parentFolderId = MapUtil.getLong(
				jsonWebServiceActionParametersMap, "parentFolderId");

			return moveFolder(folderId, parentFolderId, serviceContext);
		}
		else if (urlPath.endsWith("/move-folder-to-trash")) {
			long folderId = MapUtil.getLong(
				jsonWebServiceActionParametersMap, "folderId");

			return moveFolderToTrash(folderId);
		}
		else if (urlPath.endsWith("/patch-file-entry")) {
			long fileEntryId = MapUtil.getLong(
				jsonWebServiceActionParametersMap, "fileEntryId");
			long sourceVersionId = MapUtil.getLong(
				jsonWebServiceActionParametersMap, "sourceVersionId");
			String sourceFileName = MapUtil.getString(
				jsonWebServiceActionParametersMap, "sourceFileName");
			String mimeType = MapUtil.getString(
				jsonWebServiceActionParametersMap, "mimeType");
			String title = MapUtil.getString(
				jsonWebServiceActionParametersMap, "title");
			String description = MapUtil.getString(
				jsonWebServiceActionParametersMap, "description");
			String changeLog = MapUtil.getString(
				jsonWebServiceActionParametersMap, "changeLog");
			boolean majorVersion = MapUtil.getBoolean(
				jsonWebServiceActionParametersMap, "majorVersion");

			InputStream inputStream = zipReader.getEntryAsInputStream(
				zipFileId);

			File tempFile = null;

			try {
				tempFile = FileUtil.createTempFile(inputStream);

				String checksum = MapUtil.getString(
					jsonWebServiceActionParametersMap, "checksum");

				return patchFileEntry(
					fileEntryId, sourceVersionId, sourceFileName, mimeType,
					title, description, changeLog, majorVersion, tempFile,
					checksum, serviceContext);
			}
			finally {
				FileUtil.delete(tempFile);
			}
		}
		else if (urlPath.endsWith("/update-file-entry")) {
			long fileEntryId = MapUtil.getLong(
				jsonWebServiceActionParametersMap, "fileEntryId");
			String sourceFileName = MapUtil.getString(
				jsonWebServiceActionParametersMap, "sourceFileName");
			String mimeType = MapUtil.getString(
				jsonWebServiceActionParametersMap, "mimeType");
			String title = MapUtil.getString(
				jsonWebServiceActionParametersMap, "title");
			String description = MapUtil.getString(
				jsonWebServiceActionParametersMap, "description");
			String changeLog = MapUtil.getString(
				jsonWebServiceActionParametersMap, "changeLog");
			boolean majorVersion = MapUtil.getBoolean(
				jsonWebServiceActionParametersMap, "majorVersion");

			File tempFile = null;

			try {
				InputStream inputStream = zipReader.getEntryAsInputStream(
					zipFileId);

				if (inputStream != null) {
					tempFile = FileUtil.createTempFile(inputStream);
				}

				String checksum = MapUtil.getString(
					jsonWebServiceActionParametersMap, "checksum");

				return updateFileEntry(
					fileEntryId, sourceFileName, mimeType, title, description,
					changeLog, majorVersion, tempFile, checksum,
					serviceContext);
			}
			finally {
				FileUtil.delete(tempFile);
			}
		}
		else if (urlPath.endsWith("/update-folder")) {
			long folderId = MapUtil.getLong(
				jsonWebServiceActionParametersMap, "folderId");
			String name = MapUtil.getString(
				jsonWebServiceActionParametersMap, "name");
			String description = MapUtil.getString(
				jsonWebServiceActionParametersMap, "description");

			return updateFolder(folderId, name, description, serviceContext);
		}

		return null;
	}

	private static Log _log = LogFactoryUtil.getLog(
		SyncDLObjectServiceImpl.class);

}