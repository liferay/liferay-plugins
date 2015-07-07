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
import com.liferay.portal.workflow.kaleo.NoSuchTimerException;
import com.liferay.portal.workflow.kaleo.model.KaleoTimer;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTimerModelImpl;
import com.liferay.portal.workflow.kaleo.service.persistence.KaleoTimerPersistence;

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
 * The persistence implementation for the kaleo timer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTimerPersistence
 * @see com.liferay.portal.workflow.kaleo.service.persistence.KaleoTimerUtil
 * @generated
 */
@ProviderType
public class KaleoTimerPersistenceImpl extends BasePersistenceImpl<KaleoTimer>
	implements KaleoTimerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KaleoTimerUtil} to access the kaleo timer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoTimerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerModelImpl.FINDER_CACHE_ENABLED, KaleoTimerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerModelImpl.FINDER_CACHE_ENABLED, KaleoTimerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KCN_KCPK = new FinderPath(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerModelImpl.FINDER_CACHE_ENABLED, KaleoTimerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKCN_KCPK",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK =
		new FinderPath(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerModelImpl.FINDER_CACHE_ENABLED, KaleoTimerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByKCN_KCPK",
			new String[] { String.class.getName(), Long.class.getName() },
			KaleoTimerModelImpl.KALEOCLASSNAME_COLUMN_BITMASK |
			KaleoTimerModelImpl.KALEOCLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KCN_KCPK = new FinderPath(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKCN_KCPK",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @return the matching kaleo timers
	 */
	@Override
	public List<KaleoTimer> findByKCN_KCPK(String kaleoClassName,
		long kaleoClassPK) {
		return findByKCN_KCPK(kaleoClassName, kaleoClassPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param start the lower bound of the range of kaleo timers
	 * @param end the upper bound of the range of kaleo timers (not inclusive)
	 * @return the range of matching kaleo timers
	 */
	@Override
	public List<KaleoTimer> findByKCN_KCPK(String kaleoClassName,
		long kaleoClassPK, int start, int end) {
		return findByKCN_KCPK(kaleoClassName, kaleoClassPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param start the lower bound of the range of kaleo timers
	 * @param end the upper bound of the range of kaleo timers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo timers
	 */
	@Override
	public List<KaleoTimer> findByKCN_KCPK(String kaleoClassName,
		long kaleoClassPK, int start, int end,
		OrderByComparator<KaleoTimer> orderByComparator) {
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

		List<KaleoTimer> list = (List<KaleoTimer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoTimer kaleoTimer : list) {
				if (!Validator.equals(kaleoClassName,
							kaleoTimer.getKaleoClassName()) ||
						(kaleoClassPK != kaleoTimer.getKaleoClassPK())) {
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

			query.append(_SQL_SELECT_KALEOTIMER_WHERE);

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
				query.append(KaleoTimerModelImpl.ORDER_BY_JPQL);
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
					list = (List<KaleoTimer>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoTimer>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first kaleo timer in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo timer
	 * @throws NoSuchTimerException if a matching kaleo timer could not be found
	 */
	@Override
	public KaleoTimer findByKCN_KCPK_First(String kaleoClassName,
		long kaleoClassPK, OrderByComparator<KaleoTimer> orderByComparator)
		throws NoSuchTimerException {
		KaleoTimer kaleoTimer = fetchByKCN_KCPK_First(kaleoClassName,
				kaleoClassPK, orderByComparator);

		if (kaleoTimer != null) {
			return kaleoTimer;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoClassName=");
		msg.append(kaleoClassName);

		msg.append(", kaleoClassPK=");
		msg.append(kaleoClassPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTimerException(msg.toString());
	}

	/**
	 * Returns the first kaleo timer in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo timer, or <code>null</code> if a matching kaleo timer could not be found
	 */
	@Override
	public KaleoTimer fetchByKCN_KCPK_First(String kaleoClassName,
		long kaleoClassPK, OrderByComparator<KaleoTimer> orderByComparator) {
		List<KaleoTimer> list = findByKCN_KCPK(kaleoClassName, kaleoClassPK, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo timer in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo timer
	 * @throws NoSuchTimerException if a matching kaleo timer could not be found
	 */
	@Override
	public KaleoTimer findByKCN_KCPK_Last(String kaleoClassName,
		long kaleoClassPK, OrderByComparator<KaleoTimer> orderByComparator)
		throws NoSuchTimerException {
		KaleoTimer kaleoTimer = fetchByKCN_KCPK_Last(kaleoClassName,
				kaleoClassPK, orderByComparator);

		if (kaleoTimer != null) {
			return kaleoTimer;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoClassName=");
		msg.append(kaleoClassName);

		msg.append(", kaleoClassPK=");
		msg.append(kaleoClassPK);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTimerException(msg.toString());
	}

	/**
	 * Returns the last kaleo timer in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo timer, or <code>null</code> if a matching kaleo timer could not be found
	 */
	@Override
	public KaleoTimer fetchByKCN_KCPK_Last(String kaleoClassName,
		long kaleoClassPK, OrderByComparator<KaleoTimer> orderByComparator) {
		int count = countByKCN_KCPK(kaleoClassName, kaleoClassPK);

		if (count == 0) {
			return null;
		}

		List<KaleoTimer> list = findByKCN_KCPK(kaleoClassName, kaleoClassPK,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo timers before and after the current kaleo timer in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoTimerId the primary key of the current kaleo timer
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo timer
	 * @throws NoSuchTimerException if a kaleo timer with the primary key could not be found
	 */
	@Override
	public KaleoTimer[] findByKCN_KCPK_PrevAndNext(long kaleoTimerId,
		String kaleoClassName, long kaleoClassPK,
		OrderByComparator<KaleoTimer> orderByComparator)
		throws NoSuchTimerException {
		KaleoTimer kaleoTimer = findByPrimaryKey(kaleoTimerId);

		Session session = null;

		try {
			session = openSession();

			KaleoTimer[] array = new KaleoTimerImpl[3];

			array[0] = getByKCN_KCPK_PrevAndNext(session, kaleoTimer,
					kaleoClassName, kaleoClassPK, orderByComparator, true);

			array[1] = kaleoTimer;

			array[2] = getByKCN_KCPK_PrevAndNext(session, kaleoTimer,
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

	protected KaleoTimer getByKCN_KCPK_PrevAndNext(Session session,
		KaleoTimer kaleoTimer, String kaleoClassName, long kaleoClassPK,
		OrderByComparator<KaleoTimer> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTIMER_WHERE);

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
			query.append(KaleoTimerModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoTimer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTimer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63; from the database.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 */
	@Override
	public void removeByKCN_KCPK(String kaleoClassName, long kaleoClassPK) {
		for (KaleoTimer kaleoTimer : findByKCN_KCPK(kaleoClassName,
				kaleoClassPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoTimer);
		}
	}

	/**
	 * Returns the number of kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @return the number of matching kaleo timers
	 */
	@Override
	public int countByKCN_KCPK(String kaleoClassName, long kaleoClassPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KCN_KCPK;

		Object[] finderArgs = new Object[] { kaleoClassName, kaleoClassPK };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEOTIMER_WHERE);

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

	private static final String _FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_1 = "kaleoTimer.kaleoClassName IS NULL AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_2 = "kaleoTimer.kaleoClassName = ? AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_KALEOCLASSNAME_3 = "(kaleoTimer.kaleoClassName IS NULL OR kaleoTimer.kaleoClassName = '') AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_KALEOCLASSPK_2 = "kaleoTimer.kaleoClassPK = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KCN_KCPK_BLOCKING =
		new FinderPath(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerModelImpl.FINDER_CACHE_ENABLED, KaleoTimerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByKCN_KCPK_Blocking",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_BLOCKING =
		new FinderPath(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerModelImpl.FINDER_CACHE_ENABLED, KaleoTimerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByKCN_KCPK_Blocking",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			},
			KaleoTimerModelImpl.KALEOCLASSNAME_COLUMN_BITMASK |
			KaleoTimerModelImpl.KALEOCLASSPK_COLUMN_BITMASK |
			KaleoTimerModelImpl.BLOCKING_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KCN_KCPK_BLOCKING = new FinderPath(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByKCN_KCPK_Blocking",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param blocking the blocking
	 * @return the matching kaleo timers
	 */
	@Override
	public List<KaleoTimer> findByKCN_KCPK_Blocking(String kaleoClassName,
		long kaleoClassPK, boolean blocking) {
		return findByKCN_KCPK_Blocking(kaleoClassName, kaleoClassPK, blocking,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param blocking the blocking
	 * @param start the lower bound of the range of kaleo timers
	 * @param end the upper bound of the range of kaleo timers (not inclusive)
	 * @return the range of matching kaleo timers
	 */
	@Override
	public List<KaleoTimer> findByKCN_KCPK_Blocking(String kaleoClassName,
		long kaleoClassPK, boolean blocking, int start, int end) {
		return findByKCN_KCPK_Blocking(kaleoClassName, kaleoClassPK, blocking,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param blocking the blocking
	 * @param start the lower bound of the range of kaleo timers
	 * @param end the upper bound of the range of kaleo timers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo timers
	 */
	@Override
	public List<KaleoTimer> findByKCN_KCPK_Blocking(String kaleoClassName,
		long kaleoClassPK, boolean blocking, int start, int end,
		OrderByComparator<KaleoTimer> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_BLOCKING;
			finderArgs = new Object[] { kaleoClassName, kaleoClassPK, blocking };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_KCN_KCPK_BLOCKING;
			finderArgs = new Object[] {
					kaleoClassName, kaleoClassPK, blocking,
					
					start, end, orderByComparator
				};
		}

		List<KaleoTimer> list = (List<KaleoTimer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoTimer kaleoTimer : list) {
				if (!Validator.equals(kaleoClassName,
							kaleoTimer.getKaleoClassName()) ||
						(kaleoClassPK != kaleoTimer.getKaleoClassPK()) ||
						(blocking != kaleoTimer.getBlocking())) {
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

			query.append(_SQL_SELECT_KALEOTIMER_WHERE);

			boolean bindKaleoClassName = false;

			if (kaleoClassName == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSNAME_1);
			}
			else if (kaleoClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSNAME_3);
			}
			else {
				bindKaleoClassName = true;

				query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSNAME_2);
			}

			query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSPK_2);

			query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_BLOCKING_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoTimerModelImpl.ORDER_BY_JPQL);
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

				qPos.add(blocking);

				if (!pagination) {
					list = (List<KaleoTimer>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoTimer>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first kaleo timer in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param blocking the blocking
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo timer
	 * @throws NoSuchTimerException if a matching kaleo timer could not be found
	 */
	@Override
	public KaleoTimer findByKCN_KCPK_Blocking_First(String kaleoClassName,
		long kaleoClassPK, boolean blocking,
		OrderByComparator<KaleoTimer> orderByComparator)
		throws NoSuchTimerException {
		KaleoTimer kaleoTimer = fetchByKCN_KCPK_Blocking_First(kaleoClassName,
				kaleoClassPK, blocking, orderByComparator);

		if (kaleoTimer != null) {
			return kaleoTimer;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoClassName=");
		msg.append(kaleoClassName);

		msg.append(", kaleoClassPK=");
		msg.append(kaleoClassPK);

		msg.append(", blocking=");
		msg.append(blocking);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTimerException(msg.toString());
	}

	/**
	 * Returns the first kaleo timer in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param blocking the blocking
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo timer, or <code>null</code> if a matching kaleo timer could not be found
	 */
	@Override
	public KaleoTimer fetchByKCN_KCPK_Blocking_First(String kaleoClassName,
		long kaleoClassPK, boolean blocking,
		OrderByComparator<KaleoTimer> orderByComparator) {
		List<KaleoTimer> list = findByKCN_KCPK_Blocking(kaleoClassName,
				kaleoClassPK, blocking, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo timer in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param blocking the blocking
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo timer
	 * @throws NoSuchTimerException if a matching kaleo timer could not be found
	 */
	@Override
	public KaleoTimer findByKCN_KCPK_Blocking_Last(String kaleoClassName,
		long kaleoClassPK, boolean blocking,
		OrderByComparator<KaleoTimer> orderByComparator)
		throws NoSuchTimerException {
		KaleoTimer kaleoTimer = fetchByKCN_KCPK_Blocking_Last(kaleoClassName,
				kaleoClassPK, blocking, orderByComparator);

		if (kaleoTimer != null) {
			return kaleoTimer;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoClassName=");
		msg.append(kaleoClassName);

		msg.append(", kaleoClassPK=");
		msg.append(kaleoClassPK);

		msg.append(", blocking=");
		msg.append(blocking);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTimerException(msg.toString());
	}

	/**
	 * Returns the last kaleo timer in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param blocking the blocking
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo timer, or <code>null</code> if a matching kaleo timer could not be found
	 */
	@Override
	public KaleoTimer fetchByKCN_KCPK_Blocking_Last(String kaleoClassName,
		long kaleoClassPK, boolean blocking,
		OrderByComparator<KaleoTimer> orderByComparator) {
		int count = countByKCN_KCPK_Blocking(kaleoClassName, kaleoClassPK,
				blocking);

		if (count == 0) {
			return null;
		}

		List<KaleoTimer> list = findByKCN_KCPK_Blocking(kaleoClassName,
				kaleoClassPK, blocking, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo timers before and after the current kaleo timer in the ordered set where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	 *
	 * @param kaleoTimerId the primary key of the current kaleo timer
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param blocking the blocking
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo timer
	 * @throws NoSuchTimerException if a kaleo timer with the primary key could not be found
	 */
	@Override
	public KaleoTimer[] findByKCN_KCPK_Blocking_PrevAndNext(long kaleoTimerId,
		String kaleoClassName, long kaleoClassPK, boolean blocking,
		OrderByComparator<KaleoTimer> orderByComparator)
		throws NoSuchTimerException {
		KaleoTimer kaleoTimer = findByPrimaryKey(kaleoTimerId);

		Session session = null;

		try {
			session = openSession();

			KaleoTimer[] array = new KaleoTimerImpl[3];

			array[0] = getByKCN_KCPK_Blocking_PrevAndNext(session, kaleoTimer,
					kaleoClassName, kaleoClassPK, blocking, orderByComparator,
					true);

			array[1] = kaleoTimer;

			array[2] = getByKCN_KCPK_Blocking_PrevAndNext(session, kaleoTimer,
					kaleoClassName, kaleoClassPK, blocking, orderByComparator,
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

	protected KaleoTimer getByKCN_KCPK_Blocking_PrevAndNext(Session session,
		KaleoTimer kaleoTimer, String kaleoClassName, long kaleoClassPK,
		boolean blocking, OrderByComparator<KaleoTimer> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTIMER_WHERE);

		boolean bindKaleoClassName = false;

		if (kaleoClassName == null) {
			query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSNAME_1);
		}
		else if (kaleoClassName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSNAME_3);
		}
		else {
			bindKaleoClassName = true;

			query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSNAME_2);
		}

		query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSPK_2);

		query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_BLOCKING_2);

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
			query.append(KaleoTimerModelImpl.ORDER_BY_JPQL);
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

		qPos.add(blocking);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoTimer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTimer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63; from the database.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param blocking the blocking
	 */
	@Override
	public void removeByKCN_KCPK_Blocking(String kaleoClassName,
		long kaleoClassPK, boolean blocking) {
		for (KaleoTimer kaleoTimer : findByKCN_KCPK_Blocking(kaleoClassName,
				kaleoClassPK, blocking, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(kaleoTimer);
		}
	}

	/**
	 * Returns the number of kaleo timers where kaleoClassName = &#63; and kaleoClassPK = &#63; and blocking = &#63;.
	 *
	 * @param kaleoClassName the kaleo class name
	 * @param kaleoClassPK the kaleo class p k
	 * @param blocking the blocking
	 * @return the number of matching kaleo timers
	 */
	@Override
	public int countByKCN_KCPK_Blocking(String kaleoClassName,
		long kaleoClassPK, boolean blocking) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KCN_KCPK_BLOCKING;

		Object[] finderArgs = new Object[] {
				kaleoClassName, kaleoClassPK, blocking
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KALEOTIMER_WHERE);

			boolean bindKaleoClassName = false;

			if (kaleoClassName == null) {
				query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSNAME_1);
			}
			else if (kaleoClassName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSNAME_3);
			}
			else {
				bindKaleoClassName = true;

				query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSNAME_2);
			}

			query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSPK_2);

			query.append(_FINDER_COLUMN_KCN_KCPK_BLOCKING_BLOCKING_2);

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

				qPos.add(blocking);

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

	private static final String _FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSNAME_1 =
		"kaleoTimer.kaleoClassName IS NULL AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSNAME_2 =
		"kaleoTimer.kaleoClassName = ? AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSNAME_3 =
		"(kaleoTimer.kaleoClassName IS NULL OR kaleoTimer.kaleoClassName = '') AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_BLOCKING_KALEOCLASSPK_2 = "kaleoTimer.kaleoClassPK = ? AND ";
	private static final String _FINDER_COLUMN_KCN_KCPK_BLOCKING_BLOCKING_2 = "kaleoTimer.blocking = ?";

	public KaleoTimerPersistenceImpl() {
		setModelClass(KaleoTimer.class);
	}

	/**
	 * Caches the kaleo timer in the entity cache if it is enabled.
	 *
	 * @param kaleoTimer the kaleo timer
	 */
	@Override
	public void cacheResult(KaleoTimer kaleoTimer) {
		EntityCacheUtil.putResult(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerImpl.class, kaleoTimer.getPrimaryKey(), kaleoTimer);

		kaleoTimer.resetOriginalValues();
	}

	/**
	 * Caches the kaleo timers in the entity cache if it is enabled.
	 *
	 * @param kaleoTimers the kaleo timers
	 */
	@Override
	public void cacheResult(List<KaleoTimer> kaleoTimers) {
		for (KaleoTimer kaleoTimer : kaleoTimers) {
			if (EntityCacheUtil.getResult(
						KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTimerImpl.class, kaleoTimer.getPrimaryKey()) == null) {
				cacheResult(kaleoTimer);
			}
			else {
				kaleoTimer.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all kaleo timers.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(KaleoTimerImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the kaleo timer.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KaleoTimer kaleoTimer) {
		EntityCacheUtil.removeResult(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerImpl.class, kaleoTimer.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<KaleoTimer> kaleoTimers) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (KaleoTimer kaleoTimer : kaleoTimers) {
			EntityCacheUtil.removeResult(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
				KaleoTimerImpl.class, kaleoTimer.getPrimaryKey());
		}
	}

	/**
	 * Creates a new kaleo timer with the primary key. Does not add the kaleo timer to the database.
	 *
	 * @param kaleoTimerId the primary key for the new kaleo timer
	 * @return the new kaleo timer
	 */
	@Override
	public KaleoTimer create(long kaleoTimerId) {
		KaleoTimer kaleoTimer = new KaleoTimerImpl();

		kaleoTimer.setNew(true);
		kaleoTimer.setPrimaryKey(kaleoTimerId);

		return kaleoTimer;
	}

	/**
	 * Removes the kaleo timer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTimerId the primary key of the kaleo timer
	 * @return the kaleo timer that was removed
	 * @throws NoSuchTimerException if a kaleo timer with the primary key could not be found
	 */
	@Override
	public KaleoTimer remove(long kaleoTimerId) throws NoSuchTimerException {
		return remove((Serializable)kaleoTimerId);
	}

	/**
	 * Removes the kaleo timer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo timer
	 * @return the kaleo timer that was removed
	 * @throws NoSuchTimerException if a kaleo timer with the primary key could not be found
	 */
	@Override
	public KaleoTimer remove(Serializable primaryKey)
		throws NoSuchTimerException {
		Session session = null;

		try {
			session = openSession();

			KaleoTimer kaleoTimer = (KaleoTimer)session.get(KaleoTimerImpl.class,
					primaryKey);

			if (kaleoTimer == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTimerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(kaleoTimer);
		}
		catch (NoSuchTimerException nsee) {
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
	protected KaleoTimer removeImpl(KaleoTimer kaleoTimer) {
		kaleoTimer = toUnwrappedModel(kaleoTimer);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(kaleoTimer)) {
				kaleoTimer = (KaleoTimer)session.get(KaleoTimerImpl.class,
						kaleoTimer.getPrimaryKeyObj());
			}

			if (kaleoTimer != null) {
				session.delete(kaleoTimer);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (kaleoTimer != null) {
			clearCache(kaleoTimer);
		}

		return kaleoTimer;
	}

	@Override
	public KaleoTimer updateImpl(KaleoTimer kaleoTimer) {
		kaleoTimer = toUnwrappedModel(kaleoTimer);

		boolean isNew = kaleoTimer.isNew();

		KaleoTimerModelImpl kaleoTimerModelImpl = (KaleoTimerModelImpl)kaleoTimer;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (kaleoTimer.getCreateDate() == null)) {
			if (serviceContext == null) {
				kaleoTimer.setCreateDate(now);
			}
			else {
				kaleoTimer.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!kaleoTimerModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				kaleoTimer.setModifiedDate(now);
			}
			else {
				kaleoTimer.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (kaleoTimer.isNew()) {
				session.save(kaleoTimer);

				kaleoTimer.setNew(false);
			}
			else {
				session.merge(kaleoTimer);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !KaleoTimerModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((kaleoTimerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoTimerModelImpl.getOriginalKaleoClassName(),
						kaleoTimerModelImpl.getOriginalKaleoClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KCN_KCPK, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK,
					args);

				args = new Object[] {
						kaleoTimerModelImpl.getKaleoClassName(),
						kaleoTimerModelImpl.getKaleoClassPK()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KCN_KCPK, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK,
					args);
			}

			if ((kaleoTimerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_BLOCKING.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoTimerModelImpl.getOriginalKaleoClassName(),
						kaleoTimerModelImpl.getOriginalKaleoClassPK(),
						kaleoTimerModelImpl.getOriginalBlocking()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KCN_KCPK_BLOCKING,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_BLOCKING,
					args);

				args = new Object[] {
						kaleoTimerModelImpl.getKaleoClassName(),
						kaleoTimerModelImpl.getKaleoClassPK(),
						kaleoTimerModelImpl.getBlocking()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KCN_KCPK_BLOCKING,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KCN_KCPK_BLOCKING,
					args);
			}
		}

		EntityCacheUtil.putResult(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTimerImpl.class, kaleoTimer.getPrimaryKey(), kaleoTimer, false);

		kaleoTimer.resetOriginalValues();

		return kaleoTimer;
	}

	protected KaleoTimer toUnwrappedModel(KaleoTimer kaleoTimer) {
		if (kaleoTimer instanceof KaleoTimerImpl) {
			return kaleoTimer;
		}

		KaleoTimerImpl kaleoTimerImpl = new KaleoTimerImpl();

		kaleoTimerImpl.setNew(kaleoTimer.isNew());
		kaleoTimerImpl.setPrimaryKey(kaleoTimer.getPrimaryKey());

		kaleoTimerImpl.setKaleoTimerId(kaleoTimer.getKaleoTimerId());
		kaleoTimerImpl.setGroupId(kaleoTimer.getGroupId());
		kaleoTimerImpl.setCompanyId(kaleoTimer.getCompanyId());
		kaleoTimerImpl.setUserId(kaleoTimer.getUserId());
		kaleoTimerImpl.setUserName(kaleoTimer.getUserName());
		kaleoTimerImpl.setCreateDate(kaleoTimer.getCreateDate());
		kaleoTimerImpl.setModifiedDate(kaleoTimer.getModifiedDate());
		kaleoTimerImpl.setKaleoClassName(kaleoTimer.getKaleoClassName());
		kaleoTimerImpl.setKaleoClassPK(kaleoTimer.getKaleoClassPK());
		kaleoTimerImpl.setKaleoDefinitionId(kaleoTimer.getKaleoDefinitionId());
		kaleoTimerImpl.setName(kaleoTimer.getName());
		kaleoTimerImpl.setBlocking(kaleoTimer.isBlocking());
		kaleoTimerImpl.setDescription(kaleoTimer.getDescription());
		kaleoTimerImpl.setDuration(kaleoTimer.getDuration());
		kaleoTimerImpl.setScale(kaleoTimer.getScale());
		kaleoTimerImpl.setRecurrenceDuration(kaleoTimer.getRecurrenceDuration());
		kaleoTimerImpl.setRecurrenceScale(kaleoTimer.getRecurrenceScale());

		return kaleoTimerImpl;
	}

	/**
	 * Returns the kaleo timer with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo timer
	 * @return the kaleo timer
	 * @throws NoSuchTimerException if a kaleo timer with the primary key could not be found
	 */
	@Override
	public KaleoTimer findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTimerException {
		KaleoTimer kaleoTimer = fetchByPrimaryKey(primaryKey);

		if (kaleoTimer == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTimerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return kaleoTimer;
	}

	/**
	 * Returns the kaleo timer with the primary key or throws a {@link NoSuchTimerException} if it could not be found.
	 *
	 * @param kaleoTimerId the primary key of the kaleo timer
	 * @return the kaleo timer
	 * @throws NoSuchTimerException if a kaleo timer with the primary key could not be found
	 */
	@Override
	public KaleoTimer findByPrimaryKey(long kaleoTimerId)
		throws NoSuchTimerException {
		return findByPrimaryKey((Serializable)kaleoTimerId);
	}

	/**
	 * Returns the kaleo timer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo timer
	 * @return the kaleo timer, or <code>null</code> if a kaleo timer with the primary key could not be found
	 */
	@Override
	public KaleoTimer fetchByPrimaryKey(Serializable primaryKey) {
		KaleoTimer kaleoTimer = (KaleoTimer)EntityCacheUtil.getResult(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
				KaleoTimerImpl.class, primaryKey);

		if (kaleoTimer == _nullKaleoTimer) {
			return null;
		}

		if (kaleoTimer == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoTimer = (KaleoTimer)session.get(KaleoTimerImpl.class,
						primaryKey);

				if (kaleoTimer != null) {
					cacheResult(kaleoTimer);
				}
				else {
					EntityCacheUtil.putResult(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTimerImpl.class, primaryKey, _nullKaleoTimer);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
					KaleoTimerImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return kaleoTimer;
	}

	/**
	 * Returns the kaleo timer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoTimerId the primary key of the kaleo timer
	 * @return the kaleo timer, or <code>null</code> if a kaleo timer with the primary key could not be found
	 */
	@Override
	public KaleoTimer fetchByPrimaryKey(long kaleoTimerId) {
		return fetchByPrimaryKey((Serializable)kaleoTimerId);
	}

	@Override
	public Map<Serializable, KaleoTimer> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, KaleoTimer> map = new HashMap<Serializable, KaleoTimer>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			KaleoTimer kaleoTimer = fetchByPrimaryKey(primaryKey);

			if (kaleoTimer != null) {
				map.put(primaryKey, kaleoTimer);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			KaleoTimer kaleoTimer = (KaleoTimer)EntityCacheUtil.getResult(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
					KaleoTimerImpl.class, primaryKey);

			if (kaleoTimer == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, kaleoTimer);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_KALEOTIMER_WHERE_PKS_IN);

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

			for (KaleoTimer kaleoTimer : (List<KaleoTimer>)q.list()) {
				map.put(kaleoTimer.getPrimaryKeyObj(), kaleoTimer);

				cacheResult(kaleoTimer);

				uncachedPrimaryKeys.remove(kaleoTimer.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(KaleoTimerModelImpl.ENTITY_CACHE_ENABLED,
					KaleoTimerImpl.class, primaryKey, _nullKaleoTimer);
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
	 * Returns all the kaleo timers.
	 *
	 * @return the kaleo timers
	 */
	@Override
	public List<KaleoTimer> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo timers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo timers
	 * @param end the upper bound of the range of kaleo timers (not inclusive)
	 * @return the range of kaleo timers
	 */
	@Override
	public List<KaleoTimer> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo timers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KaleoTimerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo timers
	 * @param end the upper bound of the range of kaleo timers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo timers
	 */
	@Override
	public List<KaleoTimer> findAll(int start, int end,
		OrderByComparator<KaleoTimer> orderByComparator) {
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

		List<KaleoTimer> list = (List<KaleoTimer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KALEOTIMER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEOTIMER;

				if (pagination) {
					sql = sql.concat(KaleoTimerModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<KaleoTimer>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KaleoTimer>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the kaleo timers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (KaleoTimer kaleoTimer : findAll()) {
			remove(kaleoTimer);
		}
	}

	/**
	 * Returns the number of kaleo timers.
	 *
	 * @return the number of kaleo timers
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KALEOTIMER);

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
		return KaleoTimerModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the kaleo timer persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(KaleoTimerImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_KALEOTIMER = "SELECT kaleoTimer FROM KaleoTimer kaleoTimer";
	private static final String _SQL_SELECT_KALEOTIMER_WHERE_PKS_IN = "SELECT kaleoTimer FROM KaleoTimer kaleoTimer WHERE kaleoTimerId IN (";
	private static final String _SQL_SELECT_KALEOTIMER_WHERE = "SELECT kaleoTimer FROM KaleoTimer kaleoTimer WHERE ";
	private static final String _SQL_COUNT_KALEOTIMER = "SELECT COUNT(kaleoTimer) FROM KaleoTimer kaleoTimer";
	private static final String _SQL_COUNT_KALEOTIMER_WHERE = "SELECT COUNT(kaleoTimer) FROM KaleoTimer kaleoTimer WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoTimer.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoTimer exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoTimer exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(KaleoTimerPersistenceImpl.class);
	private static final KaleoTimer _nullKaleoTimer = new KaleoTimerImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<KaleoTimer> toCacheModel() {
				return _nullKaleoTimerCacheModel;
			}
		};

	private static final CacheModel<KaleoTimer> _nullKaleoTimerCacheModel = new CacheModel<KaleoTimer>() {
			@Override
			public KaleoTimer toEntityModel() {
				return _nullKaleoTimer;
			}
		};
}