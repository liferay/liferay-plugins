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

package com.liferay.screens.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ScreensAssetEntryService}.
 *
 * @author José Manuel Navarro
 * @see ScreensAssetEntryService
 * @generated
 */
public class ScreensAssetEntryServiceWrapper implements ScreensAssetEntryService,
	ServiceWrapper<ScreensAssetEntryService> {
	public ScreensAssetEntryServiceWrapper(
		ScreensAssetEntryService screensAssetEntryService) {
		_screensAssetEntryService = screensAssetEntryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _screensAssetEntryService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_screensAssetEntryService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _screensAssetEntryService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getAssetEntries(
		com.liferay.portlet.asset.service.persistence.AssetEntryQuery assetEntryQuery,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _screensAssetEntryService.getAssetEntries(assetEntryQuery, locale);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getFilteredAssetEntries(
		long companyId, long groupId, java.lang.String portletItemName,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _screensAssetEntryService.getFilteredAssetEntries(companyId,
			groupId, portletItemName, locale);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public ScreensAssetEntryService getWrappedScreensAssetEntryService() {
		return _screensAssetEntryService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedScreensAssetEntryService(
		ScreensAssetEntryService screensAssetEntryService) {
		_screensAssetEntryService = screensAssetEntryService;
	}

	@Override
	public ScreensAssetEntryService getWrappedService() {
		return _screensAssetEntryService;
	}

	@Override
	public void setWrappedService(
		ScreensAssetEntryService screensAssetEntryService) {
		_screensAssetEntryService = screensAssetEntryService;
	}

	private ScreensAssetEntryService _screensAssetEntryService;
}