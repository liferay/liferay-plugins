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

import com.liferay.hr.NoSuchTimeOffTypeException;
import com.liferay.hr.model.HRTimeOffType;
import com.liferay.hr.model.impl.HRTimeOffTypeImpl;
import com.liferay.hr.model.impl.HRTimeOffTypeModelImpl;

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
 * The persistence implementation for the h r time off type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRTimeOffTypePersistence
 * @see HRTimeOffTypeUtil
 * @generated
 */
public class HRTimeOffTypePersistenceImpl extends BasePersistenceImpl<HRTimeOffType>
	implements HRTimeOffTypePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRTimeOffTypeUtil} to access the h r time off type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRTimeOffTypeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRTimeOffTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeOffTypeModelImpl.FINDER_CACHE_ENABLED,
			HRTimeOffTypeImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRTimeOffTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeOffTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r time off type in the entity cache if it is enabled.
	 *
	 * @param hrTimeOffType the h r time off type
	 */
	public void cacheResult(HRTimeOffType hrTimeOffType) {
		EntityCacheUtil.putResult(HRTimeOffTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeOffTypeImpl.class, hrTimeOffType.getPrimaryKey(),
			hrTimeOffType);

		hrTimeOffType.resetOriginalValues();
	}

	/**
	 * Caches the h r time off types in the entity cache if it is enabled.
	 *
	 * @param hrTimeOffTypes the h r time off types
	 */
	public void cacheResult(List<HRTimeOffType> hrTimeOffTypes) {
		for (HRTimeOffType hrTimeOffType : hrTimeOffTypes) {
			if (EntityCacheUtil.getResult(
						HRTimeOffTypeModelImpl.ENTITY_CACHE_ENABLED,
						HRTimeOffTypeImpl.class, hrTimeOffType.getPrimaryKey(),
						this) == null) {
				cacheResult(hrTimeOffType);
			}
		}
	}

	/**
	 * Clears the cache for all h r time off types.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRTimeOffTypeImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRTimeOffTypeImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r time off type.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRTimeOffType hrTimeOffType) {
		EntityCacheUtil.removeResult(HRTimeOffTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeOffTypeImpl.class, hrTimeOffType.getPrimaryKey());
	}

	/**
	 * Creates a new h r time off type with the primary key. Does not add the h r time off type to the database.
	 *
	 * @param hrTimeOffTypeId the primary key for the new h r time off type
	 * @return the new h r time off type
	 */
	public HRTimeOffType create(long hrTimeOffTypeId) {
		HRTimeOffType hrTimeOffType = new HRTimeOffTypeImpl();

		hrTimeOffType.setNew(true);
		hrTimeOffType.setPrimaryKey(hrTimeOffTypeId);

		return hrTimeOffType;
	}

	/**
	 * Removes the h r time off type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r time off type
	 * @return the h r time off type that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r time off type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTimeOffType remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r time off type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrTimeOffTypeId the primary key of the h r time off type
	 * @return the h r time off type that was removed
	 * @throws com.liferay.hr.NoSuchTimeOffTypeException if a h r time off type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTimeOffType remove(long hrTimeOffTypeId)
		throws NoSuchTimeOffTypeException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRTimeOffType hrTimeOffType = (HRTimeOffType)session.get(HRTimeOffTypeImpl.class,
					Long.valueOf(hrTimeOffTypeId));

			if (hrTimeOffType == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						hrTimeOffTypeId);
				}

				throw new NoSuchTimeOffTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrTimeOffTypeId);
			}

			return hrTimeOffTypePersistence.remove(hrTimeOffType);
		}
		catch (NoSuchTimeOffTypeException nsee) {
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
	 * Removes the h r time off type from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrTimeOffType the h r time off type
	 * @return the h r time off type that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTimeOffType remove(HRTimeOffType hrTimeOffType)
		throws SystemException {
		return super.remove(hrTimeOffType);
	}

	@Override
	protected HRTimeOffType removeImpl(HRTimeOffType hrTimeOffType)
		throws SystemException {
		hrTimeOffType = toUnwrappedModel(hrTimeOffType);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrTimeOffType);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRTimeOffTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeOffTypeImpl.class, hrTimeOffType.getPrimaryKey());

		return hrTimeOffType;
	}

	@Override
	public HRTimeOffType updateImpl(
		com.liferay.hr.model.HRTimeOffType hrTimeOffType, boolean merge)
		throws SystemException {
		hrTimeOffType = toUnwrappedModel(hrTimeOffType);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrTimeOffType, merge);

			hrTimeOffType.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRTimeOffTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeOffTypeImpl.class, hrTimeOffType.getPrimaryKey(),
			hrTimeOffType);

		return hrTimeOffType;
	}

	protected HRTimeOffType toUnwrappedModel(HRTimeOffType hrTimeOffType) {
		if (hrTimeOffType instanceof HRTimeOffTypeImpl) {
			return hrTimeOffType;
		}

		HRTimeOffTypeImpl hrTimeOffTypeImpl = new HRTimeOffTypeImpl();

		hrTimeOffTypeImpl.setNew(hrTimeOffType.isNew());
		hrTimeOffTypeImpl.setPrimaryKey(hrTimeOffType.getPrimaryKey());

		hrTimeOffTypeImpl.setHrTimeOffTypeId(hrTimeOffType.getHrTimeOffTypeId());
		hrTimeOffTypeImpl.setGroupId(hrTimeOffType.getGroupId());
		hrTimeOffTypeImpl.setCompanyId(hrTimeOffType.getCompanyId());
		hrTimeOffTypeImpl.setUserId(hrTimeOffType.getUserId());
		hrTimeOffTypeImpl.setUserName(hrTimeOffType.getUserName());
		hrTimeOffTypeImpl.setCreateDate(hrTimeOffType.getCreateDate());
		hrTimeOffTypeImpl.setModifiedDate(hrTimeOffType.getModifiedDate());
		hrTimeOffTypeImpl.setName(hrTimeOffType.getName());
		hrTimeOffTypeImpl.setDescription(hrTimeOffType.getDescription());

		return hrTimeOffTypeImpl;
	}

	/**
	 * Returns the h r time off type with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r time off type
	 * @return the h r time off type
	 * @throws com.liferay.portal.NoSuchModelException if a h r time off type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTimeOffType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r time off type with the primary key or throws a {@link com.liferay.hr.NoSuchTimeOffTypeException} if it could not be found.
	 *
	 * @param hrTimeOffTypeId the primary key of the h r time off type
	 * @return the h r time off type
	 * @throws com.liferay.hr.NoSuchTimeOffTypeException if a h r time off type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTimeOffType findByPrimaryKey(long hrTimeOffTypeId)
		throws NoSuchTimeOffTypeException, SystemException {
		HRTimeOffType hrTimeOffType = fetchByPrimaryKey(hrTimeOffTypeId);

		if (hrTimeOffType == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrTimeOffTypeId);
			}

			throw new NoSuchTimeOffTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrTimeOffTypeId);
		}

		return hrTimeOffType;
	}

	/**
	 * Returns the h r time off type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r time off type
	 * @return the h r time off type, or <code>null</code> if a h r time off type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTimeOffType fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r time off type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrTimeOffTypeId the primary key of the h r time off type
	 * @return the h r time off type, or <code>null</code> if a h r time off type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTimeOffType fetchByPrimaryKey(long hrTimeOffTypeId)
		throws SystemException {
		HRTimeOffType hrTimeOffType = (HRTimeOffType)EntityCacheUtil.getResult(HRTimeOffTypeModelImpl.ENTITY_CACHE_ENABLED,
				HRTimeOffTypeImpl.class, hrTimeOffTypeId, this);

		if (hrTimeOffType == _nullHRTimeOffType) {
			return null;
		}

		if (hrTimeOffType == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrTimeOffType = (HRTimeOffType)session.get(HRTimeOffTypeImpl.class,
						Long.valueOf(hrTimeOffTypeId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrTimeOffType != null) {
					cacheResult(hrTimeOffType);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRTimeOffTypeModelImpl.ENTITY_CACHE_ENABLED,
						HRTimeOffTypeImpl.class, hrTimeOffTypeId,
						_nullHRTimeOffType);
				}

				closeSession(session);
			}
		}

		return hrTimeOffType;
	}

	/**
	 * Returns all the h r time off types.
	 *
	 * @return the h r time off types
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRTimeOffType> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r time off types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r time off types
	 * @param end the upper bound of the range of h r time off types (not inclusive)
	 * @return the range of h r time off types
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRTimeOffType> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r time off types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r time off types
	 * @param end the upper bound of the range of h r time off types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r time off types
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRTimeOffType> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRTimeOffType> list = (List<HRTimeOffType>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRTIMEOFFTYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRTIMEOFFTYPE;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRTimeOffType>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRTimeOffType>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the h r time off types from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRTimeOffType hrTimeOffType : findAll()) {
			hrTimeOffTypePersistence.remove(hrTimeOffType);
		}
	}

	/**
	 * Returns the number of h r time off types.
	 *
	 * @return the number of h r time off types
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

				Query q = session.createQuery(_SQL_COUNT_HRTIMEOFFTYPE);

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
	 * Initializes the h r time off type persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRTimeOffType")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRTimeOffType>> listenersList = new ArrayList<ModelListener<HRTimeOffType>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRTimeOffType>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRTimeOffTypeImpl.class.getName());
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
	private static final String _SQL_SELECT_HRTIMEOFFTYPE = "SELECT hrTimeOffType FROM HRTimeOffType hrTimeOffType";
	private static final String _SQL_COUNT_HRTIMEOFFTYPE = "SELECT COUNT(hrTimeOffType) FROM HRTimeOffType hrTimeOffType";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrTimeOffType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRTimeOffType exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRTimeOffTypePersistenceImpl.class);
	private static HRTimeOffType _nullHRTimeOffType = new HRTimeOffTypeImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRTimeOffType> toCacheModel() {
				return _nullHRTimeOffTypeCacheModel;
			}
		};

	private static CacheModel<HRTimeOffType> _nullHRTimeOffTypeCacheModel = new CacheModel<HRTimeOffType>() {
			public HRTimeOffType toEntityModel() {
				return _nullHRTimeOffType;
			}
		};
}