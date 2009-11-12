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

import com.liferay.portal.NoSuchModelException;
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

import com.liferay.wsrp.NoSuchConsumerException;
import com.liferay.wsrp.model.WSRPConsumer;
import com.liferay.wsrp.model.impl.WSRPConsumerImpl;
import com.liferay.wsrp.model.impl.WSRPConsumerModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="WSRPConsumerPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WSRPConsumerPersistenceImpl extends BasePersistenceImpl<WSRPConsumer>
	implements WSRPConsumerPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = WSRPConsumerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByCompanyId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_COMPANYID = new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByCompanyId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(WSRPConsumer wsrpConsumer) {
		EntityCacheUtil.putResult(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerImpl.class, wsrpConsumer.getPrimaryKey(), wsrpConsumer);
	}

	public void cacheResult(List<WSRPConsumer> wsrpConsumers) {
		for (WSRPConsumer wsrpConsumer : wsrpConsumers) {
			if (EntityCacheUtil.getResult(
						WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
						WSRPConsumerImpl.class, wsrpConsumer.getPrimaryKey(),
						this) == null) {
				cacheResult(wsrpConsumer);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(WSRPConsumerImpl.class.getName());
		EntityCacheUtil.clearCache(WSRPConsumerImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public WSRPConsumer create(long wsrpConsumerId) {
		WSRPConsumer wsrpConsumer = new WSRPConsumerImpl();

		wsrpConsumer.setNew(true);
		wsrpConsumer.setPrimaryKey(wsrpConsumerId);

		return wsrpConsumer;
	}

	public WSRPConsumer remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public WSRPConsumer remove(long wsrpConsumerId)
		throws NoSuchConsumerException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WSRPConsumer wsrpConsumer = (WSRPConsumer)session.get(WSRPConsumerImpl.class,
					new Long(wsrpConsumerId));

			if (wsrpConsumer == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No WSRPConsumer exists with the primary key " +
						wsrpConsumerId);
				}

				throw new NoSuchConsumerException(
					"No WSRPConsumer exists with the primary key " +
					wsrpConsumerId);
			}

			return remove(wsrpConsumer);
		}
		catch (NoSuchConsumerException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public WSRPConsumer remove(WSRPConsumer wsrpConsumer)
		throws SystemException {
		for (ModelListener<WSRPConsumer> listener : listeners) {
			listener.onBeforeRemove(wsrpConsumer);
		}

		wsrpConsumer = removeImpl(wsrpConsumer);

		for (ModelListener<WSRPConsumer> listener : listeners) {
			listener.onAfterRemove(wsrpConsumer);
		}

		return wsrpConsumer;
	}

	protected WSRPConsumer removeImpl(WSRPConsumer wsrpConsumer)
		throws SystemException {
		wsrpConsumer = toUnwrappedModel(wsrpConsumer);

		Session session = null;

		try {
			session = openSession();

			if (wsrpConsumer.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(WSRPConsumerImpl.class,
						wsrpConsumer.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(wsrpConsumer);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerImpl.class, wsrpConsumer.getPrimaryKey());

		return wsrpConsumer;
	}

	public WSRPConsumer updateImpl(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer, boolean merge)
		throws SystemException {
		wsrpConsumer = toUnwrappedModel(wsrpConsumer);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, wsrpConsumer, merge);

			wsrpConsumer.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerImpl.class, wsrpConsumer.getPrimaryKey(), wsrpConsumer);

		return wsrpConsumer;
	}

	protected WSRPConsumer toUnwrappedModel(WSRPConsumer wsrpConsumer) {
		if (wsrpConsumer instanceof WSRPConsumerImpl) {
			return wsrpConsumer;
		}

		WSRPConsumerImpl wsrpConsumerImpl = new WSRPConsumerImpl();

		wsrpConsumerImpl.setNew(wsrpConsumer.isNew());
		wsrpConsumerImpl.setPrimaryKey(wsrpConsumer.getPrimaryKey());

		wsrpConsumerImpl.setWsrpConsumerId(wsrpConsumer.getWsrpConsumerId());
		wsrpConsumerImpl.setCompanyId(wsrpConsumer.getCompanyId());
		wsrpConsumerImpl.setCreateDate(wsrpConsumer.getCreateDate());
		wsrpConsumerImpl.setModifiedDate(wsrpConsumer.getModifiedDate());
		wsrpConsumerImpl.setName(wsrpConsumer.getName());
		wsrpConsumerImpl.setUrl(wsrpConsumer.getUrl());
		wsrpConsumerImpl.setWsdl(wsrpConsumer.getWsdl());
		wsrpConsumerImpl.setRegistrationContextString(wsrpConsumer.getRegistrationContextString());
		wsrpConsumerImpl.setRegistrationPropertiesString(wsrpConsumer.getRegistrationPropertiesString());

		return wsrpConsumerImpl;
	}

	public WSRPConsumer findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public WSRPConsumer findByPrimaryKey(long wsrpConsumerId)
		throws NoSuchConsumerException, SystemException {
		WSRPConsumer wsrpConsumer = fetchByPrimaryKey(wsrpConsumerId);

		if (wsrpConsumer == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No WSRPConsumer exists with the primary key " +
					wsrpConsumerId);
			}

			throw new NoSuchConsumerException(
				"No WSRPConsumer exists with the primary key " +
				wsrpConsumerId);
		}

		return wsrpConsumer;
	}

	public WSRPConsumer fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public WSRPConsumer fetchByPrimaryKey(long wsrpConsumerId)
		throws SystemException {
		WSRPConsumer wsrpConsumer = (WSRPConsumer)EntityCacheUtil.getResult(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
				WSRPConsumerImpl.class, wsrpConsumerId, this);

		if (wsrpConsumer == null) {
			Session session = null;

			try {
				session = openSession();

				wsrpConsumer = (WSRPConsumer)session.get(WSRPConsumerImpl.class,
						new Long(wsrpConsumerId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (wsrpConsumer != null) {
					cacheResult(wsrpConsumer);
				}

				closeSession(session);
			}
		}

		return wsrpConsumer;
	}

	public List<WSRPConsumer> findByCompanyId(long companyId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(companyId) };

		List<WSRPConsumer> list = (List<WSRPConsumer>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT wsrpConsumer FROM WSRPConsumer wsrpConsumer WHERE ");

				query.append("wsrpConsumer.companyId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("wsrpConsumer.name ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPConsumer>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WSRPConsumer> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	public List<WSRPConsumer> findByCompanyId(long companyId, int start,
		int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WSRPConsumer> list = (List<WSRPConsumer>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_COMPANYID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT wsrpConsumer FROM WSRPConsumer wsrpConsumer WHERE ");

				query.append("wsrpConsumer.companyId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("wsrpConsumer.");
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

					query.append("wsrpConsumer.name ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<WSRPConsumer>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPConsumer>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WSRPConsumer findByCompanyId_First(long companyId,
		OrderByComparator obc) throws NoSuchConsumerException, SystemException {
		List<WSRPConsumer> list = findByCompanyId(companyId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WSRPConsumer exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchConsumerException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WSRPConsumer findByCompanyId_Last(long companyId,
		OrderByComparator obc) throws NoSuchConsumerException, SystemException {
		int count = countByCompanyId(companyId);

		List<WSRPConsumer> list = findByCompanyId(companyId, count - 1, count,
				obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WSRPConsumer exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchConsumerException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WSRPConsumer[] findByCompanyId_PrevAndNext(long wsrpConsumerId,
		long companyId, OrderByComparator obc)
		throws NoSuchConsumerException, SystemException {
		WSRPConsumer wsrpConsumer = findByPrimaryKey(wsrpConsumerId);

		int count = countByCompanyId(companyId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"SELECT wsrpConsumer FROM WSRPConsumer wsrpConsumer WHERE ");

			query.append("wsrpConsumer.companyId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");

				String[] orderByFields = obc.getOrderByFields();

				for (int i = 0; i < orderByFields.length; i++) {
					query.append("wsrpConsumer.");
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

				query.append("wsrpConsumer.name ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					wsrpConsumer);

			WSRPConsumer[] array = new WSRPConsumerImpl[3];

			array[0] = (WSRPConsumer)objArray[0];
			array[1] = (WSRPConsumer)objArray[1];
			array[2] = (WSRPConsumer)objArray[2];

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

	public List<WSRPConsumer> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<WSRPConsumer> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<WSRPConsumer> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WSRPConsumer> list = (List<WSRPConsumer>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"SELECT wsrpConsumer FROM WSRPConsumer wsrpConsumer ");

				if (obc != null) {
					query.append("ORDER BY ");

					String[] orderByFields = obc.getOrderByFields();

					for (int i = 0; i < orderByFields.length; i++) {
						query.append("wsrpConsumer.");
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

					query.append("wsrpConsumer.name ASC");
				}

				Query q = session.createQuery(query.toString());

				if (obc == null) {
					list = (List<WSRPConsumer>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<WSRPConsumer>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPConsumer>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByCompanyId(long companyId) throws SystemException {
		for (WSRPConsumer wsrpConsumer : findByCompanyId(companyId)) {
			remove(wsrpConsumer);
		}
	}

	public void removeAll() throws SystemException {
		for (WSRPConsumer wsrpConsumer : findAll()) {
			remove(wsrpConsumer);
		}
	}

	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(companyId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(wsrpConsumer) ");
				query.append("FROM WSRPConsumer wsrpConsumer WHERE ");

				query.append("wsrpConsumer.companyId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPANYID,
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
						"SELECT COUNT(wsrpConsumer) FROM WSRPConsumer wsrpConsumer");

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
						"value.object.listener.com.liferay.wsrp.model.WSRPConsumer")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<WSRPConsumer>> listenersList = new ArrayList<ModelListener<WSRPConsumer>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<WSRPConsumer>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(name = "com.liferay.wsrp.service.persistence.WSRPConsumerPersistence")
	protected com.liferay.wsrp.service.persistence.WSRPConsumerPersistence wsrpConsumerPersistence;
	@BeanReference(name = "com.liferay.wsrp.service.persistence.WSRPConsumerPortletPersistence")
	protected com.liferay.wsrp.service.persistence.WSRPConsumerPortletPersistence wsrpConsumerPortletPersistence;
	@BeanReference(name = "com.liferay.wsrp.service.persistence.WSRPProducerPersistence")
	protected com.liferay.wsrp.service.persistence.WSRPProducerPersistence wsrpProducerPersistence;
	private static Log _log = LogFactoryUtil.getLog(WSRPConsumerPersistenceImpl.class);
}