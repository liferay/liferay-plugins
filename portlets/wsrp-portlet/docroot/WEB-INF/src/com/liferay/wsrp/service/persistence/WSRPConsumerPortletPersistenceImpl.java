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

import com.liferay.wsrp.NoSuchConsumerPortletException;
import com.liferay.wsrp.model.WSRPConsumerPortlet;
import com.liferay.wsrp.model.impl.WSRPConsumerPortletImpl;
import com.liferay.wsrp.model.impl.WSRPConsumerPortletModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="WSRPConsumerPortletPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPConsumerPortletPersistenceImpl extends BasePersistenceImpl
	implements WSRPConsumerPortletPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = WSRPConsumerPortletImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_WSRPCONSUMERID = new FinderPath(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByWsrpConsumerId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_WSRPCONSUMERID = new FinderPath(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByWsrpConsumerId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_WSRPCONSUMERID = new FinderPath(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByWsrpConsumerId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	public void cacheResult(WSRPConsumerPortlet wsrpConsumerPortlet) {
		EntityCacheUtil.putResult(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletImpl.class, wsrpConsumerPortlet.getPrimaryKey(),
			wsrpConsumerPortlet);
	}

	public void cacheResult(List<WSRPConsumerPortlet> wsrpConsumerPortlets) {
		for (WSRPConsumerPortlet wsrpConsumerPortlet : wsrpConsumerPortlets) {
			if (EntityCacheUtil.getResult(
						WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
						WSRPConsumerPortletImpl.class,
						wsrpConsumerPortlet.getPrimaryKey(), this) == null) {
				cacheResult(wsrpConsumerPortlet);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(WSRPConsumerPortletImpl.class.getName());
		EntityCacheUtil.clearCache(WSRPConsumerPortletImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public WSRPConsumerPortlet create(long wsrpConsumerPortletId) {
		WSRPConsumerPortlet wsrpConsumerPortlet = new WSRPConsumerPortletImpl();

		wsrpConsumerPortlet.setNew(true);
		wsrpConsumerPortlet.setPrimaryKey(wsrpConsumerPortletId);

		return wsrpConsumerPortlet;
	}

	public WSRPConsumerPortlet remove(long wsrpConsumerPortletId)
		throws NoSuchConsumerPortletException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WSRPConsumerPortlet wsrpConsumerPortlet = (WSRPConsumerPortlet)session.get(WSRPConsumerPortletImpl.class,
					new Long(wsrpConsumerPortletId));

			if (wsrpConsumerPortlet == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"No WSRPConsumerPortlet exists with the primary key " +
						wsrpConsumerPortletId);
				}

				throw new NoSuchConsumerPortletException(
					"No WSRPConsumerPortlet exists with the primary key " +
					wsrpConsumerPortletId);
			}

			return remove(wsrpConsumerPortlet);
		}
		catch (NoSuchConsumerPortletException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public WSRPConsumerPortlet remove(WSRPConsumerPortlet wsrpConsumerPortlet)
		throws SystemException {
		for (ModelListener<WSRPConsumerPortlet> listener : listeners) {
			listener.onBeforeRemove(wsrpConsumerPortlet);
		}

		wsrpConsumerPortlet = removeImpl(wsrpConsumerPortlet);

		for (ModelListener<WSRPConsumerPortlet> listener : listeners) {
			listener.onAfterRemove(wsrpConsumerPortlet);
		}

		return wsrpConsumerPortlet;
	}

	protected WSRPConsumerPortlet removeImpl(
		WSRPConsumerPortlet wsrpConsumerPortlet) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (wsrpConsumerPortlet.isCachedModel() ||
					BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(WSRPConsumerPortletImpl.class,
						wsrpConsumerPortlet.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(wsrpConsumerPortlet);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletImpl.class, wsrpConsumerPortlet.getPrimaryKey());

		return wsrpConsumerPortlet;
	}

	public WSRPConsumerPortlet update(WSRPConsumerPortlet wsrpConsumerPortlet)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(WSRPConsumerPortlet wsrpConsumerPortlet) method. Use update(WSRPConsumerPortlet wsrpConsumerPortlet, boolean merge) instead.");
		}

		return update(wsrpConsumerPortlet, false);
	}

	public WSRPConsumerPortlet update(WSRPConsumerPortlet wsrpConsumerPortlet,
		boolean merge) throws SystemException {
		boolean isNew = wsrpConsumerPortlet.isNew();

		for (ModelListener<WSRPConsumerPortlet> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(wsrpConsumerPortlet);
			}
			else {
				listener.onBeforeUpdate(wsrpConsumerPortlet);
			}
		}

		wsrpConsumerPortlet = updateImpl(wsrpConsumerPortlet, merge);

		for (ModelListener<WSRPConsumerPortlet> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(wsrpConsumerPortlet);
			}
			else {
				listener.onAfterUpdate(wsrpConsumerPortlet);
			}
		}

		return wsrpConsumerPortlet;
	}

	public WSRPConsumerPortlet updateImpl(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet,
		boolean merge) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, wsrpConsumerPortlet, merge);

			wsrpConsumerPortlet.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletImpl.class, wsrpConsumerPortlet.getPrimaryKey(),
			wsrpConsumerPortlet);

		return wsrpConsumerPortlet;
	}

	public WSRPConsumerPortlet findByPrimaryKey(long wsrpConsumerPortletId)
		throws NoSuchConsumerPortletException, SystemException {
		WSRPConsumerPortlet wsrpConsumerPortlet = fetchByPrimaryKey(wsrpConsumerPortletId);

		if (wsrpConsumerPortlet == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No WSRPConsumerPortlet exists with the primary key " +
					wsrpConsumerPortletId);
			}

			throw new NoSuchConsumerPortletException(
				"No WSRPConsumerPortlet exists with the primary key " +
				wsrpConsumerPortletId);
		}

		return wsrpConsumerPortlet;
	}

	public WSRPConsumerPortlet fetchByPrimaryKey(long wsrpConsumerPortletId)
		throws SystemException {
		WSRPConsumerPortlet wsrpConsumerPortlet = (WSRPConsumerPortlet)EntityCacheUtil.getResult(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
				WSRPConsumerPortletImpl.class, wsrpConsumerPortletId, this);

		if (wsrpConsumerPortlet == null) {
			Session session = null;

			try {
				session = openSession();

				wsrpConsumerPortlet = (WSRPConsumerPortlet)session.get(WSRPConsumerPortletImpl.class,
						new Long(wsrpConsumerPortletId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (wsrpConsumerPortlet != null) {
					cacheResult(wsrpConsumerPortlet);
				}

				closeSession(session);
			}
		}

		return wsrpConsumerPortlet;
	}

	public List<WSRPConsumerPortlet> findByWsrpConsumerId(long wsrpConsumerId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(wsrpConsumerId) };

		List<WSRPConsumerPortlet> list = (List<WSRPConsumerPortlet>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_WSRPCONSUMERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT wsrpConsumerPortlet FROM WSRPConsumerPortlet wsrpConsumerPortlet WHERE ");

				query.append("wsrpConsumerPortlet.wsrpConsumerId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("wsrpConsumerPortlet.name ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(wsrpConsumerId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPConsumerPortlet>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_WSRPCONSUMERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WSRPConsumerPortlet> findByWsrpConsumerId(long wsrpConsumerId,
		int start, int end) throws SystemException {
		return findByWsrpConsumerId(wsrpConsumerId, start, end, null);
	}

	public List<WSRPConsumerPortlet> findByWsrpConsumerId(long wsrpConsumerId,
		int start, int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(wsrpConsumerId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WSRPConsumerPortlet> list = (List<WSRPConsumerPortlet>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_WSRPCONSUMERID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT wsrpConsumerPortlet FROM WSRPConsumerPortlet wsrpConsumerPortlet WHERE ");

				query.append("wsrpConsumerPortlet.wsrpConsumerId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("wsrpConsumerPortlet.");
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

				else {
					query.append("ORDER BY ");

					query.append("wsrpConsumerPortlet.name ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(wsrpConsumerId);

				list = (List<WSRPConsumerPortlet>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPConsumerPortlet>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_WSRPCONSUMERID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WSRPConsumerPortlet findByWsrpConsumerId_First(long wsrpConsumerId,
		OrderByComparator obc)
		throws NoSuchConsumerPortletException, SystemException {
		List<WSRPConsumerPortlet> list = findByWsrpConsumerId(wsrpConsumerId,
				0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WSRPConsumerPortlet exists with the key {");

			msg.append("wsrpConsumerId=" + wsrpConsumerId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchConsumerPortletException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WSRPConsumerPortlet findByWsrpConsumerId_Last(long wsrpConsumerId,
		OrderByComparator obc)
		throws NoSuchConsumerPortletException, SystemException {
		int count = countByWsrpConsumerId(wsrpConsumerId);

		List<WSRPConsumerPortlet> list = findByWsrpConsumerId(wsrpConsumerId,
				count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WSRPConsumerPortlet exists with the key {");

			msg.append("wsrpConsumerId=" + wsrpConsumerId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchConsumerPortletException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WSRPConsumerPortlet[] findByWsrpConsumerId_PrevAndNext(
		long wsrpConsumerPortletId, long wsrpConsumerId, OrderByComparator obc)
		throws NoSuchConsumerPortletException, SystemException {
		WSRPConsumerPortlet wsrpConsumerPortlet = findByPrimaryKey(wsrpConsumerPortletId);

		int count = countByWsrpConsumerId(wsrpConsumerId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT wsrpConsumerPortlet FROM WSRPConsumerPortlet wsrpConsumerPortlet WHERE ");

			query.append("wsrpConsumerPortlet.wsrpConsumerId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("wsrpConsumerPortlet.");
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

			else {
				query.append("ORDER BY ");

				query.append("wsrpConsumerPortlet.name ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(wsrpConsumerId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					wsrpConsumerPortlet);

			WSRPConsumerPortlet[] array = new WSRPConsumerPortletImpl[3];

			array[0] = (WSRPConsumerPortlet)objArray[0];
			array[1] = (WSRPConsumerPortlet)objArray[1];
			array[2] = (WSRPConsumerPortlet)objArray[2];

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

	public List<WSRPConsumerPortlet> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<WSRPConsumerPortlet> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<WSRPConsumerPortlet> findAll(int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WSRPConsumerPortlet> list = (List<WSRPConsumerPortlet>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT wsrpConsumerPortlet FROM WSRPConsumerPortlet wsrpConsumerPortlet ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("wsrpConsumerPortlet.");
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

				else {
					query.append("ORDER BY ");

					query.append("wsrpConsumerPortlet.name ASC");
				}

				Query q = session.createQuery(query.toString());

				if (obc == null) {
					list = (List<WSRPConsumerPortlet>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<WSRPConsumerPortlet>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPConsumerPortlet>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByWsrpConsumerId(long wsrpConsumerId)
		throws SystemException {
		for (WSRPConsumerPortlet wsrpConsumerPortlet : findByWsrpConsumerId(
				wsrpConsumerId)) {
			remove(wsrpConsumerPortlet);
		}
	}

	public void removeAll() throws SystemException {
		for (WSRPConsumerPortlet wsrpConsumerPortlet : findAll()) {
			remove(wsrpConsumerPortlet);
		}
	}

	public int countByWsrpConsumerId(long wsrpConsumerId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(wsrpConsumerId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_WSRPCONSUMERID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(wsrpConsumerPortlet) ");
				query.append(
					"FROM WSRPConsumerPortlet wsrpConsumerPortlet WHERE ");

				query.append("wsrpConsumerPortlet.wsrpConsumerId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(wsrpConsumerId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_WSRPCONSUMERID,
					finderArgs, count);

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
						"SELECT COUNT(wsrpConsumerPortlet) FROM WSRPConsumerPortlet wsrpConsumerPortlet");

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
						"value.object.listener.com.liferay.wsrp.model.WSRPConsumerPortlet")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<WSRPConsumerPortlet>> listenersList = new ArrayList<ModelListener<WSRPConsumerPortlet>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<WSRPConsumerPortlet>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(name = "com.liferay.wsrp.service.persistence.WSRPConsumerPersistence.impl")
	protected com.liferay.wsrp.service.persistence.WSRPConsumerPersistence wsrpConsumerPersistence;
	@BeanReference(name = "com.liferay.wsrp.service.persistence.WSRPConsumerPortletPersistence.impl")
	protected com.liferay.wsrp.service.persistence.WSRPConsumerPortletPersistence wsrpConsumerPortletPersistence;
	private static Log _log = LogFactoryUtil.getLog(WSRPConsumerPortletPersistenceImpl.class);
}