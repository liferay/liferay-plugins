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

import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarResourceLocalServiceUtil;
import com.liferay.calendar.service.CalendarResourceServiceUtil;
import com.liferay.calendar.util.comparator.CalendarResourceCodeComparator;
import com.liferay.calendar.util.comparator.CalendarResourceNameComparator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 */
public class CalendarResourceUtil {

	public static CalendarResource fetchOrCreateGroupResource(
			HttpServletRequest request, long groupId) {

		CalendarResource calendarResource = null;

		try {
			Group group = GroupLocalServiceUtil.getGroup(groupId);

			if (group.isUser()) {
				return null;
			}

			long classNameId = PortalUtil.getClassNameId(Group.class);

			calendarResource =
				CalendarResourceLocalServiceUtil.fetchCalendarResource(
					classNameId, groupId);

			if (calendarResource == null) {
				ServiceContext serviceContext =
					ServiceContextFactory.getInstance(request);

				Map<Locale, String> nameMap = new HashMap<Locale, String>();

				nameMap.put(LocaleUtil.getDefault(), group.getName());

				Map<Locale, String> descriptionMap =
					new HashMap<Locale, String>();

				calendarResource =
					CalendarResourceLocalServiceUtil.addCalendarResource(
						serviceContext.getUserId(), 0, Group.class.getName(),
						groupId, null, 0, group.getName(), nameMap,
						descriptionMap, null, true, serviceContext);
			}
		} catch (Exception e) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Unable to create calendar resource for groupId: " +
						groupId);
			}
		}

		return calendarResource;
	}

	public static CalendarResource fetchOrCreateResource(
			HttpServletRequest request, long classNameId, long classPK)
		throws PortalException, SystemException {

		long userClassNameId = PortalUtil.getClassNameId(User.class);
		long groupClassNameId = PortalUtil.getClassNameId(Group.class);

		CalendarResource calendarResource = null;

		if (classNameId == userClassNameId) {
			calendarResource =
				CalendarResourceUtil.fetchOrCreateUserResource(
					request, classPK);
		}
		else if (classNameId == groupClassNameId) {
			calendarResource =
				CalendarResourceUtil.fetchOrCreateGroupResource(
					request, classPK);
		}
		else {
			calendarResource =
				CalendarResourceServiceUtil.fetchCalendarResource(
					classNameId, classPK);
		}

		return calendarResource;
	}

	public static CalendarResource fetchOrCreateUserResource(
			HttpServletRequest request, long userId) {

		CalendarResource calendarResource = null;

		try {
			long classNameId = PortalUtil.getClassNameId(User.class);

			calendarResource =
				CalendarResourceLocalServiceUtil.fetchCalendarResource(
					classNameId, userId);

			if (calendarResource == null) {
				User user = UserLocalServiceUtil.getUser(userId);

				ServiceContext serviceContext =
					ServiceContextFactory.getInstance(request);

				Map<Locale, String> nameMap = new HashMap<Locale, String>();

				nameMap.put(LocaleUtil.getDefault(), user.getFullName());

				Map<Locale, String> descriptionMap =
					new HashMap<Locale, String>();

				calendarResource =
					CalendarResourceLocalServiceUtil.addCalendarResource(
						serviceContext.getUserId(), 0, User.class.getName(),
						userId, null, 0, user.getFirstName(), nameMap,
						descriptionMap, null, true, serviceContext);
			}
		} catch (Exception e) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Unable to create calendar resource for userId: " + userId);
			}
		}

		return calendarResource;
	}

	public static OrderByComparator getOrderByComparator(
		String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator orderByComparator = null;

		if (orderByCol.equals("name")) {
			orderByComparator = new CalendarResourceNameComparator(orderByAsc);
		}
		else {
			orderByComparator = new CalendarResourceCodeComparator(orderByAsc);
		}

		return orderByComparator;
	}

	private static Log _log = LogFactoryUtil.getLog(CalendarResourceUtil.class);

}