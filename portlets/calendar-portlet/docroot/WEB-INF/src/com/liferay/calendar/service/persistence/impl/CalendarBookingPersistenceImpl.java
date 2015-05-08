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

package com.liferay.calendar.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.calendar.NoSuchBookingException;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.impl.CalendarBookingImpl;
import com.liferay.calendar.model.impl.CalendarBookingModelImpl;
import com.liferay.calendar.service.persistence.CalendarBookingPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
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
 * The persistence implementation for the calendar booking service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarBookingPersistence
 * @see com.liferay.calendar.service.persistence.CalendarBookingUtil
 * @generated
 */
@ProviderType
public class CalendarBookingPersistenceImpl extends BasePersistenceImpl<CalendarBooking>
	implements CalendarBookingPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CalendarBookingUtil} to access the calendar booking persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CalendarBookingImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RESOURCEBLOCKID =
		new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByResourceBlockId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEBLOCKID =
		new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByResourceBlockId",
			new String[] { Long.class.getName() },
			CalendarBookingModelImpl.RESOURCEBLOCKID_COLUMN_BITMASK |
			CalendarBookingModelImpl.STARTTIME_COLUMN_BITMASK |
			CalendarBookingModelImpl.TITLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RESOURCEBLOCKID = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByResourceBlockId", new String[] { Long.class.getName() });

	/**
	 * Returns all the calendar bookings where resourceBlockId = &#63;.
	 *
	 * @param resourceBlockId the resource block ID
	 * @return the matching calendar bookings
	 */
	@Override
	public List<CalendarBooking> findByResourceBlockId(long resourceBlockId) {
		return findByResourceBlockId(resourceBlockId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar bookings where resourceBlockId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourceBlockId the resource block ID
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @return the range of matching calendar bookings
	 */
	@Override
	public List<CalendarBooking> findByResourceBlockId(long resourceBlockId,
		int start, int end) {
		return findByResourceBlockId(resourceBlockId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar bookings where resourceBlockId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourceBlockId the resource block ID
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar bookings
	 */
	@Override
	public List<CalendarBooking> findByResourceBlockId(long resourceBlockId,
		int start, int end, OrderByComparator<CalendarBooking> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEBLOCKID;
			finderArgs = new Object[] { resourceBlockId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RESOURCEBLOCKID;
			finderArgs = new Object[] {
					resourceBlockId,
					
					start, end, orderByComparator
				};
		}

		List<CalendarBooking> list = (List<CalendarBooking>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CalendarBooking calendarBooking : list) {
				if ((resourceBlockId != calendarBooking.getResourceBlockId())) {
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

			query.append(_SQL_SELECT_CALENDARBOOKING_WHERE);

			query.append(_FINDER_COLUMN_RESOURCEBLOCKID_RESOURCEBLOCKID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CalendarBookingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourceBlockId);

				if (!pagination) {
					list = (List<CalendarBooking>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CalendarBooking>)QueryUtil.list(q,
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
	 * Returns the first calendar booking in the ordered set where resourceBlockId = &#63;.
	 *
	 * @param resourceBlockId the resource block ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar booking
	 * @throws NoSuchBookingException if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking findByResourceBlockId_First(long resourceBlockId,
		OrderByComparator<CalendarBooking> orderByComparator)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = fetchByResourceBlockId_First(resourceBlockId,
				orderByComparator);

		if (calendarBooking != null) {
			return calendarBooking;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourceBlockId=");
		msg.append(resourceBlockId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBookingException(msg.toString());
	}

	/**
	 * Returns the first calendar booking in the ordered set where resourceBlockId = &#63;.
	 *
	 * @param resourceBlockId the resource block ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking fetchByResourceBlockId_First(long resourceBlockId,
		OrderByComparator<CalendarBooking> orderByComparator) {
		List<CalendarBooking> list = findByResourceBlockId(resourceBlockId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last calendar booking in the ordered set where resourceBlockId = &#63;.
	 *
	 * @param resourceBlockId the resource block ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar booking
	 * @throws NoSuchBookingException if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking findByResourceBlockId_Last(long resourceBlockId,
		OrderByComparator<CalendarBooking> orderByComparator)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = fetchByResourceBlockId_Last(resourceBlockId,
				orderByComparator);

		if (calendarBooking != null) {
			return calendarBooking;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourceBlockId=");
		msg.append(resourceBlockId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBookingException(msg.toString());
	}

	/**
	 * Returns the last calendar booking in the ordered set where resourceBlockId = &#63;.
	 *
	 * @param resourceBlockId the resource block ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking fetchByResourceBlockId_Last(long resourceBlockId,
		OrderByComparator<CalendarBooking> orderByComparator) {
		int count = countByResourceBlockId(resourceBlockId);

		if (count == 0) {
			return null;
		}

		List<CalendarBooking> list = findByResourceBlockId(resourceBlockId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the calendar bookings before and after the current calendar booking in the ordered set where resourceBlockId = &#63;.
	 *
	 * @param calendarBookingId the primary key of the current calendar booking
	 * @param resourceBlockId the resource block ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar booking
	 * @throws NoSuchBookingException if a calendar booking with the primary key could not be found
	 */
	@Override
	public CalendarBooking[] findByResourceBlockId_PrevAndNext(
		long calendarBookingId, long resourceBlockId,
		OrderByComparator<CalendarBooking> orderByComparator)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = findByPrimaryKey(calendarBookingId);

		Session session = null;

		try {
			session = openSession();

			CalendarBooking[] array = new CalendarBookingImpl[3];

			array[0] = getByResourceBlockId_PrevAndNext(session,
					calendarBooking, resourceBlockId, orderByComparator, true);

			array[1] = calendarBooking;

			array[2] = getByResourceBlockId_PrevAndNext(session,
					calendarBooking, resourceBlockId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CalendarBooking getByResourceBlockId_PrevAndNext(
		Session session, CalendarBooking calendarBooking, long resourceBlockId,
		OrderByComparator<CalendarBooking> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDARBOOKING_WHERE);

		query.append(_FINDER_COLUMN_RESOURCEBLOCKID_RESOURCEBLOCKID_2);

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
			query.append(CalendarBookingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourceBlockId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(calendarBooking);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CalendarBooking> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the calendar bookings where resourceBlockId = &#63; from the database.
	 *
	 * @param resourceBlockId the resource block ID
	 */
	@Override
	public void removeByResourceBlockId(long resourceBlockId) {
		for (CalendarBooking calendarBooking : findByResourceBlockId(
				resourceBlockId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(calendarBooking);
		}
	}

	/**
	 * Returns the number of calendar bookings where resourceBlockId = &#63;.
	 *
	 * @param resourceBlockId the resource block ID
	 * @return the number of matching calendar bookings
	 */
	@Override
	public int countByResourceBlockId(long resourceBlockId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_RESOURCEBLOCKID;

		Object[] finderArgs = new Object[] { resourceBlockId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CALENDARBOOKING_WHERE);

			query.append(_FINDER_COLUMN_RESOURCEBLOCKID_RESOURCEBLOCKID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourceBlockId);

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

	private static final String _FINDER_COLUMN_RESOURCEBLOCKID_RESOURCEBLOCKID_2 =
		"calendarBooking.resourceBlockId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			CalendarBookingModelImpl.UUID_COLUMN_BITMASK |
			CalendarBookingModelImpl.STARTTIME_COLUMN_BITMASK |
			CalendarBookingModelImpl.TITLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the calendar bookings where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching calendar bookings
	 */
	@Override
	public List<CalendarBooking> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar bookings where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @return the range of matching calendar bookings
	 */
	@Override
	public List<CalendarBooking> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar bookings where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar bookings
	 */
	@Override
	public List<CalendarBooking> findByUuid(String uuid, int start, int end,
		OrderByComparator<CalendarBooking> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<CalendarBooking> list = (List<CalendarBooking>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CalendarBooking calendarBooking : list) {
				if (!Validator.equals(uuid, calendarBooking.getUuid())) {
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

			query.append(_SQL_SELECT_CALENDARBOOKING_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CalendarBookingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<CalendarBooking>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CalendarBooking>)QueryUtil.list(q,
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
	 * Returns the first calendar booking in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar booking
	 * @throws NoSuchBookingException if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking findByUuid_First(String uuid,
		OrderByComparator<CalendarBooking> orderByComparator)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = fetchByUuid_First(uuid,
				orderByComparator);

		if (calendarBooking != null) {
			return calendarBooking;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBookingException(msg.toString());
	}

	/**
	 * Returns the first calendar booking in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking fetchByUuid_First(String uuid,
		OrderByComparator<CalendarBooking> orderByComparator) {
		List<CalendarBooking> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last calendar booking in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar booking
	 * @throws NoSuchBookingException if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking findByUuid_Last(String uuid,
		OrderByComparator<CalendarBooking> orderByComparator)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = fetchByUuid_Last(uuid,
				orderByComparator);

		if (calendarBooking != null) {
			return calendarBooking;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBookingException(msg.toString());
	}

	/**
	 * Returns the last calendar booking in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking fetchByUuid_Last(String uuid,
		OrderByComparator<CalendarBooking> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CalendarBooking> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the calendar bookings before and after the current calendar booking in the ordered set where uuid = &#63;.
	 *
	 * @param calendarBookingId the primary key of the current calendar booking
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar booking
	 * @throws NoSuchBookingException if a calendar booking with the primary key could not be found
	 */
	@Override
	public CalendarBooking[] findByUuid_PrevAndNext(long calendarBookingId,
		String uuid, OrderByComparator<CalendarBooking> orderByComparator)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = findByPrimaryKey(calendarBookingId);

		Session session = null;

		try {
			session = openSession();

			CalendarBooking[] array = new CalendarBookingImpl[3];

			array[0] = getByUuid_PrevAndNext(session, calendarBooking, uuid,
					orderByComparator, true);

			array[1] = calendarBooking;

			array[2] = getByUuid_PrevAndNext(session, calendarBooking, uuid,
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

	protected CalendarBooking getByUuid_PrevAndNext(Session session,
		CalendarBooking calendarBooking, String uuid,
		OrderByComparator<CalendarBooking> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDARBOOKING_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

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
			query.append(CalendarBookingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(calendarBooking);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CalendarBooking> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the calendar bookings where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CalendarBooking calendarBooking : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(calendarBooking);
		}
	}

	/**
	 * Returns the number of calendar bookings where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching calendar bookings
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CALENDARBOOKING_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "calendarBooking.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "calendarBooking.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(calendarBooking.uuid IS NULL OR calendarBooking.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			CalendarBookingModelImpl.UUID_COLUMN_BITMASK |
			CalendarBookingModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the calendar booking where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchBookingException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching calendar booking
	 * @throws NoSuchBookingException if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking findByUUID_G(String uuid, long groupId)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = fetchByUUID_G(uuid, groupId);

		if (calendarBooking == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchBookingException(msg.toString());
		}

		return calendarBooking;
	}

	/**
	 * Returns the calendar booking where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the calendar booking where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof CalendarBooking) {
			CalendarBooking calendarBooking = (CalendarBooking)result;

			if (!Validator.equals(uuid, calendarBooking.getUuid()) ||
					(groupId != calendarBooking.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CALENDARBOOKING_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<CalendarBooking> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					CalendarBooking calendarBooking = list.get(0);

					result = calendarBooking;

					cacheResult(calendarBooking);

					if ((calendarBooking.getUuid() == null) ||
							!calendarBooking.getUuid().equals(uuid) ||
							(calendarBooking.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, calendarBooking);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
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
			return (CalendarBooking)result;
		}
	}

	/**
	 * Removes the calendar booking where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the calendar booking that was removed
	 */
	@Override
	public CalendarBooking removeByUUID_G(String uuid, long groupId)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = findByUUID_G(uuid, groupId);

		return remove(calendarBooking);
	}

	/**
	 * Returns the number of calendar bookings where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching calendar bookings
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CALENDARBOOKING_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "calendarBooking.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "calendarBooking.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(calendarBooking.uuid IS NULL OR calendarBooking.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "calendarBooking.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			CalendarBookingModelImpl.UUID_COLUMN_BITMASK |
			CalendarBookingModelImpl.COMPANYID_COLUMN_BITMASK |
			CalendarBookingModelImpl.STARTTIME_COLUMN_BITMASK |
			CalendarBookingModelImpl.TITLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the calendar bookings where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching calendar bookings
	 */
	@Override
	public List<CalendarBooking> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar bookings where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @return the range of matching calendar bookings
	 */
	@Override
	public List<CalendarBooking> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar bookings where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar bookings
	 */
	@Override
	public List<CalendarBooking> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<CalendarBooking> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<CalendarBooking> list = (List<CalendarBooking>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CalendarBooking calendarBooking : list) {
				if (!Validator.equals(uuid, calendarBooking.getUuid()) ||
						(companyId != calendarBooking.getCompanyId())) {
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

			query.append(_SQL_SELECT_CALENDARBOOKING_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CalendarBookingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<CalendarBooking>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CalendarBooking>)QueryUtil.list(q,
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
	 * Returns the first calendar booking in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar booking
	 * @throws NoSuchBookingException if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<CalendarBooking> orderByComparator)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (calendarBooking != null) {
			return calendarBooking;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBookingException(msg.toString());
	}

	/**
	 * Returns the first calendar booking in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<CalendarBooking> orderByComparator) {
		List<CalendarBooking> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last calendar booking in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar booking
	 * @throws NoSuchBookingException if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<CalendarBooking> orderByComparator)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (calendarBooking != null) {
			return calendarBooking;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBookingException(msg.toString());
	}

	/**
	 * Returns the last calendar booking in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<CalendarBooking> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CalendarBooking> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the calendar bookings before and after the current calendar booking in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param calendarBookingId the primary key of the current calendar booking
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar booking
	 * @throws NoSuchBookingException if a calendar booking with the primary key could not be found
	 */
	@Override
	public CalendarBooking[] findByUuid_C_PrevAndNext(long calendarBookingId,
		String uuid, long companyId,
		OrderByComparator<CalendarBooking> orderByComparator)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = findByPrimaryKey(calendarBookingId);

		Session session = null;

		try {
			session = openSession();

			CalendarBooking[] array = new CalendarBookingImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, calendarBooking, uuid,
					companyId, orderByComparator, true);

			array[1] = calendarBooking;

			array[2] = getByUuid_C_PrevAndNext(session, calendarBooking, uuid,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CalendarBooking getByUuid_C_PrevAndNext(Session session,
		CalendarBooking calendarBooking, String uuid, long companyId,
		OrderByComparator<CalendarBooking> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDARBOOKING_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

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
			query.append(CalendarBookingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(calendarBooking);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CalendarBooking> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the calendar bookings where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CalendarBooking calendarBooking : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(calendarBooking);
		}
	}

	/**
	 * Returns the number of calendar bookings where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching calendar bookings
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CALENDARBOOKING_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "calendarBooking.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "calendarBooking.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(calendarBooking.uuid IS NULL OR calendarBooking.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "calendarBooking.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CALENDARID =
		new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCalendarId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALENDARID =
		new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCalendarId",
			new String[] { Long.class.getName() },
			CalendarBookingModelImpl.CALENDARID_COLUMN_BITMASK |
			CalendarBookingModelImpl.STARTTIME_COLUMN_BITMASK |
			CalendarBookingModelImpl.TITLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CALENDARID = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCalendarId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the calendar bookings where calendarId = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @return the matching calendar bookings
	 */
	@Override
	public List<CalendarBooking> findByCalendarId(long calendarId) {
		return findByCalendarId(calendarId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar bookings where calendarId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param calendarId the calendar ID
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @return the range of matching calendar bookings
	 */
	@Override
	public List<CalendarBooking> findByCalendarId(long calendarId, int start,
		int end) {
		return findByCalendarId(calendarId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar bookings where calendarId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param calendarId the calendar ID
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar bookings
	 */
	@Override
	public List<CalendarBooking> findByCalendarId(long calendarId, int start,
		int end, OrderByComparator<CalendarBooking> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALENDARID;
			finderArgs = new Object[] { calendarId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CALENDARID;
			finderArgs = new Object[] { calendarId, start, end, orderByComparator };
		}

		List<CalendarBooking> list = (List<CalendarBooking>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CalendarBooking calendarBooking : list) {
				if ((calendarId != calendarBooking.getCalendarId())) {
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

			query.append(_SQL_SELECT_CALENDARBOOKING_WHERE);

			query.append(_FINDER_COLUMN_CALENDARID_CALENDARID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CalendarBookingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(calendarId);

				if (!pagination) {
					list = (List<CalendarBooking>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CalendarBooking>)QueryUtil.list(q,
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
	 * Returns the first calendar booking in the ordered set where calendarId = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar booking
	 * @throws NoSuchBookingException if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking findByCalendarId_First(long calendarId,
		OrderByComparator<CalendarBooking> orderByComparator)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = fetchByCalendarId_First(calendarId,
				orderByComparator);

		if (calendarBooking != null) {
			return calendarBooking;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("calendarId=");
		msg.append(calendarId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBookingException(msg.toString());
	}

	/**
	 * Returns the first calendar booking in the ordered set where calendarId = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking fetchByCalendarId_First(long calendarId,
		OrderByComparator<CalendarBooking> orderByComparator) {
		List<CalendarBooking> list = findByCalendarId(calendarId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last calendar booking in the ordered set where calendarId = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar booking
	 * @throws NoSuchBookingException if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking findByCalendarId_Last(long calendarId,
		OrderByComparator<CalendarBooking> orderByComparator)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = fetchByCalendarId_Last(calendarId,
				orderByComparator);

		if (calendarBooking != null) {
			return calendarBooking;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("calendarId=");
		msg.append(calendarId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBookingException(msg.toString());
	}

	/**
	 * Returns the last calendar booking in the ordered set where calendarId = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking fetchByCalendarId_Last(long calendarId,
		OrderByComparator<CalendarBooking> orderByComparator) {
		int count = countByCalendarId(calendarId);

		if (count == 0) {
			return null;
		}

		List<CalendarBooking> list = findByCalendarId(calendarId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the calendar bookings before and after the current calendar booking in the ordered set where calendarId = &#63;.
	 *
	 * @param calendarBookingId the primary key of the current calendar booking
	 * @param calendarId the calendar ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar booking
	 * @throws NoSuchBookingException if a calendar booking with the primary key could not be found
	 */
	@Override
	public CalendarBooking[] findByCalendarId_PrevAndNext(
		long calendarBookingId, long calendarId,
		OrderByComparator<CalendarBooking> orderByComparator)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = findByPrimaryKey(calendarBookingId);

		Session session = null;

		try {
			session = openSession();

			CalendarBooking[] array = new CalendarBookingImpl[3];

			array[0] = getByCalendarId_PrevAndNext(session, calendarBooking,
					calendarId, orderByComparator, true);

			array[1] = calendarBooking;

			array[2] = getByCalendarId_PrevAndNext(session, calendarBooking,
					calendarId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CalendarBooking getByCalendarId_PrevAndNext(Session session,
		CalendarBooking calendarBooking, long calendarId,
		OrderByComparator<CalendarBooking> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDARBOOKING_WHERE);

		query.append(_FINDER_COLUMN_CALENDARID_CALENDARID_2);

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
			query.append(CalendarBookingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(calendarId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(calendarBooking);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CalendarBooking> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the calendar bookings where calendarId = &#63; from the database.
	 *
	 * @param calendarId the calendar ID
	 */
	@Override
	public void removeByCalendarId(long calendarId) {
		for (CalendarBooking calendarBooking : findByCalendarId(calendarId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(calendarBooking);
		}
	}

	/**
	 * Returns the number of calendar bookings where calendarId = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @return the number of matching calendar bookings
	 */
	@Override
	public int countByCalendarId(long calendarId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CALENDARID;

		Object[] finderArgs = new Object[] { calendarId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CALENDARBOOKING_WHERE);

			query.append(_FINDER_COLUMN_CALENDARID_CALENDARID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(calendarId);

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

	private static final String _FINDER_COLUMN_CALENDARID_CALENDARID_2 = "calendarBooking.calendarId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CALENDARRESOURCEID =
		new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCalendarResourceId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALENDARRESOURCEID =
		new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCalendarResourceId", new String[] { Long.class.getName() },
			CalendarBookingModelImpl.CALENDARRESOURCEID_COLUMN_BITMASK |
			CalendarBookingModelImpl.STARTTIME_COLUMN_BITMASK |
			CalendarBookingModelImpl.TITLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CALENDARRESOURCEID = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCalendarResourceId", new String[] { Long.class.getName() });

	/**
	 * Returns all the calendar bookings where calendarResourceId = &#63;.
	 *
	 * @param calendarResourceId the calendar resource ID
	 * @return the matching calendar bookings
	 */
	@Override
	public List<CalendarBooking> findByCalendarResourceId(
		long calendarResourceId) {
		return findByCalendarResourceId(calendarResourceId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar bookings where calendarResourceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param calendarResourceId the calendar resource ID
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @return the range of matching calendar bookings
	 */
	@Override
	public List<CalendarBooking> findByCalendarResourceId(
		long calendarResourceId, int start, int end) {
		return findByCalendarResourceId(calendarResourceId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar bookings where calendarResourceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param calendarResourceId the calendar resource ID
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar bookings
	 */
	@Override
	public List<CalendarBooking> findByCalendarResourceId(
		long calendarResourceId, int start, int end,
		OrderByComparator<CalendarBooking> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALENDARRESOURCEID;
			finderArgs = new Object[] { calendarResourceId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CALENDARRESOURCEID;
			finderArgs = new Object[] {
					calendarResourceId,
					
					start, end, orderByComparator
				};
		}

		List<CalendarBooking> list = (List<CalendarBooking>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CalendarBooking calendarBooking : list) {
				if ((calendarResourceId != calendarBooking.getCalendarResourceId())) {
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

			query.append(_SQL_SELECT_CALENDARBOOKING_WHERE);

			query.append(_FINDER_COLUMN_CALENDARRESOURCEID_CALENDARRESOURCEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CalendarBookingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(calendarResourceId);

				if (!pagination) {
					list = (List<CalendarBooking>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CalendarBooking>)QueryUtil.list(q,
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
	 * Returns the first calendar booking in the ordered set where calendarResourceId = &#63;.
	 *
	 * @param calendarResourceId the calendar resource ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar booking
	 * @throws NoSuchBookingException if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking findByCalendarResourceId_First(
		long calendarResourceId,
		OrderByComparator<CalendarBooking> orderByComparator)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = fetchByCalendarResourceId_First(calendarResourceId,
				orderByComparator);

		if (calendarBooking != null) {
			return calendarBooking;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("calendarResourceId=");
		msg.append(calendarResourceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBookingException(msg.toString());
	}

	/**
	 * Returns the first calendar booking in the ordered set where calendarResourceId = &#63;.
	 *
	 * @param calendarResourceId the calendar resource ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking fetchByCalendarResourceId_First(
		long calendarResourceId,
		OrderByComparator<CalendarBooking> orderByComparator) {
		List<CalendarBooking> list = findByCalendarResourceId(calendarResourceId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last calendar booking in the ordered set where calendarResourceId = &#63;.
	 *
	 * @param calendarResourceId the calendar resource ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar booking
	 * @throws NoSuchBookingException if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking findByCalendarResourceId_Last(
		long calendarResourceId,
		OrderByComparator<CalendarBooking> orderByComparator)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = fetchByCalendarResourceId_Last(calendarResourceId,
				orderByComparator);

		if (calendarBooking != null) {
			return calendarBooking;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("calendarResourceId=");
		msg.append(calendarResourceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBookingException(msg.toString());
	}

	/**
	 * Returns the last calendar booking in the ordered set where calendarResourceId = &#63;.
	 *
	 * @param calendarResourceId the calendar resource ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking fetchByCalendarResourceId_Last(
		long calendarResourceId,
		OrderByComparator<CalendarBooking> orderByComparator) {
		int count = countByCalendarResourceId(calendarResourceId);

		if (count == 0) {
			return null;
		}

		List<CalendarBooking> list = findByCalendarResourceId(calendarResourceId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the calendar bookings before and after the current calendar booking in the ordered set where calendarResourceId = &#63;.
	 *
	 * @param calendarBookingId the primary key of the current calendar booking
	 * @param calendarResourceId the calendar resource ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar booking
	 * @throws NoSuchBookingException if a calendar booking with the primary key could not be found
	 */
	@Override
	public CalendarBooking[] findByCalendarResourceId_PrevAndNext(
		long calendarBookingId, long calendarResourceId,
		OrderByComparator<CalendarBooking> orderByComparator)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = findByPrimaryKey(calendarBookingId);

		Session session = null;

		try {
			session = openSession();

			CalendarBooking[] array = new CalendarBookingImpl[3];

			array[0] = getByCalendarResourceId_PrevAndNext(session,
					calendarBooking, calendarResourceId, orderByComparator, true);

			array[1] = calendarBooking;

			array[2] = getByCalendarResourceId_PrevAndNext(session,
					calendarBooking, calendarResourceId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CalendarBooking getByCalendarResourceId_PrevAndNext(
		Session session, CalendarBooking calendarBooking,
		long calendarResourceId,
		OrderByComparator<CalendarBooking> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDARBOOKING_WHERE);

		query.append(_FINDER_COLUMN_CALENDARRESOURCEID_CALENDARRESOURCEID_2);

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
			query.append(CalendarBookingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(calendarResourceId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(calendarBooking);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CalendarBooking> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the calendar bookings where calendarResourceId = &#63; from the database.
	 *
	 * @param calendarResourceId the calendar resource ID
	 */
	@Override
	public void removeByCalendarResourceId(long calendarResourceId) {
		for (CalendarBooking calendarBooking : findByCalendarResourceId(
				calendarResourceId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(calendarBooking);
		}
	}

	/**
	 * Returns the number of calendar bookings where calendarResourceId = &#63;.
	 *
	 * @param calendarResourceId the calendar resource ID
	 * @return the number of matching calendar bookings
	 */
	@Override
	public int countByCalendarResourceId(long calendarResourceId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CALENDARRESOURCEID;

		Object[] finderArgs = new Object[] { calendarResourceId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CALENDARBOOKING_WHERE);

			query.append(_FINDER_COLUMN_CALENDARRESOURCEID_CALENDARRESOURCEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(calendarResourceId);

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

	private static final String _FINDER_COLUMN_CALENDARRESOURCEID_CALENDARRESOURCEID_2 =
		"calendarBooking.calendarResourceId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTCALENDARBOOKINGID =
		new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByParentCalendarBookingId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTCALENDARBOOKINGID =
		new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByParentCalendarBookingId",
			new String[] { Long.class.getName() },
			CalendarBookingModelImpl.PARENTCALENDARBOOKINGID_COLUMN_BITMASK |
			CalendarBookingModelImpl.STARTTIME_COLUMN_BITMASK |
			CalendarBookingModelImpl.TITLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PARENTCALENDARBOOKINGID = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByParentCalendarBookingId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the calendar bookings where parentCalendarBookingId = &#63;.
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @return the matching calendar bookings
	 */
	@Override
	public List<CalendarBooking> findByParentCalendarBookingId(
		long parentCalendarBookingId) {
		return findByParentCalendarBookingId(parentCalendarBookingId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar bookings where parentCalendarBookingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @return the range of matching calendar bookings
	 */
	@Override
	public List<CalendarBooking> findByParentCalendarBookingId(
		long parentCalendarBookingId, int start, int end) {
		return findByParentCalendarBookingId(parentCalendarBookingId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the calendar bookings where parentCalendarBookingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar bookings
	 */
	@Override
	public List<CalendarBooking> findByParentCalendarBookingId(
		long parentCalendarBookingId, int start, int end,
		OrderByComparator<CalendarBooking> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTCALENDARBOOKINGID;
			finderArgs = new Object[] { parentCalendarBookingId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTCALENDARBOOKINGID;
			finderArgs = new Object[] {
					parentCalendarBookingId,
					
					start, end, orderByComparator
				};
		}

		List<CalendarBooking> list = (List<CalendarBooking>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CalendarBooking calendarBooking : list) {
				if ((parentCalendarBookingId != calendarBooking.getParentCalendarBookingId())) {
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

			query.append(_SQL_SELECT_CALENDARBOOKING_WHERE);

			query.append(_FINDER_COLUMN_PARENTCALENDARBOOKINGID_PARENTCALENDARBOOKINGID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CalendarBookingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentCalendarBookingId);

				if (!pagination) {
					list = (List<CalendarBooking>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CalendarBooking>)QueryUtil.list(q,
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
	 * Returns the first calendar booking in the ordered set where parentCalendarBookingId = &#63;.
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar booking
	 * @throws NoSuchBookingException if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking findByParentCalendarBookingId_First(
		long parentCalendarBookingId,
		OrderByComparator<CalendarBooking> orderByComparator)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = fetchByParentCalendarBookingId_First(parentCalendarBookingId,
				orderByComparator);

		if (calendarBooking != null) {
			return calendarBooking;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentCalendarBookingId=");
		msg.append(parentCalendarBookingId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBookingException(msg.toString());
	}

	/**
	 * Returns the first calendar booking in the ordered set where parentCalendarBookingId = &#63;.
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking fetchByParentCalendarBookingId_First(
		long parentCalendarBookingId,
		OrderByComparator<CalendarBooking> orderByComparator) {
		List<CalendarBooking> list = findByParentCalendarBookingId(parentCalendarBookingId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last calendar booking in the ordered set where parentCalendarBookingId = &#63;.
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar booking
	 * @throws NoSuchBookingException if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking findByParentCalendarBookingId_Last(
		long parentCalendarBookingId,
		OrderByComparator<CalendarBooking> orderByComparator)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = fetchByParentCalendarBookingId_Last(parentCalendarBookingId,
				orderByComparator);

		if (calendarBooking != null) {
			return calendarBooking;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentCalendarBookingId=");
		msg.append(parentCalendarBookingId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBookingException(msg.toString());
	}

	/**
	 * Returns the last calendar booking in the ordered set where parentCalendarBookingId = &#63;.
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking fetchByParentCalendarBookingId_Last(
		long parentCalendarBookingId,
		OrderByComparator<CalendarBooking> orderByComparator) {
		int count = countByParentCalendarBookingId(parentCalendarBookingId);

		if (count == 0) {
			return null;
		}

		List<CalendarBooking> list = findByParentCalendarBookingId(parentCalendarBookingId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the calendar bookings before and after the current calendar booking in the ordered set where parentCalendarBookingId = &#63;.
	 *
	 * @param calendarBookingId the primary key of the current calendar booking
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar booking
	 * @throws NoSuchBookingException if a calendar booking with the primary key could not be found
	 */
	@Override
	public CalendarBooking[] findByParentCalendarBookingId_PrevAndNext(
		long calendarBookingId, long parentCalendarBookingId,
		OrderByComparator<CalendarBooking> orderByComparator)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = findByPrimaryKey(calendarBookingId);

		Session session = null;

		try {
			session = openSession();

			CalendarBooking[] array = new CalendarBookingImpl[3];

			array[0] = getByParentCalendarBookingId_PrevAndNext(session,
					calendarBooking, parentCalendarBookingId,
					orderByComparator, true);

			array[1] = calendarBooking;

			array[2] = getByParentCalendarBookingId_PrevAndNext(session,
					calendarBooking, parentCalendarBookingId,
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

	protected CalendarBooking getByParentCalendarBookingId_PrevAndNext(
		Session session, CalendarBooking calendarBooking,
		long parentCalendarBookingId,
		OrderByComparator<CalendarBooking> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDARBOOKING_WHERE);

		query.append(_FINDER_COLUMN_PARENTCALENDARBOOKINGID_PARENTCALENDARBOOKINGID_2);

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
			query.append(CalendarBookingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentCalendarBookingId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(calendarBooking);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CalendarBooking> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the calendar bookings where parentCalendarBookingId = &#63; from the database.
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 */
	@Override
	public void removeByParentCalendarBookingId(long parentCalendarBookingId) {
		for (CalendarBooking calendarBooking : findByParentCalendarBookingId(
				parentCalendarBookingId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(calendarBooking);
		}
	}

	/**
	 * Returns the number of calendar bookings where parentCalendarBookingId = &#63;.
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @return the number of matching calendar bookings
	 */
	@Override
	public int countByParentCalendarBookingId(long parentCalendarBookingId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PARENTCALENDARBOOKINGID;

		Object[] finderArgs = new Object[] { parentCalendarBookingId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CALENDARBOOKING_WHERE);

			query.append(_FINDER_COLUMN_PARENTCALENDARBOOKINGID_PARENTCALENDARBOOKINGID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentCalendarBookingId);

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

	private static final String _FINDER_COLUMN_PARENTCALENDARBOOKINGID_PARENTCALENDARBOOKINGID_2 =
		"calendarBooking.parentCalendarBookingId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_C_P = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByC_P",
			new String[] { Long.class.getName(), Long.class.getName() },
			CalendarBookingModelImpl.CALENDARID_COLUMN_BITMASK |
			CalendarBookingModelImpl.PARENTCALENDARBOOKINGID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_P = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_P",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the calendar booking where calendarId = &#63; and parentCalendarBookingId = &#63; or throws a {@link NoSuchBookingException} if it could not be found.
	 *
	 * @param calendarId the calendar ID
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @return the matching calendar booking
	 * @throws NoSuchBookingException if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking findByC_P(long calendarId,
		long parentCalendarBookingId) throws NoSuchBookingException {
		CalendarBooking calendarBooking = fetchByC_P(calendarId,
				parentCalendarBookingId);

		if (calendarBooking == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("calendarId=");
			msg.append(calendarId);

			msg.append(", parentCalendarBookingId=");
			msg.append(parentCalendarBookingId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchBookingException(msg.toString());
		}

		return calendarBooking;
	}

	/**
	 * Returns the calendar booking where calendarId = &#63; and parentCalendarBookingId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param calendarId the calendar ID
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @return the matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking fetchByC_P(long calendarId,
		long parentCalendarBookingId) {
		return fetchByC_P(calendarId, parentCalendarBookingId, true);
	}

	/**
	 * Returns the calendar booking where calendarId = &#63; and parentCalendarBookingId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param calendarId the calendar ID
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking fetchByC_P(long calendarId,
		long parentCalendarBookingId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { calendarId, parentCalendarBookingId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_P,
					finderArgs, this);
		}

		if (result instanceof CalendarBooking) {
			CalendarBooking calendarBooking = (CalendarBooking)result;

			if ((calendarId != calendarBooking.getCalendarId()) ||
					(parentCalendarBookingId != calendarBooking.getParentCalendarBookingId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CALENDARBOOKING_WHERE);

			query.append(_FINDER_COLUMN_C_P_CALENDARID_2);

			query.append(_FINDER_COLUMN_C_P_PARENTCALENDARBOOKINGID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(calendarId);

				qPos.add(parentCalendarBookingId);

				List<CalendarBooking> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_P,
						finderArgs, list);
				}
				else {
					CalendarBooking calendarBooking = list.get(0);

					result = calendarBooking;

					cacheResult(calendarBooking);

					if ((calendarBooking.getCalendarId() != calendarId) ||
							(calendarBooking.getParentCalendarBookingId() != parentCalendarBookingId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_P,
							finderArgs, calendarBooking);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_P,
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
			return (CalendarBooking)result;
		}
	}

	/**
	 * Removes the calendar booking where calendarId = &#63; and parentCalendarBookingId = &#63; from the database.
	 *
	 * @param calendarId the calendar ID
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @return the calendar booking that was removed
	 */
	@Override
	public CalendarBooking removeByC_P(long calendarId,
		long parentCalendarBookingId) throws NoSuchBookingException {
		CalendarBooking calendarBooking = findByC_P(calendarId,
				parentCalendarBookingId);

		return remove(calendarBooking);
	}

	/**
	 * Returns the number of calendar bookings where calendarId = &#63; and parentCalendarBookingId = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @return the number of matching calendar bookings
	 */
	@Override
	public int countByC_P(long calendarId, long parentCalendarBookingId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_P;

		Object[] finderArgs = new Object[] { calendarId, parentCalendarBookingId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CALENDARBOOKING_WHERE);

			query.append(_FINDER_COLUMN_C_P_CALENDARID_2);

			query.append(_FINDER_COLUMN_C_P_PARENTCALENDARBOOKINGID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(calendarId);

				qPos.add(parentCalendarBookingId);

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

	private static final String _FINDER_COLUMN_C_P_CALENDARID_2 = "calendarBooking.calendarId = ? AND ";
	private static final String _FINDER_COLUMN_C_P_PARENTCALENDARBOOKINGID_2 = "calendarBooking.parentCalendarBookingId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_C_V = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByC_V",
			new String[] { Long.class.getName(), String.class.getName() },
			CalendarBookingModelImpl.CALENDARID_COLUMN_BITMASK |
			CalendarBookingModelImpl.VEVENTUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_V = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_V",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the calendar booking where calendarId = &#63; and vEventUid = &#63; or throws a {@link NoSuchBookingException} if it could not be found.
	 *
	 * @param calendarId the calendar ID
	 * @param vEventUid the v event uid
	 * @return the matching calendar booking
	 * @throws NoSuchBookingException if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking findByC_V(long calendarId, String vEventUid)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = fetchByC_V(calendarId, vEventUid);

		if (calendarBooking == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("calendarId=");
			msg.append(calendarId);

			msg.append(", vEventUid=");
			msg.append(vEventUid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchBookingException(msg.toString());
		}

		return calendarBooking;
	}

	/**
	 * Returns the calendar booking where calendarId = &#63; and vEventUid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param calendarId the calendar ID
	 * @param vEventUid the v event uid
	 * @return the matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking fetchByC_V(long calendarId, String vEventUid) {
		return fetchByC_V(calendarId, vEventUid, true);
	}

	/**
	 * Returns the calendar booking where calendarId = &#63; and vEventUid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param calendarId the calendar ID
	 * @param vEventUid the v event uid
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking fetchByC_V(long calendarId, String vEventUid,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { calendarId, vEventUid };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_V,
					finderArgs, this);
		}

		if (result instanceof CalendarBooking) {
			CalendarBooking calendarBooking = (CalendarBooking)result;

			if ((calendarId != calendarBooking.getCalendarId()) ||
					!Validator.equals(vEventUid, calendarBooking.getVEventUid())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CALENDARBOOKING_WHERE);

			query.append(_FINDER_COLUMN_C_V_CALENDARID_2);

			boolean bindVEventUid = false;

			if (vEventUid == null) {
				query.append(_FINDER_COLUMN_C_V_VEVENTUID_1);
			}
			else if (vEventUid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_V_VEVENTUID_3);
			}
			else {
				bindVEventUid = true;

				query.append(_FINDER_COLUMN_C_V_VEVENTUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(calendarId);

				if (bindVEventUid) {
					qPos.add(vEventUid);
				}

				List<CalendarBooking> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_V,
						finderArgs, list);
				}
				else {
					CalendarBooking calendarBooking = list.get(0);

					result = calendarBooking;

					cacheResult(calendarBooking);

					if ((calendarBooking.getCalendarId() != calendarId) ||
							(calendarBooking.getVEventUid() == null) ||
							!calendarBooking.getVEventUid().equals(vEventUid)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_V,
							finderArgs, calendarBooking);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_V,
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
			return (CalendarBooking)result;
		}
	}

	/**
	 * Removes the calendar booking where calendarId = &#63; and vEventUid = &#63; from the database.
	 *
	 * @param calendarId the calendar ID
	 * @param vEventUid the v event uid
	 * @return the calendar booking that was removed
	 */
	@Override
	public CalendarBooking removeByC_V(long calendarId, String vEventUid)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = findByC_V(calendarId, vEventUid);

		return remove(calendarBooking);
	}

	/**
	 * Returns the number of calendar bookings where calendarId = &#63; and vEventUid = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @param vEventUid the v event uid
	 * @return the number of matching calendar bookings
	 */
	@Override
	public int countByC_V(long calendarId, String vEventUid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_V;

		Object[] finderArgs = new Object[] { calendarId, vEventUid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CALENDARBOOKING_WHERE);

			query.append(_FINDER_COLUMN_C_V_CALENDARID_2);

			boolean bindVEventUid = false;

			if (vEventUid == null) {
				query.append(_FINDER_COLUMN_C_V_VEVENTUID_1);
			}
			else if (vEventUid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_V_VEVENTUID_3);
			}
			else {
				bindVEventUid = true;

				query.append(_FINDER_COLUMN_C_V_VEVENTUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(calendarId);

				if (bindVEventUid) {
					qPos.add(vEventUid);
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

	private static final String _FINDER_COLUMN_C_V_CALENDARID_2 = "calendarBooking.calendarId = ? AND ";
	private static final String _FINDER_COLUMN_C_V_VEVENTUID_1 = "calendarBooking.vEventUid IS NULL";
	private static final String _FINDER_COLUMN_C_V_VEVENTUID_2 = "calendarBooking.vEventUid = ?";
	private static final String _FINDER_COLUMN_C_V_VEVENTUID_3 = "(calendarBooking.vEventUid IS NULL OR calendarBooking.vEventUid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_S = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			CalendarBookingModelImpl.CALENDARID_COLUMN_BITMASK |
			CalendarBookingModelImpl.STATUS_COLUMN_BITMASK |
			CalendarBookingModelImpl.STARTTIME_COLUMN_BITMASK |
			CalendarBookingModelImpl.TITLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_S = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_S = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByC_S",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the calendar bookings where calendarId = &#63; and status = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @param status the status
	 * @return the matching calendar bookings
	 */
	@Override
	public List<CalendarBooking> findByC_S(long calendarId, int status) {
		return findByC_S(calendarId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar bookings where calendarId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param calendarId the calendar ID
	 * @param status the status
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @return the range of matching calendar bookings
	 */
	@Override
	public List<CalendarBooking> findByC_S(long calendarId, int status,
		int start, int end) {
		return findByC_S(calendarId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar bookings where calendarId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param calendarId the calendar ID
	 * @param status the status
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar bookings
	 */
	@Override
	public List<CalendarBooking> findByC_S(long calendarId, int status,
		int start, int end, OrderByComparator<CalendarBooking> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S;
			finderArgs = new Object[] { calendarId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_S;
			finderArgs = new Object[] {
					calendarId, status,
					
					start, end, orderByComparator
				};
		}

		List<CalendarBooking> list = (List<CalendarBooking>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CalendarBooking calendarBooking : list) {
				if ((calendarId != calendarBooking.getCalendarId()) ||
						(status != calendarBooking.getStatus())) {
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

			query.append(_SQL_SELECT_CALENDARBOOKING_WHERE);

			query.append(_FINDER_COLUMN_C_S_CALENDARID_2);

			query.append(_FINDER_COLUMN_C_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CalendarBookingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(calendarId);

				qPos.add(status);

				if (!pagination) {
					list = (List<CalendarBooking>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CalendarBooking>)QueryUtil.list(q,
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
	 * Returns the first calendar booking in the ordered set where calendarId = &#63; and status = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar booking
	 * @throws NoSuchBookingException if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking findByC_S_First(long calendarId, int status,
		OrderByComparator<CalendarBooking> orderByComparator)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = fetchByC_S_First(calendarId, status,
				orderByComparator);

		if (calendarBooking != null) {
			return calendarBooking;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("calendarId=");
		msg.append(calendarId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBookingException(msg.toString());
	}

	/**
	 * Returns the first calendar booking in the ordered set where calendarId = &#63; and status = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking fetchByC_S_First(long calendarId, int status,
		OrderByComparator<CalendarBooking> orderByComparator) {
		List<CalendarBooking> list = findByC_S(calendarId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last calendar booking in the ordered set where calendarId = &#63; and status = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar booking
	 * @throws NoSuchBookingException if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking findByC_S_Last(long calendarId, int status,
		OrderByComparator<CalendarBooking> orderByComparator)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = fetchByC_S_Last(calendarId, status,
				orderByComparator);

		if (calendarBooking != null) {
			return calendarBooking;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("calendarId=");
		msg.append(calendarId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBookingException(msg.toString());
	}

	/**
	 * Returns the last calendar booking in the ordered set where calendarId = &#63; and status = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking fetchByC_S_Last(long calendarId, int status,
		OrderByComparator<CalendarBooking> orderByComparator) {
		int count = countByC_S(calendarId, status);

		if (count == 0) {
			return null;
		}

		List<CalendarBooking> list = findByC_S(calendarId, status, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the calendar bookings before and after the current calendar booking in the ordered set where calendarId = &#63; and status = &#63;.
	 *
	 * @param calendarBookingId the primary key of the current calendar booking
	 * @param calendarId the calendar ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar booking
	 * @throws NoSuchBookingException if a calendar booking with the primary key could not be found
	 */
	@Override
	public CalendarBooking[] findByC_S_PrevAndNext(long calendarBookingId,
		long calendarId, int status,
		OrderByComparator<CalendarBooking> orderByComparator)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = findByPrimaryKey(calendarBookingId);

		Session session = null;

		try {
			session = openSession();

			CalendarBooking[] array = new CalendarBookingImpl[3];

			array[0] = getByC_S_PrevAndNext(session, calendarBooking,
					calendarId, status, orderByComparator, true);

			array[1] = calendarBooking;

			array[2] = getByC_S_PrevAndNext(session, calendarBooking,
					calendarId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CalendarBooking getByC_S_PrevAndNext(Session session,
		CalendarBooking calendarBooking, long calendarId, int status,
		OrderByComparator<CalendarBooking> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDARBOOKING_WHERE);

		query.append(_FINDER_COLUMN_C_S_CALENDARID_2);

		query.append(_FINDER_COLUMN_C_S_STATUS_2);

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
			query.append(CalendarBookingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(calendarId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(calendarBooking);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CalendarBooking> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the calendar bookings where calendarId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param calendarId the calendar ID
	 * @param statuses the statuses
	 * @return the matching calendar bookings
	 */
	@Override
	public List<CalendarBooking> findByC_S(long calendarId, int[] statuses) {
		return findByC_S(calendarId, statuses, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar bookings where calendarId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param calendarId the calendar ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @return the range of matching calendar bookings
	 */
	@Override
	public List<CalendarBooking> findByC_S(long calendarId, int[] statuses,
		int start, int end) {
		return findByC_S(calendarId, statuses, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar bookings where calendarId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param calendarId the calendar ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar bookings
	 */
	@Override
	public List<CalendarBooking> findByC_S(long calendarId, int[] statuses,
		int start, int end, OrderByComparator<CalendarBooking> orderByComparator) {
		if (statuses == null) {
			statuses = new int[0];
		}
		else {
			statuses = ArrayUtil.unique(statuses);
		}

		if (statuses.length == 1) {
			return findByC_S(calendarId, statuses[0], start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { calendarId, StringUtil.merge(statuses) };
		}
		else {
			finderArgs = new Object[] {
					calendarId, StringUtil.merge(statuses),
					
					start, end, orderByComparator
				};
		}

		List<CalendarBooking> list = (List<CalendarBooking>)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_C_S,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CalendarBooking calendarBooking : list) {
				if ((calendarId != calendarBooking.getCalendarId()) ||
						!ArrayUtil.contains(statuses,
							calendarBooking.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_CALENDARBOOKING_WHERE);

			query.append(_FINDER_COLUMN_C_S_CALENDARID_2);

			if (statuses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_C_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CalendarBookingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(calendarId);

				if (!pagination) {
					list = (List<CalendarBooking>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CalendarBooking>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_C_S,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_C_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the calendar bookings where calendarId = &#63; and status = &#63; from the database.
	 *
	 * @param calendarId the calendar ID
	 * @param status the status
	 */
	@Override
	public void removeByC_S(long calendarId, int status) {
		for (CalendarBooking calendarBooking : findByC_S(calendarId, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(calendarBooking);
		}
	}

	/**
	 * Returns the number of calendar bookings where calendarId = &#63; and status = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @param status the status
	 * @return the number of matching calendar bookings
	 */
	@Override
	public int countByC_S(long calendarId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_S;

		Object[] finderArgs = new Object[] { calendarId, status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CALENDARBOOKING_WHERE);

			query.append(_FINDER_COLUMN_C_S_CALENDARID_2);

			query.append(_FINDER_COLUMN_C_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(calendarId);

				qPos.add(status);

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

	/**
	 * Returns the number of calendar bookings where calendarId = &#63; and status = any &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @param statuses the statuses
	 * @return the number of matching calendar bookings
	 */
	@Override
	public int countByC_S(long calendarId, int[] statuses) {
		if (statuses == null) {
			statuses = new int[0];
		}
		else {
			statuses = ArrayUtil.unique(statuses);
		}

		Object[] finderArgs = new Object[] {
				calendarId, StringUtil.merge(statuses)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_CALENDARBOOKING_WHERE);

			query.append(_FINDER_COLUMN_C_S_CALENDARID_2);

			if (statuses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_C_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(calendarId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_S,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_S_CALENDARID_2 = "calendarBooking.calendarId = ? AND ";
	private static final String _FINDER_COLUMN_C_S_STATUS_2 = "calendarBooking.status = ?";
	private static final String _FINDER_COLUMN_C_S_STATUS_7 = "calendarBooking.status IN (";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_P_S = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByP_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			CalendarBookingModelImpl.PARENTCALENDARBOOKINGID_COLUMN_BITMASK |
			CalendarBookingModelImpl.STATUS_COLUMN_BITMASK |
			CalendarBookingModelImpl.STARTTIME_COLUMN_BITMASK |
			CalendarBookingModelImpl.TITLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_P_S = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_S",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the calendar bookings where parentCalendarBookingId = &#63; and status = &#63;.
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param status the status
	 * @return the matching calendar bookings
	 */
	@Override
	public List<CalendarBooking> findByP_S(long parentCalendarBookingId,
		int status) {
		return findByP_S(parentCalendarBookingId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar bookings where parentCalendarBookingId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param status the status
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @return the range of matching calendar bookings
	 */
	@Override
	public List<CalendarBooking> findByP_S(long parentCalendarBookingId,
		int status, int start, int end) {
		return findByP_S(parentCalendarBookingId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar bookings where parentCalendarBookingId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param status the status
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar bookings
	 */
	@Override
	public List<CalendarBooking> findByP_S(long parentCalendarBookingId,
		int status, int start, int end,
		OrderByComparator<CalendarBooking> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S;
			finderArgs = new Object[] { parentCalendarBookingId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_P_S;
			finderArgs = new Object[] {
					parentCalendarBookingId, status,
					
					start, end, orderByComparator
				};
		}

		List<CalendarBooking> list = (List<CalendarBooking>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CalendarBooking calendarBooking : list) {
				if ((parentCalendarBookingId != calendarBooking.getParentCalendarBookingId()) ||
						(status != calendarBooking.getStatus())) {
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

			query.append(_SQL_SELECT_CALENDARBOOKING_WHERE);

			query.append(_FINDER_COLUMN_P_S_PARENTCALENDARBOOKINGID_2);

			query.append(_FINDER_COLUMN_P_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CalendarBookingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentCalendarBookingId);

				qPos.add(status);

				if (!pagination) {
					list = (List<CalendarBooking>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CalendarBooking>)QueryUtil.list(q,
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
	 * Returns the first calendar booking in the ordered set where parentCalendarBookingId = &#63; and status = &#63;.
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar booking
	 * @throws NoSuchBookingException if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking findByP_S_First(long parentCalendarBookingId,
		int status, OrderByComparator<CalendarBooking> orderByComparator)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = fetchByP_S_First(parentCalendarBookingId,
				status, orderByComparator);

		if (calendarBooking != null) {
			return calendarBooking;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentCalendarBookingId=");
		msg.append(parentCalendarBookingId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBookingException(msg.toString());
	}

	/**
	 * Returns the first calendar booking in the ordered set where parentCalendarBookingId = &#63; and status = &#63;.
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking fetchByP_S_First(long parentCalendarBookingId,
		int status, OrderByComparator<CalendarBooking> orderByComparator) {
		List<CalendarBooking> list = findByP_S(parentCalendarBookingId, status,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last calendar booking in the ordered set where parentCalendarBookingId = &#63; and status = &#63;.
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar booking
	 * @throws NoSuchBookingException if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking findByP_S_Last(long parentCalendarBookingId,
		int status, OrderByComparator<CalendarBooking> orderByComparator)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = fetchByP_S_Last(parentCalendarBookingId,
				status, orderByComparator);

		if (calendarBooking != null) {
			return calendarBooking;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentCalendarBookingId=");
		msg.append(parentCalendarBookingId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBookingException(msg.toString());
	}

	/**
	 * Returns the last calendar booking in the ordered set where parentCalendarBookingId = &#63; and status = &#63;.
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 */
	@Override
	public CalendarBooking fetchByP_S_Last(long parentCalendarBookingId,
		int status, OrderByComparator<CalendarBooking> orderByComparator) {
		int count = countByP_S(parentCalendarBookingId, status);

		if (count == 0) {
			return null;
		}

		List<CalendarBooking> list = findByP_S(parentCalendarBookingId, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the calendar bookings before and after the current calendar booking in the ordered set where parentCalendarBookingId = &#63; and status = &#63;.
	 *
	 * @param calendarBookingId the primary key of the current calendar booking
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar booking
	 * @throws NoSuchBookingException if a calendar booking with the primary key could not be found
	 */
	@Override
	public CalendarBooking[] findByP_S_PrevAndNext(long calendarBookingId,
		long parentCalendarBookingId, int status,
		OrderByComparator<CalendarBooking> orderByComparator)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = findByPrimaryKey(calendarBookingId);

		Session session = null;

		try {
			session = openSession();

			CalendarBooking[] array = new CalendarBookingImpl[3];

			array[0] = getByP_S_PrevAndNext(session, calendarBooking,
					parentCalendarBookingId, status, orderByComparator, true);

			array[1] = calendarBooking;

			array[2] = getByP_S_PrevAndNext(session, calendarBooking,
					parentCalendarBookingId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CalendarBooking getByP_S_PrevAndNext(Session session,
		CalendarBooking calendarBooking, long parentCalendarBookingId,
		int status, OrderByComparator<CalendarBooking> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDARBOOKING_WHERE);

		query.append(_FINDER_COLUMN_P_S_PARENTCALENDARBOOKINGID_2);

		query.append(_FINDER_COLUMN_P_S_STATUS_2);

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
			query.append(CalendarBookingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentCalendarBookingId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(calendarBooking);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CalendarBooking> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the calendar bookings where parentCalendarBookingId = &#63; and status = &#63; from the database.
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param status the status
	 */
	@Override
	public void removeByP_S(long parentCalendarBookingId, int status) {
		for (CalendarBooking calendarBooking : findByP_S(
				parentCalendarBookingId, status, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(calendarBooking);
		}
	}

	/**
	 * Returns the number of calendar bookings where parentCalendarBookingId = &#63; and status = &#63;.
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param status the status
	 * @return the number of matching calendar bookings
	 */
	@Override
	public int countByP_S(long parentCalendarBookingId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_P_S;

		Object[] finderArgs = new Object[] { parentCalendarBookingId, status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CALENDARBOOKING_WHERE);

			query.append(_FINDER_COLUMN_P_S_PARENTCALENDARBOOKINGID_2);

			query.append(_FINDER_COLUMN_P_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentCalendarBookingId);

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_P_S_PARENTCALENDARBOOKINGID_2 = "calendarBooking.parentCalendarBookingId = ? AND ";
	private static final String _FINDER_COLUMN_P_S_STATUS_2 = "calendarBooking.status = ?";

	public CalendarBookingPersistenceImpl() {
		setModelClass(CalendarBooking.class);
	}

	/**
	 * Caches the calendar booking in the entity cache if it is enabled.
	 *
	 * @param calendarBooking the calendar booking
	 */
	@Override
	public void cacheResult(CalendarBooking calendarBooking) {
		EntityCacheUtil.putResult(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingImpl.class, calendarBooking.getPrimaryKey(),
			calendarBooking);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { calendarBooking.getUuid(), calendarBooking.getGroupId() },
			calendarBooking);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_P,
			new Object[] {
				calendarBooking.getCalendarId(),
				calendarBooking.getParentCalendarBookingId()
			}, calendarBooking);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_V,
			new Object[] {
				calendarBooking.getCalendarId(), calendarBooking.getVEventUid()
			}, calendarBooking);

		calendarBooking.resetOriginalValues();
	}

	/**
	 * Caches the calendar bookings in the entity cache if it is enabled.
	 *
	 * @param calendarBookings the calendar bookings
	 */
	@Override
	public void cacheResult(List<CalendarBooking> calendarBookings) {
		for (CalendarBooking calendarBooking : calendarBookings) {
			if (EntityCacheUtil.getResult(
						CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
						CalendarBookingImpl.class,
						calendarBooking.getPrimaryKey()) == null) {
				cacheResult(calendarBooking);
			}
			else {
				calendarBooking.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all calendar bookings.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(CalendarBookingImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the calendar booking.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CalendarBooking calendarBooking) {
		EntityCacheUtil.removeResult(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingImpl.class, calendarBooking.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(calendarBooking);
	}

	@Override
	public void clearCache(List<CalendarBooking> calendarBookings) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CalendarBooking calendarBooking : calendarBookings) {
			EntityCacheUtil.removeResult(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
				CalendarBookingImpl.class, calendarBooking.getPrimaryKey());

			clearUniqueFindersCache(calendarBooking);
		}
	}

	protected void cacheUniqueFindersCache(CalendarBooking calendarBooking) {
		if (calendarBooking.isNew()) {
			Object[] args = new Object[] {
					calendarBooking.getUuid(), calendarBooking.getGroupId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				calendarBooking);

			args = new Object[] {
					calendarBooking.getCalendarId(),
					calendarBooking.getParentCalendarBookingId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_P, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_P, args,
				calendarBooking);

			args = new Object[] {
					calendarBooking.getCalendarId(),
					calendarBooking.getVEventUid()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_V, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_V, args,
				calendarBooking);
		}
		else {
			CalendarBookingModelImpl calendarBookingModelImpl = (CalendarBookingModelImpl)calendarBooking;

			if ((calendarBookingModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						calendarBooking.getUuid(), calendarBooking.getGroupId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					calendarBooking);
			}

			if ((calendarBookingModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_C_P.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						calendarBooking.getCalendarId(),
						calendarBooking.getParentCalendarBookingId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_P, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_P, args,
					calendarBooking);
			}

			if ((calendarBookingModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_C_V.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						calendarBooking.getCalendarId(),
						calendarBooking.getVEventUid()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_V, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_V, args,
					calendarBooking);
			}
		}
	}

	protected void clearUniqueFindersCache(CalendarBooking calendarBooking) {
		CalendarBookingModelImpl calendarBookingModelImpl = (CalendarBookingModelImpl)calendarBooking;

		Object[] args = new Object[] {
				calendarBooking.getUuid(), calendarBooking.getGroupId()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((calendarBookingModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					calendarBookingModelImpl.getOriginalUuid(),
					calendarBookingModelImpl.getOriginalGroupId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		args = new Object[] {
				calendarBooking.getCalendarId(),
				calendarBooking.getParentCalendarBookingId()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_P, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_P, args);

		if ((calendarBookingModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_P.getColumnBitmask()) != 0) {
			args = new Object[] {
					calendarBookingModelImpl.getOriginalCalendarId(),
					calendarBookingModelImpl.getOriginalParentCalendarBookingId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_P, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_P, args);
		}

		args = new Object[] {
				calendarBooking.getCalendarId(), calendarBooking.getVEventUid()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_V, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_V, args);

		if ((calendarBookingModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_V.getColumnBitmask()) != 0) {
			args = new Object[] {
					calendarBookingModelImpl.getOriginalCalendarId(),
					calendarBookingModelImpl.getOriginalVEventUid()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_V, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_V, args);
		}
	}

	/**
	 * Creates a new calendar booking with the primary key. Does not add the calendar booking to the database.
	 *
	 * @param calendarBookingId the primary key for the new calendar booking
	 * @return the new calendar booking
	 */
	@Override
	public CalendarBooking create(long calendarBookingId) {
		CalendarBooking calendarBooking = new CalendarBookingImpl();

		calendarBooking.setNew(true);
		calendarBooking.setPrimaryKey(calendarBookingId);

		String uuid = PortalUUIDUtil.generate();

		calendarBooking.setUuid(uuid);

		return calendarBooking;
	}

	/**
	 * Removes the calendar booking with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param calendarBookingId the primary key of the calendar booking
	 * @return the calendar booking that was removed
	 * @throws NoSuchBookingException if a calendar booking with the primary key could not be found
	 */
	@Override
	public CalendarBooking remove(long calendarBookingId)
		throws NoSuchBookingException {
		return remove((Serializable)calendarBookingId);
	}

	/**
	 * Removes the calendar booking with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the calendar booking
	 * @return the calendar booking that was removed
	 * @throws NoSuchBookingException if a calendar booking with the primary key could not be found
	 */
	@Override
	public CalendarBooking remove(Serializable primaryKey)
		throws NoSuchBookingException {
		Session session = null;

		try {
			session = openSession();

			CalendarBooking calendarBooking = (CalendarBooking)session.get(CalendarBookingImpl.class,
					primaryKey);

			if (calendarBooking == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBookingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(calendarBooking);
		}
		catch (NoSuchBookingException nsee) {
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
	protected CalendarBooking removeImpl(CalendarBooking calendarBooking) {
		calendarBooking = toUnwrappedModel(calendarBooking);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(calendarBooking)) {
				calendarBooking = (CalendarBooking)session.get(CalendarBookingImpl.class,
						calendarBooking.getPrimaryKeyObj());
			}

			if (calendarBooking != null) {
				session.delete(calendarBooking);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (calendarBooking != null) {
			clearCache(calendarBooking);
		}

		return calendarBooking;
	}

	@Override
	public CalendarBooking updateImpl(CalendarBooking calendarBooking) {
		calendarBooking = toUnwrappedModel(calendarBooking);

		boolean isNew = calendarBooking.isNew();

		CalendarBookingModelImpl calendarBookingModelImpl = (CalendarBookingModelImpl)calendarBooking;

		if (Validator.isNull(calendarBooking.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			calendarBooking.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (calendarBooking.isNew()) {
				session.save(calendarBooking);

				calendarBooking.setNew(false);
			}
			else {
				session.merge(calendarBooking);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !CalendarBookingModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((calendarBookingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEBLOCKID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						calendarBookingModelImpl.getOriginalResourceBlockId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RESOURCEBLOCKID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEBLOCKID,
					args);

				args = new Object[] {
						calendarBookingModelImpl.getResourceBlockId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RESOURCEBLOCKID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEBLOCKID,
					args);
			}

			if ((calendarBookingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						calendarBookingModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { calendarBookingModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((calendarBookingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						calendarBookingModelImpl.getOriginalUuid(),
						calendarBookingModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						calendarBookingModelImpl.getUuid(),
						calendarBookingModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((calendarBookingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALENDARID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						calendarBookingModelImpl.getOriginalCalendarId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CALENDARID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALENDARID,
					args);

				args = new Object[] { calendarBookingModelImpl.getCalendarId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CALENDARID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALENDARID,
					args);
			}

			if ((calendarBookingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALENDARRESOURCEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						calendarBookingModelImpl.getOriginalCalendarResourceId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CALENDARRESOURCEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALENDARRESOURCEID,
					args);

				args = new Object[] {
						calendarBookingModelImpl.getCalendarResourceId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CALENDARRESOURCEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALENDARRESOURCEID,
					args);
			}

			if ((calendarBookingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTCALENDARBOOKINGID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						calendarBookingModelImpl.getOriginalParentCalendarBookingId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARENTCALENDARBOOKINGID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTCALENDARBOOKINGID,
					args);

				args = new Object[] {
						calendarBookingModelImpl.getParentCalendarBookingId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARENTCALENDARBOOKINGID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTCALENDARBOOKINGID,
					args);
			}

			if ((calendarBookingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						calendarBookingModelImpl.getOriginalCalendarId(),
						calendarBookingModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S,
					args);

				args = new Object[] {
						calendarBookingModelImpl.getCalendarId(),
						calendarBookingModelImpl.getStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S,
					args);
			}

			if ((calendarBookingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						calendarBookingModelImpl.getOriginalParentCalendarBookingId(),
						calendarBookingModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_P_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S,
					args);

				args = new Object[] {
						calendarBookingModelImpl.getParentCalendarBookingId(),
						calendarBookingModelImpl.getStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_P_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S,
					args);
			}
		}

		EntityCacheUtil.putResult(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingImpl.class, calendarBooking.getPrimaryKey(),
			calendarBooking, false);

		clearUniqueFindersCache(calendarBooking);
		cacheUniqueFindersCache(calendarBooking);

		calendarBooking.resetOriginalValues();

		return calendarBooking;
	}

	protected CalendarBooking toUnwrappedModel(CalendarBooking calendarBooking) {
		if (calendarBooking instanceof CalendarBookingImpl) {
			return calendarBooking;
		}

		CalendarBookingImpl calendarBookingImpl = new CalendarBookingImpl();

		calendarBookingImpl.setNew(calendarBooking.isNew());
		calendarBookingImpl.setPrimaryKey(calendarBooking.getPrimaryKey());

		calendarBookingImpl.setUuid(calendarBooking.getUuid());
		calendarBookingImpl.setCalendarBookingId(calendarBooking.getCalendarBookingId());
		calendarBookingImpl.setGroupId(calendarBooking.getGroupId());
		calendarBookingImpl.setCompanyId(calendarBooking.getCompanyId());
		calendarBookingImpl.setUserId(calendarBooking.getUserId());
		calendarBookingImpl.setUserName(calendarBooking.getUserName());
		calendarBookingImpl.setCreateDate(calendarBooking.getCreateDate());
		calendarBookingImpl.setModifiedDate(calendarBooking.getModifiedDate());
		calendarBookingImpl.setResourceBlockId(calendarBooking.getResourceBlockId());
		calendarBookingImpl.setCalendarId(calendarBooking.getCalendarId());
		calendarBookingImpl.setCalendarResourceId(calendarBooking.getCalendarResourceId());
		calendarBookingImpl.setParentCalendarBookingId(calendarBooking.getParentCalendarBookingId());
		calendarBookingImpl.setVEventUid(calendarBooking.getVEventUid());
		calendarBookingImpl.setTitle(calendarBooking.getTitle());
		calendarBookingImpl.setDescription(calendarBooking.getDescription());
		calendarBookingImpl.setLocation(calendarBooking.getLocation());
		calendarBookingImpl.setStartTime(calendarBooking.getStartTime());
		calendarBookingImpl.setEndTime(calendarBooking.getEndTime());
		calendarBookingImpl.setAllDay(calendarBooking.isAllDay());
		calendarBookingImpl.setRecurrence(calendarBooking.getRecurrence());
		calendarBookingImpl.setFirstReminder(calendarBooking.getFirstReminder());
		calendarBookingImpl.setFirstReminderType(calendarBooking.getFirstReminderType());
		calendarBookingImpl.setSecondReminder(calendarBooking.getSecondReminder());
		calendarBookingImpl.setSecondReminderType(calendarBooking.getSecondReminderType());
		calendarBookingImpl.setStatus(calendarBooking.getStatus());
		calendarBookingImpl.setStatusByUserId(calendarBooking.getStatusByUserId());
		calendarBookingImpl.setStatusByUserName(calendarBooking.getStatusByUserName());
		calendarBookingImpl.setStatusDate(calendarBooking.getStatusDate());

		return calendarBookingImpl;
	}

	/**
	 * Returns the calendar booking with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the calendar booking
	 * @return the calendar booking
	 * @throws NoSuchBookingException if a calendar booking with the primary key could not be found
	 */
	@Override
	public CalendarBooking findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBookingException {
		CalendarBooking calendarBooking = fetchByPrimaryKey(primaryKey);

		if (calendarBooking == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBookingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return calendarBooking;
	}

	/**
	 * Returns the calendar booking with the primary key or throws a {@link NoSuchBookingException} if it could not be found.
	 *
	 * @param calendarBookingId the primary key of the calendar booking
	 * @return the calendar booking
	 * @throws NoSuchBookingException if a calendar booking with the primary key could not be found
	 */
	@Override
	public CalendarBooking findByPrimaryKey(long calendarBookingId)
		throws NoSuchBookingException {
		return findByPrimaryKey((Serializable)calendarBookingId);
	}

	/**
	 * Returns the calendar booking with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the calendar booking
	 * @return the calendar booking, or <code>null</code> if a calendar booking with the primary key could not be found
	 */
	@Override
	public CalendarBooking fetchByPrimaryKey(Serializable primaryKey) {
		CalendarBooking calendarBooking = (CalendarBooking)EntityCacheUtil.getResult(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
				CalendarBookingImpl.class, primaryKey);

		if (calendarBooking == _nullCalendarBooking) {
			return null;
		}

		if (calendarBooking == null) {
			Session session = null;

			try {
				session = openSession();

				calendarBooking = (CalendarBooking)session.get(CalendarBookingImpl.class,
						primaryKey);

				if (calendarBooking != null) {
					cacheResult(calendarBooking);
				}
				else {
					EntityCacheUtil.putResult(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
						CalendarBookingImpl.class, primaryKey,
						_nullCalendarBooking);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
					CalendarBookingImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return calendarBooking;
	}

	/**
	 * Returns the calendar booking with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param calendarBookingId the primary key of the calendar booking
	 * @return the calendar booking, or <code>null</code> if a calendar booking with the primary key could not be found
	 */
	@Override
	public CalendarBooking fetchByPrimaryKey(long calendarBookingId) {
		return fetchByPrimaryKey((Serializable)calendarBookingId);
	}

	@Override
	public Map<Serializable, CalendarBooking> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CalendarBooking> map = new HashMap<Serializable, CalendarBooking>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CalendarBooking calendarBooking = fetchByPrimaryKey(primaryKey);

			if (calendarBooking != null) {
				map.put(primaryKey, calendarBooking);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			CalendarBooking calendarBooking = (CalendarBooking)EntityCacheUtil.getResult(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
					CalendarBookingImpl.class, primaryKey);

			if (calendarBooking == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, calendarBooking);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CALENDARBOOKING_WHERE_PKS_IN);

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

			for (CalendarBooking calendarBooking : (List<CalendarBooking>)q.list()) {
				map.put(calendarBooking.getPrimaryKeyObj(), calendarBooking);

				cacheResult(calendarBooking);

				uncachedPrimaryKeys.remove(calendarBooking.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
					CalendarBookingImpl.class, primaryKey, _nullCalendarBooking);
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
	 * Returns all the calendar bookings.
	 *
	 * @return the calendar bookings
	 */
	@Override
	public List<CalendarBooking> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar bookings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @return the range of calendar bookings
	 */
	@Override
	public List<CalendarBooking> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar bookings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of calendar bookings
	 */
	@Override
	public List<CalendarBooking> findAll(int start, int end,
		OrderByComparator<CalendarBooking> orderByComparator) {
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

		List<CalendarBooking> list = (List<CalendarBooking>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CALENDARBOOKING);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CALENDARBOOKING;

				if (pagination) {
					sql = sql.concat(CalendarBookingModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CalendarBooking>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CalendarBooking>)QueryUtil.list(q,
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
	 * Removes all the calendar bookings from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CalendarBooking calendarBooking : findAll()) {
			remove(calendarBooking);
		}
	}

	/**
	 * Returns the number of calendar bookings.
	 *
	 * @return the number of calendar bookings
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CALENDARBOOKING);

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
	 * Initializes the calendar booking persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(CalendarBookingImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_CALENDARBOOKING = "SELECT calendarBooking FROM CalendarBooking calendarBooking";
	private static final String _SQL_SELECT_CALENDARBOOKING_WHERE_PKS_IN = "SELECT calendarBooking FROM CalendarBooking calendarBooking WHERE calendarBookingId IN (";
	private static final String _SQL_SELECT_CALENDARBOOKING_WHERE = "SELECT calendarBooking FROM CalendarBooking calendarBooking WHERE ";
	private static final String _SQL_COUNT_CALENDARBOOKING = "SELECT COUNT(calendarBooking) FROM CalendarBooking calendarBooking";
	private static final String _SQL_COUNT_CALENDARBOOKING_WHERE = "SELECT COUNT(calendarBooking) FROM CalendarBooking calendarBooking WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "calendarBooking.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CalendarBooking exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CalendarBooking exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CalendarBookingPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static final CalendarBooking _nullCalendarBooking = new CalendarBookingImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<CalendarBooking> toCacheModel() {
				return _nullCalendarBookingCacheModel;
			}
		};

	private static final CacheModel<CalendarBooking> _nullCalendarBookingCacheModel =
		new CacheModel<CalendarBooking>() {
			@Override
			public CalendarBooking toEntityModel() {
				return _nullCalendarBooking;
			}
		};
}