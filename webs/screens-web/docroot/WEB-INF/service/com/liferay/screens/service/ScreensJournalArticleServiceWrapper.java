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
 * Provides a wrapper for {@link ScreensJournalArticleService}.
 *
 * @author José Manuel Navarro
 * @see ScreensJournalArticleService
 * @generated
 */
public class ScreensJournalArticleServiceWrapper
	implements ScreensJournalArticleService,
		ServiceWrapper<ScreensJournalArticleService> {
	public ScreensJournalArticleServiceWrapper(
		ScreensJournalArticleService screensJournalArticleService) {
		_screensJournalArticleService = screensJournalArticleService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _screensJournalArticleService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_screensJournalArticleService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _screensJournalArticleService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public java.lang.String getJournalArticle(int groupId, int classPK,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _screensJournalArticleService.getJournalArticle(groupId,
			classPK, locale);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public ScreensJournalArticleService getWrappedScreensJournalArticleService() {
		return _screensJournalArticleService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedScreensJournalArticleService(
		ScreensJournalArticleService screensJournalArticleService) {
		_screensJournalArticleService = screensJournalArticleService;
	}

	@Override
	public ScreensJournalArticleService getWrappedService() {
		return _screensJournalArticleService;
	}

	@Override
	public void setWrappedService(
		ScreensJournalArticleService screensJournalArticleService) {
		_screensJournalArticleService = screensJournalArticleService;
	}

	private ScreensJournalArticleService _screensJournalArticleService;
}