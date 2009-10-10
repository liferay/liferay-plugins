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
import com.liferay.portal.workflow.edoras.NoSuchWorkflowJobException;
import com.liferay.portal.workflow.edoras.model.WorkflowJob;
import com.liferay.portal.workflow.edoras.model.impl.WorkflowJobImpl;
import com.liferay.portal.workflow.edoras.model.impl.WorkflowJobModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="WorkflowJobPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowJobPersistenceImpl extends BasePersistenceImpl
	implements WorkflowJobPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = WorkflowJobImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_SETUPID = new FinderPath(WorkflowJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowJobModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findBySetupId", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_SETUPID = new FinderPath(WorkflowJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowJobModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findBySetupId",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_SETUPID = new FinderPath(WorkflowJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowJobModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countBySetupId", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_WORKFLOWDEFINITIONID = new FinderPath(WorkflowJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowJobModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByWorkflowDefinitionId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_WORKFLOWDEFINITIONID = new FinderPath(WorkflowJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowJobModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByWorkflowDefinitionId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_WORKFLOWDEFINITIONID = new FinderPath(WorkflowJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowJobModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByWorkflowDefinitionId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_WORKFLOWINSTANCEID = new FinderPath(WorkflowJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowJobModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByWorkflowInstanceId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_WORKFLOWINSTANCEID = new FinderPath(WorkflowJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowJobModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByWorkflowInstanceId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_WORKFLOWINSTANCEID = new FinderPath(WorkflowJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowJobModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByWorkflowInstanceId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(WorkflowJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowJobModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WorkflowJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowJobModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(WorkflowJob workflowJob) {
		EntityCacheUtil.putResult(WorkflowJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowJobImpl.class, workflowJob.getPrimaryKey(), workflowJob);
	}

	public void cacheResult(List<WorkflowJob> workflowJobs) {
		for (WorkflowJob workflowJob : workflowJobs) {
			if (EntityCacheUtil.getResult(
						WorkflowJobModelImpl.ENTITY_CACHE_ENABLED,
						WorkflowJobImpl.class, workflowJob.getPrimaryKey(), this) == null) {
				cacheResult(workflowJob);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(WorkflowJobImpl.class.getName());
		EntityCacheUtil.clearCache(WorkflowJobImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public WorkflowJob create(long workflowJobId) {
		WorkflowJob workflowJob = new WorkflowJobImpl();

		workflowJob.setNew(true);
		workflowJob.setPrimaryKey(workflowJobId);

		return workflowJob;
	}

	public WorkflowJob remove(long workflowJobId)
		throws NoSuchWorkflowJobException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WorkflowJob workflowJob = (WorkflowJob)session.get(WorkflowJobImpl.class,
					new Long(workflowJobId));

			if (workflowJob == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No WorkflowJob exists with the primary key " +
						workflowJobId);
				}

				throw new NoSuchWorkflowJobException(
					"No WorkflowJob exists with the primary key " +
					workflowJobId);
			}

			return remove(workflowJob);
		}
		catch (NoSuchWorkflowJobException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public WorkflowJob remove(WorkflowJob workflowJob)
		throws SystemException {
		for (ModelListener<WorkflowJob> listener : listeners) {
			listener.onBeforeRemove(workflowJob);
		}

		workflowJob = removeImpl(workflowJob);

		for (ModelListener<WorkflowJob> listener : listeners) {
			listener.onAfterRemove(workflowJob);
		}

		return workflowJob;
	}

	protected WorkflowJob removeImpl(WorkflowJob workflowJob)
		throws SystemException {
		workflowJob = toUnwrappedModel(workflowJob);

		Session session = null;

		try {
			session = openSession();

			if (workflowJob.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(WorkflowJobImpl.class,
						workflowJob.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(workflowJob);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(WorkflowJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowJobImpl.class, workflowJob.getPrimaryKey());

		return workflowJob;
	}

	public WorkflowJob update(WorkflowJob workflowJob)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(WorkflowJob workflowJob) method. Use update(WorkflowJob workflowJob, boolean merge) instead.");
		}

		return update(workflowJob, false);
	}

	public WorkflowJob update(WorkflowJob workflowJob, boolean merge)
		throws SystemException {
		boolean isNew = workflowJob.isNew();

		for (ModelListener<WorkflowJob> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(workflowJob);
			}
			else {
				listener.onBeforeUpdate(workflowJob);
			}
		}

		workflowJob = updateImpl(workflowJob, merge);

		for (ModelListener<WorkflowJob> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(workflowJob);
			}
			else {
				listener.onAfterUpdate(workflowJob);
			}
		}

		return workflowJob;
	}

	public WorkflowJob updateImpl(
		com.liferay.portal.workflow.edoras.model.WorkflowJob workflowJob,
		boolean merge) throws SystemException {
		workflowJob = toUnwrappedModel(workflowJob);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, workflowJob, merge);

			workflowJob.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(WorkflowJobModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowJobImpl.class, workflowJob.getPrimaryKey(), workflowJob);

		return workflowJob;
	}

	protected WorkflowJob toUnwrappedModel(WorkflowJob workflowJob) {
		if (workflowJob instanceof WorkflowJobImpl) {
			return workflowJob;
		}

		WorkflowJobImpl workflowJobImpl = new WorkflowJobImpl();

		workflowJobImpl.setNew(workflowJob.isNew());
		workflowJobImpl.setPrimaryKey(workflowJob.getPrimaryKey());

		workflowJobImpl.setWorkflowJobId(workflowJob.getWorkflowJobId());
		workflowJobImpl.setCompanyId(workflowJob.getCompanyId());
		workflowJobImpl.setCreateDate(workflowJob.getCreateDate());
		workflowJobImpl.setSetupId(workflowJob.getSetupId());
		workflowJobImpl.setWorkflowDefinitionId(workflowJob.getWorkflowDefinitionId());
		workflowJobImpl.setWorkflowInstanceId(workflowJob.getWorkflowInstanceId());
		workflowJobImpl.setElementName(workflowJob.getElementName());
		workflowJobImpl.setCause(workflowJob.getCause());
		workflowJobImpl.setDueDate(workflowJob.getDueDate());
		workflowJobImpl.setNotBeforeDate(workflowJob.getNotBeforeDate());
		workflowJobImpl.setExceptionCount(workflowJob.getExceptionCount());

		return workflowJobImpl;
	}

	public WorkflowJob findByPrimaryKey(long workflowJobId)
		throws NoSuchWorkflowJobException, SystemException {
		WorkflowJob workflowJob = fetchByPrimaryKey(workflowJobId);

		if (workflowJob == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No WorkflowJob exists with the primary key " +
					workflowJobId);
			}

			throw new NoSuchWorkflowJobException(
				"No WorkflowJob exists with the primary key " + workflowJobId);
		}

		return workflowJob;
	}

	public WorkflowJob fetchByPrimaryKey(long workflowJobId)
		throws SystemException {
		WorkflowJob workflowJob = (WorkflowJob)EntityCacheUtil.getResult(WorkflowJobModelImpl.ENTITY_CACHE_ENABLED,
				WorkflowJobImpl.class, workflowJobId, this);

		if (workflowJob == null) {
			Session session = null;

			try {
				session = openSession();

				workflowJob = (WorkflowJob)session.get(WorkflowJobImpl.class,
						new Long(workflowJobId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (workflowJob != null) {
					cacheResult(workflowJob);
				}

				closeSession(session);
			}
		}

		return workflowJob;
	}

	public List<WorkflowJob> findBySetupId(String setupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { setupId };

		List<WorkflowJob> list = (List<WorkflowJob>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_SETUPID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowJob FROM WorkflowJob workflowJob WHERE ");

				if (setupId == null) {
					query.append("workflowJob.setupId IS NULL");
				}
				else {
					query.append("workflowJob.setupId = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (setupId != null) {
					qPos.add(setupId);
				}

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowJob>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_SETUPID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WorkflowJob> findBySetupId(String setupId, int start, int end)
		throws SystemException {
		return findBySetupId(setupId, start, end, null);
	}

	public List<WorkflowJob> findBySetupId(String setupId, int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				setupId,
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WorkflowJob> list = (List<WorkflowJob>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_SETUPID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowJob FROM WorkflowJob workflowJob WHERE ");

				if (setupId == null) {
					query.append("workflowJob.setupId IS NULL");
				}
				else {
					query.append("workflowJob.setupId = ?");
				}

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("workflowJob.");
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

				if (setupId != null) {
					qPos.add(setupId);
				}

				list = (List<WorkflowJob>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowJob>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_SETUPID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WorkflowJob findBySetupId_First(String setupId, OrderByComparator obc)
		throws NoSuchWorkflowJobException, SystemException {
		List<WorkflowJob> list = findBySetupId(setupId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowJob exists with the key {");

			msg.append("setupId=" + setupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowJobException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowJob findBySetupId_Last(String setupId, OrderByComparator obc)
		throws NoSuchWorkflowJobException, SystemException {
		int count = countBySetupId(setupId);

		List<WorkflowJob> list = findBySetupId(setupId, count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowJob exists with the key {");

			msg.append("setupId=" + setupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowJobException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowJob[] findBySetupId_PrevAndNext(long workflowJobId,
		String setupId, OrderByComparator obc)
		throws NoSuchWorkflowJobException, SystemException {
		WorkflowJob workflowJob = findByPrimaryKey(workflowJobId);

		int count = countBySetupId(setupId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT workflowJob FROM WorkflowJob workflowJob WHERE ");

			if (setupId == null) {
				query.append("workflowJob.setupId IS NULL");
			}
			else {
				query.append("workflowJob.setupId = ?");
			}

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("workflowJob.");
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

			if (setupId != null) {
				qPos.add(setupId);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					workflowJob);

			WorkflowJob[] array = new WorkflowJobImpl[3];

			array[0] = (WorkflowJob)objArray[0];
			array[1] = (WorkflowJob)objArray[1];
			array[2] = (WorkflowJob)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<WorkflowJob> findByWorkflowDefinitionId(
		long workflowDefinitionId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(workflowDefinitionId) };

		List<WorkflowJob> list = (List<WorkflowJob>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_WORKFLOWDEFINITIONID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowJob FROM WorkflowJob workflowJob WHERE ");

				query.append("workflowJob.workflowDefinitionId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(workflowDefinitionId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowJob>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_WORKFLOWDEFINITIONID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WorkflowJob> findByWorkflowDefinitionId(
		long workflowDefinitionId, int start, int end)
		throws SystemException {
		return findByWorkflowDefinitionId(workflowDefinitionId, start, end, null);
	}

	public List<WorkflowJob> findByWorkflowDefinitionId(
		long workflowDefinitionId, int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(workflowDefinitionId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WorkflowJob> list = (List<WorkflowJob>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_WORKFLOWDEFINITIONID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowJob FROM WorkflowJob workflowJob WHERE ");

				query.append("workflowJob.workflowDefinitionId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("workflowJob.");
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

				qPos.add(workflowDefinitionId);

				list = (List<WorkflowJob>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowJob>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_WORKFLOWDEFINITIONID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WorkflowJob findByWorkflowDefinitionId_First(
		long workflowDefinitionId, OrderByComparator obc)
		throws NoSuchWorkflowJobException, SystemException {
		List<WorkflowJob> list = findByWorkflowDefinitionId(workflowDefinitionId,
				0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowJob exists with the key {");

			msg.append("workflowDefinitionId=" + workflowDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowJobException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowJob findByWorkflowDefinitionId_Last(
		long workflowDefinitionId, OrderByComparator obc)
		throws NoSuchWorkflowJobException, SystemException {
		int count = countByWorkflowDefinitionId(workflowDefinitionId);

		List<WorkflowJob> list = findByWorkflowDefinitionId(workflowDefinitionId,
				count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowJob exists with the key {");

			msg.append("workflowDefinitionId=" + workflowDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowJobException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowJob[] findByWorkflowDefinitionId_PrevAndNext(
		long workflowJobId, long workflowDefinitionId, OrderByComparator obc)
		throws NoSuchWorkflowJobException, SystemException {
		WorkflowJob workflowJob = findByPrimaryKey(workflowJobId);

		int count = countByWorkflowDefinitionId(workflowDefinitionId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT workflowJob FROM WorkflowJob workflowJob WHERE ");

			query.append("workflowJob.workflowDefinitionId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("workflowJob.");
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

			qPos.add(workflowDefinitionId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					workflowJob);

			WorkflowJob[] array = new WorkflowJobImpl[3];

			array[0] = (WorkflowJob)objArray[0];
			array[1] = (WorkflowJob)objArray[1];
			array[2] = (WorkflowJob)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<WorkflowJob> findByWorkflowInstanceId(long workflowInstanceId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(workflowInstanceId) };

		List<WorkflowJob> list = (List<WorkflowJob>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_WORKFLOWINSTANCEID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowJob FROM WorkflowJob workflowJob WHERE ");

				query.append("workflowJob.workflowInstanceId = ?");

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
					list = new ArrayList<WorkflowJob>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_WORKFLOWINSTANCEID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WorkflowJob> findByWorkflowInstanceId(long workflowInstanceId,
		int start, int end) throws SystemException {
		return findByWorkflowInstanceId(workflowInstanceId, start, end, null);
	}

	public List<WorkflowJob> findByWorkflowInstanceId(long workflowInstanceId,
		int start, int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(workflowInstanceId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WorkflowJob> list = (List<WorkflowJob>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_WORKFLOWINSTANCEID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowJob FROM WorkflowJob workflowJob WHERE ");

				query.append("workflowJob.workflowInstanceId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("workflowJob.");
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

				list = (List<WorkflowJob>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowJob>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_WORKFLOWINSTANCEID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WorkflowJob findByWorkflowInstanceId_First(long workflowInstanceId,
		OrderByComparator obc)
		throws NoSuchWorkflowJobException, SystemException {
		List<WorkflowJob> list = findByWorkflowInstanceId(workflowInstanceId,
				0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowJob exists with the key {");

			msg.append("workflowInstanceId=" + workflowInstanceId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowJobException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowJob findByWorkflowInstanceId_Last(long workflowInstanceId,
		OrderByComparator obc)
		throws NoSuchWorkflowJobException, SystemException {
		int count = countByWorkflowInstanceId(workflowInstanceId);

		List<WorkflowJob> list = findByWorkflowInstanceId(workflowInstanceId,
				count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowJob exists with the key {");

			msg.append("workflowInstanceId=" + workflowInstanceId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowJobException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowJob[] findByWorkflowInstanceId_PrevAndNext(
		long workflowJobId, long workflowInstanceId, OrderByComparator obc)
		throws NoSuchWorkflowJobException, SystemException {
		WorkflowJob workflowJob = findByPrimaryKey(workflowJobId);

		int count = countByWorkflowInstanceId(workflowInstanceId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT workflowJob FROM WorkflowJob workflowJob WHERE ");

			query.append("workflowJob.workflowInstanceId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("workflowJob.");
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
					workflowJob);

			WorkflowJob[] array = new WorkflowJobImpl[3];

			array[0] = (WorkflowJob)objArray[0];
			array[1] = (WorkflowJob)objArray[1];
			array[2] = (WorkflowJob)objArray[2];

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

	public List<WorkflowJob> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<WorkflowJob> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<WorkflowJob> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WorkflowJob> list = (List<WorkflowJob>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT workflowJob FROM WorkflowJob workflowJob ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("workflowJob.");
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
					list = (List<WorkflowJob>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<WorkflowJob>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowJob>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeBySetupId(String setupId) throws SystemException {
		for (WorkflowJob workflowJob : findBySetupId(setupId)) {
			remove(workflowJob);
		}
	}

	public void removeByWorkflowDefinitionId(long workflowDefinitionId)
		throws SystemException {
		for (WorkflowJob workflowJob : findByWorkflowDefinitionId(
				workflowDefinitionId)) {
			remove(workflowJob);
		}
	}

	public void removeByWorkflowInstanceId(long workflowInstanceId)
		throws SystemException {
		for (WorkflowJob workflowJob : findByWorkflowInstanceId(
				workflowInstanceId)) {
			remove(workflowJob);
		}
	}

	public void removeAll() throws SystemException {
		for (WorkflowJob workflowJob : findAll()) {
			remove(workflowJob);
		}
	}

	public int countBySetupId(String setupId) throws SystemException {
		Object[] finderArgs = new Object[] { setupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SETUPID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(workflowJob) ");
				query.append("FROM WorkflowJob workflowJob WHERE ");

				if (setupId == null) {
					query.append("workflowJob.setupId IS NULL");
				}
				else {
					query.append("workflowJob.setupId = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (setupId != null) {
					qPos.add(setupId);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SETUPID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByWorkflowDefinitionId(long workflowDefinitionId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(workflowDefinitionId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_WORKFLOWDEFINITIONID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(workflowJob) ");
				query.append("FROM WorkflowJob workflowJob WHERE ");

				query.append("workflowJob.workflowDefinitionId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(workflowDefinitionId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_WORKFLOWDEFINITIONID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
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

				query.append("SELECT COUNT(workflowJob) ");
				query.append("FROM WorkflowJob workflowJob WHERE ");

				query.append("workflowJob.workflowInstanceId = ?");

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

	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(
						"SELECT COUNT(workflowJob) FROM WorkflowJob workflowJob");

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
						"value.object.listener.com.liferay.portal.workflow.edoras.model.WorkflowJob")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<WorkflowJob>> listenersList = new ArrayList<ModelListener<WorkflowJob>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<WorkflowJob>)Class.forName(
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
	private static Log _log = LogFactoryUtil.getLog(WorkflowJobPersistenceImpl.class);
}