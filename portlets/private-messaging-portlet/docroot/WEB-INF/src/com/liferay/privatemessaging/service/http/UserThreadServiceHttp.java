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

package com.liferay.privatemessaging.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.security.auth.HttpPrincipal;
import com.liferay.portal.service.http.TunnelUtil;

import com.liferay.privatemessaging.service.UserThreadServiceUtil;

/**
 * Provides the HTTP utility for the
 * {@link UserThreadServiceUtil} service utility. The
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
 * @author Brian Wing Shun Chan
 * @see UserThreadServiceSoap
 * @see HttpPrincipal
 * @see UserThreadServiceUtil
 * @generated
 */
@ProviderType
public class UserThreadServiceHttp {
	public static com.liferay.portlet.messageboards.model.MBMessage getLastThreadMessage(
		HttpPrincipal httpPrincipal, long mbThreadId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(UserThreadServiceUtil.class,
					"getLastThreadMessage", _getLastThreadMessageParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					mbThreadId);

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

			return (com.liferay.portlet.messageboards.model.MBMessage)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portlet.messageboards.model.MBMessage> getThreadMessages(
		HttpPrincipal httpPrincipal, long mbThreadId, int start, int end,
		boolean ascending)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(UserThreadServiceUtil.class,
					"getThreadMessages", _getThreadMessagesParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					mbThreadId, start, end, ascending);

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

			return (java.util.List<com.liferay.portlet.messageboards.model.MBMessage>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getThreadMessagesCount(HttpPrincipal httpPrincipal,
		long mbThreadId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(UserThreadServiceUtil.class,
					"getThreadMessagesCount",
					_getThreadMessagesCountParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					mbThreadId);

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

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.privatemessaging.model.UserThread> getUserUserThreads(
		HttpPrincipal httpPrincipal, boolean deleted)
		throws com.liferay.portal.security.auth.PrincipalException {
		try {
			MethodKey methodKey = new MethodKey(UserThreadServiceUtil.class,
					"getUserUserThreads", _getUserUserThreadsParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, deleted);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.security.auth.PrincipalException) {
					throw (com.liferay.portal.security.auth.PrincipalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.privatemessaging.model.UserThread>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(UserThreadServiceHttp.class);
	private static final Class<?>[] _getLastThreadMessageParameterTypes0 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getThreadMessagesParameterTypes1 = new Class[] {
			long.class, int.class, int.class, boolean.class
		};
	private static final Class<?>[] _getThreadMessagesCountParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getUserUserThreadsParameterTypes3 = new Class[] {
			boolean.class
		};
}