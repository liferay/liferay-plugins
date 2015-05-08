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
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentModelImpl;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskAssignmentPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the kaleo task assignment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskAssignmentPersistence
 * @see com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskAssignmentUtil
 * @generated
 */
@ProviderType
public class KaleoTaskAssignmentPersistenceImpl extends BasePersistenceImpl<KaleoTaskAssignment>
	implements KaleoTaskAssignmentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KaleoTaskAssignmentUtil} to access the kaleo task assignment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoTaskAssignmentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			KaleoTaskAssignmentModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo task assignments where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching kaleo task assignments
	 */
	@Override
	public List<KaleoTaskAssignment> findByCompanyId(long companyId) {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the kaleo task assignments where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTaskAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo task assignments
	 * @param end the upper bound of the range of kaleo task assignments (not inclusive)
	 * @return the range of matching kaleo task assignments
	 */
	@Override
	public List<KaleoTaskAssignment> findByCompanyId(long companyId, int start,
		int end) {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo task assignments where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTaskAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo task assignments
	 * @param end the upper bound of the range of kaleo task assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo task assignments
	 */
	@Override
	public List<KaleoTaskAssignment> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<KaleoTaskAssignment> orderByComparator) {
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

		List<KaleoTaskAssignment> list = (List<KaleoTaskAssignment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoTaskAssignment kaleoTaskAssignment : list) {
				if ((companyId != kaleoTaskAssignment.getCompanyId())) {
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

			query.append(_SQL_SELECT_KALEOTASKASSIGNMENT_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoTaskAssignmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<KaleoTaskAssignment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoTaskAssignment>)QueryUtil.list(q,
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
	 * Returns the first kaleo task assignment in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task assignment
	 * @throws NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	 */
	@Override
	public KaleoTaskAssignment findByCompanyId_First(long companyId,
		OrderByComparator<KaleoTaskAssignment> orderByComparator)
		throws NoSuchTaskAssignmentException {
		KaleoTaskAssignment kaleoTaskAssignment = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (kaleoTaskAssignment != null) {
			return kaleoTaskAssignment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTaskAssignmentException(msg.toString());
	}

	/**
	 * Returns the first kaleo task assignment in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task assignment, or <code>null</code> if a matching kaleo task assignment could not be found
	 */
	@Override
	public KaleoTaskAssignment fetchByCompanyId_First(long companyId,
		OrderByComparator<KaleoTaskAssignment> orderByComparator) {
		List<KaleoTaskAssignment> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo task assignment in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task assignment
	 * @throws NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	 */
	@Override
	public KaleoTaskAssignment findByCompanyId_Last(long companyId,
		OrderByComparator<KaleoTaskAssignment> orderByComparator)
		throws NoSuchTaskAssignmentException {
		KaleoTaskAssignment kaleoTaskAssignment = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (kaleoTaskAssignment != null) {
			return kaleoTaskAssignment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTaskAssignmentException(msg.toString());
	}

	/**
	 * Returns the last kaleo task assignment in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task assignment, or <code>null</code> if a matching kaleo task assignment could not be found
	 */
	@Override
	public KaleoTaskAssignment fetchByCompanyId_Last(long companyId,
		OrderByComparator<KaleoTaskAssignment> orderByComparator) {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<KaleoTaskAssignment> list = findByCompanyId(companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo task assignments before and after the current kaleo task assignment in the ordered set where companyId = &#63;.
	 *
	 * @param kaleoTaskAssignmentId the primary key of the current kaleo task assignment
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo task assignment
	 * @throws NoSuchTaskAssignmentException if a kaleo task assignment with the primary key could not be found
	 */
	@Override
	public KaleoTaskAssignment[] findByCompanyId_PrevAndNext(
		long kaleoTaskAssignmentId, long companyId,
		OrderByComparator<KaleoTaskAssignment> orderByComparator)
		throws NoSuchTaskAssignmentException {
		KaleoTaskAssignment kaleoTaskAssignment = findByPrimaryKey(kaleoTaskAssignmentId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskAssignment[] array = new KaleoTaskAssignmentImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, kaleoTaskAssignment,
					companyId, orderByComparator, true);

			array[1] = kaleoTaskAssignment;

			array[2] = getByCompanyId_PrevAndNext(session, kaleoTaskAssignment,
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

	protected KaleoTaskAssignment getByCompanyId_PrevAndNext(Session session,
		KaleoTaskAssignment kaleoTaskAssignment, long companyId,
		OrderByComparator<KaleoTaskAssignment> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASKASSIGNMENT_WHERE);

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
			query.append(KaleoTaskAssignmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoTaskAssignment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskAssignment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo task assignments where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (KaleoTaskAssignment kaleoTaskAssignment : findByCompanyId(
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoTaskAssignment);
		}
	}

	/**
	 * Returns the number of kaleo task assignments where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching kaleo task assignments
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTASKASSIGNMENT_WHERE);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoTaskAssignment.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEODEFINITIONID =
		new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID =
		new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByKaleoDefinitionId", new String[] { Long.class.getName() },
			KaleoTaskAssignmentModelImpl.KALEODEFINITIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEODEFINITIONID = new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKaleoDefinitionId", new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo task assignments where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the matching kaleo task assignments
	 */
	@Override
	public List<KaleoTaskAssignment> findByKaleoDefinitionId(
		long kaleoDefinitionId) {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo task assignments where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTaskAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo task assignments
	 * @param end the upper bound of the range of kaleo task assignments (not inclusive)
	 * @return the range of matching kaleo task assignments
	 */
	@Override
	public List<KaleoTaskAssignment> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end) {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo task assignments where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTaskAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo task assignments
	 * @param end the upper bound of the range of kaleo task assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo task assignments
	 */
	@Override
	public List<KaleoTaskAssignment> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		OrderByComparator<KaleoTaskAssignment> orderByComparator) {
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

		List<KaleoTaskAssignment> list = (List<KaleoTaskAssignment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoTaskAssignment kaleoTaskAssignment : list) {
				if ((kaleoDefinitionId != kaleoTaskAssignment.getKaleoDefinitionId())) {
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

			query.append(_SQL_SELECT_KALEOTASKASSIGNMENT_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoTaskAssignmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				if (!pagination) {
					list = (List<KaleoTaskAssignment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoTaskAssignment>)QueryUtil.list(q,
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
	 * Returns the first kaleo task assignment in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task assignment
	 * @throws NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	 */
	@Override
	public KaleoTaskAssignment findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		OrderByComparator<KaleoTaskAssignment> orderByComparator)
		throws NoSuchTaskAssignmentException {
		KaleoTaskAssignment kaleoTaskAssignment = fetchByKaleoDefinitionId_First(kaleoDefinitionId,
				orderByComparator);

		if (kaleoTaskAssignment != null) {
			return kaleoTaskAssignment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoDefinitionId=");
		msg.append(kaleoDefinitionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTaskAssignmentException(msg.toString());
	}

	/**
	 * Returns the first kaleo task assignment in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task assignment, or <code>null</code> if a matching kaleo task assignment could not be found
	 */
	@Override
	public KaleoTaskAssignment fetchByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		OrderByComparator<KaleoTaskAssignment> orderByComparator) {
		List<KaleoTaskAssignment> list = findByKaleoDefinitionId(kaleoDefinitionId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo task assignment in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task assignment
	 * @throws NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	 */
	@Override
	public KaleoTaskAssignment findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		OrderByComparator<KaleoTaskAssignment> orderByComparator)
		throws NoSuchTaskAssignmentException {
		KaleoTaskAssignment kaleoTaskAssignment = fetchByKaleoDefinitionId_Last(kaleoDefinitionId,
				orderByComparator);

		if (kaleoTaskAssignment != null) {
			return kaleoTaskAssignment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoDefinitionId=");
		msg.append(kaleoDefinitionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTaskAssignmentException(msg.toString());
	}

	/**
	 * Returns the last kaleo task assignment in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task assignment, or <code>null</code> if a matching kaleo task assignment could not be found
	 */
	@Override
	public KaleoTaskAssignment fetchByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		OrderByComparator<KaleoTaskAssignment> orderByComparator) {
		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		if (count == 0) {
			return null;
		}

		List<KaleoTaskAssignment> list = findByKaleoDefinitionId(kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo task assignments before and after the current kaleo task assignment in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoTaskAssignmentId the primary key of the current kaleo task assignment
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo task assignment
	 * @throws NoSuchTaskAssignmentException if a kaleo task assignment with the primary key could not be found
	 */
	@Override
	public KaleoTaskAssignment[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoTaskAssignmentId, long kaleoDefinitionId,
		OrderByComparator<KaleoTaskAssignment> orderByComparator)
		throws NoSuchTaskAssignmentException {
		KaleoTaskAssignment kaleoTaskAssignment = findByPrimaryKey(kaleoTaskAssignmentId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskAssignment[] array = new KaleoTaskAssignmentImpl[3];

			array[0] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoTaskAssignment, kaleoDefinitionId, orderByComparator,
					true);

			array[1] = kaleoTaskAssignment;

			array[2] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoTaskAssignment, kaleoDefinitionId, orderByComparator,
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

	protected KaleoTaskAssignment getByKaleoDefinitionId_PrevAndNext(
		Session session, KaleoTaskAssignment kaleoTaskAssignment,
		long kaleoDefinitionId,
		OrderByComparator<KaleoTaskAssignment> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASKASSIGNMENT_WHERE);

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
			query.append(KaleoTaskAssignmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoTaskAssignment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskAssignment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo task assignments where kaleoDefinitionId = &#63; from the database.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 */
	@Override
	public void removeByKaleoDefinitionId(long kaleoDefinitionId) {
		for (KaleoTaskAssignment kaleoTaskAssignment : findByKaleoDefinitionId(
				kaleoDefinitionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoTaskAssignment);
		}
	}

	/**
	 * Returns the number of kaleo task assignments where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the number of matching kaleo task assignments
	 */
	@Override
	public int countByKaleoDefinitionId(long kaleoDefinitionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KALEODEFINITIONID;

		Object[] finderArgs = new Object[] { kaleoDefinitionId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTASKASSIGNMENT_WHERE);

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
		"kaleoTaskAssignment.kaleoDefinitionId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KCN_KCPK = new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKCN_KCPK",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK =
		new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByKCN_KCPK",
			new String[] { String.class.getName(), Long.class.getName() },
			KaleoTaskAssignmentModelImpl.KALEOCLASSNAME_COLUMN_BITMASK |
			KaleoTaskAssignmentModelImpl.KALEOCLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KCN_KCPK = new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKCN_KCPK",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @return the matching kaleo task assignments
	 */
	@Override
	public List<KaleoTaskAssignment> findByKCN_KCPK(String kaleoClassName,
		long kaleoClassPK) {
		return findByKCN_KCPK(kaleoClassName, kaleoClassPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTaskAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param start the lower bound of the range of kaleo task assignments
	 * @param end the upper bound of the range of kaleo task assignments (not inclusive)
	 * @return the range of matching kaleo task assignments
	 */
	@Override
	public List<KaleoTaskAssignment> findByKCN_KCPK(String kaleoClassName,
		long kaleoClassPK, int start, int end) {
		return findByKCN_KCPK(kaleoClassName, kaleoClassPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTaskAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param start the lower bound of the range of kaleo task assignments
	 * @param end the upper bound of the range of kaleo task assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo task assignments
	 */
	@Override
	public List<KaleoTaskAssignment> findByKCN_KCPK(String kaleoClassName,
		long kaleoClassPK, int start, int end,
		OrderByComparator<KaleoTaskAssignment> orderByComparator) {
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

		List<KaleoTaskAssignment> list = (List<KaleoTaskAssignment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoTaskAssignment kaleoTaskAssignment : list) {
				if (!Validator.equals(kaleoClassName,
							kaleoTaskAssignment.getKaleoClassName()) ||
						(kaleoClassPK != kaleoTaskAssignment.getKaleoClassPK())) {
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

			query.append(_SQL_SELECT_KALEOTASKASSIGNMENT_WHERE);

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
				query.append(KaleoTaskAssignmentModelImpl.ORDER_BY_JPQL);
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
					list = (List<KaleoTaskAssignment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoTaskAssignment>)QueryUtil.list(q,
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
	 * Returns the first kaleo task assignment in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task assignment
	 * @throws NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	 */
	@Override
	public KaleoTaskAssignment findByKCN_KCPK_First(String kaleoClassName,
		long kaleoClassPK,
		OrderByComparator<KaleoTaskAssignment> orderByComparator)
		throws NoSuchTaskAssignmentException {
		KaleoTaskAssignment kaleoTaskAssignment = fetchByKCN_KCPK_First(kaleoClassName,
				kaleoClassPK, orderByComparator);

		if (kaleoTaskAssignment != null) {
			return kaleoTaskAssignment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoClassName=");
		msg.append(kaleoClassName);

		msg.append(", kaleoClassPK=");
		msg.append(kaleoClassPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTaskAssignmentException(msg.toString());
	}

	/**
	 * Returns the first kaleo task assignment in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task assignment, or <code>null</code> if a matching kaleo task assignment could not be found
	 */
	@Override
	public KaleoTaskAssignment fetchByKCN_KCPK_First(String kaleoClassName,
		long kaleoClassPK,
		OrderByComparator<KaleoTaskAssignment> orderByComparator) {
		List<KaleoTaskAssignment> list = findByKCN_KCPK(kaleoClassName,
				kaleoClassPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo task assignment in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task assignment
	 * @throws NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	 */
	@Override
	public KaleoTaskAssignment findByKCN_KCPK_Last(String kaleoClassName,
		long kaleoClassPK,
		OrderByComparator<KaleoTaskAssignment> orderByComparator)
		throws NoSuchTaskAssignmentException {
		KaleoTaskAssignment kaleoTaskAssignment = fetchByKCN_KCPK_Last(kaleoClassName,
				kaleoClassPK, orderByComparator);

		if (kaleoTaskAssignment != null) {
			return kaleoTaskAssignment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoClassName=");
		msg.append(kaleoClassName);

		msg.append(", kaleoClassPK=");
		msg.append(kaleoClassPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTaskAssignmentException(msg.toString());
	}

	/**
	 * Returns the last kaleo task assignment in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task assignment, or <code>null</code> if a matching kaleo task assignment could not be found
	 */
	@Override
	public KaleoTaskAssignment fetchByKCN_KCPK_Last(String kaleoClassName,
		long kaleoClassPK,
		OrderByComparator<KaleoTaskAssignment> orderByComparator) {
		int count = countByKCN_KCPK(kaleoClassName, kaleoClassPK);

		if (count == 0) {
			return null;
		}

		List<KaleoTaskAssignment> list = findByKCN_KCPK(kaleoClassName,
				kaleoClassPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo task assignments before and after the current kaleo task assignment in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoTaskAssignmentId the primary key of the current kaleo task assignment
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo task assignment
	 * @throws NoSuchTaskAssignmentException if a kaleo task assignment with the primary key could not be found
	 */
	@Override
	public KaleoTaskAssignment[] findByKCN_KCPK_PrevAndNext(
		long kaleoTaskAssignmentId, String kaleoClassName, long kaleoClassPK,
		OrderByComparator<KaleoTaskAssignment> orderByComparator)
		throws NoSuchTaskAssignmentException {
		KaleoTaskAssignment kaleoTaskAssignment = findByPrimaryKey(kaleoTaskAssignmentId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskAssignment[] array = new KaleoTaskAssignmentImpl[3];

			array[0] = getByKCN_KCPK_PrevAndNext(session, kaleoTaskAssignment,
					kaleoClassName, kaleoClassPK, orderByComparator, true);

			array[1] = kaleoTaskAssignment;

			array[2] = getByKCN_KCPK_PrevAndNext(session, kaleoTaskAssignment,
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

	protected KaleoTaskAssignment getByKCN_KCPK_PrevAndNext(Session session,
		KaleoTaskAssignment kaleoTaskAssignment, String kaleoClassName,
		long kaleoClassPK,
		OrderByComparator<KaleoTaskAssignment> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASKASSIGNMENT_WHERE);

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
			query.append(KaleoTaskAssignmentModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoTaskAssignment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskAssignment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63; from the database.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 */
	@Override
	public void removeByKCN_KCPK(String kaleoClassName, long kaleoClassPK) {
		for (KaleoTaskAssignment kaleoTaskAssignment : findByKCN_KCPK(
				kaleoClassName, kaleoClassPK, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(kaleoTaskAssignment);
		}
	}

	/**
	 * Returns the number of kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @return the number of matching kaleo task assignments
	 */
	@Override
	public int countByKCN_KCPK(String kaleoClassName, long kaleoClassPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KCN_KCPK;

		Object[] finderArgs = new Object[] { kaleoClassName, kaleoClassPK };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEOTASKASSIGNMENT_WHERE);

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

	private static final String _FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_1 = "kaleoTaskAssignment.kaleoClassName IS NULL AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_2 = "kaleoTaskAssignment.kaleoClassName = ? AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_3 = "(kaleoTaskAssignment.kaleoClassName IS NULL OR kaleoTaskAssignment.kaleoClassName = '') AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_KALEOCLASSPK_2 = "kaleoTaskAssignment.kaleoClassPK = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KCN_KCPK_ACN =
		new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKCN_KCPK_ACN",
			new String[] {
				String.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_ACN =
		new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByKCN_KCPK_ACN",
			new String[] {
				String.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			KaleoTaskAssignmentModelImpl.KALEOCLASSNAME_COLUMN_BITMASK |
			KaleoTaskAssignmentModelImpl.KALEOCLASSPK_COLUMN_BITMASK |
			KaleoTaskAssignmentModelImpl.ASSIGNEECLASSNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KCN_KCPK_ACN = new FinderPath(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKCN_KCPK_ACN",
			new String[] {
				String.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param assigneeClassName the assignee class name
	 * @return the matching kaleo task assignments
	 */
	@Override
	public List<KaleoTaskAssignment> findByKCN_KCPK_ACN(String kaleoClassName,
		long kaleoClassPK, String assigneeClassName) {
		return findByKCN_KCPK_ACN(kaleoClassName, kaleoClassPK,
			assigneeClassName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTaskAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param assigneeClassName the assignee class name
	 * @param start the lower bound of the range of kaleo task assignments
	 * @param end the upper bound of the range of kaleo task assignments (not inclusive)
	 * @return the range of matching kaleo task assignments
	 */
	@Override
	public List<KaleoTaskAssignment> findByKCN_KCPK_ACN(String kaleoClassName,
		long kaleoClassPK, String assigneeClassName, int start, int end) {
		return findByKCN_KCPK_ACN(kaleoClassName, kaleoClassPK,
			assigneeClassName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTaskAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param assigneeClassName the assignee class name
	 * @param start the lower bound of the range of kaleo task assignments
	 * @param end the upper bound of the range of kaleo task assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo task assignments
	 */
	@Override
	public List<KaleoTaskAssignment> findByKCN_KCPK_ACN(String kaleoClassName,
		long kaleoClassPK, String assigneeClassName, int start, int end,
		OrderByComparator<KaleoTaskAssignment> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_ACN;
			finderArgs = new Object[] {
					kaleoClassName, kaleoClassPK, assigneeClassName
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_KCN_KCPK_ACN;
			finderArgs = new Object[] {
					kaleoClassName, kaleoClassPK, assigneeClassName,
					
					start, end, orderByComparator
				};
		}

		List<KaleoTaskAssignment> list = (List<KaleoTaskAssignment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoTaskAssignment kaleoTaskAssignment : list) {
				if (!Validator.equals(kaleoClassName,
							kaleoTaskAssignment.getKaleoClassName()) ||
						(kaleoClassPK != kaleoTaskAssignment.getKaleoClassPK()) ||
						!Validator.equals(assigneeClassName,
							kaleoTaskAssignment.getAssigneeClassName())) {
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

			query.append(_SQL_SELECT_KALEOTASKASSIGNMENT_WHERE);

			boolean bindKaleoClassName = false;

			if (kaleoClassName == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSNAME_1);
			}
			else if (kaleoClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSNAME_3);
			}
			else {
				bindKaleoClassName = true;

				query.append(_FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSNAME_2);
			}

			query.append(_FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSPK_2);

			boolean bindAssigneeClassName = false;

			if (assigneeClassName == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ACN_ASSIGNEECLASSNAME_1);
			}
			else if (assigneeClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ACN_ASSIGNEECLASSNAME_3);
			}
			else {
				bindAssigneeClassName = true;

				query.append(_FINDER_COLUMN_KCN_KCPK_ACN_ASSIGNEECLASSNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoTaskAssignmentModelImpl.ORDER_BY_JPQL);
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

				if (bindAssigneeClassName) {
					qPos.add(assigneeClassName);
				}

				if (!pagination) {
					list = (List<KaleoTaskAssignment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoTaskAssignment>)QueryUtil.list(q,
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
	 * Returns the first kaleo task assignment in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param assigneeClassName the assignee class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task assignment
	 * @throws NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	 */
	@Override
	public KaleoTaskAssignment findByKCN_KCPK_ACN_First(String kaleoClassName,
		long kaleoClassPK, String assigneeClassName,
		OrderByComparator<KaleoTaskAssignment> orderByComparator)
		throws NoSuchTaskAssignmentException {
		KaleoTaskAssignment kaleoTaskAssignment = fetchByKCN_KCPK_ACN_First(kaleoClassName,
				kaleoClassPK, assigneeClassName, orderByComparator);

		if (kaleoTaskAssignment != null) {
			return kaleoTaskAssignment;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoClassName=");
		msg.append(kaleoClassName);

		msg.append(", kaleoClassPK=");
		msg.append(kaleoClassPK);

		msg.append(", assigneeClassName=");
		msg.append(assigneeClassName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTaskAssignmentException(msg.toString());
	}

	/**
	 * Returns the first kaleo task assignment in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param assigneeClassName the assignee class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task assignment, or <code>null</code> if a matching kaleo task assignment could not be found
	 */
	@Override
	public KaleoTaskAssignment fetchByKCN_KCPK_ACN_First(
		String kaleoClassName, long kaleoClassPK, String assigneeClassName,
		OrderByComparator<KaleoTaskAssignment> orderByComparator) {
		List<KaleoTaskAssignment> list = findByKCN_KCPK_ACN(kaleoClassName,
				kaleoClassPK, assigneeClassName, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo task assignment in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param assigneeClassName the assignee class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task assignment
	 * @throws NoSuchTaskAssignmentException if a matching kaleo task assignment could not be found
	 */
	@Override
	public KaleoTaskAssignment findByKCN_KCPK_ACN_Last(String kaleoClassName,
		long kaleoClassPK, String assigneeClassName,
		OrderByComparator<KaleoTaskAssignment> orderByComparator)
		throws NoSuchTaskAssignmentException {
		KaleoTaskAssignment kaleoTaskAssignment = fetchByKCN_KCPK_ACN_Last(kaleoClassName,
				kaleoClassPK, assigneeClassName, orderByComparator);

		if (kaleoTaskAssignment != null) {
			return kaleoTaskAssignment;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoClassName=");
		msg.append(kaleoClassName);

		msg.append(", kaleoClassPK=");
		msg.append(kaleoClassPK);

		msg.append(", assigneeClassName=");
		msg.append(assigneeClassName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTaskAssignmentException(msg.toString());
	}

	/**
	 * Returns the last kaleo task assignment in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param assigneeClassName the assignee class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task assignment, or <code>null</code> if a matching kaleo task assignment could not be found
	 */
	@Override
	public KaleoTaskAssignment fetchByKCN_KCPK_ACN_Last(String kaleoClassName,
		long kaleoClassPK, String assigneeClassName,
		OrderByComparator<KaleoTaskAssignment> orderByComparator) {
		int count = countByKCN_KCPK_ACN(kaleoClassName, kaleoClassPK,
				assigneeClassName);

		if (count == 0) {
			return null;
		}

		List<KaleoTaskAssignment> list = findByKCN_KCPK_ACN(kaleoClassName,
				kaleoClassPK, assigneeClassName, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo task assignments before and after the current kaleo task assignment in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63;.
	 *
	 * @param kaleoTaskAssignmentId the primary key of the current kaleo task assignment
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param assigneeClassName the assignee class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo task assignment
	 * @throws NoSuchTaskAssignmentException if a kaleo task assignment with the primary key could not be found
	 */
	@Override
	public KaleoTaskAssignment[] findByKCN_KCPK_ACN_PrevAndNext(
		long kaleoTaskAssignmentId, String kaleoClassName, long kaleoClassPK,
		String assigneeClassName,
		OrderByComparator<KaleoTaskAssignment> orderByComparator)
		throws NoSuchTaskAssignmentException {
		KaleoTaskAssignment kaleoTaskAssignment = findByPrimaryKey(kaleoTaskAssignmentId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskAssignment[] array = new KaleoTaskAssignmentImpl[3];

			array[0] = getByKCN_KCPK_ACN_PrevAndNext(session,
					kaleoTaskAssignment, kaleoClassName, kaleoClassPK,
					assigneeClassName, orderByComparator, true);

			array[1] = kaleoTaskAssignment;

			array[2] = getByKCN_KCPK_ACN_PrevAndNext(session,
					kaleoTaskAssignment, kaleoClassName, kaleoClassPK,
					assigneeClassName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTaskAssignment getByKCN_KCPK_ACN_PrevAndNext(
		Session session, KaleoTaskAssignment kaleoTaskAssignment,
		String kaleoClassName, long kaleoClassPK, String assigneeClassName,
		OrderByComparator<KaleoTaskAssignment> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASKASSIGNMENT_WHERE);

		boolean bindKaleoClassName = false;

		if (kaleoClassName == null) {
			query.append(_FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSNAME_1);
		}
		else if (kaleoClassName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSNAME_3);
		}
		else {
			bindKaleoClassName = true;

			query.append(_FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSNAME_2);
		}

		query.append(_FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSPK_2);

		boolean bindAssigneeClassName = false;

		if (assigneeClassName == null) {
			query.append(_FINDER_COLUMN_KCN_KCPK_ACN_ASSIGNEECLASSNAME_1);
		}
		else if (assigneeClassName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_KCN_KCPK_ACN_ASSIGNEECLASSNAME_3);
		}
		else {
			bindAssigneeClassName = true;

			query.append(_FINDER_COLUMN_KCN_KCPK_ACN_ASSIGNEECLASSNAME_2);
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
			query.append(KaleoTaskAssignmentModelImpl.ORDER_BY_JPQL);
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

		if (bindAssigneeClassName) {
			qPos.add(assigneeClassName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoTaskAssignment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskAssignment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63; from the database.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param assigneeClassName the assignee class name
	 */
	@Override
	public void removeByKCN_KCPK_ACN(String kaleoClassName, long kaleoClassPK,
		String assigneeClassName) {
		for (KaleoTaskAssignment kaleoTaskAssignment : findByKCN_KCPK_ACN(
				kaleoClassName, kaleoClassPK, assigneeClassName,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoTaskAssignment);
		}
	}

	/**
	 * Returns the number of kaleo task assignments where kaleoClassName = &#63; and kaleoClassPK = &#63; and assigneeClassName = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param assigneeClassName the assignee class name
	 * @return the number of matching kaleo task assignments
	 */
	@Override
	public int countByKCN_KCPK_ACN(String kaleoClassName, long kaleoClassPK,
		String assigneeClassName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KCN_KCPK_ACN;

		Object[] finderArgs = new Object[] {
				kaleoClassName, kaleoClassPK, assigneeClassName
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KALEOTASKASSIGNMENT_WHERE);

			boolean bindKaleoClassName = false;

			if (kaleoClassName == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSNAME_1);
			}
			else if (kaleoClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSNAME_3);
			}
			else {
				bindKaleoClassName = true;

				query.append(_FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSNAME_2);
			}

			query.append(_FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSPK_2);

			boolean bindAssigneeClassName = false;

			if (assigneeClassName == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ACN_ASSIGNEECLASSNAME_1);
			}
			else if (assigneeClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KCN_KCPK_ACN_ASSIGNEECLASSNAME_3);
			}
			else {
				bindAssigneeClassName = true;

				query.append(_FINDER_COLUMN_KCN_KCPK_ACN_ASSIGNEECLASSNAME_2);
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

				if (bindAssigneeClassName) {
					qPos.add(assigneeClassName);
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

	private static final String _FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSNAME_1 = "kaleoTaskAssignment.kaleoClassName IS NULL AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSNAME_2 = "kaleoTaskAssignment.kaleoClassName = ? AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSNAME_3 = "(kaleoTaskAssignment.kaleoClassName IS NULL OR kaleoTaskAssignment.kaleoClassName = '') AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_ACN_KALEOCLASSPK_2 = "kaleoTaskAssignment.kaleoClassPK = ? AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_ACN_ASSIGNEECLASSNAME_1 = "kaleoTaskAssignment.assigneeClassName IS NULL";
	private static final String _FINDER_COLUMN_KCN_KCPK_ACN_ASSIGNEECLASSNAME_2 = "kaleoTaskAssignment.assigneeClassName = ?";
	private static final String _FINDER_COLUMN_KCN_KCPK_ACN_ASSIGNEECLASSNAME_3 = "(kaleoTaskAssignment.assigneeClassName IS NULL OR kaleoTaskAssignment.assigneeClassName = '')";

	public KaleoTaskAssignmentPersistenceImpl() {
		setModelClass(KaleoTaskAssignment.class);
	}

	/**
	 * Caches the kaleo task assignment in the entity cache if it is enabled.
	 *
	 * @param kaleoTaskAssignment the kaleo task assignment
	 */
	@Override
	public void cacheResult(KaleoTaskAssignment kaleoTaskAssignment) {
		EntityCacheUtil.putResult(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class, kaleoTaskAssignment.getPrimaryKey(),
			kaleoTaskAssignment);

		kaleoTaskAssignment.resetOriginalValues();
	}

	/**
	 * Caches the kaleo task assignments in the entity cache if it is enabled.
	 *
	 * @param kaleoTaskAssignments the kaleo task assignments
	 */
	@Override
	public void cacheResult(List<KaleoTaskAssignment> kaleoTaskAssignments) {
		for (KaleoTaskAssignment kaleoTaskAssignment : kaleoTaskAssignments) {
			if (EntityCacheUtil.getResult(
						KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTaskAssignmentImpl.class,
						kaleoTaskAssignment.getPrimaryKey()) == null) {
				cacheResult(kaleoTaskAssignment);
			}
			else {
				kaleoTaskAssignment.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all kaleo task assignments.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(KaleoTaskAssignmentImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the kaleo task assignment.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KaleoTaskAssignment kaleoTaskAssignment) {
		EntityCacheUtil.removeResult(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class, kaleoTaskAssignment.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<KaleoTaskAssignment> kaleoTaskAssignments) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (KaleoTaskAssignment kaleoTaskAssignment : kaleoTaskAssignments) {
			EntityCacheUtil.removeResult(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
				KaleoTaskAssignmentImpl.class,
				kaleoTaskAssignment.getPrimaryKey());
		}
	}

	/**
	 * Creates a new kaleo task assignment with the primary key. Does not add the kaleo task assignment to the database.
	 *
	 * @param kaleoTaskAssignmentId the primary key for the new kaleo task assignment
	 * @return the new kaleo task assignment
	 */
	@Override
	public KaleoTaskAssignment create(long kaleoTaskAssignmentId) {
		KaleoTaskAssignment kaleoTaskAssignment = new KaleoTaskAssignmentImpl();

		kaleoTaskAssignment.setNew(true);
		kaleoTaskAssignment.setPrimaryKey(kaleoTaskAssignmentId);

		return kaleoTaskAssignment;
	}

	/**
	 * Removes the kaleo task assignment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTaskAssignmentId the primary key of the kaleo task assignment
	 * @return the kaleo task assignment that was removed
	 * @throws NoSuchTaskAssignmentException if a kaleo task assignment with the primary key could not be found
	 */
	@Override
	public KaleoTaskAssignment remove(long kaleoTaskAssignmentId)
		throws NoSuchTaskAssignmentException {
		return remove((Serializable)kaleoTaskAssignmentId);
	}

	/**
	 * Removes the kaleo task assignment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo task assignment
	 * @return the kaleo task assignment that was removed
	 * @throws NoSuchTaskAssignmentException if a kaleo task assignment with the primary key could not be found
	 */
	@Override
	public KaleoTaskAssignment remove(Serializable primaryKey)
		throws NoSuchTaskAssignmentException {
		Session session = null;

		try {
			session = openSession();

			KaleoTaskAssignment kaleoTaskAssignment = (KaleoTaskAssignment)session.get(KaleoTaskAssignmentImpl.class,
					primaryKey);

			if (kaleoTaskAssignment == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTaskAssignmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(kaleoTaskAssignment);
		}
		catch (NoSuchTaskAssignmentException nsee) {
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
	protected KaleoTaskAssignment removeImpl(
		KaleoTaskAssignment kaleoTaskAssignment) {
		kaleoTaskAssignment = toUnwrappedModel(kaleoTaskAssignment);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(kaleoTaskAssignment)) {
				kaleoTaskAssignment = (KaleoTaskAssignment)session.get(KaleoTaskAssignmentImpl.class,
						kaleoTaskAssignment.getPrimaryKeyObj());
			}

			if (kaleoTaskAssignment != null) {
				session.delete(kaleoTaskAssignment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (kaleoTaskAssignment != null) {
			clearCache(kaleoTaskAssignment);
		}

		return kaleoTaskAssignment;
	}

	@Override
	public KaleoTaskAssignment updateImpl(
		KaleoTaskAssignment kaleoTaskAssignment) {
		kaleoTaskAssignment = toUnwrappedModel(kaleoTaskAssignment);

		boolean isNew = kaleoTaskAssignment.isNew();

		KaleoTaskAssignmentModelImpl kaleoTaskAssignmentModelImpl = (KaleoTaskAssignmentModelImpl)kaleoTaskAssignment;

		Session session = null;

		try {
			session = openSession();

			if (kaleoTaskAssignment.isNew()) {
				session.save(kaleoTaskAssignment);

				kaleoTaskAssignment.setNew(false);
			}
			else {
				session.merge(kaleoTaskAssignment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !KaleoTaskAssignmentModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((kaleoTaskAssignmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoTaskAssignmentModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { kaleoTaskAssignmentModelImpl.getCompanyId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((kaleoTaskAssignmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoTaskAssignmentModelImpl.getOriginalKaleoDefinitionId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);

				args = new Object[] {
						kaleoTaskAssignmentModelImpl.getKaleoDefinitionId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);
			}

			if ((kaleoTaskAssignmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoTaskAssignmentModelImpl.getOriginalKaleoClassName(),
						kaleoTaskAssignmentModelImpl.getOriginalKaleoClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KCN_KCPK, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK,
					args);

				args = new Object[] {
						kaleoTaskAssignmentModelImpl.getKaleoClassName(),
						kaleoTaskAssignmentModelImpl.getKaleoClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KCN_KCPK, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK,
					args);
			}

			if ((kaleoTaskAssignmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_ACN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoTaskAssignmentModelImpl.getOriginalKaleoClassName(),
						kaleoTaskAssignmentModelImpl.getOriginalKaleoClassPK(),
						kaleoTaskAssignmentModelImpl.getOriginalAssigneeClassName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KCN_KCPK_ACN,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_ACN,
					args);

				args = new Object[] {
						kaleoTaskAssignmentModelImpl.getKaleoClassName(),
						kaleoTaskAssignmentModelImpl.getKaleoClassPK(),
						kaleoTaskAssignmentModelImpl.getAssigneeClassName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KCN_KCPK_ACN,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_ACN,
					args);
			}
		}

		EntityCacheUtil.putResult(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentImpl.class, kaleoTaskAssignment.getPrimaryKey(),
			kaleoTaskAssignment, false);

		kaleoTaskAssignment.resetOriginalValues();

		return kaleoTaskAssignment;
	}

	protected KaleoTaskAssignment toUnwrappedModel(
		KaleoTaskAssignment kaleoTaskAssignment) {
		if (kaleoTaskAssignment instanceof KaleoTaskAssignmentImpl) {
			return kaleoTaskAssignment;
		}

		KaleoTaskAssignmentImpl kaleoTaskAssignmentImpl = new KaleoTaskAssignmentImpl();

		kaleoTaskAssignmentImpl.setNew(kaleoTaskAssignment.isNew());
		kaleoTaskAssignmentImpl.setPrimaryKey(kaleoTaskAssignment.getPrimaryKey());

		kaleoTaskAssignmentImpl.setKaleoTaskAssignmentId(kaleoTaskAssignment.getKaleoTaskAssignmentId());
		kaleoTaskAssignmentImpl.setGroupId(kaleoTaskAssignment.getGroupId());
		kaleoTaskAssignmentImpl.setCompanyId(kaleoTaskAssignment.getCompanyId());
		kaleoTaskAssignmentImpl.setUserId(kaleoTaskAssignment.getUserId());
		kaleoTaskAssignmentImpl.setUserName(kaleoTaskAssignment.getUserName());
		kaleoTaskAssignmentImpl.setCreateDate(kaleoTaskAssignment.getCreateDate());
		kaleoTaskAssignmentImpl.setModifiedDate(kaleoTaskAssignment.getModifiedDate());
		kaleoTaskAssignmentImpl.setKaleoClassName(kaleoTaskAssignment.getKaleoClassName());
		kaleoTaskAssignmentImpl.setKaleoClassPK(kaleoTaskAssignment.getKaleoClassPK());
		kaleoTaskAssignmentImpl.setKaleoDefinitionId(kaleoTaskAssignment.getKaleoDefinitionId());
		kaleoTaskAssignmentImpl.setKaleoNodeId(kaleoTaskAssignment.getKaleoNodeId());
		kaleoTaskAssignmentImpl.setAssigneeClassName(kaleoTaskAssignment.getAssigneeClassName());
		kaleoTaskAssignmentImpl.setAssigneeClassPK(kaleoTaskAssignment.getAssigneeClassPK());
		kaleoTaskAssignmentImpl.setAssigneeActionId(kaleoTaskAssignment.getAssigneeActionId());
		kaleoTaskAssignmentImpl.setAssigneeScript(kaleoTaskAssignment.getAssigneeScript());
		kaleoTaskAssignmentImpl.setAssigneeScriptLanguage(kaleoTaskAssignment.getAssigneeScriptLanguage());
		kaleoTaskAssignmentImpl.setAssigneeScriptRequiredContexts(kaleoTaskAssignment.getAssigneeScriptRequiredContexts());

		return kaleoTaskAssignmentImpl;
	}

	/**
	 * Returns the kaleo task assignment with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo task assignment
	 * @return the kaleo task assignment
	 * @throws NoSuchTaskAssignmentException if a kaleo task assignment with the primary key could not be found
	 */
	@Override
	public KaleoTaskAssignment findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTaskAssignmentException {
		KaleoTaskAssignment kaleoTaskAssignment = fetchByPrimaryKey(primaryKey);

		if (kaleoTaskAssignment == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTaskAssignmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return kaleoTaskAssignment;
	}

	/**
	 * Returns the kaleo task assignment with the primary key or throws a {@link NoSuchTaskAssignmentException} if it could not be found.
	 *
	 * @param kaleoTaskAssignmentId the primary key of the kaleo task assignment
	 * @return the kaleo task assignment
	 * @throws NoSuchTaskAssignmentException if a kaleo task assignment with the primary key could not be found
	 */
	@Override
	public KaleoTaskAssignment findByPrimaryKey(long kaleoTaskAssignmentId)
		throws NoSuchTaskAssignmentException {
		return findByPrimaryKey((Serializable)kaleoTaskAssignmentId);
	}

	/**
	 * Returns the kaleo task assignment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo task assignment
	 * @return the kaleo task assignment, or <code>null</code> if a kaleo task assignment with the primary key could not be found
	 */
	@Override
	public KaleoTaskAssignment fetchByPrimaryKey(Serializable primaryKey) {
		KaleoTaskAssignment kaleoTaskAssignment = (KaleoTaskAssignment)EntityCacheUtil.getResult(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
				KaleoTaskAssignmentImpl.class, primaryKey);

		if (kaleoTaskAssignment == _nullKaleoTaskAssignment) {
			return null;
		}

		if (kaleoTaskAssignment == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoTaskAssignment = (KaleoTaskAssignment)session.get(KaleoTaskAssignmentImpl.class,
						primaryKey);

				if (kaleoTaskAssignment != null) {
					cacheResult(kaleoTaskAssignment);
				}
				else {
					EntityCacheUtil.putResult(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTaskAssignmentImpl.class, primaryKey,
						_nullKaleoTaskAssignment);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
					KaleoTaskAssignmentImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return kaleoTaskAssignment;
	}

	/**
	 * Returns the kaleo task assignment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoTaskAssignmentId the primary key of the kaleo task assignment
	 * @return the kaleo task assignment, or <code>null</code> if a kaleo task assignment with the primary key could not be found
	 */
	@Override
	public KaleoTaskAssignment fetchByPrimaryKey(long kaleoTaskAssignmentId) {
		return fetchByPrimaryKey((Serializable)kaleoTaskAssignmentId);
	}

	@Override
	public Map<Serializable, KaleoTaskAssignment> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, KaleoTaskAssignment> map = new HashMap<Serializable, KaleoTaskAssignment>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			KaleoTaskAssignment kaleoTaskAssignment = fetchByPrimaryKey(primaryKey);

			if (kaleoTaskAssignment != null) {
				map.put(primaryKey, kaleoTaskAssignment);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			KaleoTaskAssignment kaleoTaskAssignment = (KaleoTaskAssignment)EntityCacheUtil.getResult(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
					KaleoTaskAssignmentImpl.class, primaryKey);

			if (kaleoTaskAssignment == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, kaleoTaskAssignment);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_KALEOTASKASSIGNMENT_WHERE_PKS_IN);

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

			for (KaleoTaskAssignment kaleoTaskAssignment : (List<KaleoTaskAssignment>)q.list()) {
				map.put(kaleoTaskAssignment.getPrimaryKeyObj(),
					kaleoTaskAssignment);

				cacheResult(kaleoTaskAssignment);

				uncachedPrimaryKeys.remove(kaleoTaskAssignment.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(KaleoTaskAssignmentModelImpl.ENTITY_CACHE_ENABLED,
					KaleoTaskAssignmentImpl.class, primaryKey,
					_nullKaleoTaskAssignment);
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
	 * Returns all the kaleo task assignments.
	 *
	 * @return the kaleo task assignments
	 */
	@Override
	public List<KaleoTaskAssignment> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo task assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTaskAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo task assignments
	 * @param end the upper bound of the range of kaleo task assignments (not inclusive)
	 * @return the range of kaleo task assignments
	 */
	@Override
	public List<KaleoTaskAssignment> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo task assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTaskAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo task assignments
	 * @param end the upper bound of the range of kaleo task assignments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo task assignments
	 */
	@Override
	public List<KaleoTaskAssignment> findAll(int start, int end,
		OrderByComparator<KaleoTaskAssignment> orderByComparator) {
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

		List<KaleoTaskAssignment> list = (List<KaleoTaskAssignment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KALEOTASKASSIGNMENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEOTASKASSIGNMENT;

				if (pagination) {
					sql = sql.concat(KaleoTaskAssignmentModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<KaleoTaskAssignment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoTaskAssignment>)QueryUtil.list(q,
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
	 * Removes all the kaleo task assignments from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (KaleoTaskAssignment kaleoTaskAssignment : findAll()) {
			remove(kaleoTaskAssignment);
		}
	}

	/**
	 * Returns the number of kaleo task assignments.
	 *
	 * @return the number of kaleo task assignments
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KALEOTASKASSIGNMENT);

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
	 * Initializes the kaleo task assignment persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(KaleoTaskAssignmentImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_KALEOTASKASSIGNMENT = "SELECT kaleoTaskAssignment FROM KaleoTaskAssignment kaleoTaskAssignment";
	private static final String _SQL_SELECT_KALEOTASKASSIGNMENT_WHERE_PKS_IN = "SELECT kaleoTaskAssignment FROM KaleoTaskAssignment kaleoTaskAssignment WHERE kaleoTaskAssignmentId IN (";
	private static final String _SQL_SELECT_KALEOTASKASSIGNMENT_WHERE = "SELECT kaleoTaskAssignment FROM KaleoTaskAssignment kaleoTaskAssignment WHERE ";
	private static final String _SQL_COUNT_KALEOTASKASSIGNMENT = "SELECT COUNT(kaleoTaskAssignment) FROM KaleoTaskAssignment kaleoTaskAssignment";
	private static final String _SQL_COUNT_KALEOTASKASSIGNMENT_WHERE = "SELECT COUNT(kaleoTaskAssignment) FROM KaleoTaskAssignment kaleoTaskAssignment WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoTaskAssignment.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoTaskAssignment exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoTaskAssignment exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(KaleoTaskAssignmentPersistenceImpl.class);
	private static final KaleoTaskAssignment _nullKaleoTaskAssignment = new KaleoTaskAssignmentImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<KaleoTaskAssignment> toCacheModel() {
				return _nullKaleoTaskAssignmentCacheModel;
			}
		};

	private static final CacheModel<KaleoTaskAssignment> _nullKaleoTaskAssignmentCacheModel =
		new CacheModel<KaleoTaskAssignment>() {
			@Override
			public KaleoTaskAssignment toEntityModel() {
				return _nullKaleoTaskAssignment;
			}
		};
}