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

import com.liferay.testblob.NoSuchEntryException;
import com.liferay.testblob.model.TestBlobEntry;
import com.liferay.testblob.model.impl.TestBlobEntryImpl;
import com.liferay.testblob.model.impl.TestBlobEntryModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the test blob entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TestBlobEntryPersistence
 * @see TestBlobEntryUtil
 * @generated
 */
public class TestBlobEntryPersistenceImpl extends BasePersistenceImpl<TestBlobEntry>
	implements TestBlobEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TestBlobEntryUtil} to access the test blob entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TestBlobEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TestBlobEntryModelImpl.ENTITY_CACHE_ENABLED,
			TestBlobEntryModelImpl.FINDER_CACHE_ENABLED,
			TestBlobEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TestBlobEntryModelImpl.ENTITY_CACHE_ENABLED,
			TestBlobEntryModelImpl.FINDER_CACHE_ENABLED,
			TestBlobEntryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TestBlobEntryModelImpl.ENTITY_CACHE_ENABLED,
			TestBlobEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(TestBlobEntryModelImpl.ENTITY_CACHE_ENABLED,
			TestBlobEntryModelImpl.FINDER_CACHE_ENABLED,
			TestBlobEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(TestBlobEntryModelImpl.ENTITY_CACHE_ENABLED,
			TestBlobEntryModelImpl.FINDER_CACHE_ENABLED,
			TestBlobEntryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUuid", new String[] { String.class.getName() },
			TestBlobEntryModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(TestBlobEntryModelImpl.ENTITY_CACHE_ENABLED,
			TestBlobEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the test blob entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching test blob entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<TestBlobEntry> findByUuid(String uuid)
		throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the test blob entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testblob.model.impl.TestBlobEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of test blob entries
	 * @param end the upper bound of the range of test blob entries (not inclusive)
	 * @return the range of matching test blob entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<TestBlobEntry> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the test blob entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testblob.model.impl.TestBlobEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of test blob entries
	 * @param end the upper bound of the range of test blob entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching test blob entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<TestBlobEntry> findByUuid(String uuid, int start, int end,
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

		List<TestBlobEntry> list = (List<TestBlobEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TestBlobEntry testBlobEntry : list) {
				if (!Validator.equals(uuid, testBlobEntry.getUuid())) {
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

			query.append(_SQL_SELECT_TESTBLOBENTRY_WHERE);

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
				query.append(TestBlobEntryModelImpl.ORDER_BY_JPQL);
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
					list = (List<TestBlobEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<TestBlobEntry>(list);
				}
				else {
					list = (List<TestBlobEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first test blob entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching test blob entry
	 * @throws com.liferay.testblob.NoSuchEntryException if a matching test blob entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TestBlobEntry findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		TestBlobEntry testBlobEntry = fetchByUuid_First(uuid, orderByComparator);

		if (testBlobEntry != null) {
			return testBlobEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the first test blob entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching test blob entry, or <code>null</code> if a matching test blob entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TestBlobEntry fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<TestBlobEntry> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last test blob entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching test blob entry
	 * @throws com.liferay.testblob.NoSuchEntryException if a matching test blob entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TestBlobEntry findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		TestBlobEntry testBlobEntry = fetchByUuid_Last(uuid, orderByComparator);

		if (testBlobEntry != null) {
			return testBlobEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntryException(msg.toString());
	}

	/**
	 * Returns the last test blob entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching test blob entry, or <code>null</code> if a matching test blob entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TestBlobEntry fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<TestBlobEntry> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the test blob entries before and after the current test blob entry in the ordered set where uuid = &#63;.
	 *
	 * @param testBlobEntryId the primary key of the current test blob entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next test blob entry
	 * @throws com.liferay.testblob.NoSuchEntryException if a test blob entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TestBlobEntry[] findByUuid_PrevAndNext(long testBlobEntryId,
		String uuid, OrderByComparator orderByComparator)
		throws NoSuchEntryException, SystemException {
		TestBlobEntry testBlobEntry = findByPrimaryKey(testBlobEntryId);

		Session session = null;

		try {
			session = openSession();

			TestBlobEntry[] array = new TestBlobEntryImpl[3];

			array[0] = getByUuid_PrevAndNext(session, testBlobEntry, uuid,
					orderByComparator, true);

			array[1] = testBlobEntry;

			array[2] = getByUuid_PrevAndNext(session, testBlobEntry, uuid,
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

	protected TestBlobEntry getByUuid_PrevAndNext(Session session,
		TestBlobEntry testBlobEntry, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TESTBLOBENTRY_WHERE);

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
			query.append(TestBlobEntryModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(testBlobEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TestBlobEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the test blob entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid(String uuid) throws SystemException {
		for (TestBlobEntry testBlobEntry : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(testBlobEntry);
		}
	}

	/**
	 * Returns the number of test blob entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching test blob entries
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

			query.append(_SQL_COUNT_TESTBLOBENTRY_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "testBlobEntry.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "testBlobEntry.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(testBlobEntry.uuid IS NULL OR testBlobEntry.uuid = '')";

	public TestBlobEntryPersistenceImpl() {
		setModelClass(TestBlobEntry.class);
	}

	/**
	 * Caches the test blob entry in the entity cache if it is enabled.
	 *
	 * @param testBlobEntry the test blob entry
	 */
	@Override
	public void cacheResult(TestBlobEntry testBlobEntry) {
		EntityCacheUtil.putResult(TestBlobEntryModelImpl.ENTITY_CACHE_ENABLED,
			TestBlobEntryImpl.class, testBlobEntry.getPrimaryKey(),
			testBlobEntry);

		testBlobEntry.resetOriginalValues();
	}

	/**
	 * Caches the test blob entries in the entity cache if it is enabled.
	 *
	 * @param testBlobEntries the test blob entries
	 */
	@Override
	public void cacheResult(List<TestBlobEntry> testBlobEntries) {
		for (TestBlobEntry testBlobEntry : testBlobEntries) {
			if (EntityCacheUtil.getResult(
						TestBlobEntryModelImpl.ENTITY_CACHE_ENABLED,
						TestBlobEntryImpl.class, testBlobEntry.getPrimaryKey()) == null) {
				cacheResult(testBlobEntry);
			}
			else {
				testBlobEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all test blob entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(TestBlobEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(TestBlobEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the test blob entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TestBlobEntry testBlobEntry) {
		EntityCacheUtil.removeResult(TestBlobEntryModelImpl.ENTITY_CACHE_ENABLED,
			TestBlobEntryImpl.class, testBlobEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<TestBlobEntry> testBlobEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TestBlobEntry testBlobEntry : testBlobEntries) {
			EntityCacheUtil.removeResult(TestBlobEntryModelImpl.ENTITY_CACHE_ENABLED,
				TestBlobEntryImpl.class, testBlobEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new test blob entry with the primary key. Does not add the test blob entry to the database.
	 *
	 * @param testBlobEntryId the primary key for the new test blob entry
	 * @return the new test blob entry
	 */
	@Override
	public TestBlobEntry create(long testBlobEntryId) {
		TestBlobEntry testBlobEntry = new TestBlobEntryImpl();

		testBlobEntry.setNew(true);
		testBlobEntry.setPrimaryKey(testBlobEntryId);

		String uuid = PortalUUIDUtil.generate();

		testBlobEntry.setUuid(uuid);

		return testBlobEntry;
	}

	/**
	 * Removes the test blob entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testBlobEntryId the primary key of the test blob entry
	 * @return the test blob entry that was removed
	 * @throws com.liferay.testblob.NoSuchEntryException if a test blob entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TestBlobEntry remove(long testBlobEntryId)
		throws NoSuchEntryException, SystemException {
		return remove((Serializable)testBlobEntryId);
	}

	/**
	 * Removes the test blob entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the test blob entry
	 * @return the test blob entry that was removed
	 * @throws com.liferay.testblob.NoSuchEntryException if a test blob entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TestBlobEntry remove(Serializable primaryKey)
		throws NoSuchEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			TestBlobEntry testBlobEntry = (TestBlobEntry)session.get(TestBlobEntryImpl.class,
					primaryKey);

			if (testBlobEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(testBlobEntry);
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
	protected TestBlobEntry removeImpl(TestBlobEntry testBlobEntry)
		throws SystemException {
		testBlobEntry = toUnwrappedModel(testBlobEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(testBlobEntry)) {
				testBlobEntry = (TestBlobEntry)session.get(TestBlobEntryImpl.class,
						testBlobEntry.getPrimaryKeyObj());
			}

			if (testBlobEntry != null) {
				session.delete(testBlobEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (testBlobEntry != null) {
			clearCache(testBlobEntry);
		}

		return testBlobEntry;
	}

	@Override
	public TestBlobEntry updateImpl(
		com.liferay.testblob.model.TestBlobEntry testBlobEntry)
		throws SystemException {
		testBlobEntry = toUnwrappedModel(testBlobEntry);

		boolean isNew = testBlobEntry.isNew();

		TestBlobEntryModelImpl testBlobEntryModelImpl = (TestBlobEntryModelImpl)testBlobEntry;

		if (Validator.isNull(testBlobEntry.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			testBlobEntry.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (testBlobEntry.isNew()) {
				session.save(testBlobEntry);

				testBlobEntry.setNew(false);
			}
			else {
				session.evict(testBlobEntry);
				session.saveOrUpdate(testBlobEntry);
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

		if (isNew || !TestBlobEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((testBlobEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						testBlobEntryModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { testBlobEntryModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}
		}

		EntityCacheUtil.putResult(TestBlobEntryModelImpl.ENTITY_CACHE_ENABLED,
			TestBlobEntryImpl.class, testBlobEntry.getPrimaryKey(),
			testBlobEntry);

		testBlobEntry.resetOriginalValues();

		return testBlobEntry;
	}

	protected TestBlobEntry toUnwrappedModel(TestBlobEntry testBlobEntry) {
		if (testBlobEntry instanceof TestBlobEntryImpl) {
			return testBlobEntry;
		}

		TestBlobEntryImpl testBlobEntryImpl = new TestBlobEntryImpl();

		testBlobEntryImpl.setNew(testBlobEntry.isNew());
		testBlobEntryImpl.setPrimaryKey(testBlobEntry.getPrimaryKey());

		testBlobEntryImpl.setUuid(testBlobEntry.getUuid());
		testBlobEntryImpl.setTestBlobEntryId(testBlobEntry.getTestBlobEntryId());
		testBlobEntryImpl.setBlobField(testBlobEntry.getBlobField());

		return testBlobEntryImpl;
	}

	/**
	 * Returns the test blob entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the test blob entry
	 * @return the test blob entry
	 * @throws com.liferay.testblob.NoSuchEntryException if a test blob entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TestBlobEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEntryException, SystemException {
		TestBlobEntry testBlobEntry = fetchByPrimaryKey(primaryKey);

		if (testBlobEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return testBlobEntry;
	}

	/**
	 * Returns the test blob entry with the primary key or throws a {@link com.liferay.testblob.NoSuchEntryException} if it could not be found.
	 *
	 * @param testBlobEntryId the primary key of the test blob entry
	 * @return the test blob entry
	 * @throws com.liferay.testblob.NoSuchEntryException if a test blob entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TestBlobEntry findByPrimaryKey(long testBlobEntryId)
		throws NoSuchEntryException, SystemException {
		return findByPrimaryKey((Serializable)testBlobEntryId);
	}

	/**
	 * Returns the test blob entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the test blob entry
	 * @return the test blob entry, or <code>null</code> if a test blob entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TestBlobEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		TestBlobEntry testBlobEntry = (TestBlobEntry)EntityCacheUtil.getResult(TestBlobEntryModelImpl.ENTITY_CACHE_ENABLED,
				TestBlobEntryImpl.class, primaryKey);

		if (testBlobEntry == _nullTestBlobEntry) {
			return null;
		}

		if (testBlobEntry == null) {
			Session session = null;

			try {
				session = openSession();

				testBlobEntry = (TestBlobEntry)session.get(TestBlobEntryImpl.class,
						primaryKey);

				if (testBlobEntry != null) {
					cacheResult(testBlobEntry);
				}
				else {
					EntityCacheUtil.putResult(TestBlobEntryModelImpl.ENTITY_CACHE_ENABLED,
						TestBlobEntryImpl.class, primaryKey, _nullTestBlobEntry);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(TestBlobEntryModelImpl.ENTITY_CACHE_ENABLED,
					TestBlobEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return testBlobEntry;
	}

	/**
	 * Returns the test blob entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param testBlobEntryId the primary key of the test blob entry
	 * @return the test blob entry, or <code>null</code> if a test blob entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TestBlobEntry fetchByPrimaryKey(long testBlobEntryId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)testBlobEntryId);
	}

	/**
	 * Returns all the test blob entries.
	 *
	 * @return the test blob entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<TestBlobEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the test blob entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testblob.model.impl.TestBlobEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of test blob entries
	 * @param end the upper bound of the range of test blob entries (not inclusive)
	 * @return the range of test blob entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<TestBlobEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the test blob entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testblob.model.impl.TestBlobEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of test blob entries
	 * @param end the upper bound of the range of test blob entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of test blob entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<TestBlobEntry> findAll(int start, int end,
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

		List<TestBlobEntry> list = (List<TestBlobEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TESTBLOBENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TESTBLOBENTRY;

				if (pagination) {
					sql = sql.concat(TestBlobEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<TestBlobEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<TestBlobEntry>(list);
				}
				else {
					list = (List<TestBlobEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the test blob entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (TestBlobEntry testBlobEntry : findAll()) {
			remove(testBlobEntry);
		}
	}

	/**
	 * Returns the number of test blob entries.
	 *
	 * @return the number of test blob entries
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

				Query q = session.createQuery(_SQL_COUNT_TESTBLOBENTRY);

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
	 * Initializes the test blob entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.testblob.model.TestBlobEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<TestBlobEntry>> listenersList = new ArrayList<ModelListener<TestBlobEntry>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<TestBlobEntry>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(TestBlobEntryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_TESTBLOBENTRY = "SELECT testBlobEntry FROM TestBlobEntry testBlobEntry";
	private static final String _SQL_SELECT_TESTBLOBENTRY_WHERE = "SELECT testBlobEntry FROM TestBlobEntry testBlobEntry WHERE ";
	private static final String _SQL_COUNT_TESTBLOBENTRY = "SELECT COUNT(testBlobEntry) FROM TestBlobEntry testBlobEntry";
	private static final String _SQL_COUNT_TESTBLOBENTRY_WHERE = "SELECT COUNT(testBlobEntry) FROM TestBlobEntry testBlobEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "testBlobEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TestBlobEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TestBlobEntry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(TestBlobEntryPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static TestBlobEntry _nullTestBlobEntry = new TestBlobEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<TestBlobEntry> toCacheModel() {
				return _nullTestBlobEntryCacheModel;
			}
		};

	private static CacheModel<TestBlobEntry> _nullTestBlobEntryCacheModel = new CacheModel<TestBlobEntry>() {
			@Override
			public TestBlobEntry toEntityModel() {
				return _nullTestBlobEntry;
			}
		};
}