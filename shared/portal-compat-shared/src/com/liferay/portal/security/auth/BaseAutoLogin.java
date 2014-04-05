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

package com.liferay.portal.security.auth;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mate Thurzo
 */
public abstract class BaseAutoLogin implements AutoLogin {

	public String[] handleException(
			HttpServletRequest request, HttpServletResponse response,
			Exception e)
		throws AutoLoginException {

		return doHandleException(request, response, e);
	}

	public String[] login(
			HttpServletRequest request, HttpServletResponse response)
		throws AutoLoginException {

		try {
			return doLogin(request, response);
		}
		catch (Exception e) {
			return handleException(request, response, e);
		}
	}

	protected String[] doHandleException(
			HttpServletRequest request, HttpServletResponse response,
			Exception e)
		throws AutoLoginException {

		if (request.getAttribute(AutoLogin.AUTO_LOGIN_REDIRECT) == null) {
			throw new AutoLoginException(e);
		}

		_log.error(e, e);

		return null;
	}

	protected abstract String[] doLogin(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception;

	private static Log _log = LogFactoryUtil.getLog(BaseAutoLogin.class);

}