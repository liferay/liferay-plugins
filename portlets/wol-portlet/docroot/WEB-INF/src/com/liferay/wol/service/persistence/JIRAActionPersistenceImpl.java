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

package com.liferay.wol.service.persistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.DynamicQuery;
import com.liferay.portal.kernel.dao.DynamicQueryInitializer;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringMaker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;

import com.liferay.portlet.service.BasePersistence;
import com.liferay.portlet.service.FinderCache;
import com.liferay.portlet.service.HibernateUtil;
import com.liferay.portlet.service.PropsUtil;

import com.liferay.util.dao.hibernate.QueryPos;
import com.liferay.util.dao.hibernate.QueryUtil;

import com.liferay.wol.NoSuchJIRAActionException;
import com.liferay.wol.model.JIRAAction;
import com.liferay.wol.model.impl.JIRAActionImpl;
import com.liferay.wol.model.impl.JIRAActionModelImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="JIRAActionPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JIRAActionPersistenceImpl extends BasePersistence
	implements JIRAActionPersistence {
	public JIRAAction create(long jiraActionId) {
		JIRAAction jiraAction = new JIRAActionImpl();

		jiraAction.setNew(true);
		jiraAction.setPrimaryKey(jiraActionId);

		return jiraAction;
	}

	public JIRAAction remove(long jiraActionId)
		throws NoSuchJIRAActionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			JIRAAction jiraAction = (JIRAAction)session.get(JIRAActionImpl.class,
					new Long(jiraActionId));

			if (jiraAction == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No JIRAAction exists with the primary key " +
						jiraActionId);
				}

				throw new NoSuchJIRAActionException(
					"No JIRAAction exists with the primary key " +
					jiraActionId);
			}

			return remove(jiraAction);
		}
		catch (NoSuchJIRAActionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public JIRAAction remove(JIRAAction jiraAction) throws SystemException {
		if (_listeners != null) {
			for (ModelListener listener : _listeners) {
				listener.onBeforeRemove(jiraAction);
			}
		}

		jiraAction = removeImpl(jiraAction);

		if (_listeners != null) {
			for (ModelListener listener : _listeners) {
				listener.onAfterRemove(jiraAction);
			}
		}

		return jiraAction;
	}

	protected JIRAAction removeImpl(JIRAAction jiraAction)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			session.delete(jiraAction);

			session.flush();

			return jiraAction;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);

			FinderCache.clearCache(JIRAAction.class.getName());
		}
	}

	public JIRAAction update(JIRAAction jiraAction) throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(JIRAAction jiraAction) method. Use update(JIRAAction jiraAction, boolean merge) instead.");
		}

		return update(jiraAction, false);
	}

	public JIRAAction update(JIRAAction jiraAction, boolean merge)
		throws SystemException {
		boolean isNew = jiraAction.isNew();

		if (_listeners != null) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onBeforeCreate(jiraAction);
				}
				else {
					listener.onBeforeUpdate(jiraAction);
				}
			}
		}

		jiraAction = updateImpl(jiraAction, merge);

		if (_listeners != null) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onAfterCreate(jiraAction);
				}
				else {
					listener.onAfterUpdate(jiraAction);
				}
			}
		}

		return jiraAction;
	}

	public JIRAAction updateImpl(com.liferay.wol.model.JIRAAction jiraAction,
		boolean merge) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (merge) {
				session.merge(jiraAction);
			}
			else {
				if (jiraAction.isNew()) {
					session.save(jiraAction);
				}
			}

			session.flush();

			jiraAction.setNew(false);

			return jiraAction;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);

			FinderCache.clearCache(JIRAAction.class.getName());
		}
	}

	public JIRAAction findByPrimaryKey(long jiraActionId)
		throws NoSuchJIRAActionException, SystemException {
		JIRAAction jiraAction = fetchByPrimaryKey(jiraActionId);

		if (jiraAction == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No JIRAAction exists with the primary key " +
					jiraActionId);
			}

			throw new NoSuchJIRAActionException(
				"No JIRAAction exists with the primary key " + jiraActionId);
		}

		return jiraAction;
	}

	public JIRAAction fetchByPrimaryKey(long jiraActionId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (JIRAAction)session.get(JIRAActionImpl.class,
				new Long(jiraActionId));
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<JIRAAction> findByJiraUserId(String jiraUserId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAActionModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAAction.class.getName();
		String finderMethodName = "findByJiraUserId";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { jiraUserId };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("FROM com.liferay.wol.model.JIRAAction WHERE ");

				if (jiraUserId == null) {
					query.append("author IS NULL");
				}
				else {
					query.append("author = ?");
				}

				query.append(" ");

				query.append("ORDER BY ");

				query.append("updated DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (jiraUserId != null) {
					qPos.add(jiraUserId);
				}

				List<JIRAAction> list = q.list();

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAAction>)result;
		}
	}

	public List<JIRAAction> findByJiraUserId(String jiraUserId, int begin,
		int end) throws SystemException {
		return findByJiraUserId(jiraUserId, begin, end, null);
	}

	public List<JIRAAction> findByJiraUserId(String jiraUserId, int begin,
		int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAActionModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAAction.class.getName();
		String finderMethodName = "findByJiraUserId";
		String[] finderParams = new String[] {
				String.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				jiraUserId,
				
				String.valueOf(begin), String.valueOf(end), String.valueOf(obc)
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("FROM com.liferay.wol.model.JIRAAction WHERE ");

				if (jiraUserId == null) {
					query.append("author IS NULL");
				}
				else {
					query.append("author = ?");
				}

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("updated DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (jiraUserId != null) {
					qPos.add(jiraUserId);
				}

				List<JIRAAction> list = (List<JIRAAction>)QueryUtil.list(q,
						getDialect(), begin, end);

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAAction>)result;
		}
	}

	public JIRAAction findByJiraUserId_First(String jiraUserId,
		OrderByComparator obc)
		throws NoSuchJIRAActionException, SystemException {
		List<JIRAAction> list = findByJiraUserId(jiraUserId, 0, 1, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();

			msg.append("No JIRAAction exists with the key {");

			msg.append("jiraUserId=" + jiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAActionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAAction findByJiraUserId_Last(String jiraUserId,
		OrderByComparator obc)
		throws NoSuchJIRAActionException, SystemException {
		int count = countByJiraUserId(jiraUserId);

		List<JIRAAction> list = findByJiraUserId(jiraUserId, count - 1, count,
				obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();

			msg.append("No JIRAAction exists with the key {");

			msg.append("jiraUserId=" + jiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAActionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAAction[] findByJiraUserId_PrevAndNext(long jiraActionId,
		String jiraUserId, OrderByComparator obc)
		throws NoSuchJIRAActionException, SystemException {
		JIRAAction jiraAction = findByPrimaryKey(jiraActionId);

		int count = countByJiraUserId(jiraUserId);

		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();

			query.append("FROM com.liferay.wol.model.JIRAAction WHERE ");

			if (jiraUserId == null) {
				query.append("author IS NULL");
			}
			else {
				query.append("author = ?");
			}

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("updated DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			if (jiraUserId != null) {
				qPos.add(jiraUserId);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					jiraAction);

			JIRAAction[] array = new JIRAActionImpl[3];

			array[0] = (JIRAAction)objArray[0];
			array[1] = (JIRAAction)objArray[1];
			array[2] = (JIRAAction)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<JIRAAction> findByJiraIssueId(long jiraIssueId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAActionModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAAction.class.getName();
		String finderMethodName = "findByJiraIssueId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(jiraIssueId) };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("FROM com.liferay.wol.model.JIRAAction WHERE ");

				query.append("issueid = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("updated DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(jiraIssueId);

				List<JIRAAction> list = q.list();

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAAction>)result;
		}
	}

	public List<JIRAAction> findByJiraIssueId(long jiraIssueId, int begin,
		int end) throws SystemException {
		return findByJiraIssueId(jiraIssueId, begin, end, null);
	}

	public List<JIRAAction> findByJiraIssueId(long jiraIssueId, int begin,
		int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAActionModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAAction.class.getName();
		String finderMethodName = "findByJiraIssueId";
		String[] finderParams = new String[] {
				Long.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(jiraIssueId),
				
				String.valueOf(begin), String.valueOf(end), String.valueOf(obc)
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("FROM com.liferay.wol.model.JIRAAction WHERE ");

				query.append("issueid = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("updated DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(jiraIssueId);

				List<JIRAAction> list = (List<JIRAAction>)QueryUtil.list(q,
						getDialect(), begin, end);

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAAction>)result;
		}
	}

	public JIRAAction findByJiraIssueId_First(long jiraIssueId,
		OrderByComparator obc)
		throws NoSuchJIRAActionException, SystemException {
		List<JIRAAction> list = findByJiraIssueId(jiraIssueId, 0, 1, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();

			msg.append("No JIRAAction exists with the key {");

			msg.append("jiraIssueId=" + jiraIssueId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAActionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAAction findByJiraIssueId_Last(long jiraIssueId,
		OrderByComparator obc)
		throws NoSuchJIRAActionException, SystemException {
		int count = countByJiraIssueId(jiraIssueId);

		List<JIRAAction> list = findByJiraIssueId(jiraIssueId, count - 1,
				count, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();

			msg.append("No JIRAAction exists with the key {");

			msg.append("jiraIssueId=" + jiraIssueId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAActionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAAction[] findByJiraIssueId_PrevAndNext(long jiraActionId,
		long jiraIssueId, OrderByComparator obc)
		throws NoSuchJIRAActionException, SystemException {
		JIRAAction jiraAction = findByPrimaryKey(jiraActionId);

		int count = countByJiraIssueId(jiraIssueId);

		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();

			query.append("FROM com.liferay.wol.model.JIRAAction WHERE ");

			query.append("issueid = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("updated DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(jiraIssueId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					jiraAction);

			JIRAAction[] array = new JIRAActionImpl[3];

			array[0] = (JIRAAction)objArray[0];
			array[1] = (JIRAAction)objArray[1];
			array[2] = (JIRAAction)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<JIRAAction> findByType(String type) throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAActionModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAAction.class.getName();
		String finderMethodName = "findByType";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { type };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("FROM com.liferay.wol.model.JIRAAction WHERE ");

				if (type == null) {
					query.append("actiontype IS NULL");
				}
				else {
					query.append("actiontype = ?");
				}

				query.append(" ");

				query.append("ORDER BY ");

				query.append("updated DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (type != null) {
					qPos.add(type);
				}

				List<JIRAAction> list = q.list();

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAAction>)result;
		}
	}

	public List<JIRAAction> findByType(String type, int begin, int end)
		throws SystemException {
		return findByType(type, begin, end, null);
	}

	public List<JIRAAction> findByType(String type, int begin, int end,
		OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAActionModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAAction.class.getName();
		String finderMethodName = "findByType";
		String[] finderParams = new String[] {
				String.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				type,
				
				String.valueOf(begin), String.valueOf(end), String.valueOf(obc)
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("FROM com.liferay.wol.model.JIRAAction WHERE ");

				if (type == null) {
					query.append("actiontype IS NULL");
				}
				else {
					query.append("actiontype = ?");
				}

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("updated DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (type != null) {
					qPos.add(type);
				}

				List<JIRAAction> list = (List<JIRAAction>)QueryUtil.list(q,
						getDialect(), begin, end);

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAAction>)result;
		}
	}

	public JIRAAction findByType_First(String type, OrderByComparator obc)
		throws NoSuchJIRAActionException, SystemException {
		List<JIRAAction> list = findByType(type, 0, 1, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();

			msg.append("No JIRAAction exists with the key {");

			msg.append("type=" + type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAActionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAAction findByType_Last(String type, OrderByComparator obc)
		throws NoSuchJIRAActionException, SystemException {
		int count = countByType(type);

		List<JIRAAction> list = findByType(type, count - 1, count, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();

			msg.append("No JIRAAction exists with the key {");

			msg.append("type=" + type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAActionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAAction[] findByType_PrevAndNext(long jiraActionId, String type,
		OrderByComparator obc)
		throws NoSuchJIRAActionException, SystemException {
		JIRAAction jiraAction = findByPrimaryKey(jiraActionId);

		int count = countByType(type);

		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();

			query.append("FROM com.liferay.wol.model.JIRAAction WHERE ");

			if (type == null) {
				query.append("actiontype IS NULL");
			}
			else {
				query.append("actiontype = ?");
			}

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("updated DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			if (type != null) {
				qPos.add(type);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					jiraAction);

			JIRAAction[] array = new JIRAActionImpl[3];

			array[0] = (JIRAAction)objArray[0];
			array[1] = (JIRAAction)objArray[1];
			array[2] = (JIRAAction)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<JIRAAction> findWithDynamicQuery(
		DynamicQueryInitializer queryInitializer) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			DynamicQuery query = queryInitializer.initialize(session);

			return query.list();
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<JIRAAction> findWithDynamicQuery(
		DynamicQueryInitializer queryInitializer, int begin, int end)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			DynamicQuery query = queryInitializer.initialize(session);

			query.setLimit(begin, end);

			return query.list();
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<JIRAAction> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<JIRAAction> findAll(int begin, int end)
		throws SystemException {
		return findAll(begin, end, null);
	}

	public List<JIRAAction> findAll(int begin, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAActionModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAAction.class.getName();
		String finderMethodName = "findAll";
		String[] finderParams = new String[] {
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				String.valueOf(begin), String.valueOf(end), String.valueOf(obc)
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("FROM com.liferay.wol.model.JIRAAction ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("updated DESC");
				}

				Query q = session.createQuery(query.toString());

				List<JIRAAction> list = (List<JIRAAction>)QueryUtil.list(q,
						getDialect(), begin, end);

				if (obc == null) {
					Collections.sort(list);
				}

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAAction>)result;
		}
	}

	public void removeByJiraUserId(String jiraUserId) throws SystemException {
		for (JIRAAction jiraAction : findByJiraUserId(jiraUserId)) {
			remove(jiraAction);
		}
	}

	public void removeByJiraIssueId(long jiraIssueId) throws SystemException {
		for (JIRAAction jiraAction : findByJiraIssueId(jiraIssueId)) {
			remove(jiraAction);
		}
	}

	public void removeByType(String type) throws SystemException {
		for (JIRAAction jiraAction : findByType(type)) {
			remove(jiraAction);
		}
	}

	public void removeAll() throws SystemException {
		for (JIRAAction jiraAction : findAll()) {
			remove(jiraAction);
		}
	}

	public int countByJiraUserId(String jiraUserId) throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAActionModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAAction.class.getName();
		String finderMethodName = "countByJiraUserId";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { jiraUserId };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.wol.model.JIRAAction WHERE ");

				if (jiraUserId == null) {
					query.append("author IS NULL");
				}
				else {
					query.append("author = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (jiraUserId != null) {
					qPos.add(jiraUserId);
				}

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return ((Long)result).intValue();
		}
	}

	public int countByJiraIssueId(long jiraIssueId) throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAActionModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAAction.class.getName();
		String finderMethodName = "countByJiraIssueId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(jiraIssueId) };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.wol.model.JIRAAction WHERE ");

				query.append("issueid = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(jiraIssueId);

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return ((Long)result).intValue();
		}
	}

	public int countByType(String type) throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAActionModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAAction.class.getName();
		String finderMethodName = "countByType";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { type };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.wol.model.JIRAAction WHERE ");

				if (type == null) {
					query.append("actiontype IS NULL");
				}
				else {
					query.append("actiontype = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (type != null) {
					qPos.add(type);
				}

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return ((Long)result).intValue();
		}
	}

	public int countAll() throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAActionModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAAction.class.getName();
		String finderMethodName = "countAll";
		String[] finderParams = new String[] {  };
		Object[] finderArgs = new Object[] {  };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(
						"SELECT COUNT(*) FROM com.liferay.wol.model.JIRAAction");

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return ((Long)result).intValue();
		}
	}

	protected void initDao() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					PropsUtil.get(
						"value.object.listener.com.liferay.wol.model.JIRAAction")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener> listeners = new ArrayList<ModelListener>();

				for (String listenerClassName : listenerClassNames) {
					listeners.add((ModelListener)Class.forName(
							listenerClassName).newInstance());
				}

				_listeners = listeners.toArray(new ModelListener[listeners.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	private static Log _log = LogFactory.getLog(JIRAActionPersistenceImpl.class);
	private ModelListener[] _listeners;
}