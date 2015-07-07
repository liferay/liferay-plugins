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

package com.liferay.socialcoding.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.socialcoding.NoSuchSVNRepositoryException;
import com.liferay.socialcoding.model.SVNRepository;
import com.liferay.socialcoding.model.impl.SVNRepositoryImpl;
import com.liferay.socialcoding.model.impl.SVNRepositoryModelImpl;
import com.liferay.socialcoding.service.persistence.SVNRepositoryPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the s v n repository service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SVNRepositoryPersistence
 * @see com.liferay.socialcoding.service.persistence.SVNRepositoryUtil
 * @generated
 */
@ProviderType
public class SVNRepositoryPersistenceImpl extends BasePersistenceImpl<SVNRepository>
	implements SVNRepositoryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SVNRepositoryUtil} to access the s v n repository persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SVNRepositoryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SVNRepositoryModelImpl.ENTITY_CACHE_ENABLED,
			SVNRepositoryModelImpl.FINDER_CACHE_ENABLED,
			SVNRepositoryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SVNRepositoryModelImpl.ENTITY_CACHE_ENABLED,
			SVNRepositoryModelImpl.FINDER_CACHE_ENABLED,
			SVNRepositoryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SVNRepositoryModelImpl.ENTITY_CACHE_ENABLED,
			SVNRepositoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_URL = new FinderPath(SVNRepositoryModelImpl.ENTITY_CACHE_ENABLED,
			SVNRepositoryModelImpl.FINDER_CACHE_ENABLED,
			SVNRepositoryImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUrl",
			new String[] { String.class.getName() },
			SVNRepositoryModelImpl.URL_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_URL = new FinderPath(SVNRepositoryModelImpl.ENTITY_CACHE_ENABLED,
			SVNRepositoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUrl",
			new String[] { String.class.getName() });

	/**
	 * Returns the s v n repository where url = &#63; or throws a {@link NoSuchSVNRepositoryException} if it could not be found.
	 *
	 * @param url the url
	 * @return the matching s v n repository
	 * @throws NoSuchSVNRepositoryException if a matching s v n repository could not be found
	 */
	@Override
	public SVNRepository findByUrl(String url)
		throws NoSuchSVNRepositoryException {
		SVNRepository svnRepository = fetchByUrl(url);

		if (svnRepository == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("url=");
			msg.append(url);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchSVNRepositoryException(msg.toString());
		}

		return svnRepository;
	}

	/**
	 * Returns the s v n repository where url = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param url the url
	 * @return the matching s v n repository, or <code>null</code> if a matching s v n repository could not be found
	 */
	@Override
	public SVNRepository fetchByUrl(String url) {
		return fetchByUrl(url, true);
	}

	/**
	 * Returns the s v n repository where url = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param url the url
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching s v n repository, or <code>null</code> if a matching s v n repository could not be found
	 */
	@Override
	public SVNRepository fetchByUrl(String url, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { url };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_URL,
					finderArgs, this);
		}

		if (result instanceof SVNRepository) {
			SVNRepository svnRepository = (SVNRepository)result;

			if (!Validator.equals(url, svnRepository.getUrl())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SVNREPOSITORY_WHERE);

			boolean bindUrl = false;

			if (url == null) {
				query.append(_FINDER_COLUMN_URL_URL_1);
			}
			else if (url.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_URL_URL_3);
			}
			else {
				bindUrl = true;

				query.append(_FINDER_COLUMN_URL_URL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUrl) {
					qPos.add(url);
				}

				List<SVNRepository> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_URL,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"SVNRepositoryPersistenceImpl.fetchByUrl(String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					SVNRepository svnRepository = list.get(0);

					result = svnRepository;

					cacheResult(svnRepository);

					if ((svnRepository.getUrl() == null) ||
							!svnRepository.getUrl().equals(url)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_URL,
							finderArgs, svnRepository);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_URL,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (SVNRepository)result;
		}
	}

	/**
	 * Removes the s v n repository where url = &#63; from the database.
	 *
	 * @param url the url
	 * @return the s v n repository that was removed
	 */
	@Override
	public SVNRepository removeByUrl(String url)
		throws NoSuchSVNRepositoryException {
		SVNRepository svnRepository = findByUrl(url);

		return remove(svnRepository);
	}

	/**
	 * Returns the number of s v n repositories where url = &#63;.
	 *
	 * @param url the url
	 * @return the number of matching s v n repositories
	 */
	@Override
	public int countByUrl(String url) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_URL;

		Object[] finderArgs = new Object[] { url };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SVNREPOSITORY_WHERE);

			boolean bindUrl = false;

			if (url == null) {
				query.append(_FINDER_COLUMN_URL_URL_1);
			}
			else if (url.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_URL_URL_3);
			}
			else {
				bindUrl = true;

				query.append(_FINDER_COLUMN_URL_URL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUrl) {
					qPos.add(url);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_URL_URL_1 = "svnRepository.url IS NULL";
	private static final String _FINDER_COLUMN_URL_URL_2 = "svnRepository.url = ?";
	private static final String _FINDER_COLUMN_URL_URL_3 = "(svnRepository.url IS NULL OR svnRepository.url = '')";

	public SVNRepositoryPersistenceImpl() {
		setModelClass(SVNRepository.class);
	}

	/**
	 * Caches the s v n repository in the entity cache if it is enabled.
	 *
	 * @param svnRepository the s v n repository
	 */
	@Override
	public void cacheResult(SVNRepository svnRepository) {
		EntityCacheUtil.putResult(SVNRepositoryModelImpl.ENTITY_CACHE_ENABLED,
			SVNRepositoryImpl.class, svnRepository.getPrimaryKey(),
			svnRepository);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_URL,
			new Object[] { svnRepository.getUrl() }, svnRepository);

		svnRepository.resetOriginalValues();
	}

	/**
	 * Caches the s v n repositories in the entity cache if it is enabled.
	 *
	 * @param svnRepositories the s v n repositories
	 */
	@Override
	public void cacheResult(List<SVNRepository> svnRepositories) {
		for (SVNRepository svnRepository : svnRepositories) {
			if (EntityCacheUtil.getResult(
						SVNRepositoryModelImpl.ENTITY_CACHE_ENABLED,
						SVNRepositoryImpl.class, svnRepository.getPrimaryKey()) == null) {
				cacheResult(svnRepository);
			}
			else {
				svnRepository.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all s v n repositories.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(SVNRepositoryImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the s v n repository.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SVNRepository svnRepository) {
		EntityCacheUtil.removeResult(SVNRepositoryModelImpl.ENTITY_CACHE_ENABLED,
			SVNRepositoryImpl.class, svnRepository.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(svnRepository);
	}

	@Override
	public void clearCache(List<SVNRepository> svnRepositories) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SVNRepository svnRepository : svnRepositories) {
			EntityCacheUtil.removeResult(SVNRepositoryModelImpl.ENTITY_CACHE_ENABLED,
				SVNRepositoryImpl.class, svnRepository.getPrimaryKey());

			clearUniqueFindersCache(svnRepository);
		}
	}

	protected void cacheUniqueFindersCache(SVNRepository svnRepository) {
		if (svnRepository.isNew()) {
			Object[] args = new Object[] { svnRepository.getUrl() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_URL, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_URL, args,
				svnRepository);
		}
		else {
			SVNRepositoryModelImpl svnRepositoryModelImpl = (SVNRepositoryModelImpl)svnRepository;

			if ((svnRepositoryModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_URL.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { svnRepository.getUrl() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_URL, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_URL, args,
					svnRepository);
			}
		}
	}

	protected void clearUniqueFindersCache(SVNRepository svnRepository) {
		SVNRepositoryModelImpl svnRepositoryModelImpl = (SVNRepositoryModelImpl)svnRepository;

		Object[] args = new Object[] { svnRepository.getUrl() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_URL, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_URL, args);

		if ((svnRepositoryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_URL.getColumnBitmask()) != 0) {
			args = new Object[] { svnRepositoryModelImpl.getOriginalUrl() };

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_URL, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_URL, args);
		}
	}

	/**
	 * Creates a new s v n repository with the primary key. Does not add the s v n repository to the database.
	 *
	 * @param svnRepositoryId the primary key for the new s v n repository
	 * @return the new s v n repository
	 */
	@Override
	public SVNRepository create(long svnRepositoryId) {
		SVNRepository svnRepository = new SVNRepositoryImpl();

		svnRepository.setNew(true);
		svnRepository.setPrimaryKey(svnRepositoryId);

		return svnRepository;
	}

	/**
	 * Removes the s v n repository with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param svnRepositoryId the primary key of the s v n repository
	 * @return the s v n repository that was removed
	 * @throws NoSuchSVNRepositoryException if a s v n repository with the primary key could not be found
	 */
	@Override
	public SVNRepository remove(long svnRepositoryId)
		throws NoSuchSVNRepositoryException {
		return remove((Serializable)svnRepositoryId);
	}

	/**
	 * Removes the s v n repository with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the s v n repository
	 * @return the s v n repository that was removed
	 * @throws NoSuchSVNRepositoryException if a s v n repository with the primary key could not be found
	 */
	@Override
	public SVNRepository remove(Serializable primaryKey)
		throws NoSuchSVNRepositoryException {
		Session session = null;

		try {
			session = openSession();

			SVNRepository svnRepository = (SVNRepository)session.get(SVNRepositoryImpl.class,
					primaryKey);

			if (svnRepository == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSVNRepositoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(svnRepository);
		}
		catch (NoSuchSVNRepositoryException nsee) {
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
	protected SVNRepository removeImpl(SVNRepository svnRepository) {
		svnRepository = toUnwrappedModel(svnRepository);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(svnRepository)) {
				svnRepository = (SVNRepository)session.get(SVNRepositoryImpl.class,
						svnRepository.getPrimaryKeyObj());
			}

			if (svnRepository != null) {
				session.delete(svnRepository);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (svnRepository != null) {
			clearCache(svnRepository);
		}

		return svnRepository;
	}

	@Override
	public SVNRepository updateImpl(SVNRepository svnRepository) {
		svnRepository = toUnwrappedModel(svnRepository);

		boolean isNew = svnRepository.isNew();

		Session session = null;

		try {
			session = openSession();

			if (svnRepository.isNew()) {
				session.save(svnRepository);

				svnRepository.setNew(false);
			}
			else {
				session.merge(svnRepository);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SVNRepositoryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(SVNRepositoryModelImpl.ENTITY_CACHE_ENABLED,
			SVNRepositoryImpl.class, svnRepository.getPrimaryKey(),
			svnRepository, false);

		clearUniqueFindersCache(svnRepository);
		cacheUniqueFindersCache(svnRepository);

		svnRepository.resetOriginalValues();

		return svnRepository;
	}

	protected SVNRepository toUnwrappedModel(SVNRepository svnRepository) {
		if (svnRepository instanceof SVNRepositoryImpl) {
			return svnRepository;
		}

		SVNRepositoryImpl svnRepositoryImpl = new SVNRepositoryImpl();

		svnRepositoryImpl.setNew(svnRepository.isNew());
		svnRepositoryImpl.setPrimaryKey(svnRepository.getPrimaryKey());

		svnRepositoryImpl.setSvnRepositoryId(svnRepository.getSvnRepositoryId());
		svnRepositoryImpl.setUrl(svnRepository.getUrl());
		svnRepositoryImpl.setRevisionNumber(svnRepository.getRevisionNumber());

		return svnRepositoryImpl;
	}

	/**
	 * Returns the s v n repository with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the s v n repository
	 * @return the s v n repository
	 * @throws NoSuchSVNRepositoryException if a s v n repository with the primary key could not be found
	 */
	@Override
	public SVNRepository findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSVNRepositoryException {
		SVNRepository svnRepository = fetchByPrimaryKey(primaryKey);

		if (svnRepository == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSVNRepositoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return svnRepository;
	}

	/**
	 * Returns the s v n repository with the primary key or throws a {@link NoSuchSVNRepositoryException} if it could not be found.
	 *
	 * @param svnRepositoryId the primary key of the s v n repository
	 * @return the s v n repository
	 * @throws NoSuchSVNRepositoryException if a s v n repository with the primary key could not be found
	 */
	@Override
	public SVNRepository findByPrimaryKey(long svnRepositoryId)
		throws NoSuchSVNRepositoryException {
		return findByPrimaryKey((Serializable)svnRepositoryId);
	}

	/**
	 * Returns the s v n repository with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the s v n repository
	 * @return the s v n repository, or <code>null</code> if a s v n repository with the primary key could not be found
	 */
	@Override
	public SVNRepository fetchByPrimaryKey(Serializable primaryKey) {
		SVNRepository svnRepository = (SVNRepository)EntityCacheUtil.getResult(SVNRepositoryModelImpl.ENTITY_CACHE_ENABLED,
				SVNRepositoryImpl.class, primaryKey);

		if (svnRepository == _nullSVNRepository) {
			return null;
		}

		if (svnRepository == null) {
			Session session = null;

			try {
				session = openSession();

				svnRepository = (SVNRepository)session.get(SVNRepositoryImpl.class,
						primaryKey);

				if (svnRepository != null) {
					cacheResult(svnRepository);
				}
				else {
					EntityCacheUtil.putResult(SVNRepositoryModelImpl.ENTITY_CACHE_ENABLED,
						SVNRepositoryImpl.class, primaryKey, _nullSVNRepository);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(SVNRepositoryModelImpl.ENTITY_CACHE_ENABLED,
					SVNRepositoryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return svnRepository;
	}

	/**
	 * Returns the s v n repository with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param svnRepositoryId the primary key of the s v n repository
	 * @return the s v n repository, or <code>null</code> if a s v n repository with the primary key could not be found
	 */
	@Override
	public SVNRepository fetchByPrimaryKey(long svnRepositoryId) {
		return fetchByPrimaryKey((Serializable)svnRepositoryId);
	}

	@Override
	public Map<Serializable, SVNRepository> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SVNRepository> map = new HashMap<Serializable, SVNRepository>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SVNRepository svnRepository = fetchByPrimaryKey(primaryKey);

			if (svnRepository != null) {
				map.put(primaryKey, svnRepository);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			SVNRepository svnRepository = (SVNRepository)EntityCacheUtil.getResult(SVNRepositoryModelImpl.ENTITY_CACHE_ENABLED,
					SVNRepositoryImpl.class, primaryKey);

			if (svnRepository == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, svnRepository);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SVNREPOSITORY_WHERE_PKS_IN);

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

			for (SVNRepository svnRepository : (List<SVNRepository>)q.list()) {
				map.put(svnRepository.getPrimaryKeyObj(), svnRepository);

				cacheResult(svnRepository);

				uncachedPrimaryKeys.remove(svnRepository.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(SVNRepositoryModelImpl.ENTITY_CACHE_ENABLED,
					SVNRepositoryImpl.class, primaryKey, _nullSVNRepository);
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
	 * Returns all the s v n repositories.
	 *
	 * @return the s v n repositories
	 */
	@Override
	public List<SVNRepository> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the s v n repositories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SVNRepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of s v n repositories
	 * @param end the upper bound of the range of s v n repositories (not inclusive)
	 * @return the range of s v n repositories
	 */
	@Override
	public List<SVNRepository> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the s v n repositories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SVNRepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of s v n repositories
	 * @param end the upper bound of the range of s v n repositories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of s v n repositories
	 */
	@Override
	public List<SVNRepository> findAll(int start, int end,
		OrderByComparator<SVNRepository> orderByComparator) {
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

		List<SVNRepository> list = (List<SVNRepository>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SVNREPOSITORY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SVNREPOSITORY;

				if (pagination) {
					sql = sql.concat(SVNRepositoryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SVNRepository>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SVNRepository>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the s v n repositories from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SVNRepository svnRepository : findAll()) {
			remove(svnRepository);
		}
	}

	/**
	 * Returns the number of s v n repositories.
	 *
	 * @return the number of s v n repositories
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SVNREPOSITORY);

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

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SVNRepositoryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the s v n repository persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(SVNRepositoryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SVNREPOSITORY = "SELECT svnRepository FROM SVNRepository svnRepository";
	private static final String _SQL_SELECT_SVNREPOSITORY_WHERE_PKS_IN = "SELECT svnRepository FROM SVNRepository svnRepository WHERE svnRepositoryId IN (";
	private static final String _SQL_SELECT_SVNREPOSITORY_WHERE = "SELECT svnRepository FROM SVNRepository svnRepository WHERE ";
	private static final String _SQL_COUNT_SVNREPOSITORY = "SELECT COUNT(svnRepository) FROM SVNRepository svnRepository";
	private static final String _SQL_COUNT_SVNREPOSITORY_WHERE = "SELECT COUNT(svnRepository) FROM SVNRepository svnRepository WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "svnRepository.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SVNRepository exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SVNRepository exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SVNRepositoryPersistenceImpl.class);
	private static final SVNRepository _nullSVNRepository = new SVNRepositoryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SVNRepository> toCacheModel() {
				return _nullSVNRepositoryCacheModel;
			}
		};

	private static final CacheModel<SVNRepository> _nullSVNRepositoryCacheModel = new CacheModel<SVNRepository>() {
			@Override
			public SVNRepository toEntityModel() {
				return _nullSVNRepository;
			}
		};
}