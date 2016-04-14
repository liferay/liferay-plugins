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

import com.liferay.portal.kernel.service.ServiceWrapper;

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

	@Override
	public boolean isSocialOfficeGroup(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _socialOfficeService.isSocialOfficeGroup(groupId);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _socialOfficeService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _socialOfficeService.getOSGiServiceIdentifier();
	}

	@Override
	public long[] getUserSocialOfficeGroupIds()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _socialOfficeService.getUserSocialOfficeGroupIds();
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