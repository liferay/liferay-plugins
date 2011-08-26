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

import com.liferay.hr.NoSuchUserTimeOffException;
import com.liferay.hr.model.HRUserTimeOff;
import com.liferay.hr.model.impl.HRUserTimeOffImpl;
import com.liferay.hr.model.impl.HRUserTimeOffModelImpl;

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
 * The persistence implementation for the h r user time off service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRUserTimeOffPersistence
 * @see HRUserTimeOffUtil
 * @generated
 */
public class HRUserTimeOffPersistenceImpl extends BasePersistenceImpl<HRUserTimeOff>
	implements HRUserTimeOffPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRUserTimeOffUtil} to access the h r user time off persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRUserTimeOffImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRUserTimeOffModelImpl.ENTITY_CACHE_ENABLED,
			HRUserTimeOffModelImpl.FINDER_CACHE_ENABLED,
			HRUserTimeOffImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRUserTimeOffModelImpl.ENTITY_CACHE_ENABLED,
			HRUserTimeOffModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r user time off in the entity cache if it is enabled.
	 *
	 * @param hrUserTimeOff the h r user time off
	 */
	public void cacheResult(HRUserTimeOff hrUserTimeOff) {
		EntityCacheUtil.putResult(HRUserTimeOffModelImpl.ENTITY_CACHE_ENABLED,
			HRUserTimeOffImpl.class, hrUserTimeOff.getPrimaryKey(),
			hrUserTimeOff);

		hrUserTimeOff.resetOriginalValues();
	}

	/**
	 * Caches the h r user time offs in the entity cache if it is enabled.
	 *
	 * @param hrUserTimeOffs the h r user time offs
	 */
	public void cacheResult(List<HRUserTimeOff> hrUserTimeOffs) {
		for (HRUserTimeOff hrUserTimeOff : hrUserTimeOffs) {
			if (EntityCacheUtil.getResult(
						HRUserTimeOffModelImpl.ENTITY_CACHE_ENABLED,
						HRUserTimeOffImpl.class, hrUserTimeOff.getPrimaryKey(),
						this) == null) {
				cacheResult(hrUserTimeOff);
			}
		}
	}

	/**
	 * Clears the cache for all h r user time offs.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRUserTimeOffImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRUserTimeOffImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r user time off.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRUserTimeOff hrUserTimeOff) {
		EntityCacheUtil.removeResult(HRUserTimeOffModelImpl.ENTITY_CACHE_ENABLED,
			HRUserTimeOffImpl.class, hrUserTimeOff.getPrimaryKey());
	}

	/**
	 * Creates a new h r user time off with the primary key. Does not add the h r user time off to the database.
	 *
	 * @param hrUserTimeOffId the primary key for the new h r user time off
	 * @return the new h r user time off
	 */
	public HRUserTimeOff create(long hrUserTimeOffId) {
		HRUserTimeOff hrUserTimeOff = new HRUserTimeOffImpl();

		hrUserTimeOff.setNew(true);
		hrUserTimeOff.setPrimaryKey(hrUserTimeOffId);

		return hrUserTimeOff;
	}

	/**
	 * Removes the h r user time off with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r user time off
	 * @return the h r user time off that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r user time off with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRUserTimeOff remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r user time off with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrUserTimeOffId the primary key of the h r user time off
	 * @return the h r user time off that was removed
	 * @throws com.liferay.hr.NoSuchUserTimeOffException if a h r user time off with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRUserTimeOff remove(long hrUserTimeOffId)
		throws NoSuchUserTimeOffException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRUserTimeOff hrUserTimeOff = (HRUserTimeOff)session.get(HRUserTimeOffImpl.class,
					Long.valueOf(hrUserTimeOffId));

			if (hrUserTimeOff == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						hrUserTimeOffId);
				}

				throw new NoSuchUserTimeOffException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrUserTimeOffId);
			}

			return hrUserTimeOffPersistence.remove(hrUserTimeOff);
		}
		catch (NoSuchUserTimeOffException nsee) {
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
	 * Removes the h r user time off from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrUserTimeOff the h r user time off
	 * @return the h r user time off that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRUserTimeOff remove(HRUserTimeOff hrUserTimeOff)
		throws SystemException {
		return super.remove(hrUserTimeOff);
	}

	@Override
	protected HRUserTimeOff removeImpl(HRUserTimeOff hrUserTimeOff)
		throws SystemException {
		hrUserTimeOff = toUnwrappedModel(hrUserTimeOff);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrUserTimeOff);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRUserTimeOffModelImpl.ENTITY_CACHE_ENABLED,
			HRUserTimeOffImpl.class, hrUserTimeOff.getPrimaryKey());

		return hrUserTimeOff;
	}

	@Override
	public HRUserTimeOff updateImpl(
		com.liferay.hr.model.HRUserTimeOff hrUserTimeOff, boolean merge)
		throws SystemException {
		hrUserTimeOff = toUnwrappedModel(hrUserTimeOff);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrUserTimeOff, merge);

			hrUserTimeOff.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRUserTimeOffModelImpl.ENTITY_CACHE_ENABLED,
			HRUserTimeOffImpl.class, hrUserTimeOff.getPrimaryKey(),
			hrUserTimeOff);

		return hrUserTimeOff;
	}

	protected HRUserTimeOff toUnwrappedModel(HRUserTimeOff hrUserTimeOff) {
		if (hrUserTimeOff instanceof HRUserTimeOffImpl) {
			return hrUserTimeOff;
		}

		HRUserTimeOffImpl hrUserTimeOffImpl = new HRUserTimeOffImpl();

		hrUserTimeOffImpl.setNew(hrUserTimeOff.isNew());
		hrUserTimeOffImpl.setPrimaryKey(hrUserTimeOff.getPrimaryKey());

		hrUserTimeOffImpl.setHrUserTimeOffId(hrUserTimeOff.getHrUserTimeOffId());
		hrUserTimeOffImpl.setGroupId(hrUserTimeOff.getGroupId());
		hrUserTimeOffImpl.setCompanyId(hrUserTimeOff.getCompanyId());
		hrUserTimeOffImpl.setUserId(hrUserTimeOff.getUserId());
		hrUserTimeOffImpl.setUserName(hrUserTimeOff.getUserName());
		hrUserTimeOffImpl.setCreateDate(hrUserTimeOff.getCreateDate());
		hrUserTimeOffImpl.setModifiedDate(hrUserTimeOff.getModifiedDate());
		hrUserTimeOffImpl.setHrTimeOffTypeId(hrUserTimeOff.getHrTimeOffTypeId());
		hrUserTimeOffImpl.setHrUserId(hrUserTimeOff.getHrUserId());
		hrUserTimeOffImpl.setYear(hrUserTimeOff.getYear());
		hrUserTimeOffImpl.setHoursAllowed(hrUserTimeOff.getHoursAllowed());
		hrUserTimeOffImpl.setHoursAccrued(hrUserTimeOff.getHoursAccrued());
		hrUserTimeOffImpl.setHoursCarriedOver(hrUserTimeOff.getHoursCarriedOver());
		hrUserTimeOffImpl.setHoursUsed(hrUserTimeOff.getHoursUsed());

		return hrUserTimeOffImpl;
	}

	/**
	 * Returns the h r user time off with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r user time off
	 * @return the h r user time off
	 * @throws com.liferay.portal.NoSuchModelException if a h r user time off with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRUserTimeOff findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r user time off with the primary key or throws a {@link com.liferay.hr.NoSuchUserTimeOffException} if it could not be found.
	 *
	 * @param hrUserTimeOffId the primary key of the h r user time off
	 * @return the h r user time off
	 * @throws com.liferay.hr.NoSuchUserTimeOffException if a h r user time off with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRUserTimeOff findByPrimaryKey(long hrUserTimeOffId)
		throws NoSuchUserTimeOffException, SystemException {
		HRUserTimeOff hrUserTimeOff = fetchByPrimaryKey(hrUserTimeOffId);

		if (hrUserTimeOff == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrUserTimeOffId);
			}

			throw new NoSuchUserTimeOffException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrUserTimeOffId);
		}

		return hrUserTimeOff;
	}

	/**
	 * Returns the h r user time off with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r user time off
	 * @return the h r user time off, or <code>null</code> if a h r user time off with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRUserTimeOff fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r user time off with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrUserTimeOffId the primary key of the h r user time off
	 * @return the h r user time off, or <code>null</code> if a h r user time off with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRUserTimeOff fetchByPrimaryKey(long hrUserTimeOffId)
		throws SystemException {
		HRUserTimeOff hrUserTimeOff = (HRUserTimeOff)EntityCacheUtil.getResult(HRUserTimeOffModelImpl.ENTITY_CACHE_ENABLED,
				HRUserTimeOffImpl.class, hrUserTimeOffId, this);

		if (hrUserTimeOff == _nullHRUserTimeOff) {
			return null;
		}

		if (hrUserTimeOff == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrUserTimeOff = (HRUserTimeOff)session.get(HRUserTimeOffImpl.class,
						Long.valueOf(hrUserTimeOffId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrUserTimeOff != null) {
					cacheResult(hrUserTimeOff);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRUserTimeOffModelImpl.ENTITY_CACHE_ENABLED,
						HRUserTimeOffImpl.class, hrUserTimeOffId,
						_nullHRUserTimeOff);
				}

				closeSession(session);
			}
		}

		return hrUserTimeOff;
	}

	/**
	 * Returns all the h r user time offs.
	 *
	 * @return the h r user time offs
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRUserTimeOff> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r user time offs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r user time offs
	 * @param end the upper bound of the range of h r user time offs (not inclusive)
	 * @return the range of h r user time offs
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRUserTimeOff> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r user time offs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r user time offs
	 * @param end the upper bound of the range of h r user time offs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r user time offs
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRUserTimeOff> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRUserTimeOff> list = (List<HRUserTimeOff>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRUSERTIMEOFF);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRUSERTIMEOFF;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRUserTimeOff>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRUserTimeOff>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the h r user time offs from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRUserTimeOff hrUserTimeOff : findAll()) {
			hrUserTimeOffPersistence.remove(hrUserTimeOff);
		}
	}

	/**
	 * Returns the number of h r user time offs.
	 *
	 * @return the number of h r user time offs
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

				Query q = session.createQuery(_SQL_COUNT_HRUSERTIMEOFF);

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
	 * Initializes the h r user time off persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRUserTimeOff")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRUserTimeOff>> listenersList = new ArrayList<ModelListener<HRUserTimeOff>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRUserTimeOff>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRUserTimeOffImpl.class.getName());
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
	private static final String _SQL_SELECT_HRUSERTIMEOFF = "SELECT hrUserTimeOff FROM HRUserTimeOff hrUserTimeOff";
	private static final String _SQL_COUNT_HRUSERTIMEOFF = "SELECT COUNT(hrUserTimeOff) FROM HRUserTimeOff hrUserTimeOff";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrUserTimeOff.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRUserTimeOff exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRUserTimeOffPersistenceImpl.class);
	private static HRUserTimeOff _nullHRUserTimeOff = new HRUserTimeOffImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRUserTimeOff> toCacheModel() {
				return _nullHRUserTimeOffCacheModel;
			}
		};

	private static CacheModel<HRUserTimeOff> _nullHRUserTimeOffCacheModel = new CacheModel<HRUserTimeOff>() {
			public HRUserTimeOff toEntityModel() {
				return _nullHRUserTimeOff;
			}
		};
}