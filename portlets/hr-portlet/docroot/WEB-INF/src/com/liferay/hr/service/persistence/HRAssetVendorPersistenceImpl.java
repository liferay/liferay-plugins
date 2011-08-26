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

import com.liferay.hr.NoSuchAssetVendorException;
import com.liferay.hr.model.HRAssetVendor;
import com.liferay.hr.model.impl.HRAssetVendorImpl;
import com.liferay.hr.model.impl.HRAssetVendorModelImpl;

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
 * The persistence implementation for the h r asset vendor service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRAssetVendorPersistence
 * @see HRAssetVendorUtil
 * @generated
 */
public class HRAssetVendorPersistenceImpl extends BasePersistenceImpl<HRAssetVendor>
	implements HRAssetVendorPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRAssetVendorUtil} to access the h r asset vendor persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRAssetVendorImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRAssetVendorModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetVendorModelImpl.FINDER_CACHE_ENABLED,
			HRAssetVendorImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRAssetVendorModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetVendorModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r asset vendor in the entity cache if it is enabled.
	 *
	 * @param hrAssetVendor the h r asset vendor
	 */
	public void cacheResult(HRAssetVendor hrAssetVendor) {
		EntityCacheUtil.putResult(HRAssetVendorModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetVendorImpl.class, hrAssetVendor.getPrimaryKey(),
			hrAssetVendor);

		hrAssetVendor.resetOriginalValues();
	}

	/**
	 * Caches the h r asset vendors in the entity cache if it is enabled.
	 *
	 * @param hrAssetVendors the h r asset vendors
	 */
	public void cacheResult(List<HRAssetVendor> hrAssetVendors) {
		for (HRAssetVendor hrAssetVendor : hrAssetVendors) {
			if (EntityCacheUtil.getResult(
						HRAssetVendorModelImpl.ENTITY_CACHE_ENABLED,
						HRAssetVendorImpl.class, hrAssetVendor.getPrimaryKey(),
						this) == null) {
				cacheResult(hrAssetVendor);
			}
		}
	}

	/**
	 * Clears the cache for all h r asset vendors.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRAssetVendorImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRAssetVendorImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r asset vendor.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRAssetVendor hrAssetVendor) {
		EntityCacheUtil.removeResult(HRAssetVendorModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetVendorImpl.class, hrAssetVendor.getPrimaryKey());
	}

	/**
	 * Creates a new h r asset vendor with the primary key. Does not add the h r asset vendor to the database.
	 *
	 * @param hrAssetVendorId the primary key for the new h r asset vendor
	 * @return the new h r asset vendor
	 */
	public HRAssetVendor create(long hrAssetVendorId) {
		HRAssetVendor hrAssetVendor = new HRAssetVendorImpl();

		hrAssetVendor.setNew(true);
		hrAssetVendor.setPrimaryKey(hrAssetVendorId);

		return hrAssetVendor;
	}

	/**
	 * Removes the h r asset vendor with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r asset vendor
	 * @return the h r asset vendor that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r asset vendor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRAssetVendor remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r asset vendor with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrAssetVendorId the primary key of the h r asset vendor
	 * @return the h r asset vendor that was removed
	 * @throws com.liferay.hr.NoSuchAssetVendorException if a h r asset vendor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetVendor remove(long hrAssetVendorId)
		throws NoSuchAssetVendorException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRAssetVendor hrAssetVendor = (HRAssetVendor)session.get(HRAssetVendorImpl.class,
					Long.valueOf(hrAssetVendorId));

			if (hrAssetVendor == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						hrAssetVendorId);
				}

				throw new NoSuchAssetVendorException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrAssetVendorId);
			}

			return hrAssetVendorPersistence.remove(hrAssetVendor);
		}
		catch (NoSuchAssetVendorException nsee) {
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
	 * Removes the h r asset vendor from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrAssetVendor the h r asset vendor
	 * @return the h r asset vendor that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRAssetVendor remove(HRAssetVendor hrAssetVendor)
		throws SystemException {
		return super.remove(hrAssetVendor);
	}

	@Override
	protected HRAssetVendor removeImpl(HRAssetVendor hrAssetVendor)
		throws SystemException {
		hrAssetVendor = toUnwrappedModel(hrAssetVendor);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrAssetVendor);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRAssetVendorModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetVendorImpl.class, hrAssetVendor.getPrimaryKey());

		return hrAssetVendor;
	}

	@Override
	public HRAssetVendor updateImpl(
		com.liferay.hr.model.HRAssetVendor hrAssetVendor, boolean merge)
		throws SystemException {
		hrAssetVendor = toUnwrappedModel(hrAssetVendor);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrAssetVendor, merge);

			hrAssetVendor.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRAssetVendorModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetVendorImpl.class, hrAssetVendor.getPrimaryKey(),
			hrAssetVendor);

		return hrAssetVendor;
	}

	protected HRAssetVendor toUnwrappedModel(HRAssetVendor hrAssetVendor) {
		if (hrAssetVendor instanceof HRAssetVendorImpl) {
			return hrAssetVendor;
		}

		HRAssetVendorImpl hrAssetVendorImpl = new HRAssetVendorImpl();

		hrAssetVendorImpl.setNew(hrAssetVendor.isNew());
		hrAssetVendorImpl.setPrimaryKey(hrAssetVendor.getPrimaryKey());

		hrAssetVendorImpl.setHrAssetVendorId(hrAssetVendor.getHrAssetVendorId());
		hrAssetVendorImpl.setGroupId(hrAssetVendor.getGroupId());
		hrAssetVendorImpl.setCompanyId(hrAssetVendor.getCompanyId());
		hrAssetVendorImpl.setUserId(hrAssetVendor.getUserId());
		hrAssetVendorImpl.setUserName(hrAssetVendor.getUserName());
		hrAssetVendorImpl.setCreateDate(hrAssetVendor.getCreateDate());
		hrAssetVendorImpl.setModifiedDate(hrAssetVendor.getModifiedDate());
		hrAssetVendorImpl.setName(hrAssetVendor.getName());
		hrAssetVendorImpl.setDescription(hrAssetVendor.getDescription());

		return hrAssetVendorImpl;
	}

	/**
	 * Returns the h r asset vendor with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r asset vendor
	 * @return the h r asset vendor
	 * @throws com.liferay.portal.NoSuchModelException if a h r asset vendor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRAssetVendor findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r asset vendor with the primary key or throws a {@link com.liferay.hr.NoSuchAssetVendorException} if it could not be found.
	 *
	 * @param hrAssetVendorId the primary key of the h r asset vendor
	 * @return the h r asset vendor
	 * @throws com.liferay.hr.NoSuchAssetVendorException if a h r asset vendor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetVendor findByPrimaryKey(long hrAssetVendorId)
		throws NoSuchAssetVendorException, SystemException {
		HRAssetVendor hrAssetVendor = fetchByPrimaryKey(hrAssetVendorId);

		if (hrAssetVendor == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrAssetVendorId);
			}

			throw new NoSuchAssetVendorException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrAssetVendorId);
		}

		return hrAssetVendor;
	}

	/**
	 * Returns the h r asset vendor with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r asset vendor
	 * @return the h r asset vendor, or <code>null</code> if a h r asset vendor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRAssetVendor fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r asset vendor with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrAssetVendorId the primary key of the h r asset vendor
	 * @return the h r asset vendor, or <code>null</code> if a h r asset vendor with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetVendor fetchByPrimaryKey(long hrAssetVendorId)
		throws SystemException {
		HRAssetVendor hrAssetVendor = (HRAssetVendor)EntityCacheUtil.getResult(HRAssetVendorModelImpl.ENTITY_CACHE_ENABLED,
				HRAssetVendorImpl.class, hrAssetVendorId, this);

		if (hrAssetVendor == _nullHRAssetVendor) {
			return null;
		}

		if (hrAssetVendor == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrAssetVendor = (HRAssetVendor)session.get(HRAssetVendorImpl.class,
						Long.valueOf(hrAssetVendorId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrAssetVendor != null) {
					cacheResult(hrAssetVendor);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRAssetVendorModelImpl.ENTITY_CACHE_ENABLED,
						HRAssetVendorImpl.class, hrAssetVendorId,
						_nullHRAssetVendor);
				}

				closeSession(session);
			}
		}

		return hrAssetVendor;
	}

	/**
	 * Returns all the h r asset vendors.
	 *
	 * @return the h r asset vendors
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRAssetVendor> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r asset vendors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r asset vendors
	 * @param end the upper bound of the range of h r asset vendors (not inclusive)
	 * @return the range of h r asset vendors
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRAssetVendor> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r asset vendors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r asset vendors
	 * @param end the upper bound of the range of h r asset vendors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r asset vendors
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRAssetVendor> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRAssetVendor> list = (List<HRAssetVendor>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRASSETVENDOR);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRASSETVENDOR;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRAssetVendor>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRAssetVendor>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the h r asset vendors from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRAssetVendor hrAssetVendor : findAll()) {
			hrAssetVendorPersistence.remove(hrAssetVendor);
		}
	}

	/**
	 * Returns the number of h r asset vendors.
	 *
	 * @return the number of h r asset vendors
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

				Query q = session.createQuery(_SQL_COUNT_HRASSETVENDOR);

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
	 * Initializes the h r asset vendor persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRAssetVendor")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRAssetVendor>> listenersList = new ArrayList<ModelListener<HRAssetVendor>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRAssetVendor>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRAssetVendorImpl.class.getName());
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
	private static final String _SQL_SELECT_HRASSETVENDOR = "SELECT hrAssetVendor FROM HRAssetVendor hrAssetVendor";
	private static final String _SQL_COUNT_HRASSETVENDOR = "SELECT COUNT(hrAssetVendor) FROM HRAssetVendor hrAssetVendor";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrAssetVendor.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRAssetVendor exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRAssetVendorPersistenceImpl.class);
	private static HRAssetVendor _nullHRAssetVendor = new HRAssetVendorImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRAssetVendor> toCacheModel() {
				return _nullHRAssetVendorCacheModel;
			}
		};

	private static CacheModel<HRAssetVendor> _nullHRAssetVendorCacheModel = new CacheModel<HRAssetVendor>() {
			public HRAssetVendor toEntityModel() {
				return _nullHRAssetVendor;
			}
		};
}