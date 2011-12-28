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

package com.liferay.socialcoding.service.persistence;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.socialcoding.model.JIRAIssue;
import com.liferay.socialcoding.model.impl.JIRAIssueImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.sql.Timestamp;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class JIRAIssueFinderImpl
	extends BasePersistenceImpl<JIRAIssue> implements JIRAIssueFinder {

	public static String COUNT_BY_CD_P =
		JIRAIssueFinder.class.getName() + ".countByCD_P";

	public static String FIND_BY_CD_P =
		JIRAIssueFinder.class.getName() + ".findByCD_P";

	public int countByCD_P(Date createDate, long projectId)
		throws SystemException {

		Timestamp createDate_TS = CalendarUtil.getTimestamp(createDate);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_CD_P);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(projectId);
			qPos.add(createDate_TS);

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

	public List<JIRAIssue> findByCD_P(Date createDate, long projectId)
		throws SystemException {

		return findByCD_P(
			createDate, projectId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<JIRAIssue> findByCD_P(
			Date createDate, long projectId, int start, int end)
		throws SystemException {

		Timestamp createDate_TS = CalendarUtil.getTimestamp(createDate);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_CD_P);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("jiraissue", JIRAIssueImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(projectId);
			qPos.add(createDate_TS);

			return (List<JIRAIssue>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}