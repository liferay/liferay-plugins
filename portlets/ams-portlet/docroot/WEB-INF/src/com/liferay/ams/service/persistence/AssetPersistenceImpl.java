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
import com.liferay.ams.model.Asset;
import com.liferay.ams.model.impl.AssetImpl;
import com.liferay.ams.model.impl.AssetModelImpl;

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
 * @author    Brian Wing Shun Chan
 * @see       AssetPersistence
 * @see       AssetUtil
 * @generated
 */
public class AssetPersistenceImpl extends BasePersistenceImpl<Asset>
	implements AssetPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = AssetImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(AssetModelImpl.ENTITY_CACHE_ENABLED,
			AssetModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssetModelImpl.ENTITY_CACHE_ENABLED,
			AssetModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(Asset asset) {
		EntityCacheUtil.putResult(AssetModelImpl.ENTITY_CACHE_ENABLED,
			AssetImpl.class, asset.getPrimaryKey(), asset);
	}

	public void cacheResult(List<Asset> assets) {
		for (Asset asset : assets) {
			if (EntityCacheUtil.getResult(AssetModelImpl.ENTITY_CACHE_ENABLED,
						AssetImpl.class, asset.getPrimaryKey(), this) == null) {
				cacheResult(asset);
			}
		}
	}

	public void clearCache() {
		CacheRegistryUtil.clear(AssetImpl.class.getName());
		EntityCacheUtil.clearCache(AssetImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public void clearCache(Asset asset) {
		EntityCacheUtil.removeResult(AssetModelImpl.ENTITY_CACHE_ENABLED,
			AssetImpl.class, asset.getPrimaryKey());
	}

	public Asset create(long assetId) {
		Asset asset = new AssetImpl();

		asset.setNew(true);
		asset.setPrimaryKey(assetId);

		return asset;
	}

	public Asset remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public Asset remove(long assetId)
		throws NoSuchAssetException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Asset asset = (Asset)session.get(AssetImpl.class, new Long(assetId));

			if (asset == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + assetId);
				}

				throw new NoSuchAssetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					assetId);
			}

			return remove(asset);
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

	protected Asset removeImpl(Asset asset) throws SystemException {
		asset = toUnwrappedModel(asset);

		Session session = null;

		try {
			session = openSession();

			if (asset.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(AssetImpl.class,
						asset.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(asset);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(AssetModelImpl.ENTITY_CACHE_ENABLED,
			AssetImpl.class, asset.getPrimaryKey());

		return asset;
	}

	public Asset updateImpl(com.liferay.ams.model.Asset asset, boolean merge)
		throws SystemException {
		asset = toUnwrappedModel(asset);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, asset, merge);

			asset.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(AssetModelImpl.ENTITY_CACHE_ENABLED,
			AssetImpl.class, asset.getPrimaryKey(), asset);

		return asset;
	}

	protected Asset toUnwrappedModel(Asset asset) {
		if (asset instanceof AssetImpl) {
			return asset;
		}

		AssetImpl assetImpl = new AssetImpl();

		assetImpl.setNew(asset.isNew());
		assetImpl.setPrimaryKey(asset.getPrimaryKey());

		assetImpl.setAssetId(asset.getAssetId());
		assetImpl.setCompanyId(asset.getCompanyId());
		assetImpl.setUserId(asset.getUserId());
		assetImpl.setUserName(asset.getUserName());
		assetImpl.setCreateDate(asset.getCreateDate());
		assetImpl.setModifiedDate(asset.getModifiedDate());
		assetImpl.setDefinitionId(asset.getDefinitionId());
		assetImpl.setSerialNumber(asset.getSerialNumber());
		assetImpl.setInactiveDate(asset.getInactiveDate());
		assetImpl.setActive(asset.isActive());

		return assetImpl;
	}

	public Asset findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public Asset findByPrimaryKey(long assetId)
		throws NoSuchAssetException, SystemException {
		Asset asset = fetchByPrimaryKey(assetId);

		if (asset == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + assetId);
			}

			throw new NoSuchAssetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				assetId);
		}

		return asset;
	}

	public Asset fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public Asset fetchByPrimaryKey(long assetId) throws SystemException {
		Asset asset = (Asset)EntityCacheUtil.getResult(AssetModelImpl.ENTITY_CACHE_ENABLED,
				AssetImpl.class, assetId, this);

		if (asset == null) {
			Session session = null;

			try {
				session = openSession();

				asset = (Asset)session.get(AssetImpl.class, new Long(assetId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (asset != null) {
					cacheResult(asset);
				}

				closeSession(session);
			}
		}

		return asset;
	}

	public List<Asset> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<Asset> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	public List<Asset> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Asset> list = (List<Asset>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_ASSET);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_ASSET;
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Asset>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Asset>)QueryUtil.list(q, getDialect(), start,
							end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Asset>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeAll() throws SystemException {
		for (Asset asset : findAll()) {
			remove(asset);
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

				Query q = session.createQuery(_SQL_COUNT_ASSET);

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
						"value.object.listener.com.liferay.ams.model.Asset")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Asset>> listenersList = new ArrayList<ModelListener<Asset>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Asset>)InstanceFactory.newInstance(
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
	private static final String _SQL_SELECT_ASSET = "SELECT asset FROM Asset asset";
	private static final String _SQL_COUNT_ASSET = "SELECT COUNT(asset) FROM Asset asset";
	private static final String _ORDER_BY_ENTITY_ALIAS = "asset.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Asset exists with the primary key ";
	private static Log _log = LogFactoryUtil.getLog(AssetPersistenceImpl.class);
}