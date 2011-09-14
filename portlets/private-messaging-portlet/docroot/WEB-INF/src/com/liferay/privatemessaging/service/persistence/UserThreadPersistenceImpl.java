/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.model.CacheModel;
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
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link UserThreadUtil} to access the user thread persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = UserThreadImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_MBTHREADID = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, UserThreadImpl.class,
			FINDER_CLASS_NAME_LIST, "findByMBThreadId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_MBTHREADID = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByMBThreadId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_USERID = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, UserThreadImpl.class,
			FINDER_CLASS_NAME_LIST, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_U_M = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, UserThreadImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByU_M",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_U_M = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByU_M",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_U_D = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, UserThreadImpl.class,
			FINDER_CLASS_NAME_LIST, "findByU_D",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_U_D = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByU_D",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_U_R_D = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, UserThreadImpl.class,
			FINDER_CLASS_NAME_LIST, "findByU_R_D",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_U_R_D = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByU_R_D",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, UserThreadImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the user thread in the entity cache if it is enabled.
	 *
	 * @param userThread the user thread
	 */
	public void cacheResult(UserThread userThread) {
		EntityCacheUtil.putResult(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadImpl.class, userThread.getPrimaryKey(), userThread);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_M,
			new Object[] {
				Long.valueOf(userThread.getUserId()),
				Long.valueOf(userThread.getMbThreadId())
			}, userThread);

		userThread.resetOriginalValues();
	}

	/**
	 * Caches the user threads in the entity cache if it is enabled.
	 *
	 * @param userThreads the user threads
	 */
	public void cacheResult(List<UserThread> userThreads) {
		for (UserThread userThread : userThreads) {
			if (EntityCacheUtil.getResult(
						UserThreadModelImpl.ENTITY_CACHE_ENABLED,
						UserThreadImpl.class, userThread.getPrimaryKey()) == null) {
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
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(UserThreadImpl.class.getName());
		}

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
	@Override
	public void clearCache(UserThread userThread) {
		EntityCacheUtil.removeResult(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadImpl.class, userThread.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FIND_ALL, FINDER_ARGS_EMPTY);

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_M,
			new Object[] {
				Long.valueOf(userThread.getUserId()),
				Long.valueOf(userThread.getMbThreadId())
			});
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
	 * @param primaryKey the primary key of the user thread
	 * @return the user thread that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a user thread with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserThread remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the user thread with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userThreadId the primary key of the user thread
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
					Long.valueOf(userThreadId));

			if (userThread == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + userThreadId);
				}

				throw new NoSuchUserThreadException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					userThreadId);
			}

			return userThreadPersistence.remove(userThread);
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

	/**
	 * Removes the user thread from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userThread the user thread
	 * @return the user thread that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserThread remove(UserThread userThread) throws SystemException {
		return super.remove(userThread);
	}

	@Override
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

		UserThreadModelImpl userThreadModelImpl = (UserThreadModelImpl)userThread;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_M,
			new Object[] {
				Long.valueOf(userThreadModelImpl.getUserId()),
				Long.valueOf(userThreadModelImpl.getMbThreadId())
			});

		EntityCacheUtil.removeResult(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadImpl.class, userThread.getPrimaryKey());

		return userThread;
	}

	@Override
	public UserThread updateImpl(
		com.liferay.privatemessaging.model.UserThread userThread, boolean merge)
		throws SystemException {
		userThread = toUnwrappedModel(userThread);

		boolean isNew = userThread.isNew();

		UserThreadModelImpl userThreadModelImpl = (UserThreadModelImpl)userThread;

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

		if (!isNew &&
				((userThread.getUserId() != userThreadModelImpl.getOriginalUserId()) ||
				(userThread.getMbThreadId() != userThreadModelImpl.getOriginalMbThreadId()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_M,
				new Object[] {
					Long.valueOf(userThreadModelImpl.getOriginalUserId()),
					Long.valueOf(userThreadModelImpl.getOriginalMbThreadId())
				});
		}

		if (isNew ||
				((userThread.getUserId() != userThreadModelImpl.getOriginalUserId()) ||
				(userThread.getMbThreadId() != userThreadModelImpl.getOriginalMbThreadId()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_M,
				new Object[] {
					Long.valueOf(userThread.getUserId()),
					Long.valueOf(userThread.getMbThreadId())
				}, userThread);
		}

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
		userThreadImpl.setCompanyId(userThread.getCompanyId());
		userThreadImpl.setUserId(userThread.getUserId());
		userThreadImpl.setCreateDate(userThread.getCreateDate());
		userThreadImpl.setModifiedDate(userThread.getModifiedDate());
		userThreadImpl.setMbThreadId(userThread.getMbThreadId());
		userThreadImpl.setTopMBMessageId(userThread.getTopMBMessageId());
		userThreadImpl.setRead(userThread.isRead());
		userThreadImpl.setDeleted(userThread.isDeleted());

		return userThreadImpl;
	}

	/**
	 * Returns the user thread with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the user thread
	 * @return the user thread
	 * @throws com.liferay.portal.NoSuchModelException if a user thread with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserThread findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the user thread with the primary key or throws a {@link com.liferay.privatemessaging.NoSuchUserThreadException} if it could not be found.
	 *
	 * @param userThreadId the primary key of the user thread
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
	 * Returns the user thread with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the user thread
	 * @return the user thread, or <code>null</code> if a user thread with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserThread fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the user thread with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userThreadId the primary key of the user thread
	 * @return the user thread, or <code>null</code> if a user thread with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserThread fetchByPrimaryKey(long userThreadId)
		throws SystemException {
		UserThread userThread = (UserThread)EntityCacheUtil.getResult(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
				UserThreadImpl.class, userThreadId);

		if (userThread == _nullUserThread) {
			return null;
		}

		if (userThread == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				userThread = (UserThread)session.get(UserThreadImpl.class,
						Long.valueOf(userThreadId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (userThread != null) {
					cacheResult(userThread);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
						UserThreadImpl.class, userThreadId, _nullUserThread);
				}

				closeSession(session);
			}
		}

		return userThread;
	}

	/**
	 * Returns all the user threads where mbThreadId = &#63;.
	 *
	 * @param mbThreadId the mb thread ID
	 * @return the matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserThread> findByMBThreadId(long mbThreadId)
		throws SystemException {
		return findByMBThreadId(mbThreadId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user threads where mbThreadId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param mbThreadId the mb thread ID
	 * @param start the lower bound of the range of user threads
	 * @param end the upper bound of the range of user threads (not inclusive)
	 * @return the range of matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserThread> findByMBThreadId(long mbThreadId, int start, int end)
		throws SystemException {
		return findByMBThreadId(mbThreadId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user threads where mbThreadId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param mbThreadId the mb thread ID
	 * @param start the lower bound of the range of user threads
	 * @param end the upper bound of the range of user threads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserThread> findByMBThreadId(long mbThreadId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				mbThreadId,
				
				start, end, orderByComparator
			};

		List<UserThread> list = (List<UserThread>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_MBTHREADID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_USERTHREAD_WHERE);

			query.append(_FINDER_COLUMN_MBTHREADID_MBTHREADID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(UserThreadModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

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
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_MBTHREADID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_MBTHREADID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first user thread in the ordered set where mbThreadId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param mbThreadId the mb thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
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
	 * Returns the last user thread in the ordered set where mbThreadId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param mbThreadId the mb thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
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
	 * Returns the user threads before and after the current user thread in the ordered set where mbThreadId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userThreadId the primary key of the current user thread
	 * @param mbThreadId the mb thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
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

		else {
			query.append(UserThreadModelImpl.ORDER_BY_JPQL);
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
	 * Returns all the user threads where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserThread> findByUserId(long userId) throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user threads where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of user threads
	 * @param end the upper bound of the range of user threads (not inclusive)
	 * @return the range of matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserThread> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user threads where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of user threads
	 * @param end the upper bound of the range of user threads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserThread> findByUserId(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] { userId, start, end, orderByComparator };

		List<UserThread> list = (List<UserThread>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_USERID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_USERTHREAD_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(UserThreadModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

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
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_USERID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_USERID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first user thread in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
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
	 * Returns the last user thread in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
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
	 * Returns the user threads before and after the current user thread in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userThreadId the primary key of the current user thread
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
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

		else {
			query.append(UserThreadModelImpl.ORDER_BY_JPQL);
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
	 * Returns the user thread where userId = &#63; and mbThreadId = &#63; or throws a {@link com.liferay.privatemessaging.NoSuchUserThreadException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param mbThreadId the mb thread ID
	 * @return the matching user thread
	 * @throws com.liferay.privatemessaging.NoSuchUserThreadException if a matching user thread could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserThread findByU_M(long userId, long mbThreadId)
		throws NoSuchUserThreadException, SystemException {
		UserThread userThread = fetchByU_M(userId, mbThreadId);

		if (userThread == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", mbThreadId=");
			msg.append(mbThreadId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchUserThreadException(msg.toString());
		}

		return userThread;
	}

	/**
	 * Returns the user thread where userId = &#63; and mbThreadId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param mbThreadId the mb thread ID
	 * @return the matching user thread, or <code>null</code> if a matching user thread could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserThread fetchByU_M(long userId, long mbThreadId)
		throws SystemException {
		return fetchByU_M(userId, mbThreadId, true);
	}

	/**
	 * Returns the user thread where userId = &#63; and mbThreadId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param mbThreadId the mb thread ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching user thread, or <code>null</code> if a matching user thread could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserThread fetchByU_M(long userId, long mbThreadId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { userId, mbThreadId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_U_M,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_USERTHREAD_WHERE);

			query.append(_FINDER_COLUMN_U_M_USERID_2);

			query.append(_FINDER_COLUMN_U_M_MBTHREADID_2);

			query.append(UserThreadModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(mbThreadId);

				List<UserThread> list = q.list();

				result = list;

				UserThread userThread = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_M,
						finderArgs, list);
				}
				else {
					userThread = list.get(0);

					cacheResult(userThread);

					if ((userThread.getUserId() != userId) ||
							(userThread.getMbThreadId() != mbThreadId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_M,
							finderArgs, userThread);
					}
				}

				return userThread;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_M,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (UserThread)result;
			}
		}
	}

	/**
	 * Returns all the user threads where userId = &#63; and deleted = &#63;.
	 *
	 * @param userId the user ID
	 * @param deleted the deleted
	 * @return the matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserThread> findByU_D(long userId, boolean deleted)
		throws SystemException {
		return findByU_D(userId, deleted, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the user threads where userId = &#63; and deleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param deleted the deleted
	 * @param start the lower bound of the range of user threads
	 * @param end the upper bound of the range of user threads (not inclusive)
	 * @return the range of matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserThread> findByU_D(long userId, boolean deleted, int start,
		int end) throws SystemException {
		return findByU_D(userId, deleted, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user threads where userId = &#63; and deleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param deleted the deleted
	 * @param start the lower bound of the range of user threads
	 * @param end the upper bound of the range of user threads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserThread> findByU_D(long userId, boolean deleted, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				userId, deleted,
				
				start, end, orderByComparator
			};

		List<UserThread> list = (List<UserThread>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_U_D,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_USERTHREAD_WHERE);

			query.append(_FINDER_COLUMN_U_D_USERID_2);

			query.append(_FINDER_COLUMN_U_D_DELETED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(UserThreadModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(deleted);

				list = (List<UserThread>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_U_D,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_U_D,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first user thread in the ordered set where userId = &#63; and deleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param deleted the deleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user thread
	 * @throws com.liferay.privatemessaging.NoSuchUserThreadException if a matching user thread could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserThread findByU_D_First(long userId, boolean deleted,
		OrderByComparator orderByComparator)
		throws NoSuchUserThreadException, SystemException {
		List<UserThread> list = findByU_D(userId, deleted, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", deleted=");
			msg.append(deleted);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchUserThreadException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last user thread in the ordered set where userId = &#63; and deleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param deleted the deleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user thread
	 * @throws com.liferay.privatemessaging.NoSuchUserThreadException if a matching user thread could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserThread findByU_D_Last(long userId, boolean deleted,
		OrderByComparator orderByComparator)
		throws NoSuchUserThreadException, SystemException {
		int count = countByU_D(userId, deleted);

		List<UserThread> list = findByU_D(userId, deleted, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", deleted=");
			msg.append(deleted);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchUserThreadException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the user threads before and after the current user thread in the ordered set where userId = &#63; and deleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userThreadId the primary key of the current user thread
	 * @param userId the user ID
	 * @param deleted the deleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user thread
	 * @throws com.liferay.privatemessaging.NoSuchUserThreadException if a user thread with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserThread[] findByU_D_PrevAndNext(long userThreadId, long userId,
		boolean deleted, OrderByComparator orderByComparator)
		throws NoSuchUserThreadException, SystemException {
		UserThread userThread = findByPrimaryKey(userThreadId);

		Session session = null;

		try {
			session = openSession();

			UserThread[] array = new UserThreadImpl[3];

			array[0] = getByU_D_PrevAndNext(session, userThread, userId,
					deleted, orderByComparator, true);

			array[1] = userThread;

			array[2] = getByU_D_PrevAndNext(session, userThread, userId,
					deleted, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected UserThread getByU_D_PrevAndNext(Session session,
		UserThread userThread, long userId, boolean deleted,
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

		query.append(_FINDER_COLUMN_U_D_USERID_2);

		query.append(_FINDER_COLUMN_U_D_DELETED_2);

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
			query.append(UserThreadModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(deleted);

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
	 * Returns all the user threads where userId = &#63; and read = &#63; and deleted = &#63;.
	 *
	 * @param userId the user ID
	 * @param read the read
	 * @param deleted the deleted
	 * @return the matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserThread> findByU_R_D(long userId, boolean read,
		boolean deleted) throws SystemException {
		return findByU_R_D(userId, read, deleted, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user threads where userId = &#63; and read = &#63; and deleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param read the read
	 * @param deleted the deleted
	 * @param start the lower bound of the range of user threads
	 * @param end the upper bound of the range of user threads (not inclusive)
	 * @return the range of matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserThread> findByU_R_D(long userId, boolean read,
		boolean deleted, int start, int end) throws SystemException {
		return findByU_R_D(userId, read, deleted, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user threads where userId = &#63; and read = &#63; and deleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param read the read
	 * @param deleted the deleted
	 * @param start the lower bound of the range of user threads
	 * @param end the upper bound of the range of user threads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserThread> findByU_R_D(long userId, boolean read,
		boolean deleted, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				userId, read, deleted,
				
				start, end, orderByComparator
			};

		List<UserThread> list = (List<UserThread>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_U_R_D,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_USERTHREAD_WHERE);

			query.append(_FINDER_COLUMN_U_R_D_USERID_2);

			query.append(_FINDER_COLUMN_U_R_D_READ_2);

			query.append(_FINDER_COLUMN_U_R_D_DELETED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(UserThreadModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(read);

				qPos.add(deleted);

				list = (List<UserThread>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_U_R_D,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_U_R_D,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first user thread in the ordered set where userId = &#63; and read = &#63; and deleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param read the read
	 * @param deleted the deleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user thread
	 * @throws com.liferay.privatemessaging.NoSuchUserThreadException if a matching user thread could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserThread findByU_R_D_First(long userId, boolean read,
		boolean deleted, OrderByComparator orderByComparator)
		throws NoSuchUserThreadException, SystemException {
		List<UserThread> list = findByU_R_D(userId, read, deleted, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", read=");
			msg.append(read);

			msg.append(", deleted=");
			msg.append(deleted);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchUserThreadException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last user thread in the ordered set where userId = &#63; and read = &#63; and deleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param read the read
	 * @param deleted the deleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user thread
	 * @throws com.liferay.privatemessaging.NoSuchUserThreadException if a matching user thread could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserThread findByU_R_D_Last(long userId, boolean read,
		boolean deleted, OrderByComparator orderByComparator)
		throws NoSuchUserThreadException, SystemException {
		int count = countByU_R_D(userId, read, deleted);

		List<UserThread> list = findByU_R_D(userId, read, deleted, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", read=");
			msg.append(read);

			msg.append(", deleted=");
			msg.append(deleted);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchUserThreadException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the user threads before and after the current user thread in the ordered set where userId = &#63; and read = &#63; and deleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userThreadId the primary key of the current user thread
	 * @param userId the user ID
	 * @param read the read
	 * @param deleted the deleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user thread
	 * @throws com.liferay.privatemessaging.NoSuchUserThreadException if a user thread with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserThread[] findByU_R_D_PrevAndNext(long userThreadId, long userId,
		boolean read, boolean deleted, OrderByComparator orderByComparator)
		throws NoSuchUserThreadException, SystemException {
		UserThread userThread = findByPrimaryKey(userThreadId);

		Session session = null;

		try {
			session = openSession();

			UserThread[] array = new UserThreadImpl[3];

			array[0] = getByU_R_D_PrevAndNext(session, userThread, userId,
					read, deleted, orderByComparator, true);

			array[1] = userThread;

			array[2] = getByU_R_D_PrevAndNext(session, userThread, userId,
					read, deleted, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected UserThread getByU_R_D_PrevAndNext(Session session,
		UserThread userThread, long userId, boolean read, boolean deleted,
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

		query.append(_FINDER_COLUMN_U_R_D_USERID_2);

		query.append(_FINDER_COLUMN_U_R_D_READ_2);

		query.append(_FINDER_COLUMN_U_R_D_DELETED_2);

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
			query.append(UserThreadModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(read);

		qPos.add(deleted);

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
	 * Returns all the user threads.
	 *
	 * @return the user threads
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserThread> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user threads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of user threads
	 * @param end the upper bound of the range of user threads (not inclusive)
	 * @return the range of user threads
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserThread> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the user threads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of user threads
	 * @param end the upper bound of the range of user threads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user threads
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserThread> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		List<UserThread> list = (List<UserThread>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
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
				sql = _SQL_SELECT_USERTHREAD.concat(UserThreadModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

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
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_ALL,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs,
						list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the user threads where mbThreadId = &#63; from the database.
	 *
	 * @param mbThreadId the mb thread ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByMBThreadId(long mbThreadId) throws SystemException {
		for (UserThread userThread : findByMBThreadId(mbThreadId)) {
			userThreadPersistence.remove(userThread);
		}
	}

	/**
	 * Removes all the user threads where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (UserThread userThread : findByUserId(userId)) {
			userThreadPersistence.remove(userThread);
		}
	}

	/**
	 * Removes the user thread where userId = &#63; and mbThreadId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param mbThreadId the mb thread ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_M(long userId, long mbThreadId)
		throws NoSuchUserThreadException, SystemException {
		UserThread userThread = findByU_M(userId, mbThreadId);

		userThreadPersistence.remove(userThread);
	}

	/**
	 * Removes all the user threads where userId = &#63; and deleted = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param deleted the deleted
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_D(long userId, boolean deleted)
		throws SystemException {
		for (UserThread userThread : findByU_D(userId, deleted)) {
			userThreadPersistence.remove(userThread);
		}
	}

	/**
	 * Removes all the user threads where userId = &#63; and read = &#63; and deleted = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param read the read
	 * @param deleted the deleted
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_R_D(long userId, boolean read, boolean deleted)
		throws SystemException {
		for (UserThread userThread : findByU_R_D(userId, read, deleted)) {
			userThreadPersistence.remove(userThread);
		}
	}

	/**
	 * Removes all the user threads from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (UserThread userThread : findAll()) {
			userThreadPersistence.remove(userThread);
		}
	}

	/**
	 * Returns the number of user threads where mbThreadId = &#63;.
	 *
	 * @param mbThreadId the mb thread ID
	 * @return the number of matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public int countByMBThreadId(long mbThreadId) throws SystemException {
		Object[] finderArgs = new Object[] { mbThreadId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MBTHREADID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USERTHREAD_WHERE);

			query.append(_FINDER_COLUMN_MBTHREADID_MBTHREADID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

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
	 * Returns the number of user threads where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USERTHREAD_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

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
	 * Returns the number of user threads where userId = &#63; and mbThreadId = &#63;.
	 *
	 * @param userId the user ID
	 * @param mbThreadId the mb thread ID
	 * @return the number of matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_M(long userId, long mbThreadId)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, mbThreadId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_M,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_USERTHREAD_WHERE);

			query.append(_FINDER_COLUMN_U_M_USERID_2);

			query.append(_FINDER_COLUMN_U_M_MBTHREADID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_M, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of user threads where userId = &#63; and deleted = &#63;.
	 *
	 * @param userId the user ID
	 * @param deleted the deleted
	 * @return the number of matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_D(long userId, boolean deleted)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, deleted };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_D,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_USERTHREAD_WHERE);

			query.append(_FINDER_COLUMN_U_D_USERID_2);

			query.append(_FINDER_COLUMN_U_D_DELETED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(deleted);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_D, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of user threads where userId = &#63; and read = &#63; and deleted = &#63;.
	 *
	 * @param userId the user ID
	 * @param read the read
	 * @param deleted the deleted
	 * @return the number of matching user threads
	 * @throws SystemException if a system exception occurred
	 */
	public int countByU_R_D(long userId, boolean read, boolean deleted)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId, read, deleted };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_R_D,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_USERTHREAD_WHERE);

			query.append(_FINDER_COLUMN_U_R_D_USERID_2);

			query.append(_FINDER_COLUMN_U_R_D_READ_2);

			query.append(_FINDER_COLUMN_U_R_D_DELETED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(read);

				qPos.add(deleted);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_R_D,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of user threads.
	 *
	 * @return the number of user threads
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

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
	private static final String _FINDER_COLUMN_MBTHREADID_MBTHREADID_2 = "userThread.mbThreadId = ?";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "userThread.userId = ?";
	private static final String _FINDER_COLUMN_U_M_USERID_2 = "userThread.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_M_MBTHREADID_2 = "userThread.mbThreadId = ?";
	private static final String _FINDER_COLUMN_U_D_USERID_2 = "userThread.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_D_DELETED_2 = "userThread.deleted = ?";
	private static final String _FINDER_COLUMN_U_R_D_USERID_2 = "userThread.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_R_D_READ_2 = "userThread.read = ? AND ";
	private static final String _FINDER_COLUMN_U_R_D_DELETED_2 = "userThread.deleted = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "userThread.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No UserThread exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No UserThread exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(UserThreadPersistenceImpl.class);
	private static UserThread _nullUserThread = new UserThreadImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<UserThread> toCacheModel() {
				return _nullUserThreadCacheModel;
			}
		};

	private static CacheModel<UserThread> _nullUserThreadCacheModel = new CacheModel<UserThread>() {
			public UserThread toEntityModel() {
				return _nullUserThread;
			}
		};
}