/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.so.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.so.model.ProjectsEntry;

/**
 * The persistence interface for the projects entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProjectsEntryPersistenceImpl
 * @see ProjectsEntryUtil
 * @generated
 */
public interface ProjectsEntryPersistence extends BasePersistence<ProjectsEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProjectsEntryUtil} to access the projects entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the projects entry in the entity cache if it is enabled.
	*
	* @param projectsEntry the projects entry
	*/
	public void cacheResult(com.liferay.so.model.ProjectsEntry projectsEntry);

	/**
	* Caches the projects entries in the entity cache if it is enabled.
	*
	* @param projectsEntries the projects entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.so.model.ProjectsEntry> projectsEntries);

	/**
	* Creates a new projects entry with the primary key. Does not add the projects entry to the database.
	*
	* @param projectsEntryId the primary key for the new projects entry
	* @return the new projects entry
	*/
	public com.liferay.so.model.ProjectsEntry create(long projectsEntryId);

	/**
	* Removes the projects entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectsEntryId the primary key of the projects entry
	* @return the projects entry that was removed
	* @throws com.liferay.so.NoSuchProjectsEntryException if a projects entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.ProjectsEntry remove(long projectsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchProjectsEntryException;

	public com.liferay.so.model.ProjectsEntry updateImpl(
		com.liferay.so.model.ProjectsEntry projectsEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the projects entry with the primary key or throws a {@link com.liferay.so.NoSuchProjectsEntryException} if it could not be found.
	*
	* @param projectsEntryId the primary key of the projects entry
	* @return the projects entry
	* @throws com.liferay.so.NoSuchProjectsEntryException if a projects entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.ProjectsEntry findByPrimaryKey(
		long projectsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchProjectsEntryException;

	/**
	* Returns the projects entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param projectsEntryId the primary key of the projects entry
	* @return the projects entry, or <code>null</code> if a projects entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.ProjectsEntry fetchByPrimaryKey(
		long projectsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the projects entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching projects entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.model.ProjectsEntry> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the projects entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of projects entries
	* @param end the upper bound of the range of projects entries (not inclusive)
	* @return the range of matching projects entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.model.ProjectsEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the projects entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of projects entries
	* @param end the upper bound of the range of projects entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching projects entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.model.ProjectsEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first projects entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching projects entry
	* @throws com.liferay.so.NoSuchProjectsEntryException if a matching projects entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.ProjectsEntry findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchProjectsEntryException;

	/**
	* Returns the first projects entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching projects entry, or <code>null</code> if a matching projects entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.ProjectsEntry fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last projects entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching projects entry
	* @throws com.liferay.so.NoSuchProjectsEntryException if a matching projects entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.ProjectsEntry findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchProjectsEntryException;

	/**
	* Returns the last projects entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching projects entry, or <code>null</code> if a matching projects entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.ProjectsEntry fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the projects entries before and after the current projects entry in the ordered set where userId = &#63;.
	*
	* @param projectsEntryId the primary key of the current projects entry
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next projects entry
	* @throws com.liferay.so.NoSuchProjectsEntryException if a projects entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.ProjectsEntry[] findByUserId_PrevAndNext(
		long projectsEntryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchProjectsEntryException;

	/**
	* Returns all the projects entries.
	*
	* @return the projects entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.model.ProjectsEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the projects entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of projects entries
	* @param end the upper bound of the range of projects entries (not inclusive)
	* @return the range of projects entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.model.ProjectsEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the projects entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of projects entries
	* @param end the upper bound of the range of projects entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of projects entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.model.ProjectsEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the projects entries where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the projects entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of projects entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching projects entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of projects entries.
	*
	* @return the number of projects entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}