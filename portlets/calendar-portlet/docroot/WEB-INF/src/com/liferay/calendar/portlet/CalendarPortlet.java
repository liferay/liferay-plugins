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

package com.liferay.calendar.portlet;

import com.liferay.calendar.CalendarEventDurationException;
import com.liferay.calendar.CalendarEventEndDateException;
import com.liferay.calendar.CalendarEventStartDateException;
import com.liferay.calendar.CalendarEventTitleException;
import com.liferay.calendar.DuplicateCalendarResourceException;
import com.liferay.calendar.NoSuchBookingException;
import com.liferay.calendar.NoSuchEventException;
import com.liferay.calendar.NoSuchResourceException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * @author Eduardo Lundgren
 */
public class CalendarPortlet extends MVCPortlet {

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof CalendarEventDurationException ||
			cause instanceof CalendarEventEndDateException ||
			cause instanceof CalendarEventStartDateException ||
			cause instanceof CalendarEventTitleException ||
			cause instanceof DuplicateCalendarResourceException ||
			cause instanceof NoSuchBookingException ||
			cause instanceof NoSuchEventException ||
			cause instanceof NoSuchResourceException ||
			cause instanceof PrincipalException) {

			return true;
		}

		return false;
	}

}