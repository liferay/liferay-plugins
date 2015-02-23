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

package com.liferay.calendar.hook.upgrade.v1_0_1;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.calendar.util.CalendarResourceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.List;

import org.jboss.arquillian.junit.Arquillian;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Adam Brandizzi
 */
@RunWith(Arquillian.class)
public class UpgradeCalendarTest {

	@Test
	public void testDoUpgradeCreatesCalendarTimeZoneId() throws Exception {
		UpgradeCalendar upgradeCalendar = new UpgradeCalendar();

		upgradeCalendar.doUpgrade();

		Assert.assertTrue(
			upgradeCalendar.tableHasColumn("Calendar", "timeZoneId"));
	}

	@Test
	public void testDoUpgradeSetsSiteCalendarTimeZoneId() throws Exception {
		Group group = GroupTestUtil.addGroup();
		long groupId = group.getGroupId();
		CalendarResource calendarResource =
			CalendarResourceUtil.getGroupCalendarResource(
				groupId, new ServiceContext());

		UpgradeCalendar upgradeCalendar = new UpgradeCalendar();

		upgradeCalendar.doUpgrade();

		List<Calendar> calendars =
			CalendarLocalServiceUtil.getCalendarResourceCalendars(
				groupId, calendarResource.getCalendarResourceId());
		Calendar calendar = calendars.get(0);

		Assert.assertEquals(
			PropsUtil.get(PropsKeys.COMPANY_DEFAULT_TIME_ZONE),
			calendar.getTimeZoneId());
	}

	@Test
	public void testDoUpgradeSetsUserCalendarTimeZoneId() throws Exception {
		User user = UserTestUtil.addUser();

		user.setTimeZoneId("Asia/Shangai");
		UserLocalServiceUtil.updateUser(user);

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(user.getCompanyId());

		CalendarResource calendarResource =
			CalendarResourceUtil.getUserCalendarResource(
				user.getUserId(), serviceContext);

		UpgradeCalendar upgradeCalendar = new UpgradeCalendar();

		upgradeCalendar.doUpgrade();

		List<Calendar> calendars =
			CalendarLocalServiceUtil.getCalendarResourceCalendars(
				user.getGroupId(), calendarResource.getCalendarResourceId());
		Calendar calendar = calendars.get(0);

		Assert.assertEquals("Asia/Shangai", calendar.getTimeZoneId());
	}

}