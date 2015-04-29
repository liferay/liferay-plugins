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

package com.liferay.google.apps.connector.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Matthew Kong
 */
public class PortletPropsValues {

	public static final int GOOGLE_API_RETRY_ATTEMPTS = GetterUtil.getInteger(
		PortletProps.get(PortletPropsKeys.GOOGLE_API_RETRY_ATTEMPTS));

	public static final int GOOGLE_API_RETRY_INTERVAL = GetterUtil.getInteger(
		PortletProps.get(PortletPropsKeys.GOOGLE_API_RETRY_INTERVAL));

	public static final String GOOGLE_API_SERVICE_ACCOUNT_ID =
		GetterUtil.getString(
			PortletProps.get(PortletPropsKeys.GOOGLE_API_SERVICE_ACCOUNT_ID));

	public static final String GOOGLE_API_SERVICE_ACCOUNT_PRIVATE_KEY_P12_FILE =
		GetterUtil.getString(
			PortletProps.get(
				PortletPropsKeys.
					GOOGLE_API_SERVICE_ACCOUNT_PRIVATE_KEY_P12_FILE));

	public static final String[] GOOGLE_API_SERVICE_ACCOUNT_SCOPES =
		PortletProps.getArray(
			PortletPropsKeys.GOOGLE_API_SERVICE_ACCOUNT_SCOPES);

	public static final String GOOGLE_API_SERVICE_ACCOUNT_USER =
		GetterUtil.getString(
			PortletProps.get(PortletPropsKeys.GOOGLE_API_SERVICE_ACCOUNT_USER));

}