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

import com.liferay.wsrp.NoSuchConsumerRegistrationException;
import com.liferay.wsrp.model.WSRPConsumerRegistration;
import com.liferay.wsrp.model.impl.WSRPConsumerRegistrationImpl;
import com.liferay.wsrp.model.impl.WSRPConsumerRegistrationModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="WSRPConsumerRegistrationPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPConsumerRegistrationPersistenceImpl extends BasePersistenceImpl
	implements WSRPConsumerRegistrationPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = WSRPConsumerRegistrationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_PRODUCERKEY = new FinderPath(WSRPConsumerRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerRegistrationModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByProducerKey",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_PRODUCERKEY = new FinderPath(WSRPConsumerRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerRegistrationModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByProducerKey",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_PRODUCERKEY = new FinderPath(WSRPConsumerRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerRegistrationModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByProducerKey",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_R_P = new FinderPath(WSRPConsumerRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerRegistrationModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_ENTITY, "fetchByR_P",
			new String[] { String.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_R_P = new FinderPath(WSRPConsumerRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerRegistrationModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByR_P",
			new String[] { String.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(WSRPConsumerRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerRegistrationModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WSRPConsumerRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerRegistrationModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	public void cacheResult(WSRPConsumerRegistration wsrpConsumerRegistration) {
		EntityCacheUtil.putResult(WSRPConsumerRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerRegistrationImpl.class,
			wsrpConsumerRegistration.getPrimaryKey(), wsrpConsumerRegistration);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_P,
			new Object[] {
				wsrpConsumerRegistration.getRegistrationHandle(),
				
			wsrpConsumerRegistration.getProducerKey()
			}, wsrpConsumerRegistration);
	}

	public void cacheResult(
		List<WSRPConsumerRegistration> wsrpConsumerRegistrations) {
		for (WSRPConsumerRegistration wsrpConsumerRegistration : wsrpConsumerRegistrations) {
			if (EntityCacheUtil.getResult(
						WSRPConsumerRegistrationModelImpl.ENTITY_CACHE_ENABLED,
						WSRPConsumerRegistrationImpl.class,
						wsrpConsumerRegistration.getPrimaryKey(), this) == null) {
				cacheResult(wsrpConsumerRegistration);
			}
		}
	}

	public WSRPConsumerRegistration create(long consumerRegistrationId) {
		WSRPConsumerRegistration wsrpConsumerRegistration = new WSRPConsumerRegistrationImpl();

		wsrpConsumerRegistration.setNew(true);
		wsrpConsumerRegistration.setPrimaryKey(consumerRegistrationId);

		return wsrpConsumerRegistration;
	}

	public WSRPConsumerRegistration remove(long consumerRegistrationId)
		throws NoSuchConsumerRegistrationException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WSRPConsumerRegistration wsrpConsumerRegistration = (WSRPConsumerRegistration)session.get(WSRPConsumerRegistrationImpl.class,
					new Long(consumerRegistrationId));

			if (wsrpConsumerRegistration == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"No WSRPConsumerRegistration exists with the primary key " +
						consumerRegistrationId);
				}

				throw new NoSuchConsumerRegistrationException(
					"No WSRPConsumerRegistration exists with the primary key " +
					consumerRegistrationId);
			}

			return remove(wsrpConsumerRegistration);
		}
		catch (NoSuchConsumerRegistrationException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public WSRPConsumerRegistration remove(
		WSRPConsumerRegistration wsrpConsumerRegistration)
		throws SystemException {
		for (ModelListener<WSRPConsumerRegistration> listener : listeners) {
			listener.onBeforeRemove(wsrpConsumerRegistration);
		}

		wsrpConsumerRegistration = removeImpl(wsrpConsumerRegistration);

		for (ModelListener<WSRPConsumerRegistration> listener : listeners) {
			listener.onAfterRemove(wsrpConsumerRegistration);
		}

		return wsrpConsumerRegistration;
	}

	protected WSRPConsumerRegistration removeImpl(
		WSRPConsumerRegistration wsrpConsumerRegistration)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (wsrpConsumerRegistration.isCachedModel() ||
					BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(WSRPConsumerRegistrationImpl.class,
						wsrpConsumerRegistration.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(wsrpConsumerRegistration);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		WSRPConsumerRegistrationModelImpl wsrpConsumerRegistrationModelImpl = (WSRPConsumerRegistrationModelImpl)wsrpConsumerRegistration;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_R_P,
			new Object[] {
				wsrpConsumerRegistrationModelImpl.getOriginalRegistrationHandle(),
				
			wsrpConsumerRegistrationModelImpl.getOriginalProducerKey()
			});

		EntityCacheUtil.removeResult(WSRPConsumerRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerRegistrationImpl.class,
			wsrpConsumerRegistration.getPrimaryKey());

		return wsrpConsumerRegistration;
	}

	public WSRPConsumerRegistration update(
		WSRPConsumerRegistration wsrpConsumerRegistration)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(WSRPConsumerRegistration wsrpConsumerRegistration) method. Use update(WSRPConsumerRegistration wsrpConsumerRegistration, boolean merge) instead.");
		}

		return update(wsrpConsumerRegistration, false);
	}

	public WSRPConsumerRegistration update(
		WSRPConsumerRegistration wsrpConsumerRegistration, boolean merge)
		throws SystemException {
		boolean isNew = wsrpConsumerRegistration.isNew();

		for (ModelListener<WSRPConsumerRegistration> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(wsrpConsumerRegistration);
			}
			else {
				listener.onBeforeUpdate(wsrpConsumerRegistration);
			}
		}

		wsrpConsumerRegistration = updateImpl(wsrpConsumerRegistration, merge);

		for (ModelListener<WSRPConsumerRegistration> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(wsrpConsumerRegistration);
			}
			else {
				listener.onAfterUpdate(wsrpConsumerRegistration);
			}
		}

		return wsrpConsumerRegistration;
	}

	public WSRPConsumerRegistration updateImpl(
		com.liferay.wsrp.model.WSRPConsumerRegistration wsrpConsumerRegistration,
		boolean merge) throws SystemException {
		boolean isNew = wsrpConsumerRegistration.isNew();

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, wsrpConsumerRegistration, merge);

			wsrpConsumerRegistration.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(WSRPConsumerRegistrationModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerRegistrationImpl.class,
			wsrpConsumerRegistration.getPrimaryKey(), wsrpConsumerRegistration);

		WSRPConsumerRegistrationModelImpl wsrpConsumerRegistrationModelImpl = (WSRPConsumerRegistrationModelImpl)wsrpConsumerRegistration;

		if (!isNew &&
				(!wsrpConsumerRegistration.getRegistrationHandle()
											  .equals(wsrpConsumerRegistrationModelImpl.getOriginalRegistrationHandle()) ||
				!wsrpConsumerRegistration.getProducerKey()
											 .equals(wsrpConsumerRegistrationModelImpl.getOriginalProducerKey()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_R_P,
				new Object[] {
					wsrpConsumerRegistrationModelImpl.getOriginalRegistrationHandle(),
					
				wsrpConsumerRegistrationModelImpl.getOriginalProducerKey()
				});
		}

		if (isNew ||
				(!wsrpConsumerRegistration.getRegistrationHandle()
											  .equals(wsrpConsumerRegistrationModelImpl.getOriginalRegistrationHandle()) ||
				!wsrpConsumerRegistration.getProducerKey()
											 .equals(wsrpConsumerRegistrationModelImpl.getOriginalProducerKey()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_P,
				new Object[] {
					wsrpConsumerRegistration.getRegistrationHandle(),
					
				wsrpConsumerRegistration.getProducerKey()
				}, wsrpConsumerRegistration);
		}

		return wsrpConsumerRegistration;
	}

	public WSRPConsumerRegistration findByPrimaryKey(
		long consumerRegistrationId)
		throws NoSuchConsumerRegistrationException, SystemException {
		WSRPConsumerRegistration wsrpConsumerRegistration = fetchByPrimaryKey(consumerRegistrationId);

		if (wsrpConsumerRegistration == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"No WSRPConsumerRegistration exists with the primary key " +
					consumerRegistrationId);
			}

			throw new NoSuchConsumerRegistrationException(
				"No WSRPConsumerRegistration exists with the primary key " +
				consumerRegistrationId);
		}

		return wsrpConsumerRegistration;
	}

	public WSRPConsumerRegistration fetchByPrimaryKey(
		long consumerRegistrationId) throws SystemException {
		WSRPConsumerRegistration wsrpConsumerRegistration = (WSRPConsumerRegistration)EntityCacheUtil.getResult(WSRPConsumerRegistrationModelImpl.ENTITY_CACHE_ENABLED,
				WSRPConsumerRegistrationImpl.class, consumerRegistrationId, this);

		if (wsrpConsumerRegistration == null) {
			Session session = null;

			try {
				session = openSession();

				wsrpConsumerRegistration = (WSRPConsumerRegistration)session.get(WSRPConsumerRegistrationImpl.class,
						new Long(consumerRegistrationId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (wsrpConsumerRegistration != null) {
					cacheResult(wsrpConsumerRegistration);
				}

				closeSession(session);
			}
		}

		return wsrpConsumerRegistration;
	}

	public List<WSRPConsumerRegistration> findByProducerKey(String producerKey)
		throws SystemException {
		Object[] finderArgs = new Object[] { producerKey };

		List<WSRPConsumerRegistration> list = (List<WSRPConsumerRegistration>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_PRODUCERKEY,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.wsrp.model.WSRPConsumerRegistration WHERE ");

				if (producerKey == null) {
					query.append("producerKey IS NULL");
				}
				else {
					query.append("producerKey = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (producerKey != null) {
					qPos.add(producerKey);
				}

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPConsumerRegistration>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_PRODUCERKEY,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WSRPConsumerRegistration> findByProducerKey(
		String producerKey, int start, int end) throws SystemException {
		return findByProducerKey(producerKey, start, end, null);
	}

	public List<WSRPConsumerRegistration> findByProducerKey(
		String producerKey, int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				producerKey,
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WSRPConsumerRegistration> list = (List<WSRPConsumerRegistration>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_PRODUCERKEY,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.wsrp.model.WSRPConsumerRegistration WHERE ");

				if (producerKey == null) {
					query.append("producerKey IS NULL");
				}
				else {
					query.append("producerKey = ?");
				}

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (producerKey != null) {
					qPos.add(producerKey);
				}

				list = (List<WSRPConsumerRegistration>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPConsumerRegistration>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_PRODUCERKEY,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WSRPConsumerRegistration findByProducerKey_First(
		String producerKey, OrderByComparator obc)
		throws NoSuchConsumerRegistrationException, SystemException {
		List<WSRPConsumerRegistration> list = findByProducerKey(producerKey, 0,
				1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WSRPConsumerRegistration exists with the key {");

			msg.append("producerKey=" + producerKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchConsumerRegistrationException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WSRPConsumerRegistration findByProducerKey_Last(String producerKey,
		OrderByComparator obc)
		throws NoSuchConsumerRegistrationException, SystemException {
		int count = countByProducerKey(producerKey);

		List<WSRPConsumerRegistration> list = findByProducerKey(producerKey,
				count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WSRPConsumerRegistration exists with the key {");

			msg.append("producerKey=" + producerKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchConsumerRegistrationException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WSRPConsumerRegistration[] findByProducerKey_PrevAndNext(
		long consumerRegistrationId, String producerKey, OrderByComparator obc)
		throws NoSuchConsumerRegistrationException, SystemException {
		WSRPConsumerRegistration wsrpConsumerRegistration = findByPrimaryKey(consumerRegistrationId);

		int count = countByProducerKey(producerKey);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append(
				"FROM com.liferay.wsrp.model.WSRPConsumerRegistration WHERE ");

			if (producerKey == null) {
				query.append("producerKey IS NULL");
			}
			else {
				query.append("producerKey = ?");
			}

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			if (producerKey != null) {
				qPos.add(producerKey);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					wsrpConsumerRegistration);

			WSRPConsumerRegistration[] array = new WSRPConsumerRegistrationImpl[3];

			array[0] = (WSRPConsumerRegistration)objArray[0];
			array[1] = (WSRPConsumerRegistration)objArray[1];
			array[2] = (WSRPConsumerRegistration)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public WSRPConsumerRegistration findByR_P(String registrationHandle,
		String producerKey)
		throws NoSuchConsumerRegistrationException, SystemException {
		WSRPConsumerRegistration wsrpConsumerRegistration = fetchByR_P(registrationHandle,
				producerKey);

		if (wsrpConsumerRegistration == null) {
			StringBuilder msg = new StringBuilder();

			msg.append("No WSRPConsumerRegistration exists with the key {");

			msg.append("registrationHandle=" + registrationHandle);

			msg.append(", ");
			msg.append("producerKey=" + producerKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchConsumerRegistrationException(msg.toString());
		}

		return wsrpConsumerRegistration;
	}

	public WSRPConsumerRegistration fetchByR_P(String registrationHandle,
		String producerKey) throws SystemException {
		return fetchByR_P(registrationHandle, producerKey, true);
	}

	public WSRPConsumerRegistration fetchByR_P(String registrationHandle,
		String producerKey, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { registrationHandle, producerKey };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_R_P,
					finderArgs, this);
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.wsrp.model.WSRPConsumerRegistration WHERE ");

				if (registrationHandle == null) {
					query.append("registrationHandle IS NULL");
				}
				else {
					query.append("registrationHandle = ?");
				}

				query.append(" AND ");

				if (producerKey == null) {
					query.append("producerKey IS NULL");
				}
				else {
					query.append("producerKey = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (registrationHandle != null) {
					qPos.add(registrationHandle);
				}

				if (producerKey != null) {
					qPos.add(producerKey);
				}

				List<WSRPConsumerRegistration> list = q.list();

				result = list;

				WSRPConsumerRegistration wsrpConsumerRegistration = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_P,
						finderArgs, list);
				}
				else {
					wsrpConsumerRegistration = list.get(0);

					cacheResult(wsrpConsumerRegistration);
				}

				return wsrpConsumerRegistration;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_P,
						finderArgs, new ArrayList<WSRPConsumerRegistration>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List) {
				return null;
			}
			else {
				return (WSRPConsumerRegistration)result;
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

	public List<WSRPConsumerRegistration> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<WSRPConsumerRegistration> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<WSRPConsumerRegistration> findAll(int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WSRPConsumerRegistration> list = (List<WSRPConsumerRegistration>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM com.liferay.wsrp.model.WSRPConsumerRegistration ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				if (obc == null) {
					list = (List<WSRPConsumerRegistration>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<WSRPConsumerRegistration>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPConsumerRegistration>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByProducerKey(String producerKey)
		throws SystemException {
		for (WSRPConsumerRegistration wsrpConsumerRegistration : findByProducerKey(
				producerKey)) {
			remove(wsrpConsumerRegistration);
		}
	}

	public void removeByR_P(String registrationHandle, String producerKey)
		throws NoSuchConsumerRegistrationException, SystemException {
		WSRPConsumerRegistration wsrpConsumerRegistration = findByR_P(registrationHandle,
				producerKey);

		remove(wsrpConsumerRegistration);
	}

	public void removeAll() throws SystemException {
		for (WSRPConsumerRegistration wsrpConsumerRegistration : findAll()) {
			remove(wsrpConsumerRegistration);
		}
	}

	public int countByProducerKey(String producerKey) throws SystemException {
		Object[] finderArgs = new Object[] { producerKey };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PRODUCERKEY,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.wsrp.model.WSRPConsumerRegistration WHERE ");

				if (producerKey == null) {
					query.append("producerKey IS NULL");
				}
				else {
					query.append("producerKey = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (producerKey != null) {
					qPos.add(producerKey);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PRODUCERKEY,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByR_P(String registrationHandle, String producerKey)
		throws SystemException {
		Object[] finderArgs = new Object[] { registrationHandle, producerKey };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_R_P,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM com.liferay.wsrp.model.WSRPConsumerRegistration WHERE ");

				if (registrationHandle == null) {
					query.append("registrationHandle IS NULL");
				}
				else {
					query.append("registrationHandle = ?");
				}

				query.append(" AND ");

				if (producerKey == null) {
					query.append("producerKey IS NULL");
				}
				else {
					query.append("producerKey = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (registrationHandle != null) {
					qPos.add(registrationHandle);
				}

				if (producerKey != null) {
					qPos.add(producerKey);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_R_P, finderArgs,
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
						"SELECT COUNT(*) FROM com.liferay.wsrp.model.WSRPConsumerRegistration");

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
						"value.object.listener.com.liferay.wsrp.model.WSRPConsumerRegistration")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<WSRPConsumerRegistration>> listenersList = new ArrayList<ModelListener<WSRPConsumerRegistration>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<WSRPConsumerRegistration>)Class.forName(
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
	private static Log _log = LogFactoryUtil.getLog(WSRPConsumerRegistrationPersistenceImpl.class);
}