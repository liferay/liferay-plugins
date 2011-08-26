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

import com.liferay.hr.NoSuchUserHistoryException;
import com.liferay.hr.model.HRUserHistory;
import com.liferay.hr.model.impl.HRUserHistoryImpl;
import com.liferay.hr.model.impl.HRUserHistoryModelImpl;

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
 * The persistence implementation for the h r user history service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRUserHistoryPersistence
 * @see HRUserHistoryUtil
 * @generated
 */
public class HRUserHistoryPersistenceImpl extends BasePersistenceImpl<HRUserHistory>
	implements HRUserHistoryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRUserHistoryUtil} to access the h r user history persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRUserHistoryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRUserHistoryModelImpl.ENTITY_CACHE_ENABLED,
			HRUserHistoryModelImpl.FINDER_CACHE_ENABLED,
			HRUserHistoryImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRUserHistoryModelImpl.ENTITY_CACHE_ENABLED,
			HRUserHistoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r user history in the entity cache if it is enabled.
	 *
	 * @param hrUserHistory the h r user history
	 */
	public void cacheResult(HRUserHistory hrUserHistory) {
		EntityCacheUtil.putResult(HRUserHistoryModelImpl.ENTITY_CACHE_ENABLED,
			HRUserHistoryImpl.class, hrUserHistory.getPrimaryKey(),
			hrUserHistory);

		hrUserHistory.resetOriginalValues();
	}

	/**
	 * Caches the h r user histories in the entity cache if it is enabled.
	 *
	 * @param hrUserHistories the h r user histories
	 */
	public void cacheResult(List<HRUserHistory> hrUserHistories) {
		for (HRUserHistory hrUserHistory : hrUserHistories) {
			if (EntityCacheUtil.getResult(
						HRUserHistoryModelImpl.ENTITY_CACHE_ENABLED,
						HRUserHistoryImpl.class, hrUserHistory.getPrimaryKey(),
						this) == null) {
				cacheResult(hrUserHistory);
			}
		}
	}

	/**
	 * Clears the cache for all h r user histories.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRUserHistoryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRUserHistoryImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r user history.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRUserHistory hrUserHistory) {
		EntityCacheUtil.removeResult(HRUserHistoryModelImpl.ENTITY_CACHE_ENABLED,
			HRUserHistoryImpl.class, hrUserHistory.getPrimaryKey());
	}

	/**
	 * Creates a new h r user history with the primary key. Does not add the h r user history to the database.
	 *
	 * @param hrUserHistoryId the primary key for the new h r user history
	 * @return the new h r user history
	 */
	public HRUserHistory create(long hrUserHistoryId) {
		HRUserHistory hrUserHistory = new HRUserHistoryImpl();

		hrUserHistory.setNew(true);
		hrUserHistory.setPrimaryKey(hrUserHistoryId);

		return hrUserHistory;
	}

	/**
	 * Removes the h r user history with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r user history
	 * @return the h r user history that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r user history with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRUserHistory remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r user history with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrUserHistoryId the primary key of the h r user history
	 * @return the h r user history that was removed
	 * @throws com.liferay.hr.NoSuchUserHistoryException if a h r user history with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRUserHistory remove(long hrUserHistoryId)
		throws NoSuchUserHistoryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRUserHistory hrUserHistory = (HRUserHistory)session.get(HRUserHistoryImpl.class,
					Long.valueOf(hrUserHistoryId));

			if (hrUserHistory == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						hrUserHistoryId);
				}

				throw new NoSuchUserHistoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrUserHistoryId);
			}

			return hrUserHistoryPersistence.remove(hrUserHistory);
		}
		catch (NoSuchUserHistoryException nsee) {
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
	 * Removes the h r user history from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrUserHistory the h r user history
	 * @return the h r user history that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRUserHistory remove(HRUserHistory hrUserHistory)
		throws SystemException {
		return super.remove(hrUserHistory);
	}

	@Override
	protected HRUserHistory removeImpl(HRUserHistory hrUserHistory)
		throws SystemException {
		hrUserHistory = toUnwrappedModel(hrUserHistory);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrUserHistory);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRUserHistoryModelImpl.ENTITY_CACHE_ENABLED,
			HRUserHistoryImpl.class, hrUserHistory.getPrimaryKey());

		return hrUserHistory;
	}

	@Override
	public HRUserHistory updateImpl(
		com.liferay.hr.model.HRUserHistory hrUserHistory, boolean merge)
		throws SystemException {
		hrUserHistory = toUnwrappedModel(hrUserHistory);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrUserHistory, merge);

			hrUserHistory.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRUserHistoryModelImpl.ENTITY_CACHE_ENABLED,
			HRUserHistoryImpl.class, hrUserHistory.getPrimaryKey(),
			hrUserHistory);

		return hrUserHistory;
	}

	protected HRUserHistory toUnwrappedModel(HRUserHistory hrUserHistory) {
		if (hrUserHistory instanceof HRUserHistoryImpl) {
			return hrUserHistory;
		}

		HRUserHistoryImpl hrUserHistoryImpl = new HRUserHistoryImpl();

		hrUserHistoryImpl.setNew(hrUserHistory.isNew());
		hrUserHistoryImpl.setPrimaryKey(hrUserHistory.getPrimaryKey());

		hrUserHistoryImpl.setHrUserHistoryId(hrUserHistory.getHrUserHistoryId());
		hrUserHistoryImpl.setGroupId(hrUserHistory.getGroupId());
		hrUserHistoryImpl.setCompanyId(hrUserHistory.getCompanyId());
		hrUserHistoryImpl.setUserId(hrUserHistory.getUserId());
		hrUserHistoryImpl.setUserName(hrUserHistory.getUserName());
		hrUserHistoryImpl.setCreateDate(hrUserHistory.getCreateDate());
		hrUserHistoryImpl.setHrEmploymentTypeId(hrUserHistory.getHrEmploymentTypeId());
		hrUserHistoryImpl.setHrJobTitleId(hrUserHistory.getHrJobTitleId());
		hrUserHistoryImpl.setHrOfficeId(hrUserHistory.getHrOfficeId());
		hrUserHistoryImpl.setHrTerminationTypeId(hrUserHistory.getHrTerminationTypeId());
		hrUserHistoryImpl.setHrWageTypeId(hrUserHistory.getHrWageTypeId());
		hrUserHistoryImpl.setHireDate(hrUserHistory.getHireDate());
		hrUserHistoryImpl.setTerminationDate(hrUserHistory.getTerminationDate());
		hrUserHistoryImpl.setWageAmount(hrUserHistory.getWageAmount());
		hrUserHistoryImpl.setWageCurrencyCode(hrUserHistory.getWageCurrencyCode());
		hrUserHistoryImpl.setBenefitsExempt(hrUserHistory.isBenefitsExempt());
		hrUserHistoryImpl.setOvertimeExempt(hrUserHistory.isOvertimeExempt());

		return hrUserHistoryImpl;
	}

	/**
	 * Returns the h r user history with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r user history
	 * @return the h r user history
	 * @throws com.liferay.portal.NoSuchModelException if a h r user history with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRUserHistory findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r user history with the primary key or throws a {@link com.liferay.hr.NoSuchUserHistoryException} if it could not be found.
	 *
	 * @param hrUserHistoryId the primary key of the h r user history
	 * @return the h r user history
	 * @throws com.liferay.hr.NoSuchUserHistoryException if a h r user history with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRUserHistory findByPrimaryKey(long hrUserHistoryId)
		throws NoSuchUserHistoryException, SystemException {
		HRUserHistory hrUserHistory = fetchByPrimaryKey(hrUserHistoryId);

		if (hrUserHistory == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrUserHistoryId);
			}

			throw new NoSuchUserHistoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrUserHistoryId);
		}

		return hrUserHistory;
	}

	/**
	 * Returns the h r user history with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r user history
	 * @return the h r user history, or <code>null</code> if a h r user history with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRUserHistory fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r user history with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrUserHistoryId the primary key of the h r user history
	 * @return the h r user history, or <code>null</code> if a h r user history with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRUserHistory fetchByPrimaryKey(long hrUserHistoryId)
		throws SystemException {
		HRUserHistory hrUserHistory = (HRUserHistory)EntityCacheUtil.getResult(HRUserHistoryModelImpl.ENTITY_CACHE_ENABLED,
				HRUserHistoryImpl.class, hrUserHistoryId, this);

		if (hrUserHistory == _nullHRUserHistory) {
			return null;
		}

		if (hrUserHistory == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrUserHistory = (HRUserHistory)session.get(HRUserHistoryImpl.class,
						Long.valueOf(hrUserHistoryId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrUserHistory != null) {
					cacheResult(hrUserHistory);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRUserHistoryModelImpl.ENTITY_CACHE_ENABLED,
						HRUserHistoryImpl.class, hrUserHistoryId,
						_nullHRUserHistory);
				}

				closeSession(session);
			}
		}

		return hrUserHistory;
	}

	/**
	 * Returns all the h r user histories.
	 *
	 * @return the h r user histories
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRUserHistory> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r user histories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r user histories
	 * @param end the upper bound of the range of h r user histories (not inclusive)
	 * @return the range of h r user histories
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRUserHistory> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r user histories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r user histories
	 * @param end the upper bound of the range of h r user histories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r user histories
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRUserHistory> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRUserHistory> list = (List<HRUserHistory>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRUSERHISTORY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRUSERHISTORY;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRUserHistory>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRUserHistory>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the h r user histories from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRUserHistory hrUserHistory : findAll()) {
			hrUserHistoryPersistence.remove(hrUserHistory);
		}
	}

	/**
	 * Returns the number of h r user histories.
	 *
	 * @return the number of h r user histories
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

				Query q = session.createQuery(_SQL_COUNT_HRUSERHISTORY);

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
	 * Initializes the h r user history persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRUserHistory")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRUserHistory>> listenersList = new ArrayList<ModelListener<HRUserHistory>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRUserHistory>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRUserHistoryImpl.class.getName());
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
	private static final String _SQL_SELECT_HRUSERHISTORY = "SELECT hrUserHistory FROM HRUserHistory hrUserHistory";
	private static final String _SQL_COUNT_HRUSERHISTORY = "SELECT COUNT(hrUserHistory) FROM HRUserHistory hrUserHistory";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrUserHistory.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRUserHistory exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRUserHistoryPersistenceImpl.class);
	private static HRUserHistory _nullHRUserHistory = new HRUserHistoryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRUserHistory> toCacheModel() {
				return _nullHRUserHistoryCacheModel;
			}
		};

	private static CacheModel<HRUserHistory> _nullHRUserHistoryCacheModel = new CacheModel<HRUserHistory>() {
			public HRUserHistory toEntityModel() {
				return _nullHRUserHistory;
			}
		};
}