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

import com.liferay.hr.NoSuchUserTaskException;
import com.liferay.hr.model.HRUserTask;
import com.liferay.hr.model.impl.HRUserTaskImpl;
import com.liferay.hr.model.impl.HRUserTaskModelImpl;

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
 * The persistence implementation for the h r user task service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRUserTaskPersistence
 * @see HRUserTaskUtil
 * @generated
 */
public class HRUserTaskPersistenceImpl extends BasePersistenceImpl<HRUserTask>
	implements HRUserTaskPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRUserTaskUtil} to access the h r user task persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRUserTaskImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRUserTaskModelImpl.ENTITY_CACHE_ENABLED,
			HRUserTaskModelImpl.FINDER_CACHE_ENABLED, HRUserTaskImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRUserTaskModelImpl.ENTITY_CACHE_ENABLED,
			HRUserTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r user task in the entity cache if it is enabled.
	 *
	 * @param hrUserTask the h r user task
	 */
	public void cacheResult(HRUserTask hrUserTask) {
		EntityCacheUtil.putResult(HRUserTaskModelImpl.ENTITY_CACHE_ENABLED,
			HRUserTaskImpl.class, hrUserTask.getPrimaryKey(), hrUserTask);

		hrUserTask.resetOriginalValues();
	}

	/**
	 * Caches the h r user tasks in the entity cache if it is enabled.
	 *
	 * @param hrUserTasks the h r user tasks
	 */
	public void cacheResult(List<HRUserTask> hrUserTasks) {
		for (HRUserTask hrUserTask : hrUserTasks) {
			if (EntityCacheUtil.getResult(
						HRUserTaskModelImpl.ENTITY_CACHE_ENABLED,
						HRUserTaskImpl.class, hrUserTask.getPrimaryKey(), this) == null) {
				cacheResult(hrUserTask);
			}
		}
	}

	/**
	 * Clears the cache for all h r user tasks.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRUserTaskImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRUserTaskImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r user task.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRUserTask hrUserTask) {
		EntityCacheUtil.removeResult(HRUserTaskModelImpl.ENTITY_CACHE_ENABLED,
			HRUserTaskImpl.class, hrUserTask.getPrimaryKey());
	}

	/**
	 * Creates a new h r user task with the primary key. Does not add the h r user task to the database.
	 *
	 * @param hrUserTaskId the primary key for the new h r user task
	 * @return the new h r user task
	 */
	public HRUserTask create(long hrUserTaskId) {
		HRUserTask hrUserTask = new HRUserTaskImpl();

		hrUserTask.setNew(true);
		hrUserTask.setPrimaryKey(hrUserTaskId);

		return hrUserTask;
	}

	/**
	 * Removes the h r user task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r user task
	 * @return the h r user task that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r user task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRUserTask remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r user task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrUserTaskId the primary key of the h r user task
	 * @return the h r user task that was removed
	 * @throws com.liferay.hr.NoSuchUserTaskException if a h r user task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRUserTask remove(long hrUserTaskId)
		throws NoSuchUserTaskException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRUserTask hrUserTask = (HRUserTask)session.get(HRUserTaskImpl.class,
					Long.valueOf(hrUserTaskId));

			if (hrUserTask == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrUserTaskId);
				}

				throw new NoSuchUserTaskException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrUserTaskId);
			}

			return hrUserTaskPersistence.remove(hrUserTask);
		}
		catch (NoSuchUserTaskException nsee) {
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
	 * Removes the h r user task from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrUserTask the h r user task
	 * @return the h r user task that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRUserTask remove(HRUserTask hrUserTask) throws SystemException {
		return super.remove(hrUserTask);
	}

	@Override
	protected HRUserTask removeImpl(HRUserTask hrUserTask)
		throws SystemException {
		hrUserTask = toUnwrappedModel(hrUserTask);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrUserTask);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRUserTaskModelImpl.ENTITY_CACHE_ENABLED,
			HRUserTaskImpl.class, hrUserTask.getPrimaryKey());

		return hrUserTask;
	}

	@Override
	public HRUserTask updateImpl(com.liferay.hr.model.HRUserTask hrUserTask,
		boolean merge) throws SystemException {
		hrUserTask = toUnwrappedModel(hrUserTask);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrUserTask, merge);

			hrUserTask.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRUserTaskModelImpl.ENTITY_CACHE_ENABLED,
			HRUserTaskImpl.class, hrUserTask.getPrimaryKey(), hrUserTask);

		return hrUserTask;
	}

	protected HRUserTask toUnwrappedModel(HRUserTask hrUserTask) {
		if (hrUserTask instanceof HRUserTaskImpl) {
			return hrUserTask;
		}

		HRUserTaskImpl hrUserTaskImpl = new HRUserTaskImpl();

		hrUserTaskImpl.setNew(hrUserTask.isNew());
		hrUserTaskImpl.setPrimaryKey(hrUserTask.getPrimaryKey());

		hrUserTaskImpl.setHrUserTaskId(hrUserTask.getHrUserTaskId());
		hrUserTaskImpl.setGroupId(hrUserTask.getGroupId());
		hrUserTaskImpl.setCompanyId(hrUserTask.getCompanyId());
		hrUserTaskImpl.setUserId(hrUserTask.getUserId());
		hrUserTaskImpl.setUserName(hrUserTask.getUserName());
		hrUserTaskImpl.setCreateDate(hrUserTask.getCreateDate());
		hrUserTaskImpl.setModifiedDate(hrUserTask.getModifiedDate());
		hrUserTaskImpl.setHrBillabilityId(hrUserTask.getHrBillabilityId());
		hrUserTaskImpl.setHrTaskId(hrUserTask.getHrTaskId());
		hrUserTaskImpl.setHrTimesheetId(hrUserTask.getHrTimesheetId());
		hrUserTaskImpl.setHrUserId(hrUserTask.getHrUserId());

		return hrUserTaskImpl;
	}

	/**
	 * Returns the h r user task with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r user task
	 * @return the h r user task
	 * @throws com.liferay.portal.NoSuchModelException if a h r user task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRUserTask findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r user task with the primary key or throws a {@link com.liferay.hr.NoSuchUserTaskException} if it could not be found.
	 *
	 * @param hrUserTaskId the primary key of the h r user task
	 * @return the h r user task
	 * @throws com.liferay.hr.NoSuchUserTaskException if a h r user task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRUserTask findByPrimaryKey(long hrUserTaskId)
		throws NoSuchUserTaskException, SystemException {
		HRUserTask hrUserTask = fetchByPrimaryKey(hrUserTaskId);

		if (hrUserTask == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrUserTaskId);
			}

			throw new NoSuchUserTaskException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrUserTaskId);
		}

		return hrUserTask;
	}

	/**
	 * Returns the h r user task with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r user task
	 * @return the h r user task, or <code>null</code> if a h r user task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRUserTask fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r user task with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrUserTaskId the primary key of the h r user task
	 * @return the h r user task, or <code>null</code> if a h r user task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRUserTask fetchByPrimaryKey(long hrUserTaskId)
		throws SystemException {
		HRUserTask hrUserTask = (HRUserTask)EntityCacheUtil.getResult(HRUserTaskModelImpl.ENTITY_CACHE_ENABLED,
				HRUserTaskImpl.class, hrUserTaskId, this);

		if (hrUserTask == _nullHRUserTask) {
			return null;
		}

		if (hrUserTask == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrUserTask = (HRUserTask)session.get(HRUserTaskImpl.class,
						Long.valueOf(hrUserTaskId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrUserTask != null) {
					cacheResult(hrUserTask);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRUserTaskModelImpl.ENTITY_CACHE_ENABLED,
						HRUserTaskImpl.class, hrUserTaskId, _nullHRUserTask);
				}

				closeSession(session);
			}
		}

		return hrUserTask;
	}

	/**
	 * Returns all the h r user tasks.
	 *
	 * @return the h r user tasks
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRUserTask> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r user tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r user tasks
	 * @param end the upper bound of the range of h r user tasks (not inclusive)
	 * @return the range of h r user tasks
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRUserTask> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r user tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r user tasks
	 * @param end the upper bound of the range of h r user tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r user tasks
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRUserTask> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRUserTask> list = (List<HRUserTask>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRUSERTASK);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRUSERTASK;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRUserTask>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRUserTask>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the h r user tasks from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRUserTask hrUserTask : findAll()) {
			hrUserTaskPersistence.remove(hrUserTask);
		}
	}

	/**
	 * Returns the number of h r user tasks.
	 *
	 * @return the number of h r user tasks
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

				Query q = session.createQuery(_SQL_COUNT_HRUSERTASK);

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
	 * Initializes the h r user task persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRUserTask")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRUserTask>> listenersList = new ArrayList<ModelListener<HRUserTask>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRUserTask>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRUserTaskImpl.class.getName());
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
	private static final String _SQL_SELECT_HRUSERTASK = "SELECT hrUserTask FROM HRUserTask hrUserTask";
	private static final String _SQL_COUNT_HRUSERTASK = "SELECT COUNT(hrUserTask) FROM HRUserTask hrUserTask";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrUserTask.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRUserTask exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRUserTaskPersistenceImpl.class);
	private static HRUserTask _nullHRUserTask = new HRUserTaskImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRUserTask> toCacheModel() {
				return _nullHRUserTaskCacheModel;
			}
		};

	private static CacheModel<HRUserTask> _nullHRUserTaskCacheModel = new CacheModel<HRUserTask>() {
			public HRUserTask toEntityModel() {
				return _nullHRUserTask;
			}
		};
}