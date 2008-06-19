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

package com.liferay.chat.service.persistence;

import com.liferay.chat.NoSuchEntryException;
import com.liferay.chat.model.Entry;
import com.liferay.chat.model.impl.EntryImpl;
import com.liferay.chat.model.impl.EntryModelImpl;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.DynamicQuery;
import com.liferay.portal.kernel.dao.DynamicQueryInitializer;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringMaker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;

import com.liferay.portlet.service.BasePersistence;
import com.liferay.portlet.service.FinderCache;
import com.liferay.portlet.service.HibernateUtil;
import com.liferay.portlet.service.PropsUtil;

import com.liferay.util.dao.hibernate.QueryPos;
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
 * <a href="EntryPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class EntryPersistenceImpl extends BasePersistence
	implements EntryPersistence {
	public Entry create(long entryId) {
		Entry entry = new EntryImpl();

		entry.setNew(true);
		entry.setPrimaryKey(entryId);

		return entry;
	}

	public Entry remove(long entryId)
		throws NoSuchEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Entry entry = (Entry)session.get(EntryImpl.class, new Long(entryId));

			if (entry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No Entry exists with the primary key " +
						entryId);
				}

				throw new NoSuchEntryException(
					"No Entry exists with the primary key " + entryId);
			}

			return remove(entry);
		}
		catch (NoSuchEntryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public Entry remove(Entry entry) throws SystemException {
		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onBeforeRemove(entry);
			}
		}

		entry = removeImpl(entry);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onAfterRemove(entry);
			}
		}

		return entry;
	}

	protected Entry removeImpl(Entry entry) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			session.delete(entry);

			session.flush();

			return entry;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);

			FinderCache.clearCache(Entry.class.getName());
		}
	}

	public Entry update(Entry entry) throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(Entry entry) method. Use update(Entry entry, boolean merge) instead.");
		}

		return update(entry, false);
	}

	public Entry update(Entry entry, boolean merge) throws SystemException {
		boolean isNew = entry.isNew();

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onBeforeCreate(entry);
				}
				else {
					listener.onBeforeUpdate(entry);
				}
			}
		}

		entry = updateImpl(entry, merge);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onAfterCreate(entry);
				}
				else {
					listener.onAfterUpdate(entry);
				}
			}
		}

		return entry;
	}

	public Entry updateImpl(com.liferay.chat.model.Entry entry, boolean merge)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (merge) {
				session.merge(entry);
			}
			else {
				if (entry.isNew()) {
					session.save(entry);
				}
			}

			session.flush();

			entry.setNew(false);

			return entry;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);

			FinderCache.clearCache(Entry.class.getName());
		}
	}

	public Entry findByPrimaryKey(long entryId)
		throws NoSuchEntryException, SystemException {
		Entry entry = fetchByPrimaryKey(entryId);

		if (entry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No Entry exists with the primary key " + entryId);
			}

			throw new NoSuchEntryException(
				"No Entry exists with the primary key " + entryId);
		}

		return entry;
	}

	public Entry fetchByPrimaryKey(long entryId) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (Entry)session.get(EntryImpl.class, new Long(entryId));
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Entry> findByCreateDate(long createDate)
		throws SystemException {
		boolean finderClassNameCacheEnabled = EntryModelImpl.CACHE_ENABLED;
		String finderClassName = Entry.class.getName();
		String finderMethodName = "findByCreateDate";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(createDate) };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("createDate = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("createDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				List<Entry> list = q.list();

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
			return (List<Entry>)result;
		}
	}

	public List<Entry> findByCreateDate(long createDate, int start, int end)
		throws SystemException {
		return findByCreateDate(createDate, start, end, null);
	}

	public List<Entry> findByCreateDate(long createDate, int start, int end,
		OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = EntryModelImpl.CACHE_ENABLED;
		String finderClassName = Entry.class.getName();
		String finderMethodName = "findByCreateDate";
		String[] finderParams = new String[] {
				Long.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(createDate),
				
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

				StringMaker query = new StringMaker();

				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("createDate = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

				List<Entry> list = (List<Entry>)QueryUtil.list(q, getDialect(),
						start, end);

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
			return (List<Entry>)result;
		}
	}

	public Entry findByCreateDate_First(long createDate, OrderByComparator obc)
		throws NoSuchEntryException, SystemException {
		List<Entry> list = findByCreateDate(createDate, 0, 1, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();

			msg.append("No Entry exists with the key {");

			msg.append("createDate=" + createDate);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Entry findByCreateDate_Last(long createDate, OrderByComparator obc)
		throws NoSuchEntryException, SystemException {
		int count = countByCreateDate(createDate);

		List<Entry> list = findByCreateDate(createDate, count - 1, count, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();

			msg.append("No Entry exists with the key {");

			msg.append("createDate=" + createDate);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Entry[] findByCreateDate_PrevAndNext(long entryId, long createDate,
		OrderByComparator obc) throws NoSuchEntryException, SystemException {
		Entry entry = findByPrimaryKey(entryId);

		int count = countByCreateDate(createDate);

		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();

			query.append("FROM com.liferay.chat.model.Entry WHERE ");

			query.append("createDate = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("createDate DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(createDate);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, entry);

			Entry[] array = new EntryImpl[3];

			array[0] = (Entry)objArray[0];
			array[1] = (Entry)objArray[1];
			array[2] = (Entry)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Entry> findByFromUserId(long fromUserId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = EntryModelImpl.CACHE_ENABLED;
		String finderClassName = Entry.class.getName();
		String finderMethodName = "findByFromUserId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(fromUserId) };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("fromUserId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("createDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fromUserId);

				List<Entry> list = q.list();

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
			return (List<Entry>)result;
		}
	}

	public List<Entry> findByFromUserId(long fromUserId, int start, int end)
		throws SystemException {
		return findByFromUserId(fromUserId, start, end, null);
	}

	public List<Entry> findByFromUserId(long fromUserId, int start, int end,
		OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = EntryModelImpl.CACHE_ENABLED;
		String finderClassName = Entry.class.getName();
		String finderMethodName = "findByFromUserId";
		String[] finderParams = new String[] {
				Long.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(fromUserId),
				
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

				StringMaker query = new StringMaker();

				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("fromUserId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fromUserId);

				List<Entry> list = (List<Entry>)QueryUtil.list(q, getDialect(),
						start, end);

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
			return (List<Entry>)result;
		}
	}

	public Entry findByFromUserId_First(long fromUserId, OrderByComparator obc)
		throws NoSuchEntryException, SystemException {
		List<Entry> list = findByFromUserId(fromUserId, 0, 1, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();

			msg.append("No Entry exists with the key {");

			msg.append("fromUserId=" + fromUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Entry findByFromUserId_Last(long fromUserId, OrderByComparator obc)
		throws NoSuchEntryException, SystemException {
		int count = countByFromUserId(fromUserId);

		List<Entry> list = findByFromUserId(fromUserId, count - 1, count, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();

			msg.append("No Entry exists with the key {");

			msg.append("fromUserId=" + fromUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Entry[] findByFromUserId_PrevAndNext(long entryId, long fromUserId,
		OrderByComparator obc) throws NoSuchEntryException, SystemException {
		Entry entry = findByPrimaryKey(entryId);

		int count = countByFromUserId(fromUserId);

		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();

			query.append("FROM com.liferay.chat.model.Entry WHERE ");

			query.append("fromUserId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("createDate DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(fromUserId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, entry);

			Entry[] array = new EntryImpl[3];

			array[0] = (Entry)objArray[0];
			array[1] = (Entry)objArray[1];
			array[2] = (Entry)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Entry> findByToUserId(long toUserId) throws SystemException {
		boolean finderClassNameCacheEnabled = EntryModelImpl.CACHE_ENABLED;
		String finderClassName = Entry.class.getName();
		String finderMethodName = "findByToUserId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(toUserId) };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("toUserId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("createDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(toUserId);

				List<Entry> list = q.list();

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
			return (List<Entry>)result;
		}
	}

	public List<Entry> findByToUserId(long toUserId, int start, int end)
		throws SystemException {
		return findByToUserId(toUserId, start, end, null);
	}

	public List<Entry> findByToUserId(long toUserId, int start, int end,
		OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = EntryModelImpl.CACHE_ENABLED;
		String finderClassName = Entry.class.getName();
		String finderMethodName = "findByToUserId";
		String[] finderParams = new String[] {
				Long.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(toUserId),
				
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

				StringMaker query = new StringMaker();

				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("toUserId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(toUserId);

				List<Entry> list = (List<Entry>)QueryUtil.list(q, getDialect(),
						start, end);

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
			return (List<Entry>)result;
		}
	}

	public Entry findByToUserId_First(long toUserId, OrderByComparator obc)
		throws NoSuchEntryException, SystemException {
		List<Entry> list = findByToUserId(toUserId, 0, 1, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();

			msg.append("No Entry exists with the key {");

			msg.append("toUserId=" + toUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Entry findByToUserId_Last(long toUserId, OrderByComparator obc)
		throws NoSuchEntryException, SystemException {
		int count = countByToUserId(toUserId);

		List<Entry> list = findByToUserId(toUserId, count - 1, count, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();

			msg.append("No Entry exists with the key {");

			msg.append("toUserId=" + toUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Entry[] findByToUserId_PrevAndNext(long entryId, long toUserId,
		OrderByComparator obc) throws NoSuchEntryException, SystemException {
		Entry entry = findByPrimaryKey(entryId);

		int count = countByToUserId(toUserId);

		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();

			query.append("FROM com.liferay.chat.model.Entry WHERE ");

			query.append("toUserId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("createDate DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(toUserId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, entry);

			Entry[] array = new EntryImpl[3];

			array[0] = (Entry)objArray[0];
			array[1] = (Entry)objArray[1];
			array[2] = (Entry)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Entry> findWithDynamicQuery(
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

	public List<Entry> findWithDynamicQuery(
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

	public List<Entry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<Entry> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	public List<Entry> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = EntryModelImpl.CACHE_ENABLED;
		String finderClassName = Entry.class.getName();
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

				StringMaker query = new StringMaker();

				query.append("FROM com.liferay.chat.model.Entry ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("createDate DESC");
				}

				Query q = session.createQuery(query.toString());

				List<Entry> list = (List<Entry>)QueryUtil.list(q, getDialect(),
						start, end);

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
			return (List<Entry>)result;
		}
	}

	public void removeByCreateDate(long createDate) throws SystemException {
		for (Entry entry : findByCreateDate(createDate)) {
			remove(entry);
		}
	}

	public void removeByFromUserId(long fromUserId) throws SystemException {
		for (Entry entry : findByFromUserId(fromUserId)) {
			remove(entry);
		}
	}

	public void removeByToUserId(long toUserId) throws SystemException {
		for (Entry entry : findByToUserId(toUserId)) {
			remove(entry);
		}
	}

	public void removeAll() throws SystemException {
		for (Entry entry : findAll()) {
			remove(entry);
		}
	}

	public int countByCreateDate(long createDate) throws SystemException {
		boolean finderClassNameCacheEnabled = EntryModelImpl.CACHE_ENABLED;
		String finderClassName = Entry.class.getName();
		String finderMethodName = "countByCreateDate";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(createDate) };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("createDate = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(createDate);

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

	public int countByFromUserId(long fromUserId) throws SystemException {
		boolean finderClassNameCacheEnabled = EntryModelImpl.CACHE_ENABLED;
		String finderClassName = Entry.class.getName();
		String finderMethodName = "countByFromUserId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(fromUserId) };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("fromUserId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fromUserId);

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

	public int countByToUserId(long toUserId) throws SystemException {
		boolean finderClassNameCacheEnabled = EntryModelImpl.CACHE_ENABLED;
		String finderClassName = Entry.class.getName();
		String finderMethodName = "countByToUserId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(toUserId) };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.chat.model.Entry WHERE ");

				query.append("toUserId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(toUserId);

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

	public int countAll() throws SystemException {
		boolean finderClassNameCacheEnabled = EntryModelImpl.CACHE_ENABLED;
		String finderClassName = Entry.class.getName();
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
						"SELECT COUNT(*) FROM com.liferay.chat.model.Entry");

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
						"value.object.listener.com.liferay.chat.model.Entry")));

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

	private static Log _log = LogFactory.getLog(EntryPersistenceImpl.class);
	private ModelListener[] _listeners = new ModelListener[0];
}