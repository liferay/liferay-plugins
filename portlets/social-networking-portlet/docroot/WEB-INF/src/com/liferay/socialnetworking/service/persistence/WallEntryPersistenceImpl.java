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

package com.liferay.socialnetworking.service.persistence;

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
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.socialnetworking.NoSuchWallEntryException;
import com.liferay.socialnetworking.model.WallEntry;
import com.liferay.socialnetworking.model.impl.WallEntryImpl;
import com.liferay.socialnetworking.model.impl.WallEntryModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the wall entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WallEntryPersistence
 * @see WallEntryUtil
 * @generated
 */
public class WallEntryPersistenceImpl extends BasePersistenceImpl<WallEntry>
	implements WallEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WallEntryUtil} to access the wall entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WallEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, WallEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, WallEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			WallEntryModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, WallEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, WallEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			WallEntryModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, WallEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, WallEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_U",
			new String[] { Long.class.getName(), Long.class.getName() },
			WallEntryModelImpl.GROUPID_COLUMN_BITMASK |
			WallEntryModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_U = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_U",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, WallEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, WallEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the wall entry in the entity cache if it is enabled.
	 *
	 * @param wallEntry the wall entry
	 */
	public void cacheResult(WallEntry wallEntry) {
		EntityCacheUtil.putResult(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryImpl.class, wallEntry.getPrimaryKey(), wallEntry);

		wallEntry.resetOriginalValues();
	}

	/**
	 * Caches the wall entries in the entity cache if it is enabled.
	 *
	 * @param wallEntries the wall entries
	 */
	public void cacheResult(List<WallEntry> wallEntries) {
		for (WallEntry wallEntry : wallEntries) {
			if (EntityCacheUtil.getResult(
						WallEntryModelImpl.ENTITY_CACHE_ENABLED,
						WallEntryImpl.class, wallEntry.getPrimaryKey()) == null) {
				cacheResult(wallEntry);
			}
			else {
				wallEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all wall entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(WallEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(WallEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the wall entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WallEntry wallEntry) {
		EntityCacheUtil.removeResult(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryImpl.class, wallEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WallEntry> wallEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WallEntry wallEntry : wallEntries) {
			EntityCacheUtil.removeResult(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
				WallEntryImpl.class, wallEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new wall entry with the primary key. Does not add the wall entry to the database.
	 *
	 * @param wallEntryId the primary key for the new wall entry
	 * @return the new wall entry
	 */
	public WallEntry create(long wallEntryId) {
		WallEntry wallEntry = new WallEntryImpl();

		wallEntry.setNew(true);
		wallEntry.setPrimaryKey(wallEntryId);

		return wallEntry;
	}

	/**
	 * Removes the wall entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param wallEntryId the primary key of the wall entry
	 * @return the wall entry that was removed
	 * @throws com.liferay.socialnetworking.NoSuchWallEntryException if a wall entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WallEntry remove(long wallEntryId)
		throws NoSuchWallEntryException, SystemException {
		return remove(Long.valueOf(wallEntryId));
	}

	/**
	 * Removes the wall entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the wall entry
	 * @return the wall entry that was removed
	 * @throws com.liferay.socialnetworking.NoSuchWallEntryException if a wall entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WallEntry remove(Serializable primaryKey)
		throws NoSuchWallEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WallEntry wallEntry = (WallEntry)session.get(WallEntryImpl.class,
					primaryKey);

			if (wallEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchWallEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(wallEntry);
		}
		catch (NoSuchWallEntryException nsee) {
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
	protected WallEntry removeImpl(WallEntry wallEntry)
		throws SystemException {
		wallEntry = toUnwrappedModel(wallEntry);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, wallEntry);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(wallEntry);

		return wallEntry;
	}

	@Override
	public WallEntry updateImpl(
		com.liferay.socialnetworking.model.WallEntry wallEntry, boolean merge)
		throws SystemException {
		wallEntry = toUnwrappedModel(wallEntry);

		boolean isNew = wallEntry.isNew();

		WallEntryModelImpl wallEntryModelImpl = (WallEntryModelImpl)wallEntry;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, wallEntry, merge);

			wallEntry.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !WallEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((wallEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(wallEntryModelImpl.getOriginalGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] {
						Long.valueOf(wallEntryModelImpl.getGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((wallEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(wallEntryModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { Long.valueOf(wallEntryModelImpl.getUserId()) };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((wallEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(wallEntryModelImpl.getOriginalGroupId()),
						Long.valueOf(wallEntryModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_U, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U,
					args);

				args = new Object[] {
						Long.valueOf(wallEntryModelImpl.getGroupId()),
						Long.valueOf(wallEntryModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_U, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U,
					args);
			}
		}

		EntityCacheUtil.putResult(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryImpl.class, wallEntry.getPrimaryKey(), wallEntry);

		return wallEntry;
	}

	protected WallEntry toUnwrappedModel(WallEntry wallEntry) {
		if (wallEntry instanceof WallEntryImpl) {
			return wallEntry;
		}

		WallEntryImpl wallEntryImpl = new WallEntryImpl();

		wallEntryImpl.setNew(wallEntry.isNew());
		wallEntryImpl.setPrimaryKey(wallEntry.getPrimaryKey());

		wallEntryImpl.setWallEntryId(wallEntry.getWallEntryId());
		wallEntryImpl.setGroupId(wallEntry.getGroupId());
		wallEntryImpl.setCompanyId(wallEntry.getCompanyId());
		wallEntryImpl.setUserId(wallEntry.getUserId());
		wallEntryImpl.setUserName(wallEntry.getUserName());
		wallEntryImpl.setCreateDate(wallEntry.getCreateDate());
		wallEntryImpl.setModifiedDate(wallEntry.getModifiedDate());
		wallEntryImpl.setComments(wallEntry.getComments());

		return wallEntryImpl;
	}

	/**
	 * Returns the wall entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the wall entry
	 * @return the wall entry
	 * @throws com.liferay.portal.NoSuchModelException if a wall entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WallEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the wall entry with the primary key or throws a {@link com.liferay.socialnetworking.NoSuchWallEntryException} if it could not be found.
	 *
	 * @param wallEntryId the primary key of the wall entry
	 * @return the wall entry
	 * @throws com.liferay.socialnetworking.NoSuchWallEntryException if a wall entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WallEntry findByPrimaryKey(long wallEntryId)
		throws NoSuchWallEntryException, SystemException {
		WallEntry wallEntry = fetchByPrimaryKey(wallEntryId);

		if (wallEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + wallEntryId);
			}

			throw new NoSuchWallEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				wallEntryId);
		}

		return wallEntry;
	}

	/**
	 * Returns the wall entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the wall entry
	 * @return the wall entry, or <code>null</code> if a wall entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WallEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the wall entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param wallEntryId the primary key of the wall entry
	 * @return the wall entry, or <code>null</code> if a wall entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WallEntry fetchByPrimaryKey(long wallEntryId)
		throws SystemException {
		WallEntry wallEntry = (WallEntry)EntityCacheUtil.getResult(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
				WallEntryImpl.class, wallEntryId);

		if (wallEntry == _nullWallEntry) {
			return null;
		}

		if (wallEntry == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				wallEntry = (WallEntry)session.get(WallEntryImpl.class,
						Long.valueOf(wallEntryId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (wallEntry != null) {
					cacheResult(wallEntry);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
						WallEntryImpl.class, wallEntryId, _nullWallEntry);
				}

				closeSession(session);
			}
		}

		return wallEntry;
	}

	/**
	 * Returns all the wall entries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching wall entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<WallEntry> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the wall entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of wall entries
	 * @param end the upper bound of the range of wall entries (not inclusive)
	 * @return the range of matching wall entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<WallEntry> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the wall entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of wall entries
	 * @param end the upper bound of the range of wall entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching wall entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<WallEntry> findByGroupId(long groupId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<WallEntry> list = (List<WallEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WallEntry wallEntry : list) {
				if ((groupId != wallEntry.getGroupId())) {
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
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_WALLENTRY_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(WallEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				list = (List<WallEntry>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first wall entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wall entry
	 * @throws com.liferay.socialnetworking.NoSuchWallEntryException if a matching wall entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WallEntry findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchWallEntryException, SystemException {
		WallEntry wallEntry = fetchByGroupId_First(groupId, orderByComparator);

		if (wallEntry != null) {
			return wallEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWallEntryException(msg.toString());
	}

	/**
	 * Returns the first wall entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wall entry, or <code>null</code> if a matching wall entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WallEntry fetchByGroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<WallEntry> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last wall entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wall entry
	 * @throws com.liferay.socialnetworking.NoSuchWallEntryException if a matching wall entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WallEntry findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchWallEntryException, SystemException {
		WallEntry wallEntry = fetchByGroupId_Last(groupId, orderByComparator);

		if (wallEntry != null) {
			return wallEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWallEntryException(msg.toString());
	}

	/**
	 * Returns the last wall entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wall entry, or <code>null</code> if a matching wall entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WallEntry fetchByGroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupId(groupId);

		List<WallEntry> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the wall entries before and after the current wall entry in the ordered set where groupId = &#63;.
	 *
	 * @param wallEntryId the primary key of the current wall entry
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next wall entry
	 * @throws com.liferay.socialnetworking.NoSuchWallEntryException if a wall entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WallEntry[] findByGroupId_PrevAndNext(long wallEntryId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchWallEntryException, SystemException {
		WallEntry wallEntry = findByPrimaryKey(wallEntryId);

		Session session = null;

		try {
			session = openSession();

			WallEntry[] array = new WallEntryImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, wallEntry, groupId,
					orderByComparator, true);

			array[1] = wallEntry;

			array[2] = getByGroupId_PrevAndNext(session, wallEntry, groupId,
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

	protected WallEntry getByGroupId_PrevAndNext(Session session,
		WallEntry wallEntry, long groupId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WALLENTRY_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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

		else {
			query.append(WallEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(wallEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WallEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the wall entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching wall entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<WallEntry> findByUserId(long userId) throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the wall entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of wall entries
	 * @param end the upper bound of the range of wall entries (not inclusive)
	 * @return the range of matching wall entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<WallEntry> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the wall entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of wall entries
	 * @param end the upper bound of the range of wall entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching wall entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<WallEntry> findByUserId(long userId, int start, int end,
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

		List<WallEntry> list = (List<WallEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WallEntry wallEntry : list) {
				if ((userId != wallEntry.getUserId())) {
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
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_WALLENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(WallEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = (List<WallEntry>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first wall entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wall entry
	 * @throws com.liferay.socialnetworking.NoSuchWallEntryException if a matching wall entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WallEntry findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchWallEntryException, SystemException {
		WallEntry wallEntry = fetchByUserId_First(userId, orderByComparator);

		if (wallEntry != null) {
			return wallEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWallEntryException(msg.toString());
	}

	/**
	 * Returns the first wall entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wall entry, or <code>null</code> if a matching wall entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WallEntry fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<WallEntry> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last wall entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wall entry
	 * @throws com.liferay.socialnetworking.NoSuchWallEntryException if a matching wall entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WallEntry findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchWallEntryException, SystemException {
		WallEntry wallEntry = fetchByUserId_Last(userId, orderByComparator);

		if (wallEntry != null) {
			return wallEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWallEntryException(msg.toString());
	}

	/**
	 * Returns the last wall entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wall entry, or <code>null</code> if a matching wall entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WallEntry fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		List<WallEntry> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the wall entries before and after the current wall entry in the ordered set where userId = &#63;.
	 *
	 * @param wallEntryId the primary key of the current wall entry
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next wall entry
	 * @throws com.liferay.socialnetworking.NoSuchWallEntryException if a wall entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WallEntry[] findByUserId_PrevAndNext(long wallEntryId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchWallEntryException, SystemException {
		WallEntry wallEntry = findByPrimaryKey(wallEntryId);

		Session session = null;

		try {
			session = openSession();

			WallEntry[] array = new WallEntryImpl[3];

			array[0] = getByUserId_PrevAndNext(session, wallEntry, userId,
					orderByComparator, true);

			array[1] = wallEntry;

			array[2] = getByUserId_PrevAndNext(session, wallEntry, userId,
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

	protected WallEntry getByUserId_PrevAndNext(Session session,
		WallEntry wallEntry, long userId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WALLENTRY_WHERE);

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

		else {
			query.append(WallEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(wallEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WallEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the wall entries where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching wall entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<WallEntry> findByG_U(long groupId, long userId)
		throws SystemException {
		return findByG_U(groupId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the wall entries where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of wall entries
	 * @param end the upper bound of the range of wall entries (not inclusive)
	 * @return the range of matching wall entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<WallEntry> findByG_U(long groupId, long userId, int start,
		int end) throws SystemException {
		return findByG_U(groupId, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the wall entries where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of wall entries
	 * @param end the upper bound of the range of wall entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching wall entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<WallEntry> findByG_U(long groupId, long userId, int start,
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

		List<WallEntry> list = (List<WallEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WallEntry wallEntry : list) {
				if ((groupId != wallEntry.getGroupId()) ||
						(userId != wallEntry.getUserId())) {
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
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_WALLENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(WallEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				list = (List<WallEntry>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first wall entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wall entry
	 * @throws com.liferay.socialnetworking.NoSuchWallEntryException if a matching wall entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WallEntry findByG_U_First(long groupId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchWallEntryException, SystemException {
		WallEntry wallEntry = fetchByG_U_First(groupId, userId,
				orderByComparator);

		if (wallEntry != null) {
			return wallEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWallEntryException(msg.toString());
	}

	/**
	 * Returns the first wall entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching wall entry, or <code>null</code> if a matching wall entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WallEntry fetchByG_U_First(long groupId, long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<WallEntry> list = findByG_U(groupId, userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last wall entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wall entry
	 * @throws com.liferay.socialnetworking.NoSuchWallEntryException if a matching wall entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WallEntry findByG_U_Last(long groupId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchWallEntryException, SystemException {
		WallEntry wallEntry = fetchByG_U_Last(groupId, userId, orderByComparator);

		if (wallEntry != null) {
			return wallEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchWallEntryException(msg.toString());
	}

	/**
	 * Returns the last wall entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching wall entry, or <code>null</code> if a matching wall entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WallEntry fetchByG_U_Last(long groupId, long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_U(groupId, userId);

		List<WallEntry> list = findByG_U(groupId, userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the wall entries before and after the current wall entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param wallEntryId the primary key of the current wall entry
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next wall entry
	 * @throws com.liferay.socialnetworking.NoSuchWallEntryException if a wall entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WallEntry[] findByG_U_PrevAndNext(long wallEntryId, long groupId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchWallEntryException, SystemException {
		WallEntry wallEntry = findByPrimaryKey(wallEntryId);

		Session session = null;

		try {
			session = openSession();

			WallEntry[] array = new WallEntryImpl[3];

			array[0] = getByG_U_PrevAndNext(session, wallEntry, groupId,
					userId, orderByComparator, true);

			array[1] = wallEntry;

			array[2] = getByG_U_PrevAndNext(session, wallEntry, groupId,
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

	protected WallEntry getByG_U_PrevAndNext(Session session,
		WallEntry wallEntry, long groupId, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WALLENTRY_WHERE);

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

		else {
			query.append(WallEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(wallEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WallEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the wall entries.
	 *
	 * @return the wall entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<WallEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the wall entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of wall entries
	 * @param end the upper bound of the range of wall entries (not inclusive)
	 * @return the range of wall entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<WallEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the wall entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of wall entries
	 * @param end the upper bound of the range of wall entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of wall entries
	 * @throws SystemException if a system exception occurred
	 */
	public List<WallEntry> findAll(int start, int end,
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

		List<WallEntry> list = (List<WallEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_WALLENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WALLENTRY.concat(WallEntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<WallEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<WallEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the wall entries where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByGroupId(long groupId) throws SystemException {
		for (WallEntry wallEntry : findByGroupId(groupId)) {
			remove(wallEntry);
		}
	}

	/**
	 * Removes all the wall entries where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId) throws SystemException {
		for (WallEntry wallEntry : findByUserId(userId)) {
			remove(wallEntry);
		}
	}

	/**
	 * Removes all the wall entries where groupId = &#63; and userId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_U(long groupId, long userId)
		throws SystemException {
		for (WallEntry wallEntry : findByG_U(groupId, userId)) {
			remove(wallEntry);
		}
	}

	/**
	 * Removes all the wall entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (WallEntry wallEntry : findAll()) {
			remove(wallEntry);
		}
	}

	/**
	 * Returns the number of wall entries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching wall entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGroupId(long groupId) throws SystemException {
		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GROUPID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WALLENTRY_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GROUPID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of wall entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching wall entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WALLENTRY_WHERE);

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
	 * Returns the number of wall entries where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching wall entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_U(long groupId, long userId) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_U,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WALLENTRY_WHERE);

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
	 * Returns the number of wall entries.
	 *
	 * @return the number of wall entries
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WALLENTRY);

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
	 * Initializes the wall entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.socialnetworking.model.WallEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<WallEntry>> listenersList = new ArrayList<ModelListener<WallEntry>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<WallEntry>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(WallEntryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = MeetupsEntryPersistence.class)
	protected MeetupsEntryPersistence meetupsEntryPersistence;
	@BeanReference(type = MeetupsRegistrationPersistence.class)
	protected MeetupsRegistrationPersistence meetupsRegistrationPersistence;
	@BeanReference(type = WallEntryPersistence.class)
	protected WallEntryPersistence wallEntryPersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_WALLENTRY = "SELECT wallEntry FROM WallEntry wallEntry";
	private static final String _SQL_SELECT_WALLENTRY_WHERE = "SELECT wallEntry FROM WallEntry wallEntry WHERE ";
	private static final String _SQL_COUNT_WALLENTRY = "SELECT COUNT(wallEntry) FROM WallEntry wallEntry";
	private static final String _SQL_COUNT_WALLENTRY_WHERE = "SELECT COUNT(wallEntry) FROM WallEntry wallEntry WHERE ";
	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "wallEntry.groupId = ?";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "wallEntry.userId = ?";
	private static final String _FINDER_COLUMN_G_U_GROUPID_2 = "wallEntry.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_USERID_2 = "wallEntry.userId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "wallEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WallEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No WallEntry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(WallEntryPersistenceImpl.class);
	private static WallEntry _nullWallEntry = new WallEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<WallEntry> toCacheModel() {
				return _nullWallEntryCacheModel;
			}
		};

	private static CacheModel<WallEntry> _nullWallEntryCacheModel = new CacheModel<WallEntry>() {
			public WallEntry toEntityModel() {
				return _nullWallEntry;
			}
		};
}