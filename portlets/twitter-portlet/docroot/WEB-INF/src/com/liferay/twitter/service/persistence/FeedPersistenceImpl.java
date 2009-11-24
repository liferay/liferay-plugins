/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.twitter.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.SystemException;
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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.twitter.NoSuchFeedException;
import com.liferay.twitter.model.Feed;
import com.liferay.twitter.model.impl.FeedImpl;
import com.liferay.twitter.model.impl.FeedModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="FeedPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class FeedPersistenceImpl extends BasePersistenceImpl<Feed>
	implements FeedPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = FeedImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FETCH_BY_TWITTERUSERID = new FinderPath(FeedModelImpl.ENTITY_CACHE_ENABLED,
			FeedModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_ENTITY,
			"fetchByTwitterUserId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_TWITTERUSERID = new FinderPath(FeedModelImpl.ENTITY_CACHE_ENABLED,
			FeedModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByTwitterUserId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_TWITTERSCREENNAME = new FinderPath(FeedModelImpl.ENTITY_CACHE_ENABLED,
			FeedModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_ENTITY,
			"fetchByTwitterScreenName", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_TWITTERSCREENNAME = new FinderPath(FeedModelImpl.ENTITY_CACHE_ENABLED,
			FeedModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByTwitterScreenName", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(FeedModelImpl.ENTITY_CACHE_ENABLED,
			FeedModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FeedModelImpl.ENTITY_CACHE_ENABLED,
			FeedModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(Feed feed) {
		EntityCacheUtil.putResult(FeedModelImpl.ENTITY_CACHE_ENABLED,
			FeedImpl.class, feed.getPrimaryKey(), feed);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TWITTERUSERID,
			new Object[] { new Long(feed.getTwitterUserId()) }, feed);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TWITTERSCREENNAME,
			new Object[] { feed.getTwitterScreenName() }, feed);
	}

	public void cacheResult(List<Feed> feeds) {
		for (Feed feed : feeds) {
			if (EntityCacheUtil.getResult(FeedModelImpl.ENTITY_CACHE_ENABLED,
						FeedImpl.class, feed.getPrimaryKey(), this) == null) {
				cacheResult(feed);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(FeedImpl.class.getName());
		EntityCacheUtil.clearCache(FeedImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public Feed create(long feedId) {
		Feed feed = new FeedImpl();

		feed.setNew(true);
		feed.setPrimaryKey(feedId);

		return feed;
	}

	public Feed remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public Feed remove(long feedId) throws NoSuchFeedException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Feed feed = (Feed)session.get(FeedImpl.class, new Long(feedId));

			if (feed == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No Feed exists with the primary key " + feedId);
				}

				throw new NoSuchFeedException(
					"No Feed exists with the primary key " + feedId);
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

	public Feed remove(Feed feed) throws SystemException {
		for (ModelListener<Feed> listener : listeners) {
			listener.onBeforeRemove(feed);
		}

		feed = removeImpl(feed);

		for (ModelListener<Feed> listener : listeners) {
			listener.onAfterRemove(feed);
		}

		return feed;
	}

	protected Feed removeImpl(Feed feed) throws SystemException {
		feed = toUnwrappedModel(feed);

		Session session = null;

		try {
			session = openSession();

			if (feed.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(FeedImpl.class,
						feed.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(feed);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		FeedModelImpl feedModelImpl = (FeedModelImpl)feed;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TWITTERUSERID,
			new Object[] { new Long(feedModelImpl.getOriginalTwitterUserId()) });

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TWITTERSCREENNAME,
			new Object[] { feedModelImpl.getOriginalTwitterScreenName() });

		EntityCacheUtil.removeResult(FeedModelImpl.ENTITY_CACHE_ENABLED,
			FeedImpl.class, feed.getPrimaryKey());

		return feed;
	}

	public Feed updateImpl(com.liferay.twitter.model.Feed feed, boolean merge)
		throws SystemException {
		feed = toUnwrappedModel(feed);

		boolean isNew = feed.isNew();

		FeedModelImpl feedModelImpl = (FeedModelImpl)feed;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, feed, merge);

			feed.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(FeedModelImpl.ENTITY_CACHE_ENABLED,
			FeedImpl.class, feed.getPrimaryKey(), feed);

		if (!isNew &&
				(feed.getTwitterUserId() != feedModelImpl.getOriginalTwitterUserId())) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TWITTERUSERID,
				new Object[] { new Long(feedModelImpl.getOriginalTwitterUserId()) });
		}

		if (isNew ||
				(feed.getTwitterUserId() != feedModelImpl.getOriginalTwitterUserId())) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TWITTERUSERID,
				new Object[] { new Long(feed.getTwitterUserId()) }, feed);
		}

		if (!isNew &&
				(!Validator.equals(feed.getTwitterScreenName(),
					feedModelImpl.getOriginalTwitterScreenName()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TWITTERSCREENNAME,
				new Object[] { feedModelImpl.getOriginalTwitterScreenName() });
		}

		if (isNew ||
				(!Validator.equals(feed.getTwitterScreenName(),
					feedModelImpl.getOriginalTwitterScreenName()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TWITTERSCREENNAME,
				new Object[] { feed.getTwitterScreenName() }, feed);
		}

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
		feedImpl.setTwitterUserId(feed.getTwitterUserId());
		feedImpl.setTwitterScreenName(feed.getTwitterScreenName());
		feedImpl.setCreateDate(feed.getCreateDate());
		feedImpl.setModifiedDate(feed.getModifiedDate());
		feedImpl.setLastStatusId(feed.getLastStatusId());

		return feedImpl;
	}

	public Feed findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public Feed findByPrimaryKey(long feedId)
		throws NoSuchFeedException, SystemException {
		Feed feed = fetchByPrimaryKey(feedId);

		if (feed == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No Feed exists with the primary key " + feedId);
			}

			throw new NoSuchFeedException(
				"No Feed exists with the primary key " + feedId);
		}

		return feed;
	}

	public Feed fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public Feed fetchByPrimaryKey(long feedId) throws SystemException {
		Feed feed = (Feed)EntityCacheUtil.getResult(FeedModelImpl.ENTITY_CACHE_ENABLED,
				FeedImpl.class, feedId, this);

		if (feed == null) {
			Session session = null;

			try {
				session = openSession();

				feed = (Feed)session.get(FeedImpl.class, new Long(feedId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (feed != null) {
					cacheResult(feed);
				}

				closeSession(session);
			}
		}

		return feed;
	}

	public Feed findByTwitterUserId(long twitterUserId)
		throws NoSuchFeedException, SystemException {
		Feed feed = fetchByTwitterUserId(twitterUserId);

		if (feed == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Feed exists with the key {");

			msg.append("twitterUserId=" + twitterUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchFeedException(msg.toString());
		}

		return feed;
	}

	public Feed fetchByTwitterUserId(long twitterUserId)
		throws SystemException {
		return fetchByTwitterUserId(twitterUserId, true);
	}

	public Feed fetchByTwitterUserId(long twitterUserId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(twitterUserId) };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_TWITTERUSERID,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT feed FROM Feed feed WHERE ");

				query.append("feed.twitterUserId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(twitterUserId);

				List<Feed> list = q.list();

				result = list;

				Feed feed = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TWITTERUSERID,
						finderArgs, list);
				}
				else {
					feed = list.get(0);

					cacheResult(feed);

					if ((feed.getTwitterUserId() != twitterUserId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TWITTERUSERID,
							finderArgs, feed);
					}
				}

				return feed;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TWITTERUSERID,
						finderArgs, new ArrayList<Feed>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (Feed)result;
			}
		}
	}

	public Feed findByTwitterScreenName(String twitterScreenName)
		throws NoSuchFeedException, SystemException {
		Feed feed = fetchByTwitterScreenName(twitterScreenName);

		if (feed == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Feed exists with the key {");

			msg.append("twitterScreenName=" + twitterScreenName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchFeedException(msg.toString());
		}

		return feed;
	}

	public Feed fetchByTwitterScreenName(String twitterScreenName)
		throws SystemException {
		return fetchByTwitterScreenName(twitterScreenName, true);
	}

	public Feed fetchByTwitterScreenName(String twitterScreenName,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { twitterScreenName };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_TWITTERSCREENNAME,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT feed FROM Feed feed WHERE ");

				if (twitterScreenName == null) {
					query.append("feed.twitterScreenName IS NULL");
				}
				else {
					if (twitterScreenName.equals(StringPool.BLANK)) {
						query.append("(feed.twitterScreenName IS NULL OR ");
					}

					query.append("feed.twitterScreenName = ?");

					if (twitterScreenName.equals(StringPool.BLANK)) {
						query.append(")");
					}
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (twitterScreenName != null) {
					qPos.add(twitterScreenName);
				}

				List<Feed> list = q.list();

				result = list;

				Feed feed = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TWITTERSCREENNAME,
						finderArgs, list);
				}
				else {
					feed = list.get(0);

					cacheResult(feed);

					if ((feed.getTwitterScreenName() == null) ||
							!feed.getTwitterScreenName()
									 .equals(twitterScreenName)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TWITTERSCREENNAME,
							finderArgs, feed);
					}
				}

				return feed;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TWITTERSCREENNAME,
						finderArgs, new ArrayList<Feed>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (Feed)result;
			}
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

	public List<Feed> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<Feed> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	public List<Feed> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<Feed> list = (List<Feed>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT feed FROM Feed feed ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("feed.");
						query.append(orderByFields[i]);

						if (obc.isAscending()) {
							query.append(" ASC");
						}
						else {
							query.append(" DESC");
						}

						if ((i + 1) < orderByFields.length) {
							query.append(", ");
						}
					}
				}

				Query q = session.createQuery(query.toString());

				if (obc == null) {
					list = (List<Feed>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Feed>)QueryUtil.list(q, getDialect(), start,
							end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Feed>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByTwitterUserId(long twitterUserId)
		throws NoSuchFeedException, SystemException {
		Feed feed = findByTwitterUserId(twitterUserId);

		remove(feed);
	}

	public void removeByTwitterScreenName(String twitterScreenName)
		throws NoSuchFeedException, SystemException {
		Feed feed = findByTwitterScreenName(twitterScreenName);

		remove(feed);
	}

	public void removeAll() throws SystemException {
		for (Feed feed : findAll()) {
			remove(feed);
		}
	}

	public int countByTwitterUserId(long twitterUserId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(twitterUserId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TWITTERUSERID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(feed) ");
				query.append("FROM Feed feed WHERE ");

				query.append("feed.twitterUserId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(twitterUserId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TWITTERUSERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByTwitterScreenName(String twitterScreenName)
		throws SystemException {
		Object[] finderArgs = new Object[] { twitterScreenName };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TWITTERSCREENNAME,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(feed) ");
				query.append("FROM Feed feed WHERE ");

				if (twitterScreenName == null) {
					query.append("feed.twitterScreenName IS NULL");
				}
				else {
					if (twitterScreenName.equals(StringPool.BLANK)) {
						query.append("(feed.twitterScreenName IS NULL OR ");
					}

					query.append("feed.twitterScreenName = ?");

					if (twitterScreenName.equals(StringPool.BLANK)) {
						query.append(")");
					}
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (twitterScreenName != null) {
					qPos.add(twitterScreenName);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TWITTERSCREENNAME,
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

				Query q = session.createQuery(
						"SELECT COUNT(feed) FROM Feed feed");

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
						"value.object.listener.com.liferay.twitter.model.Feed")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Feed>> listenersList = new ArrayList<ModelListener<Feed>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Feed>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(name = "com.liferay.twitter.service.persistence.FeedPersistence")
	protected com.liferay.twitter.service.persistence.FeedPersistence feedPersistence;
	private static Log _log = LogFactoryUtil.getLog(FeedPersistenceImpl.class);
}