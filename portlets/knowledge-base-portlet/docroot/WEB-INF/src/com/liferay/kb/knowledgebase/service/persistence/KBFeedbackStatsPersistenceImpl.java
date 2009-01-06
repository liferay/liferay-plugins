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

package com.liferay.kb.knowledgebase.service.persistence;

import com.liferay.kb.knowledgebase.NoSuchFeedbackStatsException;
import com.liferay.kb.knowledgebase.model.KBFeedbackStats;
import com.liferay.kb.knowledgebase.model.impl.KBFeedbackStatsImpl;
import com.liferay.kb.knowledgebase.model.impl.KBFeedbackStatsModelImpl;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
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
	implements KBFeedbackStatsPersistence {
	public KBFeedbackStats create(long feedbackStatsId) {
		KBFeedbackStats kbFeedbackStats = new KBFeedbackStatsImpl();

		kbFeedbackStats.setNew(true);
		kbFeedbackStats.setPrimaryKey(feedbackStatsId);

		return kbFeedbackStats;
	}

	public KBFeedbackStats remove(long feedbackStatsId)
		throws NoSuchFeedbackStatsException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KBFeedbackStats kbFeedbackStats = (KBFeedbackStats)session.get(KBFeedbackStatsImpl.class,
					new Long(feedbackStatsId));

			if (kbFeedbackStats == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No KBFeedbackStats exists with the primary key " +
						feedbackStatsId);
				}

				throw new NoSuchFeedbackStatsException(
					"No KBFeedbackStats exists with the primary key " +
					feedbackStatsId);
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
		for (ModelListener listener : listeners) {
			listener.onBeforeRemove(kbFeedbackStats);
		}

		kbFeedbackStats = removeImpl(kbFeedbackStats);

		for (ModelListener listener : listeners) {
			listener.onAfterRemove(kbFeedbackStats);
		}

		return kbFeedbackStats;
	}

	protected KBFeedbackStats removeImpl(KBFeedbackStats kbFeedbackStats)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(KBFeedbackStatsImpl.class,
						kbFeedbackStats.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

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

		for (ModelListener listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(kbFeedbackStats);
			}
			else {
				listener.onBeforeUpdate(kbFeedbackStats);
			}
		}

		kbFeedbackStats = updateImpl(kbFeedbackStats, merge);

		for (ModelListener listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(kbFeedbackStats);
			}
			else {
				listener.onAfterUpdate(kbFeedbackStats);
			}
		}

		return kbFeedbackStats;
	}

	public KBFeedbackStats updateImpl(
		com.liferay.kb.knowledgebase.model.KBFeedbackStats kbFeedbackStats,
		boolean merge) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kbFeedbackStats, merge);

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

	public KBFeedbackStats findByPrimaryKey(long feedbackStatsId)
		throws NoSuchFeedbackStatsException, SystemException {
		KBFeedbackStats kbFeedbackStats = fetchByPrimaryKey(feedbackStatsId);

		if (kbFeedbackStats == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No KBFeedbackStats exists with the primary key " +
					feedbackStatsId);
			}

			throw new NoSuchFeedbackStatsException(
				"No KBFeedbackStats exists with the primary key " +
				feedbackStatsId);
		}

		return kbFeedbackStats;
	}

	public KBFeedbackStats fetchByPrimaryKey(long feedbackStatsId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (KBFeedbackStats)session.get(KBFeedbackStatsImpl.class,
				new Long(feedbackStatsId));
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public KBFeedbackStats findByArticleResourcePrimKey(
		long articleResourcePrimKey)
		throws NoSuchFeedbackStatsException, SystemException {
		KBFeedbackStats kbFeedbackStats = fetchByArticleResourcePrimKey(articleResourcePrimKey);

		if (kbFeedbackStats == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBFeedbackStats exists with the key {");

			msg.append("articleResourcePrimKey=" + articleResourcePrimKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchFeedbackStatsException(msg.toString());
		}

		return kbFeedbackStats;
	}

	public KBFeedbackStats fetchByArticleResourcePrimKey(
		long articleResourcePrimKey) throws SystemException {
		boolean finderClassNameCacheEnabled = KBFeedbackStatsModelImpl.CACHE_ENABLED;
		String finderClassName = KBFeedbackStats.class.getName();
		String finderMethodName = "fetchByArticleResourcePrimKey";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(articleResourcePrimKey) };

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
					"FROM com.liferay.kb.knowledgebase.model.KBFeedbackStats WHERE ");

				query.append("articleResourcePrimKey = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(articleResourcePrimKey);

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
					"FROM com.liferay.kb.knowledgebase.model.KBFeedbackStats ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				List<KBFeedbackStats> list = null;

				if (obc == null) {
					list = (List<KBFeedbackStats>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KBFeedbackStats>)QueryUtil.list(q,
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
			return (List<KBFeedbackStats>)result;
		}
	}

	public void removeByArticleResourcePrimKey(long articleResourcePrimKey)
		throws NoSuchFeedbackStatsException, SystemException {
		KBFeedbackStats kbFeedbackStats = findByArticleResourcePrimKey(articleResourcePrimKey);

		remove(kbFeedbackStats);
	}

	public void removeAll() throws SystemException {
		for (KBFeedbackStats kbFeedbackStats : findAll()) {
			remove(kbFeedbackStats);
		}
	}

	public int countByArticleResourcePrimKey(long articleResourcePrimKey)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBFeedbackStatsModelImpl.CACHE_ENABLED;
		String finderClassName = KBFeedbackStats.class.getName();
		String finderMethodName = "countByArticleResourcePrimKey";
		String[] finderParams = new String[] { Long.class.getName() };
		Object[] finderArgs = new Object[] { new Long(articleResourcePrimKey) };

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
					"FROM com.liferay.kb.knowledgebase.model.KBFeedbackStats WHERE ");

				query.append("articleResourcePrimKey = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(articleResourcePrimKey);

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
						"SELECT COUNT(*) FROM com.liferay.kb.knowledgebase.model.KBFeedbackStats");

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
						"value.object.listener.com.liferay.kb.knowledgebase.model.KBFeedbackStats")));

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

	@BeanReference(name = "com.liferay.kb.knowledgebase.service.persistence.KBArticlePersistence.impl")
	protected com.liferay.kb.knowledgebase.service.persistence.KBArticlePersistence kbArticlePersistence;
	@BeanReference(name = "com.liferay.kb.knowledgebase.service.persistence.KBArticleResourcePersistence.impl")
	protected com.liferay.kb.knowledgebase.service.persistence.KBArticleResourcePersistence kbArticleResourcePersistence;
	@BeanReference(name = "com.liferay.kb.knowledgebase.service.persistence.KBFeedbackEntryPersistence.impl")
	protected com.liferay.kb.knowledgebase.service.persistence.KBFeedbackEntryPersistence kbFeedbackEntryPersistence;
	@BeanReference(name = "com.liferay.kb.knowledgebase.service.persistence.KBFeedbackStatsPersistence.impl")
	protected com.liferay.kb.knowledgebase.service.persistence.KBFeedbackStatsPersistence kbFeedbackStatsPersistence;
	private static Log _log = LogFactory.getLog(KBFeedbackStatsPersistenceImpl.class);
}