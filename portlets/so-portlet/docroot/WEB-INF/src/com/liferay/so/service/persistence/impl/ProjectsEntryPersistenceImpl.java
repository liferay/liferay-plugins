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

package com.liferay.so.service.persistence.impl;

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
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.so.NoSuchProjectsEntryException;
import com.liferay.so.model.ProjectsEntry;
import com.liferay.so.model.impl.ProjectsEntryImpl;
import com.liferay.so.model.impl.ProjectsEntryModelImpl;
import com.liferay.so.service.persistence.ProjectsEntryPersistence;

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
 * The persistence implementation for the projects entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProjectsEntryPersistence
 * @see com.liferay.so.service.persistence.ProjectsEntryUtil
 * @generated
 */
@ProviderType
public class ProjectsEntryPersistenceImpl extends BasePersistenceImpl<ProjectsEntry>
	implements ProjectsEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ProjectsEntryUtil} to access the projects entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ProjectsEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProjectsEntryModelImpl.FINDER_CACHE_ENABLED,
			ProjectsEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProjectsEntryModelImpl.FINDER_CACHE_ENABLED,
			ProjectsEntryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProjectsEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProjectsEntryModelImpl.FINDER_CACHE_ENABLED,
			ProjectsEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProjectsEntryModelImpl.FINDER_CACHE_ENABLED,
			ProjectsEntryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByUserId", new String[] { Long.class.getName() },
			ProjectsEntryModelImpl.USERID_COLUMN_BITMASK |
			ProjectsEntryModelImpl.ENDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProjectsEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the projects entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching projects entries
	 */
	@Override
	public List<ProjectsEntry> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the projects entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of projects entries
	 * @param end the upper bound of the range of projects entries (not inclusive)
	 * @return the range of matching projects entries
	 */
	@Override
	public List<ProjectsEntry> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the projects entries where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of projects entries
	 * @param end the upper bound of the range of projects entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching projects entries
	 */
	@Override
	public List<ProjectsEntry> findByUserId(long userId, int start, int end,
		OrderByComparator<ProjectsEntry> orderByComparator) {
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

		List<ProjectsEntry> list = (List<ProjectsEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (ProjectsEntry projectsEntry : list) {
				if ((userId != projectsEntry.getUserId())) {
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

			query.append(_SQL_SELECT_PROJECTSENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ProjectsEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<ProjectsEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProjectsEntry>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first projects entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching projects entry
	 * @throws NoSuchProjectsEntryException if a matching projects entry could not be found
	 */
	@Override
	public ProjectsEntry findByUserId_First(long userId,
		OrderByComparator<ProjectsEntry> orderByComparator)
		throws NoSuchProjectsEntryException {
		ProjectsEntry projectsEntry = fetchByUserId_First(userId,
				orderByComparator);

		if (projectsEntry != null) {
			return projectsEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchProjectsEntryException(msg.toString());
	}

	/**
	 * Returns the first projects entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching projects entry, or <code>null</code> if a matching projects entry could not be found
	 */
	@Override
	public ProjectsEntry fetchByUserId_First(long userId,
		OrderByComparator<ProjectsEntry> orderByComparator) {
		List<ProjectsEntry> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last projects entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching projects entry
	 * @throws NoSuchProjectsEntryException if a matching projects entry could not be found
	 */
	@Override
	public ProjectsEntry findByUserId_Last(long userId,
		OrderByComparator<ProjectsEntry> orderByComparator)
		throws NoSuchProjectsEntryException {
		ProjectsEntry projectsEntry = fetchByUserId_Last(userId,
				orderByComparator);

		if (projectsEntry != null) {
			return projectsEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchProjectsEntryException(msg.toString());
	}

	/**
	 * Returns the last projects entry in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching projects entry, or <code>null</code> if a matching projects entry could not be found
	 */
	@Override
	public ProjectsEntry fetchByUserId_Last(long userId,
		OrderByComparator<ProjectsEntry> orderByComparator) {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<ProjectsEntry> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the projects entries before and after the current projects entry in the ordered set where userId = &#63;.
	 *
	 * @param projectsEntryId the primary key of the current projects entry
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next projects entry
	 * @throws NoSuchProjectsEntryException if a projects entry with the primary key could not be found
	 */
	@Override
	public ProjectsEntry[] findByUserId_PrevAndNext(long projectsEntryId,
		long userId, OrderByComparator<ProjectsEntry> orderByComparator)
		throws NoSuchProjectsEntryException {
		ProjectsEntry projectsEntry = findByPrimaryKey(projectsEntryId);

		Session session = null;

		try {
			session = openSession();

			ProjectsEntry[] array = new ProjectsEntryImpl[3];

			array[0] = getByUserId_PrevAndNext(session, projectsEntry, userId,
					orderByComparator, true);

			array[1] = projectsEntry;

			array[2] = getByUserId_PrevAndNext(session, projectsEntry, userId,
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

	protected ProjectsEntry getByUserId_PrevAndNext(Session session,
		ProjectsEntry projectsEntry, long userId,
		OrderByComparator<ProjectsEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PROJECTSENTRY_WHERE);

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
			query.append(ProjectsEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(projectsEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ProjectsEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the projects entries where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (ProjectsEntry projectsEntry : findByUserId(userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(projectsEntry);
		}
	}

	/**
	 * Returns the number of projects entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching projects entries
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PROJECTSENTRY_WHERE);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "projectsEntry.userId = ?";

	public ProjectsEntryPersistenceImpl() {
		setModelClass(ProjectsEntry.class);
	}

	/**
	 * Caches the projects entry in the entity cache if it is enabled.
	 *
	 * @param projectsEntry the projects entry
	 */
	@Override
	public void cacheResult(ProjectsEntry projectsEntry) {
		EntityCacheUtil.putResult(ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProjectsEntryImpl.class, projectsEntry.getPrimaryKey(),
			projectsEntry);

		projectsEntry.resetOriginalValues();
	}

	/**
	 * Caches the projects entries in the entity cache if it is enabled.
	 *
	 * @param projectsEntries the projects entries
	 */
	@Override
	public void cacheResult(List<ProjectsEntry> projectsEntries) {
		for (ProjectsEntry projectsEntry : projectsEntries) {
			if (EntityCacheUtil.getResult(
						ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
						ProjectsEntryImpl.class, projectsEntry.getPrimaryKey()) == null) {
				cacheResult(projectsEntry);
			}
			else {
				projectsEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all projects entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(ProjectsEntryImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the projects entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ProjectsEntry projectsEntry) {
		EntityCacheUtil.removeResult(ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProjectsEntryImpl.class, projectsEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ProjectsEntry> projectsEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ProjectsEntry projectsEntry : projectsEntries) {
			EntityCacheUtil.removeResult(ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
				ProjectsEntryImpl.class, projectsEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new projects entry with the primary key. Does not add the projects entry to the database.
	 *
	 * @param projectsEntryId the primary key for the new projects entry
	 * @return the new projects entry
	 */
	@Override
	public ProjectsEntry create(long projectsEntryId) {
		ProjectsEntry projectsEntry = new ProjectsEntryImpl();

		projectsEntry.setNew(true);
		projectsEntry.setPrimaryKey(projectsEntryId);

		return projectsEntry;
	}

	/**
	 * Removes the projects entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param projectsEntryId the primary key of the projects entry
	 * @return the projects entry that was removed
	 * @throws NoSuchProjectsEntryException if a projects entry with the primary key could not be found
	 */
	@Override
	public ProjectsEntry remove(long projectsEntryId)
		throws NoSuchProjectsEntryException {
		return remove((Serializable)projectsEntryId);
	}

	/**
	 * Removes the projects entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the projects entry
	 * @return the projects entry that was removed
	 * @throws NoSuchProjectsEntryException if a projects entry with the primary key could not be found
	 */
	@Override
	public ProjectsEntry remove(Serializable primaryKey)
		throws NoSuchProjectsEntryException {
		Session session = null;

		try {
			session = openSession();

			ProjectsEntry projectsEntry = (ProjectsEntry)session.get(ProjectsEntryImpl.class,
					primaryKey);

			if (projectsEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProjectsEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(projectsEntry);
		}
		catch (NoSuchProjectsEntryException nsee) {
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
	protected ProjectsEntry removeImpl(ProjectsEntry projectsEntry) {
		projectsEntry = toUnwrappedModel(projectsEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(projectsEntry)) {
				projectsEntry = (ProjectsEntry)session.get(ProjectsEntryImpl.class,
						projectsEntry.getPrimaryKeyObj());
			}

			if (projectsEntry != null) {
				session.delete(projectsEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (projectsEntry != null) {
			clearCache(projectsEntry);
		}

		return projectsEntry;
	}

	@Override
	public ProjectsEntry updateImpl(ProjectsEntry projectsEntry) {
		projectsEntry = toUnwrappedModel(projectsEntry);

		boolean isNew = projectsEntry.isNew();

		ProjectsEntryModelImpl projectsEntryModelImpl = (ProjectsEntryModelImpl)projectsEntry;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (projectsEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				projectsEntry.setCreateDate(now);
			}
			else {
				projectsEntry.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!projectsEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				projectsEntry.setModifiedDate(now);
			}
			else {
				projectsEntry.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (projectsEntry.isNew()) {
				session.save(projectsEntry);

				projectsEntry.setNew(false);
			}
			else {
				session.merge(projectsEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ProjectsEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((projectsEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						projectsEntryModelImpl.getOriginalUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { projectsEntryModelImpl.getUserId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}
		}

		EntityCacheUtil.putResult(ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProjectsEntryImpl.class, projectsEntry.getPrimaryKey(),
			projectsEntry, false);

		projectsEntry.resetOriginalValues();

		return projectsEntry;
	}

	protected ProjectsEntry toUnwrappedModel(ProjectsEntry projectsEntry) {
		if (projectsEntry instanceof ProjectsEntryImpl) {
			return projectsEntry;
		}

		ProjectsEntryImpl projectsEntryImpl = new ProjectsEntryImpl();

		projectsEntryImpl.setNew(projectsEntry.isNew());
		projectsEntryImpl.setPrimaryKey(projectsEntry.getPrimaryKey());

		projectsEntryImpl.setProjectsEntryId(projectsEntry.getProjectsEntryId());
		projectsEntryImpl.setCompanyId(projectsEntry.getCompanyId());
		projectsEntryImpl.setUserId(projectsEntry.getUserId());
		projectsEntryImpl.setUserName(projectsEntry.getUserName());
		projectsEntryImpl.setCreateDate(projectsEntry.getCreateDate());
		projectsEntryImpl.setModifiedDate(projectsEntry.getModifiedDate());
		projectsEntryImpl.setTitle(projectsEntry.getTitle());
		projectsEntryImpl.setDescription(projectsEntry.getDescription());
		projectsEntryImpl.setStartDate(projectsEntry.getStartDate());
		projectsEntryImpl.setEndDate(projectsEntry.getEndDate());
		projectsEntryImpl.setData(projectsEntry.getData());

		return projectsEntryImpl;
	}

	/**
	 * Returns the projects entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the projects entry
	 * @return the projects entry
	 * @throws NoSuchProjectsEntryException if a projects entry with the primary key could not be found
	 */
	@Override
	public ProjectsEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProjectsEntryException {
		ProjectsEntry projectsEntry = fetchByPrimaryKey(primaryKey);

		if (projectsEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProjectsEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return projectsEntry;
	}

	/**
	 * Returns the projects entry with the primary key or throws a {@link NoSuchProjectsEntryException} if it could not be found.
	 *
	 * @param projectsEntryId the primary key of the projects entry
	 * @return the projects entry
	 * @throws NoSuchProjectsEntryException if a projects entry with the primary key could not be found
	 */
	@Override
	public ProjectsEntry findByPrimaryKey(long projectsEntryId)
		throws NoSuchProjectsEntryException {
		return findByPrimaryKey((Serializable)projectsEntryId);
	}

	/**
	 * Returns the projects entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the projects entry
	 * @return the projects entry, or <code>null</code> if a projects entry with the primary key could not be found
	 */
	@Override
	public ProjectsEntry fetchByPrimaryKey(Serializable primaryKey) {
		ProjectsEntry projectsEntry = (ProjectsEntry)EntityCacheUtil.getResult(ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
				ProjectsEntryImpl.class, primaryKey);

		if (projectsEntry == _nullProjectsEntry) {
			return null;
		}

		if (projectsEntry == null) {
			Session session = null;

			try {
				session = openSession();

				projectsEntry = (ProjectsEntry)session.get(ProjectsEntryImpl.class,
						primaryKey);

				if (projectsEntry != null) {
					cacheResult(projectsEntry);
				}
				else {
					EntityCacheUtil.putResult(ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
						ProjectsEntryImpl.class, primaryKey, _nullProjectsEntry);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
					ProjectsEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return projectsEntry;
	}

	/**
	 * Returns the projects entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param projectsEntryId the primary key of the projects entry
	 * @return the projects entry, or <code>null</code> if a projects entry with the primary key could not be found
	 */
	@Override
	public ProjectsEntry fetchByPrimaryKey(long projectsEntryId) {
		return fetchByPrimaryKey((Serializable)projectsEntryId);
	}

	@Override
	public Map<Serializable, ProjectsEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ProjectsEntry> map = new HashMap<Serializable, ProjectsEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ProjectsEntry projectsEntry = fetchByPrimaryKey(primaryKey);

			if (projectsEntry != null) {
				map.put(primaryKey, projectsEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			ProjectsEntry projectsEntry = (ProjectsEntry)EntityCacheUtil.getResult(ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
					ProjectsEntryImpl.class, primaryKey);

			if (projectsEntry == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, projectsEntry);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PROJECTSENTRY_WHERE_PKS_IN);

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

			for (ProjectsEntry projectsEntry : (List<ProjectsEntry>)q.list()) {
				map.put(projectsEntry.getPrimaryKeyObj(), projectsEntry);

				cacheResult(projectsEntry);

				uncachedPrimaryKeys.remove(projectsEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
					ProjectsEntryImpl.class, primaryKey, _nullProjectsEntry);
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
	 * Returns all the projects entries.
	 *
	 * @return the projects entries
	 */
	@Override
	public List<ProjectsEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the projects entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of projects entries
	 * @param end the upper bound of the range of projects entries (not inclusive)
	 * @return the range of projects entries
	 */
	@Override
	public List<ProjectsEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the projects entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of projects entries
	 * @param end the upper bound of the range of projects entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of projects entries
	 */
	@Override
	public List<ProjectsEntry> findAll(int start, int end,
		OrderByComparator<ProjectsEntry> orderByComparator) {
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

		List<ProjectsEntry> list = (List<ProjectsEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_PROJECTSENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PROJECTSENTRY;

				if (pagination) {
					sql = sql.concat(ProjectsEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ProjectsEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProjectsEntry>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the projects entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ProjectsEntry projectsEntry : findAll()) {
			remove(projectsEntry);
		}
	}

	/**
	 * Returns the number of projects entries.
	 *
	 * @return the number of projects entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PROJECTSENTRY);

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
		return ProjectsEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the projects entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(ProjectsEntryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_PROJECTSENTRY = "SELECT projectsEntry FROM ProjectsEntry projectsEntry";
	private static final String _SQL_SELECT_PROJECTSENTRY_WHERE_PKS_IN = "SELECT projectsEntry FROM ProjectsEntry projectsEntry WHERE projectsEntryId IN (";
	private static final String _SQL_SELECT_PROJECTSENTRY_WHERE = "SELECT projectsEntry FROM ProjectsEntry projectsEntry WHERE ";
	private static final String _SQL_COUNT_PROJECTSENTRY = "SELECT COUNT(projectsEntry) FROM ProjectsEntry projectsEntry";
	private static final String _SQL_COUNT_PROJECTSENTRY_WHERE = "SELECT COUNT(projectsEntry) FROM ProjectsEntry projectsEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "projectsEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProjectsEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ProjectsEntry exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ProjectsEntryPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"data"
			});
	private static final ProjectsEntry _nullProjectsEntry = new ProjectsEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<ProjectsEntry> toCacheModel() {
				return _nullProjectsEntryCacheModel;
			}
		};

	private static final CacheModel<ProjectsEntry> _nullProjectsEntryCacheModel = new CacheModel<ProjectsEntry>() {
			@Override
			public ProjectsEntry toEntityModel() {
				return _nullProjectsEntry;
			}
		};
}