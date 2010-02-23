/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.so.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
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
 * <a href="ProjectsEntryPersistenceImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 */
public class ProjectsEntryPersistenceImpl extends BasePersistenceImpl<ProjectsEntry>
	implements ProjectsEntryPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = ProjectsEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_USERID = new FinderPath(ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
			ProjectsEntryModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_USERID = new FinderPath(ProjectsEntryModelImpl.ENTITY_CACHE_ENABLED,
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
		CacheRegistry.clear(ProjectsEntryImpl.class.getName());
		EntityCacheUtil.clearCache(ProjectsEntryImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
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

	public ProjectsEntry remove(ProjectsEntry projectsEntry)
		throws SystemException {
		for (ModelListener<ProjectsEntry> listener : listeners) {
			listener.onBeforeRemove(projectsEntry);
		}

		projectsEntry = removeImpl(projectsEntry);

		for (ModelListener<ProjectsEntry> listener : listeners) {
			listener.onAfterRemove(projectsEntry);
		}

		return projectsEntry;
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

	public ProjectsEntry update(ProjectsEntry projectsEntry)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(ProjectsEntry projectsEntry) method. Use update(ProjectsEntry projectsEntry, boolean merge) instead.");
		}

		return update(projectsEntry, false);
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
		Object[] finderArgs = new Object[] { new Long(userId) };

		List<ProjectsEntry> list = (List<ProjectsEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_USERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_SELECT_PROJECTSENTRY_WHERE);

				query.append(_FINDER_COLUMN_USERID_USERID_2);

				query.append(ProjectsEntryModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = q.list();
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

	public List<ProjectsEntry> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	public List<ProjectsEntry> findByUserId(long userId, int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(userId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<ProjectsEntry> list = (List<ProjectsEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_USERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (obc != null) {
					query = new StringBundler(3 +
							(obc.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(3);
				}

				query.append(_SQL_SELECT_PROJECTSENTRY_WHERE);

				query.append(_FINDER_COLUMN_USERID_USERID_2);

				if (obc != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);
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

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_USERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public ProjectsEntry findByUserId_First(long userId, OrderByComparator obc)
		throws NoSuchProjectsEntryException, SystemException {
		List<ProjectsEntry> list = findByUserId(userId, 0, 1, obc);

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

	public ProjectsEntry findByUserId_Last(long userId, OrderByComparator obc)
		throws NoSuchProjectsEntryException, SystemException {
		int count = countByUserId(userId);

		List<ProjectsEntry> list = findByUserId(userId, count - 1, count, obc);

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
		long userId, OrderByComparator obc)
		throws NoSuchProjectsEntryException, SystemException {
		ProjectsEntry projectsEntry = findByPrimaryKey(projectsEntryId);

		int count = countByUserId(userId);

		Session session = null;

		try {
			session = openSession();

			StringBundler query = null;

			if (obc != null) {
				query = new StringBundler(3 +
						(obc.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_PROJECTSENTRY_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (obc != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);
			}

			else {
				query.append(ProjectsEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					projectsEntry);

			ProjectsEntry[] array = new ProjectsEntryImpl[3];

			array[0] = (ProjectsEntry)objArray[0];
			array[1] = (ProjectsEntry)objArray[1];
			array[2] = (ProjectsEntry)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			dynamicQuery.compile(session);

			return dynamicQuery.list();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			dynamicQuery.setLimit(start, end);

			dynamicQuery.compile(session);

			return dynamicQuery.list();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<ProjectsEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<ProjectsEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<ProjectsEntry> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<ProjectsEntry> list = (List<ProjectsEntry>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;
				String sql = null;

				if (obc != null) {
					query = new StringBundler(2 +
							(obc.getOrderByFields().length * 3));

					query.append(_SQL_SELECT_PROJECTSENTRY);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);

					sql = query.toString();
				}

				else {
					sql = _SQL_SELECT_PROJECTSENTRY.concat(ProjectsEntryModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (obc == null) {
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
		Object[] finderArgs = new Object[] { new Long(userId) };

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
					listenersList.add((ModelListener<ProjectsEntry>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(name = "com.liferay.so.service.persistence.MemberRequestPersistence")
	protected com.liferay.so.service.persistence.MemberRequestPersistence memberRequestPersistence;
	@BeanReference(name = "com.liferay.so.service.persistence.ProjectsEntryPersistence")
	protected com.liferay.so.service.persistence.ProjectsEntryPersistence projectsEntryPersistence;
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