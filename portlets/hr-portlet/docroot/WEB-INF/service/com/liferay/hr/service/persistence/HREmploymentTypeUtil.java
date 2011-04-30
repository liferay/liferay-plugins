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

import com.liferay.hr.model.HREmploymentType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the h r employment type service. This utility wraps {@link HREmploymentTypePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HREmploymentTypePersistence
 * @see HREmploymentTypePersistenceImpl
 * @generated
 */
public class HREmploymentTypeUtil {
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
	public static void clearCache(HREmploymentType hrEmploymentType) {
		getPersistence().clearCache(hrEmploymentType);
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
	public static List<HREmploymentType> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HREmploymentType> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HREmploymentType> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static HREmploymentType remove(HREmploymentType hrEmploymentType)
		throws SystemException {
		return getPersistence().remove(hrEmploymentType);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static HREmploymentType update(HREmploymentType hrEmploymentType,
		boolean merge) throws SystemException {
		return getPersistence().update(hrEmploymentType, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static HREmploymentType update(HREmploymentType hrEmploymentType,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(hrEmploymentType, merge, serviceContext);
	}

	/**
	* Caches the h r employment type in the entity cache if it is enabled.
	*
	* @param hrEmploymentType the h r employment type to cache
	*/
	public static void cacheResult(
		com.liferay.hr.model.HREmploymentType hrEmploymentType) {
		getPersistence().cacheResult(hrEmploymentType);
	}

	/**
	* Caches the h r employment types in the entity cache if it is enabled.
	*
	* @param hrEmploymentTypes the h r employment types to cache
	*/
	public static void cacheResult(
		java.util.List<com.liferay.hr.model.HREmploymentType> hrEmploymentTypes) {
		getPersistence().cacheResult(hrEmploymentTypes);
	}

	/**
	* Creates a new h r employment type with the primary key. Does not add the h r employment type to the database.
	*
	* @param hrEmploymentTypeId the primary key for the new h r employment type
	* @return the new h r employment type
	*/
	public static com.liferay.hr.model.HREmploymentType create(
		long hrEmploymentTypeId) {
		return getPersistence().create(hrEmploymentTypeId);
	}

	/**
	* Removes the h r employment type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrEmploymentTypeId the primary key of the h r employment type to remove
	* @return the h r employment type that was removed
	* @throws com.liferay.hr.NoSuchEmploymentTypeException if a h r employment type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HREmploymentType remove(
		long hrEmploymentTypeId)
		throws com.liferay.hr.NoSuchEmploymentTypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(hrEmploymentTypeId);
	}

	public static com.liferay.hr.model.HREmploymentType updateImpl(
		com.liferay.hr.model.HREmploymentType hrEmploymentType, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(hrEmploymentType, merge);
	}

	/**
	* Finds the h r employment type with the primary key or throws a {@link com.liferay.hr.NoSuchEmploymentTypeException} if it could not be found.
	*
	* @param hrEmploymentTypeId the primary key of the h r employment type to find
	* @return the h r employment type
	* @throws com.liferay.hr.NoSuchEmploymentTypeException if a h r employment type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HREmploymentType findByPrimaryKey(
		long hrEmploymentTypeId)
		throws com.liferay.hr.NoSuchEmploymentTypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(hrEmploymentTypeId);
	}

	/**
	* Finds the h r employment type with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrEmploymentTypeId the primary key of the h r employment type to find
	* @return the h r employment type, or <code>null</code> if a h r employment type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HREmploymentType fetchByPrimaryKey(
		long hrEmploymentTypeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(hrEmploymentTypeId);
	}

	/**
	* Finds the h r employment type where groupId = &#63; and code = &#63; or throws a {@link com.liferay.hr.NoSuchEmploymentTypeException} if it could not be found.
	*
	* @param groupId the group ID to search with
	* @param code the code to search with
	* @return the matching h r employment type
	* @throws com.liferay.hr.NoSuchEmploymentTypeException if a matching h r employment type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HREmploymentType findByG_C(
		long groupId, java.lang.String code)
		throws com.liferay.hr.NoSuchEmploymentTypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_C(groupId, code);
	}

	/**
	* Finds the h r employment type where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID to search with
	* @param code the code to search with
	* @return the matching h r employment type, or <code>null</code> if a matching h r employment type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HREmploymentType fetchByG_C(
		long groupId, java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByG_C(groupId, code);
	}

	/**
	* Finds the h r employment type where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID to search with
	* @param code the code to search with
	* @return the matching h r employment type, or <code>null</code> if a matching h r employment type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HREmploymentType fetchByG_C(
		long groupId, java.lang.String code, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByG_C(groupId, code, retrieveFromCache);
	}

	/**
	* Finds all the h r employment types.
	*
	* @return the h r employment types
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HREmploymentType> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Finds a range of all the h r employment types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r employment types to return
	* @param end the upper bound of the range of h r employment types to return (not inclusive)
	* @return the range of h r employment types
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HREmploymentType> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Finds an ordered range of all the h r employment types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r employment types to return
	* @param end the upper bound of the range of h r employment types to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r employment types
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HREmploymentType> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes the h r employment type where groupId = &#63; and code = &#63; from the database.
	*
	* @param groupId the group ID to search with
	* @param code the code to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_C(long groupId, java.lang.String code)
		throws com.liferay.hr.NoSuchEmploymentTypeException,
			com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_C(groupId, code);
	}

	/**
	* Removes all the h r employment types from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the h r employment types where groupId = &#63; and code = &#63;.
	*
	* @param groupId the group ID to search with
	* @param code the code to search with
	* @return the number of matching h r employment types
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_C(long groupId, java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_C(groupId, code);
	}

	/**
	* Counts all the h r employment types.
	*
	* @return the number of h r employment types
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static HREmploymentTypePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (HREmploymentTypePersistence)PortletBeanLocatorUtil.locate(com.liferay.hr.service.ClpSerializer.getServletContextName(),
					HREmploymentTypePersistence.class.getName());

			ReferenceRegistry.registerReference(HREmploymentTypeUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(HREmploymentTypePersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(HREmploymentTypeUtil.class,
			"_persistence");
	}

	private static HREmploymentTypePersistence _persistence;
}