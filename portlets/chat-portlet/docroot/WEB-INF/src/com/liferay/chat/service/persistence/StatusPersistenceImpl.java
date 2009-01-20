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

package com.liferay.chat.service.persistence;

import com.liferay.chat.NoSuchStatusException;
import com.liferay.chat.model.Status;
import com.liferay.chat.model.impl.StatusImpl;
import com.liferay.chat.model.impl.StatusModelImpl;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="StatusPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class StatusPersistenceImpl extends BasePersistenceImpl
	implements StatusPersistence {
	public Status create(long statusId) {
		Status status = new StatusImpl();

		status.setNew(true);
		status.setPrimaryKey(statusId);

		return status;
	}

	public Status remove(long statusId)
		throws NoSuchStatusException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Status status = (Status)session.get(StatusImpl.class,
					new Long(statusId));

			if (status == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No Status exists with the primary key " +
						statusId);
				}

				throw new NoSuchStatusException(
					"No Status exists with the primary key " + statusId);
			}

			return remove(status);
		}
		catch (NoSuchStatusException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public Status remove(Status status) throws SystemException {
		for (ModelListener listener : listeners) {
			listener.onBeforeRemove(status);
		}

		status = removeImpl(status);

		for (ModelListener listener : listeners) {
			listener.onAfterRemove(status);
		}

		return status;
	}

	protected Status removeImpl(Status status) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(StatusImpl.class,
						status.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(status);

			session.flush();

			return status;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(Status.class.getName());
		}
	}

	public Status update(Status status) throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(Status status) method. Use update(Status status, boolean merge) instead.");
		}

		return update(status, false);
	}

	public Status update(Status status, boolean merge)
		throws SystemException {
		boolean isNew = status.isNew();

		for (ModelListener listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(status);
			}
			else {
				listener.onBeforeUpdate(status);
			}
		}

		status = updateImpl(status, merge);

		for (ModelListener listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(status);
			}
			else {
				listener.onAfterUpdate(status);
			}
		}

		return status;
	}

	public Status updateImpl(com.liferay.chat.model.Status status, boolean merge)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, status, merge);

			status.setNew(false);

			return status;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(Status.class.getName());
		}
	}

	public Status findByPrimaryKey(long statusId)
		throws NoSuchStatusException, SystemException {
		Status status = fetchByPrimaryKey(statusId);

		if (status == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No Status exists with the primary key " + statusId);
			}

			throw new NoSuchStatusException(
				"No Status exists with the primary key " + statusId);
		}

		return status;
	}

	public Status fetchByPrimaryKey(long statusId) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (Status)session.get(StatusImpl.class, new Long(statusId));
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public Status findByUserId(long userId)
		throws NoSuchStatusException, SystemException {
		Status status = fetchByUserId(userId);

		if (status == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Status exists with the key {");

			msg.append("userId=" + userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchStatusException(msg.toString());
		}

		return status;
	}

	public Status fetchByUserId(long userId) throws SystemException {
		boolean finderClassNameCacheEnabled = StatusModelImpl.CACHE_ENABLED;
		String finderClassName = Status.class.getName();
		String finderMethodName = "fetchByUserId";
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

				query.append("FROM com.liferay.chat.model.Status WHERE ");

				query.append("userId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				List<Status> list = q.list();

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
			List<Status> list = (List<Status>)result;

			if (list.size() == 0) {
				return null;
			}
			else {
				return list.get(0);
			}
		}
	}

	public List<Status> findByModifiedDate(long modifiedDate)
		throws SystemException {
		boolean finderClassNameCacheEnabled = StatusModelImpl.CACHE_ENABLED;
		String finderClassName = Status.class.getName();
		String finderMethodName = "findByModifiedDate";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(modifiedDate) };

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

				query.append("FROM com.liferay.chat.model.Status WHERE ");

				query.append("modifiedDate = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(modifiedDate);

				List<Status> list = q.list();

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
			return (List<Status>)result;
		}
	}

	public List<Status> findByModifiedDate(long modifiedDate, int start, int end)
		throws SystemException {
		return findByModifiedDate(modifiedDate, start, end, null);
	}

	public List<Status> findByModifiedDate(long modifiedDate, int start,
		int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = StatusModelImpl.CACHE_ENABLED;
		String finderClassName = Status.class.getName();
		String finderMethodName = "findByModifiedDate";
		String[] finderParams = new String[] {
				Long.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(modifiedDate),
				
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

				query.append("FROM com.liferay.chat.model.Status WHERE ");

				query.append("modifiedDate = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(modifiedDate);

				List<Status> list = (List<Status>)QueryUtil.list(q,
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
			return (List<Status>)result;
		}
	}

	public Status findByModifiedDate_First(long modifiedDate,
		OrderByComparator obc) throws NoSuchStatusException, SystemException {
		List<Status> list = findByModifiedDate(modifiedDate, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Status exists with the key {");

			msg.append("modifiedDate=" + modifiedDate);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchStatusException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Status findByModifiedDate_Last(long modifiedDate,
		OrderByComparator obc) throws NoSuchStatusException, SystemException {
		int count = countByModifiedDate(modifiedDate);

		List<Status> list = findByModifiedDate(modifiedDate, count - 1, count,
				obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Status exists with the key {");

			msg.append("modifiedDate=" + modifiedDate);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchStatusException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Status[] findByModifiedDate_PrevAndNext(long statusId,
		long modifiedDate, OrderByComparator obc)
		throws NoSuchStatusException, SystemException {
		Status status = findByPrimaryKey(statusId);

		int count = countByModifiedDate(modifiedDate);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.chat.model.Status WHERE ");

			query.append("modifiedDate = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(modifiedDate);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, status);

			Status[] array = new StatusImpl[3];

			array[0] = (Status)objArray[0];
			array[1] = (Status)objArray[1];
			array[2] = (Status)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Status> findByOnline(boolean online) throws SystemException {
		boolean finderClassNameCacheEnabled = StatusModelImpl.CACHE_ENABLED;
		String finderClassName = Status.class.getName();
		String finderMethodName = "findByOnline";
		String[] finderParams = new String[] { Boolean.class.getName() };
		Object[] finderArgs = new Object[] { Boolean.valueOf(online) };

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

				query.append("FROM com.liferay.chat.model.Status WHERE ");

				query.append("online_ = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(online);

				List<Status> list = q.list();

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
			return (List<Status>)result;
		}
	}

	public List<Status> findByOnline(boolean online, int start, int end)
		throws SystemException {
		return findByOnline(online, start, end, null);
	}

	public List<Status> findByOnline(boolean online, int start, int end,
		OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = StatusModelImpl.CACHE_ENABLED;
		String finderClassName = Status.class.getName();
		String finderMethodName = "findByOnline";
		String[] finderParams = new String[] {
				Boolean.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				Boolean.valueOf(online),
				
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

				query.append("FROM com.liferay.chat.model.Status WHERE ");

				query.append("online_ = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(online);

				List<Status> list = (List<Status>)QueryUtil.list(q,
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
			return (List<Status>)result;
		}
	}

	public Status findByOnline_First(boolean online, OrderByComparator obc)
		throws NoSuchStatusException, SystemException {
		List<Status> list = findByOnline(online, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Status exists with the key {");

			msg.append("online=" + online);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchStatusException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Status findByOnline_Last(boolean online, OrderByComparator obc)
		throws NoSuchStatusException, SystemException {
		int count = countByOnline(online);

		List<Status> list = findByOnline(online, count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Status exists with the key {");

			msg.append("online=" + online);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchStatusException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Status[] findByOnline_PrevAndNext(long statusId, boolean online,
		OrderByComparator obc) throws NoSuchStatusException, SystemException {
		Status status = findByPrimaryKey(statusId);

		int count = countByOnline(online);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.chat.model.Status WHERE ");

			query.append("online_ = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(online);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, status);

			Status[] array = new StatusImpl[3];

			array[0] = (Status)objArray[0];
			array[1] = (Status)objArray[1];
			array[2] = (Status)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Status> findByM_O(long modifiedDate, boolean online)
		throws SystemException {
		boolean finderClassNameCacheEnabled = StatusModelImpl.CACHE_ENABLED;
		String finderClassName = Status.class.getName();
		String finderMethodName = "findByM_O";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(modifiedDate), Boolean.valueOf(online)
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

				query.append("FROM com.liferay.chat.model.Status WHERE ");

				query.append("modifiedDate = ?");

				query.append(" AND ");

				query.append("online_ = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(modifiedDate);

				qPos.add(online);

				List<Status> list = q.list();

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
			return (List<Status>)result;
		}
	}

	public List<Status> findByM_O(long modifiedDate, boolean online, int start,
		int end) throws SystemException {
		return findByM_O(modifiedDate, online, start, end, null);
	}

	public List<Status> findByM_O(long modifiedDate, boolean online, int start,
		int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = StatusModelImpl.CACHE_ENABLED;
		String finderClassName = Status.class.getName();
		String finderMethodName = "findByM_O";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(modifiedDate), Boolean.valueOf(online),
				
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

				query.append("FROM com.liferay.chat.model.Status WHERE ");

				query.append("modifiedDate = ?");

				query.append(" AND ");

				query.append("online_ = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(modifiedDate);

				qPos.add(online);

				List<Status> list = (List<Status>)QueryUtil.list(q,
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
			return (List<Status>)result;
		}
	}

	public Status findByM_O_First(long modifiedDate, boolean online,
		OrderByComparator obc) throws NoSuchStatusException, SystemException {
		List<Status> list = findByM_O(modifiedDate, online, 0, 1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Status exists with the key {");

			msg.append("modifiedDate=" + modifiedDate);

			msg.append(", ");
			msg.append("online=" + online);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchStatusException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Status findByM_O_Last(long modifiedDate, boolean online,
		OrderByComparator obc) throws NoSuchStatusException, SystemException {
		int count = countByM_O(modifiedDate, online);

		List<Status> list = findByM_O(modifiedDate, online, count - 1, count,
				obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Status exists with the key {");

			msg.append("modifiedDate=" + modifiedDate);

			msg.append(", ");
			msg.append("online=" + online);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchStatusException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Status[] findByM_O_PrevAndNext(long statusId, long modifiedDate,
		boolean online, OrderByComparator obc)
		throws NoSuchStatusException, SystemException {
		Status status = findByPrimaryKey(statusId);

		int count = countByM_O(modifiedDate, online);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.chat.model.Status WHERE ");

			query.append("modifiedDate = ?");

			query.append(" AND ");

			query.append("online_ = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(modifiedDate);

			qPos.add(online);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, status);

			Status[] array = new StatusImpl[3];

			array[0] = (Status)objArray[0];
			array[1] = (Status)objArray[1];
			array[2] = (Status)objArray[2];

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

	public List<Status> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<Status> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	public List<Status> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = StatusModelImpl.CACHE_ENABLED;
		String finderClassName = Status.class.getName();
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

				query.append("FROM com.liferay.chat.model.Status ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				List<Status> list = null;

				if (obc == null) {
					list = (List<Status>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Status>)QueryUtil.list(q, getDialect(), start,
							end);
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
			return (List<Status>)result;
		}
	}

	public void removeByUserId(long userId)
		throws NoSuchStatusException, SystemException {
		Status status = findByUserId(userId);

		remove(status);
	}

	public void removeByModifiedDate(long modifiedDate)
		throws SystemException {
		for (Status status : findByModifiedDate(modifiedDate)) {
			remove(status);
		}
	}

	public void removeByOnline(boolean online) throws SystemException {
		for (Status status : findByOnline(online)) {
			remove(status);
		}
	}

	public void removeByM_O(long modifiedDate, boolean online)
		throws SystemException {
		for (Status status : findByM_O(modifiedDate, online)) {
			remove(status);
		}
	}

	public void removeAll() throws SystemException {
		for (Status status : findAll()) {
			remove(status);
		}
	}

	public int countByUserId(long userId) throws SystemException {
		boolean finderClassNameCacheEnabled = StatusModelImpl.CACHE_ENABLED;
		String finderClassName = Status.class.getName();
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
				query.append("FROM com.liferay.chat.model.Status WHERE ");

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

	public int countByModifiedDate(long modifiedDate) throws SystemException {
		boolean finderClassNameCacheEnabled = StatusModelImpl.CACHE_ENABLED;
		String finderClassName = Status.class.getName();
		String finderMethodName = "countByModifiedDate";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(modifiedDate) };

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
				query.append("FROM com.liferay.chat.model.Status WHERE ");

				query.append("modifiedDate = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(modifiedDate);

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

	public int countByOnline(boolean online) throws SystemException {
		boolean finderClassNameCacheEnabled = StatusModelImpl.CACHE_ENABLED;
		String finderClassName = Status.class.getName();
		String finderMethodName = "countByOnline";
		String[] finderParams = new String[] { Boolean.class.getName() };
		Object[] finderArgs = new Object[] { Boolean.valueOf(online) };

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
				query.append("FROM com.liferay.chat.model.Status WHERE ");

				query.append("online_ = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

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

	public int countByM_O(long modifiedDate, boolean online)
		throws SystemException {
		boolean finderClassNameCacheEnabled = StatusModelImpl.CACHE_ENABLED;
		String finderClassName = Status.class.getName();
		String finderMethodName = "countByM_O";
		String[] finderParams = new String[] {
				Long.class.getName(), Boolean.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(modifiedDate), Boolean.valueOf(online)
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
				query.append("FROM com.liferay.chat.model.Status WHERE ");

				query.append("modifiedDate = ?");

				query.append(" AND ");

				query.append("online_ = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(modifiedDate);

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
		boolean finderClassNameCacheEnabled = StatusModelImpl.CACHE_ENABLED;
		String finderClassName = Status.class.getName();
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
						"SELECT COUNT(*) FROM com.liferay.chat.model.Status");

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

	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.chat.model.Status")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener> listenersList = new ArrayList<ModelListener>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(name = "com.liferay.chat.service.persistence.EntryPersistence.impl")
	protected com.liferay.chat.service.persistence.EntryPersistence entryPersistence;
	@BeanReference(name = "com.liferay.chat.service.persistence.StatusPersistence.impl")
	protected com.liferay.chat.service.persistence.StatusPersistence statusPersistence;
	private static Log _log = LogFactoryUtil.getLog(StatusPersistenceImpl.class);
}