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

import com.liferay.hr.NoSuchAssetProductException;
import com.liferay.hr.model.HRAssetProduct;
import com.liferay.hr.model.impl.HRAssetProductImpl;
import com.liferay.hr.model.impl.HRAssetProductModelImpl;

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
 * The persistence implementation for the h r asset product service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRAssetProductPersistence
 * @see HRAssetProductUtil
 * @generated
 */
public class HRAssetProductPersistenceImpl extends BasePersistenceImpl<HRAssetProduct>
	implements HRAssetProductPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRAssetProductUtil} to access the h r asset product persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRAssetProductImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRAssetProductModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetProductModelImpl.FINDER_CACHE_ENABLED,
			HRAssetProductImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRAssetProductModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetProductModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r asset product in the entity cache if it is enabled.
	 *
	 * @param hrAssetProduct the h r asset product
	 */
	public void cacheResult(HRAssetProduct hrAssetProduct) {
		EntityCacheUtil.putResult(HRAssetProductModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetProductImpl.class, hrAssetProduct.getPrimaryKey(),
			hrAssetProduct);

		hrAssetProduct.resetOriginalValues();
	}

	/**
	 * Caches the h r asset products in the entity cache if it is enabled.
	 *
	 * @param hrAssetProducts the h r asset products
	 */
	public void cacheResult(List<HRAssetProduct> hrAssetProducts) {
		for (HRAssetProduct hrAssetProduct : hrAssetProducts) {
			if (EntityCacheUtil.getResult(
						HRAssetProductModelImpl.ENTITY_CACHE_ENABLED,
						HRAssetProductImpl.class,
						hrAssetProduct.getPrimaryKey(), this) == null) {
				cacheResult(hrAssetProduct);
			}
		}
	}

	/**
	 * Clears the cache for all h r asset products.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRAssetProductImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRAssetProductImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r asset product.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRAssetProduct hrAssetProduct) {
		EntityCacheUtil.removeResult(HRAssetProductModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetProductImpl.class, hrAssetProduct.getPrimaryKey());
	}

	/**
	 * Creates a new h r asset product with the primary key. Does not add the h r asset product to the database.
	 *
	 * @param hrAssetProductId the primary key for the new h r asset product
	 * @return the new h r asset product
	 */
	public HRAssetProduct create(long hrAssetProductId) {
		HRAssetProduct hrAssetProduct = new HRAssetProductImpl();

		hrAssetProduct.setNew(true);
		hrAssetProduct.setPrimaryKey(hrAssetProductId);

		return hrAssetProduct;
	}

	/**
	 * Removes the h r asset product with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r asset product
	 * @return the h r asset product that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r asset product with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRAssetProduct remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r asset product with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrAssetProductId the primary key of the h r asset product
	 * @return the h r asset product that was removed
	 * @throws com.liferay.hr.NoSuchAssetProductException if a h r asset product with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetProduct remove(long hrAssetProductId)
		throws NoSuchAssetProductException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRAssetProduct hrAssetProduct = (HRAssetProduct)session.get(HRAssetProductImpl.class,
					Long.valueOf(hrAssetProductId));

			if (hrAssetProduct == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						hrAssetProductId);
				}

				throw new NoSuchAssetProductException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrAssetProductId);
			}

			return hrAssetProductPersistence.remove(hrAssetProduct);
		}
		catch (NoSuchAssetProductException nsee) {
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
	 * Removes the h r asset product from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrAssetProduct the h r asset product
	 * @return the h r asset product that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRAssetProduct remove(HRAssetProduct hrAssetProduct)
		throws SystemException {
		return super.remove(hrAssetProduct);
	}

	@Override
	protected HRAssetProduct removeImpl(HRAssetProduct hrAssetProduct)
		throws SystemException {
		hrAssetProduct = toUnwrappedModel(hrAssetProduct);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrAssetProduct);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRAssetProductModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetProductImpl.class, hrAssetProduct.getPrimaryKey());

		return hrAssetProduct;
	}

	@Override
	public HRAssetProduct updateImpl(
		com.liferay.hr.model.HRAssetProduct hrAssetProduct, boolean merge)
		throws SystemException {
		hrAssetProduct = toUnwrappedModel(hrAssetProduct);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrAssetProduct, merge);

			hrAssetProduct.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRAssetProductModelImpl.ENTITY_CACHE_ENABLED,
			HRAssetProductImpl.class, hrAssetProduct.getPrimaryKey(),
			hrAssetProduct);

		return hrAssetProduct;
	}

	protected HRAssetProduct toUnwrappedModel(HRAssetProduct hrAssetProduct) {
		if (hrAssetProduct instanceof HRAssetProductImpl) {
			return hrAssetProduct;
		}

		HRAssetProductImpl hrAssetProductImpl = new HRAssetProductImpl();

		hrAssetProductImpl.setNew(hrAssetProduct.isNew());
		hrAssetProductImpl.setPrimaryKey(hrAssetProduct.getPrimaryKey());

		hrAssetProductImpl.setHrAssetProductId(hrAssetProduct.getHrAssetProductId());
		hrAssetProductImpl.setGroupId(hrAssetProduct.getGroupId());
		hrAssetProductImpl.setCompanyId(hrAssetProduct.getCompanyId());
		hrAssetProductImpl.setUserId(hrAssetProduct.getUserId());
		hrAssetProductImpl.setUserName(hrAssetProduct.getUserName());
		hrAssetProductImpl.setCreateDate(hrAssetProduct.getCreateDate());
		hrAssetProductImpl.setModifiedDate(hrAssetProduct.getModifiedDate());
		hrAssetProductImpl.setHrAssetVendorId(hrAssetProduct.getHrAssetVendorId());
		hrAssetProductImpl.setName(hrAssetProduct.getName());
		hrAssetProductImpl.setDescription(hrAssetProduct.getDescription());

		return hrAssetProductImpl;
	}

	/**
	 * Returns the h r asset product with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r asset product
	 * @return the h r asset product
	 * @throws com.liferay.portal.NoSuchModelException if a h r asset product with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRAssetProduct findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r asset product with the primary key or throws a {@link com.liferay.hr.NoSuchAssetProductException} if it could not be found.
	 *
	 * @param hrAssetProductId the primary key of the h r asset product
	 * @return the h r asset product
	 * @throws com.liferay.hr.NoSuchAssetProductException if a h r asset product with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetProduct findByPrimaryKey(long hrAssetProductId)
		throws NoSuchAssetProductException, SystemException {
		HRAssetProduct hrAssetProduct = fetchByPrimaryKey(hrAssetProductId);

		if (hrAssetProduct == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrAssetProductId);
			}

			throw new NoSuchAssetProductException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrAssetProductId);
		}

		return hrAssetProduct;
	}

	/**
	 * Returns the h r asset product with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r asset product
	 * @return the h r asset product, or <code>null</code> if a h r asset product with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRAssetProduct fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r asset product with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrAssetProductId the primary key of the h r asset product
	 * @return the h r asset product, or <code>null</code> if a h r asset product with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRAssetProduct fetchByPrimaryKey(long hrAssetProductId)
		throws SystemException {
		HRAssetProduct hrAssetProduct = (HRAssetProduct)EntityCacheUtil.getResult(HRAssetProductModelImpl.ENTITY_CACHE_ENABLED,
				HRAssetProductImpl.class, hrAssetProductId, this);

		if (hrAssetProduct == _nullHRAssetProduct) {
			return null;
		}

		if (hrAssetProduct == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrAssetProduct = (HRAssetProduct)session.get(HRAssetProductImpl.class,
						Long.valueOf(hrAssetProductId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrAssetProduct != null) {
					cacheResult(hrAssetProduct);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRAssetProductModelImpl.ENTITY_CACHE_ENABLED,
						HRAssetProductImpl.class, hrAssetProductId,
						_nullHRAssetProduct);
				}

				closeSession(session);
			}
		}

		return hrAssetProduct;
	}

	/**
	 * Returns all the h r asset products.
	 *
	 * @return the h r asset products
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRAssetProduct> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r asset products.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r asset products
	 * @param end the upper bound of the range of h r asset products (not inclusive)
	 * @return the range of h r asset products
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRAssetProduct> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r asset products.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r asset products
	 * @param end the upper bound of the range of h r asset products (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r asset products
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRAssetProduct> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRAssetProduct> list = (List<HRAssetProduct>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRASSETPRODUCT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRASSETPRODUCT;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRAssetProduct>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRAssetProduct>)QueryUtil.list(q,
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
	 * Removes all the h r asset products from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRAssetProduct hrAssetProduct : findAll()) {
			hrAssetProductPersistence.remove(hrAssetProduct);
		}
	}

	/**
	 * Returns the number of h r asset products.
	 *
	 * @return the number of h r asset products
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

				Query q = session.createQuery(_SQL_COUNT_HRASSETPRODUCT);

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
	 * Initializes the h r asset product persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRAssetProduct")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRAssetProduct>> listenersList = new ArrayList<ModelListener<HRAssetProduct>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRAssetProduct>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRAssetProductImpl.class.getName());
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
	private static final String _SQL_SELECT_HRASSETPRODUCT = "SELECT hrAssetProduct FROM HRAssetProduct hrAssetProduct";
	private static final String _SQL_COUNT_HRASSETPRODUCT = "SELECT COUNT(hrAssetProduct) FROM HRAssetProduct hrAssetProduct";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrAssetProduct.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRAssetProduct exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRAssetProductPersistenceImpl.class);
	private static HRAssetProduct _nullHRAssetProduct = new HRAssetProductImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRAssetProduct> toCacheModel() {
				return _nullHRAssetProductCacheModel;
			}
		};

	private static CacheModel<HRAssetProduct> _nullHRAssetProductCacheModel = new CacheModel<HRAssetProduct>() {
			public HRAssetProduct toEntityModel() {
				return _nullHRAssetProduct;
			}
		};
}