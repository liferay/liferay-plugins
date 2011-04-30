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

import com.liferay.hr.model.HRAssetVendor;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the h r asset vendor service. This utility wraps {@link HRAssetVendorPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRAssetVendorPersistence
 * @see HRAssetVendorPersistenceImpl
 * @generated
 */
public class HRAssetVendorUtil {
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
	public static void clearCache(HRAssetVendor hrAssetVendor) {
		getPersistence().clearCache(hrAssetVendor);
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
	public static List<HRAssetVendor> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HRAssetVendor> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HRAssetVendor> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static HRAssetVendor remove(HRAssetVendor hrAssetVendor)
		throws SystemException {
		return getPersistence().remove(hrAssetVendor);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static HRAssetVendor update(HRAssetVendor hrAssetVendor,
		boolean merge) throws SystemException {
		return getPersistence().update(hrAssetVendor, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static HRAssetVendor update(HRAssetVendor hrAssetVendor,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(hrAssetVendor, merge, serviceContext);
	}

	/**
	* Caches the h r asset vendor in the entity cache if it is enabled.
	*
	* @param hrAssetVendor the h r asset vendor to cache
	*/
	public static void cacheResult(
		com.liferay.hr.model.HRAssetVendor hrAssetVendor) {
		getPersistence().cacheResult(hrAssetVendor);
	}

	/**
	* Caches the h r asset vendors in the entity cache if it is enabled.
	*
	* @param hrAssetVendors the h r asset vendors to cache
	*/
	public static void cacheResult(
		java.util.List<com.liferay.hr.model.HRAssetVendor> hrAssetVendors) {
		getPersistence().cacheResult(hrAssetVendors);
	}

	/**
	* Creates a new h r asset vendor with the primary key. Does not add the h r asset vendor to the database.
	*
	* @param hrAssetVendorId the primary key for the new h r asset vendor
	* @return the new h r asset vendor
	*/
	public static com.liferay.hr.model.HRAssetVendor create(
		long hrAssetVendorId) {
		return getPersistence().create(hrAssetVendorId);
	}

	/**
	* Removes the h r asset vendor with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrAssetVendorId the primary key of the h r asset vendor to remove
	* @return the h r asset vendor that was removed
	* @throws com.liferay.hr.NoSuchAssetVendorException if a h r asset vendor with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRAssetVendor remove(
		long hrAssetVendorId)
		throws com.liferay.hr.NoSuchAssetVendorException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(hrAssetVendorId);
	}

	public static com.liferay.hr.model.HRAssetVendor updateImpl(
		com.liferay.hr.model.HRAssetVendor hrAssetVendor, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(hrAssetVendor, merge);
	}

	/**
	* Finds the h r asset vendor with the primary key or throws a {@link com.liferay.hr.NoSuchAssetVendorException} if it could not be found.
	*
	* @param hrAssetVendorId the primary key of the h r asset vendor to find
	* @return the h r asset vendor
	* @throws com.liferay.hr.NoSuchAssetVendorException if a h r asset vendor with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRAssetVendor findByPrimaryKey(
		long hrAssetVendorId)
		throws com.liferay.hr.NoSuchAssetVendorException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(hrAssetVendorId);
	}

	/**
	* Finds the h r asset vendor with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrAssetVendorId the primary key of the h r asset vendor to find
	* @return the h r asset vendor, or <code>null</code> if a h r asset vendor with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRAssetVendor fetchByPrimaryKey(
		long hrAssetVendorId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(hrAssetVendorId);
	}

	/**
	* Finds all the h r asset vendors.
	*
	* @return the h r asset vendors
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRAssetVendor> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Finds a range of all the h r asset vendors.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r asset vendors to return
	* @param end the upper bound of the range of h r asset vendors to return (not inclusive)
	* @return the range of h r asset vendors
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRAssetVendor> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Finds an ordered range of all the h r asset vendors.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r asset vendors to return
	* @param end the upper bound of the range of h r asset vendors to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r asset vendors
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRAssetVendor> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the h r asset vendors from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the h r asset vendors.
	*
	* @return the number of h r asset vendors
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static HRAssetVendorPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (HRAssetVendorPersistence)PortletBeanLocatorUtil.locate(com.liferay.hr.service.ClpSerializer.getServletContextName(),
					HRAssetVendorPersistence.class.getName());

			ReferenceRegistry.registerReference(HRAssetVendorUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(HRAssetVendorPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(HRAssetVendorUtil.class,
			"_persistence");
	}

	private static HRAssetVendorPersistence _persistence;
}