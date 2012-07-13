/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.so.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.CompanyPersistence;
import com.liferay.portal.service.persistence.GroupPersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.so.NoSuchFavoriteSiteException;
import com.liferay.so.model.FavoriteSite;
import com.liferay.so.model.impl.FavoriteSiteImpl;
import com.liferay.so.model.impl.FavoriteSiteModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the favorite site service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FavoriteSitePersistence
 * @see FavoriteSiteUtil
 * @generated
 */
public class FavoriteSitePersistenceImpl extends BasePersistenceImpl<FavoriteSite>
	implements FavoriteSitePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link FavoriteSiteUtil} to access the favorite site persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = FavoriteSiteImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(FavoriteSiteModelImpl.ENTITY_CACHE_ENABLED,
			FavoriteSiteModelImpl.FINDER_CACHE_ENABLED, FavoriteSiteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(FavoriteSiteModelImpl.ENTITY_CACHE_ENABLED,
			FavoriteSiteModelImpl.FINDER_CACHE_ENABLED, FavoriteSiteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			FavoriteSiteModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(FavoriteSiteModelImpl.ENTITY_CACHE_ENABLED,
			FavoriteSiteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U = new FinderPath(FavoriteSiteModelImpl.ENTITY_CACHE_ENABLED,
			FavoriteSiteModelImpl.FINDER_CACHE_ENABLED, FavoriteSiteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U = new FinderPath(FavoriteSiteModelImpl.ENTITY_CACHE_ENABLED,
			FavoriteSiteModelImpl.FINDER_CACHE_ENABLED, FavoriteSiteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_U",
			new String[] { Long.class.getName(), Long.class.getName() },
			FavoriteSiteModelImpl.GROUPID_COLUMN_BITMASK |
			FavoriteSiteModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_U = new FinderPath(FavoriteSiteModelImpl.ENTITY_CACHE_ENABLED,
			FavoriteSiteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_U",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FavoriteSiteModelImpl.ENTITY_CACHE_ENABLED,
			FavoriteSiteModelImpl.FINDER_CACHE_ENABLED, FavoriteSiteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FavoriteSiteModelImpl.ENTITY_CACHE_ENABLED,
			FavoriteSiteModelImpl.FINDER_CACHE_ENABLED, FavoriteSiteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FavoriteSiteModelImpl.ENTITY_CACHE_ENABLED,
			FavoriteSiteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the favorite site in the entity cache if it is enabled.
	 *
	 * @param favoriteSite the favorite site
	 */
	public void cacheResult(FavoriteSite favoriteSite) {
		EntityCacheUtil.putResult(FavoriteSiteModelImpl.ENTITY_CACHE_ENABLED,
			FavoriteSiteImpl.class, favoriteSite.getPrimaryKey(), favoriteSite);

		favoriteSite.resetOriginalValues();
	}

	/**
	 * Caches the favorite sites in the entity cache if it is enabled.
	 *
	 * @param favoriteSites the favorite sites
	 */
	public void cacheResult(List<FavoriteSite> favoriteSites) {
		for (FavoriteSite favoriteSite : favoriteSites) {
			if (EntityCacheUtil.getResult(
						FavoriteSiteModelImpl.ENTITY_CACHE_ENABLED,
						FavoriteSiteImpl.class, favoriteSite.getPrimaryKey()) == null) {
				cacheResult(favoriteSite);
			}
			else {
				favoriteSite.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all favorite sites.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(FavoriteSiteImpl.class.getName());
		}

		EntityCacheUtil.clearCache(FavoriteSiteImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the favorite site.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(FavoriteSite favoriteSite) {
		EntityCacheUtil.removeResult(FavoriteSiteModelImpl.ENTITY_CACHE_ENABLED,
			FavoriteSiteImpl.class, favoriteSite.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<FavoriteSite> favoriteSites) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (FavoriteSite favoriteSite : favoriteSites) {
			EntityCacheUtil.removeResult(FavoriteSiteModelImpl.ENTITY_CACHE_ENABLED,
				FavoriteSiteImpl.class, favoriteSite.getPrimaryKey());
		}
	}

	/**
	 * Creates a new favorite site with the primary key. Does not add the favorite site to the database.
	 *
	 * @param favoriteSiteId the primary key for the new favorite site
	 * @return the new favorite site
	 */
	public FavoriteSite create(long favoriteSiteId) {
		FavoriteSite favoriteSite = new FavoriteSiteImpl();

		favoriteSite.setNew(true);
		favoriteSite.setPrimaryKey(favoriteSiteId);

		return favoriteSite;
	}

	/**
	 * Removes the favorite site with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param favoriteSiteId the primary key of the favorite site
	 * @return the favorite site that was removed
	 * @throws com.liferay.so.NoSuchFavoriteSiteException if a favorite site with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FavoriteSite remove(long favoriteSiteId)
		throws NoSuchFavoriteSiteException, SystemException {
		return remove(Long.valueOf(favoriteSiteId));
	}

	/**
	 * Removes the favorite site with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the favorite site
	 * @return the favorite site that was removed
	 * @throws com.liferay.so.NoSuchFavoriteSiteException if a favorite site with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public FavoriteSite remove(Serializable primaryKey)
		throws NoSuchFavoriteSiteException, SystemException {
		Session session = null;

		try {
			session = openSession();

			FavoriteSite favoriteSite = (FavoriteSite)session.get(FavoriteSiteImpl.class,
					primaryKey);

			if (favoriteSite == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFavoriteSiteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(favoriteSite);
		}
		catch (NoSuchFavoriteSiteException nsee) {
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
	protected FavoriteSite removeImpl(FavoriteSite favoriteSite)
		throws SystemException {
		favoriteSite = toUnwrappedModel(favoriteSite);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, favoriteSite);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(favoriteSite);

		return favoriteSite;
	}

	@Override
	public FavoriteSite updateImpl(
		com.liferay.so.model.FavoriteSite favoriteSite, boolean merge)
		throws SystemException {
		favoriteSite = toUnwrappedModel(favoriteSite);

		boolean isNew = favoriteSite.isNew();

		FavoriteSiteModelImpl favoriteSiteModelImpl = (FavoriteSiteModelImpl)favoriteSite;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, favoriteSite, merge);

			favoriteSite.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !FavoriteSiteModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((favoriteSiteModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(favoriteSiteModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] {
						Long.valueOf(favoriteSiteModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((favoriteSiteModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(favoriteSiteModelImpl.getOriginalGroupId()),
						Long.valueOf(favoriteSiteModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_U, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U,
					args);

				args = new Object[] {
						Long.valueOf(favoriteSiteModelImpl.getGroupId()),
						Long.valueOf(favoriteSiteModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_U, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U,
					args);
			}
		}

		EntityCacheUtil.putResult(FavoriteSiteModelImpl.ENTITY_CACHE_ENABLED,
			FavoriteSiteImpl.class, favoriteSite.getPrimaryKey(), favoriteSite);

		return favoriteSite;
	}

	protected FavoriteSite toUnwrappedModel(FavoriteSite favoriteSite) {
		if (favoriteSite instanceof FavoriteSiteImpl) {
			return favoriteSite;
		}

		FavoriteSiteImpl favoriteSiteImpl = new FavoriteSiteImpl();

		favoriteSiteImpl.setNew(favoriteSite.isNew());
		favoriteSiteImpl.setPrimaryKey(favoriteSite.getPrimaryKey());

		favoriteSiteImpl.setFavoriteSiteId(favoriteSite.getFavoriteSiteId());
		favoriteSiteImpl.setGroupId(favoriteSite.getGroupId());
		favoriteSiteImpl.setCompanyId(favoriteSite.getCompanyId());
		favoriteSiteImpl.setUserId(favoriteSite.getUserId());

		return favoriteSiteImpl;
	}

	/**
	 * Returns the favorite site with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the favorite site
	 * @return the favorite site
	 * @throws com.liferay.portal.NoSuchModelException if a favorite site with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public FavoriteSite findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the favorite site with the primary key or throws a {@link com.liferay.so.NoSuchFavoriteSiteException} if it could not be found.
	 *
	 * @param favoriteSiteId the primary key of the favorite site
	 * @return the favorite site
	 * @throws com.liferay.so.NoSuchFavoriteSiteException if a favorite site with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FavoriteSite findByPrimaryKey(long favoriteSiteId)
		throws NoSuchFavoriteSiteException, SystemException {
		FavoriteSite favoriteSite = fetchByPrimaryKey(favoriteSiteId);

		if (favoriteSite == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + favoriteSiteId);
			}

			throw new NoSuchFavoriteSiteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				favoriteSiteId);
		}

		return favoriteSite;
	}

	/**
	 * Returns the favorite site with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the favorite site
	 * @return the favorite site, or <code>null</code> if a favorite site with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public FavoriteSite fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the favorite site with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param favoriteSiteId the primary key of the favorite site
	 * @return the favorite site, or <code>null</code> if a favorite site with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FavoriteSite fetchByPrimaryKey(long favoriteSiteId)
		throws SystemException {
		FavoriteSite favoriteSite = (FavoriteSite)EntityCacheUtil.getResult(FavoriteSiteModelImpl.ENTITY_CACHE_ENABLED,
				FavoriteSiteImpl.class, favoriteSiteId);

		if (favoriteSite == _nullFavoriteSite) {
			return null;
		}

		if (favoriteSite == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				favoriteSite = (FavoriteSite)session.get(FavoriteSiteImpl.class,
						Long.valueOf(favoriteSiteId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (favoriteSite != null) {
					cacheResult(favoriteSite);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(FavoriteSiteModelImpl.ENTITY_CACHE_ENABLED,
						FavoriteSiteImpl.class, favoriteSiteId,
						_nullFavoriteSite);
				}

				closeSession(session);
			}
		}

		return favoriteSite;
	}

	/**
	 * Returns all the favorite sites where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching favorite sites
	 * @throws SystemException if a system exception occurred
	 */
	public List<FavoriteSite> findByUserId(long userId)
		throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the favorite sites where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of favorite sites
	 * @param end the upper bound of the range of favorite sites (not inclusive)
	 * @return the range of matching favorite sites
	 * @throws SystemException if a system exception occurred
	 */
	public List<FavoriteSite> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the favorite sites where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of favorite sites
	 * @param end the upper bound of the range of favorite sites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching favorite sites
	 * @throws SystemException if a system exception occurred
	 */
	public List<FavoriteSite> findByUserId(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<FavoriteSite> list = (List<FavoriteSite>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (FavoriteSite favoriteSite : list) {
				if ((userId != favoriteSite.getUserId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_FAVORITESITE_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = (List<FavoriteSite>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first favorite site in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching favorite site
	 * @throws com.liferay.so.NoSuchFavoriteSiteException if a matching favorite site could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FavoriteSite findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchFavoriteSiteException, SystemException {
		FavoriteSite favoriteSite = fetchByUserId_First(userId,
				orderByComparator);

		if (favoriteSite != null) {
			return favoriteSite;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFavoriteSiteException(msg.toString());
	}

	/**
	 * Returns the first favorite site in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching favorite site, or <code>null</code> if a matching favorite site could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FavoriteSite fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<FavoriteSite> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last favorite site in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching favorite site
	 * @throws com.liferay.so.NoSuchFavoriteSiteException if a matching favorite site could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FavoriteSite findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchFavoriteSiteException, SystemException {
		FavoriteSite favoriteSite = fetchByUserId_Last(userId, orderByComparator);

		if (favoriteSite != null) {
			return favoriteSite;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFavoriteSiteException(msg.toString());
	}

	/**
	 * Returns the last favorite site in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching favorite site, or <code>null</code> if a matching favorite site could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FavoriteSite fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		List<FavoriteSite> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the favorite sites before and after the current favorite site in the ordered set where userId = &#63;.
	 *
	 * @param favoriteSiteId the primary key of the current favorite site
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next favorite site
	 * @throws com.liferay.so.NoSuchFavoriteSiteException if a favorite site with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FavoriteSite[] findByUserId_PrevAndNext(long favoriteSiteId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchFavoriteSiteException, SystemException {
		FavoriteSite favoriteSite = findByPrimaryKey(favoriteSiteId);

		Session session = null;

		try {
			session = openSession();

			FavoriteSite[] array = new FavoriteSiteImpl[3];

			array[0] = getByUserId_PrevAndNext(session, favoriteSite, userId,
					orderByComparator, true);

			array[1] = favoriteSite;

			array[2] = getByUserId_PrevAndNext(session, favoriteSite, userId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FavoriteSite getByUserId_PrevAndNext(Session session,
		FavoriteSite favoriteSite, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_FAVORITESITE_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(favoriteSite);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FavoriteSite> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the favorite sites where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching favorite sites
	 * @throws SystemException if a system exception occurred
	 */
	public List<FavoriteSite> findByG_U(long groupId, long userId)
		throws SystemException {
		return findByG_U(groupId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the favorite sites where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of favorite sites
	 * @param end the upper bound of the range of favorite sites (not inclusive)
	 * @return the range of matching favorite sites
	 * @throws SystemException if a system exception occurred
	 */
	public List<FavoriteSite> findByG_U(long groupId, long userId, int start,
		int end) throws SystemException {
		return findByG_U(groupId, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the favorite sites where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of favorite sites
	 * @param end the upper bound of the range of favorite sites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching favorite sites
	 * @throws SystemException if a system exception occurred
	 */
	public List<FavoriteSite> findByG_U(long groupId, long userId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U;
			finderArgs = new Object[] { groupId, userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U;
			finderArgs = new Object[] {
					groupId, userId,
					
					start, end, orderByComparator
				};
		}

		List<FavoriteSite> list = (List<FavoriteSite>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (FavoriteSite favoriteSite : list) {
				if ((groupId != favoriteSite.getGroupId()) ||
						(userId != favoriteSite.getUserId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_FAVORITESITE_WHERE);

			query.append(_FINDER_COLUMN_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				list = (List<FavoriteSite>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first favorite site in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching favorite site
	 * @throws com.liferay.so.NoSuchFavoriteSiteException if a matching favorite site could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FavoriteSite findByG_U_First(long groupId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchFavoriteSiteException, SystemException {
		FavoriteSite favoriteSite = fetchByG_U_First(groupId, userId,
				orderByComparator);

		if (favoriteSite != null) {
			return favoriteSite;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFavoriteSiteException(msg.toString());
	}

	/**
	 * Returns the first favorite site in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching favorite site, or <code>null</code> if a matching favorite site could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FavoriteSite fetchByG_U_First(long groupId, long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<FavoriteSite> list = findByG_U(groupId, userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last favorite site in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching favorite site
	 * @throws com.liferay.so.NoSuchFavoriteSiteException if a matching favorite site could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FavoriteSite findByG_U_Last(long groupId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchFavoriteSiteException, SystemException {
		FavoriteSite favoriteSite = fetchByG_U_Last(groupId, userId,
				orderByComparator);

		if (favoriteSite != null) {
			return favoriteSite;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFavoriteSiteException(msg.toString());
	}

	/**
	 * Returns the last favorite site in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching favorite site, or <code>null</code> if a matching favorite site could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FavoriteSite fetchByG_U_Last(long groupId, long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_U(groupId, userId);

		List<FavoriteSite> list = findByG_U(groupId, userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the favorite sites before and after the current favorite site in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param favoriteSiteId the primary key of the current favorite site
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next favorite site
	 * @throws com.liferay.so.NoSuchFavoriteSiteException if a favorite site with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public FavoriteSite[] findByG_U_PrevAndNext(long favoriteSiteId,
		long groupId, long userId, OrderByComparator orderByComparator)
		throws NoSuchFavoriteSiteException, SystemException {
		FavoriteSite favoriteSite = findByPrimaryKey(favoriteSiteId);

		Session session = null;

		try {
			session = openSession();

			FavoriteSite[] array = new FavoriteSiteImpl[3];

			array[0] = getByG_U_PrevAndNext(session, favoriteSite, groupId,
					userId, orderByComparator, true);

			array[1] = favoriteSite;

			array[2] = getByG_U_PrevAndNext(session, favoriteSite, groupId,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FavoriteSite getByG_U_PrevAndNext(Session session,
		FavoriteSite favoriteSite, long groupId, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_FAVORITESITE_WHERE);

		query.append(_FINDER_COLUMN_G_U_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_USERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(favoriteSite);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FavoriteSite> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the favorite sites.
	 *
	 * @return the favorite sites
	 * @throws SystemException if a system exception occurred
	 */
	public List<FavoriteSite> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the favorite sites.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of favorite sites
	 * @param end the upper bound of the range of favorite sites (not inclusive)
	 * @return the range of favorite sites
	 * @throws SystemException if a system exception occurred
	 */
	public List<FavoriteSite> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the favorite sites.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of favorite sites
	 * @param end the upper bound of the range of favorite sites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of favorite sites
	 * @throws SystemException if a system exception occurred
	 */
	public List<FavoriteSite> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<FavoriteSite> list = (List<FavoriteSite>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_FAVORITESITE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_FAVORITESITE;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<FavoriteSite>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<FavoriteSite>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the favorite sites where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (FavoriteSite favoriteSite : findByUserId(userId)) {
			remove(favoriteSite);
		}
	}

	/**
	 * Removes all the favorite sites where groupId = &#63; and userId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_U(long groupId, long userId)
		throws SystemException {
		for (FavoriteSite favoriteSite : findByG_U(groupId, userId)) {
			remove(favoriteSite);
		}
	}

	/**
	 * Removes all the favorite sites from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (FavoriteSite favoriteSite : findAll()) {
			remove(favoriteSite);
		}
	}

	/**
	 * Returns the number of favorite sites where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching favorite sites
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FAVORITESITE_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of favorite sites where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching favorite sites
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_U(long groupId, long userId) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_U,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_FAVORITESITE_WHERE);

			query.append(_FINDER_COLUMN_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_U, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of favorite sites.
	 *
	 * @return the number of favorite sites
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_FAVORITESITE);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the favorite site persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.so.model.FavoriteSite")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<FavoriteSite>> listenersList = new ArrayList<ModelListener<FavoriteSite>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<FavoriteSite>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(FavoriteSiteImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = FavoriteSitePersistence.class)
	protected FavoriteSitePersistence favoriteSitePersistence;
	@BeanReference(type = MemberRequestPersistence.class)
	protected MemberRequestPersistence memberRequestPersistence;
	@BeanReference(type = ProjectsEntryPersistence.class)
	protected ProjectsEntryPersistence projectsEntryPersistence;
	@BeanReference(type = CompanyPersistence.class)
	protected CompanyPersistence companyPersistence;
	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_FAVORITESITE = "SELECT favoriteSite FROM FavoriteSite favoriteSite";
	private static final String _SQL_SELECT_FAVORITESITE_WHERE = "SELECT favoriteSite FROM FavoriteSite favoriteSite WHERE ";
	private static final String _SQL_COUNT_FAVORITESITE = "SELECT COUNT(favoriteSite) FROM FavoriteSite favoriteSite";
	private static final String _SQL_COUNT_FAVORITESITE_WHERE = "SELECT COUNT(favoriteSite) FROM FavoriteSite favoriteSite WHERE ";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "favoriteSite.userId = ?";
	private static final String _FINDER_COLUMN_G_U_GROUPID_2 = "favoriteSite.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_USERID_2 = "favoriteSite.userId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "favoriteSite.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No FavoriteSite exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No FavoriteSite exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(FavoriteSitePersistenceImpl.class);
	private static FavoriteSite _nullFavoriteSite = new FavoriteSiteImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<FavoriteSite> toCacheModel() {
				return _nullFavoriteSiteCacheModel;
			}
		};

	private static CacheModel<FavoriteSite> _nullFavoriteSiteCacheModel = new CacheModel<FavoriteSite>() {
			public FavoriteSite toEntityModel() {
				return _nullFavoriteSite;
			}
		};
}