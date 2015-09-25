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

import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.impl.KBArticleImpl;
import com.liferay.knowledgebase.service.persistence.KBArticleFinder;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.math.BigInteger;

import java.util.Iterator;
import java.util.List;

/**
 * @author Adolfo Pérez
 */
public class KBArticleFinderImpl
	extends KBArticleFinderBaseImpl implements KBArticleFinder {

	@Override
	public int countByUrlTitle(
		long groupId, String kbFolderUrlTitle, String kbArticleUrlTitle,
		int[] status) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(_COUNT_BY_URL_TITLE);

			sql = replaceWorkflowStatus(sql, status);

			SQLQuery query = session.createSynchronizedSQLQuery(sql);

			QueryPos qPos = QueryPos.getInstance(query);

			qPos.add(groupId);
			qPos.add(kbArticleUrlTitle);
			qPos.add(kbFolderUrlTitle);

			Iterator<BigInteger> itr = query.iterate();

			if (itr.hasNext()) {
				BigInteger count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<KBArticle> findByUrlTitle(
		long groupId, String kbFolderUrlTitle, String kbArticleUrlTitle,
		int[] status, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(_FIND_BY_URL_TITLE);

			sql = replaceWorkflowStatus(sql, status);

			SQLQuery query = session.createSynchronizedSQLQuery(sql);

			query.addEntity(KBArticleImpl.TABLE_NAME, KBArticleImpl.class);

			QueryPos qPos = QueryPos.getInstance(query);

			qPos.add(groupId);
			qPos.add(kbArticleUrlTitle);
			qPos.add(kbFolderUrlTitle);

			return (List)QueryUtil.list(query, getDialect(), start, end);
		}
		finally {
			closeSession(session);
		}
	}

	protected String replaceWorkflowStatus(String sql, int[] status) {
		StringBundler sb = new StringBundler(status.length);

		for (int i = 0; i < status.length; i++) {
			sb.append(status[i]);

			if (i != (status.length - 1)) {
				sb.append(", ");
			}
		}

		return StringUtil.replace(sql, "[$WORKFLOW_STATUS$]", sb.toString());
	}

	private static final String _COUNT_BY_URL_TITLE =
		KBArticleFinder.class.getName() + ".countByUrlTitle";

	private static final String _FIND_BY_URL_TITLE =
		KBArticleFinder.class.getName() + ".findByUrlTitle";

}