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

package com.liferay.meeting.webex.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link WebExSiteService}.
 *
 * @author Anant Singh
 * @see WebExSiteService
 * @generated
 */
@ProviderType
public class WebExSiteServiceWrapper implements WebExSiteService,
	ServiceWrapper<WebExSiteService> {
	public WebExSiteServiceWrapper(WebExSiteService webExSiteService) {
		_webExSiteService = webExSiteService;
	}

	@Override
	public com.liferay.meeting.webex.model.WebExSite deleteWebExSite(
		long webExSiteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _webExSiteService.deleteWebExSite(webExSiteId);
	}

	@Override
	public com.liferay.meeting.webex.model.WebExSite fetchSiteKeyWebExSite(
		long siteKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _webExSiteService.fetchSiteKeyWebExSite(siteKey);
	}

	@Override
	public com.liferay.meeting.webex.model.WebExSite getWebExSite(
		long webExSiteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _webExSiteService.getWebExSite(webExSiteId);
	}

	@Override
	public int getWebExSitesCount(long groupId) {
		return _webExSiteService.getWebExSitesCount(groupId);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _webExSiteService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _webExSiteService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.meeting.webex.model.WebExSite> getWebExSites(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _webExSiteService.getWebExSites(groupId, start, end, obc);
	}

	@Override
	public void addWebExSite(long groupId, java.lang.String name,
		java.lang.String apiURL, java.lang.String login,
		java.lang.String password, java.lang.String partnerKey, long siteKey,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_webExSiteService.addWebExSite(groupId, name, apiURL, login, password,
			partnerKey, siteKey, serviceContext);
	}

	@Override
	public void updateWebExSite(long webExSiteId, java.lang.String apiURL,
		java.lang.String login, java.lang.String password,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_webExSiteService.updateWebExSite(webExSiteId, apiURL, login, password,
			serviceContext);
	}

	@Override
	public WebExSiteService getWrappedService() {
		return _webExSiteService;
	}

	@Override
	public void setWrappedService(WebExSiteService webExSiteService) {
		_webExSiteService = webExSiteService;
	}

	private WebExSiteService _webExSiteService;
}