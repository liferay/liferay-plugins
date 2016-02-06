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

package com.liferay.so.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.liferay.so.exception.NoSuchProjectsEntryException;
import com.liferay.so.model.ProjectsEntry;

/**
 * The persistence interface for the projects entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.so.service.persistence.impl.ProjectsEntryPersistenceImpl
 * @see ProjectsEntryUtil
 * @generated
 */
@ProviderType
public interface ProjectsEntryPersistence extends BasePersistence<ProjectsEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProjectsEntryUtil} to access the projects entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the projects entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching projects entries
	*/
	public java.util.List<ProjectsEntry> findByUserId(long userId);

	/**
	* Returns a range of all the projects entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of projects entries
	* @param end the upper bound of the range of projects entries (not inclusive)
	* @return the range of matching projects entries
	*/
	public java.util.List<ProjectsEntry> findByUserId(long userId, int start,
		int end);

	/**
	* Returns an ordered range of all the projects entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of projects entries
	* @param end the upper bound of the range of projects entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching projects entries
	*/
	public java.util.List<ProjectsEntry> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProjectsEntry> orderByComparator);

	/**
	* Returns an ordered range of all the projects entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of projects entries
	* @param end the upper bound of the range of projects entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching projects entries
	*/
	public java.util.List<ProjectsEntry> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProjectsEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first projects entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching projects entry
	* @throws NoSuchProjectsEntryException if a matching projects entry could not be found
	*/
	public ProjectsEntry findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<ProjectsEntry> orderByComparator)
		throws NoSuchProjectsEntryException;

	/**
	* Returns the first projects entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching projects entry, or <code>null</code> if a matching projects entry could not be found
	*/
	public ProjectsEntry fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<ProjectsEntry> orderByComparator);

	/**
	* Returns the last projects entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching projects entry
	* @throws NoSuchProjectsEntryException if a matching projects entry could not be found
	*/
	public ProjectsEntry findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<ProjectsEntry> orderByComparator)
		throws NoSuchProjectsEntryException;

	/**
	* Returns the last projects entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching projects entry, or <code>null</code> if a matching projects entry could not be found
	*/
	public ProjectsEntry fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<ProjectsEntry> orderByComparator);

	/**
	* Returns the projects entries before and after the current projects entry in the ordered set where userId = &#63;.
	*
	* @param projectsEntryId the primary key of the current projects entry
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next projects entry
	* @throws NoSuchProjectsEntryException if a projects entry with the primary key could not be found
	*/
	public ProjectsEntry[] findByUserId_PrevAndNext(long projectsEntryId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<ProjectsEntry> orderByComparator)
		throws NoSuchProjectsEntryException;

	/**
	* Removes all the projects entries where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUserId(long userId);

	/**
	* Returns the number of projects entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching projects entries
	*/
	public int countByUserId(long userId);

	/**
	* Caches the projects entry in the entity cache if it is enabled.
	*
	* @param projectsEntry the projects entry
	*/
	public void cacheResult(ProjectsEntry projectsEntry);

	/**
	* Caches the projects entries in the entity cache if it is enabled.
	*
	* @param projectsEntries the projects entries
	*/
	public void cacheResult(java.util.List<ProjectsEntry> projectsEntries);

	/**
	* Creates a new projects entry with the primary key. Does not add the projects entry to the database.
	*
	* @param projectsEntryId the primary key for the new projects entry
	* @return the new projects entry
	*/
	public ProjectsEntry create(long projectsEntryId);

	/**
	* Removes the projects entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectsEntryId the primary key of the projects entry
	* @return the projects entry that was removed
	* @throws NoSuchProjectsEntryException if a projects entry with the primary key could not be found
	*/
	public ProjectsEntry remove(long projectsEntryId)
		throws NoSuchProjectsEntryException;

	public ProjectsEntry updateImpl(ProjectsEntry projectsEntry);

	/**
	* Returns the projects entry with the primary key or throws a {@link NoSuchProjectsEntryException} if it could not be found.
	*
	* @param projectsEntryId the primary key of the projects entry
	* @return the projects entry
	* @throws NoSuchProjectsEntryException if a projects entry with the primary key could not be found
	*/
	public ProjectsEntry findByPrimaryKey(long projectsEntryId)
		throws NoSuchProjectsEntryException;

	/**
	* Returns the projects entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param projectsEntryId the primary key of the projects entry
	* @return the projects entry, or <code>null</code> if a projects entry with the primary key could not be found
	*/
	public ProjectsEntry fetchByPrimaryKey(long projectsEntryId);

	@Override
	public java.util.Map<java.io.Serializable, ProjectsEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the projects entries.
	*
	* @return the projects entries
	*/
	public java.util.List<ProjectsEntry> findAll();

	/**
	* Returns a range of all the projects entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projects entries
	* @param end the upper bound of the range of projects entries (not inclusive)
	* @return the range of projects entries
	*/
	public java.util.List<ProjectsEntry> findAll(int start, int end);

	/**
	* Returns an ordered range of all the projects entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projects entries
	* @param end the upper bound of the range of projects entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of projects entries
	*/
	public java.util.List<ProjectsEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProjectsEntry> orderByComparator);

	/**
	* Returns an ordered range of all the projects entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projects entries
	* @param end the upper bound of the range of projects entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of projects entries
	*/
	public java.util.List<ProjectsEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProjectsEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the projects entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of projects entries.
	*
	* @return the number of projects entries
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}