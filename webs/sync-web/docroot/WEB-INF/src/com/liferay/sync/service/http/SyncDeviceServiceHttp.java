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

package com.liferay.sync.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.liferay.sync.service.SyncDeviceServiceUtil;

/**
 * Provides the HTTP utility for the
 * {@link SyncDeviceServiceUtil} service utility. The
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
 * @author Brian Wing Shun Chan
 * @see SyncDeviceServiceSoap
 * @see HttpPrincipal
 * @see SyncDeviceServiceUtil
 * @generated
 */
@ProviderType
public class SyncDeviceServiceHttp {
	public static com.liferay.sync.model.SyncDevice registerSyncDevice(
		HttpPrincipal httpPrincipal, java.lang.String type, int buildNumber,
		int featureSet, java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(SyncDeviceServiceUtil.class,
					"registerSyncDevice", _registerSyncDeviceParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, type,
					buildNumber, featureSet, uuid);

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

			return (com.liferay.sync.model.SyncDevice)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void unregisterSyncDevice(HttpPrincipal httpPrincipal,
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(SyncDeviceServiceUtil.class,
					"unregisterSyncDevice", _unregisterSyncDeviceParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, uuid);

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

	private static Log _log = LogFactoryUtil.getLog(SyncDeviceServiceHttp.class);
	private static final Class<?>[] _registerSyncDeviceParameterTypes0 = new Class[] {
			java.lang.String.class, int.class, int.class, java.lang.String.class
		};
	private static final Class<?>[] _unregisterSyncDeviceParameterTypes1 = new Class[] {
			java.lang.String.class
		};
}