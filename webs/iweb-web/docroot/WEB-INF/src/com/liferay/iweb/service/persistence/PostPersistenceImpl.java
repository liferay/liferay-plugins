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

package com.liferay.iweb.service.persistence;

import com.liferay.iweb.NoSuchPostException;
import com.liferay.iweb.model.Post;
import com.liferay.iweb.model.impl.PostImpl;
import com.liferay.iweb.model.impl.PostModelImpl;

import com.liferay.portal.SystemException;
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
import com.liferay.portal.kernel.util.StringPool;
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
 * <a href="PostPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PostPersistenceImpl extends BasePersistenceImpl
	implements PostPersistence {
	public Post create(long uid) {
		Post post = new PostImpl();

		post.setNew(true);
		post.setPrimaryKey(uid);

		return post;
	}

	public Post remove(long uid) throws NoSuchPostException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Post post = (Post)session.get(PostImpl.class, new Long(uid));

			if (post == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No Post exists with the primary key " + uid);
				}

				throw new NoSuchPostException(
					"No Post exists with the primary key " + uid);
			}

			return remove(post);
		}
		catch (NoSuchPostException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public Post remove(Post post) throws SystemException {
		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onBeforeRemove(post);
			}
		}

		post = removeImpl(post);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onAfterRemove(post);
			}
		}

		return post;
	}

	protected Post removeImpl(Post post) throws SystemException {
		try {
			clearSemanticsElements.clear(post.getPrimaryKey());
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

			session.delete(post);

			session.flush();

			return post;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(Post.class.getName());
		}
	}

	public Post update(Post post) throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(Post post) method. Use update(Post post, boolean merge) instead.");
		}

		return update(post, false);
	}

	public Post update(Post post, boolean merge) throws SystemException {
		boolean isNew = post.isNew();

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onBeforeCreate(post);
				}
				else {
					listener.onBeforeUpdate(post);
				}
			}
		}

		post = updateImpl(post, merge);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onAfterCreate(post);
				}
				else {
					listener.onAfterUpdate(post);
				}
			}
		}

		return post;
	}

	public Post updateImpl(com.liferay.iweb.model.Post post, boolean merge)
		throws SystemException {
		FinderCacheUtil.clearCache("Posts_SemanticsElements");

		Session session = null;

		try {
			session = openSession();

			if (merge) {
				session.merge(post);
			}
			else {
				if (post.isNew()) {
					session.save(post);
				}
			}

			session.flush();

			post.setNew(false);

			return post;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(Post.class.getName());
		}
	}

	public Post findByPrimaryKey(long uid)
		throws NoSuchPostException, SystemException {
		Post post = fetchByPrimaryKey(uid);

		if (post == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No Post exists with the primary key " + uid);
			}

			throw new NoSuchPostException(
				"No Post exists with the primary key " + uid);
		}

		return post;
	}

	public Post fetchByPrimaryKey(long uid) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (Post)session.get(PostImpl.class, new Long(uid));
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public Post findById_Type(long pid, String type)
		throws NoSuchPostException, SystemException {
		Post post = fetchById_Type(pid, type);

		if (post == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Post exists with the key {");

			msg.append("pid=" + pid);

			msg.append(", ");
			msg.append("type=" + type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchPostException(msg.toString());
		}

		return post;
	}

	public Post fetchById_Type(long pid, String type) throws SystemException {
		boolean finderClassNameCacheEnabled = PostModelImpl.CACHE_ENABLED;
		String finderClassName = Post.class.getName();
		String finderMethodName = "fetchById_Type";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName()
			};
		Object[] finderArgs = new Object[] { new Long(pid), type };

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

				query.append("FROM com.liferay.iweb.model.Post WHERE ");

				query.append("pid = ?");

				query.append(" AND ");

				if (type == null) {
					query.append("type_ IS NULL");
				}
				else {
					query.append("type_ = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pid);

				if (type != null) {
					qPos.add(type);
				}

				List<Post> list = q.list();

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				if (list.size() == 0) {
					return null;
				}
				else {
					return list.get(0);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			List<Post> list = (List<Post>)result;

			if (list.size() == 0) {
				return null;
			}
			else {
				return list.get(0);
			}
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

	public List<Post> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<Post> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	public List<Post> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = PostModelImpl.CACHE_ENABLED;
		String finderClassName = Post.class.getName();
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

				query.append("FROM com.liferay.iweb.model.Post ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				List<Post> list = (List<Post>)QueryUtil.list(q, getDialect(),
						start, end);

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
			return (List<Post>)result;
		}
	}

	public void removeById_Type(long pid, String type)
		throws NoSuchPostException, SystemException {
		Post post = findById_Type(pid, type);

		remove(post);
	}

	public void removeAll() throws SystemException {
		for (Post post : findAll()) {
			remove(post);
		}
	}

	public int countById_Type(long pid, String type) throws SystemException {
		boolean finderClassNameCacheEnabled = PostModelImpl.CACHE_ENABLED;
		String finderClassName = Post.class.getName();
		String finderMethodName = "countById_Type";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName()
			};
		Object[] finderArgs = new Object[] { new Long(pid), type };

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

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.iweb.model.Post WHERE ");

				query.append("pid = ?");

				query.append(" AND ");

				if (type == null) {
					query.append("type_ IS NULL");
				}
				else {
					query.append("type_ = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pid);

				if (type != null) {
					qPos.add(type);
				}

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

	public int countAll() throws SystemException {
		boolean finderClassNameCacheEnabled = PostModelImpl.CACHE_ENABLED;
		String finderClassName = Post.class.getName();
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
						"SELECT COUNT(*) FROM com.liferay.iweb.model.Post");

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

	public List<com.liferay.iweb.model.SemanticsElement> getSemanticsElements(
		long pk) throws SystemException {
		return getSemanticsElements(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<com.liferay.iweb.model.SemanticsElement> getSemanticsElements(
		long pk, int start, int end) throws SystemException {
		return getSemanticsElements(pk, start, end, null);
	}

	public List<com.liferay.iweb.model.SemanticsElement> getSemanticsElements(
		long pk, int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = PostModelImpl.CACHE_ENABLED_POSTS_SEMANTICSELEMENTS;

		String finderClassName = "Posts_SemanticsElements";

		String finderMethodName = "getSemanticsElements";
		String[] finderParams = new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(pk), String.valueOf(start), String.valueOf(end),
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

				sb.append(_SQL_GETSEMANTICSELEMENTS);

				if (obc != null) {
					sb.append("ORDER BY ");
					sb.append(obc.getOrderBy());
				}

				String sql = sb.toString();

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("IWEB_SemanticsElement",
					com.liferay.iweb.model.impl.SemanticsElementImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				List<com.liferay.iweb.model.SemanticsElement> list = (List<com.liferay.iweb.model.SemanticsElement>)QueryUtil.list(q,
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
			return (List<com.liferay.iweb.model.SemanticsElement>)result;
		}
	}

	public int getSemanticsElementsSize(long pk) throws SystemException {
		boolean finderClassNameCacheEnabled = PostModelImpl.CACHE_ENABLED_POSTS_SEMANTICSELEMENTS;

		String finderClassName = "Posts_SemanticsElements";

		String finderMethodName = "getSemanticsElementsSize";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(pk) };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETSEMANTICSELEMENTSSIZE);

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

	public boolean containsSemanticsElement(long pk, String semanticsElementPK)
		throws SystemException {
		boolean finderClassNameCacheEnabled = PostModelImpl.CACHE_ENABLED_POSTS_SEMANTICSELEMENTS;

		String finderClassName = "Posts_SemanticsElements";

		String finderMethodName = "containsSemanticsElements";
		String[] finderParams = new String[] {
				Long.class.getName(),
				
				String.class.getName()
			};
		Object[] finderArgs = new Object[] { new Long(pk), semanticsElementPK };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			try {
				Boolean value = Boolean.valueOf(containsSemanticsElement.contains(
							pk, semanticsElementPK));

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

	public boolean containsSemanticsElements(long pk) throws SystemException {
		if (getSemanticsElementsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public void addSemanticsElement(long pk, String semanticsElementPK)
		throws SystemException {
		try {
			addSemanticsElement.add(pk, semanticsElementPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Posts_SemanticsElements");
		}
	}

	public void addSemanticsElement(long pk,
		com.liferay.iweb.model.SemanticsElement semanticsElement)
		throws SystemException {
		try {
			addSemanticsElement.add(pk, semanticsElement.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Posts_SemanticsElements");
		}
	}

	public void addSemanticsElements(long pk, String[] semanticsElementPKs)
		throws SystemException {
		try {
			for (String semanticsElementPK : semanticsElementPKs) {
				addSemanticsElement.add(pk, semanticsElementPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Posts_SemanticsElements");
		}
	}

	public void addSemanticsElements(long pk,
		List<com.liferay.iweb.model.SemanticsElement> semanticsElements)
		throws SystemException {
		try {
			for (com.liferay.iweb.model.SemanticsElement semanticsElement : semanticsElements) {
				addSemanticsElement.add(pk, semanticsElement.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Posts_SemanticsElements");
		}
	}

	public void clearSemanticsElements(long pk) throws SystemException {
		try {
			clearSemanticsElements.clear(pk);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Posts_SemanticsElements");
		}
	}

	public void removeSemanticsElement(long pk, String semanticsElementPK)
		throws SystemException {
		try {
			removeSemanticsElement.remove(pk, semanticsElementPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Posts_SemanticsElements");
		}
	}

	public void removeSemanticsElement(long pk,
		com.liferay.iweb.model.SemanticsElement semanticsElement)
		throws SystemException {
		try {
			removeSemanticsElement.remove(pk, semanticsElement.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Posts_SemanticsElements");
		}
	}

	public void removeSemanticsElements(long pk, String[] semanticsElementPKs)
		throws SystemException {
		try {
			for (String semanticsElementPK : semanticsElementPKs) {
				removeSemanticsElement.remove(pk, semanticsElementPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Posts_SemanticsElements");
		}
	}

	public void removeSemanticsElements(long pk,
		List<com.liferay.iweb.model.SemanticsElement> semanticsElements)
		throws SystemException {
		try {
			for (com.liferay.iweb.model.SemanticsElement semanticsElement : semanticsElements) {
				removeSemanticsElement.remove(pk,
					semanticsElement.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Posts_SemanticsElements");
		}
	}

	public void setSemanticsElements(long pk, String[] semanticsElementPKs)
		throws SystemException {
		try {
			clearSemanticsElements.clear(pk);

			for (String semanticsElementPK : semanticsElementPKs) {
				addSemanticsElement.add(pk, semanticsElementPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("Posts_SemanticsElements");
		}
	}

	public void setSemanticsElements(long pk,
		List<com.liferay.iweb.model.SemanticsElement> semanticsElements)
		throws SystemException {
		try {
			clearSemanticsElements.clear(pk);

			for (com.liferay.iweb.model.SemanticsElement semanticsElement : semanticsElements) {
				addSemanticsElement.add(pk, semanticsElement.getPrimaryKey());
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
						"value.object.listener.com.liferay.iweb.model.Post")));

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

		containsSemanticsElement = new ContainsSemanticsElement(this);

		addSemanticsElement = new AddSemanticsElement(this);
		clearSemanticsElements = new ClearSemanticsElements(this);
		removeSemanticsElement = new RemoveSemanticsElement(this);
	}

	protected ContainsSemanticsElement containsSemanticsElement;
	protected AddSemanticsElement addSemanticsElement;
	protected ClearSemanticsElements clearSemanticsElements;
	protected RemoveSemanticsElement removeSemanticsElement;

	protected class ContainsSemanticsElement {
		protected ContainsSemanticsElement(PostPersistenceImpl persistenceImpl) {
			super();

			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSSEMANTICSELEMENT,
					new int[] { Types.BIGINT, Types.VARCHAR }, RowMapper.COUNT);
		}

		protected boolean contains(long uid, String elementURI) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(uid), elementURI
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

	protected class AddSemanticsElement {
		protected AddSemanticsElement(PostPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO Posts_SemanticsElements (uid, elementURI) VALUES (?, ?)",
					new int[] { Types.BIGINT, Types.VARCHAR });
			_persistenceImpl = persistenceImpl;
		}

		protected void add(long uid, String elementURI) {
			if (!_persistenceImpl.containsSemanticsElement.contains(uid,
						elementURI)) {
				_sqlUpdate.update(new Object[] { new Long(uid), elementURI });
			}
		}

		private SqlUpdate _sqlUpdate;
		private PostPersistenceImpl _persistenceImpl;
	}

	protected class ClearSemanticsElements {
		protected ClearSemanticsElements(PostPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM Posts_SemanticsElements WHERE uid = ?",
					new int[] { Types.BIGINT });
		}

		protected void clear(long uid) {
			_sqlUpdate.update(new Object[] { new Long(uid) });
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemoveSemanticsElement {
		protected RemoveSemanticsElement(PostPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM Posts_SemanticsElements WHERE uid = ? AND elementURI = ?",
					new int[] { Types.BIGINT, Types.VARCHAR });
		}

		protected void remove(long uid, String elementURI) {
			_sqlUpdate.update(new Object[] { new Long(uid), elementURI });
		}

		private SqlUpdate _sqlUpdate;
	}

	private static final String _SQL_GETSEMANTICSELEMENTS = "SELECT {IWEB_SemanticsElement.*} FROM IWEB_SemanticsElement INNER JOIN Posts_SemanticsElements ON (Posts_SemanticsElements.elementURI = IWEB_SemanticsElement.elementURI) WHERE (Posts_SemanticsElements.uid = ?)";
	private static final String _SQL_GETSEMANTICSELEMENTSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM Posts_SemanticsElements WHERE uid = ?";
	private static final String _SQL_CONTAINSSEMANTICSELEMENT = "SELECT COUNT(*) AS COUNT_VALUE FROM Posts_SemanticsElements WHERE uid = ? AND elementURI = ?";
	private static Log _log = LogFactory.getLog(PostPersistenceImpl.class);
	private ModelListener[] _listeners = new ModelListener[0];
}