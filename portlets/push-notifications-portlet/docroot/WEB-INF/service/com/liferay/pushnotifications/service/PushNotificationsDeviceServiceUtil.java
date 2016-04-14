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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the remote service utility for PushNotificationsDevice. This utility wraps
 * {@link com.liferay.pushnotifications.service.impl.PushNotificationsDeviceServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Bruno Farache
 * @see PushNotificationsDeviceService
 * @see com.liferay.pushnotifications.service.base.PushNotificationsDeviceServiceBaseImpl
 * @see com.liferay.pushnotifications.service.impl.PushNotificationsDeviceServiceImpl
 * @generated
 */
@ProviderType
public class PushNotificationsDeviceServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.pushnotifications.service.impl.PushNotificationsDeviceServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.pushnotifications.model.PushNotificationsDevice addPushNotificationsDevice(
		java.lang.String token, java.lang.String platform)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().addPushNotificationsDevice(token, platform);
	}

	public static com.liferay.pushnotifications.model.PushNotificationsDevice deletePushNotificationsDevice(
		java.lang.String token)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePushNotificationsDevice(token);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static void sendPushNotification(java.lang.String platform,
		java.util.List<java.lang.String> tokens, java.lang.String payload)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().sendPushNotification(platform, tokens, payload);
	}

	public static void sendPushNotification(long[] toUserIds,
		java.lang.String payload)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().sendPushNotification(toUserIds, payload);
	}

	public static void clearService() {
		_service = null;
	}

	public static PushNotificationsDeviceService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					PushNotificationsDeviceService.class.getName());

			if (invokableService instanceof PushNotificationsDeviceService) {
				_service = (PushNotificationsDeviceService)invokableService;
			}
			else {
				_service = new PushNotificationsDeviceServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(PushNotificationsDeviceServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static PushNotificationsDeviceService _service;
}