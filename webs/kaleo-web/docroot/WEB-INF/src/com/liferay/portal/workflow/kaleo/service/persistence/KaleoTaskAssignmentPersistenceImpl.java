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
import com.liferay.portal.service.persistence.RolePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the kaleo task assignment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskAssignmentPersistence
 * @see KaleoTaskAssignmentUtil
 * @generated
 */
public class KaleoTaskAssignmentPersistenceImpl extends BasePersistenceImpl<KaleoTaskAssignment>
	implements KaleoTaskAssignmentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KaleoTaskAssignmentUtil} to access the kaleo task assignment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoTaskAssignmentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			KaleoTaskAssignmentModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEODEFINITIONID =
		new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID =
		new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByKaleoDefinitionId", new String[] { Long.class.getName() },
			KaleoTaskAssignmentModelImpl.KALEODEFINITIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEODEFINITIONID = new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKaleoDefinitionId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KCN_KCPK = new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKCN_KCPK",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK =
		new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByKCN_KCPK",
			new String[] { String.class.getName(), Long.class.getName() },
			KaleoTaskAssignmentModelImpl.KALEOCLASSNAME_COLUMN_BITMASK |
			KaleoTaskAssignmentModelImpl.KALEOCLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KCN_KCPK = new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKCN_KCPK",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KCN_KCPK_ACN =
		new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKCN_KCPK_ACN",
			new String[] {
				String.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_ACN =
		new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByKCN_KCPK_ACN",
			new String[] {
				String.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			KaleoTaskAssignmentModelImpl.KALEOCLASSNAME_COLUMN_BITMASK |
			KaleoTaskAssignmentModelImpl.KALEOCLASSPK_COLUMN_BITMASK |
			KaleoTaskAssignmentModelImpl.ASSIGNEECLASSNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KCN_KCPK_ACN = new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKCN_KCPK_ACN",
			new String[] {
				String.class.getName(), Long.class.getName(),
				String.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the kaleo task assignment in the entity cache if it is enabled.
	 *
	 * @param kaleoTaskAssignment the kaleo task assignment
	 */
	public void cacheResult(KaleoTaskAssignment kaleoTaskAssignment) {
		EntityCacheUtil.putResult(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class, kaleoTaskAssignment.getPrimaryKey(),
			kaleoTaskAssignment);

		kaleoTaskAssignment.resetOriginalValues();
	}

	/**
	 * Caches the kaleo task assignments in the entity cache if it is enabled.
	 *
	 * @param kaleoTaskAssignments the kaleo task assignments
	 */
	public void cacheResult(List<KaleoTaskAssignment> kaleoTaskAssignments) {
		for (KaleoTaskAssignment kaleoTaskAssignment : kaleoTaskAssignments) {
			if (EntityCacheUtil.getResult(
						KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTaskAssignmentImpl.class,
						kaleoTaskAssignment.getPrimaryKey()) == null) {
				cacheResult(kaleoTaskAssignment);
			}
			else {
				kaleoTaskAssignment.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all kaleo task assignments.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(KaleoTaskAssignmentImpl.class.getName());
		}

		EntityCacheUtil.clearCache(KaleoTaskAssignmentImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the kaleo task assignment.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KaleoTaskAssignment kaleoTaskAssignment) {
		EntityCacheUtil.removeResult(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class, kaleoTaskAssignment.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Creates a new kaleo task assignment with the primary key. Does not add the kaleo task assignment to the database.
	 *
	 * @param kaleoTaskAssignmentId the primary key for the new kaleo task assignment
	 * @return the new kaleo task assignment
	 */
	public KaleoTaskAssignment create(long kaleoTaskAssignmentId) {
		KaleoTaskAssignment kaleoTaskAssignment = new KaleoTaskAssignmentImpl();

		kaleoTaskAssignment.setNew(true);
		kaleoTaskAssignment.setPrimaryKey(kaleoTaskAssignmentId);

		return kaleoTaskAssignment;
	}

	/**
	 * Removes the kaleo task assignment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo task assignment
	 * @return the kaleo task assignment that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo task assignment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskAssignment remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the kaleo task assignment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTaskAssignmentId the primary key of the kaleo task assignment
	 * @return the kaleo task assignment that was removed
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a kaleo task assignment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignment remove(long kaleoTaskAssignmentId)
		throws NoSuchTaskAssignmentException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoTaskAssignment kaleoTaskAssignment = (KaleoTaskAssignment)session.get(KaleoTaskAssignmentImpl.class,
					Long.valueOf(kaleoTaskAssignmentId));

			if (kaleoTaskAssignment == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						kaleoTaskAssignmentId);
				}

				throw new NoSuchTaskAssignmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoTaskAssignmentId);
			}

			return kaleoTaskAssignmentPersistence.remove(kaleoTaskAssignment);
		}
		catch (NoSuchTaskAssignmentException nsee) {
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
	 * Removes the kaleo task assignment from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTaskAssignment the kaleo task assignment
	 * @return the kaleo task assignment that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskAssignment remove(KaleoTaskAssignment kaleoTaskAssignment)
		throws SystemException {
		return super.remove(kaleoTaskAssignment);
	}

	@Override
	protected KaleoTaskAssignment removeImpl(
		KaleoTaskAssignment kaleoTaskAssignment) throws SystemException {
		kaleoTaskAssignment = toUnwrappedModel(kaleoTaskAssignment);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, kaleoTaskAssignment);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		EntityCacheUtil.removeResult(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class, kaleoTaskAssignment.getPrimaryKey());

		return kaleoTaskAssignment;
	}

	@Override
	public KaleoTaskAssignment updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment,
		boolean merge) throws SystemException {
		kaleoTaskAssignment = toUnwrappedModel(kaleoTaskAssignment);

		boolean isNew = kaleoTaskAssignment.isNew();

		KaleoTaskAssignmentModelImpl kaleoTaskAssignmentModelImpl = (KaleoTaskAssignmentModelImpl)kaleoTaskAssignment;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kaleoTaskAssignment, merge);

			kaleoTaskAssignment.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !KaleoTaskAssignmentModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((kaleoTaskAssignmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kaleoTaskAssignmentModelImpl.getOriginalCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] {
						Long.valueOf(kaleoTaskAssignmentModelImpl.getCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((kaleoTaskAssignmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kaleoTaskAssignmentModelImpl.getOriginalKaleoDefinitionId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);

				args = new Object[] {
						Long.valueOf(kaleoTaskAssignmentModelImpl.getKaleoDefinitionId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);
			}

			if ((kaleoTaskAssignmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoTaskAssignmentModelImpl.getOriginalKaleoClassName(),
						Long.valueOf(kaleoTaskAssignmentModelImpl.getOriginalKaleoClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KCN_KCPK, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK,
					args);

				args = new Object[] {
						kaleoTaskAssignmentModelImpl.getKaleoClassName(),
						Long.valueOf(kaleoTaskAssignmentModelImpl.getKaleoClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KCN_KCPK, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK,
					args);
			}

			if ((kaleoTaskAssignmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_ACN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoTaskAssignmentModelImpl.getOriginalKaleoClassName(),
						Long.valueOf(kaleoTaskAssignmentModelImpl.getOriginalKaleoClassPK()),
						
						kaleoTaskAssignmentModelImpl.getOriginalAssigneeClassName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KCN_KCPK_ACN,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_ACN,
					args);

				args = new Object[] {
						kaleoTaskAssignmentModelImpl.getKaleoClassName(),
						Long.valueOf(kaleoTaskAssignmentModelImpl.getKaleoClassPK()),
						
						kaleoTaskAssignmentModelImpl.getAssigneeClassName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KCN_KCPK_ACN,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_ACN,
					args);
			}
		}

		EntityCacheUtil.putResult(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class, kaleoTaskAssignment.getPrimaryKey(),
			kaleoTaskAssignment);

		return kaleoTaskAssignment;
	}

	protected KaleoTaskAssignment toUnwrappedModel(
		KaleoTaskAssignment kaleoTaskAssignment) {
		if (kaleoTaskAssignment instanceof KaleoTaskAssignmentImpl) {
			return kaleoTaskAssignment;
		}

		KaleoTaskAssignmentImpl kaleoTaskAssignmentImpl = new KaleoTaskAssignmentImpl();

		kaleoTaskAssignmentImpl.setNew(kaleoTaskAssignment.isNew());
		kaleoTaskAssignmentImpl.setPrimaryKey(kaleoTaskAssignment.getPrimaryKey());

		kaleoTaskAssignmentImpl.setKaleoTaskAssignmentId(kaleoTaskAssignment.getKaleoTaskAssignmentId());
		kaleoTaskAssignmentImpl.setGroupId(kaleoTaskAssignment.getGroupId());
		kaleoTaskAssignmentImpl.setCompanyId(kaleoTaskAssignment.getCompanyId());
		kaleoTaskAssignmentImpl.setUserId(kaleoTaskAssignment.getUserId());
		kaleoTaskAssignmentImpl.setUserName(kaleoTaskAssignment.getUserName());
		kaleoTaskAssignmentImpl.setCreateDate(kaleoTaskAssignment.getCreateDate());
		kaleoTaskAssignmentImpl.setModifiedDate(kaleoTaskAssignment.getModifiedDate());
		kaleoTaskAssignmentImpl.setKaleoClassName(kaleoTaskAssignment.getKaleoClassName());
		kaleoTaskAssignmentImpl.setKaleoClassPK(kaleoTaskAssignment.getKaleoClassPK());
		kaleoTaskAssignmentImpl.setKaleoDefinitionId(kaleoTaskAssignment.getKaleoDefinitionId());
		kaleoTaskAssignmentImpl.setKaleoNodeId(kaleoTaskAssignment.getKaleoNodeId());
		kaleoTaskAssignmentImpl.setAssigneeClassName(kaleoTaskAssignment.getAssigneeClassName());
		kaleoTaskAssignmentImpl.setAssigneeClassPK(kaleoTaskAssignment.getAssigneeClassPK());
		kaleoTaskAssignmentImpl.setAssigneeActionId(kaleoTaskAssignment.getAssigneeActionId());
		kaleoTaskAssignmentImpl.setAssigneeScript(kaleoTaskAssignment.getAssigneeScript());
		kaleoTaskAssignmentImpl.setAssigneeScriptLanguage(kaleoTaskAssignment.getAssigneeScriptLanguage());

		return kaleoTaskAssignmentImpl;
	}

	/**
	 * Returns the kaleo task assignment with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo task assignment
	 * @return the kaleo task assignment
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo task assignment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskAssignment findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the kaleo task assignment with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException} if it could not be found.
	 *
	 * @param kaleoTaskAssignmentId the primary key of the kaleo task assignment
	 * @return the kaleo task assignment
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a kaleo task assignment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignment findByPrimaryKey(long kaleoTaskAssignmentId)
		throws NoSuchTaskAssignmentException, SystemException {
		KaleoTaskAssignment kaleoTaskAssignment = fetchByPrimaryKey(kaleoTaskAssignmentId);

		if (kaleoTaskAssignment == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoTaskAssignmentId);
			}

			throw new NoSuchTaskAssignmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				kaleoTaskAssignmentId);
		}

		return kaleoTaskAssignment;
	}

	/**
	 * Returns the kaleo task assignment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo task assignment
	 * @return the kaleo task assignment, or <code>null</code> if a kaleo task assignment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskAssignment fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the kaleo task assignment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoTaskAssignmentId the primary key of the kaleo task assignment
	 * @return the kaleo task assignment, or <code>null</code> if a kaleo task assignment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignment fetchByPrimaryKey(long kaleoTaskAssignmentId)
		throws SystemException {
		KaleoTaskAssignment kaleoTaskAssignment = (KaleoTaskAssignment)EntityCacheUtil.getResult(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
				KaleoTaskAssignmentImpl.class, kaleoTaskAssignmentId);

		if (kaleoTaskAssignment == _nullKaleoTaskAssignment) {
			return null;
		}

		if (kaleoTaskAssignment == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				kaleoTaskAssignment = (KaleoTaskAssignment)session.get(KaleoTaskAssignmentImpl.class,
						Long.valueOf(kaleoTaskAssignmentId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (kaleoTaskAssignment != null) {
					cacheResult(kaleoTaskAssignment);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTaskAssignmentImpl.class, kaleoTaskAssignmentId,
						_nullKaleoTaskAssignment);
				}

				closeSession(session);
			}
		}

		return kaleoTaskAssignment;
	}

	/**
	 * Returns all the kaleo task assignments where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching kaleo task assignments
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignment> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the kaleo task assignments where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo task assignments
	 * @param end the upper bound of the range of kaleo task assignments (not inclusive)
	 * @return the range of matching kaleo task assignments
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignment> findByCompanyId(long companyId, int start,
		int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo task assignments where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo task assignments
	 * @param end the upper bound of the range of kaleo task assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo task assignments
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignment> findByCompanyId(long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
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

		List<KaleoTaskAssignment> list = (List<KaleoTaskAssignment>)FinderCacheUtil.getResult(finderPath,
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

			query.append(_SQL_SELECT_KALEOTASKASSIGNMENT_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoTaskAssignmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<KaleoTaskAssignment>)QueryUtil.list(q,
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
	 * Returns the first kaleo task assignment in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task assignment
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignment findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentException, SystemException {
		List<KaleoTaskAssignment> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo task assignment in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task assignment
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignment findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentException, SystemException {
		int count = countByCompanyId(companyId);

		List<KaleoTaskAssignment> list = findByCompanyId(companyId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo task assignments before and after the current kaleo task assignment in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskAssignmentId the primary key of the current kaleo task assignment
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo task assignment
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a kaleo task assignment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignment[] findByCompanyId_PrevAndNext(
		long kaleoTaskAssignmentId, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentException, SystemException {
		KaleoTaskAssignment kaleoTaskAssignment = findByPrimaryKey(kaleoTaskAssignmentId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskAssignment[] array = new KaleoTaskAssignmentImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, kaleoTaskAssignment,
					companyId, orderByComparator, true);

			array[1] = kaleoTaskAssignment;

			array[2] = getByCompanyId_PrevAndNext(session, kaleoTaskAssignment,
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

	protected KaleoTaskAssignment getByCompanyId_PrevAndNext(Session session,
		KaleoTaskAssignment kaleoTaskAssignment, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASKASSIGNMENT_WHERE);

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
			query.append(KaleoTaskAssignmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoTaskAssignment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskAssignment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo task assignments where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the matching kaleo task assignments
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignment> findByKaleoDefinitionId(
		long kaleoDefinitionId) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo task assignments where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo task assignments
	 * @param end the upper bound of the range of kaleo task assignments (not inclusive)
	 * @return the range of matching kaleo task assignments
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignment> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo task assignments where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo task assignments
	 * @param end the upper bound of the range of kaleo task assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo task assignments
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignment> findByKaleoDefinitionId(
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

		List<KaleoTaskAssignment> list = (List<KaleoTaskAssignment>)FinderCacheUtil.getResult(finderPath,
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

			query.append(_SQL_SELECT_KALEOTASKASSIGNMENT_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoTaskAssignmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				list = (List<KaleoTaskAssignment>)QueryUtil.list(q,
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
	 * Returns the first kaleo task assignment in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task assignment
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignment findByKaleoDefinitionId_First(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentException, SystemException {
		List<KaleoTaskAssignment> list = findByKaleoDefinitionId(kaleoDefinitionId,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo task assignment in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task assignment
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignment findByKaleoDefinitionId_Last(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentException, SystemException {
		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		List<KaleoTaskAssignment> list = findByKaleoDefinitionId(kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo task assignments before and after the current kaleo task assignment in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskAssignmentId the primary key of the current kaleo task assignment
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo task assignment
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a kaleo task assignment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignment[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoTaskAssignmentId, long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentException, SystemException {
		KaleoTaskAssignment kaleoTaskAssignment = findByPrimaryKey(kaleoTaskAssignmentId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskAssignment[] array = new KaleoTaskAssignmentImpl[3];

			array[0] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoTaskAssignment, kaleoDefinitionId, orderByComparator,
					true);

			array[1] = kaleoTaskAssignment;

			array[2] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoTaskAssignment, kaleoDefinitionId, orderByComparator,
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

	protected KaleoTaskAssignment getByKaleoDefinitionId_PrevAndNext(
		Session session, KaleoTaskAssignment kaleoTaskAssignment,
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

		query.append(_SQL_SELECT_KALEOTASKASSIGNMENT_WHERE);

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
			query.append(KaleoTaskAssignmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoTaskAssignment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskAssignment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @return the matching kaleo task assignments
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignment> findByKCN_KCPK(String kaleoClassName,
		long kaleoClassPK) throws SystemException {
		return findByKCN_KCPK(kaleoClassName, kaleoClassPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param start the lower bound of the range of kaleo task assignments
	 * @param end the upper bound of the range of kaleo task assignments (not inclusive)
	 * @return the range of matching kaleo task assignments
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignment> findByKCN_KCPK(String kaleoClassName,
		long kaleoClassPK, int start, int end) throws SystemException {
		return findByKCN_KCPK(kaleoClassName, kaleoClassPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param start the lower bound of the range of kaleo task assignments
	 * @param end the upper bound of the range of kaleo task assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo task assignments
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignment> findByKCN_KCPK(String kaleoClassName,
		long kaleoClassPK, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK;
			finderArgs = new Object[] { kaleoClassName, kaleoClassPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_KCN_KCPK;
			finderArgs = new Object[] {
					kaleoClassName, kaleoClassPK,
					
					start, end, orderByComparator
				};
		}

		List<KaleoTaskAssignment> list = (List<KaleoTaskAssignment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_KALEOTASKASSIGNMENT_WHERE);

			if (kaleoClassName == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_1);
			}
			else {
				if (kaleoClassName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_2);
				}
			}

			query.append(_FINDER_COLUMN_KCN_KCPK_KALEOCLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoTaskAssignmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (kaleoClassName != null) {
					qPos.add(kaleoClassName);
				}

				qPos.add(kaleoClassPK);

				list = (List<KaleoTaskAssignment>)QueryUtil.list(q,
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
	 * Returns the first kaleo task assignment in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task assignment
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignment findByKCN_KCPK_First(String kaleoClassName,
		long kaleoClassPK, OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentException, SystemException {
		List<KaleoTaskAssignment> list = findByKCN_KCPK(kaleoClassName,
				kaleoClassPK, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoClassName=");
			msg.append(kaleoClassName);

			msg.append(", kaleoClassPK=");
			msg.append(kaleoClassPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo task assignment in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task assignment
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignment findByKCN_KCPK_Last(String kaleoClassName,
		long kaleoClassPK, OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentException, SystemException {
		int count = countByKCN_KCPK(kaleoClassName, kaleoClassPK);

		List<KaleoTaskAssignment> list = findByKCN_KCPK(kaleoClassName,
				kaleoClassPK, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoClassName=");
			msg.append(kaleoClassName);

			msg.append(", kaleoClassPK=");
			msg.append(kaleoClassPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo task assignments before and after the current kaleo task assignment in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskAssignmentId the primary key of the current kaleo task assignment
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo task assignment
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a kaleo task assignment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignment[] findByKCN_KCPK_PrevAndNext(
		long kaleoTaskAssignmentId, String kaleoClassName, long kaleoClassPK,
		OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentException, SystemException {
		KaleoTaskAssignment kaleoTaskAssignment = findByPrimaryKey(kaleoTaskAssignmentId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskAssignment[] array = new KaleoTaskAssignmentImpl[3];

			array[0] = getByKCN_KCPK_PrevAndNext(session, kaleoTaskAssignment,
					kaleoClassName, kaleoClassPK, orderByComparator, true);

			array[1] = kaleoTaskAssignment;

			array[2] = getByKCN_KCPK_PrevAndNext(session, kaleoTaskAssignment,
					kaleoClassName, kaleoClassPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTaskAssignment getByKCN_KCPK_PrevAndNext(Session session,
		KaleoTaskAssignment kaleoTaskAssignment, String kaleoClassName,
		long kaleoClassPK, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASKASSIGNMENT_WHERE);

		if (kaleoClassName == null) {
			query.append(_FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_1);
		}
		else {
			if (kaleoClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_2);
			}
		}

		query.append(_FINDER_COLUMN_KCN_KCPK_KALEOCLASSPK_2);

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
			query.append(KaleoTaskAssignmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (kaleoClassName != null) {
			qPos.add(kaleoClassName);
		}

		qPos.add(kaleoClassPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoTaskAssignment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskAssignment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param assigneeClassName the assignee class name
	 * @return the matching kaleo task assignments
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignment> findByKCN_KCPK_ACN(String kaleoClassName,
		long kaleoClassPK, String assigneeClassName) throws SystemException {
		return findByKCN_KCPK_ACN(kaleoClassName, kaleoClassPK,
			assigneeClassName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param assigneeClassName the assignee class name
	 * @param start the lower bound of the range of kaleo task assignments
	 * @param end the upper bound of the range of kaleo task assignments (not inclusive)
	 * @return the range of matching kaleo task assignments
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignment> findByKCN_KCPK_ACN(String kaleoClassName,
		long kaleoClassPK, String assigneeClassName, int start, int end)
		throws SystemException {
		return findByKCN_KCPK_ACN(kaleoClassName, kaleoClassPK,
			assigneeClassName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param assigneeClassName the assignee class name
	 * @param start the lower bound of the range of kaleo task assignments
	 * @param end the upper bound of the range of kaleo task assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo task assignments
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignment> findByKCN_KCPK_ACN(String kaleoClassName,
		long kaleoClassPK, String assigneeClassName, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_ACN;
			finderArgs = new Object[] {
					kaleoClassName, kaleoClassPK, assigneeClassName
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_KCN_KCPK_ACN;
			finderArgs = new Object[] {
					kaleoClassName, kaleoClassPK, assigneeClassName,
					
					start, end, orderByComparator
				};
		}

		List<KaleoTaskAssignment> list = (List<KaleoTaskAssignment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_KALEOTASKASSIGNMENT_WHERE);

			if (kaleoClassName == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSNAME_1);
			}
			else {
				if (kaleoClassName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSNAME_2);
				}
			}

			query.append(_FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSPK_2);

			if (assigneeClassName == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ACN_ASSIGNEECLASSNAME_1);
			}
			else {
				if (assigneeClassName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_KCN_KCPK_ACN_ASSIGNEECLASSNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_KCN_KCPK_ACN_ASSIGNEECLASSNAME_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoTaskAssignmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (kaleoClassName != null) {
					qPos.add(kaleoClassName);
				}

				qPos.add(kaleoClassPK);

				if (assigneeClassName != null) {
					qPos.add(assigneeClassName);
				}

				list = (List<KaleoTaskAssignment>)QueryUtil.list(q,
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
	 * Returns the first kaleo task assignment in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param assigneeClassName the assignee class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task assignment
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignment findByKCN_KCPK_ACN_First(String kaleoClassName,
		long kaleoClassPK, String assigneeClassName,
		OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentException, SystemException {
		List<KaleoTaskAssignment> list = findByKCN_KCPK_ACN(kaleoClassName,
				kaleoClassPK, assigneeClassName, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoClassName=");
			msg.append(kaleoClassName);

			msg.append(", kaleoClassPK=");
			msg.append(kaleoClassPK);

			msg.append(", assigneeClassName=");
			msg.append(assigneeClassName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo task assignment in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param assigneeClassName the assignee class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task assignment
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignment findByKCN_KCPK_ACN_Last(String kaleoClassName,
		long kaleoClassPK, String assigneeClassName,
		OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentException, SystemException {
		int count = countByKCN_KCPK_ACN(kaleoClassName, kaleoClassPK,
				assigneeClassName);

		List<KaleoTaskAssignment> list = findByKCN_KCPK_ACN(kaleoClassName,
				kaleoClassPK, assigneeClassName, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoClassName=");
			msg.append(kaleoClassName);

			msg.append(", kaleoClassPK=");
			msg.append(kaleoClassPK);

			msg.append(", assigneeClassName=");
			msg.append(assigneeClassName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo task assignments before and after the current kaleo task assignment in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskAssignmentId the primary key of the current kaleo task assignment
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param assigneeClassName the assignee class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo task assignment
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException if a kaleo task assignment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignment[] findByKCN_KCPK_ACN_PrevAndNext(
		long kaleoTaskAssignmentId, String kaleoClassName, long kaleoClassPK,
		String assigneeClassName, OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentException, SystemException {
		KaleoTaskAssignment kaleoTaskAssignment = findByPrimaryKey(kaleoTaskAssignmentId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskAssignment[] array = new KaleoTaskAssignmentImpl[3];

			array[0] = getByKCN_KCPK_ACN_PrevAndNext(session,
					kaleoTaskAssignment, kaleoClassName, kaleoClassPK,
					assigneeClassName, orderByComparator, true);

			array[1] = kaleoTaskAssignment;

			array[2] = getByKCN_KCPK_ACN_PrevAndNext(session,
					kaleoTaskAssignment, kaleoClassName, kaleoClassPK,
					assigneeClassName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTaskAssignment getByKCN_KCPK_ACN_PrevAndNext(
		Session session, KaleoTaskAssignment kaleoTaskAssignment,
		String kaleoClassName, long kaleoClassPK, String assigneeClassName,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASKASSIGNMENT_WHERE);

		if (kaleoClassName == null) {
			query.append(_FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSNAME_1);
		}
		else {
			if (kaleoClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSNAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSNAME_2);
			}
		}

		query.append(_FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSPK_2);

		if (assigneeClassName == null) {
			query.append(_FINDER_COLUMN_KCN_KCPK_ACN_ASSIGNEECLASSNAME_1);
		}
		else {
			if (assigneeClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ACN_ASSIGNEECLASSNAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_KCN_KCPK_ACN_ASSIGNEECLASSNAME_2);
			}
		}

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
			query.append(KaleoTaskAssignmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (kaleoClassName != null) {
			qPos.add(kaleoClassName);
		}

		qPos.add(kaleoClassPK);

		if (assigneeClassName != null) {
			qPos.add(assigneeClassName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoTaskAssignment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskAssignment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo task assignments.
	 *
	 * @return the kaleo task assignments
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignment> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo task assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo task assignments
	 * @param end the upper bound of the range of kaleo task assignments (not inclusive)
	 * @return the range of kaleo task assignments
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignment> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo task assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo task assignments
	 * @param end the upper bound of the range of kaleo task assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo task assignments
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignment> findAll(int start, int end,
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

		List<KaleoTaskAssignment> list = (List<KaleoTaskAssignment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KALEOTASKASSIGNMENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEOTASKASSIGNMENT.concat(KaleoTaskAssignmentModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<KaleoTaskAssignment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KaleoTaskAssignment>)QueryUtil.list(q,
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
	 * Removes all the kaleo task assignments where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (KaleoTaskAssignment kaleoTaskAssignment : findByCompanyId(
				companyId)) {
			kaleoTaskAssignmentPersistence.remove(kaleoTaskAssignment);
		}
	}

	/**
	 * Removes all the kaleo task assignments where kaleoDefinitionId = &#63; from the database.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		for (KaleoTaskAssignment kaleoTaskAssignment : findByKaleoDefinitionId(
				kaleoDefinitionId)) {
			kaleoTaskAssignmentPersistence.remove(kaleoTaskAssignment);
		}
	}

	/**
	 * Removes all the kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63; from the database.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKCN_KCPK(String kaleoClassName, long kaleoClassPK)
		throws SystemException {
		for (KaleoTaskAssignment kaleoTaskAssignment : findByKCN_KCPK(
				kaleoClassName, kaleoClassPK)) {
			kaleoTaskAssignmentPersistence.remove(kaleoTaskAssignment);
		}
	}

	/**
	 * Removes all the kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63; from the database.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param assigneeClassName the assignee class name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKCN_KCPK_ACN(String kaleoClassName, long kaleoClassPK,
		String assigneeClassName) throws SystemException {
		for (KaleoTaskAssignment kaleoTaskAssignment : findByKCN_KCPK_ACN(
				kaleoClassName, kaleoClassPK, assigneeClassName)) {
			kaleoTaskAssignmentPersistence.remove(kaleoTaskAssignment);
		}
	}

	/**
	 * Removes all the kaleo task assignments from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (KaleoTaskAssignment kaleoTaskAssignment : findAll()) {
			kaleoTaskAssignmentPersistence.remove(kaleoTaskAssignment);
		}
	}

	/**
	 * Returns the number of kaleo task assignments where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching kaleo task assignments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTASKASSIGNMENT_WHERE);

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
	 * Returns the number of kaleo task assignments where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the number of matching kaleo task assignments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoDefinitionId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTASKASSIGNMENT_WHERE);

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
	 * Returns the number of kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @return the number of matching kaleo task assignments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKCN_KCPK(String kaleoClassName, long kaleoClassPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoClassName, kaleoClassPK };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KCN_KCPK,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEOTASKASSIGNMENT_WHERE);

			if (kaleoClassName == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_1);
			}
			else {
				if (kaleoClassName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_2);
				}
			}

			query.append(_FINDER_COLUMN_KCN_KCPK_KALEOCLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (kaleoClassName != null) {
					qPos.add(kaleoClassName);
				}

				qPos.add(kaleoClassPK);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KCN_KCPK,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param assigneeClassName the assignee class name
	 * @return the number of matching kaleo task assignments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKCN_KCPK_ACN(String kaleoClassName, long kaleoClassPK,
		String assigneeClassName) throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoClassName, kaleoClassPK, assigneeClassName
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KCN_KCPK_ACN,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KALEOTASKASSIGNMENT_WHERE);

			if (kaleoClassName == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSNAME_1);
			}
			else {
				if (kaleoClassName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSNAME_2);
				}
			}

			query.append(_FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSPK_2);

			if (assigneeClassName == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ACN_ASSIGNEECLASSNAME_1);
			}
			else {
				if (assigneeClassName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_KCN_KCPK_ACN_ASSIGNEECLASSNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_KCN_KCPK_ACN_ASSIGNEECLASSNAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (kaleoClassName != null) {
					qPos.add(kaleoClassName);
				}

				qPos.add(kaleoClassPK);

				if (assigneeClassName != null) {
					qPos.add(assigneeClassName);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KCN_KCPK_ACN,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of kaleo task assignments.
	 *
	 * @return the number of kaleo task assignments
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KALEOTASKASSIGNMENT);

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
	 * Initializes the kaleo task assignment persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoTaskAssignment>> listenersList = new ArrayList<ModelListener<KaleoTaskAssignment>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoTaskAssignment>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(KaleoTaskAssignmentImpl.class.getName());
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
	@BeanReference(type = RolePersistence.class)
	protected RolePersistence rolePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_KALEOTASKASSIGNMENT = "SELECT kaleoTaskAssignment FROM KaleoTaskAssignment kaleoTaskAssignment";
	private static final String _SQL_SELECT_KALEOTASKASSIGNMENT_WHERE = "SELECT kaleoTaskAssignment FROM KaleoTaskAssignment kaleoTaskAssignment WHERE ";
	private static final String _SQL_COUNT_KALEOTASKASSIGNMENT = "SELECT COUNT(kaleoTaskAssignment) FROM KaleoTaskAssignment kaleoTaskAssignment";
	private static final String _SQL_COUNT_KALEOTASKASSIGNMENT_WHERE = "SELECT COUNT(kaleoTaskAssignment) FROM KaleoTaskAssignment kaleoTaskAssignment WHERE ";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoTaskAssignment.companyId = ?";
	private static final String _FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2 =
		"kaleoTaskAssignment.kaleoDefinitionId = ?";
	private static final String _FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_1 = "kaleoTaskAssignment.kaleoClassName IS NULL AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_2 = "kaleoTaskAssignment.kaleoClassName = ? AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_3 = "(kaleoTaskAssignment.kaleoClassName IS NULL OR kaleoTaskAssignment.kaleoClassName = ?) AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_KALEOCLASSPK_2 = "kaleoTaskAssignment.kaleoClassPK = ?";
	private static final String _FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSNAME_1 = "kaleoTaskAssignment.kaleoClassName IS NULL AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSNAME_2 = "kaleoTaskAssignment.kaleoClassName = ? AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSNAME_3 = "(kaleoTaskAssignment.kaleoClassName IS NULL OR kaleoTaskAssignment.kaleoClassName = ?) AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSPK_2 = "kaleoTaskAssignment.kaleoClassPK = ? AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_ACN_ASSIGNEECLASSNAME_1 = "kaleoTaskAssignment.assigneeClassName IS NULL";
	private static final String _FINDER_COLUMN_KCN_KCPK_ACN_ASSIGNEECLASSNAME_2 = "kaleoTaskAssignment.assigneeClassName = ?";
	private static final String _FINDER_COLUMN_KCN_KCPK_ACN_ASSIGNEECLASSNAME_3 = "(kaleoTaskAssignment.assigneeClassName IS NULL OR kaleoTaskAssignment.assigneeClassName = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoTaskAssignment.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoTaskAssignment exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoTaskAssignment exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(KaleoTaskAssignmentPersistenceImpl.class);
	private static KaleoTaskAssignment _nullKaleoTaskAssignment = new KaleoTaskAssignmentImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<KaleoTaskAssignment> toCacheModel() {
				return _nullKaleoTaskAssignmentCacheModel;
			}
		};

	private static CacheModel<KaleoTaskAssignment> _nullKaleoTaskAssignmentCacheModel =
		new CacheModel<KaleoTaskAssignment>() {
			public KaleoTaskAssignment toEntityModel() {
				return _nullKaleoTaskAssignment;
			}
		};
}