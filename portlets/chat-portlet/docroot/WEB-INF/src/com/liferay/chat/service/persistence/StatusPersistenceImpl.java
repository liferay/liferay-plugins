/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.chat.service.persistence;

import com.liferay.chat.NoSuchStatusException;
import com.liferay.chat.model.Status;
import com.liferay.chat.model.impl.StatusImpl;
import com.liferay.chat.model.impl.StatusModelImpl;

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
import com.liferay.portal.kernel.util.SetUtil;
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
 * @see StatusUtil
 * @generated
 */
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
	 * Returns the status where userId = &#63; or throws a {@link com.liferay.chat.NoSuchStatusException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching status
	 * @throws com.liferay.chat.NoSuchStatusException if a matching status could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Status findByUserId(long userId)
		throws NoSuchStatusException, SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Status fetchByUserId(long userId) throws SystemException {
		return fetchByUserId(userId, true);
	}

	/**
	 * Returns the status where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching status, or <code>null</code> if a matching status could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Status fetchByUserId(long userId, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_USERID,
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
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERID,
						finderArgs, list);
				}
				else {
					Status status = list.get(0);

					result = status;

					cacheResult(status);

					if ((status.getUserId() != userId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERID,
							finderArgs, status);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERID,
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
			return (Status)result;
		}
	}

	/**
	 * Removes the status where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the status that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Status removeByUserId(long userId)
		throws NoSuchStatusException, SystemException {
		Status status = findByUserId(userId);

		return remove(status);
	}

	/**
	 * Returns the number of statuses where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching statuses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUserId(long userId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

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
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Status> findByModifiedDate(long modifiedDate)
		throws SystemException {
		return findByModifiedDate(modifiedDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the statuses where modifiedDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of statuses
	 * @param end the upper bound of the range of statuses (not inclusive)
	 * @return the range of matching statuses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Status> findByModifiedDate(long modifiedDate, int start, int end)
		throws SystemException {
		return findByModifiedDate(modifiedDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the statuses where modifiedDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of statuses
	 * @param end the upper bound of the range of statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching statuses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Status> findByModifiedDate(long modifiedDate, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
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

		List<Status> list = (List<Status>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Status status : list) {
				if ((modifiedDate != status.getModifiedDate())) {
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

					list = new UnmodifiableList<Status>(list);
				}
				else {
					list = (List<Status>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first status in the ordered set where modifiedDate = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching status
	 * @throws com.liferay.chat.NoSuchStatusException if a matching status could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Status findByModifiedDate_First(long modifiedDate,
		OrderByComparator orderByComparator)
		throws NoSuchStatusException, SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Status fetchByModifiedDate_First(long modifiedDate,
		OrderByComparator orderByComparator) throws SystemException {
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
	 * @throws com.liferay.chat.NoSuchStatusException if a matching status could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Status findByModifiedDate_Last(long modifiedDate,
		OrderByComparator orderByComparator)
		throws NoSuchStatusException, SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Status fetchByModifiedDate_Last(long modifiedDate,
		OrderByComparator orderByComparator) throws SystemException {
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
	 * @throws com.liferay.chat.NoSuchStatusException if a status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Status[] findByModifiedDate_PrevAndNext(long statusId,
		long modifiedDate, OrderByComparator orderByComparator)
		throws NoSuchStatusException, SystemException {
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
		Status status, long modifiedDate, OrderByComparator orderByComparator,
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
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByModifiedDate(long modifiedDate)
		throws SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByModifiedDate(long modifiedDate) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MODIFIEDDATE;

		Object[] finderArgs = new Object[] { modifiedDate };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

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
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Status> findByOnline(boolean online) throws SystemException {
		return findByOnline(online, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the statuses where online = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param online the online
	 * @param start the lower bound of the range of statuses
	 * @param end the upper bound of the range of statuses (not inclusive)
	 * @return the range of matching statuses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Status> findByOnline(boolean online, int start, int end)
		throws SystemException {
		return findByOnline(online, start, end, null);
	}

	/**
	 * Returns an ordered range of all the statuses where online = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param online the online
	 * @param start the lower bound of the range of statuses
	 * @param end the upper bound of the range of statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching statuses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Status> findByOnline(boolean online, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
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

		List<Status> list = (List<Status>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Status status : list) {
				if ((online != status.getOnline())) {
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

					list = new UnmodifiableList<Status>(list);
				}
				else {
					list = (List<Status>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first status in the ordered set where online = &#63;.
	 *
	 * @param online the online
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching status
	 * @throws com.liferay.chat.NoSuchStatusException if a matching status could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Status findByOnline_First(boolean online,
		OrderByComparator orderByComparator)
		throws NoSuchStatusException, SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Status fetchByOnline_First(boolean online,
		OrderByComparator orderByComparator) throws SystemException {
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
	 * @throws com.liferay.chat.NoSuchStatusException if a matching status could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Status findByOnline_Last(boolean online,
		OrderByComparator orderByComparator)
		throws NoSuchStatusException, SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Status fetchByOnline_Last(boolean online,
		OrderByComparator orderByComparator) throws SystemException {
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
	 * @throws com.liferay.chat.NoSuchStatusException if a status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Status[] findByOnline_PrevAndNext(long statusId, boolean online,
		OrderByComparator orderByComparator)
		throws NoSuchStatusException, SystemException {
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
		boolean online, OrderByComparator orderByComparator, boolean previous) {
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
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByOnline(boolean online) throws SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByOnline(boolean online) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ONLINE;

		Object[] finderArgs = new Object[] { online };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

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
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Status> findByM_O(long modifiedDate, boolean online)
		throws SystemException {
		return findByM_O(modifiedDate, online, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the statuses where modifiedDate = &#63; and online = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param online the online
	 * @param start the lower bound of the range of statuses
	 * @param end the upper bound of the range of statuses (not inclusive)
	 * @return the range of matching statuses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Status> findByM_O(long modifiedDate, boolean online, int start,
		int end) throws SystemException {
		return findByM_O(modifiedDate, online, start, end, null);
	}

	/**
	 * Returns an ordered range of all the statuses where modifiedDate = &#63; and online = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param online the online
	 * @param start the lower bound of the range of statuses
	 * @param end the upper bound of the range of statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching statuses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Status> findByM_O(long modifiedDate, boolean online, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
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

		List<Status> list = (List<Status>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Status status : list) {
				if ((modifiedDate != status.getModifiedDate()) ||
						(online != status.getOnline())) {
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

					list = new UnmodifiableList<Status>(list);
				}
				else {
					list = (List<Status>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first status in the ordered set where modifiedDate = &#63; and online = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param online the online
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching status
	 * @throws com.liferay.chat.NoSuchStatusException if a matching status could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Status findByM_O_First(long modifiedDate, boolean online,
		OrderByComparator orderByComparator)
		throws NoSuchStatusException, SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Status fetchByM_O_First(long modifiedDate, boolean online,
		OrderByComparator orderByComparator) throws SystemException {
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
	 * @throws com.liferay.chat.NoSuchStatusException if a matching status could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Status findByM_O_Last(long modifiedDate, boolean online,
		OrderByComparator orderByComparator)
		throws NoSuchStatusException, SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Status fetchByM_O_Last(long modifiedDate, boolean online,
		OrderByComparator orderByComparator) throws SystemException {
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
	 * @throws com.liferay.chat.NoSuchStatusException if a status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Status[] findByM_O_PrevAndNext(long statusId, long modifiedDate,
		boolean online, OrderByComparator orderByComparator)
		throws NoSuchStatusException, SystemException {
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
		long modifiedDate, boolean online, OrderByComparator orderByComparator,
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
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByM_O(long modifiedDate, boolean online)
		throws SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByM_O(long modifiedDate, boolean online)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_M_O;

		Object[] finderArgs = new Object[] { modifiedDate, online };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

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
		EntityCacheUtil.putResult(StatusModelImpl.ENTITY_CACHE_ENABLED,
			StatusImpl.class, status.getPrimaryKey(), status);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERID,
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
			if (EntityCacheUtil.getResult(
						StatusModelImpl.ENTITY_CACHE_ENABLED, StatusImpl.class,
						status.getPrimaryKey()) == null) {
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
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(StatusImpl.class.getName());
		}

		EntityCacheUtil.clearCache(StatusImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the status.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Status status) {
		EntityCacheUtil.removeResult(StatusModelImpl.ENTITY_CACHE_ENABLED,
			StatusImpl.class, status.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(status);
	}

	@Override
	public void clearCache(List<Status> statuses) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Status status : statuses) {
			EntityCacheUtil.removeResult(StatusModelImpl.ENTITY_CACHE_ENABLED,
				StatusImpl.class, status.getPrimaryKey());

			clearUniqueFindersCache(status);
		}
	}

	protected void cacheUniqueFindersCache(Status status) {
		if (status.isNew()) {
			Object[] args = new Object[] { status.getUserId() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERID, args, status);
		}
		else {
			StatusModelImpl statusModelImpl = (StatusModelImpl)status;

			if ((statusModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { status.getUserId() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERID, args,
					status);
			}
		}
	}

	protected void clearUniqueFindersCache(Status status) {
		StatusModelImpl statusModelImpl = (StatusModelImpl)status;

		Object[] args = new Object[] { status.getUserId() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERID, args);

		if ((statusModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_USERID.getColumnBitmask()) != 0) {
			args = new Object[] { statusModelImpl.getOriginalUserId() };

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERID, args);
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
	 * @throws com.liferay.chat.NoSuchStatusException if a status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Status remove(long statusId)
		throws NoSuchStatusException, SystemException {
		return remove((Serializable)statusId);
	}

	/**
	 * Removes the status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the status
	 * @return the status that was removed
	 * @throws com.liferay.chat.NoSuchStatusException if a status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Status remove(Serializable primaryKey)
		throws NoSuchStatusException, SystemException {
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
	protected Status removeImpl(Status status) throws SystemException {
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
	public Status updateImpl(com.liferay.chat.model.Status status)
		throws SystemException {
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
				session.merge(status);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !StatusModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((statusModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDATE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						statusModelImpl.getOriginalModifiedDate()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODIFIEDDATE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDATE,
					args);

				args = new Object[] { statusModelImpl.getModifiedDate() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODIFIEDDATE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODIFIEDDATE,
					args);
			}

			if ((statusModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ONLINE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { statusModelImpl.getOriginalOnline() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ONLINE, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ONLINE,
					args);

				args = new Object[] { statusModelImpl.getOnline() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ONLINE, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ONLINE,
					args);
			}

			if ((statusModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_M_O.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						statusModelImpl.getOriginalModifiedDate(),
						statusModelImpl.getOriginalOnline()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_M_O, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_M_O,
					args);

				args = new Object[] {
						statusModelImpl.getModifiedDate(),
						statusModelImpl.getOnline()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_M_O, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_M_O,
					args);
			}
		}

		EntityCacheUtil.putResult(StatusModelImpl.ENTITY_CACHE_ENABLED,
			StatusImpl.class, status.getPrimaryKey(), status);

		clearUniqueFindersCache(status);
		cacheUniqueFindersCache(status);

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
	 * @throws com.liferay.chat.NoSuchStatusException if a status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Status findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStatusException, SystemException {
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
	 * Returns the status with the primary key or throws a {@link com.liferay.chat.NoSuchStatusException} if it could not be found.
	 *
	 * @param statusId the primary key of the status
	 * @return the status
	 * @throws com.liferay.chat.NoSuchStatusException if a status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Status findByPrimaryKey(long statusId)
		throws NoSuchStatusException, SystemException {
		return findByPrimaryKey((Serializable)statusId);
	}

	/**
	 * Returns the status with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the status
	 * @return the status, or <code>null</code> if a status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Status fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Status status = (Status)EntityCacheUtil.getResult(StatusModelImpl.ENTITY_CACHE_ENABLED,
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
					EntityCacheUtil.putResult(StatusModelImpl.ENTITY_CACHE_ENABLED,
						StatusImpl.class, primaryKey, _nullStatus);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(StatusModelImpl.ENTITY_CACHE_ENABLED,
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
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Status fetchByPrimaryKey(long statusId) throws SystemException {
		return fetchByPrimaryKey((Serializable)statusId);
	}

	/**
	 * Returns all the statuses.
	 *
	 * @return the statuses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Status> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of statuses
	 * @param end the upper bound of the range of statuses (not inclusive)
	 * @return the range of statuses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Status> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of statuses
	 * @param end the upper bound of the range of statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of statuses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Status> findAll(int start, int end,
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

		List<Status> list = (List<Status>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

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

					list = new UnmodifiableList<Status>(list);
				}
				else {
					list = (List<Status>)QueryUtil.list(q, getDialect(), start,
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
	 * Removes all the statuses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Status status : findAll()) {
			remove(status);
		}
	}

	/**
	 * Returns the number of statuses.
	 *
	 * @return the number of statuses
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

				Query q = session.createQuery(_SQL_COUNT_STATUS);

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
	 * Initializes the status persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.chat.model.Status")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Status>> listenersList = new ArrayList<ModelListener<Status>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Status>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(StatusImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_STATUS = "SELECT status FROM Status status";
	private static final String _SQL_SELECT_STATUS_WHERE = "SELECT status FROM Status status WHERE ";
	private static final String _SQL_COUNT_STATUS = "SELECT COUNT(status) FROM Status status";
	private static final String _SQL_COUNT_STATUS_WHERE = "SELECT COUNT(status) FROM Status status WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "status.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Status exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Status exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(StatusPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"online"
			});
	private static Status _nullStatus = new StatusImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Status> toCacheModel() {
				return _nullStatusCacheModel;
			}
		};

	private static CacheModel<Status> _nullStatusCacheModel = new CacheModel<Status>() {
			@Override
			public Status toEntityModel() {
				return _nullStatus;
			}
		};
}