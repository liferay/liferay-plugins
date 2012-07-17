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

package com.liferay.portal.oauth.service.persistence;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.oauth.model.OAuthApplications_Users;
import com.liferay.portal.oauth.model.impl.OAuthApplications_UsersImpl;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Igor Beslic
 *
 */
public class OAuthApplications_UsersFinderImpl
	extends BasePersistenceImpl<OAuthApplications_Users>
	implements OAuthApplications_UsersFinder {
	public static final String COUNT_ALL =
			OAuthApplications_UsersFinder.class.getName() + ".countAll";

	public static final String COUNT_BY_OWNER_AUTHORIZED =
			OAuthApplications_UsersFinder.class.getName() + ".countByO_A";

	public static final String FIND_ALL =
			OAuthApplications_UsersFinder.class.getName() + ".findAll";

	public static final String FIND_BY_OWNER_AUTHORIZED =
			OAuthApplications_UsersFinder.class.getName() + ".findByO_A";

	public int countAll() throws SystemException {
		Session session = null;

		try {
			session = openSession();

			String query = CustomSQLUtil.get(COUNT_ALL);

			SQLQuery q = session.createSQLQuery(query);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (null != count) {
					return count.intValue();
				}
			}

			return 0;
		}

		catch(Exception e) {
			throw new SystemException();
		}
		finally {
			closeSession(session);
		}
	}

	public int countByO_A(long ownerId, boolean authorized)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			String query = CustomSQLUtil.get(COUNT_BY_OWNER_AUTHORIZED);

			SQLQuery q = session.createSQLQuery(query);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(ownerId);
			qPos.add(authorized);

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (null != count) {
					return count.intValue();
				}
			}

			return 0;
		}

		catch(Exception e) {
			throw new SystemException();
		}
		finally {
			closeSession(session);
		}
	}

	public List<OAuthApplications_Users> findAll(
			int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			String query = CustomSQLUtil.get(FIND_ALL);

			query = CustomSQLUtil.replaceOrderBy(query, orderByComparator);

			SQLQuery q = session.createSQLQuery(query);

			q.addEntity(
				"OAuthApplications_Users", OAuthApplications_UsersImpl.class);

			return (List<OAuthApplications_Users>)QueryUtil.list(
					q, getDialect(), start, end);
		}

		catch(Exception e) {
			throw new SystemException();
		}
		finally {
			closeSession(session);
		}
	}

	public List<OAuthApplications_Users> findByO_A(
			long ownerId, boolean authorized, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			String query = CustomSQLUtil.get(FIND_BY_OWNER_AUTHORIZED);

			query = CustomSQLUtil.replaceOrderBy(query, orderByComparator);

			SQLQuery q = session.createSQLQuery(query);

			q.addEntity(
				"OAuthApplications_Users", OAuthApplications_UsersImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(ownerId);
			qPos.add(authorized);

			return (List<OAuthApplications_Users>)QueryUtil.list(
					q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException();
		}
		finally {
			closeSession(session);
		}
	}

}