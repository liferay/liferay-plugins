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

package com.liferay.meeting.webex.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.meeting.webex.exception.NoSuchAccountException;
import com.liferay.meeting.webex.model.WebExAccount;
import com.liferay.meeting.webex.model.impl.WebExAccountImpl;
import com.liferay.meeting.webex.model.impl.WebExAccountModelImpl;
import com.liferay.meeting.webex.service.persistence.WebExAccountPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

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
 * The persistence implementation for the web ex account service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Anant Singh
 * @see WebExAccountPersistence
 * @see com.liferay.meeting.webex.service.persistence.WebExAccountUtil
 * @generated
 */
@ProviderType
public class WebExAccountPersistenceImpl extends BasePersistenceImpl<WebExAccount>
	implements WebExAccountPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WebExAccountUtil} to access the web ex account persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WebExAccountImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountModelImpl.FINDER_CACHE_ENABLED, WebExAccountImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountModelImpl.FINDER_CACHE_ENABLED, WebExAccountImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountModelImpl.FINDER_CACHE_ENABLED, WebExAccountImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountModelImpl.FINDER_CACHE_ENABLED, WebExAccountImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			WebExAccountModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the web ex accounts where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching web ex accounts
	 */
	@Override
	public List<WebExAccount> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the web ex accounts where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of web ex accounts
	 * @param end the upper bound of the range of web ex accounts (not inclusive)
	 * @return the range of matching web ex accounts
	 */
	@Override
	public List<WebExAccount> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the web ex accounts where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of web ex accounts
	 * @param end the upper bound of the range of web ex accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching web ex accounts
	 */
	@Override
	public List<WebExAccount> findByUuid(String uuid, int start, int end,
		OrderByComparator<WebExAccount> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the web ex accounts where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of web ex accounts
	 * @param end the upper bound of the range of web ex accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching web ex accounts
	 */
	@Override
	public List<WebExAccount> findByUuid(String uuid, int start, int end,
		OrderByComparator<WebExAccount> orderByComparator,
		boolean retrieveFromCache) {
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

		List<WebExAccount> list = null;

		if (retrieveFromCache) {
			list = (List<WebExAccount>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (WebExAccount webExAccount : list) {
					if (!Validator.equals(uuid, webExAccount.getUuid())) {
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
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_WEBEXACCOUNT_WHERE);

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
				query.append(WebExAccountModelImpl.ORDER_BY_JPQL);
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
					list = (List<WebExAccount>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WebExAccount>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first web ex account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching web ex account
	 * @throws NoSuchAccountException if a matching web ex account could not be found
	 */
	@Override
	public WebExAccount findByUuid_First(String uuid,
		OrderByComparator<WebExAccount> orderByComparator)
		throws NoSuchAccountException {
		WebExAccount webExAccount = fetchByUuid_First(uuid, orderByComparator);

		if (webExAccount != null) {
			return webExAccount;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountException(msg.toString());
	}

	/**
	 * Returns the first web ex account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching web ex account, or <code>null</code> if a matching web ex account could not be found
	 */
	@Override
	public WebExAccount fetchByUuid_First(String uuid,
		OrderByComparator<WebExAccount> orderByComparator) {
		List<WebExAccount> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last web ex account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching web ex account
	 * @throws NoSuchAccountException if a matching web ex account could not be found
	 */
	@Override
	public WebExAccount findByUuid_Last(String uuid,
		OrderByComparator<WebExAccount> orderByComparator)
		throws NoSuchAccountException {
		WebExAccount webExAccount = fetchByUuid_Last(uuid, orderByComparator);

		if (webExAccount != null) {
			return webExAccount;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountException(msg.toString());
	}

	/**
	 * Returns the last web ex account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching web ex account, or <code>null</code> if a matching web ex account could not be found
	 */
	@Override
	public WebExAccount fetchByUuid_Last(String uuid,
		OrderByComparator<WebExAccount> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<WebExAccount> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the web ex accounts before and after the current web ex account in the ordered set where uuid = &#63;.
	 *
	 * @param webExAccountId the primary key of the current web ex account
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next web ex account
	 * @throws NoSuchAccountException if a web ex account with the primary key could not be found
	 */
	@Override
	public WebExAccount[] findByUuid_PrevAndNext(long webExAccountId,
		String uuid, OrderByComparator<WebExAccount> orderByComparator)
		throws NoSuchAccountException {
		WebExAccount webExAccount = findByPrimaryKey(webExAccountId);

		Session session = null;

		try {
			session = openSession();

			WebExAccount[] array = new WebExAccountImpl[3];

			array[0] = getByUuid_PrevAndNext(session, webExAccount, uuid,
					orderByComparator, true);

			array[1] = webExAccount;

			array[2] = getByUuid_PrevAndNext(session, webExAccount, uuid,
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

	protected WebExAccount getByUuid_PrevAndNext(Session session,
		WebExAccount webExAccount, String uuid,
		OrderByComparator<WebExAccount> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WEBEXACCOUNT_WHERE);

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
			query.append(WebExAccountModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(webExAccount);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WebExAccount> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the web ex accounts where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (WebExAccount webExAccount : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(webExAccount);
		}
	}

	/**
	 * Returns the number of web ex accounts where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching web ex accounts
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WEBEXACCOUNT_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "webExAccount.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "webExAccount.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(webExAccount.uuid IS NULL OR webExAccount.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountModelImpl.FINDER_CACHE_ENABLED, WebExAccountImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			WebExAccountModelImpl.UUID_COLUMN_BITMASK |
			WebExAccountModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the web ex account where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchAccountException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching web ex account
	 * @throws NoSuchAccountException if a matching web ex account could not be found
	 */
	@Override
	public WebExAccount findByUUID_G(String uuid, long groupId)
		throws NoSuchAccountException {
		WebExAccount webExAccount = fetchByUUID_G(uuid, groupId);

		if (webExAccount == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchAccountException(msg.toString());
		}

		return webExAccount;
	}

	/**
	 * Returns the web ex account where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching web ex account, or <code>null</code> if a matching web ex account could not be found
	 */
	@Override
	public WebExAccount fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the web ex account where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching web ex account, or <code>null</code> if a matching web ex account could not be found
	 */
	@Override
	public WebExAccount fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof WebExAccount) {
			WebExAccount webExAccount = (WebExAccount)result;

			if (!Validator.equals(uuid, webExAccount.getUuid()) ||
					(groupId != webExAccount.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_WEBEXACCOUNT_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<WebExAccount> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					WebExAccount webExAccount = list.get(0);

					result = webExAccount;

					cacheResult(webExAccount);

					if ((webExAccount.getUuid() == null) ||
							!webExAccount.getUuid().equals(uuid) ||
							(webExAccount.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, webExAccount);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, finderArgs);

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
			return (WebExAccount)result;
		}
	}

	/**
	 * Removes the web ex account where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the web ex account that was removed
	 */
	@Override
	public WebExAccount removeByUUID_G(String uuid, long groupId)
		throws NoSuchAccountException {
		WebExAccount webExAccount = findByUUID_G(uuid, groupId);

		return remove(webExAccount);
	}

	/**
	 * Returns the number of web ex accounts where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching web ex accounts
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WEBEXACCOUNT_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "webExAccount.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "webExAccount.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(webExAccount.uuid IS NULL OR webExAccount.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "webExAccount.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountModelImpl.FINDER_CACHE_ENABLED, WebExAccountImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountModelImpl.FINDER_CACHE_ENABLED, WebExAccountImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			WebExAccountModelImpl.UUID_COLUMN_BITMASK |
			WebExAccountModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the web ex accounts where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching web ex accounts
	 */
	@Override
	public List<WebExAccount> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the web ex accounts where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of web ex accounts
	 * @param end the upper bound of the range of web ex accounts (not inclusive)
	 * @return the range of matching web ex accounts
	 */
	@Override
	public List<WebExAccount> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the web ex accounts where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of web ex accounts
	 * @param end the upper bound of the range of web ex accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching web ex accounts
	 */
	@Override
	public List<WebExAccount> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<WebExAccount> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the web ex accounts where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of web ex accounts
	 * @param end the upper bound of the range of web ex accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching web ex accounts
	 */
	@Override
	public List<WebExAccount> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<WebExAccount> orderByComparator,
		boolean retrieveFromCache) {
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

		List<WebExAccount> list = null;

		if (retrieveFromCache) {
			list = (List<WebExAccount>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (WebExAccount webExAccount : list) {
					if (!Validator.equals(uuid, webExAccount.getUuid()) ||
							(companyId != webExAccount.getCompanyId())) {
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
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_WEBEXACCOUNT_WHERE);

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
				query.append(WebExAccountModelImpl.ORDER_BY_JPQL);
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
					list = (List<WebExAccount>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WebExAccount>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first web ex account in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching web ex account
	 * @throws NoSuchAccountException if a matching web ex account could not be found
	 */
	@Override
	public WebExAccount findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<WebExAccount> orderByComparator)
		throws NoSuchAccountException {
		WebExAccount webExAccount = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (webExAccount != null) {
			return webExAccount;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountException(msg.toString());
	}

	/**
	 * Returns the first web ex account in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching web ex account, or <code>null</code> if a matching web ex account could not be found
	 */
	@Override
	public WebExAccount fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<WebExAccount> orderByComparator) {
		List<WebExAccount> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last web ex account in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching web ex account
	 * @throws NoSuchAccountException if a matching web ex account could not be found
	 */
	@Override
	public WebExAccount findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<WebExAccount> orderByComparator)
		throws NoSuchAccountException {
		WebExAccount webExAccount = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (webExAccount != null) {
			return webExAccount;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountException(msg.toString());
	}

	/**
	 * Returns the last web ex account in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching web ex account, or <code>null</code> if a matching web ex account could not be found
	 */
	@Override
	public WebExAccount fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<WebExAccount> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<WebExAccount> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the web ex accounts before and after the current web ex account in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param webExAccountId the primary key of the current web ex account
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next web ex account
	 * @throws NoSuchAccountException if a web ex account with the primary key could not be found
	 */
	@Override
	public WebExAccount[] findByUuid_C_PrevAndNext(long webExAccountId,
		String uuid, long companyId,
		OrderByComparator<WebExAccount> orderByComparator)
		throws NoSuchAccountException {
		WebExAccount webExAccount = findByPrimaryKey(webExAccountId);

		Session session = null;

		try {
			session = openSession();

			WebExAccount[] array = new WebExAccountImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, webExAccount, uuid,
					companyId, orderByComparator, true);

			array[1] = webExAccount;

			array[2] = getByUuid_C_PrevAndNext(session, webExAccount, uuid,
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

	protected WebExAccount getByUuid_C_PrevAndNext(Session session,
		WebExAccount webExAccount, String uuid, long companyId,
		OrderByComparator<WebExAccount> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_WEBEXACCOUNT_WHERE);

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
			query.append(WebExAccountModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(webExAccount);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WebExAccount> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the web ex accounts where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (WebExAccount webExAccount : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(webExAccount);
		}
	}

	/**
	 * Returns the number of web ex accounts where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching web ex accounts
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WEBEXACCOUNT_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "webExAccount.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "webExAccount.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(webExAccount.uuid IS NULL OR webExAccount.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "webExAccount.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_W = new FinderPath(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountModelImpl.FINDER_CACHE_ENABLED, WebExAccountImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_W",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_W = new FinderPath(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountModelImpl.FINDER_CACHE_ENABLED, WebExAccountImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_W",
			new String[] { Long.class.getName(), Long.class.getName() },
			WebExAccountModelImpl.GROUPID_COLUMN_BITMASK |
			WebExAccountModelImpl.WEBEXSITEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_W = new FinderPath(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_W",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the web ex accounts where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @return the matching web ex accounts
	 */
	@Override
	public List<WebExAccount> findByG_W(long groupId, long webExSiteId) {
		return findByG_W(groupId, webExSiteId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the web ex accounts where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @param start the lower bound of the range of web ex accounts
	 * @param end the upper bound of the range of web ex accounts (not inclusive)
	 * @return the range of matching web ex accounts
	 */
	@Override
	public List<WebExAccount> findByG_W(long groupId, long webExSiteId,
		int start, int end) {
		return findByG_W(groupId, webExSiteId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the web ex accounts where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @param start the lower bound of the range of web ex accounts
	 * @param end the upper bound of the range of web ex accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching web ex accounts
	 */
	@Override
	public List<WebExAccount> findByG_W(long groupId, long webExSiteId,
		int start, int end, OrderByComparator<WebExAccount> orderByComparator) {
		return findByG_W(groupId, webExSiteId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the web ex accounts where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @param start the lower bound of the range of web ex accounts
	 * @param end the upper bound of the range of web ex accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching web ex accounts
	 */
	@Override
	public List<WebExAccount> findByG_W(long groupId, long webExSiteId,
		int start, int end, OrderByComparator<WebExAccount> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_W;
			finderArgs = new Object[] { groupId, webExSiteId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_W;
			finderArgs = new Object[] {
					groupId, webExSiteId,
					
					start, end, orderByComparator
				};
		}

		List<WebExAccount> list = null;

		if (retrieveFromCache) {
			list = (List<WebExAccount>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (WebExAccount webExAccount : list) {
					if ((groupId != webExAccount.getGroupId()) ||
							(webExSiteId != webExAccount.getWebExSiteId())) {
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
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_WEBEXACCOUNT_WHERE);

			query.append(_FINDER_COLUMN_G_W_GROUPID_2);

			query.append(_FINDER_COLUMN_G_W_WEBEXSITEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WebExAccountModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(webExSiteId);

				if (!pagination) {
					list = (List<WebExAccount>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WebExAccount>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first web ex account in the ordered set where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching web ex account
	 * @throws NoSuchAccountException if a matching web ex account could not be found
	 */
	@Override
	public WebExAccount findByG_W_First(long groupId, long webExSiteId,
		OrderByComparator<WebExAccount> orderByComparator)
		throws NoSuchAccountException {
		WebExAccount webExAccount = fetchByG_W_First(groupId, webExSiteId,
				orderByComparator);

		if (webExAccount != null) {
			return webExAccount;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", webExSiteId=");
		msg.append(webExSiteId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountException(msg.toString());
	}

	/**
	 * Returns the first web ex account in the ordered set where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching web ex account, or <code>null</code> if a matching web ex account could not be found
	 */
	@Override
	public WebExAccount fetchByG_W_First(long groupId, long webExSiteId,
		OrderByComparator<WebExAccount> orderByComparator) {
		List<WebExAccount> list = findByG_W(groupId, webExSiteId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last web ex account in the ordered set where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching web ex account
	 * @throws NoSuchAccountException if a matching web ex account could not be found
	 */
	@Override
	public WebExAccount findByG_W_Last(long groupId, long webExSiteId,
		OrderByComparator<WebExAccount> orderByComparator)
		throws NoSuchAccountException {
		WebExAccount webExAccount = fetchByG_W_Last(groupId, webExSiteId,
				orderByComparator);

		if (webExAccount != null) {
			return webExAccount;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", webExSiteId=");
		msg.append(webExSiteId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountException(msg.toString());
	}

	/**
	 * Returns the last web ex account in the ordered set where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching web ex account, or <code>null</code> if a matching web ex account could not be found
	 */
	@Override
	public WebExAccount fetchByG_W_Last(long groupId, long webExSiteId,
		OrderByComparator<WebExAccount> orderByComparator) {
		int count = countByG_W(groupId, webExSiteId);

		if (count == 0) {
			return null;
		}

		List<WebExAccount> list = findByG_W(groupId, webExSiteId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the web ex accounts before and after the current web ex account in the ordered set where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * @param webExAccountId the primary key of the current web ex account
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next web ex account
	 * @throws NoSuchAccountException if a web ex account with the primary key could not be found
	 */
	@Override
	public WebExAccount[] findByG_W_PrevAndNext(long webExAccountId,
		long groupId, long webExSiteId,
		OrderByComparator<WebExAccount> orderByComparator)
		throws NoSuchAccountException {
		WebExAccount webExAccount = findByPrimaryKey(webExAccountId);

		Session session = null;

		try {
			session = openSession();

			WebExAccount[] array = new WebExAccountImpl[3];

			array[0] = getByG_W_PrevAndNext(session, webExAccount, groupId,
					webExSiteId, orderByComparator, true);

			array[1] = webExAccount;

			array[2] = getByG_W_PrevAndNext(session, webExAccount, groupId,
					webExSiteId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WebExAccount getByG_W_PrevAndNext(Session session,
		WebExAccount webExAccount, long groupId, long webExSiteId,
		OrderByComparator<WebExAccount> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_WEBEXACCOUNT_WHERE);

		query.append(_FINDER_COLUMN_G_W_GROUPID_2);

		query.append(_FINDER_COLUMN_G_W_WEBEXSITEID_2);

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
			query.append(WebExAccountModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(webExSiteId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(webExAccount);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WebExAccount> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the web ex accounts that the user has permission to view where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @return the matching web ex accounts that the user has permission to view
	 */
	@Override
	public List<WebExAccount> filterFindByG_W(long groupId, long webExSiteId) {
		return filterFindByG_W(groupId, webExSiteId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the web ex accounts that the user has permission to view where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @param start the lower bound of the range of web ex accounts
	 * @param end the upper bound of the range of web ex accounts (not inclusive)
	 * @return the range of matching web ex accounts that the user has permission to view
	 */
	@Override
	public List<WebExAccount> filterFindByG_W(long groupId, long webExSiteId,
		int start, int end) {
		return filterFindByG_W(groupId, webExSiteId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the web ex accounts that the user has permissions to view where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @param start the lower bound of the range of web ex accounts
	 * @param end the upper bound of the range of web ex accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching web ex accounts that the user has permission to view
	 */
	@Override
	public List<WebExAccount> filterFindByG_W(long groupId, long webExSiteId,
		int start, int end, OrderByComparator<WebExAccount> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_W(groupId, webExSiteId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_WEBEXACCOUNT_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_WEBEXACCOUNT_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_W_GROUPID_2);

		query.append(_FINDER_COLUMN_G_W_WEBEXSITEID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_WEBEXACCOUNT_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(WebExAccountModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(WebExAccountModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				WebExAccount.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, WebExAccountImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, WebExAccountImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(webExSiteId);

			return (List<WebExAccount>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the web ex accounts before and after the current web ex account in the ordered set of web ex accounts that the user has permission to view where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * @param webExAccountId the primary key of the current web ex account
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next web ex account
	 * @throws NoSuchAccountException if a web ex account with the primary key could not be found
	 */
	@Override
	public WebExAccount[] filterFindByG_W_PrevAndNext(long webExAccountId,
		long groupId, long webExSiteId,
		OrderByComparator<WebExAccount> orderByComparator)
		throws NoSuchAccountException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_W_PrevAndNext(webExAccountId, groupId, webExSiteId,
				orderByComparator);
		}

		WebExAccount webExAccount = findByPrimaryKey(webExAccountId);

		Session session = null;

		try {
			session = openSession();

			WebExAccount[] array = new WebExAccountImpl[3];

			array[0] = filterGetByG_W_PrevAndNext(session, webExAccount,
					groupId, webExSiteId, orderByComparator, true);

			array[1] = webExAccount;

			array[2] = filterGetByG_W_PrevAndNext(session, webExAccount,
					groupId, webExSiteId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WebExAccount filterGetByG_W_PrevAndNext(Session session,
		WebExAccount webExAccount, long groupId, long webExSiteId,
		OrderByComparator<WebExAccount> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_WEBEXACCOUNT_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_WEBEXACCOUNT_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_W_GROUPID_2);

		query.append(_FINDER_COLUMN_G_W_WEBEXSITEID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_WEBEXACCOUNT_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(WebExAccountModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(WebExAccountModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				WebExAccount.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, WebExAccountImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, WebExAccountImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(webExSiteId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(webExAccount);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WebExAccount> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the web ex accounts where groupId = &#63; and webExSiteId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 */
	@Override
	public void removeByG_W(long groupId, long webExSiteId) {
		for (WebExAccount webExAccount : findByG_W(groupId, webExSiteId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(webExAccount);
		}
	}

	/**
	 * Returns the number of web ex accounts where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @return the number of matching web ex accounts
	 */
	@Override
	public int countByG_W(long groupId, long webExSiteId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_W;

		Object[] finderArgs = new Object[] { groupId, webExSiteId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WEBEXACCOUNT_WHERE);

			query.append(_FINDER_COLUMN_G_W_GROUPID_2);

			query.append(_FINDER_COLUMN_G_W_WEBEXSITEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(webExSiteId);

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

	/**
	 * Returns the number of web ex accounts that the user has permission to view where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @return the number of matching web ex accounts that the user has permission to view
	 */
	@Override
	public int filterCountByG_W(long groupId, long webExSiteId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_W(groupId, webExSiteId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_WEBEXACCOUNT_WHERE);

		query.append(_FINDER_COLUMN_G_W_GROUPID_2);

		query.append(_FINDER_COLUMN_G_W_WEBEXSITEID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				WebExAccount.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(webExSiteId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_W_GROUPID_2 = "webExAccount.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_W_WEBEXSITEID_2 = "webExAccount.webExSiteId = ?";

	public WebExAccountPersistenceImpl() {
		setModelClass(WebExAccount.class);
	}

	/**
	 * Caches the web ex account in the entity cache if it is enabled.
	 *
	 * @param webExAccount the web ex account
	 */
	@Override
	public void cacheResult(WebExAccount webExAccount) {
		entityCache.putResult(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountImpl.class, webExAccount.getPrimaryKey(), webExAccount);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { webExAccount.getUuid(), webExAccount.getGroupId() },
			webExAccount);

		webExAccount.resetOriginalValues();
	}

	/**
	 * Caches the web ex accounts in the entity cache if it is enabled.
	 *
	 * @param webExAccounts the web ex accounts
	 */
	@Override
	public void cacheResult(List<WebExAccount> webExAccounts) {
		for (WebExAccount webExAccount : webExAccounts) {
			if (entityCache.getResult(
						WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
						WebExAccountImpl.class, webExAccount.getPrimaryKey()) == null) {
				cacheResult(webExAccount);
			}
			else {
				webExAccount.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all web ex accounts.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WebExAccountImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the web ex account.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WebExAccount webExAccount) {
		entityCache.removeResult(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountImpl.class, webExAccount.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((WebExAccountModelImpl)webExAccount);
	}

	@Override
	public void clearCache(List<WebExAccount> webExAccounts) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WebExAccount webExAccount : webExAccounts) {
			entityCache.removeResult(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
				WebExAccountImpl.class, webExAccount.getPrimaryKey());

			clearUniqueFindersCache((WebExAccountModelImpl)webExAccount);
		}
	}

	protected void cacheUniqueFindersCache(
		WebExAccountModelImpl webExAccountModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					webExAccountModelImpl.getUuid(),
					webExAccountModelImpl.getGroupId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				webExAccountModelImpl);
		}
		else {
			if ((webExAccountModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						webExAccountModelImpl.getUuid(),
						webExAccountModelImpl.getGroupId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					webExAccountModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		WebExAccountModelImpl webExAccountModelImpl) {
		Object[] args = new Object[] {
				webExAccountModelImpl.getUuid(),
				webExAccountModelImpl.getGroupId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((webExAccountModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					webExAccountModelImpl.getOriginalUuid(),
					webExAccountModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new web ex account with the primary key. Does not add the web ex account to the database.
	 *
	 * @param webExAccountId the primary key for the new web ex account
	 * @return the new web ex account
	 */
	@Override
	public WebExAccount create(long webExAccountId) {
		WebExAccount webExAccount = new WebExAccountImpl();

		webExAccount.setNew(true);
		webExAccount.setPrimaryKey(webExAccountId);

		String uuid = PortalUUIDUtil.generate();

		webExAccount.setUuid(uuid);

		webExAccount.setCompanyId(companyProvider.getCompanyId());

		return webExAccount;
	}

	/**
	 * Removes the web ex account with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param webExAccountId the primary key of the web ex account
	 * @return the web ex account that was removed
	 * @throws NoSuchAccountException if a web ex account with the primary key could not be found
	 */
	@Override
	public WebExAccount remove(long webExAccountId)
		throws NoSuchAccountException {
		return remove((Serializable)webExAccountId);
	}

	/**
	 * Removes the web ex account with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the web ex account
	 * @return the web ex account that was removed
	 * @throws NoSuchAccountException if a web ex account with the primary key could not be found
	 */
	@Override
	public WebExAccount remove(Serializable primaryKey)
		throws NoSuchAccountException {
		Session session = null;

		try {
			session = openSession();

			WebExAccount webExAccount = (WebExAccount)session.get(WebExAccountImpl.class,
					primaryKey);

			if (webExAccount == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(webExAccount);
		}
		catch (NoSuchAccountException nsee) {
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
	protected WebExAccount removeImpl(WebExAccount webExAccount) {
		webExAccount = toUnwrappedModel(webExAccount);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(webExAccount)) {
				webExAccount = (WebExAccount)session.get(WebExAccountImpl.class,
						webExAccount.getPrimaryKeyObj());
			}

			if (webExAccount != null) {
				session.delete(webExAccount);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (webExAccount != null) {
			clearCache(webExAccount);
		}

		return webExAccount;
	}

	@Override
	public WebExAccount updateImpl(WebExAccount webExAccount) {
		webExAccount = toUnwrappedModel(webExAccount);

		boolean isNew = webExAccount.isNew();

		WebExAccountModelImpl webExAccountModelImpl = (WebExAccountModelImpl)webExAccount;

		if (Validator.isNull(webExAccount.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			webExAccount.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (webExAccount.getCreateDate() == null)) {
			if (serviceContext == null) {
				webExAccount.setCreateDate(now);
			}
			else {
				webExAccount.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!webExAccountModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				webExAccount.setModifiedDate(now);
			}
			else {
				webExAccount.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (webExAccount.isNew()) {
				session.save(webExAccount);

				webExAccount.setNew(false);
			}
			else {
				webExAccount = (WebExAccount)session.merge(webExAccount);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !WebExAccountModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((webExAccountModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						webExAccountModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { webExAccountModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((webExAccountModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						webExAccountModelImpl.getOriginalUuid(),
						webExAccountModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						webExAccountModelImpl.getUuid(),
						webExAccountModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((webExAccountModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_W.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						webExAccountModelImpl.getOriginalGroupId(),
						webExAccountModelImpl.getOriginalWebExSiteId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_W, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_W,
					args);

				args = new Object[] {
						webExAccountModelImpl.getGroupId(),
						webExAccountModelImpl.getWebExSiteId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_G_W, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_W,
					args);
			}
		}

		entityCache.putResult(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountImpl.class, webExAccount.getPrimaryKey(), webExAccount,
			false);

		clearUniqueFindersCache(webExAccountModelImpl);
		cacheUniqueFindersCache(webExAccountModelImpl, isNew);

		webExAccount.resetOriginalValues();

		return webExAccount;
	}

	protected WebExAccount toUnwrappedModel(WebExAccount webExAccount) {
		if (webExAccount instanceof WebExAccountImpl) {
			return webExAccount;
		}

		WebExAccountImpl webExAccountImpl = new WebExAccountImpl();

		webExAccountImpl.setNew(webExAccount.isNew());
		webExAccountImpl.setPrimaryKey(webExAccount.getPrimaryKey());

		webExAccountImpl.setUuid(webExAccount.getUuid());
		webExAccountImpl.setWebExAccountId(webExAccount.getWebExAccountId());
		webExAccountImpl.setGroupId(webExAccount.getGroupId());
		webExAccountImpl.setCompanyId(webExAccount.getCompanyId());
		webExAccountImpl.setUserId(webExAccount.getUserId());
		webExAccountImpl.setUserName(webExAccount.getUserName());
		webExAccountImpl.setCreateDate(webExAccount.getCreateDate());
		webExAccountImpl.setModifiedDate(webExAccount.getModifiedDate());
		webExAccountImpl.setWebExSiteId(webExAccount.getWebExSiteId());
		webExAccountImpl.setLogin(webExAccount.getLogin());
		webExAccountImpl.setPassword(webExAccount.getPassword());

		return webExAccountImpl;
	}

	/**
	 * Returns the web ex account with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the web ex account
	 * @return the web ex account
	 * @throws NoSuchAccountException if a web ex account with the primary key could not be found
	 */
	@Override
	public WebExAccount findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAccountException {
		WebExAccount webExAccount = fetchByPrimaryKey(primaryKey);

		if (webExAccount == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAccountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return webExAccount;
	}

	/**
	 * Returns the web ex account with the primary key or throws a {@link NoSuchAccountException} if it could not be found.
	 *
	 * @param webExAccountId the primary key of the web ex account
	 * @return the web ex account
	 * @throws NoSuchAccountException if a web ex account with the primary key could not be found
	 */
	@Override
	public WebExAccount findByPrimaryKey(long webExAccountId)
		throws NoSuchAccountException {
		return findByPrimaryKey((Serializable)webExAccountId);
	}

	/**
	 * Returns the web ex account with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the web ex account
	 * @return the web ex account, or <code>null</code> if a web ex account with the primary key could not be found
	 */
	@Override
	public WebExAccount fetchByPrimaryKey(Serializable primaryKey) {
		WebExAccount webExAccount = (WebExAccount)entityCache.getResult(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
				WebExAccountImpl.class, primaryKey);

		if (webExAccount == _nullWebExAccount) {
			return null;
		}

		if (webExAccount == null) {
			Session session = null;

			try {
				session = openSession();

				webExAccount = (WebExAccount)session.get(WebExAccountImpl.class,
						primaryKey);

				if (webExAccount != null) {
					cacheResult(webExAccount);
				}
				else {
					entityCache.putResult(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
						WebExAccountImpl.class, primaryKey, _nullWebExAccount);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
					WebExAccountImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return webExAccount;
	}

	/**
	 * Returns the web ex account with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param webExAccountId the primary key of the web ex account
	 * @return the web ex account, or <code>null</code> if a web ex account with the primary key could not be found
	 */
	@Override
	public WebExAccount fetchByPrimaryKey(long webExAccountId) {
		return fetchByPrimaryKey((Serializable)webExAccountId);
	}

	@Override
	public Map<Serializable, WebExAccount> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WebExAccount> map = new HashMap<Serializable, WebExAccount>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WebExAccount webExAccount = fetchByPrimaryKey(primaryKey);

			if (webExAccount != null) {
				map.put(primaryKey, webExAccount);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			WebExAccount webExAccount = (WebExAccount)entityCache.getResult(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
					WebExAccountImpl.class, primaryKey);

			if (webExAccount == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, webExAccount);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_WEBEXACCOUNT_WHERE_PKS_IN);

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

			for (WebExAccount webExAccount : (List<WebExAccount>)q.list()) {
				map.put(webExAccount.getPrimaryKeyObj(), webExAccount);

				cacheResult(webExAccount);

				uncachedPrimaryKeys.remove(webExAccount.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
					WebExAccountImpl.class, primaryKey, _nullWebExAccount);
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
	 * Returns all the web ex accounts.
	 *
	 * @return the web ex accounts
	 */
	@Override
	public List<WebExAccount> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the web ex accounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of web ex accounts
	 * @param end the upper bound of the range of web ex accounts (not inclusive)
	 * @return the range of web ex accounts
	 */
	@Override
	public List<WebExAccount> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the web ex accounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of web ex accounts
	 * @param end the upper bound of the range of web ex accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of web ex accounts
	 */
	@Override
	public List<WebExAccount> findAll(int start, int end,
		OrderByComparator<WebExAccount> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the web ex accounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of web ex accounts
	 * @param end the upper bound of the range of web ex accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of web ex accounts
	 */
	@Override
	public List<WebExAccount> findAll(int start, int end,
		OrderByComparator<WebExAccount> orderByComparator,
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

		List<WebExAccount> list = null;

		if (retrieveFromCache) {
			list = (List<WebExAccount>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_WEBEXACCOUNT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WEBEXACCOUNT;

				if (pagination) {
					sql = sql.concat(WebExAccountModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WebExAccount>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WebExAccount>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the web ex accounts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WebExAccount webExAccount : findAll()) {
			remove(webExAccount);
		}
	}

	/**
	 * Returns the number of web ex accounts.
	 *
	 * @return the number of web ex accounts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WEBEXACCOUNT);

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
		return WebExAccountModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the web ex account persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(WebExAccountImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_WEBEXACCOUNT = "SELECT webExAccount FROM WebExAccount webExAccount";
	private static final String _SQL_SELECT_WEBEXACCOUNT_WHERE_PKS_IN = "SELECT webExAccount FROM WebExAccount webExAccount WHERE webExAccountId IN (";
	private static final String _SQL_SELECT_WEBEXACCOUNT_WHERE = "SELECT webExAccount FROM WebExAccount webExAccount WHERE ";
	private static final String _SQL_COUNT_WEBEXACCOUNT = "SELECT COUNT(webExAccount) FROM WebExAccount webExAccount";
	private static final String _SQL_COUNT_WEBEXACCOUNT_WHERE = "SELECT COUNT(webExAccount) FROM WebExAccount webExAccount WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "webExAccount.webExAccountId";
	private static final String _FILTER_SQL_SELECT_WEBEXACCOUNT_WHERE = "SELECT DISTINCT {webExAccount.*} FROM WebEx_WebExAccount webExAccount WHERE ";
	private static final String _FILTER_SQL_SELECT_WEBEXACCOUNT_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {WebEx_WebExAccount.*} FROM (SELECT DISTINCT webExAccount.webExAccountId FROM WebEx_WebExAccount webExAccount WHERE ";
	private static final String _FILTER_SQL_SELECT_WEBEXACCOUNT_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN WebEx_WebExAccount ON TEMP_TABLE.webExAccountId = WebEx_WebExAccount.webExAccountId";
	private static final String _FILTER_SQL_COUNT_WEBEXACCOUNT_WHERE = "SELECT COUNT(DISTINCT webExAccount.webExAccountId) AS COUNT_VALUE FROM WebEx_WebExAccount webExAccount WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "webExAccount";
	private static final String _FILTER_ENTITY_TABLE = "WebEx_WebExAccount";
	private static final String _ORDER_BY_ENTITY_ALIAS = "webExAccount.";
	private static final String _ORDER_BY_ENTITY_TABLE = "WebEx_WebExAccount.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WebExAccount exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No WebExAccount exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(WebExAccountPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "password"
			});
	private static final WebExAccount _nullWebExAccount = new WebExAccountImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<WebExAccount> toCacheModel() {
				return _nullWebExAccountCacheModel;
			}
		};

	private static final CacheModel<WebExAccount> _nullWebExAccountCacheModel = new CacheModel<WebExAccount>() {
			@Override
			public WebExAccount toEntityModel() {
				return _nullWebExAccount;
			}
		};
}