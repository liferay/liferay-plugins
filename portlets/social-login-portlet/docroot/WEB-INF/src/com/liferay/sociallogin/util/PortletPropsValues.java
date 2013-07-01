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

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Terry Jia
 */
public class PortletPropsValues {

	public static final String QQ_CONNECT_APP_ID = PortletProps.get(
		PortletPropsKeys.QQ_CONNECT_APP_ID);

	public static final String QQ_CONNECT_APP_KEY = PortletProps.get(
		PortletPropsKeys.QQ_CONNECT_APP_KEY);

	public static final boolean QQ_CONNECT_AUTH_ENABLED = GetterUtil.getBoolean(
		PortletProps.get(PortletPropsKeys.QQ_CONNECT_AUTH_ENABLED));

	public static final String QQ_CONNECT_OAUTH_AUTH_URL = PortletProps.get(
		PortletPropsKeys.QQ_CONNECT_OAUTH_AUTH_URL);

	public static final String QQ_CONNECT_OAUTH_REDIRECT_URI = PortletProps.get(
		PortletPropsKeys.QQ_CONNECT_OAUTH_REDIRECT_URI);

	public static final String QQ_CONNECT_OAUTH_SCOPE = PortletProps.get(
		PortletPropsKeys.QQ_CONNECT_OAUTH_SCOPE);

	public static final String QQ_CONNECT_OAUTH_SOCIAL_ACCOUNT_ID_URL =
		PortletProps.get(
			PortletPropsKeys.QQ_CONNECT_OAUTH_SOCIAL_ACCOUNT_ID_URL);

	public static final String QQ_CONNECT_OAUTH_TOKEN_URL = PortletProps.get(
		PortletPropsKeys.QQ_CONNECT_OAUTH_TOKEN_URL);

}