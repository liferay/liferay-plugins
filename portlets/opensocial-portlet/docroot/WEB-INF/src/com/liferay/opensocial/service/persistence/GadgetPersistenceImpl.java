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

package com.liferay.opensocial.service.persistence;

import com.liferay.opensocial.NoSuchGadgetException;
import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.model.impl.GadgetImpl;
import com.liferay.opensocial.model.impl.GadgetModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
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
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the gadget service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GadgetPersistence
 * @see GadgetUtil
 * @generated
 */
public class GadgetPersistenceImpl extends BasePersistenceImpl<Gadget>
	implements GadgetPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link GadgetUtil} to access the gadget persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = GadgetImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_UUID = new FinderPath(GadgetModelImpl.ENTITY_CACHE_ENABLED,
			GadgetModelImpl.FINDER_CACHE_ENABLED, GadgetImpl.class,
			FINDER_CLASS_NAME_LIST, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(GadgetModelImpl.ENTITY_CACHE_ENABLED,
			GadgetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(GadgetModelImpl.ENTITY_CACHE_ENABLED,
			GadgetModelImpl.FINDER_CACHE_ENABLED, GadgetImpl.class,
			FINDER_CLASS_NAME_LIST, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(GadgetModelImpl.ENTITY_CACHE_ENABLED,
			GadgetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_C_U = new FinderPath(GadgetModelImpl.ENTITY_CACHE_ENABLED,
			GadgetModelImpl.FINDER_CACHE_ENABLED, GadgetImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByC_U",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_C_U = new FinderPath(GadgetModelImpl.ENTITY_CACHE_ENABLED,
			GadgetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByC_U",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(GadgetModelImpl.ENTITY_CACHE_ENABLED,
			GadgetModelImpl.FINDER_CACHE_ENABLED, GadgetImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GadgetModelImpl.ENTITY_CACHE_ENABLED,
			GadgetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the gadget in the entity cache if it is enabled.
	 *
	 * @param gadget the gadget
	 */
	public void cacheResult(Gadget gadget) {
		EntityCacheUtil.putResult(GadgetModelImpl.ENTITY_CACHE_ENABLED,
			GadgetImpl.class, gadget.getPrimaryKey(), gadget);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_U,
			new Object[] { Long.valueOf(gadget.getCompanyId()), gadget.getUrl() },
			gadget);

		gadget.resetOriginalValues();
	}

	/**
	 * Caches the gadgets in the entity cache if it is enabled.
	 *
	 * @param gadgets the gadgets
	 */
	public void cacheResult(List<Gadget> gadgets) {
		for (Gadget gadget : gadgets) {
			if (EntityCacheUtil.getResult(
						GadgetModelImpl.ENTITY_CACHE_ENABLED, GadgetImpl.class,
						gadget.getPrimaryKey()) == null) {
				cacheResult(gadget);
			}
		}
	}

	/**
	 * Clears the cache for all gadgets.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(GadgetImpl.class.getName());
		}

		EntityCacheUtil.clearCache(GadgetImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the gadget.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Gadget gadget) {
		EntityCacheUtil.removeResult(GadgetModelImpl.ENTITY_CACHE_ENABLED,
			GadgetImpl.class, gadget.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FIND_ALL, FINDER_ARGS_EMPTY);

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_U,
			new Object[] { Long.valueOf(gadget.getCompanyId()), gadget.getUrl() });
	}

	/**
	 * Creates a new gadget with the primary key. Does not add the gadget to the database.
	 *
	 * @param gadgetId the primary key for the new gadget
	 * @return the new gadget
	 */
	public Gadget create(long gadgetId) {
		Gadget gadget = new GadgetImpl();

		gadget.setNew(true);
		gadget.setPrimaryKey(gadgetId);

		String uuid = PortalUUIDUtil.generate();

		gadget.setUuid(uuid);

		return gadget;
	}

	/**
	 * Removes the gadget with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the gadget
	 * @return the gadget that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a gadget with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Gadget remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the gadget with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param gadgetId the primary key of the gadget
	 * @return the gadget that was removed
	 * @throws com.liferay.opensocial.NoSuchGadgetException if a gadget with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Gadget remove(long gadgetId)
		throws NoSuchGadgetException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Gadget gadget = (Gadget)session.get(GadgetImpl.class,
					Long.valueOf(gadgetId));

			if (gadget == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + gadgetId);
				}

				throw new NoSuchGadgetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					gadgetId);
			}

			return gadgetPersistence.remove(gadget);
		}
		catch (NoSuchGadgetException nsee) {
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
	 * Removes the gadget from the database. Also notifies the appropriate model listeners.
	 *
	 * @param gadget the gadget
	 * @return the gadget that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Gadget remove(Gadget gadget) throws SystemException {
		return super.remove(gadget);
	}

	@Override
	protected Gadget removeImpl(Gadget gadget) throws SystemException {
		gadget = toUnwrappedModel(gadget);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, gadget);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		GadgetModelImpl gadgetModelImpl = (GadgetModelImpl)gadget;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_U,
			new Object[] {
				Long.valueOf(gadgetModelImpl.getCompanyId()),
				
			gadgetModelImpl.getUrl()
			});

		EntityCacheUtil.removeResult(GadgetModelImpl.ENTITY_CACHE_ENABLED,
			GadgetImpl.class, gadget.getPrimaryKey());

		return gadget;
	}

	@Override
	public Gadget updateImpl(com.liferay.opensocial.model.Gadget gadget,
		boolean merge) throws SystemException {
		gadget = toUnwrappedModel(gadget);

		boolean isNew = gadget.isNew();

		GadgetModelImpl gadgetModelImpl = (GadgetModelImpl)gadget;

		if (Validator.isNull(gadget.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			gadget.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, gadget, merge);

			gadget.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(GadgetModelImpl.ENTITY_CACHE_ENABLED,
			GadgetImpl.class, gadget.getPrimaryKey(), gadget);

		if (!isNew &&
				((gadget.getCompanyId() != gadgetModelImpl.getOriginalCompanyId()) ||
				!Validator.equals(gadget.getUrl(),
					gadgetModelImpl.getOriginalUrl()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_U,
				new Object[] {
					Long.valueOf(gadgetModelImpl.getOriginalCompanyId()),
					
				gadgetModelImpl.getOriginalUrl()
				});
		}

		if (isNew ||
				((gadget.getCompanyId() != gadgetModelImpl.getOriginalCompanyId()) ||
				!Validator.equals(gadget.getUrl(),
					gadgetModelImpl.getOriginalUrl()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_U,
				new Object[] {
					Long.valueOf(gadget.getCompanyId()),
					
				gadget.getUrl()
				}, gadget);
		}

		return gadget;
	}

	protected Gadget toUnwrappedModel(Gadget gadget) {
		if (gadget instanceof GadgetImpl) {
			return gadget;
		}

		GadgetImpl gadgetImpl = new GadgetImpl();

		gadgetImpl.setNew(gadget.isNew());
		gadgetImpl.setPrimaryKey(gadget.getPrimaryKey());

		gadgetImpl.setUuid(gadget.getUuid());
		gadgetImpl.setGadgetId(gadget.getGadgetId());
		gadgetImpl.setCompanyId(gadget.getCompanyId());
		gadgetImpl.setCreateDate(gadget.getCreateDate());
		gadgetImpl.setModifiedDate(gadget.getModifiedDate());
		gadgetImpl.setName(gadget.getName());
		gadgetImpl.setUrl(gadget.getUrl());
		gadgetImpl.setPortletCategoryNames(gadget.getPortletCategoryNames());

		return gadgetImpl;
	}

	/**
	 * Returns the gadget with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the gadget
	 * @return the gadget
	 * @throws com.liferay.portal.NoSuchModelException if a gadget with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Gadget findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the gadget with the primary key or throws a {@link com.liferay.opensocial.NoSuchGadgetException} if it could not be found.
	 *
	 * @param gadgetId the primary key of the gadget
	 * @return the gadget
	 * @throws com.liferay.opensocial.NoSuchGadgetException if a gadget with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Gadget findByPrimaryKey(long gadgetId)
		throws NoSuchGadgetException, SystemException {
		Gadget gadget = fetchByPrimaryKey(gadgetId);

		if (gadget == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + gadgetId);
			}

			throw new NoSuchGadgetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				gadgetId);
		}

		return gadget;
	}

	/**
	 * Returns the gadget with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the gadget
	 * @return the gadget, or <code>null</code> if a gadget with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Gadget fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the gadget with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param gadgetId the primary key of the gadget
	 * @return the gadget, or <code>null</code> if a gadget with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Gadget fetchByPrimaryKey(long gadgetId) throws SystemException {
		Gadget gadget = (Gadget)EntityCacheUtil.getResult(GadgetModelImpl.ENTITY_CACHE_ENABLED,
				GadgetImpl.class, gadgetId);

		if (gadget == _nullGadget) {
			return null;
		}

		if (gadget == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				gadget = (Gadget)session.get(GadgetImpl.class,
						Long.valueOf(gadgetId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (gadget != null) {
					cacheResult(gadget);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(GadgetModelImpl.ENTITY_CACHE_ENABLED,
						GadgetImpl.class, gadgetId, _nullGadget);
				}

				closeSession(session);
			}
		}

		return gadget;
	}

	/**
	 * Returns all the gadgets where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching gadgets
	 * @throws SystemException if a system exception occurred
	 */
	public List<Gadget> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the gadgets where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of gadgets
	 * @param end the upper bound of the range of gadgets (not inclusive)
	 * @return the range of matching gadgets
	 * @throws SystemException if a system exception occurred
	 */
	public List<Gadget> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the gadgets where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of gadgets
	 * @param end the upper bound of the range of gadgets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching gadgets
	 * @throws SystemException if a system exception occurred
	 */
	public List<Gadget> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, start, end, orderByComparator };

		List<Gadget> list = (List<Gadget>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_UUID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_GADGET_WHERE);

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

			else {
				query.append(GadgetModelImpl.ORDER_BY_JPQL);
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

				list = (List<Gadget>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first gadget in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching gadget
	 * @throws com.liferay.opensocial.NoSuchGadgetException if a matching gadget could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Gadget findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchGadgetException, SystemException {
		List<Gadget> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchGadgetException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last gadget in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching gadget
	 * @throws com.liferay.opensocial.NoSuchGadgetException if a matching gadget could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Gadget findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchGadgetException, SystemException {
		int count = countByUuid(uuid);

		List<Gadget> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchGadgetException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the gadgets before and after the current gadget in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param gadgetId the primary key of the current gadget
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next gadget
	 * @throws com.liferay.opensocial.NoSuchGadgetException if a gadget with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Gadget[] findByUuid_PrevAndNext(long gadgetId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchGadgetException, SystemException {
		Gadget gadget = findByPrimaryKey(gadgetId);

		Session session = null;

		try {
			session = openSession();

			Gadget[] array = new GadgetImpl[3];

			array[0] = getByUuid_PrevAndNext(session, gadget, uuid,
					orderByComparator, true);

			array[1] = gadget;

			array[2] = getByUuid_PrevAndNext(session, gadget, uuid,
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

	protected Gadget getByUuid_PrevAndNext(Session session, Gadget gadget,
		String uuid, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_GADGET_WHERE);

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

		else {
			query.append(GadgetModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByValues(gadget);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Gadget> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the gadgets that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching gadgets that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Gadget> filterFindByUuid(String uuid) throws SystemException {
		return filterFindByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the gadgets that the user has permission to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of gadgets
	 * @param end the upper bound of the range of gadgets (not inclusive)
	 * @return the range of matching gadgets that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Gadget> filterFindByUuid(String uuid, int start, int end)
		throws SystemException {
		return filterFindByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the gadgets that the user has permissions to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of gadgets
	 * @param end the upper bound of the range of gadgets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching gadgets that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Gadget> filterFindByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUuid(uuid, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_GADGET_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_GADGET_NO_INLINE_DISTINCT_WHERE_1);
		}

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

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_GADGET_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(GadgetModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(GadgetModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Gadget.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, GadgetImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, GadgetImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			if (uuid != null) {
				qPos.add(uuid);
			}

			return (List<Gadget>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the gadgets before and after the current gadget in the ordered set of gadgets that the user has permission to view where uuid = &#63;.
	 *
	 * @param gadgetId the primary key of the current gadget
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next gadget
	 * @throws com.liferay.opensocial.NoSuchGadgetException if a gadget with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Gadget[] filterFindByUuid_PrevAndNext(long gadgetId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchGadgetException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUuid_PrevAndNext(gadgetId, uuid, orderByComparator);
		}

		Gadget gadget = findByPrimaryKey(gadgetId);

		Session session = null;

		try {
			session = openSession();

			Gadget[] array = new GadgetImpl[3];

			array[0] = filterGetByUuid_PrevAndNext(session, gadget, uuid,
					orderByComparator, true);

			array[1] = gadget;

			array[2] = filterGetByUuid_PrevAndNext(session, gadget, uuid,
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

	protected Gadget filterGetByUuid_PrevAndNext(Session session,
		Gadget gadget, String uuid, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_GADGET_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_GADGET_NO_INLINE_DISTINCT_WHERE_1);
		}

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

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_GADGET_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(GadgetModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(GadgetModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Gadget.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, GadgetImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, GadgetImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		if (uuid != null) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(gadget);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Gadget> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the gadgets where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching gadgets
	 * @throws SystemException if a system exception occurred
	 */
	public List<Gadget> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the gadgets where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of gadgets
	 * @param end the upper bound of the range of gadgets (not inclusive)
	 * @return the range of matching gadgets
	 * @throws SystemException if a system exception occurred
	 */
	public List<Gadget> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the gadgets where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of gadgets
	 * @param end the upper bound of the range of gadgets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching gadgets
	 * @throws SystemException if a system exception occurred
	 */
	public List<Gadget> findByCompanyId(long companyId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId,
				
				start, end, orderByComparator
			};

		List<Gadget> list = (List<Gadget>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_GADGET_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(GadgetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<Gadget>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_COMPANYID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first gadget in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching gadget
	 * @throws com.liferay.opensocial.NoSuchGadgetException if a matching gadget could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Gadget findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchGadgetException, SystemException {
		List<Gadget> list = findByCompanyId(companyId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchGadgetException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last gadget in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching gadget
	 * @throws com.liferay.opensocial.NoSuchGadgetException if a matching gadget could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Gadget findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchGadgetException, SystemException {
		int count = countByCompanyId(companyId);

		List<Gadget> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchGadgetException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the gadgets before and after the current gadget in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param gadgetId the primary key of the current gadget
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next gadget
	 * @throws com.liferay.opensocial.NoSuchGadgetException if a gadget with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Gadget[] findByCompanyId_PrevAndNext(long gadgetId, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchGadgetException, SystemException {
		Gadget gadget = findByPrimaryKey(gadgetId);

		Session session = null;

		try {
			session = openSession();

			Gadget[] array = new GadgetImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, gadget, companyId,
					orderByComparator, true);

			array[1] = gadget;

			array[2] = getByCompanyId_PrevAndNext(session, gadget, companyId,
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

	protected Gadget getByCompanyId_PrevAndNext(Session session, Gadget gadget,
		long companyId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_GADGET_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

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

		else {
			query.append(GadgetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(gadget);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Gadget> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the gadgets that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching gadgets that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Gadget> filterFindByCompanyId(long companyId)
		throws SystemException {
		return filterFindByCompanyId(companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the gadgets that the user has permission to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of gadgets
	 * @param end the upper bound of the range of gadgets (not inclusive)
	 * @return the range of matching gadgets that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Gadget> filterFindByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return filterFindByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the gadgets that the user has permissions to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of gadgets
	 * @param end the upper bound of the range of gadgets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching gadgets that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Gadget> filterFindByCompanyId(long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByCompanyId(companyId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_GADGET_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_GADGET_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_GADGET_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(GadgetModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(GadgetModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Gadget.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, GadgetImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, GadgetImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			return (List<Gadget>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the gadgets before and after the current gadget in the ordered set of gadgets that the user has permission to view where companyId = &#63;.
	 *
	 * @param gadgetId the primary key of the current gadget
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next gadget
	 * @throws com.liferay.opensocial.NoSuchGadgetException if a gadget with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Gadget[] filterFindByCompanyId_PrevAndNext(long gadgetId,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchGadgetException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByCompanyId_PrevAndNext(gadgetId, companyId,
				orderByComparator);
		}

		Gadget gadget = findByPrimaryKey(gadgetId);

		Session session = null;

		try {
			session = openSession();

			Gadget[] array = new GadgetImpl[3];

			array[0] = filterGetByCompanyId_PrevAndNext(session, gadget,
					companyId, orderByComparator, true);

			array[1] = gadget;

			array[2] = filterGetByCompanyId_PrevAndNext(session, gadget,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Gadget filterGetByCompanyId_PrevAndNext(Session session,
		Gadget gadget, long companyId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_GADGET_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_GADGET_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_GADGET_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(GadgetModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(GadgetModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Gadget.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, GadgetImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, GadgetImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(gadget);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Gadget> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the gadget where companyId = &#63; and url = &#63; or throws a {@link com.liferay.opensocial.NoSuchGadgetException} if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param url the url
	 * @return the matching gadget
	 * @throws com.liferay.opensocial.NoSuchGadgetException if a matching gadget could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Gadget findByC_U(long companyId, String url)
		throws NoSuchGadgetException, SystemException {
		Gadget gadget = fetchByC_U(companyId, url);

		if (gadget == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", url=");
			msg.append(url);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchGadgetException(msg.toString());
		}

		return gadget;
	}

	/**
	 * Returns the gadget where companyId = &#63; and url = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param url the url
	 * @return the matching gadget, or <code>null</code> if a matching gadget could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Gadget fetchByC_U(long companyId, String url)
		throws SystemException {
		return fetchByC_U(companyId, url, true);
	}

	/**
	 * Returns the gadget where companyId = &#63; and url = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param url the url
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching gadget, or <code>null</code> if a matching gadget could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Gadget fetchByC_U(long companyId, String url,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { companyId, url };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_U,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_GADGET_WHERE);

			query.append(_FINDER_COLUMN_C_U_COMPANYID_2);

			if (url == null) {
				query.append(_FINDER_COLUMN_C_U_URL_1);
			}
			else {
				if (url.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_C_U_URL_3);
				}
				else {
					query.append(_FINDER_COLUMN_C_U_URL_2);
				}
			}

			query.append(GadgetModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (url != null) {
					qPos.add(url);
				}

				List<Gadget> list = q.list();

				result = list;

				Gadget gadget = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_U,
						finderArgs, list);
				}
				else {
					gadget = list.get(0);

					cacheResult(gadget);

					if ((gadget.getCompanyId() != companyId) ||
							(gadget.getUrl() == null) ||
							!gadget.getUrl().equals(url)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_U,
							finderArgs, gadget);
					}
				}

				return gadget;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_U,
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
				return (Gadget)result;
			}
		}
	}

	/**
	 * Returns all the gadgets.
	 *
	 * @return the gadgets
	 * @throws SystemException if a system exception occurred
	 */
	public List<Gadget> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the gadgets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of gadgets
	 * @param end the upper bound of the range of gadgets (not inclusive)
	 * @return the range of gadgets
	 * @throws SystemException if a system exception occurred
	 */
	public List<Gadget> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the gadgets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of gadgets
	 * @param end the upper bound of the range of gadgets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of gadgets
	 * @throws SystemException if a system exception occurred
	 */
	public List<Gadget> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		List<Gadget> list = (List<Gadget>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_GADGET);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_GADGET.concat(GadgetModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Gadget>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Gadget>)QueryUtil.list(q, getDialect(), start,
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
	 * Removes all the gadgets where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (Gadget gadget : findByUuid(uuid)) {
			gadgetPersistence.remove(gadget);
		}
	}

	/**
	 * Removes all the gadgets where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (Gadget gadget : findByCompanyId(companyId)) {
			gadgetPersistence.remove(gadget);
		}
	}

	/**
	 * Removes the gadget where companyId = &#63; and url = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param url the url
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_U(long companyId, String url)
		throws NoSuchGadgetException, SystemException {
		Gadget gadget = findByC_U(companyId, url);

		gadgetPersistence.remove(gadget);
	}

	/**
	 * Removes all the gadgets from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Gadget gadget : findAll()) {
			gadgetPersistence.remove(gadget);
		}
	}

	/**
	 * Returns the number of gadgets where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching gadgets
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_GADGET_WHERE);

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
	 * Returns the number of gadgets that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching gadgets that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByUuid(String uuid) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByUuid(uuid);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_GADGET_WHERE);

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

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Gadget.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if (uuid != null) {
				qPos.add(uuid);
			}

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of gadgets where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching gadgets
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_GADGET_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPANYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of gadgets that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching gadgets that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByCompanyId(long companyId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByCompanyId(companyId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_GADGET_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Gadget.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of gadgets where companyId = &#63; and url = &#63;.
	 *
	 * @param companyId the company ID
	 * @param url the url
	 * @return the number of matching gadgets
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_U(long companyId, String url) throws SystemException {
		Object[] finderArgs = new Object[] { companyId, url };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_U,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_GADGET_WHERE);

			query.append(_FINDER_COLUMN_C_U_COMPANYID_2);

			if (url == null) {
				query.append(_FINDER_COLUMN_C_U_URL_1);
			}
			else {
				if (url.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_C_U_URL_3);
				}
				else {
					query.append(_FINDER_COLUMN_C_U_URL_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (url != null) {
					qPos.add(url);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_U, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of gadgets.
	 *
	 * @return the number of gadgets
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_GADGET);

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
	 * Initializes the gadget persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.opensocial.model.Gadget")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Gadget>> listenersList = new ArrayList<ModelListener<Gadget>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Gadget>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(GadgetImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = GadgetPersistence.class)
	protected GadgetPersistence gadgetPersistence;
	@BeanReference(type = OAuthConsumerPersistence.class)
	protected OAuthConsumerPersistence oAuthConsumerPersistence;
	@BeanReference(type = OAuthTokenPersistence.class)
	protected OAuthTokenPersistence oAuthTokenPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_GADGET = "SELECT gadget FROM Gadget gadget";
	private static final String _SQL_SELECT_GADGET_WHERE = "SELECT gadget FROM Gadget gadget WHERE ";
	private static final String _SQL_COUNT_GADGET = "SELECT COUNT(gadget) FROM Gadget gadget";
	private static final String _SQL_COUNT_GADGET_WHERE = "SELECT COUNT(gadget) FROM Gadget gadget WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "gadget.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "gadget.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(gadget.uuid IS NULL OR gadget.uuid = ?)";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "gadget.companyId = ?";
	private static final String _FINDER_COLUMN_C_U_COMPANYID_2 = "gadget.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_U_URL_1 = "gadget.url IS NULL";
	private static final String _FINDER_COLUMN_C_U_URL_2 = "gadget.url = ?";
	private static final String _FINDER_COLUMN_C_U_URL_3 = "(gadget.url IS NULL OR gadget.url = ?)";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "gadget.gadgetId";
	private static final String _FILTER_SQL_SELECT_GADGET_WHERE = "SELECT DISTINCT {gadget.*} FROM OpenSocial_Gadget gadget WHERE ";
	private static final String _FILTER_SQL_SELECT_GADGET_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {OpenSocial_Gadget.*} FROM (SELECT DISTINCT gadget.gadgetId FROM OpenSocial_Gadget gadget WHERE ";
	private static final String _FILTER_SQL_SELECT_GADGET_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN OpenSocial_Gadget ON TEMP_TABLE.gadgetId = OpenSocial_Gadget.gadgetId";
	private static final String _FILTER_SQL_COUNT_GADGET_WHERE = "SELECT COUNT(DISTINCT gadget.gadgetId) AS COUNT_VALUE FROM OpenSocial_Gadget gadget WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "gadget";
	private static final String _FILTER_ENTITY_TABLE = "OpenSocial_Gadget";
	private static final String _ORDER_BY_ENTITY_ALIAS = "gadget.";
	private static final String _ORDER_BY_ENTITY_TABLE = "OpenSocial_Gadget.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Gadget exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Gadget exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(GadgetPersistenceImpl.class);
	private static Gadget _nullGadget = new GadgetImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Gadget> toCacheModel() {
				return _nullGadgetCacheModel;
			}
		};

	private static CacheModel<Gadget> _nullGadgetCacheModel = new CacheModel<Gadget>() {
			public Gadget toEntityModel() {
				return _nullGadget;
			}
		};
}