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

package com.liferay.socialcoding.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.socialcoding.model.JIRAIssue;
import com.liferay.socialcoding.model.impl.JIRAIssueImpl;
import com.liferay.socialcoding.service.persistence.JIRAIssueFinder;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.sql.Timestamp;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class JIRAIssueFinderImpl
	extends JIRAIssueFinderBaseImpl implements JIRAIssueFinder {

	public static final String COUNT_BY_CD_P =
		JIRAIssueFinder.class.getName() + ".countByCD_P";

	public static final String FIND_BY_CD_P =
		JIRAIssueFinder.class.getName() + ".findByCD_P";

	public static final String FIND_BY_KEY =
		JIRAIssueFinder.class.getName() + ".findByKey";

	public int countByCD_P(Date createDate, long projectId) {
		Timestamp createDate_TS = CalendarUtil.getTimestamp(createDate);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_CD_P);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

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

	public JIRAIssue findByKey(String jiraIssueKey) {
		String[] jiraIssueKeyArray = StringUtil.split(
			jiraIssueKey, StringPool.DASH);

		if (jiraIssueKeyArray.length != 2) {
			return null;
		}

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_KEY);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("jiraissue", JIRAIssueImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(jiraIssueKeyArray[1]);
			qPos.add(jiraIssueKeyArray[0]);

			List<JIRAIssue> list = q.list();

			if (list.isEmpty()) {
				return null;
			}

			return list.get(0);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<JIRAIssue> findByCD_P(Date createDate, long projectId) {
		return findByCD_P(
			createDate, projectId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<JIRAIssue> findByCD_P(
		Date createDate, long projectId, int start, int end) {

		Timestamp createDate_TS = CalendarUtil.getTimestamp(createDate);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_CD_P);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

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