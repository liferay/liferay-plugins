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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SyncDeviceService}.
 *
 * @author Brian Wing Shun Chan
 * @see SyncDeviceService
 * @generated
 */
@ProviderType
public class SyncDeviceServiceWrapper implements SyncDeviceService,
	ServiceWrapper<SyncDeviceService> {
	public SyncDeviceServiceWrapper(SyncDeviceService syncDeviceService) {
		_syncDeviceService = syncDeviceService;
	}

	@Override
	public com.liferay.sync.model.SyncDevice registerSyncDevice(
		java.lang.String type, int buildNumber, int featureSet,
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _syncDeviceService.registerSyncDevice(type, buildNumber,
			featureSet, uuid);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _syncDeviceService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _syncDeviceService.getOSGiServiceIdentifier();
	}

	@Override
	public void unregisterSyncDevice(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		_syncDeviceService.unregisterSyncDevice(uuid);
	}

	@Override
	public SyncDeviceService getWrappedService() {
		return _syncDeviceService;
	}

	@Override
	public void setWrappedService(SyncDeviceService syncDeviceService) {
		_syncDeviceService = syncDeviceService;
	}

	private SyncDeviceService _syncDeviceService;
}