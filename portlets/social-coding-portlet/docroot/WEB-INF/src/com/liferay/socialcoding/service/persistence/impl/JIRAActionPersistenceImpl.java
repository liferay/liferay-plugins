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

import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
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
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.socialcoding.NoSuchJIRAActionException;
import com.liferay.socialcoding.model.JIRAAction;
import com.liferay.socialcoding.model.impl.JIRAActionImpl;
import com.liferay.socialcoding.model.impl.JIRAActionModelImpl;
import com.liferay.socialcoding.service.persistence.JIRAActionPersistence;

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
 * The persistence implementation for the j i r a action service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAActionPersistence
 * @see com.liferay.socialcoding.service.persistence.JIRAActionUtil
 * @generated
 */
@ProviderType
public class JIRAActionPersistenceImpl extends BasePersistenceImpl<JIRAAction>
	implements JIRAActionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link JIRAActionUtil} to access the j i r a action persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = JIRAActionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAActionModelImpl.FINDER_CACHE_ENABLED, JIRAActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAActionModelImpl.FINDER_CACHE_ENABLED, JIRAActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_JIRAUSERID =
		new FinderPath(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAActionModelImpl.FINDER_CACHE_ENABLED, JIRAActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByJiraUserId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JIRAUSERID =
		new FinderPath(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAActionModelImpl.FINDER_CACHE_ENABLED, JIRAActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByJiraUserId",
			new String[] { String.class.getName() },
			JIRAActionModelImpl.JIRAUSERID_COLUMN_BITMASK |
			JIRAActionModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_JIRAUSERID = new FinderPath(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByJiraUserId",
			new String[] { String.class.getName() });

	/**
	 * Returns all the j i r a actions where jiraUserId = &#63;.
	 *
	 * @param jiraUserId the jira user ID
	 * @return the matching j i r a actions
	 */
	@Override
	public List<JIRAAction> findByJiraUserId(String jiraUserId) {
		return findByJiraUserId(jiraUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a actions where jiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param jiraUserId the jira user ID
	 * @param start the lower bound of the range of j i r a actions
	 * @param end the upper bound of the range of j i r a actions (not inclusive)
	 * @return the range of matching j i r a actions
	 */
	@Override
	public List<JIRAAction> findByJiraUserId(String jiraUserId, int start,
		int end) {
		return findByJiraUserId(jiraUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a actions where jiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param jiraUserId the jira user ID
	 * @param start the lower bound of the range of j i r a actions
	 * @param end the upper bound of the range of j i r a actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a actions
	 */
	@Override
	public List<JIRAAction> findByJiraUserId(String jiraUserId, int start,
		int end, OrderByComparator<JIRAAction> orderByComparator) {
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

		List<JIRAAction> list = (List<JIRAAction>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (JIRAAction jiraAction : list) {
				if (!Validator.equals(jiraUserId, jiraAction.getJiraUserId())) {
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

			query.append(_SQL_SELECT_JIRAACTION_WHERE);

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
				query.append(JIRAActionModelImpl.ORDER_BY_JPQL);
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
					list = (List<JIRAAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JIRAAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first j i r a action in the ordered set where jiraUserId = &#63;.
	 *
	 * @param jiraUserId the jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a action
	 * @throws NoSuchJIRAActionException if a matching j i r a action could not be found
	 */
	@Override
	public JIRAAction findByJiraUserId_First(String jiraUserId,
		OrderByComparator<JIRAAction> orderByComparator)
		throws NoSuchJIRAActionException {
		JIRAAction jiraAction = fetchByJiraUserId_First(jiraUserId,
				orderByComparator);

		if (jiraAction != null) {
			return jiraAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("jiraUserId=");
		msg.append(jiraUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAActionException(msg.toString());
	}

	/**
	 * Returns the first j i r a action in the ordered set where jiraUserId = &#63;.
	 *
	 * @param jiraUserId the jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a action, or <code>null</code> if a matching j i r a action could not be found
	 */
	@Override
	public JIRAAction fetchByJiraUserId_First(String jiraUserId,
		OrderByComparator<JIRAAction> orderByComparator) {
		List<JIRAAction> list = findByJiraUserId(jiraUserId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last j i r a action in the ordered set where jiraUserId = &#63;.
	 *
	 * @param jiraUserId the jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a action
	 * @throws NoSuchJIRAActionException if a matching j i r a action could not be found
	 */
	@Override
	public JIRAAction findByJiraUserId_Last(String jiraUserId,
		OrderByComparator<JIRAAction> orderByComparator)
		throws NoSuchJIRAActionException {
		JIRAAction jiraAction = fetchByJiraUserId_Last(jiraUserId,
				orderByComparator);

		if (jiraAction != null) {
			return jiraAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("jiraUserId=");
		msg.append(jiraUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAActionException(msg.toString());
	}

	/**
	 * Returns the last j i r a action in the ordered set where jiraUserId = &#63;.
	 *
	 * @param jiraUserId the jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a action, or <code>null</code> if a matching j i r a action could not be found
	 */
	@Override
	public JIRAAction fetchByJiraUserId_Last(String jiraUserId,
		OrderByComparator<JIRAAction> orderByComparator) {
		int count = countByJiraUserId(jiraUserId);

		if (count == 0) {
			return null;
		}

		List<JIRAAction> list = findByJiraUserId(jiraUserId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the j i r a actions before and after the current j i r a action in the ordered set where jiraUserId = &#63;.
	 *
	 * @param jiraActionId the primary key of the current j i r a action
	 * @param jiraUserId the jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a action
	 * @throws NoSuchJIRAActionException if a j i r a action with the primary key could not be found
	 */
	@Override
	public JIRAAction[] findByJiraUserId_PrevAndNext(long jiraActionId,
		String jiraUserId, OrderByComparator<JIRAAction> orderByComparator)
		throws NoSuchJIRAActionException {
		JIRAAction jiraAction = findByPrimaryKey(jiraActionId);

		Session session = null;

		try {
			session = openSession();

			JIRAAction[] array = new JIRAActionImpl[3];

			array[0] = getByJiraUserId_PrevAndNext(session, jiraAction,
					jiraUserId, orderByComparator, true);

			array[1] = jiraAction;

			array[2] = getByJiraUserId_PrevAndNext(session, jiraAction,
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

	protected JIRAAction getByJiraUserId_PrevAndNext(Session session,
		JIRAAction jiraAction, String jiraUserId,
		OrderByComparator<JIRAAction> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRAACTION_WHERE);

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
			query.append(JIRAActionModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(jiraAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the j i r a actions where jiraUserId = &#63; from the database.
	 *
	 * @param jiraUserId the jira user ID
	 */
	@Override
	public void removeByJiraUserId(String jiraUserId) {
		for (JIRAAction jiraAction : findByJiraUserId(jiraUserId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(jiraAction);
		}
	}

	/**
	 * Returns the number of j i r a actions where jiraUserId = &#63;.
	 *
	 * @param jiraUserId the jira user ID
	 * @return the number of matching j i r a actions
	 */
	@Override
	public int countByJiraUserId(String jiraUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_JIRAUSERID;

		Object[] finderArgs = new Object[] { jiraUserId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JIRAACTION_WHERE);

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

	private static final String _FINDER_COLUMN_JIRAUSERID_JIRAUSERID_1 = "jiraAction.jiraUserId IS NULL";
	private static final String _FINDER_COLUMN_JIRAUSERID_JIRAUSERID_2 = "jiraAction.jiraUserId = ?";
	private static final String _FINDER_COLUMN_JIRAUSERID_JIRAUSERID_3 = "(jiraAction.jiraUserId IS NULL OR jiraAction.jiraUserId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_JIRAISSUEID =
		new FinderPath(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAActionModelImpl.FINDER_CACHE_ENABLED, JIRAActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByJiraIssueId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JIRAISSUEID =
		new FinderPath(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAActionModelImpl.FINDER_CACHE_ENABLED, JIRAActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByJiraIssueId",
			new String[] { Long.class.getName() },
			JIRAActionModelImpl.JIRAISSUEID_COLUMN_BITMASK |
			JIRAActionModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_JIRAISSUEID = new FinderPath(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByJiraIssueId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the j i r a actions where jiraIssueId = &#63;.
	 *
	 * @param jiraIssueId the jira issue ID
	 * @return the matching j i r a actions
	 */
	@Override
	public List<JIRAAction> findByJiraIssueId(long jiraIssueId) {
		return findByJiraIssueId(jiraIssueId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a actions where jiraIssueId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param jiraIssueId the jira issue ID
	 * @param start the lower bound of the range of j i r a actions
	 * @param end the upper bound of the range of j i r a actions (not inclusive)
	 * @return the range of matching j i r a actions
	 */
	@Override
	public List<JIRAAction> findByJiraIssueId(long jiraIssueId, int start,
		int end) {
		return findByJiraIssueId(jiraIssueId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a actions where jiraIssueId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param jiraIssueId the jira issue ID
	 * @param start the lower bound of the range of j i r a actions
	 * @param end the upper bound of the range of j i r a actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a actions
	 */
	@Override
	public List<JIRAAction> findByJiraIssueId(long jiraIssueId, int start,
		int end, OrderByComparator<JIRAAction> orderByComparator) {
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

		List<JIRAAction> list = (List<JIRAAction>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (JIRAAction jiraAction : list) {
				if ((jiraIssueId != jiraAction.getJiraIssueId())) {
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

			query.append(_SQL_SELECT_JIRAACTION_WHERE);

			query.append(_FINDER_COLUMN_JIRAISSUEID_JIRAISSUEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JIRAActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(jiraIssueId);

				if (!pagination) {
					list = (List<JIRAAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JIRAAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first j i r a action in the ordered set where jiraIssueId = &#63;.
	 *
	 * @param jiraIssueId the jira issue ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a action
	 * @throws NoSuchJIRAActionException if a matching j i r a action could not be found
	 */
	@Override
	public JIRAAction findByJiraIssueId_First(long jiraIssueId,
		OrderByComparator<JIRAAction> orderByComparator)
		throws NoSuchJIRAActionException {
		JIRAAction jiraAction = fetchByJiraIssueId_First(jiraIssueId,
				orderByComparator);

		if (jiraAction != null) {
			return jiraAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("jiraIssueId=");
		msg.append(jiraIssueId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAActionException(msg.toString());
	}

	/**
	 * Returns the first j i r a action in the ordered set where jiraIssueId = &#63;.
	 *
	 * @param jiraIssueId the jira issue ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a action, or <code>null</code> if a matching j i r a action could not be found
	 */
	@Override
	public JIRAAction fetchByJiraIssueId_First(long jiraIssueId,
		OrderByComparator<JIRAAction> orderByComparator) {
		List<JIRAAction> list = findByJiraIssueId(jiraIssueId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last j i r a action in the ordered set where jiraIssueId = &#63;.
	 *
	 * @param jiraIssueId the jira issue ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a action
	 * @throws NoSuchJIRAActionException if a matching j i r a action could not be found
	 */
	@Override
	public JIRAAction findByJiraIssueId_Last(long jiraIssueId,
		OrderByComparator<JIRAAction> orderByComparator)
		throws NoSuchJIRAActionException {
		JIRAAction jiraAction = fetchByJiraIssueId_Last(jiraIssueId,
				orderByComparator);

		if (jiraAction != null) {
			return jiraAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("jiraIssueId=");
		msg.append(jiraIssueId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAActionException(msg.toString());
	}

	/**
	 * Returns the last j i r a action in the ordered set where jiraIssueId = &#63;.
	 *
	 * @param jiraIssueId the jira issue ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a action, or <code>null</code> if a matching j i r a action could not be found
	 */
	@Override
	public JIRAAction fetchByJiraIssueId_Last(long jiraIssueId,
		OrderByComparator<JIRAAction> orderByComparator) {
		int count = countByJiraIssueId(jiraIssueId);

		if (count == 0) {
			return null;
		}

		List<JIRAAction> list = findByJiraIssueId(jiraIssueId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the j i r a actions before and after the current j i r a action in the ordered set where jiraIssueId = &#63;.
	 *
	 * @param jiraActionId the primary key of the current j i r a action
	 * @param jiraIssueId the jira issue ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a action
	 * @throws NoSuchJIRAActionException if a j i r a action with the primary key could not be found
	 */
	@Override
	public JIRAAction[] findByJiraIssueId_PrevAndNext(long jiraActionId,
		long jiraIssueId, OrderByComparator<JIRAAction> orderByComparator)
		throws NoSuchJIRAActionException {
		JIRAAction jiraAction = findByPrimaryKey(jiraActionId);

		Session session = null;

		try {
			session = openSession();

			JIRAAction[] array = new JIRAActionImpl[3];

			array[0] = getByJiraIssueId_PrevAndNext(session, jiraAction,
					jiraIssueId, orderByComparator, true);

			array[1] = jiraAction;

			array[2] = getByJiraIssueId_PrevAndNext(session, jiraAction,
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

	protected JIRAAction getByJiraIssueId_PrevAndNext(Session session,
		JIRAAction jiraAction, long jiraIssueId,
		OrderByComparator<JIRAAction> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRAACTION_WHERE);

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
			query.append(JIRAActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(jiraIssueId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(jiraAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the j i r a actions where jiraIssueId = &#63; from the database.
	 *
	 * @param jiraIssueId the jira issue ID
	 */
	@Override
	public void removeByJiraIssueId(long jiraIssueId) {
		for (JIRAAction jiraAction : findByJiraIssueId(jiraIssueId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(jiraAction);
		}
	}

	/**
	 * Returns the number of j i r a actions where jiraIssueId = &#63;.
	 *
	 * @param jiraIssueId the jira issue ID
	 * @return the number of matching j i r a actions
	 */
	@Override
	public int countByJiraIssueId(long jiraIssueId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_JIRAISSUEID;

		Object[] finderArgs = new Object[] { jiraIssueId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JIRAACTION_WHERE);

			query.append(_FINDER_COLUMN_JIRAISSUEID_JIRAISSUEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(jiraIssueId);

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

	private static final String _FINDER_COLUMN_JIRAISSUEID_JIRAISSUEID_2 = "jiraAction.jiraIssueId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPE = new FinderPath(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAActionModelImpl.FINDER_CACHE_ENABLED, JIRAActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByType",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE = new FinderPath(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAActionModelImpl.FINDER_CACHE_ENABLED, JIRAActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByType",
			new String[] { String.class.getName() },
			JIRAActionModelImpl.TYPE_COLUMN_BITMASK |
			JIRAActionModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TYPE = new FinderPath(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByType",
			new String[] { String.class.getName() });

	/**
	 * Returns all the j i r a actions where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching j i r a actions
	 */
	@Override
	public List<JIRAAction> findByType(String type) {
		return findByType(type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a actions where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of j i r a actions
	 * @param end the upper bound of the range of j i r a actions (not inclusive)
	 * @return the range of matching j i r a actions
	 */
	@Override
	public List<JIRAAction> findByType(String type, int start, int end) {
		return findByType(type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a actions where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of j i r a actions
	 * @param end the upper bound of the range of j i r a actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a actions
	 */
	@Override
	public List<JIRAAction> findByType(String type, int start, int end,
		OrderByComparator<JIRAAction> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE;
			finderArgs = new Object[] { type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPE;
			finderArgs = new Object[] { type, start, end, orderByComparator };
		}

		List<JIRAAction> list = (List<JIRAAction>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (JIRAAction jiraAction : list) {
				if (!Validator.equals(type, jiraAction.getType())) {
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

			query.append(_SQL_SELECT_JIRAACTION_WHERE);

			boolean bindType = false;

			if (type == null) {
				query.append(_FINDER_COLUMN_TYPE_TYPE_1);
			}
			else if (type.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TYPE_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_TYPE_TYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JIRAActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindType) {
					qPos.add(type);
				}

				if (!pagination) {
					list = (List<JIRAAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JIRAAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first j i r a action in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a action
	 * @throws NoSuchJIRAActionException if a matching j i r a action could not be found
	 */
	@Override
	public JIRAAction findByType_First(String type,
		OrderByComparator<JIRAAction> orderByComparator)
		throws NoSuchJIRAActionException {
		JIRAAction jiraAction = fetchByType_First(type, orderByComparator);

		if (jiraAction != null) {
			return jiraAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAActionException(msg.toString());
	}

	/**
	 * Returns the first j i r a action in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a action, or <code>null</code> if a matching j i r a action could not be found
	 */
	@Override
	public JIRAAction fetchByType_First(String type,
		OrderByComparator<JIRAAction> orderByComparator) {
		List<JIRAAction> list = findByType(type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last j i r a action in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a action
	 * @throws NoSuchJIRAActionException if a matching j i r a action could not be found
	 */
	@Override
	public JIRAAction findByType_Last(String type,
		OrderByComparator<JIRAAction> orderByComparator)
		throws NoSuchJIRAActionException {
		JIRAAction jiraAction = fetchByType_Last(type, orderByComparator);

		if (jiraAction != null) {
			return jiraAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAActionException(msg.toString());
	}

	/**
	 * Returns the last j i r a action in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a action, or <code>null</code> if a matching j i r a action could not be found
	 */
	@Override
	public JIRAAction fetchByType_Last(String type,
		OrderByComparator<JIRAAction> orderByComparator) {
		int count = countByType(type);

		if (count == 0) {
			return null;
		}

		List<JIRAAction> list = findByType(type, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the j i r a actions before and after the current j i r a action in the ordered set where type = &#63;.
	 *
	 * @param jiraActionId the primary key of the current j i r a action
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a action
	 * @throws NoSuchJIRAActionException if a j i r a action with the primary key could not be found
	 */
	@Override
	public JIRAAction[] findByType_PrevAndNext(long jiraActionId, String type,
		OrderByComparator<JIRAAction> orderByComparator)
		throws NoSuchJIRAActionException {
		JIRAAction jiraAction = findByPrimaryKey(jiraActionId);

		Session session = null;

		try {
			session = openSession();

			JIRAAction[] array = new JIRAActionImpl[3];

			array[0] = getByType_PrevAndNext(session, jiraAction, type,
					orderByComparator, true);

			array[1] = jiraAction;

			array[2] = getByType_PrevAndNext(session, jiraAction, type,
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

	protected JIRAAction getByType_PrevAndNext(Session session,
		JIRAAction jiraAction, String type,
		OrderByComparator<JIRAAction> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRAACTION_WHERE);

		boolean bindType = false;

		if (type == null) {
			query.append(_FINDER_COLUMN_TYPE_TYPE_1);
		}
		else if (type.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_TYPE_TYPE_3);
		}
		else {
			bindType = true;

			query.append(_FINDER_COLUMN_TYPE_TYPE_2);
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
			query.append(JIRAActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindType) {
			qPos.add(type);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(jiraAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the j i r a actions where type = &#63; from the database.
	 *
	 * @param type the type
	 */
	@Override
	public void removeByType(String type) {
		for (JIRAAction jiraAction : findByType(type, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(jiraAction);
		}
	}

	/**
	 * Returns the number of j i r a actions where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching j i r a actions
	 */
	@Override
	public int countByType(String type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TYPE;

		Object[] finderArgs = new Object[] { type };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JIRAACTION_WHERE);

			boolean bindType = false;

			if (type == null) {
				query.append(_FINDER_COLUMN_TYPE_TYPE_1);
			}
			else if (type.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TYPE_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_TYPE_TYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindType) {
					qPos.add(type);
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

	private static final String _FINDER_COLUMN_TYPE_TYPE_1 = "jiraAction.type IS NULL";
	private static final String _FINDER_COLUMN_TYPE_TYPE_2 = "jiraAction.type = ?";
	private static final String _FINDER_COLUMN_TYPE_TYPE_3 = "(jiraAction.type IS NULL OR jiraAction.type = '')";

	public JIRAActionPersistenceImpl() {
		setModelClass(JIRAAction.class);
	}

	/**
	 * Caches the j i r a action in the entity cache if it is enabled.
	 *
	 * @param jiraAction the j i r a action
	 */
	@Override
	public void cacheResult(JIRAAction jiraAction) {
		EntityCacheUtil.putResult(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAActionImpl.class, jiraAction.getPrimaryKey(), jiraAction);

		jiraAction.resetOriginalValues();
	}

	/**
	 * Caches the j i r a actions in the entity cache if it is enabled.
	 *
	 * @param jiraActions the j i r a actions
	 */
	@Override
	public void cacheResult(List<JIRAAction> jiraActions) {
		for (JIRAAction jiraAction : jiraActions) {
			if (EntityCacheUtil.getResult(
						JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
						JIRAActionImpl.class, jiraAction.getPrimaryKey()) == null) {
				cacheResult(jiraAction);
			}
			else {
				jiraAction.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all j i r a actions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(JIRAActionImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the j i r a action.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(JIRAAction jiraAction) {
		EntityCacheUtil.removeResult(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAActionImpl.class, jiraAction.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<JIRAAction> jiraActions) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (JIRAAction jiraAction : jiraActions) {
			EntityCacheUtil.removeResult(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
				JIRAActionImpl.class, jiraAction.getPrimaryKey());
		}
	}

	/**
	 * Creates a new j i r a action with the primary key. Does not add the j i r a action to the database.
	 *
	 * @param jiraActionId the primary key for the new j i r a action
	 * @return the new j i r a action
	 */
	@Override
	public JIRAAction create(long jiraActionId) {
		JIRAAction jiraAction = new JIRAActionImpl();

		jiraAction.setNew(true);
		jiraAction.setPrimaryKey(jiraActionId);

		return jiraAction;
	}

	/**
	 * Removes the j i r a action with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraActionId the primary key of the j i r a action
	 * @return the j i r a action that was removed
	 * @throws NoSuchJIRAActionException if a j i r a action with the primary key could not be found
	 */
	@Override
	public JIRAAction remove(long jiraActionId)
		throws NoSuchJIRAActionException {
		return remove((Serializable)jiraActionId);
	}

	/**
	 * Removes the j i r a action with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the j i r a action
	 * @return the j i r a action that was removed
	 * @throws NoSuchJIRAActionException if a j i r a action with the primary key could not be found
	 */
	@Override
	public JIRAAction remove(Serializable primaryKey)
		throws NoSuchJIRAActionException {
		Session session = null;

		try {
			session = openSession();

			JIRAAction jiraAction = (JIRAAction)session.get(JIRAActionImpl.class,
					primaryKey);

			if (jiraAction == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchJIRAActionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(jiraAction);
		}
		catch (NoSuchJIRAActionException nsee) {
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
	protected JIRAAction removeImpl(JIRAAction jiraAction) {
		jiraAction = toUnwrappedModel(jiraAction);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(jiraAction)) {
				jiraAction = (JIRAAction)session.get(JIRAActionImpl.class,
						jiraAction.getPrimaryKeyObj());
			}

			if (jiraAction != null) {
				session.delete(jiraAction);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (jiraAction != null) {
			clearCache(jiraAction);
		}

		return jiraAction;
	}

	@Override
	public JIRAAction updateImpl(JIRAAction jiraAction) {
		jiraAction = toUnwrappedModel(jiraAction);

		boolean isNew = jiraAction.isNew();

		JIRAActionModelImpl jiraActionModelImpl = (JIRAActionModelImpl)jiraAction;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (jiraAction.getCreateDate() == null)) {
			if (serviceContext == null) {
				jiraAction.setCreateDate(now);
			}
			else {
				jiraAction.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!jiraActionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				jiraAction.setModifiedDate(now);
			}
			else {
				jiraAction.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (jiraAction.isNew()) {
				session.save(jiraAction);

				jiraAction.setNew(false);
			}
			else {
				session.merge(jiraAction);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !JIRAActionModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((jiraActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JIRAUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jiraActionModelImpl.getOriginalJiraUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_JIRAUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JIRAUSERID,
					args);

				args = new Object[] { jiraActionModelImpl.getJiraUserId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_JIRAUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JIRAUSERID,
					args);
			}

			if ((jiraActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JIRAISSUEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jiraActionModelImpl.getOriginalJiraIssueId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_JIRAISSUEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JIRAISSUEID,
					args);

				args = new Object[] { jiraActionModelImpl.getJiraIssueId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_JIRAISSUEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JIRAISSUEID,
					args);
			}

			if ((jiraActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jiraActionModelImpl.getOriginalType()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPE, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE,
					args);

				args = new Object[] { jiraActionModelImpl.getType() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPE, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE,
					args);
			}
		}

		EntityCacheUtil.putResult(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
			JIRAActionImpl.class, jiraAction.getPrimaryKey(), jiraAction, false);

		jiraAction.resetOriginalValues();

		return jiraAction;
	}

	protected JIRAAction toUnwrappedModel(JIRAAction jiraAction) {
		if (jiraAction instanceof JIRAActionImpl) {
			return jiraAction;
		}

		JIRAActionImpl jiraActionImpl = new JIRAActionImpl();

		jiraActionImpl.setNew(jiraAction.isNew());
		jiraActionImpl.setPrimaryKey(jiraAction.getPrimaryKey());

		jiraActionImpl.setJiraActionId(jiraAction.getJiraActionId());
		jiraActionImpl.setJiraUserId(jiraAction.getJiraUserId());
		jiraActionImpl.setCreateDate(jiraAction.getCreateDate());
		jiraActionImpl.setModifiedDate(jiraAction.getModifiedDate());
		jiraActionImpl.setJiraIssueId(jiraAction.getJiraIssueId());
		jiraActionImpl.setType(jiraAction.getType());
		jiraActionImpl.setBody(jiraAction.getBody());
		jiraActionImpl.setJiraGroupName(jiraAction.getJiraGroupName());

		return jiraActionImpl;
	}

	/**
	 * Returns the j i r a action with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the j i r a action
	 * @return the j i r a action
	 * @throws NoSuchJIRAActionException if a j i r a action with the primary key could not be found
	 */
	@Override
	public JIRAAction findByPrimaryKey(Serializable primaryKey)
		throws NoSuchJIRAActionException {
		JIRAAction jiraAction = fetchByPrimaryKey(primaryKey);

		if (jiraAction == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchJIRAActionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return jiraAction;
	}

	/**
	 * Returns the j i r a action with the primary key or throws a {@link NoSuchJIRAActionException} if it could not be found.
	 *
	 * @param jiraActionId the primary key of the j i r a action
	 * @return the j i r a action
	 * @throws NoSuchJIRAActionException if a j i r a action with the primary key could not be found
	 */
	@Override
	public JIRAAction findByPrimaryKey(long jiraActionId)
		throws NoSuchJIRAActionException {
		return findByPrimaryKey((Serializable)jiraActionId);
	}

	/**
	 * Returns the j i r a action with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the j i r a action
	 * @return the j i r a action, or <code>null</code> if a j i r a action with the primary key could not be found
	 */
	@Override
	public JIRAAction fetchByPrimaryKey(Serializable primaryKey) {
		JIRAAction jiraAction = (JIRAAction)EntityCacheUtil.getResult(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
				JIRAActionImpl.class, primaryKey);

		if (jiraAction == _nullJIRAAction) {
			return null;
		}

		if (jiraAction == null) {
			Session session = null;

			try {
				session = openSession();

				jiraAction = (JIRAAction)session.get(JIRAActionImpl.class,
						primaryKey);

				if (jiraAction != null) {
					cacheResult(jiraAction);
				}
				else {
					EntityCacheUtil.putResult(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
						JIRAActionImpl.class, primaryKey, _nullJIRAAction);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
					JIRAActionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return jiraAction;
	}

	/**
	 * Returns the j i r a action with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param jiraActionId the primary key of the j i r a action
	 * @return the j i r a action, or <code>null</code> if a j i r a action with the primary key could not be found
	 */
	@Override
	public JIRAAction fetchByPrimaryKey(long jiraActionId) {
		return fetchByPrimaryKey((Serializable)jiraActionId);
	}

	@Override
	public Map<Serializable, JIRAAction> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, JIRAAction> map = new HashMap<Serializable, JIRAAction>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			JIRAAction jiraAction = fetchByPrimaryKey(primaryKey);

			if (jiraAction != null) {
				map.put(primaryKey, jiraAction);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			JIRAAction jiraAction = (JIRAAction)EntityCacheUtil.getResult(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
					JIRAActionImpl.class, primaryKey);

			if (jiraAction == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, jiraAction);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_JIRAACTION_WHERE_PKS_IN);

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

			for (JIRAAction jiraAction : (List<JIRAAction>)q.list()) {
				map.put(jiraAction.getPrimaryKeyObj(), jiraAction);

				cacheResult(jiraAction);

				uncachedPrimaryKeys.remove(jiraAction.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(JIRAActionModelImpl.ENTITY_CACHE_ENABLED,
					JIRAActionImpl.class, primaryKey, _nullJIRAAction);
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
	 * Returns all the j i r a actions.
	 *
	 * @return the j i r a actions
	 */
	@Override
	public List<JIRAAction> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a actions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of j i r a actions
	 * @param end the upper bound of the range of j i r a actions (not inclusive)
	 * @return the range of j i r a actions
	 */
	@Override
	public List<JIRAAction> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a actions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of j i r a actions
	 * @param end the upper bound of the range of j i r a actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of j i r a actions
	 */
	@Override
	public List<JIRAAction> findAll(int start, int end,
		OrderByComparator<JIRAAction> orderByComparator) {
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

		List<JIRAAction> list = (List<JIRAAction>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_JIRAACTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_JIRAACTION;

				if (pagination) {
					sql = sql.concat(JIRAActionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<JIRAAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JIRAAction>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the j i r a actions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (JIRAAction jiraAction : findAll()) {
			remove(jiraAction);
		}
	}

	/**
	 * Returns the number of j i r a actions.
	 *
	 * @return the number of j i r a actions
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_JIRAACTION);

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

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return JIRAActionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the j i r a action persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(JIRAActionImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_JIRAACTION = "SELECT jiraAction FROM JIRAAction jiraAction";
	private static final String _SQL_SELECT_JIRAACTION_WHERE_PKS_IN = "SELECT jiraAction FROM JIRAAction jiraAction WHERE id IN (";
	private static final String _SQL_SELECT_JIRAACTION_WHERE = "SELECT jiraAction FROM JIRAAction jiraAction WHERE ";
	private static final String _SQL_COUNT_JIRAACTION = "SELECT COUNT(jiraAction) FROM JIRAAction jiraAction";
	private static final String _SQL_COUNT_JIRAACTION_WHERE = "SELECT COUNT(jiraAction) FROM JIRAAction jiraAction WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "jiraAction.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No JIRAAction exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No JIRAAction exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(JIRAActionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"jiraActionId", "jiraUserId", "createDate", "modifiedDate",
				"jiraIssueId", "type", "body", "jiraGroupName"
			});
	private static final JIRAAction _nullJIRAAction = new JIRAActionImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<JIRAAction> toCacheModel() {
				return _nullJIRAActionCacheModel;
			}
		};

	private static final CacheModel<JIRAAction> _nullJIRAActionCacheModel = new CacheModel<JIRAAction>() {
			@Override
			public JIRAAction toEntityModel() {
				return _nullJIRAAction;
			}
		};
}