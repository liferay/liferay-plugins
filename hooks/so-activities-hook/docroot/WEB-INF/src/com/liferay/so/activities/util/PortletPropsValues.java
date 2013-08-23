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

package com.liferay.so.activities.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Jonathan Lee
 */
public class PortletPropsValues {

	public static final String[] SOCIAL_ACTIVITY_INTERPRETER_PORTLET_IDS =
		PortletProps.getArray(
			PortletPropsKeys.SOCIAL_ACTIVITY_INTERPRETER_PORTLET_IDS);

	public static final int SOCIAL_ACTIVITY_SETS_BUNDLING_TIME_WINDOW =
		GetterUtil.getInteger(
			PortletProps.get(
				PortletPropsKeys.SOCIAL_ACTIVITY_SETS_BUNDLING_TIME_WINDOW));

	public static final int SOCIAL_ACTIVITY_SETS_COMMENTS_BUNDLING_TIME_WINDOW =
		GetterUtil.getInteger(
			PortletProps.get(
				PortletPropsKeys.
					SOCIAL_ACTIVITY_SETS_COMMENTS_BUNDLING_TIME_WINDOW));

}