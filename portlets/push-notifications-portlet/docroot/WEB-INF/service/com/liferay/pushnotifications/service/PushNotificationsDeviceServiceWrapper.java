/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.pushnotifications.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PushNotificationsDeviceService}.
 *
 * @author Bruno Farache
 * @see PushNotificationsDeviceService
 * @generated
 */
@ProviderType
public class PushNotificationsDeviceServiceWrapper
	implements PushNotificationsDeviceService,
		ServiceWrapper<PushNotificationsDeviceService> {
	public PushNotificationsDeviceServiceWrapper(
		PushNotificationsDeviceService pushNotificationsDeviceService) {
		_pushNotificationsDeviceService = pushNotificationsDeviceService;
	}

	@Override
	public com.liferay.pushnotifications.model.PushNotificationsDevice addPushNotificationsDevice(
		java.lang.String token, java.lang.String platform)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _pushNotificationsDeviceService.addPushNotificationsDevice(token,
			platform);
	}

	@Override
	public com.liferay.pushnotifications.model.PushNotificationsDevice deletePushNotificationsDevice(
		java.lang.String token)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _pushNotificationsDeviceService.deletePushNotificationsDevice(token);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _pushNotificationsDeviceService.getBeanIdentifier();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _pushNotificationsDeviceService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public void sendPushNotification(java.lang.String platform,
		java.util.List<java.lang.String> tokens, java.lang.String payload)
		throws com.liferay.portal.kernel.exception.PortalException {
		_pushNotificationsDeviceService.sendPushNotification(platform, tokens,
			payload);
	}

	@Override
	public void sendPushNotification(long[] toUserIds, java.lang.String payload)
		throws com.liferay.portal.kernel.exception.PortalException {
		_pushNotificationsDeviceService.sendPushNotification(toUserIds, payload);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_pushNotificationsDeviceService.setBeanIdentifier(beanIdentifier);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public PushNotificationsDeviceService getWrappedPushNotificationsDeviceService() {
		return _pushNotificationsDeviceService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedPushNotificationsDeviceService(
		PushNotificationsDeviceService pushNotificationsDeviceService) {
		_pushNotificationsDeviceService = pushNotificationsDeviceService;
	}

	@Override
	public PushNotificationsDeviceService getWrappedService() {
		return _pushNotificationsDeviceService;
	}

	@Override
	public void setWrappedService(
		PushNotificationsDeviceService pushNotificationsDeviceService) {
		_pushNotificationsDeviceService = pushNotificationsDeviceService;
	}

	private PushNotificationsDeviceService _pushNotificationsDeviceService;
}