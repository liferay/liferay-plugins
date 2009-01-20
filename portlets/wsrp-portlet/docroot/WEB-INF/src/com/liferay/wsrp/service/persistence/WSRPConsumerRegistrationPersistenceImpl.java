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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="WSRPConsumerRegistrationPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPConsumerRegistrationPersistenceImpl extends BasePersistenceImpl
	implements WSRPConsumerRegistrationPersistence {
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
		for (ModelListener listener : listeners) {
			listener.onBeforeRemove(wsrpConsumerRegistration);
		}

		wsrpConsumerRegistration = removeImpl(wsrpConsumerRegistration);

		for (ModelListener listener : listeners) {
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

			if (BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(WSRPConsumerRegistrationImpl.class,
						wsrpConsumerRegistration.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(wsrpConsumerRegistration);

			session.flush();

			return wsrpConsumerRegistration;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(WSRPConsumerRegistration.class.getName());
		}
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

		for (ModelListener listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(wsrpConsumerRegistration);
			}
			else {
				listener.onBeforeUpdate(wsrpConsumerRegistration);
			}
		}

		wsrpConsumerRegistration = updateImpl(wsrpConsumerRegistration, merge);

		for (ModelListener listener : listeners) {
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
		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, wsrpConsumerRegistration, merge);

			wsrpConsumerRegistration.setNew(false);

			return wsrpConsumerRegistration;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(WSRPConsumerRegistration.class.getName());
		}
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
		Session session = null;

		try {
			session = openSession();

			return (WSRPConsumerRegistration)session.get(WSRPConsumerRegistrationImpl.class,
				new Long(consumerRegistrationId));
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<WSRPConsumerRegistration> findByProducerKey(String producerKey)
		throws SystemException {
		boolean finderClassNameCacheEnabled = WSRPConsumerRegistrationModelImpl.CACHE_ENABLED;
		String finderClassName = WSRPConsumerRegistration.class.getName();
		String finderMethodName = "findByProducerKey";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { producerKey };

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

				List<WSRPConsumerRegistration> list = q.list();

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
			return (List<WSRPConsumerRegistration>)result;
		}
	}

	public List<WSRPConsumerRegistration> findByProducerKey(
		String producerKey, int start, int end) throws SystemException {
		return findByProducerKey(producerKey, start, end, null);
	}

	public List<WSRPConsumerRegistration> findByProducerKey(
		String producerKey, int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = WSRPConsumerRegistrationModelImpl.CACHE_ENABLED;
		String finderClassName = WSRPConsumerRegistration.class.getName();
		String finderMethodName = "findByProducerKey";
		String[] finderParams = new String[] {
				String.class.getName(),
				
				"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};
		Object[] finderArgs = new Object[] {
				producerKey,
				
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

				List<WSRPConsumerRegistration> list = (List<WSRPConsumerRegistration>)QueryUtil.list(q,
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
			return (List<WSRPConsumerRegistration>)result;
		}
	}

	public WSRPConsumerRegistration findByProducerKey_First(
		String producerKey, OrderByComparator obc)
		throws NoSuchConsumerRegistrationException, SystemException {
		List<WSRPConsumerRegistration> list = findByProducerKey(producerKey, 0,
				1, obc);

		if (list.size() == 0) {
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

		if (list.size() == 0) {
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
		boolean finderClassNameCacheEnabled = WSRPConsumerRegistrationModelImpl.CACHE_ENABLED;
		String finderClassName = WSRPConsumerRegistration.class.getName();
		String finderMethodName = "fetchByR_P";
		String[] finderParams = new String[] {
				String.class.getName(), String.class.getName()
			};
		Object[] finderArgs = new Object[] { registrationHandle, producerKey };

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
			List<WSRPConsumerRegistration> list = (List<WSRPConsumerRegistration>)result;

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

	public List<WSRPConsumerRegistration> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<WSRPConsumerRegistration> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<WSRPConsumerRegistration> findAll(int start, int end,
		OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = WSRPConsumerRegistrationModelImpl.CACHE_ENABLED;
		String finderClassName = WSRPConsumerRegistration.class.getName();
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
					"FROM com.liferay.wsrp.model.WSRPConsumerRegistration ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				List<WSRPConsumerRegistration> list = null;

				if (obc == null) {
					list = (List<WSRPConsumerRegistration>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<WSRPConsumerRegistration>)QueryUtil.list(q,
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
			return (List<WSRPConsumerRegistration>)result;
		}
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
		boolean finderClassNameCacheEnabled = WSRPConsumerRegistrationModelImpl.CACHE_ENABLED;
		String finderClassName = WSRPConsumerRegistration.class.getName();
		String finderMethodName = "countByProducerKey";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { producerKey };

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

	public int countByR_P(String registrationHandle, String producerKey)
		throws SystemException {
		boolean finderClassNameCacheEnabled = WSRPConsumerRegistrationModelImpl.CACHE_ENABLED;
		String finderClassName = WSRPConsumerRegistration.class.getName();
		String finderMethodName = "countByR_P";
		String[] finderParams = new String[] {
				String.class.getName(), String.class.getName()
			};
		Object[] finderArgs = new Object[] { registrationHandle, producerKey };

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
		boolean finderClassNameCacheEnabled = WSRPConsumerRegistrationModelImpl.CACHE_ENABLED;
		String finderClassName = WSRPConsumerRegistration.class.getName();
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
						"SELECT COUNT(*) FROM com.liferay.wsrp.model.WSRPConsumerRegistration");

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
						"value.object.listener.com.liferay.wsrp.model.WSRPConsumerRegistration")));

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
	private static Log _log = LogFactory.getLog(WSRPConsumerRegistrationPersistenceImpl.class);
}