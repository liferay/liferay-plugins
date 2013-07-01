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

package com.liferay.sociallogin.util.tencent.qq;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.sociallogin.util.BaseConnect;
import com.liferay.sociallogin.util.PortletPropsKeys;
import com.liferay.sociallogin.util.PortletPropsValues;

import java.io.IOException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Terry Jia
 */
public class QqConnectImpl extends BaseConnect implements QqConnect {

	public String getAccessToken(String content) {
		int x = content.indexOf("access_token=");

		if (x >= 0) {
			int y = content.indexOf(CharPool.AMPERSAND, x);

			if (y < x) {
				y = content.length();
			}

			return content.substring(x + 13, y);
		}

		return StringPool.BLANK;
	}

	public String getAccessTokenURL(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(
			companyId, PortletPropsKeys.QQ_CONNECT_OAUTH_TOKEN_URL,
			PortletPropsValues.QQ_CONNECT_OAUTH_TOKEN_URL);
	}

	public String getAuthURL(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(
			companyId, PortletPropsKeys.QQ_CONNECT_OAUTH_AUTH_URL,
			PortletPropsValues.QQ_CONNECT_OAUTH_AUTH_URL);
	}

	public String getClientId(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(
			companyId, PortletPropsKeys.QQ_CONNECT_APP_ID,
			PortletPropsValues.QQ_CONNECT_APP_ID);
	}

	public String getClientSecret(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(
			companyId, PortletPropsKeys.QQ_CONNECT_APP_KEY,
			PortletPropsValues.QQ_CONNECT_APP_KEY);
	}

	public String getConnectState() {
		return _QQ_CONNECT_STATE;
	}

	public String getRedirectURI(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(
			companyId, PortletPropsKeys.QQ_CONNECT_OAUTH_REDIRECT_URI,
			PortletPropsValues.QQ_CONNECT_OAUTH_REDIRECT_URI);
	}

	public String getScope(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(
			companyId, PortletPropsKeys.QQ_CONNECT_OAUTH_SCOPE,
			PortletPropsValues.QQ_CONNECT_OAUTH_SCOPE);
	}

	public String getSocialAccountId(long companyId, String accessToken)
		throws SystemException {

		String url = HttpUtil.addParameter(
			getSocialAccountIdURL(companyId), "access_token", accessToken);

		Http.Options options = new Http.Options();

		options.setLocation(url);
		options.setPost(false);

		String socialAccountId = StringPool.BLANK;

		try {
			Matcher matcher =
				Pattern.compile("\"openid\"\\s*:\\s*\"(\\w+)\"").matcher(
					HttpUtil.URLtoString(options));

			if (matcher.find()) {
				socialAccountId = matcher.group(1);
			}
		}
		catch (IOException ioe) {
		}

		return socialAccountId;
	}

	public String getSocialAccountIdURL(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(
			companyId, PortletPropsKeys.QQ_CONNECT_OAUTH_SOCIAL_ACCOUNT_ID_URL,
			PortletPropsValues.QQ_CONNECT_OAUTH_SOCIAL_ACCOUNT_ID_URL);
	}

	public boolean isEnabled(long companyId) throws SystemException {
		return PrefsPropsUtil.getBoolean(
			companyId, PortletPropsKeys.QQ_CONNECT_AUTH_ENABLED,
			PortletPropsValues.QQ_CONNECT_AUTH_ENABLED);
	}

	private static final String _QQ_CONNECT_STATE = "QQ_CONNECT_STATE";

}