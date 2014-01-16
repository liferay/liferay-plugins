/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.BaseRepositoryImpl;
import com.liferay.portal.kernel.repository.RepositoryException;
import com.liferay.portal.kernel.repository.model.FileEntry;
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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Lock;
import com.liferay.portal.model.RepositoryEntry;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.persistence.RepositoryEntryUtil;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.NoSuchFileVersionException;
import com.liferay.portlet.documentlibrary.NoSuchFolderException;
import com.liferay.portlet.documentlibrary.model.DLFileEntryTypeConstants;
import com.liferay.repository.external.model.ExtRepositoryEntryAdapter;
import com.liferay.repository.external.model.ExtRepositoryFileEntryAdapter;
import com.liferay.repository.external.model.ExtRepositoryFileVersionAdapter;
import com.liferay.repository.external.model.ExtRepositoryFolderAdapter;
import com.liferay.repository.external.model.ExtRepositoryModelAdapterType;

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
			long folderId, String sourceFileName, String mimeType, String title,
			String description, String changeLog, InputStream is, long size,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		String fileName = null;

		if (Validator.isNull(title)) {
			fileName = sourceFileName;
		}
		else {
			fileName = title;
		}

		String repositoryMappedFolderId = _getRepositoryEntryMappedId(folderId);

		ExtRepositoryFileEntry extRepositoryFileEntry =
			_extRepository.addFileEntry(
				repositoryMappedFolderId, fileName, changeLog, is);

		return _toExtRepositoryEntryAdapter(
			ExtRepositoryModelAdapterType.FILE, extRepositoryFileEntry);
	}

	@Override
	public ExtRepositoryFolderAdapter addFolder(
			long parentFolderId, String title, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		String repositoryMappedParentFolderId = _getRepositoryEntryMappedId(
			parentFolderId);

		ExtRepositoryFolder extRepositoryFolder = _extRepository.addFolder(
			repositoryMappedParentFolderId, title);

		return _toExtRepositoryEntryAdapter(
			ExtRepositoryModelAdapterType.FOLDER, extRepositoryFolder);
	}

	@Override
	public FileVersion cancelCheckOut(long fileEntryId)
		throws PortalException, SystemException {

		String repositoryMappedFileEntryId = _getRepositoryEntryMappedId(
			fileEntryId);

		ExtRepositoryFileVersion extRepositoryFileVersion =
			_extRepository.cancelCheckOut(repositoryMappedFileEntryId);

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
			long fileEntryId, boolean major, String changeLog,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		String repositoryMappedFileEntryId = _getRepositoryEntryMappedId(
			fileEntryId);

		_extRepository.checkInFileEntry(
			repositoryMappedFileEntryId, major, changeLog);
	}

	@Override
	public void checkInFileEntry(
			long fileEntryId, String lockUuid, ServiceContext serviceContext)
		throws PortalException, SystemException {

		checkInFileEntry(fileEntryId, false, StringPool.BLANK, serviceContext);
	}

	@Override
	public FileEntry checkOutFileEntry(
			long fileEntryId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		String repositoryMappedFileEntryId = _getRepositoryEntryMappedId(
			fileEntryId);

		ExtRepositoryFileEntry extRepositoryFileEntry =
			_extRepository.checkOutFileEntry(repositoryMappedFileEntryId);

		return _toExtRepositoryEntryAdapter(
			ExtRepositoryModelAdapterType.FILE, extRepositoryFileEntry);
	}

	@Override
	@SuppressWarnings("unused")
	public FileEntry checkOutFileEntry(
			long fileEntryId, String owner, long expirationTime,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException(
			"Checkout with expiration not supported");
	}

	@Override
	public FileEntry copyFileEntry(
			long groupId, long fileEntryId, long destFolderId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		String repositoryMappedFileEntryId = _getRepositoryEntryMappedId(
			fileEntryId);
		String repositoryMappedDestFolderId = _getRepositoryEntryMappedId(
			destFolderId);

		ExtRepositoryFileEntry extRepositoryFileEntry =
			_extRepository.getEntry(
				ExtRepositoryModelType.FILE, repositoryMappedFileEntryId);

		ExtRepositoryFileEntry copyExtRepositoryFileEntry =
			_extRepository.copyEntry(
				ExtRepositoryModelType.FILE, repositoryMappedFileEntryId,
				repositoryMappedDestFolderId,
				extRepositoryFileEntry.getTitle());

		return _toExtRepositoryEntryAdapter(
			ExtRepositoryModelAdapterType.FILE, copyExtRepositoryFileEntry);
	}

	@Override
	public void deleteFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		String repositoryMappedFileEntryId = _getRepositoryEntryMappedId(
			fileEntryId);

		_extRepository.deleteEntry(
			ExtRepositoryModelType.FILE, repositoryMappedFileEntryId);
	}

	@Override
	public void deleteFolder(long folderId)
		throws PortalException, SystemException {

		String repositoryMappedFolderId = _getRepositoryEntryMappedId(folderId);

		_extRepository.deleteEntry(
			ExtRepositoryModelType.FOLDER, repositoryMappedFolderId);
	}

	public String getAuthType() {
		return _extRepository.getAuthType();
	}

	public InputStream getContentStream(
			ExtRepositoryFileEntryAdapter extRepositoryFileEntryAdapter)
		throws PortalException, SystemException {

		return _extRepository.getContentStream(
			extRepositoryFileEntryAdapter.getExtRepositoryModel());
	}

	public InputStream getContentStream(
			ExtRepositoryFileVersionAdapter extRepositoryFileVersionAdapter)
		throws PortalException, SystemException {

		return _extRepository.getContentStream(
			extRepositoryFileVersionAdapter.getExtRepositoryModel());
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List<FileEntry> getFileEntries(
			long folderId, int start, int end, OrderByComparator obc)
		throws PortalException, SystemException {

		String repositoryMappedFolderId = _getRepositoryEntryMappedId(folderId);

		List<ExtRepositoryFileEntry> extRepositoryFileEntries =
			_extRepository.getEntries(
				ExtRepositoryModelType.FILE, repositoryMappedFolderId);

		List<ExtRepositoryFileEntryAdapter> entries =
			_toExtRepositoryEntryAdapters(
				ExtRepositoryModelAdapterType.FILE, extRepositoryFileEntries);

		return (List)_subList(entries, start, end, obc);
	}

	@Override
	public List<FileEntry> getFileEntries(
			long folderId, long fileEntryTypeId, int start, int end,
			OrderByComparator obc)
		throws PortalException, SystemException {

		if (fileEntryTypeId ==
				DLFileEntryTypeConstants.FILE_ENTRY_TYPE_ID_BASIC_DOCUMENT) {

			return getFileEntries(folderId, start, end, obc);
		}
		else {
			return Collections.emptyList();
		}
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List<FileEntry> getFileEntries(
			long folderId, String[] mimeTypes, int start, int end,
			OrderByComparator obc)
		throws PortalException, SystemException {

		String repositoryMappedFolderId = _getRepositoryEntryMappedId(folderId);

		List<ExtRepositoryFileEntry> extRepositoryFileEntries =
			_extRepository.getEntries(
				ExtRepositoryModelType.FILE, repositoryMappedFolderId);

		List<ExtRepositoryFileEntryAdapter> entries =
			_toExtRepositoryEntryAdapters(
				ExtRepositoryModelAdapterType.FILE, extRepositoryFileEntries);

		entries = _filterByMimeType(entries, mimeTypes);

		return (List)_subList(entries, start, end, obc);
	}

	@Override
	public int getFileEntriesCount(long folderId)
		throws PortalException, SystemException {

		String repositoryMappedFolderId = _getRepositoryEntryMappedId(folderId);

		return _extRepository.getEntriesCount(
			ExtRepositoryModelType.FILE, repositoryMappedFolderId);
	}

	@Override
	public int getFileEntriesCount(long folderId, long fileEntryTypeId)
		throws PortalException, SystemException {

		if (fileEntryTypeId ==
				DLFileEntryTypeConstants.FILE_ENTRY_TYPE_ID_BASIC_DOCUMENT) {

			String repositoryMappedFolderId = _getRepositoryEntryMappedId(
				folderId);

			return _extRepository.getEntriesCount(
				ExtRepositoryModelType.FILE, repositoryMappedFolderId);
		}
		else {
			return 0;
		}
	}

	@Override
	public int getFileEntriesCount(long folderId, String[] mimeTypes)
		throws PortalException, SystemException {

		String repositoryMappedFolderId = _getRepositoryEntryMappedId(folderId);

		return _extRepository.getEntriesCount(
			ExtRepositoryModelType.FILE, repositoryMappedFolderId);
	}

	@Override
	public ExtRepositoryFileEntryAdapter getFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		String repositoryMappedFileEntryId = _getRepositoryEntryMappedId(
			fileEntryId);

		ExtRepositoryEntry extRepositoryEntry = _extRepository.getEntry(
			ExtRepositoryModelType.FILE, repositoryMappedFileEntryId);

		return _toExtRepositoryEntryAdapter(
			ExtRepositoryModelAdapterType.FILE, extRepositoryEntry);
	}

	@Override
	public FileEntry getFileEntry(long folderId, String title)
		throws PortalException, SystemException {

		String repositoryMappedFolderId = _getRepositoryEntryMappedId(folderId);

		ExtRepositoryEntry extRepositoryEntry = _extRepository.getEntry(
			ExtRepositoryModelType.FILE, repositoryMappedFolderId, title);

		return _toExtRepositoryEntryAdapter(
			ExtRepositoryModelAdapterType.FILE, extRepositoryEntry);
	}

	@Override
	public FileEntry getFileEntryByUuid(String uuid)
		throws PortalException, SystemException {

		String repositoryMappedFileEntryId = _getRepositoryEntryMappedId(uuid);

		ExtRepositoryFileEntry extRepositoryFileEntry = _extRepository.getEntry(
			ExtRepositoryModelType.FILE, repositoryMappedFileEntryId);

		return _toExtRepositoryEntryAdapter(
			ExtRepositoryModelAdapterType.FILE, extRepositoryFileEntry);
	}

	@Override
	public FileVersion getFileVersion(long fileVersionId)
		throws PortalException, SystemException {

		String repositoryMappedFileVersionId = _getRepositoryEntryMappedId(
			fileVersionId);

		String[] parts = repositoryMappedFileVersionId.split("@");

		String fileEntryMappedId = parts[0];

		String versionTag = parts[1];

		ExtRepositoryFileEntry extRepositoryFileEntry =
			_extRepository.getEntry(
				ExtRepositoryModelType.FILE, fileEntryMappedId);

		ExtRepositoryFileVersion extRepositoryFileVersion =
			_extRepository.getFileVersion(extRepositoryFileEntry, versionTag);

		if (extRepositoryFileVersion == null) {
			return null;
		}

		ExtRepositoryFileEntryAdapter extRepositoryFileEntryAdapter =
			_toExtRepositoryEntryAdapter(
				ExtRepositoryModelAdapterType.FILE, extRepositoryFileEntry);

		return _toExtRepositoryFileVersionAdapter(
			extRepositoryFileEntryAdapter, extRepositoryFileVersion);
	}

	@Override
	public ExtRepositoryFolderAdapter getFolder(long folderId)
		throws PortalException, SystemException {

		String repositoryMappedFolderId = _getRepositoryEntryMappedId(folderId);

		ExtRepositoryEntry extRepositoryEntry = _extRepository.getEntry(
			ExtRepositoryModelType.FOLDER, repositoryMappedFolderId);

		return _toExtRepositoryEntryAdapter(
			ExtRepositoryModelAdapterType.FOLDER, extRepositoryEntry);
	}

	@Override
	public ExtRepositoryFolderAdapter getFolder(
			long parentFolderId, String title)
		throws PortalException, SystemException {

		String repositoryMappedParentFolderId = _getRepositoryEntryMappedId(
			parentFolderId);

		ExtRepositoryEntry folder = _extRepository.getEntry(
			ExtRepositoryModelType.FOLDER, repositoryMappedParentFolderId,
			title);

		return _toExtRepositoryEntryAdapter(
			ExtRepositoryModelAdapterType.FOLDER, folder);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List<Folder> getFolders(
			long parentFolderId, boolean includeMountFolders, int start,
			int end, OrderByComparator obc)
		throws PortalException, SystemException {

		String repositoryMappedParentFolderId = _getRepositoryEntryMappedId(
			parentFolderId);

		List<ExtRepositoryFolder> extRepositoryFolders =
			_extRepository.getEntries(
				ExtRepositoryModelType.FOLDER, repositoryMappedParentFolderId);

		List<ExtRepositoryFolderAdapter> entries =
			_toExtRepositoryEntryAdapters(
				ExtRepositoryModelAdapterType.FOLDER, extRepositoryFolders);

		return (List)_subList(entries, start, end, obc);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List getFoldersAndFileEntries(
			long folderId, int start, int end, OrderByComparator obc)
		throws SystemException {

		try {
			String repositoryMappedFolderId = _getRepositoryEntryMappedId(
				folderId);

			List<? extends ExtRepositoryEntry> extRepositoryEntries =
				_extRepository.getEntries(
					ExtRepositoryModelType.ENTRY, repositoryMappedFolderId);

			List<ExtRepositoryEntryAdapter<?>> entries =
				_toExtRepositoryEntryAdapters(
					ExtRepositoryModelAdapterType.ENTRY, extRepositoryEntries);

			return _subList(entries, start, end, obc);
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List<Object> getFoldersAndFileEntries(
			long folderId, String[] mimeTypes, int start, int end,
			OrderByComparator obc)
		throws PortalException, SystemException {

		String repositoryMappedFolderId = _getRepositoryEntryMappedId(folderId);

		List<ExtRepositoryEntry> extRepositoryEntries =
			_extRepository.getEntries(
				ExtRepositoryModelType.ENTRY, repositoryMappedFolderId);

		List<ExtRepositoryEntryAdapter<?>> entries =
			_toExtRepositoryEntryAdapters(
				ExtRepositoryModelAdapterType.ENTRY, extRepositoryEntries);

		entries = _filterByMimeType(entries, mimeTypes);

		return (List)_subList(entries, start, end, obc);
	}

	@Override
	public int getFoldersAndFileEntriesCount(long folderId)
		throws SystemException {

		try {
			String repositoryMappedFolderId = _getRepositoryEntryMappedId(
				folderId);

			return _extRepository.getEntriesCount(
				ExtRepositoryModelType.ENTRY, repositoryMappedFolderId);
		}
		catch (PortalException e) {
			throw new SystemException(e);
		}
	}

	@Override
	public int getFoldersAndFileEntriesCount(long folderId, String[] mimeTypes)
		throws PortalException, SystemException {

		List<Object> entries = getFoldersAndFileEntries(
			folderId, mimeTypes, 0, -1, null);

		return entries.size();
	}

	@Override
	public int getFoldersCount(long parentFolderId, boolean includeMountfolders)
		throws PortalException, SystemException {

		String repositoryMappedParentFolderId = _getRepositoryEntryMappedId(
			parentFolderId);

		return _extRepository.getEntriesCount(
			ExtRepositoryModelType.FOLDER, repositoryMappedParentFolderId);
	}

	@Override
	public int getFoldersFileEntriesCount(List<Long> folderIds, int status)
		throws PortalException, SystemException {

		int count = 0;

		for (long folderId : folderIds) {
			String repositoryMappedFolderId = _getRepositoryEntryMappedId(
				folderId);

			count += _extRepository.getEntriesCount(
				ExtRepositoryModelType.ENTRY, repositoryMappedFolderId);
		}

		return count;
	}

	public String getLiferayUserId(String repositoryUserName) {
		return _extRepository.getLiferayUserId(repositoryUserName);
	}

	@Override
	@SuppressWarnings("unused")
	public List<Folder> getMountFolders(
			long parentFolderId, int start, int end, OrderByComparator obc)
		throws PortalException, SystemException {

		return Collections.emptyList();
	}

	@Override
	@SuppressWarnings("unused")
	public int getMountFoldersCount(long parentFolderId)
		throws PortalException, SystemException {

		return 0;
	}

	public ExtRepositoryFolderAdapter getParentFolder(
			ExtRepositoryEntryAdapter<?> extRepositoryEntryAdapter)
		throws PortalException, SystemException {

		ExtRepositoryFolder parentFolder = _extRepository.getParentFolder(
			extRepositoryEntryAdapter.getExtRepositoryModel());

		return _toExtRepositoryEntryAdapter(
			ExtRepositoryModelAdapterType.FOLDER, parentFolder);
	}

	public String getRootFolderId() throws PortalException, SystemException {
		return _extRepository.getRootFolderId();
	}

	@Override
	public void getSubfolderIds(List<Long> folderIds, long folderId)
		throws PortalException, SystemException {

		folderIds.addAll(getSubfolderIds(folderId, true));
	}

	@Override
	public List<Long> getSubfolderIds(long folderId, boolean recurse)
		throws PortalException, SystemException {

		String repositoryMappedFolderId = _getRepositoryEntryMappedId(folderId);

		List<String> extRepositoryEntryIds = _extRepository.getSubfolderIds(
			repositoryMappedFolderId, recurse);

		List<Long> subfolderIds = new ArrayList<Long>();

		for (String extRepositoryEntryId : extRepositoryEntryIds) {
			Object[] ids = getRepositoryEntryIds(extRepositoryEntryId);

			long repositoryEntryId = (Long)ids[0];

			subfolderIds.add(repositoryEntryId);
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

	public List<ExtRepositoryFileVersionAdapter> getVersions(
			ExtRepositoryFileEntryAdapter extRepositoryFileEntryAdapter)
		throws SystemException {

		List<ExtRepositoryFileVersion> extRepositoryFileVersions =
			_extRepository.getFileVersions(
				extRepositoryFileEntryAdapter.getExtRepositoryModel());

		return _toExtRepositoryFileVersionAdapters(
			extRepositoryFileEntryAdapter, extRepositoryFileVersions);
	}

	@Override
	public void initRepository() throws PortalException, SystemException {
		try {
			CredentialsProvider credentialsProvider =
				new CredentialsProvider() {
					@Override
					public String getUser() {
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
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Cannot initialize repository: " + _extRepository, pe);
			}

			throw pe;
		}
		catch (SystemException se) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Cannot initialize repository: " + _extRepository, se);
			}

			throw se;
		}
	}

	@Override
	@SuppressWarnings("unused")
	public Lock lockFolder(long folderId)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException(
			"Folder locks are not supported");
	}

	@Override
	@SuppressWarnings("unused")
	public Lock lockFolder(
			long folderId, String owner, boolean inheritable,
			long expirationTime)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException(
			"Folder locks are not supported");
	}

	@Override
	public FileEntry moveFileEntry(
			long fileEntryId, long newFolderId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		String repositoryMappedFileEntryId = _getRepositoryEntryMappedId(
			fileEntryId);
		String repositoryMappedNewFolderId = _getRepositoryEntryMappedId(
			newFolderId);

		ExtRepositoryFileEntry extRepositoryFileEntry =
			_extRepository.getEntry(
				ExtRepositoryModelType.FILE, repositoryMappedFileEntryId);

		ExtRepositoryFileEntry moveExtRepositoryFileEntry =
			_extRepository.moveEntry(
				ExtRepositoryModelType.FILE, repositoryMappedFileEntryId,
				repositoryMappedNewFolderId, extRepositoryFileEntry.getTitle());

		return _toExtRepositoryEntryAdapter(
			ExtRepositoryModelAdapterType.FILE, moveExtRepositoryFileEntry);
	}

	@Override
	public ExtRepositoryFolderAdapter moveFolder(
			long folderId, long newParentFolderId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		String repositoryMappedFolderId = _getRepositoryEntryMappedId(folderId);

		String repositoryMappedNewParentFolderId = _getRepositoryEntryMappedId(
			newParentFolderId);

		ExtRepositoryFolder extRepositoryFolder =
			_extRepository.getEntry(
				ExtRepositoryModelType.FOLDER, repositoryMappedFolderId);

		ExtRepositoryFolder moveExtRepositoryFolder = _extRepository.moveEntry(
			ExtRepositoryModelType.FOLDER, repositoryMappedFolderId,
			repositoryMappedNewParentFolderId, extRepositoryFolder.getName());

		return _toExtRepositoryEntryAdapter(
			ExtRepositoryModelAdapterType.FOLDER, moveExtRepositoryFolder);
	}

	@Override
	@SuppressWarnings("unused")
	public Lock refreshFileEntryLock(
			String lockUuid, long companyId, long expirationTime)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException(
			"Lock refresh is not supported");
	}

	@Override
	@SuppressWarnings("unused")
	public Lock refreshFolderLock(
			String lockUuid, long companyId, long expirationTime)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException(
			"Folder locks are not supported");
	}

	@Override
	public void revertFileEntry(
			long fileEntryId, String version, ServiceContext serviceContext)
		throws PortalException, SystemException {

		String repositoryMappedFileEntryId = _getRepositoryEntryMappedId(
			fileEntryId);

		ExtRepositoryFileEntry extRepositoryFileEntry = _extRepository.getEntry(
			ExtRepositoryModelType.FILE, repositoryMappedFileEntryId);

		ExtRepositoryFileVersion extRepositoryFileVersion = null;

		List<ExtRepositoryFileVersion> extRepositoryFileVersions =
			_extRepository.getFileVersions(extRepositoryFileEntry);

		for (ExtRepositoryFileVersion curExtRepositoryFileVersion :
				extRepositoryFileVersions) {

			String tag = curExtRepositoryFileVersion.getVersion();

			if (tag.equals(version)) {
				extRepositoryFileVersion = curExtRepositoryFileVersion;

				break;
			}
		}

		if (extRepositoryFileVersion != null) {
			InputStream is = _extRepository.getContentStream(
				extRepositoryFileVersion);

			_extRepository.checkOutFileEntry(repositoryMappedFileEntryId);

			_extRepository.updateFileEntry(repositoryMappedFileEntryId, is);

			String changeLog = "Reverted to " + version;

			_extRepository.checkInFileEntry(
				repositoryMappedFileEntryId, true, changeLog);
		}
		else {
			throw new NoSuchFileVersionException(
				extRepositoryFileEntry.getTitle() + "@" + version);
		}
	}

	@Override
	@SuppressWarnings("unused")
	public Hits search(long creatorUserId, int status, int start, int end)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException(
			"Search by creator and status is not supported");
	}

	@Override
	@SuppressWarnings("unused")
	public Hits search(
			long creatorUserId, long folderId, String[] mimeTypes, int status,
			int start, int end)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException(
			"Search by creator, folder, mime type and status is not supported");
	}

	@Override
	public Hits search(SearchContext searchContext) throws SearchException {
		long startTime = System.currentTimeMillis();

		List<String> folderMappedIds = null;

		try {
			long[] folderIds = searchContext.getFolderIds();

			if ((folderIds != null) && (folderIds.length > 0)) {
				folderMappedIds = new ArrayList<String>();

				for (long folderId : folderIds) {
					String repositoryMappedFolderId =
						_getRepositoryEntryMappedId(folderId);

					folderMappedIds.add(repositoryMappedFolderId);
				}
			}
		}
		catch (SystemException se) {
			throw new SearchException("Cannot perform search", se);
		}
		catch (PortalException pe) {
			throw new SearchException("Cannot perform search", pe);
		}

		List<ExtRepositorySearchResult<?>> extRepositorySearchResults = null;

		try {
			extRepositorySearchResults = _extRepository.search(
				folderMappedIds, searchContext.getKeywords(),
				searchContext.getQueryConfig(), searchContext.getStart(),
				searchContext.getEnd());
		}
		catch (PortalException pe) {
			throw new SearchException("Error performing search", pe);
		}
		catch (SystemException e) {
			throw new SearchException("Error performing search", e);
		}

		QueryConfig queryConfig = searchContext.getQueryConfig();

		List<Document> documents = new ArrayList<Document>();
		List<String> snippets = new ArrayList<String>();
		List<Float> scores = new ArrayList<Float>();

		int total = 0;

		for (ExtRepositorySearchResult<?> extRepositorySearchResult :
				extRepositorySearchResults) {

			try {
				ExtRepositoryEntryAdapter<?> extRepositoryEntryAdapter =
					_toExtRepositoryEntryAdapter(
						ExtRepositoryModelAdapterType.ENTRY,
						extRepositorySearchResult.getEntry());

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
			catch (SystemException se) {
				if (_log.isWarnEnabled()) {
					_log.warn("Invalid entry returned from search", se);
				}
			}
			catch (PortalException pe) {
				if (_log.isWarnEnabled()) {
					_log.warn("Invalid entry returned from search", pe);
				}
			}
		}

		float searchTime =
			(float)(System.currentTimeMillis() - startTime) / Time.SECOND;

		Hits hits = new HitsImpl();

		hits.setDocs(documents.toArray(new Document[documents.size()]));
		hits.setLength(total);
		hits.setQueryTerms(new String[0]);
		hits.setScores(scores.toArray(new Float[scores.size()]));
		hits.setSearchTime(searchTime);
		hits.setSnippets(snippets.toArray(new String[snippets.size()]));
		hits.setStart(startTime);

		return hits;
	}

	@Override
	@SuppressWarnings("unused")
	public Hits search(SearchContext searchContext, Query query)
		throws SearchException {

		throw new UnsupportedOperationException(
			"Search by context and query is not supported");
	}

	@Override
	@SuppressWarnings("unused")
	public void unlockFolder(long folderId, String lockUuid)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException(
			"Folder locking is not supported");
	}

	@Override
	public FileEntry updateFileEntry(
			long fileEntryId, String sourceFileName, String mimeType,
			String title, String description, String changeLog,
			boolean majorVersion, InputStream is, long size,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		String repositoryMappedFileEntryId = _getRepositoryEntryMappedId(
			fileEntryId);

		ExtRepositoryFileEntry extRepositoryFileEntry =
			_extRepository.updateFileEntry(repositoryMappedFileEntryId, is);

		_extRepository.checkInFileEntry(
			repositoryMappedFileEntryId, majorVersion, changeLog);

		ExtRepositoryFolder folder = _extRepository.getParentFolder(
			extRepositoryFileEntry);

		if (!title.equals(extRepositoryFileEntry.getTitle())) {
			_extRepository.moveEntry(
				ExtRepositoryModelType.FILE, repositoryMappedFileEntryId,
				folder.getId(), title);
		}

		return _toExtRepositoryEntryAdapter(
			ExtRepositoryModelAdapterType.FILE, extRepositoryFileEntry);
	}

	@Override
	public ExtRepositoryFolderAdapter updateFolder(
			long folderId, String title, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		String repositoryMappedFolderId = _getRepositoryEntryMappedId(folderId);

		ExtRepositoryFolder extRepositoryFolder = _extRepository.getEntry(
			ExtRepositoryModelType.FOLDER, repositoryMappedFolderId);

		ExtRepositoryFolder folder = _extRepository.getParentFolder(
			extRepositoryFolder);

		ExtRepositoryFolder moveExtRepositoryFolder = _extRepository.moveEntry(
			ExtRepositoryModelType.FOLDER, repositoryMappedFolderId,
			folder.getId(), title);

		return _toExtRepositoryEntryAdapter(
			ExtRepositoryModelAdapterType.FOLDER, moveExtRepositoryFolder);
	}

	@Override
	@SuppressWarnings("unused")
	public boolean verifyFileEntryCheckOut(long fileEntryId, String lockUuid)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException(
			"Verification of file check outs is not supported");
	}

	@Override
	@SuppressWarnings("unused")
	public boolean verifyInheritableLock(long folderId, String lockUuid)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException(
			"Inheritable locks are not supported");
	}

	protected ExtRepositoryAdapter(ExtRepository extRepository) {
		_extRepository = extRepository;
	}

	private <T extends ExtRepositoryEntryAdapter<?>> List<T>
		_filterByMimeType(List<T> entries, String[] mimeTypes) {

		if ( mimeTypes == null ) {
			return entries;
		}
		else {
			Set<String> allowedMimeTypes = new HashSet<String>(
				Arrays.asList(mimeTypes));

			List<T> filtered = new ArrayList<T>();

			for (T entry : entries) {
				if (entry instanceof ExtRepositoryFileEntryAdapter) {
					ExtRepositoryFileEntryAdapter fileEntry =
						(ExtRepositoryFileEntryAdapter)entry;

					if (allowedMimeTypes.contains(fileEntry.getMimeType())) {
						filtered.add(entry);
					}
				}
			}

			return filtered;
		}
	}

	private void _forceGetVersions(
			ExtRepositoryFileEntryAdapter extRepositoryFileEntryAdapter)
		throws SystemException {

		extRepositoryFileEntryAdapter.getFileVersions(
			WorkflowConstants.STATUS_ANY);
	}

	private String _getLogin() {
		String login = PrincipalThreadLocal.getName();

		if (Validator.isNull(login)) {
			return login;
		}

		try {
			Company company = companyLocalService.getCompany(getCompanyId());

			String authType = company.getAuthType();

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
		catch (PortalException e) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Cannot obtain user name to connect to ext repository: " +
						_extRepository,
					e);
			}

			login = null;
		}
		catch (SystemException e) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Cannot obtain user name to connect to ext repository: " +
						_extRepository,
					e);
			}

			login = null;
		}

		return login;
	}

	private String _getPassword() {
		return PrincipalThreadLocal.getPassword();
	}

	private String _getRepositoryEntryMappedId(long entryId)
		throws PortalException, SystemException {

		RepositoryEntry repositoryEntry = RepositoryEntryUtil.fetchByPrimaryKey(
			entryId);

		if (repositoryEntry == null) {
			throw new NoSuchRepositoryEntryException(
				"No repository entry exists with {entryId=" + entryId + "}");
		}

		return repositoryEntry.getMappedId();
	}

	private String _getRepositoryEntryMappedId(String entryUuid)
		throws PortalException, SystemException {

		RepositoryEntry repositoryEntry = RepositoryEntryUtil.fetchByUUID_G(
			entryUuid, getGroupId(), true);

		if (repositoryEntry != null) {
			throw new NoSuchRepositoryEntryException(
				"No repository entry exits with {Uuid='" + entryUuid + "}");
		}

		return repositoryEntry.getMappedId();
	}

	@SuppressWarnings("unchecked")
	private <T extends ExtRepositoryEntryAdapter<?>> List<T> _subList(
		List<T> list, int start, int end, OrderByComparator obc) {

		list = ListUtil.sort(list, obc);

		return ListUtil.subList(list, start, end);
	}

	@SuppressWarnings("unchecked")
	private <T extends ExtRepositoryEntryAdapter<?>> T
			_toExtRepositoryEntryAdapter(
				ExtRepositoryModelAdapterType<T> entryType,
				ExtRepositoryEntry extRepositoryEntry)
		throws PortalException, SystemException {

		Object[] ids = getRepositoryEntryIds(extRepositoryEntry.getId());

		long repositoryEntryId = (Long)ids[0];

		String uuid = (String)ids[1];

		ExtRepositoryEntryAdapter<?> extRepositoryEntryAdapter = null;

		if (extRepositoryEntryAdapter == null) {
			if (extRepositoryEntry instanceof ExtRepositoryFolder) {
				ExtRepositoryFolder extRepositoryFolder =
					(ExtRepositoryFolder)extRepositoryEntry;

				extRepositoryEntryAdapter = new ExtRepositoryFolderAdapter(
					this, repositoryEntryId, uuid, extRepositoryFolder);
			}
			else {
				ExtRepositoryFileEntry extRepositoryFileEntry =
					(ExtRepositoryFileEntry)extRepositoryEntry;

				extRepositoryEntryAdapter = new ExtRepositoryFileEntryAdapter(
					this, repositoryEntryId, uuid, extRepositoryFileEntry);

				_forceGetVersions(
					(ExtRepositoryFileEntryAdapter)extRepositoryEntryAdapter);
			}
		}

		if (entryType == ExtRepositoryModelAdapterType.FILE) {
			if (!(extRepositoryEntryAdapter instanceof
					ExtRepositoryFileEntryAdapter)) {

				throw new NoSuchFileEntryException(
					"Ext repository entry is not a file: " +
						extRepositoryEntry);
			}
		}
		else if (entryType == ExtRepositoryModelAdapterType.FOLDER) {
			if (!(extRepositoryEntryAdapter instanceof
					ExtRepositoryFolderAdapter)) {

				throw new NoSuchFolderException(
					"Ext repository entry is not a folder: " +
						extRepositoryEntry);
			}
		}
		else if (entryType != ExtRepositoryModelAdapterType.ENTRY) {
			throw new IllegalArgumentException(
				"Unsupported repository entry type: " + entryType);
		}

		return (T)extRepositoryEntryAdapter;
	}

	private <T extends ExtRepositoryEntryAdapter<?>> List<T>
			_toExtRepositoryEntryAdapters(
				ExtRepositoryModelAdapterType<T> entryType,
				List<? extends ExtRepositoryEntry> extRepositoryEntries)
		throws PortalException, SystemException {

		List<T> entries = new ArrayList<T>();

		for (ExtRepositoryEntry extRepositoryEntry : extRepositoryEntries) {
			entries.add(
				_toExtRepositoryEntryAdapter(entryType, extRepositoryEntry));
		}

		return entries;
	}

	private ExtRepositoryFileVersionAdapter _toExtRepositoryFileVersionAdapter(
			ExtRepositoryFileEntryAdapter extRepositoryFileEntryAdapter,
			ExtRepositoryFileVersion extRepositoryFileVersion)
		throws SystemException {

		Object[] ids = getRepositoryEntryIds(extRepositoryFileVersion.getId());

		long repositoryEntryId = (Long)ids[0];

		String uuid = (String)ids[1];

		return new ExtRepositoryFileVersionAdapter(
			this, repositoryEntryId, uuid, extRepositoryFileEntryAdapter,
			extRepositoryFileVersion);
	}

	private List<ExtRepositoryFileVersionAdapter>
		_toExtRepositoryFileVersionAdapters(
			ExtRepositoryFileEntryAdapter extRepositoryFileEntryAdapter,
			List<ExtRepositoryFileVersion> extRepositoryFileVersions)
		throws SystemException {

		List<ExtRepositoryFileVersionAdapter> versions =
			new ArrayList<ExtRepositoryFileVersionAdapter>();

		for (ExtRepositoryFileVersion extRepositoryFileVersion :
				extRepositoryFileVersions) {

			ExtRepositoryFileVersionAdapter extRepositoryFileVersionAdapter =
				_toExtRepositoryFileVersionAdapter(
					extRepositoryFileEntryAdapter, extRepositoryFileVersion);

			versions.add(extRepositoryFileVersionAdapter);
		}

		return versions;
	}

	private static Log _log = LogFactoryUtil.getLog(ExtRepositoryAdapter.class);

	private ExtRepository _extRepository;

}