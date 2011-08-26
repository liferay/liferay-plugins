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

import com.liferay.hr.NoSuchUserException;
import com.liferay.hr.model.HRUser;
import com.liferay.hr.model.impl.HRUserImpl;
import com.liferay.hr.model.impl.HRUserModelImpl;

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
 * The persistence implementation for the h r user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRUserPersistence
 * @see HRUserUtil
 * @generated
 */
public class HRUserPersistenceImpl extends BasePersistenceImpl<HRUser>
	implements HRUserPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRUserUtil} to access the h r user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRUserImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRUserModelImpl.ENTITY_CACHE_ENABLED,
			HRUserModelImpl.FINDER_CACHE_ENABLED, HRUserImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRUserModelImpl.ENTITY_CACHE_ENABLED,
			HRUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r user in the entity cache if it is enabled.
	 *
	 * @param hrUser the h r user
	 */
	public void cacheResult(HRUser hrUser) {
		EntityCacheUtil.putResult(HRUserModelImpl.ENTITY_CACHE_ENABLED,
			HRUserImpl.class, hrUser.getPrimaryKey(), hrUser);

		hrUser.resetOriginalValues();
	}

	/**
	 * Caches the h r users in the entity cache if it is enabled.
	 *
	 * @param hrUsers the h r users
	 */
	public void cacheResult(List<HRUser> hrUsers) {
		for (HRUser hrUser : hrUsers) {
			if (EntityCacheUtil.getResult(
						HRUserModelImpl.ENTITY_CACHE_ENABLED, HRUserImpl.class,
						hrUser.getPrimaryKey(), this) == null) {
				cacheResult(hrUser);
			}
		}
	}

	/**
	 * Clears the cache for all h r users.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRUserImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRUserImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r user.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRUser hrUser) {
		EntityCacheUtil.removeResult(HRUserModelImpl.ENTITY_CACHE_ENABLED,
			HRUserImpl.class, hrUser.getPrimaryKey());
	}

	/**
	 * Creates a new h r user with the primary key. Does not add the h r user to the database.
	 *
	 * @param hrUserId the primary key for the new h r user
	 * @return the new h r user
	 */
	public HRUser create(long hrUserId) {
		HRUser hrUser = new HRUserImpl();

		hrUser.setNew(true);
		hrUser.setPrimaryKey(hrUserId);

		return hrUser;
	}

	/**
	 * Removes the h r user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r user
	 * @return the h r user that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRUser remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrUserId the primary key of the h r user
	 * @return the h r user that was removed
	 * @throws com.liferay.hr.NoSuchUserException if a h r user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRUser remove(long hrUserId)
		throws NoSuchUserException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRUser hrUser = (HRUser)session.get(HRUserImpl.class,
					Long.valueOf(hrUserId));

			if (hrUser == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrUserId);
				}

				throw new NoSuchUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrUserId);
			}

			return hrUserPersistence.remove(hrUser);
		}
		catch (NoSuchUserException nsee) {
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
	 * Removes the h r user from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrUser the h r user
	 * @return the h r user that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRUser remove(HRUser hrUser) throws SystemException {
		return super.remove(hrUser);
	}

	@Override
	protected HRUser removeImpl(HRUser hrUser) throws SystemException {
		hrUser = toUnwrappedModel(hrUser);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrUser);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRUserModelImpl.ENTITY_CACHE_ENABLED,
			HRUserImpl.class, hrUser.getPrimaryKey());

		return hrUser;
	}

	@Override
	public HRUser updateImpl(com.liferay.hr.model.HRUser hrUser, boolean merge)
		throws SystemException {
		hrUser = toUnwrappedModel(hrUser);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrUser, merge);

			hrUser.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRUserModelImpl.ENTITY_CACHE_ENABLED,
			HRUserImpl.class, hrUser.getPrimaryKey(), hrUser);

		return hrUser;
	}

	protected HRUser toUnwrappedModel(HRUser hrUser) {
		if (hrUser instanceof HRUserImpl) {
			return hrUser;
		}

		HRUserImpl hrUserImpl = new HRUserImpl();

		hrUserImpl.setNew(hrUser.isNew());
		hrUserImpl.setPrimaryKey(hrUser.getPrimaryKey());

		hrUserImpl.setHrUserId(hrUser.getHrUserId());
		hrUserImpl.setGroupId(hrUser.getGroupId());
		hrUserImpl.setCompanyId(hrUser.getCompanyId());
		hrUserImpl.setUserId(hrUser.getUserId());
		hrUserImpl.setUserName(hrUser.getUserName());
		hrUserImpl.setCreateDate(hrUser.getCreateDate());
		hrUserImpl.setModifiedDate(hrUser.getModifiedDate());
		hrUserImpl.setHrEmploymentTypeId(hrUser.getHrEmploymentTypeId());
		hrUserImpl.setHrJobTitleId(hrUser.getHrJobTitleId());
		hrUserImpl.setHrOfficeId(hrUser.getHrOfficeId());
		hrUserImpl.setHrTerminationTypeId(hrUser.getHrTerminationTypeId());
		hrUserImpl.setHrWageTypeId(hrUser.getHrWageTypeId());
		hrUserImpl.setHireDate(hrUser.getHireDate());
		hrUserImpl.setTerminationDate(hrUser.getTerminationDate());
		hrUserImpl.setWageAmount(hrUser.getWageAmount());
		hrUserImpl.setWageCurrencyCode(hrUser.getWageCurrencyCode());
		hrUserImpl.setBenefitsExempt(hrUser.isBenefitsExempt());
		hrUserImpl.setOvertimeExempt(hrUser.isOvertimeExempt());

		return hrUserImpl;
	}

	/**
	 * Returns the h r user with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r user
	 * @return the h r user
	 * @throws com.liferay.portal.NoSuchModelException if a h r user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRUser findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r user with the primary key or throws a {@link com.liferay.hr.NoSuchUserException} if it could not be found.
	 *
	 * @param hrUserId the primary key of the h r user
	 * @return the h r user
	 * @throws com.liferay.hr.NoSuchUserException if a h r user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRUser findByPrimaryKey(long hrUserId)
		throws NoSuchUserException, SystemException {
		HRUser hrUser = fetchByPrimaryKey(hrUserId);

		if (hrUser == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrUserId);
			}

			throw new NoSuchUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrUserId);
		}

		return hrUser;
	}

	/**
	 * Returns the h r user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r user
	 * @return the h r user, or <code>null</code> if a h r user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRUser fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrUserId the primary key of the h r user
	 * @return the h r user, or <code>null</code> if a h r user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRUser fetchByPrimaryKey(long hrUserId) throws SystemException {
		HRUser hrUser = (HRUser)EntityCacheUtil.getResult(HRUserModelImpl.ENTITY_CACHE_ENABLED,
				HRUserImpl.class, hrUserId, this);

		if (hrUser == _nullHRUser) {
			return null;
		}

		if (hrUser == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrUser = (HRUser)session.get(HRUserImpl.class,
						Long.valueOf(hrUserId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrUser != null) {
					cacheResult(hrUser);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRUserModelImpl.ENTITY_CACHE_ENABLED,
						HRUserImpl.class, hrUserId, _nullHRUser);
				}

				closeSession(session);
			}
		}

		return hrUser;
	}

	/**
	 * Returns all the h r users.
	 *
	 * @return the h r users
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRUser> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r users
	 * @param end the upper bound of the range of h r users (not inclusive)
	 * @return the range of h r users
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRUser> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r users
	 * @param end the upper bound of the range of h r users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r users
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRUser> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRUser> list = (List<HRUser>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRUSER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRUSER;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRUser>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRUser>)QueryUtil.list(q, getDialect(), start,
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
	 * Removes all the h r users from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRUser hrUser : findAll()) {
			hrUserPersistence.remove(hrUser);
		}
	}

	/**
	 * Returns the number of h r users.
	 *
	 * @return the number of h r users
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

				Query q = session.createQuery(_SQL_COUNT_HRUSER);

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
	 * Initializes the h r user persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRUser")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRUser>> listenersList = new ArrayList<ModelListener<HRUser>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRUser>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRUserImpl.class.getName());
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
	private static final String _SQL_SELECT_HRUSER = "SELECT hrUser FROM HRUser hrUser";
	private static final String _SQL_COUNT_HRUSER = "SELECT COUNT(hrUser) FROM HRUser hrUser";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrUser.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRUser exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRUserPersistenceImpl.class);
	private static HRUser _nullHRUser = new HRUserImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRUser> toCacheModel() {
				return _nullHRUserCacheModel;
			}
		};

	private static CacheModel<HRUser> _nullHRUserCacheModel = new CacheModel<HRUser>() {
			public HRUser toEntityModel() {
				return _nullHRUser;
			}
		};
}