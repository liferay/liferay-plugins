/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.annotation.BeanReference;
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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.NoSuchConditionException;
import com.liferay.portal.workflow.kaleo.model.KaleoCondition;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoConditionImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoConditionModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the kaleo condition service.
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link KaleoConditionUtil} to access the kaleo condition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoConditionPersistence
 * @see KaleoConditionUtil
 * @generated
 */
public class KaleoConditionPersistenceImpl extends BasePersistenceImpl<KaleoCondition>
	implements KaleoConditionPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoConditionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoConditionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoConditionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_KALEOCN_PK = new FinderPath(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoConditionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_ENTITY, "fetchByKaleoCN_PK",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEOCN_PK = new FinderPath(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoConditionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByKaleoCN_PK",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoConditionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoConditionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the kaleo condition in the entity cache if it is enabled.
	 *
	 * @param kaleoCondition the kaleo condition to cache
	 */
	public void cacheResult(KaleoCondition kaleoCondition) {
		EntityCacheUtil.putResult(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoConditionImpl.class, kaleoCondition.getPrimaryKey(),
			kaleoCondition);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KALEOCN_PK,
			new Object[] {
				kaleoCondition.getKaleoClassName(),
				new Long(kaleoCondition.getKaleoClassPK())
			}, kaleoCondition);
	}

	/**
	 * Caches the kaleo conditions in the entity cache if it is enabled.
	 *
	 * @param kaleoConditions the kaleo conditions to cache
	 */
	public void cacheResult(List<KaleoCondition> kaleoConditions) {
		for (KaleoCondition kaleoCondition : kaleoConditions) {
			if (EntityCacheUtil.getResult(
						KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
						KaleoConditionImpl.class,
						kaleoCondition.getPrimaryKey(), this) == null) {
				cacheResult(kaleoCondition);
			}
		}
	}

	/**
	 * Clears the cache for all kaleo conditions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(KaleoConditionImpl.class.getName());
		EntityCacheUtil.clearCache(KaleoConditionImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the kaleo condition.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(KaleoCondition kaleoCondition) {
		EntityCacheUtil.removeResult(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoConditionImpl.class, kaleoCondition.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KALEOCN_PK,
			new Object[] {
				kaleoCondition.getKaleoClassName(),
				new Long(kaleoCondition.getKaleoClassPK())
			});
	}

	/**
	 * Creates a new kaleo condition with the primary key. Does not add the kaleo condition to the database.
	 *
	 * @param kaleoConditionId the primary key for the new kaleo condition
	 * @return the new kaleo condition
	 */
	public KaleoCondition create(long kaleoConditionId) {
		KaleoCondition kaleoCondition = new KaleoConditionImpl();

		kaleoCondition.setNew(true);
		kaleoCondition.setPrimaryKey(kaleoConditionId);

		return kaleoCondition;
	}

	/**
	 * Removes the kaleo condition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo condition to remove
	 * @return the kaleo condition that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo condition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoCondition remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the kaleo condition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoConditionId the primary key of the kaleo condition to remove
	 * @return the kaleo condition that was removed
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchConditionException if a kaleo condition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoCondition remove(long kaleoConditionId)
		throws NoSuchConditionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoCondition kaleoCondition = (KaleoCondition)session.get(KaleoConditionImpl.class,
					new Long(kaleoConditionId));

			if (kaleoCondition == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						kaleoConditionId);
				}

				throw new NoSuchConditionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoConditionId);
			}

			return remove(kaleoCondition);
		}
		catch (NoSuchConditionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoCondition removeImpl(KaleoCondition kaleoCondition)
		throws SystemException {
		kaleoCondition = toUnwrappedModel(kaleoCondition);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, kaleoCondition);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		KaleoConditionModelImpl kaleoConditionModelImpl = (KaleoConditionModelImpl)kaleoCondition;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KALEOCN_PK,
			new Object[] {
				kaleoConditionModelImpl.getOriginalKaleoClassName(),
				new Long(kaleoConditionModelImpl.getOriginalKaleoClassPK())
			});

		EntityCacheUtil.removeResult(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoConditionImpl.class, kaleoCondition.getPrimaryKey());

		return kaleoCondition;
	}

	public KaleoCondition updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoCondition kaleoCondition,
		boolean merge) throws SystemException {
		kaleoCondition = toUnwrappedModel(kaleoCondition);

		boolean isNew = kaleoCondition.isNew();

		KaleoConditionModelImpl kaleoConditionModelImpl = (KaleoConditionModelImpl)kaleoCondition;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kaleoCondition, merge);

			kaleoCondition.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoConditionImpl.class, kaleoCondition.getPrimaryKey(),
			kaleoCondition);

		if (!isNew &&
				(!Validator.equals(kaleoCondition.getKaleoClassName(),
					kaleoConditionModelImpl.getOriginalKaleoClassName()) ||
				(kaleoCondition.getKaleoClassPK() != kaleoConditionModelImpl.getOriginalKaleoClassPK()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KALEOCN_PK,
				new Object[] {
					kaleoConditionModelImpl.getOriginalKaleoClassName(),
					new Long(kaleoConditionModelImpl.getOriginalKaleoClassPK())
				});
		}

		if (isNew ||
				(!Validator.equals(kaleoCondition.getKaleoClassName(),
					kaleoConditionModelImpl.getOriginalKaleoClassName()) ||
				(kaleoCondition.getKaleoClassPK() != kaleoConditionModelImpl.getOriginalKaleoClassPK()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KALEOCN_PK,
				new Object[] {
					kaleoCondition.getKaleoClassName(),
					new Long(kaleoCondition.getKaleoClassPK())
				}, kaleoCondition);
		}

		return kaleoCondition;
	}

	protected KaleoCondition toUnwrappedModel(KaleoCondition kaleoCondition) {
		if (kaleoCondition instanceof KaleoConditionImpl) {
			return kaleoCondition;
		}

		KaleoConditionImpl kaleoConditionImpl = new KaleoConditionImpl();

		kaleoConditionImpl.setNew(kaleoCondition.isNew());
		kaleoConditionImpl.setPrimaryKey(kaleoCondition.getPrimaryKey());

		kaleoConditionImpl.setKaleoConditionId(kaleoCondition.getKaleoConditionId());
		kaleoConditionImpl.setGroupId(kaleoCondition.getGroupId());
		kaleoConditionImpl.setCompanyId(kaleoCondition.getCompanyId());
		kaleoConditionImpl.setUserId(kaleoCondition.getUserId());
		kaleoConditionImpl.setUserName(kaleoCondition.getUserName());
		kaleoConditionImpl.setCreateDate(kaleoCondition.getCreateDate());
		kaleoConditionImpl.setModifiedDate(kaleoCondition.getModifiedDate());
		kaleoConditionImpl.setKaleoClassName(kaleoCondition.getKaleoClassName());
		kaleoConditionImpl.setKaleoClassPK(kaleoCondition.getKaleoClassPK());
		kaleoConditionImpl.setDescription(kaleoCondition.getDescription());
		kaleoConditionImpl.setScript(kaleoCondition.getScript());
		kaleoConditionImpl.setScriptLanguage(kaleoCondition.getScriptLanguage());

		return kaleoConditionImpl;
	}

	/**
	 * Finds the kaleo condition with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo condition to find
	 * @return the kaleo condition
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo condition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoCondition findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the kaleo condition with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchConditionException} if it could not be found.
	 *
	 * @param kaleoConditionId the primary key of the kaleo condition to find
	 * @return the kaleo condition
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchConditionException if a kaleo condition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoCondition findByPrimaryKey(long kaleoConditionId)
		throws NoSuchConditionException, SystemException {
		KaleoCondition kaleoCondition = fetchByPrimaryKey(kaleoConditionId);

		if (kaleoCondition == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + kaleoConditionId);
			}

			throw new NoSuchConditionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				kaleoConditionId);
		}

		return kaleoCondition;
	}

	/**
	 * Finds the kaleo condition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo condition to find
	 * @return the kaleo condition, or <code>null</code> if a kaleo condition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoCondition fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the kaleo condition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoConditionId the primary key of the kaleo condition to find
	 * @return the kaleo condition, or <code>null</code> if a kaleo condition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoCondition fetchByPrimaryKey(long kaleoConditionId)
		throws SystemException {
		KaleoCondition kaleoCondition = (KaleoCondition)EntityCacheUtil.getResult(KaleoConditionModelImpl.ENTITY_CACHE_ENABLED,
				KaleoConditionImpl.class, kaleoConditionId, this);

		if (kaleoCondition == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoCondition = (KaleoCondition)session.get(KaleoConditionImpl.class,
						new Long(kaleoConditionId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (kaleoCondition != null) {
					cacheResult(kaleoCondition);
				}

				closeSession(session);
			}
		}

		return kaleoCondition;
	}

	/**
	 * Finds all the kaleo conditions where companyId = &#63;.
	 *
	 * @param companyId the company id to search with
	 * @return the matching kaleo conditions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoCondition> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Finds a range of all the kaleo conditions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param start the lower bound of the range of kaleo conditions to return
	 * @param end the upper bound of the range of kaleo conditions to return (not inclusive)
	 * @return the range of matching kaleo conditions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoCondition> findByCompanyId(long companyId, int start,
		int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the kaleo conditions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param start the lower bound of the range of kaleo conditions to return
	 * @param end the upper bound of the range of kaleo conditions to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching kaleo conditions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoCondition> findByCompanyId(long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoCondition> list = (List<KaleoCondition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
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

			query.append(_SQL_SELECT_KALEOCONDITION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoConditionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<KaleoCondition>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoCondition>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first kaleo condition in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching kaleo condition
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchConditionException if a matching kaleo condition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoCondition findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchConditionException, SystemException {
		List<KaleoCondition> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchConditionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last kaleo condition in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching kaleo condition
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchConditionException if a matching kaleo condition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoCondition findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchConditionException, SystemException {
		int count = countByCompanyId(companyId);

		List<KaleoCondition> list = findByCompanyId(companyId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchConditionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the kaleo conditions before and after the current kaleo condition in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoConditionId the primary key of the current kaleo condition
	 * @param companyId the company id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next kaleo condition
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchConditionException if a kaleo condition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoCondition[] findByCompanyId_PrevAndNext(long kaleoConditionId,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchConditionException, SystemException {
		KaleoCondition kaleoCondition = findByPrimaryKey(kaleoConditionId);

		Session session = null;

		try {
			session = openSession();

			KaleoCondition[] array = new KaleoConditionImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, kaleoCondition,
					companyId, orderByComparator, true);

			array[1] = kaleoCondition;

			array[2] = getByCompanyId_PrevAndNext(session, kaleoCondition,
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

	protected KaleoCondition getByCompanyId_PrevAndNext(Session session,
		KaleoCondition kaleoCondition, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOCONDITION_WHERE);

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
			query.append(KaleoConditionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoCondition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoCondition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds the kaleo condition where kaleoClassName = &#63; and kaleoClassPK = &#63; or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchConditionException} if it could not be found.
	 *
	 * @param kaleoClassName the kaleo class name to search with
	 * @param kaleoClassPK the kaleo class p k to search with
	 * @return the matching kaleo condition
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchConditionException if a matching kaleo condition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoCondition findByKaleoCN_PK(String kaleoClassName,
		long kaleoClassPK) throws NoSuchConditionException, SystemException {
		KaleoCondition kaleoCondition = fetchByKaleoCN_PK(kaleoClassName,
				kaleoClassPK);

		if (kaleoCondition == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoClassName=");
			msg.append(kaleoClassName);

			msg.append(", kaleoClassPK=");
			msg.append(kaleoClassPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchConditionException(msg.toString());
		}

		return kaleoCondition;
	}

	/**
	 * Finds the kaleo condition where kaleoClassName = &#63; and kaleoClassPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param kaleoClassName the kaleo class name to search with
	 * @param kaleoClassPK the kaleo class p k to search with
	 * @return the matching kaleo condition, or <code>null</code> if a matching kaleo condition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoCondition fetchByKaleoCN_PK(String kaleoClassName,
		long kaleoClassPK) throws SystemException {
		return fetchByKaleoCN_PK(kaleoClassName, kaleoClassPK, true);
	}

	/**
	 * Finds the kaleo condition where kaleoClassName = &#63; and kaleoClassPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param kaleoClassName the kaleo class name to search with
	 * @param kaleoClassPK the kaleo class p k to search with
	 * @return the matching kaleo condition, or <code>null</code> if a matching kaleo condition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoCondition fetchByKaleoCN_PK(String kaleoClassName,
		long kaleoClassPK, boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { kaleoClassName, kaleoClassPK };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_KALEOCN_PK,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_KALEOCONDITION_WHERE);

			if (kaleoClassName == null) {
				query.append(_FINDER_COLUMN_KALEOCN_PK_KALEOCLASSNAME_1);
			}
			else {
				if (kaleoClassName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_KALEOCN_PK_KALEOCLASSNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_KALEOCN_PK_KALEOCLASSNAME_2);
				}
			}

			query.append(_FINDER_COLUMN_KALEOCN_PK_KALEOCLASSPK_2);

			query.append(KaleoConditionModelImpl.ORDER_BY_JPQL);

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

				List<KaleoCondition> list = q.list();

				result = list;

				KaleoCondition kaleoCondition = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KALEOCN_PK,
						finderArgs, list);
				}
				else {
					kaleoCondition = list.get(0);

					cacheResult(kaleoCondition);

					if ((kaleoCondition.getKaleoClassName() == null) ||
							!kaleoCondition.getKaleoClassName()
											   .equals(kaleoClassName) ||
							(kaleoCondition.getKaleoClassPK() != kaleoClassPK)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KALEOCN_PK,
							finderArgs, kaleoCondition);
					}
				}

				return kaleoCondition;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KALEOCN_PK,
						finderArgs, new ArrayList<KaleoCondition>());
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (KaleoCondition)result;
			}
		}
	}

	/**
	 * Finds all the kaleo conditions.
	 *
	 * @return the kaleo conditions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoCondition> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the kaleo conditions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo conditions to return
	 * @param end the upper bound of the range of kaleo conditions to return (not inclusive)
	 * @return the range of kaleo conditions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoCondition> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the kaleo conditions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo conditions to return
	 * @param end the upper bound of the range of kaleo conditions to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of kaleo conditions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoCondition> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoCondition> list = (List<KaleoCondition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KALEOCONDITION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEOCONDITION.concat(KaleoConditionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<KaleoCondition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KaleoCondition>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<KaleoCondition>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the kaleo conditions where companyId = &#63; from the database.
	 *
	 * @param companyId the company id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (KaleoCondition kaleoCondition : findByCompanyId(companyId)) {
			remove(kaleoCondition);
		}
	}

	/**
	 * Removes the kaleo condition where kaleoClassName = &#63; and kaleoClassPK = &#63; from the database.
	 *
	 * @param kaleoClassName the kaleo class name to search with
	 * @param kaleoClassPK the kaleo class p k to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKaleoCN_PK(String kaleoClassName, long kaleoClassPK)
		throws NoSuchConditionException, SystemException {
		KaleoCondition kaleoCondition = findByKaleoCN_PK(kaleoClassName,
				kaleoClassPK);

		remove(kaleoCondition);
	}

	/**
	 * Removes all the kaleo conditions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (KaleoCondition kaleoCondition : findAll()) {
			remove(kaleoCondition);
		}
	}

	/**
	 * Counts all the kaleo conditions where companyId = &#63;.
	 *
	 * @param companyId the company id to search with
	 * @return the number of matching kaleo conditions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOCONDITION_WHERE);

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
	 * Counts all the kaleo conditions where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name to search with
	 * @param kaleoClassPK the kaleo class p k to search with
	 * @return the number of matching kaleo conditions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKaleoCN_PK(String kaleoClassName, long kaleoClassPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoClassName, kaleoClassPK };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEOCN_PK,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEOCONDITION_WHERE);

			if (kaleoClassName == null) {
				query.append(_FINDER_COLUMN_KALEOCN_PK_KALEOCLASSNAME_1);
			}
			else {
				if (kaleoClassName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_KALEOCN_PK_KALEOCLASSNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_KALEOCN_PK_KALEOCLASSNAME_2);
				}
			}

			query.append(_FINDER_COLUMN_KALEOCN_PK_KALEOCLASSPK_2);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KALEOCN_PK,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the kaleo conditions.
	 *
	 * @return the number of kaleo conditions
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

				Query q = session.createQuery(_SQL_COUNT_KALEOCONDITION);

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
	 * Initializes the kaleo condition persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.kaleo.model.KaleoCondition")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoCondition>> listenersList = new ArrayList<ModelListener<KaleoCondition>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoCondition>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(KaleoConditionImpl.class.getName());
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
	@BeanReference(type = KaleoTransitionPersistence.class)
	protected KaleoTransitionPersistence kaleoTransitionPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_KALEOCONDITION = "SELECT kaleoCondition FROM KaleoCondition kaleoCondition";
	private static final String _SQL_SELECT_KALEOCONDITION_WHERE = "SELECT kaleoCondition FROM KaleoCondition kaleoCondition WHERE ";
	private static final String _SQL_COUNT_KALEOCONDITION = "SELECT COUNT(kaleoCondition) FROM KaleoCondition kaleoCondition";
	private static final String _SQL_COUNT_KALEOCONDITION_WHERE = "SELECT COUNT(kaleoCondition) FROM KaleoCondition kaleoCondition WHERE ";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoCondition.companyId = ?";
	private static final String _FINDER_COLUMN_KALEOCN_PK_KALEOCLASSNAME_1 = "kaleoCondition.kaleoClassName IS NULL AND ";
	private static final String _FINDER_COLUMN_KALEOCN_PK_KALEOCLASSNAME_2 = "kaleoCondition.kaleoClassName = ? AND ";
	private static final String _FINDER_COLUMN_KALEOCN_PK_KALEOCLASSNAME_3 = "(kaleoCondition.kaleoClassName IS NULL OR kaleoCondition.kaleoClassName = ?) AND ";
	private static final String _FINDER_COLUMN_KALEOCN_PK_KALEOCLASSPK_2 = "kaleoCondition.kaleoClassPK = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoCondition.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoCondition exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoCondition exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(KaleoConditionPersistenceImpl.class);
}