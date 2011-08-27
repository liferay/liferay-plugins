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

import com.liferay.hr.NoSuchBillabilityException;
import com.liferay.hr.model.HRBillability;
import com.liferay.hr.model.impl.HRBillabilityImpl;
import com.liferay.hr.model.impl.HRBillabilityModelImpl;

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
 * The persistence implementation for the h r billability service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRBillabilityPersistence
 * @see HRBillabilityUtil
 * @generated
 */
public class HRBillabilityPersistenceImpl extends BasePersistenceImpl<HRBillability>
	implements HRBillabilityPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRBillabilityUtil} to access the h r billability persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRBillabilityImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_C = new FinderPath(HRBillabilityModelImpl.ENTITY_CACHE_ENABLED,
			HRBillabilityModelImpl.FINDER_CACHE_ENABLED,
			HRBillabilityImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByG_C",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_G_C = new FinderPath(HRBillabilityModelImpl.ENTITY_CACHE_ENABLED,
			HRBillabilityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByG_C",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRBillabilityModelImpl.ENTITY_CACHE_ENABLED,
			HRBillabilityModelImpl.FINDER_CACHE_ENABLED,
			HRBillabilityImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRBillabilityModelImpl.ENTITY_CACHE_ENABLED,
			HRBillabilityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r billability in the entity cache if it is enabled.
	 *
	 * @param hrBillability the h r billability
	 */
	public void cacheResult(HRBillability hrBillability) {
		EntityCacheUtil.putResult(HRBillabilityModelImpl.ENTITY_CACHE_ENABLED,
			HRBillabilityImpl.class, hrBillability.getPrimaryKey(),
			hrBillability);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
			new Object[] {
				Long.valueOf(hrBillability.getGroupId()),
				
			hrBillability.getCode()
			}, hrBillability);

		hrBillability.resetOriginalValues();
	}

	/**
	 * Caches the h r billabilities in the entity cache if it is enabled.
	 *
	 * @param hrBillabilities the h r billabilities
	 */
	public void cacheResult(List<HRBillability> hrBillabilities) {
		for (HRBillability hrBillability : hrBillabilities) {
			if (EntityCacheUtil.getResult(
						HRBillabilityModelImpl.ENTITY_CACHE_ENABLED,
						HRBillabilityImpl.class, hrBillability.getPrimaryKey(),
						this) == null) {
				cacheResult(hrBillability);
			}
		}
	}

	/**
	 * Clears the cache for all h r billabilities.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRBillabilityImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRBillabilityImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r billability.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRBillability hrBillability) {
		EntityCacheUtil.removeResult(HRBillabilityModelImpl.ENTITY_CACHE_ENABLED,
			HRBillabilityImpl.class, hrBillability.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_C,
			new Object[] {
				Long.valueOf(hrBillability.getGroupId()),
				
			hrBillability.getCode()
			});
	}

	/**
	 * Creates a new h r billability with the primary key. Does not add the h r billability to the database.
	 *
	 * @param hrBillabilityId the primary key for the new h r billability
	 * @return the new h r billability
	 */
	public HRBillability create(long hrBillabilityId) {
		HRBillability hrBillability = new HRBillabilityImpl();

		hrBillability.setNew(true);
		hrBillability.setPrimaryKey(hrBillabilityId);

		return hrBillability;
	}

	/**
	 * Removes the h r billability with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r billability
	 * @return the h r billability that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r billability with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRBillability remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r billability with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrBillabilityId the primary key of the h r billability
	 * @return the h r billability that was removed
	 * @throws com.liferay.hr.NoSuchBillabilityException if a h r billability with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRBillability remove(long hrBillabilityId)
		throws NoSuchBillabilityException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRBillability hrBillability = (HRBillability)session.get(HRBillabilityImpl.class,
					Long.valueOf(hrBillabilityId));

			if (hrBillability == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						hrBillabilityId);
				}

				throw new NoSuchBillabilityException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrBillabilityId);
			}

			return hrBillabilityPersistence.remove(hrBillability);
		}
		catch (NoSuchBillabilityException nsee) {
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
	 * Removes the h r billability from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrBillability the h r billability
	 * @return the h r billability that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRBillability remove(HRBillability hrBillability)
		throws SystemException {
		return super.remove(hrBillability);
	}

	@Override
	protected HRBillability removeImpl(HRBillability hrBillability)
		throws SystemException {
		hrBillability = toUnwrappedModel(hrBillability);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrBillability);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		HRBillabilityModelImpl hrBillabilityModelImpl = (HRBillabilityModelImpl)hrBillability;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_C,
			new Object[] {
				Long.valueOf(hrBillabilityModelImpl.getGroupId()),
				
			hrBillabilityModelImpl.getCode()
			});

		EntityCacheUtil.removeResult(HRBillabilityModelImpl.ENTITY_CACHE_ENABLED,
			HRBillabilityImpl.class, hrBillability.getPrimaryKey());

		return hrBillability;
	}

	@Override
	public HRBillability updateImpl(
		com.liferay.hr.model.HRBillability hrBillability, boolean merge)
		throws SystemException {
		hrBillability = toUnwrappedModel(hrBillability);

		boolean isNew = hrBillability.isNew();

		HRBillabilityModelImpl hrBillabilityModelImpl = (HRBillabilityModelImpl)hrBillability;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrBillability, merge);

			hrBillability.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRBillabilityModelImpl.ENTITY_CACHE_ENABLED,
			HRBillabilityImpl.class, hrBillability.getPrimaryKey(),
			hrBillability);

		if (!isNew &&
				((hrBillability.getGroupId() != hrBillabilityModelImpl.getOriginalGroupId()) ||
				!Validator.equals(hrBillability.getCode(),
					hrBillabilityModelImpl.getOriginalCode()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_C,
				new Object[] {
					Long.valueOf(hrBillabilityModelImpl.getOriginalGroupId()),
					
				hrBillabilityModelImpl.getOriginalCode()
				});
		}

		if (isNew ||
				((hrBillability.getGroupId() != hrBillabilityModelImpl.getOriginalGroupId()) ||
				!Validator.equals(hrBillability.getCode(),
					hrBillabilityModelImpl.getOriginalCode()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
				new Object[] {
					Long.valueOf(hrBillability.getGroupId()),
					
				hrBillability.getCode()
				}, hrBillability);
		}

		return hrBillability;
	}

	protected HRBillability toUnwrappedModel(HRBillability hrBillability) {
		if (hrBillability instanceof HRBillabilityImpl) {
			return hrBillability;
		}

		HRBillabilityImpl hrBillabilityImpl = new HRBillabilityImpl();

		hrBillabilityImpl.setNew(hrBillability.isNew());
		hrBillabilityImpl.setPrimaryKey(hrBillability.getPrimaryKey());

		hrBillabilityImpl.setHrBillabilityId(hrBillability.getHrBillabilityId());
		hrBillabilityImpl.setGroupId(hrBillability.getGroupId());
		hrBillabilityImpl.setCompanyId(hrBillability.getCompanyId());
		hrBillabilityImpl.setUserId(hrBillability.getUserId());
		hrBillabilityImpl.setUserName(hrBillability.getUserName());
		hrBillabilityImpl.setCreateDate(hrBillability.getCreateDate());
		hrBillabilityImpl.setModifiedDate(hrBillability.getModifiedDate());
		hrBillabilityImpl.setCode(hrBillability.getCode());
		hrBillabilityImpl.setName(hrBillability.getName());
		hrBillabilityImpl.setDescription(hrBillability.getDescription());

		return hrBillabilityImpl;
	}

	/**
	 * Returns the h r billability with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r billability
	 * @return the h r billability
	 * @throws com.liferay.portal.NoSuchModelException if a h r billability with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRBillability findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r billability with the primary key or throws a {@link com.liferay.hr.NoSuchBillabilityException} if it could not be found.
	 *
	 * @param hrBillabilityId the primary key of the h r billability
	 * @return the h r billability
	 * @throws com.liferay.hr.NoSuchBillabilityException if a h r billability with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRBillability findByPrimaryKey(long hrBillabilityId)
		throws NoSuchBillabilityException, SystemException {
		HRBillability hrBillability = fetchByPrimaryKey(hrBillabilityId);

		if (hrBillability == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrBillabilityId);
			}

			throw new NoSuchBillabilityException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrBillabilityId);
		}

		return hrBillability;
	}

	/**
	 * Returns the h r billability with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r billability
	 * @return the h r billability, or <code>null</code> if a h r billability with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRBillability fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r billability with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrBillabilityId the primary key of the h r billability
	 * @return the h r billability, or <code>null</code> if a h r billability with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRBillability fetchByPrimaryKey(long hrBillabilityId)
		throws SystemException {
		HRBillability hrBillability = (HRBillability)EntityCacheUtil.getResult(HRBillabilityModelImpl.ENTITY_CACHE_ENABLED,
				HRBillabilityImpl.class, hrBillabilityId, this);

		if (hrBillability == _nullHRBillability) {
			return null;
		}

		if (hrBillability == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrBillability = (HRBillability)session.get(HRBillabilityImpl.class,
						Long.valueOf(hrBillabilityId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrBillability != null) {
					cacheResult(hrBillability);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRBillabilityModelImpl.ENTITY_CACHE_ENABLED,
						HRBillabilityImpl.class, hrBillabilityId,
						_nullHRBillability);
				}

				closeSession(session);
			}
		}

		return hrBillability;
	}

	/**
	 * Returns the h r billability where groupId = &#63; and code = &#63; or throws a {@link com.liferay.hr.NoSuchBillabilityException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @return the matching h r billability
	 * @throws com.liferay.hr.NoSuchBillabilityException if a matching h r billability could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRBillability findByG_C(long groupId, String code)
		throws NoSuchBillabilityException, SystemException {
		HRBillability hrBillability = fetchByG_C(groupId, code);

		if (hrBillability == null) {
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

			throw new NoSuchBillabilityException(msg.toString());
		}

		return hrBillability;
	}

	/**
	 * Returns the h r billability where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @return the matching h r billability, or <code>null</code> if a matching h r billability could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRBillability fetchByG_C(long groupId, String code)
		throws SystemException {
		return fetchByG_C(groupId, code, true);
	}

	/**
	 * Returns the h r billability where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching h r billability, or <code>null</code> if a matching h r billability could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRBillability fetchByG_C(long groupId, String code,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, code };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_G_C,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_HRBILLABILITY_WHERE);

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

				List<HRBillability> list = q.list();

				result = list;

				HRBillability hrBillability = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
						finderArgs, list);
				}
				else {
					hrBillability = list.get(0);

					cacheResult(hrBillability);

					if ((hrBillability.getGroupId() != groupId) ||
							(hrBillability.getCode() == null) ||
							!hrBillability.getCode().equals(code)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
							finderArgs, hrBillability);
					}
				}

				return hrBillability;
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
				return (HRBillability)result;
			}
		}
	}

	/**
	 * Returns all the h r billabilities.
	 *
	 * @return the h r billabilities
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRBillability> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r billabilities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r billabilities
	 * @param end the upper bound of the range of h r billabilities (not inclusive)
	 * @return the range of h r billabilities
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRBillability> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r billabilities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r billabilities
	 * @param end the upper bound of the range of h r billabilities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r billabilities
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRBillability> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRBillability> list = (List<HRBillability>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRBILLABILITY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRBILLABILITY;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRBillability>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRBillability>)QueryUtil.list(q, getDialect(),
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
	 * Removes the h r billability where groupId = &#63; and code = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_C(long groupId, String code)
		throws NoSuchBillabilityException, SystemException {
		HRBillability hrBillability = findByG_C(groupId, code);

		hrBillabilityPersistence.remove(hrBillability);
	}

	/**
	 * Removes all the h r billabilities from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRBillability hrBillability : findAll()) {
			hrBillabilityPersistence.remove(hrBillability);
		}
	}

	/**
	 * Returns the number of h r billabilities where groupId = &#63; and code = &#63;.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @return the number of matching h r billabilities
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_C(long groupId, String code) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, code };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_HRBILLABILITY_WHERE);

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
	 * Returns the number of h r billabilities.
	 *
	 * @return the number of h r billabilities
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

				Query q = session.createQuery(_SQL_COUNT_HRBILLABILITY);

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
	 * Initializes the h r billability persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRBillability")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRBillability>> listenersList = new ArrayList<ModelListener<HRBillability>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRBillability>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRBillabilityImpl.class.getName());
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
	private static final String _SQL_SELECT_HRBILLABILITY = "SELECT hrBillability FROM HRBillability hrBillability";
	private static final String _SQL_SELECT_HRBILLABILITY_WHERE = "SELECT hrBillability FROM HRBillability hrBillability WHERE ";
	private static final String _SQL_COUNT_HRBILLABILITY = "SELECT COUNT(hrBillability) FROM HRBillability hrBillability";
	private static final String _SQL_COUNT_HRBILLABILITY_WHERE = "SELECT COUNT(hrBillability) FROM HRBillability hrBillability WHERE ";
	private static final String _FINDER_COLUMN_G_C_GROUPID_2 = "hrBillability.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_CODE_1 = "hrBillability.code IS NULL";
	private static final String _FINDER_COLUMN_G_C_CODE_2 = "hrBillability.code = ?";
	private static final String _FINDER_COLUMN_G_C_CODE_3 = "(hrBillability.code IS NULL OR hrBillability.code = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrBillability.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRBillability exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No HRBillability exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRBillabilityPersistenceImpl.class);
	private static HRBillability _nullHRBillability = new HRBillabilityImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRBillability> toCacheModel() {
				return _nullHRBillabilityCacheModel;
			}
		};

	private static CacheModel<HRBillability> _nullHRBillabilityCacheModel = new CacheModel<HRBillability>() {
			public HRBillability toEntityModel() {
				return _nullHRBillability;
			}
		};
}