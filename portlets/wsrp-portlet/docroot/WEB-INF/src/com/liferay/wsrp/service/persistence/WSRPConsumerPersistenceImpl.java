/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.bean.BeanReference;
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
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
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
 * The persistence implementation for the w s r p consumer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumerPersistence
 * @see WSRPConsumerUtil
 * @generated
 */
public class WSRPConsumerPersistenceImpl extends BasePersistenceImpl<WSRPConsumer>
	implements WSRPConsumerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WSRPConsumerUtil} to access the w s r p consumer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WSRPConsumerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, WSRPConsumerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, WSRPConsumerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			WSRPConsumerModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, WSRPConsumerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, WSRPConsumerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			WSRPConsumerModelImpl.UUID_COLUMN_BITMASK |
			WSRPConsumerModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, WSRPConsumerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, WSRPConsumerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			WSRPConsumerModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, WSRPConsumerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, WSRPConsumerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the w s r p consumer in the entity cache if it is enabled.
	 *
	 * @param wsrpConsumer the w s r p consumer
	 */
	public void cacheResult(WSRPConsumer wsrpConsumer) {
		EntityCacheUtil.putResult(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerImpl.class, wsrpConsumer.getPrimaryKey(), wsrpConsumer);

		wsrpConsumer.resetOriginalValues();
	}

	/**
	 * Caches the w s r p consumers in the entity cache if it is enabled.
	 *
	 * @param wsrpConsumers the w s r p consumers
	 */
	public void cacheResult(List<WSRPConsumer> wsrpConsumers) {
		for (WSRPConsumer wsrpConsumer : wsrpConsumers) {
			if (EntityCacheUtil.getResult(
						WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
						WSRPConsumerImpl.class, wsrpConsumer.getPrimaryKey()) == null) {
				cacheResult(wsrpConsumer);
			}
			else {
				wsrpConsumer.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all w s r p consumers.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(WSRPConsumerImpl.class.getName());
		}

		EntityCacheUtil.clearCache(WSRPConsumerImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the w s r p consumer.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WSRPConsumer wsrpConsumer) {
		EntityCacheUtil.removeResult(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerImpl.class, wsrpConsumer.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WSRPConsumer> wsrpConsumers) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WSRPConsumer wsrpConsumer : wsrpConsumers) {
			EntityCacheUtil.removeResult(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
				WSRPConsumerImpl.class, wsrpConsumer.getPrimaryKey());
		}
	}

	/**
	 * Creates a new w s r p consumer with the primary key. Does not add the w s r p consumer to the database.
	 *
	 * @param wsrpConsumerId the primary key for the new w s r p consumer
	 * @return the new w s r p consumer
	 */
	public WSRPConsumer create(long wsrpConsumerId) {
		WSRPConsumer wsrpConsumer = new WSRPConsumerImpl();

		wsrpConsumer.setNew(true);
		wsrpConsumer.setPrimaryKey(wsrpConsumerId);

		String uuid = PortalUUIDUtil.generate();

		wsrpConsumer.setUuid(uuid);

		return wsrpConsumer;
	}

	/**
	 * Removes the w s r p consumer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param wsrpConsumerId the primary key of the w s r p consumer
	 * @return the w s r p consumer that was removed
	 * @throws com.liferay.wsrp.NoSuchConsumerException if a w s r p consumer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WSRPConsumer remove(long wsrpConsumerId)
		throws NoSuchConsumerException, SystemException {
		return remove(Long.valueOf(wsrpConsumerId));
	}

	/**
	 * Removes the w s r p consumer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the w s r p consumer
	 * @return the w s r p consumer that was removed
	 * @throws com.liferay.wsrp.NoSuchConsumerException if a w s r p consumer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumer remove(Serializable primaryKey)
		throws NoSuchConsumerException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WSRPConsumer wsrpConsumer = (WSRPConsumer)session.get(WSRPConsumerImpl.class,
					primaryKey);

			if (wsrpConsumer == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchConsumerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
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

	@Override
	protected WSRPConsumer removeImpl(WSRPConsumer wsrpConsumer)
		throws SystemException {
		wsrpConsumer = toUnwrappedModel(wsrpConsumer);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, wsrpConsumer);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(wsrpConsumer);

		return wsrpConsumer;
	}

	@Override
	public WSRPConsumer updateImpl(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer, boolean merge)
		throws SystemException {
		wsrpConsumer = toUnwrappedModel(wsrpConsumer);

		boolean isNew = wsrpConsumer.isNew();

		WSRPConsumerModelImpl wsrpConsumerModelImpl = (WSRPConsumerModelImpl)wsrpConsumer;

		if (Validator.isNull(wsrpConsumer.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			wsrpConsumer.setUuid(uuid);
		}

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

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !WSRPConsumerModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((wsrpConsumerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						wsrpConsumerModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { wsrpConsumerModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((wsrpConsumerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						wsrpConsumerModelImpl.getOriginalUuid(),
						Long.valueOf(wsrpConsumerModelImpl.getOriginalCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						wsrpConsumerModelImpl.getUuid(),
						Long.valueOf(wsrpConsumerModelImpl.getCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((wsrpConsumerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(wsrpConsumerModelImpl.getOriginalCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] {
						Long.valueOf(wsrpConsumerModelImpl.getCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}
		}

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

		wsrpConsumerImpl.setUuid(wsrpConsumer.getUuid());
		wsrpConsumerImpl.setWsrpConsumerId(wsrpConsumer.getWsrpConsumerId());
		wsrpConsumerImpl.setCompanyId(wsrpConsumer.getCompanyId());
		wsrpConsumerImpl.setCreateDate(wsrpConsumer.getCreateDate());
		wsrpConsumerImpl.setModifiedDate(wsrpConsumer.getModifiedDate());
		wsrpConsumerImpl.setName(wsrpConsumer.getName());
		wsrpConsumerImpl.setUrl(wsrpConsumer.getUrl());
		wsrpConsumerImpl.setWsdl(wsrpConsumer.getWsdl());
		wsrpConsumerImpl.setRegistrationContextString(wsrpConsumer.getRegistrationContextString());
		wsrpConsumerImpl.setRegistrationPropertiesString(wsrpConsumer.getRegistrationPropertiesString());
		wsrpConsumerImpl.setForwardCookies(wsrpConsumer.getForwardCookies());

		return wsrpConsumerImpl;
	}

	/**
	 * Returns the w s r p consumer with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the w s r p consumer
	 * @return the w s r p consumer
	 * @throws com.liferay.portal.NoSuchModelException if a w s r p consumer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumer findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the w s r p consumer with the primary key or throws a {@link com.liferay.wsrp.NoSuchConsumerException} if it could not be found.
	 *
	 * @param wsrpConsumerId the primary key of the w s r p consumer
	 * @return the w s r p consumer
	 * @throws com.liferay.wsrp.NoSuchConsumerException if a w s r p consumer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
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

	/**
	 * Returns the w s r p consumer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the w s r p consumer
	 * @return the w s r p consumer, or <code>null</code> if a w s r p consumer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumer fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the w s r p consumer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param wsrpConsumerId the primary key of the w s r p consumer
	 * @return the w s r p consumer, or <code>null</code> if a w s r p consumer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WSRPConsumer fetchByPrimaryKey(long wsrpConsumerId)
		throws SystemException {
		WSRPConsumer wsrpConsumer = (WSRPConsumer)EntityCacheUtil.getResult(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
				WSRPConsumerImpl.class, wsrpConsumerId);

		if (wsrpConsumer == _nullWSRPConsumer) {
			return null;
		}

		if (wsrpConsumer == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				wsrpConsumer = (WSRPConsumer)session.get(WSRPConsumerImpl.class,
						Long.valueOf(wsrpConsumerId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (wsrpConsumer != null) {
					cacheResult(wsrpConsumer);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
						WSRPConsumerImpl.class, wsrpConsumerId,
						_nullWSRPConsumer);
				}

				closeSession(session);
			}
		}

		return wsrpConsumer;
	}

	/**
	 * Returns all the w s r p consumers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching w s r p consumers
	 * @throws SystemException if a system exception occurred
	 */
	public List<WSRPConsumer> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the w s r p consumers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of w s r p consumers
	 * @param end the upper bound of the range of w s r p consumers (not inclusive)
	 * @return the range of matching w s r p consumers
	 * @throws SystemException if a system exception occurred
	 */
	public List<WSRPConsumer> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the w s r p consumers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of w s r p consumers
	 * @param end the upper bound of the range of w s r p consumers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching w s r p consumers
	 * @throws SystemException if a system exception occurred
	 */
	public List<WSRPConsumer> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<WSRPConsumer> list = (List<WSRPConsumer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WSRPConsumer wsrpConsumer : list) {
				if (!Validator.equals(uuid, wsrpConsumer.getUuid())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_WSRPCONSUMER_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(WSRPConsumerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				list = (List<WSRPConsumer>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first w s r p consumer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching w s r p consumer
	 * @throws com.liferay.wsrp.NoSuchConsumerException if a matching w s r p consumer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WSRPConsumer findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchConsumerException, SystemException {
		WSRPConsumer wsrpConsumer = fetchByUuid_First(uuid, orderByComparator);

		if (wsrpConsumer != null) {
			return wsrpConsumer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConsumerException(msg.toString());
	}

	/**
	 * Returns the first w s r p consumer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching w s r p consumer, or <code>null</code> if a matching w s r p consumer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WSRPConsumer fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<WSRPConsumer> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last w s r p consumer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching w s r p consumer
	 * @throws com.liferay.wsrp.NoSuchConsumerException if a matching w s r p consumer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WSRPConsumer findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchConsumerException, SystemException {
		WSRPConsumer wsrpConsumer = fetchByUuid_Last(uuid, orderByComparator);

		if (wsrpConsumer != null) {
			return wsrpConsumer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConsumerException(msg.toString());
	}

	/**
	 * Returns the last w s r p consumer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching w s r p consumer, or <code>null</code> if a matching w s r p consumer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WSRPConsumer fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		List<WSRPConsumer> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the w s r p consumers before and after the current w s r p consumer in the ordered set where uuid = &#63;.
	 *
	 * @param wsrpConsumerId the primary key of the current w s r p consumer
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next w s r p consumer
	 * @throws com.liferay.wsrp.NoSuchConsumerException if a w s r p consumer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WSRPConsumer[] findByUuid_PrevAndNext(long wsrpConsumerId,
		String uuid, OrderByComparator orderByComparator)
		throws NoSuchConsumerException, SystemException {
		WSRPConsumer wsrpConsumer = findByPrimaryKey(wsrpConsumerId);

		Session session = null;

		try {
			session = openSession();

			WSRPConsumer[] array = new WSRPConsumerImpl[3];

			array[0] = getByUuid_PrevAndNext(session, wsrpConsumer, uuid,
					orderByComparator, true);

			array[1] = wsrpConsumer;

			array[2] = getByUuid_PrevAndNext(session, wsrpConsumer, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WSRPConsumer getByUuid_PrevAndNext(Session session,
		WSRPConsumer wsrpConsumer, String uuid,
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

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else {
			if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
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

			String[] orderByFields = orderByComparator.getOrderByFields();

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

		if (uuid != null) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(wsrpConsumer);

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

	/**
	 * Returns all the w s r p consumers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching w s r p consumers
	 * @throws SystemException if a system exception occurred
	 */
	public List<WSRPConsumer> findByUuid_C(String uuid, long companyId)
		throws SystemException {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the w s r p consumers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of w s r p consumers
	 * @param end the upper bound of the range of w s r p consumers (not inclusive)
	 * @return the range of matching w s r p consumers
	 * @throws SystemException if a system exception occurred
	 */
	public List<WSRPConsumer> findByUuid_C(String uuid, long companyId,
		int start, int end) throws SystemException {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the w s r p consumers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of w s r p consumers
	 * @param end the upper bound of the range of w s r p consumers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching w s r p consumers
	 * @throws SystemException if a system exception occurred
	 */
	public List<WSRPConsumer> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<WSRPConsumer> list = (List<WSRPConsumer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WSRPConsumer wsrpConsumer : list) {
				if (!Validator.equals(uuid, wsrpConsumer.getUuid()) ||
						(companyId != wsrpConsumer.getCompanyId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_WSRPCONSUMER_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_C_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_C_UUID_2);
				}
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(WSRPConsumerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				list = (List<WSRPConsumer>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first w s r p consumer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching w s r p consumer
	 * @throws com.liferay.wsrp.NoSuchConsumerException if a matching w s r p consumer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WSRPConsumer findByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchConsumerException, SystemException {
		WSRPConsumer wsrpConsumer = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (wsrpConsumer != null) {
			return wsrpConsumer;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConsumerException(msg.toString());
	}

	/**
	 * Returns the first w s r p consumer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching w s r p consumer, or <code>null</code> if a matching w s r p consumer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WSRPConsumer fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<WSRPConsumer> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last w s r p consumer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching w s r p consumer
	 * @throws com.liferay.wsrp.NoSuchConsumerException if a matching w s r p consumer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WSRPConsumer findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchConsumerException, SystemException {
		WSRPConsumer wsrpConsumer = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (wsrpConsumer != null) {
			return wsrpConsumer;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConsumerException(msg.toString());
	}

	/**
	 * Returns the last w s r p consumer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching w s r p consumer, or <code>null</code> if a matching w s r p consumer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WSRPConsumer fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid_C(uuid, companyId);

		List<WSRPConsumer> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the w s r p consumers before and after the current w s r p consumer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param wsrpConsumerId the primary key of the current w s r p consumer
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next w s r p consumer
	 * @throws com.liferay.wsrp.NoSuchConsumerException if a w s r p consumer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WSRPConsumer[] findByUuid_C_PrevAndNext(long wsrpConsumerId,
		String uuid, long companyId, OrderByComparator orderByComparator)
		throws NoSuchConsumerException, SystemException {
		WSRPConsumer wsrpConsumer = findByPrimaryKey(wsrpConsumerId);

		Session session = null;

		try {
			session = openSession();

			WSRPConsumer[] array = new WSRPConsumerImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, wsrpConsumer, uuid,
					companyId, orderByComparator, true);

			array[1] = wsrpConsumer;

			array[2] = getByUuid_C_PrevAndNext(session, wsrpConsumer, uuid,
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

	protected WSRPConsumer getByUuid_C_PrevAndNext(Session session,
		WSRPConsumer wsrpConsumer, String uuid, long companyId,
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

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else {
			if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
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

			String[] orderByFields = orderByComparator.getOrderByFields();

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

		if (uuid != null) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(wsrpConsumer);

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

	/**
	 * Returns all the w s r p consumers where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching w s r p consumers
	 * @throws SystemException if a system exception occurred
	 */
	public List<WSRPConsumer> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the w s r p consumers where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of w s r p consumers
	 * @param end the upper bound of the range of w s r p consumers (not inclusive)
	 * @return the range of matching w s r p consumers
	 * @throws SystemException if a system exception occurred
	 */
	public List<WSRPConsumer> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the w s r p consumers where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of w s r p consumers
	 * @param end the upper bound of the range of w s r p consumers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching w s r p consumers
	 * @throws SystemException if a system exception occurred
	 */
	public List<WSRPConsumer> findByCompanyId(long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId, start, end, orderByComparator };
		}

		List<WSRPConsumer> list = (List<WSRPConsumer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WSRPConsumer wsrpConsumer : list) {
				if ((companyId != wsrpConsumer.getCompanyId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
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

			Session session = null;

			try {
				session = openSession();

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
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first w s r p consumer in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching w s r p consumer
	 * @throws com.liferay.wsrp.NoSuchConsumerException if a matching w s r p consumer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WSRPConsumer findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchConsumerException, SystemException {
		WSRPConsumer wsrpConsumer = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (wsrpConsumer != null) {
			return wsrpConsumer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConsumerException(msg.toString());
	}

	/**
	 * Returns the first w s r p consumer in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching w s r p consumer, or <code>null</code> if a matching w s r p consumer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WSRPConsumer fetchByCompanyId_First(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<WSRPConsumer> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last w s r p consumer in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching w s r p consumer
	 * @throws com.liferay.wsrp.NoSuchConsumerException if a matching w s r p consumer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WSRPConsumer findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchConsumerException, SystemException {
		WSRPConsumer wsrpConsumer = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (wsrpConsumer != null) {
			return wsrpConsumer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConsumerException(msg.toString());
	}

	/**
	 * Returns the last w s r p consumer in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching w s r p consumer, or <code>null</code> if a matching w s r p consumer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WSRPConsumer fetchByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCompanyId(companyId);

		List<WSRPConsumer> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the w s r p consumers before and after the current w s r p consumer in the ordered set where companyId = &#63;.
	 *
	 * @param wsrpConsumerId the primary key of the current w s r p consumer
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next w s r p consumer
	 * @throws com.liferay.wsrp.NoSuchConsumerException if a w s r p consumer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
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
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
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

			String[] orderByFields = orderByComparator.getOrderByFields();

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
			Object[] values = orderByComparator.getOrderByConditionValues(wsrpConsumer);

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

	/**
	 * Returns all the w s r p consumers.
	 *
	 * @return the w s r p consumers
	 * @throws SystemException if a system exception occurred
	 */
	public List<WSRPConsumer> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the w s r p consumers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of w s r p consumers
	 * @param end the upper bound of the range of w s r p consumers (not inclusive)
	 * @return the range of w s r p consumers
	 * @throws SystemException if a system exception occurred
	 */
	public List<WSRPConsumer> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the w s r p consumers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of w s r p consumers
	 * @param end the upper bound of the range of w s r p consumers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of w s r p consumers
	 * @throws SystemException if a system exception occurred
	 */
	public List<WSRPConsumer> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<WSRPConsumer> list = (List<WSRPConsumer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
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

			Session session = null;

			try {
				session = openSession();

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
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the w s r p consumers where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (WSRPConsumer wsrpConsumer : findByUuid(uuid)) {
			remove(wsrpConsumer);
		}
	}

	/**
	 * Removes all the w s r p consumers where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid_C(String uuid, long companyId)
		throws SystemException {
		for (WSRPConsumer wsrpConsumer : findByUuid_C(uuid, companyId)) {
			remove(wsrpConsumer);
		}
	}

	/**
	 * Removes all the w s r p consumers where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (WSRPConsumer wsrpConsumer : findByCompanyId(companyId)) {
			remove(wsrpConsumer);
		}
	}

	/**
	 * Removes all the w s r p consumers from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (WSRPConsumer wsrpConsumer : findAll()) {
			remove(wsrpConsumer);
		}
	}

	/**
	 * Returns the number of w s r p consumers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching w s r p consumers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WSRPCONSUMER_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of w s r p consumers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching w s r p consumers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid_C(String uuid, long companyId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WSRPCONSUMER_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_C_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_C_UUID_2);
				}
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_C,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of w s r p consumers where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching w s r p consumers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WSRPCONSUMER_WHERE);

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
	 * Returns the number of w s r p consumers.
	 *
	 * @return the number of w s r p consumers
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the w s r p consumer persistence.
	 */
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

	public void destroy() {
		EntityCacheUtil.removeCache(WSRPConsumerImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = WSRPConsumerPersistence.class)
	protected WSRPConsumerPersistence wsrpConsumerPersistence;
	@BeanReference(type = WSRPConsumerPortletPersistence.class)
	protected WSRPConsumerPortletPersistence wsrpConsumerPortletPersistence;
	@BeanReference(type = WSRPProducerPersistence.class)
	protected WSRPProducerPersistence wsrpProducerPersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_WSRPCONSUMER = "SELECT wsrpConsumer FROM WSRPConsumer wsrpConsumer";
	private static final String _SQL_SELECT_WSRPCONSUMER_WHERE = "SELECT wsrpConsumer FROM WSRPConsumer wsrpConsumer WHERE ";
	private static final String _SQL_COUNT_WSRPCONSUMER = "SELECT COUNT(wsrpConsumer) FROM WSRPConsumer wsrpConsumer";
	private static final String _SQL_COUNT_WSRPCONSUMER_WHERE = "SELECT COUNT(wsrpConsumer) FROM WSRPConsumer wsrpConsumer WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "wsrpConsumer.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "wsrpConsumer.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(wsrpConsumer.uuid IS NULL OR wsrpConsumer.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "wsrpConsumer.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "wsrpConsumer.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(wsrpConsumer.uuid IS NULL OR wsrpConsumer.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "wsrpConsumer.companyId = ?";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "wsrpConsumer.companyId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "wsrpConsumer.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WSRPConsumer exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No WSRPConsumer exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(WSRPConsumerPersistenceImpl.class);
	private static WSRPConsumer _nullWSRPConsumer = new WSRPConsumerImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<WSRPConsumer> toCacheModel() {
				return _nullWSRPConsumerCacheModel;
			}
		};

	private static CacheModel<WSRPConsumer> _nullWSRPConsumerCacheModel = new CacheModel<WSRPConsumer>() {
			public WSRPConsumer toEntityModel() {
				return _nullWSRPConsumer;
			}
		};
}