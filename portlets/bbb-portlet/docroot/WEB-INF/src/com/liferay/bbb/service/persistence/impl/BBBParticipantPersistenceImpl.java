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

package com.liferay.bbb.service.persistence.impl;

import com.liferay.bbb.NoSuchParticipantException;
import com.liferay.bbb.model.BBBParticipant;
import com.liferay.bbb.model.impl.BBBParticipantImpl;
import com.liferay.bbb.model.impl.BBBParticipantModelImpl;
import com.liferay.bbb.service.persistence.BBBParticipantPersistence;

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
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the b b b participant service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Shinn Lok
 * @see BBBParticipantPersistence
 * @see BBBParticipantUtil
 * @generated
 */
public class BBBParticipantPersistenceImpl extends BasePersistenceImpl<BBBParticipant>
	implements BBBParticipantPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link BBBParticipantUtil} to access the b b b participant persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = BBBParticipantImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BBBParticipantModelImpl.ENTITY_CACHE_ENABLED,
			BBBParticipantModelImpl.FINDER_CACHE_ENABLED,
			BBBParticipantImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BBBParticipantModelImpl.ENTITY_CACHE_ENABLED,
			BBBParticipantModelImpl.FINDER_CACHE_ENABLED,
			BBBParticipantImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BBBParticipantModelImpl.ENTITY_CACHE_ENABLED,
			BBBParticipantModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_BBBMEETINGID =
		new FinderPath(BBBParticipantModelImpl.ENTITY_CACHE_ENABLED,
			BBBParticipantModelImpl.FINDER_CACHE_ENABLED,
			BBBParticipantImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByBbbMeetingId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BBBMEETINGID =
		new FinderPath(BBBParticipantModelImpl.ENTITY_CACHE_ENABLED,
			BBBParticipantModelImpl.FINDER_CACHE_ENABLED,
			BBBParticipantImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByBbbMeetingId",
			new String[] { Long.class.getName() },
			BBBParticipantModelImpl.BBBMEETINGID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_BBBMEETINGID = new FinderPath(BBBParticipantModelImpl.ENTITY_CACHE_ENABLED,
			BBBParticipantModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByBbbMeetingId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the b b b participants where bbbMeetingId = &#63;.
	 *
	 * @param bbbMeetingId the bbb meeting ID
	 * @return the matching b b b participants
	 */
	@Override
	public List<BBBParticipant> findByBbbMeetingId(long bbbMeetingId) {
		return findByBbbMeetingId(bbbMeetingId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the b b b participants where bbbMeetingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBParticipantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param bbbMeetingId the bbb meeting ID
	 * @param start the lower bound of the range of b b b participants
	 * @param end the upper bound of the range of b b b participants (not inclusive)
	 * @return the range of matching b b b participants
	 */
	@Override
	public List<BBBParticipant> findByBbbMeetingId(long bbbMeetingId,
		int start, int end) {
		return findByBbbMeetingId(bbbMeetingId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the b b b participants where bbbMeetingId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBParticipantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param bbbMeetingId the bbb meeting ID
	 * @param start the lower bound of the range of b b b participants
	 * @param end the upper bound of the range of b b b participants (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching b b b participants
	 */
	@Override
	public List<BBBParticipant> findByBbbMeetingId(long bbbMeetingId,
		int start, int end, OrderByComparator<BBBParticipant> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BBBMEETINGID;
			finderArgs = new Object[] { bbbMeetingId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_BBBMEETINGID;
			finderArgs = new Object[] {
					bbbMeetingId,
					
					start, end, orderByComparator
				};
		}

		List<BBBParticipant> list = (List<BBBParticipant>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BBBParticipant bbbParticipant : list) {
				if ((bbbMeetingId != bbbParticipant.getBbbMeetingId())) {
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

			query.append(_SQL_SELECT_BBBPARTICIPANT_WHERE);

			query.append(_FINDER_COLUMN_BBBMEETINGID_BBBMEETINGID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BBBParticipantModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(bbbMeetingId);

				if (!pagination) {
					list = (List<BBBParticipant>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BBBParticipant>)QueryUtil.list(q,
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
	 * Returns the first b b b participant in the ordered set where bbbMeetingId = &#63;.
	 *
	 * @param bbbMeetingId the bbb meeting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching b b b participant
	 * @throws com.liferay.bbb.NoSuchParticipantException if a matching b b b participant could not be found
	 */
	@Override
	public BBBParticipant findByBbbMeetingId_First(long bbbMeetingId,
		OrderByComparator<BBBParticipant> orderByComparator)
		throws NoSuchParticipantException {
		BBBParticipant bbbParticipant = fetchByBbbMeetingId_First(bbbMeetingId,
				orderByComparator);

		if (bbbParticipant != null) {
			return bbbParticipant;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("bbbMeetingId=");
		msg.append(bbbMeetingId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchParticipantException(msg.toString());
	}

	/**
	 * Returns the first b b b participant in the ordered set where bbbMeetingId = &#63;.
	 *
	 * @param bbbMeetingId the bbb meeting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching b b b participant, or <code>null</code> if a matching b b b participant could not be found
	 */
	@Override
	public BBBParticipant fetchByBbbMeetingId_First(long bbbMeetingId,
		OrderByComparator<BBBParticipant> orderByComparator) {
		List<BBBParticipant> list = findByBbbMeetingId(bbbMeetingId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last b b b participant in the ordered set where bbbMeetingId = &#63;.
	 *
	 * @param bbbMeetingId the bbb meeting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching b b b participant
	 * @throws com.liferay.bbb.NoSuchParticipantException if a matching b b b participant could not be found
	 */
	@Override
	public BBBParticipant findByBbbMeetingId_Last(long bbbMeetingId,
		OrderByComparator<BBBParticipant> orderByComparator)
		throws NoSuchParticipantException {
		BBBParticipant bbbParticipant = fetchByBbbMeetingId_Last(bbbMeetingId,
				orderByComparator);

		if (bbbParticipant != null) {
			return bbbParticipant;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("bbbMeetingId=");
		msg.append(bbbMeetingId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchParticipantException(msg.toString());
	}

	/**
	 * Returns the last b b b participant in the ordered set where bbbMeetingId = &#63;.
	 *
	 * @param bbbMeetingId the bbb meeting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching b b b participant, or <code>null</code> if a matching b b b participant could not be found
	 */
	@Override
	public BBBParticipant fetchByBbbMeetingId_Last(long bbbMeetingId,
		OrderByComparator<BBBParticipant> orderByComparator) {
		int count = countByBbbMeetingId(bbbMeetingId);

		if (count == 0) {
			return null;
		}

		List<BBBParticipant> list = findByBbbMeetingId(bbbMeetingId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the b b b participants before and after the current b b b participant in the ordered set where bbbMeetingId = &#63;.
	 *
	 * @param bbbParticipantId the primary key of the current b b b participant
	 * @param bbbMeetingId the bbb meeting ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next b b b participant
	 * @throws com.liferay.bbb.NoSuchParticipantException if a b b b participant with the primary key could not be found
	 */
	@Override
	public BBBParticipant[] findByBbbMeetingId_PrevAndNext(
		long bbbParticipantId, long bbbMeetingId,
		OrderByComparator<BBBParticipant> orderByComparator)
		throws NoSuchParticipantException {
		BBBParticipant bbbParticipant = findByPrimaryKey(bbbParticipantId);

		Session session = null;

		try {
			session = openSession();

			BBBParticipant[] array = new BBBParticipantImpl[3];

			array[0] = getByBbbMeetingId_PrevAndNext(session, bbbParticipant,
					bbbMeetingId, orderByComparator, true);

			array[1] = bbbParticipant;

			array[2] = getByBbbMeetingId_PrevAndNext(session, bbbParticipant,
					bbbMeetingId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BBBParticipant getByBbbMeetingId_PrevAndNext(Session session,
		BBBParticipant bbbParticipant, long bbbMeetingId,
		OrderByComparator<BBBParticipant> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BBBPARTICIPANT_WHERE);

		query.append(_FINDER_COLUMN_BBBMEETINGID_BBBMEETINGID_2);

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
			query.append(BBBParticipantModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(bbbMeetingId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(bbbParticipant);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BBBParticipant> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the b b b participants where bbbMeetingId = &#63; from the database.
	 *
	 * @param bbbMeetingId the bbb meeting ID
	 */
	@Override
	public void removeByBbbMeetingId(long bbbMeetingId) {
		for (BBBParticipant bbbParticipant : findByBbbMeetingId(bbbMeetingId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(bbbParticipant);
		}
	}

	/**
	 * Returns the number of b b b participants where bbbMeetingId = &#63;.
	 *
	 * @param bbbMeetingId the bbb meeting ID
	 * @return the number of matching b b b participants
	 */
	@Override
	public int countByBbbMeetingId(long bbbMeetingId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_BBBMEETINGID;

		Object[] finderArgs = new Object[] { bbbMeetingId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BBBPARTICIPANT_WHERE);

			query.append(_FINDER_COLUMN_BBBMEETINGID_BBBMEETINGID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(bbbMeetingId);

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

	private static final String _FINDER_COLUMN_BBBMEETINGID_BBBMEETINGID_2 = "bbbParticipant.bbbMeetingId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_BMI_EA = new FinderPath(BBBParticipantModelImpl.ENTITY_CACHE_ENABLED,
			BBBParticipantModelImpl.FINDER_CACHE_ENABLED,
			BBBParticipantImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByBMI_EA",
			new String[] { Long.class.getName(), String.class.getName() },
			BBBParticipantModelImpl.BBBMEETINGID_COLUMN_BITMASK |
			BBBParticipantModelImpl.EMAILADDRESS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_BMI_EA = new FinderPath(BBBParticipantModelImpl.ENTITY_CACHE_ENABLED,
			BBBParticipantModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByBMI_EA",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the b b b participant where bbbMeetingId = &#63; and emailAddress = &#63; or throws a {@link com.liferay.bbb.NoSuchParticipantException} if it could not be found.
	 *
	 * @param bbbMeetingId the bbb meeting ID
	 * @param emailAddress the email address
	 * @return the matching b b b participant
	 * @throws com.liferay.bbb.NoSuchParticipantException if a matching b b b participant could not be found
	 */
	@Override
	public BBBParticipant findByBMI_EA(long bbbMeetingId, String emailAddress)
		throws NoSuchParticipantException {
		BBBParticipant bbbParticipant = fetchByBMI_EA(bbbMeetingId, emailAddress);

		if (bbbParticipant == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("bbbMeetingId=");
			msg.append(bbbMeetingId);

			msg.append(", emailAddress=");
			msg.append(emailAddress);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchParticipantException(msg.toString());
		}

		return bbbParticipant;
	}

	/**
	 * Returns the b b b participant where bbbMeetingId = &#63; and emailAddress = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param bbbMeetingId the bbb meeting ID
	 * @param emailAddress the email address
	 * @return the matching b b b participant, or <code>null</code> if a matching b b b participant could not be found
	 */
	@Override
	public BBBParticipant fetchByBMI_EA(long bbbMeetingId, String emailAddress) {
		return fetchByBMI_EA(bbbMeetingId, emailAddress, true);
	}

	/**
	 * Returns the b b b participant where bbbMeetingId = &#63; and emailAddress = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param bbbMeetingId the bbb meeting ID
	 * @param emailAddress the email address
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching b b b participant, or <code>null</code> if a matching b b b participant could not be found
	 */
	@Override
	public BBBParticipant fetchByBMI_EA(long bbbMeetingId, String emailAddress,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { bbbMeetingId, emailAddress };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_BMI_EA,
					finderArgs, this);
		}

		if (result instanceof BBBParticipant) {
			BBBParticipant bbbParticipant = (BBBParticipant)result;

			if ((bbbMeetingId != bbbParticipant.getBbbMeetingId()) ||
					!Validator.equals(emailAddress,
						bbbParticipant.getEmailAddress())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_BBBPARTICIPANT_WHERE);

			query.append(_FINDER_COLUMN_BMI_EA_BBBMEETINGID_2);

			boolean bindEmailAddress = false;

			if (emailAddress == null) {
				query.append(_FINDER_COLUMN_BMI_EA_EMAILADDRESS_1);
			}
			else if (emailAddress.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_BMI_EA_EMAILADDRESS_3);
			}
			else {
				bindEmailAddress = true;

				query.append(_FINDER_COLUMN_BMI_EA_EMAILADDRESS_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(bbbMeetingId);

				if (bindEmailAddress) {
					qPos.add(emailAddress);
				}

				List<BBBParticipant> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_BMI_EA,
						finderArgs, list);
				}
				else {
					BBBParticipant bbbParticipant = list.get(0);

					result = bbbParticipant;

					cacheResult(bbbParticipant);

					if ((bbbParticipant.getBbbMeetingId() != bbbMeetingId) ||
							(bbbParticipant.getEmailAddress() == null) ||
							!bbbParticipant.getEmailAddress()
											   .equals(emailAddress)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_BMI_EA,
							finderArgs, bbbParticipant);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_BMI_EA,
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
			return (BBBParticipant)result;
		}
	}

	/**
	 * Removes the b b b participant where bbbMeetingId = &#63; and emailAddress = &#63; from the database.
	 *
	 * @param bbbMeetingId the bbb meeting ID
	 * @param emailAddress the email address
	 * @return the b b b participant that was removed
	 */
	@Override
	public BBBParticipant removeByBMI_EA(long bbbMeetingId, String emailAddress)
		throws NoSuchParticipantException {
		BBBParticipant bbbParticipant = findByBMI_EA(bbbMeetingId, emailAddress);

		return remove(bbbParticipant);
	}

	/**
	 * Returns the number of b b b participants where bbbMeetingId = &#63; and emailAddress = &#63;.
	 *
	 * @param bbbMeetingId the bbb meeting ID
	 * @param emailAddress the email address
	 * @return the number of matching b b b participants
	 */
	@Override
	public int countByBMI_EA(long bbbMeetingId, String emailAddress) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_BMI_EA;

		Object[] finderArgs = new Object[] { bbbMeetingId, emailAddress };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_BBBPARTICIPANT_WHERE);

			query.append(_FINDER_COLUMN_BMI_EA_BBBMEETINGID_2);

			boolean bindEmailAddress = false;

			if (emailAddress == null) {
				query.append(_FINDER_COLUMN_BMI_EA_EMAILADDRESS_1);
			}
			else if (emailAddress.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_BMI_EA_EMAILADDRESS_3);
			}
			else {
				bindEmailAddress = true;

				query.append(_FINDER_COLUMN_BMI_EA_EMAILADDRESS_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(bbbMeetingId);

				if (bindEmailAddress) {
					qPos.add(emailAddress);
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

	private static final String _FINDER_COLUMN_BMI_EA_BBBMEETINGID_2 = "bbbParticipant.bbbMeetingId = ? AND ";
	private static final String _FINDER_COLUMN_BMI_EA_EMAILADDRESS_1 = "bbbParticipant.emailAddress IS NULL";
	private static final String _FINDER_COLUMN_BMI_EA_EMAILADDRESS_2 = "bbbParticipant.emailAddress = ?";
	private static final String _FINDER_COLUMN_BMI_EA_EMAILADDRESS_3 = "(bbbParticipant.emailAddress IS NULL OR bbbParticipant.emailAddress = '')";

	public BBBParticipantPersistenceImpl() {
		setModelClass(BBBParticipant.class);
	}

	/**
	 * Caches the b b b participant in the entity cache if it is enabled.
	 *
	 * @param bbbParticipant the b b b participant
	 */
	@Override
	public void cacheResult(BBBParticipant bbbParticipant) {
		EntityCacheUtil.putResult(BBBParticipantModelImpl.ENTITY_CACHE_ENABLED,
			BBBParticipantImpl.class, bbbParticipant.getPrimaryKey(),
			bbbParticipant);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_BMI_EA,
			new Object[] {
				bbbParticipant.getBbbMeetingId(),
				bbbParticipant.getEmailAddress()
			}, bbbParticipant);

		bbbParticipant.resetOriginalValues();
	}

	/**
	 * Caches the b b b participants in the entity cache if it is enabled.
	 *
	 * @param bbbParticipants the b b b participants
	 */
	@Override
	public void cacheResult(List<BBBParticipant> bbbParticipants) {
		for (BBBParticipant bbbParticipant : bbbParticipants) {
			if (EntityCacheUtil.getResult(
						BBBParticipantModelImpl.ENTITY_CACHE_ENABLED,
						BBBParticipantImpl.class, bbbParticipant.getPrimaryKey()) == null) {
				cacheResult(bbbParticipant);
			}
			else {
				bbbParticipant.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all b b b participants.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(BBBParticipantImpl.class.getName());
		}

		EntityCacheUtil.clearCache(BBBParticipantImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the b b b participant.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(BBBParticipant bbbParticipant) {
		EntityCacheUtil.removeResult(BBBParticipantModelImpl.ENTITY_CACHE_ENABLED,
			BBBParticipantImpl.class, bbbParticipant.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(bbbParticipant);
	}

	@Override
	public void clearCache(List<BBBParticipant> bbbParticipants) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (BBBParticipant bbbParticipant : bbbParticipants) {
			EntityCacheUtil.removeResult(BBBParticipantModelImpl.ENTITY_CACHE_ENABLED,
				BBBParticipantImpl.class, bbbParticipant.getPrimaryKey());

			clearUniqueFindersCache(bbbParticipant);
		}
	}

	protected void cacheUniqueFindersCache(BBBParticipant bbbParticipant) {
		if (bbbParticipant.isNew()) {
			Object[] args = new Object[] {
					bbbParticipant.getBbbMeetingId(),
					bbbParticipant.getEmailAddress()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_BMI_EA, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_BMI_EA, args,
				bbbParticipant);
		}
		else {
			BBBParticipantModelImpl bbbParticipantModelImpl = (BBBParticipantModelImpl)bbbParticipant;

			if ((bbbParticipantModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_BMI_EA.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						bbbParticipant.getBbbMeetingId(),
						bbbParticipant.getEmailAddress()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_BMI_EA, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_BMI_EA, args,
					bbbParticipant);
			}
		}
	}

	protected void clearUniqueFindersCache(BBBParticipant bbbParticipant) {
		BBBParticipantModelImpl bbbParticipantModelImpl = (BBBParticipantModelImpl)bbbParticipant;

		Object[] args = new Object[] {
				bbbParticipant.getBbbMeetingId(),
				bbbParticipant.getEmailAddress()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BMI_EA, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_BMI_EA, args);

		if ((bbbParticipantModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_BMI_EA.getColumnBitmask()) != 0) {
			args = new Object[] {
					bbbParticipantModelImpl.getOriginalBbbMeetingId(),
					bbbParticipantModelImpl.getOriginalEmailAddress()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BMI_EA, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_BMI_EA, args);
		}
	}

	/**
	 * Creates a new b b b participant with the primary key. Does not add the b b b participant to the database.
	 *
	 * @param bbbParticipantId the primary key for the new b b b participant
	 * @return the new b b b participant
	 */
	@Override
	public BBBParticipant create(long bbbParticipantId) {
		BBBParticipant bbbParticipant = new BBBParticipantImpl();

		bbbParticipant.setNew(true);
		bbbParticipant.setPrimaryKey(bbbParticipantId);

		return bbbParticipant;
	}

	/**
	 * Removes the b b b participant with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param bbbParticipantId the primary key of the b b b participant
	 * @return the b b b participant that was removed
	 * @throws com.liferay.bbb.NoSuchParticipantException if a b b b participant with the primary key could not be found
	 */
	@Override
	public BBBParticipant remove(long bbbParticipantId)
		throws NoSuchParticipantException {
		return remove((Serializable)bbbParticipantId);
	}

	/**
	 * Removes the b b b participant with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the b b b participant
	 * @return the b b b participant that was removed
	 * @throws com.liferay.bbb.NoSuchParticipantException if a b b b participant with the primary key could not be found
	 */
	@Override
	public BBBParticipant remove(Serializable primaryKey)
		throws NoSuchParticipantException {
		Session session = null;

		try {
			session = openSession();

			BBBParticipant bbbParticipant = (BBBParticipant)session.get(BBBParticipantImpl.class,
					primaryKey);

			if (bbbParticipant == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchParticipantException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(bbbParticipant);
		}
		catch (NoSuchParticipantException nsee) {
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
	protected BBBParticipant removeImpl(BBBParticipant bbbParticipant) {
		bbbParticipant = toUnwrappedModel(bbbParticipant);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(bbbParticipant)) {
				bbbParticipant = (BBBParticipant)session.get(BBBParticipantImpl.class,
						bbbParticipant.getPrimaryKeyObj());
			}

			if (bbbParticipant != null) {
				session.delete(bbbParticipant);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (bbbParticipant != null) {
			clearCache(bbbParticipant);
		}

		return bbbParticipant;
	}

	@Override
	public BBBParticipant updateImpl(
		com.liferay.bbb.model.BBBParticipant bbbParticipant) {
		bbbParticipant = toUnwrappedModel(bbbParticipant);

		boolean isNew = bbbParticipant.isNew();

		BBBParticipantModelImpl bbbParticipantModelImpl = (BBBParticipantModelImpl)bbbParticipant;

		Session session = null;

		try {
			session = openSession();

			if (bbbParticipant.isNew()) {
				session.save(bbbParticipant);

				bbbParticipant.setNew(false);
			}
			else {
				session.merge(bbbParticipant);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !BBBParticipantModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((bbbParticipantModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BBBMEETINGID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						bbbParticipantModelImpl.getOriginalBbbMeetingId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BBBMEETINGID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BBBMEETINGID,
					args);

				args = new Object[] { bbbParticipantModelImpl.getBbbMeetingId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BBBMEETINGID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BBBMEETINGID,
					args);
			}
		}

		EntityCacheUtil.putResult(BBBParticipantModelImpl.ENTITY_CACHE_ENABLED,
			BBBParticipantImpl.class, bbbParticipant.getPrimaryKey(),
			bbbParticipant, false);

		clearUniqueFindersCache(bbbParticipant);
		cacheUniqueFindersCache(bbbParticipant);

		bbbParticipant.resetOriginalValues();

		return bbbParticipant;
	}

	protected BBBParticipant toUnwrappedModel(BBBParticipant bbbParticipant) {
		if (bbbParticipant instanceof BBBParticipantImpl) {
			return bbbParticipant;
		}

		BBBParticipantImpl bbbParticipantImpl = new BBBParticipantImpl();

		bbbParticipantImpl.setNew(bbbParticipant.isNew());
		bbbParticipantImpl.setPrimaryKey(bbbParticipant.getPrimaryKey());

		bbbParticipantImpl.setBbbParticipantId(bbbParticipant.getBbbParticipantId());
		bbbParticipantImpl.setGroupId(bbbParticipant.getGroupId());
		bbbParticipantImpl.setCompanyId(bbbParticipant.getCompanyId());
		bbbParticipantImpl.setUserId(bbbParticipant.getUserId());
		bbbParticipantImpl.setUserName(bbbParticipant.getUserName());
		bbbParticipantImpl.setCreateDate(bbbParticipant.getCreateDate());
		bbbParticipantImpl.setModifiedDate(bbbParticipant.getModifiedDate());
		bbbParticipantImpl.setBbbMeetingId(bbbParticipant.getBbbMeetingId());
		bbbParticipantImpl.setName(bbbParticipant.getName());
		bbbParticipantImpl.setEmailAddress(bbbParticipant.getEmailAddress());
		bbbParticipantImpl.setType(bbbParticipant.getType());
		bbbParticipantImpl.setStatus(bbbParticipant.getStatus());

		return bbbParticipantImpl;
	}

	/**
	 * Returns the b b b participant with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the b b b participant
	 * @return the b b b participant
	 * @throws com.liferay.bbb.NoSuchParticipantException if a b b b participant with the primary key could not be found
	 */
	@Override
	public BBBParticipant findByPrimaryKey(Serializable primaryKey)
		throws NoSuchParticipantException {
		BBBParticipant bbbParticipant = fetchByPrimaryKey(primaryKey);

		if (bbbParticipant == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchParticipantException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return bbbParticipant;
	}

	/**
	 * Returns the b b b participant with the primary key or throws a {@link com.liferay.bbb.NoSuchParticipantException} if it could not be found.
	 *
	 * @param bbbParticipantId the primary key of the b b b participant
	 * @return the b b b participant
	 * @throws com.liferay.bbb.NoSuchParticipantException if a b b b participant with the primary key could not be found
	 */
	@Override
	public BBBParticipant findByPrimaryKey(long bbbParticipantId)
		throws NoSuchParticipantException {
		return findByPrimaryKey((Serializable)bbbParticipantId);
	}

	/**
	 * Returns the b b b participant with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the b b b participant
	 * @return the b b b participant, or <code>null</code> if a b b b participant with the primary key could not be found
	 */
	@Override
	public BBBParticipant fetchByPrimaryKey(Serializable primaryKey) {
		BBBParticipant bbbParticipant = (BBBParticipant)EntityCacheUtil.getResult(BBBParticipantModelImpl.ENTITY_CACHE_ENABLED,
				BBBParticipantImpl.class, primaryKey);

		if (bbbParticipant == _nullBBBParticipant) {
			return null;
		}

		if (bbbParticipant == null) {
			Session session = null;

			try {
				session = openSession();

				bbbParticipant = (BBBParticipant)session.get(BBBParticipantImpl.class,
						primaryKey);

				if (bbbParticipant != null) {
					cacheResult(bbbParticipant);
				}
				else {
					EntityCacheUtil.putResult(BBBParticipantModelImpl.ENTITY_CACHE_ENABLED,
						BBBParticipantImpl.class, primaryKey,
						_nullBBBParticipant);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(BBBParticipantModelImpl.ENTITY_CACHE_ENABLED,
					BBBParticipantImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return bbbParticipant;
	}

	/**
	 * Returns the b b b participant with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param bbbParticipantId the primary key of the b b b participant
	 * @return the b b b participant, or <code>null</code> if a b b b participant with the primary key could not be found
	 */
	@Override
	public BBBParticipant fetchByPrimaryKey(long bbbParticipantId) {
		return fetchByPrimaryKey((Serializable)bbbParticipantId);
	}

	@Override
	public Map<Serializable, BBBParticipant> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, BBBParticipant> map = new HashMap<Serializable, BBBParticipant>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			BBBParticipant bbbParticipant = fetchByPrimaryKey(primaryKey);

			if (bbbParticipant != null) {
				map.put(primaryKey, bbbParticipant);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			BBBParticipant bbbParticipant = (BBBParticipant)EntityCacheUtil.getResult(BBBParticipantModelImpl.ENTITY_CACHE_ENABLED,
					BBBParticipantImpl.class, primaryKey);

			if (bbbParticipant == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, bbbParticipant);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_BBBPARTICIPANT_WHERE_PKS_IN);

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

			for (BBBParticipant bbbParticipant : (List<BBBParticipant>)q.list()) {
				map.put(bbbParticipant.getPrimaryKeyObj(), bbbParticipant);

				cacheResult(bbbParticipant);

				uncachedPrimaryKeys.remove(bbbParticipant.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(BBBParticipantModelImpl.ENTITY_CACHE_ENABLED,
					BBBParticipantImpl.class, primaryKey, _nullBBBParticipant);
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
	 * Returns all the b b b participants.
	 *
	 * @return the b b b participants
	 */
	@Override
	public List<BBBParticipant> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the b b b participants.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBParticipantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of b b b participants
	 * @param end the upper bound of the range of b b b participants (not inclusive)
	 * @return the range of b b b participants
	 */
	@Override
	public List<BBBParticipant> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the b b b participants.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBParticipantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of b b b participants
	 * @param end the upper bound of the range of b b b participants (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of b b b participants
	 */
	@Override
	public List<BBBParticipant> findAll(int start, int end,
		OrderByComparator<BBBParticipant> orderByComparator) {
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

		List<BBBParticipant> list = (List<BBBParticipant>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_BBBPARTICIPANT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_BBBPARTICIPANT;

				if (pagination) {
					sql = sql.concat(BBBParticipantModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<BBBParticipant>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BBBParticipant>)QueryUtil.list(q,
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
	 * Removes all the b b b participants from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (BBBParticipant bbbParticipant : findAll()) {
			remove(bbbParticipant);
		}
	}

	/**
	 * Returns the number of b b b participants.
	 *
	 * @return the number of b b b participants
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_BBBPARTICIPANT);

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
	 * Initializes the b b b participant persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.bbb.model.BBBParticipant")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<BBBParticipant>> listenersList = new ArrayList<ModelListener<BBBParticipant>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<BBBParticipant>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(BBBParticipantImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_BBBPARTICIPANT = "SELECT bbbParticipant FROM BBBParticipant bbbParticipant";
	private static final String _SQL_SELECT_BBBPARTICIPANT_WHERE_PKS_IN = "SELECT bbbParticipant FROM BBBParticipant bbbParticipant WHERE bbbParticipantId IN (";
	private static final String _SQL_SELECT_BBBPARTICIPANT_WHERE = "SELECT bbbParticipant FROM BBBParticipant bbbParticipant WHERE ";
	private static final String _SQL_COUNT_BBBPARTICIPANT = "SELECT COUNT(bbbParticipant) FROM BBBParticipant bbbParticipant";
	private static final String _SQL_COUNT_BBBPARTICIPANT_WHERE = "SELECT COUNT(bbbParticipant) FROM BBBParticipant bbbParticipant WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "bbbParticipant.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No BBBParticipant exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No BBBParticipant exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(BBBParticipantPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
	private static BBBParticipant _nullBBBParticipant = new BBBParticipantImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<BBBParticipant> toCacheModel() {
				return _nullBBBParticipantCacheModel;
			}
		};

	private static CacheModel<BBBParticipant> _nullBBBParticipantCacheModel = new CacheModel<BBBParticipant>() {
			@Override
			public BBBParticipant toEntityModel() {
				return _nullBBBParticipant;
			}
		};
}