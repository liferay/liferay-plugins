/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.so.service.persistence;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.so.model.FavoriteSite;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;

/**
 * @author Jonathan Lee
 */
public class FavoriteSiteFinderImpl
	extends BasePersistenceImpl<FavoriteSite>
	implements FavoriteSiteFinder {

	public static final String COUNT_BY_U_N =
		FavoriteSiteFinder.class.getName() + ".countByU_N";

	public static final String FIND_BY_U_N =
		FavoriteSiteFinder.class.getName() + ".findByU_N";

	public FavoriteSiteFinderImpl() {
		try {
			MethodKey methodKey = new MethodKey(
				"com.liferay.util.dao.orm.CustomSQL", "get", String.class);

			_joinByUsersGroupsSQL = (String)PortalClassInvoker.invoke(
				true, methodKey,
				"com.liferay.portal.service.persistence." +
					"GroupFinder.joinByUsersGroups");
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public int countByU_N(long userId, String name, String realName)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_U_N);

			sql = StringUtil.replace(
				sql, "[$JOIN_BY_USERS_GROUP$]", _joinByUsersGroupsSQL);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(StringPool.PERCENT + name + StringPool.PERCENT);
			qPos.add(realName);
			qPos.add(name);
			qPos.add(userId);
			qPos.add(StringPool.PERCENT + name + StringPool.PERCENT);
			qPos.add(realName);
			qPos.add(name);

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

	public List<Object[]> findByU_N(
			long userId, String name, String realName, int start, int end)
		throws SystemException {

		name = StringUtil.lowerCase(name);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_U_N);

			sql = StringUtil.replace(
				sql, "[$JOIN_BY_USERS_GROUP$]", _joinByUsersGroupsSQL);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar("userId", Type.LONG);
			q.addScalar("groupId", Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(StringPool.PERCENT + name + StringPool.PERCENT);
			qPos.add(realName);
			qPos.add(name);
			qPos.add(userId);
			qPos.add(StringPool.PERCENT + name + StringPool.PERCENT);
			qPos.add(realName);
			qPos.add(name);

			return (List<Object[]>)QueryUtil.list(q, getDialect(), start, end);

		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		FavoriteSiteFinderImpl.class);

	private String _joinByUsersGroupsSQL;

}