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
import com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the kaleo task instance token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskInstanceTokenPersistence
 * @see KaleoTaskInstanceTokenUtil
 * @generated
 */
public class KaleoTaskInstanceTokenPersistenceImpl extends BasePersistenceImpl<KaleoTaskInstanceToken>
	implements KaleoTaskInstanceTokenPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KaleoTaskInstanceTokenUtil} to access the kaleo task instance token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoTaskInstanceTokenImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskInstanceTokenImpl.class, FINDER_CLASS_NAME_LIST,
			"findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KALEODEFINITIONID = new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskInstanceTokenImpl.class, FINDER_CLASS_NAME_LIST,
			"findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEODEFINITIONID = new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByKaleoDefinitionId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KALEOINSTANCEID = new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskInstanceTokenImpl.class, FINDER_CLASS_NAME_LIST,
			"findByKaleoInstanceId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEOINSTANCEID = new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByKaleoInstanceId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_KII_KTI = new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskInstanceTokenImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByKII_KTI",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_KII_KTI = new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByKII_KTI",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskInstanceTokenImpl.class, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the kaleo task instance token in the entity cache if it is enabled.
	 *
	 * @param kaleoTaskInstanceToken the kaleo task instance token
	 */
	public void cacheResult(KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		EntityCacheUtil.putResult(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenImpl.class,
			kaleoTaskInstanceToken.getPrimaryKey(), kaleoTaskInstanceToken);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KII_KTI,
			new Object[] {
				Long.valueOf(kaleoTaskInstanceToken.getKaleoInstanceId()),
				Long.valueOf(kaleoTaskInstanceToken.getKaleoTaskId())
			}, kaleoTaskInstanceToken);

		kaleoTaskInstanceToken.resetOriginalValues();
	}

	/**
	 * Caches the kaleo task instance tokens in the entity cache if it is enabled.
	 *
	 * @param kaleoTaskInstanceTokens the kaleo task instance tokens
	 */
	public void cacheResult(
		List<KaleoTaskInstanceToken> kaleoTaskInstanceTokens) {
		for (KaleoTaskInstanceToken kaleoTaskInstanceToken : kaleoTaskInstanceTokens) {
			if (EntityCacheUtil.getResult(
						KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTaskInstanceTokenImpl.class,
						kaleoTaskInstanceToken.getPrimaryKey(), this) == null) {
				cacheResult(kaleoTaskInstanceToken);
			}
		}
	}

	/**
	 * Clears the cache for all kaleo task instance tokens.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(KaleoTaskInstanceTokenImpl.class.getName());
		}

		EntityCacheUtil.clearCache(KaleoTaskInstanceTokenImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the kaleo task instance token.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		EntityCacheUtil.removeResult(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenImpl.class,
			kaleoTaskInstanceToken.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KII_KTI,
			new Object[] {
				Long.valueOf(kaleoTaskInstanceToken.getKaleoInstanceId()),
				Long.valueOf(kaleoTaskInstanceToken.getKaleoTaskId())
			});
	}

	/**
	 * Creates a new kaleo task instance token with the primary key. Does not add the kaleo task instance token to the database.
	 *
	 * @param kaleoTaskInstanceTokenId the primary key for the new kaleo task instance token
	 * @return the new kaleo task instance token
	 */
	public KaleoTaskInstanceToken create(long kaleoTaskInstanceTokenId) {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = new KaleoTaskInstanceTokenImpl();

		kaleoTaskInstanceToken.setNew(true);
		kaleoTaskInstanceToken.setPrimaryKey(kaleoTaskInstanceTokenId);

		return kaleoTaskInstanceToken;
	}

	/**
	 * Removes the kaleo task instance token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo task instance token
	 * @return the kaleo task instance token that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo task instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the kaleo task instance token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTaskInstanceTokenId the primary key of the kaleo task instance token
	 * @return the kaleo task instance token that was removed
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a kaleo task instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskInstanceToken remove(long kaleoTaskInstanceTokenId)
		throws NoSuchTaskInstanceTokenException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoTaskInstanceToken kaleoTaskInstanceToken = (KaleoTaskInstanceToken)session.get(KaleoTaskInstanceTokenImpl.class,
					Long.valueOf(kaleoTaskInstanceTokenId));

			if (kaleoTaskInstanceToken == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						kaleoTaskInstanceTokenId);
				}

				throw new NoSuchTaskInstanceTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoTaskInstanceTokenId);
			}

			return kaleoTaskInstanceTokenPersistence.remove(kaleoTaskInstanceToken);
		}
		catch (NoSuchTaskInstanceTokenException nsee) {
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
	 * Removes the kaleo task instance token from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTaskInstanceToken the kaleo task instance token
	 * @return the kaleo task instance token that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken remove(
		KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws SystemException {
		return super.remove(kaleoTaskInstanceToken);
	}

	@Override
	protected KaleoTaskInstanceToken removeImpl(
		KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws SystemException {
		kaleoTaskInstanceToken = toUnwrappedModel(kaleoTaskInstanceToken);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, kaleoTaskInstanceToken);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		KaleoTaskInstanceTokenModelImpl kaleoTaskInstanceTokenModelImpl = (KaleoTaskInstanceTokenModelImpl)kaleoTaskInstanceToken;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KII_KTI,
			new Object[] {
				Long.valueOf(
					kaleoTaskInstanceTokenModelImpl.getKaleoInstanceId()),
				Long.valueOf(kaleoTaskInstanceTokenModelImpl.getKaleoTaskId())
			});

		EntityCacheUtil.removeResult(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenImpl.class,
			kaleoTaskInstanceToken.getPrimaryKey());

		return kaleoTaskInstanceToken;
	}

	@Override
	public KaleoTaskInstanceToken updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		boolean merge) throws SystemException {
		kaleoTaskInstanceToken = toUnwrappedModel(kaleoTaskInstanceToken);

		boolean isNew = kaleoTaskInstanceToken.isNew();

		KaleoTaskInstanceTokenModelImpl kaleoTaskInstanceTokenModelImpl = (KaleoTaskInstanceTokenModelImpl)kaleoTaskInstanceToken;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kaleoTaskInstanceToken, merge);

			kaleoTaskInstanceToken.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenImpl.class,
			kaleoTaskInstanceToken.getPrimaryKey(), kaleoTaskInstanceToken);

		if (!isNew &&
				((kaleoTaskInstanceToken.getKaleoInstanceId() != kaleoTaskInstanceTokenModelImpl.getOriginalKaleoInstanceId()) ||
				(kaleoTaskInstanceToken.getKaleoTaskId() != kaleoTaskInstanceTokenModelImpl.getOriginalKaleoTaskId()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KII_KTI,
				new Object[] {
					Long.valueOf(
						kaleoTaskInstanceTokenModelImpl.getOriginalKaleoInstanceId()),
					Long.valueOf(
						kaleoTaskInstanceTokenModelImpl.getOriginalKaleoTaskId())
				});
		}

		if (isNew ||
				((kaleoTaskInstanceToken.getKaleoInstanceId() != kaleoTaskInstanceTokenModelImpl.getOriginalKaleoInstanceId()) ||
				(kaleoTaskInstanceToken.getKaleoTaskId() != kaleoTaskInstanceTokenModelImpl.getOriginalKaleoTaskId()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KII_KTI,
				new Object[] {
					Long.valueOf(kaleoTaskInstanceToken.getKaleoInstanceId()),
					Long.valueOf(kaleoTaskInstanceToken.getKaleoTaskId())
				}, kaleoTaskInstanceToken);
		}

		return kaleoTaskInstanceToken;
	}

	protected KaleoTaskInstanceToken toUnwrappedModel(
		KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		if (kaleoTaskInstanceToken instanceof KaleoTaskInstanceTokenImpl) {
			return kaleoTaskInstanceToken;
		}

		KaleoTaskInstanceTokenImpl kaleoTaskInstanceTokenImpl = new KaleoTaskInstanceTokenImpl();

		kaleoTaskInstanceTokenImpl.setNew(kaleoTaskInstanceToken.isNew());
		kaleoTaskInstanceTokenImpl.setPrimaryKey(kaleoTaskInstanceToken.getPrimaryKey());

		kaleoTaskInstanceTokenImpl.setKaleoTaskInstanceTokenId(kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId());
		kaleoTaskInstanceTokenImpl.setGroupId(kaleoTaskInstanceToken.getGroupId());
		kaleoTaskInstanceTokenImpl.setCompanyId(kaleoTaskInstanceToken.getCompanyId());
		kaleoTaskInstanceTokenImpl.setUserId(kaleoTaskInstanceToken.getUserId());
		kaleoTaskInstanceTokenImpl.setUserName(kaleoTaskInstanceToken.getUserName());
		kaleoTaskInstanceTokenImpl.setCreateDate(kaleoTaskInstanceToken.getCreateDate());
		kaleoTaskInstanceTokenImpl.setModifiedDate(kaleoTaskInstanceToken.getModifiedDate());
		kaleoTaskInstanceTokenImpl.setKaleoDefinitionId(kaleoTaskInstanceToken.getKaleoDefinitionId());
		kaleoTaskInstanceTokenImpl.setKaleoInstanceId(kaleoTaskInstanceToken.getKaleoInstanceId());
		kaleoTaskInstanceTokenImpl.setKaleoInstanceTokenId(kaleoTaskInstanceToken.getKaleoInstanceTokenId());
		kaleoTaskInstanceTokenImpl.setKaleoTaskId(kaleoTaskInstanceToken.getKaleoTaskId());
		kaleoTaskInstanceTokenImpl.setKaleoTaskName(kaleoTaskInstanceToken.getKaleoTaskName());
		kaleoTaskInstanceTokenImpl.setClassName(kaleoTaskInstanceToken.getClassName());
		kaleoTaskInstanceTokenImpl.setClassPK(kaleoTaskInstanceToken.getClassPK());
		kaleoTaskInstanceTokenImpl.setCompletionUserId(kaleoTaskInstanceToken.getCompletionUserId());
		kaleoTaskInstanceTokenImpl.setCompleted(kaleoTaskInstanceToken.isCompleted());
		kaleoTaskInstanceTokenImpl.setCompletionDate(kaleoTaskInstanceToken.getCompletionDate());
		kaleoTaskInstanceTokenImpl.setDueDate(kaleoTaskInstanceToken.getDueDate());
		kaleoTaskInstanceTokenImpl.setWorkflowContext(kaleoTaskInstanceToken.getWorkflowContext());

		return kaleoTaskInstanceTokenImpl;
	}

	/**
	 * Returns the kaleo task instance token with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo task instance token
	 * @return the kaleo task instance token
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo task instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the kaleo task instance token with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException} if it could not be found.
	 *
	 * @param kaleoTaskInstanceTokenId the primary key of the kaleo task instance token
	 * @return the kaleo task instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a kaleo task instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskInstanceToken findByPrimaryKey(
		long kaleoTaskInstanceTokenId)
		throws NoSuchTaskInstanceTokenException, SystemException {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = fetchByPrimaryKey(kaleoTaskInstanceTokenId);

		if (kaleoTaskInstanceToken == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoTaskInstanceTokenId);
			}

			throw new NoSuchTaskInstanceTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				kaleoTaskInstanceTokenId);
		}

		return kaleoTaskInstanceToken;
	}

	/**
	 * Returns the kaleo task instance token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo task instance token
	 * @return the kaleo task instance token, or <code>null</code> if a kaleo task instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the kaleo task instance token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoTaskInstanceTokenId the primary key of the kaleo task instance token
	 * @return the kaleo task instance token, or <code>null</code> if a kaleo task instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskInstanceToken fetchByPrimaryKey(
		long kaleoTaskInstanceTokenId) throws SystemException {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = (KaleoTaskInstanceToken)EntityCacheUtil.getResult(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
				KaleoTaskInstanceTokenImpl.class, kaleoTaskInstanceTokenId, this);

		if (kaleoTaskInstanceToken == _nullKaleoTaskInstanceToken) {
			return null;
		}

		if (kaleoTaskInstanceToken == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				kaleoTaskInstanceToken = (KaleoTaskInstanceToken)session.get(KaleoTaskInstanceTokenImpl.class,
						Long.valueOf(kaleoTaskInstanceTokenId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (kaleoTaskInstanceToken != null) {
					cacheResult(kaleoTaskInstanceToken);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTaskInstanceTokenImpl.class,
						kaleoTaskInstanceTokenId, _nullKaleoTaskInstanceToken);
				}

				closeSession(session);
			}
		}

		return kaleoTaskInstanceToken;
	}

	/**
	 * Returns all the kaleo task instance tokens where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskInstanceToken> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the kaleo task instance tokens where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo task instance tokens
	 * @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	 * @return the range of matching kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskInstanceToken> findByCompanyId(long companyId,
		int start, int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo task instance tokens where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo task instance tokens
	 * @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskInstanceToken> findByCompanyId(long companyId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTaskInstanceToken> list = (List<KaleoTaskInstanceToken>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
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

			query.append(_SQL_SELECT_KALEOTASKINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoTaskInstanceTokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<KaleoTaskInstanceToken>)QueryUtil.list(q,
						getDialect(), start, end);
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
	 * Returns the first kaleo task instance token in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskInstanceToken findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskInstanceTokenException, SystemException {
		List<KaleoTaskInstanceToken> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo task instance token in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskInstanceToken findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskInstanceTokenException, SystemException {
		int count = countByCompanyId(companyId);

		List<KaleoTaskInstanceToken> list = findByCompanyId(companyId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo task instance tokens before and after the current kaleo task instance token in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskInstanceTokenId the primary key of the current kaleo task instance token
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo task instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a kaleo task instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskInstanceToken[] findByCompanyId_PrevAndNext(
		long kaleoTaskInstanceTokenId, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskInstanceTokenException, SystemException {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = findByPrimaryKey(kaleoTaskInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskInstanceToken[] array = new KaleoTaskInstanceTokenImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session,
					kaleoTaskInstanceToken, companyId, orderByComparator, true);

			array[1] = kaleoTaskInstanceToken;

			array[2] = getByCompanyId_PrevAndNext(session,
					kaleoTaskInstanceToken, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTaskInstanceToken getByCompanyId_PrevAndNext(
		Session session, KaleoTaskInstanceToken kaleoTaskInstanceToken,
		long companyId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASKINSTANCETOKEN_WHERE);

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
			query.append(KaleoTaskInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoTaskInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo task instance tokens where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the matching kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo task instance tokens where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo task instance tokens
	 * @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	 * @return the range of matching kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo task instance tokens where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo task instance tokens
	 * @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoDefinitionId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTaskInstanceToken> list = (List<KaleoTaskInstanceToken>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
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

			query.append(_SQL_SELECT_KALEOTASKINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoTaskInstanceTokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				list = (List<KaleoTaskInstanceToken>)QueryUtil.list(q,
						getDialect(), start, end);
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
	 * Returns the first kaleo task instance token in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskInstanceToken findByKaleoDefinitionId_First(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchTaskInstanceTokenException, SystemException {
		List<KaleoTaskInstanceToken> list = findByKaleoDefinitionId(kaleoDefinitionId,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo task instance token in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskInstanceToken findByKaleoDefinitionId_Last(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchTaskInstanceTokenException, SystemException {
		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		List<KaleoTaskInstanceToken> list = findByKaleoDefinitionId(kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo task instance tokens before and after the current kaleo task instance token in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskInstanceTokenId the primary key of the current kaleo task instance token
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo task instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a kaleo task instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskInstanceToken[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoTaskInstanceTokenId, long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskInstanceTokenException, SystemException {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = findByPrimaryKey(kaleoTaskInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskInstanceToken[] array = new KaleoTaskInstanceTokenImpl[3];

			array[0] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoTaskInstanceToken, kaleoDefinitionId,
					orderByComparator, true);

			array[1] = kaleoTaskInstanceToken;

			array[2] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoTaskInstanceToken, kaleoDefinitionId,
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

	protected KaleoTaskInstanceToken getByKaleoDefinitionId_PrevAndNext(
		Session session, KaleoTaskInstanceToken kaleoTaskInstanceToken,
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

		query.append(_SQL_SELECT_KALEOTASKINSTANCETOKEN_WHERE);

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
			query.append(KaleoTaskInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoTaskInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo task instance tokens where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @return the matching kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId) throws SystemException {
		return findByKaleoInstanceId(kaleoInstanceId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo task instance tokens where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param start the lower bound of the range of kaleo task instance tokens
	 * @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	 * @return the range of matching kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end) throws SystemException {
		return findByKaleoInstanceId(kaleoInstanceId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo task instance tokens where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param start the lower bound of the range of kaleo task instance tokens
	 * @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoInstanceId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTaskInstanceToken> list = (List<KaleoTaskInstanceToken>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KALEOINSTANCEID,
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

			query.append(_SQL_SELECT_KALEOTASKINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoTaskInstanceTokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceId);

				list = (List<KaleoTaskInstanceToken>)QueryUtil.list(q,
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
	 * Returns the first kaleo task instance token in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskInstanceToken findByKaleoInstanceId_First(
		long kaleoInstanceId, OrderByComparator orderByComparator)
		throws NoSuchTaskInstanceTokenException, SystemException {
		List<KaleoTaskInstanceToken> list = findByKaleoInstanceId(kaleoInstanceId,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceId=");
			msg.append(kaleoInstanceId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo task instance token in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskInstanceToken findByKaleoInstanceId_Last(
		long kaleoInstanceId, OrderByComparator orderByComparator)
		throws NoSuchTaskInstanceTokenException, SystemException {
		int count = countByKaleoInstanceId(kaleoInstanceId);

		List<KaleoTaskInstanceToken> list = findByKaleoInstanceId(kaleoInstanceId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceId=");
			msg.append(kaleoInstanceId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskInstanceTokenException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo task instance tokens before and after the current kaleo task instance token in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskInstanceTokenId the primary key of the current kaleo task instance token
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo task instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a kaleo task instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskInstanceToken[] findByKaleoInstanceId_PrevAndNext(
		long kaleoTaskInstanceTokenId, long kaleoInstanceId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskInstanceTokenException, SystemException {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = findByPrimaryKey(kaleoTaskInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskInstanceToken[] array = new KaleoTaskInstanceTokenImpl[3];

			array[0] = getByKaleoInstanceId_PrevAndNext(session,
					kaleoTaskInstanceToken, kaleoInstanceId, orderByComparator,
					true);

			array[1] = kaleoTaskInstanceToken;

			array[2] = getByKaleoInstanceId_PrevAndNext(session,
					kaleoTaskInstanceToken, kaleoInstanceId, orderByComparator,
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

	protected KaleoTaskInstanceToken getByKaleoInstanceId_PrevAndNext(
		Session session, KaleoTaskInstanceToken kaleoTaskInstanceToken,
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

		query.append(_SQL_SELECT_KALEOTASKINSTANCETOKEN_WHERE);

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
			query.append(KaleoTaskInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoInstanceId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoTaskInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the kaleo task instance token where kaleoInstanceId = &#63; and kaleoTaskId = &#63; or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException} if it could not be found.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param kaleoTaskId the kaleo task ID
	 * @return the matching kaleo task instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskInstanceToken findByKII_KTI(long kaleoInstanceId,
		long kaleoTaskId)
		throws NoSuchTaskInstanceTokenException, SystemException {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = fetchByKII_KTI(kaleoInstanceId,
				kaleoTaskId);

		if (kaleoTaskInstanceToken == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceId=");
			msg.append(kaleoInstanceId);

			msg.append(", kaleoTaskId=");
			msg.append(kaleoTaskId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTaskInstanceTokenException(msg.toString());
		}

		return kaleoTaskInstanceToken;
	}

	/**
	 * Returns the kaleo task instance token where kaleoInstanceId = &#63; and kaleoTaskId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param kaleoTaskId the kaleo task ID
	 * @return the matching kaleo task instance token, or <code>null</code> if a matching kaleo task instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskInstanceToken fetchByKII_KTI(long kaleoInstanceId,
		long kaleoTaskId) throws SystemException {
		return fetchByKII_KTI(kaleoInstanceId, kaleoTaskId, true);
	}

	/**
	 * Returns the kaleo task instance token where kaleoInstanceId = &#63; and kaleoTaskId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param kaleoTaskId the kaleo task ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching kaleo task instance token, or <code>null</code> if a matching kaleo task instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskInstanceToken fetchByKII_KTI(long kaleoInstanceId,
		long kaleoTaskId, boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { kaleoInstanceId, kaleoTaskId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_KII_KTI,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_KALEOTASKINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_KII_KTI_KALEOINSTANCEID_2);

			query.append(_FINDER_COLUMN_KII_KTI_KALEOTASKID_2);

			query.append(KaleoTaskInstanceTokenModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceId);

				qPos.add(kaleoTaskId);

				List<KaleoTaskInstanceToken> list = q.list();

				result = list;

				KaleoTaskInstanceToken kaleoTaskInstanceToken = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KII_KTI,
						finderArgs, list);
				}
				else {
					kaleoTaskInstanceToken = list.get(0);

					cacheResult(kaleoTaskInstanceToken);

					if ((kaleoTaskInstanceToken.getKaleoInstanceId() != kaleoInstanceId) ||
							(kaleoTaskInstanceToken.getKaleoTaskId() != kaleoTaskId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KII_KTI,
							finderArgs, kaleoTaskInstanceToken);
					}
				}

				return kaleoTaskInstanceToken;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KII_KTI,
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
				return (KaleoTaskInstanceToken)result;
			}
		}
	}

	/**
	 * Returns all the kaleo task instance tokens.
	 *
	 * @return the kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskInstanceToken> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo task instance tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo task instance tokens
	 * @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	 * @return the range of kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskInstanceToken> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo task instance tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo task instance tokens
	 * @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskInstanceToken> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTaskInstanceToken> list = (List<KaleoTaskInstanceToken>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KALEOTASKINSTANCETOKEN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEOTASKINSTANCETOKEN.concat(KaleoTaskInstanceTokenModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<KaleoTaskInstanceToken>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KaleoTaskInstanceToken>)QueryUtil.list(q,
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
	 * Removes all the kaleo task instance tokens where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (KaleoTaskInstanceToken kaleoTaskInstanceToken : findByCompanyId(
				companyId)) {
			kaleoTaskInstanceTokenPersistence.remove(kaleoTaskInstanceToken);
		}
	}

	/**
	 * Removes all the kaleo task instance tokens where kaleoDefinitionId = &#63; from the database.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		for (KaleoTaskInstanceToken kaleoTaskInstanceToken : findByKaleoDefinitionId(
				kaleoDefinitionId)) {
			kaleoTaskInstanceTokenPersistence.remove(kaleoTaskInstanceToken);
		}
	}

	/**
	 * Removes all the kaleo task instance tokens where kaleoInstanceId = &#63; from the database.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKaleoInstanceId(long kaleoInstanceId)
		throws SystemException {
		for (KaleoTaskInstanceToken kaleoTaskInstanceToken : findByKaleoInstanceId(
				kaleoInstanceId)) {
			kaleoTaskInstanceTokenPersistence.remove(kaleoTaskInstanceToken);
		}
	}

	/**
	 * Removes the kaleo task instance token where kaleoInstanceId = &#63; and kaleoTaskId = &#63; from the database.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param kaleoTaskId the kaleo task ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKII_KTI(long kaleoInstanceId, long kaleoTaskId)
		throws NoSuchTaskInstanceTokenException, SystemException {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = findByKII_KTI(kaleoInstanceId,
				kaleoTaskId);

		kaleoTaskInstanceTokenPersistence.remove(kaleoTaskInstanceToken);
	}

	/**
	 * Removes all the kaleo task instance tokens from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (KaleoTaskInstanceToken kaleoTaskInstanceToken : findAll()) {
			kaleoTaskInstanceTokenPersistence.remove(kaleoTaskInstanceToken);
		}
	}

	/**
	 * Returns the number of kaleo task instance tokens where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTASKINSTANCETOKEN_WHERE);

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
	 * Returns the number of kaleo task instance tokens where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the number of matching kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoDefinitionId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTASKINSTANCETOKEN_WHERE);

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
	 * Returns the number of kaleo task instance tokens where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @return the number of matching kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKaleoInstanceId(long kaleoInstanceId)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoInstanceId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEOINSTANCEID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTASKINSTANCETOKEN_WHERE);

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
	 * Returns the number of kaleo task instance tokens where kaleoInstanceId = &#63; and kaleoTaskId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param kaleoTaskId the kaleo task ID
	 * @return the number of matching kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKII_KTI(long kaleoInstanceId, long kaleoTaskId)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoInstanceId, kaleoTaskId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KII_KTI,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEOTASKINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_KII_KTI_KALEOINSTANCEID_2);

			query.append(_FINDER_COLUMN_KII_KTI_KALEOTASKID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceId);

				qPos.add(kaleoTaskId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KII_KTI,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of kaleo task instance tokens.
	 *
	 * @return the number of kaleo task instance tokens
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

				Query q = session.createQuery(_SQL_COUNT_KALEOTASKINSTANCETOKEN);

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
	 * Returns all the kaleo task assignment instances associated with the kaleo task instance token.
	 *
	 * @param pk the primary key of the kaleo task instance token
	 * @return the kaleo task assignment instances associated with the kaleo task instance token
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> getKaleoTaskAssignmentInstances(
		long pk) throws SystemException {
		return getKaleoTaskAssignmentInstances(pk, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the kaleo task assignment instances associated with the kaleo task instance token.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the kaleo task instance token
	 * @param start the lower bound of the range of kaleo task instance tokens
	 * @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	 * @return the range of kaleo task assignment instances associated with the kaleo task instance token
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> getKaleoTaskAssignmentInstances(
		long pk, int start, int end) throws SystemException {
		return getKaleoTaskAssignmentInstances(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_KALEOTASKASSIGNMENTINSTANCES = new FinderPath(com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceImpl.class,
			com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskAssignmentInstancePersistenceImpl.FINDER_CLASS_NAME_LIST,
			"getKaleoTaskAssignmentInstances",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	/**
	 * Returns an ordered range of all the kaleo task assignment instances associated with the kaleo task instance token.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the kaleo task instance token
	 * @param start the lower bound of the range of kaleo task instance tokens
	 * @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo task assignment instances associated with the kaleo task instance token
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> getKaleoTaskAssignmentInstances(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				pk, String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> list =
			(List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance>)FinderCacheUtil.getResult(FINDER_PATH_GET_KALEOTASKASSIGNMENTINSTANCES,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETKALEOTASKASSIGNMENTINSTANCES.concat(ORDER_BY_CLAUSE)
															  .concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETKALEOTASKASSIGNMENTINSTANCES.concat(com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl.ORDER_BY_SQL);
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("KaleoTaskAssignmentInstance",
					com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_KALEOTASKASSIGNMENTINSTANCES,
						finderArgs);
				}
				else {
					kaleoTaskAssignmentInstancePersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_KALEOTASKASSIGNMENTINSTANCES,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_KALEOTASKASSIGNMENTINSTANCES_SIZE =
		new FinderPath(com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceImpl.class,
			com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskAssignmentInstancePersistenceImpl.FINDER_CLASS_NAME_LIST,
			"getKaleoTaskAssignmentInstancesSize",
			new String[] { Long.class.getName() });

	/**
	 * Returns the number of kaleo task assignment instances associated with the kaleo task instance token.
	 *
	 * @param pk the primary key of the kaleo task instance token
	 * @return the number of kaleo task assignment instances associated with the kaleo task instance token
	 * @throws SystemException if a system exception occurred
	 */
	public int getKaleoTaskAssignmentInstancesSize(long pk)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_KALEOTASKASSIGNMENTINSTANCES_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETKALEOTASKASSIGNMENTINSTANCESSIZE);

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

				FinderCacheUtil.putResult(FINDER_PATH_GET_KALEOTASKASSIGNMENTINSTANCES_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_KALEOTASKASSIGNMENTINSTANCE =
		new FinderPath(com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceImpl.class,
			com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskAssignmentInstancePersistenceImpl.FINDER_CLASS_NAME_LIST,
			"containsKaleoTaskAssignmentInstance",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the kaleo task assignment instance is associated with the kaleo task instance token.
	 *
	 * @param pk the primary key of the kaleo task instance token
	 * @param kaleoTaskAssignmentInstancePK the primary key of the kaleo task assignment instance
	 * @return <code>true</code> if the kaleo task assignment instance is associated with the kaleo task instance token; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsKaleoTaskAssignmentInstance(long pk,
		long kaleoTaskAssignmentInstancePK) throws SystemException {
		Object[] finderArgs = new Object[] { pk, kaleoTaskAssignmentInstancePK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_KALEOTASKASSIGNMENTINSTANCE,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsKaleoTaskAssignmentInstance.contains(
							pk, kaleoTaskAssignmentInstancePK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_KALEOTASKASSIGNMENTINSTANCE,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Returns <code>true</code> if the kaleo task instance token has any kaleo task assignment instances associated with it.
	 *
	 * @param pk the primary key of the kaleo task instance token to check for associations with kaleo task assignment instances
	 * @return <code>true</code> if the kaleo task instance token has any kaleo task assignment instances associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsKaleoTaskAssignmentInstances(long pk)
		throws SystemException {
		if (getKaleoTaskAssignmentInstancesSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Initializes the kaleo task instance token persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoTaskInstanceToken>> listenersList = new ArrayList<ModelListener<KaleoTaskInstanceToken>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoTaskInstanceToken>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsKaleoTaskAssignmentInstance = new ContainsKaleoTaskAssignmentInstance(this);
	}

	public void destroy() {
		EntityCacheUtil.removeCache(KaleoTaskInstanceTokenImpl.class.getName());
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
	protected ContainsKaleoTaskAssignmentInstance containsKaleoTaskAssignmentInstance;

	protected class ContainsKaleoTaskAssignmentInstance {
		protected ContainsKaleoTaskAssignmentInstance(
			KaleoTaskInstanceTokenPersistenceImpl persistenceImpl) {
			super();

			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSKALEOTASKASSIGNMENTINSTANCE,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long kaleoTaskInstanceTokenId,
			long kaleoTaskAssignmentInstanceId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(kaleoTaskInstanceTokenId),
						new Long(kaleoTaskAssignmentInstanceId)
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

	private static final String _SQL_SELECT_KALEOTASKINSTANCETOKEN = "SELECT kaleoTaskInstanceToken FROM KaleoTaskInstanceToken kaleoTaskInstanceToken";
	private static final String _SQL_SELECT_KALEOTASKINSTANCETOKEN_WHERE = "SELECT kaleoTaskInstanceToken FROM KaleoTaskInstanceToken kaleoTaskInstanceToken WHERE ";
	private static final String _SQL_COUNT_KALEOTASKINSTANCETOKEN = "SELECT COUNT(kaleoTaskInstanceToken) FROM KaleoTaskInstanceToken kaleoTaskInstanceToken";
	private static final String _SQL_COUNT_KALEOTASKINSTANCETOKEN_WHERE = "SELECT COUNT(kaleoTaskInstanceToken) FROM KaleoTaskInstanceToken kaleoTaskInstanceToken WHERE ";
	private static final String _SQL_GETKALEOTASKASSIGNMENTINSTANCES = "SELECT {KaleoTaskAssignmentInstance.*} FROM KaleoTaskAssignmentInstance INNER JOIN KaleoTaskInstanceToken ON (KaleoTaskInstanceToken.kaleoTaskInstanceTokenId = KaleoTaskAssignmentInstance.kaleoTaskInstanceTokenId) WHERE (KaleoTaskInstanceToken.kaleoTaskInstanceTokenId = ?)";
	private static final String _SQL_GETKALEOTASKASSIGNMENTINSTANCESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM KaleoTaskAssignmentInstance WHERE kaleoTaskInstanceTokenId = ?";
	private static final String _SQL_CONTAINSKALEOTASKASSIGNMENTINSTANCE = "SELECT COUNT(*) AS COUNT_VALUE FROM KaleoTaskAssignmentInstance WHERE kaleoTaskInstanceTokenId = ? AND kaleoTaskAssignmentInstanceId = ?";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoTaskInstanceToken.companyId = ?";
	private static final String _FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2 =
		"kaleoTaskInstanceToken.kaleoDefinitionId = ?";
	private static final String _FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2 =
		"kaleoTaskInstanceToken.kaleoInstanceId = ?";
	private static final String _FINDER_COLUMN_KII_KTI_KALEOINSTANCEID_2 = "kaleoTaskInstanceToken.kaleoInstanceId = ? AND ";
	private static final String _FINDER_COLUMN_KII_KTI_KALEOTASKID_2 = "kaleoTaskInstanceToken.kaleoTaskId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoTaskInstanceToken.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoTaskInstanceToken exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoTaskInstanceToken exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(KaleoTaskInstanceTokenPersistenceImpl.class);
	private static KaleoTaskInstanceToken _nullKaleoTaskInstanceToken = new KaleoTaskInstanceTokenImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<KaleoTaskInstanceToken> toCacheModel() {
				return _nullKaleoTaskInstanceTokenCacheModel;
			}
		};

	private static CacheModel<KaleoTaskInstanceToken> _nullKaleoTaskInstanceTokenCacheModel =
		new CacheModel<KaleoTaskInstanceToken>() {
			public KaleoTaskInstanceToken toEntityModel() {
				return _nullKaleoTaskInstanceToken;
			}
		};
}