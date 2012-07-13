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
import com.liferay.portal.kernel.util.CalendarUtil;
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
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The persistence implementation for the kaleo instance token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoInstanceTokenPersistence
 * @see KaleoInstanceTokenUtil
 * @generated
 */
public class KaleoInstanceTokenPersistenceImpl extends BasePersistenceImpl<KaleoInstanceToken>
	implements KaleoInstanceTokenPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KaleoInstanceTokenUtil} to access the kaleo instance token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoInstanceTokenImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			KaleoInstanceTokenModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEODEFINITIONID =
		new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID =
		new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByKaleoDefinitionId", new String[] { Long.class.getName() },
			KaleoInstanceTokenModelImpl.KALEODEFINITIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEODEFINITIONID = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKaleoDefinitionId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEOINSTANCEID =
		new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKaleoInstanceId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOINSTANCEID =
		new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByKaleoInstanceId",
			new String[] { Long.class.getName() },
			KaleoInstanceTokenModelImpl.KALEOINSTANCEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEOINSTANCEID = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKaleoInstanceId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_PKITI = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_PKITI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_PKITI =
		new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_PKITI",
			new String[] { Long.class.getName(), Long.class.getName() },
			KaleoInstanceTokenModelImpl.COMPANYID_COLUMN_BITMASK |
			KaleoInstanceTokenModelImpl.PARENTKALEOINSTANCETOKENID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_PKITI = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_PKITI",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_PKITI_CD =
		new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_PKITI_CD",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_PKITI_CD =
		new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_PKITI_CD",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName()
			},
			KaleoInstanceTokenModelImpl.COMPANYID_COLUMN_BITMASK |
			KaleoInstanceTokenModelImpl.PARENTKALEOINSTANCETOKENID_COLUMN_BITMASK |
			KaleoInstanceTokenModelImpl.COMPLETIONDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_PKITI_CD = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_PKITI_CD",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the kaleo instance token in the entity cache if it is enabled.
	 *
	 * @param kaleoInstanceToken the kaleo instance token
	 */
	public void cacheResult(KaleoInstanceToken kaleoInstanceToken) {
		EntityCacheUtil.putResult(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class, kaleoInstanceToken.getPrimaryKey(),
			kaleoInstanceToken);

		kaleoInstanceToken.resetOriginalValues();
	}

	/**
	 * Caches the kaleo instance tokens in the entity cache if it is enabled.
	 *
	 * @param kaleoInstanceTokens the kaleo instance tokens
	 */
	public void cacheResult(List<KaleoInstanceToken> kaleoInstanceTokens) {
		for (KaleoInstanceToken kaleoInstanceToken : kaleoInstanceTokens) {
			if (EntityCacheUtil.getResult(
						KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
						KaleoInstanceTokenImpl.class,
						kaleoInstanceToken.getPrimaryKey()) == null) {
				cacheResult(kaleoInstanceToken);
			}
			else {
				kaleoInstanceToken.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all kaleo instance tokens.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(KaleoInstanceTokenImpl.class.getName());
		}

		EntityCacheUtil.clearCache(KaleoInstanceTokenImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the kaleo instance token.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KaleoInstanceToken kaleoInstanceToken) {
		EntityCacheUtil.removeResult(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class, kaleoInstanceToken.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<KaleoInstanceToken> kaleoInstanceTokens) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (KaleoInstanceToken kaleoInstanceToken : kaleoInstanceTokens) {
			EntityCacheUtil.removeResult(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
				KaleoInstanceTokenImpl.class, kaleoInstanceToken.getPrimaryKey());
		}
	}

	/**
	 * Creates a new kaleo instance token with the primary key. Does not add the kaleo instance token to the database.
	 *
	 * @param kaleoInstanceTokenId the primary key for the new kaleo instance token
	 * @return the new kaleo instance token
	 */
	public KaleoInstanceToken create(long kaleoInstanceTokenId) {
		KaleoInstanceToken kaleoInstanceToken = new KaleoInstanceTokenImpl();

		kaleoInstanceToken.setNew(true);
		kaleoInstanceToken.setPrimaryKey(kaleoInstanceTokenId);

		return kaleoInstanceToken;
	}

	/**
	 * Removes the kaleo instance token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoInstanceTokenId the primary key of the kaleo instance token
	 * @return the kaleo instance token that was removed
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken remove(long kaleoInstanceTokenId)
		throws NoSuchInstanceTokenException, SystemException {
		return remove(Long.valueOf(kaleoInstanceTokenId));
	}

	/**
	 * Removes the kaleo instance token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo instance token
	 * @return the kaleo instance token that was removed
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoInstanceToken remove(Serializable primaryKey)
		throws NoSuchInstanceTokenException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoInstanceToken kaleoInstanceToken = (KaleoInstanceToken)session.get(KaleoInstanceTokenImpl.class,
					primaryKey);

			if (kaleoInstanceToken == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchInstanceTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(kaleoInstanceToken);
		}
		catch (NoSuchInstanceTokenException nsee) {
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
	protected KaleoInstanceToken removeImpl(
		KaleoInstanceToken kaleoInstanceToken) throws SystemException {
		kaleoInstanceToken = toUnwrappedModel(kaleoInstanceToken);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, kaleoInstanceToken);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(kaleoInstanceToken);

		return kaleoInstanceToken;
	}

	@Override
	public KaleoInstanceToken updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		boolean merge) throws SystemException {
		kaleoInstanceToken = toUnwrappedModel(kaleoInstanceToken);

		boolean isNew = kaleoInstanceToken.isNew();

		KaleoInstanceTokenModelImpl kaleoInstanceTokenModelImpl = (KaleoInstanceTokenModelImpl)kaleoInstanceToken;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kaleoInstanceToken, merge);

			kaleoInstanceToken.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !KaleoInstanceTokenModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((kaleoInstanceTokenModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kaleoInstanceTokenModelImpl.getOriginalCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] {
						Long.valueOf(kaleoInstanceTokenModelImpl.getCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((kaleoInstanceTokenModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kaleoInstanceTokenModelImpl.getOriginalKaleoDefinitionId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);

				args = new Object[] {
						Long.valueOf(kaleoInstanceTokenModelImpl.getKaleoDefinitionId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);
			}

			if ((kaleoInstanceTokenModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOINSTANCEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kaleoInstanceTokenModelImpl.getOriginalKaleoInstanceId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEOINSTANCEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOINSTANCEID,
					args);

				args = new Object[] {
						Long.valueOf(kaleoInstanceTokenModelImpl.getKaleoInstanceId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEOINSTANCEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOINSTANCEID,
					args);
			}

			if ((kaleoInstanceTokenModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_PKITI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kaleoInstanceTokenModelImpl.getOriginalCompanyId()),
						Long.valueOf(kaleoInstanceTokenModelImpl.getOriginalParentKaleoInstanceTokenId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_PKITI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_PKITI,
					args);

				args = new Object[] {
						Long.valueOf(kaleoInstanceTokenModelImpl.getCompanyId()),
						Long.valueOf(kaleoInstanceTokenModelImpl.getParentKaleoInstanceTokenId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_PKITI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_PKITI,
					args);
			}

			if ((kaleoInstanceTokenModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_PKITI_CD.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(kaleoInstanceTokenModelImpl.getOriginalCompanyId()),
						Long.valueOf(kaleoInstanceTokenModelImpl.getOriginalParentKaleoInstanceTokenId()),
						
						kaleoInstanceTokenModelImpl.getOriginalCompletionDate()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_PKITI_CD,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_PKITI_CD,
					args);

				args = new Object[] {
						Long.valueOf(kaleoInstanceTokenModelImpl.getCompanyId()),
						Long.valueOf(kaleoInstanceTokenModelImpl.getParentKaleoInstanceTokenId()),
						
						kaleoInstanceTokenModelImpl.getCompletionDate()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_PKITI_CD,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_PKITI_CD,
					args);
			}
		}

		EntityCacheUtil.putResult(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class, kaleoInstanceToken.getPrimaryKey(),
			kaleoInstanceToken);

		return kaleoInstanceToken;
	}

	protected KaleoInstanceToken toUnwrappedModel(
		KaleoInstanceToken kaleoInstanceToken) {
		if (kaleoInstanceToken instanceof KaleoInstanceTokenImpl) {
			return kaleoInstanceToken;
		}

		KaleoInstanceTokenImpl kaleoInstanceTokenImpl = new KaleoInstanceTokenImpl();

		kaleoInstanceTokenImpl.setNew(kaleoInstanceToken.isNew());
		kaleoInstanceTokenImpl.setPrimaryKey(kaleoInstanceToken.getPrimaryKey());

		kaleoInstanceTokenImpl.setKaleoInstanceTokenId(kaleoInstanceToken.getKaleoInstanceTokenId());
		kaleoInstanceTokenImpl.setGroupId(kaleoInstanceToken.getGroupId());
		kaleoInstanceTokenImpl.setCompanyId(kaleoInstanceToken.getCompanyId());
		kaleoInstanceTokenImpl.setUserId(kaleoInstanceToken.getUserId());
		kaleoInstanceTokenImpl.setUserName(kaleoInstanceToken.getUserName());
		kaleoInstanceTokenImpl.setCreateDate(kaleoInstanceToken.getCreateDate());
		kaleoInstanceTokenImpl.setModifiedDate(kaleoInstanceToken.getModifiedDate());
		kaleoInstanceTokenImpl.setKaleoDefinitionId(kaleoInstanceToken.getKaleoDefinitionId());
		kaleoInstanceTokenImpl.setKaleoInstanceId(kaleoInstanceToken.getKaleoInstanceId());
		kaleoInstanceTokenImpl.setParentKaleoInstanceTokenId(kaleoInstanceToken.getParentKaleoInstanceTokenId());
		kaleoInstanceTokenImpl.setCurrentKaleoNodeId(kaleoInstanceToken.getCurrentKaleoNodeId());
		kaleoInstanceTokenImpl.setCurrentKaleoNodeName(kaleoInstanceToken.getCurrentKaleoNodeName());
		kaleoInstanceTokenImpl.setClassName(kaleoInstanceToken.getClassName());
		kaleoInstanceTokenImpl.setClassPK(kaleoInstanceToken.getClassPK());
		kaleoInstanceTokenImpl.setCompleted(kaleoInstanceToken.isCompleted());
		kaleoInstanceTokenImpl.setCompletionDate(kaleoInstanceToken.getCompletionDate());

		return kaleoInstanceTokenImpl;
	}

	/**
	 * Returns the kaleo instance token with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo instance token
	 * @return the kaleo instance token
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoInstanceToken findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the kaleo instance token with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException} if it could not be found.
	 *
	 * @param kaleoInstanceTokenId the primary key of the kaleo instance token
	 * @return the kaleo instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken findByPrimaryKey(long kaleoInstanceTokenId)
		throws NoSuchInstanceTokenException, SystemException {
		KaleoInstanceToken kaleoInstanceToken = fetchByPrimaryKey(kaleoInstanceTokenId);

		if (kaleoInstanceToken == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoInstanceTokenId);
			}

			throw new NoSuchInstanceTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				kaleoInstanceTokenId);
		}

		return kaleoInstanceToken;
	}

	/**
	 * Returns the kaleo instance token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo instance token
	 * @return the kaleo instance token, or <code>null</code> if a kaleo instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoInstanceToken fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the kaleo instance token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoInstanceTokenId the primary key of the kaleo instance token
	 * @return the kaleo instance token, or <code>null</code> if a kaleo instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken fetchByPrimaryKey(long kaleoInstanceTokenId)
		throws SystemException {
		KaleoInstanceToken kaleoInstanceToken = (KaleoInstanceToken)EntityCacheUtil.getResult(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
				KaleoInstanceTokenImpl.class, kaleoInstanceTokenId);

		if (kaleoInstanceToken == _nullKaleoInstanceToken) {
			return null;
		}

		if (kaleoInstanceToken == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				kaleoInstanceToken = (KaleoInstanceToken)session.get(KaleoInstanceTokenImpl.class,
						Long.valueOf(kaleoInstanceTokenId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (kaleoInstanceToken != null) {
					cacheResult(kaleoInstanceToken);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
						KaleoInstanceTokenImpl.class, kaleoInstanceTokenId,
						_nullKaleoInstanceToken);
				}

				closeSession(session);
			}
		}

		return kaleoInstanceToken;
	}

	/**
	 * Returns all the kaleo instance tokens where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching kaleo instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoInstanceToken> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the kaleo instance tokens where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo instance tokens
	 * @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	 * @return the range of matching kaleo instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoInstanceToken> findByCompanyId(long companyId, int start,
		int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo instance tokens where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo instance tokens
	 * @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoInstanceToken> findByCompanyId(long companyId, int start,
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

		List<KaleoInstanceToken> list = (List<KaleoInstanceToken>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoInstanceToken kaleoInstanceToken : list) {
				if ((companyId != kaleoInstanceToken.getCompanyId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<KaleoInstanceToken>)QueryUtil.list(q,
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
	 * Returns the first kaleo instance token in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		KaleoInstanceToken kaleoInstanceToken = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (kaleoInstanceToken != null) {
			return kaleoInstanceToken;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the first kaleo instance token in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken fetchByCompanyId_First(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<KaleoInstanceToken> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo instance token in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		KaleoInstanceToken kaleoInstanceToken = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (kaleoInstanceToken != null) {
			return kaleoInstanceToken;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the last kaleo instance token in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken fetchByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCompanyId(companyId);

		List<KaleoInstanceToken> list = findByCompanyId(companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo instance tokens before and after the current kaleo instance token in the ordered set where companyId = &#63;.
	 *
	 * @param kaleoInstanceTokenId the primary key of the current kaleo instance token
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken[] findByCompanyId_PrevAndNext(
		long kaleoInstanceTokenId, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		KaleoInstanceToken kaleoInstanceToken = findByPrimaryKey(kaleoInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoInstanceToken[] array = new KaleoInstanceTokenImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, kaleoInstanceToken,
					companyId, orderByComparator, true);

			array[1] = kaleoInstanceToken;

			array[2] = getByCompanyId_PrevAndNext(session, kaleoInstanceToken,
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

	protected KaleoInstanceToken getByCompanyId_PrevAndNext(Session session,
		KaleoInstanceToken kaleoInstanceToken, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

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
			query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo instance tokens where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the matching kaleo instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo instance tokens where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo instance tokens
	 * @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	 * @return the range of matching kaleo instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo instance tokens where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo instance tokens
	 * @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoInstanceToken> findByKaleoDefinitionId(
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

		List<KaleoInstanceToken> list = (List<KaleoInstanceToken>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoInstanceToken kaleoInstanceToken : list) {
				if ((kaleoDefinitionId != kaleoInstanceToken.getKaleoDefinitionId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				list = (List<KaleoInstanceToken>)QueryUtil.list(q,
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
	 * Returns the first kaleo instance token in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken findByKaleoDefinitionId_First(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		KaleoInstanceToken kaleoInstanceToken = fetchByKaleoDefinitionId_First(kaleoDefinitionId,
				orderByComparator);

		if (kaleoInstanceToken != null) {
			return kaleoInstanceToken;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoDefinitionId=");
		msg.append(kaleoDefinitionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the first kaleo instance token in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken fetchByKaleoDefinitionId_First(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws SystemException {
		List<KaleoInstanceToken> list = findByKaleoDefinitionId(kaleoDefinitionId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo instance token in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken findByKaleoDefinitionId_Last(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		KaleoInstanceToken kaleoInstanceToken = fetchByKaleoDefinitionId_Last(kaleoDefinitionId,
				orderByComparator);

		if (kaleoInstanceToken != null) {
			return kaleoInstanceToken;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoDefinitionId=");
		msg.append(kaleoDefinitionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the last kaleo instance token in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken fetchByKaleoDefinitionId_Last(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		List<KaleoInstanceToken> list = findByKaleoDefinitionId(kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo instance tokens before and after the current kaleo instance token in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoInstanceTokenId the primary key of the current kaleo instance token
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoInstanceTokenId, long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		KaleoInstanceToken kaleoInstanceToken = findByPrimaryKey(kaleoInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoInstanceToken[] array = new KaleoInstanceTokenImpl[3];

			array[0] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoInstanceToken, kaleoDefinitionId, orderByComparator,
					true);

			array[1] = kaleoInstanceToken;

			array[2] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoInstanceToken, kaleoDefinitionId, orderByComparator,
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

	protected KaleoInstanceToken getByKaleoDefinitionId_PrevAndNext(
		Session session, KaleoInstanceToken kaleoInstanceToken,
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

		query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

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
			query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo instance tokens where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @return the matching kaleo instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoInstanceToken> findByKaleoInstanceId(long kaleoInstanceId)
		throws SystemException {
		return findByKaleoInstanceId(kaleoInstanceId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo instance tokens where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param start the lower bound of the range of kaleo instance tokens
	 * @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	 * @return the range of matching kaleo instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end) throws SystemException {
		return findByKaleoInstanceId(kaleoInstanceId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo instance tokens where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param start the lower bound of the range of kaleo instance tokens
	 * @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoInstanceToken> findByKaleoInstanceId(
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

		List<KaleoInstanceToken> list = (List<KaleoInstanceToken>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoInstanceToken kaleoInstanceToken : list) {
				if ((kaleoInstanceId != kaleoInstanceToken.getKaleoInstanceId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceId);

				list = (List<KaleoInstanceToken>)QueryUtil.list(q,
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
	 * Returns the first kaleo instance token in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken findByKaleoInstanceId_First(
		long kaleoInstanceId, OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		KaleoInstanceToken kaleoInstanceToken = fetchByKaleoInstanceId_First(kaleoInstanceId,
				orderByComparator);

		if (kaleoInstanceToken != null) {
			return kaleoInstanceToken;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoInstanceId=");
		msg.append(kaleoInstanceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the first kaleo instance token in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken fetchByKaleoInstanceId_First(
		long kaleoInstanceId, OrderByComparator orderByComparator)
		throws SystemException {
		List<KaleoInstanceToken> list = findByKaleoInstanceId(kaleoInstanceId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo instance token in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken findByKaleoInstanceId_Last(long kaleoInstanceId,
		OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		KaleoInstanceToken kaleoInstanceToken = fetchByKaleoInstanceId_Last(kaleoInstanceId,
				orderByComparator);

		if (kaleoInstanceToken != null) {
			return kaleoInstanceToken;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoInstanceId=");
		msg.append(kaleoInstanceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the last kaleo instance token in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken fetchByKaleoInstanceId_Last(
		long kaleoInstanceId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByKaleoInstanceId(kaleoInstanceId);

		List<KaleoInstanceToken> list = findByKaleoInstanceId(kaleoInstanceId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo instance tokens before and after the current kaleo instance token in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceTokenId the primary key of the current kaleo instance token
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken[] findByKaleoInstanceId_PrevAndNext(
		long kaleoInstanceTokenId, long kaleoInstanceId,
		OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		KaleoInstanceToken kaleoInstanceToken = findByPrimaryKey(kaleoInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoInstanceToken[] array = new KaleoInstanceTokenImpl[3];

			array[0] = getByKaleoInstanceId_PrevAndNext(session,
					kaleoInstanceToken, kaleoInstanceId, orderByComparator, true);

			array[1] = kaleoInstanceToken;

			array[2] = getByKaleoInstanceId_PrevAndNext(session,
					kaleoInstanceToken, kaleoInstanceId, orderByComparator,
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

	protected KaleoInstanceToken getByKaleoInstanceId_PrevAndNext(
		Session session, KaleoInstanceToken kaleoInstanceToken,
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

		query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

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
			query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoInstanceId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @return the matching kaleo instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoInstanceToken> findByC_PKITI(long companyId,
		long parentKaleoInstanceTokenId) throws SystemException {
		return findByC_PKITI(companyId, parentKaleoInstanceTokenId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param start the lower bound of the range of kaleo instance tokens
	 * @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	 * @return the range of matching kaleo instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoInstanceToken> findByC_PKITI(long companyId,
		long parentKaleoInstanceTokenId, int start, int end)
		throws SystemException {
		return findByC_PKITI(companyId, parentKaleoInstanceTokenId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param start the lower bound of the range of kaleo instance tokens
	 * @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoInstanceToken> findByC_PKITI(long companyId,
		long parentKaleoInstanceTokenId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_PKITI;
			finderArgs = new Object[] { companyId, parentKaleoInstanceTokenId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_PKITI;
			finderArgs = new Object[] {
					companyId, parentKaleoInstanceTokenId,
					
					start, end, orderByComparator
				};
		}

		List<KaleoInstanceToken> list = (List<KaleoInstanceToken>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoInstanceToken kaleoInstanceToken : list) {
				if ((companyId != kaleoInstanceToken.getCompanyId()) ||
						(parentKaleoInstanceTokenId != kaleoInstanceToken.getParentKaleoInstanceTokenId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_C_PKITI_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_PKITI_PARENTKALEOINSTANCETOKENID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(parentKaleoInstanceTokenId);

				list = (List<KaleoInstanceToken>)QueryUtil.list(q,
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
	 * Returns the first kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken findByC_PKITI_First(long companyId,
		long parentKaleoInstanceTokenId, OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		KaleoInstanceToken kaleoInstanceToken = fetchByC_PKITI_First(companyId,
				parentKaleoInstanceTokenId, orderByComparator);

		if (kaleoInstanceToken != null) {
			return kaleoInstanceToken;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", parentKaleoInstanceTokenId=");
		msg.append(parentKaleoInstanceTokenId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the first kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken fetchByC_PKITI_First(long companyId,
		long parentKaleoInstanceTokenId, OrderByComparator orderByComparator)
		throws SystemException {
		List<KaleoInstanceToken> list = findByC_PKITI(companyId,
				parentKaleoInstanceTokenId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken findByC_PKITI_Last(long companyId,
		long parentKaleoInstanceTokenId, OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		KaleoInstanceToken kaleoInstanceToken = fetchByC_PKITI_Last(companyId,
				parentKaleoInstanceTokenId, orderByComparator);

		if (kaleoInstanceToken != null) {
			return kaleoInstanceToken;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", parentKaleoInstanceTokenId=");
		msg.append(parentKaleoInstanceTokenId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the last kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken fetchByC_PKITI_Last(long companyId,
		long parentKaleoInstanceTokenId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByC_PKITI(companyId, parentKaleoInstanceTokenId);

		List<KaleoInstanceToken> list = findByC_PKITI(companyId,
				parentKaleoInstanceTokenId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo instance tokens before and after the current kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	 *
	 * @param kaleoInstanceTokenId the primary key of the current kaleo instance token
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken[] findByC_PKITI_PrevAndNext(
		long kaleoInstanceTokenId, long companyId,
		long parentKaleoInstanceTokenId, OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		KaleoInstanceToken kaleoInstanceToken = findByPrimaryKey(kaleoInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoInstanceToken[] array = new KaleoInstanceTokenImpl[3];

			array[0] = getByC_PKITI_PrevAndNext(session, kaleoInstanceToken,
					companyId, parentKaleoInstanceTokenId, orderByComparator,
					true);

			array[1] = kaleoInstanceToken;

			array[2] = getByC_PKITI_PrevAndNext(session, kaleoInstanceToken,
					companyId, parentKaleoInstanceTokenId, orderByComparator,
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

	protected KaleoInstanceToken getByC_PKITI_PrevAndNext(Session session,
		KaleoInstanceToken kaleoInstanceToken, long companyId,
		long parentKaleoInstanceTokenId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

		query.append(_FINDER_COLUMN_C_PKITI_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_PKITI_PARENTKALEOINSTANCETOKENID_2);

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
			query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(parentKaleoInstanceTokenId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param completionDate the completion date
	 * @return the matching kaleo instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoInstanceToken> findByC_PKITI_CD(long companyId,
		long parentKaleoInstanceTokenId, Date completionDate)
		throws SystemException {
		return findByC_PKITI_CD(companyId, parentKaleoInstanceTokenId,
			completionDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param completionDate the completion date
	 * @param start the lower bound of the range of kaleo instance tokens
	 * @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	 * @return the range of matching kaleo instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoInstanceToken> findByC_PKITI_CD(long companyId,
		long parentKaleoInstanceTokenId, Date completionDate, int start, int end)
		throws SystemException {
		return findByC_PKITI_CD(companyId, parentKaleoInstanceTokenId,
			completionDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param completionDate the completion date
	 * @param start the lower bound of the range of kaleo instance tokens
	 * @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoInstanceToken> findByC_PKITI_CD(long companyId,
		long parentKaleoInstanceTokenId, Date completionDate, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_PKITI_CD;
			finderArgs = new Object[] {
					companyId, parentKaleoInstanceTokenId, completionDate
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_PKITI_CD;
			finderArgs = new Object[] {
					companyId, parentKaleoInstanceTokenId, completionDate,
					
					start, end, orderByComparator
				};
		}

		List<KaleoInstanceToken> list = (List<KaleoInstanceToken>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoInstanceToken kaleoInstanceToken : list) {
				if ((companyId != kaleoInstanceToken.getCompanyId()) ||
						(parentKaleoInstanceTokenId != kaleoInstanceToken.getParentKaleoInstanceTokenId()) ||
						!Validator.equals(completionDate,
							kaleoInstanceToken.getCompletionDate())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_C_PKITI_CD_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_PKITI_CD_PARENTKALEOINSTANCETOKENID_2);

			if (completionDate == null) {
				query.append(_FINDER_COLUMN_C_PKITI_CD_COMPLETIONDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_C_PKITI_CD_COMPLETIONDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(parentKaleoInstanceTokenId);

				if (completionDate != null) {
					qPos.add(CalendarUtil.getTimestamp(completionDate));
				}

				list = (List<KaleoInstanceToken>)QueryUtil.list(q,
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
	 * Returns the first kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param completionDate the completion date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken findByC_PKITI_CD_First(long companyId,
		long parentKaleoInstanceTokenId, Date completionDate,
		OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		KaleoInstanceToken kaleoInstanceToken = fetchByC_PKITI_CD_First(companyId,
				parentKaleoInstanceTokenId, completionDate, orderByComparator);

		if (kaleoInstanceToken != null) {
			return kaleoInstanceToken;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", parentKaleoInstanceTokenId=");
		msg.append(parentKaleoInstanceTokenId);

		msg.append(", completionDate=");
		msg.append(completionDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the first kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param completionDate the completion date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken fetchByC_PKITI_CD_First(long companyId,
		long parentKaleoInstanceTokenId, Date completionDate,
		OrderByComparator orderByComparator) throws SystemException {
		List<KaleoInstanceToken> list = findByC_PKITI_CD(companyId,
				parentKaleoInstanceTokenId, completionDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param completionDate the completion date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken findByC_PKITI_CD_Last(long companyId,
		long parentKaleoInstanceTokenId, Date completionDate,
		OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		KaleoInstanceToken kaleoInstanceToken = fetchByC_PKITI_CD_Last(companyId,
				parentKaleoInstanceTokenId, completionDate, orderByComparator);

		if (kaleoInstanceToken != null) {
			return kaleoInstanceToken;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", parentKaleoInstanceTokenId=");
		msg.append(parentKaleoInstanceTokenId);

		msg.append(", completionDate=");
		msg.append(completionDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the last kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param completionDate the completion date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken fetchByC_PKITI_CD_Last(long companyId,
		long parentKaleoInstanceTokenId, Date completionDate,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_PKITI_CD(companyId, parentKaleoInstanceTokenId,
				completionDate);

		List<KaleoInstanceToken> list = findByC_PKITI_CD(companyId,
				parentKaleoInstanceTokenId, completionDate, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo instance tokens before and after the current kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	 *
	 * @param kaleoInstanceTokenId the primary key of the current kaleo instance token
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param completionDate the completion date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoInstanceToken[] findByC_PKITI_CD_PrevAndNext(
		long kaleoInstanceTokenId, long companyId,
		long parentKaleoInstanceTokenId, Date completionDate,
		OrderByComparator orderByComparator)
		throws NoSuchInstanceTokenException, SystemException {
		KaleoInstanceToken kaleoInstanceToken = findByPrimaryKey(kaleoInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoInstanceToken[] array = new KaleoInstanceTokenImpl[3];

			array[0] = getByC_PKITI_CD_PrevAndNext(session, kaleoInstanceToken,
					companyId, parentKaleoInstanceTokenId, completionDate,
					orderByComparator, true);

			array[1] = kaleoInstanceToken;

			array[2] = getByC_PKITI_CD_PrevAndNext(session, kaleoInstanceToken,
					companyId, parentKaleoInstanceTokenId, completionDate,
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

	protected KaleoInstanceToken getByC_PKITI_CD_PrevAndNext(Session session,
		KaleoInstanceToken kaleoInstanceToken, long companyId,
		long parentKaleoInstanceTokenId, Date completionDate,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

		query.append(_FINDER_COLUMN_C_PKITI_CD_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_PKITI_CD_PARENTKALEOINSTANCETOKENID_2);

		if (completionDate == null) {
			query.append(_FINDER_COLUMN_C_PKITI_CD_COMPLETIONDATE_1);
		}
		else {
			query.append(_FINDER_COLUMN_C_PKITI_CD_COMPLETIONDATE_2);
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
			query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(parentKaleoInstanceTokenId);

		if (completionDate != null) {
			qPos.add(CalendarUtil.getTimestamp(completionDate));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo instance tokens.
	 *
	 * @return the kaleo instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoInstanceToken> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo instance tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo instance tokens
	 * @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	 * @return the range of kaleo instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoInstanceToken> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo instance tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo instance tokens
	 * @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoInstanceToken> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<KaleoInstanceToken> list = (List<KaleoInstanceToken>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KALEOINSTANCETOKEN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEOINSTANCETOKEN.concat(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<KaleoInstanceToken>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KaleoInstanceToken>)QueryUtil.list(q,
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
	 * Removes all the kaleo instance tokens where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (KaleoInstanceToken kaleoInstanceToken : findByCompanyId(companyId)) {
			remove(kaleoInstanceToken);
		}
	}

	/**
	 * Removes all the kaleo instance tokens where kaleoDefinitionId = &#63; from the database.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		for (KaleoInstanceToken kaleoInstanceToken : findByKaleoDefinitionId(
				kaleoDefinitionId)) {
			remove(kaleoInstanceToken);
		}
	}

	/**
	 * Removes all the kaleo instance tokens where kaleoInstanceId = &#63; from the database.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKaleoInstanceId(long kaleoInstanceId)
		throws SystemException {
		for (KaleoInstanceToken kaleoInstanceToken : findByKaleoInstanceId(
				kaleoInstanceId)) {
			remove(kaleoInstanceToken);
		}
	}

	/**
	 * Removes all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_PKITI(long companyId, long parentKaleoInstanceTokenId)
		throws SystemException {
		for (KaleoInstanceToken kaleoInstanceToken : findByC_PKITI(companyId,
				parentKaleoInstanceTokenId)) {
			remove(kaleoInstanceToken);
		}
	}

	/**
	 * Removes all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param completionDate the completion date
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_PKITI_CD(long companyId,
		long parentKaleoInstanceTokenId, Date completionDate)
		throws SystemException {
		for (KaleoInstanceToken kaleoInstanceToken : findByC_PKITI_CD(
				companyId, parentKaleoInstanceTokenId, completionDate)) {
			remove(kaleoInstanceToken);
		}
	}

	/**
	 * Removes all the kaleo instance tokens from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (KaleoInstanceToken kaleoInstanceToken : findAll()) {
			remove(kaleoInstanceToken);
		}
	}

	/**
	 * Returns the number of kaleo instance tokens where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching kaleo instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOINSTANCETOKEN_WHERE);

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
	 * Returns the number of kaleo instance tokens where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the number of matching kaleo instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoDefinitionId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOINSTANCETOKEN_WHERE);

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
	 * Returns the number of kaleo instance tokens where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @return the number of matching kaleo instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKaleoInstanceId(long kaleoInstanceId)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoInstanceId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEOINSTANCEID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOINSTANCETOKEN_WHERE);

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
	 * Returns the number of kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @return the number of matching kaleo instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_PKITI(long companyId, long parentKaleoInstanceTokenId)
		throws SystemException {
		Object[] finderArgs = new Object[] { companyId, parentKaleoInstanceTokenId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_PKITI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEOINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_C_PKITI_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_PKITI_PARENTKALEOINSTANCETOKENID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(parentKaleoInstanceTokenId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_PKITI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param completionDate the completion date
	 * @return the number of matching kaleo instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_PKITI_CD(long companyId,
		long parentKaleoInstanceTokenId, Date completionDate)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId, parentKaleoInstanceTokenId, completionDate
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_PKITI_CD,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KALEOINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_C_PKITI_CD_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_PKITI_CD_PARENTKALEOINSTANCETOKENID_2);

			if (completionDate == null) {
				query.append(_FINDER_COLUMN_C_PKITI_CD_COMPLETIONDATE_1);
			}
			else {
				query.append(_FINDER_COLUMN_C_PKITI_CD_COMPLETIONDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(parentKaleoInstanceTokenId);

				if (completionDate != null) {
					qPos.add(CalendarUtil.getTimestamp(completionDate));
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_PKITI_CD,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of kaleo instance tokens.
	 *
	 * @return the number of kaleo instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KALEOINSTANCETOKEN);

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
	 * Initializes the kaleo instance token persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoInstanceToken>> listenersList = new ArrayList<ModelListener<KaleoInstanceToken>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoInstanceToken>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(KaleoInstanceTokenImpl.class.getName());
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
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_KALEOINSTANCETOKEN = "SELECT kaleoInstanceToken FROM KaleoInstanceToken kaleoInstanceToken";
	private static final String _SQL_SELECT_KALEOINSTANCETOKEN_WHERE = "SELECT kaleoInstanceToken FROM KaleoInstanceToken kaleoInstanceToken WHERE ";
	private static final String _SQL_COUNT_KALEOINSTANCETOKEN = "SELECT COUNT(kaleoInstanceToken) FROM KaleoInstanceToken kaleoInstanceToken";
	private static final String _SQL_COUNT_KALEOINSTANCETOKEN_WHERE = "SELECT COUNT(kaleoInstanceToken) FROM KaleoInstanceToken kaleoInstanceToken WHERE ";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoInstanceToken.companyId = ?";
	private static final String _FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2 =
		"kaleoInstanceToken.kaleoDefinitionId = ?";
	private static final String _FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2 =
		"kaleoInstanceToken.kaleoInstanceId = ?";
	private static final String _FINDER_COLUMN_C_PKITI_COMPANYID_2 = "kaleoInstanceToken.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_PKITI_PARENTKALEOINSTANCETOKENID_2 =
		"kaleoInstanceToken.parentKaleoInstanceTokenId = ?";
	private static final String _FINDER_COLUMN_C_PKITI_CD_COMPANYID_2 = "kaleoInstanceToken.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_PKITI_CD_PARENTKALEOINSTANCETOKENID_2 =
		"kaleoInstanceToken.parentKaleoInstanceTokenId = ? AND ";
	private static final String _FINDER_COLUMN_C_PKITI_CD_COMPLETIONDATE_1 = "kaleoInstanceToken.completionDate IS NULL";
	private static final String _FINDER_COLUMN_C_PKITI_CD_COMPLETIONDATE_2 = "kaleoInstanceToken.completionDate = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoInstanceToken.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoInstanceToken exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoInstanceToken exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(KaleoInstanceTokenPersistenceImpl.class);
	private static KaleoInstanceToken _nullKaleoInstanceToken = new KaleoInstanceTokenImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<KaleoInstanceToken> toCacheModel() {
				return _nullKaleoInstanceTokenCacheModel;
			}
		};

	private static CacheModel<KaleoInstanceToken> _nullKaleoInstanceTokenCacheModel =
		new CacheModel<KaleoInstanceToken>() {
			public KaleoInstanceToken toEntityModel() {
				return _nullKaleoInstanceToken;
			}
		};
}