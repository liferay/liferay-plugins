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

package com.liferay.meeting.webex.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.meeting.webex.service.WebExSiteServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link WebExSiteServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.meeting.webex.model.WebExSiteSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.meeting.webex.model.WebExSite}, that is translated to a
 * {@link com.liferay.meeting.webex.model.WebExSiteSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Anant Singh
 * @see WebExSiteServiceHttp
 * @see com.liferay.meeting.webex.model.WebExSiteSoap
 * @see WebExSiteServiceUtil
 * @generated
 */
@ProviderType
public class WebExSiteServiceSoap {
	public static void addWebExSite(long groupId, java.lang.String name,
		java.lang.String apiURL, java.lang.String login,
		java.lang.String password, java.lang.String partnerKey, long siteKey,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			WebExSiteServiceUtil.addWebExSite(groupId, name, apiURL, login,
				password, partnerKey, siteKey, serviceContext);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.meeting.webex.model.WebExSiteSoap deleteWebExSite(
		long webExSiteId) throws RemoteException {
		try {
			com.liferay.meeting.webex.model.WebExSite returnValue = WebExSiteServiceUtil.deleteWebExSite(webExSiteId);

			return com.liferay.meeting.webex.model.WebExSiteSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.meeting.webex.model.WebExSiteSoap fetchSiteKeyWebExSite(
		long siteKey) throws RemoteException {
		try {
			com.liferay.meeting.webex.model.WebExSite returnValue = WebExSiteServiceUtil.fetchSiteKeyWebExSite(siteKey);

			return com.liferay.meeting.webex.model.WebExSiteSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.meeting.webex.model.WebExSiteSoap getWebExSite(
		long webExSiteId) throws RemoteException {
		try {
			com.liferay.meeting.webex.model.WebExSite returnValue = WebExSiteServiceUtil.getWebExSite(webExSiteId);

			return com.liferay.meeting.webex.model.WebExSiteSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.meeting.webex.model.WebExSiteSoap[] getWebExSites(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws RemoteException {
		try {
			java.util.List<com.liferay.meeting.webex.model.WebExSite> returnValue =
				WebExSiteServiceUtil.getWebExSites(groupId, start, end, obc);

			return com.liferay.meeting.webex.model.WebExSiteSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getWebExSitesCount(long groupId)
		throws RemoteException {
		try {
			int returnValue = WebExSiteServiceUtil.getWebExSitesCount(groupId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void updateWebExSite(long webExSiteId,
		java.lang.String apiURL, java.lang.String login,
		java.lang.String password,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			WebExSiteServiceUtil.updateWebExSite(webExSiteId, apiURL, login,
				password, serviceContext);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(WebExSiteServiceSoap.class);
}