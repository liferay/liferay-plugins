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
 * Provides a wrapper for {@link MobileWidgetsAssetEntryService}.
 *
 * @author José Manuel Navarro
 * @see MobileWidgetsAssetEntryService
 * @generated
 */
public class MobileWidgetsAssetEntryServiceWrapper
	implements MobileWidgetsAssetEntryService,
		ServiceWrapper<MobileWidgetsAssetEntryService> {
	public MobileWidgetsAssetEntryServiceWrapper(
		MobileWidgetsAssetEntryService mobileWidgetsAssetEntryService) {
		_mobileWidgetsAssetEntryService = mobileWidgetsAssetEntryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _mobileWidgetsAssetEntryService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_mobileWidgetsAssetEntryService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _mobileWidgetsAssetEntryService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getAssetEntries(
		com.liferay.portlet.asset.service.persistence.AssetEntryQuery assetEntryQuery,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mobileWidgetsAssetEntryService.getAssetEntries(assetEntryQuery,
			locale);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public MobileWidgetsAssetEntryService getWrappedMobileWidgetsAssetEntryService() {
		return _mobileWidgetsAssetEntryService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedMobileWidgetsAssetEntryService(
		MobileWidgetsAssetEntryService mobileWidgetsAssetEntryService) {
		_mobileWidgetsAssetEntryService = mobileWidgetsAssetEntryService;
	}

	@Override
	public MobileWidgetsAssetEntryService getWrappedService() {
		return _mobileWidgetsAssetEntryService;
	}

	@Override
	public void setWrappedService(
		MobileWidgetsAssetEntryService mobileWidgetsAssetEntryService) {
		_mobileWidgetsAssetEntryService = mobileWidgetsAssetEntryService;
	}

	private MobileWidgetsAssetEntryService _mobileWidgetsAssetEntryService;
}