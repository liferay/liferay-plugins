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
import com.liferay.portal.oauth.model.OAuthApplications_Users;
import com.liferay.portal.oauth.service.OAuthApplications_UsersLocalServiceUtil;
import com.liferay.portal.security.auth.AuthException;
import com.liferay.portal.security.auth.AuthenticationContext;
import com.liferay.portal.security.auth.verifier.AuthVerifier;
import com.liferay.portal.security.auth.verifier.VerificationResult;

import java.util.Properties;

/**
 * @author Ivica Cardic
 */
public class OAuthAuthVerifier implements AuthVerifier {
	public VerificationResult verify(
		AuthenticationContext authenticationContext, Properties configuration)
		throws AuthException {

		VerificationResult result = new VerificationResult();

		String accessToken = ParamUtil.get(
			authenticationContext.getHttpServletRequest(), "oauth_token",
			StringPool.BLANK);

		if (Validator.isNull(accessToken)) {
			return result;
		}

		OAuthApplications_Users oAuthApplications_users;

		try {
			oAuthApplications_users =
				OAuthApplications_UsersLocalServiceUtil
					.getOAuthApplications_UsersByAccessToken(accessToken);

		} catch (SystemException e) {
			throw new AuthException(e);
		}

		if (oAuthApplications_users == null) {
			return result;
		}

		long userId = oAuthApplications_users.getUserId();

		result.setState(VerificationResult.State.SUCCESS);
		result.setUserId(userId);

		return result;
	}

}