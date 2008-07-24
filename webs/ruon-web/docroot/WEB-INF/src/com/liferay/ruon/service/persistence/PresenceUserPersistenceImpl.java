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

package com.liferay.ruon.service.persistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bean.InitializingBean;
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
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.ruon.NoSuchPresenceUserException;
import com.liferay.ruon.model.PresenceUser;
import com.liferay.ruon.model.impl.PresenceUserImpl;
import com.liferay.ruon.model.impl.PresenceUserModelImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="PresenceUserPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PresenceUserPersistenceImpl extends BasePersistenceImpl
	implements PresenceUserPersistence, InitializingBean {
	public PresenceUser create(long presenceUserId) {
		PresenceUser presenceUser = new PresenceUserImpl();

		presenceUser.setNew(true);
		presenceUser.setPrimaryKey(presenceUserId);

		return presenceUser;
	}

	public PresenceUser remove(long presenceUserId)
		throws NoSuchPresenceUserException, SystemException {
		Session session = null;

		try {
			session = openSession();

			PresenceUser presenceUser = (PresenceUser)session.get(PresenceUserImpl.class,
					new Long(presenceUserId));

			if (presenceUser == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No PresenceUser exists with the primary key " +
						presenceUserId);
				}

				throw new NoSuchPresenceUserException(
					"No PresenceUser exists with the primary key " +
					presenceUserId);
			}

			return remove(presenceUser);
		}
		catch (NoSuchPresenceUserException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public PresenceUser remove(PresenceUser presenceUser)
		throws SystemException {
		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onBeforeRemove(presenceUser);
			}
		}

		presenceUser = removeImpl(presenceUser);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onAfterRemove(presenceUser);
			}
		}

		return presenceUser;
	}

	protected PresenceUser removeImpl(PresenceUser presenceUser)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			session.delete(presenceUser);

			session.flush();

			return presenceUser;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(PresenceUser.class.getName());
		}
	}

	public PresenceUser update(PresenceUser presenceUser)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(PresenceUser presenceUser) method. Use update(PresenceUser presenceUser, boolean merge) instead.");
		}

		return update(presenceUser, false);
	}

	public PresenceUser update(PresenceUser presenceUser, boolean merge)
		throws SystemException {
		boolean isNew = presenceUser.isNew();

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onBeforeCreate(presenceUser);
				}
				else {
					listener.onBeforeUpdate(presenceUser);
				}
			}
		}

		presenceUser = updateImpl(presenceUser, merge);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onAfterCreate(presenceUser);
				}
				else {
					listener.onAfterUpdate(presenceUser);
				}
			}
		}

		return presenceUser;
	}

	public PresenceUser updateImpl(
		com.liferay.ruon.model.PresenceUser presenceUser, boolean merge)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (merge) {
				session.merge(presenceUser);
			}
			else {
				if (presenceUser.isNew()) {
					session.save(presenceUser);
				}
			}

			session.flush();

			presenceUser.setNew(false);

			return presenceUser;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(PresenceUser.class.getName());
		}
	}

	public PresenceUser findByPrimaryKey(long presenceUserId)
		throws NoSuchPresenceUserException, SystemException {
		PresenceUser presenceUser = fetchByPrimaryKey(presenceUserId);

		if (presenceUser == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No PresenceUser exists with the primary key " +
					presenceUserId);
			}

			throw new NoSuchPresenceUserException(
				"No PresenceUser exists with the primary key " +
				presenceUserId);
		}

		return presenceUser;
	}

	public PresenceUser fetchByPrimaryKey(long presenceUserId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (PresenceUser)session.get(PresenceUserImpl.class,
				new Long(presenceUserId));
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

	public List<PresenceUser> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<PresenceUser> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<PresenceUser> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = PresenceUserModelImpl.CACHE_ENABLED;
		String finderClassName = PresenceUser.class.getName();
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

				query.append("FROM com.liferay.ruon.model.PresenceUser ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				List<PresenceUser> list = (List<PresenceUser>)QueryUtil.list(q,
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
			return (List<PresenceUser>)result;
		}
	}

	public void removeAll() throws SystemException {
		for (PresenceUser presenceUser : findAll()) {
			remove(presenceUser);
		}
	}

	public int countAll() throws SystemException {
		boolean finderClassNameCacheEnabled = PresenceUserModelImpl.CACHE_ENABLED;
		String finderClassName = PresenceUser.class.getName();
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
						"SELECT COUNT(*) FROM com.liferay.ruon.model.PresenceUser");

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
						"value.object.listener.com.liferay.ruon.model.PresenceUser")));

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

	private static Log _log = LogFactory.getLog(PresenceUserPersistenceImpl.class);
	private ModelListener[] _listeners = new ModelListener[0];
}