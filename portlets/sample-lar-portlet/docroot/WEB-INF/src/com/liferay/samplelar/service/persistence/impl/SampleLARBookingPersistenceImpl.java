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

package com.liferay.samplelar.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCache;
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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.service.persistence.CompanyProvider;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.samplelar.NoSuchBookingException;
import com.liferay.samplelar.model.SampleLARBooking;
import com.liferay.samplelar.model.impl.SampleLARBookingImpl;
import com.liferay.samplelar.model.impl.SampleLARBookingModelImpl;
import com.liferay.samplelar.service.persistence.SampleLARBookingPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the sample l a r booking service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Mate Thurzo
 * @see SampleLARBookingPersistence
 * @see com.liferay.samplelar.service.persistence.SampleLARBookingUtil
 * @generated
 */
@ProviderType
public class SampleLARBookingPersistenceImpl extends BasePersistenceImpl<SampleLARBooking>
	implements SampleLARBookingPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SampleLARBookingUtil} to access the sample l a r booking persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SampleLARBookingImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SampleLARBookingModelImpl.ENTITY_CACHE_ENABLED,
			SampleLARBookingModelImpl.FINDER_CACHE_ENABLED,
			SampleLARBookingImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SampleLARBookingModelImpl.ENTITY_CACHE_ENABLED,
			SampleLARBookingModelImpl.FINDER_CACHE_ENABLED,
			SampleLARBookingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SampleLARBookingModelImpl.ENTITY_CACHE_ENABLED,
			SampleLARBookingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(SampleLARBookingModelImpl.ENTITY_CACHE_ENABLED,
			SampleLARBookingModelImpl.FINDER_CACHE_ENABLED,
			SampleLARBookingImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(SampleLARBookingModelImpl.ENTITY_CACHE_ENABLED,
			SampleLARBookingModelImpl.FINDER_CACHE_ENABLED,
			SampleLARBookingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			SampleLARBookingModelImpl.UUID_COLUMN_BITMASK |
			SampleLARBookingModelImpl.BOOKINGNUMBER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(SampleLARBookingModelImpl.ENTITY_CACHE_ENABLED,
			SampleLARBookingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the sample l a r bookings where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching sample l a r bookings
	 */
	@Override
	public List<SampleLARBooking> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sample l a r bookings where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SampleLARBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of sample l a r bookings
	 * @param end the upper bound of the range of sample l a r bookings (not inclusive)
	 * @return the range of matching sample l a r bookings
	 */
	@Override
	public List<SampleLARBooking> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sample l a r bookings where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SampleLARBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of sample l a r bookings
	 * @param end the upper bound of the range of sample l a r bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sample l a r bookings
	 */
	@Override
	public List<SampleLARBooking> findByUuid(String uuid, int start, int end,
		OrderByComparator<SampleLARBooking> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sample l a r bookings where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SampleLARBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of sample l a r bookings
	 * @param end the upper bound of the range of sample l a r bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sample l a r bookings
	 */
	@Override
	public List<SampleLARBooking> findByUuid(String uuid, int start, int end,
		OrderByComparator<SampleLARBooking> orderByComparator,
		boolean retrieveFromCache) {
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

		List<SampleLARBooking> list = null;

		if (retrieveFromCache) {
			list = (List<SampleLARBooking>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SampleLARBooking sampleLARBooking : list) {
					if (!Validator.equals(uuid, sampleLARBooking.getUuid())) {
						list = null;

						break;
					}
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

			query.append(_SQL_SELECT_SAMPLELARBOOKING_WHERE);

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
				query.append(SampleLARBookingModelImpl.ORDER_BY_JPQL);
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
					list = (List<SampleLARBooking>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SampleLARBooking>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first sample l a r booking in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sample l a r booking
	 * @throws NoSuchBookingException if a matching sample l a r booking could not be found
	 */
	@Override
	public SampleLARBooking findByUuid_First(String uuid,
		OrderByComparator<SampleLARBooking> orderByComparator)
		throws NoSuchBookingException {
		SampleLARBooking sampleLARBooking = fetchByUuid_First(uuid,
				orderByComparator);

		if (sampleLARBooking != null) {
			return sampleLARBooking;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBookingException(msg.toString());
	}

	/**
	 * Returns the first sample l a r booking in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sample l a r booking, or <code>null</code> if a matching sample l a r booking could not be found
	 */
	@Override
	public SampleLARBooking fetchByUuid_First(String uuid,
		OrderByComparator<SampleLARBooking> orderByComparator) {
		List<SampleLARBooking> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sample l a r booking in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sample l a r booking
	 * @throws NoSuchBookingException if a matching sample l a r booking could not be found
	 */
	@Override
	public SampleLARBooking findByUuid_Last(String uuid,
		OrderByComparator<SampleLARBooking> orderByComparator)
		throws NoSuchBookingException {
		SampleLARBooking sampleLARBooking = fetchByUuid_Last(uuid,
				orderByComparator);

		if (sampleLARBooking != null) {
			return sampleLARBooking;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBookingException(msg.toString());
	}

	/**
	 * Returns the last sample l a r booking in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sample l a r booking, or <code>null</code> if a matching sample l a r booking could not be found
	 */
	@Override
	public SampleLARBooking fetchByUuid_Last(String uuid,
		OrderByComparator<SampleLARBooking> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<SampleLARBooking> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sample l a r bookings before and after the current sample l a r booking in the ordered set where uuid = &#63;.
	 *
	 * @param sampleLARBookingId the primary key of the current sample l a r booking
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sample l a r booking
	 * @throws NoSuchBookingException if a sample l a r booking with the primary key could not be found
	 */
	@Override
	public SampleLARBooking[] findByUuid_PrevAndNext(long sampleLARBookingId,
		String uuid, OrderByComparator<SampleLARBooking> orderByComparator)
		throws NoSuchBookingException {
		SampleLARBooking sampleLARBooking = findByPrimaryKey(sampleLARBookingId);

		Session session = null;

		try {
			session = openSession();

			SampleLARBooking[] array = new SampleLARBookingImpl[3];

			array[0] = getByUuid_PrevAndNext(session, sampleLARBooking, uuid,
					orderByComparator, true);

			array[1] = sampleLARBooking;

			array[2] = getByUuid_PrevAndNext(session, sampleLARBooking, uuid,
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

	protected SampleLARBooking getByUuid_PrevAndNext(Session session,
		SampleLARBooking sampleLARBooking, String uuid,
		OrderByComparator<SampleLARBooking> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SAMPLELARBOOKING_WHERE);

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
			query.append(SampleLARBookingModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(sampleLARBooking);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SampleLARBooking> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sample l a r bookings where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (SampleLARBooking sampleLARBooking : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(sampleLARBooking);
		}
	}

	/**
	 * Returns the number of sample l a r bookings where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching sample l a r bookings
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SAMPLELARBOOKING_WHERE);

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

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "sampleLARBooking.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "sampleLARBooking.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(sampleLARBooking.uuid IS NULL OR sampleLARBooking.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(SampleLARBookingModelImpl.ENTITY_CACHE_ENABLED,
			SampleLARBookingModelImpl.FINDER_CACHE_ENABLED,
			SampleLARBookingImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			SampleLARBookingModelImpl.UUID_COLUMN_BITMASK |
			SampleLARBookingModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(SampleLARBookingModelImpl.ENTITY_CACHE_ENABLED,
			SampleLARBookingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the sample l a r booking where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchBookingException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching sample l a r booking
	 * @throws NoSuchBookingException if a matching sample l a r booking could not be found
	 */
	@Override
	public SampleLARBooking findByUUID_G(String uuid, long groupId)
		throws NoSuchBookingException {
		SampleLARBooking sampleLARBooking = fetchByUUID_G(uuid, groupId);

		if (sampleLARBooking == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchBookingException(msg.toString());
		}

		return sampleLARBooking;
	}

	/**
	 * Returns the sample l a r booking where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching sample l a r booking, or <code>null</code> if a matching sample l a r booking could not be found
	 */
	@Override
	public SampleLARBooking fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the sample l a r booking where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching sample l a r booking, or <code>null</code> if a matching sample l a r booking could not be found
	 */
	@Override
	public SampleLARBooking fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof SampleLARBooking) {
			SampleLARBooking sampleLARBooking = (SampleLARBooking)result;

			if (!Validator.equals(uuid, sampleLARBooking.getUuid()) ||
					(groupId != sampleLARBooking.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SAMPLELARBOOKING_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<SampleLARBooking> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					SampleLARBooking sampleLARBooking = list.get(0);

					result = sampleLARBooking;

					cacheResult(sampleLARBooking);

					if ((sampleLARBooking.getUuid() == null) ||
							!sampleLARBooking.getUuid().equals(uuid) ||
							(sampleLARBooking.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, sampleLARBooking);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, finderArgs);

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
			return (SampleLARBooking)result;
		}
	}

	/**
	 * Removes the sample l a r booking where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the sample l a r booking that was removed
	 */
	@Override
	public SampleLARBooking removeByUUID_G(String uuid, long groupId)
		throws NoSuchBookingException {
		SampleLARBooking sampleLARBooking = findByUUID_G(uuid, groupId);

		return remove(sampleLARBooking);
	}

	/**
	 * Returns the number of sample l a r bookings where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching sample l a r bookings
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SAMPLELARBOOKING_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "sampleLARBooking.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "sampleLARBooking.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(sampleLARBooking.uuid IS NULL OR sampleLARBooking.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "sampleLARBooking.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(SampleLARBookingModelImpl.ENTITY_CACHE_ENABLED,
			SampleLARBookingModelImpl.FINDER_CACHE_ENABLED,
			SampleLARBookingImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(SampleLARBookingModelImpl.ENTITY_CACHE_ENABLED,
			SampleLARBookingModelImpl.FINDER_CACHE_ENABLED,
			SampleLARBookingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			SampleLARBookingModelImpl.UUID_COLUMN_BITMASK |
			SampleLARBookingModelImpl.COMPANYID_COLUMN_BITMASK |
			SampleLARBookingModelImpl.BOOKINGNUMBER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(SampleLARBookingModelImpl.ENTITY_CACHE_ENABLED,
			SampleLARBookingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the sample l a r bookings where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching sample l a r bookings
	 */
	@Override
	public List<SampleLARBooking> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sample l a r bookings where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SampleLARBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of sample l a r bookings
	 * @param end the upper bound of the range of sample l a r bookings (not inclusive)
	 * @return the range of matching sample l a r bookings
	 */
	@Override
	public List<SampleLARBooking> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sample l a r bookings where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SampleLARBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of sample l a r bookings
	 * @param end the upper bound of the range of sample l a r bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sample l a r bookings
	 */
	@Override
	public List<SampleLARBooking> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<SampleLARBooking> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sample l a r bookings where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SampleLARBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of sample l a r bookings
	 * @param end the upper bound of the range of sample l a r bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sample l a r bookings
	 */
	@Override
	public List<SampleLARBooking> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<SampleLARBooking> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<SampleLARBooking> list = null;

		if (retrieveFromCache) {
			list = (List<SampleLARBooking>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SampleLARBooking sampleLARBooking : list) {
					if (!Validator.equals(uuid, sampleLARBooking.getUuid()) ||
							(companyId != sampleLARBooking.getCompanyId())) {
						list = null;

						break;
					}
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

			query.append(_SQL_SELECT_SAMPLELARBOOKING_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SampleLARBookingModelImpl.ORDER_BY_JPQL);
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

				qPos.add(companyId);

				if (!pagination) {
					list = (List<SampleLARBooking>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SampleLARBooking>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first sample l a r booking in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sample l a r booking
	 * @throws NoSuchBookingException if a matching sample l a r booking could not be found
	 */
	@Override
	public SampleLARBooking findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<SampleLARBooking> orderByComparator)
		throws NoSuchBookingException {
		SampleLARBooking sampleLARBooking = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (sampleLARBooking != null) {
			return sampleLARBooking;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBookingException(msg.toString());
	}

	/**
	 * Returns the first sample l a r booking in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sample l a r booking, or <code>null</code> if a matching sample l a r booking could not be found
	 */
	@Override
	public SampleLARBooking fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<SampleLARBooking> orderByComparator) {
		List<SampleLARBooking> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sample l a r booking in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sample l a r booking
	 * @throws NoSuchBookingException if a matching sample l a r booking could not be found
	 */
	@Override
	public SampleLARBooking findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<SampleLARBooking> orderByComparator)
		throws NoSuchBookingException {
		SampleLARBooking sampleLARBooking = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (sampleLARBooking != null) {
			return sampleLARBooking;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBookingException(msg.toString());
	}

	/**
	 * Returns the last sample l a r booking in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sample l a r booking, or <code>null</code> if a matching sample l a r booking could not be found
	 */
	@Override
	public SampleLARBooking fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<SampleLARBooking> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<SampleLARBooking> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sample l a r bookings before and after the current sample l a r booking in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param sampleLARBookingId the primary key of the current sample l a r booking
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sample l a r booking
	 * @throws NoSuchBookingException if a sample l a r booking with the primary key could not be found
	 */
	@Override
	public SampleLARBooking[] findByUuid_C_PrevAndNext(
		long sampleLARBookingId, String uuid, long companyId,
		OrderByComparator<SampleLARBooking> orderByComparator)
		throws NoSuchBookingException {
		SampleLARBooking sampleLARBooking = findByPrimaryKey(sampleLARBookingId);

		Session session = null;

		try {
			session = openSession();

			SampleLARBooking[] array = new SampleLARBookingImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, sampleLARBooking, uuid,
					companyId, orderByComparator, true);

			array[1] = sampleLARBooking;

			array[2] = getByUuid_C_PrevAndNext(session, sampleLARBooking, uuid,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SampleLARBooking getByUuid_C_PrevAndNext(Session session,
		SampleLARBooking sampleLARBooking, String uuid, long companyId,
		OrderByComparator<SampleLARBooking> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SAMPLELARBOOKING_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

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
			query.append(SampleLARBookingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(sampleLARBooking);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SampleLARBooking> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sample l a r bookings where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (SampleLARBooking sampleLARBooking : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(sampleLARBooking);
		}
	}

	/**
	 * Returns the number of sample l a r bookings where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching sample l a r bookings
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SAMPLELARBOOKING_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "sampleLARBooking.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "sampleLARBooking.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(sampleLARBooking.uuid IS NULL OR sampleLARBooking.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "sampleLARBooking.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(SampleLARBookingModelImpl.ENTITY_CACHE_ENABLED,
			SampleLARBookingModelImpl.FINDER_CACHE_ENABLED,
			SampleLARBookingImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(SampleLARBookingModelImpl.ENTITY_CACHE_ENABLED,
			SampleLARBookingModelImpl.FINDER_CACHE_ENABLED,
			SampleLARBookingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			SampleLARBookingModelImpl.GROUPID_COLUMN_BITMASK |
			SampleLARBookingModelImpl.BOOKINGNUMBER_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(SampleLARBookingModelImpl.ENTITY_CACHE_ENABLED,
			SampleLARBookingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the sample l a r bookings where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching sample l a r bookings
	 */
	@Override
	public List<SampleLARBooking> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sample l a r bookings where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SampleLARBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of sample l a r bookings
	 * @param end the upper bound of the range of sample l a r bookings (not inclusive)
	 * @return the range of matching sample l a r bookings
	 */
	@Override
	public List<SampleLARBooking> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sample l a r bookings where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SampleLARBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of sample l a r bookings
	 * @param end the upper bound of the range of sample l a r bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sample l a r bookings
	 */
	@Override
	public List<SampleLARBooking> findByGroupId(long groupId, int start,
		int end, OrderByComparator<SampleLARBooking> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sample l a r bookings where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SampleLARBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of sample l a r bookings
	 * @param end the upper bound of the range of sample l a r bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching sample l a r bookings
	 */
	@Override
	public List<SampleLARBooking> findByGroupId(long groupId, int start,
		int end, OrderByComparator<SampleLARBooking> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<SampleLARBooking> list = null;

		if (retrieveFromCache) {
			list = (List<SampleLARBooking>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SampleLARBooking sampleLARBooking : list) {
					if ((groupId != sampleLARBooking.getGroupId())) {
						list = null;

						break;
					}
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

			query.append(_SQL_SELECT_SAMPLELARBOOKING_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SampleLARBookingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<SampleLARBooking>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SampleLARBooking>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first sample l a r booking in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sample l a r booking
	 * @throws NoSuchBookingException if a matching sample l a r booking could not be found
	 */
	@Override
	public SampleLARBooking findByGroupId_First(long groupId,
		OrderByComparator<SampleLARBooking> orderByComparator)
		throws NoSuchBookingException {
		SampleLARBooking sampleLARBooking = fetchByGroupId_First(groupId,
				orderByComparator);

		if (sampleLARBooking != null) {
			return sampleLARBooking;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBookingException(msg.toString());
	}

	/**
	 * Returns the first sample l a r booking in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sample l a r booking, or <code>null</code> if a matching sample l a r booking could not be found
	 */
	@Override
	public SampleLARBooking fetchByGroupId_First(long groupId,
		OrderByComparator<SampleLARBooking> orderByComparator) {
		List<SampleLARBooking> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sample l a r booking in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sample l a r booking
	 * @throws NoSuchBookingException if a matching sample l a r booking could not be found
	 */
	@Override
	public SampleLARBooking findByGroupId_Last(long groupId,
		OrderByComparator<SampleLARBooking> orderByComparator)
		throws NoSuchBookingException {
		SampleLARBooking sampleLARBooking = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (sampleLARBooking != null) {
			return sampleLARBooking;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchBookingException(msg.toString());
	}

	/**
	 * Returns the last sample l a r booking in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sample l a r booking, or <code>null</code> if a matching sample l a r booking could not be found
	 */
	@Override
	public SampleLARBooking fetchByGroupId_Last(long groupId,
		OrderByComparator<SampleLARBooking> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<SampleLARBooking> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sample l a r bookings before and after the current sample l a r booking in the ordered set where groupId = &#63;.
	 *
	 * @param sampleLARBookingId the primary key of the current sample l a r booking
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sample l a r booking
	 * @throws NoSuchBookingException if a sample l a r booking with the primary key could not be found
	 */
	@Override
	public SampleLARBooking[] findByGroupId_PrevAndNext(
		long sampleLARBookingId, long groupId,
		OrderByComparator<SampleLARBooking> orderByComparator)
		throws NoSuchBookingException {
		SampleLARBooking sampleLARBooking = findByPrimaryKey(sampleLARBookingId);

		Session session = null;

		try {
			session = openSession();

			SampleLARBooking[] array = new SampleLARBookingImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, sampleLARBooking,
					groupId, orderByComparator, true);

			array[1] = sampleLARBooking;

			array[2] = getByGroupId_PrevAndNext(session, sampleLARBooking,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SampleLARBooking getByGroupId_PrevAndNext(Session session,
		SampleLARBooking sampleLARBooking, long groupId,
		OrderByComparator<SampleLARBooking> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SAMPLELARBOOKING_WHERE);

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
			query.append(SampleLARBookingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(sampleLARBooking);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SampleLARBooking> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sample l a r bookings where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (SampleLARBooking sampleLARBooking : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(sampleLARBooking);
		}
	}

	/**
	 * Returns the number of sample l a r bookings where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching sample l a r bookings
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SAMPLELARBOOKING_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "sampleLARBooking.groupId = ?";

	public SampleLARBookingPersistenceImpl() {
		setModelClass(SampleLARBooking.class);
	}

	/**
	 * Caches the sample l a r booking in the entity cache if it is enabled.
	 *
	 * @param sampleLARBooking the sample l a r booking
	 */
	@Override
	public void cacheResult(SampleLARBooking sampleLARBooking) {
		entityCache.putResult(SampleLARBookingModelImpl.ENTITY_CACHE_ENABLED,
			SampleLARBookingImpl.class, sampleLARBooking.getPrimaryKey(),
			sampleLARBooking);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				sampleLARBooking.getUuid(), sampleLARBooking.getGroupId()
			}, sampleLARBooking);

		sampleLARBooking.resetOriginalValues();
	}

	/**
	 * Caches the sample l a r bookings in the entity cache if it is enabled.
	 *
	 * @param sampleLARBookings the sample l a r bookings
	 */
	@Override
	public void cacheResult(List<SampleLARBooking> sampleLARBookings) {
		for (SampleLARBooking sampleLARBooking : sampleLARBookings) {
			if (entityCache.getResult(
						SampleLARBookingModelImpl.ENTITY_CACHE_ENABLED,
						SampleLARBookingImpl.class,
						sampleLARBooking.getPrimaryKey()) == null) {
				cacheResult(sampleLARBooking);
			}
			else {
				sampleLARBooking.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all sample l a r bookings.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SampleLARBookingImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the sample l a r booking.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SampleLARBooking sampleLARBooking) {
		entityCache.removeResult(SampleLARBookingModelImpl.ENTITY_CACHE_ENABLED,
			SampleLARBookingImpl.class, sampleLARBooking.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((SampleLARBookingModelImpl)sampleLARBooking);
	}

	@Override
	public void clearCache(List<SampleLARBooking> sampleLARBookings) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SampleLARBooking sampleLARBooking : sampleLARBookings) {
			entityCache.removeResult(SampleLARBookingModelImpl.ENTITY_CACHE_ENABLED,
				SampleLARBookingImpl.class, sampleLARBooking.getPrimaryKey());

			clearUniqueFindersCache((SampleLARBookingModelImpl)sampleLARBooking);
		}
	}

	protected void cacheUniqueFindersCache(
		SampleLARBookingModelImpl sampleLARBookingModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					sampleLARBookingModelImpl.getUuid(),
					sampleLARBookingModelImpl.getGroupId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				sampleLARBookingModelImpl);
		}
		else {
			if ((sampleLARBookingModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						sampleLARBookingModelImpl.getUuid(),
						sampleLARBookingModelImpl.getGroupId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					sampleLARBookingModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		SampleLARBookingModelImpl sampleLARBookingModelImpl) {
		Object[] args = new Object[] {
				sampleLARBookingModelImpl.getUuid(),
				sampleLARBookingModelImpl.getGroupId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((sampleLARBookingModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					sampleLARBookingModelImpl.getOriginalUuid(),
					sampleLARBookingModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new sample l a r booking with the primary key. Does not add the sample l a r booking to the database.
	 *
	 * @param sampleLARBookingId the primary key for the new sample l a r booking
	 * @return the new sample l a r booking
	 */
	@Override
	public SampleLARBooking create(long sampleLARBookingId) {
		SampleLARBooking sampleLARBooking = new SampleLARBookingImpl();

		sampleLARBooking.setNew(true);
		sampleLARBooking.setPrimaryKey(sampleLARBookingId);

		String uuid = PortalUUIDUtil.generate();

		sampleLARBooking.setUuid(uuid);

		return sampleLARBooking;
	}

	/**
	 * Removes the sample l a r booking with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sampleLARBookingId the primary key of the sample l a r booking
	 * @return the sample l a r booking that was removed
	 * @throws NoSuchBookingException if a sample l a r booking with the primary key could not be found
	 */
	@Override
	public SampleLARBooking remove(long sampleLARBookingId)
		throws NoSuchBookingException {
		return remove((Serializable)sampleLARBookingId);
	}

	/**
	 * Removes the sample l a r booking with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sample l a r booking
	 * @return the sample l a r booking that was removed
	 * @throws NoSuchBookingException if a sample l a r booking with the primary key could not be found
	 */
	@Override
	public SampleLARBooking remove(Serializable primaryKey)
		throws NoSuchBookingException {
		Session session = null;

		try {
			session = openSession();

			SampleLARBooking sampleLARBooking = (SampleLARBooking)session.get(SampleLARBookingImpl.class,
					primaryKey);

			if (sampleLARBooking == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBookingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(sampleLARBooking);
		}
		catch (NoSuchBookingException nsee) {
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
	protected SampleLARBooking removeImpl(SampleLARBooking sampleLARBooking) {
		sampleLARBooking = toUnwrappedModel(sampleLARBooking);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(sampleLARBooking)) {
				sampleLARBooking = (SampleLARBooking)session.get(SampleLARBookingImpl.class,
						sampleLARBooking.getPrimaryKeyObj());
			}

			if (sampleLARBooking != null) {
				session.delete(sampleLARBooking);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (sampleLARBooking != null) {
			clearCache(sampleLARBooking);
		}

		return sampleLARBooking;
	}

	@Override
	public SampleLARBooking updateImpl(SampleLARBooking sampleLARBooking) {
		sampleLARBooking = toUnwrappedModel(sampleLARBooking);

		boolean isNew = sampleLARBooking.isNew();

		SampleLARBookingModelImpl sampleLARBookingModelImpl = (SampleLARBookingModelImpl)sampleLARBooking;

		if (Validator.isNull(sampleLARBooking.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			sampleLARBooking.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (sampleLARBooking.getCreateDate() == null)) {
			if (serviceContext == null) {
				sampleLARBooking.setCreateDate(now);
			}
			else {
				sampleLARBooking.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!sampleLARBookingModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				sampleLARBooking.setModifiedDate(now);
			}
			else {
				sampleLARBooking.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (sampleLARBooking.isNew()) {
				session.save(sampleLARBooking);

				sampleLARBooking.setNew(false);
			}
			else {
				sampleLARBooking = (SampleLARBooking)session.merge(sampleLARBooking);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SampleLARBookingModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((sampleLARBookingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						sampleLARBookingModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { sampleLARBookingModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((sampleLARBookingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						sampleLARBookingModelImpl.getOriginalUuid(),
						sampleLARBookingModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						sampleLARBookingModelImpl.getUuid(),
						sampleLARBookingModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((sampleLARBookingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						sampleLARBookingModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { sampleLARBookingModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}
		}

		entityCache.putResult(SampleLARBookingModelImpl.ENTITY_CACHE_ENABLED,
			SampleLARBookingImpl.class, sampleLARBooking.getPrimaryKey(),
			sampleLARBooking, false);

		clearUniqueFindersCache(sampleLARBookingModelImpl);
		cacheUniqueFindersCache(sampleLARBookingModelImpl, isNew);

		sampleLARBooking.resetOriginalValues();

		return sampleLARBooking;
	}

	protected SampleLARBooking toUnwrappedModel(
		SampleLARBooking sampleLARBooking) {
		if (sampleLARBooking instanceof SampleLARBookingImpl) {
			return sampleLARBooking;
		}

		SampleLARBookingImpl sampleLARBookingImpl = new SampleLARBookingImpl();

		sampleLARBookingImpl.setNew(sampleLARBooking.isNew());
		sampleLARBookingImpl.setPrimaryKey(sampleLARBooking.getPrimaryKey());

		sampleLARBookingImpl.setUuid(sampleLARBooking.getUuid());
		sampleLARBookingImpl.setSampleLARBookingId(sampleLARBooking.getSampleLARBookingId());
		sampleLARBookingImpl.setGroupId(sampleLARBooking.getGroupId());
		sampleLARBookingImpl.setCompanyId(sampleLARBooking.getCompanyId());
		sampleLARBookingImpl.setUserId(sampleLARBooking.getUserId());
		sampleLARBookingImpl.setUserName(sampleLARBooking.getUserName());
		sampleLARBookingImpl.setCreateDate(sampleLARBooking.getCreateDate());
		sampleLARBookingImpl.setModifiedDate(sampleLARBooking.getModifiedDate());
		sampleLARBookingImpl.setBookingNumber(sampleLARBooking.getBookingNumber());
		sampleLARBookingImpl.setLastPublishDate(sampleLARBooking.getLastPublishDate());

		return sampleLARBookingImpl;
	}

	/**
	 * Returns the sample l a r booking with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the sample l a r booking
	 * @return the sample l a r booking
	 * @throws NoSuchBookingException if a sample l a r booking with the primary key could not be found
	 */
	@Override
	public SampleLARBooking findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBookingException {
		SampleLARBooking sampleLARBooking = fetchByPrimaryKey(primaryKey);

		if (sampleLARBooking == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBookingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return sampleLARBooking;
	}

	/**
	 * Returns the sample l a r booking with the primary key or throws a {@link NoSuchBookingException} if it could not be found.
	 *
	 * @param sampleLARBookingId the primary key of the sample l a r booking
	 * @return the sample l a r booking
	 * @throws NoSuchBookingException if a sample l a r booking with the primary key could not be found
	 */
	@Override
	public SampleLARBooking findByPrimaryKey(long sampleLARBookingId)
		throws NoSuchBookingException {
		return findByPrimaryKey((Serializable)sampleLARBookingId);
	}

	/**
	 * Returns the sample l a r booking with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sample l a r booking
	 * @return the sample l a r booking, or <code>null</code> if a sample l a r booking with the primary key could not be found
	 */
	@Override
	public SampleLARBooking fetchByPrimaryKey(Serializable primaryKey) {
		SampleLARBooking sampleLARBooking = (SampleLARBooking)entityCache.getResult(SampleLARBookingModelImpl.ENTITY_CACHE_ENABLED,
				SampleLARBookingImpl.class, primaryKey);

		if (sampleLARBooking == _nullSampleLARBooking) {
			return null;
		}

		if (sampleLARBooking == null) {
			Session session = null;

			try {
				session = openSession();

				sampleLARBooking = (SampleLARBooking)session.get(SampleLARBookingImpl.class,
						primaryKey);

				if (sampleLARBooking != null) {
					cacheResult(sampleLARBooking);
				}
				else {
					entityCache.putResult(SampleLARBookingModelImpl.ENTITY_CACHE_ENABLED,
						SampleLARBookingImpl.class, primaryKey,
						_nullSampleLARBooking);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SampleLARBookingModelImpl.ENTITY_CACHE_ENABLED,
					SampleLARBookingImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return sampleLARBooking;
	}

	/**
	 * Returns the sample l a r booking with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sampleLARBookingId the primary key of the sample l a r booking
	 * @return the sample l a r booking, or <code>null</code> if a sample l a r booking with the primary key could not be found
	 */
	@Override
	public SampleLARBooking fetchByPrimaryKey(long sampleLARBookingId) {
		return fetchByPrimaryKey((Serializable)sampleLARBookingId);
	}

	@Override
	public Map<Serializable, SampleLARBooking> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SampleLARBooking> map = new HashMap<Serializable, SampleLARBooking>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SampleLARBooking sampleLARBooking = fetchByPrimaryKey(primaryKey);

			if (sampleLARBooking != null) {
				map.put(primaryKey, sampleLARBooking);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			SampleLARBooking sampleLARBooking = (SampleLARBooking)entityCache.getResult(SampleLARBookingModelImpl.ENTITY_CACHE_ENABLED,
					SampleLARBookingImpl.class, primaryKey);

			if (sampleLARBooking == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, sampleLARBooking);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SAMPLELARBOOKING_WHERE_PKS_IN);

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

			for (SampleLARBooking sampleLARBooking : (List<SampleLARBooking>)q.list()) {
				map.put(sampleLARBooking.getPrimaryKeyObj(), sampleLARBooking);

				cacheResult(sampleLARBooking);

				uncachedPrimaryKeys.remove(sampleLARBooking.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SampleLARBookingModelImpl.ENTITY_CACHE_ENABLED,
					SampleLARBookingImpl.class, primaryKey,
					_nullSampleLARBooking);
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
	 * Returns all the sample l a r bookings.
	 *
	 * @return the sample l a r bookings
	 */
	@Override
	public List<SampleLARBooking> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sample l a r bookings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SampleLARBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sample l a r bookings
	 * @param end the upper bound of the range of sample l a r bookings (not inclusive)
	 * @return the range of sample l a r bookings
	 */
	@Override
	public List<SampleLARBooking> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sample l a r bookings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SampleLARBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sample l a r bookings
	 * @param end the upper bound of the range of sample l a r bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sample l a r bookings
	 */
	@Override
	public List<SampleLARBooking> findAll(int start, int end,
		OrderByComparator<SampleLARBooking> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sample l a r bookings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SampleLARBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sample l a r bookings
	 * @param end the upper bound of the range of sample l a r bookings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of sample l a r bookings
	 */
	@Override
	public List<SampleLARBooking> findAll(int start, int end,
		OrderByComparator<SampleLARBooking> orderByComparator,
		boolean retrieveFromCache) {
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

		List<SampleLARBooking> list = null;

		if (retrieveFromCache) {
			list = (List<SampleLARBooking>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SAMPLELARBOOKING);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SAMPLELARBOOKING;

				if (pagination) {
					sql = sql.concat(SampleLARBookingModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SampleLARBooking>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SampleLARBooking>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the sample l a r bookings from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SampleLARBooking sampleLARBooking : findAll()) {
			remove(sampleLARBooking);
		}
	}

	/**
	 * Returns the number of sample l a r bookings.
	 *
	 * @return the number of sample l a r bookings
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SAMPLELARBOOKING);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SampleLARBookingModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sample l a r booking persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SampleLARBookingImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = CompanyProvider.class)
	protected CompanyProvider companyProvider;
	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_SAMPLELARBOOKING = "SELECT sampleLARBooking FROM SampleLARBooking sampleLARBooking";
	private static final String _SQL_SELECT_SAMPLELARBOOKING_WHERE_PKS_IN = "SELECT sampleLARBooking FROM SampleLARBooking sampleLARBooking WHERE sampleLARBookingId IN (";
	private static final String _SQL_SELECT_SAMPLELARBOOKING_WHERE = "SELECT sampleLARBooking FROM SampleLARBooking sampleLARBooking WHERE ";
	private static final String _SQL_COUNT_SAMPLELARBOOKING = "SELECT COUNT(sampleLARBooking) FROM SampleLARBooking sampleLARBooking";
	private static final String _SQL_COUNT_SAMPLELARBOOKING_WHERE = "SELECT COUNT(sampleLARBooking) FROM SampleLARBooking sampleLARBooking WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "sampleLARBooking.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SampleLARBooking exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SampleLARBooking exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SampleLARBookingPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static final SampleLARBooking _nullSampleLARBooking = new SampleLARBookingImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SampleLARBooking> toCacheModel() {
				return _nullSampleLARBookingCacheModel;
			}
		};

	private static final CacheModel<SampleLARBooking> _nullSampleLARBookingCacheModel =
		new CacheModel<SampleLARBooking>() {
			@Override
			public SampleLARBooking toEntityModel() {
				return _nullSampleLARBooking;
			}
		};
}