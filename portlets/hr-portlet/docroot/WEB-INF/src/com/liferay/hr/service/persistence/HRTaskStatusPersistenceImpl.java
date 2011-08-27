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

import com.liferay.hr.NoSuchTaskStatusException;
import com.liferay.hr.model.HRTaskStatus;
import com.liferay.hr.model.impl.HRTaskStatusImpl;
import com.liferay.hr.model.impl.HRTaskStatusModelImpl;

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
 * The persistence implementation for the h r task status service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRTaskStatusPersistence
 * @see HRTaskStatusUtil
 * @generated
 */
public class HRTaskStatusPersistenceImpl extends BasePersistenceImpl<HRTaskStatus>
	implements HRTaskStatusPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRTaskStatusUtil} to access the h r task status persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRTaskStatusImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_C = new FinderPath(HRTaskStatusModelImpl.ENTITY_CACHE_ENABLED,
			HRTaskStatusModelImpl.FINDER_CACHE_ENABLED, HRTaskStatusImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByG_C",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_G_C = new FinderPath(HRTaskStatusModelImpl.ENTITY_CACHE_ENABLED,
			HRTaskStatusModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByG_C",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRTaskStatusModelImpl.ENTITY_CACHE_ENABLED,
			HRTaskStatusModelImpl.FINDER_CACHE_ENABLED, HRTaskStatusImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRTaskStatusModelImpl.ENTITY_CACHE_ENABLED,
			HRTaskStatusModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r task status in the entity cache if it is enabled.
	 *
	 * @param hrTaskStatus the h r task status
	 */
	public void cacheResult(HRTaskStatus hrTaskStatus) {
		EntityCacheUtil.putResult(HRTaskStatusModelImpl.ENTITY_CACHE_ENABLED,
			HRTaskStatusImpl.class, hrTaskStatus.getPrimaryKey(), hrTaskStatus);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
			new Object[] {
				Long.valueOf(hrTaskStatus.getGroupId()),
				
			hrTaskStatus.getCode()
			}, hrTaskStatus);

		hrTaskStatus.resetOriginalValues();
	}

	/**
	 * Caches the h r task statuses in the entity cache if it is enabled.
	 *
	 * @param hrTaskStatuses the h r task statuses
	 */
	public void cacheResult(List<HRTaskStatus> hrTaskStatuses) {
		for (HRTaskStatus hrTaskStatus : hrTaskStatuses) {
			if (EntityCacheUtil.getResult(
						HRTaskStatusModelImpl.ENTITY_CACHE_ENABLED,
						HRTaskStatusImpl.class, hrTaskStatus.getPrimaryKey(),
						this) == null) {
				cacheResult(hrTaskStatus);
			}
		}
	}

	/**
	 * Clears the cache for all h r task statuses.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRTaskStatusImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRTaskStatusImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r task status.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRTaskStatus hrTaskStatus) {
		EntityCacheUtil.removeResult(HRTaskStatusModelImpl.ENTITY_CACHE_ENABLED,
			HRTaskStatusImpl.class, hrTaskStatus.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_C,
			new Object[] {
				Long.valueOf(hrTaskStatus.getGroupId()),
				
			hrTaskStatus.getCode()
			});
	}

	/**
	 * Creates a new h r task status with the primary key. Does not add the h r task status to the database.
	 *
	 * @param hrTaskStatusId the primary key for the new h r task status
	 * @return the new h r task status
	 */
	public HRTaskStatus create(long hrTaskStatusId) {
		HRTaskStatus hrTaskStatus = new HRTaskStatusImpl();

		hrTaskStatus.setNew(true);
		hrTaskStatus.setPrimaryKey(hrTaskStatusId);

		return hrTaskStatus;
	}

	/**
	 * Removes the h r task status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r task status
	 * @return the h r task status that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r task status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTaskStatus remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r task status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrTaskStatusId the primary key of the h r task status
	 * @return the h r task status that was removed
	 * @throws com.liferay.hr.NoSuchTaskStatusException if a h r task status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTaskStatus remove(long hrTaskStatusId)
		throws NoSuchTaskStatusException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRTaskStatus hrTaskStatus = (HRTaskStatus)session.get(HRTaskStatusImpl.class,
					Long.valueOf(hrTaskStatusId));

			if (hrTaskStatus == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						hrTaskStatusId);
				}

				throw new NoSuchTaskStatusException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrTaskStatusId);
			}

			return hrTaskStatusPersistence.remove(hrTaskStatus);
		}
		catch (NoSuchTaskStatusException nsee) {
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
	 * Removes the h r task status from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrTaskStatus the h r task status
	 * @return the h r task status that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTaskStatus remove(HRTaskStatus hrTaskStatus)
		throws SystemException {
		return super.remove(hrTaskStatus);
	}

	@Override
	protected HRTaskStatus removeImpl(HRTaskStatus hrTaskStatus)
		throws SystemException {
		hrTaskStatus = toUnwrappedModel(hrTaskStatus);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrTaskStatus);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		HRTaskStatusModelImpl hrTaskStatusModelImpl = (HRTaskStatusModelImpl)hrTaskStatus;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_C,
			new Object[] {
				Long.valueOf(hrTaskStatusModelImpl.getGroupId()),
				
			hrTaskStatusModelImpl.getCode()
			});

		EntityCacheUtil.removeResult(HRTaskStatusModelImpl.ENTITY_CACHE_ENABLED,
			HRTaskStatusImpl.class, hrTaskStatus.getPrimaryKey());

		return hrTaskStatus;
	}

	@Override
	public HRTaskStatus updateImpl(
		com.liferay.hr.model.HRTaskStatus hrTaskStatus, boolean merge)
		throws SystemException {
		hrTaskStatus = toUnwrappedModel(hrTaskStatus);

		boolean isNew = hrTaskStatus.isNew();

		HRTaskStatusModelImpl hrTaskStatusModelImpl = (HRTaskStatusModelImpl)hrTaskStatus;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrTaskStatus, merge);

			hrTaskStatus.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRTaskStatusModelImpl.ENTITY_CACHE_ENABLED,
			HRTaskStatusImpl.class, hrTaskStatus.getPrimaryKey(), hrTaskStatus);

		if (!isNew &&
				((hrTaskStatus.getGroupId() != hrTaskStatusModelImpl.getOriginalGroupId()) ||
				!Validator.equals(hrTaskStatus.getCode(),
					hrTaskStatusModelImpl.getOriginalCode()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_C,
				new Object[] {
					Long.valueOf(hrTaskStatusModelImpl.getOriginalGroupId()),
					
				hrTaskStatusModelImpl.getOriginalCode()
				});
		}

		if (isNew ||
				((hrTaskStatus.getGroupId() != hrTaskStatusModelImpl.getOriginalGroupId()) ||
				!Validator.equals(hrTaskStatus.getCode(),
					hrTaskStatusModelImpl.getOriginalCode()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
				new Object[] {
					Long.valueOf(hrTaskStatus.getGroupId()),
					
				hrTaskStatus.getCode()
				}, hrTaskStatus);
		}

		return hrTaskStatus;
	}

	protected HRTaskStatus toUnwrappedModel(HRTaskStatus hrTaskStatus) {
		if (hrTaskStatus instanceof HRTaskStatusImpl) {
			return hrTaskStatus;
		}

		HRTaskStatusImpl hrTaskStatusImpl = new HRTaskStatusImpl();

		hrTaskStatusImpl.setNew(hrTaskStatus.isNew());
		hrTaskStatusImpl.setPrimaryKey(hrTaskStatus.getPrimaryKey());

		hrTaskStatusImpl.setHrTaskStatusId(hrTaskStatus.getHrTaskStatusId());
		hrTaskStatusImpl.setGroupId(hrTaskStatus.getGroupId());
		hrTaskStatusImpl.setCompanyId(hrTaskStatus.getCompanyId());
		hrTaskStatusImpl.setUserId(hrTaskStatus.getUserId());
		hrTaskStatusImpl.setUserName(hrTaskStatus.getUserName());
		hrTaskStatusImpl.setCreateDate(hrTaskStatus.getCreateDate());
		hrTaskStatusImpl.setModifiedDate(hrTaskStatus.getModifiedDate());
		hrTaskStatusImpl.setCode(hrTaskStatus.getCode());
		hrTaskStatusImpl.setName(hrTaskStatus.getName());
		hrTaskStatusImpl.setDescription(hrTaskStatus.getDescription());

		return hrTaskStatusImpl;
	}

	/**
	 * Returns the h r task status with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r task status
	 * @return the h r task status
	 * @throws com.liferay.portal.NoSuchModelException if a h r task status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTaskStatus findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r task status with the primary key or throws a {@link com.liferay.hr.NoSuchTaskStatusException} if it could not be found.
	 *
	 * @param hrTaskStatusId the primary key of the h r task status
	 * @return the h r task status
	 * @throws com.liferay.hr.NoSuchTaskStatusException if a h r task status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTaskStatus findByPrimaryKey(long hrTaskStatusId)
		throws NoSuchTaskStatusException, SystemException {
		HRTaskStatus hrTaskStatus = fetchByPrimaryKey(hrTaskStatusId);

		if (hrTaskStatus == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrTaskStatusId);
			}

			throw new NoSuchTaskStatusException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrTaskStatusId);
		}

		return hrTaskStatus;
	}

	/**
	 * Returns the h r task status with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r task status
	 * @return the h r task status, or <code>null</code> if a h r task status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRTaskStatus fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r task status with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrTaskStatusId the primary key of the h r task status
	 * @return the h r task status, or <code>null</code> if a h r task status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTaskStatus fetchByPrimaryKey(long hrTaskStatusId)
		throws SystemException {
		HRTaskStatus hrTaskStatus = (HRTaskStatus)EntityCacheUtil.getResult(HRTaskStatusModelImpl.ENTITY_CACHE_ENABLED,
				HRTaskStatusImpl.class, hrTaskStatusId, this);

		if (hrTaskStatus == _nullHRTaskStatus) {
			return null;
		}

		if (hrTaskStatus == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrTaskStatus = (HRTaskStatus)session.get(HRTaskStatusImpl.class,
						Long.valueOf(hrTaskStatusId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrTaskStatus != null) {
					cacheResult(hrTaskStatus);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRTaskStatusModelImpl.ENTITY_CACHE_ENABLED,
						HRTaskStatusImpl.class, hrTaskStatusId,
						_nullHRTaskStatus);
				}

				closeSession(session);
			}
		}

		return hrTaskStatus;
	}

	/**
	 * Returns the h r task status where groupId = &#63; and code = &#63; or throws a {@link com.liferay.hr.NoSuchTaskStatusException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @return the matching h r task status
	 * @throws com.liferay.hr.NoSuchTaskStatusException if a matching h r task status could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTaskStatus findByG_C(long groupId, String code)
		throws NoSuchTaskStatusException, SystemException {
		HRTaskStatus hrTaskStatus = fetchByG_C(groupId, code);

		if (hrTaskStatus == null) {
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

			throw new NoSuchTaskStatusException(msg.toString());
		}

		return hrTaskStatus;
	}

	/**
	 * Returns the h r task status where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @return the matching h r task status, or <code>null</code> if a matching h r task status could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTaskStatus fetchByG_C(long groupId, String code)
		throws SystemException {
		return fetchByG_C(groupId, code, true);
	}

	/**
	 * Returns the h r task status where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching h r task status, or <code>null</code> if a matching h r task status could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRTaskStatus fetchByG_C(long groupId, String code,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, code };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_G_C,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_HRTASKSTATUS_WHERE);

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

				List<HRTaskStatus> list = q.list();

				result = list;

				HRTaskStatus hrTaskStatus = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
						finderArgs, list);
				}
				else {
					hrTaskStatus = list.get(0);

					cacheResult(hrTaskStatus);

					if ((hrTaskStatus.getGroupId() != groupId) ||
							(hrTaskStatus.getCode() == null) ||
							!hrTaskStatus.getCode().equals(code)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
							finderArgs, hrTaskStatus);
					}
				}

				return hrTaskStatus;
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
				return (HRTaskStatus)result;
			}
		}
	}

	/**
	 * Returns all the h r task statuses.
	 *
	 * @return the h r task statuses
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRTaskStatus> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r task statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r task statuses
	 * @param end the upper bound of the range of h r task statuses (not inclusive)
	 * @return the range of h r task statuses
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRTaskStatus> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r task statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r task statuses
	 * @param end the upper bound of the range of h r task statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r task statuses
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRTaskStatus> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRTaskStatus> list = (List<HRTaskStatus>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRTASKSTATUS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRTASKSTATUS;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRTaskStatus>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRTaskStatus>)QueryUtil.list(q, getDialect(),
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
	 * Removes the h r task status where groupId = &#63; and code = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_C(long groupId, String code)
		throws NoSuchTaskStatusException, SystemException {
		HRTaskStatus hrTaskStatus = findByG_C(groupId, code);

		hrTaskStatusPersistence.remove(hrTaskStatus);
	}

	/**
	 * Removes all the h r task statuses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRTaskStatus hrTaskStatus : findAll()) {
			hrTaskStatusPersistence.remove(hrTaskStatus);
		}
	}

	/**
	 * Returns the number of h r task statuses where groupId = &#63; and code = &#63;.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @return the number of matching h r task statuses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_C(long groupId, String code) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, code };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_HRTASKSTATUS_WHERE);

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
	 * Returns the number of h r task statuses.
	 *
	 * @return the number of h r task statuses
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

				Query q = session.createQuery(_SQL_COUNT_HRTASKSTATUS);

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
	 * Initializes the h r task status persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRTaskStatus")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRTaskStatus>> listenersList = new ArrayList<ModelListener<HRTaskStatus>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRTaskStatus>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRTaskStatusImpl.class.getName());
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
	private static final String _SQL_SELECT_HRTASKSTATUS = "SELECT hrTaskStatus FROM HRTaskStatus hrTaskStatus";
	private static final String _SQL_SELECT_HRTASKSTATUS_WHERE = "SELECT hrTaskStatus FROM HRTaskStatus hrTaskStatus WHERE ";
	private static final String _SQL_COUNT_HRTASKSTATUS = "SELECT COUNT(hrTaskStatus) FROM HRTaskStatus hrTaskStatus";
	private static final String _SQL_COUNT_HRTASKSTATUS_WHERE = "SELECT COUNT(hrTaskStatus) FROM HRTaskStatus hrTaskStatus WHERE ";
	private static final String _FINDER_COLUMN_G_C_GROUPID_2 = "hrTaskStatus.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_CODE_1 = "hrTaskStatus.code IS NULL";
	private static final String _FINDER_COLUMN_G_C_CODE_2 = "hrTaskStatus.code = ?";
	private static final String _FINDER_COLUMN_G_C_CODE_3 = "(hrTaskStatus.code IS NULL OR hrTaskStatus.code = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrTaskStatus.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRTaskStatus exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No HRTaskStatus exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRTaskStatusPersistenceImpl.class);
	private static HRTaskStatus _nullHRTaskStatus = new HRTaskStatusImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRTaskStatus> toCacheModel() {
				return _nullHRTaskStatusCacheModel;
			}
		};

	private static CacheModel<HRTaskStatus> _nullHRTaskStatusCacheModel = new CacheModel<HRTaskStatus>() {
			public HRTaskStatus toEntityModel() {
				return _nullHRTaskStatus;
			}
		};
}