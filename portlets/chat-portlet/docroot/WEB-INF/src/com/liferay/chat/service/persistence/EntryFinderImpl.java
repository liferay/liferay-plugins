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

import com.liferay.chat.model.Entry;
import com.liferay.chat.model.impl.EntryImpl;
import com.liferay.portal.SystemException;
import com.liferay.portlet.service.CustomSQLUtil;
import com.liferay.portlet.service.HibernateUtil;
import com.liferay.util.dao.hibernate.QueryPos;
import com.liferay.util.dao.hibernate.QueryUtil;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 * <a href="EntryFinderImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class EntryFinderImpl implements EntryFinder {

	public static String FIND_BY_NEW =
		EntryFinder.class.getName() + ".findByNew";

	public static String FIND_BY_OLD =
		EntryFinder.class.getName() + ".findByOld";

	public List<Entry> findByNew(
			long createDate, long userId, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = HibernateUtil.openSession();

			String sql = CustomSQLUtil.get(FIND_BY_NEW);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("Chat_Entry", EntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(createDate);
			qPos.add(userId);
			qPos.add(userId);

			return (List<Entry>)QueryUtil.list(
				q, HibernateUtil.getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			HibernateUtil.closeSession(session);
		}
	}

	public List<Entry> findByOld(long createDate, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = HibernateUtil.openSession();

			String sql = CustomSQLUtil.get(FIND_BY_OLD);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("Chat_Entry", EntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(createDate);

			return (List<Entry>)QueryUtil.list(
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