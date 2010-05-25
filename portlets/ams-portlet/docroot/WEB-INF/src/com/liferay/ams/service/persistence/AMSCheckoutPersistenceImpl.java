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
import com.liferay.ams.model.AMSCheckout;
import com.liferay.ams.model.impl.AMSCheckoutImpl;
import com.liferay.ams.model.impl.AMSCheckoutModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistry;
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
 * <a href="AMSCheckoutPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AMSCheckoutPersistence
 * @see       AMSCheckoutUtil
 * @generated
 */
public class AMSCheckoutPersistenceImpl extends BasePersistenceImpl<AMSCheckout>
	implements AMSCheckoutPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = AMSCheckoutImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(AMSCheckoutModelImpl.ENTITY_CACHE_ENABLED,
			AMSCheckoutModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AMSCheckoutModelImpl.ENTITY_CACHE_ENABLED,
			AMSCheckoutModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(AMSCheckout amsCheckout) {
		EntityCacheUtil.putResult(AMSCheckoutModelImpl.ENTITY_CACHE_ENABLED,
			AMSCheckoutImpl.class, amsCheckout.getPrimaryKey(), amsCheckout);
	}

	public void cacheResult(List<AMSCheckout> amsCheckouts) {
		for (AMSCheckout amsCheckout : amsCheckouts) {
			if (EntityCacheUtil.getResult(
						AMSCheckoutModelImpl.ENTITY_CACHE_ENABLED,
						AMSCheckoutImpl.class, amsCheckout.getPrimaryKey(), this) == null) {
				cacheResult(amsCheckout);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(AMSCheckoutImpl.class.getName());
		EntityCacheUtil.clearCache(AMSCheckoutImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public void clearCache(AMSCheckout amsCheckout) {
		EntityCacheUtil.removeResult(AMSCheckoutModelImpl.ENTITY_CACHE_ENABLED,
			AMSCheckoutImpl.class, amsCheckout.getPrimaryKey());
	}

	public AMSCheckout create(long amsCheckoutId) {
		AMSCheckout amsCheckout = new AMSCheckoutImpl();

		amsCheckout.setNew(true);
		amsCheckout.setPrimaryKey(amsCheckoutId);

		return amsCheckout;
	}

	public AMSCheckout remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public AMSCheckout remove(long amsCheckoutId)
		throws NoSuchCheckoutException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AMSCheckout amsCheckout = (AMSCheckout)session.get(AMSCheckoutImpl.class,
					new Long(amsCheckoutId));

			if (amsCheckout == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + amsCheckoutId);
				}

				throw new NoSuchCheckoutException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					amsCheckoutId);
			}

			return remove(amsCheckout);
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

	public AMSCheckout remove(AMSCheckout amsCheckout)
		throws SystemException {
		for (ModelListener<AMSCheckout> listener : listeners) {
			listener.onBeforeRemove(amsCheckout);
		}

		amsCheckout = removeImpl(amsCheckout);

		for (ModelListener<AMSCheckout> listener : listeners) {
			listener.onAfterRemove(amsCheckout);
		}

		return amsCheckout;
	}

	protected AMSCheckout removeImpl(AMSCheckout amsCheckout)
		throws SystemException {
		amsCheckout = toUnwrappedModel(amsCheckout);

		Session session = null;

		try {
			session = openSession();

			if (amsCheckout.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(AMSCheckoutImpl.class,
						amsCheckout.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(amsCheckout);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(AMSCheckoutModelImpl.ENTITY_CACHE_ENABLED,
			AMSCheckoutImpl.class, amsCheckout.getPrimaryKey());

		return amsCheckout;
	}

	public AMSCheckout updateImpl(
		com.liferay.ams.model.AMSCheckout amsCheckout, boolean merge)
		throws SystemException {
		amsCheckout = toUnwrappedModel(amsCheckout);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, amsCheckout, merge);

			amsCheckout.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(AMSCheckoutModelImpl.ENTITY_CACHE_ENABLED,
			AMSCheckoutImpl.class, amsCheckout.getPrimaryKey(), amsCheckout);

		return amsCheckout;
	}

	protected AMSCheckout toUnwrappedModel(AMSCheckout amsCheckout) {
		if (amsCheckout instanceof AMSCheckoutImpl) {
			return amsCheckout;
		}

		AMSCheckoutImpl amsCheckoutImpl = new AMSCheckoutImpl();

		amsCheckoutImpl.setNew(amsCheckout.isNew());
		amsCheckoutImpl.setPrimaryKey(amsCheckout.getPrimaryKey());

		amsCheckoutImpl.setAmsCheckoutId(amsCheckout.getAmsCheckoutId());
		amsCheckoutImpl.setCompanyId(amsCheckout.getCompanyId());
		amsCheckoutImpl.setUserId(amsCheckout.getUserId());
		amsCheckoutImpl.setUserName(amsCheckout.getUserName());
		amsCheckoutImpl.setCreateDate(amsCheckout.getCreateDate());
		amsCheckoutImpl.setModifiedDate(amsCheckout.getModifiedDate());
		amsCheckoutImpl.setAmsAssetId(amsCheckout.getAmsAssetId());
		amsCheckoutImpl.setCheckOutDate(amsCheckout.getCheckOutDate());
		amsCheckoutImpl.setExpectedCheckInDate(amsCheckout.getExpectedCheckInDate());
		amsCheckoutImpl.setActualCheckInDate(amsCheckout.getActualCheckInDate());

		return amsCheckoutImpl;
	}

	public AMSCheckout findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public AMSCheckout findByPrimaryKey(long amsCheckoutId)
		throws NoSuchCheckoutException, SystemException {
		AMSCheckout amsCheckout = fetchByPrimaryKey(amsCheckoutId);

		if (amsCheckout == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + amsCheckoutId);
			}

			throw new NoSuchCheckoutException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				amsCheckoutId);
		}

		return amsCheckout;
	}

	public AMSCheckout fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public AMSCheckout fetchByPrimaryKey(long amsCheckoutId)
		throws SystemException {
		AMSCheckout amsCheckout = (AMSCheckout)EntityCacheUtil.getResult(AMSCheckoutModelImpl.ENTITY_CACHE_ENABLED,
				AMSCheckoutImpl.class, amsCheckoutId, this);

		if (amsCheckout == null) {
			Session session = null;

			try {
				session = openSession();

				amsCheckout = (AMSCheckout)session.get(AMSCheckoutImpl.class,
						new Long(amsCheckoutId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (amsCheckout != null) {
					cacheResult(amsCheckout);
				}

				closeSession(session);
			}
		}

		return amsCheckout;
	}

	public List<AMSCheckout> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<AMSCheckout> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<AMSCheckout> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<AMSCheckout> list = (List<AMSCheckout>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_AMSCHECKOUT);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}

				sql = _SQL_SELECT_AMSCHECKOUT;

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AMSCheckout>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AMSCheckout>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<AMSCheckout>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeAll() throws SystemException {
		for (AMSCheckout amsCheckout : findAll()) {
			remove(amsCheckout);
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

				Query q = session.createQuery(_SQL_COUNT_AMSCHECKOUT);

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
						"value.object.listener.com.liferay.ams.model.AMSCheckout")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AMSCheckout>> listenersList = new ArrayList<ModelListener<AMSCheckout>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<AMSCheckout>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(type = AMSAssetPersistence.class)
	protected AMSAssetPersistence amsAssetPersistence;
	@BeanReference(type = AMSCheckoutPersistence.class)
	protected AMSCheckoutPersistence amsCheckoutPersistence;
	@BeanReference(type = AMSDefinitionPersistence.class)
	protected AMSDefinitionPersistence amsDefinitionPersistence;
	@BeanReference(type = AMSTypePersistence.class)
	protected AMSTypePersistence amsTypePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_AMSCHECKOUT = "SELECT amsCheckout FROM AMSCheckout amsCheckout";
	private static final String _SQL_COUNT_AMSCHECKOUT = "SELECT COUNT(amsCheckout) FROM AMSCheckout amsCheckout";
	private static final String _ORDER_BY_ENTITY_ALIAS = "amsCheckout.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AMSCheckout exists with the primary key ";
	private static Log _log = LogFactoryUtil.getLog(AMSCheckoutPersistenceImpl.class);
}