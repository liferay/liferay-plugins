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

package com.liferay.oauth.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.oauth.service.OAuthApplicationServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.security.auth.HttpPrincipal;
import com.liferay.portal.service.http.TunnelUtil;

/**
 * Provides the HTTP utility for the
 * {@link OAuthApplicationServiceUtil} service utility. The
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
 * @author Ivica Cardic
 * @see OAuthApplicationServiceSoap
 * @see HttpPrincipal
 * @see OAuthApplicationServiceUtil
 * @generated
 */
@ProviderType
public class OAuthApplicationServiceHttp {
	public static com.liferay.oauth.model.OAuthApplication addOAuthApplication(
		HttpPrincipal httpPrincipal, java.lang.String name,
		java.lang.String description, int accessLevel,
		boolean shareableAccessToken, java.lang.String callbackURI,
		java.lang.String websiteURL,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(OAuthApplicationServiceUtil.class,
					"addOAuthApplication", _addOAuthApplicationParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, name,
					description, accessLevel, shareableAccessToken,
					callbackURI, websiteURL, serviceContext);

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

			return (com.liferay.oauth.model.OAuthApplication)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteLogo(HttpPrincipal httpPrincipal,
		long oAuthApplicationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(OAuthApplicationServiceUtil.class,
					"deleteLogo", _deleteLogoParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					oAuthApplicationId);

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

	public static com.liferay.oauth.model.OAuthApplication deleteOAuthApplication(
		HttpPrincipal httpPrincipal, long oAuthApplicationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(OAuthApplicationServiceUtil.class,
					"deleteOAuthApplication",
					_deleteOAuthApplicationParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					oAuthApplicationId);

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

			return (com.liferay.oauth.model.OAuthApplication)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.oauth.model.OAuthApplication updateLogo(
		HttpPrincipal httpPrincipal, long oAuthApplicationId,
		java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(OAuthApplicationServiceUtil.class,
					"updateLogo", _updateLogoParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					oAuthApplicationId, inputStream);

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

			return (com.liferay.oauth.model.OAuthApplication)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.oauth.model.OAuthApplication updateOAuthApplication(
		HttpPrincipal httpPrincipal, long oAuthApplicationId,
		java.lang.String name, java.lang.String description,
		boolean shareableAccessToken, java.lang.String callbackURI,
		java.lang.String websiteURL,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(OAuthApplicationServiceUtil.class,
					"updateOAuthApplication",
					_updateOAuthApplicationParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					oAuthApplicationId, name, description,
					shareableAccessToken, callbackURI, websiteURL,
					serviceContext);

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

			return (com.liferay.oauth.model.OAuthApplication)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(OAuthApplicationServiceHttp.class);
	private static final Class<?>[] _addOAuthApplicationParameterTypes0 = new Class[] {
			java.lang.String.class, java.lang.String.class, int.class,
			boolean.class, java.lang.String.class, java.lang.String.class,
			com.liferay.portal.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteLogoParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[] _deleteOAuthApplicationParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _updateLogoParameterTypes3 = new Class[] {
			long.class, java.io.InputStream.class
		};
	private static final Class<?>[] _updateOAuthApplicationParameterTypes4 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			boolean.class, java.lang.String.class, java.lang.String.class,
			com.liferay.portal.service.ServiceContext.class
		};
}