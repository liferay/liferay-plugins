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

package com.liferay.so.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCache;
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
import com.liferay.portal.service.persistence.CompanyProvider;
import com.liferay.portal.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.so.NoSuchMemberRequestException;
import com.liferay.so.model.MemberRequest;
import com.liferay.so.model.impl.MemberRequestImpl;
import com.liferay.so.model.impl.MemberRequestModelImpl;
import com.liferay.so.service.persistence.MemberRequestPersistence;

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
 * The persistence implementation for the member request service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MemberRequestPersistence
 * @see com.liferay.so.service.persistence.MemberRequestUtil
 * @generated
 */
@ProviderType
public class MemberRequestPersistenceImpl extends BasePersistenceImpl<MemberRequest>
	implements MemberRequestPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MemberRequestUtil} to access the member request persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MemberRequestImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED,
			MemberRequestImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED,
			MemberRequestImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_KEY = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED,
			MemberRequestImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByKey",
			new String[] { String.class.getName() },
			MemberRequestModelImpl.KEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KEY = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKey",
			new String[] { String.class.getName() });

	/**
	 * Returns the member request where key = &#63; or throws a {@link NoSuchMemberRequestException} if it could not be found.
	 *
	 * @param key the key
	 * @return the matching member request
	 * @throws NoSuchMemberRequestException if a matching member request could not be found
	 */
	@Override
	public MemberRequest findByKey(String key)
		throws NoSuchMemberRequestException {
		MemberRequest memberRequest = fetchByKey(key);

		if (memberRequest == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("key=");
			msg.append(key);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchMemberRequestException(msg.toString());
		}

		return memberRequest;
	}

	/**
	 * Returns the member request where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param key the key
	 * @return the matching member request, or <code>null</code> if a matching member request could not be found
	 */
	@Override
	public MemberRequest fetchByKey(String key) {
		return fetchByKey(key, true);
	}

	/**
	 * Returns the member request where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param key the key
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching member request, or <code>null</code> if a matching member request could not be found
	 */
	@Override
	public MemberRequest fetchByKey(String key, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { key };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_KEY,
					finderArgs, this);
		}

		if (result instanceof MemberRequest) {
			MemberRequest memberRequest = (MemberRequest)result;

			if (!Validator.equals(key, memberRequest.getKey())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_MEMBERREQUEST_WHERE);

			boolean bindKey = false;

			if (key == null) {
				query.append(_FINDER_COLUMN_KEY_KEY_1);
			}
			else if (key.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KEY_KEY_3);
			}
			else {
				bindKey = true;

				query.append(_FINDER_COLUMN_KEY_KEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindKey) {
					qPos.add(key);
				}

				List<MemberRequest> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_KEY, finderArgs,
						list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"MemberRequestPersistenceImpl.fetchByKey(String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					MemberRequest memberRequest = list.get(0);

					result = memberRequest;

					cacheResult(memberRequest);

					if ((memberRequest.getKey() == null) ||
							!memberRequest.getKey().equals(key)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_KEY,
							finderArgs, memberRequest);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_KEY, finderArgs);

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
			return (MemberRequest)result;
		}
	}

	/**
	 * Removes the member request where key = &#63; from the database.
	 *
	 * @param key the key
	 * @return the member request that was removed
	 */
	@Override
	public MemberRequest removeByKey(String key)
		throws NoSuchMemberRequestException {
		MemberRequest memberRequest = findByKey(key);

		return remove(memberRequest);
	}

	/**
	 * Returns the number of member requests where key = &#63;.
	 *
	 * @param key the key
	 * @return the number of matching member requests
	 */
	@Override
	public int countByKey(String key) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KEY;

		Object[] finderArgs = new Object[] { key };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MEMBERREQUEST_WHERE);

			boolean bindKey = false;

			if (key == null) {
				query.append(_FINDER_COLUMN_KEY_KEY_1);
			}
			else if (key.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KEY_KEY_3);
			}
			else {
				bindKey = true;

				query.append(_FINDER_COLUMN_KEY_KEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindKey) {
					qPos.add(key);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_KEY_KEY_1 = "memberRequest.key IS NULL";
	private static final String _FINDER_COLUMN_KEY_KEY_2 = "memberRequest.key = ?";
	private static final String _FINDER_COLUMN_KEY_KEY_3 = "(memberRequest.key IS NULL OR memberRequest.key = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RECEIVERUSERID =
		new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED,
			MemberRequestImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByReceiverUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECEIVERUSERID =
		new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED,
			MemberRequestImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByReceiverUserId", new String[] { Long.class.getName() },
			MemberRequestModelImpl.RECEIVERUSERID_COLUMN_BITMASK |
			MemberRequestModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RECEIVERUSERID = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByReceiverUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the member requests where receiverUserId = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @return the matching member requests
	 */
	@Override
	public List<MemberRequest> findByReceiverUserId(long receiverUserId) {
		return findByReceiverUserId(receiverUserId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the member requests where receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MemberRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param receiverUserId the receiver user ID
	 * @param start the lower bound of the range of member requests
	 * @param end the upper bound of the range of member requests (not inclusive)
	 * @return the range of matching member requests
	 */
	@Override
	public List<MemberRequest> findByReceiverUserId(long receiverUserId,
		int start, int end) {
		return findByReceiverUserId(receiverUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the member requests where receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MemberRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param receiverUserId the receiver user ID
	 * @param start the lower bound of the range of member requests
	 * @param end the upper bound of the range of member requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching member requests
	 */
	@Override
	public List<MemberRequest> findByReceiverUserId(long receiverUserId,
		int start, int end, OrderByComparator<MemberRequest> orderByComparator) {
		return findByReceiverUserId(receiverUserId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the member requests where receiverUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MemberRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param receiverUserId the receiver user ID
	 * @param start the lower bound of the range of member requests
	 * @param end the upper bound of the range of member requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching member requests
	 */
	@Override
	public List<MemberRequest> findByReceiverUserId(long receiverUserId,
		int start, int end, OrderByComparator<MemberRequest> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECEIVERUSERID;
			finderArgs = new Object[] { receiverUserId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RECEIVERUSERID;
			finderArgs = new Object[] {
					receiverUserId,
					
					start, end, orderByComparator
				};
		}

		List<MemberRequest> list = null;

		if (retrieveFromCache) {
			list = (List<MemberRequest>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MemberRequest memberRequest : list) {
					if ((receiverUserId != memberRequest.getReceiverUserId())) {
						list = null;

						break;
					}
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

			query.append(_SQL_SELECT_MEMBERREQUEST_WHERE);

			query.append(_FINDER_COLUMN_RECEIVERUSERID_RECEIVERUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MemberRequestModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(receiverUserId);

				if (!pagination) {
					list = (List<MemberRequest>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MemberRequest>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first member request in the ordered set where receiverUserId = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching member request
	 * @throws NoSuchMemberRequestException if a matching member request could not be found
	 */
	@Override
	public MemberRequest findByReceiverUserId_First(long receiverUserId,
		OrderByComparator<MemberRequest> orderByComparator)
		throws NoSuchMemberRequestException {
		MemberRequest memberRequest = fetchByReceiverUserId_First(receiverUserId,
				orderByComparator);

		if (memberRequest != null) {
			return memberRequest;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("receiverUserId=");
		msg.append(receiverUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMemberRequestException(msg.toString());
	}

	/**
	 * Returns the first member request in the ordered set where receiverUserId = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching member request, or <code>null</code> if a matching member request could not be found
	 */
	@Override
	public MemberRequest fetchByReceiverUserId_First(long receiverUserId,
		OrderByComparator<MemberRequest> orderByComparator) {
		List<MemberRequest> list = findByReceiverUserId(receiverUserId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last member request in the ordered set where receiverUserId = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching member request
	 * @throws NoSuchMemberRequestException if a matching member request could not be found
	 */
	@Override
	public MemberRequest findByReceiverUserId_Last(long receiverUserId,
		OrderByComparator<MemberRequest> orderByComparator)
		throws NoSuchMemberRequestException {
		MemberRequest memberRequest = fetchByReceiverUserId_Last(receiverUserId,
				orderByComparator);

		if (memberRequest != null) {
			return memberRequest;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("receiverUserId=");
		msg.append(receiverUserId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMemberRequestException(msg.toString());
	}

	/**
	 * Returns the last member request in the ordered set where receiverUserId = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching member request, or <code>null</code> if a matching member request could not be found
	 */
	@Override
	public MemberRequest fetchByReceiverUserId_Last(long receiverUserId,
		OrderByComparator<MemberRequest> orderByComparator) {
		int count = countByReceiverUserId(receiverUserId);

		if (count == 0) {
			return null;
		}

		List<MemberRequest> list = findByReceiverUserId(receiverUserId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the member requests before and after the current member request in the ordered set where receiverUserId = &#63;.
	 *
	 * @param memberRequestId the primary key of the current member request
	 * @param receiverUserId the receiver user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next member request
	 * @throws NoSuchMemberRequestException if a member request with the primary key could not be found
	 */
	@Override
	public MemberRequest[] findByReceiverUserId_PrevAndNext(
		long memberRequestId, long receiverUserId,
		OrderByComparator<MemberRequest> orderByComparator)
		throws NoSuchMemberRequestException {
		MemberRequest memberRequest = findByPrimaryKey(memberRequestId);

		Session session = null;

		try {
			session = openSession();

			MemberRequest[] array = new MemberRequestImpl[3];

			array[0] = getByReceiverUserId_PrevAndNext(session, memberRequest,
					receiverUserId, orderByComparator, true);

			array[1] = memberRequest;

			array[2] = getByReceiverUserId_PrevAndNext(session, memberRequest,
					receiverUserId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MemberRequest getByReceiverUserId_PrevAndNext(Session session,
		MemberRequest memberRequest, long receiverUserId,
		OrderByComparator<MemberRequest> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MEMBERREQUEST_WHERE);

		query.append(_FINDER_COLUMN_RECEIVERUSERID_RECEIVERUSERID_2);

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
			query.append(MemberRequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(receiverUserId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(memberRequest);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MemberRequest> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the member requests where receiverUserId = &#63; from the database.
	 *
	 * @param receiverUserId the receiver user ID
	 */
	@Override
	public void removeByReceiverUserId(long receiverUserId) {
		for (MemberRequest memberRequest : findByReceiverUserId(
				receiverUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(memberRequest);
		}
	}

	/**
	 * Returns the number of member requests where receiverUserId = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @return the number of matching member requests
	 */
	@Override
	public int countByReceiverUserId(long receiverUserId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_RECEIVERUSERID;

		Object[] finderArgs = new Object[] { receiverUserId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MEMBERREQUEST_WHERE);

			query.append(_FINDER_COLUMN_RECEIVERUSERID_RECEIVERUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(receiverUserId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_RECEIVERUSERID_RECEIVERUSERID_2 = "memberRequest.receiverUserId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_R_S = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED,
			MemberRequestImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByR_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED,
			MemberRequestImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByR_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			MemberRequestModelImpl.RECEIVERUSERID_COLUMN_BITMASK |
			MemberRequestModelImpl.STATUS_COLUMN_BITMASK |
			MemberRequestModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_R_S = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByR_S",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the member requests where receiverUserId = &#63; and status = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @return the matching member requests
	 */
	@Override
	public List<MemberRequest> findByR_S(long receiverUserId, int status) {
		return findByR_S(receiverUserId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the member requests where receiverUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MemberRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param start the lower bound of the range of member requests
	 * @param end the upper bound of the range of member requests (not inclusive)
	 * @return the range of matching member requests
	 */
	@Override
	public List<MemberRequest> findByR_S(long receiverUserId, int status,
		int start, int end) {
		return findByR_S(receiverUserId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the member requests where receiverUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MemberRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param start the lower bound of the range of member requests
	 * @param end the upper bound of the range of member requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching member requests
	 */
	@Override
	public List<MemberRequest> findByR_S(long receiverUserId, int status,
		int start, int end, OrderByComparator<MemberRequest> orderByComparator) {
		return findByR_S(receiverUserId, status, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the member requests where receiverUserId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MemberRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param start the lower bound of the range of member requests
	 * @param end the upper bound of the range of member requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching member requests
	 */
	@Override
	public List<MemberRequest> findByR_S(long receiverUserId, int status,
		int start, int end, OrderByComparator<MemberRequest> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S;
			finderArgs = new Object[] { receiverUserId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_S;
			finderArgs = new Object[] {
					receiverUserId, status,
					
					start, end, orderByComparator
				};
		}

		List<MemberRequest> list = null;

		if (retrieveFromCache) {
			list = (List<MemberRequest>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MemberRequest memberRequest : list) {
					if ((receiverUserId != memberRequest.getReceiverUserId()) ||
							(status != memberRequest.getStatus())) {
						list = null;

						break;
					}
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

			query.append(_SQL_SELECT_MEMBERREQUEST_WHERE);

			query.append(_FINDER_COLUMN_R_S_RECEIVERUSERID_2);

			query.append(_FINDER_COLUMN_R_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MemberRequestModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(receiverUserId);

				qPos.add(status);

				if (!pagination) {
					list = (List<MemberRequest>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MemberRequest>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first member request in the ordered set where receiverUserId = &#63; and status = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching member request
	 * @throws NoSuchMemberRequestException if a matching member request could not be found
	 */
	@Override
	public MemberRequest findByR_S_First(long receiverUserId, int status,
		OrderByComparator<MemberRequest> orderByComparator)
		throws NoSuchMemberRequestException {
		MemberRequest memberRequest = fetchByR_S_First(receiverUserId, status,
				orderByComparator);

		if (memberRequest != null) {
			return memberRequest;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("receiverUserId=");
		msg.append(receiverUserId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMemberRequestException(msg.toString());
	}

	/**
	 * Returns the first member request in the ordered set where receiverUserId = &#63; and status = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching member request, or <code>null</code> if a matching member request could not be found
	 */
	@Override
	public MemberRequest fetchByR_S_First(long receiverUserId, int status,
		OrderByComparator<MemberRequest> orderByComparator) {
		List<MemberRequest> list = findByR_S(receiverUserId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last member request in the ordered set where receiverUserId = &#63; and status = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching member request
	 * @throws NoSuchMemberRequestException if a matching member request could not be found
	 */
	@Override
	public MemberRequest findByR_S_Last(long receiverUserId, int status,
		OrderByComparator<MemberRequest> orderByComparator)
		throws NoSuchMemberRequestException {
		MemberRequest memberRequest = fetchByR_S_Last(receiverUserId, status,
				orderByComparator);

		if (memberRequest != null) {
			return memberRequest;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("receiverUserId=");
		msg.append(receiverUserId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMemberRequestException(msg.toString());
	}

	/**
	 * Returns the last member request in the ordered set where receiverUserId = &#63; and status = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching member request, or <code>null</code> if a matching member request could not be found
	 */
	@Override
	public MemberRequest fetchByR_S_Last(long receiverUserId, int status,
		OrderByComparator<MemberRequest> orderByComparator) {
		int count = countByR_S(receiverUserId, status);

		if (count == 0) {
			return null;
		}

		List<MemberRequest> list = findByR_S(receiverUserId, status, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the member requests before and after the current member request in the ordered set where receiverUserId = &#63; and status = &#63;.
	 *
	 * @param memberRequestId the primary key of the current member request
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next member request
	 * @throws NoSuchMemberRequestException if a member request with the primary key could not be found
	 */
	@Override
	public MemberRequest[] findByR_S_PrevAndNext(long memberRequestId,
		long receiverUserId, int status,
		OrderByComparator<MemberRequest> orderByComparator)
		throws NoSuchMemberRequestException {
		MemberRequest memberRequest = findByPrimaryKey(memberRequestId);

		Session session = null;

		try {
			session = openSession();

			MemberRequest[] array = new MemberRequestImpl[3];

			array[0] = getByR_S_PrevAndNext(session, memberRequest,
					receiverUserId, status, orderByComparator, true);

			array[1] = memberRequest;

			array[2] = getByR_S_PrevAndNext(session, memberRequest,
					receiverUserId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MemberRequest getByR_S_PrevAndNext(Session session,
		MemberRequest memberRequest, long receiverUserId, int status,
		OrderByComparator<MemberRequest> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MEMBERREQUEST_WHERE);

		query.append(_FINDER_COLUMN_R_S_RECEIVERUSERID_2);

		query.append(_FINDER_COLUMN_R_S_STATUS_2);

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
			query.append(MemberRequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(receiverUserId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(memberRequest);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MemberRequest> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the member requests where receiverUserId = &#63; and status = &#63; from the database.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 */
	@Override
	public void removeByR_S(long receiverUserId, int status) {
		for (MemberRequest memberRequest : findByR_S(receiverUserId, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(memberRequest);
		}
	}

	/**
	 * Returns the number of member requests where receiverUserId = &#63; and status = &#63;.
	 *
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @return the number of matching member requests
	 */
	@Override
	public int countByR_S(long receiverUserId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_R_S;

		Object[] finderArgs = new Object[] { receiverUserId, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_MEMBERREQUEST_WHERE);

			query.append(_FINDER_COLUMN_R_S_RECEIVERUSERID_2);

			query.append(_FINDER_COLUMN_R_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(receiverUserId);

				qPos.add(status);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_R_S_RECEIVERUSERID_2 = "memberRequest.receiverUserId = ? AND ";
	private static final String _FINDER_COLUMN_R_S_STATUS_2 = "memberRequest.status = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_R_S = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED,
			MemberRequestImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByG_R_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			MemberRequestModelImpl.GROUPID_COLUMN_BITMASK |
			MemberRequestModelImpl.RECEIVERUSERID_COLUMN_BITMASK |
			MemberRequestModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_R_S = new FinderPath(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_R_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns the member request where groupId = &#63; and receiverUserId = &#63; and status = &#63; or throws a {@link NoSuchMemberRequestException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @return the matching member request
	 * @throws NoSuchMemberRequestException if a matching member request could not be found
	 */
	@Override
	public MemberRequest findByG_R_S(long groupId, long receiverUserId,
		int status) throws NoSuchMemberRequestException {
		MemberRequest memberRequest = fetchByG_R_S(groupId, receiverUserId,
				status);

		if (memberRequest == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", receiverUserId=");
			msg.append(receiverUserId);

			msg.append(", status=");
			msg.append(status);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchMemberRequestException(msg.toString());
		}

		return memberRequest;
	}

	/**
	 * Returns the member request where groupId = &#63; and receiverUserId = &#63; and status = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @return the matching member request, or <code>null</code> if a matching member request could not be found
	 */
	@Override
	public MemberRequest fetchByG_R_S(long groupId, long receiverUserId,
		int status) {
		return fetchByG_R_S(groupId, receiverUserId, status, true);
	}

	/**
	 * Returns the member request where groupId = &#63; and receiverUserId = &#63; and status = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching member request, or <code>null</code> if a matching member request could not be found
	 */
	@Override
	public MemberRequest fetchByG_R_S(long groupId, long receiverUserId,
		int status, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, receiverUserId, status };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_G_R_S,
					finderArgs, this);
		}

		if (result instanceof MemberRequest) {
			MemberRequest memberRequest = (MemberRequest)result;

			if ((groupId != memberRequest.getGroupId()) ||
					(receiverUserId != memberRequest.getReceiverUserId()) ||
					(status != memberRequest.getStatus())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_MEMBERREQUEST_WHERE);

			query.append(_FINDER_COLUMN_G_R_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_R_S_RECEIVERUSERID_2);

			query.append(_FINDER_COLUMN_G_R_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(receiverUserId);

				qPos.add(status);

				List<MemberRequest> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_G_R_S,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"MemberRequestPersistenceImpl.fetchByG_R_S(long, long, int, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					MemberRequest memberRequest = list.get(0);

					result = memberRequest;

					cacheResult(memberRequest);

					if ((memberRequest.getGroupId() != groupId) ||
							(memberRequest.getReceiverUserId() != receiverUserId) ||
							(memberRequest.getStatus() != status)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_G_R_S,
							finderArgs, memberRequest);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_G_R_S, finderArgs);

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
			return (MemberRequest)result;
		}
	}

	/**
	 * Removes the member request where groupId = &#63; and receiverUserId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @return the member request that was removed
	 */
	@Override
	public MemberRequest removeByG_R_S(long groupId, long receiverUserId,
		int status) throws NoSuchMemberRequestException {
		MemberRequest memberRequest = findByG_R_S(groupId, receiverUserId,
				status);

		return remove(memberRequest);
	}

	/**
	 * Returns the number of member requests where groupId = &#63; and receiverUserId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param receiverUserId the receiver user ID
	 * @param status the status
	 * @return the number of matching member requests
	 */
	@Override
	public int countByG_R_S(long groupId, long receiverUserId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_R_S;

		Object[] finderArgs = new Object[] { groupId, receiverUserId, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_MEMBERREQUEST_WHERE);

			query.append(_FINDER_COLUMN_G_R_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_R_S_RECEIVERUSERID_2);

			query.append(_FINDER_COLUMN_G_R_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(receiverUserId);

				qPos.add(status);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_R_S_GROUPID_2 = "memberRequest.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_R_S_RECEIVERUSERID_2 = "memberRequest.receiverUserId = ? AND ";
	private static final String _FINDER_COLUMN_G_R_S_STATUS_2 = "memberRequest.status = ?";

	public MemberRequestPersistenceImpl() {
		setModelClass(MemberRequest.class);
	}

	/**
	 * Caches the member request in the entity cache if it is enabled.
	 *
	 * @param memberRequest the member request
	 */
	@Override
	public void cacheResult(MemberRequest memberRequest) {
		entityCache.putResult(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestImpl.class, memberRequest.getPrimaryKey(),
			memberRequest);

		finderCache.putResult(FINDER_PATH_FETCH_BY_KEY,
			new Object[] { memberRequest.getKey() }, memberRequest);

		finderCache.putResult(FINDER_PATH_FETCH_BY_G_R_S,
			new Object[] {
				memberRequest.getGroupId(), memberRequest.getReceiverUserId(),
				memberRequest.getStatus()
			}, memberRequest);

		memberRequest.resetOriginalValues();
	}

	/**
	 * Caches the member requests in the entity cache if it is enabled.
	 *
	 * @param memberRequests the member requests
	 */
	@Override
	public void cacheResult(List<MemberRequest> memberRequests) {
		for (MemberRequest memberRequest : memberRequests) {
			if (entityCache.getResult(
						MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
						MemberRequestImpl.class, memberRequest.getPrimaryKey()) == null) {
				cacheResult(memberRequest);
			}
			else {
				memberRequest.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all member requests.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MemberRequestImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the member request.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MemberRequest memberRequest) {
		entityCache.removeResult(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestImpl.class, memberRequest.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((MemberRequestModelImpl)memberRequest);
	}

	@Override
	public void clearCache(List<MemberRequest> memberRequests) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MemberRequest memberRequest : memberRequests) {
			entityCache.removeResult(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
				MemberRequestImpl.class, memberRequest.getPrimaryKey());

			clearUniqueFindersCache((MemberRequestModelImpl)memberRequest);
		}
	}

	protected void cacheUniqueFindersCache(
		MemberRequestModelImpl memberRequestModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] { memberRequestModelImpl.getKey() };

			finderCache.putResult(FINDER_PATH_COUNT_BY_KEY, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_KEY, args,
				memberRequestModelImpl);

			args = new Object[] {
					memberRequestModelImpl.getGroupId(),
					memberRequestModelImpl.getReceiverUserId(),
					memberRequestModelImpl.getStatus()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_G_R_S, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_G_R_S, args,
				memberRequestModelImpl);
		}
		else {
			if ((memberRequestModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_KEY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { memberRequestModelImpl.getKey() };

				finderCache.putResult(FINDER_PATH_COUNT_BY_KEY, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_KEY, args,
					memberRequestModelImpl);
			}

			if ((memberRequestModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_G_R_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						memberRequestModelImpl.getGroupId(),
						memberRequestModelImpl.getReceiverUserId(),
						memberRequestModelImpl.getStatus()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_G_R_S, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_G_R_S, args,
					memberRequestModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		MemberRequestModelImpl memberRequestModelImpl) {
		Object[] args = new Object[] { memberRequestModelImpl.getKey() };

		finderCache.removeResult(FINDER_PATH_COUNT_BY_KEY, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_KEY, args);

		if ((memberRequestModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_KEY.getColumnBitmask()) != 0) {
			args = new Object[] { memberRequestModelImpl.getOriginalKey() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_KEY, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_KEY, args);
		}

		args = new Object[] {
				memberRequestModelImpl.getGroupId(),
				memberRequestModelImpl.getReceiverUserId(),
				memberRequestModelImpl.getStatus()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_G_R_S, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_G_R_S, args);

		if ((memberRequestModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_R_S.getColumnBitmask()) != 0) {
			args = new Object[] {
					memberRequestModelImpl.getOriginalGroupId(),
					memberRequestModelImpl.getOriginalReceiverUserId(),
					memberRequestModelImpl.getOriginalStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_G_R_S, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_G_R_S, args);
		}
	}

	/**
	 * Creates a new member request with the primary key. Does not add the member request to the database.
	 *
	 * @param memberRequestId the primary key for the new member request
	 * @return the new member request
	 */
	@Override
	public MemberRequest create(long memberRequestId) {
		MemberRequest memberRequest = new MemberRequestImpl();

		memberRequest.setNew(true);
		memberRequest.setPrimaryKey(memberRequestId);

		memberRequest.setCompanyId(companyProvider.getCompanyId());

		return memberRequest;
	}

	/**
	 * Removes the member request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param memberRequestId the primary key of the member request
	 * @return the member request that was removed
	 * @throws NoSuchMemberRequestException if a member request with the primary key could not be found
	 */
	@Override
	public MemberRequest remove(long memberRequestId)
		throws NoSuchMemberRequestException {
		return remove((Serializable)memberRequestId);
	}

	/**
	 * Removes the member request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the member request
	 * @return the member request that was removed
	 * @throws NoSuchMemberRequestException if a member request with the primary key could not be found
	 */
	@Override
	public MemberRequest remove(Serializable primaryKey)
		throws NoSuchMemberRequestException {
		Session session = null;

		try {
			session = openSession();

			MemberRequest memberRequest = (MemberRequest)session.get(MemberRequestImpl.class,
					primaryKey);

			if (memberRequest == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMemberRequestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(memberRequest);
		}
		catch (NoSuchMemberRequestException nsee) {
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
	protected MemberRequest removeImpl(MemberRequest memberRequest) {
		memberRequest = toUnwrappedModel(memberRequest);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(memberRequest)) {
				memberRequest = (MemberRequest)session.get(MemberRequestImpl.class,
						memberRequest.getPrimaryKeyObj());
			}

			if (memberRequest != null) {
				session.delete(memberRequest);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (memberRequest != null) {
			clearCache(memberRequest);
		}

		return memberRequest;
	}

	@Override
	public MemberRequest updateImpl(MemberRequest memberRequest) {
		memberRequest = toUnwrappedModel(memberRequest);

		boolean isNew = memberRequest.isNew();

		MemberRequestModelImpl memberRequestModelImpl = (MemberRequestModelImpl)memberRequest;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (memberRequest.getCreateDate() == null)) {
			if (serviceContext == null) {
				memberRequest.setCreateDate(now);
			}
			else {
				memberRequest.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!memberRequestModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				memberRequest.setModifiedDate(now);
			}
			else {
				memberRequest.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (memberRequest.isNew()) {
				session.save(memberRequest);

				memberRequest.setNew(false);
			}
			else {
				memberRequest = (MemberRequest)session.merge(memberRequest);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !MemberRequestModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((memberRequestModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECEIVERUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						memberRequestModelImpl.getOriginalReceiverUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RECEIVERUSERID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECEIVERUSERID,
					args);

				args = new Object[] { memberRequestModelImpl.getReceiverUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_RECEIVERUSERID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECEIVERUSERID,
					args);
			}

			if ((memberRequestModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						memberRequestModelImpl.getOriginalReceiverUserId(),
						memberRequestModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_R_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S,
					args);

				args = new Object[] {
						memberRequestModelImpl.getReceiverUserId(),
						memberRequestModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_R_S, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S,
					args);
			}
		}

		entityCache.putResult(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
			MemberRequestImpl.class, memberRequest.getPrimaryKey(),
			memberRequest, false);

		clearUniqueFindersCache(memberRequestModelImpl);
		cacheUniqueFindersCache(memberRequestModelImpl, isNew);

		memberRequest.resetOriginalValues();

		return memberRequest;
	}

	protected MemberRequest toUnwrappedModel(MemberRequest memberRequest) {
		if (memberRequest instanceof MemberRequestImpl) {
			return memberRequest;
		}

		MemberRequestImpl memberRequestImpl = new MemberRequestImpl();

		memberRequestImpl.setNew(memberRequest.isNew());
		memberRequestImpl.setPrimaryKey(memberRequest.getPrimaryKey());

		memberRequestImpl.setMemberRequestId(memberRequest.getMemberRequestId());
		memberRequestImpl.setGroupId(memberRequest.getGroupId());
		memberRequestImpl.setCompanyId(memberRequest.getCompanyId());
		memberRequestImpl.setUserId(memberRequest.getUserId());
		memberRequestImpl.setUserName(memberRequest.getUserName());
		memberRequestImpl.setCreateDate(memberRequest.getCreateDate());
		memberRequestImpl.setModifiedDate(memberRequest.getModifiedDate());
		memberRequestImpl.setKey(memberRequest.getKey());
		memberRequestImpl.setReceiverUserId(memberRequest.getReceiverUserId());
		memberRequestImpl.setInvitedRoleId(memberRequest.getInvitedRoleId());
		memberRequestImpl.setInvitedTeamId(memberRequest.getInvitedTeamId());
		memberRequestImpl.setStatus(memberRequest.getStatus());

		return memberRequestImpl;
	}

	/**
	 * Returns the member request with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the member request
	 * @return the member request
	 * @throws NoSuchMemberRequestException if a member request with the primary key could not be found
	 */
	@Override
	public MemberRequest findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMemberRequestException {
		MemberRequest memberRequest = fetchByPrimaryKey(primaryKey);

		if (memberRequest == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMemberRequestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return memberRequest;
	}

	/**
	 * Returns the member request with the primary key or throws a {@link NoSuchMemberRequestException} if it could not be found.
	 *
	 * @param memberRequestId the primary key of the member request
	 * @return the member request
	 * @throws NoSuchMemberRequestException if a member request with the primary key could not be found
	 */
	@Override
	public MemberRequest findByPrimaryKey(long memberRequestId)
		throws NoSuchMemberRequestException {
		return findByPrimaryKey((Serializable)memberRequestId);
	}

	/**
	 * Returns the member request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the member request
	 * @return the member request, or <code>null</code> if a member request with the primary key could not be found
	 */
	@Override
	public MemberRequest fetchByPrimaryKey(Serializable primaryKey) {
		MemberRequest memberRequest = (MemberRequest)entityCache.getResult(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
				MemberRequestImpl.class, primaryKey);

		if (memberRequest == _nullMemberRequest) {
			return null;
		}

		if (memberRequest == null) {
			Session session = null;

			try {
				session = openSession();

				memberRequest = (MemberRequest)session.get(MemberRequestImpl.class,
						primaryKey);

				if (memberRequest != null) {
					cacheResult(memberRequest);
				}
				else {
					entityCache.putResult(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
						MemberRequestImpl.class, primaryKey, _nullMemberRequest);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
					MemberRequestImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return memberRequest;
	}

	/**
	 * Returns the member request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param memberRequestId the primary key of the member request
	 * @return the member request, or <code>null</code> if a member request with the primary key could not be found
	 */
	@Override
	public MemberRequest fetchByPrimaryKey(long memberRequestId) {
		return fetchByPrimaryKey((Serializable)memberRequestId);
	}

	@Override
	public Map<Serializable, MemberRequest> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, MemberRequest> map = new HashMap<Serializable, MemberRequest>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			MemberRequest memberRequest = fetchByPrimaryKey(primaryKey);

			if (memberRequest != null) {
				map.put(primaryKey, memberRequest);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			MemberRequest memberRequest = (MemberRequest)entityCache.getResult(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
					MemberRequestImpl.class, primaryKey);

			if (memberRequest == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, memberRequest);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MEMBERREQUEST_WHERE_PKS_IN);

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

			for (MemberRequest memberRequest : (List<MemberRequest>)q.list()) {
				map.put(memberRequest.getPrimaryKeyObj(), memberRequest);

				cacheResult(memberRequest);

				uncachedPrimaryKeys.remove(memberRequest.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(MemberRequestModelImpl.ENTITY_CACHE_ENABLED,
					MemberRequestImpl.class, primaryKey, _nullMemberRequest);
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
	 * Returns all the member requests.
	 *
	 * @return the member requests
	 */
	@Override
	public List<MemberRequest> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the member requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MemberRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of member requests
	 * @param end the upper bound of the range of member requests (not inclusive)
	 * @return the range of member requests
	 */
	@Override
	public List<MemberRequest> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the member requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MemberRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of member requests
	 * @param end the upper bound of the range of member requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of member requests
	 */
	@Override
	public List<MemberRequest> findAll(int start, int end,
		OrderByComparator<MemberRequest> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the member requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MemberRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of member requests
	 * @param end the upper bound of the range of member requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of member requests
	 */
	@Override
	public List<MemberRequest> findAll(int start, int end,
		OrderByComparator<MemberRequest> orderByComparator,
		boolean retrieveFromCache) {
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

		List<MemberRequest> list = null;

		if (retrieveFromCache) {
			list = (List<MemberRequest>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_MEMBERREQUEST);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MEMBERREQUEST;

				if (pagination) {
					sql = sql.concat(MemberRequestModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MemberRequest>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MemberRequest>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the member requests from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MemberRequest memberRequest : findAll()) {
			remove(memberRequest);
		}
	}

	/**
	 * Returns the number of member requests.
	 *
	 * @return the number of member requests
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MEMBERREQUEST);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
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
		return MemberRequestModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the member request persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MemberRequestImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_MEMBERREQUEST = "SELECT memberRequest FROM MemberRequest memberRequest";
	private static final String _SQL_SELECT_MEMBERREQUEST_WHERE_PKS_IN = "SELECT memberRequest FROM MemberRequest memberRequest WHERE memberRequestId IN (";
	private static final String _SQL_SELECT_MEMBERREQUEST_WHERE = "SELECT memberRequest FROM MemberRequest memberRequest WHERE ";
	private static final String _SQL_COUNT_MEMBERREQUEST = "SELECT COUNT(memberRequest) FROM MemberRequest memberRequest";
	private static final String _SQL_COUNT_MEMBERREQUEST_WHERE = "SELECT COUNT(memberRequest) FROM MemberRequest memberRequest WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "memberRequest.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MemberRequest exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MemberRequest exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(MemberRequestPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"key"
			});
	private static final MemberRequest _nullMemberRequest = new MemberRequestImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<MemberRequest> toCacheModel() {
				return _nullMemberRequestCacheModel;
			}
		};

	private static final CacheModel<MemberRequest> _nullMemberRequestCacheModel = new CacheModel<MemberRequest>() {
			@Override
			public MemberRequest toEntityModel() {
				return _nullMemberRequest;
			}
		};
}