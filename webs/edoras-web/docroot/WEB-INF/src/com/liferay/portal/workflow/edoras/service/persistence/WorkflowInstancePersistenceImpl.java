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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;
import com.liferay.portal.workflow.edoras.model.WorkflowInstance;
import com.liferay.portal.workflow.edoras.model.impl.WorkflowInstanceImpl;
import com.liferay.portal.workflow.edoras.model.impl.WorkflowInstanceModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="WorkflowInstancePersistenceImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowInstancePersistenceImpl extends BasePersistenceImpl
	implements WorkflowInstancePersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = WorkflowInstanceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_SETUPID = new FinderPath(WorkflowInstanceModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findBySetupId",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_SETUPID = new FinderPath(WorkflowInstanceModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findBySetupId",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_SETUPID = new FinderPath(WorkflowInstanceModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countBySetupId",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_WORKFLOWDEFINITIONID = new FinderPath(WorkflowInstanceModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByWorkflowDefinitionId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_WORKFLOWDEFINITIONID = new FinderPath(WorkflowInstanceModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByWorkflowDefinitionId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_WORKFLOWDEFINITIONID = new FinderPath(WorkflowInstanceModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByWorkflowDefinitionId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_C_P = new FinderPath(WorkflowInstanceModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByC_P",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_C_P = new FinderPath(WorkflowInstanceModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByC_P",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_C_P = new FinderPath(WorkflowInstanceModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByC_P",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_C_FID = new FinderPath(WorkflowInstanceModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_ENTITY, "fetchByC_FID",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_C_FID = new FinderPath(WorkflowInstanceModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByC_FID",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_C_F = new FinderPath(WorkflowInstanceModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByC_F",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_C_F = new FinderPath(WorkflowInstanceModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByC_F",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_C_F = new FinderPath(WorkflowInstanceModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByC_F",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_C_C = new FinderPath(WorkflowInstanceModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByC_C",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_C_C = new FinderPath(WorkflowInstanceModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByC_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(WorkflowInstanceModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByC_C",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(WorkflowInstanceModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WorkflowInstanceModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	public void cacheResult(WorkflowInstance workflowInstance) {
		EntityCacheUtil.putResult(WorkflowInstanceModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceImpl.class, workflowInstance.getPrimaryKey(),
			workflowInstance);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_FID,
			new Object[] {
				new Long(workflowInstance.getCompanyId()),
				
			workflowInstance.getFriendlyId()
			}, workflowInstance);
	}

	public void cacheResult(List<WorkflowInstance> workflowInstances) {
		for (WorkflowInstance workflowInstance : workflowInstances) {
			if (EntityCacheUtil.getResult(
						WorkflowInstanceModelImpl.ENTITY_CACHE_ENABLED,
						WorkflowInstanceImpl.class,
						workflowInstance.getPrimaryKey(), this) == null) {
				cacheResult(workflowInstance);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(WorkflowInstanceImpl.class.getName());
		EntityCacheUtil.clearCache(WorkflowInstanceImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public WorkflowInstance create(long workflowInstanceId) {
		WorkflowInstance workflowInstance = new WorkflowInstanceImpl();

		workflowInstance.setNew(true);
		workflowInstance.setPrimaryKey(workflowInstanceId);

		return workflowInstance;
	}

	public WorkflowInstance remove(long workflowInstanceId)
		throws NoSuchWorkflowInstanceException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WorkflowInstance workflowInstance = (WorkflowInstance)session.get(WorkflowInstanceImpl.class,
					new Long(workflowInstanceId));

			if (workflowInstance == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"No WorkflowInstance exists with the primary key " +
						workflowInstanceId);
				}

				throw new NoSuchWorkflowInstanceException(
					"No WorkflowInstance exists with the primary key " +
					workflowInstanceId);
			}

			return remove(workflowInstance);
		}
		catch (NoSuchWorkflowInstanceException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public WorkflowInstance remove(WorkflowInstance workflowInstance)
		throws SystemException {
		for (ModelListener<WorkflowInstance> listener : listeners) {
			listener.onBeforeRemove(workflowInstance);
		}

		workflowInstance = removeImpl(workflowInstance);

		for (ModelListener<WorkflowInstance> listener : listeners) {
			listener.onAfterRemove(workflowInstance);
		}

		return workflowInstance;
	}

	protected WorkflowInstance removeImpl(WorkflowInstance workflowInstance)
		throws SystemException {
		workflowInstance = toUnwrappedModel(workflowInstance);

		Session session = null;

		try {
			session = openSession();

			if (workflowInstance.isCachedModel() ||
					BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(WorkflowInstanceImpl.class,
						workflowInstance.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(workflowInstance);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		WorkflowInstanceModelImpl workflowInstanceModelImpl = (WorkflowInstanceModelImpl)workflowInstance;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_FID,
			new Object[] {
				new Long(workflowInstanceModelImpl.getOriginalCompanyId()),
				
			workflowInstanceModelImpl.getOriginalFriendlyId()
			});

		EntityCacheUtil.removeResult(WorkflowInstanceModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceImpl.class, workflowInstance.getPrimaryKey());

		return workflowInstance;
	}

	public WorkflowInstance update(WorkflowInstance workflowInstance)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(WorkflowInstance workflowInstance) method. Use update(WorkflowInstance workflowInstance, boolean merge) instead.");
		}

		return update(workflowInstance, false);
	}

	public WorkflowInstance update(WorkflowInstance workflowInstance,
		boolean merge) throws SystemException {
		boolean isNew = workflowInstance.isNew();

		for (ModelListener<WorkflowInstance> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(workflowInstance);
			}
			else {
				listener.onBeforeUpdate(workflowInstance);
			}
		}

		workflowInstance = updateImpl(workflowInstance, merge);

		for (ModelListener<WorkflowInstance> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(workflowInstance);
			}
			else {
				listener.onAfterUpdate(workflowInstance);
			}
		}

		return workflowInstance;
	}

	public WorkflowInstance updateImpl(
		com.liferay.portal.workflow.edoras.model.WorkflowInstance workflowInstance,
		boolean merge) throws SystemException {
		workflowInstance = toUnwrappedModel(workflowInstance);

		boolean isNew = workflowInstance.isNew();

		WorkflowInstanceModelImpl workflowInstanceModelImpl = (WorkflowInstanceModelImpl)workflowInstance;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, workflowInstance, merge);

			workflowInstance.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(WorkflowInstanceModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowInstanceImpl.class, workflowInstance.getPrimaryKey(),
			workflowInstance);

		if (!isNew &&
				((workflowInstance.getCompanyId() != workflowInstanceModelImpl.getOriginalCompanyId()) ||
				!Validator.equals(workflowInstance.getFriendlyId(),
					workflowInstanceModelImpl.getOriginalFriendlyId()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_FID,
				new Object[] {
					new Long(workflowInstanceModelImpl.getOriginalCompanyId()),
					
				workflowInstanceModelImpl.getOriginalFriendlyId()
				});
		}

		if (isNew ||
				((workflowInstance.getCompanyId() != workflowInstanceModelImpl.getOriginalCompanyId()) ||
				!Validator.equals(workflowInstance.getFriendlyId(),
					workflowInstanceModelImpl.getOriginalFriendlyId()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_FID,
				new Object[] {
					new Long(workflowInstance.getCompanyId()),
					
				workflowInstance.getFriendlyId()
				}, workflowInstance);
		}

		return workflowInstance;
	}

	protected WorkflowInstance toUnwrappedModel(
		WorkflowInstance workflowInstance) {
		if (workflowInstance instanceof WorkflowInstanceImpl) {
			return workflowInstance;
		}

		WorkflowInstanceImpl workflowInstanceImpl = new WorkflowInstanceImpl();

		workflowInstanceImpl.setNew(workflowInstance.isNew());
		workflowInstanceImpl.setPrimaryKey(workflowInstance.getPrimaryKey());

		workflowInstanceImpl.setWorkflowInstanceId(workflowInstance.getWorkflowInstanceId());
		workflowInstanceImpl.setCompanyId(workflowInstance.getCompanyId());
		workflowInstanceImpl.setUserId(workflowInstance.getUserId());
		workflowInstanceImpl.setUserName(workflowInstance.getUserName());
		workflowInstanceImpl.setCreateDate(workflowInstance.getCreateDate());
		workflowInstanceImpl.setModifiedDate(workflowInstance.getModifiedDate());
		workflowInstanceImpl.setSetupId(workflowInstance.getSetupId());
		workflowInstanceImpl.setFriendlyId(workflowInstance.getFriendlyId());
		workflowInstanceImpl.setWorkflowDefinitionId(workflowInstance.getWorkflowDefinitionId());
		workflowInstanceImpl.setParentWorkflowInstanceId(workflowInstance.getParentWorkflowInstanceId());
		workflowInstanceImpl.setClassName(workflowInstance.getClassName());
		workflowInstanceImpl.setClassPK(workflowInstance.getClassPK());
		workflowInstanceImpl.setAttributes(workflowInstance.getAttributes());
		workflowInstanceImpl.setNestedWorkflowDefinitionIds(workflowInstance.getNestedWorkflowDefinitionIds());
		workflowInstanceImpl.setNestedWorkflowDefinitionVersions(workflowInstance.getNestedWorkflowDefinitionVersions());
		workflowInstanceImpl.setNestedRelatedElements(workflowInstance.getNestedRelatedElements());
		workflowInstanceImpl.setCurrentElementName(workflowInstance.getCurrentElementName());
		workflowInstanceImpl.setRelatedElementName(workflowInstance.getRelatedElementName());
		workflowInstanceImpl.setFinished(workflowInstance.isFinished());
		workflowInstanceImpl.setFinishedDated(workflowInstance.getFinishedDated());
		workflowInstanceImpl.setActive(workflowInstance.isActive());

		return workflowInstanceImpl;
	}

	public WorkflowInstance findByPrimaryKey(long workflowInstanceId)
		throws NoSuchWorkflowInstanceException, SystemException {
		WorkflowInstance workflowInstance = fetchByPrimaryKey(workflowInstanceId);

		if (workflowInstance == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No WorkflowInstance exists with the primary key " +
					workflowInstanceId);
			}

			throw new NoSuchWorkflowInstanceException(
				"No WorkflowInstance exists with the primary key " +
				workflowInstanceId);
		}

		return workflowInstance;
	}

	public WorkflowInstance fetchByPrimaryKey(long workflowInstanceId)
		throws SystemException {
		WorkflowInstance workflowInstance = (WorkflowInstance)EntityCacheUtil.getResult(WorkflowInstanceModelImpl.ENTITY_CACHE_ENABLED,
				WorkflowInstanceImpl.class, workflowInstanceId, this);

		if (workflowInstance == null) {
			Session session = null;

			try {
				session = openSession();

				workflowInstance = (WorkflowInstance)session.get(WorkflowInstanceImpl.class,
						new Long(workflowInstanceId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (workflowInstance != null) {
					cacheResult(workflowInstance);
				}

				closeSession(session);
			}
		}

		return workflowInstance;
	}

	public List<WorkflowInstance> findBySetupId(String setupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { setupId };

		List<WorkflowInstance> list = (List<WorkflowInstance>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_SETUPID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowInstance FROM WorkflowInstance workflowInstance WHERE ");

				if (setupId == null) {
					query.append("workflowInstance.setupId IS NULL");
				}
				else {
					query.append("workflowInstance.setupId = ?");
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
					list = new ArrayList<WorkflowInstance>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_SETUPID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WorkflowInstance> findBySetupId(String setupId, int start,
		int end) throws SystemException {
		return findBySetupId(setupId, start, end, null);
	}

	public List<WorkflowInstance> findBySetupId(String setupId, int start,
		int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				setupId,
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WorkflowInstance> list = (List<WorkflowInstance>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_SETUPID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowInstance FROM WorkflowInstance workflowInstance WHERE ");

				if (setupId == null) {
					query.append("workflowInstance.setupId IS NULL");
				}
				else {
					query.append("workflowInstance.setupId = ?");
				}

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("workflowInstance.");
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

				list = (List<WorkflowInstance>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowInstance>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_SETUPID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WorkflowInstance findBySetupId_First(String setupId,
		OrderByComparator obc)
		throws NoSuchWorkflowInstanceException, SystemException {
		List<WorkflowInstance> list = findBySetupId(setupId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowInstance exists with the key {");

			msg.append("setupId=" + setupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowInstance findBySetupId_Last(String setupId,
		OrderByComparator obc)
		throws NoSuchWorkflowInstanceException, SystemException {
		int count = countBySetupId(setupId);

		List<WorkflowInstance> list = findBySetupId(setupId, count - 1, count,
				obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowInstance exists with the key {");

			msg.append("setupId=" + setupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowInstance[] findBySetupId_PrevAndNext(
		long workflowInstanceId, String setupId, OrderByComparator obc)
		throws NoSuchWorkflowInstanceException, SystemException {
		WorkflowInstance workflowInstance = findByPrimaryKey(workflowInstanceId);

		int count = countBySetupId(setupId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT workflowInstance FROM WorkflowInstance workflowInstance WHERE ");

			if (setupId == null) {
				query.append("workflowInstance.setupId IS NULL");
			}
			else {
				query.append("workflowInstance.setupId = ?");
			}

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("workflowInstance.");
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
					workflowInstance);

			WorkflowInstance[] array = new WorkflowInstanceImpl[3];

			array[0] = (WorkflowInstance)objArray[0];
			array[1] = (WorkflowInstance)objArray[1];
			array[2] = (WorkflowInstance)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<WorkflowInstance> findByWorkflowDefinitionId(
		long workflowDefinitionId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(workflowDefinitionId) };

		List<WorkflowInstance> list = (List<WorkflowInstance>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_WORKFLOWDEFINITIONID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowInstance FROM WorkflowInstance workflowInstance WHERE ");

				query.append("workflowInstance.workflowDefinitionId = ?");

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
					list = new ArrayList<WorkflowInstance>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_WORKFLOWDEFINITIONID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WorkflowInstance> findByWorkflowDefinitionId(
		long workflowDefinitionId, int start, int end)
		throws SystemException {
		return findByWorkflowDefinitionId(workflowDefinitionId, start, end, null);
	}

	public List<WorkflowInstance> findByWorkflowDefinitionId(
		long workflowDefinitionId, int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(workflowDefinitionId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WorkflowInstance> list = (List<WorkflowInstance>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_WORKFLOWDEFINITIONID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowInstance FROM WorkflowInstance workflowInstance WHERE ");

				query.append("workflowInstance.workflowDefinitionId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("workflowInstance.");
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

				list = (List<WorkflowInstance>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowInstance>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_WORKFLOWDEFINITIONID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WorkflowInstance findByWorkflowDefinitionId_First(
		long workflowDefinitionId, OrderByComparator obc)
		throws NoSuchWorkflowInstanceException, SystemException {
		List<WorkflowInstance> list = findByWorkflowDefinitionId(workflowDefinitionId,
				0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowInstance exists with the key {");

			msg.append("workflowDefinitionId=" + workflowDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowInstance findByWorkflowDefinitionId_Last(
		long workflowDefinitionId, OrderByComparator obc)
		throws NoSuchWorkflowInstanceException, SystemException {
		int count = countByWorkflowDefinitionId(workflowDefinitionId);

		List<WorkflowInstance> list = findByWorkflowDefinitionId(workflowDefinitionId,
				count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowInstance exists with the key {");

			msg.append("workflowDefinitionId=" + workflowDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowInstance[] findByWorkflowDefinitionId_PrevAndNext(
		long workflowInstanceId, long workflowDefinitionId,
		OrderByComparator obc)
		throws NoSuchWorkflowInstanceException, SystemException {
		WorkflowInstance workflowInstance = findByPrimaryKey(workflowInstanceId);

		int count = countByWorkflowDefinitionId(workflowDefinitionId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT workflowInstance FROM WorkflowInstance workflowInstance WHERE ");

			query.append("workflowInstance.workflowDefinitionId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("workflowInstance.");
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
					workflowInstance);

			WorkflowInstance[] array = new WorkflowInstanceImpl[3];

			array[0] = (WorkflowInstance)objArray[0];
			array[1] = (WorkflowInstance)objArray[1];
			array[2] = (WorkflowInstance)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<WorkflowInstance> findByC_P(long companyId,
		long parentWorkflowInstanceId) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId), new Long(parentWorkflowInstanceId)
			};

		List<WorkflowInstance> list = (List<WorkflowInstance>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_C_P,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowInstance FROM WorkflowInstance workflowInstance WHERE ");

				query.append("workflowInstance.companyId = ?");

				query.append(" AND ");

				query.append("workflowInstance.parentWorkflowInstanceId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(parentWorkflowInstanceId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowInstance>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_C_P, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WorkflowInstance> findByC_P(long companyId,
		long parentWorkflowInstanceId, int start, int end)
		throws SystemException {
		return findByC_P(companyId, parentWorkflowInstanceId, start, end, null);
	}

	public List<WorkflowInstance> findByC_P(long companyId,
		long parentWorkflowInstanceId, int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId), new Long(parentWorkflowInstanceId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WorkflowInstance> list = (List<WorkflowInstance>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_C_P,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowInstance FROM WorkflowInstance workflowInstance WHERE ");

				query.append("workflowInstance.companyId = ?");

				query.append(" AND ");

				query.append("workflowInstance.parentWorkflowInstanceId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("workflowInstance.");
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

				qPos.add(parentWorkflowInstanceId);

				list = (List<WorkflowInstance>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowInstance>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_C_P,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WorkflowInstance findByC_P_First(long companyId,
		long parentWorkflowInstanceId, OrderByComparator obc)
		throws NoSuchWorkflowInstanceException, SystemException {
		List<WorkflowInstance> list = findByC_P(companyId,
				parentWorkflowInstanceId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowInstance exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(", ");
			msg.append("parentWorkflowInstanceId=" + parentWorkflowInstanceId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowInstance findByC_P_Last(long companyId,
		long parentWorkflowInstanceId, OrderByComparator obc)
		throws NoSuchWorkflowInstanceException, SystemException {
		int count = countByC_P(companyId, parentWorkflowInstanceId);

		List<WorkflowInstance> list = findByC_P(companyId,
				parentWorkflowInstanceId, count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowInstance exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(", ");
			msg.append("parentWorkflowInstanceId=" + parentWorkflowInstanceId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowInstance[] findByC_P_PrevAndNext(long workflowInstanceId,
		long companyId, long parentWorkflowInstanceId, OrderByComparator obc)
		throws NoSuchWorkflowInstanceException, SystemException {
		WorkflowInstance workflowInstance = findByPrimaryKey(workflowInstanceId);

		int count = countByC_P(companyId, parentWorkflowInstanceId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT workflowInstance FROM WorkflowInstance workflowInstance WHERE ");

			query.append("workflowInstance.companyId = ?");

			query.append(" AND ");

			query.append("workflowInstance.parentWorkflowInstanceId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("workflowInstance.");
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

			qPos.add(parentWorkflowInstanceId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					workflowInstance);

			WorkflowInstance[] array = new WorkflowInstanceImpl[3];

			array[0] = (WorkflowInstance)objArray[0];
			array[1] = (WorkflowInstance)objArray[1];
			array[2] = (WorkflowInstance)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public WorkflowInstance findByC_FID(long companyId, String friendlyId)
		throws NoSuchWorkflowInstanceException, SystemException {
		WorkflowInstance workflowInstance = fetchByC_FID(companyId, friendlyId);

		if (workflowInstance == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowInstance exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(", ");
			msg.append("friendlyId=" + friendlyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchWorkflowInstanceException(msg.toString());
		}

		return workflowInstance;
	}

	public WorkflowInstance fetchByC_FID(long companyId, String friendlyId)
		throws SystemException {
		return fetchByC_FID(companyId, friendlyId, true);
	}

	public WorkflowInstance fetchByC_FID(long companyId, String friendlyId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(companyId), friendlyId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_FID,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowInstance FROM WorkflowInstance workflowInstance WHERE ");

				query.append("workflowInstance.companyId = ?");

				query.append(" AND ");

				if (friendlyId == null) {
					query.append("workflowInstance.friendlyId IS NULL");
				}
				else {
					query.append("workflowInstance.friendlyId = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (friendlyId != null) {
					qPos.add(friendlyId);
				}

				List<WorkflowInstance> list = q.list();

				result = list;

				WorkflowInstance workflowInstance = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_FID,
						finderArgs, list);
				}
				else {
					workflowInstance = list.get(0);

					cacheResult(workflowInstance);

					if ((workflowInstance.getCompanyId() != companyId) ||
							(workflowInstance.getFriendlyId() == null) ||
							!workflowInstance.getFriendlyId().equals(friendlyId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_FID,
							finderArgs, workflowInstance);
					}
				}

				return workflowInstance;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_FID,
						finderArgs, new ArrayList<WorkflowInstance>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (WorkflowInstance)result;
			}
		}
	}

	public List<WorkflowInstance> findByC_F(long companyId, boolean finished)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId), Boolean.valueOf(finished)
			};

		List<WorkflowInstance> list = (List<WorkflowInstance>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_C_F,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowInstance FROM WorkflowInstance workflowInstance WHERE ");

				query.append("workflowInstance.companyId = ?");

				query.append(" AND ");

				query.append("workflowInstance.finished = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(finished);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowInstance>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_C_F, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WorkflowInstance> findByC_F(long companyId, boolean finished,
		int start, int end) throws SystemException {
		return findByC_F(companyId, finished, start, end, null);
	}

	public List<WorkflowInstance> findByC_F(long companyId, boolean finished,
		int start, int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId), Boolean.valueOf(finished),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WorkflowInstance> list = (List<WorkflowInstance>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_C_F,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowInstance FROM WorkflowInstance workflowInstance WHERE ");

				query.append("workflowInstance.companyId = ?");

				query.append(" AND ");

				query.append("workflowInstance.finished = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("workflowInstance.");
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

				qPos.add(finished);

				list = (List<WorkflowInstance>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowInstance>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_C_F,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WorkflowInstance findByC_F_First(long companyId, boolean finished,
		OrderByComparator obc)
		throws NoSuchWorkflowInstanceException, SystemException {
		List<WorkflowInstance> list = findByC_F(companyId, finished, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowInstance exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(", ");
			msg.append("finished=" + finished);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowInstance findByC_F_Last(long companyId, boolean finished,
		OrderByComparator obc)
		throws NoSuchWorkflowInstanceException, SystemException {
		int count = countByC_F(companyId, finished);

		List<WorkflowInstance> list = findByC_F(companyId, finished, count - 1,
				count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowInstance exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(", ");
			msg.append("finished=" + finished);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowInstance[] findByC_F_PrevAndNext(long workflowInstanceId,
		long companyId, boolean finished, OrderByComparator obc)
		throws NoSuchWorkflowInstanceException, SystemException {
		WorkflowInstance workflowInstance = findByPrimaryKey(workflowInstanceId);

		int count = countByC_F(companyId, finished);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT workflowInstance FROM WorkflowInstance workflowInstance WHERE ");

			query.append("workflowInstance.companyId = ?");

			query.append(" AND ");

			query.append("workflowInstance.finished = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("workflowInstance.");
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

			qPos.add(finished);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					workflowInstance);

			WorkflowInstance[] array = new WorkflowInstanceImpl[3];

			array[0] = (WorkflowInstance)objArray[0];
			array[1] = (WorkflowInstance)objArray[1];
			array[2] = (WorkflowInstance)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<WorkflowInstance> findByC_C(String className, long classPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { className, new Long(classPK) };

		List<WorkflowInstance> list = (List<WorkflowInstance>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_C_C,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowInstance FROM WorkflowInstance workflowInstance WHERE ");

				if (className == null) {
					query.append("workflowInstance.className IS NULL");
				}
				else {
					query.append("workflowInstance.className = ?");
				}

				query.append(" AND ");

				query.append("workflowInstance.classPK = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (className != null) {
					qPos.add(className);
				}

				qPos.add(classPK);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowInstance>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_C_C, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WorkflowInstance> findByC_C(String className, long classPK,
		int start, int end) throws SystemException {
		return findByC_C(className, classPK, start, end, null);
	}

	public List<WorkflowInstance> findByC_C(String className, long classPK,
		int start, int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				className, new Long(classPK),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WorkflowInstance> list = (List<WorkflowInstance>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_C_C,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowInstance FROM WorkflowInstance workflowInstance WHERE ");

				if (className == null) {
					query.append("workflowInstance.className IS NULL");
				}
				else {
					query.append("workflowInstance.className = ?");
				}

				query.append(" AND ");

				query.append("workflowInstance.classPK = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("workflowInstance.");
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

				if (className != null) {
					qPos.add(className);
				}

				qPos.add(classPK);

				list = (List<WorkflowInstance>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowInstance>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_C_C,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WorkflowInstance findByC_C_First(String className, long classPK,
		OrderByComparator obc)
		throws NoSuchWorkflowInstanceException, SystemException {
		List<WorkflowInstance> list = findByC_C(className, classPK, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowInstance exists with the key {");

			msg.append("className=" + className);

			msg.append(", ");
			msg.append("classPK=" + classPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowInstance findByC_C_Last(String className, long classPK,
		OrderByComparator obc)
		throws NoSuchWorkflowInstanceException, SystemException {
		int count = countByC_C(className, classPK);

		List<WorkflowInstance> list = findByC_C(className, classPK, count - 1,
				count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowInstance exists with the key {");

			msg.append("className=" + className);

			msg.append(", ");
			msg.append("classPK=" + classPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowInstance[] findByC_C_PrevAndNext(long workflowInstanceId,
		String className, long classPK, OrderByComparator obc)
		throws NoSuchWorkflowInstanceException, SystemException {
		WorkflowInstance workflowInstance = findByPrimaryKey(workflowInstanceId);

		int count = countByC_C(className, classPK);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT workflowInstance FROM WorkflowInstance workflowInstance WHERE ");

			if (className == null) {
				query.append("workflowInstance.className IS NULL");
			}
			else {
				query.append("workflowInstance.className = ?");
			}

			query.append(" AND ");

			query.append("workflowInstance.classPK = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("workflowInstance.");
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

			if (className != null) {
				qPos.add(className);
			}

			qPos.add(classPK);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					workflowInstance);

			WorkflowInstance[] array = new WorkflowInstanceImpl[3];

			array[0] = (WorkflowInstance)objArray[0];
			array[1] = (WorkflowInstance)objArray[1];
			array[2] = (WorkflowInstance)objArray[2];

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

	public List<WorkflowInstance> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<WorkflowInstance> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<WorkflowInstance> findAll(int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WorkflowInstance> list = (List<WorkflowInstance>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowInstance FROM WorkflowInstance workflowInstance ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("workflowInstance.");
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
					list = (List<WorkflowInstance>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<WorkflowInstance>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowInstance>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeBySetupId(String setupId) throws SystemException {
		for (WorkflowInstance workflowInstance : findBySetupId(setupId)) {
			remove(workflowInstance);
		}
	}

	public void removeByWorkflowDefinitionId(long workflowDefinitionId)
		throws SystemException {
		for (WorkflowInstance workflowInstance : findByWorkflowDefinitionId(
				workflowDefinitionId)) {
			remove(workflowInstance);
		}
	}

	public void removeByC_P(long companyId, long parentWorkflowInstanceId)
		throws SystemException {
		for (WorkflowInstance workflowInstance : findByC_P(companyId,
				parentWorkflowInstanceId)) {
			remove(workflowInstance);
		}
	}

	public void removeByC_FID(long companyId, String friendlyId)
		throws NoSuchWorkflowInstanceException, SystemException {
		WorkflowInstance workflowInstance = findByC_FID(companyId, friendlyId);

		remove(workflowInstance);
	}

	public void removeByC_F(long companyId, boolean finished)
		throws SystemException {
		for (WorkflowInstance workflowInstance : findByC_F(companyId, finished)) {
			remove(workflowInstance);
		}
	}

	public void removeByC_C(String className, long classPK)
		throws SystemException {
		for (WorkflowInstance workflowInstance : findByC_C(className, classPK)) {
			remove(workflowInstance);
		}
	}

	public void removeAll() throws SystemException {
		for (WorkflowInstance workflowInstance : findAll()) {
			remove(workflowInstance);
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

				query.append("SELECT COUNT(workflowInstance) ");
				query.append("FROM WorkflowInstance workflowInstance WHERE ");

				if (setupId == null) {
					query.append("workflowInstance.setupId IS NULL");
				}
				else {
					query.append("workflowInstance.setupId = ?");
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

				query.append("SELECT COUNT(workflowInstance) ");
				query.append("FROM WorkflowInstance workflowInstance WHERE ");

				query.append("workflowInstance.workflowDefinitionId = ?");

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

	public int countByC_P(long companyId, long parentWorkflowInstanceId)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId), new Long(parentWorkflowInstanceId)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_P,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(workflowInstance) ");
				query.append("FROM WorkflowInstance workflowInstance WHERE ");

				query.append("workflowInstance.companyId = ?");

				query.append(" AND ");

				query.append("workflowInstance.parentWorkflowInstanceId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(parentWorkflowInstanceId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_P, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByC_FID(long companyId, String friendlyId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(companyId), friendlyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_FID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(workflowInstance) ");
				query.append("FROM WorkflowInstance workflowInstance WHERE ");

				query.append("workflowInstance.companyId = ?");

				query.append(" AND ");

				if (friendlyId == null) {
					query.append("workflowInstance.friendlyId IS NULL");
				}
				else {
					query.append("workflowInstance.friendlyId = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (friendlyId != null) {
					qPos.add(friendlyId);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_FID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByC_F(long companyId, boolean finished)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId), Boolean.valueOf(finished)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_F,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(workflowInstance) ");
				query.append("FROM WorkflowInstance workflowInstance WHERE ");

				query.append("workflowInstance.companyId = ?");

				query.append(" AND ");

				query.append("workflowInstance.finished = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(finished);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_F, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByC_C(String className, long classPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { className, new Long(classPK) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_C,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(workflowInstance) ");
				query.append("FROM WorkflowInstance workflowInstance WHERE ");

				if (className == null) {
					query.append("workflowInstance.className IS NULL");
				}
				else {
					query.append("workflowInstance.className = ?");
				}

				query.append(" AND ");

				query.append("workflowInstance.classPK = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (className != null) {
					qPos.add(className);
				}

				qPos.add(classPK);

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

	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(
						"SELECT COUNT(workflowInstance) FROM WorkflowInstance workflowInstance");

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
						"value.object.listener.com.liferay.portal.workflow.edoras.model.WorkflowInstance")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<WorkflowInstance>> listenersList = new ArrayList<ModelListener<WorkflowInstance>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<WorkflowInstance>)Class.forName(
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
	private static Log _log = LogFactoryUtil.getLog(WorkflowInstancePersistenceImpl.class);
}