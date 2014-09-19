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

package com.liferay.mobilewidgets.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MobileWidgetsUserService}.
 *
 * @author José Manuel Navarro
 * @see MobileWidgetsUserService
 * @generated
 */
public class MobileWidgetsUserServiceWrapper implements MobileWidgetsUserService,
	ServiceWrapper<MobileWidgetsUserService> {
	public MobileWidgetsUserServiceWrapper(
		MobileWidgetsUserService mobileWidgetsUserService) {
		_mobileWidgetsUserService = mobileWidgetsUserService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _mobileWidgetsUserService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_mobileWidgetsUserService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _mobileWidgetsUserService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public boolean sendPasswordByEmailAddress(long companyId,
		java.lang.String emailAddress)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mobileWidgetsUserService.sendPasswordByEmailAddress(companyId,
			emailAddress);
	}

	@Override
	public boolean sendPasswordByScreenName(long companyId,
		java.lang.String screenName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mobileWidgetsUserService.sendPasswordByScreenName(companyId,
			screenName);
	}

	@Override
	public boolean sendPasswordByUserId(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mobileWidgetsUserService.sendPasswordByUserId(userId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public MobileWidgetsUserService getWrappedMobileWidgetsUserService() {
		return _mobileWidgetsUserService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedMobileWidgetsUserService(
		MobileWidgetsUserService mobileWidgetsUserService) {
		_mobileWidgetsUserService = mobileWidgetsUserService;
	}

	@Override
	public MobileWidgetsUserService getWrappedService() {
		return _mobileWidgetsUserService;
	}

	@Override
	public void setWrappedService(
		MobileWidgetsUserService mobileWidgetsUserService) {
		_mobileWidgetsUserService = mobileWidgetsUserService;
	}

	private MobileWidgetsUserService _mobileWidgetsUserService;
}