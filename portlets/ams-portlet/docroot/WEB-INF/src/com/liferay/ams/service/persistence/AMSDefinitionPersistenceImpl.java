/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.ams.service.persistence;

import com.liferay.ams.NoSuchDefinitionException;
import com.liferay.ams.model.AMSDefinition;
import com.liferay.ams.model.impl.AMSDefinitionImpl;
import com.liferay.ams.model.impl.AMSDefinitionModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistry;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="AMSDefinitionPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AMSDefinitionPersistence
 * @see       AMSDefinitionUtil
 * @generated
 */
public class AMSDefinitionPersistenceImpl extends BasePersistenceImpl<AMSDefinition>
	implements AMSDefinitionPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = AMSDefinitionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(AMSDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			AMSDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AMSDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			AMSDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	public void cacheResult(AMSDefinition amsDefinition) {
		EntityCacheUtil.putResult(AMSDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			AMSDefinitionImpl.class, amsDefinition.getPrimaryKey(),
			amsDefinition);
	}

	public void cacheResult(List<AMSDefinition> amsDefinitions) {
		for (AMSDefinition amsDefinition : amsDefinitions) {
			if (EntityCacheUtil.getResult(
						AMSDefinitionModelImpl.ENTITY_CACHE_ENABLED,
						AMSDefinitionImpl.class, amsDefinition.getPrimaryKey(),
						this) == null) {
				cacheResult(amsDefinition);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(AMSDefinitionImpl.class.getName());
		EntityCacheUtil.clearCache(AMSDefinitionImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public void clearCache(AMSDefinition amsDefinition) {
		EntityCacheUtil.removeResult(AMSDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			AMSDefinitionImpl.class, amsDefinition.getPrimaryKey());
	}

	public AMSDefinition create(long assetDefinitionId) {
		AMSDefinition amsDefinition = new AMSDefinitionImpl();

		amsDefinition.setNew(true);
		amsDefinition.setPrimaryKey(assetDefinitionId);

		return amsDefinition;
	}

	public AMSDefinition remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public AMSDefinition remove(long assetDefinitionId)
		throws NoSuchDefinitionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AMSDefinition amsDefinition = (AMSDefinition)session.get(AMSDefinitionImpl.class,
					new Long(assetDefinitionId));

			if (amsDefinition == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						assetDefinitionId);
				}

				throw new NoSuchDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					assetDefinitionId);
			}

			return remove(amsDefinition);
		}
		catch (NoSuchDefinitionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public AMSDefinition remove(AMSDefinition amsDefinition)
		throws SystemException {
		for (ModelListener<AMSDefinition> listener : listeners) {
			listener.onBeforeRemove(amsDefinition);
		}

		amsDefinition = removeImpl(amsDefinition);

		for (ModelListener<AMSDefinition> listener : listeners) {
			listener.onAfterRemove(amsDefinition);
		}

		return amsDefinition;
	}

	protected AMSDefinition removeImpl(AMSDefinition amsDefinition)
		throws SystemException {
		amsDefinition = toUnwrappedModel(amsDefinition);

		Session session = null;

		try {
			session = openSession();

			if (amsDefinition.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(AMSDefinitionImpl.class,
						amsDefinition.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(amsDefinition);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(AMSDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			AMSDefinitionImpl.class, amsDefinition.getPrimaryKey());

		return amsDefinition;
	}

	public AMSDefinition updateImpl(
		com.liferay.ams.model.AMSDefinition amsDefinition, boolean merge)
		throws SystemException {
		amsDefinition = toUnwrappedModel(amsDefinition);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, amsDefinition, merge);

			amsDefinition.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(AMSDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			AMSDefinitionImpl.class, amsDefinition.getPrimaryKey(),
			amsDefinition);

		return amsDefinition;
	}

	protected AMSDefinition toUnwrappedModel(AMSDefinition amsDefinition) {
		if (amsDefinition instanceof AMSDefinitionImpl) {
			return amsDefinition;
		}

		AMSDefinitionImpl amsDefinitionImpl = new AMSDefinitionImpl();

		amsDefinitionImpl.setNew(amsDefinition.isNew());
		amsDefinitionImpl.setPrimaryKey(amsDefinition.getPrimaryKey());

		amsDefinitionImpl.setAssetDefinitionId(amsDefinition.getAssetDefinitionId());
		amsDefinitionImpl.setGroupId(amsDefinition.getGroupId());
		amsDefinitionImpl.setCompanyId(amsDefinition.getCompanyId());
		amsDefinitionImpl.setUserId(amsDefinition.getUserId());
		amsDefinitionImpl.setUserName(amsDefinition.getUserName());
		amsDefinitionImpl.setCreateDate(amsDefinition.getCreateDate());
		amsDefinitionImpl.setModifiedDate(amsDefinition.getModifiedDate());
		amsDefinitionImpl.setAmsTypeId(amsDefinition.getAmsTypeId());
		amsDefinitionImpl.setManufacturer(amsDefinition.getManufacturer());
		amsDefinitionImpl.setModel(amsDefinition.getModel());
		amsDefinitionImpl.setOrderDate(amsDefinition.getOrderDate());
		amsDefinitionImpl.setQuantity(amsDefinition.getQuantity());
		amsDefinitionImpl.setPrice(amsDefinition.getPrice());

		return amsDefinitionImpl;
	}

	public AMSDefinition findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public AMSDefinition findByPrimaryKey(long assetDefinitionId)
		throws NoSuchDefinitionException, SystemException {
		AMSDefinition amsDefinition = fetchByPrimaryKey(assetDefinitionId);

		if (amsDefinition == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + assetDefinitionId);
			}

			throw new NoSuchDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				assetDefinitionId);
		}

		return amsDefinition;
	}

	public AMSDefinition fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public AMSDefinition fetchByPrimaryKey(long assetDefinitionId)
		throws SystemException {
		AMSDefinition amsDefinition = (AMSDefinition)EntityCacheUtil.getResult(AMSDefinitionModelImpl.ENTITY_CACHE_ENABLED,
				AMSDefinitionImpl.class, assetDefinitionId, this);

		if (amsDefinition == null) {
			Session session = null;

			try {
				session = openSession();

				amsDefinition = (AMSDefinition)session.get(AMSDefinitionImpl.class,
						new Long(assetDefinitionId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (amsDefinition != null) {
					cacheResult(amsDefinition);
				}

				closeSession(session);
			}
		}

		return amsDefinition;
	}

	public List<AMSDefinition> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<AMSDefinition> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<AMSDefinition> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<AMSDefinition> list = (List<AMSDefinition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;
				String sql = null;

				if (orderByComparator != null) {
					query = new StringBundler(2 +
							(orderByComparator.getOrderByFields().length * 3));

					query.append(_SQL_SELECT_AMSDEFINITION);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}

				sql = _SQL_SELECT_AMSDEFINITION;

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AMSDefinition>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AMSDefinition>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<AMSDefinition>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeAll() throws SystemException {
		for (AMSDefinition amsDefinition : findAll()) {
			remove(amsDefinition);
		}
	}

	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_AMSDEFINITION);

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
						"value.object.listener.com.liferay.ams.model.AMSDefinition")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AMSDefinition>> listenersList = new ArrayList<ModelListener<AMSDefinition>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<AMSDefinition>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(type = AMSAssetPersistence.class)
	protected AMSAssetPersistence amsAssetPersistence;
	@BeanReference(type = AMSCheckoutPersistence.class)
	protected AMSCheckoutPersistence amsCheckoutPersistence;
	@BeanReference(type = AMSDefinitionPersistence.class)
	protected AMSDefinitionPersistence amsDefinitionPersistence;
	@BeanReference(type = AMSTypePersistence.class)
	protected AMSTypePersistence amsTypePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_AMSDEFINITION = "SELECT amsDefinition FROM AMSDefinition amsDefinition";
	private static final String _SQL_COUNT_AMSDEFINITION = "SELECT COUNT(amsDefinition) FROM AMSDefinition amsDefinition";
	private static final String _ORDER_BY_ENTITY_ALIAS = "amsDefinition.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AMSDefinition exists with the primary key ";
	private static Log _log = LogFactoryUtil.getLog(AMSDefinitionPersistenceImpl.class);
}