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

import com.liferay.hr.model.HRAsset;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the h r asset service. This utility wraps {@link HRAssetPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRAssetPersistence
 * @see HRAssetPersistenceImpl
 * @generated
 */
public class HRAssetUtil {
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
	public static void clearCache(HRAsset hrAsset) {
		getPersistence().clearCache(hrAsset);
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
	public static List<HRAsset> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HRAsset> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HRAsset> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static HRAsset remove(HRAsset hrAsset) throws SystemException {
		return getPersistence().remove(hrAsset);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static HRAsset update(HRAsset hrAsset, boolean merge)
		throws SystemException {
		return getPersistence().update(hrAsset, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static HRAsset update(HRAsset hrAsset, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(hrAsset, merge, serviceContext);
	}

	/**
	* Caches the h r asset in the entity cache if it is enabled.
	*
	* @param hrAsset the h r asset to cache
	*/
	public static void cacheResult(com.liferay.hr.model.HRAsset hrAsset) {
		getPersistence().cacheResult(hrAsset);
	}

	/**
	* Caches the h r assets in the entity cache if it is enabled.
	*
	* @param hrAssets the h r assets to cache
	*/
	public static void cacheResult(
		java.util.List<com.liferay.hr.model.HRAsset> hrAssets) {
		getPersistence().cacheResult(hrAssets);
	}

	/**
	* Creates a new h r asset with the primary key. Does not add the h r asset to the database.
	*
	* @param hrAssetId the primary key for the new h r asset
	* @return the new h r asset
	*/
	public static com.liferay.hr.model.HRAsset create(long hrAssetId) {
		return getPersistence().create(hrAssetId);
	}

	/**
	* Removes the h r asset with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrAssetId the primary key of the h r asset to remove
	* @return the h r asset that was removed
	* @throws com.liferay.hr.NoSuchAssetException if a h r asset with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRAsset remove(long hrAssetId)
		throws com.liferay.hr.NoSuchAssetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(hrAssetId);
	}

	public static com.liferay.hr.model.HRAsset updateImpl(
		com.liferay.hr.model.HRAsset hrAsset, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(hrAsset, merge);
	}

	/**
	* Finds the h r asset with the primary key or throws a {@link com.liferay.hr.NoSuchAssetException} if it could not be found.
	*
	* @param hrAssetId the primary key of the h r asset to find
	* @return the h r asset
	* @throws com.liferay.hr.NoSuchAssetException if a h r asset with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRAsset findByPrimaryKey(long hrAssetId)
		throws com.liferay.hr.NoSuchAssetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(hrAssetId);
	}

	/**
	* Finds the h r asset with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrAssetId the primary key of the h r asset to find
	* @return the h r asset, or <code>null</code> if a h r asset with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRAsset fetchByPrimaryKey(long hrAssetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(hrAssetId);
	}

	/**
	* Finds all the h r assets.
	*
	* @return the h r assets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRAsset> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Finds a range of all the h r assets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r assets to return
	* @param end the upper bound of the range of h r assets to return (not inclusive)
	* @return the range of h r assets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRAsset> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Finds an ordered range of all the h r assets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r assets to return
	* @param end the upper bound of the range of h r assets to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r assets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRAsset> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the h r assets from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the h r assets.
	*
	* @return the number of h r assets
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static HRAssetPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (HRAssetPersistence)PortletBeanLocatorUtil.locate(com.liferay.hr.service.ClpSerializer.getServletContextName(),
					HRAssetPersistence.class.getName());

			ReferenceRegistry.registerReference(HRAssetUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(HRAssetPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(HRAssetUtil.class, "_persistence");
	}

	private static HRAssetPersistence _persistence;
}