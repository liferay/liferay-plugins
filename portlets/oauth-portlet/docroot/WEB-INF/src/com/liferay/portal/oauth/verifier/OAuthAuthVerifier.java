/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.oauth.verifier;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.oauth.model.ApplicationUser;
import com.liferay.portal.oauth.service.ApplicationUserLocalServiceUtil;
import com.liferay.portal.security.auth.AccessControlContext;
import com.liferay.portal.security.auth.AuthException;
import com.liferay.portal.security.auth.AuthVerifier;
import com.liferay.portal.security.auth.AuthVerifierResult;

import java.util.Properties;

/**
 * @author Ivica Cardic
 */
public class OAuthAuthVerifier implements AuthVerifier {
	public AuthVerifierResult verify(
		AccessControlContext accessControlContext, Properties properties)
		throws AuthException {

		AuthVerifierResult result = new AuthVerifierResult();

		String accessToken = ParamUtil.get(
			accessControlContext.getRequest(), "oauth_token", StringPool.BLANK);

		if (Validator.isNull(accessToken)) {
			return result;
		}

		ApplicationUser applicationUser;

		try {
			applicationUser =
				ApplicationUserLocalServiceUtil
					.getApplicationUserByAccessToken(accessToken);

		} catch (SystemException e) {
			throw new AuthException(e);
		}

		if (applicationUser == null) {
			return result;
		}

		long userId = applicationUser.getUserId();

		result.setState(AuthVerifierResult.State.SUCCESS);
		result.setUserId(userId);

		return result;
	}

	public String getAuthType() {

		return "OAuth";
	}

}