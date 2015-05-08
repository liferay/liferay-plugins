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

import com.liferay.calendar.NoSuchCalendarException;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.impl.CalendarImpl;
import com.liferay.calendar.model.impl.CalendarModelImpl;
import com.liferay.calendar.service.persistence.CalendarPersistence;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
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
 * The persistence implementation for the calendar service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarPersistence
 * @see com.liferay.calendar.service.persistence.CalendarUtil
 * @generated
 */
@ProviderType
public class CalendarPersistenceImpl extends BasePersistenceImpl<Calendar>
	implements CalendarPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CalendarUtil} to access the calendar persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CalendarImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RESOURCEBLOCKID =
		new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByResourceBlockId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEBLOCKID =
		new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByResourceBlockId",
			new String[] { Long.class.getName() },
			CalendarModelImpl.RESOURCEBLOCKID_COLUMN_BITMASK |
			CalendarModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RESOURCEBLOCKID = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByResourceBlockId", new String[] { Long.class.getName() });

	/**
	 * Returns all the calendars where resourceBlockId = &#63;.
	 *
	 * @param resourceBlockId the resource block ID
	 * @return the matching calendars
	 */
	@Override
	public List<Calendar> findByResourceBlockId(long resourceBlockId) {
		return findByResourceBlockId(resourceBlockId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendars where resourceBlockId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourceBlockId the resource block ID
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @return the range of matching calendars
	 */
	@Override
	public List<Calendar> findByResourceBlockId(long resourceBlockId,
		int start, int end) {
		return findByResourceBlockId(resourceBlockId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendars where resourceBlockId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourceBlockId the resource block ID
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendars
	 */
	@Override
	public List<Calendar> findByResourceBlockId(long resourceBlockId,
		int start, int end, OrderByComparator<Calendar> orderByComparator) {
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

		List<Calendar> list = (List<Calendar>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Calendar calendar : list) {
				if ((resourceBlockId != calendar.getResourceBlockId())) {
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

			query.append(_SQL_SELECT_CALENDAR_WHERE);

			query.append(_FINDER_COLUMN_RESOURCEBLOCKID_RESOURCEBLOCKID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CalendarModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourceBlockId);

				if (!pagination) {
					list = (List<Calendar>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Calendar>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first calendar in the ordered set where resourceBlockId = &#63;.
	 *
	 * @param resourceBlockId the resource block ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar
	 * @throws NoSuchCalendarException if a matching calendar could not be found
	 */
	@Override
	public Calendar findByResourceBlockId_First(long resourceBlockId,
		OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {
		Calendar calendar = fetchByResourceBlockId_First(resourceBlockId,
				orderByComparator);

		if (calendar != null) {
			return calendar;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourceBlockId=");
		msg.append(resourceBlockId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCalendarException(msg.toString());
	}

	/**
	 * Returns the first calendar in the ordered set where resourceBlockId = &#63;.
	 *
	 * @param resourceBlockId the resource block ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar, or <code>null</code> if a matching calendar could not be found
	 */
	@Override
	public Calendar fetchByResourceBlockId_First(long resourceBlockId,
		OrderByComparator<Calendar> orderByComparator) {
		List<Calendar> list = findByResourceBlockId(resourceBlockId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last calendar in the ordered set where resourceBlockId = &#63;.
	 *
	 * @param resourceBlockId the resource block ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar
	 * @throws NoSuchCalendarException if a matching calendar could not be found
	 */
	@Override
	public Calendar findByResourceBlockId_Last(long resourceBlockId,
		OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {
		Calendar calendar = fetchByResourceBlockId_Last(resourceBlockId,
				orderByComparator);

		if (calendar != null) {
			return calendar;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourceBlockId=");
		msg.append(resourceBlockId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCalendarException(msg.toString());
	}

	/**
	 * Returns the last calendar in the ordered set where resourceBlockId = &#63;.
	 *
	 * @param resourceBlockId the resource block ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar, or <code>null</code> if a matching calendar could not be found
	 */
	@Override
	public Calendar fetchByResourceBlockId_Last(long resourceBlockId,
		OrderByComparator<Calendar> orderByComparator) {
		int count = countByResourceBlockId(resourceBlockId);

		if (count == 0) {
			return null;
		}

		List<Calendar> list = findByResourceBlockId(resourceBlockId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the calendars before and after the current calendar in the ordered set where resourceBlockId = &#63;.
	 *
	 * @param calendarId the primary key of the current calendar
	 * @param resourceBlockId the resource block ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar
	 * @throws NoSuchCalendarException if a calendar with the primary key could not be found
	 */
	@Override
	public Calendar[] findByResourceBlockId_PrevAndNext(long calendarId,
		long resourceBlockId, OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {
		Calendar calendar = findByPrimaryKey(calendarId);

		Session session = null;

		try {
			session = openSession();

			Calendar[] array = new CalendarImpl[3];

			array[0] = getByResourceBlockId_PrevAndNext(session, calendar,
					resourceBlockId, orderByComparator, true);

			array[1] = calendar;

			array[2] = getByResourceBlockId_PrevAndNext(session, calendar,
					resourceBlockId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Calendar getByResourceBlockId_PrevAndNext(Session session,
		Calendar calendar, long resourceBlockId,
		OrderByComparator<Calendar> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDAR_WHERE);

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
			query.append(CalendarModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourceBlockId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(calendar);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Calendar> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the calendars where resourceBlockId = &#63; from the database.
	 *
	 * @param resourceBlockId the resource block ID
	 */
	@Override
	public void removeByResourceBlockId(long resourceBlockId) {
		for (Calendar calendar : findByResourceBlockId(resourceBlockId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(calendar);
		}
	}

	/**
	 * Returns the number of calendars where resourceBlockId = &#63;.
	 *
	 * @param resourceBlockId the resource block ID
	 * @return the number of matching calendars
	 */
	@Override
	public int countByResourceBlockId(long resourceBlockId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_RESOURCEBLOCKID;

		Object[] finderArgs = new Object[] { resourceBlockId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CALENDAR_WHERE);

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
		"calendar.resourceBlockId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			CalendarModelImpl.UUID_COLUMN_BITMASK |
			CalendarModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the calendars where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching calendars
	 */
	@Override
	public List<Calendar> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendars where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @return the range of matching calendars
	 */
	@Override
	public List<Calendar> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendars where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendars
	 */
	@Override
	public List<Calendar> findByUuid(String uuid, int start, int end,
		OrderByComparator<Calendar> orderByComparator) {
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

		List<Calendar> list = (List<Calendar>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Calendar calendar : list) {
				if (!Validator.equals(uuid, calendar.getUuid())) {
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

			query.append(_SQL_SELECT_CALENDAR_WHERE);

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
				query.append(CalendarModelImpl.ORDER_BY_JPQL);
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
					list = (List<Calendar>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Calendar>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first calendar in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar
	 * @throws NoSuchCalendarException if a matching calendar could not be found
	 */
	@Override
	public Calendar findByUuid_First(String uuid,
		OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {
		Calendar calendar = fetchByUuid_First(uuid, orderByComparator);

		if (calendar != null) {
			return calendar;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCalendarException(msg.toString());
	}

	/**
	 * Returns the first calendar in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar, or <code>null</code> if a matching calendar could not be found
	 */
	@Override
	public Calendar fetchByUuid_First(String uuid,
		OrderByComparator<Calendar> orderByComparator) {
		List<Calendar> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last calendar in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar
	 * @throws NoSuchCalendarException if a matching calendar could not be found
	 */
	@Override
	public Calendar findByUuid_Last(String uuid,
		OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {
		Calendar calendar = fetchByUuid_Last(uuid, orderByComparator);

		if (calendar != null) {
			return calendar;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCalendarException(msg.toString());
	}

	/**
	 * Returns the last calendar in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar, or <code>null</code> if a matching calendar could not be found
	 */
	@Override
	public Calendar fetchByUuid_Last(String uuid,
		OrderByComparator<Calendar> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Calendar> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the calendars before and after the current calendar in the ordered set where uuid = &#63;.
	 *
	 * @param calendarId the primary key of the current calendar
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar
	 * @throws NoSuchCalendarException if a calendar with the primary key could not be found
	 */
	@Override
	public Calendar[] findByUuid_PrevAndNext(long calendarId, String uuid,
		OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {
		Calendar calendar = findByPrimaryKey(calendarId);

		Session session = null;

		try {
			session = openSession();

			Calendar[] array = new CalendarImpl[3];

			array[0] = getByUuid_PrevAndNext(session, calendar, uuid,
					orderByComparator, true);

			array[1] = calendar;

			array[2] = getByUuid_PrevAndNext(session, calendar, uuid,
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

	protected Calendar getByUuid_PrevAndNext(Session session,
		Calendar calendar, String uuid,
		OrderByComparator<Calendar> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDAR_WHERE);

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
			query.append(CalendarModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(calendar);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Calendar> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the calendars where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Calendar calendar : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(calendar);
		}
	}

	/**
	 * Returns the number of calendars where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching calendars
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CALENDAR_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "calendar.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "calendar.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(calendar.uuid IS NULL OR calendar.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			CalendarModelImpl.UUID_COLUMN_BITMASK |
			CalendarModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the calendar where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchCalendarException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching calendar
	 * @throws NoSuchCalendarException if a matching calendar could not be found
	 */
	@Override
	public Calendar findByUUID_G(String uuid, long groupId)
		throws NoSuchCalendarException {
		Calendar calendar = fetchByUUID_G(uuid, groupId);

		if (calendar == null) {
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

			throw new NoSuchCalendarException(msg.toString());
		}

		return calendar;
	}

	/**
	 * Returns the calendar where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching calendar, or <code>null</code> if a matching calendar could not be found
	 */
	@Override
	public Calendar fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the calendar where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching calendar, or <code>null</code> if a matching calendar could not be found
	 */
	@Override
	public Calendar fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof Calendar) {
			Calendar calendar = (Calendar)result;

			if (!Validator.equals(uuid, calendar.getUuid()) ||
					(groupId != calendar.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CALENDAR_WHERE);

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

				List<Calendar> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					Calendar calendar = list.get(0);

					result = calendar;

					cacheResult(calendar);

					if ((calendar.getUuid() == null) ||
							!calendar.getUuid().equals(uuid) ||
							(calendar.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, calendar);
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
			return (Calendar)result;
		}
	}

	/**
	 * Removes the calendar where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the calendar that was removed
	 */
	@Override
	public Calendar removeByUUID_G(String uuid, long groupId)
		throws NoSuchCalendarException {
		Calendar calendar = findByUUID_G(uuid, groupId);

		return remove(calendar);
	}

	/**
	 * Returns the number of calendars where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching calendars
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CALENDAR_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "calendar.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "calendar.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(calendar.uuid IS NULL OR calendar.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "calendar.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			CalendarModelImpl.UUID_COLUMN_BITMASK |
			CalendarModelImpl.COMPANYID_COLUMN_BITMASK |
			CalendarModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the calendars where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching calendars
	 */
	@Override
	public List<Calendar> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendars where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @return the range of matching calendars
	 */
	@Override
	public List<Calendar> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendars where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendars
	 */
	@Override
	public List<Calendar> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<Calendar> orderByComparator) {
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

		List<Calendar> list = (List<Calendar>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Calendar calendar : list) {
				if (!Validator.equals(uuid, calendar.getUuid()) ||
						(companyId != calendar.getCompanyId())) {
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

			query.append(_SQL_SELECT_CALENDAR_WHERE);

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
				query.append(CalendarModelImpl.ORDER_BY_JPQL);
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
					list = (List<Calendar>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Calendar>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first calendar in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar
	 * @throws NoSuchCalendarException if a matching calendar could not be found
	 */
	@Override
	public Calendar findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {
		Calendar calendar = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (calendar != null) {
			return calendar;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCalendarException(msg.toString());
	}

	/**
	 * Returns the first calendar in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar, or <code>null</code> if a matching calendar could not be found
	 */
	@Override
	public Calendar fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Calendar> orderByComparator) {
		List<Calendar> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last calendar in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar
	 * @throws NoSuchCalendarException if a matching calendar could not be found
	 */
	@Override
	public Calendar findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {
		Calendar calendar = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (calendar != null) {
			return calendar;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCalendarException(msg.toString());
	}

	/**
	 * Returns the last calendar in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar, or <code>null</code> if a matching calendar could not be found
	 */
	@Override
	public Calendar fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Calendar> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Calendar> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the calendars before and after the current calendar in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param calendarId the primary key of the current calendar
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar
	 * @throws NoSuchCalendarException if a calendar with the primary key could not be found
	 */
	@Override
	public Calendar[] findByUuid_C_PrevAndNext(long calendarId, String uuid,
		long companyId, OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {
		Calendar calendar = findByPrimaryKey(calendarId);

		Session session = null;

		try {
			session = openSession();

			Calendar[] array = new CalendarImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, calendar, uuid,
					companyId, orderByComparator, true);

			array[1] = calendar;

			array[2] = getByUuid_C_PrevAndNext(session, calendar, uuid,
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

	protected Calendar getByUuid_C_PrevAndNext(Session session,
		Calendar calendar, String uuid, long companyId,
		OrderByComparator<Calendar> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDAR_WHERE);

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
			query.append(CalendarModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(calendar);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Calendar> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the calendars where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Calendar calendar : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(calendar);
		}
	}

	/**
	 * Returns the number of calendars where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching calendars
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CALENDAR_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "calendar.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "calendar.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(calendar.uuid IS NULL OR calendar.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "calendar.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_C = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			CalendarModelImpl.GROUPID_COLUMN_BITMASK |
			CalendarModelImpl.CALENDARRESOURCEID_COLUMN_BITMASK |
			CalendarModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_C = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_C",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the calendars where groupId = &#63; and calendarResourceId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @return the matching calendars
	 */
	@Override
	public List<Calendar> findByG_C(long groupId, long calendarResourceId) {
		return findByG_C(groupId, calendarResourceId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendars where groupId = &#63; and calendarResourceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @return the range of matching calendars
	 */
	@Override
	public List<Calendar> findByG_C(long groupId, long calendarResourceId,
		int start, int end) {
		return findByG_C(groupId, calendarResourceId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendars where groupId = &#63; and calendarResourceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendars
	 */
	@Override
	public List<Calendar> findByG_C(long groupId, long calendarResourceId,
		int start, int end, OrderByComparator<Calendar> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C;
			finderArgs = new Object[] { groupId, calendarResourceId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_C;
			finderArgs = new Object[] {
					groupId, calendarResourceId,
					
					start, end, orderByComparator
				};
		}

		List<Calendar> list = (List<Calendar>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Calendar calendar : list) {
				if ((groupId != calendar.getGroupId()) ||
						(calendarResourceId != calendar.getCalendarResourceId())) {
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

			query.append(_SQL_SELECT_CALENDAR_WHERE);

			query.append(_FINDER_COLUMN_G_C_GROUPID_2);

			query.append(_FINDER_COLUMN_G_C_CALENDARRESOURCEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CalendarModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(calendarResourceId);

				if (!pagination) {
					list = (List<Calendar>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Calendar>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar
	 * @throws NoSuchCalendarException if a matching calendar could not be found
	 */
	@Override
	public Calendar findByG_C_First(long groupId, long calendarResourceId,
		OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {
		Calendar calendar = fetchByG_C_First(groupId, calendarResourceId,
				orderByComparator);

		if (calendar != null) {
			return calendar;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", calendarResourceId=");
		msg.append(calendarResourceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCalendarException(msg.toString());
	}

	/**
	 * Returns the first calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar, or <code>null</code> if a matching calendar could not be found
	 */
	@Override
	public Calendar fetchByG_C_First(long groupId, long calendarResourceId,
		OrderByComparator<Calendar> orderByComparator) {
		List<Calendar> list = findByG_C(groupId, calendarResourceId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar
	 * @throws NoSuchCalendarException if a matching calendar could not be found
	 */
	@Override
	public Calendar findByG_C_Last(long groupId, long calendarResourceId,
		OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {
		Calendar calendar = fetchByG_C_Last(groupId, calendarResourceId,
				orderByComparator);

		if (calendar != null) {
			return calendar;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", calendarResourceId=");
		msg.append(calendarResourceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCalendarException(msg.toString());
	}

	/**
	 * Returns the last calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar, or <code>null</code> if a matching calendar could not be found
	 */
	@Override
	public Calendar fetchByG_C_Last(long groupId, long calendarResourceId,
		OrderByComparator<Calendar> orderByComparator) {
		int count = countByG_C(groupId, calendarResourceId);

		if (count == 0) {
			return null;
		}

		List<Calendar> list = findByG_C(groupId, calendarResourceId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the calendars before and after the current calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63;.
	 *
	 * @param calendarId the primary key of the current calendar
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar
	 * @throws NoSuchCalendarException if a calendar with the primary key could not be found
	 */
	@Override
	public Calendar[] findByG_C_PrevAndNext(long calendarId, long groupId,
		long calendarResourceId, OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {
		Calendar calendar = findByPrimaryKey(calendarId);

		Session session = null;

		try {
			session = openSession();

			Calendar[] array = new CalendarImpl[3];

			array[0] = getByG_C_PrevAndNext(session, calendar, groupId,
					calendarResourceId, orderByComparator, true);

			array[1] = calendar;

			array[2] = getByG_C_PrevAndNext(session, calendar, groupId,
					calendarResourceId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Calendar getByG_C_PrevAndNext(Session session, Calendar calendar,
		long groupId, long calendarResourceId,
		OrderByComparator<Calendar> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDAR_WHERE);

		query.append(_FINDER_COLUMN_G_C_GROUPID_2);

		query.append(_FINDER_COLUMN_G_C_CALENDARRESOURCEID_2);

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
			query.append(CalendarModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(calendarResourceId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(calendar);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Calendar> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the calendars that the user has permission to view where groupId = &#63; and calendarResourceId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @return the matching calendars that the user has permission to view
	 */
	@Override
	public List<Calendar> filterFindByG_C(long groupId, long calendarResourceId) {
		return filterFindByG_C(groupId, calendarResourceId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendars that the user has permission to view where groupId = &#63; and calendarResourceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @return the range of matching calendars that the user has permission to view
	 */
	@Override
	public List<Calendar> filterFindByG_C(long groupId,
		long calendarResourceId, int start, int end) {
		return filterFindByG_C(groupId, calendarResourceId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendars that the user has permissions to view where groupId = &#63; and calendarResourceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendars that the user has permission to view
	 */
	@Override
	public List<Calendar> filterFindByG_C(long groupId,
		long calendarResourceId, int start, int end,
		OrderByComparator<Calendar> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_C(groupId, calendarResourceId, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_CALENDAR_WHERE);

		query.append(_FINDER_COLUMN_G_C_GROUPID_2);

		query.append(_FINDER_COLUMN_G_C_CALENDARRESOURCEID_2);

		if (orderByComparator != null) {
			appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
				orderByComparator);
		}
		else {
			query.append(CalendarModelImpl.ORDER_BY_JPQL);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Calendar.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				_FILTER_ENTITY_TABLE_FILTER_USERID_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(calendarResourceId);

			return (List<Calendar>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the calendars before and after the current calendar in the ordered set of calendars that the user has permission to view where groupId = &#63; and calendarResourceId = &#63;.
	 *
	 * @param calendarId the primary key of the current calendar
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar
	 * @throws NoSuchCalendarException if a calendar with the primary key could not be found
	 */
	@Override
	public Calendar[] filterFindByG_C_PrevAndNext(long calendarId,
		long groupId, long calendarResourceId,
		OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_C_PrevAndNext(calendarId, groupId,
				calendarResourceId, orderByComparator);
		}

		Calendar calendar = findByPrimaryKey(calendarId);

		Session session = null;

		try {
			session = openSession();

			Calendar[] array = new CalendarImpl[3];

			array[0] = filterGetByG_C_PrevAndNext(session, calendar, groupId,
					calendarResourceId, orderByComparator, true);

			array[1] = calendar;

			array[2] = filterGetByG_C_PrevAndNext(session, calendar, groupId,
					calendarResourceId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Calendar filterGetByG_C_PrevAndNext(Session session,
		Calendar calendar, long groupId, long calendarResourceId,
		OrderByComparator<Calendar> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDAR_WHERE);

		query.append(_FINDER_COLUMN_G_C_GROUPID_2);

		query.append(_FINDER_COLUMN_G_C_CALENDARRESOURCEID_2);

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
			query.append(CalendarModelImpl.ORDER_BY_JPQL);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Calendar.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				_FILTER_ENTITY_TABLE_FILTER_USERID_COLUMN, groupId);

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(calendarResourceId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(calendar);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Calendar> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the calendars where groupId = &#63; and calendarResourceId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 */
	@Override
	public void removeByG_C(long groupId, long calendarResourceId) {
		for (Calendar calendar : findByG_C(groupId, calendarResourceId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(calendar);
		}
	}

	/**
	 * Returns the number of calendars where groupId = &#63; and calendarResourceId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @return the number of matching calendars
	 */
	@Override
	public int countByG_C(long groupId, long calendarResourceId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_C;

		Object[] finderArgs = new Object[] { groupId, calendarResourceId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CALENDAR_WHERE);

			query.append(_FINDER_COLUMN_G_C_GROUPID_2);

			query.append(_FINDER_COLUMN_G_C_CALENDARRESOURCEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

	/**
	 * Returns the number of calendars that the user has permission to view where groupId = &#63; and calendarResourceId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @return the number of matching calendars that the user has permission to view
	 */
	@Override
	public int filterCountByG_C(long groupId, long calendarResourceId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_C(groupId, calendarResourceId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_SQL_COUNT_CALENDAR_WHERE);

		query.append(_FINDER_COLUMN_G_C_GROUPID_2);

		query.append(_FINDER_COLUMN_G_C_CALENDARRESOURCEID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Calendar.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				_FILTER_ENTITY_TABLE_FILTER_USERID_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(calendarResourceId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_C_GROUPID_2 = "calendar.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_CALENDARRESOURCEID_2 = "calendar.calendarResourceId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_C_D = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_C_D",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C_D = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_C_D",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			},
			CalendarModelImpl.GROUPID_COLUMN_BITMASK |
			CalendarModelImpl.CALENDARRESOURCEID_COLUMN_BITMASK |
			CalendarModelImpl.DEFAULTCALENDAR_COLUMN_BITMASK |
			CalendarModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_C_D = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_C_D",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the calendars where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param defaultCalendar the default calendar
	 * @return the matching calendars
	 */
	@Override
	public List<Calendar> findByG_C_D(long groupId, long calendarResourceId,
		boolean defaultCalendar) {
		return findByG_C_D(groupId, calendarResourceId, defaultCalendar,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendars where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param defaultCalendar the default calendar
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @return the range of matching calendars
	 */
	@Override
	public List<Calendar> findByG_C_D(long groupId, long calendarResourceId,
		boolean defaultCalendar, int start, int end) {
		return findByG_C_D(groupId, calendarResourceId, defaultCalendar, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the calendars where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param defaultCalendar the default calendar
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendars
	 */
	@Override
	public List<Calendar> findByG_C_D(long groupId, long calendarResourceId,
		boolean defaultCalendar, int start, int end,
		OrderByComparator<Calendar> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C_D;
			finderArgs = new Object[] {
					groupId, calendarResourceId, defaultCalendar
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_C_D;
			finderArgs = new Object[] {
					groupId, calendarResourceId, defaultCalendar,
					
					start, end, orderByComparator
				};
		}

		List<Calendar> list = (List<Calendar>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Calendar calendar : list) {
				if ((groupId != calendar.getGroupId()) ||
						(calendarResourceId != calendar.getCalendarResourceId()) ||
						(defaultCalendar != calendar.getDefaultCalendar())) {
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

			query.append(_SQL_SELECT_CALENDAR_WHERE);

			query.append(_FINDER_COLUMN_G_C_D_GROUPID_2);

			query.append(_FINDER_COLUMN_G_C_D_CALENDARRESOURCEID_2);

			query.append(_FINDER_COLUMN_G_C_D_DEFAULTCALENDAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CalendarModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(calendarResourceId);

				qPos.add(defaultCalendar);

				if (!pagination) {
					list = (List<Calendar>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Calendar>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param defaultCalendar the default calendar
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar
	 * @throws NoSuchCalendarException if a matching calendar could not be found
	 */
	@Override
	public Calendar findByG_C_D_First(long groupId, long calendarResourceId,
		boolean defaultCalendar, OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {
		Calendar calendar = fetchByG_C_D_First(groupId, calendarResourceId,
				defaultCalendar, orderByComparator);

		if (calendar != null) {
			return calendar;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", calendarResourceId=");
		msg.append(calendarResourceId);

		msg.append(", defaultCalendar=");
		msg.append(defaultCalendar);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCalendarException(msg.toString());
	}

	/**
	 * Returns the first calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param defaultCalendar the default calendar
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar, or <code>null</code> if a matching calendar could not be found
	 */
	@Override
	public Calendar fetchByG_C_D_First(long groupId, long calendarResourceId,
		boolean defaultCalendar, OrderByComparator<Calendar> orderByComparator) {
		List<Calendar> list = findByG_C_D(groupId, calendarResourceId,
				defaultCalendar, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param defaultCalendar the default calendar
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar
	 * @throws NoSuchCalendarException if a matching calendar could not be found
	 */
	@Override
	public Calendar findByG_C_D_Last(long groupId, long calendarResourceId,
		boolean defaultCalendar, OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {
		Calendar calendar = fetchByG_C_D_Last(groupId, calendarResourceId,
				defaultCalendar, orderByComparator);

		if (calendar != null) {
			return calendar;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", calendarResourceId=");
		msg.append(calendarResourceId);

		msg.append(", defaultCalendar=");
		msg.append(defaultCalendar);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCalendarException(msg.toString());
	}

	/**
	 * Returns the last calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param defaultCalendar the default calendar
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar, or <code>null</code> if a matching calendar could not be found
	 */
	@Override
	public Calendar fetchByG_C_D_Last(long groupId, long calendarResourceId,
		boolean defaultCalendar, OrderByComparator<Calendar> orderByComparator) {
		int count = countByG_C_D(groupId, calendarResourceId, defaultCalendar);

		if (count == 0) {
			return null;
		}

		List<Calendar> list = findByG_C_D(groupId, calendarResourceId,
				defaultCalendar, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the calendars before and after the current calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	 *
	 * @param calendarId the primary key of the current calendar
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param defaultCalendar the default calendar
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar
	 * @throws NoSuchCalendarException if a calendar with the primary key could not be found
	 */
	@Override
	public Calendar[] findByG_C_D_PrevAndNext(long calendarId, long groupId,
		long calendarResourceId, boolean defaultCalendar,
		OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {
		Calendar calendar = findByPrimaryKey(calendarId);

		Session session = null;

		try {
			session = openSession();

			Calendar[] array = new CalendarImpl[3];

			array[0] = getByG_C_D_PrevAndNext(session, calendar, groupId,
					calendarResourceId, defaultCalendar, orderByComparator, true);

			array[1] = calendar;

			array[2] = getByG_C_D_PrevAndNext(session, calendar, groupId,
					calendarResourceId, defaultCalendar, orderByComparator,
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

	protected Calendar getByG_C_D_PrevAndNext(Session session,
		Calendar calendar, long groupId, long calendarResourceId,
		boolean defaultCalendar, OrderByComparator<Calendar> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDAR_WHERE);

		query.append(_FINDER_COLUMN_G_C_D_GROUPID_2);

		query.append(_FINDER_COLUMN_G_C_D_CALENDARRESOURCEID_2);

		query.append(_FINDER_COLUMN_G_C_D_DEFAULTCALENDAR_2);

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
			query.append(CalendarModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(calendarResourceId);

		qPos.add(defaultCalendar);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(calendar);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Calendar> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the calendars that the user has permission to view where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param defaultCalendar the default calendar
	 * @return the matching calendars that the user has permission to view
	 */
	@Override
	public List<Calendar> filterFindByG_C_D(long groupId,
		long calendarResourceId, boolean defaultCalendar) {
		return filterFindByG_C_D(groupId, calendarResourceId, defaultCalendar,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendars that the user has permission to view where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param defaultCalendar the default calendar
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @return the range of matching calendars that the user has permission to view
	 */
	@Override
	public List<Calendar> filterFindByG_C_D(long groupId,
		long calendarResourceId, boolean defaultCalendar, int start, int end) {
		return filterFindByG_C_D(groupId, calendarResourceId, defaultCalendar,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendars that the user has permissions to view where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param defaultCalendar the default calendar
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendars that the user has permission to view
	 */
	@Override
	public List<Calendar> filterFindByG_C_D(long groupId,
		long calendarResourceId, boolean defaultCalendar, int start, int end,
		OrderByComparator<Calendar> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_C_D(groupId, calendarResourceId, defaultCalendar,
				start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_CALENDAR_WHERE);

		query.append(_FINDER_COLUMN_G_C_D_GROUPID_2);

		query.append(_FINDER_COLUMN_G_C_D_CALENDARRESOURCEID_2);

		query.append(_FINDER_COLUMN_G_C_D_DEFAULTCALENDAR_2);

		if (orderByComparator != null) {
			appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
				orderByComparator);
		}
		else {
			query.append(CalendarModelImpl.ORDER_BY_JPQL);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Calendar.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				_FILTER_ENTITY_TABLE_FILTER_USERID_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(calendarResourceId);

			qPos.add(defaultCalendar);

			return (List<Calendar>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the calendars before and after the current calendar in the ordered set of calendars that the user has permission to view where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	 *
	 * @param calendarId the primary key of the current calendar
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param defaultCalendar the default calendar
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar
	 * @throws NoSuchCalendarException if a calendar with the primary key could not be found
	 */
	@Override
	public Calendar[] filterFindByG_C_D_PrevAndNext(long calendarId,
		long groupId, long calendarResourceId, boolean defaultCalendar,
		OrderByComparator<Calendar> orderByComparator)
		throws NoSuchCalendarException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_C_D_PrevAndNext(calendarId, groupId,
				calendarResourceId, defaultCalendar, orderByComparator);
		}

		Calendar calendar = findByPrimaryKey(calendarId);

		Session session = null;

		try {
			session = openSession();

			Calendar[] array = new CalendarImpl[3];

			array[0] = filterGetByG_C_D_PrevAndNext(session, calendar, groupId,
					calendarResourceId, defaultCalendar, orderByComparator, true);

			array[1] = calendar;

			array[2] = filterGetByG_C_D_PrevAndNext(session, calendar, groupId,
					calendarResourceId, defaultCalendar, orderByComparator,
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

	protected Calendar filterGetByG_C_D_PrevAndNext(Session session,
		Calendar calendar, long groupId, long calendarResourceId,
		boolean defaultCalendar, OrderByComparator<Calendar> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDAR_WHERE);

		query.append(_FINDER_COLUMN_G_C_D_GROUPID_2);

		query.append(_FINDER_COLUMN_G_C_D_CALENDARRESOURCEID_2);

		query.append(_FINDER_COLUMN_G_C_D_DEFAULTCALENDAR_2);

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
			query.append(CalendarModelImpl.ORDER_BY_JPQL);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Calendar.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				_FILTER_ENTITY_TABLE_FILTER_USERID_COLUMN, groupId);

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(calendarResourceId);

		qPos.add(defaultCalendar);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(calendar);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Calendar> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the calendars where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param defaultCalendar the default calendar
	 */
	@Override
	public void removeByG_C_D(long groupId, long calendarResourceId,
		boolean defaultCalendar) {
		for (Calendar calendar : findByG_C_D(groupId, calendarResourceId,
				defaultCalendar, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(calendar);
		}
	}

	/**
	 * Returns the number of calendars where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param defaultCalendar the default calendar
	 * @return the number of matching calendars
	 */
	@Override
	public int countByG_C_D(long groupId, long calendarResourceId,
		boolean defaultCalendar) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_C_D;

		Object[] finderArgs = new Object[] {
				groupId, calendarResourceId, defaultCalendar
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_CALENDAR_WHERE);

			query.append(_FINDER_COLUMN_G_C_D_GROUPID_2);

			query.append(_FINDER_COLUMN_G_C_D_CALENDARRESOURCEID_2);

			query.append(_FINDER_COLUMN_G_C_D_DEFAULTCALENDAR_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(calendarResourceId);

				qPos.add(defaultCalendar);

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
	 * Returns the number of calendars that the user has permission to view where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param defaultCalendar the default calendar
	 * @return the number of matching calendars that the user has permission to view
	 */
	@Override
	public int filterCountByG_C_D(long groupId, long calendarResourceId,
		boolean defaultCalendar) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_C_D(groupId, calendarResourceId, defaultCalendar);
		}

		StringBundler query = new StringBundler(4);

		query.append(_SQL_COUNT_CALENDAR_WHERE);

		query.append(_FINDER_COLUMN_G_C_D_GROUPID_2);

		query.append(_FINDER_COLUMN_G_C_D_CALENDARRESOURCEID_2);

		query.append(_FINDER_COLUMN_G_C_D_DEFAULTCALENDAR_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Calendar.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
				_FILTER_ENTITY_TABLE_FILTER_USERID_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(calendarResourceId);

			qPos.add(defaultCalendar);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_C_D_GROUPID_2 = "calendar.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_D_CALENDARRESOURCEID_2 = "calendar.calendarResourceId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_D_DEFAULTCALENDAR_2 = "calendar.defaultCalendar = ?";

	public CalendarPersistenceImpl() {
		setModelClass(Calendar.class);
	}

	/**
	 * Caches the calendar in the entity cache if it is enabled.
	 *
	 * @param calendar the calendar
	 */
	@Override
	public void cacheResult(Calendar calendar) {
		EntityCacheUtil.putResult(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarImpl.class, calendar.getPrimaryKey(), calendar);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { calendar.getUuid(), calendar.getGroupId() }, calendar);

		calendar.resetOriginalValues();
	}

	/**
	 * Caches the calendars in the entity cache if it is enabled.
	 *
	 * @param calendars the calendars
	 */
	@Override
	public void cacheResult(List<Calendar> calendars) {
		for (Calendar calendar : calendars) {
			if (EntityCacheUtil.getResult(
						CalendarModelImpl.ENTITY_CACHE_ENABLED,
						CalendarImpl.class, calendar.getPrimaryKey()) == null) {
				cacheResult(calendar);
			}
			else {
				calendar.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all calendars.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(CalendarImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the calendar.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Calendar calendar) {
		EntityCacheUtil.removeResult(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarImpl.class, calendar.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(calendar);
	}

	@Override
	public void clearCache(List<Calendar> calendars) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Calendar calendar : calendars) {
			EntityCacheUtil.removeResult(CalendarModelImpl.ENTITY_CACHE_ENABLED,
				CalendarImpl.class, calendar.getPrimaryKey());

			clearUniqueFindersCache(calendar);
		}
	}

	protected void cacheUniqueFindersCache(Calendar calendar) {
		if (calendar.isNew()) {
			Object[] args = new Object[] {
					calendar.getUuid(), calendar.getGroupId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				calendar);
		}
		else {
			CalendarModelImpl calendarModelImpl = (CalendarModelImpl)calendar;

			if ((calendarModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						calendar.getUuid(), calendar.getGroupId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					calendar);
			}
		}
	}

	protected void clearUniqueFindersCache(Calendar calendar) {
		CalendarModelImpl calendarModelImpl = (CalendarModelImpl)calendar;

		Object[] args = new Object[] { calendar.getUuid(), calendar.getGroupId() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((calendarModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					calendarModelImpl.getOriginalUuid(),
					calendarModelImpl.getOriginalGroupId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new calendar with the primary key. Does not add the calendar to the database.
	 *
	 * @param calendarId the primary key for the new calendar
	 * @return the new calendar
	 */
	@Override
	public Calendar create(long calendarId) {
		Calendar calendar = new CalendarImpl();

		calendar.setNew(true);
		calendar.setPrimaryKey(calendarId);

		String uuid = PortalUUIDUtil.generate();

		calendar.setUuid(uuid);

		return calendar;
	}

	/**
	 * Removes the calendar with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param calendarId the primary key of the calendar
	 * @return the calendar that was removed
	 * @throws NoSuchCalendarException if a calendar with the primary key could not be found
	 */
	@Override
	public Calendar remove(long calendarId) throws NoSuchCalendarException {
		return remove((Serializable)calendarId);
	}

	/**
	 * Removes the calendar with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the calendar
	 * @return the calendar that was removed
	 * @throws NoSuchCalendarException if a calendar with the primary key could not be found
	 */
	@Override
	public Calendar remove(Serializable primaryKey)
		throws NoSuchCalendarException {
		Session session = null;

		try {
			session = openSession();

			Calendar calendar = (Calendar)session.get(CalendarImpl.class,
					primaryKey);

			if (calendar == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCalendarException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(calendar);
		}
		catch (NoSuchCalendarException nsee) {
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
	protected Calendar removeImpl(Calendar calendar) {
		calendar = toUnwrappedModel(calendar);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(calendar)) {
				calendar = (Calendar)session.get(CalendarImpl.class,
						calendar.getPrimaryKeyObj());
			}

			if (calendar != null) {
				session.delete(calendar);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (calendar != null) {
			clearCache(calendar);
		}

		return calendar;
	}

	@Override
	public Calendar updateImpl(Calendar calendar) {
		calendar = toUnwrappedModel(calendar);

		boolean isNew = calendar.isNew();

		CalendarModelImpl calendarModelImpl = (CalendarModelImpl)calendar;

		if (Validator.isNull(calendar.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			calendar.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (calendar.isNew()) {
				session.save(calendar);

				calendar.setNew(false);
			}
			else {
				session.merge(calendar);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !CalendarModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((calendarModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEBLOCKID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						calendarModelImpl.getOriginalResourceBlockId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RESOURCEBLOCKID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEBLOCKID,
					args);

				args = new Object[] { calendarModelImpl.getResourceBlockId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RESOURCEBLOCKID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEBLOCKID,
					args);
			}

			if ((calendarModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { calendarModelImpl.getOriginalUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { calendarModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((calendarModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						calendarModelImpl.getOriginalUuid(),
						calendarModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						calendarModelImpl.getUuid(),
						calendarModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((calendarModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						calendarModelImpl.getOriginalGroupId(),
						calendarModelImpl.getOriginalCalendarResourceId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C,
					args);

				args = new Object[] {
						calendarModelImpl.getGroupId(),
						calendarModelImpl.getCalendarResourceId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C,
					args);
			}

			if ((calendarModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C_D.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						calendarModelImpl.getOriginalGroupId(),
						calendarModelImpl.getOriginalCalendarResourceId(),
						calendarModelImpl.getOriginalDefaultCalendar()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_C_D, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C_D,
					args);

				args = new Object[] {
						calendarModelImpl.getGroupId(),
						calendarModelImpl.getCalendarResourceId(),
						calendarModelImpl.getDefaultCalendar()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_C_D, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C_D,
					args);
			}
		}

		EntityCacheUtil.putResult(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarImpl.class, calendar.getPrimaryKey(), calendar, false);

		clearUniqueFindersCache(calendar);
		cacheUniqueFindersCache(calendar);

		calendar.resetOriginalValues();

		return calendar;
	}

	protected Calendar toUnwrappedModel(Calendar calendar) {
		if (calendar instanceof CalendarImpl) {
			return calendar;
		}

		CalendarImpl calendarImpl = new CalendarImpl();

		calendarImpl.setNew(calendar.isNew());
		calendarImpl.setPrimaryKey(calendar.getPrimaryKey());

		calendarImpl.setUuid(calendar.getUuid());
		calendarImpl.setCalendarId(calendar.getCalendarId());
		calendarImpl.setGroupId(calendar.getGroupId());
		calendarImpl.setCompanyId(calendar.getCompanyId());
		calendarImpl.setUserId(calendar.getUserId());
		calendarImpl.setUserName(calendar.getUserName());
		calendarImpl.setCreateDate(calendar.getCreateDate());
		calendarImpl.setModifiedDate(calendar.getModifiedDate());
		calendarImpl.setResourceBlockId(calendar.getResourceBlockId());
		calendarImpl.setCalendarResourceId(calendar.getCalendarResourceId());
		calendarImpl.setName(calendar.getName());
		calendarImpl.setDescription(calendar.getDescription());
		calendarImpl.setTimeZoneId(calendar.getTimeZoneId());
		calendarImpl.setColor(calendar.getColor());
		calendarImpl.setDefaultCalendar(calendar.isDefaultCalendar());
		calendarImpl.setEnableComments(calendar.isEnableComments());
		calendarImpl.setEnableRatings(calendar.isEnableRatings());

		return calendarImpl;
	}

	/**
	 * Returns the calendar with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the calendar
	 * @return the calendar
	 * @throws NoSuchCalendarException if a calendar with the primary key could not be found
	 */
	@Override
	public Calendar findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCalendarException {
		Calendar calendar = fetchByPrimaryKey(primaryKey);

		if (calendar == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCalendarException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return calendar;
	}

	/**
	 * Returns the calendar with the primary key or throws a {@link NoSuchCalendarException} if it could not be found.
	 *
	 * @param calendarId the primary key of the calendar
	 * @return the calendar
	 * @throws NoSuchCalendarException if a calendar with the primary key could not be found
	 */
	@Override
	public Calendar findByPrimaryKey(long calendarId)
		throws NoSuchCalendarException {
		return findByPrimaryKey((Serializable)calendarId);
	}

	/**
	 * Returns the calendar with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the calendar
	 * @return the calendar, or <code>null</code> if a calendar with the primary key could not be found
	 */
	@Override
	public Calendar fetchByPrimaryKey(Serializable primaryKey) {
		Calendar calendar = (Calendar)EntityCacheUtil.getResult(CalendarModelImpl.ENTITY_CACHE_ENABLED,
				CalendarImpl.class, primaryKey);

		if (calendar == _nullCalendar) {
			return null;
		}

		if (calendar == null) {
			Session session = null;

			try {
				session = openSession();

				calendar = (Calendar)session.get(CalendarImpl.class, primaryKey);

				if (calendar != null) {
					cacheResult(calendar);
				}
				else {
					EntityCacheUtil.putResult(CalendarModelImpl.ENTITY_CACHE_ENABLED,
						CalendarImpl.class, primaryKey, _nullCalendar);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(CalendarModelImpl.ENTITY_CACHE_ENABLED,
					CalendarImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return calendar;
	}

	/**
	 * Returns the calendar with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param calendarId the primary key of the calendar
	 * @return the calendar, or <code>null</code> if a calendar with the primary key could not be found
	 */
	@Override
	public Calendar fetchByPrimaryKey(long calendarId) {
		return fetchByPrimaryKey((Serializable)calendarId);
	}

	@Override
	public Map<Serializable, Calendar> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Calendar> map = new HashMap<Serializable, Calendar>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Calendar calendar = fetchByPrimaryKey(primaryKey);

			if (calendar != null) {
				map.put(primaryKey, calendar);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Calendar calendar = (Calendar)EntityCacheUtil.getResult(CalendarModelImpl.ENTITY_CACHE_ENABLED,
					CalendarImpl.class, primaryKey);

			if (calendar == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, calendar);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CALENDAR_WHERE_PKS_IN);

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

			for (Calendar calendar : (List<Calendar>)q.list()) {
				map.put(calendar.getPrimaryKeyObj(), calendar);

				cacheResult(calendar);

				uncachedPrimaryKeys.remove(calendar.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(CalendarModelImpl.ENTITY_CACHE_ENABLED,
					CalendarImpl.class, primaryKey, _nullCalendar);
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
	 * Returns all the calendars.
	 *
	 * @return the calendars
	 */
	@Override
	public List<Calendar> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @return the range of calendars
	 */
	@Override
	public List<Calendar> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CalendarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of calendars
	 */
	@Override
	public List<Calendar> findAll(int start, int end,
		OrderByComparator<Calendar> orderByComparator) {
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

		List<Calendar> list = (List<Calendar>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CALENDAR);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CALENDAR;

				if (pagination) {
					sql = sql.concat(CalendarModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Calendar>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Calendar>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the calendars from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Calendar calendar : findAll()) {
			remove(calendar);
		}
	}

	/**
	 * Returns the number of calendars.
	 *
	 * @return the number of calendars
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CALENDAR);

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
	 * Initializes the calendar persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(CalendarImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_CALENDAR = "SELECT calendar FROM Calendar calendar";
	private static final String _SQL_SELECT_CALENDAR_WHERE_PKS_IN = "SELECT calendar FROM Calendar calendar WHERE calendarId IN (";
	private static final String _SQL_SELECT_CALENDAR_WHERE = "SELECT calendar FROM Calendar calendar WHERE ";
	private static final String _SQL_COUNT_CALENDAR = "SELECT COUNT(calendar) FROM Calendar calendar";
	private static final String _SQL_COUNT_CALENDAR_WHERE = "SELECT COUNT(calendar) FROM Calendar calendar WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "calendar.calendarId";
	private static final String _FILTER_ENTITY_TABLE_FILTER_USERID_COLUMN = "calendar.userId";
	private static final String _ORDER_BY_ENTITY_ALIAS = "calendar.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Calendar exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Calendar exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CalendarPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static final Calendar _nullCalendar = new CalendarImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Calendar> toCacheModel() {
				return _nullCalendarCacheModel;
			}
		};

	private static final CacheModel<Calendar> _nullCalendarCacheModel = new CacheModel<Calendar>() {
			@Override
			public Calendar toEntityModel() {
				return _nullCalendar;
			}
		};
}