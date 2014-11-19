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

package com.liferay.pushnotifications.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.pushnotifications.NoSuchEntryException;
import com.liferay.pushnotifications.model.PushNotificationsEntry;
import com.liferay.pushnotifications.model.impl.PushNotificationsEntryImpl;
import com.liferay.pushnotifications.model.impl.PushNotificationsEntryModelImpl;
import com.liferay.pushnotifications.service.persistence.PushNotificationsEntryPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the push notifications entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Bruno Farache
 * @see PushNotificationsEntryPersistence
 * @see PushNotificationsEntryUtil
 * @generated
 */
@ProviderType
public class PushNotificationsEntryPersistenceImpl extends BasePersistenceImpl<PushNotificationsEntry>
	implements PushNotificationsEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PushNotificationsEntryUtil} to access the push notifications entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PushNotificationsEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsEntryModelImpl.FINDER_CACHE_ENABLED,
			PushNotificationsEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsEntryModelImpl.FINDER_CACHE_ENABLED,
			PushNotificationsEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTPUSHNOTIFICATIONSENTRYID =
		new FinderPath(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsEntryModelImpl.FINDER_CACHE_ENABLED,
			PushNotificationsEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByParentPushNotificationsEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPUSHNOTIFICATIONSENTRYID =
		new FinderPath(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsEntryModelImpl.FINDER_CACHE_ENABLED,
			PushNotificationsEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByParentPushNotificationsEntryId",
			new String[] { Long.class.getName() },
			PushNotificationsEntryModelImpl.PARENTPUSHNOTIFICATIONSENTRYID_COLUMN_BITMASK |
			PushNotificationsEntryModelImpl.CREATETIME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PARENTPUSHNOTIFICATIONSENTRYID =
		new FinderPath(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByParentPushNotificationsEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the push notifications entries where parentPushNotificationsEntryId = &#63;.
	 *
	 * @param parentPushNotificationsEntryId the parent push notifications entry ID
	 * @return the matching push notifications entries
	 */
	@Override
	public List<PushNotificationsEntry> findByParentPushNotificationsEntryId(
		long parentPushNotificationsEntryId) {
		return findByParentPushNotificationsEntryId(parentPushNotificationsEntryId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the push notifications entries where parentPushNotificationsEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.pushnotifications.model.impl.PushNotificationsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentPushNotificationsEntryId the parent push notifications entry ID
	 * @param start the lower bound of the range of push notifications entries
	 * @param end the upper bound of the range of push notifications entries (not inclusive)
	 * @return the range of matching push notifications entries
	 */
	@Override
	public List<PushNotificationsEntry> findByParentPushNotificationsEntryId(
		long parentPushNotificationsEntryId, int start, int end) {
		return findByParentPushNotificationsEntryId(parentPushNotificationsEntryId,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the push notifications entries where parentPushNotificationsEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.pushnotifications.model.impl.PushNotificationsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentPushNotificationsEntryId the parent push notifications entry ID
	 * @param start the lower bound of the range of push notifications entries
	 * @param end the upper bound of the range of push notifications entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching push notifications entries
	 */
	@Override
	public List<PushNotificationsEntry> findByParentPushNotificationsEntryId(
		long parentPushNotificationsEntryId, int start, int end,
		OrderByComparator<PushNotificationsEntry> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPUSHNOTIFICATIONSENTRYID;
			finderArgs = new Object[] { parentPushNotificationsEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTPUSHNOTIFICATIONSENTRYID;
			finderArgs = new Object[] {
					parentPushNotificationsEntryId,
					
					start, end, orderByComparator
				};
		}

		List<PushNotificationsEntry> list = (List<PushNotificationsEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PushNotificationsEntry pushNotificationsEntry : list) {
				if ((parentPushNotificationsEntryId != pushNotificationsEntry.getParentPushNotificationsEntryId())) {
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

			query.append(_SQL_SELECT_PUSHNOTIFICATIONSENTRY_WHERE);

			query.append(_FINDER_COLUMN_PARENTPUSHNOTIFICATIONSENTRYID_PARENTPUSHNOTIFICATIONSENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PushNotificationsEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentPushNotificationsEntryId);

				if (!pagination) {
					list = (List<PushNotificationsEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PushNotificationsEntry>)QueryUtil.list(q,
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
	 * Returns the first push notifications entry in the ordered set where parentPushNotificationsEntryId = &#63;.
	 *
	 * @param parentPushNotificationsEntryId the parent push notifications entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching push notifications entry
	 * @throws com.liferay.pushnotifications.NoSuchEntryException if a matching push notifications entry could not be found
	 */
	@Override
	public PushNotificationsEntry findByParentPushNotificationsEntryId_First(
		long parentPushNotificationsEntryId,
		OrderByComparator<PushNotificationsEntry> orderByComparator)
		throws NoSuchEntryException {
		PushNotificationsEntry pushNotificationsEntry = fetchByParentPushNotificationsEntryId_First(parentPushNotificationsEntryId,
				orderByComparator);

		if (pushNotificationsEntry != null) {
			return pushNotificationsEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentPushNotificationsEntryId=");
		msg.append(parentPushNotificationsEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first push notifications entry in the ordered set where parentPushNotificationsEntryId = &#63;.
	 *
	 * @param parentPushNotificationsEntryId the parent push notifications entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching push notifications entry, or <code>null</code> if a matching push notifications entry could not be found
	 */
	@Override
	public PushNotificationsEntry fetchByParentPushNotificationsEntryId_First(
		long parentPushNotificationsEntryId,
		OrderByComparator<PushNotificationsEntry> orderByComparator) {
		List<PushNotificationsEntry> list = findByParentPushNotificationsEntryId(parentPushNotificationsEntryId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last push notifications entry in the ordered set where parentPushNotificationsEntryId = &#63;.
	 *
	 * @param parentPushNotificationsEntryId the parent push notifications entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching push notifications entry
	 * @throws com.liferay.pushnotifications.NoSuchEntryException if a matching push notifications entry could not be found
	 */
	@Override
	public PushNotificationsEntry findByParentPushNotificationsEntryId_Last(
		long parentPushNotificationsEntryId,
		OrderByComparator<PushNotificationsEntry> orderByComparator)
		throws NoSuchEntryException {
		PushNotificationsEntry pushNotificationsEntry = fetchByParentPushNotificationsEntryId_Last(parentPushNotificationsEntryId,
				orderByComparator);

		if (pushNotificationsEntry != null) {
			return pushNotificationsEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentPushNotificationsEntryId=");
		msg.append(parentPushNotificationsEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last push notifications entry in the ordered set where parentPushNotificationsEntryId = &#63;.
	 *
	 * @param parentPushNotificationsEntryId the parent push notifications entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching push notifications entry, or <code>null</code> if a matching push notifications entry could not be found
	 */
	@Override
	public PushNotificationsEntry fetchByParentPushNotificationsEntryId_Last(
		long parentPushNotificationsEntryId,
		OrderByComparator<PushNotificationsEntry> orderByComparator) {
		int count = countByParentPushNotificationsEntryId(parentPushNotificationsEntryId);

		if (count == 0) {
			return null;
		}

		List<PushNotificationsEntry> list = findByParentPushNotificationsEntryId(parentPushNotificationsEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the push notifications entries before and after the current push notifications entry in the ordered set where parentPushNotificationsEntryId = &#63;.
	 *
	 * @param pushNotificationsEntryId the primary key of the current push notifications entry
	 * @param parentPushNotificationsEntryId the parent push notifications entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next push notifications entry
	 * @throws com.liferay.pushnotifications.NoSuchEntryException if a push notifications entry with the primary key could not be found
	 */
	@Override
	public PushNotificationsEntry[] findByParentPushNotificationsEntryId_PrevAndNext(
		long pushNotificationsEntryId, long parentPushNotificationsEntryId,
		OrderByComparator<PushNotificationsEntry> orderByComparator)
		throws NoSuchEntryException {
		PushNotificationsEntry pushNotificationsEntry = findByPrimaryKey(pushNotificationsEntryId);

		Session session = null;

		try {
			session = openSession();

			PushNotificationsEntry[] array = new PushNotificationsEntryImpl[3];

			array[0] = getByParentPushNotificationsEntryId_PrevAndNext(session,
					pushNotificationsEntry, parentPushNotificationsEntryId,
					orderByComparator, true);

			array[1] = pushNotificationsEntry;

			array[2] = getByParentPushNotificationsEntryId_PrevAndNext(session,
					pushNotificationsEntry, parentPushNotificationsEntryId,
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

	protected PushNotificationsEntry getByParentPushNotificationsEntryId_PrevAndNext(
		Session session, PushNotificationsEntry pushNotificationsEntry,
		long parentPushNotificationsEntryId,
		OrderByComparator<PushNotificationsEntry> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PUSHNOTIFICATIONSENTRY_WHERE);

		query.append(_FINDER_COLUMN_PARENTPUSHNOTIFICATIONSENTRYID_PARENTPUSHNOTIFICATIONSENTRYID_2);

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
			query.append(PushNotificationsEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentPushNotificationsEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(pushNotificationsEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PushNotificationsEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the push notifications entries where parentPushNotificationsEntryId = &#63; from the database.
	 *
	 * @param parentPushNotificationsEntryId the parent push notifications entry ID
	 */
	@Override
	public void removeByParentPushNotificationsEntryId(
		long parentPushNotificationsEntryId) {
		for (PushNotificationsEntry pushNotificationsEntry : findByParentPushNotificationsEntryId(
				parentPushNotificationsEntryId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(pushNotificationsEntry);
		}
	}

	/**
	 * Returns the number of push notifications entries where parentPushNotificationsEntryId = &#63;.
	 *
	 * @param parentPushNotificationsEntryId the parent push notifications entry ID
	 * @return the number of matching push notifications entries
	 */
	@Override
	public int countByParentPushNotificationsEntryId(
		long parentPushNotificationsEntryId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PARENTPUSHNOTIFICATIONSENTRYID;

		Object[] finderArgs = new Object[] { parentPushNotificationsEntryId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PUSHNOTIFICATIONSENTRY_WHERE);

			query.append(_FINDER_COLUMN_PARENTPUSHNOTIFICATIONSENTRYID_PARENTPUSHNOTIFICATIONSENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentPushNotificationsEntryId);

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

	private static final String _FINDER_COLUMN_PARENTPUSHNOTIFICATIONSENTRYID_PARENTPUSHNOTIFICATIONSENTRYID_2 =
		"pushNotificationsEntry.parentPushNotificationsEntryId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_P = new FinderPath(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsEntryModelImpl.FINDER_CACHE_ENABLED,
			PushNotificationsEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_P",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_P = new FinderPath(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByC_P",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the push notifications entries where createTime &gt; &#63; and parentPushNotificationsEntryId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentPushNotificationsEntryId the parent push notifications entry ID
	 * @return the matching push notifications entries
	 */
	@Override
	public List<PushNotificationsEntry> findByC_P(long createTime,
		long parentPushNotificationsEntryId) {
		return findByC_P(createTime, parentPushNotificationsEntryId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the push notifications entries where createTime &gt; &#63; and parentPushNotificationsEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.pushnotifications.model.impl.PushNotificationsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createTime the create time
	 * @param parentPushNotificationsEntryId the parent push notifications entry ID
	 * @param start the lower bound of the range of push notifications entries
	 * @param end the upper bound of the range of push notifications entries (not inclusive)
	 * @return the range of matching push notifications entries
	 */
	@Override
	public List<PushNotificationsEntry> findByC_P(long createTime,
		long parentPushNotificationsEntryId, int start, int end) {
		return findByC_P(createTime, parentPushNotificationsEntryId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the push notifications entries where createTime &gt; &#63; and parentPushNotificationsEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.pushnotifications.model.impl.PushNotificationsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createTime the create time
	 * @param parentPushNotificationsEntryId the parent push notifications entry ID
	 * @param start the lower bound of the range of push notifications entries
	 * @param end the upper bound of the range of push notifications entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching push notifications entries
	 */
	@Override
	public List<PushNotificationsEntry> findByC_P(long createTime,
		long parentPushNotificationsEntryId, int start, int end,
		OrderByComparator<PushNotificationsEntry> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_P;
		finderArgs = new Object[] {
				createTime, parentPushNotificationsEntryId,
				
				start, end, orderByComparator
			};

		List<PushNotificationsEntry> list = (List<PushNotificationsEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PushNotificationsEntry pushNotificationsEntry : list) {
				if ((createTime >= pushNotificationsEntry.getCreateTime()) ||
						(parentPushNotificationsEntryId != pushNotificationsEntry.getParentPushNotificationsEntryId())) {
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

			query.append(_SQL_SELECT_PUSHNOTIFICATIONSENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_P_CREATETIME_2);

			query.append(_FINDER_COLUMN_C_P_PARENTPUSHNOTIFICATIONSENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PushNotificationsEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createTime);

				qPos.add(parentPushNotificationsEntryId);

				if (!pagination) {
					list = (List<PushNotificationsEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PushNotificationsEntry>)QueryUtil.list(q,
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
	 * Returns the first push notifications entry in the ordered set where createTime &gt; &#63; and parentPushNotificationsEntryId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentPushNotificationsEntryId the parent push notifications entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching push notifications entry
	 * @throws com.liferay.pushnotifications.NoSuchEntryException if a matching push notifications entry could not be found
	 */
	@Override
	public PushNotificationsEntry findByC_P_First(long createTime,
		long parentPushNotificationsEntryId,
		OrderByComparator<PushNotificationsEntry> orderByComparator)
		throws NoSuchEntryException {
		PushNotificationsEntry pushNotificationsEntry = fetchByC_P_First(createTime,
				parentPushNotificationsEntryId, orderByComparator);

		if (pushNotificationsEntry != null) {
			return pushNotificationsEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createTime=");
		msg.append(createTime);

		msg.append(", parentPushNotificationsEntryId=");
		msg.append(parentPushNotificationsEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first push notifications entry in the ordered set where createTime &gt; &#63; and parentPushNotificationsEntryId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentPushNotificationsEntryId the parent push notifications entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching push notifications entry, or <code>null</code> if a matching push notifications entry could not be found
	 */
	@Override
	public PushNotificationsEntry fetchByC_P_First(long createTime,
		long parentPushNotificationsEntryId,
		OrderByComparator<PushNotificationsEntry> orderByComparator) {
		List<PushNotificationsEntry> list = findByC_P(createTime,
				parentPushNotificationsEntryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last push notifications entry in the ordered set where createTime &gt; &#63; and parentPushNotificationsEntryId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentPushNotificationsEntryId the parent push notifications entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching push notifications entry
	 * @throws com.liferay.pushnotifications.NoSuchEntryException if a matching push notifications entry could not be found
	 */
	@Override
	public PushNotificationsEntry findByC_P_Last(long createTime,
		long parentPushNotificationsEntryId,
		OrderByComparator<PushNotificationsEntry> orderByComparator)
		throws NoSuchEntryException {
		PushNotificationsEntry pushNotificationsEntry = fetchByC_P_Last(createTime,
				parentPushNotificationsEntryId, orderByComparator);

		if (pushNotificationsEntry != null) {
			return pushNotificationsEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createTime=");
		msg.append(createTime);

		msg.append(", parentPushNotificationsEntryId=");
		msg.append(parentPushNotificationsEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last push notifications entry in the ordered set where createTime &gt; &#63; and parentPushNotificationsEntryId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentPushNotificationsEntryId the parent push notifications entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching push notifications entry, or <code>null</code> if a matching push notifications entry could not be found
	 */
	@Override
	public PushNotificationsEntry fetchByC_P_Last(long createTime,
		long parentPushNotificationsEntryId,
		OrderByComparator<PushNotificationsEntry> orderByComparator) {
		int count = countByC_P(createTime, parentPushNotificationsEntryId);

		if (count == 0) {
			return null;
		}

		List<PushNotificationsEntry> list = findByC_P(createTime,
				parentPushNotificationsEntryId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the push notifications entries before and after the current push notifications entry in the ordered set where createTime &gt; &#63; and parentPushNotificationsEntryId = &#63;.
	 *
	 * @param pushNotificationsEntryId the primary key of the current push notifications entry
	 * @param createTime the create time
	 * @param parentPushNotificationsEntryId the parent push notifications entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next push notifications entry
	 * @throws com.liferay.pushnotifications.NoSuchEntryException if a push notifications entry with the primary key could not be found
	 */
	@Override
	public PushNotificationsEntry[] findByC_P_PrevAndNext(
		long pushNotificationsEntryId, long createTime,
		long parentPushNotificationsEntryId,
		OrderByComparator<PushNotificationsEntry> orderByComparator)
		throws NoSuchEntryException {
		PushNotificationsEntry pushNotificationsEntry = findByPrimaryKey(pushNotificationsEntryId);

		Session session = null;

		try {
			session = openSession();

			PushNotificationsEntry[] array = new PushNotificationsEntryImpl[3];

			array[0] = getByC_P_PrevAndNext(session, pushNotificationsEntry,
					createTime, parentPushNotificationsEntryId,
					orderByComparator, true);

			array[1] = pushNotificationsEntry;

			array[2] = getByC_P_PrevAndNext(session, pushNotificationsEntry,
					createTime, parentPushNotificationsEntryId,
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

	protected PushNotificationsEntry getByC_P_PrevAndNext(Session session,
		PushNotificationsEntry pushNotificationsEntry, long createTime,
		long parentPushNotificationsEntryId,
		OrderByComparator<PushNotificationsEntry> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PUSHNOTIFICATIONSENTRY_WHERE);

		query.append(_FINDER_COLUMN_C_P_CREATETIME_2);

		query.append(_FINDER_COLUMN_C_P_PARENTPUSHNOTIFICATIONSENTRYID_2);

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
			query.append(PushNotificationsEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(createTime);

		qPos.add(parentPushNotificationsEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(pushNotificationsEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PushNotificationsEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the push notifications entries where createTime &gt; &#63; and parentPushNotificationsEntryId = &#63; from the database.
	 *
	 * @param createTime the create time
	 * @param parentPushNotificationsEntryId the parent push notifications entry ID
	 */
	@Override
	public void removeByC_P(long createTime, long parentPushNotificationsEntryId) {
		for (PushNotificationsEntry pushNotificationsEntry : findByC_P(
				createTime, parentPushNotificationsEntryId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(pushNotificationsEntry);
		}
	}

	/**
	 * Returns the number of push notifications entries where createTime &gt; &#63; and parentPushNotificationsEntryId = &#63;.
	 *
	 * @param createTime the create time
	 * @param parentPushNotificationsEntryId the parent push notifications entry ID
	 * @return the number of matching push notifications entries
	 */
	@Override
	public int countByC_P(long createTime, long parentPushNotificationsEntryId) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_P;

		Object[] finderArgs = new Object[] {
				createTime, parentPushNotificationsEntryId
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PUSHNOTIFICATIONSENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_P_CREATETIME_2);

			query.append(_FINDER_COLUMN_C_P_PARENTPUSHNOTIFICATIONSENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createTime);

				qPos.add(parentPushNotificationsEntryId);

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

	private static final String _FINDER_COLUMN_C_P_CREATETIME_2 = "pushNotificationsEntry.createTime > ? AND ";
	private static final String _FINDER_COLUMN_C_P_PARENTPUSHNOTIFICATIONSENTRYID_2 =
		"pushNotificationsEntry.parentPushNotificationsEntryId = ?";

	public PushNotificationsEntryPersistenceImpl() {
		setModelClass(PushNotificationsEntry.class);
	}

	/**
	 * Caches the push notifications entry in the entity cache if it is enabled.
	 *
	 * @param pushNotificationsEntry the push notifications entry
	 */
	@Override
	public void cacheResult(PushNotificationsEntry pushNotificationsEntry) {
		EntityCacheUtil.putResult(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsEntryImpl.class,
			pushNotificationsEntry.getPrimaryKey(), pushNotificationsEntry);

		pushNotificationsEntry.resetOriginalValues();
	}

	/**
	 * Caches the push notifications entries in the entity cache if it is enabled.
	 *
	 * @param pushNotificationsEntries the push notifications entries
	 */
	@Override
	public void cacheResult(
		List<PushNotificationsEntry> pushNotificationsEntries) {
		for (PushNotificationsEntry pushNotificationsEntry : pushNotificationsEntries) {
			if (EntityCacheUtil.getResult(
						PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
						PushNotificationsEntryImpl.class,
						pushNotificationsEntry.getPrimaryKey()) == null) {
				cacheResult(pushNotificationsEntry);
			}
			else {
				pushNotificationsEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all push notifications entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(PushNotificationsEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(PushNotificationsEntryImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the push notifications entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PushNotificationsEntry pushNotificationsEntry) {
		EntityCacheUtil.removeResult(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsEntryImpl.class,
			pushNotificationsEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<PushNotificationsEntry> pushNotificationsEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PushNotificationsEntry pushNotificationsEntry : pushNotificationsEntries) {
			EntityCacheUtil.removeResult(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
				PushNotificationsEntryImpl.class,
				pushNotificationsEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new push notifications entry with the primary key. Does not add the push notifications entry to the database.
	 *
	 * @param pushNotificationsEntryId the primary key for the new push notifications entry
	 * @return the new push notifications entry
	 */
	@Override
	public PushNotificationsEntry create(long pushNotificationsEntryId) {
		PushNotificationsEntry pushNotificationsEntry = new PushNotificationsEntryImpl();

		pushNotificationsEntry.setNew(true);
		pushNotificationsEntry.setPrimaryKey(pushNotificationsEntryId);

		return pushNotificationsEntry;
	}

	/**
	 * Removes the push notifications entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param pushNotificationsEntryId the primary key of the push notifications entry
	 * @return the push notifications entry that was removed
	 * @throws com.liferay.pushnotifications.NoSuchEntryException if a push notifications entry with the primary key could not be found
	 */
	@Override
	public PushNotificationsEntry remove(long pushNotificationsEntryId)
		throws NoSuchEntryException {
		return remove((Serializable)pushNotificationsEntryId);
	}

	/**
	 * Removes the push notifications entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the push notifications entry
	 * @return the push notifications entry that was removed
	 * @throws com.liferay.pushnotifications.NoSuchEntryException if a push notifications entry with the primary key could not be found
	 */
	@Override
	public PushNotificationsEntry remove(Serializable primaryKey)
		throws NoSuchEntryException {
		Session session = null;

		try {
			session = openSession();

			PushNotificationsEntry pushNotificationsEntry = (PushNotificationsEntry)session.get(PushNotificationsEntryImpl.class,
					primaryKey);

			if (pushNotificationsEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(pushNotificationsEntry);
		}
		catch (NoSuchEntryException nsee) {
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
	protected PushNotificationsEntry removeImpl(
		PushNotificationsEntry pushNotificationsEntry) {
		pushNotificationsEntry = toUnwrappedModel(pushNotificationsEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(pushNotificationsEntry)) {
				pushNotificationsEntry = (PushNotificationsEntry)session.get(PushNotificationsEntryImpl.class,
						pushNotificationsEntry.getPrimaryKeyObj());
			}

			if (pushNotificationsEntry != null) {
				session.delete(pushNotificationsEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (pushNotificationsEntry != null) {
			clearCache(pushNotificationsEntry);
		}

		return pushNotificationsEntry;
	}

	@Override
	public PushNotificationsEntry updateImpl(
		com.liferay.pushnotifications.model.PushNotificationsEntry pushNotificationsEntry) {
		pushNotificationsEntry = toUnwrappedModel(pushNotificationsEntry);

		boolean isNew = pushNotificationsEntry.isNew();

		PushNotificationsEntryModelImpl pushNotificationsEntryModelImpl = (PushNotificationsEntryModelImpl)pushNotificationsEntry;

		Session session = null;

		try {
			session = openSession();

			if (pushNotificationsEntry.isNew()) {
				session.save(pushNotificationsEntry);

				pushNotificationsEntry.setNew(false);
			}
			else {
				session.merge(pushNotificationsEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !PushNotificationsEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((pushNotificationsEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPUSHNOTIFICATIONSENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pushNotificationsEntryModelImpl.getOriginalParentPushNotificationsEntryId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARENTPUSHNOTIFICATIONSENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPUSHNOTIFICATIONSENTRYID,
					args);

				args = new Object[] {
						pushNotificationsEntryModelImpl.getParentPushNotificationsEntryId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARENTPUSHNOTIFICATIONSENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPUSHNOTIFICATIONSENTRYID,
					args);
			}
		}

		EntityCacheUtil.putResult(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsEntryImpl.class,
			pushNotificationsEntry.getPrimaryKey(), pushNotificationsEntry,
			false);

		pushNotificationsEntry.resetOriginalValues();

		return pushNotificationsEntry;
	}

	protected PushNotificationsEntry toUnwrappedModel(
		PushNotificationsEntry pushNotificationsEntry) {
		if (pushNotificationsEntry instanceof PushNotificationsEntryImpl) {
			return pushNotificationsEntry;
		}

		PushNotificationsEntryImpl pushNotificationsEntryImpl = new PushNotificationsEntryImpl();

		pushNotificationsEntryImpl.setNew(pushNotificationsEntry.isNew());
		pushNotificationsEntryImpl.setPrimaryKey(pushNotificationsEntry.getPrimaryKey());

		pushNotificationsEntryImpl.setPushNotificationsEntryId(pushNotificationsEntry.getPushNotificationsEntryId());
		pushNotificationsEntryImpl.setUserId(pushNotificationsEntry.getUserId());
		pushNotificationsEntryImpl.setCreateTime(pushNotificationsEntry.getCreateTime());
		pushNotificationsEntryImpl.setParentPushNotificationsEntryId(pushNotificationsEntry.getParentPushNotificationsEntryId());
		pushNotificationsEntryImpl.setChildrenPushNotificationsEntriesCount(pushNotificationsEntry.getChildrenPushNotificationsEntriesCount());
		pushNotificationsEntryImpl.setPayload(pushNotificationsEntry.getPayload());
		pushNotificationsEntryImpl.setRatingsTotalScore(pushNotificationsEntry.getRatingsTotalScore());

		return pushNotificationsEntryImpl;
	}

	/**
	 * Returns the push notifications entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the push notifications entry
	 * @return the push notifications entry
	 * @throws com.liferay.pushnotifications.NoSuchEntryException if a push notifications entry with the primary key could not be found
	 */
	@Override
	public PushNotificationsEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEntryException {
		PushNotificationsEntry pushNotificationsEntry = fetchByPrimaryKey(primaryKey);

		if (pushNotificationsEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return pushNotificationsEntry;
	}

	/**
	 * Returns the push notifications entry with the primary key or throws a {@link com.liferay.pushnotifications.NoSuchEntryException} if it could not be found.
	 *
	 * @param pushNotificationsEntryId the primary key of the push notifications entry
	 * @return the push notifications entry
	 * @throws com.liferay.pushnotifications.NoSuchEntryException if a push notifications entry with the primary key could not be found
	 */
	@Override
	public PushNotificationsEntry findByPrimaryKey(
		long pushNotificationsEntryId) throws NoSuchEntryException {
		return findByPrimaryKey((Serializable)pushNotificationsEntryId);
	}

	/**
	 * Returns the push notifications entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the push notifications entry
	 * @return the push notifications entry, or <code>null</code> if a push notifications entry with the primary key could not be found
	 */
	@Override
	public PushNotificationsEntry fetchByPrimaryKey(Serializable primaryKey) {
		PushNotificationsEntry pushNotificationsEntry = (PushNotificationsEntry)EntityCacheUtil.getResult(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
				PushNotificationsEntryImpl.class, primaryKey);

		if (pushNotificationsEntry == _nullPushNotificationsEntry) {
			return null;
		}

		if (pushNotificationsEntry == null) {
			Session session = null;

			try {
				session = openSession();

				pushNotificationsEntry = (PushNotificationsEntry)session.get(PushNotificationsEntryImpl.class,
						primaryKey);

				if (pushNotificationsEntry != null) {
					cacheResult(pushNotificationsEntry);
				}
				else {
					EntityCacheUtil.putResult(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
						PushNotificationsEntryImpl.class, primaryKey,
						_nullPushNotificationsEntry);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
					PushNotificationsEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return pushNotificationsEntry;
	}

	/**
	 * Returns the push notifications entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param pushNotificationsEntryId the primary key of the push notifications entry
	 * @return the push notifications entry, or <code>null</code> if a push notifications entry with the primary key could not be found
	 */
	@Override
	public PushNotificationsEntry fetchByPrimaryKey(
		long pushNotificationsEntryId) {
		return fetchByPrimaryKey((Serializable)pushNotificationsEntryId);
	}

	@Override
	public Map<Serializable, PushNotificationsEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PushNotificationsEntry> map = new HashMap<Serializable, PushNotificationsEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			PushNotificationsEntry pushNotificationsEntry = fetchByPrimaryKey(primaryKey);

			if (pushNotificationsEntry != null) {
				map.put(primaryKey, pushNotificationsEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			PushNotificationsEntry pushNotificationsEntry = (PushNotificationsEntry)EntityCacheUtil.getResult(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
					PushNotificationsEntryImpl.class, primaryKey);

			if (pushNotificationsEntry == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, pushNotificationsEntry);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PUSHNOTIFICATIONSENTRY_WHERE_PKS_IN);

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

			for (PushNotificationsEntry pushNotificationsEntry : (List<PushNotificationsEntry>)q.list()) {
				map.put(pushNotificationsEntry.getPrimaryKeyObj(),
					pushNotificationsEntry);

				cacheResult(pushNotificationsEntry);

				uncachedPrimaryKeys.remove(pushNotificationsEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(PushNotificationsEntryModelImpl.ENTITY_CACHE_ENABLED,
					PushNotificationsEntryImpl.class, primaryKey,
					_nullPushNotificationsEntry);
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
	 * Returns all the push notifications entries.
	 *
	 * @return the push notifications entries
	 */
	@Override
	public List<PushNotificationsEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the push notifications entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.pushnotifications.model.impl.PushNotificationsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of push notifications entries
	 * @param end the upper bound of the range of push notifications entries (not inclusive)
	 * @return the range of push notifications entries
	 */
	@Override
	public List<PushNotificationsEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the push notifications entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.pushnotifications.model.impl.PushNotificationsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of push notifications entries
	 * @param end the upper bound of the range of push notifications entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of push notifications entries
	 */
	@Override
	public List<PushNotificationsEntry> findAll(int start, int end,
		OrderByComparator<PushNotificationsEntry> orderByComparator) {
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

		List<PushNotificationsEntry> list = (List<PushNotificationsEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_PUSHNOTIFICATIONSENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PUSHNOTIFICATIONSENTRY;

				if (pagination) {
					sql = sql.concat(PushNotificationsEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PushNotificationsEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PushNotificationsEntry>)QueryUtil.list(q,
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
	 * Removes all the push notifications entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PushNotificationsEntry pushNotificationsEntry : findAll()) {
			remove(pushNotificationsEntry);
		}
	}

	/**
	 * Returns the number of push notifications entries.
	 *
	 * @return the number of push notifications entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PUSHNOTIFICATIONSENTRY);

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

	/**
	 * Initializes the push notifications entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(PushNotificationsEntryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_PUSHNOTIFICATIONSENTRY = "SELECT pushNotificationsEntry FROM PushNotificationsEntry pushNotificationsEntry";
	private static final String _SQL_SELECT_PUSHNOTIFICATIONSENTRY_WHERE_PKS_IN = "SELECT pushNotificationsEntry FROM PushNotificationsEntry pushNotificationsEntry WHERE pushNotificationsEntryId IN (";
	private static final String _SQL_SELECT_PUSHNOTIFICATIONSENTRY_WHERE = "SELECT pushNotificationsEntry FROM PushNotificationsEntry pushNotificationsEntry WHERE ";
	private static final String _SQL_COUNT_PUSHNOTIFICATIONSENTRY = "SELECT COUNT(pushNotificationsEntry) FROM PushNotificationsEntry pushNotificationsEntry";
	private static final String _SQL_COUNT_PUSHNOTIFICATIONSENTRY_WHERE = "SELECT COUNT(pushNotificationsEntry) FROM PushNotificationsEntry pushNotificationsEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "pushNotificationsEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PushNotificationsEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PushNotificationsEntry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static final Log _log = LogFactoryUtil.getLog(PushNotificationsEntryPersistenceImpl.class);
	private static final PushNotificationsEntry _nullPushNotificationsEntry = new PushNotificationsEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<PushNotificationsEntry> toCacheModel() {
				return _nullPushNotificationsEntryCacheModel;
			}
		};

	private static final CacheModel<PushNotificationsEntry> _nullPushNotificationsEntryCacheModel =
		new CacheModel<PushNotificationsEntry>() {
			@Override
			public PushNotificationsEntry toEntityModel() {
				return _nullPushNotificationsEntry;
			}
		};
}