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

package com.liferay.shortlink.service.persistence;

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
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.shortlink.NoSuchEntryException;
import com.liferay.shortlink.model.ShortLinkEntry;
import com.liferay.shortlink.model.impl.ShortLinkEntryImpl;
import com.liferay.shortlink.model.impl.ShortLinkEntryModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the short link entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ShortLinkEntryPersistence
 * @see ShortLinkEntryUtil
 * @generated
 */
public class ShortLinkEntryPersistenceImpl extends BasePersistenceImpl<ShortLinkEntry>
	implements ShortLinkEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ShortLinkEntryUtil} to access the short link entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ShortLinkEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ShortLinkEntryModelImpl.ENTITY_CACHE_ENABLED,
			ShortLinkEntryModelImpl.FINDER_CACHE_ENABLED,
			ShortLinkEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ShortLinkEntryModelImpl.ENTITY_CACHE_ENABLED,
			ShortLinkEntryModelImpl.FINDER_CACHE_ENABLED,
			ShortLinkEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ShortLinkEntryModelImpl.ENTITY_CACHE_ENABLED,
			ShortLinkEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ShortLinkEntryModelImpl.ENTITY_CACHE_ENABLED,
			ShortLinkEntryModelImpl.FINDER_CACHE_ENABLED,
			ShortLinkEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ShortLinkEntryModelImpl.ENTITY_CACHE_ENABLED,
			ShortLinkEntryModelImpl.FINDER_CACHE_ENABLED,
			ShortLinkEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ShortLinkEntryModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ShortLinkEntryModelImpl.ENTITY_CACHE_ENABLED,
			ShortLinkEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the short link entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching short link entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ShortLinkEntry> findByUuid(String uuid)
		throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the short link entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.shortlink.model.impl.ShortLinkEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of short link entries
	 * @param end the upper bound of the range of short link entries (not inclusive)
	 * @return the range of matching short link entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ShortLinkEntry> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the short link entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.shortlink.model.impl.ShortLinkEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of short link entries
	 * @param end the upper bound of the range of short link entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching short link entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ShortLinkEntry> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<ShortLinkEntry> list = (List<ShortLinkEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ShortLinkEntry shortLinkEntry : list) {
				if (!Validator.equals(uuid, shortLinkEntry.getUuid())) {
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

			query.append(_SQL_SELECT_SHORTLINKENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ShortLinkEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<ShortLinkEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ShortLinkEntry>(list);
				}
				else {
					list = (List<ShortLinkEntry>)QueryUtil.list(q,
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
	 * Returns the first short link entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching short link entry
	 * @throws com.liferay.shortlink.NoSuchEntryException if a matching short link entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		ShortLinkEntry shortLinkEntry = fetchByUuid_First(uuid,
				orderByComparator);

		if (shortLinkEntry != null) {
			return shortLinkEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first short link entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching short link entry, or <code>null</code> if a matching short link entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<ShortLinkEntry> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last short link entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching short link entry
	 * @throws com.liferay.shortlink.NoSuchEntryException if a matching short link entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		ShortLinkEntry shortLinkEntry = fetchByUuid_Last(uuid, orderByComparator);

		if (shortLinkEntry != null) {
			return shortLinkEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last short link entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching short link entry, or <code>null</code> if a matching short link entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ShortLinkEntry> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the short link entries before and after the current short link entry in the ordered set where uuid = &#63;.
	 *
	 * @param shortLinkEntryId the primary key of the current short link entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next short link entry
	 * @throws com.liferay.shortlink.NoSuchEntryException if a short link entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry[] findByUuid_PrevAndNext(long shortLinkEntryId,
		String uuid, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		ShortLinkEntry shortLinkEntry = findByPrimaryKey(shortLinkEntryId);

		Session session = null;

		try {
			session = openSession();

			ShortLinkEntry[] array = new ShortLinkEntryImpl[3];

			array[0] = getByUuid_PrevAndNext(session, shortLinkEntry, uuid,
					orderByComparator, true);

			array[1] = shortLinkEntry;

			array[2] = getByUuid_PrevAndNext(session, shortLinkEntry, uuid,
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

	protected ShortLinkEntry getByUuid_PrevAndNext(Session session,
		ShortLinkEntry shortLinkEntry, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SHORTLINKENTRY_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
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
			query.append(ShortLinkEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(shortLinkEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ShortLinkEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the short link entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid(String uuid) throws SystemException {
		for (ShortLinkEntry shortLinkEntry : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(shortLinkEntry);
		}
	}

	/**
	 * Returns the number of short link entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching short link entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUuid(String uuid) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SHORTLINKENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "shortLinkEntry.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "shortLinkEntry.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(shortLinkEntry.uuid IS NULL OR shortLinkEntry.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LTMODIFIEDDATE =
		new FinderPath(ShortLinkEntryModelImpl.ENTITY_CACHE_ENABLED,
			ShortLinkEntryModelImpl.FINDER_CACHE_ENABLED,
			ShortLinkEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByLtModifiedDate",
			new String[] {
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_LTMODIFIEDDATE =
		new FinderPath(ShortLinkEntryModelImpl.ENTITY_CACHE_ENABLED,
			ShortLinkEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByLtModifiedDate",
			new String[] { Date.class.getName() });

	/**
	 * Returns all the short link entries where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @return the matching short link entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ShortLinkEntry> findByLtModifiedDate(Date modifiedDate)
		throws SystemException {
		return findByLtModifiedDate(modifiedDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the short link entries where modifiedDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.shortlink.model.impl.ShortLinkEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of short link entries
	 * @param end the upper bound of the range of short link entries (not inclusive)
	 * @return the range of matching short link entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ShortLinkEntry> findByLtModifiedDate(Date modifiedDate,
		int start, int end) throws SystemException {
		return findByLtModifiedDate(modifiedDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the short link entries where modifiedDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.shortlink.model.impl.ShortLinkEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of short link entries
	 * @param end the upper bound of the range of short link entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching short link entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ShortLinkEntry> findByLtModifiedDate(Date modifiedDate,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LTMODIFIEDDATE;
		finderArgs = new Object[] { modifiedDate, start, end, orderByComparator };

		List<ShortLinkEntry> list = (List<ShortLinkEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ShortLinkEntry shortLinkEntry : list) {
				if ((modifiedDate.getTime() <= shortLinkEntry.getModifiedDate()
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

			query.append(_SQL_SELECT_SHORTLINKENTRY_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ShortLinkEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModifiedDate) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				if (!pagination) {
					list = (List<ShortLinkEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ShortLinkEntry>(list);
				}
				else {
					list = (List<ShortLinkEntry>)QueryUtil.list(q,
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
	 * Returns the first short link entry in the ordered set where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching short link entry
	 * @throws com.liferay.shortlink.NoSuchEntryException if a matching short link entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry findByLtModifiedDate_First(Date modifiedDate,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		ShortLinkEntry shortLinkEntry = fetchByLtModifiedDate_First(modifiedDate,
				orderByComparator);

		if (shortLinkEntry != null) {
			return shortLinkEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first short link entry in the ordered set where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching short link entry, or <code>null</code> if a matching short link entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry fetchByLtModifiedDate_First(Date modifiedDate,
		OrderByComparator orderByComparator) throws SystemException {
		List<ShortLinkEntry> list = findByLtModifiedDate(modifiedDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last short link entry in the ordered set where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching short link entry
	 * @throws com.liferay.shortlink.NoSuchEntryException if a matching short link entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry findByLtModifiedDate_Last(Date modifiedDate,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		ShortLinkEntry shortLinkEntry = fetchByLtModifiedDate_Last(modifiedDate,
				orderByComparator);

		if (shortLinkEntry != null) {
			return shortLinkEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last short link entry in the ordered set where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching short link entry, or <code>null</code> if a matching short link entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry fetchByLtModifiedDate_Last(Date modifiedDate,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByLtModifiedDate(modifiedDate);

		if (count == 0) {
			return null;
		}

		List<ShortLinkEntry> list = findByLtModifiedDate(modifiedDate,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the short link entries before and after the current short link entry in the ordered set where modifiedDate &lt; &#63;.
	 *
	 * @param shortLinkEntryId the primary key of the current short link entry
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next short link entry
	 * @throws com.liferay.shortlink.NoSuchEntryException if a short link entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry[] findByLtModifiedDate_PrevAndNext(
		long shortLinkEntryId, Date modifiedDate,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		ShortLinkEntry shortLinkEntry = findByPrimaryKey(shortLinkEntryId);

		Session session = null;

		try {
			session = openSession();

			ShortLinkEntry[] array = new ShortLinkEntryImpl[3];

			array[0] = getByLtModifiedDate_PrevAndNext(session, shortLinkEntry,
					modifiedDate, orderByComparator, true);

			array[1] = shortLinkEntry;

			array[2] = getByLtModifiedDate_PrevAndNext(session, shortLinkEntry,
					modifiedDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ShortLinkEntry getByLtModifiedDate_PrevAndNext(Session session,
		ShortLinkEntry shortLinkEntry, Date modifiedDate,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SHORTLINKENTRY_WHERE);

		boolean bindModifiedDate = false;

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_1);
		}
		else {
			bindModifiedDate = true;

			query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_2);
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
			query.append(ShortLinkEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindModifiedDate) {
			qPos.add(CalendarUtil.getTimestamp(modifiedDate));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(shortLinkEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ShortLinkEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the short link entries where modifiedDate &lt; &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByLtModifiedDate(Date modifiedDate)
		throws SystemException {
		for (ShortLinkEntry shortLinkEntry : findByLtModifiedDate(
				modifiedDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(shortLinkEntry);
		}
	}

	/**
	 * Returns the number of short link entries where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @return the number of matching short link entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByLtModifiedDate(Date modifiedDate)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_LTMODIFIEDDATE;

		Object[] finderArgs = new Object[] { modifiedDate };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SHORTLINKENTRY_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModifiedDate) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
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

	private static final String _FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_1 = "shortLinkEntry.modifiedDate < NULL";
	private static final String _FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_2 = "shortLinkEntry.modifiedDate < ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_SHORTURL = new FinderPath(ShortLinkEntryModelImpl.ENTITY_CACHE_ENABLED,
			ShortLinkEntryModelImpl.FINDER_CACHE_ENABLED,
			ShortLinkEntryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByShortURL", new String[] { String.class.getName() },
			ShortLinkEntryModelImpl.SHORTURL_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SHORTURL = new FinderPath(ShortLinkEntryModelImpl.ENTITY_CACHE_ENABLED,
			ShortLinkEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByShortURL",
			new String[] { String.class.getName() });

	/**
	 * Returns the short link entry where shortURL = &#63; or throws a {@link com.liferay.shortlink.NoSuchEntryException} if it could not be found.
	 *
	 * @param shortURL the short u r l
	 * @return the matching short link entry
	 * @throws com.liferay.shortlink.NoSuchEntryException if a matching short link entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry findByShortURL(String shortURL)
		throws NoSuchEntryException, SystemException {
		ShortLinkEntry shortLinkEntry = fetchByShortURL(shortURL);

		if (shortLinkEntry == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("shortURL=");
			msg.append(shortURL);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchEntryException(msg.toString());
		}

		return shortLinkEntry;
	}

	/**
	 * Returns the short link entry where shortURL = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param shortURL the short u r l
	 * @return the matching short link entry, or <code>null</code> if a matching short link entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry fetchByShortURL(String shortURL)
		throws SystemException {
		return fetchByShortURL(shortURL, true);
	}

	/**
	 * Returns the short link entry where shortURL = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param shortURL the short u r l
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching short link entry, or <code>null</code> if a matching short link entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry fetchByShortURL(String shortURL,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { shortURL };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_SHORTURL,
					finderArgs, this);
		}

		if (result instanceof ShortLinkEntry) {
			ShortLinkEntry shortLinkEntry = (ShortLinkEntry)result;

			if (!Validator.equals(shortURL, shortLinkEntry.getShortURL())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SHORTLINKENTRY_WHERE);

			boolean bindShortURL = false;

			if (shortURL == null) {
				query.append(_FINDER_COLUMN_SHORTURL_SHORTURL_1);
			}
			else if (shortURL.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SHORTURL_SHORTURL_3);
			}
			else {
				bindShortURL = true;

				query.append(_FINDER_COLUMN_SHORTURL_SHORTURL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindShortURL) {
					qPos.add(shortURL);
				}

				List<ShortLinkEntry> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SHORTURL,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"ShortLinkEntryPersistenceImpl.fetchByShortURL(String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					ShortLinkEntry shortLinkEntry = list.get(0);

					result = shortLinkEntry;

					cacheResult(shortLinkEntry);

					if ((shortLinkEntry.getShortURL() == null) ||
							!shortLinkEntry.getShortURL().equals(shortURL)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SHORTURL,
							finderArgs, shortLinkEntry);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SHORTURL,
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
			return (ShortLinkEntry)result;
		}
	}

	/**
	 * Removes the short link entry where shortURL = &#63; from the database.
	 *
	 * @param shortURL the short u r l
	 * @return the short link entry that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry removeByShortURL(String shortURL)
		throws NoSuchEntryException, SystemException {
		ShortLinkEntry shortLinkEntry = findByShortURL(shortURL);

		return remove(shortLinkEntry);
	}

	/**
	 * Returns the number of short link entries where shortURL = &#63;.
	 *
	 * @param shortURL the short u r l
	 * @return the number of matching short link entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByShortURL(String shortURL) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SHORTURL;

		Object[] finderArgs = new Object[] { shortURL };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SHORTLINKENTRY_WHERE);

			boolean bindShortURL = false;

			if (shortURL == null) {
				query.append(_FINDER_COLUMN_SHORTURL_SHORTURL_1);
			}
			else if (shortURL.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SHORTURL_SHORTURL_3);
			}
			else {
				bindShortURL = true;

				query.append(_FINDER_COLUMN_SHORTURL_SHORTURL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindShortURL) {
					qPos.add(shortURL);
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

	private static final String _FINDER_COLUMN_SHORTURL_SHORTURL_1 = "shortLinkEntry.shortURL IS NULL";
	private static final String _FINDER_COLUMN_SHORTURL_SHORTURL_2 = "shortLinkEntry.shortURL = ?";
	private static final String _FINDER_COLUMN_SHORTURL_SHORTURL_3 = "(shortLinkEntry.shortURL IS NULL OR shortLinkEntry.shortURL = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_AUTOGENERATED =
		new FinderPath(ShortLinkEntryModelImpl.ENTITY_CACHE_ENABLED,
			ShortLinkEntryModelImpl.FINDER_CACHE_ENABLED,
			ShortLinkEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByAutogenerated",
			new String[] {
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AUTOGENERATED =
		new FinderPath(ShortLinkEntryModelImpl.ENTITY_CACHE_ENABLED,
			ShortLinkEntryModelImpl.FINDER_CACHE_ENABLED,
			ShortLinkEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAutogenerated",
			new String[] { Boolean.class.getName() },
			ShortLinkEntryModelImpl.AUTOGENERATED_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_AUTOGENERATED = new FinderPath(ShortLinkEntryModelImpl.ENTITY_CACHE_ENABLED,
			ShortLinkEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAutogenerated",
			new String[] { Boolean.class.getName() });

	/**
	 * Returns all the short link entries where autogenerated = &#63;.
	 *
	 * @param autogenerated the autogenerated
	 * @return the matching short link entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ShortLinkEntry> findByAutogenerated(boolean autogenerated)
		throws SystemException {
		return findByAutogenerated(autogenerated, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the short link entries where autogenerated = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.shortlink.model.impl.ShortLinkEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param autogenerated the autogenerated
	 * @param start the lower bound of the range of short link entries
	 * @param end the upper bound of the range of short link entries (not inclusive)
	 * @return the range of matching short link entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ShortLinkEntry> findByAutogenerated(boolean autogenerated,
		int start, int end) throws SystemException {
		return findByAutogenerated(autogenerated, start, end, null);
	}

	/**
	 * Returns an ordered range of all the short link entries where autogenerated = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.shortlink.model.impl.ShortLinkEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param autogenerated the autogenerated
	 * @param start the lower bound of the range of short link entries
	 * @param end the upper bound of the range of short link entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching short link entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ShortLinkEntry> findByAutogenerated(boolean autogenerated,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AUTOGENERATED;
			finderArgs = new Object[] { autogenerated };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_AUTOGENERATED;
			finderArgs = new Object[] {
					autogenerated,
					
					start, end, orderByComparator
				};
		}

		List<ShortLinkEntry> list = (List<ShortLinkEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ShortLinkEntry shortLinkEntry : list) {
				if ((autogenerated != shortLinkEntry.getAutogenerated())) {
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

			query.append(_SQL_SELECT_SHORTLINKENTRY_WHERE);

			query.append(_FINDER_COLUMN_AUTOGENERATED_AUTOGENERATED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ShortLinkEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(autogenerated);

				if (!pagination) {
					list = (List<ShortLinkEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ShortLinkEntry>(list);
				}
				else {
					list = (List<ShortLinkEntry>)QueryUtil.list(q,
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
	 * Returns the first short link entry in the ordered set where autogenerated = &#63;.
	 *
	 * @param autogenerated the autogenerated
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching short link entry
	 * @throws com.liferay.shortlink.NoSuchEntryException if a matching short link entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry findByAutogenerated_First(boolean autogenerated,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		ShortLinkEntry shortLinkEntry = fetchByAutogenerated_First(autogenerated,
				orderByComparator);

		if (shortLinkEntry != null) {
			return shortLinkEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("autogenerated=");
		msg.append(autogenerated);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first short link entry in the ordered set where autogenerated = &#63;.
	 *
	 * @param autogenerated the autogenerated
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching short link entry, or <code>null</code> if a matching short link entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry fetchByAutogenerated_First(boolean autogenerated,
		OrderByComparator orderByComparator) throws SystemException {
		List<ShortLinkEntry> list = findByAutogenerated(autogenerated, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last short link entry in the ordered set where autogenerated = &#63;.
	 *
	 * @param autogenerated the autogenerated
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching short link entry
	 * @throws com.liferay.shortlink.NoSuchEntryException if a matching short link entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry findByAutogenerated_Last(boolean autogenerated,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		ShortLinkEntry shortLinkEntry = fetchByAutogenerated_Last(autogenerated,
				orderByComparator);

		if (shortLinkEntry != null) {
			return shortLinkEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("autogenerated=");
		msg.append(autogenerated);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last short link entry in the ordered set where autogenerated = &#63;.
	 *
	 * @param autogenerated the autogenerated
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching short link entry, or <code>null</code> if a matching short link entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry fetchByAutogenerated_Last(boolean autogenerated,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAutogenerated(autogenerated);

		if (count == 0) {
			return null;
		}

		List<ShortLinkEntry> list = findByAutogenerated(autogenerated,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the short link entries before and after the current short link entry in the ordered set where autogenerated = &#63;.
	 *
	 * @param shortLinkEntryId the primary key of the current short link entry
	 * @param autogenerated the autogenerated
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next short link entry
	 * @throws com.liferay.shortlink.NoSuchEntryException if a short link entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry[] findByAutogenerated_PrevAndNext(
		long shortLinkEntryId, boolean autogenerated,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		ShortLinkEntry shortLinkEntry = findByPrimaryKey(shortLinkEntryId);

		Session session = null;

		try {
			session = openSession();

			ShortLinkEntry[] array = new ShortLinkEntryImpl[3];

			array[0] = getByAutogenerated_PrevAndNext(session, shortLinkEntry,
					autogenerated, orderByComparator, true);

			array[1] = shortLinkEntry;

			array[2] = getByAutogenerated_PrevAndNext(session, shortLinkEntry,
					autogenerated, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ShortLinkEntry getByAutogenerated_PrevAndNext(Session session,
		ShortLinkEntry shortLinkEntry, boolean autogenerated,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SHORTLINKENTRY_WHERE);

		query.append(_FINDER_COLUMN_AUTOGENERATED_AUTOGENERATED_2);

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
			query.append(ShortLinkEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(autogenerated);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(shortLinkEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ShortLinkEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the short link entries where autogenerated = &#63; from the database.
	 *
	 * @param autogenerated the autogenerated
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByAutogenerated(boolean autogenerated)
		throws SystemException {
		for (ShortLinkEntry shortLinkEntry : findByAutogenerated(
				autogenerated, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(shortLinkEntry);
		}
	}

	/**
	 * Returns the number of short link entries where autogenerated = &#63;.
	 *
	 * @param autogenerated the autogenerated
	 * @return the number of matching short link entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByAutogenerated(boolean autogenerated)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_AUTOGENERATED;

		Object[] finderArgs = new Object[] { autogenerated };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SHORTLINKENTRY_WHERE);

			query.append(_FINDER_COLUMN_AUTOGENERATED_AUTOGENERATED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(autogenerated);

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

	private static final String _FINDER_COLUMN_AUTOGENERATED_AUTOGENERATED_2 = "shortLinkEntry.autogenerated = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_OURL_A = new FinderPath(ShortLinkEntryModelImpl.ENTITY_CACHE_ENABLED,
			ShortLinkEntryModelImpl.FINDER_CACHE_ENABLED,
			ShortLinkEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByOURL_A",
			new String[] {
				String.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OURL_A =
		new FinderPath(ShortLinkEntryModelImpl.ENTITY_CACHE_ENABLED,
			ShortLinkEntryModelImpl.FINDER_CACHE_ENABLED,
			ShortLinkEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByOURL_A",
			new String[] { String.class.getName(), Boolean.class.getName() },
			ShortLinkEntryModelImpl.ORIGINALURL_COLUMN_BITMASK |
			ShortLinkEntryModelImpl.AUTOGENERATED_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_OURL_A = new FinderPath(ShortLinkEntryModelImpl.ENTITY_CACHE_ENABLED,
			ShortLinkEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByOURL_A",
			new String[] { String.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the short link entries where originalURL = &#63; and autogenerated = &#63;.
	 *
	 * @param originalURL the original u r l
	 * @param autogenerated the autogenerated
	 * @return the matching short link entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ShortLinkEntry> findByOURL_A(String originalURL,
		boolean autogenerated) throws SystemException {
		return findByOURL_A(originalURL, autogenerated, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the short link entries where originalURL = &#63; and autogenerated = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.shortlink.model.impl.ShortLinkEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param originalURL the original u r l
	 * @param autogenerated the autogenerated
	 * @param start the lower bound of the range of short link entries
	 * @param end the upper bound of the range of short link entries (not inclusive)
	 * @return the range of matching short link entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ShortLinkEntry> findByOURL_A(String originalURL,
		boolean autogenerated, int start, int end) throws SystemException {
		return findByOURL_A(originalURL, autogenerated, start, end, null);
	}

	/**
	 * Returns an ordered range of all the short link entries where originalURL = &#63; and autogenerated = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.shortlink.model.impl.ShortLinkEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param originalURL the original u r l
	 * @param autogenerated the autogenerated
	 * @param start the lower bound of the range of short link entries
	 * @param end the upper bound of the range of short link entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching short link entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ShortLinkEntry> findByOURL_A(String originalURL,
		boolean autogenerated, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OURL_A;
			finderArgs = new Object[] { originalURL, autogenerated };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_OURL_A;
			finderArgs = new Object[] {
					originalURL, autogenerated,
					
					start, end, orderByComparator
				};
		}

		List<ShortLinkEntry> list = (List<ShortLinkEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ShortLinkEntry shortLinkEntry : list) {
				if (!Validator.equals(originalURL,
							shortLinkEntry.getOriginalURL()) ||
						(autogenerated != shortLinkEntry.getAutogenerated())) {
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

			query.append(_SQL_SELECT_SHORTLINKENTRY_WHERE);

			boolean bindOriginalURL = false;

			if (originalURL == null) {
				query.append(_FINDER_COLUMN_OURL_A_ORIGINALURL_1);
			}
			else if (originalURL.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_OURL_A_ORIGINALURL_3);
			}
			else {
				bindOriginalURL = true;

				query.append(_FINDER_COLUMN_OURL_A_ORIGINALURL_2);
			}

			query.append(_FINDER_COLUMN_OURL_A_AUTOGENERATED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ShortLinkEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindOriginalURL) {
					qPos.add(originalURL);
				}

				qPos.add(autogenerated);

				if (!pagination) {
					list = (List<ShortLinkEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ShortLinkEntry>(list);
				}
				else {
					list = (List<ShortLinkEntry>)QueryUtil.list(q,
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
	 * Returns the first short link entry in the ordered set where originalURL = &#63; and autogenerated = &#63;.
	 *
	 * @param originalURL the original u r l
	 * @param autogenerated the autogenerated
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching short link entry
	 * @throws com.liferay.shortlink.NoSuchEntryException if a matching short link entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry findByOURL_A_First(String originalURL,
		boolean autogenerated, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		ShortLinkEntry shortLinkEntry = fetchByOURL_A_First(originalURL,
				autogenerated, orderByComparator);

		if (shortLinkEntry != null) {
			return shortLinkEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("originalURL=");
		msg.append(originalURL);

		msg.append(", autogenerated=");
		msg.append(autogenerated);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first short link entry in the ordered set where originalURL = &#63; and autogenerated = &#63;.
	 *
	 * @param originalURL the original u r l
	 * @param autogenerated the autogenerated
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching short link entry, or <code>null</code> if a matching short link entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry fetchByOURL_A_First(String originalURL,
		boolean autogenerated, OrderByComparator orderByComparator)
		throws SystemException {
		List<ShortLinkEntry> list = findByOURL_A(originalURL, autogenerated, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last short link entry in the ordered set where originalURL = &#63; and autogenerated = &#63;.
	 *
	 * @param originalURL the original u r l
	 * @param autogenerated the autogenerated
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching short link entry
	 * @throws com.liferay.shortlink.NoSuchEntryException if a matching short link entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry findByOURL_A_Last(String originalURL,
		boolean autogenerated, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		ShortLinkEntry shortLinkEntry = fetchByOURL_A_Last(originalURL,
				autogenerated, orderByComparator);

		if (shortLinkEntry != null) {
			return shortLinkEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("originalURL=");
		msg.append(originalURL);

		msg.append(", autogenerated=");
		msg.append(autogenerated);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last short link entry in the ordered set where originalURL = &#63; and autogenerated = &#63;.
	 *
	 * @param originalURL the original u r l
	 * @param autogenerated the autogenerated
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching short link entry, or <code>null</code> if a matching short link entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry fetchByOURL_A_Last(String originalURL,
		boolean autogenerated, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByOURL_A(originalURL, autogenerated);

		if (count == 0) {
			return null;
		}

		List<ShortLinkEntry> list = findByOURL_A(originalURL, autogenerated,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the short link entries before and after the current short link entry in the ordered set where originalURL = &#63; and autogenerated = &#63;.
	 *
	 * @param shortLinkEntryId the primary key of the current short link entry
	 * @param originalURL the original u r l
	 * @param autogenerated the autogenerated
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next short link entry
	 * @throws com.liferay.shortlink.NoSuchEntryException if a short link entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry[] findByOURL_A_PrevAndNext(long shortLinkEntryId,
		String originalURL, boolean autogenerated,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		ShortLinkEntry shortLinkEntry = findByPrimaryKey(shortLinkEntryId);

		Session session = null;

		try {
			session = openSession();

			ShortLinkEntry[] array = new ShortLinkEntryImpl[3];

			array[0] = getByOURL_A_PrevAndNext(session, shortLinkEntry,
					originalURL, autogenerated, orderByComparator, true);

			array[1] = shortLinkEntry;

			array[2] = getByOURL_A_PrevAndNext(session, shortLinkEntry,
					originalURL, autogenerated, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected ShortLinkEntry getByOURL_A_PrevAndNext(Session session,
		ShortLinkEntry shortLinkEntry, String originalURL,
		boolean autogenerated, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SHORTLINKENTRY_WHERE);

		boolean bindOriginalURL = false;

		if (originalURL == null) {
			query.append(_FINDER_COLUMN_OURL_A_ORIGINALURL_1);
		}
		else if (originalURL.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_OURL_A_ORIGINALURL_3);
		}
		else {
			bindOriginalURL = true;

			query.append(_FINDER_COLUMN_OURL_A_ORIGINALURL_2);
		}

		query.append(_FINDER_COLUMN_OURL_A_AUTOGENERATED_2);

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
			query.append(ShortLinkEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindOriginalURL) {
			qPos.add(originalURL);
		}

		qPos.add(autogenerated);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(shortLinkEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ShortLinkEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the short link entries where originalURL = &#63; and autogenerated = &#63; from the database.
	 *
	 * @param originalURL the original u r l
	 * @param autogenerated the autogenerated
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByOURL_A(String originalURL, boolean autogenerated)
		throws SystemException {
		for (ShortLinkEntry shortLinkEntry : findByOURL_A(originalURL,
				autogenerated, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(shortLinkEntry);
		}
	}

	/**
	 * Returns the number of short link entries where originalURL = &#63; and autogenerated = &#63;.
	 *
	 * @param originalURL the original u r l
	 * @param autogenerated the autogenerated
	 * @return the number of matching short link entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByOURL_A(String originalURL, boolean autogenerated)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_OURL_A;

		Object[] finderArgs = new Object[] { originalURL, autogenerated };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SHORTLINKENTRY_WHERE);

			boolean bindOriginalURL = false;

			if (originalURL == null) {
				query.append(_FINDER_COLUMN_OURL_A_ORIGINALURL_1);
			}
			else if (originalURL.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_OURL_A_ORIGINALURL_3);
			}
			else {
				bindOriginalURL = true;

				query.append(_FINDER_COLUMN_OURL_A_ORIGINALURL_2);
			}

			query.append(_FINDER_COLUMN_OURL_A_AUTOGENERATED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindOriginalURL) {
					qPos.add(originalURL);
				}

				qPos.add(autogenerated);

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

	private static final String _FINDER_COLUMN_OURL_A_ORIGINALURL_1 = "shortLinkEntry.originalURL IS NULL AND ";
	private static final String _FINDER_COLUMN_OURL_A_ORIGINALURL_2 = "shortLinkEntry.originalURL = ? AND ";
	private static final String _FINDER_COLUMN_OURL_A_ORIGINALURL_3 = "(shortLinkEntry.originalURL IS NULL OR shortLinkEntry.originalURL = '') AND ";
	private static final String _FINDER_COLUMN_OURL_A_AUTOGENERATED_2 = "shortLinkEntry.autogenerated = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_SURL_A = new FinderPath(ShortLinkEntryModelImpl.ENTITY_CACHE_ENABLED,
			ShortLinkEntryModelImpl.FINDER_CACHE_ENABLED,
			ShortLinkEntryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchBySURL_A",
			new String[] { String.class.getName(), Boolean.class.getName() },
			ShortLinkEntryModelImpl.SHORTURL_COLUMN_BITMASK |
			ShortLinkEntryModelImpl.AUTOGENERATED_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SURL_A = new FinderPath(ShortLinkEntryModelImpl.ENTITY_CACHE_ENABLED,
			ShortLinkEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySURL_A",
			new String[] { String.class.getName(), Boolean.class.getName() });

	/**
	 * Returns the short link entry where shortURL = &#63; and autogenerated = &#63; or throws a {@link com.liferay.shortlink.NoSuchEntryException} if it could not be found.
	 *
	 * @param shortURL the short u r l
	 * @param autogenerated the autogenerated
	 * @return the matching short link entry
	 * @throws com.liferay.shortlink.NoSuchEntryException if a matching short link entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry findBySURL_A(String shortURL, boolean autogenerated)
		throws NoSuchEntryException, SystemException {
		ShortLinkEntry shortLinkEntry = fetchBySURL_A(shortURL, autogenerated);

		if (shortLinkEntry == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("shortURL=");
			msg.append(shortURL);

			msg.append(", autogenerated=");
			msg.append(autogenerated);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchEntryException(msg.toString());
		}

		return shortLinkEntry;
	}

	/**
	 * Returns the short link entry where shortURL = &#63; and autogenerated = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param shortURL the short u r l
	 * @param autogenerated the autogenerated
	 * @return the matching short link entry, or <code>null</code> if a matching short link entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry fetchBySURL_A(String shortURL, boolean autogenerated)
		throws SystemException {
		return fetchBySURL_A(shortURL, autogenerated, true);
	}

	/**
	 * Returns the short link entry where shortURL = &#63; and autogenerated = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param shortURL the short u r l
	 * @param autogenerated the autogenerated
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching short link entry, or <code>null</code> if a matching short link entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry fetchBySURL_A(String shortURL, boolean autogenerated,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { shortURL, autogenerated };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_SURL_A,
					finderArgs, this);
		}

		if (result instanceof ShortLinkEntry) {
			ShortLinkEntry shortLinkEntry = (ShortLinkEntry)result;

			if (!Validator.equals(shortURL, shortLinkEntry.getShortURL()) ||
					(autogenerated != shortLinkEntry.getAutogenerated())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SHORTLINKENTRY_WHERE);

			boolean bindShortURL = false;

			if (shortURL == null) {
				query.append(_FINDER_COLUMN_SURL_A_SHORTURL_1);
			}
			else if (shortURL.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SURL_A_SHORTURL_3);
			}
			else {
				bindShortURL = true;

				query.append(_FINDER_COLUMN_SURL_A_SHORTURL_2);
			}

			query.append(_FINDER_COLUMN_SURL_A_AUTOGENERATED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindShortURL) {
					qPos.add(shortURL);
				}

				qPos.add(autogenerated);

				List<ShortLinkEntry> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SURL_A,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"ShortLinkEntryPersistenceImpl.fetchBySURL_A(String, boolean, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					ShortLinkEntry shortLinkEntry = list.get(0);

					result = shortLinkEntry;

					cacheResult(shortLinkEntry);

					if ((shortLinkEntry.getShortURL() == null) ||
							!shortLinkEntry.getShortURL().equals(shortURL) ||
							(shortLinkEntry.getAutogenerated() != autogenerated)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SURL_A,
							finderArgs, shortLinkEntry);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SURL_A,
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
			return (ShortLinkEntry)result;
		}
	}

	/**
	 * Removes the short link entry where shortURL = &#63; and autogenerated = &#63; from the database.
	 *
	 * @param shortURL the short u r l
	 * @param autogenerated the autogenerated
	 * @return the short link entry that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry removeBySURL_A(String shortURL, boolean autogenerated)
		throws NoSuchEntryException, SystemException {
		ShortLinkEntry shortLinkEntry = findBySURL_A(shortURL, autogenerated);

		return remove(shortLinkEntry);
	}

	/**
	 * Returns the number of short link entries where shortURL = &#63; and autogenerated = &#63;.
	 *
	 * @param shortURL the short u r l
	 * @param autogenerated the autogenerated
	 * @return the number of matching short link entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countBySURL_A(String shortURL, boolean autogenerated)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SURL_A;

		Object[] finderArgs = new Object[] { shortURL, autogenerated };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SHORTLINKENTRY_WHERE);

			boolean bindShortURL = false;

			if (shortURL == null) {
				query.append(_FINDER_COLUMN_SURL_A_SHORTURL_1);
			}
			else if (shortURL.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SURL_A_SHORTURL_3);
			}
			else {
				bindShortURL = true;

				query.append(_FINDER_COLUMN_SURL_A_SHORTURL_2);
			}

			query.append(_FINDER_COLUMN_SURL_A_AUTOGENERATED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindShortURL) {
					qPos.add(shortURL);
				}

				qPos.add(autogenerated);

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

	private static final String _FINDER_COLUMN_SURL_A_SHORTURL_1 = "shortLinkEntry.shortURL IS NULL AND ";
	private static final String _FINDER_COLUMN_SURL_A_SHORTURL_2 = "shortLinkEntry.shortURL = ? AND ";
	private static final String _FINDER_COLUMN_SURL_A_SHORTURL_3 = "(shortLinkEntry.shortURL IS NULL OR shortLinkEntry.shortURL = '') AND ";
	private static final String _FINDER_COLUMN_SURL_A_AUTOGENERATED_2 = "shortLinkEntry.autogenerated = ?";

	public ShortLinkEntryPersistenceImpl() {
		setModelClass(ShortLinkEntry.class);
	}

	/**
	 * Caches the short link entry in the entity cache if it is enabled.
	 *
	 * @param shortLinkEntry the short link entry
	 */
	@Override
	public void cacheResult(ShortLinkEntry shortLinkEntry) {
		EntityCacheUtil.putResult(ShortLinkEntryModelImpl.ENTITY_CACHE_ENABLED,
			ShortLinkEntryImpl.class, shortLinkEntry.getPrimaryKey(),
			shortLinkEntry);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SHORTURL,
			new Object[] { shortLinkEntry.getShortURL() }, shortLinkEntry);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SURL_A,
			new Object[] {
				shortLinkEntry.getShortURL(), shortLinkEntry.getAutogenerated()
			}, shortLinkEntry);

		shortLinkEntry.resetOriginalValues();
	}

	/**
	 * Caches the short link entries in the entity cache if it is enabled.
	 *
	 * @param shortLinkEntries the short link entries
	 */
	@Override
	public void cacheResult(List<ShortLinkEntry> shortLinkEntries) {
		for (ShortLinkEntry shortLinkEntry : shortLinkEntries) {
			if (EntityCacheUtil.getResult(
						ShortLinkEntryModelImpl.ENTITY_CACHE_ENABLED,
						ShortLinkEntryImpl.class, shortLinkEntry.getPrimaryKey()) == null) {
				cacheResult(shortLinkEntry);
			}
			else {
				shortLinkEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all short link entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ShortLinkEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ShortLinkEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the short link entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ShortLinkEntry shortLinkEntry) {
		EntityCacheUtil.removeResult(ShortLinkEntryModelImpl.ENTITY_CACHE_ENABLED,
			ShortLinkEntryImpl.class, shortLinkEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(shortLinkEntry);
	}

	@Override
	public void clearCache(List<ShortLinkEntry> shortLinkEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ShortLinkEntry shortLinkEntry : shortLinkEntries) {
			EntityCacheUtil.removeResult(ShortLinkEntryModelImpl.ENTITY_CACHE_ENABLED,
				ShortLinkEntryImpl.class, shortLinkEntry.getPrimaryKey());

			clearUniqueFindersCache(shortLinkEntry);
		}
	}

	protected void cacheUniqueFindersCache(ShortLinkEntry shortLinkEntry) {
		if (shortLinkEntry.isNew()) {
			Object[] args = new Object[] { shortLinkEntry.getShortURL() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SHORTURL, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SHORTURL, args,
				shortLinkEntry);

			args = new Object[] {
					shortLinkEntry.getShortURL(),
					shortLinkEntry.getAutogenerated()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SURL_A, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SURL_A, args,
				shortLinkEntry);
		}
		else {
			ShortLinkEntryModelImpl shortLinkEntryModelImpl = (ShortLinkEntryModelImpl)shortLinkEntry;

			if ((shortLinkEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_SHORTURL.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { shortLinkEntry.getShortURL() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SHORTURL, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SHORTURL, args,
					shortLinkEntry);
			}

			if ((shortLinkEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_SURL_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						shortLinkEntry.getShortURL(),
						shortLinkEntry.getAutogenerated()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SURL_A, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SURL_A, args,
					shortLinkEntry);
			}
		}
	}

	protected void clearUniqueFindersCache(ShortLinkEntry shortLinkEntry) {
		ShortLinkEntryModelImpl shortLinkEntryModelImpl = (ShortLinkEntryModelImpl)shortLinkEntry;

		Object[] args = new Object[] { shortLinkEntry.getShortURL() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SHORTURL, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SHORTURL, args);

		if ((shortLinkEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SHORTURL.getColumnBitmask()) != 0) {
			args = new Object[] { shortLinkEntryModelImpl.getOriginalShortURL() };

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SHORTURL, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SHORTURL, args);
		}

		args = new Object[] {
				shortLinkEntry.getShortURL(), shortLinkEntry.getAutogenerated()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SURL_A, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SURL_A, args);

		if ((shortLinkEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SURL_A.getColumnBitmask()) != 0) {
			args = new Object[] {
					shortLinkEntryModelImpl.getOriginalShortURL(),
					shortLinkEntryModelImpl.getOriginalAutogenerated()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SURL_A, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SURL_A, args);
		}
	}

	/**
	 * Creates a new short link entry with the primary key. Does not add the short link entry to the database.
	 *
	 * @param shortLinkEntryId the primary key for the new short link entry
	 * @return the new short link entry
	 */
	@Override
	public ShortLinkEntry create(long shortLinkEntryId) {
		ShortLinkEntry shortLinkEntry = new ShortLinkEntryImpl();

		shortLinkEntry.setNew(true);
		shortLinkEntry.setPrimaryKey(shortLinkEntryId);

		String uuid = PortalUUIDUtil.generate();

		shortLinkEntry.setUuid(uuid);

		return shortLinkEntry;
	}

	/**
	 * Removes the short link entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param shortLinkEntryId the primary key of the short link entry
	 * @return the short link entry that was removed
	 * @throws com.liferay.shortlink.NoSuchEntryException if a short link entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry remove(long shortLinkEntryId)
		throws NoSuchEntryException, SystemException {
		return remove((Serializable)shortLinkEntryId);
	}

	/**
	 * Removes the short link entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the short link entry
	 * @return the short link entry that was removed
	 * @throws com.liferay.shortlink.NoSuchEntryException if a short link entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry remove(Serializable primaryKey)
		throws NoSuchEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ShortLinkEntry shortLinkEntry = (ShortLinkEntry)session.get(ShortLinkEntryImpl.class,
					primaryKey);

			if (shortLinkEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(shortLinkEntry);
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
	protected ShortLinkEntry removeImpl(ShortLinkEntry shortLinkEntry)
		throws SystemException {
		shortLinkEntry = toUnwrappedModel(shortLinkEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(shortLinkEntry)) {
				shortLinkEntry = (ShortLinkEntry)session.get(ShortLinkEntryImpl.class,
						shortLinkEntry.getPrimaryKeyObj());
			}

			if (shortLinkEntry != null) {
				session.delete(shortLinkEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (shortLinkEntry != null) {
			clearCache(shortLinkEntry);
		}

		return shortLinkEntry;
	}

	@Override
	public ShortLinkEntry updateImpl(
		com.liferay.shortlink.model.ShortLinkEntry shortLinkEntry)
		throws SystemException {
		shortLinkEntry = toUnwrappedModel(shortLinkEntry);

		boolean isNew = shortLinkEntry.isNew();

		ShortLinkEntryModelImpl shortLinkEntryModelImpl = (ShortLinkEntryModelImpl)shortLinkEntry;

		if (Validator.isNull(shortLinkEntry.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			shortLinkEntry.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (shortLinkEntry.isNew()) {
				session.save(shortLinkEntry);

				shortLinkEntry.setNew(false);
			}
			else {
				session.merge(shortLinkEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ShortLinkEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((shortLinkEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						shortLinkEntryModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { shortLinkEntryModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((shortLinkEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AUTOGENERATED.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						shortLinkEntryModelImpl.getOriginalAutogenerated()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AUTOGENERATED,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AUTOGENERATED,
					args);

				args = new Object[] { shortLinkEntryModelImpl.getAutogenerated() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_AUTOGENERATED,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_AUTOGENERATED,
					args);
			}

			if ((shortLinkEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OURL_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						shortLinkEntryModelImpl.getOriginalOriginalURL(),
						shortLinkEntryModelImpl.getOriginalAutogenerated()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OURL_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OURL_A,
					args);

				args = new Object[] {
						shortLinkEntryModelImpl.getOriginalURL(),
						shortLinkEntryModelImpl.getAutogenerated()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OURL_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OURL_A,
					args);
			}
		}

		EntityCacheUtil.putResult(ShortLinkEntryModelImpl.ENTITY_CACHE_ENABLED,
			ShortLinkEntryImpl.class, shortLinkEntry.getPrimaryKey(),
			shortLinkEntry);

		clearUniqueFindersCache(shortLinkEntry);
		cacheUniqueFindersCache(shortLinkEntry);

		return shortLinkEntry;
	}

	protected ShortLinkEntry toUnwrappedModel(ShortLinkEntry shortLinkEntry) {
		if (shortLinkEntry instanceof ShortLinkEntryImpl) {
			return shortLinkEntry;
		}

		ShortLinkEntryImpl shortLinkEntryImpl = new ShortLinkEntryImpl();

		shortLinkEntryImpl.setNew(shortLinkEntry.isNew());
		shortLinkEntryImpl.setPrimaryKey(shortLinkEntry.getPrimaryKey());

		shortLinkEntryImpl.setUuid(shortLinkEntry.getUuid());
		shortLinkEntryImpl.setShortLinkEntryId(shortLinkEntry.getShortLinkEntryId());
		shortLinkEntryImpl.setCreateDate(shortLinkEntry.getCreateDate());
		shortLinkEntryImpl.setModifiedDate(shortLinkEntry.getModifiedDate());
		shortLinkEntryImpl.setOriginalURL(shortLinkEntry.getOriginalURL());
		shortLinkEntryImpl.setShortURL(shortLinkEntry.getShortURL());
		shortLinkEntryImpl.setAutogenerated(shortLinkEntry.isAutogenerated());
		shortLinkEntryImpl.setActive(shortLinkEntry.isActive());

		return shortLinkEntryImpl;
	}

	/**
	 * Returns the short link entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the short link entry
	 * @return the short link entry
	 * @throws com.liferay.shortlink.NoSuchEntryException if a short link entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEntryException, SystemException {
		ShortLinkEntry shortLinkEntry = fetchByPrimaryKey(primaryKey);

		if (shortLinkEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return shortLinkEntry;
	}

	/**
	 * Returns the short link entry with the primary key or throws a {@link com.liferay.shortlink.NoSuchEntryException} if it could not be found.
	 *
	 * @param shortLinkEntryId the primary key of the short link entry
	 * @return the short link entry
	 * @throws com.liferay.shortlink.NoSuchEntryException if a short link entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry findByPrimaryKey(long shortLinkEntryId)
		throws NoSuchEntryException, SystemException {
		return findByPrimaryKey((Serializable)shortLinkEntryId);
	}

	/**
	 * Returns the short link entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the short link entry
	 * @return the short link entry, or <code>null</code> if a short link entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		ShortLinkEntry shortLinkEntry = (ShortLinkEntry)EntityCacheUtil.getResult(ShortLinkEntryModelImpl.ENTITY_CACHE_ENABLED,
				ShortLinkEntryImpl.class, primaryKey);

		if (shortLinkEntry == _nullShortLinkEntry) {
			return null;
		}

		if (shortLinkEntry == null) {
			Session session = null;

			try {
				session = openSession();

				shortLinkEntry = (ShortLinkEntry)session.get(ShortLinkEntryImpl.class,
						primaryKey);

				if (shortLinkEntry != null) {
					cacheResult(shortLinkEntry);
				}
				else {
					EntityCacheUtil.putResult(ShortLinkEntryModelImpl.ENTITY_CACHE_ENABLED,
						ShortLinkEntryImpl.class, primaryKey,
						_nullShortLinkEntry);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ShortLinkEntryModelImpl.ENTITY_CACHE_ENABLED,
					ShortLinkEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return shortLinkEntry;
	}

	/**
	 * Returns the short link entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param shortLinkEntryId the primary key of the short link entry
	 * @return the short link entry, or <code>null</code> if a short link entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShortLinkEntry fetchByPrimaryKey(long shortLinkEntryId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)shortLinkEntryId);
	}

	/**
	 * Returns all the short link entries.
	 *
	 * @return the short link entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ShortLinkEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the short link entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.shortlink.model.impl.ShortLinkEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of short link entries
	 * @param end the upper bound of the range of short link entries (not inclusive)
	 * @return the range of short link entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ShortLinkEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the short link entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.shortlink.model.impl.ShortLinkEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of short link entries
	 * @param end the upper bound of the range of short link entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of short link entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ShortLinkEntry> findAll(int start, int end,
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

		List<ShortLinkEntry> list = (List<ShortLinkEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SHORTLINKENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SHORTLINKENTRY;

				if (pagination) {
					sql = sql.concat(ShortLinkEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ShortLinkEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<ShortLinkEntry>(list);
				}
				else {
					list = (List<ShortLinkEntry>)QueryUtil.list(q,
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
	 * Removes all the short link entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (ShortLinkEntry shortLinkEntry : findAll()) {
			remove(shortLinkEntry);
		}
	}

	/**
	 * Returns the number of short link entries.
	 *
	 * @return the number of short link entries
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

				Query q = session.createQuery(_SQL_COUNT_SHORTLINKENTRY);

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
	 * Initializes the short link entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.shortlink.model.ShortLinkEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ShortLinkEntry>> listenersList = new ArrayList<ModelListener<ShortLinkEntry>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ShortLinkEntry>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(ShortLinkEntryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SHORTLINKENTRY = "SELECT shortLinkEntry FROM ShortLinkEntry shortLinkEntry";
	private static final String _SQL_SELECT_SHORTLINKENTRY_WHERE = "SELECT shortLinkEntry FROM ShortLinkEntry shortLinkEntry WHERE ";
	private static final String _SQL_COUNT_SHORTLINKENTRY = "SELECT COUNT(shortLinkEntry) FROM ShortLinkEntry shortLinkEntry";
	private static final String _SQL_COUNT_SHORTLINKENTRY_WHERE = "SELECT COUNT(shortLinkEntry) FROM ShortLinkEntry shortLinkEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "shortLinkEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ShortLinkEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ShortLinkEntry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ShortLinkEntryPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "active"
			});
	private static ShortLinkEntry _nullShortLinkEntry = new ShortLinkEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ShortLinkEntry> toCacheModel() {
				return _nullShortLinkEntryCacheModel;
			}
		};

	private static CacheModel<ShortLinkEntry> _nullShortLinkEntryCacheModel = new CacheModel<ShortLinkEntry>() {
			@Override
			public ShortLinkEntry toEntityModel() {
				return _nullShortLinkEntry;
			}
		};
}