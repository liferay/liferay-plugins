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

import com.liferay.hr.NoSuchJobTitleException;
import com.liferay.hr.model.HRJobTitle;
import com.liferay.hr.model.impl.HRJobTitleImpl;
import com.liferay.hr.model.impl.HRJobTitleModelImpl;

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
 * The persistence implementation for the h r job title service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRJobTitlePersistence
 * @see HRJobTitleUtil
 * @generated
 */
public class HRJobTitlePersistenceImpl extends BasePersistenceImpl<HRJobTitle>
	implements HRJobTitlePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HRJobTitleUtil} to access the h r job title persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HRJobTitleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(HRJobTitleModelImpl.ENTITY_CACHE_ENABLED,
			HRJobTitleModelImpl.FINDER_CACHE_ENABLED, HRJobTitleImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HRJobTitleModelImpl.ENTITY_CACHE_ENABLED,
			HRJobTitleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the h r job title in the entity cache if it is enabled.
	 *
	 * @param hrJobTitle the h r job title
	 */
	public void cacheResult(HRJobTitle hrJobTitle) {
		EntityCacheUtil.putResult(HRJobTitleModelImpl.ENTITY_CACHE_ENABLED,
			HRJobTitleImpl.class, hrJobTitle.getPrimaryKey(), hrJobTitle);

		hrJobTitle.resetOriginalValues();
	}

	/**
	 * Caches the h r job titles in the entity cache if it is enabled.
	 *
	 * @param hrJobTitles the h r job titles
	 */
	public void cacheResult(List<HRJobTitle> hrJobTitles) {
		for (HRJobTitle hrJobTitle : hrJobTitles) {
			if (EntityCacheUtil.getResult(
						HRJobTitleModelImpl.ENTITY_CACHE_ENABLED,
						HRJobTitleImpl.class, hrJobTitle.getPrimaryKey(), this) == null) {
				cacheResult(hrJobTitle);
			}
		}
	}

	/**
	 * Clears the cache for all h r job titles.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(HRJobTitleImpl.class.getName());
		}

		EntityCacheUtil.clearCache(HRJobTitleImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the h r job title.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HRJobTitle hrJobTitle) {
		EntityCacheUtil.removeResult(HRJobTitleModelImpl.ENTITY_CACHE_ENABLED,
			HRJobTitleImpl.class, hrJobTitle.getPrimaryKey());
	}

	/**
	 * Creates a new h r job title with the primary key. Does not add the h r job title to the database.
	 *
	 * @param hrJobTitleId the primary key for the new h r job title
	 * @return the new h r job title
	 */
	public HRJobTitle create(long hrJobTitleId) {
		HRJobTitle hrJobTitle = new HRJobTitleImpl();

		hrJobTitle.setNew(true);
		hrJobTitle.setPrimaryKey(hrJobTitleId);

		return hrJobTitle;
	}

	/**
	 * Removes the h r job title with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the h r job title
	 * @return the h r job title that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a h r job title with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRJobTitle remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the h r job title with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrJobTitleId the primary key of the h r job title
	 * @return the h r job title that was removed
	 * @throws com.liferay.hr.NoSuchJobTitleException if a h r job title with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRJobTitle remove(long hrJobTitleId)
		throws NoSuchJobTitleException, SystemException {
		Session session = null;

		try {
			session = openSession();

			HRJobTitle hrJobTitle = (HRJobTitle)session.get(HRJobTitleImpl.class,
					Long.valueOf(hrJobTitleId));

			if (hrJobTitle == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrJobTitleId);
				}

				throw new NoSuchJobTitleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					hrJobTitleId);
			}

			return hrJobTitlePersistence.remove(hrJobTitle);
		}
		catch (NoSuchJobTitleException nsee) {
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
	 * Removes the h r job title from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrJobTitle the h r job title
	 * @return the h r job title that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRJobTitle remove(HRJobTitle hrJobTitle) throws SystemException {
		return super.remove(hrJobTitle);
	}

	@Override
	protected HRJobTitle removeImpl(HRJobTitle hrJobTitle)
		throws SystemException {
		hrJobTitle = toUnwrappedModel(hrJobTitle);

		try {
			clearHRBranchs.clear(hrJobTitle.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRJobTitleModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, hrJobTitle);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(HRJobTitleModelImpl.ENTITY_CACHE_ENABLED,
			HRJobTitleImpl.class, hrJobTitle.getPrimaryKey());

		return hrJobTitle;
	}

	@Override
	public HRJobTitle updateImpl(com.liferay.hr.model.HRJobTitle hrJobTitle,
		boolean merge) throws SystemException {
		hrJobTitle = toUnwrappedModel(hrJobTitle);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, hrJobTitle, merge);

			hrJobTitle.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(HRJobTitleModelImpl.ENTITY_CACHE_ENABLED,
			HRJobTitleImpl.class, hrJobTitle.getPrimaryKey(), hrJobTitle);

		return hrJobTitle;
	}

	protected HRJobTitle toUnwrappedModel(HRJobTitle hrJobTitle) {
		if (hrJobTitle instanceof HRJobTitleImpl) {
			return hrJobTitle;
		}

		HRJobTitleImpl hrJobTitleImpl = new HRJobTitleImpl();

		hrJobTitleImpl.setNew(hrJobTitle.isNew());
		hrJobTitleImpl.setPrimaryKey(hrJobTitle.getPrimaryKey());

		hrJobTitleImpl.setHrJobTitleId(hrJobTitle.getHrJobTitleId());
		hrJobTitleImpl.setGroupId(hrJobTitle.getGroupId());
		hrJobTitleImpl.setCompanyId(hrJobTitle.getCompanyId());
		hrJobTitleImpl.setUserId(hrJobTitle.getUserId());
		hrJobTitleImpl.setUserName(hrJobTitle.getUserName());
		hrJobTitleImpl.setCreateDate(hrJobTitle.getCreateDate());
		hrJobTitleImpl.setModifiedDate(hrJobTitle.getModifiedDate());
		hrJobTitleImpl.setName(hrJobTitle.getName());
		hrJobTitleImpl.setDescription(hrJobTitle.getDescription());

		return hrJobTitleImpl;
	}

	/**
	 * Returns the h r job title with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r job title
	 * @return the h r job title
	 * @throws com.liferay.portal.NoSuchModelException if a h r job title with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRJobTitle findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r job title with the primary key or throws a {@link com.liferay.hr.NoSuchJobTitleException} if it could not be found.
	 *
	 * @param hrJobTitleId the primary key of the h r job title
	 * @return the h r job title
	 * @throws com.liferay.hr.NoSuchJobTitleException if a h r job title with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRJobTitle findByPrimaryKey(long hrJobTitleId)
		throws NoSuchJobTitleException, SystemException {
		HRJobTitle hrJobTitle = fetchByPrimaryKey(hrJobTitleId);

		if (hrJobTitle == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + hrJobTitleId);
			}

			throw new NoSuchJobTitleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				hrJobTitleId);
		}

		return hrJobTitle;
	}

	/**
	 * Returns the h r job title with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the h r job title
	 * @return the h r job title, or <code>null</code> if a h r job title with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public HRJobTitle fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the h r job title with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hrJobTitleId the primary key of the h r job title
	 * @return the h r job title, or <code>null</code> if a h r job title with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRJobTitle fetchByPrimaryKey(long hrJobTitleId)
		throws SystemException {
		HRJobTitle hrJobTitle = (HRJobTitle)EntityCacheUtil.getResult(HRJobTitleModelImpl.ENTITY_CACHE_ENABLED,
				HRJobTitleImpl.class, hrJobTitleId, this);

		if (hrJobTitle == _nullHRJobTitle) {
			return null;
		}

		if (hrJobTitle == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				hrJobTitle = (HRJobTitle)session.get(HRJobTitleImpl.class,
						Long.valueOf(hrJobTitleId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (hrJobTitle != null) {
					cacheResult(hrJobTitle);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(HRJobTitleModelImpl.ENTITY_CACHE_ENABLED,
						HRJobTitleImpl.class, hrJobTitleId, _nullHRJobTitle);
				}

				closeSession(session);
			}
		}

		return hrJobTitle;
	}

	/**
	 * Returns all the h r job titles.
	 *
	 * @return the h r job titles
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRJobTitle> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the h r job titles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r job titles
	 * @param end the upper bound of the range of h r job titles (not inclusive)
	 * @return the range of h r job titles
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRJobTitle> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the h r job titles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r job titles
	 * @param end the upper bound of the range of h r job titles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r job titles
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRJobTitle> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<HRJobTitle> list = (List<HRJobTitle>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HRJOBTITLE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HRJOBTITLE;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<HRJobTitle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<HRJobTitle>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the h r job titles from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HRJobTitle hrJobTitle : findAll()) {
			hrJobTitlePersistence.remove(hrJobTitle);
		}
	}

	/**
	 * Returns the number of h r job titles.
	 *
	 * @return the number of h r job titles
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

				Query q = session.createQuery(_SQL_COUNT_HRJOBTITLE);

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
	 * Returns all the h r branchs associated with the h r job title.
	 *
	 * @param pk the primary key of the h r job title
	 * @return the h r branchs associated with the h r job title
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.hr.model.HRBranch> getHRBranchs(long pk)
		throws SystemException {
		return getHRBranchs(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the h r branchs associated with the h r job title.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the h r job title
	 * @param start the lower bound of the range of h r job titles
	 * @param end the upper bound of the range of h r job titles (not inclusive)
	 * @return the range of h r branchs associated with the h r job title
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.hr.model.HRBranch> getHRBranchs(long pk, int start,
		int end) throws SystemException {
		return getHRBranchs(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_HRBRANCHS = new FinderPath(com.liferay.hr.model.impl.HRBranchModelImpl.ENTITY_CACHE_ENABLED,
			HRJobTitleModelImpl.FINDER_CACHE_ENABLED_HRBRANCHES_HRJOBTITLES,
			com.liferay.hr.model.impl.HRBranchImpl.class,
			HRJobTitleModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME,
			"getHRBranchs",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	/**
	 * Returns an ordered range of all the h r branchs associated with the h r job title.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the h r job title
	 * @param start the lower bound of the range of h r job titles
	 * @param end the upper bound of the range of h r job titles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of h r branchs associated with the h r job title
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.hr.model.HRBranch> getHRBranchs(long pk, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				pk, String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<com.liferay.hr.model.HRBranch> list = (List<com.liferay.hr.model.HRBranch>)FinderCacheUtil.getResult(FINDER_PATH_GET_HRBRANCHS,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETHRBRANCHS.concat(ORDER_BY_CLAUSE)
										   .concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETHRBRANCHS;
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("HRBranch",
					com.liferay.hr.model.impl.HRBranchImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.liferay.hr.model.HRBranch>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_HRBRANCHS,
						finderArgs);
				}
				else {
					hrBranchPersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_HRBRANCHS,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_HRBRANCHS_SIZE = new FinderPath(com.liferay.hr.model.impl.HRBranchModelImpl.ENTITY_CACHE_ENABLED,
			HRJobTitleModelImpl.FINDER_CACHE_ENABLED_HRBRANCHES_HRJOBTITLES,
			Long.class,
			HRJobTitleModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME,
			"getHRBranchsSize", new String[] { Long.class.getName() });

	/**
	 * Returns the number of h r branchs associated with the h r job title.
	 *
	 * @param pk the primary key of the h r job title
	 * @return the number of h r branchs associated with the h r job title
	 * @throws SystemException if a system exception occurred
	 */
	public int getHRBranchsSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_HRBRANCHS_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETHRBRANCHSSIZE);

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

				FinderCacheUtil.putResult(FINDER_PATH_GET_HRBRANCHS_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_HRBRANCH = new FinderPath(com.liferay.hr.model.impl.HRBranchModelImpl.ENTITY_CACHE_ENABLED,
			HRJobTitleModelImpl.FINDER_CACHE_ENABLED_HRBRANCHES_HRJOBTITLES,
			Boolean.class,
			HRJobTitleModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME,
			"containsHRBranch",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the h r branch is associated with the h r job title.
	 *
	 * @param pk the primary key of the h r job title
	 * @param hrBranchPK the primary key of the h r branch
	 * @return <code>true</code> if the h r branch is associated with the h r job title; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsHRBranch(long pk, long hrBranchPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, hrBranchPK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_HRBRANCH,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsHRBranch.contains(pk, hrBranchPK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_HRBRANCH,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Returns <code>true</code> if the h r job title has any h r branchs associated with it.
	 *
	 * @param pk the primary key of the h r job title to check for associations with h r branchs
	 * @return <code>true</code> if the h r job title has any h r branchs associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsHRBranchs(long pk) throws SystemException {
		if (getHRBranchsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the h r job title and the h r branch. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r job title
	 * @param hrBranchPK the primary key of the h r branch
	 * @throws SystemException if a system exception occurred
	 */
	public void addHRBranch(long pk, long hrBranchPK) throws SystemException {
		try {
			addHRBranch.add(pk, hrBranchPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRJobTitleModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME);
		}
	}

	/**
	 * Adds an association between the h r job title and the h r branch. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r job title
	 * @param hrBranch the h r branch
	 * @throws SystemException if a system exception occurred
	 */
	public void addHRBranch(long pk, com.liferay.hr.model.HRBranch hrBranch)
		throws SystemException {
		try {
			addHRBranch.add(pk, hrBranch.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRJobTitleModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME);
		}
	}

	/**
	 * Adds an association between the h r job title and the h r branchs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r job title
	 * @param hrBranchPKs the primary keys of the h r branchs
	 * @throws SystemException if a system exception occurred
	 */
	public void addHRBranchs(long pk, long[] hrBranchPKs)
		throws SystemException {
		try {
			for (long hrBranchPK : hrBranchPKs) {
				addHRBranch.add(pk, hrBranchPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRJobTitleModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME);
		}
	}

	/**
	 * Adds an association between the h r job title and the h r branchs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r job title
	 * @param hrBranchs the h r branchs
	 * @throws SystemException if a system exception occurred
	 */
	public void addHRBranchs(long pk,
		List<com.liferay.hr.model.HRBranch> hrBranchs)
		throws SystemException {
		try {
			for (com.liferay.hr.model.HRBranch hrBranch : hrBranchs) {
				addHRBranch.add(pk, hrBranch.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRJobTitleModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME);
		}
	}

	/**
	 * Clears all associations between the h r job title and its h r branchs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r job title to clear the associated h r branchs from
	 * @throws SystemException if a system exception occurred
	 */
	public void clearHRBranchs(long pk) throws SystemException {
		try {
			clearHRBranchs.clear(pk);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRJobTitleModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME);
		}
	}

	/**
	 * Removes the association between the h r job title and the h r branch. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r job title
	 * @param hrBranchPK the primary key of the h r branch
	 * @throws SystemException if a system exception occurred
	 */
	public void removeHRBranch(long pk, long hrBranchPK)
		throws SystemException {
		try {
			removeHRBranch.remove(pk, hrBranchPK);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRJobTitleModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME);
		}
	}

	/**
	 * Removes the association between the h r job title and the h r branch. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r job title
	 * @param hrBranch the h r branch
	 * @throws SystemException if a system exception occurred
	 */
	public void removeHRBranch(long pk, com.liferay.hr.model.HRBranch hrBranch)
		throws SystemException {
		try {
			removeHRBranch.remove(pk, hrBranch.getPrimaryKey());
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRJobTitleModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME);
		}
	}

	/**
	 * Removes the association between the h r job title and the h r branchs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r job title
	 * @param hrBranchPKs the primary keys of the h r branchs
	 * @throws SystemException if a system exception occurred
	 */
	public void removeHRBranchs(long pk, long[] hrBranchPKs)
		throws SystemException {
		try {
			for (long hrBranchPK : hrBranchPKs) {
				removeHRBranch.remove(pk, hrBranchPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRJobTitleModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME);
		}
	}

	/**
	 * Removes the association between the h r job title and the h r branchs. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r job title
	 * @param hrBranchs the h r branchs
	 * @throws SystemException if a system exception occurred
	 */
	public void removeHRBranchs(long pk,
		List<com.liferay.hr.model.HRBranch> hrBranchs)
		throws SystemException {
		try {
			for (com.liferay.hr.model.HRBranch hrBranch : hrBranchs) {
				removeHRBranch.remove(pk, hrBranch.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRJobTitleModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME);
		}
	}

	/**
	 * Sets the h r branchs associated with the h r job title, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r job title
	 * @param hrBranchPKs the primary keys of the h r branchs to be associated with the h r job title
	 * @throws SystemException if a system exception occurred
	 */
	public void setHRBranchs(long pk, long[] hrBranchPKs)
		throws SystemException {
		try {
			Set<Long> hrBranchPKSet = SetUtil.fromArray(hrBranchPKs);

			List<com.liferay.hr.model.HRBranch> hrBranchs = getHRBranchs(pk);

			for (com.liferay.hr.model.HRBranch hrBranch : hrBranchs) {
				if (!hrBranchPKSet.remove(hrBranch.getPrimaryKey())) {
					removeHRBranch.remove(pk, hrBranch.getPrimaryKey());
				}
			}

			for (Long hrBranchPK : hrBranchPKSet) {
				addHRBranch.add(pk, hrBranchPK);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRJobTitleModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME);
		}
	}

	/**
	 * Sets the h r branchs associated with the h r job title, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the h r job title
	 * @param hrBranchs the h r branchs to be associated with the h r job title
	 * @throws SystemException if a system exception occurred
	 */
	public void setHRBranchs(long pk,
		List<com.liferay.hr.model.HRBranch> hrBranchs)
		throws SystemException {
		try {
			long[] hrBranchPKs = new long[hrBranchs.size()];

			for (int i = 0; i < hrBranchs.size(); i++) {
				com.liferay.hr.model.HRBranch hrBranch = hrBranchs.get(i);

				hrBranchPKs[i] = hrBranch.getPrimaryKey();
			}

			setHRBranchs(pk, hrBranchPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			FinderCacheUtil.clearCache(HRJobTitleModelImpl.MAPPING_TABLE_HRBRANCHES_HRJOBTITLES_NAME);
		}
	}

	/**
	 * Initializes the h r job title persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.hr.model.HRJobTitle")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<HRJobTitle>> listenersList = new ArrayList<ModelListener<HRJobTitle>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<HRJobTitle>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsHRBranch = new ContainsHRBranch(this);

		addHRBranch = new AddHRBranch(this);
		clearHRBranchs = new ClearHRBranchs(this);
		removeHRBranch = new RemoveHRBranch(this);
	}

	public void destroy() {
		EntityCacheUtil.removeCache(HRJobTitleImpl.class.getName());
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
	protected ContainsHRBranch containsHRBranch;
	protected AddHRBranch addHRBranch;
	protected ClearHRBranchs clearHRBranchs;
	protected RemoveHRBranch removeHRBranch;

	protected class ContainsHRBranch {
		protected ContainsHRBranch(HRJobTitlePersistenceImpl persistenceImpl) {
			super();

			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSHRBRANCH,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long hrJobTitleId, long hrBranchId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(hrJobTitleId), new Long(hrBranchId)
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

	protected class AddHRBranch {
		protected AddHRBranch(HRJobTitlePersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"INSERT INTO HRBranches_HRJobTitles (hrJobTitleId, hrBranchId) VALUES (?, ?)",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
			_persistenceImpl = persistenceImpl;
		}

		protected void add(long hrJobTitleId, long hrBranchId)
			throws SystemException {
			if (!_persistenceImpl.containsHRBranch.contains(hrJobTitleId,
						hrBranchId)) {
				ModelListener<com.liferay.hr.model.HRBranch>[] hrBranchListeners =
					hrBranchPersistence.getListeners();

				for (ModelListener<HRJobTitle> listener : listeners) {
					listener.onBeforeAddAssociation(hrJobTitleId,
						com.liferay.hr.model.HRBranch.class.getName(),
						hrBranchId);
				}

				for (ModelListener<com.liferay.hr.model.HRBranch> listener : hrBranchListeners) {
					listener.onBeforeAddAssociation(hrBranchId,
						HRJobTitle.class.getName(), hrJobTitleId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(hrJobTitleId), new Long(hrBranchId)
					});

				for (ModelListener<HRJobTitle> listener : listeners) {
					listener.onAfterAddAssociation(hrJobTitleId,
						com.liferay.hr.model.HRBranch.class.getName(),
						hrBranchId);
				}

				for (ModelListener<com.liferay.hr.model.HRBranch> listener : hrBranchListeners) {
					listener.onAfterAddAssociation(hrBranchId,
						HRJobTitle.class.getName(), hrJobTitleId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
		private HRJobTitlePersistenceImpl _persistenceImpl;
	}

	protected class ClearHRBranchs {
		protected ClearHRBranchs(HRJobTitlePersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM HRBranches_HRJobTitles WHERE hrJobTitleId = ?",
					new int[] { java.sql.Types.BIGINT });
		}

		protected void clear(long hrJobTitleId) throws SystemException {
			ModelListener<com.liferay.hr.model.HRBranch>[] hrBranchListeners = hrBranchPersistence.getListeners();

			List<com.liferay.hr.model.HRBranch> hrBranchs = null;

			if ((listeners.length > 0) || (hrBranchListeners.length > 0)) {
				hrBranchs = getHRBranchs(hrJobTitleId);

				for (com.liferay.hr.model.HRBranch hrBranch : hrBranchs) {
					for (ModelListener<HRJobTitle> listener : listeners) {
						listener.onBeforeRemoveAssociation(hrJobTitleId,
							com.liferay.hr.model.HRBranch.class.getName(),
							hrBranch.getPrimaryKey());
					}

					for (ModelListener<com.liferay.hr.model.HRBranch> listener : hrBranchListeners) {
						listener.onBeforeRemoveAssociation(hrBranch.getPrimaryKey(),
							HRJobTitle.class.getName(), hrJobTitleId);
					}
				}
			}

			_sqlUpdate.update(new Object[] { new Long(hrJobTitleId) });

			if ((listeners.length > 0) || (hrBranchListeners.length > 0)) {
				for (com.liferay.hr.model.HRBranch hrBranch : hrBranchs) {
					for (ModelListener<HRJobTitle> listener : listeners) {
						listener.onAfterRemoveAssociation(hrJobTitleId,
							com.liferay.hr.model.HRBranch.class.getName(),
							hrBranch.getPrimaryKey());
					}

					for (ModelListener<com.liferay.hr.model.HRBranch> listener : hrBranchListeners) {
						listener.onAfterRemoveAssociation(hrBranch.getPrimaryKey(),
							HRJobTitle.class.getName(), hrJobTitleId);
					}
				}
			}
		}

		private SqlUpdate _sqlUpdate;
	}

	protected class RemoveHRBranch {
		protected RemoveHRBranch(HRJobTitlePersistenceImpl persistenceImpl) {
			_sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(getDataSource(),
					"DELETE FROM HRBranches_HRJobTitles WHERE hrJobTitleId = ? AND hrBranchId = ?",
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT });
			_persistenceImpl = persistenceImpl;
		}

		protected void remove(long hrJobTitleId, long hrBranchId)
			throws SystemException {
			if (_persistenceImpl.containsHRBranch.contains(hrJobTitleId,
						hrBranchId)) {
				ModelListener<com.liferay.hr.model.HRBranch>[] hrBranchListeners =
					hrBranchPersistence.getListeners();

				for (ModelListener<HRJobTitle> listener : listeners) {
					listener.onBeforeRemoveAssociation(hrJobTitleId,
						com.liferay.hr.model.HRBranch.class.getName(),
						hrBranchId);
				}

				for (ModelListener<com.liferay.hr.model.HRBranch> listener : hrBranchListeners) {
					listener.onBeforeRemoveAssociation(hrBranchId,
						HRJobTitle.class.getName(), hrJobTitleId);
				}

				_sqlUpdate.update(new Object[] {
						new Long(hrJobTitleId), new Long(hrBranchId)
					});

				for (ModelListener<HRJobTitle> listener : listeners) {
					listener.onAfterRemoveAssociation(hrJobTitleId,
						com.liferay.hr.model.HRBranch.class.getName(),
						hrBranchId);
				}

				for (ModelListener<com.liferay.hr.model.HRBranch> listener : hrBranchListeners) {
					listener.onAfterRemoveAssociation(hrBranchId,
						HRJobTitle.class.getName(), hrJobTitleId);
				}
			}
		}

		private SqlUpdate _sqlUpdate;
		private HRJobTitlePersistenceImpl _persistenceImpl;
	}

	private static final String _SQL_SELECT_HRJOBTITLE = "SELECT hrJobTitle FROM HRJobTitle hrJobTitle";
	private static final String _SQL_COUNT_HRJOBTITLE = "SELECT COUNT(hrJobTitle) FROM HRJobTitle hrJobTitle";
	private static final String _SQL_GETHRBRANCHS = "SELECT {HRBranch.*} FROM HRBranch INNER JOIN HRBranches_HRJobTitles ON (HRBranches_HRJobTitles.hrBranchId = HRBranch.hrBranchId) WHERE (HRBranches_HRJobTitles.hrJobTitleId = ?)";
	private static final String _SQL_GETHRBRANCHSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM HRBranches_HRJobTitles WHERE hrJobTitleId = ?";
	private static final String _SQL_CONTAINSHRBRANCH = "SELECT COUNT(*) AS COUNT_VALUE FROM HRBranches_HRJobTitles WHERE hrJobTitleId = ? AND hrBranchId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hrJobTitle.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HRJobTitle exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(HRJobTitlePersistenceImpl.class);
	private static HRJobTitle _nullHRJobTitle = new HRJobTitleImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<HRJobTitle> toCacheModel() {
				return _nullHRJobTitleCacheModel;
			}
		};

	private static CacheModel<HRJobTitle> _nullHRJobTitleCacheModel = new CacheModel<HRJobTitle>() {
			public HRJobTitle toEntityModel() {
				return _nullHRJobTitle;
			}
		};
}