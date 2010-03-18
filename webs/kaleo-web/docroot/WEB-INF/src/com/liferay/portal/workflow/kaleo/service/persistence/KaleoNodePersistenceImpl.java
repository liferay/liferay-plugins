/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.workflow.kaleo.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistry;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQuery;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.RowMapper;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.NoSuchNodeException;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeModelImpl;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="KaleoNodePersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoNodePersistence
 * @see       KaleoNodeUtil
 * @generated
 */
public class KaleoNodePersistenceImpl extends BasePersistenceImpl<KaleoNode>
	implements KaleoNodePersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoNodeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(KaleoNodeModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNodeModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoNodeModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNodeModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(KaleoNode kaleoNode) {
		EntityCacheUtil.putResult(KaleoNodeModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNodeImpl.class, kaleoNode.getPrimaryKey(), kaleoNode);
	}

	public void cacheResult(List<KaleoNode> kaleoNodes) {
		for (KaleoNode kaleoNode : kaleoNodes) {
			if (EntityCacheUtil.getResult(
						KaleoNodeModelImpl.ENTITY_CACHE_ENABLED,
						KaleoNodeImpl.class, kaleoNode.getPrimaryKey(), this) == null) {
				cacheResult(kaleoNode);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(KaleoNodeImpl.class.getName());
		EntityCacheUtil.clearCache(KaleoNodeImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public KaleoNode create(long kaleoNodeId) {
		KaleoNode kaleoNode = new KaleoNodeImpl();

		kaleoNode.setNew(true);
		kaleoNode.setPrimaryKey(kaleoNodeId);

		return kaleoNode;
	}

	public KaleoNode remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public KaleoNode remove(long kaleoNodeId)
		throws NoSuchNodeException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoNode kaleoNode = (KaleoNode)session.get(KaleoNodeImpl.class,
					new Long(kaleoNodeId));

			if (kaleoNode == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + kaleoNodeId);
				}

				throw new NoSuchNodeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoNodeId);
			}

			return remove(kaleoNode);
		}
		catch (NoSuchNodeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public KaleoNode remove(KaleoNode kaleoNode) throws SystemException {
		for (ModelListener<KaleoNode> listener : listeners) {
			listener.onBeforeRemove(kaleoNode);
		}

		kaleoNode = removeImpl(kaleoNode);

		for (ModelListener<KaleoNode> listener : listeners) {
			listener.onAfterRemove(kaleoNode);
		}

		return kaleoNode;
	}

	protected KaleoNode removeImpl(KaleoNode kaleoNode)
		throws SystemException {
		kaleoNode = toUnwrappedModel(kaleoNode);

		Session session = null;

		try {
			session = openSession();

			if (kaleoNode.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(KaleoNodeImpl.class,
						kaleoNode.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(kaleoNode);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(KaleoNodeModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNodeImpl.class, kaleoNode.getPrimaryKey());

		return kaleoNode;
	}

	public KaleoNode updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoNode kaleoNode,
		boolean merge) throws SystemException {
		kaleoNode = toUnwrappedModel(kaleoNode);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kaleoNode, merge);

			kaleoNode.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(KaleoNodeModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNodeImpl.class, kaleoNode.getPrimaryKey(), kaleoNode);

		return kaleoNode;
	}

	protected KaleoNode toUnwrappedModel(KaleoNode kaleoNode) {
		if (kaleoNode instanceof KaleoNodeImpl) {
			return kaleoNode;
		}

		KaleoNodeImpl kaleoNodeImpl = new KaleoNodeImpl();

		kaleoNodeImpl.setNew(kaleoNode.isNew());
		kaleoNodeImpl.setPrimaryKey(kaleoNode.getPrimaryKey());

		kaleoNodeImpl.setKaleoNodeId(kaleoNode.getKaleoNodeId());
		kaleoNodeImpl.setCompanyId(kaleoNode.getCompanyId());
		kaleoNodeImpl.setUserId(kaleoNode.getUserId());
		kaleoNodeImpl.setUserName(kaleoNode.getUserName());
		kaleoNodeImpl.setCreateDate(kaleoNode.getCreateDate());
		kaleoNodeImpl.setModifiedDate(kaleoNode.getModifiedDate());
		kaleoNodeImpl.setKaleoDefinitionId(kaleoNode.getKaleoDefinitionId());
		kaleoNodeImpl.setName(kaleoNode.getName());
		kaleoNodeImpl.setDescription(kaleoNode.getDescription());
		kaleoNodeImpl.setType(kaleoNode.getType());
		kaleoNodeImpl.setInitial(kaleoNode.isInitial());
		kaleoNodeImpl.setTerminal(kaleoNode.isTerminal());

		return kaleoNodeImpl;
	}

	public KaleoNode findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public KaleoNode findByPrimaryKey(long kaleoNodeId)
		throws NoSuchNodeException, SystemException {
		KaleoNode kaleoNode = fetchByPrimaryKey(kaleoNodeId);

		if (kaleoNode == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + kaleoNodeId);
			}

			throw new NoSuchNodeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				kaleoNodeId);
		}

		return kaleoNode;
	}

	public KaleoNode fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public KaleoNode fetchByPrimaryKey(long kaleoNodeId)
		throws SystemException {
		KaleoNode kaleoNode = (KaleoNode)EntityCacheUtil.getResult(KaleoNodeModelImpl.ENTITY_CACHE_ENABLED,
				KaleoNodeImpl.class, kaleoNodeId, this);

		if (kaleoNode == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoNode = (KaleoNode)session.get(KaleoNodeImpl.class,
						new Long(kaleoNodeId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (kaleoNode != null) {
					cacheResult(kaleoNode);
				}

				closeSession(session);
			}
		}

		return kaleoNode;
	}

	public List<KaleoNode> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<KaleoNode> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<KaleoNode> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoNode> list = (List<KaleoNode>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;
				String sql = null;

				if (orderByComparator != null) {
					query = new StringBundler(2 +
							(orderByComparator.getOrderByFields().length * 3));

					query.append(_SQL_SELECT_KALEONODE);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}

				else {
					sql = _SQL_SELECT_KALEONODE.concat(KaleoNodeModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<KaleoNode>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KaleoNode>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoNode>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeAll() throws SystemException {
		for (KaleoNode kaleoNode : findAll()) {
			remove(kaleoNode);
		}
	}

	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KALEONODE);

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

	public List<com.liferay.portal.workflow.kaleo.model.KaleoAction> getKaleoActions(
		long pk) throws SystemException {
		return getKaleoActions(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<com.liferay.portal.workflow.kaleo.model.KaleoAction> getKaleoActions(
		long pk, int start, int end) throws SystemException {
		return getKaleoActions(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_KALEOACTIONS = new FinderPath(com.liferay.portal.workflow.kaleo.model.impl.KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoActionModelImpl.FINDER_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.service.persistence.KaleoActionPersistenceImpl.FINDER_CLASS_NAME_LIST,
			"getKaleoActions",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	public List<com.liferay.portal.workflow.kaleo.model.KaleoAction> getKaleoActions(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(pk), String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<com.liferay.portal.workflow.kaleo.model.KaleoAction> list = (List<com.liferay.portal.workflow.kaleo.model.KaleoAction>)FinderCacheUtil.getResult(FINDER_PATH_GET_KALEOACTIONS,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETKALEOACTIONS.concat(ORDER_BY_CLAUSE)
											  .concat(orderByComparator.getOrderBy());
				}

				else {
					sql = _SQL_GETKALEOACTIONS.concat(com.liferay.portal.workflow.kaleo.model.impl.KaleoActionModelImpl.ORDER_BY_SQL);
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("Kaleo_KaleoAction",
					com.liferay.portal.workflow.kaleo.model.impl.KaleoActionImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.liferay.portal.workflow.kaleo.model.KaleoAction>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<com.liferay.portal.workflow.kaleo.model.KaleoAction>();
				}

				kaleoActionPersistence.cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_GET_KALEOACTIONS,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_KALEOACTIONS_SIZE = new FinderPath(com.liferay.portal.workflow.kaleo.model.impl.KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoActionModelImpl.FINDER_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.service.persistence.KaleoActionPersistenceImpl.FINDER_CLASS_NAME_LIST,
			"getKaleoActionsSize", new String[] { Long.class.getName() });

	public int getKaleoActionsSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(pk) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_KALEOACTIONS_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETKALEOACTIONSSIZE);

				q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_GET_KALEOACTIONS_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_KALEOACTION = new FinderPath(com.liferay.portal.workflow.kaleo.model.impl.KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoActionModelImpl.FINDER_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.service.persistence.KaleoActionPersistenceImpl.FINDER_CLASS_NAME_LIST,
			"containsKaleoAction",
			new String[] { Long.class.getName(), Long.class.getName() });

	public boolean containsKaleoAction(long pk, long kaleoActionPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(pk), new Long(kaleoActionPK) };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_KALEOACTION,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsKaleoAction.contains(pk,
							kaleoActionPK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_KALEOACTION,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	public boolean containsKaleoActions(long pk) throws SystemException {
		if (getKaleoActionsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.kaleo.model.KaleoNode")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoNode>> listenersList = new ArrayList<ModelListener<KaleoNode>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoNode>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsKaleoAction = new ContainsKaleoAction(this);
	}

	@BeanReference(type = KaleoActionPersistence.class)
	protected KaleoActionPersistence kaleoActionPersistence;
	@BeanReference(type = KaleoDefinitionPersistence.class)
	protected KaleoDefinitionPersistence kaleoDefinitionPersistence;
	@BeanReference(type = KaleoInstancePersistence.class)
	protected KaleoInstancePersistence kaleoInstancePersistence;
	@BeanReference(type = KaleoInstanceTokenPersistence.class)
	protected KaleoInstanceTokenPersistence kaleoInstanceTokenPersistence;
	@BeanReference(type = KaleoLogPersistence.class)
	protected KaleoLogPersistence kaleoLogPersistence;
	@BeanReference(type = KaleoNodePersistence.class)
	protected KaleoNodePersistence kaleoNodePersistence;
	@BeanReference(type = KaleoTaskPersistence.class)
	protected KaleoTaskPersistence kaleoTaskPersistence;
	@BeanReference(type = KaleoTaskAssignmentPersistence.class)
	protected KaleoTaskAssignmentPersistence kaleoTaskAssignmentPersistence;
	@BeanReference(type = KaleoTaskInstanceAssignmentPersistence.class)
	protected KaleoTaskInstanceAssignmentPersistence kaleoTaskInstanceAssignmentPersistence;
	@BeanReference(type = KaleoTaskInstanceTokenPersistence.class)
	protected KaleoTaskInstanceTokenPersistence kaleoTaskInstanceTokenPersistence;
	@BeanReference(type = KaleoTransitionPersistence.class)
	protected KaleoTransitionPersistence kaleoTransitionPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	protected ContainsKaleoAction containsKaleoAction;

	protected class ContainsKaleoAction {
		protected ContainsKaleoAction(KaleoNodePersistenceImpl persistenceImpl) {
			super();

			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSKALEOACTION,
					new int[] { Types.BIGINT, Types.BIGINT }, RowMapper.COUNT);
		}

		protected boolean contains(long kaleoNodeId, long kaleoActionId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(kaleoNodeId), new Long(kaleoActionId)
					});

			if (results.size() > 0) {
				Integer count = results.get(0);

				if (count.intValue() > 0) {
					return true;
				}
			}

			return false;
		}

		private MappingSqlQuery<Integer> _mappingSqlQuery;
	}

	private static final String _SQL_SELECT_KALEONODE = "SELECT kaleoNode FROM KaleoNode kaleoNode";
	private static final String _SQL_COUNT_KALEONODE = "SELECT COUNT(kaleoNode) FROM KaleoNode kaleoNode";
	private static final String _SQL_GETKALEOACTIONS = "SELECT {Kaleo_KaleoAction.*} FROM Kaleo_KaleoAction INNER JOIN Kaleo_KaleoNode ON (Kaleo_KaleoNode.kaleoNodeId = Kaleo_KaleoAction.kaleoNodeId) WHERE (Kaleo_KaleoNode.kaleoNodeId = ?)";
	private static final String _SQL_GETKALEOACTIONSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM Kaleo_KaleoAction WHERE kaleoNodeId = ?";
	private static final String _SQL_CONTAINSKALEOACTION = "SELECT COUNT(*) AS COUNT_VALUE FROM Kaleo_KaleoAction WHERE kaleoNodeId = ? AND kaleoActionId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoNode.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoNode exists with the primary key ";
	private static Log _log = LogFactoryUtil.getLog(KaleoNodePersistenceImpl.class);
}