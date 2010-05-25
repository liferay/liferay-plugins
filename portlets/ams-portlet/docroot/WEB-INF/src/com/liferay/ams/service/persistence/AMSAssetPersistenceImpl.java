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

import com.liferay.ams.NoSuchAssetException;
import com.liferay.ams.model.AMSAsset;
import com.liferay.ams.model.impl.AMSAssetImpl;
import com.liferay.ams.model.impl.AMSAssetModelImpl;

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
 * <a href="AMSAssetPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AMSAssetPersistence
 * @see       AMSAssetUtil
 * @generated
 */
public class AMSAssetPersistenceImpl extends BasePersistenceImpl<AMSAsset>
	implements AMSAssetPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = AMSAssetImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(AMSAssetModelImpl.ENTITY_CACHE_ENABLED,
			AMSAssetModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AMSAssetModelImpl.ENTITY_CACHE_ENABLED,
			AMSAssetModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(AMSAsset amsAsset) {
		EntityCacheUtil.putResult(AMSAssetModelImpl.ENTITY_CACHE_ENABLED,
			AMSAssetImpl.class, amsAsset.getPrimaryKey(), amsAsset);
	}

	public void cacheResult(List<AMSAsset> amsAssets) {
		for (AMSAsset amsAsset : amsAssets) {
			if (EntityCacheUtil.getResult(
						AMSAssetModelImpl.ENTITY_CACHE_ENABLED,
						AMSAssetImpl.class, amsAsset.getPrimaryKey(), this) == null) {
				cacheResult(amsAsset);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(AMSAssetImpl.class.getName());
		EntityCacheUtil.clearCache(AMSAssetImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public void clearCache(AMSAsset amsAsset) {
		EntityCacheUtil.removeResult(AMSAssetModelImpl.ENTITY_CACHE_ENABLED,
			AMSAssetImpl.class, amsAsset.getPrimaryKey());
	}

	public AMSAsset create(long amsAssetId) {
		AMSAsset amsAsset = new AMSAssetImpl();

		amsAsset.setNew(true);
		amsAsset.setPrimaryKey(amsAssetId);

		return amsAsset;
	}

	public AMSAsset remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public AMSAsset remove(long amsAssetId)
		throws NoSuchAssetException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AMSAsset amsAsset = (AMSAsset)session.get(AMSAssetImpl.class,
					new Long(amsAssetId));

			if (amsAsset == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + amsAssetId);
				}

				throw new NoSuchAssetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					amsAssetId);
			}

			return remove(amsAsset);
		}
		catch (NoSuchAssetException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public AMSAsset remove(AMSAsset amsAsset) throws SystemException {
		for (ModelListener<AMSAsset> listener : listeners) {
			listener.onBeforeRemove(amsAsset);
		}

		amsAsset = removeImpl(amsAsset);

		for (ModelListener<AMSAsset> listener : listeners) {
			listener.onAfterRemove(amsAsset);
		}

		return amsAsset;
	}

	protected AMSAsset removeImpl(AMSAsset amsAsset) throws SystemException {
		amsAsset = toUnwrappedModel(amsAsset);

		Session session = null;

		try {
			session = openSession();

			if (amsAsset.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(AMSAssetImpl.class,
						amsAsset.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(amsAsset);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(AMSAssetModelImpl.ENTITY_CACHE_ENABLED,
			AMSAssetImpl.class, amsAsset.getPrimaryKey());

		return amsAsset;
	}

	public AMSAsset updateImpl(com.liferay.ams.model.AMSAsset amsAsset,
		boolean merge) throws SystemException {
		amsAsset = toUnwrappedModel(amsAsset);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, amsAsset, merge);

			amsAsset.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(AMSAssetModelImpl.ENTITY_CACHE_ENABLED,
			AMSAssetImpl.class, amsAsset.getPrimaryKey(), amsAsset);

		return amsAsset;
	}

	protected AMSAsset toUnwrappedModel(AMSAsset amsAsset) {
		if (amsAsset instanceof AMSAssetImpl) {
			return amsAsset;
		}

		AMSAssetImpl amsAssetImpl = new AMSAssetImpl();

		amsAssetImpl.setNew(amsAsset.isNew());
		amsAssetImpl.setPrimaryKey(amsAsset.getPrimaryKey());

		amsAssetImpl.setAmsAssetId(amsAsset.getAmsAssetId());
		amsAssetImpl.setCompanyId(amsAsset.getCompanyId());
		amsAssetImpl.setUserId(amsAsset.getUserId());
		amsAssetImpl.setUserName(amsAsset.getUserName());
		amsAssetImpl.setCreateDate(amsAsset.getCreateDate());
		amsAssetImpl.setModifiedDate(amsAsset.getModifiedDate());
		amsAssetImpl.setAssetDefinitionId(amsAsset.getAssetDefinitionId());
		amsAssetImpl.setSerialNumber(amsAsset.getSerialNumber());
		amsAssetImpl.setInactiveDate(amsAsset.getInactiveDate());
		amsAssetImpl.setActive(amsAsset.isActive());

		return amsAssetImpl;
	}

	public AMSAsset findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public AMSAsset findByPrimaryKey(long amsAssetId)
		throws NoSuchAssetException, SystemException {
		AMSAsset amsAsset = fetchByPrimaryKey(amsAssetId);

		if (amsAsset == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + amsAssetId);
			}

			throw new NoSuchAssetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				amsAssetId);
		}

		return amsAsset;
	}

	public AMSAsset fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public AMSAsset fetchByPrimaryKey(long amsAssetId)
		throws SystemException {
		AMSAsset amsAsset = (AMSAsset)EntityCacheUtil.getResult(AMSAssetModelImpl.ENTITY_CACHE_ENABLED,
				AMSAssetImpl.class, amsAssetId, this);

		if (amsAsset == null) {
			Session session = null;

			try {
				session = openSession();

				amsAsset = (AMSAsset)session.get(AMSAssetImpl.class,
						new Long(amsAssetId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (amsAsset != null) {
					cacheResult(amsAsset);
				}

				closeSession(session);
			}
		}

		return amsAsset;
	}

	public List<AMSAsset> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<AMSAsset> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	public List<AMSAsset> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<AMSAsset> list = (List<AMSAsset>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_AMSASSET);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}

				sql = _SQL_SELECT_AMSASSET;

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AMSAsset>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AMSAsset>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<AMSAsset>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeAll() throws SystemException {
		for (AMSAsset amsAsset : findAll()) {
			remove(amsAsset);
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

				Query q = session.createQuery(_SQL_COUNT_AMSASSET);

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
						"value.object.listener.com.liferay.ams.model.AMSAsset")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AMSAsset>> listenersList = new ArrayList<ModelListener<AMSAsset>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<AMSAsset>)Class.forName(
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
	private static final String _SQL_SELECT_AMSASSET = "SELECT amsAsset FROM AMSAsset amsAsset";
	private static final String _SQL_COUNT_AMSASSET = "SELECT COUNT(amsAsset) FROM AMSAsset amsAsset";
	private static final String _ORDER_BY_ENTITY_ALIAS = "amsAsset.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AMSAsset exists with the primary key ";
	private static Log _log = LogFactoryUtil.getLog(AMSAssetPersistenceImpl.class);
}