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

import com.liferay.hr.NoSuchAssetDefinitionException;
import com.liferay.hr.model.HRAssetDefinition;
import com.liferay.hr.model.impl.HRAssetDefinitionImpl;
import com.liferay.hr.model.impl.HRAssetDefinitionModelImpl;

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
 * The persistence implementation for the h r asset definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRAssetDefinitionPersistence
 * @see HRAssetDefinitionUtil
 * @generated
 */
public class HRAssetDefinitionPersistenceImpl extends BasePersistenceImpl<HRAssetDefinition>
	implements HRAssetDefinitionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRAssetDefinitionUtil} to access the h r asset definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRAssetDefinitionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRAssetDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetDefinitionModelImpl.FINDER_CACHE_ENABLED,
			HRAssetDefinitionImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRAssetDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r asset definition in the entity cache if it is enabled.
	 *
	 * @param hrAssetDefinition the h r asset definition
	 */
	public void cacheResult(HRAssetDefinition hrAssetDefinition) {
		EntityCacheUtil.putResult(HRAssetDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetDefinitionImpl.class, hrAssetDefinition.getPrimaryKey(),
			hrAssetDefinition);

		hrAssetDefinition.resetOriginalValues();
	}

	/**
	 * Caches the h r asset definitions in the entity cache if it is enabled.
	 *
	 * @param hrAssetDefinitions the h r asset definitions
	 */
	public void cacheResult(List<HRAssetDefinition> hrAssetDefinitions) {
		for (HRAssetDefinition hrAssetDefinition : hrAssetDefinitions) {
			if (EntityCacheUtil.getResult(
						HRAssetDefinitionModelImpl.ENTITY_CACHE_ENABLED,
						HRAssetDefinitionImpl.class,
						hrAssetDefinition.getPrimaryKey(), this) == null) {
				cacheResult(hrAssetDefinition);
			}
		}
	}

	/**
	 * Clears the cache for all h r asset definitions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRAssetDefinitionImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRAssetDefinitionImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r asset definition.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRAssetDefinition hrAssetDefinition) {
		EntityCacheUtil.removeResult(HRAssetDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetDefinitionImpl.class, hrAssetDefinition.getPrimaryKey());
	}

	/**
	 * Creates a new h r asset definition with the primary key. Does not add the h r asset definition to the database.
	 *
	 * @param hrAssetDefinitionId the primary key for the new h r asset definition
	 * @return the new h r asset definition
	 */
	public HRAssetDefinition create(long hrAssetDefinitionId) {
		HRAssetDefinition hrAssetDefinition = new HRAssetDefinitionImpl();

		hrAssetDefinition.setNew(true);
		hrAssetDefinition.setPrimaryKey(hrAssetDefinitionId);

		return hrAssetDefinition;
	}

	/**
	 * Removes the h r asset definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r asset definition
	 * @return the h r asset definition that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r asset definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRAssetDefinition remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r asset definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrAssetDefinitionId the primary key of the h r asset definition
	 * @return the h r asset definition that was removed
	 * @throws com.liferay.hr.NoSuchAssetDefinitionException if a h r asset definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetDefinition remove(long hrAssetDefinitionId)
		throws NoSuchAssetDefinitionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRAssetDefinition hrAssetDefinition = (HRAssetDefinition)session.get(HRAssetDefinitionImpl.class,
					Long.valueOf(hrAssetDefinitionId));

			if (hrAssetDefinition == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						hrAssetDefinitionId);
				}

				throw new NoSuchAssetDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrAssetDefinitionId);
			}

			return hrAssetDefinitionPersistence.remove(hrAssetDefinition);
		}
		catch (NoSuchAssetDefinitionException nsee) {
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
	 * Removes the h r asset definition from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrAssetDefinition the h r asset definition
	 * @return the h r asset definition that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRAssetDefinition remove(HRAssetDefinition hrAssetDefinition)
		throws SystemException {
		return super.remove(hrAssetDefinition);
	}

	@Override
	protected HRAssetDefinition removeImpl(HRAssetDefinition hrAssetDefinition)
		throws SystemException {
		hrAssetDefinition = toUnwrappedModel(hrAssetDefinition);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrAssetDefinition);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRAssetDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetDefinitionImpl.class, hrAssetDefinition.getPrimaryKey());

		return hrAssetDefinition;
	}

	@Override
	public HRAssetDefinition updateImpl(
		com.liferay.hr.model.HRAssetDefinition hrAssetDefinition, boolean merge)
		throws SystemException {
		hrAssetDefinition = toUnwrappedModel(hrAssetDefinition);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrAssetDefinition, merge);

			hrAssetDefinition.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRAssetDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetDefinitionImpl.class, hrAssetDefinition.getPrimaryKey(),
			hrAssetDefinition);

		return hrAssetDefinition;
	}

	protected HRAssetDefinition toUnwrappedModel(
		HRAssetDefinition hrAssetDefinition) {
		if (hrAssetDefinition instanceof HRAssetDefinitionImpl) {
			return hrAssetDefinition;
		}

		HRAssetDefinitionImpl hrAssetDefinitionImpl = new HRAssetDefinitionImpl();

		hrAssetDefinitionImpl.setNew(hrAssetDefinition.isNew());
		hrAssetDefinitionImpl.setPrimaryKey(hrAssetDefinition.getPrimaryKey());

		hrAssetDefinitionImpl.setHrAssetDefinitionId(hrAssetDefinition.getHrAssetDefinitionId());
		hrAssetDefinitionImpl.setGroupId(hrAssetDefinition.getGroupId());
		hrAssetDefinitionImpl.setCompanyId(hrAssetDefinition.getCompanyId());
		hrAssetDefinitionImpl.setUserId(hrAssetDefinition.getUserId());
		hrAssetDefinitionImpl.setUserName(hrAssetDefinition.getUserName());
		hrAssetDefinitionImpl.setCreateDate(hrAssetDefinition.getCreateDate());
		hrAssetDefinitionImpl.setModifiedDate(hrAssetDefinition.getModifiedDate());
		hrAssetDefinitionImpl.setHrAssetProductId(hrAssetDefinition.getHrAssetProductId());
		hrAssetDefinitionImpl.setHrAssetTypeId(hrAssetDefinition.getHrAssetTypeId());
		hrAssetDefinitionImpl.setHrAssetVendorId(hrAssetDefinition.getHrAssetVendorId());
		hrAssetDefinitionImpl.setDefinitionNumber(hrAssetDefinition.getDefinitionNumber());
		hrAssetDefinitionImpl.setOrderId(hrAssetDefinition.getOrderId());
		hrAssetDefinitionImpl.setOrderDate(hrAssetDefinition.getOrderDate());
		hrAssetDefinitionImpl.setQuantity(hrAssetDefinition.getQuantity());
		hrAssetDefinitionImpl.setIndividualPrice(hrAssetDefinition.getIndividualPrice());

		return hrAssetDefinitionImpl;
	}

	/**
	 * Returns the h r asset definition with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r asset definition
	 * @return the h r asset definition
	 * @throws com.liferay.portal.NoSuchModelException if a h r asset definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRAssetDefinition findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r asset definition with the primary key or throws a {@link com.liferay.hr.NoSuchAssetDefinitionException} if it could not be found.
	 *
	 * @param hrAssetDefinitionId the primary key of the h r asset definition
	 * @return the h r asset definition
	 * @throws com.liferay.hr.NoSuchAssetDefinitionException if a h r asset definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetDefinition findByPrimaryKey(long hrAssetDefinitionId)
		throws NoSuchAssetDefinitionException, SystemException {
		HRAssetDefinition hrAssetDefinition = fetchByPrimaryKey(hrAssetDefinitionId);

		if (hrAssetDefinition == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrAssetDefinitionId);
			}

			throw new NoSuchAssetDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrAssetDefinitionId);
		}

		return hrAssetDefinition;
	}

	/**
	 * Returns the h r asset definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r asset definition
	 * @return the h r asset definition, or <code>null</code> if a h r asset definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRAssetDefinition fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r asset definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrAssetDefinitionId the primary key of the h r asset definition
	 * @return the h r asset definition, or <code>null</code> if a h r asset definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetDefinition fetchByPrimaryKey(long hrAssetDefinitionId)
		throws SystemException {
		HRAssetDefinition hrAssetDefinition = (HRAssetDefinition)EntityCacheUtil.getResult(HRAssetDefinitionModelImpl.ENTITY_CACHE_ENABLED,
				HRAssetDefinitionImpl.class, hrAssetDefinitionId, this);

		if (hrAssetDefinition == _nullHRAssetDefinition) {
			return null;
		}

		if (hrAssetDefinition == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrAssetDefinition = (HRAssetDefinition)session.get(HRAssetDefinitionImpl.class,
						Long.valueOf(hrAssetDefinitionId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrAssetDefinition != null) {
					cacheResult(hrAssetDefinition);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRAssetDefinitionModelImpl.ENTITY_CACHE_ENABLED,
						HRAssetDefinitionImpl.class, hrAssetDefinitionId,
						_nullHRAssetDefinition);
				}

				closeSession(session);
			}
		}

		return hrAssetDefinition;
	}

	/**
	 * Returns all the h r asset definitions.
	 *
	 * @return the h r asset definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRAssetDefinition> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r asset definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r asset definitions
	 * @param end the upper bound of the range of h r asset definitions (not inclusive)
	 * @return the range of h r asset definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRAssetDefinition> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r asset definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r asset definitions
	 * @param end the upper bound of the range of h r asset definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r asset definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRAssetDefinition> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRAssetDefinition> list = (List<HRAssetDefinition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRASSETDEFINITION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRASSETDEFINITION;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRAssetDefinition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRAssetDefinition>)QueryUtil.list(q,
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
	 * Removes all the h r asset definitions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRAssetDefinition hrAssetDefinition : findAll()) {
			hrAssetDefinitionPersistence.remove(hrAssetDefinition);
		}
	}

	/**
	 * Returns the number of h r asset definitions.
	 *
	 * @return the number of h r asset definitions
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

				Query q = session.createQuery(_SQL_COUNT_HRASSETDEFINITION);

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
	 * Initializes the h r asset definition persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRAssetDefinition")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRAssetDefinition>> listenersList = new ArrayList<ModelListener<HRAssetDefinition>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRAssetDefinition>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRAssetDefinitionImpl.class.getName());
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
	private static final String _SQL_SELECT_HRASSETDEFINITION = "SELECT hrAssetDefinition FROM HRAssetDefinition hrAssetDefinition";
	private static final String _SQL_COUNT_HRASSETDEFINITION = "SELECT COUNT(hrAssetDefinition) FROM HRAssetDefinition hrAssetDefinition";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrAssetDefinition.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRAssetDefinition exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRAssetDefinitionPersistenceImpl.class);
	private static HRAssetDefinition _nullHRAssetDefinition = new HRAssetDefinitionImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRAssetDefinition> toCacheModel() {
				return _nullHRAssetDefinitionCacheModel;
			}
		};

	private static CacheModel<HRAssetDefinition> _nullHRAssetDefinitionCacheModel =
		new CacheModel<HRAssetDefinition>() {
			public HRAssetDefinition toEntityModel() {
				return _nullHRAssetDefinition;
			}
		};
}