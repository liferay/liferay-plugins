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
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class StatusFinderImpl
	extends BasePersistenceImpl<Status> implements StatusFinder {

	public static String FIND_BY_MODIFIED_DATE =
		StatusFinder.class.getName() + ".findByModifiedDate";

	public static String FIND_BY_SOCIAL_RELATION_TYPE =
		StatusFinder.class.getName() + ".findBySocialRelationType";

	public static String FIND_BY_USERS_GROUPS =
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
			long userId, long modifiedDate, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_USERS_GROUPS);

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

}