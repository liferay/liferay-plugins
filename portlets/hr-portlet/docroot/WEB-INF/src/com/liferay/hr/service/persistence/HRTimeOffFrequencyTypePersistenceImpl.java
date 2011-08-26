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

import com.liferay.hr.NoSuchTimeOffFrequencyTypeException;
import com.liferay.hr.model.HRTimeOffFrequencyType;
import com.liferay.hr.model.impl.HRTimeOffFrequencyTypeImpl;
import com.liferay.hr.model.impl.HRTimeOffFrequencyTypeModelImpl;

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
 * The persistence implementation for the h r time off frequency type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRTimeOffFrequencyTypePersistence
 * @see HRTimeOffFrequencyTypeUtil
 * @generated
 */
public class HRTimeOffFrequencyTypePersistenceImpl extends BasePersistenceImpl<HRTimeOffFrequencyType>
	implements HRTimeOffFrequencyTypePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRTimeOffFrequencyTypeUtil} to access the h r time off frequency type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRTimeOffFrequencyTypeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_C = new FinderPath(HRTimeOffFrequencyTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeOffFrequencyTypeModelImpl.FINDER_CACHE_ENABLED,
			HRTimeOffFrequencyTypeImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByG_C",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_G_C = new FinderPath(HRTimeOffFrequencyTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeOffFrequencyTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByG_C",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRTimeOffFrequencyTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeOffFrequencyTypeModelImpl.FINDER_CACHE_ENABLED,
			HRTimeOffFrequencyTypeImpl.class, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRTimeOffFrequencyTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeOffFrequencyTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r time off frequency type in the entity cache if it is enabled.
	 *
	 * @param hrTimeOffFrequencyType the h r time off frequency type
	 */
	public void cacheResult(HRTimeOffFrequencyType hrTimeOffFrequencyType) {
		EntityCacheUtil.putResult(HRTimeOffFrequencyTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeOffFrequencyTypeImpl.class,
			hrTimeOffFrequencyType.getPrimaryKey(), hrTimeOffFrequencyType);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
			new Object[] {
				Long.valueOf(hrTimeOffFrequencyType.getGroupId()),
				
			hrTimeOffFrequencyType.getCode()
			}, hrTimeOffFrequencyType);

		hrTimeOffFrequencyType.resetOriginalValues();
	}

	/**
	 * Caches the h r time off frequency types in the entity cache if it is enabled.
	 *
	 * @param hrTimeOffFrequencyTypes the h r time off frequency types
	 */
	public void cacheResult(
		List<HRTimeOffFrequencyType> hrTimeOffFrequencyTypes) {
		for (HRTimeOffFrequencyType hrTimeOffFrequencyType : hrTimeOffFrequencyTypes) {
			if (EntityCacheUtil.getResult(
						HRTimeOffFrequencyTypeModelImpl.ENTITY_CACHE_ENABLED,
						HRTimeOffFrequencyTypeImpl.class,
						hrTimeOffFrequencyType.getPrimaryKey(), this) == null) {
				cacheResult(hrTimeOffFrequencyType);
			}
		}
	}

	/**
	 * Clears the cache for all h r time off frequency types.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRTimeOffFrequencyTypeImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRTimeOffFrequencyTypeImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r time off frequency type.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRTimeOffFrequencyType hrTimeOffFrequencyType) {
		EntityCacheUtil.removeResult(HRTimeOffFrequencyTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeOffFrequencyTypeImpl.class,
			hrTimeOffFrequencyType.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_C,
			new Object[] {
				Long.valueOf(hrTimeOffFrequencyType.getGroupId()),
				
			hrTimeOffFrequencyType.getCode()
			});
	}

	/**
	 * Creates a new h r time off frequency type with the primary key. Does not add the h r time off frequency type to the database.
	 *
	 * @param hrTimeOffFrequencyTypeId the primary key for the new h r time off frequency type
	 * @return the new h r time off frequency type
	 */
	public HRTimeOffFrequencyType create(long hrTimeOffFrequencyTypeId) {
		HRTimeOffFrequencyType hrTimeOffFrequencyType = new HRTimeOffFrequencyTypeImpl();

		hrTimeOffFrequencyType.setNew(true);
		hrTimeOffFrequencyType.setPrimaryKey(hrTimeOffFrequencyTypeId);

		return hrTimeOffFrequencyType;
	}

	/**
	 * Removes the h r time off frequency type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r time off frequency type
	 * @return the h r time off frequency type that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r time off frequency type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTimeOffFrequencyType remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r time off frequency type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrTimeOffFrequencyTypeId the primary key of the h r time off frequency type
	 * @return the h r time off frequency type that was removed
	 * @throws com.liferay.hr.NoSuchTimeOffFrequencyTypeException if a h r time off frequency type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTimeOffFrequencyType remove(long hrTimeOffFrequencyTypeId)
		throws NoSuchTimeOffFrequencyTypeException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRTimeOffFrequencyType hrTimeOffFrequencyType = (HRTimeOffFrequencyType)session.get(HRTimeOffFrequencyTypeImpl.class,
					Long.valueOf(hrTimeOffFrequencyTypeId));

			if (hrTimeOffFrequencyType == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						hrTimeOffFrequencyTypeId);
				}

				throw new NoSuchTimeOffFrequencyTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrTimeOffFrequencyTypeId);
			}

			return hrTimeOffFrequencyTypePersistence.remove(hrTimeOffFrequencyType);
		}
		catch (NoSuchTimeOffFrequencyTypeException nsee) {
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
	 * Removes the h r time off frequency type from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrTimeOffFrequencyType the h r time off frequency type
	 * @return the h r time off frequency type that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTimeOffFrequencyType remove(
		HRTimeOffFrequencyType hrTimeOffFrequencyType)
		throws SystemException {
		return super.remove(hrTimeOffFrequencyType);
	}

	@Override
	protected HRTimeOffFrequencyType removeImpl(
		HRTimeOffFrequencyType hrTimeOffFrequencyType)
		throws SystemException {
		hrTimeOffFrequencyType = toUnwrappedModel(hrTimeOffFrequencyType);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrTimeOffFrequencyType);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		HRTimeOffFrequencyTypeModelImpl hrTimeOffFrequencyTypeModelImpl = (HRTimeOffFrequencyTypeModelImpl)hrTimeOffFrequencyType;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_C,
			new Object[] {
				Long.valueOf(hrTimeOffFrequencyTypeModelImpl.getGroupId()),
				
			hrTimeOffFrequencyTypeModelImpl.getCode()
			});

		EntityCacheUtil.removeResult(HRTimeOffFrequencyTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeOffFrequencyTypeImpl.class,
			hrTimeOffFrequencyType.getPrimaryKey());

		return hrTimeOffFrequencyType;
	}

	@Override
	public HRTimeOffFrequencyType updateImpl(
		com.liferay.hr.model.HRTimeOffFrequencyType hrTimeOffFrequencyType,
		boolean merge) throws SystemException {
		hrTimeOffFrequencyType = toUnwrappedModel(hrTimeOffFrequencyType);

		boolean isNew = hrTimeOffFrequencyType.isNew();

		HRTimeOffFrequencyTypeModelImpl hrTimeOffFrequencyTypeModelImpl = (HRTimeOffFrequencyTypeModelImpl)hrTimeOffFrequencyType;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrTimeOffFrequencyType, merge);

			hrTimeOffFrequencyType.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRTimeOffFrequencyTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRTimeOffFrequencyTypeImpl.class,
			hrTimeOffFrequencyType.getPrimaryKey(), hrTimeOffFrequencyType);

		if (!isNew &&
				((hrTimeOffFrequencyType.getGroupId() != hrTimeOffFrequencyTypeModelImpl.getOriginalGroupId()) ||
				!Validator.equals(hrTimeOffFrequencyType.getCode(),
					hrTimeOffFrequencyTypeModelImpl.getOriginalCode()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_C,
				new Object[] {
					Long.valueOf(
						hrTimeOffFrequencyTypeModelImpl.getOriginalGroupId()),
					
				hrTimeOffFrequencyTypeModelImpl.getOriginalCode()
				});
		}

		if (isNew ||
				((hrTimeOffFrequencyType.getGroupId() != hrTimeOffFrequencyTypeModelImpl.getOriginalGroupId()) ||
				!Validator.equals(hrTimeOffFrequencyType.getCode(),
					hrTimeOffFrequencyTypeModelImpl.getOriginalCode()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
				new Object[] {
					Long.valueOf(hrTimeOffFrequencyType.getGroupId()),
					
				hrTimeOffFrequencyType.getCode()
				}, hrTimeOffFrequencyType);
		}

		return hrTimeOffFrequencyType;
	}

	protected HRTimeOffFrequencyType toUnwrappedModel(
		HRTimeOffFrequencyType hrTimeOffFrequencyType) {
		if (hrTimeOffFrequencyType instanceof HRTimeOffFrequencyTypeImpl) {
			return hrTimeOffFrequencyType;
		}

		HRTimeOffFrequencyTypeImpl hrTimeOffFrequencyTypeImpl = new HRTimeOffFrequencyTypeImpl();

		hrTimeOffFrequencyTypeImpl.setNew(hrTimeOffFrequencyType.isNew());
		hrTimeOffFrequencyTypeImpl.setPrimaryKey(hrTimeOffFrequencyType.getPrimaryKey());

		hrTimeOffFrequencyTypeImpl.setHrTimeOffFrequencyTypeId(hrTimeOffFrequencyType.getHrTimeOffFrequencyTypeId());
		hrTimeOffFrequencyTypeImpl.setGroupId(hrTimeOffFrequencyType.getGroupId());
		hrTimeOffFrequencyTypeImpl.setCompanyId(hrTimeOffFrequencyType.getCompanyId());
		hrTimeOffFrequencyTypeImpl.setUserId(hrTimeOffFrequencyType.getUserId());
		hrTimeOffFrequencyTypeImpl.setUserName(hrTimeOffFrequencyType.getUserName());
		hrTimeOffFrequencyTypeImpl.setCreateDate(hrTimeOffFrequencyType.getCreateDate());
		hrTimeOffFrequencyTypeImpl.setModifiedDate(hrTimeOffFrequencyType.getModifiedDate());
		hrTimeOffFrequencyTypeImpl.setCode(hrTimeOffFrequencyType.getCode());
		hrTimeOffFrequencyTypeImpl.setName(hrTimeOffFrequencyType.getName());
		hrTimeOffFrequencyTypeImpl.setDescription(hrTimeOffFrequencyType.getDescription());

		return hrTimeOffFrequencyTypeImpl;
	}

	/**
	 * Returns the h r time off frequency type with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r time off frequency type
	 * @return the h r time off frequency type
	 * @throws com.liferay.portal.NoSuchModelException if a h r time off frequency type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTimeOffFrequencyType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r time off frequency type with the primary key or throws a {@link com.liferay.hr.NoSuchTimeOffFrequencyTypeException} if it could not be found.
	 *
	 * @param hrTimeOffFrequencyTypeId the primary key of the h r time off frequency type
	 * @return the h r time off frequency type
	 * @throws com.liferay.hr.NoSuchTimeOffFrequencyTypeException if a h r time off frequency type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTimeOffFrequencyType findByPrimaryKey(
		long hrTimeOffFrequencyTypeId)
		throws NoSuchTimeOffFrequencyTypeException, SystemException {
		HRTimeOffFrequencyType hrTimeOffFrequencyType = fetchByPrimaryKey(hrTimeOffFrequencyTypeId);

		if (hrTimeOffFrequencyType == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrTimeOffFrequencyTypeId);
			}

			throw new NoSuchTimeOffFrequencyTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrTimeOffFrequencyTypeId);
		}

		return hrTimeOffFrequencyType;
	}

	/**
	 * Returns the h r time off frequency type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r time off frequency type
	 * @return the h r time off frequency type, or <code>null</code> if a h r time off frequency type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTimeOffFrequencyType fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r time off frequency type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrTimeOffFrequencyTypeId the primary key of the h r time off frequency type
	 * @return the h r time off frequency type, or <code>null</code> if a h r time off frequency type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTimeOffFrequencyType fetchByPrimaryKey(
		long hrTimeOffFrequencyTypeId) throws SystemException {
		HRTimeOffFrequencyType hrTimeOffFrequencyType = (HRTimeOffFrequencyType)EntityCacheUtil.getResult(HRTimeOffFrequencyTypeModelImpl.ENTITY_CACHE_ENABLED,
				HRTimeOffFrequencyTypeImpl.class, hrTimeOffFrequencyTypeId, this);

		if (hrTimeOffFrequencyType == _nullHRTimeOffFrequencyType) {
			return null;
		}

		if (hrTimeOffFrequencyType == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrTimeOffFrequencyType = (HRTimeOffFrequencyType)session.get(HRTimeOffFrequencyTypeImpl.class,
						Long.valueOf(hrTimeOffFrequencyTypeId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrTimeOffFrequencyType != null) {
					cacheResult(hrTimeOffFrequencyType);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRTimeOffFrequencyTypeModelImpl.ENTITY_CACHE_ENABLED,
						HRTimeOffFrequencyTypeImpl.class,
						hrTimeOffFrequencyTypeId, _nullHRTimeOffFrequencyType);
				}

				closeSession(session);
			}
		}

		return hrTimeOffFrequencyType;
	}

	/**
	 * Returns the h r time off frequency type where groupId = &#63; and code = &#63; or throws a {@link com.liferay.hr.NoSuchTimeOffFrequencyTypeException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @return the matching h r time off frequency type
	 * @throws com.liferay.hr.NoSuchTimeOffFrequencyTypeException if a matching h r time off frequency type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTimeOffFrequencyType findByG_C(long groupId, String code)
		throws NoSuchTimeOffFrequencyTypeException, SystemException {
		HRTimeOffFrequencyType hrTimeOffFrequencyType = fetchByG_C(groupId, code);

		if (hrTimeOffFrequencyType == null) {
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

			throw new NoSuchTimeOffFrequencyTypeException(msg.toString());
		}

		return hrTimeOffFrequencyType;
	}

	/**
	 * Returns the h r time off frequency type where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @return the matching h r time off frequency type, or <code>null</code> if a matching h r time off frequency type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTimeOffFrequencyType fetchByG_C(long groupId, String code)
		throws SystemException {
		return fetchByG_C(groupId, code, true);
	}

	/**
	 * Returns the h r time off frequency type where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching h r time off frequency type, or <code>null</code> if a matching h r time off frequency type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTimeOffFrequencyType fetchByG_C(long groupId, String code,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, code };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_G_C,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_HRTIMEOFFFREQUENCYTYPE_WHERE);

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

				List<HRTimeOffFrequencyType> list = q.list();

				result = list;

				HRTimeOffFrequencyType hrTimeOffFrequencyType = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
						finderArgs, list);
				}
				else {
					hrTimeOffFrequencyType = list.get(0);

					cacheResult(hrTimeOffFrequencyType);

					if ((hrTimeOffFrequencyType.getGroupId() != groupId) ||
							(hrTimeOffFrequencyType.getCode() == null) ||
							!hrTimeOffFrequencyType.getCode().equals(code)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
							finderArgs, hrTimeOffFrequencyType);
					}
				}

				return hrTimeOffFrequencyType;
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
				return (HRTimeOffFrequencyType)result;
			}
		}
	}

	/**
	 * Returns all the h r time off frequency types.
	 *
	 * @return the h r time off frequency types
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRTimeOffFrequencyType> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r time off frequency types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r time off frequency types
	 * @param end the upper bound of the range of h r time off frequency types (not inclusive)
	 * @return the range of h r time off frequency types
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRTimeOffFrequencyType> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r time off frequency types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r time off frequency types
	 * @param end the upper bound of the range of h r time off frequency types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r time off frequency types
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRTimeOffFrequencyType> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRTimeOffFrequencyType> list = (List<HRTimeOffFrequencyType>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRTIMEOFFFREQUENCYTYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRTIMEOFFFREQUENCYTYPE;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRTimeOffFrequencyType>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRTimeOffFrequencyType>)QueryUtil.list(q,
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
	 * Removes the h r time off frequency type where groupId = &#63; and code = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_C(long groupId, String code)
		throws NoSuchTimeOffFrequencyTypeException, SystemException {
		HRTimeOffFrequencyType hrTimeOffFrequencyType = findByG_C(groupId, code);

		hrTimeOffFrequencyTypePersistence.remove(hrTimeOffFrequencyType);
	}

	/**
	 * Removes all the h r time off frequency types from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRTimeOffFrequencyType hrTimeOffFrequencyType : findAll()) {
			hrTimeOffFrequencyTypePersistence.remove(hrTimeOffFrequencyType);
		}
	}

	/**
	 * Returns the number of h r time off frequency types where groupId = &#63; and code = &#63;.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @return the number of matching h r time off frequency types
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_C(long groupId, String code) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, code };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_HRTIMEOFFFREQUENCYTYPE_WHERE);

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
	 * Returns the number of h r time off frequency types.
	 *
	 * @return the number of h r time off frequency types
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

				Query q = session.createQuery(_SQL_COUNT_HRTIMEOFFFREQUENCYTYPE);

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
	 * Initializes the h r time off frequency type persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRTimeOffFrequencyType")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRTimeOffFrequencyType>> listenersList = new ArrayList<ModelListener<HRTimeOffFrequencyType>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRTimeOffFrequencyType>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRTimeOffFrequencyTypeImpl.class.getName());
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
	private static final String _SQL_SELECT_HRTIMEOFFFREQUENCYTYPE = "SELECT hrTimeOffFrequencyType FROM HRTimeOffFrequencyType hrTimeOffFrequencyType";
	private static final String _SQL_SELECT_HRTIMEOFFFREQUENCYTYPE_WHERE = "SELECT hrTimeOffFrequencyType FROM HRTimeOffFrequencyType hrTimeOffFrequencyType WHERE ";
	private static final String _SQL_COUNT_HRTIMEOFFFREQUENCYTYPE = "SELECT COUNT(hrTimeOffFrequencyType) FROM HRTimeOffFrequencyType hrTimeOffFrequencyType";
	private static final String _SQL_COUNT_HRTIMEOFFFREQUENCYTYPE_WHERE = "SELECT COUNT(hrTimeOffFrequencyType) FROM HRTimeOffFrequencyType hrTimeOffFrequencyType WHERE ";
	private static final String _FINDER_COLUMN_G_C_GROUPID_2 = "hrTimeOffFrequencyType.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_CODE_1 = "hrTimeOffFrequencyType.code IS NULL";
	private static final String _FINDER_COLUMN_G_C_CODE_2 = "hrTimeOffFrequencyType.code = ?";
	private static final String _FINDER_COLUMN_G_C_CODE_3 = "(hrTimeOffFrequencyType.code IS NULL OR hrTimeOffFrequencyType.code = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrTimeOffFrequencyType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRTimeOffFrequencyType exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No HRTimeOffFrequencyType exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRTimeOffFrequencyTypePersistenceImpl.class);
	private static HRTimeOffFrequencyType _nullHRTimeOffFrequencyType = new HRTimeOffFrequencyTypeImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRTimeOffFrequencyType> toCacheModel() {
				return _nullHRTimeOffFrequencyTypeCacheModel;
			}
		};

	private static CacheModel<HRTimeOffFrequencyType> _nullHRTimeOffFrequencyTypeCacheModel =
		new CacheModel<HRTimeOffFrequencyType>() {
			public HRTimeOffFrequencyType toEntityModel() {
				return _nullHRTimeOffFrequencyType;
			}
		};
}