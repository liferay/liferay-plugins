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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the remote service utility for WebExSite. This utility wraps
 * {@link com.liferay.meeting.webex.service.impl.WebExSiteServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Anant Singh
 * @see WebExSiteService
 * @see com.liferay.meeting.webex.service.base.WebExSiteServiceBaseImpl
 * @see com.liferay.meeting.webex.service.impl.WebExSiteServiceImpl
 * @generated
 */
@ProviderType
public class WebExSiteServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.meeting.webex.service.impl.WebExSiteServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.meeting.webex.model.WebExSite deleteWebExSite(
		long webExSiteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteWebExSite(webExSiteId);
	}

	public static com.liferay.meeting.webex.model.WebExSite fetchSiteKeyWebExSite(
		long siteKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().fetchSiteKeyWebExSite(siteKey);
	}

	public static com.liferay.meeting.webex.model.WebExSite getWebExSite(
		long webExSiteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getWebExSite(webExSiteId);
	}

	public static int getWebExSitesCount(long groupId) {
		return getService().getWebExSitesCount(groupId);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.meeting.webex.model.WebExSite> getWebExSites(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService().getWebExSites(groupId, start, end, obc);
	}

	public static void addWebExSite(long groupId, java.lang.String name,
		java.lang.String apiURL, java.lang.String login,
		java.lang.String password, java.lang.String partnerKey, long siteKey,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.addWebExSite(groupId, name, apiURL, login, password, partnerKey,
			siteKey, serviceContext);
	}

	public static void updateWebExSite(long webExSiteId,
		java.lang.String apiURL, java.lang.String login,
		java.lang.String password,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.updateWebExSite(webExSiteId, apiURL, login, password,
			serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static WebExSiteService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					WebExSiteService.class.getName());

			if (invokableService instanceof WebExSiteService) {
				_service = (WebExSiteService)invokableService;
			}
			else {
				_service = new WebExSiteServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(WebExSiteServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static WebExSiteService _service;
}