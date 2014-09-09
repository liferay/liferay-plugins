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
 * Provides a wrapper for {@link MobileWidgetsDDLService}.
 *
 * @author Jose Manuel Navarro
 * @see MobileWidgetsDDLService
 * @generated
 */
public class MobileWidgetsDDLServiceWrapper implements MobileWidgetsDDLService,
	ServiceWrapper<MobileWidgetsDDLService> {
	public MobileWidgetsDDLServiceWrapper(
		MobileWidgetsDDLService mobileWidgetsDDLService) {
		_mobileWidgetsDDLService = mobileWidgetsDDLService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _mobileWidgetsDDLService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_mobileWidgetsDDLService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _mobileWidgetsDDLService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public java.util.Map<java.lang.String, java.lang.String> getDDLRecordValues(
		long recordId, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mobileWidgetsDDLService.getDDLRecordValues(recordId, locale);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public MobileWidgetsDDLService getWrappedMobileWidgetsDDLService() {
		return _mobileWidgetsDDLService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedMobileWidgetsDDLService(
		MobileWidgetsDDLService mobileWidgetsDDLService) {
		_mobileWidgetsDDLService = mobileWidgetsDDLService;
	}

	@Override
	public MobileWidgetsDDLService getWrappedService() {
		return _mobileWidgetsDDLService;
	}

	@Override
	public void setWrappedService(
		MobileWidgetsDDLService mobileWidgetsDDLService) {
		_mobileWidgetsDDLService = mobileWidgetsDDLService;
	}

	private MobileWidgetsDDLService _mobileWidgetsDDLService;
}