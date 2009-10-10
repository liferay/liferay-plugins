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

package com.liferay.portal.workflow.edoras.service.persistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
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
import com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException;
import com.liferay.portal.workflow.edoras.model.WorkflowTask;
import com.liferay.portal.workflow.edoras.model.impl.WorkflowTaskImpl;
import com.liferay.portal.workflow.edoras.model.impl.WorkflowTaskModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="WorkflowTaskPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowTaskPersistenceImpl extends BasePersistenceImpl
	implements WorkflowTaskPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = WorkflowTaskImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_WORKFLOWINSTANCEID = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByWorkflowInstanceId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_WORKFLOWINSTANCEID = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByWorkflowInstanceId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_WORKFLOWINSTANCEID = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByWorkflowInstanceId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_W_S = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByW_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_W_S = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByW_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_W_S = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByW_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(WorkflowTask workflowTask) {
		EntityCacheUtil.putResult(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskImpl.class, workflowTask.getPrimaryKey(), workflowTask);
	}

	public void cacheResult(List<WorkflowTask> workflowTasks) {
		for (WorkflowTask workflowTask : workflowTasks) {
			if (EntityCacheUtil.getResult(
						WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
						WorkflowTaskImpl.class, workflowTask.getPrimaryKey(),
						this) == null) {
				cacheResult(workflowTask);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(WorkflowTaskImpl.class.getName());
		EntityCacheUtil.clearCache(WorkflowTaskImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public WorkflowTask create(long workflowTaskId) {
		WorkflowTask workflowTask = new WorkflowTaskImpl();

		workflowTask.setNew(true);
		workflowTask.setPrimaryKey(workflowTaskId);

		return workflowTask;
	}

	public WorkflowTask remove(long workflowTaskId)
		throws NoSuchWorkflowTaskException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WorkflowTask workflowTask = (WorkflowTask)session.get(WorkflowTaskImpl.class,
					new Long(workflowTaskId));

			if (workflowTask == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No WorkflowTask exists with the primary key " +
						workflowTaskId);
				}

				throw new NoSuchWorkflowTaskException(
					"No WorkflowTask exists with the primary key " +
					workflowTaskId);
			}

			return remove(workflowTask);
		}
		catch (NoSuchWorkflowTaskException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public WorkflowTask remove(WorkflowTask workflowTask)
		throws SystemException {
		for (ModelListener<WorkflowTask> listener : listeners) {
			listener.onBeforeRemove(workflowTask);
		}

		workflowTask = removeImpl(workflowTask);

		for (ModelListener<WorkflowTask> listener : listeners) {
			listener.onAfterRemove(workflowTask);
		}

		return workflowTask;
	}

	protected WorkflowTask removeImpl(WorkflowTask workflowTask)
		throws SystemException {
		workflowTask = toUnwrappedModel(workflowTask);

		Session session = null;

		try {
			session = openSession();

			if (workflowTask.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(WorkflowTaskImpl.class,
						workflowTask.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(workflowTask);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskImpl.class, workflowTask.getPrimaryKey());

		return workflowTask;
	}

	public WorkflowTask update(WorkflowTask workflowTask)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(WorkflowTask workflowTask) method. Use update(WorkflowTask workflowTask, boolean merge) instead.");
		}

		return update(workflowTask, false);
	}

	public WorkflowTask update(WorkflowTask workflowTask, boolean merge)
		throws SystemException {
		boolean isNew = workflowTask.isNew();

		for (ModelListener<WorkflowTask> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(workflowTask);
			}
			else {
				listener.onBeforeUpdate(workflowTask);
			}
		}

		workflowTask = updateImpl(workflowTask, merge);

		for (ModelListener<WorkflowTask> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(workflowTask);
			}
			else {
				listener.onAfterUpdate(workflowTask);
			}
		}

		return workflowTask;
	}

	public WorkflowTask updateImpl(
		com.liferay.portal.workflow.edoras.model.WorkflowTask workflowTask,
		boolean merge) throws SystemException {
		workflowTask = toUnwrappedModel(workflowTask);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, workflowTask, merge);

			workflowTask.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskImpl.class, workflowTask.getPrimaryKey(), workflowTask);

		return workflowTask;
	}

	protected WorkflowTask toUnwrappedModel(WorkflowTask workflowTask) {
		if (workflowTask instanceof WorkflowTaskImpl) {
			return workflowTask;
		}

		WorkflowTaskImpl workflowTaskImpl = new WorkflowTaskImpl();

		workflowTaskImpl.setNew(workflowTask.isNew());
		workflowTaskImpl.setPrimaryKey(workflowTask.getPrimaryKey());

		workflowTaskImpl.setWorkflowTaskId(workflowTask.getWorkflowTaskId());
		workflowTaskImpl.setCompanyId(workflowTask.getCompanyId());
		workflowTaskImpl.setCreateDate(workflowTask.getCreateDate());
		workflowTaskImpl.setFriendlyId(workflowTask.getFriendlyId());
		workflowTaskImpl.setWorkflowDefinitionId(workflowTask.getWorkflowDefinitionId());
		workflowTaskImpl.setWorkflowInstanceId(workflowTask.getWorkflowInstanceId());
		workflowTaskImpl.setMetaName(workflowTask.getMetaName());
		workflowTaskImpl.setRelation(workflowTask.getRelation());
		workflowTaskImpl.setDueDate(workflowTask.getDueDate());
		workflowTaskImpl.setCompletionDate(workflowTask.getCompletionDate());
		workflowTaskImpl.setState(workflowTask.getState());
		workflowTaskImpl.setPriority(workflowTask.getPriority());
		workflowTaskImpl.setAssigneeUserId(workflowTask.getAssigneeUserId());
		workflowTaskImpl.setAssigneeUserName(workflowTask.getAssigneeUserName());
		workflowTaskImpl.setRoleId(workflowTask.getRoleId());

		return workflowTaskImpl;
	}

	public WorkflowTask findByPrimaryKey(long workflowTaskId)
		throws NoSuchWorkflowTaskException, SystemException {
		WorkflowTask workflowTask = fetchByPrimaryKey(workflowTaskId);

		if (workflowTask == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No WorkflowTask exists with the primary key " +
					workflowTaskId);
			}

			throw new NoSuchWorkflowTaskException(
				"No WorkflowTask exists with the primary key " +
				workflowTaskId);
		}

		return workflowTask;
	}

	public WorkflowTask fetchByPrimaryKey(long workflowTaskId)
		throws SystemException {
		WorkflowTask workflowTask = (WorkflowTask)EntityCacheUtil.getResult(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
				WorkflowTaskImpl.class, workflowTaskId, this);

		if (workflowTask == null) {
			Session session = null;

			try {
				session = openSession();

				workflowTask = (WorkflowTask)session.get(WorkflowTaskImpl.class,
						new Long(workflowTaskId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (workflowTask != null) {
					cacheResult(workflowTask);
				}

				closeSession(session);
			}
		}

		return workflowTask;
	}

	public List<WorkflowTask> findByWorkflowInstanceId(long workflowInstanceId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(workflowInstanceId) };

		List<WorkflowTask> list = (List<WorkflowTask>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_WORKFLOWINSTANCEID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowTask FROM WorkflowTask workflowTask WHERE ");

				query.append("workflowTask.workflowInstanceId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(workflowInstanceId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowTask>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_WORKFLOWINSTANCEID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WorkflowTask> findByWorkflowInstanceId(
		long workflowInstanceId, int start, int end) throws SystemException {
		return findByWorkflowInstanceId(workflowInstanceId, start, end, null);
	}

	public List<WorkflowTask> findByWorkflowInstanceId(
		long workflowInstanceId, int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(workflowInstanceId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WorkflowTask> list = (List<WorkflowTask>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_WORKFLOWINSTANCEID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowTask FROM WorkflowTask workflowTask WHERE ");

				query.append("workflowTask.workflowInstanceId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("workflowTask.");
						query.append(orderByFields[i]);

						if (obc.isAscending()) {
							query.append(" ASC");
						}
						else {
							query.append(" DESC");
						}

						if ((i + 1) < orderByFields.length) {
							query.append(", ");
						}
					}
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(workflowInstanceId);

				list = (List<WorkflowTask>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowTask>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_WORKFLOWINSTANCEID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WorkflowTask findByWorkflowInstanceId_First(
		long workflowInstanceId, OrderByComparator obc)
		throws NoSuchWorkflowTaskException, SystemException {
		List<WorkflowTask> list = findByWorkflowInstanceId(workflowInstanceId,
				0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowTask exists with the key {");

			msg.append("workflowInstanceId=" + workflowInstanceId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowTask findByWorkflowInstanceId_Last(long workflowInstanceId,
		OrderByComparator obc)
		throws NoSuchWorkflowTaskException, SystemException {
		int count = countByWorkflowInstanceId(workflowInstanceId);

		List<WorkflowTask> list = findByWorkflowInstanceId(workflowInstanceId,
				count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowTask exists with the key {");

			msg.append("workflowInstanceId=" + workflowInstanceId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowTask[] findByWorkflowInstanceId_PrevAndNext(
		long workflowTaskId, long workflowInstanceId, OrderByComparator obc)
		throws NoSuchWorkflowTaskException, SystemException {
		WorkflowTask workflowTask = findByPrimaryKey(workflowTaskId);

		int count = countByWorkflowInstanceId(workflowInstanceId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT workflowTask FROM WorkflowTask workflowTask WHERE ");

			query.append("workflowTask.workflowInstanceId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("workflowTask.");
					query.append(orderByFields[i]);

					if (obc.isAscending()) {
						query.append(" ASC");
					}
					else {
						query.append(" DESC");
					}

					if ((i + 1) < orderByFields.length) {
						query.append(", ");
					}
				}
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(workflowInstanceId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					workflowTask);

			WorkflowTask[] array = new WorkflowTaskImpl[3];

			array[0] = (WorkflowTask)objArray[0];
			array[1] = (WorkflowTask)objArray[1];
			array[2] = (WorkflowTask)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<WorkflowTask> findByW_S(long workflowInstanceId, int state)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(workflowInstanceId), new Integer(state)
			};

		List<WorkflowTask> list = (List<WorkflowTask>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_W_S,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowTask FROM WorkflowTask workflowTask WHERE ");

				query.append("workflowTask.workflowInstanceId = ?");

				query.append(" AND ");

				query.append("workflowTask.state = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(workflowInstanceId);

				qPos.add(state);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowTask>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_W_S, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WorkflowTask> findByW_S(long workflowInstanceId, int state,
		int start, int end) throws SystemException {
		return findByW_S(workflowInstanceId, state, start, end, null);
	}

	public List<WorkflowTask> findByW_S(long workflowInstanceId, int state,
		int start, int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(workflowInstanceId), new Integer(state),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WorkflowTask> list = (List<WorkflowTask>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_W_S,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowTask FROM WorkflowTask workflowTask WHERE ");

				query.append("workflowTask.workflowInstanceId = ?");

				query.append(" AND ");

				query.append("workflowTask.state = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("workflowTask.");
						query.append(orderByFields[i]);

						if (obc.isAscending()) {
							query.append(" ASC");
						}
						else {
							query.append(" DESC");
						}

						if ((i + 1) < orderByFields.length) {
							query.append(", ");
						}
					}
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(workflowInstanceId);

				qPos.add(state);

				list = (List<WorkflowTask>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowTask>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_W_S,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WorkflowTask findByW_S_First(long workflowInstanceId, int state,
		OrderByComparator obc)
		throws NoSuchWorkflowTaskException, SystemException {
		List<WorkflowTask> list = findByW_S(workflowInstanceId, state, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowTask exists with the key {");

			msg.append("workflowInstanceId=" + workflowInstanceId);

			msg.append(", ");
			msg.append("state=" + state);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowTask findByW_S_Last(long workflowInstanceId, int state,
		OrderByComparator obc)
		throws NoSuchWorkflowTaskException, SystemException {
		int count = countByW_S(workflowInstanceId, state);

		List<WorkflowTask> list = findByW_S(workflowInstanceId, state,
				count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowTask exists with the key {");

			msg.append("workflowInstanceId=" + workflowInstanceId);

			msg.append(", ");
			msg.append("state=" + state);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowTask[] findByW_S_PrevAndNext(long workflowTaskId,
		long workflowInstanceId, int state, OrderByComparator obc)
		throws NoSuchWorkflowTaskException, SystemException {
		WorkflowTask workflowTask = findByPrimaryKey(workflowTaskId);

		int count = countByW_S(workflowInstanceId, state);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT workflowTask FROM WorkflowTask workflowTask WHERE ");

			query.append("workflowTask.workflowInstanceId = ?");

			query.append(" AND ");

			query.append("workflowTask.state = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("workflowTask.");
					query.append(orderByFields[i]);

					if (obc.isAscending()) {
						query.append(" ASC");
					}
					else {
						query.append(" DESC");
					}

					if ((i + 1) < orderByFields.length) {
						query.append(", ");
					}
				}
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(workflowInstanceId);

			qPos.add(state);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					workflowTask);

			WorkflowTask[] array = new WorkflowTaskImpl[3];

			array[0] = (WorkflowTask)objArray[0];
			array[1] = (WorkflowTask)objArray[1];
			array[2] = (WorkflowTask)objArray[2];

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

	public List<WorkflowTask> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<WorkflowTask> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<WorkflowTask> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WorkflowTask> list = (List<WorkflowTask>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowTask FROM WorkflowTask workflowTask ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("workflowTask.");
						query.append(orderByFields[i]);

						if (obc.isAscending()) {
							query.append(" ASC");
						}
						else {
							query.append(" DESC");
						}

						if ((i + 1) < orderByFields.length) {
							query.append(", ");
						}
					}
				}

				Query q = session.createQuery(query.toString());

				if (obc == null) {
					list = (List<WorkflowTask>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<WorkflowTask>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowTask>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByWorkflowInstanceId(long workflowInstanceId)
		throws SystemException {
		for (WorkflowTask workflowTask : findByWorkflowInstanceId(
				workflowInstanceId)) {
			remove(workflowTask);
		}
	}

	public void removeByW_S(long workflowInstanceId, int state)
		throws SystemException {
		for (WorkflowTask workflowTask : findByW_S(workflowInstanceId, state)) {
			remove(workflowTask);
		}
	}

	public void removeAll() throws SystemException {
		for (WorkflowTask workflowTask : findAll()) {
			remove(workflowTask);
		}
	}

	public int countByWorkflowInstanceId(long workflowInstanceId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(workflowInstanceId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_WORKFLOWINSTANCEID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(workflowTask) ");
				query.append("FROM WorkflowTask workflowTask WHERE ");

				query.append("workflowTask.workflowInstanceId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(workflowInstanceId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_WORKFLOWINSTANCEID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByW_S(long workflowInstanceId, int state)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(workflowInstanceId), new Integer(state)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_W_S,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(workflowTask) ");
				query.append("FROM WorkflowTask workflowTask WHERE ");

				query.append("workflowTask.workflowInstanceId = ?");

				query.append(" AND ");

				query.append("workflowTask.state = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(workflowInstanceId);

				qPos.add(state);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_W_S, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(
						"SELECT COUNT(workflowTask) FROM WorkflowTask workflowTask");

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.edoras.model.WorkflowTask")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<WorkflowTask>> listenersList = new ArrayList<ModelListener<WorkflowTask>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<WorkflowTask>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(name = "com.liferay.portal.workflow.edoras.service.persistence.WorkflowDefinitionPersistence.impl")
	protected com.liferay.portal.workflow.edoras.service.persistence.WorkflowDefinitionPersistence workflowDefinitionPersistence;
	@BeanReference(name = "com.liferay.portal.workflow.edoras.service.persistence.WorkflowInstancePersistence.impl")
	protected com.liferay.portal.workflow.edoras.service.persistence.WorkflowInstancePersistence workflowInstancePersistence;
	@BeanReference(name = "com.liferay.portal.workflow.edoras.service.persistence.WorkflowJobPersistence.impl")
	protected com.liferay.portal.workflow.edoras.service.persistence.WorkflowJobPersistence workflowJobPersistence;
	@BeanReference(name = "com.liferay.portal.workflow.edoras.service.persistence.WorkflowLogPersistence.impl")
	protected com.liferay.portal.workflow.edoras.service.persistence.WorkflowLogPersistence workflowLogPersistence;
	@BeanReference(name = "com.liferay.portal.workflow.edoras.service.persistence.WorkflowTaskPersistence.impl")
	protected com.liferay.portal.workflow.edoras.service.persistence.WorkflowTaskPersistence workflowTaskPersistence;
	private static Log _log = LogFactoryUtil.getLog(WorkflowTaskPersistenceImpl.class);
}