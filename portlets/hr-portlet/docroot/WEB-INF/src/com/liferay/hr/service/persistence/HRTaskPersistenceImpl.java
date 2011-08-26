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

import com.liferay.hr.NoSuchTaskException;
import com.liferay.hr.model.HRTask;
import com.liferay.hr.model.impl.HRTaskImpl;
import com.liferay.hr.model.impl.HRTaskModelImpl;

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
 * The persistence implementation for the h r task service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRTaskPersistence
 * @see HRTaskUtil
 * @generated
 */
public class HRTaskPersistenceImpl extends BasePersistenceImpl<HRTask>
	implements HRTaskPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRTaskUtil} to access the h r task persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRTaskImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRTaskModelImpl.ENTITY_CACHE_ENABLED,
			HRTaskModelImpl.FINDER_CACHE_ENABLED, HRTaskImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRTaskModelImpl.ENTITY_CACHE_ENABLED,
			HRTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r task in the entity cache if it is enabled.
	 *
	 * @param hrTask the h r task
	 */
	public void cacheResult(HRTask hrTask) {
		EntityCacheUtil.putResult(HRTaskModelImpl.ENTITY_CACHE_ENABLED,
			HRTaskImpl.class, hrTask.getPrimaryKey(), hrTask);

		hrTask.resetOriginalValues();
	}

	/**
	 * Caches the h r tasks in the entity cache if it is enabled.
	 *
	 * @param hrTasks the h r tasks
	 */
	public void cacheResult(List<HRTask> hrTasks) {
		for (HRTask hrTask : hrTasks) {
			if (EntityCacheUtil.getResult(
						HRTaskModelImpl.ENTITY_CACHE_ENABLED, HRTaskImpl.class,
						hrTask.getPrimaryKey(), this) == null) {
				cacheResult(hrTask);
			}
		}
	}

	/**
	 * Clears the cache for all h r tasks.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRTaskImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRTaskImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r task.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRTask hrTask) {
		EntityCacheUtil.removeResult(HRTaskModelImpl.ENTITY_CACHE_ENABLED,
			HRTaskImpl.class, hrTask.getPrimaryKey());
	}

	/**
	 * Creates a new h r task with the primary key. Does not add the h r task to the database.
	 *
	 * @param hrTaskId the primary key for the new h r task
	 * @return the new h r task
	 */
	public HRTask create(long hrTaskId) {
		HRTask hrTask = new HRTaskImpl();

		hrTask.setNew(true);
		hrTask.setPrimaryKey(hrTaskId);

		return hrTask;
	}

	/**
	 * Removes the h r task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r task
	 * @return the h r task that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTask remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrTaskId the primary key of the h r task
	 * @return the h r task that was removed
	 * @throws com.liferay.hr.NoSuchTaskException if a h r task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTask remove(long hrTaskId)
		throws NoSuchTaskException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRTask hrTask = (HRTask)session.get(HRTaskImpl.class,
					Long.valueOf(hrTaskId));

			if (hrTask == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrTaskId);
				}

				throw new NoSuchTaskException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrTaskId);
			}

			return hrTaskPersistence.remove(hrTask);
		}
		catch (NoSuchTaskException nsee) {
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
	 * Removes the h r task from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrTask the h r task
	 * @return the h r task that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTask remove(HRTask hrTask) throws SystemException {
		return super.remove(hrTask);
	}

	@Override
	protected HRTask removeImpl(HRTask hrTask) throws SystemException {
		hrTask = toUnwrappedModel(hrTask);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrTask);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRTaskModelImpl.ENTITY_CACHE_ENABLED,
			HRTaskImpl.class, hrTask.getPrimaryKey());

		return hrTask;
	}

	@Override
	public HRTask updateImpl(com.liferay.hr.model.HRTask hrTask, boolean merge)
		throws SystemException {
		hrTask = toUnwrappedModel(hrTask);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrTask, merge);

			hrTask.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRTaskModelImpl.ENTITY_CACHE_ENABLED,
			HRTaskImpl.class, hrTask.getPrimaryKey(), hrTask);

		return hrTask;
	}

	protected HRTask toUnwrappedModel(HRTask hrTask) {
		if (hrTask instanceof HRTaskImpl) {
			return hrTask;
		}

		HRTaskImpl hrTaskImpl = new HRTaskImpl();

		hrTaskImpl.setNew(hrTask.isNew());
		hrTaskImpl.setPrimaryKey(hrTask.getPrimaryKey());

		hrTaskImpl.setHrTaskId(hrTask.getHrTaskId());
		hrTaskImpl.setGroupId(hrTask.getGroupId());
		hrTaskImpl.setCompanyId(hrTask.getCompanyId());
		hrTaskImpl.setUserId(hrTask.getUserId());
		hrTaskImpl.setUserName(hrTask.getUserName());
		hrTaskImpl.setCreateDate(hrTask.getCreateDate());
		hrTaskImpl.setModifiedDate(hrTask.getModifiedDate());
		hrTaskImpl.setHrBillabilityId(hrTask.getHrBillabilityId());
		hrTaskImpl.setHrProjectId(hrTask.getHrProjectId());
		hrTaskImpl.setHrTaskStatusId(hrTask.getHrTaskStatusId());
		hrTaskImpl.setParentHRTaskId(hrTask.getParentHRTaskId());
		hrTaskImpl.setName(hrTask.getName());
		hrTaskImpl.setDescription(hrTask.getDescription());
		hrTaskImpl.setEstimatedStartDate(hrTask.getEstimatedStartDate());
		hrTaskImpl.setEstimatedEndDate(hrTask.getEstimatedEndDate());
		hrTaskImpl.setEstimatedHours(hrTask.getEstimatedHours());
		hrTaskImpl.setEstimatedHoursCost(hrTask.getEstimatedHoursCost());
		hrTaskImpl.setEstimatedHoursCostCurrencyCode(hrTask.getEstimatedHoursCostCurrencyCode());
		hrTaskImpl.setEstimatedExpenses(hrTask.getEstimatedExpenses());
		hrTaskImpl.setEstimatedExpensesCurrencyCode(hrTask.getEstimatedExpensesCurrencyCode());
		hrTaskImpl.setActualStartDate(hrTask.getActualStartDate());
		hrTaskImpl.setActualEndDate(hrTask.getActualEndDate());
		hrTaskImpl.setActualHours(hrTask.getActualHours());
		hrTaskImpl.setActualHoursCost(hrTask.getActualHoursCost());
		hrTaskImpl.setActualHoursCostCurrencyCode(hrTask.getActualHoursCostCurrencyCode());
		hrTaskImpl.setActualExpenses(hrTask.getActualExpenses());
		hrTaskImpl.setActualExpensesCurrencyCode(hrTask.getActualExpensesCurrencyCode());

		return hrTaskImpl;
	}

	/**
	 * Returns the h r task with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r task
	 * @return the h r task
	 * @throws com.liferay.portal.NoSuchModelException if a h r task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTask findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r task with the primary key or throws a {@link com.liferay.hr.NoSuchTaskException} if it could not be found.
	 *
	 * @param hrTaskId the primary key of the h r task
	 * @return the h r task
	 * @throws com.liferay.hr.NoSuchTaskException if a h r task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTask findByPrimaryKey(long hrTaskId)
		throws NoSuchTaskException, SystemException {
		HRTask hrTask = fetchByPrimaryKey(hrTaskId);

		if (hrTask == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrTaskId);
			}

			throw new NoSuchTaskException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrTaskId);
		}

		return hrTask;
	}

	/**
	 * Returns the h r task with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r task
	 * @return the h r task, or <code>null</code> if a h r task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTask fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r task with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrTaskId the primary key of the h r task
	 * @return the h r task, or <code>null</code> if a h r task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTask fetchByPrimaryKey(long hrTaskId) throws SystemException {
		HRTask hrTask = (HRTask)EntityCacheUtil.getResult(HRTaskModelImpl.ENTITY_CACHE_ENABLED,
				HRTaskImpl.class, hrTaskId, this);

		if (hrTask == _nullHRTask) {
			return null;
		}

		if (hrTask == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrTask = (HRTask)session.get(HRTaskImpl.class,
						Long.valueOf(hrTaskId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrTask != null) {
					cacheResult(hrTask);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRTaskModelImpl.ENTITY_CACHE_ENABLED,
						HRTaskImpl.class, hrTaskId, _nullHRTask);
				}

				closeSession(session);
			}
		}

		return hrTask;
	}

	/**
	 * Returns all the h r tasks.
	 *
	 * @return the h r tasks
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRTask> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r tasks
	 * @param end the upper bound of the range of h r tasks (not inclusive)
	 * @return the range of h r tasks
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRTask> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r tasks
	 * @param end the upper bound of the range of h r tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r tasks
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRTask> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRTask> list = (List<HRTask>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRTASK);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRTASK;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRTask>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRTask>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Removes all the h r tasks from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRTask hrTask : findAll()) {
			hrTaskPersistence.remove(hrTask);
		}
	}

	/**
	 * Returns the number of h r tasks.
	 *
	 * @return the number of h r tasks
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

				Query q = session.createQuery(_SQL_COUNT_HRTASK);

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
	 * Initializes the h r task persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRTask")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRTask>> listenersList = new ArrayList<ModelListener<HRTask>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRTask>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRTaskImpl.class.getName());
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
	private static final String _SQL_SELECT_HRTASK = "SELECT hrTask FROM HRTask hrTask";
	private static final String _SQL_COUNT_HRTASK = "SELECT COUNT(hrTask) FROM HRTask hrTask";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrTask.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRTask exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRTaskPersistenceImpl.class);
	private static HRTask _nullHRTask = new HRTaskImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRTask> toCacheModel() {
				return _nullHRTaskCacheModel;
			}
		};

	private static CacheModel<HRTask> _nullHRTaskCacheModel = new CacheModel<HRTask>() {
			public HRTask toEntityModel() {
				return _nullHRTask;
			}
		};
}