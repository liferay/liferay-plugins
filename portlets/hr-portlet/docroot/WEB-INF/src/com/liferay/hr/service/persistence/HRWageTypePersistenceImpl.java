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

import com.liferay.hr.NoSuchWageTypeException;
import com.liferay.hr.model.HRWageType;
import com.liferay.hr.model.impl.HRWageTypeImpl;
import com.liferay.hr.model.impl.HRWageTypeModelImpl;

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
 * The persistence implementation for the h r wage type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRWageTypePersistence
 * @see HRWageTypeUtil
 * @generated
 */
public class HRWageTypePersistenceImpl extends BasePersistenceImpl<HRWageType>
	implements HRWageTypePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRWageTypeUtil} to access the h r wage type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRWageTypeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_C = new FinderPath(HRWageTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRWageTypeModelImpl.FINDER_CACHE_ENABLED, HRWageTypeImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByG_C",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_G_C = new FinderPath(HRWageTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRWageTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByG_C",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRWageTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRWageTypeModelImpl.FINDER_CACHE_ENABLED, HRWageTypeImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRWageTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRWageTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r wage type in the entity cache if it is enabled.
	 *
	 * @param hrWageType the h r wage type
	 */
	public void cacheResult(HRWageType hrWageType) {
		EntityCacheUtil.putResult(HRWageTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRWageTypeImpl.class, hrWageType.getPrimaryKey(), hrWageType);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
			new Object[] {
				Long.valueOf(hrWageType.getGroupId()),
				
			hrWageType.getCode()
			}, hrWageType);

		hrWageType.resetOriginalValues();
	}

	/**
	 * Caches the h r wage types in the entity cache if it is enabled.
	 *
	 * @param hrWageTypes the h r wage types
	 */
	public void cacheResult(List<HRWageType> hrWageTypes) {
		for (HRWageType hrWageType : hrWageTypes) {
			if (EntityCacheUtil.getResult(
						HRWageTypeModelImpl.ENTITY_CACHE_ENABLED,
						HRWageTypeImpl.class, hrWageType.getPrimaryKey(), this) == null) {
				cacheResult(hrWageType);
			}
		}
	}

	/**
	 * Clears the cache for all h r wage types.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRWageTypeImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRWageTypeImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r wage type.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRWageType hrWageType) {
		EntityCacheUtil.removeResult(HRWageTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRWageTypeImpl.class, hrWageType.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_C,
			new Object[] {
				Long.valueOf(hrWageType.getGroupId()),
				
			hrWageType.getCode()
			});
	}

	/**
	 * Creates a new h r wage type with the primary key. Does not add the h r wage type to the database.
	 *
	 * @param hrWageTypeId the primary key for the new h r wage type
	 * @return the new h r wage type
	 */
	public HRWageType create(long hrWageTypeId) {
		HRWageType hrWageType = new HRWageTypeImpl();

		hrWageType.setNew(true);
		hrWageType.setPrimaryKey(hrWageTypeId);

		return hrWageType;
	}

	/**
	 * Removes the h r wage type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r wage type
	 * @return the h r wage type that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r wage type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRWageType remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r wage type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrWageTypeId the primary key of the h r wage type
	 * @return the h r wage type that was removed
	 * @throws com.liferay.hr.NoSuchWageTypeException if a h r wage type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRWageType remove(long hrWageTypeId)
		throws NoSuchWageTypeException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRWageType hrWageType = (HRWageType)session.get(HRWageTypeImpl.class,
					Long.valueOf(hrWageTypeId));

			if (hrWageType == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrWageTypeId);
				}

				throw new NoSuchWageTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrWageTypeId);
			}

			return hrWageTypePersistence.remove(hrWageType);
		}
		catch (NoSuchWageTypeException nsee) {
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
	 * Removes the h r wage type from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrWageType the h r wage type
	 * @return the h r wage type that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRWageType remove(HRWageType hrWageType) throws SystemException {
		return super.remove(hrWageType);
	}

	@Override
	protected HRWageType removeImpl(HRWageType hrWageType)
		throws SystemException {
		hrWageType = toUnwrappedModel(hrWageType);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrWageType);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		HRWageTypeModelImpl hrWageTypeModelImpl = (HRWageTypeModelImpl)hrWageType;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_C,
			new Object[] {
				Long.valueOf(hrWageTypeModelImpl.getGroupId()),
				
			hrWageTypeModelImpl.getCode()
			});

		EntityCacheUtil.removeResult(HRWageTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRWageTypeImpl.class, hrWageType.getPrimaryKey());

		return hrWageType;
	}

	@Override
	public HRWageType updateImpl(com.liferay.hr.model.HRWageType hrWageType,
		boolean merge) throws SystemException {
		hrWageType = toUnwrappedModel(hrWageType);

		boolean isNew = hrWageType.isNew();

		HRWageTypeModelImpl hrWageTypeModelImpl = (HRWageTypeModelImpl)hrWageType;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrWageType, merge);

			hrWageType.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRWageTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRWageTypeImpl.class, hrWageType.getPrimaryKey(), hrWageType);

		if (!isNew &&
				((hrWageType.getGroupId() != hrWageTypeModelImpl.getOriginalGroupId()) ||
				!Validator.equals(hrWageType.getCode(),
					hrWageTypeModelImpl.getOriginalCode()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_C,
				new Object[] {
					Long.valueOf(hrWageTypeModelImpl.getOriginalGroupId()),
					
				hrWageTypeModelImpl.getOriginalCode()
				});
		}

		if (isNew ||
				((hrWageType.getGroupId() != hrWageTypeModelImpl.getOriginalGroupId()) ||
				!Validator.equals(hrWageType.getCode(),
					hrWageTypeModelImpl.getOriginalCode()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
				new Object[] {
					Long.valueOf(hrWageType.getGroupId()),
					
				hrWageType.getCode()
				}, hrWageType);
		}

		return hrWageType;
	}

	protected HRWageType toUnwrappedModel(HRWageType hrWageType) {
		if (hrWageType instanceof HRWageTypeImpl) {
			return hrWageType;
		}

		HRWageTypeImpl hrWageTypeImpl = new HRWageTypeImpl();

		hrWageTypeImpl.setNew(hrWageType.isNew());
		hrWageTypeImpl.setPrimaryKey(hrWageType.getPrimaryKey());

		hrWageTypeImpl.setHrWageTypeId(hrWageType.getHrWageTypeId());
		hrWageTypeImpl.setGroupId(hrWageType.getGroupId());
		hrWageTypeImpl.setCompanyId(hrWageType.getCompanyId());
		hrWageTypeImpl.setUserId(hrWageType.getUserId());
		hrWageTypeImpl.setUserName(hrWageType.getUserName());
		hrWageTypeImpl.setCreateDate(hrWageType.getCreateDate());
		hrWageTypeImpl.setModifiedDate(hrWageType.getModifiedDate());
		hrWageTypeImpl.setCode(hrWageType.getCode());
		hrWageTypeImpl.setName(hrWageType.getName());
		hrWageTypeImpl.setDescription(hrWageType.getDescription());

		return hrWageTypeImpl;
	}

	/**
	 * Returns the h r wage type with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r wage type
	 * @return the h r wage type
	 * @throws com.liferay.portal.NoSuchModelException if a h r wage type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRWageType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r wage type with the primary key or throws a {@link com.liferay.hr.NoSuchWageTypeException} if it could not be found.
	 *
	 * @param hrWageTypeId the primary key of the h r wage type
	 * @return the h r wage type
	 * @throws com.liferay.hr.NoSuchWageTypeException if a h r wage type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRWageType findByPrimaryKey(long hrWageTypeId)
		throws NoSuchWageTypeException, SystemException {
		HRWageType hrWageType = fetchByPrimaryKey(hrWageTypeId);

		if (hrWageType == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrWageTypeId);
			}

			throw new NoSuchWageTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrWageTypeId);
		}

		return hrWageType;
	}

	/**
	 * Returns the h r wage type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r wage type
	 * @return the h r wage type, or <code>null</code> if a h r wage type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRWageType fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r wage type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrWageTypeId the primary key of the h r wage type
	 * @return the h r wage type, or <code>null</code> if a h r wage type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRWageType fetchByPrimaryKey(long hrWageTypeId)
		throws SystemException {
		HRWageType hrWageType = (HRWageType)EntityCacheUtil.getResult(HRWageTypeModelImpl.ENTITY_CACHE_ENABLED,
				HRWageTypeImpl.class, hrWageTypeId, this);

		if (hrWageType == _nullHRWageType) {
			return null;
		}

		if (hrWageType == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrWageType = (HRWageType)session.get(HRWageTypeImpl.class,
						Long.valueOf(hrWageTypeId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrWageType != null) {
					cacheResult(hrWageType);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRWageTypeModelImpl.ENTITY_CACHE_ENABLED,
						HRWageTypeImpl.class, hrWageTypeId, _nullHRWageType);
				}

				closeSession(session);
			}
		}

		return hrWageType;
	}

	/**
	 * Returns the h r wage type where groupId = &#63; and code = &#63; or throws a {@link com.liferay.hr.NoSuchWageTypeException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @return the matching h r wage type
	 * @throws com.liferay.hr.NoSuchWageTypeException if a matching h r wage type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRWageType findByG_C(long groupId, String code)
		throws NoSuchWageTypeException, SystemException {
		HRWageType hrWageType = fetchByG_C(groupId, code);

		if (hrWageType == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", code=");
			msg.append(code);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchWageTypeException(msg.toString());
		}

		return hrWageType;
	}

	/**
	 * Returns the h r wage type where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @return the matching h r wage type, or <code>null</code> if a matching h r wage type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRWageType fetchByG_C(long groupId, String code)
		throws SystemException {
		return fetchByG_C(groupId, code, true);
	}

	/**
	 * Returns the h r wage type where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching h r wage type, or <code>null</code> if a matching h r wage type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRWageType fetchByG_C(long groupId, String code,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, code };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_G_C,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_HRWAGETYPE_WHERE);

			query.append(_FINDER_COLUMN_G_C_GROUPID_2);

			if (code == null) {
				query.append(_FINDER_COLUMN_G_C_CODE_1);
			}
			else {
				if (code.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_C_CODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_C_CODE_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (code != null) {
					qPos.add(code);
				}

				List<HRWageType> list = q.list();

				result = list;

				HRWageType hrWageType = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
						finderArgs, list);
				}
				else {
					hrWageType = list.get(0);

					cacheResult(hrWageType);

					if ((hrWageType.getGroupId() != groupId) ||
							(hrWageType.getCode() == null) ||
							!hrWageType.getCode().equals(code)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
							finderArgs, hrWageType);
					}
				}

				return hrWageType;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_C,
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
				return (HRWageType)result;
			}
		}
	}

	/**
	 * Returns all the h r wage types.
	 *
	 * @return the h r wage types
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRWageType> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r wage types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r wage types
	 * @param end the upper bound of the range of h r wage types (not inclusive)
	 * @return the range of h r wage types
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRWageType> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r wage types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r wage types
	 * @param end the upper bound of the range of h r wage types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r wage types
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRWageType> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRWageType> list = (List<HRWageType>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRWAGETYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRWAGETYPE;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRWageType>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRWageType>)QueryUtil.list(q, getDialect(),
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
	 * Removes the h r wage type where groupId = &#63; and code = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_C(long groupId, String code)
		throws NoSuchWageTypeException, SystemException {
		HRWageType hrWageType = findByG_C(groupId, code);

		hrWageTypePersistence.remove(hrWageType);
	}

	/**
	 * Removes all the h r wage types from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRWageType hrWageType : findAll()) {
			hrWageTypePersistence.remove(hrWageType);
		}
	}

	/**
	 * Returns the number of h r wage types where groupId = &#63; and code = &#63;.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @return the number of matching h r wage types
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_C(long groupId, String code) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, code };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_HRWAGETYPE_WHERE);

			query.append(_FINDER_COLUMN_G_C_GROUPID_2);

			if (code == null) {
				query.append(_FINDER_COLUMN_G_C_CODE_1);
			}
			else {
				if (code.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_C_CODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_G_C_CODE_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (code != null) {
					qPos.add(code);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_C, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of h r wage types.
	 *
	 * @return the number of h r wage types
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

				Query q = session.createQuery(_SQL_COUNT_HRWAGETYPE);

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
	 * Initializes the h r wage type persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRWageType")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRWageType>> listenersList = new ArrayList<ModelListener<HRWageType>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRWageType>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRWageTypeImpl.class.getName());
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
	private static final String _SQL_SELECT_HRWAGETYPE = "SELECT hrWageType FROM HRWageType hrWageType";
	private static final String _SQL_SELECT_HRWAGETYPE_WHERE = "SELECT hrWageType FROM HRWageType hrWageType WHERE ";
	private static final String _SQL_COUNT_HRWAGETYPE = "SELECT COUNT(hrWageType) FROM HRWageType hrWageType";
	private static final String _SQL_COUNT_HRWAGETYPE_WHERE = "SELECT COUNT(hrWageType) FROM HRWageType hrWageType WHERE ";
	private static final String _FINDER_COLUMN_G_C_GROUPID_2 = "hrWageType.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_CODE_1 = "hrWageType.code IS NULL";
	private static final String _FINDER_COLUMN_G_C_CODE_2 = "hrWageType.code = ?";
	private static final String _FINDER_COLUMN_G_C_CODE_3 = "(hrWageType.code IS NULL OR hrWageType.code = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrWageType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRWageType exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No HRWageType exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRWageTypePersistenceImpl.class);
	private static HRWageType _nullHRWageType = new HRWageTypeImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRWageType> toCacheModel() {
				return _nullHRWageTypeCacheModel;
			}
		};

	private static CacheModel<HRWageType> _nullHRWageTypeCacheModel = new CacheModel<HRWageType>() {
			public HRWageType toEntityModel() {
				return _nullHRWageType;
			}
		};
}