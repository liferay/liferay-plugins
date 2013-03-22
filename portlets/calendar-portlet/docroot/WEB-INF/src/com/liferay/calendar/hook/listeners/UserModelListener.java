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

package com.liferay.calendar.hook.listeners;

import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarResourceLocalServiceUtil;
import com.liferay.portal.ModelListenerException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

import java.util.Locale;

/**
 * @author Antonio Junior
 */
public class UserModelListener extends BaseModelListener<User> {

	@Override
	public void onAfterUpdate(User user) throws ModelListenerException {
		try {
			long classNameId = PortalUtil.getClassNameId(User.class);

			CalendarResource calendarResource =
				CalendarResourceLocalServiceUtil.fetchCalendarResource(
					classNameId, user.getUserId());

			if (calendarResource == null) {
				return;
			}

			Locale locale = LocaleUtil.getDefault();

			calendarResource.setName(user.getFullName(), locale);

			CalendarResourceLocalServiceUtil.updateCalendarResource(
				calendarResource);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

}