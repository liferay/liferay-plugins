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

import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.model.impl.CalendarResourceImpl;
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

/**
 * @author Eduardo Lundgren
 */
public class CalendarResourceFinderImpl
	extends BasePersistenceImpl<CalendarResource>
	implements CalendarResourceFinder {

	public static final String COUNT_BY_C_G_C_C_N_D_T_A =
		CalendarResourceFinder.class.getName() + ".countByC_G_C_C_N_D_T_A";

	public static final String FIND_BY_C_G_C_C_N_D_T_A =
		CalendarResourceFinder.class.getName() + ".findByC_G_C_C_N_D_T_A";

	public int countByKeywords(
			long companyId, long[] groupIds, long[] classNameIds,
			String keywords, boolean active)
		throws SystemException {

		String[] codes = null;
		String[] names = null;
		String[] descriptions = null;
		String[] types = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			codes = CustomSQLUtil.keywords(keywords);
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
			types = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return countByC_G_C_C_N_D_T_A(
			companyId, groupIds, classNameIds, codes, names, descriptions,
			types, active, andOperator);
	}

	public int countByC_G_C_C_N_D_T_A(
			long companyId, long[] groupIds, long[] classNameIds, String code,
			String name, String description, String type, boolean active,
			boolean andOperator)
		throws SystemException {

		String[] codes = CustomSQLUtil.keywords(code);
		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description, false);
		String[] types = CustomSQLUtil.keywords(type, false);

		return countByC_G_C_C_N_D_T_A(
			companyId, groupIds, classNameIds, codes, names, descriptions,
			types, active, andOperator);
	}

	public int countByC_G_C_C_N_D_T_A(
			long companyId, long[] groupIds, long[] classNameIds,
			String[] codes, String[] names, String[] descriptions,
			String[] types, boolean active, boolean andOperator)
		throws SystemException {

		return doCountByC_G_C_C_N_D_T_A(
			companyId, groupIds, classNameIds, codes, names, descriptions,
			types, active, andOperator, false);
	}

	public int filterCountByKeywords(
			long companyId, long[] groupIds, long[] classNameIds,
			String keywords, boolean active)
		throws SystemException {

		String[] codes = null;
		String[] names = null;
		String[] descriptions = null;
		String[] types = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			codes = CustomSQLUtil.keywords(keywords);
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
			types = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return filterCountByC_G_C_C_N_D_T_A(
			companyId, groupIds, classNameIds, codes, names, descriptions,
			types, active, andOperator);
	}

	public int filterCountByC_G_C_C_N_D_T_A(
			long companyId, long[] groupIds, long[] classNameIds, String code,
			String name, String description, String type, boolean active,
			boolean andOperator)
		throws SystemException {

		String[] codes = CustomSQLUtil.keywords(code);
		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description, false);
		String[] types = CustomSQLUtil.keywords(type, false);

		return filterCountByC_G_C_C_N_D_T_A(
			companyId, groupIds, classNameIds, codes, names, descriptions,
			types, active, andOperator);
	}

	public int filterCountByC_G_C_C_N_D_T_A(
			long companyId, long[] groupIds, long[] classNameIds,
			String[] codes, String[] names, String[] descriptions,
			String[] types, boolean active, boolean andOperator)
		throws SystemException {

		return doCountByC_G_C_C_N_D_T_A(
			companyId, groupIds, classNameIds, codes, names, descriptions,
			types, active, andOperator, true);
	}

	public List<CalendarResource> filterFindByKeywords(
			long companyId, long[] groupIds, long[] classNameIds,
			String keywords, boolean active, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		String[] codes = null;
		String[] names = null;
		String[] descriptions = null;
		String[] types = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			codes = CustomSQLUtil.keywords(keywords);
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
			types = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return filterFindByC_G_C_C_N_D_T_A(
			companyId, groupIds, classNameIds, codes, names, descriptions,
			types, active, andOperator, start, end, orderByComparator);
	}

	public List<CalendarResource> filterFindByC_G_C_C_N_D_T_A(
			long companyId, long[] groupIds, long[] classNameIds, String code,
			String name, String description, String type, boolean active,
			boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		String[] codes = CustomSQLUtil.keywords(code);
		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description, false);
		String[] types = CustomSQLUtil.keywords(type, false);

		return filterFindByC_G_C_C_N_D_T_A(
			companyId, groupIds, classNameIds, codes, names, descriptions,
			types, active, andOperator, start, end, orderByComparator);
	}

	public List<CalendarResource> filterFindByC_G_C_C_N_D_T_A(
			long companyId, long[] groupIds, long[] classNameIds,
			String[] codes, String[] names, String[] descriptions,
			String[] types, boolean active, boolean andOperator, int start,
			int end, OrderByComparator orderByComparator)
		throws SystemException {

		return doFindByC_G_C_C_N_D_T_A(
			companyId, groupIds, classNameIds, codes, names, descriptions,
			types, active, andOperator, start, end, orderByComparator, true);
	}

	public List<CalendarResource> findByKeywords(
			long companyId, long[] groupIds, long[] classNameIds,
			String keywords, boolean active, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		String[] codes = null;
		String[] names = null;
		String[] descriptions = null;
		String[] types = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			codes = CustomSQLUtil.keywords(keywords);
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
			types = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return findByC_G_C_C_N_D_T_A(
			companyId, groupIds, classNameIds, codes, names, descriptions,
			types, active, andOperator, start, end, orderByComparator);
	}

	public List<CalendarResource> findByC_G_C_C_N_D_T_A(
			long companyId, long[] groupIds, long[] classNameIds, String code,
			String name, String description, String type, boolean active,
			boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		String[] codes = CustomSQLUtil.keywords(code);
		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description, false);
		String[] types = CustomSQLUtil.keywords(type, false);

		return findByC_G_C_C_N_D_T_A(
			companyId, groupIds, classNameIds, codes, names, descriptions,
			types, active, andOperator, start, end, orderByComparator);
	}

	public List<CalendarResource> findByC_G_C_C_N_D_T_A(
			long companyId, long[] groupIds, long[] classNameIds,
			String[] codes, String[] names, String[] descriptions,
			String[] types, boolean active, boolean andOperator, int start,
			int end, OrderByComparator orderByComparator)
		throws SystemException {

		return doFindByC_G_C_C_N_D_T_A(
			companyId, groupIds, classNameIds, codes, names, descriptions,
			types, active, andOperator, start, end, orderByComparator, false);
	}

	protected int doCountByC_G_C_C_N_D_T_A(
			long companyId, long[] groupIds, long[] classNameIds,
			String[] codes, String[] names, String[] descriptions,
			String[] types, boolean active, boolean andOperator,
			boolean inlineSQLHelper)
		throws SystemException {

		String[] classNameIdsString = null;

		if (classNameIds == null) {
			classNameIdsString = new String[] {null};
		}
		else {
			classNameIdsString = ArrayUtil.toStringArray(classNameIds);
		}

		codes = CustomSQLUtil.keywords(codes);
		names = CustomSQLUtil.keywords(names);
		descriptions = CustomSQLUtil.keywords(descriptions, false);
		types = CustomSQLUtil.keywords(types, false);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_C_G_C_C_N_D_T_A);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, CalendarResource.class.getName(),
					"CalendarResource.calendarResourceId", groupIds);
			}

			sql = StringUtil.replace(
				sql, "[$GROUP_ID$]", getGroupIds(groupIds));
			sql = CustomSQLUtil.replaceKeywords(
				sql, "classNameId", StringPool.EQUAL, false,
				classNameIdsString);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(code_)", StringPool.LIKE, false, codes);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(name)", StringPool.LIKE, false, names);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "description", StringPool.LIKE, false, descriptions);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "type_", StringPool.LIKE, true, types);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(groupIds);
			qPos.add(classNameIds, 2);
			qPos.add(codes, 2);
			qPos.add(names, 2);
			qPos.add(descriptions, 2);
			qPos.add(types, 2);
			qPos.add(active);

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

	protected List<CalendarResource> doFindByC_G_C_C_N_D_T_A(
			long companyId, long[] groupIds, long[] classNameIds,
			String[] codes, String[] names, String[] descriptions,
			String[] types, boolean active, boolean andOperator, int start,
			int end, OrderByComparator orderByComparator,
			boolean inlineSQLHelper)
		throws SystemException {

		String[] classNameIdsString = null;

		if (classNameIds == null) {
			classNameIdsString = new String[] {null};
		}
		else {
			classNameIdsString = ArrayUtil.toStringArray(classNameIds);
		}

		codes = CustomSQLUtil.keywords(codes);
		names = CustomSQLUtil.keywords(names);
		descriptions = CustomSQLUtil.keywords(descriptions, false);
		types = CustomSQLUtil.keywords(types, false);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_C_G_C_C_N_D_T_A);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, CalendarResource.class.getName(),
					"CalendarResource.calendarResourceId", groupIds);
			}

			sql = StringUtil.replace(
				sql, "[$GROUP_ID$]", getGroupIds(groupIds));
			sql = CustomSQLUtil.replaceKeywords(
				sql, "classNameId", StringPool.EQUAL, false,
				classNameIdsString);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(code_)", StringPool.LIKE, false, codes);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(name)", StringPool.LIKE, false, names);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "description", StringPool.LIKE, false, descriptions);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "type_", StringPool.LIKE, true, types);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			StringBundler sb = new StringBundler();

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, "CalendarResource.", orderByComparator);
			}

			sql = StringUtil.replace(sql, "[$ORDER_BY$]", sb.toString());

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("CalendarResource", CalendarResourceImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(groupIds);
			qPos.add(classNameIds, 2);
			qPos.add(codes, 2);
			qPos.add(names, 2);
			qPos.add(descriptions, 2);
			qPos.add(types, 2);
			qPos.add(active);

			return (List<CalendarResource>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
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