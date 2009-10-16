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
import com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException;
import com.liferay.portal.workflow.edoras.model.WorkflowLog;
import com.liferay.portal.workflow.edoras.model.impl.WorkflowLogImpl;
import com.liferay.portal.workflow.edoras.model.impl.WorkflowLogModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="WorkflowLogPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowLogPersistenceImpl extends BasePersistenceImpl
	implements WorkflowLogPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = WorkflowLogImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_WORKFLOWINSTANCEID = new FinderPath(WorkflowLogModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByWorkflowInstanceId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_WORKFLOWINSTANCEID = new FinderPath(WorkflowLogModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByWorkflowInstanceId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_WORKFLOWINSTANCEID = new FinderPath(WorkflowLogModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByWorkflowInstanceId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_WORKFLOWTASKID = new FinderPath(WorkflowLogModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByWorkflowTaskId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_WORKFLOWTASKID = new FinderPath(WorkflowLogModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByWorkflowTaskId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_WORKFLOWTASKID = new FinderPath(WorkflowLogModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByWorkflowTaskId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_W_T = new FinderPath(WorkflowLogModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByW_T",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_W_T = new FinderPath(WorkflowLogModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByW_T",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_W_T = new FinderPath(WorkflowLogModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByW_T",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(WorkflowLogModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WorkflowLogModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(WorkflowLog workflowLog) {
		EntityCacheUtil.putResult(WorkflowLogModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowLogImpl.class, workflowLog.getPrimaryKey(), workflowLog);
	}

	public void cacheResult(List<WorkflowLog> workflowLogs) {
		for (WorkflowLog workflowLog : workflowLogs) {
			if (EntityCacheUtil.getResult(
						WorkflowLogModelImpl.ENTITY_CACHE_ENABLED,
						WorkflowLogImpl.class, workflowLog.getPrimaryKey(), this) == null) {
				cacheResult(workflowLog);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(WorkflowLogImpl.class.getName());
		EntityCacheUtil.clearCache(WorkflowLogImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public WorkflowLog create(long workflowLogId) {
		WorkflowLog workflowLog = new WorkflowLogImpl();

		workflowLog.setNew(true);
		workflowLog.setPrimaryKey(workflowLogId);

		return workflowLog;
	}

	public WorkflowLog remove(long workflowLogId)
		throws NoSuchWorkflowLogException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WorkflowLog workflowLog = (WorkflowLog)session.get(WorkflowLogImpl.class,
					new Long(workflowLogId));

			if (workflowLog == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No WorkflowLog exists with the primary key " +
						workflowLogId);
				}

				throw new NoSuchWorkflowLogException(
					"No WorkflowLog exists with the primary key " +
					workflowLogId);
			}

			return remove(workflowLog);
		}
		catch (NoSuchWorkflowLogException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public WorkflowLog remove(WorkflowLog workflowLog)
		throws SystemException {
		for (ModelListener<WorkflowLog> listener : listeners) {
			listener.onBeforeRemove(workflowLog);
		}

		workflowLog = removeImpl(workflowLog);

		for (ModelListener<WorkflowLog> listener : listeners) {
			listener.onAfterRemove(workflowLog);
		}

		return workflowLog;
	}

	protected WorkflowLog removeImpl(WorkflowLog workflowLog)
		throws SystemException {
		workflowLog = toUnwrappedModel(workflowLog);

		Session session = null;

		try {
			session = openSession();

			if (workflowLog.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(WorkflowLogImpl.class,
						workflowLog.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(workflowLog);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(WorkflowLogModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowLogImpl.class, workflowLog.getPrimaryKey());

		return workflowLog;
	}

	public WorkflowLog update(WorkflowLog workflowLog)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(WorkflowLog workflowLog) method. Use update(WorkflowLog workflowLog, boolean merge) instead.");
		}

		return update(workflowLog, false);
	}

	public WorkflowLog update(WorkflowLog workflowLog, boolean merge)
		throws SystemException {
		boolean isNew = workflowLog.isNew();

		for (ModelListener<WorkflowLog> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(workflowLog);
			}
			else {
				listener.onBeforeUpdate(workflowLog);
			}
		}

		workflowLog = updateImpl(workflowLog, merge);

		for (ModelListener<WorkflowLog> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(workflowLog);
			}
			else {
				listener.onAfterUpdate(workflowLog);
			}
		}

		return workflowLog;
	}

	public WorkflowLog updateImpl(
		com.liferay.portal.workflow.edoras.model.WorkflowLog workflowLog,
		boolean merge) throws SystemException {
		workflowLog = toUnwrappedModel(workflowLog);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, workflowLog, merge);

			workflowLog.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(WorkflowLogModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowLogImpl.class, workflowLog.getPrimaryKey(), workflowLog);

		return workflowLog;
	}

	protected WorkflowLog toUnwrappedModel(WorkflowLog workflowLog) {
		if (workflowLog instanceof WorkflowLogImpl) {
			return workflowLog;
		}

		WorkflowLogImpl workflowLogImpl = new WorkflowLogImpl();

		workflowLogImpl.setNew(workflowLog.isNew());
		workflowLogImpl.setPrimaryKey(workflowLog.getPrimaryKey());

		workflowLogImpl.setWorkflowLogId(workflowLog.getWorkflowLogId());
		workflowLogImpl.setCompanyId(workflowLog.getCompanyId());
		workflowLogImpl.setUserId(workflowLog.getUserId());
		workflowLogImpl.setUserName(workflowLog.getUserName());
		workflowLogImpl.setCreateDate(workflowLog.getCreateDate());
		workflowLogImpl.setWorkflowDefinitionId(workflowLog.getWorkflowDefinitionId());
		workflowLogImpl.setWorkflowInstanceId(workflowLog.getWorkflowInstanceId());
		workflowLogImpl.setWorkflowTaskId(workflowLog.getWorkflowTaskId());
		workflowLogImpl.setLogEntityType(workflowLog.getLogEntityType());
		workflowLogImpl.setDescription(workflowLog.getDescription());
		workflowLogImpl.setActivityName(workflowLog.getActivityName());
		workflowLogImpl.setOldState(workflowLog.getOldState());
		workflowLogImpl.setNewState(workflowLog.getNewState());
		workflowLogImpl.setType(workflowLog.getType());
		workflowLogImpl.setComment(workflowLog.getComment());

		return workflowLogImpl;
	}

	public WorkflowLog findByPrimaryKey(long workflowLogId)
		throws NoSuchWorkflowLogException, SystemException {
		WorkflowLog workflowLog = fetchByPrimaryKey(workflowLogId);

		if (workflowLog == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No WorkflowLog exists with the primary key " +
					workflowLogId);
			}

			throw new NoSuchWorkflowLogException(
				"No WorkflowLog exists with the primary key " + workflowLogId);
		}

		return workflowLog;
	}

	public WorkflowLog fetchByPrimaryKey(long workflowLogId)
		throws SystemException {
		WorkflowLog workflowLog = (WorkflowLog)EntityCacheUtil.getResult(WorkflowLogModelImpl.ENTITY_CACHE_ENABLED,
				WorkflowLogImpl.class, workflowLogId, this);

		if (workflowLog == null) {
			Session session = null;

			try {
				session = openSession();

				workflowLog = (WorkflowLog)session.get(WorkflowLogImpl.class,
						new Long(workflowLogId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (workflowLog != null) {
					cacheResult(workflowLog);
				}

				closeSession(session);
			}
		}

		return workflowLog;
	}

	public List<WorkflowLog> findByWorkflowInstanceId(long workflowInstanceId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(workflowInstanceId) };

		List<WorkflowLog> list = (List<WorkflowLog>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_WORKFLOWINSTANCEID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowLog FROM WorkflowLog workflowLog WHERE ");

				query.append("workflowLog.workflowInstanceId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("workflowLog.createDate ASC");

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
					list = new ArrayList<WorkflowLog>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_WORKFLOWINSTANCEID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WorkflowLog> findByWorkflowInstanceId(long workflowInstanceId,
		int start, int end) throws SystemException {
		return findByWorkflowInstanceId(workflowInstanceId, start, end, null);
	}

	public List<WorkflowLog> findByWorkflowInstanceId(long workflowInstanceId,
		int start, int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(workflowInstanceId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WorkflowLog> list = (List<WorkflowLog>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_WORKFLOWINSTANCEID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowLog FROM WorkflowLog workflowLog WHERE ");

				query.append("workflowLog.workflowInstanceId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("workflowLog.");
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

				else {
					query.append("ORDER BY ");

					query.append("workflowLog.createDate ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(workflowInstanceId);

				list = (List<WorkflowLog>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowLog>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_WORKFLOWINSTANCEID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WorkflowLog findByWorkflowInstanceId_First(long workflowInstanceId,
		OrderByComparator obc)
		throws NoSuchWorkflowLogException, SystemException {
		List<WorkflowLog> list = findByWorkflowInstanceId(workflowInstanceId,
				0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowLog exists with the key {");

			msg.append("workflowInstanceId=" + workflowInstanceId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowLog findByWorkflowInstanceId_Last(long workflowInstanceId,
		OrderByComparator obc)
		throws NoSuchWorkflowLogException, SystemException {
		int count = countByWorkflowInstanceId(workflowInstanceId);

		List<WorkflowLog> list = findByWorkflowInstanceId(workflowInstanceId,
				count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowLog exists with the key {");

			msg.append("workflowInstanceId=" + workflowInstanceId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowLog[] findByWorkflowInstanceId_PrevAndNext(
		long workflowLogId, long workflowInstanceId, OrderByComparator obc)
		throws NoSuchWorkflowLogException, SystemException {
		WorkflowLog workflowLog = findByPrimaryKey(workflowLogId);

		int count = countByWorkflowInstanceId(workflowInstanceId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT workflowLog FROM WorkflowLog workflowLog WHERE ");

			query.append("workflowLog.workflowInstanceId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("workflowLog.");
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

			else {
				query.append("ORDER BY ");

				query.append("workflowLog.createDate ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(workflowInstanceId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					workflowLog);

			WorkflowLog[] array = new WorkflowLogImpl[3];

			array[0] = (WorkflowLog)objArray[0];
			array[1] = (WorkflowLog)objArray[1];
			array[2] = (WorkflowLog)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<WorkflowLog> findByWorkflowTaskId(long workflowTaskId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(workflowTaskId) };

		List<WorkflowLog> list = (List<WorkflowLog>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_WORKFLOWTASKID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowLog FROM WorkflowLog workflowLog WHERE ");

				query.append("workflowLog.workflowTaskId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("workflowLog.createDate ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(workflowTaskId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowLog>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_WORKFLOWTASKID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WorkflowLog> findByWorkflowTaskId(long workflowTaskId,
		int start, int end) throws SystemException {
		return findByWorkflowTaskId(workflowTaskId, start, end, null);
	}

	public List<WorkflowLog> findByWorkflowTaskId(long workflowTaskId,
		int start, int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(workflowTaskId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WorkflowLog> list = (List<WorkflowLog>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_WORKFLOWTASKID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowLog FROM WorkflowLog workflowLog WHERE ");

				query.append("workflowLog.workflowTaskId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("workflowLog.");
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

				else {
					query.append("ORDER BY ");

					query.append("workflowLog.createDate ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(workflowTaskId);

				list = (List<WorkflowLog>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowLog>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_WORKFLOWTASKID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WorkflowLog findByWorkflowTaskId_First(long workflowTaskId,
		OrderByComparator obc)
		throws NoSuchWorkflowLogException, SystemException {
		List<WorkflowLog> list = findByWorkflowTaskId(workflowTaskId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowLog exists with the key {");

			msg.append("workflowTaskId=" + workflowTaskId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowLog findByWorkflowTaskId_Last(long workflowTaskId,
		OrderByComparator obc)
		throws NoSuchWorkflowLogException, SystemException {
		int count = countByWorkflowTaskId(workflowTaskId);

		List<WorkflowLog> list = findByWorkflowTaskId(workflowTaskId,
				count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowLog exists with the key {");

			msg.append("workflowTaskId=" + workflowTaskId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowLog[] findByWorkflowTaskId_PrevAndNext(long workflowLogId,
		long workflowTaskId, OrderByComparator obc)
		throws NoSuchWorkflowLogException, SystemException {
		WorkflowLog workflowLog = findByPrimaryKey(workflowLogId);

		int count = countByWorkflowTaskId(workflowTaskId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT workflowLog FROM WorkflowLog workflowLog WHERE ");

			query.append("workflowLog.workflowTaskId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("workflowLog.");
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

			else {
				query.append("ORDER BY ");

				query.append("workflowLog.createDate ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(workflowTaskId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					workflowLog);

			WorkflowLog[] array = new WorkflowLogImpl[3];

			array[0] = (WorkflowLog)objArray[0];
			array[1] = (WorkflowLog)objArray[1];
			array[2] = (WorkflowLog)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<WorkflowLog> findByW_T(long workflowInstanceId, int type)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(workflowInstanceId), new Integer(type)
			};

		List<WorkflowLog> list = (List<WorkflowLog>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_W_T,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowLog FROM WorkflowLog workflowLog WHERE ");

				query.append("workflowLog.workflowInstanceId = ?");

				query.append(" AND ");

				query.append("workflowLog.type = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("workflowLog.createDate ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(workflowInstanceId);

				qPos.add(type);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowLog>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_W_T, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WorkflowLog> findByW_T(long workflowInstanceId, int type,
		int start, int end) throws SystemException {
		return findByW_T(workflowInstanceId, type, start, end, null);
	}

	public List<WorkflowLog> findByW_T(long workflowInstanceId, int type,
		int start, int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(workflowInstanceId), new Integer(type),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WorkflowLog> list = (List<WorkflowLog>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_W_T,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowLog FROM WorkflowLog workflowLog WHERE ");

				query.append("workflowLog.workflowInstanceId = ?");

				query.append(" AND ");

				query.append("workflowLog.type = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("workflowLog.");
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

				else {
					query.append("ORDER BY ");

					query.append("workflowLog.createDate ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(workflowInstanceId);

				qPos.add(type);

				list = (List<WorkflowLog>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowLog>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_W_T,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WorkflowLog findByW_T_First(long workflowInstanceId, int type,
		OrderByComparator obc)
		throws NoSuchWorkflowLogException, SystemException {
		List<WorkflowLog> list = findByW_T(workflowInstanceId, type, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowLog exists with the key {");

			msg.append("workflowInstanceId=" + workflowInstanceId);

			msg.append(", ");
			msg.append("type=" + type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowLog findByW_T_Last(long workflowInstanceId, int type,
		OrderByComparator obc)
		throws NoSuchWorkflowLogException, SystemException {
		int count = countByW_T(workflowInstanceId, type);

		List<WorkflowLog> list = findByW_T(workflowInstanceId, type, count - 1,
				count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowLog exists with the key {");

			msg.append("workflowInstanceId=" + workflowInstanceId);

			msg.append(", ");
			msg.append("type=" + type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowLog[] findByW_T_PrevAndNext(long workflowLogId,
		long workflowInstanceId, int type, OrderByComparator obc)
		throws NoSuchWorkflowLogException, SystemException {
		WorkflowLog workflowLog = findByPrimaryKey(workflowLogId);

		int count = countByW_T(workflowInstanceId, type);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT workflowLog FROM WorkflowLog workflowLog WHERE ");

			query.append("workflowLog.workflowInstanceId = ?");

			query.append(" AND ");

			query.append("workflowLog.type = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("workflowLog.");
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

			else {
				query.append("ORDER BY ");

				query.append("workflowLog.createDate ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(workflowInstanceId);

			qPos.add(type);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					workflowLog);

			WorkflowLog[] array = new WorkflowLogImpl[3];

			array[0] = (WorkflowLog)objArray[0];
			array[1] = (WorkflowLog)objArray[1];
			array[2] = (WorkflowLog)objArray[2];

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

	public List<WorkflowLog> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<WorkflowLog> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<WorkflowLog> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WorkflowLog> list = (List<WorkflowLog>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT workflowLog FROM WorkflowLog workflowLog ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("workflowLog.");
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

				else {
					query.append("ORDER BY ");

					query.append("workflowLog.createDate ASC");
				}

				Query q = session.createQuery(query.toString());

				if (obc == null) {
					list = (List<WorkflowLog>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<WorkflowLog>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowLog>();
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
		for (WorkflowLog workflowLog : findByWorkflowInstanceId(
				workflowInstanceId)) {
			remove(workflowLog);
		}
	}

	public void removeByWorkflowTaskId(long workflowTaskId)
		throws SystemException {
		for (WorkflowLog workflowLog : findByWorkflowTaskId(workflowTaskId)) {
			remove(workflowLog);
		}
	}

	public void removeByW_T(long workflowInstanceId, int type)
		throws SystemException {
		for (WorkflowLog workflowLog : findByW_T(workflowInstanceId, type)) {
			remove(workflowLog);
		}
	}

	public void removeAll() throws SystemException {
		for (WorkflowLog workflowLog : findAll()) {
			remove(workflowLog);
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

				query.append("SELECT COUNT(workflowLog) ");
				query.append("FROM WorkflowLog workflowLog WHERE ");

				query.append("workflowLog.workflowInstanceId = ?");

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

	public int countByWorkflowTaskId(long workflowTaskId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(workflowTaskId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_WORKFLOWTASKID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(workflowLog) ");
				query.append("FROM WorkflowLog workflowLog WHERE ");

				query.append("workflowLog.workflowTaskId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(workflowTaskId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_WORKFLOWTASKID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByW_T(long workflowInstanceId, int type)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(workflowInstanceId), new Integer(type)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_W_T,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(workflowLog) ");
				query.append("FROM WorkflowLog workflowLog WHERE ");

				query.append("workflowLog.workflowInstanceId = ?");

				query.append(" AND ");

				query.append("workflowLog.type = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(workflowInstanceId);

				qPos.add(type);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_W_T, finderArgs,
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
						"SELECT COUNT(workflowLog) FROM WorkflowLog workflowLog");

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
						"value.object.listener.com.liferay.portal.workflow.edoras.model.WorkflowLog")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<WorkflowLog>> listenersList = new ArrayList<ModelListener<WorkflowLog>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<WorkflowLog>)Class.forName(
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
	private static Log _log = LogFactoryUtil.getLog(WorkflowLogPersistenceImpl.class);
}