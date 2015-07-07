/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.NoSuchNotificationException;
import com.liferay.portal.workflow.kaleo.model.KaleoNotification;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationModelImpl;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoNotificationPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the kaleo notification service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoNotificationPersistence
 * @see com.liferay.portal.workflow.kaleo.service.persistence.KaleoNotificationUtil
 * @generated
 */
@ProviderType
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
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED,
			KaleoNotificationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED,
			KaleoNotificationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED,
			KaleoNotificationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
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

	/**
	 * Returns all the kaleo notifications where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching kaleo notifications
	 */
	@Override
	public List<KaleoNotification> findByCompanyId(long companyId) {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the kaleo notifications where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo notifications
	 * @param end the upper bound of the range of kaleo notifications (not inclusive)
	 * @return the range of matching kaleo notifications
	 */
	@Override
	public List<KaleoNotification> findByCompanyId(long companyId, int start,
		int end) {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo notifications where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo notifications
	 * @param end the upper bound of the range of kaleo notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo notifications
	 */
	@Override
	public List<KaleoNotification> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<KaleoNotification> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId, start, end, orderByComparator };
		}

		List<KaleoNotification> list = (List<KaleoNotification>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoNotification kaleoNotification : list) {
				if ((companyId != kaleoNotification.getCompanyId())) {
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

			query.append(_SQL_SELECT_KALEONOTIFICATION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoNotificationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<KaleoNotification>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoNotification>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first kaleo notification in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo notification
	 * @throws NoSuchNotificationException if a matching kaleo notification could not be found
	 */
	@Override
	public KaleoNotification findByCompanyId_First(long companyId,
		OrderByComparator<KaleoNotification> orderByComparator)
		throws NoSuchNotificationException {
		KaleoNotification kaleoNotification = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (kaleoNotification != null) {
			return kaleoNotification;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchNotificationException(msg.toString());
	}

	/**
	 * Returns the first kaleo notification in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo notification, or <code>null</code> if a matching kaleo notification could not be found
	 */
	@Override
	public KaleoNotification fetchByCompanyId_First(long companyId,
		OrderByComparator<KaleoNotification> orderByComparator) {
		List<KaleoNotification> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo notification in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo notification
	 * @throws NoSuchNotificationException if a matching kaleo notification could not be found
	 */
	@Override
	public KaleoNotification findByCompanyId_Last(long companyId,
		OrderByComparator<KaleoNotification> orderByComparator)
		throws NoSuchNotificationException {
		KaleoNotification kaleoNotification = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (kaleoNotification != null) {
			return kaleoNotification;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchNotificationException(msg.toString());
	}

	/**
	 * Returns the last kaleo notification in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo notification, or <code>null</code> if a matching kaleo notification could not be found
	 */
	@Override
	public KaleoNotification fetchByCompanyId_Last(long companyId,
		OrderByComparator<KaleoNotification> orderByComparator) {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<KaleoNotification> list = findByCompanyId(companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo notifications before and after the current kaleo notification in the ordered set where companyId = &#63;.
	 *
	 * @param kaleoNotificationId the primary key of the current kaleo notification
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo notification
	 * @throws NoSuchNotificationException if a kaleo notification with the primary key could not be found
	 */
	@Override
	public KaleoNotification[] findByCompanyId_PrevAndNext(
		long kaleoNotificationId, long companyId,
		OrderByComparator<KaleoNotification> orderByComparator)
		throws NoSuchNotificationException {
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
		OrderByComparator<KaleoNotification> orderByComparator, boolean previous) {
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
	 * Removes all the kaleo notifications where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (KaleoNotification kaleoNotification : findByCompanyId(companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoNotification);
		}
	}

	/**
	 * Returns the number of kaleo notifications where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching kaleo notifications
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

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

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoNotification.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEODEFINITIONID =
		new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED,
			KaleoNotificationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
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

	/**
	 * Returns all the kaleo notifications where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the matching kaleo notifications
	 */
	@Override
	public List<KaleoNotification> findByKaleoDefinitionId(
		long kaleoDefinitionId) {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo notifications where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo notifications
	 * @param end the upper bound of the range of kaleo notifications (not inclusive)
	 * @return the range of matching kaleo notifications
	 */
	@Override
	public List<KaleoNotification> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end) {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo notifications where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo notifications
	 * @param end the upper bound of the range of kaleo notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo notifications
	 */
	@Override
	public List<KaleoNotification> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		OrderByComparator<KaleoNotification> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
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

		if ((list != null) && !list.isEmpty()) {
			for (KaleoNotification kaleoNotification : list) {
				if ((kaleoDefinitionId != kaleoNotification.getKaleoDefinitionId())) {
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

			query.append(_SQL_SELECT_KALEONOTIFICATION_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoNotificationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				if (!pagination) {
					list = (List<KaleoNotification>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoNotification>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first kaleo notification in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo notification
	 * @throws NoSuchNotificationException if a matching kaleo notification could not be found
	 */
	@Override
	public KaleoNotification findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		OrderByComparator<KaleoNotification> orderByComparator)
		throws NoSuchNotificationException {
		KaleoNotification kaleoNotification = fetchByKaleoDefinitionId_First(kaleoDefinitionId,
				orderByComparator);

		if (kaleoNotification != null) {
			return kaleoNotification;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoDefinitionId=");
		msg.append(kaleoDefinitionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchNotificationException(msg.toString());
	}

	/**
	 * Returns the first kaleo notification in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo notification, or <code>null</code> if a matching kaleo notification could not be found
	 */
	@Override
	public KaleoNotification fetchByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		OrderByComparator<KaleoNotification> orderByComparator) {
		List<KaleoNotification> list = findByKaleoDefinitionId(kaleoDefinitionId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo notification in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo notification
	 * @throws NoSuchNotificationException if a matching kaleo notification could not be found
	 */
	@Override
	public KaleoNotification findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		OrderByComparator<KaleoNotification> orderByComparator)
		throws NoSuchNotificationException {
		KaleoNotification kaleoNotification = fetchByKaleoDefinitionId_Last(kaleoDefinitionId,
				orderByComparator);

		if (kaleoNotification != null) {
			return kaleoNotification;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoDefinitionId=");
		msg.append(kaleoDefinitionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchNotificationException(msg.toString());
	}

	/**
	 * Returns the last kaleo notification in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo notification, or <code>null</code> if a matching kaleo notification could not be found
	 */
	@Override
	public KaleoNotification fetchByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		OrderByComparator<KaleoNotification> orderByComparator) {
		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		if (count == 0) {
			return null;
		}

		List<KaleoNotification> list = findByKaleoDefinitionId(kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo notifications before and after the current kaleo notification in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoNotificationId the primary key of the current kaleo notification
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo notification
	 * @throws NoSuchNotificationException if a kaleo notification with the primary key could not be found
	 */
	@Override
	public KaleoNotification[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoNotificationId, long kaleoDefinitionId,
		OrderByComparator<KaleoNotification> orderByComparator)
		throws NoSuchNotificationException {
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
		long kaleoDefinitionId,
		OrderByComparator<KaleoNotification> orderByComparator, boolean previous) {
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
	 * Removes all the kaleo notifications where kaleoDefinitionId = &#63; from the database.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 */
	@Override
	public void removeByKaleoDefinitionId(long kaleoDefinitionId) {
		for (KaleoNotification kaleoNotification : findByKaleoDefinitionId(
				kaleoDefinitionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoNotification);
		}
	}

	/**
	 * Returns the number of kaleo notifications where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the number of matching kaleo notifications
	 */
	@Override
	public int countByKaleoDefinitionId(long kaleoDefinitionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KALEODEFINITIONID;

		Object[] finderArgs = new Object[] { kaleoDefinitionId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

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

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2 =
		"kaleoNotification.kaleoDefinitionId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KCN_KCPK = new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED,
			KaleoNotificationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKCN_KCPK",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK =
		new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED,
			KaleoNotificationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByKCN_KCPK",
			new String[] { String.class.getName(), Long.class.getName() },
			KaleoNotificationModelImpl.KALEOCLASSNAME_COLUMN_BITMASK |
			KaleoNotificationModelImpl.KALEOCLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KCN_KCPK = new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKCN_KCPK",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @return the matching kaleo notifications
	 */
	@Override
	public List<KaleoNotification> findByKCN_KCPK(String kaleoClassName,
		long kaleoClassPK) {
		return findByKCN_KCPK(kaleoClassName, kaleoClassPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param start the lower bound of the range of kaleo notifications
	 * @param end the upper bound of the range of kaleo notifications (not inclusive)
	 * @return the range of matching kaleo notifications
	 */
	@Override
	public List<KaleoNotification> findByKCN_KCPK(String kaleoClassName,
		long kaleoClassPK, int start, int end) {
		return findByKCN_KCPK(kaleoClassName, kaleoClassPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param start the lower bound of the range of kaleo notifications
	 * @param end the upper bound of the range of kaleo notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo notifications
	 */
	@Override
	public List<KaleoNotification> findByKCN_KCPK(String kaleoClassName,
		long kaleoClassPK, int start, int end,
		OrderByComparator<KaleoNotification> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
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

		List<KaleoNotification> list = (List<KaleoNotification>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoNotification kaleoNotification : list) {
				if (!Validator.equals(kaleoClassName,
							kaleoNotification.getKaleoClassName()) ||
						(kaleoClassPK != kaleoNotification.getKaleoClassPK())) {
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

			query.append(_SQL_SELECT_KALEONOTIFICATION_WHERE);

			boolean bindKaleoClassName = false;

			if (kaleoClassName == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_1);
			}
			else if (kaleoClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_3);
			}
			else {
				bindKaleoClassName = true;

				query.append(_FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_2);
			}

			query.append(_FINDER_COLUMN_KCN_KCPK_KALEOCLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoNotificationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindKaleoClassName) {
					qPos.add(kaleoClassName);
				}

				qPos.add(kaleoClassPK);

				if (!pagination) {
					list = (List<KaleoNotification>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoNotification>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first kaleo notification in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo notification
	 * @throws NoSuchNotificationException if a matching kaleo notification could not be found
	 */
	@Override
	public KaleoNotification findByKCN_KCPK_First(String kaleoClassName,
		long kaleoClassPK,
		OrderByComparator<KaleoNotification> orderByComparator)
		throws NoSuchNotificationException {
		KaleoNotification kaleoNotification = fetchByKCN_KCPK_First(kaleoClassName,
				kaleoClassPK, orderByComparator);

		if (kaleoNotification != null) {
			return kaleoNotification;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoClassName=");
		msg.append(kaleoClassName);

		msg.append(", kaleoClassPK=");
		msg.append(kaleoClassPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchNotificationException(msg.toString());
	}

	/**
	 * Returns the first kaleo notification in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo notification, or <code>null</code> if a matching kaleo notification could not be found
	 */
	@Override
	public KaleoNotification fetchByKCN_KCPK_First(String kaleoClassName,
		long kaleoClassPK,
		OrderByComparator<KaleoNotification> orderByComparator) {
		List<KaleoNotification> list = findByKCN_KCPK(kaleoClassName,
				kaleoClassPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo notification in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo notification
	 * @throws NoSuchNotificationException if a matching kaleo notification could not be found
	 */
	@Override
	public KaleoNotification findByKCN_KCPK_Last(String kaleoClassName,
		long kaleoClassPK,
		OrderByComparator<KaleoNotification> orderByComparator)
		throws NoSuchNotificationException {
		KaleoNotification kaleoNotification = fetchByKCN_KCPK_Last(kaleoClassName,
				kaleoClassPK, orderByComparator);

		if (kaleoNotification != null) {
			return kaleoNotification;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoClassName=");
		msg.append(kaleoClassName);

		msg.append(", kaleoClassPK=");
		msg.append(kaleoClassPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchNotificationException(msg.toString());
	}

	/**
	 * Returns the last kaleo notification in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo notification, or <code>null</code> if a matching kaleo notification could not be found
	 */
	@Override
	public KaleoNotification fetchByKCN_KCPK_Last(String kaleoClassName,
		long kaleoClassPK,
		OrderByComparator<KaleoNotification> orderByComparator) {
		int count = countByKCN_KCPK(kaleoClassName, kaleoClassPK);

		if (count == 0) {
			return null;
		}

		List<KaleoNotification> list = findByKCN_KCPK(kaleoClassName,
				kaleoClassPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo notifications before and after the current kaleo notification in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoNotificationId the primary key of the current kaleo notification
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo notification
	 * @throws NoSuchNotificationException if a kaleo notification with the primary key could not be found
	 */
	@Override
	public KaleoNotification[] findByKCN_KCPK_PrevAndNext(
		long kaleoNotificationId, String kaleoClassName, long kaleoClassPK,
		OrderByComparator<KaleoNotification> orderByComparator)
		throws NoSuchNotificationException {
		KaleoNotification kaleoNotification = findByPrimaryKey(kaleoNotificationId);

		Session session = null;

		try {
			session = openSession();

			KaleoNotification[] array = new KaleoNotificationImpl[3];

			array[0] = getByKCN_KCPK_PrevAndNext(session, kaleoNotification,
					kaleoClassName, kaleoClassPK, orderByComparator, true);

			array[1] = kaleoNotification;

			array[2] = getByKCN_KCPK_PrevAndNext(session, kaleoNotification,
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

	protected KaleoNotification getByKCN_KCPK_PrevAndNext(Session session,
		KaleoNotification kaleoNotification, String kaleoClassName,
		long kaleoClassPK,
		OrderByComparator<KaleoNotification> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEONOTIFICATION_WHERE);

		boolean bindKaleoClassName = false;

		if (kaleoClassName == null) {
			query.append(_FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_1);
		}
		else if (kaleoClassName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_3);
		}
		else {
			bindKaleoClassName = true;

			query.append(_FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_2);
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
			query.append(KaleoNotificationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindKaleoClassName) {
			qPos.add(kaleoClassName);
		}

		qPos.add(kaleoClassPK);

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
	 * Removes all the kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63; from the database.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 */
	@Override
	public void removeByKCN_KCPK(String kaleoClassName, long kaleoClassPK) {
		for (KaleoNotification kaleoNotification : findByKCN_KCPK(
				kaleoClassName, kaleoClassPK, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(kaleoNotification);
		}
	}

	/**
	 * Returns the number of kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @return the number of matching kaleo notifications
	 */
	@Override
	public int countByKCN_KCPK(String kaleoClassName, long kaleoClassPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KCN_KCPK;

		Object[] finderArgs = new Object[] { kaleoClassName, kaleoClassPK };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEONOTIFICATION_WHERE);

			boolean bindKaleoClassName = false;

			if (kaleoClassName == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_1);
			}
			else if (kaleoClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_3);
			}
			else {
				bindKaleoClassName = true;

				query.append(_FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_2);
			}

			query.append(_FINDER_COLUMN_KCN_KCPK_KALEOCLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindKaleoClassName) {
					qPos.add(kaleoClassName);
				}

				qPos.add(kaleoClassPK);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_1 = "kaleoNotification.kaleoClassName IS NULL AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_2 = "kaleoNotification.kaleoClassName = ? AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_3 = "(kaleoNotification.kaleoClassName IS NULL OR kaleoNotification.kaleoClassName = '') AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_KALEOCLASSPK_2 = "kaleoNotification.kaleoClassPK = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KCN_KCPK_ET =
		new FinderPath(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationModelImpl.FINDER_CACHE_ENABLED,
			KaleoNotificationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKCN_KCPK_ET",
			new String[] {
				String.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
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

	/**
	 * Returns all the kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @return the matching kaleo notifications
	 */
	@Override
	public List<KaleoNotification> findByKCN_KCPK_ET(String kaleoClassName,
		long kaleoClassPK, String executionType) {
		return findByKCN_KCPK_ET(kaleoClassName, kaleoClassPK, executionType,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @param start the lower bound of the range of kaleo notifications
	 * @param end the upper bound of the range of kaleo notifications (not inclusive)
	 * @return the range of matching kaleo notifications
	 */
	@Override
	public List<KaleoNotification> findByKCN_KCPK_ET(String kaleoClassName,
		long kaleoClassPK, String executionType, int start, int end) {
		return findByKCN_KCPK_ET(kaleoClassName, kaleoClassPK, executionType,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @param start the lower bound of the range of kaleo notifications
	 * @param end the upper bound of the range of kaleo notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo notifications
	 */
	@Override
	public List<KaleoNotification> findByKCN_KCPK_ET(String kaleoClassName,
		long kaleoClassPK, String executionType, int start, int end,
		OrderByComparator<KaleoNotification> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
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

		if ((list != null) && !list.isEmpty()) {
			for (KaleoNotification kaleoNotification : list) {
				if (!Validator.equals(kaleoClassName,
							kaleoNotification.getKaleoClassName()) ||
						(kaleoClassPK != kaleoNotification.getKaleoClassPK()) ||
						!Validator.equals(executionType,
							kaleoNotification.getExecutionType())) {
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

			query.append(_SQL_SELECT_KALEONOTIFICATION_WHERE);

			boolean bindKaleoClassName = false;

			if (kaleoClassName == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_1);
			}
			else if (kaleoClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_3);
			}
			else {
				bindKaleoClassName = true;

				query.append(_FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_2);
			}

			query.append(_FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSPK_2);

			boolean bindExecutionType = false;

			if (executionType == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_1);
			}
			else if (executionType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_3);
			}
			else {
				bindExecutionType = true;

				query.append(_FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoNotificationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindKaleoClassName) {
					qPos.add(kaleoClassName);
				}

				qPos.add(kaleoClassPK);

				if (bindExecutionType) {
					qPos.add(executionType);
				}

				if (!pagination) {
					list = (List<KaleoNotification>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoNotification>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first kaleo notification in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo notification
	 * @throws NoSuchNotificationException if a matching kaleo notification could not be found
	 */
	@Override
	public KaleoNotification findByKCN_KCPK_ET_First(String kaleoClassName,
		long kaleoClassPK, String executionType,
		OrderByComparator<KaleoNotification> orderByComparator)
		throws NoSuchNotificationException {
		KaleoNotification kaleoNotification = fetchByKCN_KCPK_ET_First(kaleoClassName,
				kaleoClassPK, executionType, orderByComparator);

		if (kaleoNotification != null) {
			return kaleoNotification;
		}

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

	/**
	 * Returns the first kaleo notification in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo notification, or <code>null</code> if a matching kaleo notification could not be found
	 */
	@Override
	public KaleoNotification fetchByKCN_KCPK_ET_First(String kaleoClassName,
		long kaleoClassPK, String executionType,
		OrderByComparator<KaleoNotification> orderByComparator) {
		List<KaleoNotification> list = findByKCN_KCPK_ET(kaleoClassName,
				kaleoClassPK, executionType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo notification in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo notification
	 * @throws NoSuchNotificationException if a matching kaleo notification could not be found
	 */
	@Override
	public KaleoNotification findByKCN_KCPK_ET_Last(String kaleoClassName,
		long kaleoClassPK, String executionType,
		OrderByComparator<KaleoNotification> orderByComparator)
		throws NoSuchNotificationException {
		KaleoNotification kaleoNotification = fetchByKCN_KCPK_ET_Last(kaleoClassName,
				kaleoClassPK, executionType, orderByComparator);

		if (kaleoNotification != null) {
			return kaleoNotification;
		}

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

	/**
	 * Returns the last kaleo notification in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo notification, or <code>null</code> if a matching kaleo notification could not be found
	 */
	@Override
	public KaleoNotification fetchByKCN_KCPK_ET_Last(String kaleoClassName,
		long kaleoClassPK, String executionType,
		OrderByComparator<KaleoNotification> orderByComparator) {
		int count = countByKCN_KCPK_ET(kaleoClassName, kaleoClassPK,
				executionType);

		if (count == 0) {
			return null;
		}

		List<KaleoNotification> list = findByKCN_KCPK_ET(kaleoClassName,
				kaleoClassPK, executionType, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo notifications before and after the current kaleo notification in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * @param kaleoNotificationId the primary key of the current kaleo notification
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo notification
	 * @throws NoSuchNotificationException if a kaleo notification with the primary key could not be found
	 */
	@Override
	public KaleoNotification[] findByKCN_KCPK_ET_PrevAndNext(
		long kaleoNotificationId, String kaleoClassName, long kaleoClassPK,
		String executionType,
		OrderByComparator<KaleoNotification> orderByComparator)
		throws NoSuchNotificationException {
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
		OrderByComparator<KaleoNotification> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEONOTIFICATION_WHERE);

		boolean bindKaleoClassName = false;

		if (kaleoClassName == null) {
			query.append(_FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_1);
		}
		else if (kaleoClassName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_3);
		}
		else {
			bindKaleoClassName = true;

			query.append(_FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_2);
		}

		query.append(_FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSPK_2);

		boolean bindExecutionType = false;

		if (executionType == null) {
			query.append(_FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_1);
		}
		else if (executionType.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_3);
		}
		else {
			bindExecutionType = true;

			query.append(_FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_2);
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

		if (bindKaleoClassName) {
			qPos.add(kaleoClassName);
		}

		qPos.add(kaleoClassPK);

		if (bindExecutionType) {
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
	 * Removes all the kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63; from the database.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 */
	@Override
	public void removeByKCN_KCPK_ET(String kaleoClassName, long kaleoClassPK,
		String executionType) {
		for (KaleoNotification kaleoNotification : findByKCN_KCPK_ET(
				kaleoClassName, kaleoClassPK, executionType, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(kaleoNotification);
		}
	}

	/**
	 * Returns the number of kaleo notifications where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @return the number of matching kaleo notifications
	 */
	@Override
	public int countByKCN_KCPK_ET(String kaleoClassName, long kaleoClassPK,
		String executionType) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KCN_KCPK_ET;

		Object[] finderArgs = new Object[] {
				kaleoClassName, kaleoClassPK, executionType
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KALEONOTIFICATION_WHERE);

			boolean bindKaleoClassName = false;

			if (kaleoClassName == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_1);
			}
			else if (kaleoClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_3);
			}
			else {
				bindKaleoClassName = true;

				query.append(_FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_2);
			}

			query.append(_FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSPK_2);

			boolean bindExecutionType = false;

			if (executionType == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_1);
			}
			else if (executionType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_3);
			}
			else {
				bindExecutionType = true;

				query.append(_FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindKaleoClassName) {
					qPos.add(kaleoClassName);
				}

				qPos.add(kaleoClassPK);

				if (bindExecutionType) {
					qPos.add(executionType);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_1 = "kaleoNotification.kaleoClassName IS NULL AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_2 = "kaleoNotification.kaleoClassName = ? AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_3 = "(kaleoNotification.kaleoClassName IS NULL OR kaleoNotification.kaleoClassName = '') AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSPK_2 = "kaleoNotification.kaleoClassPK = ? AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_1 = "kaleoNotification.executionType IS NULL";
	private static final String _FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_2 = "kaleoNotification.executionType = ?";
	private static final String _FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_3 = "(kaleoNotification.executionType IS NULL OR kaleoNotification.executionType = '')";

	public KaleoNotificationPersistenceImpl() {
		setModelClass(KaleoNotification.class);
	}

	/**
	 * Caches the kaleo notification in the entity cache if it is enabled.
	 *
	 * @param kaleoNotification the kaleo notification
	 */
	@Override
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
	@Override
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
		EntityCacheUtil.clearCache(KaleoNotificationImpl.class);

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
	@Override
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
	 * @throws NoSuchNotificationException if a kaleo notification with the primary key could not be found
	 */
	@Override
	public KaleoNotification remove(long kaleoNotificationId)
		throws NoSuchNotificationException {
		return remove((Serializable)kaleoNotificationId);
	}

	/**
	 * Removes the kaleo notification with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo notification
	 * @return the kaleo notification that was removed
	 * @throws NoSuchNotificationException if a kaleo notification with the primary key could not be found
	 */
	@Override
	public KaleoNotification remove(Serializable primaryKey)
		throws NoSuchNotificationException {
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
	protected KaleoNotification removeImpl(KaleoNotification kaleoNotification) {
		kaleoNotification = toUnwrappedModel(kaleoNotification);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(kaleoNotification)) {
				kaleoNotification = (KaleoNotification)session.get(KaleoNotificationImpl.class,
						kaleoNotification.getPrimaryKeyObj());
			}

			if (kaleoNotification != null) {
				session.delete(kaleoNotification);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (kaleoNotification != null) {
			clearCache(kaleoNotification);
		}

		return kaleoNotification;
	}

	@Override
	public KaleoNotification updateImpl(KaleoNotification kaleoNotification) {
		kaleoNotification = toUnwrappedModel(kaleoNotification);

		boolean isNew = kaleoNotification.isNew();

		KaleoNotificationModelImpl kaleoNotificationModelImpl = (KaleoNotificationModelImpl)kaleoNotification;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (kaleoNotification.getCreateDate() == null)) {
			if (serviceContext == null) {
				kaleoNotification.setCreateDate(now);
			}
			else {
				kaleoNotification.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!kaleoNotificationModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				kaleoNotification.setModifiedDate(now);
			}
			else {
				kaleoNotification.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (kaleoNotification.isNew()) {
				session.save(kaleoNotification);

				kaleoNotification.setNew(false);
			}
			else {
				session.merge(kaleoNotification);
			}
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
						kaleoNotificationModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { kaleoNotificationModelImpl.getCompanyId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((kaleoNotificationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoNotificationModelImpl.getOriginalKaleoDefinitionId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);

				args = new Object[] {
						kaleoNotificationModelImpl.getKaleoDefinitionId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);
			}

			if ((kaleoNotificationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoNotificationModelImpl.getOriginalKaleoClassName(),
						kaleoNotificationModelImpl.getOriginalKaleoClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KCN_KCPK, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK,
					args);

				args = new Object[] {
						kaleoNotificationModelImpl.getKaleoClassName(),
						kaleoNotificationModelImpl.getKaleoClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KCN_KCPK, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK,
					args);
			}

			if ((kaleoNotificationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_ET.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoNotificationModelImpl.getOriginalKaleoClassName(),
						kaleoNotificationModelImpl.getOriginalKaleoClassPK(),
						kaleoNotificationModelImpl.getOriginalExecutionType()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KCN_KCPK_ET,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_ET,
					args);

				args = new Object[] {
						kaleoNotificationModelImpl.getKaleoClassName(),
						kaleoNotificationModelImpl.getKaleoClassPK(),
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
			kaleoNotification, false);

		kaleoNotification.resetOriginalValues();

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
	 * @throws NoSuchNotificationException if a kaleo notification with the primary key could not be found
	 */
	@Override
	public KaleoNotification findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNotificationException {
		KaleoNotification kaleoNotification = fetchByPrimaryKey(primaryKey);

		if (kaleoNotification == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNotificationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return kaleoNotification;
	}

	/**
	 * Returns the kaleo notification with the primary key or throws a {@link NoSuchNotificationException} if it could not be found.
	 *
	 * @param kaleoNotificationId the primary key of the kaleo notification
	 * @return the kaleo notification
	 * @throws NoSuchNotificationException if a kaleo notification with the primary key could not be found
	 */
	@Override
	public KaleoNotification findByPrimaryKey(long kaleoNotificationId)
		throws NoSuchNotificationException {
		return findByPrimaryKey((Serializable)kaleoNotificationId);
	}

	/**
	 * Returns the kaleo notification with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo notification
	 * @return the kaleo notification, or <code>null</code> if a kaleo notification with the primary key could not be found
	 */
	@Override
	public KaleoNotification fetchByPrimaryKey(Serializable primaryKey) {
		KaleoNotification kaleoNotification = (KaleoNotification)EntityCacheUtil.getResult(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
				KaleoNotificationImpl.class, primaryKey);

		if (kaleoNotification == _nullKaleoNotification) {
			return null;
		}

		if (kaleoNotification == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoNotification = (KaleoNotification)session.get(KaleoNotificationImpl.class,
						primaryKey);

				if (kaleoNotification != null) {
					cacheResult(kaleoNotification);
				}
				else {
					EntityCacheUtil.putResult(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
						KaleoNotificationImpl.class, primaryKey,
						_nullKaleoNotification);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
					KaleoNotificationImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return kaleoNotification;
	}

	/**
	 * Returns the kaleo notification with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoNotificationId the primary key of the kaleo notification
	 * @return the kaleo notification, or <code>null</code> if a kaleo notification with the primary key could not be found
	 */
	@Override
	public KaleoNotification fetchByPrimaryKey(long kaleoNotificationId) {
		return fetchByPrimaryKey((Serializable)kaleoNotificationId);
	}

	@Override
	public Map<Serializable, KaleoNotification> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, KaleoNotification> map = new HashMap<Serializable, KaleoNotification>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			KaleoNotification kaleoNotification = fetchByPrimaryKey(primaryKey);

			if (kaleoNotification != null) {
				map.put(primaryKey, kaleoNotification);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			KaleoNotification kaleoNotification = (KaleoNotification)EntityCacheUtil.getResult(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
					KaleoNotificationImpl.class, primaryKey);

			if (kaleoNotification == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, kaleoNotification);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_KALEONOTIFICATION_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (KaleoNotification kaleoNotification : (List<KaleoNotification>)q.list()) {
				map.put(kaleoNotification.getPrimaryKeyObj(), kaleoNotification);

				cacheResult(kaleoNotification);

				uncachedPrimaryKeys.remove(kaleoNotification.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(KaleoNotificationModelImpl.ENTITY_CACHE_ENABLED,
					KaleoNotificationImpl.class, primaryKey,
					_nullKaleoNotification);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the kaleo notifications.
	 *
	 * @return the kaleo notifications
	 */
	@Override
	public List<KaleoNotification> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo notifications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo notifications
	 * @param end the upper bound of the range of kaleo notifications (not inclusive)
	 * @return the range of kaleo notifications
	 */
	@Override
	public List<KaleoNotification> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo notifications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo notifications
	 * @param end the upper bound of the range of kaleo notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo notifications
	 */
	@Override
	public List<KaleoNotification> findAll(int start, int end,
		OrderByComparator<KaleoNotification> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
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
				sql = _SQL_SELECT_KALEONOTIFICATION;

				if (pagination) {
					sql = sql.concat(KaleoNotificationModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<KaleoNotification>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoNotification>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the kaleo notifications from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (KaleoNotification kaleoNotification : findAll()) {
			remove(kaleoNotification);
		}
	}

	/**
	 * Returns the number of kaleo notifications.
	 *
	 * @return the number of kaleo notifications
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KALEONOTIFICATION);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return KaleoNotificationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the kaleo notification persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(KaleoNotificationImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_KALEONOTIFICATION = "SELECT kaleoNotification FROM KaleoNotification kaleoNotification";
	private static final String _SQL_SELECT_KALEONOTIFICATION_WHERE_PKS_IN = "SELECT kaleoNotification FROM KaleoNotification kaleoNotification WHERE kaleoNotificationId IN (";
	private static final String _SQL_SELECT_KALEONOTIFICATION_WHERE = "SELECT kaleoNotification FROM KaleoNotification kaleoNotification WHERE ";
	private static final String _SQL_COUNT_KALEONOTIFICATION = "SELECT COUNT(kaleoNotification) FROM KaleoNotification kaleoNotification";
	private static final String _SQL_COUNT_KALEONOTIFICATION_WHERE = "SELECT COUNT(kaleoNotification) FROM KaleoNotification kaleoNotification WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoNotification.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoNotification exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoNotification exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(KaleoNotificationPersistenceImpl.class);
	private static final KaleoNotification _nullKaleoNotification = new KaleoNotificationImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<KaleoNotification> toCacheModel() {
				return _nullKaleoNotificationCacheModel;
			}
		};

	private static final CacheModel<KaleoNotification> _nullKaleoNotificationCacheModel =
		new CacheModel<KaleoNotification>() {
			@Override
			public KaleoNotification toEntityModel() {
				return _nullKaleoNotification;
			}
		};
}