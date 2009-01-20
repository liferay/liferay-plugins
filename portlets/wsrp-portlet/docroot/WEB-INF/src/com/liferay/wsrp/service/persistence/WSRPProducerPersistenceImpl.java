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

import com.liferay.wsrp.NoSuchProducerException;
import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.model.impl.WSRPProducerImpl;
import com.liferay.wsrp.model.impl.WSRPProducerModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="WSRPProducerPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPProducerPersistenceImpl extends BasePersistenceImpl
	implements WSRPProducerPersistence {
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
		for (ModelListener listener : listeners) {
			listener.onBeforeRemove(wsrpProducer);
		}

		wsrpProducer = removeImpl(wsrpProducer);

		for (ModelListener listener : listeners) {
			listener.onAfterRemove(wsrpProducer);
		}

		return wsrpProducer;
	}

	protected WSRPProducer removeImpl(WSRPProducer wsrpProducer)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(WSRPProducerImpl.class,
						wsrpProducer.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(wsrpProducer);

			session.flush();

			return wsrpProducer;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(WSRPProducer.class.getName());
		}
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

		for (ModelListener listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(wsrpProducer);
			}
			else {
				listener.onBeforeUpdate(wsrpProducer);
			}
		}

		wsrpProducer = updateImpl(wsrpProducer, merge);

		for (ModelListener listener : listeners) {
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
		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, wsrpProducer, merge);

			wsrpProducer.setNew(false);

			return wsrpProducer;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(WSRPProducer.class.getName());
		}
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
		Session session = null;

		try {
			session = openSession();

			return (WSRPProducer)session.get(WSRPProducerImpl.class,
				new Long(producerId));
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
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
		boolean finderClassNameCacheEnabled = WSRPProducerModelImpl.CACHE_ENABLED;
		String finderClassName = WSRPProducer.class.getName();
		String finderMethodName = "fetchByInstanceName";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { instanceName };

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
			List<WSRPProducer> list = (List<WSRPProducer>)result;

			if (list.size() == 0) {
				return null;
			}
			else {
				return list.get(0);
			}
		}
	}

	public List<WSRPProducer> findByP_N(String portalId, String namespace)
		throws SystemException {
		boolean finderClassNameCacheEnabled = WSRPProducerModelImpl.CACHE_ENABLED;
		String finderClassName = WSRPProducer.class.getName();
		String finderMethodName = "findByP_N";
		String[] finderParams = new String[] {
				String.class.getName(), String.class.getName()
			};
		Object[] finderArgs = new Object[] { portalId, namespace };

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

				List<WSRPProducer> list = q.list();

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
			return (List<WSRPProducer>)result;
		}
	}

	public List<WSRPProducer> findByP_N(String portalId, String namespace,
		int start, int end) throws SystemException {
		return findByP_N(portalId, namespace, start, end, null);
	}

	public List<WSRPProducer> findByP_N(String portalId, String namespace,
		int start, int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = WSRPProducerModelImpl.CACHE_ENABLED;
		String finderClassName = WSRPProducer.class.getName();
		String finderMethodName = "findByP_N";
		String[] finderParams = new String[] {
				String.class.getName(), String.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				portalId,
				
				namespace,
				
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

				List<WSRPProducer> list = (List<WSRPProducer>)QueryUtil.list(q,
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
			return (List<WSRPProducer>)result;
		}
	}

	public WSRPProducer findByP_N_First(String portalId, String namespace,
		OrderByComparator obc) throws NoSuchProducerException, SystemException {
		List<WSRPProducer> list = findByP_N(portalId, namespace, 0, 1, obc);

		if (list.size() == 0) {
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

		if (list.size() == 0) {
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
		boolean finderClassNameCacheEnabled = WSRPProducerModelImpl.CACHE_ENABLED;
		String finderClassName = WSRPProducer.class.getName();
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

				query.append("FROM com.liferay.wsrp.model.WSRPProducer ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				List<WSRPProducer> list = null;

				if (obc == null) {
					list = (List<WSRPProducer>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<WSRPProducer>)QueryUtil.list(q, getDialect(),
							start, end);
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
			return (List<WSRPProducer>)result;
		}
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
		boolean finderClassNameCacheEnabled = WSRPProducerModelImpl.CACHE_ENABLED;
		String finderClassName = WSRPProducer.class.getName();
		String finderMethodName = "countByInstanceName";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { instanceName };

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

	public int countByP_N(String portalId, String namespace)
		throws SystemException {
		boolean finderClassNameCacheEnabled = WSRPProducerModelImpl.CACHE_ENABLED;
		String finderClassName = WSRPProducer.class.getName();
		String finderMethodName = "countByP_N";
		String[] finderParams = new String[] {
				String.class.getName(), String.class.getName()
			};
		Object[] finderArgs = new Object[] { portalId, namespace };

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
		boolean finderClassNameCacheEnabled = WSRPProducerModelImpl.CACHE_ENABLED;
		String finderClassName = WSRPProducer.class.getName();
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
						"SELECT COUNT(*) FROM com.liferay.wsrp.model.WSRPProducer");

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
						"value.object.listener.com.liferay.wsrp.model.WSRPProducer")));

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