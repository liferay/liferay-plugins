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

package com.liferay.marketplace.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AppService}.
 *
 * @author Ryan Park
 * @see AppService
 * @generated
 */
public class AppServiceWrapper implements AppService,
	ServiceWrapper<AppService> {
	public AppServiceWrapper(AppService appService) {
		_appService = appService;
	}

	@Override
	public com.liferay.marketplace.model.App deleteApp(long appId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _appService.deleteApp(appId);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _appService.getBeanIdentifier();
	}

	@Override
	public void installApp(long remoteAppId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_appService.installApp(remoteAppId);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _appService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_appService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public void uninstallApp(long remoteAppId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_appService.uninstallApp(remoteAppId);
	}

	@Override
	public com.liferay.marketplace.model.App updateApp(long remoteAppId,
		java.lang.String version, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _appService.updateApp(remoteAppId, version, file);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public AppService getWrappedAppService() {
		return _appService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedAppService(AppService appService) {
		_appService = appService;
	}

	@Override
	public AppService getWrappedService() {
		return _appService;
	}

	@Override
	public void setWrappedService(AppService appService) {
		_appService = appService;
	}

	private AppService _appService;
}