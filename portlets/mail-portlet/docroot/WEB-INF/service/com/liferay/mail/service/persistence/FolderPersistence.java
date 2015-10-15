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

package com.liferay.mail.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.mail.model.Folder;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the folder service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.mail.service.persistence.impl.FolderPersistenceImpl
 * @see FolderUtil
 * @generated
 */
@ProviderType
public interface FolderPersistence extends BasePersistence<Folder> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FolderUtil} to access the folder persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the folders where accountId = &#63;.
	*
	* @param accountId the account ID
	* @return the matching folders
	*/
	public java.util.List<Folder> findByAccountId(long accountId);

	/**
	* Returns a range of all the folders where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of folders
	* @param end the upper bound of the range of folders (not inclusive)
	* @return the range of matching folders
	*/
	public java.util.List<Folder> findByAccountId(long accountId, int start,
		int end);

	/**
	* Returns an ordered range of all the folders where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of folders
	* @param end the upper bound of the range of folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching folders
	*/
	public java.util.List<Folder> findByAccountId(long accountId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Folder> orderByComparator);

	/**
	* Returns an ordered range of all the folders where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of folders
	* @param end the upper bound of the range of folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching folders
	*/
	public java.util.List<Folder> findByAccountId(long accountId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Folder> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first folder in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching folder
	* @throws NoSuchFolderException if a matching folder could not be found
	*/
	public Folder findByAccountId_First(long accountId,
		com.liferay.portal.kernel.util.OrderByComparator<Folder> orderByComparator)
		throws com.liferay.mail.NoSuchFolderException;

	/**
	* Returns the first folder in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching folder, or <code>null</code> if a matching folder could not be found
	*/
	public Folder fetchByAccountId_First(long accountId,
		com.liferay.portal.kernel.util.OrderByComparator<Folder> orderByComparator);

	/**
	* Returns the last folder in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching folder
	* @throws NoSuchFolderException if a matching folder could not be found
	*/
	public Folder findByAccountId_Last(long accountId,
		com.liferay.portal.kernel.util.OrderByComparator<Folder> orderByComparator)
		throws com.liferay.mail.NoSuchFolderException;

	/**
	* Returns the last folder in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching folder, or <code>null</code> if a matching folder could not be found
	*/
	public Folder fetchByAccountId_Last(long accountId,
		com.liferay.portal.kernel.util.OrderByComparator<Folder> orderByComparator);

	/**
	* Returns the folders before and after the current folder in the ordered set where accountId = &#63;.
	*
	* @param folderId the primary key of the current folder
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next folder
	* @throws NoSuchFolderException if a folder with the primary key could not be found
	*/
	public Folder[] findByAccountId_PrevAndNext(long folderId, long accountId,
		com.liferay.portal.kernel.util.OrderByComparator<Folder> orderByComparator)
		throws com.liferay.mail.NoSuchFolderException;

	/**
	* Removes all the folders where accountId = &#63; from the database.
	*
	* @param accountId the account ID
	*/
	public void removeByAccountId(long accountId);

	/**
	* Returns the number of folders where accountId = &#63;.
	*
	* @param accountId the account ID
	* @return the number of matching folders
	*/
	public int countByAccountId(long accountId);

	/**
	* Returns the folder where accountId = &#63; and fullName = &#63; or throws a {@link NoSuchFolderException} if it could not be found.
	*
	* @param accountId the account ID
	* @param fullName the full name
	* @return the matching folder
	* @throws NoSuchFolderException if a matching folder could not be found
	*/
	public Folder findByA_F(long accountId, java.lang.String fullName)
		throws com.liferay.mail.NoSuchFolderException;

	/**
	* Returns the folder where accountId = &#63; and fullName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param accountId the account ID
	* @param fullName the full name
	* @return the matching folder, or <code>null</code> if a matching folder could not be found
	*/
	public Folder fetchByA_F(long accountId, java.lang.String fullName);

	/**
	* Returns the folder where accountId = &#63; and fullName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param accountId the account ID
	* @param fullName the full name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching folder, or <code>null</code> if a matching folder could not be found
	*/
	public Folder fetchByA_F(long accountId, java.lang.String fullName,
		boolean retrieveFromCache);

	/**
	* Removes the folder where accountId = &#63; and fullName = &#63; from the database.
	*
	* @param accountId the account ID
	* @param fullName the full name
	* @return the folder that was removed
	*/
	public Folder removeByA_F(long accountId, java.lang.String fullName)
		throws com.liferay.mail.NoSuchFolderException;

	/**
	* Returns the number of folders where accountId = &#63; and fullName = &#63;.
	*
	* @param accountId the account ID
	* @param fullName the full name
	* @return the number of matching folders
	*/
	public int countByA_F(long accountId, java.lang.String fullName);

	/**
	* Caches the folder in the entity cache if it is enabled.
	*
	* @param folder the folder
	*/
	public void cacheResult(Folder folder);

	/**
	* Caches the folders in the entity cache if it is enabled.
	*
	* @param folders the folders
	*/
	public void cacheResult(java.util.List<Folder> folders);

	/**
	* Creates a new folder with the primary key. Does not add the folder to the database.
	*
	* @param folderId the primary key for the new folder
	* @return the new folder
	*/
	public Folder create(long folderId);

	/**
	* Removes the folder with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param folderId the primary key of the folder
	* @return the folder that was removed
	* @throws NoSuchFolderException if a folder with the primary key could not be found
	*/
	public Folder remove(long folderId)
		throws com.liferay.mail.NoSuchFolderException;

	public Folder updateImpl(Folder folder);

	/**
	* Returns the folder with the primary key or throws a {@link NoSuchFolderException} if it could not be found.
	*
	* @param folderId the primary key of the folder
	* @return the folder
	* @throws NoSuchFolderException if a folder with the primary key could not be found
	*/
	public Folder findByPrimaryKey(long folderId)
		throws com.liferay.mail.NoSuchFolderException;

	/**
	* Returns the folder with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param folderId the primary key of the folder
	* @return the folder, or <code>null</code> if a folder with the primary key could not be found
	*/
	public Folder fetchByPrimaryKey(long folderId);

	@Override
	public java.util.Map<java.io.Serializable, Folder> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the folders.
	*
	* @return the folders
	*/
	public java.util.List<Folder> findAll();

	/**
	* Returns a range of all the folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of folders
	* @param end the upper bound of the range of folders (not inclusive)
	* @return the range of folders
	*/
	public java.util.List<Folder> findAll(int start, int end);

	/**
	* Returns an ordered range of all the folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of folders
	* @param end the upper bound of the range of folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of folders
	*/
	public java.util.List<Folder> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Folder> orderByComparator);

	/**
	* Returns an ordered range of all the folders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of folders
	* @param end the upper bound of the range of folders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of folders
	*/
	public java.util.List<Folder> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Folder> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the folders from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of folders.
	*
	* @return the number of folders
	*/
	public int countAll();
}