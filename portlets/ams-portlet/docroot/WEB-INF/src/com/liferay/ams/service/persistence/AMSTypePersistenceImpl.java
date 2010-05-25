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

import com.liferay.ams.NoSuchTypeException;
import com.liferay.ams.model.AMSType;
import com.liferay.ams.model.impl.AMSTypeImpl;
import com.liferay.ams.model.impl.AMSTypeModelImpl;

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
 * <a href="AMSTypePersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AMSTypePersistence
 * @see       AMSTypeUtil
 * @generated
 */
public class AMSTypePersistenceImpl extends BasePersistenceImpl<AMSType>
	implements AMSTypePersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = AMSTypeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(AMSTypeModelImpl.ENTITY_CACHE_ENABLED,
			AMSTypeModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AMSTypeModelImpl.ENTITY_CACHE_ENABLED,
			AMSTypeModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(AMSType amsType) {
		EntityCacheUtil.putResult(AMSTypeModelImpl.ENTITY_CACHE_ENABLED,
			AMSTypeImpl.class, amsType.getPrimaryKey(), amsType);
	}

	public void cacheResult(List<AMSType> amsTypes) {
		for (AMSType amsType : amsTypes) {
			if (EntityCacheUtil.getResult(
						AMSTypeModelImpl.ENTITY_CACHE_ENABLED,
						AMSTypeImpl.class, amsType.getPrimaryKey(), this) == null) {
				cacheResult(amsType);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(AMSTypeImpl.class.getName());
		EntityCacheUtil.clearCache(AMSTypeImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public void clearCache(AMSType amsType) {
		EntityCacheUtil.removeResult(AMSTypeModelImpl.ENTITY_CACHE_ENABLED,
			AMSTypeImpl.class, amsType.getPrimaryKey());
	}

	public AMSType create(long amsTypeId) {
		AMSType amsType = new AMSTypeImpl();

		amsType.setNew(true);
		amsType.setPrimaryKey(amsTypeId);

		return amsType;
	}

	public AMSType remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public AMSType remove(long amsTypeId)
		throws NoSuchTypeException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AMSType amsType = (AMSType)session.get(AMSTypeImpl.class,
					new Long(amsTypeId));

			if (amsType == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + amsTypeId);
				}

				throw new NoSuchTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					amsTypeId);
			}

			return remove(amsType);
		}
		catch (NoSuchTypeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public AMSType remove(AMSType amsType) throws SystemException {
		for (ModelListener<AMSType> listener : listeners) {
			listener.onBeforeRemove(amsType);
		}

		amsType = removeImpl(amsType);

		for (ModelListener<AMSType> listener : listeners) {
			listener.onAfterRemove(amsType);
		}

		return amsType;
	}

	protected AMSType removeImpl(AMSType amsType) throws SystemException {
		amsType = toUnwrappedModel(amsType);

		Session session = null;

		try {
			session = openSession();

			if (amsType.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(AMSTypeImpl.class,
						amsType.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(amsType);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(AMSTypeModelImpl.ENTITY_CACHE_ENABLED,
			AMSTypeImpl.class, amsType.getPrimaryKey());

		return amsType;
	}

	public AMSType updateImpl(com.liferay.ams.model.AMSType amsType,
		boolean merge) throws SystemException {
		amsType = toUnwrappedModel(amsType);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, amsType, merge);

			amsType.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(AMSTypeModelImpl.ENTITY_CACHE_ENABLED,
			AMSTypeImpl.class, amsType.getPrimaryKey(), amsType);

		return amsType;
	}

	protected AMSType toUnwrappedModel(AMSType amsType) {
		if (amsType instanceof AMSTypeImpl) {
			return amsType;
		}

		AMSTypeImpl amsTypeImpl = new AMSTypeImpl();

		amsTypeImpl.setNew(amsType.isNew());
		amsTypeImpl.setPrimaryKey(amsType.getPrimaryKey());

		amsTypeImpl.setAmsTypeId(amsType.getAmsTypeId());
		amsTypeImpl.setGroupId(amsType.getGroupId());
		amsTypeImpl.setName(amsType.getName());

		return amsTypeImpl;
	}

	public AMSType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public AMSType findByPrimaryKey(long amsTypeId)
		throws NoSuchTypeException, SystemException {
		AMSType amsType = fetchByPrimaryKey(amsTypeId);

		if (amsType == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + amsTypeId);
			}

			throw new NoSuchTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				amsTypeId);
		}

		return amsType;
	}

	public AMSType fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public AMSType fetchByPrimaryKey(long amsTypeId) throws SystemException {
		AMSType amsType = (AMSType)EntityCacheUtil.getResult(AMSTypeModelImpl.ENTITY_CACHE_ENABLED,
				AMSTypeImpl.class, amsTypeId, this);

		if (amsType == null) {
			Session session = null;

			try {
				session = openSession();

				amsType = (AMSType)session.get(AMSTypeImpl.class,
						new Long(amsTypeId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (amsType != null) {
					cacheResult(amsType);
				}

				closeSession(session);
			}
		}

		return amsType;
	}

	public List<AMSType> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<AMSType> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	public List<AMSType> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<AMSType> list = (List<AMSType>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_AMSTYPE);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}

				else {
					sql = _SQL_SELECT_AMSTYPE.concat(AMSTypeModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AMSType>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AMSType>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<AMSType>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeAll() throws SystemException {
		for (AMSType amsType : findAll()) {
			remove(amsType);
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

				Query q = session.createQuery(_SQL_COUNT_AMSTYPE);

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
						"value.object.listener.com.liferay.ams.model.AMSType")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AMSType>> listenersList = new ArrayList<ModelListener<AMSType>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<AMSType>)Class.forName(
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
	private static final String _SQL_SELECT_AMSTYPE = "SELECT amsType FROM AMSType amsType";
	private static final String _SQL_COUNT_AMSTYPE = "SELECT COUNT(amsType) FROM AMSType amsType";
	private static final String _ORDER_BY_ENTITY_ALIAS = "amsType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AMSType exists with the primary key ";
	private static Log _log = LogFactoryUtil.getLog(AMSTypePersistenceImpl.class);
}