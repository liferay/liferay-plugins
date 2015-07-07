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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.NoSuchDefinitionException;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionModelImpl;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoDefinitionPersistence;

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
 * The persistence implementation for the kaleo definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoDefinitionPersistence
 * @see com.liferay.portal.workflow.kaleo.service.persistence.KaleoDefinitionUtil
 * @generated
 */
@ProviderType
public class KaleoDefinitionPersistenceImpl extends BasePersistenceImpl<KaleoDefinition>
	implements KaleoDefinitionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KaleoDefinitionUtil} to access the kaleo definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoDefinitionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoDefinitionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoDefinitionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			KaleoDefinitionModelImpl.COMPANYID_COLUMN_BITMASK |
			KaleoDefinitionModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo definitions where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching kaleo definitions
	 */
	@Override
	public List<KaleoDefinition> findByCompanyId(long companyId) {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the kaleo definitions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo definitions
	 * @param end the upper bound of the range of kaleo definitions (not inclusive)
	 * @return the range of matching kaleo definitions
	 */
	@Override
	public List<KaleoDefinition> findByCompanyId(long companyId, int start,
		int end) {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo definitions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo definitions
	 * @param end the upper bound of the range of kaleo definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo definitions
	 */
	@Override
	public List<KaleoDefinition> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<KaleoDefinition> orderByComparator) {
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

		List<KaleoDefinition> list = (List<KaleoDefinition>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoDefinition kaleoDefinition : list) {
				if ((companyId != kaleoDefinition.getCompanyId())) {
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

			query.append(_SQL_SELECT_KALEODEFINITION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoDefinitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<KaleoDefinition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoDefinition>)QueryUtil.list(q,
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
	 * Returns the first kaleo definition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo definition
	 * @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	 */
	@Override
	public KaleoDefinition findByCompanyId_First(long companyId,
		OrderByComparator<KaleoDefinition> orderByComparator)
		throws NoSuchDefinitionException {
		KaleoDefinition kaleoDefinition = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (kaleoDefinition != null) {
			return kaleoDefinition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDefinitionException(msg.toString());
	}

	/**
	 * Returns the first kaleo definition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	 */
	@Override
	public KaleoDefinition fetchByCompanyId_First(long companyId,
		OrderByComparator<KaleoDefinition> orderByComparator) {
		List<KaleoDefinition> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo definition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo definition
	 * @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	 */
	@Override
	public KaleoDefinition findByCompanyId_Last(long companyId,
		OrderByComparator<KaleoDefinition> orderByComparator)
		throws NoSuchDefinitionException {
		KaleoDefinition kaleoDefinition = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (kaleoDefinition != null) {
			return kaleoDefinition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDefinitionException(msg.toString());
	}

	/**
	 * Returns the last kaleo definition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	 */
	@Override
	public KaleoDefinition fetchByCompanyId_Last(long companyId,
		OrderByComparator<KaleoDefinition> orderByComparator) {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<KaleoDefinition> list = findByCompanyId(companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo definitions before and after the current kaleo definition in the ordered set where companyId = &#63;.
	 *
	 * @param kaleoDefinitionId the primary key of the current kaleo definition
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo definition
	 * @throws NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	 */
	@Override
	public KaleoDefinition[] findByCompanyId_PrevAndNext(
		long kaleoDefinitionId, long companyId,
		OrderByComparator<KaleoDefinition> orderByComparator)
		throws NoSuchDefinitionException {
		KaleoDefinition kaleoDefinition = findByPrimaryKey(kaleoDefinitionId);

		Session session = null;

		try {
			session = openSession();

			KaleoDefinition[] array = new KaleoDefinitionImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, kaleoDefinition,
					companyId, orderByComparator, true);

			array[1] = kaleoDefinition;

			array[2] = getByCompanyId_PrevAndNext(session, kaleoDefinition,
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

	protected KaleoDefinition getByCompanyId_PrevAndNext(Session session,
		KaleoDefinition kaleoDefinition, long companyId,
		OrderByComparator<KaleoDefinition> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEODEFINITION_WHERE);

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
			query.append(KaleoDefinitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoDefinition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoDefinition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo definitions where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (KaleoDefinition kaleoDefinition : findByCompanyId(companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoDefinition);
		}
	}

	/**
	 * Returns the number of kaleo definitions where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching kaleo definitions
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEODEFINITION_WHERE);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoDefinition.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_N = new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoDefinitionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_N",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N = new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_N",
			new String[] { Long.class.getName(), String.class.getName() },
			KaleoDefinitionModelImpl.COMPANYID_COLUMN_BITMASK |
			KaleoDefinitionModelImpl.NAME_COLUMN_BITMASK |
			KaleoDefinitionModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_N = new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_N",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the kaleo definitions where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the matching kaleo definitions
	 */
	@Override
	public List<KaleoDefinition> findByC_N(long companyId, String name) {
		return findByC_N(companyId, name, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the kaleo definitions where companyId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param start the lower bound of the range of kaleo definitions
	 * @param end the upper bound of the range of kaleo definitions (not inclusive)
	 * @return the range of matching kaleo definitions
	 */
	@Override
	public List<KaleoDefinition> findByC_N(long companyId, String name,
		int start, int end) {
		return findByC_N(companyId, name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo definitions where companyId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param start the lower bound of the range of kaleo definitions
	 * @param end the upper bound of the range of kaleo definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo definitions
	 */
	@Override
	public List<KaleoDefinition> findByC_N(long companyId, String name,
		int start, int end, OrderByComparator<KaleoDefinition> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N;
			finderArgs = new Object[] { companyId, name };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_N;
			finderArgs = new Object[] {
					companyId, name,
					
					start, end, orderByComparator
				};
		}

		List<KaleoDefinition> list = (List<KaleoDefinition>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoDefinition kaleoDefinition : list) {
				if ((companyId != kaleoDefinition.getCompanyId()) ||
						!Validator.equals(name, kaleoDefinition.getName())) {
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

			query.append(_SQL_SELECT_KALEODEFINITION_WHERE);

			query.append(_FINDER_COLUMN_C_N_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoDefinitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
				}

				if (!pagination) {
					list = (List<KaleoDefinition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoDefinition>)QueryUtil.list(q,
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
	 * Returns the first kaleo definition in the ordered set where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo definition
	 * @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	 */
	@Override
	public KaleoDefinition findByC_N_First(long companyId, String name,
		OrderByComparator<KaleoDefinition> orderByComparator)
		throws NoSuchDefinitionException {
		KaleoDefinition kaleoDefinition = fetchByC_N_First(companyId, name,
				orderByComparator);

		if (kaleoDefinition != null) {
			return kaleoDefinition;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDefinitionException(msg.toString());
	}

	/**
	 * Returns the first kaleo definition in the ordered set where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	 */
	@Override
	public KaleoDefinition fetchByC_N_First(long companyId, String name,
		OrderByComparator<KaleoDefinition> orderByComparator) {
		List<KaleoDefinition> list = findByC_N(companyId, name, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo definition in the ordered set where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo definition
	 * @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	 */
	@Override
	public KaleoDefinition findByC_N_Last(long companyId, String name,
		OrderByComparator<KaleoDefinition> orderByComparator)
		throws NoSuchDefinitionException {
		KaleoDefinition kaleoDefinition = fetchByC_N_Last(companyId, name,
				orderByComparator);

		if (kaleoDefinition != null) {
			return kaleoDefinition;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDefinitionException(msg.toString());
	}

	/**
	 * Returns the last kaleo definition in the ordered set where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	 */
	@Override
	public KaleoDefinition fetchByC_N_Last(long companyId, String name,
		OrderByComparator<KaleoDefinition> orderByComparator) {
		int count = countByC_N(companyId, name);

		if (count == 0) {
			return null;
		}

		List<KaleoDefinition> list = findByC_N(companyId, name, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo definitions before and after the current kaleo definition in the ordered set where companyId = &#63; and name = &#63;.
	 *
	 * @param kaleoDefinitionId the primary key of the current kaleo definition
	 * @param companyId the company ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo definition
	 * @throws NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	 */
	@Override
	public KaleoDefinition[] findByC_N_PrevAndNext(long kaleoDefinitionId,
		long companyId, String name,
		OrderByComparator<KaleoDefinition> orderByComparator)
		throws NoSuchDefinitionException {
		KaleoDefinition kaleoDefinition = findByPrimaryKey(kaleoDefinitionId);

		Session session = null;

		try {
			session = openSession();

			KaleoDefinition[] array = new KaleoDefinitionImpl[3];

			array[0] = getByC_N_PrevAndNext(session, kaleoDefinition,
					companyId, name, orderByComparator, true);

			array[1] = kaleoDefinition;

			array[2] = getByC_N_PrevAndNext(session, kaleoDefinition,
					companyId, name, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoDefinition getByC_N_PrevAndNext(Session session,
		KaleoDefinition kaleoDefinition, long companyId, String name,
		OrderByComparator<KaleoDefinition> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEODEFINITION_WHERE);

		query.append(_FINDER_COLUMN_C_N_COMPANYID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_C_N_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_C_N_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_C_N_NAME_2);
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
			query.append(KaleoDefinitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (bindName) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoDefinition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoDefinition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo definitions where companyId = &#63; and name = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 */
	@Override
	public void removeByC_N(long companyId, String name) {
		for (KaleoDefinition kaleoDefinition : findByC_N(companyId, name,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoDefinition);
		}
	}

	/**
	 * Returns the number of kaleo definitions where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @return the number of matching kaleo definitions
	 */
	@Override
	public int countByC_N(long companyId, String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_N;

		Object[] finderArgs = new Object[] { companyId, name };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEODEFINITION_WHERE);

			query.append(_FINDER_COLUMN_C_N_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

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

	private static final String _FINDER_COLUMN_C_N_COMPANYID_2 = "kaleoDefinition.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_N_NAME_1 = "kaleoDefinition.name IS NULL";
	private static final String _FINDER_COLUMN_C_N_NAME_2 = "kaleoDefinition.name = ?";
	private static final String _FINDER_COLUMN_C_N_NAME_3 = "(kaleoDefinition.name IS NULL OR kaleoDefinition.name = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_A = new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoDefinitionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_A = new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_A",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			KaleoDefinitionModelImpl.COMPANYID_COLUMN_BITMASK |
			KaleoDefinitionModelImpl.ACTIVE_COLUMN_BITMASK |
			KaleoDefinitionModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_A = new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_A",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the kaleo definitions where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @return the matching kaleo definitions
	 */
	@Override
	public List<KaleoDefinition> findByC_A(long companyId, boolean active) {
		return findByC_A(companyId, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo definitions where companyId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param start the lower bound of the range of kaleo definitions
	 * @param end the upper bound of the range of kaleo definitions (not inclusive)
	 * @return the range of matching kaleo definitions
	 */
	@Override
	public List<KaleoDefinition> findByC_A(long companyId, boolean active,
		int start, int end) {
		return findByC_A(companyId, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo definitions where companyId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param start the lower bound of the range of kaleo definitions
	 * @param end the upper bound of the range of kaleo definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo definitions
	 */
	@Override
	public List<KaleoDefinition> findByC_A(long companyId, boolean active,
		int start, int end, OrderByComparator<KaleoDefinition> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_A;
			finderArgs = new Object[] { companyId, active };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_A;
			finderArgs = new Object[] {
					companyId, active,
					
					start, end, orderByComparator
				};
		}

		List<KaleoDefinition> list = (List<KaleoDefinition>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoDefinition kaleoDefinition : list) {
				if ((companyId != kaleoDefinition.getCompanyId()) ||
						(active != kaleoDefinition.getActive())) {
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

			query.append(_SQL_SELECT_KALEODEFINITION_WHERE);

			query.append(_FINDER_COLUMN_C_A_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoDefinitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(active);

				if (!pagination) {
					list = (List<KaleoDefinition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoDefinition>)QueryUtil.list(q,
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
	 * Returns the first kaleo definition in the ordered set where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo definition
	 * @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	 */
	@Override
	public KaleoDefinition findByC_A_First(long companyId, boolean active,
		OrderByComparator<KaleoDefinition> orderByComparator)
		throws NoSuchDefinitionException {
		KaleoDefinition kaleoDefinition = fetchByC_A_First(companyId, active,
				orderByComparator);

		if (kaleoDefinition != null) {
			return kaleoDefinition;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDefinitionException(msg.toString());
	}

	/**
	 * Returns the first kaleo definition in the ordered set where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	 */
	@Override
	public KaleoDefinition fetchByC_A_First(long companyId, boolean active,
		OrderByComparator<KaleoDefinition> orderByComparator) {
		List<KaleoDefinition> list = findByC_A(companyId, active, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo definition in the ordered set where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo definition
	 * @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	 */
	@Override
	public KaleoDefinition findByC_A_Last(long companyId, boolean active,
		OrderByComparator<KaleoDefinition> orderByComparator)
		throws NoSuchDefinitionException {
		KaleoDefinition kaleoDefinition = fetchByC_A_Last(companyId, active,
				orderByComparator);

		if (kaleoDefinition != null) {
			return kaleoDefinition;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDefinitionException(msg.toString());
	}

	/**
	 * Returns the last kaleo definition in the ordered set where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	 */
	@Override
	public KaleoDefinition fetchByC_A_Last(long companyId, boolean active,
		OrderByComparator<KaleoDefinition> orderByComparator) {
		int count = countByC_A(companyId, active);

		if (count == 0) {
			return null;
		}

		List<KaleoDefinition> list = findByC_A(companyId, active, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo definitions before and after the current kaleo definition in the ordered set where companyId = &#63; and active = &#63;.
	 *
	 * @param kaleoDefinitionId the primary key of the current kaleo definition
	 * @param companyId the company ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo definition
	 * @throws NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	 */
	@Override
	public KaleoDefinition[] findByC_A_PrevAndNext(long kaleoDefinitionId,
		long companyId, boolean active,
		OrderByComparator<KaleoDefinition> orderByComparator)
		throws NoSuchDefinitionException {
		KaleoDefinition kaleoDefinition = findByPrimaryKey(kaleoDefinitionId);

		Session session = null;

		try {
			session = openSession();

			KaleoDefinition[] array = new KaleoDefinitionImpl[3];

			array[0] = getByC_A_PrevAndNext(session, kaleoDefinition,
					companyId, active, orderByComparator, true);

			array[1] = kaleoDefinition;

			array[2] = getByC_A_PrevAndNext(session, kaleoDefinition,
					companyId, active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoDefinition getByC_A_PrevAndNext(Session session,
		KaleoDefinition kaleoDefinition, long companyId, boolean active,
		OrderByComparator<KaleoDefinition> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEODEFINITION_WHERE);

		query.append(_FINDER_COLUMN_C_A_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_A_ACTIVE_2);

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
			query.append(KaleoDefinitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoDefinition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoDefinition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo definitions where companyId = &#63; and active = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 */
	@Override
	public void removeByC_A(long companyId, boolean active) {
		for (KaleoDefinition kaleoDefinition : findByC_A(companyId, active,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoDefinition);
		}
	}

	/**
	 * Returns the number of kaleo definitions where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param active the active
	 * @return the number of matching kaleo definitions
	 */
	@Override
	public int countByC_A(long companyId, boolean active) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_A;

		Object[] finderArgs = new Object[] { companyId, active };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEODEFINITION_WHERE);

			query.append(_FINDER_COLUMN_C_A_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(active);

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

	private static final String _FINDER_COLUMN_C_A_COMPANYID_2 = "kaleoDefinition.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_A_ACTIVE_2 = "kaleoDefinition.active = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_C_N_V = new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoDefinitionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_N_V",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			},
			KaleoDefinitionModelImpl.COMPANYID_COLUMN_BITMASK |
			KaleoDefinitionModelImpl.NAME_COLUMN_BITMASK |
			KaleoDefinitionModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_N_V = new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_N_V",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns the kaleo definition where companyId = &#63; and name = &#63; and version = &#63; or throws a {@link NoSuchDefinitionException} if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @return the matching kaleo definition
	 * @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	 */
	@Override
	public KaleoDefinition findByC_N_V(long companyId, String name, int version)
		throws NoSuchDefinitionException {
		KaleoDefinition kaleoDefinition = fetchByC_N_V(companyId, name, version);

		if (kaleoDefinition == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", name=");
			msg.append(name);

			msg.append(", version=");
			msg.append(version);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchDefinitionException(msg.toString());
		}

		return kaleoDefinition;
	}

	/**
	 * Returns the kaleo definition where companyId = &#63; and name = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @return the matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	 */
	@Override
	public KaleoDefinition fetchByC_N_V(long companyId, String name, int version) {
		return fetchByC_N_V(companyId, name, version, true);
	}

	/**
	 * Returns the kaleo definition where companyId = &#63; and name = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	 */
	@Override
	public KaleoDefinition fetchByC_N_V(long companyId, String name,
		int version, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { companyId, name, version };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_N_V,
					finderArgs, this);
		}

		if (result instanceof KaleoDefinition) {
			KaleoDefinition kaleoDefinition = (KaleoDefinition)result;

			if ((companyId != kaleoDefinition.getCompanyId()) ||
					!Validator.equals(name, kaleoDefinition.getName()) ||
					(version != kaleoDefinition.getVersion())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_KALEODEFINITION_WHERE);

			query.append(_FINDER_COLUMN_C_N_V_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_V_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_V_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_V_NAME_2);
			}

			query.append(_FINDER_COLUMN_C_N_V_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(version);

				List<KaleoDefinition> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_N_V,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"KaleoDefinitionPersistenceImpl.fetchByC_N_V(long, String, int, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					KaleoDefinition kaleoDefinition = list.get(0);

					result = kaleoDefinition;

					cacheResult(kaleoDefinition);

					if ((kaleoDefinition.getCompanyId() != companyId) ||
							(kaleoDefinition.getName() == null) ||
							!kaleoDefinition.getName().equals(name) ||
							(kaleoDefinition.getVersion() != version)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_N_V,
							finderArgs, kaleoDefinition);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_N_V,
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
			return (KaleoDefinition)result;
		}
	}

	/**
	 * Removes the kaleo definition where companyId = &#63; and name = &#63; and version = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @return the kaleo definition that was removed
	 */
	@Override
	public KaleoDefinition removeByC_N_V(long companyId, String name,
		int version) throws NoSuchDefinitionException {
		KaleoDefinition kaleoDefinition = findByC_N_V(companyId, name, version);

		return remove(kaleoDefinition);
	}

	/**
	 * Returns the number of kaleo definitions where companyId = &#63; and name = &#63; and version = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @return the number of matching kaleo definitions
	 */
	@Override
	public int countByC_N_V(long companyId, String name, int version) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_N_V;

		Object[] finderArgs = new Object[] { companyId, name, version };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KALEODEFINITION_WHERE);

			query.append(_FINDER_COLUMN_C_N_V_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_V_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_V_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_V_NAME_2);
			}

			query.append(_FINDER_COLUMN_C_N_V_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(version);

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

	private static final String _FINDER_COLUMN_C_N_V_COMPANYID_2 = "kaleoDefinition.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_N_V_NAME_1 = "kaleoDefinition.name IS NULL AND ";
	private static final String _FINDER_COLUMN_C_N_V_NAME_2 = "kaleoDefinition.name = ? AND ";
	private static final String _FINDER_COLUMN_C_N_V_NAME_3 = "(kaleoDefinition.name IS NULL OR kaleoDefinition.name = '') AND ";
	private static final String _FINDER_COLUMN_C_N_V_VERSION_2 = "kaleoDefinition.version = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_N_A = new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoDefinitionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_N_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_A = new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_N_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			},
			KaleoDefinitionModelImpl.COMPANYID_COLUMN_BITMASK |
			KaleoDefinitionModelImpl.NAME_COLUMN_BITMASK |
			KaleoDefinitionModelImpl.ACTIVE_COLUMN_BITMASK |
			KaleoDefinitionModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_N_A = new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_N_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the kaleo definitions where companyId = &#63; and name = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param active the active
	 * @return the matching kaleo definitions
	 */
	@Override
	public List<KaleoDefinition> findByC_N_A(long companyId, String name,
		boolean active) {
		return findByC_N_A(companyId, name, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo definitions where companyId = &#63; and name = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param active the active
	 * @param start the lower bound of the range of kaleo definitions
	 * @param end the upper bound of the range of kaleo definitions (not inclusive)
	 * @return the range of matching kaleo definitions
	 */
	@Override
	public List<KaleoDefinition> findByC_N_A(long companyId, String name,
		boolean active, int start, int end) {
		return findByC_N_A(companyId, name, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo definitions where companyId = &#63; and name = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param active the active
	 * @param start the lower bound of the range of kaleo definitions
	 * @param end the upper bound of the range of kaleo definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo definitions
	 */
	@Override
	public List<KaleoDefinition> findByC_N_A(long companyId, String name,
		boolean active, int start, int end,
		OrderByComparator<KaleoDefinition> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_A;
			finderArgs = new Object[] { companyId, name, active };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_N_A;
			finderArgs = new Object[] {
					companyId, name, active,
					
					start, end, orderByComparator
				};
		}

		List<KaleoDefinition> list = (List<KaleoDefinition>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoDefinition kaleoDefinition : list) {
				if ((companyId != kaleoDefinition.getCompanyId()) ||
						!Validator.equals(name, kaleoDefinition.getName()) ||
						(active != kaleoDefinition.getActive())) {
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

			query.append(_SQL_SELECT_KALEODEFINITION_WHERE);

			query.append(_FINDER_COLUMN_C_N_A_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_A_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_A_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_A_NAME_2);
			}

			query.append(_FINDER_COLUMN_C_N_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoDefinitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(active);

				if (!pagination) {
					list = (List<KaleoDefinition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoDefinition>)QueryUtil.list(q,
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
	 * Returns the first kaleo definition in the ordered set where companyId = &#63; and name = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo definition
	 * @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	 */
	@Override
	public KaleoDefinition findByC_N_A_First(long companyId, String name,
		boolean active, OrderByComparator<KaleoDefinition> orderByComparator)
		throws NoSuchDefinitionException {
		KaleoDefinition kaleoDefinition = fetchByC_N_A_First(companyId, name,
				active, orderByComparator);

		if (kaleoDefinition != null) {
			return kaleoDefinition;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDefinitionException(msg.toString());
	}

	/**
	 * Returns the first kaleo definition in the ordered set where companyId = &#63; and name = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	 */
	@Override
	public KaleoDefinition fetchByC_N_A_First(long companyId, String name,
		boolean active, OrderByComparator<KaleoDefinition> orderByComparator) {
		List<KaleoDefinition> list = findByC_N_A(companyId, name, active, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo definition in the ordered set where companyId = &#63; and name = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo definition
	 * @throws NoSuchDefinitionException if a matching kaleo definition could not be found
	 */
	@Override
	public KaleoDefinition findByC_N_A_Last(long companyId, String name,
		boolean active, OrderByComparator<KaleoDefinition> orderByComparator)
		throws NoSuchDefinitionException {
		KaleoDefinition kaleoDefinition = fetchByC_N_A_Last(companyId, name,
				active, orderByComparator);

		if (kaleoDefinition != null) {
			return kaleoDefinition;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDefinitionException(msg.toString());
	}

	/**
	 * Returns the last kaleo definition in the ordered set where companyId = &#63; and name = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	 */
	@Override
	public KaleoDefinition fetchByC_N_A_Last(long companyId, String name,
		boolean active, OrderByComparator<KaleoDefinition> orderByComparator) {
		int count = countByC_N_A(companyId, name, active);

		if (count == 0) {
			return null;
		}

		List<KaleoDefinition> list = findByC_N_A(companyId, name, active,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo definitions before and after the current kaleo definition in the ordered set where companyId = &#63; and name = &#63; and active = &#63;.
	 *
	 * @param kaleoDefinitionId the primary key of the current kaleo definition
	 * @param companyId the company ID
	 * @param name the name
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo definition
	 * @throws NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	 */
	@Override
	public KaleoDefinition[] findByC_N_A_PrevAndNext(long kaleoDefinitionId,
		long companyId, String name, boolean active,
		OrderByComparator<KaleoDefinition> orderByComparator)
		throws NoSuchDefinitionException {
		KaleoDefinition kaleoDefinition = findByPrimaryKey(kaleoDefinitionId);

		Session session = null;

		try {
			session = openSession();

			KaleoDefinition[] array = new KaleoDefinitionImpl[3];

			array[0] = getByC_N_A_PrevAndNext(session, kaleoDefinition,
					companyId, name, active, orderByComparator, true);

			array[1] = kaleoDefinition;

			array[2] = getByC_N_A_PrevAndNext(session, kaleoDefinition,
					companyId, name, active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoDefinition getByC_N_A_PrevAndNext(Session session,
		KaleoDefinition kaleoDefinition, long companyId, String name,
		boolean active, OrderByComparator<KaleoDefinition> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEODEFINITION_WHERE);

		query.append(_FINDER_COLUMN_C_N_A_COMPANYID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_C_N_A_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_C_N_A_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_C_N_A_NAME_2);
		}

		query.append(_FINDER_COLUMN_C_N_A_ACTIVE_2);

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
			query.append(KaleoDefinitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (bindName) {
			qPos.add(name);
		}

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoDefinition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoDefinition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo definitions where companyId = &#63; and name = &#63; and active = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param active the active
	 */
	@Override
	public void removeByC_N_A(long companyId, String name, boolean active) {
		for (KaleoDefinition kaleoDefinition : findByC_N_A(companyId, name,
				active, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoDefinition);
		}
	}

	/**
	 * Returns the number of kaleo definitions where companyId = &#63; and name = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param active the active
	 * @return the number of matching kaleo definitions
	 */
	@Override
	public int countByC_N_A(long companyId, String name, boolean active) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_N_A;

		Object[] finderArgs = new Object[] { companyId, name, active };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KALEODEFINITION_WHERE);

			query.append(_FINDER_COLUMN_C_N_A_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_A_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_A_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_A_NAME_2);
			}

			query.append(_FINDER_COLUMN_C_N_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(active);

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

	private static final String _FINDER_COLUMN_C_N_A_COMPANYID_2 = "kaleoDefinition.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_N_A_NAME_1 = "kaleoDefinition.name IS NULL AND ";
	private static final String _FINDER_COLUMN_C_N_A_NAME_2 = "kaleoDefinition.name = ? AND ";
	private static final String _FINDER_COLUMN_C_N_A_NAME_3 = "(kaleoDefinition.name IS NULL OR kaleoDefinition.name = '') AND ";
	private static final String _FINDER_COLUMN_C_N_A_ACTIVE_2 = "kaleoDefinition.active = ?";

	public KaleoDefinitionPersistenceImpl() {
		setModelClass(KaleoDefinition.class);
	}

	/**
	 * Caches the kaleo definition in the entity cache if it is enabled.
	 *
	 * @param kaleoDefinition the kaleo definition
	 */
	@Override
	public void cacheResult(KaleoDefinition kaleoDefinition) {
		EntityCacheUtil.putResult(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionImpl.class, kaleoDefinition.getPrimaryKey(),
			kaleoDefinition);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_N_V,
			new Object[] {
				kaleoDefinition.getCompanyId(), kaleoDefinition.getName(),
				kaleoDefinition.getVersion()
			}, kaleoDefinition);

		kaleoDefinition.resetOriginalValues();
	}

	/**
	 * Caches the kaleo definitions in the entity cache if it is enabled.
	 *
	 * @param kaleoDefinitions the kaleo definitions
	 */
	@Override
	public void cacheResult(List<KaleoDefinition> kaleoDefinitions) {
		for (KaleoDefinition kaleoDefinition : kaleoDefinitions) {
			if (EntityCacheUtil.getResult(
						KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
						KaleoDefinitionImpl.class,
						kaleoDefinition.getPrimaryKey()) == null) {
				cacheResult(kaleoDefinition);
			}
			else {
				kaleoDefinition.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all kaleo definitions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(KaleoDefinitionImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the kaleo definition.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KaleoDefinition kaleoDefinition) {
		EntityCacheUtil.removeResult(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionImpl.class, kaleoDefinition.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(kaleoDefinition);
	}

	@Override
	public void clearCache(List<KaleoDefinition> kaleoDefinitions) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (KaleoDefinition kaleoDefinition : kaleoDefinitions) {
			EntityCacheUtil.removeResult(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
				KaleoDefinitionImpl.class, kaleoDefinition.getPrimaryKey());

			clearUniqueFindersCache(kaleoDefinition);
		}
	}

	protected void cacheUniqueFindersCache(KaleoDefinition kaleoDefinition) {
		if (kaleoDefinition.isNew()) {
			Object[] args = new Object[] {
					kaleoDefinition.getCompanyId(), kaleoDefinition.getName(),
					kaleoDefinition.getVersion()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_N_V, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_N_V, args,
				kaleoDefinition);
		}
		else {
			KaleoDefinitionModelImpl kaleoDefinitionModelImpl = (KaleoDefinitionModelImpl)kaleoDefinition;

			if ((kaleoDefinitionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_C_N_V.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoDefinition.getCompanyId(),
						kaleoDefinition.getName(), kaleoDefinition.getVersion()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_N_V, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_N_V, args,
					kaleoDefinition);
			}
		}
	}

	protected void clearUniqueFindersCache(KaleoDefinition kaleoDefinition) {
		KaleoDefinitionModelImpl kaleoDefinitionModelImpl = (KaleoDefinitionModelImpl)kaleoDefinition;

		Object[] args = new Object[] {
				kaleoDefinition.getCompanyId(), kaleoDefinition.getName(),
				kaleoDefinition.getVersion()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_N_V, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_N_V, args);

		if ((kaleoDefinitionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_N_V.getColumnBitmask()) != 0) {
			args = new Object[] {
					kaleoDefinitionModelImpl.getOriginalCompanyId(),
					kaleoDefinitionModelImpl.getOriginalName(),
					kaleoDefinitionModelImpl.getOriginalVersion()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_N_V, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_N_V, args);
		}
	}

	/**
	 * Creates a new kaleo definition with the primary key. Does not add the kaleo definition to the database.
	 *
	 * @param kaleoDefinitionId the primary key for the new kaleo definition
	 * @return the new kaleo definition
	 */
	@Override
	public KaleoDefinition create(long kaleoDefinitionId) {
		KaleoDefinition kaleoDefinition = new KaleoDefinitionImpl();

		kaleoDefinition.setNew(true);
		kaleoDefinition.setPrimaryKey(kaleoDefinitionId);

		return kaleoDefinition;
	}

	/**
	 * Removes the kaleo definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoDefinitionId the primary key of the kaleo definition
	 * @return the kaleo definition that was removed
	 * @throws NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	 */
	@Override
	public KaleoDefinition remove(long kaleoDefinitionId)
		throws NoSuchDefinitionException {
		return remove((Serializable)kaleoDefinitionId);
	}

	/**
	 * Removes the kaleo definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo definition
	 * @return the kaleo definition that was removed
	 * @throws NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	 */
	@Override
	public KaleoDefinition remove(Serializable primaryKey)
		throws NoSuchDefinitionException {
		Session session = null;

		try {
			session = openSession();

			KaleoDefinition kaleoDefinition = (KaleoDefinition)session.get(KaleoDefinitionImpl.class,
					primaryKey);

			if (kaleoDefinition == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(kaleoDefinition);
		}
		catch (NoSuchDefinitionException nsee) {
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
	protected KaleoDefinition removeImpl(KaleoDefinition kaleoDefinition) {
		kaleoDefinition = toUnwrappedModel(kaleoDefinition);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(kaleoDefinition)) {
				kaleoDefinition = (KaleoDefinition)session.get(KaleoDefinitionImpl.class,
						kaleoDefinition.getPrimaryKeyObj());
			}

			if (kaleoDefinition != null) {
				session.delete(kaleoDefinition);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (kaleoDefinition != null) {
			clearCache(kaleoDefinition);
		}

		return kaleoDefinition;
	}

	@Override
	public KaleoDefinition updateImpl(KaleoDefinition kaleoDefinition) {
		kaleoDefinition = toUnwrappedModel(kaleoDefinition);

		boolean isNew = kaleoDefinition.isNew();

		KaleoDefinitionModelImpl kaleoDefinitionModelImpl = (KaleoDefinitionModelImpl)kaleoDefinition;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (kaleoDefinition.getCreateDate() == null)) {
			if (serviceContext == null) {
				kaleoDefinition.setCreateDate(now);
			}
			else {
				kaleoDefinition.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!kaleoDefinitionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				kaleoDefinition.setModifiedDate(now);
			}
			else {
				kaleoDefinition.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (kaleoDefinition.isNew()) {
				session.save(kaleoDefinition);

				kaleoDefinition.setNew(false);
			}
			else {
				session.merge(kaleoDefinition);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !KaleoDefinitionModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((kaleoDefinitionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoDefinitionModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { kaleoDefinitionModelImpl.getCompanyId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((kaleoDefinitionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoDefinitionModelImpl.getOriginalCompanyId(),
						kaleoDefinitionModelImpl.getOriginalName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_N, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N,
					args);

				args = new Object[] {
						kaleoDefinitionModelImpl.getCompanyId(),
						kaleoDefinitionModelImpl.getName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_N, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N,
					args);
			}

			if ((kaleoDefinitionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoDefinitionModelImpl.getOriginalCompanyId(),
						kaleoDefinitionModelImpl.getOriginalActive()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_A,
					args);

				args = new Object[] {
						kaleoDefinitionModelImpl.getCompanyId(),
						kaleoDefinitionModelImpl.getActive()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_A,
					args);
			}

			if ((kaleoDefinitionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_A.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoDefinitionModelImpl.getOriginalCompanyId(),
						kaleoDefinitionModelImpl.getOriginalName(),
						kaleoDefinitionModelImpl.getOriginalActive()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_N_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_A,
					args);

				args = new Object[] {
						kaleoDefinitionModelImpl.getCompanyId(),
						kaleoDefinitionModelImpl.getName(),
						kaleoDefinitionModelImpl.getActive()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_N_A, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_A,
					args);
			}
		}

		EntityCacheUtil.putResult(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionImpl.class, kaleoDefinition.getPrimaryKey(),
			kaleoDefinition, false);

		clearUniqueFindersCache(kaleoDefinition);
		cacheUniqueFindersCache(kaleoDefinition);

		kaleoDefinition.resetOriginalValues();

		return kaleoDefinition;
	}

	protected KaleoDefinition toUnwrappedModel(KaleoDefinition kaleoDefinition) {
		if (kaleoDefinition instanceof KaleoDefinitionImpl) {
			return kaleoDefinition;
		}

		KaleoDefinitionImpl kaleoDefinitionImpl = new KaleoDefinitionImpl();

		kaleoDefinitionImpl.setNew(kaleoDefinition.isNew());
		kaleoDefinitionImpl.setPrimaryKey(kaleoDefinition.getPrimaryKey());

		kaleoDefinitionImpl.setKaleoDefinitionId(kaleoDefinition.getKaleoDefinitionId());
		kaleoDefinitionImpl.setGroupId(kaleoDefinition.getGroupId());
		kaleoDefinitionImpl.setCompanyId(kaleoDefinition.getCompanyId());
		kaleoDefinitionImpl.setUserId(kaleoDefinition.getUserId());
		kaleoDefinitionImpl.setUserName(kaleoDefinition.getUserName());
		kaleoDefinitionImpl.setCreateDate(kaleoDefinition.getCreateDate());
		kaleoDefinitionImpl.setModifiedDate(kaleoDefinition.getModifiedDate());
		kaleoDefinitionImpl.setName(kaleoDefinition.getName());
		kaleoDefinitionImpl.setTitle(kaleoDefinition.getTitle());
		kaleoDefinitionImpl.setDescription(kaleoDefinition.getDescription());
		kaleoDefinitionImpl.setContent(kaleoDefinition.getContent());
		kaleoDefinitionImpl.setVersion(kaleoDefinition.getVersion());
		kaleoDefinitionImpl.setActive(kaleoDefinition.isActive());
		kaleoDefinitionImpl.setStartKaleoNodeId(kaleoDefinition.getStartKaleoNodeId());

		return kaleoDefinitionImpl;
	}

	/**
	 * Returns the kaleo definition with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo definition
	 * @return the kaleo definition
	 * @throws NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	 */
	@Override
	public KaleoDefinition findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDefinitionException {
		KaleoDefinition kaleoDefinition = fetchByPrimaryKey(primaryKey);

		if (kaleoDefinition == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return kaleoDefinition;
	}

	/**
	 * Returns the kaleo definition with the primary key or throws a {@link NoSuchDefinitionException} if it could not be found.
	 *
	 * @param kaleoDefinitionId the primary key of the kaleo definition
	 * @return the kaleo definition
	 * @throws NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	 */
	@Override
	public KaleoDefinition findByPrimaryKey(long kaleoDefinitionId)
		throws NoSuchDefinitionException {
		return findByPrimaryKey((Serializable)kaleoDefinitionId);
	}

	/**
	 * Returns the kaleo definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo definition
	 * @return the kaleo definition, or <code>null</code> if a kaleo definition with the primary key could not be found
	 */
	@Override
	public KaleoDefinition fetchByPrimaryKey(Serializable primaryKey) {
		KaleoDefinition kaleoDefinition = (KaleoDefinition)EntityCacheUtil.getResult(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
				KaleoDefinitionImpl.class, primaryKey);

		if (kaleoDefinition == _nullKaleoDefinition) {
			return null;
		}

		if (kaleoDefinition == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoDefinition = (KaleoDefinition)session.get(KaleoDefinitionImpl.class,
						primaryKey);

				if (kaleoDefinition != null) {
					cacheResult(kaleoDefinition);
				}
				else {
					EntityCacheUtil.putResult(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
						KaleoDefinitionImpl.class, primaryKey,
						_nullKaleoDefinition);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
					KaleoDefinitionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return kaleoDefinition;
	}

	/**
	 * Returns the kaleo definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoDefinitionId the primary key of the kaleo definition
	 * @return the kaleo definition, or <code>null</code> if a kaleo definition with the primary key could not be found
	 */
	@Override
	public KaleoDefinition fetchByPrimaryKey(long kaleoDefinitionId) {
		return fetchByPrimaryKey((Serializable)kaleoDefinitionId);
	}

	@Override
	public Map<Serializable, KaleoDefinition> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, KaleoDefinition> map = new HashMap<Serializable, KaleoDefinition>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			KaleoDefinition kaleoDefinition = fetchByPrimaryKey(primaryKey);

			if (kaleoDefinition != null) {
				map.put(primaryKey, kaleoDefinition);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			KaleoDefinition kaleoDefinition = (KaleoDefinition)EntityCacheUtil.getResult(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
					KaleoDefinitionImpl.class, primaryKey);

			if (kaleoDefinition == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, kaleoDefinition);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_KALEODEFINITION_WHERE_PKS_IN);

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

			for (KaleoDefinition kaleoDefinition : (List<KaleoDefinition>)q.list()) {
				map.put(kaleoDefinition.getPrimaryKeyObj(), kaleoDefinition);

				cacheResult(kaleoDefinition);

				uncachedPrimaryKeys.remove(kaleoDefinition.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
					KaleoDefinitionImpl.class, primaryKey, _nullKaleoDefinition);
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
	 * Returns all the kaleo definitions.
	 *
	 * @return the kaleo definitions
	 */
	@Override
	public List<KaleoDefinition> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo definitions
	 * @param end the upper bound of the range of kaleo definitions (not inclusive)
	 * @return the range of kaleo definitions
	 */
	@Override
	public List<KaleoDefinition> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo definitions
	 * @param end the upper bound of the range of kaleo definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo definitions
	 */
	@Override
	public List<KaleoDefinition> findAll(int start, int end,
		OrderByComparator<KaleoDefinition> orderByComparator) {
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

		List<KaleoDefinition> list = (List<KaleoDefinition>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KALEODEFINITION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEODEFINITION;

				if (pagination) {
					sql = sql.concat(KaleoDefinitionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<KaleoDefinition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoDefinition>)QueryUtil.list(q,
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
	 * Removes all the kaleo definitions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (KaleoDefinition kaleoDefinition : findAll()) {
			remove(kaleoDefinition);
		}
	}

	/**
	 * Returns the number of kaleo definitions.
	 *
	 * @return the number of kaleo definitions
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KALEODEFINITION);

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
		return KaleoDefinitionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the kaleo definition persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(KaleoDefinitionImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_KALEODEFINITION = "SELECT kaleoDefinition FROM KaleoDefinition kaleoDefinition";
	private static final String _SQL_SELECT_KALEODEFINITION_WHERE_PKS_IN = "SELECT kaleoDefinition FROM KaleoDefinition kaleoDefinition WHERE kaleoDefinitionId IN (";
	private static final String _SQL_SELECT_KALEODEFINITION_WHERE = "SELECT kaleoDefinition FROM KaleoDefinition kaleoDefinition WHERE ";
	private static final String _SQL_COUNT_KALEODEFINITION = "SELECT COUNT(kaleoDefinition) FROM KaleoDefinition kaleoDefinition";
	private static final String _SQL_COUNT_KALEODEFINITION_WHERE = "SELECT COUNT(kaleoDefinition) FROM KaleoDefinition kaleoDefinition WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoDefinition.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoDefinition exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoDefinition exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(KaleoDefinitionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"active"
			});
	private static final KaleoDefinition _nullKaleoDefinition = new KaleoDefinitionImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<KaleoDefinition> toCacheModel() {
				return _nullKaleoDefinitionCacheModel;
			}
		};

	private static final CacheModel<KaleoDefinition> _nullKaleoDefinitionCacheModel =
		new CacheModel<KaleoDefinition>() {
			@Override
			public KaleoDefinition toEntityModel() {
				return _nullKaleoDefinition;
			}
		};
}