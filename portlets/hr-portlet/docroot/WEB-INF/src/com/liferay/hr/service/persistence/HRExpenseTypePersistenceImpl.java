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

package com.liferay.hr.service.persistence;

import com.liferay.hr.NoSuchExpenseTypeException;
import com.liferay.hr.model.HRExpenseType;
import com.liferay.hr.model.impl.HRExpenseTypeImpl;
import com.liferay.hr.model.impl.HRExpenseTypeModelImpl;

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
 * The persistence implementation for the h r expense type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRExpenseTypePersistence
 * @see HRExpenseTypeUtil
 * @generated
 */
public class HRExpenseTypePersistenceImpl extends BasePersistenceImpl<HRExpenseType>
	implements HRExpenseTypePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRExpenseTypeUtil} to access the h r expense type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRExpenseTypeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_N = new FinderPath(HRExpenseTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseTypeModelImpl.FINDER_CACHE_ENABLED,
			HRExpenseTypeImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByG_N",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_G_N = new FinderPath(HRExpenseTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByG_N",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRExpenseTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseTypeModelImpl.FINDER_CACHE_ENABLED,
			HRExpenseTypeImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRExpenseTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r expense type in the entity cache if it is enabled.
	 *
	 * @param hrExpenseType the h r expense type
	 */
	public void cacheResult(HRExpenseType hrExpenseType) {
		EntityCacheUtil.putResult(HRExpenseTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseTypeImpl.class, hrExpenseType.getPrimaryKey(),
			hrExpenseType);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_N,
			new Object[] {
				Long.valueOf(hrExpenseType.getGroupId()),
				
			hrExpenseType.getName()
			}, hrExpenseType);

		hrExpenseType.resetOriginalValues();
	}

	/**
	 * Caches the h r expense types in the entity cache if it is enabled.
	 *
	 * @param hrExpenseTypes the h r expense types
	 */
	public void cacheResult(List<HRExpenseType> hrExpenseTypes) {
		for (HRExpenseType hrExpenseType : hrExpenseTypes) {
			if (EntityCacheUtil.getResult(
						HRExpenseTypeModelImpl.ENTITY_CACHE_ENABLED,
						HRExpenseTypeImpl.class, hrExpenseType.getPrimaryKey(),
						this) == null) {
				cacheResult(hrExpenseType);
			}
		}
	}

	/**
	 * Clears the cache for all h r expense types.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRExpenseTypeImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRExpenseTypeImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r expense type.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRExpenseType hrExpenseType) {
		EntityCacheUtil.removeResult(HRExpenseTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseTypeImpl.class, hrExpenseType.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_N,
			new Object[] {
				Long.valueOf(hrExpenseType.getGroupId()),
				
			hrExpenseType.getName()
			});
	}

	/**
	 * Creates a new h r expense type with the primary key. Does not add the h r expense type to the database.
	 *
	 * @param hrExpenseTypeId the primary key for the new h r expense type
	 * @return the new h r expense type
	 */
	public HRExpenseType create(long hrExpenseTypeId) {
		HRExpenseType hrExpenseType = new HRExpenseTypeImpl();

		hrExpenseType.setNew(true);
		hrExpenseType.setPrimaryKey(hrExpenseTypeId);

		return hrExpenseType;
	}

	/**
	 * Removes the h r expense type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r expense type
	 * @return the h r expense type that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r expense type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRExpenseType remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r expense type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrExpenseTypeId the primary key of the h r expense type
	 * @return the h r expense type that was removed
	 * @throws com.liferay.hr.NoSuchExpenseTypeException if a h r expense type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpenseType remove(long hrExpenseTypeId)
		throws NoSuchExpenseTypeException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRExpenseType hrExpenseType = (HRExpenseType)session.get(HRExpenseTypeImpl.class,
					Long.valueOf(hrExpenseTypeId));

			if (hrExpenseType == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						hrExpenseTypeId);
				}

				throw new NoSuchExpenseTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrExpenseTypeId);
			}

			return hrExpenseTypePersistence.remove(hrExpenseType);
		}
		catch (NoSuchExpenseTypeException nsee) {
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
	 * Removes the h r expense type from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrExpenseType the h r expense type
	 * @return the h r expense type that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRExpenseType remove(HRExpenseType hrExpenseType)
		throws SystemException {
		return super.remove(hrExpenseType);
	}

	@Override
	protected HRExpenseType removeImpl(HRExpenseType hrExpenseType)
		throws SystemException {
		hrExpenseType = toUnwrappedModel(hrExpenseType);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrExpenseType);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		HRExpenseTypeModelImpl hrExpenseTypeModelImpl = (HRExpenseTypeModelImpl)hrExpenseType;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_N,
			new Object[] {
				Long.valueOf(hrExpenseTypeModelImpl.getGroupId()),
				
			hrExpenseTypeModelImpl.getName()
			});

		EntityCacheUtil.removeResult(HRExpenseTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseTypeImpl.class, hrExpenseType.getPrimaryKey());

		return hrExpenseType;
	}

	@Override
	public HRExpenseType updateImpl(
		com.liferay.hr.model.HRExpenseType hrExpenseType, boolean merge)
		throws SystemException {
		hrExpenseType = toUnwrappedModel(hrExpenseType);

		boolean isNew = hrExpenseType.isNew();

		HRExpenseTypeModelImpl hrExpenseTypeModelImpl = (HRExpenseTypeModelImpl)hrExpenseType;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrExpenseType, merge);

			hrExpenseType.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRExpenseTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRExpenseTypeImpl.class, hrExpenseType.getPrimaryKey(),
			hrExpenseType);

		if (!isNew &&
				((hrExpenseType.getGroupId() != hrExpenseTypeModelImpl.getOriginalGroupId()) ||
				!Validator.equals(hrExpenseType.getName(),
					hrExpenseTypeModelImpl.getOriginalName()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_N,
				new Object[] {
					Long.valueOf(hrExpenseTypeModelImpl.getOriginalGroupId()),
					
				hrExpenseTypeModelImpl.getOriginalName()
				});
		}

		if (isNew ||
				((hrExpenseType.getGroupId() != hrExpenseTypeModelImpl.getOriginalGroupId()) ||
				!Validator.equals(hrExpenseType.getName(),
					hrExpenseTypeModelImpl.getOriginalName()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_N,
				new Object[] {
					Long.valueOf(hrExpenseType.getGroupId()),
					
				hrExpenseType.getName()
				}, hrExpenseType);
		}

		return hrExpenseType;
	}

	protected HRExpenseType toUnwrappedModel(HRExpenseType hrExpenseType) {
		if (hrExpenseType instanceof HRExpenseTypeImpl) {
			return hrExpenseType;
		}

		HRExpenseTypeImpl hrExpenseTypeImpl = new HRExpenseTypeImpl();

		hrExpenseTypeImpl.setNew(hrExpenseType.isNew());
		hrExpenseTypeImpl.setPrimaryKey(hrExpenseType.getPrimaryKey());

		hrExpenseTypeImpl.setHrExpenseTypeId(hrExpenseType.getHrExpenseTypeId());
		hrExpenseTypeImpl.setGroupId(hrExpenseType.getGroupId());
		hrExpenseTypeImpl.setCompanyId(hrExpenseType.getCompanyId());
		hrExpenseTypeImpl.setUserId(hrExpenseType.getUserId());
		hrExpenseTypeImpl.setUserName(hrExpenseType.getUserName());
		hrExpenseTypeImpl.setCreateDate(hrExpenseType.getCreateDate());
		hrExpenseTypeImpl.setModifiedDate(hrExpenseType.getModifiedDate());
		hrExpenseTypeImpl.setName(hrExpenseType.getName());
		hrExpenseTypeImpl.setDescription(hrExpenseType.getDescription());

		return hrExpenseTypeImpl;
	}

	/**
	 * Returns the h r expense type with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r expense type
	 * @return the h r expense type
	 * @throws com.liferay.portal.NoSuchModelException if a h r expense type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRExpenseType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r expense type with the primary key or throws a {@link com.liferay.hr.NoSuchExpenseTypeException} if it could not be found.
	 *
	 * @param hrExpenseTypeId the primary key of the h r expense type
	 * @return the h r expense type
	 * @throws com.liferay.hr.NoSuchExpenseTypeException if a h r expense type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpenseType findByPrimaryKey(long hrExpenseTypeId)
		throws NoSuchExpenseTypeException, SystemException {
		HRExpenseType hrExpenseType = fetchByPrimaryKey(hrExpenseTypeId);

		if (hrExpenseType == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrExpenseTypeId);
			}

			throw new NoSuchExpenseTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrExpenseTypeId);
		}

		return hrExpenseType;
	}

	/**
	 * Returns the h r expense type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r expense type
	 * @return the h r expense type, or <code>null</code> if a h r expense type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRExpenseType fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r expense type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrExpenseTypeId the primary key of the h r expense type
	 * @return the h r expense type, or <code>null</code> if a h r expense type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpenseType fetchByPrimaryKey(long hrExpenseTypeId)
		throws SystemException {
		HRExpenseType hrExpenseType = (HRExpenseType)EntityCacheUtil.getResult(HRExpenseTypeModelImpl.ENTITY_CACHE_ENABLED,
				HRExpenseTypeImpl.class, hrExpenseTypeId, this);

		if (hrExpenseType == _nullHRExpenseType) {
			return null;
		}

		if (hrExpenseType == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrExpenseType = (HRExpenseType)session.get(HRExpenseTypeImpl.class,
						Long.valueOf(hrExpenseTypeId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrExpenseType != null) {
					cacheResult(hrExpenseType);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRExpenseTypeModelImpl.ENTITY_CACHE_ENABLED,
						HRExpenseTypeImpl.class, hrExpenseTypeId,
						_nullHRExpenseType);
				}

				closeSession(session);
			}
		}

		return hrExpenseType;
	}

	/**
	 * Returns the h r expense type where groupId = &#63; and name = &#63; or throws a {@link com.liferay.hr.NoSuchExpenseTypeException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the matching h r expense type
	 * @throws com.liferay.hr.NoSuchExpenseTypeException if a matching h r expense type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpenseType findByG_N(long groupId, String name)
		throws NoSuchExpenseTypeException, SystemException {
		HRExpenseType hrExpenseType = fetchByG_N(groupId, name);

		if (hrExpenseType == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchExpenseTypeException(msg.toString());
		}

		return hrExpenseType;
	}

	/**
	 * Returns the h r expense type where groupId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the matching h r expense type, or <code>null</code> if a matching h r expense type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpenseType fetchByG_N(long groupId, String name)
		throws SystemException {
		return fetchByG_N(groupId, name, true);
	}

	/**
	 * Returns the h r expense type where groupId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching h r expense type, or <code>null</code> if a matching h r expense type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpenseType fetchByG_N(long groupId, String name,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, name };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_G_N,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_HREXPENSETYPE_WHERE);

			query.append(_FINDER_COLUMN_G_N_GROUPID_2);

			if (name == null) {
				query.append(_FINDER_COLUMN_G_N_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_N_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_N_NAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (name != null) {
					qPos.add(name);
				}

				List<HRExpenseType> list = q.list();

				result = list;

				HRExpenseType hrExpenseType = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_N,
						finderArgs, list);
				}
				else {
					hrExpenseType = list.get(0);

					cacheResult(hrExpenseType);

					if ((hrExpenseType.getGroupId() != groupId) ||
							(hrExpenseType.getName() == null) ||
							!hrExpenseType.getName().equals(name)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_N,
							finderArgs, hrExpenseType);
					}
				}

				return hrExpenseType;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_N,
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
				return (HRExpenseType)result;
			}
		}
	}

	/**
	 * Returns all the h r expense types.
	 *
	 * @return the h r expense types
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRExpenseType> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r expense types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r expense types
	 * @param end the upper bound of the range of h r expense types (not inclusive)
	 * @return the range of h r expense types
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRExpenseType> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r expense types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r expense types
	 * @param end the upper bound of the range of h r expense types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r expense types
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRExpenseType> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRExpenseType> list = (List<HRExpenseType>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HREXPENSETYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HREXPENSETYPE;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRExpenseType>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRExpenseType>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes the h r expense type where groupId = &#63; and name = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_N(long groupId, String name)
		throws NoSuchExpenseTypeException, SystemException {
		HRExpenseType hrExpenseType = findByG_N(groupId, name);

		hrExpenseTypePersistence.remove(hrExpenseType);
	}

	/**
	 * Removes all the h r expense types from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRExpenseType hrExpenseType : findAll()) {
			hrExpenseTypePersistence.remove(hrExpenseType);
		}
	}

	/**
	 * Returns the number of h r expense types where groupId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param name the name
	 * @return the number of matching h r expense types
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_N(long groupId, String name) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, name };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_N,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_HREXPENSETYPE_WHERE);

			query.append(_FINDER_COLUMN_G_N_GROUPID_2);

			if (name == null) {
				query.append(_FINDER_COLUMN_G_N_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_N_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_N_NAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (name != null) {
					qPos.add(name);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_N, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of h r expense types.
	 *
	 * @return the number of h r expense types
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

				Query q = session.createQuery(_SQL_COUNT_HREXPENSETYPE);

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
	 * Initializes the h r expense type persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRExpenseType")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRExpenseType>> listenersList = new ArrayList<ModelListener<HRExpenseType>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRExpenseType>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRExpenseTypeImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = HRAssetPersistence.class)
	protected HRAssetPersistence hrAssetPersistence;
	@BeanReference(type = HRAssetCheckoutPersistence.class)
	protected HRAssetCheckoutPersistence hrAssetCheckoutPersistence;
	@BeanReference(type = HRAssetDefinitionPersistence.class)
	protected HRAssetDefinitionPersistence hrAssetDefinitionPersistence;
	@BeanReference(type = HRAssetProductPersistence.class)
	protected HRAssetProductPersistence hrAssetProductPersistence;
	@BeanReference(type = HRAssetTypePersistence.class)
	protected HRAssetTypePersistence hrAssetTypePersistence;
	@BeanReference(type = HRAssetVendorPersistence.class)
	protected HRAssetVendorPersistence hrAssetVendorPersistence;
	@BeanReference(type = HRBillabilityPersistence.class)
	protected HRBillabilityPersistence hrBillabilityPersistence;
	@BeanReference(type = HRBranchPersistence.class)
	protected HRBranchPersistence hrBranchPersistence;
	@BeanReference(type = HRClientPersistence.class)
	protected HRClientPersistence hrClientPersistence;
	@BeanReference(type = HREmploymentTypePersistence.class)
	protected HREmploymentTypePersistence hrEmploymentTypePersistence;
	@BeanReference(type = HRExpensePersistence.class)
	protected HRExpensePersistence hrExpensePersistence;
	@BeanReference(type = HRExpenseAccountPersistence.class)
	protected HRExpenseAccountPersistence hrExpenseAccountPersistence;
	@BeanReference(type = HRExpenseCurrencyPersistence.class)
	protected HRExpenseCurrencyPersistence hrExpenseCurrencyPersistence;
	@BeanReference(type = HRExpenseCurrencyConversionPersistence.class)
	protected HRExpenseCurrencyConversionPersistence hrExpenseCurrencyConversionPersistence;
	@BeanReference(type = HRExpenseTypePersistence.class)
	protected HRExpenseTypePersistence hrExpenseTypePersistence;
	@BeanReference(type = HRHolidayPersistence.class)
	protected HRHolidayPersistence hrHolidayPersistence;
	@BeanReference(type = HRJobTitlePersistence.class)
	protected HRJobTitlePersistence hrJobTitlePersistence;
	@BeanReference(type = HROfficePersistence.class)
	protected HROfficePersistence hrOfficePersistence;
	@BeanReference(type = HRProjectPersistence.class)
	protected HRProjectPersistence hrProjectPersistence;
	@BeanReference(type = HRProjectBillingRatePersistence.class)
	protected HRProjectBillingRatePersistence hrProjectBillingRatePersistence;
	@BeanReference(type = HRProjectRolePersistence.class)
	protected HRProjectRolePersistence hrProjectRolePersistence;
	@BeanReference(type = HRProjectStatusPersistence.class)
	protected HRProjectStatusPersistence hrProjectStatusPersistence;
	@BeanReference(type = HRTaskPersistence.class)
	protected HRTaskPersistence hrTaskPersistence;
	@BeanReference(type = HRTaskStatusPersistence.class)
	protected HRTaskStatusPersistence hrTaskStatusPersistence;
	@BeanReference(type = HRTerminationTypePersistence.class)
	protected HRTerminationTypePersistence hrTerminationTypePersistence;
	@BeanReference(type = HRTimeOffPersistence.class)
	protected HRTimeOffPersistence hrTimeOffPersistence;
	@BeanReference(type = HRTimeOffFrequencyTypePersistence.class)
	protected HRTimeOffFrequencyTypePersistence hrTimeOffFrequencyTypePersistence;
	@BeanReference(type = HRTimeOffPolicyPersistence.class)
	protected HRTimeOffPolicyPersistence hrTimeOffPolicyPersistence;
	@BeanReference(type = HRTimeOffTypePersistence.class)
	protected HRTimeOffTypePersistence hrTimeOffTypePersistence;
	@BeanReference(type = HRTimeSheetPersistence.class)
	protected HRTimeSheetPersistence hrTimeSheetPersistence;
	@BeanReference(type = HRTimeSheetDayPersistence.class)
	protected HRTimeSheetDayPersistence hrTimeSheetDayPersistence;
	@BeanReference(type = HRTimeSheetHoursPerDayPersistence.class)
	protected HRTimeSheetHoursPerDayPersistence hrTimeSheetHoursPerDayPersistence;
	@BeanReference(type = HRUserPersistence.class)
	protected HRUserPersistence hrUserPersistence;
	@BeanReference(type = HRUserHistoryPersistence.class)
	protected HRUserHistoryPersistence hrUserHistoryPersistence;
	@BeanReference(type = HRUserProjectPersistence.class)
	protected HRUserProjectPersistence hrUserProjectPersistence;
	@BeanReference(type = HRUserTaskPersistence.class)
	protected HRUserTaskPersistence hrUserTaskPersistence;
	@BeanReference(type = HRUserTimeOffPersistence.class)
	protected HRUserTimeOffPersistence hrUserTimeOffPersistence;
	@BeanReference(type = HRWageTypePersistence.class)
	protected HRWageTypePersistence hrWageTypePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_HREXPENSETYPE = "SELECT hrExpenseType FROM HRExpenseType hrExpenseType";
	private static final String _SQL_SELECT_HREXPENSETYPE_WHERE = "SELECT hrExpenseType FROM HRExpenseType hrExpenseType WHERE ";
	private static final String _SQL_COUNT_HREXPENSETYPE = "SELECT COUNT(hrExpenseType) FROM HRExpenseType hrExpenseType";
	private static final String _SQL_COUNT_HREXPENSETYPE_WHERE = "SELECT COUNT(hrExpenseType) FROM HRExpenseType hrExpenseType WHERE ";
	private static final String _FINDER_COLUMN_G_N_GROUPID_2 = "hrExpenseType.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_N_NAME_1 = "hrExpenseType.name IS NULL";
	private static final String _FINDER_COLUMN_G_N_NAME_2 = "hrExpenseType.name = ?";
	private static final String _FINDER_COLUMN_G_N_NAME_3 = "(hrExpenseType.name IS NULL OR hrExpenseType.name = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrExpenseType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRExpenseType exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No HRExpenseType exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRExpenseTypePersistenceImpl.class);
	private static HRExpenseType _nullHRExpenseType = new HRExpenseTypeImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRExpenseType> toCacheModel() {
				return _nullHRExpenseTypeCacheModel;
			}
		};

	private static CacheModel<HRExpenseType> _nullHRExpenseTypeCacheModel = new CacheModel<HRExpenseType>() {
			public HRExpenseType toEntityModel() {
				return _nullHRExpenseType;
			}
		};
}