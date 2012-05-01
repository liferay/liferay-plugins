/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.marketplace.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link AppService}.
 * </p>
 *
 * @author    Ryan Park
 * @see       AppService
 * @generated
 */
public class AppServiceWrapper implements AppService,
	ServiceWrapper<AppService> {
	public AppServiceWrapper(AppService appService) {
		_appService = appService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _appService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_appService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _appService.invokeMethod(name, parameterTypes, arguments);
	}

	public com.liferay.marketplace.model.App addApp(long remoteAppId,
		java.lang.String version, java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appService.addApp(remoteAppId, version, inputStream);
	}

	public com.liferay.marketplace.model.App deleteApp(long appId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appService.deleteApp(appId);
	}

	public void installApp(long remoteAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_appService.installApp(remoteAppId);
	}

	public void uninstallApp(long remoteAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_appService.uninstallApp(remoteAppId);
	}

	public com.liferay.marketplace.model.App updateApp(long appId,
		java.lang.String version, java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appService.updateApp(appId, version, inputStream);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AppService getWrappedAppService() {
		return _appService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAppService(AppService appService) {
		_appService = appService;
	}

	public AppService getWrappedService() {
		return _appService;
	}

	public void setWrappedService(AppService appService) {
		_appService = appService;
	}

	private AppService _appService;
}