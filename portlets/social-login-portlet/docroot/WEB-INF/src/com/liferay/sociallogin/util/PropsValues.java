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
import com.liferay.portal.kernel.util.PropsUtil;

/**
 * @author Terry Jia
 */
public class PropsValues {

	public static final String QQ_CONNECT_APP_ID = PropsUtil.get(
		PropsKeys.QQ_CONNECT_APP_ID);

	public static final String QQ_CONNECT_APP_KEY = PropsUtil.get(
		PropsKeys.QQ_CONNECT_APP_KEY);

	public static final boolean QQ_CONNECT_AUTH_ENABLED = GetterUtil.getBoolean(
		PropsUtil.get(PropsKeys.QQ_CONNECT_AUTH_ENABLED));

	public static final String QQ_CONNECT_OAUTH_REDIRECT_URI = PropsUtil.get(
		PropsKeys.QQ_CONNECT_OAUTH_REDIRECT_URI);

	public static final String QQ_CONNECT_OAUTH_SCOPE = PropsUtil.get(
		PropsKeys.QQ_CONNECT_OAUTH_SCOPE);

}