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

import com.liferay.calendar.NoSuchBookingException;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.impl.CalendarBookingImpl;
import com.liferay.calendar.model.impl.CalendarBookingModelImpl;

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
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the calendar booking service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarBookingPersistence
 * @see CalendarBookingUtil
 * @generated
 */
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
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			CalendarBookingModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });
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
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			CalendarBookingModelImpl.UUID_COLUMN_BITMASK |
			CalendarBookingModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CALENDARID =
		new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCalendarId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALENDARID =
		new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCalendarId",
			new String[] { Long.class.getName() },
			CalendarBookingModelImpl.CALENDARID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CALENDARID = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCalendarId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CALENDARRESOURCEID =
		new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCalendarResourceId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALENDARRESOURCEID =
		new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCalendarResourceId", new String[] { Long.class.getName() },
			CalendarBookingModelImpl.CALENDARRESOURCEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CALENDARRESOURCEID = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCalendarResourceId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTCALENDARBOOKINGID =
		new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByParentCalendarBookingId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTCALENDARBOOKINGID =
		new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByParentCalendarBookingId",
			new String[] { Long.class.getName() },
			CalendarBookingModelImpl.PARENTCALENDARBOOKINGID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PARENTCALENDARBOOKINGID = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByParentCalendarBookingId",
			new String[] { Long.class.getName() });
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
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_S = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			CalendarBookingModelImpl.CALENDARRESOURCEID_COLUMN_BITMASK |
			CalendarBookingModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_S = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_P_S = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByP_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			CalendarBookingModelImpl.PARENTCALENDARBOOKINGID_COLUMN_BITMASK |
			CalendarBookingModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_P_S = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_S_E = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_S_E",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S_E = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED,
			CalendarBookingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_S_E",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			CalendarBookingModelImpl.CALENDARID_COLUMN_BITMASK |
			CalendarBookingModelImpl.STARTDATE_COLUMN_BITMASK |
			CalendarBookingModelImpl.ENDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_S_E = new FinderPath(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_S_E",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});
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

	/**
	 * Caches the calendar booking in the entity cache if it is enabled.
	 *
	 * @param calendarBooking the calendar booking
	 */
	public void cacheResult(CalendarBooking calendarBooking) {
		EntityCacheUtil.putResult(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingImpl.class, calendarBooking.getPrimaryKey(),
			calendarBooking);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				calendarBooking.getUuid(),
				Long.valueOf(calendarBooking.getGroupId())
			}, calendarBooking);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_P,
			new Object[] {
				Long.valueOf(calendarBooking.getCalendarId()),
				Long.valueOf(calendarBooking.getParentCalendarBookingId())
			}, calendarBooking);

		calendarBooking.resetOriginalValues();
	}

	/**
	 * Caches the calendar bookings in the entity cache if it is enabled.
	 *
	 * @param calendarBookings the calendar bookings
	 */
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
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(CalendarBookingImpl.class.getName());
		}

		EntityCacheUtil.clearCache(CalendarBookingImpl.class.getName());

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

	protected void clearUniqueFindersCache(CalendarBooking calendarBooking) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				calendarBooking.getUuid(),
				Long.valueOf(calendarBooking.getGroupId())
			});

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_P,
			new Object[] {
				Long.valueOf(calendarBooking.getCalendarId()),
				Long.valueOf(calendarBooking.getParentCalendarBookingId())
			});
	}

	/**
	 * Creates a new calendar booking with the primary key. Does not add the calendar booking to the database.
	 *
	 * @param calendarBookingId the primary key for the new calendar booking
	 * @return the new calendar booking
	 */
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
	 * @throws com.liferay.calendar.NoSuchBookingException if a calendar booking with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking remove(long calendarBookingId)
		throws NoSuchBookingException, SystemException {
		return remove(Long.valueOf(calendarBookingId));
	}

	/**
	 * Removes the calendar booking with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the calendar booking
	 * @return the calendar booking that was removed
	 * @throws com.liferay.calendar.NoSuchBookingException if a calendar booking with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CalendarBooking remove(Serializable primaryKey)
		throws NoSuchBookingException, SystemException {
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
	protected CalendarBooking removeImpl(CalendarBooking calendarBooking)
		throws SystemException {
		calendarBooking = toUnwrappedModel(calendarBooking);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, calendarBooking);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(calendarBooking);

		return calendarBooking;
	}

	@Override
	public CalendarBooking updateImpl(
		com.liferay.calendar.model.CalendarBooking calendarBooking,
		boolean merge) throws SystemException {
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

			BatchSessionUtil.update(session, calendarBooking, merge);

			calendarBooking.setNew(false);
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
						Long.valueOf(calendarBookingModelImpl.getOriginalCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						calendarBookingModelImpl.getUuid(),
						Long.valueOf(calendarBookingModelImpl.getCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((calendarBookingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALENDARID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(calendarBookingModelImpl.getOriginalCalendarId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CALENDARID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALENDARID,
					args);

				args = new Object[] {
						Long.valueOf(calendarBookingModelImpl.getCalendarId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CALENDARID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALENDARID,
					args);
			}

			if ((calendarBookingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALENDARRESOURCEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(calendarBookingModelImpl.getOriginalCalendarResourceId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CALENDARRESOURCEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALENDARRESOURCEID,
					args);

				args = new Object[] {
						Long.valueOf(calendarBookingModelImpl.getCalendarResourceId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CALENDARRESOURCEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALENDARRESOURCEID,
					args);
			}

			if ((calendarBookingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTCALENDARBOOKINGID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(calendarBookingModelImpl.getOriginalParentCalendarBookingId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARENTCALENDARBOOKINGID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTCALENDARBOOKINGID,
					args);

				args = new Object[] {
						Long.valueOf(calendarBookingModelImpl.getParentCalendarBookingId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARENTCALENDARBOOKINGID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTCALENDARBOOKINGID,
					args);
			}

			if ((calendarBookingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(calendarBookingModelImpl.getOriginalCalendarResourceId()),
						Integer.valueOf(calendarBookingModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S,
					args);

				args = new Object[] {
						Long.valueOf(calendarBookingModelImpl.getCalendarResourceId()),
						Integer.valueOf(calendarBookingModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S,
					args);
			}

			if ((calendarBookingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(calendarBookingModelImpl.getOriginalParentCalendarBookingId()),
						Integer.valueOf(calendarBookingModelImpl.getOriginalStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_P_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S,
					args);

				args = new Object[] {
						Long.valueOf(calendarBookingModelImpl.getParentCalendarBookingId()),
						Integer.valueOf(calendarBookingModelImpl.getStatus())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_P_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S,
					args);
			}

			if ((calendarBookingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S_E.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(calendarBookingModelImpl.getOriginalCalendarId()),
						Long.valueOf(calendarBookingModelImpl.getOriginalStartDate()),
						Long.valueOf(calendarBookingModelImpl.getOriginalEndDate())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_S_E, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S_E,
					args);

				args = new Object[] {
						Long.valueOf(calendarBookingModelImpl.getCalendarId()),
						Long.valueOf(calendarBookingModelImpl.getStartDate()),
						Long.valueOf(calendarBookingModelImpl.getEndDate())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_S_E, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S_E,
					args);
			}
		}

		EntityCacheUtil.putResult(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
			CalendarBookingImpl.class, calendarBooking.getPrimaryKey(),
			calendarBooking);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
				new Object[] {
					calendarBooking.getUuid(),
					Long.valueOf(calendarBooking.getGroupId())
				}, calendarBooking);

			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_P,
				new Object[] {
					Long.valueOf(calendarBooking.getCalendarId()),
					Long.valueOf(calendarBooking.getParentCalendarBookingId())
				}, calendarBooking);
		}
		else {
			if ((calendarBookingModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						calendarBookingModelImpl.getOriginalUuid(),
						Long.valueOf(calendarBookingModelImpl.getOriginalGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
					new Object[] {
						calendarBooking.getUuid(),
						Long.valueOf(calendarBooking.getGroupId())
					}, calendarBooking);
			}

			if ((calendarBookingModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_C_P.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(calendarBookingModelImpl.getOriginalCalendarId()),
						Long.valueOf(calendarBookingModelImpl.getOriginalParentCalendarBookingId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_P, args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_P, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_P,
					new Object[] {
						Long.valueOf(calendarBooking.getCalendarId()),
						Long.valueOf(
							calendarBooking.getParentCalendarBookingId())
					}, calendarBooking);
			}
		}

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
		calendarBookingImpl.setCalendarId(calendarBooking.getCalendarId());
		calendarBookingImpl.setCalendarResourceId(calendarBooking.getCalendarResourceId());
		calendarBookingImpl.setParentCalendarBookingId(calendarBooking.getParentCalendarBookingId());
		calendarBookingImpl.setTitle(calendarBooking.getTitle());
		calendarBookingImpl.setDescription(calendarBooking.getDescription());
		calendarBookingImpl.setLocation(calendarBooking.getLocation());
		calendarBookingImpl.setStartDate(calendarBooking.getStartDate());
		calendarBookingImpl.setEndDate(calendarBooking.getEndDate());
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
	 * @throws com.liferay.portal.NoSuchModelException if a calendar booking with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CalendarBooking findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the calendar booking with the primary key or throws a {@link com.liferay.calendar.NoSuchBookingException} if it could not be found.
	 *
	 * @param calendarBookingId the primary key of the calendar booking
	 * @return the calendar booking
	 * @throws com.liferay.calendar.NoSuchBookingException if a calendar booking with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking findByPrimaryKey(long calendarBookingId)
		throws NoSuchBookingException, SystemException {
		CalendarBooking calendarBooking = fetchByPrimaryKey(calendarBookingId);

		if (calendarBooking == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + calendarBookingId);
			}

			throw new NoSuchBookingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				calendarBookingId);
		}

		return calendarBooking;
	}

	/**
	 * Returns the calendar booking with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the calendar booking
	 * @return the calendar booking, or <code>null</code> if a calendar booking with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CalendarBooking fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the calendar booking with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param calendarBookingId the primary key of the calendar booking
	 * @return the calendar booking, or <code>null</code> if a calendar booking with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking fetchByPrimaryKey(long calendarBookingId)
		throws SystemException {
		CalendarBooking calendarBooking = (CalendarBooking)EntityCacheUtil.getResult(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
				CalendarBookingImpl.class, calendarBookingId);

		if (calendarBooking == _nullCalendarBooking) {
			return null;
		}

		if (calendarBooking == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				calendarBooking = (CalendarBooking)session.get(CalendarBookingImpl.class,
						Long.valueOf(calendarBookingId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (calendarBooking != null) {
					cacheResult(calendarBooking);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(CalendarBookingModelImpl.ENTITY_CACHE_ENABLED,
						CalendarBookingImpl.class, calendarBookingId,
						_nullCalendarBooking);
				}

				closeSession(session);
			}
		}

		return calendarBooking;
	}

	/**
	 * Returns all the calendar bookings where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> findByUuid(String uuid)
		throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar bookings where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @return the range of matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar bookings where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> findByUuid(String uuid, int start, int end,
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
				query.append(CalendarBookingModelImpl.ORDER_BY_JPQL);
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

				list = (List<CalendarBooking>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first calendar booking in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar booking
	 * @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchBookingException, SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
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
	 * @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchBookingException, SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

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
	 * @throws com.liferay.calendar.NoSuchBookingException if a calendar booking with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking[] findByUuid_PrevAndNext(long calendarBookingId,
		String uuid, OrderByComparator orderByComparator)
		throws NoSuchBookingException, SystemException {
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
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDARBOOKING_WHERE);

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
			query.append(CalendarBookingModelImpl.ORDER_BY_JPQL);
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
	 * Returns the calendar booking where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.calendar.NoSuchBookingException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching calendar booking
	 * @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking findByUUID_G(String uuid, long groupId)
		throws NoSuchBookingException, SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the calendar booking where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
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

			query.append(CalendarBookingModelImpl.ORDER_BY_JPQL);

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

				List<CalendarBooking> list = q.list();

				result = list;

				CalendarBooking calendarBooking = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					calendarBooking = list.get(0);

					cacheResult(calendarBooking);

					if ((calendarBooking.getUuid() == null) ||
							!calendarBooking.getUuid().equals(uuid) ||
							(calendarBooking.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, calendarBooking);
					}
				}

				return calendarBooking;
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
				return (CalendarBooking)result;
			}
		}
	}

	/**
	 * Returns all the calendar bookings where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> findByUuid_C(String uuid, long companyId)
		throws SystemException {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar bookings where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @return the range of matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> findByUuid_C(String uuid, long companyId,
		int start, int end) throws SystemException {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar bookings where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
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

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_C_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_C_UUID_2);
				}
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(CalendarBookingModelImpl.ORDER_BY_JPQL);
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

				qPos.add(companyId);

				list = (List<CalendarBooking>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first calendar booking in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar booking
	 * @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking findByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchBookingException, SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
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
	 * @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchBookingException, SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid_C(uuid, companyId);

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
	 * @throws com.liferay.calendar.NoSuchBookingException if a calendar booking with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking[] findByUuid_C_PrevAndNext(long calendarBookingId,
		String uuid, long companyId, OrderByComparator orderByComparator)
		throws NoSuchBookingException, SystemException {
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
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDARBOOKING_WHERE);

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else {
			if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}
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

		if (uuid != null) {
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
	 * Returns all the calendar bookings where calendarId = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @return the matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> findByCalendarId(long calendarId)
		throws SystemException {
		return findByCalendarId(calendarId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar bookings where calendarId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param calendarId the calendar ID
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @return the range of matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> findByCalendarId(long calendarId, int start,
		int end) throws SystemException {
		return findByCalendarId(calendarId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar bookings where calendarId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param calendarId the calendar ID
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> findByCalendarId(long calendarId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
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

			else {
				query.append(CalendarBookingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(calendarId);

				list = (List<CalendarBooking>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first calendar booking in the ordered set where calendarId = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar booking
	 * @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking findByCalendarId_First(long calendarId,
		OrderByComparator orderByComparator)
		throws NoSuchBookingException, SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking fetchByCalendarId_First(long calendarId,
		OrderByComparator orderByComparator) throws SystemException {
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
	 * @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking findByCalendarId_Last(long calendarId,
		OrderByComparator orderByComparator)
		throws NoSuchBookingException, SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking fetchByCalendarId_Last(long calendarId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCalendarId(calendarId);

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
	 * @throws com.liferay.calendar.NoSuchBookingException if a calendar booking with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking[] findByCalendarId_PrevAndNext(
		long calendarBookingId, long calendarId,
		OrderByComparator orderByComparator)
		throws NoSuchBookingException, SystemException {
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
		OrderByComparator orderByComparator, boolean previous) {
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
	 * Returns all the calendar bookings where calendarResourceId = &#63;.
	 *
	 * @param calendarResourceId the calendar resource ID
	 * @return the matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> findByCalendarResourceId(
		long calendarResourceId) throws SystemException {
		return findByCalendarResourceId(calendarResourceId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar bookings where calendarResourceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param calendarResourceId the calendar resource ID
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @return the range of matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> findByCalendarResourceId(
		long calendarResourceId, int start, int end) throws SystemException {
		return findByCalendarResourceId(calendarResourceId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar bookings where calendarResourceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param calendarResourceId the calendar resource ID
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> findByCalendarResourceId(
		long calendarResourceId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
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

			else {
				query.append(CalendarBookingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(calendarResourceId);

				list = (List<CalendarBooking>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first calendar booking in the ordered set where calendarResourceId = &#63;.
	 *
	 * @param calendarResourceId the calendar resource ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar booking
	 * @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking findByCalendarResourceId_First(
		long calendarResourceId, OrderByComparator orderByComparator)
		throws NoSuchBookingException, SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking fetchByCalendarResourceId_First(
		long calendarResourceId, OrderByComparator orderByComparator)
		throws SystemException {
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
	 * @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking findByCalendarResourceId_Last(
		long calendarResourceId, OrderByComparator orderByComparator)
		throws NoSuchBookingException, SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking fetchByCalendarResourceId_Last(
		long calendarResourceId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByCalendarResourceId(calendarResourceId);

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
	 * @throws com.liferay.calendar.NoSuchBookingException if a calendar booking with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking[] findByCalendarResourceId_PrevAndNext(
		long calendarBookingId, long calendarResourceId,
		OrderByComparator orderByComparator)
		throws NoSuchBookingException, SystemException {
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
		long calendarResourceId, OrderByComparator orderByComparator,
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
	 * Returns all the calendar bookings where parentCalendarBookingId = &#63;.
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @return the matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> findByParentCalendarBookingId(
		long parentCalendarBookingId) throws SystemException {
		return findByParentCalendarBookingId(parentCalendarBookingId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar bookings where parentCalendarBookingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @return the range of matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> findByParentCalendarBookingId(
		long parentCalendarBookingId, int start, int end)
		throws SystemException {
		return findByParentCalendarBookingId(parentCalendarBookingId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the calendar bookings where parentCalendarBookingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> findByParentCalendarBookingId(
		long parentCalendarBookingId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
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

			else {
				query.append(CalendarBookingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentCalendarBookingId);

				list = (List<CalendarBooking>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first calendar booking in the ordered set where parentCalendarBookingId = &#63;.
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar booking
	 * @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking findByParentCalendarBookingId_First(
		long parentCalendarBookingId, OrderByComparator orderByComparator)
		throws NoSuchBookingException, SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking fetchByParentCalendarBookingId_First(
		long parentCalendarBookingId, OrderByComparator orderByComparator)
		throws SystemException {
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
	 * @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking findByParentCalendarBookingId_Last(
		long parentCalendarBookingId, OrderByComparator orderByComparator)
		throws NoSuchBookingException, SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking fetchByParentCalendarBookingId_Last(
		long parentCalendarBookingId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByParentCalendarBookingId(parentCalendarBookingId);

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
	 * @throws com.liferay.calendar.NoSuchBookingException if a calendar booking with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking[] findByParentCalendarBookingId_PrevAndNext(
		long calendarBookingId, long parentCalendarBookingId,
		OrderByComparator orderByComparator)
		throws NoSuchBookingException, SystemException {
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
		long parentCalendarBookingId, OrderByComparator orderByComparator,
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
	 * Returns the calendar booking where calendarId = &#63; and parentCalendarBookingId = &#63; or throws a {@link com.liferay.calendar.NoSuchBookingException} if it could not be found.
	 *
	 * @param calendarId the calendar ID
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @return the matching calendar booking
	 * @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking findByC_P(long calendarId,
		long parentCalendarBookingId)
		throws NoSuchBookingException, SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking fetchByC_P(long calendarId,
		long parentCalendarBookingId) throws SystemException {
		return fetchByC_P(calendarId, parentCalendarBookingId, true);
	}

	/**
	 * Returns the calendar booking where calendarId = &#63; and parentCalendarBookingId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param calendarId the calendar ID
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking fetchByC_P(long calendarId,
		long parentCalendarBookingId, boolean retrieveFromCache)
		throws SystemException {
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

			query.append(CalendarBookingModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(calendarId);

				qPos.add(parentCalendarBookingId);

				List<CalendarBooking> list = q.list();

				result = list;

				CalendarBooking calendarBooking = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_P,
						finderArgs, list);
				}
				else {
					calendarBooking = list.get(0);

					cacheResult(calendarBooking);

					if ((calendarBooking.getCalendarId() != calendarId) ||
							(calendarBooking.getParentCalendarBookingId() != parentCalendarBookingId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_P,
							finderArgs, calendarBooking);
					}
				}

				return calendarBooking;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_P,
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
				return (CalendarBooking)result;
			}
		}
	}

	/**
	 * Returns all the calendar bookings where calendarResourceId = &#63; and status = &#63;.
	 *
	 * @param calendarResourceId the calendar resource ID
	 * @param status the status
	 * @return the matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> findByC_S(long calendarResourceId, int status)
		throws SystemException {
		return findByC_S(calendarResourceId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar bookings where calendarResourceId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param calendarResourceId the calendar resource ID
	 * @param status the status
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @return the range of matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> findByC_S(long calendarResourceId, int status,
		int start, int end) throws SystemException {
		return findByC_S(calendarResourceId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar bookings where calendarResourceId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param calendarResourceId the calendar resource ID
	 * @param status the status
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> findByC_S(long calendarResourceId, int status,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S;
			finderArgs = new Object[] { calendarResourceId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_S;
			finderArgs = new Object[] {
					calendarResourceId, status,
					
					start, end, orderByComparator
				};
		}

		List<CalendarBooking> list = (List<CalendarBooking>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CalendarBooking calendarBooking : list) {
				if ((calendarResourceId != calendarBooking.getCalendarResourceId()) ||
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

			query.append(_FINDER_COLUMN_C_S_CALENDARRESOURCEID_2);

			query.append(_FINDER_COLUMN_C_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(CalendarBookingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(calendarResourceId);

				qPos.add(status);

				list = (List<CalendarBooking>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first calendar booking in the ordered set where calendarResourceId = &#63; and status = &#63;.
	 *
	 * @param calendarResourceId the calendar resource ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar booking
	 * @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking findByC_S_First(long calendarResourceId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchBookingException, SystemException {
		CalendarBooking calendarBooking = fetchByC_S_First(calendarResourceId,
				status, orderByComparator);

		if (calendarBooking != null) {
			return calendarBooking;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("calendarResourceId=");
		msg.append(calendarResourceId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBookingException(msg.toString());
	}

	/**
	 * Returns the first calendar booking in the ordered set where calendarResourceId = &#63; and status = &#63;.
	 *
	 * @param calendarResourceId the calendar resource ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking fetchByC_S_First(long calendarResourceId,
		int status, OrderByComparator orderByComparator)
		throws SystemException {
		List<CalendarBooking> list = findByC_S(calendarResourceId, status, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last calendar booking in the ordered set where calendarResourceId = &#63; and status = &#63;.
	 *
	 * @param calendarResourceId the calendar resource ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar booking
	 * @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking findByC_S_Last(long calendarResourceId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchBookingException, SystemException {
		CalendarBooking calendarBooking = fetchByC_S_Last(calendarResourceId,
				status, orderByComparator);

		if (calendarBooking != null) {
			return calendarBooking;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("calendarResourceId=");
		msg.append(calendarResourceId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBookingException(msg.toString());
	}

	/**
	 * Returns the last calendar booking in the ordered set where calendarResourceId = &#63; and status = &#63;.
	 *
	 * @param calendarResourceId the calendar resource ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking fetchByC_S_Last(long calendarResourceId, int status,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_S(calendarResourceId, status);

		List<CalendarBooking> list = findByC_S(calendarResourceId, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the calendar bookings before and after the current calendar booking in the ordered set where calendarResourceId = &#63; and status = &#63;.
	 *
	 * @param calendarBookingId the primary key of the current calendar booking
	 * @param calendarResourceId the calendar resource ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar booking
	 * @throws com.liferay.calendar.NoSuchBookingException if a calendar booking with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking[] findByC_S_PrevAndNext(long calendarBookingId,
		long calendarResourceId, int status, OrderByComparator orderByComparator)
		throws NoSuchBookingException, SystemException {
		CalendarBooking calendarBooking = findByPrimaryKey(calendarBookingId);

		Session session = null;

		try {
			session = openSession();

			CalendarBooking[] array = new CalendarBookingImpl[3];

			array[0] = getByC_S_PrevAndNext(session, calendarBooking,
					calendarResourceId, status, orderByComparator, true);

			array[1] = calendarBooking;

			array[2] = getByC_S_PrevAndNext(session, calendarBooking,
					calendarResourceId, status, orderByComparator, false);

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
		CalendarBooking calendarBooking, long calendarResourceId, int status,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDARBOOKING_WHERE);

		query.append(_FINDER_COLUMN_C_S_CALENDARRESOURCEID_2);

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

		qPos.add(calendarResourceId);

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
	 * Returns all the calendar bookings where parentCalendarBookingId = &#63; and status = &#63;.
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param status the status
	 * @return the matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> findByP_S(long parentCalendarBookingId,
		int status) throws SystemException {
		return findByP_S(parentCalendarBookingId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar bookings where parentCalendarBookingId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param status the status
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @return the range of matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> findByP_S(long parentCalendarBookingId,
		int status, int start, int end) throws SystemException {
		return findByP_S(parentCalendarBookingId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar bookings where parentCalendarBookingId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param status the status
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> findByP_S(long parentCalendarBookingId,
		int status, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
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

			else {
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

				list = (List<CalendarBooking>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first calendar booking in the ordered set where parentCalendarBookingId = &#63; and status = &#63;.
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar booking
	 * @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking findByP_S_First(long parentCalendarBookingId,
		int status, OrderByComparator orderByComparator)
		throws NoSuchBookingException, SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking fetchByP_S_First(long parentCalendarBookingId,
		int status, OrderByComparator orderByComparator)
		throws SystemException {
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
	 * @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking findByP_S_Last(long parentCalendarBookingId,
		int status, OrderByComparator orderByComparator)
		throws NoSuchBookingException, SystemException {
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
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking fetchByP_S_Last(long parentCalendarBookingId,
		int status, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByP_S(parentCalendarBookingId, status);

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
	 * @throws com.liferay.calendar.NoSuchBookingException if a calendar booking with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking[] findByP_S_PrevAndNext(long calendarBookingId,
		long parentCalendarBookingId, int status,
		OrderByComparator orderByComparator)
		throws NoSuchBookingException, SystemException {
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
		int status, OrderByComparator orderByComparator, boolean previous) {
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
	 * Returns all the calendar bookings where calendarId = &#63; and startDate = &#63; and endDate = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> findByC_S_E(long calendarId, long startDate,
		long endDate) throws SystemException {
		return findByC_S_E(calendarId, startDate, endDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar bookings where calendarId = &#63; and startDate = &#63; and endDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param calendarId the calendar ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @return the range of matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> findByC_S_E(long calendarId, long startDate,
		long endDate, int start, int end) throws SystemException {
		return findByC_S_E(calendarId, startDate, endDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar bookings where calendarId = &#63; and startDate = &#63; and endDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param calendarId the calendar ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> findByC_S_E(long calendarId, long startDate,
		long endDate, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S_E;
			finderArgs = new Object[] { calendarId, startDate, endDate };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_S_E;
			finderArgs = new Object[] {
					calendarId, startDate, endDate,
					
					start, end, orderByComparator
				};
		}

		List<CalendarBooking> list = (List<CalendarBooking>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CalendarBooking calendarBooking : list) {
				if ((calendarId != calendarBooking.getCalendarId()) ||
						(startDate != calendarBooking.getStartDate()) ||
						(endDate != calendarBooking.getEndDate())) {
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

			query.append(_SQL_SELECT_CALENDARBOOKING_WHERE);

			query.append(_FINDER_COLUMN_C_S_E_CALENDARID_2);

			query.append(_FINDER_COLUMN_C_S_E_STARTDATE_2);

			query.append(_FINDER_COLUMN_C_S_E_ENDDATE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(CalendarBookingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(calendarId);

				qPos.add(startDate);

				qPos.add(endDate);

				list = (List<CalendarBooking>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first calendar booking in the ordered set where calendarId = &#63; and startDate = &#63; and endDate = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar booking
	 * @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking findByC_S_E_First(long calendarId, long startDate,
		long endDate, OrderByComparator orderByComparator)
		throws NoSuchBookingException, SystemException {
		CalendarBooking calendarBooking = fetchByC_S_E_First(calendarId,
				startDate, endDate, orderByComparator);

		if (calendarBooking != null) {
			return calendarBooking;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("calendarId=");
		msg.append(calendarId);

		msg.append(", startDate=");
		msg.append(startDate);

		msg.append(", endDate=");
		msg.append(endDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBookingException(msg.toString());
	}

	/**
	 * Returns the first calendar booking in the ordered set where calendarId = &#63; and startDate = &#63; and endDate = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking fetchByC_S_E_First(long calendarId, long startDate,
		long endDate, OrderByComparator orderByComparator)
		throws SystemException {
		List<CalendarBooking> list = findByC_S_E(calendarId, startDate,
				endDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last calendar booking in the ordered set where calendarId = &#63; and startDate = &#63; and endDate = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar booking
	 * @throws com.liferay.calendar.NoSuchBookingException if a matching calendar booking could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking findByC_S_E_Last(long calendarId, long startDate,
		long endDate, OrderByComparator orderByComparator)
		throws NoSuchBookingException, SystemException {
		CalendarBooking calendarBooking = fetchByC_S_E_Last(calendarId,
				startDate, endDate, orderByComparator);

		if (calendarBooking != null) {
			return calendarBooking;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("calendarId=");
		msg.append(calendarId);

		msg.append(", startDate=");
		msg.append(startDate);

		msg.append(", endDate=");
		msg.append(endDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBookingException(msg.toString());
	}

	/**
	 * Returns the last calendar booking in the ordered set where calendarId = &#63; and startDate = &#63; and endDate = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching calendar booking, or <code>null</code> if a matching calendar booking could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking fetchByC_S_E_Last(long calendarId, long startDate,
		long endDate, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByC_S_E(calendarId, startDate, endDate);

		List<CalendarBooking> list = findByC_S_E(calendarId, startDate,
				endDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the calendar bookings before and after the current calendar booking in the ordered set where calendarId = &#63; and startDate = &#63; and endDate = &#63;.
	 *
	 * @param calendarBookingId the primary key of the current calendar booking
	 * @param calendarId the calendar ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next calendar booking
	 * @throws com.liferay.calendar.NoSuchBookingException if a calendar booking with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking[] findByC_S_E_PrevAndNext(long calendarBookingId,
		long calendarId, long startDate, long endDate,
		OrderByComparator orderByComparator)
		throws NoSuchBookingException, SystemException {
		CalendarBooking calendarBooking = findByPrimaryKey(calendarBookingId);

		Session session = null;

		try {
			session = openSession();

			CalendarBooking[] array = new CalendarBookingImpl[3];

			array[0] = getByC_S_E_PrevAndNext(session, calendarBooking,
					calendarId, startDate, endDate, orderByComparator, true);

			array[1] = calendarBooking;

			array[2] = getByC_S_E_PrevAndNext(session, calendarBooking,
					calendarId, startDate, endDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CalendarBooking getByC_S_E_PrevAndNext(Session session,
		CalendarBooking calendarBooking, long calendarId, long startDate,
		long endDate, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CALENDARBOOKING_WHERE);

		query.append(_FINDER_COLUMN_C_S_E_CALENDARID_2);

		query.append(_FINDER_COLUMN_C_S_E_STARTDATE_2);

		query.append(_FINDER_COLUMN_C_S_E_ENDDATE_2);

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

		qPos.add(startDate);

		qPos.add(endDate);

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
	 * Returns all the calendar bookings.
	 *
	 * @return the calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the calendar bookings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @return the range of calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the calendar bookings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of calendar bookings
	 * @param end the upper bound of the range of calendar bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public List<CalendarBooking> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
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
				sql = _SQL_SELECT_CALENDARBOOKING.concat(CalendarBookingModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<CalendarBooking>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<CalendarBooking>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the calendar bookings where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (CalendarBooking calendarBooking : findByUuid(uuid)) {
			remove(calendarBooking);
		}
	}

	/**
	 * Removes the calendar booking where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the calendar booking that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking removeByUUID_G(String uuid, long groupId)
		throws NoSuchBookingException, SystemException {
		CalendarBooking calendarBooking = findByUUID_G(uuid, groupId);

		return remove(calendarBooking);
	}

	/**
	 * Removes all the calendar bookings where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid_C(String uuid, long companyId)
		throws SystemException {
		for (CalendarBooking calendarBooking : findByUuid_C(uuid, companyId)) {
			remove(calendarBooking);
		}
	}

	/**
	 * Removes all the calendar bookings where calendarId = &#63; from the database.
	 *
	 * @param calendarId the calendar ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCalendarId(long calendarId) throws SystemException {
		for (CalendarBooking calendarBooking : findByCalendarId(calendarId)) {
			remove(calendarBooking);
		}
	}

	/**
	 * Removes all the calendar bookings where calendarResourceId = &#63; from the database.
	 *
	 * @param calendarResourceId the calendar resource ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCalendarResourceId(long calendarResourceId)
		throws SystemException {
		for (CalendarBooking calendarBooking : findByCalendarResourceId(
				calendarResourceId)) {
			remove(calendarBooking);
		}
	}

	/**
	 * Removes all the calendar bookings where parentCalendarBookingId = &#63; from the database.
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByParentCalendarBookingId(long parentCalendarBookingId)
		throws SystemException {
		for (CalendarBooking calendarBooking : findByParentCalendarBookingId(
				parentCalendarBookingId)) {
			remove(calendarBooking);
		}
	}

	/**
	 * Removes the calendar booking where calendarId = &#63; and parentCalendarBookingId = &#63; from the database.
	 *
	 * @param calendarId the calendar ID
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @return the calendar booking that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public CalendarBooking removeByC_P(long calendarId,
		long parentCalendarBookingId)
		throws NoSuchBookingException, SystemException {
		CalendarBooking calendarBooking = findByC_P(calendarId,
				parentCalendarBookingId);

		return remove(calendarBooking);
	}

	/**
	 * Removes all the calendar bookings where calendarResourceId = &#63; and status = &#63; from the database.
	 *
	 * @param calendarResourceId the calendar resource ID
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_S(long calendarResourceId, int status)
		throws SystemException {
		for (CalendarBooking calendarBooking : findByC_S(calendarResourceId,
				status)) {
			remove(calendarBooking);
		}
	}

	/**
	 * Removes all the calendar bookings where parentCalendarBookingId = &#63; and status = &#63; from the database.
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param status the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByP_S(long parentCalendarBookingId, int status)
		throws SystemException {
		for (CalendarBooking calendarBooking : findByP_S(
				parentCalendarBookingId, status)) {
			remove(calendarBooking);
		}
	}

	/**
	 * Removes all the calendar bookings where calendarId = &#63; and startDate = &#63; and endDate = &#63; from the database.
	 *
	 * @param calendarId the calendar ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_S_E(long calendarId, long startDate, long endDate)
		throws SystemException {
		for (CalendarBooking calendarBooking : findByC_S_E(calendarId,
				startDate, endDate)) {
			remove(calendarBooking);
		}
	}

	/**
	 * Removes all the calendar bookings from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (CalendarBooking calendarBooking : findAll()) {
			remove(calendarBooking);
		}
	}

	/**
	 * Returns the number of calendar bookings where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CALENDARBOOKING_WHERE);

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
	 * Returns the number of calendar bookings where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CALENDARBOOKING_WHERE);

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
	 * Returns the number of calendar bookings where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid_C(String uuid, long companyId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CALENDARBOOKING_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_C_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_C_UUID_2);
				}
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_C,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of calendar bookings where calendarId = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @return the number of matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCalendarId(long calendarId) throws SystemException {
		Object[] finderArgs = new Object[] { calendarId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CALENDARID,
				finderArgs, this);

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
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CALENDARID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of calendar bookings where calendarResourceId = &#63;.
	 *
	 * @param calendarResourceId the calendar resource ID
	 * @return the number of matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCalendarResourceId(long calendarResourceId)
		throws SystemException {
		Object[] finderArgs = new Object[] { calendarResourceId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CALENDARRESOURCEID,
				finderArgs, this);

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
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CALENDARRESOURCEID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of calendar bookings where parentCalendarBookingId = &#63;.
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @return the number of matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByParentCalendarBookingId(long parentCalendarBookingId)
		throws SystemException {
		Object[] finderArgs = new Object[] { parentCalendarBookingId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PARENTCALENDARBOOKINGID,
				finderArgs, this);

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
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PARENTCALENDARBOOKINGID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of calendar bookings where calendarId = &#63; and parentCalendarBookingId = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @return the number of matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_P(long calendarId, long parentCalendarBookingId)
		throws SystemException {
		Object[] finderArgs = new Object[] { calendarId, parentCalendarBookingId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_P,
				finderArgs, this);

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
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_P, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of calendar bookings where calendarResourceId = &#63; and status = &#63;.
	 *
	 * @param calendarResourceId the calendar resource ID
	 * @param status the status
	 * @return the number of matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_S(long calendarResourceId, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { calendarResourceId, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CALENDARBOOKING_WHERE);

			query.append(_FINDER_COLUMN_C_S_CALENDARRESOURCEID_2);

			query.append(_FINDER_COLUMN_C_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(calendarResourceId);

				qPos.add(status);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_S, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of calendar bookings where parentCalendarBookingId = &#63; and status = &#63;.
	 *
	 * @param parentCalendarBookingId the parent calendar booking ID
	 * @param status the status
	 * @return the number of matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByP_S(long parentCalendarBookingId, int status)
		throws SystemException {
		Object[] finderArgs = new Object[] { parentCalendarBookingId, status };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_P_S,
				finderArgs, this);

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
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_P_S, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of calendar bookings where calendarId = &#63; and startDate = &#63; and endDate = &#63;.
	 *
	 * @param calendarId the calendar ID
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the number of matching calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_S_E(long calendarId, long startDate, long endDate)
		throws SystemException {
		Object[] finderArgs = new Object[] { calendarId, startDate, endDate };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_S_E,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_CALENDARBOOKING_WHERE);

			query.append(_FINDER_COLUMN_C_S_E_CALENDARID_2);

			query.append(_FINDER_COLUMN_C_S_E_STARTDATE_2);

			query.append(_FINDER_COLUMN_C_S_E_ENDDATE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(calendarId);

				qPos.add(startDate);

				qPos.add(endDate);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_S_E,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of calendar bookings.
	 *
	 * @return the number of calendar bookings
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CALENDARBOOKING);

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
	 * Initializes the calendar booking persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.calendar.model.CalendarBooking")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<CalendarBooking>> listenersList = new ArrayList<ModelListener<CalendarBooking>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<CalendarBooking>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(CalendarBookingImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = CalendarPersistence.class)
	protected CalendarPersistence calendarPersistence;
	@BeanReference(type = CalendarBookingPersistence.class)
	protected CalendarBookingPersistence calendarBookingPersistence;
	@BeanReference(type = CalendarResourcePersistence.class)
	protected CalendarResourcePersistence calendarResourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_CALENDARBOOKING = "SELECT calendarBooking FROM CalendarBooking calendarBooking";
	private static final String _SQL_SELECT_CALENDARBOOKING_WHERE = "SELECT calendarBooking FROM CalendarBooking calendarBooking WHERE ";
	private static final String _SQL_COUNT_CALENDARBOOKING = "SELECT COUNT(calendarBooking) FROM CalendarBooking calendarBooking";
	private static final String _SQL_COUNT_CALENDARBOOKING_WHERE = "SELECT COUNT(calendarBooking) FROM CalendarBooking calendarBooking WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "calendarBooking.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "calendarBooking.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(calendarBooking.uuid IS NULL OR calendarBooking.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "calendarBooking.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "calendarBooking.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(calendarBooking.uuid IS NULL OR calendarBooking.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "calendarBooking.groupId = ?";
	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "calendarBooking.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "calendarBooking.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(calendarBooking.uuid IS NULL OR calendarBooking.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "calendarBooking.companyId = ?";
	private static final String _FINDER_COLUMN_CALENDARID_CALENDARID_2 = "calendarBooking.calendarId = ?";
	private static final String _FINDER_COLUMN_CALENDARRESOURCEID_CALENDARRESOURCEID_2 =
		"calendarBooking.calendarResourceId = ?";
	private static final String _FINDER_COLUMN_PARENTCALENDARBOOKINGID_PARENTCALENDARBOOKINGID_2 =
		"calendarBooking.parentCalendarBookingId = ?";
	private static final String _FINDER_COLUMN_C_P_CALENDARID_2 = "calendarBooking.calendarId = ? AND ";
	private static final String _FINDER_COLUMN_C_P_PARENTCALENDARBOOKINGID_2 = "calendarBooking.parentCalendarBookingId = ?";
	private static final String _FINDER_COLUMN_C_S_CALENDARRESOURCEID_2 = "calendarBooking.calendarResourceId = ? AND ";
	private static final String _FINDER_COLUMN_C_S_STATUS_2 = "calendarBooking.status = ?";
	private static final String _FINDER_COLUMN_P_S_PARENTCALENDARBOOKINGID_2 = "calendarBooking.parentCalendarBookingId = ? AND ";
	private static final String _FINDER_COLUMN_P_S_STATUS_2 = "calendarBooking.status = ?";
	private static final String _FINDER_COLUMN_C_S_E_CALENDARID_2 = "calendarBooking.calendarId = ? AND ";
	private static final String _FINDER_COLUMN_C_S_E_STARTDATE_2 = "calendarBooking.startDate = ? AND ";
	private static final String _FINDER_COLUMN_C_S_E_ENDDATE_2 = "calendarBooking.endDate = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "calendarBooking.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CalendarBooking exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CalendarBooking exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(CalendarBookingPersistenceImpl.class);
	private static CalendarBooking _nullCalendarBooking = new CalendarBookingImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<CalendarBooking> toCacheModel() {
				return _nullCalendarBookingCacheModel;
			}
		};

	private static CacheModel<CalendarBooking> _nullCalendarBookingCacheModel = new CacheModel<CalendarBooking>() {
			public CalendarBooking toEntityModel() {
				return _nullCalendarBooking;
			}
		};
}