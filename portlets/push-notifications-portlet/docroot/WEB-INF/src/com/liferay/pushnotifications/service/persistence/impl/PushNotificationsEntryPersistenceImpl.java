/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.pushnotifications.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.pushnotifications.NoSuchEntryException;
import com.liferay.pushnotifications.model.PushNotificationsEntry;
import com.liferay.pushnotifications.model.impl.PushNotificationsEntryImpl;
import com.liferay.pushnotifications.model.impl.PushNotificationsEntryModelImpl;
import com.liferay.pushnotifications.service.persistence.PushNotificationsEntryPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the push notifications entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Silvio Santos
 * @see PushNotificationsEntryPersistence
 * @see PushNotificationsEntryUtil
 * @generated
 */
@ProviderType
public class PushNotificationsEntryPersistenceImpl extends BasePersistenceImpl<PushNotificationsEntry>
	implements PushNotificationsEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PushNotificationsEntryUtil} to access the push notifications entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PushNotificationsEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsEntryModelImpl.FINDER_CACHE_ENABLED,
			PushNotificationsEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsEntryModelImpl.FINDER_CACHE_ENABLED,
			PushNotificationsEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public PushNotificationsEntryPersistenceImpl() {
		setModelClass(PushNotificationsEntry.class);
	}

	/**
	 * Caches the push notifications entry in the entity cache if it is enabled.
	 *
	 * @param pushNotificationsEntry the push notifications entry
	 */
	@Override
	public void cacheResult(PushNotificationsEntry pushNotificationsEntry) {
		EntityCacheUtil.putResult(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsEntryImpl.class,
			pushNotificationsEntry.getPrimaryKey(), pushNotificationsEntry);

		pushNotificationsEntry.resetOriginalValues();
	}

	/**
	 * Caches the push notifications entries in the entity cache if it is enabled.
	 *
	 * @param pushNotificationsEntries the push notifications entries
	 */
	@Override
	public void cacheResult(
		List<PushNotificationsEntry> pushNotificationsEntries) {
		for (PushNotificationsEntry pushNotificationsEntry : pushNotificationsEntries) {
			if (EntityCacheUtil.getResult(
						PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
						PushNotificationsEntryImpl.class,
						pushNotificationsEntry.getPrimaryKey()) == null) {
				cacheResult(pushNotificationsEntry);
			}
			else {
				pushNotificationsEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all push notifications entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(PushNotificationsEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(PushNotificationsEntryImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the push notifications entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PushNotificationsEntry pushNotificationsEntry) {
		EntityCacheUtil.removeResult(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsEntryImpl.class,
			pushNotificationsEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<PushNotificationsEntry> pushNotificationsEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PushNotificationsEntry pushNotificationsEntry : pushNotificationsEntries) {
			EntityCacheUtil.removeResult(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
				PushNotificationsEntryImpl.class,
				pushNotificationsEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new push notifications entry with the primary key. Does not add the push notifications entry to the database.
	 *
	 * @param pushNotificationsEntryId the primary key for the new push notifications entry
	 * @return the new push notifications entry
	 */
	@Override
	public PushNotificationsEntry create(long pushNotificationsEntryId) {
		PushNotificationsEntry pushNotificationsEntry = new PushNotificationsEntryImpl();

		pushNotificationsEntry.setNew(true);
		pushNotificationsEntry.setPrimaryKey(pushNotificationsEntryId);

		return pushNotificationsEntry;
	}

	/**
	 * Removes the push notifications entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param pushNotificationsEntryId the primary key of the push notifications entry
	 * @return the push notifications entry that was removed
	 * @throws com.liferay.pushnotifications.NoSuchEntryException if a push notifications entry with the primary key could not be found
	 */
	@Override
	public PushNotificationsEntry remove(long pushNotificationsEntryId)
		throws NoSuchEntryException {
		return remove((Serializable)pushNotificationsEntryId);
	}

	/**
	 * Removes the push notifications entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the push notifications entry
	 * @return the push notifications entry that was removed
	 * @throws com.liferay.pushnotifications.NoSuchEntryException if a push notifications entry with the primary key could not be found
	 */
	@Override
	public PushNotificationsEntry remove(Serializable primaryKey)
		throws NoSuchEntryException {
		Session session = null;

		try {
			session = openSession();

			PushNotificationsEntry pushNotificationsEntry = (PushNotificationsEntry)session.get(PushNotificationsEntryImpl.class,
					primaryKey);

			if (pushNotificationsEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(pushNotificationsEntry);
		}
		catch (NoSuchEntryException nsee) {
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
	protected PushNotificationsEntry removeImpl(
		PushNotificationsEntry pushNotificationsEntry) {
		pushNotificationsEntry = toUnwrappedModel(pushNotificationsEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(pushNotificationsEntry)) {
				pushNotificationsEntry = (PushNotificationsEntry)session.get(PushNotificationsEntryImpl.class,
						pushNotificationsEntry.getPrimaryKeyObj());
			}

			if (pushNotificationsEntry != null) {
				session.delete(pushNotificationsEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (pushNotificationsEntry != null) {
			clearCache(pushNotificationsEntry);
		}

		return pushNotificationsEntry;
	}

	@Override
	public PushNotificationsEntry updateImpl(
		com.liferay.pushnotifications.model.PushNotificationsEntry pushNotificationsEntry) {
		pushNotificationsEntry = toUnwrappedModel(pushNotificationsEntry);

		boolean isNew = pushNotificationsEntry.isNew();

		Session session = null;

		try {
			session = openSession();

			if (pushNotificationsEntry.isNew()) {
				session.save(pushNotificationsEntry);

				pushNotificationsEntry.setNew(false);
			}
			else {
				session.merge(pushNotificationsEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsEntryImpl.class,
			pushNotificationsEntry.getPrimaryKey(), pushNotificationsEntry,
			false);

		pushNotificationsEntry.resetOriginalValues();

		return pushNotificationsEntry;
	}

	protected PushNotificationsEntry toUnwrappedModel(
		PushNotificationsEntry pushNotificationsEntry) {
		if (pushNotificationsEntry instanceof PushNotificationsEntryImpl) {
			return pushNotificationsEntry;
		}

		PushNotificationsEntryImpl pushNotificationsEntryImpl = new PushNotificationsEntryImpl();

		pushNotificationsEntryImpl.setNew(pushNotificationsEntry.isNew());
		pushNotificationsEntryImpl.setPrimaryKey(pushNotificationsEntry.getPrimaryKey());

		pushNotificationsEntryImpl.setPushNotificationsEntryId(pushNotificationsEntry.getPushNotificationsEntryId());
		pushNotificationsEntryImpl.setUserId(pushNotificationsEntry.getUserId());
		pushNotificationsEntryImpl.setCreateDate(pushNotificationsEntry.getCreateDate());
		pushNotificationsEntryImpl.setParentPushNotificationsEntryId(pushNotificationsEntry.getParentPushNotificationsEntryId());
		pushNotificationsEntryImpl.setPayload(pushNotificationsEntry.getPayload());

		return pushNotificationsEntryImpl;
	}

	/**
	 * Returns the push notifications entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the push notifications entry
	 * @return the push notifications entry
	 * @throws com.liferay.pushnotifications.NoSuchEntryException if a push notifications entry with the primary key could not be found
	 */
	@Override
	public PushNotificationsEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEntryException {
		PushNotificationsEntry pushNotificationsEntry = fetchByPrimaryKey(primaryKey);

		if (pushNotificationsEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return pushNotificationsEntry;
	}

	/**
	 * Returns the push notifications entry with the primary key or throws a {@link com.liferay.pushnotifications.NoSuchEntryException} if it could not be found.
	 *
	 * @param pushNotificationsEntryId the primary key of the push notifications entry
	 * @return the push notifications entry
	 * @throws com.liferay.pushnotifications.NoSuchEntryException if a push notifications entry with the primary key could not be found
	 */
	@Override
	public PushNotificationsEntry findByPrimaryKey(
		long pushNotificationsEntryId) throws NoSuchEntryException {
		return findByPrimaryKey((Serializable)pushNotificationsEntryId);
	}

	/**
	 * Returns the push notifications entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the push notifications entry
	 * @return the push notifications entry, or <code>null</code> if a push notifications entry with the primary key could not be found
	 */
	@Override
	public PushNotificationsEntry fetchByPrimaryKey(Serializable primaryKey) {
		PushNotificationsEntry pushNotificationsEntry = (PushNotificationsEntry)EntityCacheUtil.getResult(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
				PushNotificationsEntryImpl.class, primaryKey);

		if (pushNotificationsEntry == _nullPushNotificationsEntry) {
			return null;
		}

		if (pushNotificationsEntry == null) {
			Session session = null;

			try {
				session = openSession();

				pushNotificationsEntry = (PushNotificationsEntry)session.get(PushNotificationsEntryImpl.class,
						primaryKey);

				if (pushNotificationsEntry != null) {
					cacheResult(pushNotificationsEntry);
				}
				else {
					EntityCacheUtil.putResult(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
						PushNotificationsEntryImpl.class, primaryKey,
						_nullPushNotificationsEntry);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
					PushNotificationsEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return pushNotificationsEntry;
	}

	/**
	 * Returns the push notifications entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param pushNotificationsEntryId the primary key of the push notifications entry
	 * @return the push notifications entry, or <code>null</code> if a push notifications entry with the primary key could not be found
	 */
	@Override
	public PushNotificationsEntry fetchByPrimaryKey(
		long pushNotificationsEntryId) {
		return fetchByPrimaryKey((Serializable)pushNotificationsEntryId);
	}

	@Override
	public Map<Serializable, PushNotificationsEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PushNotificationsEntry> map = new HashMap<Serializable, PushNotificationsEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			PushNotificationsEntry pushNotificationsEntry = fetchByPrimaryKey(primaryKey);

			if (pushNotificationsEntry != null) {
				map.put(primaryKey, pushNotificationsEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			PushNotificationsEntry pushNotificationsEntry = (PushNotificationsEntry)EntityCacheUtil.getResult(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
					PushNotificationsEntryImpl.class, primaryKey);

			if (pushNotificationsEntry == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, pushNotificationsEntry);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PUSHNOTIFICATIONSENTRY_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (PushNotificationsEntry pushNotificationsEntry : (List<PushNotificationsEntry>)q.list()) {
				map.put(pushNotificationsEntry.getPrimaryKeyObj(),
					pushNotificationsEntry);

				cacheResult(pushNotificationsEntry);

				uncachedPrimaryKeys.remove(pushNotificationsEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
					PushNotificationsEntryImpl.class, primaryKey,
					_nullPushNotificationsEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the push notifications entries.
	 *
	 * @return the push notifications entries
	 */
	@Override
	public List<PushNotificationsEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the push notifications entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.pushnotifications.model.impl.PushNotificationsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of push notifications entries
	 * @param end the upper bound of the range of push notifications entries (not inclusive)
	 * @return the range of push notifications entries
	 */
	@Override
	public List<PushNotificationsEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the push notifications entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.pushnotifications.model.impl.PushNotificationsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of push notifications entries
	 * @param end the upper bound of the range of push notifications entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of push notifications entries
	 */
	@Override
	public List<PushNotificationsEntry> findAll(int start, int end,
		OrderByComparator<PushNotificationsEntry> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<PushNotificationsEntry> list = (List<PushNotificationsEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_PUSHNOTIFICATIONSENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PUSHNOTIFICATIONSENTRY;

				if (pagination) {
					sql = sql.concat(PushNotificationsEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PushNotificationsEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PushNotificationsEntry>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the push notifications entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PushNotificationsEntry pushNotificationsEntry : findAll()) {
			remove(pushNotificationsEntry);
		}
	}

	/**
	 * Returns the number of push notifications entries.
	 *
	 * @return the number of push notifications entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PUSHNOTIFICATIONSENTRY);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the push notifications entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(PushNotificationsEntryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_PUSHNOTIFICATIONSENTRY = "SELECT pushNotificationsEntry FROM PushNotificationsEntry pushNotificationsEntry";
	private static final String _SQL_SELECT_PUSHNOTIFICATIONSENTRY_WHERE_PKS_IN = "SELECT pushNotificationsEntry FROM PushNotificationsEntry pushNotificationsEntry WHERE pushNotificationsEntryId IN (";
	private static final String _SQL_COUNT_PUSHNOTIFICATIONSENTRY = "SELECT COUNT(pushNotificationsEntry) FROM PushNotificationsEntry pushNotificationsEntry";
	private static final String _ORDER_BY_ENTITY_ALIAS = "pushNotificationsEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PushNotificationsEntry exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static final Log _log = LogFactoryUtil.getLog(PushNotificationsEntryPersistenceImpl.class);
	private static final PushNotificationsEntry _nullPushNotificationsEntry = new PushNotificationsEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<PushNotificationsEntry> toCacheModel() {
				return _nullPushNotificationsEntryCacheModel;
			}
		};

	private static final CacheModel<PushNotificationsEntry> _nullPushNotificationsEntryCacheModel =
		new CacheModel<PushNotificationsEntry>() {
			@Override
			public PushNotificationsEntry toEntityModel() {
				return _nullPushNotificationsEntry;
			}
		};
}