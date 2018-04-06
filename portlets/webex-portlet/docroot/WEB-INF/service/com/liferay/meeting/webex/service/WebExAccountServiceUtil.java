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
 * Provides the remote service utility for WebExAccount. This utility wraps
 * {@link com.liferay.meeting.webex.service.impl.WebExAccountServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Anant Singh
 * @see WebExAccountService
 * @see com.liferay.meeting.webex.service.base.WebExAccountServiceBaseImpl
 * @see com.liferay.meeting.webex.service.impl.WebExAccountServiceImpl
 * @generated
 */
@ProviderType
public class WebExAccountServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.meeting.webex.service.impl.WebExAccountServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.meeting.webex.model.WebExAccount getWebExAccount(
		long webExAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getWebExAccount(webExAccountId);
	}

	public static int getWebExSiteWebExAccountsCount(long groupId,
		long webExSiteId) {
		return getService().getWebExSiteWebExAccountsCount(groupId, webExSiteId);
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

	public static java.util.List<com.liferay.meeting.webex.model.WebExAccount> getWebExSiteWebExAccounts(
		long groupId, long webExSiteId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService()
				   .getWebExSiteWebExAccounts(groupId, webExSiteId, start, end,
			obc);
	}

	public static void addWebExAccount(long groupId, long webExSiteId,
		java.lang.String login, java.lang.String password,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.addWebExAccount(groupId, webExSiteId, login, password,
			serviceContext);
	}

	public static void deleteWebExAccount(long webExAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteWebExAccount(webExAccountId);
	}

	public static void updateWebExAccount(long webExAccountId,
		java.lang.String password,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateWebExAccount(webExAccountId, password, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static WebExAccountService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					WebExAccountService.class.getName());

			if (invokableService instanceof WebExAccountService) {
				_service = (WebExAccountService)invokableService;
			}
			else {
				_service = new WebExAccountServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(WebExAccountServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static WebExAccountService _service;
}