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
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.io.InputStream;

import java.util.List;

/**
 * @author Iván Zaera
 * @author Sergio González
 */
public interface ExtRepository {

	public ExtRepositoryFileEntry addFile(
			String parentFolderId, String mimeType, String title,
			String description, String changeLog, InputStream is)
		throws PortalException, SystemException;

	public ExtRepositoryFolder addFolder(
			String parentFolderId, String name, String description)
		throws PortalException, SystemException;

	public ExtRepositoryFileVersion cancelCheckOut(String extRepositoryObjectId)
		throws PortalException, SystemException;

	public void checkInFile(
			String fileId, boolean createMajorVersion, String changeLog)
		throws PortalException, SystemException;

	public ExtRepositoryFileEntry checkOutFile(String extRepositoryObjectId)
		throws PortalException, SystemException;

	public <T extends ExtRepositoryObject> T copyExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryObjectId, String newFolderId, String newTitle)
		throws PortalException, SystemException;

	public void deleteExtRepositoryObject(
			ExtRepositoryObjectType<? extends ExtRepositoryObject>
				extRepositoryObjectType,
			String extRepositoryObjectId)
		throws PortalException, SystemException;

	public String getAuthType();

	public InputStream getContentStream(
			ExtRepositoryFileEntry extRepositoryFileEntry)
		throws PortalException, SystemException;

	public InputStream getContentStream(
			ExtRepositoryFileVersion extRepositoryFileVersion)
		throws PortalException, SystemException;

	public <T extends ExtRepositoryObject> T getExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryObjectId)
		throws PortalException, SystemException;

	public <T extends ExtRepositoryObject> T getExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType, String folderId,
			String title)
		throws PortalException, SystemException;

	public <T extends ExtRepositoryObject> List<T> getExtRepositoryObjects(
			ExtRepositoryObjectType<T> extRepositoryObjectType, String folderId)
		throws PortalException, SystemException;

	public int getExtRepositoryObjectsCount(
			ExtRepositoryObjectType<? extends ExtRepositoryObject>
				extRepositoryObjectType,
			String folderId)
		throws PortalException, SystemException;

	public ExtRepositoryFileVersion getFileVersion(
			ExtRepositoryFileEntry extRepositoryFileEntry, String version)
		throws PortalException, SystemException;

	public ExtRepositoryFileVersionDescriptor getFileVersionDescriptor(
		String fileVersionId);

	public List<ExtRepositoryFileVersion> getFileVersions(
			ExtRepositoryFileEntry extRepositoryFileEntry)
		throws SystemException;

	public String getLiferayUserId(String extRepositoryUserName);

	public ExtRepositoryFolder getParentFolder(
			ExtRepositoryObject extRepositoryObject)
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

	public <T extends ExtRepositoryObject> T moveExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryObjectId, String newFolderId, String newTitle)
		throws PortalException, SystemException;

	public ExtRepositoryFileEntry updateFile(
			String fileId, String mimeType, InputStream is)
		throws PortalException, SystemException;

}