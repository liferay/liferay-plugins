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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.io.InputStream;

import java.util.List;

/**
 * @author Iván Zaera
 * @author Sergio González
 */
public interface ExtRepository {

	public ExtRepositoryFileEntry addFileEntry(
			String parentFolderId, String title, String changeLog,
			InputStream is)
		throws PortalException, SystemException;

	public ExtRepositoryFolder addFolder(String parentFolderId, String name)
		throws PortalException, SystemException;

	public ExtRepositoryFileVersion cancelCheckOut(String extRepositoryEntryId)
		throws PortalException, SystemException;

	public void checkInFileEntry(
			String extRepositoryEntryId, boolean createMajorVersion,
			String changeLog)
		throws PortalException, SystemException;

	public ExtRepositoryFileEntry checkOutFileEntry(String extRepositoryEntryId)
		throws PortalException, SystemException;

	public <T extends ExtRepositoryEntry> T copyExtRepositoryEntry(
			ExtRepositoryModelType<T> extRepositoryModelType,
			String extRepositoryEntryId, String newFolderId, String newTitle)
		throws PortalException, SystemException;

	public void deleteExtRepositoryEntry(
			ExtRepositoryModelType<? extends ExtRepositoryEntry>
				extRepositoryModelType,
			String extRepositoryEntryId)
		throws PortalException, SystemException;

	public String getAuthType();

	public InputStream getContentStream(
			ExtRepositoryFileEntry extRepositoryFileEntry)
		throws PortalException, SystemException;

	public InputStream getContentStream(
			ExtRepositoryFileVersion extRepositoryFileVersion)
		throws PortalException, SystemException;

	public int getEntriesCount(
			ExtRepositoryModelType<? extends ExtRepositoryEntry>
				extRepositoryModelType,
			String folderId)
		throws PortalException, SystemException;

	public <T extends ExtRepositoryEntry> List<T> getExtRepositoryEntries(
			ExtRepositoryModelType<T> extRepositoryModelType, String folderId)
		throws PortalException, SystemException;

	public <T extends ExtRepositoryEntry> T getExtRepositoryEntry(
			ExtRepositoryModelType<T> extRepositoryModelType,
			String extRepositoryEntryId)
		throws PortalException, SystemException;

	public <T extends ExtRepositoryEntry> T getExtRepositoryEntry(
			ExtRepositoryModelType<T> extRepositoryModelType, String folderId,
			String title)
		throws PortalException, SystemException;

	public ExtRepositoryFileVersion getFileVersion(
			ExtRepositoryFileEntry extRepositoryFileEntry, String version)
		throws PortalException, SystemException;

	public List<ExtRepositoryFileVersion> getFileVersions(
			ExtRepositoryFileEntry extRepositoryFileEntry)
		throws SystemException;

	public String getLiferayUserId(String extRepositoryUserName);

	public ExtRepositoryFolder getParentFolder(
			ExtRepositoryEntry extRepositoryEntry)
		throws PortalException, SystemException;

	public String getRootFolderId() throws PortalException, SystemException;

	public List<String> getSubfolderIds(String folderId, boolean recurse)
		throws PortalException, SystemException;

	public String[] getSupportedConfigurations();

	public String[][] getSupportedParameters();

	public void initRepository(
			UnicodeProperties typeSettingsProperties,
			CredentialsProvider credentialsProvider)
		throws PortalException, SystemException;

	public <T extends ExtRepositoryEntry> T moveExtRepositoryEntry(
			ExtRepositoryModelType<T> extRepositoryModelType,
			String extRepositoryEntryId, String newFolderId, String newTitle)
		throws PortalException, SystemException;

	public List<ExtRepositorySearchResult<?>> search(
			List<String> folderIds, String keywords, QueryConfig queryConfig,
			int start, int end)
		throws PortalException, SystemException;

	public ExtRepositoryFileEntry updateFileEntry(String fileId, InputStream is)
		throws PortalException, SystemException;

}