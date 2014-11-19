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

import aQute.bnd.annotation.ProviderType;

/**
 * @author Eduardo Lundgren
 * @generated
 */
@ProviderType
public interface CalendarResourceFinder {
	public int countByKeywords(long companyId, long[] groupIds,
		long[] classNameIds, java.lang.String keywords, boolean active);

	public int countByC_G_C_C_N_D_A(long companyId, long[] groupIds,
		long[] classNameIds, java.lang.String code, java.lang.String name,
		java.lang.String description, boolean active, boolean andOperator);

	public int countByC_G_C_C_N_D_A(long companyId, long[] groupIds,
		long[] classNameIds, java.lang.String[] codes,
		java.lang.String[] names, java.lang.String[] descriptions,
		boolean active, boolean andOperator);

	public int filterCountByKeywords(long companyId, long[] groupIds,
		long[] classNameIds, java.lang.String keywords, boolean active);

	public int filterCountByC_G_C_C_N_D_A(long companyId, long[] groupIds,
		long[] classNameIds, java.lang.String code, java.lang.String name,
		java.lang.String description, boolean active, boolean andOperator);

	public int filterCountByC_G_C_C_N_D_A(long companyId, long[] groupIds,
		long[] classNameIds, java.lang.String[] codes,
		java.lang.String[] names, java.lang.String[] descriptions,
		boolean active, boolean andOperator);

	public java.util.List<com.liferay.calendar.model.CalendarResource> filterFindByKeywords(
		long companyId, long[] groupIds, long[] classNameIds,
		java.lang.String keywords, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarResource> orderByComparator);

	public java.util.List<com.liferay.calendar.model.CalendarResource> filterFindByC_G_C_C_N_D_A(
		long companyId, long[] groupIds, long[] classNameIds,
		java.lang.String code, java.lang.String name,
		java.lang.String description, boolean active, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarResource> orderByComparator);

	public java.util.List<com.liferay.calendar.model.CalendarResource> filterFindByC_G_C_C_N_D_A(
		long companyId, long[] groupIds, long[] classNameIds,
		java.lang.String[] codes, java.lang.String[] names,
		java.lang.String[] descriptions, boolean active, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarResource> orderByComparator);

	public java.util.List<com.liferay.calendar.model.CalendarResource> findByKeywords(
		long companyId, long[] groupIds, long[] classNameIds,
		java.lang.String keywords, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarResource> orderByComparator);

	public java.util.List<com.liferay.calendar.model.CalendarResource> findByC_G_C_C_N_D_A(
		long companyId, long[] groupIds, long[] classNameIds,
		java.lang.String code, java.lang.String name,
		java.lang.String description, boolean active, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarResource> orderByComparator);

	public java.util.List<com.liferay.calendar.model.CalendarResource> findByC_G_C_C_N_D_A(
		long companyId, long[] groupIds, long[] classNameIds,
		java.lang.String[] codes, java.lang.String[] names,
		java.lang.String[] descriptions, boolean active, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarResource> orderByComparator);
}