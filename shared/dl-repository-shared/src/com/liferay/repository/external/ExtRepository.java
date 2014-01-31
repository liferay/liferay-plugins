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
 * All data going back and forth is in ext repository's domain format unless
 * otherwise stated in the javadoc. Thus, all ext repository object keys are
 * strings expressed in the native ext repository format. The same can be said
 * for user names and the rest of data.
 *
 * One exception is the
 * {@link ExtRepository#search(SearchContext, Query, ExtRepositoryQueryMapper)}
 * method where the given {@link Query} contains Liferay identifiers and user
 * names. To help with this, developers of that method are given an
 * {@link ExtRepositoryQueryMapper} instance that can translate such IDs to the
 * native ext repository format.
 *
 * @author Iván Zaera
 * @author Sergio González
 */
public interface ExtRepository {

	/**
	 * Adds an ext repository file entry and associated metadata based on an
	 * {@link InputStream} object.
	 *
	 * @param  extRepositoryParentFolderKey the primary key of ext repository
	 *         file entry's parent folder
	 * @param  mimeType the ext repository file entry's MIME type
	 * @param  title the name to be assigned to the ext repository file entry
	 * @param  description the ext repository file entry's description
	 * @param  changeLog the ext repository file entry's version change log
	 * @param  inputStream the ext repository file entry's data (optionally
	 *         <code>null</code>)
	 * @return the ext repository file entry
	 * @throws PortalException if the ext repository parent folder could not be
	 *         found or if the ext repository file entry's information was
	 *         invalid
	 * @throws SystemException if a system exception occurred
	 */
	public ExtRepositoryFileEntry addExtRepositoryFileEntry(
			String extRepositoryParentFolderKey, String mimeType, String title,
			String description, String changeLog, InputStream inputStream)
		throws PortalException, SystemException;

	/**
	 * Adds an ext repository folder.
	 *
	 * @param  extRepositoryParentFolderKey the primary key of the ext
	 *         repository folder where the new folder will be created
	 * @param  name the ext repository folder's name
	 * @param  description the ext repository folder's description
	 * @return the ext repository folder
	 * @throws PortalException if the ext repository parent folder could not be
	 *         found or if the ext repository folder's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public ExtRepositoryFolder addExtRepositoryFolder(
			String extRepositoryParentFolderKey, String name,
			String description)
		throws PortalException, SystemException;

	/**
	 * Cancels the check out of the ext repository file. If a user has not
	 * checked out the specified ext repository file entry, invoking this method
	 * will result in no changes.
	 *
	 * @param  extRepositoryFileEntryKey the primary key of the ext repository
	 *         file entry
	 * @return the discarded ext repository file version or <code>null</code>
	 *         if not available
	 * @throws PortalException if ext repository file entry's information was
	 *         invalid
	 * @throws SystemException if a system exception occurred
	 */
	public ExtRepositoryFileVersion cancelCheckOut(
			String extRepositoryFileEntryKey)
		throws PortalException, SystemException;

	/**
	 * Checks in the ext repositoy file entry. If a user has not checked out the
	 * specified ext repository file entry, invoking this method will result in
	 * no changes.
	 *
	 * @param  extRepositoryFileEntryKey the primary key of the ext repository
	 *         file entry
	 * @param  createMajorVersion whether to increase major or minor version
	 *         number
	 * @param  changeLog the description of the changes being checked in
	 * @throws PortalException if the ext repository file entry's information
	 *         was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public void checkInExtRepositoryFileEntry(
			String extRepositoryFileEntryKey, boolean createMajorVersion,
			String changeLog)
		throws PortalException, SystemException;

	/**
	 * Check out the ext repository file entry.
	 *
	 * @param  extRepositoryFileEntryKey the primary key of the ext repository
	 *         file entry
	 * @return the checked out ext repository file entry
	 * @throws PortalException if the ext repository file entry's information
	 *         was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public ExtRepositoryFileEntry checkOutExtRepositoryFileEntry(
			String extRepositoryFileEntryKey)
		throws PortalException, SystemException;

	/**
	 * Copies a ext repository file entry or ext repository folder to a
	 * different location.
	 *
	 * @param  extRepositoryObjectType the type of ext repository object
	 * @param  extRepositoryFileEntryKey the primary key of the ext repository
	 *         object
	 * @param  newExtRepositoryFolderKey the primary key of the ext repository
	 *         destination folder
	 * @param  newTitle the new name of the ext repository object in the
	 *         destination location
	 * @return the ext repository object being copied
	 * @throws PortalException if the provided information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public <T extends ExtRepositoryObject> T copyExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryFileEntryKey, String newExtRepositoryFolderKey,
			String newTitle)
		throws PortalException, SystemException;

	/**
	 * Deletes a ext repository object.
	 *
	 * @param  extRepositoryObjectType the type of the ext repository object
	 * @param  extRepositoryObjectKey the primary key of the ext repository
	 *         object
	 * @throws PortalException if the ext repository object's information was
	 *         invalid
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteExtRepositoryObject(
			ExtRepositoryObjectType<? extends ExtRepositoryObject>
				extRepositoryObjectType,
			String extRepositoryObjectKey)
		throws PortalException, SystemException;

	public String getAuthType();

	/**
	 * Returns the content stream of the current ext repository file version.
	 *
	 * @param  extRepositoryFileEntry the primary key of the ext repository
	 *         file entry
	 * @return the content stream of the current ext repository file version
	 * @throws PortalException if the ext repository file entry's information
	 *         was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public InputStream getContentStream(
			ExtRepositoryFileEntry extRepositoryFileEntry)
		throws PortalException, SystemException;

	/**
	 * Returns the content stream of a specific ext repository file version.
	 *
	 * @param  extRepositoryFileVersion the primary key of the ext repository
	 *         file version
	 * @return the content stream of the ext repository file version
	 * @throws PortalException if the ext repository file version's information
	 *         was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public InputStream getContentStream(
			ExtRepositoryFileVersion extRepositoryFileVersion)
		throws PortalException, SystemException;

	/**
	 * Returns a specific ext repository file version of a file, identified by
	 * its version name.
	 *
	 * @param  extRepositoryFileEntry the primary key of ext repository
	 *         file entry
	 * @param  version the ext repository version name (example: "1.0")
	 * @return the file version
	 * @throws PortalException if the ext repository file entry's or ext
	 *         repository file version's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public ExtRepositoryFileVersion getExtRepositoryFileVersion(
			ExtRepositoryFileEntry extRepositoryFileEntry, String version)
		throws PortalException, SystemException;

	/**
	 * Translate a version key into an {@link
	 * ExtRepositoryFileVersionDescriptor} describing the ext repository file
	 * entry key and the ext repository version name.
	 *
	 * @param  extRepositoryFileVersionKey an ext repository file version key
	 * @return the translated {@link ExtRepositoryFileVersionDescriptor}
	 * @see    ExtRepositoryFileVersionDescriptor
	 */
	public ExtRepositoryFileVersionDescriptor
		getExtRepositoryFileVersionDescriptor(
			String extRepositoryFileVersionKey);

	/**
	 * Returns the list of ext repository file versions of the ext repository
	 * file entry. The list is ordered by version age, being the newest version
	 * in the first position (index 0).
	 *
	 * @param  extRepositoryFileEntry the primary key of ext repository
	 *         file entry
	 * @return a list of the ext repository file versions of the ext repository
	 *         file entry
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExtRepositoryFileVersion> getExtRepositoryFileVersions(
			ExtRepositoryFileEntry extRepositoryFileEntry)
		throws PortalException, SystemException;

	/**
	 * Returns a ext repository object identified by its key.
	 *
	 * @param  extRepositoryObjectType the type of the ext repository object
	 * @param  extRepositoryObjectKey the repository primary key of the ext
	 *         repository object
	 * @return the requested ext repository object
	 * @throws PortalException if the ext repository object's information was
	 *         invalid
	 * @throws SystemException if a system exception occurred
	 */
	public <T extends ExtRepositoryObject> T getExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryObjectKey)
		throws PortalException, SystemException;

	/**
	 * Returns the ext repository object with the specified name inside a given
	 * ext repository folder.
	 *
	 * @param  extRepositoryObjectType the type of the ext repository object
	 * @param  extRepositoryFolderKey the primary key of the ext repository
	 *         folder
	 * @param  title the name of the ext repository object to get
	 * @return the requested ext repository object
	 * @throws PortalException if the ext repository folder's information was
	 *         invalid
	 * @throws SystemException if a system exception occurred
	 */
	public <T extends ExtRepositoryObject> T getExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryFolderKey, String title)
		throws PortalException, SystemException;

	/**
	 * Returns the list of the specified ext repository objects contained in a
	 * given folder. Depending on the type of ext repository object requested
	 * this method may return only ext repository files, only ext repository
	 * folders, or a list of ext repository files and ext repository folders.
	 *
	 * @param  extRepositoryObjectType the type of ext repository objects to
	 *         return
	 * @param  extRepositoryFolderKey the primary key of the ext repository
	 *         folder
	 * @return the requested ext repository objects
	 * @throws PortalException if the ext repository folder's information was
	 *         invalid
	 * @throws SystemException if a system exception occurred
	 */
	public <T extends ExtRepositoryObject> List<T> getExtRepositoryObjects(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryFolderKey)
		throws PortalException, SystemException;

	/**
	 * Returns the count of elements of the given type contained in the
	 * specified ext repository folder. Depending on the type of the ext
	 * repository object requested this method may return only a ext repository
	 * folder count, a ext repository file count, or the count of both ext
	 * repository files and ext repository folders.
	 *
	 * @param  extRepositoryObjectType the type of ext repository objects to
	 *         count
	 * @param  extRepositoryFolderKey the primary key of the ext repository
	 *         folder
	 * @return the requested ext repository objects' count
	 * @throws PortalException if the ext repository folder's information was
	 *         invalid
	 * @throws SystemException if a system exception occurred
	 */
	public int getExtRepositoryObjectsCount(
			ExtRepositoryObjectType<? extends ExtRepositoryObject>
				extRepositoryObjectType,
			String extRepositoryFolderKey)
		throws PortalException, SystemException;

	/**
	 * Returns the ext repository parent folder of a given ext repository
	 * object.
	 *
	 * @param  extRepositoryObject the ext repository object
	 * @return the ext repository parent folder of the ext repository object
	 * @throws PortalException if the ext repository object's information was
	 *         invalid
	 * @throws SystemException if a system exception occurred
	 */
	public ExtRepositoryFolder getExtRepositoryParentFolder(
			ExtRepositoryObject extRepositoryObject)
		throws PortalException, SystemException;

	/**
	 * Map an ext repository user login to its equivalent Liferay's login.
	 * A Liferay login can be a user ID, screen name, e-mail, ... depending on
	 * the value returned by the {@link ExtRepository#getAuthType()} method.
	 * The format of the repository login is specific for each ext repository
	 * implementation.
	 *
	 * @param  extRepositoryLogin an ext repository login
	 * @return a Liferay's login in the format specified by the {@link
	 *         ExtRepository#getAuthType()} method
	 */
	public String getLiferayLogin(String extRepositoryLogin);

	/**
	 * Get the primary key of the ext repository root folder.
	 *
	 * @return the primary key of the ext repository root folder
	 * @throws PortalException if the ext repository root folder cannot be
	 *         accessed
	 * @throws SystemException if a system exception occurred
	 */
	public String getRootFolderKey() throws PortalException, SystemException;

	/**
	 * Returns the list of keys of the ext repository subfolders stored inside
	 * the specified ext repository folder.
	 *
	 * @param  extRepositoryFolderKey the primary key of the ext repository
	 *         folder
	 * @param  recurse whether or not to recurse through each ext repository
	 *         subfolder
	 * @return list of keys of the ext repository subfolders
	 * @throws PortalException if the ext repository folder's information was
	 *         invalid
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
	 * Get the list of supported ext repository specific configuration
	 * parameters. These parameters are stored in the database when the ext
	 * repository is configured and made available through a {@link
	 * UnicodeProperties} to the {@link ExtRepository#initRepository(
	 * UnicodeProperties, CredentialsProvider)} method.
	 *
	 * @return a 2D array of supported configuration parameters lists indexed by
	 *         configuration types
	 * @see    ExtRepository#getSupportedConfigurations()
	 */
	public String[][] getSupportedParameters();

	/**
	 * Checks the connectivity between Liferay and a repository. Developers of
	 * this method must use the credentials provided by the {@link
	 * CredentialsProvider} object to authenticate to the ext repository. In
	 * addition, they should get the ext repository specific configuration
	 * parameters from the typeSettingsProperties parameter. The parameters
	 * contained in this variable are indexed by the key names returned by the
	 * {@link ExtRepository#getSupportedParameters()} method. This method is
	 * called only once, the first time the repository is used.
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
	 * Moves an ext repository object to a different location. May also be used
	 * to rename ext repository objects.
	 *
	 * @param  extRepositoryObjectType the type of ext repository object
	 * @param  extRepositoryObjectKey the primary key of the ext repository
	 *         object
	 * @param  newExtRepositoryFolderKey the destination ext repository folder
	 *         where the ext repository object must be moved
	 * @param  newTitle the new name of the ext repository object (may not
	 *         change)
	 * @return the new ext repository object in the destination location
	 * @throws PortalException if ext repository object cannot be moved
	 * @throws SystemException if a system exception occurred
	 */
	public <T extends ExtRepositoryObject> T moveExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryObjectKey, String newExtRepositoryFolderKey,
			String newTitle)
		throws PortalException, SystemException;

	/**
	 * Perform a search inside the ext repository. The ext repository objects
	 * to look for must fulfill the given {@link Query} as close as possible
	 * (possibly limited by the back end ext repository constraints).
	 *
	 * @param  searchContext the context of the search (developers of this
	 *         method are required to honor the "folderIds", "start" and "end"
	 *         attributes contained in this search context)
	 * @param  query the logical expression describing the query to be performed
	 *         (developers may need to translate this to an equivalent native
	 *         repository query)
	 * @param  extRepositoryQueryMapper a helper ext repository query mapper to
	 *         translate Liferay IDs to native repository format
	 * @return a list of results fulfilling the query
	 * @throws PortalException if the search failed or is invalid
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExtRepositorySearchResult<?>> search(
			SearchContext searchContext, Query query,
			ExtRepositoryQueryMapper extRepositoryQueryMapper)
		throws PortalException, SystemException;

	/**
	 * Updates a ext repository file entry's content
	 *
	 * @param  mimeType the new content of the MIME type of the ext repository
	 *         file entry
	 * @param  inputStream the new ext repository file entry's content
	 * @return the file entry
	 * @throws PortalException if ext repository file entry's information was
	 *         invalid
	 * @throws SystemException if a system exception occurred
	 */
	public ExtRepositoryFileEntry updateExtRepositoryFileEntry(
			String extRepositoryFileEntryKey, String mimeType,
			InputStream inputStream)
		throws PortalException, SystemException;

}