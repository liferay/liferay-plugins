/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.iweb.service.persistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.iweb.NoSuchSemanticsElementException;
import com.liferay.portal.iweb.model.SemanticsElement;
import com.liferay.portal.iweb.model.impl.SemanticsElementImpl;
import com.liferay.portal.iweb.model.impl.SemanticsElementModelImpl;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQuery;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.RowMapper;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="SemanticsElementPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SemanticsElementPersistenceImpl extends BasePersistenceImpl
	implements SemanticsElementPersistence {
	public SemanticsElement create(String elementURI) {
		SemanticsElement semanticsElement = new SemanticsElementImpl();

		semanticsElement.setNew(true);
		semanticsElement.setPrimaryKey(elementURI);

		return semanticsElement;
	}

	public SemanticsElement remove(String elementURI)
		throws NoSuchSemanticsElementException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SemanticsElement semanticsElement = (SemanticsElement)session.get(SemanticsElementImpl.class,
					elementURI);

			if (semanticsElement == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"No SemanticsElement exists with the primary key " +
						elementURI);
				}

				throw new NoSuchSemanticsElementException(
					"No SemanticsElement exists with the primary key " +
					elementURI);
			}

			return remove(semanticsElement);
		}
		catch (NoSuchSemanticsElementException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public SemanticsElement remove(SemanticsElement semanticsElement)
		throws SystemException {
		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onBeforeRemove(semanticsElement);
			}
		}

		semanticsElement = removeImpl(semanticsElement);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onAfterRemove(semanticsElement);
			}
		}

		return semanticsElement;
	}

	protected SemanticsElement removeImpl(SemanticsElement semanticsElement)
		throws SystemException {
		try {
			clearPosts.clear(semanticsElement.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Posts_SemanticsElements");
		}

		Session session = null;

		try {
			session = openSession();

			session.delete(semanticsElement);

			session.flush();

			return semanticsElement;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(SemanticsElement.class.getName());
		}
	}

	public SemanticsElement update(SemanticsElement semanticsElement)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(SemanticsElement semanticsElement) method. Use update(SemanticsElement semanticsElement, boolean merge) instead.");
		}

		return update(semanticsElement, false);
	}

	public SemanticsElement update(SemanticsElement semanticsElement,
		boolean merge) throws SystemException {
		boolean isNew = semanticsElement.isNew();

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onBeforeCreate(semanticsElement);
				}
				else {
					listener.onBeforeUpdate(semanticsElement);
				}
			}
		}

		semanticsElement = updateImpl(semanticsElement, merge);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onAfterCreate(semanticsElement);
				}
				else {
					listener.onAfterUpdate(semanticsElement);
				}
			}
		}

		return semanticsElement;
	}

	public SemanticsElement updateImpl(
		com.liferay.portal.iweb.model.SemanticsElement semanticsElement,
		boolean merge) throws SystemException {
		FinderCacheUtil.clearCache("Posts_SemanticsElements");

		Session session = null;

		try {
			session = openSession();

			if (merge) {
				session.merge(semanticsElement);
			}
			else {
				if (semanticsElement.isNew()) {
					session.save(semanticsElement);
				}
			}

			session.flush();

			semanticsElement.setNew(false);

			return semanticsElement;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(SemanticsElement.class.getName());
		}
	}

	public SemanticsElement findByPrimaryKey(String elementURI)
		throws NoSuchSemanticsElementException, SystemException {
		SemanticsElement semanticsElement = fetchByPrimaryKey(elementURI);

		if (semanticsElement == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No SemanticsElement exists with the primary key " +
					elementURI);
			}

			throw new NoSuchSemanticsElementException(
				"No SemanticsElement exists with the primary key " +
				elementURI);
		}

		return semanticsElement;
	}

	public SemanticsElement fetchByPrimaryKey(String elementURI)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (SemanticsElement)session.get(SemanticsElementImpl.class,
				elementURI);
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

	public List<SemanticsElement> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<SemanticsElement> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<SemanticsElement> findAll(int start, int end,
		OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = SemanticsElementModelImpl.CACHE_ENABLED;
		String finderClassName = SemanticsElement.class.getName();
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

				query.append(
					"FROM com.liferay.portal.iweb.model.SemanticsElement ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				List<SemanticsElement> list = (List<SemanticsElement>)QueryUtil.list(q,
						getDialect(), start, end);

				if (obc == null) {
					Collections.sort(list);
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
			return (List<SemanticsElement>)result;
		}
	}

	public void removeAll() throws SystemException {
		for (SemanticsElement semanticsElement : findAll()) {
			remove(semanticsElement);
		}
	}

	public int countAll() throws SystemException {
		boolean finderClassNameCacheEnabled = SemanticsElementModelImpl.CACHE_ENABLED;
		String finderClassName = SemanticsElement.class.getName();
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
						"SELECT COUNT(*) FROM com.liferay.portal.iweb.model.SemanticsElement");

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

	public List<com.liferay.portal.iweb.model.Post> getPosts(String pk)
		throws SystemException {
		return getPosts(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<com.liferay.portal.iweb.model.Post> getPosts(String pk,
		int start, int end) throws SystemException {
		return getPosts(pk, start, end, null);
	}

	public List<com.liferay.portal.iweb.model.Post> getPosts(String pk,
		int start, int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = SemanticsElementModelImpl.CACHE_ENABLED_POSTS_SEMANTICSELEMENTS;

		String finderClassName = "Posts_SemanticsElements";

		String finderMethodName = "getPosts";
		String[] finderParams = new String[] {
				String.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				pk, String.valueOf(start), String.valueOf(end),
				String.valueOf(obc)
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

				StringBuilder sb = new StringBuilder();

				sb.append(_SQL_GETPOSTS);

				if (obc != null) {
					sb.append("ORDER BY ");
					sb.append(obc.getOrderBy());
				}

				String sql = sb.toString();

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("IWEB_Post",
					com.liferay.portal.iweb.model.impl.PostImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				List<com.liferay.portal.iweb.model.Post> list = (List<com.liferay.portal.iweb.model.Post>)QueryUtil.list(q,
						getDialect(), start, end);

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
			return (List<com.liferay.portal.iweb.model.Post>)result;
		}
	}

	public int getPostsSize(String pk) throws SystemException {
		boolean finderClassNameCacheEnabled = SemanticsElementModelImpl.CACHE_ENABLED_POSTS_SEMANTICSELEMENTS;

		String finderClassName = "Posts_SemanticsElements";

		String finderMethodName = "getPostsSize";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { pk };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETPOSTSSIZE);

				q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

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

	public boolean containsPost(String pk, long postPK)
		throws SystemException {
		boolean finderClassNameCacheEnabled = SemanticsElementModelImpl.CACHE_ENABLED_POSTS_SEMANTICSELEMENTS;

		String finderClassName = "Posts_SemanticsElements";

		String finderMethodName = "containsPosts";
		String[] finderParams = new String[] {
				String.class.getName(),
				
				Long.class.getName()
			};
		Object[] finderArgs = new Object[] { pk, new Long(postPK) };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			try {
				Boolean value = Boolean.valueOf(containsPost.contains(pk, postPK));

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, value);

				return value.booleanValue();
			}
			catch (Exception e) {
				throw processException(e);
			}
		}
		else {
			return ((Boolean)result).booleanValue();
		}
	}

	public boolean containsPosts(String pk) throws SystemException {
		if (getPostsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public void addPost(String pk, long postPK) throws SystemException {
		try {
			addPost.add(pk, postPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Posts_SemanticsElements");
		}
	}

	public void addPost(String pk, com.liferay.portal.iweb.model.Post post)
		throws SystemException {
		try {
			addPost.add(pk, post.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Posts_SemanticsElements");
		}
	}

	public void addPosts(String pk, long[] postPKs) throws SystemException {
		try {
			for (long postPK : postPKs) {
				addPost.add(pk, postPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Posts_SemanticsElements");
		}
	}

	public void addPosts(String pk,
		List<com.liferay.portal.iweb.model.Post> posts)
		throws SystemException {
		try {
			for (com.liferay.portal.iweb.model.Post post : posts) {
				addPost.add(pk, post.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Posts_SemanticsElements");
		}
	}

	public void clearPosts(String pk) throws SystemException {
		try {
			clearPosts.clear(pk);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Posts_SemanticsElements");
		}
	}

	public void removePost(String pk, long postPK) throws SystemException {
		try {
			removePost.remove(pk, postPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Posts_SemanticsElements");
		}
	}

	public void removePost(String pk, com.liferay.portal.iweb.model.Post post)
		throws SystemException {
		try {
			removePost.remove(pk, post.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Posts_SemanticsElements");
		}
	}

	public void removePosts(String pk, long[] postPKs)
		throws SystemException {
		try {
			for (long postPK : postPKs) {
				removePost.remove(pk, postPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Posts_SemanticsElements");
		}
	}

	public void removePosts(String pk,
		List<com.liferay.portal.iweb.model.Post> posts)
		throws SystemException {
		try {
			for (com.liferay.portal.iweb.model.Post post : posts) {
				removePost.remove(pk, post.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Posts_SemanticsElements");
		}
	}

	public void setPosts(String pk, long[] postPKs) throws SystemException {
		try {
			clearPosts.clear(pk);

			for (long postPK : postPKs) {
				addPost.add(pk, postPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Posts_SemanticsElements");
		}
	}

	public void setPosts(String pk,
		List<com.liferay.portal.iweb.model.Post> posts)
		throws SystemException {
		try {
			clearPosts.clear(pk);

			for (com.liferay.portal.iweb.model.Post post : posts) {
				addPost.add(pk, post.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Posts_SemanticsElements");
		}
	}

	public void registerListener(ModelListener listener) {
		List<ModelListener> listeners = ListUtil.fromArray(_listeners);

		listeners.add(listener);

		_listeners = listeners.toArray(new ModelListener[listeners.size()]);
	}

	public void unregisterListener(ModelListener listener) {
		List<ModelListener> listeners = ListUtil.fromArray(_listeners);

		listeners.remove(listener);

		_listeners = listeners.toArray(new ModelListener[listeners.size()]);
	}

	protected void init() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.iweb.model.SemanticsElement")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener> listeners = new ArrayList<ModelListener>();

				for (String listenerClassName : listenerClassNames) {
					listeners.add((ModelListener)Class.forName(
							listenerClassName).newInstance());
				}

				_listeners = listeners.toArray(new ModelListener[listeners.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsPost = new ContainsPost(this);

		addPost = new AddPost(this);
		clearPosts = new ClearPosts(this);
		removePost = new RemovePost(this);
	}

	protected ContainsPost containsPost;
	protected AddPost addPost;
	protected ClearPosts clearPosts;
	protected RemovePost removePost;

	protected class ContainsPost {
		protected ContainsPost(SemanticsElementPersistenceImpl persistenceImpl) {
			super();

			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSPOST,
					new int[] { Types.VARCHAR, Types.BIGINT }, RowMapper.COUNT);
		}

		protected boolean contains(String elementURI, long uid) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						elementURI, new Long(uid)
					});

			if (results.size() > 0) {
				Integer count = results.get(0);

				if (count.intValue() > 0) {
					return true;
				}
			}

			return false;
		}

		private MappingSqlQuery _mappingSqlQuery;
	}

	protected class AddPost {
		protected AddPost(SemanticsElementPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO Posts_SemanticsElements (elementURI, uid) VALUES (?, ?)",
					new int[] { Types.VARCHAR, Types.BIGINT });
			_persistenceImpl = persistenceImpl;
		}

		protected void add(String elementURI, long uid) {
			if (!_persistenceImpl.containsPost.contains(elementURI, uid)) {
				_sqlUpdate.update(new Object[] { elementURI, new Long(uid) });
			}
		}

		private SqlUpdate _sqlUpdate;
		private SemanticsElementPersistenceImpl _persistenceImpl;
	}

	protected class ClearPosts {
		protected ClearPosts(SemanticsElementPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM Posts_SemanticsElements WHERE elementURI = ?",
					new int[] { Types.VARCHAR });
		}

		protected void clear(String elementURI) {
			_sqlUpdate.update(new Object[] { elementURI });
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemovePost {
		protected RemovePost(SemanticsElementPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM Posts_SemanticsElements WHERE elementURI = ? AND uid = ?",
					new int[] { Types.VARCHAR, Types.BIGINT });
		}

		protected void remove(String elementURI, long uid) {
			_sqlUpdate.update(new Object[] { elementURI, new Long(uid) });
		}

		private SqlUpdate _sqlUpdate;
	}

	private static final String _SQL_GETPOSTS = "SELECT {IWEB_Post.*} FROM IWEB_Post INNER JOIN Posts_SemanticsElements ON (Posts_SemanticsElements.uid = IWEB_Post.uid) WHERE (Posts_SemanticsElements.elementURI = ?)";
	private static final String _SQL_GETPOSTSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM Posts_SemanticsElements WHERE elementURI = ?";
	private static final String _SQL_CONTAINSPOST = "SELECT COUNT(*) AS COUNT_VALUE FROM Posts_SemanticsElements WHERE elementURI = ? AND uid = ?";
	private static Log _log = LogFactory.getLog(SemanticsElementPersistenceImpl.class);
	private ModelListener[] _listeners = new ModelListener[0];
}