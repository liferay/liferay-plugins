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

import com.liferay.hr.NoSuchClientException;
import com.liferay.hr.model.HRClient;
import com.liferay.hr.model.impl.HRClientImpl;
import com.liferay.hr.model.impl.HRClientModelImpl;

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
 * The persistence implementation for the h r client service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRClientPersistence
 * @see HRClientUtil
 * @generated
 */
public class HRClientPersistenceImpl extends BasePersistenceImpl<HRClient>
	implements HRClientPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRClientUtil} to access the h r client persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRClientImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRClientModelImpl.ENTITY_CACHE_ENABLED,
			HRClientModelImpl.FINDER_CACHE_ENABLED, HRClientImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRClientModelImpl.ENTITY_CACHE_ENABLED,
			HRClientModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r client in the entity cache if it is enabled.
	 *
	 * @param hrClient the h r client
	 */
	public void cacheResult(HRClient hrClient) {
		EntityCacheUtil.putResult(HRClientModelImpl.ENTITY_CACHE_ENABLED,
			HRClientImpl.class, hrClient.getPrimaryKey(), hrClient);

		hrClient.resetOriginalValues();
	}

	/**
	 * Caches the h r clients in the entity cache if it is enabled.
	 *
	 * @param hrClients the h r clients
	 */
	public void cacheResult(List<HRClient> hrClients) {
		for (HRClient hrClient : hrClients) {
			if (EntityCacheUtil.getResult(
						HRClientModelImpl.ENTITY_CACHE_ENABLED,
						HRClientImpl.class, hrClient.getPrimaryKey(), this) == null) {
				cacheResult(hrClient);
			}
		}
	}

	/**
	 * Clears the cache for all h r clients.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRClientImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRClientImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r client.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRClient hrClient) {
		EntityCacheUtil.removeResult(HRClientModelImpl.ENTITY_CACHE_ENABLED,
			HRClientImpl.class, hrClient.getPrimaryKey());
	}

	/**
	 * Creates a new h r client with the primary key. Does not add the h r client to the database.
	 *
	 * @param hrClientId the primary key for the new h r client
	 * @return the new h r client
	 */
	public HRClient create(long hrClientId) {
		HRClient hrClient = new HRClientImpl();

		hrClient.setNew(true);
		hrClient.setPrimaryKey(hrClientId);

		return hrClient;
	}

	/**
	 * Removes the h r client with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r client
	 * @return the h r client that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r client with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRClient remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r client with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrClientId the primary key of the h r client
	 * @return the h r client that was removed
	 * @throws com.liferay.hr.NoSuchClientException if a h r client with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRClient remove(long hrClientId)
		throws NoSuchClientException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRClient hrClient = (HRClient)session.get(HRClientImpl.class,
					Long.valueOf(hrClientId));

			if (hrClient == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrClientId);
				}

				throw new NoSuchClientException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrClientId);
			}

			return hrClientPersistence.remove(hrClient);
		}
		catch (NoSuchClientException nsee) {
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
	 * Removes the h r client from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrClient the h r client
	 * @return the h r client that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRClient remove(HRClient hrClient) throws SystemException {
		return super.remove(hrClient);
	}

	@Override
	protected HRClient removeImpl(HRClient hrClient) throws SystemException {
		hrClient = toUnwrappedModel(hrClient);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrClient);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRClientModelImpl.ENTITY_CACHE_ENABLED,
			HRClientImpl.class, hrClient.getPrimaryKey());

		return hrClient;
	}

	@Override
	public HRClient updateImpl(com.liferay.hr.model.HRClient hrClient,
		boolean merge) throws SystemException {
		hrClient = toUnwrappedModel(hrClient);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrClient, merge);

			hrClient.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRClientModelImpl.ENTITY_CACHE_ENABLED,
			HRClientImpl.class, hrClient.getPrimaryKey(), hrClient);

		return hrClient;
	}

	protected HRClient toUnwrappedModel(HRClient hrClient) {
		if (hrClient instanceof HRClientImpl) {
			return hrClient;
		}

		HRClientImpl hrClientImpl = new HRClientImpl();

		hrClientImpl.setNew(hrClient.isNew());
		hrClientImpl.setPrimaryKey(hrClient.getPrimaryKey());

		hrClientImpl.setHrClientId(hrClient.getHrClientId());
		hrClientImpl.setGroupId(hrClient.getGroupId());
		hrClientImpl.setCompanyId(hrClient.getCompanyId());
		hrClientImpl.setUserId(hrClient.getUserId());
		hrClientImpl.setUserName(hrClient.getUserName());
		hrClientImpl.setCreateDate(hrClient.getCreateDate());
		hrClientImpl.setModifiedDate(hrClient.getModifiedDate());
		hrClientImpl.setName(hrClient.getName());
		hrClientImpl.setDescription(hrClient.getDescription());

		return hrClientImpl;
	}

	/**
	 * Returns the h r client with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r client
	 * @return the h r client
	 * @throws com.liferay.portal.NoSuchModelException if a h r client with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRClient findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r client with the primary key or throws a {@link com.liferay.hr.NoSuchClientException} if it could not be found.
	 *
	 * @param hrClientId the primary key of the h r client
	 * @return the h r client
	 * @throws com.liferay.hr.NoSuchClientException if a h r client with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRClient findByPrimaryKey(long hrClientId)
		throws NoSuchClientException, SystemException {
		HRClient hrClient = fetchByPrimaryKey(hrClientId);

		if (hrClient == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrClientId);
			}

			throw new NoSuchClientException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrClientId);
		}

		return hrClient;
	}

	/**
	 * Returns the h r client with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r client
	 * @return the h r client, or <code>null</code> if a h r client with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRClient fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r client with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrClientId the primary key of the h r client
	 * @return the h r client, or <code>null</code> if a h r client with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRClient fetchByPrimaryKey(long hrClientId)
		throws SystemException {
		HRClient hrClient = (HRClient)EntityCacheUtil.getResult(HRClientModelImpl.ENTITY_CACHE_ENABLED,
				HRClientImpl.class, hrClientId, this);

		if (hrClient == _nullHRClient) {
			return null;
		}

		if (hrClient == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrClient = (HRClient)session.get(HRClientImpl.class,
						Long.valueOf(hrClientId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrClient != null) {
					cacheResult(hrClient);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRClientModelImpl.ENTITY_CACHE_ENABLED,
						HRClientImpl.class, hrClientId, _nullHRClient);
				}

				closeSession(session);
			}
		}

		return hrClient;
	}

	/**
	 * Returns all the h r clients.
	 *
	 * @return the h r clients
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRClient> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r clients.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r clients
	 * @param end the upper bound of the range of h r clients (not inclusive)
	 * @return the range of h r clients
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRClient> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r clients.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r clients
	 * @param end the upper bound of the range of h r clients (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r clients
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRClient> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRClient> list = (List<HRClient>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRCLIENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRCLIENT;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRClient>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRClient>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the h r clients from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRClient hrClient : findAll()) {
			hrClientPersistence.remove(hrClient);
		}
	}

	/**
	 * Returns the number of h r clients.
	 *
	 * @return the number of h r clients
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

				Query q = session.createQuery(_SQL_COUNT_HRCLIENT);

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
	 * Initializes the h r client persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRClient")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRClient>> listenersList = new ArrayList<ModelListener<HRClient>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRClient>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRClientImpl.class.getName());
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
	private static final String _SQL_SELECT_HRCLIENT = "SELECT hrClient FROM HRClient hrClient";
	private static final String _SQL_COUNT_HRCLIENT = "SELECT COUNT(hrClient) FROM HRClient hrClient";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrClient.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRClient exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRClientPersistenceImpl.class);
	private static HRClient _nullHRClient = new HRClientImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRClient> toCacheModel() {
				return _nullHRClientCacheModel;
			}
		};

	private static CacheModel<HRClient> _nullHRClientCacheModel = new CacheModel<HRClient>() {
			public HRClient toEntityModel() {
				return _nullHRClient;
			}
		};
}