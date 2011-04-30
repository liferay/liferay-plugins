/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.hr.service.persistence;

import com.liferay.hr.model.HRProject;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the h r project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HRProjectPersistenceImpl
 * @see HRProjectUtil
 * @generated
 */
public interface HRProjectPersistence extends BasePersistence<HRProject> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HRProjectUtil} to access the h r project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the h r project in the entity cache if it is enabled.
	*
	* @param hrProject the h r project to cache
	*/
	public void cacheResult(com.liferay.hr.model.HRProject hrProject);

	/**
	* Caches the h r projects in the entity cache if it is enabled.
	*
	* @param hrProjects the h r projects to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.hr.model.HRProject> hrProjects);

	/**
	* Creates a new h r project with the primary key. Does not add the h r project to the database.
	*
	* @param hrProjectId the primary key for the new h r project
	* @return the new h r project
	*/
	public com.liferay.hr.model.HRProject create(long hrProjectId);

	/**
	* Removes the h r project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrProjectId the primary key of the h r project to remove
	* @return the h r project that was removed
	* @throws com.liferay.hr.NoSuchProjectException if a h r project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRProject remove(long hrProjectId)
		throws com.liferay.hr.NoSuchProjectException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.hr.model.HRProject updateImpl(
		com.liferay.hr.model.HRProject hrProject, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the h r project with the primary key or throws a {@link com.liferay.hr.NoSuchProjectException} if it could not be found.
	*
	* @param hrProjectId the primary key of the h r project to find
	* @return the h r project
	* @throws com.liferay.hr.NoSuchProjectException if a h r project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRProject findByPrimaryKey(long hrProjectId)
		throws com.liferay.hr.NoSuchProjectException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the h r project with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrProjectId the primary key of the h r project to find
	* @return the h r project, or <code>null</code> if a h r project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRProject fetchByPrimaryKey(long hrProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the h r projects.
	*
	* @return the h r projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRProject> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the h r projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r projects to return
	* @param end the upper bound of the range of h r projects to return (not inclusive)
	* @return the range of h r projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRProject> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the h r projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r projects to return
	* @param end the upper bound of the range of h r projects to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRProject> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the h r projects from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the h r projects.
	*
	* @return the number of h r projects
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public HRProject remove(HRProject hrProject) throws SystemException;
}