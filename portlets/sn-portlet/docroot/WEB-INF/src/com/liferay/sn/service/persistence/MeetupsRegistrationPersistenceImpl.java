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

package com.liferay.sn.service.persistence;

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

import com.liferay.sn.NoSuchMeetupsRegistrationException;
import com.liferay.sn.model.MeetupsRegistration;
import com.liferay.sn.model.impl.MeetupsRegistrationImpl;
import com.liferay.sn.model.impl.MeetupsRegistrationModelImpl;

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
public class MeetupsRegistrationPersistenceImpl extends BasePersistenceImpl
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
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public MeetupsRegistration remove(MeetupsRegistration meetupsRegistration)
		throws SystemException {
		for (ModelListener<MeetupsRegistration> listener : listeners) {
			listener.onBeforeRemove(meetupsRegistration);
		}

		meetupsRegistration = removeImpl(meetupsRegistration);

		for (ModelListener<MeetupsRegistration> listener : listeners) {
			listener.onAfterRemove(meetupsRegistration);
		}

		return meetupsRegistration;
	}

	protected MeetupsRegistration removeImpl(
		MeetupsRegistration meetupsRegistration) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(MeetupsRegistrationImpl.class,
						meetupsRegistration.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(meetupsRegistration);

			session.flush();

			return meetupsRegistration;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(MeetupsRegistration.class.getName());
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

		for (ModelListener<MeetupsRegistration> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(meetupsRegistration);
			}
			else {
				listener.onBeforeUpdate(meetupsRegistration);
			}
		}

		meetupsRegistration = updateImpl(meetupsRegistration, merge);

		for (ModelListener<MeetupsRegistration> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(meetupsRegistration);
			}
			else {
				listener.onAfterUpdate(meetupsRegistration);
			}
		}

		return meetupsRegistration;
	}

	public MeetupsRegistration updateImpl(
		com.liferay.sn.model.MeetupsRegistration meetupsRegistration,
		boolean merge) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, meetupsRegistration, merge);

			meetupsRegistration.setNew(false);

			return meetupsRegistration;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(MeetupsRegistration.class.getName());
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
			throw processException(e);
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
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.sn.model.MeetupsRegistration WHERE ");

				query.append("meetupsEntryId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("modifiedDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(meetupsEntryId);

				List<MeetupsRegistration> list = q.list();

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
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.sn.model.MeetupsRegistration WHERE ");

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
			return (List<MeetupsRegistration>)result;
		}
	}

	public MeetupsRegistration findByMeetupsEntryId_First(long meetupsEntryId,
		OrderByComparator obc)
		throws NoSuchMeetupsRegistrationException, SystemException {
		List<MeetupsRegistration> list = findByMeetupsEntryId(meetupsEntryId,
				0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

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

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

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

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.sn.model.MeetupsRegistration WHERE ");

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
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public MeetupsRegistration findByU_ME(long userId, long meetupsEntryId)
		throws NoSuchMeetupsRegistrationException, SystemException {
		MeetupsRegistration meetupsRegistration = fetchByU_ME(userId,
				meetupsEntryId);

		if (meetupsRegistration == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No MeetupsRegistration exists with the key {");

			msg.append("userId=" + userId);

			msg.append(", ");
			msg.append("meetupsEntryId=" + meetupsEntryId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchMeetupsRegistrationException(msg.toString());
		}

		return meetupsRegistration;
	}

	public MeetupsRegistration fetchByU_ME(long userId, long meetupsEntryId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = MeetupsRegistrationModelImpl.CACHE_ENABLED;
		String finderClassName = MeetupsRegistration.class.getName();
		String finderMethodName = "fetchByU_ME";
		String[] finderParams = new String[] {
				Long.class.getName(), Long.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(userId), new Long(meetupsEntryId)
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

				query.append(
					"FROM com.liferay.sn.model.MeetupsRegistration WHERE ");

				query.append("userId = ?");

				query.append(" AND ");

				query.append("meetupsEntryId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("modifiedDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(meetupsEntryId);

				List<MeetupsRegistration> list = q.list();

				if (list.isEmpty()) {
					return null;
				}

				FinderCacheUtil.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list.get(0);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			List<MeetupsRegistration> list = (List<MeetupsRegistration>)result;

			if (list.isEmpty()) {
				return null;
			}
			else {
				return list.get(0);
			}
		}
	}

	public List<MeetupsRegistration> findByME_S(long meetupsEntryId, int status)
		throws SystemException {
		boolean finderClassNameCacheEnabled = MeetupsRegistrationModelImpl.CACHE_ENABLED;
		String finderClassName = MeetupsRegistration.class.getName();
		String finderMethodName = "findByME_S";
		String[] finderParams = new String[] {
				Long.class.getName(), Integer.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(meetupsEntryId), new Integer(status)
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

				query.append(
					"FROM com.liferay.sn.model.MeetupsRegistration WHERE ");

				query.append("meetupsEntryId = ?");

				query.append(" AND ");

				query.append("status = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("modifiedDate DESC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(meetupsEntryId);

				qPos.add(status);

				List<MeetupsRegistration> list = q.list();

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
			return (List<MeetupsRegistration>)result;
		}
	}

	public List<MeetupsRegistration> findByME_S(long meetupsEntryId,
		int status, int start, int end) throws SystemException {
		return findByME_S(meetupsEntryId, status, start, end, null);
	}

	public List<MeetupsRegistration> findByME_S(long meetupsEntryId,
		int status, int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = MeetupsRegistrationModelImpl.CACHE_ENABLED;
		String finderClassName = MeetupsRegistration.class.getName();
		String finderMethodName = "findByME_S";
		String[] finderParams = new String[] {
				Long.class.getName(), Integer.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				new Long(meetupsEntryId), new Integer(status),
				
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

				query.append(
					"FROM com.liferay.sn.model.MeetupsRegistration WHERE ");

				query.append("meetupsEntryId = ?");

				query.append(" AND ");

				query.append("status = ?");

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

				qPos.add(status);

				List<MeetupsRegistration> list = (List<MeetupsRegistration>)QueryUtil.list(q,
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
			return (List<MeetupsRegistration>)result;
		}
	}

	public MeetupsRegistration findByME_S_First(long meetupsEntryId,
		int status, OrderByComparator obc)
		throws NoSuchMeetupsRegistrationException, SystemException {
		List<MeetupsRegistration> list = findByME_S(meetupsEntryId, status, 0,
				1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No MeetupsRegistration exists with the key {");

			msg.append("meetupsEntryId=" + meetupsEntryId);

			msg.append(", ");
			msg.append("status=" + status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchMeetupsRegistrationException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public MeetupsRegistration findByME_S_Last(long meetupsEntryId, int status,
		OrderByComparator obc)
		throws NoSuchMeetupsRegistrationException, SystemException {
		int count = countByME_S(meetupsEntryId, status);

		List<MeetupsRegistration> list = findByME_S(meetupsEntryId, status,
				count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No MeetupsRegistration exists with the key {");

			msg.append("meetupsEntryId=" + meetupsEntryId);

			msg.append(", ");
			msg.append("status=" + status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchMeetupsRegistrationException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public MeetupsRegistration[] findByME_S_PrevAndNext(
		long meetupsRegistrationId, long meetupsEntryId, int status,
		OrderByComparator obc)
		throws NoSuchMeetupsRegistrationException, SystemException {
		MeetupsRegistration meetupsRegistration = findByPrimaryKey(meetupsRegistrationId);

		int count = countByME_S(meetupsEntryId, status);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.sn.model.MeetupsRegistration WHERE ");

			query.append("meetupsEntryId = ?");

			query.append(" AND ");

			query.append("status = ?");

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

			qPos.add(status);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					meetupsRegistration);

			MeetupsRegistration[] array = new MeetupsRegistrationImpl[3];

			array[0] = (MeetupsRegistration)objArray[0];
			array[1] = (MeetupsRegistration)objArray[1];
			array[2] = (MeetupsRegistration)objArray[2];

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
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.sn.model.MeetupsRegistration ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("modifiedDate DESC");
				}

				Query q = session.createQuery(query.toString());

				List<MeetupsRegistration> list = null;

				if (obc == null) {
					list = (List<MeetupsRegistration>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<MeetupsRegistration>)QueryUtil.list(q,
							getDialect(), start, end);
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

	public void removeByU_ME(long userId, long meetupsEntryId)
		throws NoSuchMeetupsRegistrationException, SystemException {
		MeetupsRegistration meetupsRegistration = findByU_ME(userId,
				meetupsEntryId);

		remove(meetupsRegistration);
	}

	public void removeByME_S(long meetupsEntryId, int status)
		throws SystemException {
		for (MeetupsRegistration meetupsRegistration : findByME_S(
				meetupsEntryId, status)) {
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
			result = FinderCacheUtil.getResult(finderClassName,
					finderMethodName, finderParams, finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.sn.model.MeetupsRegistration WHERE ");

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

	public int countByU_ME(long userId, long meetupsEntryId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = MeetupsRegistrationModelImpl.CACHE_ENABLED;
		String finderClassName = MeetupsRegistration.class.getName();
		String finderMethodName = "countByU_ME";
		String[] finderParams = new String[] {
				Long.class.getName(), Long.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(userId), new Long(meetupsEntryId)
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
				query.append(
					"FROM com.liferay.sn.model.MeetupsRegistration WHERE ");

				query.append("userId = ?");

				query.append(" AND ");

				query.append("meetupsEntryId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(meetupsEntryId);

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

	public int countByME_S(long meetupsEntryId, int status)
		throws SystemException {
		boolean finderClassNameCacheEnabled = MeetupsRegistrationModelImpl.CACHE_ENABLED;
		String finderClassName = MeetupsRegistration.class.getName();
		String finderMethodName = "countByME_S";
		String[] finderParams = new String[] {
				Long.class.getName(), Integer.class.getName()
			};
		Object[] finderArgs = new Object[] {
				new Long(meetupsEntryId), new Integer(status)
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
				query.append(
					"FROM com.liferay.sn.model.MeetupsRegistration WHERE ");

				query.append("meetupsEntryId = ?");

				query.append(" AND ");

				query.append("status = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(meetupsEntryId);

				qPos.add(status);

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
		boolean finderClassNameCacheEnabled = MeetupsRegistrationModelImpl.CACHE_ENABLED;
		String finderClassName = MeetupsRegistration.class.getName();
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
						"SELECT COUNT(*) FROM com.liferay.sn.model.MeetupsRegistration");

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
						"value.object.listener.com.liferay.sn.model.MeetupsRegistration")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<MeetupsRegistration>> listenersList = new ArrayList<ModelListener<MeetupsRegistration>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<MeetupsRegistration>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(name = "com.liferay.sn.service.persistence.JIRAActionPersistence.impl")
	protected com.liferay.sn.service.persistence.JIRAActionPersistence jiraActionPersistence;
	@BeanReference(name = "com.liferay.sn.service.persistence.JIRAChangeGroupPersistence.impl")
	protected com.liferay.sn.service.persistence.JIRAChangeGroupPersistence jiraChangeGroupPersistence;
	@BeanReference(name = "com.liferay.sn.service.persistence.JIRAChangeItemPersistence.impl")
	protected com.liferay.sn.service.persistence.JIRAChangeItemPersistence jiraChangeItemPersistence;
	@BeanReference(name = "com.liferay.sn.service.persistence.JIRAIssuePersistence.impl")
	protected com.liferay.sn.service.persistence.JIRAIssuePersistence jiraIssuePersistence;
	@BeanReference(name = "com.liferay.sn.service.persistence.MeetupsEntryPersistence.impl")
	protected com.liferay.sn.service.persistence.MeetupsEntryPersistence meetupsEntryPersistence;
	@BeanReference(name = "com.liferay.sn.service.persistence.MeetupsRegistrationPersistence.impl")
	protected com.liferay.sn.service.persistence.MeetupsRegistrationPersistence meetupsRegistrationPersistence;
	@BeanReference(name = "com.liferay.sn.service.persistence.SVNRepositoryPersistence.impl")
	protected com.liferay.sn.service.persistence.SVNRepositoryPersistence svnRepositoryPersistence;
	@BeanReference(name = "com.liferay.sn.service.persistence.SVNRevisionPersistence.impl")
	protected com.liferay.sn.service.persistence.SVNRevisionPersistence svnRevisionPersistence;
	@BeanReference(name = "com.liferay.sn.service.persistence.WallEntryPersistence.impl")
	protected com.liferay.sn.service.persistence.WallEntryPersistence wallEntryPersistence;
	private static Log _log = LogFactoryUtil.getLog(MeetupsRegistrationPersistenceImpl.class);
}