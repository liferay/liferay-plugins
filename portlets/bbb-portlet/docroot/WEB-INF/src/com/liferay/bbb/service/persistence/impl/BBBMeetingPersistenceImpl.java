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

import com.liferay.bbb.NoSuchMeetingException;
import com.liferay.bbb.model.BBBMeeting;
import com.liferay.bbb.model.impl.BBBMeetingImpl;
import com.liferay.bbb.model.impl.BBBMeetingModelImpl;
import com.liferay.bbb.service.persistence.BBBMeetingPersistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
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
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
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
 * The persistence implementation for the b b b meeting service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Shinn Lok
 * @see BBBMeetingPersistence
 * @see BBBMeetingUtil
 * @generated
 */
public class BBBMeetingPersistenceImpl extends BasePersistenceImpl<BBBMeeting>
	implements BBBMeetingPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link BBBMeetingUtil} to access the b b b meeting persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = BBBMeetingImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BBBMeetingModelImpl.ENTITY_CACHE_ENABLED,
			BBBMeetingModelImpl.FINDER_CACHE_ENABLED, BBBMeetingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BBBMeetingModelImpl.ENTITY_CACHE_ENABLED,
			BBBMeetingModelImpl.FINDER_CACHE_ENABLED, BBBMeetingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BBBMeetingModelImpl.ENTITY_CACHE_ENABLED,
			BBBMeetingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(BBBMeetingModelImpl.ENTITY_CACHE_ENABLED,
			BBBMeetingModelImpl.FINDER_CACHE_ENABLED, BBBMeetingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(BBBMeetingModelImpl.ENTITY_CACHE_ENABLED,
			BBBMeetingModelImpl.FINDER_CACHE_ENABLED, BBBMeetingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			BBBMeetingModelImpl.GROUPID_COLUMN_BITMASK |
			BBBMeetingModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(BBBMeetingModelImpl.ENTITY_CACHE_ENABLED,
			BBBMeetingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the b b b meetings where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching b b b meetings
	 */
	@Override
	public List<BBBMeeting> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the b b b meetings where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of b b b meetings
	 * @param end the upper bound of the range of b b b meetings (not inclusive)
	 * @return the range of matching b b b meetings
	 */
	@Override
	public List<BBBMeeting> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the b b b meetings where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of b b b meetings
	 * @param end the upper bound of the range of b b b meetings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching b b b meetings
	 */
	@Override
	public List<BBBMeeting> findByGroupId(long groupId, int start, int end,
		OrderByComparator<BBBMeeting> orderByComparator) {
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

		List<BBBMeeting> list = (List<BBBMeeting>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BBBMeeting bbbMeeting : list) {
				if ((groupId != bbbMeeting.getGroupId())) {
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

			query.append(_SQL_SELECT_BBBMEETING_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BBBMeetingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<BBBMeeting>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BBBMeeting>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first b b b meeting in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching b b b meeting
	 * @throws com.liferay.bbb.NoSuchMeetingException if a matching b b b meeting could not be found
	 */
	@Override
	public BBBMeeting findByGroupId_First(long groupId,
		OrderByComparator<BBBMeeting> orderByComparator)
		throws NoSuchMeetingException {
		BBBMeeting bbbMeeting = fetchByGroupId_First(groupId, orderByComparator);

		if (bbbMeeting != null) {
			return bbbMeeting;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMeetingException(msg.toString());
	}

	/**
	 * Returns the first b b b meeting in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching b b b meeting, or <code>null</code> if a matching b b b meeting could not be found
	 */
	@Override
	public BBBMeeting fetchByGroupId_First(long groupId,
		OrderByComparator<BBBMeeting> orderByComparator) {
		List<BBBMeeting> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last b b b meeting in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching b b b meeting
	 * @throws com.liferay.bbb.NoSuchMeetingException if a matching b b b meeting could not be found
	 */
	@Override
	public BBBMeeting findByGroupId_Last(long groupId,
		OrderByComparator<BBBMeeting> orderByComparator)
		throws NoSuchMeetingException {
		BBBMeeting bbbMeeting = fetchByGroupId_Last(groupId, orderByComparator);

		if (bbbMeeting != null) {
			return bbbMeeting;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMeetingException(msg.toString());
	}

	/**
	 * Returns the last b b b meeting in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching b b b meeting, or <code>null</code> if a matching b b b meeting could not be found
	 */
	@Override
	public BBBMeeting fetchByGroupId_Last(long groupId,
		OrderByComparator<BBBMeeting> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<BBBMeeting> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the b b b meetings before and after the current b b b meeting in the ordered set where groupId = &#63;.
	 *
	 * @param bbbMeetingId the primary key of the current b b b meeting
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next b b b meeting
	 * @throws com.liferay.bbb.NoSuchMeetingException if a b b b meeting with the primary key could not be found
	 */
	@Override
	public BBBMeeting[] findByGroupId_PrevAndNext(long bbbMeetingId,
		long groupId, OrderByComparator<BBBMeeting> orderByComparator)
		throws NoSuchMeetingException {
		BBBMeeting bbbMeeting = findByPrimaryKey(bbbMeetingId);

		Session session = null;

		try {
			session = openSession();

			BBBMeeting[] array = new BBBMeetingImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, bbbMeeting, groupId,
					orderByComparator, true);

			array[1] = bbbMeeting;

			array[2] = getByGroupId_PrevAndNext(session, bbbMeeting, groupId,
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

	protected BBBMeeting getByGroupId_PrevAndNext(Session session,
		BBBMeeting bbbMeeting, long groupId,
		OrderByComparator<BBBMeeting> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BBBMEETING_WHERE);

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
			query.append(BBBMeetingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(bbbMeeting);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BBBMeeting> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the b b b meetings that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching b b b meetings that the user has permission to view
	 */
	@Override
	public List<BBBMeeting> filterFindByGroupId(long groupId) {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the b b b meetings that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of b b b meetings
	 * @param end the upper bound of the range of b b b meetings (not inclusive)
	 * @return the range of matching b b b meetings that the user has permission to view
	 */
	@Override
	public List<BBBMeeting> filterFindByGroupId(long groupId, int start, int end) {
		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the b b b meetings that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of b b b meetings
	 * @param end the upper bound of the range of b b b meetings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching b b b meetings that the user has permission to view
	 */
	@Override
	public List<BBBMeeting> filterFindByGroupId(long groupId, int start,
		int end, OrderByComparator<BBBMeeting> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId(groupId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_BBBMEETING_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_BBBMEETING_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_BBBMEETING_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(BBBMeetingModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(BBBMeetingModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				BBBMeeting.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, BBBMeetingImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, BBBMeetingImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<BBBMeeting>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the b b b meetings before and after the current b b b meeting in the ordered set of b b b meetings that the user has permission to view where groupId = &#63;.
	 *
	 * @param bbbMeetingId the primary key of the current b b b meeting
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next b b b meeting
	 * @throws com.liferay.bbb.NoSuchMeetingException if a b b b meeting with the primary key could not be found
	 */
	@Override
	public BBBMeeting[] filterFindByGroupId_PrevAndNext(long bbbMeetingId,
		long groupId, OrderByComparator<BBBMeeting> orderByComparator)
		throws NoSuchMeetingException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(bbbMeetingId, groupId,
				orderByComparator);
		}

		BBBMeeting bbbMeeting = findByPrimaryKey(bbbMeetingId);

		Session session = null;

		try {
			session = openSession();

			BBBMeeting[] array = new BBBMeetingImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session, bbbMeeting,
					groupId, orderByComparator, true);

			array[1] = bbbMeeting;

			array[2] = filterGetByGroupId_PrevAndNext(session, bbbMeeting,
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

	protected BBBMeeting filterGetByGroupId_PrevAndNext(Session session,
		BBBMeeting bbbMeeting, long groupId,
		OrderByComparator<BBBMeeting> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_BBBMEETING_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_BBBMEETING_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_BBBMEETING_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(BBBMeetingModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(BBBMeetingModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				BBBMeeting.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, BBBMeetingImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, BBBMeetingImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(bbbMeeting);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BBBMeeting> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the b b b meetings where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (BBBMeeting bbbMeeting : findByGroupId(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(bbbMeeting);
		}
	}

	/**
	 * Returns the number of b b b meetings where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching b b b meetings
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BBBMEETING_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

	/**
	 * Returns the number of b b b meetings that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching b b b meetings that the user has permission to view
	 */
	@Override
	public int filterCountByGroupId(long groupId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_BBBMEETING_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				BBBMeeting.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "bbbMeeting.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_BBBSERVERID =
		new FinderPath(BBBMeetingModelImpl.ENTITY_CACHE_ENABLED,
			BBBMeetingModelImpl.FINDER_CACHE_ENABLED, BBBMeetingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByBbbServerId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BBBSERVERID =
		new FinderPath(BBBMeetingModelImpl.ENTITY_CACHE_ENABLED,
			BBBMeetingModelImpl.FINDER_CACHE_ENABLED, BBBMeetingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByBbbServerId",
			new String[] { Long.class.getName() },
			BBBMeetingModelImpl.BBBSERVERID_COLUMN_BITMASK |
			BBBMeetingModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_BBBSERVERID = new FinderPath(BBBMeetingModelImpl.ENTITY_CACHE_ENABLED,
			BBBMeetingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByBbbServerId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the b b b meetings where bbbServerId = &#63;.
	 *
	 * @param bbbServerId the bbb server ID
	 * @return the matching b b b meetings
	 */
	@Override
	public List<BBBMeeting> findByBbbServerId(long bbbServerId) {
		return findByBbbServerId(bbbServerId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the b b b meetings where bbbServerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param bbbServerId the bbb server ID
	 * @param start the lower bound of the range of b b b meetings
	 * @param end the upper bound of the range of b b b meetings (not inclusive)
	 * @return the range of matching b b b meetings
	 */
	@Override
	public List<BBBMeeting> findByBbbServerId(long bbbServerId, int start,
		int end) {
		return findByBbbServerId(bbbServerId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the b b b meetings where bbbServerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param bbbServerId the bbb server ID
	 * @param start the lower bound of the range of b b b meetings
	 * @param end the upper bound of the range of b b b meetings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching b b b meetings
	 */
	@Override
	public List<BBBMeeting> findByBbbServerId(long bbbServerId, int start,
		int end, OrderByComparator<BBBMeeting> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BBBSERVERID;
			finderArgs = new Object[] { bbbServerId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_BBBSERVERID;
			finderArgs = new Object[] { bbbServerId, start, end, orderByComparator };
		}

		List<BBBMeeting> list = (List<BBBMeeting>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BBBMeeting bbbMeeting : list) {
				if ((bbbServerId != bbbMeeting.getBbbServerId())) {
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

			query.append(_SQL_SELECT_BBBMEETING_WHERE);

			query.append(_FINDER_COLUMN_BBBSERVERID_BBBSERVERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BBBMeetingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(bbbServerId);

				if (!pagination) {
					list = (List<BBBMeeting>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BBBMeeting>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first b b b meeting in the ordered set where bbbServerId = &#63;.
	 *
	 * @param bbbServerId the bbb server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching b b b meeting
	 * @throws com.liferay.bbb.NoSuchMeetingException if a matching b b b meeting could not be found
	 */
	@Override
	public BBBMeeting findByBbbServerId_First(long bbbServerId,
		OrderByComparator<BBBMeeting> orderByComparator)
		throws NoSuchMeetingException {
		BBBMeeting bbbMeeting = fetchByBbbServerId_First(bbbServerId,
				orderByComparator);

		if (bbbMeeting != null) {
			return bbbMeeting;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("bbbServerId=");
		msg.append(bbbServerId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMeetingException(msg.toString());
	}

	/**
	 * Returns the first b b b meeting in the ordered set where bbbServerId = &#63;.
	 *
	 * @param bbbServerId the bbb server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching b b b meeting, or <code>null</code> if a matching b b b meeting could not be found
	 */
	@Override
	public BBBMeeting fetchByBbbServerId_First(long bbbServerId,
		OrderByComparator<BBBMeeting> orderByComparator) {
		List<BBBMeeting> list = findByBbbServerId(bbbServerId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last b b b meeting in the ordered set where bbbServerId = &#63;.
	 *
	 * @param bbbServerId the bbb server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching b b b meeting
	 * @throws com.liferay.bbb.NoSuchMeetingException if a matching b b b meeting could not be found
	 */
	@Override
	public BBBMeeting findByBbbServerId_Last(long bbbServerId,
		OrderByComparator<BBBMeeting> orderByComparator)
		throws NoSuchMeetingException {
		BBBMeeting bbbMeeting = fetchByBbbServerId_Last(bbbServerId,
				orderByComparator);

		if (bbbMeeting != null) {
			return bbbMeeting;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("bbbServerId=");
		msg.append(bbbServerId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMeetingException(msg.toString());
	}

	/**
	 * Returns the last b b b meeting in the ordered set where bbbServerId = &#63;.
	 *
	 * @param bbbServerId the bbb server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching b b b meeting, or <code>null</code> if a matching b b b meeting could not be found
	 */
	@Override
	public BBBMeeting fetchByBbbServerId_Last(long bbbServerId,
		OrderByComparator<BBBMeeting> orderByComparator) {
		int count = countByBbbServerId(bbbServerId);

		if (count == 0) {
			return null;
		}

		List<BBBMeeting> list = findByBbbServerId(bbbServerId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the b b b meetings before and after the current b b b meeting in the ordered set where bbbServerId = &#63;.
	 *
	 * @param bbbMeetingId the primary key of the current b b b meeting
	 * @param bbbServerId the bbb server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next b b b meeting
	 * @throws com.liferay.bbb.NoSuchMeetingException if a b b b meeting with the primary key could not be found
	 */
	@Override
	public BBBMeeting[] findByBbbServerId_PrevAndNext(long bbbMeetingId,
		long bbbServerId, OrderByComparator<BBBMeeting> orderByComparator)
		throws NoSuchMeetingException {
		BBBMeeting bbbMeeting = findByPrimaryKey(bbbMeetingId);

		Session session = null;

		try {
			session = openSession();

			BBBMeeting[] array = new BBBMeetingImpl[3];

			array[0] = getByBbbServerId_PrevAndNext(session, bbbMeeting,
					bbbServerId, orderByComparator, true);

			array[1] = bbbMeeting;

			array[2] = getByBbbServerId_PrevAndNext(session, bbbMeeting,
					bbbServerId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BBBMeeting getByBbbServerId_PrevAndNext(Session session,
		BBBMeeting bbbMeeting, long bbbServerId,
		OrderByComparator<BBBMeeting> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BBBMEETING_WHERE);

		query.append(_FINDER_COLUMN_BBBSERVERID_BBBSERVERID_2);

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
			query.append(BBBMeetingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(bbbServerId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(bbbMeeting);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BBBMeeting> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the b b b meetings where bbbServerId = &#63; from the database.
	 *
	 * @param bbbServerId the bbb server ID
	 */
	@Override
	public void removeByBbbServerId(long bbbServerId) {
		for (BBBMeeting bbbMeeting : findByBbbServerId(bbbServerId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(bbbMeeting);
		}
	}

	/**
	 * Returns the number of b b b meetings where bbbServerId = &#63;.
	 *
	 * @param bbbServerId the bbb server ID
	 * @return the number of matching b b b meetings
	 */
	@Override
	public int countByBbbServerId(long bbbServerId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_BBBSERVERID;

		Object[] finderArgs = new Object[] { bbbServerId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BBBMEETING_WHERE);

			query.append(_FINDER_COLUMN_BBBSERVERID_BBBSERVERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(bbbServerId);

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

	private static final String _FINDER_COLUMN_BBBSERVERID_BBBSERVERID_2 = "bbbMeeting.bbbServerId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS = new FinderPath(BBBMeetingModelImpl.ENTITY_CACHE_ENABLED,
			BBBMeetingModelImpl.FINDER_CACHE_ENABLED, BBBMeetingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStatus",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS =
		new FinderPath(BBBMeetingModelImpl.ENTITY_CACHE_ENABLED,
			BBBMeetingModelImpl.FINDER_CACHE_ENABLED, BBBMeetingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStatus",
			new String[] { Integer.class.getName() },
			BBBMeetingModelImpl.STATUS_COLUMN_BITMASK |
			BBBMeetingModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_STATUS = new FinderPath(BBBMeetingModelImpl.ENTITY_CACHE_ENABLED,
			BBBMeetingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStatus",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the b b b meetings where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching b b b meetings
	 */
	@Override
	public List<BBBMeeting> findByStatus(int status) {
		return findByStatus(status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the b b b meetings where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of b b b meetings
	 * @param end the upper bound of the range of b b b meetings (not inclusive)
	 * @return the range of matching b b b meetings
	 */
	@Override
	public List<BBBMeeting> findByStatus(int status, int start, int end) {
		return findByStatus(status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the b b b meetings where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of b b b meetings
	 * @param end the upper bound of the range of b b b meetings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching b b b meetings
	 */
	@Override
	public List<BBBMeeting> findByStatus(int status, int start, int end,
		OrderByComparator<BBBMeeting> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS;
			finderArgs = new Object[] { status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_STATUS;
			finderArgs = new Object[] { status, start, end, orderByComparator };
		}

		List<BBBMeeting> list = (List<BBBMeeting>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BBBMeeting bbbMeeting : list) {
				if ((status != bbbMeeting.getStatus())) {
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

			query.append(_SQL_SELECT_BBBMEETING_WHERE);

			query.append(_FINDER_COLUMN_STATUS_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BBBMeetingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(status);

				if (!pagination) {
					list = (List<BBBMeeting>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BBBMeeting>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first b b b meeting in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching b b b meeting
	 * @throws com.liferay.bbb.NoSuchMeetingException if a matching b b b meeting could not be found
	 */
	@Override
	public BBBMeeting findByStatus_First(int status,
		OrderByComparator<BBBMeeting> orderByComparator)
		throws NoSuchMeetingException {
		BBBMeeting bbbMeeting = fetchByStatus_First(status, orderByComparator);

		if (bbbMeeting != null) {
			return bbbMeeting;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMeetingException(msg.toString());
	}

	/**
	 * Returns the first b b b meeting in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching b b b meeting, or <code>null</code> if a matching b b b meeting could not be found
	 */
	@Override
	public BBBMeeting fetchByStatus_First(int status,
		OrderByComparator<BBBMeeting> orderByComparator) {
		List<BBBMeeting> list = findByStatus(status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last b b b meeting in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching b b b meeting
	 * @throws com.liferay.bbb.NoSuchMeetingException if a matching b b b meeting could not be found
	 */
	@Override
	public BBBMeeting findByStatus_Last(int status,
		OrderByComparator<BBBMeeting> orderByComparator)
		throws NoSuchMeetingException {
		BBBMeeting bbbMeeting = fetchByStatus_Last(status, orderByComparator);

		if (bbbMeeting != null) {
			return bbbMeeting;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMeetingException(msg.toString());
	}

	/**
	 * Returns the last b b b meeting in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching b b b meeting, or <code>null</code> if a matching b b b meeting could not be found
	 */
	@Override
	public BBBMeeting fetchByStatus_Last(int status,
		OrderByComparator<BBBMeeting> orderByComparator) {
		int count = countByStatus(status);

		if (count == 0) {
			return null;
		}

		List<BBBMeeting> list = findByStatus(status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the b b b meetings before and after the current b b b meeting in the ordered set where status = &#63;.
	 *
	 * @param bbbMeetingId the primary key of the current b b b meeting
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next b b b meeting
	 * @throws com.liferay.bbb.NoSuchMeetingException if a b b b meeting with the primary key could not be found
	 */
	@Override
	public BBBMeeting[] findByStatus_PrevAndNext(long bbbMeetingId, int status,
		OrderByComparator<BBBMeeting> orderByComparator)
		throws NoSuchMeetingException {
		BBBMeeting bbbMeeting = findByPrimaryKey(bbbMeetingId);

		Session session = null;

		try {
			session = openSession();

			BBBMeeting[] array = new BBBMeetingImpl[3];

			array[0] = getByStatus_PrevAndNext(session, bbbMeeting, status,
					orderByComparator, true);

			array[1] = bbbMeeting;

			array[2] = getByStatus_PrevAndNext(session, bbbMeeting, status,
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

	protected BBBMeeting getByStatus_PrevAndNext(Session session,
		BBBMeeting bbbMeeting, int status,
		OrderByComparator<BBBMeeting> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BBBMEETING_WHERE);

		query.append(_FINDER_COLUMN_STATUS_STATUS_2);

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
			query.append(BBBMeetingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(bbbMeeting);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BBBMeeting> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the b b b meetings where status = &#63; from the database.
	 *
	 * @param status the status
	 */
	@Override
	public void removeByStatus(int status) {
		for (BBBMeeting bbbMeeting : findByStatus(status, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(bbbMeeting);
		}
	}

	/**
	 * Returns the number of b b b meetings where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching b b b meetings
	 */
	@Override
	public int countByStatus(int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_STATUS;

		Object[] finderArgs = new Object[] { status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BBBMEETING_WHERE);

			query.append(_FINDER_COLUMN_STATUS_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_STATUS_STATUS_2 = "bbbMeeting.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_BSI_S = new FinderPath(BBBMeetingModelImpl.ENTITY_CACHE_ENABLED,
			BBBMeetingModelImpl.FINDER_CACHE_ENABLED, BBBMeetingImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByBSI_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BSI_S = new FinderPath(BBBMeetingModelImpl.ENTITY_CACHE_ENABLED,
			BBBMeetingModelImpl.FINDER_CACHE_ENABLED, BBBMeetingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByBSI_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			BBBMeetingModelImpl.BBBSERVERID_COLUMN_BITMASK |
			BBBMeetingModelImpl.STATUS_COLUMN_BITMASK |
			BBBMeetingModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_BSI_S = new FinderPath(BBBMeetingModelImpl.ENTITY_CACHE_ENABLED,
			BBBMeetingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByBSI_S",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the b b b meetings where bbbServerId = &#63; and status = &#63;.
	 *
	 * @param bbbServerId the bbb server ID
	 * @param status the status
	 * @return the matching b b b meetings
	 */
	@Override
	public List<BBBMeeting> findByBSI_S(long bbbServerId, int status) {
		return findByBSI_S(bbbServerId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the b b b meetings where bbbServerId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param bbbServerId the bbb server ID
	 * @param status the status
	 * @param start the lower bound of the range of b b b meetings
	 * @param end the upper bound of the range of b b b meetings (not inclusive)
	 * @return the range of matching b b b meetings
	 */
	@Override
	public List<BBBMeeting> findByBSI_S(long bbbServerId, int status,
		int start, int end) {
		return findByBSI_S(bbbServerId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the b b b meetings where bbbServerId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param bbbServerId the bbb server ID
	 * @param status the status
	 * @param start the lower bound of the range of b b b meetings
	 * @param end the upper bound of the range of b b b meetings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching b b b meetings
	 */
	@Override
	public List<BBBMeeting> findByBSI_S(long bbbServerId, int status,
		int start, int end, OrderByComparator<BBBMeeting> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BSI_S;
			finderArgs = new Object[] { bbbServerId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_BSI_S;
			finderArgs = new Object[] {
					bbbServerId, status,
					
					start, end, orderByComparator
				};
		}

		List<BBBMeeting> list = (List<BBBMeeting>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BBBMeeting bbbMeeting : list) {
				if ((bbbServerId != bbbMeeting.getBbbServerId()) ||
						(status != bbbMeeting.getStatus())) {
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

			query.append(_SQL_SELECT_BBBMEETING_WHERE);

			query.append(_FINDER_COLUMN_BSI_S_BBBSERVERID_2);

			query.append(_FINDER_COLUMN_BSI_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BBBMeetingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(bbbServerId);

				qPos.add(status);

				if (!pagination) {
					list = (List<BBBMeeting>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BBBMeeting>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first b b b meeting in the ordered set where bbbServerId = &#63; and status = &#63;.
	 *
	 * @param bbbServerId the bbb server ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching b b b meeting
	 * @throws com.liferay.bbb.NoSuchMeetingException if a matching b b b meeting could not be found
	 */
	@Override
	public BBBMeeting findByBSI_S_First(long bbbServerId, int status,
		OrderByComparator<BBBMeeting> orderByComparator)
		throws NoSuchMeetingException {
		BBBMeeting bbbMeeting = fetchByBSI_S_First(bbbServerId, status,
				orderByComparator);

		if (bbbMeeting != null) {
			return bbbMeeting;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("bbbServerId=");
		msg.append(bbbServerId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMeetingException(msg.toString());
	}

	/**
	 * Returns the first b b b meeting in the ordered set where bbbServerId = &#63; and status = &#63;.
	 *
	 * @param bbbServerId the bbb server ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching b b b meeting, or <code>null</code> if a matching b b b meeting could not be found
	 */
	@Override
	public BBBMeeting fetchByBSI_S_First(long bbbServerId, int status,
		OrderByComparator<BBBMeeting> orderByComparator) {
		List<BBBMeeting> list = findByBSI_S(bbbServerId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last b b b meeting in the ordered set where bbbServerId = &#63; and status = &#63;.
	 *
	 * @param bbbServerId the bbb server ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching b b b meeting
	 * @throws com.liferay.bbb.NoSuchMeetingException if a matching b b b meeting could not be found
	 */
	@Override
	public BBBMeeting findByBSI_S_Last(long bbbServerId, int status,
		OrderByComparator<BBBMeeting> orderByComparator)
		throws NoSuchMeetingException {
		BBBMeeting bbbMeeting = fetchByBSI_S_Last(bbbServerId, status,
				orderByComparator);

		if (bbbMeeting != null) {
			return bbbMeeting;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("bbbServerId=");
		msg.append(bbbServerId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMeetingException(msg.toString());
	}

	/**
	 * Returns the last b b b meeting in the ordered set where bbbServerId = &#63; and status = &#63;.
	 *
	 * @param bbbServerId the bbb server ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching b b b meeting, or <code>null</code> if a matching b b b meeting could not be found
	 */
	@Override
	public BBBMeeting fetchByBSI_S_Last(long bbbServerId, int status,
		OrderByComparator<BBBMeeting> orderByComparator) {
		int count = countByBSI_S(bbbServerId, status);

		if (count == 0) {
			return null;
		}

		List<BBBMeeting> list = findByBSI_S(bbbServerId, status, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the b b b meetings before and after the current b b b meeting in the ordered set where bbbServerId = &#63; and status = &#63;.
	 *
	 * @param bbbMeetingId the primary key of the current b b b meeting
	 * @param bbbServerId the bbb server ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next b b b meeting
	 * @throws com.liferay.bbb.NoSuchMeetingException if a b b b meeting with the primary key could not be found
	 */
	@Override
	public BBBMeeting[] findByBSI_S_PrevAndNext(long bbbMeetingId,
		long bbbServerId, int status,
		OrderByComparator<BBBMeeting> orderByComparator)
		throws NoSuchMeetingException {
		BBBMeeting bbbMeeting = findByPrimaryKey(bbbMeetingId);

		Session session = null;

		try {
			session = openSession();

			BBBMeeting[] array = new BBBMeetingImpl[3];

			array[0] = getByBSI_S_PrevAndNext(session, bbbMeeting, bbbServerId,
					status, orderByComparator, true);

			array[1] = bbbMeeting;

			array[2] = getByBSI_S_PrevAndNext(session, bbbMeeting, bbbServerId,
					status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BBBMeeting getByBSI_S_PrevAndNext(Session session,
		BBBMeeting bbbMeeting, long bbbServerId, int status,
		OrderByComparator<BBBMeeting> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BBBMEETING_WHERE);

		query.append(_FINDER_COLUMN_BSI_S_BBBSERVERID_2);

		query.append(_FINDER_COLUMN_BSI_S_STATUS_2);

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
			query.append(BBBMeetingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(bbbServerId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(bbbMeeting);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BBBMeeting> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the b b b meetings where bbbServerId = &#63; and status = &#63; from the database.
	 *
	 * @param bbbServerId the bbb server ID
	 * @param status the status
	 */
	@Override
	public void removeByBSI_S(long bbbServerId, int status) {
		for (BBBMeeting bbbMeeting : findByBSI_S(bbbServerId, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(bbbMeeting);
		}
	}

	/**
	 * Returns the number of b b b meetings where bbbServerId = &#63; and status = &#63;.
	 *
	 * @param bbbServerId the bbb server ID
	 * @param status the status
	 * @return the number of matching b b b meetings
	 */
	@Override
	public int countByBSI_S(long bbbServerId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_BSI_S;

		Object[] finderArgs = new Object[] { bbbServerId, status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_BBBMEETING_WHERE);

			query.append(_FINDER_COLUMN_BSI_S_BBBSERVERID_2);

			query.append(_FINDER_COLUMN_BSI_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(bbbServerId);

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_BSI_S_BBBSERVERID_2 = "bbbMeeting.bbbServerId = ? AND ";
	private static final String _FINDER_COLUMN_BSI_S_STATUS_2 = "bbbMeeting.status = ?";

	public BBBMeetingPersistenceImpl() {
		setModelClass(BBBMeeting.class);
	}

	/**
	 * Caches the b b b meeting in the entity cache if it is enabled.
	 *
	 * @param bbbMeeting the b b b meeting
	 */
	@Override
	public void cacheResult(BBBMeeting bbbMeeting) {
		EntityCacheUtil.putResult(BBBMeetingModelImpl.ENTITY_CACHE_ENABLED,
			BBBMeetingImpl.class, bbbMeeting.getPrimaryKey(), bbbMeeting);

		bbbMeeting.resetOriginalValues();
	}

	/**
	 * Caches the b b b meetings in the entity cache if it is enabled.
	 *
	 * @param bbbMeetings the b b b meetings
	 */
	@Override
	public void cacheResult(List<BBBMeeting> bbbMeetings) {
		for (BBBMeeting bbbMeeting : bbbMeetings) {
			if (EntityCacheUtil.getResult(
						BBBMeetingModelImpl.ENTITY_CACHE_ENABLED,
						BBBMeetingImpl.class, bbbMeeting.getPrimaryKey()) == null) {
				cacheResult(bbbMeeting);
			}
			else {
				bbbMeeting.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all b b b meetings.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(BBBMeetingImpl.class.getName());
		}

		EntityCacheUtil.clearCache(BBBMeetingImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the b b b meeting.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(BBBMeeting bbbMeeting) {
		EntityCacheUtil.removeResult(BBBMeetingModelImpl.ENTITY_CACHE_ENABLED,
			BBBMeetingImpl.class, bbbMeeting.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<BBBMeeting> bbbMeetings) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (BBBMeeting bbbMeeting : bbbMeetings) {
			EntityCacheUtil.removeResult(BBBMeetingModelImpl.ENTITY_CACHE_ENABLED,
				BBBMeetingImpl.class, bbbMeeting.getPrimaryKey());
		}
	}

	/**
	 * Creates a new b b b meeting with the primary key. Does not add the b b b meeting to the database.
	 *
	 * @param bbbMeetingId the primary key for the new b b b meeting
	 * @return the new b b b meeting
	 */
	@Override
	public BBBMeeting create(long bbbMeetingId) {
		BBBMeeting bbbMeeting = new BBBMeetingImpl();

		bbbMeeting.setNew(true);
		bbbMeeting.setPrimaryKey(bbbMeetingId);

		return bbbMeeting;
	}

	/**
	 * Removes the b b b meeting with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param bbbMeetingId the primary key of the b b b meeting
	 * @return the b b b meeting that was removed
	 * @throws com.liferay.bbb.NoSuchMeetingException if a b b b meeting with the primary key could not be found
	 */
	@Override
	public BBBMeeting remove(long bbbMeetingId) throws NoSuchMeetingException {
		return remove((Serializable)bbbMeetingId);
	}

	/**
	 * Removes the b b b meeting with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the b b b meeting
	 * @return the b b b meeting that was removed
	 * @throws com.liferay.bbb.NoSuchMeetingException if a b b b meeting with the primary key could not be found
	 */
	@Override
	public BBBMeeting remove(Serializable primaryKey)
		throws NoSuchMeetingException {
		Session session = null;

		try {
			session = openSession();

			BBBMeeting bbbMeeting = (BBBMeeting)session.get(BBBMeetingImpl.class,
					primaryKey);

			if (bbbMeeting == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMeetingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(bbbMeeting);
		}
		catch (NoSuchMeetingException nsee) {
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
	protected BBBMeeting removeImpl(BBBMeeting bbbMeeting) {
		bbbMeeting = toUnwrappedModel(bbbMeeting);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(bbbMeeting)) {
				bbbMeeting = (BBBMeeting)session.get(BBBMeetingImpl.class,
						bbbMeeting.getPrimaryKeyObj());
			}

			if (bbbMeeting != null) {
				session.delete(bbbMeeting);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (bbbMeeting != null) {
			clearCache(bbbMeeting);
		}

		return bbbMeeting;
	}

	@Override
	public BBBMeeting updateImpl(com.liferay.bbb.model.BBBMeeting bbbMeeting) {
		bbbMeeting = toUnwrappedModel(bbbMeeting);

		boolean isNew = bbbMeeting.isNew();

		BBBMeetingModelImpl bbbMeetingModelImpl = (BBBMeetingModelImpl)bbbMeeting;

		Session session = null;

		try {
			session = openSession();

			if (bbbMeeting.isNew()) {
				session.save(bbbMeeting);

				bbbMeeting.setNew(false);
			}
			else {
				session.merge(bbbMeeting);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !BBBMeetingModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((bbbMeetingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						bbbMeetingModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { bbbMeetingModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((bbbMeetingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BBBSERVERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						bbbMeetingModelImpl.getOriginalBbbServerId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BBBSERVERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BBBSERVERID,
					args);

				args = new Object[] { bbbMeetingModelImpl.getBbbServerId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BBBSERVERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BBBSERVERID,
					args);
			}

			if ((bbbMeetingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						bbbMeetingModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);

				args = new Object[] { bbbMeetingModelImpl.getStatus() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_STATUS, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATUS,
					args);
			}

			if ((bbbMeetingModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BSI_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						bbbMeetingModelImpl.getOriginalBbbServerId(),
						bbbMeetingModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BSI_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BSI_S,
					args);

				args = new Object[] {
						bbbMeetingModelImpl.getBbbServerId(),
						bbbMeetingModelImpl.getStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BSI_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BSI_S,
					args);
			}
		}

		EntityCacheUtil.putResult(BBBMeetingModelImpl.ENTITY_CACHE_ENABLED,
			BBBMeetingImpl.class, bbbMeeting.getPrimaryKey(), bbbMeeting, false);

		bbbMeeting.resetOriginalValues();

		return bbbMeeting;
	}

	protected BBBMeeting toUnwrappedModel(BBBMeeting bbbMeeting) {
		if (bbbMeeting instanceof BBBMeetingImpl) {
			return bbbMeeting;
		}

		BBBMeetingImpl bbbMeetingImpl = new BBBMeetingImpl();

		bbbMeetingImpl.setNew(bbbMeeting.isNew());
		bbbMeetingImpl.setPrimaryKey(bbbMeeting.getPrimaryKey());

		bbbMeetingImpl.setBbbMeetingId(bbbMeeting.getBbbMeetingId());
		bbbMeetingImpl.setGroupId(bbbMeeting.getGroupId());
		bbbMeetingImpl.setCompanyId(bbbMeeting.getCompanyId());
		bbbMeetingImpl.setUserId(bbbMeeting.getUserId());
		bbbMeetingImpl.setUserName(bbbMeeting.getUserName());
		bbbMeetingImpl.setCreateDate(bbbMeeting.getCreateDate());
		bbbMeetingImpl.setModifiedDate(bbbMeeting.getModifiedDate());
		bbbMeetingImpl.setBbbServerId(bbbMeeting.getBbbServerId());
		bbbMeetingImpl.setName(bbbMeeting.getName());
		bbbMeetingImpl.setDescription(bbbMeeting.getDescription());
		bbbMeetingImpl.setAttendeePassword(bbbMeeting.getAttendeePassword());
		bbbMeetingImpl.setModeratorPassword(bbbMeeting.getModeratorPassword());
		bbbMeetingImpl.setStatus(bbbMeeting.getStatus());

		return bbbMeetingImpl;
	}

	/**
	 * Returns the b b b meeting with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the b b b meeting
	 * @return the b b b meeting
	 * @throws com.liferay.bbb.NoSuchMeetingException if a b b b meeting with the primary key could not be found
	 */
	@Override
	public BBBMeeting findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMeetingException {
		BBBMeeting bbbMeeting = fetchByPrimaryKey(primaryKey);

		if (bbbMeeting == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMeetingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return bbbMeeting;
	}

	/**
	 * Returns the b b b meeting with the primary key or throws a {@link com.liferay.bbb.NoSuchMeetingException} if it could not be found.
	 *
	 * @param bbbMeetingId the primary key of the b b b meeting
	 * @return the b b b meeting
	 * @throws com.liferay.bbb.NoSuchMeetingException if a b b b meeting with the primary key could not be found
	 */
	@Override
	public BBBMeeting findByPrimaryKey(long bbbMeetingId)
		throws NoSuchMeetingException {
		return findByPrimaryKey((Serializable)bbbMeetingId);
	}

	/**
	 * Returns the b b b meeting with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the b b b meeting
	 * @return the b b b meeting, or <code>null</code> if a b b b meeting with the primary key could not be found
	 */
	@Override
	public BBBMeeting fetchByPrimaryKey(Serializable primaryKey) {
		BBBMeeting bbbMeeting = (BBBMeeting)EntityCacheUtil.getResult(BBBMeetingModelImpl.ENTITY_CACHE_ENABLED,
				BBBMeetingImpl.class, primaryKey);

		if (bbbMeeting == _nullBBBMeeting) {
			return null;
		}

		if (bbbMeeting == null) {
			Session session = null;

			try {
				session = openSession();

				bbbMeeting = (BBBMeeting)session.get(BBBMeetingImpl.class,
						primaryKey);

				if (bbbMeeting != null) {
					cacheResult(bbbMeeting);
				}
				else {
					EntityCacheUtil.putResult(BBBMeetingModelImpl.ENTITY_CACHE_ENABLED,
						BBBMeetingImpl.class, primaryKey, _nullBBBMeeting);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(BBBMeetingModelImpl.ENTITY_CACHE_ENABLED,
					BBBMeetingImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return bbbMeeting;
	}

	/**
	 * Returns the b b b meeting with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param bbbMeetingId the primary key of the b b b meeting
	 * @return the b b b meeting, or <code>null</code> if a b b b meeting with the primary key could not be found
	 */
	@Override
	public BBBMeeting fetchByPrimaryKey(long bbbMeetingId) {
		return fetchByPrimaryKey((Serializable)bbbMeetingId);
	}

	@Override
	public Map<Serializable, BBBMeeting> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, BBBMeeting> map = new HashMap<Serializable, BBBMeeting>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			BBBMeeting bbbMeeting = fetchByPrimaryKey(primaryKey);

			if (bbbMeeting != null) {
				map.put(primaryKey, bbbMeeting);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			BBBMeeting bbbMeeting = (BBBMeeting)EntityCacheUtil.getResult(BBBMeetingModelImpl.ENTITY_CACHE_ENABLED,
					BBBMeetingImpl.class, primaryKey);

			if (bbbMeeting == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, bbbMeeting);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_BBBMEETING_WHERE_PKS_IN);

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

			for (BBBMeeting bbbMeeting : (List<BBBMeeting>)q.list()) {
				map.put(bbbMeeting.getPrimaryKeyObj(), bbbMeeting);

				cacheResult(bbbMeeting);

				uncachedPrimaryKeys.remove(bbbMeeting.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(BBBMeetingModelImpl.ENTITY_CACHE_ENABLED,
					BBBMeetingImpl.class, primaryKey, _nullBBBMeeting);
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
	 * Returns all the b b b meetings.
	 *
	 * @return the b b b meetings
	 */
	@Override
	public List<BBBMeeting> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the b b b meetings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of b b b meetings
	 * @param end the upper bound of the range of b b b meetings (not inclusive)
	 * @return the range of b b b meetings
	 */
	@Override
	public List<BBBMeeting> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the b b b meetings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of b b b meetings
	 * @param end the upper bound of the range of b b b meetings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of b b b meetings
	 */
	@Override
	public List<BBBMeeting> findAll(int start, int end,
		OrderByComparator<BBBMeeting> orderByComparator) {
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

		List<BBBMeeting> list = (List<BBBMeeting>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_BBBMEETING);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_BBBMEETING;

				if (pagination) {
					sql = sql.concat(BBBMeetingModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<BBBMeeting>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<BBBMeeting>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the b b b meetings from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (BBBMeeting bbbMeeting : findAll()) {
			remove(bbbMeeting);
		}
	}

	/**
	 * Returns the number of b b b meetings.
	 *
	 * @return the number of b b b meetings
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_BBBMEETING);

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
	 * Initializes the b b b meeting persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.bbb.model.BBBMeeting")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<BBBMeeting>> listenersList = new ArrayList<ModelListener<BBBMeeting>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<BBBMeeting>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(BBBMeetingImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_BBBMEETING = "SELECT bbbMeeting FROM BBBMeeting bbbMeeting";
	private static final String _SQL_SELECT_BBBMEETING_WHERE_PKS_IN = "SELECT bbbMeeting FROM BBBMeeting bbbMeeting WHERE bbbMeetingId IN (";
	private static final String _SQL_SELECT_BBBMEETING_WHERE = "SELECT bbbMeeting FROM BBBMeeting bbbMeeting WHERE ";
	private static final String _SQL_COUNT_BBBMEETING = "SELECT COUNT(bbbMeeting) FROM BBBMeeting bbbMeeting";
	private static final String _SQL_COUNT_BBBMEETING_WHERE = "SELECT COUNT(bbbMeeting) FROM BBBMeeting bbbMeeting WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "bbbMeeting.bbbMeetingId";
	private static final String _FILTER_SQL_SELECT_BBBMEETING_WHERE = "SELECT DISTINCT {bbbMeeting.*} FROM BBBMeeting bbbMeeting WHERE ";
	private static final String _FILTER_SQL_SELECT_BBBMEETING_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {BBBMeeting.*} FROM (SELECT DISTINCT bbbMeeting.bbbMeetingId FROM BBBMeeting bbbMeeting WHERE ";
	private static final String _FILTER_SQL_SELECT_BBBMEETING_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN BBBMeeting ON TEMP_TABLE.bbbMeetingId = BBBMeeting.bbbMeetingId";
	private static final String _FILTER_SQL_COUNT_BBBMEETING_WHERE = "SELECT COUNT(DISTINCT bbbMeeting.bbbMeetingId) AS COUNT_VALUE FROM BBBMeeting bbbMeeting WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "bbbMeeting";
	private static final String _FILTER_ENTITY_TABLE = "BBBMeeting";
	private static final String _ORDER_BY_ENTITY_ALIAS = "bbbMeeting.";
	private static final String _ORDER_BY_ENTITY_TABLE = "BBBMeeting.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No BBBMeeting exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No BBBMeeting exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(BBBMeetingPersistenceImpl.class);
	private static BBBMeeting _nullBBBMeeting = new BBBMeetingImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<BBBMeeting> toCacheModel() {
				return _nullBBBMeetingCacheModel;
			}
		};

	private static CacheModel<BBBMeeting> _nullBBBMeetingCacheModel = new CacheModel<BBBMeeting>() {
			@Override
			public BBBMeeting toEntityModel() {
				return _nullBBBMeeting;
			}
		};
}