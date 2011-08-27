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

import com.liferay.hr.NoSuchTerminationTypeException;
import com.liferay.hr.model.HRTerminationType;
import com.liferay.hr.model.impl.HRTerminationTypeImpl;
import com.liferay.hr.model.impl.HRTerminationTypeModelImpl;

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
 * The persistence implementation for the h r termination type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRTerminationTypePersistence
 * @see HRTerminationTypeUtil
 * @generated
 */
public class HRTerminationTypePersistenceImpl extends BasePersistenceImpl<HRTerminationType>
	implements HRTerminationTypePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRTerminationTypeUtil} to access the h r termination type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRTerminationTypeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_C = new FinderPath(HRTerminationTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRTerminationTypeModelImpl.FINDER_CACHE_ENABLED,
			HRTerminationTypeImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByG_C",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_G_C = new FinderPath(HRTerminationTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRTerminationTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByG_C",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRTerminationTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRTerminationTypeModelImpl.FINDER_CACHE_ENABLED,
			HRTerminationTypeImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRTerminationTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRTerminationTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r termination type in the entity cache if it is enabled.
	 *
	 * @param hrTerminationType the h r termination type
	 */
	public void cacheResult(HRTerminationType hrTerminationType) {
		EntityCacheUtil.putResult(HRTerminationTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRTerminationTypeImpl.class, hrTerminationType.getPrimaryKey(),
			hrTerminationType);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
			new Object[] {
				Long.valueOf(hrTerminationType.getGroupId()),
				
			hrTerminationType.getCode()
			}, hrTerminationType);

		hrTerminationType.resetOriginalValues();
	}

	/**
	 * Caches the h r termination types in the entity cache if it is enabled.
	 *
	 * @param hrTerminationTypes the h r termination types
	 */
	public void cacheResult(List<HRTerminationType> hrTerminationTypes) {
		for (HRTerminationType hrTerminationType : hrTerminationTypes) {
			if (EntityCacheUtil.getResult(
						HRTerminationTypeModelImpl.ENTITY_CACHE_ENABLED,
						HRTerminationTypeImpl.class,
						hrTerminationType.getPrimaryKey(), this) == null) {
				cacheResult(hrTerminationType);
			}
		}
	}

	/**
	 * Clears the cache for all h r termination types.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRTerminationTypeImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRTerminationTypeImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r termination type.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRTerminationType hrTerminationType) {
		EntityCacheUtil.removeResult(HRTerminationTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRTerminationTypeImpl.class, hrTerminationType.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_C,
			new Object[] {
				Long.valueOf(hrTerminationType.getGroupId()),
				
			hrTerminationType.getCode()
			});
	}

	/**
	 * Creates a new h r termination type with the primary key. Does not add the h r termination type to the database.
	 *
	 * @param hrTerminationTypeId the primary key for the new h r termination type
	 * @return the new h r termination type
	 */
	public HRTerminationType create(long hrTerminationTypeId) {
		HRTerminationType hrTerminationType = new HRTerminationTypeImpl();

		hrTerminationType.setNew(true);
		hrTerminationType.setPrimaryKey(hrTerminationTypeId);

		return hrTerminationType;
	}

	/**
	 * Removes the h r termination type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r termination type
	 * @return the h r termination type that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r termination type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTerminationType remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r termination type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrTerminationTypeId the primary key of the h r termination type
	 * @return the h r termination type that was removed
	 * @throws com.liferay.hr.NoSuchTerminationTypeException if a h r termination type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTerminationType remove(long hrTerminationTypeId)
		throws NoSuchTerminationTypeException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRTerminationType hrTerminationType = (HRTerminationType)session.get(HRTerminationTypeImpl.class,
					Long.valueOf(hrTerminationTypeId));

			if (hrTerminationType == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						hrTerminationTypeId);
				}

				throw new NoSuchTerminationTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrTerminationTypeId);
			}

			return hrTerminationTypePersistence.remove(hrTerminationType);
		}
		catch (NoSuchTerminationTypeException nsee) {
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
	 * Removes the h r termination type from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrTerminationType the h r termination type
	 * @return the h r termination type that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTerminationType remove(HRTerminationType hrTerminationType)
		throws SystemException {
		return super.remove(hrTerminationType);
	}

	@Override
	protected HRTerminationType removeImpl(HRTerminationType hrTerminationType)
		throws SystemException {
		hrTerminationType = toUnwrappedModel(hrTerminationType);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrTerminationType);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		HRTerminationTypeModelImpl hrTerminationTypeModelImpl = (HRTerminationTypeModelImpl)hrTerminationType;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_C,
			new Object[] {
				Long.valueOf(hrTerminationTypeModelImpl.getGroupId()),
				
			hrTerminationTypeModelImpl.getCode()
			});

		EntityCacheUtil.removeResult(HRTerminationTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRTerminationTypeImpl.class, hrTerminationType.getPrimaryKey());

		return hrTerminationType;
	}

	@Override
	public HRTerminationType updateImpl(
		com.liferay.hr.model.HRTerminationType hrTerminationType, boolean merge)
		throws SystemException {
		hrTerminationType = toUnwrappedModel(hrTerminationType);

		boolean isNew = hrTerminationType.isNew();

		HRTerminationTypeModelImpl hrTerminationTypeModelImpl = (HRTerminationTypeModelImpl)hrTerminationType;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrTerminationType, merge);

			hrTerminationType.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRTerminationTypeModelImpl.ENTITY_CACHE_ENABLED,
			HRTerminationTypeImpl.class, hrTerminationType.getPrimaryKey(),
			hrTerminationType);

		if (!isNew &&
				((hrTerminationType.getGroupId() != hrTerminationTypeModelImpl.getOriginalGroupId()) ||
				!Validator.equals(hrTerminationType.getCode(),
					hrTerminationTypeModelImpl.getOriginalCode()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_C,
				new Object[] {
					Long.valueOf(
						hrTerminationTypeModelImpl.getOriginalGroupId()),
					
				hrTerminationTypeModelImpl.getOriginalCode()
				});
		}

		if (isNew ||
				((hrTerminationType.getGroupId() != hrTerminationTypeModelImpl.getOriginalGroupId()) ||
				!Validator.equals(hrTerminationType.getCode(),
					hrTerminationTypeModelImpl.getOriginalCode()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
				new Object[] {
					Long.valueOf(hrTerminationType.getGroupId()),
					
				hrTerminationType.getCode()
				}, hrTerminationType);
		}

		return hrTerminationType;
	}

	protected HRTerminationType toUnwrappedModel(
		HRTerminationType hrTerminationType) {
		if (hrTerminationType instanceof HRTerminationTypeImpl) {
			return hrTerminationType;
		}

		HRTerminationTypeImpl hrTerminationTypeImpl = new HRTerminationTypeImpl();

		hrTerminationTypeImpl.setNew(hrTerminationType.isNew());
		hrTerminationTypeImpl.setPrimaryKey(hrTerminationType.getPrimaryKey());

		hrTerminationTypeImpl.setHrTerminationTypeId(hrTerminationType.getHrTerminationTypeId());
		hrTerminationTypeImpl.setGroupId(hrTerminationType.getGroupId());
		hrTerminationTypeImpl.setCompanyId(hrTerminationType.getCompanyId());
		hrTerminationTypeImpl.setUserId(hrTerminationType.getUserId());
		hrTerminationTypeImpl.setUserName(hrTerminationType.getUserName());
		hrTerminationTypeImpl.setCreateDate(hrTerminationType.getCreateDate());
		hrTerminationTypeImpl.setModifiedDate(hrTerminationType.getModifiedDate());
		hrTerminationTypeImpl.setCode(hrTerminationType.getCode());
		hrTerminationTypeImpl.setName(hrTerminationType.getName());
		hrTerminationTypeImpl.setDescription(hrTerminationType.getDescription());

		return hrTerminationTypeImpl;
	}

	/**
	 * Returns the h r termination type with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r termination type
	 * @return the h r termination type
	 * @throws com.liferay.portal.NoSuchModelException if a h r termination type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTerminationType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r termination type with the primary key or throws a {@link com.liferay.hr.NoSuchTerminationTypeException} if it could not be found.
	 *
	 * @param hrTerminationTypeId the primary key of the h r termination type
	 * @return the h r termination type
	 * @throws com.liferay.hr.NoSuchTerminationTypeException if a h r termination type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTerminationType findByPrimaryKey(long hrTerminationTypeId)
		throws NoSuchTerminationTypeException, SystemException {
		HRTerminationType hrTerminationType = fetchByPrimaryKey(hrTerminationTypeId);

		if (hrTerminationType == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrTerminationTypeId);
			}

			throw new NoSuchTerminationTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrTerminationTypeId);
		}

		return hrTerminationType;
	}

	/**
	 * Returns the h r termination type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r termination type
	 * @return the h r termination type, or <code>null</code> if a h r termination type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTerminationType fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r termination type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrTerminationTypeId the primary key of the h r termination type
	 * @return the h r termination type, or <code>null</code> if a h r termination type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTerminationType fetchByPrimaryKey(long hrTerminationTypeId)
		throws SystemException {
		HRTerminationType hrTerminationType = (HRTerminationType)EntityCacheUtil.getResult(HRTerminationTypeModelImpl.ENTITY_CACHE_ENABLED,
				HRTerminationTypeImpl.class, hrTerminationTypeId, this);

		if (hrTerminationType == _nullHRTerminationType) {
			return null;
		}

		if (hrTerminationType == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrTerminationType = (HRTerminationType)session.get(HRTerminationTypeImpl.class,
						Long.valueOf(hrTerminationTypeId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrTerminationType != null) {
					cacheResult(hrTerminationType);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRTerminationTypeModelImpl.ENTITY_CACHE_ENABLED,
						HRTerminationTypeImpl.class, hrTerminationTypeId,
						_nullHRTerminationType);
				}

				closeSession(session);
			}
		}

		return hrTerminationType;
	}

	/**
	 * Returns the h r termination type where groupId = &#63; and code = &#63; or throws a {@link com.liferay.hr.NoSuchTerminationTypeException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @return the matching h r termination type
	 * @throws com.liferay.hr.NoSuchTerminationTypeException if a matching h r termination type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTerminationType findByG_C(long groupId, String code)
		throws NoSuchTerminationTypeException, SystemException {
		HRTerminationType hrTerminationType = fetchByG_C(groupId, code);

		if (hrTerminationType == null) {
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

			throw new NoSuchTerminationTypeException(msg.toString());
		}

		return hrTerminationType;
	}

	/**
	 * Returns the h r termination type where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @return the matching h r termination type, or <code>null</code> if a matching h r termination type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTerminationType fetchByG_C(long groupId, String code)
		throws SystemException {
		return fetchByG_C(groupId, code, true);
	}

	/**
	 * Returns the h r termination type where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching h r termination type, or <code>null</code> if a matching h r termination type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTerminationType fetchByG_C(long groupId, String code,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, code };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_G_C,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_HRTERMINATIONTYPE_WHERE);

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

				List<HRTerminationType> list = q.list();

				result = list;

				HRTerminationType hrTerminationType = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
						finderArgs, list);
				}
				else {
					hrTerminationType = list.get(0);

					cacheResult(hrTerminationType);

					if ((hrTerminationType.getGroupId() != groupId) ||
							(hrTerminationType.getCode() == null) ||
							!hrTerminationType.getCode().equals(code)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
							finderArgs, hrTerminationType);
					}
				}

				return hrTerminationType;
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
				return (HRTerminationType)result;
			}
		}
	}

	/**
	 * Returns all the h r termination types.
	 *
	 * @return the h r termination types
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRTerminationType> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r termination types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r termination types
	 * @param end the upper bound of the range of h r termination types (not inclusive)
	 * @return the range of h r termination types
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRTerminationType> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r termination types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r termination types
	 * @param end the upper bound of the range of h r termination types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r termination types
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRTerminationType> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRTerminationType> list = (List<HRTerminationType>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRTERMINATIONTYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRTERMINATIONTYPE;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRTerminationType>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRTerminationType>)QueryUtil.list(q,
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
	 * Removes the h r termination type where groupId = &#63; and code = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_C(long groupId, String code)
		throws NoSuchTerminationTypeException, SystemException {
		HRTerminationType hrTerminationType = findByG_C(groupId, code);

		hrTerminationTypePersistence.remove(hrTerminationType);
	}

	/**
	 * Removes all the h r termination types from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRTerminationType hrTerminationType : findAll()) {
			hrTerminationTypePersistence.remove(hrTerminationType);
		}
	}

	/**
	 * Returns the number of h r termination types where groupId = &#63; and code = &#63;.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @return the number of matching h r termination types
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_C(long groupId, String code) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, code };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_HRTERMINATIONTYPE_WHERE);

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
	 * Returns the number of h r termination types.
	 *
	 * @return the number of h r termination types
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

				Query q = session.createQuery(_SQL_COUNT_HRTERMINATIONTYPE);

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
	 * Initializes the h r termination type persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRTerminationType")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRTerminationType>> listenersList = new ArrayList<ModelListener<HRTerminationType>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRTerminationType>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRTerminationTypeImpl.class.getName());
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
	private static final String _SQL_SELECT_HRTERMINATIONTYPE = "SELECT hrTerminationType FROM HRTerminationType hrTerminationType";
	private static final String _SQL_SELECT_HRTERMINATIONTYPE_WHERE = "SELECT hrTerminationType FROM HRTerminationType hrTerminationType WHERE ";
	private static final String _SQL_COUNT_HRTERMINATIONTYPE = "SELECT COUNT(hrTerminationType) FROM HRTerminationType hrTerminationType";
	private static final String _SQL_COUNT_HRTERMINATIONTYPE_WHERE = "SELECT COUNT(hrTerminationType) FROM HRTerminationType hrTerminationType WHERE ";
	private static final String _FINDER_COLUMN_G_C_GROUPID_2 = "hrTerminationType.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_CODE_1 = "hrTerminationType.code IS NULL";
	private static final String _FINDER_COLUMN_G_C_CODE_2 = "hrTerminationType.code = ?";
	private static final String _FINDER_COLUMN_G_C_CODE_3 = "(hrTerminationType.code IS NULL OR hrTerminationType.code = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrTerminationType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRTerminationType exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No HRTerminationType exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRTerminationTypePersistenceImpl.class);
	private static HRTerminationType _nullHRTerminationType = new HRTerminationTypeImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRTerminationType> toCacheModel() {
				return _nullHRTerminationTypeCacheModel;
			}
		};

	private static CacheModel<HRTerminationType> _nullHRTerminationTypeCacheModel =
		new CacheModel<HRTerminationType>() {
			public HRTerminationType toEntityModel() {
				return _nullHRTerminationType;
			}
		};
}