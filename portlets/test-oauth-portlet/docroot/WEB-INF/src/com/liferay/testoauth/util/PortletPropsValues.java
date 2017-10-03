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

package com.liferay.testoauth.util;

import com.liferay.util.portlet.PortletProps;

/**
 * @author Peter Shin
 */
public class PortletPropsValues {

	public static final String OAUTH_ACCESS_TOKEN_URI = PortletProps.get(
		PortletPropsKeys.OAUTH_ACCESS_TOKEN_URI);

	public static final String OAUTH_AUTHORIZE_URI = PortletProps.get(
		PortletPropsKeys.OAUTH_AUTHORIZE_URI);

	public static final String OAUTH_HOST_NAME = PortletProps.get(
		PortletPropsKeys.OAUTH_HOST_NAME);

	public static final String OAUTH_HOST_PORT = PortletProps.get(
		PortletPropsKeys.OAUTH_HOST_PORT);

	public static final String OAUTH_REQUEST_TOKEN_URI = PortletProps.get(
		PortletPropsKeys.OAUTH_REQUEST_TOKEN_URI);

	public static final String PORTAL_URL = PortletProps.get(
		PortletPropsKeys.PORTAL_URL);

}