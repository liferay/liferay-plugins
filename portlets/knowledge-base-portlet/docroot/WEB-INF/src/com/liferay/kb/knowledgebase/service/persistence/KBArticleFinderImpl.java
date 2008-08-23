/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

	public static String COUNT_BY_S_U_G =
		KBArticleFinder.class.getName() + ".countByS_U_G";

	public static String COUNT_BY_U_G_H_T_D =
		KBArticleFinder.class.getName() + ".countByU_G_H_T_D";

	public static String COUNT_BY_U_R_H_D =
		KBArticleFinder.class.getName() + ".countByU_R_H_D";

	public static String FIND_BY_S_U_G =
		KBArticleFinder.class.getName() + ".findByS_U_G";

	public static String FIND_BY_U_G_H_T_D =
		KBArticleFinder.class.getName() + ".findByU_G_H_T_D";

	public static String FIND_BY_U_R_H_D =
		KBArticleFinder.class.getName() + ".findByU_R_H_D";

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

	public int countByU_G_H_T_D(
			long userId, long groupId, boolean head, boolean template,
			boolean draft)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_U_G_H_T_D);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(head);
			qPos.add(template);
			qPos.add(userId);
			qPos.add(draft);

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

	public int countByU_R_H_D(
			long userId, long resourcePrimKey, boolean head,
			boolean draft)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_U_R_H_D);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);
			qPos.add(head);
			qPos.add(userId);
			qPos.add(draft);

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

	public List<KBArticle> findByS_U_G(long userId, long groupId)
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

			return (List<KBArticle>)q.list();
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

	public List<KBArticle> findByU_G_H_T_D(
			long userId, long groupId, boolean head, boolean template,
			boolean draft)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_U_G_H_T_D);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("KB_KBArticle", KBArticleImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(head);
			qPos.add(template);
			qPos.add(userId);
			qPos.add(draft);

			return (List<KBArticle>)q.list();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<KBArticle> findByU_G_H_T_D(
			long userId, long groupId, boolean head, boolean template,
			boolean draft, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_U_G_H_T_D);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("KB_KBArticle", KBArticleImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(head);
			qPos.add(template);
			qPos.add(userId);
			qPos.add(draft);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<KBArticle> findByU_R_H_D(
			long userId, long resourcePrimKey, boolean head, boolean draft,
			int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_U_R_H_D);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("KB_KBArticle", KBArticleImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);
			qPos.add(head);
			qPos.add(userId);
			qPos.add(draft);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}