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

package com.liferay.wsrp.service.persistence;

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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.wsrp.NoSuchConsumerPortletException;
import com.liferay.wsrp.model.WSRPConsumerPortlet;
import com.liferay.wsrp.model.impl.WSRPConsumerPortletImpl;
import com.liferay.wsrp.model.impl.WSRPConsumerPortletModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the w s r p consumer portlet service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumerPortletPersistence
 * @see WSRPConsumerPortletUtil
 * @generated
 */
public class WSRPConsumerPortletPersistenceImpl extends BasePersistenceImpl<WSRPConsumerPortlet>
	implements WSRPConsumerPortletPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WSRPConsumerPortletUtil} to access the w s r p consumer portlet persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WSRPConsumerPortletImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletModelImpl.FINDER_CACHE_ENABLED,
			WSRPConsumerPortletImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletModelImpl.FINDER_CACHE_ENABLED,
			WSRPConsumerPortletImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletModelImpl.FINDER_CACHE_ENABLED,
			WSRPConsumerPortletImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletModelImpl.FINDER_CACHE_ENABLED,
			WSRPConsumerPortletImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			WSRPConsumerPortletModelImpl.UUID_COLUMN_BITMASK |
			WSRPConsumerPortletModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the w s r p consumer portlets where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching w s r p consumer portlets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WSRPConsumerPortlet> findByUuid(String uuid)
		throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the w s r p consumer portlets where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wsrp.model.impl.WSRPConsumerPortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of w s r p consumer portlets
	 * @param end the upper bound of the range of w s r p consumer portlets (not inclusive)
	 * @return the range of matching w s r p consumer portlets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WSRPConsumerPortlet> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the w s r p consumer portlets where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wsrp.model.impl.WSRPConsumerPortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of w s r p consumer portlets
	 * @param end the upper bound of the range of w s r p consumer portlets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching w s r p consumer portlets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WSRPConsumerPortlet> findByUuid(String uuid, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<WSRPConsumerPortlet> list = (List<WSRPConsumerPortlet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WSRPConsumerPortlet wsrpConsumerPortlet : list) {
				if (!Validator.equals(uuid, wsrpConsumerPortlet.getUuid())) {
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

			query.append(_SQL_SELECT_WSRPCONSUMERPORTLET_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WSRPConsumerPortletModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<WSRPConsumerPortlet>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<WSRPConsumerPortlet>(list);
				}
				else {
					list = (List<WSRPConsumerPortlet>)QueryUtil.list(q,
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
	 * Returns the first w s r p consumer portlet in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching w s r p consumer portlet
	 * @throws com.liferay.wsrp.NoSuchConsumerPortletException if a matching w s r p consumer portlet could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumerPortlet findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchConsumerPortletException, SystemException {
		WSRPConsumerPortlet wsrpConsumerPortlet = fetchByUuid_First(uuid,
				orderByComparator);

		if (wsrpConsumerPortlet != null) {
			return wsrpConsumerPortlet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConsumerPortletException(msg.toString());
	}

	/**
	 * Returns the first w s r p consumer portlet in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumerPortlet fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<WSRPConsumerPortlet> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last w s r p consumer portlet in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching w s r p consumer portlet
	 * @throws com.liferay.wsrp.NoSuchConsumerPortletException if a matching w s r p consumer portlet could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumerPortlet findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchConsumerPortletException, SystemException {
		WSRPConsumerPortlet wsrpConsumerPortlet = fetchByUuid_Last(uuid,
				orderByComparator);

		if (wsrpConsumerPortlet != null) {
			return wsrpConsumerPortlet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConsumerPortletException(msg.toString());
	}

	/**
	 * Returns the last w s r p consumer portlet in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumerPortlet fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<WSRPConsumerPortlet> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the w s r p consumer portlets before and after the current w s r p consumer portlet in the ordered set where uuid = &#63;.
	 *
	 * @param wsrpConsumerPortletId the primary key of the current w s r p consumer portlet
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next w s r p consumer portlet
	 * @throws com.liferay.wsrp.NoSuchConsumerPortletException if a w s r p consumer portlet with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumerPortlet[] findByUuid_PrevAndNext(
		long wsrpConsumerPortletId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchConsumerPortletException, SystemException {
		WSRPConsumerPortlet wsrpConsumerPortlet = findByPrimaryKey(wsrpConsumerPortletId);

		Session session = null;

		try {
			session = openSession();

			WSRPConsumerPortlet[] array = new WSRPConsumerPortletImpl[3];

			array[0] = getByUuid_PrevAndNext(session, wsrpConsumerPortlet,
					uuid, orderByComparator, true);

			array[1] = wsrpConsumerPortlet;

			array[2] = getByUuid_PrevAndNext(session, wsrpConsumerPortlet,
					uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WSRPConsumerPortlet getByUuid_PrevAndNext(Session session,
		WSRPConsumerPortlet wsrpConsumerPortlet, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WSRPCONSUMERPORTLET_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
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
			query.append(WSRPConsumerPortletModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(wsrpConsumerPortlet);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WSRPConsumerPortlet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the w s r p consumer portlets where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid(String uuid) throws SystemException {
		for (WSRPConsumerPortlet wsrpConsumerPortlet : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(wsrpConsumerPortlet);
		}
	}

	/**
	 * Returns the number of w s r p consumer portlets where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching w s r p consumer portlets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUuid(String uuid) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WSRPCONSUMERPORTLET_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "wsrpConsumerPortlet.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "wsrpConsumerPortlet.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(wsrpConsumerPortlet.uuid IS NULL OR wsrpConsumerPortlet.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletModelImpl.FINDER_CACHE_ENABLED,
			WSRPConsumerPortletImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletModelImpl.FINDER_CACHE_ENABLED,
			WSRPConsumerPortletImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			WSRPConsumerPortletModelImpl.UUID_COLUMN_BITMASK |
			WSRPConsumerPortletModelImpl.COMPANYID_COLUMN_BITMASK |
			WSRPConsumerPortletModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the w s r p consumer portlets where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching w s r p consumer portlets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WSRPConsumerPortlet> findByUuid_C(String uuid, long companyId)
		throws SystemException {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the w s r p consumer portlets where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wsrp.model.impl.WSRPConsumerPortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of w s r p consumer portlets
	 * @param end the upper bound of the range of w s r p consumer portlets (not inclusive)
	 * @return the range of matching w s r p consumer portlets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WSRPConsumerPortlet> findByUuid_C(String uuid, long companyId,
		int start, int end) throws SystemException {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the w s r p consumer portlets where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wsrp.model.impl.WSRPConsumerPortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of w s r p consumer portlets
	 * @param end the upper bound of the range of w s r p consumer portlets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching w s r p consumer portlets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WSRPConsumerPortlet> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<WSRPConsumerPortlet> list = (List<WSRPConsumerPortlet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WSRPConsumerPortlet wsrpConsumerPortlet : list) {
				if (!Validator.equals(uuid, wsrpConsumerPortlet.getUuid()) ||
						(companyId != wsrpConsumerPortlet.getCompanyId())) {
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

			query.append(_SQL_SELECT_WSRPCONSUMERPORTLET_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WSRPConsumerPortletModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<WSRPConsumerPortlet>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<WSRPConsumerPortlet>(list);
				}
				else {
					list = (List<WSRPConsumerPortlet>)QueryUtil.list(q,
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
	 * Returns the first w s r p consumer portlet in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching w s r p consumer portlet
	 * @throws com.liferay.wsrp.NoSuchConsumerPortletException if a matching w s r p consumer portlet could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumerPortlet findByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchConsumerPortletException, SystemException {
		WSRPConsumerPortlet wsrpConsumerPortlet = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (wsrpConsumerPortlet != null) {
			return wsrpConsumerPortlet;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConsumerPortletException(msg.toString());
	}

	/**
	 * Returns the first w s r p consumer portlet in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumerPortlet fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<WSRPConsumerPortlet> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last w s r p consumer portlet in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching w s r p consumer portlet
	 * @throws com.liferay.wsrp.NoSuchConsumerPortletException if a matching w s r p consumer portlet could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumerPortlet findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchConsumerPortletException, SystemException {
		WSRPConsumerPortlet wsrpConsumerPortlet = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (wsrpConsumerPortlet != null) {
			return wsrpConsumerPortlet;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConsumerPortletException(msg.toString());
	}

	/**
	 * Returns the last w s r p consumer portlet in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumerPortlet fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<WSRPConsumerPortlet> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the w s r p consumer portlets before and after the current w s r p consumer portlet in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param wsrpConsumerPortletId the primary key of the current w s r p consumer portlet
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next w s r p consumer portlet
	 * @throws com.liferay.wsrp.NoSuchConsumerPortletException if a w s r p consumer portlet with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumerPortlet[] findByUuid_C_PrevAndNext(
		long wsrpConsumerPortletId, String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchConsumerPortletException, SystemException {
		WSRPConsumerPortlet wsrpConsumerPortlet = findByPrimaryKey(wsrpConsumerPortletId);

		Session session = null;

		try {
			session = openSession();

			WSRPConsumerPortlet[] array = new WSRPConsumerPortletImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, wsrpConsumerPortlet,
					uuid, companyId, orderByComparator, true);

			array[1] = wsrpConsumerPortlet;

			array[2] = getByUuid_C_PrevAndNext(session, wsrpConsumerPortlet,
					uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WSRPConsumerPortlet getByUuid_C_PrevAndNext(Session session,
		WSRPConsumerPortlet wsrpConsumerPortlet, String uuid, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WSRPCONSUMERPORTLET_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

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
			query.append(WSRPConsumerPortletModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(wsrpConsumerPortlet);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WSRPConsumerPortlet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the w s r p consumer portlets where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId)
		throws SystemException {
		for (WSRPConsumerPortlet wsrpConsumerPortlet : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(wsrpConsumerPortlet);
		}
	}

	/**
	 * Returns the number of w s r p consumer portlets where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching w s r p consumer portlets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WSRPCONSUMERPORTLET_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "wsrpConsumerPortlet.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "wsrpConsumerPortlet.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(wsrpConsumerPortlet.uuid IS NULL OR wsrpConsumerPortlet.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "wsrpConsumerPortlet.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_WSRPCONSUMERID =
		new FinderPath(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletModelImpl.FINDER_CACHE_ENABLED,
			WSRPConsumerPortletImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByWsrpConsumerId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_WSRPCONSUMERID =
		new FinderPath(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletModelImpl.FINDER_CACHE_ENABLED,
			WSRPConsumerPortletImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByWsrpConsumerId",
			new String[] { Long.class.getName() },
			WSRPConsumerPortletModelImpl.WSRPCONSUMERID_COLUMN_BITMASK |
			WSRPConsumerPortletModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_WSRPCONSUMERID = new FinderPath(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByWsrpConsumerId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the w s r p consumer portlets where wsrpConsumerId = &#63;.
	 *
	 * @param wsrpConsumerId the wsrp consumer ID
	 * @return the matching w s r p consumer portlets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WSRPConsumerPortlet> findByWsrpConsumerId(long wsrpConsumerId)
		throws SystemException {
		return findByWsrpConsumerId(wsrpConsumerId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the w s r p consumer portlets where wsrpConsumerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wsrp.model.impl.WSRPConsumerPortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param wsrpConsumerId the wsrp consumer ID
	 * @param start the lower bound of the range of w s r p consumer portlets
	 * @param end the upper bound of the range of w s r p consumer portlets (not inclusive)
	 * @return the range of matching w s r p consumer portlets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WSRPConsumerPortlet> findByWsrpConsumerId(long wsrpConsumerId,
		int start, int end) throws SystemException {
		return findByWsrpConsumerId(wsrpConsumerId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the w s r p consumer portlets where wsrpConsumerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wsrp.model.impl.WSRPConsumerPortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param wsrpConsumerId the wsrp consumer ID
	 * @param start the lower bound of the range of w s r p consumer portlets
	 * @param end the upper bound of the range of w s r p consumer portlets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching w s r p consumer portlets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WSRPConsumerPortlet> findByWsrpConsumerId(long wsrpConsumerId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_WSRPCONSUMERID;
			finderArgs = new Object[] { wsrpConsumerId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_WSRPCONSUMERID;
			finderArgs = new Object[] {
					wsrpConsumerId,
					
					start, end, orderByComparator
				};
		}

		List<WSRPConsumerPortlet> list = (List<WSRPConsumerPortlet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WSRPConsumerPortlet wsrpConsumerPortlet : list) {
				if ((wsrpConsumerId != wsrpConsumerPortlet.getWsrpConsumerId())) {
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

			query.append(_SQL_SELECT_WSRPCONSUMERPORTLET_WHERE);

			query.append(_FINDER_COLUMN_WSRPCONSUMERID_WSRPCONSUMERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WSRPConsumerPortletModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(wsrpConsumerId);

				if (!pagination) {
					list = (List<WSRPConsumerPortlet>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<WSRPConsumerPortlet>(list);
				}
				else {
					list = (List<WSRPConsumerPortlet>)QueryUtil.list(q,
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
	 * Returns the first w s r p consumer portlet in the ordered set where wsrpConsumerId = &#63;.
	 *
	 * @param wsrpConsumerId the wsrp consumer ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching w s r p consumer portlet
	 * @throws com.liferay.wsrp.NoSuchConsumerPortletException if a matching w s r p consumer portlet could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumerPortlet findByWsrpConsumerId_First(long wsrpConsumerId,
		OrderByComparator orderByComparator)
		throws NoSuchConsumerPortletException, SystemException {
		WSRPConsumerPortlet wsrpConsumerPortlet = fetchByWsrpConsumerId_First(wsrpConsumerId,
				orderByComparator);

		if (wsrpConsumerPortlet != null) {
			return wsrpConsumerPortlet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("wsrpConsumerId=");
		msg.append(wsrpConsumerId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConsumerPortletException(msg.toString());
	}

	/**
	 * Returns the first w s r p consumer portlet in the ordered set where wsrpConsumerId = &#63;.
	 *
	 * @param wsrpConsumerId the wsrp consumer ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumerPortlet fetchByWsrpConsumerId_First(
		long wsrpConsumerId, OrderByComparator orderByComparator)
		throws SystemException {
		List<WSRPConsumerPortlet> list = findByWsrpConsumerId(wsrpConsumerId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last w s r p consumer portlet in the ordered set where wsrpConsumerId = &#63;.
	 *
	 * @param wsrpConsumerId the wsrp consumer ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching w s r p consumer portlet
	 * @throws com.liferay.wsrp.NoSuchConsumerPortletException if a matching w s r p consumer portlet could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumerPortlet findByWsrpConsumerId_Last(long wsrpConsumerId,
		OrderByComparator orderByComparator)
		throws NoSuchConsumerPortletException, SystemException {
		WSRPConsumerPortlet wsrpConsumerPortlet = fetchByWsrpConsumerId_Last(wsrpConsumerId,
				orderByComparator);

		if (wsrpConsumerPortlet != null) {
			return wsrpConsumerPortlet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("wsrpConsumerId=");
		msg.append(wsrpConsumerId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConsumerPortletException(msg.toString());
	}

	/**
	 * Returns the last w s r p consumer portlet in the ordered set where wsrpConsumerId = &#63;.
	 *
	 * @param wsrpConsumerId the wsrp consumer ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumerPortlet fetchByWsrpConsumerId_Last(long wsrpConsumerId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByWsrpConsumerId(wsrpConsumerId);

		if (count == 0) {
			return null;
		}

		List<WSRPConsumerPortlet> list = findByWsrpConsumerId(wsrpConsumerId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the w s r p consumer portlets before and after the current w s r p consumer portlet in the ordered set where wsrpConsumerId = &#63;.
	 *
	 * @param wsrpConsumerPortletId the primary key of the current w s r p consumer portlet
	 * @param wsrpConsumerId the wsrp consumer ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next w s r p consumer portlet
	 * @throws com.liferay.wsrp.NoSuchConsumerPortletException if a w s r p consumer portlet with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumerPortlet[] findByWsrpConsumerId_PrevAndNext(
		long wsrpConsumerPortletId, long wsrpConsumerId,
		OrderByComparator orderByComparator)
		throws NoSuchConsumerPortletException, SystemException {
		WSRPConsumerPortlet wsrpConsumerPortlet = findByPrimaryKey(wsrpConsumerPortletId);

		Session session = null;

		try {
			session = openSession();

			WSRPConsumerPortlet[] array = new WSRPConsumerPortletImpl[3];

			array[0] = getByWsrpConsumerId_PrevAndNext(session,
					wsrpConsumerPortlet, wsrpConsumerId, orderByComparator, true);

			array[1] = wsrpConsumerPortlet;

			array[2] = getByWsrpConsumerId_PrevAndNext(session,
					wsrpConsumerPortlet, wsrpConsumerId, orderByComparator,
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

	protected WSRPConsumerPortlet getByWsrpConsumerId_PrevAndNext(
		Session session, WSRPConsumerPortlet wsrpConsumerPortlet,
		long wsrpConsumerId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WSRPCONSUMERPORTLET_WHERE);

		query.append(_FINDER_COLUMN_WSRPCONSUMERID_WSRPCONSUMERID_2);

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
			query.append(WSRPConsumerPortletModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(wsrpConsumerId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(wsrpConsumerPortlet);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WSRPConsumerPortlet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the w s r p consumer portlets where wsrpConsumerId = &#63; from the database.
	 *
	 * @param wsrpConsumerId the wsrp consumer ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByWsrpConsumerId(long wsrpConsumerId)
		throws SystemException {
		for (WSRPConsumerPortlet wsrpConsumerPortlet : findByWsrpConsumerId(
				wsrpConsumerId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(wsrpConsumerPortlet);
		}
	}

	/**
	 * Returns the number of w s r p consumer portlets where wsrpConsumerId = &#63;.
	 *
	 * @param wsrpConsumerId the wsrp consumer ID
	 * @return the number of matching w s r p consumer portlets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByWsrpConsumerId(long wsrpConsumerId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_WSRPCONSUMERID;

		Object[] finderArgs = new Object[] { wsrpConsumerId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WSRPCONSUMERPORTLET_WHERE);

			query.append(_FINDER_COLUMN_WSRPCONSUMERID_WSRPCONSUMERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(wsrpConsumerId);

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

	private static final String _FINDER_COLUMN_WSRPCONSUMERID_WSRPCONSUMERID_2 = "wsrpConsumerPortlet.wsrpConsumerId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_W_P = new FinderPath(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletModelImpl.FINDER_CACHE_ENABLED,
			WSRPConsumerPortletImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByW_P",
			new String[] { Long.class.getName(), String.class.getName() },
			WSRPConsumerPortletModelImpl.WSRPCONSUMERID_COLUMN_BITMASK |
			WSRPConsumerPortletModelImpl.PORTLETHANDLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_W_P = new FinderPath(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByW_P",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the w s r p consumer portlet where wsrpConsumerId = &#63; and portletHandle = &#63; or throws a {@link com.liferay.wsrp.NoSuchConsumerPortletException} if it could not be found.
	 *
	 * @param wsrpConsumerId the wsrp consumer ID
	 * @param portletHandle the portlet handle
	 * @return the matching w s r p consumer portlet
	 * @throws com.liferay.wsrp.NoSuchConsumerPortletException if a matching w s r p consumer portlet could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumerPortlet findByW_P(long wsrpConsumerId,
		String portletHandle)
		throws NoSuchConsumerPortletException, SystemException {
		WSRPConsumerPortlet wsrpConsumerPortlet = fetchByW_P(wsrpConsumerId,
				portletHandle);

		if (wsrpConsumerPortlet == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("wsrpConsumerId=");
			msg.append(wsrpConsumerId);

			msg.append(", portletHandle=");
			msg.append(portletHandle);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchConsumerPortletException(msg.toString());
		}

		return wsrpConsumerPortlet;
	}

	/**
	 * Returns the w s r p consumer portlet where wsrpConsumerId = &#63; and portletHandle = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param wsrpConsumerId the wsrp consumer ID
	 * @param portletHandle the portlet handle
	 * @return the matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumerPortlet fetchByW_P(long wsrpConsumerId,
		String portletHandle) throws SystemException {
		return fetchByW_P(wsrpConsumerId, portletHandle, true);
	}

	/**
	 * Returns the w s r p consumer portlet where wsrpConsumerId = &#63; and portletHandle = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param wsrpConsumerId the wsrp consumer ID
	 * @param portletHandle the portlet handle
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumerPortlet fetchByW_P(long wsrpConsumerId,
		String portletHandle, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { wsrpConsumerId, portletHandle };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_W_P,
					finderArgs, this);
		}

		if (result instanceof WSRPConsumerPortlet) {
			WSRPConsumerPortlet wsrpConsumerPortlet = (WSRPConsumerPortlet)result;

			if ((wsrpConsumerId != wsrpConsumerPortlet.getWsrpConsumerId()) ||
					!Validator.equals(portletHandle,
						wsrpConsumerPortlet.getPortletHandle())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_WSRPCONSUMERPORTLET_WHERE);

			query.append(_FINDER_COLUMN_W_P_WSRPCONSUMERID_2);

			boolean bindPortletHandle = false;

			if (portletHandle == null) {
				query.append(_FINDER_COLUMN_W_P_PORTLETHANDLE_1);
			}
			else if (portletHandle.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_W_P_PORTLETHANDLE_3);
			}
			else {
				bindPortletHandle = true;

				query.append(_FINDER_COLUMN_W_P_PORTLETHANDLE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(wsrpConsumerId);

				if (bindPortletHandle) {
					qPos.add(portletHandle);
				}

				List<WSRPConsumerPortlet> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_W_P,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"WSRPConsumerPortletPersistenceImpl.fetchByW_P(long, String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					WSRPConsumerPortlet wsrpConsumerPortlet = list.get(0);

					result = wsrpConsumerPortlet;

					cacheResult(wsrpConsumerPortlet);

					if ((wsrpConsumerPortlet.getWsrpConsumerId() != wsrpConsumerId) ||
							(wsrpConsumerPortlet.getPortletHandle() == null) ||
							!wsrpConsumerPortlet.getPortletHandle()
													.equals(portletHandle)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_W_P,
							finderArgs, wsrpConsumerPortlet);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_W_P,
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
			return (WSRPConsumerPortlet)result;
		}
	}

	/**
	 * Removes the w s r p consumer portlet where wsrpConsumerId = &#63; and portletHandle = &#63; from the database.
	 *
	 * @param wsrpConsumerId the wsrp consumer ID
	 * @param portletHandle the portlet handle
	 * @return the w s r p consumer portlet that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumerPortlet removeByW_P(long wsrpConsumerId,
		String portletHandle)
		throws NoSuchConsumerPortletException, SystemException {
		WSRPConsumerPortlet wsrpConsumerPortlet = findByW_P(wsrpConsumerId,
				portletHandle);

		return remove(wsrpConsumerPortlet);
	}

	/**
	 * Returns the number of w s r p consumer portlets where wsrpConsumerId = &#63; and portletHandle = &#63;.
	 *
	 * @param wsrpConsumerId the wsrp consumer ID
	 * @param portletHandle the portlet handle
	 * @return the number of matching w s r p consumer portlets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByW_P(long wsrpConsumerId, String portletHandle)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_W_P;

		Object[] finderArgs = new Object[] { wsrpConsumerId, portletHandle };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WSRPCONSUMERPORTLET_WHERE);

			query.append(_FINDER_COLUMN_W_P_WSRPCONSUMERID_2);

			boolean bindPortletHandle = false;

			if (portletHandle == null) {
				query.append(_FINDER_COLUMN_W_P_PORTLETHANDLE_1);
			}
			else if (portletHandle.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_W_P_PORTLETHANDLE_3);
			}
			else {
				bindPortletHandle = true;

				query.append(_FINDER_COLUMN_W_P_PORTLETHANDLE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(wsrpConsumerId);

				if (bindPortletHandle) {
					qPos.add(portletHandle);
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

	private static final String _FINDER_COLUMN_W_P_WSRPCONSUMERID_2 = "wsrpConsumerPortlet.wsrpConsumerId = ? AND ";
	private static final String _FINDER_COLUMN_W_P_PORTLETHANDLE_1 = "wsrpConsumerPortlet.portletHandle IS NULL";
	private static final String _FINDER_COLUMN_W_P_PORTLETHANDLE_2 = "wsrpConsumerPortlet.portletHandle = ?";
	private static final String _FINDER_COLUMN_W_P_PORTLETHANDLE_3 = "(wsrpConsumerPortlet.portletHandle IS NULL OR wsrpConsumerPortlet.portletHandle = '')";

	public WSRPConsumerPortletPersistenceImpl() {
		setModelClass(WSRPConsumerPortlet.class);
	}

	/**
	 * Caches the w s r p consumer portlet in the entity cache if it is enabled.
	 *
	 * @param wsrpConsumerPortlet the w s r p consumer portlet
	 */
	@Override
	public void cacheResult(WSRPConsumerPortlet wsrpConsumerPortlet) {
		EntityCacheUtil.putResult(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletImpl.class, wsrpConsumerPortlet.getPrimaryKey(),
			wsrpConsumerPortlet);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_W_P,
			new Object[] {
				wsrpConsumerPortlet.getWsrpConsumerId(),
				wsrpConsumerPortlet.getPortletHandle()
			}, wsrpConsumerPortlet);

		wsrpConsumerPortlet.resetOriginalValues();
	}

	/**
	 * Caches the w s r p consumer portlets in the entity cache if it is enabled.
	 *
	 * @param wsrpConsumerPortlets the w s r p consumer portlets
	 */
	@Override
	public void cacheResult(List<WSRPConsumerPortlet> wsrpConsumerPortlets) {
		for (WSRPConsumerPortlet wsrpConsumerPortlet : wsrpConsumerPortlets) {
			if (EntityCacheUtil.getResult(
						WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
						WSRPConsumerPortletImpl.class,
						wsrpConsumerPortlet.getPrimaryKey()) == null) {
				cacheResult(wsrpConsumerPortlet);
			}
			else {
				wsrpConsumerPortlet.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all w s r p consumer portlets.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(WSRPConsumerPortletImpl.class.getName());
		}

		EntityCacheUtil.clearCache(WSRPConsumerPortletImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the w s r p consumer portlet.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WSRPConsumerPortlet wsrpConsumerPortlet) {
		EntityCacheUtil.removeResult(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletImpl.class, wsrpConsumerPortlet.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(wsrpConsumerPortlet);
	}

	@Override
	public void clearCache(List<WSRPConsumerPortlet> wsrpConsumerPortlets) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WSRPConsumerPortlet wsrpConsumerPortlet : wsrpConsumerPortlets) {
			EntityCacheUtil.removeResult(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
				WSRPConsumerPortletImpl.class,
				wsrpConsumerPortlet.getPrimaryKey());

			clearUniqueFindersCache(wsrpConsumerPortlet);
		}
	}

	protected void cacheUniqueFindersCache(
		WSRPConsumerPortlet wsrpConsumerPortlet) {
		if (wsrpConsumerPortlet.isNew()) {
			Object[] args = new Object[] {
					wsrpConsumerPortlet.getWsrpConsumerId(),
					wsrpConsumerPortlet.getPortletHandle()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_W_P, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_W_P, args,
				wsrpConsumerPortlet);
		}
		else {
			WSRPConsumerPortletModelImpl wsrpConsumerPortletModelImpl = (WSRPConsumerPortletModelImpl)wsrpConsumerPortlet;

			if ((wsrpConsumerPortletModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_W_P.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						wsrpConsumerPortlet.getWsrpConsumerId(),
						wsrpConsumerPortlet.getPortletHandle()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_W_P, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_W_P, args,
					wsrpConsumerPortlet);
			}
		}
	}

	protected void clearUniqueFindersCache(
		WSRPConsumerPortlet wsrpConsumerPortlet) {
		WSRPConsumerPortletModelImpl wsrpConsumerPortletModelImpl = (WSRPConsumerPortletModelImpl)wsrpConsumerPortlet;

		Object[] args = new Object[] {
				wsrpConsumerPortlet.getWsrpConsumerId(),
				wsrpConsumerPortlet.getPortletHandle()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_W_P, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_W_P, args);

		if ((wsrpConsumerPortletModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_W_P.getColumnBitmask()) != 0) {
			args = new Object[] {
					wsrpConsumerPortletModelImpl.getOriginalWsrpConsumerId(),
					wsrpConsumerPortletModelImpl.getOriginalPortletHandle()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_W_P, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_W_P, args);
		}
	}

	/**
	 * Creates a new w s r p consumer portlet with the primary key. Does not add the w s r p consumer portlet to the database.
	 *
	 * @param wsrpConsumerPortletId the primary key for the new w s r p consumer portlet
	 * @return the new w s r p consumer portlet
	 */
	@Override
	public WSRPConsumerPortlet create(long wsrpConsumerPortletId) {
		WSRPConsumerPortlet wsrpConsumerPortlet = new WSRPConsumerPortletImpl();

		wsrpConsumerPortlet.setNew(true);
		wsrpConsumerPortlet.setPrimaryKey(wsrpConsumerPortletId);

		String uuid = PortalUUIDUtil.generate();

		wsrpConsumerPortlet.setUuid(uuid);

		return wsrpConsumerPortlet;
	}

	/**
	 * Removes the w s r p consumer portlet with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param wsrpConsumerPortletId the primary key of the w s r p consumer portlet
	 * @return the w s r p consumer portlet that was removed
	 * @throws com.liferay.wsrp.NoSuchConsumerPortletException if a w s r p consumer portlet with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumerPortlet remove(long wsrpConsumerPortletId)
		throws NoSuchConsumerPortletException, SystemException {
		return remove((Serializable)wsrpConsumerPortletId);
	}

	/**
	 * Removes the w s r p consumer portlet with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the w s r p consumer portlet
	 * @return the w s r p consumer portlet that was removed
	 * @throws com.liferay.wsrp.NoSuchConsumerPortletException if a w s r p consumer portlet with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumerPortlet remove(Serializable primaryKey)
		throws NoSuchConsumerPortletException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WSRPConsumerPortlet wsrpConsumerPortlet = (WSRPConsumerPortlet)session.get(WSRPConsumerPortletImpl.class,
					primaryKey);

			if (wsrpConsumerPortlet == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchConsumerPortletException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(wsrpConsumerPortlet);
		}
		catch (NoSuchConsumerPortletException nsee) {
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
	protected WSRPConsumerPortlet removeImpl(
		WSRPConsumerPortlet wsrpConsumerPortlet) throws SystemException {
		wsrpConsumerPortlet = toUnwrappedModel(wsrpConsumerPortlet);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(wsrpConsumerPortlet)) {
				wsrpConsumerPortlet = (WSRPConsumerPortlet)session.get(WSRPConsumerPortletImpl.class,
						wsrpConsumerPortlet.getPrimaryKeyObj());
			}

			if (wsrpConsumerPortlet != null) {
				session.delete(wsrpConsumerPortlet);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (wsrpConsumerPortlet != null) {
			clearCache(wsrpConsumerPortlet);
		}

		return wsrpConsumerPortlet;
	}

	@Override
	public WSRPConsumerPortlet updateImpl(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet)
		throws SystemException {
		wsrpConsumerPortlet = toUnwrappedModel(wsrpConsumerPortlet);

		boolean isNew = wsrpConsumerPortlet.isNew();

		WSRPConsumerPortletModelImpl wsrpConsumerPortletModelImpl = (WSRPConsumerPortletModelImpl)wsrpConsumerPortlet;

		if (Validator.isNull(wsrpConsumerPortlet.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			wsrpConsumerPortlet.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (wsrpConsumerPortlet.isNew()) {
				session.save(wsrpConsumerPortlet);

				wsrpConsumerPortlet.setNew(false);
			}
			else {
				session.merge(wsrpConsumerPortlet);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !WSRPConsumerPortletModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((wsrpConsumerPortletModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						wsrpConsumerPortletModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { wsrpConsumerPortletModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((wsrpConsumerPortletModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						wsrpConsumerPortletModelImpl.getOriginalUuid(),
						wsrpConsumerPortletModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						wsrpConsumerPortletModelImpl.getUuid(),
						wsrpConsumerPortletModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((wsrpConsumerPortletModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_WSRPCONSUMERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						wsrpConsumerPortletModelImpl.getOriginalWsrpConsumerId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_WSRPCONSUMERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_WSRPCONSUMERID,
					args);

				args = new Object[] {
						wsrpConsumerPortletModelImpl.getWsrpConsumerId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_WSRPCONSUMERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_WSRPCONSUMERID,
					args);
			}
		}

		EntityCacheUtil.putResult(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerPortletImpl.class, wsrpConsumerPortlet.getPrimaryKey(),
			wsrpConsumerPortlet);

		clearUniqueFindersCache(wsrpConsumerPortlet);
		cacheUniqueFindersCache(wsrpConsumerPortlet);

		wsrpConsumerPortlet.resetOriginalValues();

		return wsrpConsumerPortlet;
	}

	protected WSRPConsumerPortlet toUnwrappedModel(
		WSRPConsumerPortlet wsrpConsumerPortlet) {
		if (wsrpConsumerPortlet instanceof WSRPConsumerPortletImpl) {
			return wsrpConsumerPortlet;
		}

		WSRPConsumerPortletImpl wsrpConsumerPortletImpl = new WSRPConsumerPortletImpl();

		wsrpConsumerPortletImpl.setNew(wsrpConsumerPortlet.isNew());
		wsrpConsumerPortletImpl.setPrimaryKey(wsrpConsumerPortlet.getPrimaryKey());

		wsrpConsumerPortletImpl.setUuid(wsrpConsumerPortlet.getUuid());
		wsrpConsumerPortletImpl.setWsrpConsumerPortletId(wsrpConsumerPortlet.getWsrpConsumerPortletId());
		wsrpConsumerPortletImpl.setCompanyId(wsrpConsumerPortlet.getCompanyId());
		wsrpConsumerPortletImpl.setCreateDate(wsrpConsumerPortlet.getCreateDate());
		wsrpConsumerPortletImpl.setModifiedDate(wsrpConsumerPortlet.getModifiedDate());
		wsrpConsumerPortletImpl.setWsrpConsumerId(wsrpConsumerPortlet.getWsrpConsumerId());
		wsrpConsumerPortletImpl.setName(wsrpConsumerPortlet.getName());
		wsrpConsumerPortletImpl.setPortletHandle(wsrpConsumerPortlet.getPortletHandle());

		return wsrpConsumerPortletImpl;
	}

	/**
	 * Returns the w s r p consumer portlet with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the w s r p consumer portlet
	 * @return the w s r p consumer portlet
	 * @throws com.liferay.wsrp.NoSuchConsumerPortletException if a w s r p consumer portlet with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumerPortlet findByPrimaryKey(Serializable primaryKey)
		throws NoSuchConsumerPortletException, SystemException {
		WSRPConsumerPortlet wsrpConsumerPortlet = fetchByPrimaryKey(primaryKey);

		if (wsrpConsumerPortlet == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchConsumerPortletException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return wsrpConsumerPortlet;
	}

	/**
	 * Returns the w s r p consumer portlet with the primary key or throws a {@link com.liferay.wsrp.NoSuchConsumerPortletException} if it could not be found.
	 *
	 * @param wsrpConsumerPortletId the primary key of the w s r p consumer portlet
	 * @return the w s r p consumer portlet
	 * @throws com.liferay.wsrp.NoSuchConsumerPortletException if a w s r p consumer portlet with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumerPortlet findByPrimaryKey(long wsrpConsumerPortletId)
		throws NoSuchConsumerPortletException, SystemException {
		return findByPrimaryKey((Serializable)wsrpConsumerPortletId);
	}

	/**
	 * Returns the w s r p consumer portlet with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the w s r p consumer portlet
	 * @return the w s r p consumer portlet, or <code>null</code> if a w s r p consumer portlet with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumerPortlet fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		WSRPConsumerPortlet wsrpConsumerPortlet = (WSRPConsumerPortlet)EntityCacheUtil.getResult(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
				WSRPConsumerPortletImpl.class, primaryKey);

		if (wsrpConsumerPortlet == _nullWSRPConsumerPortlet) {
			return null;
		}

		if (wsrpConsumerPortlet == null) {
			Session session = null;

			try {
				session = openSession();

				wsrpConsumerPortlet = (WSRPConsumerPortlet)session.get(WSRPConsumerPortletImpl.class,
						primaryKey);

				if (wsrpConsumerPortlet != null) {
					cacheResult(wsrpConsumerPortlet);
				}
				else {
					EntityCacheUtil.putResult(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
						WSRPConsumerPortletImpl.class, primaryKey,
						_nullWSRPConsumerPortlet);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(WSRPConsumerPortletModelImpl.ENTITY_CACHE_ENABLED,
					WSRPConsumerPortletImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return wsrpConsumerPortlet;
	}

	/**
	 * Returns the w s r p consumer portlet with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param wsrpConsumerPortletId the primary key of the w s r p consumer portlet
	 * @return the w s r p consumer portlet, or <code>null</code> if a w s r p consumer portlet with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WSRPConsumerPortlet fetchByPrimaryKey(long wsrpConsumerPortletId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)wsrpConsumerPortletId);
	}

	/**
	 * Returns all the w s r p consumer portlets.
	 *
	 * @return the w s r p consumer portlets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WSRPConsumerPortlet> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the w s r p consumer portlets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wsrp.model.impl.WSRPConsumerPortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of w s r p consumer portlets
	 * @param end the upper bound of the range of w s r p consumer portlets (not inclusive)
	 * @return the range of w s r p consumer portlets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WSRPConsumerPortlet> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the w s r p consumer portlets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wsrp.model.impl.WSRPConsumerPortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of w s r p consumer portlets
	 * @param end the upper bound of the range of w s r p consumer portlets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of w s r p consumer portlets
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<WSRPConsumerPortlet> findAll(int start, int end,
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

		List<WSRPConsumerPortlet> list = (List<WSRPConsumerPortlet>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_WSRPCONSUMERPORTLET);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WSRPCONSUMERPORTLET;

				if (pagination) {
					sql = sql.concat(WSRPConsumerPortletModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WSRPConsumerPortlet>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<WSRPConsumerPortlet>(list);
				}
				else {
					list = (List<WSRPConsumerPortlet>)QueryUtil.list(q,
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
	 * Removes all the w s r p consumer portlets from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (WSRPConsumerPortlet wsrpConsumerPortlet : findAll()) {
			remove(wsrpConsumerPortlet);
		}
	}

	/**
	 * Returns the number of w s r p consumer portlets.
	 *
	 * @return the number of w s r p consumer portlets
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

				Query q = session.createQuery(_SQL_COUNT_WSRPCONSUMERPORTLET);

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

	/**
	 * Initializes the w s r p consumer portlet persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.wsrp.model.WSRPConsumerPortlet")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<WSRPConsumerPortlet>> listenersList = new ArrayList<ModelListener<WSRPConsumerPortlet>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<WSRPConsumerPortlet>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(WSRPConsumerPortletImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_WSRPCONSUMERPORTLET = "SELECT wsrpConsumerPortlet FROM WSRPConsumerPortlet wsrpConsumerPortlet";
	private static final String _SQL_SELECT_WSRPCONSUMERPORTLET_WHERE = "SELECT wsrpConsumerPortlet FROM WSRPConsumerPortlet wsrpConsumerPortlet WHERE ";
	private static final String _SQL_COUNT_WSRPCONSUMERPORTLET = "SELECT COUNT(wsrpConsumerPortlet) FROM WSRPConsumerPortlet wsrpConsumerPortlet";
	private static final String _SQL_COUNT_WSRPCONSUMERPORTLET_WHERE = "SELECT COUNT(wsrpConsumerPortlet) FROM WSRPConsumerPortlet wsrpConsumerPortlet WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "wsrpConsumerPortlet.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WSRPConsumerPortlet exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No WSRPConsumerPortlet exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(WSRPConsumerPortletPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static WSRPConsumerPortlet _nullWSRPConsumerPortlet = new WSRPConsumerPortletImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<WSRPConsumerPortlet> toCacheModel() {
				return _nullWSRPConsumerPortletCacheModel;
			}
		};

	private static CacheModel<WSRPConsumerPortlet> _nullWSRPConsumerPortletCacheModel =
		new CacheModel<WSRPConsumerPortlet>() {
			@Override
			public WSRPConsumerPortlet toEntityModel() {
				return _nullWSRPConsumerPortlet;
			}
		};
}