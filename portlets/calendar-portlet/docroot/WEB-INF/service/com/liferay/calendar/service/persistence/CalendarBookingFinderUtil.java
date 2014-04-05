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

package com.liferay.calendar.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Eduardo Lundgren
 */
public class CalendarBookingFinderUtil {
	public static int countByKeywords(long companyId, long[] groupIds,
		long[] calendarIds, long[] calendarResourceIds,
		long parentCalendarBookingId, java.lang.String keywords,
		long startTime, long endTime, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByKeywords(companyId, groupIds, calendarIds,
			calendarResourceIds, parentCalendarBookingId, keywords, startTime,
			endTime, statuses);
	}

	public static int countByC_G_C_C_P_T_D_L_S_E_S(long companyId,
		long[] groupIds, long[] calendarIds, long[] calendarResourceIds,
		long parentCalendarBookingId, java.lang.String title,
		java.lang.String description, java.lang.String location,
		long startTime, long endTime, int[] statuses, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByC_G_C_C_P_T_D_L_S_E_S(companyId, groupIds,
			calendarIds, calendarResourceIds, parentCalendarBookingId, title,
			description, location, startTime, endTime, statuses, andOperator);
	}

	public static int countByC_G_C_C_P_T_D_L_S_E_S(long companyId,
		long[] groupIds, long[] calendarIds, long[] calendarResourceIds,
		long parentCalendarBookingId, java.lang.String[] titles,
		java.lang.String[] descriptions, java.lang.String[] locations,
		long startTime, long endTime, int[] statuses, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByC_G_C_C_P_T_D_L_S_E_S(companyId, groupIds,
			calendarIds, calendarResourceIds, parentCalendarBookingId, titles,
			descriptions, locations, startTime, endTime, statuses, andOperator);
	}

	public static int filterCountByKeywords(long companyId, long[] groupIds,
		long[] calendarIds, long[] calendarResourceIds,
		long parentCalendarBookingId, java.lang.String keywords,
		long startTime, long endTime, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountByKeywords(companyId, groupIds, calendarIds,
			calendarResourceIds, parentCalendarBookingId, keywords, startTime,
			endTime, statuses);
	}

	public static int filterCountByC_G_C_C_P_T_D_L_S_E_S(long companyId,
		long[] groupIds, long[] calendarIds, long[] calendarResourceIds,
		long parentCalendarBookingId, java.lang.String title,
		java.lang.String description, java.lang.String location,
		long startTime, long endTime, int[] statuses, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountByC_G_C_C_P_T_D_L_S_E_S(companyId, groupIds,
			calendarIds, calendarResourceIds, parentCalendarBookingId, title,
			description, location, startTime, endTime, statuses, andOperator);
	}

	public static int filterCountByC_G_C_C_P_T_D_L_S_E_S(long companyId,
		long[] groupIds, long[] calendarIds, long[] calendarResourceIds,
		long parentCalendarBookingId, java.lang.String[] titles,
		java.lang.String[] descriptions, java.lang.String[] locations,
		long startTime, long endTime, int[] statuses, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountByC_G_C_C_P_T_D_L_S_E_S(companyId, groupIds,
			calendarIds, calendarResourceIds, parentCalendarBookingId, titles,
			descriptions, locations, startTime, endTime, statuses, andOperator);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> filterFindByKeywords(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String keywords, long startTime, long endTime,
		boolean recurring, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByKeywords(companyId, groupIds, calendarIds,
			calendarResourceIds, parentCalendarBookingId, keywords, startTime,
			endTime, recurring, statuses, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> filterFindByC_G_C_C_P_T_D_L_S_E_S(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String title, java.lang.String description,
		java.lang.String location, long startTime, long endTime,
		boolean recurring, int[] statuses, boolean andOperator, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByC_G_C_C_P_T_D_L_S_E_S(companyId, groupIds,
			calendarIds, calendarResourceIds, parentCalendarBookingId, title,
			description, location, startTime, endTime, recurring, statuses,
			andOperator, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> filterFindByC_G_C_C_P_T_D_L_S_E_S(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String[] titles, java.lang.String[] descriptions,
		java.lang.String[] locations, long startTime, long endTime,
		boolean recurring, int[] statuses, boolean andOperator, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByC_G_C_C_P_T_D_L_S_E_S(companyId, groupIds,
			calendarIds, calendarResourceIds, parentCalendarBookingId, titles,
			descriptions, locations, startTime, endTime, recurring, statuses,
			andOperator, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> findByFutureReminders(
		long startTime)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByFutureReminders(startTime);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> findByKeywords(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String keywords, long startTime, long endTime,
		boolean recurring, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByKeywords(companyId, groupIds, calendarIds,
			calendarResourceIds, parentCalendarBookingId, keywords, startTime,
			endTime, recurring, statuses, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> findByC_G_C_C_P_T_D_L_S_E_S(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String title, java.lang.String description,
		java.lang.String location, long startTime, long endTime,
		boolean recurring, int[] statuses, boolean andOperator, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByC_G_C_C_P_T_D_L_S_E_S(companyId, groupIds,
			calendarIds, calendarResourceIds, parentCalendarBookingId, title,
			description, location, startTime, endTime, recurring, statuses,
			andOperator, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> findByC_G_C_C_P_T_D_L_S_E_S(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String[] titles, java.lang.String[] descriptions,
		java.lang.String[] locations, long startTime, long endTime,
		boolean recurring, int[] statuses, boolean andOperator, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByC_G_C_C_P_T_D_L_S_E_S(companyId, groupIds,
			calendarIds, calendarResourceIds, parentCalendarBookingId, titles,
			descriptions, locations, startTime, endTime, recurring, statuses,
			andOperator, start, end, orderByComparator);
	}

	public static CalendarBookingFinder getFinder() {
		if (_finder == null) {
			_finder = (CalendarBookingFinder)PortletBeanLocatorUtil.locate(com.liferay.calendar.service.ClpSerializer.getServletContextName(),
					CalendarBookingFinder.class.getName());

			ReferenceRegistry.registerReference(CalendarBookingFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(CalendarBookingFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(CalendarBookingFinderUtil.class,
			"_finder");
	}

	private static CalendarBookingFinder _finder;
}