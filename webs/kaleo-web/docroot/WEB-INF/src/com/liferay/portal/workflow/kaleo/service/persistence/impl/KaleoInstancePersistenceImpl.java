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

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.NoSuchInstanceException;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoInstancePersistence;

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
 * The persistence implementation for the kaleo instance service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoInstancePersistence
 * @see com.liferay.portal.workflow.kaleo.service.persistence.KaleoInstanceUtil
 * @generated
 */
@ProviderType
public class KaleoInstancePersistenceImpl extends BasePersistenceImpl<KaleoInstance>
	implements KaleoInstancePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KaleoInstanceUtil} to access the kaleo instance persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoInstanceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCompanyId", new String[] { Long.class.getName() },
			KaleoInstanceModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo instances where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching kaleo instances
	 */
	@Override
	public List<KaleoInstance> findByCompanyId(long companyId) {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the kaleo instances where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo instances
	 * @param end the upper bound of the range of kaleo instances (not inclusive)
	 * @return the range of matching kaleo instances
	 */
	@Override
	public List<KaleoInstance> findByCompanyId(long companyId, int start,
		int end) {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo instances where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo instances
	 * @param end the upper bound of the range of kaleo instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo instances
	 */
	@Override
	public List<KaleoInstance> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<KaleoInstance> orderByComparator) {
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

		List<KaleoInstance> list = (List<KaleoInstance>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoInstance kaleoInstance : list) {
				if ((companyId != kaleoInstance.getCompanyId())) {
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

			query.append(_SQL_SELECT_KALEOINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoInstanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<KaleoInstance>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoInstance>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first kaleo instance in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a matching kaleo instance could not be found
	 */
	@Override
	public KaleoInstance findByCompanyId_First(long companyId,
		OrderByComparator<KaleoInstance> orderByComparator)
		throws NoSuchInstanceException {
		KaleoInstance kaleoInstance = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (kaleoInstance != null) {
			return kaleoInstance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceException(msg.toString());
	}

	/**
	 * Returns the first kaleo instance in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	 */
	@Override
	public KaleoInstance fetchByCompanyId_First(long companyId,
		OrderByComparator<KaleoInstance> orderByComparator) {
		List<KaleoInstance> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo instance in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a matching kaleo instance could not be found
	 */
	@Override
	public KaleoInstance findByCompanyId_Last(long companyId,
		OrderByComparator<KaleoInstance> orderByComparator)
		throws NoSuchInstanceException {
		KaleoInstance kaleoInstance = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (kaleoInstance != null) {
			return kaleoInstance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceException(msg.toString());
	}

	/**
	 * Returns the last kaleo instance in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	 */
	@Override
	public KaleoInstance fetchByCompanyId_Last(long companyId,
		OrderByComparator<KaleoInstance> orderByComparator) {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<KaleoInstance> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo instances before and after the current kaleo instance in the ordered set where companyId = &#63;.
	 *
	 * @param kaleoInstanceId the primary key of the current kaleo instance
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a kaleo instance with the primary key could not be found
	 */
	@Override
	public KaleoInstance[] findByCompanyId_PrevAndNext(long kaleoInstanceId,
		long companyId, OrderByComparator<KaleoInstance> orderByComparator)
		throws NoSuchInstanceException {
		KaleoInstance kaleoInstance = findByPrimaryKey(kaleoInstanceId);

		Session session = null;

		try {
			session = openSession();

			KaleoInstance[] array = new KaleoInstanceImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, kaleoInstance,
					companyId, orderByComparator, true);

			array[1] = kaleoInstance;

			array[2] = getByCompanyId_PrevAndNext(session, kaleoInstance,
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

	protected KaleoInstance getByCompanyId_PrevAndNext(Session session,
		KaleoInstance kaleoInstance, long companyId,
		OrderByComparator<KaleoInstance> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOINSTANCE_WHERE);

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
			query.append(KaleoInstanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoInstance);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo instances where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (KaleoInstance kaleoInstance : findByCompanyId(companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoInstance);
		}
	}

	/**
	 * Returns the number of kaleo instances where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching kaleo instances
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOINSTANCE_WHERE);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoInstance.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEODEFINITIONID =
		new FinderPath(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID =
		new FinderPath(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByKaleoDefinitionId", new String[] { Long.class.getName() },
			KaleoInstanceModelImpl.KALEODEFINITIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEODEFINITIONID = new FinderPath(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKaleoDefinitionId", new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo instances where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the matching kaleo instances
	 */
	@Override
	public List<KaleoInstance> findByKaleoDefinitionId(long kaleoDefinitionId) {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo instances where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo instances
	 * @param end the upper bound of the range of kaleo instances (not inclusive)
	 * @return the range of matching kaleo instances
	 */
	@Override
	public List<KaleoInstance> findByKaleoDefinitionId(long kaleoDefinitionId,
		int start, int end) {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo instances where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param start the lower bound of the range of kaleo instances
	 * @param end the upper bound of the range of kaleo instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo instances
	 */
	@Override
	public List<KaleoInstance> findByKaleoDefinitionId(long kaleoDefinitionId,
		int start, int end, OrderByComparator<KaleoInstance> orderByComparator) {
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

		List<KaleoInstance> list = (List<KaleoInstance>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoInstance kaleoInstance : list) {
				if ((kaleoDefinitionId != kaleoInstance.getKaleoDefinitionId())) {
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

			query.append(_SQL_SELECT_KALEOINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoInstanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				if (!pagination) {
					list = (List<KaleoInstance>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoInstance>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first kaleo instance in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a matching kaleo instance could not be found
	 */
	@Override
	public KaleoInstance findByKaleoDefinitionId_First(long kaleoDefinitionId,
		OrderByComparator<KaleoInstance> orderByComparator)
		throws NoSuchInstanceException {
		KaleoInstance kaleoInstance = fetchByKaleoDefinitionId_First(kaleoDefinitionId,
				orderByComparator);

		if (kaleoInstance != null) {
			return kaleoInstance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoDefinitionId=");
		msg.append(kaleoDefinitionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceException(msg.toString());
	}

	/**
	 * Returns the first kaleo instance in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	 */
	@Override
	public KaleoInstance fetchByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		OrderByComparator<KaleoInstance> orderByComparator) {
		List<KaleoInstance> list = findByKaleoDefinitionId(kaleoDefinitionId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo instance in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a matching kaleo instance could not be found
	 */
	@Override
	public KaleoInstance findByKaleoDefinitionId_Last(long kaleoDefinitionId,
		OrderByComparator<KaleoInstance> orderByComparator)
		throws NoSuchInstanceException {
		KaleoInstance kaleoInstance = fetchByKaleoDefinitionId_Last(kaleoDefinitionId,
				orderByComparator);

		if (kaleoInstance != null) {
			return kaleoInstance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoDefinitionId=");
		msg.append(kaleoDefinitionId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceException(msg.toString());
	}

	/**
	 * Returns the last kaleo instance in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	 */
	@Override
	public KaleoInstance fetchByKaleoDefinitionId_Last(long kaleoDefinitionId,
		OrderByComparator<KaleoInstance> orderByComparator) {
		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		if (count == 0) {
			return null;
		}

		List<KaleoInstance> list = findByKaleoDefinitionId(kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo instances before and after the current kaleo instance in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoInstanceId the primary key of the current kaleo instance
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a kaleo instance with the primary key could not be found
	 */
	@Override
	public KaleoInstance[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoInstanceId, long kaleoDefinitionId,
		OrderByComparator<KaleoInstance> orderByComparator)
		throws NoSuchInstanceException {
		KaleoInstance kaleoInstance = findByPrimaryKey(kaleoInstanceId);

		Session session = null;

		try {
			session = openSession();

			KaleoInstance[] array = new KaleoInstanceImpl[3];

			array[0] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoInstance, kaleoDefinitionId, orderByComparator, true);

			array[1] = kaleoInstance;

			array[2] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoInstance, kaleoDefinitionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoInstance getByKaleoDefinitionId_PrevAndNext(
		Session session, KaleoInstance kaleoInstance, long kaleoDefinitionId,
		OrderByComparator<KaleoInstance> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOINSTANCE_WHERE);

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
			query.append(KaleoInstanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoInstance);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo instances where kaleoDefinitionId = &#63; from the database.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 */
	@Override
	public void removeByKaleoDefinitionId(long kaleoDefinitionId) {
		for (KaleoInstance kaleoInstance : findByKaleoDefinitionId(
				kaleoDefinitionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoInstance);
		}
	}

	/**
	 * Returns the number of kaleo instances where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @return the number of matching kaleo instances
	 */
	@Override
	public int countByKaleoDefinitionId(long kaleoDefinitionId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KALEODEFINITIONID;

		Object[] finderArgs = new Object[] { kaleoDefinitionId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOINSTANCE_WHERE);

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
		"kaleoInstance.kaleoDefinitionId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_U = new FinderPath(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_U = new FinderPath(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByC_U",
			new String[] { Long.class.getName(), Long.class.getName() },
			KaleoInstanceModelImpl.COMPANYID_COLUMN_BITMASK |
			KaleoInstanceModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_U = new FinderPath(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_U",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the kaleo instances where companyId = &#63; and userId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @return the matching kaleo instances
	 */
	@Override
	public List<KaleoInstance> findByC_U(long companyId, long userId) {
		return findByC_U(companyId, userId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo instances where companyId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of kaleo instances
	 * @param end the upper bound of the range of kaleo instances (not inclusive)
	 * @return the range of matching kaleo instances
	 */
	@Override
	public List<KaleoInstance> findByC_U(long companyId, long userId,
		int start, int end) {
		return findByC_U(companyId, userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo instances where companyId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of kaleo instances
	 * @param end the upper bound of the range of kaleo instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo instances
	 */
	@Override
	public List<KaleoInstance> findByC_U(long companyId, long userId,
		int start, int end, OrderByComparator<KaleoInstance> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_U;
			finderArgs = new Object[] { companyId, userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_U;
			finderArgs = new Object[] {
					companyId, userId,
					
					start, end, orderByComparator
				};
		}

		List<KaleoInstance> list = (List<KaleoInstance>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoInstance kaleoInstance : list) {
				if ((companyId != kaleoInstance.getCompanyId()) ||
						(userId != kaleoInstance.getUserId())) {
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

			query.append(_SQL_SELECT_KALEOINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_C_U_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_U_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoInstanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(userId);

				if (!pagination) {
					list = (List<KaleoInstance>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoInstance>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first kaleo instance in the ordered set where companyId = &#63; and userId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a matching kaleo instance could not be found
	 */
	@Override
	public KaleoInstance findByC_U_First(long companyId, long userId,
		OrderByComparator<KaleoInstance> orderByComparator)
		throws NoSuchInstanceException {
		KaleoInstance kaleoInstance = fetchByC_U_First(companyId, userId,
				orderByComparator);

		if (kaleoInstance != null) {
			return kaleoInstance;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceException(msg.toString());
	}

	/**
	 * Returns the first kaleo instance in the ordered set where companyId = &#63; and userId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	 */
	@Override
	public KaleoInstance fetchByC_U_First(long companyId, long userId,
		OrderByComparator<KaleoInstance> orderByComparator) {
		List<KaleoInstance> list = findByC_U(companyId, userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo instance in the ordered set where companyId = &#63; and userId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a matching kaleo instance could not be found
	 */
	@Override
	public KaleoInstance findByC_U_Last(long companyId, long userId,
		OrderByComparator<KaleoInstance> orderByComparator)
		throws NoSuchInstanceException {
		KaleoInstance kaleoInstance = fetchByC_U_Last(companyId, userId,
				orderByComparator);

		if (kaleoInstance != null) {
			return kaleoInstance;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceException(msg.toString());
	}

	/**
	 * Returns the last kaleo instance in the ordered set where companyId = &#63; and userId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	 */
	@Override
	public KaleoInstance fetchByC_U_Last(long companyId, long userId,
		OrderByComparator<KaleoInstance> orderByComparator) {
		int count = countByC_U(companyId, userId);

		if (count == 0) {
			return null;
		}

		List<KaleoInstance> list = findByC_U(companyId, userId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo instances before and after the current kaleo instance in the ordered set where companyId = &#63; and userId = &#63;.
	 *
	 * @param kaleoInstanceId the primary key of the current kaleo instance
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a kaleo instance with the primary key could not be found
	 */
	@Override
	public KaleoInstance[] findByC_U_PrevAndNext(long kaleoInstanceId,
		long companyId, long userId,
		OrderByComparator<KaleoInstance> orderByComparator)
		throws NoSuchInstanceException {
		KaleoInstance kaleoInstance = findByPrimaryKey(kaleoInstanceId);

		Session session = null;

		try {
			session = openSession();

			KaleoInstance[] array = new KaleoInstanceImpl[3];

			array[0] = getByC_U_PrevAndNext(session, kaleoInstance, companyId,
					userId, orderByComparator, true);

			array[1] = kaleoInstance;

			array[2] = getByC_U_PrevAndNext(session, kaleoInstance, companyId,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoInstance getByC_U_PrevAndNext(Session session,
		KaleoInstance kaleoInstance, long companyId, long userId,
		OrderByComparator<KaleoInstance> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOINSTANCE_WHERE);

		query.append(_FINDER_COLUMN_C_U_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_U_USERID_2);

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
			query.append(KaleoInstanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoInstance);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo instances where companyId = &#63; and userId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 */
	@Override
	public void removeByC_U(long companyId, long userId) {
		for (KaleoInstance kaleoInstance : findByC_U(companyId, userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoInstance);
		}
	}

	/**
	 * Returns the number of kaleo instances where companyId = &#63; and userId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @return the number of matching kaleo instances
	 */
	@Override
	public int countByC_U(long companyId, long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_U;

		Object[] finderArgs = new Object[] { companyId, userId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEOINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_C_U_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_C_U_COMPANYID_2 = "kaleoInstance.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_U_USERID_2 = "kaleoInstance.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KDI_C = new FinderPath(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByKDI_C",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KDI_C = new FinderPath(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByKDI_C",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			KaleoInstanceModelImpl.KALEODEFINITIONID_COLUMN_BITMASK |
			KaleoInstanceModelImpl.COMPLETED_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KDI_C = new FinderPath(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKDI_C",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the kaleo instances where kaleoDefinitionId = &#63; and completed = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param completed the completed
	 * @return the matching kaleo instances
	 */
	@Override
	public List<KaleoInstance> findByKDI_C(long kaleoDefinitionId,
		boolean completed) {
		return findByKDI_C(kaleoDefinitionId, completed, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo instances where kaleoDefinitionId = &#63; and completed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param completed the completed
	 * @param start the lower bound of the range of kaleo instances
	 * @param end the upper bound of the range of kaleo instances (not inclusive)
	 * @return the range of matching kaleo instances
	 */
	@Override
	public List<KaleoInstance> findByKDI_C(long kaleoDefinitionId,
		boolean completed, int start, int end) {
		return findByKDI_C(kaleoDefinitionId, completed, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo instances where kaleoDefinitionId = &#63; and completed = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param completed the completed
	 * @param start the lower bound of the range of kaleo instances
	 * @param end the upper bound of the range of kaleo instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo instances
	 */
	@Override
	public List<KaleoInstance> findByKDI_C(long kaleoDefinitionId,
		boolean completed, int start, int end,
		OrderByComparator<KaleoInstance> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KDI_C;
			finderArgs = new Object[] { kaleoDefinitionId, completed };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_KDI_C;
			finderArgs = new Object[] {
					kaleoDefinitionId, completed,
					
					start, end, orderByComparator
				};
		}

		List<KaleoInstance> list = (List<KaleoInstance>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoInstance kaleoInstance : list) {
				if ((kaleoDefinitionId != kaleoInstance.getKaleoDefinitionId()) ||
						(completed != kaleoInstance.getCompleted())) {
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

			query.append(_SQL_SELECT_KALEOINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_KDI_C_KALEODEFINITIONID_2);

			query.append(_FINDER_COLUMN_KDI_C_COMPLETED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoInstanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				qPos.add(completed);

				if (!pagination) {
					list = (List<KaleoInstance>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoInstance>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first kaleo instance in the ordered set where kaleoDefinitionId = &#63; and completed = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a matching kaleo instance could not be found
	 */
	@Override
	public KaleoInstance findByKDI_C_First(long kaleoDefinitionId,
		boolean completed, OrderByComparator<KaleoInstance> orderByComparator)
		throws NoSuchInstanceException {
		KaleoInstance kaleoInstance = fetchByKDI_C_First(kaleoDefinitionId,
				completed, orderByComparator);

		if (kaleoInstance != null) {
			return kaleoInstance;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoDefinitionId=");
		msg.append(kaleoDefinitionId);

		msg.append(", completed=");
		msg.append(completed);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceException(msg.toString());
	}

	/**
	 * Returns the first kaleo instance in the ordered set where kaleoDefinitionId = &#63; and completed = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	 */
	@Override
	public KaleoInstance fetchByKDI_C_First(long kaleoDefinitionId,
		boolean completed, OrderByComparator<KaleoInstance> orderByComparator) {
		List<KaleoInstance> list = findByKDI_C(kaleoDefinitionId, completed, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo instance in the ordered set where kaleoDefinitionId = &#63; and completed = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a matching kaleo instance could not be found
	 */
	@Override
	public KaleoInstance findByKDI_C_Last(long kaleoDefinitionId,
		boolean completed, OrderByComparator<KaleoInstance> orderByComparator)
		throws NoSuchInstanceException {
		KaleoInstance kaleoInstance = fetchByKDI_C_Last(kaleoDefinitionId,
				completed, orderByComparator);

		if (kaleoInstance != null) {
			return kaleoInstance;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoDefinitionId=");
		msg.append(kaleoDefinitionId);

		msg.append(", completed=");
		msg.append(completed);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceException(msg.toString());
	}

	/**
	 * Returns the last kaleo instance in the ordered set where kaleoDefinitionId = &#63; and completed = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	 */
	@Override
	public KaleoInstance fetchByKDI_C_Last(long kaleoDefinitionId,
		boolean completed, OrderByComparator<KaleoInstance> orderByComparator) {
		int count = countByKDI_C(kaleoDefinitionId, completed);

		if (count == 0) {
			return null;
		}

		List<KaleoInstance> list = findByKDI_C(kaleoDefinitionId, completed,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo instances before and after the current kaleo instance in the ordered set where kaleoDefinitionId = &#63; and completed = &#63;.
	 *
	 * @param kaleoInstanceId the primary key of the current kaleo instance
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param completed the completed
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a kaleo instance with the primary key could not be found
	 */
	@Override
	public KaleoInstance[] findByKDI_C_PrevAndNext(long kaleoInstanceId,
		long kaleoDefinitionId, boolean completed,
		OrderByComparator<KaleoInstance> orderByComparator)
		throws NoSuchInstanceException {
		KaleoInstance kaleoInstance = findByPrimaryKey(kaleoInstanceId);

		Session session = null;

		try {
			session = openSession();

			KaleoInstance[] array = new KaleoInstanceImpl[3];

			array[0] = getByKDI_C_PrevAndNext(session, kaleoInstance,
					kaleoDefinitionId, completed, orderByComparator, true);

			array[1] = kaleoInstance;

			array[2] = getByKDI_C_PrevAndNext(session, kaleoInstance,
					kaleoDefinitionId, completed, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoInstance getByKDI_C_PrevAndNext(Session session,
		KaleoInstance kaleoInstance, long kaleoDefinitionId, boolean completed,
		OrderByComparator<KaleoInstance> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOINSTANCE_WHERE);

		query.append(_FINDER_COLUMN_KDI_C_KALEODEFINITIONID_2);

		query.append(_FINDER_COLUMN_KDI_C_COMPLETED_2);

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
			query.append(KaleoInstanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		qPos.add(completed);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoInstance);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo instances where kaleoDefinitionId = &#63; and completed = &#63; from the database.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param completed the completed
	 */
	@Override
	public void removeByKDI_C(long kaleoDefinitionId, boolean completed) {
		for (KaleoInstance kaleoInstance : findByKDI_C(kaleoDefinitionId,
				completed, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoInstance);
		}
	}

	/**
	 * Returns the number of kaleo instances where kaleoDefinitionId = &#63; and completed = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition ID
	 * @param completed the completed
	 * @return the number of matching kaleo instances
	 */
	@Override
	public int countByKDI_C(long kaleoDefinitionId, boolean completed) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KDI_C;

		Object[] finderArgs = new Object[] { kaleoDefinitionId, completed };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEOINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_KDI_C_KALEODEFINITIONID_2);

			query.append(_FINDER_COLUMN_KDI_C_COMPLETED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				qPos.add(completed);

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

	private static final String _FINDER_COLUMN_KDI_C_KALEODEFINITIONID_2 = "kaleoInstance.kaleoDefinitionId = ? AND ";
	private static final String _FINDER_COLUMN_KDI_C_COMPLETED_2 = "kaleoInstance.completed = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CN_CPK = new FinderPath(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCN_CPK",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CN_CPK =
		new FinderPath(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCN_CPK",
			new String[] { String.class.getName(), Long.class.getName() },
			KaleoInstanceModelImpl.CLASSNAME_COLUMN_BITMASK |
			KaleoInstanceModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CN_CPK = new FinderPath(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCN_CPK",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the kaleo instances where className = &#63; and classPK = &#63;.
	 *
	 * @param className the class name
	 * @param classPK the class p k
	 * @return the matching kaleo instances
	 */
	@Override
	public List<KaleoInstance> findByCN_CPK(String className, long classPK) {
		return findByCN_CPK(className, classPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo instances where className = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param className the class name
	 * @param classPK the class p k
	 * @param start the lower bound of the range of kaleo instances
	 * @param end the upper bound of the range of kaleo instances (not inclusive)
	 * @return the range of matching kaleo instances
	 */
	@Override
	public List<KaleoInstance> findByCN_CPK(String className, long classPK,
		int start, int end) {
		return findByCN_CPK(className, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo instances where className = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param className the class name
	 * @param classPK the class p k
	 * @param start the lower bound of the range of kaleo instances
	 * @param end the upper bound of the range of kaleo instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo instances
	 */
	@Override
	public List<KaleoInstance> findByCN_CPK(String className, long classPK,
		int start, int end, OrderByComparator<KaleoInstance> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CN_CPK;
			finderArgs = new Object[] { className, classPK };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CN_CPK;
			finderArgs = new Object[] {
					className, classPK,
					
					start, end, orderByComparator
				};
		}

		List<KaleoInstance> list = (List<KaleoInstance>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoInstance kaleoInstance : list) {
				if (!Validator.equals(className, kaleoInstance.getClassName()) ||
						(classPK != kaleoInstance.getClassPK())) {
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

			query.append(_SQL_SELECT_KALEOINSTANCE_WHERE);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_CN_CPK_CLASSNAME_1);
			}
			else if (className.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CN_CPK_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_CN_CPK_CLASSNAME_2);
			}

			query.append(_FINDER_COLUMN_CN_CPK_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoInstanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindClassName) {
					qPos.add(className);
				}

				qPos.add(classPK);

				if (!pagination) {
					list = (List<KaleoInstance>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoInstance>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first kaleo instance in the ordered set where className = &#63; and classPK = &#63;.
	 *
	 * @param className the class name
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a matching kaleo instance could not be found
	 */
	@Override
	public KaleoInstance findByCN_CPK_First(String className, long classPK,
		OrderByComparator<KaleoInstance> orderByComparator)
		throws NoSuchInstanceException {
		KaleoInstance kaleoInstance = fetchByCN_CPK_First(className, classPK,
				orderByComparator);

		if (kaleoInstance != null) {
			return kaleoInstance;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("className=");
		msg.append(className);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceException(msg.toString());
	}

	/**
	 * Returns the first kaleo instance in the ordered set where className = &#63; and classPK = &#63;.
	 *
	 * @param className the class name
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	 */
	@Override
	public KaleoInstance fetchByCN_CPK_First(String className, long classPK,
		OrderByComparator<KaleoInstance> orderByComparator) {
		List<KaleoInstance> list = findByCN_CPK(className, classPK, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo instance in the ordered set where className = &#63; and classPK = &#63;.
	 *
	 * @param className the class name
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a matching kaleo instance could not be found
	 */
	@Override
	public KaleoInstance findByCN_CPK_Last(String className, long classPK,
		OrderByComparator<KaleoInstance> orderByComparator)
		throws NoSuchInstanceException {
		KaleoInstance kaleoInstance = fetchByCN_CPK_Last(className, classPK,
				orderByComparator);

		if (kaleoInstance != null) {
			return kaleoInstance;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("className=");
		msg.append(className);

		msg.append(", classPK=");
		msg.append(classPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceException(msg.toString());
	}

	/**
	 * Returns the last kaleo instance in the ordered set where className = &#63; and classPK = &#63;.
	 *
	 * @param className the class name
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	 */
	@Override
	public KaleoInstance fetchByCN_CPK_Last(String className, long classPK,
		OrderByComparator<KaleoInstance> orderByComparator) {
		int count = countByCN_CPK(className, classPK);

		if (count == 0) {
			return null;
		}

		List<KaleoInstance> list = findByCN_CPK(className, classPK, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo instances before and after the current kaleo instance in the ordered set where className = &#63; and classPK = &#63;.
	 *
	 * @param kaleoInstanceId the primary key of the current kaleo instance
	 * @param className the class name
	 * @param classPK the class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a kaleo instance with the primary key could not be found
	 */
	@Override
	public KaleoInstance[] findByCN_CPK_PrevAndNext(long kaleoInstanceId,
		String className, long classPK,
		OrderByComparator<KaleoInstance> orderByComparator)
		throws NoSuchInstanceException {
		KaleoInstance kaleoInstance = findByPrimaryKey(kaleoInstanceId);

		Session session = null;

		try {
			session = openSession();

			KaleoInstance[] array = new KaleoInstanceImpl[3];

			array[0] = getByCN_CPK_PrevAndNext(session, kaleoInstance,
					className, classPK, orderByComparator, true);

			array[1] = kaleoInstance;

			array[2] = getByCN_CPK_PrevAndNext(session, kaleoInstance,
					className, classPK, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoInstance getByCN_CPK_PrevAndNext(Session session,
		KaleoInstance kaleoInstance, String className, long classPK,
		OrderByComparator<KaleoInstance> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOINSTANCE_WHERE);

		boolean bindClassName = false;

		if (className == null) {
			query.append(_FINDER_COLUMN_CN_CPK_CLASSNAME_1);
		}
		else if (className.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_CN_CPK_CLASSNAME_3);
		}
		else {
			bindClassName = true;

			query.append(_FINDER_COLUMN_CN_CPK_CLASSNAME_2);
		}

		query.append(_FINDER_COLUMN_CN_CPK_CLASSPK_2);

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
			query.append(KaleoInstanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindClassName) {
			qPos.add(className);
		}

		qPos.add(classPK);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoInstance);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo instances where className = &#63; and classPK = &#63; from the database.
	 *
	 * @param className the class name
	 * @param classPK the class p k
	 */
	@Override
	public void removeByCN_CPK(String className, long classPK) {
		for (KaleoInstance kaleoInstance : findByCN_CPK(className, classPK,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoInstance);
		}
	}

	/**
	 * Returns the number of kaleo instances where className = &#63; and classPK = &#63;.
	 *
	 * @param className the class name
	 * @param classPK the class p k
	 * @return the number of matching kaleo instances
	 */
	@Override
	public int countByCN_CPK(String className, long classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CN_CPK;

		Object[] finderArgs = new Object[] { className, classPK };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEOINSTANCE_WHERE);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_CN_CPK_CLASSNAME_1);
			}
			else if (className.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CN_CPK_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_CN_CPK_CLASSNAME_2);
			}

			query.append(_FINDER_COLUMN_CN_CPK_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindClassName) {
					qPos.add(className);
				}

				qPos.add(classPK);

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

	private static final String _FINDER_COLUMN_CN_CPK_CLASSNAME_1 = "kaleoInstance.className IS NULL AND ";
	private static final String _FINDER_COLUMN_CN_CPK_CLASSNAME_2 = "kaleoInstance.className = ? AND ";
	private static final String _FINDER_COLUMN_CN_CPK_CLASSNAME_3 = "(kaleoInstance.className IS NULL OR kaleoInstance.className = '') AND ";
	private static final String _FINDER_COLUMN_CN_CPK_CLASSPK_2 = "kaleoInstance.classPK = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_KDN_KDV_CD =
		new FinderPath(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_KDN_KDV_CD",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_KDN_KDV_CD =
		new FinderPath(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceModelImpl.FINDER_CACHE_ENABLED,
			KaleoInstanceImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByC_KDN_KDV_CD",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Date.class.getName()
			},
			KaleoInstanceModelImpl.COMPANYID_COLUMN_BITMASK |
			KaleoInstanceModelImpl.KALEODEFINITIONNAME_COLUMN_BITMASK |
			KaleoInstanceModelImpl.KALEODEFINITIONVERSION_COLUMN_BITMASK |
			KaleoInstanceModelImpl.COMPLETIONDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_KDN_KDV_CD = new FinderPath(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_KDN_KDV_CD",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Date.class.getName()
			});

	/**
	 * Returns all the kaleo instances where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param kaleoDefinitionName the kaleo definition name
	 * @param kaleoDefinitionVersion the kaleo definition version
	 * @param completionDate the completion date
	 * @return the matching kaleo instances
	 */
	@Override
	public List<KaleoInstance> findByC_KDN_KDV_CD(long companyId,
		String kaleoDefinitionName, int kaleoDefinitionVersion,
		Date completionDate) {
		return findByC_KDN_KDV_CD(companyId, kaleoDefinitionName,
			kaleoDefinitionVersion, completionDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo instances where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param kaleoDefinitionName the kaleo definition name
	 * @param kaleoDefinitionVersion the kaleo definition version
	 * @param completionDate the completion date
	 * @param start the lower bound of the range of kaleo instances
	 * @param end the upper bound of the range of kaleo instances (not inclusive)
	 * @return the range of matching kaleo instances
	 */
	@Override
	public List<KaleoInstance> findByC_KDN_KDV_CD(long companyId,
		String kaleoDefinitionName, int kaleoDefinitionVersion,
		Date completionDate, int start, int end) {
		return findByC_KDN_KDV_CD(companyId, kaleoDefinitionName,
			kaleoDefinitionVersion, completionDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo instances where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param kaleoDefinitionName the kaleo definition name
	 * @param kaleoDefinitionVersion the kaleo definition version
	 * @param completionDate the completion date
	 * @param start the lower bound of the range of kaleo instances
	 * @param end the upper bound of the range of kaleo instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo instances
	 */
	@Override
	public List<KaleoInstance> findByC_KDN_KDV_CD(long companyId,
		String kaleoDefinitionName, int kaleoDefinitionVersion,
		Date completionDate, int start, int end,
		OrderByComparator<KaleoInstance> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_KDN_KDV_CD;
			finderArgs = new Object[] {
					companyId, kaleoDefinitionName, kaleoDefinitionVersion,
					completionDate
				};
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_KDN_KDV_CD;
			finderArgs = new Object[] {
					companyId, kaleoDefinitionName, kaleoDefinitionVersion,
					completionDate,
					
					start, end, orderByComparator
				};
		}

		List<KaleoInstance> list = (List<KaleoInstance>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoInstance kaleoInstance : list) {
				if ((companyId != kaleoInstance.getCompanyId()) ||
						!Validator.equals(kaleoDefinitionName,
							kaleoInstance.getKaleoDefinitionName()) ||
						(kaleoDefinitionVersion != kaleoInstance.getKaleoDefinitionVersion()) ||
						!Validator.equals(completionDate,
							kaleoInstance.getCompletionDate())) {
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

			query.append(_SQL_SELECT_KALEOINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_C_KDN_KDV_CD_COMPANYID_2);

			boolean bindKaleoDefinitionName = false;

			if (kaleoDefinitionName == null) {
				query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONNAME_1);
			}
			else if (kaleoDefinitionName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONNAME_3);
			}
			else {
				bindKaleoDefinitionName = true;

				query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONNAME_2);
			}

			query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONVERSION_2);

			boolean bindCompletionDate = false;

			if (completionDate == null) {
				query.append(_FINDER_COLUMN_C_KDN_KDV_CD_COMPLETIONDATE_1);
			}
			else {
				bindCompletionDate = true;

				query.append(_FINDER_COLUMN_C_KDN_KDV_CD_COMPLETIONDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoInstanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindKaleoDefinitionName) {
					qPos.add(kaleoDefinitionName);
				}

				qPos.add(kaleoDefinitionVersion);

				if (bindCompletionDate) {
					qPos.add(new Timestamp(completionDate.getTime()));
				}

				if (!pagination) {
					list = (List<KaleoInstance>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoInstance>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first kaleo instance in the ordered set where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param kaleoDefinitionName the kaleo definition name
	 * @param kaleoDefinitionVersion the kaleo definition version
	 * @param completionDate the completion date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a matching kaleo instance could not be found
	 */
	@Override
	public KaleoInstance findByC_KDN_KDV_CD_First(long companyId,
		String kaleoDefinitionName, int kaleoDefinitionVersion,
		Date completionDate, OrderByComparator<KaleoInstance> orderByComparator)
		throws NoSuchInstanceException {
		KaleoInstance kaleoInstance = fetchByC_KDN_KDV_CD_First(companyId,
				kaleoDefinitionName, kaleoDefinitionVersion, completionDate,
				orderByComparator);

		if (kaleoInstance != null) {
			return kaleoInstance;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", kaleoDefinitionName=");
		msg.append(kaleoDefinitionName);

		msg.append(", kaleoDefinitionVersion=");
		msg.append(kaleoDefinitionVersion);

		msg.append(", completionDate=");
		msg.append(completionDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceException(msg.toString());
	}

	/**
	 * Returns the first kaleo instance in the ordered set where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param kaleoDefinitionName the kaleo definition name
	 * @param kaleoDefinitionVersion the kaleo definition version
	 * @param completionDate the completion date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	 */
	@Override
	public KaleoInstance fetchByC_KDN_KDV_CD_First(long companyId,
		String kaleoDefinitionName, int kaleoDefinitionVersion,
		Date completionDate, OrderByComparator<KaleoInstance> orderByComparator) {
		List<KaleoInstance> list = findByC_KDN_KDV_CD(companyId,
				kaleoDefinitionName, kaleoDefinitionVersion, completionDate, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo instance in the ordered set where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param kaleoDefinitionName the kaleo definition name
	 * @param kaleoDefinitionVersion the kaleo definition version
	 * @param completionDate the completion date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a matching kaleo instance could not be found
	 */
	@Override
	public KaleoInstance findByC_KDN_KDV_CD_Last(long companyId,
		String kaleoDefinitionName, int kaleoDefinitionVersion,
		Date completionDate, OrderByComparator<KaleoInstance> orderByComparator)
		throws NoSuchInstanceException {
		KaleoInstance kaleoInstance = fetchByC_KDN_KDV_CD_Last(companyId,
				kaleoDefinitionName, kaleoDefinitionVersion, completionDate,
				orderByComparator);

		if (kaleoInstance != null) {
			return kaleoInstance;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", kaleoDefinitionName=");
		msg.append(kaleoDefinitionName);

		msg.append(", kaleoDefinitionVersion=");
		msg.append(kaleoDefinitionVersion);

		msg.append(", completionDate=");
		msg.append(completionDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInstanceException(msg.toString());
	}

	/**
	 * Returns the last kaleo instance in the ordered set where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param kaleoDefinitionName the kaleo definition name
	 * @param kaleoDefinitionVersion the kaleo definition version
	 * @param completionDate the completion date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo instance, or <code>null</code> if a matching kaleo instance could not be found
	 */
	@Override
	public KaleoInstance fetchByC_KDN_KDV_CD_Last(long companyId,
		String kaleoDefinitionName, int kaleoDefinitionVersion,
		Date completionDate, OrderByComparator<KaleoInstance> orderByComparator) {
		int count = countByC_KDN_KDV_CD(companyId, kaleoDefinitionName,
				kaleoDefinitionVersion, completionDate);

		if (count == 0) {
			return null;
		}

		List<KaleoInstance> list = findByC_KDN_KDV_CD(companyId,
				kaleoDefinitionName, kaleoDefinitionVersion, completionDate,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo instances before and after the current kaleo instance in the ordered set where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	 *
	 * @param kaleoInstanceId the primary key of the current kaleo instance
	 * @param companyId the company ID
	 * @param kaleoDefinitionName the kaleo definition name
	 * @param kaleoDefinitionVersion the kaleo definition version
	 * @param completionDate the completion date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a kaleo instance with the primary key could not be found
	 */
	@Override
	public KaleoInstance[] findByC_KDN_KDV_CD_PrevAndNext(
		long kaleoInstanceId, long companyId, String kaleoDefinitionName,
		int kaleoDefinitionVersion, Date completionDate,
		OrderByComparator<KaleoInstance> orderByComparator)
		throws NoSuchInstanceException {
		KaleoInstance kaleoInstance = findByPrimaryKey(kaleoInstanceId);

		Session session = null;

		try {
			session = openSession();

			KaleoInstance[] array = new KaleoInstanceImpl[3];

			array[0] = getByC_KDN_KDV_CD_PrevAndNext(session, kaleoInstance,
					companyId, kaleoDefinitionName, kaleoDefinitionVersion,
					completionDate, orderByComparator, true);

			array[1] = kaleoInstance;

			array[2] = getByC_KDN_KDV_CD_PrevAndNext(session, kaleoInstance,
					companyId, kaleoDefinitionName, kaleoDefinitionVersion,
					completionDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoInstance getByC_KDN_KDV_CD_PrevAndNext(Session session,
		KaleoInstance kaleoInstance, long companyId,
		String kaleoDefinitionName, int kaleoDefinitionVersion,
		Date completionDate,
		OrderByComparator<KaleoInstance> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOINSTANCE_WHERE);

		query.append(_FINDER_COLUMN_C_KDN_KDV_CD_COMPANYID_2);

		boolean bindKaleoDefinitionName = false;

		if (kaleoDefinitionName == null) {
			query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONNAME_1);
		}
		else if (kaleoDefinitionName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONNAME_3);
		}
		else {
			bindKaleoDefinitionName = true;

			query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONNAME_2);
		}

		query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONVERSION_2);

		boolean bindCompletionDate = false;

		if (completionDate == null) {
			query.append(_FINDER_COLUMN_C_KDN_KDV_CD_COMPLETIONDATE_1);
		}
		else {
			bindCompletionDate = true;

			query.append(_FINDER_COLUMN_C_KDN_KDV_CD_COMPLETIONDATE_2);
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
			query.append(KaleoInstanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (bindKaleoDefinitionName) {
			qPos.add(kaleoDefinitionName);
		}

		qPos.add(kaleoDefinitionVersion);

		if (bindCompletionDate) {
			qPos.add(new Timestamp(completionDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoInstance);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo instances where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param kaleoDefinitionName the kaleo definition name
	 * @param kaleoDefinitionVersion the kaleo definition version
	 * @param completionDate the completion date
	 */
	@Override
	public void removeByC_KDN_KDV_CD(long companyId,
		String kaleoDefinitionName, int kaleoDefinitionVersion,
		Date completionDate) {
		for (KaleoInstance kaleoInstance : findByC_KDN_KDV_CD(companyId,
				kaleoDefinitionName, kaleoDefinitionVersion, completionDate,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoInstance);
		}
	}

	/**
	 * Returns the number of kaleo instances where companyId = &#63; and kaleoDefinitionName = &#63; and kaleoDefinitionVersion = &#63; and completionDate = &#63;.
	 *
	 * @param companyId the company ID
	 * @param kaleoDefinitionName the kaleo definition name
	 * @param kaleoDefinitionVersion the kaleo definition version
	 * @param completionDate the completion date
	 * @return the number of matching kaleo instances
	 */
	@Override
	public int countByC_KDN_KDV_CD(long companyId, String kaleoDefinitionName,
		int kaleoDefinitionVersion, Date completionDate) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_KDN_KDV_CD;

		Object[] finderArgs = new Object[] {
				companyId, kaleoDefinitionName, kaleoDefinitionVersion,
				completionDate
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_KALEOINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_C_KDN_KDV_CD_COMPANYID_2);

			boolean bindKaleoDefinitionName = false;

			if (kaleoDefinitionName == null) {
				query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONNAME_1);
			}
			else if (kaleoDefinitionName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONNAME_3);
			}
			else {
				bindKaleoDefinitionName = true;

				query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONNAME_2);
			}

			query.append(_FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONVERSION_2);

			boolean bindCompletionDate = false;

			if (completionDate == null) {
				query.append(_FINDER_COLUMN_C_KDN_KDV_CD_COMPLETIONDATE_1);
			}
			else {
				bindCompletionDate = true;

				query.append(_FINDER_COLUMN_C_KDN_KDV_CD_COMPLETIONDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindKaleoDefinitionName) {
					qPos.add(kaleoDefinitionName);
				}

				qPos.add(kaleoDefinitionVersion);

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

	private static final String _FINDER_COLUMN_C_KDN_KDV_CD_COMPANYID_2 = "kaleoInstance.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONNAME_1 =
		"kaleoInstance.kaleoDefinitionName IS NULL AND ";
	private static final String _FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONNAME_2 =
		"kaleoInstance.kaleoDefinitionName = ? AND ";
	private static final String _FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONNAME_3 =
		"(kaleoInstance.kaleoDefinitionName IS NULL OR kaleoInstance.kaleoDefinitionName = '') AND ";
	private static final String _FINDER_COLUMN_C_KDN_KDV_CD_KALEODEFINITIONVERSION_2 =
		"kaleoInstance.kaleoDefinitionVersion = ? AND ";
	private static final String _FINDER_COLUMN_C_KDN_KDV_CD_COMPLETIONDATE_1 = "kaleoInstance.completionDate IS NULL";
	private static final String _FINDER_COLUMN_C_KDN_KDV_CD_COMPLETIONDATE_2 = "kaleoInstance.completionDate = ?";

	public KaleoInstancePersistenceImpl() {
		setModelClass(KaleoInstance.class);
	}

	/**
	 * Caches the kaleo instance in the entity cache if it is enabled.
	 *
	 * @param kaleoInstance the kaleo instance
	 */
	@Override
	public void cacheResult(KaleoInstance kaleoInstance) {
		EntityCacheUtil.putResult(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceImpl.class, kaleoInstance.getPrimaryKey(),
			kaleoInstance);

		kaleoInstance.resetOriginalValues();
	}

	/**
	 * Caches the kaleo instances in the entity cache if it is enabled.
	 *
	 * @param kaleoInstances the kaleo instances
	 */
	@Override
	public void cacheResult(List<KaleoInstance> kaleoInstances) {
		for (KaleoInstance kaleoInstance : kaleoInstances) {
			if (EntityCacheUtil.getResult(
						KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
						KaleoInstanceImpl.class, kaleoInstance.getPrimaryKey()) == null) {
				cacheResult(kaleoInstance);
			}
			else {
				kaleoInstance.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all kaleo instances.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(KaleoInstanceImpl.class.getName());
		}

		EntityCacheUtil.clearCache(KaleoInstanceImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the kaleo instance.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KaleoInstance kaleoInstance) {
		EntityCacheUtil.removeResult(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceImpl.class, kaleoInstance.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<KaleoInstance> kaleoInstances) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (KaleoInstance kaleoInstance : kaleoInstances) {
			EntityCacheUtil.removeResult(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
				KaleoInstanceImpl.class, kaleoInstance.getPrimaryKey());
		}
	}

	/**
	 * Creates a new kaleo instance with the primary key. Does not add the kaleo instance to the database.
	 *
	 * @param kaleoInstanceId the primary key for the new kaleo instance
	 * @return the new kaleo instance
	 */
	@Override
	public KaleoInstance create(long kaleoInstanceId) {
		KaleoInstance kaleoInstance = new KaleoInstanceImpl();

		kaleoInstance.setNew(true);
		kaleoInstance.setPrimaryKey(kaleoInstanceId);

		return kaleoInstance;
	}

	/**
	 * Removes the kaleo instance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoInstanceId the primary key of the kaleo instance
	 * @return the kaleo instance that was removed
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a kaleo instance with the primary key could not be found
	 */
	@Override
	public KaleoInstance remove(long kaleoInstanceId)
		throws NoSuchInstanceException {
		return remove((Serializable)kaleoInstanceId);
	}

	/**
	 * Removes the kaleo instance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo instance
	 * @return the kaleo instance that was removed
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a kaleo instance with the primary key could not be found
	 */
	@Override
	public KaleoInstance remove(Serializable primaryKey)
		throws NoSuchInstanceException {
		Session session = null;

		try {
			session = openSession();

			KaleoInstance kaleoInstance = (KaleoInstance)session.get(KaleoInstanceImpl.class,
					primaryKey);

			if (kaleoInstance == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchInstanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(kaleoInstance);
		}
		catch (NoSuchInstanceException nsee) {
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
	protected KaleoInstance removeImpl(KaleoInstance kaleoInstance) {
		kaleoInstance = toUnwrappedModel(kaleoInstance);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(kaleoInstance)) {
				kaleoInstance = (KaleoInstance)session.get(KaleoInstanceImpl.class,
						kaleoInstance.getPrimaryKeyObj());
			}

			if (kaleoInstance != null) {
				session.delete(kaleoInstance);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (kaleoInstance != null) {
			clearCache(kaleoInstance);
		}

		return kaleoInstance;
	}

	@Override
	public KaleoInstance updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoInstance kaleoInstance) {
		kaleoInstance = toUnwrappedModel(kaleoInstance);

		boolean isNew = kaleoInstance.isNew();

		KaleoInstanceModelImpl kaleoInstanceModelImpl = (KaleoInstanceModelImpl)kaleoInstance;

		Session session = null;

		try {
			session = openSession();

			if (kaleoInstance.isNew()) {
				session.save(kaleoInstance);

				kaleoInstance.setNew(false);
			}
			else {
				session.merge(kaleoInstance);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !KaleoInstanceModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((kaleoInstanceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoInstanceModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { kaleoInstanceModelImpl.getCompanyId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((kaleoInstanceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoInstanceModelImpl.getOriginalKaleoDefinitionId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);

				args = new Object[] {
						kaleoInstanceModelImpl.getKaleoDefinitionId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEODEFINITIONID,
					args);
			}

			if ((kaleoInstanceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoInstanceModelImpl.getOriginalCompanyId(),
						kaleoInstanceModelImpl.getOriginalUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_U, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_U,
					args);

				args = new Object[] {
						kaleoInstanceModelImpl.getCompanyId(),
						kaleoInstanceModelImpl.getUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_U, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_U,
					args);
			}

			if ((kaleoInstanceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KDI_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoInstanceModelImpl.getOriginalKaleoDefinitionId(),
						kaleoInstanceModelImpl.getOriginalCompleted()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KDI_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KDI_C,
					args);

				args = new Object[] {
						kaleoInstanceModelImpl.getKaleoDefinitionId(),
						kaleoInstanceModelImpl.getCompleted()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KDI_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KDI_C,
					args);
			}

			if ((kaleoInstanceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CN_CPK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoInstanceModelImpl.getOriginalClassName(),
						kaleoInstanceModelImpl.getOriginalClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CN_CPK, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CN_CPK,
					args);

				args = new Object[] {
						kaleoInstanceModelImpl.getClassName(),
						kaleoInstanceModelImpl.getClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CN_CPK, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CN_CPK,
					args);
			}

			if ((kaleoInstanceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_KDN_KDV_CD.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoInstanceModelImpl.getOriginalCompanyId(),
						kaleoInstanceModelImpl.getOriginalKaleoDefinitionName(),
						kaleoInstanceModelImpl.getOriginalKaleoDefinitionVersion(),
						kaleoInstanceModelImpl.getOriginalCompletionDate()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_KDN_KDV_CD,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_KDN_KDV_CD,
					args);

				args = new Object[] {
						kaleoInstanceModelImpl.getCompanyId(),
						kaleoInstanceModelImpl.getKaleoDefinitionName(),
						kaleoInstanceModelImpl.getKaleoDefinitionVersion(),
						kaleoInstanceModelImpl.getCompletionDate()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_KDN_KDV_CD,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_KDN_KDV_CD,
					args);
			}
		}

		EntityCacheUtil.putResult(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoInstanceImpl.class, kaleoInstance.getPrimaryKey(),
			kaleoInstance, false);

		kaleoInstance.resetOriginalValues();

		return kaleoInstance;
	}

	protected KaleoInstance toUnwrappedModel(KaleoInstance kaleoInstance) {
		if (kaleoInstance instanceof KaleoInstanceImpl) {
			return kaleoInstance;
		}

		KaleoInstanceImpl kaleoInstanceImpl = new KaleoInstanceImpl();

		kaleoInstanceImpl.setNew(kaleoInstance.isNew());
		kaleoInstanceImpl.setPrimaryKey(kaleoInstance.getPrimaryKey());

		kaleoInstanceImpl.setKaleoInstanceId(kaleoInstance.getKaleoInstanceId());
		kaleoInstanceImpl.setGroupId(kaleoInstance.getGroupId());
		kaleoInstanceImpl.setCompanyId(kaleoInstance.getCompanyId());
		kaleoInstanceImpl.setUserId(kaleoInstance.getUserId());
		kaleoInstanceImpl.setUserName(kaleoInstance.getUserName());
		kaleoInstanceImpl.setCreateDate(kaleoInstance.getCreateDate());
		kaleoInstanceImpl.setModifiedDate(kaleoInstance.getModifiedDate());
		kaleoInstanceImpl.setKaleoDefinitionId(kaleoInstance.getKaleoDefinitionId());
		kaleoInstanceImpl.setKaleoDefinitionName(kaleoInstance.getKaleoDefinitionName());
		kaleoInstanceImpl.setKaleoDefinitionVersion(kaleoInstance.getKaleoDefinitionVersion());
		kaleoInstanceImpl.setRootKaleoInstanceTokenId(kaleoInstance.getRootKaleoInstanceTokenId());
		kaleoInstanceImpl.setClassName(kaleoInstance.getClassName());
		kaleoInstanceImpl.setClassPK(kaleoInstance.getClassPK());
		kaleoInstanceImpl.setCompleted(kaleoInstance.isCompleted());
		kaleoInstanceImpl.setCompletionDate(kaleoInstance.getCompletionDate());
		kaleoInstanceImpl.setWorkflowContext(kaleoInstance.getWorkflowContext());

		return kaleoInstanceImpl;
	}

	/**
	 * Returns the kaleo instance with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo instance
	 * @return the kaleo instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a kaleo instance with the primary key could not be found
	 */
	@Override
	public KaleoInstance findByPrimaryKey(Serializable primaryKey)
		throws NoSuchInstanceException {
		KaleoInstance kaleoInstance = fetchByPrimaryKey(primaryKey);

		if (kaleoInstance == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchInstanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return kaleoInstance;
	}

	/**
	 * Returns the kaleo instance with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchInstanceException} if it could not be found.
	 *
	 * @param kaleoInstanceId the primary key of the kaleo instance
	 * @return the kaleo instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceException if a kaleo instance with the primary key could not be found
	 */
	@Override
	public KaleoInstance findByPrimaryKey(long kaleoInstanceId)
		throws NoSuchInstanceException {
		return findByPrimaryKey((Serializable)kaleoInstanceId);
	}

	/**
	 * Returns the kaleo instance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo instance
	 * @return the kaleo instance, or <code>null</code> if a kaleo instance with the primary key could not be found
	 */
	@Override
	public KaleoInstance fetchByPrimaryKey(Serializable primaryKey) {
		KaleoInstance kaleoInstance = (KaleoInstance)EntityCacheUtil.getResult(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
				KaleoInstanceImpl.class, primaryKey);

		if (kaleoInstance == _nullKaleoInstance) {
			return null;
		}

		if (kaleoInstance == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoInstance = (KaleoInstance)session.get(KaleoInstanceImpl.class,
						primaryKey);

				if (kaleoInstance != null) {
					cacheResult(kaleoInstance);
				}
				else {
					EntityCacheUtil.putResult(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
						KaleoInstanceImpl.class, primaryKey, _nullKaleoInstance);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
					KaleoInstanceImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return kaleoInstance;
	}

	/**
	 * Returns the kaleo instance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoInstanceId the primary key of the kaleo instance
	 * @return the kaleo instance, or <code>null</code> if a kaleo instance with the primary key could not be found
	 */
	@Override
	public KaleoInstance fetchByPrimaryKey(long kaleoInstanceId) {
		return fetchByPrimaryKey((Serializable)kaleoInstanceId);
	}

	@Override
	public Map<Serializable, KaleoInstance> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, KaleoInstance> map = new HashMap<Serializable, KaleoInstance>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			KaleoInstance kaleoInstance = fetchByPrimaryKey(primaryKey);

			if (kaleoInstance != null) {
				map.put(primaryKey, kaleoInstance);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			KaleoInstance kaleoInstance = (KaleoInstance)EntityCacheUtil.getResult(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
					KaleoInstanceImpl.class, primaryKey);

			if (kaleoInstance == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, kaleoInstance);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_KALEOINSTANCE_WHERE_PKS_IN);

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

			for (KaleoInstance kaleoInstance : (List<KaleoInstance>)q.list()) {
				map.put(kaleoInstance.getPrimaryKeyObj(), kaleoInstance);

				cacheResult(kaleoInstance);

				uncachedPrimaryKeys.remove(kaleoInstance.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(KaleoInstanceModelImpl.ENTITY_CACHE_ENABLED,
					KaleoInstanceImpl.class, primaryKey, _nullKaleoInstance);
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
	 * Returns all the kaleo instances.
	 *
	 * @return the kaleo instances
	 */
	@Override
	public List<KaleoInstance> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo instances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo instances
	 * @param end the upper bound of the range of kaleo instances (not inclusive)
	 * @return the range of kaleo instances
	 */
	@Override
	public List<KaleoInstance> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo instances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo instances
	 * @param end the upper bound of the range of kaleo instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo instances
	 */
	@Override
	public List<KaleoInstance> findAll(int start, int end,
		OrderByComparator<KaleoInstance> orderByComparator) {
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

		List<KaleoInstance> list = (List<KaleoInstance>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KALEOINSTANCE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEOINSTANCE;

				if (pagination) {
					sql = sql.concat(KaleoInstanceModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<KaleoInstance>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoInstance>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the kaleo instances from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (KaleoInstance kaleoInstance : findAll()) {
			remove(kaleoInstance);
		}
	}

	/**
	 * Returns the number of kaleo instances.
	 *
	 * @return the number of kaleo instances
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KALEOINSTANCE);

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
	 * Initializes the kaleo instance persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(KaleoInstanceImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_KALEOINSTANCE = "SELECT kaleoInstance FROM KaleoInstance kaleoInstance";
	private static final String _SQL_SELECT_KALEOINSTANCE_WHERE_PKS_IN = "SELECT kaleoInstance FROM KaleoInstance kaleoInstance WHERE kaleoInstanceId IN (";
	private static final String _SQL_SELECT_KALEOINSTANCE_WHERE = "SELECT kaleoInstance FROM KaleoInstance kaleoInstance WHERE ";
	private static final String _SQL_COUNT_KALEOINSTANCE = "SELECT COUNT(kaleoInstance) FROM KaleoInstance kaleoInstance";
	private static final String _SQL_COUNT_KALEOINSTANCE_WHERE = "SELECT COUNT(kaleoInstance) FROM KaleoInstance kaleoInstance WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoInstance.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoInstance exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoInstance exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static final Log _log = LogFactoryUtil.getLog(KaleoInstancePersistenceImpl.class);
	private static final KaleoInstance _nullKaleoInstance = new KaleoInstanceImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<KaleoInstance> toCacheModel() {
				return _nullKaleoInstanceCacheModel;
			}
		};

	private static final CacheModel<KaleoInstance> _nullKaleoInstanceCacheModel = new CacheModel<KaleoInstance>() {
			@Override
			public KaleoInstance toEntityModel() {
				return _nullKaleoInstance;
			}
		};
}