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

import com.liferay.hr.NoSuchProjectRoleException;
import com.liferay.hr.model.HRProjectRole;
import com.liferay.hr.model.impl.HRProjectRoleImpl;
import com.liferay.hr.model.impl.HRProjectRoleModelImpl;

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
 * The persistence implementation for the h r project role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRProjectRolePersistence
 * @see HRProjectRoleUtil
 * @generated
 */
public class HRProjectRolePersistenceImpl extends BasePersistenceImpl<HRProjectRole>
	implements HRProjectRolePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRProjectRoleUtil} to access the h r project role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRProjectRoleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRProjectRoleModelImpl.ENTITY_CACHE_ENABLED,
			HRProjectRoleModelImpl.FINDER_CACHE_ENABLED,
			HRProjectRoleImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRProjectRoleModelImpl.ENTITY_CACHE_ENABLED,
			HRProjectRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r project role in the entity cache if it is enabled.
	 *
	 * @param hrProjectRole the h r project role
	 */
	public void cacheResult(HRProjectRole hrProjectRole) {
		EntityCacheUtil.putResult(HRProjectRoleModelImpl.ENTITY_CACHE_ENABLED,
			HRProjectRoleImpl.class, hrProjectRole.getPrimaryKey(),
			hrProjectRole);

		hrProjectRole.resetOriginalValues();
	}

	/**
	 * Caches the h r project roles in the entity cache if it is enabled.
	 *
	 * @param hrProjectRoles the h r project roles
	 */
	public void cacheResult(List<HRProjectRole> hrProjectRoles) {
		for (HRProjectRole hrProjectRole : hrProjectRoles) {
			if (EntityCacheUtil.getResult(
						HRProjectRoleModelImpl.ENTITY_CACHE_ENABLED,
						HRProjectRoleImpl.class, hrProjectRole.getPrimaryKey(),
						this) == null) {
				cacheResult(hrProjectRole);
			}
		}
	}

	/**
	 * Clears the cache for all h r project roles.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRProjectRoleImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRProjectRoleImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r project role.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRProjectRole hrProjectRole) {
		EntityCacheUtil.removeResult(HRProjectRoleModelImpl.ENTITY_CACHE_ENABLED,
			HRProjectRoleImpl.class, hrProjectRole.getPrimaryKey());
	}

	/**
	 * Creates a new h r project role with the primary key. Does not add the h r project role to the database.
	 *
	 * @param hrProjectRoleId the primary key for the new h r project role
	 * @return the new h r project role
	 */
	public HRProjectRole create(long hrProjectRoleId) {
		HRProjectRole hrProjectRole = new HRProjectRoleImpl();

		hrProjectRole.setNew(true);
		hrProjectRole.setPrimaryKey(hrProjectRoleId);

		return hrProjectRole;
	}

	/**
	 * Removes the h r project role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r project role
	 * @return the h r project role that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r project role with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRProjectRole remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r project role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrProjectRoleId the primary key of the h r project role
	 * @return the h r project role that was removed
	 * @throws com.liferay.hr.NoSuchProjectRoleException if a h r project role with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRProjectRole remove(long hrProjectRoleId)
		throws NoSuchProjectRoleException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRProjectRole hrProjectRole = (HRProjectRole)session.get(HRProjectRoleImpl.class,
					Long.valueOf(hrProjectRoleId));

			if (hrProjectRole == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						hrProjectRoleId);
				}

				throw new NoSuchProjectRoleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrProjectRoleId);
			}

			return hrProjectRolePersistence.remove(hrProjectRole);
		}
		catch (NoSuchProjectRoleException nsee) {
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
	 * Removes the h r project role from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrProjectRole the h r project role
	 * @return the h r project role that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRProjectRole remove(HRProjectRole hrProjectRole)
		throws SystemException {
		return super.remove(hrProjectRole);
	}

	@Override
	protected HRProjectRole removeImpl(HRProjectRole hrProjectRole)
		throws SystemException {
		hrProjectRole = toUnwrappedModel(hrProjectRole);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrProjectRole);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRProjectRoleModelImpl.ENTITY_CACHE_ENABLED,
			HRProjectRoleImpl.class, hrProjectRole.getPrimaryKey());

		return hrProjectRole;
	}

	@Override
	public HRProjectRole updateImpl(
		com.liferay.hr.model.HRProjectRole hrProjectRole, boolean merge)
		throws SystemException {
		hrProjectRole = toUnwrappedModel(hrProjectRole);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrProjectRole, merge);

			hrProjectRole.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRProjectRoleModelImpl.ENTITY_CACHE_ENABLED,
			HRProjectRoleImpl.class, hrProjectRole.getPrimaryKey(),
			hrProjectRole);

		return hrProjectRole;
	}

	protected HRProjectRole toUnwrappedModel(HRProjectRole hrProjectRole) {
		if (hrProjectRole instanceof HRProjectRoleImpl) {
			return hrProjectRole;
		}

		HRProjectRoleImpl hrProjectRoleImpl = new HRProjectRoleImpl();

		hrProjectRoleImpl.setNew(hrProjectRole.isNew());
		hrProjectRoleImpl.setPrimaryKey(hrProjectRole.getPrimaryKey());

		hrProjectRoleImpl.setHrProjectRoleId(hrProjectRole.getHrProjectRoleId());
		hrProjectRoleImpl.setGroupId(hrProjectRole.getGroupId());
		hrProjectRoleImpl.setCompanyId(hrProjectRole.getCompanyId());
		hrProjectRoleImpl.setUserId(hrProjectRole.getUserId());
		hrProjectRoleImpl.setUserName(hrProjectRole.getUserName());
		hrProjectRoleImpl.setCreateDate(hrProjectRole.getCreateDate());
		hrProjectRoleImpl.setModifiedDate(hrProjectRole.getModifiedDate());
		hrProjectRoleImpl.setName(hrProjectRole.getName());
		hrProjectRoleImpl.setDescription(hrProjectRole.getDescription());

		return hrProjectRoleImpl;
	}

	/**
	 * Returns the h r project role with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r project role
	 * @return the h r project role
	 * @throws com.liferay.portal.NoSuchModelException if a h r project role with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRProjectRole findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r project role with the primary key or throws a {@link com.liferay.hr.NoSuchProjectRoleException} if it could not be found.
	 *
	 * @param hrProjectRoleId the primary key of the h r project role
	 * @return the h r project role
	 * @throws com.liferay.hr.NoSuchProjectRoleException if a h r project role with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRProjectRole findByPrimaryKey(long hrProjectRoleId)
		throws NoSuchProjectRoleException, SystemException {
		HRProjectRole hrProjectRole = fetchByPrimaryKey(hrProjectRoleId);

		if (hrProjectRole == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrProjectRoleId);
			}

			throw new NoSuchProjectRoleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrProjectRoleId);
		}

		return hrProjectRole;
	}

	/**
	 * Returns the h r project role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r project role
	 * @return the h r project role, or <code>null</code> if a h r project role with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRProjectRole fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r project role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrProjectRoleId the primary key of the h r project role
	 * @return the h r project role, or <code>null</code> if a h r project role with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRProjectRole fetchByPrimaryKey(long hrProjectRoleId)
		throws SystemException {
		HRProjectRole hrProjectRole = (HRProjectRole)EntityCacheUtil.getResult(HRProjectRoleModelImpl.ENTITY_CACHE_ENABLED,
				HRProjectRoleImpl.class, hrProjectRoleId, this);

		if (hrProjectRole == _nullHRProjectRole) {
			return null;
		}

		if (hrProjectRole == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrProjectRole = (HRProjectRole)session.get(HRProjectRoleImpl.class,
						Long.valueOf(hrProjectRoleId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrProjectRole != null) {
					cacheResult(hrProjectRole);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRProjectRoleModelImpl.ENTITY_CACHE_ENABLED,
						HRProjectRoleImpl.class, hrProjectRoleId,
						_nullHRProjectRole);
				}

				closeSession(session);
			}
		}

		return hrProjectRole;
	}

	/**
	 * Returns all the h r project roles.
	 *
	 * @return the h r project roles
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRProjectRole> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r project roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r project roles
	 * @param end the upper bound of the range of h r project roles (not inclusive)
	 * @return the range of h r project roles
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRProjectRole> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r project roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r project roles
	 * @param end the upper bound of the range of h r project roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r project roles
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRProjectRole> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRProjectRole> list = (List<HRProjectRole>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRPROJECTROLE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRPROJECTROLE;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRProjectRole>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRProjectRole>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the h r project roles from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRProjectRole hrProjectRole : findAll()) {
			hrProjectRolePersistence.remove(hrProjectRole);
		}
	}

	/**
	 * Returns the number of h r project roles.
	 *
	 * @return the number of h r project roles
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

				Query q = session.createQuery(_SQL_COUNT_HRPROJECTROLE);

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
	 * Initializes the h r project role persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRProjectRole")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRProjectRole>> listenersList = new ArrayList<ModelListener<HRProjectRole>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRProjectRole>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRProjectRoleImpl.class.getName());
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
	private static final String _SQL_SELECT_HRPROJECTROLE = "SELECT hrProjectRole FROM HRProjectRole hrProjectRole";
	private static final String _SQL_COUNT_HRPROJECTROLE = "SELECT COUNT(hrProjectRole) FROM HRProjectRole hrProjectRole";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrProjectRole.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRProjectRole exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRProjectRolePersistenceImpl.class);
	private static HRProjectRole _nullHRProjectRole = new HRProjectRoleImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRProjectRole> toCacheModel() {
				return _nullHRProjectRoleCacheModel;
			}
		};

	private static CacheModel<HRProjectRole> _nullHRProjectRoleCacheModel = new CacheModel<HRProjectRole>() {
			public HRProjectRole toEntityModel() {
				return _nullHRProjectRole;
			}
		};
}