/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the kaleo node service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoNodePersistence
 * @see KaleoNodeUtil
 * @generated
 */
public class KaleoNodePersistenceImpl extends BasePersistenceImpl<KaleoNode>
	implements KaleoNodePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KaleoNodeUtil} to access the kaleo node persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoNodeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(KaleoNodeModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNodeModelImpl.FINDER_CACHE_ENABLED, KaleoNodeImpl.class,
			FINDER_CLASS_NAME_LIST, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoNodeModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNodeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KALEODEFINITIONID = new FinderPath(KaleoNodeModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNodeModelImpl.FINDER_CACHE_ENABLED, KaleoNodeImpl.class,
			FINDER_CLASS_NAME_LIST, "findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEODEFINITIONID = new FinderPath(KaleoNodeModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNodeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByKaleoDefinitionId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_C_KDI = new FinderPath(KaleoNodeModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNodeModelImpl.FINDER_CACHE_ENABLED, KaleoNodeImpl.class,
			FINDER_CLASS_NAME_LIST, "findByC_KDI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_C_KDI = new FinderPath(KaleoNodeModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNodeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByC_KDI",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(KaleoNodeModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNodeModelImpl.FINDER_CACHE_ENABLED, KaleoNodeImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoNodeModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNodeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the kaleo node in the entity cache if it is enabled.
	 *
	 * @param kaleoNode the kaleo node
	 */
	public void cacheResult(KaleoNode kaleoNode) {
		EntityCacheUtil.putResult(KaleoNodeModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNodeImpl.class, kaleoNode.getPrimaryKey(), kaleoNode);

		kaleoNode.resetOriginalValues();
	}

	/**
	 * Caches the kaleo nodes in the entity cache if it is enabled.
	 *
	 * @param kaleoNodes the kaleo nodes
	 */
	public void cacheResult(List<KaleoNode> kaleoNodes) {
		for (KaleoNode kaleoNode : kaleoNodes) {
			if (EntityCacheUtil.getResult(
						KaleoNodeModelImpl.ENTITY_CACHE_ENABLED,
						KaleoNodeImpl.class, kaleoNode.getPrimaryKey(), this) == null) {
				cacheResult(kaleoNode);
			}
		}
	}

	/**
	 * Clears the cache for all kaleo nodes.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(KaleoNodeImpl.class.getName());
		}

		EntityCacheUtil.clearCache(KaleoNodeImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the kaleo node.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KaleoNode kaleoNode) {
		EntityCacheUtil.removeResult(KaleoNodeModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNodeImpl.class, kaleoNode.getPrimaryKey());
	}

	/**
	 * Creates a new kaleo node with the primary key. Does not add the kaleo node to the database.
	 *
	 * @param kaleoNodeId the primary key for the new kaleo node
	 * @return the new kaleo node
	 */
	public KaleoNode create(long kaleoNodeId) {
		KaleoNode kaleoNode = new KaleoNodeImpl();

		kaleoNode.setNew(true);
		kaleoNode.setPrimaryKey(kaleoNodeId);

		return kaleoNode;
	}

	/**
	 * Removes the kaleo node with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo node
	 * @return the kaleo node that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo node with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoNode remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the kaleo node with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoNodeId the primary key of the kaleo node
	 * @return the kaleo node that was removed
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a kaleo node with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNode remove(long kaleoNodeId)
		throws NoSuchNodeException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoNode kaleoNode = (KaleoNode)session.get(KaleoNodeImpl.class,
					Long.valueOf(kaleoNodeId));

			if (kaleoNode == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + kaleoNodeId);
				}

				throw new NoSuchNodeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoNodeId);
			}

			return kaleoNodePersistence.remove(kaleoNode);
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

	/**
	 * Removes the kaleo node from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoNode the kaleo node
	 * @return the kaleo node that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoNode remove(KaleoNode kaleoNode) throws SystemException {
		return super.remove(kaleoNode);
	}

	@Override
	protected KaleoNode removeImpl(KaleoNode kaleoNode)
		throws SystemException {
		kaleoNode = toUnwrappedModel(kaleoNode);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, kaleoNode);
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

	@Override
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
		kaleoNodeImpl.setGroupId(kaleoNode.getGroupId());
		kaleoNodeImpl.setCompanyId(kaleoNode.getCompanyId());
		kaleoNodeImpl.setUserId(kaleoNode.getUserId());
		kaleoNodeImpl.setUserName(kaleoNode.getUserName());
		kaleoNodeImpl.setCreateDate(kaleoNode.getCreateDate());
		kaleoNodeImpl.setModifiedDate(kaleoNode.getModifiedDate());
		kaleoNodeImpl.setKaleoDefinitionId(kaleoNode.getKaleoDefinitionId());
		kaleoNodeImpl.setName(kaleoNode.getName());
		kaleoNodeImpl.setMetadata(kaleoNode.getMetadata());
		kaleoNodeImpl.setDescription(kaleoNode.getDescription());
		kaleoNodeImpl.setType(kaleoNode.getType());
		kaleoNodeImpl.setInitial(kaleoNode.isInitial());
		kaleoNodeImpl.setTerminal(kaleoNode.isTerminal());

		return kaleoNodeImpl;
	}

	/**
	 * Returns the kaleo node with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo node
	 * @return the kaleo node
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo node with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoNode findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the kaleo node with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchNodeException} if it could not be found.
	 *
	 * @param kaleoNodeId the primary key of the kaleo node
	 * @return the kaleo node
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a kaleo node with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
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

	/**
	 * Returns the kaleo node with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo node
	 * @return the kaleo node, or <code>null</code> if a kaleo node with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoNode fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the kaleo node with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoNodeId the primary key of the kaleo node
	 * @return the kaleo node, or <code>null</code> if a kaleo node with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNode fetchByPrimaryKey(long kaleoNodeId)
		throws SystemException {
		KaleoNode kaleoNode = (KaleoNode)EntityCacheUtil.getResult(KaleoNodeModelImpl.ENTITY_CACHE_ENABLED,
				KaleoNodeImpl.class, kaleoNodeId, this);

		if (kaleoNode == _nullKaleoNode) {
			return null;
		}

		if (kaleoNode == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				kaleoNode = (KaleoNode)session.get(KaleoNodeImpl.class,
						Long.valueOf(kaleoNodeId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (kaleoNode != null) {
					cacheResult(kaleoNode);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(KaleoNodeModelImpl.ENTITY_CACHE_ENABLED,
						KaleoNodeImpl.class, kaleoNodeId, _nullKaleoNode);
				}

				closeSession(session);
			}
		}

		return kaleoNode;
	}

	/**
	 * Returns all the kaleo nodes where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching kaleo nodes
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNode> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the kaleo nodes where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo nodes
	 * @param end the upper bound of the range of kaleo nodes (not inclusive)
	 * @return the range of matching kaleo nodes
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNode> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo nodes where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo nodes
	 * @param end the upper bound of the range of kaleo nodes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo nodes
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNode> findByCompanyId(long companyId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoNode> list = (List<KaleoNode>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_KALEONODE_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoNodeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<KaleoNode>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_COMPANYID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first kaleo node in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo node
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a matching kaleo node could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNode findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchNodeException, SystemException {
		List<KaleoNode> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchNodeException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo node in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo node
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a matching kaleo node could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNode findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchNodeException, SystemException {
		int count = countByCompanyId(companyId);

		List<KaleoNode> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchNodeException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo nodes before and after the current kaleo node in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoNodeId the primary key of the current kaleo node
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo node
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a kaleo node with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNode[] findByCompanyId_PrevAndNext(long kaleoNodeId,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchNodeException, SystemException {
		KaleoNode kaleoNode = findByPrimaryKey(kaleoNodeId);

		Session session = null;

		try {
			session = openSession();

			KaleoNode[] array = new KaleoNodeImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, kaleoNode,
					companyId, orderByComparator, true);

			array[1] = kaleoNode;

			array[2] = getByCompanyId_PrevAndNext(session, kaleoNode,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoNode getByCompanyId_PrevAndNext(Session session,
		KaleoNode kaleoNode, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEONODE_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(KaleoNodeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoNode);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoNode> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo nodes where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the matching kaleo nodes
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNode> findByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo nodes where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo nodes
	 * @param end the upper bound of the range of kaleo nodes (not inclusive)
	 * @return the range of matching kaleo nodes
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNode> findByKaleoDefinitionId(long kaleoDefinitionId,
		int start, int end) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo nodes where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo nodes
	 * @param end the upper bound of the range of kaleo nodes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo nodes
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNode> findByKaleoDefinitionId(long kaleoDefinitionId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoDefinitionId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoNode> list = (List<KaleoNode>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_KALEONODE_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoNodeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				list = (List<KaleoNode>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first kaleo node in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo node
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a matching kaleo node could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNode findByKaleoDefinitionId_First(long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchNodeException, SystemException {
		List<KaleoNode> list = findByKaleoDefinitionId(kaleoDefinitionId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchNodeException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo node in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo node
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a matching kaleo node could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNode findByKaleoDefinitionId_Last(long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchNodeException, SystemException {
		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		List<KaleoNode> list = findByKaleoDefinitionId(kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchNodeException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo nodes before and after the current kaleo node in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoNodeId the primary key of the current kaleo node
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo node
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a kaleo node with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNode[] findByKaleoDefinitionId_PrevAndNext(long kaleoNodeId,
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchNodeException, SystemException {
		KaleoNode kaleoNode = findByPrimaryKey(kaleoNodeId);

		Session session = null;

		try {
			session = openSession();

			KaleoNode[] array = new KaleoNodeImpl[3];

			array[0] = getByKaleoDefinitionId_PrevAndNext(session, kaleoNode,
					kaleoDefinitionId, orderByComparator, true);

			array[1] = kaleoNode;

			array[2] = getByKaleoDefinitionId_PrevAndNext(session, kaleoNode,
					kaleoDefinitionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoNode getByKaleoDefinitionId_PrevAndNext(Session session,
		KaleoNode kaleoNode, long kaleoDefinitionId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEONODE_WHERE);

		query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(KaleoNodeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoNode);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoNode> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo nodes where companyId = &#63; and kaleoDefinitionId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the matching kaleo nodes
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNode> findByC_KDI(long companyId, long kaleoDefinitionId)
		throws SystemException {
		return findByC_KDI(companyId, kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo nodes where companyId = &#63; and kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo nodes
	 * @param end the upper bound of the range of kaleo nodes (not inclusive)
	 * @return the range of matching kaleo nodes
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNode> findByC_KDI(long companyId, long kaleoDefinitionId,
		int start, int end) throws SystemException {
		return findByC_KDI(companyId, kaleoDefinitionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo nodes where companyId = &#63; and kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo nodes
	 * @param end the upper bound of the range of kaleo nodes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo nodes
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNode> findByC_KDI(long companyId, long kaleoDefinitionId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId, kaleoDefinitionId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoNode> list = (List<KaleoNode>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_C_KDI,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_KALEONODE_WHERE);

			query.append(_FINDER_COLUMN_C_KDI_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_KDI_KALEODEFINITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoNodeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(kaleoDefinitionId);

				list = (List<KaleoNode>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_C_KDI,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_C_KDI,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first kaleo node in the ordered set where companyId = &#63; and kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo node
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a matching kaleo node could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNode findByC_KDI_First(long companyId, long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchNodeException, SystemException {
		List<KaleoNode> list = findByC_KDI(companyId, kaleoDefinitionId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchNodeException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo node in the ordered set where companyId = &#63; and kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo node
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a matching kaleo node could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNode findByC_KDI_Last(long companyId, long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchNodeException, SystemException {
		int count = countByC_KDI(companyId, kaleoDefinitionId);

		List<KaleoNode> list = findByC_KDI(companyId, kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchNodeException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo nodes before and after the current kaleo node in the ordered set where companyId = &#63; and kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoNodeId the primary key of the current kaleo node
	 * @param companyId the company ID
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo node
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchNodeException if a kaleo node with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNode[] findByC_KDI_PrevAndNext(long kaleoNodeId,
		long companyId, long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchNodeException, SystemException {
		KaleoNode kaleoNode = findByPrimaryKey(kaleoNodeId);

		Session session = null;

		try {
			session = openSession();

			KaleoNode[] array = new KaleoNodeImpl[3];

			array[0] = getByC_KDI_PrevAndNext(session, kaleoNode, companyId,
					kaleoDefinitionId, orderByComparator, true);

			array[1] = kaleoNode;

			array[2] = getByC_KDI_PrevAndNext(session, kaleoNode, companyId,
					kaleoDefinitionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoNode getByC_KDI_PrevAndNext(Session session,
		KaleoNode kaleoNode, long companyId, long kaleoDefinitionId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEONODE_WHERE);

		query.append(_FINDER_COLUMN_C_KDI_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_KDI_KALEODEFINITIONID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(KaleoNodeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoNode);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoNode> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo nodes.
	 *
	 * @return the kaleo nodes
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNode> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo nodes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo nodes
	 * @param end the upper bound of the range of kaleo nodes (not inclusive)
	 * @return the range of kaleo nodes
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNode> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo nodes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo nodes
	 * @param end the upper bound of the range of kaleo nodes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo nodes
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNode> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoNode> list = (List<KaleoNode>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
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

			Session session = null;

			try {
				session = openSession();

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
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_ALL,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs,
						list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the kaleo nodes where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (KaleoNode kaleoNode : findByCompanyId(companyId)) {
			kaleoNodePersistence.remove(kaleoNode);
		}
	}

	/**
	 * Removes all the kaleo nodes where kaleoDefinitionId = &#63; from the database.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		for (KaleoNode kaleoNode : findByKaleoDefinitionId(kaleoDefinitionId)) {
			kaleoNodePersistence.remove(kaleoNode);
		}
	}

	/**
	 * Removes all the kaleo nodes where companyId = &#63; and kaleoDefinitionId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_KDI(long companyId, long kaleoDefinitionId)
		throws SystemException {
		for (KaleoNode kaleoNode : findByC_KDI(companyId, kaleoDefinitionId)) {
			kaleoNodePersistence.remove(kaleoNode);
		}
	}

	/**
	 * Removes all the kaleo nodes from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (KaleoNode kaleoNode : findAll()) {
			kaleoNodePersistence.remove(kaleoNode);
		}
	}

	/**
	 * Returns the number of kaleo nodes where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching kaleo nodes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEONODE_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPANYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of kaleo nodes where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the number of matching kaleo nodes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoDefinitionId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEONODE_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of kaleo nodes where companyId = &#63; and kaleoDefinitionId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the number of matching kaleo nodes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_KDI(long companyId, long kaleoDefinitionId)
		throws SystemException {
		Object[] finderArgs = new Object[] { companyId, kaleoDefinitionId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_KDI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEONODE_WHERE);

			query.append(_FINDER_COLUMN_C_KDI_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_KDI_KALEODEFINITIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(kaleoDefinitionId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_KDI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of kaleo nodes.
	 *
	 * @return the number of kaleo nodes
	 * @throws SystemException if a system exception occurred
	 */
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

	/**
	 * Returns all the kaleo actions associated with the kaleo node.
	 *
	 * @param pk the primary key of the kaleo node
	 * @return the kaleo actions associated with the kaleo node
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.portal.workflow.kaleo.model.KaleoAction> getKaleoActions(
		long pk) throws SystemException {
		return getKaleoActions(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the kaleo actions associated with the kaleo node.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the kaleo node
	 * @param start the lower bound of the range of kaleo nodes
	 * @param end the upper bound of the range of kaleo nodes (not inclusive)
	 * @return the range of kaleo actions associated with the kaleo node
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.portal.workflow.kaleo.model.KaleoAction> getKaleoActions(
		long pk, int start, int end) throws SystemException {
		return getKaleoActions(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_KALEOACTIONS = new FinderPath(com.liferay.portal.workflow.kaleo.model.impl.KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoActionModelImpl.FINDER_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoActionImpl.class,
			com.liferay.portal.workflow.kaleo.service.persistence.KaleoActionPersistenceImpl.FINDER_CLASS_NAME_LIST,
			"getKaleoActions",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	/**
	 * Returns an ordered range of all the kaleo actions associated with the kaleo node.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the kaleo node
	 * @param start the lower bound of the range of kaleo nodes
	 * @param end the upper bound of the range of kaleo nodes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo actions associated with the kaleo node
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.portal.workflow.kaleo.model.KaleoAction> getKaleoActions(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				pk, String.valueOf(start), String.valueOf(end),
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

				q.addEntity("KaleoAction",
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
					FinderCacheUtil.removeResult(FINDER_PATH_GET_KALEOACTIONS,
						finderArgs);
				}
				else {
					kaleoActionPersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_KALEOACTIONS,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_KALEOACTIONS_SIZE = new FinderPath(com.liferay.portal.workflow.kaleo.model.impl.KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoActionModelImpl.FINDER_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoActionImpl.class,
			com.liferay.portal.workflow.kaleo.service.persistence.KaleoActionPersistenceImpl.FINDER_CLASS_NAME_LIST,
			"getKaleoActionsSize", new String[] { Long.class.getName() });

	/**
	 * Returns the number of kaleo actions associated with the kaleo node.
	 *
	 * @param pk the primary key of the kaleo node
	 * @return the number of kaleo actions associated with the kaleo node
	 * @throws SystemException if a system exception occurred
	 */
	public int getKaleoActionsSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_KALEOACTIONS_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETKALEOACTIONSSIZE);

				q.addScalar(COUNT_COLUMN_NAME,
					com.liferay.portal.kernel.dao.orm.Type.LONG);

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
			com.liferay.portal.workflow.kaleo.model.impl.KaleoActionImpl.class,
			com.liferay.portal.workflow.kaleo.service.persistence.KaleoActionPersistenceImpl.FINDER_CLASS_NAME_LIST,
			"containsKaleoAction",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the kaleo action is associated with the kaleo node.
	 *
	 * @param pk the primary key of the kaleo node
	 * @param kaleoActionPK the primary key of the kaleo action
	 * @return <code>true</code> if the kaleo action is associated with the kaleo node; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsKaleoAction(long pk, long kaleoActionPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, kaleoActionPK };

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

	/**
	 * Returns <code>true</code> if the kaleo node has any kaleo actions associated with it.
	 *
	 * @param pk the primary key of the kaleo node to check for associations with kaleo actions
	 * @return <code>true</code> if the kaleo node has any kaleo actions associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsKaleoActions(long pk) throws SystemException {
		if (getKaleoActionsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Returns all the kaleo transitions associated with the kaleo node.
	 *
	 * @param pk the primary key of the kaleo node
	 * @return the kaleo transitions associated with the kaleo node
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> getKaleoTransitions(
		long pk) throws SystemException {
		return getKaleoTransitions(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the kaleo transitions associated with the kaleo node.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the kaleo node
	 * @param start the lower bound of the range of kaleo nodes
	 * @param end the upper bound of the range of kaleo nodes (not inclusive)
	 * @return the range of kaleo transitions associated with the kaleo node
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> getKaleoTransitions(
		long pk, int start, int end) throws SystemException {
		return getKaleoTransitions(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_KALEOTRANSITIONS = new FinderPath(com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionImpl.class,
			com.liferay.portal.workflow.kaleo.service.persistence.KaleoTransitionPersistenceImpl.FINDER_CLASS_NAME_LIST,
			"getKaleoTransitions",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	/**
	 * Returns an ordered range of all the kaleo transitions associated with the kaleo node.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the kaleo node
	 * @param start the lower bound of the range of kaleo nodes
	 * @param end the upper bound of the range of kaleo nodes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo transitions associated with the kaleo node
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> getKaleoTransitions(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				pk, String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> list = (List<com.liferay.portal.workflow.kaleo.model.KaleoTransition>)FinderCacheUtil.getResult(FINDER_PATH_GET_KALEOTRANSITIONS,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETKALEOTRANSITIONS.concat(ORDER_BY_CLAUSE)
												  .concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETKALEOTRANSITIONS.concat(com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionModelImpl.ORDER_BY_SQL);
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("KaleoTransition",
					com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.liferay.portal.workflow.kaleo.model.KaleoTransition>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_KALEOTRANSITIONS,
						finderArgs);
				}
				else {
					kaleoTransitionPersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_KALEOTRANSITIONS,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_KALEOTRANSITIONS_SIZE = new FinderPath(com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionImpl.class,
			com.liferay.portal.workflow.kaleo.service.persistence.KaleoTransitionPersistenceImpl.FINDER_CLASS_NAME_LIST,
			"getKaleoTransitionsSize", new String[] { Long.class.getName() });

	/**
	 * Returns the number of kaleo transitions associated with the kaleo node.
	 *
	 * @param pk the primary key of the kaleo node
	 * @return the number of kaleo transitions associated with the kaleo node
	 * @throws SystemException if a system exception occurred
	 */
	public int getKaleoTransitionsSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_KALEOTRANSITIONS_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETKALEOTRANSITIONSSIZE);

				q.addScalar(COUNT_COLUMN_NAME,
					com.liferay.portal.kernel.dao.orm.Type.LONG);

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

				FinderCacheUtil.putResult(FINDER_PATH_GET_KALEOTRANSITIONS_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_KALEOTRANSITION = new FinderPath(com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionImpl.class,
			com.liferay.portal.workflow.kaleo.service.persistence.KaleoTransitionPersistenceImpl.FINDER_CLASS_NAME_LIST,
			"containsKaleoTransition",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the kaleo transition is associated with the kaleo node.
	 *
	 * @param pk the primary key of the kaleo node
	 * @param kaleoTransitionPK the primary key of the kaleo transition
	 * @return <code>true</code> if the kaleo transition is associated with the kaleo node; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsKaleoTransition(long pk, long kaleoTransitionPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, kaleoTransitionPK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_KALEOTRANSITION,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsKaleoTransition.contains(pk,
							kaleoTransitionPK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_KALEOTRANSITION,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Returns <code>true</code> if the kaleo node has any kaleo transitions associated with it.
	 *
	 * @param pk the primary key of the kaleo node to check for associations with kaleo transitions
	 * @return <code>true</code> if the kaleo node has any kaleo transitions associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsKaleoTransitions(long pk) throws SystemException {
		if (getKaleoTransitionsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Initializes the kaleo node persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.kaleo.model.KaleoNode")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoNode>> listenersList = new ArrayList<ModelListener<KaleoNode>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoNode>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsKaleoAction = new ContainsKaleoAction(this);

		containsKaleoTransition = new ContainsKaleoTransition(this);
	}

	public void destroy() {
		EntityCacheUtil.removeCache(KaleoNodeImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = KaleoActionPersistence.class)
	protected KaleoActionPersistence kaleoActionPersistence;
	@BeanReference(type = KaleoConditionPersistence.class)
	protected KaleoConditionPersistence kaleoConditionPersistence;
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
	@BeanReference(type = KaleoNotificationPersistence.class)
	protected KaleoNotificationPersistence kaleoNotificationPersistence;
	@BeanReference(type = KaleoNotificationRecipientPersistence.class)
	protected KaleoNotificationRecipientPersistence kaleoNotificationRecipientPersistence;
	@BeanReference(type = KaleoTaskPersistence.class)
	protected KaleoTaskPersistence kaleoTaskPersistence;
	@BeanReference(type = KaleoTaskAssignmentPersistence.class)
	protected KaleoTaskAssignmentPersistence kaleoTaskAssignmentPersistence;
	@BeanReference(type = KaleoTaskAssignmentInstancePersistence.class)
	protected KaleoTaskAssignmentInstancePersistence kaleoTaskAssignmentInstancePersistence;
	@BeanReference(type = KaleoTaskFormPersistence.class)
	protected KaleoTaskFormPersistence kaleoTaskFormPersistence;
	@BeanReference(type = KaleoTaskInstanceTokenPersistence.class)
	protected KaleoTaskInstanceTokenPersistence kaleoTaskInstanceTokenPersistence;
	@BeanReference(type = KaleoTimerPersistence.class)
	protected KaleoTimerPersistence kaleoTimerPersistence;
	@BeanReference(type = KaleoTimerInstanceTokenPersistence.class)
	protected KaleoTimerInstanceTokenPersistence kaleoTimerInstanceTokenPersistence;
	@BeanReference(type = KaleoTransitionPersistence.class)
	protected KaleoTransitionPersistence kaleoTransitionPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	protected ContainsKaleoAction containsKaleoAction;
	protected ContainsKaleoTransition containsKaleoTransition;

	protected class ContainsKaleoAction {
		protected ContainsKaleoAction(KaleoNodePersistenceImpl persistenceImpl) {
			super();

			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSKALEOACTION,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
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

	protected class ContainsKaleoTransition {
		protected ContainsKaleoTransition(
			KaleoNodePersistenceImpl persistenceImpl) {
			super();

			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSKALEOTRANSITION,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long kaleoNodeId, long kaleoTransitionId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(kaleoNodeId), new Long(kaleoTransitionId)
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
	private static final String _SQL_SELECT_KALEONODE_WHERE = "SELECT kaleoNode FROM KaleoNode kaleoNode WHERE ";
	private static final String _SQL_COUNT_KALEONODE = "SELECT COUNT(kaleoNode) FROM KaleoNode kaleoNode";
	private static final String _SQL_COUNT_KALEONODE_WHERE = "SELECT COUNT(kaleoNode) FROM KaleoNode kaleoNode WHERE ";
	private static final String _SQL_GETKALEOACTIONS = "SELECT {KaleoAction.*} FROM KaleoAction INNER JOIN KaleoNode ON (KaleoNode.kaleoNodeId = KaleoAction.kaleoNodeId) WHERE (KaleoNode.kaleoNodeId = ?)";
	private static final String _SQL_GETKALEOACTIONSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM KaleoAction WHERE kaleoNodeId = ?";
	private static final String _SQL_CONTAINSKALEOACTION = "SELECT COUNT(*) AS COUNT_VALUE FROM KaleoAction WHERE kaleoNodeId = ? AND kaleoActionId = ?";
	private static final String _SQL_GETKALEOTRANSITIONS = "SELECT {KaleoTransition.*} FROM KaleoTransition INNER JOIN KaleoNode ON (KaleoNode.kaleoNodeId = KaleoTransition.kaleoNodeId) WHERE (KaleoNode.kaleoNodeId = ?)";
	private static final String _SQL_GETKALEOTRANSITIONSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM KaleoTransition WHERE kaleoNodeId = ?";
	private static final String _SQL_CONTAINSKALEOTRANSITION = "SELECT COUNT(*) AS COUNT_VALUE FROM KaleoTransition WHERE kaleoNodeId = ? AND kaleoTransitionId = ?";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoNode.companyId = ?";
	private static final String _FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2 =
		"kaleoNode.kaleoDefinitionId = ?";
	private static final String _FINDER_COLUMN_C_KDI_COMPANYID_2 = "kaleoNode.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_KDI_KALEODEFINITIONID_2 = "kaleoNode.kaleoDefinitionId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoNode.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoNode exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoNode exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(KaleoNodePersistenceImpl.class);
	private static KaleoNode _nullKaleoNode = new KaleoNodeImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<KaleoNode> toCacheModel() {
				return _nullKaleoNodeCacheModel;
			}
		};

	private static CacheModel<KaleoNode> _nullKaleoNodeCacheModel = new CacheModel<KaleoNode>() {
			public KaleoNode toEntityModel() {
				return _nullKaleoNode;
			}
		};
}