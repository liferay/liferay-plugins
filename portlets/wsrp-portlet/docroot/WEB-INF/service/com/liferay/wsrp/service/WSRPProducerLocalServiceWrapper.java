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

package com.liferay.wsrp.service;


/**
 * <p>
 * This class is a wrapper for {@link WSRPProducerLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WSRPProducerLocalService
 * @generated
 */
public class WSRPProducerLocalServiceWrapper implements WSRPProducerLocalService {
	public WSRPProducerLocalServiceWrapper(
		WSRPProducerLocalService wsrpProducerLocalService) {
		_wsrpProducerLocalService = wsrpProducerLocalService;
	}

	public com.liferay.wsrp.model.WSRPProducer addWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.addWSRPProducer(wsrpProducer);
	}

	public com.liferay.wsrp.model.WSRPProducer createWSRPProducer(
		long wsrpProducerId) {
		return _wsrpProducerLocalService.createWSRPProducer(wsrpProducerId);
	}

	public void deleteWSRPProducer(long wsrpProducerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_wsrpProducerLocalService.deleteWSRPProducer(wsrpProducerId);
	}

	public void deleteWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_wsrpProducerLocalService.deleteWSRPProducer(wsrpProducer);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.wsrp.model.WSRPProducer getWSRPProducer(
		long wsrpProducerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.getWSRPProducer(wsrpProducerId);
	}

	public java.util.List<com.liferay.wsrp.model.WSRPProducer> getWSRPProducers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.getWSRPProducers(start, end);
	}

	public int getWSRPProducersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.getWSRPProducersCount();
	}

	public com.liferay.wsrp.model.WSRPProducer updateWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.updateWSRPProducer(wsrpProducer);
	}

	public com.liferay.wsrp.model.WSRPProducer updateWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.updateWSRPProducer(wsrpProducer, merge);
	}

	public com.liferay.wsrp.model.WSRPProducer addWSRPProducer(long userId,
		java.lang.String name, java.lang.String portletIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.addWSRPProducer(userId, name,
			portletIds);
	}

	public com.liferay.wsrp.model.WSRPProducer addWSRPProducer(long userId,
		long groupId, java.lang.String name, java.lang.String portletIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.addWSRPProducer(userId, groupId, name,
			portletIds);
	}

	public java.util.List<com.liferay.wsrp.model.WSRPProducer> getWSRPProducers(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.getWSRPProducers(companyId, start, end);
	}

	public int getWSRPProducersCount(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.getWSRPProducersCount(companyId);
	}

	public com.liferay.wsrp.model.WSRPProducer updateWSRPProducer(
		long wsrpProducerId, java.lang.String name, java.lang.String portletIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.updateWSRPProducer(wsrpProducerId,
			name, portletIds);
	}

	public WSRPProducerLocalService getWrappedWSRPProducerLocalService() {
		return _wsrpProducerLocalService;
	}

	private WSRPProducerLocalService _wsrpProducerLocalService;
}