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

import com.liferay.wol.NoSuchMeetupRegistrationException;
import com.liferay.wol.model.MeetupRegistration;
import com.liferay.wol.model.impl.MeetupRegistrationImpl;
import com.liferay.wol.model.impl.MeetupRegistrationModelImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="MeetupRegistrationPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class MeetupRegistrationPersistenceImpl extends BasePersistence
	implements MeetupRegistrationPersistence {
	public MeetupRegistration create(long meetupRegistrationId) {
		MeetupRegistration meetupRegistration = new MeetupRegistrationImpl();

		meetupRegistration.setNew(true);
		meetupRegistration.setPrimaryKey(meetupRegistrationId);

		return meetupRegistration;
	}

	public MeetupRegistration remove(long meetupRegistrationId)
		throws NoSuchMeetupRegistrationException, SystemException {
		Session session = null;

		try {
			session = openSession();

			MeetupRegistration meetupRegistration = (MeetupRegistration)session.get(MeetupRegistrationImpl.class,
					new Long(meetupRegistrationId));

			if (meetupRegistration == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"No MeetupRegistration exists with the primary key " +
						meetupRegistrationId);
				}

				throw new NoSuchMeetupRegistrationException(
					"No MeetupRegistration exists with the primary key " +
					meetupRegistrationId);
			}

			return remove(meetupRegistration);
		}
		catch (NoSuchMeetupRegistrationException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public MeetupRegistration remove(MeetupRegistration meetupRegistration)
		throws SystemException {
		if (_listeners != null) {
			for (ModelListener listener : _listeners) {
				listener.onBeforeRemove(meetupRegistration);
			}
		}

		meetupRegistration = removeImpl(meetupRegistration);

		if (_listeners != null) {
			for (ModelListener listener : _listeners) {
				listener.onAfterRemove(meetupRegistration);
			}
		}

		return meetupRegistration;
	}

	protected MeetupRegistration removeImpl(
		MeetupRegistration meetupRegistration) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			session.delete(meetupRegistration);

			session.flush();

			return meetupRegistration;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);

			FinderCache.clearCache(MeetupRegistration.class.getName());
		}
	}

	public MeetupRegistration update(MeetupRegistration meetupRegistration)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(MeetupRegistration meetupRegistration) method. Use update(MeetupRegistration meetupRegistration, boolean merge) instead.");
		}

		return update(meetupRegistration, false);
	}

	public MeetupRegistration update(MeetupRegistration meetupRegistration,
		boolean merge) throws SystemException {
		boolean isNew = meetupRegistration.isNew();

		if (_listeners != null) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onBeforeCreate(meetupRegistration);
				}
				else {
					listener.onBeforeUpdate(meetupRegistration);
				}
			}
		}

		meetupRegistration = updateImpl(meetupRegistration, merge);

		if (_listeners != null) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onAfterCreate(meetupRegistration);
				}
				else {
					listener.onAfterUpdate(meetupRegistration);
				}
			}
		}

		return meetupRegistration;
	}

	public MeetupRegistration updateImpl(
		com.liferay.wol.model.MeetupRegistration meetupRegistration,
		boolean merge) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (merge) {
				session.merge(meetupRegistration);
			}
			else {
				if (meetupRegistration.isNew()) {
					session.save(meetupRegistration);
				}
			}

			session.flush();

			meetupRegistration.setNew(false);

			return meetupRegistration;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);

			FinderCache.clearCache(MeetupRegistration.class.getName());
		}
	}

	public MeetupRegistration findByPrimaryKey(long meetupRegistrationId)
		throws NoSuchMeetupRegistrationException, SystemException {
		MeetupRegistration meetupRegistration = fetchByPrimaryKey(meetupRegistrationId);

		if (meetupRegistration == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No MeetupRegistration exists with the primary key " +
					meetupRegistrationId);
			}

			throw new NoSuchMeetupRegistrationException(
				"No MeetupRegistration exists with the primary key " +
				meetupRegistrationId);
		}

		return meetupRegistration;
	}

	public MeetupRegistration fetchByPrimaryKey(long meetupRegistrationId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (MeetupRegistration)session.get(MeetupRegistrationImpl.class,
				new Long(meetupRegistrationId));
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<MeetupRegistration> findByMeetupEntryId(long meetupEntryId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = MeetupRegistrationModelImpl.CACHE_ENABLED;
		String finderClassName = MeetupRegistration.class.getName();
		String finderMethodName = "findByMeetupEntryId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(meetupEntryId) };

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

				query.append(
					"FROM com.liferay.wol.model.MeetupRegistration WHERE ");

				query.append("meetupEntryId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("modifiedDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(meetupEntryId);

				List<MeetupRegistration> list = q.list();

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
			return (List<MeetupRegistration>)result;
		}
	}

	public List<MeetupRegistration> findByMeetupEntryId(long meetupEntryId,
		int start, int end) throws SystemException {
		return findByMeetupEntryId(meetupEntryId, start, end, null);
	}

	public List<MeetupRegistration> findByMeetupEntryId(long meetupEntryId,
		int start, int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = MeetupRegistrationModelImpl.CACHE_ENABLED;
		String finderClassName = MeetupRegistration.class.getName();
		String finderMethodName = "findByMeetupEntryId";
		String[] finderParams = new String[] {
				Long.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(meetupEntryId),
				
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

				query.append(
					"FROM com.liferay.wol.model.MeetupRegistration WHERE ");

				query.append("meetupEntryId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("modifiedDate DESC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(meetupEntryId);

				List<MeetupRegistration> list = (List<MeetupRegistration>)QueryUtil.list(q,
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
			return (List<MeetupRegistration>)result;
		}
	}

	public MeetupRegistration findByMeetupEntryId_First(long meetupEntryId,
		OrderByComparator obc)
		throws NoSuchMeetupRegistrationException, SystemException {
		List<MeetupRegistration> list = findByMeetupEntryId(meetupEntryId, 0,
				1, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();

			msg.append("No MeetupRegistration exists with the key {");

			msg.append("meetupEntryId=" + meetupEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchMeetupRegistrationException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public MeetupRegistration findByMeetupEntryId_Last(long meetupEntryId,
		OrderByComparator obc)
		throws NoSuchMeetupRegistrationException, SystemException {
		int count = countByMeetupEntryId(meetupEntryId);

		List<MeetupRegistration> list = findByMeetupEntryId(meetupEntryId,
				count - 1, count, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();

			msg.append("No MeetupRegistration exists with the key {");

			msg.append("meetupEntryId=" + meetupEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchMeetupRegistrationException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public MeetupRegistration[] findByMeetupEntryId_PrevAndNext(
		long meetupRegistrationId, long meetupEntryId, OrderByComparator obc)
		throws NoSuchMeetupRegistrationException, SystemException {
		MeetupRegistration meetupRegistration = findByPrimaryKey(meetupRegistrationId);

		int count = countByMeetupEntryId(meetupEntryId);

		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();

			query.append("FROM com.liferay.wol.model.MeetupRegistration WHERE ");

			query.append("meetupEntryId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("modifiedDate DESC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(meetupEntryId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					meetupRegistration);

			MeetupRegistration[] array = new MeetupRegistrationImpl[3];

			array[0] = (MeetupRegistration)objArray[0];
			array[1] = (MeetupRegistration)objArray[1];
			array[2] = (MeetupRegistration)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<MeetupRegistration> findWithDynamicQuery(
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

	public List<MeetupRegistration> findWithDynamicQuery(
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

	public List<MeetupRegistration> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<MeetupRegistration> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<MeetupRegistration> findAll(int start, int end,
		OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = MeetupRegistrationModelImpl.CACHE_ENABLED;
		String finderClassName = MeetupRegistration.class.getName();
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

				query.append("FROM com.liferay.wol.model.MeetupRegistration ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("modifiedDate DESC");
				}

				Query q = session.createQuery(query.toString());

				List<MeetupRegistration> list = (List<MeetupRegistration>)QueryUtil.list(q,
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
			return (List<MeetupRegistration>)result;
		}
	}

	public void removeByMeetupEntryId(long meetupEntryId)
		throws SystemException {
		for (MeetupRegistration meetupRegistration : findByMeetupEntryId(
				meetupEntryId)) {
			remove(meetupRegistration);
		}
	}

	public void removeAll() throws SystemException {
		for (MeetupRegistration meetupRegistration : findAll()) {
			remove(meetupRegistration);
		}
	}

	public int countByMeetupEntryId(long meetupEntryId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = MeetupRegistrationModelImpl.CACHE_ENABLED;
		String finderClassName = MeetupRegistration.class.getName();
		String finderMethodName = "countByMeetupEntryId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(meetupEntryId) };

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
				query.append(
					"FROM com.liferay.wol.model.MeetupRegistration WHERE ");

				query.append("meetupEntryId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(meetupEntryId);

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
		boolean finderClassNameCacheEnabled = MeetupRegistrationModelImpl.CACHE_ENABLED;
		String finderClassName = MeetupRegistration.class.getName();
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
						"SELECT COUNT(*) FROM com.liferay.wol.model.MeetupRegistration");

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
						"value.object.listener.com.liferay.wol.model.MeetupRegistration")));

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

	private static Log _log = LogFactory.getLog(MeetupRegistrationPersistenceImpl.class);
	private ModelListener[] _listeners;
}