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

import com.liferay.wsrp.NoSuchPortletException;
import com.liferay.wsrp.model.WSRPPortlet;
import com.liferay.wsrp.model.impl.WSRPPortletImpl;
import com.liferay.wsrp.model.impl.WSRPPortletModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="WSRPPortletPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPPortletPersistenceImpl extends BasePersistenceImpl
	implements WSRPPortletPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = WSRPPortletImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FETCH_BY_PORTLETNAME = new FinderPath(WSRPPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPPortletModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_ENTITY, "fetchByPortletName",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_PORTLETNAME = new FinderPath(WSRPPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPPortletModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByPortletName", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_PRODUCERENTITYID = new FinderPath(WSRPPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPPortletModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByProducerEntityId", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_PRODUCERENTITYID = new FinderPath(WSRPPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPPortletModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByProducerEntityId",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_PRODUCERENTITYID = new FinderPath(WSRPPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPPortletModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByProducerEntityId", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_P_P = new FinderPath(WSRPPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPPortletModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByP_P",
			new String[] { String.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_P_P = new FinderPath(WSRPPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPPortletModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByP_P",
			new String[] {
				String.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_P_P = new FinderPath(WSRPPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPPortletModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByP_P",
			new String[] { String.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(WSRPPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPPortletModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WSRPPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPPortletModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(WSRPPortlet wsrpPortlet) {
		EntityCacheUtil.putResult(WSRPPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPPortletImpl.class, wsrpPortlet.getPrimaryKey(), wsrpPortlet);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PORTLETNAME,
			new Object[] { wsrpPortlet.getName() }, wsrpPortlet);
	}

	public void cacheResult(List<WSRPPortlet> wsrpPortlets) {
		for (WSRPPortlet wsrpPortlet : wsrpPortlets) {
			if (EntityCacheUtil.getResult(
						WSRPPortletModelImpl.ENTITY_CACHE_ENABLED,
						WSRPPortletImpl.class, wsrpPortlet.getPrimaryKey(), this) == null) {
				cacheResult(wsrpPortlet);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(WSRPPortletImpl.class.getName());
		EntityCacheUtil.clearCache(WSRPPortletImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

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
		for (ModelListener<WSRPPortlet> listener : listeners) {
			listener.onBeforeRemove(wsrpPortlet);
		}

		wsrpPortlet = removeImpl(wsrpPortlet);

		for (ModelListener<WSRPPortlet> listener : listeners) {
			listener.onAfterRemove(wsrpPortlet);
		}

		return wsrpPortlet;
	}

	protected WSRPPortlet removeImpl(WSRPPortlet wsrpPortlet)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (wsrpPortlet.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(WSRPPortletImpl.class,
						wsrpPortlet.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(wsrpPortlet);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		WSRPPortletModelImpl wsrpPortletModelImpl = (WSRPPortletModelImpl)wsrpPortlet;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PORTLETNAME,
			new Object[] { wsrpPortletModelImpl.getOriginalName() });

		EntityCacheUtil.removeResult(WSRPPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPPortletImpl.class, wsrpPortlet.getPrimaryKey());

		return wsrpPortlet;
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

		for (ModelListener<WSRPPortlet> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(wsrpPortlet);
			}
			else {
				listener.onBeforeUpdate(wsrpPortlet);
			}
		}

		wsrpPortlet = updateImpl(wsrpPortlet, merge);

		for (ModelListener<WSRPPortlet> listener : listeners) {
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
		boolean isNew = wsrpPortlet.isNew();

		WSRPPortletModelImpl wsrpPortletModelImpl = (WSRPPortletModelImpl)wsrpPortlet;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, wsrpPortlet, merge);

			wsrpPortlet.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(WSRPPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPPortletImpl.class, wsrpPortlet.getPrimaryKey(), wsrpPortlet);

		if (!isNew &&
				(!wsrpPortlet.getName()
								 .equals(wsrpPortletModelImpl.getOriginalName()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PORTLETNAME,
				new Object[] { wsrpPortletModelImpl.getOriginalName() });
		}

		if (isNew ||
				(!wsrpPortlet.getName()
								 .equals(wsrpPortletModelImpl.getOriginalName()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PORTLETNAME,
				new Object[] { wsrpPortlet.getName() }, wsrpPortlet);
		}

		return wsrpPortlet;
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
		WSRPPortlet wsrpPortlet = (WSRPPortlet)EntityCacheUtil.getResult(WSRPPortletModelImpl.ENTITY_CACHE_ENABLED,
				WSRPPortletImpl.class, portletId, this);

		if (wsrpPortlet == null) {
			Session session = null;

			try {
				session = openSession();

				wsrpPortlet = (WSRPPortlet)session.get(WSRPPortletImpl.class,
						new Long(portletId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (wsrpPortlet != null) {
					cacheResult(wsrpPortlet);
				}

				closeSession(session);
			}
		}

		return wsrpPortlet;
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
		return fetchByPortletName(name, true);
	}

	public WSRPPortlet fetchByPortletName(String name, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { name };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PORTLETNAME,
					finderArgs, this);
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

				result = list;

				WSRPPortlet wsrpPortlet = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PORTLETNAME,
						finderArgs, list);
				}
				else {
					wsrpPortlet = list.get(0);

					cacheResult(wsrpPortlet);

					if ((wsrpPortlet.getName() == null) ||
							!wsrpPortlet.getName().equals(name)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PORTLETNAME,
							finderArgs, list);
					}
				}

				return wsrpPortlet;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PORTLETNAME,
						finderArgs, new ArrayList<WSRPPortlet>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List) {
				return null;
			}
			else {
				return (WSRPPortlet)result;
			}
		}
	}

	public List<WSRPPortlet> findByProducerEntityId(String producerEntityId)
		throws SystemException {
		Object[] finderArgs = new Object[] { producerEntityId };

		List<WSRPPortlet> list = (List<WSRPPortlet>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_PRODUCERENTITYID,
				finderArgs, this);

		if (list == null) {
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

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPPortlet>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_PRODUCERENTITYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WSRPPortlet> findByProducerEntityId(String producerEntityId,
		int start, int end) throws SystemException {
		return findByProducerEntityId(producerEntityId, start, end, null);
	}

	public List<WSRPPortlet> findByProducerEntityId(String producerEntityId,
		int start, int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				producerEntityId,
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WSRPPortlet> list = (List<WSRPPortlet>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_PRODUCERENTITYID,
				finderArgs, this);

		if (list == null) {
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

				list = (List<WSRPPortlet>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPPortlet>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_PRODUCERENTITYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WSRPPortlet findByProducerEntityId_First(String producerEntityId,
		OrderByComparator obc) throws NoSuchPortletException, SystemException {
		List<WSRPPortlet> list = findByProducerEntityId(producerEntityId, 0, 1,
				obc);

		if (list.isEmpty()) {
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

		if (list.isEmpty()) {
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
		Object[] finderArgs = new Object[] { producerEntityId, portletHandle };

		List<WSRPPortlet> list = (List<WSRPPortlet>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_P_P,
				finderArgs, this);

		if (list == null) {
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

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPPortlet>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_P_P, finderArgs,
					list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WSRPPortlet> findByP_P(String producerEntityId,
		String portletHandle, int start, int end) throws SystemException {
		return findByP_P(producerEntityId, portletHandle, start, end, null);
	}

	public List<WSRPPortlet> findByP_P(String producerEntityId,
		String portletHandle, int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				producerEntityId,
				
				portletHandle,
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WSRPPortlet> list = (List<WSRPPortlet>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_P_P,
				finderArgs, this);

		if (list == null) {
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

				list = (List<WSRPPortlet>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPPortlet>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_P_P,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WSRPPortlet findByP_P_First(String producerEntityId,
		String portletHandle, OrderByComparator obc)
		throws NoSuchPortletException, SystemException {
		List<WSRPPortlet> list = findByP_P(producerEntityId, portletHandle, 0,
				1, obc);

		if (list.isEmpty()) {
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

		if (list.isEmpty()) {
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
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WSRPPortlet> list = (List<WSRPPortlet>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
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

				if (obc == null) {
					list = (List<WSRPPortlet>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<WSRPPortlet>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPPortlet>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
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
		Object[] finderArgs = new Object[] { name };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PORTLETNAME,
				finderArgs, this);

		if (count == null) {
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

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PORTLETNAME,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByProducerEntityId(String producerEntityId)
		throws SystemException {
		Object[] finderArgs = new Object[] { producerEntityId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PRODUCERENTITYID,
				finderArgs, this);

		if (count == null) {
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

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PRODUCERENTITYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByP_P(String producerEntityId, String portletHandle)
		throws SystemException {
		Object[] finderArgs = new Object[] { producerEntityId, portletHandle };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_P_P,
				finderArgs, this);

		if (count == null) {
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

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_P_P, finderArgs,
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
						"SELECT COUNT(*) FROM com.liferay.wsrp.model.WSRPPortlet");

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
						"value.object.listener.com.liferay.wsrp.model.WSRPPortlet")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<WSRPPortlet>> listenersList = new ArrayList<ModelListener<WSRPPortlet>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<WSRPPortlet>)Class.forName(
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