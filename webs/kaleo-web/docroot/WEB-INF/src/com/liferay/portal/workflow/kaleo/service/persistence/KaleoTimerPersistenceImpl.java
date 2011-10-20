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
import com.liferay.portal.workflow.kaleo.NoSuchTimerException;
import com.liferay.portal.workflow.kaleo.model.KaleoTimer;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the kaleo timer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTimerPersistence
 * @see KaleoTimerUtil
 * @generated
 */
public class KaleoTimerPersistenceImpl extends BasePersistenceImpl<KaleoTimer>
	implements KaleoTimerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KaleoTimerUtil} to access the kaleo timer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoTimerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KCN_KCPK = new FinderPath(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerModelImpl.FINDER_CACHE_ENABLED, KaleoTimerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKCN_KCPK",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK =
		new FinderPath(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerModelImpl.FINDER_CACHE_ENABLED, KaleoTimerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByKCN_KCPK",
			new String[] { String.class.getName(), Long.class.getName() },
			KaleoTimerModelImpl.KALEOCLASSNAME_COLUMN_BITMASK |
			KaleoTimerModelImpl.KALEOCLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KCN_KCPK = new FinderPath(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKCN_KCPK",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KCN_KCPK_BLOCKING =
		new FinderPath(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerModelImpl.FINDER_CACHE_ENABLED, KaleoTimerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKCN_KCPK_Blocking",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_BLOCKING =
		new FinderPath(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerModelImpl.FINDER_CACHE_ENABLED, KaleoTimerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByKCN_KCPK_Blocking",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			},
			KaleoTimerModelImpl.KALEOCLASSNAME_COLUMN_BITMASK |
			KaleoTimerModelImpl.KALEOCLASSPK_COLUMN_BITMASK |
			KaleoTimerModelImpl.BLOCKING_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KCN_KCPK_BLOCKING = new FinderPath(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKCN_KCPK_Blocking",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerModelImpl.FINDER_CACHE_ENABLED, KaleoTimerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerModelImpl.FINDER_CACHE_ENABLED, KaleoTimerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the kaleo timer in the entity cache if it is enabled.
	 *
	 * @param kaleoTimer the kaleo timer
	 */
	public void cacheResult(KaleoTimer kaleoTimer) {
		EntityCacheUtil.putResult(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerImpl.class, kaleoTimer.getPrimaryKey(), kaleoTimer);

		kaleoTimer.resetOriginalValues();
	}

	/**
	 * Caches the kaleo timers in the entity cache if it is enabled.
	 *
	 * @param kaleoTimers the kaleo timers
	 */
	public void cacheResult(List<KaleoTimer> kaleoTimers) {
		for (KaleoTimer kaleoTimer : kaleoTimers) {
			if (EntityCacheUtil.getResult(
						KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTimerImpl.class, kaleoTimer.getPrimaryKey()) == null) {
				cacheResult(kaleoTimer);
			}
			else {
				kaleoTimer.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all kaleo timers.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(KaleoTimerImpl.class.getName());
		}

		EntityCacheUtil.clearCache(KaleoTimerImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the kaleo timer.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KaleoTimer kaleoTimer) {
		EntityCacheUtil.removeResult(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerImpl.class, kaleoTimer.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Creates a new kaleo timer with the primary key. Does not add the kaleo timer to the database.
	 *
	 * @param kaleoTimerId the primary key for the new kaleo timer
	 * @return the new kaleo timer
	 */
	public KaleoTimer create(long kaleoTimerId) {
		KaleoTimer kaleoTimer = new KaleoTimerImpl();

		kaleoTimer.setNew(true);
		kaleoTimer.setPrimaryKey(kaleoTimerId);

		return kaleoTimer;
	}

	/**
	 * Removes the kaleo timer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo timer
	 * @return the kaleo timer that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo timer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTimer remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the kaleo timer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTimerId the primary key of the kaleo timer
	 * @return the kaleo timer that was removed
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a kaleo timer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTimer remove(long kaleoTimerId)
		throws NoSuchTimerException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoTimer kaleoTimer = (KaleoTimer)session.get(KaleoTimerImpl.class,
					Long.valueOf(kaleoTimerId));

			if (kaleoTimer == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + kaleoTimerId);
				}

				throw new NoSuchTimerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoTimerId);
			}

			return kaleoTimerPersistence.remove(kaleoTimer);
		}
		catch (NoSuchTimerException nsee) {
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
	 * Removes the kaleo timer from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTimer the kaleo timer
	 * @return the kaleo timer that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTimer remove(KaleoTimer kaleoTimer) throws SystemException {
		return super.remove(kaleoTimer);
	}

	@Override
	protected KaleoTimer removeImpl(KaleoTimer kaleoTimer)
		throws SystemException {
		kaleoTimer = toUnwrappedModel(kaleoTimer);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, kaleoTimer);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		EntityCacheUtil.removeResult(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerImpl.class, kaleoTimer.getPrimaryKey());

		return kaleoTimer;
	}

	@Override
	public KaleoTimer updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTimer kaleoTimer,
		boolean merge) throws SystemException {
		kaleoTimer = toUnwrappedModel(kaleoTimer);

		boolean isNew = kaleoTimer.isNew();

		KaleoTimerModelImpl kaleoTimerModelImpl = (KaleoTimerModelImpl)kaleoTimer;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kaleoTimer, merge);

			kaleoTimer.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !KaleoTimerModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((kaleoTimerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoTimerModelImpl.getOriginalKaleoClassName(),
						Long.valueOf(kaleoTimerModelImpl.getOriginalKaleoClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KCN_KCPK, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK,
					args);

				args = new Object[] {
						kaleoTimerModelImpl.getKaleoClassName(),
						Long.valueOf(kaleoTimerModelImpl.getKaleoClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KCN_KCPK, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK,
					args);
			}

			if ((kaleoTimerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_BLOCKING.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoTimerModelImpl.getOriginalKaleoClassName(),
						Long.valueOf(kaleoTimerModelImpl.getOriginalKaleoClassPK()),
						Boolean.valueOf(kaleoTimerModelImpl.getOriginalBlocking())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KCN_KCPK_BLOCKING,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_BLOCKING,
					args);

				args = new Object[] {
						kaleoTimerModelImpl.getKaleoClassName(),
						Long.valueOf(kaleoTimerModelImpl.getKaleoClassPK()),
						Boolean.valueOf(kaleoTimerModelImpl.getBlocking())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KCN_KCPK_BLOCKING,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_BLOCKING,
					args);
			}
		}

		EntityCacheUtil.putResult(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerImpl.class, kaleoTimer.getPrimaryKey(), kaleoTimer);

		return kaleoTimer;
	}

	protected KaleoTimer toUnwrappedModel(KaleoTimer kaleoTimer) {
		if (kaleoTimer instanceof KaleoTimerImpl) {
			return kaleoTimer;
		}

		KaleoTimerImpl kaleoTimerImpl = new KaleoTimerImpl();

		kaleoTimerImpl.setNew(kaleoTimer.isNew());
		kaleoTimerImpl.setPrimaryKey(kaleoTimer.getPrimaryKey());

		kaleoTimerImpl.setKaleoTimerId(kaleoTimer.getKaleoTimerId());
		kaleoTimerImpl.setGroupId(kaleoTimer.getGroupId());
		kaleoTimerImpl.setCompanyId(kaleoTimer.getCompanyId());
		kaleoTimerImpl.setUserId(kaleoTimer.getUserId());
		kaleoTimerImpl.setUserName(kaleoTimer.getUserName());
		kaleoTimerImpl.setCreateDate(kaleoTimer.getCreateDate());
		kaleoTimerImpl.setModifiedDate(kaleoTimer.getModifiedDate());
		kaleoTimerImpl.setKaleoClassName(kaleoTimer.getKaleoClassName());
		kaleoTimerImpl.setKaleoClassPK(kaleoTimer.getKaleoClassPK());
		kaleoTimerImpl.setKaleoDefinitionId(kaleoTimer.getKaleoDefinitionId());
		kaleoTimerImpl.setName(kaleoTimer.getName());
		kaleoTimerImpl.setBlocking(kaleoTimer.isBlocking());
		kaleoTimerImpl.setDescription(kaleoTimer.getDescription());
		kaleoTimerImpl.setDuration(kaleoTimer.getDuration());
		kaleoTimerImpl.setScale(kaleoTimer.getScale());
		kaleoTimerImpl.setRecurrenceDuration(kaleoTimer.getRecurrenceDuration());
		kaleoTimerImpl.setRecurrenceScale(kaleoTimer.getRecurrenceScale());

		return kaleoTimerImpl;
	}

	/**
	 * Returns the kaleo timer with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo timer
	 * @return the kaleo timer
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo timer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTimer findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the kaleo timer with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTimerException} if it could not be found.
	 *
	 * @param kaleoTimerId the primary key of the kaleo timer
	 * @return the kaleo timer
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a kaleo timer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTimer findByPrimaryKey(long kaleoTimerId)
		throws NoSuchTimerException, SystemException {
		KaleoTimer kaleoTimer = fetchByPrimaryKey(kaleoTimerId);

		if (kaleoTimer == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + kaleoTimerId);
			}

			throw new NoSuchTimerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				kaleoTimerId);
		}

		return kaleoTimer;
	}

	/**
	 * Returns the kaleo timer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo timer
	 * @return the kaleo timer, or <code>null</code> if a kaleo timer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTimer fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the kaleo timer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoTimerId the primary key of the kaleo timer
	 * @return the kaleo timer, or <code>null</code> if a kaleo timer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTimer fetchByPrimaryKey(long kaleoTimerId)
		throws SystemException {
		KaleoTimer kaleoTimer = (KaleoTimer)EntityCacheUtil.getResult(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
				KaleoTimerImpl.class, kaleoTimerId);

		if (kaleoTimer == _nullKaleoTimer) {
			return null;
		}

		if (kaleoTimer == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				kaleoTimer = (KaleoTimer)session.get(KaleoTimerImpl.class,
						Long.valueOf(kaleoTimerId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (kaleoTimer != null) {
					cacheResult(kaleoTimer);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTimerImpl.class, kaleoTimerId, _nullKaleoTimer);
				}

				closeSession(session);
			}
		}

		return kaleoTimer;
	}

	/**
	 * Returns all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @return the matching kaleo timers
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTimer> findByKCN_KCPK(String kaleoClassName,
		long kaleoClassPK) throws SystemException {
		return findByKCN_KCPK(kaleoClassName, kaleoClassPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param start the lower bound of the range of kaleo timers
	 * @param end the upper bound of the range of kaleo timers (not inclusive)
	 * @return the range of matching kaleo timers
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTimer> findByKCN_KCPK(String kaleoClassName,
		long kaleoClassPK, int start, int end) throws SystemException {
		return findByKCN_KCPK(kaleoClassName, kaleoClassPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param start the lower bound of the range of kaleo timers
	 * @param end the upper bound of the range of kaleo timers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo timers
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTimer> findByKCN_KCPK(String kaleoClassName,
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

		List<KaleoTimer> list = (List<KaleoTimer>)FinderCacheUtil.getResult(finderPath,
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

			query.append(_SQL_SELECT_KALEOTIMER_WHERE);

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
				query.append(KaleoTimerModelImpl.ORDER_BY_JPQL);
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

				list = (List<KaleoTimer>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first kaleo timer in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo timer
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a matching kaleo timer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTimer findByKCN_KCPK_First(String kaleoClassName,
		long kaleoClassPK, OrderByComparator orderByComparator)
		throws NoSuchTimerException, SystemException {
		List<KaleoTimer> list = findByKCN_KCPK(kaleoClassName, kaleoClassPK, 0,
				1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoClassName=");
			msg.append(kaleoClassName);

			msg.append(", kaleoClassPK=");
			msg.append(kaleoClassPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTimerException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo timer in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo timer
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a matching kaleo timer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTimer findByKCN_KCPK_Last(String kaleoClassName,
		long kaleoClassPK, OrderByComparator orderByComparator)
		throws NoSuchTimerException, SystemException {
		int count = countByKCN_KCPK(kaleoClassName, kaleoClassPK);

		List<KaleoTimer> list = findByKCN_KCPK(kaleoClassName, kaleoClassPK,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoClassName=");
			msg.append(kaleoClassName);

			msg.append(", kaleoClassPK=");
			msg.append(kaleoClassPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTimerException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo timers before and after the current kaleo timer in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTimerId the primary key of the current kaleo timer
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo timer
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a kaleo timer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTimer[] findByKCN_KCPK_PrevAndNext(long kaleoTimerId,
		String kaleoClassName, long kaleoClassPK,
		OrderByComparator orderByComparator)
		throws NoSuchTimerException, SystemException {
		KaleoTimer kaleoTimer = findByPrimaryKey(kaleoTimerId);

		Session session = null;

		try {
			session = openSession();

			KaleoTimer[] array = new KaleoTimerImpl[3];

			array[0] = getByKCN_KCPK_PrevAndNext(session, kaleoTimer,
					kaleoClassName, kaleoClassPK, orderByComparator, true);

			array[1] = kaleoTimer;

			array[2] = getByKCN_KCPK_PrevAndNext(session, kaleoTimer,
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

	protected KaleoTimer getByKCN_KCPK_PrevAndNext(Session session,
		KaleoTimer kaleoTimer, String kaleoClassName, long kaleoClassPK,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTIMER_WHERE);

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
			query.append(KaleoTimerModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoTimer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTimer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param blocking the blocking
	 * @return the matching kaleo timers
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTimer> findByKCN_KCPK_Blocking(String kaleoClassName,
		long kaleoClassPK, boolean blocking) throws SystemException {
		return findByKCN_KCPK_Blocking(kaleoClassName, kaleoClassPK, blocking,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param blocking the blocking
	 * @param start the lower bound of the range of kaleo timers
	 * @param end the upper bound of the range of kaleo timers (not inclusive)
	 * @return the range of matching kaleo timers
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTimer> findByKCN_KCPK_Blocking(String kaleoClassName,
		long kaleoClassPK, boolean blocking, int start, int end)
		throws SystemException {
		return findByKCN_KCPK_Blocking(kaleoClassName, kaleoClassPK, blocking,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param blocking the blocking
	 * @param start the lower bound of the range of kaleo timers
	 * @param end the upper bound of the range of kaleo timers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo timers
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTimer> findByKCN_KCPK_Blocking(String kaleoClassName,
		long kaleoClassPK, boolean blocking, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_BLOCKING;
			finderArgs = new Object[] { kaleoClassName, kaleoClassPK, blocking };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_KCN_KCPK_BLOCKING;
			finderArgs = new Object[] {
					kaleoClassName, kaleoClassPK, blocking,
					
					start, end, orderByComparator
				};
		}

		List<KaleoTimer> list = (List<KaleoTimer>)FinderCacheUtil.getResult(finderPath,
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

			query.append(_SQL_SELECT_KALEOTIMER_WHERE);

			if (kaleoClassName == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSNAME_1);
			}
			else {
				if (kaleoClassName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSNAME_2);
				}
			}

			query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSPK_2);

			query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_BLOCKING_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoTimerModelImpl.ORDER_BY_JPQL);
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

				qPos.add(blocking);

				list = (List<KaleoTimer>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first kaleo timer in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param blocking the blocking
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo timer
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a matching kaleo timer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTimer findByKCN_KCPK_Blocking_First(String kaleoClassName,
		long kaleoClassPK, boolean blocking, OrderByComparator orderByComparator)
		throws NoSuchTimerException, SystemException {
		List<KaleoTimer> list = findByKCN_KCPK_Blocking(kaleoClassName,
				kaleoClassPK, blocking, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoClassName=");
			msg.append(kaleoClassName);

			msg.append(", kaleoClassPK=");
			msg.append(kaleoClassPK);

			msg.append(", blocking=");
			msg.append(blocking);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTimerException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo timer in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param blocking the blocking
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo timer
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a matching kaleo timer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTimer findByKCN_KCPK_Blocking_Last(String kaleoClassName,
		long kaleoClassPK, boolean blocking, OrderByComparator orderByComparator)
		throws NoSuchTimerException, SystemException {
		int count = countByKCN_KCPK_Blocking(kaleoClassName, kaleoClassPK,
				blocking);

		List<KaleoTimer> list = findByKCN_KCPK_Blocking(kaleoClassName,
				kaleoClassPK, blocking, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoClassName=");
			msg.append(kaleoClassName);

			msg.append(", kaleoClassPK=");
			msg.append(kaleoClassPK);

			msg.append(", blocking=");
			msg.append(blocking);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTimerException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo timers before and after the current kaleo timer in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTimerId the primary key of the current kaleo timer
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param blocking the blocking
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo timer
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTimerException if a kaleo timer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTimer[] findByKCN_KCPK_Blocking_PrevAndNext(long kaleoTimerId,
		String kaleoClassName, long kaleoClassPK, boolean blocking,
		OrderByComparator orderByComparator)
		throws NoSuchTimerException, SystemException {
		KaleoTimer kaleoTimer = findByPrimaryKey(kaleoTimerId);

		Session session = null;

		try {
			session = openSession();

			KaleoTimer[] array = new KaleoTimerImpl[3];

			array[0] = getByKCN_KCPK_Blocking_PrevAndNext(session, kaleoTimer,
					kaleoClassName, kaleoClassPK, blocking, orderByComparator,
					true);

			array[1] = kaleoTimer;

			array[2] = getByKCN_KCPK_Blocking_PrevAndNext(session, kaleoTimer,
					kaleoClassName, kaleoClassPK, blocking, orderByComparator,
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

	protected KaleoTimer getByKCN_KCPK_Blocking_PrevAndNext(Session session,
		KaleoTimer kaleoTimer, String kaleoClassName, long kaleoClassPK,
		boolean blocking, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTIMER_WHERE);

		if (kaleoClassName == null) {
			query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSNAME_1);
		}
		else {
			if (kaleoClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSNAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSNAME_2);
			}
		}

		query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSPK_2);

		query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_BLOCKING_2);

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
			query.append(KaleoTimerModelImpl.ORDER_BY_JPQL);
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

		qPos.add(blocking);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoTimer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTimer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo timers.
	 *
	 * @return the kaleo timers
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTimer> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo timers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo timers
	 * @param end the upper bound of the range of kaleo timers (not inclusive)
	 * @return the range of kaleo timers
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTimer> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo timers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo timers
	 * @param end the upper bound of the range of kaleo timers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo timers
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTimer> findAll(int start, int end,
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

		List<KaleoTimer> list = (List<KaleoTimer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KALEOTIMER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEOTIMER.concat(KaleoTimerModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<KaleoTimer>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KaleoTimer>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63; from the database.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKCN_KCPK(String kaleoClassName, long kaleoClassPK)
		throws SystemException {
		for (KaleoTimer kaleoTimer : findByKCN_KCPK(kaleoClassName, kaleoClassPK)) {
			kaleoTimerPersistence.remove(kaleoTimer);
		}
	}

	/**
	 * Removes all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63; from the database.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param blocking the blocking
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKCN_KCPK_Blocking(String kaleoClassName,
		long kaleoClassPK, boolean blocking) throws SystemException {
		for (KaleoTimer kaleoTimer : findByKCN_KCPK_Blocking(kaleoClassName,
				kaleoClassPK, blocking)) {
			kaleoTimerPersistence.remove(kaleoTimer);
		}
	}

	/**
	 * Removes all the kaleo timers from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (KaleoTimer kaleoTimer : findAll()) {
			kaleoTimerPersistence.remove(kaleoTimer);
		}
	}

	/**
	 * Returns the number of kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @return the number of matching kaleo timers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKCN_KCPK(String kaleoClassName, long kaleoClassPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoClassName, kaleoClassPK };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KCN_KCPK,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEOTIMER_WHERE);

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
	 * Returns the number of kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param blocking the blocking
	 * @return the number of matching kaleo timers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKCN_KCPK_Blocking(String kaleoClassName,
		long kaleoClassPK, boolean blocking) throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoClassName, kaleoClassPK, blocking
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KCN_KCPK_BLOCKING,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KALEOTIMER_WHERE);

			if (kaleoClassName == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSNAME_1);
			}
			else {
				if (kaleoClassName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSNAME_2);
				}
			}

			query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSPK_2);

			query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_BLOCKING_2);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KCN_KCPK_BLOCKING,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of kaleo timers.
	 *
	 * @return the number of kaleo timers
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KALEOTIMER);

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
	 * Initializes the kaleo timer persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.kaleo.model.KaleoTimer")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoTimer>> listenersList = new ArrayList<ModelListener<KaleoTimer>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoTimer>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(KaleoTimerImpl.class.getName());
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
	private static final String _SQL_SELECT_KALEOTIMER = "SELECT kaleoTimer FROM KaleoTimer kaleoTimer";
	private static final String _SQL_SELECT_KALEOTIMER_WHERE = "SELECT kaleoTimer FROM KaleoTimer kaleoTimer WHERE ";
	private static final String _SQL_COUNT_KALEOTIMER = "SELECT COUNT(kaleoTimer) FROM KaleoTimer kaleoTimer";
	private static final String _SQL_COUNT_KALEOTIMER_WHERE = "SELECT COUNT(kaleoTimer) FROM KaleoTimer kaleoTimer WHERE ";
	private static final String _FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_1 = "kaleoTimer.kaleoClassName IS NULL AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_2 = "kaleoTimer.kaleoClassName = ? AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_3 = "(kaleoTimer.kaleoClassName IS NULL OR kaleoTimer.kaleoClassName = ?) AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_KALEOCLASSPK_2 = "kaleoTimer.kaleoClassPK = ?";
	private static final String _FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSNAME_1 =
		"kaleoTimer.kaleoClassName IS NULL AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSNAME_2 =
		"kaleoTimer.kaleoClassName = ? AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSNAME_3 =
		"(kaleoTimer.kaleoClassName IS NULL OR kaleoTimer.kaleoClassName = ?) AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSPK_2 = "kaleoTimer.kaleoClassPK = ? AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_BLOCKING_BLOCKING_2 = "kaleoTimer.blocking = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoTimer.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoTimer exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoTimer exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(KaleoTimerPersistenceImpl.class);
	private static KaleoTimer _nullKaleoTimer = new KaleoTimerImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<KaleoTimer> toCacheModel() {
				return _nullKaleoTimerCacheModel;
			}
		};

	private static CacheModel<KaleoTimer> _nullKaleoTimerCacheModel = new CacheModel<KaleoTimer>() {
			public KaleoTimer toEntityModel() {
				return _nullKaleoTimer;
			}
		};
}