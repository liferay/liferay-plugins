/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.calendar.NoSuchCalendarException;
import com.liferay.calendar.model.Calendar;
import com.liferay.calendar.model.impl.CalendarImpl;
import com.liferay.calendar.model.impl.CalendarModelImpl;

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
 * The persistence implementation for the calendar service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarPersistence
 * @see CalendarUtil
 * @generated
 */
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
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RESOURCEBLOCKID =
		new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByResourceBlockId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEBLOCKID =
		new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByResourceBlockId",
			new String[] { Long.class.getName() },
			CalendarModelImpl.RESOURCEBLOCKID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RESOURCEBLOCKID = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByResourceBlockId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			CalendarModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });
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
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_C = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			CalendarModelImpl.GROUPID_COLUMN_BITMASK |
			CalendarModelImpl.CALENDARRESOURCEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_C = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_C",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_C_D = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_C_D",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
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
			CalendarModelImpl.DEFAULTCALENDAR_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_C_D = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_C_D",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, CalendarImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the calendar in the entity cache if it is enabled.
	 *
	 * @param calendar the calendar
	 */
	public void cacheResult(Calendar calendar) {
		EntityCacheUtil.putResult(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarImpl.class, calendar.getPrimaryKey(), calendar);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { calendar.getUuid(), Long.valueOf(
					calendar.getGroupId()) }, calendar);

		calendar.resetOriginalValues();
	}

	/**
	 * Caches the calendars in the entity cache if it is enabled.
	 *
	 * @param calendars the calendars
	 */
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
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(CalendarImpl.class.getName());
		}

		EntityCacheUtil.clearCache(CalendarImpl.class.getName());

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

	protected void clearUniqueFindersCache(Calendar calendar) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { calendar.getUuid(), Long.valueOf(
					calendar.getGroupId()) });
	}

	/**
	 * Creates a new calendar with the primary key. Does not add the calendar to the database.
	 *
	 * @param calendarId the primary key for the new calendar
	 * @return the new calendar
	 */
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
	 * @throws com.liferay.calendar.NoSuchCalendarException if a calendar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Calendar remove(long calendarId)
		throws NoSuchCalendarException, SystemException {
		return remove(Long.valueOf(calendarId));
	}

	/**
	 * Removes the calendar with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the calendar
	 * @return the calendar that was removed
	 * @throws com.liferay.calendar.NoSuchCalendarException if a calendar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Calendar remove(Serializable primaryKey)
		throws NoSuchCalendarException, SystemException {
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
	protected Calendar removeImpl(Calendar calendar) throws SystemException {
		calendar = toUnwrappedModel(calendar);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, calendar);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(calendar);

		return calendar;
	}

	@Override
	public Calendar updateImpl(com.liferay.calendar.model.Calendar calendar,
		boolean merge) throws SystemException {
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

			BatchSessionUtil.update(session, calendar, merge);

			calendar.setNew(false);
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
						Long.valueOf(calendarModelImpl.getOriginalResourceBlockId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RESOURCEBLOCKID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEBLOCKID,
					args);

				args = new Object[] {
						Long.valueOf(calendarModelImpl.getResourceBlockId())
					};

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
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(calendarModelImpl.getOriginalGroupId()),
						Long.valueOf(calendarModelImpl.getOriginalCalendarResourceId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C,
					args);

				args = new Object[] {
						Long.valueOf(calendarModelImpl.getGroupId()),
						Long.valueOf(calendarModelImpl.getCalendarResourceId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C,
					args);
			}

			if ((calendarModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C_D.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(calendarModelImpl.getOriginalGroupId()),
						Long.valueOf(calendarModelImpl.getOriginalCalendarResourceId()),
						Boolean.valueOf(calendarModelImpl.getOriginalDefaultCalendar())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_C_D, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C_D,
					args);

				args = new Object[] {
						Long.valueOf(calendarModelImpl.getGroupId()),
						Long.valueOf(calendarModelImpl.getCalendarResourceId()),
						Boolean.valueOf(calendarModelImpl.getDefaultCalendar())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_C_D, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C_D,
					args);
			}
		}

		EntityCacheUtil.putResult(CalendarModelImpl.ENTITY_CACHE_ENABLED,
			CalendarImpl.class, calendar.getPrimaryKey(), calendar);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					calendar.getUuid(), Long.valueOf(calendar.getGroupId())
				}, calendar);
		}
		else {
			if ((calendarModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						calendarModelImpl.getOriginalUuid(),
						Long.valueOf(calendarModelImpl.getOriginalGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
					new Object[] {
						calendar.getUuid(), Long.valueOf(calendar.getGroupId())
					}, calendar);
			}
		}

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
		calendarImpl.setColor(calendar.getColor());
		calendarImpl.setDefaultCalendar(calendar.isDefaultCalendar());

		return calendarImpl;
	}

	/**
	 * Returns the calendar with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the calendar
	 * @return the calendar
	 * @throws com.liferay.portal.NoSuchModelException if a calendar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Calendar findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the calendar with the primary key or throws a {@link com.liferay.calendar.NoSuchCalendarException} if it could not be found.
	 *
	 * @param calendarId the primary key of the calendar
	 * @return the calendar
	 * @throws com.liferay.calendar.NoSuchCalendarException if a calendar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Calendar findByPrimaryKey(long calendarId)
		throws NoSuchCalendarException, SystemException {
		Calendar calendar = fetchByPrimaryKey(calendarId);

		if (calendar == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + calendarId);
			}

			throw new NoSuchCalendarException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				calendarId);
		}

		return calendar;
	}

	/**
	 * Returns the calendar with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the calendar
	 * @return the calendar, or <code>null</code> if a calendar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Calendar fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the calendar with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param calendarId the primary key of the calendar
	 * @return the calendar, or <code>null</code> if a calendar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Calendar fetchByPrimaryKey(long calendarId)
		throws SystemException {
		Calendar calendar = (Calendar)EntityCacheUtil.getResult(CalendarModelImpl.ENTITY_CACHE_ENABLED,
				CalendarImpl.class, calendarId);

		if (calendar == _nullCalendar) {
			return null;
		}

		if (calendar == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				calendar = (Calendar)session.get(CalendarImpl.class,
						Long.valueOf(calendarId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (calendar != null) {
					cacheResult(calendar);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(CalendarModelImpl.ENTITY_CACHE_ENABLED,
						CalendarImpl.class, calendarId, _nullCalendar);
				}

				closeSession(session);
			}
		}

		return calendar;
	}

	/**
	 * Returns all the calendars where resourceBlockId = &#63;.
	 *
	 * @param resourceBlockId the resource block ID
	 * @return the matching calendars
	 * @throws SystemException if a system exception occurred
	 */
	public List<Calendar> findByResourceBlockId(long resourceBlockId)
		throws SystemException {
		return findByResourceBlockId(resourceBlockId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendars where resourceBlockId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourceBlockId the resource block ID
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @return the range of matching calendars
	 * @throws SystemException if a system exception occurred
	 */
	public List<Calendar> findByResourceBlockId(long resourceBlockId,
		int start, int end) throws SystemException {
		return findByResourceBlockId(resourceBlockId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendars where resourceBlockId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourceBlockId the resource block ID
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendars
	 * @throws SystemException if a system exception occurred
	 */
	public List<Calendar> findByResourceBlockId(long resourceBlockId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
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

			else {
				query.append(CalendarModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourceBlockId);

				list = (List<Calendar>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first calendar in the ordered set where resourceBlockId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourceBlockId the resource block ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar
	 * @throws com.liferay.calendar.NoSuchCalendarException if a matching calendar could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Calendar findByResourceBlockId_First(long resourceBlockId,
		OrderByComparator orderByComparator)
		throws NoSuchCalendarException, SystemException {
		List<Calendar> list = findByResourceBlockId(resourceBlockId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("resourceBlockId=");
			msg.append(resourceBlockId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchCalendarException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last calendar in the ordered set where resourceBlockId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param resourceBlockId the resource block ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar
	 * @throws com.liferay.calendar.NoSuchCalendarException if a matching calendar could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Calendar findByResourceBlockId_Last(long resourceBlockId,
		OrderByComparator orderByComparator)
		throws NoSuchCalendarException, SystemException {
		int count = countByResourceBlockId(resourceBlockId);

		List<Calendar> list = findByResourceBlockId(resourceBlockId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("resourceBlockId=");
			msg.append(resourceBlockId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchCalendarException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the calendars before and after the current calendar in the ordered set where resourceBlockId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param calendarId the primary key of the current calendar
	 * @param resourceBlockId the resource block ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar
	 * @throws com.liferay.calendar.NoSuchCalendarException if a calendar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Calendar[] findByResourceBlockId_PrevAndNext(long calendarId,
		long resourceBlockId, OrderByComparator orderByComparator)
		throws NoSuchCalendarException, SystemException {
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
		OrderByComparator orderByComparator, boolean previous) {
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
	 * Returns all the calendars where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching calendars
	 * @throws SystemException if a system exception occurred
	 */
	public List<Calendar> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendars where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @return the range of matching calendars
	 * @throws SystemException if a system exception occurred
	 */
	public List<Calendar> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendars where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendars
	 * @throws SystemException if a system exception occurred
	 */
	public List<Calendar> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
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
				query.append(CalendarModelImpl.ORDER_BY_JPQL);
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

				list = (List<Calendar>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first calendar in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar
	 * @throws com.liferay.calendar.NoSuchCalendarException if a matching calendar could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Calendar findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchCalendarException, SystemException {
		List<Calendar> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchCalendarException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last calendar in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar
	 * @throws com.liferay.calendar.NoSuchCalendarException if a matching calendar could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Calendar findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchCalendarException, SystemException {
		int count = countByUuid(uuid);

		List<Calendar> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchCalendarException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the calendars before and after the current calendar in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param calendarId the primary key of the current calendar
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar
	 * @throws com.liferay.calendar.NoSuchCalendarException if a calendar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Calendar[] findByUuid_PrevAndNext(long calendarId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchCalendarException, SystemException {
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
		Calendar calendar, String uuid, OrderByComparator orderByComparator,
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

		if (uuid != null) {
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
	 * Returns the calendar where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.calendar.NoSuchCalendarException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching calendar
	 * @throws com.liferay.calendar.NoSuchCalendarException if a matching calendar could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Calendar findByUUID_G(String uuid, long groupId)
		throws NoSuchCalendarException, SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	public Calendar fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the calendar where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching calendar, or <code>null</code> if a matching calendar could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Calendar fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
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

			query.append(CalendarModelImpl.ORDER_BY_JPQL);

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

				List<Calendar> list = q.list();

				result = list;

				Calendar calendar = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					calendar = list.get(0);

					cacheResult(calendar);

					if ((calendar.getUuid() == null) ||
							!calendar.getUuid().equals(uuid) ||
							(calendar.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, calendar);
					}
				}

				return calendar;
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
				return (Calendar)result;
			}
		}
	}

	/**
	 * Returns all the calendars where groupId = &#63; and calendarResourceId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @return the matching calendars
	 * @throws SystemException if a system exception occurred
	 */
	public List<Calendar> findByG_C(long groupId, long calendarResourceId)
		throws SystemException {
		return findByG_C(groupId, calendarResourceId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendars where groupId = &#63; and calendarResourceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @return the range of matching calendars
	 * @throws SystemException if a system exception occurred
	 */
	public List<Calendar> findByG_C(long groupId, long calendarResourceId,
		int start, int end) throws SystemException {
		return findByG_C(groupId, calendarResourceId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendars where groupId = &#63; and calendarResourceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendars
	 * @throws SystemException if a system exception occurred
	 */
	public List<Calendar> findByG_C(long groupId, long calendarResourceId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
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

			else {
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

				list = (List<Calendar>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar
	 * @throws com.liferay.calendar.NoSuchCalendarException if a matching calendar could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Calendar findByG_C_First(long groupId, long calendarResourceId,
		OrderByComparator orderByComparator)
		throws NoSuchCalendarException, SystemException {
		List<Calendar> list = findByG_C(groupId, calendarResourceId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", calendarResourceId=");
			msg.append(calendarResourceId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchCalendarException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar
	 * @throws com.liferay.calendar.NoSuchCalendarException if a matching calendar could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Calendar findByG_C_Last(long groupId, long calendarResourceId,
		OrderByComparator orderByComparator)
		throws NoSuchCalendarException, SystemException {
		int count = countByG_C(groupId, calendarResourceId);

		List<Calendar> list = findByG_C(groupId, calendarResourceId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", calendarResourceId=");
			msg.append(calendarResourceId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchCalendarException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the calendars before and after the current calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param calendarId the primary key of the current calendar
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar
	 * @throws com.liferay.calendar.NoSuchCalendarException if a calendar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Calendar[] findByG_C_PrevAndNext(long calendarId, long groupId,
		long calendarResourceId, OrderByComparator orderByComparator)
		throws NoSuchCalendarException, SystemException {
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
		OrderByComparator orderByComparator, boolean previous) {
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
	 * Returns all the calendars where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param defaultCalendar the default calendar
	 * @return the matching calendars
	 * @throws SystemException if a system exception occurred
	 */
	public List<Calendar> findByG_C_D(long groupId, long calendarResourceId,
		boolean defaultCalendar) throws SystemException {
		return findByG_C_D(groupId, calendarResourceId, defaultCalendar,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendars where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param defaultCalendar the default calendar
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @return the range of matching calendars
	 * @throws SystemException if a system exception occurred
	 */
	public List<Calendar> findByG_C_D(long groupId, long calendarResourceId,
		boolean defaultCalendar, int start, int end) throws SystemException {
		return findByG_C_D(groupId, calendarResourceId, defaultCalendar, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the calendars where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param defaultCalendar the default calendar
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendars
	 * @throws SystemException if a system exception occurred
	 */
	public List<Calendar> findByG_C_D(long groupId, long calendarResourceId,
		boolean defaultCalendar, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
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

			else {
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

				list = (List<Calendar>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param defaultCalendar the default calendar
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar
	 * @throws com.liferay.calendar.NoSuchCalendarException if a matching calendar could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Calendar findByG_C_D_First(long groupId, long calendarResourceId,
		boolean defaultCalendar, OrderByComparator orderByComparator)
		throws NoSuchCalendarException, SystemException {
		List<Calendar> list = findByG_C_D(groupId, calendarResourceId,
				defaultCalendar, 0, 1, orderByComparator);

		if (list.isEmpty()) {
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
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param defaultCalendar the default calendar
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar
	 * @throws com.liferay.calendar.NoSuchCalendarException if a matching calendar could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Calendar findByG_C_D_Last(long groupId, long calendarResourceId,
		boolean defaultCalendar, OrderByComparator orderByComparator)
		throws NoSuchCalendarException, SystemException {
		int count = countByG_C_D(groupId, calendarResourceId, defaultCalendar);

		List<Calendar> list = findByG_C_D(groupId, calendarResourceId,
				defaultCalendar, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
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
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the calendars before and after the current calendar in the ordered set where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param calendarId the primary key of the current calendar
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param defaultCalendar the default calendar
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar
	 * @throws com.liferay.calendar.NoSuchCalendarException if a calendar with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Calendar[] findByG_C_D_PrevAndNext(long calendarId, long groupId,
		long calendarResourceId, boolean defaultCalendar,
		OrderByComparator orderByComparator)
		throws NoSuchCalendarException, SystemException {
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
		boolean defaultCalendar, OrderByComparator orderByComparator,
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
	 * Returns all the calendars.
	 *
	 * @return the calendars
	 * @throws SystemException if a system exception occurred
	 */
	public List<Calendar> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @return the range of calendars
	 * @throws SystemException if a system exception occurred
	 */
	public List<Calendar> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendars
	 * @param end the upper bound of the range of calendars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of calendars
	 * @throws SystemException if a system exception occurred
	 */
	public List<Calendar> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
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
				sql = _SQL_SELECT_CALENDAR.concat(CalendarModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Calendar>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Calendar>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the calendars where resourceBlockId = &#63; from the database.
	 *
	 * @param resourceBlockId the resource block ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByResourceBlockId(long resourceBlockId)
		throws SystemException {
		for (Calendar calendar : findByResourceBlockId(resourceBlockId)) {
			remove(calendar);
		}
	}

	/**
	 * Removes all the calendars where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (Calendar calendar : findByUuid(uuid)) {
			remove(calendar);
		}
	}

	/**
	 * Removes the calendar where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUUID_G(String uuid, long groupId)
		throws NoSuchCalendarException, SystemException {
		Calendar calendar = findByUUID_G(uuid, groupId);

		remove(calendar);
	}

	/**
	 * Removes all the calendars where groupId = &#63; and calendarResourceId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_C(long groupId, long calendarResourceId)
		throws SystemException {
		for (Calendar calendar : findByG_C(groupId, calendarResourceId)) {
			remove(calendar);
		}
	}

	/**
	 * Removes all the calendars where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param defaultCalendar the default calendar
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_C_D(long groupId, long calendarResourceId,
		boolean defaultCalendar) throws SystemException {
		for (Calendar calendar : findByG_C_D(groupId, calendarResourceId,
				defaultCalendar)) {
			remove(calendar);
		}
	}

	/**
	 * Removes all the calendars from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Calendar calendar : findAll()) {
			remove(calendar);
		}
	}

	/**
	 * Returns the number of calendars where resourceBlockId = &#63;.
	 *
	 * @param resourceBlockId the resource block ID
	 * @return the number of matching calendars
	 * @throws SystemException if a system exception occurred
	 */
	public int countByResourceBlockId(long resourceBlockId)
		throws SystemException {
		Object[] finderArgs = new Object[] { resourceBlockId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_RESOURCEBLOCKID,
				finderArgs, this);

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
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_RESOURCEBLOCKID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of calendars where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching calendars
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CALENDAR_WHERE);

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
	 * Returns the number of calendars where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching calendars
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CALENDAR_WHERE);

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
	 * Returns the number of calendars where groupId = &#63; and calendarResourceId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @return the number of matching calendars
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_C(long groupId, long calendarResourceId)
		throws SystemException {
		Object[] finderArgs = new Object[] { groupId, calendarResourceId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_C,
				finderArgs, this);

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
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_C, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of calendars where groupId = &#63; and calendarResourceId = &#63; and defaultCalendar = &#63;.
	 *
	 * @param groupId the group ID
	 * @param calendarResourceId the calendar resource ID
	 * @param defaultCalendar the default calendar
	 * @return the number of matching calendars
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_C_D(long groupId, long calendarResourceId,
		boolean defaultCalendar) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, calendarResourceId, defaultCalendar
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_C_D,
				finderArgs, this);

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
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_C_D,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of calendars.
	 *
	 * @return the number of calendars
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CALENDAR);

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
	 * Initializes the calendar persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.calendar.model.Calendar")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Calendar>> listenersList = new ArrayList<ModelListener<Calendar>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Calendar>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(CalendarImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = CalendarPersistence.class)
	protected CalendarPersistence calendarPersistence;
	@BeanReference(type = CalendarBookingPersistence.class)
	protected CalendarBookingPersistence calendarBookingPersistence;
	@BeanReference(type = CalendarResourcePersistence.class)
	protected CalendarResourcePersistence calendarResourcePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = ExpandoValuePersistence.class)
	protected ExpandoValuePersistence expandoValuePersistence;
	private static final String _SQL_SELECT_CALENDAR = "SELECT calendar FROM Calendar calendar";
	private static final String _SQL_SELECT_CALENDAR_WHERE = "SELECT calendar FROM Calendar calendar WHERE ";
	private static final String _SQL_COUNT_CALENDAR = "SELECT COUNT(calendar) FROM Calendar calendar";
	private static final String _SQL_COUNT_CALENDAR_WHERE = "SELECT COUNT(calendar) FROM Calendar calendar WHERE ";
	private static final String _FINDER_COLUMN_RESOURCEBLOCKID_RESOURCEBLOCKID_2 =
		"calendar.resourceBlockId = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "calendar.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "calendar.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(calendar.uuid IS NULL OR calendar.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "calendar.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "calendar.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(calendar.uuid IS NULL OR calendar.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "calendar.groupId = ?";
	private static final String _FINDER_COLUMN_G_C_GROUPID_2 = "calendar.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_CALENDARRESOURCEID_2 = "calendar.calendarResourceId = ?";
	private static final String _FINDER_COLUMN_G_C_D_GROUPID_2 = "calendar.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_D_CALENDARRESOURCEID_2 = "calendar.calendarResourceId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_D_DEFAULTCALENDAR_2 = "calendar.defaultCalendar = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "calendar.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Calendar exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Calendar exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(CalendarPersistenceImpl.class);
	private static Calendar _nullCalendar = new CalendarImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Calendar> toCacheModel() {
				return _nullCalendarCacheModel;
			}
		};

	private static CacheModel<Calendar> _nullCalendarCacheModel = new CacheModel<Calendar>() {
			public Calendar toEntityModel() {
				return _nullCalendar;
			}
		};
}