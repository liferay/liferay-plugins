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

import javax.servlet.http.HttpServletRequest;

/**
 * @author Terry Jia
 */
public interface Connect {

	public String getAccessToken(long companyId, String code)
		throws SystemException;

	public String getAccessToken(String content);

	public String getAccessTokenURL(long companyId) throws SystemException;

	public String getAuthURL(long companyId) throws SystemException;

	public String getClientId(long companyId) throws SystemException;

	public String getClientSecret(long companyId) throws SystemException;

	public String getConnectState();

	public String getFullAuthURL(long companyId, HttpServletRequest request)
		throws SystemException;

	public String getRedirectURI(long companyId) throws SystemException;

	public String getScope(long companyId) throws SystemException;

	public String getSocialAccountId(long companyId, String accessToken)
		throws SystemException;

	public String getSocialAccountIdURL(long companyId) throws SystemException;

	public boolean isEnabled(long companyId) throws SystemException;

}