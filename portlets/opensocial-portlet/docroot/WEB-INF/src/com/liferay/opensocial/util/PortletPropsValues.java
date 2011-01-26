/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.opensocial.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Michael Young
 */
public class PortletPropsValues {

	public static final String SHINDIG_JS_DEBUG = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.SHINDIG_JS_DEBUG));

	public static final String SHINDIG_NO_CACHE = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.SHINDIG_NO_CACHE));

	public static final String SHINDIG_SIGNING_GLOBAL_CALLBACK_URL =
		GetterUtil.getString(
			PortletProps.get(
				PortletPropsKeys.SHINDIG_SIGNING_GLOBAL_CALLBACK_URL));

	public static final String SHINDIG_SIGNING_KEY_FILE = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.SHINDIG_SIGNING_KEY_FILE));

	public static final String SHINDIG_SIGNING_KEY_NAME = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.SHINDIG_SIGNING_KEY_NAME));

}