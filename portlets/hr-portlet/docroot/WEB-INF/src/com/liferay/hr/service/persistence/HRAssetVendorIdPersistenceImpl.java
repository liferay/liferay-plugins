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

import com.liferay.hr.NoSuchAssetVendorIdException;
import com.liferay.hr.model.HRAssetVendorId;
import com.liferay.hr.model.impl.HRAssetVendorIdImpl;
import com.liferay.hr.model.impl.HRAssetVendorIdModelImpl;

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
 * The persistence implementation for the h r asset vendor ID service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HRAssetVendorIdPersistence
 * @see HRAssetVendorIdUtil
 * @generated
 */
public class HRAssetVendorIdPersistenceImpl extends BasePersistenceImpl<HRAssetVendorId>
	implements HRAssetVendorIdPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRAssetVendorIdUtil} to access the h r asset vendor ID persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRAssetVendorIdImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRAssetVendorIdModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetVendorIdModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRAssetVendorIdModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetVendorIdModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r asset vendor ID in the entity cache if it is enabled.
	 *
	 * @param hrAssetVendorId the h r asset vendor ID to cache
	 */
	public void cacheResult(HRAssetVendorId hrAssetVendorId) {
		EntityCacheUtil.putResult(HRAssetVendorIdModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetVendorIdImpl.class, hrAssetVendorId.getPrimaryKey(),
			hrAssetVendorId);

		hrAssetVendorId.resetOriginalValues();
	}

	/**
	 * Caches the h r asset vendor IDs in the entity cache if it is enabled.
	 *
	 * @param hrAssetVendorIds the h r asset vendor IDs to cache
	 */
	public void cacheResult(List<HRAssetVendorId> hrAssetVendorIds) {
		for (HRAssetVendorId hrAssetVendorId : hrAssetVendorIds) {
			if (EntityCacheUtil.getResult(
						HRAssetVendorIdModelImpl.ENTITY_CACHE_ENABLED,
						HRAssetVendorIdImpl.class,
						hrAssetVendorId.getPrimaryKey(), this) == null) {
				cacheResult(hrAssetVendorId);
			}
		}
	}

	/**
	 * Clears the cache for all h r asset vendor IDs.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRAssetVendorIdImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRAssetVendorIdImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r asset vendor ID.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(HRAssetVendorId hrAssetVendorId) {
		EntityCacheUtil.removeResult(HRAssetVendorIdModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetVendorIdImpl.class, hrAssetVendorId.getPrimaryKey());
	}

	/**
	 * Creates a new h r asset vendor ID with the primary key. Does not add the h r asset vendor ID to the database.
	 *
	 * @param hrAssetVendorId the primary key for the new h r asset vendor ID
	 * @return the new h r asset vendor ID
	 */
	public HRAssetVendorId create(long hrAssetVendorId) {
		HRAssetVendorId hrAssetVendorId = new HRAssetVendorIdImpl();

		hrAssetVendorId.setNew(true);
		hrAssetVendorId.setPrimaryKey(hrAssetVendorId);

		return hrAssetVendorId;
	}

	/**
	 * Removes the h r asset vendor ID with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r asset vendor ID to remove
	 * @return the h r asset vendor ID that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r asset vendor ID with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetVendorId remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r asset vendor ID with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrAssetVendorId the primary key of the h r asset vendor ID to remove
	 * @return the h r asset vendor ID that was removed
	 * @throws com.liferay.hr.NoSuchAssetVendorIdException if a h r asset vendor ID with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetVendorId remove(long hrAssetVendorId)
		throws NoSuchAssetVendorIdException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRAssetVendorId hrAssetVendorId = (HRAssetVendorId)session.get(HRAssetVendorIdImpl.class,
					Long.valueOf(hrAssetVendorId));

			if (hrAssetVendorId == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						hrAssetVendorId);
				}

				throw new NoSuchAssetVendorIdException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrAssetVendorId);
			}

			return hrAssetVendorIdPersistence.remove(hrAssetVendorId);
		}
		catch (NoSuchAssetVendorIdException nsee) {
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
	 * Removes the h r asset vendor ID from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrAssetVendorId the h r asset vendor ID to remove
	 * @return the h r asset vendor ID that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetVendorId remove(HRAssetVendorId hrAssetVendorId)
		throws SystemException {
		return super.remove(hrAssetVendorId);
	}

	protected HRAssetVendorId removeImpl(HRAssetVendorId hrAssetVendorId)
		throws SystemException {
		hrAssetVendorId = toUnwrappedModel(hrAssetVendorId);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrAssetVendorId);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRAssetVendorIdModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetVendorIdImpl.class, hrAssetVendorId.getPrimaryKey());

		return hrAssetVendorId;
	}

	public HRAssetVendorId updateImpl(
		com.liferay.hr.model.HRAssetVendorId hrAssetVendorId, boolean merge)
		throws SystemException {
		hrAssetVendorId = toUnwrappedModel(hrAssetVendorId);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrAssetVendorId, merge);

			hrAssetVendorId.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRAssetVendorIdModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetVendorIdImpl.class, hrAssetVendorId.getPrimaryKey(),
			hrAssetVendorId);

		return hrAssetVendorId;
	}

	protected HRAssetVendorId toUnwrappedModel(HRAssetVendorId hrAssetVendorId) {
		if (hrAssetVendorId instanceof HRAssetVendorIdImpl) {
			return hrAssetVendorId;
		}

		HRAssetVendorIdImpl hrAssetVendorIdImpl = new HRAssetVendorIdImpl();

		hrAssetVendorIdImpl.setNew(hrAssetVendorId.isNew());
		hrAssetVendorIdImpl.setPrimaryKey(hrAssetVendorId.getPrimaryKey());

		hrAssetVendorIdImpl.setHrAssetVendorId(hrAssetVendorId.getHrAssetVendorId());
		hrAssetVendorIdImpl.setGroupId(hrAssetVendorId.getGroupId());
		hrAssetVendorIdImpl.setCompanyId(hrAssetVendorId.getCompanyId());
		hrAssetVendorIdImpl.setUserId(hrAssetVendorId.getUserId());
		hrAssetVendorIdImpl.setUserName(hrAssetVendorId.getUserName());
		hrAssetVendorIdImpl.setCreateDate(hrAssetVendorId.getCreateDate());
		hrAssetVendorIdImpl.setModifiedDate(hrAssetVendorId.getModifiedDate());
		hrAssetVendorIdImpl.setName(hrAssetVendorId.getName());
		hrAssetVendorIdImpl.setDescription(hrAssetVendorId.getDescription());

		return hrAssetVendorIdImpl;
	}

	/**
	 * Finds the h r asset vendor ID with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r asset vendor ID to find
	 * @return the h r asset vendor ID
	 * @throws com.liferay.portal.NoSuchModelException if a h r asset vendor ID with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetVendorId findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the h r asset vendor ID with the primary key or throws a {@link com.liferay.hr.NoSuchAssetVendorIdException} if it could not be found.
	 *
	 * @param hrAssetVendorId the primary key of the h r asset vendor ID to find
	 * @return the h r asset vendor ID
	 * @throws com.liferay.hr.NoSuchAssetVendorIdException if a h r asset vendor ID with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetVendorId findByPrimaryKey(long hrAssetVendorId)
		throws NoSuchAssetVendorIdException, SystemException {
		HRAssetVendorId hrAssetVendorId = fetchByPrimaryKey(hrAssetVendorId);

		if (hrAssetVendorId == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrAssetVendorId);
			}

			throw new NoSuchAssetVendorIdException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrAssetVendorId);
		}

		return hrAssetVendorId;
	}

	/**
	 * Finds the h r asset vendor ID with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r asset vendor ID to find
	 * @return the h r asset vendor ID, or <code>null</code> if a h r asset vendor ID with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetVendorId fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the h r asset vendor ID with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrAssetVendorId the primary key of the h r asset vendor ID to find
	 * @return the h r asset vendor ID, or <code>null</code> if a h r asset vendor ID with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetVendorId fetchByPrimaryKey(long hrAssetVendorId)
		throws SystemException {
		HRAssetVendorId hrAssetVendorId = (HRAssetVendorId)EntityCacheUtil.getResult(HRAssetVendorIdModelImpl.ENTITY_CACHE_ENABLED,
				HRAssetVendorIdImpl.class, hrAssetVendorId, this);

		if (hrAssetVendorId == null) {
			Session session = null;

			try {
				session = openSession();

				hrAssetVendorId = (HRAssetVendorId)session.get(HRAssetVendorIdImpl.class,
						Long.valueOf(hrAssetVendorId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (hrAssetVendorId != null) {
					cacheResult(hrAssetVendorId);
				}

				closeSession(session);
			}
		}

		return hrAssetVendorId;
	}

	/**
	 * Finds all the h r asset vendor IDs.
	 *
	 * @return the h r asset vendor IDs
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRAssetVendorId> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the h r asset vendor IDs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r asset vendor IDs to return
	 * @param end the upper bound of the range of h r asset vendor IDs to return (not inclusive)
	 * @return the range of h r asset vendor IDs
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRAssetVendorId> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the h r asset vendor IDs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r asset vendor IDs to return
	 * @param end the upper bound of the range of h r asset vendor IDs to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r asset vendor IDs
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRAssetVendorId> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRAssetVendorId> list = (List<HRAssetVendorId>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRASSETVENDORID);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRASSETVENDORID;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRAssetVendorId>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRAssetVendorId>)QueryUtil.list(q,
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
	 * Removes all the h r asset vendor IDs from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRAssetVendorId hrAssetVendorId : findAll()) {
			hrAssetVendorIdPersistence.remove(hrAssetVendorId);
		}
	}

	/**
	 * Counts all the h r asset vendor IDs.
	 *
	 * @return the number of h r asset vendor IDs
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

				Query q = session.createQuery(_SQL_COUNT_HRASSETVENDORID);

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
	 * Initializes the h r asset vendor ID persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRAssetVendorId")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRAssetVendorId>> listenersList = new ArrayList<ModelListener<HRAssetVendorId>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRAssetVendorId>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRAssetVendorIdImpl.class.getName());
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
	private static final String _SQL_SELECT_HRASSETVENDORID = "SELECT hrAssetVendorId FROM HRAssetVendorId hrAssetVendorId";
	private static final String _SQL_COUNT_HRASSETVENDORID = "SELECT COUNT(hrAssetVendorId) FROM HRAssetVendorId hrAssetVendorId";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrAssetVendorId.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRAssetVendorId exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRAssetVendorIdPersistenceImpl.class);
}