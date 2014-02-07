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
	 * Adds an Ext repository file entry and associated metadata based on the
	 * {@link InputStream} object.
	 *
	 * @param  extRepositoryParentFolderKey the primary key of the Ext
	 *         repository file entry's parent folder
	 * @param  mimeType the Ext repository file entry's MIME type
	 * @param  title the Ext repository file entry's name
	 * @param  description the Ext repository file entry's description
	 * @param  changeLog the Ext repository file entry's version change log
	 * @param  inputStream the Ext repository file entry's data (optionally
	 *         <code>null</code>)
	 * @return the Ext repository file entry and associated metadata
	 * @throws PortalException if the Ext repository parent folder could not be
	 *         found or if the Ext repository file entry's information was
	 *         invalid
	 * @throws SystemException if a system exception occurred
	 */
	public ExtRepositoryFileEntry addExtRepositoryFileEntry(
			String extRepositoryParentFolderKey, String mimeType, String title,
			String description, String changeLog, InputStream inputStream)
		throws PortalException, SystemException;

	/**
	 * Adds an Ext repository folder.
	 *
	 * @param  extRepositoryParentFolderKey the primary key of the Ext
	 *         repository folder's parent folder
	 * @param  name the Ext repository folder's name
	 * @param  description the Ext repository folder's description
	 * @return the Ext repository folder
	 * @throws PortalException if the Ext repository parent folder could not be
	 *         found or if the Ext repository folder's information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public ExtRepositoryFolder addExtRepositoryFolder(
			String extRepositoryParentFolderKey, String name,
			String description)
		throws PortalException, SystemException;

	/**
	 * Cancels the check out of the Ext repository file. If a user has not
	 * checked out the Ext repository file entry, invoking this method results
	 * in no changes.
	 *
	 * @param  extRepositoryFileEntryKey the primary key of the Ext repository
	 *         file entry
	 * @return the discarded Ext repository file version (optionally
	 *         <code>null</code>)
	 * @throws PortalException if the Ext repository file entry's information
	 *         was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public ExtRepositoryFileVersion cancelCheckOut(
			String extRepositoryFileEntryKey)
		throws PortalException, SystemException;

	/**
	 * Checks in the Ext repository file entry. If a user has not checked out
	 * the Ext repository file entry, invoking this method results in no
	 * changes.
	 *
	 * @param  extRepositoryFileEntryKey the primary key of the ext repository
	 *         file entry
	 * @param  createMajorVersion whether to increase the major or minor version
	 *         number
	 * @param  changeLog the description of the changes being checked in
	 * @throws PortalException if the Ext repository file entry's information
	 *         was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public void checkInExtRepositoryFileEntry(
			String extRepositoryFileEntryKey, boolean createMajorVersion,
			String changeLog)
		throws PortalException, SystemException;

	/**
	 * Checks out the Ext repository file entry.
	 *
	 * @param  extRepositoryFileEntryKey the primary key of the Ext repository
	 *         file entry
	 * @return the checked out Ext repository file entry
	 * @throws PortalException if the Ext repository file entry's information
	 *         was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public ExtRepositoryFileEntry checkOutExtRepositoryFileEntry(
			String extRepositoryFileEntryKey)
		throws PortalException, SystemException;

	/**
	 * Copies the Ext repository file entry or folder to a different parent
	 * folder.
	 *
	 * @param  extRepositoryObjectType the Ext repository object's type (file or
	 *         folder)
	 * @param  extRepositoryFileEntryKey the primary key of the ext repository
	 *         object
	 * @param  newExtRepositoryFolderKey the primary key of the Ext repository
	 *         destination folder
	 * @param  newTitle the new name of the Ext repository object in the
	 *         destination folder
	 * @return the Ext repository file entry or folder
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public <T extends ExtRepositoryObject> T copyExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryFileEntryKey, String newExtRepositoryFolderKey,
			String newTitle)
		throws PortalException, SystemException;

	/**
	 * Deletes the Ext repository file entry or folder.
	 *
	 * @param  extRepositoryObjectType the Ext repository object's type (file or
	 *         folder)
	 * @param  extRepositoryObjectKey the primary key of the Ext repository
	 *         object
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteExtRepositoryObject(
			ExtRepositoryObjectType<? extends ExtRepositoryObject>
				extRepositoryObjectType,
			String extRepositoryObjectKey)
		throws PortalException, SystemException;

	public String getAuthType();

	/**
	 * Returns the content stream of the Ext repository file entry.
	 *
	 * @param  extRepositoryFileEntry the primary key of the Ext repository file
	 *         entry
	 * @return the content stream of the Ext repository file entry
	 * @throws PortalException if the Ext repository file entry's information
	 *         was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public InputStream getContentStream(
			ExtRepositoryFileEntry extRepositoryFileEntry)
		throws PortalException, SystemException;

	/**
	 * Returns the content stream of the Ext repository file version.
	 *
	 * @param  extRepositoryFileVersion the primary key of the ext repository
	 *         file version
	 * @return the content stream of the Ext repository file version
	 * @throws PortalException if the Ext repository file version's information
	 *         was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public InputStream getContentStream(
			ExtRepositoryFileVersion extRepositoryFileVersion)
		throws PortalException, SystemException;

	/**
	 * Returns the Ext repository file version of the file entry, identified by
	 * its version name (e.g. "1.0").
	 *
	 * @param  extRepositoryFileEntry the primary key of the Ext repository file
	 *         entry
	 * @param  version the Ext repository version name
	 * @return the Ext repository file version of the file entry
	 * @throws PortalException if the Ext repository file entry or version
	 *         information was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public ExtRepositoryFileVersion getExtRepositoryFileVersion(
			ExtRepositoryFileEntry extRepositoryFileEntry, String version)
		throws PortalException, SystemException;

	/**
	 * Translates the Ext repository file version key into an {@link
	 * ExtRepositoryFileVersionDescriptor}, describing the Ext repository file
	 * entry key and version name.
	 *
	 * @param  extRepositoryFileVersionKey the Ext repository file version's key
	 * @return the translated {@link ExtRepositoryFileVersionDescriptor}
	 */
	public ExtRepositoryFileVersionDescriptor
		getExtRepositoryFileVersionDescriptor(
			String extRepositoryFileVersionKey);

	/**
	 * Returns the Ext repository file versions of the Ext repository file
	 * entry. The list is ordered by version age, starting with the newest
	 * version at index <code>0</code>.
	 *
	 * @param  extRepositoryFileEntry the primary key of the Ext repository file
	 *         entry
	 * @return the Ext repository file versions of the Ext repository file entry
	 * @throws PortalException if the Ext repository file entry was invalid
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExtRepositoryFileVersion> getExtRepositoryFileVersions(
			ExtRepositoryFileEntry extRepositoryFileEntry)
		throws PortalException, SystemException;

	/**
	 * Returns the Ext repository file entry or folder matching the key.
	 *
	 * @param  extRepositoryObjectType the Ext repository object's type (file or
	 *         folder)
	 * @param  extRepositoryObjectKey the primary key of the Ext repository
	 *         object
	 * @return the Ext repository file entry or folder with the key
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public <T extends ExtRepositoryObject> T getExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryObjectKey)
		throws PortalException, SystemException;

	/**
	 * Returns the Ext repository object matching the name and parent folder.
	 *
	 * @param  extRepositoryObjectType the Ext repository object's type (file or
	 *         folder)
	 * @param  extRepositoryFolderKey the primary key of the Ext repository
	 *         object's parent folder
	 * @param  title the Ext repository object's name
	 * @return the Ext repository file entry or folder matching the name and
	 *         parent folder
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public <T extends ExtRepositoryObject> T getExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryFolderKey, String title)
		throws PortalException, SystemException;

	/**
	 * Returns the Ext repository objects in the folder. Depending on the type
	 * of Ext repository object requested, this method may return only Ext
	 * repository files, only Ext repository folders, or both Ext repository
	 * files and folders.
	 *
	 * @param  extRepositoryObjectType the type of Ext repository objects to
	 *         return
	 * @param  extRepositoryFolderKey the primary key of the Ext repository
	 *         folder to search
	 * @return the Ext repository objects contained in a given folder
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public <T extends ExtRepositoryObject> List<T> getExtRepositoryObjects(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryFolderKey)
		throws PortalException, SystemException;

	/**
	 * Returns the number of elements in the Ext repository folder matching the
	 * object type. Depending on the Ext repository object type requested, this
	 * method may only return the number of Ext repository files, the number of
	 * Ext repository folders, or the number of both Ext repository files and
	 * folders.
	 *
	 * @param  extRepositoryObjectType the Ext repository object type to count
	 * @param  extRepositoryFolderKey the primary key of the Ext repository
	 *         folder to search
	 * @return the number of elements in the Ext repository folder matching the
	 *         object type
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public int getExtRepositoryObjectsCount(
			ExtRepositoryObjectType<? extends ExtRepositoryObject>
				extRepositoryObjectType,
			String extRepositoryFolderKey)
		throws PortalException, SystemException;

	/**
	 * Returns the Ext repository parent folder of the Ext repository object.
	 *
	 * @param  extRepositoryObject the Ext repository object (file or folder)
	 * @return the Ext repository parent folder of the Ext repository object
	 * @throws PortalException if the Ext repository object's information was
	 *         invalid
	 * @throws SystemException if a system exception occurred
	 */
	public ExtRepositoryFolder getExtRepositoryParentFolder(
			ExtRepositoryObject extRepositoryObject)
		throws PortalException, SystemException;

	/**
	 * Returns the Liferay login value, which is mapped from the user's Ext
	 * repository login value. A Liferay login value can be a user ID, screen
	 * name, e-mail, etc, depending on the value returned by the {@link
	 * #getAuthType()} method. The format of the repository login is specific
	 * for each Ext repository implementation.
	 *
	 * @param  extRepositoryLogin the user's Ext repository login value
	 * @return the Liferay login value, which is mapped from the user's Ext
	 *         repository login value
	 */
	public String getLiferayLogin(String extRepositoryLogin);

	/**
	 * Returns the primary key of the Ext repository root folder.
	 *
	 * @return the primary key of the Ext repository root folder
	 * @throws PortalException if the Ext repository root folder could not be
	 *         accessed
	 * @throws SystemException if a system exception occurred
	 */
	public String getRootFolderKey() throws PortalException, SystemException;

	/**
	 * Returns the keys of the Ext repository subfolders stored inside the Ext
	 * repository folder.
	 *
	 * @param  extRepositoryFolderKey the primary key of the Ext repository
	 *         folder
	 * @param  recurse whether to recurse through each Ext repository subfolder
	 * @return the keys of the Ext repository subfolders stored inside the Ext
	 *         repository folder
	 * @throws PortalException if the Ext repository folder's information was
	 *         invalid
	 * @throws SystemException if a system exception occurred
	 */
	public List<String> getSubfolderKeys(
			String extRepositoryFolderKey, boolean recurse)
		throws PortalException, SystemException;

	/**
	 * Returns the supported configurations for the Ext repository, which may
	 * each have different supported parameters.
	 *
	 * @return the supported configurations
	 * @see    #getSupportedParameters()
	 */
	public String[] getSupportedConfigurations();

	/**
	 * Returns the supported Ext repository configuration parameters indexed by
	 * configuration type. These parameters are stored in the database when the
	 * Ext repository is configured and made available using the {@link
	 * #initRepository(UnicodeProperties, CredentialsProvider)} method.
	 *
	 * @return the supported Ext repository configuration parameters indexed by
	 *         configuration type
	 * @see    #getSupportedConfigurations()
	 */
	public String[][] getSupportedParameters();

	/**
	 * Checks the connectivity between Liferay Portal and the Ext repository.
	 *
	 * <p>
	 * This method uses the credentials provided by the {@link
	 * CredentialsProvider} object to authenticate to the Ext repository. In
	 * addition, the method acquires the Ext repository-specific configuration
	 * parameters from the type settings properties parameter. The parameters
	 * contained in this variable are indexed by the key names returned by the
	 * {@link #getSupportedParameters()} method. This method is called only
	 * once, the first time the repository is used.
	 * </p>
	 *
	 * @param  typeSettingsProperties the type settings properties
	 * @param  credentialsProvider the Liferay Portal username and password
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public void initRepository(
			UnicodeProperties typeSettingsProperties,
			CredentialsProvider credentialsProvider)
		throws PortalException, SystemException;

	/**
	 * Moves the Ext repository object to a different location. This method can
	 * also be used to rename Ext repository objects.
	 *
	 * @param  extRepositoryObjectType the Ext repository object's type (file or
	 *         folder)
	 * @param  extRepositoryObjectKey the primary key of the Ext repository
	 *         object
	 * @param  newExtRepositoryFolderKey the primary key of the Ext repository
	 *         destination folder
	 * @param  newTitle the new name of the Ext repository object (may not
	 *         change)
	 * @return the Ext repository object
	 * @throws PortalException if the Ext repository object could not be moved
	 *         or if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public <T extends ExtRepositoryObject> T moveExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryObjectKey, String newExtRepositoryFolderKey,
			String newTitle)
		throws PortalException, SystemException;

	/**
	 * Performs a search inside the Ext repository. The Ext repository objects to
	 * look for must fulfill the given {@link Query} as close as possible
	 * (possibly limited by the back end Ext repository constraints).
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
	 * @throws PortalException if the search failed or is invalid, or if a
	 *         portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public List<ExtRepositorySearchResult<?>> search(
			SearchContext searchContext, Query query,
			ExtRepositoryQueryMapper extRepositoryQueryMapper)
		throws PortalException, SystemException;

	/**
	 * Updates the Ext repository file entry's content.
	 *
	 * @param  extRepositoryFileEntryKey the primary key of the Ext repository
	 *         file entry
	 * @param  mimeType the Ext repository file entry's MIME type
	 * @param  inputStream the new Ext repository file entry's content
	 * @return the updated file entry
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public ExtRepositoryFileEntry updateExtRepositoryFileEntry(
			String extRepositoryFileEntryKey, String mimeType,
			InputStream inputStream)
		throws PortalException, SystemException;

}