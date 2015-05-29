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

package com.liferay.privatemessaging.service.persistence.impl;

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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.privatemessaging.NoSuchUserThreadException;
import com.liferay.privatemessaging.model.UserThread;
import com.liferay.privatemessaging.model.impl.UserThreadImpl;
import com.liferay.privatemessaging.model.impl.UserThreadModelImpl;
import com.liferay.privatemessaging.service.persistence.UserThreadPersistence;

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
 * The persistence implementation for the user thread service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserThreadPersistence
 * @see com.liferay.privatemessaging.service.persistence.UserThreadUtil
 * @generated
 */
@ProviderType
public class UserThreadPersistenceImpl extends BasePersistenceImpl<UserThread>
	implements UserThreadPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link UserThreadUtil} to access the user thread persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = UserThreadImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, UserThreadImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, UserThreadImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MBTHREADID =
		new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, UserThreadImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByMBThreadId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MBTHREADID =
		new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, UserThreadImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByMBThreadId",
			new String[] { Long.class.getName() },
			UserThreadModelImpl.MBTHREADID_COLUMN_BITMASK |
			UserThreadModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MBTHREADID = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByMBThreadId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the user threads where mbThreadId = &#63;.
	 *
	 * @param mbThreadId the mb thread ID
	 * @return the matching user threads
	 */
	@Override
	public List<UserThread> findByMBThreadId(long mbThreadId) {
		return findByMBThreadId(mbThreadId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user threads where mbThreadId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserThreadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param mbThreadId the mb thread ID
	 * @param start the lower bound of the range of user threads
	 * @param end the upper bound of the range of user threads (not inclusive)
	 * @return the range of matching user threads
	 */
	@Override
	public List<UserThread> findByMBThreadId(long mbThreadId, int start, int end) {
		return findByMBThreadId(mbThreadId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user threads where mbThreadId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserThreadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param mbThreadId the mb thread ID
	 * @param start the lower bound of the range of user threads
	 * @param end the upper bound of the range of user threads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user threads
	 */
	@Override
	public List<UserThread> findByMBThreadId(long mbThreadId, int start,
		int end, OrderByComparator<UserThread> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MBTHREADID;
			finderArgs = new Object[] { mbThreadId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MBTHREADID;
			finderArgs = new Object[] { mbThreadId, start, end, orderByComparator };
		}

		List<UserThread> list = (List<UserThread>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (UserThread userThread : list) {
				if ((mbThreadId != userThread.getMbThreadId())) {
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

			query.append(_SQL_SELECT_USERTHREAD_WHERE);

			query.append(_FINDER_COLUMN_MBTHREADID_MBTHREADID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(UserThreadModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(mbThreadId);

				if (!pagination) {
					list = (List<UserThread>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UserThread>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first user thread in the ordered set where mbThreadId = &#63;.
	 *
	 * @param mbThreadId the mb thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user thread
	 * @throws NoSuchUserThreadException if a matching user thread could not be found
	 */
	@Override
	public UserThread findByMBThreadId_First(long mbThreadId,
		OrderByComparator<UserThread> orderByComparator)
		throws NoSuchUserThreadException {
		UserThread userThread = fetchByMBThreadId_First(mbThreadId,
				orderByComparator);

		if (userThread != null) {
			return userThread;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("mbThreadId=");
		msg.append(mbThreadId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserThreadException(msg.toString());
	}

	/**
	 * Returns the first user thread in the ordered set where mbThreadId = &#63;.
	 *
	 * @param mbThreadId the mb thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user thread, or <code>null</code> if a matching user thread could not be found
	 */
	@Override
	public UserThread fetchByMBThreadId_First(long mbThreadId,
		OrderByComparator<UserThread> orderByComparator) {
		List<UserThread> list = findByMBThreadId(mbThreadId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user thread in the ordered set where mbThreadId = &#63;.
	 *
	 * @param mbThreadId the mb thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user thread
	 * @throws NoSuchUserThreadException if a matching user thread could not be found
	 */
	@Override
	public UserThread findByMBThreadId_Last(long mbThreadId,
		OrderByComparator<UserThread> orderByComparator)
		throws NoSuchUserThreadException {
		UserThread userThread = fetchByMBThreadId_Last(mbThreadId,
				orderByComparator);

		if (userThread != null) {
			return userThread;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("mbThreadId=");
		msg.append(mbThreadId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserThreadException(msg.toString());
	}

	/**
	 * Returns the last user thread in the ordered set where mbThreadId = &#63;.
	 *
	 * @param mbThreadId the mb thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user thread, or <code>null</code> if a matching user thread could not be found
	 */
	@Override
	public UserThread fetchByMBThreadId_Last(long mbThreadId,
		OrderByComparator<UserThread> orderByComparator) {
		int count = countByMBThreadId(mbThreadId);

		if (count == 0) {
			return null;
		}

		List<UserThread> list = findByMBThreadId(mbThreadId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user threads before and after the current user thread in the ordered set where mbThreadId = &#63;.
	 *
	 * @param userThreadId the primary key of the current user thread
	 * @param mbThreadId the mb thread ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user thread
	 * @throws NoSuchUserThreadException if a user thread with the primary key could not be found
	 */
	@Override
	public UserThread[] findByMBThreadId_PrevAndNext(long userThreadId,
		long mbThreadId, OrderByComparator<UserThread> orderByComparator)
		throws NoSuchUserThreadException {
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
		OrderByComparator<UserThread> orderByComparator, boolean previous) {
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
			query.append(UserThreadModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(mbThreadId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(userThread);

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
	 * Removes all the user threads where mbThreadId = &#63; from the database.
	 *
	 * @param mbThreadId the mb thread ID
	 */
	@Override
	public void removeByMBThreadId(long mbThreadId) {
		for (UserThread userThread : findByMBThreadId(mbThreadId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(userThread);
		}
	}

	/**
	 * Returns the number of user threads where mbThreadId = &#63;.
	 *
	 * @param mbThreadId the mb thread ID
	 * @return the number of matching user threads
	 */
	@Override
	public int countByMBThreadId(long mbThreadId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MBTHREADID;

		Object[] finderArgs = new Object[] { mbThreadId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

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

	private static final String _FINDER_COLUMN_MBTHREADID_MBTHREADID_2 = "userThread.mbThreadId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, UserThreadImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, UserThreadImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			UserThreadModelImpl.USERID_COLUMN_BITMASK |
			UserThreadModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the user threads where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching user threads
	 */
	@Override
	public List<UserThread> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user threads where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserThreadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of user threads
	 * @param end the upper bound of the range of user threads (not inclusive)
	 * @return the range of matching user threads
	 */
	@Override
	public List<UserThread> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user threads where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserThreadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of user threads
	 * @param end the upper bound of the range of user threads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user threads
	 */
	@Override
	public List<UserThread> findByUserId(long userId, int start, int end,
		OrderByComparator<UserThread> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<UserThread> list = (List<UserThread>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (UserThread userThread : list) {
				if ((userId != userThread.getUserId())) {
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

			query.append(_SQL_SELECT_USERTHREAD_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(UserThreadModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<UserThread>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UserThread>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first user thread in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user thread
	 * @throws NoSuchUserThreadException if a matching user thread could not be found
	 */
	@Override
	public UserThread findByUserId_First(long userId,
		OrderByComparator<UserThread> orderByComparator)
		throws NoSuchUserThreadException {
		UserThread userThread = fetchByUserId_First(userId, orderByComparator);

		if (userThread != null) {
			return userThread;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserThreadException(msg.toString());
	}

	/**
	 * Returns the first user thread in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user thread, or <code>null</code> if a matching user thread could not be found
	 */
	@Override
	public UserThread fetchByUserId_First(long userId,
		OrderByComparator<UserThread> orderByComparator) {
		List<UserThread> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user thread in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user thread
	 * @throws NoSuchUserThreadException if a matching user thread could not be found
	 */
	@Override
	public UserThread findByUserId_Last(long userId,
		OrderByComparator<UserThread> orderByComparator)
		throws NoSuchUserThreadException {
		UserThread userThread = fetchByUserId_Last(userId, orderByComparator);

		if (userThread != null) {
			return userThread;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserThreadException(msg.toString());
	}

	/**
	 * Returns the last user thread in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user thread, or <code>null</code> if a matching user thread could not be found
	 */
	@Override
	public UserThread fetchByUserId_Last(long userId,
		OrderByComparator<UserThread> orderByComparator) {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<UserThread> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user threads before and after the current user thread in the ordered set where userId = &#63;.
	 *
	 * @param userThreadId the primary key of the current user thread
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user thread
	 * @throws NoSuchUserThreadException if a user thread with the primary key could not be found
	 */
	@Override
	public UserThread[] findByUserId_PrevAndNext(long userThreadId,
		long userId, OrderByComparator<UserThread> orderByComparator)
		throws NoSuchUserThreadException {
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
		OrderByComparator<UserThread> orderByComparator, boolean previous) {
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
			query.append(UserThreadModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(userThread);

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
	 * Removes all the user threads where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (UserThread userThread : findByUserId(userId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(userThread);
		}
	}

	/**
	 * Returns the number of user threads where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching user threads
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "userThread.userId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_U_M = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, UserThreadImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByU_M",
			new String[] { Long.class.getName(), Long.class.getName() },
			UserThreadModelImpl.USERID_COLUMN_BITMASK |
			UserThreadModelImpl.MBTHREADID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_M = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_M",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the user thread where userId = &#63; and mbThreadId = &#63; or throws a {@link NoSuchUserThreadException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param mbThreadId the mb thread ID
	 * @return the matching user thread
	 * @throws NoSuchUserThreadException if a matching user thread could not be found
	 */
	@Override
	public UserThread findByU_M(long userId, long mbThreadId)
		throws NoSuchUserThreadException {
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
	 */
	@Override
	public UserThread fetchByU_M(long userId, long mbThreadId) {
		return fetchByU_M(userId, mbThreadId, true);
	}

	/**
	 * Returns the user thread where userId = &#63; and mbThreadId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param mbThreadId the mb thread ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching user thread, or <code>null</code> if a matching user thread could not be found
	 */
	@Override
	public UserThread fetchByU_M(long userId, long mbThreadId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { userId, mbThreadId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_U_M,
					finderArgs, this);
		}

		if (result instanceof UserThread) {
			UserThread userThread = (UserThread)result;

			if ((userId != userThread.getUserId()) ||
					(mbThreadId != userThread.getMbThreadId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_USERTHREAD_WHERE);

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

				List<UserThread> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_M,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"UserThreadPersistenceImpl.fetchByU_M(long, long, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					UserThread userThread = list.get(0);

					result = userThread;

					cacheResult(userThread);

					if ((userThread.getUserId() != userId) ||
							(userThread.getMbThreadId() != mbThreadId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_M,
							finderArgs, userThread);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_M,
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
			return (UserThread)result;
		}
	}

	/**
	 * Removes the user thread where userId = &#63; and mbThreadId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param mbThreadId the mb thread ID
	 * @return the user thread that was removed
	 */
	@Override
	public UserThread removeByU_M(long userId, long mbThreadId)
		throws NoSuchUserThreadException {
		UserThread userThread = findByU_M(userId, mbThreadId);

		return remove(userThread);
	}

	/**
	 * Returns the number of user threads where userId = &#63; and mbThreadId = &#63;.
	 *
	 * @param userId the user ID
	 * @param mbThreadId the mb thread ID
	 * @return the number of matching user threads
	 */
	@Override
	public int countByU_M(long userId, long mbThreadId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_M;

		Object[] finderArgs = new Object[] { userId, mbThreadId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

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

	private static final String _FINDER_COLUMN_U_M_USERID_2 = "userThread.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_M_MBTHREADID_2 = "userThread.mbThreadId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_D = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, UserThreadImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_D",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_D = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, UserThreadImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_D",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			UserThreadModelImpl.USERID_COLUMN_BITMASK |
			UserThreadModelImpl.DELETED_COLUMN_BITMASK |
			UserThreadModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_D = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_D",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the user threads where userId = &#63; and deleted = &#63;.
	 *
	 * @param userId the user ID
	 * @param deleted the deleted
	 * @return the matching user threads
	 */
	@Override
	public List<UserThread> findByU_D(long userId, boolean deleted) {
		return findByU_D(userId, deleted, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the user threads where userId = &#63; and deleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserThreadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param deleted the deleted
	 * @param start the lower bound of the range of user threads
	 * @param end the upper bound of the range of user threads (not inclusive)
	 * @return the range of matching user threads
	 */
	@Override
	public List<UserThread> findByU_D(long userId, boolean deleted, int start,
		int end) {
		return findByU_D(userId, deleted, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user threads where userId = &#63; and deleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserThreadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param deleted the deleted
	 * @param start the lower bound of the range of user threads
	 * @param end the upper bound of the range of user threads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user threads
	 */
	@Override
	public List<UserThread> findByU_D(long userId, boolean deleted, int start,
		int end, OrderByComparator<UserThread> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_D;
			finderArgs = new Object[] { userId, deleted };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_D;
			finderArgs = new Object[] {
					userId, deleted,
					
					start, end, orderByComparator
				};
		}

		List<UserThread> list = (List<UserThread>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (UserThread userThread : list) {
				if ((userId != userThread.getUserId()) ||
						(deleted != userThread.getDeleted())) {
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

			query.append(_SQL_SELECT_USERTHREAD_WHERE);

			query.append(_FINDER_COLUMN_U_D_USERID_2);

			query.append(_FINDER_COLUMN_U_D_DELETED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
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

				if (!pagination) {
					list = (List<UserThread>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UserThread>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first user thread in the ordered set where userId = &#63; and deleted = &#63;.
	 *
	 * @param userId the user ID
	 * @param deleted the deleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user thread
	 * @throws NoSuchUserThreadException if a matching user thread could not be found
	 */
	@Override
	public UserThread findByU_D_First(long userId, boolean deleted,
		OrderByComparator<UserThread> orderByComparator)
		throws NoSuchUserThreadException {
		UserThread userThread = fetchByU_D_First(userId, deleted,
				orderByComparator);

		if (userThread != null) {
			return userThread;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", deleted=");
		msg.append(deleted);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserThreadException(msg.toString());
	}

	/**
	 * Returns the first user thread in the ordered set where userId = &#63; and deleted = &#63;.
	 *
	 * @param userId the user ID
	 * @param deleted the deleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user thread, or <code>null</code> if a matching user thread could not be found
	 */
	@Override
	public UserThread fetchByU_D_First(long userId, boolean deleted,
		OrderByComparator<UserThread> orderByComparator) {
		List<UserThread> list = findByU_D(userId, deleted, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user thread in the ordered set where userId = &#63; and deleted = &#63;.
	 *
	 * @param userId the user ID
	 * @param deleted the deleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user thread
	 * @throws NoSuchUserThreadException if a matching user thread could not be found
	 */
	@Override
	public UserThread findByU_D_Last(long userId, boolean deleted,
		OrderByComparator<UserThread> orderByComparator)
		throws NoSuchUserThreadException {
		UserThread userThread = fetchByU_D_Last(userId, deleted,
				orderByComparator);

		if (userThread != null) {
			return userThread;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", deleted=");
		msg.append(deleted);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserThreadException(msg.toString());
	}

	/**
	 * Returns the last user thread in the ordered set where userId = &#63; and deleted = &#63;.
	 *
	 * @param userId the user ID
	 * @param deleted the deleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user thread, or <code>null</code> if a matching user thread could not be found
	 */
	@Override
	public UserThread fetchByU_D_Last(long userId, boolean deleted,
		OrderByComparator<UserThread> orderByComparator) {
		int count = countByU_D(userId, deleted);

		if (count == 0) {
			return null;
		}

		List<UserThread> list = findByU_D(userId, deleted, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user threads before and after the current user thread in the ordered set where userId = &#63; and deleted = &#63;.
	 *
	 * @param userThreadId the primary key of the current user thread
	 * @param userId the user ID
	 * @param deleted the deleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user thread
	 * @throws NoSuchUserThreadException if a user thread with the primary key could not be found
	 */
	@Override
	public UserThread[] findByU_D_PrevAndNext(long userThreadId, long userId,
		boolean deleted, OrderByComparator<UserThread> orderByComparator)
		throws NoSuchUserThreadException {
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
		OrderByComparator<UserThread> orderByComparator, boolean previous) {
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
			Object[] values = orderByComparator.getOrderByConditionValues(userThread);

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
	 * Removes all the user threads where userId = &#63; and deleted = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param deleted the deleted
	 */
	@Override
	public void removeByU_D(long userId, boolean deleted) {
		for (UserThread userThread : findByU_D(userId, deleted,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(userThread);
		}
	}

	/**
	 * Returns the number of user threads where userId = &#63; and deleted = &#63;.
	 *
	 * @param userId the user ID
	 * @param deleted the deleted
	 * @return the number of matching user threads
	 */
	@Override
	public int countByU_D(long userId, boolean deleted) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_D;

		Object[] finderArgs = new Object[] { userId, deleted };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

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

	private static final String _FINDER_COLUMN_U_D_USERID_2 = "userThread.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_D_DELETED_2 = "userThread.deleted = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_R_D = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, UserThreadImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_R_D",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R_D = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, UserThreadImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_R_D",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			},
			UserThreadModelImpl.USERID_COLUMN_BITMASK |
			UserThreadModelImpl.READ_COLUMN_BITMASK |
			UserThreadModelImpl.DELETED_COLUMN_BITMASK |
			UserThreadModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_R_D = new FinderPath(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_R_D",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the user threads where userId = &#63; and read = &#63; and deleted = &#63;.
	 *
	 * @param userId the user ID
	 * @param read the read
	 * @param deleted the deleted
	 * @return the matching user threads
	 */
	@Override
	public List<UserThread> findByU_R_D(long userId, boolean read,
		boolean deleted) {
		return findByU_R_D(userId, read, deleted, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user threads where userId = &#63; and read = &#63; and deleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserThreadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param read the read
	 * @param deleted the deleted
	 * @param start the lower bound of the range of user threads
	 * @param end the upper bound of the range of user threads (not inclusive)
	 * @return the range of matching user threads
	 */
	@Override
	public List<UserThread> findByU_R_D(long userId, boolean read,
		boolean deleted, int start, int end) {
		return findByU_R_D(userId, read, deleted, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user threads where userId = &#63; and read = &#63; and deleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserThreadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param read the read
	 * @param deleted the deleted
	 * @param start the lower bound of the range of user threads
	 * @param end the upper bound of the range of user threads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user threads
	 */
	@Override
	public List<UserThread> findByU_R_D(long userId, boolean read,
		boolean deleted, int start, int end,
		OrderByComparator<UserThread> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R_D;
			finderArgs = new Object[] { userId, read, deleted };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_R_D;
			finderArgs = new Object[] {
					userId, read, deleted,
					
					start, end, orderByComparator
				};
		}

		List<UserThread> list = (List<UserThread>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (UserThread userThread : list) {
				if ((userId != userThread.getUserId()) ||
						(read != userThread.getRead()) ||
						(deleted != userThread.getDeleted())) {
					list = null;

					break;
				}
			}
		}

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
			else
			 if (pagination) {
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

				if (!pagination) {
					list = (List<UserThread>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UserThread>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first user thread in the ordered set where userId = &#63; and read = &#63; and deleted = &#63;.
	 *
	 * @param userId the user ID
	 * @param read the read
	 * @param deleted the deleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user thread
	 * @throws NoSuchUserThreadException if a matching user thread could not be found
	 */
	@Override
	public UserThread findByU_R_D_First(long userId, boolean read,
		boolean deleted, OrderByComparator<UserThread> orderByComparator)
		throws NoSuchUserThreadException {
		UserThread userThread = fetchByU_R_D_First(userId, read, deleted,
				orderByComparator);

		if (userThread != null) {
			return userThread;
		}

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

	/**
	 * Returns the first user thread in the ordered set where userId = &#63; and read = &#63; and deleted = &#63;.
	 *
	 * @param userId the user ID
	 * @param read the read
	 * @param deleted the deleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user thread, or <code>null</code> if a matching user thread could not be found
	 */
	@Override
	public UserThread fetchByU_R_D_First(long userId, boolean read,
		boolean deleted, OrderByComparator<UserThread> orderByComparator) {
		List<UserThread> list = findByU_R_D(userId, read, deleted, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user thread in the ordered set where userId = &#63; and read = &#63; and deleted = &#63;.
	 *
	 * @param userId the user ID
	 * @param read the read
	 * @param deleted the deleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user thread
	 * @throws NoSuchUserThreadException if a matching user thread could not be found
	 */
	@Override
	public UserThread findByU_R_D_Last(long userId, boolean read,
		boolean deleted, OrderByComparator<UserThread> orderByComparator)
		throws NoSuchUserThreadException {
		UserThread userThread = fetchByU_R_D_Last(userId, read, deleted,
				orderByComparator);

		if (userThread != null) {
			return userThread;
		}

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

	/**
	 * Returns the last user thread in the ordered set where userId = &#63; and read = &#63; and deleted = &#63;.
	 *
	 * @param userId the user ID
	 * @param read the read
	 * @param deleted the deleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user thread, or <code>null</code> if a matching user thread could not be found
	 */
	@Override
	public UserThread fetchByU_R_D_Last(long userId, boolean read,
		boolean deleted, OrderByComparator<UserThread> orderByComparator) {
		int count = countByU_R_D(userId, read, deleted);

		if (count == 0) {
			return null;
		}

		List<UserThread> list = findByU_R_D(userId, read, deleted, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user threads before and after the current user thread in the ordered set where userId = &#63; and read = &#63; and deleted = &#63;.
	 *
	 * @param userThreadId the primary key of the current user thread
	 * @param userId the user ID
	 * @param read the read
	 * @param deleted the deleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user thread
	 * @throws NoSuchUserThreadException if a user thread with the primary key could not be found
	 */
	@Override
	public UserThread[] findByU_R_D_PrevAndNext(long userThreadId, long userId,
		boolean read, boolean deleted,
		OrderByComparator<UserThread> orderByComparator)
		throws NoSuchUserThreadException {
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
		OrderByComparator<UserThread> orderByComparator, boolean previous) {
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
			Object[] values = orderByComparator.getOrderByConditionValues(userThread);

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
	 * Removes all the user threads where userId = &#63; and read = &#63; and deleted = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param read the read
	 * @param deleted the deleted
	 */
	@Override
	public void removeByU_R_D(long userId, boolean read, boolean deleted) {
		for (UserThread userThread : findByU_R_D(userId, read, deleted,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(userThread);
		}
	}

	/**
	 * Returns the number of user threads where userId = &#63; and read = &#63; and deleted = &#63;.
	 *
	 * @param userId the user ID
	 * @param read the read
	 * @param deleted the deleted
	 * @return the number of matching user threads
	 */
	@Override
	public int countByU_R_D(long userId, boolean read, boolean deleted) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_R_D;

		Object[] finderArgs = new Object[] { userId, read, deleted };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

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

	private static final String _FINDER_COLUMN_U_R_D_USERID_2 = "userThread.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_R_D_READ_2 = "userThread.read = ? AND ";
	private static final String _FINDER_COLUMN_U_R_D_DELETED_2 = "userThread.deleted = ?";

	public UserThreadPersistenceImpl() {
		setModelClass(UserThread.class);
	}

	/**
	 * Caches the user thread in the entity cache if it is enabled.
	 *
	 * @param userThread the user thread
	 */
	@Override
	public void cacheResult(UserThread userThread) {
		EntityCacheUtil.putResult(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadImpl.class, userThread.getPrimaryKey(), userThread);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_M,
			new Object[] { userThread.getUserId(), userThread.getMbThreadId() },
			userThread);

		userThread.resetOriginalValues();
	}

	/**
	 * Caches the user threads in the entity cache if it is enabled.
	 *
	 * @param userThreads the user threads
	 */
	@Override
	public void cacheResult(List<UserThread> userThreads) {
		for (UserThread userThread : userThreads) {
			if (EntityCacheUtil.getResult(
						UserThreadModelImpl.ENTITY_CACHE_ENABLED,
						UserThreadImpl.class, userThread.getPrimaryKey()) == null) {
				cacheResult(userThread);
			}
			else {
				userThread.resetOriginalValues();
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
		EntityCacheUtil.clearCache(UserThreadImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
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

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(userThread);
	}

	@Override
	public void clearCache(List<UserThread> userThreads) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (UserThread userThread : userThreads) {
			EntityCacheUtil.removeResult(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
				UserThreadImpl.class, userThread.getPrimaryKey());

			clearUniqueFindersCache(userThread);
		}
	}

	protected void cacheUniqueFindersCache(UserThread userThread) {
		if (userThread.isNew()) {
			Object[] args = new Object[] {
					userThread.getUserId(), userThread.getMbThreadId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_M, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_M, args, userThread);
		}
		else {
			UserThreadModelImpl userThreadModelImpl = (UserThreadModelImpl)userThread;

			if ((userThreadModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_U_M.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						userThread.getUserId(), userThread.getMbThreadId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_M, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_M, args,
					userThread);
			}
		}
	}

	protected void clearUniqueFindersCache(UserThread userThread) {
		UserThreadModelImpl userThreadModelImpl = (UserThreadModelImpl)userThread;

		Object[] args = new Object[] {
				userThread.getUserId(), userThread.getMbThreadId()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_M, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_M, args);

		if ((userThreadModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_M.getColumnBitmask()) != 0) {
			args = new Object[] {
					userThreadModelImpl.getOriginalUserId(),
					userThreadModelImpl.getOriginalMbThreadId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_M, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_M, args);
		}
	}

	/**
	 * Creates a new user thread with the primary key. Does not add the user thread to the database.
	 *
	 * @param userThreadId the primary key for the new user thread
	 * @return the new user thread
	 */
	@Override
	public UserThread create(long userThreadId) {
		UserThread userThread = new UserThreadImpl();

		userThread.setNew(true);
		userThread.setPrimaryKey(userThreadId);

		return userThread;
	}

	/**
	 * Removes the user thread with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userThreadId the primary key of the user thread
	 * @return the user thread that was removed
	 * @throws NoSuchUserThreadException if a user thread with the primary key could not be found
	 */
	@Override
	public UserThread remove(long userThreadId)
		throws NoSuchUserThreadException {
		return remove((Serializable)userThreadId);
	}

	/**
	 * Removes the user thread with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the user thread
	 * @return the user thread that was removed
	 * @throws NoSuchUserThreadException if a user thread with the primary key could not be found
	 */
	@Override
	public UserThread remove(Serializable primaryKey)
		throws NoSuchUserThreadException {
		Session session = null;

		try {
			session = openSession();

			UserThread userThread = (UserThread)session.get(UserThreadImpl.class,
					primaryKey);

			if (userThread == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUserThreadException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
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

	@Override
	protected UserThread removeImpl(UserThread userThread) {
		userThread = toUnwrappedModel(userThread);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(userThread)) {
				userThread = (UserThread)session.get(UserThreadImpl.class,
						userThread.getPrimaryKeyObj());
			}

			if (userThread != null) {
				session.delete(userThread);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (userThread != null) {
			clearCache(userThread);
		}

		return userThread;
	}

	@Override
	public UserThread updateImpl(UserThread userThread) {
		userThread = toUnwrappedModel(userThread);

		boolean isNew = userThread.isNew();

		UserThreadModelImpl userThreadModelImpl = (UserThreadModelImpl)userThread;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (userThread.getCreateDate() == null)) {
			if (serviceContext == null) {
				userThread.setCreateDate(now);
			}
			else {
				userThread.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!userThreadModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				userThread.setModifiedDate(now);
			}
			else {
				userThread.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (userThread.isNew()) {
				session.save(userThread);

				userThread.setNew(false);
			}
			else {
				session.merge(userThread);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !UserThreadModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((userThreadModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MBTHREADID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						userThreadModelImpl.getOriginalMbThreadId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MBTHREADID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MBTHREADID,
					args);

				args = new Object[] { userThreadModelImpl.getMbThreadId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MBTHREADID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MBTHREADID,
					args);
			}

			if ((userThreadModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						userThreadModelImpl.getOriginalUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { userThreadModelImpl.getUserId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((userThreadModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_D.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						userThreadModelImpl.getOriginalUserId(),
						userThreadModelImpl.getOriginalDeleted()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_D, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_D,
					args);

				args = new Object[] {
						userThreadModelImpl.getUserId(),
						userThreadModelImpl.getDeleted()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_D, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_D,
					args);
			}

			if ((userThreadModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R_D.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						userThreadModelImpl.getOriginalUserId(),
						userThreadModelImpl.getOriginalRead(),
						userThreadModelImpl.getOriginalDeleted()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_R_D, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R_D,
					args);

				args = new Object[] {
						userThreadModelImpl.getUserId(),
						userThreadModelImpl.getRead(),
						userThreadModelImpl.getDeleted()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_R_D, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_R_D,
					args);
			}
		}

		EntityCacheUtil.putResult(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
			UserThreadImpl.class, userThread.getPrimaryKey(), userThread, false);

		clearUniqueFindersCache(userThread);
		cacheUniqueFindersCache(userThread);

		userThread.resetOriginalValues();

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
		userThreadImpl.setUserName(userThread.getUserName());
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
	 * @throws NoSuchUserThreadException if a user thread with the primary key could not be found
	 */
	@Override
	public UserThread findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUserThreadException {
		UserThread userThread = fetchByPrimaryKey(primaryKey);

		if (userThread == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUserThreadException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return userThread;
	}

	/**
	 * Returns the user thread with the primary key or throws a {@link NoSuchUserThreadException} if it could not be found.
	 *
	 * @param userThreadId the primary key of the user thread
	 * @return the user thread
	 * @throws NoSuchUserThreadException if a user thread with the primary key could not be found
	 */
	@Override
	public UserThread findByPrimaryKey(long userThreadId)
		throws NoSuchUserThreadException {
		return findByPrimaryKey((Serializable)userThreadId);
	}

	/**
	 * Returns the user thread with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the user thread
	 * @return the user thread, or <code>null</code> if a user thread with the primary key could not be found
	 */
	@Override
	public UserThread fetchByPrimaryKey(Serializable primaryKey) {
		UserThread userThread = (UserThread)EntityCacheUtil.getResult(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
				UserThreadImpl.class, primaryKey);

		if (userThread == _nullUserThread) {
			return null;
		}

		if (userThread == null) {
			Session session = null;

			try {
				session = openSession();

				userThread = (UserThread)session.get(UserThreadImpl.class,
						primaryKey);

				if (userThread != null) {
					cacheResult(userThread);
				}
				else {
					EntityCacheUtil.putResult(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
						UserThreadImpl.class, primaryKey, _nullUserThread);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
					UserThreadImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return userThread;
	}

	/**
	 * Returns the user thread with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userThreadId the primary key of the user thread
	 * @return the user thread, or <code>null</code> if a user thread with the primary key could not be found
	 */
	@Override
	public UserThread fetchByPrimaryKey(long userThreadId) {
		return fetchByPrimaryKey((Serializable)userThreadId);
	}

	@Override
	public Map<Serializable, UserThread> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, UserThread> map = new HashMap<Serializable, UserThread>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			UserThread userThread = fetchByPrimaryKey(primaryKey);

			if (userThread != null) {
				map.put(primaryKey, userThread);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			UserThread userThread = (UserThread)EntityCacheUtil.getResult(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
					UserThreadImpl.class, primaryKey);

			if (userThread == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, userThread);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_USERTHREAD_WHERE_PKS_IN);

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

			for (UserThread userThread : (List<UserThread>)q.list()) {
				map.put(userThread.getPrimaryKeyObj(), userThread);

				cacheResult(userThread);

				uncachedPrimaryKeys.remove(userThread.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(UserThreadModelImpl.ENTITY_CACHE_ENABLED,
					UserThreadImpl.class, primaryKey, _nullUserThread);
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
	 * Returns all the user threads.
	 *
	 * @return the user threads
	 */
	@Override
	public List<UserThread> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user threads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserThreadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of user threads
	 * @param end the upper bound of the range of user threads (not inclusive)
	 * @return the range of user threads
	 */
	@Override
	public List<UserThread> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the user threads.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserThreadModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of user threads
	 * @param end the upper bound of the range of user threads (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user threads
	 */
	@Override
	public List<UserThread> findAll(int start, int end,
		OrderByComparator<UserThread> orderByComparator) {
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

		List<UserThread> list = (List<UserThread>)FinderCacheUtil.getResult(finderPath,
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
				sql = _SQL_SELECT_USERTHREAD;

				if (pagination) {
					sql = sql.concat(UserThreadModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<UserThread>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UserThread>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the user threads from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (UserThread userThread : findAll()) {
			remove(userThread);
		}
	}

	/**
	 * Returns the number of user threads.
	 *
	 * @return the number of user threads
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_USERTHREAD);

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
	protected Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	/**
	 * Initializes the user thread persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(UserThreadImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_USERTHREAD = "SELECT userThread FROM UserThread userThread";
	private static final String _SQL_SELECT_USERTHREAD_WHERE_PKS_IN = "SELECT userThread FROM UserThread userThread WHERE userThreadId IN (";
	private static final String _SQL_SELECT_USERTHREAD_WHERE = "SELECT userThread FROM UserThread userThread WHERE ";
	private static final String _SQL_COUNT_USERTHREAD = "SELECT COUNT(userThread) FROM UserThread userThread";
	private static final String _SQL_COUNT_USERTHREAD_WHERE = "SELECT COUNT(userThread) FROM UserThread userThread WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "userThread.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No UserThread exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No UserThread exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(UserThreadPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"read"
			});
	private static final UserThread _nullUserThread = new UserThreadImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<UserThread> toCacheModel() {
				return _nullUserThreadCacheModel;
			}
		};

	private static final CacheModel<UserThread> _nullUserThreadCacheModel = new CacheModel<UserThread>() {
			@Override
			public UserThread toEntityModel() {
				return _nullUserThread;
			}
		};
}