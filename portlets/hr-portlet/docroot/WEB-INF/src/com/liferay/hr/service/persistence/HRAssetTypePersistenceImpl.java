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

import com.liferay.hr.NoSuchAssetTypeException;
import com.liferay.hr.model.HRAssetType;
import com.liferay.hr.model.impl.HRAssetTypeImpl;
import com.liferay.hr.model.impl.HRAssetTypeModelImpl;

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
 * The persistence implementation for the h r asset type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRAssetTypePersistence
 * @see HRAssetTypeUtil
 * @generated
 */
public class HRAssetTypePersistenceImpl extends BasePersistenceImpl<HRAssetType>
	implements HRAssetTypePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRAssetTypeUtil} to access the h r asset type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRAssetTypeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRAssetTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetTypeModelImpl.FINDER_CACHE_ENABLED, HRAssetTypeImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRAssetTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r asset type in the entity cache if it is enabled.
	 *
	 * @param hrAssetType the h r asset type
	 */
	public void cacheResult(HRAssetType hrAssetType) {
		EntityCacheUtil.putResult(HRAssetTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetTypeImpl.class, hrAssetType.getPrimaryKey(), hrAssetType);

		hrAssetType.resetOriginalValues();
	}

	/**
	 * Caches the h r asset types in the entity cache if it is enabled.
	 *
	 * @param hrAssetTypes the h r asset types
	 */
	public void cacheResult(List<HRAssetType> hrAssetTypes) {
		for (HRAssetType hrAssetType : hrAssetTypes) {
			if (EntityCacheUtil.getResult(
						HRAssetTypeModelImpl.ENTITY_CACHE_ENABLED,
						HRAssetTypeImpl.class, hrAssetType.getPrimaryKey(), this) == null) {
				cacheResult(hrAssetType);
			}
		}
	}

	/**
	 * Clears the cache for all h r asset types.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRAssetTypeImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRAssetTypeImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r asset type.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRAssetType hrAssetType) {
		EntityCacheUtil.removeResult(HRAssetTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetTypeImpl.class, hrAssetType.getPrimaryKey());
	}

	/**
	 * Creates a new h r asset type with the primary key. Does not add the h r asset type to the database.
	 *
	 * @param hrAssetTypeId the primary key for the new h r asset type
	 * @return the new h r asset type
	 */
	public HRAssetType create(long hrAssetTypeId) {
		HRAssetType hrAssetType = new HRAssetTypeImpl();

		hrAssetType.setNew(true);
		hrAssetType.setPrimaryKey(hrAssetTypeId);

		return hrAssetType;
	}

	/**
	 * Removes the h r asset type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r asset type
	 * @return the h r asset type that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r asset type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRAssetType remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r asset type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrAssetTypeId the primary key of the h r asset type
	 * @return the h r asset type that was removed
	 * @throws com.liferay.hr.NoSuchAssetTypeException if a h r asset type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetType remove(long hrAssetTypeId)
		throws NoSuchAssetTypeException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRAssetType hrAssetType = (HRAssetType)session.get(HRAssetTypeImpl.class,
					Long.valueOf(hrAssetTypeId));

			if (hrAssetType == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrAssetTypeId);
				}

				throw new NoSuchAssetTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrAssetTypeId);
			}

			return hrAssetTypePersistence.remove(hrAssetType);
		}
		catch (NoSuchAssetTypeException nsee) {
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
	 * Removes the h r asset type from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrAssetType the h r asset type
	 * @return the h r asset type that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRAssetType remove(HRAssetType hrAssetType)
		throws SystemException {
		return super.remove(hrAssetType);
	}

	@Override
	protected HRAssetType removeImpl(HRAssetType hrAssetType)
		throws SystemException {
		hrAssetType = toUnwrappedModel(hrAssetType);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrAssetType);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRAssetTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetTypeImpl.class, hrAssetType.getPrimaryKey());

		return hrAssetType;
	}

	@Override
	public HRAssetType updateImpl(
		com.liferay.hr.model.HRAssetType hrAssetType, boolean merge)
		throws SystemException {
		hrAssetType = toUnwrappedModel(hrAssetType);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrAssetType, merge);

			hrAssetType.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRAssetTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetTypeImpl.class, hrAssetType.getPrimaryKey(), hrAssetType);

		return hrAssetType;
	}

	protected HRAssetType toUnwrappedModel(HRAssetType hrAssetType) {
		if (hrAssetType instanceof HRAssetTypeImpl) {
			return hrAssetType;
		}

		HRAssetTypeImpl hrAssetTypeImpl = new HRAssetTypeImpl();

		hrAssetTypeImpl.setNew(hrAssetType.isNew());
		hrAssetTypeImpl.setPrimaryKey(hrAssetType.getPrimaryKey());

		hrAssetTypeImpl.setHrAssetTypeId(hrAssetType.getHrAssetTypeId());
		hrAssetTypeImpl.setGroupId(hrAssetType.getGroupId());
		hrAssetTypeImpl.setCompanyId(hrAssetType.getCompanyId());
		hrAssetTypeImpl.setUserId(hrAssetType.getUserId());
		hrAssetTypeImpl.setUserName(hrAssetType.getUserName());
		hrAssetTypeImpl.setCreateDate(hrAssetType.getCreateDate());
		hrAssetTypeImpl.setModifiedDate(hrAssetType.getModifiedDate());
		hrAssetTypeImpl.setName(hrAssetType.getName());
		hrAssetTypeImpl.setDescription(hrAssetType.getDescription());

		return hrAssetTypeImpl;
	}

	/**
	 * Returns the h r asset type with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r asset type
	 * @return the h r asset type
	 * @throws com.liferay.portal.NoSuchModelException if a h r asset type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRAssetType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r asset type with the primary key or throws a {@link com.liferay.hr.NoSuchAssetTypeException} if it could not be found.
	 *
	 * @param hrAssetTypeId the primary key of the h r asset type
	 * @return the h r asset type
	 * @throws com.liferay.hr.NoSuchAssetTypeException if a h r asset type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetType findByPrimaryKey(long hrAssetTypeId)
		throws NoSuchAssetTypeException, SystemException {
		HRAssetType hrAssetType = fetchByPrimaryKey(hrAssetTypeId);

		if (hrAssetType == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrAssetTypeId);
			}

			throw new NoSuchAssetTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrAssetTypeId);
		}

		return hrAssetType;
	}

	/**
	 * Returns the h r asset type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r asset type
	 * @return the h r asset type, or <code>null</code> if a h r asset type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRAssetType fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r asset type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrAssetTypeId the primary key of the h r asset type
	 * @return the h r asset type, or <code>null</code> if a h r asset type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetType fetchByPrimaryKey(long hrAssetTypeId)
		throws SystemException {
		HRAssetType hrAssetType = (HRAssetType)EntityCacheUtil.getResult(HRAssetTypeModelImpl.ENTITY_CACHE_ENABLED,
				HRAssetTypeImpl.class, hrAssetTypeId, this);

		if (hrAssetType == _nullHRAssetType) {
			return null;
		}

		if (hrAssetType == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrAssetType = (HRAssetType)session.get(HRAssetTypeImpl.class,
						Long.valueOf(hrAssetTypeId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrAssetType != null) {
					cacheResult(hrAssetType);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRAssetTypeModelImpl.ENTITY_CACHE_ENABLED,
						HRAssetTypeImpl.class, hrAssetTypeId, _nullHRAssetType);
				}

				closeSession(session);
			}
		}

		return hrAssetType;
	}

	/**
	 * Returns all the h r asset types.
	 *
	 * @return the h r asset types
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRAssetType> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r asset types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r asset types
	 * @param end the upper bound of the range of h r asset types (not inclusive)
	 * @return the range of h r asset types
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRAssetType> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r asset types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r asset types
	 * @param end the upper bound of the range of h r asset types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r asset types
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRAssetType> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRAssetType> list = (List<HRAssetType>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRASSETTYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRASSETTYPE;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRAssetType>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRAssetType>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the h r asset types from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRAssetType hrAssetType : findAll()) {
			hrAssetTypePersistence.remove(hrAssetType);
		}
	}

	/**
	 * Returns the number of h r asset types.
	 *
	 * @return the number of h r asset types
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

				Query q = session.createQuery(_SQL_COUNT_HRASSETTYPE);

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
	 * Initializes the h r asset type persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRAssetType")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRAssetType>> listenersList = new ArrayList<ModelListener<HRAssetType>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRAssetType>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRAssetTypeImpl.class.getName());
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
	private static final String _SQL_SELECT_HRASSETTYPE = "SELECT hrAssetType FROM HRAssetType hrAssetType";
	private static final String _SQL_COUNT_HRASSETTYPE = "SELECT COUNT(hrAssetType) FROM HRAssetType hrAssetType";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrAssetType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRAssetType exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRAssetTypePersistenceImpl.class);
	private static HRAssetType _nullHRAssetType = new HRAssetTypeImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRAssetType> toCacheModel() {
				return _nullHRAssetTypeCacheModel;
			}
		};

	private static CacheModel<HRAssetType> _nullHRAssetTypeCacheModel = new CacheModel<HRAssetType>() {
			public HRAssetType toEntityModel() {
				return _nullHRAssetType;
			}
		};
}