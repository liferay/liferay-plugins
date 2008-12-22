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

package com.liferay.ams.service.persistence;

import com.liferay.ams.NoSuchAssetCheckoutException;
import com.liferay.ams.model.AssetCheckout;
import com.liferay.ams.model.impl.AssetCheckoutImpl;
import com.liferay.ams.model.impl.AssetCheckoutModelImpl;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="AssetCheckoutPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class AssetCheckoutPersistenceImpl extends BasePersistenceImpl
	implements AssetCheckoutPersistence {
	public AssetCheckout create(long assetCheckoutId) {
		AssetCheckout assetCheckout = new AssetCheckoutImpl();

		assetCheckout.setNew(true);
		assetCheckout.setPrimaryKey(assetCheckoutId);

		return assetCheckout;
	}

	public AssetCheckout remove(long assetCheckoutId)
		throws NoSuchAssetCheckoutException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AssetCheckout assetCheckout = (AssetCheckout)session.get(AssetCheckoutImpl.class,
					new Long(assetCheckoutId));

			if (assetCheckout == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No AssetCheckout exists with the primary key " +
						assetCheckoutId);
				}

				throw new NoSuchAssetCheckoutException(
					"No AssetCheckout exists with the primary key " +
					assetCheckoutId);
			}

			return remove(assetCheckout);
		}
		catch (NoSuchAssetCheckoutException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public AssetCheckout remove(AssetCheckout assetCheckout)
		throws SystemException {
		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onBeforeRemove(assetCheckout);
			}
		}

		assetCheckout = removeImpl(assetCheckout);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				listener.onAfterRemove(assetCheckout);
			}
		}

		return assetCheckout;
	}

	protected AssetCheckout removeImpl(AssetCheckout assetCheckout)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(AssetCheckoutImpl.class,
						assetCheckout.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(assetCheckout);

			session.flush();

			return assetCheckout;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(AssetCheckout.class.getName());
		}
	}

	public AssetCheckout update(AssetCheckout assetCheckout)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(AssetCheckout assetCheckout) method. Use update(AssetCheckout assetCheckout, boolean merge) instead.");
		}

		return update(assetCheckout, false);
	}

	public AssetCheckout update(AssetCheckout assetCheckout, boolean merge)
		throws SystemException {
		boolean isNew = assetCheckout.isNew();

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onBeforeCreate(assetCheckout);
				}
				else {
					listener.onBeforeUpdate(assetCheckout);
				}
			}
		}

		assetCheckout = updateImpl(assetCheckout, merge);

		if (_listeners.length > 0) {
			for (ModelListener listener : _listeners) {
				if (isNew) {
					listener.onAfterCreate(assetCheckout);
				}
				else {
					listener.onAfterUpdate(assetCheckout);
				}
			}
		}

		return assetCheckout;
	}

	public AssetCheckout updateImpl(
		com.liferay.ams.model.AssetCheckout assetCheckout, boolean merge)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, assetCheckout, merge);

			assetCheckout.setNew(false);

			return assetCheckout;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(AssetCheckout.class.getName());
		}
	}

	public AssetCheckout findByPrimaryKey(long assetCheckoutId)
		throws NoSuchAssetCheckoutException, SystemException {
		AssetCheckout assetCheckout = fetchByPrimaryKey(assetCheckoutId);

		if (assetCheckout == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No AssetCheckout exists with the primary key " +
					assetCheckoutId);
			}

			throw new NoSuchAssetCheckoutException(
				"No AssetCheckout exists with the primary key " +
				assetCheckoutId);
		}

		return assetCheckout;
	}

	public AssetCheckout fetchByPrimaryKey(long assetCheckoutId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (AssetCheckout)session.get(AssetCheckoutImpl.class,
				new Long(assetCheckoutId));
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

	public List<AssetCheckout> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<AssetCheckout> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<AssetCheckout> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = AssetCheckoutModelImpl.CACHE_ENABLED;
		String finderClassName = AssetCheckout.class.getName();
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

				query.append("FROM com.liferay.ams.model.AssetCheckout ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				List<AssetCheckout> list = null;

				if (obc == null) {
					list = (List<AssetCheckout>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AssetCheckout>)QueryUtil.list(q, getDialect(),
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
			return (List<AssetCheckout>)result;
		}
	}

	public void removeAll() throws SystemException {
		for (AssetCheckout assetCheckout : findAll()) {
			remove(assetCheckout);
		}
	}

	public int countAll() throws SystemException {
		boolean finderClassNameCacheEnabled = AssetCheckoutModelImpl.CACHE_ENABLED;
		String finderClassName = AssetCheckout.class.getName();
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
						"SELECT COUNT(*) FROM com.liferay.ams.model.AssetCheckout");

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

	public void registerListener(ModelListener listener) {
		List<ModelListener> listeners = ListUtil.fromArray(_listeners);

		listeners.add(listener);

		_listeners = listeners.toArray(new ModelListener[listeners.size()]);
	}

	public void unregisterListener(ModelListener listener) {
		List<ModelListener> listeners = ListUtil.fromArray(_listeners);

		listeners.remove(listener);

		_listeners = listeners.toArray(new ModelListener[listeners.size()]);
	}

	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.ams.model.AssetCheckout")));

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

	private static Log _log = LogFactory.getLog(AssetCheckoutPersistenceImpl.class);
	private ModelListener[] _listeners = new ModelListener[0];
}