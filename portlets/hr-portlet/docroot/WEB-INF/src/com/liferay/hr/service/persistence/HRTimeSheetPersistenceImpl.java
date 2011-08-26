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

import com.liferay.hr.NoSuchTimeSheetException;
import com.liferay.hr.model.HRTimeSheet;
import com.liferay.hr.model.impl.HRTimeSheetImpl;
import com.liferay.hr.model.impl.HRTimeSheetModelImpl;

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
 * The persistence implementation for the h r time sheet service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRTimeSheetPersistence
 * @see HRTimeSheetUtil
 * @generated
 */
public class HRTimeSheetPersistenceImpl extends BasePersistenceImpl<HRTimeSheet>
	implements HRTimeSheetPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRTimeSheetUtil} to access the h r time sheet persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRTimeSheetImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRTimeSheetModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeSheetModelImpl.FINDER_CACHE_ENABLED, HRTimeSheetImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRTimeSheetModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeSheetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r time sheet in the entity cache if it is enabled.
	 *
	 * @param hrTimeSheet the h r time sheet
	 */
	public void cacheResult(HRTimeSheet hrTimeSheet) {
		EntityCacheUtil.putResult(HRTimeSheetModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeSheetImpl.class, hrTimeSheet.getPrimaryKey(), hrTimeSheet);

		hrTimeSheet.resetOriginalValues();
	}

	/**
	 * Caches the h r time sheets in the entity cache if it is enabled.
	 *
	 * @param hrTimeSheets the h r time sheets
	 */
	public void cacheResult(List<HRTimeSheet> hrTimeSheets) {
		for (HRTimeSheet hrTimeSheet : hrTimeSheets) {
			if (EntityCacheUtil.getResult(
						HRTimeSheetModelImpl.ENTITY_CACHE_ENABLED,
						HRTimeSheetImpl.class, hrTimeSheet.getPrimaryKey(), this) == null) {
				cacheResult(hrTimeSheet);
			}
		}
	}

	/**
	 * Clears the cache for all h r time sheets.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRTimeSheetImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRTimeSheetImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r time sheet.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRTimeSheet hrTimeSheet) {
		EntityCacheUtil.removeResult(HRTimeSheetModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeSheetImpl.class, hrTimeSheet.getPrimaryKey());
	}

	/**
	 * Creates a new h r time sheet with the primary key. Does not add the h r time sheet to the database.
	 *
	 * @param hrTimeSheetId the primary key for the new h r time sheet
	 * @return the new h r time sheet
	 */
	public HRTimeSheet create(long hrTimeSheetId) {
		HRTimeSheet hrTimeSheet = new HRTimeSheetImpl();

		hrTimeSheet.setNew(true);
		hrTimeSheet.setPrimaryKey(hrTimeSheetId);

		return hrTimeSheet;
	}

	/**
	 * Removes the h r time sheet with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r time sheet
	 * @return the h r time sheet that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r time sheet with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTimeSheet remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r time sheet with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrTimeSheetId the primary key of the h r time sheet
	 * @return the h r time sheet that was removed
	 * @throws com.liferay.hr.NoSuchTimeSheetException if a h r time sheet with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTimeSheet remove(long hrTimeSheetId)
		throws NoSuchTimeSheetException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRTimeSheet hrTimeSheet = (HRTimeSheet)session.get(HRTimeSheetImpl.class,
					Long.valueOf(hrTimeSheetId));

			if (hrTimeSheet == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrTimeSheetId);
				}

				throw new NoSuchTimeSheetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrTimeSheetId);
			}

			return hrTimeSheetPersistence.remove(hrTimeSheet);
		}
		catch (NoSuchTimeSheetException nsee) {
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
	 * Removes the h r time sheet from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrTimeSheet the h r time sheet
	 * @return the h r time sheet that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTimeSheet remove(HRTimeSheet hrTimeSheet)
		throws SystemException {
		return super.remove(hrTimeSheet);
	}

	@Override
	protected HRTimeSheet removeImpl(HRTimeSheet hrTimeSheet)
		throws SystemException {
		hrTimeSheet = toUnwrappedModel(hrTimeSheet);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrTimeSheet);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRTimeSheetModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeSheetImpl.class, hrTimeSheet.getPrimaryKey());

		return hrTimeSheet;
	}

	@Override
	public HRTimeSheet updateImpl(
		com.liferay.hr.model.HRTimeSheet hrTimeSheet, boolean merge)
		throws SystemException {
		hrTimeSheet = toUnwrappedModel(hrTimeSheet);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrTimeSheet, merge);

			hrTimeSheet.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRTimeSheetModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeSheetImpl.class, hrTimeSheet.getPrimaryKey(), hrTimeSheet);

		return hrTimeSheet;
	}

	protected HRTimeSheet toUnwrappedModel(HRTimeSheet hrTimeSheet) {
		if (hrTimeSheet instanceof HRTimeSheetImpl) {
			return hrTimeSheet;
		}

		HRTimeSheetImpl hrTimeSheetImpl = new HRTimeSheetImpl();

		hrTimeSheetImpl.setNew(hrTimeSheet.isNew());
		hrTimeSheetImpl.setPrimaryKey(hrTimeSheet.getPrimaryKey());

		hrTimeSheetImpl.setHrTimeSheetId(hrTimeSheet.getHrTimeSheetId());
		hrTimeSheetImpl.setGroupId(hrTimeSheet.getGroupId());
		hrTimeSheetImpl.setCompanyId(hrTimeSheet.getCompanyId());
		hrTimeSheetImpl.setUserId(hrTimeSheet.getUserId());
		hrTimeSheetImpl.setUserName(hrTimeSheet.getUserName());
		hrTimeSheetImpl.setCreateDate(hrTimeSheet.getCreateDate());
		hrTimeSheetImpl.setModifiedDate(hrTimeSheet.getModifiedDate());
		hrTimeSheetImpl.setHrUserId(hrTimeSheet.getHrUserId());
		hrTimeSheetImpl.setStartDayOfYear(hrTimeSheet.getStartDayOfYear());
		hrTimeSheetImpl.setEndDayOfYear(hrTimeSheet.getEndDayOfYear());
		hrTimeSheetImpl.setYear(hrTimeSheet.getYear());
		hrTimeSheetImpl.setStatus(hrTimeSheet.getStatus());
		hrTimeSheetImpl.setStatusByUserId(hrTimeSheet.getStatusByUserId());
		hrTimeSheetImpl.setStatusByUserName(hrTimeSheet.getStatusByUserName());
		hrTimeSheetImpl.setStatusDate(hrTimeSheet.getStatusDate());

		return hrTimeSheetImpl;
	}

	/**
	 * Returns the h r time sheet with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r time sheet
	 * @return the h r time sheet
	 * @throws com.liferay.portal.NoSuchModelException if a h r time sheet with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTimeSheet findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r time sheet with the primary key or throws a {@link com.liferay.hr.NoSuchTimeSheetException} if it could not be found.
	 *
	 * @param hrTimeSheetId the primary key of the h r time sheet
	 * @return the h r time sheet
	 * @throws com.liferay.hr.NoSuchTimeSheetException if a h r time sheet with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTimeSheet findByPrimaryKey(long hrTimeSheetId)
		throws NoSuchTimeSheetException, SystemException {
		HRTimeSheet hrTimeSheet = fetchByPrimaryKey(hrTimeSheetId);

		if (hrTimeSheet == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrTimeSheetId);
			}

			throw new NoSuchTimeSheetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrTimeSheetId);
		}

		return hrTimeSheet;
	}

	/**
	 * Returns the h r time sheet with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r time sheet
	 * @return the h r time sheet, or <code>null</code> if a h r time sheet with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTimeSheet fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r time sheet with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrTimeSheetId the primary key of the h r time sheet
	 * @return the h r time sheet, or <code>null</code> if a h r time sheet with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTimeSheet fetchByPrimaryKey(long hrTimeSheetId)
		throws SystemException {
		HRTimeSheet hrTimeSheet = (HRTimeSheet)EntityCacheUtil.getResult(HRTimeSheetModelImpl.ENTITY_CACHE_ENABLED,
				HRTimeSheetImpl.class, hrTimeSheetId, this);

		if (hrTimeSheet == _nullHRTimeSheet) {
			return null;
		}

		if (hrTimeSheet == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrTimeSheet = (HRTimeSheet)session.get(HRTimeSheetImpl.class,
						Long.valueOf(hrTimeSheetId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrTimeSheet != null) {
					cacheResult(hrTimeSheet);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRTimeSheetModelImpl.ENTITY_CACHE_ENABLED,
						HRTimeSheetImpl.class, hrTimeSheetId, _nullHRTimeSheet);
				}

				closeSession(session);
			}
		}

		return hrTimeSheet;
	}

	/**
	 * Returns all the h r time sheets.
	 *
	 * @return the h r time sheets
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRTimeSheet> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r time sheets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r time sheets
	 * @param end the upper bound of the range of h r time sheets (not inclusive)
	 * @return the range of h r time sheets
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRTimeSheet> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r time sheets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r time sheets
	 * @param end the upper bound of the range of h r time sheets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r time sheets
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRTimeSheet> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRTimeSheet> list = (List<HRTimeSheet>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRTIMESHEET);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRTIMESHEET;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRTimeSheet>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRTimeSheet>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the h r time sheets from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRTimeSheet hrTimeSheet : findAll()) {
			hrTimeSheetPersistence.remove(hrTimeSheet);
		}
	}

	/**
	 * Returns the number of h r time sheets.
	 *
	 * @return the number of h r time sheets
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

				Query q = session.createQuery(_SQL_COUNT_HRTIMESHEET);

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
	 * Initializes the h r time sheet persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRTimeSheet")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRTimeSheet>> listenersList = new ArrayList<ModelListener<HRTimeSheet>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRTimeSheet>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRTimeSheetImpl.class.getName());
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
	private static final String _SQL_SELECT_HRTIMESHEET = "SELECT hrTimeSheet FROM HRTimeSheet hrTimeSheet";
	private static final String _SQL_COUNT_HRTIMESHEET = "SELECT COUNT(hrTimeSheet) FROM HRTimeSheet hrTimeSheet";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrTimeSheet.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRTimeSheet exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRTimeSheetPersistenceImpl.class);
	private static HRTimeSheet _nullHRTimeSheet = new HRTimeSheetImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRTimeSheet> toCacheModel() {
				return _nullHRTimeSheetCacheModel;
			}
		};

	private static CacheModel<HRTimeSheet> _nullHRTimeSheetCacheModel = new CacheModel<HRTimeSheet>() {
			public HRTimeSheet toEntityModel() {
				return _nullHRTimeSheet;
			}
		};
}