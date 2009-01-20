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

import com.liferay.kb.knowledgebase.NoSuchArticleResourceException;
import com.liferay.kb.knowledgebase.model.KBArticleResource;
import com.liferay.kb.knowledgebase.model.impl.KBArticleResourceImpl;
import com.liferay.kb.knowledgebase.model.impl.KBArticleResourceModelImpl;

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
 * <a href="KBArticleResourcePersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBArticleResourcePersistenceImpl extends BasePersistenceImpl
	implements KBArticleResourcePersistence {
	public KBArticleResource create(long resourcePrimKey) {
		KBArticleResource kbArticleResource = new KBArticleResourceImpl();

		kbArticleResource.setNew(true);
		kbArticleResource.setPrimaryKey(resourcePrimKey);

		return kbArticleResource;
	}

	public KBArticleResource remove(long resourcePrimKey)
		throws NoSuchArticleResourceException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KBArticleResource kbArticleResource = (KBArticleResource)session.get(KBArticleResourceImpl.class,
					new Long(resourcePrimKey));

			if (kbArticleResource == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"No KBArticleResource exists with the primary key " +
						resourcePrimKey);
				}

				throw new NoSuchArticleResourceException(
					"No KBArticleResource exists with the primary key " +
					resourcePrimKey);
			}

			return remove(kbArticleResource);
		}
		catch (NoSuchArticleResourceException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public KBArticleResource remove(KBArticleResource kbArticleResource)
		throws SystemException {
		for (ModelListener listener : listeners) {
			listener.onBeforeRemove(kbArticleResource);
		}

		kbArticleResource = removeImpl(kbArticleResource);

		for (ModelListener listener : listeners) {
			listener.onAfterRemove(kbArticleResource);
		}

		return kbArticleResource;
	}

	protected KBArticleResource removeImpl(KBArticleResource kbArticleResource)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(KBArticleResourceImpl.class,
						kbArticleResource.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(kbArticleResource);

			session.flush();

			return kbArticleResource;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(KBArticleResource.class.getName());
		}
	}

	public KBArticleResource update(KBArticleResource kbArticleResource)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(KBArticleResource kbArticleResource) method. Use update(KBArticleResource kbArticleResource, boolean merge) instead.");
		}

		return update(kbArticleResource, false);
	}

	public KBArticleResource update(KBArticleResource kbArticleResource,
		boolean merge) throws SystemException {
		boolean isNew = kbArticleResource.isNew();

		for (ModelListener listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(kbArticleResource);
			}
			else {
				listener.onBeforeUpdate(kbArticleResource);
			}
		}

		kbArticleResource = updateImpl(kbArticleResource, merge);

		for (ModelListener listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(kbArticleResource);
			}
			else {
				listener.onAfterUpdate(kbArticleResource);
			}
		}

		return kbArticleResource;
	}

	public KBArticleResource updateImpl(
		com.liferay.kb.knowledgebase.model.KBArticleResource kbArticleResource,
		boolean merge) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kbArticleResource, merge);

			kbArticleResource.setNew(false);

			return kbArticleResource;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(KBArticleResource.class.getName());
		}
	}

	public KBArticleResource findByPrimaryKey(long resourcePrimKey)
		throws NoSuchArticleResourceException, SystemException {
		KBArticleResource kbArticleResource = fetchByPrimaryKey(resourcePrimKey);

		if (kbArticleResource == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No KBArticleResource exists with the primary key " +
					resourcePrimKey);
			}

			throw new NoSuchArticleResourceException(
				"No KBArticleResource exists with the primary key " +
				resourcePrimKey);
		}

		return kbArticleResource;
	}

	public KBArticleResource fetchByPrimaryKey(long resourcePrimKey)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (KBArticleResource)session.get(KBArticleResourceImpl.class,
				new Long(resourcePrimKey));
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public KBArticleResource findByG_T(long groupId, String title)
		throws NoSuchArticleResourceException, SystemException {
		KBArticleResource kbArticleResource = fetchByG_T(groupId, title);

		if (kbArticleResource == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No KBArticleResource exists with the key {");

			msg.append("groupId=" + groupId);

			msg.append(", ");
			msg.append("title=" + title);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchArticleResourceException(msg.toString());
		}

		return kbArticleResource;
	}

	public KBArticleResource fetchByG_T(long groupId, String title)
		throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleResourceModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticleResource.class.getName();
		String finderMethodName = "fetchByG_T";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName()
			};
		Object[] finderArgs = new Object[] { new Long(groupId), title };

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
					"FROM com.liferay.kb.knowledgebase.model.KBArticleResource WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				if (title == null) {
					query.append("title IS NULL");
				}
				else {
					query.append("title = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (title != null) {
					qPos.add(title);
				}

				List<KBArticleResource> list = q.list();

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
			List<KBArticleResource> list = (List<KBArticleResource>)result;

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

	public List<KBArticleResource> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<KBArticleResource> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<KBArticleResource> findAll(int start, int end,
		OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleResourceModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticleResource.class.getName();
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
					"FROM com.liferay.kb.knowledgebase.model.KBArticleResource ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				List<KBArticleResource> list = null;

				if (obc == null) {
					list = (List<KBArticleResource>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KBArticleResource>)QueryUtil.list(q,
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
			return (List<KBArticleResource>)result;
		}
	}

	public void removeByG_T(long groupId, String title)
		throws NoSuchArticleResourceException, SystemException {
		KBArticleResource kbArticleResource = findByG_T(groupId, title);

		remove(kbArticleResource);
	}

	public void removeAll() throws SystemException {
		for (KBArticleResource kbArticleResource : findAll()) {
			remove(kbArticleResource);
		}
	}

	public int countByG_T(long groupId, String title) throws SystemException {
		boolean finderClassNameCacheEnabled = KBArticleResourceModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticleResource.class.getName();
		String finderMethodName = "countByG_T";
		String[] finderParams = new String[] {
				Long.class.getName(), String.class.getName()
			};
		Object[] finderArgs = new Object[] { new Long(groupId), title };

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
					"FROM com.liferay.kb.knowledgebase.model.KBArticleResource WHERE ");

				query.append("groupId = ?");

				query.append(" AND ");

				if (title == null) {
					query.append("title IS NULL");
				}
				else {
					query.append("title = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (title != null) {
					qPos.add(title);
				}

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
		boolean finderClassNameCacheEnabled = KBArticleResourceModelImpl.CACHE_ENABLED;
		String finderClassName = KBArticleResource.class.getName();
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
						"SELECT COUNT(*) FROM com.liferay.kb.knowledgebase.model.KBArticleResource");

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
						"value.object.listener.com.liferay.kb.knowledgebase.model.KBArticleResource")));

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
	private static Log _log = LogFactoryUtil.getLog(KBArticleResourcePersistenceImpl.class);
}