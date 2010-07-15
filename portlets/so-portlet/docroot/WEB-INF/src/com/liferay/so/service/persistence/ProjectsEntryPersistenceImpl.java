/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.so.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.so.NoSuchProjectsEntryException;
import com.liferay.so.model.ProjectsEntry;
import com.liferay.so.model.impl.ProjectsEntryImpl;
import com.liferay.so.model.impl.ProjectsEntryModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author    Brian Wing Shun Chan
 * @see       ProjectsEntryPersistence
 * @see       ProjectsEntryUtil
 * @generated
 */
public class ProjectsEntryPersistenceImpl extends BasePersistenceImpl<ProjectsEntry>
	implements ProjectsEntryPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = ProjectsEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_USERID = new FinderPath(ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProjectsEntryModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProjectsEntryModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProjectsEntryModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProjectsEntryModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	public void cacheResult(ProjectsEntry projectsEntry) {
		EntityCacheUtil.putResult(ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProjectsEntryImpl.class, projectsEntry.getPrimaryKey(),
			projectsEntry);
	}

	public void cacheResult(List<ProjectsEntry> projectsEntries) {
		for (ProjectsEntry projectsEntry : projectsEntries) {
			if (EntityCacheUtil.getResult(
						ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
						ProjectsEntryImpl.class, projectsEntry.getPrimaryKey(),
						this) == null) {
				cacheResult(projectsEntry);
			}
		}
	}

	public void clearCache() {
		CacheRegistryUtil.clear(ProjectsEntryImpl.class.getName());
		EntityCacheUtil.clearCache(ProjectsEntryImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public void clearCache(ProjectsEntry projectsEntry) {
		EntityCacheUtil.removeResult(ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProjectsEntryImpl.class, projectsEntry.getPrimaryKey());
	}

	public ProjectsEntry create(long projectsEntryId) {
		ProjectsEntry projectsEntry = new ProjectsEntryImpl();

		projectsEntry.setNew(true);
		projectsEntry.setPrimaryKey(projectsEntryId);

		return projectsEntry;
	}

	public ProjectsEntry remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public ProjectsEntry remove(long projectsEntryId)
		throws NoSuchProjectsEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ProjectsEntry projectsEntry = (ProjectsEntry)session.get(ProjectsEntryImpl.class,
					new Long(projectsEntryId));

			if (projectsEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						projectsEntryId);
				}

				throw new NoSuchProjectsEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					projectsEntryId);
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

	protected ProjectsEntry removeImpl(ProjectsEntry projectsEntry)
		throws SystemException {
		projectsEntry = toUnwrappedModel(projectsEntry);

		Session session = null;

		try {
			session = openSession();

			if (projectsEntry.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(ProjectsEntryImpl.class,
						projectsEntry.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(projectsEntry);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProjectsEntryImpl.class, projectsEntry.getPrimaryKey());

		return projectsEntry;
	}

	public ProjectsEntry updateImpl(
		com.liferay.so.model.ProjectsEntry projectsEntry, boolean merge)
		throws SystemException {
		projectsEntry = toUnwrappedModel(projectsEntry);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, projectsEntry, merge);

			projectsEntry.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProjectsEntryImpl.class, projectsEntry.getPrimaryKey(),
			projectsEntry);

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

	public ProjectsEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public ProjectsEntry findByPrimaryKey(long projectsEntryId)
		throws NoSuchProjectsEntryException, SystemException {
		ProjectsEntry projectsEntry = fetchByPrimaryKey(projectsEntryId);

		if (projectsEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + projectsEntryId);
			}

			throw new NoSuchProjectsEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				projectsEntryId);
		}

		return projectsEntry;
	}

	public ProjectsEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public ProjectsEntry fetchByPrimaryKey(long projectsEntryId)
		throws SystemException {
		ProjectsEntry projectsEntry = (ProjectsEntry)EntityCacheUtil.getResult(ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
				ProjectsEntryImpl.class, projectsEntryId, this);

		if (projectsEntry == null) {
			Session session = null;

			try {
				session = openSession();

				projectsEntry = (ProjectsEntry)session.get(ProjectsEntryImpl.class,
						new Long(projectsEntryId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (projectsEntry != null) {
					cacheResult(projectsEntry);
				}

				closeSession(session);
			}
		}

		return projectsEntry;
	}

	public List<ProjectsEntry> findByUserId(long userId)
		throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<ProjectsEntry> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	public List<ProjectsEntry> findByUserId(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				userId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<ProjectsEntry> list = (List<ProjectsEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_USERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

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

				else {
					query.append(ProjectsEntryModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = (List<ProjectsEntry>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<ProjectsEntry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_USERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public ProjectsEntry findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchProjectsEntryException, SystemException {
		List<ProjectsEntry> list = findByUserId(userId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchProjectsEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ProjectsEntry findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchProjectsEntryException, SystemException {
		int count = countByUserId(userId);

		List<ProjectsEntry> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchProjectsEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public ProjectsEntry[] findByUserId_PrevAndNext(long projectsEntryId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchProjectsEntryException, SystemException {
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
		OrderByComparator orderByComparator, boolean previous) {
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
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
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
			Object[] values = orderByComparator.getOrderByValues(projectsEntry);

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

	public List<ProjectsEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<ProjectsEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<ProjectsEntry> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<ProjectsEntry> list = (List<ProjectsEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

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
					sql = _SQL_SELECT_PROJECTSENTRY.concat(ProjectsEntryModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<ProjectsEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<ProjectsEntry>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<ProjectsEntry>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByUserId(long userId) throws SystemException {
		for (ProjectsEntry projectsEntry : findByUserId(userId)) {
			remove(projectsEntry);
		}
	}

	public void removeAll() throws SystemException {
		for (ProjectsEntry projectsEntry : findAll()) {
			remove(projectsEntry);
		}
	}

	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_PROJECTSENTRY_WHERE);

				query.append(_FINDER_COLUMN_USERID_USERID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PROJECTSENTRY);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.so.model.ProjectsEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<ProjectsEntry>> listenersList = new ArrayList<ModelListener<ProjectsEntry>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<ProjectsEntry>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(type = MemberRequestPersistence.class)
	protected MemberRequestPersistence memberRequestPersistence;
	@BeanReference(type = ProjectsEntryPersistence.class)
	protected ProjectsEntryPersistence projectsEntryPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_PROJECTSENTRY = "SELECT projectsEntry FROM ProjectsEntry projectsEntry";
	private static final String _SQL_SELECT_PROJECTSENTRY_WHERE = "SELECT projectsEntry FROM ProjectsEntry projectsEntry WHERE ";
	private static final String _SQL_COUNT_PROJECTSENTRY = "SELECT COUNT(projectsEntry) FROM ProjectsEntry projectsEntry";
	private static final String _SQL_COUNT_PROJECTSENTRY_WHERE = "SELECT COUNT(projectsEntry) FROM ProjectsEntry projectsEntry WHERE ";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "projectsEntry.userId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "projectsEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProjectsEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ProjectsEntry exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(ProjectsEntryPersistenceImpl.class);
}