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

package com.liferay.calendar.service.persistence.impl;

import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.impl.CalendarImpl;
import com.liferay.calendar.model.impl.CalendarModelImpl;
import com.liferay.calendar.service.persistence.CalendarFinder;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 */
public class CalendarFinderImpl
	extends BasePersistenceImpl<Calendar>
	implements CalendarFinder {

	public static final String COUNT_BY_C_G_C_N_D =
		CalendarFinder.class.getName() + ".countByC_G_C_N_D";

	public static final String FIND_BY_C_G_C_N_D =
		CalendarFinder.class.getName() + ".findByC_G_C_N_D";

	@Override
	public int countByKeywords(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		String keywords) {

		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return countByC_G_C_N_D(
			companyId, groupIds, calendarResourceIds, names, descriptions,
			andOperator);
	}

	@Override
	public int countByC_G_C_N_D(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		String name, String description, boolean andOperator) {

		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description, false);

		return countByC_G_C_N_D(
			companyId, groupIds, calendarResourceIds, names, descriptions,
			andOperator);
	}

	@Override
	public int countByC_G_C_N_D(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		String[] names, String[] descriptions, boolean andOperator) {

		return doCountByC_G_C_N_D(
			companyId, groupIds, calendarResourceIds, names, descriptions,
			andOperator, false);
	}

	@Override
	public int filterCountByKeywords(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		String keywords) {

		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return filterCountByC_G_C_N_D(
			companyId, groupIds, calendarResourceIds, names, descriptions,
			andOperator);
	}

	@Override
	public int filterCountByC_G_C_N_D(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		String name, String description, boolean andOperator) {

		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description, false);

		return filterCountByC_G_C_N_D(
			companyId, groupIds, calendarResourceIds, names, descriptions,
			andOperator);
	}

	@Override
	public int filterCountByC_G_C_N_D(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		String[] names, String[] descriptions, boolean andOperator) {

		return doCountByC_G_C_N_D(
			companyId, groupIds, calendarResourceIds, names, descriptions,
			andOperator, false);
	}

	@Override
	public List<Calendar> filterFindByKeywords(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		String keywords, int start, int end,
		OrderByComparator<Calendar> orderByComparator) {

		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return filterFindByC_G_C_N_D(
			companyId, groupIds, calendarResourceIds, names, descriptions,
			andOperator, start, end, orderByComparator);
	}

	@Override
	public List<Calendar> filterFindByC_G_C_N_D(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		String name, String description, boolean andOperator, int start,
		int end, OrderByComparator<Calendar> orderByComparator) {

		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description, false);

		return filterFindByC_G_C_N_D(
			companyId, groupIds, calendarResourceIds, names, descriptions,
			andOperator, start, end, orderByComparator);
	}

	@Override
	public List<Calendar> filterFindByC_G_C_N_D(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		String[] names, String[] descriptions, boolean andOperator, int start,
		int end, OrderByComparator<Calendar> orderByComparator) {

		return doFindByC_G_C_N_D(
			companyId, groupIds, calendarResourceIds, names, descriptions,
			andOperator, start, end, orderByComparator, true);
	}

	@Override
	public List<Calendar> findByKeywords(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		String keywords, int start, int end,
		OrderByComparator<Calendar> orderByComparator) {

		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return findByC_G_C_N_D(
			companyId, groupIds, calendarResourceIds, names, descriptions,
			andOperator, start, end, orderByComparator);
	}

	@Override
	public List<Calendar> findByC_G_C_N_D(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		String name, String description, boolean andOperator, int start,
		int end, OrderByComparator<Calendar> orderByComparator) {

		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description, false);

		return findByC_G_C_N_D(
			companyId, groupIds, calendarResourceIds, names, descriptions,
			andOperator, start, end, orderByComparator);
	}

	@Override
	public List<Calendar> findByC_G_C_N_D(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		String[] names, String[] descriptions, boolean andOperator, int start,
		int end, OrderByComparator<Calendar> orderByComparator) {

		return doFindByC_G_C_N_D(
			companyId, groupIds, calendarResourceIds, names, descriptions,
			andOperator, start, end, orderByComparator, false);
	}

	protected int doCountByC_G_C_N_D(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		String[] names, String[] descriptions, boolean andOperator,
		boolean inlineSQLHelper) {

		if (groupIds == null) {
			groupIds = new long[0];
		}

		names = CustomSQLUtil.keywords(names);
		descriptions = CustomSQLUtil.keywords(descriptions, false);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_C_G_C_N_D);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, Calendar.class.getName(), "Calendar.calendarId",
					groupIds);
			}

			sql = StringUtil.replace(
				sql, "[$GROUP_ID$]", getGroupIds(groupIds));
			sql = StringUtil.replace(
				sql, "[$CALENDAR_RESOURCE_ID$]",
				getCalendarResourceIds(calendarResourceIds));
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(name)", StringPool.LIKE, false, names);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "description", StringPool.LIKE, false, descriptions);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(groupIds);

			if (ArrayUtil.isNotEmpty(calendarResourceIds)) {
				qPos.add(calendarResourceIds);
			}

			qPos.add(names, 2);
			qPos.add(descriptions, 2);

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected List<Calendar> doFindByC_G_C_N_D(
		long companyId, long[] groupIds, long[] calendarResourceIds,
		String[] names, String[] descriptions, boolean andOperator, int start,
		int end, OrderByComparator<Calendar> orderByComparator,
		boolean inlineSQLHelper) {

		if (groupIds == null) {
			groupIds = new long[0];
		}

		names = CustomSQLUtil.keywords(names);
		descriptions = CustomSQLUtil.keywords(descriptions, false);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_C_G_C_N_D);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, Calendar.class.getName(), "Calendar.calendarId",
					groupIds);
			}

			sql = StringUtil.replace(
				sql, "[$GROUP_ID$]", getGroupIds(groupIds));
			sql = StringUtil.replace(
				sql, "[$CALENDAR_RESOURCE_ID$]",
				getCalendarResourceIds(calendarResourceIds));
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(name)", StringPool.LIKE, false, names);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "description", StringPool.LIKE, false, descriptions);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			StringBundler sb = new StringBundler();

			if (orderByComparator != null) {
				appendOrderByComparator(sb, "Calendar.", orderByComparator);
			}

			sql = StringUtil.replace(sql, "[$ORDER_BY$]", sb.toString());

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("Calendar", CalendarImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(groupIds);

			if (ArrayUtil.isNotEmpty(calendarResourceIds)) {
				qPos.add(calendarResourceIds);
			}

			qPos.add(names, 2);
			qPos.add(descriptions, 2);

			return (List<Calendar>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected String getCalendarResourceIds(long[] calendarResourceIds) {
		if (ArrayUtil.isEmpty(calendarResourceIds)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(calendarResourceIds.length * 2);

		sb.append("(");

		for (int i = 0; i < calendarResourceIds.length; i++) {
			sb.append("calendarResourceId = ?");

			if ((i + 1) < calendarResourceIds.length) {
				sb.append(" OR ");
			}
		}

		sb.append(") AND");

		return sb.toString();
	}

	protected String getGroupIds(long[] groupIds) {
		if (groupIds.length == 0) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(groupIds.length * 2);

		sb.append("(");

		for (int i = 0; i < groupIds.length; i++) {
			sb.append("groupId = ?");

			if ((i + 1) < groupIds.length) {
				sb.append(" OR ");
			}
		}

		sb.append(") AND");

		return sb.toString();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CalendarModelImpl.TABLE_COLUMNS_MAP;
	}

}