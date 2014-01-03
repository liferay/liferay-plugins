/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.chat.service.persistence;

import com.liferay.chat.NoSuchEntryException;
import com.liferay.chat.model.Entry;
import com.liferay.chat.model.impl.EntryImpl;
import com.liferay.chat.model.impl.EntryModelImpl;

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
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntryPersistence
 * @see EntryUtil
 * @generated
 */
public class EntryPersistenceImpl extends BasePersistenceImpl<Entry>
	implements EntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link EntryUtil} to access the entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = EntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CREATEDATE =
		new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCreateDate",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CREATEDATE =
		new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCreateDate",
			new String[] { Long.class.getName() },
			EntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CREATEDATE = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCreateDate",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the entries where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @return the matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByCreateDate(long createDate)
		throws SystemException {
		return findByCreateDate(createDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the entries where createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @return the range of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByCreateDate(long createDate, int start, int end)
		throws SystemException {
		return findByCreateDate(createDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the entries where createDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByCreateDate(long createDate, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CREATEDATE;
			finderArgs = new Object[] { createDate };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CREATEDATE;
			finderArgs = new Object[] { createDate, start, end, orderByComparator };
		}

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Entry entry : list) {
				if ((createDate != entry.getCreateDate())) {
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

			query.append(_SQL_SELECT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_CREATEDATE_CREATEDATE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				if (!pagination) {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Entry>(list);
				}
				else {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first entry in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry
	 * @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry findByCreateDate_First(long createDate,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByCreateDate_First(createDate, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first entry in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry, or <code>null</code> if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByCreateDate_First(long createDate,
		OrderByComparator orderByComparator) throws SystemException {
		List<Entry> list = findByCreateDate(createDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last entry in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry
	 * @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry findByCreateDate_Last(long createDate,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByCreateDate_Last(createDate, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last entry in the ordered set where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry, or <code>null</code> if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByCreateDate_Last(long createDate,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCreateDate(createDate);

		if (count == 0) {
			return null;
		}

		List<Entry> list = findByCreateDate(createDate, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the entries before and after the current entry in the ordered set where createDate = &#63;.
	 *
	 * @param entryId the primary key of the current entry
	 * @param createDate the create date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entry
	 * @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry[] findByCreateDate_PrevAndNext(long entryId, long createDate,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			Entry[] array = new EntryImpl[3];

			array[0] = getByCreateDate_PrevAndNext(session, entry, createDate,
					orderByComparator, true);

			array[1] = entry;

			array[2] = getByCreateDate_PrevAndNext(session, entry, createDate,
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

	protected Entry getByCreateDate_PrevAndNext(Session session, Entry entry,
		long createDate, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ENTRY_WHERE);

		query.append(_FINDER_COLUMN_CREATEDATE_CREATEDATE_2);

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
			query.append(EntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(createDate);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(entry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Entry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entries where createDate = &#63; from the database.
	 *
	 * @param createDate the create date
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByCreateDate(long createDate) throws SystemException {
		for (Entry entry : findByCreateDate(createDate, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(entry);
		}
	}

	/**
	 * Returns the number of entries where createDate = &#63;.
	 *
	 * @param createDate the create date
	 * @return the number of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByCreateDate(long createDate) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CREATEDATE;

		Object[] finderArgs = new Object[] { createDate };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_CREATEDATE_CREATEDATE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

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

	private static final String _FINDER_COLUMN_CREATEDATE_CREATEDATE_2 = "entry.createDate = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FROMUSERID =
		new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByFromUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FROMUSERID =
		new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByFromUserId",
			new String[] { Long.class.getName() },
			EntryModelImpl.FROMUSERID_COLUMN_BITMASK |
			EntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_FROMUSERID = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFromUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the entries where fromUserId = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @return the matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByFromUserId(long fromUserId)
		throws SystemException {
		return findByFromUserId(fromUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the entries where fromUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param fromUserId the from user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @return the range of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByFromUserId(long fromUserId, int start, int end)
		throws SystemException {
		return findByFromUserId(fromUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the entries where fromUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param fromUserId the from user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByFromUserId(long fromUserId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FROMUSERID;
			finderArgs = new Object[] { fromUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_FROMUSERID;
			finderArgs = new Object[] { fromUserId, start, end, orderByComparator };
		}

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Entry entry : list) {
				if ((fromUserId != entry.getFromUserId())) {
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

			query.append(_SQL_SELECT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_FROMUSERID_FROMUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fromUserId);

				if (!pagination) {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Entry>(list);
				}
				else {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first entry in the ordered set where fromUserId = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry
	 * @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry findByFromUserId_First(long fromUserId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByFromUserId_First(fromUserId, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("fromUserId=");
		msg.append(fromUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first entry in the ordered set where fromUserId = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry, or <code>null</code> if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByFromUserId_First(long fromUserId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Entry> list = findByFromUserId(fromUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last entry in the ordered set where fromUserId = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry
	 * @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry findByFromUserId_Last(long fromUserId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByFromUserId_Last(fromUserId, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("fromUserId=");
		msg.append(fromUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last entry in the ordered set where fromUserId = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry, or <code>null</code> if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByFromUserId_Last(long fromUserId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByFromUserId(fromUserId);

		if (count == 0) {
			return null;
		}

		List<Entry> list = findByFromUserId(fromUserId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the entries before and after the current entry in the ordered set where fromUserId = &#63;.
	 *
	 * @param entryId the primary key of the current entry
	 * @param fromUserId the from user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entry
	 * @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry[] findByFromUserId_PrevAndNext(long entryId, long fromUserId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			Entry[] array = new EntryImpl[3];

			array[0] = getByFromUserId_PrevAndNext(session, entry, fromUserId,
					orderByComparator, true);

			array[1] = entry;

			array[2] = getByFromUserId_PrevAndNext(session, entry, fromUserId,
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

	protected Entry getByFromUserId_PrevAndNext(Session session, Entry entry,
		long fromUserId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ENTRY_WHERE);

		query.append(_FINDER_COLUMN_FROMUSERID_FROMUSERID_2);

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
			query.append(EntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(fromUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(entry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Entry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entries where fromUserId = &#63; from the database.
	 *
	 * @param fromUserId the from user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByFromUserId(long fromUserId) throws SystemException {
		for (Entry entry : findByFromUserId(fromUserId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(entry);
		}
	}

	/**
	 * Returns the number of entries where fromUserId = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @return the number of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByFromUserId(long fromUserId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_FROMUSERID;

		Object[] finderArgs = new Object[] { fromUserId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_FROMUSERID_FROMUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fromUserId);

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

	private static final String _FINDER_COLUMN_FROMUSERID_FROMUSERID_2 = "entry.fromUserId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TOUSERID = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByToUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TOUSERID =
		new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByToUserId",
			new String[] { Long.class.getName() },
			EntryModelImpl.TOUSERID_COLUMN_BITMASK |
			EntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TOUSERID = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByToUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the entries where toUserId = &#63;.
	 *
	 * @param toUserId the to user ID
	 * @return the matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByToUserId(long toUserId) throws SystemException {
		return findByToUserId(toUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the entries where toUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param toUserId the to user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @return the range of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByToUserId(long toUserId, int start, int end)
		throws SystemException {
		return findByToUserId(toUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the entries where toUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param toUserId the to user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByToUserId(long toUserId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TOUSERID;
			finderArgs = new Object[] { toUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TOUSERID;
			finderArgs = new Object[] { toUserId, start, end, orderByComparator };
		}

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Entry entry : list) {
				if ((toUserId != entry.getToUserId())) {
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

			query.append(_SQL_SELECT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_TOUSERID_TOUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(toUserId);

				if (!pagination) {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Entry>(list);
				}
				else {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first entry in the ordered set where toUserId = &#63;.
	 *
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry
	 * @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry findByToUserId_First(long toUserId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByToUserId_First(toUserId, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("toUserId=");
		msg.append(toUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first entry in the ordered set where toUserId = &#63;.
	 *
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry, or <code>null</code> if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByToUserId_First(long toUserId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Entry> list = findByToUserId(toUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last entry in the ordered set where toUserId = &#63;.
	 *
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry
	 * @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry findByToUserId_Last(long toUserId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByToUserId_Last(toUserId, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("toUserId=");
		msg.append(toUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last entry in the ordered set where toUserId = &#63;.
	 *
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry, or <code>null</code> if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByToUserId_Last(long toUserId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByToUserId(toUserId);

		if (count == 0) {
			return null;
		}

		List<Entry> list = findByToUserId(toUserId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the entries before and after the current entry in the ordered set where toUserId = &#63;.
	 *
	 * @param entryId the primary key of the current entry
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entry
	 * @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry[] findByToUserId_PrevAndNext(long entryId, long toUserId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			Entry[] array = new EntryImpl[3];

			array[0] = getByToUserId_PrevAndNext(session, entry, toUserId,
					orderByComparator, true);

			array[1] = entry;

			array[2] = getByToUserId_PrevAndNext(session, entry, toUserId,
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

	protected Entry getByToUserId_PrevAndNext(Session session, Entry entry,
		long toUserId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ENTRY_WHERE);

		query.append(_FINDER_COLUMN_TOUSERID_TOUSERID_2);

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
			query.append(EntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(toUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(entry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Entry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entries where toUserId = &#63; from the database.
	 *
	 * @param toUserId the to user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByToUserId(long toUserId) throws SystemException {
		for (Entry entry : findByToUserId(toUserId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(entry);
		}
	}

	/**
	 * Returns the number of entries where toUserId = &#63;.
	 *
	 * @param toUserId the to user ID
	 * @return the number of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByToUserId(long toUserId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TOUSERID;

		Object[] finderArgs = new Object[] { toUserId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_TOUSERID_TOUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(toUserId);

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

	private static final String _FINDER_COLUMN_TOUSERID_TOUSERID_2 = "entry.toUserId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_F = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_F",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_F = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_F",
			new String[] { Long.class.getName(), Long.class.getName() },
			EntryModelImpl.CREATEDATE_COLUMN_BITMASK |
			EntryModelImpl.FROMUSERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_F = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_F",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the entries where createDate = &#63; and fromUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @return the matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByC_F(long createDate, long fromUserId)
		throws SystemException {
		return findByC_F(createDate, fromUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the entries where createDate = &#63; and fromUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @return the range of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByC_F(long createDate, long fromUserId, int start,
		int end) throws SystemException {
		return findByC_F(createDate, fromUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the entries where createDate = &#63; and fromUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByC_F(long createDate, long fromUserId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_F;
			finderArgs = new Object[] { createDate, fromUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_F;
			finderArgs = new Object[] {
					createDate, fromUserId,
					
					start, end, orderByComparator
				};
		}

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Entry entry : list) {
				if ((createDate != entry.getCreateDate()) ||
						(fromUserId != entry.getFromUserId())) {
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

			query.append(_SQL_SELECT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_F_CREATEDATE_2);

			query.append(_FINDER_COLUMN_C_F_FROMUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				qPos.add(fromUserId);

				if (!pagination) {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Entry>(list);
				}
				else {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first entry in the ordered set where createDate = &#63; and fromUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry
	 * @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry findByC_F_First(long createDate, long fromUserId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByC_F_First(createDate, fromUserId, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(", fromUserId=");
		msg.append(fromUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first entry in the ordered set where createDate = &#63; and fromUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry, or <code>null</code> if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByC_F_First(long createDate, long fromUserId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Entry> list = findByC_F(createDate, fromUserId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last entry in the ordered set where createDate = &#63; and fromUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry
	 * @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry findByC_F_Last(long createDate, long fromUserId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByC_F_Last(createDate, fromUserId, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(", fromUserId=");
		msg.append(fromUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last entry in the ordered set where createDate = &#63; and fromUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry, or <code>null</code> if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByC_F_Last(long createDate, long fromUserId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_F(createDate, fromUserId);

		if (count == 0) {
			return null;
		}

		List<Entry> list = findByC_F(createDate, fromUserId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the entries before and after the current entry in the ordered set where createDate = &#63; and fromUserId = &#63;.
	 *
	 * @param entryId the primary key of the current entry
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entry
	 * @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry[] findByC_F_PrevAndNext(long entryId, long createDate,
		long fromUserId, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			Entry[] array = new EntryImpl[3];

			array[0] = getByC_F_PrevAndNext(session, entry, createDate,
					fromUserId, orderByComparator, true);

			array[1] = entry;

			array[2] = getByC_F_PrevAndNext(session, entry, createDate,
					fromUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Entry getByC_F_PrevAndNext(Session session, Entry entry,
		long createDate, long fromUserId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ENTRY_WHERE);

		query.append(_FINDER_COLUMN_C_F_CREATEDATE_2);

		query.append(_FINDER_COLUMN_C_F_FROMUSERID_2);

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
			query.append(EntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(createDate);

		qPos.add(fromUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(entry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Entry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entries where createDate = &#63; and fromUserId = &#63; from the database.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByC_F(long createDate, long fromUserId)
		throws SystemException {
		for (Entry entry : findByC_F(createDate, fromUserId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(entry);
		}
	}

	/**
	 * Returns the number of entries where createDate = &#63; and fromUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @return the number of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByC_F(long createDate, long fromUserId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_F;

		Object[] finderArgs = new Object[] { createDate, fromUserId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_F_CREATEDATE_2);

			query.append(_FINDER_COLUMN_C_F_FROMUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				qPos.add(fromUserId);

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

	private static final String _FINDER_COLUMN_C_F_CREATEDATE_2 = "entry.createDate = ? AND ";
	private static final String _FINDER_COLUMN_C_F_FROMUSERID_2 = "entry.fromUserId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_T = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_T = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_T",
			new String[] { Long.class.getName(), Long.class.getName() },
			EntryModelImpl.CREATEDATE_COLUMN_BITMASK |
			EntryModelImpl.TOUSERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_T = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_T",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the entries where createDate = &#63; and toUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param toUserId the to user ID
	 * @return the matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByC_T(long createDate, long toUserId)
		throws SystemException {
		return findByC_T(createDate, toUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the entries where createDate = &#63; and toUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param toUserId the to user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @return the range of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByC_T(long createDate, long toUserId, int start,
		int end) throws SystemException {
		return findByC_T(createDate, toUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the entries where createDate = &#63; and toUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param toUserId the to user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByC_T(long createDate, long toUserId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_T;
			finderArgs = new Object[] { createDate, toUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_T;
			finderArgs = new Object[] {
					createDate, toUserId,
					
					start, end, orderByComparator
				};
		}

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Entry entry : list) {
				if ((createDate != entry.getCreateDate()) ||
						(toUserId != entry.getToUserId())) {
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

			query.append(_SQL_SELECT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_T_CREATEDATE_2);

			query.append(_FINDER_COLUMN_C_T_TOUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				qPos.add(toUserId);

				if (!pagination) {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Entry>(list);
				}
				else {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first entry in the ordered set where createDate = &#63; and toUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry
	 * @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry findByC_T_First(long createDate, long toUserId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByC_T_First(createDate, toUserId, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(", toUserId=");
		msg.append(toUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first entry in the ordered set where createDate = &#63; and toUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry, or <code>null</code> if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByC_T_First(long createDate, long toUserId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Entry> list = findByC_T(createDate, toUserId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last entry in the ordered set where createDate = &#63; and toUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry
	 * @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry findByC_T_Last(long createDate, long toUserId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByC_T_Last(createDate, toUserId, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(", toUserId=");
		msg.append(toUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last entry in the ordered set where createDate = &#63; and toUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry, or <code>null</code> if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByC_T_Last(long createDate, long toUserId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_T(createDate, toUserId);

		if (count == 0) {
			return null;
		}

		List<Entry> list = findByC_T(createDate, toUserId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the entries before and after the current entry in the ordered set where createDate = &#63; and toUserId = &#63;.
	 *
	 * @param entryId the primary key of the current entry
	 * @param createDate the create date
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entry
	 * @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry[] findByC_T_PrevAndNext(long entryId, long createDate,
		long toUserId, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			Entry[] array = new EntryImpl[3];

			array[0] = getByC_T_PrevAndNext(session, entry, createDate,
					toUserId, orderByComparator, true);

			array[1] = entry;

			array[2] = getByC_T_PrevAndNext(session, entry, createDate,
					toUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Entry getByC_T_PrevAndNext(Session session, Entry entry,
		long createDate, long toUserId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ENTRY_WHERE);

		query.append(_FINDER_COLUMN_C_T_CREATEDATE_2);

		query.append(_FINDER_COLUMN_C_T_TOUSERID_2);

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
			query.append(EntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(createDate);

		qPos.add(toUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(entry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Entry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entries where createDate = &#63; and toUserId = &#63; from the database.
	 *
	 * @param createDate the create date
	 * @param toUserId the to user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByC_T(long createDate, long toUserId)
		throws SystemException {
		for (Entry entry : findByC_T(createDate, toUserId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(entry);
		}
	}

	/**
	 * Returns the number of entries where createDate = &#63; and toUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param toUserId the to user ID
	 * @return the number of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByC_T(long createDate, long toUserId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_T;

		Object[] finderArgs = new Object[] { createDate, toUserId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_T_CREATEDATE_2);

			query.append(_FINDER_COLUMN_C_T_TOUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				qPos.add(toUserId);

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

	private static final String _FINDER_COLUMN_C_T_CREATEDATE_2 = "entry.createDate = ? AND ";
	private static final String _FINDER_COLUMN_C_T_TOUSERID_2 = "entry.toUserId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_T = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_T = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_T",
			new String[] { Long.class.getName(), Long.class.getName() },
			EntryModelImpl.FROMUSERID_COLUMN_BITMASK |
			EntryModelImpl.TOUSERID_COLUMN_BITMASK |
			EntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_T = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_T",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the entries where fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @return the matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByF_T(long fromUserId, long toUserId)
		throws SystemException {
		return findByF_T(fromUserId, toUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the entries where fromUserId = &#63; and toUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @return the range of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByF_T(long fromUserId, long toUserId, int start,
		int end) throws SystemException {
		return findByF_T(fromUserId, toUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the entries where fromUserId = &#63; and toUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByF_T(long fromUserId, long toUserId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_T;
			finderArgs = new Object[] { fromUserId, toUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_T;
			finderArgs = new Object[] {
					fromUserId, toUserId,
					
					start, end, orderByComparator
				};
		}

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Entry entry : list) {
				if ((fromUserId != entry.getFromUserId()) ||
						(toUserId != entry.getToUserId())) {
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

			query.append(_SQL_SELECT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_F_T_FROMUSERID_2);

			query.append(_FINDER_COLUMN_F_T_TOUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fromUserId);

				qPos.add(toUserId);

				if (!pagination) {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Entry>(list);
				}
				else {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first entry in the ordered set where fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry
	 * @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry findByF_T_First(long fromUserId, long toUserId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByF_T_First(fromUserId, toUserId, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("fromUserId=");
		msg.append(fromUserId);

		msg.append(", toUserId=");
		msg.append(toUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first entry in the ordered set where fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry, or <code>null</code> if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByF_T_First(long fromUserId, long toUserId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Entry> list = findByF_T(fromUserId, toUserId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last entry in the ordered set where fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry
	 * @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry findByF_T_Last(long fromUserId, long toUserId,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByF_T_Last(fromUserId, toUserId, orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("fromUserId=");
		msg.append(fromUserId);

		msg.append(", toUserId=");
		msg.append(toUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last entry in the ordered set where fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry, or <code>null</code> if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByF_T_Last(long fromUserId, long toUserId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_T(fromUserId, toUserId);

		if (count == 0) {
			return null;
		}

		List<Entry> list = findByF_T(fromUserId, toUserId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the entries before and after the current entry in the ordered set where fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param entryId the primary key of the current entry
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entry
	 * @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry[] findByF_T_PrevAndNext(long entryId, long fromUserId,
		long toUserId, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			Entry[] array = new EntryImpl[3];

			array[0] = getByF_T_PrevAndNext(session, entry, fromUserId,
					toUserId, orderByComparator, true);

			array[1] = entry;

			array[2] = getByF_T_PrevAndNext(session, entry, fromUserId,
					toUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Entry getByF_T_PrevAndNext(Session session, Entry entry,
		long fromUserId, long toUserId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ENTRY_WHERE);

		query.append(_FINDER_COLUMN_F_T_FROMUSERID_2);

		query.append(_FINDER_COLUMN_F_T_TOUSERID_2);

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
			query.append(EntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(fromUserId);

		qPos.add(toUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(entry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Entry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entries where fromUserId = &#63; and toUserId = &#63; from the database.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByF_T(long fromUserId, long toUserId)
		throws SystemException {
		for (Entry entry : findByF_T(fromUserId, toUserId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(entry);
		}
	}

	/**
	 * Returns the number of entries where fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @return the number of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByF_T(long fromUserId, long toUserId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_T;

		Object[] finderArgs = new Object[] { fromUserId, toUserId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_F_T_FROMUSERID_2);

			query.append(_FINDER_COLUMN_F_T_TOUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fromUserId);

				qPos.add(toUserId);

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

	private static final String _FINDER_COLUMN_F_T_FROMUSERID_2 = "entry.fromUserId = ? AND ";
	private static final String _FINDER_COLUMN_F_T_TOUSERID_2 = "entry.toUserId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_F_T = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_F_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_F_T = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_F_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			EntryModelImpl.CREATEDATE_COLUMN_BITMASK |
			EntryModelImpl.FROMUSERID_COLUMN_BITMASK |
			EntryModelImpl.TOUSERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_F_T = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_F_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the entries where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @return the matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByC_F_T(long createDate, long fromUserId,
		long toUserId) throws SystemException {
		return findByC_F_T(createDate, fromUserId, toUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the entries where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @return the range of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByC_F_T(long createDate, long fromUserId,
		long toUserId, int start, int end) throws SystemException {
		return findByC_F_T(createDate, fromUserId, toUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the entries where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByC_F_T(long createDate, long fromUserId,
		long toUserId, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_F_T;
			finderArgs = new Object[] { createDate, fromUserId, toUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_F_T;
			finderArgs = new Object[] {
					createDate, fromUserId, toUserId,
					
					start, end, orderByComparator
				};
		}

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Entry entry : list) {
				if ((createDate != entry.getCreateDate()) ||
						(fromUserId != entry.getFromUserId()) ||
						(toUserId != entry.getToUserId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_F_T_CREATEDATE_2);

			query.append(_FINDER_COLUMN_C_F_T_FROMUSERID_2);

			query.append(_FINDER_COLUMN_C_F_T_TOUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				qPos.add(fromUserId);

				qPos.add(toUserId);

				if (!pagination) {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Entry>(list);
				}
				else {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first entry in the ordered set where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry
	 * @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry findByC_F_T_First(long createDate, long fromUserId,
		long toUserId, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByC_F_T_First(createDate, fromUserId, toUserId,
				orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(", fromUserId=");
		msg.append(fromUserId);

		msg.append(", toUserId=");
		msg.append(toUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first entry in the ordered set where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry, or <code>null</code> if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByC_F_T_First(long createDate, long fromUserId,
		long toUserId, OrderByComparator orderByComparator)
		throws SystemException {
		List<Entry> list = findByC_F_T(createDate, fromUserId, toUserId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last entry in the ordered set where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry
	 * @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry findByC_F_T_Last(long createDate, long fromUserId,
		long toUserId, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByC_F_T_Last(createDate, fromUserId, toUserId,
				orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("createDate=");
		msg.append(createDate);

		msg.append(", fromUserId=");
		msg.append(fromUserId);

		msg.append(", toUserId=");
		msg.append(toUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last entry in the ordered set where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry, or <code>null</code> if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByC_F_T_Last(long createDate, long fromUserId,
		long toUserId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByC_F_T(createDate, fromUserId, toUserId);

		if (count == 0) {
			return null;
		}

		List<Entry> list = findByC_F_T(createDate, fromUserId, toUserId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the entries before and after the current entry in the ordered set where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param entryId the primary key of the current entry
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entry
	 * @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry[] findByC_F_T_PrevAndNext(long entryId, long createDate,
		long fromUserId, long toUserId, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			Entry[] array = new EntryImpl[3];

			array[0] = getByC_F_T_PrevAndNext(session, entry, createDate,
					fromUserId, toUserId, orderByComparator, true);

			array[1] = entry;

			array[2] = getByC_F_T_PrevAndNext(session, entry, createDate,
					fromUserId, toUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Entry getByC_F_T_PrevAndNext(Session session, Entry entry,
		long createDate, long fromUserId, long toUserId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ENTRY_WHERE);

		query.append(_FINDER_COLUMN_C_F_T_CREATEDATE_2);

		query.append(_FINDER_COLUMN_C_F_T_FROMUSERID_2);

		query.append(_FINDER_COLUMN_C_F_T_TOUSERID_2);

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
			query.append(EntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(createDate);

		qPos.add(fromUserId);

		qPos.add(toUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(entry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Entry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entries where createDate = &#63; and fromUserId = &#63; and toUserId = &#63; from the database.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByC_F_T(long createDate, long fromUserId, long toUserId)
		throws SystemException {
		for (Entry entry : findByC_F_T(createDate, fromUserId, toUserId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(entry);
		}
	}

	/**
	 * Returns the number of entries where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	 *
	 * @param createDate the create date
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @return the number of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByC_F_T(long createDate, long fromUserId, long toUserId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_F_T;

		Object[] finderArgs = new Object[] { createDate, fromUserId, toUserId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_C_F_T_CREATEDATE_2);

			query.append(_FINDER_COLUMN_C_F_T_FROMUSERID_2);

			query.append(_FINDER_COLUMN_C_F_T_TOUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				qPos.add(fromUserId);

				qPos.add(toUserId);

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

	private static final String _FINDER_COLUMN_C_F_T_CREATEDATE_2 = "entry.createDate = ? AND ";
	private static final String _FINDER_COLUMN_C_F_T_FROMUSERID_2 = "entry.fromUserId = ? AND ";
	private static final String _FINDER_COLUMN_C_F_T_TOUSERID_2 = "entry.toUserId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_F_T_C = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByF_T_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_T_C = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, EntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByF_T_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			EntryModelImpl.FROMUSERID_COLUMN_BITMASK |
			EntryModelImpl.TOUSERID_COLUMN_BITMASK |
			EntryModelImpl.CONTENT_COLUMN_BITMASK |
			EntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_F_T_C = new FinderPath(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_T_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the entries where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param content the content
	 * @return the matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByF_T_C(long fromUserId, long toUserId,
		String content) throws SystemException {
		return findByF_T_C(fromUserId, toUserId, content, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the entries where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param content the content
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @return the range of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByF_T_C(long fromUserId, long toUserId,
		String content, int start, int end) throws SystemException {
		return findByF_T_C(fromUserId, toUserId, content, start, end, null);
	}

	/**
	 * Returns an ordered range of all the entries where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param content the content
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findByF_T_C(long fromUserId, long toUserId,
		String content, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_T_C;
			finderArgs = new Object[] { fromUserId, toUserId, content };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_F_T_C;
			finderArgs = new Object[] {
					fromUserId, toUserId, content,
					
					start, end, orderByComparator
				};
		}

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Entry entry : list) {
				if ((fromUserId != entry.getFromUserId()) ||
						(toUserId != entry.getToUserId()) ||
						!Validator.equals(content, entry.getContent())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_F_T_C_FROMUSERID_2);

			query.append(_FINDER_COLUMN_F_T_C_TOUSERID_2);

			boolean bindContent = false;

			if (content == null) {
				query.append(_FINDER_COLUMN_F_T_C_CONTENT_1);
			}
			else if (content.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_T_C_CONTENT_3);
			}
			else {
				bindContent = true;

				query.append(_FINDER_COLUMN_F_T_C_CONTENT_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fromUserId);

				qPos.add(toUserId);

				if (bindContent) {
					qPos.add(content);
				}

				if (!pagination) {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Entry>(list);
				}
				else {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first entry in the ordered set where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param content the content
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry
	 * @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry findByF_T_C_First(long fromUserId, long toUserId,
		String content, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByF_T_C_First(fromUserId, toUserId, content,
				orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("fromUserId=");
		msg.append(fromUserId);

		msg.append(", toUserId=");
		msg.append(toUserId);

		msg.append(", content=");
		msg.append(content);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first entry in the ordered set where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param content the content
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entry, or <code>null</code> if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByF_T_C_First(long fromUserId, long toUserId,
		String content, OrderByComparator orderByComparator)
		throws SystemException {
		List<Entry> list = findByF_T_C(fromUserId, toUserId, content, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last entry in the ordered set where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param content the content
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry
	 * @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry findByF_T_C_Last(long fromUserId, long toUserId,
		String content, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByF_T_C_Last(fromUserId, toUserId, content,
				orderByComparator);

		if (entry != null) {
			return entry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("fromUserId=");
		msg.append(fromUserId);

		msg.append(", toUserId=");
		msg.append(toUserId);

		msg.append(", content=");
		msg.append(content);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last entry in the ordered set where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param content the content
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entry, or <code>null</code> if a matching entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByF_T_C_Last(long fromUserId, long toUserId,
		String content, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByF_T_C(fromUserId, toUserId, content);

		if (count == 0) {
			return null;
		}

		List<Entry> list = findByF_T_C(fromUserId, toUserId, content,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the entries before and after the current entry in the ordered set where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	 *
	 * @param entryId the primary key of the current entry
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param content the content
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entry
	 * @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry[] findByF_T_C_PrevAndNext(long entryId, long fromUserId,
		long toUserId, String content, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		Entry entry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			Entry[] array = new EntryImpl[3];

			array[0] = getByF_T_C_PrevAndNext(session, entry, fromUserId,
					toUserId, content, orderByComparator, true);

			array[1] = entry;

			array[2] = getByF_T_C_PrevAndNext(session, entry, fromUserId,
					toUserId, content, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Entry getByF_T_C_PrevAndNext(Session session, Entry entry,
		long fromUserId, long toUserId, String content,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ENTRY_WHERE);

		query.append(_FINDER_COLUMN_F_T_C_FROMUSERID_2);

		query.append(_FINDER_COLUMN_F_T_C_TOUSERID_2);

		boolean bindContent = false;

		if (content == null) {
			query.append(_FINDER_COLUMN_F_T_C_CONTENT_1);
		}
		else if (content.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_F_T_C_CONTENT_3);
		}
		else {
			bindContent = true;

			query.append(_FINDER_COLUMN_F_T_C_CONTENT_2);
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
			query.append(EntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(fromUserId);

		qPos.add(toUserId);

		if (bindContent) {
			qPos.add(content);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(entry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Entry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entries where fromUserId = &#63; and toUserId = &#63; and content = &#63; from the database.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param content the content
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByF_T_C(long fromUserId, long toUserId, String content)
		throws SystemException {
		for (Entry entry : findByF_T_C(fromUserId, toUserId, content,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(entry);
		}
	}

	/**
	 * Returns the number of entries where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	 *
	 * @param fromUserId the from user ID
	 * @param toUserId the to user ID
	 * @param content the content
	 * @return the number of matching entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByF_T_C(long fromUserId, long toUserId, String content)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_F_T_C;

		Object[] finderArgs = new Object[] { fromUserId, toUserId, content };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ENTRY_WHERE);

			query.append(_FINDER_COLUMN_F_T_C_FROMUSERID_2);

			query.append(_FINDER_COLUMN_F_T_C_TOUSERID_2);

			boolean bindContent = false;

			if (content == null) {
				query.append(_FINDER_COLUMN_F_T_C_CONTENT_1);
			}
			else if (content.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_T_C_CONTENT_3);
			}
			else {
				bindContent = true;

				query.append(_FINDER_COLUMN_F_T_C_CONTENT_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fromUserId);

				qPos.add(toUserId);

				if (bindContent) {
					qPos.add(content);
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

	private static final String _FINDER_COLUMN_F_T_C_FROMUSERID_2 = "entry.fromUserId = ? AND ";
	private static final String _FINDER_COLUMN_F_T_C_TOUSERID_2 = "entry.toUserId = ? AND ";
	private static final String _FINDER_COLUMN_F_T_C_CONTENT_1 = "entry.content IS NULL";
	private static final String _FINDER_COLUMN_F_T_C_CONTENT_2 = "entry.content = ?";
	private static final String _FINDER_COLUMN_F_T_C_CONTENT_3 = "(entry.content IS NULL OR entry.content = '')";

	public EntryPersistenceImpl() {
		setModelClass(Entry.class);
	}

	/**
	 * Caches the entry in the entity cache if it is enabled.
	 *
	 * @param entry the entry
	 */
	@Override
	public void cacheResult(Entry entry) {
		EntityCacheUtil.putResult(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryImpl.class, entry.getPrimaryKey(), entry);

		entry.resetOriginalValues();
	}

	/**
	 * Caches the entries in the entity cache if it is enabled.
	 *
	 * @param entries the entries
	 */
	@Override
	public void cacheResult(List<Entry> entries) {
		for (Entry entry : entries) {
			if (EntityCacheUtil.getResult(EntryModelImpl.ENTITY_CACHE_ENABLED,
						EntryImpl.class, entry.getPrimaryKey()) == null) {
				cacheResult(entry);
			}
			else {
				entry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(EntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(EntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Entry entry) {
		EntityCacheUtil.removeResult(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryImpl.class, entry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Entry> entries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Entry entry : entries) {
			EntityCacheUtil.removeResult(EntryModelImpl.ENTITY_CACHE_ENABLED,
				EntryImpl.class, entry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new entry with the primary key. Does not add the entry to the database.
	 *
	 * @param entryId the primary key for the new entry
	 * @return the new entry
	 */
	@Override
	public Entry create(long entryId) {
		Entry entry = new EntryImpl();

		entry.setNew(true);
		entry.setPrimaryKey(entryId);

		return entry;
	}

	/**
	 * Removes the entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entryId the primary key of the entry
	 * @return the entry that was removed
	 * @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry remove(long entryId)
		throws NoSuchEntryException, SystemException {
		return remove((Serializable)entryId);
	}

	/**
	 * Removes the entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the entry
	 * @return the entry that was removed
	 * @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry remove(Serializable primaryKey)
		throws NoSuchEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Entry entry = (Entry)session.get(EntryImpl.class, primaryKey);

			if (entry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(entry);
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
	protected Entry removeImpl(Entry entry) throws SystemException {
		entry = toUnwrappedModel(entry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(entry)) {
				entry = (Entry)session.get(EntryImpl.class,
						entry.getPrimaryKeyObj());
			}

			if (entry != null) {
				session.delete(entry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (entry != null) {
			clearCache(entry);
		}

		return entry;
	}

	@Override
	public Entry updateImpl(com.liferay.chat.model.Entry entry)
		throws SystemException {
		entry = toUnwrappedModel(entry);

		boolean isNew = entry.isNew();

		EntryModelImpl entryModelImpl = (EntryModelImpl)entry;

		Session session = null;

		try {
			session = openSession();

			if (entry.isNew()) {
				session.save(entry);

				entry.setNew(false);
			}
			else {
				session.merge(entry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !EntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((entryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CREATEDATE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						entryModelImpl.getOriginalCreateDate()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CREATEDATE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CREATEDATE,
					args);

				args = new Object[] { entryModelImpl.getCreateDate() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CREATEDATE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CREATEDATE,
					args);
			}

			if ((entryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FROMUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						entryModelImpl.getOriginalFromUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FROMUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FROMUSERID,
					args);

				args = new Object[] { entryModelImpl.getFromUserId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FROMUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FROMUSERID,
					args);
			}

			if ((entryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TOUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						entryModelImpl.getOriginalToUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TOUSERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TOUSERID,
					args);

				args = new Object[] { entryModelImpl.getToUserId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TOUSERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TOUSERID,
					args);
			}

			if ((entryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_F.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						entryModelImpl.getOriginalCreateDate(),
						entryModelImpl.getOriginalFromUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_F, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_F,
					args);

				args = new Object[] {
						entryModelImpl.getCreateDate(),
						entryModelImpl.getFromUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_F, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_F,
					args);
			}

			if ((entryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						entryModelImpl.getOriginalCreateDate(),
						entryModelImpl.getOriginalToUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_T,
					args);

				args = new Object[] {
						entryModelImpl.getCreateDate(),
						entryModelImpl.getToUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_T,
					args);
			}

			if ((entryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						entryModelImpl.getOriginalFromUserId(),
						entryModelImpl.getOriginalToUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_F_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_T,
					args);

				args = new Object[] {
						entryModelImpl.getFromUserId(),
						entryModelImpl.getToUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_F_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_T,
					args);
			}

			if ((entryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_F_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						entryModelImpl.getOriginalCreateDate(),
						entryModelImpl.getOriginalFromUserId(),
						entryModelImpl.getOriginalToUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_F_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_F_T,
					args);

				args = new Object[] {
						entryModelImpl.getCreateDate(),
						entryModelImpl.getFromUserId(),
						entryModelImpl.getToUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_F_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_F_T,
					args);
			}

			if ((entryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_T_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						entryModelImpl.getOriginalFromUserId(),
						entryModelImpl.getOriginalToUserId(),
						entryModelImpl.getOriginalContent()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_F_T_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_T_C,
					args);

				args = new Object[] {
						entryModelImpl.getFromUserId(),
						entryModelImpl.getToUserId(),
						entryModelImpl.getContent()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_F_T_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_F_T_C,
					args);
			}
		}

		EntityCacheUtil.putResult(EntryModelImpl.ENTITY_CACHE_ENABLED,
			EntryImpl.class, entry.getPrimaryKey(), entry);

		entry.resetOriginalValues();

		return entry;
	}

	protected Entry toUnwrappedModel(Entry entry) {
		if (entry instanceof EntryImpl) {
			return entry;
		}

		EntryImpl entryImpl = new EntryImpl();

		entryImpl.setNew(entry.isNew());
		entryImpl.setPrimaryKey(entry.getPrimaryKey());

		entryImpl.setEntryId(entry.getEntryId());
		entryImpl.setCreateDate(entry.getCreateDate());
		entryImpl.setFromUserId(entry.getFromUserId());
		entryImpl.setToUserId(entry.getToUserId());
		entryImpl.setContent(entry.getContent());
		entryImpl.setFlag(entry.getFlag());

		return entryImpl;
	}

	/**
	 * Returns the entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the entry
	 * @return the entry
	 * @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByPrimaryKey(primaryKey);

		if (entry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return entry;
	}

	/**
	 * Returns the entry with the primary key or throws a {@link com.liferay.chat.NoSuchEntryException} if it could not be found.
	 *
	 * @param entryId the primary key of the entry
	 * @return the entry
	 * @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry findByPrimaryKey(long entryId)
		throws NoSuchEntryException, SystemException {
		return findByPrimaryKey((Serializable)entryId);
	}

	/**
	 * Returns the entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the entry
	 * @return the entry, or <code>null</code> if a entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Entry entry = (Entry)EntityCacheUtil.getResult(EntryModelImpl.ENTITY_CACHE_ENABLED,
				EntryImpl.class, primaryKey);

		if (entry == _nullEntry) {
			return null;
		}

		if (entry == null) {
			Session session = null;

			try {
				session = openSession();

				entry = (Entry)session.get(EntryImpl.class, primaryKey);

				if (entry != null) {
					cacheResult(entry);
				}
				else {
					EntityCacheUtil.putResult(EntryModelImpl.ENTITY_CACHE_ENABLED,
						EntryImpl.class, primaryKey, _nullEntry);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(EntryModelImpl.ENTITY_CACHE_ENABLED,
					EntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return entry;
	}

	/**
	 * Returns the entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entryId the primary key of the entry
	 * @return the entry, or <code>null</code> if a entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Entry fetchByPrimaryKey(long entryId) throws SystemException {
		return fetchByPrimaryKey((Serializable)entryId);
	}

	/**
	 * Returns all the entries.
	 *
	 * @return the entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @return the range of entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of entries
	 * @param end the upper bound of the range of entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Entry> findAll(int start, int end,
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

		List<Entry> list = (List<Entry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ENTRY;

				if (pagination) {
					sql = sql.concat(EntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Entry>(list);
				}
				else {
					list = (List<Entry>)QueryUtil.list(q, getDialect(), start,
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
	 * Removes all the entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Entry entry : findAll()) {
			remove(entry);
		}
	}

	/**
	 * Returns the number of entries.
	 *
	 * @return the number of entries
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

				Query q = session.createQuery(_SQL_COUNT_ENTRY);

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
	 * Initializes the entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.chat.model.Entry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Entry>> listenersList = new ArrayList<ModelListener<Entry>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Entry>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(EntryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_ENTRY = "SELECT entry FROM Entry entry";
	private static final String _SQL_SELECT_ENTRY_WHERE = "SELECT entry FROM Entry entry WHERE ";
	private static final String _SQL_COUNT_ENTRY = "SELECT COUNT(entry) FROM Entry entry";
	private static final String _SQL_COUNT_ENTRY_WHERE = "SELECT COUNT(entry) FROM Entry entry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "entry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Entry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Entry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(EntryPersistenceImpl.class);
	private static Entry _nullEntry = new EntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Entry> toCacheModel() {
				return _nullEntryCacheModel;
			}
		};

	private static CacheModel<Entry> _nullEntryCacheModel = new CacheModel<Entry>() {
			@Override
			public Entry toEntityModel() {
				return _nullEntry;
			}
		};
}