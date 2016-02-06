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

package com.liferay.chat.service.persistence.impl;

import com.liferay.chat.service.persistence.StatusFinder;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.UserUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Tibor Lipusz
 */
public class StatusFinderImpl
	extends StatusFinderBaseImpl implements StatusFinder {

	public static final String FIND_BY_MODIFIED_DATE =
		StatusFinder.class.getName() + ".findByModifiedDate";

	public static final String FIND_BY_SOCIAL_RELATION_TYPES =
		StatusFinder.class.getName() + ".findBySocialRelationTypes";

	public static final String FIND_BY_USERS_GROUPS =
		StatusFinder.class.getName() + ".findByUsersGroups";

	@Override
	public List<Object[]> findByModifiedDate(
		long companyId, long userId, long modifiedDate, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_MODIFIED_DATE);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			addScalars(q);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(ClassNameLocalServiceUtil.getClassNameId(User.class));
			qPos.add(companyId);
			qPos.add(userId);
			qPos.add(modifiedDate);

			return toObjectArray(QueryUtil.list(q, getDialect(), start, end));
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<Object[]> findBySocialRelationTypes(
		long userId, int[] types, long modifiedDate, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = getFindBySocialRelationTypes_SQL(types);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			addScalars(q);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(ClassNameLocalServiceUtil.getClassNameId(User.class));
			qPos.add(userId);

			if (types.length > 0) {
				qPos.add(types);
			}

			qPos.add(CompanyThreadLocal.getCompanyId());
			qPos.add(userId);
			qPos.add(modifiedDate);

			return toObjectArray(QueryUtil.list(q, getDialect(), start, end));
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
		int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = getFindByUsersGroups_SQL(groupNames);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			addScalars(q);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(ClassNameLocalServiceUtil.getClassNameId(User.class));
			qPos.add(CompanyThreadLocal.getCompanyId());
			qPos.add(userId);
			qPos.add(userId);

			if (groupNames.length > 0) {
				qPos.add(groupNames);
			}

			qPos.add(modifiedDate);

			return toObjectArray(QueryUtil.list(q, getDialect(), start, end));
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected void addScalars(SQLQuery sqlQuery) {
		sqlQuery.addScalar("awake", Type.BOOLEAN);
		sqlQuery.addScalar("firstName", Type.STRING);
		sqlQuery.addScalar("groupId", Type.LONG);
		sqlQuery.addScalar("lastName", Type.STRING);
		//sqlQuery.addScalar("male", Type.BOOLEAN);
		sqlQuery.addScalar("middleName", Type.STRING);
		sqlQuery.addScalar("portraitId", Type.LONG);
		sqlQuery.addScalar("screenName", Type.STRING);
		sqlQuery.addScalar("userId", Type.LONG);
		sqlQuery.addScalar("userUuid", Type.STRING);
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

	protected List<Object[]> toObjectArray(List<?> list) throws Exception {
		List<Object[]> objectArrayList = (List<Object[]>)list;

		List<Object[]> newObjectArrayList = new ArrayList<>(
			objectArrayList.size());

		for (Object[] objectArray : objectArrayList) {
			long userId = (Long)objectArray[7];

			User user = UserUtil.findByPrimaryKey(userId);

			Object[] newObjectArray = new Object[objectArray.length + 1];

			System.arraycopy(objectArray, 0, newObjectArray, 0, 4);

			newObjectArray[4] = user.isMale();

			System.arraycopy(objectArray, 4, newObjectArray, 5, 5);

			newObjectArrayList.add(newObjectArray);
		}

		return newObjectArrayList;
	}

}