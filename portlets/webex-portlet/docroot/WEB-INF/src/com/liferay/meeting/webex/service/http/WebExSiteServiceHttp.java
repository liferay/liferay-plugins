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
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.security.auth.HttpPrincipal;
import com.liferay.portal.service.http.TunnelUtil;

/**
 * Provides the HTTP utility for the
 * {@link WebExSiteServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Anant Singh
 * @see WebExSiteServiceSoap
 * @see HttpPrincipal
 * @see WebExSiteServiceUtil
 * @generated
 */
@ProviderType
public class WebExSiteServiceHttp {
	public static void addWebExSite(HttpPrincipal httpPrincipal, long groupId,
		java.lang.String name, java.lang.String apiURL, java.lang.String login,
		java.lang.String password, java.lang.String partnerKey, long siteKey,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(WebExSiteServiceUtil.class,
					"addWebExSite", _addWebExSiteParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					name, apiURL, login, password, partnerKey, siteKey,
					serviceContext);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.meeting.webex.model.WebExSite deleteWebExSite(
		HttpPrincipal httpPrincipal, long webExSiteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(WebExSiteServiceUtil.class,
					"deleteWebExSite", _deleteWebExSiteParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					webExSiteId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.meeting.webex.model.WebExSite)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.meeting.webex.model.WebExSite fetchSiteKeyWebExSite(
		HttpPrincipal httpPrincipal, long siteKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(WebExSiteServiceUtil.class,
					"fetchSiteKeyWebExSite",
					_fetchSiteKeyWebExSiteParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, siteKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.meeting.webex.model.WebExSite)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.meeting.webex.model.WebExSite getWebExSite(
		HttpPrincipal httpPrincipal, long webExSiteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(WebExSiteServiceUtil.class,
					"getWebExSite", _getWebExSiteParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					webExSiteId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.meeting.webex.model.WebExSite)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.meeting.webex.model.WebExSite> getWebExSites(
		HttpPrincipal httpPrincipal, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		try {
			MethodKey methodKey = new MethodKey(WebExSiteServiceUtil.class,
					"getWebExSites", _getWebExSitesParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.meeting.webex.model.WebExSite>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getWebExSitesCount(HttpPrincipal httpPrincipal,
		long groupId) {
		try {
			MethodKey methodKey = new MethodKey(WebExSiteServiceUtil.class,
					"getWebExSitesCount", _getWebExSitesCountParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void updateWebExSite(HttpPrincipal httpPrincipal,
		long webExSiteId, java.lang.String apiURL, java.lang.String login,
		java.lang.String password,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(WebExSiteServiceUtil.class,
					"updateWebExSite", _updateWebExSiteParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					webExSiteId, apiURL, login, password, serviceContext);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(WebExSiteServiceHttp.class);
	private static final Class<?>[] _addWebExSiteParameterTypes0 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, long.class,
			com.liferay.portal.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteWebExSiteParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[] _fetchSiteKeyWebExSiteParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getWebExSiteParameterTypes3 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getWebExSitesParameterTypes4 = new Class[] {
			long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getWebExSitesCountParameterTypes5 = new Class[] {
			long.class
		};
	private static final Class<?>[] _updateWebExSiteParameterTypes6 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			java.lang.String.class,
			com.liferay.portal.service.ServiceContext.class
		};
}