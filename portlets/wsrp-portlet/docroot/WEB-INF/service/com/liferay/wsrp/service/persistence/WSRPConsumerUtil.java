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

import com.liferay.wsrp.model.WSRPConsumer;

import java.util.List;

/**
 * <a href="WSRPConsumerUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WSRPConsumerPersistence
 * @see       WSRPConsumerPersistenceImpl
 * @generated
 */
public class WSRPConsumerUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(WSRPConsumer wsrpConsumer) {
		getPersistence().clearCache(wsrpConsumer);
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
	public static List<WSRPConsumer> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WSRPConsumer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WSRPConsumer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static WSRPConsumer remove(WSRPConsumer wsrpConsumer)
		throws SystemException {
		return getPersistence().remove(wsrpConsumer);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static WSRPConsumer update(WSRPConsumer wsrpConsumer, boolean merge)
		throws SystemException {
		return getPersistence().update(wsrpConsumer, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static WSRPConsumer update(WSRPConsumer wsrpConsumer, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(wsrpConsumer, merge, serviceContext);
	}

	public static void cacheResult(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer) {
		getPersistence().cacheResult(wsrpConsumer);
	}

	public static void cacheResult(
		java.util.List<com.liferay.wsrp.model.WSRPConsumer> wsrpConsumers) {
		getPersistence().cacheResult(wsrpConsumers);
	}

	public static com.liferay.wsrp.model.WSRPConsumer create(
		long wsrpConsumerId) {
		return getPersistence().create(wsrpConsumerId);
	}

	public static com.liferay.wsrp.model.WSRPConsumer remove(
		long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException {
		return getPersistence().remove(wsrpConsumerId);
	}

	public static com.liferay.wsrp.model.WSRPConsumer updateImpl(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(wsrpConsumer, merge);
	}

	public static com.liferay.wsrp.model.WSRPConsumer findByPrimaryKey(
		long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException {
		return getPersistence().findByPrimaryKey(wsrpConsumerId);
	}

	public static com.liferay.wsrp.model.WSRPConsumer fetchByPrimaryKey(
		long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(wsrpConsumerId);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumer> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumer> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumer> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	public static com.liferay.wsrp.model.WSRPConsumer findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	public static com.liferay.wsrp.model.WSRPConsumer findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	public static com.liferay.wsrp.model.WSRPConsumer[] findByCompanyId_PrevAndNext(
		long wsrpConsumerId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(wsrpConsumerId, companyId,
			orderByComparator);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumer> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumer> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumer> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static WSRPConsumerPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (WSRPConsumerPersistence)PortletBeanLocatorUtil.locate(com.liferay.wsrp.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					WSRPConsumerPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(WSRPConsumerPersistence persistence) {
		_persistence = persistence;
	}

	private static WSRPConsumerPersistence _persistence;
}