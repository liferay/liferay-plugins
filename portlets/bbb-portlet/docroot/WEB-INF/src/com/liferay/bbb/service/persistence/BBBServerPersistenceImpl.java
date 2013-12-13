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

package com.liferay.bbb.service.persistence;

import com.liferay.bbb.NoSuchServerException;
import com.liferay.bbb.model.BBBServer;
import com.liferay.bbb.model.impl.BBBServerImpl;
import com.liferay.bbb.model.impl.BBBServerModelImpl;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the b b b server service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Shinn Lok
 * @see BBBServerPersistence
 * @see BBBServerUtil
 * @generated
 */
public class BBBServerPersistenceImpl extends BasePersistenceImpl<BBBServer>
	implements BBBServerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link BBBServerUtil} to access the b b b server persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = BBBServerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BBBServerModelImpl.ENTITY_CACHE_ENABLED,
			BBBServerModelImpl.FINDER_CACHE_ENABLED, BBBServerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BBBServerModelImpl.ENTITY_CACHE_ENABLED,
			BBBServerModelImpl.FINDER_CACHE_ENABLED, BBBServerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BBBServerModelImpl.ENTITY_CACHE_ENABLED,
			BBBServerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(BBBServerModelImpl.ENTITY_CACHE_ENABLED,
			BBBServerModelImpl.FINDER_CACHE_ENABLED, BBBServerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(BBBServerModelImpl.ENTITY_CACHE_ENABLED,
			BBBServerModelImpl.FINDER_CACHE_ENABLED, BBBServerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			BBBServerModelImpl.GROUPID_COLUMN_BITMASK |
			BBBServerModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(BBBServerModelImpl.ENTITY_CACHE_ENABLED,
			BBBServerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the b b b servers where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching b b b servers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BBBServer> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the b b b servers where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBServerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of b b b servers
	 * @param end the upper bound of the range of b b b servers (not inclusive)
	 * @return the range of matching b b b servers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BBBServer> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the b b b servers where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBServerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of b b b servers
	 * @param end the upper bound of the range of b b b servers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching b b b servers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BBBServer> findByGroupId(long groupId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
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

		List<BBBServer> list = (List<BBBServer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BBBServer bbbServer : list) {
				if ((groupId != bbbServer.getGroupId())) {
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

			query.append(_SQL_SELECT_BBBSERVER_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BBBServerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<BBBServer>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<BBBServer>(list);
				}
				else {
					list = (List<BBBServer>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first b b b server in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching b b b server
	 * @throws com.liferay.bbb.NoSuchServerException if a matching b b b server could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BBBServer findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchServerException, SystemException {
		BBBServer bbbServer = fetchByGroupId_First(groupId, orderByComparator);

		if (bbbServer != null) {
			return bbbServer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchServerException(msg.toString());
	}

	/**
	 * Returns the first b b b server in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching b b b server, or <code>null</code> if a matching b b b server could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BBBServer fetchByGroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<BBBServer> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last b b b server in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching b b b server
	 * @throws com.liferay.bbb.NoSuchServerException if a matching b b b server could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BBBServer findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchServerException, SystemException {
		BBBServer bbbServer = fetchByGroupId_Last(groupId, orderByComparator);

		if (bbbServer != null) {
			return bbbServer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchServerException(msg.toString());
	}

	/**
	 * Returns the last b b b server in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching b b b server, or <code>null</code> if a matching b b b server could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BBBServer fetchByGroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<BBBServer> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the b b b servers before and after the current b b b server in the ordered set where groupId = &#63;.
	 *
	 * @param bbbServerId the primary key of the current b b b server
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next b b b server
	 * @throws com.liferay.bbb.NoSuchServerException if a b b b server with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BBBServer[] findByGroupId_PrevAndNext(long bbbServerId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchServerException, SystemException {
		BBBServer bbbServer = findByPrimaryKey(bbbServerId);

		Session session = null;

		try {
			session = openSession();

			BBBServer[] array = new BBBServerImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, bbbServer, groupId,
					orderByComparator, true);

			array[1] = bbbServer;

			array[2] = getByGroupId_PrevAndNext(session, bbbServer, groupId,
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

	protected BBBServer getByGroupId_PrevAndNext(Session session,
		BBBServer bbbServer, long groupId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BBBSERVER_WHERE);

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
			query.append(BBBServerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(bbbServer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BBBServer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the b b b servers that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching b b b servers that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BBBServer> filterFindByGroupId(long groupId)
		throws SystemException {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the b b b servers that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBServerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of b b b servers
	 * @param end the upper bound of the range of b b b servers (not inclusive)
	 * @return the range of matching b b b servers that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BBBServer> filterFindByGroupId(long groupId, int start, int end)
		throws SystemException {
		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the b b b servers that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBServerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of b b b servers
	 * @param end the upper bound of the range of b b b servers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching b b b servers that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BBBServer> filterFindByGroupId(long groupId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
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
			query.append(_FILTER_SQL_SELECT_BBBSERVER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_BBBSERVER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_BBBSERVER_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(BBBServerModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(BBBServerModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				BBBServer.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, BBBServerImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, BBBServerImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<BBBServer>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the b b b servers before and after the current b b b server in the ordered set of b b b servers that the user has permission to view where groupId = &#63;.
	 *
	 * @param bbbServerId the primary key of the current b b b server
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next b b b server
	 * @throws com.liferay.bbb.NoSuchServerException if a b b b server with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BBBServer[] filterFindByGroupId_PrevAndNext(long bbbServerId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchServerException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(bbbServerId, groupId,
				orderByComparator);
		}

		BBBServer bbbServer = findByPrimaryKey(bbbServerId);

		Session session = null;

		try {
			session = openSession();

			BBBServer[] array = new BBBServerImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session, bbbServer,
					groupId, orderByComparator, true);

			array[1] = bbbServer;

			array[2] = filterGetByGroupId_PrevAndNext(session, bbbServer,
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

	protected BBBServer filterGetByGroupId_PrevAndNext(Session session,
		BBBServer bbbServer, long groupId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_BBBSERVER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_BBBSERVER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_BBBSERVER_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(BBBServerModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(BBBServerModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				BBBServer.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, BBBServerImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, BBBServerImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(bbbServer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BBBServer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the b b b servers where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupId(long groupId) throws SystemException {
		for (BBBServer bbbServer : findByGroupId(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(bbbServer);
		}
	}

	/**
	 * Returns the number of b b b servers where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching b b b servers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroupId(long groupId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BBBSERVER_WHERE);

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
	 * Returns the number of b b b servers that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching b b b servers that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGroupId(long groupId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_BBBSERVER_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				BBBServer.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "bbbServer.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_A = new FinderPath(BBBServerModelImpl.ENTITY_CACHE_ENABLED,
			BBBServerModelImpl.FINDER_CACHE_ENABLED, BBBServerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_A = new FinderPath(BBBServerModelImpl.ENTITY_CACHE_ENABLED,
			BBBServerModelImpl.FINDER_CACHE_ENABLED, BBBServerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_A",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			BBBServerModelImpl.GROUPID_COLUMN_BITMASK |
			BBBServerModelImpl.ACTIVE_COLUMN_BITMASK |
			BBBServerModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_A = new FinderPath(BBBServerModelImpl.ENTITY_CACHE_ENABLED,
			BBBServerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_A",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the b b b servers where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @return the matching b b b servers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BBBServer> findByG_A(long groupId, boolean active)
		throws SystemException {
		return findByG_A(groupId, active, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the b b b servers where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBServerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param start the lower bound of the range of b b b servers
	 * @param end the upper bound of the range of b b b servers (not inclusive)
	 * @return the range of matching b b b servers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BBBServer> findByG_A(long groupId, boolean active, int start,
		int end) throws SystemException {
		return findByG_A(groupId, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the b b b servers where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBServerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param start the lower bound of the range of b b b servers
	 * @param end the upper bound of the range of b b b servers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching b b b servers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BBBServer> findByG_A(long groupId, boolean active, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_A;
			finderArgs = new Object[] { groupId, active };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_A;
			finderArgs = new Object[] {
					groupId, active,
					
					start, end, orderByComparator
				};
		}

		List<BBBServer> list = (List<BBBServer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (BBBServer bbbServer : list) {
				if ((groupId != bbbServer.getGroupId()) ||
						(active != bbbServer.getActive())) {
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

			query.append(_SQL_SELECT_BBBSERVER_WHERE);

			query.append(_FINDER_COLUMN_G_A_GROUPID_2);

			query.append(_FINDER_COLUMN_G_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(BBBServerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(active);

				if (!pagination) {
					list = (List<BBBServer>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<BBBServer>(list);
				}
				else {
					list = (List<BBBServer>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first b b b server in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching b b b server
	 * @throws com.liferay.bbb.NoSuchServerException if a matching b b b server could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BBBServer findByG_A_First(long groupId, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchServerException, SystemException {
		BBBServer bbbServer = fetchByG_A_First(groupId, active,
				orderByComparator);

		if (bbbServer != null) {
			return bbbServer;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchServerException(msg.toString());
	}

	/**
	 * Returns the first b b b server in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching b b b server, or <code>null</code> if a matching b b b server could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BBBServer fetchByG_A_First(long groupId, boolean active,
		OrderByComparator orderByComparator) throws SystemException {
		List<BBBServer> list = findByG_A(groupId, active, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last b b b server in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching b b b server
	 * @throws com.liferay.bbb.NoSuchServerException if a matching b b b server could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BBBServer findByG_A_Last(long groupId, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchServerException, SystemException {
		BBBServer bbbServer = fetchByG_A_Last(groupId, active, orderByComparator);

		if (bbbServer != null) {
			return bbbServer;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchServerException(msg.toString());
	}

	/**
	 * Returns the last b b b server in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching b b b server, or <code>null</code> if a matching b b b server could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BBBServer fetchByG_A_Last(long groupId, boolean active,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_A(groupId, active);

		if (count == 0) {
			return null;
		}

		List<BBBServer> list = findByG_A(groupId, active, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the b b b servers before and after the current b b b server in the ordered set where groupId = &#63; and active = &#63;.
	 *
	 * @param bbbServerId the primary key of the current b b b server
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next b b b server
	 * @throws com.liferay.bbb.NoSuchServerException if a b b b server with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BBBServer[] findByG_A_PrevAndNext(long bbbServerId, long groupId,
		boolean active, OrderByComparator orderByComparator)
		throws NoSuchServerException, SystemException {
		BBBServer bbbServer = findByPrimaryKey(bbbServerId);

		Session session = null;

		try {
			session = openSession();

			BBBServer[] array = new BBBServerImpl[3];

			array[0] = getByG_A_PrevAndNext(session, bbbServer, groupId,
					active, orderByComparator, true);

			array[1] = bbbServer;

			array[2] = getByG_A_PrevAndNext(session, bbbServer, groupId,
					active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BBBServer getByG_A_PrevAndNext(Session session,
		BBBServer bbbServer, long groupId, boolean active,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BBBSERVER_WHERE);

		query.append(_FINDER_COLUMN_G_A_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_ACTIVE_2);

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
			query.append(BBBServerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(bbbServer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BBBServer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the b b b servers that the user has permission to view where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @return the matching b b b servers that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BBBServer> filterFindByG_A(long groupId, boolean active)
		throws SystemException {
		return filterFindByG_A(groupId, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the b b b servers that the user has permission to view where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBServerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param start the lower bound of the range of b b b servers
	 * @param end the upper bound of the range of b b b servers (not inclusive)
	 * @return the range of matching b b b servers that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BBBServer> filterFindByG_A(long groupId, boolean active,
		int start, int end) throws SystemException {
		return filterFindByG_A(groupId, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the b b b servers that the user has permissions to view where groupId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBServerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @param start the lower bound of the range of b b b servers
	 * @param end the upper bound of the range of b b b servers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching b b b servers that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BBBServer> filterFindByG_A(long groupId, boolean active,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_A(groupId, active, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_BBBSERVER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_BBBSERVER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_A_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_ACTIVE_2_SQL);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_BBBSERVER_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(BBBServerModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(BBBServerModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				BBBServer.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, BBBServerImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, BBBServerImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(active);

			return (List<BBBServer>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the b b b servers before and after the current b b b server in the ordered set of b b b servers that the user has permission to view where groupId = &#63; and active = &#63;.
	 *
	 * @param bbbServerId the primary key of the current b b b server
	 * @param groupId the group ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next b b b server
	 * @throws com.liferay.bbb.NoSuchServerException if a b b b server with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BBBServer[] filterFindByG_A_PrevAndNext(long bbbServerId,
		long groupId, boolean active, OrderByComparator orderByComparator)
		throws NoSuchServerException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_A_PrevAndNext(bbbServerId, groupId, active,
				orderByComparator);
		}

		BBBServer bbbServer = findByPrimaryKey(bbbServerId);

		Session session = null;

		try {
			session = openSession();

			BBBServer[] array = new BBBServerImpl[3];

			array[0] = filterGetByG_A_PrevAndNext(session, bbbServer, groupId,
					active, orderByComparator, true);

			array[1] = bbbServer;

			array[2] = filterGetByG_A_PrevAndNext(session, bbbServer, groupId,
					active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected BBBServer filterGetByG_A_PrevAndNext(Session session,
		BBBServer bbbServer, long groupId, boolean active,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_BBBSERVER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_BBBSERVER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_A_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_ACTIVE_2_SQL);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_BBBSERVER_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(BBBServerModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(BBBServerModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				BBBServer.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, BBBServerImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, BBBServerImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(bbbServer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<BBBServer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the b b b servers where groupId = &#63; and active = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByG_A(long groupId, boolean active)
		throws SystemException {
		for (BBBServer bbbServer : findByG_A(groupId, active,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(bbbServer);
		}
	}

	/**
	 * Returns the number of b b b servers where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @return the number of matching b b b servers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByG_A(long groupId, boolean active)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_A;

		Object[] finderArgs = new Object[] { groupId, active };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_BBBSERVER_WHERE);

			query.append(_FINDER_COLUMN_G_A_GROUPID_2);

			query.append(_FINDER_COLUMN_G_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(active);

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
	 * Returns the number of b b b servers that the user has permission to view where groupId = &#63; and active = &#63;.
	 *
	 * @param groupId the group ID
	 * @param active the active
	 * @return the number of matching b b b servers that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByG_A(long groupId, boolean active)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_A(groupId, active);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_BBBSERVER_WHERE);

		query.append(_FINDER_COLUMN_G_A_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_ACTIVE_2_SQL);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				BBBServer.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(active);

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

	private static final String _FINDER_COLUMN_G_A_GROUPID_2 = "bbbServer.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_A_ACTIVE_2 = "bbbServer.active = ?";
	private static final String _FINDER_COLUMN_G_A_ACTIVE_2_SQL = "bbbServer.active_ = ?";

	public BBBServerPersistenceImpl() {
		setModelClass(BBBServer.class);
	}

	/**
	 * Caches the b b b server in the entity cache if it is enabled.
	 *
	 * @param bbbServer the b b b server
	 */
	@Override
	public void cacheResult(BBBServer bbbServer) {
		EntityCacheUtil.putResult(BBBServerModelImpl.ENTITY_CACHE_ENABLED,
			BBBServerImpl.class, bbbServer.getPrimaryKey(), bbbServer);

		bbbServer.resetOriginalValues();
	}

	/**
	 * Caches the b b b servers in the entity cache if it is enabled.
	 *
	 * @param bbbServers the b b b servers
	 */
	@Override
	public void cacheResult(List<BBBServer> bbbServers) {
		for (BBBServer bbbServer : bbbServers) {
			if (EntityCacheUtil.getResult(
						BBBServerModelImpl.ENTITY_CACHE_ENABLED,
						BBBServerImpl.class, bbbServer.getPrimaryKey()) == null) {
				cacheResult(bbbServer);
			}
			else {
				bbbServer.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all b b b servers.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(BBBServerImpl.class.getName());
		}

		EntityCacheUtil.clearCache(BBBServerImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the b b b server.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(BBBServer bbbServer) {
		EntityCacheUtil.removeResult(BBBServerModelImpl.ENTITY_CACHE_ENABLED,
			BBBServerImpl.class, bbbServer.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<BBBServer> bbbServers) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (BBBServer bbbServer : bbbServers) {
			EntityCacheUtil.removeResult(BBBServerModelImpl.ENTITY_CACHE_ENABLED,
				BBBServerImpl.class, bbbServer.getPrimaryKey());
		}
	}

	/**
	 * Creates a new b b b server with the primary key. Does not add the b b b server to the database.
	 *
	 * @param bbbServerId the primary key for the new b b b server
	 * @return the new b b b server
	 */
	@Override
	public BBBServer create(long bbbServerId) {
		BBBServer bbbServer = new BBBServerImpl();

		bbbServer.setNew(true);
		bbbServer.setPrimaryKey(bbbServerId);

		return bbbServer;
	}

	/**
	 * Removes the b b b server with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param bbbServerId the primary key of the b b b server
	 * @return the b b b server that was removed
	 * @throws com.liferay.bbb.NoSuchServerException if a b b b server with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BBBServer remove(long bbbServerId)
		throws NoSuchServerException, SystemException {
		return remove((Serializable)bbbServerId);
	}

	/**
	 * Removes the b b b server with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the b b b server
	 * @return the b b b server that was removed
	 * @throws com.liferay.bbb.NoSuchServerException if a b b b server with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BBBServer remove(Serializable primaryKey)
		throws NoSuchServerException, SystemException {
		Session session = null;

		try {
			session = openSession();

			BBBServer bbbServer = (BBBServer)session.get(BBBServerImpl.class,
					primaryKey);

			if (bbbServer == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchServerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(bbbServer);
		}
		catch (NoSuchServerException nsee) {
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
	protected BBBServer removeImpl(BBBServer bbbServer)
		throws SystemException {
		bbbServer = toUnwrappedModel(bbbServer);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(bbbServer)) {
				bbbServer = (BBBServer)session.get(BBBServerImpl.class,
						bbbServer.getPrimaryKeyObj());
			}

			if (bbbServer != null) {
				session.delete(bbbServer);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (bbbServer != null) {
			clearCache(bbbServer);
		}

		return bbbServer;
	}

	@Override
	public BBBServer updateImpl(com.liferay.bbb.model.BBBServer bbbServer)
		throws SystemException {
		bbbServer = toUnwrappedModel(bbbServer);

		boolean isNew = bbbServer.isNew();

		BBBServerModelImpl bbbServerModelImpl = (BBBServerModelImpl)bbbServer;

		Session session = null;

		try {
			session = openSession();

			if (bbbServer.isNew()) {
				session.save(bbbServer);

				bbbServer.setNew(false);
			}
			else {
				session.merge(bbbServer);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !BBBServerModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((bbbServerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						bbbServerModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { bbbServerModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((bbbServerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						bbbServerModelImpl.getOriginalGroupId(),
						bbbServerModelImpl.getOriginalActive()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_A,
					args);

				args = new Object[] {
						bbbServerModelImpl.getGroupId(),
						bbbServerModelImpl.getActive()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_A,
					args);
			}
		}

		EntityCacheUtil.putResult(BBBServerModelImpl.ENTITY_CACHE_ENABLED,
			BBBServerImpl.class, bbbServer.getPrimaryKey(), bbbServer);

		return bbbServer;
	}

	protected BBBServer toUnwrappedModel(BBBServer bbbServer) {
		if (bbbServer instanceof BBBServerImpl) {
			return bbbServer;
		}

		BBBServerImpl bbbServerImpl = new BBBServerImpl();

		bbbServerImpl.setNew(bbbServer.isNew());
		bbbServerImpl.setPrimaryKey(bbbServer.getPrimaryKey());

		bbbServerImpl.setBbbServerId(bbbServer.getBbbServerId());
		bbbServerImpl.setGroupId(bbbServer.getGroupId());
		bbbServerImpl.setCompanyId(bbbServer.getCompanyId());
		bbbServerImpl.setUserId(bbbServer.getUserId());
		bbbServerImpl.setUserName(bbbServer.getUserName());
		bbbServerImpl.setCreateDate(bbbServer.getCreateDate());
		bbbServerImpl.setModifiedDate(bbbServer.getModifiedDate());
		bbbServerImpl.setName(bbbServer.getName());
		bbbServerImpl.setUrl(bbbServer.getUrl());
		bbbServerImpl.setSecret(bbbServer.getSecret());
		bbbServerImpl.setActive(bbbServer.isActive());

		return bbbServerImpl;
	}

	/**
	 * Returns the b b b server with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the b b b server
	 * @return the b b b server
	 * @throws com.liferay.bbb.NoSuchServerException if a b b b server with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BBBServer findByPrimaryKey(Serializable primaryKey)
		throws NoSuchServerException, SystemException {
		BBBServer bbbServer = fetchByPrimaryKey(primaryKey);

		if (bbbServer == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchServerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return bbbServer;
	}

	/**
	 * Returns the b b b server with the primary key or throws a {@link com.liferay.bbb.NoSuchServerException} if it could not be found.
	 *
	 * @param bbbServerId the primary key of the b b b server
	 * @return the b b b server
	 * @throws com.liferay.bbb.NoSuchServerException if a b b b server with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BBBServer findByPrimaryKey(long bbbServerId)
		throws NoSuchServerException, SystemException {
		return findByPrimaryKey((Serializable)bbbServerId);
	}

	/**
	 * Returns the b b b server with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the b b b server
	 * @return the b b b server, or <code>null</code> if a b b b server with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BBBServer fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		BBBServer bbbServer = (BBBServer)EntityCacheUtil.getResult(BBBServerModelImpl.ENTITY_CACHE_ENABLED,
				BBBServerImpl.class, primaryKey);

		if (bbbServer == _nullBBBServer) {
			return null;
		}

		if (bbbServer == null) {
			Session session = null;

			try {
				session = openSession();

				bbbServer = (BBBServer)session.get(BBBServerImpl.class,
						primaryKey);

				if (bbbServer != null) {
					cacheResult(bbbServer);
				}
				else {
					EntityCacheUtil.putResult(BBBServerModelImpl.ENTITY_CACHE_ENABLED,
						BBBServerImpl.class, primaryKey, _nullBBBServer);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(BBBServerModelImpl.ENTITY_CACHE_ENABLED,
					BBBServerImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return bbbServer;
	}

	/**
	 * Returns the b b b server with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param bbbServerId the primary key of the b b b server
	 * @return the b b b server, or <code>null</code> if a b b b server with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BBBServer fetchByPrimaryKey(long bbbServerId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)bbbServerId);
	}

	/**
	 * Returns all the b b b servers.
	 *
	 * @return the b b b servers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BBBServer> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the b b b servers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBServerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of b b b servers
	 * @param end the upper bound of the range of b b b servers (not inclusive)
	 * @return the range of b b b servers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BBBServer> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the b b b servers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBServerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of b b b servers
	 * @param end the upper bound of the range of b b b servers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of b b b servers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BBBServer> findAll(int start, int end,
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

		List<BBBServer> list = (List<BBBServer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_BBBSERVER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_BBBSERVER;

				if (pagination) {
					sql = sql.concat(BBBServerModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<BBBServer>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<BBBServer>(list);
				}
				else {
					list = (List<BBBServer>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the b b b servers from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (BBBServer bbbServer : findAll()) {
			remove(bbbServer);
		}
	}

	/**
	 * Returns the number of b b b servers.
	 *
	 * @return the number of b b b servers
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

				Query q = session.createQuery(_SQL_COUNT_BBBSERVER);

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
	 * Initializes the b b b server persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.bbb.model.BBBServer")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<BBBServer>> listenersList = new ArrayList<ModelListener<BBBServer>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<BBBServer>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(BBBServerImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_BBBSERVER = "SELECT bbbServer FROM BBBServer bbbServer";
	private static final String _SQL_SELECT_BBBSERVER_WHERE = "SELECT bbbServer FROM BBBServer bbbServer WHERE ";
	private static final String _SQL_COUNT_BBBSERVER = "SELECT COUNT(bbbServer) FROM BBBServer bbbServer";
	private static final String _SQL_COUNT_BBBSERVER_WHERE = "SELECT COUNT(bbbServer) FROM BBBServer bbbServer WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "bbbServer.bbbServerId";
	private static final String _FILTER_SQL_SELECT_BBBSERVER_WHERE = "SELECT DISTINCT {bbbServer.*} FROM BBBServer bbbServer WHERE ";
	private static final String _FILTER_SQL_SELECT_BBBSERVER_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {BBBServer.*} FROM (SELECT DISTINCT bbbServer.bbbServerId FROM BBBServer bbbServer WHERE ";
	private static final String _FILTER_SQL_SELECT_BBBSERVER_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN BBBServer ON TEMP_TABLE.bbbServerId = BBBServer.bbbServerId";
	private static final String _FILTER_SQL_COUNT_BBBSERVER_WHERE = "SELECT COUNT(DISTINCT bbbServer.bbbServerId) AS COUNT_VALUE FROM BBBServer bbbServer WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "bbbServer";
	private static final String _FILTER_ENTITY_TABLE = "BBBServer";
	private static final String _ORDER_BY_ENTITY_ALIAS = "bbbServer.";
	private static final String _ORDER_BY_ENTITY_TABLE = "BBBServer.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No BBBServer exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No BBBServer exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(BBBServerPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"active"
			});
	private static BBBServer _nullBBBServer = new BBBServerImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<BBBServer> toCacheModel() {
				return _nullBBBServerCacheModel;
			}
		};

	private static CacheModel<BBBServer> _nullBBBServerCacheModel = new CacheModel<BBBServer>() {
			@Override
			public BBBServer toEntityModel() {
				return _nullBBBServer;
			}
		};
}