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

import com.liferay.hr.model.HRAssetVendorId;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the h r asset vendor ID service. This utility wraps {@link HRAssetVendorIdPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HRAssetVendorIdPersistence
 * @see HRAssetVendorIdPersistenceImpl
 * @generated
 */
public class HRAssetVendorIdUtil {
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
	public static void clearCache(HRAssetVendorId hrAssetVendorId) {
		getPersistence().clearCache(hrAssetVendorId);
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
	public static List<HRAssetVendorId> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HRAssetVendorId> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HRAssetVendorId> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static HRAssetVendorId remove(HRAssetVendorId hrAssetVendorId)
		throws SystemException {
		return getPersistence().remove(hrAssetVendorId);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static HRAssetVendorId update(HRAssetVendorId hrAssetVendorId,
		boolean merge) throws SystemException {
		return getPersistence().update(hrAssetVendorId, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static HRAssetVendorId update(HRAssetVendorId hrAssetVendorId,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(hrAssetVendorId, merge, serviceContext);
	}

	/**
	* Caches the h r asset vendor ID in the entity cache if it is enabled.
	*
	* @param hrAssetVendorId the h r asset vendor ID to cache
	*/
	public static void cacheResult(
		com.liferay.hr.model.HRAssetVendorId hrAssetVendorId) {
		getPersistence().cacheResult(hrAssetVendorId);
	}

	/**
	* Caches the h r asset vendor IDs in the entity cache if it is enabled.
	*
	* @param hrAssetVendorIds the h r asset vendor IDs to cache
	*/
	public static void cacheResult(
		java.util.List<com.liferay.hr.model.HRAssetVendorId> hrAssetVendorIds) {
		getPersistence().cacheResult(hrAssetVendorIds);
	}

	/**
	* Creates a new h r asset vendor ID with the primary key. Does not add the h r asset vendor ID to the database.
	*
	* @param hrAssetVendorId the primary key for the new h r asset vendor ID
	* @return the new h r asset vendor ID
	*/
	public static com.liferay.hr.model.HRAssetVendorId create(
		long hrAssetVendorId) {
		return getPersistence().create(hrAssetVendorId);
	}

	/**
	* Removes the h r asset vendor ID with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrAssetVendorId the primary key of the h r asset vendor ID to remove
	* @return the h r asset vendor ID that was removed
	* @throws com.liferay.hr.NoSuchAssetVendorIdException if a h r asset vendor ID with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRAssetVendorId remove(
		long hrAssetVendorId)
		throws com.liferay.hr.NoSuchAssetVendorIdException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(hrAssetVendorId);
	}

	public static com.liferay.hr.model.HRAssetVendorId updateImpl(
		com.liferay.hr.model.HRAssetVendorId hrAssetVendorId, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(hrAssetVendorId, merge);
	}

	/**
	* Finds the h r asset vendor ID with the primary key or throws a {@link com.liferay.hr.NoSuchAssetVendorIdException} if it could not be found.
	*
	* @param hrAssetVendorId the primary key of the h r asset vendor ID to find
	* @return the h r asset vendor ID
	* @throws com.liferay.hr.NoSuchAssetVendorIdException if a h r asset vendor ID with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRAssetVendorId findByPrimaryKey(
		long hrAssetVendorId)
		throws com.liferay.hr.NoSuchAssetVendorIdException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(hrAssetVendorId);
	}

	/**
	* Finds the h r asset vendor ID with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrAssetVendorId the primary key of the h r asset vendor ID to find
	* @return the h r asset vendor ID, or <code>null</code> if a h r asset vendor ID with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRAssetVendorId fetchByPrimaryKey(
		long hrAssetVendorId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(hrAssetVendorId);
	}

	/**
	* Finds all the h r asset vendor IDs.
	*
	* @return the h r asset vendor IDs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRAssetVendorId> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Finds a range of all the h r asset vendor IDs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r asset vendor IDs to return
	* @param end the upper bound of the range of h r asset vendor IDs to return (not inclusive)
	* @return the range of h r asset vendor IDs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRAssetVendorId> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Finds an ordered range of all the h r asset vendor IDs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r asset vendor IDs to return
	* @param end the upper bound of the range of h r asset vendor IDs to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r asset vendor IDs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRAssetVendorId> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the h r asset vendor IDs from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the h r asset vendor IDs.
	*
	* @return the number of h r asset vendor IDs
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static HRAssetVendorIdPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (HRAssetVendorIdPersistence)PortletBeanLocatorUtil.locate(com.liferay.hr.service.ClpSerializer.getServletContextName(),
					HRAssetVendorIdPersistence.class.getName());

			ReferenceRegistry.registerReference(HRAssetVendorIdUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(HRAssetVendorIdPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(HRAssetVendorIdUtil.class,
			"_persistence");
	}

	private static HRAssetVendorIdPersistence _persistence;
}