/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the kaleo task assignment instance service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskAssignmentInstancePersistence
 * @see KaleoTaskAssignmentInstanceUtil
 * @generated
 */
public class KaleoTaskAssignmentInstancePersistenceImpl
	extends BasePersistenceImpl<KaleoTaskAssignmentInstance>
	implements KaleoTaskAssignmentInstancePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KaleoTaskAssignmentInstanceUtil} to access the kaleo task assignment instance persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoTaskAssignmentInstanceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			KaleoTaskAssignmentInstanceModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCompanyId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEODEFINITIONID =
		new FinderPath(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID =
		new FinderPath(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByKaleoDefinitionId", new String[] { Long.class.getName() },
			KaleoTaskAssignmentInstanceModelImpl.KALEODEFINITIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEODEFINITIONID = new FinderPath(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKaleoDefinitionId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEOINSTANCEID =
		new FinderPath(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKaleoInstanceId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOINSTANCEID =
		new FinderPath(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByKaleoInstanceId",
			new String[] { Long.class.getName() },
			KaleoTaskAssignmentInstanceModelImpl.KALEOINSTANCEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEOINSTANCEID = new FinderPath(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKaleoInstanceId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEOTASKINSTANCETOKENID =
		new FinderPath(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBykaleoTaskInstanceTokenId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOTASKINSTANCETOKENID =
		new FinderPath(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBykaleoTaskInstanceTokenId",
			new String[] { Long.class.getName() },
			KaleoTaskAssignmentInstanceModelImpl.KALEOTASKINSTANCETOKENID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEOTASKINSTANCETOKENID =
		new FinderPath(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBykaleoTaskInstanceTokenId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

	/**
	 * Caches the kaleo task assignment instance in the entity cache if it is enabled.
	 *
	 * @param kaleoTaskAssignmentInstance the kaleo task assignment instance
	 */
	public void cacheResult(
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance) {
		EntityCacheUtil.putResult(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceImpl.class,
			kaleoTaskAssignmentInstance.getPrimaryKey(),
			kaleoTaskAssignmentInstance);

		kaleoTaskAssignmentInstance.resetOriginalValues();
	}

	/**
	 * Caches the kaleo task assignment instances in the entity cache if it is enabled.
	 *
	 * @param kaleoTaskAssignmentInstances the kaleo task assignment instances
	 */
	public void cacheResult(
		List<KaleoTaskAssignmentInstance> kaleoTaskAssignmentInstances) {
		for (KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance : kaleoTaskAssignmentInstances) {
			if (EntityCacheUtil.getResult(
						KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTaskAssignmentInstanceImpl.class,
						kaleoTaskAssignmentInstance.getPrimaryKey()) == null) {
				cacheResult(kaleoTaskAssignmentInstance);
			}
			else {
				kaleoTaskAssignmentInstance.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all kaleo task assignment instances.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(KaleoTaskAssignmentInstanceImpl.class.getName());
		}

		EntityCacheUtil.clearCache(KaleoTaskAssignmentInstanceImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the kaleo task assignment instance.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance) {
		EntityCacheUtil.removeResult(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceImpl.class,
			kaleoTaskAssignmentInstance.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<KaleoTaskAssignmentInstance> kaleoTaskAssignmentInstances) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance : kaleoTaskAssignmentInstances) {
			EntityCacheUtil.removeResult(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
				KaleoTaskAssignmentInstanceImpl.class,
				kaleoTaskAssignmentInstance.getPrimaryKey());
		}
	}

	/**
	 * Creates a new kaleo task assignment instance with the primary key. Does not add the kaleo task assignment instance to the database.
	 *
	 * @param kaleoTaskAssignmentInstanceId the primary key for the new kaleo task assignment instance
	 * @return the new kaleo task assignment instance
	 */
	public KaleoTaskAssignmentInstance create(
		long kaleoTaskAssignmentInstanceId) {
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance = new KaleoTaskAssignmentInstanceImpl();

		kaleoTaskAssignmentInstance.setNew(true);
		kaleoTaskAssignmentInstance.setPrimaryKey(kaleoTaskAssignmentInstanceId);

		return kaleoTaskAssignmentInstance;
	}

	/**
	 * Removes the kaleo task assignment instance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTaskAssignmentInstanceId the primary key of the kaleo task assignment instance
	 * @return the kaleo task assignment instance that was removed
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a kaleo task assignment instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance remove(
		long kaleoTaskAssignmentInstanceId)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		return remove(Long.valueOf(kaleoTaskAssignmentInstanceId));
	}

	/**
	 * Removes the kaleo task assignment instance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo task assignment instance
	 * @return the kaleo task assignment instance that was removed
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a kaleo task assignment instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskAssignmentInstance remove(Serializable primaryKey)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance = (KaleoTaskAssignmentInstance)session.get(KaleoTaskAssignmentInstanceImpl.class,
					primaryKey);

			if (kaleoTaskAssignmentInstance == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTaskAssignmentInstanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(kaleoTaskAssignmentInstance);
		}
		catch (NoSuchTaskAssignmentInstanceException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected KaleoTaskAssignmentInstance removeImpl(
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance)
		throws SystemException {
		kaleoTaskAssignmentInstance = toUnwrappedModel(kaleoTaskAssignmentInstance);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, kaleoTaskAssignmentInstance);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(kaleoTaskAssignmentInstance);

		return kaleoTaskAssignmentInstance;
	}

	@Override
	public KaleoTaskAssignmentInstance updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance,
		boolean merge) throws SystemException {
		kaleoTaskAssignmentInstance = toUnwrappedModel(kaleoTaskAssignmentInstance);

		boolean isNew = kaleoTaskAssignmentInstance.isNew();

		KaleoTaskAssignmentInstanceModelImpl kaleoTaskAssignmentInstanceModelImpl =
			(KaleoTaskAssignmentInstanceModelImpl)kaleoTaskAssignmentInstance;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kaleoTaskAssignmentInstance, merge);

			kaleoTaskAssignmentInstance.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew ||
				!KaleoTaskAssignmentInstanceModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((kaleoTaskAssignmentInstanceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kaleoTaskAssignmentInstanceModelImpl.getOriginalCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] {
						Long.valueOf(kaleoTaskAssignmentInstanceModelImpl.getCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((kaleoTaskAssignmentInstanceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kaleoTaskAssignmentInstanceModelImpl.getOriginalKaleoDefinitionId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);

				args = new Object[] {
						Long.valueOf(kaleoTaskAssignmentInstanceModelImpl.getKaleoDefinitionId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);
			}

			if ((kaleoTaskAssignmentInstanceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOINSTANCEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kaleoTaskAssignmentInstanceModelImpl.getOriginalKaleoInstanceId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEOINSTANCEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOINSTANCEID,
					args);

				args = new Object[] {
						Long.valueOf(kaleoTaskAssignmentInstanceModelImpl.getKaleoInstanceId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEOINSTANCEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOINSTANCEID,
					args);
			}

			if ((kaleoTaskAssignmentInstanceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOTASKINSTANCETOKENID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kaleoTaskAssignmentInstanceModelImpl.getOriginalKaleoTaskInstanceTokenId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEOTASKINSTANCETOKENID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOTASKINSTANCETOKENID,
					args);

				args = new Object[] {
						Long.valueOf(kaleoTaskAssignmentInstanceModelImpl.getKaleoTaskInstanceTokenId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEOTASKINSTANCETOKENID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOTASKINSTANCETOKENID,
					args);
			}
		}

		EntityCacheUtil.putResult(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceImpl.class,
			kaleoTaskAssignmentInstance.getPrimaryKey(),
			kaleoTaskAssignmentInstance);

		return kaleoTaskAssignmentInstance;
	}

	protected KaleoTaskAssignmentInstance toUnwrappedModel(
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance) {
		if (kaleoTaskAssignmentInstance instanceof KaleoTaskAssignmentInstanceImpl) {
			return kaleoTaskAssignmentInstance;
		}

		KaleoTaskAssignmentInstanceImpl kaleoTaskAssignmentInstanceImpl = new KaleoTaskAssignmentInstanceImpl();

		kaleoTaskAssignmentInstanceImpl.setNew(kaleoTaskAssignmentInstance.isNew());
		kaleoTaskAssignmentInstanceImpl.setPrimaryKey(kaleoTaskAssignmentInstance.getPrimaryKey());

		kaleoTaskAssignmentInstanceImpl.setKaleoTaskAssignmentInstanceId(kaleoTaskAssignmentInstance.getKaleoTaskAssignmentInstanceId());
		kaleoTaskAssignmentInstanceImpl.setGroupId(kaleoTaskAssignmentInstance.getGroupId());
		kaleoTaskAssignmentInstanceImpl.setCompanyId(kaleoTaskAssignmentInstance.getCompanyId());
		kaleoTaskAssignmentInstanceImpl.setUserId(kaleoTaskAssignmentInstance.getUserId());
		kaleoTaskAssignmentInstanceImpl.setUserName(kaleoTaskAssignmentInstance.getUserName());
		kaleoTaskAssignmentInstanceImpl.setCreateDate(kaleoTaskAssignmentInstance.getCreateDate());
		kaleoTaskAssignmentInstanceImpl.setModifiedDate(kaleoTaskAssignmentInstance.getModifiedDate());
		kaleoTaskAssignmentInstanceImpl.setKaleoDefinitionId(kaleoTaskAssignmentInstance.getKaleoDefinitionId());
		kaleoTaskAssignmentInstanceImpl.setKaleoInstanceId(kaleoTaskAssignmentInstance.getKaleoInstanceId());
		kaleoTaskAssignmentInstanceImpl.setKaleoInstanceTokenId(kaleoTaskAssignmentInstance.getKaleoInstanceTokenId());
		kaleoTaskAssignmentInstanceImpl.setKaleoTaskInstanceTokenId(kaleoTaskAssignmentInstance.getKaleoTaskInstanceTokenId());
		kaleoTaskAssignmentInstanceImpl.setKaleoTaskId(kaleoTaskAssignmentInstance.getKaleoTaskId());
		kaleoTaskAssignmentInstanceImpl.setKaleoTaskName(kaleoTaskAssignmentInstance.getKaleoTaskName());
		kaleoTaskAssignmentInstanceImpl.setAssigneeClassName(kaleoTaskAssignmentInstance.getAssigneeClassName());
		kaleoTaskAssignmentInstanceImpl.setAssigneeClassPK(kaleoTaskAssignmentInstance.getAssigneeClassPK());
		kaleoTaskAssignmentInstanceImpl.setCompleted(kaleoTaskAssignmentInstance.isCompleted());
		kaleoTaskAssignmentInstanceImpl.setCompletionDate(kaleoTaskAssignmentInstance.getCompletionDate());

		return kaleoTaskAssignmentInstanceImpl;
	}

	/**
	 * Returns the kaleo task assignment instance with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo task assignment instance
	 * @return the kaleo task assignment instance
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo task assignment instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskAssignmentInstance findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the kaleo task assignment instance with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException} if it could not be found.
	 *
	 * @param kaleoTaskAssignmentInstanceId the primary key of the kaleo task assignment instance
	 * @return the kaleo task assignment instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a kaleo task assignment instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance findByPrimaryKey(
		long kaleoTaskAssignmentInstanceId)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance = fetchByPrimaryKey(kaleoTaskAssignmentInstanceId);

		if (kaleoTaskAssignmentInstance == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoTaskAssignmentInstanceId);
			}

			throw new NoSuchTaskAssignmentInstanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				kaleoTaskAssignmentInstanceId);
		}

		return kaleoTaskAssignmentInstance;
	}

	/**
	 * Returns the kaleo task assignment instance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo task assignment instance
	 * @return the kaleo task assignment instance, or <code>null</code> if a kaleo task assignment instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskAssignmentInstance fetchByPrimaryKey(
		Serializable primaryKey) throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the kaleo task assignment instance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoTaskAssignmentInstanceId the primary key of the kaleo task assignment instance
	 * @return the kaleo task assignment instance, or <code>null</code> if a kaleo task assignment instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance fetchByPrimaryKey(
		long kaleoTaskAssignmentInstanceId) throws SystemException {
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance = (KaleoTaskAssignmentInstance)EntityCacheUtil.getResult(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
				KaleoTaskAssignmentInstanceImpl.class,
				kaleoTaskAssignmentInstanceId);

		if (kaleoTaskAssignmentInstance == _nullKaleoTaskAssignmentInstance) {
			return null;
		}

		if (kaleoTaskAssignmentInstance == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				kaleoTaskAssignmentInstance = (KaleoTaskAssignmentInstance)session.get(KaleoTaskAssignmentInstanceImpl.class,
						Long.valueOf(kaleoTaskAssignmentInstanceId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (kaleoTaskAssignmentInstance != null) {
					cacheResult(kaleoTaskAssignmentInstance);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTaskAssignmentInstanceImpl.class,
						kaleoTaskAssignmentInstanceId,
						_nullKaleoTaskAssignmentInstance);
				}

				closeSession(session);
			}
		}

		return kaleoTaskAssignmentInstance;
	}

	/**
	 * Returns all the kaleo task assignment instances where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the kaleo task assignment instances where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo task assignment instances
	 * @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	 * @return the range of matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findByCompanyId(long companyId,
		int start, int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo task assignment instances where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo task assignment instances
	 * @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findByCompanyId(long companyId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId, start, end, orderByComparator };
		}

		List<KaleoTaskAssignmentInstance> list = (List<KaleoTaskAssignmentInstance>)FinderCacheUtil.getResult(finderPath,
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

			query.append(_SQL_SELECT_KALEOTASKASSIGNMENTINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoTaskAssignmentInstanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<KaleoTaskAssignmentInstance>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first kaleo task assignment instance in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task assignment instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		List<KaleoTaskAssignmentInstance> list = findByCompanyId(companyId, 0,
				1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo task assignment instance in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task assignment instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		int count = countByCompanyId(companyId);

		List<KaleoTaskAssignmentInstance> list = findByCompanyId(companyId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo task assignment instances before and after the current kaleo task assignment instance in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskAssignmentInstanceId the primary key of the current kaleo task assignment instance
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo task assignment instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a kaleo task assignment instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance[] findByCompanyId_PrevAndNext(
		long kaleoTaskAssignmentInstanceId, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance = findByPrimaryKey(kaleoTaskAssignmentInstanceId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskAssignmentInstance[] array = new KaleoTaskAssignmentInstanceImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session,
					kaleoTaskAssignmentInstance, companyId, orderByComparator,
					true);

			array[1] = kaleoTaskAssignmentInstance;

			array[2] = getByCompanyId_PrevAndNext(session,
					kaleoTaskAssignmentInstance, companyId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTaskAssignmentInstance getByCompanyId_PrevAndNext(
		Session session,
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance,
		long companyId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASKASSIGNMENTINSTANCE_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
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

			String[] orderByFields = orderByComparator.getOrderByFields();

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
			query.append(KaleoTaskAssignmentInstanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoTaskAssignmentInstance);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskAssignmentInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo task assignment instances where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findByKaleoDefinitionId(
		long kaleoDefinitionId) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo task assignment instances where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo task assignment instances
	 * @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	 * @return the range of matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo task assignment instances where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo task assignment instances
	 * @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID;
			finderArgs = new Object[] { kaleoDefinitionId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEODEFINITIONID;
			finderArgs = new Object[] {
					kaleoDefinitionId,
					
					start, end, orderByComparator
				};
		}

		List<KaleoTaskAssignmentInstance> list = (List<KaleoTaskAssignmentInstance>)FinderCacheUtil.getResult(finderPath,
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

			query.append(_SQL_SELECT_KALEOTASKASSIGNMENTINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoTaskAssignmentInstanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				list = (List<KaleoTaskAssignmentInstance>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first kaleo task assignment instance in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task assignment instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance findByKaleoDefinitionId_First(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		List<KaleoTaskAssignmentInstance> list = findByKaleoDefinitionId(kaleoDefinitionId,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo task assignment instance in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task assignment instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance findByKaleoDefinitionId_Last(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		List<KaleoTaskAssignmentInstance> list = findByKaleoDefinitionId(kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo task assignment instances before and after the current kaleo task assignment instance in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskAssignmentInstanceId the primary key of the current kaleo task assignment instance
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo task assignment instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a kaleo task assignment instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoTaskAssignmentInstanceId, long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance = findByPrimaryKey(kaleoTaskAssignmentInstanceId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskAssignmentInstance[] array = new KaleoTaskAssignmentInstanceImpl[3];

			array[0] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoTaskAssignmentInstance, kaleoDefinitionId,
					orderByComparator, true);

			array[1] = kaleoTaskAssignmentInstance;

			array[2] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoTaskAssignmentInstance, kaleoDefinitionId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTaskAssignmentInstance getByKaleoDefinitionId_PrevAndNext(
		Session session,
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance,
		long kaleoDefinitionId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASKASSIGNMENTINSTANCE_WHERE);

		query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
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

			String[] orderByFields = orderByComparator.getOrderByFields();

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
			query.append(KaleoTaskAssignmentInstanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoTaskAssignmentInstance);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskAssignmentInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo task assignment instances where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @return the matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findByKaleoInstanceId(
		long kaleoInstanceId) throws SystemException {
		return findByKaleoInstanceId(kaleoInstanceId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo task assignment instances where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param start the lower bound of the range of kaleo task assignment instances
	 * @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	 * @return the range of matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end) throws SystemException {
		return findByKaleoInstanceId(kaleoInstanceId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo task assignment instances where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param start the lower bound of the range of kaleo task assignment instances
	 * @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOINSTANCEID;
			finderArgs = new Object[] { kaleoInstanceId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEOINSTANCEID;
			finderArgs = new Object[] {
					kaleoInstanceId,
					
					start, end, orderByComparator
				};
		}

		List<KaleoTaskAssignmentInstance> list = (List<KaleoTaskAssignmentInstance>)FinderCacheUtil.getResult(finderPath,
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

			query.append(_SQL_SELECT_KALEOTASKASSIGNMENTINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoTaskAssignmentInstanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceId);

				list = (List<KaleoTaskAssignmentInstance>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first kaleo task assignment instance in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task assignment instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance findByKaleoInstanceId_First(
		long kaleoInstanceId, OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		List<KaleoTaskAssignmentInstance> list = findByKaleoInstanceId(kaleoInstanceId,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceId=");
			msg.append(kaleoInstanceId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo task assignment instance in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task assignment instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance findByKaleoInstanceId_Last(
		long kaleoInstanceId, OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		int count = countByKaleoInstanceId(kaleoInstanceId);

		List<KaleoTaskAssignmentInstance> list = findByKaleoInstanceId(kaleoInstanceId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceId=");
			msg.append(kaleoInstanceId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo task assignment instances before and after the current kaleo task assignment instance in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskAssignmentInstanceId the primary key of the current kaleo task assignment instance
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo task assignment instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a kaleo task assignment instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance[] findByKaleoInstanceId_PrevAndNext(
		long kaleoTaskAssignmentInstanceId, long kaleoInstanceId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance = findByPrimaryKey(kaleoTaskAssignmentInstanceId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskAssignmentInstance[] array = new KaleoTaskAssignmentInstanceImpl[3];

			array[0] = getByKaleoInstanceId_PrevAndNext(session,
					kaleoTaskAssignmentInstance, kaleoInstanceId,
					orderByComparator, true);

			array[1] = kaleoTaskAssignmentInstance;

			array[2] = getByKaleoInstanceId_PrevAndNext(session,
					kaleoTaskAssignmentInstance, kaleoInstanceId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTaskAssignmentInstance getByKaleoInstanceId_PrevAndNext(
		Session session,
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance,
		long kaleoInstanceId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASKASSIGNMENTINSTANCE_WHERE);

		query.append(_FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
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

			String[] orderByFields = orderByComparator.getOrderByFields();

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
			query.append(KaleoTaskAssignmentInstanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoInstanceId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoTaskAssignmentInstance);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskAssignmentInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo task assignment instances where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	 * @return the matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findBykaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId) throws SystemException {
		return findBykaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo task assignment instances where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	 * @param start the lower bound of the range of kaleo task assignment instances
	 * @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	 * @return the range of matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findBykaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId, int start, int end)
		throws SystemException {
		return findBykaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo task assignment instances where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	 * @param start the lower bound of the range of kaleo task assignment instances
	 * @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findBykaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOTASKINSTANCETOKENID;
			finderArgs = new Object[] { kaleoTaskInstanceTokenId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEOTASKINSTANCETOKENID;
			finderArgs = new Object[] {
					kaleoTaskInstanceTokenId,
					
					start, end, orderByComparator
				};
		}

		List<KaleoTaskAssignmentInstance> list = (List<KaleoTaskAssignmentInstance>)FinderCacheUtil.getResult(finderPath,
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

			query.append(_SQL_SELECT_KALEOTASKASSIGNMENTINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_KALEOTASKINSTANCETOKENID_KALEOTASKINSTANCETOKENID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoTaskAssignmentInstanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoTaskInstanceTokenId);

				list = (List<KaleoTaskAssignmentInstance>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first kaleo task assignment instance in the ordered set where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task assignment instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance findBykaleoTaskInstanceTokenId_First(
		long kaleoTaskInstanceTokenId, OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		List<KaleoTaskAssignmentInstance> list = findBykaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoTaskInstanceTokenId=");
			msg.append(kaleoTaskInstanceTokenId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo task assignment instance in the ordered set where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task assignment instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance findBykaleoTaskInstanceTokenId_Last(
		long kaleoTaskInstanceTokenId, OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		int count = countBykaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);

		List<KaleoTaskAssignmentInstance> list = findBykaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoTaskInstanceTokenId=");
			msg.append(kaleoTaskInstanceTokenId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo task assignment instances before and after the current kaleo task assignment instance in the ordered set where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskAssignmentInstanceId the primary key of the current kaleo task assignment instance
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo task assignment instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a kaleo task assignment instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance[] findBykaleoTaskInstanceTokenId_PrevAndNext(
		long kaleoTaskAssignmentInstanceId, long kaleoTaskInstanceTokenId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance = findByPrimaryKey(kaleoTaskAssignmentInstanceId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskAssignmentInstance[] array = new KaleoTaskAssignmentInstanceImpl[3];

			array[0] = getBykaleoTaskInstanceTokenId_PrevAndNext(session,
					kaleoTaskAssignmentInstance, kaleoTaskInstanceTokenId,
					orderByComparator, true);

			array[1] = kaleoTaskAssignmentInstance;

			array[2] = getBykaleoTaskInstanceTokenId_PrevAndNext(session,
					kaleoTaskAssignmentInstance, kaleoTaskInstanceTokenId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTaskAssignmentInstance getBykaleoTaskInstanceTokenId_PrevAndNext(
		Session session,
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance,
		long kaleoTaskInstanceTokenId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASKASSIGNMENTINSTANCE_WHERE);

		query.append(_FINDER_COLUMN_KALEOTASKINSTANCETOKENID_KALEOTASKINSTANCETOKENID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
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

			String[] orderByFields = orderByComparator.getOrderByFields();

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
			query.append(KaleoTaskAssignmentInstanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoTaskInstanceTokenId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoTaskAssignmentInstance);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskAssignmentInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo task assignment instances.
	 *
	 * @return the kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findAll()
		throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo task assignment instances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo task assignment instances
	 * @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	 * @return the range of kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo task assignment instances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo task assignment instances
	 * @param end the upper bound of the range of kaleo task assignment instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<KaleoTaskAssignmentInstance> list = (List<KaleoTaskAssignmentInstance>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KALEOTASKASSIGNMENTINSTANCE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEOTASKASSIGNMENTINSTANCE.concat(KaleoTaskAssignmentInstanceModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<KaleoTaskAssignmentInstance>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KaleoTaskAssignmentInstance>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the kaleo task assignment instances where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance : findByCompanyId(
				companyId)) {
			remove(kaleoTaskAssignmentInstance);
		}
	}

	/**
	 * Removes all the kaleo task assignment instances where kaleoDefinitionId = &#63; from the database.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		for (KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance : findByKaleoDefinitionId(
				kaleoDefinitionId)) {
			remove(kaleoTaskAssignmentInstance);
		}
	}

	/**
	 * Removes all the kaleo task assignment instances where kaleoInstanceId = &#63; from the database.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKaleoInstanceId(long kaleoInstanceId)
		throws SystemException {
		for (KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance : findByKaleoInstanceId(
				kaleoInstanceId)) {
			remove(kaleoTaskAssignmentInstance);
		}
	}

	/**
	 * Removes all the kaleo task assignment instances where kaleoTaskInstanceTokenId = &#63; from the database.
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBykaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId)
		throws SystemException {
		for (KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance : findBykaleoTaskInstanceTokenId(
				kaleoTaskInstanceTokenId)) {
			remove(kaleoTaskAssignmentInstance);
		}
	}

	/**
	 * Removes all the kaleo task assignment instances from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance : findAll()) {
			remove(kaleoTaskAssignmentInstance);
		}
	}

	/**
	 * Returns the number of kaleo task assignment instances where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTASKASSIGNMENTINSTANCE_WHERE);

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
	 * Returns the number of kaleo task assignment instances where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the number of matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoDefinitionId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTASKASSIGNMENTINSTANCE_WHERE);

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
	 * Returns the number of kaleo task assignment instances where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @return the number of matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKaleoInstanceId(long kaleoInstanceId)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoInstanceId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEOINSTANCEID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTASKASSIGNMENTINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KALEOINSTANCEID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of kaleo task assignment instances where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	 * @return the number of matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public int countBykaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoTaskInstanceTokenId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEOTASKINSTANCETOKENID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTASKASSIGNMENTINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_KALEOTASKINSTANCETOKENID_KALEOTASKINSTANCETOKENID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoTaskInstanceTokenId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KALEOTASKINSTANCETOKENID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of kaleo task assignment instances.
	 *
	 * @return the number of kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KALEOTASKASSIGNMENTINSTANCE);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the kaleo task assignment instance persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoTaskAssignmentInstance>> listenersList = new ArrayList<ModelListener<KaleoTaskAssignmentInstance>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoTaskAssignmentInstance>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(KaleoTaskAssignmentInstanceImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
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
	private static final String _SQL_SELECT_KALEOTASKASSIGNMENTINSTANCE = "SELECT kaleoTaskAssignmentInstance FROM KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance";
	private static final String _SQL_SELECT_KALEOTASKASSIGNMENTINSTANCE_WHERE = "SELECT kaleoTaskAssignmentInstance FROM KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance WHERE ";
	private static final String _SQL_COUNT_KALEOTASKASSIGNMENTINSTANCE = "SELECT COUNT(kaleoTaskAssignmentInstance) FROM KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance";
	private static final String _SQL_COUNT_KALEOTASKASSIGNMENTINSTANCE_WHERE = "SELECT COUNT(kaleoTaskAssignmentInstance) FROM KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance WHERE ";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoTaskAssignmentInstance.companyId = ?";
	private static final String _FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2 =
		"kaleoTaskAssignmentInstance.kaleoDefinitionId = ?";
	private static final String _FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2 =
		"kaleoTaskAssignmentInstance.kaleoInstanceId = ?";
	private static final String _FINDER_COLUMN_KALEOTASKINSTANCETOKENID_KALEOTASKINSTANCETOKENID_2 =
		"kaleoTaskAssignmentInstance.kaleoTaskInstanceTokenId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoTaskAssignmentInstance.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoTaskAssignmentInstance exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoTaskAssignmentInstance exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(KaleoTaskAssignmentInstancePersistenceImpl.class);
	private static KaleoTaskAssignmentInstance _nullKaleoTaskAssignmentInstance = new KaleoTaskAssignmentInstanceImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<KaleoTaskAssignmentInstance> toCacheModel() {
				return _nullKaleoTaskAssignmentInstanceCacheModel;
			}
		};

	private static CacheModel<KaleoTaskAssignmentInstance> _nullKaleoTaskAssignmentInstanceCacheModel =
		new CacheModel<KaleoTaskAssignmentInstance>() {
			public KaleoTaskAssignmentInstance toEntityModel() {
				return _nullKaleoTaskAssignmentInstance;
			}
		};
}