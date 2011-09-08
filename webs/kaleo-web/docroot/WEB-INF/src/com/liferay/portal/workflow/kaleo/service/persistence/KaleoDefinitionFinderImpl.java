/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.service.persistence;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;

/**
 * @author Marcellus Tavares
 */
public class KaleoDefinitionFinderImpl
	extends BasePersistenceImpl<KaleoDefinition>
	implements KaleoDefinitionFinder {

	public static String COUNT_BY_C_N_A_S =
		KaleoDefinitionFinder.class.getName() + ".countByC_N_A_S";

	public static String FIND_BY_C_N_A_S =
		KaleoDefinitionFinder.class.getName() + ".findByC_N_A_S";

	public int countKaleoDefinitions(KaleoDefinitionQuery kaleoDefinitionQuery)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = buildKaleoDefinitionQuerySQL(
				kaleoDefinitionQuery, true, session);

			Iterator<Long> itr = q.list().iterator();

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

	public List<KaleoDefinition> findKaleoDefinitions(
			KaleoDefinitionQuery kaleoDefinitionQuery)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = buildKaleoDefinitionQuerySQL(
				kaleoDefinitionQuery, false, session);

			List<KaleoDefinition> kaleoDefinitions =
				(List<KaleoDefinition>)QueryUtil.list(
					q, getDialect(), kaleoDefinitionQuery.getStart(),
					kaleoDefinitionQuery.getEnd());

			return kaleoDefinitions;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SQLQuery buildKaleoDefinitionQuerySQL(
			KaleoDefinitionQuery kaleoDefinitionQuery, boolean count,
			Session session)
		throws Exception {

		String sql = null;

		if (count) {
			sql = CustomSQLUtil.get(COUNT_BY_C_N_A_S);
		}
		else {
			sql = CustomSQLUtil.get(FIND_BY_C_N_A_S);
		}

		sql = CustomSQLUtil.appendCriteria(
			sql, getActive(kaleoDefinitionQuery));
		sql = CustomSQLUtil.appendCriteria(
			sql, getName(kaleoDefinitionQuery));
		sql = CustomSQLUtil.appendCriteria(
			sql, getScope(kaleoDefinitionQuery));

		if (kaleoDefinitionQuery.getOrderByComparator() != null) {
			StringBundler sb = new StringBundler(sql);

			appendOrderByComparator(
				sb, _ORDER_BY_ENTITY_ALIAS,
				kaleoDefinitionQuery.getOrderByComparator());

			sql = sb.toString();
		}

		SQLQuery q = session.createSQLQuery(sql);

		if (count) {
			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);
		}
		else {
			q.addEntity("KaleoDefinition", KaleoDefinitionImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionQuery.getCompanyId());

		setActive(qPos, kaleoDefinitionQuery);
		setName(qPos, kaleoDefinitionQuery);
		setScope(qPos, kaleoDefinitionQuery);

		return q;
	}

	protected String getActive(KaleoDefinitionQuery kaleoDefinitionQuery) {
		Boolean active = kaleoDefinitionQuery.isActive();

		if (active == null) {
			return StringPool.BLANK;
		}

		return "AND (KaleoDefinition.active_ = ?)";
	}

	protected String getName(KaleoDefinitionQuery kaleoDefinitionQuery) {
		String name = kaleoDefinitionQuery.getName();

		if (Validator.isNull(name)) {
			return StringPool.BLANK;
		}

		return "AND (KaleoDefinition.name LIKE ?)";
	}

	protected String getScope(KaleoDefinitionQuery kaleoDefinitionQuery) {
		Long scope = kaleoDefinitionQuery.getScope();

		if (scope == null) {
			return StringPool.BLANK;
		}

		return "AND (KaleoDefinition.scope = ?)";
	}

	protected void setActive(
		QueryPos qPos, KaleoDefinitionQuery kaleoDefinitionQuery) {

		Boolean active = kaleoDefinitionQuery.isActive();

		if (active == null) {
			return;
		}

		qPos.add(active);
	}

	protected void setName(
		QueryPos qPos, KaleoDefinitionQuery kaleoDefinitionQuery) {

		String name = kaleoDefinitionQuery.getName();

		if (Validator.isNull(name)) {
			return;
		}

		qPos.add(name);
	}

	protected void setScope(
		QueryPos qPos, KaleoDefinitionQuery kaleoDefinitionQuery) {

		Long scope = kaleoDefinitionQuery.getScope();

		if (scope == null) {
			return;
		}

		qPos.add(scope);
	}

	private static final String _ORDER_BY_ENTITY_ALIAS = "KaleoDefinition.";

}