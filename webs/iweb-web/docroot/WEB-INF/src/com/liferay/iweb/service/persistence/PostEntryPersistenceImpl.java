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

package com.liferay.iweb.service.persistence;

import com.liferay.iweb.NoSuchPostEntryException;
import com.liferay.iweb.model.PostEntry;
import com.liferay.iweb.model.impl.PostEntryImpl;
import com.liferay.iweb.model.impl.PostEntryModelImpl;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bean.InitializingBean;
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
 * <a href="PostEntryPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PostEntryPersistenceImpl extends BasePersistenceImpl
	implements PostEntryPersistence, InitializingBean {
	public PostEntry create(long uid) {
		PostEntry postEntry = new PostEntryImpl();

		postEntry.setNew(true);
		postEntry.setPrimaryKey(uid);

		return postEntry;
	}

	public PostEntry remove(long uid)
		throws NoSuchPostEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			PostEntry postEntry = (PostEntry)session.get(PostEntryImpl.class,
					new Long(uid));

			if (postEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No PostEntry exists with the primary key " +
						uid);
				}

				throw new NoSuchPostEntryException(
					"No PostEntry exists with the primary key " + uid);
			}

			return remove(postEntry);
		}
		catch (NoSuchPostEntryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public PostEntry remove(PostEntry postEntry) throws SystemException {
		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onBeforeRemove(postEntry);
			}
		}

		postEntry = removeImpl(postEntry);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onAfterRemove(postEntry);
			}
		}

		return postEntry;
	}

	protected PostEntry removeImpl(PostEntry postEntry)
		throws SystemException {
		try {
			clearSemanticsElements.clear(postEntry.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("PostEntries_SemanticsElements");
		}

		Session session = null;

		try {
			session = openSession();

			session.delete(postEntry);

			session.flush();

			return postEntry;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(PostEntry.class.getName());
		}
	}

	public PostEntry update(PostEntry postEntry) throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(PostEntry postEntry) method. Use update(PostEntry postEntry, boolean merge) instead.");
		}

		return update(postEntry, false);
	}

	public PostEntry update(PostEntry postEntry, boolean merge)
		throws SystemException {
		boolean isNew = postEntry.isNew();

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onBeforeCreate(postEntry);
				}
				else {
					listener.onBeforeUpdate(postEntry);
				}
			}
		}

		postEntry = updateImpl(postEntry, merge);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onAfterCreate(postEntry);
				}
				else {
					listener.onAfterUpdate(postEntry);
				}
			}
		}

		return postEntry;
	}

	public PostEntry updateImpl(com.liferay.iweb.model.PostEntry postEntry,
		boolean merge) throws SystemException {
		FinderCacheUtil.clearCache("PostEntries_SemanticsElements");

		Session session = null;

		try {
			session = openSession();

			if (merge) {
				session.merge(postEntry);
			}
			else {
				if (postEntry.isNew()) {
					session.save(postEntry);
				}
			}

			session.flush();

			postEntry.setNew(false);

			return postEntry;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(PostEntry.class.getName());
		}
	}

	public PostEntry findByPrimaryKey(long uid)
		throws NoSuchPostEntryException, SystemException {
		PostEntry postEntry = fetchByPrimaryKey(uid);

		if (postEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No PostEntry exists with the primary key " + uid);
			}

			throw new NoSuchPostEntryException(
				"No PostEntry exists with the primary key " + uid);
		}

		return postEntry;
	}

	public PostEntry fetchByPrimaryKey(long uid) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (PostEntry)session.get(PostEntryImpl.class, new Long(uid));
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public PostEntry findById_Type(long pid, String type)
		throws NoSuchPostEntryException, SystemException {
		PostEntry postEntry = fetchById_Type(pid, type);

		if (postEntry == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No PostEntry exists with the key {");

			msg.append("pid=" + pid);

			msg.append(", ");
			msg.append("type=" + type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchPostEntryException(msg.toString());
		}

		return postEntry;
	}

	public PostEntry fetchById_Type(long pid, String type)
		throws SystemException {
		boolean finderClassNameCacheEnabled = PostEntryModelImpl.CACHE_ENABLED;
		String finderClassName = PostEntry.class.getName();
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

				query.append("FROM com.liferay.iweb.model.PostEntry WHERE ");

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

				List<PostEntry> list = q.list();

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
			List<PostEntry> list = (List<PostEntry>)result;

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

	public List<PostEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<PostEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<PostEntry> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = PostEntryModelImpl.CACHE_ENABLED;
		String finderClassName = PostEntry.class.getName();
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

				query.append("FROM com.liferay.iweb.model.PostEntry ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				List<PostEntry> list = (List<PostEntry>)QueryUtil.list(q,
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
			return (List<PostEntry>)result;
		}
	}

	public void removeById_Type(long pid, String type)
		throws NoSuchPostEntryException, SystemException {
		PostEntry postEntry = findById_Type(pid, type);

		remove(postEntry);
	}

	public void removeAll() throws SystemException {
		for (PostEntry postEntry : findAll()) {
			remove(postEntry);
		}
	}

	public int countById_Type(long pid, String type) throws SystemException {
		boolean finderClassNameCacheEnabled = PostEntryModelImpl.CACHE_ENABLED;
		String finderClassName = PostEntry.class.getName();
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
				query.append("FROM com.liferay.iweb.model.PostEntry WHERE ");

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
		boolean finderClassNameCacheEnabled = PostEntryModelImpl.CACHE_ENABLED;
		String finderClassName = PostEntry.class.getName();
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
						"SELECT COUNT(*) FROM com.liferay.iweb.model.PostEntry");

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
		boolean finderClassNameCacheEnabled = PostEntryModelImpl.CACHE_ENABLED_POSTENTRIES_SEMANTICSELEMENTS;

		String finderClassName = "PostEntries_SemanticsElements";

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
		boolean finderClassNameCacheEnabled = PostEntryModelImpl.CACHE_ENABLED_POSTENTRIES_SEMANTICSELEMENTS;

		String finderClassName = "PostEntries_SemanticsElements";

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
		boolean finderClassNameCacheEnabled = PostEntryModelImpl.CACHE_ENABLED_POSTENTRIES_SEMANTICSELEMENTS;

		String finderClassName = "PostEntries_SemanticsElements";

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
			FinderCacheUtil.clearCache("PostEntries_SemanticsElements");
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
			FinderCacheUtil.clearCache("PostEntries_SemanticsElements");
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
			FinderCacheUtil.clearCache("PostEntries_SemanticsElements");
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
			FinderCacheUtil.clearCache("PostEntries_SemanticsElements");
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
			FinderCacheUtil.clearCache("PostEntries_SemanticsElements");
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
			FinderCacheUtil.clearCache("PostEntries_SemanticsElements");
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
			FinderCacheUtil.clearCache("PostEntries_SemanticsElements");
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
			FinderCacheUtil.clearCache("PostEntries_SemanticsElements");
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
			FinderCacheUtil.clearCache("PostEntries_SemanticsElements");
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
			FinderCacheUtil.clearCache("PostEntries_SemanticsElements");
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
			FinderCacheUtil.clearCache("PostEntries_SemanticsElements");
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

	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.iweb.model.PostEntry")));

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
		protected ContainsSemanticsElement(
			PostEntryPersistenceImpl persistenceImpl) {
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
		protected AddSemanticsElement(PostEntryPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO PostEntries_SemanticsElements (uid, elementURI) VALUES (?, ?)",
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
		private PostEntryPersistenceImpl _persistenceImpl;
	}

	protected class ClearSemanticsElements {
		protected ClearSemanticsElements(
			PostEntryPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM PostEntries_SemanticsElements WHERE uid = ?",
					new int[] { Types.BIGINT });
		}

		protected void clear(long uid) {
			_sqlUpdate.update(new Object[] { new Long(uid) });
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemoveSemanticsElement {
		protected RemoveSemanticsElement(
			PostEntryPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM PostEntries_SemanticsElements WHERE uid = ? AND elementURI = ?",
					new int[] { Types.BIGINT, Types.VARCHAR });
		}

		protected void remove(long uid, String elementURI) {
			_sqlUpdate.update(new Object[] { new Long(uid), elementURI });
		}

		private SqlUpdate _sqlUpdate;
	}

	private static final String _SQL_GETSEMANTICSELEMENTS = "SELECT {IWEB_SemanticsElement.*} FROM IWEB_SemanticsElement INNER JOIN PostEntries_SemanticsElements ON (PostEntries_SemanticsElements.elementURI = IWEB_SemanticsElement.elementURI) WHERE (PostEntries_SemanticsElements.uid = ?)";
	private static final String _SQL_GETSEMANTICSELEMENTSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM PostEntries_SemanticsElements WHERE uid = ?";
	private static final String _SQL_CONTAINSSEMANTICSELEMENT = "SELECT COUNT(*) AS COUNT_VALUE FROM PostEntries_SemanticsElements WHERE uid = ? AND elementURI = ?";
	private static Log _log = LogFactory.getLog(PostEntryPersistenceImpl.class);
	private ModelListener[] _listeners = new ModelListener[0];
}