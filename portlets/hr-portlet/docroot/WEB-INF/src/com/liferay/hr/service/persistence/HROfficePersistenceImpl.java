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

import com.liferay.hr.NoSuchOfficeException;
import com.liferay.hr.model.HROffice;
import com.liferay.hr.model.impl.HROfficeImpl;
import com.liferay.hr.model.impl.HROfficeModelImpl;

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
 * The persistence implementation for the h r office service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HROfficePersistence
 * @see HROfficeUtil
 * @generated
 */
public class HROfficePersistenceImpl extends BasePersistenceImpl<HROffice>
	implements HROfficePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HROfficeUtil} to access the h r office persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HROfficeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HROfficeModelImpl.ENTITY_CACHE_ENABLED,
			HROfficeModelImpl.FINDER_CACHE_ENABLED, HROfficeImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HROfficeModelImpl.ENTITY_CACHE_ENABLED,
			HROfficeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r office in the entity cache if it is enabled.
	 *
	 * @param hrOffice the h r office
	 */
	public void cacheResult(HROffice hrOffice) {
		EntityCacheUtil.putResult(HROfficeModelImpl.ENTITY_CACHE_ENABLED,
			HROfficeImpl.class, hrOffice.getPrimaryKey(), hrOffice);

		hrOffice.resetOriginalValues();
	}

	/**
	 * Caches the h r offices in the entity cache if it is enabled.
	 *
	 * @param hrOffices the h r offices
	 */
	public void cacheResult(List<HROffice> hrOffices) {
		for (HROffice hrOffice : hrOffices) {
			if (EntityCacheUtil.getResult(
						HROfficeModelImpl.ENTITY_CACHE_ENABLED,
						HROfficeImpl.class, hrOffice.getPrimaryKey(), this) == null) {
				cacheResult(hrOffice);
			}
		}
	}

	/**
	 * Clears the cache for all h r offices.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HROfficeImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HROfficeImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r office.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HROffice hrOffice) {
		EntityCacheUtil.removeResult(HROfficeModelImpl.ENTITY_CACHE_ENABLED,
			HROfficeImpl.class, hrOffice.getPrimaryKey());
	}

	/**
	 * Creates a new h r office with the primary key. Does not add the h r office to the database.
	 *
	 * @param hrOfficeId the primary key for the new h r office
	 * @return the new h r office
	 */
	public HROffice create(long hrOfficeId) {
		HROffice hrOffice = new HROfficeImpl();

		hrOffice.setNew(true);
		hrOffice.setPrimaryKey(hrOfficeId);

		return hrOffice;
	}

	/**
	 * Removes the h r office with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r office
	 * @return the h r office that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r office with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HROffice remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r office with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrOfficeId the primary key of the h r office
	 * @return the h r office that was removed
	 * @throws com.liferay.hr.NoSuchOfficeException if a h r office with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HROffice remove(long hrOfficeId)
		throws NoSuchOfficeException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HROffice hrOffice = (HROffice)session.get(HROfficeImpl.class,
					Long.valueOf(hrOfficeId));

			if (hrOffice == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrOfficeId);
				}

				throw new NoSuchOfficeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrOfficeId);
			}

			return hrOfficePersistence.remove(hrOffice);
		}
		catch (NoSuchOfficeException nsee) {
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
	 * Removes the h r office from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrOffice the h r office
	 * @return the h r office that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HROffice remove(HROffice hrOffice) throws SystemException {
		return super.remove(hrOffice);
	}

	@Override
	protected HROffice removeImpl(HROffice hrOffice) throws SystemException {
		hrOffice = toUnwrappedModel(hrOffice);

		try {
			clearHRHolidaies.clear(hrOffice.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HROfficeModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrOffice);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HROfficeModelImpl.ENTITY_CACHE_ENABLED,
			HROfficeImpl.class, hrOffice.getPrimaryKey());

		return hrOffice;
	}

	@Override
	public HROffice updateImpl(com.liferay.hr.model.HROffice hrOffice,
		boolean merge) throws SystemException {
		hrOffice = toUnwrappedModel(hrOffice);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrOffice, merge);

			hrOffice.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HROfficeModelImpl.ENTITY_CACHE_ENABLED,
			HROfficeImpl.class, hrOffice.getPrimaryKey(), hrOffice);

		return hrOffice;
	}

	protected HROffice toUnwrappedModel(HROffice hrOffice) {
		if (hrOffice instanceof HROfficeImpl) {
			return hrOffice;
		}

		HROfficeImpl hrOfficeImpl = new HROfficeImpl();

		hrOfficeImpl.setNew(hrOffice.isNew());
		hrOfficeImpl.setPrimaryKey(hrOffice.getPrimaryKey());

		hrOfficeImpl.setHrOfficeId(hrOffice.getHrOfficeId());
		hrOfficeImpl.setGroupId(hrOffice.getGroupId());
		hrOfficeImpl.setCompanyId(hrOffice.getCompanyId());
		hrOfficeImpl.setUserId(hrOffice.getUserId());
		hrOfficeImpl.setUserName(hrOffice.getUserName());
		hrOfficeImpl.setCreateDate(hrOffice.getCreateDate());
		hrOfficeImpl.setModifiedDate(hrOffice.getModifiedDate());
		hrOfficeImpl.setOrganizationId(hrOffice.getOrganizationId());

		return hrOfficeImpl;
	}

	/**
	 * Returns the h r office with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r office
	 * @return the h r office
	 * @throws com.liferay.portal.NoSuchModelException if a h r office with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HROffice findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r office with the primary key or throws a {@link com.liferay.hr.NoSuchOfficeException} if it could not be found.
	 *
	 * @param hrOfficeId the primary key of the h r office
	 * @return the h r office
	 * @throws com.liferay.hr.NoSuchOfficeException if a h r office with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HROffice findByPrimaryKey(long hrOfficeId)
		throws NoSuchOfficeException, SystemException {
		HROffice hrOffice = fetchByPrimaryKey(hrOfficeId);

		if (hrOffice == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrOfficeId);
			}

			throw new NoSuchOfficeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrOfficeId);
		}

		return hrOffice;
	}

	/**
	 * Returns the h r office with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r office
	 * @return the h r office, or <code>null</code> if a h r office with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HROffice fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r office with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrOfficeId the primary key of the h r office
	 * @return the h r office, or <code>null</code> if a h r office with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HROffice fetchByPrimaryKey(long hrOfficeId)
		throws SystemException {
		HROffice hrOffice = (HROffice)EntityCacheUtil.getResult(HROfficeModelImpl.ENTITY_CACHE_ENABLED,
				HROfficeImpl.class, hrOfficeId, this);

		if (hrOffice == _nullHROffice) {
			return null;
		}

		if (hrOffice == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrOffice = (HROffice)session.get(HROfficeImpl.class,
						Long.valueOf(hrOfficeId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrOffice != null) {
					cacheResult(hrOffice);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HROfficeModelImpl.ENTITY_CACHE_ENABLED,
						HROfficeImpl.class, hrOfficeId, _nullHROffice);
				}

				closeSession(session);
			}
		}

		return hrOffice;
	}

	/**
	 * Returns all the h r offices.
	 *
	 * @return the h r offices
	 * @throws SystemException if a system exception occurred
	 */
	public List<HROffice> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r offices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r offices
	 * @param end the upper bound of the range of h r offices (not inclusive)
	 * @return the range of h r offices
	 * @throws SystemException if a system exception occurred
	 */
	public List<HROffice> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r offices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r offices
	 * @param end the upper bound of the range of h r offices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r offices
	 * @throws SystemException if a system exception occurred
	 */
	public List<HROffice> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HROffice> list = (List<HROffice>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HROFFICE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HROFFICE;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HROffice>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HROffice>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the h r offices from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HROffice hrOffice : findAll()) {
			hrOfficePersistence.remove(hrOffice);
		}
	}

	/**
	 * Returns the number of h r offices.
	 *
	 * @return the number of h r offices
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

				Query q = session.createQuery(_SQL_COUNT_HROFFICE);

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
	 * Returns all the h r holidaies associated with the h r office.
	 *
	 * @param pk the primary key of the h r office
	 * @return the h r holidaies associated with the h r office
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.hr.model.HRHoliday> getHRHolidaies(long pk)
		throws SystemException {
		return getHRHolidaies(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the h r holidaies associated with the h r office.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the h r office
	 * @param start the lower bound of the range of h r offices
	 * @param end the upper bound of the range of h r offices (not inclusive)
	 * @return the range of h r holidaies associated with the h r office
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.hr.model.HRHoliday> getHRHolidaies(long pk,
		int start, int end) throws SystemException {
		return getHRHolidaies(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_HRHOLIDAIES = new FinderPath(com.liferay.hr.model.impl.HRHolidayModelImpl.ENTITY_CACHE_ENABLED,
			HROfficeModelImpl.FINDER_CACHE_ENABLED_HRHOLIDAYS_HROFFICES,
			com.liferay.hr.model.impl.HRHolidayImpl.class,
			HROfficeModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME,
			"getHRHolidaies",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	/**
	 * Returns an ordered range of all the h r holidaies associated with the h r office.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the h r office
	 * @param start the lower bound of the range of h r offices
	 * @param end the upper bound of the range of h r offices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r holidaies associated with the h r office
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.hr.model.HRHoliday> getHRHolidaies(long pk,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				pk, String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<com.liferay.hr.model.HRHoliday> list = (List<com.liferay.hr.model.HRHoliday>)FinderCacheUtil.getResult(FINDER_PATH_GET_HRHOLIDAIES,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETHRHOLIDAIES.concat(ORDER_BY_CLAUSE)
											 .concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETHRHOLIDAIES;
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("HRHoliday",
					com.liferay.hr.model.impl.HRHolidayImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.liferay.hr.model.HRHoliday>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_HRHOLIDAIES,
						finderArgs);
				}
				else {
					hrHolidayPersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_HRHOLIDAIES,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_HRHOLIDAIES_SIZE = new FinderPath(com.liferay.hr.model.impl.HRHolidayModelImpl.ENTITY_CACHE_ENABLED,
			HROfficeModelImpl.FINDER_CACHE_ENABLED_HRHOLIDAYS_HROFFICES,
			Long.class,
			HROfficeModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME,
			"getHRHolidaiesSize", new String[] { Long.class.getName() });

	/**
	 * Returns the number of h r holidaies associated with the h r office.
	 *
	 * @param pk the primary key of the h r office
	 * @return the number of h r holidaies associated with the h r office
	 * @throws SystemException if a system exception occurred
	 */
	public int getHRHolidaiesSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_HRHOLIDAIES_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETHRHOLIDAIESSIZE);

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

				FinderCacheUtil.putResult(FINDER_PATH_GET_HRHOLIDAIES_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_HRHOLIDAY = new FinderPath(com.liferay.hr.model.impl.HRHolidayModelImpl.ENTITY_CACHE_ENABLED,
			HROfficeModelImpl.FINDER_CACHE_ENABLED_HRHOLIDAYS_HROFFICES,
			Boolean.class,
			HROfficeModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME,
			"containsHRHoliday",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the h r holiday is associated with the h r office.
	 *
	 * @param pk the primary key of the h r office
	 * @param hrHolidayPK the primary key of the h r holiday
	 * @return <code>true</code> if the h r holiday is associated with the h r office; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsHRHoliday(long pk, long hrHolidayPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, hrHolidayPK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_HRHOLIDAY,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsHRHoliday.contains(pk,
							hrHolidayPK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_HRHOLIDAY,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Returns <code>true</code> if the h r office has any h r holidaies associated with it.
	 *
	 * @param pk the primary key of the h r office to check for associations with h r holidaies
	 * @return <code>true</code> if the h r office has any h r holidaies associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsHRHolidaies(long pk) throws SystemException {
		if (getHRHolidaiesSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the h r office and the h r holiday. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r office
	 * @param hrHolidayPK the primary key of the h r holiday
	 * @throws SystemException if a system exception occurred
	 */
	public void addHRHoliday(long pk, long hrHolidayPK)
		throws SystemException {
		try {
			addHRHoliday.add(pk, hrHolidayPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HROfficeModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME);
		}
	}

	/**
	 * Adds an association between the h r office and the h r holiday. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r office
	 * @param hrHoliday the h r holiday
	 * @throws SystemException if a system exception occurred
	 */
	public void addHRHoliday(long pk, com.liferay.hr.model.HRHoliday hrHoliday)
		throws SystemException {
		try {
			addHRHoliday.add(pk, hrHoliday.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HROfficeModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME);
		}
	}

	/**
	 * Adds an association between the h r office and the h r holidaies. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r office
	 * @param hrHolidayPKs the primary keys of the h r holidaies
	 * @throws SystemException if a system exception occurred
	 */
	public void addHRHolidaies(long pk, long[] hrHolidayPKs)
		throws SystemException {
		try {
			for (long hrHolidayPK : hrHolidayPKs) {
				addHRHoliday.add(pk, hrHolidayPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HROfficeModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME);
		}
	}

	/**
	 * Adds an association between the h r office and the h r holidaies. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r office
	 * @param hrHolidaies the h r holidaies
	 * @throws SystemException if a system exception occurred
	 */
	public void addHRHolidaies(long pk,
		List<com.liferay.hr.model.HRHoliday> hrHolidaies)
		throws SystemException {
		try {
			for (com.liferay.hr.model.HRHoliday hrHoliday : hrHolidaies) {
				addHRHoliday.add(pk, hrHoliday.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HROfficeModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME);
		}
	}

	/**
	 * Clears all associations between the h r office and its h r holidaies. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r office to clear the associated h r holidaies from
	 * @throws SystemException if a system exception occurred
	 */
	public void clearHRHolidaies(long pk) throws SystemException {
		try {
			clearHRHolidaies.clear(pk);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HROfficeModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME);
		}
	}

	/**
	 * Removes the association between the h r office and the h r holiday. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r office
	 * @param hrHolidayPK the primary key of the h r holiday
	 * @throws SystemException if a system exception occurred
	 */
	public void removeHRHoliday(long pk, long hrHolidayPK)
		throws SystemException {
		try {
			removeHRHoliday.remove(pk, hrHolidayPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HROfficeModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME);
		}
	}

	/**
	 * Removes the association between the h r office and the h r holiday. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r office
	 * @param hrHoliday the h r holiday
	 * @throws SystemException if a system exception occurred
	 */
	public void removeHRHoliday(long pk,
		com.liferay.hr.model.HRHoliday hrHoliday) throws SystemException {
		try {
			removeHRHoliday.remove(pk, hrHoliday.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HROfficeModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME);
		}
	}

	/**
	 * Removes the association between the h r office and the h r holidaies. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r office
	 * @param hrHolidayPKs the primary keys of the h r holidaies
	 * @throws SystemException if a system exception occurred
	 */
	public void removeHRHolidaies(long pk, long[] hrHolidayPKs)
		throws SystemException {
		try {
			for (long hrHolidayPK : hrHolidayPKs) {
				removeHRHoliday.remove(pk, hrHolidayPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HROfficeModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME);
		}
	}

	/**
	 * Removes the association between the h r office and the h r holidaies. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r office
	 * @param hrHolidaies the h r holidaies
	 * @throws SystemException if a system exception occurred
	 */
	public void removeHRHolidaies(long pk,
		List<com.liferay.hr.model.HRHoliday> hrHolidaies)
		throws SystemException {
		try {
			for (com.liferay.hr.model.HRHoliday hrHoliday : hrHolidaies) {
				removeHRHoliday.remove(pk, hrHoliday.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HROfficeModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME);
		}
	}

	/**
	 * Sets the h r holidaies associated with the h r office, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r office
	 * @param hrHolidayPKs the primary keys of the h r holidaies to be associated with the h r office
	 * @throws SystemException if a system exception occurred
	 */
	public void setHRHolidaies(long pk, long[] hrHolidayPKs)
		throws SystemException {
		try {
			Set<Long> hrHolidayPKSet = SetUtil.fromArray(hrHolidayPKs);

			List<com.liferay.hr.model.HRHoliday> hrHolidaies = getHRHolidaies(pk);

			for (com.liferay.hr.model.HRHoliday hrHoliday : hrHolidaies) {
				if (!hrHolidayPKSet.remove(hrHoliday.getPrimaryKey())) {
					removeHRHoliday.remove(pk, hrHoliday.getPrimaryKey());
				}
			}

			for (Long hrHolidayPK : hrHolidayPKSet) {
				addHRHoliday.add(pk, hrHolidayPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HROfficeModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME);
		}
	}

	/**
	 * Sets the h r holidaies associated with the h r office, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r office
	 * @param hrHolidaies the h r holidaies to be associated with the h r office
	 * @throws SystemException if a system exception occurred
	 */
	public void setHRHolidaies(long pk,
		List<com.liferay.hr.model.HRHoliday> hrHolidaies)
		throws SystemException {
		try {
			long[] hrHolidayPKs = new long[hrHolidaies.size()];

			for (int i = 0; i < hrHolidaies.size(); i++) {
				com.liferay.hr.model.HRHoliday hrHoliday = hrHolidaies.get(i);

				hrHolidayPKs[i] = hrHoliday.getPrimaryKey();
			}

			setHRHolidaies(pk, hrHolidayPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HROfficeModelImpl.MAPPING_TABLE_HRHOLIDAYS_HROFFICES_NAME);
		}
	}

	/**
	 * Initializes the h r office persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HROffice")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HROffice>> listenersList = new ArrayList<ModelListener<HROffice>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HROffice>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsHRHoliday = new ContainsHRHoliday(this);

		addHRHoliday = new AddHRHoliday(this);
		clearHRHolidaies = new ClearHRHolidaies(this);
		removeHRHoliday = new RemoveHRHoliday(this);
	}

	public void destroy() {
		EntityCacheUtil.removeCache(HROfficeImpl.class.getName());
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
	protected ContainsHRHoliday containsHRHoliday;
	protected AddHRHoliday addHRHoliday;
	protected ClearHRHolidaies clearHRHolidaies;
	protected RemoveHRHoliday removeHRHoliday;

	protected class ContainsHRHoliday {
		protected ContainsHRHoliday(HROfficePersistenceImpl persistenceImpl) {
			super();

			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSHRHOLIDAY,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long hrOfficeId, long hrHolidayId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(hrOfficeId), new Long(hrHolidayId)
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

	protected class AddHRHoliday {
		protected AddHRHoliday(HROfficePersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO HRHolidays_HROffices (hrOfficeId, hrHolidayId) VALUES (?, ?)",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
			_persistenceImpl = persistenceImpl;
		}

		protected void add(long hrOfficeId, long hrHolidayId)
			throws SystemException {
			if (!_persistenceImpl.containsHRHoliday.contains(hrOfficeId,
						hrHolidayId)) {
				ModelListener<com.liferay.hr.model.HRHoliday>[] hrHolidayListeners =
					hrHolidayPersistence.getListeners();

				for (ModelListener<HROffice> listener : listeners) {
					listener.onBeforeAddAssociation(hrOfficeId,
						com.liferay.hr.model.HRHoliday.class.getName(),
						hrHolidayId);
				}

				for (ModelListener<com.liferay.hr.model.HRHoliday> listener : hrHolidayListeners) {
					listener.onBeforeAddAssociation(hrHolidayId,
						HROffice.class.getName(), hrOfficeId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(hrOfficeId), new Long(hrHolidayId)
					});

				for (ModelListener<HROffice> listener : listeners) {
					listener.onAfterAddAssociation(hrOfficeId,
						com.liferay.hr.model.HRHoliday.class.getName(),
						hrHolidayId);
				}

				for (ModelListener<com.liferay.hr.model.HRHoliday> listener : hrHolidayListeners) {
					listener.onAfterAddAssociation(hrHolidayId,
						HROffice.class.getName(), hrOfficeId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
		private HROfficePersistenceImpl _persistenceImpl;
	}

	protected class ClearHRHolidaies {
		protected ClearHRHolidaies(HROfficePersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM HRHolidays_HROffices WHERE hrOfficeId = ?",
					new int[] { java.sql.Types.BIGINT });
		}

		protected void clear(long hrOfficeId) throws SystemException {
			ModelListener<com.liferay.hr.model.HRHoliday>[] hrHolidayListeners = hrHolidayPersistence.getListeners();

			List<com.liferay.hr.model.HRHoliday> hrHolidaies = null;

			if ((listeners.length > 0) || (hrHolidayListeners.length > 0)) {
				hrHolidaies = getHRHolidaies(hrOfficeId);

				for (com.liferay.hr.model.HRHoliday hrHoliday : hrHolidaies) {
					for (ModelListener<HROffice> listener : listeners) {
						listener.onBeforeRemoveAssociation(hrOfficeId,
							com.liferay.hr.model.HRHoliday.class.getName(),
							hrHoliday.getPrimaryKey());
					}

					for (ModelListener<com.liferay.hr.model.HRHoliday> listener : hrHolidayListeners) {
						listener.onBeforeRemoveAssociation(hrHoliday.getPrimaryKey(),
							HROffice.class.getName(), hrOfficeId);
					}
				}
			}

			_sqlUpdate.update(new Object[] { new Long(hrOfficeId) });

			if ((listeners.length > 0) || (hrHolidayListeners.length > 0)) {
				for (com.liferay.hr.model.HRHoliday hrHoliday : hrHolidaies) {
					for (ModelListener<HROffice> listener : listeners) {
						listener.onAfterRemoveAssociation(hrOfficeId,
							com.liferay.hr.model.HRHoliday.class.getName(),
							hrHoliday.getPrimaryKey());
					}

					for (ModelListener<com.liferay.hr.model.HRHoliday> listener : hrHolidayListeners) {
						listener.onAfterRemoveAssociation(hrHoliday.getPrimaryKey(),
							HROffice.class.getName(), hrOfficeId);
					}
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemoveHRHoliday {
		protected RemoveHRHoliday(HROfficePersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM HRHolidays_HROffices WHERE hrOfficeId = ? AND hrHolidayId = ?",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
			_persistenceImpl = persistenceImpl;
		}

		protected void remove(long hrOfficeId, long hrHolidayId)
			throws SystemException {
			if (_persistenceImpl.containsHRHoliday.contains(hrOfficeId,
						hrHolidayId)) {
				ModelListener<com.liferay.hr.model.HRHoliday>[] hrHolidayListeners =
					hrHolidayPersistence.getListeners();

				for (ModelListener<HROffice> listener : listeners) {
					listener.onBeforeRemoveAssociation(hrOfficeId,
						com.liferay.hr.model.HRHoliday.class.getName(),
						hrHolidayId);
				}

				for (ModelListener<com.liferay.hr.model.HRHoliday> listener : hrHolidayListeners) {
					listener.onBeforeRemoveAssociation(hrHolidayId,
						HROffice.class.getName(), hrOfficeId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(hrOfficeId), new Long(hrHolidayId)
					});

				for (ModelListener<HROffice> listener : listeners) {
					listener.onAfterRemoveAssociation(hrOfficeId,
						com.liferay.hr.model.HRHoliday.class.getName(),
						hrHolidayId);
				}

				for (ModelListener<com.liferay.hr.model.HRHoliday> listener : hrHolidayListeners) {
					listener.onAfterRemoveAssociation(hrHolidayId,
						HROffice.class.getName(), hrOfficeId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
		private HROfficePersistenceImpl _persistenceImpl;
	}

	private static final String _SQL_SELECT_HROFFICE = "SELECT hrOffice FROM HROffice hrOffice";
	private static final String _SQL_COUNT_HROFFICE = "SELECT COUNT(hrOffice) FROM HROffice hrOffice";
	private static final String _SQL_GETHRHOLIDAIES = "SELECT {HRHoliday.*} FROM HRHoliday INNER JOIN HRHolidays_HROffices ON (HRHolidays_HROffices.hrHolidayId = HRHoliday.hrHolidayId) WHERE (HRHolidays_HROffices.hrOfficeId = ?)";
	private static final String _SQL_GETHRHOLIDAIESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM HRHolidays_HROffices WHERE hrOfficeId = ?";
	private static final String _SQL_CONTAINSHRHOLIDAY = "SELECT COUNT(*) AS COUNT_VALUE FROM HRHolidays_HROffices WHERE hrOfficeId = ? AND hrHolidayId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrOffice.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HROffice exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HROfficePersistenceImpl.class);
	private static HROffice _nullHROffice = new HROfficeImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HROffice> toCacheModel() {
				return _nullHROfficeCacheModel;
			}
		};

	private static CacheModel<HROffice> _nullHROfficeCacheModel = new CacheModel<HROffice>() {
			public HROffice toEntityModel() {
				return _nullHROffice;
			}
		};
}