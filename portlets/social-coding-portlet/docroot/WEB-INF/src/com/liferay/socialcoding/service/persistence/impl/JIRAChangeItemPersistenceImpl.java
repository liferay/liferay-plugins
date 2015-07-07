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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.socialcoding.NoSuchJIRAChangeItemException;
import com.liferay.socialcoding.model.JIRAChangeItem;
import com.liferay.socialcoding.model.impl.JIRAChangeItemImpl;
import com.liferay.socialcoding.model.impl.JIRAChangeItemModelImpl;
import com.liferay.socialcoding.service.persistence.JIRAChangeItemPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the j i r a change item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAChangeItemPersistence
 * @see com.liferay.socialcoding.service.persistence.JIRAChangeItemUtil
 * @generated
 */
@ProviderType
public class JIRAChangeItemPersistenceImpl extends BasePersistenceImpl<JIRAChangeItem>
	implements JIRAChangeItemPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link JIRAChangeItemUtil} to access the j i r a change item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = JIRAChangeItemImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemModelImpl.FINDER_CACHE_ENABLED,
			JIRAChangeItemImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemModelImpl.FINDER_CACHE_ENABLED,
			JIRAChangeItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_JIRACHANGEGROUPID =
		new FinderPath(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemModelImpl.FINDER_CACHE_ENABLED,
			JIRAChangeItemImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByJiraChangeGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JIRACHANGEGROUPID =
		new FinderPath(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemModelImpl.FINDER_CACHE_ENABLED,
			JIRAChangeItemImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByJiraChangeGroupId", new String[] { Long.class.getName() },
			JIRAChangeItemModelImpl.JIRACHANGEGROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_JIRACHANGEGROUPID = new FinderPath(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByJiraChangeGroupId", new String[] { Long.class.getName() });

	/**
	 * Returns all the j i r a change items where jiraChangeGroupId = &#63;.
	 *
	 * @param jiraChangeGroupId the jira change group ID
	 * @return the matching j i r a change items
	 */
	@Override
	public List<JIRAChangeItem> findByJiraChangeGroupId(long jiraChangeGroupId) {
		return findByJiraChangeGroupId(jiraChangeGroupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a change items where jiraChangeGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAChangeItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param jiraChangeGroupId the jira change group ID
	 * @param start the lower bound of the range of j i r a change items
	 * @param end the upper bound of the range of j i r a change items (not inclusive)
	 * @return the range of matching j i r a change items
	 */
	@Override
	public List<JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId, int start, int end) {
		return findByJiraChangeGroupId(jiraChangeGroupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a change items where jiraChangeGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAChangeItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param jiraChangeGroupId the jira change group ID
	 * @param start the lower bound of the range of j i r a change items
	 * @param end the upper bound of the range of j i r a change items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching j i r a change items
	 */
	@Override
	public List<JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId, int start, int end,
		OrderByComparator<JIRAChangeItem> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JIRACHANGEGROUPID;
			finderArgs = new Object[] { jiraChangeGroupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_JIRACHANGEGROUPID;
			finderArgs = new Object[] {
					jiraChangeGroupId,
					
					start, end, orderByComparator
				};
		}

		List<JIRAChangeItem> list = (List<JIRAChangeItem>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (JIRAChangeItem jiraChangeItem : list) {
				if ((jiraChangeGroupId != jiraChangeItem.getJiraChangeGroupId())) {
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

			query.append(_SQL_SELECT_JIRACHANGEITEM_WHERE);

			query.append(_FINDER_COLUMN_JIRACHANGEGROUPID_JIRACHANGEGROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(JIRAChangeItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(jiraChangeGroupId);

				if (!pagination) {
					list = (List<JIRAChangeItem>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JIRAChangeItem>)QueryUtil.list(q,
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
	 * Returns the first j i r a change item in the ordered set where jiraChangeGroupId = &#63;.
	 *
	 * @param jiraChangeGroupId the jira change group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a change item
	 * @throws NoSuchJIRAChangeItemException if a matching j i r a change item could not be found
	 */
	@Override
	public JIRAChangeItem findByJiraChangeGroupId_First(
		long jiraChangeGroupId,
		OrderByComparator<JIRAChangeItem> orderByComparator)
		throws NoSuchJIRAChangeItemException {
		JIRAChangeItem jiraChangeItem = fetchByJiraChangeGroupId_First(jiraChangeGroupId,
				orderByComparator);

		if (jiraChangeItem != null) {
			return jiraChangeItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("jiraChangeGroupId=");
		msg.append(jiraChangeGroupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAChangeItemException(msg.toString());
	}

	/**
	 * Returns the first j i r a change item in the ordered set where jiraChangeGroupId = &#63;.
	 *
	 * @param jiraChangeGroupId the jira change group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching j i r a change item, or <code>null</code> if a matching j i r a change item could not be found
	 */
	@Override
	public JIRAChangeItem fetchByJiraChangeGroupId_First(
		long jiraChangeGroupId,
		OrderByComparator<JIRAChangeItem> orderByComparator) {
		List<JIRAChangeItem> list = findByJiraChangeGroupId(jiraChangeGroupId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last j i r a change item in the ordered set where jiraChangeGroupId = &#63;.
	 *
	 * @param jiraChangeGroupId the jira change group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a change item
	 * @throws NoSuchJIRAChangeItemException if a matching j i r a change item could not be found
	 */
	@Override
	public JIRAChangeItem findByJiraChangeGroupId_Last(long jiraChangeGroupId,
		OrderByComparator<JIRAChangeItem> orderByComparator)
		throws NoSuchJIRAChangeItemException {
		JIRAChangeItem jiraChangeItem = fetchByJiraChangeGroupId_Last(jiraChangeGroupId,
				orderByComparator);

		if (jiraChangeItem != null) {
			return jiraChangeItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("jiraChangeGroupId=");
		msg.append(jiraChangeGroupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchJIRAChangeItemException(msg.toString());
	}

	/**
	 * Returns the last j i r a change item in the ordered set where jiraChangeGroupId = &#63;.
	 *
	 * @param jiraChangeGroupId the jira change group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching j i r a change item, or <code>null</code> if a matching j i r a change item could not be found
	 */
	@Override
	public JIRAChangeItem fetchByJiraChangeGroupId_Last(
		long jiraChangeGroupId,
		OrderByComparator<JIRAChangeItem> orderByComparator) {
		int count = countByJiraChangeGroupId(jiraChangeGroupId);

		if (count == 0) {
			return null;
		}

		List<JIRAChangeItem> list = findByJiraChangeGroupId(jiraChangeGroupId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the j i r a change items before and after the current j i r a change item in the ordered set where jiraChangeGroupId = &#63;.
	 *
	 * @param jiraChangeItemId the primary key of the current j i r a change item
	 * @param jiraChangeGroupId the jira change group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next j i r a change item
	 * @throws NoSuchJIRAChangeItemException if a j i r a change item with the primary key could not be found
	 */
	@Override
	public JIRAChangeItem[] findByJiraChangeGroupId_PrevAndNext(
		long jiraChangeItemId, long jiraChangeGroupId,
		OrderByComparator<JIRAChangeItem> orderByComparator)
		throws NoSuchJIRAChangeItemException {
		JIRAChangeItem jiraChangeItem = findByPrimaryKey(jiraChangeItemId);

		Session session = null;

		try {
			session = openSession();

			JIRAChangeItem[] array = new JIRAChangeItemImpl[3];

			array[0] = getByJiraChangeGroupId_PrevAndNext(session,
					jiraChangeItem, jiraChangeGroupId, orderByComparator, true);

			array[1] = jiraChangeItem;

			array[2] = getByJiraChangeGroupId_PrevAndNext(session,
					jiraChangeItem, jiraChangeGroupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected JIRAChangeItem getByJiraChangeGroupId_PrevAndNext(
		Session session, JIRAChangeItem jiraChangeItem, long jiraChangeGroupId,
		OrderByComparator<JIRAChangeItem> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_JIRACHANGEITEM_WHERE);

		query.append(_FINDER_COLUMN_JIRACHANGEGROUPID_JIRACHANGEGROUPID_2);

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
			query.append(JIRAChangeItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(jiraChangeGroupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(jiraChangeItem);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<JIRAChangeItem> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the j i r a change items where jiraChangeGroupId = &#63; from the database.
	 *
	 * @param jiraChangeGroupId the jira change group ID
	 */
	@Override
	public void removeByJiraChangeGroupId(long jiraChangeGroupId) {
		for (JIRAChangeItem jiraChangeItem : findByJiraChangeGroupId(
				jiraChangeGroupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(jiraChangeItem);
		}
	}

	/**
	 * Returns the number of j i r a change items where jiraChangeGroupId = &#63;.
	 *
	 * @param jiraChangeGroupId the jira change group ID
	 * @return the number of matching j i r a change items
	 */
	@Override
	public int countByJiraChangeGroupId(long jiraChangeGroupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_JIRACHANGEGROUPID;

		Object[] finderArgs = new Object[] { jiraChangeGroupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_JIRACHANGEITEM_WHERE);

			query.append(_FINDER_COLUMN_JIRACHANGEGROUPID_JIRACHANGEGROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(jiraChangeGroupId);

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

	private static final String _FINDER_COLUMN_JIRACHANGEGROUPID_JIRACHANGEGROUPID_2 =
		"jiraChangeItem.jiraChangeGroupId = ?";

	public JIRAChangeItemPersistenceImpl() {
		setModelClass(JIRAChangeItem.class);
	}

	/**
	 * Caches the j i r a change item in the entity cache if it is enabled.
	 *
	 * @param jiraChangeItem the j i r a change item
	 */
	@Override
	public void cacheResult(JIRAChangeItem jiraChangeItem) {
		EntityCacheUtil.putResult(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemImpl.class, jiraChangeItem.getPrimaryKey(),
			jiraChangeItem);

		jiraChangeItem.resetOriginalValues();
	}

	/**
	 * Caches the j i r a change items in the entity cache if it is enabled.
	 *
	 * @param jiraChangeItems the j i r a change items
	 */
	@Override
	public void cacheResult(List<JIRAChangeItem> jiraChangeItems) {
		for (JIRAChangeItem jiraChangeItem : jiraChangeItems) {
			if (EntityCacheUtil.getResult(
						JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
						JIRAChangeItemImpl.class, jiraChangeItem.getPrimaryKey()) == null) {
				cacheResult(jiraChangeItem);
			}
			else {
				jiraChangeItem.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all j i r a change items.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(JIRAChangeItemImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the j i r a change item.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(JIRAChangeItem jiraChangeItem) {
		EntityCacheUtil.removeResult(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemImpl.class, jiraChangeItem.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<JIRAChangeItem> jiraChangeItems) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (JIRAChangeItem jiraChangeItem : jiraChangeItems) {
			EntityCacheUtil.removeResult(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
				JIRAChangeItemImpl.class, jiraChangeItem.getPrimaryKey());
		}
	}

	/**
	 * Creates a new j i r a change item with the primary key. Does not add the j i r a change item to the database.
	 *
	 * @param jiraChangeItemId the primary key for the new j i r a change item
	 * @return the new j i r a change item
	 */
	@Override
	public JIRAChangeItem create(long jiraChangeItemId) {
		JIRAChangeItem jiraChangeItem = new JIRAChangeItemImpl();

		jiraChangeItem.setNew(true);
		jiraChangeItem.setPrimaryKey(jiraChangeItemId);

		return jiraChangeItem;
	}

	/**
	 * Removes the j i r a change item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param jiraChangeItemId the primary key of the j i r a change item
	 * @return the j i r a change item that was removed
	 * @throws NoSuchJIRAChangeItemException if a j i r a change item with the primary key could not be found
	 */
	@Override
	public JIRAChangeItem remove(long jiraChangeItemId)
		throws NoSuchJIRAChangeItemException {
		return remove((Serializable)jiraChangeItemId);
	}

	/**
	 * Removes the j i r a change item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the j i r a change item
	 * @return the j i r a change item that was removed
	 * @throws NoSuchJIRAChangeItemException if a j i r a change item with the primary key could not be found
	 */
	@Override
	public JIRAChangeItem remove(Serializable primaryKey)
		throws NoSuchJIRAChangeItemException {
		Session session = null;

		try {
			session = openSession();

			JIRAChangeItem jiraChangeItem = (JIRAChangeItem)session.get(JIRAChangeItemImpl.class,
					primaryKey);

			if (jiraChangeItem == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchJIRAChangeItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(jiraChangeItem);
		}
		catch (NoSuchJIRAChangeItemException nsee) {
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
	protected JIRAChangeItem removeImpl(JIRAChangeItem jiraChangeItem) {
		jiraChangeItem = toUnwrappedModel(jiraChangeItem);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(jiraChangeItem)) {
				jiraChangeItem = (JIRAChangeItem)session.get(JIRAChangeItemImpl.class,
						jiraChangeItem.getPrimaryKeyObj());
			}

			if (jiraChangeItem != null) {
				session.delete(jiraChangeItem);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (jiraChangeItem != null) {
			clearCache(jiraChangeItem);
		}

		return jiraChangeItem;
	}

	@Override
	public JIRAChangeItem updateImpl(JIRAChangeItem jiraChangeItem) {
		jiraChangeItem = toUnwrappedModel(jiraChangeItem);

		boolean isNew = jiraChangeItem.isNew();

		JIRAChangeItemModelImpl jiraChangeItemModelImpl = (JIRAChangeItemModelImpl)jiraChangeItem;

		Session session = null;

		try {
			session = openSession();

			if (jiraChangeItem.isNew()) {
				session.save(jiraChangeItem);

				jiraChangeItem.setNew(false);
			}
			else {
				session.merge(jiraChangeItem);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !JIRAChangeItemModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((jiraChangeItemModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JIRACHANGEGROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						jiraChangeItemModelImpl.getOriginalJiraChangeGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_JIRACHANGEGROUPID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JIRACHANGEGROUPID,
					args);

				args = new Object[] {
						jiraChangeItemModelImpl.getJiraChangeGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_JIRACHANGEGROUPID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_JIRACHANGEGROUPID,
					args);
			}
		}

		EntityCacheUtil.putResult(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemImpl.class, jiraChangeItem.getPrimaryKey(),
			jiraChangeItem, false);

		jiraChangeItem.resetOriginalValues();

		return jiraChangeItem;
	}

	protected JIRAChangeItem toUnwrappedModel(JIRAChangeItem jiraChangeItem) {
		if (jiraChangeItem instanceof JIRAChangeItemImpl) {
			return jiraChangeItem;
		}

		JIRAChangeItemImpl jiraChangeItemImpl = new JIRAChangeItemImpl();

		jiraChangeItemImpl.setNew(jiraChangeItem.isNew());
		jiraChangeItemImpl.setPrimaryKey(jiraChangeItem.getPrimaryKey());

		jiraChangeItemImpl.setJiraChangeItemId(jiraChangeItem.getJiraChangeItemId());
		jiraChangeItemImpl.setJiraChangeGroupId(jiraChangeItem.getJiraChangeGroupId());
		jiraChangeItemImpl.setField(jiraChangeItem.getField());
		jiraChangeItemImpl.setOldValue(jiraChangeItem.getOldValue());
		jiraChangeItemImpl.setOldString(jiraChangeItem.getOldString());
		jiraChangeItemImpl.setNewValue(jiraChangeItem.getNewValue());
		jiraChangeItemImpl.setNewString(jiraChangeItem.getNewString());

		return jiraChangeItemImpl;
	}

	/**
	 * Returns the j i r a change item with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the j i r a change item
	 * @return the j i r a change item
	 * @throws NoSuchJIRAChangeItemException if a j i r a change item with the primary key could not be found
	 */
	@Override
	public JIRAChangeItem findByPrimaryKey(Serializable primaryKey)
		throws NoSuchJIRAChangeItemException {
		JIRAChangeItem jiraChangeItem = fetchByPrimaryKey(primaryKey);

		if (jiraChangeItem == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchJIRAChangeItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return jiraChangeItem;
	}

	/**
	 * Returns the j i r a change item with the primary key or throws a {@link NoSuchJIRAChangeItemException} if it could not be found.
	 *
	 * @param jiraChangeItemId the primary key of the j i r a change item
	 * @return the j i r a change item
	 * @throws NoSuchJIRAChangeItemException if a j i r a change item with the primary key could not be found
	 */
	@Override
	public JIRAChangeItem findByPrimaryKey(long jiraChangeItemId)
		throws NoSuchJIRAChangeItemException {
		return findByPrimaryKey((Serializable)jiraChangeItemId);
	}

	/**
	 * Returns the j i r a change item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the j i r a change item
	 * @return the j i r a change item, or <code>null</code> if a j i r a change item with the primary key could not be found
	 */
	@Override
	public JIRAChangeItem fetchByPrimaryKey(Serializable primaryKey) {
		JIRAChangeItem jiraChangeItem = (JIRAChangeItem)EntityCacheUtil.getResult(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
				JIRAChangeItemImpl.class, primaryKey);

		if (jiraChangeItem == _nullJIRAChangeItem) {
			return null;
		}

		if (jiraChangeItem == null) {
			Session session = null;

			try {
				session = openSession();

				jiraChangeItem = (JIRAChangeItem)session.get(JIRAChangeItemImpl.class,
						primaryKey);

				if (jiraChangeItem != null) {
					cacheResult(jiraChangeItem);
				}
				else {
					EntityCacheUtil.putResult(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
						JIRAChangeItemImpl.class, primaryKey,
						_nullJIRAChangeItem);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
					JIRAChangeItemImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return jiraChangeItem;
	}

	/**
	 * Returns the j i r a change item with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param jiraChangeItemId the primary key of the j i r a change item
	 * @return the j i r a change item, or <code>null</code> if a j i r a change item with the primary key could not be found
	 */
	@Override
	public JIRAChangeItem fetchByPrimaryKey(long jiraChangeItemId) {
		return fetchByPrimaryKey((Serializable)jiraChangeItemId);
	}

	@Override
	public Map<Serializable, JIRAChangeItem> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, JIRAChangeItem> map = new HashMap<Serializable, JIRAChangeItem>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			JIRAChangeItem jiraChangeItem = fetchByPrimaryKey(primaryKey);

			if (jiraChangeItem != null) {
				map.put(primaryKey, jiraChangeItem);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			JIRAChangeItem jiraChangeItem = (JIRAChangeItem)EntityCacheUtil.getResult(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
					JIRAChangeItemImpl.class, primaryKey);

			if (jiraChangeItem == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, jiraChangeItem);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_JIRACHANGEITEM_WHERE_PKS_IN);

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

			for (JIRAChangeItem jiraChangeItem : (List<JIRAChangeItem>)q.list()) {
				map.put(jiraChangeItem.getPrimaryKeyObj(), jiraChangeItem);

				cacheResult(jiraChangeItem);

				uncachedPrimaryKeys.remove(jiraChangeItem.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
					JIRAChangeItemImpl.class, primaryKey, _nullJIRAChangeItem);
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
	 * Returns all the j i r a change items.
	 *
	 * @return the j i r a change items
	 */
	@Override
	public List<JIRAChangeItem> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the j i r a change items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAChangeItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of j i r a change items
	 * @param end the upper bound of the range of j i r a change items (not inclusive)
	 * @return the range of j i r a change items
	 */
	@Override
	public List<JIRAChangeItem> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the j i r a change items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAChangeItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of j i r a change items
	 * @param end the upper bound of the range of j i r a change items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of j i r a change items
	 */
	@Override
	public List<JIRAChangeItem> findAll(int start, int end,
		OrderByComparator<JIRAChangeItem> orderByComparator) {
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

		List<JIRAChangeItem> list = (List<JIRAChangeItem>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_JIRACHANGEITEM);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_JIRACHANGEITEM;

				if (pagination) {
					sql = sql.concat(JIRAChangeItemModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<JIRAChangeItem>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<JIRAChangeItem>)QueryUtil.list(q,
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
	 * Removes all the j i r a change items from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (JIRAChangeItem jiraChangeItem : findAll()) {
			remove(jiraChangeItem);
		}
	}

	/**
	 * Returns the number of j i r a change items.
	 *
	 * @return the number of j i r a change items
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_JIRACHANGEITEM);

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
		return JIRAChangeItemModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the j i r a change item persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(JIRAChangeItemImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_JIRACHANGEITEM = "SELECT jiraChangeItem FROM JIRAChangeItem jiraChangeItem";
	private static final String _SQL_SELECT_JIRACHANGEITEM_WHERE_PKS_IN = "SELECT jiraChangeItem FROM JIRAChangeItem jiraChangeItem WHERE id IN (";
	private static final String _SQL_SELECT_JIRACHANGEITEM_WHERE = "SELECT jiraChangeItem FROM JIRAChangeItem jiraChangeItem WHERE ";
	private static final String _SQL_COUNT_JIRACHANGEITEM = "SELECT COUNT(jiraChangeItem) FROM JIRAChangeItem jiraChangeItem";
	private static final String _SQL_COUNT_JIRACHANGEITEM_WHERE = "SELECT COUNT(jiraChangeItem) FROM JIRAChangeItem jiraChangeItem WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "jiraChangeItem.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No JIRAChangeItem exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No JIRAChangeItem exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(JIRAChangeItemPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"jiraChangeItemId", "jiraChangeGroupId"
			});
	private static final JIRAChangeItem _nullJIRAChangeItem = new JIRAChangeItemImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<JIRAChangeItem> toCacheModel() {
				return _nullJIRAChangeItemCacheModel;
			}
		};

	private static final CacheModel<JIRAChangeItem> _nullJIRAChangeItemCacheModel =
		new CacheModel<JIRAChangeItem>() {
			@Override
			public JIRAChangeItem toEntityModel() {
				return _nullJIRAChangeItem;
			}
		};
}