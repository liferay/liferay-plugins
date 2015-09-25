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

package com.liferay.knowledgebase.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.knowledgebase.NoSuchArticleException;
import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.impl.KBArticleImpl;
import com.liferay.knowledgebase.model.impl.KBArticleModelImpl;
import com.liferay.knowledgebase.service.persistence.KBArticlePersistence;

import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

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
 * The persistence implementation for the k b article service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBArticlePersistence
 * @see com.liferay.knowledgebase.service.persistence.KBArticleUtil
 * @generated
 */
@ProviderType
public class KBArticlePersistenceImpl extends BasePersistenceImpl<KBArticle>
	implements KBArticlePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KBArticleUtil} to access the k b article persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KBArticleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			KBArticleModelImpl.UUID_COLUMN_BITMASK |
			KBArticleModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the k b articles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByUuid(String uuid, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
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

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if (!Validator.equals(uuid, kbArticle.getUuid())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
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
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByUuid_First(String uuid,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByUuid_First(uuid, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByUuid_First(String uuid,
		OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByUuid_Last(String uuid,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByUuid_Last(uuid, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByUuid_Last(String uuid,
		OrderByComparator<KBArticle> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where uuid = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByUuid_PrevAndNext(long kbArticleId, String uuid,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByUuid_PrevAndNext(session, kbArticle, uuid,
					orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByUuid_PrevAndNext(session, kbArticle, uuid,
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

	protected KBArticle getByUuid_PrevAndNext(Session session,
		KBArticle kbArticle, String uuid,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the k b articles where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (KBArticle kbArticle : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "kbArticle.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "kbArticle.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(kbArticle.uuid IS NULL OR kbArticle.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			KBArticleModelImpl.UUID_COLUMN_BITMASK |
			KBArticleModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the k b article where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchArticleException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByUUID_G(String uuid, long groupId)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByUUID_G(uuid, groupId);

		if (kbArticle == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchArticleException(msg.toString());
		}

		return kbArticle;
	}

	/**
	 * Returns the k b article where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the k b article where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof KBArticle) {
			KBArticle kbArticle = (KBArticle)result;

			if (!Validator.equals(uuid, kbArticle.getUuid()) ||
					(groupId != kbArticle.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

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

				List<KBArticle> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					KBArticle kbArticle = list.get(0);

					result = kbArticle;

					cacheResult(kbArticle);

					if ((kbArticle.getUuid() == null) ||
							!kbArticle.getUuid().equals(uuid) ||
							(kbArticle.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, kbArticle);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
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
			return (KBArticle)result;
		}
	}

	/**
	 * Removes the k b article where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the k b article that was removed
	 */
	@Override
	public KBArticle removeByUUID_G(String uuid, long groupId)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByUUID_G(uuid, groupId);

		return remove(kbArticle);
	}

	/**
	 * Returns the number of k b articles where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "kbArticle.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "kbArticle.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(kbArticle.uuid IS NULL OR kbArticle.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "kbArticle.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			KBArticleModelImpl.UUID_COLUMN_BITMASK |
			KBArticleModelImpl.COMPANYID_COLUMN_BITMASK |
			KBArticleModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the k b articles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<KBArticle> orderByComparator) {
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

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if (!Validator.equals(uuid, kbArticle.getUuid()) ||
						(companyId != kbArticle.getCompanyId())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
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
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<KBArticle> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByUuid_C_PrevAndNext(long kbArticleId, String uuid,
		long companyId, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, kbArticle, uuid,
					companyId, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByUuid_C_PrevAndNext(session, kbArticle, uuid,
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

	protected KBArticle getByUuid_C_PrevAndNext(Session session,
		KBArticle kbArticle, String uuid, long companyId,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the k b articles where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (KBArticle kbArticle : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "kbArticle.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "kbArticle.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(kbArticle.uuid IS NULL OR kbArticle.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "kbArticle.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RESOURCEPRIMKEY =
		new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByResourcePrimKey",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEPRIMKEY =
		new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByResourcePrimKey",
			new String[] { Long.class.getName() },
			KBArticleModelImpl.RESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_RESOURCEPRIMKEY = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByResourcePrimKey", new String[] { Long.class.getName() });

	/**
	 * Returns all the k b articles where resourcePrimKey = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByResourcePrimKey(long resourcePrimKey) {
		return findByResourcePrimKey(resourcePrimKey, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByResourcePrimKey(long resourcePrimKey,
		int start, int end) {
		return findByResourcePrimKey(resourcePrimKey, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByResourcePrimKey(long resourcePrimKey,
		int start, int end, OrderByComparator<KBArticle> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEPRIMKEY;
			finderArgs = new Object[] { resourcePrimKey };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RESOURCEPRIMKEY;
			finderArgs = new Object[] {
					resourcePrimKey,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((resourcePrimKey != kbArticle.getResourcePrimKey())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_RESOURCEPRIMKEY_RESOURCEPRIMKEY_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByResourcePrimKey_First(long resourcePrimKey,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByResourcePrimKey_First(resourcePrimKey,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByResourcePrimKey_First(long resourcePrimKey,
		OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByResourcePrimKey(resourcePrimKey, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByResourcePrimKey_Last(long resourcePrimKey,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByResourcePrimKey_Last(resourcePrimKey,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByResourcePrimKey_Last(long resourcePrimKey,
		OrderByComparator<KBArticle> orderByComparator) {
		int count = countByResourcePrimKey(resourcePrimKey);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByResourcePrimKey(resourcePrimKey,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param resourcePrimKey the resource prim key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByResourcePrimKey_PrevAndNext(long kbArticleId,
		long resourcePrimKey, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByResourcePrimKey_PrevAndNext(session, kbArticle,
					resourcePrimKey, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByResourcePrimKey_PrevAndNext(session, kbArticle,
					resourcePrimKey, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByResourcePrimKey_PrevAndNext(Session session,
		KBArticle kbArticle, long resourcePrimKey,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_RESOURCEPRIMKEY_RESOURCEPRIMKEY_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the k b articles where resourcePrimKey = &#63; from the database.
	 *
	 * @param resourcePrimKey the resource prim key
	 */
	@Override
	public void removeByResourcePrimKey(long resourcePrimKey) {
		for (KBArticle kbArticle : findByResourcePrimKey(resourcePrimKey,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where resourcePrimKey = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByResourcePrimKey(long resourcePrimKey) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_RESOURCEPRIMKEY;

		Object[] finderArgs = new Object[] { resourcePrimKey };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_RESOURCEPRIMKEY_RESOURCEPRIMKEY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

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

	private static final String _FINDER_COLUMN_RESOURCEPRIMKEY_RESOURCEPRIMKEY_2 =
		"kbArticle.resourcePrimKey = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByR_G",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByR_G",
			new String[] { Long.class.getName(), Long.class.getName() },
			KBArticleModelImpl.RESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.GROUPID_COLUMN_BITMASK |
			KBArticleModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_R_G = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByR_G",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the k b articles where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_G(long resourcePrimKey, long groupId) {
		return findByR_G(resourcePrimKey, groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_G(long resourcePrimKey, long groupId,
		int start, int end) {
		return findByR_G(resourcePrimKey, groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_G(long resourcePrimKey, long groupId,
		int start, int end, OrderByComparator<KBArticle> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G;
			finderArgs = new Object[] { resourcePrimKey, groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G;
			finderArgs = new Object[] {
					resourcePrimKey, groupId,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((resourcePrimKey != kbArticle.getResourcePrimKey()) ||
						(groupId != kbArticle.getGroupId())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_G_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_G_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByR_G_First(long resourcePrimKey, long groupId,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByR_G_First(resourcePrimKey, groupId,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByR_G_First(long resourcePrimKey, long groupId,
		OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByR_G(resourcePrimKey, groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByR_G_Last(long resourcePrimKey, long groupId,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByR_G_Last(resourcePrimKey, groupId,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByR_G_Last(long resourcePrimKey, long groupId,
		OrderByComparator<KBArticle> orderByComparator) {
		int count = countByR_G(resourcePrimKey, groupId);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByR_G(resourcePrimKey, groupId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByR_G_PrevAndNext(long kbArticleId,
		long resourcePrimKey, long groupId,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByR_G_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByR_G_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByR_G_PrevAndNext(Session session,
		KBArticle kbArticle, long resourcePrimKey, long groupId,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_G_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_GROUPID_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @return the matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByR_G(long resourcePrimKey, long groupId) {
		return filterFindByR_G(resourcePrimKey, groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByR_G(long resourcePrimKey, long groupId,
		int start, int end) {
		return filterFindByR_G(resourcePrimKey, groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByR_G(long resourcePrimKey, long groupId,
		int start, int end, OrderByComparator<KBArticle> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByR_G(resourcePrimKey, groupId, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_R_G_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);

			qPos.add(groupId);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] filterFindByR_G_PrevAndNext(long kbArticleId,
		long resourcePrimKey, long groupId,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByR_G_PrevAndNext(kbArticleId, resourcePrimKey, groupId,
				orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByR_G_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = filterGetByR_G_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle filterGetByR_G_PrevAndNext(Session session,
		KBArticle kbArticle, long resourcePrimKey, long groupId,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_R_G_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the k b articles where resourcePrimKey = &#63; and groupId = &#63; from the database.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 */
	@Override
	public void removeByR_G(long resourcePrimKey, long groupId) {
		for (KBArticle kbArticle : findByR_G(resourcePrimKey, groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByR_G(long resourcePrimKey, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_R_G;

		Object[] finderArgs = new Object[] { resourcePrimKey, groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_G_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(groupId);

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

	/**
	 * Returns the number of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @return the number of matching k b articles that the user has permission to view
	 */
	@Override
	public int filterCountByR_G(long resourcePrimKey, long groupId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByR_G(resourcePrimKey, groupId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_G_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);

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

	private static final String _FINDER_COLUMN_R_G_RESOURCEPRIMKEY_2 = "kbArticle.resourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_R_G_GROUPID_2 = "kbArticle.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_R_V = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByR_V",
			new String[] { Long.class.getName(), Integer.class.getName() },
			KBArticleModelImpl.RESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.VERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_R_V = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByR_V",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns the k b article where resourcePrimKey = &#63; and version = &#63; or throws a {@link NoSuchArticleException} if it could not be found.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param version the version
	 * @return the matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByR_V(long resourcePrimKey, int version)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByR_V(resourcePrimKey, version);

		if (kbArticle == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("resourcePrimKey=");
			msg.append(resourcePrimKey);

			msg.append(", version=");
			msg.append(version);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchArticleException(msg.toString());
		}

		return kbArticle;
	}

	/**
	 * Returns the k b article where resourcePrimKey = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param version the version
	 * @return the matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByR_V(long resourcePrimKey, int version) {
		return fetchByR_V(resourcePrimKey, version, true);
	}

	/**
	 * Returns the k b article where resourcePrimKey = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param version the version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByR_V(long resourcePrimKey, int version,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { resourcePrimKey, version };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_R_V,
					finderArgs, this);
		}

		if (result instanceof KBArticle) {
			KBArticle kbArticle = (KBArticle)result;

			if ((resourcePrimKey != kbArticle.getResourcePrimKey()) ||
					(version != kbArticle.getVersion())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_V_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_V_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(version);

				List<KBArticle> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_V,
						finderArgs, list);
				}
				else {
					KBArticle kbArticle = list.get(0);

					result = kbArticle;

					cacheResult(kbArticle);

					if ((kbArticle.getResourcePrimKey() != resourcePrimKey) ||
							(kbArticle.getVersion() != version)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_V,
							finderArgs, kbArticle);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_R_V,
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
			return (KBArticle)result;
		}
	}

	/**
	 * Removes the k b article where resourcePrimKey = &#63; and version = &#63; from the database.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param version the version
	 * @return the k b article that was removed
	 */
	@Override
	public KBArticle removeByR_V(long resourcePrimKey, int version)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByR_V(resourcePrimKey, version);

		return remove(kbArticle);
	}

	/**
	 * Returns the number of k b articles where resourcePrimKey = &#63; and version = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param version the version
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByR_V(long resourcePrimKey, int version) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_R_V;

		Object[] finderArgs = new Object[] { resourcePrimKey, version };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_V_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_V_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

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

	private static final String _FINDER_COLUMN_R_V_RESOURCEPRIMKEY_2 = "kbArticle.resourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_R_V_VERSION_2 = "kbArticle.version = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_R_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByR_L",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByR_L",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			KBArticleModelImpl.RESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.LATEST_COLUMN_BITMASK |
			KBArticleModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_R_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByR_L",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByR_L",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the k b articles where resourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param latest the latest
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_L(long resourcePrimKey, boolean latest) {
		return findByR_L(resourcePrimKey, latest, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_L(long resourcePrimKey, boolean latest,
		int start, int end) {
		return findByR_L(resourcePrimKey, latest, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_L(long resourcePrimKey, boolean latest,
		int start, int end, OrderByComparator<KBArticle> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_L;
			finderArgs = new Object[] { resourcePrimKey, latest };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_L;
			finderArgs = new Object[] {
					resourcePrimKey, latest,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((resourcePrimKey != kbArticle.getResourcePrimKey()) ||
						(latest != kbArticle.getLatest())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_L_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_L_LATEST_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(latest);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByR_L_First(long resourcePrimKey, boolean latest,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByR_L_First(resourcePrimKey, latest,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", latest=");
		msg.append(latest);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByR_L_First(long resourcePrimKey, boolean latest,
		OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByR_L(resourcePrimKey, latest, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByR_L_Last(long resourcePrimKey, boolean latest,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByR_L_Last(resourcePrimKey, latest,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", latest=");
		msg.append(latest);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByR_L_Last(long resourcePrimKey, boolean latest,
		OrderByComparator<KBArticle> orderByComparator) {
		int count = countByR_L(resourcePrimKey, latest);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByR_L(resourcePrimKey, latest, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param resourcePrimKey the resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByR_L_PrevAndNext(long kbArticleId,
		long resourcePrimKey, boolean latest,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByR_L_PrevAndNext(session, kbArticle,
					resourcePrimKey, latest, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByR_L_PrevAndNext(session, kbArticle,
					resourcePrimKey, latest, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByR_L_PrevAndNext(Session session,
		KBArticle kbArticle, long resourcePrimKey, boolean latest,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_L_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_L_LATEST_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(latest);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where resourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param latest the latest
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_L(long[] resourcePrimKeies, boolean latest) {
		return findByR_L(resourcePrimKeies, latest, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_L(long[] resourcePrimKeies, boolean latest,
		int start, int end) {
		return findByR_L(resourcePrimKeies, latest, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_L(long[] resourcePrimKeies, boolean latest,
		int start, int end, OrderByComparator<KBArticle> orderByComparator) {
		if (resourcePrimKeies == null) {
			resourcePrimKeies = new long[0];
		}
		else {
			resourcePrimKeies = ArrayUtil.unique(resourcePrimKeies);
		}

		if (resourcePrimKeies.length == 1) {
			return findByR_L(resourcePrimKeies[0], latest, start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					StringUtil.merge(resourcePrimKeies), latest
				};
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(resourcePrimKeies), latest,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_R_L,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if (!ArrayUtil.contains(resourcePrimKeies,
							kbArticle.getResourcePrimKey()) ||
						(latest != kbArticle.getLatest())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			if (resourcePrimKeies.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_R_L_RESOURCEPRIMKEY_7);

				query.append(StringUtil.merge(resourcePrimKeies));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_L_LATEST_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(latest);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_R_L,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_R_L,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the k b articles where resourcePrimKey = &#63; and latest = &#63; from the database.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param latest the latest
	 */
	@Override
	public void removeByR_L(long resourcePrimKey, boolean latest) {
		for (KBArticle kbArticle : findByR_L(resourcePrimKey, latest,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where resourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param latest the latest
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByR_L(long resourcePrimKey, boolean latest) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_R_L;

		Object[] finderArgs = new Object[] { resourcePrimKey, latest };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_L_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_L_LATEST_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(latest);

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

	/**
	 * Returns the number of k b articles where resourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param latest the latest
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByR_L(long[] resourcePrimKeies, boolean latest) {
		if (resourcePrimKeies == null) {
			resourcePrimKeies = new long[0];
		}
		else {
			resourcePrimKeies = ArrayUtil.unique(resourcePrimKeies);
		}

		Object[] finderArgs = new Object[] {
				StringUtil.merge(resourcePrimKeies), latest
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			if (resourcePrimKeies.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_R_L_RESOURCEPRIMKEY_7);

				query.append(StringUtil.merge(resourcePrimKeies));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_L_LATEST_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(latest);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_L,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_L,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_R_L_RESOURCEPRIMKEY_2 = "kbArticle.resourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_R_L_RESOURCEPRIMKEY_7 = "kbArticle.resourcePrimKey IN (";
	private static final String _FINDER_COLUMN_R_L_LATEST_2 = "kbArticle.latest = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_R_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByR_M",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByR_M",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			KBArticleModelImpl.RESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.MAIN_COLUMN_BITMASK |
			KBArticleModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_R_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByR_M",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByR_M",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the k b articles where resourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param main the main
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_M(long resourcePrimKey, boolean main) {
		return findByR_M(resourcePrimKey, main, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_M(long resourcePrimKey, boolean main,
		int start, int end) {
		return findByR_M(resourcePrimKey, main, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_M(long resourcePrimKey, boolean main,
		int start, int end, OrderByComparator<KBArticle> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_M;
			finderArgs = new Object[] { resourcePrimKey, main };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_M;
			finderArgs = new Object[] {
					resourcePrimKey, main,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((resourcePrimKey != kbArticle.getResourcePrimKey()) ||
						(main != kbArticle.getMain())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_M_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_M_MAIN_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(main);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByR_M_First(long resourcePrimKey, boolean main,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByR_M_First(resourcePrimKey, main,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByR_M_First(long resourcePrimKey, boolean main,
		OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByR_M(resourcePrimKey, main, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByR_M_Last(long resourcePrimKey, boolean main,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByR_M_Last(resourcePrimKey, main,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByR_M_Last(long resourcePrimKey, boolean main,
		OrderByComparator<KBArticle> orderByComparator) {
		int count = countByR_M(resourcePrimKey, main);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByR_M(resourcePrimKey, main, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param resourcePrimKey the resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByR_M_PrevAndNext(long kbArticleId,
		long resourcePrimKey, boolean main,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByR_M_PrevAndNext(session, kbArticle,
					resourcePrimKey, main, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByR_M_PrevAndNext(session, kbArticle,
					resourcePrimKey, main, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByR_M_PrevAndNext(Session session,
		KBArticle kbArticle, long resourcePrimKey, boolean main,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_M_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_M_MAIN_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(main);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where resourcePrimKey = any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param main the main
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_M(long[] resourcePrimKeies, boolean main) {
		return findByR_M(resourcePrimKeies, main, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_M(long[] resourcePrimKeies, boolean main,
		int start, int end) {
		return findByR_M(resourcePrimKeies, main, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_M(long[] resourcePrimKeies, boolean main,
		int start, int end, OrderByComparator<KBArticle> orderByComparator) {
		if (resourcePrimKeies == null) {
			resourcePrimKeies = new long[0];
		}
		else {
			resourcePrimKeies = ArrayUtil.unique(resourcePrimKeies);
		}

		if (resourcePrimKeies.length == 1) {
			return findByR_M(resourcePrimKeies[0], main, start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { StringUtil.merge(resourcePrimKeies), main };
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(resourcePrimKeies), main,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_R_M,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if (!ArrayUtil.contains(resourcePrimKeies,
							kbArticle.getResourcePrimKey()) ||
						(main != kbArticle.getMain())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			if (resourcePrimKeies.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_R_M_RESOURCEPRIMKEY_7);

				query.append(StringUtil.merge(resourcePrimKeies));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_M_MAIN_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(main);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_R_M,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_R_M,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the k b articles where resourcePrimKey = &#63; and main = &#63; from the database.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param main the main
	 */
	@Override
	public void removeByR_M(long resourcePrimKey, boolean main) {
		for (KBArticle kbArticle : findByR_M(resourcePrimKey, main,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where resourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param main the main
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByR_M(long resourcePrimKey, boolean main) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_R_M;

		Object[] finderArgs = new Object[] { resourcePrimKey, main };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_M_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_M_MAIN_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(main);

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

	/**
	 * Returns the number of k b articles where resourcePrimKey = any &#63; and main = &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param main the main
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByR_M(long[] resourcePrimKeies, boolean main) {
		if (resourcePrimKeies == null) {
			resourcePrimKeies = new long[0];
		}
		else {
			resourcePrimKeies = ArrayUtil.unique(resourcePrimKeies);
		}

		Object[] finderArgs = new Object[] {
				StringUtil.merge(resourcePrimKeies), main
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_M,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			if (resourcePrimKeies.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_R_M_RESOURCEPRIMKEY_7);

				query.append(StringUtil.merge(resourcePrimKeies));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_M_MAIN_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(main);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_M,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_M,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_R_M_RESOURCEPRIMKEY_2 = "kbArticle.resourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_R_M_RESOURCEPRIMKEY_7 = "kbArticle.resourcePrimKey IN (";
	private static final String _FINDER_COLUMN_R_M_MAIN_2 = "kbArticle.main = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_R_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByR_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByR_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			KBArticleModelImpl.RESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.STATUS_COLUMN_BITMASK |
			KBArticleModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_R_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByR_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByR_S",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the k b articles where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param status the status
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_S(long resourcePrimKey, int status) {
		return findByR_S(resourcePrimKey, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_S(long resourcePrimKey, int status,
		int start, int end) {
		return findByR_S(resourcePrimKey, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_S(long resourcePrimKey, int status,
		int start, int end, OrderByComparator<KBArticle> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S;
			finderArgs = new Object[] { resourcePrimKey, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_S;
			finderArgs = new Object[] {
					resourcePrimKey, status,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((resourcePrimKey != kbArticle.getResourcePrimKey()) ||
						(status != kbArticle.getStatus())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_S_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(status);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByR_S_First(long resourcePrimKey, int status,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByR_S_First(resourcePrimKey, status,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByR_S_First(long resourcePrimKey, int status,
		OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByR_S(resourcePrimKey, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByR_S_Last(long resourcePrimKey, int status,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByR_S_Last(resourcePrimKey, status,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByR_S_Last(long resourcePrimKey, int status,
		OrderByComparator<KBArticle> orderByComparator) {
		int count = countByR_S(resourcePrimKey, status);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByR_S(resourcePrimKey, status, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param resourcePrimKey the resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByR_S_PrevAndNext(long kbArticleId,
		long resourcePrimKey, int status,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByR_S_PrevAndNext(session, kbArticle,
					resourcePrimKey, status, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByR_S_PrevAndNext(session, kbArticle,
					resourcePrimKey, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByR_S_PrevAndNext(Session session,
		KBArticle kbArticle, long resourcePrimKey, int status,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_S_RESOURCEPRIMKEY_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where resourcePrimKey = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param status the status
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_S(long[] resourcePrimKeies, int status) {
		return findByR_S(resourcePrimKeies, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_S(long[] resourcePrimKeies, int status,
		int start, int end) {
		return findByR_S(resourcePrimKeies, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_S(long[] resourcePrimKeies, int status,
		int start, int end, OrderByComparator<KBArticle> orderByComparator) {
		if (resourcePrimKeies == null) {
			resourcePrimKeies = new long[0];
		}
		else {
			resourcePrimKeies = ArrayUtil.unique(resourcePrimKeies);
		}

		if (resourcePrimKeies.length == 1) {
			return findByR_S(resourcePrimKeies[0], status, start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					StringUtil.merge(resourcePrimKeies), status
				};
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(resourcePrimKeies), status,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_R_S,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if (!ArrayUtil.contains(resourcePrimKeies,
							kbArticle.getResourcePrimKey()) ||
						(status != kbArticle.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			if (resourcePrimKeies.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_R_S_RESOURCEPRIMKEY_7);

				query.append(StringUtil.merge(resourcePrimKeies));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_S_STATUS_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(status);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_R_S,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_R_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the k b articles where resourcePrimKey = &#63; and status = &#63; from the database.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param status the status
	 */
	@Override
	public void removeByR_S(long resourcePrimKey, int status) {
		for (KBArticle kbArticle : findByR_S(resourcePrimKey, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where resourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param status the status
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByR_S(long resourcePrimKey, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_R_S;

		Object[] finderArgs = new Object[] { resourcePrimKey, status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_S_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(status);

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

	/**
	 * Returns the number of k b articles where resourcePrimKey = any &#63; and status = &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param status the status
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByR_S(long[] resourcePrimKeies, int status) {
		if (resourcePrimKeies == null) {
			resourcePrimKeies = new long[0];
		}
		else {
			resourcePrimKeies = ArrayUtil.unique(resourcePrimKeies);
		}

		Object[] finderArgs = new Object[] {
				StringUtil.merge(resourcePrimKeies), status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			if (resourcePrimKeies.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_R_S_RESOURCEPRIMKEY_7);

				query.append(StringUtil.merge(resourcePrimKeies));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_S_STATUS_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(status);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_S,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_R_S_RESOURCEPRIMKEY_2 = "kbArticle.resourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_R_S_RESOURCEPRIMKEY_7 = "kbArticle.resourcePrimKey IN (";
	private static final String _FINDER_COLUMN_R_S_STATUS_2 = "kbArticle.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_L",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_L",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			KBArticleModelImpl.GROUPID_COLUMN_BITMASK |
			KBArticleModelImpl.LATEST_COLUMN_BITMASK |
			KBArticleModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_L",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the k b articles where groupId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param latest the latest
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_L(long groupId, boolean latest) {
		return findByG_L(groupId, latest, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_L(long groupId, boolean latest, int start,
		int end) {
		return findByG_L(groupId, latest, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_L(long groupId, boolean latest, int start,
		int end, OrderByComparator<KBArticle> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_L;
			finderArgs = new Object[] { groupId, latest };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_L;
			finderArgs = new Object[] {
					groupId, latest,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						(latest != kbArticle.getLatest())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_L_GROUPID_2);

			query.append(_FINDER_COLUMN_G_L_LATEST_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(latest);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where groupId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByG_L_First(long groupId, boolean latest,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByG_L_First(groupId, latest,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", latest=");
		msg.append(latest);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where groupId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByG_L_First(long groupId, boolean latest,
		OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByG_L(groupId, latest, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByG_L_Last(long groupId, boolean latest,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByG_L_Last(groupId, latest, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", latest=");
		msg.append(latest);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByG_L_Last(long groupId, boolean latest,
		OrderByComparator<KBArticle> orderByComparator) {
		int count = countByG_L(groupId, latest);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByG_L(groupId, latest, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and latest = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByG_L_PrevAndNext(long kbArticleId, long groupId,
		boolean latest, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByG_L_PrevAndNext(session, kbArticle, groupId,
					latest, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByG_L_PrevAndNext(session, kbArticle, groupId,
					latest, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByG_L_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, boolean latest,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_L_GROUPID_2);

		query.append(_FINDER_COLUMN_G_L_LATEST_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(latest);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param latest the latest
	 * @return the matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_L(long groupId, boolean latest) {
		return filterFindByG_L(groupId, latest, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_L(long groupId, boolean latest,
		int start, int end) {
		return filterFindByG_L(groupId, latest, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_L(long groupId, boolean latest,
		int start, int end, OrderByComparator<KBArticle> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_L(groupId, latest, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_L_GROUPID_2);

		query.append(_FINDER_COLUMN_G_L_LATEST_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(latest);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and latest = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] filterFindByG_L_PrevAndNext(long kbArticleId,
		long groupId, boolean latest,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_L_PrevAndNext(kbArticleId, groupId, latest,
				orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByG_L_PrevAndNext(session, kbArticle, groupId,
					latest, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = filterGetByG_L_PrevAndNext(session, kbArticle, groupId,
					latest, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle filterGetByG_L_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, boolean latest,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_L_GROUPID_2);

		query.append(_FINDER_COLUMN_G_L_LATEST_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(latest);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the k b articles where groupId = &#63; and latest = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param latest the latest
	 */
	@Override
	public void removeByG_L(long groupId, boolean latest) {
		for (KBArticle kbArticle : findByG_L(groupId, latest,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where groupId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param latest the latest
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByG_L(long groupId, boolean latest) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_L;

		Object[] finderArgs = new Object[] { groupId, latest };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_L_GROUPID_2);

			query.append(_FINDER_COLUMN_G_L_LATEST_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(latest);

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

	/**
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param latest the latest
	 * @return the number of matching k b articles that the user has permission to view
	 */
	@Override
	public int filterCountByG_L(long groupId, boolean latest) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_L(groupId, latest);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_L_GROUPID_2);

		query.append(_FINDER_COLUMN_G_L_LATEST_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(latest);

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

	private static final String _FINDER_COLUMN_G_L_GROUPID_2 = "kbArticle.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_L_LATEST_2 = "kbArticle.latest = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_M",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_M",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			KBArticleModelImpl.GROUPID_COLUMN_BITMASK |
			KBArticleModelImpl.MAIN_COLUMN_BITMASK |
			KBArticleModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_M",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the k b articles where groupId = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param main the main
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_M(long groupId, boolean main) {
		return findByG_M(groupId, main, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_M(long groupId, boolean main, int start,
		int end) {
		return findByG_M(groupId, main, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_M(long groupId, boolean main, int start,
		int end, OrderByComparator<KBArticle> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M;
			finderArgs = new Object[] { groupId, main };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_M;
			finderArgs = new Object[] {
					groupId, main,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						(main != kbArticle.getMain())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_M_GROUPID_2);

			query.append(_FINDER_COLUMN_G_M_MAIN_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(main);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where groupId = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByG_M_First(long groupId, boolean main,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByG_M_First(groupId, main, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where groupId = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByG_M_First(long groupId, boolean main,
		OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByG_M(groupId, main, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByG_M_Last(long groupId, boolean main,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByG_M_Last(groupId, main, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByG_M_Last(long groupId, boolean main,
		OrderByComparator<KBArticle> orderByComparator) {
		int count = countByG_M(groupId, main);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByG_M(groupId, main, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and main = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByG_M_PrevAndNext(long kbArticleId, long groupId,
		boolean main, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByG_M_PrevAndNext(session, kbArticle, groupId, main,
					orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByG_M_PrevAndNext(session, kbArticle, groupId, main,
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

	protected KBArticle getByG_M_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, boolean main,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_M_GROUPID_2);

		query.append(_FINDER_COLUMN_G_M_MAIN_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(main);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param main the main
	 * @return the matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_M(long groupId, boolean main) {
		return filterFindByG_M(groupId, main, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_M(long groupId, boolean main,
		int start, int end) {
		return filterFindByG_M(groupId, main, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_M(long groupId, boolean main,
		int start, int end, OrderByComparator<KBArticle> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_M(groupId, main, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_M_GROUPID_2);

		query.append(_FINDER_COLUMN_G_M_MAIN_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(main);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and main = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] filterFindByG_M_PrevAndNext(long kbArticleId,
		long groupId, boolean main,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_M_PrevAndNext(kbArticleId, groupId, main,
				orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByG_M_PrevAndNext(session, kbArticle, groupId,
					main, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = filterGetByG_M_PrevAndNext(session, kbArticle, groupId,
					main, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle filterGetByG_M_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, boolean main,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_M_GROUPID_2);

		query.append(_FINDER_COLUMN_G_M_MAIN_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(main);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the k b articles where groupId = &#63; and main = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param main the main
	 */
	@Override
	public void removeByG_M(long groupId, boolean main) {
		for (KBArticle kbArticle : findByG_M(groupId, main, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where groupId = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param main the main
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByG_M(long groupId, boolean main) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_M;

		Object[] finderArgs = new Object[] { groupId, main };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_M_GROUPID_2);

			query.append(_FINDER_COLUMN_G_M_MAIN_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(main);

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

	/**
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param main the main
	 * @return the number of matching k b articles that the user has permission to view
	 */
	@Override
	public int filterCountByG_M(long groupId, boolean main) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_M(groupId, main);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_M_GROUPID_2);

		query.append(_FINDER_COLUMN_G_M_MAIN_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(main);

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

	private static final String _FINDER_COLUMN_G_M_GROUPID_2 = "kbArticle.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_M_MAIN_2 = "kbArticle.main = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			KBArticleModelImpl.GROUPID_COLUMN_BITMASK |
			KBArticleModelImpl.STATUS_COLUMN_BITMASK |
			KBArticleModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_S",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the k b articles where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_S(long groupId, int status) {
		return findByG_S(groupId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_S(long groupId, int status, int start,
		int end) {
		return findByG_S(groupId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_S(long groupId, int status, int start,
		int end, OrderByComparator<KBArticle> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S;
			finderArgs = new Object[] { groupId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S;
			finderArgs = new Object[] {
					groupId, status,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						(status != kbArticle.getStatus())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(status);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByG_S_First(long groupId, int status,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByG_S_First(groupId, status,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByG_S_First(long groupId, int status,
		OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByG_S(groupId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByG_S_Last(long groupId, int status,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByG_S_Last(groupId, status, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByG_S_Last(long groupId, int status,
		OrderByComparator<KBArticle> orderByComparator) {
		int count = countByG_S(groupId, status);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByG_S(groupId, status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and status = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByG_S_PrevAndNext(long kbArticleId, long groupId,
		int status, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByG_S_PrevAndNext(session, kbArticle, groupId,
					status, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByG_S_PrevAndNext(session, kbArticle, groupId,
					status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByG_S_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, int status,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_S_STATUS_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_S(long groupId, int status) {
		return filterFindByG_S(groupId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_S(long groupId, int status, int start,
		int end) {
		return filterFindByG_S(groupId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_S(long groupId, int status, int start,
		int end, OrderByComparator<KBArticle> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_S(groupId, status, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(status);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] filterFindByG_S_PrevAndNext(long kbArticleId,
		long groupId, int status, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_S_PrevAndNext(kbArticleId, groupId, status,
				orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByG_S_PrevAndNext(session, kbArticle, groupId,
					status, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = filterGetByG_S_PrevAndNext(session, kbArticle, groupId,
					status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle filterGetByG_S_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, int status,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the k b articles where groupId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 */
	@Override
	public void removeByG_S(long groupId, int status) {
		for (KBArticle kbArticle : findByG_S(groupId, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByG_S(long groupId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_S;

		Object[] finderArgs = new Object[] { groupId, status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(status);

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

	/**
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching k b articles that the user has permission to view
	 */
	@Override
	public int filterCountByG_S(long groupId, int status) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_S(groupId, status);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_S_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(status);

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

	private static final String _FINDER_COLUMN_G_S_GROUPID_2 = "kbArticle.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_S_STATUS_2 = "kbArticle.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_L",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_L",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			KBArticleModelImpl.COMPANYID_COLUMN_BITMASK |
			KBArticleModelImpl.LATEST_COLUMN_BITMASK |
			KBArticleModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_L",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the k b articles where companyId = &#63; and latest = &#63;.
	 *
	 * @param companyId the company ID
	 * @param latest the latest
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByC_L(long companyId, boolean latest) {
		return findByC_L(companyId, latest, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where companyId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByC_L(long companyId, boolean latest, int start,
		int end) {
		return findByC_L(companyId, latest, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where companyId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByC_L(long companyId, boolean latest, int start,
		int end, OrderByComparator<KBArticle> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_L;
			finderArgs = new Object[] { companyId, latest };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_L;
			finderArgs = new Object[] {
					companyId, latest,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((companyId != kbArticle.getCompanyId()) ||
						(latest != kbArticle.getLatest())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_C_L_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_L_LATEST_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(latest);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where companyId = &#63; and latest = &#63;.
	 *
	 * @param companyId the company ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByC_L_First(long companyId, boolean latest,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByC_L_First(companyId, latest,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", latest=");
		msg.append(latest);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where companyId = &#63; and latest = &#63;.
	 *
	 * @param companyId the company ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByC_L_First(long companyId, boolean latest,
		OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByC_L(companyId, latest, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where companyId = &#63; and latest = &#63;.
	 *
	 * @param companyId the company ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByC_L_Last(long companyId, boolean latest,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByC_L_Last(companyId, latest,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", latest=");
		msg.append(latest);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where companyId = &#63; and latest = &#63;.
	 *
	 * @param companyId the company ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByC_L_Last(long companyId, boolean latest,
		OrderByComparator<KBArticle> orderByComparator) {
		int count = countByC_L(companyId, latest);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByC_L(companyId, latest, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where companyId = &#63; and latest = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param companyId the company ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByC_L_PrevAndNext(long kbArticleId, long companyId,
		boolean latest, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByC_L_PrevAndNext(session, kbArticle, companyId,
					latest, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByC_L_PrevAndNext(session, kbArticle, companyId,
					latest, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByC_L_PrevAndNext(Session session,
		KBArticle kbArticle, long companyId, boolean latest,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_C_L_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_L_LATEST_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(latest);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the k b articles where companyId = &#63; and latest = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param latest the latest
	 */
	@Override
	public void removeByC_L(long companyId, boolean latest) {
		for (KBArticle kbArticle : findByC_L(companyId, latest,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where companyId = &#63; and latest = &#63;.
	 *
	 * @param companyId the company ID
	 * @param latest the latest
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByC_L(long companyId, boolean latest) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_L;

		Object[] finderArgs = new Object[] { companyId, latest };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_C_L_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_L_LATEST_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(latest);

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

	private static final String _FINDER_COLUMN_C_L_COMPANYID_2 = "kbArticle.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_L_LATEST_2 = "kbArticle.latest = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_M",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_M",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			KBArticleModelImpl.COMPANYID_COLUMN_BITMASK |
			KBArticleModelImpl.MAIN_COLUMN_BITMASK |
			KBArticleModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_M",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the k b articles where companyId = &#63; and main = &#63;.
	 *
	 * @param companyId the company ID
	 * @param main the main
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByC_M(long companyId, boolean main) {
		return findByC_M(companyId, main, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the k b articles where companyId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByC_M(long companyId, boolean main, int start,
		int end) {
		return findByC_M(companyId, main, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where companyId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByC_M(long companyId, boolean main, int start,
		int end, OrderByComparator<KBArticle> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_M;
			finderArgs = new Object[] { companyId, main };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_M;
			finderArgs = new Object[] {
					companyId, main,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((companyId != kbArticle.getCompanyId()) ||
						(main != kbArticle.getMain())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_C_M_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_M_MAIN_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(main);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where companyId = &#63; and main = &#63;.
	 *
	 * @param companyId the company ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByC_M_First(long companyId, boolean main,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByC_M_First(companyId, main,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where companyId = &#63; and main = &#63;.
	 *
	 * @param companyId the company ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByC_M_First(long companyId, boolean main,
		OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByC_M(companyId, main, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where companyId = &#63; and main = &#63;.
	 *
	 * @param companyId the company ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByC_M_Last(long companyId, boolean main,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByC_M_Last(companyId, main, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where companyId = &#63; and main = &#63;.
	 *
	 * @param companyId the company ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByC_M_Last(long companyId, boolean main,
		OrderByComparator<KBArticle> orderByComparator) {
		int count = countByC_M(companyId, main);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByC_M(companyId, main, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where companyId = &#63; and main = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param companyId the company ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByC_M_PrevAndNext(long kbArticleId, long companyId,
		boolean main, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByC_M_PrevAndNext(session, kbArticle, companyId,
					main, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByC_M_PrevAndNext(session, kbArticle, companyId,
					main, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByC_M_PrevAndNext(Session session,
		KBArticle kbArticle, long companyId, boolean main,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_C_M_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_M_MAIN_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(main);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the k b articles where companyId = &#63; and main = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param main the main
	 */
	@Override
	public void removeByC_M(long companyId, boolean main) {
		for (KBArticle kbArticle : findByC_M(companyId, main,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where companyId = &#63; and main = &#63;.
	 *
	 * @param companyId the company ID
	 * @param main the main
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByC_M(long companyId, boolean main) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_M;

		Object[] finderArgs = new Object[] { companyId, main };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_C_M_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_M_MAIN_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(main);

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

	private static final String _FINDER_COLUMN_C_M_COMPANYID_2 = "kbArticle.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_M_MAIN_2 = "kbArticle.main = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			KBArticleModelImpl.COMPANYID_COLUMN_BITMASK |
			KBArticleModelImpl.STATUS_COLUMN_BITMASK |
			KBArticleModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_S",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the k b articles where companyId = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByC_S(long companyId, int status) {
		return findByC_S(companyId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where companyId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByC_S(long companyId, int status, int start,
		int end) {
		return findByC_S(companyId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where companyId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByC_S(long companyId, int status, int start,
		int end, OrderByComparator<KBArticle> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S;
			finderArgs = new Object[] { companyId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_S;
			finderArgs = new Object[] {
					companyId, status,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((companyId != kbArticle.getCompanyId()) ||
						(status != kbArticle.getStatus())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_C_S_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(status);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where companyId = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByC_S_First(long companyId, int status,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByC_S_First(companyId, status,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where companyId = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByC_S_First(long companyId, int status,
		OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByC_S(companyId, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where companyId = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByC_S_Last(long companyId, int status,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByC_S_Last(companyId, status,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where companyId = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByC_S_Last(long companyId, int status,
		OrderByComparator<KBArticle> orderByComparator) {
		int count = countByC_S(companyId, status);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByC_S(companyId, status, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where companyId = &#63; and status = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param companyId the company ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByC_S_PrevAndNext(long kbArticleId, long companyId,
		int status, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByC_S_PrevAndNext(session, kbArticle, companyId,
					status, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByC_S_PrevAndNext(session, kbArticle, companyId,
					status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByC_S_PrevAndNext(Session session,
		KBArticle kbArticle, long companyId, int status,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_C_S_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_S_STATUS_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the k b articles where companyId = &#63; and status = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 */
	@Override
	public void removeByC_S(long companyId, int status) {
		for (KBArticle kbArticle : findByC_S(companyId, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where companyId = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param status the status
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByC_S(long companyId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_S;

		Object[] finderArgs = new Object[] { companyId, status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_C_S_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_C_S_COMPANYID_2 = "kbArticle.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_S_STATUS_2 = "kbArticle.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_P_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP_L",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_L",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			KBArticleModelImpl.PARENTRESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.LATEST_COLUMN_BITMASK |
			KBArticleModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_P_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_L",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_P_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByP_L",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the k b articles where parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByP_L(long parentResourcePrimKey, boolean latest) {
		return findByP_L(parentResourcePrimKey, latest, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByP_L(long parentResourcePrimKey,
		boolean latest, int start, int end) {
		return findByP_L(parentResourcePrimKey, latest, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByP_L(long parentResourcePrimKey,
		boolean latest, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_L;
			finderArgs = new Object[] { parentResourcePrimKey, latest };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_P_L;
			finderArgs = new Object[] {
					parentResourcePrimKey, latest,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((parentResourcePrimKey != kbArticle.getParentResourcePrimKey()) ||
						(latest != kbArticle.getLatest())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_P_L_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_P_L_LATEST_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentResourcePrimKey);

				qPos.add(latest);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByP_L_First(long parentResourcePrimKey,
		boolean latest, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByP_L_First(parentResourcePrimKey, latest,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", latest=");
		msg.append(latest);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByP_L_First(long parentResourcePrimKey,
		boolean latest, OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByP_L(parentResourcePrimKey, latest, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByP_L_Last(long parentResourcePrimKey, boolean latest,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByP_L_Last(parentResourcePrimKey, latest,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", latest=");
		msg.append(latest);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByP_L_Last(long parentResourcePrimKey,
		boolean latest, OrderByComparator<KBArticle> orderByComparator) {
		int count = countByP_L(parentResourcePrimKey, latest);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByP_L(parentResourcePrimKey, latest,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByP_L_PrevAndNext(long kbArticleId,
		long parentResourcePrimKey, boolean latest,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByP_L_PrevAndNext(session, kbArticle,
					parentResourcePrimKey, latest, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByP_L_PrevAndNext(session, kbArticle,
					parentResourcePrimKey, latest, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByP_L_PrevAndNext(Session session,
		KBArticle kbArticle, long parentResourcePrimKey, boolean latest,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_P_L_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_P_L_LATEST_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentResourcePrimKey);

		qPos.add(latest);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where parentResourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param latest the latest
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByP_L(long[] parentResourcePrimKeies,
		boolean latest) {
		return findByP_L(parentResourcePrimKeies, latest, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where parentResourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByP_L(long[] parentResourcePrimKeies,
		boolean latest, int start, int end) {
		return findByP_L(parentResourcePrimKeies, latest, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where parentResourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByP_L(long[] parentResourcePrimKeies,
		boolean latest, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (parentResourcePrimKeies == null) {
			parentResourcePrimKeies = new long[0];
		}
		else {
			parentResourcePrimKeies = ArrayUtil.unique(parentResourcePrimKeies);
		}

		if (parentResourcePrimKeies.length == 1) {
			return findByP_L(parentResourcePrimKeies[0], latest, start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					StringUtil.merge(parentResourcePrimKeies), latest
				};
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(parentResourcePrimKeies), latest,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_P_L,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if (!ArrayUtil.contains(parentResourcePrimKeies,
							kbArticle.getParentResourcePrimKey()) ||
						(latest != kbArticle.getLatest())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			if (parentResourcePrimKeies.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_P_L_PARENTRESOURCEPRIMKEY_7);

				query.append(StringUtil.merge(parentResourcePrimKeies));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_P_L_LATEST_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(latest);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_P_L,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_P_L,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the k b articles where parentResourcePrimKey = &#63; and latest = &#63; from the database.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 */
	@Override
	public void removeByP_L(long parentResourcePrimKey, boolean latest) {
		for (KBArticle kbArticle : findByP_L(parentResourcePrimKey, latest,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByP_L(long parentResourcePrimKey, boolean latest) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_P_L;

		Object[] finderArgs = new Object[] { parentResourcePrimKey, latest };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_P_L_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_P_L_LATEST_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentResourcePrimKey);

				qPos.add(latest);

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

	/**
	 * Returns the number of k b articles where parentResourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param latest the latest
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByP_L(long[] parentResourcePrimKeies, boolean latest) {
		if (parentResourcePrimKeies == null) {
			parentResourcePrimKeies = new long[0];
		}
		else {
			parentResourcePrimKeies = ArrayUtil.unique(parentResourcePrimKeies);
		}

		Object[] finderArgs = new Object[] {
				StringUtil.merge(parentResourcePrimKeies), latest
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_P_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			if (parentResourcePrimKeies.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_P_L_PARENTRESOURCEPRIMKEY_7);

				query.append(StringUtil.merge(parentResourcePrimKeies));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_P_L_LATEST_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(latest);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_P_L,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_P_L,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_P_L_PARENTRESOURCEPRIMKEY_2 = "kbArticle.parentResourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_P_L_PARENTRESOURCEPRIMKEY_7 = "kbArticle.parentResourcePrimKey IN (";
	private static final String _FINDER_COLUMN_P_L_LATEST_2 = "kbArticle.latest = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_P_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP_M",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_M",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			KBArticleModelImpl.PARENTRESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.MAIN_COLUMN_BITMASK |
			KBArticleModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_P_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_M",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_P_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByP_M",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the k b articles where parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByP_M(long parentResourcePrimKey, boolean main) {
		return findByP_M(parentResourcePrimKey, main, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByP_M(long parentResourcePrimKey, boolean main,
		int start, int end) {
		return findByP_M(parentResourcePrimKey, main, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByP_M(long parentResourcePrimKey, boolean main,
		int start, int end, OrderByComparator<KBArticle> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_M;
			finderArgs = new Object[] { parentResourcePrimKey, main };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_P_M;
			finderArgs = new Object[] {
					parentResourcePrimKey, main,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((parentResourcePrimKey != kbArticle.getParentResourcePrimKey()) ||
						(main != kbArticle.getMain())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_P_M_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_P_M_MAIN_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentResourcePrimKey);

				qPos.add(main);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByP_M_First(long parentResourcePrimKey, boolean main,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByP_M_First(parentResourcePrimKey, main,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByP_M_First(long parentResourcePrimKey, boolean main,
		OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByP_M(parentResourcePrimKey, main, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByP_M_Last(long parentResourcePrimKey, boolean main,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByP_M_Last(parentResourcePrimKey, main,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByP_M_Last(long parentResourcePrimKey, boolean main,
		OrderByComparator<KBArticle> orderByComparator) {
		int count = countByP_M(parentResourcePrimKey, main);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByP_M(parentResourcePrimKey, main,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByP_M_PrevAndNext(long kbArticleId,
		long parentResourcePrimKey, boolean main,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByP_M_PrevAndNext(session, kbArticle,
					parentResourcePrimKey, main, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByP_M_PrevAndNext(session, kbArticle,
					parentResourcePrimKey, main, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByP_M_PrevAndNext(Session session,
		KBArticle kbArticle, long parentResourcePrimKey, boolean main,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_P_M_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_P_M_MAIN_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentResourcePrimKey);

		qPos.add(main);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where parentResourcePrimKey = any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param main the main
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByP_M(long[] parentResourcePrimKeies,
		boolean main) {
		return findByP_M(parentResourcePrimKeies, main, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where parentResourcePrimKey = any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByP_M(long[] parentResourcePrimKeies,
		boolean main, int start, int end) {
		return findByP_M(parentResourcePrimKeies, main, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where parentResourcePrimKey = any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByP_M(long[] parentResourcePrimKeies,
		boolean main, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (parentResourcePrimKeies == null) {
			parentResourcePrimKeies = new long[0];
		}
		else {
			parentResourcePrimKeies = ArrayUtil.unique(parentResourcePrimKeies);
		}

		if (parentResourcePrimKeies.length == 1) {
			return findByP_M(parentResourcePrimKeies[0], main, start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					StringUtil.merge(parentResourcePrimKeies), main
				};
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(parentResourcePrimKeies), main,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_P_M,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if (!ArrayUtil.contains(parentResourcePrimKeies,
							kbArticle.getParentResourcePrimKey()) ||
						(main != kbArticle.getMain())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			if (parentResourcePrimKeies.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_P_M_PARENTRESOURCEPRIMKEY_7);

				query.append(StringUtil.merge(parentResourcePrimKeies));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_P_M_MAIN_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(main);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_P_M,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_P_M,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the k b articles where parentResourcePrimKey = &#63; and main = &#63; from the database.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 */
	@Override
	public void removeByP_M(long parentResourcePrimKey, boolean main) {
		for (KBArticle kbArticle : findByP_M(parentResourcePrimKey, main,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByP_M(long parentResourcePrimKey, boolean main) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_P_M;

		Object[] finderArgs = new Object[] { parentResourcePrimKey, main };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_P_M_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_P_M_MAIN_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentResourcePrimKey);

				qPos.add(main);

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

	/**
	 * Returns the number of k b articles where parentResourcePrimKey = any &#63; and main = &#63;.
	 *
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param main the main
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByP_M(long[] parentResourcePrimKeies, boolean main) {
		if (parentResourcePrimKeies == null) {
			parentResourcePrimKeies = new long[0];
		}
		else {
			parentResourcePrimKeies = ArrayUtil.unique(parentResourcePrimKeies);
		}

		Object[] finderArgs = new Object[] {
				StringUtil.merge(parentResourcePrimKeies), main
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_P_M,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			if (parentResourcePrimKeies.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_P_M_PARENTRESOURCEPRIMKEY_7);

				query.append(StringUtil.merge(parentResourcePrimKeies));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_P_M_MAIN_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(main);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_P_M,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_P_M,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_P_M_PARENTRESOURCEPRIMKEY_2 = "kbArticle.parentResourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_P_M_PARENTRESOURCEPRIMKEY_7 = "kbArticle.parentResourcePrimKey IN (";
	private static final String _FINDER_COLUMN_P_M_MAIN_2 = "kbArticle.main = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_P_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_S",
			new String[] { Long.class.getName(), Integer.class.getName() },
			KBArticleModelImpl.PARENTRESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.STATUS_COLUMN_BITMASK |
			KBArticleModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_P_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_S",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_P_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByP_S",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the k b articles where parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByP_S(long parentResourcePrimKey, int status) {
		return findByP_S(parentResourcePrimKey, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByP_S(long parentResourcePrimKey, int status,
		int start, int end) {
		return findByP_S(parentResourcePrimKey, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByP_S(long parentResourcePrimKey, int status,
		int start, int end, OrderByComparator<KBArticle> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S;
			finderArgs = new Object[] { parentResourcePrimKey, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_P_S;
			finderArgs = new Object[] {
					parentResourcePrimKey, status,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((parentResourcePrimKey != kbArticle.getParentResourcePrimKey()) ||
						(status != kbArticle.getStatus())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_P_S_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_P_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentResourcePrimKey);

				qPos.add(status);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByP_S_First(long parentResourcePrimKey, int status,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByP_S_First(parentResourcePrimKey, status,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByP_S_First(long parentResourcePrimKey, int status,
		OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByP_S(parentResourcePrimKey, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByP_S_Last(long parentResourcePrimKey, int status,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByP_S_Last(parentResourcePrimKey, status,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByP_S_Last(long parentResourcePrimKey, int status,
		OrderByComparator<KBArticle> orderByComparator) {
		int count = countByP_S(parentResourcePrimKey, status);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByP_S(parentResourcePrimKey, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByP_S_PrevAndNext(long kbArticleId,
		long parentResourcePrimKey, int status,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByP_S_PrevAndNext(session, kbArticle,
					parentResourcePrimKey, status, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByP_S_PrevAndNext(session, kbArticle,
					parentResourcePrimKey, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByP_S_PrevAndNext(Session session,
		KBArticle kbArticle, long parentResourcePrimKey, int status,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_P_S_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_P_S_STATUS_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentResourcePrimKey);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles where parentResourcePrimKey = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param status the status
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByP_S(long[] parentResourcePrimKeies, int status) {
		return findByP_S(parentResourcePrimKeies, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where parentResourcePrimKey = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByP_S(long[] parentResourcePrimKeies,
		int status, int start, int end) {
		return findByP_S(parentResourcePrimKeies, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where parentResourcePrimKey = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByP_S(long[] parentResourcePrimKeies,
		int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (parentResourcePrimKeies == null) {
			parentResourcePrimKeies = new long[0];
		}
		else {
			parentResourcePrimKeies = ArrayUtil.unique(parentResourcePrimKeies);
		}

		if (parentResourcePrimKeies.length == 1) {
			return findByP_S(parentResourcePrimKeies[0], status, start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					StringUtil.merge(parentResourcePrimKeies), status
				};
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(parentResourcePrimKeies), status,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_P_S,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if (!ArrayUtil.contains(parentResourcePrimKeies,
							kbArticle.getParentResourcePrimKey()) ||
						(status != kbArticle.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			if (parentResourcePrimKeies.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_P_S_PARENTRESOURCEPRIMKEY_7);

				query.append(StringUtil.merge(parentResourcePrimKeies));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_P_S_STATUS_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(status);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_P_S,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_P_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the k b articles where parentResourcePrimKey = &#63; and status = &#63; from the database.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 */
	@Override
	public void removeByP_S(long parentResourcePrimKey, int status) {
		for (KBArticle kbArticle : findByP_S(parentResourcePrimKey, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByP_S(long parentResourcePrimKey, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_P_S;

		Object[] finderArgs = new Object[] { parentResourcePrimKey, status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_P_S_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_P_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentResourcePrimKey);

				qPos.add(status);

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

	/**
	 * Returns the number of k b articles where parentResourcePrimKey = any &#63; and status = &#63;.
	 *
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param status the status
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByP_S(long[] parentResourcePrimKeies, int status) {
		if (parentResourcePrimKeies == null) {
			parentResourcePrimKeies = new long[0];
		}
		else {
			parentResourcePrimKeies = ArrayUtil.unique(parentResourcePrimKeies);
		}

		Object[] finderArgs = new Object[] {
				StringUtil.merge(parentResourcePrimKeies), status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_P_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			if (parentResourcePrimKeies.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_P_S_PARENTRESOURCEPRIMKEY_7);

				query.append(StringUtil.merge(parentResourcePrimKeies));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_P_S_STATUS_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(status);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_P_S,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_P_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_P_S_PARENTRESOURCEPRIMKEY_2 = "kbArticle.parentResourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_P_S_PARENTRESOURCEPRIMKEY_7 = "kbArticle.parentResourcePrimKey IN (";
	private static final String _FINDER_COLUMN_P_S_STATUS_2 = "kbArticle.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByR_G_L",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByR_G_L",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			},
			KBArticleModelImpl.RESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.GROUPID_COLUMN_BITMASK |
			KBArticleModelImpl.LATEST_COLUMN_BITMASK |
			KBArticleModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_R_G_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByR_G_L",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_G_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByR_G_L",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_G_L(long resourcePrimKey, long groupId,
		boolean latest) {
		return findByR_G_L(resourcePrimKey, groupId, latest, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_G_L(long resourcePrimKey, long groupId,
		boolean latest, int start, int end) {
		return findByR_G_L(resourcePrimKey, groupId, latest, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_G_L(long resourcePrimKey, long groupId,
		boolean latest, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_L;
			finderArgs = new Object[] { resourcePrimKey, groupId, latest };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G_L;
			finderArgs = new Object[] {
					resourcePrimKey, groupId, latest,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((resourcePrimKey != kbArticle.getResourcePrimKey()) ||
						(groupId != kbArticle.getGroupId()) ||
						(latest != kbArticle.getLatest())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_G_L_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_G_L_GROUPID_2);

			query.append(_FINDER_COLUMN_R_G_L_LATEST_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(groupId);

				qPos.add(latest);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByR_G_L_First(long resourcePrimKey, long groupId,
		boolean latest, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByR_G_L_First(resourcePrimKey, groupId,
				latest, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", latest=");
		msg.append(latest);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByR_G_L_First(long resourcePrimKey, long groupId,
		boolean latest, OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByR_G_L(resourcePrimKey, groupId, latest, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByR_G_L_Last(long resourcePrimKey, long groupId,
		boolean latest, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByR_G_L_Last(resourcePrimKey, groupId,
				latest, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", latest=");
		msg.append(latest);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByR_G_L_Last(long resourcePrimKey, long groupId,
		boolean latest, OrderByComparator<KBArticle> orderByComparator) {
		int count = countByR_G_L(resourcePrimKey, groupId, latest);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByR_G_L(resourcePrimKey, groupId, latest,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByR_G_L_PrevAndNext(long kbArticleId,
		long resourcePrimKey, long groupId, boolean latest,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByR_G_L_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, latest, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByR_G_L_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, latest, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByR_G_L_PrevAndNext(Session session,
		KBArticle kbArticle, long resourcePrimKey, long groupId,
		boolean latest, OrderByComparator<KBArticle> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_G_L_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_L_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_L_LATEST_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(groupId);

		qPos.add(latest);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @return the matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByR_G_L(long resourcePrimKey,
		long groupId, boolean latest) {
		return filterFindByR_G_L(resourcePrimKey, groupId, latest,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByR_G_L(long resourcePrimKey,
		long groupId, boolean latest, int start, int end) {
		return filterFindByR_G_L(resourcePrimKey, groupId, latest, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByR_G_L(long resourcePrimKey,
		long groupId, boolean latest, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByR_G_L(resourcePrimKey, groupId, latest, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_R_G_L_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_L_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_L_LATEST_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);

			qPos.add(groupId);

			qPos.add(latest);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] filterFindByR_G_L_PrevAndNext(long kbArticleId,
		long resourcePrimKey, long groupId, boolean latest,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByR_G_L_PrevAndNext(kbArticleId, resourcePrimKey,
				groupId, latest, orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByR_G_L_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, latest, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = filterGetByR_G_L_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, latest, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle filterGetByR_G_L_PrevAndNext(Session session,
		KBArticle kbArticle, long resourcePrimKey, long groupId,
		boolean latest, OrderByComparator<KBArticle> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_R_G_L_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_L_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_L_LATEST_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(groupId);

		qPos.add(latest);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param latest the latest
	 * @return the matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByR_G_L(long[] resourcePrimKeies,
		long groupId, boolean latest) {
		return filterFindByR_G_L(resourcePrimKeies, groupId, latest,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByR_G_L(long[] resourcePrimKeies,
		long groupId, boolean latest, int start, int end) {
		return filterFindByR_G_L(resourcePrimKeies, groupId, latest, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByR_G_L(long[] resourcePrimKeies,
		long groupId, boolean latest, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByR_G_L(resourcePrimKeies, groupId, latest, start, end,
				orderByComparator);
		}

		if (resourcePrimKeies == null) {
			resourcePrimKeies = new long[0];
		}
		else {
			resourcePrimKeies = ArrayUtil.unique(resourcePrimKeies);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		if (resourcePrimKeies.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			query.append(_FINDER_COLUMN_R_G_L_RESOURCEPRIMKEY_7);

			query.append(StringUtil.merge(resourcePrimKeies));

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_R_G_L_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_L_LATEST_2);

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(latest);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param latest the latest
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_G_L(long[] resourcePrimKeies, long groupId,
		boolean latest) {
		return findByR_G_L(resourcePrimKeies, groupId, latest,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_G_L(long[] resourcePrimKeies, long groupId,
		boolean latest, int start, int end) {
		return findByR_G_L(resourcePrimKeies, groupId, latest, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_G_L(long[] resourcePrimKeies, long groupId,
		boolean latest, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (resourcePrimKeies == null) {
			resourcePrimKeies = new long[0];
		}
		else {
			resourcePrimKeies = ArrayUtil.unique(resourcePrimKeies);
		}

		if (resourcePrimKeies.length == 1) {
			return findByR_G_L(resourcePrimKeies[0], groupId, latest, start,
				end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					StringUtil.merge(resourcePrimKeies), groupId, latest
				};
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(resourcePrimKeies), groupId, latest,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G_L,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if (!ArrayUtil.contains(resourcePrimKeies,
							kbArticle.getResourcePrimKey()) ||
						(groupId != kbArticle.getGroupId()) ||
						(latest != kbArticle.getLatest())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			if (resourcePrimKeies.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_R_G_L_RESOURCEPRIMKEY_7);

				query.append(StringUtil.merge(resourcePrimKeies));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_G_L_GROUPID_2);

			query.append(_FINDER_COLUMN_R_G_L_LATEST_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(latest);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G_L,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G_L,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63; from the database.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 */
	@Override
	public void removeByR_G_L(long resourcePrimKey, long groupId, boolean latest) {
		for (KBArticle kbArticle : findByR_G_L(resourcePrimKey, groupId,
				latest, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByR_G_L(long resourcePrimKey, long groupId, boolean latest) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_R_G_L;

		Object[] finderArgs = new Object[] { resourcePrimKey, groupId, latest };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_G_L_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_G_L_GROUPID_2);

			query.append(_FINDER_COLUMN_R_G_L_LATEST_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(groupId);

				qPos.add(latest);

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

	/**
	 * Returns the number of k b articles where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param latest the latest
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByR_G_L(long[] resourcePrimKeies, long groupId,
		boolean latest) {
		if (resourcePrimKeies == null) {
			resourcePrimKeies = new long[0];
		}
		else {
			resourcePrimKeies = ArrayUtil.unique(resourcePrimKeies);
		}

		Object[] finderArgs = new Object[] {
				StringUtil.merge(resourcePrimKeies), groupId, latest
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_G_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			if (resourcePrimKeies.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_R_G_L_RESOURCEPRIMKEY_7);

				query.append(StringUtil.merge(resourcePrimKeies));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_G_L_GROUPID_2);

			query.append(_FINDER_COLUMN_R_G_L_LATEST_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(latest);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_G_L,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_G_L,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param latest the latest
	 * @return the number of matching k b articles that the user has permission to view
	 */
	@Override
	public int filterCountByR_G_L(long resourcePrimKey, long groupId,
		boolean latest) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByR_G_L(resourcePrimKey, groupId, latest);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_G_L_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_L_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_L_LATEST_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);

			qPos.add(groupId);

			qPos.add(latest);

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

	/**
	 * Returns the number of k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param latest the latest
	 * @return the number of matching k b articles that the user has permission to view
	 */
	@Override
	public int filterCountByR_G_L(long[] resourcePrimKeies, long groupId,
		boolean latest) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByR_G_L(resourcePrimKeies, groupId, latest);
		}

		if (resourcePrimKeies == null) {
			resourcePrimKeies = new long[0];
		}
		else {
			resourcePrimKeies = ArrayUtil.unique(resourcePrimKeies);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		if (resourcePrimKeies.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			query.append(_FINDER_COLUMN_R_G_L_RESOURCEPRIMKEY_7);

			query.append(StringUtil.merge(resourcePrimKeies));

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_R_G_L_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_L_LATEST_2);

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(latest);

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

	private static final String _FINDER_COLUMN_R_G_L_RESOURCEPRIMKEY_2 = "kbArticle.resourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_R_G_L_RESOURCEPRIMKEY_7 = "kbArticle.resourcePrimKey IN (";
	private static final String _FINDER_COLUMN_R_G_L_GROUPID_2 = "kbArticle.groupId = ? AND ";
	private static final String _FINDER_COLUMN_R_G_L_LATEST_2 = "kbArticle.latest = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByR_G_M",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByR_G_M",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			},
			KBArticleModelImpl.RESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.GROUPID_COLUMN_BITMASK |
			KBArticleModelImpl.MAIN_COLUMN_BITMASK |
			KBArticleModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_R_G_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByR_G_M",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_G_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByR_G_M",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_G_M(long resourcePrimKey, long groupId,
		boolean main) {
		return findByR_G_M(resourcePrimKey, groupId, main, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_G_M(long resourcePrimKey, long groupId,
		boolean main, int start, int end) {
		return findByR_G_M(resourcePrimKey, groupId, main, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_G_M(long resourcePrimKey, long groupId,
		boolean main, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_M;
			finderArgs = new Object[] { resourcePrimKey, groupId, main };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G_M;
			finderArgs = new Object[] {
					resourcePrimKey, groupId, main,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((resourcePrimKey != kbArticle.getResourcePrimKey()) ||
						(groupId != kbArticle.getGroupId()) ||
						(main != kbArticle.getMain())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_G_M_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_G_M_GROUPID_2);

			query.append(_FINDER_COLUMN_R_G_M_MAIN_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(groupId);

				qPos.add(main);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByR_G_M_First(long resourcePrimKey, long groupId,
		boolean main, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByR_G_M_First(resourcePrimKey, groupId,
				main, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByR_G_M_First(long resourcePrimKey, long groupId,
		boolean main, OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByR_G_M(resourcePrimKey, groupId, main, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByR_G_M_Last(long resourcePrimKey, long groupId,
		boolean main, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByR_G_M_Last(resourcePrimKey, groupId, main,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByR_G_M_Last(long resourcePrimKey, long groupId,
		boolean main, OrderByComparator<KBArticle> orderByComparator) {
		int count = countByR_G_M(resourcePrimKey, groupId, main);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByR_G_M(resourcePrimKey, groupId, main,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByR_G_M_PrevAndNext(long kbArticleId,
		long resourcePrimKey, long groupId, boolean main,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByR_G_M_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, main, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByR_G_M_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, main, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByR_G_M_PrevAndNext(Session session,
		KBArticle kbArticle, long resourcePrimKey, long groupId, boolean main,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_G_M_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_M_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_M_MAIN_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(groupId);

		qPos.add(main);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @return the matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByR_G_M(long resourcePrimKey,
		long groupId, boolean main) {
		return filterFindByR_G_M(resourcePrimKey, groupId, main,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByR_G_M(long resourcePrimKey,
		long groupId, boolean main, int start, int end) {
		return filterFindByR_G_M(resourcePrimKey, groupId, main, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByR_G_M(long resourcePrimKey,
		long groupId, boolean main, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByR_G_M(resourcePrimKey, groupId, main, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_R_G_M_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_M_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_M_MAIN_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);

			qPos.add(groupId);

			qPos.add(main);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] filterFindByR_G_M_PrevAndNext(long kbArticleId,
		long resourcePrimKey, long groupId, boolean main,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByR_G_M_PrevAndNext(kbArticleId, resourcePrimKey,
				groupId, main, orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByR_G_M_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, main, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = filterGetByR_G_M_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, main, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle filterGetByR_G_M_PrevAndNext(Session session,
		KBArticle kbArticle, long resourcePrimKey, long groupId, boolean main,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_R_G_M_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_M_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_M_MAIN_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(groupId);

		qPos.add(main);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param main the main
	 * @return the matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByR_G_M(long[] resourcePrimKeies,
		long groupId, boolean main) {
		return filterFindByR_G_M(resourcePrimKeies, groupId, main,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByR_G_M(long[] resourcePrimKeies,
		long groupId, boolean main, int start, int end) {
		return filterFindByR_G_M(resourcePrimKeies, groupId, main, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByR_G_M(long[] resourcePrimKeies,
		long groupId, boolean main, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByR_G_M(resourcePrimKeies, groupId, main, start, end,
				orderByComparator);
		}

		if (resourcePrimKeies == null) {
			resourcePrimKeies = new long[0];
		}
		else {
			resourcePrimKeies = ArrayUtil.unique(resourcePrimKeies);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		if (resourcePrimKeies.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			query.append(_FINDER_COLUMN_R_G_M_RESOURCEPRIMKEY_7);

			query.append(StringUtil.merge(resourcePrimKeies));

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_R_G_M_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_M_MAIN_2);

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(main);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param main the main
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_G_M(long[] resourcePrimKeies, long groupId,
		boolean main) {
		return findByR_G_M(resourcePrimKeies, groupId, main, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_G_M(long[] resourcePrimKeies, long groupId,
		boolean main, int start, int end) {
		return findByR_G_M(resourcePrimKeies, groupId, main, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_G_M(long[] resourcePrimKeies, long groupId,
		boolean main, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (resourcePrimKeies == null) {
			resourcePrimKeies = new long[0];
		}
		else {
			resourcePrimKeies = ArrayUtil.unique(resourcePrimKeies);
		}

		if (resourcePrimKeies.length == 1) {
			return findByR_G_M(resourcePrimKeies[0], groupId, main, start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					StringUtil.merge(resourcePrimKeies), groupId, main
				};
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(resourcePrimKeies), groupId, main,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G_M,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if (!ArrayUtil.contains(resourcePrimKeies,
							kbArticle.getResourcePrimKey()) ||
						(groupId != kbArticle.getGroupId()) ||
						(main != kbArticle.getMain())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			if (resourcePrimKeies.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_R_G_M_RESOURCEPRIMKEY_7);

				query.append(StringUtil.merge(resourcePrimKeies));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_G_M_GROUPID_2);

			query.append(_FINDER_COLUMN_R_G_M_MAIN_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(main);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G_M,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G_M,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63; from the database.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 */
	@Override
	public void removeByR_G_M(long resourcePrimKey, long groupId, boolean main) {
		for (KBArticle kbArticle : findByR_G_M(resourcePrimKey, groupId, main,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByR_G_M(long resourcePrimKey, long groupId, boolean main) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_R_G_M;

		Object[] finderArgs = new Object[] { resourcePrimKey, groupId, main };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_G_M_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_G_M_GROUPID_2);

			query.append(_FINDER_COLUMN_R_G_M_MAIN_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(groupId);

				qPos.add(main);

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

	/**
	 * Returns the number of k b articles where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param main the main
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByR_G_M(long[] resourcePrimKeies, long groupId, boolean main) {
		if (resourcePrimKeies == null) {
			resourcePrimKeies = new long[0];
		}
		else {
			resourcePrimKeies = ArrayUtil.unique(resourcePrimKeies);
		}

		Object[] finderArgs = new Object[] {
				StringUtil.merge(resourcePrimKeies), groupId, main
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_G_M,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			if (resourcePrimKeies.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_R_G_M_RESOURCEPRIMKEY_7);

				query.append(StringUtil.merge(resourcePrimKeies));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_G_M_GROUPID_2);

			query.append(_FINDER_COLUMN_R_G_M_MAIN_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(main);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_G_M,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_G_M,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param main the main
	 * @return the number of matching k b articles that the user has permission to view
	 */
	@Override
	public int filterCountByR_G_M(long resourcePrimKey, long groupId,
		boolean main) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByR_G_M(resourcePrimKey, groupId, main);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_G_M_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_M_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_M_MAIN_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);

			qPos.add(groupId);

			qPos.add(main);

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

	/**
	 * Returns the number of k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param main the main
	 * @return the number of matching k b articles that the user has permission to view
	 */
	@Override
	public int filterCountByR_G_M(long[] resourcePrimKeies, long groupId,
		boolean main) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByR_G_M(resourcePrimKeies, groupId, main);
		}

		if (resourcePrimKeies == null) {
			resourcePrimKeies = new long[0];
		}
		else {
			resourcePrimKeies = ArrayUtil.unique(resourcePrimKeies);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		if (resourcePrimKeies.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			query.append(_FINDER_COLUMN_R_G_M_RESOURCEPRIMKEY_7);

			query.append(StringUtil.merge(resourcePrimKeies));

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_R_G_M_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_M_MAIN_2);

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(main);

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

	private static final String _FINDER_COLUMN_R_G_M_RESOURCEPRIMKEY_2 = "kbArticle.resourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_R_G_M_RESOURCEPRIMKEY_7 = "kbArticle.resourcePrimKey IN (";
	private static final String _FINDER_COLUMN_R_G_M_GROUPID_2 = "kbArticle.groupId = ? AND ";
	private static final String _FINDER_COLUMN_R_G_M_MAIN_2 = "kbArticle.main = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByR_G_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByR_G_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			KBArticleModelImpl.RESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.GROUPID_COLUMN_BITMASK |
			KBArticleModelImpl.STATUS_COLUMN_BITMASK |
			KBArticleModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_R_G_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByR_G_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_G_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByR_G_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_G_S(long resourcePrimKey, long groupId,
		int status) {
		return findByR_G_S(resourcePrimKey, groupId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_G_S(long resourcePrimKey, long groupId,
		int status, int start, int end) {
		return findByR_G_S(resourcePrimKey, groupId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_G_S(long resourcePrimKey, long groupId,
		int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_S;
			finderArgs = new Object[] { resourcePrimKey, groupId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G_S;
			finderArgs = new Object[] {
					resourcePrimKey, groupId, status,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((resourcePrimKey != kbArticle.getResourcePrimKey()) ||
						(groupId != kbArticle.getGroupId()) ||
						(status != kbArticle.getStatus())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_G_S_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_G_S_GROUPID_2);

			query.append(_FINDER_COLUMN_R_G_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(groupId);

				qPos.add(status);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByR_G_S_First(long resourcePrimKey, long groupId,
		int status, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByR_G_S_First(resourcePrimKey, groupId,
				status, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByR_G_S_First(long resourcePrimKey, long groupId,
		int status, OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByR_G_S(resourcePrimKey, groupId, status, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByR_G_S_Last(long resourcePrimKey, long groupId,
		int status, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByR_G_S_Last(resourcePrimKey, groupId,
				status, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("resourcePrimKey=");
		msg.append(resourcePrimKey);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByR_G_S_Last(long resourcePrimKey, long groupId,
		int status, OrderByComparator<KBArticle> orderByComparator) {
		int count = countByR_G_S(resourcePrimKey, groupId, status);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByR_G_S(resourcePrimKey, groupId, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByR_G_S_PrevAndNext(long kbArticleId,
		long resourcePrimKey, long groupId, int status,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByR_G_S_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, status, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByR_G_S_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByR_G_S_PrevAndNext(Session session,
		KBArticle kbArticle, long resourcePrimKey, long groupId, int status,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_G_S_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_S_STATUS_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(groupId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByR_G_S(long resourcePrimKey,
		long groupId, int status) {
		return filterFindByR_G_S(resourcePrimKey, groupId, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByR_G_S(long resourcePrimKey,
		long groupId, int status, int start, int end) {
		return filterFindByR_G_S(resourcePrimKey, groupId, status, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByR_G_S(long resourcePrimKey,
		long groupId, int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByR_G_S(resourcePrimKey, groupId, status, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_R_G_S_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);

			qPos.add(groupId);

			qPos.add(status);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] filterFindByR_G_S_PrevAndNext(long kbArticleId,
		long resourcePrimKey, long groupId, int status,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByR_G_S_PrevAndNext(kbArticleId, resourcePrimKey,
				groupId, status, orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByR_G_S_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, status, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = filterGetByR_G_S_PrevAndNext(session, kbArticle,
					resourcePrimKey, groupId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle filterGetByR_G_S_PrevAndNext(Session session,
		KBArticle kbArticle, long resourcePrimKey, long groupId, int status,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_R_G_S_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(resourcePrimKey);

		qPos.add(groupId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByR_G_S(long[] resourcePrimKeies,
		long groupId, int status) {
		return filterFindByR_G_S(resourcePrimKeies, groupId, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByR_G_S(long[] resourcePrimKeies,
		long groupId, int status, int start, int end) {
		return filterFindByR_G_S(resourcePrimKeies, groupId, status, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByR_G_S(long[] resourcePrimKeies,
		long groupId, int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByR_G_S(resourcePrimKeies, groupId, status, start, end,
				orderByComparator);
		}

		if (resourcePrimKeies == null) {
			resourcePrimKeies = new long[0];
		}
		else {
			resourcePrimKeies = ArrayUtil.unique(resourcePrimKeies);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		if (resourcePrimKeies.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			query.append(_FINDER_COLUMN_R_G_S_RESOURCEPRIMKEY_7);

			query.append(StringUtil.merge(resourcePrimKeies));

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_R_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_S_STATUS_2);

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(status);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param status the status
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_G_S(long[] resourcePrimKeies, long groupId,
		int status) {
		return findByR_G_S(resourcePrimKeies, groupId, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_G_S(long[] resourcePrimKeies, long groupId,
		int status, int start, int end) {
		return findByR_G_S(resourcePrimKeies, groupId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByR_G_S(long[] resourcePrimKeies, long groupId,
		int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (resourcePrimKeies == null) {
			resourcePrimKeies = new long[0];
		}
		else {
			resourcePrimKeies = ArrayUtil.unique(resourcePrimKeies);
		}

		if (resourcePrimKeies.length == 1) {
			return findByR_G_S(resourcePrimKeies[0], groupId, status, start,
				end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					StringUtil.merge(resourcePrimKeies), groupId, status
				};
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(resourcePrimKeies), groupId, status,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G_S,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if (!ArrayUtil.contains(resourcePrimKeies,
							kbArticle.getResourcePrimKey()) ||
						(groupId != kbArticle.getGroupId()) ||
						(status != kbArticle.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			if (resourcePrimKeies.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_R_G_S_RESOURCEPRIMKEY_7);

				query.append(StringUtil.merge(resourcePrimKeies));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_G_S_GROUPID_2);

			query.append(_FINDER_COLUMN_R_G_S_STATUS_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(status);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G_S,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_R_G_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63; from the database.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 */
	@Override
	public void removeByR_G_S(long resourcePrimKey, long groupId, int status) {
		for (KBArticle kbArticle : findByR_G_S(resourcePrimKey, groupId,
				status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByR_G_S(long resourcePrimKey, long groupId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_R_G_S;

		Object[] finderArgs = new Object[] { resourcePrimKey, groupId, status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_R_G_S_RESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_R_G_S_GROUPID_2);

			query.append(_FINDER_COLUMN_R_G_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(resourcePrimKey);

				qPos.add(groupId);

				qPos.add(status);

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

	/**
	 * Returns the number of k b articles where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByR_G_S(long[] resourcePrimKeies, long groupId, int status) {
		if (resourcePrimKeies == null) {
			resourcePrimKeies = new long[0];
		}
		else {
			resourcePrimKeies = ArrayUtil.unique(resourcePrimKeies);
		}

		Object[] finderArgs = new Object[] {
				StringUtil.merge(resourcePrimKeies), groupId, status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_G_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			if (resourcePrimKeies.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_R_G_S_RESOURCEPRIMKEY_7);

				query.append(StringUtil.merge(resourcePrimKeies));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_R_G_S_GROUPID_2);

			query.append(_FINDER_COLUMN_R_G_S_STATUS_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(status);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_G_S,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_R_G_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles that the user has permission to view where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKey the resource prim key
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching k b articles that the user has permission to view
	 */
	@Override
	public int filterCountByR_G_S(long resourcePrimKey, long groupId, int status) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByR_G_S(resourcePrimKey, groupId, status);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_R_G_S_RESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_R_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_S_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourcePrimKey);

			qPos.add(groupId);

			qPos.add(status);

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

	/**
	 * Returns the number of k b articles that the user has permission to view where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	 *
	 * @param resourcePrimKeies the resource prim keies
	 * @param groupId the group ID
	 * @param status the status
	 * @return the number of matching k b articles that the user has permission to view
	 */
	@Override
	public int filterCountByR_G_S(long[] resourcePrimKeies, long groupId,
		int status) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByR_G_S(resourcePrimKeies, groupId, status);
		}

		if (resourcePrimKeies == null) {
			resourcePrimKeies = new long[0];
		}
		else {
			resourcePrimKeies = ArrayUtil.unique(resourcePrimKeies);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		if (resourcePrimKeies.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			query.append(_FINDER_COLUMN_R_G_S_RESOURCEPRIMKEY_7);

			query.append(StringUtil.merge(resourcePrimKeies));

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_R_G_S_GROUPID_2);

		query.append(_FINDER_COLUMN_R_G_S_STATUS_2);

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(status);

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

	private static final String _FINDER_COLUMN_R_G_S_RESOURCEPRIMKEY_2 = "kbArticle.resourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_R_G_S_RESOURCEPRIMKEY_7 = "kbArticle.resourcePrimKey IN (";
	private static final String _FINDER_COLUMN_R_G_S_GROUPID_2 = "kbArticle.groupId = ? AND ";
	private static final String _FINDER_COLUMN_R_G_S_STATUS_2 = "kbArticle.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_P_L",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_P_L",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			},
			KBArticleModelImpl.GROUPID_COLUMN_BITMASK |
			KBArticleModelImpl.PARENTRESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.LATEST_COLUMN_BITMASK |
			KBArticleModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_P_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_P_L",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_P_L",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_P_L(long groupId,
		long parentResourcePrimKey, boolean latest) {
		return findByG_P_L(groupId, parentResourcePrimKey, latest,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_P_L(long groupId,
		long parentResourcePrimKey, boolean latest, int start, int end) {
		return findByG_P_L(groupId, parentResourcePrimKey, latest, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_P_L(long groupId,
		long parentResourcePrimKey, boolean latest, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_L;
			finderArgs = new Object[] { groupId, parentResourcePrimKey, latest };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_L;
			finderArgs = new Object[] {
					groupId, parentResourcePrimKey, latest,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						(parentResourcePrimKey != kbArticle.getParentResourcePrimKey()) ||
						(latest != kbArticle.getLatest())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_P_L_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_G_P_L_LATEST_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				qPos.add(latest);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByG_P_L_First(long groupId,
		long parentResourcePrimKey, boolean latest,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByG_P_L_First(groupId,
				parentResourcePrimKey, latest, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", latest=");
		msg.append(latest);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByG_P_L_First(long groupId,
		long parentResourcePrimKey, boolean latest,
		OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByG_P_L(groupId, parentResourcePrimKey,
				latest, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByG_P_L_Last(long groupId, long parentResourcePrimKey,
		boolean latest, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByG_P_L_Last(groupId, parentResourcePrimKey,
				latest, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", latest=");
		msg.append(latest);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByG_P_L_Last(long groupId,
		long parentResourcePrimKey, boolean latest,
		OrderByComparator<KBArticle> orderByComparator) {
		int count = countByG_P_L(groupId, parentResourcePrimKey, latest);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByG_P_L(groupId, parentResourcePrimKey,
				latest, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByG_P_L_PrevAndNext(long kbArticleId, long groupId,
		long parentResourcePrimKey, boolean latest,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByG_P_L_PrevAndNext(session, kbArticle, groupId,
					parentResourcePrimKey, latest, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByG_P_L_PrevAndNext(session, kbArticle, groupId,
					parentResourcePrimKey, latest, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByG_P_L_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, long parentResourcePrimKey,
		boolean latest, OrderByComparator<KBArticle> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_P_L_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_L_LATEST_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentResourcePrimKey);

		qPos.add(latest);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @return the matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_P_L(long groupId,
		long parentResourcePrimKey, boolean latest) {
		return filterFindByG_P_L(groupId, parentResourcePrimKey, latest,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_P_L(long groupId,
		long parentResourcePrimKey, boolean latest, int start, int end) {
		return filterFindByG_P_L(groupId, parentResourcePrimKey, latest, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_P_L(long groupId,
		long parentResourcePrimKey, boolean latest, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_L(groupId, parentResourcePrimKey, latest, start,
				end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_L_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_L_LATEST_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			qPos.add(latest);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] filterFindByG_P_L_PrevAndNext(long kbArticleId,
		long groupId, long parentResourcePrimKey, boolean latest,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_L_PrevAndNext(kbArticleId, groupId,
				parentResourcePrimKey, latest, orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByG_P_L_PrevAndNext(session, kbArticle,
					groupId, parentResourcePrimKey, latest, orderByComparator,
					true);

			array[1] = kbArticle;

			array[2] = filterGetByG_P_L_PrevAndNext(session, kbArticle,
					groupId, parentResourcePrimKey, latest, orderByComparator,
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

	protected KBArticle filterGetByG_P_L_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, long parentResourcePrimKey,
		boolean latest, OrderByComparator<KBArticle> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_L_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_L_LATEST_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentResourcePrimKey);

		qPos.add(latest);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param latest the latest
	 * @return the matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_P_L(long groupId,
		long[] parentResourcePrimKeies, boolean latest) {
		return filterFindByG_P_L(groupId, parentResourcePrimKeies, latest,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_P_L(long groupId,
		long[] parentResourcePrimKeies, boolean latest, int start, int end) {
		return filterFindByG_P_L(groupId, parentResourcePrimKeies, latest,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_P_L(long groupId,
		long[] parentResourcePrimKeies, boolean latest, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_L(groupId, parentResourcePrimKeies, latest, start,
				end, orderByComparator);
		}

		if (parentResourcePrimKeies == null) {
			parentResourcePrimKeies = new long[0];
		}
		else {
			parentResourcePrimKeies = ArrayUtil.unique(parentResourcePrimKeies);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_L_GROUPID_2);

		if (parentResourcePrimKeies.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			query.append(_FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_7);

			query.append(StringUtil.merge(parentResourcePrimKeies));

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_L_LATEST_2);

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(latest);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param latest the latest
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_P_L(long groupId,
		long[] parentResourcePrimKeies, boolean latest) {
		return findByG_P_L(groupId, parentResourcePrimKeies, latest,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_P_L(long groupId,
		long[] parentResourcePrimKeies, boolean latest, int start, int end) {
		return findByG_P_L(groupId, parentResourcePrimKeies, latest, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_P_L(long groupId,
		long[] parentResourcePrimKeies, boolean latest, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (parentResourcePrimKeies == null) {
			parentResourcePrimKeies = new long[0];
		}
		else {
			parentResourcePrimKeies = ArrayUtil.unique(parentResourcePrimKeies);
		}

		if (parentResourcePrimKeies.length == 1) {
			return findByG_P_L(groupId, parentResourcePrimKeies[0], latest,
				start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					groupId, StringUtil.merge(parentResourcePrimKeies), latest
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, StringUtil.merge(parentResourcePrimKeies), latest,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_L,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						!ArrayUtil.contains(parentResourcePrimKeies,
							kbArticle.getParentResourcePrimKey()) ||
						(latest != kbArticle.getLatest())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_P_L_GROUPID_2);

			if (parentResourcePrimKeies.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_7);

				query.append(StringUtil.merge(parentResourcePrimKeies));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_L_LATEST_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(latest);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_L,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_L,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 */
	@Override
	public void removeByG_P_L(long groupId, long parentResourcePrimKey,
		boolean latest) {
		for (KBArticle kbArticle : findByG_P_L(groupId, parentResourcePrimKey,
				latest, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByG_P_L(long groupId, long parentResourcePrimKey,
		boolean latest) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_P_L;

		Object[] finderArgs = new Object[] {
				groupId, parentResourcePrimKey, latest
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_P_L_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_G_P_L_LATEST_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				qPos.add(latest);

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

	/**
	 * Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param latest the latest
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByG_P_L(long groupId, long[] parentResourcePrimKeies,
		boolean latest) {
		if (parentResourcePrimKeies == null) {
			parentResourcePrimKeies = new long[0];
		}
		else {
			parentResourcePrimKeies = ArrayUtil.unique(parentResourcePrimKeies);
		}

		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(parentResourcePrimKeies), latest
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_P_L_GROUPID_2);

			if (parentResourcePrimKeies.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_7);

				query.append(StringUtil.merge(parentResourcePrimKeies));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_L_LATEST_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(latest);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_L,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_L,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param latest the latest
	 * @return the number of matching k b articles that the user has permission to view
	 */
	@Override
	public int filterCountByG_P_L(long groupId, long parentResourcePrimKey,
		boolean latest) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P_L(groupId, parentResourcePrimKey, latest);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_P_L_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_L_LATEST_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			qPos.add(latest);

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

	/**
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param latest the latest
	 * @return the number of matching k b articles that the user has permission to view
	 */
	@Override
	public int filterCountByG_P_L(long groupId, long[] parentResourcePrimKeies,
		boolean latest) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P_L(groupId, parentResourcePrimKeies, latest);
		}

		if (parentResourcePrimKeies == null) {
			parentResourcePrimKeies = new long[0];
		}
		else {
			parentResourcePrimKeies = ArrayUtil.unique(parentResourcePrimKeies);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_P_L_GROUPID_2);

		if (parentResourcePrimKeies.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			query.append(_FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_7);

			query.append(StringUtil.merge(parentResourcePrimKeies));

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_L_LATEST_2);

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(latest);

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

	private static final String _FINDER_COLUMN_G_P_L_GROUPID_2 = "kbArticle.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_2 = "kbArticle.parentResourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_G_P_L_PARENTRESOURCEPRIMKEY_7 = "kbArticle.parentResourcePrimKey IN (";
	private static final String _FINDER_COLUMN_G_P_L_LATEST_2 = "kbArticle.latest = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_P_M",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_P_M",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			},
			KBArticleModelImpl.GROUPID_COLUMN_BITMASK |
			KBArticleModelImpl.PARENTRESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.MAIN_COLUMN_BITMASK |
			KBArticleModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_P_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_P_M",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_P_M",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_P_M(long groupId,
		long parentResourcePrimKey, boolean main) {
		return findByG_P_M(groupId, parentResourcePrimKey, main,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_P_M(long groupId,
		long parentResourcePrimKey, boolean main, int start, int end) {
		return findByG_P_M(groupId, parentResourcePrimKey, main, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_P_M(long groupId,
		long parentResourcePrimKey, boolean main, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_M;
			finderArgs = new Object[] { groupId, parentResourcePrimKey, main };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_M;
			finderArgs = new Object[] {
					groupId, parentResourcePrimKey, main,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						(parentResourcePrimKey != kbArticle.getParentResourcePrimKey()) ||
						(main != kbArticle.getMain())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_P_M_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_M_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_G_P_M_MAIN_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				qPos.add(main);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByG_P_M_First(long groupId,
		long parentResourcePrimKey, boolean main,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByG_P_M_First(groupId,
				parentResourcePrimKey, main, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByG_P_M_First(long groupId,
		long parentResourcePrimKey, boolean main,
		OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByG_P_M(groupId, parentResourcePrimKey,
				main, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByG_P_M_Last(long groupId, long parentResourcePrimKey,
		boolean main, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByG_P_M_Last(groupId, parentResourcePrimKey,
				main, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByG_P_M_Last(long groupId,
		long parentResourcePrimKey, boolean main,
		OrderByComparator<KBArticle> orderByComparator) {
		int count = countByG_P_M(groupId, parentResourcePrimKey, main);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByG_P_M(groupId, parentResourcePrimKey,
				main, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByG_P_M_PrevAndNext(long kbArticleId, long groupId,
		long parentResourcePrimKey, boolean main,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByG_P_M_PrevAndNext(session, kbArticle, groupId,
					parentResourcePrimKey, main, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByG_P_M_PrevAndNext(session, kbArticle, groupId,
					parentResourcePrimKey, main, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByG_P_M_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, long parentResourcePrimKey,
		boolean main, OrderByComparator<KBArticle> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_P_M_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_M_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_M_MAIN_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentResourcePrimKey);

		qPos.add(main);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @return the matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_P_M(long groupId,
		long parentResourcePrimKey, boolean main) {
		return filterFindByG_P_M(groupId, parentResourcePrimKey, main,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_P_M(long groupId,
		long parentResourcePrimKey, boolean main, int start, int end) {
		return filterFindByG_P_M(groupId, parentResourcePrimKey, main, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_P_M(long groupId,
		long parentResourcePrimKey, boolean main, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_M(groupId, parentResourcePrimKey, main, start,
				end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_M_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_M_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_M_MAIN_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			qPos.add(main);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] filterFindByG_P_M_PrevAndNext(long kbArticleId,
		long groupId, long parentResourcePrimKey, boolean main,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_M_PrevAndNext(kbArticleId, groupId,
				parentResourcePrimKey, main, orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByG_P_M_PrevAndNext(session, kbArticle,
					groupId, parentResourcePrimKey, main, orderByComparator,
					true);

			array[1] = kbArticle;

			array[2] = filterGetByG_P_M_PrevAndNext(session, kbArticle,
					groupId, parentResourcePrimKey, main, orderByComparator,
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

	protected KBArticle filterGetByG_P_M_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, long parentResourcePrimKey,
		boolean main, OrderByComparator<KBArticle> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_M_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_M_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_M_MAIN_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentResourcePrimKey);

		qPos.add(main);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param main the main
	 * @return the matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_P_M(long groupId,
		long[] parentResourcePrimKeies, boolean main) {
		return filterFindByG_P_M(groupId, parentResourcePrimKeies, main,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_P_M(long groupId,
		long[] parentResourcePrimKeies, boolean main, int start, int end) {
		return filterFindByG_P_M(groupId, parentResourcePrimKeies, main, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_P_M(long groupId,
		long[] parentResourcePrimKeies, boolean main, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_M(groupId, parentResourcePrimKeies, main, start,
				end, orderByComparator);
		}

		if (parentResourcePrimKeies == null) {
			parentResourcePrimKeies = new long[0];
		}
		else {
			parentResourcePrimKeies = ArrayUtil.unique(parentResourcePrimKeies);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_M_GROUPID_2);

		if (parentResourcePrimKeies.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			query.append(_FINDER_COLUMN_G_P_M_PARENTRESOURCEPRIMKEY_7);

			query.append(StringUtil.merge(parentResourcePrimKeies));

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_M_MAIN_2);

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(main);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param main the main
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_P_M(long groupId,
		long[] parentResourcePrimKeies, boolean main) {
		return findByG_P_M(groupId, parentResourcePrimKeies, main,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_P_M(long groupId,
		long[] parentResourcePrimKeies, boolean main, int start, int end) {
		return findByG_P_M(groupId, parentResourcePrimKeies, main, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_P_M(long groupId,
		long[] parentResourcePrimKeies, boolean main, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (parentResourcePrimKeies == null) {
			parentResourcePrimKeies = new long[0];
		}
		else {
			parentResourcePrimKeies = ArrayUtil.unique(parentResourcePrimKeies);
		}

		if (parentResourcePrimKeies.length == 1) {
			return findByG_P_M(groupId, parentResourcePrimKeies[0], main,
				start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					groupId, StringUtil.merge(parentResourcePrimKeies), main
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, StringUtil.merge(parentResourcePrimKeies), main,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_M,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						!ArrayUtil.contains(parentResourcePrimKeies,
							kbArticle.getParentResourcePrimKey()) ||
						(main != kbArticle.getMain())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_P_M_GROUPID_2);

			if (parentResourcePrimKeies.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_G_P_M_PARENTRESOURCEPRIMKEY_7);

				query.append(StringUtil.merge(parentResourcePrimKeies));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_M_MAIN_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(main);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_M,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_M,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 */
	@Override
	public void removeByG_P_M(long groupId, long parentResourcePrimKey,
		boolean main) {
		for (KBArticle kbArticle : findByG_P_M(groupId, parentResourcePrimKey,
				main, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByG_P_M(long groupId, long parentResourcePrimKey,
		boolean main) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_P_M;

		Object[] finderArgs = new Object[] { groupId, parentResourcePrimKey, main };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_P_M_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_M_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_G_P_M_MAIN_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				qPos.add(main);

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

	/**
	 * Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param main the main
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByG_P_M(long groupId, long[] parentResourcePrimKeies,
		boolean main) {
		if (parentResourcePrimKeies == null) {
			parentResourcePrimKeies = new long[0];
		}
		else {
			parentResourcePrimKeies = ArrayUtil.unique(parentResourcePrimKeies);
		}

		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(parentResourcePrimKeies), main
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_M,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_P_M_GROUPID_2);

			if (parentResourcePrimKeies.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_G_P_M_PARENTRESOURCEPRIMKEY_7);

				query.append(StringUtil.merge(parentResourcePrimKeies));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_M_MAIN_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(main);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_M,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_M,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param main the main
	 * @return the number of matching k b articles that the user has permission to view
	 */
	@Override
	public int filterCountByG_P_M(long groupId, long parentResourcePrimKey,
		boolean main) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P_M(groupId, parentResourcePrimKey, main);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_P_M_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_M_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_M_MAIN_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			qPos.add(main);

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

	/**
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param main the main
	 * @return the number of matching k b articles that the user has permission to view
	 */
	@Override
	public int filterCountByG_P_M(long groupId, long[] parentResourcePrimKeies,
		boolean main) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P_M(groupId, parentResourcePrimKeies, main);
		}

		if (parentResourcePrimKeies == null) {
			parentResourcePrimKeies = new long[0];
		}
		else {
			parentResourcePrimKeies = ArrayUtil.unique(parentResourcePrimKeies);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_P_M_GROUPID_2);

		if (parentResourcePrimKeies.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			query.append(_FINDER_COLUMN_G_P_M_PARENTRESOURCEPRIMKEY_7);

			query.append(StringUtil.merge(parentResourcePrimKeies));

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_M_MAIN_2);

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(main);

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

	private static final String _FINDER_COLUMN_G_P_M_GROUPID_2 = "kbArticle.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_M_PARENTRESOURCEPRIMKEY_2 = "kbArticle.parentResourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_G_P_M_PARENTRESOURCEPRIMKEY_7 = "kbArticle.parentResourcePrimKey IN (";
	private static final String _FINDER_COLUMN_G_P_M_MAIN_2 = "kbArticle.main = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_P_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_P_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			KBArticleModelImpl.GROUPID_COLUMN_BITMASK |
			KBArticleModelImpl.PARENTRESOURCEPRIMKEY_COLUMN_BITMASK |
			KBArticleModelImpl.STATUS_COLUMN_BITMASK |
			KBArticleModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_P_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_P_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_P_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_P_S(long groupId,
		long parentResourcePrimKey, int status) {
		return findByG_P_S(groupId, parentResourcePrimKey, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_P_S(long groupId,
		long parentResourcePrimKey, int status, int start, int end) {
		return findByG_P_S(groupId, parentResourcePrimKey, status, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_P_S(long groupId,
		long parentResourcePrimKey, int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_S;
			finderArgs = new Object[] { groupId, parentResourcePrimKey, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_S;
			finderArgs = new Object[] {
					groupId, parentResourcePrimKey, status,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						(parentResourcePrimKey != kbArticle.getParentResourcePrimKey()) ||
						(status != kbArticle.getStatus())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_P_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_S_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_G_P_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				qPos.add(status);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByG_P_S_First(long groupId,
		long parentResourcePrimKey, int status,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByG_P_S_First(groupId,
				parentResourcePrimKey, status, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByG_P_S_First(long groupId,
		long parentResourcePrimKey, int status,
		OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByG_P_S(groupId, parentResourcePrimKey,
				status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByG_P_S_Last(long groupId, long parentResourcePrimKey,
		int status, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByG_P_S_Last(groupId, parentResourcePrimKey,
				status, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentResourcePrimKey=");
		msg.append(parentResourcePrimKey);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByG_P_S_Last(long groupId,
		long parentResourcePrimKey, int status,
		OrderByComparator<KBArticle> orderByComparator) {
		int count = countByG_P_S(groupId, parentResourcePrimKey, status);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByG_P_S(groupId, parentResourcePrimKey,
				status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByG_P_S_PrevAndNext(long kbArticleId, long groupId,
		long parentResourcePrimKey, int status,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByG_P_S_PrevAndNext(session, kbArticle, groupId,
					parentResourcePrimKey, status, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByG_P_S_PrevAndNext(session, kbArticle, groupId,
					parentResourcePrimKey, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByG_P_S_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, long parentResourcePrimKey,
		int status, OrderByComparator<KBArticle> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_P_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_S_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_S_STATUS_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentResourcePrimKey);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @return the matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_P_S(long groupId,
		long parentResourcePrimKey, int status) {
		return filterFindByG_P_S(groupId, parentResourcePrimKey, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_P_S(long groupId,
		long parentResourcePrimKey, int status, int start, int end) {
		return filterFindByG_P_S(groupId, parentResourcePrimKey, status, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_P_S(long groupId,
		long parentResourcePrimKey, int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_S(groupId, parentResourcePrimKey, status, start,
				end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_S_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			qPos.add(status);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] filterFindByG_P_S_PrevAndNext(long kbArticleId,
		long groupId, long parentResourcePrimKey, int status,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_S_PrevAndNext(kbArticleId, groupId,
				parentResourcePrimKey, status, orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByG_P_S_PrevAndNext(session, kbArticle,
					groupId, parentResourcePrimKey, status, orderByComparator,
					true);

			array[1] = kbArticle;

			array[2] = filterGetByG_P_S_PrevAndNext(session, kbArticle,
					groupId, parentResourcePrimKey, status, orderByComparator,
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

	protected KBArticle filterGetByG_P_S_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, long parentResourcePrimKey,
		int status, OrderByComparator<KBArticle> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_S_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentResourcePrimKey);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param status the status
	 * @return the matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_P_S(long groupId,
		long[] parentResourcePrimKeies, int status) {
		return filterFindByG_P_S(groupId, parentResourcePrimKeies, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_P_S(long groupId,
		long[] parentResourcePrimKeies, int status, int start, int end) {
		return filterFindByG_P_S(groupId, parentResourcePrimKeies, status,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_P_S(long groupId,
		long[] parentResourcePrimKeies, int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_S(groupId, parentResourcePrimKeies, status, start,
				end, orderByComparator);
		}

		if (parentResourcePrimKeies == null) {
			parentResourcePrimKeies = new long[0];
		}
		else {
			parentResourcePrimKeies = ArrayUtil.unique(parentResourcePrimKeies);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_S_GROUPID_2);

		if (parentResourcePrimKeies.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			query.append(_FINDER_COLUMN_G_P_S_PARENTRESOURCEPRIMKEY_7);

			query.append(StringUtil.merge(parentResourcePrimKeies));

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_S_STATUS_2);

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(status);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param status the status
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_P_S(long groupId,
		long[] parentResourcePrimKeies, int status) {
		return findByG_P_S(groupId, parentResourcePrimKeies, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_P_S(long groupId,
		long[] parentResourcePrimKeies, int status, int start, int end) {
		return findByG_P_S(groupId, parentResourcePrimKeies, status, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_P_S(long groupId,
		long[] parentResourcePrimKeies, int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (parentResourcePrimKeies == null) {
			parentResourcePrimKeies = new long[0];
		}
		else {
			parentResourcePrimKeies = ArrayUtil.unique(parentResourcePrimKeies);
		}

		if (parentResourcePrimKeies.length == 1) {
			return findByG_P_S(groupId, parentResourcePrimKeies[0], status,
				start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					groupId, StringUtil.merge(parentResourcePrimKeies), status
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, StringUtil.merge(parentResourcePrimKeies), status,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_S,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						!ArrayUtil.contains(parentResourcePrimKeies,
							kbArticle.getParentResourcePrimKey()) ||
						(status != kbArticle.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_P_S_GROUPID_2);

			if (parentResourcePrimKeies.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_G_P_S_PARENTRESOURCEPRIMKEY_7);

				query.append(StringUtil.merge(parentResourcePrimKeies));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_S_STATUS_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(status);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_S,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 */
	@Override
	public void removeByG_P_S(long groupId, long parentResourcePrimKey,
		int status) {
		for (KBArticle kbArticle : findByG_P_S(groupId, parentResourcePrimKey,
				status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByG_P_S(long groupId, long parentResourcePrimKey, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_P_S;

		Object[] finderArgs = new Object[] {
				groupId, parentResourcePrimKey, status
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_P_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_S_PARENTRESOURCEPRIMKEY_2);

			query.append(_FINDER_COLUMN_G_P_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentResourcePrimKey);

				qPos.add(status);

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

	/**
	 * Returns the number of k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param status the status
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByG_P_S(long groupId, long[] parentResourcePrimKeies,
		int status) {
		if (parentResourcePrimKeies == null) {
			parentResourcePrimKeies = new long[0];
		}
		else {
			parentResourcePrimKeies = ArrayUtil.unique(parentResourcePrimKeies);
		}

		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(parentResourcePrimKeies), status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_P_S_GROUPID_2);

			if (parentResourcePrimKeies.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_G_P_S_PARENTRESOURCEPRIMKEY_7);

				query.append(StringUtil.merge(parentResourcePrimKeies));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_P_S_STATUS_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(status);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_S,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_P_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKey the parent resource prim key
	 * @param status the status
	 * @return the number of matching k b articles that the user has permission to view
	 */
	@Override
	public int filterCountByG_P_S(long groupId, long parentResourcePrimKey,
		int status) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P_S(groupId, parentResourcePrimKey, status);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_P_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_S_PARENTRESOURCEPRIMKEY_2);

		query.append(_FINDER_COLUMN_G_P_S_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentResourcePrimKey);

			qPos.add(status);

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

	/**
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentResourcePrimKeies the parent resource prim keies
	 * @param status the status
	 * @return the number of matching k b articles that the user has permission to view
	 */
	@Override
	public int filterCountByG_P_S(long groupId, long[] parentResourcePrimKeies,
		int status) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P_S(groupId, parentResourcePrimKeies, status);
		}

		if (parentResourcePrimKeies == null) {
			parentResourcePrimKeies = new long[0];
		}
		else {
			parentResourcePrimKeies = ArrayUtil.unique(parentResourcePrimKeies);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_P_S_GROUPID_2);

		if (parentResourcePrimKeies.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			query.append(_FINDER_COLUMN_G_P_S_PARENTRESOURCEPRIMKEY_7);

			query.append(StringUtil.merge(parentResourcePrimKeies));

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_P_S_STATUS_2);

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(status);

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

	private static final String _FINDER_COLUMN_G_P_S_GROUPID_2 = "kbArticle.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_S_PARENTRESOURCEPRIMKEY_2 = "kbArticle.parentResourcePrimKey = ? AND ";
	private static final String _FINDER_COLUMN_G_P_S_PARENTRESOURCEPRIMKEY_7 = "kbArticle.parentResourcePrimKey IN (";
	private static final String _FINDER_COLUMN_G_P_S_STATUS_2 = "kbArticle.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_KBFI_UT =
		new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_KBFI_UT",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_KBFI_UT =
		new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_KBFI_UT",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			KBArticleModelImpl.GROUPID_COLUMN_BITMASK |
			KBArticleModelImpl.KBFOLDERID_COLUMN_BITMASK |
			KBArticleModelImpl.URLTITLE_COLUMN_BITMASK |
			KBArticleModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_KBFI_UT = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_KBFI_UT",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_KBFI_UT(long groupId, long kbFolderId,
		String urlTitle) {
		return findByG_KBFI_UT(groupId, kbFolderId, urlTitle,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_KBFI_UT(long groupId, long kbFolderId,
		String urlTitle, int start, int end) {
		return findByG_KBFI_UT(groupId, kbFolderId, urlTitle, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_KBFI_UT(long groupId, long kbFolderId,
		String urlTitle, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_KBFI_UT;
			finderArgs = new Object[] { groupId, kbFolderId, urlTitle };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_KBFI_UT;
			finderArgs = new Object[] {
					groupId, kbFolderId, urlTitle,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						(kbFolderId != kbArticle.getKbFolderId()) ||
						!Validator.equals(urlTitle, kbArticle.getUrlTitle())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_KBFI_UT_GROUPID_2);

			query.append(_FINDER_COLUMN_G_KBFI_UT_KBFOLDERID_2);

			boolean bindUrlTitle = false;

			if (urlTitle == null) {
				query.append(_FINDER_COLUMN_G_KBFI_UT_URLTITLE_1);
			}
			else if (urlTitle.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_KBFI_UT_URLTITLE_3);
			}
			else {
				bindUrlTitle = true;

				query.append(_FINDER_COLUMN_G_KBFI_UT_URLTITLE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(kbFolderId);

				if (bindUrlTitle) {
					qPos.add(urlTitle);
				}

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByG_KBFI_UT_First(long groupId, long kbFolderId,
		String urlTitle, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByG_KBFI_UT_First(groupId, kbFolderId,
				urlTitle, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", kbFolderId=");
		msg.append(kbFolderId);

		msg.append(", urlTitle=");
		msg.append(urlTitle);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByG_KBFI_UT_First(long groupId, long kbFolderId,
		String urlTitle, OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByG_KBFI_UT(groupId, kbFolderId, urlTitle,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByG_KBFI_UT_Last(long groupId, long kbFolderId,
		String urlTitle, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByG_KBFI_UT_Last(groupId, kbFolderId,
				urlTitle, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", kbFolderId=");
		msg.append(kbFolderId);

		msg.append(", urlTitle=");
		msg.append(urlTitle);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByG_KBFI_UT_Last(long groupId, long kbFolderId,
		String urlTitle, OrderByComparator<KBArticle> orderByComparator) {
		int count = countByG_KBFI_UT(groupId, kbFolderId, urlTitle);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByG_KBFI_UT(groupId, kbFolderId, urlTitle,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByG_KBFI_UT_PrevAndNext(long kbArticleId,
		long groupId, long kbFolderId, String urlTitle,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByG_KBFI_UT_PrevAndNext(session, kbArticle, groupId,
					kbFolderId, urlTitle, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByG_KBFI_UT_PrevAndNext(session, kbArticle, groupId,
					kbFolderId, urlTitle, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByG_KBFI_UT_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, long kbFolderId, String urlTitle,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_KBFI_UT_GROUPID_2);

		query.append(_FINDER_COLUMN_G_KBFI_UT_KBFOLDERID_2);

		boolean bindUrlTitle = false;

		if (urlTitle == null) {
			query.append(_FINDER_COLUMN_G_KBFI_UT_URLTITLE_1);
		}
		else if (urlTitle.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_KBFI_UT_URLTITLE_3);
		}
		else {
			bindUrlTitle = true;

			query.append(_FINDER_COLUMN_G_KBFI_UT_URLTITLE_2);
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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(kbFolderId);

		if (bindUrlTitle) {
			qPos.add(urlTitle);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @return the matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_KBFI_UT(long groupId, long kbFolderId,
		String urlTitle) {
		return filterFindByG_KBFI_UT(groupId, kbFolderId, urlTitle,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_KBFI_UT(long groupId, long kbFolderId,
		String urlTitle, int start, int end) {
		return filterFindByG_KBFI_UT(groupId, kbFolderId, urlTitle, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_KBFI_UT(long groupId, long kbFolderId,
		String urlTitle, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_KBFI_UT(groupId, kbFolderId, urlTitle, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_KBFI_UT_GROUPID_2);

		query.append(_FINDER_COLUMN_G_KBFI_UT_KBFOLDERID_2);

		boolean bindUrlTitle = false;

		if (urlTitle == null) {
			query.append(_FINDER_COLUMN_G_KBFI_UT_URLTITLE_1);
		}
		else if (urlTitle.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_KBFI_UT_URLTITLE_3);
		}
		else {
			bindUrlTitle = true;

			query.append(_FINDER_COLUMN_G_KBFI_UT_URLTITLE_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(kbFolderId);

			if (bindUrlTitle) {
				qPos.add(urlTitle);
			}

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] filterFindByG_KBFI_UT_PrevAndNext(long kbArticleId,
		long groupId, long kbFolderId, String urlTitle,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_KBFI_UT_PrevAndNext(kbArticleId, groupId,
				kbFolderId, urlTitle, orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByG_KBFI_UT_PrevAndNext(session, kbArticle,
					groupId, kbFolderId, urlTitle, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = filterGetByG_KBFI_UT_PrevAndNext(session, kbArticle,
					groupId, kbFolderId, urlTitle, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle filterGetByG_KBFI_UT_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, long kbFolderId, String urlTitle,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_KBFI_UT_GROUPID_2);

		query.append(_FINDER_COLUMN_G_KBFI_UT_KBFOLDERID_2);

		boolean bindUrlTitle = false;

		if (urlTitle == null) {
			query.append(_FINDER_COLUMN_G_KBFI_UT_URLTITLE_1);
		}
		else if (urlTitle.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_KBFI_UT_URLTITLE_3);
		}
		else {
			bindUrlTitle = true;

			query.append(_FINDER_COLUMN_G_KBFI_UT_URLTITLE_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(kbFolderId);

		if (bindUrlTitle) {
			qPos.add(urlTitle);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 */
	@Override
	public void removeByG_KBFI_UT(long groupId, long kbFolderId, String urlTitle) {
		for (KBArticle kbArticle : findByG_KBFI_UT(groupId, kbFolderId,
				urlTitle, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByG_KBFI_UT(long groupId, long kbFolderId, String urlTitle) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_KBFI_UT;

		Object[] finderArgs = new Object[] { groupId, kbFolderId, urlTitle };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_KBFI_UT_GROUPID_2);

			query.append(_FINDER_COLUMN_G_KBFI_UT_KBFOLDERID_2);

			boolean bindUrlTitle = false;

			if (urlTitle == null) {
				query.append(_FINDER_COLUMN_G_KBFI_UT_URLTITLE_1);
			}
			else if (urlTitle.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_KBFI_UT_URLTITLE_3);
			}
			else {
				bindUrlTitle = true;

				query.append(_FINDER_COLUMN_G_KBFI_UT_URLTITLE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(kbFolderId);

				if (bindUrlTitle) {
					qPos.add(urlTitle);
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

	/**
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @return the number of matching k b articles that the user has permission to view
	 */
	@Override
	public int filterCountByG_KBFI_UT(long groupId, long kbFolderId,
		String urlTitle) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_KBFI_UT(groupId, kbFolderId, urlTitle);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_KBFI_UT_GROUPID_2);

		query.append(_FINDER_COLUMN_G_KBFI_UT_KBFOLDERID_2);

		boolean bindUrlTitle = false;

		if (urlTitle == null) {
			query.append(_FINDER_COLUMN_G_KBFI_UT_URLTITLE_1);
		}
		else if (urlTitle.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_KBFI_UT_URLTITLE_3);
		}
		else {
			bindUrlTitle = true;

			query.append(_FINDER_COLUMN_G_KBFI_UT_URLTITLE_2);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(kbFolderId);

			if (bindUrlTitle) {
				qPos.add(urlTitle);
			}

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

	private static final String _FINDER_COLUMN_G_KBFI_UT_GROUPID_2 = "kbArticle.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_KBFI_UT_KBFOLDERID_2 = "kbArticle.kbFolderId = ? AND ";
	private static final String _FINDER_COLUMN_G_KBFI_UT_URLTITLE_1 = "kbArticle.urlTitle IS NULL";
	private static final String _FINDER_COLUMN_G_KBFI_UT_URLTITLE_2 = "kbArticle.urlTitle = ?";
	private static final String _FINDER_COLUMN_G_KBFI_UT_URLTITLE_3 = "(kbArticle.urlTitle IS NULL OR kbArticle.urlTitle = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_KBFI_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_KBFI_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_KBFI_S =
		new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_KBFI_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			KBArticleModelImpl.GROUPID_COLUMN_BITMASK |
			KBArticleModelImpl.KBFOLDERID_COLUMN_BITMASK |
			KBArticleModelImpl.STATUS_COLUMN_BITMASK |
			KBArticleModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_KBFI_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_KBFI_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the k b articles where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param status the status
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_KBFI_S(long groupId, long kbFolderId,
		int status) {
		return findByG_KBFI_S(groupId, kbFolderId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_KBFI_S(long groupId, long kbFolderId,
		int status, int start, int end) {
		return findByG_KBFI_S(groupId, kbFolderId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_KBFI_S(long groupId, long kbFolderId,
		int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_KBFI_S;
			finderArgs = new Object[] { groupId, kbFolderId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_KBFI_S;
			finderArgs = new Object[] {
					groupId, kbFolderId, status,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						(kbFolderId != kbArticle.getKbFolderId()) ||
						(status != kbArticle.getStatus())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_KBFI_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_KBFI_S_KBFOLDERID_2);

			query.append(_FINDER_COLUMN_G_KBFI_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(kbFolderId);

				qPos.add(status);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByG_KBFI_S_First(long groupId, long kbFolderId,
		int status, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByG_KBFI_S_First(groupId, kbFolderId,
				status, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", kbFolderId=");
		msg.append(kbFolderId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByG_KBFI_S_First(long groupId, long kbFolderId,
		int status, OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByG_KBFI_S(groupId, kbFolderId, status, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByG_KBFI_S_Last(long groupId, long kbFolderId,
		int status, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByG_KBFI_S_Last(groupId, kbFolderId, status,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", kbFolderId=");
		msg.append(kbFolderId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByG_KBFI_S_Last(long groupId, long kbFolderId,
		int status, OrderByComparator<KBArticle> orderByComparator) {
		int count = countByG_KBFI_S(groupId, kbFolderId, status);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByG_KBFI_S(groupId, kbFolderId, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByG_KBFI_S_PrevAndNext(long kbArticleId,
		long groupId, long kbFolderId, int status,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByG_KBFI_S_PrevAndNext(session, kbArticle, groupId,
					kbFolderId, status, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByG_KBFI_S_PrevAndNext(session, kbArticle, groupId,
					kbFolderId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByG_KBFI_S_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, long kbFolderId, int status,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_KBFI_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_KBFI_S_KBFOLDERID_2);

		query.append(_FINDER_COLUMN_G_KBFI_S_STATUS_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(kbFolderId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param status the status
	 * @return the matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_KBFI_S(long groupId, long kbFolderId,
		int status) {
		return filterFindByG_KBFI_S(groupId, kbFolderId, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_KBFI_S(long groupId, long kbFolderId,
		int status, int start, int end) {
		return filterFindByG_KBFI_S(groupId, kbFolderId, status, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_KBFI_S(long groupId, long kbFolderId,
		int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_KBFI_S(groupId, kbFolderId, status, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_KBFI_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_KBFI_S_KBFOLDERID_2);

		query.append(_FINDER_COLUMN_G_KBFI_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(kbFolderId);

			qPos.add(status);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] filterFindByG_KBFI_S_PrevAndNext(long kbArticleId,
		long groupId, long kbFolderId, int status,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_KBFI_S_PrevAndNext(kbArticleId, groupId, kbFolderId,
				status, orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByG_KBFI_S_PrevAndNext(session, kbArticle,
					groupId, kbFolderId, status, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = filterGetByG_KBFI_S_PrevAndNext(session, kbArticle,
					groupId, kbFolderId, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle filterGetByG_KBFI_S_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, long kbFolderId, int status,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_KBFI_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_KBFI_S_KBFOLDERID_2);

		query.append(_FINDER_COLUMN_G_KBFI_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(kbFolderId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the k b articles where groupId = &#63; and kbFolderId = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param status the status
	 */
	@Override
	public void removeByG_KBFI_S(long groupId, long kbFolderId, int status) {
		for (KBArticle kbArticle : findByG_KBFI_S(groupId, kbFolderId, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param status the status
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByG_KBFI_S(long groupId, long kbFolderId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_KBFI_S;

		Object[] finderArgs = new Object[] { groupId, kbFolderId, status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_KBFI_S_GROUPID_2);

			query.append(_FINDER_COLUMN_G_KBFI_S_KBFOLDERID_2);

			query.append(_FINDER_COLUMN_G_KBFI_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(kbFolderId);

				qPos.add(status);

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

	/**
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param status the status
	 * @return the number of matching k b articles that the user has permission to view
	 */
	@Override
	public int filterCountByG_KBFI_S(long groupId, long kbFolderId, int status) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_KBFI_S(groupId, kbFolderId, status);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_KBFI_S_GROUPID_2);

		query.append(_FINDER_COLUMN_G_KBFI_S_KBFOLDERID_2);

		query.append(_FINDER_COLUMN_G_KBFI_S_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(kbFolderId);

			qPos.add(status);

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

	private static final String _FINDER_COLUMN_G_KBFI_S_GROUPID_2 = "kbArticle.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_KBFI_S_KBFOLDERID_2 = "kbArticle.kbFolderId = ? AND ";
	private static final String _FINDER_COLUMN_G_KBFI_S_STATUS_2 = "kbArticle.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_S_L",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_S_L = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_S_L",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the k b articles where groupId = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param latest the latest
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_S_L(long groupId, String sections,
		boolean latest) {
		return findByG_S_L(groupId, sections, latest, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_S_L(long groupId, String sections,
		boolean latest, int start, int end) {
		return findByG_S_L(groupId, sections, latest, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_S_L(long groupId, String sections,
		boolean latest, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S_L;
		finderArgs = new Object[] {
				groupId, sections, latest,
				
				start, end, orderByComparator
			};

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						!StringUtil.wildcardMatches(kbArticle.getSections(),
							sections, CharPool.UNDERLINE, CharPool.PERCENT,
							CharPool.BACK_SLASH, true) ||
						(latest != kbArticle.getLatest())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_S_L_GROUPID_2);

			boolean bindSections = false;

			if (sections == null) {
				query.append(_FINDER_COLUMN_G_S_L_SECTIONS_1);
			}
			else if (sections.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_S_L_SECTIONS_3);
			}
			else {
				bindSections = true;

				query.append(_FINDER_COLUMN_G_S_L_SECTIONS_2);
			}

			query.append(_FINDER_COLUMN_G_S_L_LATEST_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindSections) {
					qPos.add(sections);
				}

				qPos.add(latest);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where groupId = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByG_S_L_First(long groupId, String sections,
		boolean latest, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByG_S_L_First(groupId, sections, latest,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", sections=");
		msg.append(sections);

		msg.append(", latest=");
		msg.append(latest);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where groupId = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByG_S_L_First(long groupId, String sections,
		boolean latest, OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByG_S_L(groupId, sections, latest, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByG_S_L_Last(long groupId, String sections,
		boolean latest, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByG_S_L_Last(groupId, sections, latest,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", sections=");
		msg.append(sections);

		msg.append(", latest=");
		msg.append(latest);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByG_S_L_Last(long groupId, String sections,
		boolean latest, OrderByComparator<KBArticle> orderByComparator) {
		int count = countByG_S_L(groupId, sections, latest);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByG_S_L(groupId, sections, latest,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByG_S_L_PrevAndNext(long kbArticleId, long groupId,
		String sections, boolean latest,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByG_S_L_PrevAndNext(session, kbArticle, groupId,
					sections, latest, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByG_S_L_PrevAndNext(session, kbArticle, groupId,
					sections, latest, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByG_S_L_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, String sections, boolean latest,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_S_L_GROUPID_2);

		boolean bindSections = false;

		if (sections == null) {
			query.append(_FINDER_COLUMN_G_S_L_SECTIONS_1);
		}
		else if (sections.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_S_L_SECTIONS_3);
		}
		else {
			bindSections = true;

			query.append(_FINDER_COLUMN_G_S_L_SECTIONS_2);
		}

		query.append(_FINDER_COLUMN_G_S_L_LATEST_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindSections) {
			qPos.add(sections);
		}

		qPos.add(latest);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param latest the latest
	 * @return the matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_S_L(long groupId, String sections,
		boolean latest) {
		return filterFindByG_S_L(groupId, sections, latest, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_S_L(long groupId, String sections,
		boolean latest, int start, int end) {
		return filterFindByG_S_L(groupId, sections, latest, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_S_L(long groupId, String sections,
		boolean latest, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_S_L(groupId, sections, latest, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_S_L_GROUPID_2);

		boolean bindSections = false;

		if (sections == null) {
			query.append(_FINDER_COLUMN_G_S_L_SECTIONS_1);
		}
		else if (sections.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_S_L_SECTIONS_3);
		}
		else {
			bindSections = true;

			query.append(_FINDER_COLUMN_G_S_L_SECTIONS_2);
		}

		query.append(_FINDER_COLUMN_G_S_L_LATEST_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindSections) {
				qPos.add(sections);
			}

			qPos.add(latest);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] filterFindByG_S_L_PrevAndNext(long kbArticleId,
		long groupId, String sections, boolean latest,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_S_L_PrevAndNext(kbArticleId, groupId, sections,
				latest, orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByG_S_L_PrevAndNext(session, kbArticle,
					groupId, sections, latest, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = filterGetByG_S_L_PrevAndNext(session, kbArticle,
					groupId, sections, latest, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle filterGetByG_S_L_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, String sections, boolean latest,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_S_L_GROUPID_2);

		boolean bindSections = false;

		if (sections == null) {
			query.append(_FINDER_COLUMN_G_S_L_SECTIONS_1);
		}
		else if (sections.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_S_L_SECTIONS_3);
		}
		else {
			bindSections = true;

			query.append(_FINDER_COLUMN_G_S_L_SECTIONS_2);
		}

		query.append(_FINDER_COLUMN_G_S_L_LATEST_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindSections) {
			qPos.add(sections);
		}

		qPos.add(latest);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and sections LIKE any &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sectionses the sectionses
	 * @param latest the latest
	 * @return the matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_S_L(long groupId, String[] sectionses,
		boolean latest) {
		return filterFindByG_S_L(groupId, sectionses, latest,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and sections LIKE any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sectionses the sectionses
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_S_L(long groupId, String[] sectionses,
		boolean latest, int start, int end) {
		return filterFindByG_S_L(groupId, sectionses, latest, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permission to view where groupId = &#63; and sections LIKE any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sectionses the sectionses
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_S_L(long groupId, String[] sectionses,
		boolean latest, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_S_L(groupId, sectionses, latest, start, end,
				orderByComparator);
		}

		if (sectionses == null) {
			sectionses = new String[0];
		}
		else {
			sectionses = ArrayUtil.distinct(sectionses,
					NULL_SAFE_STRING_COMPARATOR);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_S_L_GROUPID_2);

		if (sectionses.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < sectionses.length; i++) {
				String sections = sectionses[i];

				if (sections == null) {
					query.append(_FINDER_COLUMN_G_S_L_SECTIONS_4);
				}
				else if (sections.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_S_L_SECTIONS_6);
				}
				else {
					query.append(_FINDER_COLUMN_G_S_L_SECTIONS_5);
				}

				if ((i + 1) < sectionses.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_S_L_LATEST_2);

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			for (String sections : sectionses) {
				if ((sections != null) && !sections.isEmpty()) {
					qPos.add(sections);
				}
			}

			qPos.add(latest);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the k b articles where groupId = &#63; and sections LIKE any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sectionses the sectionses
	 * @param latest the latest
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_S_L(long groupId, String[] sectionses,
		boolean latest) {
		return findByG_S_L(groupId, sectionses, latest, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and sections LIKE any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sectionses the sectionses
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_S_L(long groupId, String[] sectionses,
		boolean latest, int start, int end) {
		return findByG_S_L(groupId, sectionses, latest, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and sections LIKE any &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sectionses the sectionses
	 * @param latest the latest
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_S_L(long groupId, String[] sectionses,
		boolean latest, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (sectionses == null) {
			sectionses = new String[0];
		}
		else {
			sectionses = ArrayUtil.distinct(sectionses,
					NULL_SAFE_STRING_COMPARATOR);
		}

		if (sectionses.length == 1) {
			return findByG_S_L(groupId, sectionses[0], latest, start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					groupId, StringUtil.merge(sectionses), latest
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, StringUtil.merge(sectionses), latest,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S_L,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						!ArrayUtil.contains(sectionses, kbArticle.getSections()) ||
						(latest != kbArticle.getLatest())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_S_L_GROUPID_2);

			if (sectionses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < sectionses.length; i++) {
					String sections = sectionses[i];

					if (sections == null) {
						query.append(_FINDER_COLUMN_G_S_L_SECTIONS_4);
					}
					else if (sections.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_G_S_L_SECTIONS_6);
					}
					else {
						query.append(_FINDER_COLUMN_G_S_L_SECTIONS_5);
					}

					if ((i + 1) < sectionses.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_S_L_LATEST_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				for (String sections : sectionses) {
					if ((sections != null) && !sections.isEmpty()) {
						qPos.add(sections);
					}
				}

				qPos.add(latest);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S_L,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S_L,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the k b articles where groupId = &#63; and sections LIKE &#63; and latest = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param latest the latest
	 */
	@Override
	public void removeByG_S_L(long groupId, String sections, boolean latest) {
		for (KBArticle kbArticle : findByG_S_L(groupId, sections, latest,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where groupId = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param latest the latest
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByG_S_L(long groupId, String sections, boolean latest) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_S_L;

		Object[] finderArgs = new Object[] { groupId, sections, latest };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_S_L_GROUPID_2);

			boolean bindSections = false;

			if (sections == null) {
				query.append(_FINDER_COLUMN_G_S_L_SECTIONS_1);
			}
			else if (sections.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_S_L_SECTIONS_3);
			}
			else {
				bindSections = true;

				query.append(_FINDER_COLUMN_G_S_L_SECTIONS_2);
			}

			query.append(_FINDER_COLUMN_G_S_L_LATEST_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindSections) {
					qPos.add(sections);
				}

				qPos.add(latest);

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

	/**
	 * Returns the number of k b articles where groupId = &#63; and sections LIKE any &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sectionses the sectionses
	 * @param latest the latest
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByG_S_L(long groupId, String[] sectionses, boolean latest) {
		if (sectionses == null) {
			sectionses = new String[0];
		}
		else {
			sectionses = ArrayUtil.distinct(sectionses,
					NULL_SAFE_STRING_COMPARATOR);
		}

		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(sectionses), latest
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_S_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_S_L_GROUPID_2);

			if (sectionses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < sectionses.length; i++) {
					String sections = sectionses[i];

					if (sections == null) {
						query.append(_FINDER_COLUMN_G_S_L_SECTIONS_4);
					}
					else if (sections.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_G_S_L_SECTIONS_6);
					}
					else {
						query.append(_FINDER_COLUMN_G_S_L_SECTIONS_5);
					}

					if ((i + 1) < sectionses.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_S_L_LATEST_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				for (String sections : sectionses) {
					if ((sections != null) && !sections.isEmpty()) {
						qPos.add(sections);
					}
				}

				qPos.add(latest);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_S_L,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_S_L,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and sections LIKE &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param latest the latest
	 * @return the number of matching k b articles that the user has permission to view
	 */
	@Override
	public int filterCountByG_S_L(long groupId, String sections, boolean latest) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_S_L(groupId, sections, latest);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_S_L_GROUPID_2);

		boolean bindSections = false;

		if (sections == null) {
			query.append(_FINDER_COLUMN_G_S_L_SECTIONS_1);
		}
		else if (sections.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_S_L_SECTIONS_3);
		}
		else {
			bindSections = true;

			query.append(_FINDER_COLUMN_G_S_L_SECTIONS_2);
		}

		query.append(_FINDER_COLUMN_G_S_L_LATEST_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindSections) {
				qPos.add(sections);
			}

			qPos.add(latest);

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

	/**
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and sections LIKE any &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sectionses the sectionses
	 * @param latest the latest
	 * @return the number of matching k b articles that the user has permission to view
	 */
	@Override
	public int filterCountByG_S_L(long groupId, String[] sectionses,
		boolean latest) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_S_L(groupId, sectionses, latest);
		}

		if (sectionses == null) {
			sectionses = new String[0];
		}
		else {
			sectionses = ArrayUtil.distinct(sectionses,
					NULL_SAFE_STRING_COMPARATOR);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_S_L_GROUPID_2);

		if (sectionses.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < sectionses.length; i++) {
				String sections = sectionses[i];

				if (sections == null) {
					query.append(_FINDER_COLUMN_G_S_L_SECTIONS_4);
				}
				else if (sections.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_S_L_SECTIONS_6);
				}
				else {
					query.append(_FINDER_COLUMN_G_S_L_SECTIONS_5);
				}

				if ((i + 1) < sectionses.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_S_L_LATEST_2);

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			for (String sections : sectionses) {
				if ((sections != null) && !sections.isEmpty()) {
					qPos.add(sections);
				}
			}

			qPos.add(latest);

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

	private static final String _FINDER_COLUMN_G_S_L_GROUPID_2 = "kbArticle.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_S_L_SECTIONS_1 = "kbArticle.sections IS NULL AND ";
	private static final String _FINDER_COLUMN_G_S_L_SECTIONS_2 = "kbArticle.sections LIKE ? AND ";
	private static final String _FINDER_COLUMN_G_S_L_SECTIONS_3 = "(kbArticle.sections IS NULL OR kbArticle.sections LIKE '') AND ";
	private static final String _FINDER_COLUMN_G_S_L_SECTIONS_4 = "(" +
		removeConjunction(_FINDER_COLUMN_G_S_L_SECTIONS_1) + ")";
	private static final String _FINDER_COLUMN_G_S_L_SECTIONS_5 = "(" +
		removeConjunction(_FINDER_COLUMN_G_S_L_SECTIONS_2) + ")";
	private static final String _FINDER_COLUMN_G_S_L_SECTIONS_6 = "(" +
		removeConjunction(_FINDER_COLUMN_G_S_L_SECTIONS_3) + ")";
	private static final String _FINDER_COLUMN_G_S_L_LATEST_2 = "kbArticle.latest = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_S_M",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_S_M = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_S_M",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the k b articles where groupId = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param main the main
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_S_M(long groupId, String sections,
		boolean main) {
		return findByG_S_M(groupId, sections, main, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_S_M(long groupId, String sections,
		boolean main, int start, int end) {
		return findByG_S_M(groupId, sections, main, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_S_M(long groupId, String sections,
		boolean main, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S_M;
		finderArgs = new Object[] {
				groupId, sections, main,
				
				start, end, orderByComparator
			};

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						!StringUtil.wildcardMatches(kbArticle.getSections(),
							sections, CharPool.UNDERLINE, CharPool.PERCENT,
							CharPool.BACK_SLASH, true) ||
						(main != kbArticle.getMain())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_S_M_GROUPID_2);

			boolean bindSections = false;

			if (sections == null) {
				query.append(_FINDER_COLUMN_G_S_M_SECTIONS_1);
			}
			else if (sections.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_S_M_SECTIONS_3);
			}
			else {
				bindSections = true;

				query.append(_FINDER_COLUMN_G_S_M_SECTIONS_2);
			}

			query.append(_FINDER_COLUMN_G_S_M_MAIN_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindSections) {
					qPos.add(sections);
				}

				qPos.add(main);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where groupId = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByG_S_M_First(long groupId, String sections,
		boolean main, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByG_S_M_First(groupId, sections, main,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", sections=");
		msg.append(sections);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where groupId = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByG_S_M_First(long groupId, String sections,
		boolean main, OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByG_S_M(groupId, sections, main, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByG_S_M_Last(long groupId, String sections,
		boolean main, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByG_S_M_Last(groupId, sections, main,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", sections=");
		msg.append(sections);

		msg.append(", main=");
		msg.append(main);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByG_S_M_Last(long groupId, String sections,
		boolean main, OrderByComparator<KBArticle> orderByComparator) {
		int count = countByG_S_M(groupId, sections, main);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByG_S_M(groupId, sections, main, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByG_S_M_PrevAndNext(long kbArticleId, long groupId,
		String sections, boolean main,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByG_S_M_PrevAndNext(session, kbArticle, groupId,
					sections, main, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByG_S_M_PrevAndNext(session, kbArticle, groupId,
					sections, main, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByG_S_M_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, String sections, boolean main,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_S_M_GROUPID_2);

		boolean bindSections = false;

		if (sections == null) {
			query.append(_FINDER_COLUMN_G_S_M_SECTIONS_1);
		}
		else if (sections.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_S_M_SECTIONS_3);
		}
		else {
			bindSections = true;

			query.append(_FINDER_COLUMN_G_S_M_SECTIONS_2);
		}

		query.append(_FINDER_COLUMN_G_S_M_MAIN_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindSections) {
			qPos.add(sections);
		}

		qPos.add(main);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param main the main
	 * @return the matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_S_M(long groupId, String sections,
		boolean main) {
		return filterFindByG_S_M(groupId, sections, main, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_S_M(long groupId, String sections,
		boolean main, int start, int end) {
		return filterFindByG_S_M(groupId, sections, main, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_S_M(long groupId, String sections,
		boolean main, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_S_M(groupId, sections, main, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_S_M_GROUPID_2);

		boolean bindSections = false;

		if (sections == null) {
			query.append(_FINDER_COLUMN_G_S_M_SECTIONS_1);
		}
		else if (sections.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_S_M_SECTIONS_3);
		}
		else {
			bindSections = true;

			query.append(_FINDER_COLUMN_G_S_M_SECTIONS_2);
		}

		query.append(_FINDER_COLUMN_G_S_M_MAIN_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindSections) {
				qPos.add(sections);
			}

			qPos.add(main);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param main the main
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] filterFindByG_S_M_PrevAndNext(long kbArticleId,
		long groupId, String sections, boolean main,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_S_M_PrevAndNext(kbArticleId, groupId, sections,
				main, orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByG_S_M_PrevAndNext(session, kbArticle,
					groupId, sections, main, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = filterGetByG_S_M_PrevAndNext(session, kbArticle,
					groupId, sections, main, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle filterGetByG_S_M_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, String sections, boolean main,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_S_M_GROUPID_2);

		boolean bindSections = false;

		if (sections == null) {
			query.append(_FINDER_COLUMN_G_S_M_SECTIONS_1);
		}
		else if (sections.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_S_M_SECTIONS_3);
		}
		else {
			bindSections = true;

			query.append(_FINDER_COLUMN_G_S_M_SECTIONS_2);
		}

		query.append(_FINDER_COLUMN_G_S_M_MAIN_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindSections) {
			qPos.add(sections);
		}

		qPos.add(main);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and sections LIKE any &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sectionses the sectionses
	 * @param main the main
	 * @return the matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_S_M(long groupId, String[] sectionses,
		boolean main) {
		return filterFindByG_S_M(groupId, sectionses, main, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and sections LIKE any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sectionses the sectionses
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_S_M(long groupId, String[] sectionses,
		boolean main, int start, int end) {
		return filterFindByG_S_M(groupId, sectionses, main, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permission to view where groupId = &#63; and sections LIKE any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sectionses the sectionses
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_S_M(long groupId, String[] sectionses,
		boolean main, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_S_M(groupId, sectionses, main, start, end,
				orderByComparator);
		}

		if (sectionses == null) {
			sectionses = new String[0];
		}
		else {
			sectionses = ArrayUtil.distinct(sectionses,
					NULL_SAFE_STRING_COMPARATOR);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_S_M_GROUPID_2);

		if (sectionses.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < sectionses.length; i++) {
				String sections = sectionses[i];

				if (sections == null) {
					query.append(_FINDER_COLUMN_G_S_M_SECTIONS_4);
				}
				else if (sections.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_S_M_SECTIONS_6);
				}
				else {
					query.append(_FINDER_COLUMN_G_S_M_SECTIONS_5);
				}

				if ((i + 1) < sectionses.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_S_M_MAIN_2);

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			for (String sections : sectionses) {
				if ((sections != null) && !sections.isEmpty()) {
					qPos.add(sections);
				}
			}

			qPos.add(main);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the k b articles where groupId = &#63; and sections LIKE any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sectionses the sectionses
	 * @param main the main
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_S_M(long groupId, String[] sectionses,
		boolean main) {
		return findByG_S_M(groupId, sectionses, main, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and sections LIKE any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sectionses the sectionses
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_S_M(long groupId, String[] sectionses,
		boolean main, int start, int end) {
		return findByG_S_M(groupId, sectionses, main, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and sections LIKE any &#63; and main = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sectionses the sectionses
	 * @param main the main
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_S_M(long groupId, String[] sectionses,
		boolean main, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (sectionses == null) {
			sectionses = new String[0];
		}
		else {
			sectionses = ArrayUtil.distinct(sectionses,
					NULL_SAFE_STRING_COMPARATOR);
		}

		if (sectionses.length == 1) {
			return findByG_S_M(groupId, sectionses[0], main, start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					groupId, StringUtil.merge(sectionses), main
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, StringUtil.merge(sectionses), main,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S_M,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						!ArrayUtil.contains(sectionses, kbArticle.getSections()) ||
						(main != kbArticle.getMain())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_S_M_GROUPID_2);

			if (sectionses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < sectionses.length; i++) {
					String sections = sectionses[i];

					if (sections == null) {
						query.append(_FINDER_COLUMN_G_S_M_SECTIONS_4);
					}
					else if (sections.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_G_S_M_SECTIONS_6);
					}
					else {
						query.append(_FINDER_COLUMN_G_S_M_SECTIONS_5);
					}

					if ((i + 1) < sectionses.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_S_M_MAIN_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				for (String sections : sectionses) {
					if ((sections != null) && !sections.isEmpty()) {
						qPos.add(sections);
					}
				}

				qPos.add(main);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S_M,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S_M,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the k b articles where groupId = &#63; and sections LIKE &#63; and main = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param main the main
	 */
	@Override
	public void removeByG_S_M(long groupId, String sections, boolean main) {
		for (KBArticle kbArticle : findByG_S_M(groupId, sections, main,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where groupId = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param main the main
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByG_S_M(long groupId, String sections, boolean main) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_S_M;

		Object[] finderArgs = new Object[] { groupId, sections, main };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_S_M_GROUPID_2);

			boolean bindSections = false;

			if (sections == null) {
				query.append(_FINDER_COLUMN_G_S_M_SECTIONS_1);
			}
			else if (sections.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_S_M_SECTIONS_3);
			}
			else {
				bindSections = true;

				query.append(_FINDER_COLUMN_G_S_M_SECTIONS_2);
			}

			query.append(_FINDER_COLUMN_G_S_M_MAIN_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindSections) {
					qPos.add(sections);
				}

				qPos.add(main);

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

	/**
	 * Returns the number of k b articles where groupId = &#63; and sections LIKE any &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sectionses the sectionses
	 * @param main the main
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByG_S_M(long groupId, String[] sectionses, boolean main) {
		if (sectionses == null) {
			sectionses = new String[0];
		}
		else {
			sectionses = ArrayUtil.distinct(sectionses,
					NULL_SAFE_STRING_COMPARATOR);
		}

		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(sectionses), main
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_S_M,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_S_M_GROUPID_2);

			if (sectionses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < sectionses.length; i++) {
					String sections = sectionses[i];

					if (sections == null) {
						query.append(_FINDER_COLUMN_G_S_M_SECTIONS_4);
					}
					else if (sections.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_G_S_M_SECTIONS_6);
					}
					else {
						query.append(_FINDER_COLUMN_G_S_M_SECTIONS_5);
					}

					if ((i + 1) < sectionses.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_S_M_MAIN_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				for (String sections : sectionses) {
					if ((sections != null) && !sections.isEmpty()) {
						qPos.add(sections);
					}
				}

				qPos.add(main);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_S_M,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_S_M,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and sections LIKE &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param main the main
	 * @return the number of matching k b articles that the user has permission to view
	 */
	@Override
	public int filterCountByG_S_M(long groupId, String sections, boolean main) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_S_M(groupId, sections, main);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_S_M_GROUPID_2);

		boolean bindSections = false;

		if (sections == null) {
			query.append(_FINDER_COLUMN_G_S_M_SECTIONS_1);
		}
		else if (sections.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_S_M_SECTIONS_3);
		}
		else {
			bindSections = true;

			query.append(_FINDER_COLUMN_G_S_M_SECTIONS_2);
		}

		query.append(_FINDER_COLUMN_G_S_M_MAIN_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindSections) {
				qPos.add(sections);
			}

			qPos.add(main);

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

	/**
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and sections LIKE any &#63; and main = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sectionses the sectionses
	 * @param main the main
	 * @return the number of matching k b articles that the user has permission to view
	 */
	@Override
	public int filterCountByG_S_M(long groupId, String[] sectionses,
		boolean main) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_S_M(groupId, sectionses, main);
		}

		if (sectionses == null) {
			sectionses = new String[0];
		}
		else {
			sectionses = ArrayUtil.distinct(sectionses,
					NULL_SAFE_STRING_COMPARATOR);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_S_M_GROUPID_2);

		if (sectionses.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < sectionses.length; i++) {
				String sections = sectionses[i];

				if (sections == null) {
					query.append(_FINDER_COLUMN_G_S_M_SECTIONS_4);
				}
				else if (sections.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_S_M_SECTIONS_6);
				}
				else {
					query.append(_FINDER_COLUMN_G_S_M_SECTIONS_5);
				}

				if ((i + 1) < sectionses.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_S_M_MAIN_2);

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			for (String sections : sectionses) {
				if ((sections != null) && !sections.isEmpty()) {
					qPos.add(sections);
				}
			}

			qPos.add(main);

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

	private static final String _FINDER_COLUMN_G_S_M_GROUPID_2 = "kbArticle.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_S_M_SECTIONS_1 = "kbArticle.sections IS NULL AND ";
	private static final String _FINDER_COLUMN_G_S_M_SECTIONS_2 = "kbArticle.sections LIKE ? AND ";
	private static final String _FINDER_COLUMN_G_S_M_SECTIONS_3 = "(kbArticle.sections IS NULL OR kbArticle.sections LIKE '') AND ";
	private static final String _FINDER_COLUMN_G_S_M_SECTIONS_4 = "(" +
		removeConjunction(_FINDER_COLUMN_G_S_M_SECTIONS_1) + ")";
	private static final String _FINDER_COLUMN_G_S_M_SECTIONS_5 = "(" +
		removeConjunction(_FINDER_COLUMN_G_S_M_SECTIONS_2) + ")";
	private static final String _FINDER_COLUMN_G_S_M_SECTIONS_6 = "(" +
		removeConjunction(_FINDER_COLUMN_G_S_M_SECTIONS_3) + ")";
	private static final String _FINDER_COLUMN_G_S_M_MAIN_2 = "kbArticle.main = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_S_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_S_S = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_S_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the k b articles where groupId = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param status the status
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_S_S(long groupId, String sections, int status) {
		return findByG_S_S(groupId, sections, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_S_S(long groupId, String sections,
		int status, int start, int end) {
		return findByG_S_S(groupId, sections, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_S_S(long groupId, String sections,
		int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S_S;
		finderArgs = new Object[] {
				groupId, sections, status,
				
				start, end, orderByComparator
			};

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						!StringUtil.wildcardMatches(kbArticle.getSections(),
							sections, CharPool.UNDERLINE, CharPool.PERCENT,
							CharPool.BACK_SLASH, true) ||
						(status != kbArticle.getStatus())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_S_S_GROUPID_2);

			boolean bindSections = false;

			if (sections == null) {
				query.append(_FINDER_COLUMN_G_S_S_SECTIONS_1);
			}
			else if (sections.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_S_S_SECTIONS_3);
			}
			else {
				bindSections = true;

				query.append(_FINDER_COLUMN_G_S_S_SECTIONS_2);
			}

			query.append(_FINDER_COLUMN_G_S_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindSections) {
					qPos.add(sections);
				}

				qPos.add(status);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where groupId = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByG_S_S_First(long groupId, String sections,
		int status, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByG_S_S_First(groupId, sections, status,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", sections=");
		msg.append(sections);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where groupId = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByG_S_S_First(long groupId, String sections,
		int status, OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByG_S_S(groupId, sections, status, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByG_S_S_Last(long groupId, String sections,
		int status, OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByG_S_S_Last(groupId, sections, status,
				orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", sections=");
		msg.append(sections);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByG_S_S_Last(long groupId, String sections,
		int status, OrderByComparator<KBArticle> orderByComparator) {
		int count = countByG_S_S(groupId, sections, status);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByG_S_S(groupId, sections, status,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByG_S_S_PrevAndNext(long kbArticleId, long groupId,
		String sections, int status,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByG_S_S_PrevAndNext(session, kbArticle, groupId,
					sections, status, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = getByG_S_S_PrevAndNext(session, kbArticle, groupId,
					sections, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle getByG_S_S_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, String sections, int status,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_S_S_GROUPID_2);

		boolean bindSections = false;

		if (sections == null) {
			query.append(_FINDER_COLUMN_G_S_S_SECTIONS_1);
		}
		else if (sections.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_S_S_SECTIONS_3);
		}
		else {
			bindSections = true;

			query.append(_FINDER_COLUMN_G_S_S_SECTIONS_2);
		}

		query.append(_FINDER_COLUMN_G_S_S_STATUS_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindSections) {
			qPos.add(sections);
		}

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param status the status
	 * @return the matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_S_S(long groupId, String sections,
		int status) {
		return filterFindByG_S_S(groupId, sections, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_S_S(long groupId, String sections,
		int status, int start, int end) {
		return filterFindByG_S_S(groupId, sections, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_S_S(long groupId, String sections,
		int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_S_S(groupId, sections, status, start, end,
				orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_S_S_GROUPID_2);

		boolean bindSections = false;

		if (sections == null) {
			query.append(_FINDER_COLUMN_G_S_S_SECTIONS_1);
		}
		else if (sections.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_S_S_SECTIONS_3);
		}
		else {
			bindSections = true;

			query.append(_FINDER_COLUMN_G_S_S_SECTIONS_2);
		}

		query.append(_FINDER_COLUMN_G_S_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindSections) {
				qPos.add(sections);
			}

			qPos.add(status);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] filterFindByG_S_S_PrevAndNext(long kbArticleId,
		long groupId, String sections, int status,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_S_S_PrevAndNext(kbArticleId, groupId, sections,
				status, orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByG_S_S_PrevAndNext(session, kbArticle,
					groupId, sections, status, orderByComparator, true);

			array[1] = kbArticle;

			array[2] = filterGetByG_S_S_PrevAndNext(session, kbArticle,
					groupId, sections, status, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBArticle filterGetByG_S_S_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, String sections, int status,
		OrderByComparator<KBArticle> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_S_S_GROUPID_2);

		boolean bindSections = false;

		if (sections == null) {
			query.append(_FINDER_COLUMN_G_S_S_SECTIONS_1);
		}
		else if (sections.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_S_S_SECTIONS_3);
		}
		else {
			bindSections = true;

			query.append(_FINDER_COLUMN_G_S_S_SECTIONS_2);
		}

		query.append(_FINDER_COLUMN_G_S_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindSections) {
			qPos.add(sections);
		}

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and sections LIKE any &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sectionses the sectionses
	 * @param status the status
	 * @return the matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_S_S(long groupId, String[] sectionses,
		int status) {
		return filterFindByG_S_S(groupId, sectionses, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and sections LIKE any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sectionses the sectionses
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_S_S(long groupId, String[] sectionses,
		int status, int start, int end) {
		return filterFindByG_S_S(groupId, sectionses, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permission to view where groupId = &#63; and sections LIKE any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sectionses the sectionses
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_S_S(long groupId, String[] sectionses,
		int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_S_S(groupId, sectionses, status, start, end,
				orderByComparator);
		}

		if (sectionses == null) {
			sectionses = new String[0];
		}
		else {
			sectionses = ArrayUtil.distinct(sectionses,
					NULL_SAFE_STRING_COMPARATOR);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_S_S_GROUPID_2);

		if (sectionses.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < sectionses.length; i++) {
				String sections = sectionses[i];

				if (sections == null) {
					query.append(_FINDER_COLUMN_G_S_S_SECTIONS_4);
				}
				else if (sections.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_S_S_SECTIONS_6);
				}
				else {
					query.append(_FINDER_COLUMN_G_S_S_SECTIONS_5);
				}

				if ((i + 1) < sectionses.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_S_S_STATUS_2);

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			for (String sections : sectionses) {
				if ((sections != null) && !sections.isEmpty()) {
					qPos.add(sections);
				}
			}

			qPos.add(status);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the k b articles where groupId = &#63; and sections LIKE any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sectionses the sectionses
	 * @param status the status
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_S_S(long groupId, String[] sectionses,
		int status) {
		return findByG_S_S(groupId, sectionses, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and sections LIKE any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sectionses the sectionses
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_S_S(long groupId, String[] sectionses,
		int status, int start, int end) {
		return findByG_S_S(groupId, sectionses, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and sections LIKE any &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param sectionses the sectionses
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_S_S(long groupId, String[] sectionses,
		int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (sectionses == null) {
			sectionses = new String[0];
		}
		else {
			sectionses = ArrayUtil.distinct(sectionses,
					NULL_SAFE_STRING_COMPARATOR);
		}

		if (sectionses.length == 1) {
			return findByG_S_S(groupId, sectionses[0], status, start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					groupId, StringUtil.merge(sectionses), status
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, StringUtil.merge(sectionses), status,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S_S,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						!ArrayUtil.contains(sectionses, kbArticle.getSections()) ||
						(status != kbArticle.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_S_S_GROUPID_2);

			if (sectionses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < sectionses.length; i++) {
					String sections = sectionses[i];

					if (sections == null) {
						query.append(_FINDER_COLUMN_G_S_S_SECTIONS_4);
					}
					else if (sections.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_G_S_S_SECTIONS_6);
					}
					else {
						query.append(_FINDER_COLUMN_G_S_S_SECTIONS_5);
					}

					if ((i + 1) < sectionses.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_S_S_STATUS_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				for (String sections : sectionses) {
					if ((sections != null) && !sections.isEmpty()) {
						qPos.add(sections);
					}
				}

				qPos.add(status);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S_S,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_S_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the k b articles where groupId = &#63; and sections LIKE &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param status the status
	 */
	@Override
	public void removeByG_S_S(long groupId, String sections, int status) {
		for (KBArticle kbArticle : findByG_S_S(groupId, sections, status,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where groupId = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param status the status
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByG_S_S(long groupId, String sections, int status) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_S_S;

		Object[] finderArgs = new Object[] { groupId, sections, status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_S_S_GROUPID_2);

			boolean bindSections = false;

			if (sections == null) {
				query.append(_FINDER_COLUMN_G_S_S_SECTIONS_1);
			}
			else if (sections.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_S_S_SECTIONS_3);
			}
			else {
				bindSections = true;

				query.append(_FINDER_COLUMN_G_S_S_SECTIONS_2);
			}

			query.append(_FINDER_COLUMN_G_S_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindSections) {
					qPos.add(sections);
				}

				qPos.add(status);

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

	/**
	 * Returns the number of k b articles where groupId = &#63; and sections LIKE any &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sectionses the sectionses
	 * @param status the status
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByG_S_S(long groupId, String[] sectionses, int status) {
		if (sectionses == null) {
			sectionses = new String[0];
		}
		else {
			sectionses = ArrayUtil.distinct(sectionses,
					NULL_SAFE_STRING_COMPARATOR);
		}

		Object[] finderArgs = new Object[] {
				groupId, StringUtil.merge(sectionses), status
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_S_S,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_S_S_GROUPID_2);

			if (sectionses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < sectionses.length; i++) {
					String sections = sectionses[i];

					if (sections == null) {
						query.append(_FINDER_COLUMN_G_S_S_SECTIONS_4);
					}
					else if (sections.equals(StringPool.BLANK)) {
						query.append(_FINDER_COLUMN_G_S_S_SECTIONS_6);
					}
					else {
						query.append(_FINDER_COLUMN_G_S_S_SECTIONS_5);
					}

					if ((i + 1) < sectionses.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_G_S_S_STATUS_2);

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				for (String sections : sectionses) {
					if ((sections != null) && !sections.isEmpty()) {
						qPos.add(sections);
					}
				}

				qPos.add(status);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_S_S,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_S_S,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and sections LIKE &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sections the sections
	 * @param status the status
	 * @return the number of matching k b articles that the user has permission to view
	 */
	@Override
	public int filterCountByG_S_S(long groupId, String sections, int status) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_S_S(groupId, sections, status);
		}

		StringBundler query = new StringBundler(4);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_S_S_GROUPID_2);

		boolean bindSections = false;

		if (sections == null) {
			query.append(_FINDER_COLUMN_G_S_S_SECTIONS_1);
		}
		else if (sections.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_S_S_SECTIONS_3);
		}
		else {
			bindSections = true;

			query.append(_FINDER_COLUMN_G_S_S_SECTIONS_2);
		}

		query.append(_FINDER_COLUMN_G_S_S_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			if (bindSections) {
				qPos.add(sections);
			}

			qPos.add(status);

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

	/**
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and sections LIKE any &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param sectionses the sectionses
	 * @param status the status
	 * @return the number of matching k b articles that the user has permission to view
	 */
	@Override
	public int filterCountByG_S_S(long groupId, String[] sectionses, int status) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_S_S(groupId, sectionses, status);
		}

		if (sectionses == null) {
			sectionses = new String[0];
		}
		else {
			sectionses = ArrayUtil.distinct(sectionses,
					NULL_SAFE_STRING_COMPARATOR);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_S_S_GROUPID_2);

		if (sectionses.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			for (int i = 0; i < sectionses.length; i++) {
				String sections = sectionses[i];

				if (sections == null) {
					query.append(_FINDER_COLUMN_G_S_S_SECTIONS_4);
				}
				else if (sections.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_G_S_S_SECTIONS_6);
				}
				else {
					query.append(_FINDER_COLUMN_G_S_S_SECTIONS_5);
				}

				if ((i + 1) < sectionses.length) {
					query.append(WHERE_OR);
				}
			}

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(WHERE_AND);
		}

		query.append(_FINDER_COLUMN_G_S_S_STATUS_2);

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			for (String sections : sectionses) {
				if ((sections != null) && !sections.isEmpty()) {
					qPos.add(sections);
				}
			}

			qPos.add(status);

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

	private static final String _FINDER_COLUMN_G_S_S_GROUPID_2 = "kbArticle.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_S_S_SECTIONS_1 = "kbArticle.sections IS NULL AND ";
	private static final String _FINDER_COLUMN_G_S_S_SECTIONS_2 = "kbArticle.sections LIKE ? AND ";
	private static final String _FINDER_COLUMN_G_S_S_SECTIONS_3 = "(kbArticle.sections IS NULL OR kbArticle.sections LIKE '') AND ";
	private static final String _FINDER_COLUMN_G_S_S_SECTIONS_4 = "(" +
		removeConjunction(_FINDER_COLUMN_G_S_S_SECTIONS_1) + ")";
	private static final String _FINDER_COLUMN_G_S_S_SECTIONS_5 = "(" +
		removeConjunction(_FINDER_COLUMN_G_S_S_SECTIONS_2) + ")";
	private static final String _FINDER_COLUMN_G_S_S_SECTIONS_6 = "(" +
		removeConjunction(_FINDER_COLUMN_G_S_S_SECTIONS_3) + ")";
	private static final String _FINDER_COLUMN_G_S_S_STATUS_2 = "kbArticle.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_KBFI_UT_ST =
		new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_KBFI_UT_ST",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_KBFI_UT_ST =
		new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, KBArticleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_KBFI_UT_ST",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName()
			},
			KBArticleModelImpl.GROUPID_COLUMN_BITMASK |
			KBArticleModelImpl.KBFOLDERID_COLUMN_BITMASK |
			KBArticleModelImpl.URLTITLE_COLUMN_BITMASK |
			KBArticleModelImpl.STATUS_COLUMN_BITMASK |
			KBArticleModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_KBFI_UT_ST = new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_KBFI_UT_ST",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_KBFI_UT_ST =
		new FinderPath(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByG_KBFI_UT_ST",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param status the status
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_KBFI_UT_ST(long groupId, long kbFolderId,
		String urlTitle, int status) {
		return findByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_KBFI_UT_ST(long groupId, long kbFolderId,
		String urlTitle, int status, int start, int end) {
		return findByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle, status, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_KBFI_UT_ST(long groupId, long kbFolderId,
		String urlTitle, int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_KBFI_UT_ST;
			finderArgs = new Object[] { groupId, kbFolderId, urlTitle, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_KBFI_UT_ST;
			finderArgs = new Object[] {
					groupId, kbFolderId, urlTitle, status,
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						(kbFolderId != kbArticle.getKbFolderId()) ||
						!Validator.equals(urlTitle, kbArticle.getUrlTitle()) ||
						(status != kbArticle.getStatus())) {
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

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_GROUPID_2);

			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_KBFOLDERID_2);

			boolean bindUrlTitle = false;

			if (urlTitle == null) {
				query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_1);
			}
			else if (urlTitle.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_3);
			}
			else {
				bindUrlTitle = true;

				query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_2);
			}

			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(kbFolderId);

				if (bindUrlTitle) {
					qPos.add(urlTitle);
				}

				qPos.add(status);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByG_KBFI_UT_ST_First(long groupId, long kbFolderId,
		String urlTitle, int status,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByG_KBFI_UT_ST_First(groupId, kbFolderId,
				urlTitle, status, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", kbFolderId=");
		msg.append(kbFolderId);

		msg.append(", urlTitle=");
		msg.append(urlTitle);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the first k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByG_KBFI_UT_ST_First(long groupId, long kbFolderId,
		String urlTitle, int status,
		OrderByComparator<KBArticle> orderByComparator) {
		List<KBArticle> list = findByG_KBFI_UT_ST(groupId, kbFolderId,
				urlTitle, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article
	 * @throws NoSuchArticleException if a matching k b article could not be found
	 */
	@Override
	public KBArticle findByG_KBFI_UT_ST_Last(long groupId, long kbFolderId,
		String urlTitle, int status,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByG_KBFI_UT_ST_Last(groupId, kbFolderId,
				urlTitle, status, orderByComparator);

		if (kbArticle != null) {
			return kbArticle;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", kbFolderId=");
		msg.append(kbFolderId);

		msg.append(", urlTitle=");
		msg.append(urlTitle);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchArticleException(msg.toString());
	}

	/**
	 * Returns the last k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b article, or <code>null</code> if a matching k b article could not be found
	 */
	@Override
	public KBArticle fetchByG_KBFI_UT_ST_Last(long groupId, long kbFolderId,
		String urlTitle, int status,
		OrderByComparator<KBArticle> orderByComparator) {
		int count = countByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle, status);

		if (count == 0) {
			return null;
		}

		List<KBArticle> list = findByG_KBFI_UT_ST(groupId, kbFolderId,
				urlTitle, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] findByG_KBFI_UT_ST_PrevAndNext(long kbArticleId,
		long groupId, long kbFolderId, String urlTitle, int status,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = getByG_KBFI_UT_ST_PrevAndNext(session, kbArticle,
					groupId, kbFolderId, urlTitle, status, orderByComparator,
					true);

			array[1] = kbArticle;

			array[2] = getByG_KBFI_UT_ST_PrevAndNext(session, kbArticle,
					groupId, kbFolderId, urlTitle, status, orderByComparator,
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

	protected KBArticle getByG_KBFI_UT_ST_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, long kbFolderId, String urlTitle,
		int status, OrderByComparator<KBArticle> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_KBFI_UT_ST_GROUPID_2);

		query.append(_FINDER_COLUMN_G_KBFI_UT_ST_KBFOLDERID_2);

		boolean bindUrlTitle = false;

		if (urlTitle == null) {
			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_1);
		}
		else if (urlTitle.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_3);
		}
		else {
			bindUrlTitle = true;

			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_2);
		}

		query.append(_FINDER_COLUMN_G_KBFI_UT_ST_STATUS_2);

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
			query.append(KBArticleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(kbFolderId);

		if (bindUrlTitle) {
			qPos.add(urlTitle);
		}

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param status the status
	 * @return the matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_KBFI_UT_ST(long groupId,
		long kbFolderId, String urlTitle, int status) {
		return filterFindByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle, status,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_KBFI_UT_ST(long groupId,
		long kbFolderId, String urlTitle, int status, int start, int end) {
		return filterFindByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle, status,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permissions to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param status the status
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_KBFI_UT_ST(long groupId,
		long kbFolderId, String urlTitle, int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle, status,
				start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(6);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_KBFI_UT_ST_GROUPID_2);

		query.append(_FINDER_COLUMN_G_KBFI_UT_ST_KBFOLDERID_2);

		boolean bindUrlTitle = false;

		if (urlTitle == null) {
			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_1);
		}
		else if (urlTitle.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_3);
		}
		else {
			bindUrlTitle = true;

			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_2);
		}

		query.append(_FINDER_COLUMN_G_KBFI_UT_ST_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(kbFolderId);

			if (bindUrlTitle) {
				qPos.add(urlTitle);
			}

			qPos.add(status);

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b articles before and after the current k b article in the ordered set of k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	 *
	 * @param kbArticleId the primary key of the current k b article
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle[] filterFindByG_KBFI_UT_ST_PrevAndNext(long kbArticleId,
		long groupId, long kbFolderId, String urlTitle, int status,
		OrderByComparator<KBArticle> orderByComparator)
		throws NoSuchArticleException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_KBFI_UT_ST_PrevAndNext(kbArticleId, groupId,
				kbFolderId, urlTitle, status, orderByComparator);
		}

		KBArticle kbArticle = findByPrimaryKey(kbArticleId);

		Session session = null;

		try {
			session = openSession();

			KBArticle[] array = new KBArticleImpl[3];

			array[0] = filterGetByG_KBFI_UT_ST_PrevAndNext(session, kbArticle,
					groupId, kbFolderId, urlTitle, status, orderByComparator,
					true);

			array[1] = kbArticle;

			array[2] = filterGetByG_KBFI_UT_ST_PrevAndNext(session, kbArticle,
					groupId, kbFolderId, urlTitle, status, orderByComparator,
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

	protected KBArticle filterGetByG_KBFI_UT_ST_PrevAndNext(Session session,
		KBArticle kbArticle, long groupId, long kbFolderId, String urlTitle,
		int status, OrderByComparator<KBArticle> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_KBFI_UT_ST_GROUPID_2);

		query.append(_FINDER_COLUMN_G_KBFI_UT_ST_KBFOLDERID_2);

		boolean bindUrlTitle = false;

		if (urlTitle == null) {
			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_1);
		}
		else if (urlTitle.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_3);
		}
		else {
			bindUrlTitle = true;

			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_2);
		}

		query.append(_FINDER_COLUMN_G_KBFI_UT_ST_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(kbFolderId);

		if (bindUrlTitle) {
			qPos.add(urlTitle);
		}

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbArticle);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBArticle> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param statuses the statuses
	 * @return the matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_KBFI_UT_ST(long groupId,
		long kbFolderId, String urlTitle, int[] statuses) {
		return filterFindByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle,
			statuses, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param statuses the statuses
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_KBFI_UT_ST(long groupId,
		long kbFolderId, String urlTitle, int[] statuses, int start, int end) {
		return filterFindByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle,
			statuses, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param statuses the statuses
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles that the user has permission to view
	 */
	@Override
	public List<KBArticle> filterFindByG_KBFI_UT_ST(long groupId,
		long kbFolderId, String urlTitle, int[] statuses, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle, statuses,
				start, end, orderByComparator);
		}

		if (statuses == null) {
			statuses = new int[0];
		}
		else {
			statuses = ArrayUtil.unique(statuses);
		}

		StringBundler query = new StringBundler();

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_KBFI_UT_ST_GROUPID_2);

		query.append(_FINDER_COLUMN_G_KBFI_UT_ST_KBFOLDERID_2);

		boolean bindUrlTitle = false;

		if (urlTitle == null) {
			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_1);
		}
		else if (urlTitle.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_3);
		}
		else {
			bindUrlTitle = true;

			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_2);
		}

		if (statuses.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_STATUS_7);

			query.append(StringUtil.merge(statuses));

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(StringPool.CLOSE_PARENTHESIS);
		}

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBArticleModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBArticleImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBArticleImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(kbFolderId);

			if (bindUrlTitle) {
				qPos.add(urlTitle);
			}

			return (List<KBArticle>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param statuses the statuses
	 * @return the matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_KBFI_UT_ST(long groupId, long kbFolderId,
		String urlTitle, int[] statuses) {
		return findByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle, statuses,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param statuses the statuses
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_KBFI_UT_ST(long groupId, long kbFolderId,
		String urlTitle, int[] statuses, int start, int end) {
		return findByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle, statuses,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param statuses the statuses
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b articles
	 */
	@Override
	public List<KBArticle> findByG_KBFI_UT_ST(long groupId, long kbFolderId,
		String urlTitle, int[] statuses, int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
		if (statuses == null) {
			statuses = new int[0];
		}
		else {
			statuses = ArrayUtil.unique(statuses);
		}

		if (statuses.length == 1) {
			return findByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle,
				statuses[0], start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] {
					groupId, kbFolderId, urlTitle, StringUtil.merge(statuses)
				};
		}
		else {
			finderArgs = new Object[] {
					groupId, kbFolderId, urlTitle, StringUtil.merge(statuses),
					
					start, end, orderByComparator
				};
		}

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_KBFI_UT_ST,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBArticle kbArticle : list) {
				if ((groupId != kbArticle.getGroupId()) ||
						(kbFolderId != kbArticle.getKbFolderId()) ||
						!Validator.equals(urlTitle, kbArticle.getUrlTitle()) ||
						!ArrayUtil.contains(statuses, kbArticle.getStatus())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_GROUPID_2);

			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_KBFOLDERID_2);

			boolean bindUrlTitle = false;

			if (urlTitle == null) {
				query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_1);
			}
			else if (urlTitle.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_3);
			}
			else {
				bindUrlTitle = true;

				query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_2);
			}

			if (statuses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_G_KBFI_UT_ST_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBArticleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(kbFolderId);

				if (bindUrlTitle) {
					qPos.add(urlTitle);
				}

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_KBFI_UT_ST,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_G_KBFI_UT_ST,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param status the status
	 */
	@Override
	public void removeByG_KBFI_UT_ST(long groupId, long kbFolderId,
		String urlTitle, int status) {
		for (KBArticle kbArticle : findByG_KBFI_UT_ST(groupId, kbFolderId,
				urlTitle, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param status the status
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByG_KBFI_UT_ST(long groupId, long kbFolderId,
		String urlTitle, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_KBFI_UT_ST;

		Object[] finderArgs = new Object[] { groupId, kbFolderId, urlTitle, status };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_GROUPID_2);

			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_KBFOLDERID_2);

			boolean bindUrlTitle = false;

			if (urlTitle == null) {
				query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_1);
			}
			else if (urlTitle.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_3);
			}
			else {
				bindUrlTitle = true;

				query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_2);
			}

			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(kbFolderId);

				if (bindUrlTitle) {
					qPos.add(urlTitle);
				}

				qPos.add(status);

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

	/**
	 * Returns the number of k b articles where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param statuses the statuses
	 * @return the number of matching k b articles
	 */
	@Override
	public int countByG_KBFI_UT_ST(long groupId, long kbFolderId,
		String urlTitle, int[] statuses) {
		if (statuses == null) {
			statuses = new int[0];
		}
		else {
			statuses = ArrayUtil.unique(statuses);
		}

		Object[] finderArgs = new Object[] {
				groupId, kbFolderId, urlTitle, StringUtil.merge(statuses)
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_KBFI_UT_ST,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_KBARTICLE_WHERE);

			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_GROUPID_2);

			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_KBFOLDERID_2);

			boolean bindUrlTitle = false;

			if (urlTitle == null) {
				query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_1);
			}
			else if (urlTitle.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_3);
			}
			else {
				bindUrlTitle = true;

				query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_2);
			}

			if (statuses.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_G_KBFI_UT_ST_STATUS_7);

				query.append(StringUtil.merge(statuses));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(kbFolderId);

				if (bindUrlTitle) {
					qPos.add(urlTitle);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_KBFI_UT_ST,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_G_KBFI_UT_ST,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param status the status
	 * @return the number of matching k b articles that the user has permission to view
	 */
	@Override
	public int filterCountByG_KBFI_UT_ST(long groupId, long kbFolderId,
		String urlTitle, int status) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle, status);
		}

		StringBundler query = new StringBundler(5);

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_KBFI_UT_ST_GROUPID_2);

		query.append(_FINDER_COLUMN_G_KBFI_UT_ST_KBFOLDERID_2);

		boolean bindUrlTitle = false;

		if (urlTitle == null) {
			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_1);
		}
		else if (urlTitle.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_3);
		}
		else {
			bindUrlTitle = true;

			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_2);
		}

		query.append(_FINDER_COLUMN_G_KBFI_UT_ST_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(kbFolderId);

			if (bindUrlTitle) {
				qPos.add(urlTitle);
			}

			qPos.add(status);

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

	/**
	 * Returns the number of k b articles that the user has permission to view where groupId = &#63; and kbFolderId = &#63; and urlTitle = &#63; and status = any &#63;.
	 *
	 * @param groupId the group ID
	 * @param kbFolderId the kb folder ID
	 * @param urlTitle the url title
	 * @param statuses the statuses
	 * @return the number of matching k b articles that the user has permission to view
	 */
	@Override
	public int filterCountByG_KBFI_UT_ST(long groupId, long kbFolderId,
		String urlTitle, int[] statuses) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_KBFI_UT_ST(groupId, kbFolderId, urlTitle, statuses);
		}

		if (statuses == null) {
			statuses = new int[0];
		}
		else {
			statuses = ArrayUtil.unique(statuses);
		}

		StringBundler query = new StringBundler();

		query.append(_FILTER_SQL_COUNT_KBARTICLE_WHERE);

		query.append(_FINDER_COLUMN_G_KBFI_UT_ST_GROUPID_2);

		query.append(_FINDER_COLUMN_G_KBFI_UT_ST_KBFOLDERID_2);

		boolean bindUrlTitle = false;

		if (urlTitle == null) {
			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_1);
		}
		else if (urlTitle.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_3);
		}
		else {
			bindUrlTitle = true;

			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_2);
		}

		if (statuses.length > 0) {
			query.append(StringPool.OPEN_PARENTHESIS);

			query.append(_FINDER_COLUMN_G_KBFI_UT_ST_STATUS_7);

			query.append(StringUtil.merge(statuses));

			query.append(StringPool.CLOSE_PARENTHESIS);

			query.append(StringPool.CLOSE_PARENTHESIS);
		}

		query.setStringAt(removeConjunction(query.stringAt(query.index() - 1)),
			query.index() - 1);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBArticle.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(kbFolderId);

			if (bindUrlTitle) {
				qPos.add(urlTitle);
			}

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

	private static final String _FINDER_COLUMN_G_KBFI_UT_ST_GROUPID_2 = "kbArticle.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_KBFI_UT_ST_KBFOLDERID_2 = "kbArticle.kbFolderId = ? AND ";
	private static final String _FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_1 = "kbArticle.urlTitle IS NULL AND ";
	private static final String _FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_2 = "kbArticle.urlTitle = ? AND ";
	private static final String _FINDER_COLUMN_G_KBFI_UT_ST_URLTITLE_3 = "(kbArticle.urlTitle IS NULL OR kbArticle.urlTitle = '') AND ";
	private static final String _FINDER_COLUMN_G_KBFI_UT_ST_STATUS_2 = "kbArticle.status = ?";
	private static final String _FINDER_COLUMN_G_KBFI_UT_ST_STATUS_7 = "kbArticle.status IN (";

	public KBArticlePersistenceImpl() {
		setModelClass(KBArticle.class);
	}

	/**
	 * Caches the k b article in the entity cache if it is enabled.
	 *
	 * @param kbArticle the k b article
	 */
	@Override
	public void cacheResult(KBArticle kbArticle) {
		EntityCacheUtil.putResult(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleImpl.class, kbArticle.getPrimaryKey(), kbArticle);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { kbArticle.getUuid(), kbArticle.getGroupId() },
			kbArticle);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_V,
			new Object[] { kbArticle.getResourcePrimKey(), kbArticle.getVersion() },
			kbArticle);

		kbArticle.resetOriginalValues();
	}

	/**
	 * Caches the k b articles in the entity cache if it is enabled.
	 *
	 * @param kbArticles the k b articles
	 */
	@Override
	public void cacheResult(List<KBArticle> kbArticles) {
		for (KBArticle kbArticle : kbArticles) {
			if (EntityCacheUtil.getResult(
						KBArticleModelImpl.ENTITY_CACHE_ENABLED,
						KBArticleImpl.class, kbArticle.getPrimaryKey()) == null) {
				cacheResult(kbArticle);
			}
			else {
				kbArticle.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all k b articles.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(KBArticleImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the k b article.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KBArticle kbArticle) {
		EntityCacheUtil.removeResult(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleImpl.class, kbArticle.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((KBArticleModelImpl)kbArticle);
	}

	@Override
	public void clearCache(List<KBArticle> kbArticles) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (KBArticle kbArticle : kbArticles) {
			EntityCacheUtil.removeResult(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
				KBArticleImpl.class, kbArticle.getPrimaryKey());

			clearUniqueFindersCache((KBArticleModelImpl)kbArticle);
		}
	}

	protected void cacheUniqueFindersCache(
		KBArticleModelImpl kbArticleModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					kbArticleModelImpl.getUuid(),
					kbArticleModelImpl.getGroupId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				kbArticleModelImpl);

			args = new Object[] {
					kbArticleModelImpl.getResourcePrimKey(),
					kbArticleModelImpl.getVersion()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_R_V, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_V, args,
				kbArticleModelImpl);
		}
		else {
			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getUuid(),
						kbArticleModelImpl.getGroupId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					kbArticleModelImpl);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_R_V.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getResourcePrimKey(),
						kbArticleModelImpl.getVersion()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_R_V, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_R_V, args,
					kbArticleModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		KBArticleModelImpl kbArticleModelImpl) {
		Object[] args = new Object[] {
				kbArticleModelImpl.getUuid(), kbArticleModelImpl.getGroupId()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((kbArticleModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					kbArticleModelImpl.getOriginalUuid(),
					kbArticleModelImpl.getOriginalGroupId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		args = new Object[] {
				kbArticleModelImpl.getResourcePrimKey(),
				kbArticleModelImpl.getVersion()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_V, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_R_V, args);

		if ((kbArticleModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_R_V.getColumnBitmask()) != 0) {
			args = new Object[] {
					kbArticleModelImpl.getOriginalResourcePrimKey(),
					kbArticleModelImpl.getOriginalVersion()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_V, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_R_V, args);
		}
	}

	/**
	 * Creates a new k b article with the primary key. Does not add the k b article to the database.
	 *
	 * @param kbArticleId the primary key for the new k b article
	 * @return the new k b article
	 */
	@Override
	public KBArticle create(long kbArticleId) {
		KBArticle kbArticle = new KBArticleImpl();

		kbArticle.setNew(true);
		kbArticle.setPrimaryKey(kbArticleId);

		String uuid = PortalUUIDUtil.generate();

		kbArticle.setUuid(uuid);

		return kbArticle;
	}

	/**
	 * Removes the k b article with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kbArticleId the primary key of the k b article
	 * @return the k b article that was removed
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle remove(long kbArticleId) throws NoSuchArticleException {
		return remove((Serializable)kbArticleId);
	}

	/**
	 * Removes the k b article with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the k b article
	 * @return the k b article that was removed
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle remove(Serializable primaryKey)
		throws NoSuchArticleException {
		Session session = null;

		try {
			session = openSession();

			KBArticle kbArticle = (KBArticle)session.get(KBArticleImpl.class,
					primaryKey);

			if (kbArticle == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchArticleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(kbArticle);
		}
		catch (NoSuchArticleException nsee) {
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
	protected KBArticle removeImpl(KBArticle kbArticle) {
		kbArticle = toUnwrappedModel(kbArticle);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(kbArticle)) {
				kbArticle = (KBArticle)session.get(KBArticleImpl.class,
						kbArticle.getPrimaryKeyObj());
			}

			if (kbArticle != null) {
				session.delete(kbArticle);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (kbArticle != null) {
			clearCache(kbArticle);
		}

		return kbArticle;
	}

	@Override
	public KBArticle updateImpl(KBArticle kbArticle) {
		kbArticle = toUnwrappedModel(kbArticle);

		boolean isNew = kbArticle.isNew();

		KBArticleModelImpl kbArticleModelImpl = (KBArticleModelImpl)kbArticle;

		if (Validator.isNull(kbArticle.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			kbArticle.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (kbArticle.getCreateDate() == null)) {
			if (serviceContext == null) {
				kbArticle.setCreateDate(now);
			}
			else {
				kbArticle.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!kbArticleModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				kbArticle.setModifiedDate(now);
			}
			else {
				kbArticle.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (kbArticle.isNew()) {
				session.save(kbArticle);

				kbArticle.setNew(false);
			}
			else {
				kbArticle = (KBArticle)session.merge(kbArticle);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !KBArticleModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { kbArticleModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalUuid(),
						kbArticleModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						kbArticleModelImpl.getUuid(),
						kbArticleModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEPRIMKEY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalResourcePrimKey()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RESOURCEPRIMKEY,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEPRIMKEY,
					args);

				args = new Object[] { kbArticleModelImpl.getResourcePrimKey() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RESOURCEPRIMKEY,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RESOURCEPRIMKEY,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalResourcePrimKey(),
						kbArticleModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_G, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G,
					args);

				args = new Object[] {
						kbArticleModelImpl.getResourcePrimKey(),
						kbArticleModelImpl.getGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_G, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_L.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalResourcePrimKey(),
						kbArticleModelImpl.getOriginalLatest()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_L, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_L,
					args);

				args = new Object[] {
						kbArticleModelImpl.getResourcePrimKey(),
						kbArticleModelImpl.getLatest()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_L, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_L,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_M.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalResourcePrimKey(),
						kbArticleModelImpl.getOriginalMain()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_M, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_M,
					args);

				args = new Object[] {
						kbArticleModelImpl.getResourcePrimKey(),
						kbArticleModelImpl.getMain()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_M, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_M,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalResourcePrimKey(),
						kbArticleModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S,
					args);

				args = new Object[] {
						kbArticleModelImpl.getResourcePrimKey(),
						kbArticleModelImpl.getStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_S,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_L.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalGroupId(),
						kbArticleModelImpl.getOriginalLatest()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_L, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_L,
					args);

				args = new Object[] {
						kbArticleModelImpl.getGroupId(),
						kbArticleModelImpl.getLatest()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_L, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_L,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalGroupId(),
						kbArticleModelImpl.getOriginalMain()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_M, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M,
					args);

				args = new Object[] {
						kbArticleModelImpl.getGroupId(),
						kbArticleModelImpl.getMain()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_M, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_M,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalGroupId(),
						kbArticleModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S,
					args);

				args = new Object[] {
						kbArticleModelImpl.getGroupId(),
						kbArticleModelImpl.getStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_S,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_L.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalCompanyId(),
						kbArticleModelImpl.getOriginalLatest()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_L, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_L,
					args);

				args = new Object[] {
						kbArticleModelImpl.getCompanyId(),
						kbArticleModelImpl.getLatest()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_L, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_L,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_M.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalCompanyId(),
						kbArticleModelImpl.getOriginalMain()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_M, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_M,
					args);

				args = new Object[] {
						kbArticleModelImpl.getCompanyId(),
						kbArticleModelImpl.getMain()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_M, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_M,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalCompanyId(),
						kbArticleModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S,
					args);

				args = new Object[] {
						kbArticleModelImpl.getCompanyId(),
						kbArticleModelImpl.getStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_S,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_L.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalParentResourcePrimKey(),
						kbArticleModelImpl.getOriginalLatest()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_P_L, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_L,
					args);

				args = new Object[] {
						kbArticleModelImpl.getParentResourcePrimKey(),
						kbArticleModelImpl.getLatest()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_P_L, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_L,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_M.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalParentResourcePrimKey(),
						kbArticleModelImpl.getOriginalMain()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_P_M, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_M,
					args);

				args = new Object[] {
						kbArticleModelImpl.getParentResourcePrimKey(),
						kbArticleModelImpl.getMain()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_P_M, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_M,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalParentResourcePrimKey(),
						kbArticleModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_P_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S,
					args);

				args = new Object[] {
						kbArticleModelImpl.getParentResourcePrimKey(),
						kbArticleModelImpl.getStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_P_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_P_S,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_L.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalResourcePrimKey(),
						kbArticleModelImpl.getOriginalGroupId(),
						kbArticleModelImpl.getOriginalLatest()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_G_L, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_L,
					args);

				args = new Object[] {
						kbArticleModelImpl.getResourcePrimKey(),
						kbArticleModelImpl.getGroupId(),
						kbArticleModelImpl.getLatest()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_G_L, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_L,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_M.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalResourcePrimKey(),
						kbArticleModelImpl.getOriginalGroupId(),
						kbArticleModelImpl.getOriginalMain()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_G_M, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_M,
					args);

				args = new Object[] {
						kbArticleModelImpl.getResourcePrimKey(),
						kbArticleModelImpl.getGroupId(),
						kbArticleModelImpl.getMain()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_G_M, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_M,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalResourcePrimKey(),
						kbArticleModelImpl.getOriginalGroupId(),
						kbArticleModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_G_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_S,
					args);

				args = new Object[] {
						kbArticleModelImpl.getResourcePrimKey(),
						kbArticleModelImpl.getGroupId(),
						kbArticleModelImpl.getStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_R_G_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_R_G_S,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_L.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalGroupId(),
						kbArticleModelImpl.getOriginalParentResourcePrimKey(),
						kbArticleModelImpl.getOriginalLatest()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_P_L, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_L,
					args);

				args = new Object[] {
						kbArticleModelImpl.getGroupId(),
						kbArticleModelImpl.getParentResourcePrimKey(),
						kbArticleModelImpl.getLatest()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_P_L, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_L,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_M.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalGroupId(),
						kbArticleModelImpl.getOriginalParentResourcePrimKey(),
						kbArticleModelImpl.getOriginalMain()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_P_M, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_M,
					args);

				args = new Object[] {
						kbArticleModelImpl.getGroupId(),
						kbArticleModelImpl.getParentResourcePrimKey(),
						kbArticleModelImpl.getMain()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_P_M, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_M,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalGroupId(),
						kbArticleModelImpl.getOriginalParentResourcePrimKey(),
						kbArticleModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_P_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_S,
					args);

				args = new Object[] {
						kbArticleModelImpl.getGroupId(),
						kbArticleModelImpl.getParentResourcePrimKey(),
						kbArticleModelImpl.getStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_P_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P_S,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_KBFI_UT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalGroupId(),
						kbArticleModelImpl.getOriginalKbFolderId(),
						kbArticleModelImpl.getOriginalUrlTitle()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_KBFI_UT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_KBFI_UT,
					args);

				args = new Object[] {
						kbArticleModelImpl.getGroupId(),
						kbArticleModelImpl.getKbFolderId(),
						kbArticleModelImpl.getUrlTitle()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_KBFI_UT,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_KBFI_UT,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_KBFI_S.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalGroupId(),
						kbArticleModelImpl.getOriginalKbFolderId(),
						kbArticleModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_KBFI_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_KBFI_S,
					args);

				args = new Object[] {
						kbArticleModelImpl.getGroupId(),
						kbArticleModelImpl.getKbFolderId(),
						kbArticleModelImpl.getStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_KBFI_S, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_KBFI_S,
					args);
			}

			if ((kbArticleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_KBFI_UT_ST.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbArticleModelImpl.getOriginalGroupId(),
						kbArticleModelImpl.getOriginalKbFolderId(),
						kbArticleModelImpl.getOriginalUrlTitle(),
						kbArticleModelImpl.getOriginalStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_KBFI_UT_ST,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_KBFI_UT_ST,
					args);

				args = new Object[] {
						kbArticleModelImpl.getGroupId(),
						kbArticleModelImpl.getKbFolderId(),
						kbArticleModelImpl.getUrlTitle(),
						kbArticleModelImpl.getStatus()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_KBFI_UT_ST,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_KBFI_UT_ST,
					args);
			}
		}

		EntityCacheUtil.putResult(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
			KBArticleImpl.class, kbArticle.getPrimaryKey(), kbArticle, false);

		clearUniqueFindersCache(kbArticleModelImpl);
		cacheUniqueFindersCache(kbArticleModelImpl, isNew);

		kbArticle.resetOriginalValues();

		return kbArticle;
	}

	protected KBArticle toUnwrappedModel(KBArticle kbArticle) {
		if (kbArticle instanceof KBArticleImpl) {
			return kbArticle;
		}

		KBArticleImpl kbArticleImpl = new KBArticleImpl();

		kbArticleImpl.setNew(kbArticle.isNew());
		kbArticleImpl.setPrimaryKey(kbArticle.getPrimaryKey());

		kbArticleImpl.setUuid(kbArticle.getUuid());
		kbArticleImpl.setKbArticleId(kbArticle.getKbArticleId());
		kbArticleImpl.setResourcePrimKey(kbArticle.getResourcePrimKey());
		kbArticleImpl.setGroupId(kbArticle.getGroupId());
		kbArticleImpl.setCompanyId(kbArticle.getCompanyId());
		kbArticleImpl.setUserId(kbArticle.getUserId());
		kbArticleImpl.setUserName(kbArticle.getUserName());
		kbArticleImpl.setCreateDate(kbArticle.getCreateDate());
		kbArticleImpl.setModifiedDate(kbArticle.getModifiedDate());
		kbArticleImpl.setRootResourcePrimKey(kbArticle.getRootResourcePrimKey());
		kbArticleImpl.setParentResourceClassNameId(kbArticle.getParentResourceClassNameId());
		kbArticleImpl.setParentResourcePrimKey(kbArticle.getParentResourcePrimKey());
		kbArticleImpl.setKbFolderId(kbArticle.getKbFolderId());
		kbArticleImpl.setVersion(kbArticle.getVersion());
		kbArticleImpl.setTitle(kbArticle.getTitle());
		kbArticleImpl.setUrlTitle(kbArticle.getUrlTitle());
		kbArticleImpl.setContent(kbArticle.getContent());
		kbArticleImpl.setDescription(kbArticle.getDescription());
		kbArticleImpl.setPriority(kbArticle.getPriority());
		kbArticleImpl.setSections(kbArticle.getSections());
		kbArticleImpl.setViewCount(kbArticle.getViewCount());
		kbArticleImpl.setLatest(kbArticle.isLatest());
		kbArticleImpl.setMain(kbArticle.isMain());
		kbArticleImpl.setSourceURL(kbArticle.getSourceURL());
		kbArticleImpl.setLastPublishDate(kbArticle.getLastPublishDate());
		kbArticleImpl.setStatus(kbArticle.getStatus());
		kbArticleImpl.setStatusByUserId(kbArticle.getStatusByUserId());
		kbArticleImpl.setStatusByUserName(kbArticle.getStatusByUserName());
		kbArticleImpl.setStatusDate(kbArticle.getStatusDate());

		return kbArticleImpl;
	}

	/**
	 * Returns the k b article with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the k b article
	 * @return the k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle findByPrimaryKey(Serializable primaryKey)
		throws NoSuchArticleException {
		KBArticle kbArticle = fetchByPrimaryKey(primaryKey);

		if (kbArticle == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchArticleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return kbArticle;
	}

	/**
	 * Returns the k b article with the primary key or throws a {@link NoSuchArticleException} if it could not be found.
	 *
	 * @param kbArticleId the primary key of the k b article
	 * @return the k b article
	 * @throws NoSuchArticleException if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle findByPrimaryKey(long kbArticleId)
		throws NoSuchArticleException {
		return findByPrimaryKey((Serializable)kbArticleId);
	}

	/**
	 * Returns the k b article with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the k b article
	 * @return the k b article, or <code>null</code> if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle fetchByPrimaryKey(Serializable primaryKey) {
		KBArticle kbArticle = (KBArticle)EntityCacheUtil.getResult(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
				KBArticleImpl.class, primaryKey);

		if (kbArticle == _nullKBArticle) {
			return null;
		}

		if (kbArticle == null) {
			Session session = null;

			try {
				session = openSession();

				kbArticle = (KBArticle)session.get(KBArticleImpl.class,
						primaryKey);

				if (kbArticle != null) {
					cacheResult(kbArticle);
				}
				else {
					EntityCacheUtil.putResult(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
						KBArticleImpl.class, primaryKey, _nullKBArticle);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
					KBArticleImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return kbArticle;
	}

	/**
	 * Returns the k b article with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kbArticleId the primary key of the k b article
	 * @return the k b article, or <code>null</code> if a k b article with the primary key could not be found
	 */
	@Override
	public KBArticle fetchByPrimaryKey(long kbArticleId) {
		return fetchByPrimaryKey((Serializable)kbArticleId);
	}

	@Override
	public Map<Serializable, KBArticle> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, KBArticle> map = new HashMap<Serializable, KBArticle>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			KBArticle kbArticle = fetchByPrimaryKey(primaryKey);

			if (kbArticle != null) {
				map.put(primaryKey, kbArticle);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			KBArticle kbArticle = (KBArticle)EntityCacheUtil.getResult(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
					KBArticleImpl.class, primaryKey);

			if (kbArticle == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, kbArticle);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_KBARTICLE_WHERE_PKS_IN);

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

			for (KBArticle kbArticle : (List<KBArticle>)q.list()) {
				map.put(kbArticle.getPrimaryKeyObj(), kbArticle);

				cacheResult(kbArticle);

				uncachedPrimaryKeys.remove(kbArticle.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(KBArticleModelImpl.ENTITY_CACHE_ENABLED,
					KBArticleImpl.class, primaryKey, _nullKBArticle);
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
	 * Returns all the k b articles.
	 *
	 * @return the k b articles
	 */
	@Override
	public List<KBArticle> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b articles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @return the range of k b articles
	 */
	@Override
	public List<KBArticle> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b articles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of k b articles
	 * @param end the upper bound of the range of k b articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of k b articles
	 */
	@Override
	public List<KBArticle> findAll(int start, int end,
		OrderByComparator<KBArticle> orderByComparator) {
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

		List<KBArticle> list = (List<KBArticle>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KBARTICLE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KBARTICLE;

				if (pagination) {
					sql = sql.concat(KBArticleModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBArticle>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the k b articles from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (KBArticle kbArticle : findAll()) {
			remove(kbArticle);
		}
	}

	/**
	 * Returns the number of k b articles.
	 *
	 * @return the number of k b articles
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KBARTICLE);

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
		return KBArticleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the k b article persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(KBArticleImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_KBARTICLE = "SELECT kbArticle FROM KBArticle kbArticle";
	private static final String _SQL_SELECT_KBARTICLE_WHERE_PKS_IN = "SELECT kbArticle FROM KBArticle kbArticle WHERE kbArticleId IN (";
	private static final String _SQL_SELECT_KBARTICLE_WHERE = "SELECT kbArticle FROM KBArticle kbArticle WHERE ";
	private static final String _SQL_COUNT_KBARTICLE = "SELECT COUNT(kbArticle) FROM KBArticle kbArticle";
	private static final String _SQL_COUNT_KBARTICLE_WHERE = "SELECT COUNT(kbArticle) FROM KBArticle kbArticle WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "kbArticle.rootResourcePrimKey";
	private static final String _FILTER_SQL_SELECT_KBARTICLE_WHERE = "SELECT DISTINCT {kbArticle.*} FROM KBArticle kbArticle WHERE ";
	private static final String _FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {KBArticle.*} FROM (SELECT DISTINCT kbArticle.kbArticleId FROM KBArticle kbArticle WHERE ";
	private static final String _FILTER_SQL_SELECT_KBARTICLE_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN KBArticle ON TEMP_TABLE.kbArticleId = KBArticle.kbArticleId";
	private static final String _FILTER_SQL_COUNT_KBARTICLE_WHERE = "SELECT COUNT(DISTINCT kbArticle.kbArticleId) AS COUNT_VALUE FROM KBArticle kbArticle WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "kbArticle";
	private static final String _FILTER_ENTITY_TABLE = "KBArticle";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kbArticle.";
	private static final String _ORDER_BY_ENTITY_TABLE = "KBArticle.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KBArticle exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KBArticle exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(KBArticlePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static final KBArticle _nullKBArticle = new KBArticleImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<KBArticle> toCacheModel() {
				return _nullKBArticleCacheModel;
			}
		};

	private static final CacheModel<KBArticle> _nullKBArticleCacheModel = new CacheModel<KBArticle>() {
			@Override
			public KBArticle toEntityModel() {
				return _nullKBArticle;
			}
		};
}