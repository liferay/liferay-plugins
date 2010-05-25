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

import com.liferay.ams.model.AMSCheckout;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * <a href="AMSCheckoutUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AMSCheckoutPersistence
 * @see       AMSCheckoutPersistenceImpl
 * @generated
 */
public class AMSCheckoutUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(AMSCheckout)
	 */
	public static void clearCache(AMSCheckout amsCheckout) {
		getPersistence().clearCache(amsCheckout);
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
	public static List<AMSCheckout> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AMSCheckout> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static AMSCheckout remove(AMSCheckout amsCheckout)
		throws SystemException {
		return getPersistence().remove(amsCheckout);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AMSCheckout update(AMSCheckout amsCheckout, boolean merge)
		throws SystemException {
		return getPersistence().update(amsCheckout, merge);
	}

	public static void cacheResult(
		com.liferay.ams.model.AMSCheckout amsCheckout) {
		getPersistence().cacheResult(amsCheckout);
	}

	public static void cacheResult(
		java.util.List<com.liferay.ams.model.AMSCheckout> amsCheckouts) {
		getPersistence().cacheResult(amsCheckouts);
	}

	public static com.liferay.ams.model.AMSCheckout create(long amsCheckoutId) {
		return getPersistence().create(amsCheckoutId);
	}

	public static com.liferay.ams.model.AMSCheckout remove(long amsCheckoutId)
		throws com.liferay.ams.NoSuchCheckoutException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(amsCheckoutId);
	}

	public static com.liferay.ams.model.AMSCheckout updateImpl(
		com.liferay.ams.model.AMSCheckout amsCheckout, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(amsCheckout, merge);
	}

	public static com.liferay.ams.model.AMSCheckout findByPrimaryKey(
		long amsCheckoutId)
		throws com.liferay.ams.NoSuchCheckoutException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(amsCheckoutId);
	}

	public static com.liferay.ams.model.AMSCheckout fetchByPrimaryKey(
		long amsCheckoutId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(amsCheckoutId);
	}

	public static java.util.List<com.liferay.ams.model.AMSCheckout> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.ams.model.AMSCheckout> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.ams.model.AMSCheckout> findAll(
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

	public static AMSCheckoutPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AMSCheckoutPersistence)PortletBeanLocatorUtil.locate(com.liferay.ams.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					AMSCheckoutPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(AMSCheckoutPersistence persistence) {
		_persistence = persistence;
	}

	private static AMSCheckoutPersistence _persistence;
}