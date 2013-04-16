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

package com.liferay.calendar.model.impl;

import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarResourceLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Eduardo Lundgren
 */
public class CalendarImpl extends CalendarBaseImpl {

	public CalendarImpl() {
	}

	public CalendarResource getCalendarResource()
		throws PortalException, SystemException {

		return CalendarResourceLocalServiceUtil.getCalendarResource(
			getCalendarResourceId());
	}

	public long getResourceGroupId() {
		try {
			CalendarResource calendarResource = getCalendarResource();

			if (calendarResource.isGroup()) {
				return calendarResource.getClassPK();
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return getGroupId();
	}

	private static Log _log = LogFactoryUtil.getLog(CalendarImpl.class);

}