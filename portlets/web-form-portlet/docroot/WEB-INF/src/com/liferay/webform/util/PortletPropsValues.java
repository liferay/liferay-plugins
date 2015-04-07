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

package com.liferay.webform.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletPropsValues {

	public static final String CSV_SEPARATOR = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.CSV_SEPARATOR));

	public static final String DATA_ROOT_DIR = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.DATA_ROOT_DIR));

	public static final String EMAIL_FROM_ADDRESS = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.EMAIL_FROM_ADDRESS));

	public static final String EMAIL_FROM_NAME = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.EMAIL_FROM_NAME));

	public static final boolean VALIDATION_SCRIPT_ENABLED =
		GetterUtil.getBoolean(
			PortletProps.get(PortletPropsKeys.VALIDATION_SCRIPT_ENABLED));

}