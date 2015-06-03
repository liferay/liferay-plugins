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

package com.liferay.google.login.hook.security.auto.login;

import com.liferay.google.login.util.WebKeys;
import com.liferay.portal.kernel.security.auto.login.BaseAutoLogin;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
* @author Sergio González
*/
public class GoogleAutoLogin extends BaseAutoLogin {

	@Override
	protected String[] doLogin(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		long companyId = PortalUtil.getCompanyId(request);

		boolean googleAuthEnabled = PrefsPropsUtil.getBoolean(
			companyId, "google-auth-enabled", true);

		if (!googleAuthEnabled) {
			return null;
		}

		User user = getUser(request, companyId);

		if (user == null) {
			return null;
		}

		String[] credentials = new String[3];

		credentials[0] = String.valueOf(user.getUserId());
		credentials[1] = user.getPassword();
		credentials[2] = Boolean.TRUE.toString();

		return credentials;
	}

	protected User getUser(HttpServletRequest request, long companyId)
		throws Exception {

		HttpSession session = request.getSession();

		String emailAddress = GetterUtil.getString(
			session.getAttribute(WebKeys.GOOGLE_USER_EMAIL_ADDRESS));

		if (Validator.isNull(emailAddress)) {
			return null;
		}

		session.removeAttribute(WebKeys.GOOGLE_USER_EMAIL_ADDRESS);

		User user = UserLocalServiceUtil.getUserByEmailAddress(
			companyId, emailAddress);

		return user;
	}

}