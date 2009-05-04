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

package com.liferay.ruon.service.persistence;

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

import com.liferay.ruon.NoSuchNetworkException;
import com.liferay.ruon.model.Network;
import com.liferay.ruon.model.impl.NetworkImpl;
import com.liferay.ruon.model.impl.NetworkModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="NetworkPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NetworkPersistenceImpl extends BasePersistenceImpl
	implements NetworkPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = NetworkImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FETCH_BY_NAME = new FinderPath(NetworkModelImpl.ENTITY_CACHE_ENABLED,
			NetworkModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_ENTITY,
			"fetchByName", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(NetworkModelImpl.ENTITY_CACHE_ENABLED,
			NetworkModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByName", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(NetworkModelImpl.ENTITY_CACHE_ENABLED,
			NetworkModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NetworkModelImpl.ENTITY_CACHE_ENABLED,
			NetworkModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(Network network) {
		EntityCacheUtil.putResult(NetworkModelImpl.ENTITY_CACHE_ENABLED,
			NetworkImpl.class, network.getPrimaryKey(), network);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
			new Object[] { network.getName() }, network);
	}

	public void cacheResult(List<Network> networks) {
		for (Network network : networks) {
			if (EntityCacheUtil.getResult(
						NetworkModelImpl.ENTITY_CACHE_ENABLED,
						NetworkImpl.class, network.getPrimaryKey(), this) == null) {
				cacheResult(network);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(NetworkImpl.class.getName());
		EntityCacheUtil.clearCache(NetworkImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public Network create(long networkId) {
		Network network = new NetworkImpl();

		network.setNew(true);
		network.setPrimaryKey(networkId);

		return network;
	}

	public Network remove(long networkId)
		throws NoSuchNetworkException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Network network = (Network)session.get(NetworkImpl.class,
					new Long(networkId));

			if (network == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No Network exists with the primary key " +
						networkId);
				}

				throw new NoSuchNetworkException(
					"No Network exists with the primary key " + networkId);
			}

			return remove(network);
		}
		catch (NoSuchNetworkException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public Network remove(Network network) throws SystemException {
		for (ModelListener<Network> listener : listeners) {
			listener.onBeforeRemove(network);
		}

		network = removeImpl(network);

		for (ModelListener<Network> listener : listeners) {
			listener.onAfterRemove(network);
		}

		return network;
	}

	protected Network removeImpl(Network network) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (network.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(NetworkImpl.class,
						network.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(network);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		NetworkModelImpl networkModelImpl = (NetworkModelImpl)network;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME,
			new Object[] { networkModelImpl.getOriginalName() });

		EntityCacheUtil.removeResult(NetworkModelImpl.ENTITY_CACHE_ENABLED,
			NetworkImpl.class, network.getPrimaryKey());

		return network;
	}

	public Network update(Network network) throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(Network network) method. Use update(Network network, boolean merge) instead.");
		}

		return update(network, false);
	}

	public Network update(Network network, boolean merge)
		throws SystemException {
		boolean isNew = network.isNew();

		for (ModelListener<Network> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(network);
			}
			else {
				listener.onBeforeUpdate(network);
			}
		}

		network = updateImpl(network, merge);

		for (ModelListener<Network> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(network);
			}
			else {
				listener.onAfterUpdate(network);
			}
		}

		return network;
	}

	public Network updateImpl(com.liferay.ruon.model.Network network,
		boolean merge) throws SystemException {
		boolean isNew = network.isNew();

		NetworkModelImpl networkModelImpl = (NetworkModelImpl)network;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, network, merge);

			network.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(NetworkModelImpl.ENTITY_CACHE_ENABLED,
			NetworkImpl.class, network.getPrimaryKey(), network);

		if (!isNew &&
				(!network.getName().equals(networkModelImpl.getOriginalName()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME,
				new Object[] { networkModelImpl.getOriginalName() });
		}

		if (isNew ||
				(!network.getName().equals(networkModelImpl.getOriginalName()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
				new Object[] { network.getName() }, network);
		}

		return network;
	}

	public Network findByPrimaryKey(long networkId)
		throws NoSuchNetworkException, SystemException {
		Network network = fetchByPrimaryKey(networkId);

		if (network == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No Network exists with the primary key " +
					networkId);
			}

			throw new NoSuchNetworkException(
				"No Network exists with the primary key " + networkId);
		}

		return network;
	}

	public Network fetchByPrimaryKey(long networkId) throws SystemException {
		Network network = (Network)EntityCacheUtil.getResult(NetworkModelImpl.ENTITY_CACHE_ENABLED,
				NetworkImpl.class, networkId, this);

		if (network == null) {
			Session session = null;

			try {
				session = openSession();

				network = (Network)session.get(NetworkImpl.class,
						new Long(networkId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (network != null) {
					cacheResult(network);
				}

				closeSession(session);
			}
		}

		return network;
	}

	public Network findByName(String name)
		throws NoSuchNetworkException, SystemException {
		Network network = fetchByName(name);

		if (network == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Network exists with the key {");

			msg.append("name=" + name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchNetworkException(msg.toString());
		}

		return network;
	}

	public Network fetchByName(String name) throws SystemException {
		return fetchByName(name, true);
	}

	public Network fetchByName(String name, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { name };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_NAME,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.ruon.model.Network WHERE ");

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

				List<Network> list = q.list();

				result = list;

				Network network = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
						finderArgs, list);
				}
				else {
					network = list.get(0);

					cacheResult(network);

					if ((network.getName() == null) ||
							!network.getName().equals(name)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
							finderArgs, list);
					}
				}

				return network;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
						finderArgs, new ArrayList<Network>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List) {
				return null;
			}
			else {
				return (Network)result;
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

	public List<Network> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<Network> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	public List<Network> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<Network> list = (List<Network>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM com.liferay.ruon.model.Network ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				if (obc == null) {
					list = (List<Network>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Network>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Network>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByName(String name)
		throws NoSuchNetworkException, SystemException {
		Network network = findByName(name);

		remove(network);
	}

	public void removeAll() throws SystemException {
		for (Network network : findAll()) {
			remove(network);
		}
	}

	public int countByName(String name) throws SystemException {
		Object[] finderArgs = new Object[] { name };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_NAME,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.ruon.model.Network WHERE ");

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NAME,
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
						"SELECT COUNT(*) FROM com.liferay.ruon.model.Network");

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
						"value.object.listener.com.liferay.ruon.model.Network")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Network>> listenersList = new ArrayList<ModelListener<Network>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Network>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(name = "com.liferay.ruon.service.persistence.NetworkPersistence.impl")
	protected com.liferay.ruon.service.persistence.NetworkPersistence networkPersistence;
	@BeanReference(name = "com.liferay.ruon.service.persistence.PresencePersistence.impl")
	protected com.liferay.ruon.service.persistence.PresencePersistence presencePersistence;
	private static Log _log = LogFactoryUtil.getLog(NetworkPersistenceImpl.class);
}