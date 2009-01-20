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

import com.liferay.wsrp.NoSuchPortletException;
import com.liferay.wsrp.model.WSRPPortlet;
import com.liferay.wsrp.model.impl.WSRPPortletImpl;
import com.liferay.wsrp.model.impl.WSRPPortletModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="WSRPPortletPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPPortletPersistenceImpl extends BasePersistenceImpl
	implements WSRPPortletPersistence {
	public WSRPPortlet create(long portletId) {
		WSRPPortlet wsrpPortlet = new WSRPPortletImpl();

		wsrpPortlet.setNew(true);
		wsrpPortlet.setPrimaryKey(portletId);

		return wsrpPortlet;
	}

	public WSRPPortlet remove(long portletId)
		throws NoSuchPortletException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WSRPPortlet wsrpPortlet = (WSRPPortlet)session.get(WSRPPortletImpl.class,
					new Long(portletId));

			if (wsrpPortlet == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No WSRPPortlet exists with the primary key " +
						portletId);
				}

				throw new NoSuchPortletException(
					"No WSRPPortlet exists with the primary key " + portletId);
			}

			return remove(wsrpPortlet);
		}
		catch (NoSuchPortletException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public WSRPPortlet remove(WSRPPortlet wsrpPortlet)
		throws SystemException {
		for (ModelListener listener : listeners) {
			listener.onBeforeRemove(wsrpPortlet);
		}

		wsrpPortlet = removeImpl(wsrpPortlet);

		for (ModelListener listener : listeners) {
			listener.onAfterRemove(wsrpPortlet);
		}

		return wsrpPortlet;
	}

	protected WSRPPortlet removeImpl(WSRPPortlet wsrpPortlet)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(WSRPPortletImpl.class,
						wsrpPortlet.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(wsrpPortlet);

			session.flush();

			return wsrpPortlet;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(WSRPPortlet.class.getName());
		}
	}

	public WSRPPortlet update(WSRPPortlet wsrpPortlet)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(WSRPPortlet wsrpPortlet) method. Use update(WSRPPortlet wsrpPortlet, boolean merge) instead.");
		}

		return update(wsrpPortlet, false);
	}

	public WSRPPortlet update(WSRPPortlet wsrpPortlet, boolean merge)
		throws SystemException {
		boolean isNew = wsrpPortlet.isNew();

		for (ModelListener listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(wsrpPortlet);
			}
			else {
				listener.onBeforeUpdate(wsrpPortlet);
			}
		}

		wsrpPortlet = updateImpl(wsrpPortlet, merge);

		for (ModelListener listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(wsrpPortlet);
			}
			else {
				listener.onAfterUpdate(wsrpPortlet);
			}
		}

		return wsrpPortlet;
	}

	public WSRPPortlet updateImpl(
		com.liferay.wsrp.model.WSRPPortlet wsrpPortlet, boolean merge)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, wsrpPortlet, merge);

			wsrpPortlet.setNew(false);

			return wsrpPortlet;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(WSRPPortlet.class.getName());
		}
	}

	public WSRPPortlet findByPrimaryKey(long portletId)
		throws NoSuchPortletException, SystemException {
		WSRPPortlet wsrpPortlet = fetchByPrimaryKey(portletId);

		if (wsrpPortlet == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No WSRPPortlet exists with the primary key " +
					portletId);
			}

			throw new NoSuchPortletException(
				"No WSRPPortlet exists with the primary key " + portletId);
		}

		return wsrpPortlet;
	}

	public WSRPPortlet fetchByPrimaryKey(long portletId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (WSRPPortlet)session.get(WSRPPortletImpl.class,
				new Long(portletId));
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public WSRPPortlet findByPortletName(String name)
		throws NoSuchPortletException, SystemException {
		WSRPPortlet wsrpPortlet = fetchByPortletName(name);

		if (wsrpPortlet == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WSRPPortlet exists with the key {");

			msg.append("name=" + name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchPortletException(msg.toString());
		}

		return wsrpPortlet;
	}

	public WSRPPortlet fetchByPortletName(String name)
		throws SystemException {
		boolean finderClassNameCacheEnabled = WSRPPortletModelImpl.CACHE_ENABLED;
		String finderClassName = WSRPPortlet.class.getName();
		String finderMethodName = "fetchByPortletName";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { name };

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

				query.append("FROM com.liferay.wsrp.model.WSRPPortlet WHERE ");

				if (name == null) {
					query.append("name IS NULL");
				}
				else {
					query.append("name = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (name != null) {
					qPos.add(name);
				}

				List<WSRPPortlet> list = q.list();

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
			List<WSRPPortlet> list = (List<WSRPPortlet>)result;

			if (list.size() == 0) {
				return null;
			}
			else {
				return list.get(0);
			}
		}
	}

	public List<WSRPPortlet> findByProducerEntityId(String producerEntityId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = WSRPPortletModelImpl.CACHE_ENABLED;
		String finderClassName = WSRPPortlet.class.getName();
		String finderMethodName = "findByProducerEntityId";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { producerEntityId };

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

				query.append("FROM com.liferay.wsrp.model.WSRPPortlet WHERE ");

				if (producerEntityId == null) {
					query.append("producerEntityId IS NULL");
				}
				else {
					query.append("producerEntityId = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (producerEntityId != null) {
					qPos.add(producerEntityId);
				}

				List<WSRPPortlet> list = q.list();

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
			return (List<WSRPPortlet>)result;
		}
	}

	public List<WSRPPortlet> findByProducerEntityId(String producerEntityId,
		int start, int end) throws SystemException {
		return findByProducerEntityId(producerEntityId, start, end, null);
	}

	public List<WSRPPortlet> findByProducerEntityId(String producerEntityId,
		int start, int end, OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = WSRPPortletModelImpl.CACHE_ENABLED;
		String finderClassName = WSRPPortlet.class.getName();
		String finderMethodName = "findByProducerEntityId";
		String[] finderParams = new String[] {
				String.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				producerEntityId,
				
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

				query.append("FROM com.liferay.wsrp.model.WSRPPortlet WHERE ");

				if (producerEntityId == null) {
					query.append("producerEntityId IS NULL");
				}
				else {
					query.append("producerEntityId = ?");
				}

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (producerEntityId != null) {
					qPos.add(producerEntityId);
				}

				List<WSRPPortlet> list = (List<WSRPPortlet>)QueryUtil.list(q,
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
			return (List<WSRPPortlet>)result;
		}
	}

	public WSRPPortlet findByProducerEntityId_First(String producerEntityId,
		OrderByComparator obc) throws NoSuchPortletException, SystemException {
		List<WSRPPortlet> list = findByProducerEntityId(producerEntityId, 0, 1,
				obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WSRPPortlet exists with the key {");

			msg.append("producerEntityId=" + producerEntityId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPortletException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WSRPPortlet findByProducerEntityId_Last(String producerEntityId,
		OrderByComparator obc) throws NoSuchPortletException, SystemException {
		int count = countByProducerEntityId(producerEntityId);

		List<WSRPPortlet> list = findByProducerEntityId(producerEntityId,
				count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WSRPPortlet exists with the key {");

			msg.append("producerEntityId=" + producerEntityId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPortletException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WSRPPortlet[] findByProducerEntityId_PrevAndNext(long portletId,
		String producerEntityId, OrderByComparator obc)
		throws NoSuchPortletException, SystemException {
		WSRPPortlet wsrpPortlet = findByPrimaryKey(portletId);

		int count = countByProducerEntityId(producerEntityId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.wsrp.model.WSRPPortlet WHERE ");

			if (producerEntityId == null) {
				query.append("producerEntityId IS NULL");
			}
			else {
				query.append("producerEntityId = ?");
			}

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			if (producerEntityId != null) {
				qPos.add(producerEntityId);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					wsrpPortlet);

			WSRPPortlet[] array = new WSRPPortletImpl[3];

			array[0] = (WSRPPortlet)objArray[0];
			array[1] = (WSRPPortlet)objArray[1];
			array[2] = (WSRPPortlet)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<WSRPPortlet> findByP_P(String producerEntityId,
		String portletHandle) throws SystemException {
		boolean finderClassNameCacheEnabled = WSRPPortletModelImpl.CACHE_ENABLED;
		String finderClassName = WSRPPortlet.class.getName();
		String finderMethodName = "findByP_P";
		String[] finderParams = new String[] {
				String.class.getName(), String.class.getName()
			};
		Object[] finderArgs = new Object[] { producerEntityId, portletHandle };

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

				query.append("FROM com.liferay.wsrp.model.WSRPPortlet WHERE ");

				if (producerEntityId == null) {
					query.append("producerEntityId IS NULL");
				}
				else {
					query.append("producerEntityId = ?");
				}

				query.append(" AND ");

				if (portletHandle == null) {
					query.append("portletHandle IS NULL");
				}
				else {
					query.append("portletHandle = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (producerEntityId != null) {
					qPos.add(producerEntityId);
				}

				if (portletHandle != null) {
					qPos.add(portletHandle);
				}

				List<WSRPPortlet> list = q.list();

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
			return (List<WSRPPortlet>)result;
		}
	}

	public List<WSRPPortlet> findByP_P(String producerEntityId,
		String portletHandle, int start, int end) throws SystemException {
		return findByP_P(producerEntityId, portletHandle, start, end, null);
	}

	public List<WSRPPortlet> findByP_P(String producerEntityId,
		String portletHandle, int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = WSRPPortletModelImpl.CACHE_ENABLED;
		String finderClassName = WSRPPortlet.class.getName();
		String finderMethodName = "findByP_P";
		String[] finderParams = new String[] {
				String.class.getName(), String.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				producerEntityId,
				
				portletHandle,
				
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

				query.append("FROM com.liferay.wsrp.model.WSRPPortlet WHERE ");

				if (producerEntityId == null) {
					query.append("producerEntityId IS NULL");
				}
				else {
					query.append("producerEntityId = ?");
				}

				query.append(" AND ");

				if (portletHandle == null) {
					query.append("portletHandle IS NULL");
				}
				else {
					query.append("portletHandle = ?");
				}

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (producerEntityId != null) {
					qPos.add(producerEntityId);
				}

				if (portletHandle != null) {
					qPos.add(portletHandle);
				}

				List<WSRPPortlet> list = (List<WSRPPortlet>)QueryUtil.list(q,
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
			return (List<WSRPPortlet>)result;
		}
	}

	public WSRPPortlet findByP_P_First(String producerEntityId,
		String portletHandle, OrderByComparator obc)
		throws NoSuchPortletException, SystemException {
		List<WSRPPortlet> list = findByP_P(producerEntityId, portletHandle, 0,
				1, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WSRPPortlet exists with the key {");

			msg.append("producerEntityId=" + producerEntityId);

			msg.append(", ");
			msg.append("portletHandle=" + portletHandle);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPortletException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WSRPPortlet findByP_P_Last(String producerEntityId,
		String portletHandle, OrderByComparator obc)
		throws NoSuchPortletException, SystemException {
		int count = countByP_P(producerEntityId, portletHandle);

		List<WSRPPortlet> list = findByP_P(producerEntityId, portletHandle,
				count - 1, count, obc);

		if (list.size() == 0) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WSRPPortlet exists with the key {");

			msg.append("producerEntityId=" + producerEntityId);

			msg.append(", ");
			msg.append("portletHandle=" + portletHandle);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchPortletException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WSRPPortlet[] findByP_P_PrevAndNext(long portletId,
		String producerEntityId, String portletHandle, OrderByComparator obc)
		throws NoSuchPortletException, SystemException {
		WSRPPortlet wsrpPortlet = findByPrimaryKey(portletId);

		int count = countByP_P(producerEntityId, portletHandle);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM com.liferay.wsrp.model.WSRPPortlet WHERE ");

			if (producerEntityId == null) {
				query.append("producerEntityId IS NULL");
			}
			else {
				query.append("producerEntityId = ?");
			}

			query.append(" AND ");

			if (portletHandle == null) {
				query.append("portletHandle IS NULL");
			}
			else {
				query.append("portletHandle = ?");
			}

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			if (producerEntityId != null) {
				qPos.add(producerEntityId);
			}

			if (portletHandle != null) {
				qPos.add(portletHandle);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					wsrpPortlet);

			WSRPPortlet[] array = new WSRPPortletImpl[3];

			array[0] = (WSRPPortlet)objArray[0];
			array[1] = (WSRPPortlet)objArray[1];
			array[2] = (WSRPPortlet)objArray[2];

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

	public List<WSRPPortlet> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<WSRPPortlet> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<WSRPPortlet> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = WSRPPortletModelImpl.CACHE_ENABLED;
		String finderClassName = WSRPPortlet.class.getName();
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

				query.append("FROM com.liferay.wsrp.model.WSRPPortlet ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				List<WSRPPortlet> list = null;

				if (obc == null) {
					list = (List<WSRPPortlet>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<WSRPPortlet>)QueryUtil.list(q, getDialect(),
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
			return (List<WSRPPortlet>)result;
		}
	}

	public void removeByPortletName(String name)
		throws NoSuchPortletException, SystemException {
		WSRPPortlet wsrpPortlet = findByPortletName(name);

		remove(wsrpPortlet);
	}

	public void removeByProducerEntityId(String producerEntityId)
		throws SystemException {
		for (WSRPPortlet wsrpPortlet : findByProducerEntityId(producerEntityId)) {
			remove(wsrpPortlet);
		}
	}

	public void removeByP_P(String producerEntityId, String portletHandle)
		throws SystemException {
		for (WSRPPortlet wsrpPortlet : findByP_P(producerEntityId, portletHandle)) {
			remove(wsrpPortlet);
		}
	}

	public void removeAll() throws SystemException {
		for (WSRPPortlet wsrpPortlet : findAll()) {
			remove(wsrpPortlet);
		}
	}

	public int countByPortletName(String name) throws SystemException {
		boolean finderClassNameCacheEnabled = WSRPPortletModelImpl.CACHE_ENABLED;
		String finderClassName = WSRPPortlet.class.getName();
		String finderMethodName = "countByPortletName";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { name };

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
				query.append("FROM com.liferay.wsrp.model.WSRPPortlet WHERE ");

				if (name == null) {
					query.append("name IS NULL");
				}
				else {
					query.append("name = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (name != null) {
					qPos.add(name);
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

	public int countByProducerEntityId(String producerEntityId)
		throws SystemException {
		boolean finderClassNameCacheEnabled = WSRPPortletModelImpl.CACHE_ENABLED;
		String finderClassName = WSRPPortlet.class.getName();
		String finderMethodName = "countByProducerEntityId";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { producerEntityId };

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
				query.append("FROM com.liferay.wsrp.model.WSRPPortlet WHERE ");

				if (producerEntityId == null) {
					query.append("producerEntityId IS NULL");
				}
				else {
					query.append("producerEntityId = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (producerEntityId != null) {
					qPos.add(producerEntityId);
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

	public int countByP_P(String producerEntityId, String portletHandle)
		throws SystemException {
		boolean finderClassNameCacheEnabled = WSRPPortletModelImpl.CACHE_ENABLED;
		String finderClassName = WSRPPortlet.class.getName();
		String finderMethodName = "countByP_P";
		String[] finderParams = new String[] {
				String.class.getName(), String.class.getName()
			};
		Object[] finderArgs = new Object[] { producerEntityId, portletHandle };

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
				query.append("FROM com.liferay.wsrp.model.WSRPPortlet WHERE ");

				if (producerEntityId == null) {
					query.append("producerEntityId IS NULL");
				}
				else {
					query.append("producerEntityId = ?");
				}

				query.append(" AND ");

				if (portletHandle == null) {
					query.append("portletHandle IS NULL");
				}
				else {
					query.append("portletHandle = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (producerEntityId != null) {
					qPos.add(producerEntityId);
				}

				if (portletHandle != null) {
					qPos.add(portletHandle);
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
		boolean finderClassNameCacheEnabled = WSRPPortletModelImpl.CACHE_ENABLED;
		String finderClassName = WSRPPortlet.class.getName();
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
						"SELECT COUNT(*) FROM com.liferay.wsrp.model.WSRPPortlet");

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
						"value.object.listener.com.liferay.wsrp.model.WSRPPortlet")));

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
	private static Log _log = LogFactoryUtil.getLog(WSRPPortletPersistenceImpl.class);
}