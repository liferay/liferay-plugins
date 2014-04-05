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
public class CalendarFinderUtil {
	public static int countByKeywords(long companyId, long[] groupIds,
		long[] calendarResourceIds, java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByKeywords(companyId, groupIds, calendarResourceIds,
			keywords);
	}

	public static int countByC_G_C_N_D(long companyId, long[] groupIds,
		long[] calendarResourceIds, java.lang.String name,
		java.lang.String description, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByC_G_C_N_D(companyId, groupIds, calendarResourceIds,
			name, description, andOperator);
	}

	public static int countByC_G_C_N_D(long companyId, long[] groupIds,
		long[] calendarResourceIds, java.lang.String[] names,
		java.lang.String[] descriptions, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByC_G_C_N_D(companyId, groupIds, calendarResourceIds,
			names, descriptions, andOperator);
	}

	public static int filterCountByKeywords(long companyId, long[] groupIds,
		long[] calendarResourceIds, java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountByKeywords(companyId, groupIds,
			calendarResourceIds, keywords);
	}

	public static int filterCountByC_G_C_N_D(long companyId, long[] groupIds,
		long[] calendarResourceIds, java.lang.String name,
		java.lang.String description, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountByC_G_C_N_D(companyId, groupIds,
			calendarResourceIds, name, description, andOperator);
	}

	public static int filterCountByC_G_C_N_D(long companyId, long[] groupIds,
		long[] calendarResourceIds, java.lang.String[] names,
		java.lang.String[] descriptions, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountByC_G_C_N_D(companyId, groupIds,
			calendarResourceIds, names, descriptions, andOperator);
	}

	public static java.util.List<com.liferay.calendar.model.Calendar> filterFindByKeywords(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByKeywords(companyId, groupIds,
			calendarResourceIds, keywords, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.Calendar> filterFindByC_G_C_N_D(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		java.lang.String name, java.lang.String description,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByC_G_C_N_D(companyId, groupIds,
			calendarResourceIds, name, description, andOperator, start, end,
			orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.Calendar> filterFindByC_G_C_N_D(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		java.lang.String[] names, java.lang.String[] descriptions,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByC_G_C_N_D(companyId, groupIds,
			calendarResourceIds, names, descriptions, andOperator, start, end,
			orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.Calendar> findByKeywords(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByKeywords(companyId, groupIds, calendarResourceIds,
			keywords, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.Calendar> findByC_G_C_N_D(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		java.lang.String name, java.lang.String description,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByC_G_C_N_D(companyId, groupIds, calendarResourceIds,
			name, description, andOperator, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.Calendar> findByC_G_C_N_D(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		java.lang.String[] names, java.lang.String[] descriptions,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByC_G_C_N_D(companyId, groupIds, calendarResourceIds,
			names, descriptions, andOperator, start, end, orderByComparator);
	}

	public static CalendarFinder getFinder() {
		if (_finder == null) {
			_finder = (CalendarFinder)PortletBeanLocatorUtil.locate(com.liferay.calendar.service.ClpSerializer.getServletContextName(),
					CalendarFinder.class.getName());

			ReferenceRegistry.registerReference(CalendarFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(CalendarFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(CalendarFinderUtil.class, "_finder");
	}

	private static CalendarFinder _finder;
}