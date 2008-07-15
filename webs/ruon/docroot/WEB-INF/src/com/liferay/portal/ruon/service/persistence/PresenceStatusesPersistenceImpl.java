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

 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.sun.com/cddl/cddl.html and
 * legal/CDDLv1.0.txt. See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at legal/CDDLv1.0.txt.
 *
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Copyright 2008 Sun Microsystems Inc. All rights reserved.
 **/

package com.liferay.portal.ruon.service.persistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.ruon.NoSuchPresenceStatusesException;
import com.liferay.portal.ruon.model.PresenceStatuses;
import com.liferay.portal.ruon.model.impl.PresenceStatusesImpl;
import com.liferay.portal.ruon.model.impl.PresenceStatusesModelImpl;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="PresenceStatusesPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Murali Krishna Reddy
 * @author Brian Wing Shun Chan
 *
 */
public class PresenceStatusesPersistenceImpl extends BasePersistenceImpl
	implements PresenceStatusesPersistence {
	public PresenceStatuses create(long presenceStatusId) {
		PresenceStatuses presenceStatuses = new PresenceStatusesImpl();

		presenceStatuses.setNew(true);
		presenceStatuses.setPrimaryKey(presenceStatusId);

		return presenceStatuses;
	}

	public PresenceStatuses remove(long presenceStatusId)
		throws NoSuchPresenceStatusesException, SystemException {
		Session session = null;

		try {
			session = openSession();

			PresenceStatuses presenceStatuses = (PresenceStatuses)session.get(PresenceStatusesImpl.class,
					new Long(presenceStatusId));

			if (presenceStatuses == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"No PresenceStatuses exists with the primary key " +
						presenceStatusId);
				}

				throw new NoSuchPresenceStatusesException(
					"No PresenceStatuses exists with the primary key " +
					presenceStatusId);
			}

			return remove(presenceStatuses);
		}
		catch (NoSuchPresenceStatusesException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public PresenceStatuses remove(PresenceStatuses presenceStatuses)
		throws SystemException {
		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onBeforeRemove(presenceStatuses);
			}
		}

		presenceStatuses = removeImpl(presenceStatuses);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onAfterRemove(presenceStatuses);
			}
		}

		return presenceStatuses;
	}

	protected PresenceStatuses removeImpl(PresenceStatuses presenceStatuses)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			session.delete(presenceStatuses);

			session.flush();

			return presenceStatuses;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(PresenceStatuses.class.getName());
		}
	}

	public PresenceStatuses update(PresenceStatuses presenceStatuses)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(PresenceStatuses presenceStatuses) method. Use update(PresenceStatuses presenceStatuses, boolean merge) instead.");
		}

		return update(presenceStatuses, false);
	}

	public PresenceStatuses update(PresenceStatuses presenceStatuses,
		boolean merge) throws SystemException {
		boolean isNew = presenceStatuses.isNew();

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onBeforeCreate(presenceStatuses);
				}
				else {
					listener.onBeforeUpdate(presenceStatuses);
				}
			}
		}

		presenceStatuses = updateImpl(presenceStatuses, merge);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onAfterCreate(presenceStatuses);
				}
				else {
					listener.onAfterUpdate(presenceStatuses);
				}
			}
		}

		return presenceStatuses;
	}

	public PresenceStatuses updateImpl(
		com.liferay.portal.ruon.model.PresenceStatuses presenceStatuses,
		boolean merge) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (merge) {
				session.merge(presenceStatuses);
			}
			else {
				if (presenceStatuses.isNew()) {
					session.save(presenceStatuses);
				}
			}

			session.flush();

			presenceStatuses.setNew(false);

			return presenceStatuses;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(PresenceStatuses.class.getName());
		}
	}

	public PresenceStatuses findByPrimaryKey(long presenceStatusId)
		throws NoSuchPresenceStatusesException, SystemException {
		PresenceStatuses presenceStatuses = fetchByPrimaryKey(presenceStatusId);

		if (presenceStatuses == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No PresenceStatuses exists with the primary key " +
					presenceStatusId);
			}

			throw new NoSuchPresenceStatusesException(
				"No PresenceStatuses exists with the primary key " +
				presenceStatusId);
		}

		return presenceStatuses;
	}

	public PresenceStatuses fetchByPrimaryKey(long presenceStatusId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (PresenceStatuses)session.get(PresenceStatusesImpl.class,
				new Long(presenceStatusId));
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

	public List<PresenceStatuses> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<PresenceStatuses> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<PresenceStatuses> findAll(int start, int end,
		OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = PresenceStatusesModelImpl.CACHE_ENABLED;
		String finderClassName = PresenceStatuses.class.getName();
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

				query.append(
					"FROM com.liferay.portal.ruon.model.PresenceStatuses ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				List<PresenceStatuses> list = (List<PresenceStatuses>)QueryUtil.list(q,
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
			return (List<PresenceStatuses>)result;
		}
	}

	public void removeAll() throws SystemException {
		for (PresenceStatuses presenceStatuses : findAll()) {
			remove(presenceStatuses);
		}
	}

	public int countAll() throws SystemException {
		boolean finderClassNameCacheEnabled = PresenceStatusesModelImpl.CACHE_ENABLED;
		String finderClassName = PresenceStatuses.class.getName();
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
						"SELECT COUNT(*) FROM com.liferay.portal.ruon.model.PresenceStatuses");

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

	protected void init() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.ruon.model.PresenceStatuses")));

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

	private static Log _log = LogFactory.getLog(PresenceStatusesPersistenceImpl.class);
	private ModelListener[] _listeners = new ModelListener[0];
}