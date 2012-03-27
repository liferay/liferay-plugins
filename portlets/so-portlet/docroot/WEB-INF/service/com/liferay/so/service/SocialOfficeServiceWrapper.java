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

package com.liferay.so.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link SocialOfficeService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SocialOfficeService
 * @generated
 */
public class SocialOfficeServiceWrapper implements SocialOfficeService,
	ServiceWrapper<SocialOfficeService> {
	public SocialOfficeServiceWrapper(SocialOfficeService socialOfficeService) {
		_socialOfficeService = socialOfficeService;
	}

	public boolean isSocialOfficeSite(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _socialOfficeService.isSocialOfficeSite(groupId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SocialOfficeService getWrappedSocialOfficeService() {
		return _socialOfficeService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSocialOfficeService(
		SocialOfficeService socialOfficeService) {
		_socialOfficeService = socialOfficeService;
	}

	public SocialOfficeService getWrappedService() {
		return _socialOfficeService;
	}

	public void setWrappedService(SocialOfficeService socialOfficeService) {
		_socialOfficeService = socialOfficeService;
	}

	private SocialOfficeService _socialOfficeService;
}