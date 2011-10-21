/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation; version 3.0 of the License.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.mobile.device.wurfl.util;

import com.liferay.util.portlet.PortletProps;

/**
 * @author Michael C. Han
 */
public class PortletPropsValues {

	public static final String WURFL_DATABASE_PATCHES = PortletProps.get(
		PortletPropsKeys.WURFL_DATABASE_PATCHES);

	public static final String WURFL_DATABASE_PRIMARY = PortletProps.get(
		PortletPropsKeys.WURFL_DATABASE_PRIMARY);

}