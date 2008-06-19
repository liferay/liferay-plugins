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

package com.liferay.chat.service.persistence;

import com.liferay.portal.SystemException;
import com.liferay.portlet.service.CustomSQLUtil;
import com.liferay.portlet.service.HibernateUtil;
import com.liferay.util.dao.hibernate.QueryPos;
import com.liferay.util.dao.hibernate.QueryUtil;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 * <a href="StatusFinderImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class StatusFinderImpl implements StatusFinder {

	public static String FIND_BY_MODIFIED_DATE =
		StatusFinder.class.getName() + ".findByModifiedDate";

	public static String FIND_BY_SOCIAL_RELATION_TYPE =
		StatusFinder.class.getName() + ".findBySocialRelationType";

	public List<Object[]> findByModifiedDate(
			long modifiedDate, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = HibernateUtil.openSession();

			String sql = CustomSQLUtil.get(FIND_BY_MODIFIED_DATE);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar("User_.userId", Hibernate.LONG);
			q.addScalar("Contact_.firstName", Hibernate.STRING);
			q.addScalar("Contact_.middleName", Hibernate.STRING);
			q.addScalar("Contact_.lastName", Hibernate.STRING);
			q.addScalar("User_.portraitId", Hibernate.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(modifiedDate);

			return (List<Object[]>)QueryUtil.list(
				q, HibernateUtil.getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			HibernateUtil.closeSession(session);
		}
	}

	public List<Object[]> findBySocialRelationType(
			long userId, int type, long modifiedDate, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = HibernateUtil.openSession();

			String sql = CustomSQLUtil.get(FIND_BY_SOCIAL_RELATION_TYPE);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar("User_.userId", Hibernate.LONG);
			q.addScalar("Contact_.firstName", Hibernate.STRING);
			q.addScalar("Contact_.middleName", Hibernate.STRING);
			q.addScalar("Contact_.lastName", Hibernate.STRING);
			q.addScalar("User_.portraitId", Hibernate.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(type);
			qPos.add(modifiedDate);

			return (List<Object[]>)QueryUtil.list(
				q, HibernateUtil.getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			HibernateUtil.closeSession(session);
		}
	}

}