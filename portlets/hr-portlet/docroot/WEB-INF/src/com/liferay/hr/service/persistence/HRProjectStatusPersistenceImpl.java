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

import com.liferay.hr.NoSuchProjectStatusException;
import com.liferay.hr.model.HRProjectStatus;
import com.liferay.hr.model.impl.HRProjectStatusImpl;
import com.liferay.hr.model.impl.HRProjectStatusModelImpl;

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
 * The persistence implementation for the h r project status service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRProjectStatusPersistence
 * @see HRProjectStatusUtil
 * @generated
 */
public class HRProjectStatusPersistenceImpl extends BasePersistenceImpl<HRProjectStatus>
	implements HRProjectStatusPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRProjectStatusUtil} to access the h r project status persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRProjectStatusImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_C = new FinderPath(HRProjectStatusModelImpl.ENTITY_CACHE_ENABLED,
			HRProjectStatusModelImpl.FINDER_CACHE_ENABLED,
			HRProjectStatusImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByG_C",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_G_C = new FinderPath(HRProjectStatusModelImpl.ENTITY_CACHE_ENABLED,
			HRProjectStatusModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByG_C",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRProjectStatusModelImpl.ENTITY_CACHE_ENABLED,
			HRProjectStatusModelImpl.FINDER_CACHE_ENABLED,
			HRProjectStatusImpl.class, FINDER_CLASS_NAME_LIST, "findAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRProjectStatusModelImpl.ENTITY_CACHE_ENABLED,
			HRProjectStatusModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r project status in the entity cache if it is enabled.
	 *
	 * @param hrProjectStatus the h r project status
	 */
	public void cacheResult(HRProjectStatus hrProjectStatus) {
		EntityCacheUtil.putResult(HRProjectStatusModelImpl.ENTITY_CACHE_ENABLED,
			HRProjectStatusImpl.class, hrProjectStatus.getPrimaryKey(),
			hrProjectStatus);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
			new Object[] {
				Long.valueOf(hrProjectStatus.getGroupId()),
				
			hrProjectStatus.getCode()
			}, hrProjectStatus);

		hrProjectStatus.resetOriginalValues();
	}

	/**
	 * Caches the h r project statuses in the entity cache if it is enabled.
	 *
	 * @param hrProjectStatuses the h r project statuses
	 */
	public void cacheResult(List<HRProjectStatus> hrProjectStatuses) {
		for (HRProjectStatus hrProjectStatus : hrProjectStatuses) {
			if (EntityCacheUtil.getResult(
						HRProjectStatusModelImpl.ENTITY_CACHE_ENABLED,
						HRProjectStatusImpl.class,
						hrProjectStatus.getPrimaryKey(), this) == null) {
				cacheResult(hrProjectStatus);
			}
		}
	}

	/**
	 * Clears the cache for all h r project statuses.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRProjectStatusImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRProjectStatusImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r project status.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRProjectStatus hrProjectStatus) {
		EntityCacheUtil.removeResult(HRProjectStatusModelImpl.ENTITY_CACHE_ENABLED,
			HRProjectStatusImpl.class, hrProjectStatus.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_C,
			new Object[] {
				Long.valueOf(hrProjectStatus.getGroupId()),
				
			hrProjectStatus.getCode()
			});
	}

	/**
	 * Creates a new h r project status with the primary key. Does not add the h r project status to the database.
	 *
	 * @param hrProjectStatusId the primary key for the new h r project status
	 * @return the new h r project status
	 */
	public HRProjectStatus create(long hrProjectStatusId) {
		HRProjectStatus hrProjectStatus = new HRProjectStatusImpl();

		hrProjectStatus.setNew(true);
		hrProjectStatus.setPrimaryKey(hrProjectStatusId);

		return hrProjectStatus;
	}

	/**
	 * Removes the h r project status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r project status
	 * @return the h r project status that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r project status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRProjectStatus remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r project status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrProjectStatusId the primary key of the h r project status
	 * @return the h r project status that was removed
	 * @throws com.liferay.hr.NoSuchProjectStatusException if a h r project status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRProjectStatus remove(long hrProjectStatusId)
		throws NoSuchProjectStatusException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRProjectStatus hrProjectStatus = (HRProjectStatus)session.get(HRProjectStatusImpl.class,
					Long.valueOf(hrProjectStatusId));

			if (hrProjectStatus == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						hrProjectStatusId);
				}

				throw new NoSuchProjectStatusException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrProjectStatusId);
			}

			return hrProjectStatusPersistence.remove(hrProjectStatus);
		}
		catch (NoSuchProjectStatusException nsee) {
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
	 * Removes the h r project status from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrProjectStatus the h r project status
	 * @return the h r project status that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRProjectStatus remove(HRProjectStatus hrProjectStatus)
		throws SystemException {
		return super.remove(hrProjectStatus);
	}

	@Override
	protected HRProjectStatus removeImpl(HRProjectStatus hrProjectStatus)
		throws SystemException {
		hrProjectStatus = toUnwrappedModel(hrProjectStatus);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrProjectStatus);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		HRProjectStatusModelImpl hrProjectStatusModelImpl = (HRProjectStatusModelImpl)hrProjectStatus;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_C,
			new Object[] {
				Long.valueOf(hrProjectStatusModelImpl.getGroupId()),
				
			hrProjectStatusModelImpl.getCode()
			});

		EntityCacheUtil.removeResult(HRProjectStatusModelImpl.ENTITY_CACHE_ENABLED,
			HRProjectStatusImpl.class, hrProjectStatus.getPrimaryKey());

		return hrProjectStatus;
	}

	@Override
	public HRProjectStatus updateImpl(
		com.liferay.hr.model.HRProjectStatus hrProjectStatus, boolean merge)
		throws SystemException {
		hrProjectStatus = toUnwrappedModel(hrProjectStatus);

		boolean isNew = hrProjectStatus.isNew();

		HRProjectStatusModelImpl hrProjectStatusModelImpl = (HRProjectStatusModelImpl)hrProjectStatus;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrProjectStatus, merge);

			hrProjectStatus.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRProjectStatusModelImpl.ENTITY_CACHE_ENABLED,
			HRProjectStatusImpl.class, hrProjectStatus.getPrimaryKey(),
			hrProjectStatus);

		if (!isNew &&
				((hrProjectStatus.getGroupId() != hrProjectStatusModelImpl.getOriginalGroupId()) ||
				!Validator.equals(hrProjectStatus.getCode(),
					hrProjectStatusModelImpl.getOriginalCode()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_C,
				new Object[] {
					Long.valueOf(hrProjectStatusModelImpl.getOriginalGroupId()),
					
				hrProjectStatusModelImpl.getOriginalCode()
				});
		}

		if (isNew ||
				((hrProjectStatus.getGroupId() != hrProjectStatusModelImpl.getOriginalGroupId()) ||
				!Validator.equals(hrProjectStatus.getCode(),
					hrProjectStatusModelImpl.getOriginalCode()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
				new Object[] {
					Long.valueOf(hrProjectStatus.getGroupId()),
					
				hrProjectStatus.getCode()
				}, hrProjectStatus);
		}

		return hrProjectStatus;
	}

	protected HRProjectStatus toUnwrappedModel(HRProjectStatus hrProjectStatus) {
		if (hrProjectStatus instanceof HRProjectStatusImpl) {
			return hrProjectStatus;
		}

		HRProjectStatusImpl hrProjectStatusImpl = new HRProjectStatusImpl();

		hrProjectStatusImpl.setNew(hrProjectStatus.isNew());
		hrProjectStatusImpl.setPrimaryKey(hrProjectStatus.getPrimaryKey());

		hrProjectStatusImpl.setHrProjectStatusId(hrProjectStatus.getHrProjectStatusId());
		hrProjectStatusImpl.setGroupId(hrProjectStatus.getGroupId());
		hrProjectStatusImpl.setCompanyId(hrProjectStatus.getCompanyId());
		hrProjectStatusImpl.setUserId(hrProjectStatus.getUserId());
		hrProjectStatusImpl.setUserName(hrProjectStatus.getUserName());
		hrProjectStatusImpl.setCreateDate(hrProjectStatus.getCreateDate());
		hrProjectStatusImpl.setModifiedDate(hrProjectStatus.getModifiedDate());
		hrProjectStatusImpl.setCode(hrProjectStatus.getCode());
		hrProjectStatusImpl.setName(hrProjectStatus.getName());
		hrProjectStatusImpl.setDescription(hrProjectStatus.getDescription());

		return hrProjectStatusImpl;
	}

	/**
	 * Returns the h r project status with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r project status
	 * @return the h r project status
	 * @throws com.liferay.portal.NoSuchModelException if a h r project status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRProjectStatus findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r project status with the primary key or throws a {@link com.liferay.hr.NoSuchProjectStatusException} if it could not be found.
	 *
	 * @param hrProjectStatusId the primary key of the h r project status
	 * @return the h r project status
	 * @throws com.liferay.hr.NoSuchProjectStatusException if a h r project status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRProjectStatus findByPrimaryKey(long hrProjectStatusId)
		throws NoSuchProjectStatusException, SystemException {
		HRProjectStatus hrProjectStatus = fetchByPrimaryKey(hrProjectStatusId);

		if (hrProjectStatus == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrProjectStatusId);
			}

			throw new NoSuchProjectStatusException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrProjectStatusId);
		}

		return hrProjectStatus;
	}

	/**
	 * Returns the h r project status with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r project status
	 * @return the h r project status, or <code>null</code> if a h r project status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRProjectStatus fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r project status with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrProjectStatusId the primary key of the h r project status
	 * @return the h r project status, or <code>null</code> if a h r project status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRProjectStatus fetchByPrimaryKey(long hrProjectStatusId)
		throws SystemException {
		HRProjectStatus hrProjectStatus = (HRProjectStatus)EntityCacheUtil.getResult(HRProjectStatusModelImpl.ENTITY_CACHE_ENABLED,
				HRProjectStatusImpl.class, hrProjectStatusId, this);

		if (hrProjectStatus == _nullHRProjectStatus) {
			return null;
		}

		if (hrProjectStatus == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrProjectStatus = (HRProjectStatus)session.get(HRProjectStatusImpl.class,
						Long.valueOf(hrProjectStatusId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrProjectStatus != null) {
					cacheResult(hrProjectStatus);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRProjectStatusModelImpl.ENTITY_CACHE_ENABLED,
						HRProjectStatusImpl.class, hrProjectStatusId,
						_nullHRProjectStatus);
				}

				closeSession(session);
			}
		}

		return hrProjectStatus;
	}

	/**
	 * Returns the h r project status where groupId = &#63; and code = &#63; or throws a {@link com.liferay.hr.NoSuchProjectStatusException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @return the matching h r project status
	 * @throws com.liferay.hr.NoSuchProjectStatusException if a matching h r project status could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRProjectStatus findByG_C(long groupId, String code)
		throws NoSuchProjectStatusException, SystemException {
		HRProjectStatus hrProjectStatus = fetchByG_C(groupId, code);

		if (hrProjectStatus == null) {
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

			throw new NoSuchProjectStatusException(msg.toString());
		}

		return hrProjectStatus;
	}

	/**
	 * Returns the h r project status where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @return the matching h r project status, or <code>null</code> if a matching h r project status could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRProjectStatus fetchByG_C(long groupId, String code)
		throws SystemException {
		return fetchByG_C(groupId, code, true);
	}

	/**
	 * Returns the h r project status where groupId = &#63; and code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching h r project status, or <code>null</code> if a matching h r project status could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRProjectStatus fetchByG_C(long groupId, String code,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, code };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_G_C,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_HRPROJECTSTATUS_WHERE);

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

				List<HRProjectStatus> list = q.list();

				result = list;

				HRProjectStatus hrProjectStatus = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
						finderArgs, list);
				}
				else {
					hrProjectStatus = list.get(0);

					cacheResult(hrProjectStatus);

					if ((hrProjectStatus.getGroupId() != groupId) ||
							(hrProjectStatus.getCode() == null) ||
							!hrProjectStatus.getCode().equals(code)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_C,
							finderArgs, hrProjectStatus);
					}
				}

				return hrProjectStatus;
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
				return (HRProjectStatus)result;
			}
		}
	}

	/**
	 * Returns all the h r project statuses.
	 *
	 * @return the h r project statuses
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRProjectStatus> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r project statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r project statuses
	 * @param end the upper bound of the range of h r project statuses (not inclusive)
	 * @return the range of h r project statuses
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRProjectStatus> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r project statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r project statuses
	 * @param end the upper bound of the range of h r project statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r project statuses
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRProjectStatus> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRProjectStatus> list = (List<HRProjectStatus>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRPROJECTSTATUS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRPROJECTSTATUS;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRProjectStatus>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRProjectStatus>)QueryUtil.list(q,
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
	 * Removes the h r project status where groupId = &#63; and code = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_C(long groupId, String code)
		throws NoSuchProjectStatusException, SystemException {
		HRProjectStatus hrProjectStatus = findByG_C(groupId, code);

		hrProjectStatusPersistence.remove(hrProjectStatus);
	}

	/**
	 * Removes all the h r project statuses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRProjectStatus hrProjectStatus : findAll()) {
			hrProjectStatusPersistence.remove(hrProjectStatus);
		}
	}

	/**
	 * Returns the number of h r project statuses where groupId = &#63; and code = &#63;.
	 *
	 * @param groupId the group ID
	 * @param code the code
	 * @return the number of matching h r project statuses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_C(long groupId, String code) throws SystemException {
		Object[] finderArgs = new Object[] { groupId, code };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_HRPROJECTSTATUS_WHERE);

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
	 * Returns the number of h r project statuses.
	 *
	 * @return the number of h r project statuses
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

				Query q = session.createQuery(_SQL_COUNT_HRPROJECTSTATUS);

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
	 * Initializes the h r project status persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRProjectStatus")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRProjectStatus>> listenersList = new ArrayList<ModelListener<HRProjectStatus>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRProjectStatus>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(HRProjectStatusImpl.class.getName());
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
	private static final String _SQL_SELECT_HRPROJECTSTATUS = "SELECT hrProjectStatus FROM HRProjectStatus hrProjectStatus";
	private static final String _SQL_SELECT_HRPROJECTSTATUS_WHERE = "SELECT hrProjectStatus FROM HRProjectStatus hrProjectStatus WHERE ";
	private static final String _SQL_COUNT_HRPROJECTSTATUS = "SELECT COUNT(hrProjectStatus) FROM HRProjectStatus hrProjectStatus";
	private static final String _SQL_COUNT_HRPROJECTSTATUS_WHERE = "SELECT COUNT(hrProjectStatus) FROM HRProjectStatus hrProjectStatus WHERE ";
	private static final String _FINDER_COLUMN_G_C_GROUPID_2 = "hrProjectStatus.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_CODE_1 = "hrProjectStatus.code IS NULL";
	private static final String _FINDER_COLUMN_G_C_CODE_2 = "hrProjectStatus.code = ?";
	private static final String _FINDER_COLUMN_G_C_CODE_3 = "(hrProjectStatus.code IS NULL OR hrProjectStatus.code = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrProjectStatus.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRProjectStatus exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No HRProjectStatus exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRProjectStatusPersistenceImpl.class);
	private static HRProjectStatus _nullHRProjectStatus = new HRProjectStatusImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRProjectStatus> toCacheModel() {
				return _nullHRProjectStatusCacheModel;
			}
		};

	private static CacheModel<HRProjectStatus> _nullHRProjectStatusCacheModel = new CacheModel<HRProjectStatus>() {
			public HRProjectStatus toEntityModel() {
				return _nullHRProjectStatus;
			}
		};
}