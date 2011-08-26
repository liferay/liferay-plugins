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

package com.liferay.ams.service.persistence;

import com.liferay.ams.NoSuchCheckoutException;
import com.liferay.ams.model.Checkout;
import com.liferay.ams.model.impl.CheckoutImpl;
import com.liferay.ams.model.impl.CheckoutModelImpl;

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
 * The persistence implementation for the checkout service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CheckoutPersistence
 * @see CheckoutUtil
 * @generated
 */
public class CheckoutPersistenceImpl extends BasePersistenceImpl<Checkout>
	implements CheckoutPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CheckoutUtil} to access the checkout persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CheckoutImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(CheckoutModelImpl.ENTITY_CACHE_ENABLED,
			CheckoutModelImpl.FINDER_CACHE_ENABLED, CheckoutImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CheckoutModelImpl.ENTITY_CACHE_ENABLED,
			CheckoutModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the checkout in the entity cache if it is enabled.
	 *
	 * @param checkout the checkout
	 */
	public void cacheResult(Checkout checkout) {
		EntityCacheUtil.putResult(CheckoutModelImpl.ENTITY_CACHE_ENABLED,
			CheckoutImpl.class, checkout.getPrimaryKey(), checkout);

		checkout.resetOriginalValues();
	}

	/**
	 * Caches the checkouts in the entity cache if it is enabled.
	 *
	 * @param checkouts the checkouts
	 */
	public void cacheResult(List<Checkout> checkouts) {
		for (Checkout checkout : checkouts) {
			if (EntityCacheUtil.getResult(
						CheckoutModelImpl.ENTITY_CACHE_ENABLED,
						CheckoutImpl.class, checkout.getPrimaryKey(), this) == null) {
				cacheResult(checkout);
			}
		}
	}

	/**
	 * Clears the cache for all checkouts.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(CheckoutImpl.class.getName());
		}

		EntityCacheUtil.clearCache(CheckoutImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the checkout.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Checkout checkout) {
		EntityCacheUtil.removeResult(CheckoutModelImpl.ENTITY_CACHE_ENABLED,
			CheckoutImpl.class, checkout.getPrimaryKey());
	}

	/**
	 * Creates a new checkout with the primary key. Does not add the checkout to the database.
	 *
	 * @param checkoutId the primary key for the new checkout
	 * @return the new checkout
	 */
	public Checkout create(long checkoutId) {
		Checkout checkout = new CheckoutImpl();

		checkout.setNew(true);
		checkout.setPrimaryKey(checkoutId);

		return checkout;
	}

	/**
	 * Removes the checkout with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the checkout
	 * @return the checkout that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a checkout with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Checkout remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the checkout with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param checkoutId the primary key of the checkout
	 * @return the checkout that was removed
	 * @throws com.liferay.ams.NoSuchCheckoutException if a checkout with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Checkout remove(long checkoutId)
		throws NoSuchCheckoutException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Checkout checkout = (Checkout)session.get(CheckoutImpl.class,
					Long.valueOf(checkoutId));

			if (checkout == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + checkoutId);
				}

				throw new NoSuchCheckoutException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					checkoutId);
			}

			return checkoutPersistence.remove(checkout);
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

	/**
	 * Removes the checkout from the database. Also notifies the appropriate model listeners.
	 *
	 * @param checkout the checkout
	 * @return the checkout that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Checkout remove(Checkout checkout) throws SystemException {
		return super.remove(checkout);
	}

	@Override
	protected Checkout removeImpl(Checkout checkout) throws SystemException {
		checkout = toUnwrappedModel(checkout);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, checkout);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(CheckoutModelImpl.ENTITY_CACHE_ENABLED,
			CheckoutImpl.class, checkout.getPrimaryKey());

		return checkout;
	}

	@Override
	public Checkout updateImpl(com.liferay.ams.model.Checkout checkout,
		boolean merge) throws SystemException {
		checkout = toUnwrappedModel(checkout);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, checkout, merge);

			checkout.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(CheckoutModelImpl.ENTITY_CACHE_ENABLED,
			CheckoutImpl.class, checkout.getPrimaryKey(), checkout);

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
	 * Returns the checkout with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the checkout
	 * @return the checkout
	 * @throws com.liferay.portal.NoSuchModelException if a checkout with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Checkout findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the checkout with the primary key or throws a {@link com.liferay.ams.NoSuchCheckoutException} if it could not be found.
	 *
	 * @param checkoutId the primary key of the checkout
	 * @return the checkout
	 * @throws com.liferay.ams.NoSuchCheckoutException if a checkout with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Checkout findByPrimaryKey(long checkoutId)
		throws NoSuchCheckoutException, SystemException {
		Checkout checkout = fetchByPrimaryKey(checkoutId);

		if (checkout == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + checkoutId);
			}

			throw new NoSuchCheckoutException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				checkoutId);
		}

		return checkout;
	}

	/**
	 * Returns the checkout with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the checkout
	 * @return the checkout, or <code>null</code> if a checkout with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Checkout fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the checkout with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param checkoutId the primary key of the checkout
	 * @return the checkout, or <code>null</code> if a checkout with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Checkout fetchByPrimaryKey(long checkoutId)
		throws SystemException {
		Checkout checkout = (Checkout)EntityCacheUtil.getResult(CheckoutModelImpl.ENTITY_CACHE_ENABLED,
				CheckoutImpl.class, checkoutId, this);

		if (checkout == _nullCheckout) {
			return null;
		}

		if (checkout == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				checkout = (Checkout)session.get(CheckoutImpl.class,
						Long.valueOf(checkoutId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (checkout != null) {
					cacheResult(checkout);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(CheckoutModelImpl.ENTITY_CACHE_ENABLED,
						CheckoutImpl.class, checkoutId, _nullCheckout);
				}

				closeSession(session);
			}
		}

		return checkout;
	}

	/**
	 * Returns all the checkouts.
	 *
	 * @return the checkouts
	 * @throws SystemException if a system exception occurred
	 */
	public List<Checkout> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the checkouts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of checkouts
	 * @param end the upper bound of the range of checkouts (not inclusive)
	 * @return the range of checkouts
	 * @throws SystemException if a system exception occurred
	 */
	public List<Checkout> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the checkouts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of checkouts
	 * @param end the upper bound of the range of checkouts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of checkouts
	 * @throws SystemException if a system exception occurred
	 */
	public List<Checkout> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Checkout> list = (List<Checkout>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CHECKOUT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CHECKOUT;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Checkout>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Checkout>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the checkouts from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Checkout checkout : findAll()) {
			checkoutPersistence.remove(checkout);
		}
	}

	/**
	 * Returns the number of checkouts.
	 *
	 * @return the number of checkouts
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

				Query q = session.createQuery(_SQL_COUNT_CHECKOUT);

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
	 * Initializes the checkout persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.ams.model.Checkout")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Checkout>> listenersList = new ArrayList<ModelListener<Checkout>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Checkout>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(CheckoutImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = AssetPersistence.class)
	protected AssetPersistence assetPersistence;
	@BeanReference(type = CheckoutPersistence.class)
	protected CheckoutPersistence checkoutPersistence;
	@BeanReference(type = DefinitionPersistence.class)
	protected DefinitionPersistence definitionPersistence;
	@BeanReference(type = TypePersistence.class)
	protected TypePersistence typePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_CHECKOUT = "SELECT checkout FROM Checkout checkout";
	private static final String _SQL_COUNT_CHECKOUT = "SELECT COUNT(checkout) FROM Checkout checkout";
	private static final String _ORDER_BY_ENTITY_ALIAS = "checkout.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Checkout exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(CheckoutPersistenceImpl.class);
	private static Checkout _nullCheckout = new CheckoutImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Checkout> toCacheModel() {
				return _nullCheckoutCacheModel;
			}
		};

	private static CacheModel<Checkout> _nullCheckoutCacheModel = new CacheModel<Checkout>() {
			public Checkout toEntityModel() {
				return _nullCheckout;
			}
		};
}