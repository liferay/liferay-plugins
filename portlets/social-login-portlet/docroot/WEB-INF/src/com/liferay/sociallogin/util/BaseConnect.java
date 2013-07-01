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
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Terry Jia
 */
public abstract class BaseConnect implements Connect {

	public String getAccessToken(long companyId, String code)
		throws SystemException {

		String url = getAccessTokenURL(companyId);

		url = HttpUtil.addParameter(
			url, Constants.GRANT_TYPE, Constants.DEFAULT_GRANT_TYPE);
		url = HttpUtil.addParameter(
			url, Constants.CLIENT_ID, getClientId(companyId));
		url = HttpUtil.addParameter(
			url, Constants.CLIENT_SECRET, getClientSecret(companyId));
		url = HttpUtil.addParameter(url, Constants.CODE, code);
		url = HttpUtil.addParameter(
			url, Constants.REDIRECT_URI, getRedirectURI(companyId));

		Http.Options options = new Http.Options();

		options.setLocation(url);
		options.setPost(false);

		String content = StringPool.BLANK;

		try {
			content = HttpUtil.URLtoString(options);
		}
		catch (Exception e) {
			throw new SystemException("Unable to retrieve access token", e);
		}

		return getAccessToken(content);
	}

	public String getFullAuthURL(long companyId, HttpServletRequest request)
		throws SystemException {

		String state = RandomStatusGenerator.getRandomState();

		HttpSession session = request.getSession();

		session.setAttribute(getConnectState(), state);

		String url = getAuthURL(companyId);

		url = HttpUtil.addParameter(
			url, Constants.CLIENT_ID, getClientId(companyId));
		url = HttpUtil.addParameter(
			url, Constants.RESPONSE_TYPE, Constants.DEFAULT_RESPONSE_TYPE);
		url = HttpUtil.addParameter(
			url, Constants.REDIRECT_URI, getRedirectURI(companyId));
		url = HttpUtil.addParameter(url, Constants.STATE, state);
		url = HttpUtil.addParameter(url, Constants.SCOPE, getScope(companyId));

		return url;
	}

}