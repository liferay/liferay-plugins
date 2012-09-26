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
import com.liferay.portal.oauth.model.ApplicationUser;
import com.liferay.portal.oauth.model.impl.ApplicationUserImpl;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Igor Beslic
 *
 */
public class ApplicationUserFinderImpl
	extends BasePersistenceImpl<ApplicationUser>
	implements ApplicationUserFinder
	{
	public static final String COUNT_ALL =
			ApplicationUserFinder.class.getName() + ".countAll";

	public static final String COUNT_BY_OWNER_AUTHORIZED =
			ApplicationUserFinder.class.getName() + ".countByO";

	public static final String FIND_ALL =
			ApplicationUserFinder.class.getName() + ".findAll";

	public static final String FIND_BY_OWNER_AUTHORIZED =
			ApplicationUserFinder.class.getName() + ".findByO";

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

	public int countByO(long ownerId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			String query = CustomSQLUtil.get(COUNT_BY_OWNER_AUTHORIZED);

			SQLQuery q = session.createSQLQuery(query);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(ownerId);

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

	public List<ApplicationUser> findAll(
			int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			String query = CustomSQLUtil.get(FIND_ALL);

			query = CustomSQLUtil.replaceOrderBy(query, orderByComparator);

			SQLQuery q = session.createSQLQuery(query);

			q.addEntity(
				"ApplicationUser", ApplicationUserImpl.class);

			return (List<ApplicationUser>)QueryUtil.list(
					q, getDialect(), start, end);
		}

		catch(Exception e) {
			throw new SystemException();
		}
		finally {
			closeSession(session);
		}
	}

	public List<ApplicationUser> findByO(
		long ownerId, int start, int end,
		OrderByComparator orderByComparator)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			String query = CustomSQLUtil.get(FIND_BY_OWNER_AUTHORIZED);

			query = CustomSQLUtil.replaceOrderBy(query, orderByComparator);

			SQLQuery q = session.createSQLQuery(query);

			q.addEntity(
				"ApplicationUser", ApplicationUserImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(ownerId);

			return (List<ApplicationUser>)QueryUtil.list(
					q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}