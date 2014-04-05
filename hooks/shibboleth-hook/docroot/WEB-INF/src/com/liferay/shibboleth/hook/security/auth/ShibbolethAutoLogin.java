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

package com.liferay.shibboleth.hook.security.auth;

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.BaseAutoLogin;
import com.liferay.portal.security.ldap.PortalLDAPImporterUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.shibboleth.util.PropsKeys;
import com.liferay.shibboleth.util.PropsValues;
import com.liferay.shibboleth.util.ShibbolethUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Eric Chin
 */
public class ShibbolethAutoLogin extends BaseAutoLogin {

	@Override
	protected String[] doLogin(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		Company company = PortalUtil.getCompany(request);

		long companyId = company.getCompanyId();

		if (!ShibbolethUtil.isEnabled(companyId)) {
			return null;
		}

		String shibbolethUserHeader = request.getHeader(
			PrefsPropsUtil.getString(
				companyId, PropsKeys.SHIBBOLETH_USER_HEADER,
				PropsValues.SHIBBOLETH_USER_HEADER));

		if (Validator.isNull(shibbolethUserHeader)) {
			return null;
		}

		String authType = company.getAuthType();

		User user = null;

		if (PrefsPropsUtil.getBoolean(
				companyId, PropsKeys.SHIBBOLETH_IMPORT_FROM_LDAP,
				PropsValues.SHIBBOLETH_IMPORT_FROM_LDAP)) {

			try {
				if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
					user = PortalLDAPImporterUtil.importLDAPUser(
						companyId, shibbolethUserHeader, StringPool.BLANK);
				}
				else {
					user = PortalLDAPImporterUtil.importLDAPUser(
						companyId, StringPool.BLANK, shibbolethUserHeader);
				}
			}
			catch (SystemException se) {
			}
		}

		if (user == null) {
			if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
				user = UserLocalServiceUtil.fetchUserByEmailAddress(
					companyId, shibbolethUserHeader);
			}
			else {
				user = UserLocalServiceUtil.fetchUserByScreenName(
					companyId, shibbolethUserHeader);
			}
		}

		String[] credentials = new String[3];

		credentials[0] = String.valueOf(user.getUserId());
		credentials[1] = user.getPassword();
		credentials[2] = Boolean.TRUE.toString();

		return credentials;
	}

}