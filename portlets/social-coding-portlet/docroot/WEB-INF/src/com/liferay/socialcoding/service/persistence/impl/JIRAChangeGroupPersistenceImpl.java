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

package com.liferay.socialcoding.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.socialcoding.NoSuchJIRAChangeGroupException;
import com.liferay.socialcoding.model.JIRAChangeGroup;
import com.liferay.socialcoding.model.impl.JIRAChangeGroupImpl;
import com.liferay.socialcoding.model.impl.JIRAChangeGroupModelImpl;
import com.liferay.socialcoding.service.persistence.JIRAChangeGroupPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the j i r a change group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAChangeGroupPersistence
 * @see com.liferay.socialcoding.service.persistence.JIRAChangeGroupUtil
 * @generated
 */
@ProviderType
public class JIRAChangeGroupPersistenceImpl extends BasePersistenceImpl<JIRAChangeGroup>
	implements JIRAChangeGroupPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link JIRAChangeGroupUtil} to access the j i r a change group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = JIRAChangeGroupImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupModelImpl.FINDER_CACHE_ENABLED,
			JIRAChangeGroupImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupModelImpl.FINDER_CACHE_ENABLED,
			JIRAChangeGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_JIRAUSERID =
		new FinderPath(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupModelImpl.FINDER_CACHE_ENABLED,
			JIRAChangeGroupImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByJiraUserId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JIRAUSERID =
		new FinderPath(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupModelImpl.FINDER_CACHE_ENABLED,
			JIRAChangeGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByJiraUserId",
			new String[] { String.class.getName() },
			JIRAChangeGroupModelImpl.JIRAUSERID_COLUMN_BITMASK |
			JIRAChangeGroupModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_JIRAUSERID = new FinderPath(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByJiraUserId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the j i r a change groups where jiraUserId = &#63;.
	 *
	 * @param jiraUserId the jira user ID
	 * @return the matching j i r a change groups
	 */
	@Override
	public List<JIRAChangeGroup> findByJiraUserId(String jiraUserId) {
		return findByJiraUserId(jiraUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a change groups where jiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAChangeGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param jiraUserId the jira user ID
	 * @param start the lower bound of the range of j i r a change groups
	 * @param end the upper bound of the range of j i r a change groups (not inclusive)
	 * @return the range of matching j i r a change groups
	 */
	@Override
	public List<JIRAChangeGroup> findByJiraUserId(String jiraUserId, int start,
		int end) {
		return findByJiraUserId(jiraUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a change groups where jiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAChangeGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param jiraUserId the jira user ID
	 * @param start the lower bound of the range of j i r a change groups
	 * @param end the upper bound of the range of j i r a change groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a change groups
	 */
	@Override
	public List<JIRAChangeGroup> findByJiraUserId(String jiraUserId, int start,
		int end, OrderByComparator<JIRAChangeGroup> orderByComparator) {
		return findByJiraUserId(jiraUserId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the j i r a change groups where jiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAChangeGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param jiraUserId the jira user ID
	 * @param start the lower bound of the range of j i r a change groups
	 * @param end the upper bound of the range of j i r a change groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching j i r a change groups
	 */
	@Override
	public List<JIRAChangeGroup> findByJiraUserId(String jiraUserId, int start,
		int end, OrderByComparator<JIRAChangeGroup> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JIRAUSERID;
			finderArgs = new Object[] { jiraUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_JIRAUSERID;
			finderArgs = new Object[] { jiraUserId, start, end, orderByComparator };
		}

		List<JIRAChangeGroup> list = null;

		if (retrieveFromCache) {
			list = (List<JIRAChangeGroup>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (JIRAChangeGroup jiraChangeGroup : list) {
					if (!Validator.equals(jiraUserId,
								jiraChangeGroup.getJiraUserId())) {
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

			query.append(_SQL_SELECT_JIRACHANGEGROUP_WHERE);

			boolean bindJiraUserId = false;

			if (jiraUserId == null) {
				query.append(_FINDER_COLUMN_JIRAUSERID_JIRAUSERID_1);
			}
			else if (jiraUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_JIRAUSERID_JIRAUSERID_3);
			}
			else {
				bindJiraUserId = true;

				query.append(_FINDER_COLUMN_JIRAUSERID_JIRAUSERID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JIRAChangeGroupModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindJiraUserId) {
					qPos.add(jiraUserId);
				}

				if (!pagination) {
					list = (List<JIRAChangeGroup>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JIRAChangeGroup>)QueryUtil.list(q,
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
	 * Returns the first j i r a change group in the ordered set where jiraUserId = &#63;.
	 *
	 * @param jiraUserId the jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a change group
	 * @throws NoSuchJIRAChangeGroupException if a matching j i r a change group could not be found
	 */
	@Override
	public JIRAChangeGroup findByJiraUserId_First(String jiraUserId,
		OrderByComparator<JIRAChangeGroup> orderByComparator)
		throws NoSuchJIRAChangeGroupException {
		JIRAChangeGroup jiraChangeGroup = fetchByJiraUserId_First(jiraUserId,
				orderByComparator);

		if (jiraChangeGroup != null) {
			return jiraChangeGroup;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("jiraUserId=");
		msg.append(jiraUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAChangeGroupException(msg.toString());
	}

	/**
	 * Returns the first j i r a change group in the ordered set where jiraUserId = &#63;.
	 *
	 * @param jiraUserId the jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a change group, or <code>null</code> if a matching j i r a change group could not be found
	 */
	@Override
	public JIRAChangeGroup fetchByJiraUserId_First(String jiraUserId,
		OrderByComparator<JIRAChangeGroup> orderByComparator) {
		List<JIRAChangeGroup> list = findByJiraUserId(jiraUserId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last j i r a change group in the ordered set where jiraUserId = &#63;.
	 *
	 * @param jiraUserId the jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a change group
	 * @throws NoSuchJIRAChangeGroupException if a matching j i r a change group could not be found
	 */
	@Override
	public JIRAChangeGroup findByJiraUserId_Last(String jiraUserId,
		OrderByComparator<JIRAChangeGroup> orderByComparator)
		throws NoSuchJIRAChangeGroupException {
		JIRAChangeGroup jiraChangeGroup = fetchByJiraUserId_Last(jiraUserId,
				orderByComparator);

		if (jiraChangeGroup != null) {
			return jiraChangeGroup;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("jiraUserId=");
		msg.append(jiraUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAChangeGroupException(msg.toString());
	}

	/**
	 * Returns the last j i r a change group in the ordered set where jiraUserId = &#63;.
	 *
	 * @param jiraUserId the jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a change group, or <code>null</code> if a matching j i r a change group could not be found
	 */
	@Override
	public JIRAChangeGroup fetchByJiraUserId_Last(String jiraUserId,
		OrderByComparator<JIRAChangeGroup> orderByComparator) {
		int count = countByJiraUserId(jiraUserId);

		if (count == 0) {
			return null;
		}

		List<JIRAChangeGroup> list = findByJiraUserId(jiraUserId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the j i r a change groups before and after the current j i r a change group in the ordered set where jiraUserId = &#63;.
	 *
	 * @param jiraChangeGroupId the primary key of the current j i r a change group
	 * @param jiraUserId the jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a change group
	 * @throws NoSuchJIRAChangeGroupException if a j i r a change group with the primary key could not be found
	 */
	@Override
	public JIRAChangeGroup[] findByJiraUserId_PrevAndNext(
		long jiraChangeGroupId, String jiraUserId,
		OrderByComparator<JIRAChangeGroup> orderByComparator)
		throws NoSuchJIRAChangeGroupException {
		JIRAChangeGroup jiraChangeGroup = findByPrimaryKey(jiraChangeGroupId);

		Session session = null;

		try {
			session = openSession();

			JIRAChangeGroup[] array = new JIRAChangeGroupImpl[3];

			array[0] = getByJiraUserId_PrevAndNext(session, jiraChangeGroup,
					jiraUserId, orderByComparator, true);

			array[1] = jiraChangeGroup;

			array[2] = getByJiraUserId_PrevAndNext(session, jiraChangeGroup,
					jiraUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JIRAChangeGroup getByJiraUserId_PrevAndNext(Session session,
		JIRAChangeGroup jiraChangeGroup, String jiraUserId,
		OrderByComparator<JIRAChangeGroup> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRACHANGEGROUP_WHERE);

		boolean bindJiraUserId = false;

		if (jiraUserId == null) {
			query.append(_FINDER_COLUMN_JIRAUSERID_JIRAUSERID_1);
		}
		else if (jiraUserId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_JIRAUSERID_JIRAUSERID_3);
		}
		else {
			bindJiraUserId = true;

			query.append(_FINDER_COLUMN_JIRAUSERID_JIRAUSERID_2);
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
			query.append(JIRAChangeGroupModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindJiraUserId) {
			qPos.add(jiraUserId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(jiraChangeGroup);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAChangeGroup> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the j i r a change groups where jiraUserId = &#63; from the database.
	 *
	 * @param jiraUserId the jira user ID
	 */
	@Override
	public void removeByJiraUserId(String jiraUserId) {
		for (JIRAChangeGroup jiraChangeGroup : findByJiraUserId(jiraUserId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(jiraChangeGroup);
		}
	}

	/**
	 * Returns the number of j i r a change groups where jiraUserId = &#63;.
	 *
	 * @param jiraUserId the jira user ID
	 * @return the number of matching j i r a change groups
	 */
	@Override
	public int countByJiraUserId(String jiraUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_JIRAUSERID;

		Object[] finderArgs = new Object[] { jiraUserId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JIRACHANGEGROUP_WHERE);

			boolean bindJiraUserId = false;

			if (jiraUserId == null) {
				query.append(_FINDER_COLUMN_JIRAUSERID_JIRAUSERID_1);
			}
			else if (jiraUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_JIRAUSERID_JIRAUSERID_3);
			}
			else {
				bindJiraUserId = true;

				query.append(_FINDER_COLUMN_JIRAUSERID_JIRAUSERID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindJiraUserId) {
					qPos.add(jiraUserId);
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

	private static final String _FINDER_COLUMN_JIRAUSERID_JIRAUSERID_1 = "jiraChangeGroup.jiraUserId IS NULL";
	private static final String _FINDER_COLUMN_JIRAUSERID_JIRAUSERID_2 = "jiraChangeGroup.jiraUserId = ?";
	private static final String _FINDER_COLUMN_JIRAUSERID_JIRAUSERID_3 = "(jiraChangeGroup.jiraUserId IS NULL OR jiraChangeGroup.jiraUserId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_JIRAISSUEID =
		new FinderPath(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupModelImpl.FINDER_CACHE_ENABLED,
			JIRAChangeGroupImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByJiraIssueId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JIRAISSUEID =
		new FinderPath(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupModelImpl.FINDER_CACHE_ENABLED,
			JIRAChangeGroupImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByJiraIssueId",
			new String[] { Long.class.getName() },
			JIRAChangeGroupModelImpl.JIRAISSUEID_COLUMN_BITMASK |
			JIRAChangeGroupModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_JIRAISSUEID = new FinderPath(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByJiraIssueId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the j i r a change groups where jiraIssueId = &#63;.
	 *
	 * @param jiraIssueId the jira issue ID
	 * @return the matching j i r a change groups
	 */
	@Override
	public List<JIRAChangeGroup> findByJiraIssueId(long jiraIssueId) {
		return findByJiraIssueId(jiraIssueId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a change groups where jiraIssueId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAChangeGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param jiraIssueId the jira issue ID
	 * @param start the lower bound of the range of j i r a change groups
	 * @param end the upper bound of the range of j i r a change groups (not inclusive)
	 * @return the range of matching j i r a change groups
	 */
	@Override
	public List<JIRAChangeGroup> findByJiraIssueId(long jiraIssueId, int start,
		int end) {
		return findByJiraIssueId(jiraIssueId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a change groups where jiraIssueId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAChangeGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param jiraIssueId the jira issue ID
	 * @param start the lower bound of the range of j i r a change groups
	 * @param end the upper bound of the range of j i r a change groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a change groups
	 */
	@Override
	public List<JIRAChangeGroup> findByJiraIssueId(long jiraIssueId, int start,
		int end, OrderByComparator<JIRAChangeGroup> orderByComparator) {
		return findByJiraIssueId(jiraIssueId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the j i r a change groups where jiraIssueId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAChangeGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param jiraIssueId the jira issue ID
	 * @param start the lower bound of the range of j i r a change groups
	 * @param end the upper bound of the range of j i r a change groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching j i r a change groups
	 */
	@Override
	public List<JIRAChangeGroup> findByJiraIssueId(long jiraIssueId, int start,
		int end, OrderByComparator<JIRAChangeGroup> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JIRAISSUEID;
			finderArgs = new Object[] { jiraIssueId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_JIRAISSUEID;
			finderArgs = new Object[] { jiraIssueId, start, end, orderByComparator };
		}

		List<JIRAChangeGroup> list = null;

		if (retrieveFromCache) {
			list = (List<JIRAChangeGroup>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (JIRAChangeGroup jiraChangeGroup : list) {
					if ((jiraIssueId != jiraChangeGroup.getJiraIssueId())) {
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

			query.append(_SQL_SELECT_JIRACHANGEGROUP_WHERE);

			query.append(_FINDER_COLUMN_JIRAISSUEID_JIRAISSUEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JIRAChangeGroupModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(jiraIssueId);

				if (!pagination) {
					list = (List<JIRAChangeGroup>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JIRAChangeGroup>)QueryUtil.list(q,
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
	 * Returns the first j i r a change group in the ordered set where jiraIssueId = &#63;.
	 *
	 * @param jiraIssueId the jira issue ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a change group
	 * @throws NoSuchJIRAChangeGroupException if a matching j i r a change group could not be found
	 */
	@Override
	public JIRAChangeGroup findByJiraIssueId_First(long jiraIssueId,
		OrderByComparator<JIRAChangeGroup> orderByComparator)
		throws NoSuchJIRAChangeGroupException {
		JIRAChangeGroup jiraChangeGroup = fetchByJiraIssueId_First(jiraIssueId,
				orderByComparator);

		if (jiraChangeGroup != null) {
			return jiraChangeGroup;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("jiraIssueId=");
		msg.append(jiraIssueId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAChangeGroupException(msg.toString());
	}

	/**
	 * Returns the first j i r a change group in the ordered set where jiraIssueId = &#63;.
	 *
	 * @param jiraIssueId the jira issue ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a change group, or <code>null</code> if a matching j i r a change group could not be found
	 */
	@Override
	public JIRAChangeGroup fetchByJiraIssueId_First(long jiraIssueId,
		OrderByComparator<JIRAChangeGroup> orderByComparator) {
		List<JIRAChangeGroup> list = findByJiraIssueId(jiraIssueId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last j i r a change group in the ordered set where jiraIssueId = &#63;.
	 *
	 * @param jiraIssueId the jira issue ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a change group
	 * @throws NoSuchJIRAChangeGroupException if a matching j i r a change group could not be found
	 */
	@Override
	public JIRAChangeGroup findByJiraIssueId_Last(long jiraIssueId,
		OrderByComparator<JIRAChangeGroup> orderByComparator)
		throws NoSuchJIRAChangeGroupException {
		JIRAChangeGroup jiraChangeGroup = fetchByJiraIssueId_Last(jiraIssueId,
				orderByComparator);

		if (jiraChangeGroup != null) {
			return jiraChangeGroup;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("jiraIssueId=");
		msg.append(jiraIssueId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAChangeGroupException(msg.toString());
	}

	/**
	 * Returns the last j i r a change group in the ordered set where jiraIssueId = &#63;.
	 *
	 * @param jiraIssueId the jira issue ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a change group, or <code>null</code> if a matching j i r a change group could not be found
	 */
	@Override
	public JIRAChangeGroup fetchByJiraIssueId_Last(long jiraIssueId,
		OrderByComparator<JIRAChangeGroup> orderByComparator) {
		int count = countByJiraIssueId(jiraIssueId);

		if (count == 0) {
			return null;
		}

		List<JIRAChangeGroup> list = findByJiraIssueId(jiraIssueId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the j i r a change groups before and after the current j i r a change group in the ordered set where jiraIssueId = &#63;.
	 *
	 * @param jiraChangeGroupId the primary key of the current j i r a change group
	 * @param jiraIssueId the jira issue ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a change group
	 * @throws NoSuchJIRAChangeGroupException if a j i r a change group with the primary key could not be found
	 */
	@Override
	public JIRAChangeGroup[] findByJiraIssueId_PrevAndNext(
		long jiraChangeGroupId, long jiraIssueId,
		OrderByComparator<JIRAChangeGroup> orderByComparator)
		throws NoSuchJIRAChangeGroupException {
		JIRAChangeGroup jiraChangeGroup = findByPrimaryKey(jiraChangeGroupId);

		Session session = null;

		try {
			session = openSession();

			JIRAChangeGroup[] array = new JIRAChangeGroupImpl[3];

			array[0] = getByJiraIssueId_PrevAndNext(session, jiraChangeGroup,
					jiraIssueId, orderByComparator, true);

			array[1] = jiraChangeGroup;

			array[2] = getByJiraIssueId_PrevAndNext(session, jiraChangeGroup,
					jiraIssueId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JIRAChangeGroup getByJiraIssueId_PrevAndNext(Session session,
		JIRAChangeGroup jiraChangeGroup, long jiraIssueId,
		OrderByComparator<JIRAChangeGroup> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRACHANGEGROUP_WHERE);

		query.append(_FINDER_COLUMN_JIRAISSUEID_JIRAISSUEID_2);

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
			query.append(JIRAChangeGroupModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(jiraIssueId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(jiraChangeGroup);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAChangeGroup> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the j i r a change groups where jiraIssueId = &#63; from the database.
	 *
	 * @param jiraIssueId the jira issue ID
	 */
	@Override
	public void removeByJiraIssueId(long jiraIssueId) {
		for (JIRAChangeGroup jiraChangeGroup : findByJiraIssueId(jiraIssueId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(jiraChangeGroup);
		}
	}

	/**
	 * Returns the number of j i r a change groups where jiraIssueId = &#63;.
	 *
	 * @param jiraIssueId the jira issue ID
	 * @return the number of matching j i r a change groups
	 */
	@Override
	public int countByJiraIssueId(long jiraIssueId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_JIRAISSUEID;

		Object[] finderArgs = new Object[] { jiraIssueId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JIRACHANGEGROUP_WHERE);

			query.append(_FINDER_COLUMN_JIRAISSUEID_JIRAISSUEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(jiraIssueId);

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

	private static final String _FINDER_COLUMN_JIRAISSUEID_JIRAISSUEID_2 = "jiraChangeGroup.jiraIssueId = ?";

	public JIRAChangeGroupPersistenceImpl() {
		setModelClass(JIRAChangeGroup.class);
	}

	/**
	 * Caches the j i r a change group in the entity cache if it is enabled.
	 *
	 * @param jiraChangeGroup the j i r a change group
	 */
	@Override
	public void cacheResult(JIRAChangeGroup jiraChangeGroup) {
		entityCache.putResult(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupImpl.class, jiraChangeGroup.getPrimaryKey(),
			jiraChangeGroup);

		jiraChangeGroup.resetOriginalValues();
	}

	/**
	 * Caches the j i r a change groups in the entity cache if it is enabled.
	 *
	 * @param jiraChangeGroups the j i r a change groups
	 */
	@Override
	public void cacheResult(List<JIRAChangeGroup> jiraChangeGroups) {
		for (JIRAChangeGroup jiraChangeGroup : jiraChangeGroups) {
			if (entityCache.getResult(
						JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
						JIRAChangeGroupImpl.class,
						jiraChangeGroup.getPrimaryKey()) == null) {
				cacheResult(jiraChangeGroup);
			}
			else {
				jiraChangeGroup.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all j i r a change groups.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(JIRAChangeGroupImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the j i r a change group.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(JIRAChangeGroup jiraChangeGroup) {
		entityCache.removeResult(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupImpl.class, jiraChangeGroup.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<JIRAChangeGroup> jiraChangeGroups) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (JIRAChangeGroup jiraChangeGroup : jiraChangeGroups) {
			entityCache.removeResult(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
				JIRAChangeGroupImpl.class, jiraChangeGroup.getPrimaryKey());
		}
	}

	/**
	 * Creates a new j i r a change group with the primary key. Does not add the j i r a change group to the database.
	 *
	 * @param jiraChangeGroupId the primary key for the new j i r a change group
	 * @return the new j i r a change group
	 */
	@Override
	public JIRAChangeGroup create(long jiraChangeGroupId) {
		JIRAChangeGroup jiraChangeGroup = new JIRAChangeGroupImpl();

		jiraChangeGroup.setNew(true);
		jiraChangeGroup.setPrimaryKey(jiraChangeGroupId);

		return jiraChangeGroup;
	}

	/**
	 * Removes the j i r a change group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraChangeGroupId the primary key of the j i r a change group
	 * @return the j i r a change group that was removed
	 * @throws NoSuchJIRAChangeGroupException if a j i r a change group with the primary key could not be found
	 */
	@Override
	public JIRAChangeGroup remove(long jiraChangeGroupId)
		throws NoSuchJIRAChangeGroupException {
		return remove((Serializable)jiraChangeGroupId);
	}

	/**
	 * Removes the j i r a change group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the j i r a change group
	 * @return the j i r a change group that was removed
	 * @throws NoSuchJIRAChangeGroupException if a j i r a change group with the primary key could not be found
	 */
	@Override
	public JIRAChangeGroup remove(Serializable primaryKey)
		throws NoSuchJIRAChangeGroupException {
		Session session = null;

		try {
			session = openSession();

			JIRAChangeGroup jiraChangeGroup = (JIRAChangeGroup)session.get(JIRAChangeGroupImpl.class,
					primaryKey);

			if (jiraChangeGroup == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchJIRAChangeGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(jiraChangeGroup);
		}
		catch (NoSuchJIRAChangeGroupException nsee) {
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
	protected JIRAChangeGroup removeImpl(JIRAChangeGroup jiraChangeGroup) {
		jiraChangeGroup = toUnwrappedModel(jiraChangeGroup);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(jiraChangeGroup)) {
				jiraChangeGroup = (JIRAChangeGroup)session.get(JIRAChangeGroupImpl.class,
						jiraChangeGroup.getPrimaryKeyObj());
			}

			if (jiraChangeGroup != null) {
				session.delete(jiraChangeGroup);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (jiraChangeGroup != null) {
			clearCache(jiraChangeGroup);
		}

		return jiraChangeGroup;
	}

	@Override
	public JIRAChangeGroup updateImpl(JIRAChangeGroup jiraChangeGroup) {
		jiraChangeGroup = toUnwrappedModel(jiraChangeGroup);

		boolean isNew = jiraChangeGroup.isNew();

		JIRAChangeGroupModelImpl jiraChangeGroupModelImpl = (JIRAChangeGroupModelImpl)jiraChangeGroup;

		Session session = null;

		try {
			session = openSession();

			if (jiraChangeGroup.isNew()) {
				session.save(jiraChangeGroup);

				jiraChangeGroup.setNew(false);
			}
			else {
				jiraChangeGroup = (JIRAChangeGroup)session.merge(jiraChangeGroup);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !JIRAChangeGroupModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((jiraChangeGroupModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JIRAUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jiraChangeGroupModelImpl.getOriginalJiraUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_JIRAUSERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JIRAUSERID,
					args);

				args = new Object[] { jiraChangeGroupModelImpl.getJiraUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_JIRAUSERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JIRAUSERID,
					args);
			}

			if ((jiraChangeGroupModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JIRAISSUEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jiraChangeGroupModelImpl.getOriginalJiraIssueId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_JIRAISSUEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JIRAISSUEID,
					args);

				args = new Object[] { jiraChangeGroupModelImpl.getJiraIssueId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_JIRAISSUEID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JIRAISSUEID,
					args);
			}
		}

		entityCache.putResult(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupImpl.class, jiraChangeGroup.getPrimaryKey(),
			jiraChangeGroup, false);

		jiraChangeGroup.resetOriginalValues();

		return jiraChangeGroup;
	}

	protected JIRAChangeGroup toUnwrappedModel(JIRAChangeGroup jiraChangeGroup) {
		if (jiraChangeGroup instanceof JIRAChangeGroupImpl) {
			return jiraChangeGroup;
		}

		JIRAChangeGroupImpl jiraChangeGroupImpl = new JIRAChangeGroupImpl();

		jiraChangeGroupImpl.setNew(jiraChangeGroup.isNew());
		jiraChangeGroupImpl.setPrimaryKey(jiraChangeGroup.getPrimaryKey());

		jiraChangeGroupImpl.setJiraChangeGroupId(jiraChangeGroup.getJiraChangeGroupId());
		jiraChangeGroupImpl.setJiraUserId(jiraChangeGroup.getJiraUserId());
		jiraChangeGroupImpl.setCreateDate(jiraChangeGroup.getCreateDate());
		jiraChangeGroupImpl.setJiraIssueId(jiraChangeGroup.getJiraIssueId());

		return jiraChangeGroupImpl;
	}

	/**
	 * Returns the j i r a change group with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the j i r a change group
	 * @return the j i r a change group
	 * @throws NoSuchJIRAChangeGroupException if a j i r a change group with the primary key could not be found
	 */
	@Override
	public JIRAChangeGroup findByPrimaryKey(Serializable primaryKey)
		throws NoSuchJIRAChangeGroupException {
		JIRAChangeGroup jiraChangeGroup = fetchByPrimaryKey(primaryKey);

		if (jiraChangeGroup == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchJIRAChangeGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return jiraChangeGroup;
	}

	/**
	 * Returns the j i r a change group with the primary key or throws a {@link NoSuchJIRAChangeGroupException} if it could not be found.
	 *
	 * @param jiraChangeGroupId the primary key of the j i r a change group
	 * @return the j i r a change group
	 * @throws NoSuchJIRAChangeGroupException if a j i r a change group with the primary key could not be found
	 */
	@Override
	public JIRAChangeGroup findByPrimaryKey(long jiraChangeGroupId)
		throws NoSuchJIRAChangeGroupException {
		return findByPrimaryKey((Serializable)jiraChangeGroupId);
	}

	/**
	 * Returns the j i r a change group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the j i r a change group
	 * @return the j i r a change group, or <code>null</code> if a j i r a change group with the primary key could not be found
	 */
	@Override
	public JIRAChangeGroup fetchByPrimaryKey(Serializable primaryKey) {
		JIRAChangeGroup jiraChangeGroup = (JIRAChangeGroup)entityCache.getResult(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
				JIRAChangeGroupImpl.class, primaryKey);

		if (jiraChangeGroup == _nullJIRAChangeGroup) {
			return null;
		}

		if (jiraChangeGroup == null) {
			Session session = null;

			try {
				session = openSession();

				jiraChangeGroup = (JIRAChangeGroup)session.get(JIRAChangeGroupImpl.class,
						primaryKey);

				if (jiraChangeGroup != null) {
					cacheResult(jiraChangeGroup);
				}
				else {
					entityCache.putResult(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
						JIRAChangeGroupImpl.class, primaryKey,
						_nullJIRAChangeGroup);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
					JIRAChangeGroupImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return jiraChangeGroup;
	}

	/**
	 * Returns the j i r a change group with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param jiraChangeGroupId the primary key of the j i r a change group
	 * @return the j i r a change group, or <code>null</code> if a j i r a change group with the primary key could not be found
	 */
	@Override
	public JIRAChangeGroup fetchByPrimaryKey(long jiraChangeGroupId) {
		return fetchByPrimaryKey((Serializable)jiraChangeGroupId);
	}

	@Override
	public Map<Serializable, JIRAChangeGroup> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, JIRAChangeGroup> map = new HashMap<Serializable, JIRAChangeGroup>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			JIRAChangeGroup jiraChangeGroup = fetchByPrimaryKey(primaryKey);

			if (jiraChangeGroup != null) {
				map.put(primaryKey, jiraChangeGroup);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			JIRAChangeGroup jiraChangeGroup = (JIRAChangeGroup)entityCache.getResult(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
					JIRAChangeGroupImpl.class, primaryKey);

			if (jiraChangeGroup == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, jiraChangeGroup);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_JIRACHANGEGROUP_WHERE_PKS_IN);

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

			for (JIRAChangeGroup jiraChangeGroup : (List<JIRAChangeGroup>)q.list()) {
				map.put(jiraChangeGroup.getPrimaryKeyObj(), jiraChangeGroup);

				cacheResult(jiraChangeGroup);

				uncachedPrimaryKeys.remove(jiraChangeGroup.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
					JIRAChangeGroupImpl.class, primaryKey, _nullJIRAChangeGroup);
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
	 * Returns all the j i r a change groups.
	 *
	 * @return the j i r a change groups
	 */
	@Override
	public List<JIRAChangeGroup> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a change groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAChangeGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of j i r a change groups
	 * @param end the upper bound of the range of j i r a change groups (not inclusive)
	 * @return the range of j i r a change groups
	 */
	@Override
	public List<JIRAChangeGroup> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a change groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAChangeGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of j i r a change groups
	 * @param end the upper bound of the range of j i r a change groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of j i r a change groups
	 */
	@Override
	public List<JIRAChangeGroup> findAll(int start, int end,
		OrderByComparator<JIRAChangeGroup> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the j i r a change groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAChangeGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of j i r a change groups
	 * @param end the upper bound of the range of j i r a change groups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of j i r a change groups
	 */
	@Override
	public List<JIRAChangeGroup> findAll(int start, int end,
		OrderByComparator<JIRAChangeGroup> orderByComparator,
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

		List<JIRAChangeGroup> list = null;

		if (retrieveFromCache) {
			list = (List<JIRAChangeGroup>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_JIRACHANGEGROUP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_JIRACHANGEGROUP;

				if (pagination) {
					sql = sql.concat(JIRAChangeGroupModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<JIRAChangeGroup>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JIRAChangeGroup>)QueryUtil.list(q,
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
	 * Removes all the j i r a change groups from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (JIRAChangeGroup jiraChangeGroup : findAll()) {
			remove(jiraChangeGroup);
		}
	}

	/**
	 * Returns the number of j i r a change groups.
	 *
	 * @return the number of j i r a change groups
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_JIRACHANGEGROUP);

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
		return JIRAChangeGroupModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the j i r a change group persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(JIRAChangeGroupImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_JIRACHANGEGROUP = "SELECT jiraChangeGroup FROM JIRAChangeGroup jiraChangeGroup";
	private static final String _SQL_SELECT_JIRACHANGEGROUP_WHERE_PKS_IN = "SELECT jiraChangeGroup FROM JIRAChangeGroup jiraChangeGroup WHERE id IN (";
	private static final String _SQL_SELECT_JIRACHANGEGROUP_WHERE = "SELECT jiraChangeGroup FROM JIRAChangeGroup jiraChangeGroup WHERE ";
	private static final String _SQL_COUNT_JIRACHANGEGROUP = "SELECT COUNT(jiraChangeGroup) FROM JIRAChangeGroup jiraChangeGroup";
	private static final String _SQL_COUNT_JIRACHANGEGROUP_WHERE = "SELECT COUNT(jiraChangeGroup) FROM JIRAChangeGroup jiraChangeGroup WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "jiraChangeGroup.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No JIRAChangeGroup exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No JIRAChangeGroup exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(JIRAChangeGroupPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"jiraChangeGroupId", "jiraUserId", "createDate", "jiraIssueId"
			});
	private static final JIRAChangeGroup _nullJIRAChangeGroup = new JIRAChangeGroupImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<JIRAChangeGroup> toCacheModel() {
				return _nullJIRAChangeGroupCacheModel;
			}
		};

	private static final CacheModel<JIRAChangeGroup> _nullJIRAChangeGroupCacheModel =
		new CacheModel<JIRAChangeGroup>() {
			@Override
			public JIRAChangeGroup toEntityModel() {
				return _nullJIRAChangeGroup;
			}
		};
}