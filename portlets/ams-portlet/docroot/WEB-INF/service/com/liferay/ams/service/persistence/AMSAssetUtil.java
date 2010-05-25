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

import com.liferay.ams.model.AMSAsset;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * <a href="AMSAssetUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AMSAssetPersistence
 * @see       AMSAssetPersistenceImpl
 * @generated
 */
public class AMSAssetUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(AMSAsset)
	 */
	public static void clearCache(AMSAsset amsAsset) {
		getPersistence().clearCache(amsAsset);
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
	public static List<AMSAsset> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AMSAsset> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static AMSAsset remove(AMSAsset amsAsset) throws SystemException {
		return getPersistence().remove(amsAsset);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AMSAsset update(AMSAsset amsAsset, boolean merge)
		throws SystemException {
		return getPersistence().update(amsAsset, merge);
	}

	public static void cacheResult(com.liferay.ams.model.AMSAsset amsAsset) {
		getPersistence().cacheResult(amsAsset);
	}

	public static void cacheResult(
		java.util.List<com.liferay.ams.model.AMSAsset> amsAssets) {
		getPersistence().cacheResult(amsAssets);
	}

	public static com.liferay.ams.model.AMSAsset create(long amsAssetId) {
		return getPersistence().create(amsAssetId);
	}

	public static com.liferay.ams.model.AMSAsset remove(long amsAssetId)
		throws com.liferay.ams.NoSuchAssetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(amsAssetId);
	}

	public static com.liferay.ams.model.AMSAsset updateImpl(
		com.liferay.ams.model.AMSAsset amsAsset, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(amsAsset, merge);
	}

	public static com.liferay.ams.model.AMSAsset findByPrimaryKey(
		long amsAssetId)
		throws com.liferay.ams.NoSuchAssetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(amsAssetId);
	}

	public static com.liferay.ams.model.AMSAsset fetchByPrimaryKey(
		long amsAssetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(amsAssetId);
	}

	public static java.util.List<com.liferay.ams.model.AMSAsset> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.ams.model.AMSAsset> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.ams.model.AMSAsset> findAll(
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

	public static AMSAssetPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AMSAssetPersistence)PortletBeanLocatorUtil.locate(com.liferay.ams.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					AMSAssetPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(AMSAssetPersistence persistence) {
		_persistence = persistence;
	}

	private static AMSAssetPersistence _persistence;
}