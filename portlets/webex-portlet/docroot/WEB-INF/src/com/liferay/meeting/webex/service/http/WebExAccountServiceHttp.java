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

import com.liferay.meeting.webex.service.WebExAccountServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.service.http.TunnelUtil;

/**
 * Provides the HTTP utility for the
 * {@link WebExAccountServiceUtil} service utility. The
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
 * @see WebExAccountServiceSoap
 * @see HttpPrincipal
 * @see WebExAccountServiceUtil
 * @generated
 */
@ProviderType
public class WebExAccountServiceHttp {
	public static void addWebExAccount(HttpPrincipal httpPrincipal,
		long groupId, long webExSiteId, java.lang.String login,
		java.lang.String password,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(WebExAccountServiceUtil.class,
					"addWebExAccount", _addWebExAccountParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					webExSiteId, login, password, serviceContext);

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

	public static void deleteWebExAccount(HttpPrincipal httpPrincipal,
		long webExAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(WebExAccountServiceUtil.class,
					"deleteWebExAccount", _deleteWebExAccountParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					webExAccountId);

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

	public static com.liferay.meeting.webex.model.WebExAccount getWebExAccount(
		HttpPrincipal httpPrincipal, long webExAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(WebExAccountServiceUtil.class,
					"getWebExAccount", _getWebExAccountParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					webExAccountId);

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

			return (com.liferay.meeting.webex.model.WebExAccount)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.meeting.webex.model.WebExAccount> getWebExSiteWebExAccounts(
		HttpPrincipal httpPrincipal, long groupId, long webExSiteId, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		try {
			MethodKey methodKey = new MethodKey(WebExAccountServiceUtil.class,
					"getWebExSiteWebExAccounts",
					_getWebExSiteWebExAccountsParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					webExSiteId, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.meeting.webex.model.WebExAccount>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getWebExSiteWebExAccountsCount(
		HttpPrincipal httpPrincipal, long groupId, long webExSiteId) {
		try {
			MethodKey methodKey = new MethodKey(WebExAccountServiceUtil.class,
					"getWebExSiteWebExAccountsCount",
					_getWebExSiteWebExAccountsCountParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					webExSiteId);

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

	public static void updateWebExAccount(HttpPrincipal httpPrincipal,
		long webExAccountId, java.lang.String password,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(WebExAccountServiceUtil.class,
					"updateWebExAccount", _updateWebExAccountParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					webExAccountId, password, serviceContext);

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

	private static Log _log = LogFactoryUtil.getLog(WebExAccountServiceHttp.class);
	private static final Class<?>[] _addWebExAccountParameterTypes0 = new Class[] {
			long.class, long.class, java.lang.String.class,
			java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteWebExAccountParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getWebExAccountParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getWebExSiteWebExAccountsParameterTypes3 = new Class[] {
			long.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getWebExSiteWebExAccountsCountParameterTypes4 =
		new Class[] { long.class, long.class };
	private static final Class<?>[] _updateWebExAccountParameterTypes5 = new Class[] {
			long.class, java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
}