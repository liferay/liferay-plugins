/*
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.bi.report.service.persistence;

import com.liferay.bi.report.model.ReportDefinition;
import com.liferay.bi.report.model.impl.ReportDefinitionImpl;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <a href="ReportDefinitionFinderImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class ReportDefinitionFinderImpl
	extends BasePersistenceImpl implements ReportDefinitionFinder {

	public static String COUNT_BY_C_G_D_D =
		ReportDefinitionFinder.class.getName() +
		".countByC_G_D_D";

	public static String FIND_BY_C_G_D_D =
		ReportDefinitionFinder.class.getName() +
		".findByC_G_D_D";

	public int countByC_G_N_D(
		long companyId,
		long groupId, String[] definitionNames, String[] descriptions,
		boolean andOperator) throws SystemException {
		definitionNames = CustomSQLUtil.keywords(definitionNames);
		descriptions = CustomSQLUtil.keywords(descriptions);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_C_G_D_D);

			if (groupId <= 0) {
				sql = StringUtil.replace(sql, "(groupId = ?) AND", "");
			}

			sql = CustomSQLUtil.replaceKeywords(
				sql, "definitionName", StringPool.LIKE, false, definitionNames);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "description", StringPool.LIKE, false, descriptions);

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (groupId > 0) {
				qPos.add(groupId);
			}

			qPos.add(definitionNames, 2);
			qPos.add(descriptions, 2);

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

	public int countByKeywords(
		long companyId, long groupId, java.lang.String keywords,
		LinkedHashMap<String, Object> params)
		throws SystemException {
		String[] definitionNames = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			definitionNames = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return countByC_G_N_D(
			companyId, groupId, definitionNames,
			descriptions, andOperator);
	}

	public List<ReportDefinition> findByC_G_N_D(
		long companyId, long groupId, String[] definitionNames,
		String[] descriptions, boolean andOperator, int start,
		int end, OrderByComparator obc)
		throws SystemException {

		definitionNames = CustomSQLUtil.keywords(definitionNames);
		descriptions = CustomSQLUtil.keywords(descriptions);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_C_G_D_D);

			if (groupId <= 0) {
				sql = StringUtil.replace(sql, "(groupId = ?) AND", "");
			}

			sql = CustomSQLUtil.replaceKeywords(
				sql, "definitionName", StringPool.LIKE, false, definitionNames);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "description", StringPool.LIKE, false, descriptions);

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);
			sql = CustomSQLUtil.replaceOrderBy(sql, obc);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("Report_ReportDefinition", ReportDefinitionImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (groupId > 0) {
				qPos.add(groupId);
			}

			qPos.add(definitionNames, 2);
			qPos.add(descriptions, 2);

			return (List<ReportDefinition>) QueryUtil.list(
				q, getDialect(), start, end);

		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<ReportDefinition> findByKeywords(
		long companyId, long groupId, String keywords,
		LinkedHashMap<String, Object> params, int start, int end,
		OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		
		String[] definitionNames = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			definitionNames = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return findByC_G_N_D(
			companyId, groupId, definitionNames,
			descriptions, andOperator, start, end, obc);
	}

}
