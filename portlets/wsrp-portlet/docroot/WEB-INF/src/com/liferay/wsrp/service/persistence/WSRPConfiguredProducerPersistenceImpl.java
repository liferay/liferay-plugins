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

package com.liferay.wsrp.service.persistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
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

import com.liferay.wsrp.NoSuchConfiguredProducerException;
import com.liferay.wsrp.model.WSRPConfiguredProducer;
import com.liferay.wsrp.model.impl.WSRPConfiguredProducerImpl;
import com.liferay.wsrp.model.impl.WSRPConfiguredProducerModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="WSRPConfiguredProducerPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPConfiguredProducerPersistenceImpl extends BasePersistenceImpl
	implements WSRPConfiguredProducerPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = WSRPConfiguredProducerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_P_N = new FinderPath(WSRPConfiguredProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConfiguredProducerModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByP_N",
			new String[] { String.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_P_N = new FinderPath(WSRPConfiguredProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConfiguredProducerModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByP_N",
			new String[] {
				String.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_P_N = new FinderPath(WSRPConfiguredProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConfiguredProducerModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByP_N",
			new String[] { String.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(WSRPConfiguredProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConfiguredProducerModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WSRPConfiguredProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConfiguredProducerModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	public void cacheResult(WSRPConfiguredProducer wsrpConfiguredProducer) {
		EntityCacheUtil.putResult(WSRPConfiguredProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConfiguredProducerImpl.class,
			wsrpConfiguredProducer.getPrimaryKey(), wsrpConfiguredProducer);
	}

	public void cacheResult(
		List<WSRPConfiguredProducer> wsrpConfiguredProducers) {
		for (WSRPConfiguredProducer wsrpConfiguredProducer : wsrpConfiguredProducers) {
			if (EntityCacheUtil.getResult(
						WSRPConfiguredProducerModelImpl.ENTITY_CACHE_ENABLED,
						WSRPConfiguredProducerImpl.class,
						wsrpConfiguredProducer.getPrimaryKey(), this) == null) {
				cacheResult(wsrpConfiguredProducer);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(WSRPConfiguredProducerImpl.class.getName());
		EntityCacheUtil.clearCache(WSRPConfiguredProducerImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public WSRPConfiguredProducer create(long configuredProducerId) {
		WSRPConfiguredProducer wsrpConfiguredProducer = new WSRPConfiguredProducerImpl();

		wsrpConfiguredProducer.setNew(true);
		wsrpConfiguredProducer.setPrimaryKey(configuredProducerId);

		return wsrpConfiguredProducer;
	}

	public WSRPConfiguredProducer remove(long configuredProducerId)
		throws NoSuchConfiguredProducerException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WSRPConfiguredProducer wsrpConfiguredProducer = (WSRPConfiguredProducer)session.get(WSRPConfiguredProducerImpl.class,
					new Long(configuredProducerId));

			if (wsrpConfiguredProducer == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"No WSRPConfiguredProducer exists with the primary key " +
						configuredProducerId);
				}

				throw new NoSuchConfiguredProducerException(
					"No WSRPConfiguredProducer exists with the primary key " +
					configuredProducerId);
			}

			return remove(wsrpConfiguredProducer);
		}
		catch (NoSuchConfiguredProducerException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public WSRPConfiguredProducer remove(
		WSRPConfiguredProducer wsrpConfiguredProducer)
		throws SystemException {
		for (ModelListener<WSRPConfiguredProducer> listener : listeners) {
			listener.onBeforeRemove(wsrpConfiguredProducer);
		}

		wsrpConfiguredProducer = removeImpl(wsrpConfiguredProducer);

		for (ModelListener<WSRPConfiguredProducer> listener : listeners) {
			listener.onAfterRemove(wsrpConfiguredProducer);
		}

		return wsrpConfiguredProducer;
	}

	protected WSRPConfiguredProducer removeImpl(
		WSRPConfiguredProducer wsrpConfiguredProducer)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (wsrpConfiguredProducer.isCachedModel() ||
					BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(WSRPConfiguredProducerImpl.class,
						wsrpConfiguredProducer.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(wsrpConfiguredProducer);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(WSRPConfiguredProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConfiguredProducerImpl.class,
			wsrpConfiguredProducer.getPrimaryKey());

		return wsrpConfiguredProducer;
	}

	public WSRPConfiguredProducer update(
		WSRPConfiguredProducer wsrpConfiguredProducer)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(WSRPConfiguredProducer wsrpConfiguredProducer) method. Use update(WSRPConfiguredProducer wsrpConfiguredProducer, boolean merge) instead.");
		}

		return update(wsrpConfiguredProducer, false);
	}

	public WSRPConfiguredProducer update(
		WSRPConfiguredProducer wsrpConfiguredProducer, boolean merge)
		throws SystemException {
		boolean isNew = wsrpConfiguredProducer.isNew();

		for (ModelListener<WSRPConfiguredProducer> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(wsrpConfiguredProducer);
			}
			else {
				listener.onBeforeUpdate(wsrpConfiguredProducer);
			}
		}

		wsrpConfiguredProducer = updateImpl(wsrpConfiguredProducer, merge);

		for (ModelListener<WSRPConfiguredProducer> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(wsrpConfiguredProducer);
			}
			else {
				listener.onAfterUpdate(wsrpConfiguredProducer);
			}
		}

		return wsrpConfiguredProducer;
	}

	public WSRPConfiguredProducer updateImpl(
		com.liferay.wsrp.model.WSRPConfiguredProducer wsrpConfiguredProducer,
		boolean merge) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, wsrpConfiguredProducer, merge);

			wsrpConfiguredProducer.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(WSRPConfiguredProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConfiguredProducerImpl.class,
			wsrpConfiguredProducer.getPrimaryKey(), wsrpConfiguredProducer);

		return wsrpConfiguredProducer;
	}

	public WSRPConfiguredProducer findByPrimaryKey(long configuredProducerId)
		throws NoSuchConfiguredProducerException, SystemException {
		WSRPConfiguredProducer wsrpConfiguredProducer = fetchByPrimaryKey(configuredProducerId);

		if (wsrpConfiguredProducer == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"No WSRPConfiguredProducer exists with the primary key " +
					configuredProducerId);
			}

			throw new NoSuchConfiguredProducerException(
				"No WSRPConfiguredProducer exists with the primary key " +
				configuredProducerId);
		}

		return wsrpConfiguredProducer;
	}

	public WSRPConfiguredProducer fetchByPrimaryKey(long configuredProducerId)
		throws SystemException {
		WSRPConfiguredProducer wsrpConfiguredProducer = (WSRPConfiguredProducer)EntityCacheUtil.getResult(WSRPConfiguredProducerModelImpl.ENTITY_CACHE_ENABLED,
				WSRPConfiguredProducerImpl.class, configuredProducerId, this);

		if (wsrpConfiguredProducer == null) {
			Session session = null;

			try {
				session = openSession();

				wsrpConfiguredProducer = (WSRPConfiguredProducer)session.get(WSRPConfiguredProducerImpl.class,
						new Long(configuredProducerId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (wsrpConfiguredProducer != null) {
					cacheResult(wsrpConfiguredProducer);
				}

				closeSession(session);
			}
		}

		return wsrpConfiguredProducer;
	}

	public List<WSRPConfiguredProducer> findByP_N(String portalId,
		String namespace) throws SystemException {
		Object[] finderArgs = new Object[] { portalId, namespace };

		List<WSRPConfiguredProducer> list = (List<WSRPConfiguredProducer>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_P_N,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT wsrpConfiguredProducer FROM WSRPConfiguredProducer wsrpConfiguredProducer WHERE ");

				if (portalId == null) {
					query.append("wsrpConfiguredProducer.portalId IS NULL");
				}
				else {
					query.append("wsrpConfiguredProducer.portalId = ?");
				}

				query.append(" AND ");

				if (namespace == null) {
					query.append("wsrpConfiguredProducer.namespace IS NULL");
				}
				else {
					query.append("wsrpConfiguredProducer.namespace = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (portalId != null) {
					qPos.add(portalId);
				}

				if (namespace != null) {
					qPos.add(namespace);
				}

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPConfiguredProducer>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_P_N, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WSRPConfiguredProducer> findByP_N(String portalId,
		String namespace, int start, int end) throws SystemException {
		return findByP_N(portalId, namespace, start, end, null);
	}

	public List<WSRPConfiguredProducer> findByP_N(String portalId,
		String namespace, int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				portalId,
				
				namespace,
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WSRPConfiguredProducer> list = (List<WSRPConfiguredProducer>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_P_N,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT wsrpConfiguredProducer FROM WSRPConfiguredProducer wsrpConfiguredProducer WHERE ");

				if (portalId == null) {
					query.append("wsrpConfiguredProducer.portalId IS NULL");
				}
				else {
					query.append("wsrpConfiguredProducer.portalId = ?");
				}

				query.append(" AND ");

				if (namespace == null) {
					query.append("wsrpConfiguredProducer.namespace IS NULL");
				}
				else {
					query.append("wsrpConfiguredProducer.namespace = ?");
				}

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("wsrpConfiguredProducer.");
						query.append(orderByFields[i]);

						if (obc.isAscending()) {
							query.append(" ASC");
						}
						else {
							query.append(" DESC");
						}

						if ((i + 1) < orderByFields.length) {
							query.append(", ");
						}
					}
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (portalId != null) {
					qPos.add(portalId);
				}

				if (namespace != null) {
					qPos.add(namespace);
				}

				list = (List<WSRPConfiguredProducer>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPConfiguredProducer>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_P_N,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WSRPConfiguredProducer findByP_N_First(String portalId,
		String namespace, OrderByComparator obc)
		throws NoSuchConfiguredProducerException, SystemException {
		List<WSRPConfiguredProducer> list = findByP_N(portalId, namespace, 0,
				1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WSRPConfiguredProducer exists with the key {");

			msg.append("portalId=" + portalId);

			msg.append(", ");
			msg.append("namespace=" + namespace);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchConfiguredProducerException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WSRPConfiguredProducer findByP_N_Last(String portalId,
		String namespace, OrderByComparator obc)
		throws NoSuchConfiguredProducerException, SystemException {
		int count = countByP_N(portalId, namespace);

		List<WSRPConfiguredProducer> list = findByP_N(portalId, namespace,
				count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WSRPConfiguredProducer exists with the key {");

			msg.append("portalId=" + portalId);

			msg.append(", ");
			msg.append("namespace=" + namespace);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchConfiguredProducerException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WSRPConfiguredProducer[] findByP_N_PrevAndNext(
		long configuredProducerId, String portalId, String namespace,
		OrderByComparator obc)
		throws NoSuchConfiguredProducerException, SystemException {
		WSRPConfiguredProducer wsrpConfiguredProducer = findByPrimaryKey(configuredProducerId);

		int count = countByP_N(portalId, namespace);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT wsrpConfiguredProducer FROM WSRPConfiguredProducer wsrpConfiguredProducer WHERE ");

			if (portalId == null) {
				query.append("wsrpConfiguredProducer.portalId IS NULL");
			}
			else {
				query.append("wsrpConfiguredProducer.portalId = ?");
			}

			query.append(" AND ");

			if (namespace == null) {
				query.append("wsrpConfiguredProducer.namespace IS NULL");
			}
			else {
				query.append("wsrpConfiguredProducer.namespace = ?");
			}

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("wsrpConfiguredProducer.");
					query.append(orderByFields[i]);

					if (obc.isAscending()) {
						query.append(" ASC");
					}
					else {
						query.append(" DESC");
					}

					if ((i + 1) < orderByFields.length) {
						query.append(", ");
					}
				}
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			if (portalId != null) {
				qPos.add(portalId);
			}

			if (namespace != null) {
				qPos.add(namespace);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					wsrpConfiguredProducer);

			WSRPConfiguredProducer[] array = new WSRPConfiguredProducerImpl[3];

			array[0] = (WSRPConfiguredProducer)objArray[0];
			array[1] = (WSRPConfiguredProducer)objArray[1];
			array[2] = (WSRPConfiguredProducer)objArray[2];

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

	public List<WSRPConfiguredProducer> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<WSRPConfiguredProducer> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<WSRPConfiguredProducer> findAll(int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WSRPConfiguredProducer> list = (List<WSRPConfiguredProducer>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT wsrpConfiguredProducer FROM WSRPConfiguredProducer wsrpConfiguredProducer ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("wsrpConfiguredProducer.");
						query.append(orderByFields[i]);

						if (obc.isAscending()) {
							query.append(" ASC");
						}
						else {
							query.append(" DESC");
						}

						if ((i + 1) < orderByFields.length) {
							query.append(", ");
						}
					}
				}

				Query q = session.createQuery(query.toString());

				if (obc == null) {
					list = (List<WSRPConfiguredProducer>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<WSRPConfiguredProducer>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPConfiguredProducer>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByP_N(String portalId, String namespace)
		throws SystemException {
		for (WSRPConfiguredProducer wsrpConfiguredProducer : findByP_N(
				portalId, namespace)) {
			remove(wsrpConfiguredProducer);
		}
	}

	public void removeAll() throws SystemException {
		for (WSRPConfiguredProducer wsrpConfiguredProducer : findAll()) {
			remove(wsrpConfiguredProducer);
		}
	}

	public int countByP_N(String portalId, String namespace)
		throws SystemException {
		Object[] finderArgs = new Object[] { portalId, namespace };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_P_N,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(wsrpConfiguredProducer) ");
				query.append(
					"FROM WSRPConfiguredProducer wsrpConfiguredProducer WHERE ");

				if (portalId == null) {
					query.append("wsrpConfiguredProducer.portalId IS NULL");
				}
				else {
					query.append("wsrpConfiguredProducer.portalId = ?");
				}

				query.append(" AND ");

				if (namespace == null) {
					query.append("wsrpConfiguredProducer.namespace IS NULL");
				}
				else {
					query.append("wsrpConfiguredProducer.namespace = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (portalId != null) {
					qPos.add(portalId);
				}

				if (namespace != null) {
					qPos.add(namespace);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_P_N, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(
						"SELECT COUNT(wsrpConfiguredProducer) FROM WSRPConfiguredProducer wsrpConfiguredProducer");

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.wsrp.model.WSRPConfiguredProducer")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<WSRPConfiguredProducer>> listenersList = new ArrayList<ModelListener<WSRPConfiguredProducer>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<WSRPConfiguredProducer>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(name = "com.liferay.wsrp.service.persistence.WSRPConfiguredProducerPersistence.impl")
	protected com.liferay.wsrp.service.persistence.WSRPConfiguredProducerPersistence wsrpConfiguredProducerPersistence;
	@BeanReference(name = "com.liferay.wsrp.service.persistence.WSRPConsumerRegistrationPersistence.impl")
	protected com.liferay.wsrp.service.persistence.WSRPConsumerRegistrationPersistence wsrpConsumerRegistrationPersistence;
	@BeanReference(name = "com.liferay.wsrp.service.persistence.WSRPPortletPersistence.impl")
	protected com.liferay.wsrp.service.persistence.WSRPPortletPersistence wsrpPortletPersistence;
	@BeanReference(name = "com.liferay.wsrp.service.persistence.WSRPProducerPersistence.impl")
	protected com.liferay.wsrp.service.persistence.WSRPProducerPersistence wsrpProducerPersistence;
	private static Log _log = LogFactoryUtil.getLog(WSRPConfiguredProducerPersistenceImpl.class);
}