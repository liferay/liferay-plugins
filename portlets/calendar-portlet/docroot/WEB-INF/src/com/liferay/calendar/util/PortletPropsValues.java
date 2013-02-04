/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.calendar.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 * @author Andrea Di Giorgi
 * @author Bruno Basto
 */
public class PortletPropsValues {

	public static final int CALENDAR_COLOR_DEFAULT = Integer.decode(
		PortletProps.get(PortletPropsKeys.CALENDAR_COLOR_DEFAULT));

	public static final int CALENDAR_NOTIFICATION_CHECK_INTERVAL =
		GetterUtil.getInteger(
			PortletProps.get(
				PortletPropsKeys.CALENDAR_NOTIFICATION_CHECK_INTERVAL));

	public static final String CALENDAR_NOTIFICATION_DEFAULT_TYPE =
		PortletProps.get(PortletPropsKeys.CALENDAR_NOTIFICATION_DEFAULT_TYPE);

	public static final String CALENDAR_NOTIFICATION_FROM_ADDRESS =
		PortletProps.get(PortletPropsKeys.CALENDAR_NOTIFICATION_FROM_ADDRESS);

	public static final String CALENDAR_NOTIFICATION_FROM_NAME =
		PortletProps.get(PortletPropsKeys.CALENDAR_NOTIFICATION_FROM_NAME);

	public static final boolean CALENDAR_RESOURCE_FORCE_AUTOGENERATE_CODE =
		GetterUtil.getBoolean(
			PortletProps.get(
				PortletPropsKeys.CALENDAR_RESOURCE_FORCE_AUTOGENERATE_CODE));

}