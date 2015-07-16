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

package com.liferay.sync.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.sync.NoSuchDLFileVersionDiffException;
import com.liferay.sync.model.SyncDLFileVersionDiff;
import com.liferay.sync.model.impl.SyncDLFileVersionDiffImpl;
import com.liferay.sync.model.impl.SyncDLFileVersionDiffModelImpl;
import com.liferay.sync.service.persistence.SyncDLFileVersionDiffPersistence;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the sync d l file version diff service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SyncDLFileVersionDiffPersistence
 * @see com.liferay.sync.service.persistence.SyncDLFileVersionDiffUtil
 * @generated
 */
@ProviderType
public class SyncDLFileVersionDiffPersistenceImpl extends BasePersistenceImpl<SyncDLFileVersionDiff>
	implements SyncDLFileVersionDiffPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SyncDLFileVersionDiffUtil} to access the sync d l file version diff persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SyncDLFileVersionDiffImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SyncDLFileVersionDiffModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLFileVersionDiffModelImpl.FINDER_CACHE_ENABLED,
			SyncDLFileVersionDiffImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SyncDLFileVersionDiffModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLFileVersionDiffModelImpl.FINDER_CACHE_ENABLED,
			SyncDLFileVersionDiffImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SyncDLFileVersionDiffModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLFileVersionDiffModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FILEENTRYID =
		new FinderPath(SyncDLFileVersionDiffModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLFileVersionDiffModelImpl.FINDER_CACHE_ENABLED,
			SyncDLFileVersionDiffImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByFileEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FILEENTRYID =
		new FinderPath(SyncDLFileVersionDiffModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLFileVersionDiffModelImpl.FINDER_CACHE_ENABLED,
			SyncDLFileVersionDiffImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByFileEntryId",
			new String[] { Long.class.getName() },
			SyncDLFileVersionDiffModelImpl.FILEENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_FILEENTRYID = new FinderPath(SyncDLFileVersionDiffModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLFileVersionDiffModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFileEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the sync d l file version diffs where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @return the matching sync d l file version diffs
	 */
	@Override
	public List<SyncDLFileVersionDiff> findByFileEntryId(long fileEntryId) {
		return findByFileEntryId(fileEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync d l file version diffs where fileEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLFileVersionDiffModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param start the lower bound of the range of sync d l file version diffs
	 * @param end the upper bound of the range of sync d l file version diffs (not inclusive)
	 * @return the range of matching sync d l file version diffs
	 */
	@Override
	public List<SyncDLFileVersionDiff> findByFileEntryId(long fileEntryId,
		int start, int end) {
		return findByFileEntryId(fileEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync d l file version diffs where fileEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLFileVersionDiffModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param start the lower bound of the range of sync d l file version diffs
	 * @param end the upper bound of the range of sync d l file version diffs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync d l file version diffs
	 */
	@Override
	public List<SyncDLFileVersionDiff> findByFileEntryId(long fileEntryId,
		int start, int end,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FILEENTRYID;
			finderArgs = new Object[] { fileEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_FILEENTRYID;
			finderArgs = new Object[] { fileEntryId, start, end, orderByComparator };
		}

		List<SyncDLFileVersionDiff> list = (List<SyncDLFileVersionDiff>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SyncDLFileVersionDiff syncDLFileVersionDiff : list) {
				if ((fileEntryId != syncDLFileVersionDiff.getFileEntryId())) {
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

			query.append(_SQL_SELECT_SYNCDLFILEVERSIONDIFF_WHERE);

			query.append(_FINDER_COLUMN_FILEENTRYID_FILEENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SyncDLFileVersionDiffModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fileEntryId);

				if (!pagination) {
					list = (List<SyncDLFileVersionDiff>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncDLFileVersionDiff>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first sync d l file version diff in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync d l file version diff
	 * @throws NoSuchDLFileVersionDiffException if a matching sync d l file version diff could not be found
	 */
	@Override
	public SyncDLFileVersionDiff findByFileEntryId_First(long fileEntryId,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator)
		throws NoSuchDLFileVersionDiffException {
		SyncDLFileVersionDiff syncDLFileVersionDiff = fetchByFileEntryId_First(fileEntryId,
				orderByComparator);

		if (syncDLFileVersionDiff != null) {
			return syncDLFileVersionDiff;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("fileEntryId=");
		msg.append(fileEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDLFileVersionDiffException(msg.toString());
	}

	/**
	 * Returns the first sync d l file version diff in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync d l file version diff, or <code>null</code> if a matching sync d l file version diff could not be found
	 */
	@Override
	public SyncDLFileVersionDiff fetchByFileEntryId_First(long fileEntryId,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator) {
		List<SyncDLFileVersionDiff> list = findByFileEntryId(fileEntryId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync d l file version diff in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync d l file version diff
	 * @throws NoSuchDLFileVersionDiffException if a matching sync d l file version diff could not be found
	 */
	@Override
	public SyncDLFileVersionDiff findByFileEntryId_Last(long fileEntryId,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator)
		throws NoSuchDLFileVersionDiffException {
		SyncDLFileVersionDiff syncDLFileVersionDiff = fetchByFileEntryId_Last(fileEntryId,
				orderByComparator);

		if (syncDLFileVersionDiff != null) {
			return syncDLFileVersionDiff;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("fileEntryId=");
		msg.append(fileEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDLFileVersionDiffException(msg.toString());
	}

	/**
	 * Returns the last sync d l file version diff in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync d l file version diff, or <code>null</code> if a matching sync d l file version diff could not be found
	 */
	@Override
	public SyncDLFileVersionDiff fetchByFileEntryId_Last(long fileEntryId,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator) {
		int count = countByFileEntryId(fileEntryId);

		if (count == 0) {
			return null;
		}

		List<SyncDLFileVersionDiff> list = findByFileEntryId(fileEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync d l file version diffs before and after the current sync d l file version diff in the ordered set where fileEntryId = &#63;.
	 *
	 * @param syncDLFileVersionDiffId the primary key of the current sync d l file version diff
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync d l file version diff
	 * @throws NoSuchDLFileVersionDiffException if a sync d l file version diff with the primary key could not be found
	 */
	@Override
	public SyncDLFileVersionDiff[] findByFileEntryId_PrevAndNext(
		long syncDLFileVersionDiffId, long fileEntryId,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator)
		throws NoSuchDLFileVersionDiffException {
		SyncDLFileVersionDiff syncDLFileVersionDiff = findByPrimaryKey(syncDLFileVersionDiffId);

		Session session = null;

		try {
			session = openSession();

			SyncDLFileVersionDiff[] array = new SyncDLFileVersionDiffImpl[3];

			array[0] = getByFileEntryId_PrevAndNext(session,
					syncDLFileVersionDiff, fileEntryId, orderByComparator, true);

			array[1] = syncDLFileVersionDiff;

			array[2] = getByFileEntryId_PrevAndNext(session,
					syncDLFileVersionDiff, fileEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SyncDLFileVersionDiff getByFileEntryId_PrevAndNext(
		Session session, SyncDLFileVersionDiff syncDLFileVersionDiff,
		long fileEntryId,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SYNCDLFILEVERSIONDIFF_WHERE);

		query.append(_FINDER_COLUMN_FILEENTRYID_FILEENTRYID_2);

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
			query.append(SyncDLFileVersionDiffModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(fileEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(syncDLFileVersionDiff);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncDLFileVersionDiff> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sync d l file version diffs where fileEntryId = &#63; from the database.
	 *
	 * @param fileEntryId the file entry ID
	 */
	@Override
	public void removeByFileEntryId(long fileEntryId) {
		for (SyncDLFileVersionDiff syncDLFileVersionDiff : findByFileEntryId(
				fileEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(syncDLFileVersionDiff);
		}
	}

	/**
	 * Returns the number of sync d l file version diffs where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @return the number of matching sync d l file version diffs
	 */
	@Override
	public int countByFileEntryId(long fileEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_FILEENTRYID;

		Object[] finderArgs = new Object[] { fileEntryId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SYNCDLFILEVERSIONDIFF_WHERE);

			query.append(_FINDER_COLUMN_FILEENTRYID_FILEENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fileEntryId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_FILEENTRYID_FILEENTRYID_2 = "syncDLFileVersionDiff.fileEntryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_EXPIRATIONDATE =
		new FinderPath(SyncDLFileVersionDiffModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLFileVersionDiffModelImpl.FINDER_CACHE_ENABLED,
			SyncDLFileVersionDiffImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByExpirationDate",
			new String[] {
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_EXPIRATIONDATE =
		new FinderPath(SyncDLFileVersionDiffModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLFileVersionDiffModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByExpirationDate",
			new String[] { Date.class.getName() });

	/**
	 * Returns all the sync d l file version diffs where expirationDate &lt; &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @return the matching sync d l file version diffs
	 */
	@Override
	public List<SyncDLFileVersionDiff> findByExpirationDate(Date expirationDate) {
		return findByExpirationDate(expirationDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync d l file version diffs where expirationDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLFileVersionDiffModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param expirationDate the expiration date
	 * @param start the lower bound of the range of sync d l file version diffs
	 * @param end the upper bound of the range of sync d l file version diffs (not inclusive)
	 * @return the range of matching sync d l file version diffs
	 */
	@Override
	public List<SyncDLFileVersionDiff> findByExpirationDate(
		Date expirationDate, int start, int end) {
		return findByExpirationDate(expirationDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync d l file version diffs where expirationDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLFileVersionDiffModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param expirationDate the expiration date
	 * @param start the lower bound of the range of sync d l file version diffs
	 * @param end the upper bound of the range of sync d l file version diffs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync d l file version diffs
	 */
	@Override
	public List<SyncDLFileVersionDiff> findByExpirationDate(
		Date expirationDate, int start, int end,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_EXPIRATIONDATE;
		finderArgs = new Object[] { expirationDate, start, end, orderByComparator };

		List<SyncDLFileVersionDiff> list = (List<SyncDLFileVersionDiff>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SyncDLFileVersionDiff syncDLFileVersionDiff : list) {
				if ((expirationDate.getTime() <= syncDLFileVersionDiff.getExpirationDate()
																		  .getTime())) {
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

			query.append(_SQL_SELECT_SYNCDLFILEVERSIONDIFF_WHERE);

			boolean bindExpirationDate = false;

			if (expirationDate == null) {
				query.append(_FINDER_COLUMN_EXPIRATIONDATE_EXPIRATIONDATE_1);
			}
			else {
				bindExpirationDate = true;

				query.append(_FINDER_COLUMN_EXPIRATIONDATE_EXPIRATIONDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SyncDLFileVersionDiffModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindExpirationDate) {
					qPos.add(new Timestamp(expirationDate.getTime()));
				}

				if (!pagination) {
					list = (List<SyncDLFileVersionDiff>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncDLFileVersionDiff>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first sync d l file version diff in the ordered set where expirationDate &lt; &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync d l file version diff
	 * @throws NoSuchDLFileVersionDiffException if a matching sync d l file version diff could not be found
	 */
	@Override
	public SyncDLFileVersionDiff findByExpirationDate_First(
		Date expirationDate,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator)
		throws NoSuchDLFileVersionDiffException {
		SyncDLFileVersionDiff syncDLFileVersionDiff = fetchByExpirationDate_First(expirationDate,
				orderByComparator);

		if (syncDLFileVersionDiff != null) {
			return syncDLFileVersionDiff;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("expirationDate=");
		msg.append(expirationDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDLFileVersionDiffException(msg.toString());
	}

	/**
	 * Returns the first sync d l file version diff in the ordered set where expirationDate &lt; &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync d l file version diff, or <code>null</code> if a matching sync d l file version diff could not be found
	 */
	@Override
	public SyncDLFileVersionDiff fetchByExpirationDate_First(
		Date expirationDate,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator) {
		List<SyncDLFileVersionDiff> list = findByExpirationDate(expirationDate,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync d l file version diff in the ordered set where expirationDate &lt; &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync d l file version diff
	 * @throws NoSuchDLFileVersionDiffException if a matching sync d l file version diff could not be found
	 */
	@Override
	public SyncDLFileVersionDiff findByExpirationDate_Last(
		Date expirationDate,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator)
		throws NoSuchDLFileVersionDiffException {
		SyncDLFileVersionDiff syncDLFileVersionDiff = fetchByExpirationDate_Last(expirationDate,
				orderByComparator);

		if (syncDLFileVersionDiff != null) {
			return syncDLFileVersionDiff;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("expirationDate=");
		msg.append(expirationDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDLFileVersionDiffException(msg.toString());
	}

	/**
	 * Returns the last sync d l file version diff in the ordered set where expirationDate &lt; &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync d l file version diff, or <code>null</code> if a matching sync d l file version diff could not be found
	 */
	@Override
	public SyncDLFileVersionDiff fetchByExpirationDate_Last(
		Date expirationDate,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator) {
		int count = countByExpirationDate(expirationDate);

		if (count == 0) {
			return null;
		}

		List<SyncDLFileVersionDiff> list = findByExpirationDate(expirationDate,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync d l file version diffs before and after the current sync d l file version diff in the ordered set where expirationDate &lt; &#63;.
	 *
	 * @param syncDLFileVersionDiffId the primary key of the current sync d l file version diff
	 * @param expirationDate the expiration date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync d l file version diff
	 * @throws NoSuchDLFileVersionDiffException if a sync d l file version diff with the primary key could not be found
	 */
	@Override
	public SyncDLFileVersionDiff[] findByExpirationDate_PrevAndNext(
		long syncDLFileVersionDiffId, Date expirationDate,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator)
		throws NoSuchDLFileVersionDiffException {
		SyncDLFileVersionDiff syncDLFileVersionDiff = findByPrimaryKey(syncDLFileVersionDiffId);

		Session session = null;

		try {
			session = openSession();

			SyncDLFileVersionDiff[] array = new SyncDLFileVersionDiffImpl[3];

			array[0] = getByExpirationDate_PrevAndNext(session,
					syncDLFileVersionDiff, expirationDate, orderByComparator,
					true);

			array[1] = syncDLFileVersionDiff;

			array[2] = getByExpirationDate_PrevAndNext(session,
					syncDLFileVersionDiff, expirationDate, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SyncDLFileVersionDiff getByExpirationDate_PrevAndNext(
		Session session, SyncDLFileVersionDiff syncDLFileVersionDiff,
		Date expirationDate,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SYNCDLFILEVERSIONDIFF_WHERE);

		boolean bindExpirationDate = false;

		if (expirationDate == null) {
			query.append(_FINDER_COLUMN_EXPIRATIONDATE_EXPIRATIONDATE_1);
		}
		else {
			bindExpirationDate = true;

			query.append(_FINDER_COLUMN_EXPIRATIONDATE_EXPIRATIONDATE_2);
		}

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
			query.append(SyncDLFileVersionDiffModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindExpirationDate) {
			qPos.add(new Timestamp(expirationDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(syncDLFileVersionDiff);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncDLFileVersionDiff> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sync d l file version diffs where expirationDate &lt; &#63; from the database.
	 *
	 * @param expirationDate the expiration date
	 */
	@Override
	public void removeByExpirationDate(Date expirationDate) {
		for (SyncDLFileVersionDiff syncDLFileVersionDiff : findByExpirationDate(
				expirationDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(syncDLFileVersionDiff);
		}
	}

	/**
	 * Returns the number of sync d l file version diffs where expirationDate &lt; &#63;.
	 *
	 * @param expirationDate the expiration date
	 * @return the number of matching sync d l file version diffs
	 */
	@Override
	public int countByExpirationDate(Date expirationDate) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_EXPIRATIONDATE;

		Object[] finderArgs = new Object[] { expirationDate };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SYNCDLFILEVERSIONDIFF_WHERE);

			boolean bindExpirationDate = false;

			if (expirationDate == null) {
				query.append(_FINDER_COLUMN_EXPIRATIONDATE_EXPIRATIONDATE_1);
			}
			else {
				bindExpirationDate = true;

				query.append(_FINDER_COLUMN_EXPIRATIONDATE_EXPIRATIONDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindExpirationDate) {
					qPos.add(new Timestamp(expirationDate.getTime()));
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_EXPIRATIONDATE_EXPIRATIONDATE_1 = "syncDLFileVersionDiff.expirationDate < NULL";
	private static final String _FINDER_COLUMN_EXPIRATIONDATE_EXPIRATIONDATE_2 = "syncDLFileVersionDiff.expirationDate < ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_F_S_T = new FinderPath(SyncDLFileVersionDiffModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLFileVersionDiffModelImpl.FINDER_CACHE_ENABLED,
			SyncDLFileVersionDiffImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_S_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			SyncDLFileVersionDiffModelImpl.FILEENTRYID_COLUMN_BITMASK |
			SyncDLFileVersionDiffModelImpl.SOURCEFILEVERSIONID_COLUMN_BITMASK |
			SyncDLFileVersionDiffModelImpl.TARGETFILEVERSIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_S_T = new FinderPath(SyncDLFileVersionDiffModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLFileVersionDiffModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_S_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns the sync d l file version diff where fileEntryId = &#63; and sourceFileVersionId = &#63; and targetFileVersionId = &#63; or throws a {@link NoSuchDLFileVersionDiffException} if it could not be found.
	 *
	 * @param fileEntryId the file entry ID
	 * @param sourceFileVersionId the source file version ID
	 * @param targetFileVersionId the target file version ID
	 * @return the matching sync d l file version diff
	 * @throws NoSuchDLFileVersionDiffException if a matching sync d l file version diff could not be found
	 */
	@Override
	public SyncDLFileVersionDiff findByF_S_T(long fileEntryId,
		long sourceFileVersionId, long targetFileVersionId)
		throws NoSuchDLFileVersionDiffException {
		SyncDLFileVersionDiff syncDLFileVersionDiff = fetchByF_S_T(fileEntryId,
				sourceFileVersionId, targetFileVersionId);

		if (syncDLFileVersionDiff == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("fileEntryId=");
			msg.append(fileEntryId);

			msg.append(", sourceFileVersionId=");
			msg.append(sourceFileVersionId);

			msg.append(", targetFileVersionId=");
			msg.append(targetFileVersionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchDLFileVersionDiffException(msg.toString());
		}

		return syncDLFileVersionDiff;
	}

	/**
	 * Returns the sync d l file version diff where fileEntryId = &#63; and sourceFileVersionId = &#63; and targetFileVersionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param fileEntryId the file entry ID
	 * @param sourceFileVersionId the source file version ID
	 * @param targetFileVersionId the target file version ID
	 * @return the matching sync d l file version diff, or <code>null</code> if a matching sync d l file version diff could not be found
	 */
	@Override
	public SyncDLFileVersionDiff fetchByF_S_T(long fileEntryId,
		long sourceFileVersionId, long targetFileVersionId) {
		return fetchByF_S_T(fileEntryId, sourceFileVersionId,
			targetFileVersionId, true);
	}

	/**
	 * Returns the sync d l file version diff where fileEntryId = &#63; and sourceFileVersionId = &#63; and targetFileVersionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param fileEntryId the file entry ID
	 * @param sourceFileVersionId the source file version ID
	 * @param targetFileVersionId the target file version ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching sync d l file version diff, or <code>null</code> if a matching sync d l file version diff could not be found
	 */
	@Override
	public SyncDLFileVersionDiff fetchByF_S_T(long fileEntryId,
		long sourceFileVersionId, long targetFileVersionId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				fileEntryId, sourceFileVersionId, targetFileVersionId
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_F_S_T,
					finderArgs, this);
		}

		if (result instanceof SyncDLFileVersionDiff) {
			SyncDLFileVersionDiff syncDLFileVersionDiff = (SyncDLFileVersionDiff)result;

			if ((fileEntryId != syncDLFileVersionDiff.getFileEntryId()) ||
					(sourceFileVersionId != syncDLFileVersionDiff.getSourceFileVersionId()) ||
					(targetFileVersionId != syncDLFileVersionDiff.getTargetFileVersionId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_SYNCDLFILEVERSIONDIFF_WHERE);

			query.append(_FINDER_COLUMN_F_S_T_FILEENTRYID_2);

			query.append(_FINDER_COLUMN_F_S_T_SOURCEFILEVERSIONID_2);

			query.append(_FINDER_COLUMN_F_S_T_TARGETFILEVERSIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fileEntryId);

				qPos.add(sourceFileVersionId);

				qPos.add(targetFileVersionId);

				List<SyncDLFileVersionDiff> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_F_S_T,
						finderArgs, list);
				}
				else {
					SyncDLFileVersionDiff syncDLFileVersionDiff = list.get(0);

					result = syncDLFileVersionDiff;

					cacheResult(syncDLFileVersionDiff);

					if ((syncDLFileVersionDiff.getFileEntryId() != fileEntryId) ||
							(syncDLFileVersionDiff.getSourceFileVersionId() != sourceFileVersionId) ||
							(syncDLFileVersionDiff.getTargetFileVersionId() != targetFileVersionId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_F_S_T,
							finderArgs, syncDLFileVersionDiff);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_F_S_T,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (SyncDLFileVersionDiff)result;
		}
	}

	/**
	 * Removes the sync d l file version diff where fileEntryId = &#63; and sourceFileVersionId = &#63; and targetFileVersionId = &#63; from the database.
	 *
	 * @param fileEntryId the file entry ID
	 * @param sourceFileVersionId the source file version ID
	 * @param targetFileVersionId the target file version ID
	 * @return the sync d l file version diff that was removed
	 */
	@Override
	public SyncDLFileVersionDiff removeByF_S_T(long fileEntryId,
		long sourceFileVersionId, long targetFileVersionId)
		throws NoSuchDLFileVersionDiffException {
		SyncDLFileVersionDiff syncDLFileVersionDiff = findByF_S_T(fileEntryId,
				sourceFileVersionId, targetFileVersionId);

		return remove(syncDLFileVersionDiff);
	}

	/**
	 * Returns the number of sync d l file version diffs where fileEntryId = &#63; and sourceFileVersionId = &#63; and targetFileVersionId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param sourceFileVersionId the source file version ID
	 * @param targetFileVersionId the target file version ID
	 * @return the number of matching sync d l file version diffs
	 */
	@Override
	public int countByF_S_T(long fileEntryId, long sourceFileVersionId,
		long targetFileVersionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_S_T;

		Object[] finderArgs = new Object[] {
				fileEntryId, sourceFileVersionId, targetFileVersionId
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_SYNCDLFILEVERSIONDIFF_WHERE);

			query.append(_FINDER_COLUMN_F_S_T_FILEENTRYID_2);

			query.append(_FINDER_COLUMN_F_S_T_SOURCEFILEVERSIONID_2);

			query.append(_FINDER_COLUMN_F_S_T_TARGETFILEVERSIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fileEntryId);

				qPos.add(sourceFileVersionId);

				qPos.add(targetFileVersionId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_F_S_T_FILEENTRYID_2 = "syncDLFileVersionDiff.fileEntryId = ? AND ";
	private static final String _FINDER_COLUMN_F_S_T_SOURCEFILEVERSIONID_2 = "syncDLFileVersionDiff.sourceFileVersionId = ? AND ";
	private static final String _FINDER_COLUMN_F_S_T_TARGETFILEVERSIONID_2 = "syncDLFileVersionDiff.targetFileVersionId = ?";

	public SyncDLFileVersionDiffPersistenceImpl() {
		setModelClass(SyncDLFileVersionDiff.class);
	}

	/**
	 * Caches the sync d l file version diff in the entity cache if it is enabled.
	 *
	 * @param syncDLFileVersionDiff the sync d l file version diff
	 */
	@Override
	public void cacheResult(SyncDLFileVersionDiff syncDLFileVersionDiff) {
		EntityCacheUtil.putResult(SyncDLFileVersionDiffModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLFileVersionDiffImpl.class,
			syncDLFileVersionDiff.getPrimaryKey(), syncDLFileVersionDiff);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_F_S_T,
			new Object[] {
				syncDLFileVersionDiff.getFileEntryId(),
				syncDLFileVersionDiff.getSourceFileVersionId(),
				syncDLFileVersionDiff.getTargetFileVersionId()
			}, syncDLFileVersionDiff);

		syncDLFileVersionDiff.resetOriginalValues();
	}

	/**
	 * Caches the sync d l file version diffs in the entity cache if it is enabled.
	 *
	 * @param syncDLFileVersionDiffs the sync d l file version diffs
	 */
	@Override
	public void cacheResult(List<SyncDLFileVersionDiff> syncDLFileVersionDiffs) {
		for (SyncDLFileVersionDiff syncDLFileVersionDiff : syncDLFileVersionDiffs) {
			if (EntityCacheUtil.getResult(
						SyncDLFileVersionDiffModelImpl.ENTITY_CACHE_ENABLED,
						SyncDLFileVersionDiffImpl.class,
						syncDLFileVersionDiff.getPrimaryKey()) == null) {
				cacheResult(syncDLFileVersionDiff);
			}
			else {
				syncDLFileVersionDiff.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all sync d l file version diffs.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(SyncDLFileVersionDiffImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the sync d l file version diff.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SyncDLFileVersionDiff syncDLFileVersionDiff) {
		EntityCacheUtil.removeResult(SyncDLFileVersionDiffModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLFileVersionDiffImpl.class,
			syncDLFileVersionDiff.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(syncDLFileVersionDiff);
	}

	@Override
	public void clearCache(List<SyncDLFileVersionDiff> syncDLFileVersionDiffs) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SyncDLFileVersionDiff syncDLFileVersionDiff : syncDLFileVersionDiffs) {
			EntityCacheUtil.removeResult(SyncDLFileVersionDiffModelImpl.ENTITY_CACHE_ENABLED,
				SyncDLFileVersionDiffImpl.class,
				syncDLFileVersionDiff.getPrimaryKey());

			clearUniqueFindersCache(syncDLFileVersionDiff);
		}
	}

	protected void cacheUniqueFindersCache(
		SyncDLFileVersionDiff syncDLFileVersionDiff, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					syncDLFileVersionDiff.getFileEntryId(),
					syncDLFileVersionDiff.getSourceFileVersionId(),
					syncDLFileVersionDiff.getTargetFileVersionId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_F_S_T, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_F_S_T, args,
				syncDLFileVersionDiff);
		}
		else {
			SyncDLFileVersionDiffModelImpl syncDLFileVersionDiffModelImpl = (SyncDLFileVersionDiffModelImpl)syncDLFileVersionDiff;

			if ((syncDLFileVersionDiffModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_F_S_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						syncDLFileVersionDiff.getFileEntryId(),
						syncDLFileVersionDiff.getSourceFileVersionId(),
						syncDLFileVersionDiff.getTargetFileVersionId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_F_S_T, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_F_S_T, args,
					syncDLFileVersionDiff);
			}
		}
	}

	protected void clearUniqueFindersCache(
		SyncDLFileVersionDiff syncDLFileVersionDiff) {
		SyncDLFileVersionDiffModelImpl syncDLFileVersionDiffModelImpl = (SyncDLFileVersionDiffModelImpl)syncDLFileVersionDiff;

		Object[] args = new Object[] {
				syncDLFileVersionDiff.getFileEntryId(),
				syncDLFileVersionDiff.getSourceFileVersionId(),
				syncDLFileVersionDiff.getTargetFileVersionId()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_F_S_T, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_F_S_T, args);

		if ((syncDLFileVersionDiffModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_F_S_T.getColumnBitmask()) != 0) {
			args = new Object[] {
					syncDLFileVersionDiffModelImpl.getOriginalFileEntryId(),
					syncDLFileVersionDiffModelImpl.getOriginalSourceFileVersionId(),
					syncDLFileVersionDiffModelImpl.getOriginalTargetFileVersionId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_F_S_T, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_F_S_T, args);
		}
	}

	/**
	 * Creates a new sync d l file version diff with the primary key. Does not add the sync d l file version diff to the database.
	 *
	 * @param syncDLFileVersionDiffId the primary key for the new sync d l file version diff
	 * @return the new sync d l file version diff
	 */
	@Override
	public SyncDLFileVersionDiff create(long syncDLFileVersionDiffId) {
		SyncDLFileVersionDiff syncDLFileVersionDiff = new SyncDLFileVersionDiffImpl();

		syncDLFileVersionDiff.setNew(true);
		syncDLFileVersionDiff.setPrimaryKey(syncDLFileVersionDiffId);

		return syncDLFileVersionDiff;
	}

	/**
	 * Removes the sync d l file version diff with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param syncDLFileVersionDiffId the primary key of the sync d l file version diff
	 * @return the sync d l file version diff that was removed
	 * @throws NoSuchDLFileVersionDiffException if a sync d l file version diff with the primary key could not be found
	 */
	@Override
	public SyncDLFileVersionDiff remove(long syncDLFileVersionDiffId)
		throws NoSuchDLFileVersionDiffException {
		return remove((Serializable)syncDLFileVersionDiffId);
	}

	/**
	 * Removes the sync d l file version diff with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sync d l file version diff
	 * @return the sync d l file version diff that was removed
	 * @throws NoSuchDLFileVersionDiffException if a sync d l file version diff with the primary key could not be found
	 */
	@Override
	public SyncDLFileVersionDiff remove(Serializable primaryKey)
		throws NoSuchDLFileVersionDiffException {
		Session session = null;

		try {
			session = openSession();

			SyncDLFileVersionDiff syncDLFileVersionDiff = (SyncDLFileVersionDiff)session.get(SyncDLFileVersionDiffImpl.class,
					primaryKey);

			if (syncDLFileVersionDiff == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDLFileVersionDiffException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(syncDLFileVersionDiff);
		}
		catch (NoSuchDLFileVersionDiffException nsee) {
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
	protected SyncDLFileVersionDiff removeImpl(
		SyncDLFileVersionDiff syncDLFileVersionDiff) {
		syncDLFileVersionDiff = toUnwrappedModel(syncDLFileVersionDiff);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(syncDLFileVersionDiff)) {
				syncDLFileVersionDiff = (SyncDLFileVersionDiff)session.get(SyncDLFileVersionDiffImpl.class,
						syncDLFileVersionDiff.getPrimaryKeyObj());
			}

			if (syncDLFileVersionDiff != null) {
				session.delete(syncDLFileVersionDiff);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (syncDLFileVersionDiff != null) {
			clearCache(syncDLFileVersionDiff);
		}

		return syncDLFileVersionDiff;
	}

	@Override
	public SyncDLFileVersionDiff updateImpl(
		SyncDLFileVersionDiff syncDLFileVersionDiff) {
		syncDLFileVersionDiff = toUnwrappedModel(syncDLFileVersionDiff);

		boolean isNew = syncDLFileVersionDiff.isNew();

		SyncDLFileVersionDiffModelImpl syncDLFileVersionDiffModelImpl = (SyncDLFileVersionDiffModelImpl)syncDLFileVersionDiff;

		Session session = null;

		try {
			session = openSession();

			if (syncDLFileVersionDiff.isNew()) {
				session.save(syncDLFileVersionDiff);

				syncDLFileVersionDiff.setNew(false);
			}
			else {
				session.merge(syncDLFileVersionDiff);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SyncDLFileVersionDiffModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((syncDLFileVersionDiffModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FILEENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						syncDLFileVersionDiffModelImpl.getOriginalFileEntryId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FILEENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FILEENTRYID,
					args);

				args = new Object[] {
						syncDLFileVersionDiffModelImpl.getFileEntryId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FILEENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FILEENTRYID,
					args);
			}
		}

		EntityCacheUtil.putResult(SyncDLFileVersionDiffModelImpl.ENTITY_CACHE_ENABLED,
			SyncDLFileVersionDiffImpl.class,
			syncDLFileVersionDiff.getPrimaryKey(), syncDLFileVersionDiff, false);

		clearUniqueFindersCache(syncDLFileVersionDiff);
		cacheUniqueFindersCache(syncDLFileVersionDiff, isNew);

		syncDLFileVersionDiff.resetOriginalValues();

		return syncDLFileVersionDiff;
	}

	protected SyncDLFileVersionDiff toUnwrappedModel(
		SyncDLFileVersionDiff syncDLFileVersionDiff) {
		if (syncDLFileVersionDiff instanceof SyncDLFileVersionDiffImpl) {
			return syncDLFileVersionDiff;
		}

		SyncDLFileVersionDiffImpl syncDLFileVersionDiffImpl = new SyncDLFileVersionDiffImpl();

		syncDLFileVersionDiffImpl.setNew(syncDLFileVersionDiff.isNew());
		syncDLFileVersionDiffImpl.setPrimaryKey(syncDLFileVersionDiff.getPrimaryKey());

		syncDLFileVersionDiffImpl.setSyncDLFileVersionDiffId(syncDLFileVersionDiff.getSyncDLFileVersionDiffId());
		syncDLFileVersionDiffImpl.setFileEntryId(syncDLFileVersionDiff.getFileEntryId());
		syncDLFileVersionDiffImpl.setSourceFileVersionId(syncDLFileVersionDiff.getSourceFileVersionId());
		syncDLFileVersionDiffImpl.setTargetFileVersionId(syncDLFileVersionDiff.getTargetFileVersionId());
		syncDLFileVersionDiffImpl.setDataFileEntryId(syncDLFileVersionDiff.getDataFileEntryId());
		syncDLFileVersionDiffImpl.setSize(syncDLFileVersionDiff.getSize());
		syncDLFileVersionDiffImpl.setExpirationDate(syncDLFileVersionDiff.getExpirationDate());

		return syncDLFileVersionDiffImpl;
	}

	/**
	 * Returns the sync d l file version diff with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the sync d l file version diff
	 * @return the sync d l file version diff
	 * @throws NoSuchDLFileVersionDiffException if a sync d l file version diff with the primary key could not be found
	 */
	@Override
	public SyncDLFileVersionDiff findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDLFileVersionDiffException {
		SyncDLFileVersionDiff syncDLFileVersionDiff = fetchByPrimaryKey(primaryKey);

		if (syncDLFileVersionDiff == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDLFileVersionDiffException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return syncDLFileVersionDiff;
	}

	/**
	 * Returns the sync d l file version diff with the primary key or throws a {@link NoSuchDLFileVersionDiffException} if it could not be found.
	 *
	 * @param syncDLFileVersionDiffId the primary key of the sync d l file version diff
	 * @return the sync d l file version diff
	 * @throws NoSuchDLFileVersionDiffException if a sync d l file version diff with the primary key could not be found
	 */
	@Override
	public SyncDLFileVersionDiff findByPrimaryKey(long syncDLFileVersionDiffId)
		throws NoSuchDLFileVersionDiffException {
		return findByPrimaryKey((Serializable)syncDLFileVersionDiffId);
	}

	/**
	 * Returns the sync d l file version diff with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sync d l file version diff
	 * @return the sync d l file version diff, or <code>null</code> if a sync d l file version diff with the primary key could not be found
	 */
	@Override
	public SyncDLFileVersionDiff fetchByPrimaryKey(Serializable primaryKey) {
		SyncDLFileVersionDiff syncDLFileVersionDiff = (SyncDLFileVersionDiff)EntityCacheUtil.getResult(SyncDLFileVersionDiffModelImpl.ENTITY_CACHE_ENABLED,
				SyncDLFileVersionDiffImpl.class, primaryKey);

		if (syncDLFileVersionDiff == _nullSyncDLFileVersionDiff) {
			return null;
		}

		if (syncDLFileVersionDiff == null) {
			Session session = null;

			try {
				session = openSession();

				syncDLFileVersionDiff = (SyncDLFileVersionDiff)session.get(SyncDLFileVersionDiffImpl.class,
						primaryKey);

				if (syncDLFileVersionDiff != null) {
					cacheResult(syncDLFileVersionDiff);
				}
				else {
					EntityCacheUtil.putResult(SyncDLFileVersionDiffModelImpl.ENTITY_CACHE_ENABLED,
						SyncDLFileVersionDiffImpl.class, primaryKey,
						_nullSyncDLFileVersionDiff);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(SyncDLFileVersionDiffModelImpl.ENTITY_CACHE_ENABLED,
					SyncDLFileVersionDiffImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return syncDLFileVersionDiff;
	}

	/**
	 * Returns the sync d l file version diff with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param syncDLFileVersionDiffId the primary key of the sync d l file version diff
	 * @return the sync d l file version diff, or <code>null</code> if a sync d l file version diff with the primary key could not be found
	 */
	@Override
	public SyncDLFileVersionDiff fetchByPrimaryKey(long syncDLFileVersionDiffId) {
		return fetchByPrimaryKey((Serializable)syncDLFileVersionDiffId);
	}

	@Override
	public Map<Serializable, SyncDLFileVersionDiff> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SyncDLFileVersionDiff> map = new HashMap<Serializable, SyncDLFileVersionDiff>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SyncDLFileVersionDiff syncDLFileVersionDiff = fetchByPrimaryKey(primaryKey);

			if (syncDLFileVersionDiff != null) {
				map.put(primaryKey, syncDLFileVersionDiff);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			SyncDLFileVersionDiff syncDLFileVersionDiff = (SyncDLFileVersionDiff)EntityCacheUtil.getResult(SyncDLFileVersionDiffModelImpl.ENTITY_CACHE_ENABLED,
					SyncDLFileVersionDiffImpl.class, primaryKey);

			if (syncDLFileVersionDiff == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, syncDLFileVersionDiff);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SYNCDLFILEVERSIONDIFF_WHERE_PKS_IN);

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

			for (SyncDLFileVersionDiff syncDLFileVersionDiff : (List<SyncDLFileVersionDiff>)q.list()) {
				map.put(syncDLFileVersionDiff.getPrimaryKeyObj(),
					syncDLFileVersionDiff);

				cacheResult(syncDLFileVersionDiff);

				uncachedPrimaryKeys.remove(syncDLFileVersionDiff.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(SyncDLFileVersionDiffModelImpl.ENTITY_CACHE_ENABLED,
					SyncDLFileVersionDiffImpl.class, primaryKey,
					_nullSyncDLFileVersionDiff);
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
	 * Returns all the sync d l file version diffs.
	 *
	 * @return the sync d l file version diffs
	 */
	@Override
	public List<SyncDLFileVersionDiff> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync d l file version diffs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLFileVersionDiffModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync d l file version diffs
	 * @param end the upper bound of the range of sync d l file version diffs (not inclusive)
	 * @return the range of sync d l file version diffs
	 */
	@Override
	public List<SyncDLFileVersionDiff> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync d l file version diffs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncDLFileVersionDiffModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync d l file version diffs
	 * @param end the upper bound of the range of sync d l file version diffs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sync d l file version diffs
	 */
	@Override
	public List<SyncDLFileVersionDiff> findAll(int start, int end,
		OrderByComparator<SyncDLFileVersionDiff> orderByComparator) {
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

		List<SyncDLFileVersionDiff> list = (List<SyncDLFileVersionDiff>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SYNCDLFILEVERSIONDIFF);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SYNCDLFILEVERSIONDIFF;

				if (pagination) {
					sql = sql.concat(SyncDLFileVersionDiffModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SyncDLFileVersionDiff>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SyncDLFileVersionDiff>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the sync d l file version diffs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SyncDLFileVersionDiff syncDLFileVersionDiff : findAll()) {
			remove(syncDLFileVersionDiff);
		}
	}

	/**
	 * Returns the number of sync d l file version diffs.
	 *
	 * @return the number of sync d l file version diffs
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SYNCDLFILEVERSIONDIFF);

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
		return SyncDLFileVersionDiffModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sync d l file version diff persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(SyncDLFileVersionDiffImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SYNCDLFILEVERSIONDIFF = "SELECT syncDLFileVersionDiff FROM SyncDLFileVersionDiff syncDLFileVersionDiff";
	private static final String _SQL_SELECT_SYNCDLFILEVERSIONDIFF_WHERE_PKS_IN = "SELECT syncDLFileVersionDiff FROM SyncDLFileVersionDiff syncDLFileVersionDiff WHERE syncDLFileVersionDiffId IN (";
	private static final String _SQL_SELECT_SYNCDLFILEVERSIONDIFF_WHERE = "SELECT syncDLFileVersionDiff FROM SyncDLFileVersionDiff syncDLFileVersionDiff WHERE ";
	private static final String _SQL_COUNT_SYNCDLFILEVERSIONDIFF = "SELECT COUNT(syncDLFileVersionDiff) FROM SyncDLFileVersionDiff syncDLFileVersionDiff";
	private static final String _SQL_COUNT_SYNCDLFILEVERSIONDIFF_WHERE = "SELECT COUNT(syncDLFileVersionDiff) FROM SyncDLFileVersionDiff syncDLFileVersionDiff WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "syncDLFileVersionDiff.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SyncDLFileVersionDiff exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SyncDLFileVersionDiff exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SyncDLFileVersionDiffPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"size"
			});
	private static final SyncDLFileVersionDiff _nullSyncDLFileVersionDiff = new SyncDLFileVersionDiffImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SyncDLFileVersionDiff> toCacheModel() {
				return _nullSyncDLFileVersionDiffCacheModel;
			}
		};

	private static final CacheModel<SyncDLFileVersionDiff> _nullSyncDLFileVersionDiffCacheModel =
		new CacheModel<SyncDLFileVersionDiff>() {
			@Override
			public SyncDLFileVersionDiff toEntityModel() {
				return _nullSyncDLFileVersionDiff;
			}
		};
}