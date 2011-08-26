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

package com.liferay.hr.service.persistence;

import com.liferay.hr.NoSuchHolidayException;
import com.liferay.hr.model.HRHoliday;
import com.liferay.hr.model.impl.HRHolidayImpl;
import com.liferay.hr.model.impl.HRHolidayModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQuery;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.RowMapper;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the h r holiday service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRHolidayPersistence
 * @see HRHolidayUtil
 * @generated
 */
public class HRHolidayPersistenceImpl extends BasePersistenceImpl<HRHoliday>
	implements HRHolidayPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRHolidayUtil} to access the h r holiday persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRHolidayImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRHolidayModelImpl.ENTITY_CACHE_ENABLED,
			HRHolidayModelImpl.FINDER_CACHE_ENABLED, HRHolidayImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRHolidayModelImpl.ENTITY_CACHE_ENABLED,
			HRHolidayModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r holiday in the entity cache if it is enabled.
	 *
	 * @param hrHoliday the h r holiday
	 */
	public void cacheResult(HRHoliday hrHoliday) {
		EntityCacheUtil.putResult(HRHolidayModelImpl.ENTITY_CACHE_ENABLED,
			HRHolidayImpl.class, hrHoliday.getPrimaryKey(), hrHoliday);

		hrHoliday.resetOriginalValues();
	}

	/**
	 * Caches the h r holidaies in the entity cache if it is enabled.
	 *
	 * @param hrHolidaies the h r holidaies
	 */
	public void cacheResult(List<HRHoliday> hrHolidaies) {
		for (HRHoliday hrHoliday : hrHolidaies) {
			if (EntityCacheUtil.getResult(
						HRHolidayModelImpl.ENTITY_CACHE_ENABLED,
						HRHolidayImpl.class, hrHoliday.getPrimaryKey(), this) == null) {
				cacheResult(hrHoliday);
			}
		}
	}

	/**
	 * Clears the cache for all h r holidaies.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRHolidayImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRHolidayImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r holiday.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRHoliday hrHoliday) {
		EntityCacheUtil.removeResult(HRHolidayModelImpl.ENTITY_CACHE_ENABLED,
			HRHolidayImpl.class, hrHoliday.getPrimaryKey());
	}

	/**
	 * Creates a new h r holiday with the primary key. Does not add the h r holiday to the database.
	 *
	 * @param hrHolidayId the primary key for the new h r holiday
	 * @return the new h r holiday
	 */
	public HRHoliday create(long hrHolidayId) {
		HRHoliday hrHoliday = new HRHolidayImpl();

		hrHoliday.setNew(true);
		hrHoliday.setPrimaryKey(hrHolidayId);

		return hrHoliday;
	}

	/**
	 * Removes the h r holiday with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r holiday
	 * @return the h r holiday that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r holiday with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRHoliday remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r holiday with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrHolidayId the primary key of the h r holiday
	 * @return the h r holiday that was removed
	 * @throws com.liferay.hr.NoSuchHolidayException if a h r holiday with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRHoliday remove(long hrHolidayId)
		throws NoSuchHolidayException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRHoliday hrHoliday = (HRHoliday)session.get(HRHolidayImpl.class,
					Long.valueOf(hrHolidayId));

			if (hrHoliday == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrHolidayId);
				}

				throw new NoSuchHolidayException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrHolidayId);
			}

			return hrHolidayPersistence.remove(hrHoliday);
		}
		catch (NoSuchHolidayException nsee) {
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
	 * Removes the h r holiday from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrHoliday the h r holiday
	 * @return the h r holiday that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRHoliday remove(HRHoliday hrHoliday) throws SystemException {
		return super.remove(hrHoliday);
	}

	@Override
	protected HRHoliday removeImpl(HRHoliday hrHoliday)
		throws SystemException {
		hrHoliday = toUnwrappedModel(hrHoliday);

		try {
			clearHROffices.clear(hrHoliday.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRHolidayModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrHoliday);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRHolidayModelImpl.ENTITY_CACHE_ENABLED,
			HRHolidayImpl.class, hrHoliday.getPrimaryKey());

		return hrHoliday;
	}

	@Override
	public HRHoliday updateImpl(com.liferay.hr.model.HRHoliday hrHoliday,
		boolean merge) throws SystemException {
		hrHoliday = toUnwrappedModel(hrHoliday);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrHoliday, merge);

			hrHoliday.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRHolidayModelImpl.ENTITY_CACHE_ENABLED,
			HRHolidayImpl.class, hrHoliday.getPrimaryKey(), hrHoliday);

		return hrHoliday;
	}

	protected HRHoliday toUnwrappedModel(HRHoliday hrHoliday) {
		if (hrHoliday instanceof HRHolidayImpl) {
			return hrHoliday;
		}

		HRHolidayImpl hrHolidayImpl = new HRHolidayImpl();

		hrHolidayImpl.setNew(hrHoliday.isNew());
		hrHolidayImpl.setPrimaryKey(hrHoliday.getPrimaryKey());

		hrHolidayImpl.setHrHolidayId(hrHoliday.getHrHolidayId());
		hrHolidayImpl.setGroupId(hrHoliday.getGroupId());
		hrHolidayImpl.setCompanyId(hrHoliday.getCompanyId());
		hrHolidayImpl.setUserId(hrHoliday.getUserId());
		hrHolidayImpl.setUserName(hrHoliday.getUserName());
		hrHolidayImpl.setCreateDate(hrHoliday.getCreateDate());
		hrHolidayImpl.setModifiedDate(hrHoliday.getModifiedDate());
		hrHolidayImpl.setName(hrHoliday.getName());
		hrHolidayImpl.setDescription(hrHoliday.getDescription());
		hrHolidayImpl.setDayOfYear(hrHoliday.getDayOfYear());
		hrHolidayImpl.setYear(hrHoliday.getYear());
		hrHolidayImpl.setPaid(hrHoliday.isPaid());

		return hrHolidayImpl;
	}

	/**
	 * Returns the h r holiday with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r holiday
	 * @return the h r holiday
	 * @throws com.liferay.portal.NoSuchModelException if a h r holiday with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRHoliday findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r holiday with the primary key or throws a {@link com.liferay.hr.NoSuchHolidayException} if it could not be found.
	 *
	 * @param hrHolidayId the primary key of the h r holiday
	 * @return the h r holiday
	 * @throws com.liferay.hr.NoSuchHolidayException if a h r holiday with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRHoliday findByPrimaryKey(long hrHolidayId)
		throws NoSuchHolidayException, SystemException {
		HRHoliday hrHoliday = fetchByPrimaryKey(hrHolidayId);

		if (hrHoliday == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrHolidayId);
			}

			throw new NoSuchHolidayException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrHolidayId);
		}

		return hrHoliday;
	}

	/**
	 * Returns the h r holiday with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r holiday
	 * @return the h r holiday, or <code>null</code> if a h r holiday with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRHoliday fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r holiday with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrHolidayId the primary key of the h r holiday
	 * @return the h r holiday, or <code>null</code> if a h r holiday with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRHoliday fetchByPrimaryKey(long hrHolidayId)
		throws SystemException {
		HRHoliday hrHoliday = (HRHoliday)EntityCacheUtil.getResult(HRHolidayModelImpl.ENTITY_CACHE_ENABLED,
				HRHolidayImpl.class, hrHolidayId, this);

		if (hrHoliday == _nullHRHoliday) {
			return null;
		}

		if (hrHoliday == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrHoliday = (HRHoliday)session.get(HRHolidayImpl.class,
						Long.valueOf(hrHolidayId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrHoliday != null) {
					cacheResult(hrHoliday);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRHolidayModelImpl.ENTITY_CACHE_ENABLED,
						HRHolidayImpl.class, hrHolidayId, _nullHRHoliday);
				}

				closeSession(session);
			}
		}

		return hrHoliday;
	}

	/**
	 * Returns all the h r holidaies.
	 *
	 * @return the h r holidaies
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRHoliday> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r holidaies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r holidaies
	 * @param end the upper bound of the range of h r holidaies (not inclusive)
	 * @return the range of h r holidaies
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRHoliday> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r holidaies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r holidaies
	 * @param end the upper bound of the range of h r holidaies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r holidaies
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRHoliday> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRHoliday> list = (List<HRHoliday>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRHOLIDAY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRHOLIDAY;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRHoliday>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRHoliday>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the h r holidaies from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRHoliday hrHoliday : findAll()) {
			hrHolidayPersistence.remove(hrHoliday);
		}
	}

	/**
	 * Returns the number of h r holidaies.
	 *
	 * @return the number of h r holidaies
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_HRHOLIDAY);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns all the h r offices associated with the h r holiday.
	 *
	 * @param pk the primary key of the h r holiday
	 * @return the h r offices associated with the h r holiday
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.hr.model.HROffice> getHROffices(long pk)
		throws SystemException {
		return getHROffices(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the h r offices associated with the h r holiday.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the h r holiday
	 * @param start the lower bound of the range of h r holidaies
	 * @param end the upper bound of the range of h r holidaies (not inclusive)
	 * @return the range of h r offices associated with the h r holiday
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.hr.model.HROffice> getHROffices(long pk, int start,
		int end) throws SystemException {
		return getHROffices(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_HROFFICES = new FinderPath(com.liferay.hr.model.impl.HROfficeModelImpl.ENTITY_CACHE_ENABLED,
			HRHolidayModelImpl.FINDER_CACHE_ENABLED_HRHOLIDAYS_HROFFICES,
			com.liferay.hr.model.impl.HROfficeImpl.class,
			HRHolidayModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME,
			"getHROffices",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	/**
	 * Returns an ordered range of all the h r offices associated with the h r holiday.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the h r holiday
	 * @param start the lower bound of the range of h r holidaies
	 * @param end the upper bound of the range of h r holidaies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r offices associated with the h r holiday
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.hr.model.HROffice> getHROffices(long pk, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				pk, String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<com.liferay.hr.model.HROffice> list = (List<com.liferay.hr.model.HROffice>)FinderCacheUtil.getResult(FINDER_PATH_GET_HROFFICES,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETHROFFICES.concat(ORDER_BY_CLAUSE)
										   .concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETHROFFICES;
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("HROffice",
					com.liferay.hr.model.impl.HROfficeImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.liferay.hr.model.HROffice>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_HROFFICES,
						finderArgs);
				}
				else {
					hrOfficePersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_HROFFICES,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_HROFFICES_SIZE = new FinderPath(com.liferay.hr.model.impl.HROfficeModelImpl.ENTITY_CACHE_ENABLED,
			HRHolidayModelImpl.FINDER_CACHE_ENABLED_HRHOLIDAYS_HROFFICES,
			Long.class,
			HRHolidayModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME,
			"getHROfficesSize", new String[] { Long.class.getName() });

	/**
	 * Returns the number of h r offices associated with the h r holiday.
	 *
	 * @param pk the primary key of the h r holiday
	 * @return the number of h r offices associated with the h r holiday
	 * @throws SystemException if a system exception occurred
	 */
	public int getHROfficesSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_HROFFICES_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETHROFFICESSIZE);

				q.addScalar(COUNT_COLUMN_NAME,
					com.liferay.portal.kernel.dao.orm.Type.LONG);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_GET_HROFFICES_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_HROFFICE = new FinderPath(com.liferay.hr.model.impl.HROfficeModelImpl.ENTITY_CACHE_ENABLED,
			HRHolidayModelImpl.FINDER_CACHE_ENABLED_HRHOLIDAYS_HROFFICES,
			Boolean.class,
			HRHolidayModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME,
			"containsHROffice",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the h r office is associated with the h r holiday.
	 *
	 * @param pk the primary key of the h r holiday
	 * @param hrOfficePK the primary key of the h r office
	 * @return <code>true</code> if the h r office is associated with the h r holiday; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsHROffice(long pk, long hrOfficePK)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, hrOfficePK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_HROFFICE,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsHROffice.contains(pk, hrOfficePK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_HROFFICE,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Returns <code>true</code> if the h r holiday has any h r offices associated with it.
	 *
	 * @param pk the primary key of the h r holiday to check for associations with h r offices
	 * @return <code>true</code> if the h r holiday has any h r offices associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsHROffices(long pk) throws SystemException {
		if (getHROfficesSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the h r holiday and the h r office. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r holiday
	 * @param hrOfficePK the primary key of the h r office
	 * @throws SystemException if a system exception occurred
	 */
	public void addHROffice(long pk, long hrOfficePK) throws SystemException {
		try {
			addHROffice.add(pk, hrOfficePK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRHolidayModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME);
		}
	}

	/**
	 * Adds an association between the h r holiday and the h r office. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r holiday
	 * @param hrOffice the h r office
	 * @throws SystemException if a system exception occurred
	 */
	public void addHROffice(long pk, com.liferay.hr.model.HROffice hrOffice)
		throws SystemException {
		try {
			addHROffice.add(pk, hrOffice.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRHolidayModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME);
		}
	}

	/**
	 * Adds an association between the h r holiday and the h r offices. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r holiday
	 * @param hrOfficePKs the primary keys of the h r offices
	 * @throws SystemException if a system exception occurred
	 */
	public void addHROffices(long pk, long[] hrOfficePKs)
		throws SystemException {
		try {
			for (long hrOfficePK : hrOfficePKs) {
				addHROffice.add(pk, hrOfficePK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRHolidayModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME);
		}
	}

	/**
	 * Adds an association between the h r holiday and the h r offices. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r holiday
	 * @param hrOffices the h r offices
	 * @throws SystemException if a system exception occurred
	 */
	public void addHROffices(long pk,
		List<com.liferay.hr.model.HROffice> hrOffices)
		throws SystemException {
		try {
			for (com.liferay.hr.model.HROffice hrOffice : hrOffices) {
				addHROffice.add(pk, hrOffice.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRHolidayModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME);
		}
	}

	/**
	 * Clears all associations between the h r holiday and its h r offices. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r holiday to clear the associated h r offices from
	 * @throws SystemException if a system exception occurred
	 */
	public void clearHROffices(long pk) throws SystemException {
		try {
			clearHROffices.clear(pk);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRHolidayModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME);
		}
	}

	/**
	 * Removes the association between the h r holiday and the h r office. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r holiday
	 * @param hrOfficePK the primary key of the h r office
	 * @throws SystemException if a system exception occurred
	 */
	public void removeHROffice(long pk, long hrOfficePK)
		throws SystemException {
		try {
			removeHROffice.remove(pk, hrOfficePK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRHolidayModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME);
		}
	}

	/**
	 * Removes the association between the h r holiday and the h r office. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r holiday
	 * @param hrOffice the h r office
	 * @throws SystemException if a system exception occurred
	 */
	public void removeHROffice(long pk, com.liferay.hr.model.HROffice hrOffice)
		throws SystemException {
		try {
			removeHROffice.remove(pk, hrOffice.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRHolidayModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME);
		}
	}

	/**
	 * Removes the association between the h r holiday and the h r offices. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r holiday
	 * @param hrOfficePKs the primary keys of the h r offices
	 * @throws SystemException if a system exception occurred
	 */
	public void removeHROffices(long pk, long[] hrOfficePKs)
		throws SystemException {
		try {
			for (long hrOfficePK : hrOfficePKs) {
				removeHROffice.remove(pk, hrOfficePK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRHolidayModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME);
		}
	}

	/**
	 * Removes the association between the h r holiday and the h r offices. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r holiday
	 * @param hrOffices the h r offices
	 * @throws SystemException if a system exception occurred
	 */
	public void removeHROffices(long pk,
		List<com.liferay.hr.model.HROffice> hrOffices)
		throws SystemException {
		try {
			for (com.liferay.hr.model.HROffice hrOffice : hrOffices) {
				removeHROffice.remove(pk, hrOffice.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRHolidayModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME);
		}
	}

	/**
	 * Sets the h r offices associated with the h r holiday, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r holiday
	 * @param hrOfficePKs the primary keys of the h r offices to be associated with the h r holiday
	 * @throws SystemException if a system exception occurred
	 */
	public void setHROffices(long pk, long[] hrOfficePKs)
		throws SystemException {
		try {
			Set<Long> hrOfficePKSet = SetUtil.fromArray(hrOfficePKs);

			List<com.liferay.hr.model.HROffice> hrOffices = getHROffices(pk);

			for (com.liferay.hr.model.HROffice hrOffice : hrOffices) {
				if (!hrOfficePKSet.remove(hrOffice.getPrimaryKey())) {
					removeHROffice.remove(pk, hrOffice.getPrimaryKey());
				}
			}

			for (Long hrOfficePK : hrOfficePKSet) {
				addHROffice.add(pk, hrOfficePK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRHolidayModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME);
		}
	}

	/**
	 * Sets the h r offices associated with the h r holiday, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r holiday
	 * @param hrOffices the h r offices to be associated with the h r holiday
	 * @throws SystemException if a system exception occurred
	 */
	public void setHROffices(long pk,
		List<com.liferay.hr.model.HROffice> hrOffices)
		throws SystemException {
		try {
			long[] hrOfficePKs = new long[hrOffices.size()];

			for (int i = 0; i < hrOffices.size(); i++) {
				com.liferay.hr.model.HROffice hrOffice = hrOffices.get(i);

				hrOfficePKs[i] = hrOffice.getPrimaryKey();
			}

			setHROffices(pk, hrOfficePKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRHolidayModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME);
		}
	}

	/**
	 * Initializes the h r holiday persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRHoliday")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRHoliday>> listenersList = new ArrayList<ModelListener<HRHoliday>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRHoliday>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsHROffice = new ContainsHROffice(this);

		addHROffice = new AddHROffice(this);
		clearHROffices = new ClearHROffices(this);
		removeHROffice = new RemoveHROffice(this);
	}

	public void destroy() {
		EntityCacheUtil.removeCache(HRHolidayImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = HRAssetPersistence.class)
	protected HRAssetPersistence hrAssetPersistence;
	@BeanReference(type = HRAssetCheckoutPersistence.class)
	protected HRAssetCheckoutPersistence hrAssetCheckoutPersistence;
	@BeanReference(type = HRAssetDefinitionPersistence.class)
	protected HRAssetDefinitionPersistence hrAssetDefinitionPersistence;
	@BeanReference(type = HRAssetProductPersistence.class)
	protected HRAssetProductPersistence hrAssetProductPersistence;
	@BeanReference(type = HRAssetTypePersistence.class)
	protected HRAssetTypePersistence hrAssetTypePersistence;
	@BeanReference(type = HRAssetVendorPersistence.class)
	protected HRAssetVendorPersistence hrAssetVendorPersistence;
	@BeanReference(type = HRBillabilityPersistence.class)
	protected HRBillabilityPersistence hrBillabilityPersistence;
	@BeanReference(type = HRBranchPersistence.class)
	protected HRBranchPersistence hrBranchPersistence;
	@BeanReference(type = HRClientPersistence.class)
	protected HRClientPersistence hrClientPersistence;
	@BeanReference(type = HREmploymentTypePersistence.class)
	protected HREmploymentTypePersistence hrEmploymentTypePersistence;
	@BeanReference(type = HRExpensePersistence.class)
	protected HRExpensePersistence hrExpensePersistence;
	@BeanReference(type = HRExpenseAccountPersistence.class)
	protected HRExpenseAccountPersistence hrExpenseAccountPersistence;
	@BeanReference(type = HRExpenseCurrencyPersistence.class)
	protected HRExpenseCurrencyPersistence hrExpenseCurrencyPersistence;
	@BeanReference(type = HRExpenseCurrencyConversionPersistence.class)
	protected HRExpenseCurrencyConversionPersistence hrExpenseCurrencyConversionPersistence;
	@BeanReference(type = HRExpenseTypePersistence.class)
	protected HRExpenseTypePersistence hrExpenseTypePersistence;
	@BeanReference(type = HRHolidayPersistence.class)
	protected HRHolidayPersistence hrHolidayPersistence;
	@BeanReference(type = HRJobTitlePersistence.class)
	protected HRJobTitlePersistence hrJobTitlePersistence;
	@BeanReference(type = HROfficePersistence.class)
	protected HROfficePersistence hrOfficePersistence;
	@BeanReference(type = HRProjectPersistence.class)
	protected HRProjectPersistence hrProjectPersistence;
	@BeanReference(type = HRProjectBillingRatePersistence.class)
	protected HRProjectBillingRatePersistence hrProjectBillingRatePersistence;
	@BeanReference(type = HRProjectRolePersistence.class)
	protected HRProjectRolePersistence hrProjectRolePersistence;
	@BeanReference(type = HRProjectStatusPersistence.class)
	protected HRProjectStatusPersistence hrProjectStatusPersistence;
	@BeanReference(type = HRTaskPersistence.class)
	protected HRTaskPersistence hrTaskPersistence;
	@BeanReference(type = HRTaskStatusPersistence.class)
	protected HRTaskStatusPersistence hrTaskStatusPersistence;
	@BeanReference(type = HRTerminationTypePersistence.class)
	protected HRTerminationTypePersistence hrTerminationTypePersistence;
	@BeanReference(type = HRTimeOffPersistence.class)
	protected HRTimeOffPersistence hrTimeOffPersistence;
	@BeanReference(type = HRTimeOffFrequencyTypePersistence.class)
	protected HRTimeOffFrequencyTypePersistence hrTimeOffFrequencyTypePersistence;
	@BeanReference(type = HRTimeOffPolicyPersistence.class)
	protected HRTimeOffPolicyPersistence hrTimeOffPolicyPersistence;
	@BeanReference(type = HRTimeOffTypePersistence.class)
	protected HRTimeOffTypePersistence hrTimeOffTypePersistence;
	@BeanReference(type = HRTimeSheetPersistence.class)
	protected HRTimeSheetPersistence hrTimeSheetPersistence;
	@BeanReference(type = HRTimeSheetDayPersistence.class)
	protected HRTimeSheetDayPersistence hrTimeSheetDayPersistence;
	@BeanReference(type = HRTimeSheetHoursPerDayPersistence.class)
	protected HRTimeSheetHoursPerDayPersistence hrTimeSheetHoursPerDayPersistence;
	@BeanReference(type = HRUserPersistence.class)
	protected HRUserPersistence hrUserPersistence;
	@BeanReference(type = HRUserHistoryPersistence.class)
	protected HRUserHistoryPersistence hrUserHistoryPersistence;
	@BeanReference(type = HRUserProjectPersistence.class)
	protected HRUserProjectPersistence hrUserProjectPersistence;
	@BeanReference(type = HRUserTaskPersistence.class)
	protected HRUserTaskPersistence hrUserTaskPersistence;
	@BeanReference(type = HRUserTimeOffPersistence.class)
	protected HRUserTimeOffPersistence hrUserTimeOffPersistence;
	@BeanReference(type = HRWageTypePersistence.class)
	protected HRWageTypePersistence hrWageTypePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	protected ContainsHROffice containsHROffice;
	protected AddHROffice addHROffice;
	protected ClearHROffices clearHROffices;
	protected RemoveHROffice removeHROffice;

	protected class ContainsHROffice {
		protected ContainsHROffice(HRHolidayPersistenceImpl persistenceImpl) {
			super();

			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSHROFFICE,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long hrHolidayId, long hrOfficeId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(hrHolidayId), new Long(hrOfficeId)
					});

			if (results.size() > 0) {
				Integer count = results.get(0);

				if (count.intValue() > 0) {
					return true;
				}
			}

			return false;
		}

		private MappingSqlQuery<Integer> _mappingSqlQuery;
	}

	protected class AddHROffice {
		protected AddHROffice(HRHolidayPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO HRHolidays_HROffices (hrHolidayId, hrOfficeId) VALUES (?, ?)",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
			_persistenceImpl = persistenceImpl;
		}

		protected void add(long hrHolidayId, long hrOfficeId)
			throws SystemException {
			if (!_persistenceImpl.containsHROffice.contains(hrHolidayId,
						hrOfficeId)) {
				ModelListener<com.liferay.hr.model.HROffice>[] hrOfficeListeners =
					hrOfficePersistence.getListeners();

				for (ModelListener<HRHoliday> listener : listeners) {
					listener.onBeforeAddAssociation(hrHolidayId,
						com.liferay.hr.model.HROffice.class.getName(),
						hrOfficeId);
				}

				for (ModelListener<com.liferay.hr.model.HROffice> listener : hrOfficeListeners) {
					listener.onBeforeAddAssociation(hrOfficeId,
						HRHoliday.class.getName(), hrHolidayId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(hrHolidayId), new Long(hrOfficeId)
					});

				for (ModelListener<HRHoliday> listener : listeners) {
					listener.onAfterAddAssociation(hrHolidayId,
						com.liferay.hr.model.HROffice.class.getName(),
						hrOfficeId);
				}

				for (ModelListener<com.liferay.hr.model.HROffice> listener : hrOfficeListeners) {
					listener.onAfterAddAssociation(hrOfficeId,
						HRHoliday.class.getName(), hrHolidayId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
		private HRHolidayPersistenceImpl _persistenceImpl;
	}

	protected class ClearHROffices {
		protected ClearHROffices(HRHolidayPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM HRHolidays_HROffices WHERE hrHolidayId = ?",
					new int[] { java.sql.Types.BIGINT });
		}

		protected void clear(long hrHolidayId) throws SystemException {
			ModelListener<com.liferay.hr.model.HROffice>[] hrOfficeListeners = hrOfficePersistence.getListeners();

			List<com.liferay.hr.model.HROffice> hrOffices = null;

			if ((listeners.length > 0) || (hrOfficeListeners.length > 0)) {
				hrOffices = getHROffices(hrHolidayId);

				for (com.liferay.hr.model.HROffice hrOffice : hrOffices) {
					for (ModelListener<HRHoliday> listener : listeners) {
						listener.onBeforeRemoveAssociation(hrHolidayId,
							com.liferay.hr.model.HROffice.class.getName(),
							hrOffice.getPrimaryKey());
					}

					for (ModelListener<com.liferay.hr.model.HROffice> listener : hrOfficeListeners) {
						listener.onBeforeRemoveAssociation(hrOffice.getPrimaryKey(),
							HRHoliday.class.getName(), hrHolidayId);
					}
				}
			}

			_sqlUpdate.update(new Object[] { new Long(hrHolidayId) });

			if ((listeners.length > 0) || (hrOfficeListeners.length > 0)) {
				for (com.liferay.hr.model.HROffice hrOffice : hrOffices) {
					for (ModelListener<HRHoliday> listener : listeners) {
						listener.onAfterRemoveAssociation(hrHolidayId,
							com.liferay.hr.model.HROffice.class.getName(),
							hrOffice.getPrimaryKey());
					}

					for (ModelListener<com.liferay.hr.model.HROffice> listener : hrOfficeListeners) {
						listener.onAfterRemoveAssociation(hrOffice.getPrimaryKey(),
							HRHoliday.class.getName(), hrHolidayId);
					}
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemoveHROffice {
		protected RemoveHROffice(HRHolidayPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM HRHolidays_HROffices WHERE hrHolidayId = ? AND hrOfficeId = ?",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
			_persistenceImpl = persistenceImpl;
		}

		protected void remove(long hrHolidayId, long hrOfficeId)
			throws SystemException {
			if (_persistenceImpl.containsHROffice.contains(hrHolidayId,
						hrOfficeId)) {
				ModelListener<com.liferay.hr.model.HROffice>[] hrOfficeListeners =
					hrOfficePersistence.getListeners();

				for (ModelListener<HRHoliday> listener : listeners) {
					listener.onBeforeRemoveAssociation(hrHolidayId,
						com.liferay.hr.model.HROffice.class.getName(),
						hrOfficeId);
				}

				for (ModelListener<com.liferay.hr.model.HROffice> listener : hrOfficeListeners) {
					listener.onBeforeRemoveAssociation(hrOfficeId,
						HRHoliday.class.getName(), hrHolidayId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(hrHolidayId), new Long(hrOfficeId)
					});

				for (ModelListener<HRHoliday> listener : listeners) {
					listener.onAfterRemoveAssociation(hrHolidayId,
						com.liferay.hr.model.HROffice.class.getName(),
						hrOfficeId);
				}

				for (ModelListener<com.liferay.hr.model.HROffice> listener : hrOfficeListeners) {
					listener.onAfterRemoveAssociation(hrOfficeId,
						HRHoliday.class.getName(), hrHolidayId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
		private HRHolidayPersistenceImpl _persistenceImpl;
	}

	private static final String _SQL_SELECT_HRHOLIDAY = "SELECT hrHoliday FROM HRHoliday hrHoliday";
	private static final String _SQL_COUNT_HRHOLIDAY = "SELECT COUNT(hrHoliday) FROM HRHoliday hrHoliday";
	private static final String _SQL_GETHROFFICES = "SELECT {HROffice.*} FROM HROffice INNER JOIN HRHolidays_HROffices ON (HRHolidays_HROffices.hrOfficeId = HROffice.hrOfficeId) WHERE (HRHolidays_HROffices.hrHolidayId = ?)";
	private static final String _SQL_GETHROFFICESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM HRHolidays_HROffices WHERE hrHolidayId = ?";
	private static final String _SQL_CONTAINSHROFFICE = "SELECT COUNT(*) AS COUNT_VALUE FROM HRHolidays_HROffices WHERE hrHolidayId = ? AND hrOfficeId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrHoliday.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRHoliday exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRHolidayPersistenceImpl.class);
	private static HRHoliday _nullHRHoliday = new HRHolidayImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRHoliday> toCacheModel() {
				return _nullHRHolidayCacheModel;
			}
		};

	private static CacheModel<HRHoliday> _nullHRHolidayCacheModel = new CacheModel<HRHoliday>() {
			public HRHoliday toEntityModel() {
				return _nullHRHoliday;
			}
		};
}