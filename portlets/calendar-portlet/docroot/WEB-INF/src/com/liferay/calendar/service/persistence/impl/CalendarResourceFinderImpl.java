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

import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.model.impl.CalendarResourceImpl;
import com.liferay.calendar.service.persistence.CalendarResourceFinder;
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
 * @author Fabio Pezzutto
 */
public class CalendarResourceFinderImpl
	extends BasePersistenceImpl<CalendarResource>
	implements CalendarResourceFinder {

	public static final String COUNT_BY_C_G_C_C_N_D_A =
		CalendarResourceFinder.class.getName() + ".countByC_G_C_C_N_D_A";

	public static final String FIND_BY_C_G_C_C_N_D_A =
		CalendarResourceFinder.class.getName() + ".findByC_G_C_C_N_D_A";

	@Override
	public int countByKeywords(
		long companyId, long[] groupIds, long[] classNameIds, String keywords,
		boolean active) {

		String[] codes = null;
		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			codes = CustomSQLUtil.keywords(keywords);
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return countByC_G_C_C_N_D_A(
			companyId, groupIds, classNameIds, codes, names, descriptions,
			active, andOperator);
	}

	@Override
	public int countByC_G_C_C_N_D_A(
		long companyId, long[] groupIds, long[] classNameIds, String code,
		String name, String description, boolean active, boolean andOperator) {

		String[] codes = CustomSQLUtil.keywords(code);
		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description, false);

		return countByC_G_C_C_N_D_A(
			companyId, groupIds, classNameIds, codes, names, descriptions,
			active, andOperator);
	}

	@Override
	public int countByC_G_C_C_N_D_A(
		long companyId, long[] groupIds, long[] classNameIds, String[] codes,
		String[] names, String[] descriptions, boolean active,
		boolean andOperator) {

		return doCountByC_G_C_C_N_D_A(
			companyId, groupIds, classNameIds, codes, names, descriptions,
			active, andOperator, false);
	}

	@Override
	public int filterCountByKeywords(
		long companyId, long[] groupIds, long[] classNameIds, String keywords,
		boolean active) {

		String[] codes = null;
		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			codes = CustomSQLUtil.keywords(keywords);
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return filterCountByC_G_C_C_N_D_A(
			companyId, groupIds, classNameIds, codes, names, descriptions,
			active, andOperator);
	}

	@Override
	public int filterCountByC_G_C_C_N_D_A(
		long companyId, long[] groupIds, long[] classNameIds, String code,
		String name, String description, boolean active, boolean andOperator) {

		String[] codes = CustomSQLUtil.keywords(code);
		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description, false);

		return filterCountByC_G_C_C_N_D_A(
			companyId, groupIds, classNameIds, codes, names, descriptions,
			active, andOperator);
	}

	@Override
	public int filterCountByC_G_C_C_N_D_A(
		long companyId, long[] groupIds, long[] classNameIds, String[] codes,
		String[] names, String[] descriptions, boolean active,
		boolean andOperator) {

		return doCountByC_G_C_C_N_D_A(
			companyId, groupIds, classNameIds, codes, names, descriptions,
			active, andOperator, true);
	}

	@Override
	public List<CalendarResource> filterFindByKeywords(
		long companyId, long[] groupIds, long[] classNameIds, String keywords,
		boolean active, int start, int end,
		OrderByComparator<CalendarResource> orderByComparator) {

		String[] codes = null;
		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			codes = CustomSQLUtil.keywords(keywords);
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return filterFindByC_G_C_C_N_D_A(
			companyId, groupIds, classNameIds, codes, names, descriptions,
			active, andOperator, start, end, orderByComparator);
	}

	@Override
	public List<CalendarResource> filterFindByC_G_C_C_N_D_A(
		long companyId, long[] groupIds, long[] classNameIds, String code,
		String name, String description, boolean active, boolean andOperator,
		int start, int end,
		OrderByComparator<CalendarResource> orderByComparator) {

		String[] codes = CustomSQLUtil.keywords(code);
		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description, false);

		return filterFindByC_G_C_C_N_D_A(
			companyId, groupIds, classNameIds, codes, names, descriptions,
			active, andOperator, start, end, orderByComparator);
	}

	@Override
	public List<CalendarResource> filterFindByC_G_C_C_N_D_A(
		long companyId, long[] groupIds, long[] classNameIds, String[] codes,
		String[] names, String[] descriptions, boolean active,
		boolean andOperator, int start, int end,
		OrderByComparator<CalendarResource> orderByComparator) {

		return doFindByC_G_C_C_N_D_A(
			companyId, groupIds, classNameIds, codes, names, descriptions,
			active, andOperator, start, end, orderByComparator, true);
	}

	@Override
	public List<CalendarResource> findByKeywords(
		long companyId, long[] groupIds, long[] classNameIds, String keywords,
		boolean active, int start, int end,
		OrderByComparator<CalendarResource> orderByComparator) {

		String[] codes = null;
		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			codes = CustomSQLUtil.keywords(keywords);
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords, false);
		}
		else {
			andOperator = true;
		}

		return findByC_G_C_C_N_D_A(
			companyId, groupIds, classNameIds, codes, names, descriptions,
			active, andOperator, start, end, orderByComparator);
	}

	@Override
	public List<CalendarResource> findByC_G_C_C_N_D_A(
		long companyId, long[] groupIds, long[] classNameIds, String code,
		String name, String description, boolean active, boolean andOperator,
		int start, int end,
		OrderByComparator<CalendarResource> orderByComparator) {

		String[] codes = CustomSQLUtil.keywords(code);
		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description, false);

		return findByC_G_C_C_N_D_A(
			companyId, groupIds, classNameIds, codes, names, descriptions,
			active, andOperator, start, end, orderByComparator);
	}

	@Override
	public List<CalendarResource> findByC_G_C_C_N_D_A(
		long companyId, long[] groupIds, long[] classNameIds, String[] codes,
		String[] names, String[] descriptions, boolean active,
		boolean andOperator, int start, int end,
		OrderByComparator<CalendarResource> orderByComparator) {

		return doFindByC_G_C_C_N_D_A(
			companyId, groupIds, classNameIds, codes, names, descriptions,
			active, andOperator, start, end, orderByComparator, false);
	}

	protected int doCountByC_G_C_C_N_D_A(
		long companyId, long[] groupIds, long[] classNameIds, String[] codes,
		String[] names, String[] descriptions, boolean active,
		boolean andOperator, boolean inlineSQLHelper) {

		codes = CustomSQLUtil.keywords(codes);
		names = CustomSQLUtil.keywords(names);
		descriptions = CustomSQLUtil.keywords(descriptions, false);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_C_G_C_C_N_D_A);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, CalendarResource.class.getName(),
					"CalendarResource.calendarResourceId", groupIds);
			}

			sql = StringUtil.replace(
				sql, "[$GROUP_ID$]", getGroupIds(groupIds));
			sql = StringUtil.replace(
				sql, "[$CLASS_NAME_ID$]", getClassNameIds(classNameIds));
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(code_)", StringPool.LIKE, false, codes);
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

			if (ArrayUtil.isNotEmpty(classNameIds)) {
				qPos.add(classNameIds);
			}

			qPos.add(codes, 2);
			qPos.add(names, 2);
			qPos.add(descriptions, 2);
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

	protected List<CalendarResource> doFindByC_G_C_C_N_D_A(
		long companyId, long[] groupIds, long[] classNameIds, String[] codes,
		String[] names, String[] descriptions, boolean active,
		boolean andOperator, int start, int end,
		OrderByComparator<CalendarResource> orderByComparator,
		boolean inlineSQLHelper) {

		codes = CustomSQLUtil.keywords(codes);
		names = CustomSQLUtil.keywords(names);
		descriptions = CustomSQLUtil.keywords(descriptions, false);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_C_G_C_C_N_D_A);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, CalendarResource.class.getName(),
					"CalendarResource.calendarResourceId", groupIds);
			}

			sql = StringUtil.replace(
				sql, "[$GROUP_ID$]", getGroupIds(groupIds));
			sql = StringUtil.replace(
				sql, "[$CLASS_NAME_ID$]", getClassNameIds(classNameIds));
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(code_)", StringPool.LIKE, false, codes);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(name)", StringPool.LIKE, false, names);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "description", StringPool.LIKE, true, descriptions);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			StringBundler sb = new StringBundler();

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, "CalendarResource.", orderByComparator);
			}

			sql = StringUtil.replace(sql, "[$ORDER_BY$]", sb.toString());

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("CalendarResource", CalendarResourceImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(groupIds);

			if (ArrayUtil.isNotEmpty(classNameIds)) {
				qPos.add(classNameIds);
			}

			qPos.add(codes, 2);
			qPos.add(names, 2);
			qPos.add(descriptions, 2);
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

	protected String getClassNameIds(long[] classNameIds) {
		if (ArrayUtil.isEmpty(classNameIds)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(classNameIds.length * 2);

		sb.append("(");

		for (int i = 0; i < classNameIds.length; i++) {
			sb.append("classNameId = ?");

			if ((i + 1) < classNameIds.length) {
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

}