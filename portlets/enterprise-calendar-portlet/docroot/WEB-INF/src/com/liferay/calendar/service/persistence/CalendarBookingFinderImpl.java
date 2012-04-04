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
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.sql.Timestamp;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 */

public class CalendarBookingFinderImpl
	extends BasePersistenceImpl<CalendarBooking>
	implements CalendarBookingFinder {

	public static final String COUNT_BY_C_G_C_C_P_T_D_L_T_S_E_P_S =
		CalendarBookingFinder.class.getName() +
		".countByC_G_C_C_P_T_D_L_T_S_E_P_S";

	public static final String FIND_BY_C_G_C_C_P_T_D_L_T_S_E_P_S =
		CalendarBookingFinder.class.getName() +
		".findByC_G_C_C_P_T_D_L_T_S_E_P_S";

	public int countByC_G_C_C_P_T_D_L_T_S_E_P_S(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String title, String description, String location, String type,
			Date startDate, Date endDate, Integer priority, int status,
			boolean andOperator)
		throws SystemException {

		String[] titles = CustomSQLUtil.keywords(title);
		String[] descriptions = CustomSQLUtil.keywords(description, false);
		String[] locations = CustomSQLUtil.keywords(location);
		String[] types = CustomSQLUtil.keywords(type, false);

		return countByC_G_C_C_P_T_D_L_T_S_E_P_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, titles, descriptions, locations, types,
			startDate, endDate, priority, status, andOperator);
	}

	public int countByC_G_C_C_P_T_D_L_T_S_E_P_S(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String[] titles, String[] descriptions, String[] locations,
			String[] types, Date startDate, Date endDate, Integer priority,
			int status, boolean andOperator)
		throws SystemException {

		return doCountByC_G_C_C_P_T_D_L_T_S_E_P_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, titles, descriptions, locations, types,
			startDate, endDate, priority, status, andOperator, false);
	}

	public int countByKeywords(
			long companyId, long[] groupIds, long[] calendarIds,
			String keywords, long[] calendarResourceIds,
			long parentCalendarBookingId, Date startDate, Date endDate,
			Integer priority, int status)
		throws SystemException {

		String[] titles = null;
		String[] descriptions = null;
		String[] locations = null;
		String[] types = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			titles = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
			locations = CustomSQLUtil.keywords(keywords);
			types = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return countByC_G_C_C_P_T_D_L_T_S_E_P_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, titles, descriptions, locations, types,
			startDate, endDate, priority, status, andOperator);
	}

	public int filterCountByC_G_C_C_P_T_D_L_T_S_E_P_S(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String title, String description, String location, String type,
			Date startDate, Date endDate, Integer priority, int status,
			boolean andOperator)
		throws SystemException {

		String[] titles = CustomSQLUtil.keywords(title);
		String[] descriptions = CustomSQLUtil.keywords(description, false);
		String[] locations = CustomSQLUtil.keywords(location);
		String[] types = CustomSQLUtil.keywords(type, false);

		return filterCountByC_G_C_C_P_T_D_L_T_S_E_P_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, titles, descriptions, locations, types,
			startDate, endDate, priority, status, andOperator);
	}

	public int filterCountByC_G_C_C_P_T_D_L_T_S_E_P_S(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String[] titles, String[] descriptions, String[] locations,
			String[] types, Date startDate, Date endDate, Integer priority,
			int status, boolean andOperator)
		throws SystemException {

		return doCountByC_G_C_C_P_T_D_L_T_S_E_P_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, titles, descriptions, locations, types,
			startDate, endDate, priority, status, andOperator, true);
	}

	public int filterCountByKeywords(
			long companyId, long[] groupIds, long[] calendarIds,
			String keywords, long[] calendarResourceIds,
			long parentCalendarBookingId, Date startDate, Date endDate,
			Integer priority, int status)
		throws SystemException {

		String[] titles = null;
		String[] descriptions = null;
		String[] locations = null;
		String[] types = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			titles = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
			locations = CustomSQLUtil.keywords(keywords);
			types = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return filterCountByC_G_C_C_P_T_D_L_T_S_E_P_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, titles, descriptions, locations, types,
			startDate, endDate, priority, status, andOperator);
	}

	public List<CalendarBooking> filterFindByC_G_C_C_P_T_D_L_T_S_E_P_S(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String title, String description, String location, String type,
			Date startDate, Date endDate, Integer priority, int status,
			boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		String[] titles = CustomSQLUtil.keywords(title);
		String[] descriptions = CustomSQLUtil.keywords(description, false);
		String[] locations = CustomSQLUtil.keywords(location);
		String[] types = CustomSQLUtil.keywords(type, false);

		return filterFindByC_G_C_C_P_T_D_L_T_S_E_P_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, titles, descriptions, locations, types,
			startDate, endDate, priority, status, andOperator, start, end,
			orderByComparator);
	}

	public List<CalendarBooking> filterFindByC_G_C_C_P_T_D_L_T_S_E_P_S(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String[] titles, String[] descriptions, String[] locations,
			String[] types, Date startDate, Date endDate, Integer priority,
			int status, boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return doFindByC_G_C_C_P_T_D_L_T_S_E_P_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, titles, descriptions, locations, types,
			startDate, endDate, priority, status, andOperator, start, end,
			orderByComparator, true);
	}

	public List<CalendarBooking> filterFindByKeywords(
			long companyId, long[] groupIds, long[] calendarIds,
			String keywords, long[] calendarResourceIds,
			long parentCalendarBookingId, Date startDate, Date endDate,
			Integer priority, int status, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		String[] titles = null;
		String[] descriptions = null;
		String[] locations = null;
		String[] types = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			titles = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
			locations = CustomSQLUtil.keywords(keywords);
			types = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return filterFindByC_G_C_C_P_T_D_L_T_S_E_P_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, titles, descriptions, locations, types,
			startDate, endDate, priority, status, andOperator, start, end,
			orderByComparator);
	}

	public List<CalendarBooking> findByC_G_C_C_P_T_D_L_T_S_E_P_S(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String title, String description, String location, String type,
			Date startDate, Date endDate, Integer priority, int status,
			boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		String[] titles = CustomSQLUtil.keywords(title);
		String[] descriptions = CustomSQLUtil.keywords(description, false);
		String[] locations = CustomSQLUtil.keywords(location);
		String[] types = CustomSQLUtil.keywords(type, false);

		return findByC_G_C_C_P_T_D_L_T_S_E_P_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, titles, descriptions, locations, types,
			startDate, endDate, priority, status, andOperator, start, end,
			orderByComparator);
	}

	public List<CalendarBooking> findByC_G_C_C_P_T_D_L_T_S_E_P_S(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String[] titles, String[] descriptions, String[] locations,
			String[] types, Date startDate, Date endDate, Integer priority,
			int status, boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return doFindByC_G_C_C_P_T_D_L_T_S_E_P_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, titles, descriptions, locations, types,
			startDate, endDate, priority, status, andOperator, start, end,
			orderByComparator, false);
	}

	public List<CalendarBooking> findByKeywords(
			long companyId, long[] groupIds, long[] calendarIds,
			String keywords, long[] calendarResourceIds,
			long parentCalendarBookingId, Date startDate, Date endDate,
			Integer priority, int status, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		String[] titles = null;
		String[] descriptions = null;
		String[] locations = null;
		String[] types = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			titles = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
			locations = CustomSQLUtil.keywords(keywords);
			types = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return findByC_G_C_C_P_T_D_L_T_S_E_P_S(
			companyId, groupIds, calendarIds, calendarResourceIds,
			parentCalendarBookingId, titles, descriptions, locations, types,
			startDate, endDate, priority, status, andOperator, start, end,
			orderByComparator);
	}

	protected int doCountByC_G_C_C_P_T_D_L_T_S_E_P_S(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String[] titles, String[] descriptions, String[] locations,
			String[] types, Date startDate, Date endDate, Integer priority,
			int status, boolean andOperator, boolean inlineSQLHelper)
		throws SystemException {

		titles = CustomSQLUtil.keywords(titles);
		descriptions = CustomSQLUtil.keywords(descriptions, false);
		locations = CustomSQLUtil.keywords(locations);
		types = CustomSQLUtil.keywords(types, false);
		Timestamp startDate_TS = CalendarUtil.getTimestamp(startDate);
		Timestamp endDate_TS = CalendarUtil.getTimestamp(endDate);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_C_G_C_C_P_T_D_L_T_S_E_P_S);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, CalendarBooking.class.getName(),
					"CalendarBooking.CalendarBookingId", groupIds);
			}

			sql = StringUtil.replace(
				sql, "[$GROUP_ID$]", getGroupIds(groupIds));
			sql = StringUtil.replace(
				sql, "[$CALENDAR_ID$]", getCalendarIds(calendarIds));
			sql = StringUtil.replace(
				sql, "[$CALENDAR_RESOURCE_ID$]",
				getCalendarResourceIds(calendarResourceIds));

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
			sql = CustomSQLUtil.replaceKeywords(
				sql, "type_", StringPool.LIKE, true, types);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			if (priority == null) {
				sql = StringUtil.replace(sql, "(priority = ? ) AND", "");
			}

			if (status == WorkflowConstants.STATUS_ANY) {
				sql = StringUtil.replace(sql, "(status = ?) AND", "");
			}

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
			qPos.add(types, 2);

			if (priority != null) {
				qPos.add(priority);
			}

			if (status != WorkflowConstants.STATUS_ANY) {
				qPos.add(status);
			}

			qPos.add(startDate_TS);
			qPos.add(startDate_TS);
			qPos.add(endDate_TS);
			qPos.add(endDate_TS);

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

	protected List<CalendarBooking> doFindByC_G_C_C_P_T_D_L_T_S_E_P_S(
			long companyId, long[] groupIds, long[] calendarIds,
			long[] calendarResourceIds, long parentCalendarBookingId,
			String[] titles, String[] descriptions, String[] locations,
			String[] types, Date startDate, Date endDate, Integer priority,
			int status, boolean andOperator, int start, int end,
			OrderByComparator orderByComparator, boolean inlineSQLHelper)
		throws SystemException {

		titles = CustomSQLUtil.keywords(titles);
		descriptions = CustomSQLUtil.keywords(descriptions, false);
		locations = CustomSQLUtil.keywords(locations);
		types = CustomSQLUtil.keywords(types, false);
		Timestamp startDate_TS = CalendarUtil.getTimestamp(startDate);
		Timestamp endDate_TS = CalendarUtil.getTimestamp(endDate);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_C_G_C_C_P_T_D_L_T_S_E_P_S);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, CalendarBooking.class.getName(),
					"CalendarBooking.CalendarBookingId", groupIds);
			}

			sql = StringUtil.replace(
				sql, "[$GROUP_ID$]", getGroupIds(groupIds));
			sql = StringUtil.replace(
				sql, "[$CALENDAR_ID$]", getCalendarIds(calendarIds));
			sql = StringUtil.replace(
				sql, "[$CALENDAR_RESOURCE_ID$]",
				getCalendarResourceIds(calendarResourceIds));

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
			sql = CustomSQLUtil.replaceKeywords(
				sql, "type_", StringPool.LIKE, true, types);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			if (priority == null) {
				sql = StringUtil.replace(sql, "(priority = ? ) AND", "");
			}

			if (status == WorkflowConstants.STATUS_ANY) {
				sql = StringUtil.replace(sql, "(status = ?) AND", "");
			}

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
			qPos.add(types, 2);

			if (priority != null) {
				qPos.add(priority);
			}

			if (status != WorkflowConstants.STATUS_ANY) {
				qPos.add(status);
			}

			qPos.add(startDate_TS);
			qPos.add(startDate_TS);
			qPos.add(endDate_TS);
			qPos.add(endDate_TS);

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

}