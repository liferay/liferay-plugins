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

package com.liferay.samplepersonaldata.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SPDService}.
 *
 * @author Brian Wing Shun Chan
 * @see SPDService
 * @generated
 */
public class SPDServiceWrapper implements SPDService,
	ServiceWrapper<SPDService> {
	public SPDServiceWrapper(SPDService spdService) {
		_spdService = spdService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _spdService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_spdService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _spdService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public void anonymizeBlogsEntries(long userId, long anonymousUserId)
		throws java.lang.Exception {
		_spdService.anonymizeBlogsEntries(userId, anonymousUserId);
	}

	@Override
	public void deleteBlogsEntries(long userId) throws java.lang.Exception {
		_spdService.deleteBlogsEntries(userId);
	}

	@Override
	public java.lang.String exportBlogsEntries(long userId)
		throws java.lang.Exception {
		return _spdService.exportBlogsEntries(userId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public SPDService getWrappedSPDService() {
		return _spdService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedSPDService(SPDService spdService) {
		_spdService = spdService;
	}

	@Override
	public SPDService getWrappedService() {
		return _spdService;
	}

	@Override
	public void setWrappedService(SPDService spdService) {
		_spdService = spdService;
	}

	private SPDService _spdService;
}