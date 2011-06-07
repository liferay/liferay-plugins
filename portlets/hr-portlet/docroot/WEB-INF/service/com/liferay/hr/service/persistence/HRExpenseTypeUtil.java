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

import com.liferay.hr.model.HRExpenseType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the h r expense type service. This utility wraps {@link HRExpenseTypePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRExpenseTypePersistence
 * @see HRExpenseTypePersistenceImpl
 * @generated
 */
public class HRExpenseTypeUtil {
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
	public static void clearCache(HRExpenseType hrExpenseType) {
		getPersistence().clearCache(hrExpenseType);
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
	public static List<HRExpenseType> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HRExpenseType> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HRExpenseType> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static HRExpenseType remove(HRExpenseType hrExpenseType)
		throws SystemException {
		return getPersistence().remove(hrExpenseType);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static HRExpenseType update(HRExpenseType hrExpenseType,
		boolean merge) throws SystemException {
		return getPersistence().update(hrExpenseType, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static HRExpenseType update(HRExpenseType hrExpenseType,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(hrExpenseType, merge, serviceContext);
	}

	/**
	* Caches the h r expense type in the entity cache if it is enabled.
	*
	* @param hrExpenseType the h r expense type
	*/
	public static void cacheResult(
		com.liferay.hr.model.HRExpenseType hrExpenseType) {
		getPersistence().cacheResult(hrExpenseType);
	}

	/**
	* Caches the h r expense types in the entity cache if it is enabled.
	*
	* @param hrExpenseTypes the h r expense types
	*/
	public static void cacheResult(
		java.util.List<com.liferay.hr.model.HRExpenseType> hrExpenseTypes) {
		getPersistence().cacheResult(hrExpenseTypes);
	}

	/**
	* Creates a new h r expense type with the primary key. Does not add the h r expense type to the database.
	*
	* @param hrExpenseTypeId the primary key for the new h r expense type
	* @return the new h r expense type
	*/
	public static com.liferay.hr.model.HRExpenseType create(
		long hrExpenseTypeId) {
		return getPersistence().create(hrExpenseTypeId);
	}

	/**
	* Removes the h r expense type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrExpenseTypeId the primary key of the h r expense type
	* @return the h r expense type that was removed
	* @throws com.liferay.hr.NoSuchExpenseTypeException if a h r expense type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRExpenseType remove(
		long hrExpenseTypeId)
		throws com.liferay.hr.NoSuchExpenseTypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(hrExpenseTypeId);
	}

	public static com.liferay.hr.model.HRExpenseType updateImpl(
		com.liferay.hr.model.HRExpenseType hrExpenseType, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(hrExpenseType, merge);
	}

	/**
	* Returns the h r expense type with the primary key or throws a {@link com.liferay.hr.NoSuchExpenseTypeException} if it could not be found.
	*
	* @param hrExpenseTypeId the primary key of the h r expense type
	* @return the h r expense type
	* @throws com.liferay.hr.NoSuchExpenseTypeException if a h r expense type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRExpenseType findByPrimaryKey(
		long hrExpenseTypeId)
		throws com.liferay.hr.NoSuchExpenseTypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(hrExpenseTypeId);
	}

	/**
	* Returns the h r expense type with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrExpenseTypeId the primary key of the h r expense type
	* @return the h r expense type, or <code>null</code> if a h r expense type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRExpenseType fetchByPrimaryKey(
		long hrExpenseTypeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(hrExpenseTypeId);
	}

	/**
	* Returns the h r expense type where groupId = &#63; and name = &#63; or throws a {@link com.liferay.hr.NoSuchExpenseTypeException} if it could not be found.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the matching h r expense type
	* @throws com.liferay.hr.NoSuchExpenseTypeException if a matching h r expense type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRExpenseType findByG_N(long groupId,
		java.lang.String name)
		throws com.liferay.hr.NoSuchExpenseTypeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_N(groupId, name);
	}

	/**
	* Returns the h r expense type where groupId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the matching h r expense type, or <code>null</code> if a matching h r expense type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRExpenseType fetchByG_N(long groupId,
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByG_N(groupId, name);
	}

	/**
	* Returns the h r expense type where groupId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the matching h r expense type, or <code>null</code> if a matching h r expense type could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRExpenseType fetchByG_N(long groupId,
		java.lang.String name, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByG_N(groupId, name, retrieveFromCache);
	}

	/**
	* Returns all the h r expense types.
	*
	* @return the h r expense types
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRExpenseType> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the h r expense types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r expense types
	* @param end the upper bound of the range of h r expense types (not inclusive)
	* @return the range of h r expense types
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRExpenseType> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the h r expense types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r expense types
	* @param end the upper bound of the range of h r expense types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r expense types
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRExpenseType> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes the h r expense type where groupId = &#63; and name = &#63; from the database.
	*
	* @param groupId the group ID
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_N(long groupId, java.lang.String name)
		throws com.liferay.hr.NoSuchExpenseTypeException,
			com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_N(groupId, name);
	}

	/**
	* Removes all the h r expense types from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of h r expense types where groupId = &#63; and name = &#63;.
	*
	* @param groupId the group ID
	* @param name the name
	* @return the number of matching h r expense types
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_N(long groupId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_N(groupId, name);
	}

	/**
	* Returns the number of h r expense types.
	*
	* @return the number of h r expense types
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static HRExpenseTypePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (HRExpenseTypePersistence)PortletBeanLocatorUtil.locate(com.liferay.hr.service.ClpSerializer.getServletContextName(),
					HRExpenseTypePersistence.class.getName());

			ReferenceRegistry.registerReference(HRExpenseTypeUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(HRExpenseTypePersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(HRExpenseTypeUtil.class,
			"_persistence");
	}

	private static HRExpenseTypePersistence _persistence;
}