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

package com.liferay.twitter.service.persistence.impl;

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
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.twitter.NoSuchFeedException;
import com.liferay.twitter.model.Feed;
import com.liferay.twitter.model.impl.FeedImpl;
import com.liferay.twitter.model.impl.FeedModelImpl;
import com.liferay.twitter.service.persistence.FeedPersistence;

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
 * The persistence implementation for the feed service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FeedPersistence
 * @see com.liferay.twitter.service.persistence.FeedUtil
 * @generated
 */
@ProviderType
public class FeedPersistenceImpl extends BasePersistenceImpl<Feed>
	implements FeedPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link FeedUtil} to access the feed persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = FeedImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FeedModelImpl.ENTITY_CACHE_ENABLED,
			FeedModelImpl.FINDER_CACHE_ENABLED, FeedImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FeedModelImpl.ENTITY_CACHE_ENABLED,
			FeedModelImpl.FINDER_CACHE_ENABLED, FeedImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FeedModelImpl.ENTITY_CACHE_ENABLED,
			FeedModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_U_TSN = new FinderPath(FeedModelImpl.ENTITY_CACHE_ENABLED,
			FeedModelImpl.FINDER_CACHE_ENABLED, FeedImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByU_TSN",
			new String[] { Long.class.getName(), String.class.getName() },
			FeedModelImpl.USERID_COLUMN_BITMASK |
			FeedModelImpl.TWITTERSCREENNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_TSN = new FinderPath(FeedModelImpl.ENTITY_CACHE_ENABLED,
			FeedModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_TSN",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the feed where userId = &#63; and twitterScreenName = &#63; or throws a {@link NoSuchFeedException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param twitterScreenName the twitter screen name
	 * @return the matching feed
	 * @throws NoSuchFeedException if a matching feed could not be found
	 */
	@Override
	public Feed findByU_TSN(long userId, String twitterScreenName)
		throws NoSuchFeedException {
		Feed feed = fetchByU_TSN(userId, twitterScreenName);

		if (feed == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", twitterScreenName=");
			msg.append(twitterScreenName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchFeedException(msg.toString());
		}

		return feed;
	}

	/**
	 * Returns the feed where userId = &#63; and twitterScreenName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param twitterScreenName the twitter screen name
	 * @return the matching feed, or <code>null</code> if a matching feed could not be found
	 */
	@Override
	public Feed fetchByU_TSN(long userId, String twitterScreenName) {
		return fetchByU_TSN(userId, twitterScreenName, true);
	}

	/**
	 * Returns the feed where userId = &#63; and twitterScreenName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param twitterScreenName the twitter screen name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching feed, or <code>null</code> if a matching feed could not be found
	 */
	@Override
	public Feed fetchByU_TSN(long userId, String twitterScreenName,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { userId, twitterScreenName };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_U_TSN,
					finderArgs, this);
		}

		if (result instanceof Feed) {
			Feed feed = (Feed)result;

			if ((userId != feed.getUserId()) ||
					!Validator.equals(twitterScreenName,
						feed.getTwitterScreenName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_FEED_WHERE);

			query.append(_FINDER_COLUMN_U_TSN_USERID_2);

			boolean bindTwitterScreenName = false;

			if (twitterScreenName == null) {
				query.append(_FINDER_COLUMN_U_TSN_TWITTERSCREENNAME_1);
			}
			else if (twitterScreenName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_U_TSN_TWITTERSCREENNAME_3);
			}
			else {
				bindTwitterScreenName = true;

				query.append(_FINDER_COLUMN_U_TSN_TWITTERSCREENNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (bindTwitterScreenName) {
					qPos.add(twitterScreenName);
				}

				List<Feed> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_TSN,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"FeedPersistenceImpl.fetchByU_TSN(long, String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					Feed feed = list.get(0);

					result = feed;

					cacheResult(feed);

					if ((feed.getUserId() != userId) ||
							(feed.getTwitterScreenName() == null) ||
							!feed.getTwitterScreenName()
									 .equals(twitterScreenName)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_TSN,
							finderArgs, feed);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_TSN,
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
			return (Feed)result;
		}
	}

	/**
	 * Removes the feed where userId = &#63; and twitterScreenName = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param twitterScreenName the twitter screen name
	 * @return the feed that was removed
	 */
	@Override
	public Feed removeByU_TSN(long userId, String twitterScreenName)
		throws NoSuchFeedException {
		Feed feed = findByU_TSN(userId, twitterScreenName);

		return remove(feed);
	}

	/**
	 * Returns the number of feeds where userId = &#63; and twitterScreenName = &#63;.
	 *
	 * @param userId the user ID
	 * @param twitterScreenName the twitter screen name
	 * @return the number of matching feeds
	 */
	@Override
	public int countByU_TSN(long userId, String twitterScreenName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_TSN;

		Object[] finderArgs = new Object[] { userId, twitterScreenName };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_FEED_WHERE);

			query.append(_FINDER_COLUMN_U_TSN_USERID_2);

			boolean bindTwitterScreenName = false;

			if (twitterScreenName == null) {
				query.append(_FINDER_COLUMN_U_TSN_TWITTERSCREENNAME_1);
			}
			else if (twitterScreenName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_U_TSN_TWITTERSCREENNAME_3);
			}
			else {
				bindTwitterScreenName = true;

				query.append(_FINDER_COLUMN_U_TSN_TWITTERSCREENNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (bindTwitterScreenName) {
					qPos.add(twitterScreenName);
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

	private static final String _FINDER_COLUMN_U_TSN_USERID_2 = "feed.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_TSN_TWITTERSCREENNAME_1 = "feed.twitterScreenName IS NULL";
	private static final String _FINDER_COLUMN_U_TSN_TWITTERSCREENNAME_2 = "feed.twitterScreenName = ?";
	private static final String _FINDER_COLUMN_U_TSN_TWITTERSCREENNAME_3 = "(feed.twitterScreenName IS NULL OR feed.twitterScreenName = '')";

	public FeedPersistenceImpl() {
		setModelClass(Feed.class);
	}

	/**
	 * Caches the feed in the entity cache if it is enabled.
	 *
	 * @param feed the feed
	 */
	@Override
	public void cacheResult(Feed feed) {
		EntityCacheUtil.putResult(FeedModelImpl.ENTITY_CACHE_ENABLED,
			FeedImpl.class, feed.getPrimaryKey(), feed);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_TSN,
			new Object[] { feed.getUserId(), feed.getTwitterScreenName() }, feed);

		feed.resetOriginalValues();
	}

	/**
	 * Caches the feeds in the entity cache if it is enabled.
	 *
	 * @param feeds the feeds
	 */
	@Override
	public void cacheResult(List<Feed> feeds) {
		for (Feed feed : feeds) {
			if (EntityCacheUtil.getResult(FeedModelImpl.ENTITY_CACHE_ENABLED,
						FeedImpl.class, feed.getPrimaryKey()) == null) {
				cacheResult(feed);
			}
			else {
				feed.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all feeds.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(FeedImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the feed.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Feed feed) {
		EntityCacheUtil.removeResult(FeedModelImpl.ENTITY_CACHE_ENABLED,
			FeedImpl.class, feed.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((FeedModelImpl)feed);
	}

	@Override
	public void clearCache(List<Feed> feeds) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Feed feed : feeds) {
			EntityCacheUtil.removeResult(FeedModelImpl.ENTITY_CACHE_ENABLED,
				FeedImpl.class, feed.getPrimaryKey());

			clearUniqueFindersCache((FeedModelImpl)feed);
		}
	}

	protected void cacheUniqueFindersCache(FeedModelImpl feedModelImpl,
		boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					feedModelImpl.getUserId(),
					feedModelImpl.getTwitterScreenName()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_TSN, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_TSN, args,
				feedModelImpl);
		}
		else {
			if ((feedModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_U_TSN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						feedModelImpl.getUserId(),
						feedModelImpl.getTwitterScreenName()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_TSN, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_TSN, args,
					feedModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(FeedModelImpl feedModelImpl) {
		Object[] args = new Object[] {
				feedModelImpl.getUserId(), feedModelImpl.getTwitterScreenName()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_TSN, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_TSN, args);

		if ((feedModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_TSN.getColumnBitmask()) != 0) {
			args = new Object[] {
					feedModelImpl.getOriginalUserId(),
					feedModelImpl.getOriginalTwitterScreenName()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_TSN, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_TSN, args);
		}
	}

	/**
	 * Creates a new feed with the primary key. Does not add the feed to the database.
	 *
	 * @param feedId the primary key for the new feed
	 * @return the new feed
	 */
	@Override
	public Feed create(long feedId) {
		Feed feed = new FeedImpl();

		feed.setNew(true);
		feed.setPrimaryKey(feedId);

		return feed;
	}

	/**
	 * Removes the feed with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param feedId the primary key of the feed
	 * @return the feed that was removed
	 * @throws NoSuchFeedException if a feed with the primary key could not be found
	 */
	@Override
	public Feed remove(long feedId) throws NoSuchFeedException {
		return remove((Serializable)feedId);
	}

	/**
	 * Removes the feed with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the feed
	 * @return the feed that was removed
	 * @throws NoSuchFeedException if a feed with the primary key could not be found
	 */
	@Override
	public Feed remove(Serializable primaryKey) throws NoSuchFeedException {
		Session session = null;

		try {
			session = openSession();

			Feed feed = (Feed)session.get(FeedImpl.class, primaryKey);

			if (feed == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFeedException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(feed);
		}
		catch (NoSuchFeedException nsee) {
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
	protected Feed removeImpl(Feed feed) {
		feed = toUnwrappedModel(feed);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(feed)) {
				feed = (Feed)session.get(FeedImpl.class, feed.getPrimaryKeyObj());
			}

			if (feed != null) {
				session.delete(feed);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (feed != null) {
			clearCache(feed);
		}

		return feed;
	}

	@Override
	public Feed updateImpl(Feed feed) {
		feed = toUnwrappedModel(feed);

		boolean isNew = feed.isNew();

		FeedModelImpl feedModelImpl = (FeedModelImpl)feed;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (feed.getCreateDate() == null)) {
			if (serviceContext == null) {
				feed.setCreateDate(now);
			}
			else {
				feed.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!feedModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				feed.setModifiedDate(now);
			}
			else {
				feed.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (feed.isNew()) {
				session.save(feed);

				feed.setNew(false);
			}
			else {
				feed = (Feed)session.merge(feed);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !FeedModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(FeedModelImpl.ENTITY_CACHE_ENABLED,
			FeedImpl.class, feed.getPrimaryKey(), feed, false);

		clearUniqueFindersCache(feedModelImpl);
		cacheUniqueFindersCache(feedModelImpl, isNew);

		feed.resetOriginalValues();

		return feed;
	}

	protected Feed toUnwrappedModel(Feed feed) {
		if (feed instanceof FeedImpl) {
			return feed;
		}

		FeedImpl feedImpl = new FeedImpl();

		feedImpl.setNew(feed.isNew());
		feedImpl.setPrimaryKey(feed.getPrimaryKey());

		feedImpl.setFeedId(feed.getFeedId());
		feedImpl.setCompanyId(feed.getCompanyId());
		feedImpl.setUserId(feed.getUserId());
		feedImpl.setUserName(feed.getUserName());
		feedImpl.setCreateDate(feed.getCreateDate());
		feedImpl.setModifiedDate(feed.getModifiedDate());
		feedImpl.setTwitterUserId(feed.getTwitterUserId());
		feedImpl.setTwitterScreenName(feed.getTwitterScreenName());
		feedImpl.setLastStatusId(feed.getLastStatusId());

		return feedImpl;
	}

	/**
	 * Returns the feed with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the feed
	 * @return the feed
	 * @throws NoSuchFeedException if a feed with the primary key could not be found
	 */
	@Override
	public Feed findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFeedException {
		Feed feed = fetchByPrimaryKey(primaryKey);

		if (feed == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFeedException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return feed;
	}

	/**
	 * Returns the feed with the primary key or throws a {@link NoSuchFeedException} if it could not be found.
	 *
	 * @param feedId the primary key of the feed
	 * @return the feed
	 * @throws NoSuchFeedException if a feed with the primary key could not be found
	 */
	@Override
	public Feed findByPrimaryKey(long feedId) throws NoSuchFeedException {
		return findByPrimaryKey((Serializable)feedId);
	}

	/**
	 * Returns the feed with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the feed
	 * @return the feed, or <code>null</code> if a feed with the primary key could not be found
	 */
	@Override
	public Feed fetchByPrimaryKey(Serializable primaryKey) {
		Feed feed = (Feed)EntityCacheUtil.getResult(FeedModelImpl.ENTITY_CACHE_ENABLED,
				FeedImpl.class, primaryKey);

		if (feed == _nullFeed) {
			return null;
		}

		if (feed == null) {
			Session session = null;

			try {
				session = openSession();

				feed = (Feed)session.get(FeedImpl.class, primaryKey);

				if (feed != null) {
					cacheResult(feed);
				}
				else {
					EntityCacheUtil.putResult(FeedModelImpl.ENTITY_CACHE_ENABLED,
						FeedImpl.class, primaryKey, _nullFeed);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(FeedModelImpl.ENTITY_CACHE_ENABLED,
					FeedImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return feed;
	}

	/**
	 * Returns the feed with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param feedId the primary key of the feed
	 * @return the feed, or <code>null</code> if a feed with the primary key could not be found
	 */
	@Override
	public Feed fetchByPrimaryKey(long feedId) {
		return fetchByPrimaryKey((Serializable)feedId);
	}

	@Override
	public Map<Serializable, Feed> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Feed> map = new HashMap<Serializable, Feed>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Feed feed = fetchByPrimaryKey(primaryKey);

			if (feed != null) {
				map.put(primaryKey, feed);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Feed feed = (Feed)EntityCacheUtil.getResult(FeedModelImpl.ENTITY_CACHE_ENABLED,
					FeedImpl.class, primaryKey);

			if (feed == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, feed);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_FEED_WHERE_PKS_IN);

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

			for (Feed feed : (List<Feed>)q.list()) {
				map.put(feed.getPrimaryKeyObj(), feed);

				cacheResult(feed);

				uncachedPrimaryKeys.remove(feed.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(FeedModelImpl.ENTITY_CACHE_ENABLED,
					FeedImpl.class, primaryKey, _nullFeed);
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
	 * Returns all the feeds.
	 *
	 * @return the feeds
	 */
	@Override
	public List<Feed> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the feeds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FeedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of feeds
	 * @param end the upper bound of the range of feeds (not inclusive)
	 * @return the range of feeds
	 */
	@Override
	public List<Feed> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the feeds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FeedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of feeds
	 * @param end the upper bound of the range of feeds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of feeds
	 */
	@Override
	public List<Feed> findAll(int start, int end,
		OrderByComparator<Feed> orderByComparator) {
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

		List<Feed> list = (List<Feed>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_FEED);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_FEED;

				if (pagination) {
					sql = sql.concat(FeedModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Feed>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Feed>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Removes all the feeds from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Feed feed : findAll()) {
			remove(feed);
		}
	}

	/**
	 * Returns the number of feeds.
	 *
	 * @return the number of feeds
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_FEED);

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
		return FeedModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the feed persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(FeedImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_FEED = "SELECT feed FROM Feed feed";
	private static final String _SQL_SELECT_FEED_WHERE_PKS_IN = "SELECT feed FROM Feed feed WHERE feedId IN (";
	private static final String _SQL_SELECT_FEED_WHERE = "SELECT feed FROM Feed feed WHERE ";
	private static final String _SQL_COUNT_FEED = "SELECT COUNT(feed) FROM Feed feed";
	private static final String _SQL_COUNT_FEED_WHERE = "SELECT COUNT(feed) FROM Feed feed WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "feed.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Feed exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Feed exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(FeedPersistenceImpl.class);
	private static final Feed _nullFeed = new FeedImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Feed> toCacheModel() {
				return _nullFeedCacheModel;
			}
		};

	private static final CacheModel<Feed> _nullFeedCacheModel = new CacheModel<Feed>() {
			@Override
			public Feed toEntityModel() {
				return _nullFeed;
			}
		};
}