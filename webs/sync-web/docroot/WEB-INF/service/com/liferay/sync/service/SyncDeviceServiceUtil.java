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

package com.liferay.sync.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the remote service utility for SyncDevice. This utility wraps
 * {@link com.liferay.sync.service.impl.SyncDeviceServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see SyncDeviceService
 * @see com.liferay.sync.service.base.SyncDeviceServiceBaseImpl
 * @see com.liferay.sync.service.impl.SyncDeviceServiceImpl
 * @generated
 */
@ProviderType
public class SyncDeviceServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.sync.service.impl.SyncDeviceServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.sync.model.SyncDevice registerSyncDevice(
		java.lang.String type, int buildNumber, int featureSet,
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .registerSyncDevice(type, buildNumber, featureSet, uuid);
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

	public static void unregisterSyncDevice(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().unregisterSyncDevice(uuid);
	}

	public static void clearService() {
		_service = null;
	}

	public static SyncDeviceService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SyncDeviceService.class.getName());

			if (invokableService instanceof SyncDeviceService) {
				_service = (SyncDeviceService)invokableService;
			}
			else {
				_service = new SyncDeviceServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(SyncDeviceServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static SyncDeviceService _service;
}