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

package com.liferay.pushnotifications.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Bruno Farache
 */
public class PortletPropsValues {

	public static final String ANDROID_API_KEY = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.ANDROID_API_KEY));

	public static final int ANDROID_RETRIES = GetterUtil.getInteger(
		PortletProps.get(PortletPropsKeys.ANDROID_RETRIES));

	public static final String APPLE_CERTIFICATE_PASSWORD =
		GetterUtil.getString(
			PortletProps.get(PortletPropsKeys.APPLE_CERTIFICATE_PASSWORD));

	public static final String APPLE_CERTIFICATE_PATH = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.APPLE_CERTIFICATE_PATH));

	public static final boolean APPLE_SANDBOX = GetterUtil.getBoolean(
		PortletProps.get(PortletPropsKeys.APPLE_SANDBOX), true);

	public static final String LIFERAY_PASSWORD = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.LIFERAY_PASSWORD));

	public static final String LIFERAY_SERVER = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.LIFERAY_SERVER));

	public static final int LIFERAY_TIMEOUT = GetterUtil.getInteger(
		PortletProps.get(PortletPropsKeys.LIFERAY_TIMEOUT), 1000);

	public static final String LIFERAY_USERNAME = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.LIFERAY_USERNAME));

	public static final String SMS_TWILIO_ACCOUNT_SID = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.SMS_TWILIO_ACCOUNT_SID));

	public static final String SMS_TWILIO_AUTH_TOKEN = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.SMS_TWILIO_AUTH_TOKEN));

	public static final String SMS_TWILIO_NUMBER = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.SMS_TWILIO_NUMBER));

}