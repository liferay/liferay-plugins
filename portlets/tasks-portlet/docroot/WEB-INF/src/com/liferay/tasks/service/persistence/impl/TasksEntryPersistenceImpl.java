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

package com.liferay.tasks.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

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
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.tasks.NoSuchTasksEntryException;
import com.liferay.tasks.model.TasksEntry;
import com.liferay.tasks.model.impl.TasksEntryImpl;
import com.liferay.tasks.model.impl.TasksEntryModelImpl;
import com.liferay.tasks.service.persistence.TasksEntryPersistence;

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
 * The persistence implementation for the tasks entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ryan Park
 * @see TasksEntryPersistence
 * @see com.liferay.tasks.service.persistence.TasksEntryUtil
 * @generated
 */
@ProviderType
public class TasksEntryPersistenceImpl extends BasePersistenceImpl<TasksEntry>
	implements TasksEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TasksEntryUtil} to access the tasks entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TasksEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			TasksEntryModelImpl.GROUPID_COLUMN_BITMASK |
			TasksEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TasksEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TasksEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the tasks entries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByGroupId(long groupId, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator) {
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

		List<TasksEntry> list = (List<TasksEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TasksEntry tasksEntry : list) {
				if ((groupId != tasksEntry.getGroupId())) {
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

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first tasks entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByGroupId_First(long groupId,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = fetchByGroupId_First(groupId, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the first tasks entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByGroupId_First(long groupId,
		OrderByComparator<TasksEntry> orderByComparator) {
		List<TasksEntry> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last tasks entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByGroupId_Last(long groupId,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = fetchByGroupId_Last(groupId, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the last tasks entry in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByGroupId_Last(long groupId,
		OrderByComparator<TasksEntry> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<TasksEntry> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] findByGroupId_PrevAndNext(long tasksEntryId,
		long groupId, OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, tasksEntry, groupId,
					orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = getByGroupId_PrevAndNext(session, tasksEntry, groupId,
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

	protected TasksEntry getByGroupId_PrevAndNext(Session session,
		TasksEntry tasksEntry, long groupId,
		OrderByComparator<TasksEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

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
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the tasks entries that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching tasks entries that the user has permission to view
	 */
	@Override
	public List<TasksEntry> filterFindByGroupId(long groupId) {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries that the user has permission to view
	 */
	@Override
	public List<TasksEntry> filterFindByGroupId(long groupId, int start, int end) {
		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries that the user has permission to view
	 */
	@Override
	public List<TasksEntry> filterFindByGroupId(long groupId, int start,
		int end, OrderByComparator<TasksEntry> orderByComparator) {
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
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, TasksEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, TasksEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<TasksEntry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set of tasks entries that the user has permission to view where groupId = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] filterFindByGroupId_PrevAndNext(long tasksEntryId,
		long groupId, OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(tasksEntryId, groupId,
				orderByComparator);
		}

		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session, tasksEntry,
					groupId, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = filterGetByGroupId_PrevAndNext(session, tasksEntry,
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

	protected TasksEntry filterGetByGroupId_PrevAndNext(Session session,
		TasksEntry tasksEntry, long groupId,
		OrderByComparator<TasksEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, TasksEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, TasksEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the tasks entries where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (TasksEntry tasksEntry : findByGroupId(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(tasksEntry);
		}
	}

	/**
	 * Returns the number of tasks entries where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

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
	 * Returns the number of tasks entries that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching tasks entries that the user has permission to view
	 */
	@Override
	public int filterCountByGroupId(long groupId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(),
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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "tasksEntry.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			TasksEntryModelImpl.USERID_COLUMN_BITMASK |
			TasksEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TasksEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TasksEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the tasks entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByUserId(long userId, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<TasksEntry> list = (List<TasksEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TasksEntry tasksEntry : list) {
				if ((userId != tasksEntry.getUserId())) {
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

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first tasks entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByUserId_First(long userId,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = fetchByUserId_First(userId, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the first tasks entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByUserId_First(long userId,
		OrderByComparator<TasksEntry> orderByComparator) {
		List<TasksEntry> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last tasks entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByUserId_Last(long userId,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = fetchByUserId_Last(userId, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the last tasks entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByUserId_Last(long userId,
		OrderByComparator<TasksEntry> orderByComparator) {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<TasksEntry> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set where userId = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] findByUserId_PrevAndNext(long tasksEntryId,
		long userId, OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByUserId_PrevAndNext(session, tasksEntry, userId,
					orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = getByUserId_PrevAndNext(session, tasksEntry, userId,
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

	protected TasksEntry getByUserId_PrevAndNext(Session session,
		TasksEntry tasksEntry, long userId,
		OrderByComparator<TasksEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

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
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the tasks entries where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (TasksEntry tasksEntry : findByUserId(userId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(tasksEntry);
		}
	}

	/**
	 * Returns the number of tasks entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "tasksEntry.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSIGNEEUSERID =
		new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAssigneeUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSIGNEEUSERID =
		new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAssigneeUserId",
			new String[] { Long.class.getName() },
			TasksEntryModelImpl.ASSIGNEEUSERID_COLUMN_BITMASK |
			TasksEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TasksEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TasksEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ASSIGNEEUSERID = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAssigneeUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the tasks entries where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByAssigneeUserId(long assigneeUserId) {
		return findByAssigneeUserId(assigneeUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries where assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByAssigneeUserId(long assigneeUserId,
		int start, int end) {
		return findByAssigneeUserId(assigneeUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByAssigneeUserId(long assigneeUserId,
		int start, int end, OrderByComparator<TasksEntry> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSIGNEEUSERID;
			finderArgs = new Object[] { assigneeUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSIGNEEUSERID;
			finderArgs = new Object[] {
					assigneeUserId,
					
					start, end, orderByComparator
				};
		}

		List<TasksEntry> list = (List<TasksEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TasksEntry tasksEntry : list) {
				if ((assigneeUserId != tasksEntry.getAssigneeUserId())) {
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

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_ASSIGNEEUSERID_ASSIGNEEUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeUserId);

				if (!pagination) {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first tasks entry in the ordered set where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByAssigneeUserId_First(long assigneeUserId,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = fetchByAssigneeUserId_First(assigneeUserId,
				orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assigneeUserId=");
		msg.append(assigneeUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the first tasks entry in the ordered set where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByAssigneeUserId_First(long assigneeUserId,
		OrderByComparator<TasksEntry> orderByComparator) {
		List<TasksEntry> list = findByAssigneeUserId(assigneeUserId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last tasks entry in the ordered set where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByAssigneeUserId_Last(long assigneeUserId,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = fetchByAssigneeUserId_Last(assigneeUserId,
				orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assigneeUserId=");
		msg.append(assigneeUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the last tasks entry in the ordered set where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByAssigneeUserId_Last(long assigneeUserId,
		OrderByComparator<TasksEntry> orderByComparator) {
		int count = countByAssigneeUserId(assigneeUserId);

		if (count == 0) {
			return null;
		}

		List<TasksEntry> list = findByAssigneeUserId(assigneeUserId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set where assigneeUserId = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] findByAssigneeUserId_PrevAndNext(long tasksEntryId,
		long assigneeUserId, OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByAssigneeUserId_PrevAndNext(session, tasksEntry,
					assigneeUserId, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = getByAssigneeUserId_PrevAndNext(session, tasksEntry,
					assigneeUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry getByAssigneeUserId_PrevAndNext(Session session,
		TasksEntry tasksEntry, long assigneeUserId,
		OrderByComparator<TasksEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_ASSIGNEEUSERID_ASSIGNEEUSERID_2);

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
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(assigneeUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the tasks entries where assigneeUserId = &#63; from the database.
	 *
	 * @param assigneeUserId the assignee user ID
	 */
	@Override
	public void removeByAssigneeUserId(long assigneeUserId) {
		for (TasksEntry tasksEntry : findByAssigneeUserId(assigneeUserId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(tasksEntry);
		}
	}

	/**
	 * Returns the number of tasks entries where assigneeUserId = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByAssigneeUserId(long assigneeUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ASSIGNEEUSERID;

		Object[] finderArgs = new Object[] { assigneeUserId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_ASSIGNEEUSERID_ASSIGNEEUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeUserId);

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

	private static final String _FINDER_COLUMN_ASSIGNEEUSERID_ASSIGNEEUSERID_2 = "tasksEntry.assigneeUserId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RESOLVERUSERID =
		new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByResolverUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOLVERUSERID =
		new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByResolverUserId",
			new String[] { Long.class.getName() },
			TasksEntryModelImpl.RESOLVERUSERID_COLUMN_BITMASK |
			TasksEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TasksEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TasksEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RESOLVERUSERID = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByResolverUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the tasks entries where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByResolverUserId(long resolverUserId) {
		return findByResolverUserId(resolverUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries where resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resolverUserId the resolver user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByResolverUserId(long resolverUserId,
		int start, int end) {
		return findByResolverUserId(resolverUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resolverUserId the resolver user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByResolverUserId(long resolverUserId,
		int start, int end, OrderByComparator<TasksEntry> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOLVERUSERID;
			finderArgs = new Object[] { resolverUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RESOLVERUSERID;
			finderArgs = new Object[] {
					resolverUserId,
					
					start, end, orderByComparator
				};
		}

		List<TasksEntry> list = (List<TasksEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TasksEntry tasksEntry : list) {
				if ((resolverUserId != tasksEntry.getResolverUserId())) {
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

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_RESOLVERUSERID_RESOLVERUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resolverUserId);

				if (!pagination) {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first tasks entry in the ordered set where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByResolverUserId_First(long resolverUserId,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = fetchByResolverUserId_First(resolverUserId,
				orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resolverUserId=");
		msg.append(resolverUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the first tasks entry in the ordered set where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByResolverUserId_First(long resolverUserId,
		OrderByComparator<TasksEntry> orderByComparator) {
		List<TasksEntry> list = findByResolverUserId(resolverUserId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last tasks entry in the ordered set where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByResolverUserId_Last(long resolverUserId,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = fetchByResolverUserId_Last(resolverUserId,
				orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resolverUserId=");
		msg.append(resolverUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the last tasks entry in the ordered set where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByResolverUserId_Last(long resolverUserId,
		OrderByComparator<TasksEntry> orderByComparator) {
		int count = countByResolverUserId(resolverUserId);

		if (count == 0) {
			return null;
		}

		List<TasksEntry> list = findByResolverUserId(resolverUserId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set where resolverUserId = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] findByResolverUserId_PrevAndNext(long tasksEntryId,
		long resolverUserId, OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByResolverUserId_PrevAndNext(session, tasksEntry,
					resolverUserId, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = getByResolverUserId_PrevAndNext(session, tasksEntry,
					resolverUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry getByResolverUserId_PrevAndNext(Session session,
		TasksEntry tasksEntry, long resolverUserId,
		OrderByComparator<TasksEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_RESOLVERUSERID_RESOLVERUSERID_2);

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
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resolverUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the tasks entries where resolverUserId = &#63; from the database.
	 *
	 * @param resolverUserId the resolver user ID
	 */
	@Override
	public void removeByResolverUserId(long resolverUserId) {
		for (TasksEntry tasksEntry : findByResolverUserId(resolverUserId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(tasksEntry);
		}
	}

	/**
	 * Returns the number of tasks entries where resolverUserId = &#63;.
	 *
	 * @param resolverUserId the resolver user ID
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByResolverUserId(long resolverUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_RESOLVERUSERID;

		Object[] finderArgs = new Object[] { resolverUserId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_RESOLVERUSERID_RESOLVERUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resolverUserId);

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

	private static final String _FINDER_COLUMN_RESOLVERUSERID_RESOLVERUSERID_2 = "tasksEntry.resolverUserId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_U",
			new String[] { Long.class.getName(), Long.class.getName() },
			TasksEntryModelImpl.GROUPID_COLUMN_BITMASK |
			TasksEntryModelImpl.USERID_COLUMN_BITMASK |
			TasksEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TasksEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TasksEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_U = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_U",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the tasks entries where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_U(long groupId, long userId) {
		return findByG_U(groupId, userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the tasks entries where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_U(long groupId, long userId, int start,
		int end) {
		return findByG_U(groupId, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_U(long groupId, long userId, int start,
		int end, OrderByComparator<TasksEntry> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U;
			finderArgs = new Object[] { groupId, userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U;
			finderArgs = new Object[] {
					groupId, userId,
					
					start, end, orderByComparator
				};
		}

		List<TasksEntry> list = (List<TasksEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TasksEntry tasksEntry : list) {
				if ((groupId != tasksEntry.getGroupId()) ||
						(userId != tasksEntry.getUserId())) {
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

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				if (!pagination) {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByG_U_First(long groupId, long userId,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = fetchByG_U_First(groupId, userId,
				orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the first tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByG_U_First(long groupId, long userId,
		OrderByComparator<TasksEntry> orderByComparator) {
		List<TasksEntry> list = findByG_U(groupId, userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByG_U_Last(long groupId, long userId,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = fetchByG_U_Last(groupId, userId,
				orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the last tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByG_U_Last(long groupId, long userId,
		OrderByComparator<TasksEntry> orderByComparator) {
		int count = countByG_U(groupId, userId);

		if (count == 0) {
			return null;
		}

		List<TasksEntry> list = findByG_U(groupId, userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and userId = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] findByG_U_PrevAndNext(long tasksEntryId, long groupId,
		long userId, OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByG_U_PrevAndNext(session, tasksEntry, groupId,
					userId, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = getByG_U_PrevAndNext(session, tasksEntry, groupId,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry getByG_U_PrevAndNext(Session session,
		TasksEntry tasksEntry, long groupId, long userId,
		OrderByComparator<TasksEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_U_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_USERID_2);

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
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the tasks entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the matching tasks entries that the user has permission to view
	 */
	@Override
	public List<TasksEntry> filterFindByG_U(long groupId, long userId) {
		return filterFindByG_U(groupId, userId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries that the user has permission to view
	 */
	@Override
	public List<TasksEntry> filterFindByG_U(long groupId, long userId,
		int start, int end) {
		return filterFindByG_U(groupId, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries that the user has permissions to view where groupId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries that the user has permission to view
	 */
	@Override
	public List<TasksEntry> filterFindByG_U(long groupId, long userId,
		int start, int end, OrderByComparator<TasksEntry> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_U(groupId, userId, start, end, orderByComparator);
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
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_U_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_USERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, TasksEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, TasksEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(userId);

			return (List<TasksEntry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set of tasks entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] filterFindByG_U_PrevAndNext(long tasksEntryId,
		long groupId, long userId,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_U_PrevAndNext(tasksEntryId, groupId, userId,
				orderByComparator);
		}

		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = filterGetByG_U_PrevAndNext(session, tasksEntry, groupId,
					userId, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = filterGetByG_U_PrevAndNext(session, tasksEntry, groupId,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry filterGetByG_U_PrevAndNext(Session session,
		TasksEntry tasksEntry, long groupId, long userId,
		OrderByComparator<TasksEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_U_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_USERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, TasksEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, TasksEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the tasks entries where groupId = &#63; and userId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 */
	@Override
	public void removeByG_U(long groupId, long userId) {
		for (TasksEntry tasksEntry : findByG_U(groupId, userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(tasksEntry);
		}
	}

	/**
	 * Returns the number of tasks entries where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByG_U(long groupId, long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_U;

		Object[] finderArgs = new Object[] { groupId, userId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_U_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

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
	 * Returns the number of tasks entries that the user has permission to view where groupId = &#63; and userId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching tasks entries that the user has permission to view
	 */
	@Override
	public int filterCountByG_U(long groupId, long userId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_U(groupId, userId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_U_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_USERID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(userId);

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

	private static final String _FINDER_COLUMN_G_U_GROUPID_2 = "tasksEntry.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_USERID_2 = "tasksEntry.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_A = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_A",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_A = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_A",
			new String[] { Long.class.getName(), Long.class.getName() },
			TasksEntryModelImpl.GROUPID_COLUMN_BITMASK |
			TasksEntryModelImpl.ASSIGNEEUSERID_COLUMN_BITMASK |
			TasksEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TasksEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TasksEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_A = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_A",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_A(long groupId, long assigneeUserId) {
		return findByG_A(groupId, assigneeUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_A(long groupId, long assigneeUserId,
		int start, int end) {
		return findByG_A(groupId, assigneeUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_A(long groupId, long assigneeUserId,
		int start, int end, OrderByComparator<TasksEntry> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_A;
			finderArgs = new Object[] { groupId, assigneeUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_A;
			finderArgs = new Object[] {
					groupId, assigneeUserId,
					
					start, end, orderByComparator
				};
		}

		List<TasksEntry> list = (List<TasksEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TasksEntry tasksEntry : list) {
				if ((groupId != tasksEntry.getGroupId()) ||
						(assigneeUserId != tasksEntry.getAssigneeUserId())) {
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

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_A_GROUPID_2);

			query.append(_FINDER_COLUMN_G_A_ASSIGNEEUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(assigneeUserId);

				if (!pagination) {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByG_A_First(long groupId, long assigneeUserId,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = fetchByG_A_First(groupId, assigneeUserId,
				orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", assigneeUserId=");
		msg.append(assigneeUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the first tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByG_A_First(long groupId, long assigneeUserId,
		OrderByComparator<TasksEntry> orderByComparator) {
		List<TasksEntry> list = findByG_A(groupId, assigneeUserId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByG_A_Last(long groupId, long assigneeUserId,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = fetchByG_A_Last(groupId, assigneeUserId,
				orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", assigneeUserId=");
		msg.append(assigneeUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the last tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByG_A_Last(long groupId, long assigneeUserId,
		OrderByComparator<TasksEntry> orderByComparator) {
		int count = countByG_A(groupId, assigneeUserId);

		if (count == 0) {
			return null;
		}

		List<TasksEntry> list = findByG_A(groupId, assigneeUserId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] findByG_A_PrevAndNext(long tasksEntryId, long groupId,
		long assigneeUserId, OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByG_A_PrevAndNext(session, tasksEntry, groupId,
					assigneeUserId, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = getByG_A_PrevAndNext(session, tasksEntry, groupId,
					assigneeUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry getByG_A_PrevAndNext(Session session,
		TasksEntry tasksEntry, long groupId, long assigneeUserId,
		OrderByComparator<TasksEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_A_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_ASSIGNEEUSERID_2);

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
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(assigneeUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @return the matching tasks entries that the user has permission to view
	 */
	@Override
	public List<TasksEntry> filterFindByG_A(long groupId, long assigneeUserId) {
		return filterFindByG_A(groupId, assigneeUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries that the user has permission to view
	 */
	@Override
	public List<TasksEntry> filterFindByG_A(long groupId, long assigneeUserId,
		int start, int end) {
		return filterFindByG_A(groupId, assigneeUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries that the user has permissions to view where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries that the user has permission to view
	 */
	@Override
	public List<TasksEntry> filterFindByG_A(long groupId, long assigneeUserId,
		int start, int end, OrderByComparator<TasksEntry> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_A(groupId, assigneeUserId, start, end,
				orderByComparator);
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
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_A_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_ASSIGNEEUSERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, TasksEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, TasksEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(assigneeUserId);

			return (List<TasksEntry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set of tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] filterFindByG_A_PrevAndNext(long tasksEntryId,
		long groupId, long assigneeUserId,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_A_PrevAndNext(tasksEntryId, groupId, assigneeUserId,
				orderByComparator);
		}

		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = filterGetByG_A_PrevAndNext(session, tasksEntry, groupId,
					assigneeUserId, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = filterGetByG_A_PrevAndNext(session, tasksEntry, groupId,
					assigneeUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry filterGetByG_A_PrevAndNext(Session session,
		TasksEntry tasksEntry, long groupId, long assigneeUserId,
		OrderByComparator<TasksEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_A_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_ASSIGNEEUSERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, TasksEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, TasksEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(assigneeUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the tasks entries where groupId = &#63; and assigneeUserId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 */
	@Override
	public void removeByG_A(long groupId, long assigneeUserId) {
		for (TasksEntry tasksEntry : findByG_A(groupId, assigneeUserId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(tasksEntry);
		}
	}

	/**
	 * Returns the number of tasks entries where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByG_A(long groupId, long assigneeUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_A;

		Object[] finderArgs = new Object[] { groupId, assigneeUserId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_A_GROUPID_2);

			query.append(_FINDER_COLUMN_G_A_ASSIGNEEUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(assigneeUserId);

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
	 * Returns the number of tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @return the number of matching tasks entries that the user has permission to view
	 */
	@Override
	public int filterCountByG_A(long groupId, long assigneeUserId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_A(groupId, assigneeUserId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_A_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_ASSIGNEEUSERID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(assigneeUserId);

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

	private static final String _FINDER_COLUMN_G_A_GROUPID_2 = "tasksEntry.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_A_ASSIGNEEUSERID_2 = "tasksEntry.assigneeUserId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_R = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_R",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_R",
			new String[] { Long.class.getName(), Long.class.getName() },
			TasksEntryModelImpl.GROUPID_COLUMN_BITMASK |
			TasksEntryModelImpl.RESOLVERUSERID_COLUMN_BITMASK |
			TasksEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TasksEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TasksEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_R = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_R",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_R(long groupId, long resolverUserId) {
		return findByG_R(groupId, resolverUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_R(long groupId, long resolverUserId,
		int start, int end) {
		return findByG_R(groupId, resolverUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_R(long groupId, long resolverUserId,
		int start, int end, OrderByComparator<TasksEntry> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R;
			finderArgs = new Object[] { groupId, resolverUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_R;
			finderArgs = new Object[] {
					groupId, resolverUserId,
					
					start, end, orderByComparator
				};
		}

		List<TasksEntry> list = (List<TasksEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TasksEntry tasksEntry : list) {
				if ((groupId != tasksEntry.getGroupId()) ||
						(resolverUserId != tasksEntry.getResolverUserId())) {
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

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_R_GROUPID_2);

			query.append(_FINDER_COLUMN_G_R_RESOLVERUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(resolverUserId);

				if (!pagination) {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByG_R_First(long groupId, long resolverUserId,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = fetchByG_R_First(groupId, resolverUserId,
				orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", resolverUserId=");
		msg.append(resolverUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the first tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByG_R_First(long groupId, long resolverUserId,
		OrderByComparator<TasksEntry> orderByComparator) {
		List<TasksEntry> list = findByG_R(groupId, resolverUserId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByG_R_Last(long groupId, long resolverUserId,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = fetchByG_R_Last(groupId, resolverUserId,
				orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", resolverUserId=");
		msg.append(resolverUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the last tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByG_R_Last(long groupId, long resolverUserId,
		OrderByComparator<TasksEntry> orderByComparator) {
		int count = countByG_R(groupId, resolverUserId);

		if (count == 0) {
			return null;
		}

		List<TasksEntry> list = findByG_R(groupId, resolverUserId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] findByG_R_PrevAndNext(long tasksEntryId, long groupId,
		long resolverUserId, OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByG_R_PrevAndNext(session, tasksEntry, groupId,
					resolverUserId, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = getByG_R_PrevAndNext(session, tasksEntry, groupId,
					resolverUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry getByG_R_PrevAndNext(Session session,
		TasksEntry tasksEntry, long groupId, long resolverUserId,
		OrderByComparator<TasksEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_R_GROUPID_2);

		query.append(_FINDER_COLUMN_G_R_RESOLVERUSERID_2);

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
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(resolverUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the tasks entries that the user has permission to view where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @return the matching tasks entries that the user has permission to view
	 */
	@Override
	public List<TasksEntry> filterFindByG_R(long groupId, long resolverUserId) {
		return filterFindByG_R(groupId, resolverUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries that the user has permission to view where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries that the user has permission to view
	 */
	@Override
	public List<TasksEntry> filterFindByG_R(long groupId, long resolverUserId,
		int start, int end) {
		return filterFindByG_R(groupId, resolverUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries that the user has permissions to view where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries that the user has permission to view
	 */
	@Override
	public List<TasksEntry> filterFindByG_R(long groupId, long resolverUserId,
		int start, int end, OrderByComparator<TasksEntry> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_R(groupId, resolverUserId, start, end,
				orderByComparator);
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
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_R_GROUPID_2);

		query.append(_FINDER_COLUMN_G_R_RESOLVERUSERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, TasksEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, TasksEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(resolverUserId);

			return (List<TasksEntry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set of tasks entries that the user has permission to view where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] filterFindByG_R_PrevAndNext(long tasksEntryId,
		long groupId, long resolverUserId,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_R_PrevAndNext(tasksEntryId, groupId, resolverUserId,
				orderByComparator);
		}

		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = filterGetByG_R_PrevAndNext(session, tasksEntry, groupId,
					resolverUserId, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = filterGetByG_R_PrevAndNext(session, tasksEntry, groupId,
					resolverUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry filterGetByG_R_PrevAndNext(Session session,
		TasksEntry tasksEntry, long groupId, long resolverUserId,
		OrderByComparator<TasksEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_R_GROUPID_2);

		query.append(_FINDER_COLUMN_G_R_RESOLVERUSERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, TasksEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, TasksEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(resolverUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the tasks entries where groupId = &#63; and resolverUserId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 */
	@Override
	public void removeByG_R(long groupId, long resolverUserId) {
		for (TasksEntry tasksEntry : findByG_R(groupId, resolverUserId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(tasksEntry);
		}
	}

	/**
	 * Returns the number of tasks entries where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByG_R(long groupId, long resolverUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_R;

		Object[] finderArgs = new Object[] { groupId, resolverUserId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_R_GROUPID_2);

			query.append(_FINDER_COLUMN_G_R_RESOLVERUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(resolverUserId);

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
	 * Returns the number of tasks entries that the user has permission to view where groupId = &#63; and resolverUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param resolverUserId the resolver user ID
	 * @return the number of matching tasks entries that the user has permission to view
	 */
	@Override
	public int filterCountByG_R(long groupId, long resolverUserId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_R(groupId, resolverUserId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_R_GROUPID_2);

		query.append(_FINDER_COLUMN_G_R_RESOLVERUSERID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(resolverUserId);

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

	private static final String _FINDER_COLUMN_G_R_GROUPID_2 = "tasksEntry.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_R_RESOLVERUSERID_2 = "tasksEntry.resolverUserId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_S = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_S = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			TasksEntryModelImpl.USERID_COLUMN_BITMASK |
			TasksEntryModelImpl.STATUS_COLUMN_BITMASK |
			TasksEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TasksEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TasksEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_S = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_S = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByU_S",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the tasks entries where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByU_S(long userId, int status) {
		return findByU_S(userId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the tasks entries where userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByU_S(long userId, int status, int start,
		int end) {
		return findByU_S(userId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByU_S(long userId, int status, int start,
		int end, OrderByComparator<TasksEntry> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_S;
			finderArgs = new Object[] { userId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_S;
			finderArgs = new Object[] {
					userId, status,
					
					start, end, orderByComparator
				};
		}

		List<TasksEntry> list = (List<TasksEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TasksEntry tasksEntry : list) {
				if ((userId != tasksEntry.getUserId()) ||
						(status != tasksEntry.getStatus())) {
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

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_U_S_USERID_2);

			query.append(_FINDER_COLUMN_U_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(status);

				if (!pagination) {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first tasks entry in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByU_S_First(long userId, int status,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = fetchByU_S_First(userId, status,
				orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the first tasks entry in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByU_S_First(long userId, int status,
		OrderByComparator<TasksEntry> orderByComparator) {
		List<TasksEntry> list = findByU_S(userId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last tasks entry in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByU_S_Last(long userId, int status,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = fetchByU_S_Last(userId, status,
				orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the last tasks entry in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByU_S_Last(long userId, int status,
		OrderByComparator<TasksEntry> orderByComparator) {
		int count = countByU_S(userId, status);

		if (count == 0) {
			return null;
		}

		List<TasksEntry> list = findByU_S(userId, status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set where userId = &#63; and status = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] findByU_S_PrevAndNext(long tasksEntryId, long userId,
		int status, OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByU_S_PrevAndNext(session, tasksEntry, userId,
					status, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = getByU_S_PrevAndNext(session, tasksEntry, userId,
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

	protected TasksEntry getByU_S_PrevAndNext(Session session,
		TasksEntry tasksEntry, long userId, int status,
		OrderByComparator<TasksEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_U_S_USERID_2);

		query.append(_FINDER_COLUMN_U_S_STATUS_2);

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
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the tasks entries where userId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByU_S(long userId, int[] statuses) {
		return findByU_S(userId, statuses, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries where userId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByU_S(long userId, int[] statuses, int start,
		int end) {
		return findByU_S(userId, statuses, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where userId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByU_S(long userId, int[] statuses, int start,
		int end, OrderByComparator<TasksEntry> orderByComparator) {
		if (statuses == null) {
			statuses = new int[0];
		}
		else {
			statuses = ArrayUtil.unique(statuses);
		}

		if (statuses.length == 1) {
			return findByU_S(userId, statuses[0], start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { userId, StringUtil.merge(statuses) };
		}
		else {
			finderArgs = new Object[] {
					userId, StringUtil.merge(statuses),
					
					start, end, orderByComparator
				};
		}

		List<TasksEntry> list = (List<TasksEntry>)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_U_S,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TasksEntry tasksEntry : list) {
				if ((userId != tasksEntry.getUserId()) ||
						!ArrayUtil.contains(statuses, tasksEntry.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_U_S_USERID_2);

			if (statuses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_U_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_U_S,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_U_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the tasks entries where userId = &#63; and status = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param status the status
	 */
	@Override
	public void removeByU_S(long userId, int status) {
		for (TasksEntry tasksEntry : findByU_S(userId, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(tasksEntry);
		}
	}

	/**
	 * Returns the number of tasks entries where userId = &#63; and status = &#63;.
	 *
	 * @param userId the user ID
	 * @param status the status
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByU_S(long userId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_S;

		Object[] finderArgs = new Object[] { userId, status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_U_S_USERID_2);

			query.append(_FINDER_COLUMN_U_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

	/**
	 * Returns the number of tasks entries where userId = &#63; and status = any &#63;.
	 *
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByU_S(long userId, int[] statuses) {
		if (statuses == null) {
			statuses = new int[0];
		}
		else {
			statuses = ArrayUtil.unique(statuses);
		}

		Object[] finderArgs = new Object[] { userId, StringUtil.merge(statuses) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_U_S_USERID_2);

			if (statuses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_U_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_S,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_U_S_USERID_2 = "tasksEntry.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_S_STATUS_2 = "tasksEntry.status = ?";
	private static final String _FINDER_COLUMN_U_S_STATUS_7 = "tasksEntry.status IN (";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_A_S = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByA_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_A_S = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByA_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			TasksEntryModelImpl.ASSIGNEEUSERID_COLUMN_BITMASK |
			TasksEntryModelImpl.STATUS_COLUMN_BITMASK |
			TasksEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TasksEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TasksEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_A_S = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByA_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_A_S = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByA_S",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the tasks entries where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByA_S(long assigneeUserId, int status) {
		return findByA_S(assigneeUserId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries where assigneeUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByA_S(long assigneeUserId, int status,
		int start, int end) {
		return findByA_S(assigneeUserId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where assigneeUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByA_S(long assigneeUserId, int status,
		int start, int end, OrderByComparator<TasksEntry> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_A_S;
			finderArgs = new Object[] { assigneeUserId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_A_S;
			finderArgs = new Object[] {
					assigneeUserId, status,
					
					start, end, orderByComparator
				};
		}

		List<TasksEntry> list = (List<TasksEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TasksEntry tasksEntry : list) {
				if ((assigneeUserId != tasksEntry.getAssigneeUserId()) ||
						(status != tasksEntry.getStatus())) {
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

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_A_S_ASSIGNEEUSERID_2);

			query.append(_FINDER_COLUMN_A_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeUserId);

				qPos.add(status);

				if (!pagination) {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first tasks entry in the ordered set where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByA_S_First(long assigneeUserId, int status,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = fetchByA_S_First(assigneeUserId, status,
				orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assigneeUserId=");
		msg.append(assigneeUserId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the first tasks entry in the ordered set where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByA_S_First(long assigneeUserId, int status,
		OrderByComparator<TasksEntry> orderByComparator) {
		List<TasksEntry> list = findByA_S(assigneeUserId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last tasks entry in the ordered set where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByA_S_Last(long assigneeUserId, int status,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = fetchByA_S_Last(assigneeUserId, status,
				orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assigneeUserId=");
		msg.append(assigneeUserId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the last tasks entry in the ordered set where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByA_S_Last(long assigneeUserId, int status,
		OrderByComparator<TasksEntry> orderByComparator) {
		int count = countByA_S(assigneeUserId, status);

		if (count == 0) {
			return null;
		}

		List<TasksEntry> list = findByA_S(assigneeUserId, status, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] findByA_S_PrevAndNext(long tasksEntryId,
		long assigneeUserId, int status,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByA_S_PrevAndNext(session, tasksEntry,
					assigneeUserId, status, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = getByA_S_PrevAndNext(session, tasksEntry,
					assigneeUserId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry getByA_S_PrevAndNext(Session session,
		TasksEntry tasksEntry, long assigneeUserId, int status,
		OrderByComparator<TasksEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_A_S_ASSIGNEEUSERID_2);

		query.append(_FINDER_COLUMN_A_S_STATUS_2);

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
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(assigneeUserId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the tasks entries where assigneeUserId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByA_S(long assigneeUserId, int[] statuses) {
		return findByA_S(assigneeUserId, statuses, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries where assigneeUserId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByA_S(long assigneeUserId, int[] statuses,
		int start, int end) {
		return findByA_S(assigneeUserId, statuses, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where assigneeUserId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByA_S(long assigneeUserId, int[] statuses,
		int start, int end, OrderByComparator<TasksEntry> orderByComparator) {
		if (statuses == null) {
			statuses = new int[0];
		}
		else {
			statuses = ArrayUtil.unique(statuses);
		}

		if (statuses.length == 1) {
			return findByA_S(assigneeUserId, statuses[0], start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { assigneeUserId, StringUtil.merge(statuses) };
		}
		else {
			finderArgs = new Object[] {
					assigneeUserId, StringUtil.merge(statuses),
					
					start, end, orderByComparator
				};
		}

		List<TasksEntry> list = (List<TasksEntry>)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_A_S,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TasksEntry tasksEntry : list) {
				if ((assigneeUserId != tasksEntry.getAssigneeUserId()) ||
						!ArrayUtil.contains(statuses, tasksEntry.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_A_S_ASSIGNEEUSERID_2);

			if (statuses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_A_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeUserId);

				if (!pagination) {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_A_S,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_A_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the tasks entries where assigneeUserId = &#63; and status = &#63; from the database.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 */
	@Override
	public void removeByA_S(long assigneeUserId, int status) {
		for (TasksEntry tasksEntry : findByA_S(assigneeUserId, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(tasksEntry);
		}
	}

	/**
	 * Returns the number of tasks entries where assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByA_S(long assigneeUserId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_A_S;

		Object[] finderArgs = new Object[] { assigneeUserId, status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_A_S_ASSIGNEEUSERID_2);

			query.append(_FINDER_COLUMN_A_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeUserId);

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

	/**
	 * Returns the number of tasks entries where assigneeUserId = &#63; and status = any &#63;.
	 *
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByA_S(long assigneeUserId, int[] statuses) {
		if (statuses == null) {
			statuses = new int[0];
		}
		else {
			statuses = ArrayUtil.unique(statuses);
		}

		Object[] finderArgs = new Object[] {
				assigneeUserId, StringUtil.merge(statuses)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_A_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_A_S_ASSIGNEEUSERID_2);

			if (statuses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_A_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assigneeUserId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_A_S,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_A_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_A_S_ASSIGNEEUSERID_2 = "tasksEntry.assigneeUserId = ? AND ";
	private static final String _FINDER_COLUMN_A_S_STATUS_2 = "tasksEntry.status = ?";
	private static final String _FINDER_COLUMN_A_S_STATUS_7 = "tasksEntry.status IN (";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U_S = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_U_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_S = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_U_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			TasksEntryModelImpl.GROUPID_COLUMN_BITMASK |
			TasksEntryModelImpl.USERID_COLUMN_BITMASK |
			TasksEntryModelImpl.STATUS_COLUMN_BITMASK |
			TasksEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TasksEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TasksEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_U_S = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_U_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_U_S = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_U_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the tasks entries where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_U_S(long groupId, long userId, int status) {
		return findByG_U_S(groupId, userId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_U_S(long groupId, long userId, int status,
		int start, int end) {
		return findByG_U_S(groupId, userId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_U_S(long groupId, long userId, int status,
		int start, int end, OrderByComparator<TasksEntry> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_S;
			finderArgs = new Object[] { groupId, userId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U_S;
			finderArgs = new Object[] {
					groupId, userId, status,
					
					start, end, orderByComparator
				};
		}

		List<TasksEntry> list = (List<TasksEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TasksEntry tasksEntry : list) {
				if ((groupId != tasksEntry.getGroupId()) ||
						(userId != tasksEntry.getUserId()) ||
						(status != tasksEntry.getStatus())) {
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

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_S_USERID_2);

			query.append(_FINDER_COLUMN_G_U_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				qPos.add(status);

				if (!pagination) {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first tasks entry in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByG_U_S_First(long groupId, long userId, int status,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = fetchByG_U_S_First(groupId, userId, status,
				orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the first tasks entry in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByG_U_S_First(long groupId, long userId, int status,
		OrderByComparator<TasksEntry> orderByComparator) {
		List<TasksEntry> list = findByG_U_S(groupId, userId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last tasks entry in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByG_U_S_Last(long groupId, long userId, int status,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = fetchByG_U_S_Last(groupId, userId, status,
				orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the last tasks entry in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByG_U_S_Last(long groupId, long userId, int status,
		OrderByComparator<TasksEntry> orderByComparator) {
		int count = countByG_U_S(groupId, userId, status);

		if (count == 0) {
			return null;
		}

		List<TasksEntry> list = findByG_U_S(groupId, userId, status, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] findByG_U_S_PrevAndNext(long tasksEntryId,
		long groupId, long userId, int status,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByG_U_S_PrevAndNext(session, tasksEntry, groupId,
					userId, status, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = getByG_U_S_PrevAndNext(session, tasksEntry, groupId,
					userId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry getByG_U_S_PrevAndNext(Session session,
		TasksEntry tasksEntry, long groupId, long userId, int status,
		OrderByComparator<TasksEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_S_USERID_2);

		query.append(_FINDER_COLUMN_G_U_S_STATUS_2);

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
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the tasks entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @return the matching tasks entries that the user has permission to view
	 */
	@Override
	public List<TasksEntry> filterFindByG_U_S(long groupId, long userId,
		int status) {
		return filterFindByG_U_S(groupId, userId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries that the user has permission to view
	 */
	@Override
	public List<TasksEntry> filterFindByG_U_S(long groupId, long userId,
		int status, int start, int end) {
		return filterFindByG_U_S(groupId, userId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries that the user has permissions to view where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries that the user has permission to view
	 */
	@Override
	public List<TasksEntry> filterFindByG_U_S(long groupId, long userId,
		int status, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_U_S(groupId, userId, status, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_S_USERID_2);

		query.append(_FINDER_COLUMN_G_U_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, TasksEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, TasksEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(userId);

			qPos.add(status);

			return (List<TasksEntry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set of tasks entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] filterFindByG_U_S_PrevAndNext(long tasksEntryId,
		long groupId, long userId, int status,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_U_S_PrevAndNext(tasksEntryId, groupId, userId,
				status, orderByComparator);
		}

		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = filterGetByG_U_S_PrevAndNext(session, tasksEntry,
					groupId, userId, status, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = filterGetByG_U_S_PrevAndNext(session, tasksEntry,
					groupId, userId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry filterGetByG_U_S_PrevAndNext(Session session,
		TasksEntry tasksEntry, long groupId, long userId, int status,
		OrderByComparator<TasksEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_S_USERID_2);

		query.append(_FINDER_COLUMN_G_U_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, TasksEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, TasksEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(userId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the tasks entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @return the matching tasks entries that the user has permission to view
	 */
	@Override
	public List<TasksEntry> filterFindByG_U_S(long groupId, long userId,
		int[] statuses) {
		return filterFindByG_U_S(groupId, userId, statuses, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries that the user has permission to view
	 */
	@Override
	public List<TasksEntry> filterFindByG_U_S(long groupId, long userId,
		int[] statuses, int start, int end) {
		return filterFindByG_U_S(groupId, userId, statuses, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries that the user has permission to view
	 */
	@Override
	public List<TasksEntry> filterFindByG_U_S(long groupId, long userId,
		int[] statuses, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_U_S(groupId, userId, statuses, start, end,
				orderByComparator);
		}

		if (statuses == null) {
			statuses = new int[0];
		}
		else {
			statuses = ArrayUtil.unique(statuses);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_S_USERID_2);

		if (statuses.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			query.append(_FINDER_COLUMN_G_U_S_STATUS_7);

			query.append(StringUtil.merge(statuses));

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(StringPool.CLOSE_PARENTHESIS);
		}

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, TasksEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, TasksEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(userId);

			return (List<TasksEntry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the tasks entries where groupId = &#63; and userId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_U_S(long groupId, long userId,
		int[] statuses) {
		return findByG_U_S(groupId, userId, statuses, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries where groupId = &#63; and userId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_U_S(long groupId, long userId,
		int[] statuses, int start, int end) {
		return findByG_U_S(groupId, userId, statuses, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where groupId = &#63; and userId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_U_S(long groupId, long userId,
		int[] statuses, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator) {
		if (statuses == null) {
			statuses = new int[0];
		}
		else {
			statuses = ArrayUtil.unique(statuses);
		}

		if (statuses.length == 1) {
			return findByG_U_S(groupId, userId, statuses[0], start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					groupId, userId, StringUtil.merge(statuses)
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, userId, StringUtil.merge(statuses),
					
					start, end, orderByComparator
				};
		}

		List<TasksEntry> list = (List<TasksEntry>)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U_S,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TasksEntry tasksEntry : list) {
				if ((groupId != tasksEntry.getGroupId()) ||
						(userId != tasksEntry.getUserId()) ||
						!ArrayUtil.contains(statuses, tasksEntry.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_S_USERID_2);

			if (statuses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_G_U_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				if (!pagination) {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U_S,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_U_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the tasks entries where groupId = &#63; and userId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 */
	@Override
	public void removeByG_U_S(long groupId, long userId, int status) {
		for (TasksEntry tasksEntry : findByG_U_S(groupId, userId, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(tasksEntry);
		}
	}

	/**
	 * Returns the number of tasks entries where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByG_U_S(long groupId, long userId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_U_S;

		Object[] finderArgs = new Object[] { groupId, userId, status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_S_USERID_2);

			query.append(_FINDER_COLUMN_G_U_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

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

	/**
	 * Returns the number of tasks entries where groupId = &#63; and userId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByG_U_S(long groupId, long userId, int[] statuses) {
		if (statuses == null) {
			statuses = new int[0];
		}
		else {
			statuses = ArrayUtil.unique(statuses);
		}

		Object[] finderArgs = new Object[] {
				groupId, userId, StringUtil.merge(statuses)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_U_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_U_S_USERID_2);

			if (statuses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_G_U_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(userId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_U_S,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_U_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of tasks entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param status the status
	 * @return the number of matching tasks entries that the user has permission to view
	 */
	@Override
	public int filterCountByG_U_S(long groupId, long userId, int status) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_U_S(groupId, userId, status);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_S_USERID_2);

		query.append(_FINDER_COLUMN_G_U_S_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(userId);

			qPos.add(status);

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

	/**
	 * Returns the number of tasks entries that the user has permission to view where groupId = &#63; and userId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param statuses the statuses
	 * @return the number of matching tasks entries that the user has permission to view
	 */
	@Override
	public int filterCountByG_U_S(long groupId, long userId, int[] statuses) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_U_S(groupId, userId, statuses);
		}

		if (statuses == null) {
			statuses = new int[0];
		}
		else {
			statuses = ArrayUtil.unique(statuses);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_U_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_U_S_USERID_2);

		if (statuses.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			query.append(_FINDER_COLUMN_G_U_S_STATUS_7);

			query.append(StringUtil.merge(statuses));

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(StringPool.CLOSE_PARENTHESIS);
		}

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(userId);

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

	private static final String _FINDER_COLUMN_G_U_S_GROUPID_2 = "tasksEntry.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_S_USERID_2 = "tasksEntry.userId = ? AND ";
	private static final String _FINDER_COLUMN_G_U_S_STATUS_2 = "tasksEntry.status = ?";
	private static final String _FINDER_COLUMN_G_U_S_STATUS_7 = "tasksEntry.status IN (";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_A_S = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_A_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_A_S = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, TasksEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_A_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			TasksEntryModelImpl.GROUPID_COLUMN_BITMASK |
			TasksEntryModelImpl.ASSIGNEEUSERID_COLUMN_BITMASK |
			TasksEntryModelImpl.STATUS_COLUMN_BITMASK |
			TasksEntryModelImpl.PRIORITY_COLUMN_BITMASK |
			TasksEntryModelImpl.DUEDATE_COLUMN_BITMASK |
			TasksEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_A_S = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_A_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_A_S = new FinderPath(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_A_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_A_S(long groupId, long assigneeUserId,
		int status) {
		return findByG_A_S(groupId, assigneeUserId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_A_S(long groupId, long assigneeUserId,
		int status, int start, int end) {
		return findByG_A_S(groupId, assigneeUserId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_A_S(long groupId, long assigneeUserId,
		int status, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_A_S;
			finderArgs = new Object[] { groupId, assigneeUserId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_A_S;
			finderArgs = new Object[] {
					groupId, assigneeUserId, status,
					
					start, end, orderByComparator
				};
		}

		List<TasksEntry> list = (List<TasksEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TasksEntry tasksEntry : list) {
				if ((groupId != tasksEntry.getGroupId()) ||
						(assigneeUserId != tasksEntry.getAssigneeUserId()) ||
						(status != tasksEntry.getStatus())) {
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

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_A_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_A_S_ASSIGNEEUSERID_2);

			query.append(_FINDER_COLUMN_G_A_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(assigneeUserId);

				qPos.add(status);

				if (!pagination) {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByG_A_S_First(long groupId, long assigneeUserId,
		int status, OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = fetchByG_A_S_First(groupId, assigneeUserId,
				status, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", assigneeUserId=");
		msg.append(assigneeUserId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the first tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByG_A_S_First(long groupId, long assigneeUserId,
		int status, OrderByComparator<TasksEntry> orderByComparator) {
		List<TasksEntry> list = findByG_A_S(groupId, assigneeUserId, status, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry
	 * @throws NoSuchTasksEntryException if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry findByG_A_S_Last(long groupId, long assigneeUserId,
		int status, OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = fetchByG_A_S_Last(groupId, assigneeUserId,
				status, orderByComparator);

		if (tasksEntry != null) {
			return tasksEntry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", assigneeUserId=");
		msg.append(assigneeUserId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTasksEntryException(msg.toString());
	}

	/**
	 * Returns the last tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching tasks entry, or <code>null</code> if a matching tasks entry could not be found
	 */
	@Override
	public TasksEntry fetchByG_A_S_Last(long groupId, long assigneeUserId,
		int status, OrderByComparator<TasksEntry> orderByComparator) {
		int count = countByG_A_S(groupId, assigneeUserId, status);

		if (count == 0) {
			return null;
		}

		List<TasksEntry> list = findByG_A_S(groupId, assigneeUserId, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] findByG_A_S_PrevAndNext(long tasksEntryId,
		long groupId, long assigneeUserId, int status,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = getByG_A_S_PrevAndNext(session, tasksEntry, groupId,
					assigneeUserId, status, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = getByG_A_S_PrevAndNext(session, tasksEntry, groupId,
					assigneeUserId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry getByG_A_S_PrevAndNext(Session session,
		TasksEntry tasksEntry, long groupId, long assigneeUserId, int status,
		OrderByComparator<TasksEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_A_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_S_ASSIGNEEUSERID_2);

		query.append(_FINDER_COLUMN_G_A_S_STATUS_2);

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
			query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(assigneeUserId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the matching tasks entries that the user has permission to view
	 */
	@Override
	public List<TasksEntry> filterFindByG_A_S(long groupId,
		long assigneeUserId, int status) {
		return filterFindByG_A_S(groupId, assigneeUserId, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries that the user has permission to view
	 */
	@Override
	public List<TasksEntry> filterFindByG_A_S(long groupId,
		long assigneeUserId, int status, int start, int end) {
		return filterFindByG_A_S(groupId, assigneeUserId, status, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the tasks entries that the user has permissions to view where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries that the user has permission to view
	 */
	@Override
	public List<TasksEntry> filterFindByG_A_S(long groupId,
		long assigneeUserId, int status, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_A_S(groupId, assigneeUserId, status, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_A_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_S_ASSIGNEEUSERID_2);

		query.append(_FINDER_COLUMN_G_A_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, TasksEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, TasksEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(assigneeUserId);

			qPos.add(status);

			return (List<TasksEntry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the tasks entries before and after the current tasks entry in the ordered set of tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param tasksEntryId the primary key of the current tasks entry
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry[] filterFindByG_A_S_PrevAndNext(long tasksEntryId,
		long groupId, long assigneeUserId, int status,
		OrderByComparator<TasksEntry> orderByComparator)
		throws NoSuchTasksEntryException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_A_S_PrevAndNext(tasksEntryId, groupId,
				assigneeUserId, status, orderByComparator);
		}

		TasksEntry tasksEntry = findByPrimaryKey(tasksEntryId);

		Session session = null;

		try {
			session = openSession();

			TasksEntry[] array = new TasksEntryImpl[3];

			array[0] = filterGetByG_A_S_PrevAndNext(session, tasksEntry,
					groupId, assigneeUserId, status, orderByComparator, true);

			array[1] = tasksEntry;

			array[2] = filterGetByG_A_S_PrevAndNext(session, tasksEntry,
					groupId, assigneeUserId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TasksEntry filterGetByG_A_S_PrevAndNext(Session session,
		TasksEntry tasksEntry, long groupId, long assigneeUserId, int status,
		OrderByComparator<TasksEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_A_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_S_ASSIGNEEUSERID_2);

		query.append(_FINDER_COLUMN_G_A_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, TasksEntryImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, TasksEntryImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(assigneeUserId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tasksEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TasksEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @return the matching tasks entries that the user has permission to view
	 */
	@Override
	public List<TasksEntry> filterFindByG_A_S(long groupId,
		long assigneeUserId, int[] statuses) {
		return filterFindByG_A_S(groupId, assigneeUserId, statuses,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries that the user has permission to view
	 */
	@Override
	public List<TasksEntry> filterFindByG_A_S(long groupId,
		long assigneeUserId, int[] statuses, int start, int end) {
		return filterFindByG_A_S(groupId, assigneeUserId, statuses, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries that the user has permission to view
	 */
	@Override
	public List<TasksEntry> filterFindByG_A_S(long groupId,
		long assigneeUserId, int[] statuses, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_A_S(groupId, assigneeUserId, statuses, start, end,
				orderByComparator);
		}

		if (statuses == null) {
			statuses = new int[0];
		}
		else {
			statuses = ArrayUtil.unique(statuses);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_A_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_S_ASSIGNEEUSERID_2);

		if (statuses.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			query.append(_FINDER_COLUMN_G_A_S_STATUS_7);

			query.append(StringUtil.merge(statuses));

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(StringPool.CLOSE_PARENTHESIS);
		}

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TasksEntryModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, TasksEntryImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, TasksEntryImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(assigneeUserId);

			return (List<TasksEntry>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @return the matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_A_S(long groupId, long assigneeUserId,
		int[] statuses) {
		return findByG_A_S(groupId, assigneeUserId, statuses,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_A_S(long groupId, long assigneeUserId,
		int[] statuses, int start, int end) {
		return findByG_A_S(groupId, assigneeUserId, statuses, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks entries
	 */
	@Override
	public List<TasksEntry> findByG_A_S(long groupId, long assigneeUserId,
		int[] statuses, int start, int end,
		OrderByComparator<TasksEntry> orderByComparator) {
		if (statuses == null) {
			statuses = new int[0];
		}
		else {
			statuses = ArrayUtil.unique(statuses);
		}

		if (statuses.length == 1) {
			return findByG_A_S(groupId, assigneeUserId, statuses[0], start,
				end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					groupId, assigneeUserId, StringUtil.merge(statuses)
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, assigneeUserId, StringUtil.merge(statuses),
					
					start, end, orderByComparator
				};
		}

		List<TasksEntry> list = (List<TasksEntry>)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_A_S,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (TasksEntry tasksEntry : list) {
				if ((groupId != tasksEntry.getGroupId()) ||
						(assigneeUserId != tasksEntry.getAssigneeUserId()) ||
						!ArrayUtil.contains(statuses, tasksEntry.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_A_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_A_S_ASSIGNEEUSERID_2);

			if (statuses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_G_A_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TasksEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(assigneeUserId);

				if (!pagination) {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_A_S,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_A_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 */
	@Override
	public void removeByG_A_S(long groupId, long assigneeUserId, int status) {
		for (TasksEntry tasksEntry : findByG_A_S(groupId, assigneeUserId,
				status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(tasksEntry);
		}
	}

	/**
	 * Returns the number of tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByG_A_S(long groupId, long assigneeUserId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_A_S;

		Object[] finderArgs = new Object[] { groupId, assigneeUserId, status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_A_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_A_S_ASSIGNEEUSERID_2);

			query.append(_FINDER_COLUMN_G_A_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(assigneeUserId);

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

	/**
	 * Returns the number of tasks entries where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @return the number of matching tasks entries
	 */
	@Override
	public int countByG_A_S(long groupId, long assigneeUserId, int[] statuses) {
		if (statuses == null) {
			statuses = new int[0];
		}
		else {
			statuses = ArrayUtil.unique(statuses);
		}

		Object[] finderArgs = new Object[] {
				groupId, assigneeUserId, StringUtil.merge(statuses)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_A_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_TASKSENTRY_WHERE);

			query.append(_FINDER_COLUMN_G_A_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_A_S_ASSIGNEEUSERID_2);

			if (statuses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_G_A_S_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(assigneeUserId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_A_S,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_A_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param status the status
	 * @return the number of matching tasks entries that the user has permission to view
	 */
	@Override
	public int filterCountByG_A_S(long groupId, long assigneeUserId, int status) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_A_S(groupId, assigneeUserId, status);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_A_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_S_ASSIGNEEUSERID_2);

		query.append(_FINDER_COLUMN_G_A_S_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(assigneeUserId);

			qPos.add(status);

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

	/**
	 * Returns the number of tasks entries that the user has permission to view where groupId = &#63; and assigneeUserId = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param assigneeUserId the assignee user ID
	 * @param statuses the statuses
	 * @return the number of matching tasks entries that the user has permission to view
	 */
	@Override
	public int filterCountByG_A_S(long groupId, long assigneeUserId,
		int[] statuses) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_A_S(groupId, assigneeUserId, statuses);
		}

		if (statuses == null) {
			statuses = new int[0];
		}
		else {
			statuses = ArrayUtil.unique(statuses);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_TASKSENTRY_WHERE);

		query.append(_FINDER_COLUMN_G_A_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_A_S_ASSIGNEEUSERID_2);

		if (statuses.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			query.append(_FINDER_COLUMN_G_A_S_STATUS_7);

			query.append(StringUtil.merge(statuses));

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(StringPool.CLOSE_PARENTHESIS);
		}

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				TasksEntry.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(assigneeUserId);

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

	private static final String _FINDER_COLUMN_G_A_S_GROUPID_2 = "tasksEntry.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_A_S_ASSIGNEEUSERID_2 = "tasksEntry.assigneeUserId = ? AND ";
	private static final String _FINDER_COLUMN_G_A_S_STATUS_2 = "tasksEntry.status = ?";
	private static final String _FINDER_COLUMN_G_A_S_STATUS_7 = "tasksEntry.status IN (";

	public TasksEntryPersistenceImpl() {
		setModelClass(TasksEntry.class);
	}

	/**
	 * Caches the tasks entry in the entity cache if it is enabled.
	 *
	 * @param tasksEntry the tasks entry
	 */
	@Override
	public void cacheResult(TasksEntry tasksEntry) {
		EntityCacheUtil.putResult(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryImpl.class, tasksEntry.getPrimaryKey(), tasksEntry);

		tasksEntry.resetOriginalValues();
	}

	/**
	 * Caches the tasks entries in the entity cache if it is enabled.
	 *
	 * @param tasksEntries the tasks entries
	 */
	@Override
	public void cacheResult(List<TasksEntry> tasksEntries) {
		for (TasksEntry tasksEntry : tasksEntries) {
			if (EntityCacheUtil.getResult(
						TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
						TasksEntryImpl.class, tasksEntry.getPrimaryKey()) == null) {
				cacheResult(tasksEntry);
			}
			else {
				tasksEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all tasks entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(TasksEntryImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the tasks entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TasksEntry tasksEntry) {
		EntityCacheUtil.removeResult(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryImpl.class, tasksEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<TasksEntry> tasksEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TasksEntry tasksEntry : tasksEntries) {
			EntityCacheUtil.removeResult(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
				TasksEntryImpl.class, tasksEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new tasks entry with the primary key. Does not add the tasks entry to the database.
	 *
	 * @param tasksEntryId the primary key for the new tasks entry
	 * @return the new tasks entry
	 */
	@Override
	public TasksEntry create(long tasksEntryId) {
		TasksEntry tasksEntry = new TasksEntryImpl();

		tasksEntry.setNew(true);
		tasksEntry.setPrimaryKey(tasksEntryId);

		return tasksEntry;
	}

	/**
	 * Removes the tasks entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param tasksEntryId the primary key of the tasks entry
	 * @return the tasks entry that was removed
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry remove(long tasksEntryId)
		throws NoSuchTasksEntryException {
		return remove((Serializable)tasksEntryId);
	}

	/**
	 * Removes the tasks entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the tasks entry
	 * @return the tasks entry that was removed
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry remove(Serializable primaryKey)
		throws NoSuchTasksEntryException {
		Session session = null;

		try {
			session = openSession();

			TasksEntry tasksEntry = (TasksEntry)session.get(TasksEntryImpl.class,
					primaryKey);

			if (tasksEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTasksEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(tasksEntry);
		}
		catch (NoSuchTasksEntryException nsee) {
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
	protected TasksEntry removeImpl(TasksEntry tasksEntry) {
		tasksEntry = toUnwrappedModel(tasksEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(tasksEntry)) {
				tasksEntry = (TasksEntry)session.get(TasksEntryImpl.class,
						tasksEntry.getPrimaryKeyObj());
			}

			if (tasksEntry != null) {
				session.delete(tasksEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (tasksEntry != null) {
			clearCache(tasksEntry);
		}

		return tasksEntry;
	}

	@Override
	public TasksEntry updateImpl(TasksEntry tasksEntry) {
		tasksEntry = toUnwrappedModel(tasksEntry);

		boolean isNew = tasksEntry.isNew();

		TasksEntryModelImpl tasksEntryModelImpl = (TasksEntryModelImpl)tasksEntry;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (tasksEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				tasksEntry.setCreateDate(now);
			}
			else {
				tasksEntry.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!tasksEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				tasksEntry.setModifiedDate(now);
			}
			else {
				tasksEntry.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (tasksEntry.isNew()) {
				session.save(tasksEntry);

				tasksEntry.setNew(false);
			}
			else {
				tasksEntry = (TasksEntry)session.merge(tasksEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !TasksEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((tasksEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						tasksEntryModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { tasksEntryModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((tasksEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						tasksEntryModelImpl.getOriginalUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { tasksEntryModelImpl.getUserId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((tasksEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSIGNEEUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						tasksEntryModelImpl.getOriginalAssigneeUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ASSIGNEEUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSIGNEEUSERID,
					args);

				args = new Object[] { tasksEntryModelImpl.getAssigneeUserId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ASSIGNEEUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSIGNEEUSERID,
					args);
			}

			if ((tasksEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOLVERUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						tasksEntryModelImpl.getOriginalResolverUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RESOLVERUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOLVERUSERID,
					args);

				args = new Object[] { tasksEntryModelImpl.getResolverUserId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RESOLVERUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOLVERUSERID,
					args);
			}

			if ((tasksEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						tasksEntryModelImpl.getOriginalGroupId(),
						tasksEntryModelImpl.getOriginalUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_U, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U,
					args);

				args = new Object[] {
						tasksEntryModelImpl.getGroupId(),
						tasksEntryModelImpl.getUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_U, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U,
					args);
			}

			if ((tasksEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						tasksEntryModelImpl.getOriginalGroupId(),
						tasksEntryModelImpl.getOriginalAssigneeUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_A,
					args);

				args = new Object[] {
						tasksEntryModelImpl.getGroupId(),
						tasksEntryModelImpl.getAssigneeUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_A,
					args);
			}

			if ((tasksEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						tasksEntryModelImpl.getOriginalGroupId(),
						tasksEntryModelImpl.getOriginalResolverUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_R, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R,
					args);

				args = new Object[] {
						tasksEntryModelImpl.getGroupId(),
						tasksEntryModelImpl.getResolverUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_R, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_R,
					args);
			}

			if ((tasksEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						tasksEntryModelImpl.getOriginalUserId(),
						tasksEntryModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_S,
					args);

				args = new Object[] {
						tasksEntryModelImpl.getUserId(),
						tasksEntryModelImpl.getStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_S,
					args);
			}

			if ((tasksEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_A_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						tasksEntryModelImpl.getOriginalAssigneeUserId(),
						tasksEntryModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_A_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_A_S,
					args);

				args = new Object[] {
						tasksEntryModelImpl.getAssigneeUserId(),
						tasksEntryModelImpl.getStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_A_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_A_S,
					args);
			}

			if ((tasksEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						tasksEntryModelImpl.getOriginalGroupId(),
						tasksEntryModelImpl.getOriginalUserId(),
						tasksEntryModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_U_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_S,
					args);

				args = new Object[] {
						tasksEntryModelImpl.getGroupId(),
						tasksEntryModelImpl.getUserId(),
						tasksEntryModelImpl.getStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_U_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_U_S,
					args);
			}

			if ((tasksEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_A_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						tasksEntryModelImpl.getOriginalGroupId(),
						tasksEntryModelImpl.getOriginalAssigneeUserId(),
						tasksEntryModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_A_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_A_S,
					args);

				args = new Object[] {
						tasksEntryModelImpl.getGroupId(),
						tasksEntryModelImpl.getAssigneeUserId(),
						tasksEntryModelImpl.getStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_A_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_A_S,
					args);
			}
		}

		EntityCacheUtil.putResult(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
			TasksEntryImpl.class, tasksEntry.getPrimaryKey(), tasksEntry, false);

		tasksEntry.resetOriginalValues();

		return tasksEntry;
	}

	protected TasksEntry toUnwrappedModel(TasksEntry tasksEntry) {
		if (tasksEntry instanceof TasksEntryImpl) {
			return tasksEntry;
		}

		TasksEntryImpl tasksEntryImpl = new TasksEntryImpl();

		tasksEntryImpl.setNew(tasksEntry.isNew());
		tasksEntryImpl.setPrimaryKey(tasksEntry.getPrimaryKey());

		tasksEntryImpl.setTasksEntryId(tasksEntry.getTasksEntryId());
		tasksEntryImpl.setGroupId(tasksEntry.getGroupId());
		tasksEntryImpl.setCompanyId(tasksEntry.getCompanyId());
		tasksEntryImpl.setUserId(tasksEntry.getUserId());
		tasksEntryImpl.setUserName(tasksEntry.getUserName());
		tasksEntryImpl.setCreateDate(tasksEntry.getCreateDate());
		tasksEntryImpl.setModifiedDate(tasksEntry.getModifiedDate());
		tasksEntryImpl.setTitle(tasksEntry.getTitle());
		tasksEntryImpl.setPriority(tasksEntry.getPriority());
		tasksEntryImpl.setAssigneeUserId(tasksEntry.getAssigneeUserId());
		tasksEntryImpl.setResolverUserId(tasksEntry.getResolverUserId());
		tasksEntryImpl.setDueDate(tasksEntry.getDueDate());
		tasksEntryImpl.setFinishDate(tasksEntry.getFinishDate());
		tasksEntryImpl.setStatus(tasksEntry.getStatus());

		return tasksEntryImpl;
	}

	/**
	 * Returns the tasks entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the tasks entry
	 * @return the tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTasksEntryException {
		TasksEntry tasksEntry = fetchByPrimaryKey(primaryKey);

		if (tasksEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTasksEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return tasksEntry;
	}

	/**
	 * Returns the tasks entry with the primary key or throws a {@link NoSuchTasksEntryException} if it could not be found.
	 *
	 * @param tasksEntryId the primary key of the tasks entry
	 * @return the tasks entry
	 * @throws NoSuchTasksEntryException if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry findByPrimaryKey(long tasksEntryId)
		throws NoSuchTasksEntryException {
		return findByPrimaryKey((Serializable)tasksEntryId);
	}

	/**
	 * Returns the tasks entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the tasks entry
	 * @return the tasks entry, or <code>null</code> if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry fetchByPrimaryKey(Serializable primaryKey) {
		TasksEntry tasksEntry = (TasksEntry)EntityCacheUtil.getResult(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
				TasksEntryImpl.class, primaryKey);

		if (tasksEntry == _nullTasksEntry) {
			return null;
		}

		if (tasksEntry == null) {
			Session session = null;

			try {
				session = openSession();

				tasksEntry = (TasksEntry)session.get(TasksEntryImpl.class,
						primaryKey);

				if (tasksEntry != null) {
					cacheResult(tasksEntry);
				}
				else {
					EntityCacheUtil.putResult(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
						TasksEntryImpl.class, primaryKey, _nullTasksEntry);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
					TasksEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return tasksEntry;
	}

	/**
	 * Returns the tasks entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param tasksEntryId the primary key of the tasks entry
	 * @return the tasks entry, or <code>null</code> if a tasks entry with the primary key could not be found
	 */
	@Override
	public TasksEntry fetchByPrimaryKey(long tasksEntryId) {
		return fetchByPrimaryKey((Serializable)tasksEntryId);
	}

	@Override
	public Map<Serializable, TasksEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, TasksEntry> map = new HashMap<Serializable, TasksEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			TasksEntry tasksEntry = fetchByPrimaryKey(primaryKey);

			if (tasksEntry != null) {
				map.put(primaryKey, tasksEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			TasksEntry tasksEntry = (TasksEntry)EntityCacheUtil.getResult(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
					TasksEntryImpl.class, primaryKey);

			if (tasksEntry == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, tasksEntry);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_TASKSENTRY_WHERE_PKS_IN);

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

			for (TasksEntry tasksEntry : (List<TasksEntry>)q.list()) {
				map.put(tasksEntry.getPrimaryKeyObj(), tasksEntry);

				cacheResult(tasksEntry);

				uncachedPrimaryKeys.remove(tasksEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(TasksEntryModelImpl.ENTITY_CACHE_ENABLED,
					TasksEntryImpl.class, primaryKey, _nullTasksEntry);
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
	 * Returns all the tasks entries.
	 *
	 * @return the tasks entries
	 */
	@Override
	public List<TasksEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @return the range of tasks entries
	 */
	@Override
	public List<TasksEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TasksEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of tasks entries
	 * @param end the upper bound of the range of tasks entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of tasks entries
	 */
	@Override
	public List<TasksEntry> findAll(int start, int end,
		OrderByComparator<TasksEntry> orderByComparator) {
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

		List<TasksEntry> list = (List<TasksEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TASKSENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TASKSENTRY;

				if (pagination) {
					sql = sql.concat(TasksEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TasksEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the tasks entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TasksEntry tasksEntry : findAll()) {
			remove(tasksEntry);
		}
	}

	/**
	 * Returns the number of tasks entries.
	 *
	 * @return the number of tasks entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TASKSENTRY);

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
	protected Map<String, Integer> getTableColumnsMap() {
		return TasksEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the tasks entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(TasksEntryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_TASKSENTRY = "SELECT tasksEntry FROM TasksEntry tasksEntry";
	private static final String _SQL_SELECT_TASKSENTRY_WHERE_PKS_IN = "SELECT tasksEntry FROM TasksEntry tasksEntry WHERE tasksEntryId IN (";
	private static final String _SQL_SELECT_TASKSENTRY_WHERE = "SELECT tasksEntry FROM TasksEntry tasksEntry WHERE ";
	private static final String _SQL_COUNT_TASKSENTRY = "SELECT COUNT(tasksEntry) FROM TasksEntry tasksEntry";
	private static final String _SQL_COUNT_TASKSENTRY_WHERE = "SELECT COUNT(tasksEntry) FROM TasksEntry tasksEntry WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "tasksEntry.tasksEntryId";
	private static final String _FILTER_SQL_SELECT_TASKSENTRY_WHERE = "SELECT DISTINCT {tasksEntry.*} FROM TMS_TasksEntry tasksEntry WHERE ";
	private static final String _FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {TMS_TasksEntry.*} FROM (SELECT DISTINCT tasksEntry.tasksEntryId FROM TMS_TasksEntry tasksEntry WHERE ";
	private static final String _FILTER_SQL_SELECT_TASKSENTRY_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN TMS_TasksEntry ON TEMP_TABLE.tasksEntryId = TMS_TasksEntry.tasksEntryId";
	private static final String _FILTER_SQL_COUNT_TASKSENTRY_WHERE = "SELECT COUNT(DISTINCT tasksEntry.tasksEntryId) AS COUNT_VALUE FROM TMS_TasksEntry tasksEntry WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "tasksEntry";
	private static final String _FILTER_ENTITY_TABLE = "TMS_TasksEntry";
	private static final String _ORDER_BY_ENTITY_ALIAS = "tasksEntry.";
	private static final String _ORDER_BY_ENTITY_TABLE = "TMS_TasksEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TasksEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TasksEntry exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(TasksEntryPersistenceImpl.class);
	private static final TasksEntry _nullTasksEntry = new TasksEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<TasksEntry> toCacheModel() {
				return _nullTasksEntryCacheModel;
			}
		};

	private static final CacheModel<TasksEntry> _nullTasksEntryCacheModel = new CacheModel<TasksEntry>() {
			@Override
			public TasksEntry toEntityModel() {
				return _nullTasksEntry;
			}
		};
}