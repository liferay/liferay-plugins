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

import com.liferay.wsrp.NoSuchProducerException;
import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.model.impl.WSRPProducerImpl;
import com.liferay.wsrp.model.impl.WSRPProducerModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="WSRPProducerPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPProducerPersistenceImpl extends BasePersistenceImpl
	implements WSRPProducerPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = WSRPProducerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FETCH_BY_INSTANCENAME = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_ENTITY, "fetchByInstanceName",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_INSTANCENAME = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByInstanceName", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_P_N = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByP_N",
			new String[] { String.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_P_N = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByP_N",
			new String[] {
				String.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_P_N = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByP_N",
			new String[] { String.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(WSRPProducer wsrpProducer) {
		EntityCacheUtil.putResult(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerImpl.class, wsrpProducer.getPrimaryKey(), wsrpProducer);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_INSTANCENAME,
			new Object[] { wsrpProducer.getInstanceName() }, wsrpProducer);
	}

	public void cacheResult(List<WSRPProducer> wsrpProducers) {
		for (WSRPProducer wsrpProducer : wsrpProducers) {
			if (EntityCacheUtil.getResult(
						WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
						WSRPProducerImpl.class, wsrpProducer.getPrimaryKey(),
						this) == null) {
				cacheResult(wsrpProducer);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(WSRPProducerImpl.class.getName());
		EntityCacheUtil.clearCache(WSRPProducerImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public WSRPProducer create(long producerId) {
		WSRPProducer wsrpProducer = new WSRPProducerImpl();

		wsrpProducer.setNew(true);
		wsrpProducer.setPrimaryKey(producerId);

		return wsrpProducer;
	}

	public WSRPProducer remove(long producerId)
		throws NoSuchProducerException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WSRPProducer wsrpProducer = (WSRPProducer)session.get(WSRPProducerImpl.class,
					new Long(producerId));

			if (wsrpProducer == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No WSRPProducer exists with the primary key " +
						producerId);
				}

				throw new NoSuchProducerException(
					"No WSRPProducer exists with the primary key " +
					producerId);
			}

			return remove(wsrpProducer);
		}
		catch (NoSuchProducerException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public WSRPProducer remove(WSRPProducer wsrpProducer)
		throws SystemException {
		for (ModelListener<WSRPProducer> listener : listeners) {
			listener.onBeforeRemove(wsrpProducer);
		}

		wsrpProducer = removeImpl(wsrpProducer);

		for (ModelListener<WSRPProducer> listener : listeners) {
			listener.onAfterRemove(wsrpProducer);
		}

		return wsrpProducer;
	}

	protected WSRPProducer removeImpl(WSRPProducer wsrpProducer)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (wsrpProducer.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(WSRPProducerImpl.class,
						wsrpProducer.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(wsrpProducer);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		WSRPProducerModelImpl wsrpProducerModelImpl = (WSRPProducerModelImpl)wsrpProducer;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_INSTANCENAME,
			new Object[] { wsrpProducerModelImpl.getOriginalInstanceName() });

		EntityCacheUtil.removeResult(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerImpl.class, wsrpProducer.getPrimaryKey());

		return wsrpProducer;
	}

	public WSRPProducer update(WSRPProducer wsrpProducer)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(WSRPProducer wsrpProducer) method. Use update(WSRPProducer wsrpProducer, boolean merge) instead.");
		}

		return update(wsrpProducer, false);
	}

	public WSRPProducer update(WSRPProducer wsrpProducer, boolean merge)
		throws SystemException {
		boolean isNew = wsrpProducer.isNew();

		for (ModelListener<WSRPProducer> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(wsrpProducer);
			}
			else {
				listener.onBeforeUpdate(wsrpProducer);
			}
		}

		wsrpProducer = updateImpl(wsrpProducer, merge);

		for (ModelListener<WSRPProducer> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(wsrpProducer);
			}
			else {
				listener.onAfterUpdate(wsrpProducer);
			}
		}

		return wsrpProducer;
	}

	public WSRPProducer updateImpl(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer, boolean merge)
		throws SystemException {
		boolean isNew = wsrpProducer.isNew();

		WSRPProducerModelImpl wsrpProducerModelImpl = (WSRPProducerModelImpl)wsrpProducer;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, wsrpProducer, merge);

			wsrpProducer.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerImpl.class, wsrpProducer.getPrimaryKey(), wsrpProducer);

		if (!isNew &&
				(!wsrpProducer.getInstanceName()
								  .equals(wsrpProducerModelImpl.getOriginalInstanceName()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_INSTANCENAME,
				new Object[] { wsrpProducerModelImpl.getOriginalInstanceName() });
		}

		if (isNew ||
				(!wsrpProducer.getInstanceName()
								  .equals(wsrpProducerModelImpl.getOriginalInstanceName()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_INSTANCENAME,
				new Object[] { wsrpProducer.getInstanceName() }, wsrpProducer);
		}

		return wsrpProducer;
	}

	public WSRPProducer findByPrimaryKey(long producerId)
		throws NoSuchProducerException, SystemException {
		WSRPProducer wsrpProducer = fetchByPrimaryKey(producerId);

		if (wsrpProducer == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No WSRPProducer exists with the primary key " +
					producerId);
			}

			throw new NoSuchProducerException(
				"No WSRPProducer exists with the primary key " + producerId);
		}

		return wsrpProducer;
	}

	public WSRPProducer fetchByPrimaryKey(long producerId)
		throws SystemException {
		WSRPProducer wsrpProducer = (WSRPProducer)EntityCacheUtil.getResult(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
				WSRPProducerImpl.class, producerId, this);

		if (wsrpProducer == null) {
			Session session = null;

			try {
				session = openSession();

				wsrpProducer = (WSRPProducer)session.get(WSRPProducerImpl.class,
						new Long(producerId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (wsrpProducer != null) {
					cacheResult(wsrpProducer);
				}

				closeSession(session);
			}
		}

		return wsrpProducer;
	}

	public WSRPProducer findByInstanceName(String instanceName)
		throws NoSuchProducerException, SystemException {
		WSRPProducer wsrpProducer = fetchByInstanceName(instanceName);

		if (wsrpProducer == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WSRPProducer exists with the key {");

			msg.append("instanceName=" + instanceName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchProducerException(msg.toString());
		}

		return wsrpProducer;
	}

	public WSRPProducer fetchByInstanceName(String instanceName)
		throws SystemException {
		return fetchByInstanceName(instanceName, true);
	}

	public WSRPProducer fetchByInstanceName(String instanceName,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { instanceName };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_INSTANCENAME,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wsrp.model.WSRPProducer WHERE ");

				if (instanceName == null) {
					query.append("instanceName IS NULL");
				}
				else {
					query.append("instanceName = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (instanceName != null) {
					qPos.add(instanceName);
				}

				List<WSRPProducer> list = q.list();

				result = list;

				WSRPProducer wsrpProducer = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_INSTANCENAME,
						finderArgs, list);
				}
				else {
					wsrpProducer = list.get(0);

					cacheResult(wsrpProducer);

					if ((wsrpProducer.getInstanceName() == null) ||
							!wsrpProducer.getInstanceName().equals(instanceName)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_INSTANCENAME,
							finderArgs, list);
					}
				}

				return wsrpProducer;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_INSTANCENAME,
						finderArgs, new ArrayList<WSRPProducer>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List) {
				return null;
			}
			else {
				return (WSRPProducer)result;
			}
		}
	}

	public List<WSRPProducer> findByP_N(String portalId, String namespace)
		throws SystemException {
		Object[] finderArgs = new Object[] { portalId, namespace };

		List<WSRPProducer> list = (List<WSRPProducer>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_P_N,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wsrp.model.WSRPProducer WHERE ");

				if (portalId == null) {
					query.append("portalId IS NULL");
				}
				else {
					query.append("portalId = ?");
				}

				query.append(" AND ");

				if (namespace == null) {
					query.append("namespace IS NULL");
				}
				else {
					query.append("namespace = ?");
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
					list = new ArrayList<WSRPProducer>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_P_N, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WSRPProducer> findByP_N(String portalId, String namespace,
		int start, int end) throws SystemException {
		return findByP_N(portalId, namespace, start, end, null);
	}

	public List<WSRPProducer> findByP_N(String portalId, String namespace,
		int start, int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				portalId,
				
				namespace,
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WSRPProducer> list = (List<WSRPProducer>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_P_N,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wsrp.model.WSRPProducer WHERE ");

				if (portalId == null) {
					query.append("portalId IS NULL");
				}
				else {
					query.append("portalId = ?");
				}

				query.append(" AND ");

				if (namespace == null) {
					query.append("namespace IS NULL");
				}
				else {
					query.append("namespace = ?");
				}

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (portalId != null) {
					qPos.add(portalId);
				}

				if (namespace != null) {
					qPos.add(namespace);
				}

				list = (List<WSRPProducer>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPProducer>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_P_N,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WSRPProducer findByP_N_First(String portalId, String namespace,
		OrderByComparator obc) throws NoSuchProducerException, SystemException {
		List<WSRPProducer> list = findByP_N(portalId, namespace, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WSRPProducer exists with the key {");

			msg.append("portalId=" + portalId);

			msg.append(", ");
			msg.append("namespace=" + namespace);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchProducerException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WSRPProducer findByP_N_Last(String portalId, String namespace,
		OrderByComparator obc) throws NoSuchProducerException, SystemException {
		int count = countByP_N(portalId, namespace);

		List<WSRPProducer> list = findByP_N(portalId, namespace, count - 1,
				count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WSRPProducer exists with the key {");

			msg.append("portalId=" + portalId);

			msg.append(", ");
			msg.append("namespace=" + namespace);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchProducerException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WSRPProducer[] findByP_N_PrevAndNext(long producerId,
		String portalId, String namespace, OrderByComparator obc)
		throws NoSuchProducerException, SystemException {
		WSRPProducer wsrpProducer = findByPrimaryKey(producerId);

		int count = countByP_N(portalId, namespace);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.wsrp.model.WSRPProducer WHERE ");

			if (portalId == null) {
				query.append("portalId IS NULL");
			}
			else {
				query.append("portalId = ?");
			}

			query.append(" AND ");

			if (namespace == null) {
				query.append("namespace IS NULL");
			}
			else {
				query.append("namespace = ?");
			}

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
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
					wsrpProducer);

			WSRPProducer[] array = new WSRPProducerImpl[3];

			array[0] = (WSRPProducer)objArray[0];
			array[1] = (WSRPProducer)objArray[1];
			array[2] = (WSRPProducer)objArray[2];

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

	public List<WSRPProducer> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<WSRPProducer> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<WSRPProducer> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WSRPProducer> list = (List<WSRPProducer>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.wsrp.model.WSRPProducer ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				if (obc == null) {
					list = (List<WSRPProducer>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<WSRPProducer>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPProducer>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByInstanceName(String instanceName)
		throws NoSuchProducerException, SystemException {
		WSRPProducer wsrpProducer = findByInstanceName(instanceName);

		remove(wsrpProducer);
	}

	public void removeByP_N(String portalId, String namespace)
		throws SystemException {
		for (WSRPProducer wsrpProducer : findByP_N(portalId, namespace)) {
			remove(wsrpProducer);
		}
	}

	public void removeAll() throws SystemException {
		for (WSRPProducer wsrpProducer : findAll()) {
			remove(wsrpProducer);
		}
	}

	public int countByInstanceName(String instanceName)
		throws SystemException {
		Object[] finderArgs = new Object[] { instanceName };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_INSTANCENAME,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.wsrp.model.WSRPProducer WHERE ");

				if (instanceName == null) {
					query.append("instanceName IS NULL");
				}
				else {
					query.append("instanceName = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (instanceName != null) {
					qPos.add(instanceName);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_INSTANCENAME,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
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

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.wsrp.model.WSRPProducer WHERE ");

				if (portalId == null) {
					query.append("portalId IS NULL");
				}
				else {
					query.append("portalId = ?");
				}

				query.append(" AND ");

				if (namespace == null) {
					query.append("namespace IS NULL");
				}
				else {
					query.append("namespace = ?");
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
						"SELECT COUNT(*) FROM com.liferay.wsrp.model.WSRPProducer");

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
						"value.object.listener.com.liferay.wsrp.model.WSRPProducer")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<WSRPProducer>> listenersList = new ArrayList<ModelListener<WSRPProducer>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<WSRPProducer>)Class.forName(
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
	private static Log _log = LogFactoryUtil.getLog(WSRPProducerPersistenceImpl.class);
}