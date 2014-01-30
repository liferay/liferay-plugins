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
 * This interface must be implemented by classes willing to provide support for
 * a new external repository for Liferay's Document Library.
 *
 * All data going back and forth is in repository's domain format unless
 * otherwise stated in the javadoc. Thus, all object keys are strings expressed
 * in the native repository format. The same can be said for user names and the
 * rest of data.
 *
 * One exception is the
 * {@link ExtRepository#search(SearchContext, Query, ExtRepositoryQueryMapper)}
 * method where the given {@link Query} contains Liferay identifiers and user
 * names. To help with this, implementers of that method are given an
 * {@link ExtRepositoryQueryMapper} instance that can translate such ids to the
 * native repository format.
 *
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
	 *         folder where the new folder will be created
	 * @param  name the new folder's name
	 * @param  description the new folder's description
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
	 * @return the discarded file version or null if not available
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
	 * @param  createMajorVersion whether to increase major or minor version
	 *         number
	 * @param  changeLog the description of the changes being checked in
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
	 * @return the checked out file entry
	 * @throws PortalException if the file entry's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public ExtRepositoryFileEntry checkOutExtRepositoryFileEntry(
			String extRepositoryFileEntryKey)
		throws PortalException, SystemException;

	/**
	 * Copies a file or folder to a different location.
	 *
	 * @param  extRepositoryObjectType the type of object (file or folder)
	 * @param  extRepositoryFileEntryKey the repository primary key of the
	 *         object
	 * @param  newExtRepositoryFolderKey the repository primary key of the
	 *         destination folder
	 * @param  newTitle the new name of the object in the destination location
	 * @return the file or folder being copied
	 * @throws PortalException if the provided information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public <T extends ExtRepositoryObject> T copyExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryFileEntryKey, String newExtRepositoryFolderKey,
			String newTitle)
		throws PortalException, SystemException;

	/**
	 * Deletes a file, folder or version.
	 *
	 * @param  extRepositoryObjectType the type of object (file or folder)
	 * @param  extRepositoryObjectKey the repository primary key of the object
	 * @throws PortalException if the object's information was invalid
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
	 * @return the content stream of the current file version
	 * @throws PortalException if the file entry's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public InputStream getContentStream(
			ExtRepositoryFileEntry extRepositoryFileEntry)
		throws PortalException, SystemException;

	/**
	 * Returns the content stream of a specific file version.
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
	 * Returns a specific file version of a file, identified by its version
	 * name.
	 *
	 * @param  extRepositoryFileEntry the repository file entry primary key
	 * @param  version the version name (example: "1.0")
	 * @return the file version
	 * @throws PortalException if the file entry's or file version's information
	 *         was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public ExtRepositoryFileVersion getExtRepositoryFileVersion(
			ExtRepositoryFileEntry extRepositoryFileEntry, String version)
		throws PortalException, SystemException;

	/**
	 * Translate a version key into an
	 * {@link ExtRepositoryFileVersionDescriptor} describing the file entry key
	 * and the version name.
	 *
	 * @param  extRepositoryFileVersionKey a file version key
	 * @return the translated {@link ExtRepositoryFileVersionDescriptor}
	 * @see    ExtRepositoryFileVersionDescriptor
	 */
	public ExtRepositoryFileVersionDescriptor
		getExtRepositoryFileVersionDescriptor(
			String extRepositoryFileVersionKey);

	/**
	 * Returns the list of file versions of the file entry. The list is ordered
	 * by version age, being the newest version in the first position (index 0).
	 *
	 * @param  extRepositoryFileEntry the repository file entry primary key
	 * @return a list of the file versions of the file entry
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExtRepositoryFileVersion> getExtRepositoryFileVersions(
			ExtRepositoryFileEntry extRepositoryFileEntry)
		throws PortalException, SystemException;

	/**
	 * Returns a file or a folder identified by its key.
	 *
	 * @param  extRepositoryObjectType the type of the object (folder of file)
	 * @param  extRepositoryObjectKey the repository primary key of the object
	 * @return the requested file or folder
	 * @throws PortalException if the object's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public <T extends ExtRepositoryObject> T getExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryObjectKey)
		throws PortalException, SystemException;

	/**
	 * Returns the file or a folder with the specified name inside a given
	 * folder.
	 *
	 * @param  extRepositoryObjectType the type of the object (folder or file)
	 * @param  extRepositoryFolderKey the repository primary key of the folder
	 * @param  title the name of the object to get
	 * @return the requested file or folder
	 * @throws PortalException if the folder's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public <T extends ExtRepositoryObject> T getExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryFolderKey, String title)
		throws PortalException, SystemException;

	/**
	 * Returns the list of the specified objects contained in a given folder.
	 * Depending on the type of object requested this method may return only
	 * files, only folders, or a list of files and folders.
	 *
	 * @param  extRepositoryObjectType the type of objects to return (file,
	 *         folder or object for both)
	 * @param  extRepositoryFolderKey the repository primary key of the folder
	 * @return the requested objects
	 * @throws PortalException if the folder's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public <T extends ExtRepositoryObject> List<T> getExtRepositoryObjects(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryFolderKey)
		throws PortalException, SystemException;

	/**
	 * Returns the count of elements of the given type contained in the
	 * specified folder. Depending on the type of object requested this method
	 * may return only a folder count, a file count, or the count of both files
	 * and folders.
	 *
	 * @param  extRepositoryObjectType the type of objects to count (file,
	 *         folder or object for both)
	 * @param  extRepositoryFolderKey the repository primary key of the folder
	 * @return the requested objects' count
	 * @throws PortalException if the folder's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public int getExtRepositoryObjectsCount(
			ExtRepositoryObjectType<? extends ExtRepositoryObject>
				extRepositoryObjectType,
			String extRepositoryFolderKey)
		throws PortalException, SystemException;

	/**
	 * Returns the parent folder of a given object.
	 *
	 * @param  extRepositoryObject the repository object
	 * @return the parent folder of the object
	 * @throws PortalException if the object's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public ExtRepositoryFolder getExtRepositoryParentFolder(
			ExtRepositoryObject extRepositoryObject)
		throws PortalException, SystemException;

	/**
	 * Map a repository user login to its equivalent Liferay's login. A
	 * Liferay login can be a user id, screen name, e-mail, ... depending on
	 * the value returned by the {@link ExtRepository#getAuthType()} method. The
	 * format of the repository login is specific for each repository
	 * implementation.
	 *
	 * @param  extRepositoryLogin a repository login
	 * @return a Liferay's login in the format specified by the
	 *         {@link ExtRepository#getAuthType()} method
	 */
	public String getLiferayLogin(String extRepositoryLogin);

	/**
	 * Get the primary key of the root folder.
	 *
	 * @return the primary key of the root folder
	 * @throws PortalException if the root folder cannot be accessed
	 * @throws SystemException if a system exception occurred
	 */
	public String getRootFolderKey() throws PortalException, SystemException;

	/**
	 * Returns the list of keys of the subfolders stored inside the specified
	 * folder.
	 *
	 * @param  extRepositoryFolderKey the repository primary key of the folder
	 * @param  recurse whether or not to recurse through each subfolder
	 * @return list of keys of the subfolders
	 * @throws PortalException if the folder's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public List<String> getSubfolderKeys(
			String extRepositoryFolderKey, boolean recurse)
		throws PortalException, SystemException;

	/**
	 * Get the list of supported configurations. Each supported configuration
	 * may have a different list of supported parameters.
	 *
	 * @return the list of supported configurations
	 * @see    ExtRepository#getSupportedParameters()
	 */
	public String[] getSupportedConfigurations();

	/**
	 * Get the list of supported repository specific configuration parameters.
	 * These parameters are stored in the database when the repository is
	 * configured and  made available through a {@link UnicodeProperties} to the
	 * {@link ExtRepository#initRepository(UnicodeProperties, CredentialsProvider)}
	 * method.
	 *
	 * @return a 2D array of supported configuration parameters lists indexed
	 *         by configuration types
	 * @see    ExtRepository#getSupportedConfigurations()
	 */
	public String[][] getSupportedParameters();

	/**
	 * Checks the connectivity between Liferay and a repository. Implementors of
	 * this method must use the credentials provided by the
	 * {@link CredentialsProvider} object to authenticate to the repository. In
	 * addition, they should get the repository specific configuration
	 * parameters from the typeSettingsProperties parameter. The parameters
	 * contained in this variable are indexed by the key names returned by the
	 * {@link ExtRepository#getSupportedParameters()} method.
	 * This method is called only once, the first time the repository is used.
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
	 * Moves an folder or file to a different location. May also be used to
	 * rename files or folders.
	 *
	 * @param  extRepositoryObjectType the type of object (file or folder)
	 * @param  extRepositoryObjectKey the repository primary key of the object
	 * @param  newExtRepositoryFolderKey the destination folder where the object
	 *         must be moved
	 * @param  newTitle the new name of the object (may not change)
	 * @return the new object (file or folder) in the destination location
	 * @throws PortalException if object cannot be moved
	 * @throws SystemException if a system exception occurred
	 */
	public <T extends ExtRepositoryObject> T moveExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryObjectKey, String newExtRepositoryFolderKey,
			String newTitle)
		throws PortalException, SystemException;

	/**
	 * Perform a search inside the repository. The objects to look for must
	 * fulfill the given {@link Query} as close as possible (possibly limited
	 * by the back end repository constraints.
	 *
	 * @param  searchContext the context of the search (implementors of this
	 * 	       method are required to honor the "folderIds", "start" and "end"
	 * 		   attributes contained in this search context)
	 * @param  query the logical expression describing the query to be
	 * 		   performed (implementors may need to translate this to an
	 * 		   equivalent native repository query)
	 * @param  extRepositoryQueryMapper a helper object to translate Liferay ids
	 *         to native repository format
	 * @return a list of results fulfilling the query
	 * @throws PortalException if the search failed or is invalid
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExtRepositorySearchResult<?>> search(
			SearchContext searchContext, Query query,
			ExtRepositoryQueryMapper extRepositoryQueryMapper)
		throws PortalException, SystemException;

	/**
	 * Updates a file entry's content
	 *
	 * @param  mimeType the new content's MIME type
	 * @param  inputStream the new file's content
	 * @return the file entry
	 * @throws PortalException if file entry's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public ExtRepositoryFileEntry updateExtRepositoryFileEntry(
			String extRepositoryFileEntryKey, String mimeType,
			InputStream inputStream)
		throws PortalException, SystemException;

}