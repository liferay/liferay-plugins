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

package com.liferay.calendar.util;

import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarResourceLocalServiceUtil;
import com.liferay.calendar.service.CalendarResourceServiceUtil;
import com.liferay.calendar.util.comparator.CalendarResourceCodeComparator;
import com.liferay.calendar.util.comparator.CalendarResourceNameComparator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 * @author Marcellus Tavares
 */
public class CalendarResourceUtil {

	public static CalendarResource getCalendarResource(
			PortletRequest portletRequest, long classNameId, long classPK)
		throws PortalException, SystemException {

		long groupClassNameId = PortalUtil.getClassNameId(Group.class);

		if (classNameId == groupClassNameId) {
			return CalendarResourceUtil.getGroupCalendarResource(
				portletRequest, classPK);
		}

		long userClassNameId = PortalUtil.getClassNameId(User.class);

		if (classNameId == userClassNameId) {
			return CalendarResourceUtil.getUserCalendarResource(
				portletRequest, classPK);
		}

		return CalendarResourceServiceUtil.fetchCalendarResource(
			classNameId, classPK);
	}

	public static CalendarResource getGroupCalendarResource(
			long groupId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		if (group.isUser()) {
			return null;
		}

		CalendarResource calendarResource =
			CalendarResourceLocalServiceUtil.fetchCalendarResource(
				PortalUtil.getClassNameId(Group.class), groupId);

		if (calendarResource != null) {
			return calendarResource;
		}

		long userId = group.getCreatorUserId();

		User user = UserLocalServiceUtil.fetchUserById(userId);

		if (user == null) {
			userId = UserLocalServiceUtil.getDefaultUserId(
				group.getCompanyId());
		}

		Map<Locale, String> nameMap = new HashMap<Locale, String>();

		nameMap.put(LocaleUtil.getDefault(), group.getDescriptiveName());

		Map<Locale, String> descriptionMap = new HashMap<Locale, String>();

		return CalendarResourceLocalServiceUtil.addCalendarResource(
			userId, groupId, PortalUtil.getClassNameId(Group.class), groupId,
			null, null, nameMap, descriptionMap, true, serviceContext);
	}

	public static CalendarResource getGroupCalendarResource(
			PortletRequest portletRequest, long groupId)
		throws PortalException, SystemException {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			portletRequest);

		return getGroupCalendarResource(groupId, serviceContext);
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

	public static CalendarResource getScopedGroupCalendarResource(
			long groupId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		if (group.isUser()) {
			return getUserCalendarResource(group.getClassPK(), serviceContext);
		}
		else {
			return getGroupCalendarResource(groupId, serviceContext);
		}
	}

	public static CalendarResource getScopedGroupCalendarResource(
			PortletRequest portletRequest, long groupId)
		throws PortalException, SystemException {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			portletRequest);

		return getScopedGroupCalendarResource(groupId, serviceContext);
	}

	public static CalendarResource getUserCalendarResource(
			long userId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		long classNameId = PortalUtil.getClassNameId(User.class);

		CalendarResource calendarResource =
			CalendarResourceLocalServiceUtil.fetchCalendarResource(
				classNameId, userId);

		if (calendarResource != null) {
			return calendarResource;
		}

		User user = UserLocalServiceUtil.getUser(userId);

		Group userGroup = null;

		String userName = user.getFullName();

		if (user.isDefaultUser()) {
			userGroup = GroupLocalServiceUtil.getGroup(
				serviceContext.getCompanyId(), GroupConstants.GUEST);

			userName = GroupConstants.GUEST;
		}
		else {
			userGroup = GroupLocalServiceUtil.getUserGroup(
				serviceContext.getCompanyId(), userId);
		}

		Map<Locale, String> nameMap = new HashMap<Locale, String>();

		nameMap.put(LocaleUtil.getDefault(), userName);

		Map<Locale, String> descriptionMap = new HashMap<Locale, String>();

		return CalendarResourceLocalServiceUtil.addCalendarResource(
			userId, userGroup.getGroupId(),
			PortalUtil.getClassNameId(User.class), userId, null, null, nameMap,
			descriptionMap, true, serviceContext);
	}

	public static CalendarResource getUserCalendarResource(
			PortletRequest portletRequest, long userId)
		throws PortalException, SystemException {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			portletRequest);

		serviceContext.setUserId(userId);

		return getUserCalendarResource(userId, serviceContext);
	}

}