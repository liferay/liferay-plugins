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

import com.liferay.hr.NoSuchAssetCheckoutException;
import com.liferay.hr.model.HRAssetCheckout;
import com.liferay.hr.model.impl.HRAssetCheckoutImpl;
import com.liferay.hr.model.impl.HRAssetCheckoutModelImpl;

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
 * The persistence implementation for the h r asset checkout service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRAssetCheckoutPersistence
 * @see HRAssetCheckoutUtil
 * @generated
 */
public class HRAssetCheckoutPersistenceImpl extends BasePersistenceImpl<HRAssetCheckout>
	implements HRAssetCheckoutPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRAssetCheckoutUtil} to access the h r asset checkout persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRAssetCheckoutImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRAssetCheckoutModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetCheckoutModelImpl.FINDER_CACHE_ENABLED,
			HRAssetCheckoutImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRAssetCheckoutModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetCheckoutModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r asset checkout in the entity cache if it is enabled.
	 *
	 * @param hrAssetCheckout the h r asset checkout
	 */
	public void cacheResult(HRAssetCheckout hrAssetCheckout) {
		EntityCacheUtil.putResult(HRAssetCheckoutModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetCheckoutImpl.class, hrAssetCheckout.getPrimaryKey(),
			hrAssetCheckout);

		hrAssetCheckout.resetOriginalValues();
	}

	/**
	 * Caches the h r asset checkouts in the entity cache if it is enabled.
	 *
	 * @param hrAssetCheckouts the h r asset checkouts
	 */
	public void cacheResult(List<HRAssetCheckout> hrAssetCheckouts) {
		for (HRAssetCheckout hrAssetCheckout : hrAssetCheckouts) {
			if (EntityCacheUtil.getResult(
						HRAssetCheckoutModelImpl.ENTITY_CACHE_ENABLED,
						HRAssetCheckoutImpl.class,
						hrAssetCheckout.getPrimaryKey(), this) == null) {
				cacheResult(hrAssetCheckout);
			}
		}
	}

	/**
	 * Clears the cache for all h r asset checkouts.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRAssetCheckoutImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRAssetCheckoutImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r asset checkout.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRAssetCheckout hrAssetCheckout) {
		EntityCacheUtil.removeResult(HRAssetCheckoutModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetCheckoutImpl.class, hrAssetCheckout.getPrimaryKey());
	}

	/**
	 * Creates a new h r asset checkout with the primary key. Does not add the h r asset checkout to the database.
	 *
	 * @param hrAssetCheckoutId the primary key for the new h r asset checkout
	 * @return the new h r asset checkout
	 */
	public HRAssetCheckout create(long hrAssetCheckoutId) {
		HRAssetCheckout hrAssetCheckout = new HRAssetCheckoutImpl();

		hrAssetCheckout.setNew(true);
		hrAssetCheckout.setPrimaryKey(hrAssetCheckoutId);

		return hrAssetCheckout;
	}

	/**
	 * Removes the h r asset checkout with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r asset checkout
	 * @return the h r asset checkout that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r asset checkout with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRAssetCheckout remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r asset checkout with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrAssetCheckoutId the primary key of the h r asset checkout
	 * @return the h r asset checkout that was removed
	 * @throws com.liferay.hr.NoSuchAssetCheckoutException if a h r asset checkout with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetCheckout remove(long hrAssetCheckoutId)
		throws NoSuchAssetCheckoutException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRAssetCheckout hrAssetCheckout = (HRAssetCheckout)session.get(HRAssetCheckoutImpl.class,
					Long.valueOf(hrAssetCheckoutId));

			if (hrAssetCheckout == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						hrAssetCheckoutId);
				}

				throw new NoSuchAssetCheckoutException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrAssetCheckoutId);
			}

			return hrAssetCheckoutPersistence.remove(hrAssetCheckout);
		}
		catch (NoSuchAssetCheckoutException nsee) {
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
	 * Removes the h r asset checkout from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrAssetCheckout the h r asset checkout
	 * @return the h r asset checkout that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRAssetCheckout remove(HRAssetCheckout hrAssetCheckout)
		throws SystemException {
		return super.remove(hrAssetCheckout);
	}

	@Override
	protected HRAssetCheckout removeImpl(HRAssetCheckout hrAssetCheckout)
		throws SystemException {
		hrAssetCheckout = toUnwrappedModel(hrAssetCheckout);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrAssetCheckout);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRAssetCheckoutModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetCheckoutImpl.class, hrAssetCheckout.getPrimaryKey());

		return hrAssetCheckout;
	}

	@Override
	public HRAssetCheckout updateImpl(
		com.liferay.hr.model.HRAssetCheckout hrAssetCheckout, boolean merge)
		throws SystemException {
		hrAssetCheckout = toUnwrappedModel(hrAssetCheckout);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrAssetCheckout, merge);

			hrAssetCheckout.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRAssetCheckoutModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetCheckoutImpl.class, hrAssetCheckout.getPrimaryKey(),
			hrAssetCheckout);

		return hrAssetCheckout;
	}

	protected HRAssetCheckout toUnwrappedModel(HRAssetCheckout hrAssetCheckout) {
		if (hrAssetCheckout instanceof HRAssetCheckoutImpl) {
			return hrAssetCheckout;
		}

		HRAssetCheckoutImpl hrAssetCheckoutImpl = new HRAssetCheckoutImpl();

		hrAssetCheckoutImpl.setNew(hrAssetCheckout.isNew());
		hrAssetCheckoutImpl.setPrimaryKey(hrAssetCheckout.getPrimaryKey());

		hrAssetCheckoutImpl.setHrAssetCheckoutId(hrAssetCheckout.getHrAssetCheckoutId());
		hrAssetCheckoutImpl.setGroupId(hrAssetCheckout.getGroupId());
		hrAssetCheckoutImpl.setCompanyId(hrAssetCheckout.getCompanyId());
		hrAssetCheckoutImpl.setUserId(hrAssetCheckout.getUserId());
		hrAssetCheckoutImpl.setUserName(hrAssetCheckout.getUserName());
		hrAssetCheckoutImpl.setCreateDate(hrAssetCheckout.getCreateDate());
		hrAssetCheckoutImpl.setModifiedDate(hrAssetCheckout.getModifiedDate());
		hrAssetCheckoutImpl.setHrAssetId(hrAssetCheckout.getHrAssetId());
		hrAssetCheckoutImpl.setHrUserId(hrAssetCheckout.getHrUserId());
		hrAssetCheckoutImpl.setCheckoutDate(hrAssetCheckout.getCheckoutDate());
		hrAssetCheckoutImpl.setExpectedCheckInDate(hrAssetCheckout.getExpectedCheckInDate());
		hrAssetCheckoutImpl.setActualCheckInDate(hrAssetCheckout.getActualCheckInDate());

		return hrAssetCheckoutImpl;
	}

	/**
	 * Returns the h r asset checkout with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r asset checkout
	 * @return the h r asset checkout
	 * @throws com.liferay.portal.NoSuchModelException if a h r asset checkout with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRAssetCheckout findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r asset checkout with the primary key or throws a {@link com.liferay.hr.NoSuchAssetCheckoutException} if it could not be found.
	 *
	 * @param hrAssetCheckoutId the primary key of the h r asset checkout
	 * @return the h r asset checkout
	 * @throws com.liferay.hr.NoSuchAssetCheckoutException if a h r asset checkout with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetCheckout findByPrimaryKey(long hrAssetCheckoutId)
		throws NoSuchAssetCheckoutException, SystemException {
		HRAssetCheckout hrAssetCheckout = fetchByPrimaryKey(hrAssetCheckoutId);

		if (hrAssetCheckout == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrAssetCheckoutId);
			}

			throw new NoSuchAssetCheckoutException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrAssetCheckoutId);
		}

		return hrAssetCheckout;
	}

	/**
	 * Returns the h r asset checkout with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r asset checkout
	 * @return the h r asset checkout, or <code>null</code> if a h r asset checkout with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRAssetCheckout fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r asset checkout with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrAssetCheckoutId the primary key of the h r asset checkout
	 * @return the h r asset checkout, or <code>null</code> if a h r asset checkout with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetCheckout fetchByPrimaryKey(long hrAssetCheckoutId)
		throws SystemException {
		HRAssetCheckout hrAssetCheckout = (HRAssetCheckout)EntityCacheUtil.getResult(HRAssetCheckoutModelImpl.ENTITY_CACHE_ENABLED,
				HRAssetCheckoutImpl.class, hrAssetCheckoutId, this);

		if (hrAssetCheckout == _nullHRAssetCheckout) {
			return null;
		}

		if (hrAssetCheckout == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrAssetCheckout = (HRAssetCheckout)session.get(HRAssetCheckoutImpl.class,
						Long.valueOf(hrAssetCheckoutId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrAssetCheckout != null) {
					cacheResult(hrAssetCheckout);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRAssetCheckoutModelImpl.ENTITY_CACHE_ENABLED,
						HRAssetCheckoutImpl.class, hrAssetCheckoutId,
						_nullHRAssetCheckout);
				}

				closeSession(session);
			}
		}

		return hrAssetCheckout;
	}

	/**
	 * Returns all the h r asset checkouts.
	 *
	 * @return the h r asset checkouts
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRAssetCheckout> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r asset checkouts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r asset checkouts
	 * @param end the upper bound of the range of h r asset checkouts (not inclusive)
	 * @return the range of h r asset checkouts
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRAssetCheckout> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r asset checkouts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r asset checkouts
	 * @param end the upper bound of the range of h r asset checkouts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r asset checkouts
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRAssetCheckout> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRAssetCheckout> list = (List<HRAssetCheckout>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRASSETCHECKOUT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRASSETCHECKOUT;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRAssetCheckout>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRAssetCheckout>)QueryUtil.list(q,
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
	 * Removes all the h r asset checkouts from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRAssetCheckout hrAssetCheckout : findAll()) {
			hrAssetCheckoutPersistence.remove(hrAssetCheckout);
		}
	}

	/**
	 * Returns the number of h r asset checkouts.
	 *
	 * @return the number of h r asset checkouts
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

				Query q = session.createQuery(_SQL_COUNT_HRASSETCHECKOUT);

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
	 * Initializes the h r asset checkout persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRAssetCheckout")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRAssetCheckout>> listenersList = new ArrayList<ModelListener<HRAssetCheckout>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRAssetCheckout>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRAssetCheckoutImpl.class.getName());
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
	private static final String _SQL_SELECT_HRASSETCHECKOUT = "SELECT hrAssetCheckout FROM HRAssetCheckout hrAssetCheckout";
	private static final String _SQL_COUNT_HRASSETCHECKOUT = "SELECT COUNT(hrAssetCheckout) FROM HRAssetCheckout hrAssetCheckout";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrAssetCheckout.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRAssetCheckout exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRAssetCheckoutPersistenceImpl.class);
	private static HRAssetCheckout _nullHRAssetCheckout = new HRAssetCheckoutImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRAssetCheckout> toCacheModel() {
				return _nullHRAssetCheckoutCacheModel;
			}
		};

	private static CacheModel<HRAssetCheckout> _nullHRAssetCheckoutCacheModel = new CacheModel<HRAssetCheckout>() {
			public HRAssetCheckout toEntityModel() {
				return _nullHRAssetCheckout;
			}
		};
}