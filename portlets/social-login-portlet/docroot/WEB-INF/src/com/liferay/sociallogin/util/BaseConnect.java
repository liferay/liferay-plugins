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

package com.liferay.sociallogin.util;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Terry Jia
 */
public abstract class BaseConnect implements Connect {

	public String getAccessToken(HttpServletRequest request) {
		return StringPool.BLANK;
	}

	public String getAuthURL(HttpServletRequest request) {
		return StringPool.BLANK;
	}

	public String getClientId(long companyId) throws SystemException {
		return StringPool.BLANK;
	}

	public String getClientSecret(long companyId) throws SystemException {
		return StringPool.BLANK;
	}

	public String getOpenId(String accessToken) {
		return StringPool.BLANK;
	}

	public String getRedirectURI(long companyId) throws SystemException {
		return StringPool.BLANK;
	}

	public String getScope(long companyId) throws SystemException {
		return StringPool.BLANK;
	}

	public boolean isEnabled(long companyId) throws SystemException {
		return false;
	}

	public void updateConnectConfigProperties(
		String clientId, String clientSecret, String redirectURI,
		String scope) {
	}

}