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

package com.liferay.sample.service.persistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.DynamicQuery;
import com.liferay.portal.kernel.dao.DynamicQueryInitializer;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;

import com.liferay.portlet.service.BasePersistence;
import com.liferay.portlet.service.FinderCache;
import com.liferay.portlet.service.HibernateUtil;
import com.liferay.portlet.service.PropsUtil;

import com.liferay.sample.NoSuchNotepadEntryException;
import com.liferay.sample.model.NotepadEntry;
import com.liferay.sample.model.impl.NotepadEntryImpl;
import com.liferay.sample.model.impl.NotepadEntryModelImpl;

import com.liferay.util.dao.hibernate.QueryUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="NotepadEntryPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NotepadEntryPersistenceImpl extends BasePersistence
	implements NotepadEntryPersistence {
	public NotepadEntry create(long notepadEntryId) {
		NotepadEntry notepadEntry = new NotepadEntryImpl();

		notepadEntry.setNew(true);
		notepadEntry.setPrimaryKey(notepadEntryId);

		return notepadEntry;
	}

	public NotepadEntry remove(long notepadEntryId)
		throws NoSuchNotepadEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			NotepadEntry notepadEntry = (NotepadEntry)session.get(NotepadEntryImpl.class,
					new Long(notepadEntryId));

			if (notepadEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No NotepadEntry exists with the primary key " +
						notepadEntryId);
				}

				throw new NoSuchNotepadEntryException(
					"No NotepadEntry exists with the primary key " +
					notepadEntryId);
			}

			return remove(notepadEntry);
		}
		catch (NoSuchNotepadEntryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public NotepadEntry remove(NotepadEntry notepadEntry)
		throws SystemException {
		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onBeforeRemove(notepadEntry);
			}
		}

		notepadEntry = removeImpl(notepadEntry);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onAfterRemove(notepadEntry);
			}
		}

		return notepadEntry;
	}

	protected NotepadEntry removeImpl(NotepadEntry notepadEntry)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			session.delete(notepadEntry);

			session.flush();

			return notepadEntry;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);

			FinderCache.clearCache(NotepadEntry.class.getName());
		}
	}

	public NotepadEntry update(NotepadEntry notepadEntry)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(NotepadEntry notepadEntry) method. Use update(NotepadEntry notepadEntry, boolean merge) instead.");
		}

		return update(notepadEntry, false);
	}

	public NotepadEntry update(NotepadEntry notepadEntry, boolean merge)
		throws SystemException {
		boolean isNew = notepadEntry.isNew();

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onBeforeCreate(notepadEntry);
				}
				else {
					listener.onBeforeUpdate(notepadEntry);
				}
			}
		}

		notepadEntry = updateImpl(notepadEntry, merge);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onAfterCreate(notepadEntry);
				}
				else {
					listener.onAfterUpdate(notepadEntry);
				}
			}
		}

		return notepadEntry;
	}

	public NotepadEntry updateImpl(
		com.liferay.sample.model.NotepadEntry notepadEntry, boolean merge)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (merge) {
				session.merge(notepadEntry);
			}
			else {
				if (notepadEntry.isNew()) {
					session.save(notepadEntry);
				}
			}

			session.flush();

			notepadEntry.setNew(false);

			return notepadEntry;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);

			FinderCache.clearCache(NotepadEntry.class.getName());
		}
	}

	public NotepadEntry findByPrimaryKey(long notepadEntryId)
		throws NoSuchNotepadEntryException, SystemException {
		NotepadEntry notepadEntry = fetchByPrimaryKey(notepadEntryId);

		if (notepadEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No NotepadEntry exists with the primary key " +
					notepadEntryId);
			}

			throw new NoSuchNotepadEntryException(
				"No NotepadEntry exists with the primary key " +
				notepadEntryId);
		}

		return notepadEntry;
	}

	public NotepadEntry fetchByPrimaryKey(long notepadEntryId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (NotepadEntry)session.get(NotepadEntryImpl.class,
				new Long(notepadEntryId));
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<NotepadEntry> findWithDynamicQuery(
		DynamicQueryInitializer queryInitializer) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			DynamicQuery query = queryInitializer.initialize(session);

			return query.list();
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<NotepadEntry> findWithDynamicQuery(
		DynamicQueryInitializer queryInitializer, int start, int end)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			DynamicQuery query = queryInitializer.initialize(session);

			query.setLimit(start, end);

			return query.list();
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<NotepadEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<NotepadEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<NotepadEntry> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = NotepadEntryModelImpl.CACHE_ENABLED;
		String finderClassName = NotepadEntry.class.getName();
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
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.sample.model.NotepadEntry ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				List<NotepadEntry> list = (List<NotepadEntry>)QueryUtil.list(q,
						getDialect(), start, end);

				if (obc == null) {
					Collections.sort(list);
				}

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<NotepadEntry>)result;
		}
	}

	public void removeAll() throws SystemException {
		for (NotepadEntry notepadEntry : findAll()) {
			remove(notepadEntry);
		}
	}

	public int countAll() throws SystemException {
		boolean finderClassNameCacheEnabled = NotepadEntryModelImpl.CACHE_ENABLED;
		String finderClassName = NotepadEntry.class.getName();
		String finderMethodName = "countAll";
		String[] finderParams = new String[] {  };
		Object[] finderArgs = new Object[] {  };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(
						"SELECT COUNT(*) FROM com.liferay.sample.model.NotepadEntry");

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return ((Long)result).intValue();
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

	protected void initDao() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					PropsUtil.get(
						"value.object.listener.com.liferay.sample.model.NotepadEntry")));

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
	}

	private static Log _log = LogFactory.getLog(NotepadEntryPersistenceImpl.class);
	private ModelListener[] _listeners = new ModelListener[0];
}