/*
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

package com.liferay.calendar.util;

import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

/**
 * @author Michael C. Han
 */
public class CalendarConstants {

	public static final String ACCEPT = "accept";

	public static final String ACCEPT_BOOKING = "accept-booking";

	public static final String ADD_BOOKING = "add-booking";

	public static final String ADD_RESOURCE = "add-resource";

	public static final String DECLINE = "decline";

	public static final String DECLINE_BOOKING = "decline-booking";

	public static final String DELETE_BOOKING = "delete-booking";

	public static final String DELETE_RESOURCE = "delete-resource";

	public static final int MAX_QUERY_RESULTS = 1000;

	public static final String[] TYPES = PropsUtil.getArray(
		PropsKeys.CALENDAR_EVENT_TYPES);

	public static final String UPDATE_BOOKING = "update-booking";

	public static final String UPDATE_RESOURCE = "update-resource";
}
