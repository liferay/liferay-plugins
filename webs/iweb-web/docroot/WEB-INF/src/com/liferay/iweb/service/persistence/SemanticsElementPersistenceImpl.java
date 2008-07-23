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

import com.liferay.iweb.NoSuchSemanticsElementException;
import com.liferay.iweb.model.SemanticsElement;
import com.liferay.iweb.model.impl.SemanticsElementImpl;
import com.liferay.iweb.model.impl.SemanticsElementModelImpl;

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
			clearPostEntries.clear(semanticsElement.getPrimaryKey());
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
		com.liferay.iweb.model.SemanticsElement semanticsElement, boolean merge)
		throws SystemException {
		FinderCacheUtil.clearCache("PostEntries_SemanticsElements");

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

				query.append("FROM com.liferay.iweb.model.SemanticsElement ");

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
						"SELECT COUNT(*) FROM com.liferay.iweb.model.SemanticsElement");

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

	public List<com.liferay.iweb.model.PostEntry> getPostEntries(String pk)
		throws SystemException {
		return getPostEntries(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<com.liferay.iweb.model.PostEntry> getPostEntries(String pk,
		int start, int end) throws SystemException {
		return getPostEntries(pk, start, end, null);
	}

	public List<com.liferay.iweb.model.PostEntry> getPostEntries(String pk,
		int start, int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = SemanticsElementModelImpl.CACHE_ENABLED_POSTENTRIES_SEMANTICSELEMENTS;

		String finderClassName = "PostEntries_SemanticsElements";

		String finderMethodName = "getPostEntries";
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

				sb.append(_SQL_GETPOSTENTRIES);

				if (obc != null) {
					sb.append("ORDER BY ");
					sb.append(obc.getOrderBy());
				}

				String sql = sb.toString();

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("IWEB_PostEntry",
					com.liferay.iweb.model.impl.PostEntryImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				List<com.liferay.iweb.model.PostEntry> list = (List<com.liferay.iweb.model.PostEntry>)QueryUtil.list(q,
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
			return (List<com.liferay.iweb.model.PostEntry>)result;
		}
	}

	public int getPostEntriesSize(String pk) throws SystemException {
		boolean finderClassNameCacheEnabled = SemanticsElementModelImpl.CACHE_ENABLED_POSTENTRIES_SEMANTICSELEMENTS;

		String finderClassName = "PostEntries_SemanticsElements";

		String finderMethodName = "getPostEntriesSize";
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

				SQLQuery q = session.createSQLQuery(_SQL_GETPOSTENTRIESSIZE);

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

	public boolean containsPostEntry(String pk, long postEntryPK)
		throws SystemException {
		boolean finderClassNameCacheEnabled = SemanticsElementModelImpl.CACHE_ENABLED_POSTENTRIES_SEMANTICSELEMENTS;

		String finderClassName = "PostEntries_SemanticsElements";

		String finderMethodName = "containsPostEntries";
		String[] finderParams = new String[] {
				String.class.getName(),
				
				Long.class.getName()
			};
		Object[] finderArgs = new Object[] { pk, new Long(postEntryPK) };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			try {
				Boolean value = Boolean.valueOf(containsPostEntry.contains(pk,
							postEntryPK));

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

	public boolean containsPostEntries(String pk) throws SystemException {
		if (getPostEntriesSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public void addPostEntry(String pk, long postEntryPK)
		throws SystemException {
		try {
			addPostEntry.add(pk, postEntryPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("PostEntries_SemanticsElements");
		}
	}

	public void addPostEntry(String pk,
		com.liferay.iweb.model.PostEntry postEntry) throws SystemException {
		try {
			addPostEntry.add(pk, postEntry.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("PostEntries_SemanticsElements");
		}
	}

	public void addPostEntries(String pk, long[] postEntryPKs)
		throws SystemException {
		try {
			for (long postEntryPK : postEntryPKs) {
				addPostEntry.add(pk, postEntryPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("PostEntries_SemanticsElements");
		}
	}

	public void addPostEntries(String pk,
		List<com.liferay.iweb.model.PostEntry> postEntries)
		throws SystemException {
		try {
			for (com.liferay.iweb.model.PostEntry postEntry : postEntries) {
				addPostEntry.add(pk, postEntry.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("PostEntries_SemanticsElements");
		}
	}

	public void clearPostEntries(String pk) throws SystemException {
		try {
			clearPostEntries.clear(pk);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("PostEntries_SemanticsElements");
		}
	}

	public void removePostEntry(String pk, long postEntryPK)
		throws SystemException {
		try {
			removePostEntry.remove(pk, postEntryPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("PostEntries_SemanticsElements");
		}
	}

	public void removePostEntry(String pk,
		com.liferay.iweb.model.PostEntry postEntry) throws SystemException {
		try {
			removePostEntry.remove(pk, postEntry.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("PostEntries_SemanticsElements");
		}
	}

	public void removePostEntries(String pk, long[] postEntryPKs)
		throws SystemException {
		try {
			for (long postEntryPK : postEntryPKs) {
				removePostEntry.remove(pk, postEntryPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("PostEntries_SemanticsElements");
		}
	}

	public void removePostEntries(String pk,
		List<com.liferay.iweb.model.PostEntry> postEntries)
		throws SystemException {
		try {
			for (com.liferay.iweb.model.PostEntry postEntry : postEntries) {
				removePostEntry.remove(pk, postEntry.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("PostEntries_SemanticsElements");
		}
	}

	public void setPostEntries(String pk, long[] postEntryPKs)
		throws SystemException {
		try {
			clearPostEntries.clear(pk);

			for (long postEntryPK : postEntryPKs) {
				addPostEntry.add(pk, postEntryPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache("PostEntries_SemanticsElements");
		}
	}

	public void setPostEntries(String pk,
		List<com.liferay.iweb.model.PostEntry> postEntries)
		throws SystemException {
		try {
			clearPostEntries.clear(pk);

			for (com.liferay.iweb.model.PostEntry postEntry : postEntries) {
				addPostEntry.add(pk, postEntry.getPrimaryKey());
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

	protected void init() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.iweb.model.SemanticsElement")));

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

		containsPostEntry = new ContainsPostEntry(this);

		addPostEntry = new AddPostEntry(this);
		clearPostEntries = new ClearPostEntries(this);
		removePostEntry = new RemovePostEntry(this);
	}

	protected ContainsPostEntry containsPostEntry;
	protected AddPostEntry addPostEntry;
	protected ClearPostEntries clearPostEntries;
	protected RemovePostEntry removePostEntry;

	protected class ContainsPostEntry {
		protected ContainsPostEntry(
			SemanticsElementPersistenceImpl persistenceImpl) {
			super();

			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSPOSTENTRY,
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

	protected class AddPostEntry {
		protected AddPostEntry(SemanticsElementPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO PostEntries_SemanticsElements (elementURI, uid) VALUES (?, ?)",
					new int[] { Types.VARCHAR, Types.BIGINT });
			_persistenceImpl = persistenceImpl;
		}

		protected void add(String elementURI, long uid) {
			if (!_persistenceImpl.containsPostEntry.contains(elementURI, uid)) {
				_sqlUpdate.update(new Object[] { elementURI, new Long(uid) });
			}
		}

		private SqlUpdate _sqlUpdate;
		private SemanticsElementPersistenceImpl _persistenceImpl;
	}

	protected class ClearPostEntries {
		protected ClearPostEntries(
			SemanticsElementPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM PostEntries_SemanticsElements WHERE elementURI = ?",
					new int[] { Types.VARCHAR });
		}

		protected void clear(String elementURI) {
			_sqlUpdate.update(new Object[] { elementURI });
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemovePostEntry {
		protected RemovePostEntry(
			SemanticsElementPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM PostEntries_SemanticsElements WHERE elementURI = ? AND uid = ?",
					new int[] { Types.VARCHAR, Types.BIGINT });
		}

		protected void remove(String elementURI, long uid) {
			_sqlUpdate.update(new Object[] { elementURI, new Long(uid) });
		}

		private SqlUpdate _sqlUpdate;
	}

	private static final String _SQL_GETPOSTENTRIES = "SELECT {IWEB_PostEntry.*} FROM IWEB_PostEntry INNER JOIN PostEntries_SemanticsElements ON (PostEntries_SemanticsElements.uid = IWEB_PostEntry.uid) WHERE (PostEntries_SemanticsElements.elementURI = ?)";
	private static final String _SQL_GETPOSTENTRIESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM PostEntries_SemanticsElements WHERE elementURI = ?";
	private static final String _SQL_CONTAINSPOSTENTRY = "SELECT COUNT(*) AS COUNT_VALUE FROM PostEntries_SemanticsElements WHERE elementURI = ? AND uid = ?";
	private static Log _log = LogFactory.getLog(SemanticsElementPersistenceImpl.class);
	private ModelListener[] _listeners = new ModelListener[0];
}