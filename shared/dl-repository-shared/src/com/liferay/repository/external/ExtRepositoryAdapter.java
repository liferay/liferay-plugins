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
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.BaseRepositoryImpl;
import com.liferay.portal.kernel.repository.RepositoryException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Lock;
import com.liferay.portal.model.RepositoryEntry;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.service.RepositoryEntryLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.persistence.RepositoryEntryUtil;
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

		ExtRepositoryFileEntry extRepositoryFileEntry = _extRepository.addFile(
			repositoryMappedFolderId, mimeType, fileName, description,
			changeLog, is);

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FILE, extRepositoryFileEntry);
	}

	@Override
	public ExtRepositoryFolderAdapter addFolder(
			long parentFolderId, String title, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		String repositoryMappedParentFolderId = _getRepositoryEntryMappedId(
			parentFolderId);

		ExtRepositoryFolder extRepositoryFolder = _extRepository.addFolder(
			repositoryMappedParentFolderId, title, description);

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FOLDER, extRepositoryFolder);
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

		_extRepository.checkInFile(
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
			_extRepository.checkOutFile(repositoryMappedFileEntryId);

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FILE, extRepositoryFileEntry);
	}

	@Override
	@SuppressWarnings("unused")
	public FileEntry checkOutFileEntry(
			long fileEntryId, String owner, long expirationTime,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
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
			_extRepository.getExtRepositoryObject(
				ExtRepositoryObjectType.FILE, repositoryMappedFileEntryId);

		ExtRepositoryFileEntry copyExtRepositoryFileEntry =
			_extRepository.copyExtRepositoryObject(
				ExtRepositoryObjectType.FILE, repositoryMappedFileEntryId,
				repositoryMappedDestFolderId,
				extRepositoryFileEntry.getTitle());

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FILE, copyExtRepositoryFileEntry);
	}

	@Override
	public void deleteFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		String repositoryMappedFileEntryId = _getRepositoryEntryMappedId(
			fileEntryId);

		_extRepository.deleteExtRepositoryObject(
			ExtRepositoryObjectType.FILE, repositoryMappedFileEntryId);
	}

	@Override
	public void deleteFolder(long folderId)
		throws PortalException, SystemException {

		String repositoryMappedFolderId = _getRepositoryEntryMappedId(folderId);

		_extRepository.deleteExtRepositoryObject(
			ExtRepositoryObjectType.FOLDER, repositoryMappedFolderId);
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
			_extRepository.getExtRepositoryObjects(
				ExtRepositoryObjectType.FILE, repositoryMappedFolderId);

		List<ExtRepositoryFileEntryAdapter> fileEntries =
			_toExtRepositoryObjectAdapters(
				ExtRepositoryObjectAdapterType.FILE, extRepositoryFileEntries);

		return (List)_subList(fileEntries, start, end, obc);
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
			_extRepository.getExtRepositoryObjects(
				ExtRepositoryObjectType.FILE, repositoryMappedFolderId);

		List<ExtRepositoryFileEntryAdapter> fileEntries =
			_toExtRepositoryObjectAdapters(
				ExtRepositoryObjectAdapterType.FILE, extRepositoryFileEntries);

		fileEntries = _filterByMimeType(fileEntries, mimeTypes);

		return (List)_subList(fileEntries, start, end, obc);
	}

	@Override
	public int getFileEntriesCount(long folderId)
		throws PortalException, SystemException {

		String repositoryMappedFolderId = _getRepositoryEntryMappedId(folderId);

		return _extRepository.getExtRepositoryObjectsCount(
			ExtRepositoryObjectType.FILE, repositoryMappedFolderId);
	}

	@Override
	public int getFileEntriesCount(long folderId, long fileEntryTypeId)
		throws PortalException, SystemException {

		if (fileEntryTypeId ==
				DLFileEntryTypeConstants.FILE_ENTRY_TYPE_ID_BASIC_DOCUMENT) {

			String repositoryMappedFolderId = _getRepositoryEntryMappedId(
				folderId);

			return _extRepository.getExtRepositoryObjectsCount(
				ExtRepositoryObjectType.FILE, repositoryMappedFolderId);
		}
		else {
			return 0;
		}
	}

	@Override
	public int getFileEntriesCount(long folderId, String[] mimeTypes)
		throws PortalException, SystemException {

		String repositoryMappedFolderId = _getRepositoryEntryMappedId(folderId);

		return _extRepository.getExtRepositoryObjectsCount(
			ExtRepositoryObjectType.FILE, repositoryMappedFolderId);
	}

	@Override
	public ExtRepositoryFileEntryAdapter getFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		String repositoryMappedFileEntryId = _getRepositoryEntryMappedId(
			fileEntryId);

		ExtRepositoryObject extRepositoryObject =
			_extRepository.getExtRepositoryObject(
				ExtRepositoryObjectType.FILE, repositoryMappedFileEntryId);

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FILE, extRepositoryObject);
	}

	@Override
	public FileEntry getFileEntry(long folderId, String title)
		throws PortalException, SystemException {

		String repositoryMappedFolderId = _getRepositoryEntryMappedId(folderId);

		ExtRepositoryObject extRepositoryObject =
			_extRepository.getExtRepositoryObject(
				ExtRepositoryObjectType.FILE, repositoryMappedFolderId, title);

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FILE, extRepositoryObject);
	}

	@Override
	public FileEntry getFileEntryByUuid(String uuid)
		throws PortalException, SystemException {

		String repositoryMappedFileEntryId = _getRepositoryEntryMappedId(uuid);

		ExtRepositoryFileEntry extRepositoryFileEntry =
			_extRepository.getExtRepositoryObject(
				ExtRepositoryObjectType.FILE, repositoryMappedFileEntryId);

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FILE, extRepositoryFileEntry);
	}

	@Override
	public FileVersion getFileVersion(long fileVersionId)
		throws PortalException, SystemException {

		String repositoryMappedFileVersionId = _getRepositoryEntryMappedId(
			fileVersionId);

		ExtRepositoryFileVersionDescriptor extRepositoryFileVersionDescriptor =
			_extRepository.getFileVersionDescriptor(
				repositoryMappedFileVersionId);

		String fileId = extRepositoryFileVersionDescriptor.getFileId();

		String version = extRepositoryFileVersionDescriptor.getVersion();

		ExtRepositoryFileEntry extRepositoryFileEntry =
			_extRepository.getExtRepositoryObject(
				ExtRepositoryObjectType.FILE, fileId);

		ExtRepositoryFileVersion extRepositoryFileVersion =
			_extRepository.getFileVersion(extRepositoryFileEntry, version);

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
		throws PortalException, SystemException {

		String repositoryMappedFolderId = _getRepositoryEntryMappedId(folderId);

		ExtRepositoryFolder extRepositoryFolder =
			_extRepository.getExtRepositoryObject(
				ExtRepositoryObjectType.FOLDER, repositoryMappedFolderId);

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FOLDER, extRepositoryFolder);
	}

	@Override
	public ExtRepositoryFolderAdapter getFolder(
			long parentFolderId, String title)
		throws PortalException, SystemException {

		String repositoryMappedParentFolderId = _getRepositoryEntryMappedId(
			parentFolderId);

		ExtRepositoryFolder extRepositoryFolder =
			_extRepository.getExtRepositoryObject(
				ExtRepositoryObjectType.FOLDER, repositoryMappedParentFolderId,
				title);

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FOLDER, extRepositoryFolder);
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
			_extRepository.getExtRepositoryObjects(
				ExtRepositoryObjectType.FOLDER, repositoryMappedParentFolderId);

		List<ExtRepositoryFolderAdapter> folders =
			_toExtRepositoryObjectAdapters(
				ExtRepositoryObjectAdapterType.FOLDER, extRepositoryFolders);

		return (List)_subList(folders, start, end, obc);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List getFoldersAndFileEntries(
			long folderId, int start, int end, OrderByComparator obc)
		throws SystemException {

		try {
			String repositoryMappedFolderId = _getRepositoryEntryMappedId(
				folderId);

			List<? extends ExtRepositoryObject> extRepositoryObjects =
				_extRepository.getExtRepositoryObjects(
					ExtRepositoryObjectType.ENTRY, repositoryMappedFolderId);

			List<ExtRepositoryObjectAdapter<?>> entries =
				_toExtRepositoryObjectAdapters(
					ExtRepositoryObjectAdapterType.OBJECT,
					extRepositoryObjects);

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

		List<ExtRepositoryObject> extRepositoryObjects =
			_extRepository.getExtRepositoryObjects(
				ExtRepositoryObjectType.ENTRY, repositoryMappedFolderId);

		List<ExtRepositoryObjectAdapter<?>> extRepositoryObjectAdapters =
			_toExtRepositoryObjectAdapters(
				ExtRepositoryObjectAdapterType.OBJECT, extRepositoryObjects);

		extRepositoryObjectAdapters = _filterByMimeType(
			extRepositoryObjectAdapters, mimeTypes);

		return (List)_subList(extRepositoryObjectAdapters, start, end, obc);
	}

	@Override
	public int getFoldersAndFileEntriesCount(long folderId)
		throws SystemException {

		try {
			String repositoryMappedFolderId = _getRepositoryEntryMappedId(
				folderId);

			return _extRepository.getExtRepositoryObjectsCount(
				ExtRepositoryObjectType.ENTRY, repositoryMappedFolderId);
		}
		catch (PortalException e) {
			throw new SystemException(e);
		}
	}

	@Override
	public int getFoldersAndFileEntriesCount(long folderId, String[] mimeTypes)
		throws PortalException, SystemException {

		List<Object> entries = getFoldersAndFileEntries(
			folderId, mimeTypes, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		return entries.size();
	}

	@Override
	public int getFoldersCount(long parentFolderId, boolean includeMountfolders)
		throws PortalException, SystemException {

		String repositoryMappedParentFolderId = _getRepositoryEntryMappedId(
			parentFolderId);

		return _extRepository.getExtRepositoryObjectsCount(
			ExtRepositoryObjectType.FOLDER, repositoryMappedParentFolderId);
	}

	@Override
	public int getFoldersFileEntriesCount(List<Long> folderIds, int status)
		throws PortalException, SystemException {

		int count = 0;

		for (long folderId : folderIds) {
			String repositoryMappedFolderId = _getRepositoryEntryMappedId(
				folderId);

			count += _extRepository.getExtRepositoryObjectsCount(
				ExtRepositoryObjectType.ENTRY, repositoryMappedFolderId);
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
			ExtRepositoryObjectAdapter<?> extRepositoryObjectAdapter)
		throws PortalException, SystemException {

		ExtRepositoryFolder parentFolder = _extRepository.getParentFolder(
			extRepositoryObjectAdapter.getExtRepositoryModel());

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FOLDER, parentFolder);
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

		List<String> extRepositoryObjectIds = _extRepository.getSubfolderIds(
			repositoryMappedFolderId, recurse);

		List<Long> subfolderIds = new ArrayList<Long>();

		for (String extRepositoryObjectId : extRepositoryObjectIds) {
			Object[] ids = getRepositoryEntryIds(extRepositoryObjectId);

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
					"Unable to initialize repository " + _extRepository, pe);
			}

			throw pe;
		}
		catch (SystemException se) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to initialize repository " + _extRepository, se);
			}

			throw se;
		}
	}

	@Override
	@SuppressWarnings("unused")
	public Lock lockFolder(long folderId)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	@SuppressWarnings("unused")
	public Lock lockFolder(
			long folderId, String owner, boolean inheritable,
			long expirationTime)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
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
			_extRepository.getExtRepositoryObject(
				ExtRepositoryObjectType.FILE, repositoryMappedFileEntryId);

		ExtRepositoryFileEntry moveExtRepositoryFileEntry =
			_extRepository.moveExtRepositoryObject(
				ExtRepositoryObjectType.FILE, repositoryMappedFileEntryId,
				repositoryMappedNewFolderId, extRepositoryFileEntry.getTitle());

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FILE, moveExtRepositoryFileEntry);
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
			_extRepository.getExtRepositoryObject(
				ExtRepositoryObjectType.FOLDER, repositoryMappedFolderId);

		ExtRepositoryFolder moveExtRepositoryFolder =
			_extRepository.moveExtRepositoryObject(
				ExtRepositoryObjectType.FOLDER, repositoryMappedFolderId,
				repositoryMappedNewParentFolderId,
				extRepositoryFolder.getName());

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FOLDER, moveExtRepositoryFolder);
	}

	@Override
	@SuppressWarnings("unused")
	public Lock refreshFileEntryLock(
			String lockUuid, long companyId, long expirationTime)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	@SuppressWarnings("unused")
	public Lock refreshFolderLock(
			String lockUuid, long companyId, long expirationTime)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void revertFileEntry(
			long fileEntryId, String version, ServiceContext serviceContext)
		throws PortalException, SystemException {

		String repositoryMappedFileEntryId = _getRepositoryEntryMappedId(
			fileEntryId);

		ExtRepositoryFileEntry extRepositoryFileEntry =
			_extRepository.getExtRepositoryObject(
				ExtRepositoryObjectType.FILE, repositoryMappedFileEntryId);

		ExtRepositoryFileVersion extRepositoryFileVersion = null;

		List<ExtRepositoryFileVersion> extRepositoryFileVersions =
			_extRepository.getFileVersions(extRepositoryFileEntry);

		for (ExtRepositoryFileVersion curExtRepositoryFileVersion :
				extRepositoryFileVersions) {

			String curVersion = curExtRepositoryFileVersion.getVersion();

			if (curVersion.equals(version)) {
				extRepositoryFileVersion = curExtRepositoryFileVersion;

				break;
			}
		}

		if (extRepositoryFileVersion != null) {
			InputStream is = _extRepository.getContentStream(
				extRepositoryFileVersion);

			_extRepository.checkOutFile(repositoryMappedFileEntryId);

			_extRepository.updateFile(
				repositoryMappedFileEntryId,
				extRepositoryFileVersion.getMimeType(), is);

			String changeLog = "Reverted to " + version;

			_extRepository.checkInFile(
				repositoryMappedFileEntryId, true, changeLog);
		}
		else {
			throw new NoSuchFileVersionException(
				"No file version with {id=" + extRepositoryFileEntry.getId() +
					", version: " + version + "}");
		}
	}

	@Override
	@SuppressWarnings("unused")
	public Hits search(long creatorUserId, int status, int start, int end)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	@SuppressWarnings("unused")
	public Hits search(
			long creatorUserId, long folderId, String[] mimeTypes, int status,
			int start, int end)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	@SuppressWarnings("unused")
	public Hits search(SearchContext searchContext) throws SearchException {
		throw new UnsupportedOperationException();
	}

	@Override
	@SuppressWarnings("unused")
	public Hits search(SearchContext searchContext, Query query)
		throws SearchException {

		throw new UnsupportedOperationException();
	}

	@Override
	@SuppressWarnings("unused")
	public void unlockFolder(long folderId, String lockUuid)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
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
			_extRepository.updateFile(
				repositoryMappedFileEntryId, mimeType, is);

		_extRepository.checkInFile(
			repositoryMappedFileEntryId, majorVersion, changeLog);

		ExtRepositoryFolder folder = _extRepository.getParentFolder(
			extRepositoryFileEntry);

		if (!title.equals(extRepositoryFileEntry.getTitle())) {
			_extRepository.moveExtRepositoryObject(
				ExtRepositoryObjectType.FILE, repositoryMappedFileEntryId,
				folder.getId(), title);
		}

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FILE, extRepositoryFileEntry);
	}

	@Override
	public ExtRepositoryFolderAdapter updateFolder(
			long folderId, String title, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		String repositoryMappedFolderId = _getRepositoryEntryMappedId(folderId);

		ExtRepositoryFolder extRepositoryFolder =
			_extRepository.getExtRepositoryObject(
				ExtRepositoryObjectType.FOLDER, repositoryMappedFolderId);

		ExtRepositoryFolder folder = _extRepository.getParentFolder(
			extRepositoryFolder);

		ExtRepositoryFolder moveExtRepositoryFolder =
			_extRepository.moveExtRepositoryObject(
				ExtRepositoryObjectType.FOLDER, repositoryMappedFolderId,
				folder.getId(), title);

		return _toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType.FOLDER, moveExtRepositoryFolder);
	}

	@Override
	@SuppressWarnings("unused")
	public boolean verifyFileEntryCheckOut(long fileEntryId, String lockUuid)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	@SuppressWarnings("unused")
	public boolean verifyInheritableLock(long folderId, String lockUuid)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	protected ExtRepositoryAdapter(ExtRepository extRepository) {
		_extRepository = extRepository;
	}

	private <T extends ExtRepositoryObjectAdapter<?>> List<T> _filterByMimeType(
		List<T> extRepositoryObjects, String[] mimeTypes) {

		if (mimeTypes == null) {
			return extRepositoryObjects;
		}
		else {
			Set<String> allowedMimeTypes = new HashSet<String>(
				Arrays.asList(mimeTypes));

			List<T> filtered = new ArrayList<T>();

			for (T extRepositoryObject : extRepositoryObjects) {
				if (extRepositoryObject instanceof
						ExtRepositoryFileEntryAdapter) {

					ExtRepositoryFileEntryAdapter fileEntry =
						(ExtRepositoryFileEntryAdapter)extRepositoryObject;

					if (allowedMimeTypes.contains(fileEntry.getMimeType())) {
						filtered.add(extRepositoryObject);
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
					"Unable to get login to connect to external repository " +
						_extRepository,
					e);
			}

			login = null;
		}
		catch (SystemException e) {
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
		return PrincipalThreadLocal.getPassword();
	}

	private String _getRepositoryEntryMappedId(long repositoryEntryId)
		throws PortalException, SystemException {

		RepositoryEntry repositoryEntry = RepositoryEntryUtil.fetchByPrimaryKey(
			repositoryEntryId);

		if (repositoryEntry != null) {
			return repositoryEntry.getMappedId();
		}

		DLFolder rootMountFolder = DLFolderLocalServiceUtil.getDLFolder(
			repositoryEntryId);

		repositoryEntry = _getRootRepositoryEntry(rootMountFolder);

		return repositoryEntry.getMappedId();
	}

	private String _getRepositoryEntryMappedId(String entryUuid)
		throws PortalException, SystemException {

		RepositoryEntry repositoryEntry = RepositoryEntryUtil.fetchByUUID_G(
			entryUuid, getGroupId(), true);

		if (repositoryEntry == null) {
			throw new NoSuchRepositoryEntryException(
				"No repository entry exits with {uuid='" + entryUuid + "}");
		}

		return repositoryEntry.getMappedId();
	}

	private RepositoryEntry _getRootRepositoryEntry(DLFolder rootMountFolder)
		throws PortalException, SystemException {

		RepositoryEntry repositoryEntry = RepositoryEntryUtil.fetchByR_M(
			getRepositoryId(), _extRepository.getRootFolderId());

		if (repositoryEntry == null) {
			try {
				repositoryEntry =
					RepositoryEntryLocalServiceUtil.addRepositoryEntry(
						rootMountFolder.getUserId(), getGroupId(),
						getRepositoryId(), _extRepository.getRootFolderId(),
						new ServiceContext());
			}
			catch (PortalException pe) {
				throw new SystemException(
					"Cannot create root folder entry", pe);
			}
		}

		return repositoryEntry;
	}

	@SuppressWarnings("unchecked")
	private <T extends ExtRepositoryObjectAdapter<?>> List<T> _subList(
		List<T> list, int start, int end, OrderByComparator obc) {

		if (obc != null) {
			list = ListUtil.sort(list, obc);
		}

		return ListUtil.subList(list, start, end);
	}

	private ExtRepositoryFileVersionAdapter _toExtRepositoryFileVersionAdapter(
			ExtRepositoryFileEntryAdapter extRepositoryFileEntryAdapter,
			ExtRepositoryFileVersion extRepositoryFileVersion)
		throws SystemException {

		Object[] ids = getRepositoryEntryIds(extRepositoryFileVersion.getId());

		long extRepositoryObjectId = (Long)ids[0];

		String uuid = (String)ids[1];

		return new ExtRepositoryFileVersionAdapter(
			this, extRepositoryObjectId, uuid, extRepositoryFileEntryAdapter,
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

	@SuppressWarnings("unchecked")
	private <T extends ExtRepositoryObjectAdapter<?>> T
		_toExtRepositoryObjectAdapter(
			ExtRepositoryObjectAdapterType<T> extRepositoryObjectAdapterType,
			ExtRepositoryObject extRepositoryObject)
		throws PortalException, SystemException {

		Object[] ids = getRepositoryEntryIds(extRepositoryObject.getId());

		long extRepositoryObjectId = (Long)ids[0];

		String uuid = (String)ids[1];

		ExtRepositoryObjectAdapter<?> extRepositoryObjectAdapter = null;

		if (extRepositoryObjectAdapter == null) {
			if (extRepositoryObject instanceof ExtRepositoryFolder) {
				ExtRepositoryFolder extRepositoryFolder =
					(ExtRepositoryFolder)extRepositoryObject;

				extRepositoryObjectAdapter = new ExtRepositoryFolderAdapter(
					this, extRepositoryObjectId, uuid, extRepositoryFolder);
			}
			else {
				ExtRepositoryFileEntry extRepositoryFileEntry =
					(ExtRepositoryFileEntry)extRepositoryObject;

				extRepositoryObjectAdapter = new ExtRepositoryFileEntryAdapter(
					this, extRepositoryObjectId, uuid, extRepositoryFileEntry);

				_forceGetVersions(
					(ExtRepositoryFileEntryAdapter)extRepositoryObjectAdapter);
			}
		}

		if (extRepositoryObjectAdapterType ==
				ExtRepositoryObjectAdapterType.FILE) {

			if (!(extRepositoryObjectAdapter instanceof
					ExtRepositoryFileEntryAdapter)) {

				throw new NoSuchFileEntryException(
					"External repository entry is not a file " +
						extRepositoryObject);
			}
		}
		else if (extRepositoryObjectAdapterType ==
					ExtRepositoryObjectAdapterType.FOLDER) {

			if (!(extRepositoryObjectAdapter instanceof
					ExtRepositoryFolderAdapter)) {

				throw new NoSuchFolderException(
					"External repository entry is not a folder " +
						extRepositoryObject);
			}
		}
		else if (extRepositoryObjectAdapterType !=
					ExtRepositoryObjectAdapterType.OBJECT) {

			throw new IllegalArgumentException(
				"Unsupported repository entry type " +
					extRepositoryObjectAdapterType);
		}

		return (T)extRepositoryObjectAdapter;
	}

	private <T extends ExtRepositoryObjectAdapter<?>> List<T>
		_toExtRepositoryObjectAdapters(
			ExtRepositoryObjectAdapterType<T> extRepositoryObjectAdapterType,
			List<? extends ExtRepositoryObject> extRepositoryObjects)
		throws PortalException, SystemException {

		List<T> extRepositoryObjectAdapters = new ArrayList<T>();

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