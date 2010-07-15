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
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.wsrp.NoSuchConsumerException;
import com.liferay.wsrp.model.WSRPConsumer;
import com.liferay.wsrp.model.impl.WSRPConsumerImpl;
import com.liferay.wsrp.model.impl.WSRPConsumerModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author    Brian Wing Shun Chan
 * @see       WSRPConsumerPersistence
 * @see       WSRPConsumerUtil
 * @generated
 */
public class WSRPConsumerPersistenceImpl extends BasePersistenceImpl<WSRPConsumer>
	implements WSRPConsumerPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = WSRPConsumerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByCompanyId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(WSRPConsumer wsrpConsumer) {
		EntityCacheUtil.putResult(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerImpl.class, wsrpConsumer.getPrimaryKey(), wsrpConsumer);
	}

	public void cacheResult(List<WSRPConsumer> wsrpConsumers) {
		for (WSRPConsumer wsrpConsumer : wsrpConsumers) {
			if (EntityCacheUtil.getResult(
						WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
						WSRPConsumerImpl.class, wsrpConsumer.getPrimaryKey(),
						this) == null) {
				cacheResult(wsrpConsumer);
			}
		}
	}

	public void clearCache() {
		CacheRegistryUtil.clear(WSRPConsumerImpl.class.getName());
		EntityCacheUtil.clearCache(WSRPConsumerImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public void clearCache(WSRPConsumer wsrpConsumer) {
		EntityCacheUtil.removeResult(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerImpl.class, wsrpConsumer.getPrimaryKey());
	}

	public WSRPConsumer create(long wsrpConsumerId) {
		WSRPConsumer wsrpConsumer = new WSRPConsumerImpl();

		wsrpConsumer.setNew(true);
		wsrpConsumer.setPrimaryKey(wsrpConsumerId);

		return wsrpConsumer;
	}

	public WSRPConsumer remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public WSRPConsumer remove(long wsrpConsumerId)
		throws NoSuchConsumerException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WSRPConsumer wsrpConsumer = (WSRPConsumer)session.get(WSRPConsumerImpl.class,
					new Long(wsrpConsumerId));

			if (wsrpConsumer == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						wsrpConsumerId);
				}

				throw new NoSuchConsumerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					wsrpConsumerId);
			}

			return remove(wsrpConsumer);
		}
		catch (NoSuchConsumerException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WSRPConsumer removeImpl(WSRPConsumer wsrpConsumer)
		throws SystemException {
		wsrpConsumer = toUnwrappedModel(wsrpConsumer);

		Session session = null;

		try {
			session = openSession();

			if (wsrpConsumer.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(WSRPConsumerImpl.class,
						wsrpConsumer.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(wsrpConsumer);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerImpl.class, wsrpConsumer.getPrimaryKey());

		return wsrpConsumer;
	}

	public WSRPConsumer updateImpl(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer, boolean merge)
		throws SystemException {
		wsrpConsumer = toUnwrappedModel(wsrpConsumer);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, wsrpConsumer, merge);

			wsrpConsumer.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerImpl.class, wsrpConsumer.getPrimaryKey(), wsrpConsumer);

		return wsrpConsumer;
	}

	protected WSRPConsumer toUnwrappedModel(WSRPConsumer wsrpConsumer) {
		if (wsrpConsumer instanceof WSRPConsumerImpl) {
			return wsrpConsumer;
		}

		WSRPConsumerImpl wsrpConsumerImpl = new WSRPConsumerImpl();

		wsrpConsumerImpl.setNew(wsrpConsumer.isNew());
		wsrpConsumerImpl.setPrimaryKey(wsrpConsumer.getPrimaryKey());

		wsrpConsumerImpl.setWsrpConsumerId(wsrpConsumer.getWsrpConsumerId());
		wsrpConsumerImpl.setCompanyId(wsrpConsumer.getCompanyId());
		wsrpConsumerImpl.setCreateDate(wsrpConsumer.getCreateDate());
		wsrpConsumerImpl.setModifiedDate(wsrpConsumer.getModifiedDate());
		wsrpConsumerImpl.setName(wsrpConsumer.getName());
		wsrpConsumerImpl.setUrl(wsrpConsumer.getUrl());
		wsrpConsumerImpl.setWsdl(wsrpConsumer.getWsdl());
		wsrpConsumerImpl.setRegistrationContextString(wsrpConsumer.getRegistrationContextString());
		wsrpConsumerImpl.setRegistrationPropertiesString(wsrpConsumer.getRegistrationPropertiesString());

		return wsrpConsumerImpl;
	}

	public WSRPConsumer findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public WSRPConsumer findByPrimaryKey(long wsrpConsumerId)
		throws NoSuchConsumerException, SystemException {
		WSRPConsumer wsrpConsumer = fetchByPrimaryKey(wsrpConsumerId);

		if (wsrpConsumer == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + wsrpConsumerId);
			}

			throw new NoSuchConsumerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				wsrpConsumerId);
		}

		return wsrpConsumer;
	}

	public WSRPConsumer fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public WSRPConsumer fetchByPrimaryKey(long wsrpConsumerId)
		throws SystemException {
		WSRPConsumer wsrpConsumer = (WSRPConsumer)EntityCacheUtil.getResult(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
				WSRPConsumerImpl.class, wsrpConsumerId, this);

		if (wsrpConsumer == null) {
			Session session = null;

			try {
				session = openSession();

				wsrpConsumer = (WSRPConsumer)session.get(WSRPConsumerImpl.class,
						new Long(wsrpConsumerId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (wsrpConsumer != null) {
					cacheResult(wsrpConsumer);
				}

				closeSession(session);
			}
		}

		return wsrpConsumer;
	}

	public List<WSRPConsumer> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	public List<WSRPConsumer> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	public List<WSRPConsumer> findByCompanyId(long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<WSRPConsumer> list = (List<WSRPConsumer>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
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

				query.append(_SQL_SELECT_WSRPCONSUMER_WHERE);

				query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				else {
					query.append(WSRPConsumerModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<WSRPConsumer>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPConsumer>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WSRPConsumer findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchConsumerException, SystemException {
		List<WSRPConsumer> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchConsumerException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WSRPConsumer findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchConsumerException, SystemException {
		int count = countByCompanyId(companyId);

		List<WSRPConsumer> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchConsumerException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WSRPConsumer[] findByCompanyId_PrevAndNext(long wsrpConsumerId,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchConsumerException, SystemException {
		WSRPConsumer wsrpConsumer = findByPrimaryKey(wsrpConsumerId);

		Session session = null;

		try {
			session = openSession();

			WSRPConsumer[] array = new WSRPConsumerImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, wsrpConsumer,
					companyId, orderByComparator, true);

			array[1] = wsrpConsumer;

			array[2] = getByCompanyId_PrevAndNext(session, wsrpConsumer,
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

	protected WSRPConsumer getByCompanyId_PrevAndNext(Session session,
		WSRPConsumer wsrpConsumer, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WSRPCONSUMER_WHERE);

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
			query.append(WSRPConsumerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(wsrpConsumer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WSRPConsumer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	public List<WSRPConsumer> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<WSRPConsumer> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<WSRPConsumer> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<WSRPConsumer> list = (List<WSRPConsumer>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_WSRPCONSUMER);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_WSRPCONSUMER.concat(WSRPConsumerModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<WSRPConsumer>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<WSRPConsumer>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPConsumer>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByCompanyId(long companyId) throws SystemException {
		for (WSRPConsumer wsrpConsumer : findByCompanyId(companyId)) {
			remove(wsrpConsumer);
		}
	}

	public void removeAll() throws SystemException {
		for (WSRPConsumer wsrpConsumer : findAll()) {
			remove(wsrpConsumer);
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

				query.append(_SQL_COUNT_WSRPCONSUMER_WHERE);

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

				Query q = session.createQuery(_SQL_COUNT_WSRPCONSUMER);

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
						"value.object.listener.com.liferay.wsrp.model.WSRPConsumer")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<WSRPConsumer>> listenersList = new ArrayList<ModelListener<WSRPConsumer>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<WSRPConsumer>)InstanceFactory.newInstance(
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
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_WSRPCONSUMER = "SELECT wsrpConsumer FROM WSRPConsumer wsrpConsumer";
	private static final String _SQL_SELECT_WSRPCONSUMER_WHERE = "SELECT wsrpConsumer FROM WSRPConsumer wsrpConsumer WHERE ";
	private static final String _SQL_COUNT_WSRPCONSUMER = "SELECT COUNT(wsrpConsumer) FROM WSRPConsumer wsrpConsumer";
	private static final String _SQL_COUNT_WSRPCONSUMER_WHERE = "SELECT COUNT(wsrpConsumer) FROM WSRPConsumer wsrpConsumer WHERE ";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "wsrpConsumer.companyId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "wsrpConsumer.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WSRPConsumer exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No WSRPConsumer exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(WSRPConsumerPersistenceImpl.class);
}