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
import com.liferay.portal.workflow.kaleo.NoSuchActionException;
import com.liferay.portal.workflow.kaleo.model.KaleoAction;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoActionImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoActionModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the kaleo action service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoActionPersistence
 * @see KaleoActionUtil
 * @generated
 */
public class KaleoActionPersistenceImpl extends BasePersistenceImpl<KaleoAction>
	implements KaleoActionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KaleoActionUtil} to access the kaleo action persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoActionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionModelImpl.FINDER_CACHE_ENABLED, KaleoActionImpl.class,
			FINDER_CLASS_NAME_LIST, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KALEODEFINITIONID = new FinderPath(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionModelImpl.FINDER_CACHE_ENABLED, KaleoActionImpl.class,
			FINDER_CLASS_NAME_LIST, "findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEODEFINITIONID = new FinderPath(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByKaleoDefinitionId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KCN_KCPK_ET = new FinderPath(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionModelImpl.FINDER_CACHE_ENABLED, KaleoActionImpl.class,
			FINDER_CLASS_NAME_LIST, "findByKCN_KCPK_ET",
			new String[] {
				String.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KCN_KCPK_ET = new FinderPath(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByKCN_KCPK_ET",
			new String[] {
				String.class.getName(), Long.class.getName(),
				String.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionModelImpl.FINDER_CACHE_ENABLED, KaleoActionImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the kaleo action in the entity cache if it is enabled.
	 *
	 * @param kaleoAction the kaleo action
	 */
	public void cacheResult(KaleoAction kaleoAction) {
		EntityCacheUtil.putResult(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionImpl.class, kaleoAction.getPrimaryKey(), kaleoAction);

		kaleoAction.resetOriginalValues();
	}

	/**
	 * Caches the kaleo actions in the entity cache if it is enabled.
	 *
	 * @param kaleoActions the kaleo actions
	 */
	public void cacheResult(List<KaleoAction> kaleoActions) {
		for (KaleoAction kaleoAction : kaleoActions) {
			if (EntityCacheUtil.getResult(
						KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
						KaleoActionImpl.class, kaleoAction.getPrimaryKey(), this) == null) {
				cacheResult(kaleoAction);
			}
		}
	}

	/**
	 * Clears the cache for all kaleo actions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(KaleoActionImpl.class.getName());
		}

		EntityCacheUtil.clearCache(KaleoActionImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the kaleo action.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KaleoAction kaleoAction) {
		EntityCacheUtil.removeResult(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionImpl.class, kaleoAction.getPrimaryKey());
	}

	/**
	 * Creates a new kaleo action with the primary key. Does not add the kaleo action to the database.
	 *
	 * @param kaleoActionId the primary key for the new kaleo action
	 * @return the new kaleo action
	 */
	public KaleoAction create(long kaleoActionId) {
		KaleoAction kaleoAction = new KaleoActionImpl();

		kaleoAction.setNew(true);
		kaleoAction.setPrimaryKey(kaleoActionId);

		return kaleoAction;
	}

	/**
	 * Removes the kaleo action with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo action
	 * @return the kaleo action that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoAction remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the kaleo action with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoActionId the primary key of the kaleo action
	 * @return the kaleo action that was removed
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchActionException if a kaleo action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoAction remove(long kaleoActionId)
		throws NoSuchActionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoAction kaleoAction = (KaleoAction)session.get(KaleoActionImpl.class,
					Long.valueOf(kaleoActionId));

			if (kaleoAction == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + kaleoActionId);
				}

				throw new NoSuchActionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoActionId);
			}

			return kaleoActionPersistence.remove(kaleoAction);
		}
		catch (NoSuchActionException nsee) {
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
	 * Removes the kaleo action from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoAction the kaleo action
	 * @return the kaleo action that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoAction remove(KaleoAction kaleoAction)
		throws SystemException {
		return super.remove(kaleoAction);
	}

	@Override
	protected KaleoAction removeImpl(KaleoAction kaleoAction)
		throws SystemException {
		kaleoAction = toUnwrappedModel(kaleoAction);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, kaleoAction);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionImpl.class, kaleoAction.getPrimaryKey());

		return kaleoAction;
	}

	@Override
	public KaleoAction updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoAction kaleoAction,
		boolean merge) throws SystemException {
		kaleoAction = toUnwrappedModel(kaleoAction);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kaleoAction, merge);

			kaleoAction.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionImpl.class, kaleoAction.getPrimaryKey(), kaleoAction);

		return kaleoAction;
	}

	protected KaleoAction toUnwrappedModel(KaleoAction kaleoAction) {
		if (kaleoAction instanceof KaleoActionImpl) {
			return kaleoAction;
		}

		KaleoActionImpl kaleoActionImpl = new KaleoActionImpl();

		kaleoActionImpl.setNew(kaleoAction.isNew());
		kaleoActionImpl.setPrimaryKey(kaleoAction.getPrimaryKey());

		kaleoActionImpl.setKaleoActionId(kaleoAction.getKaleoActionId());
		kaleoActionImpl.setGroupId(kaleoAction.getGroupId());
		kaleoActionImpl.setCompanyId(kaleoAction.getCompanyId());
		kaleoActionImpl.setUserId(kaleoAction.getUserId());
		kaleoActionImpl.setUserName(kaleoAction.getUserName());
		kaleoActionImpl.setCreateDate(kaleoAction.getCreateDate());
		kaleoActionImpl.setModifiedDate(kaleoAction.getModifiedDate());
		kaleoActionImpl.setKaleoClassName(kaleoAction.getKaleoClassName());
		kaleoActionImpl.setKaleoClassPK(kaleoAction.getKaleoClassPK());
		kaleoActionImpl.setKaleoDefinitionId(kaleoAction.getKaleoDefinitionId());
		kaleoActionImpl.setKaleoNodeName(kaleoAction.getKaleoNodeName());
		kaleoActionImpl.setName(kaleoAction.getName());
		kaleoActionImpl.setDescription(kaleoAction.getDescription());
		kaleoActionImpl.setExecutionType(kaleoAction.getExecutionType());
		kaleoActionImpl.setScript(kaleoAction.getScript());
		kaleoActionImpl.setScriptLanguage(kaleoAction.getScriptLanguage());
		kaleoActionImpl.setPriority(kaleoAction.getPriority());

		return kaleoActionImpl;
	}

	/**
	 * Returns the kaleo action with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo action
	 * @return the kaleo action
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoAction findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the kaleo action with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchActionException} if it could not be found.
	 *
	 * @param kaleoActionId the primary key of the kaleo action
	 * @return the kaleo action
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchActionException if a kaleo action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoAction findByPrimaryKey(long kaleoActionId)
		throws NoSuchActionException, SystemException {
		KaleoAction kaleoAction = fetchByPrimaryKey(kaleoActionId);

		if (kaleoAction == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + kaleoActionId);
			}

			throw new NoSuchActionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				kaleoActionId);
		}

		return kaleoAction;
	}

	/**
	 * Returns the kaleo action with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo action
	 * @return the kaleo action, or <code>null</code> if a kaleo action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoAction fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the kaleo action with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoActionId the primary key of the kaleo action
	 * @return the kaleo action, or <code>null</code> if a kaleo action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoAction fetchByPrimaryKey(long kaleoActionId)
		throws SystemException {
		KaleoAction kaleoAction = (KaleoAction)EntityCacheUtil.getResult(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
				KaleoActionImpl.class, kaleoActionId, this);

		if (kaleoAction == _nullKaleoAction) {
			return null;
		}

		if (kaleoAction == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				kaleoAction = (KaleoAction)session.get(KaleoActionImpl.class,
						Long.valueOf(kaleoActionId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (kaleoAction != null) {
					cacheResult(kaleoAction);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
						KaleoActionImpl.class, kaleoActionId, _nullKaleoAction);
				}

				closeSession(session);
			}
		}

		return kaleoAction;
	}

	/**
	 * Returns all the kaleo actions where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching kaleo actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoAction> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the kaleo actions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo actions
	 * @param end the upper bound of the range of kaleo actions (not inclusive)
	 * @return the range of matching kaleo actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoAction> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo actions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo actions
	 * @param end the upper bound of the range of kaleo actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoAction> findByCompanyId(long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoAction> list = (List<KaleoAction>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
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

			query.append(_SQL_SELECT_KALEOACTION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<KaleoAction>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first kaleo action in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo action
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchActionException if a matching kaleo action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoAction findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchActionException, SystemException {
		List<KaleoAction> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchActionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo action in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo action
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchActionException if a matching kaleo action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoAction findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchActionException, SystemException {
		int count = countByCompanyId(companyId);

		List<KaleoAction> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchActionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo actions before and after the current kaleo action in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoActionId the primary key of the current kaleo action
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo action
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchActionException if a kaleo action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoAction[] findByCompanyId_PrevAndNext(long kaleoActionId,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchActionException, SystemException {
		KaleoAction kaleoAction = findByPrimaryKey(kaleoActionId);

		Session session = null;

		try {
			session = openSession();

			KaleoAction[] array = new KaleoActionImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, kaleoAction,
					companyId, orderByComparator, true);

			array[1] = kaleoAction;

			array[2] = getByCompanyId_PrevAndNext(session, kaleoAction,
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

	protected KaleoAction getByCompanyId_PrevAndNext(Session session,
		KaleoAction kaleoAction, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOACTION_WHERE);

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
			query.append(KaleoActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo actions where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the matching kaleo actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoAction> findByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo actions where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo actions
	 * @param end the upper bound of the range of kaleo actions (not inclusive)
	 * @return the range of matching kaleo actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoAction> findByKaleoDefinitionId(long kaleoDefinitionId,
		int start, int end) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo actions where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo actions
	 * @param end the upper bound of the range of kaleo actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoAction> findByKaleoDefinitionId(long kaleoDefinitionId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoDefinitionId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoAction> list = (List<KaleoAction>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
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

			query.append(_SQL_SELECT_KALEOACTION_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				list = (List<KaleoAction>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first kaleo action in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo action
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchActionException if a matching kaleo action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoAction findByKaleoDefinitionId_First(long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchActionException, SystemException {
		List<KaleoAction> list = findByKaleoDefinitionId(kaleoDefinitionId, 0,
				1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchActionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo action in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo action
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchActionException if a matching kaleo action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoAction findByKaleoDefinitionId_Last(long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchActionException, SystemException {
		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		List<KaleoAction> list = findByKaleoDefinitionId(kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchActionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo actions before and after the current kaleo action in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoActionId the primary key of the current kaleo action
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo action
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchActionException if a kaleo action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoAction[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoActionId, long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchActionException, SystemException {
		KaleoAction kaleoAction = findByPrimaryKey(kaleoActionId);

		Session session = null;

		try {
			session = openSession();

			KaleoAction[] array = new KaleoActionImpl[3];

			array[0] = getByKaleoDefinitionId_PrevAndNext(session, kaleoAction,
					kaleoDefinitionId, orderByComparator, true);

			array[1] = kaleoAction;

			array[2] = getByKaleoDefinitionId_PrevAndNext(session, kaleoAction,
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

	protected KaleoAction getByKaleoDefinitionId_PrevAndNext(Session session,
		KaleoAction kaleoAction, long kaleoDefinitionId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOACTION_WHERE);

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
			query.append(KaleoActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo actions where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @return the matching kaleo actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoAction> findByKCN_KCPK_ET(String kaleoClassName,
		long kaleoClassPK, String executionType) throws SystemException {
		return findByKCN_KCPK_ET(kaleoClassName, kaleoClassPK, executionType,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo actions where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @param start the lower bound of the range of kaleo actions
	 * @param end the upper bound of the range of kaleo actions (not inclusive)
	 * @return the range of matching kaleo actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoAction> findByKCN_KCPK_ET(String kaleoClassName,
		long kaleoClassPK, String executionType, int start, int end)
		throws SystemException {
		return findByKCN_KCPK_ET(kaleoClassName, kaleoClassPK, executionType,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo actions where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @param start the lower bound of the range of kaleo actions
	 * @param end the upper bound of the range of kaleo actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoAction> findByKCN_KCPK_ET(String kaleoClassName,
		long kaleoClassPK, String executionType, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoClassName, kaleoClassPK, executionType,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoAction> list = (List<KaleoAction>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KCN_KCPK_ET,
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

			query.append(_SQL_SELECT_KALEOACTION_WHERE);

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
				query.append(KaleoActionModelImpl.ORDER_BY_JPQL);
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

				list = (List<KaleoAction>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_KCN_KCPK_ET,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KCN_KCPK_ET,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first kaleo action in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo action
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchActionException if a matching kaleo action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoAction findByKCN_KCPK_ET_First(String kaleoClassName,
		long kaleoClassPK, String executionType,
		OrderByComparator orderByComparator)
		throws NoSuchActionException, SystemException {
		List<KaleoAction> list = findByKCN_KCPK_ET(kaleoClassName,
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

			throw new NoSuchActionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last kaleo action in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo action
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchActionException if a matching kaleo action could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoAction findByKCN_KCPK_ET_Last(String kaleoClassName,
		long kaleoClassPK, String executionType,
		OrderByComparator orderByComparator)
		throws NoSuchActionException, SystemException {
		int count = countByKCN_KCPK_ET(kaleoClassName, kaleoClassPK,
				executionType);

		List<KaleoAction> list = findByKCN_KCPK_ET(kaleoClassName,
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

			throw new NoSuchActionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the kaleo actions before and after the current kaleo action in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoActionId the primary key of the current kaleo action
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo action
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchActionException if a kaleo action with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoAction[] findByKCN_KCPK_ET_PrevAndNext(long kaleoActionId,
		String kaleoClassName, long kaleoClassPK, String executionType,
		OrderByComparator orderByComparator)
		throws NoSuchActionException, SystemException {
		KaleoAction kaleoAction = findByPrimaryKey(kaleoActionId);

		Session session = null;

		try {
			session = openSession();

			KaleoAction[] array = new KaleoActionImpl[3];

			array[0] = getByKCN_KCPK_ET_PrevAndNext(session, kaleoAction,
					kaleoClassName, kaleoClassPK, executionType,
					orderByComparator, true);

			array[1] = kaleoAction;

			array[2] = getByKCN_KCPK_ET_PrevAndNext(session, kaleoAction,
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

	protected KaleoAction getByKCN_KCPK_ET_PrevAndNext(Session session,
		KaleoAction kaleoAction, String kaleoClassName, long kaleoClassPK,
		String executionType, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOACTION_WHERE);

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
			query.append(KaleoActionModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByValues(kaleoAction);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoAction> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo actions.
	 *
	 * @return the kaleo actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoAction> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo actions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo actions
	 * @param end the upper bound of the range of kaleo actions (not inclusive)
	 * @return the range of kaleo actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoAction> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo actions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo actions
	 * @param end the upper bound of the range of kaleo actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo actions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoAction> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoAction> list = (List<KaleoAction>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KALEOACTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEOACTION.concat(KaleoActionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<KaleoAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KaleoAction>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the kaleo actions where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (KaleoAction kaleoAction : findByCompanyId(companyId)) {
			kaleoActionPersistence.remove(kaleoAction);
		}
	}

	/**
	 * Removes all the kaleo actions where kaleoDefinitionId = &#63; from the database.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		for (KaleoAction kaleoAction : findByKaleoDefinitionId(
				kaleoDefinitionId)) {
			kaleoActionPersistence.remove(kaleoAction);
		}
	}

	/**
	 * Removes all the kaleo actions where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63; from the database.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKCN_KCPK_ET(String kaleoClassName, long kaleoClassPK,
		String executionType) throws SystemException {
		for (KaleoAction kaleoAction : findByKCN_KCPK_ET(kaleoClassName,
				kaleoClassPK, executionType)) {
			kaleoActionPersistence.remove(kaleoAction);
		}
	}

	/**
	 * Removes all the kaleo actions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (KaleoAction kaleoAction : findAll()) {
			kaleoActionPersistence.remove(kaleoAction);
		}
	}

	/**
	 * Returns the number of kaleo actions where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching kaleo actions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOACTION_WHERE);

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
	 * Returns the number of kaleo actions where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the number of matching kaleo actions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoDefinitionId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOACTION_WHERE);

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
	 * Returns the number of kaleo actions where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @return the number of matching kaleo actions
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

			query.append(_SQL_COUNT_KALEOACTION_WHERE);

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
	 * Returns the number of kaleo actions.
	 *
	 * @return the number of kaleo actions
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

				Query q = session.createQuery(_SQL_COUNT_KALEOACTION);

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
	 * Initializes the kaleo action persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.kaleo.model.KaleoAction")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoAction>> listenersList = new ArrayList<ModelListener<KaleoAction>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoAction>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(KaleoActionImpl.class.getName());
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
	private static final String _SQL_SELECT_KALEOACTION = "SELECT kaleoAction FROM KaleoAction kaleoAction";
	private static final String _SQL_SELECT_KALEOACTION_WHERE = "SELECT kaleoAction FROM KaleoAction kaleoAction WHERE ";
	private static final String _SQL_COUNT_KALEOACTION = "SELECT COUNT(kaleoAction) FROM KaleoAction kaleoAction";
	private static final String _SQL_COUNT_KALEOACTION_WHERE = "SELECT COUNT(kaleoAction) FROM KaleoAction kaleoAction WHERE ";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoAction.companyId = ?";
	private static final String _FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2 =
		"kaleoAction.kaleoDefinitionId = ?";
	private static final String _FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_1 = "kaleoAction.kaleoClassName IS NULL AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_2 = "kaleoAction.kaleoClassName = ? AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_3 = "(kaleoAction.kaleoClassName IS NULL OR kaleoAction.kaleoClassName = ?) AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSPK_2 = "kaleoAction.kaleoClassPK = ? AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_1 = "kaleoAction.executionType IS NULL";
	private static final String _FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_2 = "kaleoAction.executionType = ?";
	private static final String _FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_3 = "(kaleoAction.executionType IS NULL OR kaleoAction.executionType = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoAction.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoAction exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoAction exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(KaleoActionPersistenceImpl.class);
	private static KaleoAction _nullKaleoAction = new KaleoActionImpl() {
			public Object clone() {
				return this;
			}

			public CacheModel<KaleoAction> toCacheModel() {
				return _nullKaleoActionCacheModel;
			}
		};

	private static CacheModel<KaleoAction> _nullKaleoActionCacheModel = new CacheModel<KaleoAction>() {
			public KaleoAction toEntityModel() {
				return _nullKaleoAction;
			}
		};
}