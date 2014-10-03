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

package com.liferay.sharing.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SharingEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see SharingEntryService
 * @generated
 */
@ProviderType
public class SharingEntryServiceWrapper implements SharingEntryService,
	ServiceWrapper<SharingEntryService> {
	public SharingEntryServiceWrapper(SharingEntryService sharingEntryService) {
		_sharingEntryService = sharingEntryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _sharingEntryService.getBeanIdentifier();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _sharingEntryService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_sharingEntryService.setBeanIdentifier(beanIdentifier);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public SharingEntryService getWrappedSharingEntryService() {
		return _sharingEntryService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedSharingEntryService(
		SharingEntryService sharingEntryService) {
		_sharingEntryService = sharingEntryService;
	}

	@Override
	public SharingEntryService getWrappedService() {
		return _sharingEntryService;
	}

	@Override
	public void setWrappedService(SharingEntryService sharingEntryService) {
		_sharingEntryService = sharingEntryService;
	}

	private SharingEntryService _sharingEntryService;
}