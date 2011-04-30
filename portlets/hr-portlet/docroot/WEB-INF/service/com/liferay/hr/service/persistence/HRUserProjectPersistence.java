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

import com.liferay.hr.model.HRUserProject;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the h r user project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRUserProjectPersistenceImpl
 * @see HRUserProjectUtil
 * @generated
 */
public interface HRUserProjectPersistence extends BasePersistence<HRUserProject> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HRUserProjectUtil} to access the h r user project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the h r user project in the entity cache if it is enabled.
	*
	* @param hrUserProject the h r user project to cache
	*/
	public void cacheResult(com.liferay.hr.model.HRUserProject hrUserProject);

	/**
	* Caches the h r user projects in the entity cache if it is enabled.
	*
	* @param hrUserProjects the h r user projects to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.hr.model.HRUserProject> hrUserProjects);

	/**
	* Creates a new h r user project with the primary key. Does not add the h r user project to the database.
	*
	* @param hrUserProjectId the primary key for the new h r user project
	* @return the new h r user project
	*/
	public com.liferay.hr.model.HRUserProject create(long hrUserProjectId);

	/**
	* Removes the h r user project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrUserProjectId the primary key of the h r user project to remove
	* @return the h r user project that was removed
	* @throws com.liferay.hr.NoSuchUserProjectException if a h r user project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRUserProject remove(long hrUserProjectId)
		throws com.liferay.hr.NoSuchUserProjectException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.hr.model.HRUserProject updateImpl(
		com.liferay.hr.model.HRUserProject hrUserProject, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the h r user project with the primary key or throws a {@link com.liferay.hr.NoSuchUserProjectException} if it could not be found.
	*
	* @param hrUserProjectId the primary key of the h r user project to find
	* @return the h r user project
	* @throws com.liferay.hr.NoSuchUserProjectException if a h r user project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRUserProject findByPrimaryKey(
		long hrUserProjectId)
		throws com.liferay.hr.NoSuchUserProjectException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the h r user project with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrUserProjectId the primary key of the h r user project to find
	* @return the h r user project, or <code>null</code> if a h r user project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRUserProject fetchByPrimaryKey(
		long hrUserProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the h r user projects.
	*
	* @return the h r user projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRUserProject> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the h r user projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r user projects to return
	* @param end the upper bound of the range of h r user projects to return (not inclusive)
	* @return the range of h r user projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRUserProject> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the h r user projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r user projects to return
	* @param end the upper bound of the range of h r user projects to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r user projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRUserProject> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the h r user projects from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the h r user projects.
	*
	* @return the number of h r user projects
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public HRUserProject remove(HRUserProject hrUserProject)
		throws SystemException;
}