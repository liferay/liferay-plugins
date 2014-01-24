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
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.repository.external.search.ExtRepositoryQueryMapper;

import java.io.InputStream;

import java.util.List;

/**
 * @author Iván Zaera
 * @author Sergio González
 */
public interface ExtRepository {

	/**
	 * Adds a file entry and associated metadata based on an {@link InputStream}
	 * object.
	 *
	 * @param  extRepositoryParentFolderKey the primary key of the folder
	 * @param  mimeType the file's MIME type
	 * @param  title the name to be assigned to the file
	 * @param  description the file's description
	 * @param  changeLog the file's version change log
	 * @param  inputStream the file's data (optionally <code>null</code>)
	 * @return the file entry
	 * @throws PortalException if the parent folder could not be found or if the
	 *         file entry's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public ExtRepositoryFileEntry addExtRepositoryFileEntry(
			String extRepositoryParentFolderKey, String mimeType, String title,
			String description, String changeLog, InputStream inputStream)
		throws PortalException, SystemException;

	/**
	 * Adds a folder.
	 *
	 * @param  extRepositoryParentFolderKey the repository primary key of the
	 *         folder
	 * @param  name the folder's name
	 * @param  description the folder's description
	 * @return the folder
	 * @throws PortalException if the parent folder could not be found or if the
	 *         new folder's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public ExtRepositoryFolder addExtRepositoryFolder(
			String extRepositoryParentFolderKey, String name,
			String description)
		throws PortalException, SystemException;

	/**
	 * Cancels the file's checkout.
	 *
	 * @param  extRepositoryFileEntryKey the repository primary key of the file
	 *         entry
	 * @return the file version
	 * @throws PortalException if file entry's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public ExtRepositoryFileVersion cancelCheckOut(
			String extRepositoryFileEntryKey)
		throws PortalException, SystemException;

	/**
	 * Checks in the file.
	 *
	 * @param  extRepositoryFileEntryKey the primary key of the file entry
	 * @param  createMajorVersion
	 * @param  changeLog the file's version change log
	 * @throws PortalException if the file entry's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public void checkInExtRepositoryFileEntry(
			String extRepositoryFileEntryKey, boolean createMajorVersion,
			String changeLog)
		throws PortalException, SystemException;

	/**
	 * Checks out the file.
	 *
	 * @param  extRepositoryFileEntryKey the repository primary key of the file
	 * @return the file entry
	 * @throws PortalException if the file entry's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public ExtRepositoryFileEntry checkOutExtRepositoryFileEntry(
			String extRepositoryFileEntryKey)
		throws PortalException, SystemException;

	public <T extends ExtRepositoryObject> T copyExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryFileEntryKey, String newExtRepositoryFolderKey,
			String newTitle)
		throws PortalException, SystemException;

	/**
	 * Deletes a file, a folder or a version.
	 *
	 * @param  extRepositoryObjectType the type of the object
	 * @param  extRepositoryObjectKey the repository primary key of the object
	 * @throws PortalException if the object's information was invalidd
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteExtRepositoryObject(
			ExtRepositoryObjectType<? extends ExtRepositoryObject>
				extRepositoryObjectType,
			String extRepositoryObjectKey)
		throws PortalException, SystemException;

	public String getAuthType();

	/**
	 * Returns the content stream of the current file version.
	 *
	 * @param  extRepositoryFileEntry the repository file entry primary key
	 * @return the content stream of the current file version.
	 * @throws PortalException if the file entry's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public InputStream getContentStream(
			ExtRepositoryFileEntry extRepositoryFileEntry)
		throws PortalException, SystemException;

	/**
	 * Returns the content stream of the file version.
	 *
	 * @param  extRepositoryFileVersion the repository file version primary key
	 * @return the content stream of the file version
	 * @throws PortalException if the file version's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public InputStream getContentStream(
			ExtRepositoryFileVersion extRepositoryFileVersion)
		throws PortalException, SystemException;

	/**
	 * Returns the file version.
	 *
	 * @param  extRepositoryFileEntry the repository file entry primary key
	 * @param  version the file's version
	 * @return the file version
	 * @throws PortalException if the file entry's or file version's information
	 *         was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public ExtRepositoryFileVersion getExtRepositoryFileVersion(
			ExtRepositoryFileEntry extRepositoryFileEntry, String version)
		throws PortalException, SystemException;

	public ExtRepositoryFileVersionDescriptor
		getExtRepositoryFileVersionDescriptor(
			String extRepositoryFileVersionKey);

	/**
	 * Returns a list of file versions of the file entry.
	 *
	 * @param  extRepositoryFileEntry the repository file entry primary key
	 * @return a list of the file versions of the file entry
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExtRepositoryFileVersion> getExtRepositoryFileVersions(
			ExtRepositoryFileEntry extRepositoryFileEntry)
		throws PortalException, SystemException;

	/**
	 * Returns a file, a folder or a version.
	 *
	 * @param  extRepositoryObjectType the type of the object
	 * @param  extRepositoryObjectKey the repository primary key of the object
	 * @return a file, a folder or a version
	 * @throws PortalException if the object's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public <T extends ExtRepositoryObject> T getExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryObjectKey)
		throws PortalException, SystemException;

	/**
	 * Returns a file, a folder or a version with the specified title int the
	 * contained folder.
	 *
	 * @param  extRepositoryObjectType the type of the object
	 * @param  extRepositoryFolderKey the repository primaty key of the folder
	 * @param  title
	 * @return a file, a folder or a version with the specified title int the
	 *         contained folder
	 * @throws PortalException if the folder's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public <T extends ExtRepositoryObject> T getExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryFolderKey, String title)
		throws PortalException, SystemException;

	/**
	 * Returns a list of the specified elements with the specified title
	 * contained in the specified folder.
	 *
	 * @param  extRepositoryObjectType the type of the object
	 * @param  extRepositoryFolderKey the repository primary key of the folder
	 * @return a list of the specified elements with the specified title
	 *         contained in the specified folder
	 * @throws PortalException if the folder's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public <T extends ExtRepositoryObject> List<T> getExtRepositoryObjects(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryFolderKey)
		throws PortalException, SystemException;

	/**
	 * Returns a list of the specified elements contained in the specified
	 * folder.
	 *
	 * @param  extRepositoryObjectType the type of the object
	 * @param  extRepositoryFolderKey the repository primaty key of the folder
	 * @return a list of the specified elements contained in the specified
	 *         folder
	 * @throws PortalException if the folder's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public int getExtRepositoryObjectsCount(
			ExtRepositoryObjectType<? extends ExtRepositoryObject>
				extRepositoryObjectType,
			String extRepositoryFolderKey)
		throws PortalException, SystemException;

	/**
	 * Returns the parent folder of the object.
	 *
	 * @param  extRepositoryObject the repository object
	 * @return the parent folder of the object
	 * @throws PortalException if the object's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public ExtRepositoryFolder getExtRepositoryParentFolder(
			ExtRepositoryObject extRepositoryObject)
		throws PortalException, SystemException;

	public String getLiferayLogin(String extRepositoryLogin);

	public String getRootFolderKey() throws PortalException, SystemException;

	/**
	 * Returns the list of the subfolders of the specified folder
	 *
	 * @param  extRepositoryFolderKey the repository primary key of the folder
	 * @param  recurse whether to recurse through each subfolder
	 * @return the list of the subfolders of the specified folder
	 * @throws PortalException if the folder's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public List<String> getSubfolderKeys(
			String extRepositoryFolderKey, boolean recurse)
		throws PortalException, SystemException;

	public String[] getSupportedConfigurations();

	public String[][] getSupportedParameters();

	/**
	 * Checks the conectivity between liferay and a repository with the current
	 * liferay user credentials. This method is called the first time the
	 * repository is used.
	 *
	 * @param  typeSettingsProperties the type settings properties
	 * @param  credentialsProvider the liferay user and pass
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public void initRepository(
			UnicodeProperties typeSettingsProperties,
			CredentialsProvider credentialsProvider)
		throws PortalException, SystemException;

	/**
	 * Moves an object
	 *
	 * @param  extRepositoryObjectType the type of the object
	 * @param  extRepositoryObjectKey the repository primary key of the object
	 * @param  newExtRepositoryFolderKey the target repository of the object
	 * @param  newTitle the new title of the object
	 * @return the new object
	 * @throws PortalException
	 * @throws SystemException if a system exception occurred
	 */
	public <T extends ExtRepositoryObject> T moveExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryObjectKey, String newExtRepositoryFolderKey,
			String newTitle)
		throws PortalException, SystemException;

	public List<ExtRepositorySearchResult<?>> search(
			SearchContext searchContext, Query query,
			ExtRepositoryQueryMapper extRepositoryQueryMapper)
		throws PortalException, SystemException;

	/**
	 * Updates a file entry
	 *
	 * @param  mimeType the file's MIME type
	 * @param  inputStream the file's data (optionally <code>null</code>)
	 * @return the updated file entry
	 * @throws PortalException if file entry's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public ExtRepositoryFileEntry updateExtRepositoryFileEntry(
			String extRepositoryFileEntryKey, String mimeType,
			InputStream inputStream)
		throws PortalException, SystemException;

}