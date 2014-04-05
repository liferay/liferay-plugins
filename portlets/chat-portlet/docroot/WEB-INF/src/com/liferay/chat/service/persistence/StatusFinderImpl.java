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

package com.liferay.chat.service.persistence;

import com.liferay.chat.model.Status;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Tibor Lipusz
 */
public class StatusFinderImpl
	extends BasePersistenceImpl<Status> implements StatusFinder {

	public static final String FIND_BY_MODIFIED_DATE =
		StatusFinder.class.getName() + ".findByModifiedDate";

	public static final String FIND_BY_SOCIAL_RELATION_TYPES =
		StatusFinder.class.getName() + ".findBySocialRelationTypes";

	public static final String FIND_BY_USERS_GROUPS =
		StatusFinder.class.getName() + ".findByUsersGroups";

	@Override
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

	/**
	 * @deprecated As of 6.2.0, replaced by {@link
	 *             #findBySocialRelationTypes(long, int[], long, int, int))}
	 */
	@Override
	public List<Object[]> findBySocialRelationType(
			long userId, int type, long modifiedDate, int start, int end)
		throws SystemException {

			return findBySocialRelationTypes(
				userId, new int[] {type}, modifiedDate, start, end);
	}

	@Override
	public List<Object[]> findBySocialRelationTypes(
			long userId, int[] types, long modifiedDate, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = getFindBySocialRelationTypes_SQL(types);

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

			if (types.length > 0) {
				qPos.add(types);
			}

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

	@Override
	public List<Object[]> findByUsersGroups(
			long userId, long modifiedDate, String[] groupNames, int start,
			int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = getFindByUsersGroups_SQL(groupNames);

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

			if (groupNames.length > 0) {
				qPos.add(groupNames);
			}

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

	protected String getFindBySocialRelationTypes_SQL(int[] types) {
		String sql = CustomSQLUtil.get(FIND_BY_SOCIAL_RELATION_TYPES);

		if (types.length == 0) {
			return StringUtil.replace(
				sql, "[$SOCIAL_RELATION_TYPES$]", StringPool.BLANK);
		}

		StringBundler sb = new StringBundler(types.length * 2 - 1);

		for (int i = 0; i < types.length; i++) {
			sb.append(StringPool.QUESTION);

			if ((i + 1) < types.length) {
				sb.append(StringPool.COMMA);
			}
		}

		return StringUtil.replace(
			sql, "[$SOCIAL_RELATION_TYPES$]",
			"SocialRelation.type_ IN (" + sb.toString() + ") AND");
	}

	protected String getFindByUsersGroups_SQL(String[] groupNames) {
		String sql = CustomSQLUtil.get(FIND_BY_USERS_GROUPS);

		if (groupNames.length == 0) {
			return StringUtil.replace(
				sql,
				new String[] {
					"[$USERS_GROUPS_JOIN$]", "[$USERS_GROUPS_WHERE$]"
				},
				new String[] {StringPool.BLANK, StringPool.BLANK});
		}

		StringBundler sb = new StringBundler(groupNames.length * 2 - 1);

		for (int i = 0; i < groupNames.length; i++) {
			sb.append(StringPool.QUESTION);

			if ((i + 1) < groupNames.length) {
				sb.append(StringPool.COMMA);
			}
		}

		return StringUtil.replace(
			sql,
			new String[] {"[$USERS_GROUPS_JOIN$]", "[$USERS_GROUPS_WHERE$]"},
			new String[] {
				"INNER JOIN Group_ ON Group_.groupId = Users_Groups.groupId",
				"AND Group_.name NOT IN (" + sb.toString() + ")"
			});
	}

}