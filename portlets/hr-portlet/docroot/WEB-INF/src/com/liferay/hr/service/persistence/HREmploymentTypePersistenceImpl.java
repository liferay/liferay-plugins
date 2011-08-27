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

import com.liferay.hr.NoSuchEmploymentTypeException;
import com.liferay.hr.model.HREmploymentType;
import com.liferay.hr.model.impl.HREmploymentTypeImpl;
import com.liferay.hr.model.impl.HREmploymentTypeModelImpl;

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
 * The persistence implementation for the h r employment type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HREmploymentTypePersistence
 * @see HREmploymentTypeUtil
 * @generated
 */
public class HREmploymentTypePersistenceImpl extends BasePersistenceImpl<HREmploymentType>
	implements HREmploymentTypePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HREmploymentTypeUtil} to access the h r employment type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HREmploymentTypeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_C = new FinderPath(HREmploymentTypeModelImpl.ENTITY_CACHE_ENABLED,
			HREmploymentTypeModelImpl.FINDER_CACHE_ENABLED,
			HREmploymentTypeImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByG_C",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_G_C = new FinderPath(HREmploymentTypeModelImpl.ENTITY_CACHE_ENABLED,
			HREmploymentTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByG_C",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HREmploymentTypeModelImpl.ENTITY_CACHE_ENABLED,
			HREmploymentTypeModelImpl.FINDER_CACHE_ENABLED,
			HREmploymentTypeImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HREmploymentTypeModelImpl.ENTITY_CACHE_ENABLED,
			HREmploymentTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r employment type in the entity cache if it is enabled.
	 *
	 * @param hrEmploymentType the h r employment type
	 */
	public void cacheResult(HREmploymentType hrEmploymentType) {
		EntityCacheUtil.putResult(HREmploymentTypeModelImpl.ENTITY_CACHE_ENABLED,
			HREmploymentTypeImpl.class, hrEmploymentType.getPrimaryKey(),
			hrEmploymentType);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
			new Object[] {
				Long.valueOf(hrEmploymentType.getGroupId()),
				
			hrEmploymentType.getCode()
			}, hrEmploymentType);

		hrEmploymentType.resetOriginalValues();
	}

	/**
	 * Caches the h r employment types in the entity cache if it is enabled.
	 *
	 * @param hrEmploymentTypes the h r employment types
	 */
	public void cacheResult(List<HREmploymentType> hrEmploymentTypes) {
		for (HREmploymentType hrEmploymentType : hrEmploymentTypes) {
			if (EntityCacheUtil.getResult(
						HREmploymentTypeModelImpl.ENTITY_CACHE_ENABLED,
						HREmploymentTypeImpl.class,
						hrEmploymentType.getPrimaryKey(), this) == null) {
				cacheResult(hrEmploymentType);
			}
		}
	}

	/**
	 * Clears the cache for all h r employment types.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HREmploymentTypeImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HREmploymentTypeImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r employment type.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HREmploymentType hrEmploymentType) {
		EntityCacheUtil.removeResult(HREmploymentTypeModelImpl.ENTITY_CACHE_ENABLED,
			HREmploymentTypeImpl.class, hrEmploymentType.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_C,
			new Object[] {
				Long.valueOf(hrEmploymentType.getGroupId()),
				
			hrEmploymentType.getCode()
			});
	}

	/**
	 * Creates a new h r employment type with the primary key. Does not add the h r employment type to the database.
	 *
	 * @param hrEmploymentTypeId the primary key for the new h r employment type
	 * @return the new h r employment type
	 */
	public HREmploymentType create(long hrEmploymentTypeId) {
		HREmploymentType hrEmploymentType = new HREmploymentTypeImpl();

		hrEmploymentType.setNew(true);
		hrEmploymentType.setPrimaryKey(hrEmploymentTypeId);

		return hrEmploymentType;
	}

	/**
	 * Removes the h r employment type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r employment type
	 * @return the h r employment type that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r employment type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HREmploymentType remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r employment type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrEmploymentTypeId the primary key of the h r employment type
	 * @return the h r employment type that was removed
	 * @throws com.liferay.hr.NoSuchEmploymentTypeException if a h r employment type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HREmploymentType remove(long hrEmploymentTypeId)
		throws NoSuchEmploymentTypeException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HREmploymentType hrEmploymentType = (HREmploymentType)session.get(HREmploymentTypeImpl.class,
					Long.valueOf(hrEmploymentTypeId));

			if (hrEmploymentType == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						hrEmploymentTypeId);
				}

				throw new NoSuchEmploymentTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrEmploymentTypeId);
			}

			return hrEmploymentTypePersistence.remove(hrEmploymentType);
		}
		catch (NoSuchEmploymentTypeException nsee) {
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
	 * Removes the h r employment type from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrEmploymentType the h r employment type
	 * @return the h r employment type that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HREmploymentType remove(HREmploymentType hrEmploymentType)
		throws SystemException {
		return super.remove(hrEmploymentType);
	}

	@Override
	protected HREmploymentType removeImpl(HREmploymentType hrEmploymentType)
		throws SystemException {
		hrEmploymentType = toUnwrappedModel(hrEmploymentType);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrEmploymentType);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		HREmploymentTypeModelImpl hrEmploymentTypeModelImpl = (HREmploymentTypeModelImpl)hrEmploymentType;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_C,
			new Object[] {
				Long.valueOf(hrEmploymentTypeModelImpl.getGroupId()),
				
			hrEmploymentTypeModelImpl.getCode()
			});

		EntityCacheUtil.removeResult(HREmploymentTypeModelImpl.ENTITY_CACHE_ENABLED,
			HREmploymentTypeImpl.class, hrEmploymentType.getPrimaryKey());

		return hrEmploymentType;
	}

	@Override
	public HREmploymentType updateImpl(
		com.liferay.hr.model.HREmploymentType hrEmploymentType, boolean merge)
		throws SystemException {
		hrEmploymentType = toUnwrappedModel(hrEmploymentType);

		boolean isNew = hrEmploymentType.isNew();

		HREmploymentTypeModelImpl hrEmploymentTypeModelImpl = (HREmploymentTypeModelImpl)hrEmploymentType;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrEmploymentType, merge);

			hrEmploymentType.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HREmploymentTypeModelImpl.ENTITY_CACHE_ENABLED,
			HREmploymentTypeImpl.class, hrEmploymentType.getPrimaryKey(),
			hrEmploymentType);

		if (!isNew &&
				((hrEmploymentType.getGroupId() != hrEmploymentTypeModelImpl.getOriginalGroupId()) ||
				!Validator.equals(hrEmploymentType.getCode(),
					hrEmploymentTypeModelImpl.getOriginalCode()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_C,
				new Object[] {
					Long.valueOf(hrEmploymentTypeModelImpl.getOriginalGroupId()),
					
				hrEmploymentTypeModelImpl.getOriginalCode()
				});
		}

		if (isNew ||
				((hrEmploymentType.getGroupId() != hrEmploymentTypeModelImpl.getOriginalGroupId()) ||
				!Validator.equals(hrEmploymentType.getCode(),
					hrEmploymentTypeModelImpl.getOriginalCode()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
				new Object[] {
					Long.valueOf(hrEmploymentType.getGroupId()),
					
				hrEmploymentType.getCode()
				}, hrEmploymentType);
		}

		return hrEmploymentType;
	}

	protected HREmploymentType toUnwrappedModel(
		HREmploymentType hrEmploymentType) {
		if (hrEmploymentType instanceof HREmploymentTypeImpl) {
			return hrEmploymentType;
		}

		HREmploymentTypeImpl hrEmploymentTypeImpl = new HREmploymentTypeImpl();

		hrEmploymentTypeImpl.setNew(hrEmploymentType.isNew());
		hrEmploymentTypeImpl.setPrimaryKey(hrEmploymentType.getPrimaryKey());

		hrEmploymentTypeImpl.setHrEmploymentTypeId(hrEmploymentType.getHrEmploymentTypeId());
		hrEmploymentTypeImpl.setGroupId(hrEmploymentType.getGroupId());
		hrEmploymentTypeImpl.setCompanyId(hrEmploymentType.getCompanyId());
		hrEmploymentTypeImpl.setUserId(hrEmploymentType.getUserId());
		hrEmploymentTypeImpl.setUserName(hrEmploymentType.getUserName());
		hrEmploymentTypeImpl.setCreateDate(hrEmploymentType.getCreateDate());
		hrEmploymentTypeImpl.setModifiedDate(hrEmploymentType.getModifiedDate());
		hrEmploymentTypeImpl.setCode(hrEmploymentType.getCode());
		hrEmploymentTypeImpl.setName(hrEmploymentType.getName());
		hrEmploymentTypeImpl.setDescription(hrEmploymentType.getDescription());

		return hrEmploymentTypeImpl;
	}

	/**
	 * Returns the h r employment type with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r employment type
	 * @return the h r employment type
	 * @throws com.liferay.portal.NoSuchModelException if a h r employment type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HREmploymentType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r employment type with the primary key or throws a {@link com.liferay.hr.NoSuchEmploymentTypeException} if it could not be found.
	 *
	 * @param hrEmploymentTypeId the primary key of the h r employment type
	 * @return the h r employment type
	 * @throws com.liferay.hr.NoSuchEmploymentTypeException if a h r employment type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HREmploymentType findByPrimaryKey(long hrEmploymentTypeId)
		throws NoSuchEmploymentTypeException, SystemException {
		HREmploymentType hrEmploymentType = fetchByPrimaryKey(hrEmploymentTypeId);

		if (hrEmploymentType == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrEmploymentTypeId);
			}

			throw new NoSuchEmploymentTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrEmploymentTypeId);
		}

		return hrEmploymentType;
	}

	/**
	 * Returns the h r employment type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r employment type
	 * @return the h r employment type, or <code>null</code> if a h r employment type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HREmploymentType fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r employment type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrEmploymentTypeId the primary key of the h r employment type
	 * @return the h r employment type, or <code>null</code> if a h r employment type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HREmploymentType fetchByPrimaryKey(long hrEmploymentTypeId)
		throws SystemException {
		HREmploymentType hrEmploymentType = (HREmploymentType)EntityCacheUtil.getResult(HREmploymentTypeModelImpl.ENTITY_CACHE_ENABLED,
				HREmploymentTypeImpl.class, hrEmploymentTypeId, this);

		if (hrEmploymentType == _nullHREmploymentType) {
			return null;
		}

		if (hrEmploymentType == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrEmploymentType = (HREmploymentType)session.get(HREmploymentTypeImpl.class,
						Long.valueOf(hrEmploymentTypeId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrEmploymentType != null) {
					cacheResult(hrEmploymentType);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HREmploymentTypeModelImpl.ENTITY_CACHE_ENABLED,
						HREmploymentTypeImpl.class, hrEmploymentTypeId,
						_nullHREmploymentType);
				}

				closeSession(session);
			}
		}

		return hrEmploymentType;
	}

	/**
	 * Returns the h r employment type where groupId = &#63; and code = &#63; or throws a {@link com.liferay.hr.NoSuchEmploymentTypeException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @return the matching h r employment type
	 * @throws com.liferay.hr.NoSuchEmploymentTypeException if a matching h r employment type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HREmploymentType findByG_C(long groupId, String code)
		throws NoSuchEmploymentTypeException, SystemException {
		HREmploymentType hrEmploymentType = fetchByG_C(groupId, code);

		if (hrEmploymentType == null) {
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

			throw new NoSuchEmploymentTypeException(msg.toString());
		}

		return hrEmploymentType;
	}

	/**
	 * Returns the h r employment type where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @return the matching h r employment type, or <code>null</code> if a matching h r employment type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HREmploymentType fetchByG_C(long groupId, String code)
		throws SystemException {
		return fetchByG_C(groupId, code, true);
	}

	/**
	 * Returns the h r employment type where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching h r employment type, or <code>null</code> if a matching h r employment type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HREmploymentType fetchByG_C(long groupId, String code,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, code };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_G_C,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_HREMPLOYMENTTYPE_WHERE);

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

				List<HREmploymentType> list = q.list();

				result = list;

				HREmploymentType hrEmploymentType = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
						finderArgs, list);
				}
				else {
					hrEmploymentType = list.get(0);

					cacheResult(hrEmploymentType);

					if ((hrEmploymentType.getGroupId() != groupId) ||
							(hrEmploymentType.getCode() == null) ||
							!hrEmploymentType.getCode().equals(code)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
							finderArgs, hrEmploymentType);
					}
				}

				return hrEmploymentType;
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
				return (HREmploymentType)result;
			}
		}
	}

	/**
	 * Returns all the h r employment types.
	 *
	 * @return the h r employment types
	 * @throws SystemException if a system exception occurred
	 */
	public List<HREmploymentType> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r employment types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r employment types
	 * @param end the upper bound of the range of h r employment types (not inclusive)
	 * @return the range of h r employment types
	 * @throws SystemException if a system exception occurred
	 */
	public List<HREmploymentType> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r employment types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r employment types
	 * @param end the upper bound of the range of h r employment types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r employment types
	 * @throws SystemException if a system exception occurred
	 */
	public List<HREmploymentType> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HREmploymentType> list = (List<HREmploymentType>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HREMPLOYMENTTYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HREMPLOYMENTTYPE;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HREmploymentType>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HREmploymentType>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes the h r employment type where groupId = &#63; and code = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_C(long groupId, String code)
		throws NoSuchEmploymentTypeException, SystemException {
		HREmploymentType hrEmploymentType = findByG_C(groupId, code);

		hrEmploymentTypePersistence.remove(hrEmploymentType);
	}

	/**
	 * Removes all the h r employment types from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HREmploymentType hrEmploymentType : findAll()) {
			hrEmploymentTypePersistence.remove(hrEmploymentType);
		}
	}

	/**
	 * Returns the number of h r employment types where groupId = &#63; and code = &#63;.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @return the number of matching h r employment types
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_C(long groupId, String code) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, code };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_HREMPLOYMENTTYPE_WHERE);

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
	 * Returns the number of h r employment types.
	 *
	 * @return the number of h r employment types
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

				Query q = session.createQuery(_SQL_COUNT_HREMPLOYMENTTYPE);

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
	 * Initializes the h r employment type persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HREmploymentType")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HREmploymentType>> listenersList = new ArrayList<ModelListener<HREmploymentType>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HREmploymentType>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HREmploymentTypeImpl.class.getName());
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
	private static final String _SQL_SELECT_HREMPLOYMENTTYPE = "SELECT hrEmploymentType FROM HREmploymentType hrEmploymentType";
	private static final String _SQL_SELECT_HREMPLOYMENTTYPE_WHERE = "SELECT hrEmploymentType FROM HREmploymentType hrEmploymentType WHERE ";
	private static final String _SQL_COUNT_HREMPLOYMENTTYPE = "SELECT COUNT(hrEmploymentType) FROM HREmploymentType hrEmploymentType";
	private static final String _SQL_COUNT_HREMPLOYMENTTYPE_WHERE = "SELECT COUNT(hrEmploymentType) FROM HREmploymentType hrEmploymentType WHERE ";
	private static final String _FINDER_COLUMN_G_C_GROUPID_2 = "hrEmploymentType.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_CODE_1 = "hrEmploymentType.code IS NULL";
	private static final String _FINDER_COLUMN_G_C_CODE_2 = "hrEmploymentType.code = ?";
	private static final String _FINDER_COLUMN_G_C_CODE_3 = "(hrEmploymentType.code IS NULL OR hrEmploymentType.code = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrEmploymentType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HREmploymentType exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No HREmploymentType exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HREmploymentTypePersistenceImpl.class);
	private static HREmploymentType _nullHREmploymentType = new HREmploymentTypeImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HREmploymentType> toCacheModel() {
				return _nullHREmploymentTypeCacheModel;
			}
		};

	private static CacheModel<HREmploymentType> _nullHREmploymentTypeCacheModel = new CacheModel<HREmploymentType>() {
			public HREmploymentType toEntityModel() {
				return _nullHREmploymentType;
			}
		};
}