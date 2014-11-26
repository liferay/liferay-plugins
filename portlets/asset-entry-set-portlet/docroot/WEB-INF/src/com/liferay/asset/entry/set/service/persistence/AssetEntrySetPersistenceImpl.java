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

package com.liferay.asset.entry.set.service.persistence;

import com.liferay.asset.entry.set.NoSuchAssetEntrySetException;
import com.liferay.asset.entry.set.model.AssetEntrySet;
import com.liferay.asset.entry.set.model.impl.AssetEntrySetImpl;
import com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl;

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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the asset entry set service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySetPersistence
 * @see AssetEntrySetUtil
 * @generated
 */
public class AssetEntrySetPersistenceImpl extends BasePersistenceImpl<AssetEntrySet>
	implements AssetEntrySetPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AssetEntrySetUtil} to access the asset entry set persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AssetEntrySetImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetModelImpl.FINDER_CACHE_ENABLED,
			AssetEntrySetImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetModelImpl.FINDER_CACHE_ENABLED,
			AssetEntrySetImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public AssetEntrySetPersistenceImpl() {
		setModelClass(AssetEntrySet.class);
	}

	/**
	 * Caches the asset entry set in the entity cache if it is enabled.
	 *
	 * @param assetEntrySet the asset entry set
	 */
	@Override
	public void cacheResult(AssetEntrySet assetEntrySet) {
		EntityCacheUtil.putResult(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetImpl.class, assetEntrySet.getPrimaryKey(),
			assetEntrySet);

		assetEntrySet.resetOriginalValues();
	}

	/**
	 * Caches the asset entry sets in the entity cache if it is enabled.
	 *
	 * @param assetEntrySets the asset entry sets
	 */
	@Override
	public void cacheResult(List<AssetEntrySet> assetEntrySets) {
		for (AssetEntrySet assetEntrySet : assetEntrySets) {
			if (EntityCacheUtil.getResult(
						AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
						AssetEntrySetImpl.class, assetEntrySet.getPrimaryKey()) == null) {
				cacheResult(assetEntrySet);
			}
			else {
				assetEntrySet.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all asset entry sets.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AssetEntrySetImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AssetEntrySetImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the asset entry set.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AssetEntrySet assetEntrySet) {
		EntityCacheUtil.removeResult(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetImpl.class, assetEntrySet.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AssetEntrySet> assetEntrySets) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssetEntrySet assetEntrySet : assetEntrySets) {
			EntityCacheUtil.removeResult(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
				AssetEntrySetImpl.class, assetEntrySet.getPrimaryKey());
		}
	}

	/**
	 * Creates a new asset entry set with the primary key. Does not add the asset entry set to the database.
	 *
	 * @param assetEntrySetId the primary key for the new asset entry set
	 * @return the new asset entry set
	 */
	@Override
	public AssetEntrySet create(long assetEntrySetId) {
		AssetEntrySet assetEntrySet = new AssetEntrySetImpl();

		assetEntrySet.setNew(true);
		assetEntrySet.setPrimaryKey(assetEntrySetId);

		return assetEntrySet;
	}

	/**
	 * Removes the asset entry set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntrySetId the primary key of the asset entry set
	 * @return the asset entry set that was removed
	 * @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet remove(long assetEntrySetId)
		throws NoSuchAssetEntrySetException, SystemException {
		return remove((Serializable)assetEntrySetId);
	}

	/**
	 * Removes the asset entry set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the asset entry set
	 * @return the asset entry set that was removed
	 * @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet remove(Serializable primaryKey)
		throws NoSuchAssetEntrySetException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AssetEntrySet assetEntrySet = (AssetEntrySet)session.get(AssetEntrySetImpl.class,
					primaryKey);

			if (assetEntrySet == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAssetEntrySetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(assetEntrySet);
		}
		catch (NoSuchAssetEntrySetException nsee) {
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
	protected AssetEntrySet removeImpl(AssetEntrySet assetEntrySet)
		throws SystemException {
		assetEntrySet = toUnwrappedModel(assetEntrySet);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(assetEntrySet)) {
				assetEntrySet = (AssetEntrySet)session.get(AssetEntrySetImpl.class,
						assetEntrySet.getPrimaryKeyObj());
			}

			if (assetEntrySet != null) {
				session.delete(assetEntrySet);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (assetEntrySet != null) {
			clearCache(assetEntrySet);
		}

		return assetEntrySet;
	}

	@Override
	public AssetEntrySet updateImpl(
		com.liferay.asset.entry.set.model.AssetEntrySet assetEntrySet)
		throws SystemException {
		assetEntrySet = toUnwrappedModel(assetEntrySet);

		boolean isNew = assetEntrySet.isNew();

		Session session = null;

		try {
			session = openSession();

			if (assetEntrySet.isNew()) {
				session.save(assetEntrySet);

				assetEntrySet.setNew(false);
			}
			else {
				session.merge(assetEntrySet);
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

		EntityCacheUtil.putResult(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
			AssetEntrySetImpl.class, assetEntrySet.getPrimaryKey(),
			assetEntrySet);

		return assetEntrySet;
	}

	protected AssetEntrySet toUnwrappedModel(AssetEntrySet assetEntrySet) {
		if (assetEntrySet instanceof AssetEntrySetImpl) {
			return assetEntrySet;
		}

		AssetEntrySetImpl assetEntrySetImpl = new AssetEntrySetImpl();

		assetEntrySetImpl.setNew(assetEntrySet.isNew());
		assetEntrySetImpl.setPrimaryKey(assetEntrySet.getPrimaryKey());

		assetEntrySetImpl.setAssetEntrySetId(assetEntrySet.getAssetEntrySetId());
		assetEntrySetImpl.setCompanyId(assetEntrySet.getCompanyId());
		assetEntrySetImpl.setUserId(assetEntrySet.getUserId());
		assetEntrySetImpl.setUserName(assetEntrySet.getUserName());
		assetEntrySetImpl.setCreateTime(assetEntrySet.getCreateTime());
		assetEntrySetImpl.setModifiedTime(assetEntrySet.getModifiedTime());
		assetEntrySetImpl.setAssetEntryId(assetEntrySet.getAssetEntryId());
		assetEntrySetImpl.setParentAssetEntrySetId(assetEntrySet.getParentAssetEntrySetId());
		assetEntrySetImpl.setCreatorClassNameId(assetEntrySet.getCreatorClassNameId());
		assetEntrySetImpl.setCreatorClassPK(assetEntrySet.getCreatorClassPK());
		assetEntrySetImpl.setContent(assetEntrySet.getContent());
		assetEntrySetImpl.setData(assetEntrySet.getData());

		return assetEntrySetImpl;
	}

	/**
	 * Returns the asset entry set with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset entry set
	 * @return the asset entry set
	 * @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAssetEntrySetException, SystemException {
		AssetEntrySet assetEntrySet = fetchByPrimaryKey(primaryKey);

		if (assetEntrySet == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAssetEntrySetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return assetEntrySet;
	}

	/**
	 * Returns the asset entry set with the primary key or throws a {@link com.liferay.asset.entry.set.NoSuchAssetEntrySetException} if it could not be found.
	 *
	 * @param assetEntrySetId the primary key of the asset entry set
	 * @return the asset entry set
	 * @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet findByPrimaryKey(long assetEntrySetId)
		throws NoSuchAssetEntrySetException, SystemException {
		return findByPrimaryKey((Serializable)assetEntrySetId);
	}

	/**
	 * Returns the asset entry set with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset entry set
	 * @return the asset entry set, or <code>null</code> if a asset entry set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		AssetEntrySet assetEntrySet = (AssetEntrySet)EntityCacheUtil.getResult(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
				AssetEntrySetImpl.class, primaryKey);

		if (assetEntrySet == _nullAssetEntrySet) {
			return null;
		}

		if (assetEntrySet == null) {
			Session session = null;

			try {
				session = openSession();

				assetEntrySet = (AssetEntrySet)session.get(AssetEntrySetImpl.class,
						primaryKey);

				if (assetEntrySet != null) {
					cacheResult(assetEntrySet);
				}
				else {
					EntityCacheUtil.putResult(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
						AssetEntrySetImpl.class, primaryKey, _nullAssetEntrySet);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(AssetEntrySetModelImpl.ENTITY_CACHE_ENABLED,
					AssetEntrySetImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return assetEntrySet;
	}

	/**
	 * Returns the asset entry set with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetEntrySetId the primary key of the asset entry set
	 * @return the asset entry set, or <code>null</code> if a asset entry set with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetEntrySet fetchByPrimaryKey(long assetEntrySetId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)assetEntrySetId);
	}

	/**
	 * Returns all the asset entry sets.
	 *
	 * @return the asset entry sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntrySet> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the asset entry sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @return the range of asset entry sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntrySet> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the asset entry sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry sets
	 * @param end the upper bound of the range of asset entry sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset entry sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetEntrySet> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
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

		List<AssetEntrySet> list = (List<AssetEntrySet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ASSETENTRYSET);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ASSETENTRYSET;

				if (pagination) {
					sql = sql.concat(AssetEntrySetModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AssetEntrySet>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<AssetEntrySet>(list);
				}
				else {
					list = (List<AssetEntrySet>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the asset entry sets from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (AssetEntrySet assetEntrySet : findAll()) {
			remove(assetEntrySet);
		}
	}

	/**
	 * Returns the number of asset entry sets.
	 *
	 * @return the number of asset entry sets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ASSETENTRYSET);

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

	/**
	 * Initializes the asset entry set persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.asset.entry.set.model.AssetEntrySet")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AssetEntrySet>> listenersList = new ArrayList<ModelListener<AssetEntrySet>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<AssetEntrySet>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(AssetEntrySetImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_ASSETENTRYSET = "SELECT assetEntrySet FROM AssetEntrySet assetEntrySet";
	private static final String _SQL_COUNT_ASSETENTRYSET = "SELECT COUNT(assetEntrySet) FROM AssetEntrySet assetEntrySet";
	private static final String _ORDER_BY_ENTITY_ALIAS = "assetEntrySet.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AssetEntrySet exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AssetEntrySetPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"data"
			});
	private static AssetEntrySet _nullAssetEntrySet = new AssetEntrySetImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AssetEntrySet> toCacheModel() {
				return _nullAssetEntrySetCacheModel;
			}
		};

	private static CacheModel<AssetEntrySet> _nullAssetEntrySetCacheModel = new CacheModel<AssetEntrySet>() {
			@Override
			public AssetEntrySet toEntityModel() {
				return _nullAssetEntrySet;
			}
		};
}