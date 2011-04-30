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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the h r task service. This utility wraps {@link HRTaskPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HRTaskPersistence
 * @see HRTaskPersistenceImpl
 * @generated
 */
public class HRTaskUtil {
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
	public static void clearCache(HRTask hrTask) {
		getPersistence().clearCache(hrTask);
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
	public static List<HRTask> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HRTask> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HRTask> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static HRTask remove(HRTask hrTask) throws SystemException {
		return getPersistence().remove(hrTask);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static HRTask update(HRTask hrTask, boolean merge)
		throws SystemException {
		return getPersistence().update(hrTask, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static HRTask update(HRTask hrTask, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(hrTask, merge, serviceContext);
	}

	/**
	* Caches the h r task in the entity cache if it is enabled.
	*
	* @param hrTask the h r task to cache
	*/
	public static void cacheResult(com.liferay.hr.model.HRTask hrTask) {
		getPersistence().cacheResult(hrTask);
	}

	/**
	* Caches the h r tasks in the entity cache if it is enabled.
	*
	* @param hrTasks the h r tasks to cache
	*/
	public static void cacheResult(
		java.util.List<com.liferay.hr.model.HRTask> hrTasks) {
		getPersistence().cacheResult(hrTasks);
	}

	/**
	* Creates a new h r task with the primary key. Does not add the h r task to the database.
	*
	* @param hrTaskId the primary key for the new h r task
	* @return the new h r task
	*/
	public static com.liferay.hr.model.HRTask create(long hrTaskId) {
		return getPersistence().create(hrTaskId);
	}

	/**
	* Removes the h r task with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrTaskId the primary key of the h r task to remove
	* @return the h r task that was removed
	* @throws com.liferay.hr.NoSuchTaskException if a h r task with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRTask remove(long hrTaskId)
		throws com.liferay.hr.NoSuchTaskException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(hrTaskId);
	}

	public static com.liferay.hr.model.HRTask updateImpl(
		com.liferay.hr.model.HRTask hrTask, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(hrTask, merge);
	}

	/**
	* Finds the h r task with the primary key or throws a {@link com.liferay.hr.NoSuchTaskException} if it could not be found.
	*
	* @param hrTaskId the primary key of the h r task to find
	* @return the h r task
	* @throws com.liferay.hr.NoSuchTaskException if a h r task with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRTask findByPrimaryKey(long hrTaskId)
		throws com.liferay.hr.NoSuchTaskException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(hrTaskId);
	}

	/**
	* Finds the h r task with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrTaskId the primary key of the h r task to find
	* @return the h r task, or <code>null</code> if a h r task with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRTask fetchByPrimaryKey(long hrTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(hrTaskId);
	}

	/**
	* Finds all the h r tasks.
	*
	* @return the h r tasks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRTask> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.hr.model.HRTask> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.hr.model.HRTask> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the h r tasks from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the h r tasks.
	*
	* @return the number of h r tasks
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static HRTaskPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (HRTaskPersistence)PortletBeanLocatorUtil.locate(com.liferay.hr.service.ClpSerializer.getServletContextName(),
					HRTaskPersistence.class.getName());

			ReferenceRegistry.registerReference(HRTaskUtil.class, "_persistence");
		}

		return _persistence;
	}

	public void setPersistence(HRTaskPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(HRTaskUtil.class, "_persistence");
	}

	private static HRTaskPersistence _persistence;
}