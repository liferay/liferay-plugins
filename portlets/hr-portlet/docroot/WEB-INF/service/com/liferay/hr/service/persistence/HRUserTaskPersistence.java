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

import com.liferay.hr.model.HRUserTask;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the h r user task service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRUserTaskPersistenceImpl
 * @see HRUserTaskUtil
 * @generated
 */
public interface HRUserTaskPersistence extends BasePersistence<HRUserTask> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HRUserTaskUtil} to access the h r user task persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the h r user task in the entity cache if it is enabled.
	*
	* @param hrUserTask the h r user task
	*/
	public void cacheResult(com.liferay.hr.model.HRUserTask hrUserTask);

	/**
	* Caches the h r user tasks in the entity cache if it is enabled.
	*
	* @param hrUserTasks the h r user tasks
	*/
	public void cacheResult(
		java.util.List<com.liferay.hr.model.HRUserTask> hrUserTasks);

	/**
	* Creates a new h r user task with the primary key. Does not add the h r user task to the database.
	*
	* @param hrUserTaskId the primary key for the new h r user task
	* @return the new h r user task
	*/
	public com.liferay.hr.model.HRUserTask create(long hrUserTaskId);

	/**
	* Removes the h r user task with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrUserTaskId the primary key of the h r user task
	* @return the h r user task that was removed
	* @throws com.liferay.hr.NoSuchUserTaskException if a h r user task with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRUserTask remove(long hrUserTaskId)
		throws com.liferay.hr.NoSuchUserTaskException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.hr.model.HRUserTask updateImpl(
		com.liferay.hr.model.HRUserTask hrUserTask, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r user task with the primary key or throws a {@link com.liferay.hr.NoSuchUserTaskException} if it could not be found.
	*
	* @param hrUserTaskId the primary key of the h r user task
	* @return the h r user task
	* @throws com.liferay.hr.NoSuchUserTaskException if a h r user task with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRUserTask findByPrimaryKey(long hrUserTaskId)
		throws com.liferay.hr.NoSuchUserTaskException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r user task with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrUserTaskId the primary key of the h r user task
	* @return the h r user task, or <code>null</code> if a h r user task with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRUserTask fetchByPrimaryKey(long hrUserTaskId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the h r user tasks.
	*
	* @return the h r user tasks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRUserTask> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the h r user tasks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r user tasks
	* @param end the upper bound of the range of h r user tasks (not inclusive)
	* @return the range of h r user tasks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRUserTask> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the h r user tasks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r user tasks
	* @param end the upper bound of the range of h r user tasks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r user tasks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRUserTask> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the h r user tasks from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of h r user tasks.
	*
	* @return the number of h r user tasks
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public HRUserTask remove(HRUserTask hrUserTask) throws SystemException;
}