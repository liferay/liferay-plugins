/*
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation; version 2.0 of the License.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.mobile.device.wurfl.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Michael C. Han
 */
public class PortletPropsValues {

	public static final String WURFL_DATABASE_PATCHES_PATH =
		GetterUtil.getString(
			PortletProps.get(PortletPropsKeys.WURFL_DATABASE_PATCHES_PATH));

	public static final String WURFL_DATABASE_PRIMARY = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.WURFL_DATABASE_PRIMARY));

}
