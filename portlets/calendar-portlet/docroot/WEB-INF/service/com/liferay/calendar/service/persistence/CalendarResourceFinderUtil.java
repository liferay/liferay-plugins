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
public class CalendarResourceFinderUtil {
	public static int countByKeywords(long companyId, long[] groupIds,
		long[] classNameIds, java.lang.String keywords, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByKeywords(companyId, groupIds, classNameIds,
			keywords, active);
	}

	public static int countByC_G_C_C_N_D_A(long companyId, long[] groupIds,
		long[] classNameIds, java.lang.String code, java.lang.String name,
		java.lang.String description, boolean active, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByC_G_C_C_N_D_A(companyId, groupIds, classNameIds,
			code, name, description, active, andOperator);
	}

	public static int countByC_G_C_C_N_D_A(long companyId, long[] groupIds,
		long[] classNameIds, java.lang.String[] codes,
		java.lang.String[] names, java.lang.String[] descriptions,
		boolean active, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByC_G_C_C_N_D_A(companyId, groupIds, classNameIds,
			codes, names, descriptions, active, andOperator);
	}

	public static int filterCountByKeywords(long companyId, long[] groupIds,
		long[] classNameIds, java.lang.String keywords, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountByKeywords(companyId, groupIds, classNameIds,
			keywords, active);
	}

	public static int filterCountByC_G_C_C_N_D_A(long companyId,
		long[] groupIds, long[] classNameIds, java.lang.String code,
		java.lang.String name, java.lang.String description, boolean active,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountByC_G_C_C_N_D_A(companyId, groupIds,
			classNameIds, code, name, description, active, andOperator);
	}

	public static int filterCountByC_G_C_C_N_D_A(long companyId,
		long[] groupIds, long[] classNameIds, java.lang.String[] codes,
		java.lang.String[] names, java.lang.String[] descriptions,
		boolean active, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountByC_G_C_C_N_D_A(companyId, groupIds,
			classNameIds, codes, names, descriptions, active, andOperator);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarResource> filterFindByKeywords(
		long companyId, long[] groupIds, long[] classNameIds,
		java.lang.String keywords, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByKeywords(companyId, groupIds, classNameIds,
			keywords, active, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarResource> filterFindByC_G_C_C_N_D_A(
		long companyId, long[] groupIds, long[] classNameIds,
		java.lang.String code, java.lang.String name,
		java.lang.String description, boolean active, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByC_G_C_C_N_D_A(companyId, groupIds,
			classNameIds, code, name, description, active, andOperator, start,
			end, orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarResource> filterFindByC_G_C_C_N_D_A(
		long companyId, long[] groupIds, long[] classNameIds,
		java.lang.String[] codes, java.lang.String[] names,
		java.lang.String[] descriptions, boolean active, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByC_G_C_C_N_D_A(companyId, groupIds,
			classNameIds, codes, names, descriptions, active, andOperator,
			start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByKeywords(
		long companyId, long[] groupIds, long[] classNameIds,
		java.lang.String keywords, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByKeywords(companyId, groupIds, classNameIds, keywords,
			active, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByC_G_C_C_N_D_A(
		long companyId, long[] groupIds, long[] classNameIds,
		java.lang.String code, java.lang.String name,
		java.lang.String description, boolean active, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByC_G_C_C_N_D_A(companyId, groupIds, classNameIds,
			code, name, description, active, andOperator, start, end,
			orderByComparator);
	}

	public static java.util.List<com.liferay.calendar.model.CalendarResource> findByC_G_C_C_N_D_A(
		long companyId, long[] groupIds, long[] classNameIds,
		java.lang.String[] codes, java.lang.String[] names,
		java.lang.String[] descriptions, boolean active, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByC_G_C_C_N_D_A(companyId, groupIds, classNameIds,
			codes, names, descriptions, active, andOperator, start, end,
			orderByComparator);
	}

	public static CalendarResourceFinder getFinder() {
		if (_finder == null) {
			_finder = (CalendarResourceFinder)PortletBeanLocatorUtil.locate(com.liferay.calendar.service.ClpSerializer.getServletContextName(),
					CalendarResourceFinder.class.getName());

			ReferenceRegistry.registerReference(CalendarResourceFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(CalendarResourceFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(CalendarResourceFinderUtil.class,
			"_finder");
	}

	private static CalendarResourceFinder _finder;
}