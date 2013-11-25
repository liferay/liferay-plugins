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

package com.liferay.testblob.service.persistence;

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

import com.liferay.testblob.NoSuchBlobEntryException;
import com.liferay.testblob.model.BlobEntry;
import com.liferay.testblob.model.impl.BlobEntryImpl;
import com.liferay.testblob.model.impl.BlobEntryModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the blob entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BlobEntryPersistence
 * @see BlobEntryUtil
 * @generated
 */
public class BlobEntryPersistenceImpl extends BasePersistenceImpl<BlobEntry>
	implements BlobEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link BlobEntryUtil} to access the blob entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = BlobEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BlobEntryModelImpl.ENTITY_CACHE_ENABLED,
			BlobEntryModelImpl.FINDER_CACHE_ENABLED, BlobEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BlobEntryModelImpl.ENTITY_CACHE_ENABLED,
			BlobEntryModelImpl.FINDER_CACHE_ENABLED, BlobEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BlobEntryModelImpl.ENTITY_CACHE_ENABLED,
			BlobEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(BlobEntryModelImpl.ENTITY_CACHE_ENABLED,
			BlobEntryModelImpl.FINDER_CACHE_ENABLED, BlobEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(BlobEntryModelImpl.ENTITY_CACHE_ENABLED,
			BlobEntryModelImpl.FINDER_CACHE_ENABLED, BlobEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			BlobEntryModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(BlobEntryModelImpl.ENTITY_CACHE_ENABLED,
			BlobEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the blob entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching blob entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BlobEntry> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the blob entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testblob.model.impl.BlobEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of blob entries
	 * @param end the upper bound of the range of blob entries (not inclusive)
	 * @return the range of matching blob entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BlobEntry> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the blob entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testblob.model.impl.BlobEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of blob entries
	 * @param end the upper bound of the range of blob entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching blob entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BlobEntry> findByUuid(String uuid, int start, int end,
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

		List<BlobEntry> list = (List<BlobEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BlobEntry blobEntry : list) {
				if (!Validator.equals(uuid, blobEntry.getUuid())) {
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

			query.append(_SQL_SELECT_BLOBENTRY_WHERE);

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
				query.append(BlobEntryModelImpl.ORDER_BY_JPQL);
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
					list = (List<BlobEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<BlobEntry>(list);
				}
				else {
					list = (List<BlobEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first blob entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching blob entry
	 * @throws com.liferay.testblob.NoSuchBlobEntryException if a matching blob entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BlobEntry findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchBlobEntryException, SystemException {
		BlobEntry blobEntry = fetchByUuid_First(uuid, orderByComparator);

		if (blobEntry != null) {
			return blobEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBlobEntryException(msg.toString());
	}

	/**
	 * Returns the first blob entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching blob entry, or <code>null</code> if a matching blob entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BlobEntry fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<BlobEntry> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last blob entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching blob entry
	 * @throws com.liferay.testblob.NoSuchBlobEntryException if a matching blob entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BlobEntry findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchBlobEntryException, SystemException {
		BlobEntry blobEntry = fetchByUuid_Last(uuid, orderByComparator);

		if (blobEntry != null) {
			return blobEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBlobEntryException(msg.toString());
	}

	/**
	 * Returns the last blob entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching blob entry, or <code>null</code> if a matching blob entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BlobEntry fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<BlobEntry> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the blob entries before and after the current blob entry in the ordered set where uuid = &#63;.
	 *
	 * @param testBlobEntryId the primary key of the current blob entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next blob entry
	 * @throws com.liferay.testblob.NoSuchBlobEntryException if a blob entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BlobEntry[] findByUuid_PrevAndNext(long testBlobEntryId,
		String uuid, OrderByComparator orderByComparator)
		throws NoSuchBlobEntryException, SystemException {
		BlobEntry blobEntry = findByPrimaryKey(testBlobEntryId);

		Session session = null;

		try {
			session = openSession();

			BlobEntry[] array = new BlobEntryImpl[3];

			array[0] = getByUuid_PrevAndNext(session, blobEntry, uuid,
					orderByComparator, true);

			array[1] = blobEntry;

			array[2] = getByUuid_PrevAndNext(session, blobEntry, uuid,
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

	protected BlobEntry getByUuid_PrevAndNext(Session session,
		BlobEntry blobEntry, String uuid, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BLOBENTRY_WHERE);

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
			query.append(BlobEntryModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(blobEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BlobEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the blob entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid(String uuid) throws SystemException {
		for (BlobEntry blobEntry : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(blobEntry);
		}
	}

	/**
	 * Returns the number of blob entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching blob entries
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

			query.append(_SQL_COUNT_BLOBENTRY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "blobEntry.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "blobEntry.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(blobEntry.uuid IS NULL OR blobEntry.uuid = '')";

	public BlobEntryPersistenceImpl() {
		setModelClass(BlobEntry.class);
	}

	/**
	 * Caches the blob entry in the entity cache if it is enabled.
	 *
	 * @param blobEntry the blob entry
	 */
	@Override
	public void cacheResult(BlobEntry blobEntry) {
		EntityCacheUtil.putResult(BlobEntryModelImpl.ENTITY_CACHE_ENABLED,
			BlobEntryImpl.class, blobEntry.getPrimaryKey(), blobEntry);

		blobEntry.resetOriginalValues();
	}

	/**
	 * Caches the blob entries in the entity cache if it is enabled.
	 *
	 * @param blobEntries the blob entries
	 */
	@Override
	public void cacheResult(List<BlobEntry> blobEntries) {
		for (BlobEntry blobEntry : blobEntries) {
			if (EntityCacheUtil.getResult(
						BlobEntryModelImpl.ENTITY_CACHE_ENABLED,
						BlobEntryImpl.class, blobEntry.getPrimaryKey()) == null) {
				cacheResult(blobEntry);
			}
			else {
				blobEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all blob entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(BlobEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(BlobEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the blob entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(BlobEntry blobEntry) {
		EntityCacheUtil.removeResult(BlobEntryModelImpl.ENTITY_CACHE_ENABLED,
			BlobEntryImpl.class, blobEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<BlobEntry> blobEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (BlobEntry blobEntry : blobEntries) {
			EntityCacheUtil.removeResult(BlobEntryModelImpl.ENTITY_CACHE_ENABLED,
				BlobEntryImpl.class, blobEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new blob entry with the primary key. Does not add the blob entry to the database.
	 *
	 * @param testBlobEntryId the primary key for the new blob entry
	 * @return the new blob entry
	 */
	@Override
	public BlobEntry create(long testBlobEntryId) {
		BlobEntry blobEntry = new BlobEntryImpl();

		blobEntry.setNew(true);
		blobEntry.setPrimaryKey(testBlobEntryId);

		String uuid = PortalUUIDUtil.generate();

		blobEntry.setUuid(uuid);

		return blobEntry;
	}

	/**
	 * Removes the blob entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testBlobEntryId the primary key of the blob entry
	 * @return the blob entry that was removed
	 * @throws com.liferay.testblob.NoSuchBlobEntryException if a blob entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BlobEntry remove(long testBlobEntryId)
		throws NoSuchBlobEntryException, SystemException {
		return remove((Serializable)testBlobEntryId);
	}

	/**
	 * Removes the blob entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the blob entry
	 * @return the blob entry that was removed
	 * @throws com.liferay.testblob.NoSuchBlobEntryException if a blob entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BlobEntry remove(Serializable primaryKey)
		throws NoSuchBlobEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			BlobEntry blobEntry = (BlobEntry)session.get(BlobEntryImpl.class,
					primaryKey);

			if (blobEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBlobEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(blobEntry);
		}
		catch (NoSuchBlobEntryException nsee) {
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
	protected BlobEntry removeImpl(BlobEntry blobEntry)
		throws SystemException {
		blobEntry = toUnwrappedModel(blobEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(blobEntry)) {
				blobEntry = (BlobEntry)session.get(BlobEntryImpl.class,
						blobEntry.getPrimaryKeyObj());
			}

			if (blobEntry != null) {
				session.delete(blobEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (blobEntry != null) {
			clearCache(blobEntry);
		}

		return blobEntry;
	}

	@Override
	public BlobEntry updateImpl(com.liferay.testblob.model.BlobEntry blobEntry)
		throws SystemException {
		blobEntry = toUnwrappedModel(blobEntry);

		boolean isNew = blobEntry.isNew();

		BlobEntryModelImpl blobEntryModelImpl = (BlobEntryModelImpl)blobEntry;

		if (Validator.isNull(blobEntry.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			blobEntry.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (blobEntry.isNew()) {
				session.save(blobEntry);

				blobEntry.setNew(false);
			}
			else {
				session.evict(blobEntry);
				session.saveOrUpdate(blobEntry);
			}

			session.flush();
			session.clear();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !BlobEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((blobEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						blobEntryModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { blobEntryModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}
		}

		EntityCacheUtil.putResult(BlobEntryModelImpl.ENTITY_CACHE_ENABLED,
			BlobEntryImpl.class, blobEntry.getPrimaryKey(), blobEntry);

		blobEntry.resetOriginalValues();

		return blobEntry;
	}

	protected BlobEntry toUnwrappedModel(BlobEntry blobEntry) {
		if (blobEntry instanceof BlobEntryImpl) {
			return blobEntry;
		}

		BlobEntryImpl blobEntryImpl = new BlobEntryImpl();

		blobEntryImpl.setNew(blobEntry.isNew());
		blobEntryImpl.setPrimaryKey(blobEntry.getPrimaryKey());

		blobEntryImpl.setUuid(blobEntry.getUuid());
		blobEntryImpl.setTestBlobEntryId(blobEntry.getTestBlobEntryId());
		blobEntryImpl.setBlobField(blobEntry.getBlobField());

		return blobEntryImpl;
	}

	/**
	 * Returns the blob entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the blob entry
	 * @return the blob entry
	 * @throws com.liferay.testblob.NoSuchBlobEntryException if a blob entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BlobEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBlobEntryException, SystemException {
		BlobEntry blobEntry = fetchByPrimaryKey(primaryKey);

		if (blobEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBlobEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return blobEntry;
	}

	/**
	 * Returns the blob entry with the primary key or throws a {@link com.liferay.testblob.NoSuchBlobEntryException} if it could not be found.
	 *
	 * @param testBlobEntryId the primary key of the blob entry
	 * @return the blob entry
	 * @throws com.liferay.testblob.NoSuchBlobEntryException if a blob entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BlobEntry findByPrimaryKey(long testBlobEntryId)
		throws NoSuchBlobEntryException, SystemException {
		return findByPrimaryKey((Serializable)testBlobEntryId);
	}

	/**
	 * Returns the blob entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the blob entry
	 * @return the blob entry, or <code>null</code> if a blob entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BlobEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		BlobEntry blobEntry = (BlobEntry)EntityCacheUtil.getResult(BlobEntryModelImpl.ENTITY_CACHE_ENABLED,
				BlobEntryImpl.class, primaryKey);

		if (blobEntry == _nullBlobEntry) {
			return null;
		}

		if (blobEntry == null) {
			Session session = null;

			try {
				session = openSession();

				blobEntry = (BlobEntry)session.get(BlobEntryImpl.class,
						primaryKey);

				if (blobEntry != null) {
					cacheResult(blobEntry);
				}
				else {
					EntityCacheUtil.putResult(BlobEntryModelImpl.ENTITY_CACHE_ENABLED,
						BlobEntryImpl.class, primaryKey, _nullBlobEntry);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(BlobEntryModelImpl.ENTITY_CACHE_ENABLED,
					BlobEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return blobEntry;
	}

	/**
	 * Returns the blob entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testBlobEntryId the primary key of the blob entry
	 * @return the blob entry, or <code>null</code> if a blob entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BlobEntry fetchByPrimaryKey(long testBlobEntryId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)testBlobEntryId);
	}

	/**
	 * Returns all the blob entries.
	 *
	 * @return the blob entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BlobEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the blob entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testblob.model.impl.BlobEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of blob entries
	 * @param end the upper bound of the range of blob entries (not inclusive)
	 * @return the range of blob entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BlobEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the blob entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testblob.model.impl.BlobEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of blob entries
	 * @param end the upper bound of the range of blob entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of blob entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BlobEntry> findAll(int start, int end,
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

		List<BlobEntry> list = (List<BlobEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_BLOBENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_BLOBENTRY;

				if (pagination) {
					sql = sql.concat(BlobEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<BlobEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<BlobEntry>(list);
				}
				else {
					list = (List<BlobEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the blob entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (BlobEntry blobEntry : findAll()) {
			remove(blobEntry);
		}
	}

	/**
	 * Returns the number of blob entries.
	 *
	 * @return the number of blob entries
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

				Query q = session.createQuery(_SQL_COUNT_BLOBENTRY);

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
	 * Initializes the blob entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.testblob.model.BlobEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<BlobEntry>> listenersList = new ArrayList<ModelListener<BlobEntry>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<BlobEntry>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(BlobEntryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_BLOBENTRY = "SELECT blobEntry FROM BlobEntry blobEntry";
	private static final String _SQL_SELECT_BLOBENTRY_WHERE = "SELECT blobEntry FROM BlobEntry blobEntry WHERE ";
	private static final String _SQL_COUNT_BLOBENTRY = "SELECT COUNT(blobEntry) FROM BlobEntry blobEntry";
	private static final String _SQL_COUNT_BLOBENTRY_WHERE = "SELECT COUNT(blobEntry) FROM BlobEntry blobEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "blobEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No BlobEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No BlobEntry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(BlobEntryPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static BlobEntry _nullBlobEntry = new BlobEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<BlobEntry> toCacheModel() {
				return _nullBlobEntryCacheModel;
			}
		};

	private static CacheModel<BlobEntry> _nullBlobEntryCacheModel = new CacheModel<BlobEntry>() {
			@Override
			public BlobEntry toEntityModel() {
				return _nullBlobEntry;
			}
		};
}