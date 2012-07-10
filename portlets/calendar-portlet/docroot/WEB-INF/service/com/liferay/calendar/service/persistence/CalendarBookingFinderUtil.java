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
		long startDate, long endDate, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByKeywords(companyId, groupIds, calendarIds,
			calendarResourceIds, parentCalendarBookingId, keywords, startDate,
			endDate, statuses);
	}

	public static int countByC_G_C_C_P_T_D_L_S_E_S(long companyId,
		long[] groupIds, long[] calendarIds, long[] calendarResourceIds,
		long parentCalendarBookingId, java.lang.String title,
		java.lang.String description, java.lang.String location,
		long startDate, long endDate, int[] statuses, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByC_G_C_C_P_T_D_L_S_E_S(companyId, groupIds,
			calendarIds, calendarResourceIds, parentCalendarBookingId, title,
			description, location, startDate, endDate, statuses, andOperator);
	}

	public static int countByC_G_C_C_P_T_D_L_S_E_S(long companyId,
		long[] groupIds, long[] calendarIds, long[] calendarResourceIds,
		long parentCalendarBookingId, java.lang.String[] titles,
		java.lang.String[] descriptions, java.lang.String[] locations,
		long startDate, long endDate, int[] statuses, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByC_G_C_C_P_T_D_L_S_E_S(companyId, groupIds,
			calendarIds, calendarResourceIds, parentCalendarBookingId, titles,
			descriptions, locations, startDate, endDate, statuses, andOperator);
	}

	public static int filterCountByKeywords(long companyId, long[] groupIds,
		long[] calendarIds, long[] calendarResourceIds,
		long parentCalendarBookingId, java.lang.String keywords,
		long startDate, long endDate, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountByKeywords(companyId, groupIds, calendarIds,
			calendarResourceIds, parentCalendarBookingId, keywords, startDate,
			endDate, statuses);
	}

	public static int filterCountByC_G_C_C_P_T_D_L_S_E_S(long companyId,
		long[] groupIds, long[] calendarIds, long[] calendarResourceIds,
		long parentCalendarBookingId, java.lang.String title,
		java.lang.String description, java.lang.String location,
		long startDate, long endDate, int[] statuses, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountByC_G_C_C_P_T_D_L_S_E_S(companyId, groupIds,
			calendarIds, calendarResourceIds, parentCalendarBookingId, title,
			description, location, startDate, endDate, statuses, andOperator);
	}

	public static int filterCountByC_G_C_C_P_T_D_L_S_E_S(long companyId,
		long[] groupIds, long[] calendarIds, long[] calendarResourceIds,
		long parentCalendarBookingId, java.lang.String[] titles,
		java.lang.String[] descriptions, java.lang.String[] locations,
		long startDate, long endDate, int[] statuses, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountByC_G_C_C_P_T_D_L_S_E_S(companyId, groupIds,
			calendarIds, calendarResourceIds, parentCalendarBookingId, titles,
			descriptions, locations, startDate, endDate, statuses, andOperator);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> filterFindByKeywords(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String keywords, long startDate, long endDate,
		boolean recurring, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByKeywords(companyId, groupIds, calendarIds,
			calendarResourceIds, parentCalendarBookingId, keywords, startDate,
			endDate, recurring, statuses, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> filterFindByC_G_C_C_P_T_D_L_S_E_S(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String title, java.lang.String description,
		java.lang.String location, long startDate, long endDate,
		boolean recurring, int[] statuses, boolean andOperator, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByC_G_C_C_P_T_D_L_S_E_S(companyId, groupIds,
			calendarIds, calendarResourceIds, parentCalendarBookingId, title,
			description, location, startDate, endDate, recurring, statuses,
			andOperator, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> filterFindByC_G_C_C_P_T_D_L_S_E_S(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String[] titles, java.lang.String[] descriptions,
		java.lang.String[] locations, long startDate, long endDate,
		boolean recurring, int[] statuses, boolean andOperator, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByC_G_C_C_P_T_D_L_S_E_S(companyId, groupIds,
			calendarIds, calendarResourceIds, parentCalendarBookingId, titles,
			descriptions, locations, startDate, endDate, recurring, statuses,
			andOperator, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> findByFutureReminders(
		long startDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByFutureReminders(startDate);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> findByKeywords(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String keywords, long startDate, long endDate,
		boolean recurring, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByKeywords(companyId, groupIds, calendarIds,
			calendarResourceIds, parentCalendarBookingId, keywords, startDate,
			endDate, recurring, statuses, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> findByC_G_C_C_P_T_D_L_S_E_S(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String title, java.lang.String description,
		java.lang.String location, long startDate, long endDate,
		boolean recurring, int[] statuses, boolean andOperator, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByC_G_C_C_P_T_D_L_S_E_S(companyId, groupIds,
			calendarIds, calendarResourceIds, parentCalendarBookingId, title,
			description, location, startDate, endDate, recurring, statuses,
			andOperator, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarBooking> findByC_G_C_C_P_T_D_L_S_E_S(
		long companyId, long[] groupIds, long[] calendarIds,
		long[] calendarResourceIds, long parentCalendarBookingId,
		java.lang.String[] titles, java.lang.String[] descriptions,
		java.lang.String[] locations, long startDate, long endDate,
		boolean recurring, int[] statuses, boolean andOperator, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByC_G_C_C_P_T_D_L_S_E_S(companyId, groupIds,
			calendarIds, calendarResourceIds, parentCalendarBookingId, titles,
			descriptions, locations, startDate, endDate, recurring, statuses,
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