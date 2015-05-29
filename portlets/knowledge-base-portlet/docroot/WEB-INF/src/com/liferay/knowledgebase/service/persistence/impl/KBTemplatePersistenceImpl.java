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

import com.liferay.knowledgebase.NoSuchTemplateException;
import com.liferay.knowledgebase.model.KBTemplate;
import com.liferay.knowledgebase.model.impl.KBTemplateImpl;
import com.liferay.knowledgebase.model.impl.KBTemplateModelImpl;
import com.liferay.knowledgebase.service.persistence.KBTemplatePersistence;

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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
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
 * The persistence implementation for the k b template service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBTemplatePersistence
 * @see com.liferay.knowledgebase.service.persistence.KBTemplateUtil
 * @generated
 */
@ProviderType
public class KBTemplatePersistenceImpl extends BasePersistenceImpl<KBTemplate>
	implements KBTemplatePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KBTemplateUtil} to access the k b template persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KBTemplateImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateModelImpl.FINDER_CACHE_ENABLED, KBTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateModelImpl.FINDER_CACHE_ENABLED, KBTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateModelImpl.FINDER_CACHE_ENABLED, KBTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateModelImpl.FINDER_CACHE_ENABLED, KBTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			KBTemplateModelImpl.UUID_COLUMN_BITMASK |
			KBTemplateModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the k b templates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching k b templates
	 */
	@Override
	public List<KBTemplate> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b templates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of k b templates
	 * @param end the upper bound of the range of k b templates (not inclusive)
	 * @return the range of matching k b templates
	 */
	@Override
	public List<KBTemplate> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b templates where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of k b templates
	 * @param end the upper bound of the range of k b templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b templates
	 */
	@Override
	public List<KBTemplate> findByUuid(String uuid, int start, int end,
		OrderByComparator<KBTemplate> orderByComparator) {
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

		List<KBTemplate> list = (List<KBTemplate>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBTemplate kbTemplate : list) {
				if (!Validator.equals(uuid, kbTemplate.getUuid())) {
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

			query.append(_SQL_SELECT_KBTEMPLATE_WHERE);

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
				query.append(KBTemplateModelImpl.ORDER_BY_JPQL);
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
					list = (List<KBTemplate>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBTemplate>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b template
	 * @throws NoSuchTemplateException if a matching k b template could not be found
	 */
	@Override
	public KBTemplate findByUuid_First(String uuid,
		OrderByComparator<KBTemplate> orderByComparator)
		throws NoSuchTemplateException {
		KBTemplate kbTemplate = fetchByUuid_First(uuid, orderByComparator);

		if (kbTemplate != null) {
			return kbTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTemplateException(msg.toString());
	}

	/**
	 * Returns the first k b template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b template, or <code>null</code> if a matching k b template could not be found
	 */
	@Override
	public KBTemplate fetchByUuid_First(String uuid,
		OrderByComparator<KBTemplate> orderByComparator) {
		List<KBTemplate> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b template
	 * @throws NoSuchTemplateException if a matching k b template could not be found
	 */
	@Override
	public KBTemplate findByUuid_Last(String uuid,
		OrderByComparator<KBTemplate> orderByComparator)
		throws NoSuchTemplateException {
		KBTemplate kbTemplate = fetchByUuid_Last(uuid, orderByComparator);

		if (kbTemplate != null) {
			return kbTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTemplateException(msg.toString());
	}

	/**
	 * Returns the last k b template in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b template, or <code>null</code> if a matching k b template could not be found
	 */
	@Override
	public KBTemplate fetchByUuid_Last(String uuid,
		OrderByComparator<KBTemplate> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<KBTemplate> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b templates before and after the current k b template in the ordered set where uuid = &#63;.
	 *
	 * @param kbTemplateId the primary key of the current k b template
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b template
	 * @throws NoSuchTemplateException if a k b template with the primary key could not be found
	 */
	@Override
	public KBTemplate[] findByUuid_PrevAndNext(long kbTemplateId, String uuid,
		OrderByComparator<KBTemplate> orderByComparator)
		throws NoSuchTemplateException {
		KBTemplate kbTemplate = findByPrimaryKey(kbTemplateId);

		Session session = null;

		try {
			session = openSession();

			KBTemplate[] array = new KBTemplateImpl[3];

			array[0] = getByUuid_PrevAndNext(session, kbTemplate, uuid,
					orderByComparator, true);

			array[1] = kbTemplate;

			array[2] = getByUuid_PrevAndNext(session, kbTemplate, uuid,
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

	protected KBTemplate getByUuid_PrevAndNext(Session session,
		KBTemplate kbTemplate, String uuid,
		OrderByComparator<KBTemplate> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBTEMPLATE_WHERE);

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
			query.append(KBTemplateModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(kbTemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBTemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the k b templates where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (KBTemplate kbTemplate : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(kbTemplate);
		}
	}

	/**
	 * Returns the number of k b templates where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching k b templates
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KBTEMPLATE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "kbTemplate.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "kbTemplate.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(kbTemplate.uuid IS NULL OR kbTemplate.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateModelImpl.FINDER_CACHE_ENABLED, KBTemplateImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			KBTemplateModelImpl.UUID_COLUMN_BITMASK |
			KBTemplateModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the k b template where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchTemplateException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching k b template
	 * @throws NoSuchTemplateException if a matching k b template could not be found
	 */
	@Override
	public KBTemplate findByUUID_G(String uuid, long groupId)
		throws NoSuchTemplateException {
		KBTemplate kbTemplate = fetchByUUID_G(uuid, groupId);

		if (kbTemplate == null) {
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

			throw new NoSuchTemplateException(msg.toString());
		}

		return kbTemplate;
	}

	/**
	 * Returns the k b template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching k b template, or <code>null</code> if a matching k b template could not be found
	 */
	@Override
	public KBTemplate fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the k b template where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching k b template, or <code>null</code> if a matching k b template could not be found
	 */
	@Override
	public KBTemplate fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof KBTemplate) {
			KBTemplate kbTemplate = (KBTemplate)result;

			if (!Validator.equals(uuid, kbTemplate.getUuid()) ||
					(groupId != kbTemplate.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_KBTEMPLATE_WHERE);

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

				List<KBTemplate> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					KBTemplate kbTemplate = list.get(0);

					result = kbTemplate;

					cacheResult(kbTemplate);

					if ((kbTemplate.getUuid() == null) ||
							!kbTemplate.getUuid().equals(uuid) ||
							(kbTemplate.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, kbTemplate);
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
			return (KBTemplate)result;
		}
	}

	/**
	 * Removes the k b template where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the k b template that was removed
	 */
	@Override
	public KBTemplate removeByUUID_G(String uuid, long groupId)
		throws NoSuchTemplateException {
		KBTemplate kbTemplate = findByUUID_G(uuid, groupId);

		return remove(kbTemplate);
	}

	/**
	 * Returns the number of k b templates where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching k b templates
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBTEMPLATE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "kbTemplate.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "kbTemplate.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(kbTemplate.uuid IS NULL OR kbTemplate.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "kbTemplate.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateModelImpl.FINDER_CACHE_ENABLED, KBTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateModelImpl.FINDER_CACHE_ENABLED, KBTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			KBTemplateModelImpl.UUID_COLUMN_BITMASK |
			KBTemplateModelImpl.COMPANYID_COLUMN_BITMASK |
			KBTemplateModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the k b templates where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching k b templates
	 */
	@Override
	public List<KBTemplate> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b templates where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of k b templates
	 * @param end the upper bound of the range of k b templates (not inclusive)
	 * @return the range of matching k b templates
	 */
	@Override
	public List<KBTemplate> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b templates where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of k b templates
	 * @param end the upper bound of the range of k b templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b templates
	 */
	@Override
	public List<KBTemplate> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<KBTemplate> orderByComparator) {
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

		List<KBTemplate> list = (List<KBTemplate>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBTemplate kbTemplate : list) {
				if (!Validator.equals(uuid, kbTemplate.getUuid()) ||
						(companyId != kbTemplate.getCompanyId())) {
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

			query.append(_SQL_SELECT_KBTEMPLATE_WHERE);

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
				query.append(KBTemplateModelImpl.ORDER_BY_JPQL);
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
					list = (List<KBTemplate>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBTemplate>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b template
	 * @throws NoSuchTemplateException if a matching k b template could not be found
	 */
	@Override
	public KBTemplate findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<KBTemplate> orderByComparator)
		throws NoSuchTemplateException {
		KBTemplate kbTemplate = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (kbTemplate != null) {
			return kbTemplate;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTemplateException(msg.toString());
	}

	/**
	 * Returns the first k b template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b template, or <code>null</code> if a matching k b template could not be found
	 */
	@Override
	public KBTemplate fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<KBTemplate> orderByComparator) {
		List<KBTemplate> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b template
	 * @throws NoSuchTemplateException if a matching k b template could not be found
	 */
	@Override
	public KBTemplate findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<KBTemplate> orderByComparator)
		throws NoSuchTemplateException {
		KBTemplate kbTemplate = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (kbTemplate != null) {
			return kbTemplate;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTemplateException(msg.toString());
	}

	/**
	 * Returns the last k b template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b template, or <code>null</code> if a matching k b template could not be found
	 */
	@Override
	public KBTemplate fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<KBTemplate> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<KBTemplate> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b templates before and after the current k b template in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param kbTemplateId the primary key of the current k b template
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b template
	 * @throws NoSuchTemplateException if a k b template with the primary key could not be found
	 */
	@Override
	public KBTemplate[] findByUuid_C_PrevAndNext(long kbTemplateId,
		String uuid, long companyId,
		OrderByComparator<KBTemplate> orderByComparator)
		throws NoSuchTemplateException {
		KBTemplate kbTemplate = findByPrimaryKey(kbTemplateId);

		Session session = null;

		try {
			session = openSession();

			KBTemplate[] array = new KBTemplateImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, kbTemplate, uuid,
					companyId, orderByComparator, true);

			array[1] = kbTemplate;

			array[2] = getByUuid_C_PrevAndNext(session, kbTemplate, uuid,
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

	protected KBTemplate getByUuid_C_PrevAndNext(Session session,
		KBTemplate kbTemplate, String uuid, long companyId,
		OrderByComparator<KBTemplate> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBTEMPLATE_WHERE);

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
			query.append(KBTemplateModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(kbTemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBTemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the k b templates where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (KBTemplate kbTemplate : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbTemplate);
		}
	}

	/**
	 * Returns the number of k b templates where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching k b templates
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBTEMPLATE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "kbTemplate.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "kbTemplate.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(kbTemplate.uuid IS NULL OR kbTemplate.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "kbTemplate.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateModelImpl.FINDER_CACHE_ENABLED, KBTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateModelImpl.FINDER_CACHE_ENABLED, KBTemplateImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			KBTemplateModelImpl.GROUPID_COLUMN_BITMASK |
			KBTemplateModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the k b templates where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching k b templates
	 */
	@Override
	public List<KBTemplate> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b templates where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of k b templates
	 * @param end the upper bound of the range of k b templates (not inclusive)
	 * @return the range of matching k b templates
	 */
	@Override
	public List<KBTemplate> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b templates where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of k b templates
	 * @param end the upper bound of the range of k b templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b templates
	 */
	@Override
	public List<KBTemplate> findByGroupId(long groupId, int start, int end,
		OrderByComparator<KBTemplate> orderByComparator) {
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

		List<KBTemplate> list = (List<KBTemplate>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBTemplate kbTemplate : list) {
				if ((groupId != kbTemplate.getGroupId())) {
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

			query.append(_SQL_SELECT_KBTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBTemplateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<KBTemplate>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBTemplate>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b template in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b template
	 * @throws NoSuchTemplateException if a matching k b template could not be found
	 */
	@Override
	public KBTemplate findByGroupId_First(long groupId,
		OrderByComparator<KBTemplate> orderByComparator)
		throws NoSuchTemplateException {
		KBTemplate kbTemplate = fetchByGroupId_First(groupId, orderByComparator);

		if (kbTemplate != null) {
			return kbTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTemplateException(msg.toString());
	}

	/**
	 * Returns the first k b template in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b template, or <code>null</code> if a matching k b template could not be found
	 */
	@Override
	public KBTemplate fetchByGroupId_First(long groupId,
		OrderByComparator<KBTemplate> orderByComparator) {
		List<KBTemplate> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b template in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b template
	 * @throws NoSuchTemplateException if a matching k b template could not be found
	 */
	@Override
	public KBTemplate findByGroupId_Last(long groupId,
		OrderByComparator<KBTemplate> orderByComparator)
		throws NoSuchTemplateException {
		KBTemplate kbTemplate = fetchByGroupId_Last(groupId, orderByComparator);

		if (kbTemplate != null) {
			return kbTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTemplateException(msg.toString());
	}

	/**
	 * Returns the last k b template in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b template, or <code>null</code> if a matching k b template could not be found
	 */
	@Override
	public KBTemplate fetchByGroupId_Last(long groupId,
		OrderByComparator<KBTemplate> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<KBTemplate> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b templates before and after the current k b template in the ordered set where groupId = &#63;.
	 *
	 * @param kbTemplateId the primary key of the current k b template
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b template
	 * @throws NoSuchTemplateException if a k b template with the primary key could not be found
	 */
	@Override
	public KBTemplate[] findByGroupId_PrevAndNext(long kbTemplateId,
		long groupId, OrderByComparator<KBTemplate> orderByComparator)
		throws NoSuchTemplateException {
		KBTemplate kbTemplate = findByPrimaryKey(kbTemplateId);

		Session session = null;

		try {
			session = openSession();

			KBTemplate[] array = new KBTemplateImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, kbTemplate, groupId,
					orderByComparator, true);

			array[1] = kbTemplate;

			array[2] = getByGroupId_PrevAndNext(session, kbTemplate, groupId,
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

	protected KBTemplate getByGroupId_PrevAndNext(Session session,
		KBTemplate kbTemplate, long groupId,
		OrderByComparator<KBTemplate> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBTEMPLATE_WHERE);

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
			query.append(KBTemplateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbTemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBTemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b templates that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching k b templates that the user has permission to view
	 */
	@Override
	public List<KBTemplate> filterFindByGroupId(long groupId) {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b templates that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of k b templates
	 * @param end the upper bound of the range of k b templates (not inclusive)
	 * @return the range of matching k b templates that the user has permission to view
	 */
	@Override
	public List<KBTemplate> filterFindByGroupId(long groupId, int start, int end) {
		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b templates that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of k b templates
	 * @param end the upper bound of the range of k b templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b templates that the user has permission to view
	 */
	@Override
	public List<KBTemplate> filterFindByGroupId(long groupId, int start,
		int end, OrderByComparator<KBTemplate> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId(groupId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBTEMPLATE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBTEMPLATE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBTEMPLATE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBTemplateModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBTemplateModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBTemplate.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBTemplateImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBTemplateImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<KBTemplate>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b templates before and after the current k b template in the ordered set of k b templates that the user has permission to view where groupId = &#63;.
	 *
	 * @param kbTemplateId the primary key of the current k b template
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b template
	 * @throws NoSuchTemplateException if a k b template with the primary key could not be found
	 */
	@Override
	public KBTemplate[] filterFindByGroupId_PrevAndNext(long kbTemplateId,
		long groupId, OrderByComparator<KBTemplate> orderByComparator)
		throws NoSuchTemplateException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(kbTemplateId, groupId,
				orderByComparator);
		}

		KBTemplate kbTemplate = findByPrimaryKey(kbTemplateId);

		Session session = null;

		try {
			session = openSession();

			KBTemplate[] array = new KBTemplateImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session, kbTemplate,
					groupId, orderByComparator, true);

			array[1] = kbTemplate;

			array[2] = filterGetByGroupId_PrevAndNext(session, kbTemplate,
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

	protected KBTemplate filterGetByGroupId_PrevAndNext(Session session,
		KBTemplate kbTemplate, long groupId,
		OrderByComparator<KBTemplate> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBTEMPLATE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBTEMPLATE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBTEMPLATE_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBTemplateModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBTemplateModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBTemplate.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBTemplateImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBTemplateImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbTemplate);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBTemplate> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the k b templates where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (KBTemplate kbTemplate : findByGroupId(groupId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(kbTemplate);
		}
	}

	/**
	 * Returns the number of k b templates where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching k b templates
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KBTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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
	 * Returns the number of k b templates that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching k b templates that the user has permission to view
	 */
	@Override
	public int filterCountByGroupId(long groupId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_KBTEMPLATE_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBTemplate.class.getName(),
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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "kbTemplate.groupId = ?";

	public KBTemplatePersistenceImpl() {
		setModelClass(KBTemplate.class);
	}

	/**
	 * Caches the k b template in the entity cache if it is enabled.
	 *
	 * @param kbTemplate the k b template
	 */
	@Override
	public void cacheResult(KBTemplate kbTemplate) {
		EntityCacheUtil.putResult(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateImpl.class, kbTemplate.getPrimaryKey(), kbTemplate);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { kbTemplate.getUuid(), kbTemplate.getGroupId() },
			kbTemplate);

		kbTemplate.resetOriginalValues();
	}

	/**
	 * Caches the k b templates in the entity cache if it is enabled.
	 *
	 * @param kbTemplates the k b templates
	 */
	@Override
	public void cacheResult(List<KBTemplate> kbTemplates) {
		for (KBTemplate kbTemplate : kbTemplates) {
			if (EntityCacheUtil.getResult(
						KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
						KBTemplateImpl.class, kbTemplate.getPrimaryKey()) == null) {
				cacheResult(kbTemplate);
			}
			else {
				kbTemplate.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all k b templates.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(KBTemplateImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the k b template.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KBTemplate kbTemplate) {
		EntityCacheUtil.removeResult(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateImpl.class, kbTemplate.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(kbTemplate);
	}

	@Override
	public void clearCache(List<KBTemplate> kbTemplates) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (KBTemplate kbTemplate : kbTemplates) {
			EntityCacheUtil.removeResult(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
				KBTemplateImpl.class, kbTemplate.getPrimaryKey());

			clearUniqueFindersCache(kbTemplate);
		}
	}

	protected void cacheUniqueFindersCache(KBTemplate kbTemplate) {
		if (kbTemplate.isNew()) {
			Object[] args = new Object[] {
					kbTemplate.getUuid(), kbTemplate.getGroupId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				kbTemplate);
		}
		else {
			KBTemplateModelImpl kbTemplateModelImpl = (KBTemplateModelImpl)kbTemplate;

			if ((kbTemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbTemplate.getUuid(), kbTemplate.getGroupId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					kbTemplate);
			}
		}
	}

	protected void clearUniqueFindersCache(KBTemplate kbTemplate) {
		KBTemplateModelImpl kbTemplateModelImpl = (KBTemplateModelImpl)kbTemplate;

		Object[] args = new Object[] {
				kbTemplate.getUuid(), kbTemplate.getGroupId()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((kbTemplateModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					kbTemplateModelImpl.getOriginalUuid(),
					kbTemplateModelImpl.getOriginalGroupId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new k b template with the primary key. Does not add the k b template to the database.
	 *
	 * @param kbTemplateId the primary key for the new k b template
	 * @return the new k b template
	 */
	@Override
	public KBTemplate create(long kbTemplateId) {
		KBTemplate kbTemplate = new KBTemplateImpl();

		kbTemplate.setNew(true);
		kbTemplate.setPrimaryKey(kbTemplateId);

		String uuid = PortalUUIDUtil.generate();

		kbTemplate.setUuid(uuid);

		return kbTemplate;
	}

	/**
	 * Removes the k b template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kbTemplateId the primary key of the k b template
	 * @return the k b template that was removed
	 * @throws NoSuchTemplateException if a k b template with the primary key could not be found
	 */
	@Override
	public KBTemplate remove(long kbTemplateId) throws NoSuchTemplateException {
		return remove((Serializable)kbTemplateId);
	}

	/**
	 * Removes the k b template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the k b template
	 * @return the k b template that was removed
	 * @throws NoSuchTemplateException if a k b template with the primary key could not be found
	 */
	@Override
	public KBTemplate remove(Serializable primaryKey)
		throws NoSuchTemplateException {
		Session session = null;

		try {
			session = openSession();

			KBTemplate kbTemplate = (KBTemplate)session.get(KBTemplateImpl.class,
					primaryKey);

			if (kbTemplate == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTemplateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(kbTemplate);
		}
		catch (NoSuchTemplateException nsee) {
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
	protected KBTemplate removeImpl(KBTemplate kbTemplate) {
		kbTemplate = toUnwrappedModel(kbTemplate);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(kbTemplate)) {
				kbTemplate = (KBTemplate)session.get(KBTemplateImpl.class,
						kbTemplate.getPrimaryKeyObj());
			}

			if (kbTemplate != null) {
				session.delete(kbTemplate);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (kbTemplate != null) {
			clearCache(kbTemplate);
		}

		return kbTemplate;
	}

	@Override
	public KBTemplate updateImpl(KBTemplate kbTemplate) {
		kbTemplate = toUnwrappedModel(kbTemplate);

		boolean isNew = kbTemplate.isNew();

		KBTemplateModelImpl kbTemplateModelImpl = (KBTemplateModelImpl)kbTemplate;

		if (Validator.isNull(kbTemplate.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			kbTemplate.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (kbTemplate.getCreateDate() == null)) {
			if (serviceContext == null) {
				kbTemplate.setCreateDate(now);
			}
			else {
				kbTemplate.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!kbTemplateModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				kbTemplate.setModifiedDate(now);
			}
			else {
				kbTemplate.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (kbTemplate.isNew()) {
				session.save(kbTemplate);

				kbTemplate.setNew(false);
			}
			else {
				session.merge(kbTemplate);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !KBTemplateModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((kbTemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbTemplateModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { kbTemplateModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((kbTemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbTemplateModelImpl.getOriginalUuid(),
						kbTemplateModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						kbTemplateModelImpl.getUuid(),
						kbTemplateModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((kbTemplateModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbTemplateModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { kbTemplateModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}
		}

		EntityCacheUtil.putResult(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
			KBTemplateImpl.class, kbTemplate.getPrimaryKey(), kbTemplate, false);

		clearUniqueFindersCache(kbTemplate);
		cacheUniqueFindersCache(kbTemplate);

		kbTemplate.resetOriginalValues();

		return kbTemplate;
	}

	protected KBTemplate toUnwrappedModel(KBTemplate kbTemplate) {
		if (kbTemplate instanceof KBTemplateImpl) {
			return kbTemplate;
		}

		KBTemplateImpl kbTemplateImpl = new KBTemplateImpl();

		kbTemplateImpl.setNew(kbTemplate.isNew());
		kbTemplateImpl.setPrimaryKey(kbTemplate.getPrimaryKey());

		kbTemplateImpl.setUuid(kbTemplate.getUuid());
		kbTemplateImpl.setKbTemplateId(kbTemplate.getKbTemplateId());
		kbTemplateImpl.setGroupId(kbTemplate.getGroupId());
		kbTemplateImpl.setCompanyId(kbTemplate.getCompanyId());
		kbTemplateImpl.setUserId(kbTemplate.getUserId());
		kbTemplateImpl.setUserName(kbTemplate.getUserName());
		kbTemplateImpl.setCreateDate(kbTemplate.getCreateDate());
		kbTemplateImpl.setModifiedDate(kbTemplate.getModifiedDate());
		kbTemplateImpl.setTitle(kbTemplate.getTitle());
		kbTemplateImpl.setContent(kbTemplate.getContent());

		return kbTemplateImpl;
	}

	/**
	 * Returns the k b template with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the k b template
	 * @return the k b template
	 * @throws NoSuchTemplateException if a k b template with the primary key could not be found
	 */
	@Override
	public KBTemplate findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTemplateException {
		KBTemplate kbTemplate = fetchByPrimaryKey(primaryKey);

		if (kbTemplate == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTemplateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return kbTemplate;
	}

	/**
	 * Returns the k b template with the primary key or throws a {@link NoSuchTemplateException} if it could not be found.
	 *
	 * @param kbTemplateId the primary key of the k b template
	 * @return the k b template
	 * @throws NoSuchTemplateException if a k b template with the primary key could not be found
	 */
	@Override
	public KBTemplate findByPrimaryKey(long kbTemplateId)
		throws NoSuchTemplateException {
		return findByPrimaryKey((Serializable)kbTemplateId);
	}

	/**
	 * Returns the k b template with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the k b template
	 * @return the k b template, or <code>null</code> if a k b template with the primary key could not be found
	 */
	@Override
	public KBTemplate fetchByPrimaryKey(Serializable primaryKey) {
		KBTemplate kbTemplate = (KBTemplate)EntityCacheUtil.getResult(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
				KBTemplateImpl.class, primaryKey);

		if (kbTemplate == _nullKBTemplate) {
			return null;
		}

		if (kbTemplate == null) {
			Session session = null;

			try {
				session = openSession();

				kbTemplate = (KBTemplate)session.get(KBTemplateImpl.class,
						primaryKey);

				if (kbTemplate != null) {
					cacheResult(kbTemplate);
				}
				else {
					EntityCacheUtil.putResult(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
						KBTemplateImpl.class, primaryKey, _nullKBTemplate);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
					KBTemplateImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return kbTemplate;
	}

	/**
	 * Returns the k b template with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kbTemplateId the primary key of the k b template
	 * @return the k b template, or <code>null</code> if a k b template with the primary key could not be found
	 */
	@Override
	public KBTemplate fetchByPrimaryKey(long kbTemplateId) {
		return fetchByPrimaryKey((Serializable)kbTemplateId);
	}

	@Override
	public Map<Serializable, KBTemplate> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, KBTemplate> map = new HashMap<Serializable, KBTemplate>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			KBTemplate kbTemplate = fetchByPrimaryKey(primaryKey);

			if (kbTemplate != null) {
				map.put(primaryKey, kbTemplate);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			KBTemplate kbTemplate = (KBTemplate)EntityCacheUtil.getResult(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
					KBTemplateImpl.class, primaryKey);

			if (kbTemplate == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, kbTemplate);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_KBTEMPLATE_WHERE_PKS_IN);

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

			for (KBTemplate kbTemplate : (List<KBTemplate>)q.list()) {
				map.put(kbTemplate.getPrimaryKeyObj(), kbTemplate);

				cacheResult(kbTemplate);

				uncachedPrimaryKeys.remove(kbTemplate.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(KBTemplateModelImpl.ENTITY_CACHE_ENABLED,
					KBTemplateImpl.class, primaryKey, _nullKBTemplate);
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
	 * Returns all the k b templates.
	 *
	 * @return the k b templates
	 */
	@Override
	public List<KBTemplate> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of k b templates
	 * @param end the upper bound of the range of k b templates (not inclusive)
	 * @return the range of k b templates
	 */
	@Override
	public List<KBTemplate> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of k b templates
	 * @param end the upper bound of the range of k b templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of k b templates
	 */
	@Override
	public List<KBTemplate> findAll(int start, int end,
		OrderByComparator<KBTemplate> orderByComparator) {
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

		List<KBTemplate> list = (List<KBTemplate>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KBTEMPLATE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KBTEMPLATE;

				if (pagination) {
					sql = sql.concat(KBTemplateModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<KBTemplate>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBTemplate>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the k b templates from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (KBTemplate kbTemplate : findAll()) {
			remove(kbTemplate);
		}
	}

	/**
	 * Returns the number of k b templates.
	 *
	 * @return the number of k b templates
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KBTEMPLATE);

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
	 * Initializes the k b template persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(KBTemplateImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_KBTEMPLATE = "SELECT kbTemplate FROM KBTemplate kbTemplate";
	private static final String _SQL_SELECT_KBTEMPLATE_WHERE_PKS_IN = "SELECT kbTemplate FROM KBTemplate kbTemplate WHERE kbTemplateId IN (";
	private static final String _SQL_SELECT_KBTEMPLATE_WHERE = "SELECT kbTemplate FROM KBTemplate kbTemplate WHERE ";
	private static final String _SQL_COUNT_KBTEMPLATE = "SELECT COUNT(kbTemplate) FROM KBTemplate kbTemplate";
	private static final String _SQL_COUNT_KBTEMPLATE_WHERE = "SELECT COUNT(kbTemplate) FROM KBTemplate kbTemplate WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "kbTemplate.kbTemplateId";
	private static final String _FILTER_SQL_SELECT_KBTEMPLATE_WHERE = "SELECT DISTINCT {kbTemplate.*} FROM KBTemplate kbTemplate WHERE ";
	private static final String _FILTER_SQL_SELECT_KBTEMPLATE_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {KBTemplate.*} FROM (SELECT DISTINCT kbTemplate.kbTemplateId FROM KBTemplate kbTemplate WHERE ";
	private static final String _FILTER_SQL_SELECT_KBTEMPLATE_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN KBTemplate ON TEMP_TABLE.kbTemplateId = KBTemplate.kbTemplateId";
	private static final String _FILTER_SQL_COUNT_KBTEMPLATE_WHERE = "SELECT COUNT(DISTINCT kbTemplate.kbTemplateId) AS COUNT_VALUE FROM KBTemplate kbTemplate WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "kbTemplate";
	private static final String _FILTER_ENTITY_TABLE = "KBTemplate";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kbTemplate.";
	private static final String _ORDER_BY_ENTITY_TABLE = "KBTemplate.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KBTemplate exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KBTemplate exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(KBTemplatePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static final KBTemplate _nullKBTemplate = new KBTemplateImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<KBTemplate> toCacheModel() {
				return _nullKBTemplateCacheModel;
			}
		};

	private static final CacheModel<KBTemplate> _nullKBTemplateCacheModel = new CacheModel<KBTemplate>() {
			@Override
			public KBTemplate toEntityModel() {
				return _nullKBTemplate;
			}
		};
}