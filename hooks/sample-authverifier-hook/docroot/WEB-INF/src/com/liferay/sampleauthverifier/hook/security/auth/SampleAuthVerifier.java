/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.sampleauthverifier.hook.security.auth;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.security.auth.AccessControlContext;
import com.liferay.portal.security.auth.AuthException;
import com.liferay.portal.security.auth.AuthVerifier;
import com.liferay.portal.security.auth.AuthVerifierResult;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Tomas Polesovsky
 */
public class SampleAuthVerifier implements AuthVerifier {

	@Override
	public String getAuthType() {
		return SampleAuthVerifier.class.getSimpleName();
	}

	@Override
	public AuthVerifierResult verify(
			AccessControlContext accessControlContext, Properties properties)
		throws AuthException {

		AuthVerifierResult authVerifierResult = new AuthVerifierResult();

		String propertiesSecretKey = GetterUtil.getString(
			properties.getProperty("secret.key"));

		HttpServletRequest request = accessControlContext.getRequest();

		String requestSecretKey = ParamUtil.getString(request, "secretKey");

		if (!propertiesSecretKey.equals(requestSecretKey)) {
			return authVerifierResult;
		}

		try {
			authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);

			long companyId = PortalUtil.getCompanyId(request);

			Role role = RoleLocalServiceUtil.getRole(
				companyId, RoleConstants.ADMINISTRATOR);

			long[] userIds = UserLocalServiceUtil.getRoleUserIds(
				role.getRoleId());

			if (userIds.length > 0) {
				authVerifierResult.setUserId(userIds[0]);
			}
		}
		catch (Exception e) {
			throw new AuthException(e.getMessage(), e);
		}

		return authVerifierResult;
	}

}