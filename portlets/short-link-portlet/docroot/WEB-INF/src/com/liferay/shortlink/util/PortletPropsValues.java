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

package com.liferay.shortlink.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Miroslav Ligas
 */
public class PortletPropsValues {

	public static final int SHORT_URL_CLEAN_UP_THRESHOLD =
		GetterUtil.getInteger(
			PortletProps.get(PortletPropsKeys.SHORT_URL_CLEAN_UP_THRESHOLD));

	public static final String SHORT_URL_HOSTNAME = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.SHORT_URL_HOSTNAME));

	public static final int SHORT_URL_LIFETIME = GetterUtil.getInteger(
		PortletProps.get(PortletPropsKeys.SHORT_URL_LIFETIME));

}