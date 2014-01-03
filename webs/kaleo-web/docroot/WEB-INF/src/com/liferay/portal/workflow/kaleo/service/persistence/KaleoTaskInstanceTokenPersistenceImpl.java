/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the kaleo task instance token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskInstanceTokenPersistence
 * @see KaleoTaskInstanceTokenUtil
 * @generated
 */
public class KaleoTaskInstanceTokenPersistenceImpl extends BasePersistenceImpl<KaleoTaskInstanceToken>
	implements KaleoTaskInstanceTokenPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KaleoTaskInstanceTokenUtil} to access the kaleo task instance token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoTaskInstanceTokenImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			KaleoTaskInstanceTokenModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo task instance tokens where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoTaskInstanceToken> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the kaleo task instance tokens where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo task instance tokens
	 * @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	 * @return the range of matching kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoTaskInstanceToken> findByCompanyId(long companyId,
		int start, int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo task instance tokens where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo task instance tokens
	 * @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoTaskInstanceToken> findByCompanyId(long companyId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
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

		List<KaleoTaskInstanceToken> list = (List<KaleoTaskInstanceToken>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoTaskInstanceToken kaleoTaskInstanceToken : list) {
				if ((companyId != kaleoTaskInstanceToken.getCompanyId())) {
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

			query.append(_SQL_SELECT_KALEOTASKINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoTaskInstanceTokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<KaleoTaskInstanceToken>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<KaleoTaskInstanceToken>(list);
				}
				else {
					list = (List<KaleoTaskInstanceToken>)QueryUtil.list(q,
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
	 * Returns the first kaleo task instance token in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskInstanceTokenException, SystemException {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (kaleoTaskInstanceToken != null) {
			return kaleoTaskInstanceToken;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTaskInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the first kaleo task instance token in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task instance token, or <code>null</code> if a matching kaleo task instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken fetchByCompanyId_First(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<KaleoTaskInstanceToken> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo task instance token in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskInstanceTokenException, SystemException {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (kaleoTaskInstanceToken != null) {
			return kaleoTaskInstanceToken;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTaskInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the last kaleo task instance token in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task instance token, or <code>null</code> if a matching kaleo task instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken fetchByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<KaleoTaskInstanceToken> list = findByCompanyId(companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo task instance tokens before and after the current kaleo task instance token in the ordered set where companyId = &#63;.
	 *
	 * @param kaleoTaskInstanceTokenId the primary key of the current kaleo task instance token
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo task instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a kaleo task instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken[] findByCompanyId_PrevAndNext(
		long kaleoTaskInstanceTokenId, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskInstanceTokenException, SystemException {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = findByPrimaryKey(kaleoTaskInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskInstanceToken[] array = new KaleoTaskInstanceTokenImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session,
					kaleoTaskInstanceToken, companyId, orderByComparator, true);

			array[1] = kaleoTaskInstanceToken;

			array[2] = getByCompanyId_PrevAndNext(session,
					kaleoTaskInstanceToken, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTaskInstanceToken getByCompanyId_PrevAndNext(
		Session session, KaleoTaskInstanceToken kaleoTaskInstanceToken,
		long companyId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASKINSTANCETOKEN_WHERE);

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
			query.append(KaleoTaskInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoTaskInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo task instance tokens where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByCompanyId(long companyId) throws SystemException {
		for (KaleoTaskInstanceToken kaleoTaskInstanceToken : findByCompanyId(
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoTaskInstanceToken);
		}
	}

	/**
	 * Returns the number of kaleo task instance tokens where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByCompanyId(long companyId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTASKINSTANCETOKEN_WHERE);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoTaskInstanceToken.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEODEFINITIONID =
		new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID =
		new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByKaleoDefinitionId", new String[] { Long.class.getName() },
			KaleoTaskInstanceTokenModelImpl.KALEODEFINITIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEODEFINITIONID = new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKaleoDefinitionId", new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo task instance tokens where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the matching kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoTaskInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo task instance tokens where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo task instance tokens
	 * @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	 * @return the range of matching kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoTaskInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo task instance tokens where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo task instance tokens
	 * @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoTaskInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
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

		List<KaleoTaskInstanceToken> list = (List<KaleoTaskInstanceToken>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoTaskInstanceToken kaleoTaskInstanceToken : list) {
				if ((kaleoDefinitionId != kaleoTaskInstanceToken.getKaleoDefinitionId())) {
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

			query.append(_SQL_SELECT_KALEOTASKINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoTaskInstanceTokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				if (!pagination) {
					list = (List<KaleoTaskInstanceToken>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<KaleoTaskInstanceToken>(list);
				}
				else {
					list = (List<KaleoTaskInstanceToken>)QueryUtil.list(q,
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
	 * Returns the first kaleo task instance token in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken findByKaleoDefinitionId_First(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchTaskInstanceTokenException, SystemException {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = fetchByKaleoDefinitionId_First(kaleoDefinitionId,
				orderByComparator);

		if (kaleoTaskInstanceToken != null) {
			return kaleoTaskInstanceToken;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoDefinitionId=");
		msg.append(kaleoDefinitionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTaskInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the first kaleo task instance token in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task instance token, or <code>null</code> if a matching kaleo task instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken fetchByKaleoDefinitionId_First(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws SystemException {
		List<KaleoTaskInstanceToken> list = findByKaleoDefinitionId(kaleoDefinitionId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo task instance token in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken findByKaleoDefinitionId_Last(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchTaskInstanceTokenException, SystemException {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = fetchByKaleoDefinitionId_Last(kaleoDefinitionId,
				orderByComparator);

		if (kaleoTaskInstanceToken != null) {
			return kaleoTaskInstanceToken;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoDefinitionId=");
		msg.append(kaleoDefinitionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTaskInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the last kaleo task instance token in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task instance token, or <code>null</code> if a matching kaleo task instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken fetchByKaleoDefinitionId_Last(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		if (count == 0) {
			return null;
		}

		List<KaleoTaskInstanceToken> list = findByKaleoDefinitionId(kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo task instance tokens before and after the current kaleo task instance token in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoTaskInstanceTokenId the primary key of the current kaleo task instance token
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo task instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a kaleo task instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoTaskInstanceTokenId, long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskInstanceTokenException, SystemException {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = findByPrimaryKey(kaleoTaskInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskInstanceToken[] array = new KaleoTaskInstanceTokenImpl[3];

			array[0] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoTaskInstanceToken, kaleoDefinitionId,
					orderByComparator, true);

			array[1] = kaleoTaskInstanceToken;

			array[2] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoTaskInstanceToken, kaleoDefinitionId,
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

	protected KaleoTaskInstanceToken getByKaleoDefinitionId_PrevAndNext(
		Session session, KaleoTaskInstanceToken kaleoTaskInstanceToken,
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

		query.append(_SQL_SELECT_KALEOTASKINSTANCETOKEN_WHERE);

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
			query.append(KaleoTaskInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoTaskInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo task instance tokens where kaleoDefinitionId = &#63; from the database.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		for (KaleoTaskInstanceToken kaleoTaskInstanceToken : findByKaleoDefinitionId(
				kaleoDefinitionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoTaskInstanceToken);
		}
	}

	/**
	 * Returns the number of kaleo task instance tokens where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the number of matching kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KALEODEFINITIONID;

		Object[] finderArgs = new Object[] { kaleoDefinitionId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTASKINSTANCETOKEN_WHERE);

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
		"kaleoTaskInstanceToken.kaleoDefinitionId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEOINSTANCEID =
		new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKaleoInstanceId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOINSTANCEID =
		new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskInstanceTokenImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByKaleoInstanceId",
			new String[] { Long.class.getName() },
			KaleoTaskInstanceTokenModelImpl.KALEOINSTANCEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEOINSTANCEID = new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKaleoInstanceId", new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo task instance tokens where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @return the matching kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoTaskInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId) throws SystemException {
		return findByKaleoInstanceId(kaleoInstanceId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo task instance tokens where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param start the lower bound of the range of kaleo task instance tokens
	 * @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	 * @return the range of matching kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoTaskInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end) throws SystemException {
		return findByKaleoInstanceId(kaleoInstanceId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo task instance tokens where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param start the lower bound of the range of kaleo task instance tokens
	 * @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoTaskInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
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

		List<KaleoTaskInstanceToken> list = (List<KaleoTaskInstanceToken>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoTaskInstanceToken kaleoTaskInstanceToken : list) {
				if ((kaleoInstanceId != kaleoTaskInstanceToken.getKaleoInstanceId())) {
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

			query.append(_SQL_SELECT_KALEOTASKINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoTaskInstanceTokenModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceId);

				if (!pagination) {
					list = (List<KaleoTaskInstanceToken>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<KaleoTaskInstanceToken>(list);
				}
				else {
					list = (List<KaleoTaskInstanceToken>)QueryUtil.list(q,
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
	 * Returns the first kaleo task instance token in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken findByKaleoInstanceId_First(
		long kaleoInstanceId, OrderByComparator orderByComparator)
		throws NoSuchTaskInstanceTokenException, SystemException {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = fetchByKaleoInstanceId_First(kaleoInstanceId,
				orderByComparator);

		if (kaleoTaskInstanceToken != null) {
			return kaleoTaskInstanceToken;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoInstanceId=");
		msg.append(kaleoInstanceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTaskInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the first kaleo task instance token in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo task instance token, or <code>null</code> if a matching kaleo task instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken fetchByKaleoInstanceId_First(
		long kaleoInstanceId, OrderByComparator orderByComparator)
		throws SystemException {
		List<KaleoTaskInstanceToken> list = findByKaleoInstanceId(kaleoInstanceId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo task instance token in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken findByKaleoInstanceId_Last(
		long kaleoInstanceId, OrderByComparator orderByComparator)
		throws NoSuchTaskInstanceTokenException, SystemException {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = fetchByKaleoInstanceId_Last(kaleoInstanceId,
				orderByComparator);

		if (kaleoTaskInstanceToken != null) {
			return kaleoTaskInstanceToken;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoInstanceId=");
		msg.append(kaleoInstanceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTaskInstanceTokenException(msg.toString());
	}

	/**
	 * Returns the last kaleo task instance token in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo task instance token, or <code>null</code> if a matching kaleo task instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken fetchByKaleoInstanceId_Last(
		long kaleoInstanceId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByKaleoInstanceId(kaleoInstanceId);

		if (count == 0) {
			return null;
		}

		List<KaleoTaskInstanceToken> list = findByKaleoInstanceId(kaleoInstanceId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo task instance tokens before and after the current kaleo task instance token in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoTaskInstanceTokenId the primary key of the current kaleo task instance token
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo task instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a kaleo task instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken[] findByKaleoInstanceId_PrevAndNext(
		long kaleoTaskInstanceTokenId, long kaleoInstanceId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskInstanceTokenException, SystemException {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = findByPrimaryKey(kaleoTaskInstanceTokenId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskInstanceToken[] array = new KaleoTaskInstanceTokenImpl[3];

			array[0] = getByKaleoInstanceId_PrevAndNext(session,
					kaleoTaskInstanceToken, kaleoInstanceId, orderByComparator,
					true);

			array[1] = kaleoTaskInstanceToken;

			array[2] = getByKaleoInstanceId_PrevAndNext(session,
					kaleoTaskInstanceToken, kaleoInstanceId, orderByComparator,
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

	protected KaleoTaskInstanceToken getByKaleoInstanceId_PrevAndNext(
		Session session, KaleoTaskInstanceToken kaleoTaskInstanceToken,
		long kaleoInstanceId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASKINSTANCETOKEN_WHERE);

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
			query.append(KaleoTaskInstanceTokenModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoInstanceId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoTaskInstanceToken);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskInstanceToken> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo task instance tokens where kaleoInstanceId = &#63; from the database.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByKaleoInstanceId(long kaleoInstanceId)
		throws SystemException {
		for (KaleoTaskInstanceToken kaleoTaskInstanceToken : findByKaleoInstanceId(
				kaleoInstanceId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoTaskInstanceToken);
		}
	}

	/**
	 * Returns the number of kaleo task instance tokens where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @return the number of matching kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByKaleoInstanceId(long kaleoInstanceId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KALEOINSTANCEID;

		Object[] finderArgs = new Object[] { kaleoInstanceId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTASKINSTANCETOKEN_WHERE);

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
		"kaleoTaskInstanceToken.kaleoInstanceId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_KII_KTI = new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED,
			KaleoTaskInstanceTokenImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByKII_KTI",
			new String[] { Long.class.getName(), Long.class.getName() },
			KaleoTaskInstanceTokenModelImpl.KALEOINSTANCEID_COLUMN_BITMASK |
			KaleoTaskInstanceTokenModelImpl.KALEOTASKID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KII_KTI = new FinderPath(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKII_KTI",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the kaleo task instance token where kaleoInstanceId = &#63; and kaleoTaskId = &#63; or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException} if it could not be found.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param kaleoTaskId the kaleo task ID
	 * @return the matching kaleo task instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a matching kaleo task instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken findByKII_KTI(long kaleoInstanceId,
		long kaleoTaskId)
		throws NoSuchTaskInstanceTokenException, SystemException {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = fetchByKII_KTI(kaleoInstanceId,
				kaleoTaskId);

		if (kaleoTaskInstanceToken == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceId=");
			msg.append(kaleoInstanceId);

			msg.append(", kaleoTaskId=");
			msg.append(kaleoTaskId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchTaskInstanceTokenException(msg.toString());
		}

		return kaleoTaskInstanceToken;
	}

	/**
	 * Returns the kaleo task instance token where kaleoInstanceId = &#63; and kaleoTaskId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param kaleoTaskId the kaleo task ID
	 * @return the matching kaleo task instance token, or <code>null</code> if a matching kaleo task instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken fetchByKII_KTI(long kaleoInstanceId,
		long kaleoTaskId) throws SystemException {
		return fetchByKII_KTI(kaleoInstanceId, kaleoTaskId, true);
	}

	/**
	 * Returns the kaleo task instance token where kaleoInstanceId = &#63; and kaleoTaskId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param kaleoTaskId the kaleo task ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching kaleo task instance token, or <code>null</code> if a matching kaleo task instance token could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken fetchByKII_KTI(long kaleoInstanceId,
		long kaleoTaskId, boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { kaleoInstanceId, kaleoTaskId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_KII_KTI,
					finderArgs, this);
		}

		if (result instanceof KaleoTaskInstanceToken) {
			KaleoTaskInstanceToken kaleoTaskInstanceToken = (KaleoTaskInstanceToken)result;

			if ((kaleoInstanceId != kaleoTaskInstanceToken.getKaleoInstanceId()) ||
					(kaleoTaskId != kaleoTaskInstanceToken.getKaleoTaskId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_KALEOTASKINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_KII_KTI_KALEOINSTANCEID_2);

			query.append(_FINDER_COLUMN_KII_KTI_KALEOTASKID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceId);

				qPos.add(kaleoTaskId);

				List<KaleoTaskInstanceToken> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KII_KTI,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"KaleoTaskInstanceTokenPersistenceImpl.fetchByKII_KTI(long, long, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					KaleoTaskInstanceToken kaleoTaskInstanceToken = list.get(0);

					result = kaleoTaskInstanceToken;

					cacheResult(kaleoTaskInstanceToken);

					if ((kaleoTaskInstanceToken.getKaleoInstanceId() != kaleoInstanceId) ||
							(kaleoTaskInstanceToken.getKaleoTaskId() != kaleoTaskId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KII_KTI,
							finderArgs, kaleoTaskInstanceToken);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KII_KTI,
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
			return (KaleoTaskInstanceToken)result;
		}
	}

	/**
	 * Removes the kaleo task instance token where kaleoInstanceId = &#63; and kaleoTaskId = &#63; from the database.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param kaleoTaskId the kaleo task ID
	 * @return the kaleo task instance token that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken removeByKII_KTI(long kaleoInstanceId,
		long kaleoTaskId)
		throws NoSuchTaskInstanceTokenException, SystemException {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = findByKII_KTI(kaleoInstanceId,
				kaleoTaskId);

		return remove(kaleoTaskInstanceToken);
	}

	/**
	 * Returns the number of kaleo task instance tokens where kaleoInstanceId = &#63; and kaleoTaskId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance ID
	 * @param kaleoTaskId the kaleo task ID
	 * @return the number of matching kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByKII_KTI(long kaleoInstanceId, long kaleoTaskId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KII_KTI;

		Object[] finderArgs = new Object[] { kaleoInstanceId, kaleoTaskId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEOTASKINSTANCETOKEN_WHERE);

			query.append(_FINDER_COLUMN_KII_KTI_KALEOINSTANCEID_2);

			query.append(_FINDER_COLUMN_KII_KTI_KALEOTASKID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceId);

				qPos.add(kaleoTaskId);

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

	private static final String _FINDER_COLUMN_KII_KTI_KALEOINSTANCEID_2 = "kaleoTaskInstanceToken.kaleoInstanceId = ? AND ";
	private static final String _FINDER_COLUMN_KII_KTI_KALEOTASKID_2 = "kaleoTaskInstanceToken.kaleoTaskId = ?";

	public KaleoTaskInstanceTokenPersistenceImpl() {
		setModelClass(KaleoTaskInstanceToken.class);
	}

	/**
	 * Caches the kaleo task instance token in the entity cache if it is enabled.
	 *
	 * @param kaleoTaskInstanceToken the kaleo task instance token
	 */
	@Override
	public void cacheResult(KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		EntityCacheUtil.putResult(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenImpl.class,
			kaleoTaskInstanceToken.getPrimaryKey(), kaleoTaskInstanceToken);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KII_KTI,
			new Object[] {
				kaleoTaskInstanceToken.getKaleoInstanceId(),
				kaleoTaskInstanceToken.getKaleoTaskId()
			}, kaleoTaskInstanceToken);

		kaleoTaskInstanceToken.resetOriginalValues();
	}

	/**
	 * Caches the kaleo task instance tokens in the entity cache if it is enabled.
	 *
	 * @param kaleoTaskInstanceTokens the kaleo task instance tokens
	 */
	@Override
	public void cacheResult(
		List<KaleoTaskInstanceToken> kaleoTaskInstanceTokens) {
		for (KaleoTaskInstanceToken kaleoTaskInstanceToken : kaleoTaskInstanceTokens) {
			if (EntityCacheUtil.getResult(
						KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTaskInstanceTokenImpl.class,
						kaleoTaskInstanceToken.getPrimaryKey()) == null) {
				cacheResult(kaleoTaskInstanceToken);
			}
			else {
				kaleoTaskInstanceToken.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all kaleo task instance tokens.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(KaleoTaskInstanceTokenImpl.class.getName());
		}

		EntityCacheUtil.clearCache(KaleoTaskInstanceTokenImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the kaleo task instance token.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		EntityCacheUtil.removeResult(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenImpl.class,
			kaleoTaskInstanceToken.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(kaleoTaskInstanceToken);
	}

	@Override
	public void clearCache(List<KaleoTaskInstanceToken> kaleoTaskInstanceTokens) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (KaleoTaskInstanceToken kaleoTaskInstanceToken : kaleoTaskInstanceTokens) {
			EntityCacheUtil.removeResult(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
				KaleoTaskInstanceTokenImpl.class,
				kaleoTaskInstanceToken.getPrimaryKey());

			clearUniqueFindersCache(kaleoTaskInstanceToken);
		}
	}

	protected void cacheUniqueFindersCache(
		KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		if (kaleoTaskInstanceToken.isNew()) {
			Object[] args = new Object[] {
					kaleoTaskInstanceToken.getKaleoInstanceId(),
					kaleoTaskInstanceToken.getKaleoTaskId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KII_KTI, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KII_KTI, args,
				kaleoTaskInstanceToken);
		}
		else {
			KaleoTaskInstanceTokenModelImpl kaleoTaskInstanceTokenModelImpl = (KaleoTaskInstanceTokenModelImpl)kaleoTaskInstanceToken;

			if ((kaleoTaskInstanceTokenModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_KII_KTI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoTaskInstanceToken.getKaleoInstanceId(),
						kaleoTaskInstanceToken.getKaleoTaskId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KII_KTI, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KII_KTI, args,
					kaleoTaskInstanceToken);
			}
		}
	}

	protected void clearUniqueFindersCache(
		KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		KaleoTaskInstanceTokenModelImpl kaleoTaskInstanceTokenModelImpl = (KaleoTaskInstanceTokenModelImpl)kaleoTaskInstanceToken;

		Object[] args = new Object[] {
				kaleoTaskInstanceToken.getKaleoInstanceId(),
				kaleoTaskInstanceToken.getKaleoTaskId()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KII_KTI, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KII_KTI, args);

		if ((kaleoTaskInstanceTokenModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_KII_KTI.getColumnBitmask()) != 0) {
			args = new Object[] {
					kaleoTaskInstanceTokenModelImpl.getOriginalKaleoInstanceId(),
					kaleoTaskInstanceTokenModelImpl.getOriginalKaleoTaskId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KII_KTI, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KII_KTI, args);
		}
	}

	/**
	 * Creates a new kaleo task instance token with the primary key. Does not add the kaleo task instance token to the database.
	 *
	 * @param kaleoTaskInstanceTokenId the primary key for the new kaleo task instance token
	 * @return the new kaleo task instance token
	 */
	@Override
	public KaleoTaskInstanceToken create(long kaleoTaskInstanceTokenId) {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = new KaleoTaskInstanceTokenImpl();

		kaleoTaskInstanceToken.setNew(true);
		kaleoTaskInstanceToken.setPrimaryKey(kaleoTaskInstanceTokenId);

		return kaleoTaskInstanceToken;
	}

	/**
	 * Removes the kaleo task instance token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTaskInstanceTokenId the primary key of the kaleo task instance token
	 * @return the kaleo task instance token that was removed
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a kaleo task instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken remove(long kaleoTaskInstanceTokenId)
		throws NoSuchTaskInstanceTokenException, SystemException {
		return remove((Serializable)kaleoTaskInstanceTokenId);
	}

	/**
	 * Removes the kaleo task instance token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo task instance token
	 * @return the kaleo task instance token that was removed
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a kaleo task instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken remove(Serializable primaryKey)
		throws NoSuchTaskInstanceTokenException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoTaskInstanceToken kaleoTaskInstanceToken = (KaleoTaskInstanceToken)session.get(KaleoTaskInstanceTokenImpl.class,
					primaryKey);

			if (kaleoTaskInstanceToken == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTaskInstanceTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(kaleoTaskInstanceToken);
		}
		catch (NoSuchTaskInstanceTokenException nsee) {
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
	protected KaleoTaskInstanceToken removeImpl(
		KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws SystemException {
		kaleoTaskInstanceToken = toUnwrappedModel(kaleoTaskInstanceToken);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(kaleoTaskInstanceToken)) {
				kaleoTaskInstanceToken = (KaleoTaskInstanceToken)session.get(KaleoTaskInstanceTokenImpl.class,
						kaleoTaskInstanceToken.getPrimaryKeyObj());
			}

			if (kaleoTaskInstanceToken != null) {
				session.delete(kaleoTaskInstanceToken);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (kaleoTaskInstanceToken != null) {
			clearCache(kaleoTaskInstanceToken);
		}

		return kaleoTaskInstanceToken;
	}

	@Override
	public KaleoTaskInstanceToken updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws SystemException {
		kaleoTaskInstanceToken = toUnwrappedModel(kaleoTaskInstanceToken);

		boolean isNew = kaleoTaskInstanceToken.isNew();

		KaleoTaskInstanceTokenModelImpl kaleoTaskInstanceTokenModelImpl = (KaleoTaskInstanceTokenModelImpl)kaleoTaskInstanceToken;

		Session session = null;

		try {
			session = openSession();

			if (kaleoTaskInstanceToken.isNew()) {
				session.save(kaleoTaskInstanceToken);

				kaleoTaskInstanceToken.setNew(false);
			}
			else {
				session.merge(kaleoTaskInstanceToken);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !KaleoTaskInstanceTokenModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((kaleoTaskInstanceTokenModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoTaskInstanceTokenModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] {
						kaleoTaskInstanceTokenModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((kaleoTaskInstanceTokenModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoTaskInstanceTokenModelImpl.getOriginalKaleoDefinitionId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);

				args = new Object[] {
						kaleoTaskInstanceTokenModelImpl.getKaleoDefinitionId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);
			}

			if ((kaleoTaskInstanceTokenModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOINSTANCEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoTaskInstanceTokenModelImpl.getOriginalKaleoInstanceId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEOINSTANCEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOINSTANCEID,
					args);

				args = new Object[] {
						kaleoTaskInstanceTokenModelImpl.getKaleoInstanceId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEOINSTANCEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOINSTANCEID,
					args);
			}
		}

		EntityCacheUtil.putResult(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskInstanceTokenImpl.class,
			kaleoTaskInstanceToken.getPrimaryKey(), kaleoTaskInstanceToken);

		clearUniqueFindersCache(kaleoTaskInstanceToken);
		cacheUniqueFindersCache(kaleoTaskInstanceToken);

		kaleoTaskInstanceToken.resetOriginalValues();

		return kaleoTaskInstanceToken;
	}

	protected KaleoTaskInstanceToken toUnwrappedModel(
		KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		if (kaleoTaskInstanceToken instanceof KaleoTaskInstanceTokenImpl) {
			return kaleoTaskInstanceToken;
		}

		KaleoTaskInstanceTokenImpl kaleoTaskInstanceTokenImpl = new KaleoTaskInstanceTokenImpl();

		kaleoTaskInstanceTokenImpl.setNew(kaleoTaskInstanceToken.isNew());
		kaleoTaskInstanceTokenImpl.setPrimaryKey(kaleoTaskInstanceToken.getPrimaryKey());

		kaleoTaskInstanceTokenImpl.setKaleoTaskInstanceTokenId(kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId());
		kaleoTaskInstanceTokenImpl.setGroupId(kaleoTaskInstanceToken.getGroupId());
		kaleoTaskInstanceTokenImpl.setCompanyId(kaleoTaskInstanceToken.getCompanyId());
		kaleoTaskInstanceTokenImpl.setUserId(kaleoTaskInstanceToken.getUserId());
		kaleoTaskInstanceTokenImpl.setUserName(kaleoTaskInstanceToken.getUserName());
		kaleoTaskInstanceTokenImpl.setCreateDate(kaleoTaskInstanceToken.getCreateDate());
		kaleoTaskInstanceTokenImpl.setModifiedDate(kaleoTaskInstanceToken.getModifiedDate());
		kaleoTaskInstanceTokenImpl.setKaleoDefinitionId(kaleoTaskInstanceToken.getKaleoDefinitionId());
		kaleoTaskInstanceTokenImpl.setKaleoInstanceId(kaleoTaskInstanceToken.getKaleoInstanceId());
		kaleoTaskInstanceTokenImpl.setKaleoInstanceTokenId(kaleoTaskInstanceToken.getKaleoInstanceTokenId());
		kaleoTaskInstanceTokenImpl.setKaleoTaskId(kaleoTaskInstanceToken.getKaleoTaskId());
		kaleoTaskInstanceTokenImpl.setKaleoTaskName(kaleoTaskInstanceToken.getKaleoTaskName());
		kaleoTaskInstanceTokenImpl.setClassName(kaleoTaskInstanceToken.getClassName());
		kaleoTaskInstanceTokenImpl.setClassPK(kaleoTaskInstanceToken.getClassPK());
		kaleoTaskInstanceTokenImpl.setCompletionUserId(kaleoTaskInstanceToken.getCompletionUserId());
		kaleoTaskInstanceTokenImpl.setCompleted(kaleoTaskInstanceToken.isCompleted());
		kaleoTaskInstanceTokenImpl.setCompletionDate(kaleoTaskInstanceToken.getCompletionDate());
		kaleoTaskInstanceTokenImpl.setDueDate(kaleoTaskInstanceToken.getDueDate());
		kaleoTaskInstanceTokenImpl.setWorkflowContext(kaleoTaskInstanceToken.getWorkflowContext());

		return kaleoTaskInstanceTokenImpl;
	}

	/**
	 * Returns the kaleo task instance token with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo task instance token
	 * @return the kaleo task instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a kaleo task instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTaskInstanceTokenException, SystemException {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = fetchByPrimaryKey(primaryKey);

		if (kaleoTaskInstanceToken == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTaskInstanceTokenException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return kaleoTaskInstanceToken;
	}

	/**
	 * Returns the kaleo task instance token with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException} if it could not be found.
	 *
	 * @param kaleoTaskInstanceTokenId the primary key of the kaleo task instance token
	 * @return the kaleo task instance token
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceTokenException if a kaleo task instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken findByPrimaryKey(
		long kaleoTaskInstanceTokenId)
		throws NoSuchTaskInstanceTokenException, SystemException {
		return findByPrimaryKey((Serializable)kaleoTaskInstanceTokenId);
	}

	/**
	 * Returns the kaleo task instance token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo task instance token
	 * @return the kaleo task instance token, or <code>null</code> if a kaleo task instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		KaleoTaskInstanceToken kaleoTaskInstanceToken = (KaleoTaskInstanceToken)EntityCacheUtil.getResult(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
				KaleoTaskInstanceTokenImpl.class, primaryKey);

		if (kaleoTaskInstanceToken == _nullKaleoTaskInstanceToken) {
			return null;
		}

		if (kaleoTaskInstanceToken == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoTaskInstanceToken = (KaleoTaskInstanceToken)session.get(KaleoTaskInstanceTokenImpl.class,
						primaryKey);

				if (kaleoTaskInstanceToken != null) {
					cacheResult(kaleoTaskInstanceToken);
				}
				else {
					EntityCacheUtil.putResult(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTaskInstanceTokenImpl.class, primaryKey,
						_nullKaleoTaskInstanceToken);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(KaleoTaskInstanceTokenModelImpl.ENTITY_CACHE_ENABLED,
					KaleoTaskInstanceTokenImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return kaleoTaskInstanceToken;
	}

	/**
	 * Returns the kaleo task instance token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoTaskInstanceTokenId the primary key of the kaleo task instance token
	 * @return the kaleo task instance token, or <code>null</code> if a kaleo task instance token with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoTaskInstanceToken fetchByPrimaryKey(
		long kaleoTaskInstanceTokenId) throws SystemException {
		return fetchByPrimaryKey((Serializable)kaleoTaskInstanceTokenId);
	}

	/**
	 * Returns all the kaleo task instance tokens.
	 *
	 * @return the kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoTaskInstanceToken> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo task instance tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo task instance tokens
	 * @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	 * @return the range of kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoTaskInstanceToken> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo task instance tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo task instance tokens
	 * @param end the upper bound of the range of kaleo task instance tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoTaskInstanceToken> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
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

		List<KaleoTaskInstanceToken> list = (List<KaleoTaskInstanceToken>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KALEOTASKINSTANCETOKEN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEOTASKINSTANCETOKEN;

				if (pagination) {
					sql = sql.concat(KaleoTaskInstanceTokenModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<KaleoTaskInstanceToken>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<KaleoTaskInstanceToken>(list);
				}
				else {
					list = (List<KaleoTaskInstanceToken>)QueryUtil.list(q,
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
	 * Removes all the kaleo task instance tokens from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (KaleoTaskInstanceToken kaleoTaskInstanceToken : findAll()) {
			remove(kaleoTaskInstanceToken);
		}
	}

	/**
	 * Returns the number of kaleo task instance tokens.
	 *
	 * @return the number of kaleo task instance tokens
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KALEOTASKINSTANCETOKEN);

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
	 * Initializes the kaleo task instance token persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoTaskInstanceToken>> listenersList = new ArrayList<ModelListener<KaleoTaskInstanceToken>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoTaskInstanceToken>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(KaleoTaskInstanceTokenImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_KALEOTASKINSTANCETOKEN = "SELECT kaleoTaskInstanceToken FROM KaleoTaskInstanceToken kaleoTaskInstanceToken";
	private static final String _SQL_SELECT_KALEOTASKINSTANCETOKEN_WHERE = "SELECT kaleoTaskInstanceToken FROM KaleoTaskInstanceToken kaleoTaskInstanceToken WHERE ";
	private static final String _SQL_COUNT_KALEOTASKINSTANCETOKEN = "SELECT COUNT(kaleoTaskInstanceToken) FROM KaleoTaskInstanceToken kaleoTaskInstanceToken";
	private static final String _SQL_COUNT_KALEOTASKINSTANCETOKEN_WHERE = "SELECT COUNT(kaleoTaskInstanceToken) FROM KaleoTaskInstanceToken kaleoTaskInstanceToken WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoTaskInstanceToken.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoTaskInstanceToken exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoTaskInstanceToken exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(KaleoTaskInstanceTokenPersistenceImpl.class);
	private static KaleoTaskInstanceToken _nullKaleoTaskInstanceToken = new KaleoTaskInstanceTokenImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<KaleoTaskInstanceToken> toCacheModel() {
				return _nullKaleoTaskInstanceTokenCacheModel;
			}
		};

	private static CacheModel<KaleoTaskInstanceToken> _nullKaleoTaskInstanceTokenCacheModel =
		new CacheModel<KaleoTaskInstanceToken>() {
			@Override
			public KaleoTaskInstanceToken toEntityModel() {
				return _nullKaleoTaskInstanceToken;
			}
		};
}