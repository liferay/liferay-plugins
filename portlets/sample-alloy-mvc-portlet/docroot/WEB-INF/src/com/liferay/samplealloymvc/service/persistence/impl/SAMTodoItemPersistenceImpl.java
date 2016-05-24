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

package com.liferay.samplealloymvc.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.liferay.samplealloymvc.exception.NoSuchTodoItemException;
import com.liferay.samplealloymvc.model.SAMTodoItem;
import com.liferay.samplealloymvc.model.impl.SAMTodoItemImpl;
import com.liferay.samplealloymvc.model.impl.SAMTodoItemModelImpl;
import com.liferay.samplealloymvc.service.persistence.SAMTodoItemPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the s a m todo item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SAMTodoItemPersistence
 * @see com.liferay.samplealloymvc.service.persistence.SAMTodoItemUtil
 * @generated
 */
@ProviderType
public class SAMTodoItemPersistenceImpl extends BasePersistenceImpl<SAMTodoItem>
	implements SAMTodoItemPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SAMTodoItemUtil} to access the s a m todo item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SAMTodoItemImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SAMTodoItemModelImpl.ENTITY_CACHE_ENABLED,
			SAMTodoItemModelImpl.FINDER_CACHE_ENABLED, SAMTodoItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SAMTodoItemModelImpl.ENTITY_CACHE_ENABLED,
			SAMTodoItemModelImpl.FINDER_CACHE_ENABLED, SAMTodoItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SAMTodoItemModelImpl.ENTITY_CACHE_ENABLED,
			SAMTodoItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public SAMTodoItemPersistenceImpl() {
		setModelClass(SAMTodoItem.class);
	}

	/**
	 * Caches the s a m todo item in the entity cache if it is enabled.
	 *
	 * @param samTodoItem the s a m todo item
	 */
	@Override
	public void cacheResult(SAMTodoItem samTodoItem) {
		entityCache.putResult(SAMTodoItemModelImpl.ENTITY_CACHE_ENABLED,
			SAMTodoItemImpl.class, samTodoItem.getPrimaryKey(), samTodoItem);

		samTodoItem.resetOriginalValues();
	}

	/**
	 * Caches the s a m todo items in the entity cache if it is enabled.
	 *
	 * @param samTodoItems the s a m todo items
	 */
	@Override
	public void cacheResult(List<SAMTodoItem> samTodoItems) {
		for (SAMTodoItem samTodoItem : samTodoItems) {
			if (entityCache.getResult(
						SAMTodoItemModelImpl.ENTITY_CACHE_ENABLED,
						SAMTodoItemImpl.class, samTodoItem.getPrimaryKey()) == null) {
				cacheResult(samTodoItem);
			}
			else {
				samTodoItem.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all s a m todo items.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SAMTodoItemImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the s a m todo item.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SAMTodoItem samTodoItem) {
		entityCache.removeResult(SAMTodoItemModelImpl.ENTITY_CACHE_ENABLED,
			SAMTodoItemImpl.class, samTodoItem.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SAMTodoItem> samTodoItems) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SAMTodoItem samTodoItem : samTodoItems) {
			entityCache.removeResult(SAMTodoItemModelImpl.ENTITY_CACHE_ENABLED,
				SAMTodoItemImpl.class, samTodoItem.getPrimaryKey());
		}
	}

	/**
	 * Creates a new s a m todo item with the primary key. Does not add the s a m todo item to the database.
	 *
	 * @param samTodoItemId the primary key for the new s a m todo item
	 * @return the new s a m todo item
	 */
	@Override
	public SAMTodoItem create(long samTodoItemId) {
		SAMTodoItem samTodoItem = new SAMTodoItemImpl();

		samTodoItem.setNew(true);
		samTodoItem.setPrimaryKey(samTodoItemId);

		samTodoItem.setCompanyId(companyProvider.getCompanyId());

		return samTodoItem;
	}

	/**
	 * Removes the s a m todo item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param samTodoItemId the primary key of the s a m todo item
	 * @return the s a m todo item that was removed
	 * @throws NoSuchTodoItemException if a s a m todo item with the primary key could not be found
	 */
	@Override
	public SAMTodoItem remove(long samTodoItemId)
		throws NoSuchTodoItemException {
		return remove((Serializable)samTodoItemId);
	}

	/**
	 * Removes the s a m todo item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the s a m todo item
	 * @return the s a m todo item that was removed
	 * @throws NoSuchTodoItemException if a s a m todo item with the primary key could not be found
	 */
	@Override
	public SAMTodoItem remove(Serializable primaryKey)
		throws NoSuchTodoItemException {
		Session session = null;

		try {
			session = openSession();

			SAMTodoItem samTodoItem = (SAMTodoItem)session.get(SAMTodoItemImpl.class,
					primaryKey);

			if (samTodoItem == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTodoItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(samTodoItem);
		}
		catch (NoSuchTodoItemException nsee) {
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
	protected SAMTodoItem removeImpl(SAMTodoItem samTodoItem) {
		samTodoItem = toUnwrappedModel(samTodoItem);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(samTodoItem)) {
				samTodoItem = (SAMTodoItem)session.get(SAMTodoItemImpl.class,
						samTodoItem.getPrimaryKeyObj());
			}

			if (samTodoItem != null) {
				session.delete(samTodoItem);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (samTodoItem != null) {
			clearCache(samTodoItem);
		}

		return samTodoItem;
	}

	@Override
	public SAMTodoItem updateImpl(SAMTodoItem samTodoItem) {
		samTodoItem = toUnwrappedModel(samTodoItem);

		boolean isNew = samTodoItem.isNew();

		SAMTodoItemModelImpl samTodoItemModelImpl = (SAMTodoItemModelImpl)samTodoItem;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (samTodoItem.getCreateDate() == null)) {
			if (serviceContext == null) {
				samTodoItem.setCreateDate(now);
			}
			else {
				samTodoItem.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!samTodoItemModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				samTodoItem.setModifiedDate(now);
			}
			else {
				samTodoItem.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (samTodoItem.isNew()) {
				session.save(samTodoItem);

				samTodoItem.setNew(false);
			}
			else {
				samTodoItem = (SAMTodoItem)session.merge(samTodoItem);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		entityCache.putResult(SAMTodoItemModelImpl.ENTITY_CACHE_ENABLED,
			SAMTodoItemImpl.class, samTodoItem.getPrimaryKey(), samTodoItem,
			false);

		samTodoItem.resetOriginalValues();

		return samTodoItem;
	}

	protected SAMTodoItem toUnwrappedModel(SAMTodoItem samTodoItem) {
		if (samTodoItem instanceof SAMTodoItemImpl) {
			return samTodoItem;
		}

		SAMTodoItemImpl samTodoItemImpl = new SAMTodoItemImpl();

		samTodoItemImpl.setNew(samTodoItem.isNew());
		samTodoItemImpl.setPrimaryKey(samTodoItem.getPrimaryKey());

		samTodoItemImpl.setSamTodoItemId(samTodoItem.getSamTodoItemId());
		samTodoItemImpl.setCompanyId(samTodoItem.getCompanyId());
		samTodoItemImpl.setUserId(samTodoItem.getUserId());
		samTodoItemImpl.setUserName(samTodoItem.getUserName());
		samTodoItemImpl.setCreateDate(samTodoItem.getCreateDate());
		samTodoItemImpl.setModifiedDate(samTodoItem.getModifiedDate());
		samTodoItemImpl.setSamTodoListId(samTodoItem.getSamTodoListId());
		samTodoItemImpl.setDescription(samTodoItem.getDescription());
		samTodoItemImpl.setPriority(samTodoItem.getPriority());
		samTodoItemImpl.setStatus(samTodoItem.getStatus());

		return samTodoItemImpl;
	}

	/**
	 * Returns the s a m todo item with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the s a m todo item
	 * @return the s a m todo item
	 * @throws NoSuchTodoItemException if a s a m todo item with the primary key could not be found
	 */
	@Override
	public SAMTodoItem findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTodoItemException {
		SAMTodoItem samTodoItem = fetchByPrimaryKey(primaryKey);

		if (samTodoItem == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTodoItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return samTodoItem;
	}

	/**
	 * Returns the s a m todo item with the primary key or throws a {@link NoSuchTodoItemException} if it could not be found.
	 *
	 * @param samTodoItemId the primary key of the s a m todo item
	 * @return the s a m todo item
	 * @throws NoSuchTodoItemException if a s a m todo item with the primary key could not be found
	 */
	@Override
	public SAMTodoItem findByPrimaryKey(long samTodoItemId)
		throws NoSuchTodoItemException {
		return findByPrimaryKey((Serializable)samTodoItemId);
	}

	/**
	 * Returns the s a m todo item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the s a m todo item
	 * @return the s a m todo item, or <code>null</code> if a s a m todo item with the primary key could not be found
	 */
	@Override
	public SAMTodoItem fetchByPrimaryKey(Serializable primaryKey) {
		SAMTodoItem samTodoItem = (SAMTodoItem)entityCache.getResult(SAMTodoItemModelImpl.ENTITY_CACHE_ENABLED,
				SAMTodoItemImpl.class, primaryKey);

		if (samTodoItem == _nullSAMTodoItem) {
			return null;
		}

		if (samTodoItem == null) {
			Session session = null;

			try {
				session = openSession();

				samTodoItem = (SAMTodoItem)session.get(SAMTodoItemImpl.class,
						primaryKey);

				if (samTodoItem != null) {
					cacheResult(samTodoItem);
				}
				else {
					entityCache.putResult(SAMTodoItemModelImpl.ENTITY_CACHE_ENABLED,
						SAMTodoItemImpl.class, primaryKey, _nullSAMTodoItem);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SAMTodoItemModelImpl.ENTITY_CACHE_ENABLED,
					SAMTodoItemImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return samTodoItem;
	}

	/**
	 * Returns the s a m todo item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param samTodoItemId the primary key of the s a m todo item
	 * @return the s a m todo item, or <code>null</code> if a s a m todo item with the primary key could not be found
	 */
	@Override
	public SAMTodoItem fetchByPrimaryKey(long samTodoItemId) {
		return fetchByPrimaryKey((Serializable)samTodoItemId);
	}

	@Override
	public Map<Serializable, SAMTodoItem> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SAMTodoItem> map = new HashMap<Serializable, SAMTodoItem>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SAMTodoItem samTodoItem = fetchByPrimaryKey(primaryKey);

			if (samTodoItem != null) {
				map.put(primaryKey, samTodoItem);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			SAMTodoItem samTodoItem = (SAMTodoItem)entityCache.getResult(SAMTodoItemModelImpl.ENTITY_CACHE_ENABLED,
					SAMTodoItemImpl.class, primaryKey);

			if (samTodoItem == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, samTodoItem);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SAMTODOITEM_WHERE_PKS_IN);

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

			for (SAMTodoItem samTodoItem : (List<SAMTodoItem>)q.list()) {
				map.put(samTodoItem.getPrimaryKeyObj(), samTodoItem);

				cacheResult(samTodoItem);

				uncachedPrimaryKeys.remove(samTodoItem.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SAMTodoItemModelImpl.ENTITY_CACHE_ENABLED,
					SAMTodoItemImpl.class, primaryKey, _nullSAMTodoItem);
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
	 * Returns all the s a m todo items.
	 *
	 * @return the s a m todo items
	 */
	@Override
	public List<SAMTodoItem> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the s a m todo items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SAMTodoItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of s a m todo items
	 * @param end the upper bound of the range of s a m todo items (not inclusive)
	 * @return the range of s a m todo items
	 */
	@Override
	public List<SAMTodoItem> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the s a m todo items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SAMTodoItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of s a m todo items
	 * @param end the upper bound of the range of s a m todo items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of s a m todo items
	 */
	@Override
	public List<SAMTodoItem> findAll(int start, int end,
		OrderByComparator<SAMTodoItem> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the s a m todo items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SAMTodoItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of s a m todo items
	 * @param end the upper bound of the range of s a m todo items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of s a m todo items
	 */
	@Override
	public List<SAMTodoItem> findAll(int start, int end,
		OrderByComparator<SAMTodoItem> orderByComparator,
		boolean retrieveFromCache) {
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

		List<SAMTodoItem> list = null;

		if (retrieveFromCache) {
			list = (List<SAMTodoItem>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SAMTODOITEM);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SAMTODOITEM;

				if (pagination) {
					sql = sql.concat(SAMTodoItemModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SAMTodoItem>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SAMTodoItem>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the s a m todo items from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SAMTodoItem samTodoItem : findAll()) {
			remove(samTodoItem);
		}
	}

	/**
	 * Returns the number of s a m todo items.
	 *
	 * @return the number of s a m todo items
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SAMTODOITEM);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SAMTodoItemModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the s a m todo item persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SAMTodoItemImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_SAMTODOITEM = "SELECT samTodoItem FROM SAMTodoItem samTodoItem";
	private static final String _SQL_SELECT_SAMTODOITEM_WHERE_PKS_IN = "SELECT samTodoItem FROM SAMTodoItem samTodoItem WHERE samTodoItemId IN (";
	private static final String _SQL_COUNT_SAMTODOITEM = "SELECT COUNT(samTodoItem) FROM SAMTodoItem samTodoItem";
	private static final String _ORDER_BY_ENTITY_ALIAS = "samTodoItem.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SAMTodoItem exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(SAMTodoItemPersistenceImpl.class);
	private static final SAMTodoItem _nullSAMTodoItem = new SAMTodoItemImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SAMTodoItem> toCacheModel() {
				return _nullSAMTodoItemCacheModel;
			}
		};

	private static final CacheModel<SAMTodoItem> _nullSAMTodoItemCacheModel = new CacheModel<SAMTodoItem>() {
			@Override
			public SAMTodoItem toEntityModel() {
				return _nullSAMTodoItem;
			}
		};
}