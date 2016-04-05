/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.samplealloymvc.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.liferay.samplealloymvc.exception.NoSuchCheckoutException;
import com.liferay.samplealloymvc.model.Checkout;
import com.liferay.samplealloymvc.model.impl.CheckoutImpl;
import com.liferay.samplealloymvc.model.impl.CheckoutModelImpl;
import com.liferay.samplealloymvc.service.persistence.CheckoutPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the checkout service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CheckoutPersistence
 * @see com.liferay.samplealloymvc.service.persistence.CheckoutUtil
 * @generated
 */
@ProviderType
public class CheckoutPersistenceImpl extends BasePersistenceImpl<Checkout>
	implements CheckoutPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CheckoutUtil} to access the checkout persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CheckoutImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CheckoutModelImpl.ENTITY_CACHE_ENABLED,
			CheckoutModelImpl.FINDER_CACHE_ENABLED, CheckoutImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CheckoutModelImpl.ENTITY_CACHE_ENABLED,
			CheckoutModelImpl.FINDER_CACHE_ENABLED, CheckoutImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CheckoutModelImpl.ENTITY_CACHE_ENABLED,
			CheckoutModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CheckoutPersistenceImpl() {
		setModelClass(Checkout.class);
	}

	/**
	 * Caches the checkout in the entity cache if it is enabled.
	 *
	 * @param checkout the checkout
	 */
	@Override
	public void cacheResult(Checkout checkout) {
		entityCache.putResult(CheckoutModelImpl.ENTITY_CACHE_ENABLED,
			CheckoutImpl.class, checkout.getPrimaryKey(), checkout);

		checkout.resetOriginalValues();
	}

	/**
	 * Caches the checkouts in the entity cache if it is enabled.
	 *
	 * @param checkouts the checkouts
	 */
	@Override
	public void cacheResult(List<Checkout> checkouts) {
		for (Checkout checkout : checkouts) {
			if (entityCache.getResult(CheckoutModelImpl.ENTITY_CACHE_ENABLED,
						CheckoutImpl.class, checkout.getPrimaryKey()) == null) {
				cacheResult(checkout);
			}
			else {
				checkout.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all checkouts.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CheckoutImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the checkout.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Checkout checkout) {
		entityCache.removeResult(CheckoutModelImpl.ENTITY_CACHE_ENABLED,
			CheckoutImpl.class, checkout.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Checkout> checkouts) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Checkout checkout : checkouts) {
			entityCache.removeResult(CheckoutModelImpl.ENTITY_CACHE_ENABLED,
				CheckoutImpl.class, checkout.getPrimaryKey());
		}
	}

	/**
	 * Creates a new checkout with the primary key. Does not add the checkout to the database.
	 *
	 * @param checkoutId the primary key for the new checkout
	 * @return the new checkout
	 */
	@Override
	public Checkout create(long checkoutId) {
		Checkout checkout = new CheckoutImpl();

		checkout.setNew(true);
		checkout.setPrimaryKey(checkoutId);

		checkout.setCompanyId(companyProvider.getCompanyId());

		return checkout;
	}

	/**
	 * Removes the checkout with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param checkoutId the primary key of the checkout
	 * @return the checkout that was removed
	 * @throws NoSuchCheckoutException if a checkout with the primary key could not be found
	 */
	@Override
	public Checkout remove(long checkoutId) throws NoSuchCheckoutException {
		return remove((Serializable)checkoutId);
	}

	/**
	 * Removes the checkout with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the checkout
	 * @return the checkout that was removed
	 * @throws NoSuchCheckoutException if a checkout with the primary key could not be found
	 */
	@Override
	public Checkout remove(Serializable primaryKey)
		throws NoSuchCheckoutException {
		Session session = null;

		try {
			session = openSession();

			Checkout checkout = (Checkout)session.get(CheckoutImpl.class,
					primaryKey);

			if (checkout == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCheckoutException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(checkout);
		}
		catch (NoSuchCheckoutException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Checkout removeImpl(Checkout checkout) {
		checkout = toUnwrappedModel(checkout);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(checkout)) {
				checkout = (Checkout)session.get(CheckoutImpl.class,
						checkout.getPrimaryKeyObj());
			}

			if (checkout != null) {
				session.delete(checkout);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (checkout != null) {
			clearCache(checkout);
		}

		return checkout;
	}

	@Override
	public Checkout updateImpl(Checkout checkout) {
		checkout = toUnwrappedModel(checkout);

		boolean isNew = checkout.isNew();

		CheckoutModelImpl checkoutModelImpl = (CheckoutModelImpl)checkout;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (checkout.getCreateDate() == null)) {
			if (serviceContext == null) {
				checkout.setCreateDate(now);
			}
			else {
				checkout.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!checkoutModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				checkout.setModifiedDate(now);
			}
			else {
				checkout.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (checkout.isNew()) {
				session.save(checkout);

				checkout.setNew(false);
			}
			else {
				checkout = (Checkout)session.merge(checkout);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		entityCache.putResult(CheckoutModelImpl.ENTITY_CACHE_ENABLED,
			CheckoutImpl.class, checkout.getPrimaryKey(), checkout, false);

		checkout.resetOriginalValues();

		return checkout;
	}

	protected Checkout toUnwrappedModel(Checkout checkout) {
		if (checkout instanceof CheckoutImpl) {
			return checkout;
		}

		CheckoutImpl checkoutImpl = new CheckoutImpl();

		checkoutImpl.setNew(checkout.isNew());
		checkoutImpl.setPrimaryKey(checkout.getPrimaryKey());

		checkoutImpl.setCheckoutId(checkout.getCheckoutId());
		checkoutImpl.setCompanyId(checkout.getCompanyId());
		checkoutImpl.setUserId(checkout.getUserId());
		checkoutImpl.setUserName(checkout.getUserName());
		checkoutImpl.setCreateDate(checkout.getCreateDate());
		checkoutImpl.setModifiedDate(checkout.getModifiedDate());
		checkoutImpl.setAssetId(checkout.getAssetId());
		checkoutImpl.setCheckOutDate(checkout.getCheckOutDate());
		checkoutImpl.setExpectedCheckInDate(checkout.getExpectedCheckInDate());
		checkoutImpl.setActualCheckInDate(checkout.getActualCheckInDate());

		return checkoutImpl;
	}

	/**
	 * Returns the checkout with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the checkout
	 * @return the checkout
	 * @throws NoSuchCheckoutException if a checkout with the primary key could not be found
	 */
	@Override
	public Checkout findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCheckoutException {
		Checkout checkout = fetchByPrimaryKey(primaryKey);

		if (checkout == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCheckoutException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return checkout;
	}

	/**
	 * Returns the checkout with the primary key or throws a {@link NoSuchCheckoutException} if it could not be found.
	 *
	 * @param checkoutId the primary key of the checkout
	 * @return the checkout
	 * @throws NoSuchCheckoutException if a checkout with the primary key could not be found
	 */
	@Override
	public Checkout findByPrimaryKey(long checkoutId)
		throws NoSuchCheckoutException {
		return findByPrimaryKey((Serializable)checkoutId);
	}

	/**
	 * Returns the checkout with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the checkout
	 * @return the checkout, or <code>null</code> if a checkout with the primary key could not be found
	 */
	@Override
	public Checkout fetchByPrimaryKey(Serializable primaryKey) {
		Checkout checkout = (Checkout)entityCache.getResult(CheckoutModelImpl.ENTITY_CACHE_ENABLED,
				CheckoutImpl.class, primaryKey);

		if (checkout == _nullCheckout) {
			return null;
		}

		if (checkout == null) {
			Session session = null;

			try {
				session = openSession();

				checkout = (Checkout)session.get(CheckoutImpl.class, primaryKey);

				if (checkout != null) {
					cacheResult(checkout);
				}
				else {
					entityCache.putResult(CheckoutModelImpl.ENTITY_CACHE_ENABLED,
						CheckoutImpl.class, primaryKey, _nullCheckout);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CheckoutModelImpl.ENTITY_CACHE_ENABLED,
					CheckoutImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return checkout;
	}

	/**
	 * Returns the checkout with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param checkoutId the primary key of the checkout
	 * @return the checkout, or <code>null</code> if a checkout with the primary key could not be found
	 */
	@Override
	public Checkout fetchByPrimaryKey(long checkoutId) {
		return fetchByPrimaryKey((Serializable)checkoutId);
	}

	@Override
	public Map<Serializable, Checkout> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Checkout> map = new HashMap<Serializable, Checkout>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Checkout checkout = fetchByPrimaryKey(primaryKey);

			if (checkout != null) {
				map.put(primaryKey, checkout);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Checkout checkout = (Checkout)entityCache.getResult(CheckoutModelImpl.ENTITY_CACHE_ENABLED,
					CheckoutImpl.class, primaryKey);

			if (checkout == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, checkout);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CHECKOUT_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (Checkout checkout : (List<Checkout>)q.list()) {
				map.put(checkout.getPrimaryKeyObj(), checkout);

				cacheResult(checkout);

				uncachedPrimaryKeys.remove(checkout.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CheckoutModelImpl.ENTITY_CACHE_ENABLED,
					CheckoutImpl.class, primaryKey, _nullCheckout);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the checkouts.
	 *
	 * @return the checkouts
	 */
	@Override
	public List<Checkout> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the checkouts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CheckoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of checkouts
	 * @param end the upper bound of the range of checkouts (not inclusive)
	 * @return the range of checkouts
	 */
	@Override
	public List<Checkout> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the checkouts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CheckoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of checkouts
	 * @param end the upper bound of the range of checkouts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of checkouts
	 */
	@Override
	public List<Checkout> findAll(int start, int end,
		OrderByComparator<Checkout> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the checkouts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CheckoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of checkouts
	 * @param end the upper bound of the range of checkouts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of checkouts
	 */
	@Override
	public List<Checkout> findAll(int start, int end,
		OrderByComparator<Checkout> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Checkout> list = null;

		if (retrieveFromCache) {
			list = (List<Checkout>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CHECKOUT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CHECKOUT;

				if (pagination) {
					sql = sql.concat(CheckoutModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Checkout>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Checkout>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the checkouts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Checkout checkout : findAll()) {
			remove(checkout);
		}
	}

	/**
	 * Returns the number of checkouts.
	 *
	 * @return the number of checkouts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CHECKOUT);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CheckoutModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the checkout persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CheckoutImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_CHECKOUT = "SELECT checkout FROM Checkout checkout";
	private static final String _SQL_SELECT_CHECKOUT_WHERE_PKS_IN = "SELECT checkout FROM Checkout checkout WHERE checkoutId IN (";
	private static final String _SQL_COUNT_CHECKOUT = "SELECT COUNT(checkout) FROM Checkout checkout";
	private static final String _ORDER_BY_ENTITY_ALIAS = "checkout.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Checkout exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CheckoutPersistenceImpl.class);
	private static final Checkout _nullCheckout = new CheckoutImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Checkout> toCacheModel() {
				return _nullCheckoutCacheModel;
			}
		};

	private static final CacheModel<Checkout> _nullCheckoutCacheModel = new CacheModel<Checkout>() {
			@Override
			public Checkout toEntityModel() {
				return _nullCheckout;
			}
		};
}