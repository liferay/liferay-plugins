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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
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
 * Never modify or reference this class directly. Always use {@link KaleoNotificationUtil} to access the kaleo notification persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
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
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoNotificationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KALEODEFINITIONID = new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEODEFINITIONID = new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByKaleoDefinitionId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KNI_ET = new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByKNI_ET",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KNI_ET = new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByKNI_ET",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the kaleo notification in the entity cache if it is enabled.
	 *
	 * @param kaleoNotification the kaleo notification to cache
	 */
	public void cacheResult(KaleoNotification kaleoNotification) {
		EntityCacheUtil.putResult(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationImpl.class, kaleoNotification.getPrimaryKey(),
			kaleoNotification);
	}

	/**
	 * Caches the kaleo notifications in the entity cache if it is enabled.
	 *
	 * @param kaleoNotifications the kaleo notifications to cache
	 */
	public void cacheResult(List<KaleoNotification> kaleoNotifications) {
		for (KaleoNotification kaleoNotification : kaleoNotifications) {
			if (EntityCacheUtil.getResult(
						KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
						KaleoNotificationImpl.class,
						kaleoNotification.getPrimaryKey(), this) == null) {
				cacheResult(kaleoNotification);
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
	public void clearCache() {
		CacheRegistryUtil.clear(KaleoNotificationImpl.class.getName());
		EntityCacheUtil.clearCache(KaleoNotificationImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the kaleo notification.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(KaleoNotification kaleoNotification) {
		EntityCacheUtil.removeResult(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationImpl.class, kaleoNotification.getPrimaryKey());
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
	 * @param primaryKey the primary key of the kaleo notification to remove
	 * @return the kaleo notification that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo notification with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNotification remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the kaleo notification with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoNotificationId the primary key of the kaleo notification to remove
	 * @return the kaleo notification that was removed
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException if a kaleo notification with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNotification remove(long kaleoNotificationId)
		throws NoSuchNotificationException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoNotification kaleoNotification = (KaleoNotification)session.get(KaleoNotificationImpl.class,
					new Long(kaleoNotificationId));

			if (kaleoNotification == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						kaleoNotificationId);
				}

				throw new NoSuchNotificationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoNotificationId);
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

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationImpl.class, kaleoNotification.getPrimaryKey());

		return kaleoNotification;
	}

	public KaleoNotification updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoNotification kaleoNotification,
		boolean merge) throws SystemException {
		kaleoNotification = toUnwrappedModel(kaleoNotification);

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

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

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
		kaleoNotificationImpl.setKaleoDefinitionId(kaleoNotification.getKaleoDefinitionId());
		kaleoNotificationImpl.setKaleoNodeId(kaleoNotification.getKaleoNodeId());
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
	 * Finds the kaleo notification with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo notification to find
	 * @return the kaleo notification
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo notification with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNotification findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the kaleo notification with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchNotificationException} if it could not be found.
	 *
	 * @param kaleoNotificationId the primary key of the kaleo notification to find
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
	 * Finds the kaleo notification with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo notification to find
	 * @return the kaleo notification, or <code>null</code> if a kaleo notification with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNotification fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the kaleo notification with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoNotificationId the primary key of the kaleo notification to find
	 * @return the kaleo notification, or <code>null</code> if a kaleo notification with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNotification fetchByPrimaryKey(long kaleoNotificationId)
		throws SystemException {
		KaleoNotification kaleoNotification = (KaleoNotification)EntityCacheUtil.getResult(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
				KaleoNotificationImpl.class, kaleoNotificationId, this);

		if (kaleoNotification == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoNotification = (KaleoNotification)session.get(KaleoNotificationImpl.class,
						new Long(kaleoNotificationId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (kaleoNotification != null) {
					cacheResult(kaleoNotification);
				}

				closeSession(session);
			}
		}

		return kaleoNotification;
	}

	/**
	 * Finds all the kaleo notifications where companyId = &#63;.
	 *
	 * @param companyId the company id to search with
	 * @return the matching kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNotification> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Finds a range of all the kaleo notifications where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param start the lower bound of the range of kaleo notifications to return
	 * @param end the upper bound of the range of kaleo notifications to return (not inclusive)
	 * @return the range of matching kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNotification> findByCompanyId(long companyId, int start,
		int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the kaleo notifications where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param start the lower bound of the range of kaleo notifications to return
	 * @param end the upper bound of the range of kaleo notifications to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNotification> findByCompanyId(long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoNotification> list = (List<KaleoNotification>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

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
					list = new ArrayList<KaleoNotification>();
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
	 * Finds the first kaleo notification in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param orderByComparator the comparator to order the set by
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
	 * Finds the last kaleo notification in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param orderByComparator the comparator to order the set by
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
	 * Finds the kaleo notifications before and after the current kaleo notification in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoNotificationId the primary key of the current kaleo notification
	 * @param companyId the company id to search with
	 * @param orderByComparator the comparator to order the set by
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
			query.append(KaleoNotificationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoNotification);

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
	 * Finds all the kaleo notifications where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition id to search with
	 * @return the matching kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNotification> findByKaleoDefinitionId(
		long kaleoDefinitionId) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the kaleo notifications where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition id to search with
	 * @param start the lower bound of the range of kaleo notifications to return
	 * @param end the upper bound of the range of kaleo notifications to return (not inclusive)
	 * @return the range of matching kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNotification> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the kaleo notifications where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition id to search with
	 * @param start the lower bound of the range of kaleo notifications to return
	 * @param end the upper bound of the range of kaleo notifications to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNotification> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoDefinitionId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoNotification> list = (List<KaleoNotification>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

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
					list = new ArrayList<KaleoNotification>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first kaleo notification in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition id to search with
	 * @param orderByComparator the comparator to order the set by
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
	 * Finds the last kaleo notification in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition id to search with
	 * @param orderByComparator the comparator to order the set by
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
	 * Finds the kaleo notifications before and after the current kaleo notification in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoNotificationId the primary key of the current kaleo notification
	 * @param kaleoDefinitionId the kaleo definition id to search with
	 * @param orderByComparator the comparator to order the set by
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
			query.append(KaleoNotificationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoNotification);

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
	 * Finds all the kaleo notifications where kaleoNodeId = &#63; and executionType = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node id to search with
	 * @param executionType the execution type to search with
	 * @return the matching kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNotification> findByKNI_ET(long kaleoNodeId,
		String executionType) throws SystemException {
		return findByKNI_ET(kaleoNodeId, executionType, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the kaleo notifications where kaleoNodeId = &#63; and executionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoNodeId the kaleo node id to search with
	 * @param executionType the execution type to search with
	 * @param start the lower bound of the range of kaleo notifications to return
	 * @param end the upper bound of the range of kaleo notifications to return (not inclusive)
	 * @return the range of matching kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNotification> findByKNI_ET(long kaleoNodeId,
		String executionType, int start, int end) throws SystemException {
		return findByKNI_ET(kaleoNodeId, executionType, start, end, null);
	}

	/**
	 * Finds an ordered range of all the kaleo notifications where kaleoNodeId = &#63; and executionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoNodeId the kaleo node id to search with
	 * @param executionType the execution type to search with
	 * @param start the lower bound of the range of kaleo notifications to return
	 * @param end the upper bound of the range of kaleo notifications to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNotification> findByKNI_ET(long kaleoNodeId,
		String executionType, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoNodeId, executionType,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoNotification> list = (List<KaleoNotification>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KNI_ET,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (orderByComparator != null) {
					query = new StringBundler(4 +
							(orderByComparator.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(4);
				}

				query.append(_SQL_SELECT_KALEONOTIFICATION_WHERE);

				query.append(_FINDER_COLUMN_KNI_ET_KALEONODEID_2);

				if (executionType == null) {
					query.append(_FINDER_COLUMN_KNI_ET_EXECUTIONTYPE_1);
				}
				else {
					if (executionType.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_KNI_ET_EXECUTIONTYPE_3);
					}
					else {
						query.append(_FINDER_COLUMN_KNI_ET_EXECUTIONTYPE_2);
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

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNodeId);

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
					list = new ArrayList<KaleoNotification>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KNI_ET,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first kaleo notification in the ordered set where kaleoNodeId = &#63; and executionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoNodeId the kaleo node id to search with
	 * @param executionType the execution type to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching kaleo notification
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException if a matching kaleo notification could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNotification findByKNI_ET_First(long kaleoNodeId,
		String executionType, OrderByComparator orderByComparator)
		throws NoSuchNotificationException, SystemException {
		List<KaleoNotification> list = findByKNI_ET(kaleoNodeId, executionType,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoNodeId=");
			msg.append(kaleoNodeId);

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
	 * Finds the last kaleo notification in the ordered set where kaleoNodeId = &#63; and executionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoNodeId the kaleo node id to search with
	 * @param executionType the execution type to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching kaleo notification
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException if a matching kaleo notification could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNotification findByKNI_ET_Last(long kaleoNodeId,
		String executionType, OrderByComparator orderByComparator)
		throws NoSuchNotificationException, SystemException {
		int count = countByKNI_ET(kaleoNodeId, executionType);

		List<KaleoNotification> list = findByKNI_ET(kaleoNodeId, executionType,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoNodeId=");
			msg.append(kaleoNodeId);

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
	 * Finds the kaleo notifications before and after the current kaleo notification in the ordered set where kaleoNodeId = &#63; and executionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoNotificationId the primary key of the current kaleo notification
	 * @param kaleoNodeId the kaleo node id to search with
	 * @param executionType the execution type to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next kaleo notification
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchNotificationException if a kaleo notification with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoNotification[] findByKNI_ET_PrevAndNext(
		long kaleoNotificationId, long kaleoNodeId, String executionType,
		OrderByComparator orderByComparator)
		throws NoSuchNotificationException, SystemException {
		KaleoNotification kaleoNotification = findByPrimaryKey(kaleoNotificationId);

		Session session = null;

		try {
			session = openSession();

			KaleoNotification[] array = new KaleoNotificationImpl[3];

			array[0] = getByKNI_ET_PrevAndNext(session, kaleoNotification,
					kaleoNodeId, executionType, orderByComparator, true);

			array[1] = kaleoNotification;

			array[2] = getByKNI_ET_PrevAndNext(session, kaleoNotification,
					kaleoNodeId, executionType, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoNotification getByKNI_ET_PrevAndNext(Session session,
		KaleoNotification kaleoNotification, long kaleoNodeId,
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

		query.append(_SQL_SELECT_KALEONOTIFICATION_WHERE);

		query.append(_FINDER_COLUMN_KNI_ET_KALEONODEID_2);

		if (executionType == null) {
			query.append(_FINDER_COLUMN_KNI_ET_EXECUTIONTYPE_1);
		}
		else {
			if (executionType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KNI_ET_EXECUTIONTYPE_3);
			}
			else {
				query.append(_FINDER_COLUMN_KNI_ET_EXECUTIONTYPE_2);
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
			query.append(KaleoNotificationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoNodeId);

		if (executionType != null) {
			qPos.add(executionType);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoNotification);

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
	 * Finds all the kaleo notifications.
	 *
	 * @return the kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNotification> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the kaleo notifications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo notifications to return
	 * @param end the upper bound of the range of kaleo notifications to return (not inclusive)
	 * @return the range of kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNotification> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the kaleo notifications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo notifications to return
	 * @param end the upper bound of the range of kaleo notifications to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoNotification> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoNotification> list = (List<KaleoNotification>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

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
					list = new ArrayList<KaleoNotification>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the kaleo notifications where companyId = &#63; from the database.
	 *
	 * @param companyId the company id to search with
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
	 * @param kaleoDefinitionId the kaleo definition id to search with
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
	 * Removes all the kaleo notifications where kaleoNodeId = &#63; and executionType = &#63; from the database.
	 *
	 * @param kaleoNodeId the kaleo node id to search with
	 * @param executionType the execution type to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKNI_ET(long kaleoNodeId, String executionType)
		throws SystemException {
		for (KaleoNotification kaleoNotification : findByKNI_ET(kaleoNodeId,
				executionType)) {
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
	 * Counts all the kaleo notifications where companyId = &#63;.
	 *
	 * @param companyId the company id to search with
	 * @return the number of matching kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_KALEONOTIFICATION_WHERE);

				query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

				String sql = query.toString();

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
	 * Counts all the kaleo notifications where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition id to search with
	 * @return the number of matching kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoDefinitionId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_KALEONOTIFICATION_WHERE);

				query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

				String sql = query.toString();

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
	 * Counts all the kaleo notifications where kaleoNodeId = &#63; and executionType = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node id to search with
	 * @param executionType the execution type to search with
	 * @return the number of matching kaleo notifications
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKNI_ET(long kaleoNodeId, String executionType)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoNodeId, executionType };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KNI_ET,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_COUNT_KALEONOTIFICATION_WHERE);

				query.append(_FINDER_COLUMN_KNI_ET_KALEONODEID_2);

				if (executionType == null) {
					query.append(_FINDER_COLUMN_KNI_ET_EXECUTIONTYPE_1);
				}
				else {
					if (executionType.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_KNI_ET_EXECUTIONTYPE_3);
					}
					else {
						query.append(_FINDER_COLUMN_KNI_ET_EXECUTIONTYPE_2);
					}
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNodeId);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KNI_ET,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the kaleo notifications.
	 *
	 * @return the number of kaleo notifications
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Gets all the kaleo notification recipients associated with the kaleo notification.
	 *
	 * @param pk the primary key of the kaleo notification to get the associated kaleo notification recipients for
	 * @return the kaleo notification recipients associated with the kaleo notification
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> getKaleoNotificationRecipients(
		long pk) throws SystemException {
		return getKaleoNotificationRecipients(pk, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	/**
	 * Gets a range of all the kaleo notification recipients associated with the kaleo notification.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the kaleo notification to get the associated kaleo notification recipients for
	 * @param start the lower bound of the range of kaleo notifications to return
	 * @param end the upper bound of the range of kaleo notifications to return (not inclusive)
	 * @return the range of kaleo notification recipients associated with the kaleo notification
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> getKaleoNotificationRecipients(
		long pk, int start, int end) throws SystemException {
		return getKaleoNotificationRecipients(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_KALEONOTIFICATIONRECIPIENTS = new FinderPath(com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientModelImpl.FINDER_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.service.persistence.KaleoNotificationRecipientPersistenceImpl.FINDER_CLASS_NAME_LIST,
			"getKaleoNotificationRecipients",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	/**
	 * Gets an ordered range of all the kaleo notification recipients associated with the kaleo notification.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the kaleo notification to get the associated kaleo notification recipients for
	 * @param start the lower bound of the range of kaleo notifications to return
	 * @param end the upper bound of the range of kaleo notifications to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of kaleo notification recipients associated with the kaleo notification
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> getKaleoNotificationRecipients(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				pk, String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

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
					list = new ArrayList<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient>();
				}

				kaleoNotificationRecipientPersistence.cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_GET_KALEONOTIFICATIONRECIPIENTS,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_KALEONOTIFICATIONRECIPIENTS_SIZE =
		new FinderPath(com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientModelImpl.FINDER_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.service.persistence.KaleoNotificationRecipientPersistenceImpl.FINDER_CLASS_NAME_LIST,
			"getKaleoNotificationRecipientsSize",
			new String[] { Long.class.getName() });

	/**
	 * Gets the number of kaleo notification recipients associated with the kaleo notification.
	 *
	 * @param pk the primary key of the kaleo notification to get the number of associated kaleo notification recipients for
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
			com.liferay.portal.workflow.kaleo.service.persistence.KaleoNotificationRecipientPersistenceImpl.FINDER_CLASS_NAME_LIST,
			"containsKaleoNotificationRecipient",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Determines if the kaleo notification recipient is associated with the kaleo notification.
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
	 * Determines if the kaleo notification has any kaleo notification recipients associated with it.
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

		containsKaleoNotificationRecipient = new ContainsKaleoNotificationRecipient(this);
	}

	public void destroy() {
		EntityCacheUtil.removeCache(KaleoNotificationImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = KaleoActionPersistence.class)
	protected KaleoActionPersistence kaleoActionPersistence;
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
	protected ContainsKaleoNotificationRecipient containsKaleoNotificationRecipient;

	protected class ContainsKaleoNotificationRecipient {
		protected ContainsKaleoNotificationRecipient(
			KaleoNotificationPersistenceImpl persistenceImpl) {
			super();

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
	private static final String _FINDER_COLUMN_KNI_ET_KALEONODEID_2 = "kaleoNotification.kaleoNodeId = ? AND ";
	private static final String _FINDER_COLUMN_KNI_ET_EXECUTIONTYPE_1 = "kaleoNotification.executionType IS NULL";
	private static final String _FINDER_COLUMN_KNI_ET_EXECUTIONTYPE_2 = "kaleoNotification.executionType = ?";
	private static final String _FINDER_COLUMN_KNI_ET_EXECUTIONTYPE_3 = "(kaleoNotification.executionType IS NULL OR kaleoNotification.executionType = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoNotification.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoNotification exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoNotification exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(KaleoNotificationPersistenceImpl.class);
}