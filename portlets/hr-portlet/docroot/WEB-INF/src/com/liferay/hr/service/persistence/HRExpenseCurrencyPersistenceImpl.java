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

import com.liferay.hr.NoSuchExpenseCurrencyException;
import com.liferay.hr.model.HRExpenseCurrency;
import com.liferay.hr.model.impl.HRExpenseCurrencyImpl;
import com.liferay.hr.model.impl.HRExpenseCurrencyModelImpl;

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
 * The persistence implementation for the h r expense currency service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRExpenseCurrencyPersistence
 * @see HRExpenseCurrencyUtil
 * @generated
 */
public class HRExpenseCurrencyPersistenceImpl extends BasePersistenceImpl<HRExpenseCurrency>
	implements HRExpenseCurrencyPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRExpenseCurrencyUtil} to access the h r expense currency persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRExpenseCurrencyImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRExpenseCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseCurrencyModelImpl.FINDER_CACHE_ENABLED,
			HRExpenseCurrencyImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRExpenseCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseCurrencyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r expense currency in the entity cache if it is enabled.
	 *
	 * @param hrExpenseCurrency the h r expense currency
	 */
	public void cacheResult(HRExpenseCurrency hrExpenseCurrency) {
		EntityCacheUtil.putResult(HRExpenseCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseCurrencyImpl.class, hrExpenseCurrency.getPrimaryKey(),
			hrExpenseCurrency);

		hrExpenseCurrency.resetOriginalValues();
	}

	/**
	 * Caches the h r expense currencies in the entity cache if it is enabled.
	 *
	 * @param hrExpenseCurrencies the h r expense currencies
	 */
	public void cacheResult(List<HRExpenseCurrency> hrExpenseCurrencies) {
		for (HRExpenseCurrency hrExpenseCurrency : hrExpenseCurrencies) {
			if (EntityCacheUtil.getResult(
						HRExpenseCurrencyModelImpl.ENTITY_CACHE_ENABLED,
						HRExpenseCurrencyImpl.class,
						hrExpenseCurrency.getPrimaryKey(), this) == null) {
				cacheResult(hrExpenseCurrency);
			}
		}
	}

	/**
	 * Clears the cache for all h r expense currencies.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRExpenseCurrencyImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRExpenseCurrencyImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r expense currency.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRExpenseCurrency hrExpenseCurrency) {
		EntityCacheUtil.removeResult(HRExpenseCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseCurrencyImpl.class, hrExpenseCurrency.getPrimaryKey());
	}

	/**
	 * Creates a new h r expense currency with the primary key. Does not add the h r expense currency to the database.
	 *
	 * @param hrExpenseCurrencyId the primary key for the new h r expense currency
	 * @return the new h r expense currency
	 */
	public HRExpenseCurrency create(long hrExpenseCurrencyId) {
		HRExpenseCurrency hrExpenseCurrency = new HRExpenseCurrencyImpl();

		hrExpenseCurrency.setNew(true);
		hrExpenseCurrency.setPrimaryKey(hrExpenseCurrencyId);

		return hrExpenseCurrency;
	}

	/**
	 * Removes the h r expense currency with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r expense currency
	 * @return the h r expense currency that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r expense currency with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRExpenseCurrency remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r expense currency with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrExpenseCurrencyId the primary key of the h r expense currency
	 * @return the h r expense currency that was removed
	 * @throws com.liferay.hr.NoSuchExpenseCurrencyException if a h r expense currency with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpenseCurrency remove(long hrExpenseCurrencyId)
		throws NoSuchExpenseCurrencyException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRExpenseCurrency hrExpenseCurrency = (HRExpenseCurrency)session.get(HRExpenseCurrencyImpl.class,
					Long.valueOf(hrExpenseCurrencyId));

			if (hrExpenseCurrency == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						hrExpenseCurrencyId);
				}

				throw new NoSuchExpenseCurrencyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrExpenseCurrencyId);
			}

			return hrExpenseCurrencyPersistence.remove(hrExpenseCurrency);
		}
		catch (NoSuchExpenseCurrencyException nsee) {
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
	 * Removes the h r expense currency from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrExpenseCurrency the h r expense currency
	 * @return the h r expense currency that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRExpenseCurrency remove(HRExpenseCurrency hrExpenseCurrency)
		throws SystemException {
		return super.remove(hrExpenseCurrency);
	}

	@Override
	protected HRExpenseCurrency removeImpl(HRExpenseCurrency hrExpenseCurrency)
		throws SystemException {
		hrExpenseCurrency = toUnwrappedModel(hrExpenseCurrency);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrExpenseCurrency);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRExpenseCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseCurrencyImpl.class, hrExpenseCurrency.getPrimaryKey());

		return hrExpenseCurrency;
	}

	@Override
	public HRExpenseCurrency updateImpl(
		com.liferay.hr.model.HRExpenseCurrency hrExpenseCurrency, boolean merge)
		throws SystemException {
		hrExpenseCurrency = toUnwrappedModel(hrExpenseCurrency);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrExpenseCurrency, merge);

			hrExpenseCurrency.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRExpenseCurrencyModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseCurrencyImpl.class, hrExpenseCurrency.getPrimaryKey(),
			hrExpenseCurrency);

		return hrExpenseCurrency;
	}

	protected HRExpenseCurrency toUnwrappedModel(
		HRExpenseCurrency hrExpenseCurrency) {
		if (hrExpenseCurrency instanceof HRExpenseCurrencyImpl) {
			return hrExpenseCurrency;
		}

		HRExpenseCurrencyImpl hrExpenseCurrencyImpl = new HRExpenseCurrencyImpl();

		hrExpenseCurrencyImpl.setNew(hrExpenseCurrency.isNew());
		hrExpenseCurrencyImpl.setPrimaryKey(hrExpenseCurrency.getPrimaryKey());

		hrExpenseCurrencyImpl.setHrExpenseCurrencyId(hrExpenseCurrency.getHrExpenseCurrencyId());
		hrExpenseCurrencyImpl.setGroupId(hrExpenseCurrency.getGroupId());
		hrExpenseCurrencyImpl.setCompanyId(hrExpenseCurrency.getCompanyId());
		hrExpenseCurrencyImpl.setUserId(hrExpenseCurrency.getUserId());
		hrExpenseCurrencyImpl.setUserName(hrExpenseCurrency.getUserName());
		hrExpenseCurrencyImpl.setCreateDate(hrExpenseCurrency.getCreateDate());
		hrExpenseCurrencyImpl.setModifiedDate(hrExpenseCurrency.getModifiedDate());
		hrExpenseCurrencyImpl.setCode(hrExpenseCurrency.getCode());
		hrExpenseCurrencyImpl.setName(hrExpenseCurrency.getName());
		hrExpenseCurrencyImpl.setDescription(hrExpenseCurrency.getDescription());

		return hrExpenseCurrencyImpl;
	}

	/**
	 * Returns the h r expense currency with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r expense currency
	 * @return the h r expense currency
	 * @throws com.liferay.portal.NoSuchModelException if a h r expense currency with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRExpenseCurrency findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r expense currency with the primary key or throws a {@link com.liferay.hr.NoSuchExpenseCurrencyException} if it could not be found.
	 *
	 * @param hrExpenseCurrencyId the primary key of the h r expense currency
	 * @return the h r expense currency
	 * @throws com.liferay.hr.NoSuchExpenseCurrencyException if a h r expense currency with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpenseCurrency findByPrimaryKey(long hrExpenseCurrencyId)
		throws NoSuchExpenseCurrencyException, SystemException {
		HRExpenseCurrency hrExpenseCurrency = fetchByPrimaryKey(hrExpenseCurrencyId);

		if (hrExpenseCurrency == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrExpenseCurrencyId);
			}

			throw new NoSuchExpenseCurrencyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrExpenseCurrencyId);
		}

		return hrExpenseCurrency;
	}

	/**
	 * Returns the h r expense currency with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r expense currency
	 * @return the h r expense currency, or <code>null</code> if a h r expense currency with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRExpenseCurrency fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r expense currency with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrExpenseCurrencyId the primary key of the h r expense currency
	 * @return the h r expense currency, or <code>null</code> if a h r expense currency with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpenseCurrency fetchByPrimaryKey(long hrExpenseCurrencyId)
		throws SystemException {
		HRExpenseCurrency hrExpenseCurrency = (HRExpenseCurrency)EntityCacheUtil.getResult(HRExpenseCurrencyModelImpl.ENTITY_CACHE_ENABLED,
				HRExpenseCurrencyImpl.class, hrExpenseCurrencyId, this);

		if (hrExpenseCurrency == _nullHRExpenseCurrency) {
			return null;
		}

		if (hrExpenseCurrency == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrExpenseCurrency = (HRExpenseCurrency)session.get(HRExpenseCurrencyImpl.class,
						Long.valueOf(hrExpenseCurrencyId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrExpenseCurrency != null) {
					cacheResult(hrExpenseCurrency);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRExpenseCurrencyModelImpl.ENTITY_CACHE_ENABLED,
						HRExpenseCurrencyImpl.class, hrExpenseCurrencyId,
						_nullHRExpenseCurrency);
				}

				closeSession(session);
			}
		}

		return hrExpenseCurrency;
	}

	/**
	 * Returns all the h r expense currencies.
	 *
	 * @return the h r expense currencies
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRExpenseCurrency> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r expense currencies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r expense currencies
	 * @param end the upper bound of the range of h r expense currencies (not inclusive)
	 * @return the range of h r expense currencies
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRExpenseCurrency> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r expense currencies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r expense currencies
	 * @param end the upper bound of the range of h r expense currencies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r expense currencies
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRExpenseCurrency> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRExpenseCurrency> list = (List<HRExpenseCurrency>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HREXPENSECURRENCY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HREXPENSECURRENCY;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRExpenseCurrency>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRExpenseCurrency>)QueryUtil.list(q,
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
	 * Removes all the h r expense currencies from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRExpenseCurrency hrExpenseCurrency : findAll()) {
			hrExpenseCurrencyPersistence.remove(hrExpenseCurrency);
		}
	}

	/**
	 * Returns the number of h r expense currencies.
	 *
	 * @return the number of h r expense currencies
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

				Query q = session.createQuery(_SQL_COUNT_HREXPENSECURRENCY);

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
	 * Initializes the h r expense currency persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRExpenseCurrency")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRExpenseCurrency>> listenersList = new ArrayList<ModelListener<HRExpenseCurrency>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRExpenseCurrency>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRExpenseCurrencyImpl.class.getName());
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
	private static final String _SQL_SELECT_HREXPENSECURRENCY = "SELECT hrExpenseCurrency FROM HRExpenseCurrency hrExpenseCurrency";
	private static final String _SQL_COUNT_HREXPENSECURRENCY = "SELECT COUNT(hrExpenseCurrency) FROM HRExpenseCurrency hrExpenseCurrency";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrExpenseCurrency.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRExpenseCurrency exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRExpenseCurrencyPersistenceImpl.class);
	private static HRExpenseCurrency _nullHRExpenseCurrency = new HRExpenseCurrencyImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRExpenseCurrency> toCacheModel() {
				return _nullHRExpenseCurrencyCacheModel;
			}
		};

	private static CacheModel<HRExpenseCurrency> _nullHRExpenseCurrencyCacheModel =
		new CacheModel<HRExpenseCurrency>() {
			public HRExpenseCurrency toEntityModel() {
				return _nullHRExpenseCurrency;
			}
		};
}