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

package com.liferay.ams.service.persistence;

import com.liferay.ams.NoSuchAssetEntryException;
import com.liferay.ams.model.AssetEntry;
import com.liferay.ams.model.impl.AssetEntryImpl;
import com.liferay.ams.model.impl.AssetEntryModelImpl;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="AssetEntryPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class AssetEntryPersistenceImpl extends BasePersistenceImpl
	implements AssetEntryPersistence {
	public AssetEntry create(long assetEntryId) {
		AssetEntry assetEntry = new AssetEntryImpl();

		assetEntry.setNew(true);
		assetEntry.setPrimaryKey(assetEntryId);

		return assetEntry;
	}

	public AssetEntry remove(long assetEntryId)
		throws NoSuchAssetEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AssetEntry assetEntry = (AssetEntry)session.get(AssetEntryImpl.class,
					new Long(assetEntryId));

			if (assetEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No AssetEntry exists with the primary key " +
						assetEntryId);
				}

				throw new NoSuchAssetEntryException(
					"No AssetEntry exists with the primary key " +
					assetEntryId);
			}

			return remove(assetEntry);
		}
		catch (NoSuchAssetEntryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public AssetEntry remove(AssetEntry assetEntry) throws SystemException {
		for (ModelListener listener : listeners) {
			listener.onBeforeRemove(assetEntry);
		}

		assetEntry = removeImpl(assetEntry);

		for (ModelListener listener : listeners) {
			listener.onAfterRemove(assetEntry);
		}

		return assetEntry;
	}

	protected AssetEntry removeImpl(AssetEntry assetEntry)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(AssetEntryImpl.class,
						assetEntry.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(assetEntry);

			session.flush();

			return assetEntry;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(AssetEntry.class.getName());
		}
	}

	public AssetEntry update(AssetEntry assetEntry) throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(AssetEntry assetEntry) method. Use update(AssetEntry assetEntry, boolean merge) instead.");
		}

		return update(assetEntry, false);
	}

	public AssetEntry update(AssetEntry assetEntry, boolean merge)
		throws SystemException {
		boolean isNew = assetEntry.isNew();

		for (ModelListener listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(assetEntry);
			}
			else {
				listener.onBeforeUpdate(assetEntry);
			}
		}

		assetEntry = updateImpl(assetEntry, merge);

		for (ModelListener listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(assetEntry);
			}
			else {
				listener.onAfterUpdate(assetEntry);
			}
		}

		return assetEntry;
	}

	public AssetEntry updateImpl(com.liferay.ams.model.AssetEntry assetEntry,
		boolean merge) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, assetEntry, merge);

			assetEntry.setNew(false);

			return assetEntry;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(AssetEntry.class.getName());
		}
	}

	public AssetEntry findByPrimaryKey(long assetEntryId)
		throws NoSuchAssetEntryException, SystemException {
		AssetEntry assetEntry = fetchByPrimaryKey(assetEntryId);

		if (assetEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No AssetEntry exists with the primary key " +
					assetEntryId);
			}

			throw new NoSuchAssetEntryException(
				"No AssetEntry exists with the primary key " + assetEntryId);
		}

		return assetEntry;
	}

	public AssetEntry fetchByPrimaryKey(long assetEntryId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (AssetEntry)session.get(AssetEntryImpl.class,
				new Long(assetEntryId));
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

	public List<AssetEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<AssetEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<AssetEntry> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		boolean finderClassNameCacheEnabled = AssetEntryModelImpl.CACHE_ENABLED;
		String finderClassName = AssetEntry.class.getName();
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

				query.append("FROM com.liferay.ams.model.AssetEntry ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				List<AssetEntry> list = null;

				if (obc == null) {
					list = (List<AssetEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AssetEntry>)QueryUtil.list(q, getDialect(),
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
			return (List<AssetEntry>)result;
		}
	}

	public void removeAll() throws SystemException {
		for (AssetEntry assetEntry : findAll()) {
			remove(assetEntry);
		}
	}

	public int countAll() throws SystemException {
		boolean finderClassNameCacheEnabled = AssetEntryModelImpl.CACHE_ENABLED;
		String finderClassName = AssetEntry.class.getName();
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
						"SELECT COUNT(*) FROM com.liferay.ams.model.AssetEntry");

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
						"value.object.listener.com.liferay.ams.model.AssetEntry")));

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

	@BeanReference(name = "com.liferay.ams.service.persistence.AssetCheckoutPersistence.impl")
	protected com.liferay.ams.service.persistence.AssetCheckoutPersistence assetCheckoutPersistence;
	@BeanReference(name = "com.liferay.ams.service.persistence.AssetDefinitionPersistence.impl")
	protected com.liferay.ams.service.persistence.AssetDefinitionPersistence assetDefinitionPersistence;
	@BeanReference(name = "com.liferay.ams.service.persistence.AssetEntryPersistence.impl")
	protected com.liferay.ams.service.persistence.AssetEntryPersistence assetEntryPersistence;
	@BeanReference(name = "com.liferay.ams.service.persistence.AssetTypePersistence.impl")
	protected com.liferay.ams.service.persistence.AssetTypePersistence assetTypePersistence;
	private static Log _log = LogFactoryUtil.getLog(AssetEntryPersistenceImpl.class);
}