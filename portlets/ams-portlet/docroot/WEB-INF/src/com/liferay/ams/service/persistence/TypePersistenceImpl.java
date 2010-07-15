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
import com.liferay.ams.model.Type;
import com.liferay.ams.model.impl.TypeImpl;
import com.liferay.ams.model.impl.TypeModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
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
import com.liferay.portal.kernel.util.InstanceFactory;
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
 * @author    Brian Wing Shun Chan
 * @see       TypePersistence
 * @see       TypeUtil
 * @generated
 */
public class TypePersistenceImpl extends BasePersistenceImpl<Type>
	implements TypePersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = TypeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(TypeModelImpl.ENTITY_CACHE_ENABLED,
			TypeModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TypeModelImpl.ENTITY_CACHE_ENABLED,
			TypeModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(Type type) {
		EntityCacheUtil.putResult(TypeModelImpl.ENTITY_CACHE_ENABLED,
			TypeImpl.class, type.getPrimaryKey(), type);
	}

	public void cacheResult(List<Type> types) {
		for (Type type : types) {
			if (EntityCacheUtil.getResult(TypeModelImpl.ENTITY_CACHE_ENABLED,
						TypeImpl.class, type.getPrimaryKey(), this) == null) {
				cacheResult(type);
			}
		}
	}

	public void clearCache() {
		CacheRegistryUtil.clear(TypeImpl.class.getName());
		EntityCacheUtil.clearCache(TypeImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public void clearCache(Type type) {
		EntityCacheUtil.removeResult(TypeModelImpl.ENTITY_CACHE_ENABLED,
			TypeImpl.class, type.getPrimaryKey());
	}

	public Type create(long typeId) {
		Type type = new TypeImpl();

		type.setNew(true);
		type.setPrimaryKey(typeId);

		return type;
	}

	public Type remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public Type remove(long typeId) throws NoSuchTypeException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Type type = (Type)session.get(TypeImpl.class, new Long(typeId));

			if (type == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + typeId);
				}

				throw new NoSuchTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					typeId);
			}

			return remove(type);
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

	protected Type removeImpl(Type type) throws SystemException {
		type = toUnwrappedModel(type);

		Session session = null;

		try {
			session = openSession();

			if (type.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(TypeImpl.class,
						type.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(type);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(TypeModelImpl.ENTITY_CACHE_ENABLED,
			TypeImpl.class, type.getPrimaryKey());

		return type;
	}

	public Type updateImpl(com.liferay.ams.model.Type type, boolean merge)
		throws SystemException {
		type = toUnwrappedModel(type);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, type, merge);

			type.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(TypeModelImpl.ENTITY_CACHE_ENABLED,
			TypeImpl.class, type.getPrimaryKey(), type);

		return type;
	}

	protected Type toUnwrappedModel(Type type) {
		if (type instanceof TypeImpl) {
			return type;
		}

		TypeImpl typeImpl = new TypeImpl();

		typeImpl.setNew(type.isNew());
		typeImpl.setPrimaryKey(type.getPrimaryKey());

		typeImpl.setTypeId(type.getTypeId());
		typeImpl.setGroupId(type.getGroupId());
		typeImpl.setName(type.getName());

		return typeImpl;
	}

	public Type findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public Type findByPrimaryKey(long typeId)
		throws NoSuchTypeException, SystemException {
		Type type = fetchByPrimaryKey(typeId);

		if (type == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + typeId);
			}

			throw new NoSuchTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				typeId);
		}

		return type;
	}

	public Type fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public Type fetchByPrimaryKey(long typeId) throws SystemException {
		Type type = (Type)EntityCacheUtil.getResult(TypeModelImpl.ENTITY_CACHE_ENABLED,
				TypeImpl.class, typeId, this);

		if (type == null) {
			Session session = null;

			try {
				session = openSession();

				type = (Type)session.get(TypeImpl.class, new Long(typeId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (type != null) {
					cacheResult(type);
				}

				closeSession(session);
			}
		}

		return type;
	}

	public List<Type> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<Type> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	public List<Type> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Type> list = (List<Type>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_TYPE);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_TYPE.concat(TypeModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Type>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Type>)QueryUtil.list(q, getDialect(), start,
							end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Type>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeAll() throws SystemException {
		for (Type type : findAll()) {
			remove(type);
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

				Query q = session.createQuery(_SQL_COUNT_TYPE);

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
						"value.object.listener.com.liferay.ams.model.Type")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Type>> listenersList = new ArrayList<ModelListener<Type>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Type>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(type = AssetPersistence.class)
	protected AssetPersistence assetPersistence;
	@BeanReference(type = CheckoutPersistence.class)
	protected CheckoutPersistence checkoutPersistence;
	@BeanReference(type = DefinitionPersistence.class)
	protected DefinitionPersistence definitionPersistence;
	@BeanReference(type = TypePersistence.class)
	protected TypePersistence typePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_TYPE = "SELECT type FROM Type type";
	private static final String _SQL_COUNT_TYPE = "SELECT COUNT(type) FROM Type type";
	private static final String _ORDER_BY_ENTITY_ALIAS = "type.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Type exists with the primary key ";
	private static Log _log = LogFactoryUtil.getLog(TypePersistenceImpl.class);
}