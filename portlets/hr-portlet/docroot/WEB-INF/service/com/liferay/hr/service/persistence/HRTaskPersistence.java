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

import com.liferay.hr.model.HRTask;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the h r task service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HRTaskPersistenceImpl
 * @see HRTaskUtil
 * @generated
 */
public interface HRTaskPersistence extends BasePersistence<HRTask> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HRTaskUtil} to access the h r task persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the h r task in the entity cache if it is enabled.
	*
	* @param hrTask the h r task to cache
	*/
	public void cacheResult(com.liferay.hr.model.HRTask hrTask);

	/**
	* Caches the h r tasks in the entity cache if it is enabled.
	*
	* @param hrTasks the h r tasks to cache
	*/
	public void cacheResult(java.util.List<com.liferay.hr.model.HRTask> hrTasks);

	/**
	* Creates a new h r task with the primary key. Does not add the h r task to the database.
	*
	* @param hrTaskId the primary key for the new h r task
	* @return the new h r task
	*/
	public com.liferay.hr.model.HRTask create(long hrTaskId);

	/**
	* Removes the h r task with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrTaskId the primary key of the h r task to remove
	* @return the h r task that was removed
	* @throws com.liferay.hr.NoSuchTaskException if a h r task with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRTask remove(long hrTaskId)
		throws com.liferay.hr.NoSuchTaskException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.hr.model.HRTask updateImpl(
		com.liferay.hr.model.HRTask hrTask, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the h r task with the primary key or throws a {@link com.liferay.hr.NoSuchTaskException} if it could not be found.
	*
	* @param hrTaskId the primary key of the h r task to find
	* @return the h r task
	* @throws com.liferay.hr.NoSuchTaskException if a h r task with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRTask findByPrimaryKey(long hrTaskId)
		throws com.liferay.hr.NoSuchTaskException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the h r task with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrTaskId the primary key of the h r task to find
	* @return the h r task, or <code>null</code> if a h r task with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRTask fetchByPrimaryKey(long hrTaskId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the h r tasks.
	*
	* @return the h r tasks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRTask> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the h r tasks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r tasks to return
	* @param end the upper bound of the range of h r tasks to return (not inclusive)
	* @return the range of h r tasks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRTask> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the h r tasks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r tasks to return
	* @param end the upper bound of the range of h r tasks to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r tasks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRTask> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the h r tasks from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the h r tasks.
	*
	* @return the number of h r tasks
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public HRTask remove(HRTask hrTask) throws SystemException;
}