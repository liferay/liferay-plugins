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

import com.liferay.hr.NoSuchTimeSheetDayException;
import com.liferay.hr.model.HRTimeSheetDay;
import com.liferay.hr.model.impl.HRTimeSheetDayImpl;
import com.liferay.hr.model.impl.HRTimeSheetDayModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
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

/**
 * The persistence implementation for the h r time sheet day service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRTimeSheetDayPersistence
 * @see HRTimeSheetDayUtil
 * @generated
 */
public class HRTimeSheetDayPersistenceImpl extends BasePersistenceImpl<HRTimeSheetDay>
	implements HRTimeSheetDayPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRTimeSheetDayUtil} to access the h r time sheet day persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRTimeSheetDayImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRTimeSheetDayModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeSheetDayModelImpl.FINDER_CACHE_ENABLED,
			HRTimeSheetDayImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRTimeSheetDayModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeSheetDayModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r time sheet day in the entity cache if it is enabled.
	 *
	 * @param hrTimeSheetDay the h r time sheet day
	 */
	public void cacheResult(HRTimeSheetDay hrTimeSheetDay) {
		EntityCacheUtil.putResult(HRTimeSheetDayModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeSheetDayImpl.class, hrTimeSheetDay.getPrimaryKey(),
			hrTimeSheetDay);

		hrTimeSheetDay.resetOriginalValues();
	}

	/**
	 * Caches the h r time sheet daies in the entity cache if it is enabled.
	 *
	 * @param hrTimeSheetDaies the h r time sheet daies
	 */
	public void cacheResult(List<HRTimeSheetDay> hrTimeSheetDaies) {
		for (HRTimeSheetDay hrTimeSheetDay : hrTimeSheetDaies) {
			if (EntityCacheUtil.getResult(
						HRTimeSheetDayModelImpl.ENTITY_CACHE_ENABLED,
						HRTimeSheetDayImpl.class,
						hrTimeSheetDay.getPrimaryKey(), this) == null) {
				cacheResult(hrTimeSheetDay);
			}
		}
	}

	/**
	 * Clears the cache for all h r time sheet daies.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRTimeSheetDayImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRTimeSheetDayImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r time sheet day.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRTimeSheetDay hrTimeSheetDay) {
		EntityCacheUtil.removeResult(HRTimeSheetDayModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeSheetDayImpl.class, hrTimeSheetDay.getPrimaryKey());
	}

	/**
	 * Creates a new h r time sheet day with the primary key. Does not add the h r time sheet day to the database.
	 *
	 * @param hrTimeSheetDayId the primary key for the new h r time sheet day
	 * @return the new h r time sheet day
	 */
	public HRTimeSheetDay create(long hrTimeSheetDayId) {
		HRTimeSheetDay hrTimeSheetDay = new HRTimeSheetDayImpl();

		hrTimeSheetDay.setNew(true);
		hrTimeSheetDay.setPrimaryKey(hrTimeSheetDayId);

		return hrTimeSheetDay;
	}

	/**
	 * Removes the h r time sheet day with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r time sheet day
	 * @return the h r time sheet day that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r time sheet day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTimeSheetDay remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r time sheet day with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrTimeSheetDayId the primary key of the h r time sheet day
	 * @return the h r time sheet day that was removed
	 * @throws com.liferay.hr.NoSuchTimeSheetDayException if a h r time sheet day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTimeSheetDay remove(long hrTimeSheetDayId)
		throws NoSuchTimeSheetDayException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRTimeSheetDay hrTimeSheetDay = (HRTimeSheetDay)session.get(HRTimeSheetDayImpl.class,
					Long.valueOf(hrTimeSheetDayId));

			if (hrTimeSheetDay == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						hrTimeSheetDayId);
				}

				throw new NoSuchTimeSheetDayException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrTimeSheetDayId);
			}

			return hrTimeSheetDayPersistence.remove(hrTimeSheetDay);
		}
		catch (NoSuchTimeSheetDayException nsee) {
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
	 * Removes the h r time sheet day from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrTimeSheetDay the h r time sheet day
	 * @return the h r time sheet day that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTimeSheetDay remove(HRTimeSheetDay hrTimeSheetDay)
		throws SystemException {
		return super.remove(hrTimeSheetDay);
	}

	@Override
	protected HRTimeSheetDay removeImpl(HRTimeSheetDay hrTimeSheetDay)
		throws SystemException {
		hrTimeSheetDay = toUnwrappedModel(hrTimeSheetDay);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrTimeSheetDay);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRTimeSheetDayModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeSheetDayImpl.class, hrTimeSheetDay.getPrimaryKey());

		return hrTimeSheetDay;
	}

	@Override
	public HRTimeSheetDay updateImpl(
		com.liferay.hr.model.HRTimeSheetDay hrTimeSheetDay, boolean merge)
		throws SystemException {
		hrTimeSheetDay = toUnwrappedModel(hrTimeSheetDay);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrTimeSheetDay, merge);

			hrTimeSheetDay.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRTimeSheetDayModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeSheetDayImpl.class, hrTimeSheetDay.getPrimaryKey(),
			hrTimeSheetDay);

		return hrTimeSheetDay;
	}

	protected HRTimeSheetDay toUnwrappedModel(HRTimeSheetDay hrTimeSheetDay) {
		if (hrTimeSheetDay instanceof HRTimeSheetDayImpl) {
			return hrTimeSheetDay;
		}

		HRTimeSheetDayImpl hrTimeSheetDayImpl = new HRTimeSheetDayImpl();

		hrTimeSheetDayImpl.setNew(hrTimeSheetDay.isNew());
		hrTimeSheetDayImpl.setPrimaryKey(hrTimeSheetDay.getPrimaryKey());

		hrTimeSheetDayImpl.setHrTimeSheetDayId(hrTimeSheetDay.getHrTimeSheetDayId());
		hrTimeSheetDayImpl.setGroupId(hrTimeSheetDay.getGroupId());
		hrTimeSheetDayImpl.setCompanyId(hrTimeSheetDay.getCompanyId());
		hrTimeSheetDayImpl.setUserId(hrTimeSheetDay.getUserId());
		hrTimeSheetDayImpl.setUserName(hrTimeSheetDay.getUserName());
		hrTimeSheetDayImpl.setCreateDate(hrTimeSheetDay.getCreateDate());
		hrTimeSheetDayImpl.setModifiedDate(hrTimeSheetDay.getModifiedDate());
		hrTimeSheetDayImpl.setHrTimeSheetId(hrTimeSheetDay.getHrTimeSheetId());
		hrTimeSheetDayImpl.setHrUserId(hrTimeSheetDay.getHrUserId());
		hrTimeSheetDayImpl.setDayOfYear(hrTimeSheetDay.getDayOfYear());
		hrTimeSheetDayImpl.setYear(hrTimeSheetDay.getYear());
		hrTimeSheetDayImpl.setHours(hrTimeSheetDay.getHours());

		return hrTimeSheetDayImpl;
	}

	/**
	 * Returns the h r time sheet day with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r time sheet day
	 * @return the h r time sheet day
	 * @throws com.liferay.portal.NoSuchModelException if a h r time sheet day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTimeSheetDay findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r time sheet day with the primary key or throws a {@link com.liferay.hr.NoSuchTimeSheetDayException} if it could not be found.
	 *
	 * @param hrTimeSheetDayId the primary key of the h r time sheet day
	 * @return the h r time sheet day
	 * @throws com.liferay.hr.NoSuchTimeSheetDayException if a h r time sheet day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTimeSheetDay findByPrimaryKey(long hrTimeSheetDayId)
		throws NoSuchTimeSheetDayException, SystemException {
		HRTimeSheetDay hrTimeSheetDay = fetchByPrimaryKey(hrTimeSheetDayId);

		if (hrTimeSheetDay == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrTimeSheetDayId);
			}

			throw new NoSuchTimeSheetDayException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrTimeSheetDayId);
		}

		return hrTimeSheetDay;
	}

	/**
	 * Returns the h r time sheet day with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r time sheet day
	 * @return the h r time sheet day, or <code>null</code> if a h r time sheet day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTimeSheetDay fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r time sheet day with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrTimeSheetDayId the primary key of the h r time sheet day
	 * @return the h r time sheet day, or <code>null</code> if a h r time sheet day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTimeSheetDay fetchByPrimaryKey(long hrTimeSheetDayId)
		throws SystemException {
		HRTimeSheetDay hrTimeSheetDay = (HRTimeSheetDay)EntityCacheUtil.getResult(HRTimeSheetDayModelImpl.ENTITY_CACHE_ENABLED,
				HRTimeSheetDayImpl.class, hrTimeSheetDayId, this);

		if (hrTimeSheetDay == _nullHRTimeSheetDay) {
			return null;
		}

		if (hrTimeSheetDay == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrTimeSheetDay = (HRTimeSheetDay)session.get(HRTimeSheetDayImpl.class,
						Long.valueOf(hrTimeSheetDayId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrTimeSheetDay != null) {
					cacheResult(hrTimeSheetDay);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRTimeSheetDayModelImpl.ENTITY_CACHE_ENABLED,
						HRTimeSheetDayImpl.class, hrTimeSheetDayId,
						_nullHRTimeSheetDay);
				}

				closeSession(session);
			}
		}

		return hrTimeSheetDay;
	}

	/**
	 * Returns all the h r time sheet daies.
	 *
	 * @return the h r time sheet daies
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRTimeSheetDay> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r time sheet daies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r time sheet daies
	 * @param end the upper bound of the range of h r time sheet daies (not inclusive)
	 * @return the range of h r time sheet daies
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRTimeSheetDay> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r time sheet daies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r time sheet daies
	 * @param end the upper bound of the range of h r time sheet daies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r time sheet daies
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRTimeSheetDay> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRTimeSheetDay> list = (List<HRTimeSheetDay>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRTIMESHEETDAY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRTIMESHEETDAY;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRTimeSheetDay>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRTimeSheetDay>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the h r time sheet daies from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRTimeSheetDay hrTimeSheetDay : findAll()) {
			hrTimeSheetDayPersistence.remove(hrTimeSheetDay);
		}
	}

	/**
	 * Returns the number of h r time sheet daies.
	 *
	 * @return the number of h r time sheet daies
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

				Query q = session.createQuery(_SQL_COUNT_HRTIMESHEETDAY);

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
	 * Initializes the h r time sheet day persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRTimeSheetDay")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRTimeSheetDay>> listenersList = new ArrayList<ModelListener<HRTimeSheetDay>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRTimeSheetDay>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRTimeSheetDayImpl.class.getName());
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
	private static final String _SQL_SELECT_HRTIMESHEETDAY = "SELECT hrTimeSheetDay FROM HRTimeSheetDay hrTimeSheetDay";
	private static final String _SQL_COUNT_HRTIMESHEETDAY = "SELECT COUNT(hrTimeSheetDay) FROM HRTimeSheetDay hrTimeSheetDay";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrTimeSheetDay.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRTimeSheetDay exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRTimeSheetDayPersistenceImpl.class);
	private static HRTimeSheetDay _nullHRTimeSheetDay = new HRTimeSheetDayImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRTimeSheetDay> toCacheModel() {
				return _nullHRTimeSheetDayCacheModel;
			}
		};

	private static CacheModel<HRTimeSheetDay> _nullHRTimeSheetDayCacheModel = new CacheModel<HRTimeSheetDay>() {
			public HRTimeSheetDay toEntityModel() {
				return _nullHRTimeSheetDay;
			}
		};
}