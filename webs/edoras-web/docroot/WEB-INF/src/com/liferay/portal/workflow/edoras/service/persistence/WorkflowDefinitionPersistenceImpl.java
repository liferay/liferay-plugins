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
import com.liferay.portal.workflow.edoras.NoSuchWorkflowDefinitionException;
import com.liferay.portal.workflow.edoras.model.WorkflowDefinition;
import com.liferay.portal.workflow.edoras.model.impl.WorkflowDefinitionImpl;
import com.liferay.portal.workflow.edoras.model.impl.WorkflowDefinitionModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="WorkflowDefinitionPersistenceImpl.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowDefinitionPersistenceImpl extends BasePersistenceImpl
	implements WorkflowDefinitionPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = WorkflowDefinitionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_C_N = new FinderPath(WorkflowDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByC_N",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_C_N = new FinderPath(WorkflowDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByC_N",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_C_N = new FinderPath(WorkflowDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByC_N",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_C_N_V = new FinderPath(WorkflowDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_ENTITY, "fetchByC_N_V",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Double.class.getName()
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_C_N_V = new FinderPath(WorkflowDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByC_N_V",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Double.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(WorkflowDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WorkflowDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	public void cacheResult(WorkflowDefinition workflowDefinition) {
		EntityCacheUtil.putResult(WorkflowDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionImpl.class, workflowDefinition.getPrimaryKey(),
			workflowDefinition);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_N_V,
			new Object[] {
				new Long(workflowDefinition.getCompanyId()),
				
			workflowDefinition.getName(),
				new Double(workflowDefinition.getVersion())
			}, workflowDefinition);
	}

	public void cacheResult(List<WorkflowDefinition> workflowDefinitions) {
		for (WorkflowDefinition workflowDefinition : workflowDefinitions) {
			if (EntityCacheUtil.getResult(
						WorkflowDefinitionModelImpl.ENTITY_CACHE_ENABLED,
						WorkflowDefinitionImpl.class,
						workflowDefinition.getPrimaryKey(), this) == null) {
				cacheResult(workflowDefinition);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(WorkflowDefinitionImpl.class.getName());
		EntityCacheUtil.clearCache(WorkflowDefinitionImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public WorkflowDefinition create(long workflowDefinitionId) {
		WorkflowDefinition workflowDefinition = new WorkflowDefinitionImpl();

		workflowDefinition.setNew(true);
		workflowDefinition.setPrimaryKey(workflowDefinitionId);

		return workflowDefinition;
	}

	public WorkflowDefinition remove(long workflowDefinitionId)
		throws NoSuchWorkflowDefinitionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WorkflowDefinition workflowDefinition = (WorkflowDefinition)session.get(WorkflowDefinitionImpl.class,
					new Long(workflowDefinitionId));

			if (workflowDefinition == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"No WorkflowDefinition exists with the primary key " +
						workflowDefinitionId);
				}

				throw new NoSuchWorkflowDefinitionException(
					"No WorkflowDefinition exists with the primary key " +
					workflowDefinitionId);
			}

			return remove(workflowDefinition);
		}
		catch (NoSuchWorkflowDefinitionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public WorkflowDefinition remove(WorkflowDefinition workflowDefinition)
		throws SystemException {
		for (ModelListener<WorkflowDefinition> listener : listeners) {
			listener.onBeforeRemove(workflowDefinition);
		}

		workflowDefinition = removeImpl(workflowDefinition);

		for (ModelListener<WorkflowDefinition> listener : listeners) {
			listener.onAfterRemove(workflowDefinition);
		}

		return workflowDefinition;
	}

	protected WorkflowDefinition removeImpl(
		WorkflowDefinition workflowDefinition) throws SystemException {
		workflowDefinition = toUnwrappedModel(workflowDefinition);

		Session session = null;

		try {
			session = openSession();

			if (workflowDefinition.isCachedModel() ||
					BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(WorkflowDefinitionImpl.class,
						workflowDefinition.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(workflowDefinition);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		WorkflowDefinitionModelImpl workflowDefinitionModelImpl = (WorkflowDefinitionModelImpl)workflowDefinition;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_N_V,
			new Object[] {
				new Long(workflowDefinitionModelImpl.getOriginalCompanyId()),
				
			workflowDefinitionModelImpl.getOriginalName(),
				new Double(workflowDefinitionModelImpl.getOriginalVersion())
			});

		EntityCacheUtil.removeResult(WorkflowDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionImpl.class, workflowDefinition.getPrimaryKey());

		return workflowDefinition;
	}

	public WorkflowDefinition update(WorkflowDefinition workflowDefinition)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(WorkflowDefinition workflowDefinition) method. Use update(WorkflowDefinition workflowDefinition, boolean merge) instead.");
		}

		return update(workflowDefinition, false);
	}

	public WorkflowDefinition update(WorkflowDefinition workflowDefinition,
		boolean merge) throws SystemException {
		boolean isNew = workflowDefinition.isNew();

		for (ModelListener<WorkflowDefinition> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(workflowDefinition);
			}
			else {
				listener.onBeforeUpdate(workflowDefinition);
			}
		}

		workflowDefinition = updateImpl(workflowDefinition, merge);

		for (ModelListener<WorkflowDefinition> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(workflowDefinition);
			}
			else {
				listener.onAfterUpdate(workflowDefinition);
			}
		}

		return workflowDefinition;
	}

	public WorkflowDefinition updateImpl(
		com.liferay.portal.workflow.edoras.model.WorkflowDefinition workflowDefinition,
		boolean merge) throws SystemException {
		workflowDefinition = toUnwrappedModel(workflowDefinition);

		boolean isNew = workflowDefinition.isNew();

		WorkflowDefinitionModelImpl workflowDefinitionModelImpl = (WorkflowDefinitionModelImpl)workflowDefinition;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, workflowDefinition, merge);

			workflowDefinition.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(WorkflowDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			WorkflowDefinitionImpl.class, workflowDefinition.getPrimaryKey(),
			workflowDefinition);

		if (!isNew &&
				((workflowDefinition.getCompanyId() != workflowDefinitionModelImpl.getOriginalCompanyId()) ||
				!Validator.equals(workflowDefinition.getName(),
					workflowDefinitionModelImpl.getOriginalName()) ||
				(workflowDefinition.getVersion() != workflowDefinitionModelImpl.getOriginalVersion()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_N_V,
				new Object[] {
					new Long(workflowDefinitionModelImpl.getOriginalCompanyId()),
					
				workflowDefinitionModelImpl.getOriginalName(),
					new Double(workflowDefinitionModelImpl.getOriginalVersion())
				});
		}

		if (isNew ||
				((workflowDefinition.getCompanyId() != workflowDefinitionModelImpl.getOriginalCompanyId()) ||
				!Validator.equals(workflowDefinition.getName(),
					workflowDefinitionModelImpl.getOriginalName()) ||
				(workflowDefinition.getVersion() != workflowDefinitionModelImpl.getOriginalVersion()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_N_V,
				new Object[] {
					new Long(workflowDefinition.getCompanyId()),
					
				workflowDefinition.getName(),
					new Double(workflowDefinition.getVersion())
				}, workflowDefinition);
		}

		return workflowDefinition;
	}

	protected WorkflowDefinition toUnwrappedModel(
		WorkflowDefinition workflowDefinition) {
		if (workflowDefinition instanceof WorkflowDefinitionImpl) {
			return workflowDefinition;
		}

		WorkflowDefinitionImpl workflowDefinitionImpl = new WorkflowDefinitionImpl();

		workflowDefinitionImpl.setNew(workflowDefinition.isNew());
		workflowDefinitionImpl.setPrimaryKey(workflowDefinition.getPrimaryKey());

		workflowDefinitionImpl.setWorkflowDefinitionId(workflowDefinition.getWorkflowDefinitionId());
		workflowDefinitionImpl.setCompanyId(workflowDefinition.getCompanyId());
		workflowDefinitionImpl.setUserId(workflowDefinition.getUserId());
		workflowDefinitionImpl.setUserName(workflowDefinition.getUserName());
		workflowDefinitionImpl.setCreateDate(workflowDefinition.getCreateDate());
		workflowDefinitionImpl.setModifiedDate(workflowDefinition.getModifiedDate());
		workflowDefinitionImpl.setName(workflowDefinition.getName());
		workflowDefinitionImpl.setVersion(workflowDefinition.getVersion());
		workflowDefinitionImpl.setDesignerVersion(workflowDefinition.getDesignerVersion());
		workflowDefinitionImpl.setModelXml(workflowDefinition.getModelXml());
		workflowDefinitionImpl.setGraphicalXml(workflowDefinition.getGraphicalXml());
		workflowDefinitionImpl.setPersistent(workflowDefinition.isPersistent());

		return workflowDefinitionImpl;
	}

	public WorkflowDefinition findByPrimaryKey(long workflowDefinitionId)
		throws NoSuchWorkflowDefinitionException, SystemException {
		WorkflowDefinition workflowDefinition = fetchByPrimaryKey(workflowDefinitionId);

		if (workflowDefinition == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No WorkflowDefinition exists with the primary key " +
					workflowDefinitionId);
			}

			throw new NoSuchWorkflowDefinitionException(
				"No WorkflowDefinition exists with the primary key " +
				workflowDefinitionId);
		}

		return workflowDefinition;
	}

	public WorkflowDefinition fetchByPrimaryKey(long workflowDefinitionId)
		throws SystemException {
		WorkflowDefinition workflowDefinition = (WorkflowDefinition)EntityCacheUtil.getResult(WorkflowDefinitionModelImpl.ENTITY_CACHE_ENABLED,
				WorkflowDefinitionImpl.class, workflowDefinitionId, this);

		if (workflowDefinition == null) {
			Session session = null;

			try {
				session = openSession();

				workflowDefinition = (WorkflowDefinition)session.get(WorkflowDefinitionImpl.class,
						new Long(workflowDefinitionId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (workflowDefinition != null) {
					cacheResult(workflowDefinition);
				}

				closeSession(session);
			}
		}

		return workflowDefinition;
	}

	public List<WorkflowDefinition> findByC_N(long companyId, String name)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(companyId), name };

		List<WorkflowDefinition> list = (List<WorkflowDefinition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_C_N,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowDefinition FROM WorkflowDefinition workflowDefinition WHERE ");

				query.append("workflowDefinition.companyId = ?");

				query.append(" AND ");

				if (name == null) {
					query.append("workflowDefinition.name IS NULL");
				}
				else {
					query.append("workflowDefinition.name = ?");
				}

				query.append(" ");

				query.append("ORDER BY ");

				query.append("workflowDefinition.name ASC, ");
				query.append("workflowDefinition.version DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (name != null) {
					qPos.add(name);
				}

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowDefinition>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_C_N, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WorkflowDefinition> findByC_N(long companyId, String name,
		int start, int end) throws SystemException {
		return findByC_N(companyId, name, start, end, null);
	}

	public List<WorkflowDefinition> findByC_N(long companyId, String name,
		int start, int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId),
				
				name,
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WorkflowDefinition> list = (List<WorkflowDefinition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_C_N,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowDefinition FROM WorkflowDefinition workflowDefinition WHERE ");

				query.append("workflowDefinition.companyId = ?");

				query.append(" AND ");

				if (name == null) {
					query.append("workflowDefinition.name IS NULL");
				}
				else {
					query.append("workflowDefinition.name = ?");
				}

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("workflowDefinition.");
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

					query.append("workflowDefinition.name ASC, ");
					query.append("workflowDefinition.version DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (name != null) {
					qPos.add(name);
				}

				list = (List<WorkflowDefinition>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowDefinition>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_C_N,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WorkflowDefinition findByC_N_First(long companyId, String name,
		OrderByComparator obc)
		throws NoSuchWorkflowDefinitionException, SystemException {
		List<WorkflowDefinition> list = findByC_N(companyId, name, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowDefinition exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(", ");
			msg.append("name=" + name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowDefinitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowDefinition findByC_N_Last(long companyId, String name,
		OrderByComparator obc)
		throws NoSuchWorkflowDefinitionException, SystemException {
		int count = countByC_N(companyId, name);

		List<WorkflowDefinition> list = findByC_N(companyId, name, count - 1,
				count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowDefinition exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(", ");
			msg.append("name=" + name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWorkflowDefinitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WorkflowDefinition[] findByC_N_PrevAndNext(
		long workflowDefinitionId, long companyId, String name,
		OrderByComparator obc)
		throws NoSuchWorkflowDefinitionException, SystemException {
		WorkflowDefinition workflowDefinition = findByPrimaryKey(workflowDefinitionId);

		int count = countByC_N(companyId, name);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT workflowDefinition FROM WorkflowDefinition workflowDefinition WHERE ");

			query.append("workflowDefinition.companyId = ?");

			query.append(" AND ");

			if (name == null) {
				query.append("workflowDefinition.name IS NULL");
			}
			else {
				query.append("workflowDefinition.name = ?");
			}

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("workflowDefinition.");
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

				query.append("workflowDefinition.name ASC, ");
				query.append("workflowDefinition.version DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			if (name != null) {
				qPos.add(name);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					workflowDefinition);

			WorkflowDefinition[] array = new WorkflowDefinitionImpl[3];

			array[0] = (WorkflowDefinition)objArray[0];
			array[1] = (WorkflowDefinition)objArray[1];
			array[2] = (WorkflowDefinition)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public WorkflowDefinition findByC_N_V(long companyId, String name,
		double version)
		throws NoSuchWorkflowDefinitionException, SystemException {
		WorkflowDefinition workflowDefinition = fetchByC_N_V(companyId, name,
				version);

		if (workflowDefinition == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WorkflowDefinition exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(", ");
			msg.append("name=" + name);

			msg.append(", ");
			msg.append("version=" + version);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchWorkflowDefinitionException(msg.toString());
		}

		return workflowDefinition;
	}

	public WorkflowDefinition fetchByC_N_V(long companyId, String name,
		double version) throws SystemException {
		return fetchByC_N_V(companyId, name, version, true);
	}

	public WorkflowDefinition fetchByC_N_V(long companyId, String name,
		double version, boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId),
				
				name, new Double(version)
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_N_V,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowDefinition FROM WorkflowDefinition workflowDefinition WHERE ");

				query.append("workflowDefinition.companyId = ?");

				query.append(" AND ");

				if (name == null) {
					query.append("workflowDefinition.name IS NULL");
				}
				else {
					query.append("workflowDefinition.name = ?");
				}

				query.append(" AND ");

				query.append("workflowDefinition.version = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("workflowDefinition.name ASC, ");
				query.append("workflowDefinition.version DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (name != null) {
					qPos.add(name);
				}

				qPos.add(version);

				List<WorkflowDefinition> list = q.list();

				result = list;

				WorkflowDefinition workflowDefinition = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_N_V,
						finderArgs, list);
				}
				else {
					workflowDefinition = list.get(0);

					cacheResult(workflowDefinition);

					if ((workflowDefinition.getCompanyId() != companyId) ||
							(workflowDefinition.getName() == null) ||
							!workflowDefinition.getName().equals(name) ||
							(workflowDefinition.getVersion() != version)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_N_V,
							finderArgs, workflowDefinition);
					}
				}

				return workflowDefinition;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_N_V,
						finderArgs, new ArrayList<WorkflowDefinition>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (WorkflowDefinition)result;
			}
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

	public List<WorkflowDefinition> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<WorkflowDefinition> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<WorkflowDefinition> findAll(int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WorkflowDefinition> list = (List<WorkflowDefinition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT workflowDefinition FROM WorkflowDefinition workflowDefinition ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("workflowDefinition.");
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

					query.append("workflowDefinition.name ASC, ");
					query.append("workflowDefinition.version DESC");
				}

				Query q = session.createQuery(query.toString());

				if (obc == null) {
					list = (List<WorkflowDefinition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<WorkflowDefinition>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WorkflowDefinition>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByC_N(long companyId, String name)
		throws SystemException {
		for (WorkflowDefinition workflowDefinition : findByC_N(companyId, name)) {
			remove(workflowDefinition);
		}
	}

	public void removeByC_N_V(long companyId, String name, double version)
		throws NoSuchWorkflowDefinitionException, SystemException {
		WorkflowDefinition workflowDefinition = findByC_N_V(companyId, name,
				version);

		remove(workflowDefinition);
	}

	public void removeAll() throws SystemException {
		for (WorkflowDefinition workflowDefinition : findAll()) {
			remove(workflowDefinition);
		}
	}

	public int countByC_N(long companyId, String name)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(companyId), name };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_N,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(workflowDefinition) ");
				query.append(
					"FROM WorkflowDefinition workflowDefinition WHERE ");

				query.append("workflowDefinition.companyId = ?");

				query.append(" AND ");

				if (name == null) {
					query.append("workflowDefinition.name IS NULL");
				}
				else {
					query.append("workflowDefinition.name = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (name != null) {
					qPos.add(name);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_N, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByC_N_V(long companyId, String name, double version)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId),
				
				name, new Double(version)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_N_V,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(workflowDefinition) ");
				query.append(
					"FROM WorkflowDefinition workflowDefinition WHERE ");

				query.append("workflowDefinition.companyId = ?");

				query.append(" AND ");

				if (name == null) {
					query.append("workflowDefinition.name IS NULL");
				}
				else {
					query.append("workflowDefinition.name = ?");
				}

				query.append(" AND ");

				query.append("workflowDefinition.version = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (name != null) {
					qPos.add(name);
				}

				qPos.add(version);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_N_V,
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
						"SELECT COUNT(workflowDefinition) FROM WorkflowDefinition workflowDefinition");

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
						"value.object.listener.com.liferay.portal.workflow.edoras.model.WorkflowDefinition")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<WorkflowDefinition>> listenersList = new ArrayList<ModelListener<WorkflowDefinition>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<WorkflowDefinition>)Class.forName(
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
	private static Log _log = LogFactoryUtil.getLog(WorkflowDefinitionPersistenceImpl.class);
}