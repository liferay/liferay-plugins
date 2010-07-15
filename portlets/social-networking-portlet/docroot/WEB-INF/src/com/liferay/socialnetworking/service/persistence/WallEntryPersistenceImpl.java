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

package com.liferay.socialnetworking.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.annotation.BeanReference;
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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
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
 * @author    Brian Wing Shun Chan
 * @see       WallEntryPersistence
 * @see       WallEntryUtil
 * @generated
 */
public class WallEntryPersistenceImpl extends BasePersistenceImpl<WallEntry>
	implements WallEntryPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = WallEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_GROUPID = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByGroupId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByGroupId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_USERID = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByUserId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_G_U = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByG_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_G_U = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByG_U",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(WallEntry wallEntry) {
		EntityCacheUtil.putResult(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryImpl.class, wallEntry.getPrimaryKey(), wallEntry);
	}

	public void cacheResult(List<WallEntry> wallEntries) {
		for (WallEntry wallEntry : wallEntries) {
			if (EntityCacheUtil.getResult(
						WallEntryModelImpl.ENTITY_CACHE_ENABLED,
						WallEntryImpl.class, wallEntry.getPrimaryKey(), this) == null) {
				cacheResult(wallEntry);
			}
		}
	}

	public void clearCache() {
		CacheRegistryUtil.clear(WallEntryImpl.class.getName());
		EntityCacheUtil.clearCache(WallEntryImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public void clearCache(WallEntry wallEntry) {
		EntityCacheUtil.removeResult(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryImpl.class, wallEntry.getPrimaryKey());
	}

	public WallEntry create(long wallEntryId) {
		WallEntry wallEntry = new WallEntryImpl();

		wallEntry.setNew(true);
		wallEntry.setPrimaryKey(wallEntryId);

		return wallEntry;
	}

	public WallEntry remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public WallEntry remove(long wallEntryId)
		throws NoSuchWallEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WallEntry wallEntry = (WallEntry)session.get(WallEntryImpl.class,
					new Long(wallEntryId));

			if (wallEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + wallEntryId);
				}

				throw new NoSuchWallEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					wallEntryId);
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

	protected WallEntry removeImpl(WallEntry wallEntry)
		throws SystemException {
		wallEntry = toUnwrappedModel(wallEntry);

		Session session = null;

		try {
			session = openSession();

			if (wallEntry.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(WallEntryImpl.class,
						wallEntry.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(wallEntry);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
			WallEntryImpl.class, wallEntry.getPrimaryKey());

		return wallEntry;
	}

	public WallEntry updateImpl(
		com.liferay.socialnetworking.model.WallEntry wallEntry, boolean merge)
		throws SystemException {
		wallEntry = toUnwrappedModel(wallEntry);

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

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

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

	public WallEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

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

	public WallEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public WallEntry fetchByPrimaryKey(long wallEntryId)
		throws SystemException {
		WallEntry wallEntry = (WallEntry)EntityCacheUtil.getResult(WallEntryModelImpl.ENTITY_CACHE_ENABLED,
				WallEntryImpl.class, wallEntryId, this);

		if (wallEntry == null) {
			Session session = null;

			try {
				session = openSession();

				wallEntry = (WallEntry)session.get(WallEntryImpl.class,
						new Long(wallEntryId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (wallEntry != null) {
					cacheResult(wallEntry);
				}

				closeSession(session);
			}
		}

		return wallEntry;
	}

	public List<WallEntry> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<WallEntry> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	public List<WallEntry> findByGroupId(long groupId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<WallEntry> list = (List<WallEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_GROUPID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

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
					list = new ArrayList<WallEntry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_GROUPID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WallEntry findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchWallEntryException, SystemException {
		List<WallEntry> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWallEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WallEntry findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchWallEntryException, SystemException {
		int count = countByGroupId(groupId);

		List<WallEntry> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWallEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

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
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
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
			Object[] values = orderByComparator.getOrderByValues(wallEntry);

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

	public List<WallEntry> findByUserId(long userId) throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<WallEntry> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	public List<WallEntry> findByUserId(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				userId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<WallEntry> list = (List<WallEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_USERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

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
					list = new ArrayList<WallEntry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_USERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WallEntry findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchWallEntryException, SystemException {
		List<WallEntry> list = findByUserId(userId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWallEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WallEntry findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchWallEntryException, SystemException {
		int count = countByUserId(userId);

		List<WallEntry> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWallEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

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
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
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
			Object[] values = orderByComparator.getOrderByValues(wallEntry);

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

	public List<WallEntry> findByG_U(long groupId, long userId)
		throws SystemException {
		return findByG_U(groupId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	public List<WallEntry> findByG_U(long groupId, long userId, int start,
		int end) throws SystemException {
		return findByG_U(groupId, userId, start, end, null);
	}

	public List<WallEntry> findByG_U(long groupId, long userId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				groupId, userId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<WallEntry> list = (List<WallEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_G_U,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

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
					list = new ArrayList<WallEntry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_G_U, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	public WallEntry findByG_U_First(long groupId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchWallEntryException, SystemException {
		List<WallEntry> list = findByG_U(groupId, userId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWallEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WallEntry findByG_U_Last(long groupId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchWallEntryException, SystemException {
		int count = countByG_U(groupId, userId);

		List<WallEntry> list = findByG_U(groupId, userId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchWallEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

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
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
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
			Object[] values = orderByComparator.getOrderByValues(wallEntry);

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

	public List<WallEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<WallEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<WallEntry> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<WallEntry> list = (List<WallEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_WALLENTRY);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_WALLENTRY.concat(WallEntryModelImpl.ORDER_BY_JPQL);
				}

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
					list = new ArrayList<WallEntry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByGroupId(long groupId) throws SystemException {
		for (WallEntry wallEntry : findByGroupId(groupId)) {
			remove(wallEntry);
		}
	}

	public void removeByUserId(long userId) throws SystemException {
		for (WallEntry wallEntry : findByUserId(userId)) {
			remove(wallEntry);
		}
	}

	public void removeByG_U(long groupId, long userId)
		throws SystemException {
		for (WallEntry wallEntry : findByG_U(groupId, userId)) {
			remove(wallEntry);
		}
	}

	public void removeAll() throws SystemException {
		for (WallEntry wallEntry : findAll()) {
			remove(wallEntry);
		}
	}

	public int countByGroupId(long groupId) throws SystemException {
		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GROUPID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_WALLENTRY_WHERE);

				query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

				String sql = query.toString();

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

	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_WALLENTRY_WHERE);

				query.append(_FINDER_COLUMN_USERID_USERID_2);

				String sql = query.toString();

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

	public int countByG_U(long groupId, long userId) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_U,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_COUNT_WALLENTRY_WHERE);

				query.append(_FINDER_COLUMN_G_U_GROUPID_2);

				query.append(_FINDER_COLUMN_G_U_USERID_2);

				String sql = query.toString();

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

	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

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

	@BeanReference(type = MeetupsEntryPersistence.class)
	protected MeetupsEntryPersistence meetupsEntryPersistence;
	@BeanReference(type = MeetupsRegistrationPersistence.class)
	protected MeetupsRegistrationPersistence meetupsRegistrationPersistence;
	@BeanReference(type = WallEntryPersistence.class)
	protected WallEntryPersistence wallEntryPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
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
	private static Log _log = LogFactoryUtil.getLog(WallEntryPersistenceImpl.class);
}