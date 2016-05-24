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

import com.liferay.samplealloymvc.exception.NoSuchTodoListException;
import com.liferay.samplealloymvc.model.SAMTodoList;
import com.liferay.samplealloymvc.model.impl.SAMTodoListImpl;
import com.liferay.samplealloymvc.model.impl.SAMTodoListModelImpl;
import com.liferay.samplealloymvc.service.persistence.SAMTodoListPersistence;

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
 * The persistence implementation for the s a m todo list service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SAMTodoListPersistence
 * @see com.liferay.samplealloymvc.service.persistence.SAMTodoListUtil
 * @generated
 */
@ProviderType
public class SAMTodoListPersistenceImpl extends BasePersistenceImpl<SAMTodoList>
	implements SAMTodoListPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SAMTodoListUtil} to access the s a m todo list persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SAMTodoListImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SAMTodoListModelImpl.ENTITY_CACHE_ENABLED,
			SAMTodoListModelImpl.FINDER_CACHE_ENABLED, SAMTodoListImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SAMTodoListModelImpl.ENTITY_CACHE_ENABLED,
			SAMTodoListModelImpl.FINDER_CACHE_ENABLED, SAMTodoListImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SAMTodoListModelImpl.ENTITY_CACHE_ENABLED,
			SAMTodoListModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public SAMTodoListPersistenceImpl() {
		setModelClass(SAMTodoList.class);
	}

	/**
	 * Caches the s a m todo list in the entity cache if it is enabled.
	 *
	 * @param samTodoList the s a m todo list
	 */
	@Override
	public void cacheResult(SAMTodoList samTodoList) {
		entityCache.putResult(SAMTodoListModelImpl.ENTITY_CACHE_ENABLED,
			SAMTodoListImpl.class, samTodoList.getPrimaryKey(), samTodoList);

		samTodoList.resetOriginalValues();
	}

	/**
	 * Caches the s a m todo lists in the entity cache if it is enabled.
	 *
	 * @param samTodoLists the s a m todo lists
	 */
	@Override
	public void cacheResult(List<SAMTodoList> samTodoLists) {
		for (SAMTodoList samTodoList : samTodoLists) {
			if (entityCache.getResult(
						SAMTodoListModelImpl.ENTITY_CACHE_ENABLED,
						SAMTodoListImpl.class, samTodoList.getPrimaryKey()) == null) {
				cacheResult(samTodoList);
			}
			else {
				samTodoList.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all s a m todo lists.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SAMTodoListImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the s a m todo list.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SAMTodoList samTodoList) {
		entityCache.removeResult(SAMTodoListModelImpl.ENTITY_CACHE_ENABLED,
			SAMTodoListImpl.class, samTodoList.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SAMTodoList> samTodoLists) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SAMTodoList samTodoList : samTodoLists) {
			entityCache.removeResult(SAMTodoListModelImpl.ENTITY_CACHE_ENABLED,
				SAMTodoListImpl.class, samTodoList.getPrimaryKey());
		}
	}

	/**
	 * Creates a new s a m todo list with the primary key. Does not add the s a m todo list to the database.
	 *
	 * @param samTodoListId the primary key for the new s a m todo list
	 * @return the new s a m todo list
	 */
	@Override
	public SAMTodoList create(long samTodoListId) {
		SAMTodoList samTodoList = new SAMTodoListImpl();

		samTodoList.setNew(true);
		samTodoList.setPrimaryKey(samTodoListId);

		samTodoList.setCompanyId(companyProvider.getCompanyId());

		return samTodoList;
	}

	/**
	 * Removes the s a m todo list with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param samTodoListId the primary key of the s a m todo list
	 * @return the s a m todo list that was removed
	 * @throws NoSuchTodoListException if a s a m todo list with the primary key could not be found
	 */
	@Override
	public SAMTodoList remove(long samTodoListId)
		throws NoSuchTodoListException {
		return remove((Serializable)samTodoListId);
	}

	/**
	 * Removes the s a m todo list with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the s a m todo list
	 * @return the s a m todo list that was removed
	 * @throws NoSuchTodoListException if a s a m todo list with the primary key could not be found
	 */
	@Override
	public SAMTodoList remove(Serializable primaryKey)
		throws NoSuchTodoListException {
		Session session = null;

		try {
			session = openSession();

			SAMTodoList samTodoList = (SAMTodoList)session.get(SAMTodoListImpl.class,
					primaryKey);

			if (samTodoList == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTodoListException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(samTodoList);
		}
		catch (NoSuchTodoListException nsee) {
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
	protected SAMTodoList removeImpl(SAMTodoList samTodoList) {
		samTodoList = toUnwrappedModel(samTodoList);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(samTodoList)) {
				samTodoList = (SAMTodoList)session.get(SAMTodoListImpl.class,
						samTodoList.getPrimaryKeyObj());
			}

			if (samTodoList != null) {
				session.delete(samTodoList);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (samTodoList != null) {
			clearCache(samTodoList);
		}

		return samTodoList;
	}

	@Override
	public SAMTodoList updateImpl(SAMTodoList samTodoList) {
		samTodoList = toUnwrappedModel(samTodoList);

		boolean isNew = samTodoList.isNew();

		SAMTodoListModelImpl samTodoListModelImpl = (SAMTodoListModelImpl)samTodoList;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (samTodoList.getCreateDate() == null)) {
			if (serviceContext == null) {
				samTodoList.setCreateDate(now);
			}
			else {
				samTodoList.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!samTodoListModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				samTodoList.setModifiedDate(now);
			}
			else {
				samTodoList.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (samTodoList.isNew()) {
				session.save(samTodoList);

				samTodoList.setNew(false);
			}
			else {
				samTodoList = (SAMTodoList)session.merge(samTodoList);
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

		entityCache.putResult(SAMTodoListModelImpl.ENTITY_CACHE_ENABLED,
			SAMTodoListImpl.class, samTodoList.getPrimaryKey(), samTodoList,
			false);

		samTodoList.resetOriginalValues();

		return samTodoList;
	}

	protected SAMTodoList toUnwrappedModel(SAMTodoList samTodoList) {
		if (samTodoList instanceof SAMTodoListImpl) {
			return samTodoList;
		}

		SAMTodoListImpl samTodoListImpl = new SAMTodoListImpl();

		samTodoListImpl.setNew(samTodoList.isNew());
		samTodoListImpl.setPrimaryKey(samTodoList.getPrimaryKey());

		samTodoListImpl.setSamTodoListId(samTodoList.getSamTodoListId());
		samTodoListImpl.setCompanyId(samTodoList.getCompanyId());
		samTodoListImpl.setUserId(samTodoList.getUserId());
		samTodoListImpl.setUserName(samTodoList.getUserName());
		samTodoListImpl.setCreateDate(samTodoList.getCreateDate());
		samTodoListImpl.setModifiedDate(samTodoList.getModifiedDate());
		samTodoListImpl.setName(samTodoList.getName());

		return samTodoListImpl;
	}

	/**
	 * Returns the s a m todo list with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the s a m todo list
	 * @return the s a m todo list
	 * @throws NoSuchTodoListException if a s a m todo list with the primary key could not be found
	 */
	@Override
	public SAMTodoList findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTodoListException {
		SAMTodoList samTodoList = fetchByPrimaryKey(primaryKey);

		if (samTodoList == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTodoListException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return samTodoList;
	}

	/**
	 * Returns the s a m todo list with the primary key or throws a {@link NoSuchTodoListException} if it could not be found.
	 *
	 * @param samTodoListId the primary key of the s a m todo list
	 * @return the s a m todo list
	 * @throws NoSuchTodoListException if a s a m todo list with the primary key could not be found
	 */
	@Override
	public SAMTodoList findByPrimaryKey(long samTodoListId)
		throws NoSuchTodoListException {
		return findByPrimaryKey((Serializable)samTodoListId);
	}

	/**
	 * Returns the s a m todo list with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the s a m todo list
	 * @return the s a m todo list, or <code>null</code> if a s a m todo list with the primary key could not be found
	 */
	@Override
	public SAMTodoList fetchByPrimaryKey(Serializable primaryKey) {
		SAMTodoList samTodoList = (SAMTodoList)entityCache.getResult(SAMTodoListModelImpl.ENTITY_CACHE_ENABLED,
				SAMTodoListImpl.class, primaryKey);

		if (samTodoList == _nullSAMTodoList) {
			return null;
		}

		if (samTodoList == null) {
			Session session = null;

			try {
				session = openSession();

				samTodoList = (SAMTodoList)session.get(SAMTodoListImpl.class,
						primaryKey);

				if (samTodoList != null) {
					cacheResult(samTodoList);
				}
				else {
					entityCache.putResult(SAMTodoListModelImpl.ENTITY_CACHE_ENABLED,
						SAMTodoListImpl.class, primaryKey, _nullSAMTodoList);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SAMTodoListModelImpl.ENTITY_CACHE_ENABLED,
					SAMTodoListImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return samTodoList;
	}

	/**
	 * Returns the s a m todo list with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param samTodoListId the primary key of the s a m todo list
	 * @return the s a m todo list, or <code>null</code> if a s a m todo list with the primary key could not be found
	 */
	@Override
	public SAMTodoList fetchByPrimaryKey(long samTodoListId) {
		return fetchByPrimaryKey((Serializable)samTodoListId);
	}

	@Override
	public Map<Serializable, SAMTodoList> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SAMTodoList> map = new HashMap<Serializable, SAMTodoList>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SAMTodoList samTodoList = fetchByPrimaryKey(primaryKey);

			if (samTodoList != null) {
				map.put(primaryKey, samTodoList);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			SAMTodoList samTodoList = (SAMTodoList)entityCache.getResult(SAMTodoListModelImpl.ENTITY_CACHE_ENABLED,
					SAMTodoListImpl.class, primaryKey);

			if (samTodoList == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, samTodoList);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SAMTODOLIST_WHERE_PKS_IN);

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

			for (SAMTodoList samTodoList : (List<SAMTodoList>)q.list()) {
				map.put(samTodoList.getPrimaryKeyObj(), samTodoList);

				cacheResult(samTodoList);

				uncachedPrimaryKeys.remove(samTodoList.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SAMTodoListModelImpl.ENTITY_CACHE_ENABLED,
					SAMTodoListImpl.class, primaryKey, _nullSAMTodoList);
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
	 * Returns all the s a m todo lists.
	 *
	 * @return the s a m todo lists
	 */
	@Override
	public List<SAMTodoList> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the s a m todo lists.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SAMTodoListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of s a m todo lists
	 * @param end the upper bound of the range of s a m todo lists (not inclusive)
	 * @return the range of s a m todo lists
	 */
	@Override
	public List<SAMTodoList> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the s a m todo lists.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SAMTodoListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of s a m todo lists
	 * @param end the upper bound of the range of s a m todo lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of s a m todo lists
	 */
	@Override
	public List<SAMTodoList> findAll(int start, int end,
		OrderByComparator<SAMTodoList> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the s a m todo lists.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SAMTodoListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of s a m todo lists
	 * @param end the upper bound of the range of s a m todo lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of s a m todo lists
	 */
	@Override
	public List<SAMTodoList> findAll(int start, int end,
		OrderByComparator<SAMTodoList> orderByComparator,
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

		List<SAMTodoList> list = null;

		if (retrieveFromCache) {
			list = (List<SAMTodoList>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SAMTODOLIST);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SAMTODOLIST;

				if (pagination) {
					sql = sql.concat(SAMTodoListModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SAMTodoList>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SAMTodoList>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the s a m todo lists from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SAMTodoList samTodoList : findAll()) {
			remove(samTodoList);
		}
	}

	/**
	 * Returns the number of s a m todo lists.
	 *
	 * @return the number of s a m todo lists
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SAMTODOLIST);

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
		return SAMTodoListModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the s a m todo list persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SAMTodoListImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_SAMTODOLIST = "SELECT samTodoList FROM SAMTodoList samTodoList";
	private static final String _SQL_SELECT_SAMTODOLIST_WHERE_PKS_IN = "SELECT samTodoList FROM SAMTodoList samTodoList WHERE samTodoListId IN (";
	private static final String _SQL_COUNT_SAMTODOLIST = "SELECT COUNT(samTodoList) FROM SAMTodoList samTodoList";
	private static final String _ORDER_BY_ENTITY_ALIAS = "samTodoList.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SAMTodoList exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(SAMTodoListPersistenceImpl.class);
	private static final SAMTodoList _nullSAMTodoList = new SAMTodoListImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SAMTodoList> toCacheModel() {
				return _nullSAMTodoListCacheModel;
			}
		};

	private static final CacheModel<SAMTodoList> _nullSAMTodoListCacheModel = new CacheModel<SAMTodoList>() {
			@Override
			public SAMTodoList toEntityModel() {
				return _nullSAMTodoList;
			}
		};
}