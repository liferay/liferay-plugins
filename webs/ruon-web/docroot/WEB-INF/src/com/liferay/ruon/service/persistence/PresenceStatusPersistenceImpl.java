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

import com.liferay.ruon.NoSuchPresenceStatusException;
import com.liferay.ruon.model.PresenceStatus;
import com.liferay.ruon.model.impl.PresenceStatusImpl;
import com.liferay.ruon.model.impl.PresenceStatusModelImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="PresenceStatusPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PresenceStatusPersistenceImpl extends BasePersistenceImpl
	implements PresenceStatusPersistence, InitializingBean {
	public PresenceStatus create(long presenceStatusId) {
		PresenceStatus presenceStatus = new PresenceStatusImpl();

		presenceStatus.setNew(true);
		presenceStatus.setPrimaryKey(presenceStatusId);

		return presenceStatus;
	}

	public PresenceStatus remove(long presenceStatusId)
		throws NoSuchPresenceStatusException, SystemException {
		Session session = null;

		try {
			session = openSession();

			PresenceStatus presenceStatus = (PresenceStatus)session.get(PresenceStatusImpl.class,
					new Long(presenceStatusId));

			if (presenceStatus == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No PresenceStatus exists with the primary key " +
						presenceStatusId);
				}

				throw new NoSuchPresenceStatusException(
					"No PresenceStatus exists with the primary key " +
					presenceStatusId);
			}

			return remove(presenceStatus);
		}
		catch (NoSuchPresenceStatusException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public PresenceStatus remove(PresenceStatus presenceStatus)
		throws SystemException {
		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onBeforeRemove(presenceStatus);
			}
		}

		presenceStatus = removeImpl(presenceStatus);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onAfterRemove(presenceStatus);
			}
		}

		return presenceStatus;
	}

	protected PresenceStatus removeImpl(PresenceStatus presenceStatus)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			session.delete(presenceStatus);

			session.flush();

			return presenceStatus;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(PresenceStatus.class.getName());
		}
	}

	public PresenceStatus update(PresenceStatus presenceStatus)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(PresenceStatus presenceStatus) method. Use update(PresenceStatus presenceStatus, boolean merge) instead.");
		}

		return update(presenceStatus, false);
	}

	public PresenceStatus update(PresenceStatus presenceStatus, boolean merge)
		throws SystemException {
		boolean isNew = presenceStatus.isNew();

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onBeforeCreate(presenceStatus);
				}
				else {
					listener.onBeforeUpdate(presenceStatus);
				}
			}
		}

		presenceStatus = updateImpl(presenceStatus, merge);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onAfterCreate(presenceStatus);
				}
				else {
					listener.onAfterUpdate(presenceStatus);
				}
			}
		}

		return presenceStatus;
	}

	public PresenceStatus updateImpl(
		com.liferay.ruon.model.PresenceStatus presenceStatus, boolean merge)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (merge) {
				session.merge(presenceStatus);
			}
			else {
				if (presenceStatus.isNew()) {
					session.save(presenceStatus);
				}
			}

			session.flush();

			presenceStatus.setNew(false);

			return presenceStatus;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(PresenceStatus.class.getName());
		}
	}

	public PresenceStatus findByPrimaryKey(long presenceStatusId)
		throws NoSuchPresenceStatusException, SystemException {
		PresenceStatus presenceStatus = fetchByPrimaryKey(presenceStatusId);

		if (presenceStatus == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No PresenceStatus exists with the primary key " +
					presenceStatusId);
			}

			throw new NoSuchPresenceStatusException(
				"No PresenceStatus exists with the primary key " +
				presenceStatusId);
		}

		return presenceStatus;
	}

	public PresenceStatus fetchByPrimaryKey(long presenceStatusId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (PresenceStatus)session.get(PresenceStatusImpl.class,
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

	public List<PresenceStatus> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<PresenceStatus> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<PresenceStatus> findAll(int start, int end,
		OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = PresenceStatusModelImpl.CACHE_ENABLED;
		String finderClassName = PresenceStatus.class.getName();
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

				query.append("FROM com.liferay.ruon.model.PresenceStatus ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				List<PresenceStatus> list = (List<PresenceStatus>)QueryUtil.list(q,
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
			return (List<PresenceStatus>)result;
		}
	}

	public void removeAll() throws SystemException {
		for (PresenceStatus presenceStatus : findAll()) {
			remove(presenceStatus);
		}
	}

	public int countAll() throws SystemException {
		boolean finderClassNameCacheEnabled = PresenceStatusModelImpl.CACHE_ENABLED;
		String finderClassName = PresenceStatus.class.getName();
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
						"SELECT COUNT(*) FROM com.liferay.ruon.model.PresenceStatus");

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
						"value.object.listener.com.liferay.ruon.model.PresenceStatus")));

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

	private static Log _log = LogFactory.getLog(PresenceStatusPersistenceImpl.class);
	private ModelListener[] _listeners = new ModelListener[0];
}