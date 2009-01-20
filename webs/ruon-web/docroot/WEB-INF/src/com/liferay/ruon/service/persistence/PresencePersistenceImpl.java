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

package com.liferay.ruon.service.persistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.ruon.NoSuchPresenceException;
import com.liferay.ruon.model.Presence;
import com.liferay.ruon.model.impl.PresenceImpl;
import com.liferay.ruon.model.impl.PresenceModelImpl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="PresencePersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PresencePersistenceImpl extends BasePersistenceImpl
	implements PresencePersistence {
	public Presence create(long presenceId) {
		Presence presence = new PresenceImpl();

		presence.setNew(true);
		presence.setPrimaryKey(presenceId);

		return presence;
	}

	public Presence remove(long presenceId)
		throws NoSuchPresenceException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Presence presence = (Presence)session.get(PresenceImpl.class,
					new Long(presenceId));

			if (presence == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No Presence exists with the primary key " +
						presenceId);
				}

				throw new NoSuchPresenceException(
					"No Presence exists with the primary key " + presenceId);
			}

			return remove(presence);
		}
		catch (NoSuchPresenceException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public Presence remove(Presence presence) throws SystemException {
		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onBeforeRemove(presence);
			}
		}

		presence = removeImpl(presence);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onAfterRemove(presence);
			}
		}

		return presence;
	}

	protected Presence removeImpl(Presence presence) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(PresenceImpl.class,
						presence.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(presence);

			session.flush();

			return presence;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(Presence.class.getName());
		}
	}

	public Presence update(Presence presence) throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(Presence presence) method. Use update(Presence presence, boolean merge) instead.");
		}

		return update(presence, false);
	}

	public Presence update(Presence presence, boolean merge)
		throws SystemException {
		boolean isNew = presence.isNew();

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onBeforeCreate(presence);
				}
				else {
					listener.onBeforeUpdate(presence);
				}
			}
		}

		presence = updateImpl(presence, merge);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onAfterCreate(presence);
				}
				else {
					listener.onAfterUpdate(presence);
				}
			}
		}

		return presence;
	}

	public Presence updateImpl(com.liferay.ruon.model.Presence presence,
		boolean merge) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, presence, merge);

			presence.setNew(false);

			return presence;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(Presence.class.getName());
		}
	}

	public Presence findByPrimaryKey(long presenceId)
		throws NoSuchPresenceException, SystemException {
		Presence presence = fetchByPrimaryKey(presenceId);

		if (presence == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No Presence exists with the primary key " +
					presenceId);
			}

			throw new NoSuchPresenceException(
				"No Presence exists with the primary key " + presenceId);
		}

		return presence;
	}

	public Presence fetchByPrimaryKey(long presenceId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (Presence)session.get(PresenceImpl.class,
				new Long(presenceId));
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Presence> findByUserId(long userId) throws SystemException {
		boolean finderClassNameCacheEnabled = PresenceModelImpl.CACHE_ENABLED;
		String finderClassName = Presence.class.getName();
		String finderMethodName = "findByUserId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(userId) };

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

				query.append("FROM com.liferay.ruon.model.Presence WHERE ");

				query.append("userId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				List<Presence> list = q.list();

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
			return (List<Presence>)result;
		}
	}

	public List<Presence> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	public List<Presence> findByUserId(long userId, int start, int end,
		OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = PresenceModelImpl.CACHE_ENABLED;
		String finderClassName = Presence.class.getName();
		String finderMethodName = "findByUserId";
		String[] finderParams = new String[] {
				Long.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(userId),
				
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

				query.append("FROM com.liferay.ruon.model.Presence WHERE ");

				query.append("userId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				List<Presence> list = (List<Presence>)QueryUtil.list(q,
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
			return (List<Presence>)result;
		}
	}

	public Presence findByUserId_First(long userId, OrderByComparator obc)
		throws NoSuchPresenceException, SystemException {
		List<Presence> list = findByUserId(userId, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Presence exists with the key {");

			msg.append("userId=" + userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPresenceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Presence findByUserId_Last(long userId, OrderByComparator obc)
		throws NoSuchPresenceException, SystemException {
		int count = countByUserId(userId);

		List<Presence> list = findByUserId(userId, count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Presence exists with the key {");

			msg.append("userId=" + userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPresenceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Presence[] findByUserId_PrevAndNext(long presenceId, long userId,
		OrderByComparator obc) throws NoSuchPresenceException, SystemException {
		Presence presence = findByPrimaryKey(presenceId);

		int count = countByUserId(userId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.ruon.model.Presence WHERE ");

			query.append("userId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, presence);

			Presence[] array = new PresenceImpl[3];

			array[0] = (Presence)objArray[0];
			array[1] = (Presence)objArray[1];
			array[2] = (Presence)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public Presence findByU_N(long userId, long networkId)
		throws NoSuchPresenceException, SystemException {
		Presence presence = fetchByU_N(userId, networkId);

		if (presence == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Presence exists with the key {");

			msg.append("userId=" + userId);

			msg.append(", ");
			msg.append("networkId=" + networkId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchPresenceException(msg.toString());
		}

		return presence;
	}

	public Presence fetchByU_N(long userId, long networkId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = PresenceModelImpl.CACHE_ENABLED;
		String finderClassName = Presence.class.getName();
		String finderMethodName = "fetchByU_N";
		String[] finderParams = new String[] {
				Long.class.getName(), Long.class.getName()
			};
		Object[] finderArgs = new Object[] { new Long(userId), new Long(networkId) };

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

				query.append("FROM com.liferay.ruon.model.Presence WHERE ");

				query.append("userId = ?");

				query.append(" AND ");

				query.append("networkId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(networkId);

				List<Presence> list = q.list();

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
			List<Presence> list = (List<Presence>)result;

			if (list.size() == 0) {
				return null;
			}
			else {
				return list.get(0);
			}
		}
	}

	public List<Presence> findByU_O(long userId, boolean online)
		throws SystemException {
		boolean finderClassNameCacheEnabled = PresenceModelImpl.CACHE_ENABLED;
		String finderClassName = Presence.class.getName();
		String finderMethodName = "findByU_O";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(userId), Boolean.valueOf(online)
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

				query.append("FROM com.liferay.ruon.model.Presence WHERE ");

				query.append("userId = ?");

				query.append(" AND ");

				query.append("online_ = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(online);

				List<Presence> list = q.list();

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
			return (List<Presence>)result;
		}
	}

	public List<Presence> findByU_O(long userId, boolean online, int start,
		int end) throws SystemException {
		return findByU_O(userId, online, start, end, null);
	}

	public List<Presence> findByU_O(long userId, boolean online, int start,
		int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = PresenceModelImpl.CACHE_ENABLED;
		String finderClassName = Presence.class.getName();
		String finderMethodName = "findByU_O";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(userId), Boolean.valueOf(online),
				
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

				query.append("FROM com.liferay.ruon.model.Presence WHERE ");

				query.append("userId = ?");

				query.append(" AND ");

				query.append("online_ = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(online);

				List<Presence> list = (List<Presence>)QueryUtil.list(q,
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
			return (List<Presence>)result;
		}
	}

	public Presence findByU_O_First(long userId, boolean online,
		OrderByComparator obc) throws NoSuchPresenceException, SystemException {
		List<Presence> list = findByU_O(userId, online, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Presence exists with the key {");

			msg.append("userId=" + userId);

			msg.append(", ");
			msg.append("online=" + online);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPresenceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Presence findByU_O_Last(long userId, boolean online,
		OrderByComparator obc) throws NoSuchPresenceException, SystemException {
		int count = countByU_O(userId, online);

		List<Presence> list = findByU_O(userId, online, count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Presence exists with the key {");

			msg.append("userId=" + userId);

			msg.append(", ");
			msg.append("online=" + online);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPresenceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Presence[] findByU_O_PrevAndNext(long presenceId, long userId,
		boolean online, OrderByComparator obc)
		throws NoSuchPresenceException, SystemException {
		Presence presence = findByPrimaryKey(presenceId);

		int count = countByU_O(userId, online);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.ruon.model.Presence WHERE ");

			query.append("userId = ?");

			query.append(" AND ");

			query.append("online_ = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			qPos.add(online);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, presence);

			Presence[] array = new PresenceImpl[3];

			array[0] = (Presence)objArray[0];
			array[1] = (Presence)objArray[1];
			array[2] = (Presence)objArray[2];

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

	public List<Presence> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<Presence> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	public List<Presence> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = PresenceModelImpl.CACHE_ENABLED;
		String finderClassName = Presence.class.getName();
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

				query.append("FROM com.liferay.ruon.model.Presence ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				List<Presence> list = (List<Presence>)QueryUtil.list(q,
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
			return (List<Presence>)result;
		}
	}

	public void removeByUserId(long userId) throws SystemException {
		for (Presence presence : findByUserId(userId)) {
			remove(presence);
		}
	}

	public void removeByU_N(long userId, long networkId)
		throws NoSuchPresenceException, SystemException {
		Presence presence = findByU_N(userId, networkId);

		remove(presence);
	}

	public void removeByU_O(long userId, boolean online)
		throws SystemException {
		for (Presence presence : findByU_O(userId, online)) {
			remove(presence);
		}
	}

	public void removeAll() throws SystemException {
		for (Presence presence : findAll()) {
			remove(presence);
		}
	}

	public int countByUserId(long userId) throws SystemException {
		boolean finderClassNameCacheEnabled = PresenceModelImpl.CACHE_ENABLED;
		String finderClassName = Presence.class.getName();
		String finderMethodName = "countByUserId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(userId) };

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
				query.append("FROM com.liferay.ruon.model.Presence WHERE ");

				query.append("userId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

	public int countByU_N(long userId, long networkId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = PresenceModelImpl.CACHE_ENABLED;
		String finderClassName = Presence.class.getName();
		String finderMethodName = "countByU_N";
		String[] finderParams = new String[] {
				Long.class.getName(), Long.class.getName()
			};
		Object[] finderArgs = new Object[] { new Long(userId), new Long(networkId) };

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
				query.append("FROM com.liferay.ruon.model.Presence WHERE ");

				query.append("userId = ?");

				query.append(" AND ");

				query.append("networkId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(networkId);

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

	public int countByU_O(long userId, boolean online)
		throws SystemException {
		boolean finderClassNameCacheEnabled = PresenceModelImpl.CACHE_ENABLED;
		String finderClassName = Presence.class.getName();
		String finderMethodName = "countByU_O";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(userId), Boolean.valueOf(online)
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

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.ruon.model.Presence WHERE ");

				query.append("userId = ?");

				query.append(" AND ");

				query.append("online_ = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(online);

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
		boolean finderClassNameCacheEnabled = PresenceModelImpl.CACHE_ENABLED;
		String finderClassName = Presence.class.getName();
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
						"SELECT COUNT(*) FROM com.liferay.ruon.model.Presence");

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
						"value.object.listener.com.liferay.ruon.model.Presence")));

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

	private static Log _log = LogFactoryUtil.getLog(PresencePersistenceImpl.class);
	private ModelListener[] _listeners = new ModelListener[0];
}