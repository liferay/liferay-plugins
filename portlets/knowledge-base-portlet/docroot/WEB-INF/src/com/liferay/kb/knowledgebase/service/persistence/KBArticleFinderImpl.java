/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.kb.knowledgebase.service.persistence;

import com.liferay.kb.knowledgebase.model.KBArticle;
import com.liferay.kb.knowledgebase.model.impl.KBArticleImpl;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;

/**
 * <a href="KBArticleQueryUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Peter Shin
 *
 */
public class KBArticleFinderImpl
	extends BasePersistenceImpl implements KBArticleFinder {

	public static String COUNT_BY_GROUP_IDS =
		KBArticleFinder.class.getName() + ".countByGroupIds";

	public static String COUNT_BY_S_U_G =
		KBArticleFinder.class.getName() + ".countByS_U_G";

	public static String COUNT_BY_G_P_T_OR_G_P_T_U =
		KBArticleFinder.class.getName() + ".countByG_P_T_Or_G_P_T_U";

	public static String FIND_BY_GROUP_IDS =
		KBArticleFinder.class.getName() + ".findByGroupIds";

	public static String FIND_BY_S_U_G =
		KBArticleFinder.class.getName() + ".findByS_U_G";

	public static String FIND_BY_G_P_T_OR_G_P_T_U =
		KBArticleFinder.class.getName() + ".findByG_P_T_Or_G_P_T_U";

	public int countByGroupIds(long[] groupIds) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_GROUP_IDS);

			sql = StringUtil.replace(
				sql, "[$GROUP_ID$]", getGroupIds(groupIds));

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			for (int i = 0; i < groupIds.length; i++) {
				Long groupId = groupIds[i];

				qPos.add(groupId);
			}

			Iterator<Long> itr = q.list().iterator();

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

	public int countByS_U_G(long userId, long groupId)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_S_U_G);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(PortalUtil.getClassNameId(KBArticle.class.getName()));
			qPos.add(groupId);
			qPos.add(userId);

			Iterator<Long> itr = q.list().iterator();

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

	public int countByG_P_T_Or_G_P_T_U(
			long groupId, boolean template, long userId)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_G_P_T_OR_G_P_T_U);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(template);
			qPos.add(userId);

			Iterator<Long> itr = q.list().iterator();

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

	public List<KBArticle> findByGroupIds(
			long[] groupIds, int start, int end, OrderByComparator obc)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_GROUP_IDS);

			sql = StringUtil.replace(
				sql, "[$GROUP_ID$]", getGroupIds(groupIds));
			sql = CustomSQLUtil.replaceOrderBy(sql, obc);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("KB_KBArticle", KBArticleImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			for (int i = 0; i < groupIds.length; i++) {
				Long groupId = groupIds[i];

				qPos.add(groupId);
			}

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<KBArticle> findByS_U_G(
			long userId, long groupId, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_S_U_G);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("KB_KBArticle", KBArticleImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(PortalUtil.getClassNameId(KBArticle.class.getName()));
			qPos.add(groupId);
			qPos.add(userId);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<KBArticle> findByG_P_T_Or_G_P_T_U(
			long groupId, long parentResourcePrimKey, boolean template,
			long userId, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_G_P_T_OR_G_P_T_U);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("KB_KBArticle", KBArticleImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(parentResourcePrimKey);
			qPos.add(template);
			qPos.add(userId);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected String getGroupIds(long[] groupIds) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < groupIds.length; i++) {
			sb.append("groupId = ? ");

			if ((i + 1) != groupIds.length) {
				sb.append("OR ");
			}
		}

		return sb.toString();
	}

}