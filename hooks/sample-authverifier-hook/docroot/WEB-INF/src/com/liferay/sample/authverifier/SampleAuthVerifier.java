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

package com.liferay.sample.authverifier;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Role;
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
 * {@link AuthVerifier}s are used for obtaining user from request based on
 * a provided token, regardless of a nature of the token.<br />
 * <br />
 * This sample verifier returns admin account if request contains secret key,
 * configurable from hook's portal.properties file.<br />
 * <br />
 * <strong>Don't use in production, only for educational purposes, security
 * of this solution is weak!</strong>
 *
 * @author Tomas Polesovsky
 */
public class SampleAuthVerifier implements AuthVerifier {

	public AuthVerifierResult verify(
			AccessControlContext accessControlContext,
			Properties configuration)
		throws AuthException {

		AuthVerifierResult result = new AuthVerifierResult();

		String keyFromConfiguration = configuration.getProperty("key");

		HttpServletRequest request = accessControlContext.getRequest();

		String keyFromRequest = ParamUtil.get(request, "key", StringPool.BLANK);

		if (!keyFromConfiguration.equals(keyFromRequest)) {
			return result;
		}

		long companyId = PortalUtil.getCompanyId(request);

		try {
			Role adminRole = RoleLocalServiceUtil.getRole(
				companyId, "Administrator");

			long[] adminIds = UserLocalServiceUtil.getRoleUserIds(
				adminRole.getRoleId());

			long adminId = 0;

			if (adminIds.length > 0) {
				adminId = adminIds[0];
			}

			result.setState(AuthVerifierResult.State.SUCCESS);
			result.setUserId(adminId);
		}
		catch (Exception e) {
			throw new AuthException(e.getMessage(), e);
		}

		return result;
	}
}