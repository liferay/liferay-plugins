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

package com.liferay.portal.workflow.kaleo.service.persistence;

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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.NoSuchTaskException;
import com.liferay.portal.workflow.kaleo.model.KaleoTask;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the kaleo task service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskPersistence
 * @see KaleoTaskUtil
 * @generated
 */
public class KaleoTaskPersistenceImpl extends BasePersistenceImpl<KaleoTask>
	implements KaleoTaskPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KaleoTaskUtil} to access the kaleo task persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoTaskImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskModelImpl.FINDER_CACHE_ENABLED, KaleoTaskImpl.class,
			FINDER_CLASS_NAME_LIST, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KALEODEFINITIONID = new FinderPath(KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskModelImpl.FINDER_CACHE_ENABLED, KaleoTaskImpl.class,
			FINDER_CLASS_NAME_LIST, "findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEODEFINITIONID = new FinderPath(KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByKaleoDefinitionId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_KALEONODEID = new FinderPath(KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskModelImpl.FINDER_CACHE_ENABLED, KaleoTaskImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByKaleoNodeId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEONODEID = new FinderPath(KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByKaleoNodeId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskModelImpl.FINDER_CACHE_ENABLED, KaleoTaskImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the kaleo task in the entity cache if it is enabled.
	 *
	 * @param kaleoTask the kaleo task
	 */
	public void cacheResult(KaleoTask kaleoTask) {
		EntityCacheUtil.putResult(KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskImpl.class, kaleoTask.getPrimaryKey(), kaleoTask);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KALEONODEID,
			new Object[] { Long.valueOf(kaleoTask.getKaleoNodeId()) }, kaleoTask);

		kaleoTask.resetOriginalValues();
	}

	/**
	 * Caches the kaleo tasks in the entity cache if it is enabled.
	 *
	 * @param kaleoTasks the kaleo tasks
	 */
	public void cacheResult(List<KaleoTask> kaleoTasks) {
		for (KaleoTask kaleoTask : kaleoTasks) {
			if (EntityCacheUtil.getResult(
						KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTaskImpl.class, kaleoTask.getPrimaryKey(), this) == null) {
				cacheResult(kaleoTask);
			}
		}
	}

	/**
	 * Clears the cache for all kaleo tasks.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(KaleoTaskImpl.class.getName());
		}

		EntityCacheUtil.clearCache(KaleoTaskImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the kaleo task.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KaleoTask kaleoTask) {
		EntityCacheUtil.removeResult(KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskImpl.class, kaleoTask.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KALEONODEID,
			new Object[] { Long.valueOf(kaleoTask.getKaleoNodeId()) });
	}

	/**
	 * Creates a new kaleo task with the primary key. Does not add the kaleo task to the database.
	 *
	 * @param kaleoTaskId the primary key for the new kaleo task
	 * @return the new kaleo task
	 */
	public KaleoTask create(long kaleoTaskId) {
		KaleoTask kaleoTask = new KaleoTaskImpl();

		kaleoTask.setNew(true);
		kaleoTask.setPrimaryKey(kaleoTaskId);

		return kaleoTask;
	}

	/**
	 * Removes the kaleo task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo task
	 * @return the kaleo task that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTask remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the kaleo task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTaskId the primary key of the kaleo task
	 * @return the kaleo task that was removed
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskException if a kaleo task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTask remove(long kaleoTaskId)
		throws NoSuchTaskException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoTask kaleoTask = (KaleoTask)session.get(KaleoTaskImpl.class,
					Long.valueOf(kaleoTaskId));

			if (kaleoTask == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + kaleoTaskId);
				}

				throw new NoSuchTaskException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoTaskId);
			}

			return kaleoTaskPersistence.remove(kaleoTask);
		}
		catch (NoSuchTaskException nsee) {
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
	 * Removes the kaleo task from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTask the kaleo task
	 * @return the kaleo task that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTask remove(KaleoTask kaleoTask) throws SystemException {
		return super.remove(kaleoTask);
	}

	@Override
	protected KaleoTask removeImpl(KaleoTask kaleoTask)
		throws SystemException {
		kaleoTask = toUnwrappedModel(kaleoTask);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, kaleoTask);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		KaleoTaskModelImpl kaleoTaskModelImpl = (KaleoTaskModelImpl)kaleoTask;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KALEONODEID,
			new Object[] { Long.valueOf(kaleoTaskModelImpl.getKaleoNodeId()) });

		EntityCacheUtil.removeResult(KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskImpl.class, kaleoTask.getPrimaryKey());

		return kaleoTask;
	}

	@Override
	public KaleoTask updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTask kaleoTask,
		boolean merge) throws SystemException {
		kaleoTask = toUnwrappedModel(kaleoTask);

		boolean isNew = kaleoTask.isNew();

		KaleoTaskModelImpl kaleoTaskModelImpl = (KaleoTaskModelImpl)kaleoTask;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kaleoTask, merge);

			kaleoTask.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskImpl.class, kaleoTask.getPrimaryKey(), kaleoTask);

		if (!isNew &&
				(kaleoTask.getKaleoNodeId() != kaleoTaskModelImpl.getOriginalKaleoNodeId())) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KALEONODEID,
				new Object[] {
					Long.valueOf(kaleoTaskModelImpl.getOriginalKaleoNodeId())
				});
		}

		if (isNew ||
				(kaleoTask.getKaleoNodeId() != kaleoTaskModelImpl.getOriginalKaleoNodeId())) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KALEONODEID,
				new Object[] { Long.valueOf(kaleoTask.getKaleoNodeId()) },
				kaleoTask);
		}

		return kaleoTask;
	}

	protected KaleoTask toUnwrappedModel(KaleoTask kaleoTask) {
		if (kaleoTask instanceof KaleoTaskImpl) {
			return kaleoTask;
		}

		KaleoTaskImpl kaleoTaskImpl = new KaleoTaskImpl();

		kaleoTaskImpl.setNew(kaleoTask.isNew());
		kaleoTaskImpl.setPrimaryKey(kaleoTask.getPrimaryKey());

		kaleoTaskImpl.setKaleoTaskId(kaleoTask.getKaleoTaskId());
		kaleoTaskImpl.setGroupId(kaleoTask.getGroupId());
		kaleoTaskImpl.setCompanyId(kaleoTask.getCompanyId());
		kaleoTaskImpl.setUserId(kaleoTask.getUserId());
		kaleoTaskImpl.setUserName(kaleoTask.getUserName());
		kaleoTaskImpl.setCreateDate(kaleoTask.getCreateDate());
		kaleoTaskImpl.setModifiedDate(kaleoTask.getModifiedDate());
		kaleoTaskImpl.setKaleoDefinitionId(kaleoTask.getKaleoDefinitionId());
		kaleoTaskImpl.setKaleoNodeId(kaleoTask.getKaleoNodeId());
		kaleoTaskImpl.setName(kaleoTask.getName());
		kaleoTaskImpl.setDescription(kaleoTask.getDescription());

		return kaleoTaskImpl;
	}

	/**
	 * Returns the kaleo task with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo task
	 * @return the kaleo task
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTask findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the kaleo task with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTaskException} if it could not be found.
	 *
	 * @param kaleoTaskId the primary key of the kaleo task
	 * @return the kaleo task
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskException if a kaleo task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTask findByPrimaryKey(long kaleoTaskId)
		throws NoSuchTaskException, SystemException {
		KaleoTask kaleoTask = fetchByPrimaryKey(kaleoTaskId);

		if (kaleoTask == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + kaleoTaskId);
			}

			throw new NoSuchTaskException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				kaleoTaskId);
		}

		return kaleoTask;
	}

	/**
	 * Returns the kaleo task with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo task
	 * @return the kaleo task, or <code>null</code> if a kaleo task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTask fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the kaleo task with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoTaskId the primary key of the kaleo task
	 * @return the kaleo task, or <code>null</code> if a kaleo task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTask fetchByPrimaryKey(long kaleoTaskId)
		throws SystemException {
		KaleoTask kaleoTask = (KaleoTask)EntityCacheUtil.getResult(KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
				KaleoTaskImpl.class, kaleoTaskId, this);

		if (kaleoTask == _nullKaleoTask) {
			return null;
		}

		if (kaleoTask == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				kaleoTask = (KaleoTask)session.get(KaleoTaskImpl.class,
						Long.valueOf(kaleoTaskId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (kaleoTask != null) {
					cacheResult(kaleoTask);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(KaleoTaskModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTaskImpl.class, kaleoTaskId, _nullKaleoTask);
				}

				closeSession(session);
			}
		}

		return kaleoTask;
	}

	/**
	 * Returns all the kaleo tasks where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching kaleo tasks
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTask> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the kaleo tasks where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo tasks
	 * @param end the upper bound of the range of kaleo tasks (not inclusive)
	 * @return the range of matching kaleo tasks
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTask> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo tasks where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo tasks
	 * @param end the upper bound of the range of kaleo tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo tasks
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTask> findByCompanyId(long companyId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTask> list = (List<KaleoTask>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_KALEOTASK_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoTaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<KaleoTask>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_COMPANYID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first kaleo task in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskException if a matching kaleo task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTask findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		List<KaleoTask> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo task in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskException if a matching kaleo task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTask findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		int count = countByCompanyId(companyId);

		List<KaleoTask> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo tasks before and after the current kaleo task in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskId the primary key of the current kaleo task
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo task
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskException if a kaleo task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTask[] findByCompanyId_PrevAndNext(long kaleoTaskId,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		KaleoTask kaleoTask = findByPrimaryKey(kaleoTaskId);

		Session session = null;

		try {
			session = openSession();

			KaleoTask[] array = new KaleoTaskImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, kaleoTask,
					companyId, orderByComparator, true);

			array[1] = kaleoTask;

			array[2] = getByCompanyId_PrevAndNext(session, kaleoTask,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTask getByCompanyId_PrevAndNext(Session session,
		KaleoTask kaleoTask, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASK_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(KaleoTaskModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoTask);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTask> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo tasks where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the matching kaleo tasks
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTask> findByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo tasks where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo tasks
	 * @param end the upper bound of the range of kaleo tasks (not inclusive)
	 * @return the range of matching kaleo tasks
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTask> findByKaleoDefinitionId(long kaleoDefinitionId,
		int start, int end) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo tasks where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo tasks
	 * @param end the upper bound of the range of kaleo tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo tasks
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTask> findByKaleoDefinitionId(long kaleoDefinitionId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoDefinitionId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTask> list = (List<KaleoTask>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_KALEOTASK_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoTaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				list = (List<KaleoTask>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first kaleo task in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskException if a matching kaleo task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTask findByKaleoDefinitionId_First(long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		List<KaleoTask> list = findByKaleoDefinitionId(kaleoDefinitionId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo task in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskException if a matching kaleo task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTask findByKaleoDefinitionId_Last(long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		List<KaleoTask> list = findByKaleoDefinitionId(kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo tasks before and after the current kaleo task in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskId the primary key of the current kaleo task
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo task
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskException if a kaleo task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTask[] findByKaleoDefinitionId_PrevAndNext(long kaleoTaskId,
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		KaleoTask kaleoTask = findByPrimaryKey(kaleoTaskId);

		Session session = null;

		try {
			session = openSession();

			KaleoTask[] array = new KaleoTaskImpl[3];

			array[0] = getByKaleoDefinitionId_PrevAndNext(session, kaleoTask,
					kaleoDefinitionId, orderByComparator, true);

			array[1] = kaleoTask;

			array[2] = getByKaleoDefinitionId_PrevAndNext(session, kaleoTask,
					kaleoDefinitionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTask getByKaleoDefinitionId_PrevAndNext(Session session,
		KaleoTask kaleoTask, long kaleoDefinitionId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASK_WHERE);

		query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(KaleoTaskModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoTask);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTask> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the kaleo task where kaleoNodeId = &#63; or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTaskException} if it could not be found.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @return the matching kaleo task
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskException if a matching kaleo task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTask findByKaleoNodeId(long kaleoNodeId)
		throws NoSuchTaskException, SystemException {
		KaleoTask kaleoTask = fetchByKaleoNodeId(kaleoNodeId);

		if (kaleoTask == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoNodeId=");
			msg.append(kaleoNodeId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTaskException(msg.toString());
		}

		return kaleoTask;
	}

	/**
	 * Returns the kaleo task where kaleoNodeId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @return the matching kaleo task, or <code>null</code> if a matching kaleo task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTask fetchByKaleoNodeId(long kaleoNodeId)
		throws SystemException {
		return fetchByKaleoNodeId(kaleoNodeId, true);
	}

	/**
	 * Returns the kaleo task where kaleoNodeId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching kaleo task, or <code>null</code> if a matching kaleo task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTask fetchByKaleoNodeId(long kaleoNodeId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { kaleoNodeId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_KALEONODEID,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_KALEOTASK_WHERE);

			query.append(_FINDER_COLUMN_KALEONODEID_KALEONODEID_2);

			query.append(KaleoTaskModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNodeId);

				List<KaleoTask> list = q.list();

				result = list;

				KaleoTask kaleoTask = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KALEONODEID,
						finderArgs, list);
				}
				else {
					kaleoTask = list.get(0);

					cacheResult(kaleoTask);

					if ((kaleoTask.getKaleoNodeId() != kaleoNodeId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KALEONODEID,
							finderArgs, kaleoTask);
					}
				}

				return kaleoTask;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KALEONODEID,
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
				return (KaleoTask)result;
			}
		}
	}

	/**
	 * Returns all the kaleo tasks.
	 *
	 * @return the kaleo tasks
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTask> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo tasks
	 * @param end the upper bound of the range of kaleo tasks (not inclusive)
	 * @return the range of kaleo tasks
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTask> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo tasks
	 * @param end the upper bound of the range of kaleo tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo tasks
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTask> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTask> list = (List<KaleoTask>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KALEOTASK);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEOTASK.concat(KaleoTaskModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<KaleoTask>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KaleoTask>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the kaleo tasks where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (KaleoTask kaleoTask : findByCompanyId(companyId)) {
			kaleoTaskPersistence.remove(kaleoTask);
		}
	}

	/**
	 * Removes all the kaleo tasks where kaleoDefinitionId = &#63; from the database.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		for (KaleoTask kaleoTask : findByKaleoDefinitionId(kaleoDefinitionId)) {
			kaleoTaskPersistence.remove(kaleoTask);
		}
	}

	/**
	 * Removes the kaleo task where kaleoNodeId = &#63; from the database.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKaleoNodeId(long kaleoNodeId)
		throws NoSuchTaskException, SystemException {
		KaleoTask kaleoTask = findByKaleoNodeId(kaleoNodeId);

		kaleoTaskPersistence.remove(kaleoTask);
	}

	/**
	 * Removes all the kaleo tasks from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (KaleoTask kaleoTask : findAll()) {
			kaleoTaskPersistence.remove(kaleoTask);
		}
	}

	/**
	 * Returns the number of kaleo tasks where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching kaleo tasks
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTASK_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPANYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of kaleo tasks where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the number of matching kaleo tasks
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoDefinitionId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTASK_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of kaleo tasks where kaleoNodeId = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @return the number of matching kaleo tasks
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKaleoNodeId(long kaleoNodeId) throws SystemException {
		Object[] finderArgs = new Object[] { kaleoNodeId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEONODEID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTASK_WHERE);

			query.append(_FINDER_COLUMN_KALEONODEID_KALEONODEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNodeId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KALEONODEID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of kaleo tasks.
	 *
	 * @return the number of kaleo tasks
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

				Query q = session.createQuery(_SQL_COUNT_KALEOTASK);

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
	 * Initializes the kaleo task persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.kaleo.model.KaleoTask")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoTask>> listenersList = new ArrayList<ModelListener<KaleoTask>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoTask>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(KaleoTaskImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = KaleoActionPersistence.class)
	protected KaleoActionPersistence kaleoActionPersistence;
	@BeanReference(type = KaleoConditionPersistence.class)
	protected KaleoConditionPersistence kaleoConditionPersistence;
	@BeanReference(type = KaleoDefinitionPersistence.class)
	protected KaleoDefinitionPersistence kaleoDefinitionPersistence;
	@BeanReference(type = KaleoInstancePersistence.class)
	protected KaleoInstancePersistence kaleoInstancePersistence;
	@BeanReference(type = KaleoInstanceTokenPersistence.class)
	protected KaleoInstanceTokenPersistence kaleoInstanceTokenPersistence;
	@BeanReference(type = KaleoLogPersistence.class)
	protected KaleoLogPersistence kaleoLogPersistence;
	@BeanReference(type = KaleoNodePersistence.class)
	protected KaleoNodePersistence kaleoNodePersistence;
	@BeanReference(type = KaleoNotificationPersistence.class)
	protected KaleoNotificationPersistence kaleoNotificationPersistence;
	@BeanReference(type = KaleoNotificationRecipientPersistence.class)
	protected KaleoNotificationRecipientPersistence kaleoNotificationRecipientPersistence;
	@BeanReference(type = KaleoTaskPersistence.class)
	protected KaleoTaskPersistence kaleoTaskPersistence;
	@BeanReference(type = KaleoTaskAssignmentPersistence.class)
	protected KaleoTaskAssignmentPersistence kaleoTaskAssignmentPersistence;
	@BeanReference(type = KaleoTaskAssignmentInstancePersistence.class)
	protected KaleoTaskAssignmentInstancePersistence kaleoTaskAssignmentInstancePersistence;
	@BeanReference(type = KaleoTaskInstanceTokenPersistence.class)
	protected KaleoTaskInstanceTokenPersistence kaleoTaskInstanceTokenPersistence;
	@BeanReference(type = KaleoTimerPersistence.class)
	protected KaleoTimerPersistence kaleoTimerPersistence;
	@BeanReference(type = KaleoTimerInstanceTokenPersistence.class)
	protected KaleoTimerInstanceTokenPersistence kaleoTimerInstanceTokenPersistence;
	@BeanReference(type = KaleoTransitionPersistence.class)
	protected KaleoTransitionPersistence kaleoTransitionPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_KALEOTASK = "SELECT kaleoTask FROM KaleoTask kaleoTask";
	private static final String _SQL_SELECT_KALEOTASK_WHERE = "SELECT kaleoTask FROM KaleoTask kaleoTask WHERE ";
	private static final String _SQL_COUNT_KALEOTASK = "SELECT COUNT(kaleoTask) FROM KaleoTask kaleoTask";
	private static final String _SQL_COUNT_KALEOTASK_WHERE = "SELECT COUNT(kaleoTask) FROM KaleoTask kaleoTask WHERE ";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoTask.companyId = ?";
	private static final String _FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2 =
		"kaleoTask.kaleoDefinitionId = ?";
	private static final String _FINDER_COLUMN_KALEONODEID_KALEONODEID_2 = "kaleoTask.kaleoNodeId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoTask.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoTask exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoTask exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(KaleoTaskPersistenceImpl.class);
	private static KaleoTask _nullKaleoTask = new KaleoTaskImpl() {
			public Object clone() {
				return this;
			}

			public CacheModel<KaleoTask> toCacheModel() {
				return _nullKaleoTaskCacheModel;
			}
		};

	private static CacheModel<KaleoTask> _nullKaleoTaskCacheModel = new CacheModel<KaleoTask>() {
			public KaleoTask toEntityModel() {
				return _nullKaleoTask;
			}
		};
}