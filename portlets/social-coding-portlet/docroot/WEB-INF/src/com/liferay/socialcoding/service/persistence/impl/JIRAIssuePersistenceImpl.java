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
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.socialcoding.NoSuchJIRAIssueException;
import com.liferay.socialcoding.model.JIRAIssue;
import com.liferay.socialcoding.model.impl.JIRAIssueImpl;
import com.liferay.socialcoding.model.impl.JIRAIssueModelImpl;
import com.liferay.socialcoding.service.persistence.JIRAIssuePersistence;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the j i r a issue service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAIssuePersistence
 * @see com.liferay.socialcoding.service.persistence.JIRAIssueUtil
 * @generated
 */
@ProviderType
public class JIRAIssuePersistenceImpl extends BasePersistenceImpl<JIRAIssue>
	implements JIRAIssuePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link JIRAIssueUtil} to access the j i r a issue persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = JIRAIssueImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PROJECTID =
		new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByProjectId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECTID =
		new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByProjectId",
			new String[] { Long.class.getName() },
			JIRAIssueModelImpl.PROJECTID_COLUMN_BITMASK |
			JIRAIssueModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PROJECTID = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProjectId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the j i r a issues where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @return the matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByProjectId(long projectId) {
		return findByProjectId(projectId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the j i r a issues where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @return the range of matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByProjectId(long projectId, int start, int end) {
		return findByProjectId(projectId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a issues where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByProjectId(long projectId, int start, int end,
		OrderByComparator<JIRAIssue> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECTID;
			finderArgs = new Object[] { projectId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PROJECTID;
			finderArgs = new Object[] { projectId, start, end, orderByComparator };
		}

		List<JIRAIssue> list = (List<JIRAIssue>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (JIRAIssue jiraIssue : list) {
				if ((projectId != jiraIssue.getProjectId())) {
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

			query.append(_SQL_SELECT_JIRAISSUE_WHERE);

			query.append(_FINDER_COLUMN_PROJECTID_PROJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (!pagination) {
					list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first j i r a issue in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue
	 * @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue findByProjectId_First(long projectId,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = fetchByProjectId_First(projectId,
				orderByComparator);

		if (jiraIssue != null) {
			return jiraIssue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("projectId=");
		msg.append(projectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAIssueException(msg.toString());
	}

	/**
	 * Returns the first j i r a issue in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue fetchByProjectId_First(long projectId,
		OrderByComparator<JIRAIssue> orderByComparator) {
		List<JIRAIssue> list = findByProjectId(projectId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last j i r a issue in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue
	 * @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue findByProjectId_Last(long projectId,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = fetchByProjectId_Last(projectId, orderByComparator);

		if (jiraIssue != null) {
			return jiraIssue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("projectId=");
		msg.append(projectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAIssueException(msg.toString());
	}

	/**
	 * Returns the last j i r a issue in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue fetchByProjectId_Last(long projectId,
		OrderByComparator<JIRAIssue> orderByComparator) {
		int count = countByProjectId(projectId);

		if (count == 0) {
			return null;
		}

		List<JIRAIssue> list = findByProjectId(projectId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the j i r a issues before and after the current j i r a issue in the ordered set where projectId = &#63;.
	 *
	 * @param jiraIssueId the primary key of the current j i r a issue
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a issue
	 * @throws NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	 */
	@Override
	public JIRAIssue[] findByProjectId_PrevAndNext(long jiraIssueId,
		long projectId, OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		Session session = null;

		try {
			session = openSession();

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = getByProjectId_PrevAndNext(session, jiraIssue,
					projectId, orderByComparator, true);

			array[1] = jiraIssue;

			array[2] = getByProjectId_PrevAndNext(session, jiraIssue,
					projectId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JIRAIssue getByProjectId_PrevAndNext(Session session,
		JIRAIssue jiraIssue, long projectId,
		OrderByComparator<JIRAIssue> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRAISSUE_WHERE);

		query.append(_FINDER_COLUMN_PROJECTID_PROJECTID_2);

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
			query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(projectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(jiraIssue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAIssue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the j i r a issues where projectId = &#63; from the database.
	 *
	 * @param projectId the project ID
	 */
	@Override
	public void removeByProjectId(long projectId) {
		for (JIRAIssue jiraIssue : findByProjectId(projectId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(jiraIssue);
		}
	}

	/**
	 * Returns the number of j i r a issues where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @return the number of matching j i r a issues
	 */
	@Override
	public int countByProjectId(long projectId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PROJECTID;

		Object[] finderArgs = new Object[] { projectId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JIRAISSUE_WHERE);

			query.append(_FINDER_COLUMN_PROJECTID_PROJECTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

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

	private static final String _FINDER_COLUMN_PROJECTID_PROJECTID_2 = "jiraIssue.projectId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_REPORTERJIRAUSERID =
		new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByReporterJiraUserId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REPORTERJIRAUSERID =
		new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByReporterJiraUserId",
			new String[] { String.class.getName() },
			JIRAIssueModelImpl.REPORTERJIRAUSERID_COLUMN_BITMASK |
			JIRAIssueModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_REPORTERJIRAUSERID = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByReporterJiraUserId", new String[] { String.class.getName() });

	/**
	 * Returns all the j i r a issues where reporterJiraUserId = &#63;.
	 *
	 * @param reporterJiraUserId the reporter jira user ID
	 * @return the matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByReporterJiraUserId(String reporterJiraUserId) {
		return findByReporterJiraUserId(reporterJiraUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a issues where reporterJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @return the range of matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByReporterJiraUserId(String reporterJiraUserId,
		int start, int end) {
		return findByReporterJiraUserId(reporterJiraUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a issues where reporterJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByReporterJiraUserId(String reporterJiraUserId,
		int start, int end, OrderByComparator<JIRAIssue> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REPORTERJIRAUSERID;
			finderArgs = new Object[] { reporterJiraUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_REPORTERJIRAUSERID;
			finderArgs = new Object[] {
					reporterJiraUserId,
					
					start, end, orderByComparator
				};
		}

		List<JIRAIssue> list = (List<JIRAIssue>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (JIRAIssue jiraIssue : list) {
				if (!Validator.equals(reporterJiraUserId,
							jiraIssue.getReporterJiraUserId())) {
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

			query.append(_SQL_SELECT_JIRAISSUE_WHERE);

			boolean bindReporterJiraUserId = false;

			if (reporterJiraUserId == null) {
				query.append(_FINDER_COLUMN_REPORTERJIRAUSERID_REPORTERJIRAUSERID_1);
			}
			else if (reporterJiraUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_REPORTERJIRAUSERID_REPORTERJIRAUSERID_3);
			}
			else {
				bindReporterJiraUserId = true;

				query.append(_FINDER_COLUMN_REPORTERJIRAUSERID_REPORTERJIRAUSERID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindReporterJiraUserId) {
					qPos.add(reporterJiraUserId);
				}

				if (!pagination) {
					list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first j i r a issue in the ordered set where reporterJiraUserId = &#63;.
	 *
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue
	 * @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue findByReporterJiraUserId_First(String reporterJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = fetchByReporterJiraUserId_First(reporterJiraUserId,
				orderByComparator);

		if (jiraIssue != null) {
			return jiraIssue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("reporterJiraUserId=");
		msg.append(reporterJiraUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAIssueException(msg.toString());
	}

	/**
	 * Returns the first j i r a issue in the ordered set where reporterJiraUserId = &#63;.
	 *
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue fetchByReporterJiraUserId_First(
		String reporterJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator) {
		List<JIRAIssue> list = findByReporterJiraUserId(reporterJiraUserId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last j i r a issue in the ordered set where reporterJiraUserId = &#63;.
	 *
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue
	 * @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue findByReporterJiraUserId_Last(String reporterJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = fetchByReporterJiraUserId_Last(reporterJiraUserId,
				orderByComparator);

		if (jiraIssue != null) {
			return jiraIssue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("reporterJiraUserId=");
		msg.append(reporterJiraUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAIssueException(msg.toString());
	}

	/**
	 * Returns the last j i r a issue in the ordered set where reporterJiraUserId = &#63;.
	 *
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue fetchByReporterJiraUserId_Last(String reporterJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator) {
		int count = countByReporterJiraUserId(reporterJiraUserId);

		if (count == 0) {
			return null;
		}

		List<JIRAIssue> list = findByReporterJiraUserId(reporterJiraUserId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the j i r a issues before and after the current j i r a issue in the ordered set where reporterJiraUserId = &#63;.
	 *
	 * @param jiraIssueId the primary key of the current j i r a issue
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a issue
	 * @throws NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	 */
	@Override
	public JIRAIssue[] findByReporterJiraUserId_PrevAndNext(long jiraIssueId,
		String reporterJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		Session session = null;

		try {
			session = openSession();

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = getByReporterJiraUserId_PrevAndNext(session, jiraIssue,
					reporterJiraUserId, orderByComparator, true);

			array[1] = jiraIssue;

			array[2] = getByReporterJiraUserId_PrevAndNext(session, jiraIssue,
					reporterJiraUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JIRAIssue getByReporterJiraUserId_PrevAndNext(Session session,
		JIRAIssue jiraIssue, String reporterJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRAISSUE_WHERE);

		boolean bindReporterJiraUserId = false;

		if (reporterJiraUserId == null) {
			query.append(_FINDER_COLUMN_REPORTERJIRAUSERID_REPORTERJIRAUSERID_1);
		}
		else if (reporterJiraUserId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_REPORTERJIRAUSERID_REPORTERJIRAUSERID_3);
		}
		else {
			bindReporterJiraUserId = true;

			query.append(_FINDER_COLUMN_REPORTERJIRAUSERID_REPORTERJIRAUSERID_2);
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
			query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindReporterJiraUserId) {
			qPos.add(reporterJiraUserId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(jiraIssue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAIssue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the j i r a issues where reporterJiraUserId = &#63; from the database.
	 *
	 * @param reporterJiraUserId the reporter jira user ID
	 */
	@Override
	public void removeByReporterJiraUserId(String reporterJiraUserId) {
		for (JIRAIssue jiraIssue : findByReporterJiraUserId(
				reporterJiraUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(jiraIssue);
		}
	}

	/**
	 * Returns the number of j i r a issues where reporterJiraUserId = &#63;.
	 *
	 * @param reporterJiraUserId the reporter jira user ID
	 * @return the number of matching j i r a issues
	 */
	@Override
	public int countByReporterJiraUserId(String reporterJiraUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_REPORTERJIRAUSERID;

		Object[] finderArgs = new Object[] { reporterJiraUserId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JIRAISSUE_WHERE);

			boolean bindReporterJiraUserId = false;

			if (reporterJiraUserId == null) {
				query.append(_FINDER_COLUMN_REPORTERJIRAUSERID_REPORTERJIRAUSERID_1);
			}
			else if (reporterJiraUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_REPORTERJIRAUSERID_REPORTERJIRAUSERID_3);
			}
			else {
				bindReporterJiraUserId = true;

				query.append(_FINDER_COLUMN_REPORTERJIRAUSERID_REPORTERJIRAUSERID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindReporterJiraUserId) {
					qPos.add(reporterJiraUserId);
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

	private static final String _FINDER_COLUMN_REPORTERJIRAUSERID_REPORTERJIRAUSERID_1 =
		"jiraIssue.reporterJiraUserId IS NULL";
	private static final String _FINDER_COLUMN_REPORTERJIRAUSERID_REPORTERJIRAUSERID_2 =
		"jiraIssue.reporterJiraUserId = ?";
	private static final String _FINDER_COLUMN_REPORTERJIRAUSERID_REPORTERJIRAUSERID_3 =
		"(jiraIssue.reporterJiraUserId IS NULL OR jiraIssue.reporterJiraUserId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSIGNEEJIRAUSERID =
		new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAssigneeJiraUserId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSIGNEEJIRAUSERID =
		new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByAssigneeJiraUserId",
			new String[] { String.class.getName() },
			JIRAIssueModelImpl.ASSIGNEEJIRAUSERID_COLUMN_BITMASK |
			JIRAIssueModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ASSIGNEEJIRAUSERID = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByAssigneeJiraUserId", new String[] { String.class.getName() });

	/**
	 * Returns all the j i r a issues where assigneeJiraUserId = &#63;.
	 *
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @return the matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByAssigneeJiraUserId(String assigneeJiraUserId) {
		return findByAssigneeJiraUserId(assigneeJiraUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a issues where assigneeJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @return the range of matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByAssigneeJiraUserId(String assigneeJiraUserId,
		int start, int end) {
		return findByAssigneeJiraUserId(assigneeJiraUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a issues where assigneeJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByAssigneeJiraUserId(String assigneeJiraUserId,
		int start, int end, OrderByComparator<JIRAIssue> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSIGNEEJIRAUSERID;
			finderArgs = new Object[] { assigneeJiraUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSIGNEEJIRAUSERID;
			finderArgs = new Object[] {
					assigneeJiraUserId,
					
					start, end, orderByComparator
				};
		}

		List<JIRAIssue> list = (List<JIRAIssue>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (JIRAIssue jiraIssue : list) {
				if (!Validator.equals(assigneeJiraUserId,
							jiraIssue.getAssigneeJiraUserId())) {
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

			query.append(_SQL_SELECT_JIRAISSUE_WHERE);

			boolean bindAssigneeJiraUserId = false;

			if (assigneeJiraUserId == null) {
				query.append(_FINDER_COLUMN_ASSIGNEEJIRAUSERID_ASSIGNEEJIRAUSERID_1);
			}
			else if (assigneeJiraUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ASSIGNEEJIRAUSERID_ASSIGNEEJIRAUSERID_3);
			}
			else {
				bindAssigneeJiraUserId = true;

				query.append(_FINDER_COLUMN_ASSIGNEEJIRAUSERID_ASSIGNEEJIRAUSERID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindAssigneeJiraUserId) {
					qPos.add(assigneeJiraUserId);
				}

				if (!pagination) {
					list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first j i r a issue in the ordered set where assigneeJiraUserId = &#63;.
	 *
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue
	 * @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue findByAssigneeJiraUserId_First(String assigneeJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = fetchByAssigneeJiraUserId_First(assigneeJiraUserId,
				orderByComparator);

		if (jiraIssue != null) {
			return jiraIssue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assigneeJiraUserId=");
		msg.append(assigneeJiraUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAIssueException(msg.toString());
	}

	/**
	 * Returns the first j i r a issue in the ordered set where assigneeJiraUserId = &#63;.
	 *
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue fetchByAssigneeJiraUserId_First(
		String assigneeJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator) {
		List<JIRAIssue> list = findByAssigneeJiraUserId(assigneeJiraUserId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last j i r a issue in the ordered set where assigneeJiraUserId = &#63;.
	 *
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue
	 * @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue findByAssigneeJiraUserId_Last(String assigneeJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = fetchByAssigneeJiraUserId_Last(assigneeJiraUserId,
				orderByComparator);

		if (jiraIssue != null) {
			return jiraIssue;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assigneeJiraUserId=");
		msg.append(assigneeJiraUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAIssueException(msg.toString());
	}

	/**
	 * Returns the last j i r a issue in the ordered set where assigneeJiraUserId = &#63;.
	 *
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue fetchByAssigneeJiraUserId_Last(String assigneeJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator) {
		int count = countByAssigneeJiraUserId(assigneeJiraUserId);

		if (count == 0) {
			return null;
		}

		List<JIRAIssue> list = findByAssigneeJiraUserId(assigneeJiraUserId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the j i r a issues before and after the current j i r a issue in the ordered set where assigneeJiraUserId = &#63;.
	 *
	 * @param jiraIssueId the primary key of the current j i r a issue
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a issue
	 * @throws NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	 */
	@Override
	public JIRAIssue[] findByAssigneeJiraUserId_PrevAndNext(long jiraIssueId,
		String assigneeJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		Session session = null;

		try {
			session = openSession();

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = getByAssigneeJiraUserId_PrevAndNext(session, jiraIssue,
					assigneeJiraUserId, orderByComparator, true);

			array[1] = jiraIssue;

			array[2] = getByAssigneeJiraUserId_PrevAndNext(session, jiraIssue,
					assigneeJiraUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JIRAIssue getByAssigneeJiraUserId_PrevAndNext(Session session,
		JIRAIssue jiraIssue, String assigneeJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRAISSUE_WHERE);

		boolean bindAssigneeJiraUserId = false;

		if (assigneeJiraUserId == null) {
			query.append(_FINDER_COLUMN_ASSIGNEEJIRAUSERID_ASSIGNEEJIRAUSERID_1);
		}
		else if (assigneeJiraUserId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_ASSIGNEEJIRAUSERID_ASSIGNEEJIRAUSERID_3);
		}
		else {
			bindAssigneeJiraUserId = true;

			query.append(_FINDER_COLUMN_ASSIGNEEJIRAUSERID_ASSIGNEEJIRAUSERID_2);
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
			query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindAssigneeJiraUserId) {
			qPos.add(assigneeJiraUserId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(jiraIssue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAIssue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the j i r a issues where assigneeJiraUserId = &#63; from the database.
	 *
	 * @param assigneeJiraUserId the assignee jira user ID
	 */
	@Override
	public void removeByAssigneeJiraUserId(String assigneeJiraUserId) {
		for (JIRAIssue jiraIssue : findByAssigneeJiraUserId(
				assigneeJiraUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(jiraIssue);
		}
	}

	/**
	 * Returns the number of j i r a issues where assigneeJiraUserId = &#63;.
	 *
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @return the number of matching j i r a issues
	 */
	@Override
	public int countByAssigneeJiraUserId(String assigneeJiraUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ASSIGNEEJIRAUSERID;

		Object[] finderArgs = new Object[] { assigneeJiraUserId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JIRAISSUE_WHERE);

			boolean bindAssigneeJiraUserId = false;

			if (assigneeJiraUserId == null) {
				query.append(_FINDER_COLUMN_ASSIGNEEJIRAUSERID_ASSIGNEEJIRAUSERID_1);
			}
			else if (assigneeJiraUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ASSIGNEEJIRAUSERID_ASSIGNEEJIRAUSERID_3);
			}
			else {
				bindAssigneeJiraUserId = true;

				query.append(_FINDER_COLUMN_ASSIGNEEJIRAUSERID_ASSIGNEEJIRAUSERID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindAssigneeJiraUserId) {
					qPos.add(assigneeJiraUserId);
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

	private static final String _FINDER_COLUMN_ASSIGNEEJIRAUSERID_ASSIGNEEJIRAUSERID_1 =
		"jiraIssue.assigneeJiraUserId IS NULL";
	private static final String _FINDER_COLUMN_ASSIGNEEJIRAUSERID_ASSIGNEEJIRAUSERID_2 =
		"jiraIssue.assigneeJiraUserId = ?";
	private static final String _FINDER_COLUMN_ASSIGNEEJIRAUSERID_ASSIGNEEJIRAUSERID_3 =
		"(jiraIssue.assigneeJiraUserId IS NULL OR jiraIssue.assigneeJiraUserId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MD_P = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByMD_P",
			new String[] {
				Date.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_MD_P = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByMD_P",
			new String[] { Date.class.getName(), Long.class.getName() });

	/**
	 * Returns all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @return the matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByMD_P(Date modifiedDate, long projectId) {
		return findByMD_P(modifiedDate, projectId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @return the range of matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByMD_P(Date modifiedDate, long projectId,
		int start, int end) {
		return findByMD_P(modifiedDate, projectId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByMD_P(Date modifiedDate, long projectId,
		int start, int end, OrderByComparator<JIRAIssue> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MD_P;
		finderArgs = new Object[] {
				modifiedDate, projectId,
				
				start, end, orderByComparator
			};

		List<JIRAIssue> list = (List<JIRAIssue>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (JIRAIssue jiraIssue : list) {
				if ((modifiedDate.getTime() >= jiraIssue.getModifiedDate()
															.getTime()) ||
						(projectId != jiraIssue.getProjectId())) {
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

			query.append(_SQL_SELECT_JIRAISSUE_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_MD_P_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_MD_P_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_MD_P_PROJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
				}

				qPos.add(projectId);

				if (!pagination) {
					list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue
	 * @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue findByMD_P_First(Date modifiedDate, long projectId,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = fetchByMD_P_First(modifiedDate, projectId,
				orderByComparator);

		if (jiraIssue != null) {
			return jiraIssue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", projectId=");
		msg.append(projectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAIssueException(msg.toString());
	}

	/**
	 * Returns the first j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue fetchByMD_P_First(Date modifiedDate, long projectId,
		OrderByComparator<JIRAIssue> orderByComparator) {
		List<JIRAIssue> list = findByMD_P(modifiedDate, projectId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue
	 * @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue findByMD_P_Last(Date modifiedDate, long projectId,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = fetchByMD_P_Last(modifiedDate, projectId,
				orderByComparator);

		if (jiraIssue != null) {
			return jiraIssue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", projectId=");
		msg.append(projectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAIssueException(msg.toString());
	}

	/**
	 * Returns the last j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue fetchByMD_P_Last(Date modifiedDate, long projectId,
		OrderByComparator<JIRAIssue> orderByComparator) {
		int count = countByMD_P(modifiedDate, projectId);

		if (count == 0) {
			return null;
		}

		List<JIRAIssue> list = findByMD_P(modifiedDate, projectId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the j i r a issues before and after the current j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63;.
	 *
	 * @param jiraIssueId the primary key of the current j i r a issue
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a issue
	 * @throws NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	 */
	@Override
	public JIRAIssue[] findByMD_P_PrevAndNext(long jiraIssueId,
		Date modifiedDate, long projectId,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		Session session = null;

		try {
			session = openSession();

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = getByMD_P_PrevAndNext(session, jiraIssue, modifiedDate,
					projectId, orderByComparator, true);

			array[1] = jiraIssue;

			array[2] = getByMD_P_PrevAndNext(session, jiraIssue, modifiedDate,
					projectId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JIRAIssue getByMD_P_PrevAndNext(Session session,
		JIRAIssue jiraIssue, Date modifiedDate, long projectId,
		OrderByComparator<JIRAIssue> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRAISSUE_WHERE);

		boolean bindModifiedDate = false;

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_MD_P_MODIFIEDDATE_1);
		}
		else {
			bindModifiedDate = true;

			query.append(_FINDER_COLUMN_MD_P_MODIFIEDDATE_2);
		}

		query.append(_FINDER_COLUMN_MD_P_PROJECTID_2);

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
			query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindModifiedDate) {
			qPos.add(new Timestamp(modifiedDate.getTime()));
		}

		qPos.add(projectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(jiraIssue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAIssue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 */
	@Override
	public void removeByMD_P(Date modifiedDate, long projectId) {
		for (JIRAIssue jiraIssue : findByMD_P(modifiedDate, projectId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(jiraIssue);
		}
	}

	/**
	 * Returns the number of j i r a issues where modifiedDate &gt; &#63; and projectId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @return the number of matching j i r a issues
	 */
	@Override
	public int countByMD_P(Date modifiedDate, long projectId) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_MD_P;

		Object[] finderArgs = new Object[] { modifiedDate, projectId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JIRAISSUE_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_MD_P_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_MD_P_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_MD_P_PROJECTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
				}

				qPos.add(projectId);

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

	private static final String _FINDER_COLUMN_MD_P_MODIFIEDDATE_1 = "jiraIssue.modifiedDate > NULL AND ";
	private static final String _FINDER_COLUMN_MD_P_MODIFIEDDATE_2 = "jiraIssue.modifiedDate > ? AND ";
	private static final String _FINDER_COLUMN_MD_P_PROJECTID_2 = "jiraIssue.projectId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_P_RJUI = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP_RJUI",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_RJUI =
		new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_RJUI",
			new String[] { Long.class.getName(), String.class.getName() },
			JIRAIssueModelImpl.PROJECTID_COLUMN_BITMASK |
			JIRAIssueModelImpl.REPORTERJIRAUSERID_COLUMN_BITMASK |
			JIRAIssueModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_P_RJUI = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_RJUI",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @return the matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByP_RJUI(long projectId,
		String reporterJiraUserId) {
		return findByP_RJUI(projectId, reporterJiraUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @return the range of matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByP_RJUI(long projectId,
		String reporterJiraUserId, int start, int end) {
		return findByP_RJUI(projectId, reporterJiraUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByP_RJUI(long projectId,
		String reporterJiraUserId, int start, int end,
		OrderByComparator<JIRAIssue> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_RJUI;
			finderArgs = new Object[] { projectId, reporterJiraUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_P_RJUI;
			finderArgs = new Object[] {
					projectId, reporterJiraUserId,
					
					start, end, orderByComparator
				};
		}

		List<JIRAIssue> list = (List<JIRAIssue>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (JIRAIssue jiraIssue : list) {
				if ((projectId != jiraIssue.getProjectId()) ||
						!Validator.equals(reporterJiraUserId,
							jiraIssue.getReporterJiraUserId())) {
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

			query.append(_SQL_SELECT_JIRAISSUE_WHERE);

			query.append(_FINDER_COLUMN_P_RJUI_PROJECTID_2);

			boolean bindReporterJiraUserId = false;

			if (reporterJiraUserId == null) {
				query.append(_FINDER_COLUMN_P_RJUI_REPORTERJIRAUSERID_1);
			}
			else if (reporterJiraUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_P_RJUI_REPORTERJIRAUSERID_3);
			}
			else {
				bindReporterJiraUserId = true;

				query.append(_FINDER_COLUMN_P_RJUI_REPORTERJIRAUSERID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (bindReporterJiraUserId) {
					qPos.add(reporterJiraUserId);
				}

				if (!pagination) {
					list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue
	 * @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue findByP_RJUI_First(long projectId,
		String reporterJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = fetchByP_RJUI_First(projectId,
				reporterJiraUserId, orderByComparator);

		if (jiraIssue != null) {
			return jiraIssue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("projectId=");
		msg.append(projectId);

		msg.append(", reporterJiraUserId=");
		msg.append(reporterJiraUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAIssueException(msg.toString());
	}

	/**
	 * Returns the first j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue fetchByP_RJUI_First(long projectId,
		String reporterJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator) {
		List<JIRAIssue> list = findByP_RJUI(projectId, reporterJiraUserId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue
	 * @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue findByP_RJUI_Last(long projectId,
		String reporterJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = fetchByP_RJUI_Last(projectId, reporterJiraUserId,
				orderByComparator);

		if (jiraIssue != null) {
			return jiraIssue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("projectId=");
		msg.append(projectId);

		msg.append(", reporterJiraUserId=");
		msg.append(reporterJiraUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAIssueException(msg.toString());
	}

	/**
	 * Returns the last j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue fetchByP_RJUI_Last(long projectId,
		String reporterJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator) {
		int count = countByP_RJUI(projectId, reporterJiraUserId);

		if (count == 0) {
			return null;
		}

		List<JIRAIssue> list = findByP_RJUI(projectId, reporterJiraUserId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the j i r a issues before and after the current j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * @param jiraIssueId the primary key of the current j i r a issue
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a issue
	 * @throws NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	 */
	@Override
	public JIRAIssue[] findByP_RJUI_PrevAndNext(long jiraIssueId,
		long projectId, String reporterJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		Session session = null;

		try {
			session = openSession();

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = getByP_RJUI_PrevAndNext(session, jiraIssue, projectId,
					reporterJiraUserId, orderByComparator, true);

			array[1] = jiraIssue;

			array[2] = getByP_RJUI_PrevAndNext(session, jiraIssue, projectId,
					reporterJiraUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JIRAIssue getByP_RJUI_PrevAndNext(Session session,
		JIRAIssue jiraIssue, long projectId, String reporterJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRAISSUE_WHERE);

		query.append(_FINDER_COLUMN_P_RJUI_PROJECTID_2);

		boolean bindReporterJiraUserId = false;

		if (reporterJiraUserId == null) {
			query.append(_FINDER_COLUMN_P_RJUI_REPORTERJIRAUSERID_1);
		}
		else if (reporterJiraUserId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_P_RJUI_REPORTERJIRAUSERID_3);
		}
		else {
			bindReporterJiraUserId = true;

			query.append(_FINDER_COLUMN_P_RJUI_REPORTERJIRAUSERID_2);
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
			query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(projectId);

		if (bindReporterJiraUserId) {
			qPos.add(reporterJiraUserId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(jiraIssue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAIssue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; from the database.
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 */
	@Override
	public void removeByP_RJUI(long projectId, String reporterJiraUserId) {
		for (JIRAIssue jiraIssue : findByP_RJUI(projectId, reporterJiraUserId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(jiraIssue);
		}
	}

	/**
	 * Returns the number of j i r a issues where projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @return the number of matching j i r a issues
	 */
	@Override
	public int countByP_RJUI(long projectId, String reporterJiraUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_P_RJUI;

		Object[] finderArgs = new Object[] { projectId, reporterJiraUserId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JIRAISSUE_WHERE);

			query.append(_FINDER_COLUMN_P_RJUI_PROJECTID_2);

			boolean bindReporterJiraUserId = false;

			if (reporterJiraUserId == null) {
				query.append(_FINDER_COLUMN_P_RJUI_REPORTERJIRAUSERID_1);
			}
			else if (reporterJiraUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_P_RJUI_REPORTERJIRAUSERID_3);
			}
			else {
				bindReporterJiraUserId = true;

				query.append(_FINDER_COLUMN_P_RJUI_REPORTERJIRAUSERID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (bindReporterJiraUserId) {
					qPos.add(reporterJiraUserId);
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

	private static final String _FINDER_COLUMN_P_RJUI_PROJECTID_2 = "jiraIssue.projectId = ? AND ";
	private static final String _FINDER_COLUMN_P_RJUI_REPORTERJIRAUSERID_1 = "jiraIssue.reporterJiraUserId IS NULL";
	private static final String _FINDER_COLUMN_P_RJUI_REPORTERJIRAUSERID_2 = "jiraIssue.reporterJiraUserId = ?";
	private static final String _FINDER_COLUMN_P_RJUI_REPORTERJIRAUSERID_3 = "(jiraIssue.reporterJiraUserId IS NULL OR jiraIssue.reporterJiraUserId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_P_AJUI = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP_AJUI",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_AJUI =
		new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_AJUI",
			new String[] { Long.class.getName(), String.class.getName() },
			JIRAIssueModelImpl.PROJECTID_COLUMN_BITMASK |
			JIRAIssueModelImpl.ASSIGNEEJIRAUSERID_COLUMN_BITMASK |
			JIRAIssueModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_P_AJUI = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_AJUI",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @return the matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByP_AJUI(long projectId,
		String assigneeJiraUserId) {
		return findByP_AJUI(projectId, assigneeJiraUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @return the range of matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByP_AJUI(long projectId,
		String assigneeJiraUserId, int start, int end) {
		return findByP_AJUI(projectId, assigneeJiraUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByP_AJUI(long projectId,
		String assigneeJiraUserId, int start, int end,
		OrderByComparator<JIRAIssue> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_AJUI;
			finderArgs = new Object[] { projectId, assigneeJiraUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_P_AJUI;
			finderArgs = new Object[] {
					projectId, assigneeJiraUserId,
					
					start, end, orderByComparator
				};
		}

		List<JIRAIssue> list = (List<JIRAIssue>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (JIRAIssue jiraIssue : list) {
				if ((projectId != jiraIssue.getProjectId()) ||
						!Validator.equals(assigneeJiraUserId,
							jiraIssue.getAssigneeJiraUserId())) {
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

			query.append(_SQL_SELECT_JIRAISSUE_WHERE);

			query.append(_FINDER_COLUMN_P_AJUI_PROJECTID_2);

			boolean bindAssigneeJiraUserId = false;

			if (assigneeJiraUserId == null) {
				query.append(_FINDER_COLUMN_P_AJUI_ASSIGNEEJIRAUSERID_1);
			}
			else if (assigneeJiraUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_P_AJUI_ASSIGNEEJIRAUSERID_3);
			}
			else {
				bindAssigneeJiraUserId = true;

				query.append(_FINDER_COLUMN_P_AJUI_ASSIGNEEJIRAUSERID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (bindAssigneeJiraUserId) {
					qPos.add(assigneeJiraUserId);
				}

				if (!pagination) {
					list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue
	 * @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue findByP_AJUI_First(long projectId,
		String assigneeJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = fetchByP_AJUI_First(projectId,
				assigneeJiraUserId, orderByComparator);

		if (jiraIssue != null) {
			return jiraIssue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("projectId=");
		msg.append(projectId);

		msg.append(", assigneeJiraUserId=");
		msg.append(assigneeJiraUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAIssueException(msg.toString());
	}

	/**
	 * Returns the first j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue fetchByP_AJUI_First(long projectId,
		String assigneeJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator) {
		List<JIRAIssue> list = findByP_AJUI(projectId, assigneeJiraUserId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue
	 * @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue findByP_AJUI_Last(long projectId,
		String assigneeJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = fetchByP_AJUI_Last(projectId, assigneeJiraUserId,
				orderByComparator);

		if (jiraIssue != null) {
			return jiraIssue;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("projectId=");
		msg.append(projectId);

		msg.append(", assigneeJiraUserId=");
		msg.append(assigneeJiraUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAIssueException(msg.toString());
	}

	/**
	 * Returns the last j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue fetchByP_AJUI_Last(long projectId,
		String assigneeJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator) {
		int count = countByP_AJUI(projectId, assigneeJiraUserId);

		if (count == 0) {
			return null;
		}

		List<JIRAIssue> list = findByP_AJUI(projectId, assigneeJiraUserId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the j i r a issues before and after the current j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * @param jiraIssueId the primary key of the current j i r a issue
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a issue
	 * @throws NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	 */
	@Override
	public JIRAIssue[] findByP_AJUI_PrevAndNext(long jiraIssueId,
		long projectId, String assigneeJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		Session session = null;

		try {
			session = openSession();

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = getByP_AJUI_PrevAndNext(session, jiraIssue, projectId,
					assigneeJiraUserId, orderByComparator, true);

			array[1] = jiraIssue;

			array[2] = getByP_AJUI_PrevAndNext(session, jiraIssue, projectId,
					assigneeJiraUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JIRAIssue getByP_AJUI_PrevAndNext(Session session,
		JIRAIssue jiraIssue, long projectId, String assigneeJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRAISSUE_WHERE);

		query.append(_FINDER_COLUMN_P_AJUI_PROJECTID_2);

		boolean bindAssigneeJiraUserId = false;

		if (assigneeJiraUserId == null) {
			query.append(_FINDER_COLUMN_P_AJUI_ASSIGNEEJIRAUSERID_1);
		}
		else if (assigneeJiraUserId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_P_AJUI_ASSIGNEEJIRAUSERID_3);
		}
		else {
			bindAssigneeJiraUserId = true;

			query.append(_FINDER_COLUMN_P_AJUI_ASSIGNEEJIRAUSERID_2);
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
			query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(projectId);

		if (bindAssigneeJiraUserId) {
			qPos.add(assigneeJiraUserId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(jiraIssue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAIssue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; from the database.
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 */
	@Override
	public void removeByP_AJUI(long projectId, String assigneeJiraUserId) {
		for (JIRAIssue jiraIssue : findByP_AJUI(projectId, assigneeJiraUserId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(jiraIssue);
		}
	}

	/**
	 * Returns the number of j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @return the number of matching j i r a issues
	 */
	@Override
	public int countByP_AJUI(long projectId, String assigneeJiraUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_P_AJUI;

		Object[] finderArgs = new Object[] { projectId, assigneeJiraUserId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_JIRAISSUE_WHERE);

			query.append(_FINDER_COLUMN_P_AJUI_PROJECTID_2);

			boolean bindAssigneeJiraUserId = false;

			if (assigneeJiraUserId == null) {
				query.append(_FINDER_COLUMN_P_AJUI_ASSIGNEEJIRAUSERID_1);
			}
			else if (assigneeJiraUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_P_AJUI_ASSIGNEEJIRAUSERID_3);
			}
			else {
				bindAssigneeJiraUserId = true;

				query.append(_FINDER_COLUMN_P_AJUI_ASSIGNEEJIRAUSERID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (bindAssigneeJiraUserId) {
					qPos.add(assigneeJiraUserId);
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

	private static final String _FINDER_COLUMN_P_AJUI_PROJECTID_2 = "jiraIssue.projectId = ? AND ";
	private static final String _FINDER_COLUMN_P_AJUI_ASSIGNEEJIRAUSERID_1 = "jiraIssue.assigneeJiraUserId IS NULL";
	private static final String _FINDER_COLUMN_P_AJUI_ASSIGNEEJIRAUSERID_2 = "jiraIssue.assigneeJiraUserId = ?";
	private static final String _FINDER_COLUMN_P_AJUI_ASSIGNEEJIRAUSERID_3 = "(jiraIssue.assigneeJiraUserId IS NULL OR jiraIssue.assigneeJiraUserId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MD_P_RJUI =
		new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByMD_P_RJUI",
			new String[] {
				Date.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_MD_P_RJUI =
		new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByMD_P_RJUI",
			new String[] {
				Date.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @return the matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByMD_P_RJUI(Date modifiedDate, long projectId,
		String reporterJiraUserId) {
		return findByMD_P_RJUI(modifiedDate, projectId, reporterJiraUserId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @return the range of matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByMD_P_RJUI(Date modifiedDate, long projectId,
		String reporterJiraUserId, int start, int end) {
		return findByMD_P_RJUI(modifiedDate, projectId, reporterJiraUserId,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByMD_P_RJUI(Date modifiedDate, long projectId,
		String reporterJiraUserId, int start, int end,
		OrderByComparator<JIRAIssue> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MD_P_RJUI;
		finderArgs = new Object[] {
				modifiedDate, projectId, reporterJiraUserId,
				
				start, end, orderByComparator
			};

		List<JIRAIssue> list = (List<JIRAIssue>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (JIRAIssue jiraIssue : list) {
				if ((modifiedDate.getTime() >= jiraIssue.getModifiedDate()
															.getTime()) ||
						(projectId != jiraIssue.getProjectId()) ||
						!Validator.equals(reporterJiraUserId,
							jiraIssue.getReporterJiraUserId())) {
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

			query.append(_SQL_SELECT_JIRAISSUE_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_MD_P_RJUI_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_MD_P_RJUI_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_MD_P_RJUI_PROJECTID_2);

			boolean bindReporterJiraUserId = false;

			if (reporterJiraUserId == null) {
				query.append(_FINDER_COLUMN_MD_P_RJUI_REPORTERJIRAUSERID_1);
			}
			else if (reporterJiraUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MD_P_RJUI_REPORTERJIRAUSERID_3);
			}
			else {
				bindReporterJiraUserId = true;

				query.append(_FINDER_COLUMN_MD_P_RJUI_REPORTERJIRAUSERID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
				}

				qPos.add(projectId);

				if (bindReporterJiraUserId) {
					qPos.add(reporterJiraUserId);
				}

				if (!pagination) {
					list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue
	 * @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue findByMD_P_RJUI_First(Date modifiedDate, long projectId,
		String reporterJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = fetchByMD_P_RJUI_First(modifiedDate, projectId,
				reporterJiraUserId, orderByComparator);

		if (jiraIssue != null) {
			return jiraIssue;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", projectId=");
		msg.append(projectId);

		msg.append(", reporterJiraUserId=");
		msg.append(reporterJiraUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAIssueException(msg.toString());
	}

	/**
	 * Returns the first j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue fetchByMD_P_RJUI_First(Date modifiedDate, long projectId,
		String reporterJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator) {
		List<JIRAIssue> list = findByMD_P_RJUI(modifiedDate, projectId,
				reporterJiraUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue
	 * @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue findByMD_P_RJUI_Last(Date modifiedDate, long projectId,
		String reporterJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = fetchByMD_P_RJUI_Last(modifiedDate, projectId,
				reporterJiraUserId, orderByComparator);

		if (jiraIssue != null) {
			return jiraIssue;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", projectId=");
		msg.append(projectId);

		msg.append(", reporterJiraUserId=");
		msg.append(reporterJiraUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAIssueException(msg.toString());
	}

	/**
	 * Returns the last j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue fetchByMD_P_RJUI_Last(Date modifiedDate, long projectId,
		String reporterJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator) {
		int count = countByMD_P_RJUI(modifiedDate, projectId, reporterJiraUserId);

		if (count == 0) {
			return null;
		}

		List<JIRAIssue> list = findByMD_P_RJUI(modifiedDate, projectId,
				reporterJiraUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the j i r a issues before and after the current j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * @param jiraIssueId the primary key of the current j i r a issue
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a issue
	 * @throws NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	 */
	@Override
	public JIRAIssue[] findByMD_P_RJUI_PrevAndNext(long jiraIssueId,
		Date modifiedDate, long projectId, String reporterJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		Session session = null;

		try {
			session = openSession();

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = getByMD_P_RJUI_PrevAndNext(session, jiraIssue,
					modifiedDate, projectId, reporterJiraUserId,
					orderByComparator, true);

			array[1] = jiraIssue;

			array[2] = getByMD_P_RJUI_PrevAndNext(session, jiraIssue,
					modifiedDate, projectId, reporterJiraUserId,
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

	protected JIRAIssue getByMD_P_RJUI_PrevAndNext(Session session,
		JIRAIssue jiraIssue, Date modifiedDate, long projectId,
		String reporterJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRAISSUE_WHERE);

		boolean bindModifiedDate = false;

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_MD_P_RJUI_MODIFIEDDATE_1);
		}
		else {
			bindModifiedDate = true;

			query.append(_FINDER_COLUMN_MD_P_RJUI_MODIFIEDDATE_2);
		}

		query.append(_FINDER_COLUMN_MD_P_RJUI_PROJECTID_2);

		boolean bindReporterJiraUserId = false;

		if (reporterJiraUserId == null) {
			query.append(_FINDER_COLUMN_MD_P_RJUI_REPORTERJIRAUSERID_1);
		}
		else if (reporterJiraUserId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_MD_P_RJUI_REPORTERJIRAUSERID_3);
		}
		else {
			bindReporterJiraUserId = true;

			query.append(_FINDER_COLUMN_MD_P_RJUI_REPORTERJIRAUSERID_2);
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
			query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindModifiedDate) {
			qPos.add(new Timestamp(modifiedDate.getTime()));
		}

		qPos.add(projectId);

		if (bindReporterJiraUserId) {
			qPos.add(reporterJiraUserId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(jiraIssue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAIssue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 */
	@Override
	public void removeByMD_P_RJUI(Date modifiedDate, long projectId,
		String reporterJiraUserId) {
		for (JIRAIssue jiraIssue : findByMD_P_RJUI(modifiedDate, projectId,
				reporterJiraUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(jiraIssue);
		}
	}

	/**
	 * Returns the number of j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and reporterJiraUserId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @return the number of matching j i r a issues
	 */
	@Override
	public int countByMD_P_RJUI(Date modifiedDate, long projectId,
		String reporterJiraUserId) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_MD_P_RJUI;

		Object[] finderArgs = new Object[] {
				modifiedDate, projectId, reporterJiraUserId
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_JIRAISSUE_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_MD_P_RJUI_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_MD_P_RJUI_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_MD_P_RJUI_PROJECTID_2);

			boolean bindReporterJiraUserId = false;

			if (reporterJiraUserId == null) {
				query.append(_FINDER_COLUMN_MD_P_RJUI_REPORTERJIRAUSERID_1);
			}
			else if (reporterJiraUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MD_P_RJUI_REPORTERJIRAUSERID_3);
			}
			else {
				bindReporterJiraUserId = true;

				query.append(_FINDER_COLUMN_MD_P_RJUI_REPORTERJIRAUSERID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
				}

				qPos.add(projectId);

				if (bindReporterJiraUserId) {
					qPos.add(reporterJiraUserId);
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

	private static final String _FINDER_COLUMN_MD_P_RJUI_MODIFIEDDATE_1 = "jiraIssue.modifiedDate > NULL AND ";
	private static final String _FINDER_COLUMN_MD_P_RJUI_MODIFIEDDATE_2 = "jiraIssue.modifiedDate > ? AND ";
	private static final String _FINDER_COLUMN_MD_P_RJUI_PROJECTID_2 = "jiraIssue.projectId = ? AND ";
	private static final String _FINDER_COLUMN_MD_P_RJUI_REPORTERJIRAUSERID_1 = "jiraIssue.reporterJiraUserId IS NULL";
	private static final String _FINDER_COLUMN_MD_P_RJUI_REPORTERJIRAUSERID_2 = "jiraIssue.reporterJiraUserId = ?";
	private static final String _FINDER_COLUMN_MD_P_RJUI_REPORTERJIRAUSERID_3 = "(jiraIssue.reporterJiraUserId IS NULL OR jiraIssue.reporterJiraUserId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MD_P_AJUI =
		new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByMD_P_AJUI",
			new String[] {
				Date.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_MD_P_AJUI =
		new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByMD_P_AJUI",
			new String[] {
				Date.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @return the matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByMD_P_AJUI(Date modifiedDate, long projectId,
		String assigneeJiraUserId) {
		return findByMD_P_AJUI(modifiedDate, projectId, assigneeJiraUserId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @return the range of matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByMD_P_AJUI(Date modifiedDate, long projectId,
		String assigneeJiraUserId, int start, int end) {
		return findByMD_P_AJUI(modifiedDate, projectId, assigneeJiraUserId,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByMD_P_AJUI(Date modifiedDate, long projectId,
		String assigneeJiraUserId, int start, int end,
		OrderByComparator<JIRAIssue> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MD_P_AJUI;
		finderArgs = new Object[] {
				modifiedDate, projectId, assigneeJiraUserId,
				
				start, end, orderByComparator
			};

		List<JIRAIssue> list = (List<JIRAIssue>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (JIRAIssue jiraIssue : list) {
				if ((modifiedDate.getTime() >= jiraIssue.getModifiedDate()
															.getTime()) ||
						(projectId != jiraIssue.getProjectId()) ||
						!Validator.equals(assigneeJiraUserId,
							jiraIssue.getAssigneeJiraUserId())) {
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

			query.append(_SQL_SELECT_JIRAISSUE_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_MD_P_AJUI_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_MD_P_AJUI_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_MD_P_AJUI_PROJECTID_2);

			boolean bindAssigneeJiraUserId = false;

			if (assigneeJiraUserId == null) {
				query.append(_FINDER_COLUMN_MD_P_AJUI_ASSIGNEEJIRAUSERID_1);
			}
			else if (assigneeJiraUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MD_P_AJUI_ASSIGNEEJIRAUSERID_3);
			}
			else {
				bindAssigneeJiraUserId = true;

				query.append(_FINDER_COLUMN_MD_P_AJUI_ASSIGNEEJIRAUSERID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
				}

				qPos.add(projectId);

				if (bindAssigneeJiraUserId) {
					qPos.add(assigneeJiraUserId);
				}

				if (!pagination) {
					list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue
	 * @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue findByMD_P_AJUI_First(Date modifiedDate, long projectId,
		String assigneeJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = fetchByMD_P_AJUI_First(modifiedDate, projectId,
				assigneeJiraUserId, orderByComparator);

		if (jiraIssue != null) {
			return jiraIssue;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", projectId=");
		msg.append(projectId);

		msg.append(", assigneeJiraUserId=");
		msg.append(assigneeJiraUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAIssueException(msg.toString());
	}

	/**
	 * Returns the first j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue fetchByMD_P_AJUI_First(Date modifiedDate, long projectId,
		String assigneeJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator) {
		List<JIRAIssue> list = findByMD_P_AJUI(modifiedDate, projectId,
				assigneeJiraUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue
	 * @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue findByMD_P_AJUI_Last(Date modifiedDate, long projectId,
		String assigneeJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = fetchByMD_P_AJUI_Last(modifiedDate, projectId,
				assigneeJiraUserId, orderByComparator);

		if (jiraIssue != null) {
			return jiraIssue;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", projectId=");
		msg.append(projectId);

		msg.append(", assigneeJiraUserId=");
		msg.append(assigneeJiraUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAIssueException(msg.toString());
	}

	/**
	 * Returns the last j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue fetchByMD_P_AJUI_Last(Date modifiedDate, long projectId,
		String assigneeJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator) {
		int count = countByMD_P_AJUI(modifiedDate, projectId, assigneeJiraUserId);

		if (count == 0) {
			return null;
		}

		List<JIRAIssue> list = findByMD_P_AJUI(modifiedDate, projectId,
				assigneeJiraUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the j i r a issues before and after the current j i r a issue in the ordered set where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * @param jiraIssueId the primary key of the current j i r a issue
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a issue
	 * @throws NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	 */
	@Override
	public JIRAIssue[] findByMD_P_AJUI_PrevAndNext(long jiraIssueId,
		Date modifiedDate, long projectId, String assigneeJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		Session session = null;

		try {
			session = openSession();

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = getByMD_P_AJUI_PrevAndNext(session, jiraIssue,
					modifiedDate, projectId, assigneeJiraUserId,
					orderByComparator, true);

			array[1] = jiraIssue;

			array[2] = getByMD_P_AJUI_PrevAndNext(session, jiraIssue,
					modifiedDate, projectId, assigneeJiraUserId,
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

	protected JIRAIssue getByMD_P_AJUI_PrevAndNext(Session session,
		JIRAIssue jiraIssue, Date modifiedDate, long projectId,
		String assigneeJiraUserId,
		OrderByComparator<JIRAIssue> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRAISSUE_WHERE);

		boolean bindModifiedDate = false;

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_MD_P_AJUI_MODIFIEDDATE_1);
		}
		else {
			bindModifiedDate = true;

			query.append(_FINDER_COLUMN_MD_P_AJUI_MODIFIEDDATE_2);
		}

		query.append(_FINDER_COLUMN_MD_P_AJUI_PROJECTID_2);

		boolean bindAssigneeJiraUserId = false;

		if (assigneeJiraUserId == null) {
			query.append(_FINDER_COLUMN_MD_P_AJUI_ASSIGNEEJIRAUSERID_1);
		}
		else if (assigneeJiraUserId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_MD_P_AJUI_ASSIGNEEJIRAUSERID_3);
		}
		else {
			bindAssigneeJiraUserId = true;

			query.append(_FINDER_COLUMN_MD_P_AJUI_ASSIGNEEJIRAUSERID_2);
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
			query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindModifiedDate) {
			qPos.add(new Timestamp(modifiedDate.getTime()));
		}

		qPos.add(projectId);

		if (bindAssigneeJiraUserId) {
			qPos.add(assigneeJiraUserId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(jiraIssue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAIssue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 */
	@Override
	public void removeByMD_P_AJUI(Date modifiedDate, long projectId,
		String assigneeJiraUserId) {
		for (JIRAIssue jiraIssue : findByMD_P_AJUI(modifiedDate, projectId,
				assigneeJiraUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(jiraIssue);
		}
	}

	/**
	 * Returns the number of j i r a issues where modifiedDate &gt; &#63; and projectId = &#63; and assigneeJiraUserId = &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @return the number of matching j i r a issues
	 */
	@Override
	public int countByMD_P_AJUI(Date modifiedDate, long projectId,
		String assigneeJiraUserId) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_MD_P_AJUI;

		Object[] finderArgs = new Object[] {
				modifiedDate, projectId, assigneeJiraUserId
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_JIRAISSUE_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_MD_P_AJUI_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_MD_P_AJUI_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_MD_P_AJUI_PROJECTID_2);

			boolean bindAssigneeJiraUserId = false;

			if (assigneeJiraUserId == null) {
				query.append(_FINDER_COLUMN_MD_P_AJUI_ASSIGNEEJIRAUSERID_1);
			}
			else if (assigneeJiraUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MD_P_AJUI_ASSIGNEEJIRAUSERID_3);
			}
			else {
				bindAssigneeJiraUserId = true;

				query.append(_FINDER_COLUMN_MD_P_AJUI_ASSIGNEEJIRAUSERID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
				}

				qPos.add(projectId);

				if (bindAssigneeJiraUserId) {
					qPos.add(assigneeJiraUserId);
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

	private static final String _FINDER_COLUMN_MD_P_AJUI_MODIFIEDDATE_1 = "jiraIssue.modifiedDate > NULL AND ";
	private static final String _FINDER_COLUMN_MD_P_AJUI_MODIFIEDDATE_2 = "jiraIssue.modifiedDate > ? AND ";
	private static final String _FINDER_COLUMN_MD_P_AJUI_PROJECTID_2 = "jiraIssue.projectId = ? AND ";
	private static final String _FINDER_COLUMN_MD_P_AJUI_ASSIGNEEJIRAUSERID_1 = "jiraIssue.assigneeJiraUserId IS NULL";
	private static final String _FINDER_COLUMN_MD_P_AJUI_ASSIGNEEJIRAUSERID_2 = "jiraIssue.assigneeJiraUserId = ?";
	private static final String _FINDER_COLUMN_MD_P_AJUI_ASSIGNEEJIRAUSERID_3 = "(jiraIssue.assigneeJiraUserId IS NULL OR jiraIssue.assigneeJiraUserId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_P_RJUI_S = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP_RJUI_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_RJUI_S =
		new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_RJUI_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			JIRAIssueModelImpl.PROJECTID_COLUMN_BITMASK |
			JIRAIssueModelImpl.REPORTERJIRAUSERID_COLUMN_BITMASK |
			JIRAIssueModelImpl.STATUS_COLUMN_BITMASK |
			JIRAIssueModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_P_RJUI_S = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_RJUI_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param status the status
	 * @return the matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByP_RJUI_S(long projectId,
		String reporterJiraUserId, String status) {
		return findByP_RJUI_S(projectId, reporterJiraUserId, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param status the status
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @return the range of matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByP_RJUI_S(long projectId,
		String reporterJiraUserId, String status, int start, int end) {
		return findByP_RJUI_S(projectId, reporterJiraUserId, status, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param status the status
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByP_RJUI_S(long projectId,
		String reporterJiraUserId, String status, int start, int end,
		OrderByComparator<JIRAIssue> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_RJUI_S;
			finderArgs = new Object[] { projectId, reporterJiraUserId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_P_RJUI_S;
			finderArgs = new Object[] {
					projectId, reporterJiraUserId, status,
					
					start, end, orderByComparator
				};
		}

		List<JIRAIssue> list = (List<JIRAIssue>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (JIRAIssue jiraIssue : list) {
				if ((projectId != jiraIssue.getProjectId()) ||
						!Validator.equals(reporterJiraUserId,
							jiraIssue.getReporterJiraUserId()) ||
						!Validator.equals(status, jiraIssue.getStatus())) {
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

			query.append(_SQL_SELECT_JIRAISSUE_WHERE);

			query.append(_FINDER_COLUMN_P_RJUI_S_PROJECTID_2);

			boolean bindReporterJiraUserId = false;

			if (reporterJiraUserId == null) {
				query.append(_FINDER_COLUMN_P_RJUI_S_REPORTERJIRAUSERID_1);
			}
			else if (reporterJiraUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_P_RJUI_S_REPORTERJIRAUSERID_3);
			}
			else {
				bindReporterJiraUserId = true;

				query.append(_FINDER_COLUMN_P_RJUI_S_REPORTERJIRAUSERID_2);
			}

			boolean bindStatus = false;

			if (status == null) {
				query.append(_FINDER_COLUMN_P_RJUI_S_STATUS_1);
			}
			else if (status.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_P_RJUI_S_STATUS_3);
			}
			else {
				bindStatus = true;

				query.append(_FINDER_COLUMN_P_RJUI_S_STATUS_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (bindReporterJiraUserId) {
					qPos.add(reporterJiraUserId);
				}

				if (bindStatus) {
					qPos.add(status);
				}

				if (!pagination) {
					list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue
	 * @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue findByP_RJUI_S_First(long projectId,
		String reporterJiraUserId, String status,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = fetchByP_RJUI_S_First(projectId,
				reporterJiraUserId, status, orderByComparator);

		if (jiraIssue != null) {
			return jiraIssue;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("projectId=");
		msg.append(projectId);

		msg.append(", reporterJiraUserId=");
		msg.append(reporterJiraUserId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAIssueException(msg.toString());
	}

	/**
	 * Returns the first j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue fetchByP_RJUI_S_First(long projectId,
		String reporterJiraUserId, String status,
		OrderByComparator<JIRAIssue> orderByComparator) {
		List<JIRAIssue> list = findByP_RJUI_S(projectId, reporterJiraUserId,
				status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue
	 * @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue findByP_RJUI_S_Last(long projectId,
		String reporterJiraUserId, String status,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = fetchByP_RJUI_S_Last(projectId,
				reporterJiraUserId, status, orderByComparator);

		if (jiraIssue != null) {
			return jiraIssue;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("projectId=");
		msg.append(projectId);

		msg.append(", reporterJiraUserId=");
		msg.append(reporterJiraUserId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAIssueException(msg.toString());
	}

	/**
	 * Returns the last j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue fetchByP_RJUI_S_Last(long projectId,
		String reporterJiraUserId, String status,
		OrderByComparator<JIRAIssue> orderByComparator) {
		int count = countByP_RJUI_S(projectId, reporterJiraUserId, status);

		if (count == 0) {
			return null;
		}

		List<JIRAIssue> list = findByP_RJUI_S(projectId, reporterJiraUserId,
				status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the j i r a issues before and after the current j i r a issue in the ordered set where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	 *
	 * @param jiraIssueId the primary key of the current j i r a issue
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a issue
	 * @throws NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	 */
	@Override
	public JIRAIssue[] findByP_RJUI_S_PrevAndNext(long jiraIssueId,
		long projectId, String reporterJiraUserId, String status,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		Session session = null;

		try {
			session = openSession();

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = getByP_RJUI_S_PrevAndNext(session, jiraIssue, projectId,
					reporterJiraUserId, status, orderByComparator, true);

			array[1] = jiraIssue;

			array[2] = getByP_RJUI_S_PrevAndNext(session, jiraIssue, projectId,
					reporterJiraUserId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JIRAIssue getByP_RJUI_S_PrevAndNext(Session session,
		JIRAIssue jiraIssue, long projectId, String reporterJiraUserId,
		String status, OrderByComparator<JIRAIssue> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRAISSUE_WHERE);

		query.append(_FINDER_COLUMN_P_RJUI_S_PROJECTID_2);

		boolean bindReporterJiraUserId = false;

		if (reporterJiraUserId == null) {
			query.append(_FINDER_COLUMN_P_RJUI_S_REPORTERJIRAUSERID_1);
		}
		else if (reporterJiraUserId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_P_RJUI_S_REPORTERJIRAUSERID_3);
		}
		else {
			bindReporterJiraUserId = true;

			query.append(_FINDER_COLUMN_P_RJUI_S_REPORTERJIRAUSERID_2);
		}

		boolean bindStatus = false;

		if (status == null) {
			query.append(_FINDER_COLUMN_P_RJUI_S_STATUS_1);
		}
		else if (status.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_P_RJUI_S_STATUS_3);
		}
		else {
			bindStatus = true;

			query.append(_FINDER_COLUMN_P_RJUI_S_STATUS_2);
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
			query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(projectId);

		if (bindReporterJiraUserId) {
			qPos.add(reporterJiraUserId);
		}

		if (bindStatus) {
			qPos.add(status);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(jiraIssue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAIssue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63; from the database.
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param status the status
	 */
	@Override
	public void removeByP_RJUI_S(long projectId, String reporterJiraUserId,
		String status) {
		for (JIRAIssue jiraIssue : findByP_RJUI_S(projectId,
				reporterJiraUserId, status, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(jiraIssue);
		}
	}

	/**
	 * Returns the number of j i r a issues where projectId = &#63; and reporterJiraUserId = &#63; and status = &#63;.
	 *
	 * @param projectId the project ID
	 * @param reporterJiraUserId the reporter jira user ID
	 * @param status the status
	 * @return the number of matching j i r a issues
	 */
	@Override
	public int countByP_RJUI_S(long projectId, String reporterJiraUserId,
		String status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_P_RJUI_S;

		Object[] finderArgs = new Object[] { projectId, reporterJiraUserId, status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_JIRAISSUE_WHERE);

			query.append(_FINDER_COLUMN_P_RJUI_S_PROJECTID_2);

			boolean bindReporterJiraUserId = false;

			if (reporterJiraUserId == null) {
				query.append(_FINDER_COLUMN_P_RJUI_S_REPORTERJIRAUSERID_1);
			}
			else if (reporterJiraUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_P_RJUI_S_REPORTERJIRAUSERID_3);
			}
			else {
				bindReporterJiraUserId = true;

				query.append(_FINDER_COLUMN_P_RJUI_S_REPORTERJIRAUSERID_2);
			}

			boolean bindStatus = false;

			if (status == null) {
				query.append(_FINDER_COLUMN_P_RJUI_S_STATUS_1);
			}
			else if (status.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_P_RJUI_S_STATUS_3);
			}
			else {
				bindStatus = true;

				query.append(_FINDER_COLUMN_P_RJUI_S_STATUS_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (bindReporterJiraUserId) {
					qPos.add(reporterJiraUserId);
				}

				if (bindStatus) {
					qPos.add(status);
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

	private static final String _FINDER_COLUMN_P_RJUI_S_PROJECTID_2 = "jiraIssue.projectId = ? AND ";
	private static final String _FINDER_COLUMN_P_RJUI_S_REPORTERJIRAUSERID_1 = "jiraIssue.reporterJiraUserId IS NULL AND ";
	private static final String _FINDER_COLUMN_P_RJUI_S_REPORTERJIRAUSERID_2 = "jiraIssue.reporterJiraUserId = ? AND ";
	private static final String _FINDER_COLUMN_P_RJUI_S_REPORTERJIRAUSERID_3 = "(jiraIssue.reporterJiraUserId IS NULL OR jiraIssue.reporterJiraUserId = '') AND ";
	private static final String _FINDER_COLUMN_P_RJUI_S_STATUS_1 = "jiraIssue.status IS NULL";
	private static final String _FINDER_COLUMN_P_RJUI_S_STATUS_2 = "jiraIssue.status = ?";
	private static final String _FINDER_COLUMN_P_RJUI_S_STATUS_3 = "(jiraIssue.status IS NULL OR jiraIssue.status = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_P_AJUI_S = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP_AJUI_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_AJUI_S =
		new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, JIRAIssueImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_AJUI_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			JIRAIssueModelImpl.PROJECTID_COLUMN_BITMASK |
			JIRAIssueModelImpl.ASSIGNEEJIRAUSERID_COLUMN_BITMASK |
			JIRAIssueModelImpl.STATUS_COLUMN_BITMASK |
			JIRAIssueModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_P_AJUI_S = new FinderPath(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_AJUI_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param status the status
	 * @return the matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByP_AJUI_S(long projectId,
		String assigneeJiraUserId, String status) {
		return findByP_AJUI_S(projectId, assigneeJiraUserId, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param status the status
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @return the range of matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByP_AJUI_S(long projectId,
		String assigneeJiraUserId, String status, int start, int end) {
		return findByP_AJUI_S(projectId, assigneeJiraUserId, status, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param status the status
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a issues
	 */
	@Override
	public List<JIRAIssue> findByP_AJUI_S(long projectId,
		String assigneeJiraUserId, String status, int start, int end,
		OrderByComparator<JIRAIssue> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_AJUI_S;
			finderArgs = new Object[] { projectId, assigneeJiraUserId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_P_AJUI_S;
			finderArgs = new Object[] {
					projectId, assigneeJiraUserId, status,
					
					start, end, orderByComparator
				};
		}

		List<JIRAIssue> list = (List<JIRAIssue>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (JIRAIssue jiraIssue : list) {
				if ((projectId != jiraIssue.getProjectId()) ||
						!Validator.equals(assigneeJiraUserId,
							jiraIssue.getAssigneeJiraUserId()) ||
						!Validator.equals(status, jiraIssue.getStatus())) {
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

			query.append(_SQL_SELECT_JIRAISSUE_WHERE);

			query.append(_FINDER_COLUMN_P_AJUI_S_PROJECTID_2);

			boolean bindAssigneeJiraUserId = false;

			if (assigneeJiraUserId == null) {
				query.append(_FINDER_COLUMN_P_AJUI_S_ASSIGNEEJIRAUSERID_1);
			}
			else if (assigneeJiraUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_P_AJUI_S_ASSIGNEEJIRAUSERID_3);
			}
			else {
				bindAssigneeJiraUserId = true;

				query.append(_FINDER_COLUMN_P_AJUI_S_ASSIGNEEJIRAUSERID_2);
			}

			boolean bindStatus = false;

			if (status == null) {
				query.append(_FINDER_COLUMN_P_AJUI_S_STATUS_1);
			}
			else if (status.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_P_AJUI_S_STATUS_3);
			}
			else {
				bindStatus = true;

				query.append(_FINDER_COLUMN_P_AJUI_S_STATUS_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (bindAssigneeJiraUserId) {
					qPos.add(assigneeJiraUserId);
				}

				if (bindStatus) {
					qPos.add(status);
				}

				if (!pagination) {
					list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue
	 * @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue findByP_AJUI_S_First(long projectId,
		String assigneeJiraUserId, String status,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = fetchByP_AJUI_S_First(projectId,
				assigneeJiraUserId, status, orderByComparator);

		if (jiraIssue != null) {
			return jiraIssue;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("projectId=");
		msg.append(projectId);

		msg.append(", assigneeJiraUserId=");
		msg.append(assigneeJiraUserId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAIssueException(msg.toString());
	}

	/**
	 * Returns the first j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue fetchByP_AJUI_S_First(long projectId,
		String assigneeJiraUserId, String status,
		OrderByComparator<JIRAIssue> orderByComparator) {
		List<JIRAIssue> list = findByP_AJUI_S(projectId, assigneeJiraUserId,
				status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue
	 * @throws NoSuchJIRAIssueException if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue findByP_AJUI_S_Last(long projectId,
		String assigneeJiraUserId, String status,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = fetchByP_AJUI_S_Last(projectId,
				assigneeJiraUserId, status, orderByComparator);

		if (jiraIssue != null) {
			return jiraIssue;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("projectId=");
		msg.append(projectId);

		msg.append(", assigneeJiraUserId=");
		msg.append(assigneeJiraUserId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAIssueException(msg.toString());
	}

	/**
	 * Returns the last j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a issue, or <code>null</code> if a matching j i r a issue could not be found
	 */
	@Override
	public JIRAIssue fetchByP_AJUI_S_Last(long projectId,
		String assigneeJiraUserId, String status,
		OrderByComparator<JIRAIssue> orderByComparator) {
		int count = countByP_AJUI_S(projectId, assigneeJiraUserId, status);

		if (count == 0) {
			return null;
		}

		List<JIRAIssue> list = findByP_AJUI_S(projectId, assigneeJiraUserId,
				status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the j i r a issues before and after the current j i r a issue in the ordered set where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	 *
	 * @param jiraIssueId the primary key of the current j i r a issue
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a issue
	 * @throws NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	 */
	@Override
	public JIRAIssue[] findByP_AJUI_S_PrevAndNext(long jiraIssueId,
		long projectId, String assigneeJiraUserId, String status,
		OrderByComparator<JIRAIssue> orderByComparator)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = findByPrimaryKey(jiraIssueId);

		Session session = null;

		try {
			session = openSession();

			JIRAIssue[] array = new JIRAIssueImpl[3];

			array[0] = getByP_AJUI_S_PrevAndNext(session, jiraIssue, projectId,
					assigneeJiraUserId, status, orderByComparator, true);

			array[1] = jiraIssue;

			array[2] = getByP_AJUI_S_PrevAndNext(session, jiraIssue, projectId,
					assigneeJiraUserId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JIRAIssue getByP_AJUI_S_PrevAndNext(Session session,
		JIRAIssue jiraIssue, long projectId, String assigneeJiraUserId,
		String status, OrderByComparator<JIRAIssue> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRAISSUE_WHERE);

		query.append(_FINDER_COLUMN_P_AJUI_S_PROJECTID_2);

		boolean bindAssigneeJiraUserId = false;

		if (assigneeJiraUserId == null) {
			query.append(_FINDER_COLUMN_P_AJUI_S_ASSIGNEEJIRAUSERID_1);
		}
		else if (assigneeJiraUserId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_P_AJUI_S_ASSIGNEEJIRAUSERID_3);
		}
		else {
			bindAssigneeJiraUserId = true;

			query.append(_FINDER_COLUMN_P_AJUI_S_ASSIGNEEJIRAUSERID_2);
		}

		boolean bindStatus = false;

		if (status == null) {
			query.append(_FINDER_COLUMN_P_AJUI_S_STATUS_1);
		}
		else if (status.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_P_AJUI_S_STATUS_3);
		}
		else {
			bindStatus = true;

			query.append(_FINDER_COLUMN_P_AJUI_S_STATUS_2);
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
			query.append(JIRAIssueModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(projectId);

		if (bindAssigneeJiraUserId) {
			qPos.add(assigneeJiraUserId);
		}

		if (bindStatus) {
			qPos.add(status);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(jiraIssue);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAIssue> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63; from the database.
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param status the status
	 */
	@Override
	public void removeByP_AJUI_S(long projectId, String assigneeJiraUserId,
		String status) {
		for (JIRAIssue jiraIssue : findByP_AJUI_S(projectId,
				assigneeJiraUserId, status, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(jiraIssue);
		}
	}

	/**
	 * Returns the number of j i r a issues where projectId = &#63; and assigneeJiraUserId = &#63; and status = &#63;.
	 *
	 * @param projectId the project ID
	 * @param assigneeJiraUserId the assignee jira user ID
	 * @param status the status
	 * @return the number of matching j i r a issues
	 */
	@Override
	public int countByP_AJUI_S(long projectId, String assigneeJiraUserId,
		String status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_P_AJUI_S;

		Object[] finderArgs = new Object[] { projectId, assigneeJiraUserId, status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_JIRAISSUE_WHERE);

			query.append(_FINDER_COLUMN_P_AJUI_S_PROJECTID_2);

			boolean bindAssigneeJiraUserId = false;

			if (assigneeJiraUserId == null) {
				query.append(_FINDER_COLUMN_P_AJUI_S_ASSIGNEEJIRAUSERID_1);
			}
			else if (assigneeJiraUserId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_P_AJUI_S_ASSIGNEEJIRAUSERID_3);
			}
			else {
				bindAssigneeJiraUserId = true;

				query.append(_FINDER_COLUMN_P_AJUI_S_ASSIGNEEJIRAUSERID_2);
			}

			boolean bindStatus = false;

			if (status == null) {
				query.append(_FINDER_COLUMN_P_AJUI_S_STATUS_1);
			}
			else if (status.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_P_AJUI_S_STATUS_3);
			}
			else {
				bindStatus = true;

				query.append(_FINDER_COLUMN_P_AJUI_S_STATUS_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (bindAssigneeJiraUserId) {
					qPos.add(assigneeJiraUserId);
				}

				if (bindStatus) {
					qPos.add(status);
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

	private static final String _FINDER_COLUMN_P_AJUI_S_PROJECTID_2 = "jiraIssue.projectId = ? AND ";
	private static final String _FINDER_COLUMN_P_AJUI_S_ASSIGNEEJIRAUSERID_1 = "jiraIssue.assigneeJiraUserId IS NULL AND ";
	private static final String _FINDER_COLUMN_P_AJUI_S_ASSIGNEEJIRAUSERID_2 = "jiraIssue.assigneeJiraUserId = ? AND ";
	private static final String _FINDER_COLUMN_P_AJUI_S_ASSIGNEEJIRAUSERID_3 = "(jiraIssue.assigneeJiraUserId IS NULL OR jiraIssue.assigneeJiraUserId = '') AND ";
	private static final String _FINDER_COLUMN_P_AJUI_S_STATUS_1 = "jiraIssue.status IS NULL";
	private static final String _FINDER_COLUMN_P_AJUI_S_STATUS_2 = "jiraIssue.status = ?";
	private static final String _FINDER_COLUMN_P_AJUI_S_STATUS_3 = "(jiraIssue.status IS NULL OR jiraIssue.status = '')";

	public JIRAIssuePersistenceImpl() {
		setModelClass(JIRAIssue.class);
	}

	/**
	 * Caches the j i r a issue in the entity cache if it is enabled.
	 *
	 * @param jiraIssue the j i r a issue
	 */
	@Override
	public void cacheResult(JIRAIssue jiraIssue) {
		EntityCacheUtil.putResult(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueImpl.class, jiraIssue.getPrimaryKey(), jiraIssue);

		jiraIssue.resetOriginalValues();
	}

	/**
	 * Caches the j i r a issues in the entity cache if it is enabled.
	 *
	 * @param jiraIssues the j i r a issues
	 */
	@Override
	public void cacheResult(List<JIRAIssue> jiraIssues) {
		for (JIRAIssue jiraIssue : jiraIssues) {
			if (EntityCacheUtil.getResult(
						JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
						JIRAIssueImpl.class, jiraIssue.getPrimaryKey()) == null) {
				cacheResult(jiraIssue);
			}
			else {
				jiraIssue.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all j i r a issues.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(JIRAIssueImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the j i r a issue.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(JIRAIssue jiraIssue) {
		EntityCacheUtil.removeResult(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueImpl.class, jiraIssue.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<JIRAIssue> jiraIssues) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (JIRAIssue jiraIssue : jiraIssues) {
			EntityCacheUtil.removeResult(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
				JIRAIssueImpl.class, jiraIssue.getPrimaryKey());
		}
	}

	/**
	 * Creates a new j i r a issue with the primary key. Does not add the j i r a issue to the database.
	 *
	 * @param jiraIssueId the primary key for the new j i r a issue
	 * @return the new j i r a issue
	 */
	@Override
	public JIRAIssue create(long jiraIssueId) {
		JIRAIssue jiraIssue = new JIRAIssueImpl();

		jiraIssue.setNew(true);
		jiraIssue.setPrimaryKey(jiraIssueId);

		return jiraIssue;
	}

	/**
	 * Removes the j i r a issue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraIssueId the primary key of the j i r a issue
	 * @return the j i r a issue that was removed
	 * @throws NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	 */
	@Override
	public JIRAIssue remove(long jiraIssueId) throws NoSuchJIRAIssueException {
		return remove((Serializable)jiraIssueId);
	}

	/**
	 * Removes the j i r a issue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the j i r a issue
	 * @return the j i r a issue that was removed
	 * @throws NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	 */
	@Override
	public JIRAIssue remove(Serializable primaryKey)
		throws NoSuchJIRAIssueException {
		Session session = null;

		try {
			session = openSession();

			JIRAIssue jiraIssue = (JIRAIssue)session.get(JIRAIssueImpl.class,
					primaryKey);

			if (jiraIssue == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchJIRAIssueException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(jiraIssue);
		}
		catch (NoSuchJIRAIssueException nsee) {
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
	protected JIRAIssue removeImpl(JIRAIssue jiraIssue) {
		jiraIssue = toUnwrappedModel(jiraIssue);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(jiraIssue)) {
				jiraIssue = (JIRAIssue)session.get(JIRAIssueImpl.class,
						jiraIssue.getPrimaryKeyObj());
			}

			if (jiraIssue != null) {
				session.delete(jiraIssue);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (jiraIssue != null) {
			clearCache(jiraIssue);
		}

		return jiraIssue;
	}

	@Override
	public JIRAIssue updateImpl(JIRAIssue jiraIssue) {
		jiraIssue = toUnwrappedModel(jiraIssue);

		boolean isNew = jiraIssue.isNew();

		JIRAIssueModelImpl jiraIssueModelImpl = (JIRAIssueModelImpl)jiraIssue;

		Session session = null;

		try {
			session = openSession();

			if (jiraIssue.isNew()) {
				session.save(jiraIssue);

				jiraIssue.setNew(false);
			}
			else {
				session.merge(jiraIssue);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !JIRAIssueModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((jiraIssueModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jiraIssueModelImpl.getOriginalProjectId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROJECTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECTID,
					args);

				args = new Object[] { jiraIssueModelImpl.getProjectId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROJECTID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECTID,
					args);
			}

			if ((jiraIssueModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REPORTERJIRAUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jiraIssueModelImpl.getOriginalReporterJiraUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REPORTERJIRAUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REPORTERJIRAUSERID,
					args);

				args = new Object[] { jiraIssueModelImpl.getReporterJiraUserId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REPORTERJIRAUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REPORTERJIRAUSERID,
					args);
			}

			if ((jiraIssueModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSIGNEEJIRAUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jiraIssueModelImpl.getOriginalAssigneeJiraUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ASSIGNEEJIRAUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSIGNEEJIRAUSERID,
					args);

				args = new Object[] { jiraIssueModelImpl.getAssigneeJiraUserId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ASSIGNEEJIRAUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSIGNEEJIRAUSERID,
					args);
			}

			if ((jiraIssueModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_RJUI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jiraIssueModelImpl.getOriginalProjectId(),
						jiraIssueModelImpl.getOriginalReporterJiraUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_P_RJUI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_RJUI,
					args);

				args = new Object[] {
						jiraIssueModelImpl.getProjectId(),
						jiraIssueModelImpl.getReporterJiraUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_P_RJUI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_RJUI,
					args);
			}

			if ((jiraIssueModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_AJUI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jiraIssueModelImpl.getOriginalProjectId(),
						jiraIssueModelImpl.getOriginalAssigneeJiraUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_P_AJUI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_AJUI,
					args);

				args = new Object[] {
						jiraIssueModelImpl.getProjectId(),
						jiraIssueModelImpl.getAssigneeJiraUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_P_AJUI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_AJUI,
					args);
			}

			if ((jiraIssueModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_RJUI_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jiraIssueModelImpl.getOriginalProjectId(),
						jiraIssueModelImpl.getOriginalReporterJiraUserId(),
						jiraIssueModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_P_RJUI_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_RJUI_S,
					args);

				args = new Object[] {
						jiraIssueModelImpl.getProjectId(),
						jiraIssueModelImpl.getReporterJiraUserId(),
						jiraIssueModelImpl.getStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_P_RJUI_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_RJUI_S,
					args);
			}

			if ((jiraIssueModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_AJUI_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jiraIssueModelImpl.getOriginalProjectId(),
						jiraIssueModelImpl.getOriginalAssigneeJiraUserId(),
						jiraIssueModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_P_AJUI_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_AJUI_S,
					args);

				args = new Object[] {
						jiraIssueModelImpl.getProjectId(),
						jiraIssueModelImpl.getAssigneeJiraUserId(),
						jiraIssueModelImpl.getStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_P_AJUI_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_AJUI_S,
					args);
			}
		}

		EntityCacheUtil.putResult(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
			JIRAIssueImpl.class, jiraIssue.getPrimaryKey(), jiraIssue, false);

		jiraIssue.resetOriginalValues();

		return jiraIssue;
	}

	protected JIRAIssue toUnwrappedModel(JIRAIssue jiraIssue) {
		if (jiraIssue instanceof JIRAIssueImpl) {
			return jiraIssue;
		}

		JIRAIssueImpl jiraIssueImpl = new JIRAIssueImpl();

		jiraIssueImpl.setNew(jiraIssue.isNew());
		jiraIssueImpl.setPrimaryKey(jiraIssue.getPrimaryKey());

		jiraIssueImpl.setJiraIssueId(jiraIssue.getJiraIssueId());
		jiraIssueImpl.setCreateDate(jiraIssue.getCreateDate());
		jiraIssueImpl.setModifiedDate(jiraIssue.getModifiedDate());
		jiraIssueImpl.setProjectId(jiraIssue.getProjectId());
		jiraIssueImpl.setIssueNumber(jiraIssue.getIssueNumber());
		jiraIssueImpl.setSummary(jiraIssue.getSummary());
		jiraIssueImpl.setDescription(jiraIssue.getDescription());
		jiraIssueImpl.setReporterJiraUserId(jiraIssue.getReporterJiraUserId());
		jiraIssueImpl.setAssigneeJiraUserId(jiraIssue.getAssigneeJiraUserId());
		jiraIssueImpl.setResolution(jiraIssue.getResolution());
		jiraIssueImpl.setStatus(jiraIssue.getStatus());

		return jiraIssueImpl;
	}

	/**
	 * Returns the j i r a issue with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the j i r a issue
	 * @return the j i r a issue
	 * @throws NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	 */
	@Override
	public JIRAIssue findByPrimaryKey(Serializable primaryKey)
		throws NoSuchJIRAIssueException {
		JIRAIssue jiraIssue = fetchByPrimaryKey(primaryKey);

		if (jiraIssue == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchJIRAIssueException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return jiraIssue;
	}

	/**
	 * Returns the j i r a issue with the primary key or throws a {@link NoSuchJIRAIssueException} if it could not be found.
	 *
	 * @param jiraIssueId the primary key of the j i r a issue
	 * @return the j i r a issue
	 * @throws NoSuchJIRAIssueException if a j i r a issue with the primary key could not be found
	 */
	@Override
	public JIRAIssue findByPrimaryKey(long jiraIssueId)
		throws NoSuchJIRAIssueException {
		return findByPrimaryKey((Serializable)jiraIssueId);
	}

	/**
	 * Returns the j i r a issue with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the j i r a issue
	 * @return the j i r a issue, or <code>null</code> if a j i r a issue with the primary key could not be found
	 */
	@Override
	public JIRAIssue fetchByPrimaryKey(Serializable primaryKey) {
		JIRAIssue jiraIssue = (JIRAIssue)EntityCacheUtil.getResult(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
				JIRAIssueImpl.class, primaryKey);

		if (jiraIssue == _nullJIRAIssue) {
			return null;
		}

		if (jiraIssue == null) {
			Session session = null;

			try {
				session = openSession();

				jiraIssue = (JIRAIssue)session.get(JIRAIssueImpl.class,
						primaryKey);

				if (jiraIssue != null) {
					cacheResult(jiraIssue);
				}
				else {
					EntityCacheUtil.putResult(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
						JIRAIssueImpl.class, primaryKey, _nullJIRAIssue);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
					JIRAIssueImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return jiraIssue;
	}

	/**
	 * Returns the j i r a issue with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param jiraIssueId the primary key of the j i r a issue
	 * @return the j i r a issue, or <code>null</code> if a j i r a issue with the primary key could not be found
	 */
	@Override
	public JIRAIssue fetchByPrimaryKey(long jiraIssueId) {
		return fetchByPrimaryKey((Serializable)jiraIssueId);
	}

	@Override
	public Map<Serializable, JIRAIssue> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, JIRAIssue> map = new HashMap<Serializable, JIRAIssue>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			JIRAIssue jiraIssue = fetchByPrimaryKey(primaryKey);

			if (jiraIssue != null) {
				map.put(primaryKey, jiraIssue);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			JIRAIssue jiraIssue = (JIRAIssue)EntityCacheUtil.getResult(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
					JIRAIssueImpl.class, primaryKey);

			if (jiraIssue == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, jiraIssue);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_JIRAISSUE_WHERE_PKS_IN);

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

			for (JIRAIssue jiraIssue : (List<JIRAIssue>)q.list()) {
				map.put(jiraIssue.getPrimaryKeyObj(), jiraIssue);

				cacheResult(jiraIssue);

				uncachedPrimaryKeys.remove(jiraIssue.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(JIRAIssueModelImpl.ENTITY_CACHE_ENABLED,
					JIRAIssueImpl.class, primaryKey, _nullJIRAIssue);
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
	 * Returns all the j i r a issues.
	 *
	 * @return the j i r a issues
	 */
	@Override
	public List<JIRAIssue> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a issues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @return the range of j i r a issues
	 */
	@Override
	public List<JIRAIssue> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a issues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAIssueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of j i r a issues
	 * @param end the upper bound of the range of j i r a issues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of j i r a issues
	 */
	@Override
	public List<JIRAIssue> findAll(int start, int end,
		OrderByComparator<JIRAIssue> orderByComparator) {
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

		List<JIRAIssue> list = (List<JIRAIssue>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_JIRAISSUE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_JIRAISSUE;

				if (pagination) {
					sql = sql.concat(JIRAIssueModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JIRAIssue>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the j i r a issues from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (JIRAIssue jiraIssue : findAll()) {
			remove(jiraIssue);
		}
	}

	/**
	 * Returns the number of j i r a issues.
	 *
	 * @return the number of j i r a issues
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_JIRAISSUE);

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
	 * Initializes the j i r a issue persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(JIRAIssueImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_JIRAISSUE = "SELECT jiraIssue FROM JIRAIssue jiraIssue";
	private static final String _SQL_SELECT_JIRAISSUE_WHERE_PKS_IN = "SELECT jiraIssue FROM JIRAIssue jiraIssue WHERE id IN (";
	private static final String _SQL_SELECT_JIRAISSUE_WHERE = "SELECT jiraIssue FROM JIRAIssue jiraIssue WHERE ";
	private static final String _SQL_COUNT_JIRAISSUE = "SELECT COUNT(jiraIssue) FROM JIRAIssue jiraIssue";
	private static final String _SQL_COUNT_JIRAISSUE_WHERE = "SELECT COUNT(jiraIssue) FROM JIRAIssue jiraIssue WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "jiraIssue.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No JIRAIssue exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No JIRAIssue exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(JIRAIssuePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"jiraIssueId", "createDate", "modifiedDate", "projectId",
				"issueNumber", "reporterJiraUserId", "assigneeJiraUserId",
				"status"
			});
	private static final JIRAIssue _nullJIRAIssue = new JIRAIssueImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<JIRAIssue> toCacheModel() {
				return _nullJIRAIssueCacheModel;
			}
		};

	private static final CacheModel<JIRAIssue> _nullJIRAIssueCacheModel = new CacheModel<JIRAIssue>() {
			@Override
			public JIRAIssue toEntityModel() {
				return _nullJIRAIssue;
			}
		};
}