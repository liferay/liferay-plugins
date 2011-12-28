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
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQuery;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.RowMapper;
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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.NoSuchNotificationException;
import com.liferay.portal.workflow.kaleo.model.KaleoNotification;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the kaleo notification service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoNotificationPersistence
 * @see KaleoNotificationUtil
 * @generated
 */
public class KaleoNotificationPersistenceImpl extends BasePersistenceImpl<KaleoNotification>
	implements KaleoNotificationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KaleoNotificationUtil} to access the kaleo notification persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoNotificationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED,
			KaleoNotificationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED,
			KaleoNotificationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			KaleoNotificationModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEODEFINITIONID =
		new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED,
			KaleoNotificationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID =
		new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED,
			KaleoNotificationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByKaleoDefinitionId", new String[] { Long.class.getName() },
			KaleoNotificationModelImpl.KALEODEFINITIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEODEFINITIONID = new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKaleoDefinitionId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KCN_KCPK_ET =
		new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED,
			KaleoNotificationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKCN_KCPK_ET",
			new String[] {
				String.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_ET =
		new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED,
			KaleoNotificationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByKCN_KCPK_ET",
			new String[] {
				String.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			KaleoNotificationModelImpl.KALEOCLASSNAME_COLUMN_BITMASK |
			KaleoNotificationModelImpl.KALEOCLASSPK_COLUMN_BITMASK |
			KaleoNotificationModelImpl.EXECUTIONTYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KCN_KCPK_ET = new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKCN_KCPK_ET",
			new String[] {
				String.class.getName(), Long.class.getName(),
				String.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED,
			KaleoNotificationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED,
			KaleoNotificationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the kaleo notification in the entity cache if it is enabled.
	 *
	 * @param kaleoNotification the kaleo notification
	 */
	public void cacheResult(KaleoNotification kaleoNotification) {
		EntityCacheUtil.putResult(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationImpl.class, kaleoNotification.getPrimaryKey(),
			kaleoNotification);

		kaleoNotification.resetOriginalValues();
	}

	/**
	 * Caches the kaleo notifications in the entity cache if it is enabled.
	 *
	 * @param kaleoNotifications the kaleo notifications
	 */
	public void cacheResult(List<KaleoNotification> kaleoNotifications) {
		for (KaleoNotification kaleoNotification : kaleoNotifications) {
			if (EntityCacheUtil.getResult(
						KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
						KaleoNotificationImpl.class,
						kaleoNotification.getPrimaryKey()) == null) {
				cacheResult(kaleoNotification);
			}
			else {
				kaleoNotification.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all kaleo notifications.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(KaleoNotificationImpl.class.getName());
		}

		EntityCacheUtil.clearCache(KaleoNotificationImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the kaleo notification.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KaleoNotification kaleoNotification) {
		EntityCacheUtil.removeResult(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationImpl.class, kaleoNotification.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<KaleoNotification> kaleoNotifications) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (KaleoNotification kaleoNotification : kaleoNotifications) {
			EntityCacheUtil.removeResult(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
				KaleoNotificationImpl.class, kaleoNotification.getPrimaryKey());
		}
	}

	/**
	 * Creates a new kaleo notification with the primary key. Does not add the kaleo notification to the database.
	 *
	 * @param kaleoNotificationId the primary key for the new kaleo notification
	 * @return the new kaleo notification
	 */
	public KaleoNotification create(long kaleoNotificationId) {
		KaleoNotification kaleoNotification = new KaleoNotificationImpl();

		kaleoNotification.setNew(true);
		kaleoNotification.setPrimaryKey(kaleoNotificationId);

		return kaleoNotification;
	}

	/**
	 * Removes the kaleo notification with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoNotificationId the primary key of the kaleo notification
	 * @return the kaleo notification that was removed
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException if a kaleo notification with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNotification remove(long kaleoNotificationId)
		throws NoSuchNotificationException, SystemException {
		return remove(Long.valueOf(kaleoNotificationId));
	}

	/**
	 * Removes the kaleo notification with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo notification
	 * @return the kaleo notification that was removed
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException if a kaleo notification with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoNotification remove(Serializable primaryKey)
		throws NoSuchNotificationException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoNotification kaleoNotification = (KaleoNotification)session.get(KaleoNotificationImpl.class,
					primaryKey);

			if (kaleoNotification == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNotificationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(kaleoNotification);
		}
		catch (NoSuchNotificationException nsee) {
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
	protected KaleoNotification removeImpl(KaleoNotification kaleoNotification)
		throws SystemException {
		kaleoNotification = toUnwrappedModel(kaleoNotification);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, kaleoNotification);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(kaleoNotification);

		return kaleoNotification;
	}

	@Override
	public KaleoNotification updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoNotification kaleoNotification,
		boolean merge) throws SystemException {
		kaleoNotification = toUnwrappedModel(kaleoNotification);

		boolean isNew = kaleoNotification.isNew();

		KaleoNotificationModelImpl kaleoNotificationModelImpl = (KaleoNotificationModelImpl)kaleoNotification;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kaleoNotification, merge);

			kaleoNotification.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !KaleoNotificationModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((kaleoNotificationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kaleoNotificationModelImpl.getOriginalCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] {
						Long.valueOf(kaleoNotificationModelImpl.getCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((kaleoNotificationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kaleoNotificationModelImpl.getOriginalKaleoDefinitionId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);

				args = new Object[] {
						Long.valueOf(kaleoNotificationModelImpl.getKaleoDefinitionId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);
			}

			if ((kaleoNotificationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_ET.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoNotificationModelImpl.getOriginalKaleoClassName(),
						Long.valueOf(kaleoNotificationModelImpl.getOriginalKaleoClassPK()),
						
						kaleoNotificationModelImpl.getOriginalExecutionType()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KCN_KCPK_ET,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_ET,
					args);

				args = new Object[] {
						kaleoNotificationModelImpl.getKaleoClassName(),
						Long.valueOf(kaleoNotificationModelImpl.getKaleoClassPK()),
						
						kaleoNotificationModelImpl.getExecutionType()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KCN_KCPK_ET,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_ET,
					args);
			}
		}

		EntityCacheUtil.putResult(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationImpl.class, kaleoNotification.getPrimaryKey(),
			kaleoNotification);

		return kaleoNotification;
	}

	protected KaleoNotification toUnwrappedModel(
		KaleoNotification kaleoNotification) {
		if (kaleoNotification instanceof KaleoNotificationImpl) {
			return kaleoNotification;
		}

		KaleoNotificationImpl kaleoNotificationImpl = new KaleoNotificationImpl();

		kaleoNotificationImpl.setNew(kaleoNotification.isNew());
		kaleoNotificationImpl.setPrimaryKey(kaleoNotification.getPrimaryKey());

		kaleoNotificationImpl.setKaleoNotificationId(kaleoNotification.getKaleoNotificationId());
		kaleoNotificationImpl.setGroupId(kaleoNotification.getGroupId());
		kaleoNotificationImpl.setCompanyId(kaleoNotification.getCompanyId());
		kaleoNotificationImpl.setUserId(kaleoNotification.getUserId());
		kaleoNotificationImpl.setUserName(kaleoNotification.getUserName());
		kaleoNotificationImpl.setCreateDate(kaleoNotification.getCreateDate());
		kaleoNotificationImpl.setModifiedDate(kaleoNotification.getModifiedDate());
		kaleoNotificationImpl.setKaleoClassName(kaleoNotification.getKaleoClassName());
		kaleoNotificationImpl.setKaleoClassPK(kaleoNotification.getKaleoClassPK());
		kaleoNotificationImpl.setKaleoDefinitionId(kaleoNotification.getKaleoDefinitionId());
		kaleoNotificationImpl.setKaleoNodeName(kaleoNotification.getKaleoNodeName());
		kaleoNotificationImpl.setName(kaleoNotification.getName());
		kaleoNotificationImpl.setDescription(kaleoNotification.getDescription());
		kaleoNotificationImpl.setExecutionType(kaleoNotification.getExecutionType());
		kaleoNotificationImpl.setTemplate(kaleoNotification.getTemplate());
		kaleoNotificationImpl.setTemplateLanguage(kaleoNotification.getTemplateLanguage());
		kaleoNotificationImpl.setNotificationTypes(kaleoNotification.getNotificationTypes());

		return kaleoNotificationImpl;
	}

	/**
	 * Returns the kaleo notification with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo notification
	 * @return the kaleo notification
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo notification with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoNotification findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the kaleo notification with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchNotificationException} if it could not be found.
	 *
	 * @param kaleoNotificationId the primary key of the kaleo notification
	 * @return the kaleo notification
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException if a kaleo notification with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNotification findByPrimaryKey(long kaleoNotificationId)
		throws NoSuchNotificationException, SystemException {
		KaleoNotification kaleoNotification = fetchByPrimaryKey(kaleoNotificationId);

		if (kaleoNotification == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoNotificationId);
			}

			throw new NoSuchNotificationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				kaleoNotificationId);
		}

		return kaleoNotification;
	}

	/**
	 * Returns the kaleo notification with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo notification
	 * @return the kaleo notification, or <code>null</code> if a kaleo notification with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoNotification fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the kaleo notification with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoNotificationId the primary key of the kaleo notification
	 * @return the kaleo notification, or <code>null</code> if a kaleo notification with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNotification fetchByPrimaryKey(long kaleoNotificationId)
		throws SystemException {
		KaleoNotification kaleoNotification = (KaleoNotification)EntityCacheUtil.getResult(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
				KaleoNotificationImpl.class, kaleoNotificationId);

		if (kaleoNotification == _nullKaleoNotification) {
			return null;
		}

		if (kaleoNotification == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				kaleoNotification = (KaleoNotification)session.get(KaleoNotificationImpl.class,
						Long.valueOf(kaleoNotificationId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (kaleoNotification != null) {
					cacheResult(kaleoNotification);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
						KaleoNotificationImpl.class, kaleoNotificationId,
						_nullKaleoNotification);
				}

				closeSession(session);
			}
		}

		return kaleoNotification;
	}

	/**
	 * Returns all the kaleo notifications where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNotification> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the kaleo notifications where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo notifications
	 * @param end the upper bound of the range of kaleo notifications (not inclusive)
	 * @return the range of matching kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNotification> findByCompanyId(long companyId, int start,
		int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo notifications where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo notifications
	 * @param end the upper bound of the range of kaleo notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNotification> findByCompanyId(long companyId, int start,
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

		List<KaleoNotification> list = (List<KaleoNotification>)FinderCacheUtil.getResult(finderPath,
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

			query.append(_SQL_SELECT_KALEONOTIFICATION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoNotificationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<KaleoNotification>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first kaleo notification in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo notification
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException if a matching kaleo notification could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNotification findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchNotificationException, SystemException {
		List<KaleoNotification> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchNotificationException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo notification in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo notification
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException if a matching kaleo notification could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNotification findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchNotificationException, SystemException {
		int count = countByCompanyId(companyId);

		List<KaleoNotification> list = findByCompanyId(companyId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchNotificationException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo notifications before and after the current kaleo notification in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoNotificationId the primary key of the current kaleo notification
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo notification
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException if a kaleo notification with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNotification[] findByCompanyId_PrevAndNext(
		long kaleoNotificationId, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchNotificationException, SystemException {
		KaleoNotification kaleoNotification = findByPrimaryKey(kaleoNotificationId);

		Session session = null;

		try {
			session = openSession();

			KaleoNotification[] array = new KaleoNotificationImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, kaleoNotification,
					companyId, orderByComparator, true);

			array[1] = kaleoNotification;

			array[2] = getByCompanyId_PrevAndNext(session, kaleoNotification,
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

	protected KaleoNotification getByCompanyId_PrevAndNext(Session session,
		KaleoNotification kaleoNotification, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEONOTIFICATION_WHERE);

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
			query.append(KaleoNotificationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoNotification);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoNotification> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo notifications where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the matching kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNotification> findByKaleoDefinitionId(
		long kaleoDefinitionId) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo notifications where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo notifications
	 * @param end the upper bound of the range of kaleo notifications (not inclusive)
	 * @return the range of matching kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNotification> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo notifications where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo notifications
	 * @param end the upper bound of the range of kaleo notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNotification> findByKaleoDefinitionId(
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

		List<KaleoNotification> list = (List<KaleoNotification>)FinderCacheUtil.getResult(finderPath,
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

			query.append(_SQL_SELECT_KALEONOTIFICATION_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoNotificationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				list = (List<KaleoNotification>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first kaleo notification in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo notification
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException if a matching kaleo notification could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNotification findByKaleoDefinitionId_First(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchNotificationException, SystemException {
		List<KaleoNotification> list = findByKaleoDefinitionId(kaleoDefinitionId,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchNotificationException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo notification in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo notification
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException if a matching kaleo notification could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNotification findByKaleoDefinitionId_Last(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchNotificationException, SystemException {
		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		List<KaleoNotification> list = findByKaleoDefinitionId(kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchNotificationException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo notifications before and after the current kaleo notification in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoNotificationId the primary key of the current kaleo notification
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo notification
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException if a kaleo notification with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNotification[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoNotificationId, long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchNotificationException, SystemException {
		KaleoNotification kaleoNotification = findByPrimaryKey(kaleoNotificationId);

		Session session = null;

		try {
			session = openSession();

			KaleoNotification[] array = new KaleoNotificationImpl[3];

			array[0] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoNotification, kaleoDefinitionId, orderByComparator,
					true);

			array[1] = kaleoNotification;

			array[2] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoNotification, kaleoDefinitionId, orderByComparator,
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

	protected KaleoNotification getByKaleoDefinitionId_PrevAndNext(
		Session session, KaleoNotification kaleoNotification,
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

		query.append(_SQL_SELECT_KALEONOTIFICATION_WHERE);

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
			query.append(KaleoNotificationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoNotification);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoNotification> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @return the matching kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNotification> findByKCN_KCPK_ET(String kaleoClassName,
		long kaleoClassPK, String executionType) throws SystemException {
		return findByKCN_KCPK_ET(kaleoClassName, kaleoClassPK, executionType,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @param start the lower bound of the range of kaleo notifications
	 * @param end the upper bound of the range of kaleo notifications (not inclusive)
	 * @return the range of matching kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNotification> findByKCN_KCPK_ET(String kaleoClassName,
		long kaleoClassPK, String executionType, int start, int end)
		throws SystemException {
		return findByKCN_KCPK_ET(kaleoClassName, kaleoClassPK, executionType,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @param start the lower bound of the range of kaleo notifications
	 * @param end the upper bound of the range of kaleo notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNotification> findByKCN_KCPK_ET(String kaleoClassName,
		long kaleoClassPK, String executionType, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_ET;
			finderArgs = new Object[] {
					kaleoClassName, kaleoClassPK, executionType
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_KCN_KCPK_ET;
			finderArgs = new Object[] {
					kaleoClassName, kaleoClassPK, executionType,
					
					start, end, orderByComparator
				};
		}

		List<KaleoNotification> list = (List<KaleoNotification>)FinderCacheUtil.getResult(finderPath,
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

			query.append(_SQL_SELECT_KALEONOTIFICATION_WHERE);

			if (kaleoClassName == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_1);
			}
			else {
				if (kaleoClassName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_2);
				}
			}

			query.append(_FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSPK_2);

			if (executionType == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_1);
			}
			else {
				if (executionType.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoNotificationModelImpl.ORDER_BY_JPQL);
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

				if (executionType != null) {
					qPos.add(executionType);
				}

				list = (List<KaleoNotification>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first kaleo notification in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo notification
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException if a matching kaleo notification could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNotification findByKCN_KCPK_ET_First(String kaleoClassName,
		long kaleoClassPK, String executionType,
		OrderByComparator orderByComparator)
		throws NoSuchNotificationException, SystemException {
		List<KaleoNotification> list = findByKCN_KCPK_ET(kaleoClassName,
				kaleoClassPK, executionType, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoClassName=");
			msg.append(kaleoClassName);

			msg.append(", kaleoClassPK=");
			msg.append(kaleoClassPK);

			msg.append(", executionType=");
			msg.append(executionType);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchNotificationException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo notification in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo notification
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException if a matching kaleo notification could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNotification findByKCN_KCPK_ET_Last(String kaleoClassName,
		long kaleoClassPK, String executionType,
		OrderByComparator orderByComparator)
		throws NoSuchNotificationException, SystemException {
		int count = countByKCN_KCPK_ET(kaleoClassName, kaleoClassPK,
				executionType);

		List<KaleoNotification> list = findByKCN_KCPK_ET(kaleoClassName,
				kaleoClassPK, executionType, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoClassName=");
			msg.append(kaleoClassName);

			msg.append(", kaleoClassPK=");
			msg.append(kaleoClassPK);

			msg.append(", executionType=");
			msg.append(executionType);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchNotificationException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo notifications before and after the current kaleo notification in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoNotificationId the primary key of the current kaleo notification
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo notification
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException if a kaleo notification with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNotification[] findByKCN_KCPK_ET_PrevAndNext(
		long kaleoNotificationId, String kaleoClassName, long kaleoClassPK,
		String executionType, OrderByComparator orderByComparator)
		throws NoSuchNotificationException, SystemException {
		KaleoNotification kaleoNotification = findByPrimaryKey(kaleoNotificationId);

		Session session = null;

		try {
			session = openSession();

			KaleoNotification[] array = new KaleoNotificationImpl[3];

			array[0] = getByKCN_KCPK_ET_PrevAndNext(session, kaleoNotification,
					kaleoClassName, kaleoClassPK, executionType,
					orderByComparator, true);

			array[1] = kaleoNotification;

			array[2] = getByKCN_KCPK_ET_PrevAndNext(session, kaleoNotification,
					kaleoClassName, kaleoClassPK, executionType,
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

	protected KaleoNotification getByKCN_KCPK_ET_PrevAndNext(Session session,
		KaleoNotification kaleoNotification, String kaleoClassName,
		long kaleoClassPK, String executionType,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEONOTIFICATION_WHERE);

		if (kaleoClassName == null) {
			query.append(_FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_1);
		}
		else {
			if (kaleoClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_2);
			}
		}

		query.append(_FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSPK_2);

		if (executionType == null) {
			query.append(_FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_1);
		}
		else {
			if (executionType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_3);
			}
			else {
				query.append(_FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_2);
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
			query.append(KaleoNotificationModelImpl.ORDER_BY_JPQL);
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

		if (executionType != null) {
			qPos.add(executionType);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoNotification);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoNotification> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo notifications.
	 *
	 * @return the kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNotification> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo notifications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo notifications
	 * @param end the upper bound of the range of kaleo notifications (not inclusive)
	 * @return the range of kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNotification> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo notifications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo notifications
	 * @param end the upper bound of the range of kaleo notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNotification> findAll(int start, int end,
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

		List<KaleoNotification> list = (List<KaleoNotification>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KALEONOTIFICATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEONOTIFICATION.concat(KaleoNotificationModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<KaleoNotification>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KaleoNotification>)QueryUtil.list(q,
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
	 * Removes all the kaleo notifications where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (KaleoNotification kaleoNotification : findByCompanyId(companyId)) {
			remove(kaleoNotification);
		}
	}

	/**
	 * Removes all the kaleo notifications where kaleoDefinitionId = &#63; from the database.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		for (KaleoNotification kaleoNotification : findByKaleoDefinitionId(
				kaleoDefinitionId)) {
			remove(kaleoNotification);
		}
	}

	/**
	 * Removes all the kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63; from the database.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKCN_KCPK_ET(String kaleoClassName, long kaleoClassPK,
		String executionType) throws SystemException {
		for (KaleoNotification kaleoNotification : findByKCN_KCPK_ET(
				kaleoClassName, kaleoClassPK, executionType)) {
			remove(kaleoNotification);
		}
	}

	/**
	 * Removes all the kaleo notifications from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (KaleoNotification kaleoNotification : findAll()) {
			remove(kaleoNotification);
		}
	}

	/**
	 * Returns the number of kaleo notifications where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEONOTIFICATION_WHERE);

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
	 * Returns the number of kaleo notifications where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the number of matching kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoDefinitionId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEONOTIFICATION_WHERE);

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
	 * Returns the number of kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @return the number of matching kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKCN_KCPK_ET(String kaleoClassName, long kaleoClassPK,
		String executionType) throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoClassName, kaleoClassPK, executionType
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KCN_KCPK_ET,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KALEONOTIFICATION_WHERE);

			if (kaleoClassName == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_1);
			}
			else {
				if (kaleoClassName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_2);
				}
			}

			query.append(_FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSPK_2);

			if (executionType == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_1);
			}
			else {
				if (executionType.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_2);
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

				if (executionType != null) {
					qPos.add(executionType);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KCN_KCPK_ET,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of kaleo notifications.
	 *
	 * @return the number of kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KALEONOTIFICATION);

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
	 * Returns all the kaleo notification recipients associated with the kaleo notification.
	 *
	 * @param pk the primary key of the kaleo notification
	 * @return the kaleo notification recipients associated with the kaleo notification
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> getKaleoNotificationRecipients(
		long pk) throws SystemException {
		return getKaleoNotificationRecipients(pk, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the kaleo notification recipients associated with the kaleo notification.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the kaleo notification
	 * @param start the lower bound of the range of kaleo notifications
	 * @param end the upper bound of the range of kaleo notifications (not inclusive)
	 * @return the range of kaleo notification recipients associated with the kaleo notification
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> getKaleoNotificationRecipients(
		long pk, int start, int end) throws SystemException {
		return getKaleoNotificationRecipients(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_KALEONOTIFICATIONRECIPIENTS = new FinderPath(com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientModelImpl.FINDER_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientImpl.class,
			com.liferay.portal.workflow.kaleo.service.persistence.KaleoNotificationRecipientPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"getKaleoNotificationRecipients",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	/**
	 * Returns an ordered range of all the kaleo notification recipients associated with the kaleo notification.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the kaleo notification
	 * @param start the lower bound of the range of kaleo notifications
	 * @param end the upper bound of the range of kaleo notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo notification recipients associated with the kaleo notification
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> getKaleoNotificationRecipients(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

		List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> list =
			(List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient>)FinderCacheUtil.getResult(FINDER_PATH_GET_KALEONOTIFICATIONRECIPIENTS,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETKALEONOTIFICATIONRECIPIENTS.concat(ORDER_BY_CLAUSE)
															 .concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETKALEONOTIFICATIONRECIPIENTS.concat(com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientModelImpl.ORDER_BY_SQL);
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("KaleoNotificationRecipient",
					com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_KALEONOTIFICATIONRECIPIENTS,
						finderArgs);
				}
				else {
					kaleoNotificationRecipientPersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_KALEONOTIFICATIONRECIPIENTS,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_KALEONOTIFICATIONRECIPIENTS_SIZE =
		new FinderPath(com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientModelImpl.FINDER_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientImpl.class,
			com.liferay.portal.workflow.kaleo.service.persistence.KaleoNotificationRecipientPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"getKaleoNotificationRecipientsSize",
			new String[] { Long.class.getName() });

	/**
	 * Returns the number of kaleo notification recipients associated with the kaleo notification.
	 *
	 * @param pk the primary key of the kaleo notification
	 * @return the number of kaleo notification recipients associated with the kaleo notification
	 * @throws SystemException if a system exception occurred
	 */
	public int getKaleoNotificationRecipientsSize(long pk)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_KALEONOTIFICATIONRECIPIENTS_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETKALEONOTIFICATIONRECIPIENTSSIZE);

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

				FinderCacheUtil.putResult(FINDER_PATH_GET_KALEONOTIFICATIONRECIPIENTS_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_KALEONOTIFICATIONRECIPIENT =
		new FinderPath(com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientModelImpl.FINDER_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientImpl.class,
			com.liferay.portal.workflow.kaleo.service.persistence.KaleoNotificationRecipientPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"containsKaleoNotificationRecipient",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the kaleo notification recipient is associated with the kaleo notification.
	 *
	 * @param pk the primary key of the kaleo notification
	 * @param kaleoNotificationRecipientPK the primary key of the kaleo notification recipient
	 * @return <code>true</code> if the kaleo notification recipient is associated with the kaleo notification; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsKaleoNotificationRecipient(long pk,
		long kaleoNotificationRecipientPK) throws SystemException {
		Object[] finderArgs = new Object[] { pk, kaleoNotificationRecipientPK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_KALEONOTIFICATIONRECIPIENT,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsKaleoNotificationRecipient.contains(
							pk, kaleoNotificationRecipientPK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_KALEONOTIFICATIONRECIPIENT,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Returns <code>true</code> if the kaleo notification has any kaleo notification recipients associated with it.
	 *
	 * @param pk the primary key of the kaleo notification to check for associations with kaleo notification recipients
	 * @return <code>true</code> if the kaleo notification has any kaleo notification recipients associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsKaleoNotificationRecipients(long pk)
		throws SystemException {
		if (getKaleoNotificationRecipientsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Initializes the kaleo notification persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.kaleo.model.KaleoNotification")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoNotification>> listenersList = new ArrayList<ModelListener<KaleoNotification>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoNotification>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsKaleoNotificationRecipient = new ContainsKaleoNotificationRecipient();
	}

	public void destroy() {
		EntityCacheUtil.removeCache(KaleoNotificationImpl.class.getName());
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
	protected ContainsKaleoNotificationRecipient containsKaleoNotificationRecipient;

	protected class ContainsKaleoNotificationRecipient {
		protected ContainsKaleoNotificationRecipient() {
			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSKALEONOTIFICATIONRECIPIENT,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long kaleoNotificationId,
			long kaleoNotificationRecipientId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(kaleoNotificationId),
						new Long(kaleoNotificationRecipientId)
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

	private static final String _SQL_SELECT_KALEONOTIFICATION = "SELECT kaleoNotification FROM KaleoNotification kaleoNotification";
	private static final String _SQL_SELECT_KALEONOTIFICATION_WHERE = "SELECT kaleoNotification FROM KaleoNotification kaleoNotification WHERE ";
	private static final String _SQL_COUNT_KALEONOTIFICATION = "SELECT COUNT(kaleoNotification) FROM KaleoNotification kaleoNotification";
	private static final String _SQL_COUNT_KALEONOTIFICATION_WHERE = "SELECT COUNT(kaleoNotification) FROM KaleoNotification kaleoNotification WHERE ";
	private static final String _SQL_GETKALEONOTIFICATIONRECIPIENTS = "SELECT {KaleoNotificationRecipient.*} FROM KaleoNotificationRecipient INNER JOIN KaleoNotification ON (KaleoNotification.kaleoNotificationId = KaleoNotificationRecipient.kaleoNotificationId) WHERE (KaleoNotification.kaleoNotificationId = ?)";
	private static final String _SQL_GETKALEONOTIFICATIONRECIPIENTSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM KaleoNotificationRecipient WHERE kaleoNotificationId = ?";
	private static final String _SQL_CONTAINSKALEONOTIFICATIONRECIPIENT = "SELECT COUNT(*) AS COUNT_VALUE FROM KaleoNotificationRecipient WHERE kaleoNotificationId = ? AND kaleoNotificationRecipientId = ?";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoNotification.companyId = ?";
	private static final String _FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2 =
		"kaleoNotification.kaleoDefinitionId = ?";
	private static final String _FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_1 = "kaleoNotification.kaleoClassName IS NULL AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_2 = "kaleoNotification.kaleoClassName = ? AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_3 = "(kaleoNotification.kaleoClassName IS NULL OR kaleoNotification.kaleoClassName = ?) AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSPK_2 = "kaleoNotification.kaleoClassPK = ? AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_1 = "kaleoNotification.executionType IS NULL";
	private static final String _FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_2 = "kaleoNotification.executionType = ?";
	private static final String _FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_3 = "(kaleoNotification.executionType IS NULL OR kaleoNotification.executionType = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoNotification.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoNotification exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoNotification exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(KaleoNotificationPersistenceImpl.class);
	private static KaleoNotification _nullKaleoNotification = new KaleoNotificationImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<KaleoNotification> toCacheModel() {
				return _nullKaleoNotificationCacheModel;
			}
		};

	private static CacheModel<KaleoNotification> _nullKaleoNotificationCacheModel =
		new CacheModel<KaleoNotification>() {
			public KaleoNotification toEntityModel() {
				return _nullKaleoNotification;
			}
		};
}