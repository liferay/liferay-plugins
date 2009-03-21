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

package com.liferay.tms.service.persistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.tms.NoSuchTasksEntryException;
import com.liferay.tms.model.TasksEntry;
import com.liferay.tms.model.impl.TasksEntryImpl;
import com.liferay.tms.model.impl.TasksEntryModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="TasksEntryPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class TasksEntryPersistenceImpl extends BasePersistenceImpl
	implements TasksEntryPersistence {
	public TasksEntry create(long tasksEntryId) {
		TasksEntry tasksEntry = new TasksEntryImpl();

		tasksEntry.setNew(true);
		tasksEntry.setPrimaryKey(tasksEntryId);

		return tasksEntry;
	}

	public TasksEntry remove(long tasksEntryId)
		throws NoSuchTasksEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			TasksEntry tasksEntry = (TasksEntry)session.get(TasksEntryImpl.class,
					new Long(tasksEntryId));

			if (tasksEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No TasksEntry exists with the primary key " +
						tasksEntryId);
				}

				throw new NoSuchTasksEntryException(
					"No TasksEntry exists with the primary key " +
					tasksEntryId);
			}

			return remove(tasksEntry);
		}
		catch (NoSuchTasksEntryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public TasksEntry remove(TasksEntry tasksEntry) throws SystemException {
		for (ModelListener<TasksEntry> listener : listeners) {
			listener.onBeforeRemove(tasksEntry);
		}

		tasksEntry = removeImpl(tasksEntry);

		for (ModelListener<TasksEntry> listener : listeners) {
			listener.onAfterRemove(tasksEntry);
		}

		return tasksEntry;
	}

	protected TasksEntry removeImpl(TasksEntry tasksEntry)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(TasksEntryImpl.class,
						tasksEntry.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(tasksEntry);

			session.flush();

			return tasksEntry;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(TasksEntry.class.getName());
		}
	}

	public TasksEntry update(TasksEntry tasksEntry) throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(TasksEntry tasksEntry) method. Use update(TasksEntry tasksEntry, boolean merge) instead.");
		}

		return update(tasksEntry, false);
	}

	public TasksEntry update(TasksEntry tasksEntry, boolean merge)
		throws SystemException {
		boolean isNew = tasksEntry.isNew();

		for (ModelListener<TasksEntry> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(tasksEntry);
			}
			else {
				listener.onBeforeUpdate(tasksEntry);
			}
		}

		tasksEntry = updateImpl(tasksEntry, merge);

		for (ModelListener<TasksEntry> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(tasksEntry);
			}
			else {
				listener.onAfterUpdate(tasksEntry);
			}
		}

		return tasksEntry;
	}

	public TasksEntry updateImpl(com.liferay.tms.model.TasksEntry tasksEntry,
		boolean merge) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, tasksEntry, merge);

			tasksEntry.setNew(false);

			return tasksEntry;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(TasksEntry.class.getName());
		}
	}

	public TasksEntry findByPrimaryKey(long tasksEntryId)
		throws NoSuchTasksEntryException, SystemException {
		TasksEntry tasksEntry = fetchByPrimaryKey(tasksEntryId);

		if (tasksEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No TasksEntry exists with the primary key " +
					tasksEntryId);
			}

			throw new NoSuchTasksEntryException(
				"No TasksEntry exists with the primary key " + tasksEntryId);
		}

		return tasksEntry;
	}

	public TasksEntry fetchByPrimaryKey(long tasksEntryId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (TasksEntry)session.get(TasksEntryImpl.class,
				new Long(tasksEntryId));
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<TasksEntry> findByGroupId(long groupId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = TasksEntryModelImpl.CACHE_ENABLED;
		String finderClassName = TasksEntry.class.getName();
		String finderMethodName = "findByGroupId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(groupId) };

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

				query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

				query.append("groupId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("priority DESC, ");
				query.append("createDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				List<TasksEntry> list = q.list();

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
			return (List<TasksEntry>)result;
		}
	}

	public List<TasksEntry> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	public List<TasksEntry> findByGroupId(long groupId, int start, int end,
		OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = TasksEntryModelImpl.CACHE_ENABLED;
		String finderClassName = TasksEntry.class.getName();
		String finderMethodName = "findByGroupId";
		String[] finderParams = new String[] {
				Long.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(groupId),
				
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

				query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

				query.append("groupId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("priority DESC, ");
					query.append("createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				List<TasksEntry> list = (List<TasksEntry>)QueryUtil.list(q,
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
			return (List<TasksEntry>)result;
		}
	}

	public TasksEntry findByGroupId_First(long groupId, OrderByComparator obc)
		throws NoSuchTasksEntryException, SystemException {
		List<TasksEntry> list = findByGroupId(groupId, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No TasksEntry exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public TasksEntry findByGroupId_Last(long groupId, OrderByComparator obc)
		throws NoSuchTasksEntryException, SystemException {
		int count = countByGroupId(groupId);

		List<TasksEntry> list = findByGroupId(groupId, count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No TasksEntry exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public TasksEntry[] findByGroupId_PrevAndNext(long tasksEntryId,
		long groupId, OrderByComparator obc)
		throws NoSuchTasksEntryException, SystemException {
		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		int count = countByGroupId(groupId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

			query.append("groupId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("priority DESC, ");
				query.append("createDate DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					tasksEntry);

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = (TasksEntry)objArray[0];
			array[1] = (TasksEntry)objArray[1];
			array[2] = (TasksEntry)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<TasksEntry> findByUserId(long userId) throws SystemException {
		boolean finderClassNameCacheEnabled = TasksEntryModelImpl.CACHE_ENABLED;
		String finderClassName = TasksEntry.class.getName();
		String finderMethodName = "findByUserId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(userId) };

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

				query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

				query.append("userId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("priority DESC, ");
				query.append("createDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				List<TasksEntry> list = q.list();

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
			return (List<TasksEntry>)result;
		}
	}

	public List<TasksEntry> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	public List<TasksEntry> findByUserId(long userId, int start, int end,
		OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = TasksEntryModelImpl.CACHE_ENABLED;
		String finderClassName = TasksEntry.class.getName();
		String finderMethodName = "findByUserId";
		String[] finderParams = new String[] {
				Long.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(userId),
				
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

				query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

				query.append("userId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("priority DESC, ");
					query.append("createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				List<TasksEntry> list = (List<TasksEntry>)QueryUtil.list(q,
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
			return (List<TasksEntry>)result;
		}
	}

	public TasksEntry findByUserId_First(long userId, OrderByComparator obc)
		throws NoSuchTasksEntryException, SystemException {
		List<TasksEntry> list = findByUserId(userId, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No TasksEntry exists with the key {");

			msg.append("userId=" + userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public TasksEntry findByUserId_Last(long userId, OrderByComparator obc)
		throws NoSuchTasksEntryException, SystemException {
		int count = countByUserId(userId);

		List<TasksEntry> list = findByUserId(userId, count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No TasksEntry exists with the key {");

			msg.append("userId=" + userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public TasksEntry[] findByUserId_PrevAndNext(long tasksEntryId,
		long userId, OrderByComparator obc)
		throws NoSuchTasksEntryException, SystemException {
		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		int count = countByUserId(userId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

			query.append("userId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("priority DESC, ");
				query.append("createDate DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					tasksEntry);

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = (TasksEntry)objArray[0];
			array[1] = (TasksEntry)objArray[1];
			array[2] = (TasksEntry)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<TasksEntry> findByAssigneeUserId(long assigneeUserId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = TasksEntryModelImpl.CACHE_ENABLED;
		String finderClassName = TasksEntry.class.getName();
		String finderMethodName = "findByAssigneeUserId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(assigneeUserId) };

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

				query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

				query.append("assigneeUserId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("priority DESC, ");
				query.append("createDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeUserId);

				List<TasksEntry> list = q.list();

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
			return (List<TasksEntry>)result;
		}
	}

	public List<TasksEntry> findByAssigneeUserId(long assigneeUserId,
		int start, int end) throws SystemException {
		return findByAssigneeUserId(assigneeUserId, start, end, null);
	}

	public List<TasksEntry> findByAssigneeUserId(long assigneeUserId,
		int start, int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = TasksEntryModelImpl.CACHE_ENABLED;
		String finderClassName = TasksEntry.class.getName();
		String finderMethodName = "findByAssigneeUserId";
		String[] finderParams = new String[] {
				Long.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(assigneeUserId),
				
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

				query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

				query.append("assigneeUserId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("priority DESC, ");
					query.append("createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeUserId);

				List<TasksEntry> list = (List<TasksEntry>)QueryUtil.list(q,
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
			return (List<TasksEntry>)result;
		}
	}

	public TasksEntry findByAssigneeUserId_First(long assigneeUserId,
		OrderByComparator obc)
		throws NoSuchTasksEntryException, SystemException {
		List<TasksEntry> list = findByAssigneeUserId(assigneeUserId, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No TasksEntry exists with the key {");

			msg.append("assigneeUserId=" + assigneeUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public TasksEntry findByAssigneeUserId_Last(long assigneeUserId,
		OrderByComparator obc)
		throws NoSuchTasksEntryException, SystemException {
		int count = countByAssigneeUserId(assigneeUserId);

		List<TasksEntry> list = findByAssigneeUserId(assigneeUserId, count - 1,
				count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No TasksEntry exists with the key {");

			msg.append("assigneeUserId=" + assigneeUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public TasksEntry[] findByAssigneeUserId_PrevAndNext(long tasksEntryId,
		long assigneeUserId, OrderByComparator obc)
		throws NoSuchTasksEntryException, SystemException {
		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		int count = countByAssigneeUserId(assigneeUserId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

			query.append("assigneeUserId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("priority DESC, ");
				query.append("createDate DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(assigneeUserId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					tasksEntry);

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = (TasksEntry)objArray[0];
			array[1] = (TasksEntry)objArray[1];
			array[2] = (TasksEntry)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<TasksEntry> findByResolverUserId(long resolverUserId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = TasksEntryModelImpl.CACHE_ENABLED;
		String finderClassName = TasksEntry.class.getName();
		String finderMethodName = "findByResolverUserId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(resolverUserId) };

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

				query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

				query.append("resolverUserId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("priority DESC, ");
				query.append("createDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resolverUserId);

				List<TasksEntry> list = q.list();

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
			return (List<TasksEntry>)result;
		}
	}

	public List<TasksEntry> findByResolverUserId(long resolverUserId,
		int start, int end) throws SystemException {
		return findByResolverUserId(resolverUserId, start, end, null);
	}

	public List<TasksEntry> findByResolverUserId(long resolverUserId,
		int start, int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = TasksEntryModelImpl.CACHE_ENABLED;
		String finderClassName = TasksEntry.class.getName();
		String finderMethodName = "findByResolverUserId";
		String[] finderParams = new String[] {
				Long.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(resolverUserId),
				
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

				query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

				query.append("resolverUserId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("priority DESC, ");
					query.append("createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resolverUserId);

				List<TasksEntry> list = (List<TasksEntry>)QueryUtil.list(q,
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
			return (List<TasksEntry>)result;
		}
	}

	public TasksEntry findByResolverUserId_First(long resolverUserId,
		OrderByComparator obc)
		throws NoSuchTasksEntryException, SystemException {
		List<TasksEntry> list = findByResolverUserId(resolverUserId, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No TasksEntry exists with the key {");

			msg.append("resolverUserId=" + resolverUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public TasksEntry findByResolverUserId_Last(long resolverUserId,
		OrderByComparator obc)
		throws NoSuchTasksEntryException, SystemException {
		int count = countByResolverUserId(resolverUserId);

		List<TasksEntry> list = findByResolverUserId(resolverUserId, count - 1,
				count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No TasksEntry exists with the key {");

			msg.append("resolverUserId=" + resolverUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public TasksEntry[] findByResolverUserId_PrevAndNext(long tasksEntryId,
		long resolverUserId, OrderByComparator obc)
		throws NoSuchTasksEntryException, SystemException {
		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		int count = countByResolverUserId(resolverUserId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

			query.append("resolverUserId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("priority DESC, ");
				query.append("createDate DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resolverUserId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					tasksEntry);

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = (TasksEntry)objArray[0];
			array[1] = (TasksEntry)objArray[1];
			array[2] = (TasksEntry)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<TasksEntry> findByG_U(long groupId, long userId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = TasksEntryModelImpl.CACHE_ENABLED;
		String finderClassName = TasksEntry.class.getName();
		String finderMethodName = "findByG_U";
		String[] finderParams = new String[] {
				Long.class.getName(), Long.class.getName()
			};
		Object[] finderArgs = new Object[] { new Long(groupId), new Long(userId) };

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

				query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				query.append("userId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("priority DESC, ");
				query.append("createDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				List<TasksEntry> list = q.list();

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
			return (List<TasksEntry>)result;
		}
	}

	public List<TasksEntry> findByG_U(long groupId, long userId, int start,
		int end) throws SystemException {
		return findByG_U(groupId, userId, start, end, null);
	}

	public List<TasksEntry> findByG_U(long groupId, long userId, int start,
		int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = TasksEntryModelImpl.CACHE_ENABLED;
		String finderClassName = TasksEntry.class.getName();
		String finderMethodName = "findByG_U";
		String[] finderParams = new String[] {
				Long.class.getName(), Long.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(groupId), new Long(userId),
				
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

				query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				query.append("userId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("priority DESC, ");
					query.append("createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				List<TasksEntry> list = (List<TasksEntry>)QueryUtil.list(q,
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
			return (List<TasksEntry>)result;
		}
	}

	public TasksEntry findByG_U_First(long groupId, long userId,
		OrderByComparator obc)
		throws NoSuchTasksEntryException, SystemException {
		List<TasksEntry> list = findByG_U(groupId, userId, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No TasksEntry exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(", ");
			msg.append("userId=" + userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public TasksEntry findByG_U_Last(long groupId, long userId,
		OrderByComparator obc)
		throws NoSuchTasksEntryException, SystemException {
		int count = countByG_U(groupId, userId);

		List<TasksEntry> list = findByG_U(groupId, userId, count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No TasksEntry exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(", ");
			msg.append("userId=" + userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public TasksEntry[] findByG_U_PrevAndNext(long tasksEntryId, long groupId,
		long userId, OrderByComparator obc)
		throws NoSuchTasksEntryException, SystemException {
		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		int count = countByG_U(groupId, userId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

			query.append("groupId = ?");

			query.append(" AND ");

			query.append("userId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("priority DESC, ");
				query.append("createDate DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(userId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					tasksEntry);

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = (TasksEntry)objArray[0];
			array[1] = (TasksEntry)objArray[1];
			array[2] = (TasksEntry)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<TasksEntry> findByG_A(long groupId, long assigneeUserId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = TasksEntryModelImpl.CACHE_ENABLED;
		String finderClassName = TasksEntry.class.getName();
		String finderMethodName = "findByG_A";
		String[] finderParams = new String[] {
				Long.class.getName(), Long.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(groupId), new Long(assigneeUserId)
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

				query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				query.append("assigneeUserId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("priority DESC, ");
				query.append("createDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(assigneeUserId);

				List<TasksEntry> list = q.list();

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
			return (List<TasksEntry>)result;
		}
	}

	public List<TasksEntry> findByG_A(long groupId, long assigneeUserId,
		int start, int end) throws SystemException {
		return findByG_A(groupId, assigneeUserId, start, end, null);
	}

	public List<TasksEntry> findByG_A(long groupId, long assigneeUserId,
		int start, int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = TasksEntryModelImpl.CACHE_ENABLED;
		String finderClassName = TasksEntry.class.getName();
		String finderMethodName = "findByG_A";
		String[] finderParams = new String[] {
				Long.class.getName(), Long.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(groupId), new Long(assigneeUserId),
				
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

				query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				query.append("assigneeUserId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("priority DESC, ");
					query.append("createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(assigneeUserId);

				List<TasksEntry> list = (List<TasksEntry>)QueryUtil.list(q,
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
			return (List<TasksEntry>)result;
		}
	}

	public TasksEntry findByG_A_First(long groupId, long assigneeUserId,
		OrderByComparator obc)
		throws NoSuchTasksEntryException, SystemException {
		List<TasksEntry> list = findByG_A(groupId, assigneeUserId, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No TasksEntry exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(", ");
			msg.append("assigneeUserId=" + assigneeUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public TasksEntry findByG_A_Last(long groupId, long assigneeUserId,
		OrderByComparator obc)
		throws NoSuchTasksEntryException, SystemException {
		int count = countByG_A(groupId, assigneeUserId);

		List<TasksEntry> list = findByG_A(groupId, assigneeUserId, count - 1,
				count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No TasksEntry exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(", ");
			msg.append("assigneeUserId=" + assigneeUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public TasksEntry[] findByG_A_PrevAndNext(long tasksEntryId, long groupId,
		long assigneeUserId, OrderByComparator obc)
		throws NoSuchTasksEntryException, SystemException {
		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		int count = countByG_A(groupId, assigneeUserId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

			query.append("groupId = ?");

			query.append(" AND ");

			query.append("assigneeUserId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("priority DESC, ");
				query.append("createDate DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(assigneeUserId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					tasksEntry);

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = (TasksEntry)objArray[0];
			array[1] = (TasksEntry)objArray[1];
			array[2] = (TasksEntry)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<TasksEntry> findByG_R(long groupId, long resolverUserId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = TasksEntryModelImpl.CACHE_ENABLED;
		String finderClassName = TasksEntry.class.getName();
		String finderMethodName = "findByG_R";
		String[] finderParams = new String[] {
				Long.class.getName(), Long.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(groupId), new Long(resolverUserId)
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

				query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				query.append("resolverUserId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("priority DESC, ");
				query.append("createDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(resolverUserId);

				List<TasksEntry> list = q.list();

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
			return (List<TasksEntry>)result;
		}
	}

	public List<TasksEntry> findByG_R(long groupId, long resolverUserId,
		int start, int end) throws SystemException {
		return findByG_R(groupId, resolverUserId, start, end, null);
	}

	public List<TasksEntry> findByG_R(long groupId, long resolverUserId,
		int start, int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = TasksEntryModelImpl.CACHE_ENABLED;
		String finderClassName = TasksEntry.class.getName();
		String finderMethodName = "findByG_R";
		String[] finderParams = new String[] {
				Long.class.getName(), Long.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(groupId), new Long(resolverUserId),
				
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

				query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				query.append("resolverUserId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("priority DESC, ");
					query.append("createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(resolverUserId);

				List<TasksEntry> list = (List<TasksEntry>)QueryUtil.list(q,
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
			return (List<TasksEntry>)result;
		}
	}

	public TasksEntry findByG_R_First(long groupId, long resolverUserId,
		OrderByComparator obc)
		throws NoSuchTasksEntryException, SystemException {
		List<TasksEntry> list = findByG_R(groupId, resolverUserId, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No TasksEntry exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(", ");
			msg.append("resolverUserId=" + resolverUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public TasksEntry findByG_R_Last(long groupId, long resolverUserId,
		OrderByComparator obc)
		throws NoSuchTasksEntryException, SystemException {
		int count = countByG_R(groupId, resolverUserId);

		List<TasksEntry> list = findByG_R(groupId, resolverUserId, count - 1,
				count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No TasksEntry exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(", ");
			msg.append("resolverUserId=" + resolverUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTasksEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public TasksEntry[] findByG_R_PrevAndNext(long tasksEntryId, long groupId,
		long resolverUserId, OrderByComparator obc)
		throws NoSuchTasksEntryException, SystemException {
		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		int count = countByG_R(groupId, resolverUserId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

			query.append("groupId = ?");

			query.append(" AND ");

			query.append("resolverUserId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("priority DESC, ");
				query.append("createDate DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(resolverUserId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					tasksEntry);

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = (TasksEntry)objArray[0];
			array[1] = (TasksEntry)objArray[1];
			array[2] = (TasksEntry)objArray[2];

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

	public List<TasksEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<TasksEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<TasksEntry> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = TasksEntryModelImpl.CACHE_ENABLED;
		String finderClassName = TasksEntry.class.getName();
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

				query.append("FROM com.liferay.tms.model.TasksEntry ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("priority DESC, ");
					query.append("createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				List<TasksEntry> list = null;

				if (obc == null) {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
							start, end);
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
			return (List<TasksEntry>)result;
		}
	}

	public void removeByGroupId(long groupId) throws SystemException {
		for (TasksEntry tasksEntry : findByGroupId(groupId)) {
			remove(tasksEntry);
		}
	}

	public void removeByUserId(long userId) throws SystemException {
		for (TasksEntry tasksEntry : findByUserId(userId)) {
			remove(tasksEntry);
		}
	}

	public void removeByAssigneeUserId(long assigneeUserId)
		throws SystemException {
		for (TasksEntry tasksEntry : findByAssigneeUserId(assigneeUserId)) {
			remove(tasksEntry);
		}
	}

	public void removeByResolverUserId(long resolverUserId)
		throws SystemException {
		for (TasksEntry tasksEntry : findByResolverUserId(resolverUserId)) {
			remove(tasksEntry);
		}
	}

	public void removeByG_U(long groupId, long userId)
		throws SystemException {
		for (TasksEntry tasksEntry : findByG_U(groupId, userId)) {
			remove(tasksEntry);
		}
	}

	public void removeByG_A(long groupId, long assigneeUserId)
		throws SystemException {
		for (TasksEntry tasksEntry : findByG_A(groupId, assigneeUserId)) {
			remove(tasksEntry);
		}
	}

	public void removeByG_R(long groupId, long resolverUserId)
		throws SystemException {
		for (TasksEntry tasksEntry : findByG_R(groupId, resolverUserId)) {
			remove(tasksEntry);
		}
	}

	public void removeAll() throws SystemException {
		for (TasksEntry tasksEntry : findAll()) {
			remove(tasksEntry);
		}
	}

	public int countByGroupId(long groupId) throws SystemException {
		boolean finderClassNameCacheEnabled = TasksEntryModelImpl.CACHE_ENABLED;
		String finderClassName = TasksEntry.class.getName();
		String finderMethodName = "countByGroupId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(groupId) };

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
				query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

				query.append("groupId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

	public int countByUserId(long userId) throws SystemException {
		boolean finderClassNameCacheEnabled = TasksEntryModelImpl.CACHE_ENABLED;
		String finderClassName = TasksEntry.class.getName();
		String finderMethodName = "countByUserId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(userId) };

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
				query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

				query.append("userId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

	public int countByAssigneeUserId(long assigneeUserId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = TasksEntryModelImpl.CACHE_ENABLED;
		String finderClassName = TasksEntry.class.getName();
		String finderMethodName = "countByAssigneeUserId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(assigneeUserId) };

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
				query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

				query.append("assigneeUserId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeUserId);

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

	public int countByResolverUserId(long resolverUserId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = TasksEntryModelImpl.CACHE_ENABLED;
		String finderClassName = TasksEntry.class.getName();
		String finderMethodName = "countByResolverUserId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(resolverUserId) };

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
				query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

				query.append("resolverUserId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resolverUserId);

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

	public int countByG_U(long groupId, long userId) throws SystemException {
		boolean finderClassNameCacheEnabled = TasksEntryModelImpl.CACHE_ENABLED;
		String finderClassName = TasksEntry.class.getName();
		String finderMethodName = "countByG_U";
		String[] finderParams = new String[] {
				Long.class.getName(), Long.class.getName()
			};
		Object[] finderArgs = new Object[] { new Long(groupId), new Long(userId) };

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
				query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				query.append("userId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

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

	public int countByG_A(long groupId, long assigneeUserId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = TasksEntryModelImpl.CACHE_ENABLED;
		String finderClassName = TasksEntry.class.getName();
		String finderMethodName = "countByG_A";
		String[] finderParams = new String[] {
				Long.class.getName(), Long.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(groupId), new Long(assigneeUserId)
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
				query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				query.append("assigneeUserId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(assigneeUserId);

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

	public int countByG_R(long groupId, long resolverUserId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = TasksEntryModelImpl.CACHE_ENABLED;
		String finderClassName = TasksEntry.class.getName();
		String finderMethodName = "countByG_R";
		String[] finderParams = new String[] {
				Long.class.getName(), Long.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(groupId), new Long(resolverUserId)
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
				query.append("FROM com.liferay.tms.model.TasksEntry WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				query.append("resolverUserId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(resolverUserId);

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
		boolean finderClassNameCacheEnabled = TasksEntryModelImpl.CACHE_ENABLED;
		String finderClassName = TasksEntry.class.getName();
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
						"SELECT COUNT(*) FROM com.liferay.tms.model.TasksEntry");

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

	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.tms.model.TasksEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<TasksEntry>> listenersList = new ArrayList<ModelListener<TasksEntry>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<TasksEntry>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(name = "com.liferay.tms.service.persistence.TasksEntryPersistence.impl")
	protected com.liferay.tms.service.persistence.TasksEntryPersistence tasksEntryPersistence;
	private static Log _log = LogFactoryUtil.getLog(TasksEntryPersistenceImpl.class);
}