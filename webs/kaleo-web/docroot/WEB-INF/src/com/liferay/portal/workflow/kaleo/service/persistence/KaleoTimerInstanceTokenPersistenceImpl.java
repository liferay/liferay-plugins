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
import com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException;
import com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerInstanceTokenImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerInstanceTokenModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the kaleo timer instance token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTimerInstanceTokenPersistence
 * @see KaleoTimerInstanceTokenUtil
 * @generated
 */
public class KaleoTimerInstanceTokenPersistenceImpl extends BasePersistenceImpl<KaleoTimerInstanceToken>
	implements KaleoTimerInstanceTokenPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KaleoTimerInstanceTokenUtil} to access the kaleo timer instance token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoTimerInstanceTokenImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_KALEOINSTANCEID = new FinderPath(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTimerInstanceTokenImpl.class, FINDER_CLASS_NAME_LIST,
			"findByKaleoInstanceId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEOINSTANCEID = new FinderPath(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByKaleoInstanceId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_KITI_KTI = new FinderPath(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTimerInstanceTokenImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByKITI_KTI",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_KITI_KTI = new FinderPath(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByKITI_KTI",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KITI_C = new FinderPath(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTimerInstanceTokenImpl.class, FINDER_CLASS_NAME_LIST,
			"findByKITI_C",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KITI_C = new FinderPath(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByKITI_C",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KITI_C_B = new FinderPath(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTimerInstanceTokenImpl.class, FINDER_CLASS_NAME_LIST,
			"findByKITI_C_B",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KITI_C_B = new FinderPath(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByKITI_C_B",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTimerInstanceTokenImpl.class, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the kaleo timer instance token in the entity cache if it is enabled.
	 *
	 * @param kaleoTimerInstanceToken the kaleo timer instance token
	 */
	public void cacheResult(KaleoTimerInstanceToken kaleoTimerInstanceToken) {
		EntityCacheUtil.putResult(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenImpl.class,
			kaleoTimerInstanceToken.getPrimaryKey(), kaleoTimerInstanceToken);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KITI_KTI,
			new Object[] {
				Long.valueOf(kaleoTimerInstanceToken.getKaleoInstanceTokenId()),
				Long.valueOf(kaleoTimerInstanceToken.getKaleoTimerId())
			}, kaleoTimerInstanceToken);

		kaleoTimerInstanceToken.resetOriginalValues();
	}

	/**
	 * Caches the kaleo timer instance tokens in the entity cache if it is enabled.
	 *
	 * @param kaleoTimerInstanceTokens the kaleo timer instance tokens
	 */
	public void cacheResult(
		List<KaleoTimerInstanceToken> kaleoTimerInstanceTokens) {
		for (KaleoTimerInstanceToken kaleoTimerInstanceToken : kaleoTimerInstanceTokens) {
			if (EntityCacheUtil.getResult(
						KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTimerInstanceTokenImpl.class,
						kaleoTimerInstanceToken.getPrimaryKey(), this) == null) {
				cacheResult(kaleoTimerInstanceToken);
			}
		}
	}

	/**
	 * Clears the cache for all kaleo timer instance tokens.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(KaleoTimerInstanceTokenImpl.class.getName());
		}

		EntityCacheUtil.clearCache(KaleoTimerInstanceTokenImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the kaleo timer instance token.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KaleoTimerInstanceToken kaleoTimerInstanceToken) {
		EntityCacheUtil.removeResult(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenImpl.class,
			kaleoTimerInstanceToken.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KITI_KTI,
			new Object[] {
				Long.valueOf(kaleoTimerInstanceToken.getKaleoInstanceTokenId()),
				Long.valueOf(kaleoTimerInstanceToken.getKaleoTimerId())
			});
	}

	/**
	 * Creates a new kaleo timer instance token with the primary key. Does not add the kaleo timer instance token to the database.
	 *
	 * @param kaleoTimerInstanceTokenId the primary key for the new kaleo timer instance token
	 * @return the new kaleo timer instance token
	 */
	public KaleoTimerInstanceToken create(long kaleoTimerInstanceTokenId) {
		KaleoTimerInstanceToken kaleoTimerInstanceToken = new KaleoTimerInstanceTokenImpl();

		kaleoTimerInstanceToken.setNew(true);
		kaleoTimerInstanceToken.setPrimaryKey(kaleoTimerInstanceTokenId);

		return kaleoTimerInstanceToken;
	}

	/**
	 * Removes the kaleo timer instance token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo timer instance token
	 * @return the kaleo timer instance token that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo timer instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTimerInstanceToken remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the kaleo timer instance token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTimerInstanceTokenId the primary key of the kaleo timer instance token
	 * @return the kaleo timer instance token that was removed
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException if a kaleo timer instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTimerInstanceToken remove(long kaleoTimerInstanceTokenId)
		throws NoSuchTimerInstanceTokenException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoTimerInstanceToken kaleoTimerInstanceToken = (KaleoTimerInstanceToken)session.get(KaleoTimerInstanceTokenImpl.class,
					Long.valueOf(kaleoTimerInstanceTokenId));

			if (kaleoTimerInstanceToken == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						kaleoTimerInstanceTokenId);
				}

				throw new NoSuchTimerInstanceTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoTimerInstanceTokenId);
			}

			return kaleoTimerInstanceTokenPersistence.remove(kaleoTimerInstanceToken);
		}
		catch (NoSuchTimerInstanceTokenException nsee) {
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
	 * Removes the kaleo timer instance token from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTimerInstanceToken the kaleo timer instance token
	 * @return the kaleo timer instance token that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTimerInstanceToken remove(
		KaleoTimerInstanceToken kaleoTimerInstanceToken)
		throws SystemException {
		return super.remove(kaleoTimerInstanceToken);
	}

	@Override
	protected KaleoTimerInstanceToken removeImpl(
		KaleoTimerInstanceToken kaleoTimerInstanceToken)
		throws SystemException {
		kaleoTimerInstanceToken = toUnwrappedModel(kaleoTimerInstanceToken);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, kaleoTimerInstanceToken);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		KaleoTimerInstanceTokenModelImpl kaleoTimerInstanceTokenModelImpl = (KaleoTimerInstanceTokenModelImpl)kaleoTimerInstanceToken;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KITI_KTI,
			new Object[] {
				Long.valueOf(
					kaleoTimerInstanceTokenModelImpl.getKaleoInstanceTokenId()),
				Long.valueOf(kaleoTimerInstanceTokenModelImpl.getKaleoTimerId())
			});

		EntityCacheUtil.removeResult(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenImpl.class,
			kaleoTimerInstanceToken.getPrimaryKey());

		return kaleoTimerInstanceToken;
	}

	@Override
	public KaleoTimerInstanceToken updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken kaleoTimerInstanceToken,
		boolean merge) throws SystemException {
		kaleoTimerInstanceToken = toUnwrappedModel(kaleoTimerInstanceToken);

		boolean isNew = kaleoTimerInstanceToken.isNew();

		KaleoTimerInstanceTokenModelImpl kaleoTimerInstanceTokenModelImpl = (KaleoTimerInstanceTokenModelImpl)kaleoTimerInstanceToken;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kaleoTimerInstanceToken, merge);

			kaleoTimerInstanceToken.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerInstanceTokenImpl.class,
			kaleoTimerInstanceToken.getPrimaryKey(), kaleoTimerInstanceToken);

		if (!isNew &&
				((kaleoTimerInstanceToken.getKaleoInstanceTokenId() != kaleoTimerInstanceTokenModelImpl.getOriginalKaleoInstanceTokenId()) ||
				(kaleoTimerInstanceToken.getKaleoTimerId() != kaleoTimerInstanceTokenModelImpl.getOriginalKaleoTimerId()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KITI_KTI,
				new Object[] {
					Long.valueOf(
						kaleoTimerInstanceTokenModelImpl.getOriginalKaleoInstanceTokenId()),
					Long.valueOf(
						kaleoTimerInstanceTokenModelImpl.getOriginalKaleoTimerId())
				});
		}

		if (isNew ||
				((kaleoTimerInstanceToken.getKaleoInstanceTokenId() != kaleoTimerInstanceTokenModelImpl.getOriginalKaleoInstanceTokenId()) ||
				(kaleoTimerInstanceToken.getKaleoTimerId() != kaleoTimerInstanceTokenModelImpl.getOriginalKaleoTimerId()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KITI_KTI,
				new Object[] {
					Long.valueOf(
						kaleoTimerInstanceToken.getKaleoInstanceTokenId()),
					Long.valueOf(kaleoTimerInstanceToken.getKaleoTimerId())
				}, kaleoTimerInstanceToken);
		}

		return kaleoTimerInstanceToken;
	}

	protected KaleoTimerInstanceToken toUnwrappedModel(
		KaleoTimerInstanceToken kaleoTimerInstanceToken) {
		if (kaleoTimerInstanceToken instanceof KaleoTimerInstanceTokenImpl) {
			return kaleoTimerInstanceToken;
		}

		KaleoTimerInstanceTokenImpl kaleoTimerInstanceTokenImpl = new KaleoTimerInstanceTokenImpl();

		kaleoTimerInstanceTokenImpl.setNew(kaleoTimerInstanceToken.isNew());
		kaleoTimerInstanceTokenImpl.setPrimaryKey(kaleoTimerInstanceToken.getPrimaryKey());

		kaleoTimerInstanceTokenImpl.setKaleoTimerInstanceTokenId(kaleoTimerInstanceToken.getKaleoTimerInstanceTokenId());
		kaleoTimerInstanceTokenImpl.setGroupId(kaleoTimerInstanceToken.getGroupId());
		kaleoTimerInstanceTokenImpl.setCompanyId(kaleoTimerInstanceToken.getCompanyId());
		kaleoTimerInstanceTokenImpl.setUserId(kaleoTimerInstanceToken.getUserId());
		kaleoTimerInstanceTokenImpl.setUserName(kaleoTimerInstanceToken.getUserName());
		kaleoTimerInstanceTokenImpl.setCreateDate(kaleoTimerInstanceToken.getCreateDate());
		kaleoTimerInstanceTokenImpl.setModifiedDate(kaleoTimerInstanceToken.getModifiedDate());
		kaleoTimerInstanceTokenImpl.setKaleoClassName(kaleoTimerInstanceToken.getKaleoClassName());
		kaleoTimerInstanceTokenImpl.setKaleoClassPK(kaleoTimerInstanceToken.getKaleoClassPK());
		kaleoTimerInstanceTokenImpl.setKaleoDefinitionId(kaleoTimerInstanceToken.getKaleoDefinitionId());
		kaleoTimerInstanceTokenImpl.setKaleoInstanceId(kaleoTimerInstanceToken.getKaleoInstanceId());
		kaleoTimerInstanceTokenImpl.setKaleoInstanceTokenId(kaleoTimerInstanceToken.getKaleoInstanceTokenId());
		kaleoTimerInstanceTokenImpl.setKaleoTaskInstanceTokenId(kaleoTimerInstanceToken.getKaleoTaskInstanceTokenId());
		kaleoTimerInstanceTokenImpl.setKaleoTimerId(kaleoTimerInstanceToken.getKaleoTimerId());
		kaleoTimerInstanceTokenImpl.setKaleoTimerName(kaleoTimerInstanceToken.getKaleoTimerName());
		kaleoTimerInstanceTokenImpl.setBlocking(kaleoTimerInstanceToken.isBlocking());
		kaleoTimerInstanceTokenImpl.setCompletionUserId(kaleoTimerInstanceToken.getCompletionUserId());
		kaleoTimerInstanceTokenImpl.setCompleted(kaleoTimerInstanceToken.isCompleted());
		kaleoTimerInstanceTokenImpl.setCompletionDate(kaleoTimerInstanceToken.getCompletionDate());
		kaleoTimerInstanceTokenImpl.setWorkflowContext(kaleoTimerInstanceToken.getWorkflowContext());

		return kaleoTimerInstanceTokenImpl;
	}

	/**
	 * Returns the kaleo timer instance token with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo timer instance token
	 * @return the kaleo timer instance token
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo timer instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTimerInstanceToken findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the kaleo timer instance token with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException} if it could not be found.
	 *
	 * @param kaleoTimerInstanceTokenId the primary key of the kaleo timer instance token
	 * @return the kaleo timer instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException if a kaleo timer instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTimerInstanceToken findByPrimaryKey(
		long kaleoTimerInstanceTokenId)
		throws NoSuchTimerInstanceTokenException, SystemException {
		KaleoTimerInstanceToken kaleoTimerInstanceToken = fetchByPrimaryKey(kaleoTimerInstanceTokenId);

		if (kaleoTimerInstanceToken == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoTimerInstanceTokenId);
			}

			throw new NoSuchTimerInstanceTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				kaleoTimerInstanceTokenId);
		}

		return kaleoTimerInstanceToken;
	}

	/**
	 * Returns the kaleo timer instance token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo timer instance token
	 * @return the kaleo timer instance token, or <code>null</code> if a kaleo timer instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTimerInstanceToken fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the kaleo timer instance token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoTimerInstanceTokenId the primary key of the kaleo timer instance token
	 * @return the kaleo timer instance token, or <code>null</code> if a kaleo timer instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTimerInstanceToken fetchByPrimaryKey(
		long kaleoTimerInstanceTokenId) throws SystemException {
		KaleoTimerInstanceToken kaleoTimerInstanceToken = (KaleoTimerInstanceToken)EntityCacheUtil.getResult(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
				KaleoTimerInstanceTokenImpl.class, kaleoTimerInstanceTokenId,
				this);

		if (kaleoTimerInstanceToken == _nullKaleoTimerInstanceToken) {
			return null;
		}

		if (kaleoTimerInstanceToken == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				kaleoTimerInstanceToken = (KaleoTimerInstanceToken)session.get(KaleoTimerInstanceTokenImpl.class,
						Long.valueOf(kaleoTimerInstanceTokenId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (kaleoTimerInstanceToken != null) {
					cacheResult(kaleoTimerInstanceToken);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(KaleoTimerInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTimerInstanceTokenImpl.class,
						kaleoTimerInstanceTokenId, _nullKaleoTimerInstanceToken);
				}

				closeSession(session);
			}
		}

		return kaleoTimerInstanceToken;
	}

	/**
	 * Returns all the kaleo timer instance tokens where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @return the matching kaleo timer instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTimerInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId) throws SystemException {
		return findByKaleoInstanceId(kaleoInstanceId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo timer instance tokens where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param start the lower bound of the range of kaleo timer instance tokens
	 * @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	 * @return the range of matching kaleo timer instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTimerInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end) throws SystemException {
		return findByKaleoInstanceId(kaleoInstanceId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo timer instance tokens where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param start the lower bound of the range of kaleo timer instance tokens
	 * @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo timer instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTimerInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoInstanceId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTimerInstanceToken> list = (List<KaleoTimerInstanceToken>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KALEOINSTANCEID,
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

			query.append(_SQL_SELECT_KALEOTIMERINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoTimerInstanceTokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceId);

				list = (List<KaleoTimerInstanceToken>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_KALEOINSTANCEID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KALEOINSTANCEID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first kaleo timer instance token in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo timer instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException if a matching kaleo timer instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTimerInstanceToken findByKaleoInstanceId_First(
		long kaleoInstanceId, OrderByComparator orderByComparator)
		throws NoSuchTimerInstanceTokenException, SystemException {
		List<KaleoTimerInstanceToken> list = findByKaleoInstanceId(kaleoInstanceId,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceId=");
			msg.append(kaleoInstanceId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTimerInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo timer instance token in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo timer instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException if a matching kaleo timer instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTimerInstanceToken findByKaleoInstanceId_Last(
		long kaleoInstanceId, OrderByComparator orderByComparator)
		throws NoSuchTimerInstanceTokenException, SystemException {
		int count = countByKaleoInstanceId(kaleoInstanceId);

		List<KaleoTimerInstanceToken> list = findByKaleoInstanceId(kaleoInstanceId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceId=");
			msg.append(kaleoInstanceId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTimerInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo timer instance tokens before and after the current kaleo timer instance token in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTimerInstanceTokenId the primary key of the current kaleo timer instance token
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo timer instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException if a kaleo timer instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTimerInstanceToken[] findByKaleoInstanceId_PrevAndNext(
		long kaleoTimerInstanceTokenId, long kaleoInstanceId,
		OrderByComparator orderByComparator)
		throws NoSuchTimerInstanceTokenException, SystemException {
		KaleoTimerInstanceToken kaleoTimerInstanceToken = findByPrimaryKey(kaleoTimerInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoTimerInstanceToken[] array = new KaleoTimerInstanceTokenImpl[3];

			array[0] = getByKaleoInstanceId_PrevAndNext(session,
					kaleoTimerInstanceToken, kaleoInstanceId,
					orderByComparator, true);

			array[1] = kaleoTimerInstanceToken;

			array[2] = getByKaleoInstanceId_PrevAndNext(session,
					kaleoTimerInstanceToken, kaleoInstanceId,
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

	protected KaleoTimerInstanceToken getByKaleoInstanceId_PrevAndNext(
		Session session, KaleoTimerInstanceToken kaleoTimerInstanceToken,
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

		query.append(_SQL_SELECT_KALEOTIMERINSTANCETOKEN_WHERE);

		query.append(_FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2);

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
			query.append(KaleoTimerInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoInstanceId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoTimerInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTimerInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the kaleo timer instance token where kaleoInstanceTokenId = &#63; and kaleoTimerId = &#63; or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException} if it could not be found.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param kaleoTimerId the kaleo timer ID
	 * @return the matching kaleo timer instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException if a matching kaleo timer instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTimerInstanceToken findByKITI_KTI(long kaleoInstanceTokenId,
		long kaleoTimerId)
		throws NoSuchTimerInstanceTokenException, SystemException {
		KaleoTimerInstanceToken kaleoTimerInstanceToken = fetchByKITI_KTI(kaleoInstanceTokenId,
				kaleoTimerId);

		if (kaleoTimerInstanceToken == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceTokenId=");
			msg.append(kaleoInstanceTokenId);

			msg.append(", kaleoTimerId=");
			msg.append(kaleoTimerId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTimerInstanceTokenException(msg.toString());
		}

		return kaleoTimerInstanceToken;
	}

	/**
	 * Returns the kaleo timer instance token where kaleoInstanceTokenId = &#63; and kaleoTimerId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param kaleoTimerId the kaleo timer ID
	 * @return the matching kaleo timer instance token, or <code>null</code> if a matching kaleo timer instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTimerInstanceToken fetchByKITI_KTI(long kaleoInstanceTokenId,
		long kaleoTimerId) throws SystemException {
		return fetchByKITI_KTI(kaleoInstanceTokenId, kaleoTimerId, true);
	}

	/**
	 * Returns the kaleo timer instance token where kaleoInstanceTokenId = &#63; and kaleoTimerId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param kaleoTimerId the kaleo timer ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching kaleo timer instance token, or <code>null</code> if a matching kaleo timer instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTimerInstanceToken fetchByKITI_KTI(long kaleoInstanceTokenId,
		long kaleoTimerId, boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { kaleoInstanceTokenId, kaleoTimerId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_KITI_KTI,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_KALEOTIMERINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_KITI_KTI_KALEOINSTANCETOKENID_2);

			query.append(_FINDER_COLUMN_KITI_KTI_KALEOTIMERID_2);

			query.append(KaleoTimerInstanceTokenModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceTokenId);

				qPos.add(kaleoTimerId);

				List<KaleoTimerInstanceToken> list = q.list();

				result = list;

				KaleoTimerInstanceToken kaleoTimerInstanceToken = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KITI_KTI,
						finderArgs, list);
				}
				else {
					kaleoTimerInstanceToken = list.get(0);

					cacheResult(kaleoTimerInstanceToken);

					if ((kaleoTimerInstanceToken.getKaleoInstanceTokenId() != kaleoInstanceTokenId) ||
							(kaleoTimerInstanceToken.getKaleoTimerId() != kaleoTimerId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KITI_KTI,
							finderArgs, kaleoTimerInstanceToken);
					}
				}

				return kaleoTimerInstanceToken;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KITI_KTI,
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
				return (KaleoTimerInstanceToken)result;
			}
		}
	}

	/**
	 * Returns all the kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @return the matching kaleo timer instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTimerInstanceToken> findByKITI_C(
		long kaleoInstanceTokenId, boolean completed) throws SystemException {
		return findByKITI_C(kaleoInstanceTokenId, completed, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param start the lower bound of the range of kaleo timer instance tokens
	 * @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	 * @return the range of matching kaleo timer instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTimerInstanceToken> findByKITI_C(
		long kaleoInstanceTokenId, boolean completed, int start, int end)
		throws SystemException {
		return findByKITI_C(kaleoInstanceTokenId, completed, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param start the lower bound of the range of kaleo timer instance tokens
	 * @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo timer instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTimerInstanceToken> findByKITI_C(
		long kaleoInstanceTokenId, boolean completed, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoInstanceTokenId, completed,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTimerInstanceToken> list = (List<KaleoTimerInstanceToken>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KITI_C,
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

			query.append(_SQL_SELECT_KALEOTIMERINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_KITI_C_KALEOINSTANCETOKENID_2);

			query.append(_FINDER_COLUMN_KITI_C_COMPLETED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoTimerInstanceTokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceTokenId);

				qPos.add(completed);

				list = (List<KaleoTimerInstanceToken>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_KITI_C,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KITI_C,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first kaleo timer instance token in the ordered set where kaleoInstanceTokenId = &#63; and completed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo timer instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException if a matching kaleo timer instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTimerInstanceToken findByKITI_C_First(
		long kaleoInstanceTokenId, boolean completed,
		OrderByComparator orderByComparator)
		throws NoSuchTimerInstanceTokenException, SystemException {
		List<KaleoTimerInstanceToken> list = findByKITI_C(kaleoInstanceTokenId,
				completed, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceTokenId=");
			msg.append(kaleoInstanceTokenId);

			msg.append(", completed=");
			msg.append(completed);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTimerInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo timer instance token in the ordered set where kaleoInstanceTokenId = &#63; and completed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo timer instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException if a matching kaleo timer instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTimerInstanceToken findByKITI_C_Last(
		long kaleoInstanceTokenId, boolean completed,
		OrderByComparator orderByComparator)
		throws NoSuchTimerInstanceTokenException, SystemException {
		int count = countByKITI_C(kaleoInstanceTokenId, completed);

		List<KaleoTimerInstanceToken> list = findByKITI_C(kaleoInstanceTokenId,
				completed, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceTokenId=");
			msg.append(kaleoInstanceTokenId);

			msg.append(", completed=");
			msg.append(completed);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTimerInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo timer instance tokens before and after the current kaleo timer instance token in the ordered set where kaleoInstanceTokenId = &#63; and completed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTimerInstanceTokenId the primary key of the current kaleo timer instance token
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo timer instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException if a kaleo timer instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTimerInstanceToken[] findByKITI_C_PrevAndNext(
		long kaleoTimerInstanceTokenId, long kaleoInstanceTokenId,
		boolean completed, OrderByComparator orderByComparator)
		throws NoSuchTimerInstanceTokenException, SystemException {
		KaleoTimerInstanceToken kaleoTimerInstanceToken = findByPrimaryKey(kaleoTimerInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoTimerInstanceToken[] array = new KaleoTimerInstanceTokenImpl[3];

			array[0] = getByKITI_C_PrevAndNext(session,
					kaleoTimerInstanceToken, kaleoInstanceTokenId, completed,
					orderByComparator, true);

			array[1] = kaleoTimerInstanceToken;

			array[2] = getByKITI_C_PrevAndNext(session,
					kaleoTimerInstanceToken, kaleoInstanceTokenId, completed,
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

	protected KaleoTimerInstanceToken getByKITI_C_PrevAndNext(Session session,
		KaleoTimerInstanceToken kaleoTimerInstanceToken,
		long kaleoInstanceTokenId, boolean completed,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTIMERINSTANCETOKEN_WHERE);

		query.append(_FINDER_COLUMN_KITI_C_KALEOINSTANCETOKENID_2);

		query.append(_FINDER_COLUMN_KITI_C_COMPLETED_2);

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
			query.append(KaleoTimerInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoInstanceTokenId);

		qPos.add(completed);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoTimerInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTimerInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param blocking the blocking
	 * @return the matching kaleo timer instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTimerInstanceToken> findByKITI_C_B(
		long kaleoInstanceTokenId, boolean completed, boolean blocking)
		throws SystemException {
		return findByKITI_C_B(kaleoInstanceTokenId, completed, blocking,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param blocking the blocking
	 * @param start the lower bound of the range of kaleo timer instance tokens
	 * @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	 * @return the range of matching kaleo timer instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTimerInstanceToken> findByKITI_C_B(
		long kaleoInstanceTokenId, boolean completed, boolean blocking,
		int start, int end) throws SystemException {
		return findByKITI_C_B(kaleoInstanceTokenId, completed, blocking, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param blocking the blocking
	 * @param start the lower bound of the range of kaleo timer instance tokens
	 * @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo timer instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTimerInstanceToken> findByKITI_C_B(
		long kaleoInstanceTokenId, boolean completed, boolean blocking,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoInstanceTokenId, completed, blocking,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTimerInstanceToken> list = (List<KaleoTimerInstanceToken>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KITI_C_B,
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

			query.append(_SQL_SELECT_KALEOTIMERINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_KITI_C_B_KALEOINSTANCETOKENID_2);

			query.append(_FINDER_COLUMN_KITI_C_B_COMPLETED_2);

			query.append(_FINDER_COLUMN_KITI_C_B_BLOCKING_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoTimerInstanceTokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceTokenId);

				qPos.add(completed);

				qPos.add(blocking);

				list = (List<KaleoTimerInstanceToken>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_KITI_C_B,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KITI_C_B,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first kaleo timer instance token in the ordered set where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param blocking the blocking
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo timer instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException if a matching kaleo timer instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTimerInstanceToken findByKITI_C_B_First(
		long kaleoInstanceTokenId, boolean completed, boolean blocking,
		OrderByComparator orderByComparator)
		throws NoSuchTimerInstanceTokenException, SystemException {
		List<KaleoTimerInstanceToken> list = findByKITI_C_B(kaleoInstanceTokenId,
				completed, blocking, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceTokenId=");
			msg.append(kaleoInstanceTokenId);

			msg.append(", completed=");
			msg.append(completed);

			msg.append(", blocking=");
			msg.append(blocking);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTimerInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo timer instance token in the ordered set where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param blocking the blocking
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo timer instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException if a matching kaleo timer instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTimerInstanceToken findByKITI_C_B_Last(
		long kaleoInstanceTokenId, boolean completed, boolean blocking,
		OrderByComparator orderByComparator)
		throws NoSuchTimerInstanceTokenException, SystemException {
		int count = countByKITI_C_B(kaleoInstanceTokenId, completed, blocking);

		List<KaleoTimerInstanceToken> list = findByKITI_C_B(kaleoInstanceTokenId,
				completed, blocking, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceTokenId=");
			msg.append(kaleoInstanceTokenId);

			msg.append(", completed=");
			msg.append(completed);

			msg.append(", blocking=");
			msg.append(blocking);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTimerInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo timer instance tokens before and after the current kaleo timer instance token in the ordered set where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTimerInstanceTokenId the primary key of the current kaleo timer instance token
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param blocking the blocking
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo timer instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTimerInstanceTokenException if a kaleo timer instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTimerInstanceToken[] findByKITI_C_B_PrevAndNext(
		long kaleoTimerInstanceTokenId, long kaleoInstanceTokenId,
		boolean completed, boolean blocking, OrderByComparator orderByComparator)
		throws NoSuchTimerInstanceTokenException, SystemException {
		KaleoTimerInstanceToken kaleoTimerInstanceToken = findByPrimaryKey(kaleoTimerInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoTimerInstanceToken[] array = new KaleoTimerInstanceTokenImpl[3];

			array[0] = getByKITI_C_B_PrevAndNext(session,
					kaleoTimerInstanceToken, kaleoInstanceTokenId, completed,
					blocking, orderByComparator, true);

			array[1] = kaleoTimerInstanceToken;

			array[2] = getByKITI_C_B_PrevAndNext(session,
					kaleoTimerInstanceToken, kaleoInstanceTokenId, completed,
					blocking, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTimerInstanceToken getByKITI_C_B_PrevAndNext(
		Session session, KaleoTimerInstanceToken kaleoTimerInstanceToken,
		long kaleoInstanceTokenId, boolean completed, boolean blocking,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTIMERINSTANCETOKEN_WHERE);

		query.append(_FINDER_COLUMN_KITI_C_B_KALEOINSTANCETOKENID_2);

		query.append(_FINDER_COLUMN_KITI_C_B_COMPLETED_2);

		query.append(_FINDER_COLUMN_KITI_C_B_BLOCKING_2);

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
			query.append(KaleoTimerInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoInstanceTokenId);

		qPos.add(completed);

		qPos.add(blocking);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoTimerInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTimerInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo timer instance tokens.
	 *
	 * @return the kaleo timer instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTimerInstanceToken> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo timer instance tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo timer instance tokens
	 * @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	 * @return the range of kaleo timer instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTimerInstanceToken> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo timer instance tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo timer instance tokens
	 * @param end the upper bound of the range of kaleo timer instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo timer instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTimerInstanceToken> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTimerInstanceToken> list = (List<KaleoTimerInstanceToken>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KALEOTIMERINSTANCETOKEN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEOTIMERINSTANCETOKEN.concat(KaleoTimerInstanceTokenModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<KaleoTimerInstanceToken>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KaleoTimerInstanceToken>)QueryUtil.list(q,
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
	 * Removes all the kaleo timer instance tokens where kaleoInstanceId = &#63; from the database.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKaleoInstanceId(long kaleoInstanceId)
		throws SystemException {
		for (KaleoTimerInstanceToken kaleoTimerInstanceToken : findByKaleoInstanceId(
				kaleoInstanceId)) {
			kaleoTimerInstanceTokenPersistence.remove(kaleoTimerInstanceToken);
		}
	}

	/**
	 * Removes the kaleo timer instance token where kaleoInstanceTokenId = &#63; and kaleoTimerId = &#63; from the database.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param kaleoTimerId the kaleo timer ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKITI_KTI(long kaleoInstanceTokenId, long kaleoTimerId)
		throws NoSuchTimerInstanceTokenException, SystemException {
		KaleoTimerInstanceToken kaleoTimerInstanceToken = findByKITI_KTI(kaleoInstanceTokenId,
				kaleoTimerId);

		kaleoTimerInstanceTokenPersistence.remove(kaleoTimerInstanceToken);
	}

	/**
	 * Removes all the kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63; from the database.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKITI_C(long kaleoInstanceTokenId, boolean completed)
		throws SystemException {
		for (KaleoTimerInstanceToken kaleoTimerInstanceToken : findByKITI_C(
				kaleoInstanceTokenId, completed)) {
			kaleoTimerInstanceTokenPersistence.remove(kaleoTimerInstanceToken);
		}
	}

	/**
	 * Removes all the kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63; from the database.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param blocking the blocking
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKITI_C_B(long kaleoInstanceTokenId, boolean completed,
		boolean blocking) throws SystemException {
		for (KaleoTimerInstanceToken kaleoTimerInstanceToken : findByKITI_C_B(
				kaleoInstanceTokenId, completed, blocking)) {
			kaleoTimerInstanceTokenPersistence.remove(kaleoTimerInstanceToken);
		}
	}

	/**
	 * Removes all the kaleo timer instance tokens from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (KaleoTimerInstanceToken kaleoTimerInstanceToken : findAll()) {
			kaleoTimerInstanceTokenPersistence.remove(kaleoTimerInstanceToken);
		}
	}

	/**
	 * Returns the number of kaleo timer instance tokens where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @return the number of matching kaleo timer instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKaleoInstanceId(long kaleoInstanceId)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoInstanceId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEOINSTANCEID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTIMERINSTANCETOKEN_WHERE);

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
	 * Returns the number of kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and kaleoTimerId = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param kaleoTimerId the kaleo timer ID
	 * @return the number of matching kaleo timer instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKITI_KTI(long kaleoInstanceTokenId, long kaleoTimerId)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoInstanceTokenId, kaleoTimerId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KITI_KTI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEOTIMERINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_KITI_KTI_KALEOINSTANCETOKENID_2);

			query.append(_FINDER_COLUMN_KITI_KTI_KALEOTIMERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceTokenId);

				qPos.add(kaleoTimerId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KITI_KTI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @return the number of matching kaleo timer instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKITI_C(long kaleoInstanceTokenId, boolean completed)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoInstanceTokenId, completed };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KITI_C,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEOTIMERINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_KITI_C_KALEOINSTANCETOKENID_2);

			query.append(_FINDER_COLUMN_KITI_C_COMPLETED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceTokenId);

				qPos.add(completed);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KITI_C,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of kaleo timer instance tokens where kaleoInstanceTokenId = &#63; and completed = &#63; and blocking = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param completed the completed
	 * @param blocking the blocking
	 * @return the number of matching kaleo timer instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKITI_C_B(long kaleoInstanceTokenId, boolean completed,
		boolean blocking) throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoInstanceTokenId, completed, blocking
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KITI_C_B,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KALEOTIMERINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_KITI_C_B_KALEOINSTANCETOKENID_2);

			query.append(_FINDER_COLUMN_KITI_C_B_COMPLETED_2);

			query.append(_FINDER_COLUMN_KITI_C_B_BLOCKING_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceTokenId);

				qPos.add(completed);

				qPos.add(blocking);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KITI_C_B,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of kaleo timer instance tokens.
	 *
	 * @return the number of kaleo timer instance tokens
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

				Query q = session.createQuery(_SQL_COUNT_KALEOTIMERINSTANCETOKEN);

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
	 * Initializes the kaleo timer instance token persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoTimerInstanceToken>> listenersList = new ArrayList<ModelListener<KaleoTimerInstanceToken>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoTimerInstanceToken>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(KaleoTimerInstanceTokenImpl.class.getName());
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
	@BeanReference(type = KaleoTaskFormPersistence.class)
	protected KaleoTaskFormPersistence kaleoTaskFormPersistence;
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
	private static final String _SQL_SELECT_KALEOTIMERINSTANCETOKEN = "SELECT kaleoTimerInstanceToken FROM KaleoTimerInstanceToken kaleoTimerInstanceToken";
	private static final String _SQL_SELECT_KALEOTIMERINSTANCETOKEN_WHERE = "SELECT kaleoTimerInstanceToken FROM KaleoTimerInstanceToken kaleoTimerInstanceToken WHERE ";
	private static final String _SQL_COUNT_KALEOTIMERINSTANCETOKEN = "SELECT COUNT(kaleoTimerInstanceToken) FROM KaleoTimerInstanceToken kaleoTimerInstanceToken";
	private static final String _SQL_COUNT_KALEOTIMERINSTANCETOKEN_WHERE = "SELECT COUNT(kaleoTimerInstanceToken) FROM KaleoTimerInstanceToken kaleoTimerInstanceToken WHERE ";
	private static final String _FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2 =
		"kaleoTimerInstanceToken.kaleoInstanceId = ?";
	private static final String _FINDER_COLUMN_KITI_KTI_KALEOINSTANCETOKENID_2 = "kaleoTimerInstanceToken.kaleoInstanceTokenId = ? AND ";
	private static final String _FINDER_COLUMN_KITI_KTI_KALEOTIMERID_2 = "kaleoTimerInstanceToken.kaleoTimerId = ?";
	private static final String _FINDER_COLUMN_KITI_C_KALEOINSTANCETOKENID_2 = "kaleoTimerInstanceToken.kaleoInstanceTokenId = ? AND ";
	private static final String _FINDER_COLUMN_KITI_C_COMPLETED_2 = "kaleoTimerInstanceToken.completed = ?";
	private static final String _FINDER_COLUMN_KITI_C_B_KALEOINSTANCETOKENID_2 = "kaleoTimerInstanceToken.kaleoInstanceTokenId = ? AND ";
	private static final String _FINDER_COLUMN_KITI_C_B_COMPLETED_2 = "kaleoTimerInstanceToken.completed = ? AND ";
	private static final String _FINDER_COLUMN_KITI_C_B_BLOCKING_2 = "kaleoTimerInstanceToken.blocking = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoTimerInstanceToken.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoTimerInstanceToken exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoTimerInstanceToken exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(KaleoTimerInstanceTokenPersistenceImpl.class);
	private static KaleoTimerInstanceToken _nullKaleoTimerInstanceToken = new KaleoTimerInstanceTokenImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<KaleoTimerInstanceToken> toCacheModel() {
				return _nullKaleoTimerInstanceTokenCacheModel;
			}
		};

	private static CacheModel<KaleoTimerInstanceToken> _nullKaleoTimerInstanceTokenCacheModel =
		new CacheModel<KaleoTimerInstanceToken>() {
			public KaleoTimerInstanceToken toEntityModel() {
				return _nullKaleoTimerInstanceToken;
			}
		};
}