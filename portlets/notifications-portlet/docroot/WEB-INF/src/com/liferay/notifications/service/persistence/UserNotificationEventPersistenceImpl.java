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

package com.liferay.notifications.service.persistence;

import com.liferay.notifications.NoSuchUserNotificationEventException;
import com.liferay.notifications.model.UserNotificationEvent;
import com.liferay.notifications.model.impl.UserNotificationEventImpl;
import com.liferay.notifications.model.impl.UserNotificationEventModelImpl;

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
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the user notification event service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserNotificationEventPersistence
 * @see UserNotificationEventUtil
 * @generated
 */
public class UserNotificationEventPersistenceImpl extends BasePersistenceImpl<UserNotificationEvent>
	implements UserNotificationEventPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link UserNotificationEventUtil} to access the user notification event persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = UserNotificationEventImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(UserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationEventModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationEventImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(UserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationEventModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationEventImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationEventModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_USERNOTIFICATIONEVENTID = new FinderPath(UserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationEventModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationEventImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUserNotificationEventId",
			new String[] { Long.class.getName() },
			UserNotificationEventModelImpl.USERNOTIFICATIONEVENTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERNOTIFICATIONEVENTID = new FinderPath(UserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationEventModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUserNotificationEventId",
			new String[] { Long.class.getName() });

	/**
	 * Returns the user notification event where userNotificationEventId = &#63; or throws a {@link com.liferay.notifications.NoSuchUserNotificationEventException} if it could not be found.
	 *
	 * @param userNotificationEventId the user notification event ID
	 * @return the matching user notification event
	 * @throws com.liferay.notifications.NoSuchUserNotificationEventException if a matching user notification event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserNotificationEvent findByUserNotificationEventId(
		long userNotificationEventId)
		throws NoSuchUserNotificationEventException, SystemException {
		UserNotificationEvent userNotificationEvent = fetchByUserNotificationEventId(userNotificationEventId);

		if (userNotificationEvent == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userNotificationEventId=");
			msg.append(userNotificationEventId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchUserNotificationEventException(msg.toString());
		}

		return userNotificationEvent;
	}

	/**
	 * Returns the user notification event where userNotificationEventId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userNotificationEventId the user notification event ID
	 * @return the matching user notification event, or <code>null</code> if a matching user notification event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserNotificationEvent fetchByUserNotificationEventId(
		long userNotificationEventId) throws SystemException {
		return fetchByUserNotificationEventId(userNotificationEventId, true);
	}

	/**
	 * Returns the user notification event where userNotificationEventId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userNotificationEventId the user notification event ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching user notification event, or <code>null</code> if a matching user notification event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserNotificationEvent fetchByUserNotificationEventId(
		long userNotificationEventId, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { userNotificationEventId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_USERNOTIFICATIONEVENTID,
					finderArgs, this);
		}

		if (result instanceof UserNotificationEvent) {
			UserNotificationEvent userNotificationEvent = (UserNotificationEvent)result;

			if ((userNotificationEventId != userNotificationEvent.getUserNotificationEventId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_USERNOTIFICATIONEVENT_WHERE);

			query.append(_FINDER_COLUMN_USERNOTIFICATIONEVENTID_USERNOTIFICATIONEVENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userNotificationEventId);

				List<UserNotificationEvent> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERNOTIFICATIONEVENTID,
						finderArgs, list);
				}
				else {
					UserNotificationEvent userNotificationEvent = list.get(0);

					result = userNotificationEvent;

					cacheResult(userNotificationEvent);

					if ((userNotificationEvent.getUserNotificationEventId() != userNotificationEventId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERNOTIFICATIONEVENTID,
							finderArgs, userNotificationEvent);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERNOTIFICATIONEVENTID,
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
			return (UserNotificationEvent)result;
		}
	}

	/**
	 * Removes the user notification event where userNotificationEventId = &#63; from the database.
	 *
	 * @param userNotificationEventId the user notification event ID
	 * @return the user notification event that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserNotificationEvent removeByUserNotificationEventId(
		long userNotificationEventId)
		throws NoSuchUserNotificationEventException, SystemException {
		UserNotificationEvent userNotificationEvent = findByUserNotificationEventId(userNotificationEventId);

		return remove(userNotificationEvent);
	}

	/**
	 * Returns the number of user notification events where userNotificationEventId = &#63;.
	 *
	 * @param userNotificationEventId the user notification event ID
	 * @return the number of matching user notification events
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUserNotificationEventId(long userNotificationEventId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERNOTIFICATIONEVENTID;

		Object[] finderArgs = new Object[] { userNotificationEventId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USERNOTIFICATIONEVENT_WHERE);

			query.append(_FINDER_COLUMN_USERNOTIFICATIONEVENTID_USERNOTIFICATIONEVENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userNotificationEventId);

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

	private static final String _FINDER_COLUMN_USERNOTIFICATIONEVENTID_USERNOTIFICATIONEVENTID_2 =
		"userNotificationEvent.userNotificationEventId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_A = new FinderPath(UserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationEventModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationEventImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_A = new FinderPath(UserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationEventModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationEventImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_A",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			UserNotificationEventModelImpl.USERID_COLUMN_BITMASK |
			UserNotificationEventModelImpl.ACTIONREQUIRED_COLUMN_BITMASK |
			UserNotificationEventModelImpl.TIMESTAMP_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_A = new FinderPath(UserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationEventModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_A",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the user notification events where userId = &#63; and actionRequired = &#63;.
	 *
	 * @param userId the user ID
	 * @param actionRequired the action required
	 * @return the matching user notification events
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<UserNotificationEvent> findByU_A(long userId,
		boolean actionRequired) throws SystemException {
		return findByU_A(userId, actionRequired, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user notification events where userId = &#63; and actionRequired = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.notifications.model.impl.UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param actionRequired the action required
	 * @param start the lower bound of the range of user notification events
	 * @param end the upper bound of the range of user notification events (not inclusive)
	 * @return the range of matching user notification events
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<UserNotificationEvent> findByU_A(long userId,
		boolean actionRequired, int start, int end) throws SystemException {
		return findByU_A(userId, actionRequired, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user notification events where userId = &#63; and actionRequired = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.notifications.model.impl.UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param actionRequired the action required
	 * @param start the lower bound of the range of user notification events
	 * @param end the upper bound of the range of user notification events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user notification events
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<UserNotificationEvent> findByU_A(long userId,
		boolean actionRequired, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_A;
			finderArgs = new Object[] { userId, actionRequired };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_A;
			finderArgs = new Object[] {
					userId, actionRequired,
					
					start, end, orderByComparator
				};
		}

		List<UserNotificationEvent> list = (List<UserNotificationEvent>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (UserNotificationEvent userNotificationEvent : list) {
				if ((userId != userNotificationEvent.getUserId()) ||
						(actionRequired != userNotificationEvent.getActionRequired())) {
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

			query.append(_SQL_SELECT_USERNOTIFICATIONEVENT_WHERE);

			query.append(_FINDER_COLUMN_U_A_USERID_2);

			query.append(_FINDER_COLUMN_U_A_ACTIONREQUIRED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(UserNotificationEventModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(actionRequired);

				if (!pagination) {
					list = (List<UserNotificationEvent>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<UserNotificationEvent>(list);
				}
				else {
					list = (List<UserNotificationEvent>)QueryUtil.list(q,
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
	 * Returns the first user notification event in the ordered set where userId = &#63; and actionRequired = &#63;.
	 *
	 * @param userId the user ID
	 * @param actionRequired the action required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user notification event
	 * @throws com.liferay.notifications.NoSuchUserNotificationEventException if a matching user notification event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserNotificationEvent findByU_A_First(long userId,
		boolean actionRequired, OrderByComparator orderByComparator)
		throws NoSuchUserNotificationEventException, SystemException {
		UserNotificationEvent userNotificationEvent = fetchByU_A_First(userId,
				actionRequired, orderByComparator);

		if (userNotificationEvent != null) {
			return userNotificationEvent;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", actionRequired=");
		msg.append(actionRequired);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserNotificationEventException(msg.toString());
	}

	/**
	 * Returns the first user notification event in the ordered set where userId = &#63; and actionRequired = &#63;.
	 *
	 * @param userId the user ID
	 * @param actionRequired the action required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user notification event, or <code>null</code> if a matching user notification event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserNotificationEvent fetchByU_A_First(long userId,
		boolean actionRequired, OrderByComparator orderByComparator)
		throws SystemException {
		List<UserNotificationEvent> list = findByU_A(userId, actionRequired, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user notification event in the ordered set where userId = &#63; and actionRequired = &#63;.
	 *
	 * @param userId the user ID
	 * @param actionRequired the action required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user notification event
	 * @throws com.liferay.notifications.NoSuchUserNotificationEventException if a matching user notification event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserNotificationEvent findByU_A_Last(long userId,
		boolean actionRequired, OrderByComparator orderByComparator)
		throws NoSuchUserNotificationEventException, SystemException {
		UserNotificationEvent userNotificationEvent = fetchByU_A_Last(userId,
				actionRequired, orderByComparator);

		if (userNotificationEvent != null) {
			return userNotificationEvent;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", actionRequired=");
		msg.append(actionRequired);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserNotificationEventException(msg.toString());
	}

	/**
	 * Returns the last user notification event in the ordered set where userId = &#63; and actionRequired = &#63;.
	 *
	 * @param userId the user ID
	 * @param actionRequired the action required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user notification event, or <code>null</code> if a matching user notification event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserNotificationEvent fetchByU_A_Last(long userId,
		boolean actionRequired, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByU_A(userId, actionRequired);

		if (count == 0) {
			return null;
		}

		List<UserNotificationEvent> list = findByU_A(userId, actionRequired,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user notification events before and after the current user notification event in the ordered set where userId = &#63; and actionRequired = &#63;.
	 *
	 * @param notificationEventId the primary key of the current user notification event
	 * @param userId the user ID
	 * @param actionRequired the action required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user notification event
	 * @throws com.liferay.notifications.NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserNotificationEvent[] findByU_A_PrevAndNext(
		long notificationEventId, long userId, boolean actionRequired,
		OrderByComparator orderByComparator)
		throws NoSuchUserNotificationEventException, SystemException {
		UserNotificationEvent userNotificationEvent = findByPrimaryKey(notificationEventId);

		Session session = null;

		try {
			session = openSession();

			UserNotificationEvent[] array = new UserNotificationEventImpl[3];

			array[0] = getByU_A_PrevAndNext(session, userNotificationEvent,
					userId, actionRequired, orderByComparator, true);

			array[1] = userNotificationEvent;

			array[2] = getByU_A_PrevAndNext(session, userNotificationEvent,
					userId, actionRequired, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected UserNotificationEvent getByU_A_PrevAndNext(Session session,
		UserNotificationEvent userNotificationEvent, long userId,
		boolean actionRequired, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_USERNOTIFICATIONEVENT_WHERE);

		query.append(_FINDER_COLUMN_U_A_USERID_2);

		query.append(_FINDER_COLUMN_U_A_ACTIONREQUIRED_2);

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
			query.append(UserNotificationEventModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(actionRequired);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(userNotificationEvent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UserNotificationEvent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user notification events where userId = &#63; and actionRequired = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param actionRequired the action required
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByU_A(long userId, boolean actionRequired)
		throws SystemException {
		for (UserNotificationEvent userNotificationEvent : findByU_A(userId,
				actionRequired, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(userNotificationEvent);
		}
	}

	/**
	 * Returns the number of user notification events where userId = &#63; and actionRequired = &#63;.
	 *
	 * @param userId the user ID
	 * @param actionRequired the action required
	 * @return the number of matching user notification events
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByU_A(long userId, boolean actionRequired)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_A;

		Object[] finderArgs = new Object[] { userId, actionRequired };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_USERNOTIFICATIONEVENT_WHERE);

			query.append(_FINDER_COLUMN_U_A_USERID_2);

			query.append(_FINDER_COLUMN_U_A_ACTIONREQUIRED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(actionRequired);

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

	private static final String _FINDER_COLUMN_U_A_USERID_2 = "userNotificationEvent.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_A_ACTIONREQUIRED_2 = "userNotificationEvent.actionRequired = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_D_A = new FinderPath(UserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationEventModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationEventImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_D_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_D_A = new FinderPath(UserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationEventModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationEventImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_D_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			},
			UserNotificationEventModelImpl.USERID_COLUMN_BITMASK |
			UserNotificationEventModelImpl.DELIVERED_COLUMN_BITMASK |
			UserNotificationEventModelImpl.ACTIONREQUIRED_COLUMN_BITMASK |
			UserNotificationEventModelImpl.TIMESTAMP_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_D_A = new FinderPath(UserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationEventModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_D_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the user notification events where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	 *
	 * @param userId the user ID
	 * @param delivered the delivered
	 * @param actionRequired the action required
	 * @return the matching user notification events
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<UserNotificationEvent> findByU_D_A(long userId,
		boolean delivered, boolean actionRequired) throws SystemException {
		return findByU_D_A(userId, delivered, actionRequired,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user notification events where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.notifications.model.impl.UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param delivered the delivered
	 * @param actionRequired the action required
	 * @param start the lower bound of the range of user notification events
	 * @param end the upper bound of the range of user notification events (not inclusive)
	 * @return the range of matching user notification events
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<UserNotificationEvent> findByU_D_A(long userId,
		boolean delivered, boolean actionRequired, int start, int end)
		throws SystemException {
		return findByU_D_A(userId, delivered, actionRequired, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user notification events where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.notifications.model.impl.UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param delivered the delivered
	 * @param actionRequired the action required
	 * @param start the lower bound of the range of user notification events
	 * @param end the upper bound of the range of user notification events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user notification events
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<UserNotificationEvent> findByU_D_A(long userId,
		boolean delivered, boolean actionRequired, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_D_A;
			finderArgs = new Object[] { userId, delivered, actionRequired };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_D_A;
			finderArgs = new Object[] {
					userId, delivered, actionRequired,
					
					start, end, orderByComparator
				};
		}

		List<UserNotificationEvent> list = (List<UserNotificationEvent>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (UserNotificationEvent userNotificationEvent : list) {
				if ((userId != userNotificationEvent.getUserId()) ||
						(delivered != userNotificationEvent.getDelivered()) ||
						(actionRequired != userNotificationEvent.getActionRequired())) {
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

			query.append(_SQL_SELECT_USERNOTIFICATIONEVENT_WHERE);

			query.append(_FINDER_COLUMN_U_D_A_USERID_2);

			query.append(_FINDER_COLUMN_U_D_A_DELIVERED_2);

			query.append(_FINDER_COLUMN_U_D_A_ACTIONREQUIRED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(UserNotificationEventModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(delivered);

				qPos.add(actionRequired);

				if (!pagination) {
					list = (List<UserNotificationEvent>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<UserNotificationEvent>(list);
				}
				else {
					list = (List<UserNotificationEvent>)QueryUtil.list(q,
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
	 * Returns the first user notification event in the ordered set where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	 *
	 * @param userId the user ID
	 * @param delivered the delivered
	 * @param actionRequired the action required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user notification event
	 * @throws com.liferay.notifications.NoSuchUserNotificationEventException if a matching user notification event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserNotificationEvent findByU_D_A_First(long userId,
		boolean delivered, boolean actionRequired,
		OrderByComparator orderByComparator)
		throws NoSuchUserNotificationEventException, SystemException {
		UserNotificationEvent userNotificationEvent = fetchByU_D_A_First(userId,
				delivered, actionRequired, orderByComparator);

		if (userNotificationEvent != null) {
			return userNotificationEvent;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", delivered=");
		msg.append(delivered);

		msg.append(", actionRequired=");
		msg.append(actionRequired);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserNotificationEventException(msg.toString());
	}

	/**
	 * Returns the first user notification event in the ordered set where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	 *
	 * @param userId the user ID
	 * @param delivered the delivered
	 * @param actionRequired the action required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user notification event, or <code>null</code> if a matching user notification event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserNotificationEvent fetchByU_D_A_First(long userId,
		boolean delivered, boolean actionRequired,
		OrderByComparator orderByComparator) throws SystemException {
		List<UserNotificationEvent> list = findByU_D_A(userId, delivered,
				actionRequired, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user notification event in the ordered set where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	 *
	 * @param userId the user ID
	 * @param delivered the delivered
	 * @param actionRequired the action required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user notification event
	 * @throws com.liferay.notifications.NoSuchUserNotificationEventException if a matching user notification event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserNotificationEvent findByU_D_A_Last(long userId,
		boolean delivered, boolean actionRequired,
		OrderByComparator orderByComparator)
		throws NoSuchUserNotificationEventException, SystemException {
		UserNotificationEvent userNotificationEvent = fetchByU_D_A_Last(userId,
				delivered, actionRequired, orderByComparator);

		if (userNotificationEvent != null) {
			return userNotificationEvent;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", delivered=");
		msg.append(delivered);

		msg.append(", actionRequired=");
		msg.append(actionRequired);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserNotificationEventException(msg.toString());
	}

	/**
	 * Returns the last user notification event in the ordered set where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	 *
	 * @param userId the user ID
	 * @param delivered the delivered
	 * @param actionRequired the action required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user notification event, or <code>null</code> if a matching user notification event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserNotificationEvent fetchByU_D_A_Last(long userId,
		boolean delivered, boolean actionRequired,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByU_D_A(userId, delivered, actionRequired);

		if (count == 0) {
			return null;
		}

		List<UserNotificationEvent> list = findByU_D_A(userId, delivered,
				actionRequired, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user notification events before and after the current user notification event in the ordered set where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	 *
	 * @param notificationEventId the primary key of the current user notification event
	 * @param userId the user ID
	 * @param delivered the delivered
	 * @param actionRequired the action required
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user notification event
	 * @throws com.liferay.notifications.NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserNotificationEvent[] findByU_D_A_PrevAndNext(
		long notificationEventId, long userId, boolean delivered,
		boolean actionRequired, OrderByComparator orderByComparator)
		throws NoSuchUserNotificationEventException, SystemException {
		UserNotificationEvent userNotificationEvent = findByPrimaryKey(notificationEventId);

		Session session = null;

		try {
			session = openSession();

			UserNotificationEvent[] array = new UserNotificationEventImpl[3];

			array[0] = getByU_D_A_PrevAndNext(session, userNotificationEvent,
					userId, delivered, actionRequired, orderByComparator, true);

			array[1] = userNotificationEvent;

			array[2] = getByU_D_A_PrevAndNext(session, userNotificationEvent,
					userId, delivered, actionRequired, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected UserNotificationEvent getByU_D_A_PrevAndNext(Session session,
		UserNotificationEvent userNotificationEvent, long userId,
		boolean delivered, boolean actionRequired,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_USERNOTIFICATIONEVENT_WHERE);

		query.append(_FINDER_COLUMN_U_D_A_USERID_2);

		query.append(_FINDER_COLUMN_U_D_A_DELIVERED_2);

		query.append(_FINDER_COLUMN_U_D_A_ACTIONREQUIRED_2);

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
			query.append(UserNotificationEventModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(delivered);

		qPos.add(actionRequired);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(userNotificationEvent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UserNotificationEvent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user notification events where userId = &#63; and delivered = &#63; and actionRequired = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param delivered the delivered
	 * @param actionRequired the action required
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByU_D_A(long userId, boolean delivered,
		boolean actionRequired) throws SystemException {
		for (UserNotificationEvent userNotificationEvent : findByU_D_A(userId,
				delivered, actionRequired, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(userNotificationEvent);
		}
	}

	/**
	 * Returns the number of user notification events where userId = &#63; and delivered = &#63; and actionRequired = &#63;.
	 *
	 * @param userId the user ID
	 * @param delivered the delivered
	 * @param actionRequired the action required
	 * @return the number of matching user notification events
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByU_D_A(long userId, boolean delivered,
		boolean actionRequired) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_D_A;

		Object[] finderArgs = new Object[] { userId, delivered, actionRequired };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_USERNOTIFICATIONEVENT_WHERE);

			query.append(_FINDER_COLUMN_U_D_A_USERID_2);

			query.append(_FINDER_COLUMN_U_D_A_DELIVERED_2);

			query.append(_FINDER_COLUMN_U_D_A_ACTIONREQUIRED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(delivered);

				qPos.add(actionRequired);

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

	private static final String _FINDER_COLUMN_U_D_A_USERID_2 = "userNotificationEvent.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_D_A_DELIVERED_2 = "userNotificationEvent.delivered = ? AND ";
	private static final String _FINDER_COLUMN_U_D_A_ACTIONREQUIRED_2 = "userNotificationEvent.actionRequired = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_A_A = new FinderPath(UserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationEventModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationEventImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_A_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_A_A = new FinderPath(UserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationEventModelImpl.FINDER_CACHE_ENABLED,
			UserNotificationEventImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_A_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			},
			UserNotificationEventModelImpl.USERID_COLUMN_BITMASK |
			UserNotificationEventModelImpl.ACTIONREQUIRED_COLUMN_BITMASK |
			UserNotificationEventModelImpl.ARCHIVED_COLUMN_BITMASK |
			UserNotificationEventModelImpl.TIMESTAMP_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_A_A = new FinderPath(UserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationEventModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_A_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the user notification events where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	 *
	 * @param userId the user ID
	 * @param actionRequired the action required
	 * @param archived the archived
	 * @return the matching user notification events
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<UserNotificationEvent> findByU_A_A(long userId,
		boolean actionRequired, boolean archived) throws SystemException {
		return findByU_A_A(userId, actionRequired, archived, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user notification events where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.notifications.model.impl.UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param actionRequired the action required
	 * @param archived the archived
	 * @param start the lower bound of the range of user notification events
	 * @param end the upper bound of the range of user notification events (not inclusive)
	 * @return the range of matching user notification events
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<UserNotificationEvent> findByU_A_A(long userId,
		boolean actionRequired, boolean archived, int start, int end)
		throws SystemException {
		return findByU_A_A(userId, actionRequired, archived, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user notification events where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.notifications.model.impl.UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param actionRequired the action required
	 * @param archived the archived
	 * @param start the lower bound of the range of user notification events
	 * @param end the upper bound of the range of user notification events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user notification events
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<UserNotificationEvent> findByU_A_A(long userId,
		boolean actionRequired, boolean archived, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_A_A;
			finderArgs = new Object[] { userId, actionRequired, archived };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_A_A;
			finderArgs = new Object[] {
					userId, actionRequired, archived,
					
					start, end, orderByComparator
				};
		}

		List<UserNotificationEvent> list = (List<UserNotificationEvent>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (UserNotificationEvent userNotificationEvent : list) {
				if ((userId != userNotificationEvent.getUserId()) ||
						(actionRequired != userNotificationEvent.getActionRequired()) ||
						(archived != userNotificationEvent.getArchived())) {
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

			query.append(_SQL_SELECT_USERNOTIFICATIONEVENT_WHERE);

			query.append(_FINDER_COLUMN_U_A_A_USERID_2);

			query.append(_FINDER_COLUMN_U_A_A_ACTIONREQUIRED_2);

			query.append(_FINDER_COLUMN_U_A_A_ARCHIVED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(UserNotificationEventModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(actionRequired);

				qPos.add(archived);

				if (!pagination) {
					list = (List<UserNotificationEvent>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<UserNotificationEvent>(list);
				}
				else {
					list = (List<UserNotificationEvent>)QueryUtil.list(q,
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
	 * Returns the first user notification event in the ordered set where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	 *
	 * @param userId the user ID
	 * @param actionRequired the action required
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user notification event
	 * @throws com.liferay.notifications.NoSuchUserNotificationEventException if a matching user notification event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserNotificationEvent findByU_A_A_First(long userId,
		boolean actionRequired, boolean archived,
		OrderByComparator orderByComparator)
		throws NoSuchUserNotificationEventException, SystemException {
		UserNotificationEvent userNotificationEvent = fetchByU_A_A_First(userId,
				actionRequired, archived, orderByComparator);

		if (userNotificationEvent != null) {
			return userNotificationEvent;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", actionRequired=");
		msg.append(actionRequired);

		msg.append(", archived=");
		msg.append(archived);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserNotificationEventException(msg.toString());
	}

	/**
	 * Returns the first user notification event in the ordered set where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	 *
	 * @param userId the user ID
	 * @param actionRequired the action required
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user notification event, or <code>null</code> if a matching user notification event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserNotificationEvent fetchByU_A_A_First(long userId,
		boolean actionRequired, boolean archived,
		OrderByComparator orderByComparator) throws SystemException {
		List<UserNotificationEvent> list = findByU_A_A(userId, actionRequired,
				archived, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user notification event in the ordered set where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	 *
	 * @param userId the user ID
	 * @param actionRequired the action required
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user notification event
	 * @throws com.liferay.notifications.NoSuchUserNotificationEventException if a matching user notification event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserNotificationEvent findByU_A_A_Last(long userId,
		boolean actionRequired, boolean archived,
		OrderByComparator orderByComparator)
		throws NoSuchUserNotificationEventException, SystemException {
		UserNotificationEvent userNotificationEvent = fetchByU_A_A_Last(userId,
				actionRequired, archived, orderByComparator);

		if (userNotificationEvent != null) {
			return userNotificationEvent;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", actionRequired=");
		msg.append(actionRequired);

		msg.append(", archived=");
		msg.append(archived);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserNotificationEventException(msg.toString());
	}

	/**
	 * Returns the last user notification event in the ordered set where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	 *
	 * @param userId the user ID
	 * @param actionRequired the action required
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user notification event, or <code>null</code> if a matching user notification event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserNotificationEvent fetchByU_A_A_Last(long userId,
		boolean actionRequired, boolean archived,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByU_A_A(userId, actionRequired, archived);

		if (count == 0) {
			return null;
		}

		List<UserNotificationEvent> list = findByU_A_A(userId, actionRequired,
				archived, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user notification events before and after the current user notification event in the ordered set where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	 *
	 * @param notificationEventId the primary key of the current user notification event
	 * @param userId the user ID
	 * @param actionRequired the action required
	 * @param archived the archived
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user notification event
	 * @throws com.liferay.notifications.NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserNotificationEvent[] findByU_A_A_PrevAndNext(
		long notificationEventId, long userId, boolean actionRequired,
		boolean archived, OrderByComparator orderByComparator)
		throws NoSuchUserNotificationEventException, SystemException {
		UserNotificationEvent userNotificationEvent = findByPrimaryKey(notificationEventId);

		Session session = null;

		try {
			session = openSession();

			UserNotificationEvent[] array = new UserNotificationEventImpl[3];

			array[0] = getByU_A_A_PrevAndNext(session, userNotificationEvent,
					userId, actionRequired, archived, orderByComparator, true);

			array[1] = userNotificationEvent;

			array[2] = getByU_A_A_PrevAndNext(session, userNotificationEvent,
					userId, actionRequired, archived, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected UserNotificationEvent getByU_A_A_PrevAndNext(Session session,
		UserNotificationEvent userNotificationEvent, long userId,
		boolean actionRequired, boolean archived,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_USERNOTIFICATIONEVENT_WHERE);

		query.append(_FINDER_COLUMN_U_A_A_USERID_2);

		query.append(_FINDER_COLUMN_U_A_A_ACTIONREQUIRED_2);

		query.append(_FINDER_COLUMN_U_A_A_ARCHIVED_2);

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
			query.append(UserNotificationEventModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(actionRequired);

		qPos.add(archived);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(userNotificationEvent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UserNotificationEvent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user notification events where userId = &#63; and actionRequired = &#63; and archived = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param actionRequired the action required
	 * @param archived the archived
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByU_A_A(long userId, boolean actionRequired,
		boolean archived) throws SystemException {
		for (UserNotificationEvent userNotificationEvent : findByU_A_A(userId,
				actionRequired, archived, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(userNotificationEvent);
		}
	}

	/**
	 * Returns the number of user notification events where userId = &#63; and actionRequired = &#63; and archived = &#63;.
	 *
	 * @param userId the user ID
	 * @param actionRequired the action required
	 * @param archived the archived
	 * @return the number of matching user notification events
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByU_A_A(long userId, boolean actionRequired,
		boolean archived) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_A_A;

		Object[] finderArgs = new Object[] { userId, actionRequired, archived };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_USERNOTIFICATIONEVENT_WHERE);

			query.append(_FINDER_COLUMN_U_A_A_USERID_2);

			query.append(_FINDER_COLUMN_U_A_A_ACTIONREQUIRED_2);

			query.append(_FINDER_COLUMN_U_A_A_ARCHIVED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(actionRequired);

				qPos.add(archived);

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

	private static final String _FINDER_COLUMN_U_A_A_USERID_2 = "userNotificationEvent.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_A_A_ACTIONREQUIRED_2 = "userNotificationEvent.actionRequired = ? AND ";
	private static final String _FINDER_COLUMN_U_A_A_ARCHIVED_2 = "userNotificationEvent.archived = ?";

	public UserNotificationEventPersistenceImpl() {
		setModelClass(UserNotificationEvent.class);
	}

	/**
	 * Caches the user notification event in the entity cache if it is enabled.
	 *
	 * @param userNotificationEvent the user notification event
	 */
	@Override
	public void cacheResult(UserNotificationEvent userNotificationEvent) {
		EntityCacheUtil.putResult(UserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationEventImpl.class,
			userNotificationEvent.getPrimaryKey(), userNotificationEvent);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERNOTIFICATIONEVENTID,
			new Object[] { userNotificationEvent.getUserNotificationEventId() },
			userNotificationEvent);

		userNotificationEvent.resetOriginalValues();
	}

	/**
	 * Caches the user notification events in the entity cache if it is enabled.
	 *
	 * @param userNotificationEvents the user notification events
	 */
	@Override
	public void cacheResult(List<UserNotificationEvent> userNotificationEvents) {
		for (UserNotificationEvent userNotificationEvent : userNotificationEvents) {
			if (EntityCacheUtil.getResult(
						UserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
						UserNotificationEventImpl.class,
						userNotificationEvent.getPrimaryKey()) == null) {
				cacheResult(userNotificationEvent);
			}
			else {
				userNotificationEvent.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all user notification events.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(UserNotificationEventImpl.class.getName());
		}

		EntityCacheUtil.clearCache(UserNotificationEventImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the user notification event.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UserNotificationEvent userNotificationEvent) {
		EntityCacheUtil.removeResult(UserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationEventImpl.class,
			userNotificationEvent.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(userNotificationEvent);
	}

	@Override
	public void clearCache(List<UserNotificationEvent> userNotificationEvents) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (UserNotificationEvent userNotificationEvent : userNotificationEvents) {
			EntityCacheUtil.removeResult(UserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
				UserNotificationEventImpl.class,
				userNotificationEvent.getPrimaryKey());

			clearUniqueFindersCache(userNotificationEvent);
		}
	}

	protected void cacheUniqueFindersCache(
		UserNotificationEvent userNotificationEvent) {
		if (userNotificationEvent.isNew()) {
			Object[] args = new Object[] {
					userNotificationEvent.getUserNotificationEventId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERNOTIFICATIONEVENTID,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERNOTIFICATIONEVENTID,
				args, userNotificationEvent);
		}
		else {
			UserNotificationEventModelImpl userNotificationEventModelImpl = (UserNotificationEventModelImpl)userNotificationEvent;

			if ((userNotificationEventModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_USERNOTIFICATIONEVENTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						userNotificationEvent.getUserNotificationEventId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERNOTIFICATIONEVENTID,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERNOTIFICATIONEVENTID,
					args, userNotificationEvent);
			}
		}
	}

	protected void clearUniqueFindersCache(
		UserNotificationEvent userNotificationEvent) {
		UserNotificationEventModelImpl userNotificationEventModelImpl = (UserNotificationEventModelImpl)userNotificationEvent;

		Object[] args = new Object[] {
				userNotificationEvent.getUserNotificationEventId()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERNOTIFICATIONEVENTID,
			args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERNOTIFICATIONEVENTID,
			args);

		if ((userNotificationEventModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_USERNOTIFICATIONEVENTID.getColumnBitmask()) != 0) {
			args = new Object[] {
					userNotificationEventModelImpl.getOriginalUserNotificationEventId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERNOTIFICATIONEVENTID,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERNOTIFICATIONEVENTID,
				args);
		}
	}

	/**
	 * Creates a new user notification event with the primary key. Does not add the user notification event to the database.
	 *
	 * @param notificationEventId the primary key for the new user notification event
	 * @return the new user notification event
	 */
	@Override
	public UserNotificationEvent create(long notificationEventId) {
		UserNotificationEvent userNotificationEvent = new UserNotificationEventImpl();

		userNotificationEvent.setNew(true);
		userNotificationEvent.setPrimaryKey(notificationEventId);

		return userNotificationEvent;
	}

	/**
	 * Removes the user notification event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param notificationEventId the primary key of the user notification event
	 * @return the user notification event that was removed
	 * @throws com.liferay.notifications.NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserNotificationEvent remove(long notificationEventId)
		throws NoSuchUserNotificationEventException, SystemException {
		return remove((Serializable)notificationEventId);
	}

	/**
	 * Removes the user notification event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the user notification event
	 * @return the user notification event that was removed
	 * @throws com.liferay.notifications.NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserNotificationEvent remove(Serializable primaryKey)
		throws NoSuchUserNotificationEventException, SystemException {
		Session session = null;

		try {
			session = openSession();

			UserNotificationEvent userNotificationEvent = (UserNotificationEvent)session.get(UserNotificationEventImpl.class,
					primaryKey);

			if (userNotificationEvent == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUserNotificationEventException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(userNotificationEvent);
		}
		catch (NoSuchUserNotificationEventException nsee) {
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
	protected UserNotificationEvent removeImpl(
		UserNotificationEvent userNotificationEvent) throws SystemException {
		userNotificationEvent = toUnwrappedModel(userNotificationEvent);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(userNotificationEvent)) {
				userNotificationEvent = (UserNotificationEvent)session.get(UserNotificationEventImpl.class,
						userNotificationEvent.getPrimaryKeyObj());
			}

			if (userNotificationEvent != null) {
				session.delete(userNotificationEvent);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (userNotificationEvent != null) {
			clearCache(userNotificationEvent);
		}

		return userNotificationEvent;
	}

	@Override
	public UserNotificationEvent updateImpl(
		com.liferay.notifications.model.UserNotificationEvent userNotificationEvent)
		throws SystemException {
		userNotificationEvent = toUnwrappedModel(userNotificationEvent);

		boolean isNew = userNotificationEvent.isNew();

		UserNotificationEventModelImpl userNotificationEventModelImpl = (UserNotificationEventModelImpl)userNotificationEvent;

		Session session = null;

		try {
			session = openSession();

			if (userNotificationEvent.isNew()) {
				session.save(userNotificationEvent);

				userNotificationEvent.setNew(false);
			}
			else {
				session.merge(userNotificationEvent);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !UserNotificationEventModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((userNotificationEventModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						userNotificationEventModelImpl.getOriginalUserId(),
						userNotificationEventModelImpl.getOriginalActionRequired()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_A,
					args);

				args = new Object[] {
						userNotificationEventModelImpl.getUserId(),
						userNotificationEventModelImpl.getActionRequired()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_A,
					args);
			}

			if ((userNotificationEventModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_D_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						userNotificationEventModelImpl.getOriginalUserId(),
						userNotificationEventModelImpl.getOriginalDelivered(),
						userNotificationEventModelImpl.getOriginalActionRequired()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_D_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_D_A,
					args);

				args = new Object[] {
						userNotificationEventModelImpl.getUserId(),
						userNotificationEventModelImpl.getDelivered(),
						userNotificationEventModelImpl.getActionRequired()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_D_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_D_A,
					args);
			}

			if ((userNotificationEventModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_A_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						userNotificationEventModelImpl.getOriginalUserId(),
						userNotificationEventModelImpl.getOriginalActionRequired(),
						userNotificationEventModelImpl.getOriginalArchived()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_A_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_A_A,
					args);

				args = new Object[] {
						userNotificationEventModelImpl.getUserId(),
						userNotificationEventModelImpl.getActionRequired(),
						userNotificationEventModelImpl.getArchived()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_A_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_A_A,
					args);
			}
		}

		EntityCacheUtil.putResult(UserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
			UserNotificationEventImpl.class,
			userNotificationEvent.getPrimaryKey(), userNotificationEvent);

		clearUniqueFindersCache(userNotificationEvent);
		cacheUniqueFindersCache(userNotificationEvent);

		return userNotificationEvent;
	}

	protected UserNotificationEvent toUnwrappedModel(
		UserNotificationEvent userNotificationEvent) {
		if (userNotificationEvent instanceof UserNotificationEventImpl) {
			return userNotificationEvent;
		}

		UserNotificationEventImpl userNotificationEventImpl = new UserNotificationEventImpl();

		userNotificationEventImpl.setNew(userNotificationEvent.isNew());
		userNotificationEventImpl.setPrimaryKey(userNotificationEvent.getPrimaryKey());

		userNotificationEventImpl.setNotificationEventId(userNotificationEvent.getNotificationEventId());
		userNotificationEventImpl.setCompanyId(userNotificationEvent.getCompanyId());
		userNotificationEventImpl.setUserId(userNotificationEvent.getUserId());
		userNotificationEventImpl.setUserNotificationEventId(userNotificationEvent.getUserNotificationEventId());
		userNotificationEventImpl.setTimestamp(userNotificationEvent.getTimestamp());
		userNotificationEventImpl.setDelivered(userNotificationEvent.isDelivered());
		userNotificationEventImpl.setActionRequired(userNotificationEvent.isActionRequired());
		userNotificationEventImpl.setArchived(userNotificationEvent.isArchived());

		return userNotificationEventImpl;
	}

	/**
	 * Returns the user notification event with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the user notification event
	 * @return the user notification event
	 * @throws com.liferay.notifications.NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserNotificationEvent findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUserNotificationEventException, SystemException {
		UserNotificationEvent userNotificationEvent = fetchByPrimaryKey(primaryKey);

		if (userNotificationEvent == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUserNotificationEventException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return userNotificationEvent;
	}

	/**
	 * Returns the user notification event with the primary key or throws a {@link com.liferay.notifications.NoSuchUserNotificationEventException} if it could not be found.
	 *
	 * @param notificationEventId the primary key of the user notification event
	 * @return the user notification event
	 * @throws com.liferay.notifications.NoSuchUserNotificationEventException if a user notification event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserNotificationEvent findByPrimaryKey(long notificationEventId)
		throws NoSuchUserNotificationEventException, SystemException {
		return findByPrimaryKey((Serializable)notificationEventId);
	}

	/**
	 * Returns the user notification event with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the user notification event
	 * @return the user notification event, or <code>null</code> if a user notification event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserNotificationEvent fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		UserNotificationEvent userNotificationEvent = (UserNotificationEvent)EntityCacheUtil.getResult(UserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
				UserNotificationEventImpl.class, primaryKey);

		if (userNotificationEvent == _nullUserNotificationEvent) {
			return null;
		}

		if (userNotificationEvent == null) {
			Session session = null;

			try {
				session = openSession();

				userNotificationEvent = (UserNotificationEvent)session.get(UserNotificationEventImpl.class,
						primaryKey);

				if (userNotificationEvent != null) {
					cacheResult(userNotificationEvent);
				}
				else {
					EntityCacheUtil.putResult(UserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
						UserNotificationEventImpl.class, primaryKey,
						_nullUserNotificationEvent);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(UserNotificationEventModelImpl.ENTITY_CACHE_ENABLED,
					UserNotificationEventImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return userNotificationEvent;
	}

	/**
	 * Returns the user notification event with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param notificationEventId the primary key of the user notification event
	 * @return the user notification event, or <code>null</code> if a user notification event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserNotificationEvent fetchByPrimaryKey(long notificationEventId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)notificationEventId);
	}

	/**
	 * Returns all the user notification events.
	 *
	 * @return the user notification events
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<UserNotificationEvent> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user notification events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.notifications.model.impl.UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of user notification events
	 * @param end the upper bound of the range of user notification events (not inclusive)
	 * @return the range of user notification events
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<UserNotificationEvent> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the user notification events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.notifications.model.impl.UserNotificationEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of user notification events
	 * @param end the upper bound of the range of user notification events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user notification events
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<UserNotificationEvent> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
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

		List<UserNotificationEvent> list = (List<UserNotificationEvent>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_USERNOTIFICATIONEVENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_USERNOTIFICATIONEVENT;

				if (pagination) {
					sql = sql.concat(UserNotificationEventModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<UserNotificationEvent>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<UserNotificationEvent>(list);
				}
				else {
					list = (List<UserNotificationEvent>)QueryUtil.list(q,
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
	 * Removes all the user notification events from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (UserNotificationEvent userNotificationEvent : findAll()) {
			remove(userNotificationEvent);
		}
	}

	/**
	 * Returns the number of user notification events.
	 *
	 * @return the number of user notification events
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_USERNOTIFICATIONEVENT);

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
	 * Initializes the user notification event persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.notifications.model.UserNotificationEvent")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<UserNotificationEvent>> listenersList = new ArrayList<ModelListener<UserNotificationEvent>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<UserNotificationEvent>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(UserNotificationEventImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_USERNOTIFICATIONEVENT = "SELECT userNotificationEvent FROM UserNotificationEvent userNotificationEvent";
	private static final String _SQL_SELECT_USERNOTIFICATIONEVENT_WHERE = "SELECT userNotificationEvent FROM UserNotificationEvent userNotificationEvent WHERE ";
	private static final String _SQL_COUNT_USERNOTIFICATIONEVENT = "SELECT COUNT(userNotificationEvent) FROM UserNotificationEvent userNotificationEvent";
	private static final String _SQL_COUNT_USERNOTIFICATIONEVENT_WHERE = "SELECT COUNT(userNotificationEvent) FROM UserNotificationEvent userNotificationEvent WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "userNotificationEvent.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No UserNotificationEvent exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No UserNotificationEvent exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(UserNotificationEventPersistenceImpl.class);
	private static UserNotificationEvent _nullUserNotificationEvent = new UserNotificationEventImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<UserNotificationEvent> toCacheModel() {
				return _nullUserNotificationEventCacheModel;
			}
		};

	private static CacheModel<UserNotificationEvent> _nullUserNotificationEventCacheModel =
		new CacheModel<UserNotificationEvent>() {
			@Override
			public UserNotificationEvent toEntityModel() {
				return _nullUserNotificationEvent;
			}
		};
}