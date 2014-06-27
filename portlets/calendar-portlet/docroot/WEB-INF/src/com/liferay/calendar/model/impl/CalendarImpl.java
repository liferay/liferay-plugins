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

package com.liferay.calendar.model.impl;

import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarResourceLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.util.TimeZone;

/**
 * @author Eduardo Lundgren
 */
public class CalendarImpl extends CalendarBaseImpl {

	public CalendarImpl() {
	}

	@Override
	public CalendarResource getCalendarResource() throws PortalException {
		return CalendarResourceLocalServiceUtil.getCalendarResource(
			getCalendarResourceId());
	}

	@Override
	public TimeZone getTimeZone() throws PortalException {
		CalendarResource calendarResource = getCalendarResource();

		if (calendarResource.getClassNameId() ==
				PortalUtil.getClassNameId(User.class)) {

			User user = UserLocalServiceUtil.getUser(
				calendarResource.getClassPK());

			return user.getTimeZone();
		}

		return TimeZoneUtil.getDefault();
	}

}