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

import javax.servlet.http.HttpServletRequest;

/**
 * @author Terry Jia
 */
public class QqConnectUtil {

	public static String getAccessToken(HttpServletRequest request) {
		return _qqConnect.getAccessToken(request);
	}

	public static String getAuthURL(HttpServletRequest request) {
		return _qqConnect.getAuthURL(request);
	}

	public static String getClientId(long companyId) throws SystemException {
		return _qqConnect.getClientId(companyId);
	}

	public static String getClientSecret(long companyId)
		throws SystemException {

		return _qqConnect.getClientSecret(companyId);
	}

	public static String getOpenId(String accessToken) {
		return _qqConnect.getOpenId(accessToken);
	}

	public static String getRedirectURI(long companyId) throws SystemException {
		return _qqConnect.getRedirectURI(companyId);
	}

	public static String getScope(long companyId) throws SystemException {
		return _qqConnect.getScope(companyId);
	}

	public static boolean isEnabled(long companyId) throws SystemException {
		return _qqConnect.isEnabled(companyId);
	}

	public static void updateConnectConfigProperties(
		String appId, String appKey, String redirectURI, String scope) {

		_qqConnect.updateConnectConfigProperties(
			appId, appKey, redirectURI, scope);
	}

	public void setQqConnect(QqConnect qqConnect) {
		_qqConnect = qqConnect;
	}

	private static QqConnect _qqConnect;

}