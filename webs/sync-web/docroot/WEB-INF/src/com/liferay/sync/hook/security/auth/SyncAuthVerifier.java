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

package com.liferay.sync.hook.security.auth;

import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.security.auth.AccessControlContext;
import com.liferay.portal.security.auth.AuthException;
import com.liferay.portal.security.auth.AuthVerifier;
import com.liferay.portal.security.auth.AuthVerifierResult;
import com.liferay.portal.security.auth.AutoLoginException;
import com.liferay.portal.security.auth.BaseAutoLogin;
import com.liferay.sync.util.PortletPropsValues;

import java.lang.reflect.Method;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Michael Young
 * @author Dennis Ju
 */
public class SyncAuthVerifier extends BaseAutoLogin implements AuthVerifier {

	@Override
	public String getAuthType() {
		return SyncAuthVerifier.class.getSimpleName();
	}

	@Override
	public AuthVerifierResult verify(
			AccessControlContext accessControlContext, Properties properties)
		throws AuthException {

		try {
			AuthVerifierResult authVerifierResult = new AuthVerifierResult();

			String[] credentials = login(
				accessControlContext.getRequest(),
				accessControlContext.getResponse());

			if (credentials != null) {
				authVerifierResult.setPassword(credentials[1]);
				authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);
				authVerifierResult.setUserId(Long.valueOf(credentials[0]));
			}

			return authVerifierResult;
		}
		catch (AutoLoginException ale) {
			throw new AuthException(ale);
		}
	}

	@Override
	protected String[] doLogin(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		for (String autoLoginClassName : _autoLoginClassNames) {
			Thread currentThread = Thread.currentThread();

			ClassLoader classLoader = currentThread.getContextClassLoader();

			try {
				ClassLoader portalClassLoader =
					PortalClassLoaderUtil.getClassLoader();

				currentThread.setContextClassLoader(portalClassLoader);

				Class<?> clazz = portalClassLoader.loadClass(
					autoLoginClassName);

				Object object = clazz.newInstance();

				Method method = ReflectionUtil.getDeclaredMethod(
					clazz, "doLogin", HttpServletRequest.class,
					HttpServletResponse.class);

				String[] credentials = (String[])method.invoke(
					object, request, response);

				if (credentials != null) {
					return credentials;
				}
			}
			finally {
				currentThread.setContextClassLoader(classLoader);
			}
		}

		return null;
	}

	private static String[] _autoLoginClassNames = StringUtil.split(
		PortletPropsValues.SYNC_AUTH_VERIFIER_PIPELINE);

}