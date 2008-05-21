/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.ipgeocoder.service.persistence;

import com.liferay.ipgeocoder.NoSuchIPInfoException;
import com.liferay.ipgeocoder.model.IPInfo;
import com.liferay.ipgeocoder.model.impl.IPInfoImpl;
import com.liferay.ipgeocoder.model.impl.IPInfoModelImpl;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.DynamicQuery;
import com.liferay.portal.kernel.dao.DynamicQueryInitializer;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringMaker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;

import com.liferay.portlet.service.BasePersistence;
import com.liferay.portlet.service.FinderCache;
import com.liferay.portlet.service.HibernateUtil;
import com.liferay.portlet.service.PropsUtil;

import com.liferay.util.dao.hibernate.QueryPos;
import com.liferay.util.dao.hibernate.QueryUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="IPInfoPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class IPInfoPersistenceImpl extends BasePersistence
	implements IPInfoPersistence {
	public IPInfo create(long ipInfoId) {
		IPInfo ipInfo = new IPInfoImpl();

		ipInfo.setNew(true);
		ipInfo.setPrimaryKey(ipInfoId);

		return ipInfo;
	}

	public IPInfo remove(long ipInfoId)
		throws NoSuchIPInfoException, SystemException {
		Session session = null;

		try {
			session = openSession();

			IPInfo ipInfo = (IPInfo)session.get(IPInfoImpl.class,
					new Long(ipInfoId));

			if (ipInfo == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No IPInfo exists with the primary key " +
						ipInfoId);
				}

				throw new NoSuchIPInfoException(
					"No IPInfo exists with the primary key " + ipInfoId);
			}

			return remove(ipInfo);
		}
		catch (NoSuchIPInfoException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public IPInfo remove(IPInfo ipInfo) throws SystemException {
		if (_listeners != null) {
			for (ModelListener listener : _listeners) {
				listener.onBeforeRemove(ipInfo);
			}
		}

		ipInfo = removeImpl(ipInfo);

		if (_listeners != null) {
			for (ModelListener listener : _listeners) {
				listener.onAfterRemove(ipInfo);
			}
		}

		return ipInfo;
	}

	protected IPInfo removeImpl(IPInfo ipInfo) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			session.delete(ipInfo);

			session.flush();

			return ipInfo;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);

			FinderCache.clearCache(IPInfo.class.getName());
		}
	}

	public IPInfo update(IPInfo ipInfo) throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(IPInfo ipInfo) method. Use update(IPInfo ipInfo, boolean merge) instead.");
		}

		return update(ipInfo, false);
	}

	public IPInfo update(IPInfo ipInfo, boolean merge)
		throws SystemException {
		boolean isNew = ipInfo.isNew();

		if (_listeners != null) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onBeforeCreate(ipInfo);
				}
				else {
					listener.onBeforeUpdate(ipInfo);
				}
			}
		}

		ipInfo = updateImpl(ipInfo, merge);

		if (_listeners != null) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onAfterCreate(ipInfo);
				}
				else {
					listener.onAfterUpdate(ipInfo);
				}
			}
		}

		return ipInfo;
	}

	public IPInfo updateImpl(com.liferay.ipgeocoder.model.IPInfo ipInfo,
		boolean merge) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (merge) {
				session.merge(ipInfo);
			}
			else {
				if (ipInfo.isNew()) {
					session.save(ipInfo);
				}
			}

			session.flush();

			ipInfo.setNew(false);

			return ipInfo;
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);

			FinderCache.clearCache(IPInfo.class.getName());
		}
	}

	public IPInfo findByPrimaryKey(long ipInfoId)
		throws NoSuchIPInfoException, SystemException {
		IPInfo ipInfo = fetchByPrimaryKey(ipInfoId);

		if (ipInfo == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No IPInfo exists with the primary key " + ipInfoId);
			}

			throw new NoSuchIPInfoException(
				"No IPInfo exists with the primary key " + ipInfoId);
		}

		return ipInfo;
	}

	public IPInfo fetchByPrimaryKey(long ipInfoId) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (IPInfo)session.get(IPInfoImpl.class, new Long(ipInfoId));
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public IPInfo findByIpAddress(String ipAddress)
		throws NoSuchIPInfoException, SystemException {
		IPInfo ipInfo = fetchByIpAddress(ipAddress);

		if (ipInfo == null) {
			StringMaker msg = new StringMaker();

			msg.append("No IPInfo exists with the key {");

			msg.append("ipAddress=" + ipAddress);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchIPInfoException(msg.toString());
		}

		return ipInfo;
	}

	public IPInfo fetchByIpAddress(String ipAddress) throws SystemException {
		boolean finderClassNameCacheEnabled = IPInfoModelImpl.CACHE_ENABLED;
		String finderClassName = IPInfo.class.getName();
		String finderMethodName = "fetchByIpAddress";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { ipAddress };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("FROM com.liferay.ipgeocoder.model.IPInfo WHERE ");

				if (ipAddress == null) {
					query.append("ipAddress IS NULL");
				}
				else {
					query.append("ipAddress = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (ipAddress != null) {
					qPos.add(ipAddress);
				}

				List<IPInfo> list = q.list();

				FinderCache.putResult(finderClassNameCacheEnabled,
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
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			List<IPInfo> list = (List<IPInfo>)result;

			if (list.size() == 0) {
				return null;
			}
			else {
				return list.get(0);
			}
		}
	}

	public List<IPInfo> findWithDynamicQuery(
		DynamicQueryInitializer queryInitializer) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			DynamicQuery query = queryInitializer.initialize(session);

			return query.list();
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<IPInfo> findWithDynamicQuery(
		DynamicQueryInitializer queryInitializer, int start, int end)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			DynamicQuery query = queryInitializer.initialize(session);

			query.setLimit(start, end);

			return query.list();
		}
		catch (Exception e) {
			throw HibernateUtil.processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<IPInfo> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<IPInfo> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	public List<IPInfo> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = IPInfoModelImpl.CACHE_ENABLED;
		String finderClassName = IPInfo.class.getName();
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
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("FROM com.liferay.ipgeocoder.model.IPInfo ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				List<IPInfo> list = (List<IPInfo>)QueryUtil.list(q,
						getDialect(), start, end);

				if (obc == null) {
					Collections.sort(list);
				}

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, list);

				return list;
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return (List<IPInfo>)result;
		}
	}

	public void removeByIpAddress(String ipAddress)
		throws NoSuchIPInfoException, SystemException {
		IPInfo ipInfo = findByIpAddress(ipAddress);

		remove(ipInfo);
	}

	public void removeAll() throws SystemException {
		for (IPInfo ipInfo : findAll()) {
			remove(ipInfo);
		}
	}

	public int countByIpAddress(String ipAddress) throws SystemException {
		boolean finderClassNameCacheEnabled = IPInfoModelImpl.CACHE_ENABLED;
		String finderClassName = IPInfo.class.getName();
		String finderMethodName = "countByIpAddress";
		String[] finderParams = new String[] { String.class.getName() };
		Object[] finderArgs = new Object[] { ipAddress };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				StringMaker query = new StringMaker();

				query.append("SELECT COUNT(*) ");
				query.append("FROM com.liferay.ipgeocoder.model.IPInfo WHERE ");

				if (ipAddress == null) {
					query.append("ipAddress IS NULL");
				}
				else {
					query.append("ipAddress = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (ipAddress != null) {
					qPos.add(ipAddress);
				}

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
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
		boolean finderClassNameCacheEnabled = IPInfoModelImpl.CACHE_ENABLED;
		String finderClassName = IPInfo.class.getName();
		String finderMethodName = "countAll";
		String[] finderParams = new String[] {  };
		Object[] finderArgs = new Object[] {  };

		Object result = null;

		if (finderClassNameCacheEnabled) {
			result = FinderCache.getResult(finderClassName, finderMethodName,
					finderParams, finderArgs, getSessionFactory());
		}

		if (result == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(
						"SELECT COUNT(*) FROM com.liferay.ipgeocoder.model.IPInfo");

				Long count = null;

				Iterator<Long> itr = q.list().iterator();

				if (itr.hasNext()) {
					count = itr.next();
				}

				if (count == null) {
					count = new Long(0);
				}

				FinderCache.putResult(finderClassNameCacheEnabled,
					finderClassName, finderMethodName, finderParams,
					finderArgs, count);

				return count.intValue();
			}
			catch (Exception e) {
				throw HibernateUtil.processException(e);
			}
			finally {
				closeSession(session);
			}
		}
		else {
			return ((Long)result).intValue();
		}
	}

	protected void initDao() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					PropsUtil.get(
						"value.object.listener.com.liferay.ipgeocoder.model.IPInfo")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener> listeners = new ArrayList<ModelListener>();

				for (String listenerClassName : listenerClassNames) {
					listeners.add((ModelListener)Class.forName(
							listenerClassName).newInstance());
				}

				_listeners = listeners.toArray(new ModelListener[listeners.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	private static Log _log = LogFactory.getLog(IPInfoPersistenceImpl.class);
	private ModelListener[] _listeners;
}