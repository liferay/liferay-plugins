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

import com.liferay.wol.NoSuchJIRAChangeGroupException;
import com.liferay.wol.model.JIRAChangeGroup;
import com.liferay.wol.model.impl.JIRAChangeGroupImpl;
import com.liferay.wol.model.impl.JIRAChangeGroupModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="JIRAChangeGroupPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JIRAChangeGroupPersistenceImpl extends BasePersistenceImpl
	implements JIRAChangeGroupPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = JIRAChangeGroupImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_JIRAUSERID = new FinderPath(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByJiraUserId",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_JIRAUSERID = new FinderPath(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByJiraUserId",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_JIRAUSERID = new FinderPath(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByJiraUserId",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_JIRAISSUEID = new FinderPath(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByJiraIssueId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_JIRAISSUEID = new FinderPath(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByJiraIssueId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_JIRAISSUEID = new FinderPath(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByJiraIssueId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	public void cacheResult(JIRAChangeGroup jiraChangeGroup) {
		EntityCacheUtil.putResult(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupImpl.class, jiraChangeGroup.getPrimaryKey(),
			jiraChangeGroup);
	}

	public void cacheResult(List<JIRAChangeGroup> jiraChangeGroups) {
		for (JIRAChangeGroup jiraChangeGroup : jiraChangeGroups) {
			if (EntityCacheUtil.getResult(
						JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
						JIRAChangeGroupImpl.class,
						jiraChangeGroup.getPrimaryKey(), this) == null) {
				cacheResult(jiraChangeGroup);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(JIRAChangeGroupImpl.class.getName());
		EntityCacheUtil.clearCache(JIRAChangeGroupImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public JIRAChangeGroup create(long jiraChangeGroupId) {
		JIRAChangeGroup jiraChangeGroup = new JIRAChangeGroupImpl();

		jiraChangeGroup.setNew(true);
		jiraChangeGroup.setPrimaryKey(jiraChangeGroupId);

		return jiraChangeGroup;
	}

	public JIRAChangeGroup remove(long jiraChangeGroupId)
		throws NoSuchJIRAChangeGroupException, SystemException {
		Session session = null;

		try {
			session = openSession();

			JIRAChangeGroup jiraChangeGroup = (JIRAChangeGroup)session.get(JIRAChangeGroupImpl.class,
					new Long(jiraChangeGroupId));

			if (jiraChangeGroup == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No JIRAChangeGroup exists with the primary key " +
						jiraChangeGroupId);
				}

				throw new NoSuchJIRAChangeGroupException(
					"No JIRAChangeGroup exists with the primary key " +
					jiraChangeGroupId);
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

	public JIRAChangeGroup remove(JIRAChangeGroup jiraChangeGroup)
		throws SystemException {
		for (ModelListener<JIRAChangeGroup> listener : listeners) {
			listener.onBeforeRemove(jiraChangeGroup);
		}

		jiraChangeGroup = removeImpl(jiraChangeGroup);

		for (ModelListener<JIRAChangeGroup> listener : listeners) {
			listener.onAfterRemove(jiraChangeGroup);
		}

		return jiraChangeGroup;
	}

	protected JIRAChangeGroup removeImpl(JIRAChangeGroup jiraChangeGroup)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (jiraChangeGroup.isCachedModel() ||
					BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(JIRAChangeGroupImpl.class,
						jiraChangeGroup.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(jiraChangeGroup);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupImpl.class, jiraChangeGroup.getPrimaryKey());

		return jiraChangeGroup;
	}

	public JIRAChangeGroup update(JIRAChangeGroup jiraChangeGroup)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(JIRAChangeGroup jiraChangeGroup) method. Use update(JIRAChangeGroup jiraChangeGroup, boolean merge) instead.");
		}

		return update(jiraChangeGroup, false);
	}

	public JIRAChangeGroup update(JIRAChangeGroup jiraChangeGroup, boolean merge)
		throws SystemException {
		boolean isNew = jiraChangeGroup.isNew();

		for (ModelListener<JIRAChangeGroup> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(jiraChangeGroup);
			}
			else {
				listener.onBeforeUpdate(jiraChangeGroup);
			}
		}

		jiraChangeGroup = updateImpl(jiraChangeGroup, merge);

		for (ModelListener<JIRAChangeGroup> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(jiraChangeGroup);
			}
			else {
				listener.onAfterUpdate(jiraChangeGroup);
			}
		}

		return jiraChangeGroup;
	}

	public JIRAChangeGroup updateImpl(
		com.liferay.wol.model.JIRAChangeGroup jiraChangeGroup, boolean merge)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, jiraChangeGroup, merge);

			jiraChangeGroup.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
			JIRAChangeGroupImpl.class, jiraChangeGroup.getPrimaryKey(),
			jiraChangeGroup);

		return jiraChangeGroup;
	}

	public JIRAChangeGroup findByPrimaryKey(long jiraChangeGroupId)
		throws NoSuchJIRAChangeGroupException, SystemException {
		JIRAChangeGroup jiraChangeGroup = fetchByPrimaryKey(jiraChangeGroupId);

		if (jiraChangeGroup == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No JIRAChangeGroup exists with the primary key " +
					jiraChangeGroupId);
			}

			throw new NoSuchJIRAChangeGroupException(
				"No JIRAChangeGroup exists with the primary key " +
				jiraChangeGroupId);
		}

		return jiraChangeGroup;
	}

	public JIRAChangeGroup fetchByPrimaryKey(long jiraChangeGroupId)
		throws SystemException {
		JIRAChangeGroup jiraChangeGroup = (JIRAChangeGroup)EntityCacheUtil.getResult(JIRAChangeGroupModelImpl.ENTITY_CACHE_ENABLED,
				JIRAChangeGroupImpl.class, jiraChangeGroupId, this);

		if (jiraChangeGroup == null) {
			Session session = null;

			try {
				session = openSession();

				jiraChangeGroup = (JIRAChangeGroup)session.get(JIRAChangeGroupImpl.class,
						new Long(jiraChangeGroupId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (jiraChangeGroup != null) {
					cacheResult(jiraChangeGroup);
				}

				closeSession(session);
			}
		}

		return jiraChangeGroup;
	}

	public List<JIRAChangeGroup> findByJiraUserId(String jiraUserId)
		throws SystemException {
		Object[] finderArgs = new Object[] { jiraUserId };

		List<JIRAChangeGroup> list = (List<JIRAChangeGroup>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_JIRAUSERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT jiraChangeGroup FROM JIRAChangeGroup jiraChangeGroup WHERE ");

				if (jiraUserId == null) {
					query.append("jiraChangeGroup.jiraUserId IS NULL");
				}
				else {
					query.append("jiraChangeGroup.jiraUserId = ?");
				}

				query.append(" ");

				query.append("ORDER BY ");

				query.append("jiraChangeGroup.createDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (jiraUserId != null) {
					qPos.add(jiraUserId);
				}

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<JIRAChangeGroup>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_JIRAUSERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<JIRAChangeGroup> findByJiraUserId(String jiraUserId, int start,
		int end) throws SystemException {
		return findByJiraUserId(jiraUserId, start, end, null);
	}

	public List<JIRAChangeGroup> findByJiraUserId(String jiraUserId, int start,
		int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				jiraUserId,
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<JIRAChangeGroup> list = (List<JIRAChangeGroup>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_JIRAUSERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT jiraChangeGroup FROM JIRAChangeGroup jiraChangeGroup WHERE ");

				if (jiraUserId == null) {
					query.append("jiraChangeGroup.jiraUserId IS NULL");
				}
				else {
					query.append("jiraChangeGroup.jiraUserId = ?");
				}

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("jiraChangeGroup.");
						query.append(orderByFields[i]);

						if (obc.isAscending()) {
							query.append(" ASC");
						}
						else {
							query.append(" DESC");
						}

						if ((i + 1) < orderByFields.length) {
							query.append(", ");
						}
					}
				}

				else {
					query.append("ORDER BY ");

					query.append("jiraChangeGroup.createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (jiraUserId != null) {
					qPos.add(jiraUserId);
				}

				list = (List<JIRAChangeGroup>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<JIRAChangeGroup>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_JIRAUSERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public JIRAChangeGroup findByJiraUserId_First(String jiraUserId,
		OrderByComparator obc)
		throws NoSuchJIRAChangeGroupException, SystemException {
		List<JIRAChangeGroup> list = findByJiraUserId(jiraUserId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No JIRAChangeGroup exists with the key {");

			msg.append("jiraUserId=" + jiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAChangeGroupException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAChangeGroup findByJiraUserId_Last(String jiraUserId,
		OrderByComparator obc)
		throws NoSuchJIRAChangeGroupException, SystemException {
		int count = countByJiraUserId(jiraUserId);

		List<JIRAChangeGroup> list = findByJiraUserId(jiraUserId, count - 1,
				count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No JIRAChangeGroup exists with the key {");

			msg.append("jiraUserId=" + jiraUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAChangeGroupException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAChangeGroup[] findByJiraUserId_PrevAndNext(
		long jiraChangeGroupId, String jiraUserId, OrderByComparator obc)
		throws NoSuchJIRAChangeGroupException, SystemException {
		JIRAChangeGroup jiraChangeGroup = findByPrimaryKey(jiraChangeGroupId);

		int count = countByJiraUserId(jiraUserId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT jiraChangeGroup FROM JIRAChangeGroup jiraChangeGroup WHERE ");

			if (jiraUserId == null) {
				query.append("jiraChangeGroup.jiraUserId IS NULL");
			}
			else {
				query.append("jiraChangeGroup.jiraUserId = ?");
			}

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("jiraChangeGroup.");
					query.append(orderByFields[i]);

					if (obc.isAscending()) {
						query.append(" ASC");
					}
					else {
						query.append(" DESC");
					}

					if ((i + 1) < orderByFields.length) {
						query.append(", ");
					}
				}
			}

			else {
				query.append("ORDER BY ");

				query.append("jiraChangeGroup.createDate DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			if (jiraUserId != null) {
				qPos.add(jiraUserId);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					jiraChangeGroup);

			JIRAChangeGroup[] array = new JIRAChangeGroupImpl[3];

			array[0] = (JIRAChangeGroup)objArray[0];
			array[1] = (JIRAChangeGroup)objArray[1];
			array[2] = (JIRAChangeGroup)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<JIRAChangeGroup> findByJiraIssueId(long jiraIssueId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(jiraIssueId) };

		List<JIRAChangeGroup> list = (List<JIRAChangeGroup>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_JIRAISSUEID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT jiraChangeGroup FROM JIRAChangeGroup jiraChangeGroup WHERE ");

				query.append("jiraChangeGroup.jiraIssueId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("jiraChangeGroup.createDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(jiraIssueId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<JIRAChangeGroup>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_JIRAISSUEID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<JIRAChangeGroup> findByJiraIssueId(long jiraIssueId, int start,
		int end) throws SystemException {
		return findByJiraIssueId(jiraIssueId, start, end, null);
	}

	public List<JIRAChangeGroup> findByJiraIssueId(long jiraIssueId, int start,
		int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(jiraIssueId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<JIRAChangeGroup> list = (List<JIRAChangeGroup>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_JIRAISSUEID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT jiraChangeGroup FROM JIRAChangeGroup jiraChangeGroup WHERE ");

				query.append("jiraChangeGroup.jiraIssueId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("jiraChangeGroup.");
						query.append(orderByFields[i]);

						if (obc.isAscending()) {
							query.append(" ASC");
						}
						else {
							query.append(" DESC");
						}

						if ((i + 1) < orderByFields.length) {
							query.append(", ");
						}
					}
				}

				else {
					query.append("ORDER BY ");

					query.append("jiraChangeGroup.createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(jiraIssueId);

				list = (List<JIRAChangeGroup>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<JIRAChangeGroup>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_JIRAISSUEID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public JIRAChangeGroup findByJiraIssueId_First(long jiraIssueId,
		OrderByComparator obc)
		throws NoSuchJIRAChangeGroupException, SystemException {
		List<JIRAChangeGroup> list = findByJiraIssueId(jiraIssueId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No JIRAChangeGroup exists with the key {");

			msg.append("jiraIssueId=" + jiraIssueId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAChangeGroupException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAChangeGroup findByJiraIssueId_Last(long jiraIssueId,
		OrderByComparator obc)
		throws NoSuchJIRAChangeGroupException, SystemException {
		int count = countByJiraIssueId(jiraIssueId);

		List<JIRAChangeGroup> list = findByJiraIssueId(jiraIssueId, count - 1,
				count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No JIRAChangeGroup exists with the key {");

			msg.append("jiraIssueId=" + jiraIssueId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchJIRAChangeGroupException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public JIRAChangeGroup[] findByJiraIssueId_PrevAndNext(
		long jiraChangeGroupId, long jiraIssueId, OrderByComparator obc)
		throws NoSuchJIRAChangeGroupException, SystemException {
		JIRAChangeGroup jiraChangeGroup = findByPrimaryKey(jiraChangeGroupId);

		int count = countByJiraIssueId(jiraIssueId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT jiraChangeGroup FROM JIRAChangeGroup jiraChangeGroup WHERE ");

			query.append("jiraChangeGroup.jiraIssueId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("jiraChangeGroup.");
					query.append(orderByFields[i]);

					if (obc.isAscending()) {
						query.append(" ASC");
					}
					else {
						query.append(" DESC");
					}

					if ((i + 1) < orderByFields.length) {
						query.append(", ");
					}
				}
			}

			else {
				query.append("ORDER BY ");

				query.append("jiraChangeGroup.createDate DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(jiraIssueId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					jiraChangeGroup);

			JIRAChangeGroup[] array = new JIRAChangeGroupImpl[3];

			array[0] = (JIRAChangeGroup)objArray[0];
			array[1] = (JIRAChangeGroup)objArray[1];
			array[2] = (JIRAChangeGroup)objArray[2];

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

	public List<JIRAChangeGroup> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<JIRAChangeGroup> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<JIRAChangeGroup> findAll(int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<JIRAChangeGroup> list = (List<JIRAChangeGroup>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT jiraChangeGroup FROM JIRAChangeGroup jiraChangeGroup ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("jiraChangeGroup.");
						query.append(orderByFields[i]);

						if (obc.isAscending()) {
							query.append(" ASC");
						}
						else {
							query.append(" DESC");
						}

						if ((i + 1) < orderByFields.length) {
							query.append(", ");
						}
					}
				}

				else {
					query.append("ORDER BY ");

					query.append("jiraChangeGroup.createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				if (obc == null) {
					list = (List<JIRAChangeGroup>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<JIRAChangeGroup>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<JIRAChangeGroup>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByJiraUserId(String jiraUserId) throws SystemException {
		for (JIRAChangeGroup jiraChangeGroup : findByJiraUserId(jiraUserId)) {
			remove(jiraChangeGroup);
		}
	}

	public void removeByJiraIssueId(long jiraIssueId) throws SystemException {
		for (JIRAChangeGroup jiraChangeGroup : findByJiraIssueId(jiraIssueId)) {
			remove(jiraChangeGroup);
		}
	}

	public void removeAll() throws SystemException {
		for (JIRAChangeGroup jiraChangeGroup : findAll()) {
			remove(jiraChangeGroup);
		}
	}

	public int countByJiraUserId(String jiraUserId) throws SystemException {
		Object[] finderArgs = new Object[] { jiraUserId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_JIRAUSERID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(jiraChangeGroup) ");
				query.append("FROM JIRAChangeGroup jiraChangeGroup WHERE ");

				if (jiraUserId == null) {
					query.append("jiraChangeGroup.jiraUserId IS NULL");
				}
				else {
					query.append("jiraChangeGroup.jiraUserId = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (jiraUserId != null) {
					qPos.add(jiraUserId);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_JIRAUSERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByJiraIssueId(long jiraIssueId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(jiraIssueId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_JIRAISSUEID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(jiraChangeGroup) ");
				query.append("FROM JIRAChangeGroup jiraChangeGroup WHERE ");

				query.append("jiraChangeGroup.jiraIssueId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(jiraIssueId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_JIRAISSUEID,
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
						"SELECT COUNT(jiraChangeGroup) FROM JIRAChangeGroup jiraChangeGroup");

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
						"value.object.listener.com.liferay.wol.model.JIRAChangeGroup")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<JIRAChangeGroup>> listenersList = new ArrayList<ModelListener<JIRAChangeGroup>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<JIRAChangeGroup>)Class.forName(
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
	private static Log _log = LogFactoryUtil.getLog(JIRAChangeGroupPersistenceImpl.class);
}