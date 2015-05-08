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

package com.liferay.sampleservicebuilder.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FooService}.
 *
 * @author Brian Wing Shun Chan
 * @see FooService
 * @generated
 */
@ProviderType
public class FooServiceWrapper implements FooService,
	ServiceWrapper<FooService> {
	public FooServiceWrapper(FooService fooService) {
		_fooService = fooService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _fooService.getBeanIdentifier();
	}

	@Override
	public com.liferay.portal.model.User getUser(long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fooService.getUser(userId);
	}

	@Override
	public java.util.List<com.liferay.portal.model.Group> getUserSitesGroups()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fooService.getUserSitesGroups();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _fooService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_fooService.setBeanIdentifier(beanIdentifier);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public FooService getWrappedFooService() {
		return _fooService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedFooService(FooService fooService) {
		_fooService = fooService;
	}

	@Override
	public FooService getWrappedService() {
		return _fooService;
	}

	@Override
	public void setWrappedService(FooService fooService) {
		_fooService = fooService;
	}

	private FooService _fooService;
}