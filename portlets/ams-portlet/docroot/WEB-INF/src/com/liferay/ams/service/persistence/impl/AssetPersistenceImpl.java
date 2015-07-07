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

package com.liferay.ams.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.ams.NoSuchAssetException;
import com.liferay.ams.model.Asset;
import com.liferay.ams.model.impl.AssetImpl;
import com.liferay.ams.model.impl.AssetModelImpl;
import com.liferay.ams.service.persistence.AssetPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

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
 * The persistence implementation for the asset service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetPersistence
 * @see com.liferay.ams.service.persistence.AssetUtil
 * @generated
 */
@ProviderType
public class AssetPersistenceImpl extends BasePersistenceImpl<Asset>
	implements AssetPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AssetUtil} to access the asset persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssetImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AssetModelImpl.ENTITY_CACHE_ENABLED,
			AssetModelImpl.FINDER_CACHE_ENABLED, AssetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AssetModelImpl.ENTITY_CACHE_ENABLED,
			AssetModelImpl.FINDER_CACHE_ENABLED, AssetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssetModelImpl.ENTITY_CACHE_ENABLED,
			AssetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public AssetPersistenceImpl() {
		setModelClass(Asset.class);
	}

	/**
	 * Caches the asset in the entity cache if it is enabled.
	 *
	 * @param asset the asset
	 */
	@Override
	public void cacheResult(Asset asset) {
		EntityCacheUtil.putResult(AssetModelImpl.ENTITY_CACHE_ENABLED,
			AssetImpl.class, asset.getPrimaryKey(), asset);

		asset.resetOriginalValues();
	}

	/**
	 * Caches the assets in the entity cache if it is enabled.
	 *
	 * @param assets the assets
	 */
	@Override
	public void cacheResult(List<Asset> assets) {
		for (Asset asset : assets) {
			if (EntityCacheUtil.getResult(AssetModelImpl.ENTITY_CACHE_ENABLED,
						AssetImpl.class, asset.getPrimaryKey()) == null) {
				cacheResult(asset);
			}
			else {
				asset.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all assets.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(AssetImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the asset.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Asset asset) {
		EntityCacheUtil.removeResult(AssetModelImpl.ENTITY_CACHE_ENABLED,
			AssetImpl.class, asset.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Asset> assets) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Asset asset : assets) {
			EntityCacheUtil.removeResult(AssetModelImpl.ENTITY_CACHE_ENABLED,
				AssetImpl.class, asset.getPrimaryKey());
		}
	}

	/**
	 * Creates a new asset with the primary key. Does not add the asset to the database.
	 *
	 * @param assetId the primary key for the new asset
	 * @return the new asset
	 */
	@Override
	public Asset create(long assetId) {
		Asset asset = new AssetImpl();

		asset.setNew(true);
		asset.setPrimaryKey(assetId);

		return asset;
	}

	/**
	 * Removes the asset with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetId the primary key of the asset
	 * @return the asset that was removed
	 * @throws NoSuchAssetException if a asset with the primary key could not be found
	 */
	@Override
	public Asset remove(long assetId) throws NoSuchAssetException {
		return remove((Serializable)assetId);
	}

	/**
	 * Removes the asset with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the asset
	 * @return the asset that was removed
	 * @throws NoSuchAssetException if a asset with the primary key could not be found
	 */
	@Override
	public Asset remove(Serializable primaryKey) throws NoSuchAssetException {
		Session session = null;

		try {
			session = openSession();

			Asset asset = (Asset)session.get(AssetImpl.class, primaryKey);

			if (asset == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAssetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
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

	@Override
	protected Asset removeImpl(Asset asset) {
		asset = toUnwrappedModel(asset);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(asset)) {
				asset = (Asset)session.get(AssetImpl.class,
						asset.getPrimaryKeyObj());
			}

			if (asset != null) {
				session.delete(asset);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (asset != null) {
			clearCache(asset);
		}

		return asset;
	}

	@Override
	public Asset updateImpl(Asset asset) {
		asset = toUnwrappedModel(asset);

		boolean isNew = asset.isNew();

		AssetModelImpl assetModelImpl = (AssetModelImpl)asset;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (asset.getCreateDate() == null)) {
			if (serviceContext == null) {
				asset.setCreateDate(now);
			}
			else {
				asset.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!assetModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				asset.setModifiedDate(now);
			}
			else {
				asset.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (asset.isNew()) {
				session.save(asset);

				asset.setNew(false);
			}
			else {
				session.merge(asset);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(AssetModelImpl.ENTITY_CACHE_ENABLED,
			AssetImpl.class, asset.getPrimaryKey(), asset, false);

		asset.resetOriginalValues();

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

	/**
	 * Returns the asset with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset
	 * @return the asset
	 * @throws NoSuchAssetException if a asset with the primary key could not be found
	 */
	@Override
	public Asset findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAssetException {
		Asset asset = fetchByPrimaryKey(primaryKey);

		if (asset == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAssetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return asset;
	}

	/**
	 * Returns the asset with the primary key or throws a {@link NoSuchAssetException} if it could not be found.
	 *
	 * @param assetId the primary key of the asset
	 * @return the asset
	 * @throws NoSuchAssetException if a asset with the primary key could not be found
	 */
	@Override
	public Asset findByPrimaryKey(long assetId) throws NoSuchAssetException {
		return findByPrimaryKey((Serializable)assetId);
	}

	/**
	 * Returns the asset with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset
	 * @return the asset, or <code>null</code> if a asset with the primary key could not be found
	 */
	@Override
	public Asset fetchByPrimaryKey(Serializable primaryKey) {
		Asset asset = (Asset)EntityCacheUtil.getResult(AssetModelImpl.ENTITY_CACHE_ENABLED,
				AssetImpl.class, primaryKey);

		if (asset == _nullAsset) {
			return null;
		}

		if (asset == null) {
			Session session = null;

			try {
				session = openSession();

				asset = (Asset)session.get(AssetImpl.class, primaryKey);

				if (asset != null) {
					cacheResult(asset);
				}
				else {
					EntityCacheUtil.putResult(AssetModelImpl.ENTITY_CACHE_ENABLED,
						AssetImpl.class, primaryKey, _nullAsset);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(AssetModelImpl.ENTITY_CACHE_ENABLED,
					AssetImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return asset;
	}

	/**
	 * Returns the asset with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetId the primary key of the asset
	 * @return the asset, or <code>null</code> if a asset with the primary key could not be found
	 */
	@Override
	public Asset fetchByPrimaryKey(long assetId) {
		return fetchByPrimaryKey((Serializable)assetId);
	}

	@Override
	public Map<Serializable, Asset> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Asset> map = new HashMap<Serializable, Asset>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Asset asset = fetchByPrimaryKey(primaryKey);

			if (asset != null) {
				map.put(primaryKey, asset);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Asset asset = (Asset)EntityCacheUtil.getResult(AssetModelImpl.ENTITY_CACHE_ENABLED,
					AssetImpl.class, primaryKey);

			if (asset == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, asset);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ASSET_WHERE_PKS_IN);

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

			for (Asset asset : (List<Asset>)q.list()) {
				map.put(asset.getPrimaryKeyObj(), asset);

				cacheResult(asset);

				uncachedPrimaryKeys.remove(asset.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(AssetModelImpl.ENTITY_CACHE_ENABLED,
					AssetImpl.class, primaryKey, _nullAsset);
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
	 * Returns all the assets.
	 *
	 * @return the assets
	 */
	@Override
	public List<Asset> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the assets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of assets
	 * @param end the upper bound of the range of assets (not inclusive)
	 * @return the range of assets
	 */
	@Override
	public List<Asset> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the assets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of assets
	 * @param end the upper bound of the range of assets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of assets
	 */
	@Override
	public List<Asset> findAll(int start, int end,
		OrderByComparator<Asset> orderByComparator) {
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

		List<Asset> list = (List<Asset>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
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

				if (pagination) {
					sql = sql.concat(AssetModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Asset>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Asset>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the assets from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Asset asset : findAll()) {
			remove(asset);
		}
	}

	/**
	 * Returns the number of assets.
	 *
	 * @return the number of assets
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ASSET);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
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
	protected Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return AssetModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the asset persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(AssetImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_ASSET = "SELECT asset FROM Asset asset";
	private static final String _SQL_SELECT_ASSET_WHERE_PKS_IN = "SELECT asset FROM Asset asset WHERE assetId IN (";
	private static final String _SQL_COUNT_ASSET = "SELECT COUNT(asset) FROM Asset asset";
	private static final String _ORDER_BY_ENTITY_ALIAS = "asset.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Asset exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(AssetPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"active"
			});
	private static final Asset _nullAsset = new AssetImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Asset> toCacheModel() {
				return _nullAssetCacheModel;
			}
		};

	private static final CacheModel<Asset> _nullAssetCacheModel = new CacheModel<Asset>() {
			@Override
			public Asset toEntityModel() {
				return _nullAsset;
			}
		};
}