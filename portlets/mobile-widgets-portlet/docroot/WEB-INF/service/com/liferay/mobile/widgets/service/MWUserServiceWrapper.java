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

package com.liferay.mobile.widgets.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MWUserService}.
 *
 * @author Jose M. Navarro
 * @see MWUserService
 * @generated
 */
public class MWUserServiceWrapper implements MWUserService,
	ServiceWrapper<MWUserService> {
	public MWUserServiceWrapper(MWUserService mwUserService) {
		_mwUserService = mwUserService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _mwUserService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_mwUserService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _mwUserService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public boolean sendPasswordByEmailAddress(long companyId,
		java.lang.String emailAddress,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return _mwUserService.sendPasswordByEmailAddress(companyId,
			emailAddress, serviceContext);
	}

	@Override
	public boolean sendPasswordByScreenName(long companyId,
		java.lang.String screenName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return _mwUserService.sendPasswordByScreenName(companyId, screenName,
			serviceContext);
	}

	@Override
	public boolean sendPasswordByUserId(long companyId, long userId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return _mwUserService.sendPasswordByUserId(companyId, userId,
			serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public MWUserService getWrappedMWUserService() {
		return _mwUserService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedMWUserService(MWUserService mwUserService) {
		_mwUserService = mwUserService;
	}

	@Override
	public MWUserService getWrappedService() {
		return _mwUserService;
	}

	@Override
	public void setWrappedService(MWUserService mwUserService) {
		_mwUserService = mwUserService;
	}

	private MWUserService _mwUserService;
}