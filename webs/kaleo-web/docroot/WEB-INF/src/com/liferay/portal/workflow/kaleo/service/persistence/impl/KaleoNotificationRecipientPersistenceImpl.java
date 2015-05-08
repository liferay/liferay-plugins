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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.NoSuchNotificationRecipientException;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationRecipientModelImpl;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoNotificationRecipientPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the kaleo notification recipient service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoNotificationRecipientPersistence
 * @see com.liferay.portal.workflow.kaleo.service.persistence.KaleoNotificationRecipientUtil
 * @generated
 */
@ProviderType
public class KaleoNotificationRecipientPersistenceImpl
	extends BasePersistenceImpl<KaleoNotificationRecipient>
	implements KaleoNotificationRecipientPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KaleoNotificationRecipientUtil} to access the kaleo notification recipient persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoNotificationRecipientImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationRecipientModelImpl.FINDER_CACHE_ENABLED,
			KaleoNotificationRecipientImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationRecipientModelImpl.FINDER_CACHE_ENABLED,
			KaleoNotificationRecipientImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationRecipientModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationRecipientModelImpl.FINDER_CACHE_ENABLED,
			KaleoNotificationRecipientImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationRecipientModelImpl.FINDER_CACHE_ENABLED,
			KaleoNotificationRecipientImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			KaleoNotificationRecipientModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationRecipientModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCompanyId", new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo notification recipients where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching kaleo notification recipients
	 */
	@Override
	public List<KaleoNotificationRecipient> findByCompanyId(long companyId) {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the kaleo notification recipients where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationRecipientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo notification recipients
	 * @param end the upper bound of the range of kaleo notification recipients (not inclusive)
	 * @return the range of matching kaleo notification recipients
	 */
	@Override
	public List<KaleoNotificationRecipient> findByCompanyId(long companyId,
		int start, int end) {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo notification recipients where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationRecipientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo notification recipients
	 * @param end the upper bound of the range of kaleo notification recipients (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo notification recipients
	 */
	@Override
	public List<KaleoNotificationRecipient> findByCompanyId(long companyId,
		int start, int end,
		OrderByComparator<KaleoNotificationRecipient> orderByComparator) {
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

		List<KaleoNotificationRecipient> list = (List<KaleoNotificationRecipient>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoNotificationRecipient kaleoNotificationRecipient : list) {
				if ((companyId != kaleoNotificationRecipient.getCompanyId())) {
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

			query.append(_SQL_SELECT_KALEONOTIFICATIONRECIPIENT_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoNotificationRecipientModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<KaleoNotificationRecipient>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoNotificationRecipient>)QueryUtil.list(q,
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
	 * Returns the first kaleo notification recipient in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo notification recipient
	 * @throws NoSuchNotificationRecipientException if a matching kaleo notification recipient could not be found
	 */
	@Override
	public KaleoNotificationRecipient findByCompanyId_First(long companyId,
		OrderByComparator<KaleoNotificationRecipient> orderByComparator)
		throws NoSuchNotificationRecipientException {
		KaleoNotificationRecipient kaleoNotificationRecipient = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (kaleoNotificationRecipient != null) {
			return kaleoNotificationRecipient;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchNotificationRecipientException(msg.toString());
	}

	/**
	 * Returns the first kaleo notification recipient in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo notification recipient, or <code>null</code> if a matching kaleo notification recipient could not be found
	 */
	@Override
	public KaleoNotificationRecipient fetchByCompanyId_First(long companyId,
		OrderByComparator<KaleoNotificationRecipient> orderByComparator) {
		List<KaleoNotificationRecipient> list = findByCompanyId(companyId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo notification recipient in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo notification recipient
	 * @throws NoSuchNotificationRecipientException if a matching kaleo notification recipient could not be found
	 */
	@Override
	public KaleoNotificationRecipient findByCompanyId_Last(long companyId,
		OrderByComparator<KaleoNotificationRecipient> orderByComparator)
		throws NoSuchNotificationRecipientException {
		KaleoNotificationRecipient kaleoNotificationRecipient = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (kaleoNotificationRecipient != null) {
			return kaleoNotificationRecipient;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchNotificationRecipientException(msg.toString());
	}

	/**
	 * Returns the last kaleo notification recipient in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo notification recipient, or <code>null</code> if a matching kaleo notification recipient could not be found
	 */
	@Override
	public KaleoNotificationRecipient fetchByCompanyId_Last(long companyId,
		OrderByComparator<KaleoNotificationRecipient> orderByComparator) {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<KaleoNotificationRecipient> list = findByCompanyId(companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo notification recipients before and after the current kaleo notification recipient in the ordered set where companyId = &#63;.
	 *
	 * @param kaleoNotificationRecipientId the primary key of the current kaleo notification recipient
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo notification recipient
	 * @throws NoSuchNotificationRecipientException if a kaleo notification recipient with the primary key could not be found
	 */
	@Override
	public KaleoNotificationRecipient[] findByCompanyId_PrevAndNext(
		long kaleoNotificationRecipientId, long companyId,
		OrderByComparator<KaleoNotificationRecipient> orderByComparator)
		throws NoSuchNotificationRecipientException {
		KaleoNotificationRecipient kaleoNotificationRecipient = findByPrimaryKey(kaleoNotificationRecipientId);

		Session session = null;

		try {
			session = openSession();

			KaleoNotificationRecipient[] array = new KaleoNotificationRecipientImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session,
					kaleoNotificationRecipient, companyId, orderByComparator,
					true);

			array[1] = kaleoNotificationRecipient;

			array[2] = getByCompanyId_PrevAndNext(session,
					kaleoNotificationRecipient, companyId, orderByComparator,
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

	protected KaleoNotificationRecipient getByCompanyId_PrevAndNext(
		Session session, KaleoNotificationRecipient kaleoNotificationRecipient,
		long companyId,
		OrderByComparator<KaleoNotificationRecipient> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEONOTIFICATIONRECIPIENT_WHERE);

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
			query.append(KaleoNotificationRecipientModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoNotificationRecipient);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoNotificationRecipient> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo notification recipients where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (KaleoNotificationRecipient kaleoNotificationRecipient : findByCompanyId(
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoNotificationRecipient);
		}
	}

	/**
	 * Returns the number of kaleo notification recipients where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching kaleo notification recipients
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEONOTIFICATIONRECIPIENT_WHERE);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoNotificationRecipient.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEODEFINITIONID =
		new FinderPath(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationRecipientModelImpl.FINDER_CACHE_ENABLED,
			KaleoNotificationRecipientImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID =
		new FinderPath(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationRecipientModelImpl.FINDER_CACHE_ENABLED,
			KaleoNotificationRecipientImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByKaleoDefinitionId", new String[] { Long.class.getName() },
			KaleoNotificationRecipientModelImpl.KALEODEFINITIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEODEFINITIONID = new FinderPath(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationRecipientModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKaleoDefinitionId", new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo notification recipients where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the matching kaleo notification recipients
	 */
	@Override
	public List<KaleoNotificationRecipient> findByKaleoDefinitionId(
		long kaleoDefinitionId) {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo notification recipients where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationRecipientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo notification recipients
	 * @param end the upper bound of the range of kaleo notification recipients (not inclusive)
	 * @return the range of matching kaleo notification recipients
	 */
	@Override
	public List<KaleoNotificationRecipient> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end) {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo notification recipients where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationRecipientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo notification recipients
	 * @param end the upper bound of the range of kaleo notification recipients (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo notification recipients
	 */
	@Override
	public List<KaleoNotificationRecipient> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		OrderByComparator<KaleoNotificationRecipient> orderByComparator) {
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

		List<KaleoNotificationRecipient> list = (List<KaleoNotificationRecipient>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoNotificationRecipient kaleoNotificationRecipient : list) {
				if ((kaleoDefinitionId != kaleoNotificationRecipient.getKaleoDefinitionId())) {
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

			query.append(_SQL_SELECT_KALEONOTIFICATIONRECIPIENT_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoNotificationRecipientModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				if (!pagination) {
					list = (List<KaleoNotificationRecipient>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoNotificationRecipient>)QueryUtil.list(q,
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
	 * Returns the first kaleo notification recipient in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo notification recipient
	 * @throws NoSuchNotificationRecipientException if a matching kaleo notification recipient could not be found
	 */
	@Override
	public KaleoNotificationRecipient findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		OrderByComparator<KaleoNotificationRecipient> orderByComparator)
		throws NoSuchNotificationRecipientException {
		KaleoNotificationRecipient kaleoNotificationRecipient = fetchByKaleoDefinitionId_First(kaleoDefinitionId,
				orderByComparator);

		if (kaleoNotificationRecipient != null) {
			return kaleoNotificationRecipient;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoDefinitionId=");
		msg.append(kaleoDefinitionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchNotificationRecipientException(msg.toString());
	}

	/**
	 * Returns the first kaleo notification recipient in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo notification recipient, or <code>null</code> if a matching kaleo notification recipient could not be found
	 */
	@Override
	public KaleoNotificationRecipient fetchByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		OrderByComparator<KaleoNotificationRecipient> orderByComparator) {
		List<KaleoNotificationRecipient> list = findByKaleoDefinitionId(kaleoDefinitionId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo notification recipient in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo notification recipient
	 * @throws NoSuchNotificationRecipientException if a matching kaleo notification recipient could not be found
	 */
	@Override
	public KaleoNotificationRecipient findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		OrderByComparator<KaleoNotificationRecipient> orderByComparator)
		throws NoSuchNotificationRecipientException {
		KaleoNotificationRecipient kaleoNotificationRecipient = fetchByKaleoDefinitionId_Last(kaleoDefinitionId,
				orderByComparator);

		if (kaleoNotificationRecipient != null) {
			return kaleoNotificationRecipient;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoDefinitionId=");
		msg.append(kaleoDefinitionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchNotificationRecipientException(msg.toString());
	}

	/**
	 * Returns the last kaleo notification recipient in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo notification recipient, or <code>null</code> if a matching kaleo notification recipient could not be found
	 */
	@Override
	public KaleoNotificationRecipient fetchByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		OrderByComparator<KaleoNotificationRecipient> orderByComparator) {
		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		if (count == 0) {
			return null;
		}

		List<KaleoNotificationRecipient> list = findByKaleoDefinitionId(kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo notification recipients before and after the current kaleo notification recipient in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoNotificationRecipientId the primary key of the current kaleo notification recipient
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo notification recipient
	 * @throws NoSuchNotificationRecipientException if a kaleo notification recipient with the primary key could not be found
	 */
	@Override
	public KaleoNotificationRecipient[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoNotificationRecipientId, long kaleoDefinitionId,
		OrderByComparator<KaleoNotificationRecipient> orderByComparator)
		throws NoSuchNotificationRecipientException {
		KaleoNotificationRecipient kaleoNotificationRecipient = findByPrimaryKey(kaleoNotificationRecipientId);

		Session session = null;

		try {
			session = openSession();

			KaleoNotificationRecipient[] array = new KaleoNotificationRecipientImpl[3];

			array[0] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoNotificationRecipient, kaleoDefinitionId,
					orderByComparator, true);

			array[1] = kaleoNotificationRecipient;

			array[2] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoNotificationRecipient, kaleoDefinitionId,
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

	protected KaleoNotificationRecipient getByKaleoDefinitionId_PrevAndNext(
		Session session, KaleoNotificationRecipient kaleoNotificationRecipient,
		long kaleoDefinitionId,
		OrderByComparator<KaleoNotificationRecipient> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEONOTIFICATIONRECIPIENT_WHERE);

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
			query.append(KaleoNotificationRecipientModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoNotificationRecipient);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoNotificationRecipient> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo notification recipients where kaleoDefinitionId = &#63; from the database.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 */
	@Override
	public void removeByKaleoDefinitionId(long kaleoDefinitionId) {
		for (KaleoNotificationRecipient kaleoNotificationRecipient : findByKaleoDefinitionId(
				kaleoDefinitionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoNotificationRecipient);
		}
	}

	/**
	 * Returns the number of kaleo notification recipients where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the number of matching kaleo notification recipients
	 */
	@Override
	public int countByKaleoDefinitionId(long kaleoDefinitionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KALEODEFINITIONID;

		Object[] finderArgs = new Object[] { kaleoDefinitionId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEONOTIFICATIONRECIPIENT_WHERE);

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
		"kaleoNotificationRecipient.kaleoDefinitionId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEONOTIFICATIONID =
		new FinderPath(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationRecipientModelImpl.FINDER_CACHE_ENABLED,
			KaleoNotificationRecipientImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByKaleoNotificationId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEONOTIFICATIONID =
		new FinderPath(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationRecipientModelImpl.FINDER_CACHE_ENABLED,
			KaleoNotificationRecipientImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByKaleoNotificationId", new String[] { Long.class.getName() },
			KaleoNotificationRecipientModelImpl.KALEONOTIFICATIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEONOTIFICATIONID = new FinderPath(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationRecipientModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKaleoNotificationId", new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo notification recipients where kaleoNotificationId = &#63;.
	 *
	 * @param kaleoNotificationId the kaleo notification ID
	 * @return the matching kaleo notification recipients
	 */
	@Override
	public List<KaleoNotificationRecipient> findByKaleoNotificationId(
		long kaleoNotificationId) {
		return findByKaleoNotificationId(kaleoNotificationId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo notification recipients where kaleoNotificationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationRecipientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoNotificationId the kaleo notification ID
	 * @param start the lower bound of the range of kaleo notification recipients
	 * @param end the upper bound of the range of kaleo notification recipients (not inclusive)
	 * @return the range of matching kaleo notification recipients
	 */
	@Override
	public List<KaleoNotificationRecipient> findByKaleoNotificationId(
		long kaleoNotificationId, int start, int end) {
		return findByKaleoNotificationId(kaleoNotificationId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo notification recipients where kaleoNotificationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationRecipientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoNotificationId the kaleo notification ID
	 * @param start the lower bound of the range of kaleo notification recipients
	 * @param end the upper bound of the range of kaleo notification recipients (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo notification recipients
	 */
	@Override
	public List<KaleoNotificationRecipient> findByKaleoNotificationId(
		long kaleoNotificationId, int start, int end,
		OrderByComparator<KaleoNotificationRecipient> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEONOTIFICATIONID;
			finderArgs = new Object[] { kaleoNotificationId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEONOTIFICATIONID;
			finderArgs = new Object[] {
					kaleoNotificationId,
					
					start, end, orderByComparator
				};
		}

		List<KaleoNotificationRecipient> list = (List<KaleoNotificationRecipient>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoNotificationRecipient kaleoNotificationRecipient : list) {
				if ((kaleoNotificationId != kaleoNotificationRecipient.getKaleoNotificationId())) {
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

			query.append(_SQL_SELECT_KALEONOTIFICATIONRECIPIENT_WHERE);

			query.append(_FINDER_COLUMN_KALEONOTIFICATIONID_KALEONOTIFICATIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoNotificationRecipientModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNotificationId);

				if (!pagination) {
					list = (List<KaleoNotificationRecipient>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoNotificationRecipient>)QueryUtil.list(q,
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
	 * Returns the first kaleo notification recipient in the ordered set where kaleoNotificationId = &#63;.
	 *
	 * @param kaleoNotificationId the kaleo notification ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo notification recipient
	 * @throws NoSuchNotificationRecipientException if a matching kaleo notification recipient could not be found
	 */
	@Override
	public KaleoNotificationRecipient findByKaleoNotificationId_First(
		long kaleoNotificationId,
		OrderByComparator<KaleoNotificationRecipient> orderByComparator)
		throws NoSuchNotificationRecipientException {
		KaleoNotificationRecipient kaleoNotificationRecipient = fetchByKaleoNotificationId_First(kaleoNotificationId,
				orderByComparator);

		if (kaleoNotificationRecipient != null) {
			return kaleoNotificationRecipient;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoNotificationId=");
		msg.append(kaleoNotificationId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchNotificationRecipientException(msg.toString());
	}

	/**
	 * Returns the first kaleo notification recipient in the ordered set where kaleoNotificationId = &#63;.
	 *
	 * @param kaleoNotificationId the kaleo notification ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo notification recipient, or <code>null</code> if a matching kaleo notification recipient could not be found
	 */
	@Override
	public KaleoNotificationRecipient fetchByKaleoNotificationId_First(
		long kaleoNotificationId,
		OrderByComparator<KaleoNotificationRecipient> orderByComparator) {
		List<KaleoNotificationRecipient> list = findByKaleoNotificationId(kaleoNotificationId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo notification recipient in the ordered set where kaleoNotificationId = &#63;.
	 *
	 * @param kaleoNotificationId the kaleo notification ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo notification recipient
	 * @throws NoSuchNotificationRecipientException if a matching kaleo notification recipient could not be found
	 */
	@Override
	public KaleoNotificationRecipient findByKaleoNotificationId_Last(
		long kaleoNotificationId,
		OrderByComparator<KaleoNotificationRecipient> orderByComparator)
		throws NoSuchNotificationRecipientException {
		KaleoNotificationRecipient kaleoNotificationRecipient = fetchByKaleoNotificationId_Last(kaleoNotificationId,
				orderByComparator);

		if (kaleoNotificationRecipient != null) {
			return kaleoNotificationRecipient;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoNotificationId=");
		msg.append(kaleoNotificationId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchNotificationRecipientException(msg.toString());
	}

	/**
	 * Returns the last kaleo notification recipient in the ordered set where kaleoNotificationId = &#63;.
	 *
	 * @param kaleoNotificationId the kaleo notification ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo notification recipient, or <code>null</code> if a matching kaleo notification recipient could not be found
	 */
	@Override
	public KaleoNotificationRecipient fetchByKaleoNotificationId_Last(
		long kaleoNotificationId,
		OrderByComparator<KaleoNotificationRecipient> orderByComparator) {
		int count = countByKaleoNotificationId(kaleoNotificationId);

		if (count == 0) {
			return null;
		}

		List<KaleoNotificationRecipient> list = findByKaleoNotificationId(kaleoNotificationId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo notification recipients before and after the current kaleo notification recipient in the ordered set where kaleoNotificationId = &#63;.
	 *
	 * @param kaleoNotificationRecipientId the primary key of the current kaleo notification recipient
	 * @param kaleoNotificationId the kaleo notification ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo notification recipient
	 * @throws NoSuchNotificationRecipientException if a kaleo notification recipient with the primary key could not be found
	 */
	@Override
	public KaleoNotificationRecipient[] findByKaleoNotificationId_PrevAndNext(
		long kaleoNotificationRecipientId, long kaleoNotificationId,
		OrderByComparator<KaleoNotificationRecipient> orderByComparator)
		throws NoSuchNotificationRecipientException {
		KaleoNotificationRecipient kaleoNotificationRecipient = findByPrimaryKey(kaleoNotificationRecipientId);

		Session session = null;

		try {
			session = openSession();

			KaleoNotificationRecipient[] array = new KaleoNotificationRecipientImpl[3];

			array[0] = getByKaleoNotificationId_PrevAndNext(session,
					kaleoNotificationRecipient, kaleoNotificationId,
					orderByComparator, true);

			array[1] = kaleoNotificationRecipient;

			array[2] = getByKaleoNotificationId_PrevAndNext(session,
					kaleoNotificationRecipient, kaleoNotificationId,
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

	protected KaleoNotificationRecipient getByKaleoNotificationId_PrevAndNext(
		Session session, KaleoNotificationRecipient kaleoNotificationRecipient,
		long kaleoNotificationId,
		OrderByComparator<KaleoNotificationRecipient> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEONOTIFICATIONRECIPIENT_WHERE);

		query.append(_FINDER_COLUMN_KALEONOTIFICATIONID_KALEONOTIFICATIONID_2);

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
			query.append(KaleoNotificationRecipientModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoNotificationId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoNotificationRecipient);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoNotificationRecipient> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo notification recipients where kaleoNotificationId = &#63; from the database.
	 *
	 * @param kaleoNotificationId the kaleo notification ID
	 */
	@Override
	public void removeByKaleoNotificationId(long kaleoNotificationId) {
		for (KaleoNotificationRecipient kaleoNotificationRecipient : findByKaleoNotificationId(
				kaleoNotificationId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoNotificationRecipient);
		}
	}

	/**
	 * Returns the number of kaleo notification recipients where kaleoNotificationId = &#63;.
	 *
	 * @param kaleoNotificationId the kaleo notification ID
	 * @return the number of matching kaleo notification recipients
	 */
	@Override
	public int countByKaleoNotificationId(long kaleoNotificationId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KALEONOTIFICATIONID;

		Object[] finderArgs = new Object[] { kaleoNotificationId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEONOTIFICATIONRECIPIENT_WHERE);

			query.append(_FINDER_COLUMN_KALEONOTIFICATIONID_KALEONOTIFICATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNotificationId);

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

	private static final String _FINDER_COLUMN_KALEONOTIFICATIONID_KALEONOTIFICATIONID_2 =
		"kaleoNotificationRecipient.kaleoNotificationId = ?";

	public KaleoNotificationRecipientPersistenceImpl() {
		setModelClass(KaleoNotificationRecipient.class);
	}

	/**
	 * Caches the kaleo notification recipient in the entity cache if it is enabled.
	 *
	 * @param kaleoNotificationRecipient the kaleo notification recipient
	 */
	@Override
	public void cacheResult(
		KaleoNotificationRecipient kaleoNotificationRecipient) {
		EntityCacheUtil.putResult(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationRecipientImpl.class,
			kaleoNotificationRecipient.getPrimaryKey(),
			kaleoNotificationRecipient);

		kaleoNotificationRecipient.resetOriginalValues();
	}

	/**
	 * Caches the kaleo notification recipients in the entity cache if it is enabled.
	 *
	 * @param kaleoNotificationRecipients the kaleo notification recipients
	 */
	@Override
	public void cacheResult(
		List<KaleoNotificationRecipient> kaleoNotificationRecipients) {
		for (KaleoNotificationRecipient kaleoNotificationRecipient : kaleoNotificationRecipients) {
			if (EntityCacheUtil.getResult(
						KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
						KaleoNotificationRecipientImpl.class,
						kaleoNotificationRecipient.getPrimaryKey()) == null) {
				cacheResult(kaleoNotificationRecipient);
			}
			else {
				kaleoNotificationRecipient.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all kaleo notification recipients.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(KaleoNotificationRecipientImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the kaleo notification recipient.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		KaleoNotificationRecipient kaleoNotificationRecipient) {
		EntityCacheUtil.removeResult(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationRecipientImpl.class,
			kaleoNotificationRecipient.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<KaleoNotificationRecipient> kaleoNotificationRecipients) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (KaleoNotificationRecipient kaleoNotificationRecipient : kaleoNotificationRecipients) {
			EntityCacheUtil.removeResult(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
				KaleoNotificationRecipientImpl.class,
				kaleoNotificationRecipient.getPrimaryKey());
		}
	}

	/**
	 * Creates a new kaleo notification recipient with the primary key. Does not add the kaleo notification recipient to the database.
	 *
	 * @param kaleoNotificationRecipientId the primary key for the new kaleo notification recipient
	 * @return the new kaleo notification recipient
	 */
	@Override
	public KaleoNotificationRecipient create(long kaleoNotificationRecipientId) {
		KaleoNotificationRecipient kaleoNotificationRecipient = new KaleoNotificationRecipientImpl();

		kaleoNotificationRecipient.setNew(true);
		kaleoNotificationRecipient.setPrimaryKey(kaleoNotificationRecipientId);

		return kaleoNotificationRecipient;
	}

	/**
	 * Removes the kaleo notification recipient with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoNotificationRecipientId the primary key of the kaleo notification recipient
	 * @return the kaleo notification recipient that was removed
	 * @throws NoSuchNotificationRecipientException if a kaleo notification recipient with the primary key could not be found
	 */
	@Override
	public KaleoNotificationRecipient remove(long kaleoNotificationRecipientId)
		throws NoSuchNotificationRecipientException {
		return remove((Serializable)kaleoNotificationRecipientId);
	}

	/**
	 * Removes the kaleo notification recipient with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo notification recipient
	 * @return the kaleo notification recipient that was removed
	 * @throws NoSuchNotificationRecipientException if a kaleo notification recipient with the primary key could not be found
	 */
	@Override
	public KaleoNotificationRecipient remove(Serializable primaryKey)
		throws NoSuchNotificationRecipientException {
		Session session = null;

		try {
			session = openSession();

			KaleoNotificationRecipient kaleoNotificationRecipient = (KaleoNotificationRecipient)session.get(KaleoNotificationRecipientImpl.class,
					primaryKey);

			if (kaleoNotificationRecipient == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNotificationRecipientException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(kaleoNotificationRecipient);
		}
		catch (NoSuchNotificationRecipientException nsee) {
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
	protected KaleoNotificationRecipient removeImpl(
		KaleoNotificationRecipient kaleoNotificationRecipient) {
		kaleoNotificationRecipient = toUnwrappedModel(kaleoNotificationRecipient);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(kaleoNotificationRecipient)) {
				kaleoNotificationRecipient = (KaleoNotificationRecipient)session.get(KaleoNotificationRecipientImpl.class,
						kaleoNotificationRecipient.getPrimaryKeyObj());
			}

			if (kaleoNotificationRecipient != null) {
				session.delete(kaleoNotificationRecipient);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (kaleoNotificationRecipient != null) {
			clearCache(kaleoNotificationRecipient);
		}

		return kaleoNotificationRecipient;
	}

	@Override
	public KaleoNotificationRecipient updateImpl(
		KaleoNotificationRecipient kaleoNotificationRecipient) {
		kaleoNotificationRecipient = toUnwrappedModel(kaleoNotificationRecipient);

		boolean isNew = kaleoNotificationRecipient.isNew();

		KaleoNotificationRecipientModelImpl kaleoNotificationRecipientModelImpl = (KaleoNotificationRecipientModelImpl)kaleoNotificationRecipient;

		Session session = null;

		try {
			session = openSession();

			if (kaleoNotificationRecipient.isNew()) {
				session.save(kaleoNotificationRecipient);

				kaleoNotificationRecipient.setNew(false);
			}
			else {
				session.merge(kaleoNotificationRecipient);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew ||
				!KaleoNotificationRecipientModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((kaleoNotificationRecipientModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoNotificationRecipientModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] {
						kaleoNotificationRecipientModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((kaleoNotificationRecipientModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoNotificationRecipientModelImpl.getOriginalKaleoDefinitionId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);

				args = new Object[] {
						kaleoNotificationRecipientModelImpl.getKaleoDefinitionId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);
			}

			if ((kaleoNotificationRecipientModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEONOTIFICATIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoNotificationRecipientModelImpl.getOriginalKaleoNotificationId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEONOTIFICATIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEONOTIFICATIONID,
					args);

				args = new Object[] {
						kaleoNotificationRecipientModelImpl.getKaleoNotificationId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEONOTIFICATIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEONOTIFICATIONID,
					args);
			}
		}

		EntityCacheUtil.putResult(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
			KaleoNotificationRecipientImpl.class,
			kaleoNotificationRecipient.getPrimaryKey(),
			kaleoNotificationRecipient, false);

		kaleoNotificationRecipient.resetOriginalValues();

		return kaleoNotificationRecipient;
	}

	protected KaleoNotificationRecipient toUnwrappedModel(
		KaleoNotificationRecipient kaleoNotificationRecipient) {
		if (kaleoNotificationRecipient instanceof KaleoNotificationRecipientImpl) {
			return kaleoNotificationRecipient;
		}

		KaleoNotificationRecipientImpl kaleoNotificationRecipientImpl = new KaleoNotificationRecipientImpl();

		kaleoNotificationRecipientImpl.setNew(kaleoNotificationRecipient.isNew());
		kaleoNotificationRecipientImpl.setPrimaryKey(kaleoNotificationRecipient.getPrimaryKey());

		kaleoNotificationRecipientImpl.setKaleoNotificationRecipientId(kaleoNotificationRecipient.getKaleoNotificationRecipientId());
		kaleoNotificationRecipientImpl.setGroupId(kaleoNotificationRecipient.getGroupId());
		kaleoNotificationRecipientImpl.setCompanyId(kaleoNotificationRecipient.getCompanyId());
		kaleoNotificationRecipientImpl.setUserId(kaleoNotificationRecipient.getUserId());
		kaleoNotificationRecipientImpl.setUserName(kaleoNotificationRecipient.getUserName());
		kaleoNotificationRecipientImpl.setCreateDate(kaleoNotificationRecipient.getCreateDate());
		kaleoNotificationRecipientImpl.setModifiedDate(kaleoNotificationRecipient.getModifiedDate());
		kaleoNotificationRecipientImpl.setKaleoDefinitionId(kaleoNotificationRecipient.getKaleoDefinitionId());
		kaleoNotificationRecipientImpl.setKaleoNotificationId(kaleoNotificationRecipient.getKaleoNotificationId());
		kaleoNotificationRecipientImpl.setRecipientClassName(kaleoNotificationRecipient.getRecipientClassName());
		kaleoNotificationRecipientImpl.setRecipientClassPK(kaleoNotificationRecipient.getRecipientClassPK());
		kaleoNotificationRecipientImpl.setRecipientRoleType(kaleoNotificationRecipient.getRecipientRoleType());
		kaleoNotificationRecipientImpl.setAddress(kaleoNotificationRecipient.getAddress());

		return kaleoNotificationRecipientImpl;
	}

	/**
	 * Returns the kaleo notification recipient with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo notification recipient
	 * @return the kaleo notification recipient
	 * @throws NoSuchNotificationRecipientException if a kaleo notification recipient with the primary key could not be found
	 */
	@Override
	public KaleoNotificationRecipient findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNotificationRecipientException {
		KaleoNotificationRecipient kaleoNotificationRecipient = fetchByPrimaryKey(primaryKey);

		if (kaleoNotificationRecipient == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNotificationRecipientException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return kaleoNotificationRecipient;
	}

	/**
	 * Returns the kaleo notification recipient with the primary key or throws a {@link NoSuchNotificationRecipientException} if it could not be found.
	 *
	 * @param kaleoNotificationRecipientId the primary key of the kaleo notification recipient
	 * @return the kaleo notification recipient
	 * @throws NoSuchNotificationRecipientException if a kaleo notification recipient with the primary key could not be found
	 */
	@Override
	public KaleoNotificationRecipient findByPrimaryKey(
		long kaleoNotificationRecipientId)
		throws NoSuchNotificationRecipientException {
		return findByPrimaryKey((Serializable)kaleoNotificationRecipientId);
	}

	/**
	 * Returns the kaleo notification recipient with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo notification recipient
	 * @return the kaleo notification recipient, or <code>null</code> if a kaleo notification recipient with the primary key could not be found
	 */
	@Override
	public KaleoNotificationRecipient fetchByPrimaryKey(Serializable primaryKey) {
		KaleoNotificationRecipient kaleoNotificationRecipient = (KaleoNotificationRecipient)EntityCacheUtil.getResult(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
				KaleoNotificationRecipientImpl.class, primaryKey);

		if (kaleoNotificationRecipient == _nullKaleoNotificationRecipient) {
			return null;
		}

		if (kaleoNotificationRecipient == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoNotificationRecipient = (KaleoNotificationRecipient)session.get(KaleoNotificationRecipientImpl.class,
						primaryKey);

				if (kaleoNotificationRecipient != null) {
					cacheResult(kaleoNotificationRecipient);
				}
				else {
					EntityCacheUtil.putResult(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
						KaleoNotificationRecipientImpl.class, primaryKey,
						_nullKaleoNotificationRecipient);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
					KaleoNotificationRecipientImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return kaleoNotificationRecipient;
	}

	/**
	 * Returns the kaleo notification recipient with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoNotificationRecipientId the primary key of the kaleo notification recipient
	 * @return the kaleo notification recipient, or <code>null</code> if a kaleo notification recipient with the primary key could not be found
	 */
	@Override
	public KaleoNotificationRecipient fetchByPrimaryKey(
		long kaleoNotificationRecipientId) {
		return fetchByPrimaryKey((Serializable)kaleoNotificationRecipientId);
	}

	@Override
	public Map<Serializable, KaleoNotificationRecipient> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, KaleoNotificationRecipient> map = new HashMap<Serializable, KaleoNotificationRecipient>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			KaleoNotificationRecipient kaleoNotificationRecipient = fetchByPrimaryKey(primaryKey);

			if (kaleoNotificationRecipient != null) {
				map.put(primaryKey, kaleoNotificationRecipient);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			KaleoNotificationRecipient kaleoNotificationRecipient = (KaleoNotificationRecipient)EntityCacheUtil.getResult(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
					KaleoNotificationRecipientImpl.class, primaryKey);

			if (kaleoNotificationRecipient == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, kaleoNotificationRecipient);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_KALEONOTIFICATIONRECIPIENT_WHERE_PKS_IN);

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

			for (KaleoNotificationRecipient kaleoNotificationRecipient : (List<KaleoNotificationRecipient>)q.list()) {
				map.put(kaleoNotificationRecipient.getPrimaryKeyObj(),
					kaleoNotificationRecipient);

				cacheResult(kaleoNotificationRecipient);

				uncachedPrimaryKeys.remove(kaleoNotificationRecipient.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(KaleoNotificationRecipientModelImpl.ENTITY_CACHE_ENABLED,
					KaleoNotificationRecipientImpl.class, primaryKey,
					_nullKaleoNotificationRecipient);
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
	 * Returns all the kaleo notification recipients.
	 *
	 * @return the kaleo notification recipients
	 */
	@Override
	public List<KaleoNotificationRecipient> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo notification recipients.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationRecipientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo notification recipients
	 * @param end the upper bound of the range of kaleo notification recipients (not inclusive)
	 * @return the range of kaleo notification recipients
	 */
	@Override
	public List<KaleoNotificationRecipient> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo notification recipients.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoNotificationRecipientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo notification recipients
	 * @param end the upper bound of the range of kaleo notification recipients (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo notification recipients
	 */
	@Override
	public List<KaleoNotificationRecipient> findAll(int start, int end,
		OrderByComparator<KaleoNotificationRecipient> orderByComparator) {
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

		List<KaleoNotificationRecipient> list = (List<KaleoNotificationRecipient>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KALEONOTIFICATIONRECIPIENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEONOTIFICATIONRECIPIENT;

				if (pagination) {
					sql = sql.concat(KaleoNotificationRecipientModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<KaleoNotificationRecipient>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoNotificationRecipient>)QueryUtil.list(q,
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
	 * Removes all the kaleo notification recipients from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (KaleoNotificationRecipient kaleoNotificationRecipient : findAll()) {
			remove(kaleoNotificationRecipient);
		}
	}

	/**
	 * Returns the number of kaleo notification recipients.
	 *
	 * @return the number of kaleo notification recipients
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KALEONOTIFICATIONRECIPIENT);

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

	/**
	 * Initializes the kaleo notification recipient persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(KaleoNotificationRecipientImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_KALEONOTIFICATIONRECIPIENT = "SELECT kaleoNotificationRecipient FROM KaleoNotificationRecipient kaleoNotificationRecipient";
	private static final String _SQL_SELECT_KALEONOTIFICATIONRECIPIENT_WHERE_PKS_IN =
		"SELECT kaleoNotificationRecipient FROM KaleoNotificationRecipient kaleoNotificationRecipient WHERE kaleoNotificationRecipientId IN (";
	private static final String _SQL_SELECT_KALEONOTIFICATIONRECIPIENT_WHERE = "SELECT kaleoNotificationRecipient FROM KaleoNotificationRecipient kaleoNotificationRecipient WHERE ";
	private static final String _SQL_COUNT_KALEONOTIFICATIONRECIPIENT = "SELECT COUNT(kaleoNotificationRecipient) FROM KaleoNotificationRecipient kaleoNotificationRecipient";
	private static final String _SQL_COUNT_KALEONOTIFICATIONRECIPIENT_WHERE = "SELECT COUNT(kaleoNotificationRecipient) FROM KaleoNotificationRecipient kaleoNotificationRecipient WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoNotificationRecipient.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoNotificationRecipient exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoNotificationRecipient exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(KaleoNotificationRecipientPersistenceImpl.class);
	private static final KaleoNotificationRecipient _nullKaleoNotificationRecipient =
		new KaleoNotificationRecipientImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<KaleoNotificationRecipient> toCacheModel() {
				return _nullKaleoNotificationRecipientCacheModel;
			}
		};

	private static final CacheModel<KaleoNotificationRecipient> _nullKaleoNotificationRecipientCacheModel =
		new CacheModel<KaleoNotificationRecipient>() {
			@Override
			public KaleoNotificationRecipient toEntityModel() {
				return _nullKaleoNotificationRecipient;
			}
		};
}