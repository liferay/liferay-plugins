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

package com.liferay.wol.service.persistence;

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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.wol.NoSuchJIRAChangeItemException;
import com.liferay.wol.model.JIRAChangeItem;
import com.liferay.wol.model.impl.JIRAChangeItemImpl;
import com.liferay.wol.model.impl.JIRAChangeItemModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="JIRAChangeItemPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JIRAChangeItemPersistenceImpl extends BasePersistenceImpl
	implements JIRAChangeItemPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = JIRAChangeItemImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_JIRACHANGEGROUPID = new FinderPath(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByJiraChangeGroupId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_JIRACHANGEGROUPID = new FinderPath(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByJiraChangeGroupId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_JIRACHANGEGROUPID = new FinderPath(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByJiraChangeGroupId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	public void cacheResult(JIRAChangeItem jiraChangeItem) {
		EntityCacheUtil.putResult(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemImpl.class, jiraChangeItem.getPrimaryKey(),
			jiraChangeItem);
	}

	public void cacheResult(List<JIRAChangeItem> jiraChangeItems) {
		for (JIRAChangeItem jiraChangeItem : jiraChangeItems) {
			if (EntityCacheUtil.getResult(
						JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
						JIRAChangeItemImpl.class,
						jiraChangeItem.getPrimaryKey(), this) == null) {
				cacheResult(jiraChangeItem);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(JIRAChangeItemImpl.class.getName());
		EntityCacheUtil.clearCache(JIRAChangeItemImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public JIRAChangeItem create(long jiraChangeItemId) {
		JIRAChangeItem jiraChangeItem = new JIRAChangeItemImpl();

		jiraChangeItem.setNew(true);
		jiraChangeItem.setPrimaryKey(jiraChangeItemId);

		return jiraChangeItem;
	}

	public JIRAChangeItem remove(long jiraChangeItemId)
		throws NoSuchJIRAChangeItemException, SystemException {
		Session session = null;

		try {
			session = openSession();

			JIRAChangeItem jiraChangeItem = (JIRAChangeItem)session.get(JIRAChangeItemImpl.class,
					new Long(jiraChangeItemId));

			if (jiraChangeItem == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No JIRAChangeItem exists with the primary key " +
						jiraChangeItemId);
				}

				throw new NoSuchJIRAChangeItemException(
					"No JIRAChangeItem exists with the primary key " +
					jiraChangeItemId);
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

	public JIRAChangeItem remove(JIRAChangeItem jiraChangeItem)
		throws SystemException {
		for (ModelListener<JIRAChangeItem> listener : listeners) {
			listener.onBeforeRemove(jiraChangeItem);
		}

		jiraChangeItem = removeImpl(jiraChangeItem);

		for (ModelListener<JIRAChangeItem> listener : listeners) {
			listener.onAfterRemove(jiraChangeItem);
		}

		return jiraChangeItem;
	}

	protected JIRAChangeItem removeImpl(JIRAChangeItem jiraChangeItem)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (jiraChangeItem.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(JIRAChangeItemImpl.class,
						jiraChangeItem.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(jiraChangeItem);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemImpl.class, jiraChangeItem.getPrimaryKey());

		return jiraChangeItem;
	}

	public JIRAChangeItem update(JIRAChangeItem jiraChangeItem)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(JIRAChangeItem jiraChangeItem) method. Use update(JIRAChangeItem jiraChangeItem, boolean merge) instead.");
		}

		return update(jiraChangeItem, false);
	}

	public JIRAChangeItem update(JIRAChangeItem jiraChangeItem, boolean merge)
		throws SystemException {
		boolean isNew = jiraChangeItem.isNew();

		for (ModelListener<JIRAChangeItem> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(jiraChangeItem);
			}
			else {
				listener.onBeforeUpdate(jiraChangeItem);
			}
		}

		jiraChangeItem = updateImpl(jiraChangeItem, merge);

		for (ModelListener<JIRAChangeItem> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(jiraChangeItem);
			}
			else {
				listener.onAfterUpdate(jiraChangeItem);
			}
		}

		return jiraChangeItem;
	}

	public JIRAChangeItem updateImpl(
		com.liferay.wol.model.JIRAChangeItem jiraChangeItem, boolean merge)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, jiraChangeItem, merge);

			jiraChangeItem.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeItemImpl.class, jiraChangeItem.getPrimaryKey(),
			jiraChangeItem);

		return jiraChangeItem;
	}

	public JIRAChangeItem findByPrimaryKey(long jiraChangeItemId)
		throws NoSuchJIRAChangeItemException, SystemException {
		JIRAChangeItem jiraChangeItem = fetchByPrimaryKey(jiraChangeItemId);

		if (jiraChangeItem == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No JIRAChangeItem exists with the primary key " +
					jiraChangeItemId);
			}

			throw new NoSuchJIRAChangeItemException(
				"No JIRAChangeItem exists with the primary key " +
				jiraChangeItemId);
		}

		return jiraChangeItem;
	}

	public JIRAChangeItem fetchByPrimaryKey(long jiraChangeItemId)
		throws SystemException {
		JIRAChangeItem jiraChangeItem = (JIRAChangeItem)EntityCacheUtil.getResult(JIRAChangeItemModelImpl.ENTITY_CACHE_ENABLED,
				JIRAChangeItemImpl.class, jiraChangeItemId, this);

		if (jiraChangeItem == null) {
			Session session = null;

			try {
				session = openSession();

				jiraChangeItem = (JIRAChangeItem)session.get(JIRAChangeItemImpl.class,
						new Long(jiraChangeItemId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (jiraChangeItem != null) {
					cacheResult(jiraChangeItem);
				}

				closeSession(session);
			}
		}

		return jiraChangeItem;
	}

	public List<JIRAChangeItem> findByJiraChangeGroupId(long jiraChangeGroupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(jiraChangeGroupId) };

		List<JIRAChangeItem> list = (List<JIRAChangeItem>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_JIRACHANGEGROUPID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wol.model.JIRAChangeItem WHERE ");

				query.append("groupid = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(jiraChangeGroupId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<JIRAChangeItem>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_JIRACHANGEGROUPID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId, int start, int end) throws SystemException {
		return findByJiraChangeGroupId(jiraChangeGroupId, start, end, null);
	}

	public List<JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId, int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(jiraChangeGroupId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<JIRAChangeItem> list = (List<JIRAChangeItem>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_JIRACHANGEGROUPID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wol.model.JIRAChangeItem WHERE ");

				query.append("groupid = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(jiraChangeGroupId);

				list = (List<JIRAChangeItem>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<JIRAChangeItem>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_JIRACHANGEGROUPID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public JIRAChangeItem findByJiraChangeGroupId_First(
		long jiraChangeGroupId, OrderByComparator obc)
		throws NoSuchJIRAChangeItemException, SystemException {
		List<JIRAChangeItem> list = findByJiraChangeGroupId(jiraChangeGroupId,
				0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No JIRAChangeItem exists with the key {");

			msg.append("jiraChangeGroupId=" + jiraChangeGroupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAChangeItemException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAChangeItem findByJiraChangeGroupId_Last(long jiraChangeGroupId,
		OrderByComparator obc)
		throws NoSuchJIRAChangeItemException, SystemException {
		int count = countByJiraChangeGroupId(jiraChangeGroupId);

		List<JIRAChangeItem> list = findByJiraChangeGroupId(jiraChangeGroupId,
				count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No JIRAChangeItem exists with the key {");

			msg.append("jiraChangeGroupId=" + jiraChangeGroupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAChangeItemException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAChangeItem[] findByJiraChangeGroupId_PrevAndNext(
		long jiraChangeItemId, long jiraChangeGroupId, OrderByComparator obc)
		throws NoSuchJIRAChangeItemException, SystemException {
		JIRAChangeItem jiraChangeItem = findByPrimaryKey(jiraChangeItemId);

		int count = countByJiraChangeGroupId(jiraChangeGroupId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.wol.model.JIRAChangeItem WHERE ");

			query.append("groupid = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(jiraChangeGroupId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					jiraChangeItem);

			JIRAChangeItem[] array = new JIRAChangeItemImpl[3];

			array[0] = (JIRAChangeItem)objArray[0];
			array[1] = (JIRAChangeItem)objArray[1];
			array[2] = (JIRAChangeItem)objArray[2];

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

	public List<JIRAChangeItem> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<JIRAChangeItem> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<JIRAChangeItem> findAll(int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<JIRAChangeItem> list = (List<JIRAChangeItem>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wol.model.JIRAChangeItem ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				if (obc == null) {
					list = (List<JIRAChangeItem>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<JIRAChangeItem>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<JIRAChangeItem>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByJiraChangeGroupId(long jiraChangeGroupId)
		throws SystemException {
		for (JIRAChangeItem jiraChangeItem : findByJiraChangeGroupId(
				jiraChangeGroupId)) {
			remove(jiraChangeItem);
		}
	}

	public void removeAll() throws SystemException {
		for (JIRAChangeItem jiraChangeItem : findAll()) {
			remove(jiraChangeItem);
		}
	}

	public int countByJiraChangeGroupId(long jiraChangeGroupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(jiraChangeGroupId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_JIRACHANGEGROUPID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.wol.model.JIRAChangeItem WHERE ");

				query.append("groupid = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(jiraChangeGroupId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_JIRACHANGEGROUPID,
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

				Query q = session.createQuery(
						"SELECT COUNT(*) FROM com.liferay.wol.model.JIRAChangeItem");

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
						"value.object.listener.com.liferay.wol.model.JIRAChangeItem")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<JIRAChangeItem>> listenersList = new ArrayList<ModelListener<JIRAChangeItem>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<JIRAChangeItem>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(name = "com.liferay.wol.service.persistence.JIRAActionPersistence.impl")
	protected com.liferay.wol.service.persistence.JIRAActionPersistence jiraActionPersistence;
	@BeanReference(name = "com.liferay.wol.service.persistence.JIRAChangeGroupPersistence.impl")
	protected com.liferay.wol.service.persistence.JIRAChangeGroupPersistence jiraChangeGroupPersistence;
	@BeanReference(name = "com.liferay.wol.service.persistence.JIRAChangeItemPersistence.impl")
	protected com.liferay.wol.service.persistence.JIRAChangeItemPersistence jiraChangeItemPersistence;
	@BeanReference(name = "com.liferay.wol.service.persistence.JIRAIssuePersistence.impl")
	protected com.liferay.wol.service.persistence.JIRAIssuePersistence jiraIssuePersistence;
	@BeanReference(name = "com.liferay.wol.service.persistence.MeetupsEntryPersistence.impl")
	protected com.liferay.wol.service.persistence.MeetupsEntryPersistence meetupsEntryPersistence;
	@BeanReference(name = "com.liferay.wol.service.persistence.MeetupsRegistrationPersistence.impl")
	protected com.liferay.wol.service.persistence.MeetupsRegistrationPersistence meetupsRegistrationPersistence;
	@BeanReference(name = "com.liferay.wol.service.persistence.SVNRepositoryPersistence.impl")
	protected com.liferay.wol.service.persistence.SVNRepositoryPersistence svnRepositoryPersistence;
	@BeanReference(name = "com.liferay.wol.service.persistence.SVNRevisionPersistence.impl")
	protected com.liferay.wol.service.persistence.SVNRevisionPersistence svnRevisionPersistence;
	@BeanReference(name = "com.liferay.wol.service.persistence.WallEntryPersistence.impl")
	protected com.liferay.wol.service.persistence.WallEntryPersistence wallEntryPersistence;
	private static Log _log = LogFactoryUtil.getLog(JIRAChangeItemPersistenceImpl.class);
}