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

package com.liferay.wol.service.persistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.DynamicQuery;
import com.liferay.portal.kernel.dao.DynamicQueryInitializer;
import com.liferay.portal.kernel.util.GetterUtil;
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

import com.liferay.wol.NoSuchMeetupEntryException;
import com.liferay.wol.model.MeetupEntry;
import com.liferay.wol.model.impl.MeetupEntryImpl;
import com.liferay.wol.model.impl.MeetupEntryModelImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="MeetupEntryPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class MeetupEntryPersistenceImpl extends BasePersistence
	implements MeetupEntryPersistence {
	public MeetupEntry create(long meetupEntryId) {
		MeetupEntry meetupEntry = new MeetupEntryImpl();

		meetupEntry.setNew(true);
		meetupEntry.setPrimaryKey(meetupEntryId);

		return meetupEntry;
	}

	public MeetupEntry remove(long meetupEntryId)
		throws NoSuchMeetupEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			MeetupEntry meetupEntry = (MeetupEntry)session.get(MeetupEntryImpl.class,
					new Long(meetupEntryId));

			if (meetupEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No MeetupEntry exists with the primary key " +
						meetupEntryId);
				}

				throw new NoSuchMeetupEntryException(
					"No MeetupEntry exists with the primary key " +
					meetupEntryId);
			}

			return remove(meetupEntry);
		}
		catch (NoSuchMeetupEntryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public MeetupEntry remove(MeetupEntry meetupEntry)
		throws SystemException {
		if (_listeners != null) {
			for (ModelListener listener : _listeners) {
				listener.onBeforeRemove(meetupEntry);
			}
		}

		meetupEntry = removeImpl(meetupEntry);

		if (_listeners != null) {
			for (ModelListener listener : _listeners) {
				listener.onAfterRemove(meetupEntry);
			}
		}

		return meetupEntry;
	}

	protected MeetupEntry removeImpl(MeetupEntry meetupEntry)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			session.delete(meetupEntry);

			session.flush();

			return meetupEntry;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);

			FinderCache.clearCache(MeetupEntry.class.getName());
		}
	}

	public MeetupEntry update(MeetupEntry meetupEntry)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(MeetupEntry meetupEntry) method. Use update(MeetupEntry meetupEntry, boolean merge) instead.");
		}

		return update(meetupEntry, false);
	}

	public MeetupEntry update(MeetupEntry meetupEntry, boolean merge)
		throws SystemException {
		boolean isNew = meetupEntry.isNew();

		if (_listeners != null) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onBeforeCreate(meetupEntry);
				}
				else {
					listener.onBeforeUpdate(meetupEntry);
				}
			}
		}

		meetupEntry = updateImpl(meetupEntry, merge);

		if (_listeners != null) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onAfterCreate(meetupEntry);
				}
				else {
					listener.onAfterUpdate(meetupEntry);
				}
			}
		}

		return meetupEntry;
	}

	public MeetupEntry updateImpl(
		com.liferay.wol.model.MeetupEntry meetupEntry, boolean merge)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (merge) {
				session.merge(meetupEntry);
			}
			else {
				if (meetupEntry.isNew()) {
					session.save(meetupEntry);
				}
			}

			session.flush();

			meetupEntry.setNew(false);

			return meetupEntry;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);

			FinderCache.clearCache(MeetupEntry.class.getName());
		}
	}

	public MeetupEntry findByPrimaryKey(long meetupEntryId)
		throws NoSuchMeetupEntryException, SystemException {
		MeetupEntry meetupEntry = fetchByPrimaryKey(meetupEntryId);

		if (meetupEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No MeetupEntry exists with the primary key " +
					meetupEntryId);
			}

			throw new NoSuchMeetupEntryException(
				"No MeetupEntry exists with the primary key " + meetupEntryId);
		}

		return meetupEntry;
	}

	public MeetupEntry fetchByPrimaryKey(long meetupEntryId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (MeetupEntry)session.get(MeetupEntryImpl.class,
				new Long(meetupEntryId));
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<MeetupEntry> findByCompanyId(long companyId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = MeetupEntryModelImpl.CACHE_ENABLED;
		String finderClassName = MeetupEntry.class.getName();
		String finderMethodName = "findByCompanyId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(companyId) };

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

				query.append("FROM com.liferay.wol.model.MeetupEntry WHERE ");

				query.append("companyId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("startDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				List<MeetupEntry> list = q.list();

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
			return (List<MeetupEntry>)result;
		}
	}

	public List<MeetupEntry> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	public List<MeetupEntry> findByCompanyId(long companyId, int start,
		int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = MeetupEntryModelImpl.CACHE_ENABLED;
		String finderClassName = MeetupEntry.class.getName();
		String finderMethodName = "findByCompanyId";
		String[] finderParams = new String[] {
				Long.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(companyId),
				
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

				query.append("FROM com.liferay.wol.model.MeetupEntry WHERE ");

				query.append("companyId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("startDate DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				List<MeetupEntry> list = (List<MeetupEntry>)QueryUtil.list(q,
						getDialect(), start, end);

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
			return (List<MeetupEntry>)result;
		}
	}

	public MeetupEntry findByCompanyId_First(long companyId,
		OrderByComparator obc)
		throws NoSuchMeetupEntryException, SystemException {
		List<MeetupEntry> list = findByCompanyId(companyId, 0, 1, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();

			msg.append("No MeetupEntry exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchMeetupEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public MeetupEntry findByCompanyId_Last(long companyId,
		OrderByComparator obc)
		throws NoSuchMeetupEntryException, SystemException {
		int count = countByCompanyId(companyId);

		List<MeetupEntry> list = findByCompanyId(companyId, count - 1, count,
				obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();

			msg.append("No MeetupEntry exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchMeetupEntryException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public MeetupEntry[] findByCompanyId_PrevAndNext(long meetupEntryId,
		long companyId, OrderByComparator obc)
		throws NoSuchMeetupEntryException, SystemException {
		MeetupEntry meetupEntry = findByPrimaryKey(meetupEntryId);

		int count = countByCompanyId(companyId);

		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();

			query.append("FROM com.liferay.wol.model.MeetupEntry WHERE ");

			query.append("companyId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("startDate DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					meetupEntry);

			MeetupEntry[] array = new MeetupEntryImpl[3];

			array[0] = (MeetupEntry)objArray[0];
			array[1] = (MeetupEntry)objArray[1];
			array[2] = (MeetupEntry)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<MeetupEntry> findWithDynamicQuery(
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

	public List<MeetupEntry> findWithDynamicQuery(
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

	public List<MeetupEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<MeetupEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<MeetupEntry> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = MeetupEntryModelImpl.CACHE_ENABLED;
		String finderClassName = MeetupEntry.class.getName();
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

				query.append("FROM com.liferay.wol.model.MeetupEntry ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("startDate DESC");
				}

				Query q = session.createQuery(query.toString());

				List<MeetupEntry> list = (List<MeetupEntry>)QueryUtil.list(q,
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
			return (List<MeetupEntry>)result;
		}
	}

	public void removeByCompanyId(long companyId) throws SystemException {
		for (MeetupEntry meetupEntry : findByCompanyId(companyId)) {
			remove(meetupEntry);
		}
	}

	public void removeAll() throws SystemException {
		for (MeetupEntry meetupEntry : findAll()) {
			remove(meetupEntry);
		}
	}

	public int countByCompanyId(long companyId) throws SystemException {
		boolean finderClassNameCacheEnabled = MeetupEntryModelImpl.CACHE_ENABLED;
		String finderClassName = MeetupEntry.class.getName();
		String finderMethodName = "countByCompanyId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(companyId) };

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
				query.append("FROM com.liferay.wol.model.MeetupEntry WHERE ");

				query.append("companyId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

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
		boolean finderClassNameCacheEnabled = MeetupEntryModelImpl.CACHE_ENABLED;
		String finderClassName = MeetupEntry.class.getName();
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
						"SELECT COUNT(*) FROM com.liferay.wol.model.MeetupEntry");

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

	protected void initDao() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					PropsUtil.get(
						"value.object.listener.com.liferay.wol.model.MeetupEntry")));

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

	private static Log _log = LogFactory.getLog(MeetupEntryPersistenceImpl.class);
	private ModelListener[] _listeners;
}