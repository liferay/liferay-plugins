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

package com.liferay.chat.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.chat.NoSuchStatusException;
import com.liferay.chat.model.Status;
import com.liferay.chat.model.impl.StatusImpl;
import com.liferay.chat.model.impl.StatusModelImpl;
import com.liferay.chat.service.persistence.StatusPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCache;
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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the status service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StatusPersistence
 * @see com.liferay.chat.service.persistence.StatusUtil
 * @generated
 */
@ProviderType
public class StatusPersistenceImpl extends BasePersistenceImpl<Status>
	implements StatusPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StatusUtil} to access the status persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StatusImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StatusModelImpl.ENTITY_CACHE_ENABLED,
			StatusModelImpl.FINDER_CACHE_ENABLED, StatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StatusModelImpl.ENTITY_CACHE_ENABLED,
			StatusModelImpl.FINDER_CACHE_ENABLED, StatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StatusModelImpl.ENTITY_CACHE_ENABLED,
			StatusModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_USERID = new FinderPath(StatusModelImpl.ENTITY_CACHE_ENABLED,
			StatusModelImpl.FINDER_CACHE_ENABLED, StatusImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUserId",
			new String[] { Long.class.getName() },
			StatusModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(StatusModelImpl.ENTITY_CACHE_ENABLED,
			StatusModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns the status where userId = &#63; or throws a {@link NoSuchStatusException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching status
	 * @throws NoSuchStatusException if a matching status could not be found
	 */
	@Override
	public Status findByUserId(long userId) throws NoSuchStatusException {
		Status status = fetchByUserId(userId);

		if (status == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchStatusException(msg.toString());
		}

		return status;
	}

	/**
	 * Returns the status where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching status, or <code>null</code> if a matching status could not be found
	 */
	@Override
	public Status fetchByUserId(long userId) {
		return fetchByUserId(userId, true);
	}

	/**
	 * Returns the status where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching status, or <code>null</code> if a matching status could not be found
	 */
	@Override
	public Status fetchByUserId(long userId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { userId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_USERID,
					finderArgs, this);
		}

		if (result instanceof Status) {
			Status status = (Status)result;

			if ((userId != status.getUserId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_STATUS_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				List<Status> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_USERID,
						finderArgs, list);
				}
				else {
					Status status = list.get(0);

					result = status;

					cacheResult(status);

					if ((status.getUserId() != userId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_USERID,
							finderArgs, status);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_USERID, finderArgs);

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
			return (Status)result;
		}
	}

	/**
	 * Removes the status where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the status that was removed
	 */
	@Override
	public Status removeByUserId(long userId) throws NoSuchStatusException {
		Status status = findByUserId(userId);

		return remove(status);
	}

	/**
	 * Returns the number of statuses where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching statuses
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_STATUS_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "status.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MODIFIEDDATE =
		new FinderPath(StatusModelImpl.ENTITY_CACHE_ENABLED,
			StatusModelImpl.FINDER_CACHE_ENABLED, StatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByModifiedDate",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDATE =
		new FinderPath(StatusModelImpl.ENTITY_CACHE_ENABLED,
			StatusModelImpl.FINDER_CACHE_ENABLED, StatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByModifiedDate",
			new String[] { Long.class.getName() },
			StatusModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MODIFIEDDATE = new FinderPath(StatusModelImpl.ENTITY_CACHE_ENABLED,
			StatusModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByModifiedDate",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the statuses where modifiedDate = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @return the matching statuses
	 */
	@Override
	public List<Status> findByModifiedDate(long modifiedDate) {
		return findByModifiedDate(modifiedDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the statuses where modifiedDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of statuses
	 * @param end the upper bound of the range of statuses (not inclusive)
	 * @return the range of matching statuses
	 */
	@Override
	public List<Status> findByModifiedDate(long modifiedDate, int start, int end) {
		return findByModifiedDate(modifiedDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the statuses where modifiedDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of statuses
	 * @param end the upper bound of the range of statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching statuses
	 */
	@Override
	public List<Status> findByModifiedDate(long modifiedDate, int start,
		int end, OrderByComparator<Status> orderByComparator) {
		return findByModifiedDate(modifiedDate, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the statuses where modifiedDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of statuses
	 * @param end the upper bound of the range of statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching statuses
	 */
	@Override
	public List<Status> findByModifiedDate(long modifiedDate, int start,
		int end, OrderByComparator<Status> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDATE;
			finderArgs = new Object[] { modifiedDate };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MODIFIEDDATE;
			finderArgs = new Object[] {
					modifiedDate,
					
					start, end, orderByComparator
				};
		}

		List<Status> list = null;

		if (retrieveFromCache) {
			list = (List<Status>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Status status : list) {
					if ((modifiedDate != status.getModifiedDate())) {
						list = null;

						break;
					}
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

			query.append(_SQL_SELECT_STATUS_WHERE);

			query.append(_FINDER_COLUMN_MODIFIEDDATE_MODIFIEDDATE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(StatusModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(modifiedDate);

				if (!pagination) {
					list = (List<Status>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Status>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first status in the ordered set where modifiedDate = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching status
	 * @throws NoSuchStatusException if a matching status could not be found
	 */
	@Override
	public Status findByModifiedDate_First(long modifiedDate,
		OrderByComparator<Status> orderByComparator)
		throws NoSuchStatusException {
		Status status = fetchByModifiedDate_First(modifiedDate,
				orderByComparator);

		if (status != null) {
			return status;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStatusException(msg.toString());
	}

	/**
	 * Returns the first status in the ordered set where modifiedDate = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching status, or <code>null</code> if a matching status could not be found
	 */
	@Override
	public Status fetchByModifiedDate_First(long modifiedDate,
		OrderByComparator<Status> orderByComparator) {
		List<Status> list = findByModifiedDate(modifiedDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last status in the ordered set where modifiedDate = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching status
	 * @throws NoSuchStatusException if a matching status could not be found
	 */
	@Override
	public Status findByModifiedDate_Last(long modifiedDate,
		OrderByComparator<Status> orderByComparator)
		throws NoSuchStatusException {
		Status status = fetchByModifiedDate_Last(modifiedDate, orderByComparator);

		if (status != null) {
			return status;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStatusException(msg.toString());
	}

	/**
	 * Returns the last status in the ordered set where modifiedDate = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching status, or <code>null</code> if a matching status could not be found
	 */
	@Override
	public Status fetchByModifiedDate_Last(long modifiedDate,
		OrderByComparator<Status> orderByComparator) {
		int count = countByModifiedDate(modifiedDate);

		if (count == 0) {
			return null;
		}

		List<Status> list = findByModifiedDate(modifiedDate, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the statuses before and after the current status in the ordered set where modifiedDate = &#63;.
	 *
	 * @param statusId the primary key of the current status
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next status
	 * @throws NoSuchStatusException if a status with the primary key could not be found
	 */
	@Override
	public Status[] findByModifiedDate_PrevAndNext(long statusId,
		long modifiedDate, OrderByComparator<Status> orderByComparator)
		throws NoSuchStatusException {
		Status status = findByPrimaryKey(statusId);

		Session session = null;

		try {
			session = openSession();

			Status[] array = new StatusImpl[3];

			array[0] = getByModifiedDate_PrevAndNext(session, status,
					modifiedDate, orderByComparator, true);

			array[1] = status;

			array[2] = getByModifiedDate_PrevAndNext(session, status,
					modifiedDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Status getByModifiedDate_PrevAndNext(Session session,
		Status status, long modifiedDate,
		OrderByComparator<Status> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_STATUS_WHERE);

		query.append(_FINDER_COLUMN_MODIFIEDDATE_MODIFIEDDATE_2);

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
			query.append(StatusModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(modifiedDate);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(status);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Status> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the statuses where modifiedDate = &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 */
	@Override
	public void removeByModifiedDate(long modifiedDate) {
		for (Status status : findByModifiedDate(modifiedDate,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(status);
		}
	}

	/**
	 * Returns the number of statuses where modifiedDate = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @return the number of matching statuses
	 */
	@Override
	public int countByModifiedDate(long modifiedDate) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MODIFIEDDATE;

		Object[] finderArgs = new Object[] { modifiedDate };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_STATUS_WHERE);

			query.append(_FINDER_COLUMN_MODIFIEDDATE_MODIFIEDDATE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(modifiedDate);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_MODIFIEDDATE_MODIFIEDDATE_2 = "status.modifiedDate = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ONLINE = new FinderPath(StatusModelImpl.ENTITY_CACHE_ENABLED,
			StatusModelImpl.FINDER_CACHE_ENABLED, StatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOnline",
			new String[] {
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ONLINE =
		new FinderPath(StatusModelImpl.ENTITY_CACHE_ENABLED,
			StatusModelImpl.FINDER_CACHE_ENABLED, StatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOnline",
			new String[] { Boolean.class.getName() },
			StatusModelImpl.ONLINE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ONLINE = new FinderPath(StatusModelImpl.ENTITY_CACHE_ENABLED,
			StatusModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOnline",
			new String[] { Boolean.class.getName() });

	/**
	 * Returns all the statuses where online = &#63;.
	 *
	 * @param online the online
	 * @return the matching statuses
	 */
	@Override
	public List<Status> findByOnline(boolean online) {
		return findByOnline(online, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the statuses where online = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param online the online
	 * @param start the lower bound of the range of statuses
	 * @param end the upper bound of the range of statuses (not inclusive)
	 * @return the range of matching statuses
	 */
	@Override
	public List<Status> findByOnline(boolean online, int start, int end) {
		return findByOnline(online, start, end, null);
	}

	/**
	 * Returns an ordered range of all the statuses where online = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param online the online
	 * @param start the lower bound of the range of statuses
	 * @param end the upper bound of the range of statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching statuses
	 */
	@Override
	public List<Status> findByOnline(boolean online, int start, int end,
		OrderByComparator<Status> orderByComparator) {
		return findByOnline(online, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the statuses where online = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param online the online
	 * @param start the lower bound of the range of statuses
	 * @param end the upper bound of the range of statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching statuses
	 */
	@Override
	public List<Status> findByOnline(boolean online, int start, int end,
		OrderByComparator<Status> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ONLINE;
			finderArgs = new Object[] { online };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ONLINE;
			finderArgs = new Object[] { online, start, end, orderByComparator };
		}

		List<Status> list = null;

		if (retrieveFromCache) {
			list = (List<Status>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Status status : list) {
					if ((online != status.getOnline())) {
						list = null;

						break;
					}
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

			query.append(_SQL_SELECT_STATUS_WHERE);

			query.append(_FINDER_COLUMN_ONLINE_ONLINE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(StatusModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(online);

				if (!pagination) {
					list = (List<Status>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Status>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first status in the ordered set where online = &#63;.
	 *
	 * @param online the online
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching status
	 * @throws NoSuchStatusException if a matching status could not be found
	 */
	@Override
	public Status findByOnline_First(boolean online,
		OrderByComparator<Status> orderByComparator)
		throws NoSuchStatusException {
		Status status = fetchByOnline_First(online, orderByComparator);

		if (status != null) {
			return status;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("online=");
		msg.append(online);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStatusException(msg.toString());
	}

	/**
	 * Returns the first status in the ordered set where online = &#63;.
	 *
	 * @param online the online
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching status, or <code>null</code> if a matching status could not be found
	 */
	@Override
	public Status fetchByOnline_First(boolean online,
		OrderByComparator<Status> orderByComparator) {
		List<Status> list = findByOnline(online, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last status in the ordered set where online = &#63;.
	 *
	 * @param online the online
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching status
	 * @throws NoSuchStatusException if a matching status could not be found
	 */
	@Override
	public Status findByOnline_Last(boolean online,
		OrderByComparator<Status> orderByComparator)
		throws NoSuchStatusException {
		Status status = fetchByOnline_Last(online, orderByComparator);

		if (status != null) {
			return status;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("online=");
		msg.append(online);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStatusException(msg.toString());
	}

	/**
	 * Returns the last status in the ordered set where online = &#63;.
	 *
	 * @param online the online
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching status, or <code>null</code> if a matching status could not be found
	 */
	@Override
	public Status fetchByOnline_Last(boolean online,
		OrderByComparator<Status> orderByComparator) {
		int count = countByOnline(online);

		if (count == 0) {
			return null;
		}

		List<Status> list = findByOnline(online, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the statuses before and after the current status in the ordered set where online = &#63;.
	 *
	 * @param statusId the primary key of the current status
	 * @param online the online
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next status
	 * @throws NoSuchStatusException if a status with the primary key could not be found
	 */
	@Override
	public Status[] findByOnline_PrevAndNext(long statusId, boolean online,
		OrderByComparator<Status> orderByComparator)
		throws NoSuchStatusException {
		Status status = findByPrimaryKey(statusId);

		Session session = null;

		try {
			session = openSession();

			Status[] array = new StatusImpl[3];

			array[0] = getByOnline_PrevAndNext(session, status, online,
					orderByComparator, true);

			array[1] = status;

			array[2] = getByOnline_PrevAndNext(session, status, online,
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

	protected Status getByOnline_PrevAndNext(Session session, Status status,
		boolean online, OrderByComparator<Status> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_STATUS_WHERE);

		query.append(_FINDER_COLUMN_ONLINE_ONLINE_2);

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
			query.append(StatusModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(online);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(status);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Status> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the statuses where online = &#63; from the database.
	 *
	 * @param online the online
	 */
	@Override
	public void removeByOnline(boolean online) {
		for (Status status : findByOnline(online, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(status);
		}
	}

	/**
	 * Returns the number of statuses where online = &#63;.
	 *
	 * @param online the online
	 * @return the number of matching statuses
	 */
	@Override
	public int countByOnline(boolean online) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ONLINE;

		Object[] finderArgs = new Object[] { online };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_STATUS_WHERE);

			query.append(_FINDER_COLUMN_ONLINE_ONLINE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(online);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_ONLINE_ONLINE_2 = "status.online = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_M_O = new FinderPath(StatusModelImpl.ENTITY_CACHE_ENABLED,
			StatusModelImpl.FINDER_CACHE_ENABLED, StatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByM_O",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_M_O = new FinderPath(StatusModelImpl.ENTITY_CACHE_ENABLED,
			StatusModelImpl.FINDER_CACHE_ENABLED, StatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByM_O",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			StatusModelImpl.MODIFIEDDATE_COLUMN_BITMASK |
			StatusModelImpl.ONLINE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_M_O = new FinderPath(StatusModelImpl.ENTITY_CACHE_ENABLED,
			StatusModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByM_O",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the statuses where modifiedDate = &#63; and online = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param online the online
	 * @return the matching statuses
	 */
	@Override
	public List<Status> findByM_O(long modifiedDate, boolean online) {
		return findByM_O(modifiedDate, online, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the statuses where modifiedDate = &#63; and online = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param online the online
	 * @param start the lower bound of the range of statuses
	 * @param end the upper bound of the range of statuses (not inclusive)
	 * @return the range of matching statuses
	 */
	@Override
	public List<Status> findByM_O(long modifiedDate, boolean online, int start,
		int end) {
		return findByM_O(modifiedDate, online, start, end, null);
	}

	/**
	 * Returns an ordered range of all the statuses where modifiedDate = &#63; and online = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param online the online
	 * @param start the lower bound of the range of statuses
	 * @param end the upper bound of the range of statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching statuses
	 */
	@Override
	public List<Status> findByM_O(long modifiedDate, boolean online, int start,
		int end, OrderByComparator<Status> orderByComparator) {
		return findByM_O(modifiedDate, online, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the statuses where modifiedDate = &#63; and online = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param online the online
	 * @param start the lower bound of the range of statuses
	 * @param end the upper bound of the range of statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching statuses
	 */
	@Override
	public List<Status> findByM_O(long modifiedDate, boolean online, int start,
		int end, OrderByComparator<Status> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_M_O;
			finderArgs = new Object[] { modifiedDate, online };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_M_O;
			finderArgs = new Object[] {
					modifiedDate, online,
					
					start, end, orderByComparator
				};
		}

		List<Status> list = null;

		if (retrieveFromCache) {
			list = (List<Status>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Status status : list) {
					if ((modifiedDate != status.getModifiedDate()) ||
							(online != status.getOnline())) {
						list = null;

						break;
					}
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

			query.append(_SQL_SELECT_STATUS_WHERE);

			query.append(_FINDER_COLUMN_M_O_MODIFIEDDATE_2);

			query.append(_FINDER_COLUMN_M_O_ONLINE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(StatusModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(modifiedDate);

				qPos.add(online);

				if (!pagination) {
					list = (List<Status>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Status>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first status in the ordered set where modifiedDate = &#63; and online = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param online the online
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching status
	 * @throws NoSuchStatusException if a matching status could not be found
	 */
	@Override
	public Status findByM_O_First(long modifiedDate, boolean online,
		OrderByComparator<Status> orderByComparator)
		throws NoSuchStatusException {
		Status status = fetchByM_O_First(modifiedDate, online, orderByComparator);

		if (status != null) {
			return status;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", online=");
		msg.append(online);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStatusException(msg.toString());
	}

	/**
	 * Returns the first status in the ordered set where modifiedDate = &#63; and online = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param online the online
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching status, or <code>null</code> if a matching status could not be found
	 */
	@Override
	public Status fetchByM_O_First(long modifiedDate, boolean online,
		OrderByComparator<Status> orderByComparator) {
		List<Status> list = findByM_O(modifiedDate, online, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last status in the ordered set where modifiedDate = &#63; and online = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param online the online
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching status
	 * @throws NoSuchStatusException if a matching status could not be found
	 */
	@Override
	public Status findByM_O_Last(long modifiedDate, boolean online,
		OrderByComparator<Status> orderByComparator)
		throws NoSuchStatusException {
		Status status = fetchByM_O_Last(modifiedDate, online, orderByComparator);

		if (status != null) {
			return status;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", online=");
		msg.append(online);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStatusException(msg.toString());
	}

	/**
	 * Returns the last status in the ordered set where modifiedDate = &#63; and online = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param online the online
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching status, or <code>null</code> if a matching status could not be found
	 */
	@Override
	public Status fetchByM_O_Last(long modifiedDate, boolean online,
		OrderByComparator<Status> orderByComparator) {
		int count = countByM_O(modifiedDate, online);

		if (count == 0) {
			return null;
		}

		List<Status> list = findByM_O(modifiedDate, online, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the statuses before and after the current status in the ordered set where modifiedDate = &#63; and online = &#63;.
	 *
	 * @param statusId the primary key of the current status
	 * @param modifiedDate the modified date
	 * @param online the online
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next status
	 * @throws NoSuchStatusException if a status with the primary key could not be found
	 */
	@Override
	public Status[] findByM_O_PrevAndNext(long statusId, long modifiedDate,
		boolean online, OrderByComparator<Status> orderByComparator)
		throws NoSuchStatusException {
		Status status = findByPrimaryKey(statusId);

		Session session = null;

		try {
			session = openSession();

			Status[] array = new StatusImpl[3];

			array[0] = getByM_O_PrevAndNext(session, status, modifiedDate,
					online, orderByComparator, true);

			array[1] = status;

			array[2] = getByM_O_PrevAndNext(session, status, modifiedDate,
					online, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Status getByM_O_PrevAndNext(Session session, Status status,
		long modifiedDate, boolean online,
		OrderByComparator<Status> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_STATUS_WHERE);

		query.append(_FINDER_COLUMN_M_O_MODIFIEDDATE_2);

		query.append(_FINDER_COLUMN_M_O_ONLINE_2);

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
			query.append(StatusModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(modifiedDate);

		qPos.add(online);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(status);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Status> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the statuses where modifiedDate = &#63; and online = &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 * @param online the online
	 */
	@Override
	public void removeByM_O(long modifiedDate, boolean online) {
		for (Status status : findByM_O(modifiedDate, online, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(status);
		}
	}

	/**
	 * Returns the number of statuses where modifiedDate = &#63; and online = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param online the online
	 * @return the number of matching statuses
	 */
	@Override
	public int countByM_O(long modifiedDate, boolean online) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_M_O;

		Object[] finderArgs = new Object[] { modifiedDate, online };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_STATUS_WHERE);

			query.append(_FINDER_COLUMN_M_O_MODIFIEDDATE_2);

			query.append(_FINDER_COLUMN_M_O_ONLINE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(modifiedDate);

				qPos.add(online);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_M_O_MODIFIEDDATE_2 = "status.modifiedDate = ? AND ";
	private static final String _FINDER_COLUMN_M_O_ONLINE_2 = "status.online = ?";

	public StatusPersistenceImpl() {
		setModelClass(Status.class);
	}

	/**
	 * Caches the status in the entity cache if it is enabled.
	 *
	 * @param status the status
	 */
	@Override
	public void cacheResult(Status status) {
		entityCache.putResult(StatusModelImpl.ENTITY_CACHE_ENABLED,
			StatusImpl.class, status.getPrimaryKey(), status);

		finderCache.putResult(FINDER_PATH_FETCH_BY_USERID,
			new Object[] { status.getUserId() }, status);

		status.resetOriginalValues();
	}

	/**
	 * Caches the statuses in the entity cache if it is enabled.
	 *
	 * @param statuses the statuses
	 */
	@Override
	public void cacheResult(List<Status> statuses) {
		for (Status status : statuses) {
			if (entityCache.getResult(StatusModelImpl.ENTITY_CACHE_ENABLED,
						StatusImpl.class, status.getPrimaryKey()) == null) {
				cacheResult(status);
			}
			else {
				status.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all statuses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StatusImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the status.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Status status) {
		entityCache.removeResult(StatusModelImpl.ENTITY_CACHE_ENABLED,
			StatusImpl.class, status.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((StatusModelImpl)status);
	}

	@Override
	public void clearCache(List<Status> statuses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Status status : statuses) {
			entityCache.removeResult(StatusModelImpl.ENTITY_CACHE_ENABLED,
				StatusImpl.class, status.getPrimaryKey());

			clearUniqueFindersCache((StatusModelImpl)status);
		}
	}

	protected void cacheUniqueFindersCache(StatusModelImpl statusModelImpl,
		boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] { statusModelImpl.getUserId() };

			finderCache.putResult(FINDER_PATH_COUNT_BY_USERID, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_USERID, args,
				statusModelImpl);
		}
		else {
			if ((statusModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { statusModelImpl.getUserId() };

				finderCache.putResult(FINDER_PATH_COUNT_BY_USERID, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_USERID, args,
					statusModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(StatusModelImpl statusModelImpl) {
		Object[] args = new Object[] { statusModelImpl.getUserId() };

		finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_USERID, args);

		if ((statusModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_USERID.getColumnBitmask()) != 0) {
			args = new Object[] { statusModelImpl.getOriginalUserId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_USERID, args);
		}
	}

	/**
	 * Creates a new status with the primary key. Does not add the status to the database.
	 *
	 * @param statusId the primary key for the new status
	 * @return the new status
	 */
	@Override
	public Status create(long statusId) {
		Status status = new StatusImpl();

		status.setNew(true);
		status.setPrimaryKey(statusId);

		return status;
	}

	/**
	 * Removes the status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param statusId the primary key of the status
	 * @return the status that was removed
	 * @throws NoSuchStatusException if a status with the primary key could not be found
	 */
	@Override
	public Status remove(long statusId) throws NoSuchStatusException {
		return remove((Serializable)statusId);
	}

	/**
	 * Removes the status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the status
	 * @return the status that was removed
	 * @throws NoSuchStatusException if a status with the primary key could not be found
	 */
	@Override
	public Status remove(Serializable primaryKey) throws NoSuchStatusException {
		Session session = null;

		try {
			session = openSession();

			Status status = (Status)session.get(StatusImpl.class, primaryKey);

			if (status == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStatusException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(status);
		}
		catch (NoSuchStatusException nsee) {
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
	protected Status removeImpl(Status status) {
		status = toUnwrappedModel(status);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(status)) {
				status = (Status)session.get(StatusImpl.class,
						status.getPrimaryKeyObj());
			}

			if (status != null) {
				session.delete(status);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (status != null) {
			clearCache(status);
		}

		return status;
	}

	@Override
	public Status updateImpl(Status status) {
		status = toUnwrappedModel(status);

		boolean isNew = status.isNew();

		StatusModelImpl statusModelImpl = (StatusModelImpl)status;

		Session session = null;

		try {
			session = openSession();

			if (status.isNew()) {
				session.save(status);

				status.setNew(false);
			}
			else {
				status = (Status)session.merge(status);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !StatusModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((statusModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDATE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						statusModelImpl.getOriginalModifiedDate()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MODIFIEDDATE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDATE,
					args);

				args = new Object[] { statusModelImpl.getModifiedDate() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MODIFIEDDATE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDATE,
					args);
			}

			if ((statusModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ONLINE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { statusModelImpl.getOriginalOnline() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ONLINE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ONLINE,
					args);

				args = new Object[] { statusModelImpl.getOnline() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ONLINE, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ONLINE,
					args);
			}

			if ((statusModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_M_O.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						statusModelImpl.getOriginalModifiedDate(),
						statusModelImpl.getOriginalOnline()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_M_O, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_M_O,
					args);

				args = new Object[] {
						statusModelImpl.getModifiedDate(),
						statusModelImpl.getOnline()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_M_O, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_M_O,
					args);
			}
		}

		entityCache.putResult(StatusModelImpl.ENTITY_CACHE_ENABLED,
			StatusImpl.class, status.getPrimaryKey(), status, false);

		clearUniqueFindersCache(statusModelImpl);
		cacheUniqueFindersCache(statusModelImpl, isNew);

		status.resetOriginalValues();

		return status;
	}

	protected Status toUnwrappedModel(Status status) {
		if (status instanceof StatusImpl) {
			return status;
		}

		StatusImpl statusImpl = new StatusImpl();

		statusImpl.setNew(status.isNew());
		statusImpl.setPrimaryKey(status.getPrimaryKey());

		statusImpl.setStatusId(status.getStatusId());
		statusImpl.setUserId(status.getUserId());
		statusImpl.setModifiedDate(status.getModifiedDate());
		statusImpl.setOnline(status.isOnline());
		statusImpl.setAwake(status.isAwake());
		statusImpl.setActivePanelIds(status.getActivePanelIds());
		statusImpl.setMessage(status.getMessage());
		statusImpl.setPlaySound(status.isPlaySound());

		return statusImpl;
	}

	/**
	 * Returns the status with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the status
	 * @return the status
	 * @throws NoSuchStatusException if a status with the primary key could not be found
	 */
	@Override
	public Status findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStatusException {
		Status status = fetchByPrimaryKey(primaryKey);

		if (status == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStatusException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return status;
	}

	/**
	 * Returns the status with the primary key or throws a {@link NoSuchStatusException} if it could not be found.
	 *
	 * @param statusId the primary key of the status
	 * @return the status
	 * @throws NoSuchStatusException if a status with the primary key could not be found
	 */
	@Override
	public Status findByPrimaryKey(long statusId) throws NoSuchStatusException {
		return findByPrimaryKey((Serializable)statusId);
	}

	/**
	 * Returns the status with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the status
	 * @return the status, or <code>null</code> if a status with the primary key could not be found
	 */
	@Override
	public Status fetchByPrimaryKey(Serializable primaryKey) {
		Status status = (Status)entityCache.getResult(StatusModelImpl.ENTITY_CACHE_ENABLED,
				StatusImpl.class, primaryKey);

		if (status == _nullStatus) {
			return null;
		}

		if (status == null) {
			Session session = null;

			try {
				session = openSession();

				status = (Status)session.get(StatusImpl.class, primaryKey);

				if (status != null) {
					cacheResult(status);
				}
				else {
					entityCache.putResult(StatusModelImpl.ENTITY_CACHE_ENABLED,
						StatusImpl.class, primaryKey, _nullStatus);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StatusModelImpl.ENTITY_CACHE_ENABLED,
					StatusImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return status;
	}

	/**
	 * Returns the status with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param statusId the primary key of the status
	 * @return the status, or <code>null</code> if a status with the primary key could not be found
	 */
	@Override
	public Status fetchByPrimaryKey(long statusId) {
		return fetchByPrimaryKey((Serializable)statusId);
	}

	@Override
	public Map<Serializable, Status> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Status> map = new HashMap<Serializable, Status>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Status status = fetchByPrimaryKey(primaryKey);

			if (status != null) {
				map.put(primaryKey, status);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Status status = (Status)entityCache.getResult(StatusModelImpl.ENTITY_CACHE_ENABLED,
					StatusImpl.class, primaryKey);

			if (status == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, status);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_STATUS_WHERE_PKS_IN);

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

			for (Status status : (List<Status>)q.list()) {
				map.put(status.getPrimaryKeyObj(), status);

				cacheResult(status);

				uncachedPrimaryKeys.remove(status.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(StatusModelImpl.ENTITY_CACHE_ENABLED,
					StatusImpl.class, primaryKey, _nullStatus);
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
	 * Returns all the statuses.
	 *
	 * @return the statuses
	 */
	@Override
	public List<Status> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of statuses
	 * @param end the upper bound of the range of statuses (not inclusive)
	 * @return the range of statuses
	 */
	@Override
	public List<Status> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of statuses
	 * @param end the upper bound of the range of statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of statuses
	 */
	@Override
	public List<Status> findAll(int start, int end,
		OrderByComparator<Status> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of statuses
	 * @param end the upper bound of the range of statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of statuses
	 */
	@Override
	public List<Status> findAll(int start, int end,
		OrderByComparator<Status> orderByComparator, boolean retrieveFromCache) {
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

		List<Status> list = null;

		if (retrieveFromCache) {
			list = (List<Status>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_STATUS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STATUS;

				if (pagination) {
					sql = sql.concat(StatusModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Status>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Status>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Removes all the statuses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Status status : findAll()) {
			remove(status);
		}
	}

	/**
	 * Returns the number of statuses.
	 *
	 * @return the number of statuses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STATUS);

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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return StatusModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the status persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StatusImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_STATUS = "SELECT status FROM Status status";
	private static final String _SQL_SELECT_STATUS_WHERE_PKS_IN = "SELECT status FROM Status status WHERE statusId IN (";
	private static final String _SQL_SELECT_STATUS_WHERE = "SELECT status FROM Status status WHERE ";
	private static final String _SQL_COUNT_STATUS = "SELECT COUNT(status) FROM Status status";
	private static final String _SQL_COUNT_STATUS_WHERE = "SELECT COUNT(status) FROM Status status WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "status.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Status exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Status exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(StatusPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"online"
			});
	private static final Status _nullStatus = new StatusImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Status> toCacheModel() {
				return _nullStatusCacheModel;
			}
		};

	private static final CacheModel<Status> _nullStatusCacheModel = new CacheModel<Status>() {
			@Override
			public Status toEntityModel() {
				return _nullStatus;
			}
		};
}