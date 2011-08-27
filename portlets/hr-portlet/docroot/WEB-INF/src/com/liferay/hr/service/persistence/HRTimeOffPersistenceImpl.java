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

import com.liferay.hr.NoSuchTimeOffException;
import com.liferay.hr.model.HRTimeOff;
import com.liferay.hr.model.impl.HRTimeOffImpl;
import com.liferay.hr.model.impl.HRTimeOffModelImpl;

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
 * The persistence implementation for the h r time off service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRTimeOffPersistence
 * @see HRTimeOffUtil
 * @generated
 */
public class HRTimeOffPersistenceImpl extends BasePersistenceImpl<HRTimeOff>
	implements HRTimeOffPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRTimeOffUtil} to access the h r time off persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRTimeOffImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRTimeOffModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeOffModelImpl.FINDER_CACHE_ENABLED, HRTimeOffImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRTimeOffModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeOffModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r time off in the entity cache if it is enabled.
	 *
	 * @param hrTimeOff the h r time off
	 */
	public void cacheResult(HRTimeOff hrTimeOff) {
		EntityCacheUtil.putResult(HRTimeOffModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeOffImpl.class, hrTimeOff.getPrimaryKey(), hrTimeOff);

		hrTimeOff.resetOriginalValues();
	}

	/**
	 * Caches the h r time offs in the entity cache if it is enabled.
	 *
	 * @param hrTimeOffs the h r time offs
	 */
	public void cacheResult(List<HRTimeOff> hrTimeOffs) {
		for (HRTimeOff hrTimeOff : hrTimeOffs) {
			if (EntityCacheUtil.getResult(
						HRTimeOffModelImpl.ENTITY_CACHE_ENABLED,
						HRTimeOffImpl.class, hrTimeOff.getPrimaryKey(), this) == null) {
				cacheResult(hrTimeOff);
			}
		}
	}

	/**
	 * Clears the cache for all h r time offs.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRTimeOffImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRTimeOffImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r time off.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRTimeOff hrTimeOff) {
		EntityCacheUtil.removeResult(HRTimeOffModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeOffImpl.class, hrTimeOff.getPrimaryKey());
	}

	/**
	 * Creates a new h r time off with the primary key. Does not add the h r time off to the database.
	 *
	 * @param hrTimeOffId the primary key for the new h r time off
	 * @return the new h r time off
	 */
	public HRTimeOff create(long hrTimeOffId) {
		HRTimeOff hrTimeOff = new HRTimeOffImpl();

		hrTimeOff.setNew(true);
		hrTimeOff.setPrimaryKey(hrTimeOffId);

		return hrTimeOff;
	}

	/**
	 * Removes the h r time off with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r time off
	 * @return the h r time off that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r time off with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTimeOff remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r time off with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrTimeOffId the primary key of the h r time off
	 * @return the h r time off that was removed
	 * @throws com.liferay.hr.NoSuchTimeOffException if a h r time off with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTimeOff remove(long hrTimeOffId)
		throws NoSuchTimeOffException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRTimeOff hrTimeOff = (HRTimeOff)session.get(HRTimeOffImpl.class,
					Long.valueOf(hrTimeOffId));

			if (hrTimeOff == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrTimeOffId);
				}

				throw new NoSuchTimeOffException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrTimeOffId);
			}

			return hrTimeOffPersistence.remove(hrTimeOff);
		}
		catch (NoSuchTimeOffException nsee) {
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
	 * Removes the h r time off from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrTimeOff the h r time off
	 * @return the h r time off that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTimeOff remove(HRTimeOff hrTimeOff) throws SystemException {
		return super.remove(hrTimeOff);
	}

	@Override
	protected HRTimeOff removeImpl(HRTimeOff hrTimeOff)
		throws SystemException {
		hrTimeOff = toUnwrappedModel(hrTimeOff);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrTimeOff);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRTimeOffModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeOffImpl.class, hrTimeOff.getPrimaryKey());

		return hrTimeOff;
	}

	@Override
	public HRTimeOff updateImpl(com.liferay.hr.model.HRTimeOff hrTimeOff,
		boolean merge) throws SystemException {
		hrTimeOff = toUnwrappedModel(hrTimeOff);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrTimeOff, merge);

			hrTimeOff.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRTimeOffModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeOffImpl.class, hrTimeOff.getPrimaryKey(), hrTimeOff);

		return hrTimeOff;
	}

	protected HRTimeOff toUnwrappedModel(HRTimeOff hrTimeOff) {
		if (hrTimeOff instanceof HRTimeOffImpl) {
			return hrTimeOff;
		}

		HRTimeOffImpl hrTimeOffImpl = new HRTimeOffImpl();

		hrTimeOffImpl.setNew(hrTimeOff.isNew());
		hrTimeOffImpl.setPrimaryKey(hrTimeOff.getPrimaryKey());

		hrTimeOffImpl.setHrTimeOffId(hrTimeOff.getHrTimeOffId());
		hrTimeOffImpl.setGroupId(hrTimeOff.getGroupId());
		hrTimeOffImpl.setCompanyId(hrTimeOff.getCompanyId());
		hrTimeOffImpl.setUserId(hrTimeOff.getUserId());
		hrTimeOffImpl.setUserName(hrTimeOff.getUserName());
		hrTimeOffImpl.setCreateDate(hrTimeOff.getCreateDate());
		hrTimeOffImpl.setModifiedDate(hrTimeOff.getModifiedDate());
		hrTimeOffImpl.setHrTimeOffTypeId(hrTimeOff.getHrTimeOffTypeId());
		hrTimeOffImpl.setHrTimeSheetId(hrTimeOff.getHrTimeSheetId());
		hrTimeOffImpl.setHrUserId(hrTimeOff.getHrUserId());
		hrTimeOffImpl.setStartDayOfYear(hrTimeOff.getStartDayOfYear());
		hrTimeOffImpl.setEndDayOfYear(hrTimeOff.getEndDayOfYear());
		hrTimeOffImpl.setYear(hrTimeOff.getYear());
		hrTimeOffImpl.setTotalHours(hrTimeOff.getTotalHours());
		hrTimeOffImpl.setStatus(hrTimeOff.getStatus());
		hrTimeOffImpl.setStatusByUserId(hrTimeOff.getStatusByUserId());
		hrTimeOffImpl.setStatusByUserName(hrTimeOff.getStatusByUserName());
		hrTimeOffImpl.setStatusDate(hrTimeOff.getStatusDate());

		return hrTimeOffImpl;
	}

	/**
	 * Returns the h r time off with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r time off
	 * @return the h r time off
	 * @throws com.liferay.portal.NoSuchModelException if a h r time off with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTimeOff findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r time off with the primary key or throws a {@link com.liferay.hr.NoSuchTimeOffException} if it could not be found.
	 *
	 * @param hrTimeOffId the primary key of the h r time off
	 * @return the h r time off
	 * @throws com.liferay.hr.NoSuchTimeOffException if a h r time off with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTimeOff findByPrimaryKey(long hrTimeOffId)
		throws NoSuchTimeOffException, SystemException {
		HRTimeOff hrTimeOff = fetchByPrimaryKey(hrTimeOffId);

		if (hrTimeOff == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrTimeOffId);
			}

			throw new NoSuchTimeOffException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrTimeOffId);
		}

		return hrTimeOff;
	}

	/**
	 * Returns the h r time off with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r time off
	 * @return the h r time off, or <code>null</code> if a h r time off with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTimeOff fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r time off with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrTimeOffId the primary key of the h r time off
	 * @return the h r time off, or <code>null</code> if a h r time off with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTimeOff fetchByPrimaryKey(long hrTimeOffId)
		throws SystemException {
		HRTimeOff hrTimeOff = (HRTimeOff)EntityCacheUtil.getResult(HRTimeOffModelImpl.ENTITY_CACHE_ENABLED,
				HRTimeOffImpl.class, hrTimeOffId, this);

		if (hrTimeOff == _nullHRTimeOff) {
			return null;
		}

		if (hrTimeOff == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrTimeOff = (HRTimeOff)session.get(HRTimeOffImpl.class,
						Long.valueOf(hrTimeOffId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrTimeOff != null) {
					cacheResult(hrTimeOff);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRTimeOffModelImpl.ENTITY_CACHE_ENABLED,
						HRTimeOffImpl.class, hrTimeOffId, _nullHRTimeOff);
				}

				closeSession(session);
			}
		}

		return hrTimeOff;
	}

	/**
	 * Returns all the h r time offs.
	 *
	 * @return the h r time offs
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRTimeOff> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r time offs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r time offs
	 * @param end the upper bound of the range of h r time offs (not inclusive)
	 * @return the range of h r time offs
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRTimeOff> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r time offs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r time offs
	 * @param end the upper bound of the range of h r time offs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r time offs
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRTimeOff> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRTimeOff> list = (List<HRTimeOff>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRTIMEOFF);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRTIMEOFF;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRTimeOff>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRTimeOff>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the h r time offs from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRTimeOff hrTimeOff : findAll()) {
			hrTimeOffPersistence.remove(hrTimeOff);
		}
	}

	/**
	 * Returns the number of h r time offs.
	 *
	 * @return the number of h r time offs
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

				Query q = session.createQuery(_SQL_COUNT_HRTIMEOFF);

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
	 * Initializes the h r time off persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRTimeOff")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRTimeOff>> listenersList = new ArrayList<ModelListener<HRTimeOff>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRTimeOff>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRTimeOffImpl.class.getName());
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
	private static final String _SQL_SELECT_HRTIMEOFF = "SELECT hrTimeOff FROM HRTimeOff hrTimeOff";
	private static final String _SQL_COUNT_HRTIMEOFF = "SELECT COUNT(hrTimeOff) FROM HRTimeOff hrTimeOff";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrTimeOff.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRTimeOff exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRTimeOffPersistenceImpl.class);
	private static HRTimeOff _nullHRTimeOff = new HRTimeOffImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRTimeOff> toCacheModel() {
				return _nullHRTimeOffCacheModel;
			}
		};

	private static CacheModel<HRTimeOff> _nullHRTimeOffCacheModel = new CacheModel<HRTimeOff>() {
			public HRTimeOff toEntityModel() {
				return _nullHRTimeOff;
			}
		};
}