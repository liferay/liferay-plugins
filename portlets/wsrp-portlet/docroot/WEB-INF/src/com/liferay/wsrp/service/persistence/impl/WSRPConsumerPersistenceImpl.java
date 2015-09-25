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

package com.liferay.wsrp.service.persistence.impl;

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
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.wsrp.NoSuchConsumerException;
import com.liferay.wsrp.model.WSRPConsumer;
import com.liferay.wsrp.model.impl.WSRPConsumerImpl;
import com.liferay.wsrp.model.impl.WSRPConsumerModelImpl;
import com.liferay.wsrp.service.persistence.WSRPConsumerPersistence;

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
 * The persistence implementation for the w s r p consumer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumerPersistence
 * @see com.liferay.wsrp.service.persistence.WSRPConsumerUtil
 * @generated
 */
@ProviderType
public class WSRPConsumerPersistenceImpl extends BasePersistenceImpl<WSRPConsumer>
	implements WSRPConsumerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WSRPConsumerUtil} to access the w s r p consumer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WSRPConsumerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, WSRPConsumerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, WSRPConsumerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, WSRPConsumerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, WSRPConsumerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			WSRPConsumerModelImpl.UUID_COLUMN_BITMASK |
			WSRPConsumerModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the w s r p consumers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching w s r p consumers
	 */
	@Override
	public List<WSRPConsumer> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the w s r p consumers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of w s r p consumers
	 * @param end the upper bound of the range of w s r p consumers (not inclusive)
	 * @return the range of matching w s r p consumers
	 */
	@Override
	public List<WSRPConsumer> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the w s r p consumers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of w s r p consumers
	 * @param end the upper bound of the range of w s r p consumers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching w s r p consumers
	 */
	@Override
	public List<WSRPConsumer> findByUuid(String uuid, int start, int end,
		OrderByComparator<WSRPConsumer> orderByComparator) {
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

		List<WSRPConsumer> list = (List<WSRPConsumer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WSRPConsumer wsrpConsumer : list) {
				if (!Validator.equals(uuid, wsrpConsumer.getUuid())) {
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

			query.append(_SQL_SELECT_WSRPCONSUMER_WHERE);

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
				query.append(WSRPConsumerModelImpl.ORDER_BY_JPQL);
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
					list = (List<WSRPConsumer>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WSRPConsumer>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first w s r p consumer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching w s r p consumer
	 * @throws NoSuchConsumerException if a matching w s r p consumer could not be found
	 */
	@Override
	public WSRPConsumer findByUuid_First(String uuid,
		OrderByComparator<WSRPConsumer> orderByComparator)
		throws NoSuchConsumerException {
		WSRPConsumer wsrpConsumer = fetchByUuid_First(uuid, orderByComparator);

		if (wsrpConsumer != null) {
			return wsrpConsumer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConsumerException(msg.toString());
	}

	/**
	 * Returns the first w s r p consumer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching w s r p consumer, or <code>null</code> if a matching w s r p consumer could not be found
	 */
	@Override
	public WSRPConsumer fetchByUuid_First(String uuid,
		OrderByComparator<WSRPConsumer> orderByComparator) {
		List<WSRPConsumer> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last w s r p consumer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching w s r p consumer
	 * @throws NoSuchConsumerException if a matching w s r p consumer could not be found
	 */
	@Override
	public WSRPConsumer findByUuid_Last(String uuid,
		OrderByComparator<WSRPConsumer> orderByComparator)
		throws NoSuchConsumerException {
		WSRPConsumer wsrpConsumer = fetchByUuid_Last(uuid, orderByComparator);

		if (wsrpConsumer != null) {
			return wsrpConsumer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConsumerException(msg.toString());
	}

	/**
	 * Returns the last w s r p consumer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching w s r p consumer, or <code>null</code> if a matching w s r p consumer could not be found
	 */
	@Override
	public WSRPConsumer fetchByUuid_Last(String uuid,
		OrderByComparator<WSRPConsumer> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<WSRPConsumer> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the w s r p consumers before and after the current w s r p consumer in the ordered set where uuid = &#63;.
	 *
	 * @param wsrpConsumerId the primary key of the current w s r p consumer
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next w s r p consumer
	 * @throws NoSuchConsumerException if a w s r p consumer with the primary key could not be found
	 */
	@Override
	public WSRPConsumer[] findByUuid_PrevAndNext(long wsrpConsumerId,
		String uuid, OrderByComparator<WSRPConsumer> orderByComparator)
		throws NoSuchConsumerException {
		WSRPConsumer wsrpConsumer = findByPrimaryKey(wsrpConsumerId);

		Session session = null;

		try {
			session = openSession();

			WSRPConsumer[] array = new WSRPConsumerImpl[3];

			array[0] = getByUuid_PrevAndNext(session, wsrpConsumer, uuid,
					orderByComparator, true);

			array[1] = wsrpConsumer;

			array[2] = getByUuid_PrevAndNext(session, wsrpConsumer, uuid,
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

	protected WSRPConsumer getByUuid_PrevAndNext(Session session,
		WSRPConsumer wsrpConsumer, String uuid,
		OrderByComparator<WSRPConsumer> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WSRPCONSUMER_WHERE);

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
			query.append(WSRPConsumerModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(wsrpConsumer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WSRPConsumer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the w s r p consumers where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (WSRPConsumer wsrpConsumer : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(wsrpConsumer);
		}
	}

	/**
	 * Returns the number of w s r p consumers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching w s r p consumers
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WSRPCONSUMER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "wsrpConsumer.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "wsrpConsumer.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(wsrpConsumer.uuid IS NULL OR wsrpConsumer.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, WSRPConsumerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, WSRPConsumerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			WSRPConsumerModelImpl.UUID_COLUMN_BITMASK |
			WSRPConsumerModelImpl.COMPANYID_COLUMN_BITMASK |
			WSRPConsumerModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the w s r p consumers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching w s r p consumers
	 */
	@Override
	public List<WSRPConsumer> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the w s r p consumers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of w s r p consumers
	 * @param end the upper bound of the range of w s r p consumers (not inclusive)
	 * @return the range of matching w s r p consumers
	 */
	@Override
	public List<WSRPConsumer> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the w s r p consumers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of w s r p consumers
	 * @param end the upper bound of the range of w s r p consumers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching w s r p consumers
	 */
	@Override
	public List<WSRPConsumer> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<WSRPConsumer> orderByComparator) {
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

		List<WSRPConsumer> list = (List<WSRPConsumer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WSRPConsumer wsrpConsumer : list) {
				if (!Validator.equals(uuid, wsrpConsumer.getUuid()) ||
						(companyId != wsrpConsumer.getCompanyId())) {
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

			query.append(_SQL_SELECT_WSRPCONSUMER_WHERE);

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
				query.append(WSRPConsumerModelImpl.ORDER_BY_JPQL);
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
					list = (List<WSRPConsumer>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WSRPConsumer>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first w s r p consumer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching w s r p consumer
	 * @throws NoSuchConsumerException if a matching w s r p consumer could not be found
	 */
	@Override
	public WSRPConsumer findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<WSRPConsumer> orderByComparator)
		throws NoSuchConsumerException {
		WSRPConsumer wsrpConsumer = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (wsrpConsumer != null) {
			return wsrpConsumer;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConsumerException(msg.toString());
	}

	/**
	 * Returns the first w s r p consumer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching w s r p consumer, or <code>null</code> if a matching w s r p consumer could not be found
	 */
	@Override
	public WSRPConsumer fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<WSRPConsumer> orderByComparator) {
		List<WSRPConsumer> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last w s r p consumer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching w s r p consumer
	 * @throws NoSuchConsumerException if a matching w s r p consumer could not be found
	 */
	@Override
	public WSRPConsumer findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<WSRPConsumer> orderByComparator)
		throws NoSuchConsumerException {
		WSRPConsumer wsrpConsumer = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (wsrpConsumer != null) {
			return wsrpConsumer;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConsumerException(msg.toString());
	}

	/**
	 * Returns the last w s r p consumer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching w s r p consumer, or <code>null</code> if a matching w s r p consumer could not be found
	 */
	@Override
	public WSRPConsumer fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<WSRPConsumer> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<WSRPConsumer> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the w s r p consumers before and after the current w s r p consumer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param wsrpConsumerId the primary key of the current w s r p consumer
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next w s r p consumer
	 * @throws NoSuchConsumerException if a w s r p consumer with the primary key could not be found
	 */
	@Override
	public WSRPConsumer[] findByUuid_C_PrevAndNext(long wsrpConsumerId,
		String uuid, long companyId,
		OrderByComparator<WSRPConsumer> orderByComparator)
		throws NoSuchConsumerException {
		WSRPConsumer wsrpConsumer = findByPrimaryKey(wsrpConsumerId);

		Session session = null;

		try {
			session = openSession();

			WSRPConsumer[] array = new WSRPConsumerImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, wsrpConsumer, uuid,
					companyId, orderByComparator, true);

			array[1] = wsrpConsumer;

			array[2] = getByUuid_C_PrevAndNext(session, wsrpConsumer, uuid,
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

	protected WSRPConsumer getByUuid_C_PrevAndNext(Session session,
		WSRPConsumer wsrpConsumer, String uuid, long companyId,
		OrderByComparator<WSRPConsumer> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WSRPCONSUMER_WHERE);

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
			query.append(WSRPConsumerModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(wsrpConsumer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WSRPConsumer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the w s r p consumers where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (WSRPConsumer wsrpConsumer : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(wsrpConsumer);
		}
	}

	/**
	 * Returns the number of w s r p consumers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching w s r p consumers
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WSRPCONSUMER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "wsrpConsumer.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "wsrpConsumer.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(wsrpConsumer.uuid IS NULL OR wsrpConsumer.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "wsrpConsumer.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, WSRPConsumerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, WSRPConsumerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			WSRPConsumerModelImpl.COMPANYID_COLUMN_BITMASK |
			WSRPConsumerModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the w s r p consumers where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching w s r p consumers
	 */
	@Override
	public List<WSRPConsumer> findByCompanyId(long companyId) {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the w s r p consumers where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of w s r p consumers
	 * @param end the upper bound of the range of w s r p consumers (not inclusive)
	 * @return the range of matching w s r p consumers
	 */
	@Override
	public List<WSRPConsumer> findByCompanyId(long companyId, int start, int end) {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the w s r p consumers where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of w s r p consumers
	 * @param end the upper bound of the range of w s r p consumers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching w s r p consumers
	 */
	@Override
	public List<WSRPConsumer> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<WSRPConsumer> orderByComparator) {
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

		List<WSRPConsumer> list = (List<WSRPConsumer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WSRPConsumer wsrpConsumer : list) {
				if ((companyId != wsrpConsumer.getCompanyId())) {
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

			query.append(_SQL_SELECT_WSRPCONSUMER_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WSRPConsumerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<WSRPConsumer>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WSRPConsumer>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first w s r p consumer in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching w s r p consumer
	 * @throws NoSuchConsumerException if a matching w s r p consumer could not be found
	 */
	@Override
	public WSRPConsumer findByCompanyId_First(long companyId,
		OrderByComparator<WSRPConsumer> orderByComparator)
		throws NoSuchConsumerException {
		WSRPConsumer wsrpConsumer = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (wsrpConsumer != null) {
			return wsrpConsumer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConsumerException(msg.toString());
	}

	/**
	 * Returns the first w s r p consumer in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching w s r p consumer, or <code>null</code> if a matching w s r p consumer could not be found
	 */
	@Override
	public WSRPConsumer fetchByCompanyId_First(long companyId,
		OrderByComparator<WSRPConsumer> orderByComparator) {
		List<WSRPConsumer> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last w s r p consumer in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching w s r p consumer
	 * @throws NoSuchConsumerException if a matching w s r p consumer could not be found
	 */
	@Override
	public WSRPConsumer findByCompanyId_Last(long companyId,
		OrderByComparator<WSRPConsumer> orderByComparator)
		throws NoSuchConsumerException {
		WSRPConsumer wsrpConsumer = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (wsrpConsumer != null) {
			return wsrpConsumer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConsumerException(msg.toString());
	}

	/**
	 * Returns the last w s r p consumer in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching w s r p consumer, or <code>null</code> if a matching w s r p consumer could not be found
	 */
	@Override
	public WSRPConsumer fetchByCompanyId_Last(long companyId,
		OrderByComparator<WSRPConsumer> orderByComparator) {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<WSRPConsumer> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the w s r p consumers before and after the current w s r p consumer in the ordered set where companyId = &#63;.
	 *
	 * @param wsrpConsumerId the primary key of the current w s r p consumer
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next w s r p consumer
	 * @throws NoSuchConsumerException if a w s r p consumer with the primary key could not be found
	 */
	@Override
	public WSRPConsumer[] findByCompanyId_PrevAndNext(long wsrpConsumerId,
		long companyId, OrderByComparator<WSRPConsumer> orderByComparator)
		throws NoSuchConsumerException {
		WSRPConsumer wsrpConsumer = findByPrimaryKey(wsrpConsumerId);

		Session session = null;

		try {
			session = openSession();

			WSRPConsumer[] array = new WSRPConsumerImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, wsrpConsumer,
					companyId, orderByComparator, true);

			array[1] = wsrpConsumer;

			array[2] = getByCompanyId_PrevAndNext(session, wsrpConsumer,
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

	protected WSRPConsumer getByCompanyId_PrevAndNext(Session session,
		WSRPConsumer wsrpConsumer, long companyId,
		OrderByComparator<WSRPConsumer> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WSRPCONSUMER_WHERE);

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
			query.append(WSRPConsumerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(wsrpConsumer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WSRPConsumer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the w s r p consumers where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (WSRPConsumer wsrpConsumer : findByCompanyId(companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(wsrpConsumer);
		}
	}

	/**
	 * Returns the number of w s r p consumers where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching w s r p consumers
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WSRPCONSUMER_WHERE);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "wsrpConsumer.companyId = ?";

	public WSRPConsumerPersistenceImpl() {
		setModelClass(WSRPConsumer.class);
	}

	/**
	 * Caches the w s r p consumer in the entity cache if it is enabled.
	 *
	 * @param wsrpConsumer the w s r p consumer
	 */
	@Override
	public void cacheResult(WSRPConsumer wsrpConsumer) {
		EntityCacheUtil.putResult(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerImpl.class, wsrpConsumer.getPrimaryKey(), wsrpConsumer);

		wsrpConsumer.resetOriginalValues();
	}

	/**
	 * Caches the w s r p consumers in the entity cache if it is enabled.
	 *
	 * @param wsrpConsumers the w s r p consumers
	 */
	@Override
	public void cacheResult(List<WSRPConsumer> wsrpConsumers) {
		for (WSRPConsumer wsrpConsumer : wsrpConsumers) {
			if (EntityCacheUtil.getResult(
						WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
						WSRPConsumerImpl.class, wsrpConsumer.getPrimaryKey()) == null) {
				cacheResult(wsrpConsumer);
			}
			else {
				wsrpConsumer.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all w s r p consumers.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(WSRPConsumerImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the w s r p consumer.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WSRPConsumer wsrpConsumer) {
		EntityCacheUtil.removeResult(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerImpl.class, wsrpConsumer.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<WSRPConsumer> wsrpConsumers) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WSRPConsumer wsrpConsumer : wsrpConsumers) {
			EntityCacheUtil.removeResult(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
				WSRPConsumerImpl.class, wsrpConsumer.getPrimaryKey());
		}
	}

	/**
	 * Creates a new w s r p consumer with the primary key. Does not add the w s r p consumer to the database.
	 *
	 * @param wsrpConsumerId the primary key for the new w s r p consumer
	 * @return the new w s r p consumer
	 */
	@Override
	public WSRPConsumer create(long wsrpConsumerId) {
		WSRPConsumer wsrpConsumer = new WSRPConsumerImpl();

		wsrpConsumer.setNew(true);
		wsrpConsumer.setPrimaryKey(wsrpConsumerId);

		String uuid = PortalUUIDUtil.generate();

		wsrpConsumer.setUuid(uuid);

		return wsrpConsumer;
	}

	/**
	 * Removes the w s r p consumer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param wsrpConsumerId the primary key of the w s r p consumer
	 * @return the w s r p consumer that was removed
	 * @throws NoSuchConsumerException if a w s r p consumer with the primary key could not be found
	 */
	@Override
	public WSRPConsumer remove(long wsrpConsumerId)
		throws NoSuchConsumerException {
		return remove((Serializable)wsrpConsumerId);
	}

	/**
	 * Removes the w s r p consumer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the w s r p consumer
	 * @return the w s r p consumer that was removed
	 * @throws NoSuchConsumerException if a w s r p consumer with the primary key could not be found
	 */
	@Override
	public WSRPConsumer remove(Serializable primaryKey)
		throws NoSuchConsumerException {
		Session session = null;

		try {
			session = openSession();

			WSRPConsumer wsrpConsumer = (WSRPConsumer)session.get(WSRPConsumerImpl.class,
					primaryKey);

			if (wsrpConsumer == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchConsumerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(wsrpConsumer);
		}
		catch (NoSuchConsumerException nsee) {
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
	protected WSRPConsumer removeImpl(WSRPConsumer wsrpConsumer) {
		wsrpConsumer = toUnwrappedModel(wsrpConsumer);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(wsrpConsumer)) {
				wsrpConsumer = (WSRPConsumer)session.get(WSRPConsumerImpl.class,
						wsrpConsumer.getPrimaryKeyObj());
			}

			if (wsrpConsumer != null) {
				session.delete(wsrpConsumer);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (wsrpConsumer != null) {
			clearCache(wsrpConsumer);
		}

		return wsrpConsumer;
	}

	@Override
	public WSRPConsumer updateImpl(WSRPConsumer wsrpConsumer) {
		wsrpConsumer = toUnwrappedModel(wsrpConsumer);

		boolean isNew = wsrpConsumer.isNew();

		WSRPConsumerModelImpl wsrpConsumerModelImpl = (WSRPConsumerModelImpl)wsrpConsumer;

		if (Validator.isNull(wsrpConsumer.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			wsrpConsumer.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (wsrpConsumer.getCreateDate() == null)) {
			if (serviceContext == null) {
				wsrpConsumer.setCreateDate(now);
			}
			else {
				wsrpConsumer.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!wsrpConsumerModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				wsrpConsumer.setModifiedDate(now);
			}
			else {
				wsrpConsumer.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (wsrpConsumer.isNew()) {
				session.save(wsrpConsumer);

				wsrpConsumer.setNew(false);
			}
			else {
				wsrpConsumer = (WSRPConsumer)session.merge(wsrpConsumer);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !WSRPConsumerModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((wsrpConsumerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						wsrpConsumerModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { wsrpConsumerModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((wsrpConsumerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						wsrpConsumerModelImpl.getOriginalUuid(),
						wsrpConsumerModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						wsrpConsumerModelImpl.getUuid(),
						wsrpConsumerModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((wsrpConsumerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						wsrpConsumerModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { wsrpConsumerModelImpl.getCompanyId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}
		}

		EntityCacheUtil.putResult(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPConsumerImpl.class, wsrpConsumer.getPrimaryKey(), wsrpConsumer,
			false);

		wsrpConsumer.resetOriginalValues();

		return wsrpConsumer;
	}

	protected WSRPConsumer toUnwrappedModel(WSRPConsumer wsrpConsumer) {
		if (wsrpConsumer instanceof WSRPConsumerImpl) {
			return wsrpConsumer;
		}

		WSRPConsumerImpl wsrpConsumerImpl = new WSRPConsumerImpl();

		wsrpConsumerImpl.setNew(wsrpConsumer.isNew());
		wsrpConsumerImpl.setPrimaryKey(wsrpConsumer.getPrimaryKey());

		wsrpConsumerImpl.setUuid(wsrpConsumer.getUuid());
		wsrpConsumerImpl.setWsrpConsumerId(wsrpConsumer.getWsrpConsumerId());
		wsrpConsumerImpl.setCompanyId(wsrpConsumer.getCompanyId());
		wsrpConsumerImpl.setCreateDate(wsrpConsumer.getCreateDate());
		wsrpConsumerImpl.setModifiedDate(wsrpConsumer.getModifiedDate());
		wsrpConsumerImpl.setName(wsrpConsumer.getName());
		wsrpConsumerImpl.setUrl(wsrpConsumer.getUrl());
		wsrpConsumerImpl.setWsdl(wsrpConsumer.getWsdl());
		wsrpConsumerImpl.setRegistrationContextString(wsrpConsumer.getRegistrationContextString());
		wsrpConsumerImpl.setRegistrationPropertiesString(wsrpConsumer.getRegistrationPropertiesString());
		wsrpConsumerImpl.setForwardCookies(wsrpConsumer.getForwardCookies());
		wsrpConsumerImpl.setForwardHeaders(wsrpConsumer.getForwardHeaders());
		wsrpConsumerImpl.setMarkupCharacterSets(wsrpConsumer.getMarkupCharacterSets());
		wsrpConsumerImpl.setLastPublishDate(wsrpConsumer.getLastPublishDate());

		return wsrpConsumerImpl;
	}

	/**
	 * Returns the w s r p consumer with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the w s r p consumer
	 * @return the w s r p consumer
	 * @throws NoSuchConsumerException if a w s r p consumer with the primary key could not be found
	 */
	@Override
	public WSRPConsumer findByPrimaryKey(Serializable primaryKey)
		throws NoSuchConsumerException {
		WSRPConsumer wsrpConsumer = fetchByPrimaryKey(primaryKey);

		if (wsrpConsumer == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchConsumerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return wsrpConsumer;
	}

	/**
	 * Returns the w s r p consumer with the primary key or throws a {@link NoSuchConsumerException} if it could not be found.
	 *
	 * @param wsrpConsumerId the primary key of the w s r p consumer
	 * @return the w s r p consumer
	 * @throws NoSuchConsumerException if a w s r p consumer with the primary key could not be found
	 */
	@Override
	public WSRPConsumer findByPrimaryKey(long wsrpConsumerId)
		throws NoSuchConsumerException {
		return findByPrimaryKey((Serializable)wsrpConsumerId);
	}

	/**
	 * Returns the w s r p consumer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the w s r p consumer
	 * @return the w s r p consumer, or <code>null</code> if a w s r p consumer with the primary key could not be found
	 */
	@Override
	public WSRPConsumer fetchByPrimaryKey(Serializable primaryKey) {
		WSRPConsumer wsrpConsumer = (WSRPConsumer)EntityCacheUtil.getResult(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
				WSRPConsumerImpl.class, primaryKey);

		if (wsrpConsumer == _nullWSRPConsumer) {
			return null;
		}

		if (wsrpConsumer == null) {
			Session session = null;

			try {
				session = openSession();

				wsrpConsumer = (WSRPConsumer)session.get(WSRPConsumerImpl.class,
						primaryKey);

				if (wsrpConsumer != null) {
					cacheResult(wsrpConsumer);
				}
				else {
					EntityCacheUtil.putResult(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
						WSRPConsumerImpl.class, primaryKey, _nullWSRPConsumer);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
					WSRPConsumerImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return wsrpConsumer;
	}

	/**
	 * Returns the w s r p consumer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param wsrpConsumerId the primary key of the w s r p consumer
	 * @return the w s r p consumer, or <code>null</code> if a w s r p consumer with the primary key could not be found
	 */
	@Override
	public WSRPConsumer fetchByPrimaryKey(long wsrpConsumerId) {
		return fetchByPrimaryKey((Serializable)wsrpConsumerId);
	}

	@Override
	public Map<Serializable, WSRPConsumer> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WSRPConsumer> map = new HashMap<Serializable, WSRPConsumer>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WSRPConsumer wsrpConsumer = fetchByPrimaryKey(primaryKey);

			if (wsrpConsumer != null) {
				map.put(primaryKey, wsrpConsumer);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			WSRPConsumer wsrpConsumer = (WSRPConsumer)EntityCacheUtil.getResult(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
					WSRPConsumerImpl.class, primaryKey);

			if (wsrpConsumer == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, wsrpConsumer);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_WSRPCONSUMER_WHERE_PKS_IN);

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

			for (WSRPConsumer wsrpConsumer : (List<WSRPConsumer>)q.list()) {
				map.put(wsrpConsumer.getPrimaryKeyObj(), wsrpConsumer);

				cacheResult(wsrpConsumer);

				uncachedPrimaryKeys.remove(wsrpConsumer.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(WSRPConsumerModelImpl.ENTITY_CACHE_ENABLED,
					WSRPConsumerImpl.class, primaryKey, _nullWSRPConsumer);
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
	 * Returns all the w s r p consumers.
	 *
	 * @return the w s r p consumers
	 */
	@Override
	public List<WSRPConsumer> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the w s r p consumers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of w s r p consumers
	 * @param end the upper bound of the range of w s r p consumers (not inclusive)
	 * @return the range of w s r p consumers
	 */
	@Override
	public List<WSRPConsumer> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the w s r p consumers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of w s r p consumers
	 * @param end the upper bound of the range of w s r p consumers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of w s r p consumers
	 */
	@Override
	public List<WSRPConsumer> findAll(int start, int end,
		OrderByComparator<WSRPConsumer> orderByComparator) {
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

		List<WSRPConsumer> list = (List<WSRPConsumer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_WSRPCONSUMER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WSRPCONSUMER;

				if (pagination) {
					sql = sql.concat(WSRPConsumerModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WSRPConsumer>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WSRPConsumer>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the w s r p consumers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WSRPConsumer wsrpConsumer : findAll()) {
			remove(wsrpConsumer);
		}
	}

	/**
	 * Returns the number of w s r p consumers.
	 *
	 * @return the number of w s r p consumers
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WSRPCONSUMER);

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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return WSRPConsumerModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the w s r p consumer persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(WSRPConsumerImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_WSRPCONSUMER = "SELECT wsrpConsumer FROM WSRPConsumer wsrpConsumer";
	private static final String _SQL_SELECT_WSRPCONSUMER_WHERE_PKS_IN = "SELECT wsrpConsumer FROM WSRPConsumer wsrpConsumer WHERE wsrpConsumerId IN (";
	private static final String _SQL_SELECT_WSRPCONSUMER_WHERE = "SELECT wsrpConsumer FROM WSRPConsumer wsrpConsumer WHERE ";
	private static final String _SQL_COUNT_WSRPCONSUMER = "SELECT COUNT(wsrpConsumer) FROM WSRPConsumer wsrpConsumer";
	private static final String _SQL_COUNT_WSRPCONSUMER_WHERE = "SELECT COUNT(wsrpConsumer) FROM WSRPConsumer wsrpConsumer WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "wsrpConsumer.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WSRPConsumer exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No WSRPConsumer exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(WSRPConsumerPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static final WSRPConsumer _nullWSRPConsumer = new WSRPConsumerImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<WSRPConsumer> toCacheModel() {
				return _nullWSRPConsumerCacheModel;
			}
		};

	private static final CacheModel<WSRPConsumer> _nullWSRPConsumerCacheModel = new CacheModel<WSRPConsumer>() {
			@Override
			public WSRPConsumer toEntityModel() {
				return _nullWSRPConsumer;
			}
		};
}