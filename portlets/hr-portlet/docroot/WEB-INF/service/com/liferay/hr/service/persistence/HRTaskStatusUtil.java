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

import com.liferay.hr.model.HRTaskStatus;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the h r task status service. This utility wraps {@link HRTaskStatusPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRTaskStatusPersistence
 * @see HRTaskStatusPersistenceImpl
 * @generated
 */
public class HRTaskStatusUtil {
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
	public static void clearCache(HRTaskStatus hrTaskStatus) {
		getPersistence().clearCache(hrTaskStatus);
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
	public static List<HRTaskStatus> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HRTaskStatus> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HRTaskStatus> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static HRTaskStatus remove(HRTaskStatus hrTaskStatus)
		throws SystemException {
		return getPersistence().remove(hrTaskStatus);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static HRTaskStatus update(HRTaskStatus hrTaskStatus, boolean merge)
		throws SystemException {
		return getPersistence().update(hrTaskStatus, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static HRTaskStatus update(HRTaskStatus hrTaskStatus, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(hrTaskStatus, merge, serviceContext);
	}

	/**
	* Caches the h r task status in the entity cache if it is enabled.
	*
	* @param hrTaskStatus the h r task status to cache
	*/
	public static void cacheResult(
		com.liferay.hr.model.HRTaskStatus hrTaskStatus) {
		getPersistence().cacheResult(hrTaskStatus);
	}

	/**
	* Caches the h r task statuses in the entity cache if it is enabled.
	*
	* @param hrTaskStatuses the h r task statuses to cache
	*/
	public static void cacheResult(
		java.util.List<com.liferay.hr.model.HRTaskStatus> hrTaskStatuses) {
		getPersistence().cacheResult(hrTaskStatuses);
	}

	/**
	* Creates a new h r task status with the primary key. Does not add the h r task status to the database.
	*
	* @param hrTaskStatusId the primary key for the new h r task status
	* @return the new h r task status
	*/
	public static com.liferay.hr.model.HRTaskStatus create(long hrTaskStatusId) {
		return getPersistence().create(hrTaskStatusId);
	}

	/**
	* Removes the h r task status with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrTaskStatusId the primary key of the h r task status to remove
	* @return the h r task status that was removed
	* @throws com.liferay.hr.NoSuchTaskStatusException if a h r task status with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRTaskStatus remove(long hrTaskStatusId)
		throws com.liferay.hr.NoSuchTaskStatusException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(hrTaskStatusId);
	}

	public static com.liferay.hr.model.HRTaskStatus updateImpl(
		com.liferay.hr.model.HRTaskStatus hrTaskStatus, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(hrTaskStatus, merge);
	}

	/**
	* Finds the h r task status with the primary key or throws a {@link com.liferay.hr.NoSuchTaskStatusException} if it could not be found.
	*
	* @param hrTaskStatusId the primary key of the h r task status to find
	* @return the h r task status
	* @throws com.liferay.hr.NoSuchTaskStatusException if a h r task status with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRTaskStatus findByPrimaryKey(
		long hrTaskStatusId)
		throws com.liferay.hr.NoSuchTaskStatusException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(hrTaskStatusId);
	}

	/**
	* Finds the h r task status with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrTaskStatusId the primary key of the h r task status to find
	* @return the h r task status, or <code>null</code> if a h r task status with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRTaskStatus fetchByPrimaryKey(
		long hrTaskStatusId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(hrTaskStatusId);
	}

	/**
	* Finds the h r task status where groupId = &#63; and code = &#63; or throws a {@link com.liferay.hr.NoSuchTaskStatusException} if it could not be found.
	*
	* @param groupId the group ID to search with
	* @param code the code to search with
	* @return the matching h r task status
	* @throws com.liferay.hr.NoSuchTaskStatusException if a matching h r task status could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRTaskStatus findByG_C(long groupId,
		java.lang.String code)
		throws com.liferay.hr.NoSuchTaskStatusException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_C(groupId, code);
	}

	/**
	* Finds the h r task status where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID to search with
	* @param code the code to search with
	* @return the matching h r task status, or <code>null</code> if a matching h r task status could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRTaskStatus fetchByG_C(long groupId,
		java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByG_C(groupId, code);
	}

	/**
	* Finds the h r task status where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID to search with
	* @param code the code to search with
	* @return the matching h r task status, or <code>null</code> if a matching h r task status could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRTaskStatus fetchByG_C(long groupId,
		java.lang.String code, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByG_C(groupId, code, retrieveFromCache);
	}

	/**
	* Finds all the h r task statuses.
	*
	* @return the h r task statuses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRTaskStatus> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Finds a range of all the h r task statuses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r task statuses to return
	* @param end the upper bound of the range of h r task statuses to return (not inclusive)
	* @return the range of h r task statuses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRTaskStatus> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Finds an ordered range of all the h r task statuses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r task statuses to return
	* @param end the upper bound of the range of h r task statuses to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r task statuses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRTaskStatus> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes the h r task status where groupId = &#63; and code = &#63; from the database.
	*
	* @param groupId the group ID to search with
	* @param code the code to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_C(long groupId, java.lang.String code)
		throws com.liferay.hr.NoSuchTaskStatusException,
			com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_C(groupId, code);
	}

	/**
	* Removes all the h r task statuses from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the h r task statuses where groupId = &#63; and code = &#63;.
	*
	* @param groupId the group ID to search with
	* @param code the code to search with
	* @return the number of matching h r task statuses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_C(long groupId, java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_C(groupId, code);
	}

	/**
	* Counts all the h r task statuses.
	*
	* @return the number of h r task statuses
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static HRTaskStatusPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (HRTaskStatusPersistence)PortletBeanLocatorUtil.locate(com.liferay.hr.service.ClpSerializer.getServletContextName(),
					HRTaskStatusPersistence.class.getName());

			ReferenceRegistry.registerReference(HRTaskStatusUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(HRTaskStatusPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(HRTaskStatusUtil.class,
			"_persistence");
	}

	private static HRTaskStatusPersistence _persistence;
}