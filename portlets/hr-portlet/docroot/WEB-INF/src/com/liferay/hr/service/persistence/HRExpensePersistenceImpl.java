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

import com.liferay.hr.NoSuchExpenseException;
import com.liferay.hr.model.HRExpense;
import com.liferay.hr.model.impl.HRExpenseImpl;
import com.liferay.hr.model.impl.HRExpenseModelImpl;

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
 * The persistence implementation for the h r expense service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRExpensePersistence
 * @see HRExpenseUtil
 * @generated
 */
public class HRExpensePersistenceImpl extends BasePersistenceImpl<HRExpense>
	implements HRExpensePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRExpenseUtil} to access the h r expense persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRExpenseImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRExpenseModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseModelImpl.FINDER_CACHE_ENABLED, HRExpenseImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRExpenseModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r expense in the entity cache if it is enabled.
	 *
	 * @param hrExpense the h r expense
	 */
	public void cacheResult(HRExpense hrExpense) {
		EntityCacheUtil.putResult(HRExpenseModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseImpl.class, hrExpense.getPrimaryKey(), hrExpense);

		hrExpense.resetOriginalValues();
	}

	/**
	 * Caches the h r expenses in the entity cache if it is enabled.
	 *
	 * @param hrExpenses the h r expenses
	 */
	public void cacheResult(List<HRExpense> hrExpenses) {
		for (HRExpense hrExpense : hrExpenses) {
			if (EntityCacheUtil.getResult(
						HRExpenseModelImpl.ENTITY_CACHE_ENABLED,
						HRExpenseImpl.class, hrExpense.getPrimaryKey(), this) == null) {
				cacheResult(hrExpense);
			}
		}
	}

	/**
	 * Clears the cache for all h r expenses.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRExpenseImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRExpenseImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r expense.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRExpense hrExpense) {
		EntityCacheUtil.removeResult(HRExpenseModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseImpl.class, hrExpense.getPrimaryKey());
	}

	/**
	 * Creates a new h r expense with the primary key. Does not add the h r expense to the database.
	 *
	 * @param hrExpenseId the primary key for the new h r expense
	 * @return the new h r expense
	 */
	public HRExpense create(long hrExpenseId) {
		HRExpense hrExpense = new HRExpenseImpl();

		hrExpense.setNew(true);
		hrExpense.setPrimaryKey(hrExpenseId);

		return hrExpense;
	}

	/**
	 * Removes the h r expense with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r expense
	 * @return the h r expense that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r expense with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRExpense remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r expense with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrExpenseId the primary key of the h r expense
	 * @return the h r expense that was removed
	 * @throws com.liferay.hr.NoSuchExpenseException if a h r expense with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpense remove(long hrExpenseId)
		throws NoSuchExpenseException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRExpense hrExpense = (HRExpense)session.get(HRExpenseImpl.class,
					Long.valueOf(hrExpenseId));

			if (hrExpense == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrExpenseId);
				}

				throw new NoSuchExpenseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrExpenseId);
			}

			return hrExpensePersistence.remove(hrExpense);
		}
		catch (NoSuchExpenseException nsee) {
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
	 * Removes the h r expense from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrExpense the h r expense
	 * @return the h r expense that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRExpense remove(HRExpense hrExpense) throws SystemException {
		return super.remove(hrExpense);
	}

	@Override
	protected HRExpense removeImpl(HRExpense hrExpense)
		throws SystemException {
		hrExpense = toUnwrappedModel(hrExpense);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrExpense);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRExpenseModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseImpl.class, hrExpense.getPrimaryKey());

		return hrExpense;
	}

	@Override
	public HRExpense updateImpl(com.liferay.hr.model.HRExpense hrExpense,
		boolean merge) throws SystemException {
		hrExpense = toUnwrappedModel(hrExpense);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrExpense, merge);

			hrExpense.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRExpenseModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseImpl.class, hrExpense.getPrimaryKey(), hrExpense);

		return hrExpense;
	}

	protected HRExpense toUnwrappedModel(HRExpense hrExpense) {
		if (hrExpense instanceof HRExpenseImpl) {
			return hrExpense;
		}

		HRExpenseImpl hrExpenseImpl = new HRExpenseImpl();

		hrExpenseImpl.setNew(hrExpense.isNew());
		hrExpenseImpl.setPrimaryKey(hrExpense.getPrimaryKey());

		hrExpenseImpl.setHrExpenseId(hrExpense.getHrExpenseId());
		hrExpenseImpl.setGroupId(hrExpense.getGroupId());
		hrExpenseImpl.setCompanyId(hrExpense.getCompanyId());
		hrExpenseImpl.setUserId(hrExpense.getUserId());
		hrExpenseImpl.setUserName(hrExpense.getUserName());
		hrExpenseImpl.setCreateDate(hrExpense.getCreateDate());
		hrExpenseImpl.setModifiedDate(hrExpense.getModifiedDate());
		hrExpenseImpl.setHrExpenseAccountId(hrExpense.getHrExpenseAccountId());
		hrExpenseImpl.setHrExpenseTypeId(hrExpense.getHrExpenseTypeId());
		hrExpenseImpl.setHrUserId(hrExpense.getHrUserId());
		hrExpenseImpl.setExpenseDate(hrExpense.getExpenseDate());
		hrExpenseImpl.setExpenseAmount(hrExpense.getExpenseAmount());
		hrExpenseImpl.setExpenseHRExpenseCurrencyId(hrExpense.getExpenseHRExpenseCurrencyId());
		hrExpenseImpl.setReimbursementAmount(hrExpense.getReimbursementAmount());
		hrExpenseImpl.setReimbursementHRExpenseCurrencyId(hrExpense.getReimbursementHRExpenseCurrencyId());
		hrExpenseImpl.setStatus(hrExpense.getStatus());
		hrExpenseImpl.setStatusByUserId(hrExpense.getStatusByUserId());
		hrExpenseImpl.setStatusByUserName(hrExpense.getStatusByUserName());
		hrExpenseImpl.setStatusDate(hrExpense.getStatusDate());

		return hrExpenseImpl;
	}

	/**
	 * Returns the h r expense with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r expense
	 * @return the h r expense
	 * @throws com.liferay.portal.NoSuchModelException if a h r expense with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRExpense findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r expense with the primary key or throws a {@link com.liferay.hr.NoSuchExpenseException} if it could not be found.
	 *
	 * @param hrExpenseId the primary key of the h r expense
	 * @return the h r expense
	 * @throws com.liferay.hr.NoSuchExpenseException if a h r expense with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpense findByPrimaryKey(long hrExpenseId)
		throws NoSuchExpenseException, SystemException {
		HRExpense hrExpense = fetchByPrimaryKey(hrExpenseId);

		if (hrExpense == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrExpenseId);
			}

			throw new NoSuchExpenseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrExpenseId);
		}

		return hrExpense;
	}

	/**
	 * Returns the h r expense with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r expense
	 * @return the h r expense, or <code>null</code> if a h r expense with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRExpense fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r expense with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrExpenseId the primary key of the h r expense
	 * @return the h r expense, or <code>null</code> if a h r expense with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpense fetchByPrimaryKey(long hrExpenseId)
		throws SystemException {
		HRExpense hrExpense = (HRExpense)EntityCacheUtil.getResult(HRExpenseModelImpl.ENTITY_CACHE_ENABLED,
				HRExpenseImpl.class, hrExpenseId, this);

		if (hrExpense == _nullHRExpense) {
			return null;
		}

		if (hrExpense == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrExpense = (HRExpense)session.get(HRExpenseImpl.class,
						Long.valueOf(hrExpenseId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrExpense != null) {
					cacheResult(hrExpense);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRExpenseModelImpl.ENTITY_CACHE_ENABLED,
						HRExpenseImpl.class, hrExpenseId, _nullHRExpense);
				}

				closeSession(session);
			}
		}

		return hrExpense;
	}

	/**
	 * Returns all the h r expenses.
	 *
	 * @return the h r expenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRExpense> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r expenses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r expenses
	 * @param end the upper bound of the range of h r expenses (not inclusive)
	 * @return the range of h r expenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRExpense> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r expenses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r expenses
	 * @param end the upper bound of the range of h r expenses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r expenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRExpense> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRExpense> list = (List<HRExpense>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HREXPENSE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HREXPENSE;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRExpense>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRExpense>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the h r expenses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRExpense hrExpense : findAll()) {
			hrExpensePersistence.remove(hrExpense);
		}
	}

	/**
	 * Returns the number of h r expenses.
	 *
	 * @return the number of h r expenses
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

				Query q = session.createQuery(_SQL_COUNT_HREXPENSE);

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
	 * Initializes the h r expense persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRExpense")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRExpense>> listenersList = new ArrayList<ModelListener<HRExpense>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRExpense>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRExpenseImpl.class.getName());
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
	private static final String _SQL_SELECT_HREXPENSE = "SELECT hrExpense FROM HRExpense hrExpense";
	private static final String _SQL_COUNT_HREXPENSE = "SELECT COUNT(hrExpense) FROM HRExpense hrExpense";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrExpense.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRExpense exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRExpensePersistenceImpl.class);
	private static HRExpense _nullHRExpense = new HRExpenseImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRExpense> toCacheModel() {
				return _nullHRExpenseCacheModel;
			}
		};

	private static CacheModel<HRExpense> _nullHRExpenseCacheModel = new CacheModel<HRExpense>() {
			public HRExpense toEntityModel() {
				return _nullHRExpense;
			}
		};
}