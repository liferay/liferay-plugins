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

package com.liferay.knowledgebase.service.persistence;

import com.liferay.knowledgebase.NoSuchFeedbackStatsException;
import com.liferay.knowledgebase.model.KBFeedbackStats;
import com.liferay.knowledgebase.model.impl.KBFeedbackStatsImpl;
import com.liferay.knowledgebase.model.impl.KBFeedbackStatsModelImpl;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bean.InitializingBean;
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
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="KBFeedbackStatsPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBFeedbackStatsPersistenceImpl extends BasePersistenceImpl
	implements KBFeedbackStatsPersistence, InitializingBean {
	public KBFeedbackStats create(long kbFeedbackStatsId) {
		KBFeedbackStats kbFeedbackStats = new KBFeedbackStatsImpl();

		kbFeedbackStats.setNew(true);
		kbFeedbackStats.setPrimaryKey(kbFeedbackStatsId);

		return kbFeedbackStats;
	}

	public KBFeedbackStats remove(long kbFeedbackStatsId)
		throws NoSuchFeedbackStatsException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KBFeedbackStats kbFeedbackStats = (KBFeedbackStats)session.get(KBFeedbackStatsImpl.class,
					new Long(kbFeedbackStatsId));

			if (kbFeedbackStats == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No KBFeedbackStats exists with the primary key " +
						kbFeedbackStatsId);
				}

				throw new NoSuchFeedbackStatsException(
					"No KBFeedbackStats exists with the primary key " +
					kbFeedbackStatsId);
			}

			return remove(kbFeedbackStats);
		}
		catch (NoSuchFeedbackStatsException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public KBFeedbackStats remove(KBFeedbackStats kbFeedbackStats)
		throws SystemException {
		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onBeforeRemove(kbFeedbackStats);
			}
		}

		kbFeedbackStats = removeImpl(kbFeedbackStats);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onAfterRemove(kbFeedbackStats);
			}
		}

		return kbFeedbackStats;
	}

	protected KBFeedbackStats removeImpl(KBFeedbackStats kbFeedbackStats)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			session.delete(kbFeedbackStats);

			session.flush();

			return kbFeedbackStats;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(KBFeedbackStats.class.getName());
		}
	}

	public KBFeedbackStats update(KBFeedbackStats kbFeedbackStats)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(KBFeedbackStats kbFeedbackStats) method. Use update(KBFeedbackStats kbFeedbackStats, boolean merge) instead.");
		}

		return update(kbFeedbackStats, false);
	}

	public KBFeedbackStats update(KBFeedbackStats kbFeedbackStats, boolean merge)
		throws SystemException {
		boolean isNew = kbFeedbackStats.isNew();

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onBeforeCreate(kbFeedbackStats);
				}
				else {
					listener.onBeforeUpdate(kbFeedbackStats);
				}
			}
		}

		kbFeedbackStats = updateImpl(kbFeedbackStats, merge);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onAfterCreate(kbFeedbackStats);
				}
				else {
					listener.onAfterUpdate(kbFeedbackStats);
				}
			}
		}

		return kbFeedbackStats;
	}

	public KBFeedbackStats updateImpl(
		com.liferay.knowledgebase.model.KBFeedbackStats kbFeedbackStats,
		boolean merge) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (merge) {
				session.merge(kbFeedbackStats);
			}
			else {
				if (kbFeedbackStats.isNew()) {
					session.save(kbFeedbackStats);
				}
			}

			session.flush();

			kbFeedbackStats.setNew(false);

			return kbFeedbackStats;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(KBFeedbackStats.class.getName());
		}
	}

	public KBFeedbackStats findByPrimaryKey(long kbFeedbackStatsId)
		throws NoSuchFeedbackStatsException, SystemException {
		KBFeedbackStats kbFeedbackStats = fetchByPrimaryKey(kbFeedbackStatsId);

		if (kbFeedbackStats == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No KBFeedbackStats exists with the primary key " +
					kbFeedbackStatsId);
			}

			throw new NoSuchFeedbackStatsException(
				"No KBFeedbackStats exists with the primary key " +
				kbFeedbackStatsId);
		}

		return kbFeedbackStats;
	}

	public KBFeedbackStats fetchByPrimaryKey(long kbFeedbackStatsId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (KBFeedbackStats)session.get(KBFeedbackStatsImpl.class,
				new Long(kbFeedbackStatsId));
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public KBFeedbackStats findByArticleId(long articleId)
		throws NoSuchFeedbackStatsException, SystemException {
		KBFeedbackStats kbFeedbackStats = fetchByArticleId(articleId);

		if (kbFeedbackStats == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBFeedbackStats exists with the key {");

			msg.append("articleId=" + articleId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchFeedbackStatsException(msg.toString());
		}

		return kbFeedbackStats;
	}

	public KBFeedbackStats fetchByArticleId(long articleId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBFeedbackStatsModelImpl.CACHE_ENABLED;
		String finderClassName = KBFeedbackStats.class.getName();
		String finderMethodName = "fetchByArticleId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(articleId) };

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
					"FROM com.liferay.knowledgebase.model.KBFeedbackStats WHERE ");

				query.append("articleId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(articleId);

				List<KBFeedbackStats> list = q.list();

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
			List<KBFeedbackStats> list = (List<KBFeedbackStats>)result;

			if (list.size() == 0) {
				return null;
			}
			else {
				return list.get(0);
			}
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

	public List<KBFeedbackStats> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<KBFeedbackStats> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<KBFeedbackStats> findAll(int start, int end,
		OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = KBFeedbackStatsModelImpl.CACHE_ENABLED;
		String finderClassName = KBFeedbackStats.class.getName();
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
					"FROM com.liferay.knowledgebase.model.KBFeedbackStats ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				List<KBFeedbackStats> list = (List<KBFeedbackStats>)QueryUtil.list(q,
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
			return (List<KBFeedbackStats>)result;
		}
	}

	public void removeByArticleId(long articleId)
		throws NoSuchFeedbackStatsException, SystemException {
		KBFeedbackStats kbFeedbackStats = findByArticleId(articleId);

		remove(kbFeedbackStats);
	}

	public void removeAll() throws SystemException {
		for (KBFeedbackStats kbFeedbackStats : findAll()) {
			remove(kbFeedbackStats);
		}
	}

	public int countByArticleId(long articleId) throws SystemException {
		boolean finderClassNameCacheEnabled = KBFeedbackStatsModelImpl.CACHE_ENABLED;
		String finderClassName = KBFeedbackStats.class.getName();
		String finderMethodName = "countByArticleId";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(articleId) };

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
					"FROM com.liferay.knowledgebase.model.KBFeedbackStats WHERE ");

				query.append("articleId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(articleId);

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
		boolean finderClassNameCacheEnabled = KBFeedbackStatsModelImpl.CACHE_ENABLED;
		String finderClassName = KBFeedbackStats.class.getName();
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
						"SELECT COUNT(*) FROM com.liferay.knowledgebase.model.KBFeedbackStats");

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
						"value.object.listener.com.liferay.knowledgebase.model.KBFeedbackStats")));

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

	private static Log _log = LogFactory.getLog(KBFeedbackStatsPersistenceImpl.class);
	private ModelListener[] _listeners = new ModelListener[0];
}