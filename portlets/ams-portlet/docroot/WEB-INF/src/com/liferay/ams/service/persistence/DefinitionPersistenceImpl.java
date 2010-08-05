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
import com.liferay.ams.model.Definition;
import com.liferay.ams.model.impl.DefinitionImpl;
import com.liferay.ams.model.impl.DefinitionModelImpl;

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
 * The persistence implementation for the definition service.
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link DefinitionUtil} to access the definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DefinitionPersistence
 * @see DefinitionUtil
 * @generated
 */
public class DefinitionPersistenceImpl extends BasePersistenceImpl<Definition>
	implements DefinitionPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = DefinitionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(DefinitionModelImpl.ENTITY_CACHE_ENABLED,
			DefinitionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DefinitionModelImpl.ENTITY_CACHE_ENABLED,
			DefinitionModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	/**
	 * Caches the definition in the entity cache if it is enabled.
	 *
	 * @param definition the definition to cache
	 */
	public void cacheResult(Definition definition) {
		EntityCacheUtil.putResult(DefinitionModelImpl.ENTITY_CACHE_ENABLED,
			DefinitionImpl.class, definition.getPrimaryKey(), definition);
	}

	/**
	 * Caches the definitions in the entity cache if it is enabled.
	 *
	 * @param definitions the definitions to cache
	 */
	public void cacheResult(List<Definition> definitions) {
		for (Definition definition : definitions) {
			if (EntityCacheUtil.getResult(
						DefinitionModelImpl.ENTITY_CACHE_ENABLED,
						DefinitionImpl.class, definition.getPrimaryKey(), this) == null) {
				cacheResult(definition);
			}
		}
	}

	/**
	 * Clears the cache for all definitions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(DefinitionImpl.class.getName());
		EntityCacheUtil.clearCache(DefinitionImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the definition.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(Definition definition) {
		EntityCacheUtil.removeResult(DefinitionModelImpl.ENTITY_CACHE_ENABLED,
			DefinitionImpl.class, definition.getPrimaryKey());
	}

	/**
	 * Creates a new definition with the primary key. Does not add the definition to the database.
	 *
	 * @param definitionId the primary key for the new definition
	 * @return the new definition
	 */
	public Definition create(long definitionId) {
		Definition definition = new DefinitionImpl();

		definition.setNew(true);
		definition.setPrimaryKey(definitionId);

		return definition;
	}

	/**
	 * Removes the definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the definition to remove
	 * @return the definition that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Definition remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param definitionId the primary key of the definition to remove
	 * @return the definition that was removed
	 * @throws com.liferay.ams.NoSuchDefinitionException if a definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Definition remove(long definitionId)
		throws NoSuchDefinitionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Definition definition = (Definition)session.get(DefinitionImpl.class,
					new Long(definitionId));

			if (definition == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + definitionId);
				}

				throw new NoSuchDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					definitionId);
			}

			return remove(definition);
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

	protected Definition removeImpl(Definition definition)
		throws SystemException {
		definition = toUnwrappedModel(definition);

		Session session = null;

		try {
			session = openSession();

			if (definition.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(DefinitionImpl.class,
						definition.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(definition);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(DefinitionModelImpl.ENTITY_CACHE_ENABLED,
			DefinitionImpl.class, definition.getPrimaryKey());

		return definition;
	}

	public Definition updateImpl(com.liferay.ams.model.Definition definition,
		boolean merge) throws SystemException {
		definition = toUnwrappedModel(definition);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, definition, merge);

			definition.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(DefinitionModelImpl.ENTITY_CACHE_ENABLED,
			DefinitionImpl.class, definition.getPrimaryKey(), definition);

		return definition;
	}

	protected Definition toUnwrappedModel(Definition definition) {
		if (definition instanceof DefinitionImpl) {
			return definition;
		}

		DefinitionImpl definitionImpl = new DefinitionImpl();

		definitionImpl.setNew(definition.isNew());
		definitionImpl.setPrimaryKey(definition.getPrimaryKey());

		definitionImpl.setDefinitionId(definition.getDefinitionId());
		definitionImpl.setGroupId(definition.getGroupId());
		definitionImpl.setCompanyId(definition.getCompanyId());
		definitionImpl.setUserId(definition.getUserId());
		definitionImpl.setUserName(definition.getUserName());
		definitionImpl.setCreateDate(definition.getCreateDate());
		definitionImpl.setModifiedDate(definition.getModifiedDate());
		definitionImpl.setTypeId(definition.getTypeId());
		definitionImpl.setManufacturer(definition.getManufacturer());
		definitionImpl.setModel(definition.getModel());
		definitionImpl.setOrderDate(definition.getOrderDate());
		definitionImpl.setQuantity(definition.getQuantity());
		definitionImpl.setPrice(definition.getPrice());

		return definitionImpl;
	}

	/**
	 * Finds the definition with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the definition to find
	 * @return the definition
	 * @throws com.liferay.portal.NoSuchModelException if a definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Definition findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the definition with the primary key or throws a {@link com.liferay.ams.NoSuchDefinitionException} if it could not be found.
	 *
	 * @param definitionId the primary key of the definition to find
	 * @return the definition
	 * @throws com.liferay.ams.NoSuchDefinitionException if a definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Definition findByPrimaryKey(long definitionId)
		throws NoSuchDefinitionException, SystemException {
		Definition definition = fetchByPrimaryKey(definitionId);

		if (definition == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + definitionId);
			}

			throw new NoSuchDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				definitionId);
		}

		return definition;
	}

	/**
	 * Finds the definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the definition to find
	 * @return the definition, or <code>null</code> if a definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Definition fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param definitionId the primary key of the definition to find
	 * @return the definition, or <code>null</code> if a definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Definition fetchByPrimaryKey(long definitionId)
		throws SystemException {
		Definition definition = (Definition)EntityCacheUtil.getResult(DefinitionModelImpl.ENTITY_CACHE_ENABLED,
				DefinitionImpl.class, definitionId, this);

		if (definition == null) {
			Session session = null;

			try {
				session = openSession();

				definition = (Definition)session.get(DefinitionImpl.class,
						new Long(definitionId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (definition != null) {
					cacheResult(definition);
				}

				closeSession(session);
			}
		}

		return definition;
	}

	/**
	 * Finds all the definitions.
	 *
	 * @return the definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<Definition> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of definitions to return
	 * @param end the upper bound of the range of definitions to return (not inclusive)
	 * @return the range of definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<Definition> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of definitions to return
	 * @param end the upper bound of the range of definitions to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<Definition> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Definition> list = (List<Definition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
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

					query.append(_SQL_SELECT_DEFINITION);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
						orderByComparator);

					sql = query.toString();
				}
				else {
					sql = _SQL_SELECT_DEFINITION;
				}

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Definition>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Definition>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Definition>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the definitions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Definition definition : findAll()) {
			remove(definition);
		}
	}

	/**
	 * Counts all the definitions.
	 *
	 * @return the number of definitions
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DEFINITION);

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

	/**
	 * Initializes the definition persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.ams.model.Definition")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Definition>> listenersList = new ArrayList<ModelListener<Definition>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Definition>)InstanceFactory.newInstance(
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
	private static final String _SQL_SELECT_DEFINITION = "SELECT definition FROM Definition definition";
	private static final String _SQL_COUNT_DEFINITION = "SELECT COUNT(definition) FROM Definition definition";
	private static final String _ORDER_BY_ENTITY_ALIAS = "definition.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Definition exists with the primary key ";
	private static Log _log = LogFactoryUtil.getLog(DefinitionPersistenceImpl.class);
}