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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.NoSuchTransitionException;
import com.liferay.portal.workflow.kaleo.model.KaleoTransition;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionModelImpl;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTransitionPersistence;

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
 * The persistence implementation for the kaleo transition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTransitionPersistence
 * @see com.liferay.portal.workflow.kaleo.service.persistence.KaleoTransitionUtil
 * @generated
 */
@ProviderType
public class KaleoTransitionPersistenceImpl extends BasePersistenceImpl<KaleoTransition>
	implements KaleoTransitionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KaleoTransitionUtil} to access the kaleo transition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoTransitionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoTransitionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoTransitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoTransitionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoTransitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			KaleoTransitionModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo transitions where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findByCompanyId(long companyId) {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the kaleo transitions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTransitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @return the range of matching kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findByCompanyId(long companyId, int start,
		int end) {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo transitions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTransitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<KaleoTransition> orderByComparator) {
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

		List<KaleoTransition> list = (List<KaleoTransition>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoTransition kaleoTransition : list) {
				if ((companyId != kaleoTransition.getCompanyId())) {
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

			query.append(_SQL_SELECT_KALEOTRANSITION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoTransitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<KaleoTransition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoTransition>)QueryUtil.list(q,
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
	 * Returns the first kaleo transition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo transition
	 * @throws NoSuchTransitionException if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition findByCompanyId_First(long companyId,
		OrderByComparator<KaleoTransition> orderByComparator)
		throws NoSuchTransitionException {
		KaleoTransition kaleoTransition = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (kaleoTransition != null) {
			return kaleoTransition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTransitionException(msg.toString());
	}

	/**
	 * Returns the first kaleo transition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition fetchByCompanyId_First(long companyId,
		OrderByComparator<KaleoTransition> orderByComparator) {
		List<KaleoTransition> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo transition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo transition
	 * @throws NoSuchTransitionException if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition findByCompanyId_Last(long companyId,
		OrderByComparator<KaleoTransition> orderByComparator)
		throws NoSuchTransitionException {
		KaleoTransition kaleoTransition = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (kaleoTransition != null) {
			return kaleoTransition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTransitionException(msg.toString());
	}

	/**
	 * Returns the last kaleo transition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition fetchByCompanyId_Last(long companyId,
		OrderByComparator<KaleoTransition> orderByComparator) {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<KaleoTransition> list = findByCompanyId(companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo transitions before and after the current kaleo transition in the ordered set where companyId = &#63;.
	 *
	 * @param kaleoTransitionId the primary key of the current kaleo transition
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo transition
	 * @throws NoSuchTransitionException if a kaleo transition with the primary key could not be found
	 */
	@Override
	public KaleoTransition[] findByCompanyId_PrevAndNext(
		long kaleoTransitionId, long companyId,
		OrderByComparator<KaleoTransition> orderByComparator)
		throws NoSuchTransitionException {
		KaleoTransition kaleoTransition = findByPrimaryKey(kaleoTransitionId);

		Session session = null;

		try {
			session = openSession();

			KaleoTransition[] array = new KaleoTransitionImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, kaleoTransition,
					companyId, orderByComparator, true);

			array[1] = kaleoTransition;

			array[2] = getByCompanyId_PrevAndNext(session, kaleoTransition,
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

	protected KaleoTransition getByCompanyId_PrevAndNext(Session session,
		KaleoTransition kaleoTransition, long companyId,
		OrderByComparator<KaleoTransition> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTRANSITION_WHERE);

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
			query.append(KaleoTransitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoTransition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTransition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo transitions where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (KaleoTransition kaleoTransition : findByCompanyId(companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoTransition);
		}
	}

	/**
	 * Returns the number of kaleo transitions where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching kaleo transitions
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTRANSITION_WHERE);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoTransition.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEODEFINITIONID =
		new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoTransitionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID =
		new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoTransitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByKaleoDefinitionId", new String[] { Long.class.getName() },
			KaleoTransitionModelImpl.KALEODEFINITIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEODEFINITIONID = new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKaleoDefinitionId", new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo transitions where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the matching kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findByKaleoDefinitionId(long kaleoDefinitionId) {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo transitions where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTransitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @return the range of matching kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end) {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo transitions where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTransitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		OrderByComparator<KaleoTransition> orderByComparator) {
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

		List<KaleoTransition> list = (List<KaleoTransition>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoTransition kaleoTransition : list) {
				if ((kaleoDefinitionId != kaleoTransition.getKaleoDefinitionId())) {
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

			query.append(_SQL_SELECT_KALEOTRANSITION_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoTransitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				if (!pagination) {
					list = (List<KaleoTransition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoTransition>)QueryUtil.list(q,
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
	 * Returns the first kaleo transition in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo transition
	 * @throws NoSuchTransitionException if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		OrderByComparator<KaleoTransition> orderByComparator)
		throws NoSuchTransitionException {
		KaleoTransition kaleoTransition = fetchByKaleoDefinitionId_First(kaleoDefinitionId,
				orderByComparator);

		if (kaleoTransition != null) {
			return kaleoTransition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoDefinitionId=");
		msg.append(kaleoDefinitionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTransitionException(msg.toString());
	}

	/**
	 * Returns the first kaleo transition in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition fetchByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		OrderByComparator<KaleoTransition> orderByComparator) {
		List<KaleoTransition> list = findByKaleoDefinitionId(kaleoDefinitionId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo transition in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo transition
	 * @throws NoSuchTransitionException if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		OrderByComparator<KaleoTransition> orderByComparator)
		throws NoSuchTransitionException {
		KaleoTransition kaleoTransition = fetchByKaleoDefinitionId_Last(kaleoDefinitionId,
				orderByComparator);

		if (kaleoTransition != null) {
			return kaleoTransition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoDefinitionId=");
		msg.append(kaleoDefinitionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTransitionException(msg.toString());
	}

	/**
	 * Returns the last kaleo transition in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition fetchByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		OrderByComparator<KaleoTransition> orderByComparator) {
		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		if (count == 0) {
			return null;
		}

		List<KaleoTransition> list = findByKaleoDefinitionId(kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo transitions before and after the current kaleo transition in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoTransitionId the primary key of the current kaleo transition
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo transition
	 * @throws NoSuchTransitionException if a kaleo transition with the primary key could not be found
	 */
	@Override
	public KaleoTransition[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoTransitionId, long kaleoDefinitionId,
		OrderByComparator<KaleoTransition> orderByComparator)
		throws NoSuchTransitionException {
		KaleoTransition kaleoTransition = findByPrimaryKey(kaleoTransitionId);

		Session session = null;

		try {
			session = openSession();

			KaleoTransition[] array = new KaleoTransitionImpl[3];

			array[0] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoTransition, kaleoDefinitionId, orderByComparator, true);

			array[1] = kaleoTransition;

			array[2] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoTransition, kaleoDefinitionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTransition getByKaleoDefinitionId_PrevAndNext(
		Session session, KaleoTransition kaleoTransition,
		long kaleoDefinitionId,
		OrderByComparator<KaleoTransition> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTRANSITION_WHERE);

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
			query.append(KaleoTransitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoTransition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTransition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo transitions where kaleoDefinitionId = &#63; from the database.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 */
	@Override
	public void removeByKaleoDefinitionId(long kaleoDefinitionId) {
		for (KaleoTransition kaleoTransition : findByKaleoDefinitionId(
				kaleoDefinitionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoTransition);
		}
	}

	/**
	 * Returns the number of kaleo transitions where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the number of matching kaleo transitions
	 */
	@Override
	public int countByKaleoDefinitionId(long kaleoDefinitionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KALEODEFINITIONID;

		Object[] finderArgs = new Object[] { kaleoDefinitionId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTRANSITION_WHERE);

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
		"kaleoTransition.kaleoDefinitionId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEONODEID =
		new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoTransitionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByKaleoNodeId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEONODEID =
		new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoTransitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByKaleoNodeId",
			new String[] { Long.class.getName() },
			KaleoTransitionModelImpl.KALEONODEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEONODEID = new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKaleoNodeId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo transitions where kaleoNodeId = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @return the matching kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findByKaleoNodeId(long kaleoNodeId) {
		return findByKaleoNodeId(kaleoNodeId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo transitions where kaleoNodeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTransitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @return the range of matching kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findByKaleoNodeId(long kaleoNodeId, int start,
		int end) {
		return findByKaleoNodeId(kaleoNodeId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo transitions where kaleoNodeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTransitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findByKaleoNodeId(long kaleoNodeId, int start,
		int end, OrderByComparator<KaleoTransition> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEONODEID;
			finderArgs = new Object[] { kaleoNodeId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEONODEID;
			finderArgs = new Object[] { kaleoNodeId, start, end, orderByComparator };
		}

		List<KaleoTransition> list = (List<KaleoTransition>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoTransition kaleoTransition : list) {
				if ((kaleoNodeId != kaleoTransition.getKaleoNodeId())) {
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

			query.append(_SQL_SELECT_KALEOTRANSITION_WHERE);

			query.append(_FINDER_COLUMN_KALEONODEID_KALEONODEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoTransitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNodeId);

				if (!pagination) {
					list = (List<KaleoTransition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoTransition>)QueryUtil.list(q,
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
	 * Returns the first kaleo transition in the ordered set where kaleoNodeId = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo transition
	 * @throws NoSuchTransitionException if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition findByKaleoNodeId_First(long kaleoNodeId,
		OrderByComparator<KaleoTransition> orderByComparator)
		throws NoSuchTransitionException {
		KaleoTransition kaleoTransition = fetchByKaleoNodeId_First(kaleoNodeId,
				orderByComparator);

		if (kaleoTransition != null) {
			return kaleoTransition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoNodeId=");
		msg.append(kaleoNodeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTransitionException(msg.toString());
	}

	/**
	 * Returns the first kaleo transition in the ordered set where kaleoNodeId = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition fetchByKaleoNodeId_First(long kaleoNodeId,
		OrderByComparator<KaleoTransition> orderByComparator) {
		List<KaleoTransition> list = findByKaleoNodeId(kaleoNodeId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo transition in the ordered set where kaleoNodeId = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo transition
	 * @throws NoSuchTransitionException if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition findByKaleoNodeId_Last(long kaleoNodeId,
		OrderByComparator<KaleoTransition> orderByComparator)
		throws NoSuchTransitionException {
		KaleoTransition kaleoTransition = fetchByKaleoNodeId_Last(kaleoNodeId,
				orderByComparator);

		if (kaleoTransition != null) {
			return kaleoTransition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoNodeId=");
		msg.append(kaleoNodeId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTransitionException(msg.toString());
	}

	/**
	 * Returns the last kaleo transition in the ordered set where kaleoNodeId = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition fetchByKaleoNodeId_Last(long kaleoNodeId,
		OrderByComparator<KaleoTransition> orderByComparator) {
		int count = countByKaleoNodeId(kaleoNodeId);

		if (count == 0) {
			return null;
		}

		List<KaleoTransition> list = findByKaleoNodeId(kaleoNodeId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo transitions before and after the current kaleo transition in the ordered set where kaleoNodeId = &#63;.
	 *
	 * @param kaleoTransitionId the primary key of the current kaleo transition
	 * @param kaleoNodeId the kaleo node ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo transition
	 * @throws NoSuchTransitionException if a kaleo transition with the primary key could not be found
	 */
	@Override
	public KaleoTransition[] findByKaleoNodeId_PrevAndNext(
		long kaleoTransitionId, long kaleoNodeId,
		OrderByComparator<KaleoTransition> orderByComparator)
		throws NoSuchTransitionException {
		KaleoTransition kaleoTransition = findByPrimaryKey(kaleoTransitionId);

		Session session = null;

		try {
			session = openSession();

			KaleoTransition[] array = new KaleoTransitionImpl[3];

			array[0] = getByKaleoNodeId_PrevAndNext(session, kaleoTransition,
					kaleoNodeId, orderByComparator, true);

			array[1] = kaleoTransition;

			array[2] = getByKaleoNodeId_PrevAndNext(session, kaleoTransition,
					kaleoNodeId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTransition getByKaleoNodeId_PrevAndNext(Session session,
		KaleoTransition kaleoTransition, long kaleoNodeId,
		OrderByComparator<KaleoTransition> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTRANSITION_WHERE);

		query.append(_FINDER_COLUMN_KALEONODEID_KALEONODEID_2);

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
			query.append(KaleoTransitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoNodeId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoTransition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTransition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo transitions where kaleoNodeId = &#63; from the database.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 */
	@Override
	public void removeByKaleoNodeId(long kaleoNodeId) {
		for (KaleoTransition kaleoTransition : findByKaleoNodeId(kaleoNodeId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoTransition);
		}
	}

	/**
	 * Returns the number of kaleo transitions where kaleoNodeId = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @return the number of matching kaleo transitions
	 */
	@Override
	public int countByKaleoNodeId(long kaleoNodeId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KALEONODEID;

		Object[] finderArgs = new Object[] { kaleoNodeId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTRANSITION_WHERE);

			query.append(_FINDER_COLUMN_KALEONODEID_KALEONODEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNodeId);

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

	private static final String _FINDER_COLUMN_KALEONODEID_KALEONODEID_2 = "kaleoTransition.kaleoNodeId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_KNI_N = new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoTransitionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByKNI_N",
			new String[] { Long.class.getName(), String.class.getName() },
			KaleoTransitionModelImpl.KALEONODEID_COLUMN_BITMASK |
			KaleoTransitionModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KNI_N = new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKNI_N",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the kaleo transition where kaleoNodeId = &#63; and name = &#63; or throws a {@link NoSuchTransitionException} if it could not be found.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param name the name
	 * @return the matching kaleo transition
	 * @throws NoSuchTransitionException if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition findByKNI_N(long kaleoNodeId, String name)
		throws NoSuchTransitionException {
		KaleoTransition kaleoTransition = fetchByKNI_N(kaleoNodeId, name);

		if (kaleoTransition == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoNodeId=");
			msg.append(kaleoNodeId);

			msg.append(", name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTransitionException(msg.toString());
		}

		return kaleoTransition;
	}

	/**
	 * Returns the kaleo transition where kaleoNodeId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param name the name
	 * @return the matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition fetchByKNI_N(long kaleoNodeId, String name) {
		return fetchByKNI_N(kaleoNodeId, name, true);
	}

	/**
	 * Returns the kaleo transition where kaleoNodeId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param name the name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition fetchByKNI_N(long kaleoNodeId, String name,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { kaleoNodeId, name };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_KNI_N,
					finderArgs, this);
		}

		if (result instanceof KaleoTransition) {
			KaleoTransition kaleoTransition = (KaleoTransition)result;

			if ((kaleoNodeId != kaleoTransition.getKaleoNodeId()) ||
					!Validator.equals(name, kaleoTransition.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_KALEOTRANSITION_WHERE);

			query.append(_FINDER_COLUMN_KNI_N_KALEONODEID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_KNI_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KNI_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_KNI_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNodeId);

				if (bindName) {
					qPos.add(name);
				}

				List<KaleoTransition> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KNI_N,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"KaleoTransitionPersistenceImpl.fetchByKNI_N(long, String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					KaleoTransition kaleoTransition = list.get(0);

					result = kaleoTransition;

					cacheResult(kaleoTransition);

					if ((kaleoTransition.getKaleoNodeId() != kaleoNodeId) ||
							(kaleoTransition.getName() == null) ||
							!kaleoTransition.getName().equals(name)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KNI_N,
							finderArgs, kaleoTransition);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KNI_N,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (KaleoTransition)result;
		}
	}

	/**
	 * Removes the kaleo transition where kaleoNodeId = &#63; and name = &#63; from the database.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param name the name
	 * @return the kaleo transition that was removed
	 */
	@Override
	public KaleoTransition removeByKNI_N(long kaleoNodeId, String name)
		throws NoSuchTransitionException {
		KaleoTransition kaleoTransition = findByKNI_N(kaleoNodeId, name);

		return remove(kaleoTransition);
	}

	/**
	 * Returns the number of kaleo transitions where kaleoNodeId = &#63; and name = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param name the name
	 * @return the number of matching kaleo transitions
	 */
	@Override
	public int countByKNI_N(long kaleoNodeId, String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KNI_N;

		Object[] finderArgs = new Object[] { kaleoNodeId, name };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEOTRANSITION_WHERE);

			query.append(_FINDER_COLUMN_KNI_N_KALEONODEID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_KNI_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KNI_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_KNI_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNodeId);

				if (bindName) {
					qPos.add(name);
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

	private static final String _FINDER_COLUMN_KNI_N_KALEONODEID_2 = "kaleoTransition.kaleoNodeId = ? AND ";
	private static final String _FINDER_COLUMN_KNI_N_NAME_1 = "kaleoTransition.name IS NULL";
	private static final String _FINDER_COLUMN_KNI_N_NAME_2 = "kaleoTransition.name = ?";
	private static final String _FINDER_COLUMN_KNI_N_NAME_3 = "(kaleoTransition.name IS NULL OR kaleoTransition.name = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_KNI_DT = new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoTransitionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByKNI_DT",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			KaleoTransitionModelImpl.KALEONODEID_COLUMN_BITMASK |
			KaleoTransitionModelImpl.DEFAULTTRANSITION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KNI_DT = new FinderPath(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKNI_DT",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns the kaleo transition where kaleoNodeId = &#63; and defaultTransition = &#63; or throws a {@link NoSuchTransitionException} if it could not be found.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param defaultTransition the default transition
	 * @return the matching kaleo transition
	 * @throws NoSuchTransitionException if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition findByKNI_DT(long kaleoNodeId,
		boolean defaultTransition) throws NoSuchTransitionException {
		KaleoTransition kaleoTransition = fetchByKNI_DT(kaleoNodeId,
				defaultTransition);

		if (kaleoTransition == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoNodeId=");
			msg.append(kaleoNodeId);

			msg.append(", defaultTransition=");
			msg.append(defaultTransition);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTransitionException(msg.toString());
		}

		return kaleoTransition;
	}

	/**
	 * Returns the kaleo transition where kaleoNodeId = &#63; and defaultTransition = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param defaultTransition the default transition
	 * @return the matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition fetchByKNI_DT(long kaleoNodeId,
		boolean defaultTransition) {
		return fetchByKNI_DT(kaleoNodeId, defaultTransition, true);
	}

	/**
	 * Returns the kaleo transition where kaleoNodeId = &#63; and defaultTransition = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param defaultTransition the default transition
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching kaleo transition, or <code>null</code> if a matching kaleo transition could not be found
	 */
	@Override
	public KaleoTransition fetchByKNI_DT(long kaleoNodeId,
		boolean defaultTransition, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { kaleoNodeId, defaultTransition };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_KNI_DT,
					finderArgs, this);
		}

		if (result instanceof KaleoTransition) {
			KaleoTransition kaleoTransition = (KaleoTransition)result;

			if ((kaleoNodeId != kaleoTransition.getKaleoNodeId()) ||
					(defaultTransition != kaleoTransition.getDefaultTransition())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_KALEOTRANSITION_WHERE);

			query.append(_FINDER_COLUMN_KNI_DT_KALEONODEID_2);

			query.append(_FINDER_COLUMN_KNI_DT_DEFAULTTRANSITION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNodeId);

				qPos.add(defaultTransition);

				List<KaleoTransition> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KNI_DT,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"KaleoTransitionPersistenceImpl.fetchByKNI_DT(long, boolean, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					KaleoTransition kaleoTransition = list.get(0);

					result = kaleoTransition;

					cacheResult(kaleoTransition);

					if ((kaleoTransition.getKaleoNodeId() != kaleoNodeId) ||
							(kaleoTransition.getDefaultTransition() != defaultTransition)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KNI_DT,
							finderArgs, kaleoTransition);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KNI_DT,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (KaleoTransition)result;
		}
	}

	/**
	 * Removes the kaleo transition where kaleoNodeId = &#63; and defaultTransition = &#63; from the database.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param defaultTransition the default transition
	 * @return the kaleo transition that was removed
	 */
	@Override
	public KaleoTransition removeByKNI_DT(long kaleoNodeId,
		boolean defaultTransition) throws NoSuchTransitionException {
		KaleoTransition kaleoTransition = findByKNI_DT(kaleoNodeId,
				defaultTransition);

		return remove(kaleoTransition);
	}

	/**
	 * Returns the number of kaleo transitions where kaleoNodeId = &#63; and defaultTransition = &#63;.
	 *
	 * @param kaleoNodeId the kaleo node ID
	 * @param defaultTransition the default transition
	 * @return the number of matching kaleo transitions
	 */
	@Override
	public int countByKNI_DT(long kaleoNodeId, boolean defaultTransition) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KNI_DT;

		Object[] finderArgs = new Object[] { kaleoNodeId, defaultTransition };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEOTRANSITION_WHERE);

			query.append(_FINDER_COLUMN_KNI_DT_KALEONODEID_2);

			query.append(_FINDER_COLUMN_KNI_DT_DEFAULTTRANSITION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoNodeId);

				qPos.add(defaultTransition);

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

	private static final String _FINDER_COLUMN_KNI_DT_KALEONODEID_2 = "kaleoTransition.kaleoNodeId = ? AND ";
	private static final String _FINDER_COLUMN_KNI_DT_DEFAULTTRANSITION_2 = "kaleoTransition.defaultTransition = ?";

	public KaleoTransitionPersistenceImpl() {
		setModelClass(KaleoTransition.class);
	}

	/**
	 * Caches the kaleo transition in the entity cache if it is enabled.
	 *
	 * @param kaleoTransition the kaleo transition
	 */
	@Override
	public void cacheResult(KaleoTransition kaleoTransition) {
		EntityCacheUtil.putResult(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionImpl.class, kaleoTransition.getPrimaryKey(),
			kaleoTransition);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KNI_N,
			new Object[] {
				kaleoTransition.getKaleoNodeId(), kaleoTransition.getName()
			}, kaleoTransition);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KNI_DT,
			new Object[] {
				kaleoTransition.getKaleoNodeId(),
				kaleoTransition.getDefaultTransition()
			}, kaleoTransition);

		kaleoTransition.resetOriginalValues();
	}

	/**
	 * Caches the kaleo transitions in the entity cache if it is enabled.
	 *
	 * @param kaleoTransitions the kaleo transitions
	 */
	@Override
	public void cacheResult(List<KaleoTransition> kaleoTransitions) {
		for (KaleoTransition kaleoTransition : kaleoTransitions) {
			if (EntityCacheUtil.getResult(
						KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTransitionImpl.class,
						kaleoTransition.getPrimaryKey()) == null) {
				cacheResult(kaleoTransition);
			}
			else {
				kaleoTransition.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all kaleo transitions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(KaleoTransitionImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the kaleo transition.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KaleoTransition kaleoTransition) {
		EntityCacheUtil.removeResult(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionImpl.class, kaleoTransition.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(kaleoTransition);
	}

	@Override
	public void clearCache(List<KaleoTransition> kaleoTransitions) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (KaleoTransition kaleoTransition : kaleoTransitions) {
			EntityCacheUtil.removeResult(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
				KaleoTransitionImpl.class, kaleoTransition.getPrimaryKey());

			clearUniqueFindersCache(kaleoTransition);
		}
	}

	protected void cacheUniqueFindersCache(KaleoTransition kaleoTransition) {
		if (kaleoTransition.isNew()) {
			Object[] args = new Object[] {
					kaleoTransition.getKaleoNodeId(), kaleoTransition.getName()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KNI_N, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KNI_N, args,
				kaleoTransition);

			args = new Object[] {
					kaleoTransition.getKaleoNodeId(),
					kaleoTransition.getDefaultTransition()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KNI_DT, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KNI_DT, args,
				kaleoTransition);
		}
		else {
			KaleoTransitionModelImpl kaleoTransitionModelImpl = (KaleoTransitionModelImpl)kaleoTransition;

			if ((kaleoTransitionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_KNI_N.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoTransition.getKaleoNodeId(),
						kaleoTransition.getName()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KNI_N, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KNI_N, args,
					kaleoTransition);
			}

			if ((kaleoTransitionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_KNI_DT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoTransition.getKaleoNodeId(),
						kaleoTransition.getDefaultTransition()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KNI_DT, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KNI_DT, args,
					kaleoTransition);
			}
		}
	}

	protected void clearUniqueFindersCache(KaleoTransition kaleoTransition) {
		KaleoTransitionModelImpl kaleoTransitionModelImpl = (KaleoTransitionModelImpl)kaleoTransition;

		Object[] args = new Object[] {
				kaleoTransition.getKaleoNodeId(), kaleoTransition.getName()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KNI_N, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KNI_N, args);

		if ((kaleoTransitionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_KNI_N.getColumnBitmask()) != 0) {
			args = new Object[] {
					kaleoTransitionModelImpl.getOriginalKaleoNodeId(),
					kaleoTransitionModelImpl.getOriginalName()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KNI_N, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KNI_N, args);
		}

		args = new Object[] {
				kaleoTransition.getKaleoNodeId(),
				kaleoTransition.getDefaultTransition()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KNI_DT, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KNI_DT, args);

		if ((kaleoTransitionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_KNI_DT.getColumnBitmask()) != 0) {
			args = new Object[] {
					kaleoTransitionModelImpl.getOriginalKaleoNodeId(),
					kaleoTransitionModelImpl.getOriginalDefaultTransition()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KNI_DT, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KNI_DT, args);
		}
	}

	/**
	 * Creates a new kaleo transition with the primary key. Does not add the kaleo transition to the database.
	 *
	 * @param kaleoTransitionId the primary key for the new kaleo transition
	 * @return the new kaleo transition
	 */
	@Override
	public KaleoTransition create(long kaleoTransitionId) {
		KaleoTransition kaleoTransition = new KaleoTransitionImpl();

		kaleoTransition.setNew(true);
		kaleoTransition.setPrimaryKey(kaleoTransitionId);

		return kaleoTransition;
	}

	/**
	 * Removes the kaleo transition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTransitionId the primary key of the kaleo transition
	 * @return the kaleo transition that was removed
	 * @throws NoSuchTransitionException if a kaleo transition with the primary key could not be found
	 */
	@Override
	public KaleoTransition remove(long kaleoTransitionId)
		throws NoSuchTransitionException {
		return remove((Serializable)kaleoTransitionId);
	}

	/**
	 * Removes the kaleo transition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo transition
	 * @return the kaleo transition that was removed
	 * @throws NoSuchTransitionException if a kaleo transition with the primary key could not be found
	 */
	@Override
	public KaleoTransition remove(Serializable primaryKey)
		throws NoSuchTransitionException {
		Session session = null;

		try {
			session = openSession();

			KaleoTransition kaleoTransition = (KaleoTransition)session.get(KaleoTransitionImpl.class,
					primaryKey);

			if (kaleoTransition == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTransitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(kaleoTransition);
		}
		catch (NoSuchTransitionException nsee) {
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
	protected KaleoTransition removeImpl(KaleoTransition kaleoTransition) {
		kaleoTransition = toUnwrappedModel(kaleoTransition);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(kaleoTransition)) {
				kaleoTransition = (KaleoTransition)session.get(KaleoTransitionImpl.class,
						kaleoTransition.getPrimaryKeyObj());
			}

			if (kaleoTransition != null) {
				session.delete(kaleoTransition);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (kaleoTransition != null) {
			clearCache(kaleoTransition);
		}

		return kaleoTransition;
	}

	@Override
	public KaleoTransition updateImpl(KaleoTransition kaleoTransition) {
		kaleoTransition = toUnwrappedModel(kaleoTransition);

		boolean isNew = kaleoTransition.isNew();

		KaleoTransitionModelImpl kaleoTransitionModelImpl = (KaleoTransitionModelImpl)kaleoTransition;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (kaleoTransition.getCreateDate() == null)) {
			if (serviceContext == null) {
				kaleoTransition.setCreateDate(now);
			}
			else {
				kaleoTransition.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!kaleoTransitionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				kaleoTransition.setModifiedDate(now);
			}
			else {
				kaleoTransition.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (kaleoTransition.isNew()) {
				session.save(kaleoTransition);

				kaleoTransition.setNew(false);
			}
			else {
				session.merge(kaleoTransition);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !KaleoTransitionModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((kaleoTransitionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoTransitionModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { kaleoTransitionModelImpl.getCompanyId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((kaleoTransitionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoTransitionModelImpl.getOriginalKaleoDefinitionId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);

				args = new Object[] {
						kaleoTransitionModelImpl.getKaleoDefinitionId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);
			}

			if ((kaleoTransitionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEONODEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoTransitionModelImpl.getOriginalKaleoNodeId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEONODEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEONODEID,
					args);

				args = new Object[] { kaleoTransitionModelImpl.getKaleoNodeId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEONODEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEONODEID,
					args);
			}
		}

		EntityCacheUtil.putResult(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTransitionImpl.class, kaleoTransition.getPrimaryKey(),
			kaleoTransition, false);

		clearUniqueFindersCache(kaleoTransition);
		cacheUniqueFindersCache(kaleoTransition);

		kaleoTransition.resetOriginalValues();

		return kaleoTransition;
	}

	protected KaleoTransition toUnwrappedModel(KaleoTransition kaleoTransition) {
		if (kaleoTransition instanceof KaleoTransitionImpl) {
			return kaleoTransition;
		}

		KaleoTransitionImpl kaleoTransitionImpl = new KaleoTransitionImpl();

		kaleoTransitionImpl.setNew(kaleoTransition.isNew());
		kaleoTransitionImpl.setPrimaryKey(kaleoTransition.getPrimaryKey());

		kaleoTransitionImpl.setKaleoTransitionId(kaleoTransition.getKaleoTransitionId());
		kaleoTransitionImpl.setGroupId(kaleoTransition.getGroupId());
		kaleoTransitionImpl.setCompanyId(kaleoTransition.getCompanyId());
		kaleoTransitionImpl.setUserId(kaleoTransition.getUserId());
		kaleoTransitionImpl.setUserName(kaleoTransition.getUserName());
		kaleoTransitionImpl.setCreateDate(kaleoTransition.getCreateDate());
		kaleoTransitionImpl.setModifiedDate(kaleoTransition.getModifiedDate());
		kaleoTransitionImpl.setKaleoDefinitionId(kaleoTransition.getKaleoDefinitionId());
		kaleoTransitionImpl.setKaleoNodeId(kaleoTransition.getKaleoNodeId());
		kaleoTransitionImpl.setName(kaleoTransition.getName());
		kaleoTransitionImpl.setDescription(kaleoTransition.getDescription());
		kaleoTransitionImpl.setSourceKaleoNodeId(kaleoTransition.getSourceKaleoNodeId());
		kaleoTransitionImpl.setSourceKaleoNodeName(kaleoTransition.getSourceKaleoNodeName());
		kaleoTransitionImpl.setTargetKaleoNodeId(kaleoTransition.getTargetKaleoNodeId());
		kaleoTransitionImpl.setTargetKaleoNodeName(kaleoTransition.getTargetKaleoNodeName());
		kaleoTransitionImpl.setDefaultTransition(kaleoTransition.isDefaultTransition());

		return kaleoTransitionImpl;
	}

	/**
	 * Returns the kaleo transition with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo transition
	 * @return the kaleo transition
	 * @throws NoSuchTransitionException if a kaleo transition with the primary key could not be found
	 */
	@Override
	public KaleoTransition findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTransitionException {
		KaleoTransition kaleoTransition = fetchByPrimaryKey(primaryKey);

		if (kaleoTransition == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTransitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return kaleoTransition;
	}

	/**
	 * Returns the kaleo transition with the primary key or throws a {@link NoSuchTransitionException} if it could not be found.
	 *
	 * @param kaleoTransitionId the primary key of the kaleo transition
	 * @return the kaleo transition
	 * @throws NoSuchTransitionException if a kaleo transition with the primary key could not be found
	 */
	@Override
	public KaleoTransition findByPrimaryKey(long kaleoTransitionId)
		throws NoSuchTransitionException {
		return findByPrimaryKey((Serializable)kaleoTransitionId);
	}

	/**
	 * Returns the kaleo transition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo transition
	 * @return the kaleo transition, or <code>null</code> if a kaleo transition with the primary key could not be found
	 */
	@Override
	public KaleoTransition fetchByPrimaryKey(Serializable primaryKey) {
		KaleoTransition kaleoTransition = (KaleoTransition)EntityCacheUtil.getResult(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
				KaleoTransitionImpl.class, primaryKey);

		if (kaleoTransition == _nullKaleoTransition) {
			return null;
		}

		if (kaleoTransition == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoTransition = (KaleoTransition)session.get(KaleoTransitionImpl.class,
						primaryKey);

				if (kaleoTransition != null) {
					cacheResult(kaleoTransition);
				}
				else {
					EntityCacheUtil.putResult(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTransitionImpl.class, primaryKey,
						_nullKaleoTransition);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
					KaleoTransitionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return kaleoTransition;
	}

	/**
	 * Returns the kaleo transition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoTransitionId the primary key of the kaleo transition
	 * @return the kaleo transition, or <code>null</code> if a kaleo transition with the primary key could not be found
	 */
	@Override
	public KaleoTransition fetchByPrimaryKey(long kaleoTransitionId) {
		return fetchByPrimaryKey((Serializable)kaleoTransitionId);
	}

	@Override
	public Map<Serializable, KaleoTransition> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, KaleoTransition> map = new HashMap<Serializable, KaleoTransition>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			KaleoTransition kaleoTransition = fetchByPrimaryKey(primaryKey);

			if (kaleoTransition != null) {
				map.put(primaryKey, kaleoTransition);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			KaleoTransition kaleoTransition = (KaleoTransition)EntityCacheUtil.getResult(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
					KaleoTransitionImpl.class, primaryKey);

			if (kaleoTransition == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, kaleoTransition);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_KALEOTRANSITION_WHERE_PKS_IN);

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

			for (KaleoTransition kaleoTransition : (List<KaleoTransition>)q.list()) {
				map.put(kaleoTransition.getPrimaryKeyObj(), kaleoTransition);

				cacheResult(kaleoTransition);

				uncachedPrimaryKeys.remove(kaleoTransition.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(KaleoTransitionModelImpl.ENTITY_CACHE_ENABLED,
					KaleoTransitionImpl.class, primaryKey, _nullKaleoTransition);
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
	 * Returns all the kaleo transitions.
	 *
	 * @return the kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo transitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTransitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @return the range of kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo transitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTransitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo transitions
	 * @param end the upper bound of the range of kaleo transitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo transitions
	 */
	@Override
	public List<KaleoTransition> findAll(int start, int end,
		OrderByComparator<KaleoTransition> orderByComparator) {
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

		List<KaleoTransition> list = (List<KaleoTransition>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KALEOTRANSITION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEOTRANSITION;

				if (pagination) {
					sql = sql.concat(KaleoTransitionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<KaleoTransition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoTransition>)QueryUtil.list(q,
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
	 * Removes all the kaleo transitions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (KaleoTransition kaleoTransition : findAll()) {
			remove(kaleoTransition);
		}
	}

	/**
	 * Returns the number of kaleo transitions.
	 *
	 * @return the number of kaleo transitions
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KALEOTRANSITION);

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
		return KaleoTransitionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the kaleo transition persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(KaleoTransitionImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_KALEOTRANSITION = "SELECT kaleoTransition FROM KaleoTransition kaleoTransition";
	private static final String _SQL_SELECT_KALEOTRANSITION_WHERE_PKS_IN = "SELECT kaleoTransition FROM KaleoTransition kaleoTransition WHERE kaleoTransitionId IN (";
	private static final String _SQL_SELECT_KALEOTRANSITION_WHERE = "SELECT kaleoTransition FROM KaleoTransition kaleoTransition WHERE ";
	private static final String _SQL_COUNT_KALEOTRANSITION = "SELECT COUNT(kaleoTransition) FROM KaleoTransition kaleoTransition";
	private static final String _SQL_COUNT_KALEOTRANSITION_WHERE = "SELECT COUNT(kaleoTransition) FROM KaleoTransition kaleoTransition WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoTransition.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoTransition exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoTransition exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(KaleoTransitionPersistenceImpl.class);
	private static final KaleoTransition _nullKaleoTransition = new KaleoTransitionImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<KaleoTransition> toCacheModel() {
				return _nullKaleoTransitionCacheModel;
			}
		};

	private static final CacheModel<KaleoTransition> _nullKaleoTransitionCacheModel =
		new CacheModel<KaleoTransition>() {
			@Override
			public KaleoTransition toEntityModel() {
				return _nullKaleoTransition;
			}
		};
}