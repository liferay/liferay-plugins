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

package com.liferay.wsrp.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

import com.liferay.wsrp.model.WSRPConsumerPortlet;

import java.util.List;

/**
 * <a href="WSRPConsumerPortletUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WSRPConsumerPortletPersistence
 * @see       WSRPConsumerPortletPersistenceImpl
 * @generated
 */
public class WSRPConsumerPortletUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(WSRPConsumerPortlet wsrpConsumerPortlet) {
		getPersistence().clearCache(wsrpConsumerPortlet);
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
	public static List<WSRPConsumerPortlet> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WSRPConsumerPortlet> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WSRPConsumerPortlet> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static WSRPConsumerPortlet remove(
		WSRPConsumerPortlet wsrpConsumerPortlet) throws SystemException {
		return getPersistence().remove(wsrpConsumerPortlet);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static WSRPConsumerPortlet update(
		WSRPConsumerPortlet wsrpConsumerPortlet, boolean merge)
		throws SystemException {
		return getPersistence().update(wsrpConsumerPortlet, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static WSRPConsumerPortlet update(
		WSRPConsumerPortlet wsrpConsumerPortlet, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(wsrpConsumerPortlet, merge, serviceContext);
	}

	public static void cacheResult(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet) {
		getPersistence().cacheResult(wsrpConsumerPortlet);
	}

	public static void cacheResult(
		java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> wsrpConsumerPortlets) {
		getPersistence().cacheResult(wsrpConsumerPortlets);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet create(
		long wsrpConsumerPortletId) {
		return getPersistence().create(wsrpConsumerPortletId);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet remove(
		long wsrpConsumerPortletId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException {
		return getPersistence().remove(wsrpConsumerPortletId);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet updateImpl(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(wsrpConsumerPortlet, merge);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet findByPrimaryKey(
		long wsrpConsumerPortletId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException {
		return getPersistence().findByPrimaryKey(wsrpConsumerPortletId);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet fetchByPrimaryKey(
		long wsrpConsumerPortletId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(wsrpConsumerPortletId);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findByWsrpConsumerId(
		long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByWsrpConsumerId(wsrpConsumerId);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findByWsrpConsumerId(
		long wsrpConsumerId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByWsrpConsumerId(wsrpConsumerId, start, end);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findByWsrpConsumerId(
		long wsrpConsumerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByWsrpConsumerId(wsrpConsumerId, start, end,
			orderByComparator);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet findByWsrpConsumerId_First(
		long wsrpConsumerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException {
		return getPersistence()
				   .findByWsrpConsumerId_First(wsrpConsumerId, orderByComparator);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet findByWsrpConsumerId_Last(
		long wsrpConsumerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException {
		return getPersistence()
				   .findByWsrpConsumerId_Last(wsrpConsumerId, orderByComparator);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet[] findByWsrpConsumerId_PrevAndNext(
		long wsrpConsumerPortletId, long wsrpConsumerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException {
		return getPersistence()
				   .findByWsrpConsumerId_PrevAndNext(wsrpConsumerPortletId,
			wsrpConsumerId, orderByComparator);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet findByW_P(
		long wsrpConsumerId, java.lang.String portletHandle)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException {
		return getPersistence().findByW_P(wsrpConsumerId, portletHandle);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet fetchByW_P(
		long wsrpConsumerId, java.lang.String portletHandle)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByW_P(wsrpConsumerId, portletHandle);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet fetchByW_P(
		long wsrpConsumerId, java.lang.String portletHandle,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByW_P(wsrpConsumerId, portletHandle, retrieveFromCache);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	public static void removeByWsrpConsumerId(long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByWsrpConsumerId(wsrpConsumerId);
	}

	public static void removeByW_P(long wsrpConsumerId,
		java.lang.String portletHandle)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException {
		getPersistence().removeByW_P(wsrpConsumerId, portletHandle);
	}

	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	public static int countByWsrpConsumerId(long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByWsrpConsumerId(wsrpConsumerId);
	}

	public static int countByW_P(long wsrpConsumerId,
		java.lang.String portletHandle)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByW_P(wsrpConsumerId, portletHandle);
	}

	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static WSRPConsumerPortletPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (WSRPConsumerPortletPersistence)PortletBeanLocatorUtil.locate(com.liferay.wsrp.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					WSRPConsumerPortletPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(WSRPConsumerPortletPersistence persistence) {
		_persistence = persistence;
	}

	private static WSRPConsumerPortletPersistence _persistence;
}