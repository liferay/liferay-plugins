/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.tms.service.persistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.tms.NoSuchProjectEntryException;
import com.liferay.tms.model.ProjectEntry;
import com.liferay.tms.model.impl.ProjectEntryImpl;
import com.liferay.tms.model.impl.ProjectEntryModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="ProjectEntryPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ProjectEntryPersistenceImpl extends BasePersistenceImpl
	implements ProjectEntryPersistence {
	public ProjectEntry create(long projectEntryId) {
		ProjectEntry projectEntry = new ProjectEntryImpl();

		projectEntry.setNew(true);
		projectEntry.setPrimaryKey(projectEntryId);

		return projectEntry;
	}

	public ProjectEntry remove(long projectEntryId)
		throws NoSuchProjectEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			ProjectEntry projectEntry = (ProjectEntry)session.get(ProjectEntryImpl.class,
					new Long(projectEntryId));

			if (projectEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No ProjectEntry exists with the primary key " +
						projectEntryId);
				}

				throw new NoSuchProjectEntryException(
					"No ProjectEntry exists with the primary key " +
					projectEntryId);
			}

			return remove(projectEntry);
		}
		catch (NoSuchProjectEntryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public ProjectEntry remove(ProjectEntry projectEntry)
		throws SystemException {
		for (ModelListener listener : listeners) {
			listener.onBeforeRemove(projectEntry);
		}

		projectEntry = removeImpl(projectEntry);

		for (ModelListener listener : listeners) {
			listener.onAfterRemove(projectEntry);
		}

		return projectEntry;
	}

	protected ProjectEntry removeImpl(ProjectEntry projectEntry)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(ProjectEntryImpl.class,
						projectEntry.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(projectEntry);

			session.flush();

			return projectEntry;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(ProjectEntry.class.getName());
		}
	}

	public ProjectEntry update(ProjectEntry projectEntry)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(ProjectEntry projectEntry) method. Use update(ProjectEntry projectEntry, boolean merge) instead.");
		}

		return update(projectEntry, false);
	}

	public ProjectEntry update(ProjectEntry projectEntry, boolean merge)
		throws SystemException {
		boolean isNew = projectEntry.isNew();

		for (ModelListener listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(projectEntry);
			}
			else {
				listener.onBeforeUpdate(projectEntry);
			}
		}

		projectEntry = updateImpl(projectEntry, merge);

		for (ModelListener listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(projectEntry);
			}
			else {
				listener.onAfterUpdate(projectEntry);
			}
		}

		return projectEntry;
	}

	public ProjectEntry updateImpl(
		com.liferay.tms.model.ProjectEntry projectEntry, boolean merge)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, projectEntry, merge);

			projectEntry.setNew(false);

			return projectEntry;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(ProjectEntry.class.getName());
		}
	}

	public ProjectEntry findByPrimaryKey(long projectEntryId)
		throws NoSuchProjectEntryException, SystemException {
		ProjectEntry projectEntry = fetchByPrimaryKey(projectEntryId);

		if (projectEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No ProjectEntry exists with the primary key " +
					projectEntryId);
			}

			throw new NoSuchProjectEntryException(
				"No ProjectEntry exists with the primary key " +
				projectEntryId);
		}

		return projectEntry;
	}

	public ProjectEntry fetchByPrimaryKey(long projectEntryId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (ProjectEntry)session.get(ProjectEntryImpl.class,
				new Long(projectEntryId));
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

	public List<ProjectEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<ProjectEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<ProjectEntry> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = ProjectEntryModelImpl.CACHE_ENABLED;
		String finderClassName = ProjectEntry.class.getName();
		String finderMethodName = "findAll";
		String[] finderParams = new String[] {
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.tms.model.ProjectEntry ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				List<ProjectEntry> list = null;

				if (obc == null) {
					list = (List<ProjectEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<ProjectEntry>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<ProjectEntry>)result;
		}
	}

	public void removeAll() throws SystemException {
		for (ProjectEntry projectEntry : findAll()) {
			remove(projectEntry);
		}
	}

	public int countAll() throws SystemException {
		boolean finderClassNameCacheEnabled = ProjectEntryModelImpl.CACHE_ENABLED;
		String finderClassName = ProjectEntry.class.getName();
		String finderMethodName = "countAll";
		String[] finderParams = new String[] {  };
		Object[] finderArgs = new Object[] {  };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(
						"SELECT COUNT(*) FROM com.liferay.tms.model.ProjectEntry");

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return ((Long)result).intValue();
		}
	}

	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.tms.model.ProjectEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener> listenersList = new ArrayList<ModelListener>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(name = "com.liferay.tms.service.persistence.ProjectEntryPersistence.impl")
	protected com.liferay.tms.service.persistence.ProjectEntryPersistence projectEntryPersistence;
	@BeanReference(name = "com.liferay.tms.service.persistence.ProjectMilestonePersistence.impl")
	protected com.liferay.tms.service.persistence.ProjectMilestonePersistence projectMilestonePersistence;
	@BeanReference(name = "com.liferay.tms.service.persistence.TaskEntryPersistence.impl")
	protected com.liferay.tms.service.persistence.TaskEntryPersistence taskEntryPersistence;
	@BeanReference(name = "com.liferay.tms.service.persistence.TaskViewPersistence.impl")
	protected com.liferay.tms.service.persistence.TaskViewPersistence taskViewPersistence;
	@BeanReference(name = "com.liferay.portlet.tags.service.persistence.TagsAssetPersistence.impl")
	protected com.liferay.portlet.tags.service.persistence.TagsAssetPersistence tagsAssetPersistence;
	@BeanReference(name = "com.liferay.portlet.tags.service.persistence.TagsEntryPersistence.impl")
	protected com.liferay.portlet.tags.service.persistence.TagsEntryPersistence tagsEntryPersistence;
	private static Log _log = LogFactoryUtil.getLog(ProjectEntryPersistenceImpl.class);
}