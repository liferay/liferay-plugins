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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.NoSuchLogException;
import com.liferay.portal.workflow.kaleo.model.KaleoLog;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoLogImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoLogModelImpl;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoLogPersistence;

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
 * The persistence implementation for the kaleo log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoLogPersistence
 * @see com.liferay.portal.workflow.kaleo.service.persistence.KaleoLogUtil
 * @generated
 */
@ProviderType
public class KaleoLogPersistenceImpl extends BasePersistenceImpl<KaleoLog>
	implements KaleoLogPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KaleoLogUtil} to access the kaleo log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoLogImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, KaleoLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, KaleoLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, KaleoLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, KaleoLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			KaleoLogModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo logs where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching kaleo logs
	 */
	@Override
	public List<KaleoLog> findByCompanyId(long companyId) {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the kaleo logs where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo logs
	 * @param end the upper bound of the range of kaleo logs (not inclusive)
	 * @return the range of matching kaleo logs
	 */
	@Override
	public List<KaleoLog> findByCompanyId(long companyId, int start, int end) {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo logs where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo logs
	 * @param end the upper bound of the range of kaleo logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo logs
	 */
	@Override
	public List<KaleoLog> findByCompanyId(long companyId, int start, int end,
		OrderByComparator<KaleoLog> orderByComparator) {
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

		List<KaleoLog> list = (List<KaleoLog>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoLog kaleoLog : list) {
				if ((companyId != kaleoLog.getCompanyId())) {
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

			query.append(_SQL_SELECT_KALEOLOG_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<KaleoLog>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoLog>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first kaleo log in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo log
	 * @throws NoSuchLogException if a matching kaleo log could not be found
	 */
	@Override
	public KaleoLog findByCompanyId_First(long companyId,
		OrderByComparator<KaleoLog> orderByComparator)
		throws NoSuchLogException {
		KaleoLog kaleoLog = fetchByCompanyId_First(companyId, orderByComparator);

		if (kaleoLog != null) {
			return kaleoLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLogException(msg.toString());
	}

	/**
	 * Returns the first kaleo log in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo log, or <code>null</code> if a matching kaleo log could not be found
	 */
	@Override
	public KaleoLog fetchByCompanyId_First(long companyId,
		OrderByComparator<KaleoLog> orderByComparator) {
		List<KaleoLog> list = findByCompanyId(companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo log in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo log
	 * @throws NoSuchLogException if a matching kaleo log could not be found
	 */
	@Override
	public KaleoLog findByCompanyId_Last(long companyId,
		OrderByComparator<KaleoLog> orderByComparator)
		throws NoSuchLogException {
		KaleoLog kaleoLog = fetchByCompanyId_Last(companyId, orderByComparator);

		if (kaleoLog != null) {
			return kaleoLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLogException(msg.toString());
	}

	/**
	 * Returns the last kaleo log in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo log, or <code>null</code> if a matching kaleo log could not be found
	 */
	@Override
	public KaleoLog fetchByCompanyId_Last(long companyId,
		OrderByComparator<KaleoLog> orderByComparator) {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<KaleoLog> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo logs before and after the current kaleo log in the ordered set where companyId = &#63;.
	 *
	 * @param kaleoLogId the primary key of the current kaleo log
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo log
	 * @throws NoSuchLogException if a kaleo log with the primary key could not be found
	 */
	@Override
	public KaleoLog[] findByCompanyId_PrevAndNext(long kaleoLogId,
		long companyId, OrderByComparator<KaleoLog> orderByComparator)
		throws NoSuchLogException {
		KaleoLog kaleoLog = findByPrimaryKey(kaleoLogId);

		Session session = null;

		try {
			session = openSession();

			KaleoLog[] array = new KaleoLogImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, kaleoLog, companyId,
					orderByComparator, true);

			array[1] = kaleoLog;

			array[2] = getByCompanyId_PrevAndNext(session, kaleoLog, companyId,
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

	protected KaleoLog getByCompanyId_PrevAndNext(Session session,
		KaleoLog kaleoLog, long companyId,
		OrderByComparator<KaleoLog> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOLOG_WHERE);

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
			query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo logs where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (KaleoLog kaleoLog : findByCompanyId(companyId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(kaleoLog);
		}
	}

	/**
	 * Returns the number of kaleo logs where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching kaleo logs
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOLOG_WHERE);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoLog.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEODEFINITIONID =
		new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, KaleoLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID =
		new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, KaleoLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByKaleoDefinitionId", new String[] { Long.class.getName() },
			KaleoLogModelImpl.KALEODEFINITIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEODEFINITIONID = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKaleoDefinitionId", new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo logs where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the matching kaleo logs
	 */
	@Override
	public List<KaleoLog> findByKaleoDefinitionId(long kaleoDefinitionId) {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo logs where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo logs
	 * @param end the upper bound of the range of kaleo logs (not inclusive)
	 * @return the range of matching kaleo logs
	 */
	@Override
	public List<KaleoLog> findByKaleoDefinitionId(long kaleoDefinitionId,
		int start, int end) {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo logs where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo logs
	 * @param end the upper bound of the range of kaleo logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo logs
	 */
	@Override
	public List<KaleoLog> findByKaleoDefinitionId(long kaleoDefinitionId,
		int start, int end, OrderByComparator<KaleoLog> orderByComparator) {
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

		List<KaleoLog> list = (List<KaleoLog>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoLog kaleoLog : list) {
				if ((kaleoDefinitionId != kaleoLog.getKaleoDefinitionId())) {
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

			query.append(_SQL_SELECT_KALEOLOG_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				if (!pagination) {
					list = (List<KaleoLog>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoLog>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first kaleo log in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo log
	 * @throws NoSuchLogException if a matching kaleo log could not be found
	 */
	@Override
	public KaleoLog findByKaleoDefinitionId_First(long kaleoDefinitionId,
		OrderByComparator<KaleoLog> orderByComparator)
		throws NoSuchLogException {
		KaleoLog kaleoLog = fetchByKaleoDefinitionId_First(kaleoDefinitionId,
				orderByComparator);

		if (kaleoLog != null) {
			return kaleoLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoDefinitionId=");
		msg.append(kaleoDefinitionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLogException(msg.toString());
	}

	/**
	 * Returns the first kaleo log in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo log, or <code>null</code> if a matching kaleo log could not be found
	 */
	@Override
	public KaleoLog fetchByKaleoDefinitionId_First(long kaleoDefinitionId,
		OrderByComparator<KaleoLog> orderByComparator) {
		List<KaleoLog> list = findByKaleoDefinitionId(kaleoDefinitionId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo log in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo log
	 * @throws NoSuchLogException if a matching kaleo log could not be found
	 */
	@Override
	public KaleoLog findByKaleoDefinitionId_Last(long kaleoDefinitionId,
		OrderByComparator<KaleoLog> orderByComparator)
		throws NoSuchLogException {
		KaleoLog kaleoLog = fetchByKaleoDefinitionId_Last(kaleoDefinitionId,
				orderByComparator);

		if (kaleoLog != null) {
			return kaleoLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoDefinitionId=");
		msg.append(kaleoDefinitionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLogException(msg.toString());
	}

	/**
	 * Returns the last kaleo log in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo log, or <code>null</code> if a matching kaleo log could not be found
	 */
	@Override
	public KaleoLog fetchByKaleoDefinitionId_Last(long kaleoDefinitionId,
		OrderByComparator<KaleoLog> orderByComparator) {
		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		if (count == 0) {
			return null;
		}

		List<KaleoLog> list = findByKaleoDefinitionId(kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo logs before and after the current kaleo log in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoLogId the primary key of the current kaleo log
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo log
	 * @throws NoSuchLogException if a kaleo log with the primary key could not be found
	 */
	@Override
	public KaleoLog[] findByKaleoDefinitionId_PrevAndNext(long kaleoLogId,
		long kaleoDefinitionId, OrderByComparator<KaleoLog> orderByComparator)
		throws NoSuchLogException {
		KaleoLog kaleoLog = findByPrimaryKey(kaleoLogId);

		Session session = null;

		try {
			session = openSession();

			KaleoLog[] array = new KaleoLogImpl[3];

			array[0] = getByKaleoDefinitionId_PrevAndNext(session, kaleoLog,
					kaleoDefinitionId, orderByComparator, true);

			array[1] = kaleoLog;

			array[2] = getByKaleoDefinitionId_PrevAndNext(session, kaleoLog,
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

	protected KaleoLog getByKaleoDefinitionId_PrevAndNext(Session session,
		KaleoLog kaleoLog, long kaleoDefinitionId,
		OrderByComparator<KaleoLog> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOLOG_WHERE);

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
			query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo logs where kaleoDefinitionId = &#63; from the database.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 */
	@Override
	public void removeByKaleoDefinitionId(long kaleoDefinitionId) {
		for (KaleoLog kaleoLog : findByKaleoDefinitionId(kaleoDefinitionId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoLog);
		}
	}

	/**
	 * Returns the number of kaleo logs where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the number of matching kaleo logs
	 */
	@Override
	public int countByKaleoDefinitionId(long kaleoDefinitionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KALEODEFINITIONID;

		Object[] finderArgs = new Object[] { kaleoDefinitionId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOLOG_WHERE);

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
		"kaleoLog.kaleoDefinitionId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEOINSTANCEID =
		new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, KaleoLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKaleoInstanceId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOINSTANCEID =
		new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, KaleoLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByKaleoInstanceId",
			new String[] { Long.class.getName() },
			KaleoLogModelImpl.KALEOINSTANCEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEOINSTANCEID = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKaleoInstanceId", new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo logs where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @return the matching kaleo logs
	 */
	@Override
	public List<KaleoLog> findByKaleoInstanceId(long kaleoInstanceId) {
		return findByKaleoInstanceId(kaleoInstanceId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo logs where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param start the lower bound of the range of kaleo logs
	 * @param end the upper bound of the range of kaleo logs (not inclusive)
	 * @return the range of matching kaleo logs
	 */
	@Override
	public List<KaleoLog> findByKaleoInstanceId(long kaleoInstanceId,
		int start, int end) {
		return findByKaleoInstanceId(kaleoInstanceId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo logs where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param start the lower bound of the range of kaleo logs
	 * @param end the upper bound of the range of kaleo logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo logs
	 */
	@Override
	public List<KaleoLog> findByKaleoInstanceId(long kaleoInstanceId,
		int start, int end, OrderByComparator<KaleoLog> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
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

		List<KaleoLog> list = (List<KaleoLog>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoLog kaleoLog : list) {
				if ((kaleoInstanceId != kaleoLog.getKaleoInstanceId())) {
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

			query.append(_SQL_SELECT_KALEOLOG_WHERE);

			query.append(_FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceId);

				if (!pagination) {
					list = (List<KaleoLog>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoLog>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first kaleo log in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo log
	 * @throws NoSuchLogException if a matching kaleo log could not be found
	 */
	@Override
	public KaleoLog findByKaleoInstanceId_First(long kaleoInstanceId,
		OrderByComparator<KaleoLog> orderByComparator)
		throws NoSuchLogException {
		KaleoLog kaleoLog = fetchByKaleoInstanceId_First(kaleoInstanceId,
				orderByComparator);

		if (kaleoLog != null) {
			return kaleoLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoInstanceId=");
		msg.append(kaleoInstanceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLogException(msg.toString());
	}

	/**
	 * Returns the first kaleo log in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo log, or <code>null</code> if a matching kaleo log could not be found
	 */
	@Override
	public KaleoLog fetchByKaleoInstanceId_First(long kaleoInstanceId,
		OrderByComparator<KaleoLog> orderByComparator) {
		List<KaleoLog> list = findByKaleoInstanceId(kaleoInstanceId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo log in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo log
	 * @throws NoSuchLogException if a matching kaleo log could not be found
	 */
	@Override
	public KaleoLog findByKaleoInstanceId_Last(long kaleoInstanceId,
		OrderByComparator<KaleoLog> orderByComparator)
		throws NoSuchLogException {
		KaleoLog kaleoLog = fetchByKaleoInstanceId_Last(kaleoInstanceId,
				orderByComparator);

		if (kaleoLog != null) {
			return kaleoLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoInstanceId=");
		msg.append(kaleoInstanceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLogException(msg.toString());
	}

	/**
	 * Returns the last kaleo log in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo log, or <code>null</code> if a matching kaleo log could not be found
	 */
	@Override
	public KaleoLog fetchByKaleoInstanceId_Last(long kaleoInstanceId,
		OrderByComparator<KaleoLog> orderByComparator) {
		int count = countByKaleoInstanceId(kaleoInstanceId);

		if (count == 0) {
			return null;
		}

		List<KaleoLog> list = findByKaleoInstanceId(kaleoInstanceId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo logs before and after the current kaleo log in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoLogId the primary key of the current kaleo log
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo log
	 * @throws NoSuchLogException if a kaleo log with the primary key could not be found
	 */
	@Override
	public KaleoLog[] findByKaleoInstanceId_PrevAndNext(long kaleoLogId,
		long kaleoInstanceId, OrderByComparator<KaleoLog> orderByComparator)
		throws NoSuchLogException {
		KaleoLog kaleoLog = findByPrimaryKey(kaleoLogId);

		Session session = null;

		try {
			session = openSession();

			KaleoLog[] array = new KaleoLogImpl[3];

			array[0] = getByKaleoInstanceId_PrevAndNext(session, kaleoLog,
					kaleoInstanceId, orderByComparator, true);

			array[1] = kaleoLog;

			array[2] = getByKaleoInstanceId_PrevAndNext(session, kaleoLog,
					kaleoInstanceId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoLog getByKaleoInstanceId_PrevAndNext(Session session,
		KaleoLog kaleoLog, long kaleoInstanceId,
		OrderByComparator<KaleoLog> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOLOG_WHERE);

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
			query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoInstanceId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo logs where kaleoInstanceId = &#63; from the database.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 */
	@Override
	public void removeByKaleoInstanceId(long kaleoInstanceId) {
		for (KaleoLog kaleoLog : findByKaleoInstanceId(kaleoInstanceId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoLog);
		}
	}

	/**
	 * Returns the number of kaleo logs where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @return the number of matching kaleo logs
	 */
	@Override
	public int countByKaleoInstanceId(long kaleoInstanceId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KALEOINSTANCEID;

		Object[] finderArgs = new Object[] { kaleoInstanceId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOLOG_WHERE);

			query.append(_FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceId);

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

	private static final String _FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2 =
		"kaleoLog.kaleoInstanceId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEOTASKINSTANCETOKENID =
		new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, KaleoLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByKaleoTaskInstanceTokenId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOTASKINSTANCETOKENID =
		new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, KaleoLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByKaleoTaskInstanceTokenId",
			new String[] { Long.class.getName() },
			KaleoLogModelImpl.KALEOTASKINSTANCETOKENID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEOTASKINSTANCETOKENID =
		new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKaleoTaskInstanceTokenId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo logs where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	 * @return the matching kaleo logs
	 */
	@Override
	public List<KaleoLog> findByKaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId) {
		return findByKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo logs where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	 * @param start the lower bound of the range of kaleo logs
	 * @param end the upper bound of the range of kaleo logs (not inclusive)
	 * @return the range of matching kaleo logs
	 */
	@Override
	public List<KaleoLog> findByKaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId, int start, int end) {
		return findByKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo logs where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	 * @param start the lower bound of the range of kaleo logs
	 * @param end the upper bound of the range of kaleo logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo logs
	 */
	@Override
	public List<KaleoLog> findByKaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId, int start, int end,
		OrderByComparator<KaleoLog> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOTASKINSTANCETOKENID;
			finderArgs = new Object[] { kaleoTaskInstanceTokenId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEOTASKINSTANCETOKENID;
			finderArgs = new Object[] {
					kaleoTaskInstanceTokenId,
					
					start, end, orderByComparator
				};
		}

		List<KaleoLog> list = (List<KaleoLog>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoLog kaleoLog : list) {
				if ((kaleoTaskInstanceTokenId != kaleoLog.getKaleoTaskInstanceTokenId())) {
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

			query.append(_SQL_SELECT_KALEOLOG_WHERE);

			query.append(_FINDER_COLUMN_KALEOTASKINSTANCETOKENID_KALEOTASKINSTANCETOKENID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoTaskInstanceTokenId);

				if (!pagination) {
					list = (List<KaleoLog>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoLog>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first kaleo log in the ordered set where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo log
	 * @throws NoSuchLogException if a matching kaleo log could not be found
	 */
	@Override
	public KaleoLog findByKaleoTaskInstanceTokenId_First(
		long kaleoTaskInstanceTokenId,
		OrderByComparator<KaleoLog> orderByComparator)
		throws NoSuchLogException {
		KaleoLog kaleoLog = fetchByKaleoTaskInstanceTokenId_First(kaleoTaskInstanceTokenId,
				orderByComparator);

		if (kaleoLog != null) {
			return kaleoLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoTaskInstanceTokenId=");
		msg.append(kaleoTaskInstanceTokenId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLogException(msg.toString());
	}

	/**
	 * Returns the first kaleo log in the ordered set where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo log, or <code>null</code> if a matching kaleo log could not be found
	 */
	@Override
	public KaleoLog fetchByKaleoTaskInstanceTokenId_First(
		long kaleoTaskInstanceTokenId,
		OrderByComparator<KaleoLog> orderByComparator) {
		List<KaleoLog> list = findByKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo log in the ordered set where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo log
	 * @throws NoSuchLogException if a matching kaleo log could not be found
	 */
	@Override
	public KaleoLog findByKaleoTaskInstanceTokenId_Last(
		long kaleoTaskInstanceTokenId,
		OrderByComparator<KaleoLog> orderByComparator)
		throws NoSuchLogException {
		KaleoLog kaleoLog = fetchByKaleoTaskInstanceTokenId_Last(kaleoTaskInstanceTokenId,
				orderByComparator);

		if (kaleoLog != null) {
			return kaleoLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoTaskInstanceTokenId=");
		msg.append(kaleoTaskInstanceTokenId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLogException(msg.toString());
	}

	/**
	 * Returns the last kaleo log in the ordered set where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo log, or <code>null</code> if a matching kaleo log could not be found
	 */
	@Override
	public KaleoLog fetchByKaleoTaskInstanceTokenId_Last(
		long kaleoTaskInstanceTokenId,
		OrderByComparator<KaleoLog> orderByComparator) {
		int count = countByKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);

		if (count == 0) {
			return null;
		}

		List<KaleoLog> list = findByKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo logs before and after the current kaleo log in the ordered set where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * @param kaleoLogId the primary key of the current kaleo log
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo log
	 * @throws NoSuchLogException if a kaleo log with the primary key could not be found
	 */
	@Override
	public KaleoLog[] findByKaleoTaskInstanceTokenId_PrevAndNext(
		long kaleoLogId, long kaleoTaskInstanceTokenId,
		OrderByComparator<KaleoLog> orderByComparator)
		throws NoSuchLogException {
		KaleoLog kaleoLog = findByPrimaryKey(kaleoLogId);

		Session session = null;

		try {
			session = openSession();

			KaleoLog[] array = new KaleoLogImpl[3];

			array[0] = getByKaleoTaskInstanceTokenId_PrevAndNext(session,
					kaleoLog, kaleoTaskInstanceTokenId, orderByComparator, true);

			array[1] = kaleoLog;

			array[2] = getByKaleoTaskInstanceTokenId_PrevAndNext(session,
					kaleoLog, kaleoTaskInstanceTokenId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoLog getByKaleoTaskInstanceTokenId_PrevAndNext(
		Session session, KaleoLog kaleoLog, long kaleoTaskInstanceTokenId,
		OrderByComparator<KaleoLog> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOLOG_WHERE);

		query.append(_FINDER_COLUMN_KALEOTASKINSTANCETOKENID_KALEOTASKINSTANCETOKENID_2);

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
			query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoTaskInstanceTokenId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo logs where kaleoTaskInstanceTokenId = &#63; from the database.
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	 */
	@Override
	public void removeByKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId) {
		for (KaleoLog kaleoLog : findByKaleoTaskInstanceTokenId(
				kaleoTaskInstanceTokenId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(kaleoLog);
		}
	}

	/**
	 * Returns the number of kaleo logs where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token ID
	 * @return the number of matching kaleo logs
	 */
	@Override
	public int countByKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KALEOTASKINSTANCETOKENID;

		Object[] finderArgs = new Object[] { kaleoTaskInstanceTokenId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOLOG_WHERE);

			query.append(_FINDER_COLUMN_KALEOTASKINSTANCETOKENID_KALEOTASKINSTANCETOKENID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoTaskInstanceTokenId);

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

	private static final String _FINDER_COLUMN_KALEOTASKINSTANCETOKENID_KALEOTASKINSTANCETOKENID_2 =
		"kaleoLog.kaleoTaskInstanceTokenId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KITI_T = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, KaleoLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKITI_T",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KITI_T =
		new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, KaleoLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByKITI_T",
			new String[] { Long.class.getName(), String.class.getName() },
			KaleoLogModelImpl.KALEOINSTANCETOKENID_COLUMN_BITMASK |
			KaleoLogModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KITI_T = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKITI_T",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the kaleo logs where kaleoInstanceTokenId = &#63; and type = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param type the type
	 * @return the matching kaleo logs
	 */
	@Override
	public List<KaleoLog> findByKITI_T(long kaleoInstanceTokenId, String type) {
		return findByKITI_T(kaleoInstanceTokenId, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo logs where kaleoInstanceTokenId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param type the type
	 * @param start the lower bound of the range of kaleo logs
	 * @param end the upper bound of the range of kaleo logs (not inclusive)
	 * @return the range of matching kaleo logs
	 */
	@Override
	public List<KaleoLog> findByKITI_T(long kaleoInstanceTokenId, String type,
		int start, int end) {
		return findByKITI_T(kaleoInstanceTokenId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo logs where kaleoInstanceTokenId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param type the type
	 * @param start the lower bound of the range of kaleo logs
	 * @param end the upper bound of the range of kaleo logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo logs
	 */
	@Override
	public List<KaleoLog> findByKITI_T(long kaleoInstanceTokenId, String type,
		int start, int end, OrderByComparator<KaleoLog> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KITI_T;
			finderArgs = new Object[] { kaleoInstanceTokenId, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_KITI_T;
			finderArgs = new Object[] {
					kaleoInstanceTokenId, type,
					
					start, end, orderByComparator
				};
		}

		List<KaleoLog> list = (List<KaleoLog>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoLog kaleoLog : list) {
				if ((kaleoInstanceTokenId != kaleoLog.getKaleoInstanceTokenId()) ||
						!Validator.equals(type, kaleoLog.getType())) {
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

			query.append(_SQL_SELECT_KALEOLOG_WHERE);

			query.append(_FINDER_COLUMN_KITI_T_KALEOINSTANCETOKENID_2);

			boolean bindType = false;

			if (type == null) {
				query.append(_FINDER_COLUMN_KITI_T_TYPE_1);
			}
			else if (type.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KITI_T_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_KITI_T_TYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceTokenId);

				if (bindType) {
					qPos.add(type);
				}

				if (!pagination) {
					list = (List<KaleoLog>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoLog>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first kaleo log in the ordered set where kaleoInstanceTokenId = &#63; and type = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo log
	 * @throws NoSuchLogException if a matching kaleo log could not be found
	 */
	@Override
	public KaleoLog findByKITI_T_First(long kaleoInstanceTokenId, String type,
		OrderByComparator<KaleoLog> orderByComparator)
		throws NoSuchLogException {
		KaleoLog kaleoLog = fetchByKITI_T_First(kaleoInstanceTokenId, type,
				orderByComparator);

		if (kaleoLog != null) {
			return kaleoLog;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoInstanceTokenId=");
		msg.append(kaleoInstanceTokenId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLogException(msg.toString());
	}

	/**
	 * Returns the first kaleo log in the ordered set where kaleoInstanceTokenId = &#63; and type = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo log, or <code>null</code> if a matching kaleo log could not be found
	 */
	@Override
	public KaleoLog fetchByKITI_T_First(long kaleoInstanceTokenId, String type,
		OrderByComparator<KaleoLog> orderByComparator) {
		List<KaleoLog> list = findByKITI_T(kaleoInstanceTokenId, type, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo log in the ordered set where kaleoInstanceTokenId = &#63; and type = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo log
	 * @throws NoSuchLogException if a matching kaleo log could not be found
	 */
	@Override
	public KaleoLog findByKITI_T_Last(long kaleoInstanceTokenId, String type,
		OrderByComparator<KaleoLog> orderByComparator)
		throws NoSuchLogException {
		KaleoLog kaleoLog = fetchByKITI_T_Last(kaleoInstanceTokenId, type,
				orderByComparator);

		if (kaleoLog != null) {
			return kaleoLog;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoInstanceTokenId=");
		msg.append(kaleoInstanceTokenId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLogException(msg.toString());
	}

	/**
	 * Returns the last kaleo log in the ordered set where kaleoInstanceTokenId = &#63; and type = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo log, or <code>null</code> if a matching kaleo log could not be found
	 */
	@Override
	public KaleoLog fetchByKITI_T_Last(long kaleoInstanceTokenId, String type,
		OrderByComparator<KaleoLog> orderByComparator) {
		int count = countByKITI_T(kaleoInstanceTokenId, type);

		if (count == 0) {
			return null;
		}

		List<KaleoLog> list = findByKITI_T(kaleoInstanceTokenId, type,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo logs before and after the current kaleo log in the ordered set where kaleoInstanceTokenId = &#63; and type = &#63;.
	 *
	 * @param kaleoLogId the primary key of the current kaleo log
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo log
	 * @throws NoSuchLogException if a kaleo log with the primary key could not be found
	 */
	@Override
	public KaleoLog[] findByKITI_T_PrevAndNext(long kaleoLogId,
		long kaleoInstanceTokenId, String type,
		OrderByComparator<KaleoLog> orderByComparator)
		throws NoSuchLogException {
		KaleoLog kaleoLog = findByPrimaryKey(kaleoLogId);

		Session session = null;

		try {
			session = openSession();

			KaleoLog[] array = new KaleoLogImpl[3];

			array[0] = getByKITI_T_PrevAndNext(session, kaleoLog,
					kaleoInstanceTokenId, type, orderByComparator, true);

			array[1] = kaleoLog;

			array[2] = getByKITI_T_PrevAndNext(session, kaleoLog,
					kaleoInstanceTokenId, type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoLog getByKITI_T_PrevAndNext(Session session,
		KaleoLog kaleoLog, long kaleoInstanceTokenId, String type,
		OrderByComparator<KaleoLog> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOLOG_WHERE);

		query.append(_FINDER_COLUMN_KITI_T_KALEOINSTANCETOKENID_2);

		boolean bindType = false;

		if (type == null) {
			query.append(_FINDER_COLUMN_KITI_T_TYPE_1);
		}
		else if (type.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_KITI_T_TYPE_3);
		}
		else {
			bindType = true;

			query.append(_FINDER_COLUMN_KITI_T_TYPE_2);
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
			query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoInstanceTokenId);

		if (bindType) {
			qPos.add(type);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo logs where kaleoInstanceTokenId = &#63; and type = &#63; from the database.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param type the type
	 */
	@Override
	public void removeByKITI_T(long kaleoInstanceTokenId, String type) {
		for (KaleoLog kaleoLog : findByKITI_T(kaleoInstanceTokenId, type,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoLog);
		}
	}

	/**
	 * Returns the number of kaleo logs where kaleoInstanceTokenId = &#63; and type = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param type the type
	 * @return the number of matching kaleo logs
	 */
	@Override
	public int countByKITI_T(long kaleoInstanceTokenId, String type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KITI_T;

		Object[] finderArgs = new Object[] { kaleoInstanceTokenId, type };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEOLOG_WHERE);

			query.append(_FINDER_COLUMN_KITI_T_KALEOINSTANCETOKENID_2);

			boolean bindType = false;

			if (type == null) {
				query.append(_FINDER_COLUMN_KITI_T_TYPE_1);
			}
			else if (type.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KITI_T_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_KITI_T_TYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceTokenId);

				if (bindType) {
					qPos.add(type);
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

	private static final String _FINDER_COLUMN_KITI_T_KALEOINSTANCETOKENID_2 = "kaleoLog.kaleoInstanceTokenId = ? AND ";
	private static final String _FINDER_COLUMN_KITI_T_TYPE_1 = "kaleoLog.type IS NULL";
	private static final String _FINDER_COLUMN_KITI_T_TYPE_2 = "kaleoLog.type = ?";
	private static final String _FINDER_COLUMN_KITI_T_TYPE_3 = "(kaleoLog.type IS NULL OR kaleoLog.type = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KCN_KCPK_KITI_T =
		new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, KaleoLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKCN_KCPK_KITI_T",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_KITI_T =
		new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, KaleoLogImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByKCN_KCPK_KITI_T",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Long.class.getName(), String.class.getName()
			},
			KaleoLogModelImpl.KALEOCLASSNAME_COLUMN_BITMASK |
			KaleoLogModelImpl.KALEOCLASSPK_COLUMN_BITMASK |
			KaleoLogModelImpl.KALEOINSTANCETOKENID_COLUMN_BITMASK |
			KaleoLogModelImpl.TYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KCN_KCPK_KITI_T = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKCN_KCPK_KITI_T",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Long.class.getName(), String.class.getName()
			});

	/**
	 * Returns all the kaleo logs where kaleoClassName = &#63; and kaleoClassPK = &#63; and kaleoInstanceTokenId = &#63; and type = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param type the type
	 * @return the matching kaleo logs
	 */
	@Override
	public List<KaleoLog> findByKCN_KCPK_KITI_T(String kaleoClassName,
		long kaleoClassPK, long kaleoInstanceTokenId, String type) {
		return findByKCN_KCPK_KITI_T(kaleoClassName, kaleoClassPK,
			kaleoInstanceTokenId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the kaleo logs where kaleoClassName = &#63; and kaleoClassPK = &#63; and kaleoInstanceTokenId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param type the type
	 * @param start the lower bound of the range of kaleo logs
	 * @param end the upper bound of the range of kaleo logs (not inclusive)
	 * @return the range of matching kaleo logs
	 */
	@Override
	public List<KaleoLog> findByKCN_KCPK_KITI_T(String kaleoClassName,
		long kaleoClassPK, long kaleoInstanceTokenId, String type, int start,
		int end) {
		return findByKCN_KCPK_KITI_T(kaleoClassName, kaleoClassPK,
			kaleoInstanceTokenId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo logs where kaleoClassName = &#63; and kaleoClassPK = &#63; and kaleoInstanceTokenId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param type the type
	 * @param start the lower bound of the range of kaleo logs
	 * @param end the upper bound of the range of kaleo logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo logs
	 */
	@Override
	public List<KaleoLog> findByKCN_KCPK_KITI_T(String kaleoClassName,
		long kaleoClassPK, long kaleoInstanceTokenId, String type, int start,
		int end, OrderByComparator<KaleoLog> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_KITI_T;
			finderArgs = new Object[] {
					kaleoClassName, kaleoClassPK, kaleoInstanceTokenId, type
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_KCN_KCPK_KITI_T;
			finderArgs = new Object[] {
					kaleoClassName, kaleoClassPK, kaleoInstanceTokenId, type,
					
					start, end, orderByComparator
				};
		}

		List<KaleoLog> list = (List<KaleoLog>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoLog kaleoLog : list) {
				if (!Validator.equals(kaleoClassName,
							kaleoLog.getKaleoClassName()) ||
						(kaleoClassPK != kaleoLog.getKaleoClassPK()) ||
						(kaleoInstanceTokenId != kaleoLog.getKaleoInstanceTokenId()) ||
						!Validator.equals(type, kaleoLog.getType())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_KALEOLOG_WHERE);

			boolean bindKaleoClassName = false;

			if (kaleoClassName == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_KITI_T_KALEOCLASSNAME_1);
			}
			else if (kaleoClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KCN_KCPK_KITI_T_KALEOCLASSNAME_3);
			}
			else {
				bindKaleoClassName = true;

				query.append(_FINDER_COLUMN_KCN_KCPK_KITI_T_KALEOCLASSNAME_2);
			}

			query.append(_FINDER_COLUMN_KCN_KCPK_KITI_T_KALEOCLASSPK_2);

			query.append(_FINDER_COLUMN_KCN_KCPK_KITI_T_KALEOINSTANCETOKENID_2);

			boolean bindType = false;

			if (type == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_KITI_T_TYPE_1);
			}
			else if (type.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KCN_KCPK_KITI_T_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_KCN_KCPK_KITI_T_TYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
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

				qPos.add(kaleoInstanceTokenId);

				if (bindType) {
					qPos.add(type);
				}

				if (!pagination) {
					list = (List<KaleoLog>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoLog>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first kaleo log in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and kaleoInstanceTokenId = &#63; and type = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo log
	 * @throws NoSuchLogException if a matching kaleo log could not be found
	 */
	@Override
	public KaleoLog findByKCN_KCPK_KITI_T_First(String kaleoClassName,
		long kaleoClassPK, long kaleoInstanceTokenId, String type,
		OrderByComparator<KaleoLog> orderByComparator)
		throws NoSuchLogException {
		KaleoLog kaleoLog = fetchByKCN_KCPK_KITI_T_First(kaleoClassName,
				kaleoClassPK, kaleoInstanceTokenId, type, orderByComparator);

		if (kaleoLog != null) {
			return kaleoLog;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoClassName=");
		msg.append(kaleoClassName);

		msg.append(", kaleoClassPK=");
		msg.append(kaleoClassPK);

		msg.append(", kaleoInstanceTokenId=");
		msg.append(kaleoInstanceTokenId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLogException(msg.toString());
	}

	/**
	 * Returns the first kaleo log in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and kaleoInstanceTokenId = &#63; and type = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo log, or <code>null</code> if a matching kaleo log could not be found
	 */
	@Override
	public KaleoLog fetchByKCN_KCPK_KITI_T_First(String kaleoClassName,
		long kaleoClassPK, long kaleoInstanceTokenId, String type,
		OrderByComparator<KaleoLog> orderByComparator) {
		List<KaleoLog> list = findByKCN_KCPK_KITI_T(kaleoClassName,
				kaleoClassPK, kaleoInstanceTokenId, type, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo log in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and kaleoInstanceTokenId = &#63; and type = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo log
	 * @throws NoSuchLogException if a matching kaleo log could not be found
	 */
	@Override
	public KaleoLog findByKCN_KCPK_KITI_T_Last(String kaleoClassName,
		long kaleoClassPK, long kaleoInstanceTokenId, String type,
		OrderByComparator<KaleoLog> orderByComparator)
		throws NoSuchLogException {
		KaleoLog kaleoLog = fetchByKCN_KCPK_KITI_T_Last(kaleoClassName,
				kaleoClassPK, kaleoInstanceTokenId, type, orderByComparator);

		if (kaleoLog != null) {
			return kaleoLog;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoClassName=");
		msg.append(kaleoClassName);

		msg.append(", kaleoClassPK=");
		msg.append(kaleoClassPK);

		msg.append(", kaleoInstanceTokenId=");
		msg.append(kaleoInstanceTokenId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLogException(msg.toString());
	}

	/**
	 * Returns the last kaleo log in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and kaleoInstanceTokenId = &#63; and type = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo log, or <code>null</code> if a matching kaleo log could not be found
	 */
	@Override
	public KaleoLog fetchByKCN_KCPK_KITI_T_Last(String kaleoClassName,
		long kaleoClassPK, long kaleoInstanceTokenId, String type,
		OrderByComparator<KaleoLog> orderByComparator) {
		int count = countByKCN_KCPK_KITI_T(kaleoClassName, kaleoClassPK,
				kaleoInstanceTokenId, type);

		if (count == 0) {
			return null;
		}

		List<KaleoLog> list = findByKCN_KCPK_KITI_T(kaleoClassName,
				kaleoClassPK, kaleoInstanceTokenId, type, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo logs before and after the current kaleo log in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and kaleoInstanceTokenId = &#63; and type = &#63;.
	 *
	 * @param kaleoLogId the primary key of the current kaleo log
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo log
	 * @throws NoSuchLogException if a kaleo log with the primary key could not be found
	 */
	@Override
	public KaleoLog[] findByKCN_KCPK_KITI_T_PrevAndNext(long kaleoLogId,
		String kaleoClassName, long kaleoClassPK, long kaleoInstanceTokenId,
		String type, OrderByComparator<KaleoLog> orderByComparator)
		throws NoSuchLogException {
		KaleoLog kaleoLog = findByPrimaryKey(kaleoLogId);

		Session session = null;

		try {
			session = openSession();

			KaleoLog[] array = new KaleoLogImpl[3];

			array[0] = getByKCN_KCPK_KITI_T_PrevAndNext(session, kaleoLog,
					kaleoClassName, kaleoClassPK, kaleoInstanceTokenId, type,
					orderByComparator, true);

			array[1] = kaleoLog;

			array[2] = getByKCN_KCPK_KITI_T_PrevAndNext(session, kaleoLog,
					kaleoClassName, kaleoClassPK, kaleoInstanceTokenId, type,
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

	protected KaleoLog getByKCN_KCPK_KITI_T_PrevAndNext(Session session,
		KaleoLog kaleoLog, String kaleoClassName, long kaleoClassPK,
		long kaleoInstanceTokenId, String type,
		OrderByComparator<KaleoLog> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOLOG_WHERE);

		boolean bindKaleoClassName = false;

		if (kaleoClassName == null) {
			query.append(_FINDER_COLUMN_KCN_KCPK_KITI_T_KALEOCLASSNAME_1);
		}
		else if (kaleoClassName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_KCN_KCPK_KITI_T_KALEOCLASSNAME_3);
		}
		else {
			bindKaleoClassName = true;

			query.append(_FINDER_COLUMN_KCN_KCPK_KITI_T_KALEOCLASSNAME_2);
		}

		query.append(_FINDER_COLUMN_KCN_KCPK_KITI_T_KALEOCLASSPK_2);

		query.append(_FINDER_COLUMN_KCN_KCPK_KITI_T_KALEOINSTANCETOKENID_2);

		boolean bindType = false;

		if (type == null) {
			query.append(_FINDER_COLUMN_KCN_KCPK_KITI_T_TYPE_1);
		}
		else if (type.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_KCN_KCPK_KITI_T_TYPE_3);
		}
		else {
			bindType = true;

			query.append(_FINDER_COLUMN_KCN_KCPK_KITI_T_TYPE_2);
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
			query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
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

		qPos.add(kaleoInstanceTokenId);

		if (bindType) {
			qPos.add(type);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo logs where kaleoClassName = &#63; and kaleoClassPK = &#63; and kaleoInstanceTokenId = &#63; and type = &#63; from the database.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param type the type
	 */
	@Override
	public void removeByKCN_KCPK_KITI_T(String kaleoClassName,
		long kaleoClassPK, long kaleoInstanceTokenId, String type) {
		for (KaleoLog kaleoLog : findByKCN_KCPK_KITI_T(kaleoClassName,
				kaleoClassPK, kaleoInstanceTokenId, type, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(kaleoLog);
		}
	}

	/**
	 * Returns the number of kaleo logs where kaleoClassName = &#63; and kaleoClassPK = &#63; and kaleoInstanceTokenId = &#63; and type = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param kaleoInstanceTokenId the kaleo instance token ID
	 * @param type the type
	 * @return the number of matching kaleo logs
	 */
	@Override
	public int countByKCN_KCPK_KITI_T(String kaleoClassName, long kaleoClassPK,
		long kaleoInstanceTokenId, String type) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KCN_KCPK_KITI_T;

		Object[] finderArgs = new Object[] {
				kaleoClassName, kaleoClassPK, kaleoInstanceTokenId, type
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_KALEOLOG_WHERE);

			boolean bindKaleoClassName = false;

			if (kaleoClassName == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_KITI_T_KALEOCLASSNAME_1);
			}
			else if (kaleoClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KCN_KCPK_KITI_T_KALEOCLASSNAME_3);
			}
			else {
				bindKaleoClassName = true;

				query.append(_FINDER_COLUMN_KCN_KCPK_KITI_T_KALEOCLASSNAME_2);
			}

			query.append(_FINDER_COLUMN_KCN_KCPK_KITI_T_KALEOCLASSPK_2);

			query.append(_FINDER_COLUMN_KCN_KCPK_KITI_T_KALEOINSTANCETOKENID_2);

			boolean bindType = false;

			if (type == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_KITI_T_TYPE_1);
			}
			else if (type.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KCN_KCPK_KITI_T_TYPE_3);
			}
			else {
				bindType = true;

				query.append(_FINDER_COLUMN_KCN_KCPK_KITI_T_TYPE_2);
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

				qPos.add(kaleoInstanceTokenId);

				if (bindType) {
					qPos.add(type);
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

	private static final String _FINDER_COLUMN_KCN_KCPK_KITI_T_KALEOCLASSNAME_1 = "kaleoLog.kaleoClassName IS NULL AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_KITI_T_KALEOCLASSNAME_2 = "kaleoLog.kaleoClassName = ? AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_KITI_T_KALEOCLASSNAME_3 = "(kaleoLog.kaleoClassName IS NULL OR kaleoLog.kaleoClassName = '') AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_KITI_T_KALEOCLASSPK_2 = "kaleoLog.kaleoClassPK = ? AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_KITI_T_KALEOINSTANCETOKENID_2 =
		"kaleoLog.kaleoInstanceTokenId = ? AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_KITI_T_TYPE_1 = "kaleoLog.type IS NULL";
	private static final String _FINDER_COLUMN_KCN_KCPK_KITI_T_TYPE_2 = "kaleoLog.type = ?";
	private static final String _FINDER_COLUMN_KCN_KCPK_KITI_T_TYPE_3 = "(kaleoLog.type IS NULL OR kaleoLog.type = '')";

	public KaleoLogPersistenceImpl() {
		setModelClass(KaleoLog.class);
	}

	/**
	 * Caches the kaleo log in the entity cache if it is enabled.
	 *
	 * @param kaleoLog the kaleo log
	 */
	@Override
	public void cacheResult(KaleoLog kaleoLog) {
		EntityCacheUtil.putResult(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogImpl.class, kaleoLog.getPrimaryKey(), kaleoLog);

		kaleoLog.resetOriginalValues();
	}

	/**
	 * Caches the kaleo logs in the entity cache if it is enabled.
	 *
	 * @param kaleoLogs the kaleo logs
	 */
	@Override
	public void cacheResult(List<KaleoLog> kaleoLogs) {
		for (KaleoLog kaleoLog : kaleoLogs) {
			if (EntityCacheUtil.getResult(
						KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
						KaleoLogImpl.class, kaleoLog.getPrimaryKey()) == null) {
				cacheResult(kaleoLog);
			}
			else {
				kaleoLog.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all kaleo logs.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(KaleoLogImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the kaleo log.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KaleoLog kaleoLog) {
		EntityCacheUtil.removeResult(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogImpl.class, kaleoLog.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<KaleoLog> kaleoLogs) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (KaleoLog kaleoLog : kaleoLogs) {
			EntityCacheUtil.removeResult(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
				KaleoLogImpl.class, kaleoLog.getPrimaryKey());
		}
	}

	/**
	 * Creates a new kaleo log with the primary key. Does not add the kaleo log to the database.
	 *
	 * @param kaleoLogId the primary key for the new kaleo log
	 * @return the new kaleo log
	 */
	@Override
	public KaleoLog create(long kaleoLogId) {
		KaleoLog kaleoLog = new KaleoLogImpl();

		kaleoLog.setNew(true);
		kaleoLog.setPrimaryKey(kaleoLogId);

		return kaleoLog;
	}

	/**
	 * Removes the kaleo log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoLogId the primary key of the kaleo log
	 * @return the kaleo log that was removed
	 * @throws NoSuchLogException if a kaleo log with the primary key could not be found
	 */
	@Override
	public KaleoLog remove(long kaleoLogId) throws NoSuchLogException {
		return remove((Serializable)kaleoLogId);
	}

	/**
	 * Removes the kaleo log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo log
	 * @return the kaleo log that was removed
	 * @throws NoSuchLogException if a kaleo log with the primary key could not be found
	 */
	@Override
	public KaleoLog remove(Serializable primaryKey) throws NoSuchLogException {
		Session session = null;

		try {
			session = openSession();

			KaleoLog kaleoLog = (KaleoLog)session.get(KaleoLogImpl.class,
					primaryKey);

			if (kaleoLog == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(kaleoLog);
		}
		catch (NoSuchLogException nsee) {
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
	protected KaleoLog removeImpl(KaleoLog kaleoLog) {
		kaleoLog = toUnwrappedModel(kaleoLog);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(kaleoLog)) {
				kaleoLog = (KaleoLog)session.get(KaleoLogImpl.class,
						kaleoLog.getPrimaryKeyObj());
			}

			if (kaleoLog != null) {
				session.delete(kaleoLog);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (kaleoLog != null) {
			clearCache(kaleoLog);
		}

		return kaleoLog;
	}

	@Override
	public KaleoLog updateImpl(KaleoLog kaleoLog) {
		kaleoLog = toUnwrappedModel(kaleoLog);

		boolean isNew = kaleoLog.isNew();

		KaleoLogModelImpl kaleoLogModelImpl = (KaleoLogModelImpl)kaleoLog;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (kaleoLog.getCreateDate() == null)) {
			if (serviceContext == null) {
				kaleoLog.setCreateDate(now);
			}
			else {
				kaleoLog.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!kaleoLogModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				kaleoLog.setModifiedDate(now);
			}
			else {
				kaleoLog.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (kaleoLog.isNew()) {
				session.save(kaleoLog);

				kaleoLog.setNew(false);
			}
			else {
				session.merge(kaleoLog);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !KaleoLogModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((kaleoLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoLogModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { kaleoLogModelImpl.getCompanyId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((kaleoLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoLogModelImpl.getOriginalKaleoDefinitionId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);

				args = new Object[] { kaleoLogModelImpl.getKaleoDefinitionId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);
			}

			if ((kaleoLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOINSTANCEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoLogModelImpl.getOriginalKaleoInstanceId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEOINSTANCEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOINSTANCEID,
					args);

				args = new Object[] { kaleoLogModelImpl.getKaleoInstanceId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEOINSTANCEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOINSTANCEID,
					args);
			}

			if ((kaleoLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOTASKINSTANCETOKENID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoLogModelImpl.getOriginalKaleoTaskInstanceTokenId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEOTASKINSTANCETOKENID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOTASKINSTANCETOKENID,
					args);

				args = new Object[] {
						kaleoLogModelImpl.getKaleoTaskInstanceTokenId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEOTASKINSTANCETOKENID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOTASKINSTANCETOKENID,
					args);
			}

			if ((kaleoLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KITI_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoLogModelImpl.getOriginalKaleoInstanceTokenId(),
						kaleoLogModelImpl.getOriginalType()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KITI_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KITI_T,
					args);

				args = new Object[] {
						kaleoLogModelImpl.getKaleoInstanceTokenId(),
						kaleoLogModelImpl.getType()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KITI_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KITI_T,
					args);
			}

			if ((kaleoLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_KITI_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoLogModelImpl.getOriginalKaleoClassName(),
						kaleoLogModelImpl.getOriginalKaleoClassPK(),
						kaleoLogModelImpl.getOriginalKaleoInstanceTokenId(),
						kaleoLogModelImpl.getOriginalType()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KCN_KCPK_KITI_T,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_KITI_T,
					args);

				args = new Object[] {
						kaleoLogModelImpl.getKaleoClassName(),
						kaleoLogModelImpl.getKaleoClassPK(),
						kaleoLogModelImpl.getKaleoInstanceTokenId(),
						kaleoLogModelImpl.getType()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KCN_KCPK_KITI_T,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_KITI_T,
					args);
			}
		}

		EntityCacheUtil.putResult(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogImpl.class, kaleoLog.getPrimaryKey(), kaleoLog, false);

		kaleoLog.resetOriginalValues();

		return kaleoLog;
	}

	protected KaleoLog toUnwrappedModel(KaleoLog kaleoLog) {
		if (kaleoLog instanceof KaleoLogImpl) {
			return kaleoLog;
		}

		KaleoLogImpl kaleoLogImpl = new KaleoLogImpl();

		kaleoLogImpl.setNew(kaleoLog.isNew());
		kaleoLogImpl.setPrimaryKey(kaleoLog.getPrimaryKey());

		kaleoLogImpl.setKaleoLogId(kaleoLog.getKaleoLogId());
		kaleoLogImpl.setGroupId(kaleoLog.getGroupId());
		kaleoLogImpl.setCompanyId(kaleoLog.getCompanyId());
		kaleoLogImpl.setUserId(kaleoLog.getUserId());
		kaleoLogImpl.setUserName(kaleoLog.getUserName());
		kaleoLogImpl.setCreateDate(kaleoLog.getCreateDate());
		kaleoLogImpl.setModifiedDate(kaleoLog.getModifiedDate());
		kaleoLogImpl.setKaleoClassName(kaleoLog.getKaleoClassName());
		kaleoLogImpl.setKaleoClassPK(kaleoLog.getKaleoClassPK());
		kaleoLogImpl.setKaleoDefinitionId(kaleoLog.getKaleoDefinitionId());
		kaleoLogImpl.setKaleoInstanceId(kaleoLog.getKaleoInstanceId());
		kaleoLogImpl.setKaleoInstanceTokenId(kaleoLog.getKaleoInstanceTokenId());
		kaleoLogImpl.setKaleoTaskInstanceTokenId(kaleoLog.getKaleoTaskInstanceTokenId());
		kaleoLogImpl.setKaleoNodeName(kaleoLog.getKaleoNodeName());
		kaleoLogImpl.setTerminalKaleoNode(kaleoLog.isTerminalKaleoNode());
		kaleoLogImpl.setKaleoActionId(kaleoLog.getKaleoActionId());
		kaleoLogImpl.setKaleoActionName(kaleoLog.getKaleoActionName());
		kaleoLogImpl.setKaleoActionDescription(kaleoLog.getKaleoActionDescription());
		kaleoLogImpl.setPreviousKaleoNodeId(kaleoLog.getPreviousKaleoNodeId());
		kaleoLogImpl.setPreviousKaleoNodeName(kaleoLog.getPreviousKaleoNodeName());
		kaleoLogImpl.setPreviousAssigneeClassName(kaleoLog.getPreviousAssigneeClassName());
		kaleoLogImpl.setPreviousAssigneeClassPK(kaleoLog.getPreviousAssigneeClassPK());
		kaleoLogImpl.setCurrentAssigneeClassName(kaleoLog.getCurrentAssigneeClassName());
		kaleoLogImpl.setCurrentAssigneeClassPK(kaleoLog.getCurrentAssigneeClassPK());
		kaleoLogImpl.setType(kaleoLog.getType());
		kaleoLogImpl.setComment(kaleoLog.getComment());
		kaleoLogImpl.setStartDate(kaleoLog.getStartDate());
		kaleoLogImpl.setEndDate(kaleoLog.getEndDate());
		kaleoLogImpl.setDuration(kaleoLog.getDuration());
		kaleoLogImpl.setWorkflowContext(kaleoLog.getWorkflowContext());

		return kaleoLogImpl;
	}

	/**
	 * Returns the kaleo log with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo log
	 * @return the kaleo log
	 * @throws NoSuchLogException if a kaleo log with the primary key could not be found
	 */
	@Override
	public KaleoLog findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLogException {
		KaleoLog kaleoLog = fetchByPrimaryKey(primaryKey);

		if (kaleoLog == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return kaleoLog;
	}

	/**
	 * Returns the kaleo log with the primary key or throws a {@link NoSuchLogException} if it could not be found.
	 *
	 * @param kaleoLogId the primary key of the kaleo log
	 * @return the kaleo log
	 * @throws NoSuchLogException if a kaleo log with the primary key could not be found
	 */
	@Override
	public KaleoLog findByPrimaryKey(long kaleoLogId) throws NoSuchLogException {
		return findByPrimaryKey((Serializable)kaleoLogId);
	}

	/**
	 * Returns the kaleo log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo log
	 * @return the kaleo log, or <code>null</code> if a kaleo log with the primary key could not be found
	 */
	@Override
	public KaleoLog fetchByPrimaryKey(Serializable primaryKey) {
		KaleoLog kaleoLog = (KaleoLog)EntityCacheUtil.getResult(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
				KaleoLogImpl.class, primaryKey);

		if (kaleoLog == _nullKaleoLog) {
			return null;
		}

		if (kaleoLog == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoLog = (KaleoLog)session.get(KaleoLogImpl.class, primaryKey);

				if (kaleoLog != null) {
					cacheResult(kaleoLog);
				}
				else {
					EntityCacheUtil.putResult(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
						KaleoLogImpl.class, primaryKey, _nullKaleoLog);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
					KaleoLogImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return kaleoLog;
	}

	/**
	 * Returns the kaleo log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoLogId the primary key of the kaleo log
	 * @return the kaleo log, or <code>null</code> if a kaleo log with the primary key could not be found
	 */
	@Override
	public KaleoLog fetchByPrimaryKey(long kaleoLogId) {
		return fetchByPrimaryKey((Serializable)kaleoLogId);
	}

	@Override
	public Map<Serializable, KaleoLog> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, KaleoLog> map = new HashMap<Serializable, KaleoLog>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			KaleoLog kaleoLog = fetchByPrimaryKey(primaryKey);

			if (kaleoLog != null) {
				map.put(primaryKey, kaleoLog);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			KaleoLog kaleoLog = (KaleoLog)EntityCacheUtil.getResult(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
					KaleoLogImpl.class, primaryKey);

			if (kaleoLog == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, kaleoLog);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_KALEOLOG_WHERE_PKS_IN);

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

			for (KaleoLog kaleoLog : (List<KaleoLog>)q.list()) {
				map.put(kaleoLog.getPrimaryKeyObj(), kaleoLog);

				cacheResult(kaleoLog);

				uncachedPrimaryKeys.remove(kaleoLog.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
					KaleoLogImpl.class, primaryKey, _nullKaleoLog);
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
	 * Returns all the kaleo logs.
	 *
	 * @return the kaleo logs
	 */
	@Override
	public List<KaleoLog> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo logs
	 * @param end the upper bound of the range of kaleo logs (not inclusive)
	 * @return the range of kaleo logs
	 */
	@Override
	public List<KaleoLog> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo logs
	 * @param end the upper bound of the range of kaleo logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo logs
	 */
	@Override
	public List<KaleoLog> findAll(int start, int end,
		OrderByComparator<KaleoLog> orderByComparator) {
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

		List<KaleoLog> list = (List<KaleoLog>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KALEOLOG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEOLOG;

				if (pagination) {
					sql = sql.concat(KaleoLogModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<KaleoLog>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoLog>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the kaleo logs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (KaleoLog kaleoLog : findAll()) {
			remove(kaleoLog);
		}
	}

	/**
	 * Returns the number of kaleo logs.
	 *
	 * @return the number of kaleo logs
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KALEOLOG);

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
	protected Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return KaleoLogModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the kaleo log persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(KaleoLogImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_KALEOLOG = "SELECT kaleoLog FROM KaleoLog kaleoLog";
	private static final String _SQL_SELECT_KALEOLOG_WHERE_PKS_IN = "SELECT kaleoLog FROM KaleoLog kaleoLog WHERE kaleoLogId IN (";
	private static final String _SQL_SELECT_KALEOLOG_WHERE = "SELECT kaleoLog FROM KaleoLog kaleoLog WHERE ";
	private static final String _SQL_COUNT_KALEOLOG = "SELECT COUNT(kaleoLog) FROM KaleoLog kaleoLog";
	private static final String _SQL_COUNT_KALEOLOG_WHERE = "SELECT COUNT(kaleoLog) FROM KaleoLog kaleoLog WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoLog.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoLog exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoLog exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(KaleoLogPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type", "comment"
			});
	private static final KaleoLog _nullKaleoLog = new KaleoLogImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<KaleoLog> toCacheModel() {
				return _nullKaleoLogCacheModel;
			}
		};

	private static final CacheModel<KaleoLog> _nullKaleoLogCacheModel = new CacheModel<KaleoLog>() {
			@Override
			public KaleoLog toEntityModel() {
				return _nullKaleoLog;
			}
		};
}