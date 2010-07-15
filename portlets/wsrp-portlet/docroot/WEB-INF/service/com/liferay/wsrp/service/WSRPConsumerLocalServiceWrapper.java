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
 * This class is a wrapper for {@link WSRPConsumerLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WSRPConsumerLocalService
 * @generated
 */
public class WSRPConsumerLocalServiceWrapper implements WSRPConsumerLocalService {
	public WSRPConsumerLocalServiceWrapper(
		WSRPConsumerLocalService wsrpConsumerLocalService) {
		_wsrpConsumerLocalService = wsrpConsumerLocalService;
	}

	public com.liferay.wsrp.model.WSRPConsumer addWSRPConsumer(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.addWSRPConsumer(wsrpConsumer);
	}

	public com.liferay.wsrp.model.WSRPConsumer createWSRPConsumer(
		long wsrpConsumerId) {
		return _wsrpConsumerLocalService.createWSRPConsumer(wsrpConsumerId);
	}

	public void deleteWSRPConsumer(long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_wsrpConsumerLocalService.deleteWSRPConsumer(wsrpConsumerId);
	}

	public void deleteWSRPConsumer(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_wsrpConsumerLocalService.deleteWSRPConsumer(wsrpConsumer);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.wsrp.model.WSRPConsumer getWSRPConsumer(
		long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.getWSRPConsumer(wsrpConsumerId);
	}

	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> getWSRPConsumers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.getWSRPConsumers(start, end);
	}

	public int getWSRPConsumersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.getWSRPConsumersCount();
	}

	public com.liferay.wsrp.model.WSRPConsumer updateWSRPConsumer(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.updateWSRPConsumer(wsrpConsumer);
	}

	public com.liferay.wsrp.model.WSRPConsumer updateWSRPConsumer(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.updateWSRPConsumer(wsrpConsumer, merge);
	}

	public com.liferay.wsrp.model.WSRPConsumer addWSRPConsumer(long companyId,
		java.lang.String adminPortletId, java.lang.String name,
		java.lang.String url, java.lang.String userToken)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.addWSRPConsumer(companyId,
			adminPortletId, name, url, userToken);
	}

	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> getWSRPConsumers(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.getWSRPConsumers(companyId, start, end);
	}

	public int getWSRPConsumersCount(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.getWSRPConsumersCount(companyId);
	}

	public com.liferay.wsrp.model.WSRPConsumer registerWSRPConsumer(
		long wsrpConsumerId, java.lang.String adminPortletId,
		com.liferay.portal.kernel.util.UnicodeProperties registrationProperties,
		java.lang.String registrationHandle, java.lang.String userToken)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.registerWSRPConsumer(wsrpConsumerId,
			adminPortletId, registrationProperties, registrationHandle,
			userToken);
	}

	public void restartConsumer(long wsrpConsumerId, java.lang.String userToken)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_wsrpConsumerLocalService.restartConsumer(wsrpConsumerId, userToken);
	}

	public void updateServiceDescription(long wsrpConsumerId,
		java.lang.String userToken)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_wsrpConsumerLocalService.updateServiceDescription(wsrpConsumerId,
			userToken);
	}

	public com.liferay.wsrp.model.WSRPConsumer updateWSRPConsumer(
		long wsrpConsumerId, java.lang.String adminPortletId,
		java.lang.String name, java.lang.String url, java.lang.String userToken)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.updateWSRPConsumer(wsrpConsumerId,
			adminPortletId, name, url, userToken);
	}

	public WSRPConsumerLocalService getWrappedWSRPConsumerLocalService() {
		return _wsrpConsumerLocalService;
	}

	private WSRPConsumerLocalService _wsrpConsumerLocalService;
}