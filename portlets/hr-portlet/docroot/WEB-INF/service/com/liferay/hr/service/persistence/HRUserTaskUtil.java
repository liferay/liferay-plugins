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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the h r user task service. This utility wraps {@link HRUserTaskPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRUserTaskPersistence
 * @see HRUserTaskPersistenceImpl
 * @generated
 */
public class HRUserTaskUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(HRUserTask hrUserTask) {
		getPersistence().clearCache(hrUserTask);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<HRUserTask> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HRUserTask> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HRUserTask> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static HRUserTask remove(HRUserTask hrUserTask)
		throws SystemException {
		return getPersistence().remove(hrUserTask);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static HRUserTask update(HRUserTask hrUserTask, boolean merge)
		throws SystemException {
		return getPersistence().update(hrUserTask, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static HRUserTask update(HRUserTask hrUserTask, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(hrUserTask, merge, serviceContext);
	}

	/**
	* Caches the h r user task in the entity cache if it is enabled.
	*
	* @param hrUserTask the h r user task
	*/
	public static void cacheResult(com.liferay.hr.model.HRUserTask hrUserTask) {
		getPersistence().cacheResult(hrUserTask);
	}

	/**
	* Caches the h r user tasks in the entity cache if it is enabled.
	*
	* @param hrUserTasks the h r user tasks
	*/
	public static void cacheResult(
		java.util.List<com.liferay.hr.model.HRUserTask> hrUserTasks) {
		getPersistence().cacheResult(hrUserTasks);
	}

	/**
	* Creates a new h r user task with the primary key. Does not add the h r user task to the database.
	*
	* @param hrUserTaskId the primary key for the new h r user task
	* @return the new h r user task
	*/
	public static com.liferay.hr.model.HRUserTask create(long hrUserTaskId) {
		return getPersistence().create(hrUserTaskId);
	}

	/**
	* Removes the h r user task with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrUserTaskId the primary key of the h r user task
	* @return the h r user task that was removed
	* @throws com.liferay.hr.NoSuchUserTaskException if a h r user task with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRUserTask remove(long hrUserTaskId)
		throws com.liferay.hr.NoSuchUserTaskException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(hrUserTaskId);
	}

	public static com.liferay.hr.model.HRUserTask updateImpl(
		com.liferay.hr.model.HRUserTask hrUserTask, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(hrUserTask, merge);
	}

	/**
	* Returns the h r user task with the primary key or throws a {@link com.liferay.hr.NoSuchUserTaskException} if it could not be found.
	*
	* @param hrUserTaskId the primary key of the h r user task
	* @return the h r user task
	* @throws com.liferay.hr.NoSuchUserTaskException if a h r user task with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRUserTask findByPrimaryKey(
		long hrUserTaskId)
		throws com.liferay.hr.NoSuchUserTaskException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(hrUserTaskId);
	}

	/**
	* Returns the h r user task with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrUserTaskId the primary key of the h r user task
	* @return the h r user task, or <code>null</code> if a h r user task with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRUserTask fetchByPrimaryKey(
		long hrUserTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(hrUserTaskId);
	}

	/**
	* Returns all the h r user tasks.
	*
	* @return the h r user tasks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRUserTask> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.hr.model.HRUserTask> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.hr.model.HRUserTask> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the h r user tasks from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of h r user tasks.
	*
	* @return the number of h r user tasks
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static HRUserTaskPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (HRUserTaskPersistence)PortletBeanLocatorUtil.locate(com.liferay.hr.service.ClpSerializer.getServletContextName(),
					HRUserTaskPersistence.class.getName());

			ReferenceRegistry.registerReference(HRUserTaskUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(HRUserTaskPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(HRUserTaskUtil.class, "_persistence");
	}

	private static HRUserTaskPersistence _persistence;
}