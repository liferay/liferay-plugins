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

package com.liferay.wsrp.service.persistence;

import com.liferay.portal.NoSuchModelException;
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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.wsrp.NoSuchConsumerPortletException;
import com.liferay.wsrp.model.WSRPConsumerPortlet;
import com.liferay.wsrp.model.impl.WSRPConsumerPortletImpl;
import com.liferay.wsrp.model.impl.WSRPConsumerPortletModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="WSRPConsumerPortletPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WSRPConsumerPortletPersistence
 * @see       WSRPConsumerPortletUtil
 * @generated
 */
public class WSRPConsumerPortletPersistenceImpl extends BasePersistenceImpl<WSRPConsumerPortlet>
	implements WSRPConsumerPortletPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = WSRPConsumerPortletImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_WSRPCONSUMERID = new FinderPath(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByWsrpConsumerId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_WSRPCONSUMERID = new FinderPath(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByWsrpConsumerId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_WSRPCONSUMERID = new FinderPath(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByWsrpConsumerId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	public void cacheResult(WSRPConsumerPortlet wsrpConsumerPortlet) {
		EntityCacheUtil.putResult(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletImpl.class, wsrpConsumerPortlet.getPrimaryKey(),
			wsrpConsumerPortlet);
	}

	public void cacheResult(List<WSRPConsumerPortlet> wsrpConsumerPortlets) {
		for (WSRPConsumerPortlet wsrpConsumerPortlet : wsrpConsumerPortlets) {
			if (EntityCacheUtil.getResult(
						WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
						WSRPConsumerPortletImpl.class,
						wsrpConsumerPortlet.getPrimaryKey(), this) == null) {
				cacheResult(wsrpConsumerPortlet);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(WSRPConsumerPortletImpl.class.getName());
		EntityCacheUtil.clearCache(WSRPConsumerPortletImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public WSRPConsumerPortlet create(long wsrpConsumerPortletId) {
		WSRPConsumerPortlet wsrpConsumerPortlet = new WSRPConsumerPortletImpl();

		wsrpConsumerPortlet.setNew(true);
		wsrpConsumerPortlet.setPrimaryKey(wsrpConsumerPortletId);

		return wsrpConsumerPortlet;
	}

	public WSRPConsumerPortlet remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public WSRPConsumerPortlet remove(long wsrpConsumerPortletId)
		throws NoSuchConsumerPortletException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WSRPConsumerPortlet wsrpConsumerPortlet = (WSRPConsumerPortlet)session.get(WSRPConsumerPortletImpl.class,
					new Long(wsrpConsumerPortletId));

			if (wsrpConsumerPortlet == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						wsrpConsumerPortletId);
				}

				throw new NoSuchConsumerPortletException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					wsrpConsumerPortletId);
			}

			return remove(wsrpConsumerPortlet);
		}
		catch (NoSuchConsumerPortletException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public WSRPConsumerPortlet remove(WSRPConsumerPortlet wsrpConsumerPortlet)
		throws SystemException {
		for (ModelListener<WSRPConsumerPortlet> listener : listeners) {
			listener.onBeforeRemove(wsrpConsumerPortlet);
		}

		wsrpConsumerPortlet = removeImpl(wsrpConsumerPortlet);

		for (ModelListener<WSRPConsumerPortlet> listener : listeners) {
			listener.onAfterRemove(wsrpConsumerPortlet);
		}

		return wsrpConsumerPortlet;
	}

	protected WSRPConsumerPortlet removeImpl(
		WSRPConsumerPortlet wsrpConsumerPortlet) throws SystemException {
		wsrpConsumerPortlet = toUnwrappedModel(wsrpConsumerPortlet);

		Session session = null;

		try {
			session = openSession();

			if (wsrpConsumerPortlet.isCachedModel() ||
					BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(WSRPConsumerPortletImpl.class,
						wsrpConsumerPortlet.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(wsrpConsumerPortlet);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletImpl.class, wsrpConsumerPortlet.getPrimaryKey());

		return wsrpConsumerPortlet;
	}

	public WSRPConsumerPortlet updateImpl(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet,
		boolean merge) throws SystemException {
		wsrpConsumerPortlet = toUnwrappedModel(wsrpConsumerPortlet);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, wsrpConsumerPortlet, merge);

			wsrpConsumerPortlet.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletImpl.class, wsrpConsumerPortlet.getPrimaryKey(),
			wsrpConsumerPortlet);

		return wsrpConsumerPortlet;
	}

	protected WSRPConsumerPortlet toUnwrappedModel(
		WSRPConsumerPortlet wsrpConsumerPortlet) {
		if (wsrpConsumerPortlet instanceof WSRPConsumerPortletImpl) {
			return wsrpConsumerPortlet;
		}

		WSRPConsumerPortletImpl wsrpConsumerPortletImpl = new WSRPConsumerPortletImpl();

		wsrpConsumerPortletImpl.setNew(wsrpConsumerPortlet.isNew());
		wsrpConsumerPortletImpl.setPrimaryKey(wsrpConsumerPortlet.getPrimaryKey());

		wsrpConsumerPortletImpl.setWsrpConsumerPortletId(wsrpConsumerPortlet.getWsrpConsumerPortletId());
		wsrpConsumerPortletImpl.setCompanyId(wsrpConsumerPortlet.getCompanyId());
		wsrpConsumerPortletImpl.setCreateDate(wsrpConsumerPortlet.getCreateDate());
		wsrpConsumerPortletImpl.setModifiedDate(wsrpConsumerPortlet.getModifiedDate());
		wsrpConsumerPortletImpl.setWsrpConsumerId(wsrpConsumerPortlet.getWsrpConsumerId());
		wsrpConsumerPortletImpl.setName(wsrpConsumerPortlet.getName());
		wsrpConsumerPortletImpl.setPortletHandle(wsrpConsumerPortlet.getPortletHandle());

		return wsrpConsumerPortletImpl;
	}

	public WSRPConsumerPortlet findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public WSRPConsumerPortlet findByPrimaryKey(long wsrpConsumerPortletId)
		throws NoSuchConsumerPortletException, SystemException {
		WSRPConsumerPortlet wsrpConsumerPortlet = fetchByPrimaryKey(wsrpConsumerPortletId);

		if (wsrpConsumerPortlet == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					wsrpConsumerPortletId);
			}

			throw new NoSuchConsumerPortletException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				wsrpConsumerPortletId);
		}

		return wsrpConsumerPortlet;
	}

	public WSRPConsumerPortlet fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public WSRPConsumerPortlet fetchByPrimaryKey(long wsrpConsumerPortletId)
		throws SystemException {
		WSRPConsumerPortlet wsrpConsumerPortlet = (WSRPConsumerPortlet)EntityCacheUtil.getResult(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
				WSRPConsumerPortletImpl.class, wsrpConsumerPortletId, this);

		if (wsrpConsumerPortlet == null) {
			Session session = null;

			try {
				session = openSession();

				wsrpConsumerPortlet = (WSRPConsumerPortlet)session.get(WSRPConsumerPortletImpl.class,
						new Long(wsrpConsumerPortletId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (wsrpConsumerPortlet != null) {
					cacheResult(wsrpConsumerPortlet);
				}

				closeSession(session);
			}
		}

		return wsrpConsumerPortlet;
	}

	public List<WSRPConsumerPortlet> findByWsrpConsumerId(long wsrpConsumerId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(wsrpConsumerId) };

		List<WSRPConsumerPortlet> list = (List<WSRPConsumerPortlet>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_WSRPCONSUMERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_SELECT_WSRPCONSUMERPORTLET_WHERE);

				query.append(_FINDER_COLUMN_WSRPCONSUMERID_WSRPCONSUMERID_2);

				query.append(WSRPConsumerPortletModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(wsrpConsumerId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPConsumerPortlet>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_WSRPCONSUMERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WSRPConsumerPortlet> findByWsrpConsumerId(long wsrpConsumerId,
		int start, int end) throws SystemException {
		return findByWsrpConsumerId(wsrpConsumerId, start, end, null);
	}

	public List<WSRPConsumerPortlet> findByWsrpConsumerId(long wsrpConsumerId,
		int start, int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(wsrpConsumerId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WSRPConsumerPortlet> list = (List<WSRPConsumerPortlet>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_WSRPCONSUMERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (obc != null) {
					query = new StringBundler(3 +
							(obc.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(3);
				}

				query.append(_SQL_SELECT_WSRPCONSUMERPORTLET_WHERE);

				query.append(_FINDER_COLUMN_WSRPCONSUMERID_WSRPCONSUMERID_2);

				if (obc != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);
				}

				else {
					query.append(WSRPConsumerPortletModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(wsrpConsumerId);

				list = (List<WSRPConsumerPortlet>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPConsumerPortlet>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_WSRPCONSUMERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WSRPConsumerPortlet findByWsrpConsumerId_First(long wsrpConsumerId,
		OrderByComparator obc)
		throws NoSuchConsumerPortletException, SystemException {
		List<WSRPConsumerPortlet> list = findByWsrpConsumerId(wsrpConsumerId,
				0, 1, obc);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("wsrpConsumerId=");
			msg.append(wsrpConsumerId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchConsumerPortletException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WSRPConsumerPortlet findByWsrpConsumerId_Last(long wsrpConsumerId,
		OrderByComparator obc)
		throws NoSuchConsumerPortletException, SystemException {
		int count = countByWsrpConsumerId(wsrpConsumerId);

		List<WSRPConsumerPortlet> list = findByWsrpConsumerId(wsrpConsumerId,
				count - 1, count, obc);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("wsrpConsumerId=");
			msg.append(wsrpConsumerId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchConsumerPortletException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WSRPConsumerPortlet[] findByWsrpConsumerId_PrevAndNext(
		long wsrpConsumerPortletId, long wsrpConsumerId, OrderByComparator obc)
		throws NoSuchConsumerPortletException, SystemException {
		WSRPConsumerPortlet wsrpConsumerPortlet = findByPrimaryKey(wsrpConsumerPortletId);

		int count = countByWsrpConsumerId(wsrpConsumerId);

		Session session = null;

		try {
			session = openSession();

			StringBundler query = null;

			if (obc != null) {
				query = new StringBundler(3 +
						(obc.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_WSRPCONSUMERPORTLET_WHERE);

			query.append(_FINDER_COLUMN_WSRPCONSUMERID_WSRPCONSUMERID_2);

			if (obc != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);
			}

			else {
				query.append(WSRPConsumerPortletModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(wsrpConsumerId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					wsrpConsumerPortlet);

			WSRPConsumerPortlet[] array = new WSRPConsumerPortletImpl[3];

			array[0] = (WSRPConsumerPortlet)objArray[0];
			array[1] = (WSRPConsumerPortlet)objArray[1];
			array[2] = (WSRPConsumerPortlet)objArray[2];

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

	public List<WSRPConsumerPortlet> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<WSRPConsumerPortlet> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<WSRPConsumerPortlet> findAll(int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WSRPConsumerPortlet> list = (List<WSRPConsumerPortlet>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;
				String sql = null;

				if (obc != null) {
					query = new StringBundler(2 +
							(obc.getOrderByFields().length * 3));

					query.append(_SQL_SELECT_WSRPCONSUMERPORTLET);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);

					sql = query.toString();
				}

				else {
					sql = _SQL_SELECT_WSRPCONSUMERPORTLET.concat(WSRPConsumerPortletModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (obc == null) {
					list = (List<WSRPConsumerPortlet>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<WSRPConsumerPortlet>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPConsumerPortlet>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByWsrpConsumerId(long wsrpConsumerId)
		throws SystemException {
		for (WSRPConsumerPortlet wsrpConsumerPortlet : findByWsrpConsumerId(
				wsrpConsumerId)) {
			remove(wsrpConsumerPortlet);
		}
	}

	public void removeAll() throws SystemException {
		for (WSRPConsumerPortlet wsrpConsumerPortlet : findAll()) {
			remove(wsrpConsumerPortlet);
		}
	}

	public int countByWsrpConsumerId(long wsrpConsumerId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(wsrpConsumerId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_WSRPCONSUMERID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_WSRPCONSUMERPORTLET_WHERE);

				query.append(_FINDER_COLUMN_WSRPCONSUMERID_WSRPCONSUMERID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(wsrpConsumerId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_WSRPCONSUMERID,
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

				Query q = session.createQuery(_SQL_COUNT_WSRPCONSUMERPORTLET);

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
						"value.object.listener.com.liferay.wsrp.model.WSRPConsumerPortlet")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<WSRPConsumerPortlet>> listenersList = new ArrayList<ModelListener<WSRPConsumerPortlet>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<WSRPConsumerPortlet>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(name = "com.liferay.wsrp.service.persistence.WSRPConsumerPersistence")
	protected com.liferay.wsrp.service.persistence.WSRPConsumerPersistence wsrpConsumerPersistence;
	@BeanReference(name = "com.liferay.wsrp.service.persistence.WSRPConsumerPortletPersistence")
	protected com.liferay.wsrp.service.persistence.WSRPConsumerPortletPersistence wsrpConsumerPortletPersistence;
	@BeanReference(name = "com.liferay.wsrp.service.persistence.WSRPProducerPersistence")
	protected com.liferay.wsrp.service.persistence.WSRPProducerPersistence wsrpProducerPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.ResourcePersistence")
	protected com.liferay.portal.service.persistence.ResourcePersistence resourcePersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.UserPersistence")
	protected com.liferay.portal.service.persistence.UserPersistence userPersistence;
	private static final String _SQL_SELECT_WSRPCONSUMERPORTLET = "SELECT wsrpConsumerPortlet FROM WSRPConsumerPortlet wsrpConsumerPortlet";
	private static final String _SQL_SELECT_WSRPCONSUMERPORTLET_WHERE = "SELECT wsrpConsumerPortlet FROM WSRPConsumerPortlet wsrpConsumerPortlet WHERE ";
	private static final String _SQL_COUNT_WSRPCONSUMERPORTLET = "SELECT COUNT(wsrpConsumerPortlet) FROM WSRPConsumerPortlet wsrpConsumerPortlet";
	private static final String _SQL_COUNT_WSRPCONSUMERPORTLET_WHERE = "SELECT COUNT(wsrpConsumerPortlet) FROM WSRPConsumerPortlet wsrpConsumerPortlet WHERE ";
	private static final String _FINDER_COLUMN_WSRPCONSUMERID_WSRPCONSUMERID_2 = "wsrpConsumerPortlet.wsrpConsumerId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "wsrpConsumerPortlet.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WSRPConsumerPortlet exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No WSRPConsumerPortlet exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(WSRPConsumerPortletPersistenceImpl.class);
}