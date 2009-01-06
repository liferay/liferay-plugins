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

import com.liferay.ams.NoSuchAssetDefinitionException;
import com.liferay.ams.model.AssetDefinition;
import com.liferay.ams.model.impl.AssetDefinitionImpl;
import com.liferay.ams.model.impl.AssetDefinitionModelImpl;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.util.GetterUtil;
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
 * <a href="AssetDefinitionPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class AssetDefinitionPersistenceImpl extends BasePersistenceImpl
	implements AssetDefinitionPersistence {
	public AssetDefinition create(long assetDefinitionId) {
		AssetDefinition assetDefinition = new AssetDefinitionImpl();

		assetDefinition.setNew(true);
		assetDefinition.setPrimaryKey(assetDefinitionId);

		return assetDefinition;
	}

	public AssetDefinition remove(long assetDefinitionId)
		throws NoSuchAssetDefinitionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AssetDefinition assetDefinition = (AssetDefinition)session.get(AssetDefinitionImpl.class,
					new Long(assetDefinitionId));

			if (assetDefinition == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No AssetDefinition exists with the primary key " +
						assetDefinitionId);
				}

				throw new NoSuchAssetDefinitionException(
					"No AssetDefinition exists with the primary key " +
					assetDefinitionId);
			}

			return remove(assetDefinition);
		}
		catch (NoSuchAssetDefinitionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public AssetDefinition remove(AssetDefinition assetDefinition)
		throws SystemException {
		for (ModelListener listener : listeners) {
			listener.onBeforeRemove(assetDefinition);
		}

		assetDefinition = removeImpl(assetDefinition);

		for (ModelListener listener : listeners) {
			listener.onAfterRemove(assetDefinition);
		}

		return assetDefinition;
	}

	protected AssetDefinition removeImpl(AssetDefinition assetDefinition)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(AssetDefinitionImpl.class,
						assetDefinition.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(assetDefinition);

			session.flush();

			return assetDefinition;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(AssetDefinition.class.getName());
		}
	}

	public AssetDefinition update(AssetDefinition assetDefinition)
		throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(AssetDefinition assetDefinition) method. Use update(AssetDefinition assetDefinition, boolean merge) instead.");
		}

		return update(assetDefinition, false);
	}

	public AssetDefinition update(AssetDefinition assetDefinition, boolean merge)
		throws SystemException {
		boolean isNew = assetDefinition.isNew();

		for (ModelListener listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(assetDefinition);
			}
			else {
				listener.onBeforeUpdate(assetDefinition);
			}
		}

		assetDefinition = updateImpl(assetDefinition, merge);

		for (ModelListener listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(assetDefinition);
			}
			else {
				listener.onAfterUpdate(assetDefinition);
			}
		}

		return assetDefinition;
	}

	public AssetDefinition updateImpl(
		com.liferay.ams.model.AssetDefinition assetDefinition, boolean merge)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, assetDefinition, merge);

			assetDefinition.setNew(false);

			return assetDefinition;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);

			FinderCacheUtil.clearCache(AssetDefinition.class.getName());
		}
	}

	public AssetDefinition findByPrimaryKey(long assetDefinitionId)
		throws NoSuchAssetDefinitionException, SystemException {
		AssetDefinition assetDefinition = fetchByPrimaryKey(assetDefinitionId);

		if (assetDefinition == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No AssetDefinition exists with the primary key " +
					assetDefinitionId);
			}

			throw new NoSuchAssetDefinitionException(
				"No AssetDefinition exists with the primary key " +
				assetDefinitionId);
		}

		return assetDefinition;
	}

	public AssetDefinition fetchByPrimaryKey(long assetDefinitionId)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			return (AssetDefinition)session.get(AssetDefinitionImpl.class,
				new Long(assetDefinitionId));
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

	public List<AssetDefinition> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<AssetDefinition> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<AssetDefinition> findAll(int start, int end,
		OrderByComparator obc) throws SystemException {
		boolean finderClassNameCacheEnabled = AssetDefinitionModelImpl.CACHE_ENABLED;
		String finderClassName = AssetDefinition.class.getName();
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

				query.append("FROM com.liferay.ams.model.AssetDefinition ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				List<AssetDefinition> list = null;

				if (obc == null) {
					list = (List<AssetDefinition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AssetDefinition>)QueryUtil.list(q,
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
			return (List<AssetDefinition>)result;
		}
	}

	public void removeAll() throws SystemException {
		for (AssetDefinition assetDefinition : findAll()) {
			remove(assetDefinition);
		}
	}

	public int countAll() throws SystemException {
		boolean finderClassNameCacheEnabled = AssetDefinitionModelImpl.CACHE_ENABLED;
		String finderClassName = AssetDefinition.class.getName();
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
						"SELECT COUNT(*) FROM com.liferay.ams.model.AssetDefinition");

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
						"value.object.listener.com.liferay.ams.model.AssetDefinition")));

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
	private static Log _log = LogFactory.getLog(AssetDefinitionPersistenceImpl.class);
}