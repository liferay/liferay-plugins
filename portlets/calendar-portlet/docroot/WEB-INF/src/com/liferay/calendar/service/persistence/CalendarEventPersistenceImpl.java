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

package com.liferay.calendar.service.persistence;

import com.liferay.calendar.NoSuchEventException;
import com.liferay.calendar.model.CalendarEvent;
import com.liferay.calendar.model.impl.CalendarEventImpl;
import com.liferay.calendar.model.impl.CalendarEventModelImpl;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.expando.service.persistence.ExpandoValuePersistence;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the calendar event service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarEventPersistence
 * @see CalendarEventUtil
 * @generated
 */
public class CalendarEventPersistenceImpl extends BasePersistenceImpl<CalendarEvent>
	implements CalendarEventPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CalendarEventUtil} to access the calendar event persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CalendarEventImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_UUID = new FinderPath(CalendarEventModelImpl.ENTITY_CACHE_ENABLED,
			CalendarEventModelImpl.FINDER_CACHE_ENABLED,
			CalendarEventImpl.class, FINDER_CLASS_NAME_LIST, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(CalendarEventModelImpl.ENTITY_CACHE_ENABLED,
			CalendarEventModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(CalendarEventModelImpl.ENTITY_CACHE_ENABLED,
			CalendarEventModelImpl.FINDER_CACHE_ENABLED,
			CalendarEventImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(CalendarEventModelImpl.ENTITY_CACHE_ENABLED,
			CalendarEventModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(CalendarEventModelImpl.ENTITY_CACHE_ENABLED,
			CalendarEventModelImpl.FINDER_CACHE_ENABLED,
			CalendarEventImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CalendarEventModelImpl.ENTITY_CACHE_ENABLED,
			CalendarEventModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the calendar event in the entity cache if it is enabled.
	 *
	 * @param calendarEvent the calendar event
	 */
	public void cacheResult(CalendarEvent calendarEvent) {
		EntityCacheUtil.putResult(CalendarEventModelImpl.ENTITY_CACHE_ENABLED,
			CalendarEventImpl.class, calendarEvent.getPrimaryKey(),
			calendarEvent);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				calendarEvent.getUuid(),
				Long.valueOf(calendarEvent.getGroupId())
			}, calendarEvent);

		calendarEvent.resetOriginalValues();
	}

	/**
	 * Caches the calendar events in the entity cache if it is enabled.
	 *
	 * @param calendarEvents the calendar events
	 */
	public void cacheResult(List<CalendarEvent> calendarEvents) {
		for (CalendarEvent calendarEvent : calendarEvents) {
			if (EntityCacheUtil.getResult(
						CalendarEventModelImpl.ENTITY_CACHE_ENABLED,
						CalendarEventImpl.class, calendarEvent.getPrimaryKey()) == null) {
				cacheResult(calendarEvent);
			}
		}
	}

	/**
	 * Clears the cache for all calendar events.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(CalendarEventImpl.class.getName());
		}

		EntityCacheUtil.clearCache(CalendarEventImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the calendar event.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CalendarEvent calendarEvent) {
		EntityCacheUtil.removeResult(CalendarEventModelImpl.ENTITY_CACHE_ENABLED,
			CalendarEventImpl.class, calendarEvent.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FIND_ALL, FINDER_ARGS_EMPTY);

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				calendarEvent.getUuid(),
				Long.valueOf(calendarEvent.getGroupId())
			});
	}

	/**
	 * Creates a new calendar event with the primary key. Does not add the calendar event to the database.
	 *
	 * @param calendarEventId the primary key for the new calendar event
	 * @return the new calendar event
	 */
	public CalendarEvent create(long calendarEventId) {
		CalendarEvent calendarEvent = new CalendarEventImpl();

		calendarEvent.setNew(true);
		calendarEvent.setPrimaryKey(calendarEventId);

		String uuid = PortalUUIDUtil.generate();

		calendarEvent.setUuid(uuid);

		return calendarEvent;
	}

	/**
	 * Removes the calendar event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the calendar event
	 * @return the calendar event that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a calendar event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CalendarEvent remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the calendar event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param calendarEventId the primary key of the calendar event
	 * @return the calendar event that was removed
	 * @throws com.liferay.calendar.NoSuchEventException if a calendar event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarEvent remove(long calendarEventId)
		throws NoSuchEventException, SystemException {
		Session session = null;

		try {
			session = openSession();

			CalendarEvent calendarEvent = (CalendarEvent)session.get(CalendarEventImpl.class,
					Long.valueOf(calendarEventId));

			if (calendarEvent == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						calendarEventId);
				}

				throw new NoSuchEventException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					calendarEventId);
			}

			return calendarEventPersistence.remove(calendarEvent);
		}
		catch (NoSuchEventException nsee) {
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
	 * Removes the calendar event from the database. Also notifies the appropriate model listeners.
	 *
	 * @param calendarEvent the calendar event
	 * @return the calendar event that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CalendarEvent remove(CalendarEvent calendarEvent)
		throws SystemException {
		return super.remove(calendarEvent);
	}

	@Override
	protected CalendarEvent removeImpl(CalendarEvent calendarEvent)
		throws SystemException {
		calendarEvent = toUnwrappedModel(calendarEvent);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, calendarEvent);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		CalendarEventModelImpl calendarEventModelImpl = (CalendarEventModelImpl)calendarEvent;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				calendarEventModelImpl.getUuid(),
				Long.valueOf(calendarEventModelImpl.getGroupId())
			});

		EntityCacheUtil.removeResult(CalendarEventModelImpl.ENTITY_CACHE_ENABLED,
			CalendarEventImpl.class, calendarEvent.getPrimaryKey());

		return calendarEvent;
	}

	@Override
	public CalendarEvent updateImpl(
		com.liferay.calendar.model.CalendarEvent calendarEvent, boolean merge)
		throws SystemException {
		calendarEvent = toUnwrappedModel(calendarEvent);

		boolean isNew = calendarEvent.isNew();

		CalendarEventModelImpl calendarEventModelImpl = (CalendarEventModelImpl)calendarEvent;

		if (Validator.isNull(calendarEvent.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			calendarEvent.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, calendarEvent, merge);

			calendarEvent.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(CalendarEventModelImpl.ENTITY_CACHE_ENABLED,
			CalendarEventImpl.class, calendarEvent.getPrimaryKey(),
			calendarEvent);

		if (!isNew &&
				(!Validator.equals(calendarEvent.getUuid(),
					calendarEventModelImpl.getOriginalUuid()) ||
				(calendarEvent.getGroupId() != calendarEventModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					calendarEventModelImpl.getOriginalUuid(),
					Long.valueOf(calendarEventModelImpl.getOriginalGroupId())
				});
		}

		if (isNew ||
				(!Validator.equals(calendarEvent.getUuid(),
					calendarEventModelImpl.getOriginalUuid()) ||
				(calendarEvent.getGroupId() != calendarEventModelImpl.getOriginalGroupId()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					calendarEvent.getUuid(),
					Long.valueOf(calendarEvent.getGroupId())
				}, calendarEvent);
		}

		return calendarEvent;
	}

	protected CalendarEvent toUnwrappedModel(CalendarEvent calendarEvent) {
		if (calendarEvent instanceof CalendarEventImpl) {
			return calendarEvent;
		}

		CalendarEventImpl calendarEventImpl = new CalendarEventImpl();

		calendarEventImpl.setNew(calendarEvent.isNew());
		calendarEventImpl.setPrimaryKey(calendarEvent.getPrimaryKey());

		calendarEventImpl.setUuid(calendarEvent.getUuid());
		calendarEventImpl.setCalendarEventId(calendarEvent.getCalendarEventId());
		calendarEventImpl.setGroupId(calendarEvent.getGroupId());
		calendarEventImpl.setCompanyId(calendarEvent.getCompanyId());
		calendarEventImpl.setUserId(calendarEvent.getUserId());
		calendarEventImpl.setUserName(calendarEvent.getUserName());
		calendarEventImpl.setCreateDate(calendarEvent.getCreateDate());
		calendarEventImpl.setModifiedDate(calendarEvent.getModifiedDate());
		calendarEventImpl.setTitle(calendarEvent.getTitle());
		calendarEventImpl.setDescription(calendarEvent.getDescription());
		calendarEventImpl.setLocation(calendarEvent.getLocation());
		calendarEventImpl.setStartDate(calendarEvent.getStartDate());
		calendarEventImpl.setEndDate(calendarEvent.getEndDate());
		calendarEventImpl.setDurationHour(calendarEvent.getDurationHour());
		calendarEventImpl.setDurationMinute(calendarEvent.getDurationMinute());
		calendarEventImpl.setAllDay(calendarEvent.isAllDay());
		calendarEventImpl.setRecurrence(calendarEvent.getRecurrence());
		calendarEventImpl.setType(calendarEvent.getType());
		calendarEventImpl.setRemindBy(calendarEvent.getRemindBy());
		calendarEventImpl.setFirstReminder(calendarEvent.getFirstReminder());
		calendarEventImpl.setSecondReminder(calendarEvent.getSecondReminder());

		return calendarEventImpl;
	}

	/**
	 * Returns the calendar event with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the calendar event
	 * @return the calendar event
	 * @throws com.liferay.portal.NoSuchModelException if a calendar event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CalendarEvent findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the calendar event with the primary key or throws a {@link com.liferay.calendar.NoSuchEventException} if it could not be found.
	 *
	 * @param calendarEventId the primary key of the calendar event
	 * @return the calendar event
	 * @throws com.liferay.calendar.NoSuchEventException if a calendar event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarEvent findByPrimaryKey(long calendarEventId)
		throws NoSuchEventException, SystemException {
		CalendarEvent calendarEvent = fetchByPrimaryKey(calendarEventId);

		if (calendarEvent == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + calendarEventId);
			}

			throw new NoSuchEventException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				calendarEventId);
		}

		return calendarEvent;
	}

	/**
	 * Returns the calendar event with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the calendar event
	 * @return the calendar event, or <code>null</code> if a calendar event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CalendarEvent fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the calendar event with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param calendarEventId the primary key of the calendar event
	 * @return the calendar event, or <code>null</code> if a calendar event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarEvent fetchByPrimaryKey(long calendarEventId)
		throws SystemException {
		CalendarEvent calendarEvent = (CalendarEvent)EntityCacheUtil.getResult(CalendarEventModelImpl.ENTITY_CACHE_ENABLED,
				CalendarEventImpl.class, calendarEventId);

		if (calendarEvent == _nullCalendarEvent) {
			return null;
		}

		if (calendarEvent == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				calendarEvent = (CalendarEvent)session.get(CalendarEventImpl.class,
						Long.valueOf(calendarEventId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (calendarEvent != null) {
					cacheResult(calendarEvent);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(CalendarEventModelImpl.ENTITY_CACHE_ENABLED,
						CalendarEventImpl.class, calendarEventId,
						_nullCalendarEvent);
				}

				closeSession(session);
			}
		}

		return calendarEvent;
	}

	/**
	 * Returns all the calendar events where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching calendar events
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarEvent> findByUuid(String uuid)
		throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar events where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of calendar events
	 * @param end the upper bound of the range of calendar events (not inclusive)
	 * @return the range of matching calendar events
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarEvent> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar events where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of calendar events
	 * @param end the upper bound of the range of calendar events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar events
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarEvent> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, start, end, orderByComparator };

		List<CalendarEvent> list = (List<CalendarEvent>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_UUID,
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

			query.append(_SQL_SELECT_CALENDAREVENT_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(CalendarEventModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				list = (List<CalendarEvent>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_UUID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_UUID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first calendar event in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar event
	 * @throws com.liferay.calendar.NoSuchEventException if a matching calendar event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarEvent findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchEventException, SystemException {
		List<CalendarEvent> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last calendar event in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar event
	 * @throws com.liferay.calendar.NoSuchEventException if a matching calendar event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarEvent findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchEventException, SystemException {
		int count = countByUuid(uuid);

		List<CalendarEvent> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEventException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the calendar events before and after the current calendar event in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param calendarEventId the primary key of the current calendar event
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar event
	 * @throws com.liferay.calendar.NoSuchEventException if a calendar event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarEvent[] findByUuid_PrevAndNext(long calendarEventId,
		String uuid, OrderByComparator orderByComparator)
		throws NoSuchEventException, SystemException {
		CalendarEvent calendarEvent = findByPrimaryKey(calendarEventId);

		Session session = null;

		try {
			session = openSession();

			CalendarEvent[] array = new CalendarEventImpl[3];

			array[0] = getByUuid_PrevAndNext(session, calendarEvent, uuid,
					orderByComparator, true);

			array[1] = calendarEvent;

			array[2] = getByUuid_PrevAndNext(session, calendarEvent, uuid,
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

	protected CalendarEvent getByUuid_PrevAndNext(Session session,
		CalendarEvent calendarEvent, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDAREVENT_WHERE);

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else {
			if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}
		}

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
			query.append(CalendarEventModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (uuid != null) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(calendarEvent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CalendarEvent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the calendar event where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.calendar.NoSuchEventException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching calendar event
	 * @throws com.liferay.calendar.NoSuchEventException if a matching calendar event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarEvent findByUUID_G(String uuid, long groupId)
		throws NoSuchEventException, SystemException {
		CalendarEvent calendarEvent = fetchByUUID_G(uuid, groupId);

		if (calendarEvent == null) {
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

			throw new NoSuchEventException(msg.toString());
		}

		return calendarEvent;
	}

	/**
	 * Returns the calendar event where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching calendar event, or <code>null</code> if a matching calendar event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarEvent fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the calendar event where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching calendar event, or <code>null</code> if a matching calendar event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarEvent fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CALENDAREVENT_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_G_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_G_UUID_2);
				}
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			query.append(CalendarEventModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<CalendarEvent> list = q.list();

				result = list;

				CalendarEvent calendarEvent = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					calendarEvent = list.get(0);

					cacheResult(calendarEvent);

					if ((calendarEvent.getUuid() == null) ||
							!calendarEvent.getUuid().equals(uuid) ||
							(calendarEvent.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, calendarEvent);
					}
				}

				return calendarEvent;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
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
				return (CalendarEvent)result;
			}
		}
	}

	/**
	 * Returns all the calendar events.
	 *
	 * @return the calendar events
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarEvent> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendar events
	 * @param end the upper bound of the range of calendar events (not inclusive)
	 * @return the range of calendar events
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarEvent> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendar events
	 * @param end the upper bound of the range of calendar events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of calendar events
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarEvent> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		List<CalendarEvent> list = (List<CalendarEvent>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CALENDAREVENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CALENDAREVENT.concat(CalendarEventModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<CalendarEvent>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<CalendarEvent>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the calendar events where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (CalendarEvent calendarEvent : findByUuid(uuid)) {
			calendarEventPersistence.remove(calendarEvent);
		}
	}

	/**
	 * Removes the calendar event where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUUID_G(String uuid, long groupId)
		throws NoSuchEventException, SystemException {
		CalendarEvent calendarEvent = findByUUID_G(uuid, groupId);

		calendarEventPersistence.remove(calendarEvent);
	}

	/**
	 * Removes all the calendar events from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (CalendarEvent calendarEvent : findAll()) {
			calendarEventPersistence.remove(calendarEvent);
		}
	}

	/**
	 * Returns the number of calendar events where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching calendar events
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CALENDAREVENT_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of calendar events where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching calendar events
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CALENDAREVENT_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_G_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_G_UUID_2);
				}
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of calendar events.
	 *
	 * @return the number of calendar events
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CALENDAREVENT);

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
	 * Initializes the calendar event persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.calendar.model.CalendarEvent")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<CalendarEvent>> listenersList = new ArrayList<ModelListener<CalendarEvent>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<CalendarEvent>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(CalendarEventImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = CalendarBookingPersistence.class)
	protected CalendarBookingPersistence calendarBookingPersistence;
	@BeanReference(type = CalendarEventPersistence.class)
	protected CalendarEventPersistence calendarEventPersistence;
	@BeanReference(type = CalendarResourcePersistence.class)
	protected CalendarResourcePersistence calendarResourcePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = ExpandoValuePersistence.class)
	protected ExpandoValuePersistence expandoValuePersistence;
	private static final String _SQL_SELECT_CALENDAREVENT = "SELECT calendarEvent FROM CalendarEvent calendarEvent";
	private static final String _SQL_SELECT_CALENDAREVENT_WHERE = "SELECT calendarEvent FROM CalendarEvent calendarEvent WHERE ";
	private static final String _SQL_COUNT_CALENDAREVENT = "SELECT COUNT(calendarEvent) FROM CalendarEvent calendarEvent";
	private static final String _SQL_COUNT_CALENDAREVENT_WHERE = "SELECT COUNT(calendarEvent) FROM CalendarEvent calendarEvent WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "calendarEvent.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "calendarEvent.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(calendarEvent.uuid IS NULL OR calendarEvent.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "calendarEvent.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "calendarEvent.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(calendarEvent.uuid IS NULL OR calendarEvent.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "calendarEvent.groupId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "calendarEvent.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CalendarEvent exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CalendarEvent exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(CalendarEventPersistenceImpl.class);
	private static CalendarEvent _nullCalendarEvent = new CalendarEventImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<CalendarEvent> toCacheModel() {
				return _nullCalendarEventCacheModel;
			}
		};

	private static CacheModel<CalendarEvent> _nullCalendarEventCacheModel = new CacheModel<CalendarEvent>() {
			public CalendarEvent toEntityModel() {
				return _nullCalendarEvent;
			}
		};
}