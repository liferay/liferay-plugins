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

import com.liferay.hr.model.HRWageType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the h r wage type service. This utility wraps {@link HRWageTypePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRWageTypePersistence
 * @see HRWageTypePersistenceImpl
 * @generated
 */
public class HRWageTypeUtil {
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
	public static void clearCache(HRWageType hrWageType) {
		getPersistence().clearCache(hrWageType);
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
	public static List<HRWageType> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HRWageType> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HRWageType> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static HRWageType remove(HRWageType hrWageType)
		throws SystemException {
		return getPersistence().remove(hrWageType);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static HRWageType update(HRWageType hrWageType, boolean merge)
		throws SystemException {
		return getPersistence().update(hrWageType, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static HRWageType update(HRWageType hrWageType, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(hrWageType, merge, serviceContext);
	}

	/**
	* Caches the h r wage type in the entity cache if it is enabled.
	*
	* @param hrWageType the h r wage type
	*/
	public static void cacheResult(com.liferay.hr.model.HRWageType hrWageType) {
		getPersistence().cacheResult(hrWageType);
	}

	/**
	* Caches the h r wage types in the entity cache if it is enabled.
	*
	* @param hrWageTypes the h r wage types
	*/
	public static void cacheResult(
		java.util.List<com.liferay.hr.model.HRWageType> hrWageTypes) {
		getPersistence().cacheResult(hrWageTypes);
	}

	/**
	* Creates a new h r wage type with the primary key. Does not add the h r wage type to the database.
	*
	* @param hrWageTypeId the primary key for the new h r wage type
	* @return the new h r wage type
	*/
	public static com.liferay.hr.model.HRWageType create(long hrWageTypeId) {
		return getPersistence().create(hrWageTypeId);
	}

	/**
	* Removes the h r wage type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrWageTypeId the primary key of the h r wage type
	* @return the h r wage type that was removed
	* @throws com.liferay.hr.NoSuchWageTypeException if a h r wage type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRWageType remove(long hrWageTypeId)
		throws com.liferay.hr.NoSuchWageTypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(hrWageTypeId);
	}

	public static com.liferay.hr.model.HRWageType updateImpl(
		com.liferay.hr.model.HRWageType hrWageType, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(hrWageType, merge);
	}

	/**
	* Returns the h r wage type with the primary key or throws a {@link com.liferay.hr.NoSuchWageTypeException} if it could not be found.
	*
	* @param hrWageTypeId the primary key of the h r wage type
	* @return the h r wage type
	* @throws com.liferay.hr.NoSuchWageTypeException if a h r wage type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRWageType findByPrimaryKey(
		long hrWageTypeId)
		throws com.liferay.hr.NoSuchWageTypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(hrWageTypeId);
	}

	/**
	* Returns the h r wage type with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrWageTypeId the primary key of the h r wage type
	* @return the h r wage type, or <code>null</code> if a h r wage type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRWageType fetchByPrimaryKey(
		long hrWageTypeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(hrWageTypeId);
	}

	/**
	* Returns the h r wage type where groupId = &#63; and code = &#63; or throws a {@link com.liferay.hr.NoSuchWageTypeException} if it could not be found.
	*
	* @param groupId the group ID
	* @param code the code
	* @return the matching h r wage type
	* @throws com.liferay.hr.NoSuchWageTypeException if a matching h r wage type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRWageType findByG_C(long groupId,
		java.lang.String code)
		throws com.liferay.hr.NoSuchWageTypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_C(groupId, code);
	}

	/**
	* Returns the h r wage type where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param code the code
	* @return the matching h r wage type, or <code>null</code> if a matching h r wage type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRWageType fetchByG_C(long groupId,
		java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByG_C(groupId, code);
	}

	/**
	* Returns the h r wage type where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param code the code
	* @return the matching h r wage type, or <code>null</code> if a matching h r wage type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRWageType fetchByG_C(long groupId,
		java.lang.String code, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByG_C(groupId, code, retrieveFromCache);
	}

	/**
	* Returns all the h r wage types.
	*
	* @return the h r wage types
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRWageType> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the h r wage types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r wage types
	* @param end the upper bound of the range of h r wage types (not inclusive)
	* @return the range of h r wage types
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRWageType> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the h r wage types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r wage types
	* @param end the upper bound of the range of h r wage types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r wage types
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRWageType> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes the h r wage type where groupId = &#63; and code = &#63; from the database.
	*
	* @param groupId the group ID
	* @param code the code
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_C(long groupId, java.lang.String code)
		throws com.liferay.hr.NoSuchWageTypeException,
			com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_C(groupId, code);
	}

	/**
	* Removes all the h r wage types from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of h r wage types where groupId = &#63; and code = &#63;.
	*
	* @param groupId the group ID
	* @param code the code
	* @return the number of matching h r wage types
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_C(long groupId, java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_C(groupId, code);
	}

	/**
	* Returns the number of h r wage types.
	*
	* @return the number of h r wage types
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static HRWageTypePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (HRWageTypePersistence)PortletBeanLocatorUtil.locate(com.liferay.hr.service.ClpSerializer.getServletContextName(),
					HRWageTypePersistence.class.getName());

			ReferenceRegistry.registerReference(HRWageTypeUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(HRWageTypePersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(HRWageTypeUtil.class, "_persistence");
	}

	private static HRWageTypePersistence _persistence;
}