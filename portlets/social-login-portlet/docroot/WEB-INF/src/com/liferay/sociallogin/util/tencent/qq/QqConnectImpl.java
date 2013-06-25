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
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.sociallogin.util.BaseConnect;
import com.liferay.sociallogin.util.PropsKeys;
import com.liferay.sociallogin.util.PropsValues;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;
import com.qq.connect.utils.QQConnectConfig;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Terry Jia
 */
public class QqConnectImpl extends BaseConnect implements QqConnect {

	public String getAccessToken(HttpServletRequest request) {
		try {
			AccessToken accessToken = new Oauth().getAccessTokenByRequest(
				request);

			return accessToken.getAccessToken();
		}
		catch (QQConnectException qqce) {
		}

		return StringPool.BLANK;
	}

	public String getAuthURL(HttpServletRequest request) {
		try {
			return new Oauth().getAuthorizeURL(request);
		}
		catch (QQConnectException qqce) {
		}

		return StringPool.BLANK;
	}

	public String getClientId(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(
			companyId, PropsKeys.QQ_CONNECT_APP_ID,
			PropsValues.QQ_CONNECT_APP_ID);
	}

	public String getClientSecret(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(
			companyId, PropsKeys.QQ_CONNECT_APP_KEY,
			PropsValues.QQ_CONNECT_APP_KEY);
	}

	public String getOpenId(String accessToken) {
		OpenID openId = new OpenID(accessToken);

		try {
			return openId.getUserOpenID();
		}
		catch (QQConnectException qqce) {
		}

		return StringPool.BLANK;
	}

	public String getRedirectURI(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(
			companyId, PropsKeys.QQ_CONNECT_OAUTH_REDIRECT_URI,
			PropsValues.QQ_CONNECT_OAUTH_REDIRECT_URI);
	}

	public String getScope(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(
			companyId, PropsKeys.QQ_CONNECT_OAUTH_SCOPE,
			PropsValues.QQ_CONNECT_OAUTH_SCOPE);
	}

	public boolean isEnabled(long companyId) throws SystemException {
		return PrefsPropsUtil.getBoolean(
			companyId, PropsKeys.QQ_CONNECT_AUTH_ENABLED,
			PropsValues.QQ_CONNECT_AUTH_ENABLED);
	}

	public void updateConnectConfigProperties(
		String clientId, String clientSecret, String redirectURI,
		String scope) {

		QQConnectConfig.updateProperties(QqConstants.APP_KEY, clientSecret);
		QQConnectConfig.updateProperties(QqConstants.APP_ID, clientId);
		QQConnectConfig.updateProperties(QqConstants.REDIRECT_URI, redirectURI);
		QQConnectConfig.updateProperties(QqConstants.SCOPE, scope);
	}

}