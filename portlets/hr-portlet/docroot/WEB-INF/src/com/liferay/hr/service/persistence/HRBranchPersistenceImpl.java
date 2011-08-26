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

import com.liferay.hr.NoSuchBranchException;
import com.liferay.hr.model.HRBranch;
import com.liferay.hr.model.impl.HRBranchImpl;
import com.liferay.hr.model.impl.HRBranchModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQuery;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.RowMapper;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
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
import java.util.Set;

/**
 * The persistence implementation for the h r branch service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRBranchPersistence
 * @see HRBranchUtil
 * @generated
 */
public class HRBranchPersistenceImpl extends BasePersistenceImpl<HRBranch>
	implements HRBranchPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRBranchUtil} to access the h r branch persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRBranchImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRBranchModelImpl.ENTITY_CACHE_ENABLED,
			HRBranchModelImpl.FINDER_CACHE_ENABLED, HRBranchImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRBranchModelImpl.ENTITY_CACHE_ENABLED,
			HRBranchModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r branch in the entity cache if it is enabled.
	 *
	 * @param hrBranch the h r branch
	 */
	public void cacheResult(HRBranch hrBranch) {
		EntityCacheUtil.putResult(HRBranchModelImpl.ENTITY_CACHE_ENABLED,
			HRBranchImpl.class, hrBranch.getPrimaryKey(), hrBranch);

		hrBranch.resetOriginalValues();
	}

	/**
	 * Caches the h r branchs in the entity cache if it is enabled.
	 *
	 * @param hrBranchs the h r branchs
	 */
	public void cacheResult(List<HRBranch> hrBranchs) {
		for (HRBranch hrBranch : hrBranchs) {
			if (EntityCacheUtil.getResult(
						HRBranchModelImpl.ENTITY_CACHE_ENABLED,
						HRBranchImpl.class, hrBranch.getPrimaryKey(), this) == null) {
				cacheResult(hrBranch);
			}
		}
	}

	/**
	 * Clears the cache for all h r branchs.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRBranchImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRBranchImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r branch.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRBranch hrBranch) {
		EntityCacheUtil.removeResult(HRBranchModelImpl.ENTITY_CACHE_ENABLED,
			HRBranchImpl.class, hrBranch.getPrimaryKey());
	}

	/**
	 * Creates a new h r branch with the primary key. Does not add the h r branch to the database.
	 *
	 * @param hrBranchId the primary key for the new h r branch
	 * @return the new h r branch
	 */
	public HRBranch create(long hrBranchId) {
		HRBranch hrBranch = new HRBranchImpl();

		hrBranch.setNew(true);
		hrBranch.setPrimaryKey(hrBranchId);

		return hrBranch;
	}

	/**
	 * Removes the h r branch with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r branch
	 * @return the h r branch that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r branch with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRBranch remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r branch with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrBranchId the primary key of the h r branch
	 * @return the h r branch that was removed
	 * @throws com.liferay.hr.NoSuchBranchException if a h r branch with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRBranch remove(long hrBranchId)
		throws NoSuchBranchException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRBranch hrBranch = (HRBranch)session.get(HRBranchImpl.class,
					Long.valueOf(hrBranchId));

			if (hrBranch == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrBranchId);
				}

				throw new NoSuchBranchException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrBranchId);
			}

			return hrBranchPersistence.remove(hrBranch);
		}
		catch (NoSuchBranchException nsee) {
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
	 * Removes the h r branch from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrBranch the h r branch
	 * @return the h r branch that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRBranch remove(HRBranch hrBranch) throws SystemException {
		return super.remove(hrBranch);
	}

	@Override
	protected HRBranch removeImpl(HRBranch hrBranch) throws SystemException {
		hrBranch = toUnwrappedModel(hrBranch);

		try {
			clearHRJobTitles.clear(hrBranch.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRBranchModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrBranch);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRBranchModelImpl.ENTITY_CACHE_ENABLED,
			HRBranchImpl.class, hrBranch.getPrimaryKey());

		return hrBranch;
	}

	@Override
	public HRBranch updateImpl(com.liferay.hr.model.HRBranch hrBranch,
		boolean merge) throws SystemException {
		hrBranch = toUnwrappedModel(hrBranch);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrBranch, merge);

			hrBranch.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRBranchModelImpl.ENTITY_CACHE_ENABLED,
			HRBranchImpl.class, hrBranch.getPrimaryKey(), hrBranch);

		return hrBranch;
	}

	protected HRBranch toUnwrappedModel(HRBranch hrBranch) {
		if (hrBranch instanceof HRBranchImpl) {
			return hrBranch;
		}

		HRBranchImpl hrBranchImpl = new HRBranchImpl();

		hrBranchImpl.setNew(hrBranch.isNew());
		hrBranchImpl.setPrimaryKey(hrBranch.getPrimaryKey());

		hrBranchImpl.setHrBranchId(hrBranch.getHrBranchId());
		hrBranchImpl.setGroupId(hrBranch.getGroupId());
		hrBranchImpl.setCompanyId(hrBranch.getCompanyId());
		hrBranchImpl.setUserId(hrBranch.getUserId());
		hrBranchImpl.setUserName(hrBranch.getUserName());
		hrBranchImpl.setCreateDate(hrBranch.getCreateDate());
		hrBranchImpl.setModifiedDate(hrBranch.getModifiedDate());
		hrBranchImpl.setOrganizationId(hrBranch.getOrganizationId());

		return hrBranchImpl;
	}

	/**
	 * Returns the h r branch with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r branch
	 * @return the h r branch
	 * @throws com.liferay.portal.NoSuchModelException if a h r branch with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRBranch findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r branch with the primary key or throws a {@link com.liferay.hr.NoSuchBranchException} if it could not be found.
	 *
	 * @param hrBranchId the primary key of the h r branch
	 * @return the h r branch
	 * @throws com.liferay.hr.NoSuchBranchException if a h r branch with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRBranch findByPrimaryKey(long hrBranchId)
		throws NoSuchBranchException, SystemException {
		HRBranch hrBranch = fetchByPrimaryKey(hrBranchId);

		if (hrBranch == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrBranchId);
			}

			throw new NoSuchBranchException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrBranchId);
		}

		return hrBranch;
	}

	/**
	 * Returns the h r branch with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r branch
	 * @return the h r branch, or <code>null</code> if a h r branch with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRBranch fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r branch with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrBranchId the primary key of the h r branch
	 * @return the h r branch, or <code>null</code> if a h r branch with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRBranch fetchByPrimaryKey(long hrBranchId)
		throws SystemException {
		HRBranch hrBranch = (HRBranch)EntityCacheUtil.getResult(HRBranchModelImpl.ENTITY_CACHE_ENABLED,
				HRBranchImpl.class, hrBranchId, this);

		if (hrBranch == _nullHRBranch) {
			return null;
		}

		if (hrBranch == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrBranch = (HRBranch)session.get(HRBranchImpl.class,
						Long.valueOf(hrBranchId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrBranch != null) {
					cacheResult(hrBranch);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRBranchModelImpl.ENTITY_CACHE_ENABLED,
						HRBranchImpl.class, hrBranchId, _nullHRBranch);
				}

				closeSession(session);
			}
		}

		return hrBranch;
	}

	/**
	 * Returns all the h r branchs.
	 *
	 * @return the h r branchs
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRBranch> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r branchs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r branchs
	 * @param end the upper bound of the range of h r branchs (not inclusive)
	 * @return the range of h r branchs
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRBranch> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r branchs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r branchs
	 * @param end the upper bound of the range of h r branchs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r branchs
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRBranch> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRBranch> list = (List<HRBranch>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRBRANCH);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRBRANCH;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRBranch>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRBranch>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the h r branchs from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRBranch hrBranch : findAll()) {
			hrBranchPersistence.remove(hrBranch);
		}
	}

	/**
	 * Returns the number of h r branchs.
	 *
	 * @return the number of h r branchs
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

				Query q = session.createQuery(_SQL_COUNT_HRBRANCH);

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
	 * Returns all the h r job titles associated with the h r branch.
	 *
	 * @param pk the primary key of the h r branch
	 * @return the h r job titles associated with the h r branch
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.hr.model.HRJobTitle> getHRJobTitles(long pk)
		throws SystemException {
		return getHRJobTitles(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the h r job titles associated with the h r branch.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the h r branch
	 * @param start the lower bound of the range of h r branchs
	 * @param end the upper bound of the range of h r branchs (not inclusive)
	 * @return the range of h r job titles associated with the h r branch
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.hr.model.HRJobTitle> getHRJobTitles(long pk,
		int start, int end) throws SystemException {
		return getHRJobTitles(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_HRJOBTITLES = new FinderPath(com.liferay.hr.model.impl.HRJobTitleModelImpl.ENTITY_CACHE_ENABLED,
			HRBranchModelImpl.FINDER_CACHE_ENABLED_HRBRANCHES_HRJOBTITLES,
			com.liferay.hr.model.impl.HRJobTitleImpl.class,
			HRBranchModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME,
			"getHRJobTitles",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	/**
	 * Returns an ordered range of all the h r job titles associated with the h r branch.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the h r branch
	 * @param start the lower bound of the range of h r branchs
	 * @param end the upper bound of the range of h r branchs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r job titles associated with the h r branch
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.hr.model.HRJobTitle> getHRJobTitles(long pk,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				pk, String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<com.liferay.hr.model.HRJobTitle> list = (List<com.liferay.hr.model.HRJobTitle>)FinderCacheUtil.getResult(FINDER_PATH_GET_HRJOBTITLES,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETHRJOBTITLES.concat(ORDER_BY_CLAUSE)
											 .concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETHRJOBTITLES;
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("HRJobTitle",
					com.liferay.hr.model.impl.HRJobTitleImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.liferay.hr.model.HRJobTitle>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_HRJOBTITLES,
						finderArgs);
				}
				else {
					hrJobTitlePersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_HRJOBTITLES,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_HRJOBTITLES_SIZE = new FinderPath(com.liferay.hr.model.impl.HRJobTitleModelImpl.ENTITY_CACHE_ENABLED,
			HRBranchModelImpl.FINDER_CACHE_ENABLED_HRBRANCHES_HRJOBTITLES,
			Long.class,
			HRBranchModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME,
			"getHRJobTitlesSize", new String[] { Long.class.getName() });

	/**
	 * Returns the number of h r job titles associated with the h r branch.
	 *
	 * @param pk the primary key of the h r branch
	 * @return the number of h r job titles associated with the h r branch
	 * @throws SystemException if a system exception occurred
	 */
	public int getHRJobTitlesSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_HRJOBTITLES_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETHRJOBTITLESSIZE);

				q.addScalar(COUNT_COLUMN_NAME,
					com.liferay.portal.kernel.dao.orm.Type.LONG);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_GET_HRJOBTITLES_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_HRJOBTITLE = new FinderPath(com.liferay.hr.model.impl.HRJobTitleModelImpl.ENTITY_CACHE_ENABLED,
			HRBranchModelImpl.FINDER_CACHE_ENABLED_HRBRANCHES_HRJOBTITLES,
			Boolean.class,
			HRBranchModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME,
			"containsHRJobTitle",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the h r job title is associated with the h r branch.
	 *
	 * @param pk the primary key of the h r branch
	 * @param hrJobTitlePK the primary key of the h r job title
	 * @return <code>true</code> if the h r job title is associated with the h r branch; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsHRJobTitle(long pk, long hrJobTitlePK)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, hrJobTitlePK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_HRJOBTITLE,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsHRJobTitle.contains(pk,
							hrJobTitlePK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_HRJOBTITLE,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Returns <code>true</code> if the h r branch has any h r job titles associated with it.
	 *
	 * @param pk the primary key of the h r branch to check for associations with h r job titles
	 * @return <code>true</code> if the h r branch has any h r job titles associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsHRJobTitles(long pk) throws SystemException {
		if (getHRJobTitlesSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the h r branch and the h r job title. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r branch
	 * @param hrJobTitlePK the primary key of the h r job title
	 * @throws SystemException if a system exception occurred
	 */
	public void addHRJobTitle(long pk, long hrJobTitlePK)
		throws SystemException {
		try {
			addHRJobTitle.add(pk, hrJobTitlePK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRBranchModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME);
		}
	}

	/**
	 * Adds an association between the h r branch and the h r job title. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r branch
	 * @param hrJobTitle the h r job title
	 * @throws SystemException if a system exception occurred
	 */
	public void addHRJobTitle(long pk,
		com.liferay.hr.model.HRJobTitle hrJobTitle) throws SystemException {
		try {
			addHRJobTitle.add(pk, hrJobTitle.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRBranchModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME);
		}
	}

	/**
	 * Adds an association between the h r branch and the h r job titles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r branch
	 * @param hrJobTitlePKs the primary keys of the h r job titles
	 * @throws SystemException if a system exception occurred
	 */
	public void addHRJobTitles(long pk, long[] hrJobTitlePKs)
		throws SystemException {
		try {
			for (long hrJobTitlePK : hrJobTitlePKs) {
				addHRJobTitle.add(pk, hrJobTitlePK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRBranchModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME);
		}
	}

	/**
	 * Adds an association between the h r branch and the h r job titles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r branch
	 * @param hrJobTitles the h r job titles
	 * @throws SystemException if a system exception occurred
	 */
	public void addHRJobTitles(long pk,
		List<com.liferay.hr.model.HRJobTitle> hrJobTitles)
		throws SystemException {
		try {
			for (com.liferay.hr.model.HRJobTitle hrJobTitle : hrJobTitles) {
				addHRJobTitle.add(pk, hrJobTitle.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRBranchModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME);
		}
	}

	/**
	 * Clears all associations between the h r branch and its h r job titles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r branch to clear the associated h r job titles from
	 * @throws SystemException if a system exception occurred
	 */
	public void clearHRJobTitles(long pk) throws SystemException {
		try {
			clearHRJobTitles.clear(pk);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRBranchModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME);
		}
	}

	/**
	 * Removes the association between the h r branch and the h r job title. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r branch
	 * @param hrJobTitlePK the primary key of the h r job title
	 * @throws SystemException if a system exception occurred
	 */
	public void removeHRJobTitle(long pk, long hrJobTitlePK)
		throws SystemException {
		try {
			removeHRJobTitle.remove(pk, hrJobTitlePK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRBranchModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME);
		}
	}

	/**
	 * Removes the association between the h r branch and the h r job title. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r branch
	 * @param hrJobTitle the h r job title
	 * @throws SystemException if a system exception occurred
	 */
	public void removeHRJobTitle(long pk,
		com.liferay.hr.model.HRJobTitle hrJobTitle) throws SystemException {
		try {
			removeHRJobTitle.remove(pk, hrJobTitle.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRBranchModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME);
		}
	}

	/**
	 * Removes the association between the h r branch and the h r job titles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r branch
	 * @param hrJobTitlePKs the primary keys of the h r job titles
	 * @throws SystemException if a system exception occurred
	 */
	public void removeHRJobTitles(long pk, long[] hrJobTitlePKs)
		throws SystemException {
		try {
			for (long hrJobTitlePK : hrJobTitlePKs) {
				removeHRJobTitle.remove(pk, hrJobTitlePK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRBranchModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME);
		}
	}

	/**
	 * Removes the association between the h r branch and the h r job titles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r branch
	 * @param hrJobTitles the h r job titles
	 * @throws SystemException if a system exception occurred
	 */
	public void removeHRJobTitles(long pk,
		List<com.liferay.hr.model.HRJobTitle> hrJobTitles)
		throws SystemException {
		try {
			for (com.liferay.hr.model.HRJobTitle hrJobTitle : hrJobTitles) {
				removeHRJobTitle.remove(pk, hrJobTitle.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRBranchModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME);
		}
	}

	/**
	 * Sets the h r job titles associated with the h r branch, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r branch
	 * @param hrJobTitlePKs the primary keys of the h r job titles to be associated with the h r branch
	 * @throws SystemException if a system exception occurred
	 */
	public void setHRJobTitles(long pk, long[] hrJobTitlePKs)
		throws SystemException {
		try {
			Set<Long> hrJobTitlePKSet = SetUtil.fromArray(hrJobTitlePKs);

			List<com.liferay.hr.model.HRJobTitle> hrJobTitles = getHRJobTitles(pk);

			for (com.liferay.hr.model.HRJobTitle hrJobTitle : hrJobTitles) {
				if (!hrJobTitlePKSet.remove(hrJobTitle.getPrimaryKey())) {
					removeHRJobTitle.remove(pk, hrJobTitle.getPrimaryKey());
				}
			}

			for (Long hrJobTitlePK : hrJobTitlePKSet) {
				addHRJobTitle.add(pk, hrJobTitlePK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRBranchModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME);
		}
	}

	/**
	 * Sets the h r job titles associated with the h r branch, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r branch
	 * @param hrJobTitles the h r job titles to be associated with the h r branch
	 * @throws SystemException if a system exception occurred
	 */
	public void setHRJobTitles(long pk,
		List<com.liferay.hr.model.HRJobTitle> hrJobTitles)
		throws SystemException {
		try {
			long[] hrJobTitlePKs = new long[hrJobTitles.size()];

			for (int i = 0; i < hrJobTitles.size(); i++) {
				com.liferay.hr.model.HRJobTitle hrJobTitle = hrJobTitles.get(i);

				hrJobTitlePKs[i] = hrJobTitle.getPrimaryKey();
			}

			setHRJobTitles(pk, hrJobTitlePKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRBranchModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME);
		}
	}

	/**
	 * Initializes the h r branch persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRBranch")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRBranch>> listenersList = new ArrayList<ModelListener<HRBranch>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRBranch>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsHRJobTitle = new ContainsHRJobTitle(this);

		addHRJobTitle = new AddHRJobTitle(this);
		clearHRJobTitles = new ClearHRJobTitles(this);
		removeHRJobTitle = new RemoveHRJobTitle(this);
	}

	public void destroy() {
		EntityCacheUtil.removeCache(HRBranchImpl.class.getName());
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
	protected ContainsHRJobTitle containsHRJobTitle;
	protected AddHRJobTitle addHRJobTitle;
	protected ClearHRJobTitles clearHRJobTitles;
	protected RemoveHRJobTitle removeHRJobTitle;

	protected class ContainsHRJobTitle {
		protected ContainsHRJobTitle(HRBranchPersistenceImpl persistenceImpl) {
			super();

			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSHRJOBTITLE,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long hrBranchId, long hrJobTitleId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(hrBranchId), new Long(hrJobTitleId)
					});

			if (results.size() > 0) {
				Integer count = results.get(0);

				if (count.intValue() > 0) {
					return true;
				}
			}

			return false;
		}

		private MappingSqlQuery<Integer> _mappingSqlQuery;
	}

	protected class AddHRJobTitle {
		protected AddHRJobTitle(HRBranchPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO HRBranches_HRJobTitles (hrBranchId, hrJobTitleId) VALUES (?, ?)",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
			_persistenceImpl = persistenceImpl;
		}

		protected void add(long hrBranchId, long hrJobTitleId)
			throws SystemException {
			if (!_persistenceImpl.containsHRJobTitle.contains(hrBranchId,
						hrJobTitleId)) {
				ModelListener<com.liferay.hr.model.HRJobTitle>[] hrJobTitleListeners =
					hrJobTitlePersistence.getListeners();

				for (ModelListener<HRBranch> listener : listeners) {
					listener.onBeforeAddAssociation(hrBranchId,
						com.liferay.hr.model.HRJobTitle.class.getName(),
						hrJobTitleId);
				}

				for (ModelListener<com.liferay.hr.model.HRJobTitle> listener : hrJobTitleListeners) {
					listener.onBeforeAddAssociation(hrJobTitleId,
						HRBranch.class.getName(), hrBranchId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(hrBranchId), new Long(hrJobTitleId)
					});

				for (ModelListener<HRBranch> listener : listeners) {
					listener.onAfterAddAssociation(hrBranchId,
						com.liferay.hr.model.HRJobTitle.class.getName(),
						hrJobTitleId);
				}

				for (ModelListener<com.liferay.hr.model.HRJobTitle> listener : hrJobTitleListeners) {
					listener.onAfterAddAssociation(hrJobTitleId,
						HRBranch.class.getName(), hrBranchId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
		private HRBranchPersistenceImpl _persistenceImpl;
	}

	protected class ClearHRJobTitles {
		protected ClearHRJobTitles(HRBranchPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM HRBranches_HRJobTitles WHERE hrBranchId = ?",
					new int[] { java.sql.Types.BIGINT });
		}

		protected void clear(long hrBranchId) throws SystemException {
			ModelListener<com.liferay.hr.model.HRJobTitle>[] hrJobTitleListeners =
				hrJobTitlePersistence.getListeners();

			List<com.liferay.hr.model.HRJobTitle> hrJobTitles = null;

			if ((listeners.length > 0) || (hrJobTitleListeners.length > 0)) {
				hrJobTitles = getHRJobTitles(hrBranchId);

				for (com.liferay.hr.model.HRJobTitle hrJobTitle : hrJobTitles) {
					for (ModelListener<HRBranch> listener : listeners) {
						listener.onBeforeRemoveAssociation(hrBranchId,
							com.liferay.hr.model.HRJobTitle.class.getName(),
							hrJobTitle.getPrimaryKey());
					}

					for (ModelListener<com.liferay.hr.model.HRJobTitle> listener : hrJobTitleListeners) {
						listener.onBeforeRemoveAssociation(hrJobTitle.getPrimaryKey(),
							HRBranch.class.getName(), hrBranchId);
					}
				}
			}

			_sqlUpdate.update(new Object[] { new Long(hrBranchId) });

			if ((listeners.length > 0) || (hrJobTitleListeners.length > 0)) {
				for (com.liferay.hr.model.HRJobTitle hrJobTitle : hrJobTitles) {
					for (ModelListener<HRBranch> listener : listeners) {
						listener.onAfterRemoveAssociation(hrBranchId,
							com.liferay.hr.model.HRJobTitle.class.getName(),
							hrJobTitle.getPrimaryKey());
					}

					for (ModelListener<com.liferay.hr.model.HRJobTitle> listener : hrJobTitleListeners) {
						listener.onAfterRemoveAssociation(hrJobTitle.getPrimaryKey(),
							HRBranch.class.getName(), hrBranchId);
					}
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemoveHRJobTitle {
		protected RemoveHRJobTitle(HRBranchPersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM HRBranches_HRJobTitles WHERE hrBranchId = ? AND hrJobTitleId = ?",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
			_persistenceImpl = persistenceImpl;
		}

		protected void remove(long hrBranchId, long hrJobTitleId)
			throws SystemException {
			if (_persistenceImpl.containsHRJobTitle.contains(hrBranchId,
						hrJobTitleId)) {
				ModelListener<com.liferay.hr.model.HRJobTitle>[] hrJobTitleListeners =
					hrJobTitlePersistence.getListeners();

				for (ModelListener<HRBranch> listener : listeners) {
					listener.onBeforeRemoveAssociation(hrBranchId,
						com.liferay.hr.model.HRJobTitle.class.getName(),
						hrJobTitleId);
				}

				for (ModelListener<com.liferay.hr.model.HRJobTitle> listener : hrJobTitleListeners) {
					listener.onBeforeRemoveAssociation(hrJobTitleId,
						HRBranch.class.getName(), hrBranchId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(hrBranchId), new Long(hrJobTitleId)
					});

				for (ModelListener<HRBranch> listener : listeners) {
					listener.onAfterRemoveAssociation(hrBranchId,
						com.liferay.hr.model.HRJobTitle.class.getName(),
						hrJobTitleId);
				}

				for (ModelListener<com.liferay.hr.model.HRJobTitle> listener : hrJobTitleListeners) {
					listener.onAfterRemoveAssociation(hrJobTitleId,
						HRBranch.class.getName(), hrBranchId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
		private HRBranchPersistenceImpl _persistenceImpl;
	}

	private static final String _SQL_SELECT_HRBRANCH = "SELECT hrBranch FROM HRBranch hrBranch";
	private static final String _SQL_COUNT_HRBRANCH = "SELECT COUNT(hrBranch) FROM HRBranch hrBranch";
	private static final String _SQL_GETHRJOBTITLES = "SELECT {HRJobTitle.*} FROM HRJobTitle INNER JOIN HRBranches_HRJobTitles ON (HRBranches_HRJobTitles.hrJobTitleId = HRJobTitle.hrJobTitleId) WHERE (HRBranches_HRJobTitles.hrBranchId = ?)";
	private static final String _SQL_GETHRJOBTITLESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM HRBranches_HRJobTitles WHERE hrBranchId = ?";
	private static final String _SQL_CONTAINSHRJOBTITLE = "SELECT COUNT(*) AS COUNT_VALUE FROM HRBranches_HRJobTitles WHERE hrBranchId = ? AND hrJobTitleId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrBranch.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRBranch exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRBranchPersistenceImpl.class);
	private static HRBranch _nullHRBranch = new HRBranchImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRBranch> toCacheModel() {
				return _nullHRBranchCacheModel;
			}
		};

	private static CacheModel<HRBranch> _nullHRBranchCacheModel = new CacheModel<HRBranch>() {
			public HRBranch toEntityModel() {
				return _nullHRBranch;
			}
		};
}