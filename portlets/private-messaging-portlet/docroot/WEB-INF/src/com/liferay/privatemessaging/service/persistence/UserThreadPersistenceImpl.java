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

package com.liferay.privatemessaging.service.persistence;

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

import com.liferay.privatemessaging.NoSuchUserThreadException;
import com.liferay.privatemessaging.model.UserThread;
import com.liferay.privatemessaging.model.impl.UserThreadImpl;
import com.liferay.privatemessaging.model.impl.UserThreadModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the user thread service.
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link UserThreadUtil} to access the user thread persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserThreadPersistence
 * @see UserThreadUtil
 * @generated
 */
public class UserThreadPersistenceImpl extends BasePersistenceImpl<UserThread>
	implements UserThreadPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = UserThreadImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_USERID = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByUserId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_MBTHREADID = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByMBThreadId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_MBTHREADID = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByMBThreadId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_U_R = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByU_R",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_U_R = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByU_R",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	/**
	 * Caches the user thread in the entity cache if it is enabled.
	 *
	 * @param userThread the user thread to cache
	 */
	public void cacheResult(UserThread userThread) {
		EntityCacheUtil.putResult(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadImpl.class, userThread.getPrimaryKey(), userThread);
	}

	/**
	 * Caches the user threads in the entity cache if it is enabled.
	 *
	 * @param userThreads the user threads to cache
	 */
	public void cacheResult(List<UserThread> userThreads) {
		for (UserThread userThread : userThreads) {
			if (EntityCacheUtil.getResult(
						UserThreadModelImpl.ENTITY_CACHE_ENABLED,
						UserThreadImpl.class, userThread.getPrimaryKey(), this) == null) {
				cacheResult(userThread);
			}
		}
	}

	/**
	 * Clears the cache for all user threads.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(UserThreadImpl.class.getName());
		EntityCacheUtil.clearCache(UserThreadImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the user thread.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(UserThread userThread) {
		EntityCacheUtil.removeResult(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadImpl.class, userThread.getPrimaryKey());
	}

	/**
	 * Creates a new user thread with the primary key. Does not add the user thread to the database.
	 *
	 * @param userThreadId the primary key for the new user thread
	 * @return the new user thread
	 */
	public UserThread create(long userThreadId) {
		UserThread userThread = new UserThreadImpl();

		userThread.setNew(true);
		userThread.setPrimaryKey(userThreadId);

		return userThread;
	}

	/**
	 * Removes the user thread with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the user thread to remove
	 * @return the user thread that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a user thread with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserThread remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the user thread with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userThreadId the primary key of the user thread to remove
	 * @return the user thread that was removed
	 * @throws com.liferay.privatemessaging.NoSuchUserThreadException if a user thread with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserThread remove(long userThreadId)
		throws NoSuchUserThreadException, SystemException {
		Session session = null;

		try {
			session = openSession();

			UserThread userThread = (UserThread)session.get(UserThreadImpl.class,
					new Long(userThreadId));

			if (userThread == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + userThreadId);
				}

				throw new NoSuchUserThreadException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					userThreadId);
			}

			return remove(userThread);
		}
		catch (NoSuchUserThreadException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected UserThread removeImpl(UserThread userThread)
		throws SystemException {
		userThread = toUnwrappedModel(userThread);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, userThread);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadImpl.class, userThread.getPrimaryKey());

		return userThread;
	}

	public UserThread updateImpl(
		com.liferay.privatemessaging.model.UserThread userThread, boolean merge)
		throws SystemException {
		userThread = toUnwrappedModel(userThread);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, userThread, merge);

			userThread.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadImpl.class, userThread.getPrimaryKey(), userThread);

		return userThread;
	}

	protected UserThread toUnwrappedModel(UserThread userThread) {
		if (userThread instanceof UserThreadImpl) {
			return userThread;
		}

		UserThreadImpl userThreadImpl = new UserThreadImpl();

		userThreadImpl.setNew(userThread.isNew());
		userThreadImpl.setPrimaryKey(userThread.getPrimaryKey());

		userThreadImpl.setUserThreadId(userThread.getUserThreadId());
		userThreadImpl.setUserId(userThread.getUserId());
		userThreadImpl.setMbThreadId(userThread.getMbThreadId());
		userThreadImpl.setRead(userThread.isRead());

		return userThreadImpl;
	}

	/**
	 * Finds the user thread with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the user thread to find
	 * @return the user thread
	 * @throws com.liferay.portal.NoSuchModelException if a user thread with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserThread findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the user thread with the primary key or throws a {@link com.liferay.privatemessaging.NoSuchUserThreadException} if it could not be found.
	 *
	 * @param userThreadId the primary key of the user thread to find
	 * @return the user thread
	 * @throws com.liferay.privatemessaging.NoSuchUserThreadException if a user thread with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserThread findByPrimaryKey(long userThreadId)
		throws NoSuchUserThreadException, SystemException {
		UserThread userThread = fetchByPrimaryKey(userThreadId);

		if (userThread == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + userThreadId);
			}

			throw new NoSuchUserThreadException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				userThreadId);
		}

		return userThread;
	}

	/**
	 * Finds the user thread with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the user thread to find
	 * @return the user thread, or <code>null</code> if a user thread with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserThread fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the user thread with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userThreadId the primary key of the user thread to find
	 * @return the user thread, or <code>null</code> if a user thread with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserThread fetchByPrimaryKey(long userThreadId)
		throws SystemException {
		UserThread userThread = (UserThread)EntityCacheUtil.getResult(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
				UserThreadImpl.class, userThreadId, this);

		if (userThread == null) {
			Session session = null;

			try {
				session = openSession();

				userThread = (UserThread)session.get(UserThreadImpl.class,
						new Long(userThreadId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (userThread != null) {
					cacheResult(userThread);
				}

				closeSession(session);
			}
		}

		return userThread;
	}

	/**
	 * Finds all the user threads where userId = &#63;.
	 *
	 * @param userId the user id to search with
	 * @return the matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserThread> findByUserId(long userId) throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the user threads where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user id to search with
	 * @param start the lower bound of the range of user threads to return
	 * @param end the upper bound of the range of user threads to return (not inclusive)
	 * @return the range of matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserThread> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the user threads where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user id to search with
	 * @param start the lower bound of the range of user threads to return
	 * @param end the upper bound of the range of user threads to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserThread> findByUserId(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				userId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<UserThread> list = (List<UserThread>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_USERID,
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
					query = new StringBundler(2);
				}

				query.append(_SQL_SELECT_USERTHREAD_WHERE);

				query.append(_FINDER_COLUMN_USERID_USERID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = (List<UserThread>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<UserThread>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_USERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first user thread in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching user thread
	 * @throws com.liferay.privatemessaging.NoSuchUserThreadException if a matching user thread could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserThread findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchUserThreadException, SystemException {
		List<UserThread> list = findByUserId(userId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchUserThreadException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last user thread in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching user thread
	 * @throws com.liferay.privatemessaging.NoSuchUserThreadException if a matching user thread could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserThread findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchUserThreadException, SystemException {
		int count = countByUserId(userId);

		List<UserThread> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchUserThreadException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the user threads before and after the current user thread in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userThreadId the primary key of the current user thread
	 * @param userId the user id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next user thread
	 * @throws com.liferay.privatemessaging.NoSuchUserThreadException if a user thread with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserThread[] findByUserId_PrevAndNext(long userThreadId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchUserThreadException, SystemException {
		UserThread userThread = findByPrimaryKey(userThreadId);

		Session session = null;

		try {
			session = openSession();

			UserThread[] array = new UserThreadImpl[3];

			array[0] = getByUserId_PrevAndNext(session, userThread, userId,
					orderByComparator, true);

			array[1] = userThread;

			array[2] = getByUserId_PrevAndNext(session, userThread, userId,
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

	protected UserThread getByUserId_PrevAndNext(Session session,
		UserThread userThread, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_USERTHREAD_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(userThread);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UserThread> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the user threads where mbThreadId = &#63;.
	 *
	 * @param mbThreadId the mb thread id to search with
	 * @return the matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserThread> findByMBThreadId(long mbThreadId)
		throws SystemException {
		return findByMBThreadId(mbThreadId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the user threads where mbThreadId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param mbThreadId the mb thread id to search with
	 * @param start the lower bound of the range of user threads to return
	 * @param end the upper bound of the range of user threads to return (not inclusive)
	 * @return the range of matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserThread> findByMBThreadId(long mbThreadId, int start, int end)
		throws SystemException {
		return findByMBThreadId(mbThreadId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the user threads where mbThreadId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param mbThreadId the mb thread id to search with
	 * @param start the lower bound of the range of user threads to return
	 * @param end the upper bound of the range of user threads to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserThread> findByMBThreadId(long mbThreadId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				mbThreadId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<UserThread> list = (List<UserThread>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_MBTHREADID,
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
					query = new StringBundler(2);
				}

				query.append(_SQL_SELECT_USERTHREAD_WHERE);

				query.append(_FINDER_COLUMN_MBTHREADID_MBTHREADID_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(mbThreadId);

				list = (List<UserThread>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<UserThread>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_MBTHREADID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first user thread in the ordered set where mbThreadId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param mbThreadId the mb thread id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching user thread
	 * @throws com.liferay.privatemessaging.NoSuchUserThreadException if a matching user thread could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserThread findByMBThreadId_First(long mbThreadId,
		OrderByComparator orderByComparator)
		throws NoSuchUserThreadException, SystemException {
		List<UserThread> list = findByMBThreadId(mbThreadId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("mbThreadId=");
			msg.append(mbThreadId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchUserThreadException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last user thread in the ordered set where mbThreadId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param mbThreadId the mb thread id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching user thread
	 * @throws com.liferay.privatemessaging.NoSuchUserThreadException if a matching user thread could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserThread findByMBThreadId_Last(long mbThreadId,
		OrderByComparator orderByComparator)
		throws NoSuchUserThreadException, SystemException {
		int count = countByMBThreadId(mbThreadId);

		List<UserThread> list = findByMBThreadId(mbThreadId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("mbThreadId=");
			msg.append(mbThreadId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchUserThreadException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the user threads before and after the current user thread in the ordered set where mbThreadId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userThreadId the primary key of the current user thread
	 * @param mbThreadId the mb thread id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next user thread
	 * @throws com.liferay.privatemessaging.NoSuchUserThreadException if a user thread with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserThread[] findByMBThreadId_PrevAndNext(long userThreadId,
		long mbThreadId, OrderByComparator orderByComparator)
		throws NoSuchUserThreadException, SystemException {
		UserThread userThread = findByPrimaryKey(userThreadId);

		Session session = null;

		try {
			session = openSession();

			UserThread[] array = new UserThreadImpl[3];

			array[0] = getByMBThreadId_PrevAndNext(session, userThread,
					mbThreadId, orderByComparator, true);

			array[1] = userThread;

			array[2] = getByMBThreadId_PrevAndNext(session, userThread,
					mbThreadId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected UserThread getByMBThreadId_PrevAndNext(Session session,
		UserThread userThread, long mbThreadId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_USERTHREAD_WHERE);

		query.append(_FINDER_COLUMN_MBTHREADID_MBTHREADID_2);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(mbThreadId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(userThread);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UserThread> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the user threads where userId = &#63; and read = &#63;.
	 *
	 * @param userId the user id to search with
	 * @param read the read to search with
	 * @return the matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserThread> findByU_R(long userId, boolean read)
		throws SystemException {
		return findByU_R(userId, read, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Finds a range of all the user threads where userId = &#63; and read = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user id to search with
	 * @param read the read to search with
	 * @param start the lower bound of the range of user threads to return
	 * @param end the upper bound of the range of user threads to return (not inclusive)
	 * @return the range of matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserThread> findByU_R(long userId, boolean read, int start,
		int end) throws SystemException {
		return findByU_R(userId, read, start, end, null);
	}

	/**
	 * Finds an ordered range of all the user threads where userId = &#63; and read = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user id to search with
	 * @param read the read to search with
	 * @param start the lower bound of the range of user threads to return
	 * @param end the upper bound of the range of user threads to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserThread> findByU_R(long userId, boolean read, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				userId, read,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<UserThread> list = (List<UserThread>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_U_R,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (orderByComparator != null) {
					query = new StringBundler(4 +
							(orderByComparator.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(3);
				}

				query.append(_SQL_SELECT_USERTHREAD_WHERE);

				query.append(_FINDER_COLUMN_U_R_USERID_2);

				query.append(_FINDER_COLUMN_U_R_READ_2);

				if (orderByComparator != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(read);

				list = (List<UserThread>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<UserThread>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_U_R, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first user thread in the ordered set where userId = &#63; and read = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user id to search with
	 * @param read the read to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching user thread
	 * @throws com.liferay.privatemessaging.NoSuchUserThreadException if a matching user thread could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserThread findByU_R_First(long userId, boolean read,
		OrderByComparator orderByComparator)
		throws NoSuchUserThreadException, SystemException {
		List<UserThread> list = findByU_R(userId, read, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", read=");
			msg.append(read);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchUserThreadException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last user thread in the ordered set where userId = &#63; and read = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user id to search with
	 * @param read the read to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching user thread
	 * @throws com.liferay.privatemessaging.NoSuchUserThreadException if a matching user thread could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserThread findByU_R_Last(long userId, boolean read,
		OrderByComparator orderByComparator)
		throws NoSuchUserThreadException, SystemException {
		int count = countByU_R(userId, read);

		List<UserThread> list = findByU_R(userId, read, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", read=");
			msg.append(read);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchUserThreadException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the user threads before and after the current user thread in the ordered set where userId = &#63; and read = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userThreadId the primary key of the current user thread
	 * @param userId the user id to search with
	 * @param read the read to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next user thread
	 * @throws com.liferay.privatemessaging.NoSuchUserThreadException if a user thread with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserThread[] findByU_R_PrevAndNext(long userThreadId, long userId,
		boolean read, OrderByComparator orderByComparator)
		throws NoSuchUserThreadException, SystemException {
		UserThread userThread = findByPrimaryKey(userThreadId);

		Session session = null;

		try {
			session = openSession();

			UserThread[] array = new UserThreadImpl[3];

			array[0] = getByU_R_PrevAndNext(session, userThread, userId, read,
					orderByComparator, true);

			array[1] = userThread;

			array[2] = getByU_R_PrevAndNext(session, userThread, userId, read,
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

	protected UserThread getByU_R_PrevAndNext(Session session,
		UserThread userThread, long userId, boolean read,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_USERTHREAD_WHERE);

		query.append(_FINDER_COLUMN_U_R_USERID_2);

		query.append(_FINDER_COLUMN_U_R_READ_2);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(read);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(userThread);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UserThread> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the user threads.
	 *
	 * @return the user threads
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserThread> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the user threads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of user threads to return
	 * @param end the upper bound of the range of user threads to return (not inclusive)
	 * @return the range of user threads
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserThread> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the user threads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of user threads to return
	 * @param end the upper bound of the range of user threads to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of user threads
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserThread> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<UserThread> list = (List<UserThread>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_USERTHREAD);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_USERTHREAD;
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<UserThread>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<UserThread>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<UserThread>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the user threads where userId = &#63; from the database.
	 *
	 * @param userId the user id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (UserThread userThread : findByUserId(userId)) {
			remove(userThread);
		}
	}

	/**
	 * Removes all the user threads where mbThreadId = &#63; from the database.
	 *
	 * @param mbThreadId the mb thread id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByMBThreadId(long mbThreadId) throws SystemException {
		for (UserThread userThread : findByMBThreadId(mbThreadId)) {
			remove(userThread);
		}
	}

	/**
	 * Removes all the user threads where userId = &#63; and read = &#63; from the database.
	 *
	 * @param userId the user id to search with
	 * @param read the read to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_R(long userId, boolean read)
		throws SystemException {
		for (UserThread userThread : findByU_R(userId, read)) {
			remove(userThread);
		}
	}

	/**
	 * Removes all the user threads from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (UserThread userThread : findAll()) {
			remove(userThread);
		}
	}

	/**
	 * Counts all the user threads where userId = &#63;.
	 *
	 * @param userId the user id to search with
	 * @return the number of matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_USERTHREAD_WHERE);

				query.append(_FINDER_COLUMN_USERID_USERID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the user threads where mbThreadId = &#63;.
	 *
	 * @param mbThreadId the mb thread id to search with
	 * @return the number of matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public int countByMBThreadId(long mbThreadId) throws SystemException {
		Object[] finderArgs = new Object[] { mbThreadId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MBTHREADID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_USERTHREAD_WHERE);

				query.append(_FINDER_COLUMN_MBTHREADID_MBTHREADID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(mbThreadId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MBTHREADID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the user threads where userId = &#63; and read = &#63;.
	 *
	 * @param userId the user id to search with
	 * @param read the read to search with
	 * @return the number of matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_R(long userId, boolean read) throws SystemException {
		Object[] finderArgs = new Object[] { userId, read };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_R,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_COUNT_USERTHREAD_WHERE);

				query.append(_FINDER_COLUMN_U_R_USERID_2);

				query.append(_FINDER_COLUMN_U_R_READ_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(read);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_R, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the user threads.
	 *
	 * @return the number of user threads
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_USERTHREAD);

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

	/**
	 * Initializes the user thread persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.privatemessaging.model.UserThread")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<UserThread>> listenersList = new ArrayList<ModelListener<UserThread>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<UserThread>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(UserThreadImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = UserThreadPersistence.class)
	protected UserThreadPersistence userThreadPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_USERTHREAD = "SELECT userThread FROM UserThread userThread";
	private static final String _SQL_SELECT_USERTHREAD_WHERE = "SELECT userThread FROM UserThread userThread WHERE ";
	private static final String _SQL_COUNT_USERTHREAD = "SELECT COUNT(userThread) FROM UserThread userThread";
	private static final String _SQL_COUNT_USERTHREAD_WHERE = "SELECT COUNT(userThread) FROM UserThread userThread WHERE ";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "userThread.userId = ?";
	private static final String _FINDER_COLUMN_MBTHREADID_MBTHREADID_2 = "userThread.mbThreadId = ?";
	private static final String _FINDER_COLUMN_U_R_USERID_2 = "userThread.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_R_READ_2 = "userThread.read = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "userThread.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No UserThread exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No UserThread exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(UserThreadPersistenceImpl.class);
}