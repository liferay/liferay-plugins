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

import com.liferay.chat.model.Entry;
import com.liferay.chat.model.EntryConstants;
import com.liferay.chat.model.impl.EntryImpl;
import com.liferay.chat.service.persistence.EntryFinder;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class EntryFinderImpl
	extends EntryFinderBaseImpl implements EntryFinder {

	public static final String FIND_BY_EMPTY_CONTENT =
		EntryFinder.class.getName() + ".findByEmptyContent";

	public static final String FIND_BY_NEW =
		EntryFinder.class.getName() + ".findByNew";

	public static final String FIND_BY_OLD =
		EntryFinder.class.getName() + ".findByOld";

	@Override
	public List<Entry> findByEmptyContent(
		long fromUserId, long toUserId, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_EMPTY_CONTENT);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("Chat_Entry", EntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(fromUserId);
			qPos.add(toUserId);

			return (List<Entry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<Entry> findByNew(
		long userId, long createDate, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_NEW);

			if (createDate > 0) {
				sql = StringUtil.replace(sql, _FLAG_SQL, _CREATE_DATE_SQL);
			}

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("Chat_Entry", EntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(userId);

			if (createDate > 0) {
				qPos.add(createDate);
			}
			else {
				qPos.add(EntryConstants.FLAG_UNREAD);
			}

			return (List<Entry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<Entry> findByOld(long createDate, int start, int end) {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_OLD);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("Chat_Entry", EntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(createDate);

			return (List<Entry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _CREATE_DATE_SQL = "(createDate > ?)";

	private static final String _FLAG_SQL = "(flag = ?)";

}