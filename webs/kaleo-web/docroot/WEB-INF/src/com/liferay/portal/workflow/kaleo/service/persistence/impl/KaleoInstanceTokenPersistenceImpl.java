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
import com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenModelImpl;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoInstanceTokenPersistence;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the kaleo instance token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoInstanceTokenPersistence
 * @see com.liferay.portal.workflow.kaleo.service.persistence.KaleoInstanceTokenUtil
 * @generated
 */
@ProviderType
public class KaleoInstanceTokenPersistenceImpl extends BasePersistenceImpl<KaleoInstanceToken>
	implements KaleoInstanceTokenPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KaleoInstanceTokenUtil} to access the kaleo instance token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoInstanceTokenImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			KaleoInstanceTokenModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo instance tokens where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching kaleo instance tokens
	 */
	@Override
	public List<KaleoInstanceToken> findByCompanyId(long companyId) {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the kaleo instance tokens where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo instance tokens
	 * @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	 * @return the range of matching kaleo instance tokens
	 */
	@Override
	public List<KaleoInstanceToken> findByCompanyId(long companyId, int start,
		int end) {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo instance tokens where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo instance tokens
	 * @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo instance tokens
	 */
	@Override
	public List<KaleoInstanceToken> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<KaleoInstanceToken> orderByComparator) {
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

		List<KaleoInstanceToken> list = (List<KaleoInstanceToken>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoInstanceToken kaleoInstanceToken : list) {
				if ((companyId != kaleoInstanceToken.getCompanyId())) {
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

			query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<KaleoInstanceToken>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoInstanceToken>)QueryUtil.list(q,
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
	 * Returns the first kaleo instance token in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance token
	 * @throws NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	 */
	@Override
	public KaleoInstanceToken findByCompanyId_First(long companyId,
		OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws NoSuchInstanceTokenException {
		KaleoInstanceToken kaleoInstanceToken = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (kaleoInstanceToken != null) {
			return kaleoInstanceToken;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the first kaleo instance token in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	 */
	@Override
	public KaleoInstanceToken fetchByCompanyId_First(long companyId,
		OrderByComparator<KaleoInstanceToken> orderByComparator) {
		List<KaleoInstanceToken> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo instance token in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance token
	 * @throws NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	 */
	@Override
	public KaleoInstanceToken findByCompanyId_Last(long companyId,
		OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws NoSuchInstanceTokenException {
		KaleoInstanceToken kaleoInstanceToken = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (kaleoInstanceToken != null) {
			return kaleoInstanceToken;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the last kaleo instance token in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	 */
	@Override
	public KaleoInstanceToken fetchByCompanyId_Last(long companyId,
		OrderByComparator<KaleoInstanceToken> orderByComparator) {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<KaleoInstanceToken> list = findByCompanyId(companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo instance tokens before and after the current kaleo instance token in the ordered set where companyId = &#63;.
	 *
	 * @param kaleoInstanceTokenId the primary key of the current kaleo instance token
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo instance token
	 * @throws NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	 */
	@Override
	public KaleoInstanceToken[] findByCompanyId_PrevAndNext(
		long kaleoInstanceTokenId, long companyId,
		OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws NoSuchInstanceTokenException {
		KaleoInstanceToken kaleoInstanceToken = findByPrimaryKey(kaleoInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoInstanceToken[] array = new KaleoInstanceTokenImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, kaleoInstanceToken,
					companyId, orderByComparator, true);

			array[1] = kaleoInstanceToken;

			array[2] = getByCompanyId_PrevAndNext(session, kaleoInstanceToken,
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

	protected KaleoInstanceToken getByCompanyId_PrevAndNext(Session session,
		KaleoInstanceToken kaleoInstanceToken, long companyId,
		OrderByComparator<KaleoInstanceToken> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

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
			query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo instance tokens where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (KaleoInstanceToken kaleoInstanceToken : findByCompanyId(
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoInstanceToken);
		}
	}

	/**
	 * Returns the number of kaleo instance tokens where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching kaleo instance tokens
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOINSTANCETOKEN_WHERE);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoInstanceToken.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEODEFINITIONID =
		new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID =
		new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByKaleoDefinitionId", new String[] { Long.class.getName() },
			KaleoInstanceTokenModelImpl.KALEODEFINITIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEODEFINITIONID = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKaleoDefinitionId", new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo instance tokens where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the matching kaleo instance tokens
	 */
	@Override
	public List<KaleoInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId) {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo instance tokens where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo instance tokens
	 * @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	 * @return the range of matching kaleo instance tokens
	 */
	@Override
	public List<KaleoInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end) {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo instance tokens where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo instance tokens
	 * @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo instance tokens
	 */
	@Override
	public List<KaleoInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		OrderByComparator<KaleoInstanceToken> orderByComparator) {
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

		List<KaleoInstanceToken> list = (List<KaleoInstanceToken>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoInstanceToken kaleoInstanceToken : list) {
				if ((kaleoDefinitionId != kaleoInstanceToken.getKaleoDefinitionId())) {
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

			query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				if (!pagination) {
					list = (List<KaleoInstanceToken>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoInstanceToken>)QueryUtil.list(q,
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
	 * Returns the first kaleo instance token in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance token
	 * @throws NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	 */
	@Override
	public KaleoInstanceToken findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws NoSuchInstanceTokenException {
		KaleoInstanceToken kaleoInstanceToken = fetchByKaleoDefinitionId_First(kaleoDefinitionId,
				orderByComparator);

		if (kaleoInstanceToken != null) {
			return kaleoInstanceToken;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoDefinitionId=");
		msg.append(kaleoDefinitionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the first kaleo instance token in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	 */
	@Override
	public KaleoInstanceToken fetchByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		OrderByComparator<KaleoInstanceToken> orderByComparator) {
		List<KaleoInstanceToken> list = findByKaleoDefinitionId(kaleoDefinitionId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo instance token in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance token
	 * @throws NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	 */
	@Override
	public KaleoInstanceToken findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws NoSuchInstanceTokenException {
		KaleoInstanceToken kaleoInstanceToken = fetchByKaleoDefinitionId_Last(kaleoDefinitionId,
				orderByComparator);

		if (kaleoInstanceToken != null) {
			return kaleoInstanceToken;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoDefinitionId=");
		msg.append(kaleoDefinitionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the last kaleo instance token in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	 */
	@Override
	public KaleoInstanceToken fetchByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		OrderByComparator<KaleoInstanceToken> orderByComparator) {
		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		if (count == 0) {
			return null;
		}

		List<KaleoInstanceToken> list = findByKaleoDefinitionId(kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo instance tokens before and after the current kaleo instance token in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoInstanceTokenId the primary key of the current kaleo instance token
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo instance token
	 * @throws NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	 */
	@Override
	public KaleoInstanceToken[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoInstanceTokenId, long kaleoDefinitionId,
		OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws NoSuchInstanceTokenException {
		KaleoInstanceToken kaleoInstanceToken = findByPrimaryKey(kaleoInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoInstanceToken[] array = new KaleoInstanceTokenImpl[3];

			array[0] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoInstanceToken, kaleoDefinitionId, orderByComparator,
					true);

			array[1] = kaleoInstanceToken;

			array[2] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoInstanceToken, kaleoDefinitionId, orderByComparator,
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

	protected KaleoInstanceToken getByKaleoDefinitionId_PrevAndNext(
		Session session, KaleoInstanceToken kaleoInstanceToken,
		long kaleoDefinitionId,
		OrderByComparator<KaleoInstanceToken> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

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
			query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo instance tokens where kaleoDefinitionId = &#63; from the database.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 */
	@Override
	public void removeByKaleoDefinitionId(long kaleoDefinitionId) {
		for (KaleoInstanceToken kaleoInstanceToken : findByKaleoDefinitionId(
				kaleoDefinitionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoInstanceToken);
		}
	}

	/**
	 * Returns the number of kaleo instance tokens where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the number of matching kaleo instance tokens
	 */
	@Override
	public int countByKaleoDefinitionId(long kaleoDefinitionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KALEODEFINITIONID;

		Object[] finderArgs = new Object[] { kaleoDefinitionId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOINSTANCETOKEN_WHERE);

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
		"kaleoInstanceToken.kaleoDefinitionId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEOINSTANCEID =
		new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKaleoInstanceId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOINSTANCEID =
		new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByKaleoInstanceId",
			new String[] { Long.class.getName() },
			KaleoInstanceTokenModelImpl.KALEOINSTANCEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEOINSTANCEID = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKaleoInstanceId", new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo instance tokens where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @return the matching kaleo instance tokens
	 */
	@Override
	public List<KaleoInstanceToken> findByKaleoInstanceId(long kaleoInstanceId) {
		return findByKaleoInstanceId(kaleoInstanceId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo instance tokens where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param start the lower bound of the range of kaleo instance tokens
	 * @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	 * @return the range of matching kaleo instance tokens
	 */
	@Override
	public List<KaleoInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end) {
		return findByKaleoInstanceId(kaleoInstanceId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo instance tokens where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param start the lower bound of the range of kaleo instance tokens
	 * @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo instance tokens
	 */
	@Override
	public List<KaleoInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end,
		OrderByComparator<KaleoInstanceToken> orderByComparator) {
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

		List<KaleoInstanceToken> list = (List<KaleoInstanceToken>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoInstanceToken kaleoInstanceToken : list) {
				if ((kaleoInstanceId != kaleoInstanceToken.getKaleoInstanceId())) {
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

			query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceId);

				if (!pagination) {
					list = (List<KaleoInstanceToken>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoInstanceToken>)QueryUtil.list(q,
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
	 * Returns the first kaleo instance token in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance token
	 * @throws NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	 */
	@Override
	public KaleoInstanceToken findByKaleoInstanceId_First(
		long kaleoInstanceId,
		OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws NoSuchInstanceTokenException {
		KaleoInstanceToken kaleoInstanceToken = fetchByKaleoInstanceId_First(kaleoInstanceId,
				orderByComparator);

		if (kaleoInstanceToken != null) {
			return kaleoInstanceToken;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoInstanceId=");
		msg.append(kaleoInstanceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the first kaleo instance token in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	 */
	@Override
	public KaleoInstanceToken fetchByKaleoInstanceId_First(
		long kaleoInstanceId,
		OrderByComparator<KaleoInstanceToken> orderByComparator) {
		List<KaleoInstanceToken> list = findByKaleoInstanceId(kaleoInstanceId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo instance token in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance token
	 * @throws NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	 */
	@Override
	public KaleoInstanceToken findByKaleoInstanceId_Last(long kaleoInstanceId,
		OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws NoSuchInstanceTokenException {
		KaleoInstanceToken kaleoInstanceToken = fetchByKaleoInstanceId_Last(kaleoInstanceId,
				orderByComparator);

		if (kaleoInstanceToken != null) {
			return kaleoInstanceToken;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoInstanceId=");
		msg.append(kaleoInstanceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the last kaleo instance token in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	 */
	@Override
	public KaleoInstanceToken fetchByKaleoInstanceId_Last(
		long kaleoInstanceId,
		OrderByComparator<KaleoInstanceToken> orderByComparator) {
		int count = countByKaleoInstanceId(kaleoInstanceId);

		if (count == 0) {
			return null;
		}

		List<KaleoInstanceToken> list = findByKaleoInstanceId(kaleoInstanceId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo instance tokens before and after the current kaleo instance token in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceTokenId the primary key of the current kaleo instance token
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo instance token
	 * @throws NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	 */
	@Override
	public KaleoInstanceToken[] findByKaleoInstanceId_PrevAndNext(
		long kaleoInstanceTokenId, long kaleoInstanceId,
		OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws NoSuchInstanceTokenException {
		KaleoInstanceToken kaleoInstanceToken = findByPrimaryKey(kaleoInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoInstanceToken[] array = new KaleoInstanceTokenImpl[3];

			array[0] = getByKaleoInstanceId_PrevAndNext(session,
					kaleoInstanceToken, kaleoInstanceId, orderByComparator, true);

			array[1] = kaleoInstanceToken;

			array[2] = getByKaleoInstanceId_PrevAndNext(session,
					kaleoInstanceToken, kaleoInstanceId, orderByComparator,
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

	protected KaleoInstanceToken getByKaleoInstanceId_PrevAndNext(
		Session session, KaleoInstanceToken kaleoInstanceToken,
		long kaleoInstanceId,
		OrderByComparator<KaleoInstanceToken> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

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
			query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoInstanceId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo instance tokens where kaleoInstanceId = &#63; from the database.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 */
	@Override
	public void removeByKaleoInstanceId(long kaleoInstanceId) {
		for (KaleoInstanceToken kaleoInstanceToken : findByKaleoInstanceId(
				kaleoInstanceId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoInstanceToken);
		}
	}

	/**
	 * Returns the number of kaleo instance tokens where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @return the number of matching kaleo instance tokens
	 */
	@Override
	public int countByKaleoInstanceId(long kaleoInstanceId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KALEOINSTANCEID;

		Object[] finderArgs = new Object[] { kaleoInstanceId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOINSTANCETOKEN_WHERE);

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
		"kaleoInstanceToken.kaleoInstanceId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_PKITI = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_PKITI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_PKITI =
		new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_PKITI",
			new String[] { Long.class.getName(), Long.class.getName() },
			KaleoInstanceTokenModelImpl.COMPANYID_COLUMN_BITMASK |
			KaleoInstanceTokenModelImpl.PARENTKALEOINSTANCETOKENID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_PKITI = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_PKITI",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @return the matching kaleo instance tokens
	 */
	@Override
	public List<KaleoInstanceToken> findByC_PKITI(long companyId,
		long parentKaleoInstanceTokenId) {
		return findByC_PKITI(companyId, parentKaleoInstanceTokenId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param start the lower bound of the range of kaleo instance tokens
	 * @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	 * @return the range of matching kaleo instance tokens
	 */
	@Override
	public List<KaleoInstanceToken> findByC_PKITI(long companyId,
		long parentKaleoInstanceTokenId, int start, int end) {
		return findByC_PKITI(companyId, parentKaleoInstanceTokenId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param start the lower bound of the range of kaleo instance tokens
	 * @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo instance tokens
	 */
	@Override
	public List<KaleoInstanceToken> findByC_PKITI(long companyId,
		long parentKaleoInstanceTokenId, int start, int end,
		OrderByComparator<KaleoInstanceToken> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_PKITI;
			finderArgs = new Object[] { companyId, parentKaleoInstanceTokenId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_PKITI;
			finderArgs = new Object[] {
					companyId, parentKaleoInstanceTokenId,
					
					start, end, orderByComparator
				};
		}

		List<KaleoInstanceToken> list = (List<KaleoInstanceToken>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoInstanceToken kaleoInstanceToken : list) {
				if ((companyId != kaleoInstanceToken.getCompanyId()) ||
						(parentKaleoInstanceTokenId != kaleoInstanceToken.getParentKaleoInstanceTokenId())) {
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

			query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_C_PKITI_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_PKITI_PARENTKALEOINSTANCETOKENID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(parentKaleoInstanceTokenId);

				if (!pagination) {
					list = (List<KaleoInstanceToken>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoInstanceToken>)QueryUtil.list(q,
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
	 * Returns the first kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance token
	 * @throws NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	 */
	@Override
	public KaleoInstanceToken findByC_PKITI_First(long companyId,
		long parentKaleoInstanceTokenId,
		OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws NoSuchInstanceTokenException {
		KaleoInstanceToken kaleoInstanceToken = fetchByC_PKITI_First(companyId,
				parentKaleoInstanceTokenId, orderByComparator);

		if (kaleoInstanceToken != null) {
			return kaleoInstanceToken;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", parentKaleoInstanceTokenId=");
		msg.append(parentKaleoInstanceTokenId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the first kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	 */
	@Override
	public KaleoInstanceToken fetchByC_PKITI_First(long companyId,
		long parentKaleoInstanceTokenId,
		OrderByComparator<KaleoInstanceToken> orderByComparator) {
		List<KaleoInstanceToken> list = findByC_PKITI(companyId,
				parentKaleoInstanceTokenId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance token
	 * @throws NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	 */
	@Override
	public KaleoInstanceToken findByC_PKITI_Last(long companyId,
		long parentKaleoInstanceTokenId,
		OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws NoSuchInstanceTokenException {
		KaleoInstanceToken kaleoInstanceToken = fetchByC_PKITI_Last(companyId,
				parentKaleoInstanceTokenId, orderByComparator);

		if (kaleoInstanceToken != null) {
			return kaleoInstanceToken;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", parentKaleoInstanceTokenId=");
		msg.append(parentKaleoInstanceTokenId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the last kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	 */
	@Override
	public KaleoInstanceToken fetchByC_PKITI_Last(long companyId,
		long parentKaleoInstanceTokenId,
		OrderByComparator<KaleoInstanceToken> orderByComparator) {
		int count = countByC_PKITI(companyId, parentKaleoInstanceTokenId);

		if (count == 0) {
			return null;
		}

		List<KaleoInstanceToken> list = findByC_PKITI(companyId,
				parentKaleoInstanceTokenId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo instance tokens before and after the current kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	 *
	 * @param kaleoInstanceTokenId the primary key of the current kaleo instance token
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo instance token
	 * @throws NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	 */
	@Override
	public KaleoInstanceToken[] findByC_PKITI_PrevAndNext(
		long kaleoInstanceTokenId, long companyId,
		long parentKaleoInstanceTokenId,
		OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws NoSuchInstanceTokenException {
		KaleoInstanceToken kaleoInstanceToken = findByPrimaryKey(kaleoInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoInstanceToken[] array = new KaleoInstanceTokenImpl[3];

			array[0] = getByC_PKITI_PrevAndNext(session, kaleoInstanceToken,
					companyId, parentKaleoInstanceTokenId, orderByComparator,
					true);

			array[1] = kaleoInstanceToken;

			array[2] = getByC_PKITI_PrevAndNext(session, kaleoInstanceToken,
					companyId, parentKaleoInstanceTokenId, orderByComparator,
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

	protected KaleoInstanceToken getByC_PKITI_PrevAndNext(Session session,
		KaleoInstanceToken kaleoInstanceToken, long companyId,
		long parentKaleoInstanceTokenId,
		OrderByComparator<KaleoInstanceToken> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

		query.append(_FINDER_COLUMN_C_PKITI_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_PKITI_PARENTKALEOINSTANCETOKENID_2);

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
			query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(parentKaleoInstanceTokenId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 */
	@Override
	public void removeByC_PKITI(long companyId, long parentKaleoInstanceTokenId) {
		for (KaleoInstanceToken kaleoInstanceToken : findByC_PKITI(companyId,
				parentKaleoInstanceTokenId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(kaleoInstanceToken);
		}
	}

	/**
	 * Returns the number of kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @return the number of matching kaleo instance tokens
	 */
	@Override
	public int countByC_PKITI(long companyId, long parentKaleoInstanceTokenId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_PKITI;

		Object[] finderArgs = new Object[] { companyId, parentKaleoInstanceTokenId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEOINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_C_PKITI_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_PKITI_PARENTKALEOINSTANCETOKENID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(parentKaleoInstanceTokenId);

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

	private static final String _FINDER_COLUMN_C_PKITI_COMPANYID_2 = "kaleoInstanceToken.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_PKITI_PARENTKALEOINSTANCETOKENID_2 =
		"kaleoInstanceToken.parentKaleoInstanceTokenId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_PKITI_CD =
		new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_PKITI_CD",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_PKITI_CD =
		new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_PKITI_CD",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName()
			},
			KaleoInstanceTokenModelImpl.COMPANYID_COLUMN_BITMASK |
			KaleoInstanceTokenModelImpl.PARENTKALEOINSTANCETOKENID_COLUMN_BITMASK |
			KaleoInstanceTokenModelImpl.COMPLETIONDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_PKITI_CD = new FinderPath(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_PKITI_CD",
			new String[] {
				Long.class.getName(), Long.class.getName(), Date.class.getName()
			});

	/**
	 * Returns all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param completionDate the completion date
	 * @return the matching kaleo instance tokens
	 */
	@Override
	public List<KaleoInstanceToken> findByC_PKITI_CD(long companyId,
		long parentKaleoInstanceTokenId, Date completionDate) {
		return findByC_PKITI_CD(companyId, parentKaleoInstanceTokenId,
			completionDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param completionDate the completion date
	 * @param start the lower bound of the range of kaleo instance tokens
	 * @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	 * @return the range of matching kaleo instance tokens
	 */
	@Override
	public List<KaleoInstanceToken> findByC_PKITI_CD(long companyId,
		long parentKaleoInstanceTokenId, Date completionDate, int start, int end) {
		return findByC_PKITI_CD(companyId, parentKaleoInstanceTokenId,
			completionDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param completionDate the completion date
	 * @param start the lower bound of the range of kaleo instance tokens
	 * @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo instance tokens
	 */
	@Override
	public List<KaleoInstanceToken> findByC_PKITI_CD(long companyId,
		long parentKaleoInstanceTokenId, Date completionDate, int start,
		int end, OrderByComparator<KaleoInstanceToken> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_PKITI_CD;
			finderArgs = new Object[] {
					companyId, parentKaleoInstanceTokenId, completionDate
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_PKITI_CD;
			finderArgs = new Object[] {
					companyId, parentKaleoInstanceTokenId, completionDate,
					
					start, end, orderByComparator
				};
		}

		List<KaleoInstanceToken> list = (List<KaleoInstanceToken>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoInstanceToken kaleoInstanceToken : list) {
				if ((companyId != kaleoInstanceToken.getCompanyId()) ||
						(parentKaleoInstanceTokenId != kaleoInstanceToken.getParentKaleoInstanceTokenId()) ||
						!Validator.equals(completionDate,
							kaleoInstanceToken.getCompletionDate())) {
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

			query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_C_PKITI_CD_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_PKITI_CD_PARENTKALEOINSTANCETOKENID_2);

			boolean bindCompletionDate = false;

			if (completionDate == null) {
				query.append(_FINDER_COLUMN_C_PKITI_CD_COMPLETIONDATE_1);
			}
			else {
				bindCompletionDate = true;

				query.append(_FINDER_COLUMN_C_PKITI_CD_COMPLETIONDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(parentKaleoInstanceTokenId);

				if (bindCompletionDate) {
					qPos.add(new Timestamp(completionDate.getTime()));
				}

				if (!pagination) {
					list = (List<KaleoInstanceToken>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoInstanceToken>)QueryUtil.list(q,
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
	 * Returns the first kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param completionDate the completion date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance token
	 * @throws NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	 */
	@Override
	public KaleoInstanceToken findByC_PKITI_CD_First(long companyId,
		long parentKaleoInstanceTokenId, Date completionDate,
		OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws NoSuchInstanceTokenException {
		KaleoInstanceToken kaleoInstanceToken = fetchByC_PKITI_CD_First(companyId,
				parentKaleoInstanceTokenId, completionDate, orderByComparator);

		if (kaleoInstanceToken != null) {
			return kaleoInstanceToken;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", parentKaleoInstanceTokenId=");
		msg.append(parentKaleoInstanceTokenId);

		msg.append(", completionDate=");
		msg.append(completionDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the first kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param completionDate the completion date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	 */
	@Override
	public KaleoInstanceToken fetchByC_PKITI_CD_First(long companyId,
		long parentKaleoInstanceTokenId, Date completionDate,
		OrderByComparator<KaleoInstanceToken> orderByComparator) {
		List<KaleoInstanceToken> list = findByC_PKITI_CD(companyId,
				parentKaleoInstanceTokenId, completionDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param completionDate the completion date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance token
	 * @throws NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	 */
	@Override
	public KaleoInstanceToken findByC_PKITI_CD_Last(long companyId,
		long parentKaleoInstanceTokenId, Date completionDate,
		OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws NoSuchInstanceTokenException {
		KaleoInstanceToken kaleoInstanceToken = fetchByC_PKITI_CD_Last(companyId,
				parentKaleoInstanceTokenId, completionDate, orderByComparator);

		if (kaleoInstanceToken != null) {
			return kaleoInstanceToken;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", parentKaleoInstanceTokenId=");
		msg.append(parentKaleoInstanceTokenId);

		msg.append(", completionDate=");
		msg.append(completionDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the last kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param completionDate the completion date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance token, or <code>null</code> if a matching kaleo instance token could not be found
	 */
	@Override
	public KaleoInstanceToken fetchByC_PKITI_CD_Last(long companyId,
		long parentKaleoInstanceTokenId, Date completionDate,
		OrderByComparator<KaleoInstanceToken> orderByComparator) {
		int count = countByC_PKITI_CD(companyId, parentKaleoInstanceTokenId,
				completionDate);

		if (count == 0) {
			return null;
		}

		List<KaleoInstanceToken> list = findByC_PKITI_CD(companyId,
				parentKaleoInstanceTokenId, completionDate, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo instance tokens before and after the current kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	 *
	 * @param kaleoInstanceTokenId the primary key of the current kaleo instance token
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param completionDate the completion date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo instance token
	 * @throws NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	 */
	@Override
	public KaleoInstanceToken[] findByC_PKITI_CD_PrevAndNext(
		long kaleoInstanceTokenId, long companyId,
		long parentKaleoInstanceTokenId, Date completionDate,
		OrderByComparator<KaleoInstanceToken> orderByComparator)
		throws NoSuchInstanceTokenException {
		KaleoInstanceToken kaleoInstanceToken = findByPrimaryKey(kaleoInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoInstanceToken[] array = new KaleoInstanceTokenImpl[3];

			array[0] = getByC_PKITI_CD_PrevAndNext(session, kaleoInstanceToken,
					companyId, parentKaleoInstanceTokenId, completionDate,
					orderByComparator, true);

			array[1] = kaleoInstanceToken;

			array[2] = getByC_PKITI_CD_PrevAndNext(session, kaleoInstanceToken,
					companyId, parentKaleoInstanceTokenId, completionDate,
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

	protected KaleoInstanceToken getByC_PKITI_CD_PrevAndNext(Session session,
		KaleoInstanceToken kaleoInstanceToken, long companyId,
		long parentKaleoInstanceTokenId, Date completionDate,
		OrderByComparator<KaleoInstanceToken> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE);

		query.append(_FINDER_COLUMN_C_PKITI_CD_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_PKITI_CD_PARENTKALEOINSTANCETOKENID_2);

		boolean bindCompletionDate = false;

		if (completionDate == null) {
			query.append(_FINDER_COLUMN_C_PKITI_CD_COMPLETIONDATE_1);
		}
		else {
			bindCompletionDate = true;

			query.append(_FINDER_COLUMN_C_PKITI_CD_COMPLETIONDATE_2);
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
			query.append(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(parentKaleoInstanceTokenId);

		if (bindCompletionDate) {
			qPos.add(new Timestamp(completionDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param completionDate the completion date
	 */
	@Override
	public void removeByC_PKITI_CD(long companyId,
		long parentKaleoInstanceTokenId, Date completionDate) {
		for (KaleoInstanceToken kaleoInstanceToken : findByC_PKITI_CD(
				companyId, parentKaleoInstanceTokenId, completionDate,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoInstanceToken);
		}
	}

	/**
	 * Returns the number of kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param parentKaleoInstanceTokenId the parent kaleo instance token ID
	 * @param completionDate the completion date
	 * @return the number of matching kaleo instance tokens
	 */
	@Override
	public int countByC_PKITI_CD(long companyId,
		long parentKaleoInstanceTokenId, Date completionDate) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_PKITI_CD;

		Object[] finderArgs = new Object[] {
				companyId, parentKaleoInstanceTokenId, completionDate
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KALEOINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_C_PKITI_CD_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_PKITI_CD_PARENTKALEOINSTANCETOKENID_2);

			boolean bindCompletionDate = false;

			if (completionDate == null) {
				query.append(_FINDER_COLUMN_C_PKITI_CD_COMPLETIONDATE_1);
			}
			else {
				bindCompletionDate = true;

				query.append(_FINDER_COLUMN_C_PKITI_CD_COMPLETIONDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(parentKaleoInstanceTokenId);

				if (bindCompletionDate) {
					qPos.add(new Timestamp(completionDate.getTime()));
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

	private static final String _FINDER_COLUMN_C_PKITI_CD_COMPANYID_2 = "kaleoInstanceToken.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_PKITI_CD_PARENTKALEOINSTANCETOKENID_2 =
		"kaleoInstanceToken.parentKaleoInstanceTokenId = ? AND ";
	private static final String _FINDER_COLUMN_C_PKITI_CD_COMPLETIONDATE_1 = "kaleoInstanceToken.completionDate IS NULL";
	private static final String _FINDER_COLUMN_C_PKITI_CD_COMPLETIONDATE_2 = "kaleoInstanceToken.completionDate = ?";

	public KaleoInstanceTokenPersistenceImpl() {
		setModelClass(KaleoInstanceToken.class);
	}

	/**
	 * Caches the kaleo instance token in the entity cache if it is enabled.
	 *
	 * @param kaleoInstanceToken the kaleo instance token
	 */
	@Override
	public void cacheResult(KaleoInstanceToken kaleoInstanceToken) {
		EntityCacheUtil.putResult(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class, kaleoInstanceToken.getPrimaryKey(),
			kaleoInstanceToken);

		kaleoInstanceToken.resetOriginalValues();
	}

	/**
	 * Caches the kaleo instance tokens in the entity cache if it is enabled.
	 *
	 * @param kaleoInstanceTokens the kaleo instance tokens
	 */
	@Override
	public void cacheResult(List<KaleoInstanceToken> kaleoInstanceTokens) {
		for (KaleoInstanceToken kaleoInstanceToken : kaleoInstanceTokens) {
			if (EntityCacheUtil.getResult(
						KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
						KaleoInstanceTokenImpl.class,
						kaleoInstanceToken.getPrimaryKey()) == null) {
				cacheResult(kaleoInstanceToken);
			}
			else {
				kaleoInstanceToken.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all kaleo instance tokens.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(KaleoInstanceTokenImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the kaleo instance token.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KaleoInstanceToken kaleoInstanceToken) {
		EntityCacheUtil.removeResult(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class, kaleoInstanceToken.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<KaleoInstanceToken> kaleoInstanceTokens) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (KaleoInstanceToken kaleoInstanceToken : kaleoInstanceTokens) {
			EntityCacheUtil.removeResult(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
				KaleoInstanceTokenImpl.class, kaleoInstanceToken.getPrimaryKey());
		}
	}

	/**
	 * Creates a new kaleo instance token with the primary key. Does not add the kaleo instance token to the database.
	 *
	 * @param kaleoInstanceTokenId the primary key for the new kaleo instance token
	 * @return the new kaleo instance token
	 */
	@Override
	public KaleoInstanceToken create(long kaleoInstanceTokenId) {
		KaleoInstanceToken kaleoInstanceToken = new KaleoInstanceTokenImpl();

		kaleoInstanceToken.setNew(true);
		kaleoInstanceToken.setPrimaryKey(kaleoInstanceTokenId);

		return kaleoInstanceToken;
	}

	/**
	 * Removes the kaleo instance token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoInstanceTokenId the primary key of the kaleo instance token
	 * @return the kaleo instance token that was removed
	 * @throws NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	 */
	@Override
	public KaleoInstanceToken remove(long kaleoInstanceTokenId)
		throws NoSuchInstanceTokenException {
		return remove((Serializable)kaleoInstanceTokenId);
	}

	/**
	 * Removes the kaleo instance token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo instance token
	 * @return the kaleo instance token that was removed
	 * @throws NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	 */
	@Override
	public KaleoInstanceToken remove(Serializable primaryKey)
		throws NoSuchInstanceTokenException {
		Session session = null;

		try {
			session = openSession();

			KaleoInstanceToken kaleoInstanceToken = (KaleoInstanceToken)session.get(KaleoInstanceTokenImpl.class,
					primaryKey);

			if (kaleoInstanceToken == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchInstanceTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(kaleoInstanceToken);
		}
		catch (NoSuchInstanceTokenException nsee) {
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
	protected KaleoInstanceToken removeImpl(
		KaleoInstanceToken kaleoInstanceToken) {
		kaleoInstanceToken = toUnwrappedModel(kaleoInstanceToken);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(kaleoInstanceToken)) {
				kaleoInstanceToken = (KaleoInstanceToken)session.get(KaleoInstanceTokenImpl.class,
						kaleoInstanceToken.getPrimaryKeyObj());
			}

			if (kaleoInstanceToken != null) {
				session.delete(kaleoInstanceToken);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (kaleoInstanceToken != null) {
			clearCache(kaleoInstanceToken);
		}

		return kaleoInstanceToken;
	}

	@Override
	public KaleoInstanceToken updateImpl(KaleoInstanceToken kaleoInstanceToken) {
		kaleoInstanceToken = toUnwrappedModel(kaleoInstanceToken);

		boolean isNew = kaleoInstanceToken.isNew();

		KaleoInstanceTokenModelImpl kaleoInstanceTokenModelImpl = (KaleoInstanceTokenModelImpl)kaleoInstanceToken;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (kaleoInstanceToken.getCreateDate() == null)) {
			if (serviceContext == null) {
				kaleoInstanceToken.setCreateDate(now);
			}
			else {
				kaleoInstanceToken.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!kaleoInstanceTokenModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				kaleoInstanceToken.setModifiedDate(now);
			}
			else {
				kaleoInstanceToken.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (kaleoInstanceToken.isNew()) {
				session.save(kaleoInstanceToken);

				kaleoInstanceToken.setNew(false);
			}
			else {
				session.merge(kaleoInstanceToken);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !KaleoInstanceTokenModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((kaleoInstanceTokenModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoInstanceTokenModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { kaleoInstanceTokenModelImpl.getCompanyId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((kaleoInstanceTokenModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoInstanceTokenModelImpl.getOriginalKaleoDefinitionId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);

				args = new Object[] {
						kaleoInstanceTokenModelImpl.getKaleoDefinitionId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);
			}

			if ((kaleoInstanceTokenModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOINSTANCEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoInstanceTokenModelImpl.getOriginalKaleoInstanceId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEOINSTANCEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOINSTANCEID,
					args);

				args = new Object[] {
						kaleoInstanceTokenModelImpl.getKaleoInstanceId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEOINSTANCEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOINSTANCEID,
					args);
			}

			if ((kaleoInstanceTokenModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_PKITI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoInstanceTokenModelImpl.getOriginalCompanyId(),
						kaleoInstanceTokenModelImpl.getOriginalParentKaleoInstanceTokenId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_PKITI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_PKITI,
					args);

				args = new Object[] {
						kaleoInstanceTokenModelImpl.getCompanyId(),
						kaleoInstanceTokenModelImpl.getParentKaleoInstanceTokenId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_PKITI, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_PKITI,
					args);
			}

			if ((kaleoInstanceTokenModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_PKITI_CD.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoInstanceTokenModelImpl.getOriginalCompanyId(),
						kaleoInstanceTokenModelImpl.getOriginalParentKaleoInstanceTokenId(),
						kaleoInstanceTokenModelImpl.getOriginalCompletionDate()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_PKITI_CD,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_PKITI_CD,
					args);

				args = new Object[] {
						kaleoInstanceTokenModelImpl.getCompanyId(),
						kaleoInstanceTokenModelImpl.getParentKaleoInstanceTokenId(),
						kaleoInstanceTokenModelImpl.getCompletionDate()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_PKITI_CD,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_PKITI_CD,
					args);
			}
		}

		EntityCacheUtil.putResult(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceTokenImpl.class, kaleoInstanceToken.getPrimaryKey(),
			kaleoInstanceToken, false);

		kaleoInstanceToken.resetOriginalValues();

		return kaleoInstanceToken;
	}

	protected KaleoInstanceToken toUnwrappedModel(
		KaleoInstanceToken kaleoInstanceToken) {
		if (kaleoInstanceToken instanceof KaleoInstanceTokenImpl) {
			return kaleoInstanceToken;
		}

		KaleoInstanceTokenImpl kaleoInstanceTokenImpl = new KaleoInstanceTokenImpl();

		kaleoInstanceTokenImpl.setNew(kaleoInstanceToken.isNew());
		kaleoInstanceTokenImpl.setPrimaryKey(kaleoInstanceToken.getPrimaryKey());

		kaleoInstanceTokenImpl.setKaleoInstanceTokenId(kaleoInstanceToken.getKaleoInstanceTokenId());
		kaleoInstanceTokenImpl.setGroupId(kaleoInstanceToken.getGroupId());
		kaleoInstanceTokenImpl.setCompanyId(kaleoInstanceToken.getCompanyId());
		kaleoInstanceTokenImpl.setUserId(kaleoInstanceToken.getUserId());
		kaleoInstanceTokenImpl.setUserName(kaleoInstanceToken.getUserName());
		kaleoInstanceTokenImpl.setCreateDate(kaleoInstanceToken.getCreateDate());
		kaleoInstanceTokenImpl.setModifiedDate(kaleoInstanceToken.getModifiedDate());
		kaleoInstanceTokenImpl.setKaleoDefinitionId(kaleoInstanceToken.getKaleoDefinitionId());
		kaleoInstanceTokenImpl.setKaleoInstanceId(kaleoInstanceToken.getKaleoInstanceId());
		kaleoInstanceTokenImpl.setParentKaleoInstanceTokenId(kaleoInstanceToken.getParentKaleoInstanceTokenId());
		kaleoInstanceTokenImpl.setCurrentKaleoNodeId(kaleoInstanceToken.getCurrentKaleoNodeId());
		kaleoInstanceTokenImpl.setCurrentKaleoNodeName(kaleoInstanceToken.getCurrentKaleoNodeName());
		kaleoInstanceTokenImpl.setClassName(kaleoInstanceToken.getClassName());
		kaleoInstanceTokenImpl.setClassPK(kaleoInstanceToken.getClassPK());
		kaleoInstanceTokenImpl.setCompleted(kaleoInstanceToken.isCompleted());
		kaleoInstanceTokenImpl.setCompletionDate(kaleoInstanceToken.getCompletionDate());

		return kaleoInstanceTokenImpl;
	}

	/**
	 * Returns the kaleo instance token with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo instance token
	 * @return the kaleo instance token
	 * @throws NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	 */
	@Override
	public KaleoInstanceToken findByPrimaryKey(Serializable primaryKey)
		throws NoSuchInstanceTokenException {
		KaleoInstanceToken kaleoInstanceToken = fetchByPrimaryKey(primaryKey);

		if (kaleoInstanceToken == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchInstanceTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return kaleoInstanceToken;
	}

	/**
	 * Returns the kaleo instance token with the primary key or throws a {@link NoSuchInstanceTokenException} if it could not be found.
	 *
	 * @param kaleoInstanceTokenId the primary key of the kaleo instance token
	 * @return the kaleo instance token
	 * @throws NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	 */
	@Override
	public KaleoInstanceToken findByPrimaryKey(long kaleoInstanceTokenId)
		throws NoSuchInstanceTokenException {
		return findByPrimaryKey((Serializable)kaleoInstanceTokenId);
	}

	/**
	 * Returns the kaleo instance token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo instance token
	 * @return the kaleo instance token, or <code>null</code> if a kaleo instance token with the primary key could not be found
	 */
	@Override
	public KaleoInstanceToken fetchByPrimaryKey(Serializable primaryKey) {
		KaleoInstanceToken kaleoInstanceToken = (KaleoInstanceToken)EntityCacheUtil.getResult(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
				KaleoInstanceTokenImpl.class, primaryKey);

		if (kaleoInstanceToken == _nullKaleoInstanceToken) {
			return null;
		}

		if (kaleoInstanceToken == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoInstanceToken = (KaleoInstanceToken)session.get(KaleoInstanceTokenImpl.class,
						primaryKey);

				if (kaleoInstanceToken != null) {
					cacheResult(kaleoInstanceToken);
				}
				else {
					EntityCacheUtil.putResult(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
						KaleoInstanceTokenImpl.class, primaryKey,
						_nullKaleoInstanceToken);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
					KaleoInstanceTokenImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return kaleoInstanceToken;
	}

	/**
	 * Returns the kaleo instance token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoInstanceTokenId the primary key of the kaleo instance token
	 * @return the kaleo instance token, or <code>null</code> if a kaleo instance token with the primary key could not be found
	 */
	@Override
	public KaleoInstanceToken fetchByPrimaryKey(long kaleoInstanceTokenId) {
		return fetchByPrimaryKey((Serializable)kaleoInstanceTokenId);
	}

	@Override
	public Map<Serializable, KaleoInstanceToken> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, KaleoInstanceToken> map = new HashMap<Serializable, KaleoInstanceToken>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			KaleoInstanceToken kaleoInstanceToken = fetchByPrimaryKey(primaryKey);

			if (kaleoInstanceToken != null) {
				map.put(primaryKey, kaleoInstanceToken);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			KaleoInstanceToken kaleoInstanceToken = (KaleoInstanceToken)EntityCacheUtil.getResult(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
					KaleoInstanceTokenImpl.class, primaryKey);

			if (kaleoInstanceToken == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, kaleoInstanceToken);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_KALEOINSTANCETOKEN_WHERE_PKS_IN);

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

			for (KaleoInstanceToken kaleoInstanceToken : (List<KaleoInstanceToken>)q.list()) {
				map.put(kaleoInstanceToken.getPrimaryKeyObj(),
					kaleoInstanceToken);

				cacheResult(kaleoInstanceToken);

				uncachedPrimaryKeys.remove(kaleoInstanceToken.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(KaleoInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
					KaleoInstanceTokenImpl.class, primaryKey,
					_nullKaleoInstanceToken);
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
	 * Returns all the kaleo instance tokens.
	 *
	 * @return the kaleo instance tokens
	 */
	@Override
	public List<KaleoInstanceToken> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo instance tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo instance tokens
	 * @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	 * @return the range of kaleo instance tokens
	 */
	@Override
	public List<KaleoInstanceToken> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo instance tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo instance tokens
	 * @param end the upper bound of the range of kaleo instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo instance tokens
	 */
	@Override
	public List<KaleoInstanceToken> findAll(int start, int end,
		OrderByComparator<KaleoInstanceToken> orderByComparator) {
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

		List<KaleoInstanceToken> list = (List<KaleoInstanceToken>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KALEOINSTANCETOKEN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEOINSTANCETOKEN;

				if (pagination) {
					sql = sql.concat(KaleoInstanceTokenModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<KaleoInstanceToken>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoInstanceToken>)QueryUtil.list(q,
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
	 * Removes all the kaleo instance tokens from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (KaleoInstanceToken kaleoInstanceToken : findAll()) {
			remove(kaleoInstanceToken);
		}
	}

	/**
	 * Returns the number of kaleo instance tokens.
	 *
	 * @return the number of kaleo instance tokens
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KALEOINSTANCETOKEN);

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
	 * Initializes the kaleo instance token persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(KaleoInstanceTokenImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_KALEOINSTANCETOKEN = "SELECT kaleoInstanceToken FROM KaleoInstanceToken kaleoInstanceToken";
	private static final String _SQL_SELECT_KALEOINSTANCETOKEN_WHERE_PKS_IN = "SELECT kaleoInstanceToken FROM KaleoInstanceToken kaleoInstanceToken WHERE kaleoInstanceTokenId IN (";
	private static final String _SQL_SELECT_KALEOINSTANCETOKEN_WHERE = "SELECT kaleoInstanceToken FROM KaleoInstanceToken kaleoInstanceToken WHERE ";
	private static final String _SQL_COUNT_KALEOINSTANCETOKEN = "SELECT COUNT(kaleoInstanceToken) FROM KaleoInstanceToken kaleoInstanceToken";
	private static final String _SQL_COUNT_KALEOINSTANCETOKEN_WHERE = "SELECT COUNT(kaleoInstanceToken) FROM KaleoInstanceToken kaleoInstanceToken WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoInstanceToken.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoInstanceToken exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoInstanceToken exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(KaleoInstanceTokenPersistenceImpl.class);
	private static final KaleoInstanceToken _nullKaleoInstanceToken = new KaleoInstanceTokenImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<KaleoInstanceToken> toCacheModel() {
				return _nullKaleoInstanceTokenCacheModel;
			}
		};

	private static final CacheModel<KaleoInstanceToken> _nullKaleoInstanceTokenCacheModel =
		new CacheModel<KaleoInstanceToken>() {
			@Override
			public KaleoInstanceToken toEntityModel() {
				return _nullKaleoInstanceToken;
			}
		};
}