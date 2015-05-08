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
import com.liferay.portal.workflow.kaleo.NoSuchActionException;
import com.liferay.portal.workflow.kaleo.model.KaleoAction;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoActionImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoActionModelImpl;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoActionPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the kaleo action service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoActionPersistence
 * @see com.liferay.portal.workflow.kaleo.service.persistence.KaleoActionUtil
 * @generated
 */
@ProviderType
public class KaleoActionPersistenceImpl extends BasePersistenceImpl<KaleoAction>
	implements KaleoActionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KaleoActionUtil} to access the kaleo action persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoActionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionModelImpl.FINDER_CACHE_ENABLED, KaleoActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionModelImpl.FINDER_CACHE_ENABLED, KaleoActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionModelImpl.FINDER_CACHE_ENABLED, KaleoActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionModelImpl.FINDER_CACHE_ENABLED, KaleoActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			KaleoActionModelImpl.COMPANYID_COLUMN_BITMASK |
			KaleoActionModelImpl.PRIORITY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo actions where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching kaleo actions
	 */
	@Override
	public List<KaleoAction> findByCompanyId(long companyId) {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the kaleo actions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo actions
	 * @param end the upper bound of the range of kaleo actions (not inclusive)
	 * @return the range of matching kaleo actions
	 */
	@Override
	public List<KaleoAction> findByCompanyId(long companyId, int start, int end) {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo actions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo actions
	 * @param end the upper bound of the range of kaleo actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo actions
	 */
	@Override
	public List<KaleoAction> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<KaleoAction> orderByComparator) {
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

		List<KaleoAction> list = (List<KaleoAction>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoAction kaleoAction : list) {
				if ((companyId != kaleoAction.getCompanyId())) {
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

			query.append(_SQL_SELECT_KALEOACTION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<KaleoAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first kaleo action in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo action
	 * @throws NoSuchActionException if a matching kaleo action could not be found
	 */
	@Override
	public KaleoAction findByCompanyId_First(long companyId,
		OrderByComparator<KaleoAction> orderByComparator)
		throws NoSuchActionException {
		KaleoAction kaleoAction = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (kaleoAction != null) {
			return kaleoAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActionException(msg.toString());
	}

	/**
	 * Returns the first kaleo action in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo action, or <code>null</code> if a matching kaleo action could not be found
	 */
	@Override
	public KaleoAction fetchByCompanyId_First(long companyId,
		OrderByComparator<KaleoAction> orderByComparator) {
		List<KaleoAction> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo action in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo action
	 * @throws NoSuchActionException if a matching kaleo action could not be found
	 */
	@Override
	public KaleoAction findByCompanyId_Last(long companyId,
		OrderByComparator<KaleoAction> orderByComparator)
		throws NoSuchActionException {
		KaleoAction kaleoAction = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (kaleoAction != null) {
			return kaleoAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActionException(msg.toString());
	}

	/**
	 * Returns the last kaleo action in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo action, or <code>null</code> if a matching kaleo action could not be found
	 */
	@Override
	public KaleoAction fetchByCompanyId_Last(long companyId,
		OrderByComparator<KaleoAction> orderByComparator) {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<KaleoAction> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo actions before and after the current kaleo action in the ordered set where companyId = &#63;.
	 *
	 * @param kaleoActionId the primary key of the current kaleo action
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo action
	 * @throws NoSuchActionException if a kaleo action with the primary key could not be found
	 */
	@Override
	public KaleoAction[] findByCompanyId_PrevAndNext(long kaleoActionId,
		long companyId, OrderByComparator<KaleoAction> orderByComparator)
		throws NoSuchActionException {
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
		OrderByComparator<KaleoAction> orderByComparator, boolean previous) {
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
			query.append(KaleoActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoAction);

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
	 * Removes all the kaleo actions where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (KaleoAction kaleoAction : findByCompanyId(companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoAction);
		}
	}

	/**
	 * Returns the number of kaleo actions where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching kaleo actions
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoAction.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEODEFINITIONID =
		new FinderPath(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionModelImpl.FINDER_CACHE_ENABLED, KaleoActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID =
		new FinderPath(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionModelImpl.FINDER_CACHE_ENABLED, KaleoActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByKaleoDefinitionId", new String[] { Long.class.getName() },
			KaleoActionModelImpl.KALEODEFINITIONID_COLUMN_BITMASK |
			KaleoActionModelImpl.PRIORITY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEODEFINITIONID = new FinderPath(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKaleoDefinitionId", new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo actions where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the matching kaleo actions
	 */
	@Override
	public List<KaleoAction> findByKaleoDefinitionId(long kaleoDefinitionId) {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo actions where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo actions
	 * @param end the upper bound of the range of kaleo actions (not inclusive)
	 * @return the range of matching kaleo actions
	 */
	@Override
	public List<KaleoAction> findByKaleoDefinitionId(long kaleoDefinitionId,
		int start, int end) {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo actions where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo actions
	 * @param end the upper bound of the range of kaleo actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo actions
	 */
	@Override
	public List<KaleoAction> findByKaleoDefinitionId(long kaleoDefinitionId,
		int start, int end, OrderByComparator<KaleoAction> orderByComparator) {
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

		List<KaleoAction> list = (List<KaleoAction>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoAction kaleoAction : list) {
				if ((kaleoDefinitionId != kaleoAction.getKaleoDefinitionId())) {
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

			query.append(_SQL_SELECT_KALEOACTION_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoActionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				if (!pagination) {
					list = (List<KaleoAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first kaleo action in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo action
	 * @throws NoSuchActionException if a matching kaleo action could not be found
	 */
	@Override
	public KaleoAction findByKaleoDefinitionId_First(long kaleoDefinitionId,
		OrderByComparator<KaleoAction> orderByComparator)
		throws NoSuchActionException {
		KaleoAction kaleoAction = fetchByKaleoDefinitionId_First(kaleoDefinitionId,
				orderByComparator);

		if (kaleoAction != null) {
			return kaleoAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoDefinitionId=");
		msg.append(kaleoDefinitionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActionException(msg.toString());
	}

	/**
	 * Returns the first kaleo action in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo action, or <code>null</code> if a matching kaleo action could not be found
	 */
	@Override
	public KaleoAction fetchByKaleoDefinitionId_First(long kaleoDefinitionId,
		OrderByComparator<KaleoAction> orderByComparator) {
		List<KaleoAction> list = findByKaleoDefinitionId(kaleoDefinitionId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo action in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo action
	 * @throws NoSuchActionException if a matching kaleo action could not be found
	 */
	@Override
	public KaleoAction findByKaleoDefinitionId_Last(long kaleoDefinitionId,
		OrderByComparator<KaleoAction> orderByComparator)
		throws NoSuchActionException {
		KaleoAction kaleoAction = fetchByKaleoDefinitionId_Last(kaleoDefinitionId,
				orderByComparator);

		if (kaleoAction != null) {
			return kaleoAction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoDefinitionId=");
		msg.append(kaleoDefinitionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActionException(msg.toString());
	}

	/**
	 * Returns the last kaleo action in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo action, or <code>null</code> if a matching kaleo action could not be found
	 */
	@Override
	public KaleoAction fetchByKaleoDefinitionId_Last(long kaleoDefinitionId,
		OrderByComparator<KaleoAction> orderByComparator) {
		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		if (count == 0) {
			return null;
		}

		List<KaleoAction> list = findByKaleoDefinitionId(kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo actions before and after the current kaleo action in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoActionId the primary key of the current kaleo action
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo action
	 * @throws NoSuchActionException if a kaleo action with the primary key could not be found
	 */
	@Override
	public KaleoAction[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoActionId, long kaleoDefinitionId,
		OrderByComparator<KaleoAction> orderByComparator)
		throws NoSuchActionException {
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
		OrderByComparator<KaleoAction> orderByComparator, boolean previous) {
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
			query.append(KaleoActionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoAction);

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
	 * Removes all the kaleo actions where kaleoDefinitionId = &#63; from the database.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 */
	@Override
	public void removeByKaleoDefinitionId(long kaleoDefinitionId) {
		for (KaleoAction kaleoAction : findByKaleoDefinitionId(
				kaleoDefinitionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoAction);
		}
	}

	/**
	 * Returns the number of kaleo actions where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the number of matching kaleo actions
	 */
	@Override
	public int countByKaleoDefinitionId(long kaleoDefinitionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KALEODEFINITIONID;

		Object[] finderArgs = new Object[] { kaleoDefinitionId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

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
		"kaleoAction.kaleoDefinitionId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KCN_KCPK = new FinderPath(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionModelImpl.FINDER_CACHE_ENABLED, KaleoActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKCN_KCPK",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK =
		new FinderPath(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionModelImpl.FINDER_CACHE_ENABLED, KaleoActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByKCN_KCPK",
			new String[] { String.class.getName(), Long.class.getName() },
			KaleoActionModelImpl.KALEOCLASSNAME_COLUMN_BITMASK |
			KaleoActionModelImpl.KALEOCLASSPK_COLUMN_BITMASK |
			KaleoActionModelImpl.PRIORITY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KCN_KCPK = new FinderPath(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKCN_KCPK",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the kaleo actions where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @return the matching kaleo actions
	 */
	@Override
	public List<KaleoAction> findByKCN_KCPK(String kaleoClassName,
		long kaleoClassPK) {
		return findByKCN_KCPK(kaleoClassName, kaleoClassPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo actions where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param start the lower bound of the range of kaleo actions
	 * @param end the upper bound of the range of kaleo actions (not inclusive)
	 * @return the range of matching kaleo actions
	 */
	@Override
	public List<KaleoAction> findByKCN_KCPK(String kaleoClassName,
		long kaleoClassPK, int start, int end) {
		return findByKCN_KCPK(kaleoClassName, kaleoClassPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo actions where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param start the lower bound of the range of kaleo actions
	 * @param end the upper bound of the range of kaleo actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo actions
	 */
	@Override
	public List<KaleoAction> findByKCN_KCPK(String kaleoClassName,
		long kaleoClassPK, int start, int end,
		OrderByComparator<KaleoAction> orderByComparator) {
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

		List<KaleoAction> list = (List<KaleoAction>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoAction kaleoAction : list) {
				if (!Validator.equals(kaleoClassName,
							kaleoAction.getKaleoClassName()) ||
						(kaleoClassPK != kaleoAction.getKaleoClassPK())) {
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

			query.append(_SQL_SELECT_KALEOACTION_WHERE);

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
				query.append(KaleoActionModelImpl.ORDER_BY_JPQL);
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
					list = (List<KaleoAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first kaleo action in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo action
	 * @throws NoSuchActionException if a matching kaleo action could not be found
	 */
	@Override
	public KaleoAction findByKCN_KCPK_First(String kaleoClassName,
		long kaleoClassPK, OrderByComparator<KaleoAction> orderByComparator)
		throws NoSuchActionException {
		KaleoAction kaleoAction = fetchByKCN_KCPK_First(kaleoClassName,
				kaleoClassPK, orderByComparator);

		if (kaleoAction != null) {
			return kaleoAction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoClassName=");
		msg.append(kaleoClassName);

		msg.append(", kaleoClassPK=");
		msg.append(kaleoClassPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActionException(msg.toString());
	}

	/**
	 * Returns the first kaleo action in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo action, or <code>null</code> if a matching kaleo action could not be found
	 */
	@Override
	public KaleoAction fetchByKCN_KCPK_First(String kaleoClassName,
		long kaleoClassPK, OrderByComparator<KaleoAction> orderByComparator) {
		List<KaleoAction> list = findByKCN_KCPK(kaleoClassName, kaleoClassPK,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo action in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo action
	 * @throws NoSuchActionException if a matching kaleo action could not be found
	 */
	@Override
	public KaleoAction findByKCN_KCPK_Last(String kaleoClassName,
		long kaleoClassPK, OrderByComparator<KaleoAction> orderByComparator)
		throws NoSuchActionException {
		KaleoAction kaleoAction = fetchByKCN_KCPK_Last(kaleoClassName,
				kaleoClassPK, orderByComparator);

		if (kaleoAction != null) {
			return kaleoAction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoClassName=");
		msg.append(kaleoClassName);

		msg.append(", kaleoClassPK=");
		msg.append(kaleoClassPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchActionException(msg.toString());
	}

	/**
	 * Returns the last kaleo action in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo action, or <code>null</code> if a matching kaleo action could not be found
	 */
	@Override
	public KaleoAction fetchByKCN_KCPK_Last(String kaleoClassName,
		long kaleoClassPK, OrderByComparator<KaleoAction> orderByComparator) {
		int count = countByKCN_KCPK(kaleoClassName, kaleoClassPK);

		if (count == 0) {
			return null;
		}

		List<KaleoAction> list = findByKCN_KCPK(kaleoClassName, kaleoClassPK,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo actions before and after the current kaleo action in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoActionId the primary key of the current kaleo action
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo action
	 * @throws NoSuchActionException if a kaleo action with the primary key could not be found
	 */
	@Override
	public KaleoAction[] findByKCN_KCPK_PrevAndNext(long kaleoActionId,
		String kaleoClassName, long kaleoClassPK,
		OrderByComparator<KaleoAction> orderByComparator)
		throws NoSuchActionException {
		KaleoAction kaleoAction = findByPrimaryKey(kaleoActionId);

		Session session = null;

		try {
			session = openSession();

			KaleoAction[] array = new KaleoActionImpl[3];

			array[0] = getByKCN_KCPK_PrevAndNext(session, kaleoAction,
					kaleoClassName, kaleoClassPK, orderByComparator, true);

			array[1] = kaleoAction;

			array[2] = getByKCN_KCPK_PrevAndNext(session, kaleoAction,
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

	protected KaleoAction getByKCN_KCPK_PrevAndNext(Session session,
		KaleoAction kaleoAction, String kaleoClassName, long kaleoClassPK,
		OrderByComparator<KaleoAction> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOACTION_WHERE);

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
			query.append(KaleoActionModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoAction);

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
	 * Removes all the kaleo actions where kaleoClassName = &#63; and kaleoClassPK = &#63; from the database.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 */
	@Override
	public void removeByKCN_KCPK(String kaleoClassName, long kaleoClassPK) {
		for (KaleoAction kaleoAction : findByKCN_KCPK(kaleoClassName,
				kaleoClassPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoAction);
		}
	}

	/**
	 * Returns the number of kaleo actions where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @return the number of matching kaleo actions
	 */
	@Override
	public int countByKCN_KCPK(String kaleoClassName, long kaleoClassPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KCN_KCPK;

		Object[] finderArgs = new Object[] { kaleoClassName, kaleoClassPK };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEOACTION_WHERE);

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

	private static final String _FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_1 = "kaleoAction.kaleoClassName IS NULL AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_2 = "kaleoAction.kaleoClassName = ? AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_3 = "(kaleoAction.kaleoClassName IS NULL OR kaleoAction.kaleoClassName = '') AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_KALEOCLASSPK_2 = "kaleoAction.kaleoClassPK = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KCN_KCPK_ET =
		new FinderPath(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionModelImpl.FINDER_CACHE_ENABLED, KaleoActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKCN_KCPK_ET",
			new String[] {
				String.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_ET =
		new FinderPath(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionModelImpl.FINDER_CACHE_ENABLED, KaleoActionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByKCN_KCPK_ET",
			new String[] {
				String.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			KaleoActionModelImpl.KALEOCLASSNAME_COLUMN_BITMASK |
			KaleoActionModelImpl.KALEOCLASSPK_COLUMN_BITMASK |
			KaleoActionModelImpl.EXECUTIONTYPE_COLUMN_BITMASK |
			KaleoActionModelImpl.PRIORITY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KCN_KCPK_ET = new FinderPath(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKCN_KCPK_ET",
			new String[] {
				String.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the kaleo actions where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @return the matching kaleo actions
	 */
	@Override
	public List<KaleoAction> findByKCN_KCPK_ET(String kaleoClassName,
		long kaleoClassPK, String executionType) {
		return findByKCN_KCPK_ET(kaleoClassName, kaleoClassPK, executionType,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo actions where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @param start the lower bound of the range of kaleo actions
	 * @param end the upper bound of the range of kaleo actions (not inclusive)
	 * @return the range of matching kaleo actions
	 */
	@Override
	public List<KaleoAction> findByKCN_KCPK_ET(String kaleoClassName,
		long kaleoClassPK, String executionType, int start, int end) {
		return findByKCN_KCPK_ET(kaleoClassName, kaleoClassPK, executionType,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo actions where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @param start the lower bound of the range of kaleo actions
	 * @param end the upper bound of the range of kaleo actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo actions
	 */
	@Override
	public List<KaleoAction> findByKCN_KCPK_ET(String kaleoClassName,
		long kaleoClassPK, String executionType, int start, int end,
		OrderByComparator<KaleoAction> orderByComparator) {
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

		List<KaleoAction> list = (List<KaleoAction>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoAction kaleoAction : list) {
				if (!Validator.equals(kaleoClassName,
							kaleoAction.getKaleoClassName()) ||
						(kaleoClassPK != kaleoAction.getKaleoClassPK()) ||
						!Validator.equals(executionType,
							kaleoAction.getExecutionType())) {
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

			query.append(_SQL_SELECT_KALEOACTION_WHERE);

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
				query.append(KaleoActionModelImpl.ORDER_BY_JPQL);
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
					list = (List<KaleoAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoAction>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first kaleo action in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo action
	 * @throws NoSuchActionException if a matching kaleo action could not be found
	 */
	@Override
	public KaleoAction findByKCN_KCPK_ET_First(String kaleoClassName,
		long kaleoClassPK, String executionType,
		OrderByComparator<KaleoAction> orderByComparator)
		throws NoSuchActionException {
		KaleoAction kaleoAction = fetchByKCN_KCPK_ET_First(kaleoClassName,
				kaleoClassPK, executionType, orderByComparator);

		if (kaleoAction != null) {
			return kaleoAction;
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

		throw new NoSuchActionException(msg.toString());
	}

	/**
	 * Returns the first kaleo action in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo action, or <code>null</code> if a matching kaleo action could not be found
	 */
	@Override
	public KaleoAction fetchByKCN_KCPK_ET_First(String kaleoClassName,
		long kaleoClassPK, String executionType,
		OrderByComparator<KaleoAction> orderByComparator) {
		List<KaleoAction> list = findByKCN_KCPK_ET(kaleoClassName,
				kaleoClassPK, executionType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo action in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo action
	 * @throws NoSuchActionException if a matching kaleo action could not be found
	 */
	@Override
	public KaleoAction findByKCN_KCPK_ET_Last(String kaleoClassName,
		long kaleoClassPK, String executionType,
		OrderByComparator<KaleoAction> orderByComparator)
		throws NoSuchActionException {
		KaleoAction kaleoAction = fetchByKCN_KCPK_ET_Last(kaleoClassName,
				kaleoClassPK, executionType, orderByComparator);

		if (kaleoAction != null) {
			return kaleoAction;
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

		throw new NoSuchActionException(msg.toString());
	}

	/**
	 * Returns the last kaleo action in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo action, or <code>null</code> if a matching kaleo action could not be found
	 */
	@Override
	public KaleoAction fetchByKCN_KCPK_ET_Last(String kaleoClassName,
		long kaleoClassPK, String executionType,
		OrderByComparator<KaleoAction> orderByComparator) {
		int count = countByKCN_KCPK_ET(kaleoClassName, kaleoClassPK,
				executionType);

		if (count == 0) {
			return null;
		}

		List<KaleoAction> list = findByKCN_KCPK_ET(kaleoClassName,
				kaleoClassPK, executionType, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo actions before and after the current kaleo action in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * @param kaleoActionId the primary key of the current kaleo action
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo action
	 * @throws NoSuchActionException if a kaleo action with the primary key could not be found
	 */
	@Override
	public KaleoAction[] findByKCN_KCPK_ET_PrevAndNext(long kaleoActionId,
		String kaleoClassName, long kaleoClassPK, String executionType,
		OrderByComparator<KaleoAction> orderByComparator)
		throws NoSuchActionException {
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
		String executionType, OrderByComparator<KaleoAction> orderByComparator,
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
			query.append(KaleoActionModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoAction);

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
	 * Removes all the kaleo actions where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63; from the database.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 */
	@Override
	public void removeByKCN_KCPK_ET(String kaleoClassName, long kaleoClassPK,
		String executionType) {
		for (KaleoAction kaleoAction : findByKCN_KCPK_ET(kaleoClassName,
				kaleoClassPK, executionType, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(kaleoAction);
		}
	}

	/**
	 * Returns the number of kaleo actions where kaleoClassName = &#63; and kaleoClassPK = &#63; and executionType = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param executionType the execution type
	 * @return the number of matching kaleo actions
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

			query.append(_SQL_COUNT_KALEOACTION_WHERE);

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

	private static final String _FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_1 = "kaleoAction.kaleoClassName IS NULL AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_2 = "kaleoAction.kaleoClassName = ? AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSNAME_3 = "(kaleoAction.kaleoClassName IS NULL OR kaleoAction.kaleoClassName = '') AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_ET_KALEOCLASSPK_2 = "kaleoAction.kaleoClassPK = ? AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_1 = "kaleoAction.executionType IS NULL";
	private static final String _FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_2 = "kaleoAction.executionType = ?";
	private static final String _FINDER_COLUMN_KCN_KCPK_ET_EXECUTIONTYPE_3 = "(kaleoAction.executionType IS NULL OR kaleoAction.executionType = '')";

	public KaleoActionPersistenceImpl() {
		setModelClass(KaleoAction.class);
	}

	/**
	 * Caches the kaleo action in the entity cache if it is enabled.
	 *
	 * @param kaleoAction the kaleo action
	 */
	@Override
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
	@Override
	public void cacheResult(List<KaleoAction> kaleoActions) {
		for (KaleoAction kaleoAction : kaleoActions) {
			if (EntityCacheUtil.getResult(
						KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
						KaleoActionImpl.class, kaleoAction.getPrimaryKey()) == null) {
				cacheResult(kaleoAction);
			}
			else {
				kaleoAction.resetOriginalValues();
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
		EntityCacheUtil.clearCache(KaleoActionImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
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

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<KaleoAction> kaleoActions) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (KaleoAction kaleoAction : kaleoActions) {
			EntityCacheUtil.removeResult(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
				KaleoActionImpl.class, kaleoAction.getPrimaryKey());
		}
	}

	/**
	 * Creates a new kaleo action with the primary key. Does not add the kaleo action to the database.
	 *
	 * @param kaleoActionId the primary key for the new kaleo action
	 * @return the new kaleo action
	 */
	@Override
	public KaleoAction create(long kaleoActionId) {
		KaleoAction kaleoAction = new KaleoActionImpl();

		kaleoAction.setNew(true);
		kaleoAction.setPrimaryKey(kaleoActionId);

		return kaleoAction;
	}

	/**
	 * Removes the kaleo action with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoActionId the primary key of the kaleo action
	 * @return the kaleo action that was removed
	 * @throws NoSuchActionException if a kaleo action with the primary key could not be found
	 */
	@Override
	public KaleoAction remove(long kaleoActionId) throws NoSuchActionException {
		return remove((Serializable)kaleoActionId);
	}

	/**
	 * Removes the kaleo action with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo action
	 * @return the kaleo action that was removed
	 * @throws NoSuchActionException if a kaleo action with the primary key could not be found
	 */
	@Override
	public KaleoAction remove(Serializable primaryKey)
		throws NoSuchActionException {
		Session session = null;

		try {
			session = openSession();

			KaleoAction kaleoAction = (KaleoAction)session.get(KaleoActionImpl.class,
					primaryKey);

			if (kaleoAction == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchActionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(kaleoAction);
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

	@Override
	protected KaleoAction removeImpl(KaleoAction kaleoAction) {
		kaleoAction = toUnwrappedModel(kaleoAction);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(kaleoAction)) {
				kaleoAction = (KaleoAction)session.get(KaleoActionImpl.class,
						kaleoAction.getPrimaryKeyObj());
			}

			if (kaleoAction != null) {
				session.delete(kaleoAction);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (kaleoAction != null) {
			clearCache(kaleoAction);
		}

		return kaleoAction;
	}

	@Override
	public KaleoAction updateImpl(KaleoAction kaleoAction) {
		kaleoAction = toUnwrappedModel(kaleoAction);

		boolean isNew = kaleoAction.isNew();

		KaleoActionModelImpl kaleoActionModelImpl = (KaleoActionModelImpl)kaleoAction;

		Session session = null;

		try {
			session = openSession();

			if (kaleoAction.isNew()) {
				session.save(kaleoAction);

				kaleoAction.setNew(false);
			}
			else {
				session.merge(kaleoAction);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !KaleoActionModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((kaleoActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoActionModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { kaleoActionModelImpl.getCompanyId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((kaleoActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoActionModelImpl.getOriginalKaleoDefinitionId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);

				args = new Object[] { kaleoActionModelImpl.getKaleoDefinitionId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);
			}

			if ((kaleoActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoActionModelImpl.getOriginalKaleoClassName(),
						kaleoActionModelImpl.getOriginalKaleoClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KCN_KCPK, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK,
					args);

				args = new Object[] {
						kaleoActionModelImpl.getKaleoClassName(),
						kaleoActionModelImpl.getKaleoClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KCN_KCPK, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK,
					args);
			}

			if ((kaleoActionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_ET.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoActionModelImpl.getOriginalKaleoClassName(),
						kaleoActionModelImpl.getOriginalKaleoClassPK(),
						kaleoActionModelImpl.getOriginalExecutionType()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KCN_KCPK_ET,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_ET,
					args);

				args = new Object[] {
						kaleoActionModelImpl.getKaleoClassName(),
						kaleoActionModelImpl.getKaleoClassPK(),
						kaleoActionModelImpl.getExecutionType()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KCN_KCPK_ET,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_ET,
					args);
			}
		}

		EntityCacheUtil.putResult(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoActionImpl.class, kaleoAction.getPrimaryKey(), kaleoAction,
			false);

		kaleoAction.resetOriginalValues();

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
		kaleoActionImpl.setScriptRequiredContexts(kaleoAction.getScriptRequiredContexts());
		kaleoActionImpl.setPriority(kaleoAction.getPriority());

		return kaleoActionImpl;
	}

	/**
	 * Returns the kaleo action with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo action
	 * @return the kaleo action
	 * @throws NoSuchActionException if a kaleo action with the primary key could not be found
	 */
	@Override
	public KaleoAction findByPrimaryKey(Serializable primaryKey)
		throws NoSuchActionException {
		KaleoAction kaleoAction = fetchByPrimaryKey(primaryKey);

		if (kaleoAction == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchActionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return kaleoAction;
	}

	/**
	 * Returns the kaleo action with the primary key or throws a {@link NoSuchActionException} if it could not be found.
	 *
	 * @param kaleoActionId the primary key of the kaleo action
	 * @return the kaleo action
	 * @throws NoSuchActionException if a kaleo action with the primary key could not be found
	 */
	@Override
	public KaleoAction findByPrimaryKey(long kaleoActionId)
		throws NoSuchActionException {
		return findByPrimaryKey((Serializable)kaleoActionId);
	}

	/**
	 * Returns the kaleo action with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo action
	 * @return the kaleo action, or <code>null</code> if a kaleo action with the primary key could not be found
	 */
	@Override
	public KaleoAction fetchByPrimaryKey(Serializable primaryKey) {
		KaleoAction kaleoAction = (KaleoAction)EntityCacheUtil.getResult(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
				KaleoActionImpl.class, primaryKey);

		if (kaleoAction == _nullKaleoAction) {
			return null;
		}

		if (kaleoAction == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoAction = (KaleoAction)session.get(KaleoActionImpl.class,
						primaryKey);

				if (kaleoAction != null) {
					cacheResult(kaleoAction);
				}
				else {
					EntityCacheUtil.putResult(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
						KaleoActionImpl.class, primaryKey, _nullKaleoAction);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
					KaleoActionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return kaleoAction;
	}

	/**
	 * Returns the kaleo action with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoActionId the primary key of the kaleo action
	 * @return the kaleo action, or <code>null</code> if a kaleo action with the primary key could not be found
	 */
	@Override
	public KaleoAction fetchByPrimaryKey(long kaleoActionId) {
		return fetchByPrimaryKey((Serializable)kaleoActionId);
	}

	@Override
	public Map<Serializable, KaleoAction> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, KaleoAction> map = new HashMap<Serializable, KaleoAction>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			KaleoAction kaleoAction = fetchByPrimaryKey(primaryKey);

			if (kaleoAction != null) {
				map.put(primaryKey, kaleoAction);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			KaleoAction kaleoAction = (KaleoAction)EntityCacheUtil.getResult(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
					KaleoActionImpl.class, primaryKey);

			if (kaleoAction == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, kaleoAction);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_KALEOACTION_WHERE_PKS_IN);

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

			for (KaleoAction kaleoAction : (List<KaleoAction>)q.list()) {
				map.put(kaleoAction.getPrimaryKeyObj(), kaleoAction);

				cacheResult(kaleoAction);

				uncachedPrimaryKeys.remove(kaleoAction.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(KaleoActionModelImpl.ENTITY_CACHE_ENABLED,
					KaleoActionImpl.class, primaryKey, _nullKaleoAction);
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
	 * Returns all the kaleo actions.
	 *
	 * @return the kaleo actions
	 */
	@Override
	public List<KaleoAction> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo actions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo actions
	 * @param end the upper bound of the range of kaleo actions (not inclusive)
	 * @return the range of kaleo actions
	 */
	@Override
	public List<KaleoAction> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo actions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo actions
	 * @param end the upper bound of the range of kaleo actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo actions
	 */
	@Override
	public List<KaleoAction> findAll(int start, int end,
		OrderByComparator<KaleoAction> orderByComparator) {
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

		List<KaleoAction> list = (List<KaleoAction>)FinderCacheUtil.getResult(finderPath,
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
				sql = _SQL_SELECT_KALEOACTION;

				if (pagination) {
					sql = sql.concat(KaleoActionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<KaleoAction>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoAction>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the kaleo actions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (KaleoAction kaleoAction : findAll()) {
			remove(kaleoAction);
		}
	}

	/**
	 * Returns the number of kaleo actions.
	 *
	 * @return the number of kaleo actions
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KALEOACTION);

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
	 * Initializes the kaleo action persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(KaleoActionImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_KALEOACTION = "SELECT kaleoAction FROM KaleoAction kaleoAction";
	private static final String _SQL_SELECT_KALEOACTION_WHERE_PKS_IN = "SELECT kaleoAction FROM KaleoAction kaleoAction WHERE kaleoActionId IN (";
	private static final String _SQL_SELECT_KALEOACTION_WHERE = "SELECT kaleoAction FROM KaleoAction kaleoAction WHERE ";
	private static final String _SQL_COUNT_KALEOACTION = "SELECT COUNT(kaleoAction) FROM KaleoAction kaleoAction";
	private static final String _SQL_COUNT_KALEOACTION_WHERE = "SELECT COUNT(kaleoAction) FROM KaleoAction kaleoAction WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoAction.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoAction exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoAction exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(KaleoActionPersistenceImpl.class);
	private static final KaleoAction _nullKaleoAction = new KaleoActionImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<KaleoAction> toCacheModel() {
				return _nullKaleoActionCacheModel;
			}
		};

	private static final CacheModel<KaleoAction> _nullKaleoActionCacheModel = new CacheModel<KaleoAction>() {
			@Override
			public KaleoAction toEntityModel() {
				return _nullKaleoAction;
			}
		};
}