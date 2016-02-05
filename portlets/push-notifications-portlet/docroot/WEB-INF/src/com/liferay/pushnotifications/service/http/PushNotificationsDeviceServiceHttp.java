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

package com.liferay.pushnotifications.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.liferay.pushnotifications.service.PushNotificationsDeviceServiceUtil;

/**
 * Provides the HTTP utility for the
 * {@link PushNotificationsDeviceServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Bruno Farache
 * @see PushNotificationsDeviceServiceSoap
 * @see HttpPrincipal
 * @see PushNotificationsDeviceServiceUtil
 * @generated
 */
@ProviderType
public class PushNotificationsDeviceServiceHttp {
	public static com.liferay.pushnotifications.model.PushNotificationsDevice addPushNotificationsDevice(
		HttpPrincipal httpPrincipal, java.lang.String token,
		java.lang.String platform)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(PushNotificationsDeviceServiceUtil.class,
					"addPushNotificationsDevice",
					_addPushNotificationsDeviceParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, token,
					platform);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.pushnotifications.model.PushNotificationsDevice)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.pushnotifications.model.PushNotificationsDevice deletePushNotificationsDevice(
		HttpPrincipal httpPrincipal, java.lang.String token)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(PushNotificationsDeviceServiceUtil.class,
					"deletePushNotificationsDevice",
					_deletePushNotificationsDeviceParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, token);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.pushnotifications.model.PushNotificationsDevice)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void sendPushNotification(HttpPrincipal httpPrincipal,
		long[] toUserIds, java.lang.String payload)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(PushNotificationsDeviceServiceUtil.class,
					"sendPushNotification", _sendPushNotificationParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					toUserIds, payload);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void sendPushNotification(HttpPrincipal httpPrincipal,
		java.lang.String platform, java.util.List<java.lang.String> tokens,
		java.lang.String payload)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(PushNotificationsDeviceServiceUtil.class,
					"sendPushNotification", _sendPushNotificationParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					platform, tokens, payload);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(PushNotificationsDeviceServiceHttp.class);
	private static final Class<?>[] _addPushNotificationsDeviceParameterTypes0 = new Class[] {
			java.lang.String.class, java.lang.String.class
		};
	private static final Class<?>[] _deletePushNotificationsDeviceParameterTypes1 =
		new Class[] { java.lang.String.class };
	private static final Class<?>[] _sendPushNotificationParameterTypes2 = new Class[] {
			long[].class, java.lang.String.class
		};
	private static final Class<?>[] _sendPushNotificationParameterTypes3 = new Class[] {
			java.lang.String.class, java.util.List.class, java.lang.String.class
		};
}