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
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
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
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.GroupPersistence;
import com.liferay.portal.service.persistence.LayoutPersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.wsrp.NoSuchProducerException;
import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.model.impl.WSRPProducerImpl;
import com.liferay.wsrp.model.impl.WSRPProducerModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author    Brian Wing Shun Chan
 * @see       WSRPProducerPersistence
 * @see       WSRPProducerUtil
 * @generated
 */
public class WSRPProducerPersistenceImpl extends BasePersistenceImpl<WSRPProducer>
	implements WSRPProducerPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = WSRPProducerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByCompanyId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(WSRPProducer wsrpProducer) {
		EntityCacheUtil.putResult(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerImpl.class, wsrpProducer.getPrimaryKey(), wsrpProducer);
	}

	public void cacheResult(List<WSRPProducer> wsrpProducers) {
		for (WSRPProducer wsrpProducer : wsrpProducers) {
			if (EntityCacheUtil.getResult(
						WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
						WSRPProducerImpl.class, wsrpProducer.getPrimaryKey(),
						this) == null) {
				cacheResult(wsrpProducer);
			}
		}
	}

	public void clearCache() {
		CacheRegistryUtil.clear(WSRPProducerImpl.class.getName());
		EntityCacheUtil.clearCache(WSRPProducerImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public void clearCache(WSRPProducer wsrpProducer) {
		EntityCacheUtil.removeResult(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerImpl.class, wsrpProducer.getPrimaryKey());
	}

	public WSRPProducer create(long wsrpProducerId) {
		WSRPProducer wsrpProducer = new WSRPProducerImpl();

		wsrpProducer.setNew(true);
		wsrpProducer.setPrimaryKey(wsrpProducerId);

		return wsrpProducer;
	}

	public WSRPProducer remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public WSRPProducer remove(long wsrpProducerId)
		throws NoSuchProducerException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WSRPProducer wsrpProducer = (WSRPProducer)session.get(WSRPProducerImpl.class,
					new Long(wsrpProducerId));

			if (wsrpProducer == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						wsrpProducerId);
				}

				throw new NoSuchProducerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					wsrpProducerId);
			}

			return remove(wsrpProducer);
		}
		catch (NoSuchProducerException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WSRPProducer removeImpl(WSRPProducer wsrpProducer)
		throws SystemException {
		wsrpProducer = toUnwrappedModel(wsrpProducer);

		Session session = null;

		try {
			session = openSession();

			if (wsrpProducer.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(WSRPProducerImpl.class,
						wsrpProducer.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(wsrpProducer);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerImpl.class, wsrpProducer.getPrimaryKey());

		return wsrpProducer;
	}

	public WSRPProducer updateImpl(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer, boolean merge)
		throws SystemException {
		wsrpProducer = toUnwrappedModel(wsrpProducer);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, wsrpProducer, merge);

			wsrpProducer.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerImpl.class, wsrpProducer.getPrimaryKey(), wsrpProducer);

		return wsrpProducer;
	}

	protected WSRPProducer toUnwrappedModel(WSRPProducer wsrpProducer) {
		if (wsrpProducer instanceof WSRPProducerImpl) {
			return wsrpProducer;
		}

		WSRPProducerImpl wsrpProducerImpl = new WSRPProducerImpl();

		wsrpProducerImpl.setNew(wsrpProducer.isNew());
		wsrpProducerImpl.setPrimaryKey(wsrpProducer.getPrimaryKey());

		wsrpProducerImpl.setWsrpProducerId(wsrpProducer.getWsrpProducerId());
		wsrpProducerImpl.setGroupId(wsrpProducer.getGroupId());
		wsrpProducerImpl.setCompanyId(wsrpProducer.getCompanyId());
		wsrpProducerImpl.setCreateDate(wsrpProducer.getCreateDate());
		wsrpProducerImpl.setModifiedDate(wsrpProducer.getModifiedDate());
		wsrpProducerImpl.setName(wsrpProducer.getName());
		wsrpProducerImpl.setPortletIds(wsrpProducer.getPortletIds());

		return wsrpProducerImpl;
	}

	public WSRPProducer findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public WSRPProducer findByPrimaryKey(long wsrpProducerId)
		throws NoSuchProducerException, SystemException {
		WSRPProducer wsrpProducer = fetchByPrimaryKey(wsrpProducerId);

		if (wsrpProducer == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + wsrpProducerId);
			}

			throw new NoSuchProducerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				wsrpProducerId);
		}

		return wsrpProducer;
	}

	public WSRPProducer fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public WSRPProducer fetchByPrimaryKey(long wsrpProducerId)
		throws SystemException {
		WSRPProducer wsrpProducer = (WSRPProducer)EntityCacheUtil.getResult(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
				WSRPProducerImpl.class, wsrpProducerId, this);

		if (wsrpProducer == null) {
			Session session = null;

			try {
				session = openSession();

				wsrpProducer = (WSRPProducer)session.get(WSRPProducerImpl.class,
						new Long(wsrpProducerId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (wsrpProducer != null) {
					cacheResult(wsrpProducer);
				}

				closeSession(session);
			}
		}

		return wsrpProducer;
	}

	public List<WSRPProducer> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	public List<WSRPProducer> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	public List<WSRPProducer> findByCompanyId(long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<WSRPProducer> list = (List<WSRPProducer>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (orderByComparator != null) {
					query = new StringBundler(3 +
							(orderByComparator.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(3);
				}

				query.append(_SQL_SELECT_WSRPPRODUCER_WHERE);

				query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(WSRPProducerModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<WSRPProducer>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPProducer>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WSRPProducer findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchProducerException, SystemException {
		List<WSRPProducer> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchProducerException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WSRPProducer findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchProducerException, SystemException {
		int count = countByCompanyId(companyId);

		List<WSRPProducer> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchProducerException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WSRPProducer[] findByCompanyId_PrevAndNext(long wsrpProducerId,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchProducerException, SystemException {
		WSRPProducer wsrpProducer = findByPrimaryKey(wsrpProducerId);

		Session session = null;

		try {
			session = openSession();

			WSRPProducer[] array = new WSRPProducerImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, wsrpProducer,
					companyId, orderByComparator, true);

			array[1] = wsrpProducer;

			array[2] = getByCompanyId_PrevAndNext(session, wsrpProducer,
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

	protected WSRPProducer getByCompanyId_PrevAndNext(Session session,
		WSRPProducer wsrpProducer, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WSRPPRODUCER_WHERE);

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
			query.append(WSRPProducerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(wsrpProducer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WSRPProducer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	public List<WSRPProducer> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<WSRPProducer> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<WSRPProducer> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<WSRPProducer> list = (List<WSRPProducer>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_WSRPPRODUCER);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_WSRPPRODUCER.concat(WSRPProducerModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<WSRPProducer>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<WSRPProducer>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPProducer>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByCompanyId(long companyId) throws SystemException {
		for (WSRPProducer wsrpProducer : findByCompanyId(companyId)) {
			remove(wsrpProducer);
		}
	}

	public void removeAll() throws SystemException {
		for (WSRPProducer wsrpProducer : findAll()) {
			remove(wsrpProducer);
		}
	}

	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_WSRPPRODUCER_WHERE);

				query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

				String sql = query.toString();

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

	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WSRPPRODUCER);

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
						"value.object.listener.com.liferay.wsrp.model.WSRPProducer")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<WSRPProducer>> listenersList = new ArrayList<ModelListener<WSRPProducer>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<WSRPProducer>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(type = WSRPConsumerPersistence.class)
	protected WSRPConsumerPersistence wsrpConsumerPersistence;
	@BeanReference(type = WSRPConsumerPortletPersistence.class)
	protected WSRPConsumerPortletPersistence wsrpConsumerPortletPersistence;
	@BeanReference(type = WSRPProducerPersistence.class)
	protected WSRPProducerPersistence wsrpProducerPersistence;
	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;
	@BeanReference(type = LayoutPersistence.class)
	protected LayoutPersistence layoutPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_WSRPPRODUCER = "SELECT wsrpProducer FROM WSRPProducer wsrpProducer";
	private static final String _SQL_SELECT_WSRPPRODUCER_WHERE = "SELECT wsrpProducer FROM WSRPProducer wsrpProducer WHERE ";
	private static final String _SQL_COUNT_WSRPPRODUCER = "SELECT COUNT(wsrpProducer) FROM WSRPProducer wsrpProducer";
	private static final String _SQL_COUNT_WSRPPRODUCER_WHERE = "SELECT COUNT(wsrpProducer) FROM WSRPProducer wsrpProducer WHERE ";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "wsrpProducer.companyId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "wsrpProducer.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WSRPProducer exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No WSRPProducer exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(WSRPProducerPersistenceImpl.class);
}