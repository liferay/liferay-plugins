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

import com.liferay.wol.NoSuchMeetupsRegistrationException;
import com.liferay.wol.model.MeetupsRegistration;
import com.liferay.wol.model.impl.MeetupsRegistrationImpl;
import com.liferay.wol.model.impl.MeetupsRegistrationModelImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="MeetupsRegistrationPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class MeetupsRegistrationPersistenceImpl extends BasePersistence
	implements MeetupsRegistrationPersistence {
	public MeetupsRegistration create(long meetupsRegistrationId) {
		MeetupsRegistration meetupsRegistration = new MeetupsRegistrationImpl();

		meetupsRegistration.setNew(true);
		meetupsRegistration.setPrimaryKey(meetupsRegistrationId);

		return meetupsRegistration;
	}

	public MeetupsRegistration remove(long meetupsRegistrationId)
		throws NoSuchMeetupsRegistrationException, SystemException {
		Session session = null;

		try {
			session = openSession();

			MeetupsRegistration meetupsRegistration = (MeetupsRegistration)session.get(MeetupsRegistrationImpl.class,
					new Long(meetupsRegistrationId));

			if (meetupsRegistration == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"No MeetupsRegistration exists with the primary key " +
						meetupsRegistrationId);
				}

				throw new NoSuchMeetupsRegistrationException(
					"No MeetupsRegistration exists with the primary key " +
					meetupsRegistrationId);
			}

			return remove(meetupsRegistration);
		}
		catch (NoSuchMeetupsRegistrationException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public MeetupsRegistration remove(MeetupsRegistration meetupsRegistration)
		throws SystemException {
		if (_listeners != null) {
			for (ModelListener listener : _listeners) {
				listener.onBeforeRemove(meetupsRegistration);
			}
		}

		meetupsRegistration = removeImpl(meetupsRegistration);

		if (_listeners != null) {
			for (ModelListener listener : _listeners) {
				listener.onAfterRemove(meetupsRegistration);
			}
		}

		return meetupsRegistration;
	}

	protected MeetupsRegistration removeImpl(
		MeetupsRegistration meetupsRegistration) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			session.delete(meetupsRegistration);

			session.flush();

			return meetupsRegistration;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);

			FinderCache.clearCache(MeetupsRegistration.class.getName());
		}
	}

	public MeetupsRegistration update(MeetupsRegistration meetupsRegistration)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(MeetupsRegistration meetupsRegistration) method. Use update(MeetupsRegistration meetupsRegistration, boolean merge) instead.");
		}

		return update(meetupsRegistration, false);
	}

	public MeetupsRegistration update(MeetupsRegistration meetupsRegistration,
		boolean merge) throws SystemException {
		boolean isNew = meetupsRegistration.isNew();

		if (_listeners != null) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onBeforeCreate(meetupsRegistration);
				}
				else {
					listener.onBeforeUpdate(meetupsRegistration);
				}
			}
		}

		meetupsRegistration = updateImpl(meetupsRegistration, merge);

		if (_listeners != null) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onAfterCreate(meetupsRegistration);
				}
				else {
					listener.onAfterUpdate(meetupsRegistration);
				}
			}
		}

		return meetupsRegistration;
	}

	public MeetupsRegistration updateImpl(
		com.liferay.wol.model.MeetupsRegistration meetupsRegistration,
		boolean merge) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (merge) {
				session.merge(meetupsRegistration);
			}
			else {
				if (meetupsRegistration.isNew()) {
					session.save(meetupsRegistration);
				}
			}

			session.flush();

			meetupsRegistration.setNew(false);

			return meetupsRegistration;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);

			FinderCache.clearCache(MeetupsRegistration.class.getName());
		}
	}

	public MeetupsRegistration findByPrimaryKey(long meetupsRegistrationId)
		throws NoSuchMeetupsRegistrationException, SystemException {
		MeetupsRegistration meetupsRegistration = fetchByPrimaryKey(meetupsRegistrationId);

		if (meetupsRegistration == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No MeetupsRegistration exists with the primary key " +
					meetupsRegistrationId);
			}

			throw new NoSuchMeetupsRegistrationException(
				"No MeetupsRegistration exists with the primary key " +
				meetupsRegistrationId);
		}

		return meetupsRegistration;
	}

	public MeetupsRegistration fetchByPrimaryKey(long meetupsRegistrationId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (MeetupsRegistration)session.get(MeetupsRegistrationImpl.class,
				new Long(meetupsRegistrationId));
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<MeetupsRegistration> findByMeetupsEntryId(long meetupsEntryId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = MeetupsRegistrationModelImpl.CACHE_ENABLED;
		String finderClassName = MeetupsRegistration.class.getName();
		String finderMethodName = "findByMeetupsEntryId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(meetupsEntryId) };

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
					"FROM com.liferay.wol.model.MeetupsRegistration WHERE ");

				query.append("meetupsEntryId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("modifiedDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(meetupsEntryId);

				List<MeetupsRegistration> list = q.list();

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
			return (List<MeetupsRegistration>)result;
		}
	}

	public List<MeetupsRegistration> findByMeetupsEntryId(long meetupsEntryId,
		int start, int end) throws SystemException {
		return findByMeetupsEntryId(meetupsEntryId, start, end, null);
	}

	public List<MeetupsRegistration> findByMeetupsEntryId(long meetupsEntryId,
		int start, int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = MeetupsRegistrationModelImpl.CACHE_ENABLED;
		String finderClassName = MeetupsRegistration.class.getName();
		String finderMethodName = "findByMeetupsEntryId";
		String[] finderParams = new String[] {
				Long.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(meetupsEntryId),
				
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
					"FROM com.liferay.wol.model.MeetupsRegistration WHERE ");

				query.append("meetupsEntryId = ?");

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

				qPos.add(meetupsEntryId);

				List<MeetupsRegistration> list = (List<MeetupsRegistration>)QueryUtil.list(q,
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
			return (List<MeetupsRegistration>)result;
		}
	}

	public MeetupsRegistration findByMeetupsEntryId_First(long meetupsEntryId,
		OrderByComparator obc)
		throws NoSuchMeetupsRegistrationException, SystemException {
		List<MeetupsRegistration> list = findByMeetupsEntryId(meetupsEntryId,
				0, 1, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();

			msg.append("No MeetupsRegistration exists with the key {");

			msg.append("meetupsEntryId=" + meetupsEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchMeetupsRegistrationException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public MeetupsRegistration findByMeetupsEntryId_Last(long meetupsEntryId,
		OrderByComparator obc)
		throws NoSuchMeetupsRegistrationException, SystemException {
		int count = countByMeetupsEntryId(meetupsEntryId);

		List<MeetupsRegistration> list = findByMeetupsEntryId(meetupsEntryId,
				count - 1, count, obc);

		if (list.size() == 0) {
			StringMaker msg = new StringMaker();

			msg.append("No MeetupsRegistration exists with the key {");

			msg.append("meetupsEntryId=" + meetupsEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchMeetupsRegistrationException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public MeetupsRegistration[] findByMeetupsEntryId_PrevAndNext(
		long meetupsRegistrationId, long meetupsEntryId, OrderByComparator obc)
		throws NoSuchMeetupsRegistrationException, SystemException {
		MeetupsRegistration meetupsRegistration = findByPrimaryKey(meetupsRegistrationId);

		int count = countByMeetupsEntryId(meetupsEntryId);

		Session session = null;

		try {
			session = openSession();

			StringMaker query = new StringMaker();

			query.append(
				"FROM com.liferay.wol.model.MeetupsRegistration WHERE ");

			query.append("meetupsEntryId = ?");

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

			qPos.add(meetupsEntryId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					meetupsRegistration);

			MeetupsRegistration[] array = new MeetupsRegistrationImpl[3];

			array[0] = (MeetupsRegistration)objArray[0];
			array[1] = (MeetupsRegistration)objArray[1];
			array[2] = (MeetupsRegistration)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<MeetupsRegistration> findWithDynamicQuery(
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

	public List<MeetupsRegistration> findWithDynamicQuery(
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

	public List<MeetupsRegistration> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<MeetupsRegistration> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<MeetupsRegistration> findAll(int start, int end,
		OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = MeetupsRegistrationModelImpl.CACHE_ENABLED;
		String finderClassName = MeetupsRegistration.class.getName();
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

				query.append("FROM com.liferay.wol.model.MeetupsRegistration ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("modifiedDate DESC");
				}

				Query q = session.createQuery(query.toString());

				List<MeetupsRegistration> list = (List<MeetupsRegistration>)QueryUtil.list(q,
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
			return (List<MeetupsRegistration>)result;
		}
	}

	public void removeByMeetupsEntryId(long meetupsEntryId)
		throws SystemException {
		for (MeetupsRegistration meetupsRegistration : findByMeetupsEntryId(
				meetupsEntryId)) {
			remove(meetupsRegistration);
		}
	}

	public void removeAll() throws SystemException {
		for (MeetupsRegistration meetupsRegistration : findAll()) {
			remove(meetupsRegistration);
		}
	}

	public int countByMeetupsEntryId(long meetupsEntryId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = MeetupsRegistrationModelImpl.CACHE_ENABLED;
		String finderClassName = MeetupsRegistration.class.getName();
		String finderMethodName = "countByMeetupsEntryId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(meetupsEntryId) };

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
					"FROM com.liferay.wol.model.MeetupsRegistration WHERE ");

				query.append("meetupsEntryId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(meetupsEntryId);

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
		boolean finderClassNameCacheEnabled = MeetupsRegistrationModelImpl.CACHE_ENABLED;
		String finderClassName = MeetupsRegistration.class.getName();
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
						"SELECT COUNT(*) FROM com.liferay.wol.model.MeetupsRegistration");

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
						"value.object.listener.com.liferay.wol.model.MeetupsRegistration")));

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

	private static Log _log = LogFactory.getLog(MeetupsRegistrationPersistenceImpl.class);
	private ModelListener[] _listeners;
}