/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.saml.hook;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.saml.NoSuchSSOSessionException;
import com.liferay.portal.saml.model.SSOSession;
import com.liferay.portal.saml.service.SSOSessionLocalServiceUtil;
import com.liferay.portal.saml.util.WebKeys;
import com.liferay.portal.security.auth.AutoLogin;
import com.liferay.portal.security.auth.AutoLoginException;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.CookieUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Mika Koivisto
 */
public class SAMLSSOAutoLoginHook implements AutoLogin {

	public String[] login(
		HttpServletRequest request, HttpServletResponse response)
	throws AutoLoginException {

		try {
			String[] credentials = null;

			String ssoSessionId = CookieUtil.get(
				request, WebKeys.SSOSESSIONID);

			if (Validator.isNotNull(ssoSessionId)) {

				Company company = PortalUtil.getCompany(request);

				SSOSession ssoSession = null;

				try {
					ssoSession = 
						SSOSessionLocalServiceUtil.getSSOSession(ssoSessionId);
				}
				catch (NoSuchSSOSessionException nssse) {
					return credentials;
				}

				if (ssoSession.isExpired()) {
					return credentials;
				}

				User user = null;

				try {
					user = UserLocalServiceUtil.getUserById(
						company.getCompanyId(), ssoSession.getUserId());
				}
				catch (NoSuchUserException nsue) {
				}

				if (user != null) {

					credentials = new String[3];

					credentials[0] = String.valueOf(user.getUserId());
					credentials[1] = user.getPassword();
					credentials[2] = Boolean.TRUE.toString();
				}
			}

			return credentials;
		}
		catch (Exception e) {
			_log.warn(e, e);

			throw new AutoLoginException(e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(SAMLSSOAutoLoginHook.class);

}
