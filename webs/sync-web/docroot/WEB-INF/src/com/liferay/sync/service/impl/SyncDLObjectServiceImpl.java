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

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.deploy.DeployManagerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.DuplicateFileException;
import com.liferay.portlet.documentlibrary.DuplicateFolderNameException;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.sync.SyncDLObjectChecksumException;
import com.liferay.sync.model.SyncConstants;
import com.liferay.sync.model.SyncContext;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.model.SyncDLObjectUpdate;
import com.liferay.sync.service.base.SyncDLObjectServiceBaseImpl;
import com.liferay.sync.util.PortletPropsKeys;
import com.liferay.sync.util.PortletPropsValues;
import com.liferay.sync.util.SyncUtil;

import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		throws PortalException, SystemException {

		try {
			validateChecksum(file, checksum);

			FileEntry fileEntry = dlAppService.addFileEntry(
				repositoryId, folderId, sourceFileName, mimeType, title,
				description, changeLog, file, serviceContext);

			return SyncUtil.toSyncDLObject(fileEntry, SyncConstants.EVENT_ADD);
		}
		catch (PortalException pe) {
			if (pe instanceof DuplicateFileException) {
				if (GetterUtil.getBoolean(
						serviceContext.getAttribute("overwrite"))) {

					FileEntry fileEntry = dlAppService.getFileEntry(
						repositoryId, folderId, title);

					fileEntry = dlAppService.updateFileEntry(
						fileEntry.getFileEntryId(), sourceFileName, mimeType,
						title, description, changeLog, true, file,
						serviceContext);

					return SyncUtil.toSyncDLObject(
						fileEntry, SyncConstants.EVENT_UPDATE);
				}
			}

			throw new PortalException(pe.getClass().getName(), pe);
		}
	}

	@Override
	public SyncDLObject addFolder(
			long repositoryId, long parentFolderId, String name,
			String description, ServiceContext serviceContext)
		throws PortalException, SystemException {

		try {
			Folder folder = dlAppService.addFolder(
				repositoryId, parentFolderId, name, description,
				serviceContext);

			return SyncUtil.toSyncDLObject(folder, SyncConstants.EVENT_ADD);
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

					return SyncUtil.toSyncDLObject(
						folder, SyncConstants.EVENT_UPDATE);
				}
			}

			throw new PortalException(pe.getClass().getName(), pe);
		}
	}

	@Override
	public SyncDLObject cancelCheckOut(long fileEntryId)
		throws PortalException, SystemException {

		try {
			dlAppService.cancelCheckOut(fileEntryId);

			FileEntry fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

			return SyncUtil.toSyncDLObject(
				fileEntry, SyncConstants.EVENT_CANCEL_CHECK_OUT);
		}
		catch (PortalException pe) {
			throw new PortalException(pe.getClass().getName(), pe);
		}
	}

	@Override
	public SyncDLObject checkInFileEntry(
			long fileEntryId, boolean majorVersion, String changeLog,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		try {
			dlAppService.checkInFileEntry(
				fileEntryId, majorVersion, changeLog, serviceContext);

			FileEntry fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

			return SyncUtil.toSyncDLObject(
				fileEntry, SyncConstants.EVENT_CHECK_IN);
		}
		catch (PortalException pe) {
			throw new PortalException(pe.getClass().getName(), pe);
		}
	}

	@Override
	public SyncDLObject checkOutFileEntry(
			long fileEntryId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		try {
			dlAppService.checkOutFileEntry(fileEntryId, serviceContext);

			FileEntry fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

			return SyncUtil.toSyncDLObject(
				fileEntry, SyncConstants.EVENT_CHECK_OUT);
		}
		catch (PortalException pe) {
			throw new PortalException(pe.getClass().getName(), pe);
		}
	}

	@Override
	public SyncDLObject checkOutFileEntry(
			long fileEntryId, String owner, long expirationTime,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		try {
			FileEntry fileEntry = dlAppService.checkOutFileEntry(
				fileEntryId, owner, expirationTime, serviceContext);

			return SyncUtil.toSyncDLObject(
				fileEntry, SyncConstants.EVENT_CHECK_OUT);
		}
		catch (PortalException pe) {
			throw new PortalException(pe.getClass().getName(), pe);
		}
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public SyncDLObjectUpdate getAllSyncDLObjects(
			long repositoryId, long folderId)
		throws PortalException, SystemException {

		try {
			long lastAccessTime = System.currentTimeMillis();

			List<SyncDLObject> syncDLObjects = new ArrayList<SyncDLObject>();

			getAllSyncDLObjects(repositoryId, folderId, syncDLObjects);

			return new SyncDLObjectUpdate(syncDLObjects, lastAccessTime);
		}
		catch (PortalException pe) {
			throw new PortalException(pe.getClass().getName(), pe);
		}
	}

	@Override
	public SyncDLObject getFileEntrySyncDLObject(
			long groupId, long folderId, String title)
		throws PortalException, SystemException {

		try {
			FileEntry fileEntry = dlAppService.getFileEntry(
				groupId, folderId, title);

			return SyncUtil.toSyncDLObject(fileEntry, SyncConstants.EVENT_GET);
		}
		catch (PortalException pe) {
			throw new PortalException(pe.getClass().getName(), pe);
		}
	}

	@Override
	public List<SyncDLObject> getFileEntrySyncDLObjects(
			long repositoryId, long folderId)
		throws PortalException, SystemException {

		try {
			List<FileEntry> fileEntries = dlAppService.getFileEntries(
				repositoryId, folderId);

			List<SyncDLObject> syncDLObjects = new ArrayList<SyncDLObject>(
				fileEntries.size());

			for (FileEntry fileEntry : fileEntries) {
				SyncDLObject syncDLObject = SyncUtil.toSyncDLObject(
					fileEntry, SyncConstants.EVENT_GET);

				syncDLObjects.add(syncDLObject);
			}

			return syncDLObjects;
		}
		catch (PortalException pe) {
			throw new PortalException(pe.getClass().getName(), pe);
		}
	}

	@Override
	public SyncDLObject getFolderSyncDLObject(long folderId)
		throws PortalException, SystemException {

		try {
			Folder folder = dlAppService.getFolder(folderId);

			if (!SyncUtil.isSupportedFolder(folder)) {
				return null;
			}

			return SyncUtil.toSyncDLObject(folder, SyncConstants.EVENT_GET);
		}
		catch (PortalException pe) {
			throw new PortalException(pe.getClass().getName(), pe);
		}
	}

	@Override
	public SyncDLObject getFolderSyncDLObject(
			long repositoryId, long parentFolderId, String name)
		throws PortalException, SystemException {

		try {
			Folder folder = dlAppService.getFolder(
				repositoryId, parentFolderId, name);

			if (!SyncUtil.isSupportedFolder(folder)) {
				return null;
			}

			return SyncUtil.toSyncDLObject(folder, SyncConstants.EVENT_GET);
		}
		catch (PortalException pe) {
			throw new PortalException(pe.getClass().getName(), pe);
		}
	}

	@Override
	public List<SyncDLObject> getFolderSyncDLObjects(
			long repositoryId, long parentFolderId)
		throws PortalException, SystemException {

		try {
			List<Folder> folders = dlAppService.getFolders(
				repositoryId, parentFolderId);

			List<SyncDLObject> syncDLObjects = new ArrayList<SyncDLObject>(
				folders.size());

			for (Folder folder : folders) {
				if (!SyncUtil.isSupportedFolder(folder)) {
					continue;
				}

				SyncDLObject syncDLObject = SyncUtil.toSyncDLObject(
					folder, SyncConstants.EVENT_GET);

				syncDLObjects.add(syncDLObject);
			}

			return syncDLObjects;
		}
		catch (PortalException pe) {
			throw new PortalException(pe.getClass().getName(), pe);
		}
	}

	@Override
	public Group getGroup(long groupId)
		throws PortalException, SystemException {

		try {
			return groupService.getGroup(groupId);
		}
		catch (PortalException pe) {
			throw new PortalException(pe.getClass().getName(), pe);
		}
	}

	@Override
	public long getLatestModifiedTime() throws SystemException {
		return syncDLObjectLocalService.getLatestModifiedTime();
	}

	@Override
	public SyncContext getSyncContext(String uuid)
		throws PortalException, SystemException {

		try {
			SyncContext syncContext = new SyncContext();

			PluginPackage syncWebPluginPackage =
				DeployManagerUtil.getInstalledPluginPackage("sync-web");

			syncContext.setPluginVersion(syncWebPluginPackage.getVersion());

			syncContext.setPortalBuildNumber(ReleaseInfo.getBuildNumber());

			PluginPackage soPortletPluginPackage =
				DeployManagerUtil.getInstalledPluginPackage("so-portlet");

			syncContext.setPortletPreferencesMap(getPortletPreferencesMap());

			if (soPortletPluginPackage != null) {
				syncContext.setSocialOfficeInstalled(true);
			}
			else {
				syncContext.setSocialOfficeInstalled(false);
			}

			syncContext.setUserId(getUserId());
			syncContext.setUserSitesGroups(getUserSitesGroups());

			return syncContext;
		}
		catch (PortalException pe) {
			throw new PortalException(pe.getClass().getName(), pe);
		}
	}

	@Override
	public SyncDLObjectUpdate getSyncDLObjectUpdate(
			long companyId, long repositoryId, long lastAccessTime)
		throws PortalException, SystemException {

		try {
			repositoryService.checkRepository(repositoryId);

			List<SyncDLObject> syncDLObjects =
				syncDLObjectFinder.filterFindByC_M_R(
					companyId, lastAccessTime, repositoryId);

			for (SyncDLObject syncDLObject : syncDLObjects) {
				if (syncDLObject.getModifiedTime() > lastAccessTime) {
					lastAccessTime = syncDLObject.getModifiedTime();
				}
			}

			return new SyncDLObjectUpdate(syncDLObjects, lastAccessTime);
		}
		catch (PortalException pe) {
			throw new PortalException(pe.getClass().getName(), pe);
		}
	}

	@Override
	public List<Group> getUserSitesGroups()
		throws PortalException, SystemException {

		try {
			return groupService.getUserSitesGroups();
		}
		catch (PortalException pe) {
			throw new PortalException(pe.getClass().getName(), pe);
		}
	}

	@Override
	public SyncDLObject moveFileEntry(
			long fileEntryId, long newFolderId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		try {
			FileEntry fileEntry = dlAppService.moveFileEntry(
				fileEntryId, newFolderId, serviceContext);

			return SyncUtil.toSyncDLObject(fileEntry, SyncConstants.EVENT_MOVE);
		}
		catch (PortalException pe) {
			throw new PortalException(pe.getClass().getName(), pe);
		}
	}

	@Override
	public SyncDLObject moveFileEntryToTrash(long fileEntryId)
		throws PortalException, SystemException {

		try {
			FileEntry fileEntry = dlAppService.moveFileEntryToTrash(
				fileEntryId);

			return SyncUtil.toSyncDLObject(
				fileEntry, SyncConstants.EVENT_TRASH);
		}
		catch (PortalException pe) {
			throw new PortalException(pe.getClass().getName(), pe);
		}
	}

	@Override
	public SyncDLObject moveFolder(
			long folderId, long parentFolderId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		try {
			Folder folder = dlAppService.moveFolder(
				folderId, parentFolderId, serviceContext);

			return SyncUtil.toSyncDLObject(folder, SyncConstants.EVENT_MOVE);
		}
		catch (PortalException pe) {
			throw new PortalException(pe.getClass().getName(), pe);
		}
	}

	@Override
	public SyncDLObject moveFolderToTrash(long folderId)
		throws PortalException, SystemException {

		try {
			Folder folder = dlAppService.moveFolderToTrash(folderId);

			return SyncUtil.toSyncDLObject(folder, SyncConstants.EVENT_TRASH);
		}
		catch (PortalException pe) {
			throw new PortalException(pe.getClass().getName(), pe);
		}
	}

	@Override
	public SyncDLObject patchFileEntry(
			long fileEntryId, String sourceVersion, String sourceFileName,
			String mimeType, String title, String description, String changeLog,
			boolean majorVersion, File deltaFile, String checksum,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

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
			throw new PortalException(pe.getClass().getName(), pe);
		}
		finally {
			FileUtil.delete(patchedFile);
		}
	}

	@Override
	public SyncDLObject restoreFileEntryFromTrash(long fileEntryId)
		throws PortalException, SystemException {

		try {
			dlAppService.restoreFileEntryFromTrash(fileEntryId);

			FileEntry fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

			return SyncUtil.toSyncDLObject(
				fileEntry, SyncConstants.EVENT_RESTORE);
		}
		catch (PortalException pe) {
			throw new PortalException(pe.getClass().getName(), pe);
		}
	}

	@Override
	public SyncDLObject restoreFolderFromTrash(long folderId)
		throws PortalException, SystemException {

		try {
			dlAppService.restoreFolderFromTrash(folderId);

			Folder folder = dlAppLocalService.getFolder(folderId);

			return SyncUtil.toSyncDLObject(folder, SyncConstants.EVENT_RESTORE);
		}
		catch (PortalException pe) {
			throw new PortalException(pe.getClass().getName(), pe);
		}
	}

	@Override
	public SyncDLObject updateFileEntry(
			long fileEntryId, String sourceFileName, String mimeType,
			String title, String description, String changeLog,
			boolean majorVersion, File file, String checksum,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		try {
			if (file != null) {
				validateChecksum(file, checksum);
			}

			FileEntry fileEntry = dlAppService.updateFileEntry(
				fileEntryId, sourceFileName, mimeType, title, description,
				changeLog, majorVersion, file, serviceContext);

			return SyncUtil.toSyncDLObject(
				fileEntry, SyncConstants.EVENT_UPDATE);
		}
		catch (PortalException pe) {
			throw new PortalException(pe.getClass().getName(), pe);
		}
	}

	@Override
	public SyncDLObject updateFolder(
			long folderId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		try {
			Folder folder = dlAppService.updateFolder(
				folderId, name, description, serviceContext);

			return SyncUtil.toSyncDLObject(folder, SyncConstants.EVENT_UPDATE);
		}
		catch (PortalException pe) {
			throw new PortalException(pe.getClass().getName(), pe);
		}
	}

	protected void getAllSyncDLObjects(
			long repositoryId, long folderId, List<SyncDLObject> syncDLObjects)
		throws PortalException, SystemException {

		List<Object> foldersAndFileEntriesAndFileShortcuts =
			dlAppService.getFoldersAndFileEntriesAndFileShortcuts(
				repositoryId, folderId, WorkflowConstants.STATUS_ANY, false,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Object folderAndFileEntryAndFileShortcut :
				foldersAndFileEntriesAndFileShortcuts) {

			if (folderAndFileEntryAndFileShortcut instanceof FileEntry) {
				FileEntry fileEntry =
					(FileEntry)folderAndFileEntryAndFileShortcut;

				syncDLObjects.add(
					SyncUtil.toSyncDLObject(
						fileEntry, SyncConstants.EVENT_GET));
			}
			else if (folderAndFileEntryAndFileShortcut instanceof Folder) {
				Folder folder = (Folder)folderAndFileEntryAndFileShortcut;

				if (!SyncUtil.isSupportedFolder(folder)) {
					continue;
				}

				syncDLObjects.add(
					SyncUtil.toSyncDLObject(folder, SyncConstants.EVENT_GET));

				getAllSyncDLObjects(
					repositoryId, folder.getFolderId(), syncDLObjects);
			}
		}
	}

	protected Map<String, String> getPortletPreferencesMap()
		throws PortalException, SystemException {

		Map<String, String> portletPreferencesMap =
			new HashMap<String, String>();

		User user = getUser();

		long companyId = user.getCompanyId();

		PortletPreferences portletPreferences = PrefsPropsUtil.getPreferences(
			companyId);

		int maxConnections = PrefsPropsUtil.getInteger(
			portletPreferences, companyId,
			PortletPropsKeys.SYNC_CLIENT_MAX_CONNECTIONS,
			PortletPropsValues.SYNC_CLIENT_MAX_CONNECTIONS);

		portletPreferencesMap.put(
			PortletPropsKeys.SYNC_CLIENT_MAX_CONNECTIONS,
			String.valueOf(maxConnections));

		int pollInterval = PrefsPropsUtil.getInteger(
			portletPreferences, companyId,
			PortletPropsKeys.SYNC_CLIENT_POLL_INTERVAL,
			PortletPropsValues.SYNC_CLIENT_POLL_INTERVAL);

		portletPreferencesMap.put(
			PortletPropsKeys.SYNC_CLIENT_POLL_INTERVAL,
			String.valueOf(pollInterval));

		return portletPreferencesMap;
	}

	protected void validateChecksum(File file, String checksum)
		throws PortalException {

		String fileChecksum = SyncUtil.getChecksum(file);

		if (!fileChecksum.equals(checksum)) {
			StringBundler sb = new StringBundler(4);

			sb.append("Expected checksum ");
			sb.append(checksum);
			sb.append(" does not match actual checksum ");
			sb.append(fileChecksum);

			throw new SyncDLObjectChecksumException(sb.toString());
		}
	}

}