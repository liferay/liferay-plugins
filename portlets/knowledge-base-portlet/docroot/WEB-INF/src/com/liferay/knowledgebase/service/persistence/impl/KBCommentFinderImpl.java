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

package com.liferay.knowledgebase.service.persistence.impl;

import com.liferay.knowledgebase.model.KBComment;
import com.liferay.knowledgebase.model.impl.KBCommentImpl;
import com.liferay.knowledgebase.service.persistence.KBCommentFinder;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;

/**
 * @author Adolfo Pérez
 */
public class KBCommentFinderImpl
	extends BasePersistenceImpl<KBComment> implements KBCommentFinder {

	public List<KBComment> filterFindByG_S(
		long groupId, int state, int start, int end) {

		return doFindByG_S(groupId, state, start, end, true);
	}

	public List<KBComment> findByG_S(
		long groupId, int state, int start, int end) {

		return doFindByG_S(groupId, state, start, end, false);
	}

	protected int doCountByG_S(
		long groupId, int state, boolean inlineSQLHelper) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(_COUNT_BY_G_S);

			if (inlineSQLHelper && InlineSQLHelperUtil.isEnabled()) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, KBComment.class.getName(), "KBComment.kbCommentId",
					groupId);
			}

			SQLQuery query = session.createSynchronizedSQLQuery(sql);

			query.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(query);

			qPos.add(groupId);
			qPos.add(state);

			Iterator<Long> iterator = query.iterate();

			if (iterator.hasNext()) {
				Long value = iterator.next();

				if (value != null) {
					return value.intValue();
				}
			}

			return 0;
		}
		finally {
			closeSession(session);
		}
	}

	public int countByG_S(long groupId, int state) {
		return doCountByG_S(groupId, state, false);
	}

	public int filterCountByG_S(long groupId, int state) {
		return doCountByG_S(groupId, state, true);
	}

	protected List<KBComment> doFindByG_S(
		long groupId, int state, int start, int end, boolean inlineSQLHelper) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(_FIND_BY_G_S);

			if (inlineSQLHelper && InlineSQLHelperUtil.isEnabled()) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, KBComment.class.getName(), "KBComment.kbCommentId",
					groupId);
			}

			SQLQuery query = session.createSynchronizedSQLQuery(sql);

			query.addEntity("KBComment", KBCommentImpl.class);

			QueryPos qPos = QueryPos.getInstance(query);

			qPos.add(groupId);
			qPos.add(state);

			return (List<KBComment>)QueryUtil.list(
				query, getDialect(), start, end, true);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _COUNT_BY_G_S =
		KBCommentFinder.class.getName() + ".countByG_S";

	private static final String _FIND_BY_G_S =
		KBCommentFinder.class.getName() + ".findByG_S";

}