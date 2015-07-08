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

package com.liferay.calendar.hook.listeners;

import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarResourceLocalServiceUtil;
import com.liferay.portal.ModelListenerException;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.Group;
import com.liferay.portal.util.PortalUtil;

/**
 * @author Marcellus Tavares
 */
public class GroupModelListener extends BaseModelListener<Group> {

	@Override
	public void onAfterRemove(Group group) throws ModelListenerException {
		try {

			// Global calendar resource

			long classNameId = PortalUtil.getClassNameId(Group.class);

			CalendarResource calendarResource =
				CalendarResourceLocalServiceUtil.fetchCalendarResource(
					classNameId, group.getGroupId());

			if (calendarResource != null) {
				CalendarResourceLocalServiceUtil.deleteCalendarResource(
					calendarResource);
			}

			// Local calendar resources

			CalendarResourceLocalServiceUtil.deleteCalendarResources(
				group.getGroupId());
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onAfterUpdate(Group group) throws ModelListenerException {
		try {
			long classNameId = PortalUtil.getClassNameId(Group.class);

			CalendarResource calendarResource =
				CalendarResourceLocalServiceUtil.fetchCalendarResource(
					classNameId, group.getGroupId());

			if (calendarResource == null) {
				return;
			}

			calendarResource.setNameMap(group.getNameMap());

			CalendarResourceLocalServiceUtil.updateCalendarResource(
				calendarResource);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

}