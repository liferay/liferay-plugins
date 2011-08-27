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

import com.liferay.hr.NoSuchUserProjectException;
import com.liferay.hr.model.HRUserProject;
import com.liferay.hr.model.impl.HRUserProjectImpl;
import com.liferay.hr.model.impl.HRUserProjectModelImpl;

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
 * The persistence implementation for the h r user project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRUserProjectPersistence
 * @see HRUserProjectUtil
 * @generated
 */
public class HRUserProjectPersistenceImpl extends BasePersistenceImpl<HRUserProject>
	implements HRUserProjectPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRUserProjectUtil} to access the h r user project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRUserProjectImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRUserProjectModelImpl.ENTITY_CACHE_ENABLED,
			HRUserProjectModelImpl.FINDER_CACHE_ENABLED,
			HRUserProjectImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRUserProjectModelImpl.ENTITY_CACHE_ENABLED,
			HRUserProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r user project in the entity cache if it is enabled.
	 *
	 * @param hrUserProject the h r user project
	 */
	public void cacheResult(HRUserProject hrUserProject) {
		EntityCacheUtil.putResult(HRUserProjectModelImpl.ENTITY_CACHE_ENABLED,
			HRUserProjectImpl.class, hrUserProject.getPrimaryKey(),
			hrUserProject);

		hrUserProject.resetOriginalValues();
	}

	/**
	 * Caches the h r user projects in the entity cache if it is enabled.
	 *
	 * @param hrUserProjects the h r user projects
	 */
	public void cacheResult(List<HRUserProject> hrUserProjects) {
		for (HRUserProject hrUserProject : hrUserProjects) {
			if (EntityCacheUtil.getResult(
						HRUserProjectModelImpl.ENTITY_CACHE_ENABLED,
						HRUserProjectImpl.class, hrUserProject.getPrimaryKey(),
						this) == null) {
				cacheResult(hrUserProject);
			}
		}
	}

	/**
	 * Clears the cache for all h r user projects.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRUserProjectImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRUserProjectImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r user project.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRUserProject hrUserProject) {
		EntityCacheUtil.removeResult(HRUserProjectModelImpl.ENTITY_CACHE_ENABLED,
			HRUserProjectImpl.class, hrUserProject.getPrimaryKey());
	}

	/**
	 * Creates a new h r user project with the primary key. Does not add the h r user project to the database.
	 *
	 * @param hrUserProjectId the primary key for the new h r user project
	 * @return the new h r user project
	 */
	public HRUserProject create(long hrUserProjectId) {
		HRUserProject hrUserProject = new HRUserProjectImpl();

		hrUserProject.setNew(true);
		hrUserProject.setPrimaryKey(hrUserProjectId);

		return hrUserProject;
	}

	/**
	 * Removes the h r user project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r user project
	 * @return the h r user project that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r user project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRUserProject remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r user project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrUserProjectId the primary key of the h r user project
	 * @return the h r user project that was removed
	 * @throws com.liferay.hr.NoSuchUserProjectException if a h r user project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRUserProject remove(long hrUserProjectId)
		throws NoSuchUserProjectException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRUserProject hrUserProject = (HRUserProject)session.get(HRUserProjectImpl.class,
					Long.valueOf(hrUserProjectId));

			if (hrUserProject == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						hrUserProjectId);
				}

				throw new NoSuchUserProjectException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrUserProjectId);
			}

			return hrUserProjectPersistence.remove(hrUserProject);
		}
		catch (NoSuchUserProjectException nsee) {
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
	 * Removes the h r user project from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrUserProject the h r user project
	 * @return the h r user project that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRUserProject remove(HRUserProject hrUserProject)
		throws SystemException {
		return super.remove(hrUserProject);
	}

	@Override
	protected HRUserProject removeImpl(HRUserProject hrUserProject)
		throws SystemException {
		hrUserProject = toUnwrappedModel(hrUserProject);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrUserProject);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRUserProjectModelImpl.ENTITY_CACHE_ENABLED,
			HRUserProjectImpl.class, hrUserProject.getPrimaryKey());

		return hrUserProject;
	}

	@Override
	public HRUserProject updateImpl(
		com.liferay.hr.model.HRUserProject hrUserProject, boolean merge)
		throws SystemException {
		hrUserProject = toUnwrappedModel(hrUserProject);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrUserProject, merge);

			hrUserProject.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRUserProjectModelImpl.ENTITY_CACHE_ENABLED,
			HRUserProjectImpl.class, hrUserProject.getPrimaryKey(),
			hrUserProject);

		return hrUserProject;
	}

	protected HRUserProject toUnwrappedModel(HRUserProject hrUserProject) {
		if (hrUserProject instanceof HRUserProjectImpl) {
			return hrUserProject;
		}

		HRUserProjectImpl hrUserProjectImpl = new HRUserProjectImpl();

		hrUserProjectImpl.setNew(hrUserProject.isNew());
		hrUserProjectImpl.setPrimaryKey(hrUserProject.getPrimaryKey());

		hrUserProjectImpl.setHrUserProjectId(hrUserProject.getHrUserProjectId());
		hrUserProjectImpl.setGroupId(hrUserProject.getGroupId());
		hrUserProjectImpl.setCompanyId(hrUserProject.getCompanyId());
		hrUserProjectImpl.setUserId(hrUserProject.getUserId());
		hrUserProjectImpl.setUserName(hrUserProject.getUserName());
		hrUserProjectImpl.setCreateDate(hrUserProject.getCreateDate());
		hrUserProjectImpl.setModifiedDate(hrUserProject.getModifiedDate());
		hrUserProjectImpl.setHrProjectBillingRateId(hrUserProject.getHrProjectBillingRateId());
		hrUserProjectImpl.setHrProjectId(hrUserProject.getHrProjectId());
		hrUserProjectImpl.setHrProjectRoleId(hrUserProject.getHrProjectRoleId());
		hrUserProjectImpl.setHrUserId(hrUserProject.getHrUserId());
		hrUserProjectImpl.setActualRate(hrUserProject.getActualRate());

		return hrUserProjectImpl;
	}

	/**
	 * Returns the h r user project with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r user project
	 * @return the h r user project
	 * @throws com.liferay.portal.NoSuchModelException if a h r user project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRUserProject findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r user project with the primary key or throws a {@link com.liferay.hr.NoSuchUserProjectException} if it could not be found.
	 *
	 * @param hrUserProjectId the primary key of the h r user project
	 * @return the h r user project
	 * @throws com.liferay.hr.NoSuchUserProjectException if a h r user project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRUserProject findByPrimaryKey(long hrUserProjectId)
		throws NoSuchUserProjectException, SystemException {
		HRUserProject hrUserProject = fetchByPrimaryKey(hrUserProjectId);

		if (hrUserProject == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrUserProjectId);
			}

			throw new NoSuchUserProjectException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrUserProjectId);
		}

		return hrUserProject;
	}

	/**
	 * Returns the h r user project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r user project
	 * @return the h r user project, or <code>null</code> if a h r user project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRUserProject fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r user project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrUserProjectId the primary key of the h r user project
	 * @return the h r user project, or <code>null</code> if a h r user project with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRUserProject fetchByPrimaryKey(long hrUserProjectId)
		throws SystemException {
		HRUserProject hrUserProject = (HRUserProject)EntityCacheUtil.getResult(HRUserProjectModelImpl.ENTITY_CACHE_ENABLED,
				HRUserProjectImpl.class, hrUserProjectId, this);

		if (hrUserProject == _nullHRUserProject) {
			return null;
		}

		if (hrUserProject == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrUserProject = (HRUserProject)session.get(HRUserProjectImpl.class,
						Long.valueOf(hrUserProjectId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrUserProject != null) {
					cacheResult(hrUserProject);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRUserProjectModelImpl.ENTITY_CACHE_ENABLED,
						HRUserProjectImpl.class, hrUserProjectId,
						_nullHRUserProject);
				}

				closeSession(session);
			}
		}

		return hrUserProject;
	}

	/**
	 * Returns all the h r user projects.
	 *
	 * @return the h r user projects
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRUserProject> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r user projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r user projects
	 * @param end the upper bound of the range of h r user projects (not inclusive)
	 * @return the range of h r user projects
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRUserProject> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r user projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r user projects
	 * @param end the upper bound of the range of h r user projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r user projects
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRUserProject> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRUserProject> list = (List<HRUserProject>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRUSERPROJECT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRUSERPROJECT;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRUserProject>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRUserProject>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the h r user projects from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRUserProject hrUserProject : findAll()) {
			hrUserProjectPersistence.remove(hrUserProject);
		}
	}

	/**
	 * Returns the number of h r user projects.
	 *
	 * @return the number of h r user projects
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

				Query q = session.createQuery(_SQL_COUNT_HRUSERPROJECT);

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
	 * Initializes the h r user project persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRUserProject")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRUserProject>> listenersList = new ArrayList<ModelListener<HRUserProject>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRUserProject>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRUserProjectImpl.class.getName());
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
	private static final String _SQL_SELECT_HRUSERPROJECT = "SELECT hrUserProject FROM HRUserProject hrUserProject";
	private static final String _SQL_COUNT_HRUSERPROJECT = "SELECT COUNT(hrUserProject) FROM HRUserProject hrUserProject";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrUserProject.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRUserProject exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRUserProjectPersistenceImpl.class);
	private static HRUserProject _nullHRUserProject = new HRUserProjectImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRUserProject> toCacheModel() {
				return _nullHRUserProjectCacheModel;
			}
		};

	private static CacheModel<HRUserProject> _nullHRUserProjectCacheModel = new CacheModel<HRUserProject>() {
			public HRUserProject toEntityModel() {
				return _nullHRUserProject;
			}
		};
}