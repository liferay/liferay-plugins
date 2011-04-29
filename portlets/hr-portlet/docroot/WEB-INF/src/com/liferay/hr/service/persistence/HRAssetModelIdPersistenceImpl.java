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

import com.liferay.hr.NoSuchAssetModelIdException;
import com.liferay.hr.model.HRAssetModelId;
import com.liferay.hr.model.impl.HRAssetModelIdImpl;
import com.liferay.hr.model.impl.HRAssetModelIdModelImpl;

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
 * The persistence implementation for the h r asset model ID service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HRAssetModelIdPersistence
 * @see HRAssetModelIdUtil
 * @generated
 */
public class HRAssetModelIdPersistenceImpl extends BasePersistenceImpl<HRAssetModelId>
	implements HRAssetModelIdPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRAssetModelIdUtil} to access the h r asset model ID persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRAssetModelIdImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRAssetModelIdModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetModelIdModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRAssetModelIdModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetModelIdModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r asset model ID in the entity cache if it is enabled.
	 *
	 * @param hrAssetModelId the h r asset model ID to cache
	 */
	public void cacheResult(HRAssetModelId hrAssetModelId) {
		EntityCacheUtil.putResult(HRAssetModelIdModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetModelIdImpl.class, hrAssetModelId.getPrimaryKey(),
			hrAssetModelId);

		hrAssetModelId.resetOriginalValues();
	}

	/**
	 * Caches the h r asset model IDs in the entity cache if it is enabled.
	 *
	 * @param hrAssetModelIds the h r asset model IDs to cache
	 */
	public void cacheResult(List<HRAssetModelId> hrAssetModelIds) {
		for (HRAssetModelId hrAssetModelId : hrAssetModelIds) {
			if (EntityCacheUtil.getResult(
						HRAssetModelIdModelImpl.ENTITY_CACHE_ENABLED,
						HRAssetModelIdImpl.class,
						hrAssetModelId.getPrimaryKey(), this) == null) {
				cacheResult(hrAssetModelId);
			}
		}
	}

	/**
	 * Clears the cache for all h r asset model IDs.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRAssetModelIdImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRAssetModelIdImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r asset model ID.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(HRAssetModelId hrAssetModelId) {
		EntityCacheUtil.removeResult(HRAssetModelIdModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetModelIdImpl.class, hrAssetModelId.getPrimaryKey());
	}

	/**
	 * Creates a new h r asset model ID with the primary key. Does not add the h r asset model ID to the database.
	 *
	 * @param hrAssetModelId the primary key for the new h r asset model ID
	 * @return the new h r asset model ID
	 */
	public HRAssetModelId create(long hrAssetModelId) {
		HRAssetModelId hrAssetModelId = new HRAssetModelIdImpl();

		hrAssetModelId.setNew(true);
		hrAssetModelId.setPrimaryKey(hrAssetModelId);

		return hrAssetModelId;
	}

	/**
	 * Removes the h r asset model ID with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r asset model ID to remove
	 * @return the h r asset model ID that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r asset model ID with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetModelId remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r asset model ID with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrAssetModelId the primary key of the h r asset model ID to remove
	 * @return the h r asset model ID that was removed
	 * @throws com.liferay.hr.NoSuchAssetModelIdException if a h r asset model ID with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetModelId remove(long hrAssetModelId)
		throws NoSuchAssetModelIdException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRAssetModelId hrAssetModelId = (HRAssetModelId)session.get(HRAssetModelIdImpl.class,
					Long.valueOf(hrAssetModelId));

			if (hrAssetModelId == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						hrAssetModelId);
				}

				throw new NoSuchAssetModelIdException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrAssetModelId);
			}

			return hrAssetModelIdPersistence.remove(hrAssetModelId);
		}
		catch (NoSuchAssetModelIdException nsee) {
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
	 * Removes the h r asset model ID from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrAssetModelId the h r asset model ID to remove
	 * @return the h r asset model ID that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetModelId remove(HRAssetModelId hrAssetModelId)
		throws SystemException {
		return super.remove(hrAssetModelId);
	}

	protected HRAssetModelId removeImpl(HRAssetModelId hrAssetModelId)
		throws SystemException {
		hrAssetModelId = toUnwrappedModel(hrAssetModelId);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrAssetModelId);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRAssetModelIdModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetModelIdImpl.class, hrAssetModelId.getPrimaryKey());

		return hrAssetModelId;
	}

	public HRAssetModelId updateImpl(
		com.liferay.hr.model.HRAssetModelId hrAssetModelId, boolean merge)
		throws SystemException {
		hrAssetModelId = toUnwrappedModel(hrAssetModelId);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrAssetModelId, merge);

			hrAssetModelId.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRAssetModelIdModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetModelIdImpl.class, hrAssetModelId.getPrimaryKey(),
			hrAssetModelId);

		return hrAssetModelId;
	}

	protected HRAssetModelId toUnwrappedModel(HRAssetModelId hrAssetModelId) {
		if (hrAssetModelId instanceof HRAssetModelIdImpl) {
			return hrAssetModelId;
		}

		HRAssetModelIdImpl hrAssetModelIdImpl = new HRAssetModelIdImpl();

		hrAssetModelIdImpl.setNew(hrAssetModelId.isNew());
		hrAssetModelIdImpl.setPrimaryKey(hrAssetModelId.getPrimaryKey());

		hrAssetModelIdImpl.setHrAssetModelId(hrAssetModelId.getHrAssetModelId());
		hrAssetModelIdImpl.setGroupId(hrAssetModelId.getGroupId());
		hrAssetModelIdImpl.setCompanyId(hrAssetModelId.getCompanyId());
		hrAssetModelIdImpl.setUserId(hrAssetModelId.getUserId());
		hrAssetModelIdImpl.setUserName(hrAssetModelId.getUserName());
		hrAssetModelIdImpl.setCreateDate(hrAssetModelId.getCreateDate());
		hrAssetModelIdImpl.setModifiedDate(hrAssetModelId.getModifiedDate());
		hrAssetModelIdImpl.setHrAssetVendorId(hrAssetModelId.getHrAssetVendorId());
		hrAssetModelIdImpl.setName(hrAssetModelId.getName());
		hrAssetModelIdImpl.setDescription(hrAssetModelId.getDescription());

		return hrAssetModelIdImpl;
	}

	/**
	 * Finds the h r asset model ID with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r asset model ID to find
	 * @return the h r asset model ID
	 * @throws com.liferay.portal.NoSuchModelException if a h r asset model ID with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetModelId findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the h r asset model ID with the primary key or throws a {@link com.liferay.hr.NoSuchAssetModelIdException} if it could not be found.
	 *
	 * @param hrAssetModelId the primary key of the h r asset model ID to find
	 * @return the h r asset model ID
	 * @throws com.liferay.hr.NoSuchAssetModelIdException if a h r asset model ID with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetModelId findByPrimaryKey(long hrAssetModelId)
		throws NoSuchAssetModelIdException, SystemException {
		HRAssetModelId hrAssetModelId = fetchByPrimaryKey(hrAssetModelId);

		if (hrAssetModelId == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrAssetModelId);
			}

			throw new NoSuchAssetModelIdException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrAssetModelId);
		}

		return hrAssetModelId;
	}

	/**
	 * Finds the h r asset model ID with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r asset model ID to find
	 * @return the h r asset model ID, or <code>null</code> if a h r asset model ID with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetModelId fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the h r asset model ID with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrAssetModelId the primary key of the h r asset model ID to find
	 * @return the h r asset model ID, or <code>null</code> if a h r asset model ID with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetModelId fetchByPrimaryKey(long hrAssetModelId)
		throws SystemException {
		HRAssetModelId hrAssetModelId = (HRAssetModelId)EntityCacheUtil.getResult(HRAssetModelIdModelImpl.ENTITY_CACHE_ENABLED,
				HRAssetModelIdImpl.class, hrAssetModelId, this);

		if (hrAssetModelId == null) {
			Session session = null;

			try {
				session = openSession();

				hrAssetModelId = (HRAssetModelId)session.get(HRAssetModelIdImpl.class,
						Long.valueOf(hrAssetModelId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (hrAssetModelId != null) {
					cacheResult(hrAssetModelId);
				}

				closeSession(session);
			}
		}

		return hrAssetModelId;
	}

	/**
	 * Finds all the h r asset model IDs.
	 *
	 * @return the h r asset model IDs
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRAssetModelId> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the h r asset model IDs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r asset model IDs to return
	 * @param end the upper bound of the range of h r asset model IDs to return (not inclusive)
	 * @return the range of h r asset model IDs
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRAssetModelId> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the h r asset model IDs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r asset model IDs to return
	 * @param end the upper bound of the range of h r asset model IDs to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r asset model IDs
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRAssetModelId> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRAssetModelId> list = (List<HRAssetModelId>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRASSETMODELID);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRASSETMODELID;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRAssetModelId>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRAssetModelId>)QueryUtil.list(q,
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
	 * Removes all the h r asset model IDs from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRAssetModelId hrAssetModelId : findAll()) {
			hrAssetModelIdPersistence.remove(hrAssetModelId);
		}
	}

	/**
	 * Counts all the h r asset model IDs.
	 *
	 * @return the number of h r asset model IDs
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

				Query q = session.createQuery(_SQL_COUNT_HRASSETMODELID);

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
	 * Initializes the h r asset model ID persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRAssetModelId")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRAssetModelId>> listenersList = new ArrayList<ModelListener<HRAssetModelId>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRAssetModelId>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRAssetModelIdImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = HRAssetPersistence.class)
	protected HRAssetPersistence hrAssetPersistence;
	@BeanReference(type = HRAssetCheckoutPersistence.class)
	protected HRAssetCheckoutPersistence hrAssetCheckoutPersistence;
	@BeanReference(type = HRAssetDefinitionPersistence.class)
	protected HRAssetDefinitionPersistence hrAssetDefinitionPersistence;
	@BeanReference(type = HRAssetModelIdPersistence.class)
	protected HRAssetModelIdPersistence hrAssetModelIdPersistence;
	@BeanReference(type = HRAssetTypePersistence.class)
	protected HRAssetTypePersistence hrAssetTypePersistence;
	@BeanReference(type = HRAssetVendorIdPersistence.class)
	protected HRAssetVendorIdPersistence hrAssetVendorIdPersistence;
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
	private static final String _SQL_SELECT_HRASSETMODELID = "SELECT hrAssetModelId FROM HRAssetModelId hrAssetModelId";
	private static final String _SQL_COUNT_HRASSETMODELID = "SELECT COUNT(hrAssetModelId) FROM HRAssetModelId hrAssetModelId";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrAssetModelId.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRAssetModelId exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRAssetModelIdPersistenceImpl.class);
}