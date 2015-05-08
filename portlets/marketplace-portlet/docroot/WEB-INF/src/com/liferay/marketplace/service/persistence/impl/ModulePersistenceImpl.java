/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.marketplace.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.marketplace.NoSuchModuleException;
import com.liferay.marketplace.model.Module;
import com.liferay.marketplace.model.impl.ModuleImpl;
import com.liferay.marketplace.model.impl.ModuleModelImpl;
import com.liferay.marketplace.service.persistence.ModulePersistence;

import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the module service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ryan Park
 * @see ModulePersistence
 * @see com.liferay.marketplace.service.persistence.ModuleUtil
 * @generated
 */
@ProviderType
public class ModulePersistenceImpl extends BasePersistenceImpl<Module>
	implements ModulePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ModuleUtil} to access the module persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ModuleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ModuleModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the modules where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching modules
	 */
	@Override
	public List<Module> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the modules where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules
	 */
	@Override
	public List<Module> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the modules where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules
	 */
	@Override
	public List<Module> findByUuid(String uuid, int start, int end,
		OrderByComparator<Module> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<Module> list = (List<Module>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Module module : list) {
				if (!Validator.equals(uuid, module.getUuid())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_MODULE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ModuleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first module in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	@Override
	public Module findByUuid_First(String uuid,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = fetchByUuid_First(uuid, orderByComparator);

		if (module != null) {
			return module;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchModuleException(msg.toString());
	}

	/**
	 * Returns the first module in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public Module fetchByUuid_First(String uuid,
		OrderByComparator<Module> orderByComparator) {
		List<Module> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last module in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	@Override
	public Module findByUuid_Last(String uuid,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = fetchByUuid_Last(uuid, orderByComparator);

		if (module != null) {
			return module;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchModuleException(msg.toString());
	}

	/**
	 * Returns the last module in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public Module fetchByUuid_Last(String uuid,
		OrderByComparator<Module> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Module> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the modules before and after the current module in the ordered set where uuid = &#63;.
	 *
	 * @param moduleId the primary key of the current module
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	@Override
	public Module[] findByUuid_PrevAndNext(long moduleId, String uuid,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
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
		String uuid, OrderByComparator<Module> orderByComparator,
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

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
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

			String[] orderByFields = orderByComparator.getOrderByFields();

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
		else {
			query.append(ModuleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(module);

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
	 * Removes all the modules where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Module module : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(module);
		}
	}

	/**
	 * Returns the number of modules where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching modules
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MODULE_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "module.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "module.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(module.uuid IS NULL OR module.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_APPID = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAppId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPID = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAppId",
			new String[] { Long.class.getName() },
			ModuleModelImpl.APPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_APPID = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAppId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the modules where appId = &#63;.
	 *
	 * @param appId the app ID
	 * @return the matching modules
	 */
	@Override
	public List<Module> findByAppId(long appId) {
		return findByAppId(appId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the modules where appId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param appId the app ID
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules
	 */
	@Override
	public List<Module> findByAppId(long appId, int start, int end) {
		return findByAppId(appId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the modules where appId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param appId the app ID
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules
	 */
	@Override
	public List<Module> findByAppId(long appId, int start, int end,
		OrderByComparator<Module> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPID;
			finderArgs = new Object[] { appId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_APPID;
			finderArgs = new Object[] { appId, start, end, orderByComparator };
		}

		List<Module> list = (List<Module>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Module module : list) {
				if ((appId != module.getAppId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_MODULE_WHERE);

			query.append(_FINDER_COLUMN_APPID_APPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ModuleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appId);

				if (!pagination) {
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first module in the ordered set where appId = &#63;.
	 *
	 * @param appId the app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	@Override
	public Module findByAppId_First(long appId,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = fetchByAppId_First(appId, orderByComparator);

		if (module != null) {
			return module;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appId=");
		msg.append(appId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchModuleException(msg.toString());
	}

	/**
	 * Returns the first module in the ordered set where appId = &#63;.
	 *
	 * @param appId the app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public Module fetchByAppId_First(long appId,
		OrderByComparator<Module> orderByComparator) {
		List<Module> list = findByAppId(appId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last module in the ordered set where appId = &#63;.
	 *
	 * @param appId the app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	@Override
	public Module findByAppId_Last(long appId,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = fetchByAppId_Last(appId, orderByComparator);

		if (module != null) {
			return module;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("appId=");
		msg.append(appId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchModuleException(msg.toString());
	}

	/**
	 * Returns the last module in the ordered set where appId = &#63;.
	 *
	 * @param appId the app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public Module fetchByAppId_Last(long appId,
		OrderByComparator<Module> orderByComparator) {
		int count = countByAppId(appId);

		if (count == 0) {
			return null;
		}

		List<Module> list = findByAppId(appId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the modules before and after the current module in the ordered set where appId = &#63;.
	 *
	 * @param moduleId the primary key of the current module
	 * @param appId the app ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	@Override
	public Module[] findByAppId_PrevAndNext(long moduleId, long appId,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
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
		long appId, OrderByComparator<Module> orderByComparator,
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

		query.append(_FINDER_COLUMN_APPID_APPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
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

			String[] orderByFields = orderByComparator.getOrderByFields();

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
		else {
			query.append(ModuleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(appId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(module);

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
	 * Removes all the modules where appId = &#63; from the database.
	 *
	 * @param appId the app ID
	 */
	@Override
	public void removeByAppId(long appId) {
		for (Module module : findByAppId(appId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(module);
		}
	}

	/**
	 * Returns the number of modules where appId = &#63;.
	 *
	 * @param appId the app ID
	 * @return the number of matching modules
	 */
	@Override
	public int countByAppId(long appId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_APPID;

		Object[] finderArgs = new Object[] { appId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

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

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_APPID_APPID_2 = "module.appId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_BUNDLESYMBOLICNAME =
		new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByBundleSymbolicName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUNDLESYMBOLICNAME =
		new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByBundleSymbolicName",
			new String[] { String.class.getName() },
			ModuleModelImpl.BUNDLESYMBOLICNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_BUNDLESYMBOLICNAME = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByBundleSymbolicName", new String[] { String.class.getName() });

	/**
	 * Returns all the modules where bundleSymbolicName = &#63;.
	 *
	 * @param bundleSymbolicName the bundle symbolic name
	 * @return the matching modules
	 */
	@Override
	public List<Module> findByBundleSymbolicName(String bundleSymbolicName) {
		return findByBundleSymbolicName(bundleSymbolicName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the modules where bundleSymbolicName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param bundleSymbolicName the bundle symbolic name
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules
	 */
	@Override
	public List<Module> findByBundleSymbolicName(String bundleSymbolicName,
		int start, int end) {
		return findByBundleSymbolicName(bundleSymbolicName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the modules where bundleSymbolicName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param bundleSymbolicName the bundle symbolic name
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules
	 */
	@Override
	public List<Module> findByBundleSymbolicName(String bundleSymbolicName,
		int start, int end, OrderByComparator<Module> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUNDLESYMBOLICNAME;
			finderArgs = new Object[] { bundleSymbolicName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_BUNDLESYMBOLICNAME;
			finderArgs = new Object[] {
					bundleSymbolicName,
					
					start, end, orderByComparator
				};
		}

		List<Module> list = (List<Module>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Module module : list) {
				if (!Validator.equals(bundleSymbolicName,
							module.getBundleSymbolicName())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_MODULE_WHERE);

			boolean bindBundleSymbolicName = false;

			if (bundleSymbolicName == null) {
				query.append(_FINDER_COLUMN_BUNDLESYMBOLICNAME_BUNDLESYMBOLICNAME_1);
			}
			else if (bundleSymbolicName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_BUNDLESYMBOLICNAME_BUNDLESYMBOLICNAME_3);
			}
			else {
				bindBundleSymbolicName = true;

				query.append(_FINDER_COLUMN_BUNDLESYMBOLICNAME_BUNDLESYMBOLICNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ModuleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindBundleSymbolicName) {
					qPos.add(bundleSymbolicName);
				}

				if (!pagination) {
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first module in the ordered set where bundleSymbolicName = &#63;.
	 *
	 * @param bundleSymbolicName the bundle symbolic name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	@Override
	public Module findByBundleSymbolicName_First(String bundleSymbolicName,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = fetchByBundleSymbolicName_First(bundleSymbolicName,
				orderByComparator);

		if (module != null) {
			return module;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("bundleSymbolicName=");
		msg.append(bundleSymbolicName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchModuleException(msg.toString());
	}

	/**
	 * Returns the first module in the ordered set where bundleSymbolicName = &#63;.
	 *
	 * @param bundleSymbolicName the bundle symbolic name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public Module fetchByBundleSymbolicName_First(String bundleSymbolicName,
		OrderByComparator<Module> orderByComparator) {
		List<Module> list = findByBundleSymbolicName(bundleSymbolicName, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last module in the ordered set where bundleSymbolicName = &#63;.
	 *
	 * @param bundleSymbolicName the bundle symbolic name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	@Override
	public Module findByBundleSymbolicName_Last(String bundleSymbolicName,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = fetchByBundleSymbolicName_Last(bundleSymbolicName,
				orderByComparator);

		if (module != null) {
			return module;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("bundleSymbolicName=");
		msg.append(bundleSymbolicName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchModuleException(msg.toString());
	}

	/**
	 * Returns the last module in the ordered set where bundleSymbolicName = &#63;.
	 *
	 * @param bundleSymbolicName the bundle symbolic name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public Module fetchByBundleSymbolicName_Last(String bundleSymbolicName,
		OrderByComparator<Module> orderByComparator) {
		int count = countByBundleSymbolicName(bundleSymbolicName);

		if (count == 0) {
			return null;
		}

		List<Module> list = findByBundleSymbolicName(bundleSymbolicName,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the modules before and after the current module in the ordered set where bundleSymbolicName = &#63;.
	 *
	 * @param moduleId the primary key of the current module
	 * @param bundleSymbolicName the bundle symbolic name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	@Override
	public Module[] findByBundleSymbolicName_PrevAndNext(long moduleId,
		String bundleSymbolicName, OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = findByPrimaryKey(moduleId);

		Session session = null;

		try {
			session = openSession();

			Module[] array = new ModuleImpl[3];

			array[0] = getByBundleSymbolicName_PrevAndNext(session, module,
					bundleSymbolicName, orderByComparator, true);

			array[1] = module;

			array[2] = getByBundleSymbolicName_PrevAndNext(session, module,
					bundleSymbolicName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Module getByBundleSymbolicName_PrevAndNext(Session session,
		Module module, String bundleSymbolicName,
		OrderByComparator<Module> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MODULE_WHERE);

		boolean bindBundleSymbolicName = false;

		if (bundleSymbolicName == null) {
			query.append(_FINDER_COLUMN_BUNDLESYMBOLICNAME_BUNDLESYMBOLICNAME_1);
		}
		else if (bundleSymbolicName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_BUNDLESYMBOLICNAME_BUNDLESYMBOLICNAME_3);
		}
		else {
			bindBundleSymbolicName = true;

			query.append(_FINDER_COLUMN_BUNDLESYMBOLICNAME_BUNDLESYMBOLICNAME_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
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

			String[] orderByFields = orderByComparator.getOrderByFields();

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
		else {
			query.append(ModuleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindBundleSymbolicName) {
			qPos.add(bundleSymbolicName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(module);

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
	 * Removes all the modules where bundleSymbolicName = &#63; from the database.
	 *
	 * @param bundleSymbolicName the bundle symbolic name
	 */
	@Override
	public void removeByBundleSymbolicName(String bundleSymbolicName) {
		for (Module module : findByBundleSymbolicName(bundleSymbolicName,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(module);
		}
	}

	/**
	 * Returns the number of modules where bundleSymbolicName = &#63;.
	 *
	 * @param bundleSymbolicName the bundle symbolic name
	 * @return the number of matching modules
	 */
	@Override
	public int countByBundleSymbolicName(String bundleSymbolicName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_BUNDLESYMBOLICNAME;

		Object[] finderArgs = new Object[] { bundleSymbolicName };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MODULE_WHERE);

			boolean bindBundleSymbolicName = false;

			if (bundleSymbolicName == null) {
				query.append(_FINDER_COLUMN_BUNDLESYMBOLICNAME_BUNDLESYMBOLICNAME_1);
			}
			else if (bundleSymbolicName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_BUNDLESYMBOLICNAME_BUNDLESYMBOLICNAME_3);
			}
			else {
				bindBundleSymbolicName = true;

				query.append(_FINDER_COLUMN_BUNDLESYMBOLICNAME_BUNDLESYMBOLICNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindBundleSymbolicName) {
					qPos.add(bundleSymbolicName);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_BUNDLESYMBOLICNAME_BUNDLESYMBOLICNAME_1 =
		"module.bundleSymbolicName IS NULL";
	private static final String _FINDER_COLUMN_BUNDLESYMBOLICNAME_BUNDLESYMBOLICNAME_2 =
		"module.bundleSymbolicName = ?";
	private static final String _FINDER_COLUMN_BUNDLESYMBOLICNAME_BUNDLESYMBOLICNAME_3 =
		"(module.bundleSymbolicName IS NULL OR module.bundleSymbolicName = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTEXTNAME =
		new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByContextName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTEXTNAME =
		new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByContextName",
			new String[] { String.class.getName() },
			ModuleModelImpl.CONTEXTNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CONTEXTNAME = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByContextName",
			new String[] { String.class.getName() });

	/**
	 * Returns all the modules where contextName = &#63;.
	 *
	 * @param contextName the context name
	 * @return the matching modules
	 */
	@Override
	public List<Module> findByContextName(String contextName) {
		return findByContextName(contextName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the modules where contextName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contextName the context name
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of matching modules
	 */
	@Override
	public List<Module> findByContextName(String contextName, int start, int end) {
		return findByContextName(contextName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the modules where contextName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contextName the context name
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching modules
	 */
	@Override
	public List<Module> findByContextName(String contextName, int start,
		int end, OrderByComparator<Module> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTEXTNAME;
			finderArgs = new Object[] { contextName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTEXTNAME;
			finderArgs = new Object[] { contextName, start, end, orderByComparator };
		}

		List<Module> list = (List<Module>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Module module : list) {
				if (!Validator.equals(contextName, module.getContextName())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_MODULE_WHERE);

			boolean bindContextName = false;

			if (contextName == null) {
				query.append(_FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_1);
			}
			else if (contextName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_3);
			}
			else {
				bindContextName = true;

				query.append(_FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ModuleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindContextName) {
					qPos.add(contextName);
				}

				if (!pagination) {
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first module in the ordered set where contextName = &#63;.
	 *
	 * @param contextName the context name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	@Override
	public Module findByContextName_First(String contextName,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = fetchByContextName_First(contextName, orderByComparator);

		if (module != null) {
			return module;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("contextName=");
		msg.append(contextName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchModuleException(msg.toString());
	}

	/**
	 * Returns the first module in the ordered set where contextName = &#63;.
	 *
	 * @param contextName the context name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public Module fetchByContextName_First(String contextName,
		OrderByComparator<Module> orderByComparator) {
		List<Module> list = findByContextName(contextName, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last module in the ordered set where contextName = &#63;.
	 *
	 * @param contextName the context name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	@Override
	public Module findByContextName_Last(String contextName,
		OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
		Module module = fetchByContextName_Last(contextName, orderByComparator);

		if (module != null) {
			return module;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("contextName=");
		msg.append(contextName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchModuleException(msg.toString());
	}

	/**
	 * Returns the last module in the ordered set where contextName = &#63;.
	 *
	 * @param contextName the context name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public Module fetchByContextName_Last(String contextName,
		OrderByComparator<Module> orderByComparator) {
		int count = countByContextName(contextName);

		if (count == 0) {
			return null;
		}

		List<Module> list = findByContextName(contextName, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the modules before and after the current module in the ordered set where contextName = &#63;.
	 *
	 * @param moduleId the primary key of the current module
	 * @param contextName the context name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	@Override
	public Module[] findByContextName_PrevAndNext(long moduleId,
		String contextName, OrderByComparator<Module> orderByComparator)
		throws NoSuchModuleException {
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
		Module module, String contextName,
		OrderByComparator<Module> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MODULE_WHERE);

		boolean bindContextName = false;

		if (contextName == null) {
			query.append(_FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_1);
		}
		else if (contextName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_3);
		}
		else {
			bindContextName = true;

			query.append(_FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
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

			String[] orderByFields = orderByComparator.getOrderByFields();

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
		else {
			query.append(ModuleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindContextName) {
			qPos.add(contextName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(module);

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
	 * Removes all the modules where contextName = &#63; from the database.
	 *
	 * @param contextName the context name
	 */
	@Override
	public void removeByContextName(String contextName) {
		for (Module module : findByContextName(contextName, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(module);
		}
	}

	/**
	 * Returns the number of modules where contextName = &#63;.
	 *
	 * @param contextName the context name
	 * @return the number of matching modules
	 */
	@Override
	public int countByContextName(String contextName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CONTEXTNAME;

		Object[] finderArgs = new Object[] { contextName };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MODULE_WHERE);

			boolean bindContextName = false;

			if (contextName == null) {
				query.append(_FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_1);
			}
			else if (contextName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_3);
			}
			else {
				bindContextName = true;

				query.append(_FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindContextName) {
					qPos.add(contextName);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_1 = "module.contextName IS NULL";
	private static final String _FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_2 = "module.contextName = ?";
	private static final String _FINDER_COLUMN_CONTEXTNAME_CONTEXTNAME_3 = "(module.contextName IS NULL OR module.contextName = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_A_CN = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByA_CN",
			new String[] { Long.class.getName(), String.class.getName() },
			ModuleModelImpl.APPID_COLUMN_BITMASK |
			ModuleModelImpl.CONTEXTNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_A_CN = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByA_CN",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the module where appId = &#63; and contextName = &#63; or throws a {@link NoSuchModuleException} if it could not be found.
	 *
	 * @param appId the app ID
	 * @param contextName the context name
	 * @return the matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	@Override
	public Module findByA_CN(long appId, String contextName)
		throws NoSuchModuleException {
		Module module = fetchByA_CN(appId, contextName);

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
	 */
	@Override
	public Module fetchByA_CN(long appId, String contextName) {
		return fetchByA_CN(appId, contextName, true);
	}

	/**
	 * Returns the module where appId = &#63; and contextName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param appId the app ID
	 * @param contextName the context name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public Module fetchByA_CN(long appId, String contextName,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { appId, contextName };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_A_CN,
					finderArgs, this);
		}

		if (result instanceof Module) {
			Module module = (Module)result;

			if ((appId != module.getAppId()) ||
					!Validator.equals(contextName, module.getContextName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_MODULE_WHERE);

			query.append(_FINDER_COLUMN_A_CN_APPID_2);

			boolean bindContextName = false;

			if (contextName == null) {
				query.append(_FINDER_COLUMN_A_CN_CONTEXTNAME_1);
			}
			else if (contextName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_A_CN_CONTEXTNAME_3);
			}
			else {
				bindContextName = true;

				query.append(_FINDER_COLUMN_A_CN_CONTEXTNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appId);

				if (bindContextName) {
					qPos.add(contextName);
				}

				List<Module> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_CN,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"ModulePersistenceImpl.fetchByA_CN(long, String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					Module module = list.get(0);

					result = module;

					cacheResult(module);

					if ((module.getAppId() != appId) ||
							(module.getContextName() == null) ||
							!module.getContextName().equals(contextName)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_CN,
							finderArgs, module);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_A_CN,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (Module)result;
		}
	}

	/**
	 * Removes the module where appId = &#63; and contextName = &#63; from the database.
	 *
	 * @param appId the app ID
	 * @param contextName the context name
	 * @return the module that was removed
	 */
	@Override
	public Module removeByA_CN(long appId, String contextName)
		throws NoSuchModuleException {
		Module module = findByA_CN(appId, contextName);

		return remove(module);
	}

	/**
	 * Returns the number of modules where appId = &#63; and contextName = &#63;.
	 *
	 * @param appId the app ID
	 * @param contextName the context name
	 * @return the number of matching modules
	 */
	@Override
	public int countByA_CN(long appId, String contextName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_A_CN;

		Object[] finderArgs = new Object[] { appId, contextName };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MODULE_WHERE);

			query.append(_FINDER_COLUMN_A_CN_APPID_2);

			boolean bindContextName = false;

			if (contextName == null) {
				query.append(_FINDER_COLUMN_A_CN_CONTEXTNAME_1);
			}
			else if (contextName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_A_CN_CONTEXTNAME_3);
			}
			else {
				bindContextName = true;

				query.append(_FINDER_COLUMN_A_CN_CONTEXTNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appId);

				if (bindContextName) {
					qPos.add(contextName);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_A_CN_APPID_2 = "module.appId = ? AND ";
	private static final String _FINDER_COLUMN_A_CN_CONTEXTNAME_1 = "module.contextName IS NULL";
	private static final String _FINDER_COLUMN_A_CN_CONTEXTNAME_2 = "module.contextName = ?";
	private static final String _FINDER_COLUMN_A_CN_CONTEXTNAME_3 = "(module.contextName IS NULL OR module.contextName = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_A_BSN_BV = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, ModuleImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByA_BSN_BV",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			},
			ModuleModelImpl.APPID_COLUMN_BITMASK |
			ModuleModelImpl.BUNDLESYMBOLICNAME_COLUMN_BITMASK |
			ModuleModelImpl.BUNDLEVERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_A_BSN_BV = new FinderPath(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByA_BSN_BV",
			new String[] {
				Long.class.getName(), String.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the module where appId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63; or throws a {@link NoSuchModuleException} if it could not be found.
	 *
	 * @param appId the app ID
	 * @param bundleSymbolicName the bundle symbolic name
	 * @param bundleVersion the bundle version
	 * @return the matching module
	 * @throws NoSuchModuleException if a matching module could not be found
	 */
	@Override
	public Module findByA_BSN_BV(long appId, String bundleSymbolicName,
		String bundleVersion) throws NoSuchModuleException {
		Module module = fetchByA_BSN_BV(appId, bundleSymbolicName, bundleVersion);

		if (module == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("appId=");
			msg.append(appId);

			msg.append(", bundleSymbolicName=");
			msg.append(bundleSymbolicName);

			msg.append(", bundleVersion=");
			msg.append(bundleVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchModuleException(msg.toString());
		}

		return module;
	}

	/**
	 * Returns the module where appId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param appId the app ID
	 * @param bundleSymbolicName the bundle symbolic name
	 * @param bundleVersion the bundle version
	 * @return the matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public Module fetchByA_BSN_BV(long appId, String bundleSymbolicName,
		String bundleVersion) {
		return fetchByA_BSN_BV(appId, bundleSymbolicName, bundleVersion, true);
	}

	/**
	 * Returns the module where appId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param appId the app ID
	 * @param bundleSymbolicName the bundle symbolic name
	 * @param bundleVersion the bundle version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching module, or <code>null</code> if a matching module could not be found
	 */
	@Override
	public Module fetchByA_BSN_BV(long appId, String bundleSymbolicName,
		String bundleVersion, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				appId, bundleSymbolicName, bundleVersion
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_A_BSN_BV,
					finderArgs, this);
		}

		if (result instanceof Module) {
			Module module = (Module)result;

			if ((appId != module.getAppId()) ||
					!Validator.equals(bundleSymbolicName,
						module.getBundleSymbolicName()) ||
					!Validator.equals(bundleVersion, module.getBundleVersion())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_MODULE_WHERE);

			query.append(_FINDER_COLUMN_A_BSN_BV_APPID_2);

			boolean bindBundleSymbolicName = false;

			if (bundleSymbolicName == null) {
				query.append(_FINDER_COLUMN_A_BSN_BV_BUNDLESYMBOLICNAME_1);
			}
			else if (bundleSymbolicName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_A_BSN_BV_BUNDLESYMBOLICNAME_3);
			}
			else {
				bindBundleSymbolicName = true;

				query.append(_FINDER_COLUMN_A_BSN_BV_BUNDLESYMBOLICNAME_2);
			}

			boolean bindBundleVersion = false;

			if (bundleVersion == null) {
				query.append(_FINDER_COLUMN_A_BSN_BV_BUNDLEVERSION_1);
			}
			else if (bundleVersion.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_A_BSN_BV_BUNDLEVERSION_3);
			}
			else {
				bindBundleVersion = true;

				query.append(_FINDER_COLUMN_A_BSN_BV_BUNDLEVERSION_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appId);

				if (bindBundleSymbolicName) {
					qPos.add(bundleSymbolicName);
				}

				if (bindBundleVersion) {
					qPos.add(bundleVersion);
				}

				List<Module> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_BSN_BV,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"ModulePersistenceImpl.fetchByA_BSN_BV(long, String, String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					Module module = list.get(0);

					result = module;

					cacheResult(module);

					if ((module.getAppId() != appId) ||
							(module.getBundleSymbolicName() == null) ||
							!module.getBundleSymbolicName()
									   .equals(bundleSymbolicName) ||
							(module.getBundleVersion() == null) ||
							!module.getBundleVersion().equals(bundleVersion)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_BSN_BV,
							finderArgs, module);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_A_BSN_BV,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (Module)result;
		}
	}

	/**
	 * Removes the module where appId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63; from the database.
	 *
	 * @param appId the app ID
	 * @param bundleSymbolicName the bundle symbolic name
	 * @param bundleVersion the bundle version
	 * @return the module that was removed
	 */
	@Override
	public Module removeByA_BSN_BV(long appId, String bundleSymbolicName,
		String bundleVersion) throws NoSuchModuleException {
		Module module = findByA_BSN_BV(appId, bundleSymbolicName, bundleVersion);

		return remove(module);
	}

	/**
	 * Returns the number of modules where appId = &#63; and bundleSymbolicName = &#63; and bundleVersion = &#63;.
	 *
	 * @param appId the app ID
	 * @param bundleSymbolicName the bundle symbolic name
	 * @param bundleVersion the bundle version
	 * @return the number of matching modules
	 */
	@Override
	public int countByA_BSN_BV(long appId, String bundleSymbolicName,
		String bundleVersion) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_A_BSN_BV;

		Object[] finderArgs = new Object[] {
				appId, bundleSymbolicName, bundleVersion
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_MODULE_WHERE);

			query.append(_FINDER_COLUMN_A_BSN_BV_APPID_2);

			boolean bindBundleSymbolicName = false;

			if (bundleSymbolicName == null) {
				query.append(_FINDER_COLUMN_A_BSN_BV_BUNDLESYMBOLICNAME_1);
			}
			else if (bundleSymbolicName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_A_BSN_BV_BUNDLESYMBOLICNAME_3);
			}
			else {
				bindBundleSymbolicName = true;

				query.append(_FINDER_COLUMN_A_BSN_BV_BUNDLESYMBOLICNAME_2);
			}

			boolean bindBundleVersion = false;

			if (bundleVersion == null) {
				query.append(_FINDER_COLUMN_A_BSN_BV_BUNDLEVERSION_1);
			}
			else if (bundleVersion.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_A_BSN_BV_BUNDLEVERSION_3);
			}
			else {
				bindBundleVersion = true;

				query.append(_FINDER_COLUMN_A_BSN_BV_BUNDLEVERSION_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(appId);

				if (bindBundleSymbolicName) {
					qPos.add(bundleSymbolicName);
				}

				if (bindBundleVersion) {
					qPos.add(bundleVersion);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_A_BSN_BV_APPID_2 = "module.appId = ? AND ";
	private static final String _FINDER_COLUMN_A_BSN_BV_BUNDLESYMBOLICNAME_1 = "module.bundleSymbolicName IS NULL AND ";
	private static final String _FINDER_COLUMN_A_BSN_BV_BUNDLESYMBOLICNAME_2 = "module.bundleSymbolicName = ? AND ";
	private static final String _FINDER_COLUMN_A_BSN_BV_BUNDLESYMBOLICNAME_3 = "(module.bundleSymbolicName IS NULL OR module.bundleSymbolicName = '') AND ";
	private static final String _FINDER_COLUMN_A_BSN_BV_BUNDLEVERSION_1 = "module.bundleVersion IS NULL";
	private static final String _FINDER_COLUMN_A_BSN_BV_BUNDLEVERSION_2 = "module.bundleVersion = ?";
	private static final String _FINDER_COLUMN_A_BSN_BV_BUNDLEVERSION_3 = "(module.bundleVersion IS NULL OR module.bundleVersion = '')";

	public ModulePersistenceImpl() {
		setModelClass(Module.class);
	}

	/**
	 * Caches the module in the entity cache if it is enabled.
	 *
	 * @param module the module
	 */
	@Override
	public void cacheResult(Module module) {
		EntityCacheUtil.putResult(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleImpl.class, module.getPrimaryKey(), module);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_CN,
			new Object[] { module.getAppId(), module.getContextName() }, module);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_BSN_BV,
			new Object[] {
				module.getAppId(), module.getBundleSymbolicName(),
				module.getBundleVersion()
			}, module);

		module.resetOriginalValues();
	}

	/**
	 * Caches the modules in the entity cache if it is enabled.
	 *
	 * @param modules the modules
	 */
	@Override
	public void cacheResult(List<Module> modules) {
		for (Module module : modules) {
			if (EntityCacheUtil.getResult(
						ModuleModelImpl.ENTITY_CACHE_ENABLED, ModuleImpl.class,
						module.getPrimaryKey()) == null) {
				cacheResult(module);
			}
			else {
				module.resetOriginalValues();
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
		EntityCacheUtil.clearCache(ModuleImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
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

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(module);
	}

	@Override
	public void clearCache(List<Module> modules) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Module module : modules) {
			EntityCacheUtil.removeResult(ModuleModelImpl.ENTITY_CACHE_ENABLED,
				ModuleImpl.class, module.getPrimaryKey());

			clearUniqueFindersCache(module);
		}
	}

	protected void cacheUniqueFindersCache(Module module) {
		if (module.isNew()) {
			Object[] args = new Object[] {
					module.getAppId(), module.getContextName()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_A_CN, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_CN, args, module);

			args = new Object[] {
					module.getAppId(), module.getBundleSymbolicName(),
					module.getBundleVersion()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_A_BSN_BV, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_BSN_BV, args,
				module);
		}
		else {
			ModuleModelImpl moduleModelImpl = (ModuleModelImpl)module;

			if ((moduleModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_A_CN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						module.getAppId(), module.getContextName()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_A_CN, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_CN, args,
					module);
			}

			if ((moduleModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_A_BSN_BV.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						module.getAppId(), module.getBundleSymbolicName(),
						module.getBundleVersion()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_A_BSN_BV, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_BSN_BV, args,
					module);
			}
		}
	}

	protected void clearUniqueFindersCache(Module module) {
		ModuleModelImpl moduleModelImpl = (ModuleModelImpl)module;

		Object[] args = new Object[] { module.getAppId(), module.getContextName() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_A_CN, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_A_CN, args);

		if ((moduleModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_A_CN.getColumnBitmask()) != 0) {
			args = new Object[] {
					moduleModelImpl.getOriginalAppId(),
					moduleModelImpl.getOriginalContextName()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_A_CN, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_A_CN, args);
		}

		args = new Object[] {
				module.getAppId(), module.getBundleSymbolicName(),
				module.getBundleVersion()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_A_BSN_BV, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_A_BSN_BV, args);

		if ((moduleModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_A_BSN_BV.getColumnBitmask()) != 0) {
			args = new Object[] {
					moduleModelImpl.getOriginalAppId(),
					moduleModelImpl.getOriginalBundleSymbolicName(),
					moduleModelImpl.getOriginalBundleVersion()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_A_BSN_BV, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_A_BSN_BV, args);
		}
	}

	/**
	 * Creates a new module with the primary key. Does not add the module to the database.
	 *
	 * @param moduleId the primary key for the new module
	 * @return the new module
	 */
	@Override
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
	 * @param moduleId the primary key of the module
	 * @return the module that was removed
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	@Override
	public Module remove(long moduleId) throws NoSuchModuleException {
		return remove((Serializable)moduleId);
	}

	/**
	 * Removes the module with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the module
	 * @return the module that was removed
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	@Override
	public Module remove(Serializable primaryKey) throws NoSuchModuleException {
		Session session = null;

		try {
			session = openSession();

			Module module = (Module)session.get(ModuleImpl.class, primaryKey);

			if (module == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchModuleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(module);
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

	@Override
	protected Module removeImpl(Module module) {
		module = toUnwrappedModel(module);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(module)) {
				module = (Module)session.get(ModuleImpl.class,
						module.getPrimaryKeyObj());
			}

			if (module != null) {
				session.delete(module);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (module != null) {
			clearCache(module);
		}

		return module;
	}

	@Override
	public Module updateImpl(Module module) {
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

			if (module.isNew()) {
				session.save(module);

				module.setNew(false);
			}
			else {
				session.merge(module);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ModuleModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((moduleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { moduleModelImpl.getOriginalUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { moduleModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((moduleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { moduleModelImpl.getOriginalAppId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPID,
					args);

				args = new Object[] { moduleModelImpl.getAppId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPID,
					args);
			}

			if ((moduleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUNDLESYMBOLICNAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						moduleModelImpl.getOriginalBundleSymbolicName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BUNDLESYMBOLICNAME,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUNDLESYMBOLICNAME,
					args);

				args = new Object[] { moduleModelImpl.getBundleSymbolicName() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BUNDLESYMBOLICNAME,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BUNDLESYMBOLICNAME,
					args);
			}

			if ((moduleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTEXTNAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						moduleModelImpl.getOriginalContextName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTEXTNAME,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTEXTNAME,
					args);

				args = new Object[] { moduleModelImpl.getContextName() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTEXTNAME,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTEXTNAME,
					args);
			}
		}

		EntityCacheUtil.putResult(ModuleModelImpl.ENTITY_CACHE_ENABLED,
			ModuleImpl.class, module.getPrimaryKey(), module, false);

		clearUniqueFindersCache(module);
		cacheUniqueFindersCache(module);

		module.resetOriginalValues();

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
		moduleImpl.setBundleSymbolicName(module.getBundleSymbolicName());
		moduleImpl.setBundleVersion(module.getBundleVersion());
		moduleImpl.setContextName(module.getContextName());

		return moduleImpl;
	}

	/**
	 * Returns the module with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the module
	 * @return the module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	@Override
	public Module findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModuleException {
		Module module = fetchByPrimaryKey(primaryKey);

		if (module == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchModuleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return module;
	}

	/**
	 * Returns the module with the primary key or throws a {@link NoSuchModuleException} if it could not be found.
	 *
	 * @param moduleId the primary key of the module
	 * @return the module
	 * @throws NoSuchModuleException if a module with the primary key could not be found
	 */
	@Override
	public Module findByPrimaryKey(long moduleId) throws NoSuchModuleException {
		return findByPrimaryKey((Serializable)moduleId);
	}

	/**
	 * Returns the module with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the module
	 * @return the module, or <code>null</code> if a module with the primary key could not be found
	 */
	@Override
	public Module fetchByPrimaryKey(Serializable primaryKey) {
		Module module = (Module)EntityCacheUtil.getResult(ModuleModelImpl.ENTITY_CACHE_ENABLED,
				ModuleImpl.class, primaryKey);

		if (module == _nullModule) {
			return null;
		}

		if (module == null) {
			Session session = null;

			try {
				session = openSession();

				module = (Module)session.get(ModuleImpl.class, primaryKey);

				if (module != null) {
					cacheResult(module);
				}
				else {
					EntityCacheUtil.putResult(ModuleModelImpl.ENTITY_CACHE_ENABLED,
						ModuleImpl.class, primaryKey, _nullModule);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ModuleModelImpl.ENTITY_CACHE_ENABLED,
					ModuleImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return module;
	}

	/**
	 * Returns the module with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param moduleId the primary key of the module
	 * @return the module, or <code>null</code> if a module with the primary key could not be found
	 */
	@Override
	public Module fetchByPrimaryKey(long moduleId) {
		return fetchByPrimaryKey((Serializable)moduleId);
	}

	@Override
	public Map<Serializable, Module> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Module> map = new HashMap<Serializable, Module>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Module module = fetchByPrimaryKey(primaryKey);

			if (module != null) {
				map.put(primaryKey, module);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Module module = (Module)EntityCacheUtil.getResult(ModuleModelImpl.ENTITY_CACHE_ENABLED,
					ModuleImpl.class, primaryKey);

			if (module == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, module);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MODULE_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (Module module : (List<Module>)q.list()) {
				map.put(module.getPrimaryKeyObj(), module);

				cacheResult(module);

				uncachedPrimaryKeys.remove(module.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(ModuleModelImpl.ENTITY_CACHE_ENABLED,
					ModuleImpl.class, primaryKey, _nullModule);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the modules.
	 *
	 * @return the modules
	 */
	@Override
	public List<Module> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the modules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @return the range of modules
	 */
	@Override
	public List<Module> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the modules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of modules
	 * @param end the upper bound of the range of modules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of modules
	 */
	@Override
	public List<Module> findAll(int start, int end,
		OrderByComparator<Module> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Module> list = (List<Module>)FinderCacheUtil.getResult(finderPath,
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

				if (pagination) {
					sql = sql.concat(ModuleModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Module>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the modules from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Module module : findAll()) {
			remove(module);
		}
	}

	/**
	 * Returns the number of modules.
	 *
	 * @return the number of modules
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MODULE);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	/**
	 * Initializes the module persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(ModuleImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_MODULE = "SELECT module FROM Module module";
	private static final String _SQL_SELECT_MODULE_WHERE_PKS_IN = "SELECT module FROM Module module WHERE moduleId IN (";
	private static final String _SQL_SELECT_MODULE_WHERE = "SELECT module FROM Module module WHERE ";
	private static final String _SQL_COUNT_MODULE = "SELECT COUNT(module) FROM Module module";
	private static final String _SQL_COUNT_MODULE_WHERE = "SELECT COUNT(module) FROM Module module WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "module.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Module exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Module exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ModulePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static final Module _nullModule = new ModuleImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Module> toCacheModel() {
				return _nullModuleCacheModel;
			}
		};

	private static final CacheModel<Module> _nullModuleCacheModel = new CacheModel<Module>() {
			@Override
			public Module toEntityModel() {
				return _nullModule;
			}
		};
}