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

import com.liferay.portal.NoSuchModelException;
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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="WorkflowTaskPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowTaskPersistenceImpl extends BasePersistenceImpl<WorkflowTask>
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
	public static final FinderPath FINDER_PATH_FIND_BY_COMPLETED = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByCompleted", new String[] { Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_COMPLETED = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByCompleted",
			new String[] {
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPLETED = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByCompleted", new String[] { Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_ASSIGNEEUSERID = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByAssigneeUserId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_ASSIGNEEUSERID = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByAssigneeUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_ASSIGNEEUSERID = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByAssigneeUserId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_ROLEID = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByRoleId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_ROLEID = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByRoleId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_ROLEID = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByRoleId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_C_C = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByC_C",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_C_C = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByC_C",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByC_C",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_W_C = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByW_C",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_W_C = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByW_C",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_W_C = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByW_C",
			new String[] { Long.class.getName(), Boolean.class.getName() });
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
	public static final FinderPath FINDER_PATH_FIND_BY_A_C = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByA_C",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_A_C = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByA_C",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_A_C = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByA_C",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_R_C = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByR_C",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_R_C = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByR_C",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_R_C = new FinderPath(WorkflowTaskModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowTaskModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByR_C",
			new String[] { Long.class.getName(), Boolean.class.getName() });
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

	public WorkflowTask remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
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
		workflowTaskImpl.setWorkflowDefinitionName(workflowTask.getWorkflowDefinitionName());
		workflowTaskImpl.setWorkflowDefinitionVersion(workflowTask.getWorkflowDefinitionVersion());
		workflowTaskImpl.setWorkflowInstanceId(workflowTask.getWorkflowInstanceId());
		workflowTaskImpl.setMetaName(workflowTask.getMetaName());
		workflowTaskImpl.setRelation(workflowTask.getRelation());
		workflowTaskImpl.setDueDate(workflowTask.getDueDate());
		workflowTaskImpl.setCompletionDate(workflowTask.getCompletionDate());
		workflowTaskImpl.setCompleted(workflowTask.isCompleted());
		workflowTaskImpl.setState(workflowTask.getState());
		workflowTaskImpl.setPriority(workflowTask.getPriority());
		workflowTaskImpl.setAsynchronous(workflowTask.isAsynchronous());
		workflowTaskImpl.setTaskName(workflowTask.getTaskName());
		workflowTaskImpl.setDescription(workflowTask.getDescription());
		workflowTaskImpl.setAssigneeUserId(workflowTask.getAssigneeUserId());
		workflowTaskImpl.setAssigneeUserName(workflowTask.getAssigneeUserName());
		workflowTaskImpl.setAssigneeGroupId(workflowTask.getAssigneeGroupId());
		workflowTaskImpl.setAssigneeGroupName(workflowTask.getAssigneeGroupName());
		workflowTaskImpl.setAssigneeRoleId(workflowTask.getAssigneeRoleId());
		workflowTaskImpl.setAssigneeRoleName(workflowTask.getAssigneeRoleName());

		return workflowTaskImpl;
	}

	public WorkflowTask findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
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

	public WorkflowTask fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
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

	public List<WorkflowTask> findByCompleted(boolean completed)
		throws SystemException {
		Object[] finderArgs = new Object[] { Boolean.valueOf(completed) };

		List<WorkflowTask> list = (List<WorkflowTask>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPLETED,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowTask FROM WorkflowTask workflowTask WHERE ");

				query.append("workflowTask.completed = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(completed);

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

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPLETED,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WorkflowTask> findByCompleted(boolean completed, int start,
		int end) throws SystemException {
		return findByCompleted(completed, start, end, null);
	}

	public List<WorkflowTask> findByCompleted(boolean completed, int start,
		int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				Boolean.valueOf(completed),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WorkflowTask> list = (List<WorkflowTask>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_COMPLETED,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowTask FROM WorkflowTask workflowTask WHERE ");

				query.append("workflowTask.completed = ?");

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

				qPos.add(completed);

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

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_COMPLETED,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WorkflowTask findByCompleted_First(boolean completed,
		OrderByComparator obc)
		throws NoSuchWorkflowTaskException, SystemException {
		List<WorkflowTask> list = findByCompleted(completed, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowTask exists with the key {");

			msg.append("completed=" + completed);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowTask findByCompleted_Last(boolean completed,
		OrderByComparator obc)
		throws NoSuchWorkflowTaskException, SystemException {
		int count = countByCompleted(completed);

		List<WorkflowTask> list = findByCompleted(completed, count - 1, count,
				obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowTask exists with the key {");

			msg.append("completed=" + completed);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowTask[] findByCompleted_PrevAndNext(long workflowTaskId,
		boolean completed, OrderByComparator obc)
		throws NoSuchWorkflowTaskException, SystemException {
		WorkflowTask workflowTask = findByPrimaryKey(workflowTaskId);

		int count = countByCompleted(completed);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT workflowTask FROM WorkflowTask workflowTask WHERE ");

			query.append("workflowTask.completed = ?");

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

			qPos.add(completed);

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

	public List<WorkflowTask> findByAssigneeUserId(long assigneeUserId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(assigneeUserId) };

		List<WorkflowTask> list = (List<WorkflowTask>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_ASSIGNEEUSERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowTask FROM WorkflowTask workflowTask WHERE ");

				query.append("workflowTask.assigneeUserId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeUserId);

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

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_ASSIGNEEUSERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WorkflowTask> findByAssigneeUserId(long assigneeUserId,
		int start, int end) throws SystemException {
		return findByAssigneeUserId(assigneeUserId, start, end, null);
	}

	public List<WorkflowTask> findByAssigneeUserId(long assigneeUserId,
		int start, int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(assigneeUserId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WorkflowTask> list = (List<WorkflowTask>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_ASSIGNEEUSERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowTask FROM WorkflowTask workflowTask WHERE ");

				query.append("workflowTask.assigneeUserId = ?");

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

				qPos.add(assigneeUserId);

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

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_ASSIGNEEUSERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WorkflowTask findByAssigneeUserId_First(long assigneeUserId,
		OrderByComparator obc)
		throws NoSuchWorkflowTaskException, SystemException {
		List<WorkflowTask> list = findByAssigneeUserId(assigneeUserId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowTask exists with the key {");

			msg.append("assigneeUserId=" + assigneeUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowTask findByAssigneeUserId_Last(long assigneeUserId,
		OrderByComparator obc)
		throws NoSuchWorkflowTaskException, SystemException {
		int count = countByAssigneeUserId(assigneeUserId);

		List<WorkflowTask> list = findByAssigneeUserId(assigneeUserId,
				count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowTask exists with the key {");

			msg.append("assigneeUserId=" + assigneeUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowTask[] findByAssigneeUserId_PrevAndNext(
		long workflowTaskId, long assigneeUserId, OrderByComparator obc)
		throws NoSuchWorkflowTaskException, SystemException {
		WorkflowTask workflowTask = findByPrimaryKey(workflowTaskId);

		int count = countByAssigneeUserId(assigneeUserId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT workflowTask FROM WorkflowTask workflowTask WHERE ");

			query.append("workflowTask.assigneeUserId = ?");

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

			qPos.add(assigneeUserId);

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

	public List<WorkflowTask> findByRoleId(long assigneeRoleId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(assigneeRoleId) };

		List<WorkflowTask> list = (List<WorkflowTask>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_ROLEID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowTask FROM WorkflowTask workflowTask WHERE ");

				query.append("workflowTask.assigneeRoleId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeRoleId);

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

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_ROLEID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WorkflowTask> findByRoleId(long assigneeRoleId, int start,
		int end) throws SystemException {
		return findByRoleId(assigneeRoleId, start, end, null);
	}

	public List<WorkflowTask> findByRoleId(long assigneeRoleId, int start,
		int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(assigneeRoleId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WorkflowTask> list = (List<WorkflowTask>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_ROLEID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowTask FROM WorkflowTask workflowTask WHERE ");

				query.append("workflowTask.assigneeRoleId = ?");

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

				qPos.add(assigneeRoleId);

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

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_ROLEID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WorkflowTask findByRoleId_First(long assigneeRoleId,
		OrderByComparator obc)
		throws NoSuchWorkflowTaskException, SystemException {
		List<WorkflowTask> list = findByRoleId(assigneeRoleId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowTask exists with the key {");

			msg.append("assigneeRoleId=" + assigneeRoleId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowTask findByRoleId_Last(long assigneeRoleId,
		OrderByComparator obc)
		throws NoSuchWorkflowTaskException, SystemException {
		int count = countByRoleId(assigneeRoleId);

		List<WorkflowTask> list = findByRoleId(assigneeRoleId, count - 1,
				count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowTask exists with the key {");

			msg.append("assigneeRoleId=" + assigneeRoleId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowTask[] findByRoleId_PrevAndNext(long workflowTaskId,
		long assigneeRoleId, OrderByComparator obc)
		throws NoSuchWorkflowTaskException, SystemException {
		WorkflowTask workflowTask = findByPrimaryKey(workflowTaskId);

		int count = countByRoleId(assigneeRoleId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT workflowTask FROM WorkflowTask workflowTask WHERE ");

			query.append("workflowTask.assigneeRoleId = ?");

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

			qPos.add(assigneeRoleId);

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

	public List<WorkflowTask> findByC_C(long companyId, boolean completed)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId), Boolean.valueOf(completed)
			};

		List<WorkflowTask> list = (List<WorkflowTask>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_C_C,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowTask FROM WorkflowTask workflowTask WHERE ");

				query.append("workflowTask.companyId = ?");

				query.append(" AND ");

				query.append("workflowTask.completed = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(completed);

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

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_C_C, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WorkflowTask> findByC_C(long companyId, boolean completed,
		int start, int end) throws SystemException {
		return findByC_C(companyId, completed, start, end, null);
	}

	public List<WorkflowTask> findByC_C(long companyId, boolean completed,
		int start, int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId), Boolean.valueOf(completed),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WorkflowTask> list = (List<WorkflowTask>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_C_C,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowTask FROM WorkflowTask workflowTask WHERE ");

				query.append("workflowTask.companyId = ?");

				query.append(" AND ");

				query.append("workflowTask.completed = ?");

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

				qPos.add(companyId);

				qPos.add(completed);

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

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_C_C,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WorkflowTask findByC_C_First(long companyId, boolean completed,
		OrderByComparator obc)
		throws NoSuchWorkflowTaskException, SystemException {
		List<WorkflowTask> list = findByC_C(companyId, completed, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowTask exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(", ");
			msg.append("completed=" + completed);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowTask findByC_C_Last(long companyId, boolean completed,
		OrderByComparator obc)
		throws NoSuchWorkflowTaskException, SystemException {
		int count = countByC_C(companyId, completed);

		List<WorkflowTask> list = findByC_C(companyId, completed, count - 1,
				count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowTask exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(", ");
			msg.append("completed=" + completed);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowTask[] findByC_C_PrevAndNext(long workflowTaskId,
		long companyId, boolean completed, OrderByComparator obc)
		throws NoSuchWorkflowTaskException, SystemException {
		WorkflowTask workflowTask = findByPrimaryKey(workflowTaskId);

		int count = countByC_C(companyId, completed);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT workflowTask FROM WorkflowTask workflowTask WHERE ");

			query.append("workflowTask.companyId = ?");

			query.append(" AND ");

			query.append("workflowTask.completed = ?");

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

			qPos.add(companyId);

			qPos.add(completed);

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

	public List<WorkflowTask> findByW_C(long workflowInstanceId,
		boolean completed) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(workflowInstanceId), Boolean.valueOf(completed)
			};

		List<WorkflowTask> list = (List<WorkflowTask>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_W_C,
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

				query.append("workflowTask.completed = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(workflowInstanceId);

				qPos.add(completed);

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

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_W_C, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WorkflowTask> findByW_C(long workflowInstanceId,
		boolean completed, int start, int end) throws SystemException {
		return findByW_C(workflowInstanceId, completed, start, end, null);
	}

	public List<WorkflowTask> findByW_C(long workflowInstanceId,
		boolean completed, int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(workflowInstanceId), Boolean.valueOf(completed),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WorkflowTask> list = (List<WorkflowTask>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_W_C,
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

				query.append("workflowTask.completed = ?");

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

				qPos.add(completed);

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

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_W_C,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WorkflowTask findByW_C_First(long workflowInstanceId,
		boolean completed, OrderByComparator obc)
		throws NoSuchWorkflowTaskException, SystemException {
		List<WorkflowTask> list = findByW_C(workflowInstanceId, completed, 0,
				1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowTask exists with the key {");

			msg.append("workflowInstanceId=" + workflowInstanceId);

			msg.append(", ");
			msg.append("completed=" + completed);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowTask findByW_C_Last(long workflowInstanceId,
		boolean completed, OrderByComparator obc)
		throws NoSuchWorkflowTaskException, SystemException {
		int count = countByW_C(workflowInstanceId, completed);

		List<WorkflowTask> list = findByW_C(workflowInstanceId, completed,
				count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowTask exists with the key {");

			msg.append("workflowInstanceId=" + workflowInstanceId);

			msg.append(", ");
			msg.append("completed=" + completed);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowTask[] findByW_C_PrevAndNext(long workflowTaskId,
		long workflowInstanceId, boolean completed, OrderByComparator obc)
		throws NoSuchWorkflowTaskException, SystemException {
		WorkflowTask workflowTask = findByPrimaryKey(workflowTaskId);

		int count = countByW_C(workflowInstanceId, completed);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT workflowTask FROM WorkflowTask workflowTask WHERE ");

			query.append("workflowTask.workflowInstanceId = ?");

			query.append(" AND ");

			query.append("workflowTask.completed = ?");

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

			qPos.add(completed);

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

	public List<WorkflowTask> findByA_C(long assigneeUserId, boolean completed)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(assigneeUserId), Boolean.valueOf(completed)
			};

		List<WorkflowTask> list = (List<WorkflowTask>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_A_C,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowTask FROM WorkflowTask workflowTask WHERE ");

				query.append("workflowTask.assigneeUserId = ?");

				query.append(" AND ");

				query.append("workflowTask.completed = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeUserId);

				qPos.add(completed);

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

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_A_C, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WorkflowTask> findByA_C(long assigneeUserId, boolean completed,
		int start, int end) throws SystemException {
		return findByA_C(assigneeUserId, completed, start, end, null);
	}

	public List<WorkflowTask> findByA_C(long assigneeUserId, boolean completed,
		int start, int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(assigneeUserId), Boolean.valueOf(completed),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WorkflowTask> list = (List<WorkflowTask>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_A_C,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowTask FROM WorkflowTask workflowTask WHERE ");

				query.append("workflowTask.assigneeUserId = ?");

				query.append(" AND ");

				query.append("workflowTask.completed = ?");

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

				qPos.add(assigneeUserId);

				qPos.add(completed);

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

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_A_C,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WorkflowTask findByA_C_First(long assigneeUserId, boolean completed,
		OrderByComparator obc)
		throws NoSuchWorkflowTaskException, SystemException {
		List<WorkflowTask> list = findByA_C(assigneeUserId, completed, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowTask exists with the key {");

			msg.append("assigneeUserId=" + assigneeUserId);

			msg.append(", ");
			msg.append("completed=" + completed);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowTask findByA_C_Last(long assigneeUserId, boolean completed,
		OrderByComparator obc)
		throws NoSuchWorkflowTaskException, SystemException {
		int count = countByA_C(assigneeUserId, completed);

		List<WorkflowTask> list = findByA_C(assigneeUserId, completed,
				count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowTask exists with the key {");

			msg.append("assigneeUserId=" + assigneeUserId);

			msg.append(", ");
			msg.append("completed=" + completed);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowTask[] findByA_C_PrevAndNext(long workflowTaskId,
		long assigneeUserId, boolean completed, OrderByComparator obc)
		throws NoSuchWorkflowTaskException, SystemException {
		WorkflowTask workflowTask = findByPrimaryKey(workflowTaskId);

		int count = countByA_C(assigneeUserId, completed);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT workflowTask FROM WorkflowTask workflowTask WHERE ");

			query.append("workflowTask.assigneeUserId = ?");

			query.append(" AND ");

			query.append("workflowTask.completed = ?");

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

			qPos.add(assigneeUserId);

			qPos.add(completed);

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

	public List<WorkflowTask> findByR_C(long assigneeRoleId, boolean completed)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(assigneeRoleId), Boolean.valueOf(completed)
			};

		List<WorkflowTask> list = (List<WorkflowTask>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_R_C,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowTask FROM WorkflowTask workflowTask WHERE ");

				query.append("workflowTask.assigneeRoleId = ?");

				query.append(" AND ");

				query.append("workflowTask.completed = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeRoleId);

				qPos.add(completed);

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

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_R_C, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WorkflowTask> findByR_C(long assigneeRoleId, boolean completed,
		int start, int end) throws SystemException {
		return findByR_C(assigneeRoleId, completed, start, end, null);
	}

	public List<WorkflowTask> findByR_C(long assigneeRoleId, boolean completed,
		int start, int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(assigneeRoleId), Boolean.valueOf(completed),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WorkflowTask> list = (List<WorkflowTask>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_R_C,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowTask FROM WorkflowTask workflowTask WHERE ");

				query.append("workflowTask.assigneeRoleId = ?");

				query.append(" AND ");

				query.append("workflowTask.completed = ?");

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

				qPos.add(assigneeRoleId);

				qPos.add(completed);

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

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_R_C,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WorkflowTask findByR_C_First(long assigneeRoleId, boolean completed,
		OrderByComparator obc)
		throws NoSuchWorkflowTaskException, SystemException {
		List<WorkflowTask> list = findByR_C(assigneeRoleId, completed, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowTask exists with the key {");

			msg.append("assigneeRoleId=" + assigneeRoleId);

			msg.append(", ");
			msg.append("completed=" + completed);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowTask findByR_C_Last(long assigneeRoleId, boolean completed,
		OrderByComparator obc)
		throws NoSuchWorkflowTaskException, SystemException {
		int count = countByR_C(assigneeRoleId, completed);

		List<WorkflowTask> list = findByR_C(assigneeRoleId, completed,
				count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowTask exists with the key {");

			msg.append("assigneeRoleId=" + assigneeRoleId);

			msg.append(", ");
			msg.append("completed=" + completed);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowTask[] findByR_C_PrevAndNext(long workflowTaskId,
		long assigneeRoleId, boolean completed, OrderByComparator obc)
		throws NoSuchWorkflowTaskException, SystemException {
		WorkflowTask workflowTask = findByPrimaryKey(workflowTaskId);

		int count = countByR_C(assigneeRoleId, completed);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT workflowTask FROM WorkflowTask workflowTask WHERE ");

			query.append("workflowTask.assigneeRoleId = ?");

			query.append(" AND ");

			query.append("workflowTask.completed = ?");

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

			qPos.add(assigneeRoleId);

			qPos.add(completed);

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

	public void removeByCompleted(boolean completed) throws SystemException {
		for (WorkflowTask workflowTask : findByCompleted(completed)) {
			remove(workflowTask);
		}
	}

	public void removeByAssigneeUserId(long assigneeUserId)
		throws SystemException {
		for (WorkflowTask workflowTask : findByAssigneeUserId(assigneeUserId)) {
			remove(workflowTask);
		}
	}

	public void removeByRoleId(long assigneeRoleId) throws SystemException {
		for (WorkflowTask workflowTask : findByRoleId(assigneeRoleId)) {
			remove(workflowTask);
		}
	}

	public void removeByC_C(long companyId, boolean completed)
		throws SystemException {
		for (WorkflowTask workflowTask : findByC_C(companyId, completed)) {
			remove(workflowTask);
		}
	}

	public void removeByW_C(long workflowInstanceId, boolean completed)
		throws SystemException {
		for (WorkflowTask workflowTask : findByW_C(workflowInstanceId, completed)) {
			remove(workflowTask);
		}
	}

	public void removeByW_S(long workflowInstanceId, int state)
		throws SystemException {
		for (WorkflowTask workflowTask : findByW_S(workflowInstanceId, state)) {
			remove(workflowTask);
		}
	}

	public void removeByA_C(long assigneeUserId, boolean completed)
		throws SystemException {
		for (WorkflowTask workflowTask : findByA_C(assigneeUserId, completed)) {
			remove(workflowTask);
		}
	}

	public void removeByR_C(long assigneeRoleId, boolean completed)
		throws SystemException {
		for (WorkflowTask workflowTask : findByR_C(assigneeRoleId, completed)) {
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

	public int countByCompleted(boolean completed) throws SystemException {
		Object[] finderArgs = new Object[] { Boolean.valueOf(completed) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPLETED,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(workflowTask) ");
				query.append("FROM WorkflowTask workflowTask WHERE ");

				query.append("workflowTask.completed = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(completed);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPLETED,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByAssigneeUserId(long assigneeUserId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(assigneeUserId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ASSIGNEEUSERID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(workflowTask) ");
				query.append("FROM WorkflowTask workflowTask WHERE ");

				query.append("workflowTask.assigneeUserId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeUserId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ASSIGNEEUSERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByRoleId(long assigneeRoleId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(assigneeRoleId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ROLEID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(workflowTask) ");
				query.append("FROM WorkflowTask workflowTask WHERE ");

				query.append("workflowTask.assigneeRoleId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeRoleId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ROLEID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByC_C(long companyId, boolean completed)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId), Boolean.valueOf(completed)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(workflowTask) ");
				query.append("FROM WorkflowTask workflowTask WHERE ");

				query.append("workflowTask.companyId = ?");

				query.append(" AND ");

				query.append("workflowTask.completed = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(completed);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByW_C(long workflowInstanceId, boolean completed)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(workflowInstanceId), Boolean.valueOf(completed)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_W_C,
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

				query.append("workflowTask.completed = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(workflowInstanceId);

				qPos.add(completed);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_W_C, finderArgs,
					count);

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

	public int countByA_C(long assigneeUserId, boolean completed)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(assigneeUserId), Boolean.valueOf(completed)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_A_C,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(workflowTask) ");
				query.append("FROM WorkflowTask workflowTask WHERE ");

				query.append("workflowTask.assigneeUserId = ?");

				query.append(" AND ");

				query.append("workflowTask.completed = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeUserId);

				qPos.add(completed);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_A_C, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByR_C(long assigneeRoleId, boolean completed)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(assigneeRoleId), Boolean.valueOf(completed)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_R_C,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(workflowTask) ");
				query.append("FROM WorkflowTask workflowTask WHERE ");

				query.append("workflowTask.assigneeRoleId = ?");

				query.append(" AND ");

				query.append("workflowTask.completed = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeRoleId);

				qPos.add(completed);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_R_C, finderArgs,
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