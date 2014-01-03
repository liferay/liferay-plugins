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

package com.liferay.samplepaypalclassic.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Douglas Wong
 */
public class PortletPropsValues {

	public static final String PAYPAL_APP_ID = PortletProps.get(
		PortletPropsKeys.PAYPAL_APP_ID);

	public static final String PAYPAL_PASSWORD = PortletProps.get(
		PortletPropsKeys.PAYPAL_PASSWORD);

	public static final String PAYPAL_PRIMARY_RECEIVER_EMAIL = PortletProps.get(
		PortletPropsKeys.PAYPAL_PRIMARY_RECEIVER_EMAIL);

	public static final boolean PAYPAL_SANDBOX_MODE = GetterUtil.getBoolean(
		PortletProps.get(PortletPropsKeys.PAYPAL_SANDBOX_MODE));

	public static final String PAYPAL_SIGNATURE = PortletProps.get(
		PortletPropsKeys.PAYPAL_SIGNATURE);

	public static final String PAYPAL_USER_NAME = PortletProps.get(
		PortletPropsKeys.PAYPAL_USER_NAME);

}