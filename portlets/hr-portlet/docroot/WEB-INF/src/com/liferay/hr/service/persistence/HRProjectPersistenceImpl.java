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

import com.liferay.hr.NoSuchProjectException;
import com.liferay.hr.model.HRProject;
import com.liferay.hr.model.impl.HRProjectImpl;
import com.liferay.hr.model.impl.HRProjectModelImpl;

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
 * The persistence implementation for the h r project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRProjectPersistence
 * @see HRProjectUtil
 * @generated
 */
public class HRProjectPersistenceImpl extends BasePersistenceImpl<HRProject>
	implements HRProjectPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRProjectUtil} to access the h r project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRProjectImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRProjectModelImpl.ENTITY_CACHE_ENABLED,
			HRProjectModelImpl.FINDER_CACHE_ENABLED, HRProjectImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRProjectModelImpl.ENTITY_CACHE_ENABLED,
			HRProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r project in the entity cache if it is enabled.
	 *
	 * @param hrProject the h r project
	 */
	public void cacheResult(HRProject hrProject) {
		EntityCacheUtil.putResult(HRProjectModelImpl.ENTITY_CACHE_ENABLED,
			HRProjectImpl.class, hrProject.getPrimaryKey(), hrProject);

		hrProject.resetOriginalValues();
	}

	/**
	 * Caches the h r projects in the entity cache if it is enabled.
	 *
	 * @param hrProjects the h r projects
	 */
	public void cacheResult(List<HRProject> hrProjects) {
		for (HRProject hrProject : hrProjects) {
			if (EntityCacheUtil.getResult(
						HRProjectModelImpl.ENTITY_CACHE_ENABLED,
						HRProjectImpl.class, hrProject.getPrimaryKey(), this) == null) {
				cacheResult(hrProject);
			}
		}
	}

	/**
	 * Clears the cache for all h r projects.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRProjectImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRProjectImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r project.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRProject hrProject) {
		EntityCacheUtil.removeResult(HRProjectModelImpl.ENTITY_CACHE_ENABLED,
			HRProjectImpl.class, hrProject.getPrimaryKey());
	}

	/**
	 * Creates a new h r project with the primary key. Does not add the h r project to the database.
	 *
	 * @param hrProjectId the primary key for the new h r project
	 * @return the new h r project
	 */
	public HRProject create(long hrProjectId) {
		HRProject hrProject = new HRProjectImpl();

		hrProject.setNew(true);
		hrProject.setPrimaryKey(hrProjectId);

		return hrProject;
	}

	/**
	 * Removes the h r project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r project
	 * @return the h r project that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRProject remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrProjectId the primary key of the h r project
	 * @return the h r project that was removed
	 * @throws com.liferay.hr.NoSuchProjectException if a h r project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRProject remove(long hrProjectId)
		throws NoSuchProjectException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRProject hrProject = (HRProject)session.get(HRProjectImpl.class,
					Long.valueOf(hrProjectId));

			if (hrProject == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrProjectId);
				}

				throw new NoSuchProjectException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrProjectId);
			}

			return hrProjectPersistence.remove(hrProject);
		}
		catch (NoSuchProjectException nsee) {
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
	 * Removes the h r project from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrProject the h r project
	 * @return the h r project that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRProject remove(HRProject hrProject) throws SystemException {
		return super.remove(hrProject);
	}

	@Override
	protected HRProject removeImpl(HRProject hrProject)
		throws SystemException {
		hrProject = toUnwrappedModel(hrProject);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrProject);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRProjectModelImpl.ENTITY_CACHE_ENABLED,
			HRProjectImpl.class, hrProject.getPrimaryKey());

		return hrProject;
	}

	@Override
	public HRProject updateImpl(com.liferay.hr.model.HRProject hrProject,
		boolean merge) throws SystemException {
		hrProject = toUnwrappedModel(hrProject);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrProject, merge);

			hrProject.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRProjectModelImpl.ENTITY_CACHE_ENABLED,
			HRProjectImpl.class, hrProject.getPrimaryKey(), hrProject);

		return hrProject;
	}

	protected HRProject toUnwrappedModel(HRProject hrProject) {
		if (hrProject instanceof HRProjectImpl) {
			return hrProject;
		}

		HRProjectImpl hrProjectImpl = new HRProjectImpl();

		hrProjectImpl.setNew(hrProject.isNew());
		hrProjectImpl.setPrimaryKey(hrProject.getPrimaryKey());

		hrProjectImpl.setHrProjectId(hrProject.getHrProjectId());
		hrProjectImpl.setGroupId(hrProject.getGroupId());
		hrProjectImpl.setCompanyId(hrProject.getCompanyId());
		hrProjectImpl.setUserId(hrProject.getUserId());
		hrProjectImpl.setUserName(hrProject.getUserName());
		hrProjectImpl.setCreateDate(hrProject.getCreateDate());
		hrProjectImpl.setModifiedDate(hrProject.getModifiedDate());
		hrProjectImpl.setHrClientId(hrProject.getHrClientId());
		hrProjectImpl.setHrProjectStatusId(hrProject.getHrProjectStatusId());
		hrProjectImpl.setName(hrProject.getName());
		hrProjectImpl.setDescription(hrProject.getDescription());
		hrProjectImpl.setEstimatedStartDate(hrProject.getEstimatedStartDate());
		hrProjectImpl.setEstimatedEndDate(hrProject.getEstimatedEndDate());
		hrProjectImpl.setEstimatedHours(hrProject.getEstimatedHours());
		hrProjectImpl.setEstimatedHoursCost(hrProject.getEstimatedHoursCost());
		hrProjectImpl.setEstimatedHoursCostCurrencyCode(hrProject.getEstimatedHoursCostCurrencyCode());
		hrProjectImpl.setEstimatedExpenses(hrProject.getEstimatedExpenses());
		hrProjectImpl.setEstimatedExpensesCurrencyCode(hrProject.getEstimatedExpensesCurrencyCode());
		hrProjectImpl.setActualStartDate(hrProject.getActualStartDate());
		hrProjectImpl.setActualEndDate(hrProject.getActualEndDate());
		hrProjectImpl.setActualHours(hrProject.getActualHours());
		hrProjectImpl.setActualHoursCost(hrProject.getActualHoursCost());
		hrProjectImpl.setActualHoursCostCurrencyCode(hrProject.getActualHoursCostCurrencyCode());
		hrProjectImpl.setActualExpenses(hrProject.getActualExpenses());
		hrProjectImpl.setActualExpensesCurrencyCode(hrProject.getActualExpensesCurrencyCode());

		return hrProjectImpl;
	}

	/**
	 * Returns the h r project with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r project
	 * @return the h r project
	 * @throws com.liferay.portal.NoSuchModelException if a h r project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRProject findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r project with the primary key or throws a {@link com.liferay.hr.NoSuchProjectException} if it could not be found.
	 *
	 * @param hrProjectId the primary key of the h r project
	 * @return the h r project
	 * @throws com.liferay.hr.NoSuchProjectException if a h r project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRProject findByPrimaryKey(long hrProjectId)
		throws NoSuchProjectException, SystemException {
		HRProject hrProject = fetchByPrimaryKey(hrProjectId);

		if (hrProject == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrProjectId);
			}

			throw new NoSuchProjectException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrProjectId);
		}

		return hrProject;
	}

	/**
	 * Returns the h r project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r project
	 * @return the h r project, or <code>null</code> if a h r project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRProject fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrProjectId the primary key of the h r project
	 * @return the h r project, or <code>null</code> if a h r project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRProject fetchByPrimaryKey(long hrProjectId)
		throws SystemException {
		HRProject hrProject = (HRProject)EntityCacheUtil.getResult(HRProjectModelImpl.ENTITY_CACHE_ENABLED,
				HRProjectImpl.class, hrProjectId, this);

		if (hrProject == _nullHRProject) {
			return null;
		}

		if (hrProject == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrProject = (HRProject)session.get(HRProjectImpl.class,
						Long.valueOf(hrProjectId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrProject != null) {
					cacheResult(hrProject);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRProjectModelImpl.ENTITY_CACHE_ENABLED,
						HRProjectImpl.class, hrProjectId, _nullHRProject);
				}

				closeSession(session);
			}
		}

		return hrProject;
	}

	/**
	 * Returns all the h r projects.
	 *
	 * @return the h r projects
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRProject> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r projects
	 * @param end the upper bound of the range of h r projects (not inclusive)
	 * @return the range of h r projects
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRProject> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r projects
	 * @param end the upper bound of the range of h r projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r projects
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRProject> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRProject> list = (List<HRProject>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRPROJECT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRPROJECT;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRProject>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRProject>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the h r projects from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRProject hrProject : findAll()) {
			hrProjectPersistence.remove(hrProject);
		}
	}

	/**
	 * Returns the number of h r projects.
	 *
	 * @return the number of h r projects
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

				Query q = session.createQuery(_SQL_COUNT_HRPROJECT);

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
	 * Initializes the h r project persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRProject")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRProject>> listenersList = new ArrayList<ModelListener<HRProject>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRProject>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRProjectImpl.class.getName());
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
	private static final String _SQL_SELECT_HRPROJECT = "SELECT hrProject FROM HRProject hrProject";
	private static final String _SQL_COUNT_HRPROJECT = "SELECT COUNT(hrProject) FROM HRProject hrProject";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrProject.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRProject exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRProjectPersistenceImpl.class);
	private static HRProject _nullHRProject = new HRProjectImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRProject> toCacheModel() {
				return _nullHRProjectCacheModel;
			}
		};

	private static CacheModel<HRProject> _nullHRProjectCacheModel = new CacheModel<HRProject>() {
			public HRProject toEntityModel() {
				return _nullHRProject;
			}
		};
}