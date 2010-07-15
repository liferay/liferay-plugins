/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.ams.service.persistence;

import com.liferay.ams.model.Asset;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * @author    Brian Wing Shun Chan
 * @see       AssetPersistence
 * @see       AssetPersistenceImpl
 * @generated
 */
public class AssetUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(Asset asset) {
		getPersistence().clearCache(asset);
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
	public static List<Asset> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Asset> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Asset> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static Asset remove(Asset asset) throws SystemException {
		return getPersistence().remove(asset);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static Asset update(Asset asset, boolean merge)
		throws SystemException {
		return getPersistence().update(asset, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static Asset update(Asset asset, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(asset, merge, serviceContext);
	}

	public static void cacheResult(com.liferay.ams.model.Asset asset) {
		getPersistence().cacheResult(asset);
	}

	public static void cacheResult(
		java.util.List<com.liferay.ams.model.Asset> assets) {
		getPersistence().cacheResult(assets);
	}

	public static com.liferay.ams.model.Asset create(long assetId) {
		return getPersistence().create(assetId);
	}

	public static com.liferay.ams.model.Asset remove(long assetId)
		throws com.liferay.ams.NoSuchAssetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(assetId);
	}

	public static com.liferay.ams.model.Asset updateImpl(
		com.liferay.ams.model.Asset asset, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(asset, merge);
	}

	public static com.liferay.ams.model.Asset findByPrimaryKey(long assetId)
		throws com.liferay.ams.NoSuchAssetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(assetId);
	}

	public static com.liferay.ams.model.Asset fetchByPrimaryKey(long assetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(assetId);
	}

	public static java.util.List<com.liferay.ams.model.Asset> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.ams.model.Asset> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.ams.model.Asset> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AssetPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AssetPersistence)PortletBeanLocatorUtil.locate(com.liferay.ams.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					AssetPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(AssetPersistence persistence) {
		_persistence = persistence;
	}

	private static AssetPersistence _persistence;
}