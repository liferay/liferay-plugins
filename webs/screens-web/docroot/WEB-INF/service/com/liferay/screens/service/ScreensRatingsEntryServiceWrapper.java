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
 * Provides a wrapper for {@link ScreensRatingsEntryService}.
 *
 * @author José Manuel Navarro
 * @see ScreensRatingsEntryService
 * @generated
 */
public class ScreensRatingsEntryServiceWrapper
	implements ScreensRatingsEntryService,
		ServiceWrapper<ScreensRatingsEntryService> {
	public ScreensRatingsEntryServiceWrapper(
		ScreensRatingsEntryService screensRatingsEntryService) {
		_screensRatingsEntryService = screensRatingsEntryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _screensRatingsEntryService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_screensRatingsEntryService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _screensRatingsEntryService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject deleteRatingsEntry(
		long classPK, java.lang.String className, int ratingsLength)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _screensRatingsEntryService.deleteRatingsEntry(classPK,
			className, ratingsLength);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getRatingsEntries(
		long assetEntryId, int ratingsLength)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _screensRatingsEntryService.getRatingsEntries(assetEntryId,
			ratingsLength);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getRatingsEntries(
		long classPK, java.lang.String className, int ratingsLength)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _screensRatingsEntryService.getRatingsEntries(classPK,
			className, ratingsLength);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject updateRatingsEntry(
		long classPK, java.lang.String className, double score,
		int ratingsLength)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _screensRatingsEntryService.updateRatingsEntry(classPK,
			className, score, ratingsLength);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public ScreensRatingsEntryService getWrappedScreensRatingsEntryService() {
		return _screensRatingsEntryService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedScreensRatingsEntryService(
		ScreensRatingsEntryService screensRatingsEntryService) {
		_screensRatingsEntryService = screensRatingsEntryService;
	}

	@Override
	public ScreensRatingsEntryService getWrappedService() {
		return _screensRatingsEntryService;
	}

	@Override
	public void setWrappedService(
		ScreensRatingsEntryService screensRatingsEntryService) {
		_screensRatingsEntryService = screensRatingsEntryService;
	}

	private ScreensRatingsEntryService _screensRatingsEntryService;
}