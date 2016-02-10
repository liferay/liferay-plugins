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

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SyncDeviceService}.
 *
 * @author Brian Wing Shun Chan
 * @see SyncDeviceService
 * @generated
 */
public class SyncDeviceServiceWrapper implements SyncDeviceService,
	ServiceWrapper<SyncDeviceService> {
	public SyncDeviceServiceWrapper(SyncDeviceService syncDeviceService) {
		_syncDeviceService = syncDeviceService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _syncDeviceService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_syncDeviceService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _syncDeviceService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public com.liferay.sync.model.SyncDevice registerSyncDevice(
		java.lang.String type, int buildNumber, int featureSet,
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _syncDeviceService.registerSyncDevice(type, buildNumber,
			featureSet, uuid);
	}

	@Override
	public void unregisterSyncDevice(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_syncDeviceService.unregisterSyncDevice(uuid);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public SyncDeviceService getWrappedSyncDeviceService() {
		return _syncDeviceService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedSyncDeviceService(SyncDeviceService syncDeviceService) {
		_syncDeviceService = syncDeviceService;
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