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

import com.liferay.hr.NoSuchProjectBillingRateException;
import com.liferay.hr.model.HRProjectBillingRate;
import com.liferay.hr.model.impl.HRProjectBillingRateImpl;
import com.liferay.hr.model.impl.HRProjectBillingRateModelImpl;

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
 * The persistence implementation for the h r project billing rate service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRProjectBillingRatePersistence
 * @see HRProjectBillingRateUtil
 * @generated
 */
public class HRProjectBillingRatePersistenceImpl extends BasePersistenceImpl<HRProjectBillingRate>
	implements HRProjectBillingRatePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRProjectBillingRateUtil} to access the h r project billing rate persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRProjectBillingRateImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRProjectBillingRateModelImpl.ENTITY_CACHE_ENABLED,
			HRProjectBillingRateModelImpl.FINDER_CACHE_ENABLED,
			HRProjectBillingRateImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRProjectBillingRateModelImpl.ENTITY_CACHE_ENABLED,
			HRProjectBillingRateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r project billing rate in the entity cache if it is enabled.
	 *
	 * @param hrProjectBillingRate the h r project billing rate
	 */
	public void cacheResult(HRProjectBillingRate hrProjectBillingRate) {
		EntityCacheUtil.putResult(HRProjectBillingRateModelImpl.ENTITY_CACHE_ENABLED,
			HRProjectBillingRateImpl.class,
			hrProjectBillingRate.getPrimaryKey(), hrProjectBillingRate);

		hrProjectBillingRate.resetOriginalValues();
	}

	/**
	 * Caches the h r project billing rates in the entity cache if it is enabled.
	 *
	 * @param hrProjectBillingRates the h r project billing rates
	 */
	public void cacheResult(List<HRProjectBillingRate> hrProjectBillingRates) {
		for (HRProjectBillingRate hrProjectBillingRate : hrProjectBillingRates) {
			if (EntityCacheUtil.getResult(
						HRProjectBillingRateModelImpl.ENTITY_CACHE_ENABLED,
						HRProjectBillingRateImpl.class,
						hrProjectBillingRate.getPrimaryKey(), this) == null) {
				cacheResult(hrProjectBillingRate);
			}
		}
	}

	/**
	 * Clears the cache for all h r project billing rates.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRProjectBillingRateImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRProjectBillingRateImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r project billing rate.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRProjectBillingRate hrProjectBillingRate) {
		EntityCacheUtil.removeResult(HRProjectBillingRateModelImpl.ENTITY_CACHE_ENABLED,
			HRProjectBillingRateImpl.class, hrProjectBillingRate.getPrimaryKey());
	}

	/**
	 * Creates a new h r project billing rate with the primary key. Does not add the h r project billing rate to the database.
	 *
	 * @param hrProjectBillingRateId the primary key for the new h r project billing rate
	 * @return the new h r project billing rate
	 */
	public HRProjectBillingRate create(long hrProjectBillingRateId) {
		HRProjectBillingRate hrProjectBillingRate = new HRProjectBillingRateImpl();

		hrProjectBillingRate.setNew(true);
		hrProjectBillingRate.setPrimaryKey(hrProjectBillingRateId);

		return hrProjectBillingRate;
	}

	/**
	 * Removes the h r project billing rate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r project billing rate
	 * @return the h r project billing rate that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r project billing rate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRProjectBillingRate remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r project billing rate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrProjectBillingRateId the primary key of the h r project billing rate
	 * @return the h r project billing rate that was removed
	 * @throws com.liferay.hr.NoSuchProjectBillingRateException if a h r project billing rate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRProjectBillingRate remove(long hrProjectBillingRateId)
		throws NoSuchProjectBillingRateException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRProjectBillingRate hrProjectBillingRate = (HRProjectBillingRate)session.get(HRProjectBillingRateImpl.class,
					Long.valueOf(hrProjectBillingRateId));

			if (hrProjectBillingRate == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						hrProjectBillingRateId);
				}

				throw new NoSuchProjectBillingRateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrProjectBillingRateId);
			}

			return hrProjectBillingRatePersistence.remove(hrProjectBillingRate);
		}
		catch (NoSuchProjectBillingRateException nsee) {
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
	 * Removes the h r project billing rate from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrProjectBillingRate the h r project billing rate
	 * @return the h r project billing rate that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRProjectBillingRate remove(
		HRProjectBillingRate hrProjectBillingRate) throws SystemException {
		return super.remove(hrProjectBillingRate);
	}

	@Override
	protected HRProjectBillingRate removeImpl(
		HRProjectBillingRate hrProjectBillingRate) throws SystemException {
		hrProjectBillingRate = toUnwrappedModel(hrProjectBillingRate);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrProjectBillingRate);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRProjectBillingRateModelImpl.ENTITY_CACHE_ENABLED,
			HRProjectBillingRateImpl.class, hrProjectBillingRate.getPrimaryKey());

		return hrProjectBillingRate;
	}

	@Override
	public HRProjectBillingRate updateImpl(
		com.liferay.hr.model.HRProjectBillingRate hrProjectBillingRate,
		boolean merge) throws SystemException {
		hrProjectBillingRate = toUnwrappedModel(hrProjectBillingRate);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrProjectBillingRate, merge);

			hrProjectBillingRate.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRProjectBillingRateModelImpl.ENTITY_CACHE_ENABLED,
			HRProjectBillingRateImpl.class,
			hrProjectBillingRate.getPrimaryKey(), hrProjectBillingRate);

		return hrProjectBillingRate;
	}

	protected HRProjectBillingRate toUnwrappedModel(
		HRProjectBillingRate hrProjectBillingRate) {
		if (hrProjectBillingRate instanceof HRProjectBillingRateImpl) {
			return hrProjectBillingRate;
		}

		HRProjectBillingRateImpl hrProjectBillingRateImpl = new HRProjectBillingRateImpl();

		hrProjectBillingRateImpl.setNew(hrProjectBillingRate.isNew());
		hrProjectBillingRateImpl.setPrimaryKey(hrProjectBillingRate.getPrimaryKey());

		hrProjectBillingRateImpl.setHrProjectBillingRateId(hrProjectBillingRate.getHrProjectBillingRateId());
		hrProjectBillingRateImpl.setGroupId(hrProjectBillingRate.getGroupId());
		hrProjectBillingRateImpl.setCompanyId(hrProjectBillingRate.getCompanyId());
		hrProjectBillingRateImpl.setUserId(hrProjectBillingRate.getUserId());
		hrProjectBillingRateImpl.setUserName(hrProjectBillingRate.getUserName());
		hrProjectBillingRateImpl.setCreateDate(hrProjectBillingRate.getCreateDate());
		hrProjectBillingRateImpl.setModifiedDate(hrProjectBillingRate.getModifiedDate());
		hrProjectBillingRateImpl.setHrProjectId(hrProjectBillingRate.getHrProjectId());
		hrProjectBillingRateImpl.setHrProjectRoleId(hrProjectBillingRate.getHrProjectRoleId());
		hrProjectBillingRateImpl.setRate(hrProjectBillingRate.getRate());

		return hrProjectBillingRateImpl;
	}

	/**
	 * Returns the h r project billing rate with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r project billing rate
	 * @return the h r project billing rate
	 * @throws com.liferay.portal.NoSuchModelException if a h r project billing rate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRProjectBillingRate findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r project billing rate with the primary key or throws a {@link com.liferay.hr.NoSuchProjectBillingRateException} if it could not be found.
	 *
	 * @param hrProjectBillingRateId the primary key of the h r project billing rate
	 * @return the h r project billing rate
	 * @throws com.liferay.hr.NoSuchProjectBillingRateException if a h r project billing rate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRProjectBillingRate findByPrimaryKey(long hrProjectBillingRateId)
		throws NoSuchProjectBillingRateException, SystemException {
		HRProjectBillingRate hrProjectBillingRate = fetchByPrimaryKey(hrProjectBillingRateId);

		if (hrProjectBillingRate == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrProjectBillingRateId);
			}

			throw new NoSuchProjectBillingRateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrProjectBillingRateId);
		}

		return hrProjectBillingRate;
	}

	/**
	 * Returns the h r project billing rate with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r project billing rate
	 * @return the h r project billing rate, or <code>null</code> if a h r project billing rate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRProjectBillingRate fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r project billing rate with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrProjectBillingRateId the primary key of the h r project billing rate
	 * @return the h r project billing rate, or <code>null</code> if a h r project billing rate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRProjectBillingRate fetchByPrimaryKey(long hrProjectBillingRateId)
		throws SystemException {
		HRProjectBillingRate hrProjectBillingRate = (HRProjectBillingRate)EntityCacheUtil.getResult(HRProjectBillingRateModelImpl.ENTITY_CACHE_ENABLED,
				HRProjectBillingRateImpl.class, hrProjectBillingRateId, this);

		if (hrProjectBillingRate == _nullHRProjectBillingRate) {
			return null;
		}

		if (hrProjectBillingRate == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrProjectBillingRate = (HRProjectBillingRate)session.get(HRProjectBillingRateImpl.class,
						Long.valueOf(hrProjectBillingRateId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrProjectBillingRate != null) {
					cacheResult(hrProjectBillingRate);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRProjectBillingRateModelImpl.ENTITY_CACHE_ENABLED,
						HRProjectBillingRateImpl.class, hrProjectBillingRateId,
						_nullHRProjectBillingRate);
				}

				closeSession(session);
			}
		}

		return hrProjectBillingRate;
	}

	/**
	 * Returns all the h r project billing rates.
	 *
	 * @return the h r project billing rates
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRProjectBillingRate> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r project billing rates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r project billing rates
	 * @param end the upper bound of the range of h r project billing rates (not inclusive)
	 * @return the range of h r project billing rates
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRProjectBillingRate> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r project billing rates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r project billing rates
	 * @param end the upper bound of the range of h r project billing rates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r project billing rates
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRProjectBillingRate> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRProjectBillingRate> list = (List<HRProjectBillingRate>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRPROJECTBILLINGRATE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRPROJECTBILLINGRATE;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRProjectBillingRate>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRProjectBillingRate>)QueryUtil.list(q,
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
	 * Removes all the h r project billing rates from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRProjectBillingRate hrProjectBillingRate : findAll()) {
			hrProjectBillingRatePersistence.remove(hrProjectBillingRate);
		}
	}

	/**
	 * Returns the number of h r project billing rates.
	 *
	 * @return the number of h r project billing rates
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

				Query q = session.createQuery(_SQL_COUNT_HRPROJECTBILLINGRATE);

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
	 * Initializes the h r project billing rate persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRProjectBillingRate")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRProjectBillingRate>> listenersList = new ArrayList<ModelListener<HRProjectBillingRate>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRProjectBillingRate>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRProjectBillingRateImpl.class.getName());
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
	private static final String _SQL_SELECT_HRPROJECTBILLINGRATE = "SELECT hrProjectBillingRate FROM HRProjectBillingRate hrProjectBillingRate";
	private static final String _SQL_COUNT_HRPROJECTBILLINGRATE = "SELECT COUNT(hrProjectBillingRate) FROM HRProjectBillingRate hrProjectBillingRate";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrProjectBillingRate.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRProjectBillingRate exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRProjectBillingRatePersistenceImpl.class);
	private static HRProjectBillingRate _nullHRProjectBillingRate = new HRProjectBillingRateImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRProjectBillingRate> toCacheModel() {
				return _nullHRProjectBillingRateCacheModel;
			}
		};

	private static CacheModel<HRProjectBillingRate> _nullHRProjectBillingRateCacheModel =
		new CacheModel<HRProjectBillingRate>() {
			public HRProjectBillingRate toEntityModel() {
				return _nullHRProjectBillingRate;
			}
		};
}