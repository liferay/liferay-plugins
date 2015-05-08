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

package com.liferay.so.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SocialOfficeService}.
 *
 * @author Brian Wing Shun Chan
 * @see SocialOfficeService
 * @generated
 */
@ProviderType
public class SocialOfficeServiceWrapper implements SocialOfficeService,
	ServiceWrapper<SocialOfficeService> {
	public SocialOfficeServiceWrapper(SocialOfficeService socialOfficeService) {
		_socialOfficeService = socialOfficeService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _socialOfficeService.getBeanIdentifier();
	}

	@Override
	public long[] getUserSocialOfficeGroupIds()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _socialOfficeService.getUserSocialOfficeGroupIds();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _socialOfficeService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public boolean isSocialOfficeGroup(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _socialOfficeService.isSocialOfficeGroup(groupId);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_socialOfficeService.setBeanIdentifier(beanIdentifier);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public SocialOfficeService getWrappedSocialOfficeService() {
		return _socialOfficeService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedSocialOfficeService(
		SocialOfficeService socialOfficeService) {
		_socialOfficeService = socialOfficeService;
	}

	@Override
	public SocialOfficeService getWrappedService() {
		return _socialOfficeService;
	}

	@Override
	public void setWrappedService(SocialOfficeService socialOfficeService) {
		_socialOfficeService = socialOfficeService;
	}

	private SocialOfficeService _socialOfficeService;
}