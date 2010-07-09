/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.annotation.BeanReference;
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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
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
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CheckoutPersistence
 * @see       CheckoutUtil
 * @generated
 */
public class CheckoutPersistenceImpl extends BasePersistenceImpl<Checkout>
	implements CheckoutPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = CheckoutImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(CheckoutModelImpl.ENTITY_CACHE_ENABLED,
			CheckoutModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CheckoutModelImpl.ENTITY_CACHE_ENABLED,
			CheckoutModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(Checkout checkout) {
		EntityCacheUtil.putResult(CheckoutModelImpl.ENTITY_CACHE_ENABLED,
			CheckoutImpl.class, checkout.getPrimaryKey(), checkout);
	}

	public void cacheResult(List<Checkout> checkouts) {
		for (Checkout checkout : checkouts) {
			if (EntityCacheUtil.getResult(
						CheckoutModelImpl.ENTITY_CACHE_ENABLED,
						CheckoutImpl.class, checkout.getPrimaryKey(), this) == null) {
				cacheResult(checkout);
			}
		}
	}

	public void clearCache() {
		CacheRegistryUtil.clear(CheckoutImpl.class.getName());
		EntityCacheUtil.clearCache(CheckoutImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public void clearCache(Checkout checkout) {
		EntityCacheUtil.removeResult(CheckoutModelImpl.ENTITY_CACHE_ENABLED,
			CheckoutImpl.class, checkout.getPrimaryKey());
	}

	public Checkout create(long checkoutId) {
		Checkout checkout = new CheckoutImpl();

		checkout.setNew(true);
		checkout.setPrimaryKey(checkoutId);

		return checkout;
	}

	public Checkout remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public Checkout remove(long checkoutId)
		throws NoSuchCheckoutException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Checkout checkout = (Checkout)session.get(CheckoutImpl.class,
					new Long(checkoutId));

			if (checkout == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + checkoutId);
				}

				throw new NoSuchCheckoutException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					checkoutId);
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

	protected Checkout removeImpl(Checkout checkout) throws SystemException {
		checkout = toUnwrappedModel(checkout);

		Session session = null;

		try {
			session = openSession();

			if (checkout.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(CheckoutImpl.class,
						checkout.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(checkout);

			session.flush();
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

	public Checkout findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

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

	public Checkout fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public Checkout fetchByPrimaryKey(long checkoutId)
		throws SystemException {
		Checkout checkout = (Checkout)EntityCacheUtil.getResult(CheckoutModelImpl.ENTITY_CACHE_ENABLED,
				CheckoutImpl.class, checkoutId, this);

		if (checkout == null) {
			Session session = null;

			try {
				session = openSession();

				checkout = (Checkout)session.get(CheckoutImpl.class,
						new Long(checkoutId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (checkout != null) {
					cacheResult(checkout);
				}

				closeSession(session);
			}
		}

		return checkout;
	}

	public List<Checkout> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<Checkout> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	public List<Checkout> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Checkout> list = (List<Checkout>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

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
					list = new ArrayList<Checkout>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeAll() throws SystemException {
		for (Checkout checkout : findAll()) {
			remove(checkout);
		}
	}

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
	private static Log _log = LogFactoryUtil.getLog(CheckoutPersistenceImpl.class);
}