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

package com.liferay.chat.service.persistence;

import com.liferay.chat.model.Status;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class StatusFinderImpl
	extends BasePersistenceImpl<Status> implements StatusFinder {

	public static final String FIND_BY_MODIFIED_DATE =
		StatusFinder.class.getName() + ".findByModifiedDate";

	public static final String FIND_BY_SOCIAL_RELATION_TYPE =
		StatusFinder.class.getName() + ".findBySocialRelationType";

	public static final String FIND_BY_USERS_GROUPS =
		StatusFinder.class.getName() + ".findByUsersGroups";

	public List<Object[]> findByModifiedDate(
			long companyId, long userId, long modifiedDate, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_MODIFIED_DATE);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar("userId", Type.LONG);
			q.addScalar("screenName", Type.STRING);
			q.addScalar("firstName", Type.STRING);
			q.addScalar("middleName", Type.STRING);
			q.addScalar("lastName", Type.STRING);
			q.addScalar("portraitId", Type.LONG);
			q.addScalar("awake", Type.BOOLEAN);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(userId);
			qPos.add(modifiedDate);

			return (List<Object[]>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Object[]> findBySocialRelationType(
			long userId, int type, long modifiedDate, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_SOCIAL_RELATION_TYPE);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar("userId", Type.LONG);
			q.addScalar("screenName", Type.STRING);
			q.addScalar("firstName", Type.STRING);
			q.addScalar("middleName", Type.STRING);
			q.addScalar("lastName", Type.STRING);
			q.addScalar("portraitId", Type.LONG);
			q.addScalar("awake", Type.BOOLEAN);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(type);
			qPos.add(modifiedDate);
			qPos.add(userId);

			return (List<Object[]>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Object[]> findByUsersGroups(
			long userId, long modifiedDate, String[] siteNames, int start,
			int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_USERS_GROUPS);

			String replacement = null;

			if (siteNames.length > 0) {
				replacement = _buildSiteFilterQuery(siteNames);
			}
			else {
				replacement = StringPool.BLANK;
			}

			sql = StringUtil.replace(sql, "[$SITES_FILTER$]", replacement);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar("userId", Type.LONG);
			q.addScalar("screenName", Type.STRING);
			q.addScalar("firstName", Type.STRING);
			q.addScalar("middleName", Type.STRING);
			q.addScalar("lastName", Type.STRING);
			q.addScalar("portraitId", Type.LONG);
			q.addScalar("awake", Type.BOOLEAN);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			_setSiteNames(qPos, siteNames);

			qPos.add(modifiedDate);
			qPos.add(userId);

			return (List<Object[]>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private String _buildSiteFilterQuery(String[] siteNames) {
		StringBundler sb = new StringBundler(siteNames.length * 2 - 1);

		sb.append("(User_.userId NOT IN (");
		sb.append("SELECT userId FROM Users_Groups INNER JOIN Group_ ON ");
		sb.append("(Users_Groups.groupId = Group_.groupId) WHERE Group_.name");
		sb.append(" IN (");

		for (int i = 0; i < siteNames.length; i++) {
			sb.append(StringPool.QUESTION);

			if ((i + 1) < siteNames.length) {
				sb.append(StringPool.COMMA);
			}
		}

		sb.append(" ))) AND");

		return sb.toString();
	}

	private void _setSiteNames(QueryPos qPos, String[] names) {
		if ((names != null) && (names.length > 0)) {
			for (String name : names) {
				qPos.add(name);
			}
		}
	}

}