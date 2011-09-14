/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.marketplace.service.persistence;

import com.liferay.marketplace.NoSuchModuleException;
import com.liferay.marketplace.model.Module;
import com.liferay.marketplace.model.impl.ModuleImpl;
import com.liferay.marketplace.model.impl.ModuleModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
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
 * The persistence implementation for the module service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ryan Park
 * @see ModulePersistence
 * @see ModuleUtil
 * @generated
 */
public class ModulePersistenceImpl extends BasePersistenceImpl<Module>
	implements ModulePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ModuleUtil} to access the module persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ModuleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_UUID = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_APPID = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST, "findByAppId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_APPID = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByAppId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_CONTEXTNAME = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST, "findByContextName",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_CONTEXTNAME = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByContextName",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_A_C = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByA_C",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_A_C = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByA_C",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the module in the entity cache if it is enabled.
	 *
	 * @param module the module
	 */
	public void cacheResult(Module module) {
		EntityCacheUtil.putResult(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleImpl.class, module.getPrimaryKey(), module);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_C,
			new Object[] {
				Long.valueOf(module.getAppId()),
				
			module.getContextName()
			}, module);

		module.resetOriginalValues();
	}

	/**
	 * Caches the modules in the entity cache if it is enabled.
	 *
	 * @param modules the modules
	 */
	public void cacheResult(List<Module> modules) {
		for (Module module : modules) {
			if (EntityCacheUtil.getResult(
						ModuleModelImpl.ENTITY_CACHE_ENABLED, ModuleImpl.class,
						module.getPrimaryKey()) == null) {
				cacheResult(module);
			}
		}
	}

	/**
	 * Clears the cache for all modules.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ModuleImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ModuleImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the module.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Module module) {
		EntityCacheUtil.removeResult(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleImpl.class, module.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FIND_ALL, FINDER_ARGS_EMPTY);

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_A_C,
			new Object[] {
				Long.valueOf(module.getAppId()),
				
			module.getContextName()
			});
	}

	/**
	 * Creates a new module with the primary key. Does not add the module to the database.
	 *
	 * @param moduleId the primary key for the new module
	 * @return the new module
	 */
	public Module create(long moduleId) {
		Module module = new ModuleImpl();

		module.setNew(true);
		module.setPrimaryKey(moduleId);

		String uuid = PortalUUIDUtil.generate();

		module.setUuid(uuid);

		return module;
	}

	/**
	 * Removes the module with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the module
	 * @return the module that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a module with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Module remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the module with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param moduleId the primary key of the module
	 * @return the module that was removed
	 * @throws com.liferay.marketplace.NoSuchModuleException if a module with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Module remove(long moduleId)
		throws NoSuchModuleException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Module module = (Module)session.get(ModuleImpl.class,
					Long.valueOf(moduleId));

			if (module == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + moduleId);
				}

				throw new NoSuchModuleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					moduleId);
			}

			return modulePersistence.remove(module);
		}
		catch (NoSuchModuleException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Removes the module from the database. Also notifies the appropriate model listeners.
	 *
	 * @param module the module
	 * @return the module that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Module remove(Module module) throws SystemException {
		return super.remove(module);
	}

	@Override
	protected Module removeImpl(Module module) throws SystemException {
		module = toUnwrappedModel(module);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, module);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		ModuleModelImpl moduleModelImpl = (ModuleModelImpl)module;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_A_C,
			new Object[] {
				Long.valueOf(moduleModelImpl.getAppId()),
				
			moduleModelImpl.getContextName()
			});

		EntityCacheUtil.removeResult(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleImpl.class, module.getPrimaryKey());

		return module;
	}

	@Override
	public Module updateImpl(com.liferay.marketplace.model.Module module,
		boolean merge) throws SystemException {
		module = toUnwrappedModel(module);

		boolean isNew = module.isNew();

		ModuleModelImpl moduleModelImpl = (ModuleModelImpl)module;

		if (Validator.isNull(module.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			module.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, module, merge);

			module.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleImpl.class, module.getPrimaryKey(), module);

		if (!isNew &&
				((module.getAppId() != moduleModelImpl.getOriginalAppId()) ||
				!Validator.equals(module.getContextName(),
					moduleModelImpl.getOriginalContextName()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_A_C,
				new Object[] {
					Long.valueOf(moduleModelImpl.getOriginalAppId()),
					
				moduleModelImpl.getOriginalContextName()
				});
		}

		if (isNew ||
				((module.getAppId() != moduleModelImpl.getOriginalAppId()) ||
				!Validator.equals(module.getContextName(),
					moduleModelImpl.getOriginalContextName()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_C,
				new Object[] {
					Long.valueOf(module.getAppId()),
					
				module.getContextName()
				}, module);
		}

		return module;
	}

	protected Module toUnwrappedModel(Module module) {
		if (module instanceof ModuleImpl) {
			return module;
		}

		ModuleImpl moduleImpl = new ModuleImpl();

		moduleImpl.setNew(module.isNew());
		moduleImpl.setPrimaryKey(module.getPrimaryKey());

		moduleImpl.setUuid(module.getUuid());
		moduleImpl.setModuleId(module.getModuleId());
		moduleImpl.setAppId(module.getAppId());
		moduleImpl.setContextName(module.getContextName());

		return moduleImpl;
	}

	/**
	 * Returns the module with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the module
	 * @return the module
	 * @throws com.liferay.portal.NoSuchModelException if a module with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Module findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the module with the primary key or throws a {@link com.liferay.marketplace.NoSuchModuleException} if it could not be found.
	 *
	 * @param moduleId the primary key of the module
	 * @return the module
	 * @throws com.liferay.marketplace.NoSuchModuleException if a module with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Module findByPrimaryKey(long moduleId)
		throws NoSuchModuleException, SystemException {
		Module module = fetchByPrimaryKey(moduleId);

		if (module == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + moduleId);
			}

			throw new NoSuchModuleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				moduleId);
		}

		return module;
	}

	/**
	 * Returns the module with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the module
	 * @return the module, or <code>null</code> if a module with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Module fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the module with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param moduleId the primary key of the module
	 * @return the module, or <code>null</code> if a module with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Module fetchByPrimaryKey(long moduleId) throws SystemException {
		Module module = (Module)EntityCacheUtil.getResult(ModuleModelImpl.ENTITY_CACHE_ENABLED,
				ModuleImpl.class, moduleId);

		if (module == _nullModule) {
			return null;
		}

		if (module == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				module = (Module)session.get(ModuleImpl.class,
						Long.valueOf(moduleId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (module != null) {
					cacheResult(module);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(ModuleModelImpl.ENTITY_CACHE_ENABLED,
						ModuleImpl.class, moduleId, _nullModule);
				}

				closeSession(session);
			}
		}

		return module;
	}

	/**
	 * Returns all the modules where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching modules
	 * @throws SystemException if a system exception occurred
	 */
	public List<Module> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the modules where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules
	 * @throws SystemException if a system exception occurred
	 */
	public List<Module> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the modules where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules
	 * @throws SystemException if a system exception occurred
	 */
	public List<Module> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, start, end, orderByComparator };

		List<Module> list = (List<Module>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_UUID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_MODULE_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				list = (List<Module>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_UUID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_UUID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first module in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module
	 * @throws com.liferay.marketplace.NoSuchModuleException if a matching module could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Module findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchModuleException, SystemException {
		List<Module> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchModuleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last module in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module
	 * @throws com.liferay.marketplace.NoSuchModuleException if a matching module could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Module findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchModuleException, SystemException {
		int count = countByUuid(uuid);

		List<Module> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchModuleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the modules before and after the current module in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param moduleId the primary key of the current module
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws com.liferay.marketplace.NoSuchModuleException if a module with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Module[] findByUuid_PrevAndNext(long moduleId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchModuleException, SystemException {
		Module module = findByPrimaryKey(moduleId);

		Session session = null;

		try {
			session = openSession();

			Module[] array = new ModuleImpl[3];

			array[0] = getByUuid_PrevAndNext(session, module, uuid,
					orderByComparator, true);

			array[1] = module;

			array[2] = getByUuid_PrevAndNext(session, module, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Module getByUuid_PrevAndNext(Session session, Module module,
		String uuid, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MODULE_WHERE);

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else {
			if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}
		}

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (uuid != null) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(module);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Module> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the modules where appId = &#63;.
	 *
	 * @param appId the app ID
	 * @return the matching modules
	 * @throws SystemException if a system exception occurred
	 */
	public List<Module> findByAppId(long appId) throws SystemException {
		return findByAppId(appId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the modules where appId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appId the app ID
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules
	 * @throws SystemException if a system exception occurred
	 */
	public List<Module> findByAppId(long appId, int start, int end)
		throws SystemException {
		return findByAppId(appId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the modules where appId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appId the app ID
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules
	 * @throws SystemException if a system exception occurred
	 */
	public List<Module> findByAppId(long appId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] { appId, start, end, orderByComparator };

		List<Module> list = (List<Module>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_APPID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_MODULE_WHERE);

			query.append(_FINDER_COLUMN_APPID_APPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appId);

				list = (List<Module>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_APPID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_APPID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first module in the ordered set where appId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appId the app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module
	 * @throws com.liferay.marketplace.NoSuchModuleException if a matching module could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Module findByAppId_First(long appId,
		OrderByComparator orderByComparator)
		throws NoSuchModuleException, SystemException {
		List<Module> list = findByAppId(appId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("appId=");
			msg.append(appId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchModuleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last module in the ordered set where appId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param appId the app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module
	 * @throws com.liferay.marketplace.NoSuchModuleException if a matching module could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Module findByAppId_Last(long appId,
		OrderByComparator orderByComparator)
		throws NoSuchModuleException, SystemException {
		int count = countByAppId(appId);

		List<Module> list = findByAppId(appId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("appId=");
			msg.append(appId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchModuleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the modules before and after the current module in the ordered set where appId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param moduleId the primary key of the current module
	 * @param appId the app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws com.liferay.marketplace.NoSuchModuleException if a module with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Module[] findByAppId_PrevAndNext(long moduleId, long appId,
		OrderByComparator orderByComparator)
		throws NoSuchModuleException, SystemException {
		Module module = findByPrimaryKey(moduleId);

		Session session = null;

		try {
			session = openSession();

			Module[] array = new ModuleImpl[3];

			array[0] = getByAppId_PrevAndNext(session, module, appId,
					orderByComparator, true);

			array[1] = module;

			array[2] = getByAppId_PrevAndNext(session, module, appId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Module getByAppId_PrevAndNext(Session session, Module module,
		long appId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MODULE_WHERE);

		query.append(_FINDER_COLUMN_APPID_APPID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(appId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(module);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Module> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the modules where contextName = &#63;.
	 *
	 * @param contextName the context name
	 * @return the matching modules
	 * @throws SystemException if a system exception occurred
	 */
	public List<Module> findByContextName(String contextName)
		throws SystemException {
		return findByContextName(contextName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the modules where contextName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param contextName the context name
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules
	 * @throws SystemException if a system exception occurred
	 */
	public List<Module> findByContextName(String contextName, int start, int end)
		throws SystemException {
		return findByContextName(contextName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the modules where contextName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param contextName the context name
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules
	 * @throws SystemException if a system exception occurred
	 */
	public List<Module> findByContextName(String contextName, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				contextName,
				
				start, end, orderByComparator
			};

		List<Module> list = (List<Module>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_CONTEXTNAME,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_MODULE_WHERE);

			if (contextName == null) {
				query.append(_FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_1);
			}
			else {
				if (contextName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (contextName != null) {
					qPos.add(contextName);
				}

				list = (List<Module>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_CONTEXTNAME,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_CONTEXTNAME,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first module in the ordered set where contextName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param contextName the context name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module
	 * @throws com.liferay.marketplace.NoSuchModuleException if a matching module could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Module findByContextName_First(String contextName,
		OrderByComparator orderByComparator)
		throws NoSuchModuleException, SystemException {
		List<Module> list = findByContextName(contextName, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("contextName=");
			msg.append(contextName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchModuleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last module in the ordered set where contextName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param contextName the context name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module
	 * @throws com.liferay.marketplace.NoSuchModuleException if a matching module could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Module findByContextName_Last(String contextName,
		OrderByComparator orderByComparator)
		throws NoSuchModuleException, SystemException {
		int count = countByContextName(contextName);

		List<Module> list = findByContextName(contextName, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("contextName=");
			msg.append(contextName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchModuleException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the modules before and after the current module in the ordered set where contextName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param moduleId the primary key of the current module
	 * @param contextName the context name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws com.liferay.marketplace.NoSuchModuleException if a module with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Module[] findByContextName_PrevAndNext(long moduleId,
		String contextName, OrderByComparator orderByComparator)
		throws NoSuchModuleException, SystemException {
		Module module = findByPrimaryKey(moduleId);

		Session session = null;

		try {
			session = openSession();

			Module[] array = new ModuleImpl[3];

			array[0] = getByContextName_PrevAndNext(session, module,
					contextName, orderByComparator, true);

			array[1] = module;

			array[2] = getByContextName_PrevAndNext(session, module,
					contextName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Module getByContextName_PrevAndNext(Session session,
		Module module, String contextName, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MODULE_WHERE);

		if (contextName == null) {
			query.append(_FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_1);
		}
		else {
			if (contextName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_2);
			}
		}

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (contextName != null) {
			qPos.add(contextName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(module);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Module> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the module where appId = &#63; and contextName = &#63; or throws a {@link com.liferay.marketplace.NoSuchModuleException} if it could not be found.
	 *
	 * @param appId the app ID
	 * @param contextName the context name
	 * @return the matching module
	 * @throws com.liferay.marketplace.NoSuchModuleException if a matching module could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Module findByA_C(long appId, String contextName)
		throws NoSuchModuleException, SystemException {
		Module module = fetchByA_C(appId, contextName);

		if (module == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("appId=");
			msg.append(appId);

			msg.append(", contextName=");
			msg.append(contextName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchModuleException(msg.toString());
		}

		return module;
	}

	/**
	 * Returns the module where appId = &#63; and contextName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param appId the app ID
	 * @param contextName the context name
	 * @return the matching module, or <code>null</code> if a matching module could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Module fetchByA_C(long appId, String contextName)
		throws SystemException {
		return fetchByA_C(appId, contextName, true);
	}

	/**
	 * Returns the module where appId = &#63; and contextName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param appId the app ID
	 * @param contextName the context name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching module, or <code>null</code> if a matching module could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Module fetchByA_C(long appId, String contextName,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { appId, contextName };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_A_C,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_MODULE_WHERE);

			query.append(_FINDER_COLUMN_A_C_APPID_2);

			if (contextName == null) {
				query.append(_FINDER_COLUMN_A_C_CONTEXTNAME_1);
			}
			else {
				if (contextName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_A_C_CONTEXTNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_A_C_CONTEXTNAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appId);

				if (contextName != null) {
					qPos.add(contextName);
				}

				List<Module> list = q.list();

				result = list;

				Module module = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_C,
						finderArgs, list);
				}
				else {
					module = list.get(0);

					cacheResult(module);

					if ((module.getAppId() != appId) ||
							(module.getContextName() == null) ||
							!module.getContextName().equals(contextName)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_C,
							finderArgs, module);
					}
				}

				return module;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_A_C,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (Module)result;
			}
		}
	}

	/**
	 * Returns all the modules.
	 *
	 * @return the modules
	 * @throws SystemException if a system exception occurred
	 */
	public List<Module> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the modules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of modules
	 * @throws SystemException if a system exception occurred
	 */
	public List<Module> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the modules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of modules
	 * @throws SystemException if a system exception occurred
	 */
	public List<Module> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		List<Module> list = (List<Module>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_MODULE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MODULE;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_ALL,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs,
						list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the modules where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (Module module : findByUuid(uuid)) {
			modulePersistence.remove(module);
		}
	}

	/**
	 * Removes all the modules where appId = &#63; from the database.
	 *
	 * @param appId the app ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAppId(long appId) throws SystemException {
		for (Module module : findByAppId(appId)) {
			modulePersistence.remove(module);
		}
	}

	/**
	 * Removes all the modules where contextName = &#63; from the database.
	 *
	 * @param contextName the context name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByContextName(String contextName)
		throws SystemException {
		for (Module module : findByContextName(contextName)) {
			modulePersistence.remove(module);
		}
	}

	/**
	 * Removes the module where appId = &#63; and contextName = &#63; from the database.
	 *
	 * @param appId the app ID
	 * @param contextName the context name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByA_C(long appId, String contextName)
		throws NoSuchModuleException, SystemException {
		Module module = findByA_C(appId, contextName);

		modulePersistence.remove(module);
	}

	/**
	 * Removes all the modules from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Module module : findAll()) {
			modulePersistence.remove(module);
		}
	}

	/**
	 * Returns the number of modules where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching modules
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MODULE_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of modules where appId = &#63;.
	 *
	 * @param appId the app ID
	 * @return the number of matching modules
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAppId(long appId) throws SystemException {
		Object[] finderArgs = new Object[] { appId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_APPID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MODULE_WHERE);

			query.append(_FINDER_COLUMN_APPID_APPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_APPID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of modules where contextName = &#63;.
	 *
	 * @param contextName the context name
	 * @return the number of matching modules
	 * @throws SystemException if a system exception occurred
	 */
	public int countByContextName(String contextName) throws SystemException {
		Object[] finderArgs = new Object[] { contextName };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CONTEXTNAME,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MODULE_WHERE);

			if (contextName == null) {
				query.append(_FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_1);
			}
			else {
				if (contextName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (contextName != null) {
					qPos.add(contextName);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CONTEXTNAME,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of modules where appId = &#63; and contextName = &#63;.
	 *
	 * @param appId the app ID
	 * @param contextName the context name
	 * @return the number of matching modules
	 * @throws SystemException if a system exception occurred
	 */
	public int countByA_C(long appId, String contextName)
		throws SystemException {
		Object[] finderArgs = new Object[] { appId, contextName };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_A_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MODULE_WHERE);

			query.append(_FINDER_COLUMN_A_C_APPID_2);

			if (contextName == null) {
				query.append(_FINDER_COLUMN_A_C_CONTEXTNAME_1);
			}
			else {
				if (contextName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_A_C_CONTEXTNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_A_C_CONTEXTNAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appId);

				if (contextName != null) {
					qPos.add(contextName);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_A_C, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of modules.
	 *
	 * @return the number of modules
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MODULE);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the module persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.marketplace.model.Module")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Module>> listenersList = new ArrayList<ModelListener<Module>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Module>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(ModuleImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = AppPersistence.class)
	protected AppPersistence appPersistence;
	@BeanReference(type = ModulePersistence.class)
	protected ModulePersistence modulePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_MODULE = "SELECT module FROM Module module";
	private static final String _SQL_SELECT_MODULE_WHERE = "SELECT module FROM Module module WHERE ";
	private static final String _SQL_COUNT_MODULE = "SELECT COUNT(module) FROM Module module";
	private static final String _SQL_COUNT_MODULE_WHERE = "SELECT COUNT(module) FROM Module module WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "module.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "module.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(module.uuid IS NULL OR module.uuid = ?)";
	private static final String _FINDER_COLUMN_APPID_APPID_2 = "module.appId = ?";
	private static final String _FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_1 = "module.contextName IS NULL";
	private static final String _FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_2 = "module.contextName = ?";
	private static final String _FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_3 = "(module.contextName IS NULL OR module.contextName = ?)";
	private static final String _FINDER_COLUMN_A_C_APPID_2 = "module.appId = ? AND ";
	private static final String _FINDER_COLUMN_A_C_CONTEXTNAME_1 = "module.contextName IS NULL";
	private static final String _FINDER_COLUMN_A_C_CONTEXTNAME_2 = "module.contextName = ?";
	private static final String _FINDER_COLUMN_A_C_CONTEXTNAME_3 = "(module.contextName IS NULL OR module.contextName = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "module.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Module exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Module exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ModulePersistenceImpl.class);
	private static Module _nullModule = new ModuleImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Module> toCacheModel() {
				return _nullModuleCacheModel;
			}
		};

	private static CacheModel<Module> _nullModuleCacheModel = new CacheModel<Module>() {
			public Module toEntityModel() {
				return _nullModule;
			}
		};
}