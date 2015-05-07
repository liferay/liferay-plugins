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

package com.liferay.repository.external;

import com.liferay.portal.NoSuchRepositoryEntryException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.BaseRepositoryImpl;
import com.liferay.portal.kernel.repository.RepositoryException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileShortcut;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.HitsImpl;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Lock;
import com.liferay.portal.model.RepositoryEntry;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.NoSuchFileVersionException;
import com.liferay.portlet.documentlibrary.NoSuchFolderException;
import com.liferay.portlet.documentlibrary.model.DLFileEntryTypeConstants;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;
import com.liferay.repository.external.model.ExtRepositoryFileEntryAdapter;
import com.liferay.repository.external.model.ExtRepositoryFileVersionAdapter;
import com.liferay.repository.external.model.ExtRepositoryFolderAdapter;
import com.liferay.repository.external.model.ExtRepositoryObjectAdapter;
import com.liferay.repository.external.model.ExtRepositoryObjectAdapterType;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Iván Zaera
 * @author Sergio González
 */
public class ExtRepositoryAdapter extends BaseRepositoryImpl {

	@Override
	public FileEntry addFileEntry(
			long userId, long folderId, String sourceFileName, String mimeType,
			String title, String description, String changeLog,
			InputStream inputStream, long size, ServiceContext serviceContext)
		throws PortalException {

		String fileName = null;

		if (Validator.isNull(title)) {
			fileName = sourceFileName;
		}
		else {
			fileName = title;
		}

		String extRepositoryFolderKey = getExtRepositoryObjectKey(folderId);

		ExtRepositoryFileEntry extRepositoryFileEntry =
			_extRepository.addExtRepositoryFileEntry(
				extRepositoryFolderKey, mimeType, fileName, description,
				changeLog, inputStream);

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FILE, extRepositoryFileEntry);
	}

	@Override
	public FileShortcut addFileShortcut(
		long userId, long folderId, long toFileEntryId,
		ServiceContext serviceContext) {

		throw new UnsupportedOperationException();
	}

	@Override
	public ExtRepositoryFolderAdapter addFolder(
			long userId, long parentFolderId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException {

		String extRepositoryParentFolderKey = getExtRepositoryObjectKey(
			parentFolderId);

		ExtRepositoryFolder extRepositoryFolder =
			_extRepository.addExtRepositoryFolder(
				extRepositoryParentFolderKey, name, description);

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FOLDER, extRepositoryFolder);
	}

	@Override
	public FileVersion cancelCheckOut(long fileEntryId) throws PortalException {
		String extRepositoryFileEntryKey = getExtRepositoryObjectKey(
			fileEntryId);

		ExtRepositoryFileVersion extRepositoryFileVersion =
			_extRepository.cancelCheckOut(extRepositoryFileEntryKey);

		if (extRepositoryFileVersion != null) {
			ExtRepositoryFileEntryAdapter extRepositoryFileEntryAdapter =
				getFileEntry(fileEntryId);

			return _toExtRepositoryFileVersionAdapter(
				extRepositoryFileEntryAdapter, extRepositoryFileVersion);
		}

		return null;
	}

	@Override
	public void checkInFileEntry(
			long userId, long fileEntryId, boolean major, String changeLog,
			ServiceContext serviceContext)
		throws PortalException {

		String extRepositoryFileEntryKey = getExtRepositoryObjectKey(
			fileEntryId);

		_extRepository.checkInExtRepositoryFileEntry(
			extRepositoryFileEntryKey, major, changeLog);
	}

	@Override
	public void checkInFileEntry(
			long userId, long fileEntryId, String lockUuid,
			ServiceContext serviceContext)
		throws PortalException {

		checkInFileEntry(
			userId, fileEntryId, false, StringPool.BLANK, serviceContext);
	}

	@Override
	public FileEntry checkOutFileEntry(
			long fileEntryId, ServiceContext serviceContext)
		throws PortalException {

		String extRepositoryFileEntryKey = getExtRepositoryObjectKey(
			fileEntryId);

		ExtRepositoryFileEntry extRepositoryFileEntry =
			_extRepository.checkOutExtRepositoryFileEntry(
				extRepositoryFileEntryKey);

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FILE, extRepositoryFileEntry);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public FileEntry checkOutFileEntry(
			long fileEntryId, String owner, long expirationTime,
			ServiceContext serviceContext)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public FileEntry copyFileEntry(
			long userId, long groupId, long fileEntryId, long destFolderId,
			ServiceContext serviceContext)
		throws PortalException {

		String extRepositoryFileEntryKey = getExtRepositoryObjectKey(
			fileEntryId);

		ExtRepositoryFileEntry extRepositoryFileEntry =
			_extRepository.getExtRepositoryObject(
				ExtRepositoryObjectType.FILE, extRepositoryFileEntryKey);

		String newExtRepositoryFolderKey = getExtRepositoryObjectKey(
			destFolderId);

		ExtRepositoryFileEntry copyExtRepositoryFileEntry =
			_extRepository.copyExtRepositoryObject(
				ExtRepositoryObjectType.FILE, extRepositoryFileEntryKey,
				newExtRepositoryFolderKey, extRepositoryFileEntry.getTitle());

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FILE, copyExtRepositoryFileEntry);
	}

	@Override
	public void deleteFileEntry(long fileEntryId) throws PortalException {
		String extRepositoryFileEntryKey = getExtRepositoryObjectKey(
			fileEntryId);

		_extRepository.deleteExtRepositoryObject(
			ExtRepositoryObjectType.FILE, extRepositoryFileEntryKey);

		ExtRepositoryAdapterCache extRepositoryAdapterCache =
			ExtRepositoryAdapterCache.getInstance();

		extRepositoryAdapterCache.remove(extRepositoryFileEntryKey);
	}

	@Override
	public void deleteFileShortcut(long fileShortcutId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteFileShortcuts(long toFileEntryId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteFolder(long folderId) throws PortalException {
		String extRepositoryFolderKey = getExtRepositoryObjectKey(folderId);

		_extRepository.deleteExtRepositoryObject(
			ExtRepositoryObjectType.FOLDER, extRepositoryFolderKey);

		ExtRepositoryAdapterCache extRepositoryAdapterCache =
			ExtRepositoryAdapterCache.getInstance();

		extRepositoryAdapterCache.remove(extRepositoryFolderKey);
	}

	public String getAuthType() {
		return _extRepository.getAuthType();
	}

	public InputStream getContentStream(
			ExtRepositoryFileEntryAdapter extRepositoryFileEntryAdapter)
		throws PortalException {

		return _extRepository.getContentStream(
			extRepositoryFileEntryAdapter.getExtRepositoryModel());
	}

	public InputStream getContentStream(
			ExtRepositoryFileVersionAdapter extRepositoryFileVersionAdapter)
		throws PortalException {

		return _extRepository.getContentStream(
			extRepositoryFileVersionAdapter.getExtRepositoryModel());
	}

	public List<ExtRepositoryFileVersionAdapter>
			getExtRepositoryFileVersionAdapters(
				ExtRepositoryFileEntryAdapter extRepositoryFileEntryAdapter)
		throws PortalException {

		List<ExtRepositoryFileVersion> extRepositoryFileVersions =
			_extRepository.getExtRepositoryFileVersions(
				extRepositoryFileEntryAdapter.getExtRepositoryModel());

		return _toExtRepositoryFileVersionAdapters(
			extRepositoryFileEntryAdapter, extRepositoryFileVersions);
	}

	@Override
	public List<FileEntry> getFileEntries(
			long folderId, int status, int start, int end,
			OrderByComparator<FileEntry> obc)
		throws PortalException {

		return getFileEntries(folderId, start, end, obc);
	}

	@Override
	public List<FileEntry> getFileEntries(
			long folderId, int start, int end, OrderByComparator<FileEntry> obc)
		throws PortalException {

		String extRepositoryFolderKey = getExtRepositoryObjectKey(folderId);

		List<ExtRepositoryFileEntry> extRepositoryFileEntries =
			_extRepository.getExtRepositoryObjects(
				ExtRepositoryObjectType.FILE, extRepositoryFolderKey);

		List<ExtRepositoryFileEntryAdapter> extRepositoryFileEntryAdapters =
			_toExtRepositoryObjectAdapters(
				ExtRepositoryObjectAdapterType.FILE, extRepositoryFileEntries);

		return _subList(extRepositoryFileEntryAdapters, start, end, obc);
	}

	@Override
	public List<FileEntry> getFileEntries(
			long folderId, long fileEntryTypeId, int start, int end,
			OrderByComparator<FileEntry> obc)
		throws PortalException {

		if (fileEntryTypeId ==
				DLFileEntryTypeConstants.FILE_ENTRY_TYPE_ID_BASIC_DOCUMENT) {

			return getFileEntries(folderId, start, end, obc);
		}
		else {
			return Collections.emptyList();
		}
	}

	@Override
	public List<FileEntry> getFileEntries(
			long folderId, String[] mimeTypes, int start, int end,
			OrderByComparator<FileEntry> obc)
		throws PortalException {

		String extRepositoryFolderKey = getExtRepositoryObjectKey(folderId);

		List<ExtRepositoryFileEntry> extRepositoryFileEntries =
			_extRepository.getExtRepositoryObjects(
				ExtRepositoryObjectType.FILE, extRepositoryFolderKey);

		List<ExtRepositoryFileEntryAdapter> extRepositoryFileEntryAdapters =
			_toExtRepositoryObjectAdapters(
				ExtRepositoryObjectAdapterType.FILE, extRepositoryFileEntries);

		extRepositoryFileEntryAdapters = _filterByMimeType(
			extRepositoryFileEntryAdapters, mimeTypes);

		return _subList(extRepositoryFileEntryAdapters, start, end, obc);
	}

	@Override
	public int getFileEntriesCount(long folderId) throws PortalException {
		String extRepositoryFolderKey = getExtRepositoryObjectKey(folderId);

		return _extRepository.getExtRepositoryObjectsCount(
			ExtRepositoryObjectType.FILE, extRepositoryFolderKey);
	}

	@Override
	public int getFileEntriesCount(long folderId, int status)
		throws PortalException {

		return getFileEntriesCount(folderId);
	}

	@Override
	public int getFileEntriesCount(long folderId, long fileEntryTypeId)
		throws PortalException {

		if (fileEntryTypeId ==
				DLFileEntryTypeConstants.FILE_ENTRY_TYPE_ID_BASIC_DOCUMENT) {

			String extRepositoryFolderKey = getExtRepositoryObjectKey(folderId);

			return _extRepository.getExtRepositoryObjectsCount(
				ExtRepositoryObjectType.FILE, extRepositoryFolderKey);
		}

		return 0;
	}

	@Override
	public int getFileEntriesCount(long folderId, String[] mimeTypes)
		throws PortalException {

		String extRepositoryFolderKey = getExtRepositoryObjectKey(folderId);

		return _extRepository.getExtRepositoryObjectsCount(
			ExtRepositoryObjectType.FILE, extRepositoryFolderKey);
	}

	@Override
	public ExtRepositoryFileEntryAdapter getFileEntry(long fileEntryId)
		throws PortalException {

		String extRepositoryFileEntryKey = getExtRepositoryObjectKey(
			fileEntryId);

		ExtRepositoryObject extRepositoryObject =
			_extRepository.getExtRepositoryObject(
				ExtRepositoryObjectType.FILE, extRepositoryFileEntryKey);

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FILE, extRepositoryObject);
	}

	@Override
	public FileEntry getFileEntry(long folderId, String title)
		throws PortalException {

		String extRepositoryFolderKey = getExtRepositoryObjectKey(folderId);

		ExtRepositoryObject extRepositoryObject =
			_extRepository.getExtRepositoryObject(
				ExtRepositoryObjectType.FILE, extRepositoryFolderKey, title);

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FILE, extRepositoryObject);
	}

	@Override
	public FileEntry getFileEntryByUuid(String uuid) throws PortalException {
		String extRepositoryFileEntryKey = _getExtRepositoryObjectKey(uuid);

		ExtRepositoryFileEntry extRepositoryFileEntry =
			_extRepository.getExtRepositoryObject(
				ExtRepositoryObjectType.FILE, extRepositoryFileEntryKey);

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FILE, extRepositoryFileEntry);
	}

	@Override
	public FileShortcut getFileShortcut(long fileShortcutId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public FileVersion getFileVersion(long fileVersionId)
		throws PortalException {

		String extRepositoryFileVersionKey = getExtRepositoryObjectKey(
			fileVersionId);

		ExtRepositoryFileVersionDescriptor extRepositoryFileVersionDescriptor =
			_extRepository.getExtRepositoryFileVersionDescriptor(
				extRepositoryFileVersionKey);

		String extRepositoryFileEntryKey =
			extRepositoryFileVersionDescriptor.getExtRepositoryFileEntryKey();

		String version = extRepositoryFileVersionDescriptor.getVersion();

		ExtRepositoryFileEntry extRepositoryFileEntry =
			_extRepository.getExtRepositoryObject(
				ExtRepositoryObjectType.FILE, extRepositoryFileEntryKey);

		ExtRepositoryFileVersion extRepositoryFileVersion =
			_extRepository.getExtRepositoryFileVersion(
				extRepositoryFileEntry, version);

		if (extRepositoryFileVersion == null) {
			return null;
		}

		ExtRepositoryFileEntryAdapter extRepositoryFileEntryAdapter =
			_toExtRepositoryObjectAdapter(
				ExtRepositoryObjectAdapterType.FILE, extRepositoryFileEntry);

		return _toExtRepositoryFileVersionAdapter(
			extRepositoryFileEntryAdapter, extRepositoryFileVersion);
	}

	@Override
	public ExtRepositoryFolderAdapter getFolder(long folderId)
		throws PortalException {

		String extRepositoryFolderKey = getExtRepositoryObjectKey(folderId);

		ExtRepositoryFolder extRepositoryFolder =
			_extRepository.getExtRepositoryObject(
				ExtRepositoryObjectType.FOLDER, extRepositoryFolderKey);

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FOLDER, extRepositoryFolder);
	}

	@Override
	public ExtRepositoryFolderAdapter getFolder(
			long parentFolderId, String name)
		throws PortalException {

		String extRepositoryParentFolderKey = getExtRepositoryObjectKey(
			parentFolderId);

		ExtRepositoryFolder extRepositoryFolder =
			_extRepository.getExtRepositoryObject(
				ExtRepositoryObjectType.FOLDER, extRepositoryParentFolderKey,
				name);

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FOLDER, extRepositoryFolder);
	}

	@Override
	public List<Folder> getFolders(
			long parentFolderId, boolean includeMountFolders, int start,
			int end, OrderByComparator<Folder> obc)
		throws PortalException {

		String extRepositoryParentFolderKey = getExtRepositoryObjectKey(
			parentFolderId);

		List<ExtRepositoryFolder> extRepositoryFolders =
			_extRepository.getExtRepositoryObjects(
				ExtRepositoryObjectType.FOLDER, extRepositoryParentFolderKey);

		List<ExtRepositoryFolderAdapter> extRepositoryFolderAdapters =
			_toExtRepositoryObjectAdapters(
				ExtRepositoryObjectAdapterType.FOLDER, extRepositoryFolders);

		return _subList(extRepositoryFolderAdapters, start, end, obc);
	}

	@Override
	public List<Object> getFoldersAndFileEntries(
		long folderId, int start, int end, OrderByComparator<?> obc) {

		try {
			String extRepositoryFolderKey = getExtRepositoryObjectKey(folderId);

			List<? extends ExtRepositoryObject> extRepositoryObjects =
				_extRepository.getExtRepositoryObjects(
					ExtRepositoryObjectType.OBJECT, extRepositoryFolderKey);

			List<ExtRepositoryObjectAdapter<?>> extRepositoryObjectAdapters =
				_toExtRepositoryObjectAdapters(
					ExtRepositoryObjectAdapterType.OBJECT,
					extRepositoryObjects);

			return _subList(
				extRepositoryObjectAdapters, start, end,
				(OrderByComparator<Object>)obc);
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	public List<Object> getFoldersAndFileEntries(
			long folderId, String[] mimeTypes, int start, int end,
			OrderByComparator<?> obc)
		throws PortalException {

		String extRepositoryFolderKey = getExtRepositoryObjectKey(folderId);

		List<ExtRepositoryObject> extRepositoryObjects =
			_extRepository.getExtRepositoryObjects(
				ExtRepositoryObjectType.OBJECT, extRepositoryFolderKey);

		List<ExtRepositoryObjectAdapter<?>> extRepositoryObjectAdapters =
			_toExtRepositoryObjectAdapters(
				ExtRepositoryObjectAdapterType.OBJECT, extRepositoryObjects);

		extRepositoryObjectAdapters = _filterByMimeType(
			extRepositoryObjectAdapters, mimeTypes);

		return _subList(
			extRepositoryObjectAdapters, start, end,
			(OrderByComparator<Object>)obc);
	}

	@Override
	public int getFoldersAndFileEntriesCount(long folderId) {
		try {
			String extRepositoryFolderKey = getExtRepositoryObjectKey(folderId);

			return _extRepository.getExtRepositoryObjectsCount(
				ExtRepositoryObjectType.OBJECT, extRepositoryFolderKey);
		}
		catch (PortalException pe) {
			throw new SystemException(pe);
		}
	}

	@Override
	public int getFoldersAndFileEntriesCount(long folderId, String[] mimeTypes)
		throws PortalException {

		List<Object> extRepositoryObjects = getFoldersAndFileEntries(
			folderId, mimeTypes, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		return extRepositoryObjects.size();
	}

	@Override
	public int getFoldersCount(long parentFolderId, boolean includeMountfolders)
		throws PortalException {

		String extRepositoryParentFolderKey = getExtRepositoryObjectKey(
			parentFolderId);

		return _extRepository.getExtRepositoryObjectsCount(
			ExtRepositoryObjectType.FOLDER, extRepositoryParentFolderKey);
	}

	@Override
	public int getFoldersFileEntriesCount(List<Long> folderIds, int status)
		throws PortalException {

		int count = 0;

		for (long folderId : folderIds) {
			String extRepositoryFolderKey = getExtRepositoryObjectKey(folderId);

			count += _extRepository.getExtRepositoryObjectsCount(
				ExtRepositoryObjectType.FILE, extRepositoryFolderKey);
		}

		return count;
	}

	public String getLiferayLogin(String extRepositoryUserName) {
		return _extRepository.getLiferayLogin(extRepositoryUserName);
	}

	@Override
	@SuppressWarnings("unused")
	public List<Folder> getMountFolders(
			long parentFolderId, int start, int end,
			OrderByComparator<Folder> obc)
		throws PortalException {

		return Collections.emptyList();
	}

	@Override
	@SuppressWarnings("unused")
	public int getMountFoldersCount(long parentFolderId)
		throws PortalException {

		return 0;
	}

	public ExtRepositoryFolderAdapter getParentFolder(
			ExtRepositoryObjectAdapter<?> extRepositoryObjectAdapter)
		throws PortalException {

		ExtRepositoryFolder parentFolder =
			_extRepository.getExtRepositoryParentFolder(
				extRepositoryObjectAdapter.getExtRepositoryModel());

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FOLDER, parentFolder);
	}

	@Override
	public void getSubfolderIds(List<Long> folderIds, long folderId)
		throws PortalException {

		folderIds.addAll(getSubfolderIds(folderId, true));
	}

	@Override
	public List<Long> getSubfolderIds(long folderId, boolean recurse)
		throws PortalException {

		String extRepositoryFolderKey = getExtRepositoryObjectKey(folderId);

		List<String> extRepositorySubfolderKeys =
			_extRepository.getSubfolderKeys(extRepositoryFolderKey, recurse);

		List<Long> subfolderIds = new ArrayList<>();

		for (String extRepositorySubfolderKey : extRepositorySubfolderKeys) {
			RepositoryEntry repositoryEntry = getRepositoryEntry(
				extRepositorySubfolderKey);

			subfolderIds.add(repositoryEntry.getRepositoryEntryId());
		}

		return subfolderIds;
	}

	@Override
	public String[] getSupportedConfigurations() {
		return _extRepository.getSupportedConfigurations();
	}

	@Override
	public String[][] getSupportedParameters() {
		return _extRepository.getSupportedParameters();
	}

	@Override
	public void initRepository() throws PortalException {
		try {
			CredentialsProvider credentialsProvider =
				new CredentialsProvider() {

					@Override
					public String getLogin() {
						return _getLogin();
					}

					@Override
					public String getPassword() {
						return _getPassword();
					}

				};

			_extRepository.initRepository(
				getTypeSettingsProperties(), credentialsProvider);
		}
		catch (PortalException | SystemException e) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to initialize repository " + _extRepository, e);
			}

			throw e;
		}
	}

	@Override
	@SuppressWarnings("unused")
	public Lock lockFolder(long folderId) throws PortalException {
		throw new UnsupportedOperationException();
	}

	@Override
	@SuppressWarnings("unused")
	public Lock lockFolder(
			long folderId, String owner, boolean inheritable,
			long expirationTime)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public FileEntry moveFileEntry(
			long userId, long fileEntryId, long newFolderId,
			ServiceContext serviceContext)
		throws PortalException {

		String extRepositoryFileEntryKey = getExtRepositoryObjectKey(
			fileEntryId);

		ExtRepositoryFileEntry extRepositoryFileEntry =
			_extRepository.getExtRepositoryObject(
				ExtRepositoryObjectType.FILE, extRepositoryFileEntryKey);

		String extRepositoryNewFolderKey = getExtRepositoryObjectKey(
			newFolderId);

		ExtRepositoryFileEntry moveExtRepositoryFileEntry =
			_extRepository.moveExtRepositoryObject(
				ExtRepositoryObjectType.FILE, extRepositoryFileEntryKey,
				extRepositoryNewFolderKey, extRepositoryFileEntry.getTitle());

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FILE, moveExtRepositoryFileEntry);
	}

	@Override
	public ExtRepositoryFolderAdapter moveFolder(
			long userId, long folderId, long newParentFolderId,
			ServiceContext serviceContext)
		throws PortalException {

		String extRepositoryFolderKey = getExtRepositoryObjectKey(folderId);

		ExtRepositoryFolder extRepositoryFolder =
			_extRepository.getExtRepositoryObject(
				ExtRepositoryObjectType.FOLDER, extRepositoryFolderKey);

		String extRepositoryNewParentFolderKey = getExtRepositoryObjectKey(
			newParentFolderId);

		ExtRepositoryFolder moveExtRepositoryFolder =
			_extRepository.moveExtRepositoryObject(
				ExtRepositoryObjectType.FOLDER, extRepositoryFolderKey,
				extRepositoryNewParentFolderKey, extRepositoryFolder.getName());

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FOLDER, moveExtRepositoryFolder);
	}

	@Override
	@SuppressWarnings("unused")
	public Lock refreshFileEntryLock(
			String lockUuid, long companyId, long expirationTime)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	@SuppressWarnings("unused")
	public Lock refreshFolderLock(
			String lockUuid, long companyId, long expirationTime)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void revertFileEntry(
			long userId, long fileEntryId, String version,
			ServiceContext serviceContext)
		throws PortalException {

		String extRepositoryFileEntryKey = getExtRepositoryObjectKey(
			fileEntryId);

		ExtRepositoryFileEntry extRepositoryFileEntry =
			_extRepository.getExtRepositoryObject(
				ExtRepositoryObjectType.FILE, extRepositoryFileEntryKey);

		ExtRepositoryFileVersion extRepositoryFileVersion = null;

		List<ExtRepositoryFileVersion> extRepositoryFileVersions =
			_extRepository.getExtRepositoryFileVersions(extRepositoryFileEntry);

		for (ExtRepositoryFileVersion curExtRepositoryFileVersion :
				extRepositoryFileVersions) {

			String curVersion = curExtRepositoryFileVersion.getVersion();

			if (curVersion.equals(version)) {
				extRepositoryFileVersion = curExtRepositoryFileVersion;

				break;
			}
		}

		if (extRepositoryFileVersion != null) {
			InputStream inputStream = _extRepository.getContentStream(
				extRepositoryFileVersion);

			boolean needsCheckIn = false;

			if (!isCheckedOut(extRepositoryFileEntry)) {
				try {
					_extRepository.checkOutExtRepositoryFileEntry(
						extRepositoryFileEntryKey);

					needsCheckIn = true;
				}
				catch (UnsupportedOperationException uoe) {
				}
			}

			_extRepository.updateExtRepositoryFileEntry(
				extRepositoryFileEntryKey,
				extRepositoryFileVersion.getMimeType(), inputStream);

			String changeLog = LanguageUtil.format(
				serviceContext.getLocale(), "reverted-to-x", version, false);

			if (needsCheckIn) {
				try {
					_extRepository.checkInExtRepositoryFileEntry(
						extRepositoryFileEntryKey, true, changeLog);
				}
				catch (UnsupportedOperationException uoe) {
				}
			}
		}
		else {
			throw new NoSuchFileVersionException(
				"No file version with {extRepositoryModelKey=" +
					extRepositoryFileEntry.getExtRepositoryModelKey() +
						", version: " + version + "}");
		}
	}

	@Override
	@SuppressWarnings("unused")
	public Hits search(long creatorUserId, int status, int start, int end)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	@SuppressWarnings("unused")
	public Hits search(
			long creatorUserId, long folderId, String[] mimeTypes, int status,
			int start, int end)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public Hits search(SearchContext searchContext, Query query)
		throws SearchException {

		long startTime = System.currentTimeMillis();

		List<ExtRepositorySearchResult<?>> extRepositorySearchResults = null;

		try {
			extRepositorySearchResults = _extRepository.search(
				searchContext, query, new ExtRepositoryQueryMapperImpl(this));
		}
		catch (PortalException | SystemException e) {
			throw new SearchException("Unable to perform search", e);
		}

		QueryConfig queryConfig = searchContext.getQueryConfig();

		List<Document> documents = new ArrayList<>();
		List<String> snippets = new ArrayList<>();
		List<Float> scores = new ArrayList<>();

		int total = 0;

		for (ExtRepositorySearchResult<?> extRepositorySearchResult :
				extRepositorySearchResults) {

			try {
				ExtRepositoryObjectAdapter<?> extRepositoryEntryAdapter =
					_toExtRepositoryObjectAdapter(
						ExtRepositoryObjectAdapterType.OBJECT,
						extRepositorySearchResult.getObject());

				Document document = new DocumentImpl();

				document.addKeyword(
					Field.ENTRY_CLASS_NAME,
					extRepositoryEntryAdapter.getModelClassName());
				document.addKeyword(
					Field.ENTRY_CLASS_PK,
					extRepositoryEntryAdapter.getPrimaryKey());
				document.addKeyword(
					Field.TITLE, extRepositoryEntryAdapter.getName());

				documents.add(document);

				if (queryConfig.isScoreEnabled()) {
					scores.add(extRepositorySearchResult.getScore());
				}
				else {
					scores.add(1.0F);
				}

				snippets.add(extRepositorySearchResult.getSnippet());

				total++;
			}
			catch (PortalException | SystemException e) {
				if (_log.isWarnEnabled()) {
					_log.warn("Invalid entry returned from search", e);
				}
			}
		}

		float searchTime =
			(float)(System.currentTimeMillis() - startTime) / Time.SECOND;

		Hits hits = new HitsImpl();

		hits.setDocs(documents.toArray(new Document[documents.size()]));
		hits.setLength(total);
		hits.setQueryTerms(new String[0]);
		hits.setScores(ArrayUtil.toFloatArray(scores));
		hits.setSearchTime(searchTime);
		hits.setSnippets(snippets.toArray(new String[snippets.size()]));
		hits.setStart(startTime);

		return hits;
	}

	@Override
	@SuppressWarnings("unused")
	public void unlockFolder(long folderId, String lockUuid)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public FileEntry updateFileEntry(
			long userId, long fileEntryId, String sourceFileName,
			String mimeType, String title, String description, String changeLog,
			boolean majorVersion, InputStream inputStream, long size,
			ServiceContext serviceContext)
		throws PortalException {

		String extRepositoryFileEntryKey = getExtRepositoryObjectKey(
			fileEntryId);

		ExtRepositoryFileEntry extRepositoryFileEntry =
			_extRepository.getExtRepositoryObject(
				ExtRepositoryObjectType.FILE, extRepositoryFileEntryKey);

		boolean needsCheckIn = false;

		if (!isCheckedOut(extRepositoryFileEntry)) {
			_extRepository.checkOutExtRepositoryFileEntry(
				extRepositoryFileEntryKey);

			needsCheckIn = true;
		}

		if (inputStream != null) {
			extRepositoryFileEntry =
				_extRepository.updateExtRepositoryFileEntry(
					extRepositoryFileEntryKey, mimeType, inputStream);
		}

		if (!title.equals(extRepositoryFileEntry.getTitle())) {
			ExtRepositoryFolder folder =
				_extRepository.getExtRepositoryParentFolder(
					extRepositoryFileEntry);

			extRepositoryFileEntry = _extRepository.moveExtRepositoryObject(
				ExtRepositoryObjectType.FILE, extRepositoryFileEntryKey,
				folder.getExtRepositoryModelKey(), title);

			ExtRepositoryAdapterCache extRepositoryAdapterCache =
				ExtRepositoryAdapterCache.getInstance();

			extRepositoryAdapterCache.remove(extRepositoryFileEntryKey);
		}

		if (needsCheckIn) {
			_extRepository.checkInExtRepositoryFileEntry(
				extRepositoryFileEntryKey, majorVersion, changeLog);
		}

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FILE, extRepositoryFileEntry);
	}

	@Override
	public FileShortcut updateFileShortcut(
		long userId, long fileShortcutId, long folderId, long toFileEntryId,
		ServiceContext serviceContext) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void updateFileShortcuts(
		long oldToFileEntryId, long newToFileEntryId) {

		throw new UnsupportedOperationException();
	}

	@Override
	public ExtRepositoryFolderAdapter updateFolder(
			long folderId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException {

		String extRepositoryFolderKey = getExtRepositoryObjectKey(folderId);

		ExtRepositoryFolder extRepositoryFolder =
			_extRepository.getExtRepositoryObject(
				ExtRepositoryObjectType.FOLDER, extRepositoryFolderKey);

		ExtRepositoryFolder parentExtRepositoryFolder =
			_extRepository.getExtRepositoryParentFolder(extRepositoryFolder);

		ExtRepositoryFolder newExtRepositoryFolder =
			_extRepository.moveExtRepositoryObject(
				ExtRepositoryObjectType.FOLDER, extRepositoryFolderKey,
				parentExtRepositoryFolder.getExtRepositoryModelKey(), name);

		ExtRepositoryAdapterCache extRepositoryAdapterCache =
			ExtRepositoryAdapterCache.getInstance();

		extRepositoryAdapterCache.remove(extRepositoryFolderKey);

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FOLDER, newExtRepositoryFolder);
	}

	@Override
	@SuppressWarnings("unused")
	public boolean verifyFileEntryCheckOut(long fileEntryId, String lockUuid)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	@SuppressWarnings("unused")
	public boolean verifyInheritableLock(long folderId, String lockUuid)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	protected ExtRepositoryAdapter(ExtRepository extRepository) {
		if (extRepository == null) {
			extRepository = (ExtRepository)this;
		}

		_extRepository = extRepository;
	}

	protected String getExtRepositoryObjectKey(long repositoryEntryId)
		throws PortalException {

		RepositoryEntry repositoryEntry =
			repositoryEntryLocalService.fetchRepositoryEntry(repositoryEntryId);

		if (repositoryEntry != null) {
			return repositoryEntry.getMappedId();
		}

		DLFolder rootMountDLFolder = DLFolderLocalServiceUtil.getDLFolder(
			repositoryEntryId);

		repositoryEntry = _getRootRepositoryEntry(rootMountDLFolder);

		return repositoryEntry.getMappedId();
	}

	protected boolean isCheckedOut(
		ExtRepositoryFileEntry extRepositoryFileEntry) {

		String checkedOutBy = extRepositoryFileEntry.getCheckedOutBy();

		if (Validator.isNull(checkedOutBy)) {
			return false;
		}

		return true;
	}

	private void _checkAssetEntry(
			ExtRepositoryFileEntryAdapter extRepositoryFileEntryAdapter)
		throws PortalException {

		dlAppHelperLocalService.checkAssetEntry(
			PrincipalThreadLocal.getUserId(), extRepositoryFileEntryAdapter,
			extRepositoryFileEntryAdapter.getFileVersion() );
	}

	private User _fetchDefaultUser() {
		try {
			return userLocalService.getDefaultUser(getCompanyId());
		}
		catch (PortalException e) {
			_log.error(
				"Unable to get default user for company " + getCompanyId(), e);

			return null;
		}
	}

	private <T extends ExtRepositoryObjectAdapter<?>> List<T> _filterByMimeType(
		List<T> extRepositoryObjects, String[] mimeTypes) {

		if (ArrayUtil.isEmpty(mimeTypes)) {
			return extRepositoryObjects;
		}

		Set<String> allowedMimeTypes = new HashSet<>(Arrays.asList(mimeTypes));

		List<T> filteredExtRepositoryObjects = new ArrayList<>();

		for (T extRepositoryObject : extRepositoryObjects) {
			if (extRepositoryObject instanceof ExtRepositoryFileEntryAdapter) {
				ExtRepositoryFileEntryAdapter extRepositoryFileEntryAdapter =
					(ExtRepositoryFileEntryAdapter)extRepositoryObject;

				if (allowedMimeTypes.contains(
						extRepositoryFileEntryAdapter.getMimeType())) {

					filteredExtRepositoryObjects.add(extRepositoryObject);
				}
			}
		}

		return filteredExtRepositoryObjects;
	}

	private void _forceGetFileVersions(
		ExtRepositoryFileEntryAdapter extRepositoryFileEntryAdapter) {

		extRepositoryFileEntryAdapter.getFileVersions(
			WorkflowConstants.STATUS_ANY);
	}

	private String _getExtRepositoryObjectKey(String uuid)
		throws PortalException {

		RepositoryEntry repositoryEntry =
			repositoryEntryLocalService.fetchRepositoryEntryByUuidAndGroupId(
				uuid, getGroupId());

		if (repositoryEntry == null) {
			throw new NoSuchRepositoryEntryException(
				"No repository entry exits with UUID " + uuid);
		}

		return repositoryEntry.getMappedId();
	}

	private String _getLogin() {
		String login = PrincipalThreadLocal.getName();

		if (Validator.isNull(login) || _isDefaultUser(login)) {
			return PropsUtil.get(PropsKeys.DL_REPOSITORY_GUEST_USERNAME);
		}

		try {
			String authType = getAuthType();

			if (!authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
				User user = userLocalService.getUser(GetterUtil.getLong(login));

				if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
					login = user.getEmailAddress();
				}
				else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
					login = user.getScreenName();
				}
			}
		}
		catch (PortalException | SystemException e) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to get login to connect to external repository " +
						_extRepository,
					e);
			}

			login = null;
		}

		return login;
	}

	private String _getPassword() {
		String login = PrincipalThreadLocal.getName();

		if (Validator.isNull(login) || _isDefaultUser(login)) {
			return PropsUtil.get(PropsKeys.DL_REPOSITORY_GUEST_PASSWORD);
		}

		return PrincipalThreadLocal.getPassword();
	}

	private RepositoryEntry _getRootRepositoryEntry(DLFolder rootMountFolder)
		throws PortalException {

		return repositoryEntryLocalService.getRepositoryEntry(
			rootMountFolder.getUserId(), getGroupId(), getRepositoryId(),
			_extRepository.getRootFolderKey());
	}

	private boolean _isDefaultUser(String login) {
		User defaultUser = _fetchDefaultUser();

		if ((defaultUser != null) &&
			login.equals(defaultUser.getScreenName())) {

			return true;
		}

		return false;
	}

	private <T, V extends T> List<T> _subList(
		List<V> list, int start, int end, OrderByComparator<T> obc) {

		if (obc != null) {
			list = ListUtil.sort(list, obc);
		}

		return (List<T>)ListUtil.toList(ListUtil.subList(list, start, end));
	}

	private ExtRepositoryFileVersionAdapter _toExtRepositoryFileVersionAdapter(
			ExtRepositoryFileEntryAdapter extRepositoryFileEntryAdapter,
			ExtRepositoryFileVersion extRepositoryFileVersion)
		throws PortalException {

		ExtRepositoryAdapterCache extRepositoryAdapterCache =
			ExtRepositoryAdapterCache.getInstance();

		String extRepositoryModelKey =
			extRepositoryFileVersion.getExtRepositoryModelKey();

		ExtRepositoryFileVersionAdapter extRepositoryVersionAdapter =
			extRepositoryAdapterCache.get(extRepositoryModelKey);

		if (extRepositoryVersionAdapter == null) {
			RepositoryEntry repositoryEntry = getRepositoryEntry(
				extRepositoryModelKey);

			extRepositoryVersionAdapter = new ExtRepositoryFileVersionAdapter(
				this, repositoryEntry.getRepositoryEntryId(),
				repositoryEntry.getUuid(), extRepositoryFileEntryAdapter,
				extRepositoryFileVersion);

			extRepositoryAdapterCache.put(extRepositoryVersionAdapter);
		}

		return extRepositoryVersionAdapter;
	}

	private List<ExtRepositoryFileVersionAdapter>
			_toExtRepositoryFileVersionAdapters(
				ExtRepositoryFileEntryAdapter extRepositoryFileEntryAdapter,
				List<ExtRepositoryFileVersion> extRepositoryFileVersions)
		throws PortalException {

		List<ExtRepositoryFileVersionAdapter> extRepositoryFileVersionAdapters =
			new ArrayList<>();

		for (ExtRepositoryFileVersion extRepositoryFileVersion :
				extRepositoryFileVersions) {

			ExtRepositoryFileVersionAdapter extRepositoryFileVersionAdapter =
				_toExtRepositoryFileVersionAdapter(
					extRepositoryFileEntryAdapter, extRepositoryFileVersion);

			extRepositoryFileVersionAdapters.add(
				extRepositoryFileVersionAdapter);
		}

		return extRepositoryFileVersionAdapters;
	}

	@SuppressWarnings("unchecked")
	private <T extends ExtRepositoryObjectAdapter<?>> T
			_toExtRepositoryObjectAdapter(
				ExtRepositoryObjectAdapterType<T>
					extRepositoryObjectAdapterType,
				ExtRepositoryObject extRepositoryObject)
		throws PortalException {

		ExtRepositoryAdapterCache extRepositoryAdapterCache =
			ExtRepositoryAdapterCache.getInstance();

		String extRepositoryModelKey =
			extRepositoryObject.getExtRepositoryModelKey();

		ExtRepositoryObjectAdapter<?> extRepositoryObjectAdapter =
			extRepositoryAdapterCache.get(extRepositoryModelKey);

		if (extRepositoryObjectAdapter == null) {
			RepositoryEntry repositoryEntry = getRepositoryEntry(
				extRepositoryModelKey);

			if (extRepositoryObject instanceof ExtRepositoryFolder) {
				ExtRepositoryFolder extRepositoryFolder =
					(ExtRepositoryFolder)extRepositoryObject;

				extRepositoryObjectAdapter = new ExtRepositoryFolderAdapter(
					this, repositoryEntry.getRepositoryEntryId(),
					repositoryEntry.getUuid(), extRepositoryFolder);
			}
			else {
				ExtRepositoryFileEntry extRepositoryFileEntry =
					(ExtRepositoryFileEntry)extRepositoryObject;

				extRepositoryObjectAdapter = new ExtRepositoryFileEntryAdapter(
					this, repositoryEntry.getRepositoryEntryId(),
					repositoryEntry.getUuid(), extRepositoryFileEntry);

				_forceGetFileVersions(
					(ExtRepositoryFileEntryAdapter)extRepositoryObjectAdapter);

				_checkAssetEntry(
					(ExtRepositoryFileEntryAdapter)extRepositoryObjectAdapter);
			}

			extRepositoryAdapterCache.put(extRepositoryObjectAdapter);
		}

		if (extRepositoryObjectAdapterType ==
				ExtRepositoryObjectAdapterType.FILE) {

			if (!(extRepositoryObjectAdapter instanceof
					ExtRepositoryFileEntryAdapter)) {

				throw new NoSuchFileEntryException(
					"External repository object is not a file " +
						extRepositoryObject);
			}
		}
		else if (extRepositoryObjectAdapterType ==
					ExtRepositoryObjectAdapterType.FOLDER) {

			if (!(extRepositoryObjectAdapter instanceof
					ExtRepositoryFolderAdapter)) {

				throw new NoSuchFolderException(
					"External repository object is not a folder " +
						extRepositoryObject);
			}
		}
		else if (extRepositoryObjectAdapterType !=
					ExtRepositoryObjectAdapterType.OBJECT) {

			throw new IllegalArgumentException(
				"Unsupported repository object type " +
					extRepositoryObjectAdapterType);
		}

		return (T)extRepositoryObjectAdapter;
	}

	private <T extends ExtRepositoryObjectAdapter<?>> List<T>
			_toExtRepositoryObjectAdapters(
				ExtRepositoryObjectAdapterType<T>
					extRepositoryObjectAdapterType,
				List<? extends ExtRepositoryObject> extRepositoryObjects)
		throws PortalException {

		List<T> extRepositoryObjectAdapters = new ArrayList<>();

		for (ExtRepositoryObject extRepositoryObject : extRepositoryObjects) {
			extRepositoryObjectAdapters.add(
				_toExtRepositoryObjectAdapter(
					extRepositoryObjectAdapterType, extRepositoryObject));
		}

		return extRepositoryObjectAdapters;
	}

	private static Log _log = LogFactoryUtil.getLog(ExtRepositoryAdapter.class);

	private ExtRepository _extRepository;

}