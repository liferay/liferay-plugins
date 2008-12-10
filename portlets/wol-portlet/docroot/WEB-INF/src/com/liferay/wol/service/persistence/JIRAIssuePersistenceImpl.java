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
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.wol.NoSuchJIRAIssueException;
import com.liferay.wol.model.JIRAIssue;
import com.liferay.wol.model.impl.JIRAIssueImpl;
import com.liferay.wol.model.impl.JIRAIssueModelImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="JIRAIssuePersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JIRAIssuePersistenceImpl extends BasePersistenceImpl
	implements JIRAIssuePersistence {
	public JIRAIssue create(long jiraIssueId) {
		JIRAIssue jiraIssue = new JIRAIssueImpl();

		jiraIssue.setNew(true);
		jiraIssue.setPrimaryKey(jiraIssueId);

		return jiraIssue;
	}

	public JIRAIssue remove(long jiraIssueId)
		throws NoSuchJIRAIssueException, SystemException {
		Session session = null;

		try {
			session = openSession();

			JIRAIssue jiraIssue = (JIRAIssue)session.get(JIRAIssueImpl.class,
					new Long(jiraIssueId));

			if (jiraIssue == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No JIRAIssue exists with the primary key " +
						jiraIssueId);
				}

				throw new NoSuchJIRAIssueException(
					"No JIRAIssue exists with the primary key " + jiraIssueId);
			}

			return remove(jiraIssue);
		}
		catch (NoSuchJIRAIssueException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public JIRAIssue remove(JIRAIssue jiraIssue) throws SystemException {
		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onBeforeRemove(jiraIssue);
			}
		}

		jiraIssue = removeImpl(jiraIssue);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onAfterRemove(jiraIssue);
			}
		}

		return jiraIssue;
	}

	protected JIRAIssue removeImpl(JIRAIssue jiraIssue)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(JIRAIssueImpl.class,
						jiraIssue.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(jiraIssue);

			session.flush();

			return jiraIssue;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(JIRAIssue.class.getName());
		}
	}

	public JIRAIssue update(JIRAIssue jiraIssue) throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(JIRAIssue jiraIssue) method. Use update(JIRAIssue jiraIssue, boolean merge) instead.");
		}

		return update(jiraIssue, false);
	}

	public JIRAIssue update(JIRAIssue jiraIssue, boolean merge)
		throws SystemException {
		boolean isNew = jiraIssue.isNew();

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onBeforeCreate(jiraIssue);
				}
				else {
					listener.onBeforeUpdate(jiraIssue);
				}
			}
		}

		jiraIssue = updateImpl(jiraIssue, merge);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onAfterCreate(jiraIssue);
				}
				else {
					listener.onAfterUpdate(jiraIssue);
				}
			}
		}

		return jiraIssue;
	}

	public JIRAIssue updateImpl(com.liferay.wol.model.JIRAIssue jiraIssue,
		boolean merge) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, jiraIssue, merge);

			jiraIssue.setNew(false);

			return jiraIssue;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(JIRAIssue.class.getName());
		}
	}

	public JIRAIssue findByPrimaryKey(long jiraIssueId)
		throws NoSuchJIRAIssueException, SystemException {
		JIRAIssue jiraIssue = fetchByPrimaryKey(jiraIssueId);

		if (jiraIssue == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No JIRAIssue exists with the primary key " +
					jiraIssueId);
			}

			throw new NoSuchJIRAIssueException(
				"No JIRAIssue exists with the primary key " + jiraIssueId);
		}

		return jiraIssue;
	}

	public JIRAIssue fetchByPrimaryKey(long jiraIssueId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (JIRAIssue)session.get(JIRAIssueImpl.class,
				new Long(jiraIssueId));
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<JIRAIssue> findByProjectId(long projectId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "findByProjectId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(projectId) };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				query.append("project = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("updated DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				List<JIRAIssue> list = q.list();

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAIssue>)result;
		}
	}

	public List<JIRAIssue> findByProjectId(long projectId, int start, int end)
		throws SystemException {
		return findByProjectId(projectId, start, end, null);
	}

	public List<JIRAIssue> findByProjectId(long projectId, int start, int end,
		OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "findByProjectId";
		String[] finderParams = new String[] {
				Long.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(projectId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				query.append("project = ?");

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

				qPos.add(projectId);

				List<JIRAIssue> list = (List<JIRAIssue>)QueryUtil.list(q,
						getDialect(), start, end);

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAIssue>)result;
		}
	}

	public JIRAIssue findByProjectId_First(long projectId, OrderByComparator obc)
		throws NoSuchJIRAIssueException, SystemException {
		List<JIRAIssue> list = findByProjectId(projectId, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No JIRAIssue exists with the key {");

			msg.append("projectId=" + projectId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAIssue findByProjectId_Last(long projectId, OrderByComparator obc)
		throws NoSuchJIRAIssueException, SystemException {
		int count = countByProjectId(projectId);

		List<JIRAIssue> list = findByProjectId(projectId, count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No JIRAIssue exists with the key {");

			msg.append("projectId=" + projectId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAIssue[] findByProjectId_PrevAndNext(long jiraIssueId,
		long projectId, OrderByComparator obc)
		throws NoSuchJIRAIssueException, SystemException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		int count = countByProjectId(projectId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

			query.append("project = ?");

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

			qPos.add(projectId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					jiraIssue);

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = (JIRAIssue)objArray[0];
			array[1] = (JIRAIssue)objArray[1];
			array[2] = (JIRAIssue)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public JIRAIssue findByKey(String key)
		throws NoSuchJIRAIssueException, SystemException {
		JIRAIssue jiraIssue = fetchByKey(key);

		if (jiraIssue == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No JIRAIssue exists with the key {");

			msg.append("key=" + key);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchJIRAIssueException(msg.toString());
		}

		return jiraIssue;
	}

	public JIRAIssue fetchByKey(String key) throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "fetchByKey";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { key };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				if (key == null) {
					query.append("pkey IS NULL");
				}
				else {
					query.append("pkey = ?");
				}

				query.append(" ");

				query.append("ORDER BY ");

				query.append("updated DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (key != null) {
					qPos.add(key);
				}

				List<JIRAIssue> list = q.list();

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				if (list.size() == 0) {
					return null;
				}
				else {
					return list.get(0);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			List<JIRAIssue> list = (List<JIRAIssue>)result;

			if (list.size() == 0) {
				return null;
			}
			else {
				return list.get(0);
			}
		}
	}

	public List<JIRAIssue> findByReporterJiraUserId(String reporterJiraUserId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "findByReporterJiraUserId";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { reporterJiraUserId };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				if (reporterJiraUserId == null) {
					query.append("reporter IS NULL");
				}
				else {
					query.append("reporter = ?");
				}

				query.append(" ");

				query.append("ORDER BY ");

				query.append("updated DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (reporterJiraUserId != null) {
					qPos.add(reporterJiraUserId);
				}

				List<JIRAIssue> list = q.list();

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAIssue>)result;
		}
	}

	public List<JIRAIssue> findByReporterJiraUserId(String reporterJiraUserId,
		int start, int end) throws SystemException {
		return findByReporterJiraUserId(reporterJiraUserId, start, end, null);
	}

	public List<JIRAIssue> findByReporterJiraUserId(String reporterJiraUserId,
		int start, int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "findByReporterJiraUserId";
		String[] finderParams = new String[] {
				String.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				reporterJiraUserId,
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				if (reporterJiraUserId == null) {
					query.append("reporter IS NULL");
				}
				else {
					query.append("reporter = ?");
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

				if (reporterJiraUserId != null) {
					qPos.add(reporterJiraUserId);
				}

				List<JIRAIssue> list = (List<JIRAIssue>)QueryUtil.list(q,
						getDialect(), start, end);

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAIssue>)result;
		}
	}

	public JIRAIssue findByReporterJiraUserId_First(String reporterJiraUserId,
		OrderByComparator obc) throws NoSuchJIRAIssueException, SystemException {
		List<JIRAIssue> list = findByReporterJiraUserId(reporterJiraUserId, 0,
				1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No JIRAIssue exists with the key {");

			msg.append("reporterJiraUserId=" + reporterJiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAIssue findByReporterJiraUserId_Last(String reporterJiraUserId,
		OrderByComparator obc) throws NoSuchJIRAIssueException, SystemException {
		int count = countByReporterJiraUserId(reporterJiraUserId);

		List<JIRAIssue> list = findByReporterJiraUserId(reporterJiraUserId,
				count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No JIRAIssue exists with the key {");

			msg.append("reporterJiraUserId=" + reporterJiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAIssue[] findByReporterJiraUserId_PrevAndNext(long jiraIssueId,
		String reporterJiraUserId, OrderByComparator obc)
		throws NoSuchJIRAIssueException, SystemException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		int count = countByReporterJiraUserId(reporterJiraUserId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

			if (reporterJiraUserId == null) {
				query.append("reporter IS NULL");
			}
			else {
				query.append("reporter = ?");
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

			if (reporterJiraUserId != null) {
				qPos.add(reporterJiraUserId);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					jiraIssue);

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = (JIRAIssue)objArray[0];
			array[1] = (JIRAIssue)objArray[1];
			array[2] = (JIRAIssue)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<JIRAIssue> findByAssigneeJiraUserId(String assigneeJiraUserId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "findByAssigneeJiraUserId";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { assigneeJiraUserId };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				if (assigneeJiraUserId == null) {
					query.append("assignee IS NULL");
				}
				else {
					query.append("assignee = ?");
				}

				query.append(" ");

				query.append("ORDER BY ");

				query.append("updated DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (assigneeJiraUserId != null) {
					qPos.add(assigneeJiraUserId);
				}

				List<JIRAIssue> list = q.list();

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAIssue>)result;
		}
	}

	public List<JIRAIssue> findByAssigneeJiraUserId(String assigneeJiraUserId,
		int start, int end) throws SystemException {
		return findByAssigneeJiraUserId(assigneeJiraUserId, start, end, null);
	}

	public List<JIRAIssue> findByAssigneeJiraUserId(String assigneeJiraUserId,
		int start, int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "findByAssigneeJiraUserId";
		String[] finderParams = new String[] {
				String.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				assigneeJiraUserId,
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				if (assigneeJiraUserId == null) {
					query.append("assignee IS NULL");
				}
				else {
					query.append("assignee = ?");
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

				if (assigneeJiraUserId != null) {
					qPos.add(assigneeJiraUserId);
				}

				List<JIRAIssue> list = (List<JIRAIssue>)QueryUtil.list(q,
						getDialect(), start, end);

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAIssue>)result;
		}
	}

	public JIRAIssue findByAssigneeJiraUserId_First(String assigneeJiraUserId,
		OrderByComparator obc) throws NoSuchJIRAIssueException, SystemException {
		List<JIRAIssue> list = findByAssigneeJiraUserId(assigneeJiraUserId, 0,
				1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No JIRAIssue exists with the key {");

			msg.append("assigneeJiraUserId=" + assigneeJiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAIssue findByAssigneeJiraUserId_Last(String assigneeJiraUserId,
		OrderByComparator obc) throws NoSuchJIRAIssueException, SystemException {
		int count = countByAssigneeJiraUserId(assigneeJiraUserId);

		List<JIRAIssue> list = findByAssigneeJiraUserId(assigneeJiraUserId,
				count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No JIRAIssue exists with the key {");

			msg.append("assigneeJiraUserId=" + assigneeJiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAIssue[] findByAssigneeJiraUserId_PrevAndNext(long jiraIssueId,
		String assigneeJiraUserId, OrderByComparator obc)
		throws NoSuchJIRAIssueException, SystemException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		int count = countByAssigneeJiraUserId(assigneeJiraUserId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

			if (assigneeJiraUserId == null) {
				query.append("assignee IS NULL");
			}
			else {
				query.append("assignee = ?");
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

			if (assigneeJiraUserId != null) {
				qPos.add(assigneeJiraUserId);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					jiraIssue);

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = (JIRAIssue)objArray[0];
			array[1] = (JIRAIssue)objArray[1];
			array[2] = (JIRAIssue)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<JIRAIssue> findByMD_P(Date modifiedDate, long projectId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "findByMD_P";
		String[] finderParams = new String[] {
				Date.class.getName(), Long.class.getName()
			};
		Object[] finderArgs = new Object[] { modifiedDate, new Long(projectId) };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				if (modifiedDate == null) {
					query.append("updated > null");
				}
				else {
					query.append("updated > ?");
				}

				query.append(" AND ");

				query.append("project = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("updated DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				qPos.add(projectId);

				List<JIRAIssue> list = q.list();

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAIssue>)result;
		}
	}

	public List<JIRAIssue> findByMD_P(Date modifiedDate, long projectId,
		int start, int end) throws SystemException {
		return findByMD_P(modifiedDate, projectId, start, end, null);
	}

	public List<JIRAIssue> findByMD_P(Date modifiedDate, long projectId,
		int start, int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "findByMD_P";
		String[] finderParams = new String[] {
				Date.class.getName(), Long.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				modifiedDate, new Long(projectId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				if (modifiedDate == null) {
					query.append("updated > null");
				}
				else {
					query.append("updated > ?");
				}

				query.append(" AND ");

				query.append("project = ?");

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

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				qPos.add(projectId);

				List<JIRAIssue> list = (List<JIRAIssue>)QueryUtil.list(q,
						getDialect(), start, end);

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAIssue>)result;
		}
	}

	public JIRAIssue findByMD_P_First(Date modifiedDate, long projectId,
		OrderByComparator obc) throws NoSuchJIRAIssueException, SystemException {
		List<JIRAIssue> list = findByMD_P(modifiedDate, projectId, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No JIRAIssue exists with the key {");

			msg.append("modifiedDate=" + modifiedDate);

			msg.append(", ");
			msg.append("projectId=" + projectId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAIssue findByMD_P_Last(Date modifiedDate, long projectId,
		OrderByComparator obc) throws NoSuchJIRAIssueException, SystemException {
		int count = countByMD_P(modifiedDate, projectId);

		List<JIRAIssue> list = findByMD_P(modifiedDate, projectId, count - 1,
				count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No JIRAIssue exists with the key {");

			msg.append("modifiedDate=" + modifiedDate);

			msg.append(", ");
			msg.append("projectId=" + projectId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAIssue[] findByMD_P_PrevAndNext(long jiraIssueId,
		Date modifiedDate, long projectId, OrderByComparator obc)
		throws NoSuchJIRAIssueException, SystemException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		int count = countByMD_P(modifiedDate, projectId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

			if (modifiedDate == null) {
				query.append("updated > null");
			}
			else {
				query.append("updated > ?");
			}

			query.append(" AND ");

			query.append("project = ?");

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

			if (modifiedDate != null) {
				qPos.add(CalendarUtil.getTimestamp(modifiedDate));
			}

			qPos.add(projectId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					jiraIssue);

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = (JIRAIssue)objArray[0];
			array[1] = (JIRAIssue)objArray[1];
			array[2] = (JIRAIssue)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<JIRAIssue> findByP_RJUI(long projectId,
		String reporterJiraUserId) throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "findByP_RJUI";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(projectId),
				
				reporterJiraUserId
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				query.append("project = ?");

				query.append(" AND ");

				if (reporterJiraUserId == null) {
					query.append("reporter IS NULL");
				}
				else {
					query.append("reporter = ?");
				}

				query.append(" ");

				query.append("ORDER BY ");

				query.append("updated DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (reporterJiraUserId != null) {
					qPos.add(reporterJiraUserId);
				}

				List<JIRAIssue> list = q.list();

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAIssue>)result;
		}
	}

	public List<JIRAIssue> findByP_RJUI(long projectId,
		String reporterJiraUserId, int start, int end)
		throws SystemException {
		return findByP_RJUI(projectId, reporterJiraUserId, start, end, null);
	}

	public List<JIRAIssue> findByP_RJUI(long projectId,
		String reporterJiraUserId, int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "findByP_RJUI";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(projectId),
				
				reporterJiraUserId,
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				query.append("project = ?");

				query.append(" AND ");

				if (reporterJiraUserId == null) {
					query.append("reporter IS NULL");
				}
				else {
					query.append("reporter = ?");
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

				qPos.add(projectId);

				if (reporterJiraUserId != null) {
					qPos.add(reporterJiraUserId);
				}

				List<JIRAIssue> list = (List<JIRAIssue>)QueryUtil.list(q,
						getDialect(), start, end);

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAIssue>)result;
		}
	}

	public JIRAIssue findByP_RJUI_First(long projectId,
		String reporterJiraUserId, OrderByComparator obc)
		throws NoSuchJIRAIssueException, SystemException {
		List<JIRAIssue> list = findByP_RJUI(projectId, reporterJiraUserId, 0,
				1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No JIRAIssue exists with the key {");

			msg.append("projectId=" + projectId);

			msg.append(", ");
			msg.append("reporterJiraUserId=" + reporterJiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAIssue findByP_RJUI_Last(long projectId,
		String reporterJiraUserId, OrderByComparator obc)
		throws NoSuchJIRAIssueException, SystemException {
		int count = countByP_RJUI(projectId, reporterJiraUserId);

		List<JIRAIssue> list = findByP_RJUI(projectId, reporterJiraUserId,
				count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No JIRAIssue exists with the key {");

			msg.append("projectId=" + projectId);

			msg.append(", ");
			msg.append("reporterJiraUserId=" + reporterJiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAIssue[] findByP_RJUI_PrevAndNext(long jiraIssueId,
		long projectId, String reporterJiraUserId, OrderByComparator obc)
		throws NoSuchJIRAIssueException, SystemException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		int count = countByP_RJUI(projectId, reporterJiraUserId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

			query.append("project = ?");

			query.append(" AND ");

			if (reporterJiraUserId == null) {
				query.append("reporter IS NULL");
			}
			else {
				query.append("reporter = ?");
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

			qPos.add(projectId);

			if (reporterJiraUserId != null) {
				qPos.add(reporterJiraUserId);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					jiraIssue);

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = (JIRAIssue)objArray[0];
			array[1] = (JIRAIssue)objArray[1];
			array[2] = (JIRAIssue)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<JIRAIssue> findByP_AJUI(long projectId,
		String assigneeJiraUserId) throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "findByP_AJUI";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(projectId),
				
				assigneeJiraUserId
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				query.append("project = ?");

				query.append(" AND ");

				if (assigneeJiraUserId == null) {
					query.append("assignee IS NULL");
				}
				else {
					query.append("assignee = ?");
				}

				query.append(" ");

				query.append("ORDER BY ");

				query.append("updated DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (assigneeJiraUserId != null) {
					qPos.add(assigneeJiraUserId);
				}

				List<JIRAIssue> list = q.list();

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAIssue>)result;
		}
	}

	public List<JIRAIssue> findByP_AJUI(long projectId,
		String assigneeJiraUserId, int start, int end)
		throws SystemException {
		return findByP_AJUI(projectId, assigneeJiraUserId, start, end, null);
	}

	public List<JIRAIssue> findByP_AJUI(long projectId,
		String assigneeJiraUserId, int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "findByP_AJUI";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(projectId),
				
				assigneeJiraUserId,
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				query.append("project = ?");

				query.append(" AND ");

				if (assigneeJiraUserId == null) {
					query.append("assignee IS NULL");
				}
				else {
					query.append("assignee = ?");
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

				qPos.add(projectId);

				if (assigneeJiraUserId != null) {
					qPos.add(assigneeJiraUserId);
				}

				List<JIRAIssue> list = (List<JIRAIssue>)QueryUtil.list(q,
						getDialect(), start, end);

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAIssue>)result;
		}
	}

	public JIRAIssue findByP_AJUI_First(long projectId,
		String assigneeJiraUserId, OrderByComparator obc)
		throws NoSuchJIRAIssueException, SystemException {
		List<JIRAIssue> list = findByP_AJUI(projectId, assigneeJiraUserId, 0,
				1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No JIRAIssue exists with the key {");

			msg.append("projectId=" + projectId);

			msg.append(", ");
			msg.append("assigneeJiraUserId=" + assigneeJiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAIssue findByP_AJUI_Last(long projectId,
		String assigneeJiraUserId, OrderByComparator obc)
		throws NoSuchJIRAIssueException, SystemException {
		int count = countByP_AJUI(projectId, assigneeJiraUserId);

		List<JIRAIssue> list = findByP_AJUI(projectId, assigneeJiraUserId,
				count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No JIRAIssue exists with the key {");

			msg.append("projectId=" + projectId);

			msg.append(", ");
			msg.append("assigneeJiraUserId=" + assigneeJiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAIssue[] findByP_AJUI_PrevAndNext(long jiraIssueId,
		long projectId, String assigneeJiraUserId, OrderByComparator obc)
		throws NoSuchJIRAIssueException, SystemException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		int count = countByP_AJUI(projectId, assigneeJiraUserId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

			query.append("project = ?");

			query.append(" AND ");

			if (assigneeJiraUserId == null) {
				query.append("assignee IS NULL");
			}
			else {
				query.append("assignee = ?");
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

			qPos.add(projectId);

			if (assigneeJiraUserId != null) {
				qPos.add(assigneeJiraUserId);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					jiraIssue);

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = (JIRAIssue)objArray[0];
			array[1] = (JIRAIssue)objArray[1];
			array[2] = (JIRAIssue)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<JIRAIssue> findByMD_P_RJUI(Date modifiedDate, long projectId,
		String reporterJiraUserId) throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "findByMD_P_RJUI";
		String[] finderParams = new String[] {
				Date.class.getName(), Long.class.getName(),
				String.class.getName()
			};
		Object[] finderArgs = new Object[] {
				modifiedDate, new Long(projectId),
				
				reporterJiraUserId
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				if (modifiedDate == null) {
					query.append("updated > null");
				}
				else {
					query.append("updated > ?");
				}

				query.append(" AND ");

				query.append("project = ?");

				query.append(" AND ");

				if (reporterJiraUserId == null) {
					query.append("reporter IS NULL");
				}
				else {
					query.append("reporter = ?");
				}

				query.append(" ");

				query.append("ORDER BY ");

				query.append("updated DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				qPos.add(projectId);

				if (reporterJiraUserId != null) {
					qPos.add(reporterJiraUserId);
				}

				List<JIRAIssue> list = q.list();

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAIssue>)result;
		}
	}

	public List<JIRAIssue> findByMD_P_RJUI(Date modifiedDate, long projectId,
		String reporterJiraUserId, int start, int end)
		throws SystemException {
		return findByMD_P_RJUI(modifiedDate, projectId, reporterJiraUserId,
			start, end, null);
	}

	public List<JIRAIssue> findByMD_P_RJUI(Date modifiedDate, long projectId,
		String reporterJiraUserId, int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "findByMD_P_RJUI";
		String[] finderParams = new String[] {
				Date.class.getName(), Long.class.getName(),
				String.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				modifiedDate, new Long(projectId),
				
				reporterJiraUserId,
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				if (modifiedDate == null) {
					query.append("updated > null");
				}
				else {
					query.append("updated > ?");
				}

				query.append(" AND ");

				query.append("project = ?");

				query.append(" AND ");

				if (reporterJiraUserId == null) {
					query.append("reporter IS NULL");
				}
				else {
					query.append("reporter = ?");
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

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				qPos.add(projectId);

				if (reporterJiraUserId != null) {
					qPos.add(reporterJiraUserId);
				}

				List<JIRAIssue> list = (List<JIRAIssue>)QueryUtil.list(q,
						getDialect(), start, end);

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAIssue>)result;
		}
	}

	public JIRAIssue findByMD_P_RJUI_First(Date modifiedDate, long projectId,
		String reporterJiraUserId, OrderByComparator obc)
		throws NoSuchJIRAIssueException, SystemException {
		List<JIRAIssue> list = findByMD_P_RJUI(modifiedDate, projectId,
				reporterJiraUserId, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No JIRAIssue exists with the key {");

			msg.append("modifiedDate=" + modifiedDate);

			msg.append(", ");
			msg.append("projectId=" + projectId);

			msg.append(", ");
			msg.append("reporterJiraUserId=" + reporterJiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAIssue findByMD_P_RJUI_Last(Date modifiedDate, long projectId,
		String reporterJiraUserId, OrderByComparator obc)
		throws NoSuchJIRAIssueException, SystemException {
		int count = countByMD_P_RJUI(modifiedDate, projectId, reporterJiraUserId);

		List<JIRAIssue> list = findByMD_P_RJUI(modifiedDate, projectId,
				reporterJiraUserId, count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No JIRAIssue exists with the key {");

			msg.append("modifiedDate=" + modifiedDate);

			msg.append(", ");
			msg.append("projectId=" + projectId);

			msg.append(", ");
			msg.append("reporterJiraUserId=" + reporterJiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAIssue[] findByMD_P_RJUI_PrevAndNext(long jiraIssueId,
		Date modifiedDate, long projectId, String reporterJiraUserId,
		OrderByComparator obc) throws NoSuchJIRAIssueException, SystemException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		int count = countByMD_P_RJUI(modifiedDate, projectId, reporterJiraUserId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

			if (modifiedDate == null) {
				query.append("updated > null");
			}
			else {
				query.append("updated > ?");
			}

			query.append(" AND ");

			query.append("project = ?");

			query.append(" AND ");

			if (reporterJiraUserId == null) {
				query.append("reporter IS NULL");
			}
			else {
				query.append("reporter = ?");
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

			if (modifiedDate != null) {
				qPos.add(CalendarUtil.getTimestamp(modifiedDate));
			}

			qPos.add(projectId);

			if (reporterJiraUserId != null) {
				qPos.add(reporterJiraUserId);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					jiraIssue);

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = (JIRAIssue)objArray[0];
			array[1] = (JIRAIssue)objArray[1];
			array[2] = (JIRAIssue)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<JIRAIssue> findByMD_P_AJUI(Date modifiedDate, long projectId,
		String assigneeJiraUserId) throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "findByMD_P_AJUI";
		String[] finderParams = new String[] {
				Date.class.getName(), Long.class.getName(),
				String.class.getName()
			};
		Object[] finderArgs = new Object[] {
				modifiedDate, new Long(projectId),
				
				assigneeJiraUserId
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				if (modifiedDate == null) {
					query.append("updated > null");
				}
				else {
					query.append("updated > ?");
				}

				query.append(" AND ");

				query.append("project = ?");

				query.append(" AND ");

				if (assigneeJiraUserId == null) {
					query.append("assignee IS NULL");
				}
				else {
					query.append("assignee = ?");
				}

				query.append(" ");

				query.append("ORDER BY ");

				query.append("updated DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				qPos.add(projectId);

				if (assigneeJiraUserId != null) {
					qPos.add(assigneeJiraUserId);
				}

				List<JIRAIssue> list = q.list();

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAIssue>)result;
		}
	}

	public List<JIRAIssue> findByMD_P_AJUI(Date modifiedDate, long projectId,
		String assigneeJiraUserId, int start, int end)
		throws SystemException {
		return findByMD_P_AJUI(modifiedDate, projectId, assigneeJiraUserId,
			start, end, null);
	}

	public List<JIRAIssue> findByMD_P_AJUI(Date modifiedDate, long projectId,
		String assigneeJiraUserId, int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "findByMD_P_AJUI";
		String[] finderParams = new String[] {
				Date.class.getName(), Long.class.getName(),
				String.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				modifiedDate, new Long(projectId),
				
				assigneeJiraUserId,
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				if (modifiedDate == null) {
					query.append("updated > null");
				}
				else {
					query.append("updated > ?");
				}

				query.append(" AND ");

				query.append("project = ?");

				query.append(" AND ");

				if (assigneeJiraUserId == null) {
					query.append("assignee IS NULL");
				}
				else {
					query.append("assignee = ?");
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

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				qPos.add(projectId);

				if (assigneeJiraUserId != null) {
					qPos.add(assigneeJiraUserId);
				}

				List<JIRAIssue> list = (List<JIRAIssue>)QueryUtil.list(q,
						getDialect(), start, end);

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAIssue>)result;
		}
	}

	public JIRAIssue findByMD_P_AJUI_First(Date modifiedDate, long projectId,
		String assigneeJiraUserId, OrderByComparator obc)
		throws NoSuchJIRAIssueException, SystemException {
		List<JIRAIssue> list = findByMD_P_AJUI(modifiedDate, projectId,
				assigneeJiraUserId, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No JIRAIssue exists with the key {");

			msg.append("modifiedDate=" + modifiedDate);

			msg.append(", ");
			msg.append("projectId=" + projectId);

			msg.append(", ");
			msg.append("assigneeJiraUserId=" + assigneeJiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAIssue findByMD_P_AJUI_Last(Date modifiedDate, long projectId,
		String assigneeJiraUserId, OrderByComparator obc)
		throws NoSuchJIRAIssueException, SystemException {
		int count = countByMD_P_AJUI(modifiedDate, projectId, assigneeJiraUserId);

		List<JIRAIssue> list = findByMD_P_AJUI(modifiedDate, projectId,
				assigneeJiraUserId, count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No JIRAIssue exists with the key {");

			msg.append("modifiedDate=" + modifiedDate);

			msg.append(", ");
			msg.append("projectId=" + projectId);

			msg.append(", ");
			msg.append("assigneeJiraUserId=" + assigneeJiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAIssue[] findByMD_P_AJUI_PrevAndNext(long jiraIssueId,
		Date modifiedDate, long projectId, String assigneeJiraUserId,
		OrderByComparator obc) throws NoSuchJIRAIssueException, SystemException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		int count = countByMD_P_AJUI(modifiedDate, projectId, assigneeJiraUserId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

			if (modifiedDate == null) {
				query.append("updated > null");
			}
			else {
				query.append("updated > ?");
			}

			query.append(" AND ");

			query.append("project = ?");

			query.append(" AND ");

			if (assigneeJiraUserId == null) {
				query.append("assignee IS NULL");
			}
			else {
				query.append("assignee = ?");
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

			if (modifiedDate != null) {
				qPos.add(CalendarUtil.getTimestamp(modifiedDate));
			}

			qPos.add(projectId);

			if (assigneeJiraUserId != null) {
				qPos.add(assigneeJiraUserId);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					jiraIssue);

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = (JIRAIssue)objArray[0];
			array[1] = (JIRAIssue)objArray[1];
			array[2] = (JIRAIssue)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<JIRAIssue> findByP_RJUI_S(long projectId,
		String reporterJiraUserId, String status) throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "findByP_RJUI_S";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(projectId),
				
				reporterJiraUserId,
				
				status
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				query.append("project = ?");

				query.append(" AND ");

				if (reporterJiraUserId == null) {
					query.append("reporter IS NULL");
				}
				else {
					query.append("reporter = ?");
				}

				query.append(" AND ");

				if (status == null) {
					query.append("issuestatus IS NULL");
				}
				else {
					query.append("issuestatus = ?");
				}

				query.append(" ");

				query.append("ORDER BY ");

				query.append("updated DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (reporterJiraUserId != null) {
					qPos.add(reporterJiraUserId);
				}

				if (status != null) {
					qPos.add(status);
				}

				List<JIRAIssue> list = q.list();

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAIssue>)result;
		}
	}

	public List<JIRAIssue> findByP_RJUI_S(long projectId,
		String reporterJiraUserId, String status, int start, int end)
		throws SystemException {
		return findByP_RJUI_S(projectId, reporterJiraUserId, status, start,
			end, null);
	}

	public List<JIRAIssue> findByP_RJUI_S(long projectId,
		String reporterJiraUserId, String status, int start, int end,
		OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "findByP_RJUI_S";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(projectId),
				
				reporterJiraUserId,
				
				status,
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				query.append("project = ?");

				query.append(" AND ");

				if (reporterJiraUserId == null) {
					query.append("reporter IS NULL");
				}
				else {
					query.append("reporter = ?");
				}

				query.append(" AND ");

				if (status == null) {
					query.append("issuestatus IS NULL");
				}
				else {
					query.append("issuestatus = ?");
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

				qPos.add(projectId);

				if (reporterJiraUserId != null) {
					qPos.add(reporterJiraUserId);
				}

				if (status != null) {
					qPos.add(status);
				}

				List<JIRAIssue> list = (List<JIRAIssue>)QueryUtil.list(q,
						getDialect(), start, end);

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAIssue>)result;
		}
	}

	public JIRAIssue findByP_RJUI_S_First(long projectId,
		String reporterJiraUserId, String status, OrderByComparator obc)
		throws NoSuchJIRAIssueException, SystemException {
		List<JIRAIssue> list = findByP_RJUI_S(projectId, reporterJiraUserId,
				status, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No JIRAIssue exists with the key {");

			msg.append("projectId=" + projectId);

			msg.append(", ");
			msg.append("reporterJiraUserId=" + reporterJiraUserId);

			msg.append(", ");
			msg.append("status=" + status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAIssue findByP_RJUI_S_Last(long projectId,
		String reporterJiraUserId, String status, OrderByComparator obc)
		throws NoSuchJIRAIssueException, SystemException {
		int count = countByP_RJUI_S(projectId, reporterJiraUserId, status);

		List<JIRAIssue> list = findByP_RJUI_S(projectId, reporterJiraUserId,
				status, count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No JIRAIssue exists with the key {");

			msg.append("projectId=" + projectId);

			msg.append(", ");
			msg.append("reporterJiraUserId=" + reporterJiraUserId);

			msg.append(", ");
			msg.append("status=" + status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAIssue[] findByP_RJUI_S_PrevAndNext(long jiraIssueId,
		long projectId, String reporterJiraUserId, String status,
		OrderByComparator obc) throws NoSuchJIRAIssueException, SystemException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		int count = countByP_RJUI_S(projectId, reporterJiraUserId, status);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

			query.append("project = ?");

			query.append(" AND ");

			if (reporterJiraUserId == null) {
				query.append("reporter IS NULL");
			}
			else {
				query.append("reporter = ?");
			}

			query.append(" AND ");

			if (status == null) {
				query.append("issuestatus IS NULL");
			}
			else {
				query.append("issuestatus = ?");
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

			qPos.add(projectId);

			if (reporterJiraUserId != null) {
				qPos.add(reporterJiraUserId);
			}

			if (status != null) {
				qPos.add(status);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					jiraIssue);

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = (JIRAIssue)objArray[0];
			array[1] = (JIRAIssue)objArray[1];
			array[2] = (JIRAIssue)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<JIRAIssue> findByP_AJUI_S(long projectId,
		String assigneeJiraUserId, String status) throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "findByP_AJUI_S";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(projectId),
				
				assigneeJiraUserId,
				
				status
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				query.append("project = ?");

				query.append(" AND ");

				if (assigneeJiraUserId == null) {
					query.append("assignee IS NULL");
				}
				else {
					query.append("assignee = ?");
				}

				query.append(" AND ");

				if (status == null) {
					query.append("issuestatus IS NULL");
				}
				else {
					query.append("issuestatus = ?");
				}

				query.append(" ");

				query.append("ORDER BY ");

				query.append("updated DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (assigneeJiraUserId != null) {
					qPos.add(assigneeJiraUserId);
				}

				if (status != null) {
					qPos.add(status);
				}

				List<JIRAIssue> list = q.list();

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAIssue>)result;
		}
	}

	public List<JIRAIssue> findByP_AJUI_S(long projectId,
		String assigneeJiraUserId, String status, int start, int end)
		throws SystemException {
		return findByP_AJUI_S(projectId, assigneeJiraUserId, status, start,
			end, null);
	}

	public List<JIRAIssue> findByP_AJUI_S(long projectId,
		String assigneeJiraUserId, String status, int start, int end,
		OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "findByP_AJUI_S";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(projectId),
				
				assigneeJiraUserId,
				
				status,
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				query.append("project = ?");

				query.append(" AND ");

				if (assigneeJiraUserId == null) {
					query.append("assignee IS NULL");
				}
				else {
					query.append("assignee = ?");
				}

				query.append(" AND ");

				if (status == null) {
					query.append("issuestatus IS NULL");
				}
				else {
					query.append("issuestatus = ?");
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

				qPos.add(projectId);

				if (assigneeJiraUserId != null) {
					qPos.add(assigneeJiraUserId);
				}

				if (status != null) {
					qPos.add(status);
				}

				List<JIRAIssue> list = (List<JIRAIssue>)QueryUtil.list(q,
						getDialect(), start, end);

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAIssue>)result;
		}
	}

	public JIRAIssue findByP_AJUI_S_First(long projectId,
		String assigneeJiraUserId, String status, OrderByComparator obc)
		throws NoSuchJIRAIssueException, SystemException {
		List<JIRAIssue> list = findByP_AJUI_S(projectId, assigneeJiraUserId,
				status, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No JIRAIssue exists with the key {");

			msg.append("projectId=" + projectId);

			msg.append(", ");
			msg.append("assigneeJiraUserId=" + assigneeJiraUserId);

			msg.append(", ");
			msg.append("status=" + status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAIssue findByP_AJUI_S_Last(long projectId,
		String assigneeJiraUserId, String status, OrderByComparator obc)
		throws NoSuchJIRAIssueException, SystemException {
		int count = countByP_AJUI_S(projectId, assigneeJiraUserId, status);

		List<JIRAIssue> list = findByP_AJUI_S(projectId, assigneeJiraUserId,
				status, count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No JIRAIssue exists with the key {");

			msg.append("projectId=" + projectId);

			msg.append(", ");
			msg.append("assigneeJiraUserId=" + assigneeJiraUserId);

			msg.append(", ");
			msg.append("status=" + status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAIssueException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAIssue[] findByP_AJUI_S_PrevAndNext(long jiraIssueId,
		long projectId, String assigneeJiraUserId, String status,
		OrderByComparator obc) throws NoSuchJIRAIssueException, SystemException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		int count = countByP_AJUI_S(projectId, assigneeJiraUserId, status);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

			query.append("project = ?");

			query.append(" AND ");

			if (assigneeJiraUserId == null) {
				query.append("assignee IS NULL");
			}
			else {
				query.append("assignee = ?");
			}

			query.append(" AND ");

			if (status == null) {
				query.append("issuestatus IS NULL");
			}
			else {
				query.append("issuestatus = ?");
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

			qPos.add(projectId);

			if (assigneeJiraUserId != null) {
				qPos.add(assigneeJiraUserId);
			}

			if (status != null) {
				qPos.add(status);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					jiraIssue);

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = (JIRAIssue)objArray[0];
			array[1] = (JIRAIssue)objArray[1];
			array[2] = (JIRAIssue)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			dynamicQuery.compile(session);

			return dynamicQuery.list();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			dynamicQuery.setLimit(start, end);

			dynamicQuery.compile(session);

			return dynamicQuery.list();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<JIRAIssue> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<JIRAIssue> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<JIRAIssue> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "findAll";
		String[] finderParams = new String[] {
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wol.model.JIRAIssue ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("updated DESC");
				}

				Query q = session.createQuery(query.toString());

				List<JIRAIssue> list = (List<JIRAIssue>)QueryUtil.list(q,
						getDialect(), start, end);

				if (obc == null) {
					Collections.sort(list);
				}

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<JIRAIssue>)result;
		}
	}

	public void removeByProjectId(long projectId) throws SystemException {
		for (JIRAIssue jiraIssue : findByProjectId(projectId)) {
			remove(jiraIssue);
		}
	}

	public void removeByKey(String key)
		throws NoSuchJIRAIssueException, SystemException {
		JIRAIssue jiraIssue = findByKey(key);

		remove(jiraIssue);
	}

	public void removeByReporterJiraUserId(String reporterJiraUserId)
		throws SystemException {
		for (JIRAIssue jiraIssue : findByReporterJiraUserId(reporterJiraUserId)) {
			remove(jiraIssue);
		}
	}

	public void removeByAssigneeJiraUserId(String assigneeJiraUserId)
		throws SystemException {
		for (JIRAIssue jiraIssue : findByAssigneeJiraUserId(assigneeJiraUserId)) {
			remove(jiraIssue);
		}
	}

	public void removeByMD_P(Date modifiedDate, long projectId)
		throws SystemException {
		for (JIRAIssue jiraIssue : findByMD_P(modifiedDate, projectId)) {
			remove(jiraIssue);
		}
	}

	public void removeByP_RJUI(long projectId, String reporterJiraUserId)
		throws SystemException {
		for (JIRAIssue jiraIssue : findByP_RJUI(projectId, reporterJiraUserId)) {
			remove(jiraIssue);
		}
	}

	public void removeByP_AJUI(long projectId, String assigneeJiraUserId)
		throws SystemException {
		for (JIRAIssue jiraIssue : findByP_AJUI(projectId, assigneeJiraUserId)) {
			remove(jiraIssue);
		}
	}

	public void removeByMD_P_RJUI(Date modifiedDate, long projectId,
		String reporterJiraUserId) throws SystemException {
		for (JIRAIssue jiraIssue : findByMD_P_RJUI(modifiedDate, projectId,
				reporterJiraUserId)) {
			remove(jiraIssue);
		}
	}

	public void removeByMD_P_AJUI(Date modifiedDate, long projectId,
		String assigneeJiraUserId) throws SystemException {
		for (JIRAIssue jiraIssue : findByMD_P_AJUI(modifiedDate, projectId,
				assigneeJiraUserId)) {
			remove(jiraIssue);
		}
	}

	public void removeByP_RJUI_S(long projectId, String reporterJiraUserId,
		String status) throws SystemException {
		for (JIRAIssue jiraIssue : findByP_RJUI_S(projectId,
				reporterJiraUserId, status)) {
			remove(jiraIssue);
		}
	}

	public void removeByP_AJUI_S(long projectId, String assigneeJiraUserId,
		String status) throws SystemException {
		for (JIRAIssue jiraIssue : findByP_AJUI_S(projectId,
				assigneeJiraUserId, status)) {
			remove(jiraIssue);
		}
	}

	public void removeAll() throws SystemException {
		for (JIRAIssue jiraIssue : findAll()) {
			remove(jiraIssue);
		}
	}

	public int countByProjectId(long projectId) throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "countByProjectId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(projectId) };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				query.append("project = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return ((Long)result).intValue();
		}
	}

	public int countByKey(String key) throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "countByKey";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { key };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				if (key == null) {
					query.append("pkey IS NULL");
				}
				else {
					query.append("pkey = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (key != null) {
					qPos.add(key);
				}

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return ((Long)result).intValue();
		}
	}

	public int countByReporterJiraUserId(String reporterJiraUserId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "countByReporterJiraUserId";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { reporterJiraUserId };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				if (reporterJiraUserId == null) {
					query.append("reporter IS NULL");
				}
				else {
					query.append("reporter = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (reporterJiraUserId != null) {
					qPos.add(reporterJiraUserId);
				}

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return ((Long)result).intValue();
		}
	}

	public int countByAssigneeJiraUserId(String assigneeJiraUserId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "countByAssigneeJiraUserId";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { assigneeJiraUserId };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				if (assigneeJiraUserId == null) {
					query.append("assignee IS NULL");
				}
				else {
					query.append("assignee = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (assigneeJiraUserId != null) {
					qPos.add(assigneeJiraUserId);
				}

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return ((Long)result).intValue();
		}
	}

	public int countByMD_P(Date modifiedDate, long projectId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "countByMD_P";
		String[] finderParams = new String[] {
				Date.class.getName(), Long.class.getName()
			};
		Object[] finderArgs = new Object[] { modifiedDate, new Long(projectId) };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				if (modifiedDate == null) {
					query.append("updated > null");
				}
				else {
					query.append("updated > ?");
				}

				query.append(" AND ");

				query.append("project = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				qPos.add(projectId);

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return ((Long)result).intValue();
		}
	}

	public int countByP_RJUI(long projectId, String reporterJiraUserId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "countByP_RJUI";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(projectId),
				
				reporterJiraUserId
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				query.append("project = ?");

				query.append(" AND ");

				if (reporterJiraUserId == null) {
					query.append("reporter IS NULL");
				}
				else {
					query.append("reporter = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (reporterJiraUserId != null) {
					qPos.add(reporterJiraUserId);
				}

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return ((Long)result).intValue();
		}
	}

	public int countByP_AJUI(long projectId, String assigneeJiraUserId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "countByP_AJUI";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(projectId),
				
				assigneeJiraUserId
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				query.append("project = ?");

				query.append(" AND ");

				if (assigneeJiraUserId == null) {
					query.append("assignee IS NULL");
				}
				else {
					query.append("assignee = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (assigneeJiraUserId != null) {
					qPos.add(assigneeJiraUserId);
				}

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return ((Long)result).intValue();
		}
	}

	public int countByMD_P_RJUI(Date modifiedDate, long projectId,
		String reporterJiraUserId) throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "countByMD_P_RJUI";
		String[] finderParams = new String[] {
				Date.class.getName(), Long.class.getName(),
				String.class.getName()
			};
		Object[] finderArgs = new Object[] {
				modifiedDate, new Long(projectId),
				
				reporterJiraUserId
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				if (modifiedDate == null) {
					query.append("updated > null");
				}
				else {
					query.append("updated > ?");
				}

				query.append(" AND ");

				query.append("project = ?");

				query.append(" AND ");

				if (reporterJiraUserId == null) {
					query.append("reporter IS NULL");
				}
				else {
					query.append("reporter = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				qPos.add(projectId);

				if (reporterJiraUserId != null) {
					qPos.add(reporterJiraUserId);
				}

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return ((Long)result).intValue();
		}
	}

	public int countByMD_P_AJUI(Date modifiedDate, long projectId,
		String assigneeJiraUserId) throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "countByMD_P_AJUI";
		String[] finderParams = new String[] {
				Date.class.getName(), Long.class.getName(),
				String.class.getName()
			};
		Object[] finderArgs = new Object[] {
				modifiedDate, new Long(projectId),
				
				assigneeJiraUserId
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				if (modifiedDate == null) {
					query.append("updated > null");
				}
				else {
					query.append("updated > ?");
				}

				query.append(" AND ");

				query.append("project = ?");

				query.append(" AND ");

				if (assigneeJiraUserId == null) {
					query.append("assignee IS NULL");
				}
				else {
					query.append("assignee = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (modifiedDate != null) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				qPos.add(projectId);

				if (assigneeJiraUserId != null) {
					qPos.add(assigneeJiraUserId);
				}

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return ((Long)result).intValue();
		}
	}

	public int countByP_RJUI_S(long projectId, String reporterJiraUserId,
		String status) throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "countByP_RJUI_S";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(projectId),
				
				reporterJiraUserId,
				
				status
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				query.append("project = ?");

				query.append(" AND ");

				if (reporterJiraUserId == null) {
					query.append("reporter IS NULL");
				}
				else {
					query.append("reporter = ?");
				}

				query.append(" AND ");

				if (status == null) {
					query.append("issuestatus IS NULL");
				}
				else {
					query.append("issuestatus = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (reporterJiraUserId != null) {
					qPos.add(reporterJiraUserId);
				}

				if (status != null) {
					qPos.add(status);
				}

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return ((Long)result).intValue();
		}
	}

	public int countByP_AJUI_S(long projectId, String assigneeJiraUserId,
		String status) throws SystemException {
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "countByP_AJUI_S";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(projectId),
				
				assigneeJiraUserId,
				
				status
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.wol.model.JIRAIssue WHERE ");

				query.append("project = ?");

				query.append(" AND ");

				if (assigneeJiraUserId == null) {
					query.append("assignee IS NULL");
				}
				else {
					query.append("assignee = ?");
				}

				query.append(" AND ");

				if (status == null) {
					query.append("issuestatus IS NULL");
				}
				else {
					query.append("issuestatus = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (assigneeJiraUserId != null) {
					qPos.add(assigneeJiraUserId);
				}

				if (status != null) {
					qPos.add(status);
				}

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw processException(e);
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
		boolean finderClassNameCacheEnabled = JIRAIssueModelImpl.CACHE_ENABLED;
		String finderClassName = JIRAIssue.class.getName();
		String finderMethodName = "countAll";
		String[] finderParams = new String[] {  };
		Object[] finderArgs = new Object[] {  };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(
						"SELECT COUNT(*) FROM com.liferay.wol.model.JIRAIssue");

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return ((Long)result).intValue();
		}
	}

	public void registerListener(ModelListener listener) {
		List<ModelListener> listeners = ListUtil.fromArray(_listeners);

		listeners.add(listener);

		_listeners = listeners.toArray(new ModelListener[listeners.size()]);
	}

	public void unregisterListener(ModelListener listener) {
		List<ModelListener> listeners = ListUtil.fromArray(_listeners);

		listeners.remove(listener);

		_listeners = listeners.toArray(new ModelListener[listeners.size()]);
	}

	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.wol.model.JIRAIssue")));

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

	private static Log _log = LogFactory.getLog(JIRAIssuePersistenceImpl.class);
	private ModelListener[] _listeners = new ModelListener[0];
}