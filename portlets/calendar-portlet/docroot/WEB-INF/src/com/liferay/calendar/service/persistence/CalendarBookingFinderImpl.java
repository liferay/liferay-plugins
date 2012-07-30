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

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.impl.CalendarBookingImpl;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
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

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 */
public class CalendarBookingFinderImpl
	extends BasePersistenceImpl<CalendarBooking>
	implements CalendarBookingFinder {

	public static final String COUNT_BY_C_G_C_C_P_T_D_L_S_E_S =
		CalendarBookingFinder.class.getName() +
			".countByC_G_C_C_P_T_D_L_S_E_S";

	public static final String FIND_BY_FUTURE_REMINDERS =
	CalendarBookingFinder.class.getName() + ".findByFutureReminders";

	public static final String FIND_BY_C_G_C_C_P_T_D_L_S_E_S =
		CalendarBookingFinder.class.getName() +
			".findByC_G_C_C_P_T_D_L_S_E_S";

	public int countByKeywords(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String keywords, long startDate, long endDate, int[] statuses)
		throws SystemException {

		String[] titles = null;
		String[] descriptions = null;
		String[] locations = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			titles = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
			locations = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return countByC_G_C_C_P_T_D_L_S_E_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, titles, descriptions, locations, startDate,
			endDate, statuses, andOperator);
	}

	public int countByC_G_C_C_P_T_D_L_S_E_S(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String title, String description, String location, long startDate,
			long endDate, int[] statuses, boolean andOperator)
		throws SystemException {

		String[] titles = CustomSQLUtil.keywords(title);
		String[] descriptions = CustomSQLUtil.keywords(description, false);
		String[] locations = CustomSQLUtil.keywords(location);

		return countByC_G_C_C_P_T_D_L_S_E_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, titles, descriptions, locations, startDate,
			endDate, statuses, andOperator);
	}

	public int countByC_G_C_C_P_T_D_L_S_E_S(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String[] titles, String[] descriptions, String[] locations,
			long startDate, long endDate, int[] statuses, boolean andOperator)
		throws SystemException {

		return doCountByC_G_C_C_P_T_D_L_S_E_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, titles, descriptions, locations, startDate,
			endDate, statuses, andOperator, false);
	}

	public int filterCountByKeywords(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String keywords, long startDate, long endDate, int[] statuses)
		throws SystemException {

		String[] titles = null;
		String[] descriptions = null;
		String[] locations = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			titles = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
			locations = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return filterCountByC_G_C_C_P_T_D_L_S_E_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, titles, descriptions, locations, startDate,
			endDate, statuses, andOperator);
	}

	public int filterCountByC_G_C_C_P_T_D_L_S_E_S(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String title, String description, String location, long startDate,
			long endDate, int[] statuses, boolean andOperator)
		throws SystemException {

		String[] titles = CustomSQLUtil.keywords(title);
		String[] descriptions = CustomSQLUtil.keywords(description, false);
		String[] locations = CustomSQLUtil.keywords(location);

		return filterCountByC_G_C_C_P_T_D_L_S_E_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, titles, descriptions, locations, startDate,
			endDate, statuses, andOperator);
	}

	public int filterCountByC_G_C_C_P_T_D_L_S_E_S(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String[] titles, String[] descriptions, String[] locations,
			long startDate, long endDate, int[] statuses, boolean andOperator)
		throws SystemException {

		return doCountByC_G_C_C_P_T_D_L_S_E_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, titles, descriptions, locations, startDate,
			endDate, statuses, andOperator, true);
	}

	public List<CalendarBooking> filterFindByKeywords(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String keywords, long startDate, long endDate, boolean recurring,
			int[] statuses, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		String[] titles = null;
		String[] descriptions = null;
		String[] locations = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			titles = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
			locations = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return filterFindByC_G_C_C_P_T_D_L_S_E_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, titles, descriptions, locations, startDate,
			endDate, recurring, statuses, andOperator, start, end,
			orderByComparator);
	}

	public List<CalendarBooking> filterFindByC_G_C_C_P_T_D_L_S_E_S(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String title, String description, String location, long startDate,
			long endDate, boolean recurring, int[] statuses,
			boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		String[] titles = CustomSQLUtil.keywords(title);
		String[] descriptions = CustomSQLUtil.keywords(description, false);
		String[] locations = CustomSQLUtil.keywords(location);

		return filterFindByC_G_C_C_P_T_D_L_S_E_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, titles, descriptions, locations, startDate,
			endDate, recurring, statuses, andOperator, start, end,
			orderByComparator);
	}

	public List<CalendarBooking> filterFindByC_G_C_C_P_T_D_L_S_E_S(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String[] titles, String[] descriptions, String[] locations,
			long startDate, long endDate, boolean recurring, int[] statuses,
			boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return doFindByC_G_C_C_P_T_D_L_S_E_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, titles, descriptions, locations, startDate,
			endDate, recurring, statuses, andOperator, start, end,
			orderByComparator, true);
	}

	public List<CalendarBooking> findByFutureReminders(long startDate)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_FUTURE_REMINDERS);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("CalendarBooking", CalendarBookingImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(startDate);

			return q.list(true);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<CalendarBooking> findByKeywords(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String keywords, long startDate, long endDate, boolean recurring,
			int[] statuses, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		String[] titles = null;
		String[] descriptions = null;
		String[] locations = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			titles = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
			locations = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return findByC_G_C_C_P_T_D_L_S_E_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, titles, descriptions, locations, startDate,
			endDate, recurring, statuses, andOperator, start, end,
			orderByComparator);
	}

	public List<CalendarBooking> findByC_G_C_C_P_T_D_L_S_E_S(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String title, String description, String location, long startDate,
			long endDate, boolean recurring, int[] statuses,
			boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		String[] titles = CustomSQLUtil.keywords(title);
		String[] descriptions = CustomSQLUtil.keywords(description, false);
		String[] locations = CustomSQLUtil.keywords(location);

		return findByC_G_C_C_P_T_D_L_S_E_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, titles, descriptions, locations, startDate,
			endDate, recurring, statuses, andOperator, start, end,
			orderByComparator);
	}

	public List<CalendarBooking> findByC_G_C_C_P_T_D_L_S_E_S(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String[] titles, String[] descriptions, String[] locations,
			long startDate, long endDate, boolean recurring, int[] statuses,
			boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return doFindByC_G_C_C_P_T_D_L_S_E_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, titles, descriptions, locations, startDate,
			endDate, recurring, statuses, andOperator, start, end,
			orderByComparator, false);
	}

	protected int doCountByC_G_C_C_P_T_D_L_S_E_S(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String[] titles, String[] descriptions, String[] locations,
			long startDate, long endDate, int[] statuses, boolean andOperator,
			boolean inlineSQLHelper)
		throws SystemException {

		titles = CustomSQLUtil.keywords(titles);
		descriptions = CustomSQLUtil.keywords(descriptions, false);
		locations = CustomSQLUtil.keywords(locations);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_C_G_C_C_P_T_D_L_S_E_S);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, CalendarBooking.class.getName(),
					"CalendarBooking.calendarBookingId", groupIds);
			}

			sql = StringUtil.replace(
				sql, "[$GROUP_ID$]", getGroupIds(groupIds));
			sql = StringUtil.replace(
				sql, "[$CALENDAR_ID$]", getCalendarIds(calendarIds));
			sql = StringUtil.replace(
				sql, "[$CALENDAR_RESOURCE_ID$]",
				getCalendarResourceIds(calendarResourceIds));
			sql = StringUtil.replace(sql, "[$STATUS$]", getStatuses(statuses));

			if (parentCalendarBookingId < 0) {
				sql = StringUtil.replace(
					sql, "(parentCalendarBookingId = ?) AND", StringPool.BLANK);
			}

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(title)", StringPool.LIKE, false, titles);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "description", StringPool.LIKE, false, descriptions);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(location)", StringPool.LIKE, false, locations);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(groupIds);

			if ((calendarIds != null) && (calendarIds.length > 0)) {
				qPos.add(calendarIds);
			}

			if ((calendarResourceIds != null) &&
				(calendarResourceIds.length > 0)) {

				qPos.add(calendarResourceIds);
			}

			if (parentCalendarBookingId >= 0) {
				qPos.add(parentCalendarBookingId);
			}

			qPos.add(titles, 2);
			qPos.add(descriptions, 2);
			qPos.add(locations, 2);

			if ((statuses != null) && (statuses.length > 0)) {
				qPos.add(statuses);
			}

			qPos.add(startDate);
			qPos.add(startDate);
			qPos.add(endDate);
			qPos.add(endDate);

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

	protected List<CalendarBooking> doFindByC_G_C_C_P_T_D_L_S_E_S(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String[] titles, String[] descriptions, String[] locations,
			long startDate, long endDate, boolean recurring, int[] statuses,
			boolean andOperator, int start, int end,
			OrderByComparator orderByComparator, boolean inlineSQLHelper)
		throws SystemException {

		titles = CustomSQLUtil.keywords(titles);
		descriptions = CustomSQLUtil.keywords(descriptions, false);
		locations = CustomSQLUtil.keywords(locations);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_C_G_C_C_P_T_D_L_S_E_S);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, CalendarBooking.class.getName(),
					"CalendarBooking.calendarBookingId", groupIds);
			}

			sql = StringUtil.replace(
				sql, "[$GROUP_ID$]", getGroupIds(groupIds));
			sql = StringUtil.replace(
				sql, "[$CALENDAR_ID$]", getCalendarIds(calendarIds));
			sql = StringUtil.replace(
				sql, "[$CALENDAR_RESOURCE_ID$]",
				getCalendarResourceIds(calendarResourceIds));
			sql = StringUtil.replace(
				sql, "[$DATE_RANGE$]",
				getDateRange(startDate, endDate, recurring));
			sql = StringUtil.replace(sql, "[$STATUS$]", getStatuses(statuses));

			if (parentCalendarBookingId < 0) {
				sql = StringUtil.replace(
					sql, "(parentCalendarBookingId = ?) AND", StringPool.BLANK);
			}

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(title)", StringPool.LIKE, false, titles);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "description", StringPool.LIKE, false, descriptions);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(location)", StringPool.LIKE, false, locations);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			StringBundler sb = new StringBundler();

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, "CalendarBooking.", orderByComparator);
			}

			sql = StringUtil.replace(sql, "[$ORDER_BY$]", sb.toString());

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("CalendarBooking", CalendarBookingImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(groupIds);

			if ((calendarIds != null) && (calendarIds.length > 0)) {
				qPos.add(calendarIds);
			}

			if ((calendarResourceIds != null) &&
				(calendarResourceIds.length > 0)) {

				qPos.add(calendarResourceIds);
			}

			if (parentCalendarBookingId >= 0) {
				qPos.add(parentCalendarBookingId);
			}

			qPos.add(titles, 2);
			qPos.add(descriptions, 2);
			qPos.add(locations, 2);
			qPos.add(startDate);
			qPos.add(startDate);
			qPos.add(endDate);
			qPos.add(endDate);

			if (recurring) {
				qPos.add(endDate);
				qPos.add(endDate);
			}

			if ((statuses != null) && (statuses.length > 0)) {
				qPos.add(statuses);
			}

			return (List<CalendarBooking>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected String getCalendarIds(long[] calendarIds) {
		if ((calendarIds == null) || (calendarIds.length == 0)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(calendarIds.length * 2 + 1);

		sb.append(" (");

		for (int i = 0; i < calendarIds.length; i++) {
			sb.append("calendarId = ? ");

			if ((i + 1) != calendarIds.length) {
				sb.append("OR ");
			}
		}

		sb.append(") AND");

		return sb.toString();
	}

	protected String getCalendarResourceIds(long[] calendarResourceIds) {
		if ((calendarResourceIds == null) ||
				(calendarResourceIds.length == 0)) {

			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(
				calendarResourceIds.length * 2 + 1);

		sb.append(" (");

		for (int i = 0; i < calendarResourceIds.length; i++) {
			sb.append("calendarResourceId = ? ");

			if ((i + 1) != calendarResourceIds.length) {
				sb.append("OR ");
			}
		}

		sb.append(") AND");

		return sb.toString();
	}

	protected String getDateRange(
		long startDate, long endDate, boolean recurring) {

		StringBundler sb = new StringBundler(7);

		sb.append("((startDate <= ? OR ? = -1) AND ");
		sb.append("(endDate >= ? OR ? = -1) AND ");
		sb.append("(recurrence IS NULL) ");

		if (recurring) {
			sb.append(" OR (");
			sb.append("(endDate <= ? OR ? = -1) AND ");
			sb.append("(recurrence IS NOT NULL))");
		}

		sb.append(")");

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

	protected String getStatuses(int[] statuses) {
		if ((statuses == null) || (statuses.length == 0)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(statuses.length * 2 + 1);

		sb.append(" (");

		for (int i = 0; i < statuses.length; i++) {
			sb.append("status = ? ");

			if ((i + 1) != statuses.length) {
				sb.append("OR ");
			}
		}

		sb.append(") ");

		return sb.toString();
	}

}