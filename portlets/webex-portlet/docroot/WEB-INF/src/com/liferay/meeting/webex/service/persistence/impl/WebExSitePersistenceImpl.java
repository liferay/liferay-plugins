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

import com.liferay.meeting.webex.exception.NoSuchSiteException;
import com.liferay.meeting.webex.model.WebExSite;
import com.liferay.meeting.webex.model.impl.WebExSiteImpl;
import com.liferay.meeting.webex.model.impl.WebExSiteModelImpl;
import com.liferay.meeting.webex.service.persistence.WebExSitePersistence;

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
import com.liferay.portal.kernel.util.StringUtil;
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
 * The persistence implementation for the web ex site service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Anant Singh
 * @see WebExSitePersistence
 * @see com.liferay.meeting.webex.service.persistence.WebExSiteUtil
 * @generated
 */
@ProviderType
public class WebExSitePersistenceImpl extends BasePersistenceImpl<WebExSite>
	implements WebExSitePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WebExSiteUtil} to access the web ex site persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WebExSiteImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, WebExSiteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, WebExSiteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, WebExSiteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, WebExSiteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			WebExSiteModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the web ex sites where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching web ex sites
	 */
	@Override
	public List<WebExSite> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the web ex sites where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of web ex sites
	 * @param end the upper bound of the range of web ex sites (not inclusive)
	 * @return the range of matching web ex sites
	 */
	@Override
	public List<WebExSite> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the web ex sites where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of web ex sites
	 * @param end the upper bound of the range of web ex sites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching web ex sites
	 */
	@Override
	public List<WebExSite> findByUuid(String uuid, int start, int end,
		OrderByComparator<WebExSite> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the web ex sites where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of web ex sites
	 * @param end the upper bound of the range of web ex sites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching web ex sites
	 */
	@Override
	public List<WebExSite> findByUuid(String uuid, int start, int end,
		OrderByComparator<WebExSite> orderByComparator,
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

		List<WebExSite> list = null;

		if (retrieveFromCache) {
			list = (List<WebExSite>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (WebExSite webExSite : list) {
					if (!Validator.equals(uuid, webExSite.getUuid())) {
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

			query.append(_SQL_SELECT_WEBEXSITE_WHERE);

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
				query.append(WebExSiteModelImpl.ORDER_BY_JPQL);
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
					list = (List<WebExSite>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WebExSite>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first web ex site in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching web ex site
	 * @throws NoSuchSiteException if a matching web ex site could not be found
	 */
	@Override
	public WebExSite findByUuid_First(String uuid,
		OrderByComparator<WebExSite> orderByComparator)
		throws NoSuchSiteException {
		WebExSite webExSite = fetchByUuid_First(uuid, orderByComparator);

		if (webExSite != null) {
			return webExSite;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSiteException(msg.toString());
	}

	/**
	 * Returns the first web ex site in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching web ex site, or <code>null</code> if a matching web ex site could not be found
	 */
	@Override
	public WebExSite fetchByUuid_First(String uuid,
		OrderByComparator<WebExSite> orderByComparator) {
		List<WebExSite> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last web ex site in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching web ex site
	 * @throws NoSuchSiteException if a matching web ex site could not be found
	 */
	@Override
	public WebExSite findByUuid_Last(String uuid,
		OrderByComparator<WebExSite> orderByComparator)
		throws NoSuchSiteException {
		WebExSite webExSite = fetchByUuid_Last(uuid, orderByComparator);

		if (webExSite != null) {
			return webExSite;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSiteException(msg.toString());
	}

	/**
	 * Returns the last web ex site in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching web ex site, or <code>null</code> if a matching web ex site could not be found
	 */
	@Override
	public WebExSite fetchByUuid_Last(String uuid,
		OrderByComparator<WebExSite> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<WebExSite> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the web ex sites before and after the current web ex site in the ordered set where uuid = &#63;.
	 *
	 * @param webExSiteId the primary key of the current web ex site
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next web ex site
	 * @throws NoSuchSiteException if a web ex site with the primary key could not be found
	 */
	@Override
	public WebExSite[] findByUuid_PrevAndNext(long webExSiteId, String uuid,
		OrderByComparator<WebExSite> orderByComparator)
		throws NoSuchSiteException {
		WebExSite webExSite = findByPrimaryKey(webExSiteId);

		Session session = null;

		try {
			session = openSession();

			WebExSite[] array = new WebExSiteImpl[3];

			array[0] = getByUuid_PrevAndNext(session, webExSite, uuid,
					orderByComparator, true);

			array[1] = webExSite;

			array[2] = getByUuid_PrevAndNext(session, webExSite, uuid,
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

	protected WebExSite getByUuid_PrevAndNext(Session session,
		WebExSite webExSite, String uuid,
		OrderByComparator<WebExSite> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WEBEXSITE_WHERE);

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
			query.append(WebExSiteModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(webExSite);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WebExSite> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the web ex sites where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (WebExSite webExSite : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(webExSite);
		}
	}

	/**
	 * Returns the number of web ex sites where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching web ex sites
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WEBEXSITE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "webExSite.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "webExSite.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(webExSite.uuid IS NULL OR webExSite.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, WebExSiteImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			WebExSiteModelImpl.UUID_COLUMN_BITMASK |
			WebExSiteModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the web ex site where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSiteException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching web ex site
	 * @throws NoSuchSiteException if a matching web ex site could not be found
	 */
	@Override
	public WebExSite findByUUID_G(String uuid, long groupId)
		throws NoSuchSiteException {
		WebExSite webExSite = fetchByUUID_G(uuid, groupId);

		if (webExSite == null) {
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

			throw new NoSuchSiteException(msg.toString());
		}

		return webExSite;
	}

	/**
	 * Returns the web ex site where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching web ex site, or <code>null</code> if a matching web ex site could not be found
	 */
	@Override
	public WebExSite fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the web ex site where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching web ex site, or <code>null</code> if a matching web ex site could not be found
	 */
	@Override
	public WebExSite fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof WebExSite) {
			WebExSite webExSite = (WebExSite)result;

			if (!Validator.equals(uuid, webExSite.getUuid()) ||
					(groupId != webExSite.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_WEBEXSITE_WHERE);

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

				List<WebExSite> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					WebExSite webExSite = list.get(0);

					result = webExSite;

					cacheResult(webExSite);

					if ((webExSite.getUuid() == null) ||
							!webExSite.getUuid().equals(uuid) ||
							(webExSite.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, webExSite);
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
			return (WebExSite)result;
		}
	}

	/**
	 * Removes the web ex site where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the web ex site that was removed
	 */
	@Override
	public WebExSite removeByUUID_G(String uuid, long groupId)
		throws NoSuchSiteException {
		WebExSite webExSite = findByUUID_G(uuid, groupId);

		return remove(webExSite);
	}

	/**
	 * Returns the number of web ex sites where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching web ex sites
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WEBEXSITE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "webExSite.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "webExSite.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(webExSite.uuid IS NULL OR webExSite.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "webExSite.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, WebExSiteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, WebExSiteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			WebExSiteModelImpl.UUID_COLUMN_BITMASK |
			WebExSiteModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the web ex sites where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching web ex sites
	 */
	@Override
	public List<WebExSite> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the web ex sites where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of web ex sites
	 * @param end the upper bound of the range of web ex sites (not inclusive)
	 * @return the range of matching web ex sites
	 */
	@Override
	public List<WebExSite> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the web ex sites where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of web ex sites
	 * @param end the upper bound of the range of web ex sites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching web ex sites
	 */
	@Override
	public List<WebExSite> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<WebExSite> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the web ex sites where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of web ex sites
	 * @param end the upper bound of the range of web ex sites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching web ex sites
	 */
	@Override
	public List<WebExSite> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<WebExSite> orderByComparator,
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

		List<WebExSite> list = null;

		if (retrieveFromCache) {
			list = (List<WebExSite>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (WebExSite webExSite : list) {
					if (!Validator.equals(uuid, webExSite.getUuid()) ||
							(companyId != webExSite.getCompanyId())) {
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

			query.append(_SQL_SELECT_WEBEXSITE_WHERE);

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
				query.append(WebExSiteModelImpl.ORDER_BY_JPQL);
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
					list = (List<WebExSite>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WebExSite>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first web ex site in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching web ex site
	 * @throws NoSuchSiteException if a matching web ex site could not be found
	 */
	@Override
	public WebExSite findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<WebExSite> orderByComparator)
		throws NoSuchSiteException {
		WebExSite webExSite = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (webExSite != null) {
			return webExSite;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSiteException(msg.toString());
	}

	/**
	 * Returns the first web ex site in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching web ex site, or <code>null</code> if a matching web ex site could not be found
	 */
	@Override
	public WebExSite fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<WebExSite> orderByComparator) {
		List<WebExSite> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last web ex site in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching web ex site
	 * @throws NoSuchSiteException if a matching web ex site could not be found
	 */
	@Override
	public WebExSite findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<WebExSite> orderByComparator)
		throws NoSuchSiteException {
		WebExSite webExSite = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (webExSite != null) {
			return webExSite;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSiteException(msg.toString());
	}

	/**
	 * Returns the last web ex site in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching web ex site, or <code>null</code> if a matching web ex site could not be found
	 */
	@Override
	public WebExSite fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<WebExSite> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<WebExSite> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the web ex sites before and after the current web ex site in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param webExSiteId the primary key of the current web ex site
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next web ex site
	 * @throws NoSuchSiteException if a web ex site with the primary key could not be found
	 */
	@Override
	public WebExSite[] findByUuid_C_PrevAndNext(long webExSiteId, String uuid,
		long companyId, OrderByComparator<WebExSite> orderByComparator)
		throws NoSuchSiteException {
		WebExSite webExSite = findByPrimaryKey(webExSiteId);

		Session session = null;

		try {
			session = openSession();

			WebExSite[] array = new WebExSiteImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, webExSite, uuid,
					companyId, orderByComparator, true);

			array[1] = webExSite;

			array[2] = getByUuid_C_PrevAndNext(session, webExSite, uuid,
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

	protected WebExSite getByUuid_C_PrevAndNext(Session session,
		WebExSite webExSite, String uuid, long companyId,
		OrderByComparator<WebExSite> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_WEBEXSITE_WHERE);

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
			query.append(WebExSiteModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(webExSite);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WebExSite> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the web ex sites where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (WebExSite webExSite : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(webExSite);
		}
	}

	/**
	 * Returns the number of web ex sites where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching web ex sites
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WEBEXSITE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "webExSite.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "webExSite.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(webExSite.uuid IS NULL OR webExSite.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "webExSite.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, WebExSiteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, WebExSiteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			WebExSiteModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the web ex sites where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching web ex sites
	 */
	@Override
	public List<WebExSite> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the web ex sites where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of web ex sites
	 * @param end the upper bound of the range of web ex sites (not inclusive)
	 * @return the range of matching web ex sites
	 */
	@Override
	public List<WebExSite> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the web ex sites where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of web ex sites
	 * @param end the upper bound of the range of web ex sites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching web ex sites
	 */
	@Override
	public List<WebExSite> findByGroupId(long groupId, int start, int end,
		OrderByComparator<WebExSite> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the web ex sites where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of web ex sites
	 * @param end the upper bound of the range of web ex sites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching web ex sites
	 */
	@Override
	public List<WebExSite> findByGroupId(long groupId, int start, int end,
		OrderByComparator<WebExSite> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<WebExSite> list = null;

		if (retrieveFromCache) {
			list = (List<WebExSite>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (WebExSite webExSite : list) {
					if ((groupId != webExSite.getGroupId())) {
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

			query.append(_SQL_SELECT_WEBEXSITE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(WebExSiteModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<WebExSite>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WebExSite>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first web ex site in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching web ex site
	 * @throws NoSuchSiteException if a matching web ex site could not be found
	 */
	@Override
	public WebExSite findByGroupId_First(long groupId,
		OrderByComparator<WebExSite> orderByComparator)
		throws NoSuchSiteException {
		WebExSite webExSite = fetchByGroupId_First(groupId, orderByComparator);

		if (webExSite != null) {
			return webExSite;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSiteException(msg.toString());
	}

	/**
	 * Returns the first web ex site in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching web ex site, or <code>null</code> if a matching web ex site could not be found
	 */
	@Override
	public WebExSite fetchByGroupId_First(long groupId,
		OrderByComparator<WebExSite> orderByComparator) {
		List<WebExSite> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last web ex site in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching web ex site
	 * @throws NoSuchSiteException if a matching web ex site could not be found
	 */
	@Override
	public WebExSite findByGroupId_Last(long groupId,
		OrderByComparator<WebExSite> orderByComparator)
		throws NoSuchSiteException {
		WebExSite webExSite = fetchByGroupId_Last(groupId, orderByComparator);

		if (webExSite != null) {
			return webExSite;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSiteException(msg.toString());
	}

	/**
	 * Returns the last web ex site in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching web ex site, or <code>null</code> if a matching web ex site could not be found
	 */
	@Override
	public WebExSite fetchByGroupId_Last(long groupId,
		OrderByComparator<WebExSite> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<WebExSite> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the web ex sites before and after the current web ex site in the ordered set where groupId = &#63;.
	 *
	 * @param webExSiteId the primary key of the current web ex site
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next web ex site
	 * @throws NoSuchSiteException if a web ex site with the primary key could not be found
	 */
	@Override
	public WebExSite[] findByGroupId_PrevAndNext(long webExSiteId,
		long groupId, OrderByComparator<WebExSite> orderByComparator)
		throws NoSuchSiteException {
		WebExSite webExSite = findByPrimaryKey(webExSiteId);

		Session session = null;

		try {
			session = openSession();

			WebExSite[] array = new WebExSiteImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, webExSite, groupId,
					orderByComparator, true);

			array[1] = webExSite;

			array[2] = getByGroupId_PrevAndNext(session, webExSite, groupId,
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

	protected WebExSite getByGroupId_PrevAndNext(Session session,
		WebExSite webExSite, long groupId,
		OrderByComparator<WebExSite> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WEBEXSITE_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			query.append(WebExSiteModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(webExSite);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WebExSite> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the web ex sites that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching web ex sites that the user has permission to view
	 */
	@Override
	public List<WebExSite> filterFindByGroupId(long groupId) {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the web ex sites that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of web ex sites
	 * @param end the upper bound of the range of web ex sites (not inclusive)
	 * @return the range of matching web ex sites that the user has permission to view
	 */
	@Override
	public List<WebExSite> filterFindByGroupId(long groupId, int start, int end) {
		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the web ex sites that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of web ex sites
	 * @param end the upper bound of the range of web ex sites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching web ex sites that the user has permission to view
	 */
	@Override
	public List<WebExSite> filterFindByGroupId(long groupId, int start,
		int end, OrderByComparator<WebExSite> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId(groupId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 2));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_WEBEXSITE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_WEBEXSITE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_WEBEXSITE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(WebExSiteModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(WebExSiteModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				WebExSite.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, WebExSiteImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, WebExSiteImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<WebExSite>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the web ex sites before and after the current web ex site in the ordered set of web ex sites that the user has permission to view where groupId = &#63;.
	 *
	 * @param webExSiteId the primary key of the current web ex site
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next web ex site
	 * @throws NoSuchSiteException if a web ex site with the primary key could not be found
	 */
	@Override
	public WebExSite[] filterFindByGroupId_PrevAndNext(long webExSiteId,
		long groupId, OrderByComparator<WebExSite> orderByComparator)
		throws NoSuchSiteException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(webExSiteId, groupId,
				orderByComparator);
		}

		WebExSite webExSite = findByPrimaryKey(webExSiteId);

		Session session = null;

		try {
			session = openSession();

			WebExSite[] array = new WebExSiteImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session, webExSite,
					groupId, orderByComparator, true);

			array[1] = webExSite;

			array[2] = filterGetByGroupId_PrevAndNext(session, webExSite,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WebExSite filterGetByGroupId_PrevAndNext(Session session,
		WebExSite webExSite, long groupId,
		OrderByComparator<WebExSite> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_WEBEXSITE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_WEBEXSITE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_WEBEXSITE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(WebExSiteModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(WebExSiteModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				WebExSite.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, WebExSiteImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, WebExSiteImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(webExSite);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WebExSite> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the web ex sites where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (WebExSite webExSite : findByGroupId(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(webExSite);
		}
	}

	/**
	 * Returns the number of web ex sites where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching web ex sites
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WEBEXSITE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

	/**
	 * Returns the number of web ex sites that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching web ex sites that the user has permission to view
	 */
	@Override
	public int filterCountByGroupId(long groupId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_WEBEXSITE_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				WebExSite.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "webExSite.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_SITEKEY = new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, WebExSiteImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchBySiteKey",
			new String[] { Long.class.getName() },
			WebExSiteModelImpl.SITEKEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SITEKEY = new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySiteKey",
			new String[] { Long.class.getName() });

	/**
	 * Returns the web ex site where siteKey = &#63; or throws a {@link NoSuchSiteException} if it could not be found.
	 *
	 * @param siteKey the site key
	 * @return the matching web ex site
	 * @throws NoSuchSiteException if a matching web ex site could not be found
	 */
	@Override
	public WebExSite findBySiteKey(long siteKey) throws NoSuchSiteException {
		WebExSite webExSite = fetchBySiteKey(siteKey);

		if (webExSite == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("siteKey=");
			msg.append(siteKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchSiteException(msg.toString());
		}

		return webExSite;
	}

	/**
	 * Returns the web ex site where siteKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param siteKey the site key
	 * @return the matching web ex site, or <code>null</code> if a matching web ex site could not be found
	 */
	@Override
	public WebExSite fetchBySiteKey(long siteKey) {
		return fetchBySiteKey(siteKey, true);
	}

	/**
	 * Returns the web ex site where siteKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param siteKey the site key
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching web ex site, or <code>null</code> if a matching web ex site could not be found
	 */
	@Override
	public WebExSite fetchBySiteKey(long siteKey, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { siteKey };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_SITEKEY,
					finderArgs, this);
		}

		if (result instanceof WebExSite) {
			WebExSite webExSite = (WebExSite)result;

			if ((siteKey != webExSite.getSiteKey())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_WEBEXSITE_WHERE);

			query.append(_FINDER_COLUMN_SITEKEY_SITEKEY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(siteKey);

				List<WebExSite> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_SITEKEY,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"WebExSitePersistenceImpl.fetchBySiteKey(long, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					WebExSite webExSite = list.get(0);

					result = webExSite;

					cacheResult(webExSite);

					if ((webExSite.getSiteKey() != siteKey)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_SITEKEY,
							finderArgs, webExSite);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_SITEKEY,
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
			return (WebExSite)result;
		}
	}

	/**
	 * Removes the web ex site where siteKey = &#63; from the database.
	 *
	 * @param siteKey the site key
	 * @return the web ex site that was removed
	 */
	@Override
	public WebExSite removeBySiteKey(long siteKey) throws NoSuchSiteException {
		WebExSite webExSite = findBySiteKey(siteKey);

		return remove(webExSite);
	}

	/**
	 * Returns the number of web ex sites where siteKey = &#63;.
	 *
	 * @param siteKey the site key
	 * @return the number of matching web ex sites
	 */
	@Override
	public int countBySiteKey(long siteKey) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SITEKEY;

		Object[] finderArgs = new Object[] { siteKey };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WEBEXSITE_WHERE);

			query.append(_FINDER_COLUMN_SITEKEY_SITEKEY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(siteKey);

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

	private static final String _FINDER_COLUMN_SITEKEY_SITEKEY_2 = "webExSite.siteKey = ?";

	public WebExSitePersistenceImpl() {
		setModelClass(WebExSite.class);
	}

	/**
	 * Caches the web ex site in the entity cache if it is enabled.
	 *
	 * @param webExSite the web ex site
	 */
	@Override
	public void cacheResult(WebExSite webExSite) {
		entityCache.putResult(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteImpl.class, webExSite.getPrimaryKey(), webExSite);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { webExSite.getUuid(), webExSite.getGroupId() },
			webExSite);

		finderCache.putResult(FINDER_PATH_FETCH_BY_SITEKEY,
			new Object[] { webExSite.getSiteKey() }, webExSite);

		webExSite.resetOriginalValues();
	}

	/**
	 * Caches the web ex sites in the entity cache if it is enabled.
	 *
	 * @param webExSites the web ex sites
	 */
	@Override
	public void cacheResult(List<WebExSite> webExSites) {
		for (WebExSite webExSite : webExSites) {
			if (entityCache.getResult(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
						WebExSiteImpl.class, webExSite.getPrimaryKey()) == null) {
				cacheResult(webExSite);
			}
			else {
				webExSite.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all web ex sites.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(WebExSiteImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the web ex site.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WebExSite webExSite) {
		entityCache.removeResult(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteImpl.class, webExSite.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((WebExSiteModelImpl)webExSite);
	}

	@Override
	public void clearCache(List<WebExSite> webExSites) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WebExSite webExSite : webExSites) {
			entityCache.removeResult(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
				WebExSiteImpl.class, webExSite.getPrimaryKey());

			clearUniqueFindersCache((WebExSiteModelImpl)webExSite);
		}
	}

	protected void cacheUniqueFindersCache(
		WebExSiteModelImpl webExSiteModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					webExSiteModelImpl.getUuid(),
					webExSiteModelImpl.getGroupId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				webExSiteModelImpl);

			args = new Object[] { webExSiteModelImpl.getSiteKey() };

			finderCache.putResult(FINDER_PATH_COUNT_BY_SITEKEY, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_SITEKEY, args,
				webExSiteModelImpl);
		}
		else {
			if ((webExSiteModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						webExSiteModelImpl.getUuid(),
						webExSiteModelImpl.getGroupId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					webExSiteModelImpl);
			}

			if ((webExSiteModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_SITEKEY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { webExSiteModelImpl.getSiteKey() };

				finderCache.putResult(FINDER_PATH_COUNT_BY_SITEKEY, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_SITEKEY, args,
					webExSiteModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		WebExSiteModelImpl webExSiteModelImpl) {
		Object[] args = new Object[] {
				webExSiteModelImpl.getUuid(), webExSiteModelImpl.getGroupId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((webExSiteModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					webExSiteModelImpl.getOriginalUuid(),
					webExSiteModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		args = new Object[] { webExSiteModelImpl.getSiteKey() };

		finderCache.removeResult(FINDER_PATH_COUNT_BY_SITEKEY, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_SITEKEY, args);

		if ((webExSiteModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SITEKEY.getColumnBitmask()) != 0) {
			args = new Object[] { webExSiteModelImpl.getOriginalSiteKey() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_SITEKEY, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_SITEKEY, args);
		}
	}

	/**
	 * Creates a new web ex site with the primary key. Does not add the web ex site to the database.
	 *
	 * @param webExSiteId the primary key for the new web ex site
	 * @return the new web ex site
	 */
	@Override
	public WebExSite create(long webExSiteId) {
		WebExSite webExSite = new WebExSiteImpl();

		webExSite.setNew(true);
		webExSite.setPrimaryKey(webExSiteId);

		String uuid = PortalUUIDUtil.generate();

		webExSite.setUuid(uuid);

		webExSite.setCompanyId(companyProvider.getCompanyId());

		return webExSite;
	}

	/**
	 * Removes the web ex site with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param webExSiteId the primary key of the web ex site
	 * @return the web ex site that was removed
	 * @throws NoSuchSiteException if a web ex site with the primary key could not be found
	 */
	@Override
	public WebExSite remove(long webExSiteId) throws NoSuchSiteException {
		return remove((Serializable)webExSiteId);
	}

	/**
	 * Removes the web ex site with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the web ex site
	 * @return the web ex site that was removed
	 * @throws NoSuchSiteException if a web ex site with the primary key could not be found
	 */
	@Override
	public WebExSite remove(Serializable primaryKey) throws NoSuchSiteException {
		Session session = null;

		try {
			session = openSession();

			WebExSite webExSite = (WebExSite)session.get(WebExSiteImpl.class,
					primaryKey);

			if (webExSite == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSiteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(webExSite);
		}
		catch (NoSuchSiteException nsee) {
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
	protected WebExSite removeImpl(WebExSite webExSite) {
		webExSite = toUnwrappedModel(webExSite);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(webExSite)) {
				webExSite = (WebExSite)session.get(WebExSiteImpl.class,
						webExSite.getPrimaryKeyObj());
			}

			if (webExSite != null) {
				session.delete(webExSite);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (webExSite != null) {
			clearCache(webExSite);
		}

		return webExSite;
	}

	@Override
	public WebExSite updateImpl(WebExSite webExSite) {
		webExSite = toUnwrappedModel(webExSite);

		boolean isNew = webExSite.isNew();

		WebExSiteModelImpl webExSiteModelImpl = (WebExSiteModelImpl)webExSite;

		if (Validator.isNull(webExSite.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			webExSite.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (webExSite.getCreateDate() == null)) {
			if (serviceContext == null) {
				webExSite.setCreateDate(now);
			}
			else {
				webExSite.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!webExSiteModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				webExSite.setModifiedDate(now);
			}
			else {
				webExSite.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (webExSite.isNew()) {
				session.save(webExSite);

				webExSite.setNew(false);
			}
			else {
				webExSite = (WebExSite)session.merge(webExSite);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !WebExSiteModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((webExSiteModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						webExSiteModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { webExSiteModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((webExSiteModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						webExSiteModelImpl.getOriginalUuid(),
						webExSiteModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						webExSiteModelImpl.getUuid(),
						webExSiteModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((webExSiteModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						webExSiteModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { webExSiteModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}
		}

		entityCache.putResult(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteImpl.class, webExSite.getPrimaryKey(), webExSite, false);

		clearUniqueFindersCache(webExSiteModelImpl);
		cacheUniqueFindersCache(webExSiteModelImpl, isNew);

		webExSite.resetOriginalValues();

		return webExSite;
	}

	protected WebExSite toUnwrappedModel(WebExSite webExSite) {
		if (webExSite instanceof WebExSiteImpl) {
			return webExSite;
		}

		WebExSiteImpl webExSiteImpl = new WebExSiteImpl();

		webExSiteImpl.setNew(webExSite.isNew());
		webExSiteImpl.setPrimaryKey(webExSite.getPrimaryKey());

		webExSiteImpl.setUuid(webExSite.getUuid());
		webExSiteImpl.setWebExSiteId(webExSite.getWebExSiteId());
		webExSiteImpl.setGroupId(webExSite.getGroupId());
		webExSiteImpl.setCompanyId(webExSite.getCompanyId());
		webExSiteImpl.setUserId(webExSite.getUserId());
		webExSiteImpl.setUserName(webExSite.getUserName());
		webExSiteImpl.setCreateDate(webExSite.getCreateDate());
		webExSiteImpl.setModifiedDate(webExSite.getModifiedDate());
		webExSiteImpl.setName(webExSite.getName());
		webExSiteImpl.setApiURL(webExSite.getApiURL());
		webExSiteImpl.setLogin(webExSite.getLogin());
		webExSiteImpl.setPassword(webExSite.getPassword());
		webExSiteImpl.setPartnerKey(webExSite.getPartnerKey());
		webExSiteImpl.setSiteKey(webExSite.getSiteKey());

		return webExSiteImpl;
	}

	/**
	 * Returns the web ex site with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the web ex site
	 * @return the web ex site
	 * @throws NoSuchSiteException if a web ex site with the primary key could not be found
	 */
	@Override
	public WebExSite findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSiteException {
		WebExSite webExSite = fetchByPrimaryKey(primaryKey);

		if (webExSite == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSiteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return webExSite;
	}

	/**
	 * Returns the web ex site with the primary key or throws a {@link NoSuchSiteException} if it could not be found.
	 *
	 * @param webExSiteId the primary key of the web ex site
	 * @return the web ex site
	 * @throws NoSuchSiteException if a web ex site with the primary key could not be found
	 */
	@Override
	public WebExSite findByPrimaryKey(long webExSiteId)
		throws NoSuchSiteException {
		return findByPrimaryKey((Serializable)webExSiteId);
	}

	/**
	 * Returns the web ex site with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the web ex site
	 * @return the web ex site, or <code>null</code> if a web ex site with the primary key could not be found
	 */
	@Override
	public WebExSite fetchByPrimaryKey(Serializable primaryKey) {
		WebExSite webExSite = (WebExSite)entityCache.getResult(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
				WebExSiteImpl.class, primaryKey);

		if (webExSite == _nullWebExSite) {
			return null;
		}

		if (webExSite == null) {
			Session session = null;

			try {
				session = openSession();

				webExSite = (WebExSite)session.get(WebExSiteImpl.class,
						primaryKey);

				if (webExSite != null) {
					cacheResult(webExSite);
				}
				else {
					entityCache.putResult(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
						WebExSiteImpl.class, primaryKey, _nullWebExSite);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
					WebExSiteImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return webExSite;
	}

	/**
	 * Returns the web ex site with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param webExSiteId the primary key of the web ex site
	 * @return the web ex site, or <code>null</code> if a web ex site with the primary key could not be found
	 */
	@Override
	public WebExSite fetchByPrimaryKey(long webExSiteId) {
		return fetchByPrimaryKey((Serializable)webExSiteId);
	}

	@Override
	public Map<Serializable, WebExSite> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, WebExSite> map = new HashMap<Serializable, WebExSite>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			WebExSite webExSite = fetchByPrimaryKey(primaryKey);

			if (webExSite != null) {
				map.put(primaryKey, webExSite);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			WebExSite webExSite = (WebExSite)entityCache.getResult(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
					WebExSiteImpl.class, primaryKey);

			if (webExSite == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, webExSite);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_WEBEXSITE_WHERE_PKS_IN);

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

			for (WebExSite webExSite : (List<WebExSite>)q.list()) {
				map.put(webExSite.getPrimaryKeyObj(), webExSite);

				cacheResult(webExSite);

				uncachedPrimaryKeys.remove(webExSite.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
					WebExSiteImpl.class, primaryKey, _nullWebExSite);
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
	 * Returns all the web ex sites.
	 *
	 * @return the web ex sites
	 */
	@Override
	public List<WebExSite> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the web ex sites.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of web ex sites
	 * @param end the upper bound of the range of web ex sites (not inclusive)
	 * @return the range of web ex sites
	 */
	@Override
	public List<WebExSite> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the web ex sites.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of web ex sites
	 * @param end the upper bound of the range of web ex sites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of web ex sites
	 */
	@Override
	public List<WebExSite> findAll(int start, int end,
		OrderByComparator<WebExSite> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the web ex sites.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of web ex sites
	 * @param end the upper bound of the range of web ex sites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of web ex sites
	 */
	@Override
	public List<WebExSite> findAll(int start, int end,
		OrderByComparator<WebExSite> orderByComparator,
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

		List<WebExSite> list = null;

		if (retrieveFromCache) {
			list = (List<WebExSite>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_WEBEXSITE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WEBEXSITE;

				if (pagination) {
					sql = sql.concat(WebExSiteModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<WebExSite>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<WebExSite>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the web ex sites from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (WebExSite webExSite : findAll()) {
			remove(webExSite);
		}
	}

	/**
	 * Returns the number of web ex sites.
	 *
	 * @return the number of web ex sites
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WEBEXSITE);

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
		return WebExSiteModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the web ex site persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(WebExSiteImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_WEBEXSITE = "SELECT webExSite FROM WebExSite webExSite";
	private static final String _SQL_SELECT_WEBEXSITE_WHERE_PKS_IN = "SELECT webExSite FROM WebExSite webExSite WHERE webExSiteId IN (";
	private static final String _SQL_SELECT_WEBEXSITE_WHERE = "SELECT webExSite FROM WebExSite webExSite WHERE ";
	private static final String _SQL_COUNT_WEBEXSITE = "SELECT COUNT(webExSite) FROM WebExSite webExSite";
	private static final String _SQL_COUNT_WEBEXSITE_WHERE = "SELECT COUNT(webExSite) FROM WebExSite webExSite WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "webExSite.webExSiteId";
	private static final String _FILTER_SQL_SELECT_WEBEXSITE_WHERE = "SELECT DISTINCT {webExSite.*} FROM WebEx_WebExSite webExSite WHERE ";
	private static final String _FILTER_SQL_SELECT_WEBEXSITE_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {WebEx_WebExSite.*} FROM (SELECT DISTINCT webExSite.webExSiteId FROM WebEx_WebExSite webExSite WHERE ";
	private static final String _FILTER_SQL_SELECT_WEBEXSITE_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN WebEx_WebExSite ON TEMP_TABLE.webExSiteId = WebEx_WebExSite.webExSiteId";
	private static final String _FILTER_SQL_COUNT_WEBEXSITE_WHERE = "SELECT COUNT(DISTINCT webExSite.webExSiteId) AS COUNT_VALUE FROM WebEx_WebExSite webExSite WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "webExSite";
	private static final String _FILTER_ENTITY_TABLE = "WebEx_WebExSite";
	private static final String _ORDER_BY_ENTITY_ALIAS = "webExSite.";
	private static final String _ORDER_BY_ENTITY_TABLE = "WebEx_WebExSite.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WebExSite exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No WebExSite exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(WebExSitePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "password"
			});
	private static final WebExSite _nullWebExSite = new WebExSiteImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<WebExSite> toCacheModel() {
				return _nullWebExSiteCacheModel;
			}
		};

	private static final CacheModel<WebExSite> _nullWebExSiteCacheModel = new CacheModel<WebExSite>() {
			@Override
			public WebExSite toEntityModel() {
				return _nullWebExSite;
			}
		};
}